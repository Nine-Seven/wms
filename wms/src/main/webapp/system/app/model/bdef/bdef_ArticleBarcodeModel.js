/**
 * 模块名称：商品包装
 * 模块编码：1401_D2
 * @author JUN
 */
Ext.define('cms.model.bdef.bdef_ArticleBarcodeModel',{
	extend:'Ext.data.Model',
	idProperty:'barcode,ownerNo,articleNo,packingQty',
	fields:[
        {name:'barcode'},
        {name:'ownerNo'},
        {name:'articleNo'},
        {name:'packingQty'},
        {name:'primaryFlag'},
        {name:'createFlag'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'updtName'},
        {name:'updtDate'},
        
        {name:'ownerArticleNo'},
        {name:'articleName'},
        {name:'primaryflagText'}
        
	]
});