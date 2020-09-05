/**
 * 商品包装、公用組件
 * 商品小类下拉
 * 创建：Jun
 */
 Ext.define('cms.view.common.bdef_ArticleVirtueCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.bdef_ArticleVirtueCombo',
    queryMode: 'local',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.bdef.bdef_ArticleVirtueComboStore')
});