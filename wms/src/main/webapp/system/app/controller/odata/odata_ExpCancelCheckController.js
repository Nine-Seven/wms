/**
 * 模块名称：病单审核
 * 模块编码：3918
 * 创建：hekangli
 */
var g_strIisCanEdit3918=false;
Ext.define('cms.controller.odata.odata_ExpCancelCheckController',{
	extend:'Ext.app.Controller',
	controllerId:'odata_ExpCancelCheckController',
	requires:[
          'cms.view.odata.odata_ExpCancelCheckUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//病单审核》货主选择
			'odata_ExpCancelCheckUI bdef_DefOwnerCombo[id=cmbOwnerNo3918]':{
				beforeselect:this.cmbOwnerNo3918beforeselect,
				select:this.cmbOwnerNo3918Select
			},
			//病单审核》刷新
			'odata_ExpCancelCheckUI button[name=refresh]':{
				click:this.TabpanelChange
			},
			//病单审核》处理方式选择
			'odata_ExpCancelCheckUI combo[id=cmbChuli3918]':{
				//select:this.cmbCancelNo3918change
			},
			//病单审核》单据日期选择
			'odata_ExpCancelCheckUI datefield[id=requestDate3918]':{
				select:this.cmbCancelNo3918change
			},
			//病单审核》病单单号选中
			'odata_ExpCancelCheckUI combo[id=cmbCancelNo3918]':{
				select:this.cmbCancelNo3918change
			},
			//病单审核》单据选择
			'odata_ExpCancelCheckUI grid[id=gridCancelCheckM3918]':{
				beforeselect:this.gridCancelCheckM3918Beforeselect,
				selectionchange:this.gridCancelCheckM3918selectionchange
			},
			//病单审核》审核
			'odata_ExpCancelCheckUI button[name=userSendButton]':{
				click:this.butSend3918click
			},
			//病单审核》关闭前事件
			'odata_ExpCancelCheckUI':{
				beforeclose:this.odata_ExpCancelCheckUIBeforeclose
			}
		});	
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		g_strIisCanEdit3918=false;
		Ext.getCmp('cmbOwnerNo3918').setValue(null);
		Ext.getCmp('cmbOwnerNo3918').getStore().load();
		
		//显示变量0为不显示，1为显示
		var planBox_3918=commonGetModuleField('3918','planBox')[0].flag;	//计划
		var planQmin_3918=commonGetModuleField('3918','planQmin')[0].flag;
		var planDis_3918=commonGetModuleField('3918','planDis')[0].flag;
		var packingUnit_3918=commonGetModuleField('3918','packingUnit')[0].flag;
		var packingSpec_3918=commonGetModuleField('3918','packingSpec')[0].flag;
		
		var realBox_3918=commonGetModuleField('3918','realBox')[0].flag;	//实际
		var realQmin_3918=commonGetModuleField('3918','realQmin')[0].flag;
		var realDis_3918=commonGetModuleField('3918','realDis')[0].flag;
		
		var differenceBox_3918=commonGetModuleField('3918','differenceBox')[0].flag;	//差异
		var differenceQmin_3918=commonGetModuleField('3918','differenceQmin')[0].flag;
		var differenceDis_3918=commonGetModuleField('3918','differenceDis')[0].flag;
		
		if(planBox_3918==0){
			Ext.getCmp('planBox_3918').setVisible(false);
		}
		if(planQmin_3918==0){
			Ext.getCmp('planQmin_3918').setVisible(false);
		}
		if(planDis_3918==0){
			Ext.getCmp('planDis_3918').setVisible(false);
		}
		if(packingUnit_3918==0){
			Ext.getCmp('packingUnit_3918').setVisible(false);
		}
		if(packingSpec_3918==0){
			Ext.getCmp('packingSpec_3918').setVisible(false);
		}
		
		if(realBox_3918==0){
			Ext.getCmp('realBox_3918').setVisible(false);
		}
		if(realQmin_3918==0){
			Ext.getCmp('realQmin_3918').setVisible(false);
		}
		if(realDis_3918==0){
			Ext.getCmp('realDis_3918').setVisible(false);
		}
		
		if(differenceBox_3918==0){
			Ext.getCmp('differenceBox_3918').setVisible(false);
		}
		if(differenceQmin_3918==0){
			Ext.getCmp('differenceQmin_3918').setVisible(false);
		}
		if(differenceDis_3918==0){
			Ext.getCmp('differenceDis_3918').setVisible(false);
		}
		
	},
	
	//刷新
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		Ext.getCmp('cmbOwnerNo3918').setValue('');
		Ext.getCmp('cmbChuli3918').setValue(null);
		//Ext.getCmp('cmbChuli3918').getStore().removeAll();
		Ext.getCmp('requestDate3918').setValue(null);
		Ext.getCmp('cmbCancelNo3918').setValue(null);
		//Ext.getCmp('cmbCancelNo3918').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3918').getStore().load();
	},
	
	//病单审核》货主选择
	cmbOwnerNo3918Select:function()
    {
		var listDetail   =  [];
		Ext.getCmp('cmbCancelNo3918').setValue(null);
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3918').getValue()))
		{
			var strDtl  =  {
				    columnId:'oom.owner_no',
					value:Ext.getCmp('cmbOwnerNo3918').getValue()
				};
				listDetail.push(strDtl);
				var strJson  =  Ext.encode(listDetail);
				var strWheresql  =  {
					str : strJson
				};
				Ext.apply(Ext.getCmp('cmbCancelNo3918')
    					.getStore().proxy.extraParams,
    					strWheresql);
    			Ext.getCmp('cmbCancelNo3918').getStore()
    					.removeAll();
    			Ext.getCmp('cmbCancelNo3918').getStore()
    					.load();
    			loadgridCancelM3918();
		}else
		{
			return;
		}
    },
	
	
	//刷新
	refreshClick:function(){
		Ext.getCmp('cmbOwnerNo3918').setValue(null);
		Ext.getCmp('cmbOwnerNo3918').getStore().reload();
	},
	
	//使网格不可编辑
	cmbOwnerNo3918beforeselect:function(){
	    if(g_strIisCanEdit3918)
	    {
	        return  false;  
	    }
	},
	
	
	//病单审核》病单单号选中、处理方式选择、单据日期选择
	cmbCancelNo3918change:function()
	{
		loadgridCancelM3918();
	},
	
	
	//病单审核》单据选择前事件
	gridCancelCheckM3918Beforeselect:function()
	{
	    if(g_strIisCanEdit3918)
	    {
	        return  false;  
	    }
	},
	
	//病单审核》单据选择
	gridCancelCheckM3918selectionchange:function()
	{
		var objRecord = Ext.getCmp('gridCancelCheckM3918').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridCancelCheckD3918').getStore().removeAll();
        }else{
			var wheresql = {
				str :objRecord[0].get("cancelNo")
			};
			Ext.apply(Ext.getCmp('gridCancelCheckD3918')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridCancelCheckD3918').getStore()
					.removeAll();
			Ext.getCmp('gridCancelCheckD3918').getStore()
					.load();
        }
	},
	
	
	//审核
	butSend3918click:function()
	{
		var data = Ext.getCmp('gridCancelCheckM3918').getSelectionModel().getSelection();
		if(Ext.isEmpty(Ext.getCmp('cmbChuli3918').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,'请先选择处理方式');
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.Msg.confirm('提示','确定审核？',function(button,text){
					if(button=='yes'){
						Ext.Ajax.request({
							method:'post',
							url:'odata_ExpCancelCheckAction_sendCancel',
							params:{
								ownerNo:Ext.getCmp('cmbOwnerNo3918').getValue(),
								expNo:data[0].data.expNo,
								cancelNo:data[0].data.cancelNo,
								handleflag:Ext.getCmp('cmbChuli3918').getValue()
						    },
						    success:function(response){
						    	var data = Ext.decode(response.responseText);
						    	if(data.isSucc){					
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('gridCancelCheckM3918').getStore().removeAll();
							 	  	Ext.getCmp('gridCancelCheckM3918').getStore().reload();
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg);

								//	Ext.example.msg($i18n.prompt,data.msg);
								}
						    }
						});
					}
				});
			
		}	
		
		
	},
	
	//病单审核》关闭前事件
	odata_ExpCancelCheckUIBeforeclose:function(){
		g_strIisCanEdit3918=false;
		Ext.getCmp('gridCancelCheckD3918').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3918').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3918').setValue(null);
	}
});

//加载单据参数传递
function loadgridCancelM3918()
{
	var ownerNo3918 = Ext.getCmp('cmbOwnerNo3918').getValue();
	var chuli3918= Ext.getCmp('cmbChuli3918').getValue();
	var requestDate3918 = Ext.getCmp('requestDate3918').getValue();
	var listDetail1 = [];
	if(!Ext.isEmpty(ownerNo3918))
	{
		if(!Ext.isEmpty(ownerNo3918))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNo3918').getValue()
			};
			listDetail1.push(strdtl);
		}
		if(!Ext.isEmpty(chuli3918))
		{
			var strdtl={
			columnId:'oom.HANDLE_FLAG',
			value:Ext.getCmp('cmbChuli3918').getValue()
			};
			listDetail1.push(strdtl);
		}
		if(!Ext.isEmpty(requestDate3918))
		{
			var strdtl={
			columnId:"to_char(oom.OPERATE_DATE,'yyyy-mm-dd')",
			value:Ext.Date.format(Ext.getCmp('requestDate3918').getValue(),'Y-m-d')
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbCancelNo3918').getValue()))
		{
			var strdtl={
			columnId:'oom.CANCEL_NO',
			value:Ext.getCmp('cmbCancelNo3918').getValue()
			};
			listDetail1.push(strdtl);
		}
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			strQuery : jsonStr
		};
		Ext.apply(Ext.getCmp('gridCancelCheckM3918')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('gridCancelCheckM3918').getStore()
				.removeAll();
		Ext.getCmp('gridCancelCheckM3918').getStore()
				.load();
	}else
	{
		return;
	}
	
}
