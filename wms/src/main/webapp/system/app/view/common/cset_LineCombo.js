/**
 * Jun
 */
Ext.define('cms.view.common.cset_LineCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.cset_LineCombo',
    //queryMode: 'remote',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.cset.cset_LineComboStore')
});