/**
 * 模块名称：储位货主关系维护
 * 模块编码：2601
 * 创建：hkl 
 */
Ext.define('cms.store.cset.cset_DefCellStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_CellArticleModel',
	//pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cset_CellOwnerAction_getCset_CellList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});