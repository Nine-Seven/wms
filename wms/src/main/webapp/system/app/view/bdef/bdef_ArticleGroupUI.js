/**
 * 模块名称：商品类别维护
 * 模块编码：1301
 * 创建：周欢
 */
var bdef_ArticleGroupStore = Ext.create('cms.store.bdef.bdef_ArticleGroupStore',{autoLoad:false});
Ext.define('cms.view.bdef.bdef_ArticleGroupUI',{
	alias:'widget.bdef_ArticleGroupUI',
	title:$i18n.title_1301,//商品类别维护
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.wms_DefStrategyCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1301',
	    region:'north'
    },{
	    xtype:'form',
	    id:'formOwner1301',
	    region:'north',
	    height:35,
		frame : true,
	    layout:{
   			type:'table',
   			columns:5
	    },
	    xtype:'container',
		defaults:{
			margin : '5 5 5 5',
			labelAlign : 'right',
			xtype:'textfield'
		},
	    items:[
	   			{
				    xtype:'bdef_DefOwnerCombo',
				    fieldLabel:$i18n.owner_no,//货主编号
				    id:'cmbFormOwner1301',
				   
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbFormOwner1301').setValue(th.getAt(0).data.value);								
									_myAppGlobal.getController('cms.controller.bdef.bdef_ArticleGroupController').cmbFormOwner1301Select();
									//_myAppGlobal.getController('cms.controller.bdef.bdef_ArticleGroupController').cmbOwnerNo1301Select();
								}
							}
						}
					}).load(),
				    displayField : 'dropValue',
				    valueField : 'value',
				    allowBlank : false,
				    beforeLabelTextTpl : required
				}/*,{

       		 		xtype : 'remoteCombo',
       		 		fieldLabel : $i18n.l_group_no,// 大类编码(模糊查询)
       		 		id:'lGroupNo1301',
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
       		 				strFlag : "2"
       		 				}
       		 			}
       		 		})
       		 	
       		 		
       		     },{

        		 		xtype : 'remoteCombo',
        		 		fieldLabel : $i18n.m_group_no,// 中类编码(模糊查询)
        		 		id:'mGroupNo1301',
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
        		 				strFlag : "1"
        		 				}
        		 			}
        		 		})
        		 	
        		 		
        		     },{

         		 		xtype : 'remoteCombo',
         		 		fieldLabel : $i18n.s_group_no,// 小类编码(模糊查询)
         		 		id:'sGroupNo1301',
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
         				id:'btnSearch1301',
         			  	text: '查询'
         			}*/]
    },{
    	xtype : 'treepanel',
		split : true,
		width : '25%',
		height : '100%',
		frame : true,
		id : 'moduleTree1301',
		store : Ext.create("cms.store.bdef.bdef_ArticleGroupTreeStore",{autoLoad: false}),
		region:'west'
	},{
	    xtype:'grid',
	    id:'grid_1301_01',
	    region:'center',
	    store:bdef_ArticleGroupStore,
	    columns:[{			
	        xtype : 'rownumberer',
		    width : 30
	    },{
		    width:120,
		    text : $i18n.group_no,//类别编码
		    dataIndex:'groupNo'			
	    },{
		    width:120,
		    text : $i18n.group_name,//类别名称
		    dataIndex:'groupName'			
	    },{
	    	width:100,
	    	text: $i18n.group_level,//类别级别
	    	dataIndex:'grouplevelText'
	    },{
		    width:120,
		    text : $i18n.m_group_no,//商品中类代码
		    dataIndex:'MGroupNo'			
	    },{
		    width:120,
		    text : $i18n.m_group_name,//商品中类名称
		    dataIndex:'MGroupName'			
	    },{
		    width : 120,
		    text : $i18n.l_group_no,//商品大类代码
		    dataIndex : 'LGroupNo'
	    },{
		    width:120,
		    text : $i18n.l_group_name,//商品大类名称
		    dataIndex:'LGroupName'			
	    },{
		    width:120,
		    text : $i18n.i_strategy,//上架策略
		    dataIndex:'iText'			
	    },{
		    width:120,
		    text : $i18n.o_strategy,//下架策略
		    dataIndex:'oText'			
	    },{
		    width:120,
		    text : $i18n.m_strategy,//补货策略
		    dataIndex:'mText'			
	    },{
		    width:120,
		    text : $i18n.ri_strategy,//返配策略
		    dataIndex:'riText'			
	    },{
		    width:120,
		    text : $i18n.ro_strategy,//退货策略
		    dataIndex:'roText'			
	    },{
		    width:120,
		    text : $i18n.fc_strategy,//盘点策略
		    dataIndex:'fcText'			
	    }],
	    
    dockedItems : [
           	    {
           	        xtype : 'pagingtoolbar',
           	        store:bdef_ArticleGroupStore,
           		    dock : 'bottom',
           			displayInfo : true
           		}
           	    ]
	},{
		region:'south'
	}]
});