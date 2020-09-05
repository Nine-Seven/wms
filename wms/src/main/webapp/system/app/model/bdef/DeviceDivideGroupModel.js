/**
 * 模块名称：设备维护
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.model.bdef.DeviceDivideGroupModel',{
	extend:'Ext.data.Model',
	idProperty:'ownerNo,colname',
	fields:[
	        {name:'enterpriseNo'},
	        {name:'warehouseNo'},
			{name:'deviceGroupNo'},
			{name:'useType'},
			{name:'useTypeText'},
			{name:'deviceGroupName'},			
			{name:'defaultFlag'},		
			{name:'computeFlag'},
			{name:'strategyType'},			
			{name:'strategyId'},
			{name:'status'},
			{name:'statusText'},
			{name:'computeText'},
			{name:'defaultFlagText'},
			{name:'rgstName'},
			{name:'rgstDate'}
	       ],
	idProperty:'enterpriseNo,warehouseNo,deviceGroupNo,useType'
});