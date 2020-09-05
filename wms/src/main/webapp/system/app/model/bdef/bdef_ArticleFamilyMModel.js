Ext.define('cms.model.bdef.bdef_ArticleFamilyMModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,ownerNo,familyNo',
    fields: [
		{name:'enterpriseNo'},
		{name:'ownerNo'},
		{name:'familyNo'},
		{name:'familyName'},
		{name:'rgstName'},
		{name:'rgstDate'}
		
    ]
});