Ext.define('cms.view.common.wms_DefStrategyCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.wms_DefStrategyCombo',
    queryMode: 'local',
    displayField: 'dropValue',
    valueField: 'value',
    forceSelection : true,
	store:Ext.create("cms.store.wms.wms_DefStrategyComboStore"),
	queryParam : 'strWheresql'
});

