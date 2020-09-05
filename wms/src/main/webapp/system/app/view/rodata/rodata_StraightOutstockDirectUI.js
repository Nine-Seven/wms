/**
 * 模块名称：退厂发单(天天惠)
 * 模块编码：7501
 * 创建：hcx
 */
var straightOutstockDirectStore=Ext.create('cms.store.rodata.rodata_StraightOutstockDirectStore',{autoLoad:true}); 
Ext.define('cms.view.rodata.rodata_StraightOutstockDirectUI',{
	alias:'widget.rodata_StraightOutstockDirectUI',
	title:$i18n.title7201,//退厂发单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.commMenu5',
	          'cms.view.common.recedeNoCombo',
	          'cms.view.common.bdef_DefSupplierCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget4',
	    	   id:'menu7501',
	   		   region:'north'
	       },{
				xtype : 'form',
				id : 'formRodata_OutstockM7501',
				region : 'north',
				layout:'column',
				frame : true,
				items : [ {
					layout : {
					type : 'table',
					columns : 2
					},
					xtype : 'container',
					defaults : {
						labelWidth : 90,
						margin : '2 2 2 2',
						labelAlign : 'right',
						width : 300
					},
					items : [ {
		        	 xtype:'combo',
	            	 fieldLabel:$i18n.suppliers,//供应商
	            	 queryMode:'local',
		             allowBlank:true,
		             id:'cmbSuppliers7501',
		             displayField: 'dropValue',
					 valueField: 'value',
		             store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore',
					 {
						proxy:{
							type:'ajax',
							method:'post',
							url:'rodata_StraightOutstockDirectAction_getSupplierCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								flag : "1"
							}
						},
						listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbSuppliers7501').setValue(th.getAt(0).data.value);
									}
								}
							}
	   				}),
		             beforeLabelTextTpl:required
		         },{
						xtype:'combo',
		            	 fieldLabel:$i18n.classType,//退货类型
			             allowBlank:true,
			             id:'classType7501',
			             displayField: 'dropValue',
						 valueField: 'value',
			             store:Ext.create('cms.store.rodata.rodata_StraightOutstockDirectStore',
						 {
							proxy:{
								type:'ajax',
								method:'post',
								url:'rodata_StraightOutstockDirectAction_getClassTypeComboList.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('classType7501').setValue(th.getAt(0).data.value);
									}
								}
							}
		   				}).load(),
						beforeLabelTextTpl : required
					},{
						xtype:'combo',
		            	 fieldLabel:'波次号',//波次号
			             allowBlank:true,
			             id:'cmbWaveNo7501',
			             displayField: 'dropValue',
						 valueField: 'value',
			             store:Ext.create('cms.store.rodata.rodata_StraightOutstockDirectStore',
						 {
							proxy:{
								type:'ajax',
								method:'post',
								url:'rodata_StraightOutstockDirectAction_getWaveNoComboList.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbWaveNo7501').setValue(th.getAt(0).data.value);
									}
								}
							}
		   				}).load()
					}, {
						xtype : 'bdef_DefWorkerCombo',
						margin:'3 0 0 2',
						id:'cmbWorker7501',
						labelAlign:'right',
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
						fieldLabel : '发单人员',//发单人员
						beforeLabelTextTpl : required
					} ]
				} ]
			},
			{
				xtype : 'grid',
				id : 'gridRodata_OutstockD7501',
				store:straightOutstockDirectStore,
				region : 'center',
				columns : [{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 140,
					text : '波次号',//波次号
					dataIndex:'waveNo'
				},{
					width : 140,
					text : $i18n.recede_no,//退货单号
					dataIndex:'sourceNo'
				},{
					width : 70,
					text : $i18n.s_cell_no,//来源储位
					 align:'center',
					dataIndex:'SCellNo'
				},{
					width : 70,
					text : $i18n.d_d_cell_no,//目的储位
					 align:'center',
					dataIndex:'DCellNo'
				},{
					width : 110,
					text : $i18n.article_no,//商品编码
					align:'center',
					dataIndex:'articleNo'
				},{
					width : 100,
					text : $i18n.owner_article_no,//货主商品编码
					dataIndex : 'ownerArticleNo'
				},{
					width : 180,
					text : $i18n.article_name,//商品名称
					align:'center',
					dataIndex:'articleName'
				},{
					width : 120,
					text : $i18n.barcode,//商品条码
					 align:'center',
					dataIndex:'barcode'
				},{
					width : 100,
					text : $i18n.outstock_article_qty,//预下数量
					 align:'center',
					dataIndex:'locateQty'
				},{

					width : 100,
					text : $i18n.packing_unit,
					align:'center',
					dataIndex:'packingUnit',
					id:'packingUnit7501'
				
				},{
					width : 80,
					text : $i18n.packing_qty,//包装数量
					 align:'center',
					dataIndex:'packingQty'
				},{
					width : 80,
					text : $i18n.packingSpec,//规格
					align:'center',
					dataIndex:'packingSpec',
					id:'packingSpec7501'
				}]
			}
	       ]
});