/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
Ext.define('cms.model.bdef.pntset_GrpgatherPrinterGroupModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,printerGroupNo,grpgatherNo',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'grpgatherNo'},
		{name:'printerGroupNo'},
		{name:'printerGroupName'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});