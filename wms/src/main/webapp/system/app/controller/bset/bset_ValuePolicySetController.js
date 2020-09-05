/**模块名称：租仓策略设置
 * 模块编码：B201
 * 创建：zhm
 */
var cmbCombo='';
var typeB201='';      //用于判断是否重新加载数据，值为“edit”则加载数据，值为“add”不加载数据                     
Ext.define('cms.controller.bset.bset_ValuePolicySetController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bset.bset_ValuePolicySetUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//新增
			'bset_ValuePolicySetUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//删除
			'bset_ValuePolicySetUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			//修改
			'bset_ValuePolicySetUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//浏览
			'bset_ValuePolicySetUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},			
			//双击
			'bset_ValuePolicySetUI grid[id=grid_01_B201]':{
					itemdblclick:this.detailEdit
			},
			//重新加载添加窗口
			'bset_ValuePolicySetAddOrEditWindow button[name=add]':{
				click:this.add
			},
			//关闭窗口
			'bset_ValuePolicySetAddOrEditWindow button[name=close]':{
				click:this.colse
			},
			//保存
			'bset_ValuePolicySetAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//业主切换
			'bset_ValuePolicySetUI combo[id=cmbOwnerB201]':{
				change:this.bset_valuepolicyset_select
			},
			
			//加载取值方式 ，根据计费项目 和计费单位
			'bset_ValuePolicySetAddOrEditWindow combo[id=cmbBillingProjectB201]':{
				select:this.valueFlagSelect
			},
			//加载取值方式 ，根据计费项目 和计费单位
			'bset_ValuePolicySetAddOrEditWindow combo[id=cmbBillingUnitB201]':{
				select:this.valueFlagSelect
			},
			//上一条记录
			'bset_ValuePolicySetAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bset_ValuePolicySetAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//备注的加载
			'bset_ValuePolicySetAddOrEditWindow combo[id=cmbValueFlagB201]':{
				change:this.remarkLoad
			}
		});
	},
	//删除选中数据
		detailDelete:function(){
		var objdata = Ext.getCmp('grid_01_B201').getSelectionModel().getSelection();  
        if (objdata.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.getCmp('grid_01_B201').getStore().remove(objdata);
    					Ext.Ajax.request({
    						url:'bset_ValuePolicySetAction_deleteLine.action',
    						params :{
    							strBillingProject:objdata[0].get("billingProject"),
    							strOwnerNo: objdata[0].get("ownerNo")
    						},
    						success : function(response) {
    						Ext.getCmp('grid_01_B201').getStore().load();
    					}
    					});
    				}			
            });
        }
	},
	valueFlagSelect:function(){
		//根据计费项目和计费单位加载取值方式
		if(!Ext.isEmpty(Ext.getCmp('cmbBillingProjectB201').getValue()) &&
		!Ext.isEmpty(Ext.getCmp('cmbBillingUnitB201').getValue()))
		{
			var wheresql=
			{
					strBillingProject:Ext.getCmp('cmbBillingProjectB201').getValue(),
					strBillingUnit:Ext.getCmp('cmbBillingUnitB201').getValue()
			};
			Ext.apply(Ext.getCmp('cmbValueFlagB201').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('cmbValueFlagB201').getStore().removeAll();
			Ext.getCmp('cmbValueFlagB201').setValue(null);
			Ext.getCmp('cmbValueFlagB201').getStore().load();
		}
	},
	//实现新增功能（清空窗口的内容）	
	add:function(){
		Ext.getCmp('IdFormB201').getForm().reset();
		bindEnterSkip($('IdFormB201'));//调用键盘处理方法
		typeB201='add';
	},
	
	//界面初始化
	initializtion:function(I)
	{
	},
	
	//调用新增窗口
	detailAdd:function(){
		Ext.create('cms.view.bset.window.bset_ValuePolicySetAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerB201_2','cmbBillingProjectB201'),false);
		addCommMenu5('bdef_MenuWidgetB201');
		typeB201='add';
	},
	bset_valuepolicyset_select:function(){
		var wheresql=
		{
				strOwnerNo:Ext.getCmp('cmbOwnerB201').getValue()
		};
		cmbCombo='grid_01_B201';
		Ext.getCmp('grid_01_B201').getStore().currentPage=1;
		loadbdef_WmsWarehouseBaseB201(cmbCombo,wheresql);		
	},
	//修改
	detailEdit:function(){
		var data = Ext.getCmp('grid_01_B201').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.create('cms.view.bset.window.bset_ValuePolicySetAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
		    Ext.getCmp('cmbOwnerB201_2').disable();
		    Ext.getCmp('cmbBillingProjectB201').disable();
			this.loadWmsDefbaseDataB201();
			commonMenu5PrevOrNext('bdef_MenuWidgetB201','grid_01_B201',0);
			updateCommMenu5('bdef_MenuWidgetB201');
		}
		typeB201='edit';	
	},
	/**
	 * 浏览
	 */
	detailBrowse:function(){
		var objBrowseData = Ext.getCmp('grid_01_B201').getSelectionModel().getSelection();
		if (objBrowseData.length != 0) {
			Ext.create('cms.view.bset.window.bset_ValuePolicySetAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB201='browse';
			browseCommMenu5('bdef_MenuWidgetB201');
			commonMenu5PrevOrNext('bdef_MenuWidgetB201','grid_01_B201',0);
			commonSetFieldReadOnly('typeB201',true);
			this.loadWmsDefbaseDataB201();
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
	},	
	//关闭
	colse:function(){
		Ext.getCmp('bset_ValuePolicySetAddOrEditWindow').close();
	},
	//上一条数据
	prev:function(){
		commonMenu5PrevOrNext('bdef_MenuWidgetB201','grid_01_B201',-1);
		this.loadWmsDefbaseDataB201();
	},
	//下一条数据
	next:function(){
		commonMenu5PrevOrNext('bdef_MenuWidgetB201','grid_01_B201',1);
		this.loadWmsDefbaseDataB201();
	},
	save:function(){
		if(Ext.getCmp('cmbOwnerB201_2').getValue()==null)
		{
			Ext.example.msg('提示','请选择货主！');
			return;
		}else if(Ext.getCmp('cmbBillingProjectB201').getValue()==null)
		{
			Ext.example.msg('提示','请选择计费项目！');
			return;
		}else if(Ext.getCmp('cmbBillingUnitB201').getValue()==null)
		{
			Ext.example.msg('提示','请选择计费单位！');
			return;
		}else if(Ext.getCmp('cmbValueFlagB201').getValue()==null)
		{
			Ext.example.msg('提示','请选择取值方式！');
			return;
		}

			addbset_ValuePolicySetB201();

		
	},
	remarkLoad:function(){
		Ext.Ajax.request({
			url : 'bset_ValuePolicySetAction_getRemarkCombo',
			params : {
				strBillingProject:Ext.getCmp('cmbBillingProjectB201').getValue(),
				strBillingUnit:Ext.getCmp('cmbBillingUnitB201').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res){
		    		Ext.getCmp('txtRemarkB201').setValue(res[0]);
		    	}
			}
		});
	},
	loadWmsDefbaseDataB201:function(){		
		var wms=Ext.getCmp('grid_01_B201').getSelectionModel().getSelection();
		if(cust.length>0)
		{	
			Ext.getCmp('cmbOwnerB201_2').setValue(wms[0].data.ownerNo);
			Ext.getCmp('cmbBillingProjectB201').setValue(wms[0].data.billingProject);
			Ext.getCmp('cmbBillingUnitB201').setValue(wms[0].data.billingUnit);
			Ext.getCmp('txtRemarkB201').setValue(wms[0].data.remark);
			var wheresql=
				{
						strBillingProject:wms[0].data.billingProject,
						strBillingUnit:wms[0].data.billingUnit
						/*strBillingProject:Ext.getCmp('cmbBillingProjectB201').getValue(),
						strBillingUnit:Ext.getCmp('cmbBillingUnitB201').getValue()*/
				};
				Ext.apply(Ext.getCmp('cmbValueFlagB201').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('cmbValueFlagB201').getStore().removeAll();
				Ext.getCmp('cmbValueFlagB201').setValue(null);
				Ext.getCmp('cmbValueFlagB201').getStore().load();
				Ext.getCmp('cmbValueFlagB201').setValue(wms[0].data.ruleId);
		}
	}
});
function loadbdef_WmsWarehouseBaseB201(cmbCombo,wheresql){
	Ext.apply(Ext.getCmp(cmbCombo).getStore().proxy.extraParams,wheresql);
	Ext.getCmp(cmbCombo).getStore().removeAll();
	Ext.getCmp(cmbCombo).getStore().load();
}
//添加数据
function addbset_ValuePolicySetB201(){
	if(Ext.getCmp('IdFormB201').getForm().isValid()){
		var wms={
			id:{
				warehouseNo:Ext.get('warehouseNo').getValue(),
		    	ownerNo:Ext.getCmp('cmbOwnerB201_2').getValue(),
		    	billingProject:Ext.getCmp('cmbBillingProjectB201').getValue()
			},
		    billingUnit:Ext.getCmp('cmbBillingUnitB201').getValue(),
			ruleId:Ext.getCmp('cmbValueFlagB201').getValue(),
			remark:Ext.getCmp('txtRemarkB201').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(wms);
		Ext.Ajax.request({
			url:'bset_ValuePolicySetAction_saveOrUpdateValuePolicySet.action',
			method:'post',
			params:{
				strBsetValuePolicySet:str,
				strWarehouseNo:Ext.get('warehouseNo').getValue(),
		    	strOwnerNo:Ext.getCmp('cmbOwnerB201_2').getValue(),
		    	strBillingProject:Ext.getCmp('cmbBillingProjectB201').getValue()
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_B201').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				};
			}
		});
	};
};

