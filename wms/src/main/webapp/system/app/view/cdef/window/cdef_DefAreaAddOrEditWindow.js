/**
 * 新增、修改、浏览储区界面
 * 周欢
 */
 
 var arr = new Array();
Ext.define('cms.view.cdef.window.cdef_DefAreaAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cdef_DefAreaAddOrEditWindow',
	id : 'cdef_DefAreaAddOrEditWindow',
	width : 850,
	height : 600,
	layout:'border',
	modal:true,
	items:[{
		xtype : 'form',
		region : 'center',
		id:'cdef_DefAreaAddOrEditForm',
		frame : true,
   		defaults : {
   			xtype : 'textfield',
   			labelWidth : 100,
   			labelAlign:'right'			
   		},
   		items:[
   		{
   			xtype:'fieldset',
   			id:'areaId2101',
   	        layout: {
       	    type: 'table',
       	    columns: 3
       	    },
       	    defaults : {
	       	    xtype : 'textfield',
	       	    labelWidth : 100,
	       	    margin:'5 4 1 4',
	       	    labelAlign:'right'			
       	    },
       	    items:[
       	    {
				xtype:'wms_DefFieldValCombo',//库区
				fieldLabel: $i18n.ware_no,
				id : 'd2_ware_no2101',
				store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
            		proxy:{
     					type:'ajax',
     					method:'post',
     					url:'cdef_DefWareAction_getCdef_DefWareComboforWindow',
     					reader:{
     						root:'rootList',
     						totalProperty:'totalCount'
     					}
     				}	     	    
				}).load(),
				displayField : 'dropValue',
	     	    valueField : 'value',
	     	    allowBlank : false,
	     	    beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.area_no,//储区代码
				id : 'd2_area_no2101',
				allowBlank: false,
				maxLength:5,
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.area_name,//储区名称
				id : 'd2_area_name2101',
				allowBlank: false,
				maxLength:30,
				beforeLabelTextTpl : required
			},{
				xtype:'numberfield',
				fieldLabel: $i18n.floor,//楼层
				id : 'd2_floor2101',
				minValue:0,
   				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.a_flag,//是否A类储区
				id : 'd2_a_flag2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,A_FLAG"}
   				}),
   				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
				xtype:'wms_DefFieldValCombo',//储区品质
				fieldLabel: $i18n.area_quality,
				id : 'd2_area_quality2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CDEF_DEFAREA,AREA_QUALITY"}
   				}),
   				allowBlank: false,
				beforeLabelTextTpl : required
	          }]
   		},{
   			xtype:'fieldset',
   			id:'useAttrType2101',
   	        layout: {
	       	    type: 'table',
	       	    columns: 3
       	    },
       	    defaults : {
	       	    xtype : 'textfield',
	  		    labelWidth : 100,
	  		    margin:'5 4 1 4',
	  		    labelAlign:'right'			
       	    },
       	    items:[
       	    {
				xtype:'wms_DefFieldValCombo',//储区用途
				fieldLabel: $i18n.area_usetype,
				id : 'd2_area_usetype2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CDEF_DEFAREA,AREA_USETYPE"}
   				}),
   				allowBlank: false,
				beforeLabelTextTpl : required
	        }, {
				xtype:'wms_DefFieldValCombo',//储区属性
				fieldLabel: $i18n.area_attribute,
				id : 'd2_area_attribute2101',
				store:Ext.create("cms.store.common.comboStore",{
					params:{str:"CDEF_DEFAREA,AREA_ATTRIBUTE"},
					listeners:{
						'load':function(th,records,successful,eOpts ){
							var type = _myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').getbut2101();
							var rowindex2101 = _myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').getRowNum();
							
							if(type=='update'){
								debugger;
								var data=Ext.getCmp('cdef_DefAreaGrid2101').getStore().
								getAt(rowindex2101-(Ext.getCmp('cdef_DefAreaGrid2101').
								getStore().currentPage-1)*appConfig.getPageSize());
								Ext.getCmp('d2_area_attribute2101').setValue(data.data.areaAttribute);
							}
						}
					}
				}),
   				allowBlank: false,
				beforeLabelTextTpl : required
	        },{
				xtype:'wms_DefFieldValCombo',//属性类型
				fieldLabel: $i18n.attribute_type,
				id : 'd2_attribute_type2101',
				store:Ext.create("cms.store.common.comboStore",{
					params:{str:"CDEF_DEFAREA,ATTRIBUTE_TYPE"},
					listeners:{
						'load':function(th,records,successful,eOpts ){
							var type = _myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').getbut2101();
							var rowindex2101 = _myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').getRowNum();
							if(type=='update'){
								var data = Ext.getCmp('cdef_DefAreaGrid2101').getStore().
								getAt(rowindex2101-(Ext.getCmp('cdef_DefAreaGrid2101').
										getStore().currentPage-1)*appConfig.getPageSize());
								Ext.getCmp('d2_attribute_type2101').setValue(data.data.attributeType);
							}
						}
					}
				}),
   				allowBlank: false,
				beforeLabelTextTpl : required
	        }]
   		},{
   			  xtype:'fieldset',
   			  id:'typeFlag2101',
       	      layout: {
	  	       	  type: 'table',
	  	       	  columns: 3
  	       	  },
  	       	  defaults : {
	  	       	  xtype : 'textfield',
	      		  labelWidth : 100,
	      		  margin:'5 4 1 4',
	      		  labelAlign:'right'			
  	       	  },
  	       	  items:[
  	       	  {
					xtype:'wms_DefFieldValCombo',//储区类型
					fieldLabel: $i18n.area_type,
					id : 'd2_area_type2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,AREA_TYPE"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
	          },{
					xtype:'wms_DefFieldValCombo',//下架方式
					fieldLabel: $i18n.o_type,
					id : 'd2_o_type2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,O_TYPE"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
			  },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.area_pick,//拣货标识
					id : 'd2_area_pick2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,AREA_PICK"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
	          },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.pick_flag,//是否允许拣货
					id : 'd2_pick_flag2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,PICK_FLAG"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.locate_number,//入库定位次数
					id : 'd2_locate_time2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,LOCATE_TIME"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
       		  		xtype:'wms_DefFieldValCombo',//拆零播种标识
       		  		fieldLabel: $i18n.b_divide_flag_s,
       		  		id : 'd2_b_divide_flag2101',
       		  		store:Ext.create("cms.store.common.comboStore").load({
       		  			params:{str:"CDEF_DEFAREA,B_DIVIDE_FLAG"}
       		  	    }),
       		  	    allowBlank: false,
       		  	    beforeLabelTextTpl : required
       	       },{
	       		xtype:'wms_DefFieldValCombo',
	       		fieldLabel: $i18n.divide_flag_a,//整箱播种标识
	       		id : 'd2_divide_flag_a2101',
	       		store:Ext.create("cms.store.common.comboStore").load(
	       				{
	       					params:{str:"CDEF_DEFAREA,B_DIVIDE_FLAG"}
	       				}),
	       				allowBlank: false,
	       				beforeLabelTextTpl : required
	       	},{
					xtype:'numberfield',//最大板数
					fieldLabel: $i18n.max_qty,
					id : 'd2_max_qty2101',
					minValue:0,
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'numberfield',
					fieldLabel: $i18n.max_case,//最大箱数
					id : 'd2_max_case2101',
					minValue:0,
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'numberfield',
					fieldLabel: $i18n.max_weight,//最大重量
					id : 'd2_max_weight2101',
					minValue:0,
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'numberfield',
					fieldLabel: $i18n.max_volume,//最大材积
					id : 'd2_max_volume2101',
					minValue:0,
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					fieldLabel: $i18n.maxqty_strategy_id,//最大取值策略
					id : 'd2_maxqty_strategy_id2101'
		        }]
   		},{

			  xtype:'fieldset',
			  id:'detailInfor2101_1',
   	      layout: {
	  	       	  type: 'table',
	  	       	  columns: 3
	       	  },
	       	  defaults : {
	  	       	  xtype : 'textfield',
	      		  labelWidth : 100,
	      		  margin:'5 4 1 4',
	      		  labelAlign:'right'			
	       	  },
	       	  items:[{
	       		xtype:'wms_DefFieldValCombo',
	       		fieldLabel: $i18n.b_replenish_type,//拆零补货方式
	       		id : 'd2_b_replenish_type2101',
	       		store:Ext.create("cms.store.common.comboStore").load(
	       		{
	       				params:{str:"CDEF_DEFAREA,B_REPLENISH_TYPE"}
	       			}),
	       			allowBlank: false,
	       		beforeLabelTextTpl : required
	       	},{
	       		xtype:'wms_DefFieldValCombo',
	       		fieldLabel: $i18n.b_replenish_rule,//拆零补货算法
	       		id : 'd2_b_replenish_rule2101',
	       		store:Ext.create("cms.store.common.comboStore",{
	       			proxy:{
	       				type:'ajax',
	       				method:'post',
	       				url : 'cdef_DefWareAction_getReplenishRuleCombo.action',
	       		        reader: {
	       		    		type:'json',
	       		            root: 'rootList',    
	       		            totalProperty: 'totalCount'
	       		        }
	       		    }
	       		}).load(),
	       		allowBlank: false,
	       		beforeLabelTextTpl : required
	       	},{
	        	xtype:'wms_DefFieldValCombo',
	        	fieldLabel: $i18n.item_type,//免拣货区属性
				id : 'd2_item_type2101',
				store:Ext.create("cms.store.common.comboStore").load(
	       				{
	       					params:{str:"CDEF_DEFAREA,ITEM_TYPE"}
	       				}),
   				allowBlank: false,
   				beforeLabelTextTpl : required
	        },{
	       		xtype:'wms_DefFieldValCombo',
	       		fieldLabel: $i18n.c_replenish_type,//整箱补货方式
	       		id : 'd2_c_replenish_type2101',
	       		store:Ext.create("cms.store.common.comboStore").load({
	       					params:{str:"CDEF_DEFAREA,C_REPLENISH_TYPE"}
	       		}),
	       		allowBlank: false,
	       		beforeLabelTextTpl : required
	       	},{
	       		xtype:'wms_DefFieldValCombo',
	       		fieldLabel: $i18n.c_replenish_rule,//整箱补货算法
	       		id : 'd2_c_replenish_rule2101',
	       		store:Ext.create("cms.store.common.comboStore",{
	       			proxy:{
	       				type:'ajax',
	       				method:'post',
	       				url : 'cdef_DefWareAction_getReplenishRuleCombo.action',
	       				reader: {
	       					type:'json',
	       					root: 'rootList',    
	       					totalProperty: 'totalCount'
	       				}
	       			}
	       		}).load(),
	       		allowBlank: false,
	       		beforeLabelTextTpl : required
	       	},{
	        	xtype:'numberfield',
	        	fieldLabel: $i18n.pick_level,//拣货区级别
				id : 'd2_pick_level2101',
				minValue:0,
   				allowBlank: false,
				beforeLabelTextTpl : required
	        	
	        }]
 		},{
   			 xtype:'fieldset',
   			 id:'detailInfor2101',
       	      layout: {
	  	       	  type: 'table',
	  	       	  columns: 3
  	       	  },
  	       	  defaults : {
	  	       	  xtype : 'textfield',
	      		  labelWidth : 100,
	      		  margin:'5 4 1 4',
	      		  labelAlign:'right'			
  	       	  },
  	       	  items:[
	  	       	 {
					xtype:'wms_DefFieldValCombo',//混属性标识
					fieldLabel: $i18n.mix_flag,
					id : 'd2_mix_flag2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,MIX_FLAG"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.mix_supplier,//供应商混载标识
					id : 'd2_mix_supplier2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,MIX_SUPPLIER"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.mix_owner,//混货主标识
					id : 'd2_mix_owner2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,MIX_OWNER"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.replenish_task_rule,//补货切单规则
					id : 'd2_replenish_task_rule2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,REPLENISH_TASK_RULE"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required,
					hidden:true
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.box_pick,//试算标识
					id : 'd2_b_pick2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,B_PICK"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.limit_type,//限制入库类型
					id : 'd2_limit_type2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,LIMIT_TYPE"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        }, {
					xtype:'numberfield',
					fieldLabel: $i18n.limit_rate,//限制比率
					id : 'd2_limit_rate2101',
					minValue:0,
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'numberfield',
					fieldLabel: $i18n.pal_out_rate,//板型出货比率
					id : 'd2_pal_out_rate2101',
					minValue:0,
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.io_buffer_flag,//是否使用上下架暂存区
					id : 'd2_io_buffer_flag2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,IO_BUFFER_FLAG"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.advancer_pick_flag,//是否对允许前拣货区域
					id : 'd2_advancer_pick_flag2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,ADVANCER_PICK_FLAG"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: $i18n.divide_line_flag,//输送线标识
					id : 'd2_divide_line_flag2101',
					store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"CDEF_DEFAREA,DIVIDE_LINE_FLAG"}
	   				}),
	   				allowBlank: false,
					beforeLabelTextTpl : required
		        },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel: '是否保留托盘号',
					id : 'd2_keep_label2101',
					store:Ext.create("cms.store.common.comboStore").load(
							{
									params:{str:"N,YESORNO"}
			   				}),
			   		allowBlank: false,
			   		beforeLabelTextTpl : required
				},{
					xtype:'textfield',
					fieldLabel: $i18n.area_remark,//储区备注
					id : 'd2_area_Remark2101'
					//colspan: 3,
					//width:520
				}
  	       	  ]
   		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'d2_menuWidget52101'
	}]
});
