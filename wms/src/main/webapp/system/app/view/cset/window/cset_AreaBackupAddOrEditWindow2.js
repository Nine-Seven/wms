Ext.define('cms.view.cset.window.cset_AreaBackupAddOrEditWindow2', {
	extend:'Ext.window.Window',
	alias:'widget.cset_AreaBackupAddOrEditWindow2',
	layout : 'border',
	id : 'cset_AreaBackupAddOrEditWindow2301_2',
	height:300,
	width:900,	
	modal:true,
	requires:['cms.view.common.commMenu5',
	          'cms.view.common.cdef_DefAreaCombo',
	          'cms.view.common.cdef_DefWareCombo',
	          'cms.view.common.cdef_DefStockCombo',
	          'cms.store.common.comboStore'],
	items:[{
		xtype:'form',
		region:'center',
		id:'cset_AreaBackupEditLevel2301',
		frame:true,
		layout: 
	    {
	        type: 'table',
	        columns: 3
	    },
		defaults:{
			xtype:'textfield',
			margin:'3 3 3 3',
			labelWidth:120,
			labelAlign:'right'
		},
		items:[
		{
			xtype : 'numberfield',
			fieldLabel:$i18n.line_id,
		 	id:'txtLine_id2301',//级别id
			minValue:0,
		 	maxLength:2,
		 	allowBlank:false,
		 	beforeLabelTextTpl : required,
		 	readOnly:false
		},
		{
			fieldLabel:$i18n.a_level,
 	    	id:'txtA_level2301',//级别
 	    	xtype : 'numberfield',
    		minValue:0,
 	    	maxLength:1,
 	    	colspan:2,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required,
 	    	readOnly:false
		},{
			xtype:'cdef_DefWareCombo',
			fieldLabel: $i18n.ware_no,
			id : 'cmbWareNo2301',
			//mode : 'remote', // 远程访问
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'cset_AreaBackupAction_getCdef_DefWareCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					} 
				}
			}).load(),
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			xtype:'cdef_DefAreaCombo',
			fieldLabel: $i18n.area_no,
			id : 'cmbAreaNo2301',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'cset_AreaBackupAction_getCdef_DefAreaCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					} 
				}
			}),
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			xtype:'cdef_DefStockCombo',
			fieldLabel: $i18n.stock_no,
			id : 'cmbStockNo2301',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'cset_AreaBackupAction_getCdef_DefStockCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					} 
				}
			}),
			allowBlank: true
		},{
 	    	fieldLabel:$i18n.keep_cells,
 	    	xtype : 'numberfield',
 	    	id:'txtkeep_cells2301',
 	    	maxLength:10,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.merger_flag,
 	    	id:'cmbMerger_flag2301',
 	    	colspan:2,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"CSET_AREA_BACKUP,MERGER_FLAG"}
			})
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.sort_flag,
 	    	id:'cmbSort_flag2301',
 	    	allowBlank:false,
 	    	colspan:3,
 	    	width:556,
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"CSET_AREA_BACKUP,SORT_FLAG"}
			})
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.stock_flag,
 	    	id:'cmbStock_flag2301',	
 	    	colspan:2,
 	    	width:556,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"CSET_AREA_BACKUP,STOCK_FLAG"}
			})
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.stockx_flag,
 	    	id:'cmbStockX_flag2301',
 	    	allowBlank:false,	    	
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"CSET_AREA_BACKUP,STOCKX_FLAG"}
			})
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.floor_flag,
 	    	id:'cmbFloor_flag2301',
 	    	allowBlank:false,
 	    	colspan:2,
 	    	width:556,
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"CSET_AREA_BACKUP,FLOOR_FLAG"}
			})
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.bay_flag,
 	    	id:'cmbBay_flag2301',
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required,
    		store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"CSET_AREA_BACKUP,BAY_FLAG"}
			})
 	    }]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget52301_2'
 	}
	]
});
