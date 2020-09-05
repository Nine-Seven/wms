/**
 * 车辆信息
 * @author JUN
 */
Ext.define('cms.model.bdef.bdef_DefcarModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,carNo',
    fields: [
        {name:'enterpriseNo'},
    	{name:'warehouseNo'},
		{name:'carNo'},
		{name:'cartypeNo'},
		{name:'carName'},
		{name:'carPlate'},
		{name:'oilConsume'},
		{name:'careMile'},
		{name:'mile'},
		{name:'careDate'},
		{name:'careWorker'},
		{name:'sanplNo'},
		{name:'carClass'},
		{name:'driverWorker'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'cartypeName'},
		{name:'careDateText'},
		{name:'carClassText'},
		{name:'workerName'},
		{name:'driverWorkerText'},
		{name:'strStatus'}
    ]
});