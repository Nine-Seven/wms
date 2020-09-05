Ext.define('cms.view.report.report_Count_RidataUI', {
	alias : 'widget.report_Count_RidataUI',
	title:'返配分析',	
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu4'
				],
	items : [
	{
		xtype : 'commMenuWidget4',
		id:'menuH103',
		region:'north'
    },
	{
    	xtype : 'form',
		height : 35,
		frame:false,
		region:'north',
		layout:{
    		type:'table',
    		columns:3
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
			fieldLabel : '报表选择',
			displayField: 'dropValue',
			valueField: 'value',
			id : 'cmbReportSelectH103',
			width:300,
    	    store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url : 'report_Action_getReportList',
			        reader: {
			    		type:'json',
			            root: 'rootList',    
			            totalProperty: 'totalCount'
			        },
					extraParams:{
						moduleId : 'H103'
					}
			    },
			    listeners:{
					'load':function(th,records,successful,eOpts){
						if(th.count()>0){
							//Ext.ComponentQuery.query("button[name=btnProjectSet]")[0].setDisabled(false);
						}else{
							//Ext.ComponentQuery.query("button[name=btnProjectSet]")[0].setDisabled(true);
						}
					}
			    }
			}),
			beforeLabelTextTpl : required
		},{
			xtype : 'combo',
			fieldLabel : '参考项目',
			displayField: 'dropValue',
			queryMode:'local',
			valueField: 'value',
			id : 'cmbProjectSelectH103',
    	    store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url : 'report_Action_getReferenceItemList.action',
			        reader: {
			    		type:'json',
			            root: 'rootList',    
			            totalProperty: 'totalCount'
			        }
			    },
			    listeners:{
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							//Ext.getCmp('cmbProjectSelectH103').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.report.report_Count_RidataController').cmbProjectSelectH103Select(Ext.getCmp('cmbProjectSelectH103'));
						}else{
							Ext.getCmp('cmbProjectSelectH103').getStore().removeAll();
							Ext.getCmp('cmbProjectSelectH103').setValue(null);
							_myAppGlobal.getController('cms.controller.report.report_Count_RidataController').commonReportTitleH103(Ext.getCmp('cmbReportSelectH103').getValue(),"");
						}
					}
			    }
			}),
			beforeLabelTextTpl : required
		},
		{
			xtype:'container',
	        margin:'0 0 0 20',
	        items:[
	        {
				xtype:'button',
				id:'btnSearchH103',
			  	text: '查询',
			  	margin : '5 5 5 5'
			},
			{
				xtype:'button',
				id:'btnProjectSetH103',
			  	text: '项目设定',
			  	margin : '5 5 5 5'
			}
	        ]
		}]
	},{
    	xtype : 'panel',
		id : 'panelReportColumnH103',
		region:'center',
		layout:'fit',
		frame:false,
		region:'center',
		items: []
	},{
		xtype:'panel',
		region:'south'
	}
	]
});