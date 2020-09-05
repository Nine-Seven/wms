/**
 * 模块名称：系统参数配置
 * 模块编码：1K01
 * 创建：zhm
 */
var rowindex1K01=0;
var saveType1K01=0;
var cmbCombo='';
Ext.define('cms.controller.bdef.bdef_WmsDefbaseController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_WmsDefbaseUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//修改
			'bdef_WmsDefbaseUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//双击
			'bdef_WmsDefbaseUI grid[id=grid_01_1K01]':{
					itemdblclick:this.detailEdit
			},
			
			//关闭窗口
			'bdef_WmsDefbaseAddorEditWindow button[name=close]':{
				click:this.colse
			},
			//保存用户信息
			'bdef_WmsDefbaseAddorEditWindow button[name=save]':{
				click:this.save
			},
			//业务切换
			'bdef_WmsDefbaseUI combo[id=cmbGroupNo1K01]':{
				change:this.groupNoSelect
			},
			'bdef_WmsDefbaseUI combo[id=cmbSubGroupNo1K01]':{
				change:this.subGroupNoSelect
			},
			//上一条记录
			'bdef_WmsDefbaseAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_WmsDefbaseAddorEditWindow button[name=next]':{
				click:this.next
			},
			'bdef_WmsDefbaseAddorEditWindow combo[id=txtGroupNo1K01]':{
				change:this.txtGroupNo1K01
			}
		});
	},
	groupNoSelect:function(){
		var wheresql=
		{
				strGroupNo:Ext.getCmp('cmbGroupNo1K01').getValue(),
				strSubGroupNo:''
		};
		cmbCombo='cmbSubGroupNo1K01';
		loadBdef_WmsDefbase1K01(cmbCombo,wheresql);
		cmbCombo='grid_01_1K01';
		Ext.getCmp('grid_01_1K01').getStore().currentPage=1;
		loadBdef_WmsDefbase1K01(cmbCombo,wheresql);		
	},
	subGroupNoSelect:function()
	{
		var wheresql=
		{
				strGroupNo:Ext.getCmp('cmbGroupNo1K01').getValue(),
				strSubGroupNo:Ext.getCmp('cmbSubGroupNo1K01').getValue()
		};
		cmbCombo='grid_01_1K01';
		loadBdef_WmsDefbase1K01(cmbCombo,wheresql);
		
		

	},
	
	detailEdit:function(){
		var data = Ext.getCmp('grid_01_1K01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.create('cms.view.bdef.window.bdef_WmsDefbaseAddorEditWindow',{
				title:$i18n.titleupdate//新增
		    }).show();
		    Ext.getCmp('txtGroupNo1K01').disable();
		    Ext.getCmp('txtSubGroupNo1K01').disable();
		    Ext.getCmp('txtColum1K01').disable();
		    Ext.getCmp('txtUserLevel1K01').disable();
			rowindex1K01=data[0].index;
			loadWmsDefbaseData1K01(rowindex1K01);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1K01','grid_01_1K01',rowindex1K01);
			updateCommMenu5('bdef_MenuWidget1K01');
			var wheresql=
			{
				colname:Ext.getCmp('txtColum1K01').getValue()
			};
		}
	},
	colse:function(){
		Ext.getCmp('bdef_WmsDefbaseAddorEditWindow').close();
	},
	prev:function(){
		rowindex1K01=rowindex1K01-1;
		loadWmsDefbaseData1K01(rowindex1K01);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1K01','grid_01_1K01',rowindex1K01);
	},
	
	next:function(){
		rowindex1K01=rowindex1K01+1;
		loadWmsDefbaseData1K01(rowindex1K01);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1K01','grid_01_1K01',rowindex1K01);
	},
	save:function(){
		addbdef_WmsDefbase1K01();
	},
	
	txtGroupNo1K01:function(){
		var wheresql=
		{
			strGroupNo:Ext.getCmp('txtGroupNo1K01').getValue()
		};
		Ext.apply(Ext.getCmp('txtSubGroupNo1K01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('txtSubGroupNo1K01').getStore().removeAll();
		Ext.getCmp('txtSubGroupNo1K01').setValue(null);
		Ext.getCmp('txtSubGroupNo1K01').getStore().load();
		loadWmsDefbaseData1K01(rowindex1K01);
	}
	
	

});
function loadBdef_WmsDefbase1K01(cmbCombo,wheresql){
	Ext.apply(Ext.getCmp(cmbCombo).getStore().proxy.extraParams,wheresql);
	Ext.getCmp(cmbCombo).getStore().removeAll();
	Ext.getCmp(cmbCombo).getStore().load();
}
//填充数据
function addbdef_WmsDefbase1K01(){
	if(Ext.getCmp('IdForm1K01').getForm().isValid()){
		var wms={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				colname:Ext.getCmp('txtColum1K01').getValue()
			},	
		    
		    groupNo:Ext.getCmp('txtGroupNo1K01').getValue(),
			subGroupNo:Ext.getCmp('txtSubGroupNo1K01').getValue(),
			useLevel:Ext.getCmp('txtUserLevel1K01').getValue(),
			sdefine:Ext.getCmp('cmbSdefine1K01').getValue(),
			ndefine:Ext.getCmp('txtNdefine1K01').getValue(),
			memo:Ext.getCmp('txtMemo1K01').getValue()
			
		};
		var str=Ext.encode(wms);
		Ext.Ajax.request({
			url:'bdef_WmsDefbaseAction_saveOrUpdateWmsDefbase.action',
			method:'post',
			params:{
				strWmsDefbase:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1K01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};

function loadWmsDefbaseData1K01(rowindex1K01){
	var wms=Ext.getCmp('grid_01_1K01').getStore().
	getAt(rowindex1K01-(Ext.getCmp('grid_01_1K01').
	getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('txtColum1K01').setValue(wms.data.colname);
	Ext.getCmp('txtGroupNo1K01').setValue(wms.data.groupNo);
	Ext.getCmp('txtSubGroupNo1K01').setValue(wms.data.subGroupNo);
	Ext.getCmp('txtUserLevel1K01').setValue(wms.data.useLevel);
	Ext.getCmp('txtNdefine1K01').setValue(wms.data.ndefine);
	Ext.getCmp('txtMemo1K01').setValue(wms.data.memo);
	
	var wheresql=
	{
		colname:wms.data.colname
	};
	Ext.apply(Ext.getCmp('cmbSdefine1K01').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('cmbSdefine1K01').getStore().removeAll();
	Ext.getCmp('cmbSdefine1K01').setValue(null);
	Ext.getCmp('cmbSdefine1K01').getStore().load();
	Ext.getCmp('cmbSdefine1K01').setValue(wms.data.sdefine);
};
