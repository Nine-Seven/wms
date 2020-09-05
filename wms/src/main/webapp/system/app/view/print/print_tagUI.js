/**
 * 补印中心视图
 */

var print_tagStore = Ext.create('cms.store.print.print_tagStore');
Ext.define('cms.view.print.print_tagUI',{
	extend:'Ext.tab.Panel',
	alias:'widget.print_tagUI',
	title:'条码打印',
	id:'print_tagUI',
	requires:['cms.view.common.bdef_DefArticleCombo'],
	
	items:[{
		layout:'border',
		title:'商品条码打印',
		items:[{
	        region: 'north',     
	        xtype: 'form',
	        height: 100,
	        split: true,
	        frame:true,
	        items:[{
	        	layout:'column',
	        	frame:true,
	        	defaults:{
	        		margin: '5 5 5 5'
	        	},
	        	items:[{
					fieldLabel : $i18n.article_no,
					id:'article_no11102',
					xtype:'bdef_DefArticleCombo',
					store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
				    displayField : 'dropValue',
				    valueField : 'value'
				}]
	        },{
	        	xtyle:'form',
	        	id:'repairForm11102',
	        	layout:'column',
	        	frame:true,
	        	defaults:{
	        		margin: '5 5 5 5',
	        		labelWidth:60
	        	},
	        	items:[{
	        		id:'radiogroup11102',
	        		xtype:'radiogroup',
	        		fieldLabel:'格式',
	        		width:250,
	                columns: 1,
	                vertical: true,
	        		items:[{
	        			boxLabel: '条码', name: 'rb',  inputValue: '1',hidden:true
	        		},{
	        			boxLabel: '内码', name: 'rb',  inputValue: '2',hidden:true
	        		},{
	        			boxLabel: '识别码', name: 'rb',  inputValue: '3',checked:true
	        		}]
	        	},{
	        		xtype:'textfield',   
					fieldLabel:'打印份数',   //打印份数
					id:'printNumber11102',
					beforeLabelTextTpl : required
        		},{
	        		xtype:'button',
	        		text:'打印',
	        		name:'printTagPrintBtn'
	        	}]
	        }]
	    },{
	        title: '商品信息',
	        region: 'center',
	        xtype: 'grid',

	        store: print_tagStore,
	        id:'grid11102',
	        columns:[
	         	    {			
	                    xtype : 'rownumberer',
	        		    width : 30
	        	    },
	        	    {
	        		    width:120,
	        		    text :$i18n.owner_no,
	        		    dataIndex:'ownerNo'			
	        	    },
	        	    {
	        		    width:120,
	        		    text :$i18n.article_no,
	        		    dataIndex:'articleNo'			
	        	    },
	        	    {
	        		    width:120,
	        		    text : $i18n.owner_article_no,
	        		    dataIndex:'ownerArticleNo'			
	        	    },
	        	    {
	        		    width:300,
	        		    text : $i18n.article_name,
	        		    dataIndex:'articleName'		
	        	    },
	        	    {
	        		    width:300,
	        		    text : "条码",
	        		    dataIndex:'barcode'		
	        	    },
	        	    {
	        	    	width:300,
	        		    text : "识别码",
	        		    dataIndex:'articleIdentifier'
	        	    	
	        	    }
	        	    ],
	    }]
	}]
});
