/**
 * 码头組件
 * zhouhuan
 */
Ext.define('cms.view.common.bdef_DefDockCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.bdef_DefDockCombo',
    queryMode: 'local',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.bdef.bdef_DefDockComboStore')
});