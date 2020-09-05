Ext.define('cms.view.common.recedeNoCombo', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.recedeNoCombo',
	typeAhead : false,
	hideTrigger : true,	
	displayField : 'value',
	valueField : 'value',
	text:'value',
	mode : 'remote', // 远程访问
	store : Ext.create('cms.store.rodata.recedeNoComboStore'),
	queryMode : 'remote',// [remote]非本地
	minChars : 1,// 多少字符请求1次
	queryParam : 'wheresql',// 请求参数
	align : 'left',
	matchFieldWidth:false,
	forceSelection : true,//必须选择 ，不然就清空
	listConfig : {
		width : 500,
		loadingText : '查询中...',
		emptyText : '没有找到相应的数据..',
		getInnerTpl : function() {
		return '{value}';
		}
	}
});