/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
Ext.define('cms.model.bset.bset_WorkstationDivideModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,workstationNo,deviceGroupNo',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'workstationNo'},
		{name:'deviceGroupNo'},
		{name:'workstationName'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});