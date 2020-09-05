Ext.define('cms.view.common.ridata_UntreadNoCombo', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.ridata_UntreadNoCombo',
	typeAhead : false,
	hideTrigger : true,	
	displayField: 'dropValue',
    valueField: 'value',
	mode : 'remote', // 远程访问
	store : Ext.create("cms.store.ridata.ridata_UntreadNoComboStore"),
	queryMode : 'remote',// [remote]非本地
	minChars : 1,// 多少字符请求1次
	queryParam : 'strPageSql',// 请求参数
	align : 'left',
	matchFieldWidth:false,
	forceSelection : true,//必须选择 ，不然就清空
	listConfig : {
		width : 150,
		loadingText : '查询中...',
		emptyText : '没有找到相应的数据..',
		getInnerTpl : function() {
		return '{dropValue}';
		}
	}
});