Ext.define('cms.view.common.bdef_DefWorkerCombo', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.bdef_DefWorkerCombo',
	typeAhead : false,
	hideTrigger : true,	
	displayField : 'dropValue',
	valueField : 'value',
	mode : 'remote', // 远程访问
	store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
	queryMode : 'remote',// [remote]非本地
	minChars : 1,// 多少字符请求1次
	queryParam : 'strWheresql',// 请求参数
	align : 'left',
	forceSelection : true,//必须选择 ，不然就清空
	listConfig : {
		loadingText : '查询中...',
		emptyText : '没有找到相应的数据..',
		getInnerTpl : function() {
		return '{dropValue}';
		}
	}
});