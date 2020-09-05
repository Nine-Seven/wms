/**
 * 创建人:panzx
 */
Ext.define('cms.model.cset.cset_BufferModel', {
    extend: 'Ext.data.Model',
    fields: [//加入实体字段
            {name:'enterpriseNo'},
			{name:'warehouseNo'},
			{name:'bufferName'},
			{name:'wareNo'},
			{name:'areaNo'},
			{name:'stockNo'},
			{name:'cellNo'},
			{name:'status'},
			{name:'useVolumn'},
			{name:'useWeight'},
			{name:'useBoxnum'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			{name:'statusText'}
    ]
   //, idProperty:'warehouseNo,articleNo,ownerNo,pickType'
});
