/**
 * 模块名称：设备维护
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.model.bdef.DeviceDivideDModel',{
	extend:'Ext.data.Model',
	idProperty:'ownerNo,colname',
	fields:[
	        {name:'enterpriseNo'},
	        {name:'warehouseNo'},
			{name:'deviceNo'},
			{name:'deviceCellNo'},
			{name:'deviceGroupNo'},						
			{name:'deviceName'},			
			{name:'bayX'},			
			{name:'stockY'},			
			{name:'mixFlag'},			
			{name:'mixSupplier'},			
			{name:'maxQty'},			
			{name:'maxWeight'},			
			{name:'maxVolume'},
			{name:'maxCase'},
			{name:'status'},
			{name:'checkStatus'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'statusText'},
			{name:'pickOrder'},
			{name:'mixSupplierText'},
			{name:'mixFlagText'},
			{name:'deviceName'}
			
	       ],
	idProperty:'enterpriseNo,warehouseNo,deviceNo,deviceCellNo'
});