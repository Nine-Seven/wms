/**
 * 模块名称：进货手建单
 * 模块编码：4101
 * 创建：MM 
 */
var idata_ImPort_M = Ext.create('cms.store.idata.idata_ImPort_MStore',{autoLoad: true});
Ext.define('cms.view.idata.idata_ImPortUI', {
	alias : 'widget.idata_ImPortUI',
	id:'idata_ImPortUI',
	title:$i18n.title4101, //进货手建单
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
	    id:'tabPId800000',
	    region:'center',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	id:'tabPId4101',
	    	itemId:'tabPId4101i',
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
				},{
					text : '取消订单',
					name : 'undoOrder',
					iconCls : 'undo' //修改
				},{
					text : '打印',
					name : 'printBut',
					id:    'printButton',
					iconCls : 'print' //打印
				}],
	    		region:'north'
	    	},{
				xtype : 'grid',
				region:'center',
				id : 'grid_01_4101',
				width : '100%',
				height : '100%',
				store : idata_ImPort_M,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 90,
					text : $i18n.owner_no,//委托业主编号
					dataIndex : 'ownerNo' 
				},{
					width : 120,
					text : $i18n.owner_name,//委托业主名称
					dataIndex : 'ownerName'
				},{
					width:80,
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
					width : 150,
					text : $i18n.supplier_name,//供应商名称
					dataIndex : 'supplierName'
				},{
					width : 90,
					text : $i18n.orderdate,//预约日期
					dataIndex : 'orderDate'
				},{
					width : 90,
					text : $i18n.request_date1,//预定到货日
					dataIndex : 'requestDate'
				},{
					width : 135,
					text : $i18n.s_import_no,//汇总进货单号
					dataIndex : 'SImportNo'
				},{
					width : 80,
					text : $i18n.status,//状态	
					dataIndex : 'statusText'
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
	    	id:'tabPIdd2_4101',
	    	itemId:'tabPIdd2_4101i',
	    	items:[
	    	{
	    	   	xtype:'commMenuWidget',
	    	   	id:'menu4101',
	    	   	region:'north'
	        },{
	        	xtype : 'form',
			    id : 'form_01_4101',
			    region:'north',
			    frame : true,
			    layout : 'column',
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
					id : 'import_no4101',	
					readOnly:true,
					beforeLabelTextTpl : required
			    },{
					xtype : 'textfield',
					fieldLabel : $i18n.po_no1,// 采购单号
					id : 'po_no14101',	 
					readOnly:true,
					maxLength:20,
					beforeLabelTextTpl : required	
			    },{
		        	xtype:'bdef_DefOwnerCombo',
	    			fieldLabel : $i18n.owner_no,//货主编号
	    			id:'owner_no4101',
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
				 	id:'supplierNo4101',
				    store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
		        	displayField : 'dropValue',
		        	valueField : 'value',
		        	readOnly:true,
		        	beforeLabelTextTpl : required
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.receive_type,// 单据类型
					id : 'receiveType4101',	
		       	    //editable:false,
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
					id : 'takeType4101',	
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,TAKE_TYPE"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
					xtype : 'datefield',
					fieldLabel : $i18n.order_date,//采购发单日
					id : 'orderDate4101',								
					format : 'Y-m-d',
					readOnly:true,
					beforeLabelTextTpl : required	
				},{
					xtype : 'datefield',
					fieldLabel : $i18n.request_date1, //预定到货日
					id : 'requestDate4101',							
					format : 'Y-m-d',
					readOnly:true,
					beforeLabelTextTpl : required	
				},{
					xtype : 'numberfield',
					fieldLabel : $i18n.end_date,//到期日
					id : 'endDate4101',
					readOnly:true,
					minValue:0,
	    	        maxValue:999,
					beforeLabelTextTpl : required	
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.orgNo,// 机构代码
					id : 'orgNo4101',	
//		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,ORG_NO"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
					xtype : 'textareafield',
					fieldLabel : $i18n.import_remark,//进货备注
					id : 'importRemark4101',
					maxLength:255,
					readOnly:true
				}]
	        },{			
				xtype : 'grid',
				id : 'grid_02_4101',//
				region:'center',
				height : 300,
				loadMask : true, // 加载时有加载的图标
				store : Ext.create('cms.store.idata.idata_ImPort_DStore'),
				selModel : {
					selType : 'cellmodel'
				},
				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1,
					onSpecialKey:function(ed,field,e){
					commEnterGridStatEdit(this.grid,true,'cms.controller.idata.idata_ImPortController',e.getKey());
					}
				})],
				columns : [
			    {
			    	xtype : 'rownumberer',
			    	width : 30
				}, 
				{
					width : 120,
					text : $i18n.article_no,// 商品编码
					dataIndex : 'articleNo',
					cls : 'notnull',
					field : {							
    	        		id : 'article_no4101',
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
					width:90,
					text:$i18n.cust_no,//客户编号
					hidden:true,
					id:'cust_no4101List',
					dataIndex : 'custNo',
					cls : 'notnull',
					field:{
						xtype:'bdef_DefCustCombo',
						id:'cust_no4101',
						store:Ext.create('cms.store.bdef.bdef_DefCustComboStore'),
				        displayField : 'value',
				        valueField : 'value',
				        beforeLabelTextTpl : required
					}
				},{
					width:180,
					text:$i18n.cust_name,//客户名称
					hidden:true,
					id:'cust_name4101List',
					dataIndex:'custName'
				},{
					width : 85,
					text : $i18n.packing_qty,//包装数量
					dataIndex : 'packingQty',
					cls : 'notnull',
					field:{
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qty4101',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
						editable:false,
						allowBlank :false
					}
				},{
					width : 85,
					text : $i18n.packing_unit,//包装单位
					dataIndex : 'packingUnit'
				},{
					width : 85,
					text : $i18n.spec,//规格
					dataIndex : 'spec'
				},{
					width : 85,
					text : $i18n.poQty,//要货数
					dataIndex : 'pkQty',
					cls : 'notnull',
					field : {
    	        		xtype : 'numberfield',
    	        		minValue:0
    	        	}
				},{
					width : 85,
					text : '价格',//价格
					dataIndex : 'unitCost',
					field : {
    	        		xtype : 'numberfield',
    	        		minValue:0
    	        	}
				}],
			    	dockedItems : [{
					xtype:'commMenuWidget6',
		    		region:'north',
		    		id:'toolbar4101'
			    }]
	},{
		xtype : 'panel',
		id : 'msterForm4101',
		region:'south',
		html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
				+ 'background-color:#C1D5ED; text-align: left;">'
				+ '<span><label>新增人:</label><label id="rgstName4101"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;新增日期：</label><label id="rgstDate4101"></label></span>'
				+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName4101"></label> </span> '
					+ '<span><label>&nbsp;&nbsp;&nbsp;修改日期：</label><label id="updtDate4101"></label> </span></div>'
		}]
	    }]
	}]
});