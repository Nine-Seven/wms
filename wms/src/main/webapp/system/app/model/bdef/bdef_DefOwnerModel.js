/**
 * 商品资料
 * 模块编码：
 * @author JUN
 */
Ext.define('cms.model.bdef.bdef_DefOwnerModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'ownerNo'},
		{name:'ownerName'},
		{name:'ownerAlias'},
		{name:'ownerAddress'},
		{name:'ownerPhone'},
		{name:'ownerFax'},
		{name:'ownerContact'},
		{name:'ownerRemark'},
		{name:'invoiceNo'},
		{name:'invoiceAddr'},
		{name:'invoiceHeader'},
		{name:'status'},
		{name:'fixedcellFlag'},
		{name:'authorityType'},
		{name:'IStrategy'},
		{name:'OStrategy'},
		{name:'MStrategy'},
		{name:'riStrategy'},
		{name:'roStrategy'},
		{name:'fcStrategy'},
		{name:'rsvStrategy1'},
		{name:'rsvStrategy2'},
		{name:'rsvStrategy3'},
		{name:'rsvStrategy4'},
		{name:'rsvStrategy5'},
		{name:'rsvStrategy6'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'scanFlag'},
		{name:'flag'},
		{name:'statusText'},
		{name:'fixedcellFlagText'},
		{name:'authorityTypeText'},
		{name:'IStrategyText'},
		{name:'OStrategyText'},
		{name:'MStrategyText'},
		{name:'riStrategyText'},
		{name:'roStrategyText'},
		{name:'fcStrategyText'},
		{name:'rsvStrategy1Text'},
		{name:'rsvStrategy2Text'},
		{name:'rsvStrategy3Text'},
		{name:'rsvStrategy4Text'},
		{name:'rsvStrategy5Text'},
		{name:'rsvStrategy6Text'},
		{name:'scanFlagText'},
		{name:'turnOverRule'},
		{name:'cellManagerType'},
		{name:'cellType'},
		{name:'typeValue'},
		
		//7-20添加
		{name:'rsvVar2'},
		{name:'rsvVar3'},
		
		//仓别级 储位管理
		{name:'wcellManagerType'},
		{name:'wtypeValue'}
		
    ],
    idProperty:'ownerNo'
});


















