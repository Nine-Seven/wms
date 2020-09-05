/**
 * 创建人:JUN
 */
Ext.define('cms.model.idata.idata_CheckMModel', {
    extend: 'Ext.data.Model',
    fields: [
		{name:'checkNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'SImportNo'},
		{name:'SCheckNo'},
		{name:'importType'},
		{name:'importNo'},
		{name:'supplierNo'},
		{name:'dockNo'},
		{name:'checkWorker'},
		{name:'qcWorker'},
		{name:'unloadWorker'},
		{name:'status'},
		{name:'checkStartDate'},
		{name:'checkEndDate'},
		{name:'printerGroupNo'},
		{name:'receivingNo'},
		{name:'checkTools'},
		{name:'sendFlag'},
		{name:'printTimes'},
		{name:'downType'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'statusText'},
		{name:'sendFlagText'},
		{name:'isDiffFlag'},//是否有差异 huangb 20160815
		{name:'ownerName'},
		{name:'workerName'},
		{name:'poType'},
		{name:'poNo'},
		{name:'supplierName'},
		{name:'sumCheckQty'},
		{name:'sumPoQty'},
		{name:'sumImportQty'},
		{name:'classType'}
    ],
    idProperty:'checkNo,warehouseNo,ownerNo'
});
