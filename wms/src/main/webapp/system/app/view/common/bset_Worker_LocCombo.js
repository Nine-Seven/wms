/**
 * 员工作业仓别、公用組件
 * lich
 */
Ext.define('cms.view.common.bset_Worker_LocCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.bset_Worker_LocCombo',
    queryMode: 'local',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.cdef.bset_Worker_LocComboStore')
});