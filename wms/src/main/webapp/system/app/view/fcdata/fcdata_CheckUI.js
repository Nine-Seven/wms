/**
 * 模块名称：盘点发单
 * 模块编码：8201
 * 创建：周欢
 */
//var checkDStore=Ext.create('cms.store.fcdata.fcdata_CheckDStore');
Ext.define('cms.view.fcdata.fcdata_CheckUI',{
	alias:'widget.fcdata_CheckUI',
	title:$i18n.title8201,//盘点发单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu3',
	          'cms.view.common.commMenu4',
	          'cms.view.common.commMenu6',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[{
			 xtype:'commMenuWidget4',
			 id:'menu8201',
			 region:'north'
		 },
       		{
	   		   xtype : 'tabpanel',
	   		   id:'tabPId8201',
			   region:'center',
			   flex : 5,
			   items : [
			   {
			     title : $i18n.singleconditions,//成单条件
			     width:'100%',
			     height:'100%',
			     layout:'border',
			     itemId:'itemPIdd1_8201i',
			     items:[{
					xtype : 'form',
					region : 'north',
					id:'conditionForm8201',
					layout:'column',
					border:false,
					width:'100%',
					frame : true,
					layout : {
						type : 'table',
						columns : 4
					},
					defaults : {
						labelWidth : 90,
						margin : '2 2 2 2',
						labelAlign : 'right'
					},
					items : [
						{
							fieldLabel:$i18n.plan_type,//盘点类别
							id:'cmbFcdataType8201cut',
							xtype:'wms_DefFieldValCombo',
					        store:Ext.create("cms.store.common.comboStore",{
					        	proxy:{
									type:'ajax',
									method:'post',
									url:'fcdata_CheckAction_getFcdataTypeCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "1"
									}
								},	
					        	listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbFcdataType8201cut').setValue(th.getAt(0).data.value);
										}
									}
								}
					        }),
					        allowBlank : false,
					        beforeLabelTextTpl : required
					    },{
						xtype : 'combo',
						fieldLabel : $i18n.plan_no,//计划单号
						id:'cmbPlan_no8201cut',
						queryMode : 'local',
						displayField: 'dropValue',
						valueField: 'value',
						store:Ext.create("cms.store.common.comboStore",{
								proxy:{
									type:'ajax',
									method:'post',
									url:'fcdata_CheckAction_getPlanNoCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "1"
									}
								},						
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbPlan_no8201cut').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.fcdata.fcdata_CheckController')
											.cmbPlan_no8201cutSelect(Ext.getCmp('cmbPlan_no8201cut'));
										}else{
											Ext.getCmp('cmbPlan_no8201cut').setValue("");
										}
									}
								}
						}),
				   		beforeLabelTextTpl : required
						},{
							xtype : 'radiogroup',
							id : 'radioCutFlag8101',
							fieldLabel : $i18n.cutFlag,//"切单方式",
							width:320,
							columns : 2,
							items : [{
								boxLabel : $i18n.cutByStockNo,//按通道切单
								name : 'cf',
								inputValue : '1'
								},
								{
								boxLabel : $i18n.cutByStockNoAndX,//按通道+层切单
								name : 'cf',
								inputValue : '2',
								width:130,
								checked:true
								}
							],
							beforeLabelTextTpl : required
						},
						{
							xtype : 'radiogroup',
							id : 'strMove_type8101',
							hidden:true,
							fieldLabel : $i18n.move_type,//"盘点动线",
							width : 200,
							columns : 2,
							items : [{
								boxLabel : $i18n.U_type,//U型
								name : 'mt',
								inputValue : '1',
							    checked:true
								},
								{
								boxLabel : $i18n.W_type,//w型
								name : 'mt',
								inputValue : '2'
								}
							],
							beforeLabelTextTpl : required
						},{
							xtype : 'button',
							margin:'2 0 0 15',
							id:'cut8201',
							text : '定位切单' 
						},{
							xtype : 'datefield',
							fieldLabel : $i18n.begin_date,//日期范围
							id : 'date_scope8201',
							format : 'Y-m-d',
							beforeLabelTextTpl : required
								
						}]
				  },
				  {//盘点发单》成单条件》盘点定位指示切单
				    region:'center',
				    xtype:'grid',
				    width:'100%',
				    id:'gridFcdataCheckDirect8201cut_cell',
				    store:Ext.create('cms.store.fcdata.fcdata_CheckDirectStore',{
				    	proxy:{
							type:'ajax',
							method:'post',
							url:'fcdata_CheckAction_getCheckDirect.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "1"
							}
						}
				    }),
				    multiSelect: true, 
					selModel: {  
					    selType:'checkboxmodel'  
					}, 
				    columns:[
				    {
				      width:150,
				      text:$i18n.request_on,//需求单号
				      dataIndex:'requestNo'
				     },
				     {
				       width:80,
				       text:$i18n.ware_no,//库区
				       dataIndex:'wareNo'
				     },
				     {
				       width:120,
				       text:$i18n.ware_name,//库区名称
				       dataIndex:'wareName'
				     },
				     {
				       width:80,
				       text:$i18n.area_no,//储区
				       dataIndex:'areaNo'
				     },
				     {
				       width:100,
				       text:$i18n.area_name,//储区名称
				       dataIndex:'areaName'
				     }]
				 },{//盘点发单》成单条件》盘点定位指示切单
				    region:'center',
				    xtype:'grid',
				    width:'100%',
				    id:'gridFcdataCheckDirect8201cut_article',
				    store:Ext.create('cms.store.fcdata.fcdata_CheckDirectStore',{
				    	proxy:{
							type:'ajax',
							method:'post',
							url:'fcdata_CheckAction_getCheckDirect.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "3"
							}
						}
				    }),
				    multiSelect: true, 
					selModel: {  
					    selType:'checkboxmodel'  
					}, 
				    columns:[
				    {
				      width:150,
				      text:$i18n.request_on,//需求单号
				      dataIndex:'requestNo'
				     }]
				 }]
				},
				{
			       title : $i18n.handout,//派单
				   width:'100%',
				   height:'100%',
				   layout:'border',
				   itemId:'tabPIdd2_8201i',
				   items:[
				   {
					 xtype : 'form',
					 id:'formgridFcdataCheckDirect8201sendForm',
					 region : 'north',
					 layout : {
						 type : 'table',
						 columns : 4
					 },
 					 defaults : {
						labelWidth : 90,
						margin : '2 2 2 2',
						labelAlign : 'right'
					 },
					 frame : true,
					 items : [
				        {
						fieldLabel:$i18n.plan_type,//盘点类别
						id:'cmbFcdataType8201send',
						xtype:'wms_DefFieldValCombo',
					    store:Ext.create("cms.store.common.comboStore",{
				        	proxy:{
								type:'ajax',
								method:'post',
								url:'fcdata_CheckAction_getFcdataTypeCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									strFlag : "2"
								}
							},	
				        	listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbFcdataType8201send').setValue(th.getAt(0).data.value);
									}
								}
							}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
					    },{
						xtype : 'combo',
						fieldLabel : $i18n.plan_no,// 计划单号
						queryMode : 'local',
						id:'cmbPlan_no8201send',
						displayField: 'dropValue',
	    				valueField: 'value',
						store:Ext.create("cms.store.common.comboStore",{
								proxy:{
									type:'ajax',
									method:'post',
									url:'fcdata_CheckAction_getPlanNoCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "2"
									}
								},						
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbPlan_no8201send').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.fcdata.fcdata_CheckController')
											.cmbPlan_no8201sendSelect(Ext.getCmp('cmbPlan_no8201send'));
										}else{
											Ext.getCmp('cmbPlan_no8201send').setValue("");
										}
									}
								}
							}),
							width : 300,
							beforeLabelTextTpl : required
						 },{
							xtype : 'radiogroup',
							id : 'radioPrint_type8201',
							fieldLabel : $i18n.print_type,//"打印类型",
							columns : 3,
							width : 320,
							items : [{
								boxLabel : $i18n.print_report,//打印报表
								name : 'pt',
								inputValue : '1'
								},
								{
								boxLabel : $i18n.print_label,//打印标签
								name : 'pt',
								inputValue : '2',
								checked:true
								},
								{
									boxLabel : '不打印',//不打印
									name : 'pt',
									inputValue : '0'
								}
							],
							beforeLabelTextTpl : required
						}]
					 },
					 {
						xtype : 'form',
						region : 'north',
						layout : {
						type : 'table',
						columns : 3
						},
						frame : true,
						items : [ {
						layout : 'column',
						xtype : 'container',
						defaults : {
						labelWidth : 90,
						margin : '2 2 2 2',
						labelAlign : 'right',
						width : 300
						},
						items : [
						{
							xtype : 'bdef_DefWorkerCombo',
							fieldLabel : $i18n.yworker_name,// 员工姓名
							id:'cmbWorkerNo8201',
							labelAlign : 'right',
							store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
							beforeLabelTextTpl : required
						}]
						}, 
						{
							xtype : 'button',
							margin:'0 0 0 25',
							id:'butSend_8201',
							text : $i18n.releases //发单
						}]
						},
						{//盘点发单》作业预览》明细档
						  xtype : 'grid',
						  region : 'center',
						  width : '100%',
						  height:200,
						  id:'gridFcdataCheckDirect8201send',
						  store:Ext.create('cms.store.fcdata.fcdata_CheckDirectStore',{
						    	proxy:{
									type:'ajax',
									method:'post',
									url:'fcdata_CheckAction_getCheckDirect.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "2"
									}
								}
						  }),
						  multiSelect: true, 
							selModel: {  
							    selType:'checkboxmodel'  
							}, 
						  columns : [ {
						  xtype : 'rownumberer',
						    width : 30
						 },
						 {
					      width:150,
					      text:$i18n.chcheck_no,//盘点单号
					      dataIndex:'checkNo'
					     },
					     {
					       width:120,
					       text:$i18n.area_no,//储区
					       dataIndex:'areaNo'
					     },
					     {
					       width:120,
					       text:$i18n.area_name,//储区名称
					       dataIndex:'areaName'
					     },
					     {
					       width:120,
					       text:$i18n.stock_no,//通道代码
					       dataIndex:'stockNo'
					     }]
					}]
			}]
	}]
});