/**
 * 模块名称：退厂回单
 * 模块编码：7302
 * 创建 ： chensr
 */
var rodata_OutstockM7302_1=Ext.create('cms.store.rodata.rodata_OutstockMTTHStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('rodata_OutstockM7302_1').getStore().count()>0){
				Ext.getCmp('rodata_OutstockM7302_1').getSelectionModel().select(0);
			}
		}
	}
});
var gridRodata_OutstockM7302_3=Ext.create('cms.store.rodata.rodata_OutstockDTTHStore');
var gridRodata_OutstockM7302_4=Ext.create('cms.store.rodata.rodata_OutstockDTTHStore',{
	listeners:{
		'load':function(){
			Ext.getCmp('gridRodata_OutstockM7302_4').getSelectionModel().selectAll();
		}
	}
});
Ext.define('cms.view.rodata.rodata_OutstockMReceiptTTHUI',{
	alias:'widget.rodata_OutstockMReceiptTTHUI',
	title:$i18n.title7301,//退厂回单
	id:'rodata_OutstockMReceiptUI7302',
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
	   		   id:'menu7302'
	       }, {

	        	xtype:'panel',
	        	id:'rodata_OutstockMTTHForm',
	        	layout:'border',
	        	region : 'center',
	        	frame:true,
	        	defaults:{
	        		margin: '5 5 5 5',
	        		labelWidth:60
	        	},
	        	items:[{
		       		id:'radiogroup7302',
		    		xtype:'radiogroup',
		    		region : 'north',
		    		fieldLabel:'回单类型',
		    		width:10,
		            columns: 2,
		            vertical: true,
		    		items:[{
		    			boxLabel: '表单', name: 'rb',  inputValue: '1',checked:true
		    		},{
		    			boxLabel: '标签', name: 'rb',  inputValue: '2'
		    		}]
		    	},{
					xtype : 'grid',
					id:'rodata_OutstockM7302_1',
					store:rodata_OutstockM7302_1,
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
				}]
	        },
			{
				xtype : 'form',
				layout:'column',
				region : 'east',
				id:'formRodata_outstockm7302_2',
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
						id:'txtOutstock_no7302_2',
						readOnly:true
					},{
						xtype : 'bdef_DefWorkerCombo',
						fieldLabel : $i18n.outstock_name,// 实际下架人员
						id:'cmbOutstock_name7302_2',
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
						beforeLabelTextTpl : required
					}]
				}]
	         },{
					xtype : 'grid',
					id:'gridRodata_OutstockM7302_3',
					region : 'south',
		    	    selModel: {  
		            	selType:'cellmodel' 
		    	    },
					store:gridRodata_OutstockM7302_3,
					height : 260,
					plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				        	clicksToEdit : 1,
					        onSpecialKey:function(ed,field,e){
								commEnterGridStatEdit(this.grid,true,'cms.controller.rodata.rodata_OutstockMTTHController',e.getKey());
							}
					    })],
					columns : [ 
					 {
						xtype : 'rownumberer',
						width : 30
					}, {
						width : 160,
						text : $i18n.recede_no,//退货单号
						id:'recede_no7302_2',
						dataIndex:'recedeNo'
					}, {
						width : 120,
						text : $i18n.barcode,//商品条码
						id:'barcode7302_2',
						dataIndex:'barcode'
					}, {
						width : 120,
						text : $i18n.article_no,//商品编码
						id:'article_no7302_2',
						dataIndex:'articleNo'
					}, {
						width : 100,
						text : $i18n.owner_article_no,//货主商品编码
						id:'ownerArticleNo7302_2',
						dataIndex : 'ownerArticleNo'
					},{
						width : 180,
						text : $i18n.article_name,//商品名称
						id:'article_name7302_2',
						dataIndex:'articleName'
					}, {
						width : 100,
						text : $i18n.s_cell_no,//来源储位
						id:'s_cell_no7302_2',
						dataIndex:'SCellNo'
					},{
						width : 100,
						text : $i18n.label_no,//标签号
						id:'slabel_no7302_2',
						dataIndex:'SLabelNo'
					},
					{
						width : 100,
						text : $i18n.origin_qty,//计划移动数量
						hidden:true,
						id:'articleQty7302_2',
						dataIndex : 'articleQty'
					}/*,{
						width : 80,
						text : $i18n.articleqty,//计划箱数
						id:'plan_wholenum7302_2',
						dataIndex:'planWholenum'
					},{
						width : 80,
						text : $i18n.real_qty3,//实际箱
						cls:'notnull',
						id:'real_wholenum7302_2',
						field : {
    						xtype : 'numberfield',
    						minValue:0
    				    },
						dataIndex:'realWholenum'
					}*/,{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7302'
					},{
						width : 85,
						text : $i18n.planBox,//计划箱数
						dataIndex : 'planBox',
						id:'planBox7302',
					},{
						width : 85,
						text : $i18n.planQmin,//计划中包数
						dataIndex : 'planQmin',
						id:'planQmin7302',
					},{
						width : 85,
						text : $i18n.planDis,//计划散数
						dataIndex : 'planDis',
						id:'planDis7302',
					},{
						width : 85,
						text : $i18n.realBox,//实际箱数
						dataIndex : 'realBox',
						//hidden:true,
						id:'realBox7302',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realQmin,//实际中包数
						dataIndex : 'realQmin',
						id:'realQmin7302',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realDis,//实际散数
						dataIndex : 'realDis',
						id:'realDis7302',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7302'
					},{
						width : 80,
						text : $i18n.packing_qty,//包装数量
						id:'packingQty7302',
						dataIndex:'packingQty'
					}]
					},{

						xtype : 'grid',
						id:'gridRodata_OutstockM7302_4',
						region : 'south',
						hidden:true,
						multiSelect: true,  
			    	    selModel: {  
			            	selType:'checkboxmodel' 
			    	    },
						store:gridRodata_OutstockM7302_4,
						height : 260,
						columns : [ 
						 {
							xtype : 'rownumberer',
							width : 30
						},{
							width : 160,
							text : '标签号',//标签号  
							dataIndex:'labelNo',
							id:'labelNo7302_2',
						}
					]						
					},{
						xtype : 'panel',
						id : 'msterForm7302',
						region:'south',
						html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
								+ 'background-color:#C1D5ED; text-align: left;">'
								+ '<span><label>新增人:</label><label id="rgstName7302"></label> </span> '
								+ '<span><label>&nbsp;&nbsp;&nbsp;新增时间：</label><label id="rgstDate7302"></label></span>'
								+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName7302"></label> </span> '
								+ '<span><label>&nbsp;&nbsp;&nbsp;修改时间：</label><label id="updtDate7302"></label> </span></div>'
					}
	       ]          
});