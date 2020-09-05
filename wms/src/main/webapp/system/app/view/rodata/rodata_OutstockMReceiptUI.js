/**
 * 模块名称：退厂回单
 * 模块编码：7301
 * 创建 ： 周欢
 */
var rodata_OutstockM7301_1=Ext.create('cms.store.rodata.rodata_OutstockMStore',{
	listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(Ext.getCmp('rodata_OutstockM7301_1').getStore().count()>0){
						Ext.getCmp('rodata_OutstockM7301_1').getSelectionModel().select(0);
					}
				}
			}
});
var gridRodata_OutstockM7301_3=Ext.create('cms.store.rodata.rodata_OutstockDStore');
Ext.define('cms.view.rodata.rodata_OutstockMReceiptUI',{
	alias:'widget.rodata_OutstockMReceiptUI',
	title:$i18n.title7301,//退厂回单
	id:'rodata_OutstockMReceiptUI7301',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.bdef_DefWorkerCombo'
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget4',
	   		   region:'north',
	   		   id:'menu7301'
	       },{
				xtype : 'grid',
				id:'rodata_OutstockM7301_1',
				store:rodata_OutstockM7301_1,
				region : 'center',
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				}, {
					width : 160,
					text : $i18n.outstock_no,//下架单号
					dataIndex : 'outstockNo'
				}, {
					width : 120,
					text : $i18n.operate_type,//作业类型
					dataIndex:'operateType'
				}, {
					width : 140,
					text : $i18n.operate_date,//操作日期
					format:'Y-m-d H:i:s',
					dataIndex:'operateDate',
					field: {
		                xtype: 'datefield',
		                format : 'Y-m-d'
		            },	                
		            renderer:function(value){   
					    if(value instanceof Date){   				 
					        return Ext.Date.format(value,'Y-m-d');   
					    }else{				        
					        return value;   
					    }  
					}
					
				}, {
					width : 120,
					text : $i18n.status2,//操作状态
					dataIndex:'statusText'
				}]
			},{
				xtype : 'form',
				layout:'column',
				region : 'east',
				id:'formRodata_outstockm7301_2',
				frame : true,
				width : '45%',
				items:[
				       {
					layout:{
					type : 'table',
					columns : 1
					},
					xtype:'container',
					margin:'20 0 0 0',
					defaults:{
						labelWidth : 90,
						margin : '5 0 5 0',
						labelAlign : 'right',
						xtype:'textfield'
					},
					items:[
					{
						xtype : 'textfield',
						fieldLabel : $i18n.outstock_no,// 下架单号
						id:'txtOutstock_no7301_2',
						readOnly:true
					},{
						xtype : 'bdef_DefWorkerCombo',
						fieldLabel : $i18n.outstock_name,// 实际下架人员
						id:'cmbOutstock_name7301_2',
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
						beforeLabelTextTpl : required
					}]
				}]
	         },{
					xtype : 'grid',
					id:'gridRodata_OutstockM7301_3',
					region : 'south',
					selModel : {
			        	selType : 'cellmodel'
			        },
					plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
						clicksToEdit : 1,
						onSpecialKey:function(ed,field,e){
							commEnterGridStatEdit(this.grid,false,'',e.getKey());
						}
					})],
					store:gridRodata_OutstockM7301_3,
					height : 260,
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					}, {
						width : 160,
						text : $i18n.recede_no,//退货单号
						dataIndex:'recedeNo'
					}, {
						width : 120,
						text : $i18n.barcode,//商品条码
						dataIndex:'barcode'
					}, {
						width : 120,
						text : $i18n.article_no,//商品编码
						dataIndex:'articleNo'
					}, {
						width : 100,
						text : $i18n.owner_article_no,//货主商品编码
						dataIndex : 'ownerArticleNo'
					},{
						width : 180,
						text : $i18n.article_name,//商品名称
						dataIndex:'articleName'
					}, {
						width : 100,
						text : $i18n.s_cell_no,//来源储位
						dataIndex:'SCellNo'
					}, {
						width : 100,
						text : $i18n.origin_qty,//计划移动数量
						hidden:true,
						dataIndex : 'articleQty'
					}/*,{
						width : 80,
						text : $i18n.articleqty,//计划箱数
						dataIndex:'planWholenum'
					}, {
						width : 80,
						text : $i18n.real_qty3,//实际箱数
						cls:'notnull',
						field : {
    						xtype : 'numberfield',
    						minValue:0,
	    					listeners:{  
								'change': function(obj, newValue, oldValue, eOpts) {
									if(newValue!=oldValue){
										var data = Ext.getCmp('gridRodata_OutstockM7301_3').getSelectionModel().getSelection();
											Ext.getCmp('gridRodata_OutstockM7301_3').getSelectionModel().getSelection();
										data[0].set('realQty', newValue
											* data[0].get('packingQty'));
									}
								}
			      			}
    				    },
						dataIndex:'realWholenum'
					}*/,{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7301'
					},{
						width : 85,
						text : $i18n.planBox,//计划箱数
						dataIndex : 'planBox',
						id:'planBox7301'
					},{
						width : 85,
						text : $i18n.planQmin,//计划中包数
						dataIndex : 'planQmin',
						id:'planQmin7301'
					},{
						width : 85,
						text : $i18n.planDis,//计划散数
						dataIndex : 'planDis',
						id:'planDis7301'
					},{
						width : 85,
						text : $i18n.realBox,//实际箱数
						dataIndex : 'realBox',
						//hidden:true,
						id:'realBox7301',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realQmin,//实际中包数
						dataIndex : 'realQmin',
						id:'realQmin7301',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realDis,//实际散数
						dataIndex : 'realDis',
						id:'realDis7301',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 80,
						text : $i18n.packing_qty,//包装数量
						dataIndex:'packingQty'
					},{
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7301'}]
					},{
						xtype : 'panel',
						id : 'msterForm7301',
						region:'south',
						html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
							+ 'background-color:#C1D5ED; text-align: left;">'
							+ '<span><label>'+$i18n.addPeople+'：</label><label id="rgstName7301"></label> </span> '
							+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.addTime+'：</label><label id="rgstDate7301"></label></span>'
							+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.local_path+'：</label><label id="updtName7301"></label> </span> '
							+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.editTime+'：</label><label id="updtDate7301"></label> </span></div>'
					}
	       ]          
});