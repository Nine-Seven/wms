/**
 * 
 * Jun
 */
Ext.define('cms.view.common.bset_GroupCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.bset_GroupCombo',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore')
});