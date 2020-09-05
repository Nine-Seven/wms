Ext.define('cms.model.bdef.bdef_ArticleFamilyDModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,ownerNo,familyNo,articleNo',
    fields: [
		{name:'enterpriseNo'},
		{name:'ownerNo'},
		{name:'familyNo'},
		{name:'articleNo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'articleName'},
		{name:'ownerArticleNo'}
		
    ]
});