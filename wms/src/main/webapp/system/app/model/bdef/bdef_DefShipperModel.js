/**
 * 模块名称：承运商资料维护
 * 模块编码：1E01
 * 创建：周欢
 */
Ext.define('cms.model.bdef.bdef_DefShipperModel',{
	extend:'Ext.data.Model',
	idProperty:'warehouseNo,shipperNo',
	fields:[
	        {name:'warehouseNo'},
			{name:'shipperNo'},
			{name:'statusText'},
			{name:'shipperName'},
			{name:'address'},
			{name:'tel'},
			{name:'contact'},
			{name:'status'},
			{name:'disprice'},
			{name:'graprice'},
			{name:'compactDate'},
			{name:'endDate'},
			{name:'multi'},
			{name:'memo'},
			{name:'volprice'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			
			{name:'reportId'},
			{name:'printType'},
			{name:'paperType'},
			{name:'paperTypeText'},
			{name:'singleLocateFlag'},
			{name:'shipperType'},
			{name:'shipperTypeText'},
			{name:'paperComifireFlag'},
			{name:'getPaperType'},
			{name:'enterpriseNo'}
			
	       ]
});





















