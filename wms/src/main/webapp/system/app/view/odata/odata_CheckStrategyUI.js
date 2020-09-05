 /**
 * 模块名称：复核策略配置
 * 模块编码：I804
 * 创建：liucl 20160812 
 */   
var odata_CheckStrategyStore=Ext.create('cms.store.odata.odata_CheckStrategyStore',{
	autoLoad:true,                     //调后台是在Store里面
	listeners:{  
		'load':function(th,records,successful,eOpts ){ //Cmp的名字要和controller里面的一致
			if(Ext.getCmp('odata_CheckStrategyGridI804').getStore().count()>0){
				Ext.getCmp('odata_CheckStrategyGridI804').getSelectionModel().select(0);
			}
		}
	}
	});

Ext.define('cms.view.odata.odata_CheckStrategyUI',{
	alias:'widget.odata_CheckStrategyUI',
	title:$i18n.titleI804,
	width:'90%',
	height:'90%',
	title:$i18n.titleI804,//复核策略配置 
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
			id:'menuI804',
			
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
     			fieldLabel : $i18n.checkTypeI804,//策略类型
     			id:'checkTypeUII804',
 				xtype:'wms_DefFieldValCombo',//自定义xtype,继承Ext.form.ComboBox,实现下拉列表，  路径：cms.view.common.wms_DefFieldValCombo
 				forceSelection : false,      //ComboBox的forceSelection属性,允许用户设置任意的文本字段
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{ //好像没什么用
 					 proxy:{
     					type:'ajax',
     					method:'post',
     					url:'Odata_CheckStrategyAction_getCheckTypeForUI',//要改
   						reader:{
     						root:'rootList',
     						totalProperty:'totalCount'
    					}
   					}
   			    }).load(),
   			    displayField : 'dropValue',
			    valueField : 'value'
 			},{
     			fieldLabel :$i18n.checkLevelI804, //复核级别
     			id:'checkLevelUII804',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{///
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'Odata_CheckStrategyAction_getCheckLevelForUI',///
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
				fieldLabel : $i18n.checkStrategyIdAndNameI804 ,// 策略id/名称
				id:'checkStrategyIdAndNameUII804',
				store : Ext.create("cms.store.idata.idata_PoNoStore",{///
					proxy:{
						type:'ajax',
						method:'post',
						url:'Odata_CheckStrategyAction_getCheckStrategyIdAndName.action',//原Odata_CheckStrategyAction_getCheckStrategyIdAndName.action
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
    				}
				}).load()
     		}]
     	}, 
		{  //复核策略表的展示表格
     		xtype : 'grid',
			id : 'odata_CheckStrategyGridI804',
			title : '复核策略表',// 复核策略表
			//height : 200,
			store : odata_CheckStrategyStore, //用的是前面定义的store的变量名
			region:'center',
			columns : [{
						xtype : 'rownumberer',
						width : 30
					}
			      , {
						width : 100,
						text : '复核类型', // 复核类型
						dataIndex : 'checkTypeText' //checkTypeText
					 }
			      , {
						width : 100,
						text : '策略id', // 策略id
						dataIndex : 'checkStrategyId'
					}, {
						width : 150,
						text : '策略名称', // 策略名称
						dataIndex : 'checkStrategyName'
					}, {
						width : 100,
						text : '拣货标识', //拣货标识
						dataIndex : 'skipPickFlagText'   //skipPickFlagText  
					}, {
						width : 100,
						text : '复核级别', //复核级别
						dataIndex : 'checkLevelText'   //checkLevelText  
					}, {
						width : 100,
						text : '自动封箱', //自动封箱
						dataIndex : 'autoCloseBoxText'   //autoCloseBoxText   
					}, {
						width : 100,
						text : '包材标识', //包材标识
						dataIndex : 'packAdvanceText'   //packAdvanceText   
					}, {
						width : 100,
						text : '重量标识', //重量标识
						dataIndex : 'weightFlagText'   //weightFlagText   
					}, {
						width : 100,
						text : '材积标识', //材积标识
						dataIndex : 'volumFlagText'   //volumFlagText   
					}
					],
		dockedItems : [{  //翻页组件
			xtype : 'pagingtoolbar',
			store : odata_CheckStrategyStore,  //用的是前面定义的store的变量名
			dock : 'bottom',
			displayInfo : true
		}] 
    } ]
});