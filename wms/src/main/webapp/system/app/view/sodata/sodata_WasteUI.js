/**
 * 模块名称：报损中心
 * 模块编码：E101
 * 创建：hkl
 */
var sodata_Waste_MStore=Ext.create('cms.store.sodata.sodata_WasteMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_E101').getSelectionModel().select(0);
			}
		}
	}
	});
var gridArticleInfoE101store=Ext.create('cms.store.stock.stock_ContentStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'sodata_WasteAction_getSodataWasteList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	listeners:{
		'load':function(th,records,successful,eOpts ){
			var arrayObj = new Array();
				arrayObj[0]='recedeQty';
				arrayObj[1]='availableQty';
				arrayObj[2]='noEnoughQty';
				countList('gridArticleInfoE101',arrayObj,'labelNo');
				
			
		}
	}
});
Ext.define('cms.view.sodata.sodata_WasteUI',{
	alias:'widget.sodata_WasteUI',
	title:$i18n.titleE101,//报损中心
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			 'cms.view.common.commMenu',
			 'cms.view.common.commMenu3',
			 'cms.view.common.commMenu6',
			 'cms.view.common.bdef_DefCustCombo',
			 'cms.view.common.bdef_DefOwnerCombo',
			 'cms.view.common.bdef_DefArticleCombo',
			 'cms.view.common.bdef_PackingQtyCombo',
			 'cms.view.common.wms_DefFieldValCombo',
	],
	items:[
	{
		xtype:'tabpanel',
		id:'tabPIdE101',
	    region:'center',
	    items:[
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	items:[
		    	{
		    		xtype:'commMenuWidget3',
		    	    region:'north',
		    		items:[
		    		{
					text : '查找',
					iconCls : 'query',
					name : 'detailQuery'
				},{
					text : '导入',
					icon: 'system/extjs/resources/icons/fam/application_split.png',
					name : 'upload'
				},{
					text : '发单',
					iconCls : 'print',
					name : 'print',
				},{
					text : '取消订单',
					name : 'undoOrder',
					iconCls : 'undo' 
				},{

					xtype : 'radiogroup',
					id : 'rdoCheckTypeE101',
					margins: '0 0 0 10',
					fieldLabel :'是否打印下架单',
					width : 240,
					columns : 2,
					items : [
			        {
						boxLabel : $i18n.no_print,//不打印
						name : 'rd',
						inputValue : '0'
						
					},
					{
						boxLabel : $i18n.print,//打印
						name : 'rd',
						inputValue : '1',
						checked:true
					}
					]
				
				}]
	    	},{
	    		region:'center',
	    	    xtype:'grid',
	    	    id:'grid_01_E101',
	    	    store:sodata_Waste_MStore,
	    	    columns:[
	    	    {
	    	        xtype:'rownumberer',
	    	       	width:30 
	    	    },{
	    	       	width:150,
	    	       	text:$i18n.waste_No,//报损单号
	    	       	dataIndex:'wasteNo'
	    	    },{
	    	       	width:100,
	    	       	text:$i18n.waste_Type,//报损单别
	    	       	dataIndex:'wasteType'
	    	    },{
	    	       	width:140,
	    	       	text:$i18n.original_no,//原单号
	    	       	dataIndex:'poNo'
	    	    },{
	    	       	width:100,
	    	       	text:$i18n.orgNo,//机构号
	    	       	dataIndex:'orgNoText'
	    	    },{
	    	       	width:100,
	    	       	text:$i18n.status2,//操作状态
	    	       	dataIndex:'statusText'
	    	    }],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
					store:sodata_Waste_MStore,
					displayInfo : true
				}]
	    	    
	    	},{

				xtype: 'grid',
				id : 'gridArticleInfoE101',
				region:'south',
		        margins: '5 0 0 5',
		        height:200,
		        store:gridArticleInfoE101store,
		        columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 120,
					text : $i18n.waste_No,//报损单号
					dataIndex : 'labelNo'
				},{
					width : 120,
					text : $i18n.article_no,
					dataIndex : 'articleNo'
				},{
					width : 100,
					text : $i18n.owner_article_no,//货主商品编码
					dataIndex : 'ownerArticleNo'
				},{
					width : 170,
					text : $i18n.article_name,
					dataIndex : 'articleName'
				},
				{
					width : 80,
					text : $i18n.articleqty,//计划数量
					dataIndex : 'recedeQty'
				},{
					width : 80,
					text : $i18n.available_qty,//可用数量
					id:'availableQty',
					dataIndex : 'availableQty'
				},{
					width : 80,
					text : $i18n.no_enough_qty,//缺量
					id:'noEnoughQty',
					dataIndex : 'noEnoughQty'
				},{
					width : 80,
					text : '包装数量',
					dataIndex : 'packingQty'
				},{
   	        	 width:80,
	        	 text:$i18n.packingUnit,//包装单位
	        	 dataIndex:'packingUnit',
	        	 id:'packingUnitE101_1'
	         },{
				width : 85,
				text : $i18n.packingSpec,//规格
				dataIndex : 'packingSpec',
				id:'packingSpecE101_1'
			}]
            	
	    	}]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	itemId:'tabPIdE101i',
	    	items:[
	    	{
				xtype:'commMenuWidget',
				region:'north',
				id:'menuE101'
			},{
				xtype : 'form',
	    	    id : 'form_01_E101',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:3
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
    	    	{
					fieldLabel:$i18n.waste_No,//报损单号
					id:'waste_noE101',
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
		  		},{
					xtype:'bdef_DefOwnerCombo',
					fieldLabel : $i18n.owner_no,//委托业主编号
					id : 'owner_noE101',
					/*displayField : 'dropValue',
	        		valueField : 'value',*/
	        		readOnly:true,
					//autoLoad:true,
	        		store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore'),
	        		beforeLabelTextTpl : required
		  		},{
					xtype:'wms_DefFieldValCombo',
    	    		fieldLabel:$i18n.waste_Type,//报损单别
    	    		id:'waste_typeE101',
    	    		store:Ext.create("cms.store.common.comboStore").load(
    	    		{
    	       	 		params:{str:"N,WASTE_TYPE"}
    	    		}),
    	    		editable:false,
    	    		allowBlank:false,
    	    		readOnly:true,
    	    		beforeLabelTextTpl:required
		  		},{
					fieldLabel:$i18n.original_no,//原单号
					id:'po_noE101',
					allowBlank:false,
					readOnly:true,
					maxLength:20,
					beforeLabelTextTpl:required
		  		},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.orgNo,// 机构代码
					id : 'orgNoE101',	
		       	    //editable:false,
		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,ORG_NO"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
					xtype : 'datefield',
					fieldLabel : $i18n.waste_date,//报损日期
					id : 'wasteDateE101',								
					format : 'Y-m-d',
					readOnly:true,
					beforeLabelTextTpl : required	
				}]
			},{
	    		xtype : 'grid',
	    	    region:'center',
	    	    id:'grid_02_E101',
	    	    loadMask : true, // 加载时有加载的图标
	    	    store : Ext.create('cms.store.sodata.sodata_WasteDStore',{
	    	    	listeners:{  
	    	    		'load':function(th,records,successful,eOpts ){
	    				var arrayObj = new Array();
	    				arrayObj[0]='poBox';
	    				arrayObj[1]='realBox';
	    				countList('grid_02_E101',arrayObj,'articleNo');
	    	    		}
	    	    	}
	    	    }),
	    	    selModel : {
	    	        selType : 'cellmodel'
	    	    },
	    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    	        clicksToEdit : 1,
	    	        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.sodata.sodata_WasteController',e.getKey());
					}
	    	    })],
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },{
    	    		width : 110,
					text : $i18n.article_no,//商品编码
					dataIndex : 'articleNo',
					cls : 'notnull',
					field : {							
	    	        	id : 'article_noE101',
	    	        	xtype:'bdef_DefArticleCombo',
	    	        	displayField : 'value',
		        		valueField : 'value',
	    	        	store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
	    	        	allowBlank :false	
	    	        } 
	    	    },{
				    width:110,
				    text:$i18n.owner_article_no,//货主商品编码
				    dataIndex:'ownerArticleNo'
	    	    },{
				    width:110,
				    text:$i18n.barcode,//商品条码
				    dataIndex:'barcode'
	    	    },{
				    width:250,
				    text:$i18n.article_name,//商品名称
				    dataIndex:'articleName'
	    	    },{
	    	    	width : 65,
					text : $i18n.packing_qty,//包装数量
					dataIndex : 'packingQty',
					cls : 'notnull',
					field:{
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qtyE101',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
						editable:false,
						allowBlank :false
					}
	    	    },{
				    width:65,
				    text:$i18n.packingUnit,//包装单位
				    dataIndex:'packingUnit',
				    id:'packingUnitE101_2',
	    	    },{
				    width:65,
				    text:$i18n.packingSpec,//规格
				    dataIndex:'packingSpec',
				    id:'packingSpecE101_2',
	    	    },{
				    width:100,
				    text:$i18n.owner_article_no,//业主商品编码
				    hidden:true,
				    dataIndex:'ownerArticleNo'
	    	    },{
					width : 85,
					text : $i18n.planBox,//计划箱数
					dataIndex : 'planBox',
					//hidden:true,
					id:'planBoxE101',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 85,
					text : $i18n.planQmin,//计划中包数
					dataIndex : 'planQmin',
					id:'planQminE101',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 85,
					text : $i18n.planDis,//计划散数
					dataIndex : 'planDis',
					id:'planDisE101',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				}/*,{
					width : 70,
					text:$i18n.plan_check_box,//箱数
					dataIndex : 'poBox',
					cls : 'notnull',
					field : {
    	        		xtype : 'numberfield',
    	        		minValue:1
    	        	}
				},{
				    width:90,
				    text:$i18n.waste_real_qty,//报损箱数
				    hidden:true,
				    dataIndex:'realBox'
	    	    }*/],
	    	    dockedItems : [{
	    	        xtype:'commMenuWidget6',
	    	        region:'north',
	    	        id:'toolbarE101'
	    	    }]
	    	}
	    	]
	    }]
	}]
});