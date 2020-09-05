/**
 * 模块名称：商品与商品群组维护
 * 模块编码：1R01
 * 创建：HKL 
 */
var bdef_ArticleFamilyMStore=Ext.create('cms.store.bdef.bdef_ArticleFamilyMStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1R01').getStore().count()>0){			
				Ext.getCmp('grid_01_1R01').getSelectionModel().select(0);
				
			}
		}
	}
});
 var bdef_DefarticleStore=Ext.create('cms.store.bdef.bdef_ArticleFamilyStore',{autoLoad:true});
 var bdef_ArticleFamilyDStore=Ext.create('cms.store.bdef.bdef_ArticleFamilyDStore');
Ext.define('cms.view.bdef.bdef_ArticleFamilyUI',{
	alias:'widget.bdef_ArticleFamilyUI',
	title:$i18n.title1R01,//商品与商品群组关系维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.bdef_DefArticleCombo',
	          'cms.view.common.remoteCombo'

	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1R01',
	    region:'north'
	},{
	    xtype:'grid',
	    region:'north',
	    height:150,
	    id:'grid_01_1R01',
	    store:bdef_ArticleFamilyMStore,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 120,
			text : $i18n.ownerno,//货主
			dataIndex:'ownerNo'
	    },{
			width : 150,
			text : $i18n.articleFamilyNo,//商品群组编码
			dataIndex:'familyNo'
	    },{
			width : 200,
			text : $i18n.articleFamilyName,//商品群组名称
			dataIndex:'familyName'
	    }]
	},{
		xtype:'panel',
		region:'north',
		height: 30,
		layout: {
		    type: 'table',
	        columns: 3
		},
	    defaults : {
			xtype : 'textfield',
			margin : '3 0 3 3',
			labelAlign:'right',
			allowBlank: true,
			width : 240,
			labelWidth : 120
		},
		items:[{
			xtype : 'bdef_DefOwnerCombo',
			fieldLabel : $i18n.article_class,// 商品类别
			id : 'cmbGroupNo1R01',
			displayField: 'dropValue',
			valueField: 'value',
			queryMode: 'local',
		    store:Ext.create("cms.store.common.comboStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url : 'bdef_ArticleFamilyAction_getArticleGroupNoCombo.action',
			        reader: {
			    		type:'json',
			            root: 'rootList',    
			            totalProperty: 'totalCount'
			        }
			    }
				}).load()
		},{
			fieldLabel : $i18n.article_no_or_ownerAticleNo_or_barcode,//商品编码(内码/条码)
			id:'txtOwnerArticleNo1R01',
			xtype:'remoteCombo',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_ArticleFamilyAction_getArticleNoCombo',
					reader:{
					root:'rootList',
						totalProperty:'totalCount'
					}
				}
		    }).load(),
		    displayField : 'dropValue',
		    valueField : 'value'
		}]
	},{
	
		xtype:'grid',
		id:'grid_02_1R01',
		title:$i18n.article,//商品
		width:'47%',
		region:'west',
		store:bdef_DefarticleStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:85,
			text:$i18n.article_no,//商品代码
			dataIndex:'articleNo',
			sortable: false
		},{
			width:150,
			text:$i18n.owner_article_no,//货主商品代码
			dataIndex:'ownerArticleNo'
		},{
			width:100,
			text:$i18n.article_name,//商品名称
			dataIndex:'articleName'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bdef_DefarticleStore,
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
			id:'right1R01'
		},
		{
			xtype:'button',			
			text:'<<<',
			id:'left1R01'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_1R01',
	    title:$i18n.articleFamily_and_article_list,//商品与商品群租关系列表
	    width:'47%',
	    region:'east',
	    store:bdef_ArticleFamilyDStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:100,
			text:$i18n.articleFamilyNo,//商品群组编码
			dataIndex:'familyNo'
	    },{
			width:120,
			text:$i18n.article_no,//商品编码
			dataIndex:'articleNo'
	    },{
			width:100,
			text:$i18n.owner_article_no,//货主商品代码
			dataIndex:'ownerArticleNo'
		},{
			width:200,
			text:$i18n.article_name,//商品名称
			dataIndex:'articleName'
	    }]
	},{
    	region:'south'
	}]

});