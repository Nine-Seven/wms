/**
 * 商品类别組件
 * zhouhuan
 */
Ext.define('cms.view.common.bdef_DefGroupNoCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.bdef_DefGroupNoCombo',
	fieldLabel: "商品类别",
    typeAhead: false,
    hideTrigger:true,
    displayField: 'dropValue',
    valueField: 'value',
    mode: 'remote',   //远程访问
    store :  Ext.create("cms.store.bdef.bdef_ArticleGroupComboStore"),
    queryMode: 'remote',
    minChars : 1,// 多少字符请求1次 
    queryParam : 'strWheresql', //请求参数  
    align:'left' ,
	forceSelection : true,
	listConfig: {
       loadingText: '查询中...',
       emptyText: '没有找到相应的数据！' ,
       getInnerTpl: function() {
    	   return '{dropValue}' ;
       }
   }
});