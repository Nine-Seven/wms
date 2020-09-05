/**
 * 
 * Jun
 */
Ext.define('cms.view.common.bdef_PackingQtyCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.bdef_PackingQtyCombo',
    //queryMode: 'remote',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore')
});