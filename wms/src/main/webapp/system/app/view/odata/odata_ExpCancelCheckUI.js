/**
 * 模块名称：病单审核
 * 模块编码：3918
 * 创建：贺康利
 */

var gridCancelCheckM3918store = Ext.create('cms.store.odata.odata_ExpCancelMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpCancelCheckAction_getCancelCheck_MList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('gridCancelCheckM3918').getSelectionModel().select(0);
			}
		}
	}
});
var gridCancelCheckD3918store = Ext.create('cms.store.odata.odata_ExpCancelDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpCancelCheckAction_getCancelCheck_DList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('cms.view.odata.odata_ExpCancelCheckUI',{
	alias:'widget.odata_ExpCancelCheckUI',
	title:$i18n.title3918,//病单审核
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu4',
	          'cms.view.common.commMenu7',
	          'cms.view.common.wms_DefFieldValCombo',
		      'cms.view.common.bdef_DefWorkerCombo',
		      'cms.view.common.cdef_DefCellCombo',
		      'cms.view.common.bdef_DefOwnerCombo',
		  	  'cms.view.common.remoteCombo'
	],
	items:[
	{
	   xtype : 'toolbar',
	   id:'menu3918',
	   region:'north',
		   items:[{
			        text: '审核',
		            name:'userSendButton',
		            iconCls:'send'
				},{
					text : '刷新',
					iconCls : 'refresh',
				 	hidden:false,
					name : 'refresh'
				}] 
	},
    {
		xtype : 'form',
		layout : {
			 type : 'table',
			 columns :5
		},
		region : 'north',
		frame : true,
		defaults:{
	       labelAlign:'right',
	       labelWidth : 60
	    },
		items:[
		   {
			xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner,//货主编号
			queryMode:'local',
		    allowBlank:true,
		    id:'cmbOwnerNo3918',
		    displayField: 'dropValue',
			valueField: 'value',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
			 {
				 proxy:{
						type:'ajax',
						method:'post',
						url:'odata_ExpCancelCheckAction_getOdata_CancelCheckCombo',
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
								Ext.getCmp('cmbOwnerNo3918').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_ExpCancelCheckController')
								.cmbOwnerNo3918Select();
							}else
							{
								Ext.getCmp('cmbCancelNo3918').getStore().removeAll();
								Ext.getCmp('cmbCancelNo3918').setValue(null);
								Ext.getCmp('gridCancelCheckM3918').getStore().removeAll();
								Ext.getCmp('gridCancelCheckD3918').getStore().removeAll();
							}
						}
					}
				}),
		    beforeLabelTextTpl:required
		},
		{
			xtype:'wms_DefFieldValCombo',
	    	fieldLabel: $i18n.Chuli,//处理结果
	    	id:'cmbChuli3918',
    		store:Ext.create("cms.store.common.comboStore").load(
    		{
       	 		params:{str:"ODATA_EXP_CANCEL_M,HANDLEFLAG"}
    		}),
    		listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(th.count()>0){
						Ext.getCmp('cmbChuli3918').setValue(th.getAt(0).data.value);
						_myAppGlobal.getController('cms.controller.odata.odata_ExpCancelCheckController')
						.cmbCancelNo3918change();
					}
				}
			},
    		//editable:false,
    		allowBlank:false,
    		//readOnly:true,
    		//beforeLabelTextTpl:required
    	},
		{
    		xtype : 'datefield',
    		fieldLabel : $i18n.paper_date,//单据日期
			id : 'requestDate3918',							
			format : 'Y-m-d',
			//readOnly:true,
			//beforeLabelTextTpl : required	
		},
		{
			xtype:'remoteCombo',
			fieldLabel : $i18n.cancel_no,//病单单号
			id:'cmbCancelNo3918',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'odata_ExpCancelCheckAction_getOdata_CancelCheckCombo',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					},
					extraParams:{
						strFlag : "2"
					}
				}
			})
		}]
	},{
			xtype:'grid',
			region : 'center',
			store:gridCancelCheckM3918store,
	        id: 'gridCancelCheckM3918',
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			},
			{
				width : 150,
				text : $i18n.cancel_no,//病单单号
				dataIndex : 'cancelNo'
			},{
				width:150,
				text :$i18n.exp_no,//出货单号
				dataIndex : 'expNo'
			},{
				width:100,
				text :$i18n.cust_no,//客户编码
				dataIndex : 'custNo'
			},{
				width:150,
				text :$i18n.paper_date,//单据日期
				dataIndex : 'operateDate'
			},{
				width:150,
				text :$i18n.Chuli,//处理方式
				dataIndex : 'handleFlagText'
			}],
			dockedItems : [{
				   xtype : 'pagingtoolbar',
				   dock : 'bottom',
				   store:gridCancelCheckM3918store,
				   displayInfo : true
    	  	}]
		},
		{
			xtype:'grid',
			region : 'south',
			store:gridCancelCheckD3918store,
	        id: 'gridCancelCheckD3918',
			height:250,
			selType : 'cellmodel',
			plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 1,
				onSpecialKey:function(ed,field,e){
					commEnterGridStatEdit(this.grid,false,'',e.getKey());
				}
			})],
			columns:[{			
				xtype : 'rownumberer',
				width : 20
			},
			{
				width:110,
				text :$i18n.article_no,//商品编码
				dataIndex : 'articleNo'
			},
			{
				width : 100,
				text : $i18n.owner_article_no,//货主商品编码
				dataIndex : 'ownerArticleNo'
			},

			{
				width:180,
				text :$i18n.article_name,//商品名称
				dataIndex : 'articleName'
			},
			{
				width : 110,
				text : $i18n.barcode,//商品条码
				dataIndex : 'barcode'
			},
			{
				width : 60,
				text : $i18n.packing_qty,//包装数量
				dataIndex : 'packingQty'
			},
			/*{
				width : 60,
				text : $i18n.packing_unit,//包装单位
				dataIndex : 'unit'
			},*/
			{
   			    width:80,
   			    text:$i18n.packingUnit,//包装单位
   			    id:'packingUnit_3918',
   			    dataIndex:'packingUnit'
   			},{
   				width:80,
   				text:$i18n.packingSpec,//规格
   				id:'packingSpec_3918',
   				dataIndex:'packingSpec'
   			},
			{
				width : 60,
				text : $i18n.articleqty,//计划数
				dataIndex : 'articleQty'
			},
			{
   				width : 85,
   				text : $i18n.planBox,	//计划箱数
   				dataIndex : 'planBox',
   				id:'planBox_3918'
   			},{
   				width : 85,
   				text : $i18n.planQmin,//计划中包数
   				dataIndex : 'planQmin',
   				id:'planQmin_3918'
   			},{
   				width : 85,
   				text : $i18n.planDis,//计划散数
   				dataIndex : 'planDis',
   				id:'planDis_3918'
   			},
			{
				width : 60,
				text : $i18n.new_real_qty3,//实际数
				dataIndex : 'realQty',
				//cls:'notnull',
			},
			{
   				width : 85,
				text : $i18n.realBox,	//实际箱数
				dataIndex : 'realBox',
				id:'realBox_3918'
			},{
				width : 85,
				text : $i18n.realQmin,//实际中包数
				dataIndex : 'realQmin',
				id:'realQmin_3918'
			},{
				width : 85,
				text : $i18n.realDis,//实际散数
				dataIndex : 'realDis',
				id:'realDis_3918'
			},
			{
				width:120,
				text:$i18n.difference_qty,//差异数量,
				dataIndex:'differenceQty'
			},
			{
   				width : 85,
				text : $i18n.differenceBox,	//差异箱数
				dataIndex : 'differenceBox',
				id:'differenceBox_3918'
			},{
				width : 85,
				text : $i18n.differenceQmin,//差异中包数
				dataIndex : 'differenceQmin',
				id:'differenceQmin_3918'
			},{
				width : 85,
				text : $i18n.differenceDis,//差异散数
				dataIndex : 'differenceDis',
				id:'differenceDis_3918'
			}]
    }
	]
});














