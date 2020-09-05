Ext.define('cms.store.fcdata.fcdata_CheckDStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.fcdata.fcdata_CheckDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad : false,
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'fcdata_CheckAction_getCheckD.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	storeId : 'fcdata_CheckDStore',
	listeners : {
		load : function(store, records, successful, operation, eOpts) {
			if (successful) {
				g_map8201.clear();
				var d = [];
				var arrayLength = Ext.data.StoreManager
						.lookup('fcdata_CheckDStore').sync().getCount();
				if (records != null) {
					for (var i = 0; i < arrayLength; i++) {
						if (Ext.getCmp('gridCheckD8301').getStore().data.items[i]
								.get('difFlag')) {
							d.push(Ext.getCmp('gridCheckD8301').getStore()
									.getAt(i));
							g_map8201.add(i, i);
						}
					}
					Ext.getCmp('gridCheckD8301').getSelectionModel().select(d);
				}
			}
		}
	}
});