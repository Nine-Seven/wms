/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
Ext.define('cms.model.bdef.pntdef_PrinterGrpgatherModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,printerGroupNo',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'grpgatherNo'},
		{name:'grpgatherName'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});