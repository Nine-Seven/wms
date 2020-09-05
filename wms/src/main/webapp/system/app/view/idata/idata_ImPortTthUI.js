/**
 * 模块名称：进货手建单（天天惠）
 * 模块编码：4102
 * 创建：hkl 
 */
var idata_ImPort_M = Ext.create('cms.store.idata.idata_ImPort_MStore',{autoLoad: true});
Ext.define('cms.view.idata.idata_ImPortTthUI', {
	alias : 'widget.idata_ImPortTthUI',
	id:'idata_ImPortTthUI',
	title:$i18n.title4102, //进货手建单
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu',
				'cms.view.common.commMenu3',
	           	'cms.view.common.commMenu6',
	           	'cms.view.common.bdef_DefArticleCombo',
	            'cms.view.common.bdef_DefOwnerCombo',
	            'cms.view.common.bdef_DefCustCombo',
	            'cms.view.common.bdef_DefSupplierCombo',
	            'cms.view.common.bdef_PackingQtyCombo',
	           	'cms.view.common.wms_DefFieldValCombo'
				],
	items : [
	{
		xtype : 'tabpanel',
	    id:'tabPId4102',
	    region:'center',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	itemId:'tabPId4102i',
	    	items:[
	    	{
	    		xtype : 'commMenuWidget3',
	    		items: [{
					text : $i18n.find,//查找
					iconCls : 'query',
					name : 'detailQuery'
				},{
					text : '导入',
					icon: 'system/extjs/resources/icons/fam/application_split.png',
					name : 'upload'
				},
				{
					text : '取消订单',
					name : 'undoOrder',
					iconCls : 'undo' //取消订单
				}],
	    		region:'north'
	    	},{
				xtype : 'grid',
				region:'center',
				id : 'grid_01_4102',
				width : '100%',
				height : '100%',
				store : idata_ImPort_M,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 60,
					text : $i18n.owner_no,//委托业主编号
					dataIndex : 'ownerNo' 
				},{
					width : 120,
					text : $i18n.owner_name,//委托业主名称
					dataIndex : 'ownerName'
				},{
					width:60,
					text:$i18n.outstocktype,//单据类型
					dataIndex:'potypeText'
				},{
					width : 135,
					text : $i18n.po_no1,//采购单号
					dataIndex : 'poNo'
				},{
					width : 135,
					text : $i18n.import_no,//手建单号
					dataIndex : 'importNo'
				},{
					width : 80,
					text : $i18n.supplier_no,//供应商编号
					dataIndex : 'supplierNo' 
				},{
					width : 130,
					text : $i18n.supplier_name,//供应商名称
					dataIndex : 'supplierName'
				},{
					width : 90,
					text : $i18n.order_date,//预采购发单日期
					dataIndex : 'orderDate'
				},{
					width : 90,
					text : $i18n.request_date1,//预定到货日
					dataIndex : 'requestDate'
				},{
					width : 60,
					text : $i18n.status,//状态	
					dataIndex : 'statusText'
				},{
					width : 135,
					text : $i18n.s_import_no,//汇总进货单号
					dataIndex : 'SImportNo'
				},{
					width : 135,
					text : '商品数量',//商品数量
					dataIndex : 'qty'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : idata_ImPort_M,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	id:'tabPIdd2_4102',
	    	itemId:'tabPIdd2_4102i',
	    	items:[
	    	{
	    	   	xtype:'commMenuWidget',
	    	   	id:'menu4102',
	    	   	region:'north'
	        },{
	        	xtype : 'form',
			    id : 'form_01_4102',
			    region:'north',
			    frame : true,
			    layout:{
    	    		type:'table',
    	    		columns:2
    	    	},
			    defaults : {
					labelWidth : 90,
					margin : '2 2 2 2',
					labelAlign : 'right',
					width : 400
			    },
			    items :[
			    {
					xtype : 'textfield',
					fieldLabel : $i18n.import_notice_list,//进货通知单号
					id : 'import_no4102',	
					readOnly:true,
					beforeLabelTextTpl : required
			    },{
					xtype : 'textfield',
					fieldLabel : $i18n.po_no1,// 采购单号
					id : 'po_no14102',	 
					readOnly:true,
					maxLength:20,
					beforeLabelTextTpl : required	
			    },{
		        	xtype:'bdef_DefOwnerCombo',
	    			fieldLabel : $i18n.owner_no,//货主编号
	    			id:'owner_no4102',
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	    	        readOnly:true,
	    	        allowBlank : false,
	    	        beforeLabelTextTpl : required
				},{
				 	xtype:'bdef_DefSupplierCombo',
					fieldLabel : $i18n.supplier_no,//供应商编号
				 	id:'supplierNo4102',
				    store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
		        	displayField : 'dropValue',
		        	valueField : 'value',
		        	readOnly:true,
		        	beforeLabelTextTpl : required
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.receive_type,// 单据类型
					id : 'receiveType4102',	
		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,IMPORT_TYPE"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.take_type,// 提货类型
					id : 'takeType4102',	
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,TAKE_TYPE"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
					xtype : 'datefield',
					fieldLabel : $i18n.order_date,//采购发单日
					id : 'orderDate4102',								
					format : 'Y-m-d',
					readOnly:true,
					beforeLabelTextTpl : required	
				},{
					xtype : 'datefield',
					fieldLabel : $i18n.request_date1, //预定到货日
					id : 'requestDate4102',							
					format : 'Y-m-d',
					readOnly:true,
					beforeLabelTextTpl : required	
				},{
					xtype : 'numberfield',
					fieldLabel : $i18n.end_date,//到期日
					id : 'endDate4102',
					readOnly:true,
					minValue:0,
	    	        maxValue:999,
					beforeLabelTextTpl : required	
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.orgNo,// 机构代码
					id : 'orgNo4102',	
		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,ORG_NO"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.unload_type,// 卸货方式
					id : 'rsvVarod1_4102',	
		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,UNLOAD_TYPE"}
		       	    })
				},{
					xtype : 'textfield',
					fieldLabel : $i18n.import_remark,//进货备注
					id : 'importRemark4102',
					maxLength:255,
					readOnly:true
				},{ 
					//huangb 20160721
					xtype : 'textfield',
					fieldLabel : $i18n.new_po_no,//新来源单号
					id : 'rsvVarod2_4102',
					maxLength:255,
					readOnly:false
				},{
					//huangb 20160721
					xtype : 'button',
					region:'left',
					width : 80,	
					id:'btnDiffConfirm4102',
					text : $i18n.diff_confirm//差异确认
				}
				]
	        },
	        {
    		xtype : 'tabpanel',
    	    id:'tabPId800001',
    	    region:'center',
    	    flex : 4,
    	    items : [
    	    {
		    	title:$i18n.idata_po,//采购定单
		    	layout:'border',
		    	id:'tabPId4102_1',
		    	itemId:'tabPId4102i_1',
		    	items:[{			
	                 xtype : 'grid',
	                 id : 'grid_02_4102',
	                 region:'center',
	                 height : 300,
	                 loadMask : true, // 加载时有加载的图标
	                 store : Ext.create('cms.store.idata.idata_ImPortTth_DStore',{
	                	     listeners:{ 	
	             				'load':function(th,records,successful,eOpts ){
	             					if(records.length>0){
	             						//判断是否允许差异确认 huangb 20160722
	             						if(records[0].data.isDiffConfirmFlag == "1" 
	             							&& records[0].data.modifyFlag == "1"){
		             						Ext.getCmp('rsvVarod2_4102').setVisible(true);
		             						Ext.getCmp('btnDiffConfirm4102').setVisible(true);
		             			
		             					}else{
		             						Ext.getCmp('rsvVarod2_4102').setVisible(false);
		             						Ext.getCmp('btnDiffConfirm4102').setVisible(false);
		             					}

	             						//状态为13,16不能修改，其他的状态可以  8-17  hj 
	             						if(records[0].data.status == "13" || records[0].data.status=="16"){
	             							Ext.getCmp('menu4102').items.items[3].setVisible(false);
		             					}else{
		             						Ext.getCmp('menu4102').items.items[3].setVisible(true);
		             					}
	             						Ext.getCmp('po_no14102').setValue(records[0].data.poNo);
		             					var arrayObj = new Array();
		             					arrayObj[0]='planBox';
		             					arrayObj[1]='planQmin';
		             					arrayObj[2]='planDis';
		             					countList('grid_02_4102',arrayObj,'articleNo');
	             					}else{
	             						Ext.getCmp('rsvVarod2_4102').setVisible(false);
	             						Ext.getCmp('btnDiffConfirm4102').setVisible(false);
	             					}
	             				
	             				}
	             			}
	                 }),
	             			
	                 selModel : {
	                        selType : 'rowmodel'
	                      },
	                 plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	                           clicksToEdit : 1,
	                           onSpecialKey:function(ed,field,e){
	                           commEnterGridStatEdit(this.grid,true,'cms.controller.idata.idata_ImPortTthController',e.getKey());
	                           }})],
	                 columns : [
	                    {
	                        xtype : 'rownumberer',
	                        width : 30
	                    },{
							width : 120,
							text : $i18n.article_no,// 商品编码
							dataIndex : 'articleNo',
							cls : 'notnull',
							field : {							
				    		id : 'article_no4102',
				    		xtype:'bdef_DefArticleCombo',
				    		displayField : 'value',
							valueField : 'value',
				    		store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
				    		allowBlank :false
				          }
						},{
							width : 110,
							text : $i18n.owner_article_no,//货主商品编码
							dataIndex : 'ownerArticleNo'
						},{
							width : 220,
							text : $i18n.article_name,//商品名称
							dataIndex : 'articleName'
						},{
							width : 110,
							text : $i18n.barcode,//商品条码
							dataIndex : 'barcode'
						},{
							width : 85,
							text : $i18n.packing_qty,//包装数量
							dataIndex : 'packingQty',
							cls : 'notnull',
							field:{
								xtype : 'bdef_PackingQtyCombo',
								id : 'packing_qty4102',
								store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
								editable:false,
								allowBlank :false
							}
						},{
							width : 85,
							text : $i18n.packing_unit,//包装单位
							id:'packingUnit4102_1',
							dataIndex : 'packingUnit'
						},{
							width : 85,
							text : $i18n.packingSpec,//规格
							id:'packingSpec4102_1',
							dataIndex : 'packingSpec'
						},{
							width : 85,
							text : $i18n.planBox,//采购箱数
							dataIndex : 'planBox',
							id:'planBox4102_1',
							cls : 'notnull',
							field : {
					    		xtype : 'numberfield',
					    		minValue:0
					    	}
						},{
							width : 85,
							text : $i18n.planQmin,//中包装数
							dataIndex : 'planQmin',
							id:'planQmin4102_1',
							cls : 'notnull',
							field : {
					    		xtype : 'numberfield',
					    		minValue:0
					    	}
						},{
							width : 85,
							text : $i18n.planDis,//采购零散数
							dataIndex : 'planDis',
							id:'planDis4102_1',
							cls : 'notnull',
							field : {
					    		xtype : 'numberfield',
					    		minValue:0
					    	}
						},{
							width : 50,
							text:$i18n.unit_cost,//单价
							dataIndex : 'unitCost',
							cls : 'notnull',
							field : {
		    	        		xtype : 'numberfield',
		    	        		minValue:0
		    	        	}
						}],
				    	dockedItems : [{
						xtype:'commMenuWidget6',
						region:'north',
						id:'toolbar4102'
				        }]
	                  }]
	    	        },
	    	        {
		    	    	title:$i18n.idata_divide_d,//分播明细
		    	    	layout:'border',
		    	    	id:'tabPId4102_2',
		    	    	itemId:'tabPId4102i_2',
		    	    	items:[{
		    	    		xtype : 'grid',
							id : 'grid_03_4102',//
							region:'center',
							height : 300,
							loadMask : true, // 加载时有加载的图标
							store : Ext.create('cms.store.idata.idata_ImPortAllotStore',{
		                	     listeners:{ 	 
			             				'load':function(th,records,successful,eOpts ){
			             					var arrayObj = new Array();
			             					arrayObj[0]='planBox';
			             					arrayObj[1]='planQmin';
			             					arrayObj[2]='planDis';
			             					countList('grid_03_4102',arrayObj,'custNo');
			             				}
			             			}
			                 }),
							selModel : {
								selType : 'cellmodel'
							},
							plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
								clicksToEdit : 1,
								onSpecialKey:function(ed,field,e){
								commEnterGridStatEdit(this.grid,true,'cms.controller.idata.idata_ImPortTthController',e.getKey());
								}
							})],
							columns : [
						    {
						    	xtype : 'rownumberer',
						    	width : 30
							},{
								width:90,
								text:$i18n.cust_no,//客户编号
								id:'cust_no4102List',
								dataIndex : 'custNo',
								cls : 'notnull',
								field:{
									xtype:'bdef_DefCustCombo',
									id:'cust_no4102',
									store:Ext.create('cms.store.bdef.bdef_DefCustComboStore'),
							        displayField : 'value',
							        valueField : 'value',
							        beforeLabelTextTpl : required,
							        allowBlank :false
								}
							},{
								width:150,
								text:$i18n.cust_name,//客户名称
								id:'cust_name4102List',
								dataIndex:'custName'
							},{
								width : 80,
								text : $i18n.take_type,//提货类型
								dataIndex : 'takeType',
								cls : 'notnull',
								field : {
									xtype:'wms_DefFieldValCombo',
									/*displayField : 'dropValue',
									valueField : 'dropValue',*/
						       	    store:Ext.create("cms.store.common.comboStore").load(
						       	    {
						       	         params:{str:"N,TAKE_TYPE"}
						       	    }),
						    	}
							},{
							width : 100,
							text : $i18n.exp_no,//出货单号
							dataIndex : 'poNo',
							cls : 'notnull',
							field : {
					    		xtype : 'textfield',
					    		minValue:0
					    	}
						},{
								width : 90,
								text : $i18n.article_no,// 商品编码
								dataIndex : 'articleNo',
								cls : 'notnull',
								field : {							
						    		id : 'article_no4102_2',
						    		xtype:'bdef_DefArticleCombo',
						    		displayField : 'value',
									valueField : 'value',
						    		store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
						    		allowBlank :false
						       }
							},{
								width : 110,
								text : $i18n.owner_article_no,//货主商品编码
								dataIndex : 'ownerArticleNo'
							},{
								width : 150,
								text : $i18n.article_name,//商品名称
								dataIndex : 'articleName'
							},{
								width : 110,
								text : $i18n.barcode,//商品条码
								dataIndex : 'barcode'
							},{
								width : 70,
								text : $i18n.packing_qty,//包装数量
								dataIndex : 'packingQty',
								cls : 'notnull',
								field:{
									xtype : 'bdef_PackingQtyCombo',
									id : 'packing_qty4102_2',
									store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
									editable:false,
									allowBlank :false
								}
							},{
								width : 70,
								text : $i18n.packing_unit,//包装单位
								id:'packingUnit4102_2',
								dataIndex : 'packingUnit'
							},{
								width : 50,
								text : $i18n.packingSpec,//规格
								id:'packingSpec4102_2',
								dataIndex : 'packingSpec'
							},{
								width : 70,
								text : $i18n.idata_box,//要货箱数
								dataIndex : 'planBox',
								id:'planBox4102_2',
								cls : 'notnull',
								field : {
						    		xtype : 'numberfield',
						    		minValue:0
						    	}
							},{
								width : 85,
								text :$i18n.idata_qmin,//中包装数
								dataIndex : 'planQmin',
								cls : 'notnull',
								id:'planQmin4102_2',
								field : {
						    		xtype : 'numberfield',
						    		minValue:0
						    	}
							},{
								width : 70,
								text : $i18n.idata_dis,//要货零散数
								dataIndex : 'planDis',
								cls : 'notnull',
								id:'planDis4102_2',
								field : {
						    		xtype : 'numberfield',
						    		minValue:0
						    	}
							}],
						    	dockedItems : [{
								xtype:'commMenuWidget6',
								region:'north',
								id:'toolbar4102_2'
						    }]		    	    				    	    				    	    
		    	    	}]
	    	         }
	          
	    	    ]
	   },
	   {
		xtype : 'panel',
		id : 'msterForm4102',
		region:'south',
		html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
				+ 'background-color:#C1D5ED; text-align: left;">'
				+ '<span><label>新增人:</label><label id="rgstName4102"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;新增日期：</label><label id="rgstDate4102"></label></span>'
				+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName4102"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;修改日期：</label><label id="updtDate4102"></label> </span> '
				+ '</div>'
		}]
	    }]
	}]
});