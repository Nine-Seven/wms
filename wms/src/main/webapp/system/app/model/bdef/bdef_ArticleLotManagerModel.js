/**
 * 模块名称：商品批属性表
 * @author JUN
 */
Ext.define('cms.model.bdef.bdef_ArticleLotManagerModel',{
	extend:'Ext.data.Model',
	idProperty:'',
	fields:[
        {name:'articleNo'},
        {name:'lotNo'},
        {name:'produceDate'},
        {name:'expireDate'},
        {name:'expiryDays'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'price'}
    ]
});