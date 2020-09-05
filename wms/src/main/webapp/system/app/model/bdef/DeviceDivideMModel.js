/**
 * 模块名称：设备维护
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.model.bdef.DeviceDivideMModel',{
	extend:'Ext.data.Model',
	idProperty:'ownerNo,colname',
	fields:[
	        {name:'enterpriseNo'},
	        {name:'warehouseNo'},
			{name:'deviceNo'},		
			{name:'deviceGroupNo'},			
			{name:'operateType'},			
			{name:'deviceType'},			
			{name:'deviceName'},			
			{name:'deviceAlias'},			
			{name:'maxQty'},			
			{name:'status'},			
			{name:'grade'},			
			{name:'boxItems'},			
			{name:'useTimes'},			
			{name:'custQty'},
			{name:'AStockNo'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'statusText'},
			{name:'deviceTypeText'},
			{name:'operateTypeText'},
			
			{name:'produceCapacity'},
			{name:'produceCapacityType'},
			{name:'diviceRate'},
			{name:'strategyId'},
			{name:'refRateFlag'},
			{name:'refRateFlag'},
			{name:'produceCapacityTypeText'},
			{name:'refRateFlagText'}
		
	       ],
	idProperty:'enterpriseNo,warehouseNo,deviceNo'
});