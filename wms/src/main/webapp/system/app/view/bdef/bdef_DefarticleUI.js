/**
 * 商品资料维护
 * 模块编码   商品主档：1401  商品包装:1401_d2   商品条码:1401_d3
 * @author JUN
 * 修改By Panzx
 */
var bdef_DefarticleStore = Ext.create('cms.store.bdef.bdef_DefarticleStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_DefarticleUI',{
	alias:'widget.bdef_DefarticleUI',
	title:$i18n.title1401,//商品资料维护
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires : [
	            'cms.view.common.commMenu2',
	            'cms.view.common.commMenu5',
	            'cms.view.common.wms_DefFieldValCombo',
	            'cms.view.common.bdef_DefOwnerCombo',
	            'cms.view.common.bdef_DefArticleCombo',
	            'cms.view.common.bdef_DefGroupNoCombo',
	            'cms.view.common.bdef_DefSupplierCombo',
	            'cms.view.common.bdef_PackingQtyCombo',
	            'cms.view.common.wms_DefStrategyCombo',
	            'cms.view.common.remoteCombo',
	            'cms.view.common.cdef_DefAreaCombo',
	  			  'cms.view.common.cdef_DefWareCombo',
	  			  'cms.view.common.cdef_DefStockCombo',
	  			  'cms.view.common.wms_DefFieldValCombo',
	  			 // 'cms.view.common.bdef_PackingQtyCombo',
	  			  //'cms.view.common.bdef_DefOwnerCombo',
	  			  'cms.view.common.cdef_DefCellCombo',
	  			 // 'cms.view.common.bdef_DefArticleCombo',
	  			  'cms.view.common.cset_LineCombo'
	            ],
	items:[
    {
    	xtype : 'commMenuWidget2',
	    id:'menu1401',
	    region:'north'
    },
    {
	    xtype:'form',
	    id:'formOwner1401',
	   /* region:'north',
	    right: 0,
		frame : true,*/
	    layout:'column',
		frame : true,
		region : 'north',
		width : '100%',
		height:'8%',
		items:[{
		    layout:{
				type : 'table',
				columns : 5
			},
			xtype:'container',
			defaults:{
				margin : '5 5 5 5',
				labelAlign : 'right',
				xtype:'textfield'
			},
	    items:[{
	   		//xtype:'textfield',
			xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner_no,//货主编号
		    id:'cmbFormOwner1401',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore'),
			displayField : 'dropValue',
			valueField : 'value'
		},
		
		{
 			fieldLabel :$i18n.status, //状态
 			id:'statusText1401',
				xtype:'wms_DefFieldValCombo',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 proxy:{
   					type:'ajax',
    				method:'post',
     				url:'bdef_DefarticleAction_getStatusList',
     				reader:{
   						root:'rootList',
   						totalProperty:'totalCount'
						}
					}
			    }).load()
        },
		
	    {

			xtype : 'remoteCombo',
			fieldLabel : $i18n.article_no_or_barcode,// 商品编码
			id:'identifierOrBarcode1401',
			store : Ext.create("cms.store.idata.idata_PoNoStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_DefarticleAction_getArticleInfo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			})
		
			
	    },{

		 		xtype : 'remoteCombo',
		 		fieldLabel : '商品类别',// 类别编码(模糊查询)
		 		id:'sGroupNo1401',
		 		store : Ext.create("cms.store.idata.idata_PoNoStore",{
		 			proxy:{
		 				type:'ajax',
		 				method:'post',
		 				url:'bdef_ArticleGroupAction_getGroupInfo.action',
		 				reader:{
		 					root:'rootList',
		 					totalProperty:'totalCount'
		 				},
		 				extraParams:{
		 				strFlag : "0"
		 				}
		 			}
		 		})
		 	
		 		
		     },{
			xtype:'button',
			id:'btnSearch1401',
		  	text: '查询'
		}
	    ]}
		]},
    {
    	xtype:'grid',
	    id:'grid_01_1401',
	    region:'center',
	    store:bdef_DefarticleStore,
	    columns:[
	    {			
            xtype : 'rownumberer',
		    width : 30
	    },
	    {
	        width : 120,
		    text : $i18n.owner_no,
		    dataIndex : 'ownerNo'
	    },
	    {
	        width : 120,
		    text : $i18n.owner_name,
		    dataIndex : 'ownerName'
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
		    width:120,
		    text : $i18n.barcode,
		    dataIndex:'barcode'			
	    },
	    {
		    width:300,
		    text : $i18n.article_name,
		    dataIndex:'articleName'			
	    },
	    {
	        width : 100,
		    text : "状态",
		    dataIndex : 'statusText'
	    }
	    ],
	    dockedItems : [
	    {
	        xtype : 'pagingtoolbar',
		    store : bdef_DefarticleStore,
		    dock : 'bottom',
			displayInfo : true
		}
	    ]
    },{
        region:'south'
    }
    ]
});