/**
 * 模块名称：接口配置
 * 模块编码：I301
 * 创建：czh
 */
Ext.define('cms.model.bdef.bdef_WmsInterfacePlatModel',{
	extend:'Ext.data.Model',
	idProperty:'enterpriseNo,warehourceNo,platNo',
	fields:[
        {name:'enterpriseNo'},
        {name:'platType'},
        {name:'platNo'},
        {name:'platDesc'},
        {name:'warehourceNo'},
        {name:'apiNo'},
        {name:'apiDesc'},
        {name:'apiMethod'},
        {name:'urlAddress'},
        {name:'dataFormat'},
        {name:'custCode'},
        {name:'appKey'},
        {name:'appSecret'},
        {name:'accessTokey'},
        {name:'callbackAddress'}
        
	]
});