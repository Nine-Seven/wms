/**
 * 模块名称：条码信息采集
 * 模块编码：1O01
 * 创建：lich 
 */
var bset_Article_Barcode_MStore = Ext.create('cms.store.bset.bset_Article_Barcode_MStore',{autoLoad: true});
Ext.define('cms.view.bset.bset_Article_BarcodeUI', {
	alias : 'widget.bset_Article_BarcodeUI',
	id:'bset_Article_BarcodeUI',
	title:'条码信息采集', //条码信息采集
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu',
				'cms.view.common.commMenu3',
	           	'cms.view.common.commMenu6',
	           	'cms.view.common.bdef_DefArticleCombo',
	            'cms.view.common.bdef_DefOwnerCombo'
				],
	items : [
	{
		xtype : 'tabpanel',
	    region:'center',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	id:'tabPId1O01',
	    	items:[
	    	{
	    		xtype : 'commMenuWidget3',
	    		items: [{
					text : $i18n.find,//查找
					iconCls : 'query',
					name : 'detailQuery'
				}],
	    		region:'north'
	    	},{
				xtype : 'grid',
				region:'center',
				id : 'grid_01_1O01',
				width : '100%',
				height : '100%',
				store : bset_Article_Barcode_MStore,
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
					width:150,
					text:$i18n.task_no,//单据类型
					dataIndex:'paperNo'
				},{
					width : 135,
					text : $i18n.serialno,//采购单号
					dataIndex : 'serialNo'
				},{
					width : 80,
					text : $i18n.status,//状态	
					dataIndex : 'statusText'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : bset_Article_Barcode_MStore,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	id:'tabPIdd2_1O01',
	    	items:[
	    	{
	    	   	xtype:'commMenuWidget',
	    	   	id:'menu1O01',
	    	   	region:'north'
	        },{
	        	xtype : 'form',
			    id : 'form_01_1O01',
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
					fieldLabel : $i18n.serialno,//流水号
					id : 'serialNo1O01',	
					readOnly:true,
					beforeLabelTextTpl : required
			    },{
					xtype : 'textfield',
					fieldLabel : $i18n.task_no,// 任务单号
					id : 'task_no1O01',	 
					readOnly:true,
					maxLength:20,
					beforeLabelTextTpl : required	
			    },{
		        	xtype:'bdef_DefOwnerCombo',
	    			fieldLabel : $i18n.owner_no,//货主编号
	    			id:'owner_no1O01',
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'bdef_DefOwnerAction_queryOwnerCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
					}).load(),
	    	        readOnly:true,
	    	        allowBlank : false,
	    	        beforeLabelTextTpl : required
				}]
	        },{			
				xtype : 'grid',
				id : 'grid_02_1O01',//
				region:'center',
				height : 300,
				loadMask : true, // 加载时有加载的图标
				store : Ext.create('cms.store.bset.bset_Article_Barcode_DStore'),
				selModel : {
					selType : 'cellmodel'
				},
				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1,
					onSpecialKey:function(ed,field,e){
					commEnterGridStatEdit(this.grid,true,'cms.controller.bset.bset_Article_BarcodeController',e.getKey());
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
    	        		id : 'article_no1O01',
    	        		xtype:'bdef_DefArticleCombo',
    	        		displayField : 'value',
	        			valueField : 'value',
    	        		store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore",{
    	    				proxy:{
    							type:'ajax',
    							method:'post',
    							url:'bset_Article_BarcodeAction_getArticle.action',
    							reader:{
    								root:'rootList',
    								totalProperty:'totalCount'
    							}
    	    				}
    					
    	        		}),
    	        		allowBlank :false
    	           }
				},{
					width : 220,
					text : $i18n.article_name,//商品名称
					dataIndex : 'articleName'
				}],
			    	dockedItems : [{
					xtype:'commMenuWidget6',
		    		region:'north',
		    		id:'toolbar1O01'
			    }]
	},{
		xtype : 'panel',
		id : 'msterForm1O01',
		region:'south',
		html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
				+ 'background-color:#C1D5ED; text-align: left;">'
				+ '<span><label>新增人:</label><label id="rgstName1O01"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;新增日期：</label><label id="rgstDate1O01"></label></span>'
				+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName1O01"></label> </span> '
					+ '<span><label>&nbsp;&nbsp;&nbsp;修改日期：</label><label id="updtDate1O01"></label> </span></div>'
		}]
	    }]
	}]
});