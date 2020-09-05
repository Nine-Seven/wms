/**
 * 色码维护
 */
var codeGroup=Ext.create('cms.store.bdef.bdef_CodeGroupStore',{autoLoad:true});
var haveCode=Ext.create('cms.store.bdef.bdef_CodeDistributionStore',{autoLoad:true});
var haveNotCode=Ext.create('cms.store.bdef.bdef_CodeNotDistributionStore',{autoLoad:true});
 
 
 
 
 
Ext.define('cms.view.bdef.bdef_ColourCodeUI',{
	alias:'widget.bdef_ColourCodeUI',
	title:'色码组维护',
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1Q01',
	    region:'north'
	},{
	    xtype:'grid',
	    region:'north',
	    height:240,
	    id:'grid_01_1G01',
	   store:codeGroup,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 120,
			text : '色码组编码',//仓别
			dataIndex:'codeGroup'
	    },{
			width : 150,
			text : '色码名称',//打印机群组代码
			dataIndex:'codeName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:codeGroup,
			displayInfo : true
	    }]
	},{
		xtype:'grid',
		id:'grid_02_1G01',
		title:'未分配色码',
		width:'47%',
		region:'west',
		store:haveNotCode,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:85,
			text:'色码',//打印机代码
			dataIndex:'codeName',
			sortable: false
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:haveNotCode,
			displayInfo : true
		}]
	},{
		xtype : 'form',
		region : 'center',
		layout:{
			type:'table',
			columns:1
		},
		width:'6%',
		frame : true,
		defaults:{
			margin:'10 0 0 8'
		},
		items : [{
			xtype:'button',
			margin:'80 0 0 8',
			text:'>>>',
			id:'right1G01'
		},
		{
			xtype:'button',			
			text:'<<<',
			id:'left1G01'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_1G01',
	    title:'已分配色码',//打印机群组列表
	    width:'47%',
	    region:'east',
	    store:haveCode,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:'色码',//打印机群组代码
			dataIndex:'codeName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:haveCode,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});