Ext.define('cms.model.bset.bset_Role_ListModel', {
    extend: 'Ext.data.Model',
    idProperty:'bset_Role_ListModel',
    fields: [
        {name:'roleId'},
		{name:'roleName'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'flag'},
		{name:'enterpriseNo'}
    ]
});