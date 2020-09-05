Ext.define('cms.model.bset.bset_RightListModel', {
    extend: 'Ext.data.Model',
    idProperty:'bset_RightListModel',
    fields: [
		{name:'moduleId'},
		{name:'moduleName'},
		{name:'name'},
		{name:'rightName'},
		{name:'butId'},
		{name:'butName'},
		{name:'parentId'},
		{name:'flag'},
		{name:'enable'}
    ]
});