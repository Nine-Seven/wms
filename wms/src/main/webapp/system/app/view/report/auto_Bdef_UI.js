Ext.define('cms.view.report.auto_Bdef_UI', {
	alias : 'widget.auto_Bdef_UI',
	title:'基础资料设置',
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu4'
				],
	items : [
	{
		xtype : 'commMenuWidget4',
		id:'menuG201',
		region:'north'
    },
	{
    	xtype : 'form',
		height : 35,
		frame:false,
		region:'north',
		layout:{
    		type:'table',
    		columns:1
    	},
    	defaults : {
			xtype:'textfield',
			labelWidth : 90,
			margin : '2 2 4 2',
			labelAlign : 'right'
  		},
		items: [
		{
			xtype : 'combo',
			fieldLabel : '设置选择',
			displayField: 'dropValue',
			valueField: 'value',
			id : 'cmbReportSelectG201',
			width:300,
    	    store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url : 'auto_SetAction_getReportList',
			        reader: {
			    		type:'json',
			            root: 'rootList',    
			            totalProperty: 'totalCount'
			        },
					extraParams:{
						modubleId : 'G201'
					}
			    }
			}),
			beforeLabelTextTpl : required
		}]
	},{
    	xtype : 'panel',
		id : 'panelReportColumnG201',
		region:'center',
		layout:'fit',
		frame:false,
		region:'center',
		items: [{

			xtype:'panel',
			region:'center',
			id:'form_01_G201',
			frame : true,
			layout:{
				type: 'table',
				columns: 2
			},
			defaults : {
				xtype : 'textfield',
				margin : '5 0 5 0',
				labelAlign:'right'
			
			},
			items:[]
		
		}]
	},{
		xtype:'panel',
		region:'south'
	}
	]
});