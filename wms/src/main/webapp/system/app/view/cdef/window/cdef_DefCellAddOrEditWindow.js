/**
 * 储位修改弹出框
 */
Ext.define('cms.view.cdef.window.cdef_DefCellAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cdef_DefCellAddOrEditWindow',
	id : 'cdef_DefCellAddOrEditWindow',
	width : 540,
	height : 400,
	layout:'border',
	modal:true,
	items:[{
		xtype:'form',
		region:'center',
		id:'cdef_DefCellAddOrEditForm',
		frame : true,
        layout: {
	        type: 'table',
	        columns: 2
	    },
		defaults : {
			xtype : 'textfield',
			margin : '5 5 5 5',
			labelAlign:'right',
			allowBlank: false,
			labelWidth : 95,
			width : 250
		},
		items:[{
				fieldLabel: $i18n.cell,
				id : 'd4_cell2101',
				readOnly : true,
				beforeLabelTextTpl : required
			},{
				fieldLabel: '货位展示',
				id : 'd4_dispCellNo2101',
				readOnly : true,
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.max_qty,
				id : 'd4_max_qty2101',
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.max_volume,
				id : 'd4_max_volume2101',
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.max_weight,
				id : 'd4_max_weight2101',
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.max_case,
				id : 'd4_max_case2101',
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.cell_status,
				id : 'd4_cell_status2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CDEF_DEFCELL,CELL_STATUS"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.check_status,
				id : 'd4_check_status2101',
				readOnly : true,
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CDEF_DEFCELL,CHECKSTATUS"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.mix_flag,
				id : 'd4_mix_flag2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,MIX_FLAG"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.b_pick,
				id : 'd4_b_pick2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,B_PICK"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.limit_type,
				id : 'd4_limit_type2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,MIX_FLAG"}
   				}),
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.limit_rate,
				id : 'd4_limit_rate2101',
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.a_flag,
				id : 'd4_a_flag2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,A_FLAG"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.mix_supplier,
				id : 'd4_mix_supplier2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,MIX_SUPPLIER"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.pick_flag,
				id : 'd4_pick_flag2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CDEF_DEFAREA,PICK_FLAG"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: $i18n.mix_owner,//混货主标识
				id : 'd4_mix_owner2101',
				store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,MIX_OWNER"}
   				}),
				beforeLabelTextTpl : required
			},{
				xtype:'wms_DefFieldValCombo',
				fieldLabel: '是否保留托盘号',
				id : 'd4_keep_label2101',
				store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,YESORNO"}
		   				}),
		   		beforeLabelTextTpl : required
			}]
	},{
		region:'south',
		xtype : 'toolbar',
		dock : 'top',
		items: ['->', {
			text : $i18n.save,//保存
			name : 'save'
		},{
			text : $i18n.close1,//关闭
			name : 'close'
		}]
	}]
});
