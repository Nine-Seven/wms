/**
 * 模块名称：商品包装
 * 模块编码：1401_D2
 * @author JUN
 */
Ext.define('cms.model.bdef.bdef_ArticlePackingModel',{
	extend:'Ext.data.Model',
	idProperty:'articleNo,packingQty',
	fields:[
        {name:'articleNo'},
        {name:'packingQty'},
        {name:'packingUnit'},
        {name:'spec'},
        {name:'mpackingQty'},
        {name:'mpackingUnit'},
        {name:'ALength'},
        {name:'AWidth'},
        {name:'AHeight'},
        {name:'packingWeight'},
        {name:'palBaseQbox'},
        {name:'palHeightQbox'},
        {name:'sorterFlag'},
        {name:'ruleFlag'},
        {name:'qpalette'},
        {name:'createFlag'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'updtName'},
        {name:'updtDate'},
    	{name:'rsvPking1'},
        {name:'rsvPking2'},
        {name:'rsvPking3'},
        {name:'rsvPking4'},
        {name:'rsvPking5'},
        
        {name:'articleName'},
        {name:'sorterflagText'},
        {name:'ruleflagText'}
	]
});