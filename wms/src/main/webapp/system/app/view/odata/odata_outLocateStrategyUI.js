/**
 * 模块名称：出货定位策略配置
 * 模块编码：3910
 * 创建：liucl 20160728 
 */                                                                 
var wms_defstrategyStore=Ext.create('cms.store.odata.odata_outLocateStrategyMStore',{
	autoLoad:true,                     //调后台是在MStore里面
	listeners:{  
		'load':function(th,records,successful,eOpts ){ //Cmp的名字要和controller里面的一致
			if(Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().count()>0){
				Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().select(0);
			}else{
				Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore()
				.removeAll();
			}
		}
	}
	});
var wms_defstrategyDStore = Ext.create('cms.store.odata.odata_outLocateStrategyDStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			var hideFlag=[];
			if(th.count()>0){
	   			 hideFlag=_myAppGlobal.getController('cms.controller.odata.odata_outLocateStrategyController').getHideFlag();
	   			 var ruleOrderFlag=_myAppGlobal.getController('cms.controller.odata.odata_outLocateStrategyController').getRuleOrderFlag();
			}
			if(hideFlag.length!=0){
				for(var i=0 ; i<th.count();i++){
					var record  = Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore().getAt(i);
					if(hideFlag[0]==record.get('strategyType') 
							&& hideFlag[1]==record.get('strategyId')    //这一块需要修改
							&& hideFlag[2]==record.get('ruleId')
							&& ruleOrderFlag==record.get('ruleOrder')){
						Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getSelectionModel().select(i);
						return;
					}				
				}
			}
		}
	}
});

Ext.define('cms.view.odata.odata_outLocateStrategyUI',{
	alias:'widget.odata_outLocateStrategyUI',
	title:$i18n.titleI802,
	width:'90%',
	height:'90%',
	title:$i18n.titleI802,//出货定位策略配置 
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
			id:'menuI802',
			
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
     			fieldLabel : $i18n.strategyNameI802,//策略名称
     			id:'strategyNameUII802',
 				xtype:'wms_DefFieldValCombo',//自定义xtype,继承Ext.form.ComboBox,实现下拉列表，  路径：cms.view.common.wms_DefFieldValCombo
 				forceSelection : false,      //ComboBox的forceSelection属性,允许用户设置任意的文本字段
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{ //好像没什么用
 					 proxy:{
     					type:'ajax',
     					method:'post',
     					url:'Odata_outLocateStrategyAction_getStrategyNameForUI',///
   						reader:{
     						root:'rootList',
     						totalProperty:'totalCount'
    					}
   					}
   			    }).load(),
   			    displayField : 'dropValue',
			    valueField : 'value'
 			},{
     			fieldLabel :$i18n.DefaultFlagI802, //默认标识
     			id:'defaultFlagUII802',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{///
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'Odata_outLocateStrategyAction_getDefaultFlagForUI',///
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
				fieldLabel : $i18n.locateStrategyId ,// 定位策略ID
				id:'locateStrategyIdUII802',
				store : Ext.create("cms.store.idata.idata_PoNoStore",{///
					proxy:{
						type:'ajax',
						method:'post',
						url:'Odata_outLocateStrategyAction_getStrategyId.action',///
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
			id : 'odata_WmsOutlocateStrategyGridI802',
			title : '策略头档',// 策略头档
			//height : 200,
			store : wms_defstrategyStore, //用的是前面定义的store的变量名
			region:'center',
			columns : [{
						xtype : 'rownumberer',
						width : 30
					}
			     // , {
				//		width : 100,
				//		text : '企业编号', // 企业编号
				//		dataIndex : 'enterpriseNo'
				//	 }
			      , {
						width : 100,
						text : '定位策略id', // 定位策略id
						dataIndex : 'locateStrategyId'
					}, {
						width : 150,
						text : '策略名称', // 规则名称
						dataIndex : 'strategyName'
					}, {
						width : 100,
						text : '默认标识', // 默认标识
						dataIndex : 'defalutFlagText'   //defalutFlag,显示的是0/1   
					}],
		dockedItems : [{  //翻页组件
			xtype : 'pagingtoolbar',
			store : wms_defstrategyStore,  //用的是前面定义的store的变量名
			dock : 'bottom',
			displayInfo : true
		}] 
    }   ,
    {
    	height : 250,
    	region:'south',
    	layout:'border',
    	items:[  //新增、删除、修改、上移、下移 的工具栏，引用的公司共用菜单组件commMenuWidget6
			{
			    xtype:'commMenuWidget6',
			    id:'menu_I802_2',
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
				       }
				       //,{
				   		//   text : '上移',
						//   iconCls : 'prev',
						//   name : 'prevButton'
					   //},{
						//   text : '下移',
						//   iconCls : 'next',
						//  name : 'nextButton'
					  // }
					   ]
			},
			{//明细的表格内容展示
				xtype : 'grid',
				region:'center',
				id : 'odata_WmsOutlocateStrategyDGridI802',
				title : '策略明细',// 策略明细
				height : 270,
				store : wms_defstrategyDStore ,     //引入store的时候，在UI前面有重新定义名字
				//selModel : {
				//	selType : 'cellmodel'
				//},
				columns : [{
							xtype : 'rownumberer',
							width : 20
						}
				      //, {
				      //	width : 100,
					  //	text : '企业编号', // 企业编号
					  //	dataIndex : 'enterpriseNo'
					   //  }
				       , {
							width : 90,
							text : '定位策略id', // 定位策略id
							//id : 'locateStrategyIdI802_1',
							dataIndex : 'locateStrategyId'
						}, {
							width : 90,
							text : '规则id', // 规则id
							//id : 'locateRuleIdI802',
							dataIndex : 'locateRuleId'
						}, {
							width : 90,
							text : '规则名称', // 规则名称
							//id : 'locateRuleNameI802',   //batchRuleNameI802
							dataIndex : 'locateRuleName'
						}, {
							width : 120,
							text : '配送对象级别 ', // 配送对象级别
							//id : 'deliverObjLevelTextI802',
							dataIndex : 'deliverObjLevelText'
						}, {
							width : 120,
							text : 'P型定位 ', // P型定位
							//id : 'PFlagTextI802',
							dataIndex : 'PFlagText'
						}, {
							width : 120,
							text : 'M型定位 ', // M型定位
							//id : 'MFlagTextI802',
							dataIndex : 'MFlagText'
						}, {
							width : 120,
							text : 'W型定位 ', // W型定位
							//id : 'WFlagTextI802',
							dataIndex : 'WFlagText'
						}, {
							width : 120,
							text : 'S型定位 ', // S型定位
							//id : 'SFlagTextI802',
							dataIndex : 'SFlagText'
						}, {
							width : 120,
							text : 'D型定位 ', // D型定位
							//id : 'DFlagTextI802',
							dataIndex : 'DFlagText'
						}, 
						/*{
							width : 120,
							text : 'B型拣货方式 ', // B型拣货方式
							//id : 'BDivideFlagTextI802',
							dataIndex : 'BDivideFlagText'
						}, {
							width : 120,
							text : 'C型拣货方式 ', // C型拣货方式
							//id : 'CDivideFlagTextI802',
							dataIndex : 'CDivideFlagText'
						},*/
						{
							width : 120,
							text : '出货定位提交方式 ', // 出货定位提交方式
							//id : 'commitTypeTextI802',
							dataIndex : 'commitTypeText'
						}, {
							width : 120,
							text : '出货定位缺量处理方式 ', // 出货定位缺量处理方式
							//id : 'shortqtyTypeTextI802',
							dataIndex : 'shortqtyTypeText'
						}]
			} 
        ]
    }]
});