/**模块名称：仓别下参数配置
 * 模块编码：1L01
 * 创建：zhm
 */
var rowindex1L01=0;
var cmbCombo='';
var type1L01='';      //用于判断是否重新加载数据，值为“edit”则加载数据，值为“add”不加载数据                     
Ext.define('cms.controller.bdef.bdef_WmsWarehouseBaseController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_WmsWarehouseBaseUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//新增
			'bdef_WmsWarehouseBaseUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//修改
			'bdef_WmsWarehouseBaseUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//双击
			'bdef_WmsWarehouseBaseUI grid[id=grid_01_1L01]':{
					itemdblclick:this.detailEdit
			},
			//重新加载添加窗口
			'bdef_WmsWarehouseBaseAddorEditWindow button[name=add]':{
				click:this.add
			},
			//关闭窗口
			'bdef_WmsWarehouseBaseAddorEditWindow button[name=close]':{
				click:this.colse
			},
			//保存用户信息
			'bdef_WmsWarehouseBaseAddorEditWindow button[name=save]':{
				click:this.save
			},
			//业务切换
			'bdef_WmsWarehouseBaseUI combo[id=cmbGroupNo1L01]':{
				change:this.groupNoSelect
			},
			'bdef_WmsWarehouseBaseUI combo[id=cmbGroupNo1L01]':{
				change:this.groupNoSelect
			},
			//参数列名
			'bdef_WmsWarehouseBaseUI combo[id=cmbSubGroupNo1L01]':{
				change:this.subGroupNoSelect
			},
			//上一条记录
			'bdef_WmsWarehouseBaseAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_WmsWarehouseBaseAddorEditWindow button[name=next]':{
				click:this.next
			},
			//模块的加载
			'bdef_WmsWarehouseBaseAddorEditWindow combo[id=cmbGroupNo1L01_2]':{
				change:this.groupNoLoad
			},
			//参数名称的加载
			'bdef_WmsWarehouseBaseAddorEditWindow combo[id=txtColum1L01]':{
				change:this.columLoad
			}
		});
	},
	//实现新增功能（清空窗口的内容）	
	add:function(){
		Ext.getCmp('IdForm1L01').getForm().reset();
		bindEnterSkip($('IdForm1L01'));//调用键盘处理方法
		saveType1F01='add';
	},
	//调用新增窗口
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_WmsWarehouseBaseAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		commonSetMsterReadOnlyByArray(
				new Array('txtColum1L01','cmbGroupNo1L01_2','cmbSubGroupNo1L01_2',
					'txtMemo1L01'),false);
		addCommMenu5('bdef_MenuWidget1L01');
		type1L01='add';
	},
	groupNoSelect:function(){
		var wheresql=
		{
				strGroupNo:Ext.getCmp('cmbGroupNo1L01').getValue(),
				strSubGroupNo:''
		};
		cmbCombo='cmbSubGroupNo1L01';
		loadbdef_WmsWarehouseBase1L01(cmbCombo,wheresql);
		cmbCombo='grid_01_1L01';
		Ext.getCmp('grid_01_1L01').getStore().currentPage=1;
		loadbdef_WmsWarehouseBase1L01(cmbCombo,wheresql);		
	},
	subGroupNoSelect:function()
	{
		var wheresql=
		{
				strGroupNo:Ext.getCmp('cmbGroupNo1L01').getValue(),
				strSubGroupNo:Ext.getCmp('cmbSubGroupNo1L01').getValue()
		};
		cmbCombo='grid_01_1L01';
		loadbdef_WmsWarehouseBase1L01(cmbCombo,wheresql);
	},
	//修改
	detailEdit:function(){
		var data = Ext.getCmp('grid_01_1L01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.create('cms.view.bdef.window.bdef_WmsWarehouseBaseAddorEditWindow',{
				title:$i18n.titleupdate
		    }).show();
		    Ext.getCmp('cmbGroupNo1L01_2').disable();
		    Ext.getCmp('cmbSubGroupNo1L01_2').disable();
		    Ext.getCmp('txtColum1L01').disable();
			rowindex1L01=data[0].index;
			loadWmsDefbaseData1L01(rowindex1L01);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1L01','grid_01_1L01',rowindex1L01);
			updateCommMenu5('bdef_MenuWidget1L01');
		}
		type1L01='edit';	
	},
	//关闭
	colse:function(){
		Ext.getCmp('bdef_WmsWarehouseBaseAddorEditWindow').close();
	},
	//上一条数据
	prev:function(){
		rowindex1L01=rowindex1L01-1;
		loadWmsDefbaseData1L01(rowindex1L01);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1L01','grid_01_1L01',rowindex1L01);
	},
	//下一条数据
	next:function(){
		rowindex1L01=rowindex1L01+1;
		loadWmsDefbaseData1L01(rowindex1L01);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1L01','grid_01_1L01',rowindex1L01);
	},
	save:function(){
		if(Ext.getCmp('cmbGroupNo1L01_2').getValue()==null)
		{
			Ext.example.msg('提示','请选择模块！');
			return;
		}else if(Ext.getCmp('cmbSubGroupNo1L01_2').getValue()==null)
		{
			Ext.example.msg('提示','请选择子模块！');
			return;
		}else if(Ext.getCmp('txtColum1L01').getValue()==null)
		{
			Ext.example.msg('提示','请选择参数名称！');
			return;
		}
		addbdef_WmsWarehouseBase1L01();
	},
	groupNoLoad:function(){
		var wheresql=
		{
			strGroupNo:Ext.getCmp('cmbGroupNo1L01_2').getValue()
		};
		Ext.apply(Ext.getCmp('txtColum1L01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('txtColum1L01').getStore().removeAll();
		Ext.getCmp('txtColum1L01').setValue(null);
		Ext.getCmp('txtColum1L01').getStore().load();
		Ext.apply(Ext.getCmp('cmbSubGroupNo1L01_2').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbSubGroupNo1L01_2').getStore().removeAll();
		Ext.getCmp('cmbSubGroupNo1L01_2').setValue(null);
		Ext.getCmp('cmbSubGroupNo1L01_2').getStore().load();
		if(type1L01=='edit'){
			loadWmsDefbaseData1L01(rowindex1L01);
		}
	},
	columLoad:function(){
		var wheresql=
		{
			strColname:Ext.getCmp('txtColum1L01').getValue()
		};
		Ext.apply(Ext.getCmp('cmbSdefine1L01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbSdefine1L01').getStore().removeAll();
		Ext.getCmp('cmbSdefine1L01').getStore().load();
		Ext.Ajax.request({
			url : 'bdef_WmsWarehouseBaseAction_getMemoCombo',
			params : {
				strColname:Ext.getCmp('txtColum1L01').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res){
		    		Ext.getCmp('txtMemo1L01').setValue(res[0]);
		    	}
			}
		});
	}
	

});
function loadbdef_WmsWarehouseBase1L01(cmbCombo,wheresql){
	Ext.apply(Ext.getCmp(cmbCombo).getStore().proxy.extraParams,wheresql);
	Ext.getCmp(cmbCombo).getStore().removeAll();
	Ext.getCmp(cmbCombo).getStore().load();
}
//填充数据
function addbdef_WmsWarehouseBase1L01(){
	if(Ext.getCmp('IdForm1L01').getForm().isValid()){
		var wms={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
		    	colname:Ext.getCmp('txtColum1L01').getValue()
			},
		    groupNo:Ext.getCmp('cmbGroupNo1L01_2').getValue(),
			subGroupNo:Ext.getCmp('cmbSubGroupNo1L01_2').getValue(),
			sdefine:Ext.getCmp('cmbSdefine1L01').getValue(),
			ndefine:Ext.getCmp('txtNdefine1L01').getValue(),
			memo:Ext.getCmp('txtMemo1L01').getValue()
			
		};
		var str=Ext.encode(wms);
		Ext.Ajax.request({
			url:'bdef_WmsWarehouseBaseAction_saveOrUpdateWmsWarehouseBase.action',
			method:'post',
			params:{
				strWarehouseBase:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1L01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};

function loadWmsDefbaseData1L01(rowindex1L01){
	var wms=Ext.getCmp('grid_01_1L01').getStore().
	getAt(rowindex1L01-(Ext.getCmp('grid_01_1L01').
	getStore().currentPage-1)*appConfig.getPageSize());
	/*Ext.getCmp('txtWarehouse1L01').setValue(wms.data.warehouseNo),*/
	Ext.getCmp('txtColum1L01').setValue(wms.data.colname);
	Ext.getCmp('cmbGroupNo1L01_2').setValue(wms.data.groupNo);
	Ext.getCmp('cmbSubGroupNo1L01_2').setValue(wms.data.subGroupNo);
	Ext.getCmp('txtNdefine1L01').setValue(wms.data.ndefine);
	Ext.getCmp('txtMemo1L01').setValue(wms.data.memo);
	
	var wheresql=
	{
		strColname:wms.data.colname
	};
	Ext.apply(Ext.getCmp('cmbSdefine1L01').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('cmbSdefine1L01').getStore().removeAll();
	Ext.getCmp('cmbSdefine1L01').setValue(null);
	Ext.getCmp('cmbSdefine1L01').getStore().load();
	Ext.getCmp('cmbSdefine1L01').setValue(wms.data.sdefine);
};
