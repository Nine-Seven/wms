/**
 * 模块名称：客户与电子标签储位对应关系
 * 模块编码：1P01
 * @author hcx
 */
Ext.define('cms.model.bdef.bdef_CsetCustDpscellModel',{
	extend:'Ext.data.Model',
	idProperty:'warehouseNo,ownerNo,dpsCellNo',
	fields:[
        {name:'warehouseNo'},
        {name:'ownerNo'},
        {name:'dpsCellNo'},
        {name:'custNo'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'status'},
        {name:'deviceGroupNo'},
        {name:'deviceNo'}
	]
});