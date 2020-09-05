/*
 * 出货日期确认
 * hkl
 * 3922
 */
//定义材积的全局变量，做勾选的增加或者减少
var boxQty= 0;
var volumn=0;
var weight=0;
Ext.define('cms.controller.odata.odata_CustSendDateController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_CustSendDateUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查询
			'odata_CustSendDateUI button[id=btnQuery3922]':
			{
				click:this.btnQuery3922
			},//勾选单据列表
			'odata_CustSendDateUI grid[id=gridOdata_CustSendDate3922]' : {
				select:this.gridOdata_CustSendDate3922Select,//选中事件
				deselect:this.gridOdata_CustSendDate3922Deselect,//取消选中事件
			},//选择确认出货日期
			'odata_CustSendDateUI datefield[id=custsend_Date3922]' : {
				select:this.custsend_Date3922Select
			},//刷新
			'odata_CustSendDateUI button[id=refresh]':{
				click:this.refresh
			},//确认
			'odata_CustSendDateUI button[id=btnConfirm3922]':{
				click:this.btnConfirm3922Click
			}
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('gridOdata_CustSendDate3922').getStore().load();
	},
	/**
	 * 刷新
	 */
	refresh:function(){
		Ext.getCmp('owner_no3922').setValue('');
		Ext.getCmp('exp_type3922').setValue('');
		Ext.getCmp('cust3922').setValue('');
		Ext.getCmp('last_custsend_Date3922').setValue('');
		Ext.getCmp('lblSumBoxQty3922_2').setText('');
		Ext.getCmp('lblSumWeight3922_2').setText('');
		Ext.getCmp('lblSumVolumn3922_2').setText('');
		var strWheresql = {
				str:null,
				strQuery:'1'
	    };
		Ext.apply(Ext.getCmp('gridOdata_CustSendDate3922').getStore().proxy.extraParams,strWheresql);
	  	Ext.getCmp('gridOdata_CustSendDate3922').getStore().removeAll();
		Ext.getCmp('gridOdata_CustSendDate3922').getStore().reload();
		
	  },
	
	btnQuery3922:function(){
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('owner_no3922').getValue())){
			var d={
					columnId:'a.owner_no',
					value:Ext.getCmp('owner_no3922').getValue()
			};
			strDetail1.push(d);
		}
		if(!Ext.isEmpty(Ext.getCmp('exp_type3922').getValue())){
			d={
					columnId:'a.exp_type',
					value:Ext.getCmp('exp_type3922').getValue()
			};
			strDetail1.push(d);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cust3922').getValue())){
			d={
					columnId:'a.cust_no',
					value:Ext.getCmp('cust3922').getValue()
			};
			strDetail1.push(d);
		}
	
		if(!Ext.isEmpty(Ext.getCmp('last_custsend_Date3922').getValue()) && !Ext.isEmpty(Ext.getCmp('select_term3922').getValue())){
			d={
					columnId:'to_char(a.last_custsend_date,\'yyyy/mm/dd\')',
					condition : Ext.getCmp('select_term3922').getValue(),				
					value:Ext.Date.format(Ext.getCmp('last_custsend_Date3922').getValue(),'Y/m/d')
			};
			strDetail1.push(d);
		}
	    var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			str:jsonDetail,
			strQuery:Ext.getCmp('confirmType3922').getValue()
		};
		Ext.apply(Ext.getCmp('gridOdata_CustSendDate3922').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('gridOdata_CustSendDate3922').getStore().removeAll();
		Ext.getCmp('gridOdata_CustSendDate3922').getStore().load();			
			
		
	},
	
	gridOdata_CustSendDate3922Select:function(row,record,index,eOpts){
		boxQty=boxQty+parseInt(record.data.sumBoxQty);
		volumn=volumn+record.data.sumVolumn;
		weight=weight+record.data.sumWeight;
		Ext.getCmp('lblSumBoxQty3922_2').setText(boxQty);
		Ext.getCmp('lblSumVolumn3922_2').setText(volumn);
		Ext.getCmp('lblSumWeight3922_2').setText(weight);
	},
	gridOdata_CustSendDate3922Deselect:function(row,record,index,eOpts){
		boxQty=boxQty-parseInt(record.data.sumBoxQty);
		volumn=volumn-record.data.sumVolumn;
		weight=weight-record.data.sumWeight;
		Ext.getCmp('lblSumBoxQty3922_2').setText(boxQty);
		Ext.getCmp('lblSumVolumn3922_2').setText(volumn);
		Ext.getCmp('lblSumWeight3922_2').setText(weight);
	},
	
	custsend_Date3922Select:function(){
		var strWheresql = {
				str : Ext.Date.format(Ext.getCmp('custsend_Date3922').getValue(),'Y-m-d')
		};
		Ext.Ajax.request({
			url:'odata_CustSendDateAction_queryVolumn',
			method:'post',
			params:{
				str:strWheresql
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('lblSumBoxQty3922_1').setText(parseInt(data.obj.sumBoxQty));
					Ext.getCmp('lblSumVolumn3922_1').setText(data.obj.sumVolumn);
					Ext.getCmp('lblSumWeight3922_1').setText(data.obj.sumWeight);
				}
				else{
					Ext.Msg.alert($i18n.prompt,data.msg);
					//Ext.example.msg($i18n.prompt,data.msg);
				}
				
			}
		});	
	},
	
	btnConfirm3922Click:function(){
		var data = Ext.getCmp('gridOdata_CustSendDate3922').getSelectionModel().getSelection();
		if(Ext.isEmpty(Ext.getCmp('custsend_Date3922').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseCustSendDate);
			return;
		}
		if(data.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseOrder);
			return;
		}
			
    	Ext.Msg.confirm($i18n.prompt,$i18n_prompt.confirmOrNot, function(button, text) 
		{
			if (button == 'yes') 
			{	
				var detail=[];
			    for(var i=0;i<data.length;i++){
					var d={
						
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							ownerNo:data[i].get('ownerNo'),
							sourceexpNo:data[i].get('sourceexpNo'),
							custsendDate:Ext.getCmp('custsend_Date3922').getValue()
					};
				    detail.push(d);
			   }
			   var str=Ext.encode(detail);
			   Ext.Ajax.request({
					method:'POST',
					url:'odata_CustSendDateAction_tscCustSendComfirm.action',
					params:params={
							str:str
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
						  	Ext.getCmp('gridOdata_CustSendDate3922').getStore().removeAll();
							Ext.getCmp('gridOdata_CustSendDate3922').getStore().reload();
							var strWheresql = {
									str : Ext.Date.format(Ext.getCmp('custsend_Date3922').getValue(),'Y-m-d')
							};
							Ext.Ajax.request({
								url:'odata_CustSendDateAction_queryVolumn',
								method:'post',
								params:{
									str:strWheresql
								},
								success:function(response){
									var data=Ext.decode(response.responseText);
									if(data.isSucc){
										Ext.getCmp('lblSumBoxQty3922_1').setText(parseInt(data.obj.sumBoxQty));
										Ext.getCmp('lblSumVolumn3922_1').setText(data.obj.sumVolumn);
										Ext.getCmp('lblSumWeight3922_1').setText(data.obj.sumWeight);
									}
									else{
										Ext.Msg.alert($i18n.prompt,data.msg);
										//Ext.example.msg($i18n.prompt,data.msg);
									}
									
								}
							});	
							Ext.example.msg($i18n.prompt,data.msg);

						}else{
							Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
							//Ext.example.msg($i18n.prompt,data.msg+data.obj);
						}				
					}
				});
			}
		});
	},
});