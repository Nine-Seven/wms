/**
 * 储位新增弹出框
 */
Ext.define('cms.view.cdef.window.cdef_DefStockAndCellAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cdef_DefStockAndCellAddOrEditWindow',
	id : 'cdef_DefStockAndCellAddOrEditWindow',
	width : 800,
	height : 520,
	layout:'border',
	modal:true,
	items:[{
		xtype:'form',
		region:'center',
		id:'cdef_DefStockAndCellAddOrEditForm',
		frame : true,
		layout:'form',
		defaults : {
			 xtype : 'textfield',
             labelWidth : 100,
             width:250,
             labelAlign:'right'	,
             margin : '5 5 5 5'
		},
		items:[{
               	xtype:'fieldset',
               	title:$i18n.baseInfo,
                layout: 
                {
                   type: 'table',
                   columns: 2
                },
                defaults : 
                {
                	xtype : 'textfield',
                    labelWidth : 100,
                    width:250,
                    labelAlign:'right'	
	  	        },
	       	       items:[{
						xtype:'cdef_DefWareCombo',
						fieldLabel: $i18n.ware_no,
						id : 'wareno2101',
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
					},
				    {
						xtype:'cdef_DefAreaCombo',
						fieldLabel: $i18n.title440001tab2,
						id : 'areano2101',
						store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',{
		            		proxy:{
		     					type:'ajax',
		     					method:'post',
		     					url:'cdef_DefWareAction_getCdef_DefAreaComboforWindow',
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
					}] 
		       },{
	               	xtype:'fieldset',
	               	title:$i18n.cellSet,
	               	id:'setCellAndStock',
	                layout: 
	                {
	                   type: 'table',
	                   columns: 3
	                },
	                defaults : 
	                {
	                	 xtype : 'textfield',
	                     labelWidth : 100,
	                     width:250,
	                     labelAlign:'right'
		  	       	 },
		       	     items:[
					 {
						 fieldLabel: $i18n.perfix,
						 id : 'perfix',
						 colspan:3
					 },{
							fieldLabel:$i18n.stockMin,
							id : 'stockMin'
					},{
							fieldLabel:$i18n.stockMax,
							id : 'stockMax'
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: '前缀',
						id : 'perf1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
							 params:{str:"N,PERFIX"}
		   				}),
						beforeLabelTextTpl : required,
						
					},{
						  fieldLabel:$i18n.cellMin,
						  id : 'cellMin',
						  beforeLabelTextTpl : required
					},{
						  fieldLabel:$i18n.cellMax,
						  id : 'cellMax',
						  beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: '前缀',
						id : 'perf2',
						store:Ext.create("cms.store.common.comboStore").load(
						{
							 params:{str:"N,PERFIX"}
		   				}),
						beforeLabelTextTpl : required,
						
					},{
						 fieldLabel:$i18n.bayXMin,
						 id : 'bayXMin',						
					 },{
						  fieldLabel:$i18n.bayXmax,
						  id : 'bayXMax'							  
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: '前缀',
						id : 'perf3',
						store:Ext.create("cms.store.common.comboStore").load(
						{
							 params:{str:"N,PERFIX"}
		   				}),
						beforeLabelTextTpl : required,
						
					},{
						fieldLabel:$i18n.floorMin,
						id : 'floorMin',
					},{
						fieldLabel: $i18n.floorMax,
						id : 'floorMax'
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: '前缀',
						id : 'perf4',
						store:Ext.create("cms.store.common.comboStore").load(
						{
							 params:{str:"N,PERFIX"}
		   				}),
						beforeLabelTextTpl : required,
						
					},{
						fieldLabel: $i18n.showCell,
						id : 'showCell',
						readOnly:true,
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.showCell,
						id : 'showDispCell',
						readOnly:true,
						beforeLabelTextTpl : required
					}
					] 
		       },
		       {
	               	xtype:'fieldset',
	               	title:$i18n.cellAttribute,
	                layout: 
	                {
	                   type: 'table',
	                   columns: 3
	                },
	                defaults : 
	                {
	                   xtype : 'textfield',
	                   labelWidth : 100,
	                   width:250,
	                   labelAlign:'right'			
		  	       	 },
		       	     items:[{
						fieldLabel: $i18n.max_qty,
						id : 'd4_max_qty2101_1',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_volume,
						id : 'd4_max_volume2101_1',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_weight,
						id : 'd4_max_weight2101_1',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_case,
						id : 'd4_max_case2101_1',
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.cell_status,
						id : 'd4_cell_status2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"CDEF_DEFCELL,CELL_STATUS"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.check_status,
						id : 'd4_check_status2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"CDEF_DEFCELL,CHECKSTATUS"}
		   				}),
						beforeLabelTextTpl : required,
						hidden:true
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.a_flag,
						id : 'd4_a_flag2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,A_FLAG"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_supplier,
						id : 'd4_mix_supplier2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_SUPPLIER"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_flag,
						id : 'd4_mix_flag2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_FLAG"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.b_pick,
						id : 'd4_b_pick2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,B_PICK"}
		   				}),
						beforeLabelTextTpl : required,
						hidden:true
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.limit_type,
						id : 'd4_limit_type2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_FLAG"}
		   				}),
						beforeLabelTextTpl : required,
						hidden:true
					},{
						fieldLabel: $i18n.limit_rate,
						id : 'd4_limit_rate2101_1',
						beforeLabelTextTpl : required,
						hidden:true
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.pick_flag,
						id : 'd4_pick_flag2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
							 params:{str:"CDEF_DEFAREA,PICK_FLAG"}
		   				}),
						beforeLabelTextTpl : required,
						hidden:true
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_owner,//混货主标识
						id : 'd4_mix_owner2101_1',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_OWNER"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: '是否保留托盘号',
						id : 'd4_keep_label2101_1',
						//hidden:true,
						store:Ext.create("cms.store.common.comboStore").load(
								{
										params:{str:"N,YESORNO"}
				   				}),
				   		beforeLabelTextTpl : required
					}]        
		       }]
	},
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'d3_menuWidget52101'
	}]
});
