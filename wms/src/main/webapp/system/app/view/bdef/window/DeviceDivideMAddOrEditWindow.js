/**
 * 模块名称：设备 window
 * 模块编码：1S01
 * 创建：chensr
 */
 
Ext.define('cms.view.bdef.window.DeviceDivideMAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.DeviceDivideMAddOrEditWindow',
	id : 'DeviceDivideMAddOrEditWindow',
	width : 600,
	height : 400,
	layout:'border',
	modal:true,
	items:[{
		xtype : 'form',
		region : 'center',
		id:'DeviceDivideMForm1S01',
		frame : true,
   		defaults : {
   			xtype : 'textfield',
   			labelWidth : 100,
   			labelAlign:'right'			
   		},
   		items:[
   		{
   			xtype:'fieldset',
   			title:'基本信息',
   			id:'DeviceDivideMF11S01',
   	        layout: {
       	    type: 'table',
       	    columns: 2
       	    },
       	    defaults : {
	       	    xtype : 'textfield',
	       	    labelWidth : 100,
	       	    margin:'5 4 1 4',
	       	    labelAlign:'right'			
       	    },
       	    items:[
       	    {
				xtype:'combo',
				fieldLabel: '设备组',
				id : 'DeviceDivideGroupM1S01',
				store:Ext.create("cms.store.common.comboStore",{
						proxy:{
							type:'ajax',
							method:'post',
							url : 'Divice_DivideAction_getDeviceDivideGroupCombo.action',
					        reader: {
					    		type:'json',
					            root: 'rootList',    
					            totalProperty: 'totalCount'
					        }
					    }
				}).load(),
				displayField : 'dropValue',
			    valueField : 'value',
			    allowBlank: false,
				beforeLabelTextTpl : required
			},{
				fieldLabel: '设备编码',//设备编码
				id : 'deviceNo1S01',
				allowBlank: false,
				beforeLabelTextTpl : required
			},{
				fieldLabel: '设备名称',//设备名称				
				id : 'deviceName1S01',
				//colspan:2,
				//width : 520,
   				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
				fieldLabel: '设备简称',//设备简称
				id : 'deviceAlias1S01'
	        },{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: '状态',//状态
				id : 'statusM1S01',
				store:Ext.create("cms.store.common.comboStore").load({
					params:{str:"DEVICE_DIVIDE_M,STATUS"}
		   		}),
		   		allowBlank: false,
				beforeLabelTextTpl : required
			},{
	   			xtype:'numberfield',
				fieldLabel:'优先级',   //优先级
				id:'grade1S01',
				minValue:1,
				allowBlank: false,
				beforeLabelTextTpl : required	
	        }]
   		},{
   			xtype:'fieldset',
   			title:'基本属性',
   			id:'DeviceDivideMF21S01',
   	        layout: {
	       	    type: 'table',
	       	    columns: 2
       	    },
       	    defaults : {
	       	    xtype : 'textfield',
	  		    labelWidth : 100,
	  		    margin:'5 4 1 4',
	  		    labelAlign:'right'			
       	    },
       	    items:[
       	    {
				xtype:'wms_DefFieldValCombo',
				fieldLabel: '设备类型',//设备类型
				id : 'deviceType1S01',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"DEVICE_DIVIDE_M,DEVICETYPE"}
   				}),
   				allowBlank: false,
				beforeLabelTextTpl : required
	        }, {
				xtype:'wms_DefFieldValCombo',
				fieldLabel: '作业类型',//作业类型
				id : 'operateType1S01',
				store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"DEVICE_DIVIDE_M,OPERATETYPE"}
		   				}),
		   		allowBlank: false,
				beforeLabelTextTpl : required
	        },{
	   			xtype:'numberfield',
				fieldLabel:'最大箱数',   //最大箱数
				id:'maxQty1S01',
				minValue:0,
				allowBlank: false,
				beforeLabelTextTpl : required	
	        },{
	        	xtype:'numberfield',
				fieldLabel:'最大品项数',  //最大品项数
				id:'boxItems1S01',
				minValue:0,
				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
	        	xtype:'numberfield',
				fieldLabel:'使用次数',    //使用次数
				id:'useTimes1S01',
				minValue:0,
				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
	        	xtype:'numberfield',
				fieldLabel:'最大客户数',    
				id:'custQty1S01',
				minValue:0,
				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
	        	fieldLabel: '分播产能（小时）',
				id : 'Produce_Capacity1S01'
	        },{

				xtype:'wms_DefFieldValCombo',
				fieldLabel: '分播产能计算方式',
				id : 'Produce_Capacity_type1S01',
				store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"DEVICE_DIVIDE_M,PRODUCE_CAPACITY_TYPE"}
		   				})
	        },{
	        	fieldLabel: '分播比率%',
				id : 'divice_rate1S01'
	        },{
	        	fieldLabel: '分播策略',
				id : 'STRATEGY_ID1S01'
	        },{

				xtype:'wms_DefFieldValCombo',
				fieldLabel: '是否分播比率配比',
				id : 'REF_RATE_flag1S01',
				store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"ODATA_DIFFERENT_D,ADD_FLAG"}
		   				})
	        }]
   		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidgetM1S01'
	}]
});
