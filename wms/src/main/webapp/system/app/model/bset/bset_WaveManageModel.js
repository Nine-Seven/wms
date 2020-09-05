/**
 * 模块名称：波次管理model
 * 模块编码：6502
 * 创建：hkl 
 */
Ext.define('cms.model.bset.bset_WaveManageModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,waveNo',
    fields: [
		{name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'waveNo'},
		{name:'startTime'},
		{name:'endTime'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'status'},
		{name:'operateDate'},
		{name:'currBatch'},
		{name:'waveType'},
		{name:'statusText'},
		{name:'waveTypeText'}
    ]
});