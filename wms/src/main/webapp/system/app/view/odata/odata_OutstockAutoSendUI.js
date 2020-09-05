/**
 * 模块名称：自动发单
 * 模块编码：3930
 * 创建：czh
 */
var store=Ext.create('cms.store.odata.odata_OutstockM_GetBatchSendOrderStore');
Ext.define('cms.view.odata.odata_OutstockAutoSendUI',{
	alias:'widget.odata_OutstockAutoSendUI',
	title:$i18n.autoSendOrder,//自动发单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu4',
	'cms.view.common.wms_DefFieldValCombo',
	'cms.view.common.bdef_DefWorkerCombo',
	'cms.view.common.bdef_DefOwnerCombo'],
	items:[
{
		xtype : 'commMenuWidget4',
		id:'menu3930',
		region:'north'
	},
{
    xtype:'form',
    id:'form3930',
    region:'north',
    height:'25%',
	frame : true,
    layout:{
			type:'table',
			columns:4
    },
    defaults:{
    	labelwidth:80,
    	margin:'0 15 12 15',
    	labelAlign:'right'
    },
    items:[
   			{
				xtype:'bdef_DefOwnerCombo',
				fieldLabel:'货主编号',//货主编号
				queryMode:'local',
			    allowBlank:true,
			    id:'cmbOwnerNoSend3930',
			    displayField: 'dropValue',
				valueField: 'value',
			    beforeLabelTextTpl:required,
			    store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
						 {
							 proxy:{
									type:'ajax',
									method:'post',
									url:'odata_OutstockMAction_getOdataGetCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "1",
										strSendFalg : "auto",
										industryFlag : "1"
									}
								},	 
							listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											//Ext.getCmp('gridOutstockInfo3930').getStore().removeAll();
											Ext.getCmp('cmbOwnerNoSend3930').setValue(th.getAt(0).data.value);
										}
									}
								}
							})
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel : $i18n.exp_type,// 出货单别
				id : 'cmbExp_typeSend3930',
				displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
				beforeLabelTextTpl : required,
				store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_OutstockMAction_getOdataGetCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									strFlag : "2",
									industryFlag : "1"
								}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbExp_typeSend3930').setValue(th.getAt(0).data.value);
								}
							}
							}
		   				}),
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel : '波次号',// 波次号
				id : 'cmbWaveNoSend3930',
				displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
				colspan:2,
				beforeLabelTextTpl : required,
				store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_OutstockMAction_getOdataGetCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									strFlag : "3",
									industryFlag : "1"
								}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbWaveNoSend3930').setValue(th.getAt(0).data.value);
								}
							}
							}
		   				}),
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel : '批次号',// 批次号
				id : 'cmbBatchNoSend3930',
				displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
				store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_OutstockMAction_getOdataGetCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									strFlag : "8",
									industryFlag : "1"
								}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbBatchNoSend3930').setValue(th.getAt(0).data.value);
								}
							}
							}
		   				}),
			},{
				xtype : 'bdef_DefWorkerCombo',
				fieldLabel : $i18n.dispatch_worker,//发单人员
				id : 'cmbWorkerNo3930',
				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
				beforeLabelTextTpl : required,
			},{
				xtype : 'numberfield',
				minValue:0,
				value:2,
				fieldLabel : '间隔时间(min)',//间隔时间
				id:'intervalTime3930',
				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
				beforeLabelTextTpl : required,
				colspan:2
			},{
				xtype : 'checkboxfield',
				id : 'checkBoxPrint_type3930',
				boxLabel : $i18n.print_report,//打印报表
				margin : '0 0 10 70',
			
			}/*,    //8-4屏蔽
			{
				xtype : 'checkboxfield',
				id : 'printFaceSheet3930',
				boxLabel : '打印面单',//打印面单
			    margin : '0 0 10 -130',
			},
			{
				xtype : 'checkboxfield',
				id : 'printBuilt3930',
				margin : '0 0 10 -330',
				boxLabel : '打印内置清单'//打印内置清单
			},
			{
				xtype : 'checkboxfield',
				id : 'printInvoice3930',
				margin : '0 0 10 -500',
				boxLabel : '打印发票'//打印发票
			}*/,{
				xtype : 'button',
				margin : '3 3 3 68',
				id:'butStart3930',
				listeners:{
					"click": function() {											
						if(Ext.getCmp('cmbOwnerNoSend3930').getValue()==null){
	                    	Ext.example.msg('提示','请先选择货主');
	                    	return;
	                    }if(Ext.getCmp('cmbExp_typeSend3930').getValue()==null){
	                    	Ext.example.msg('提示','请先选择出货单别');
	                    	return;
	                    }if(Ext.getCmp('cmbWaveNoSend3930').getValue()==null){
	                    	Ext.example.msg('提示','请先选择波次号');
	                    	return;
	                    }
	                    if(Ext.getCmp('cmbWorkerNo3930').getValue()==null){
	                    	Ext.example.msg('提示','请输入发单人员');
	                    	return;
	                    }if(Ext.getCmp('intervalTime3930').getValue()==null){
	                    	Ext.example.msg('提示','请输入间隔时间');
	                    	return;
	                    }if(Ext.isEmpty(workSpaceNo))
	        			{
	        				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
	        				return;
	        			}
	                    //发单之前锁定条件
	                    Ext.getCmp('butStart3930').setDisabled(true);
	                    Ext.getCmp('butEnd3930').setDisabled(false);
						commonSetMsterReadOnlyByArray(
								new Array('cmbOwnerNoSend3930'),true);
						commonSetMsterReadOnlyByArray(
								new Array('cmbExp_typeSend3930'),true);
						commonSetMsterReadOnlyByArray(
								new Array('cmbWaveNoSend3930'),true);
						commonSetMsterReadOnlyByArray(
								new Array('cmbBatchNoSend3930'),true);
						commonSetMsterReadOnlyByArray(
								new Array('cmbWorkerNo3930'),true);
						commonSetMsterReadOnlyByArray(
								new Array('intervalTime3930'),true);
						commonSetMsterReadOnlyByArray(
								new Array('checkBoxPrint_type3930'),true);
						/*commonSetMsterReadOnlyByArray(
								new Array('printFaceSheet3930'),true);*/
						/*commonSetMsterReadOnlyByArray(
								new Array('printBuilt3930'),true);*/
						/*commonSetMsterReadOnlyByArray(
								new Array('printInvoice3930'),true);*/
				    var listDetail = [];
				    if(Ext.getCmp('cmbOwnerNoSend3930').getValue()!='ALL'){
					    var strdt={
			    				columnId:'ood.owner_no',
			    				value:Ext.getCmp('cmbOwnerNoSend3930').getValue()
			    			};
					    listDetail.push(strdt);
				    }
				    var strdtl={
		    				columnId:'ood.exp_type',
		    				value:Ext.getCmp('cmbExp_typeSend3930').getValue()
		    			};
				    listDetail.push(strdtl);
				    if(Ext.getCmp('cmbWaveNoSend3930').getValue()!='ALL'){
				    	var strdt2={
			    				columnId:'ood.wave_no',
			    				value:Ext.getCmp('cmbWaveNoSend3930').getValue()
			    			};
				    	listDetail.push(strdt2);
				    	if(Ext.getCmp('cmbBatchNoSend3930').getValue()!='ALL'){
					    	var strdt3={
				    				columnId:'ood.batch_no',
				    				value:Ext.getCmp('cmbBatchNoSend3930').getValue()
				    			};		    	       		    	       
				    	       listDetail.push(strdt3);
					    }
				    }
		    			var strJson = Ext.encode(listDetail);
		    			var strWheresql = {
		    				str : strJson
		    			};		    				    			
		    		Ext.Ajax.request({
						method:'POST',
						url:'odata_OutstockMAction_getBatchSendOrder',
						params:strWheresql,
						async:false,
						success:function(response)
						{
							var data = Ext.decode(response.responseText);
							if(data.length==0){
								var GPSInfoModel = [{ 
								    'rsvBatch8' : '['+CurentTime()+']    '+'暂无符合条件的单据',  
								   }];
								Ext.getCmp('gridOutstockInfo3930').getStore().add(GPSInfoModel);
								return;
							}
							for(var i=0;i<data.length;i++){																
								var obj=data[i];
								var detail = [];
								var d={
									warehouseNo:Ext.get('warehouseNo').getValue(),
									ownerNo:Ext.getCmp('cmbOwnerNoSend3930').getValue(),
									expType:Ext.getCmp('cmbExp_typeSend3930').getValue(),
									sourceType:obj.sourceType,
									waveNo:obj.waveNo,
									batchNo:obj.batchNo,
									wareaNo:obj.wareaNo==''?'ALL':obj.wareaNo,
									areaNo:obj.areaNo,
									dockNo:workSpaceNo,
									operateType:obj.operateType,
									workerNo:Ext.get('workerNo').getValue(),
									outStockType:obj.outstockType,
									printPaperType:Ext.getCmp('checkBoxPrint_type3930').getValue()==false ? 0:1,
									//printFaceSheet:Ext.getCmp('printFaceSheet3930').getValue()==false ? 0:1,
									//printBuilt:Ext.getCmp('printBuilt3930').getValue()==false ? 0:1,
									//printInvoice:Ext.getCmp('printInvoice3930').getValue()==false ? 0:1	
									printFaceSheet:0,
									printBuilt:0,
									printInvoice:0
								};
								detail.push(d);
								var strJsonDetail = Ext.encode(detail);
								var params = 
								{
									strJsonDetail:strJsonDetail
								};
								var GPSInfoModel = [{ 
									'rsvBatch8' : '['+CurentTime()+']    '+'波次号'+'['+obj.waveNo+']'+'批次号为'+'['+obj.batchNo+']'+'正在发单...',  
								   }];
								Ext.getCmp('gridOutstockInfo3930').getStore().add(GPSInfoModel);
								Ext.Ajax.request({
									method:'POST',
									url:'odata_OutstockMAction_sendOrderAuto',
									params:params,
									async:false,
									success:function(response)
									{
										var data = Ext.decode(response.responseText);
										if(data.isSucc)
										{
											var GPSInfoModel = [{ 
												'rsvBatch8' : '['+CurentTime()+']    '+'波次号'+'['+obj.waveNo+']'+'批次号为'+'['+obj.batchNo+']'+data.msg,  
											   }];
											Ext.getCmp('gridOutstockInfo3930').getStore().add(GPSInfoModel);
											
										}else
										{
											clearInterval(Intervaltimer);
											var GPSInfoModel = [{ 
											    'rsvBatch8' : '['+CurentTime()+']    '+'波次号'+'['+obj.waveNo+']'+'批次号为'+'['+obj.batchNo+']'+'发单失败'+data.msg,  
											   }];
											Ext.getCmp('gridOutstockInfo3930').getStore().add(GPSInfoModel);											
											
										}				
									},
									failure:function(response){
										Ext.getCmp('butStart3930').setDisabled(false);
										Ext.example.msg('提示',"提交不上,可能是网络问题");
									}
								});
							}
							Intervaltimer = setInterval(time,Ext.getCmp('intervalTime3930').getValue()*1000*60);
							
						}
					});	
		    		}
				},
				text : '开始',//开始
			},{
				xtype : 'button',
				disabled:true,
				margin : '3 3 3 -140',
				listeners:{
					"click": function() {
						clearInterval(Intervaltimer);
						Ext.getCmp('butStart3930').setDisabled(false);
						Ext.getCmp('butEnd3930').setDisabled(true);
						commonSetMsterReadOnlyByArray(
								new Array('cmbOwnerNoSend3930'),false);
						commonSetMsterReadOnlyByArray(
								new Array('cmbExp_typeSend3930'),false);
						commonSetMsterReadOnlyByArray(
								new Array('cmbWaveNoSend3930'),false);
						commonSetMsterReadOnlyByArray(
								new Array('cmbBatchNoSend3930'),false);
						commonSetMsterReadOnlyByArray(
								new Array('cmbWorkerNo3930'),false);
						commonSetMsterReadOnlyByArray(
								new Array('intervalTime3930'),false);
						commonSetMsterReadOnlyByArray(
								new Array('checkBoxPrint_type3930'),false);
						/*commonSetMsterReadOnlyByArray(
								new Array('printFaceSheet3930'),false);*/
						/*commonSetMsterReadOnlyByArray(
								new Array('printBuilt3930'),false);*/
						/*commonSetMsterReadOnlyByArray(
								new Array('printInvoice3930'),false);*/
						Ext.getCmp('cmbOwnerNoSend3930').getStore().load();
						Ext.getCmp('cmbExp_typeSend3930').getStore().load();
						Ext.getCmp('cmbWaveNoSend3930').getStore().load();						
						Ext.getCmp('cmbBatchNoSend3930').getStore().load();
					}
				},
				id:'butEnd3930',
				text : '停止'//停止
			}]
},{
	xtype : 'grid',
	region : 'south',
	id:'gridOutstockInfo3930',
	store: store,
	height:'70%',
	columns : [ 
	{
		xtype : 'rownumberer',
		width : 30
	}, 
	{
		width : '100%',
		text : '发单反馈信息',//发单反馈信息
		dataIndex:'rsvBatch8'
	}
    ]
}
]
});

var time = function timer(){
	Ext.getCmp('butStart3930').fireEvent("click");	
};
var Intervaltimer;
//获取当前时间
function CurentTime()
{ 
    var now = new Date();
    
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒
    
    var clock = year + "-";
    
    if(month < 10)
        clock += "0";
    
    clock += month + "-";
    
    if(day < 10)
        clock += "0";
        
    clock += day + " ";
    
    if(hh < 10)
        clock += "0";
        
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm + ":"; 
     
    if (ss < 10) clock += '0'; 
    clock += ss; 
    return(clock); 
}