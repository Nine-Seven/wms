/**
 * 车辆
 * @author hcx
 */
Ext.define('cms.model.bdef.bdef_DefcartypeModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,cartypeNo',
    fields: [
        {name:'enterpriseNo'},
    	{name:'cartypeNo'},
		{name:'cartypeName'},
		{name:'cartypeWeight'},
		{name:'cartypeLength'},
		{name:'cartypeWidth'},
		{name:'cartypeHeight'},
		{name:'maxLayer'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});