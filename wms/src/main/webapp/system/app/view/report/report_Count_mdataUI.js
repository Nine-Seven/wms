Ext.define('cms.view.report.report_Count_mdataUI', {
	alias : 'widget.report_Count_mdataUI',
	id:'report_Count_mdataUIH105',
	title:'移库分析',
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu4'
				],
	items : [
	{
		xtype : 'commMenuWidget4',
		id:'menuH105',
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
			id : 'cmbReportSelectH105',
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
						moduleId : 'H105'
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
			id : 'cmbProjectSelectH105',
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
							//Ext.getCmp('cmbProjectSelectH105').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.report.report_Count_OdataController').cmbProjectSelectH105Select(Ext.getCmp('cmbProjectSelectH105'));
						}else{
							Ext.getCmp('cmbProjectSelectH105').getStore().removeAll();
							Ext.getCmp('cmbProjectSelectH105').setValue(null);
							_myAppGlobal.getController('cms.controller.report.report_Count_OdataController').commonReportTitleH105(Ext.getCmp('cmbReportSelectH105').getValue(),"");
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
				id:'btnSearchH105',
			  	text: '查询',
			  	margin : '5 5 5 5'
			},
			{
				xtype:'button',
				id:'btnProjectSetH105',
			  	text: '项目设定',
			  	margin : '5 5 5 5'
			}
	        ]
		}]
	},{
    	xtype : 'panel',
		id : 'panelReportColumnH105',
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