Ext.define('cms.view.cset.window.cset_AreaBackupAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.cset_AreaBackupAddOrEditWindow',
	layout : 'border',
	id : 'cset_AreaBackupAddOrEditWindow',
	height:180,
	width:600,	
	modal:true,
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.cdef_DefAreaCombo',
			  'cms.view.common.cdef_DefWareCombo',
			  'cms.view.common.cdef_DefStockCombo',
			  'cms.store.common.comboStore'
	],
	items:[{
		xtype:'form',
		region:'center',
		id:'cset_AreaBackupAddOrEditLine2301',
		frame:true,
		layout:'column',
		defaults:{
			xtype:'textfield',
			margin:'3 3 3 3',
			labelWidth:120,
			labelAlign:'right'
		},
		items:[{
 	    	xtype:'textfield',
 	    	fieldLabel:$i18n.line_id,//保拣线id
 	    	id:'textLineId2301',
 	    	maxLength:2,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
		},{
 	    	xtype:'textfield',
 	    	fieldLabel:$i18n.line_name,//保拣线名称
 	    	id:'txtLineName2301',
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
 	    },{
			xtype:'cdef_DefWareCombo',
			fieldLabel: $i18n.ware_no,
			id : 'cmbWareNo2301A',
			//mode : 'remote', // 远程访问
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'cset_AreaBackupAction_getCdef_DefWareComboByCsetM.action',
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
			id : 'cmbAreaNo2301A',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'cset_AreaBackupAction_getCdef_DefAreaComboByCsetM.action',
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
			id : 'cmbStockNo2301A',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'cset_AreaBackupAction_getCdef_DefStockComboByCsetM.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					} 
				}
			}),
			allowBlank: true
		},{
 	    	xtype:'bdef_DefDockCombo',
 	    	fieldLabel:$i18n.default_flag,//是否默认保拣线
 	    	id:'cmbDefaultFlag2301',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"N,DEFAULT_FLAG"}
			}),
 	    	beforeLabelTextTpl : required
 	    }]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget52301_1'
 	}
	]
});
