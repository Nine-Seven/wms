/**
 * 模块名称：车辆信息维护
 * 模块编码：1V01
 * 创建：hcx 
 */
var bdef_defcartype = Ext.create('cms.store.bdef.bdef_DefcartypeStore',{autoLoad: true});
var bdef_defcar = Ext.create('cms.store.bdef.bdef_DefcarStore',{autoLoad: true});
Ext.define('cms.view.bdef.bdef_DefCarUI', {
	alias : 'widget.bdef_DefCarUI',
	id:'bdef_DefCarUI',
	title:$i18n.title1V01, //车辆信息维护
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu5',
	            'cms.view.common.bdef_DefOwnerCombo',
	            'cms.view.common.remoteCombo',
			    'cms.view.common.bdef_DefWorkerCombo',
	           	'cms.view.common.wms_DefFieldValCombo'
				],
	items : [
	{
		xtype : 'tabpanel',
	    id:'tabPId1V01',
	    region:'center',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.car_type,//车辆类型
	    	layout:'border',
	    	id:'tabPIdd1_1V01',
	    	itemId:'tabPIdd1_1V01i',
	    	items:[
	    	{
	    	   xtype : 'toolbar',
	     	   region:'north',
	       	   items : [{
	       			    text : $i18n.additem,//新增
	       			    iconCls : 'add',
	       			    id:'add'
	       		    },
	       	        {
	       		    	text : $i18n.titleupdate,//修改
	    			    iconCls : 'edit',
	       			    id:'edit'
	       		    },
	       		    {
	       				text : $i18n.delete_1,//删除
	       				iconCls : 'delete',
	       				id:'delete'
	       			},
	       		    {
	       				text : $i18n.find,//查找
	       				iconCls : 'query',
	       				id:'query'
	       			},{
	       			    text : $i18n.refresh,//刷新
	       			    iconCls : 'refresh',
	       			    id:'refresh'
	       		    }]	
	         },{							//7-9添加
	     	    xtype:'form',
	     	    id:'formDefCar1V01',
	     	   /* region:'north',
	     	    right: 0,
	     		frame : true,*/
	     	    layout:'column',
	     		frame : true,
	     		region : 'north',
	     		width : '100%',
	     		height:'7%',
	     		items:[{
	     		    layout:{
	     				type : 'table',
	     				columns : 4
	     			},
	     			xtype:'container',
	     			defaults:{
	     				margin : '5 5 5 0',
	     				labelAlign : 'right',
	     				xtype:'textfield'
	     			},
	     	    items:[
	     	    {
	     			xtype : 'remoteCombo',
	     			fieldLabel:$i18n.cartype_no,		//车辆类型代码
	     			id:'carTypeNo1V01',
	     			store : Ext.create("cms.store.idata.idata_PoNoStore",{
	     				proxy:{
	     					type:'ajax',
	     					method:'post',
	     					url:'bdef_DefCarAction_querycarTypeNoCombo.action',
	     					reader:{
	     						root:'rootList',
	     						totalProperty:'totalCount'
	     					}
	     				}
	     			})
	     	    },{
	     			xtype : 'remoteCombo',
	     			fieldLabel:$i18n.type_name,		//类型名称
	     			id:'typeName1V01',
	     			store : Ext.create("cms.store.idata.idata_PoNoStore",{
	     				proxy:{
	     					type:'ajax',
	     					method:'post',
	     					url:'bdef_DefCarAction_querycarTypeNameCombo.action',
	     					reader:{
	     						root:'rootList',
	     						totalProperty:'totalCount'
	     					}
	     				}
	     			})
	     	    },{
	     			xtype:'button',
	     			id:'btnSearch1V01',
	     		  	text: '查询'
	     		}
	     	    ]}
	     		]
	     	
	         
	         },{
					xtype : 'grid',
					region:'center',
					id : 'grid_01_1V01',
					width : '100%',
					height : '100%',
					store : bdef_defcartype,
					columns : [ 
					{
						xtype : 'rownumberer',
						width : 30
					},{
						width : 120,
						text : $i18n.cartype_no,//车辆类型代码
						dataIndex : 'cartypeNo' 
					},{
						width : 120,
						text : $i18n.type_name,//类型名称
						dataIndex : 'cartypeName' 
					},{
						width : 120,
						text : $i18n.cartype_weight,//车重量
						dataIndex : 'cartypeWeight' 
					},{
						width : 120,
						text : $i18n.cartype_length,//车长度
						dataIndex : 'cartypeLength' 
					},{
						width : 120,
						text : $i18n.cartype_width,//车宽度
						dataIndex : 'cartypeWidth' 
					},{
						width : 120,
						text : $i18n.cartype_height,//车高度
						dataIndex : 'cartypeHeight' 
					},{
						width : 120,
						text : $i18n.max_layer,//最大载重
						dataIndex : 'maxLayer' 
					}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
						store : bdef_defcartype,
						dock : 'bottom',
						displayInfo : true
					}]
	         }]
	    },
	    {
	    	title:$i18n.car_info,//车辆信息
	    	layout:'border',
	    	id:'tabPIdd2_1V01',
	    	itemId:'tabPIdd2_1V01i',
	    	items:[
	    	{
	        	xtype : 'toolbar',
	     	    region:'north',
	       	   items : [{
	       			    text : $i18n.additem,//新增
	       			    iconCls : 'add',
	       			    id:'detailAdd'
	       		    },
	       	        {
	       		    	text : $i18n.titleupdate,//修改
	    			    iconCls : 'edit',
	       			    id:'detailEdit'
	       		    },
	       		    {
	       				text : $i18n.delete_1,//删除
	       				iconCls : 'delete',
	       				id:'detailDelete'
	       			},
	       		    {
	       				text : $i18n.find,//查找
	       				iconCls : 'query',
	       				id:'detailQuery'
	       			},{
	       			    text : $i18n.refresh,//刷新
	       			    iconCls : 'refresh',
	       			    id:'detailRefresh'
	       		    }]	
	         },{							//7-9添加
		     	    xtype:'form',
		     	    id:'formDefCar1V012',
		     	   /* region:'north',
		     	    right: 0,
		     		frame : true,*/
		     	    layout:'column',
		     		frame : true,
		     		region : 'north',
		     		width : '100%',
		     		height:'7%',
		     		items:[{
		     		    layout:{
		     				type : 'table',
		     				columns : 5
		     			},
		     			xtype:'container',
		     			defaults:{
		     				margin : '2 2 2 0',
		     				labelAlign : 'right',
		     				xtype:'textfield'
		     			},
		     	    items:[
		     	    {
		     			xtype : 'remoteCombo',
		     			fieldLabel:$i18n.cartype_no,		//车辆类型代码
		     			id:'carTypeNo1V012',
		     			store : Ext.create("cms.store.idata.idata_PoNoStore",{
		     				proxy:{
		     					type:'ajax',
		     					method:'post',
		     					url:'bdef_DefCarAction_querycarTypeNoCombo.action',
		     					reader:{
		     						root:'rootList',
		     						totalProperty:'totalCount'
		     					}
		     				}
		     			})
		     	    },{
		     			xtype : 'remoteCombo',
		     			fieldLabel:$i18n.car_name,		//车辆名称
		     			id:'carName1V011',
		     			store : Ext.create("cms.store.idata.idata_PoNoStore",{
		     				proxy:{
		     					type:'ajax',
		     					method:'post',
		     					url:'bdef_DefCarAction_querycarNameCombo.action',
		     					reader:{
		     						root:'rootList',
		     						totalProperty:'totalCount'
		     					}
		     				}
		     			})
		     	    },{
		     			xtype : 'remoteCombo',
		     			fieldLabel:$i18n.car_plate,		//车牌号
		     			id:'carPlate1V011',
		     			store : Ext.create("cms.store.idata.idata_PoNoStore",{
		     				proxy:{
		     					type:'ajax',
		     					method:'post',		
		     					url:'bdef_DefCarAction_querycarPlateCombo.action',
		     					reader:{
		     						root:'rootList',
		     						totalProperty:'totalCount'
		     					}
		     				}
		     			})
		     	    },{
		     			xtype : 'remoteCombo',
		     			fieldLabel:$i18n.deiver_worker,		//司机
		     			id:'deiverWorker1V011',
		     			store : Ext.create("cms.store.idata.idata_PoNoStore",{
		     				proxy:{
		     					type:'ajax',
		     					method:'post',
		     					url:'bdef_DefCarAction_querydeiverWorkerCombo.action',
		     					reader:{
		     						root:'rootList',
		     						totalProperty:'totalCount'
		     					}
		     				}
		     			})
		     	    },{
		     			xtype:'button',
		     			id:'btnSearch1V012',
		     		  	text: '查询'
		     		}
		     	    ]}
		     		]
		     	
		         
		         },{
					xtype : 'grid',
					region:'center',
					id : 'grid_02_1V01',
					width : '100%',
					height : '100%',
					store : bdef_defcar,
					columns : [ 
					{
						xtype : 'rownumberer',
						width : 30
					},{
						width : 90,
						text : $i18n.cartype_no,//车辆类型
						dataIndex : 'cartypeNo' 
					},{
						width : 120,
						text : $i18n.car_no,//车辆代码
						dataIndex : 'carNo' 
					},{
						width : 120,
						text : $i18n.car_name,//车辆名称
						dataIndex : 'carName' 
					},{
						width : 120,
						text : $i18n.car_plate,//车牌号
						dataIndex : 'carPlate' 
					},{
						width : 120,
						text : $i18n.oil_consume,//百公里油耗
						dataIndex : 'oilConsume' 
					},{
						width : 120,
						text : $i18n.care_mile,//保养里程数
						dataIndex : 'careMile' 
					},{
						width : 120,
						text : $i18n.mile,//里程数
						dataIndex : 'mile' 
					},{
						width : 120,
						text : $i18n.care_date,//保养日期
						dataIndex : 'careDateText' 
					},{
						width : 120,
						text : $i18n.care_worker,//保养人
						dataIndex : 'careWorker' 
					},{
						width : 120,
						text : $i18n.sanpl_no,//承运商
						dataIndex : 'sanplNo' 
					},{
						width : 120,
						text : $i18n.car_type,//车辆类型
						dataIndex : 'carClassText' 
					},{
						width : 120,
						text : $i18n.driver_worker,//司机
						dataIndex : 'driverWorkerText' 
					},{
						width : 120,
						text : $i18n.manage_status,//状态
						dataIndex : 'strStatus' 
					}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
						store : bdef_defcar,
						dock : 'bottom',
						displayInfo : true
					}]
	         }]
	    }
	    ]
	}]
});