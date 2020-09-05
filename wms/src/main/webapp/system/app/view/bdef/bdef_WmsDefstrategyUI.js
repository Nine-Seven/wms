/**
 * 模块名称：策略配置
 * 模块编码：I101
 * 创建：hcx 
 */
var wms_defstrategyStore=Ext.create('cms.store.bdef.bdef_WmsDefstrategyStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('wms_defstrategyGridI101').getStore().count()>0){
				Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().select(0);
			}else{
				Ext.getCmp('wms_defstrategyDGridI101').getStore()
				.removeAll();
			}
		}
	}
	});
var wms_defstrategyDStore = Ext.create('cms.store.bdef.bdef_WmsDefstrategyDStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			var hideFlag=[];
			if(th.count()>0){
	   			 hideFlag=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsDefstrategyController').getHideFlag();
	   			 var ruleOrderFlag=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsDefstrategyController').getRuleOrderFlag();
			}
			if(hideFlag.length!=0){
				for(var i=0 ; i<th.count();i++){
					var record  = Ext.getCmp('wms_defstrategyDGridI101').getStore().getAt(i);
					if(hideFlag[0]==record.get('strategyType') 
							&& hideFlag[1]==record.get('strategyId')
							&& hideFlag[2]==record.get('ruleId')
							&& ruleOrderFlag==record.get('ruleOrder')){
						Ext.getCmp('wms_defstrategyDGridI101').getSelectionModel().select(i);
						return;
					}				
				}
			}
		}
	}
});
Ext.define('cms.view.bdef.bdef_WmsDefstrategyUI',{
	alias:'widget.bdef_WmsDefstrategyUI',
	title:$i18n.titleI101,
	width:'90%',
	height:'90%',
	title:$i18n.titleI101,//策略配置 
	layout:'border',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.commMenu5',
			'cms.view.common.commMenu6',
			'cms.view.common.cdef_DefStockCombo',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.bdef_DefDockCombo',
	        'cms.view.common.remoteCombo'
			],
	extend:'Ext.panel.Panel',
	items:[ 	//新增、修改、删除、查找、刷新、关闭   引用的公司的菜单栏组件
		{
			xtype : 'commMenuWidget2',
			region:'north',
			id:'menuI101'
		},{
     		xtype:'panel',
     		region:'north',
     		height: 35,
     		layout: {
   			    type: 'table',
    	        columns: 3
     		},
   		    defaults : {
     			xtype : 'textfield',
     			margin : '3 3 3 3',
     			labelAlign:'right',
   				allowBlank: true,
				width : 280,
   				labelWidth : 90
				},
   			items:[{  //三个搜索框
     			fieldLabel : $i18n.strategyType,//策略类型
     			id:'strategyTypeUII101',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
 					 proxy:{
     					type:'ajax',
     					method:'post',
     					url:'bdef_WmsDefstrategyAction_getStrategyTypeForUI',
   						reader:{
     						root:'rootList',
     						totalProperty:'totalCount'
    					}
   					}
   			    }).load(),
   			    displayField : 'dropValue',
			    valueField : 'value'
 			},{
     			fieldLabel :$i18n.defaultFlag, //默认标识
     			id:'defaultFlagUII101',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'bdef_WmsDefstrategyAction_getDefaultFlagForUI',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    }).load(),
   			    displayField : 'dropValue',
   			    valueField : 'value'
     		},{
				xtype : 'remoteCombo',
				fieldLabel : $i18n.strategyIdOrName,// 策略id/名称
				id:'strategyIdOrNameI101',
				store : Ext.create("cms.store.idata.idata_PoNoStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_WmsDefstrategyAction_getStrategyIdOrName.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
    				}
				}).load()
     		}]
     	},
		{  //头表的展示表格
    	xtype : 'grid',
		id : 'wms_defstrategyGridI101',
		store:wms_defstrategyStore,
		region:'center',
		columns : [ {
			xtype : 'rownumberer',
			width : 30
		},{
			width : 100,
			text : $i18n.strategyType,//策略类型
			dataIndex : 'strategyType'
		},{
			width : 100,
			text : $i18n.strategy_id,//策略id
			dataIndex : 'strategyId'
		},{
			width : 250,
			text : $i18n.strategyTypeName,//策略名称
			dataIndex : 'strategyName'
		},{
			width : 100,
			text : $i18n.defaultFlag,//默认标识
			dataIndex : 'defaultFlagText'
		}],
		dockedItems : [{  //翻页组件
			xtype : 'pagingtoolbar',
			store : wms_defstrategyStore,
			dock : 'bottom',
			displayInfo : true
		}] 
    },
    {
    	height : 250,
    	region:'south',
    	layout:'border',
    	items:[  //新增、删除、修改、上移、下移 的工具栏，引用的公司共用菜单组件commMenuWidget6
			{
			    xtype:'commMenuWidget6',
			    id:'menu_I101_2',
			    region:'north',
			    items : [ {
				    	   text : $i18n.additem,
				    	   name : 'detailAdd',
				    	   iconCls : 'add'//新增
				       }, {
				    	   text : $i18n.delete_1,
				    	   name : 'detailDelete',
				    	   iconCls : 'delete'//删除
				       }, {
				    	   text : $i18n.titleupdate,
				    	   name : 'detailEdit',
				    	   iconCls : 'edit' //修改
				       },{
				   		   text : '上移',
						   iconCls : 'prev',
						   name : 'prevButton'
					   },{
						   text : '下移',
						   iconCls : 'next',
						  name : 'nextButton'
					   }]
			},
			{//明细的表格内容展示
				xtype : 'grid',
				region:'center',
				store:wms_defstrategyDStore,
				id : 'wms_defstrategyDGridI101',
				columns : [ {
					xtype : 'rownumberer',
					width : 20
				},{
					width : 80,
					text : $i18n.ruleId,//规则id
					dataIndex : 'ruleId'
				},{
					width : 150,
					text : '规则名称',//规则名称
					dataIndex : 'ruleName'
				},{
					width : 80,
					text : $i18n.ruleOrder,//规则顺序
					dataIndex : 'ruleOrder'
				},{
					width : 100,
					text : $i18n.limmit_mixbatch,//混批属性
					dataIndex : 'limmitMixbatchText'
				},{
					width : 100,
					text : $i18n.limmit_mixarticle,//混商品
					dataIndex : 'limmitMixarticleText'
				},{
					width : 100,
					text : $i18n.limmit_maxqty,//最大限制量
					dataIndex : 'limmitMaxqtyText'
				},{
					width : 100,
					text : $i18n.limmit_maxcase,//最大箱数
					dataIndex : 'limmitMaxcaseText'
				},{
					width : 100,
					text : $i18n.limmit_maxweight,//最大重量
					dataIndex : 'limmitMaxweightText'
				},{
					width : 120,
					text : $i18n.limmit_maxgroupno,//最大品类数
					dataIndex : 'limmitMaxgroupnoText'
				},{
					width : 100,
					text : $i18n.limmit_celluse,//可用货位数
					dataIndex : 'limmitCelluseText'
				},{
					width : 1000,
					text : $i18n.transform_des,//备注
					dataIndex : 'memo'
				},{
					width : 100,
					text : $i18n.limmit_rsv01,//预留限制1
					hidden:true,
					dataIndex : 'limmitRsv01Text'
				},{
					width : 100,
					text : $i18n.limmit_rsv02,//预留限制2
					hidden:true,
					dataIndex : 'limmitRsv02Text'
				},{
					width : 80,
					text : $i18n.limmit_rsv03,//预留限制3
					hidden:true,
					dataIndex : 'limmitRsv03Text'
				},{
					width : 100,
					text : $i18n.limmit_rsv04,//预留限制4
					hidden:true,
					dataIndex : 'limmitRsv04Text'
				},{
					width : 100,
					text : $i18n.limmit_rsv05,//预留限制5
					hidden:true,
					dataIndex : 'limmitRsv05Text'
				},{
					width : 100,
					text : $i18n.limmit_rsv06,//预留限制6
					hidden:true,
					dataIndex : 'limmitRsv06Text'
				}]
			}
        ]
    }]
});