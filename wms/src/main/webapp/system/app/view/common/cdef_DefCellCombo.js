Ext.define('cms.view.common.cdef_DefCellCombo', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.cdef_DefCellCombo',
	typeAhead : false,
	hideTrigger : true,	
	displayField : 'dropValue',
	valueField : 'value',
	mode : 'remote', // 远程访问
	store : Ext.create("cms.store.cdef.cdef_DefCellComboStore"),
	queryMode : 'remote',// [remote]非本地
	minChars : 1,// 多少字符请求1次
	queryParam : 'strWheresql',// 请求参数
	align : 'left',
	forceSelection : true,//必须选择 ，不然就清空
	listConfig : {
		loadingText : $i18n_prompt.checking,
		emptyText : $i18n_prompt.noFindRelationData,
		getInnerTpl : function() {
		return '{dropValue}';
		}
	}
});