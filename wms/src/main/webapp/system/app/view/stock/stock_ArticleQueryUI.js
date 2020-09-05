Ext.define('cms.view.stock.stock_ArticleQueryUI', {
	alias : 'widget.stock_ArticleQueryUI',
	title:'商品库存查询',
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu4'
				],
	items : [
	{
		xtype : 'commMenuWidget4',
		id:'menuK201',
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
			fieldLabel : '参考项目',
			displayField: 'dropValue',
			queryMode:'local',
			valueField: 'value',
			id : 'cmbProjectSelectK201',
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
			        },
			        extraParams:{
			        	strReferenceItem:'K201'
					}
			    },
			    listeners:{
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							//Ext.getCmp('cmbProjectSelect9112').setValue(th.getAt(0).data.value);
							//_myAppGlobal.getController('cms.controller.report.report_ArticleStock_Controller').cmbProjectSelect9112Select(Ext.getCmp('cmbProjectSelect9112'));
						}else{
							//Ext.getCmp('cmbProjectSelect9112').getStore().removeAll();
							//Ext.getCmp('cmbProjectSelect9112').setValue(null);
							//_myAppGlobal.getController('cms.controller.report.report_ArticleStock_Controller').commonReportTitle9112(Ext.getCmp('cmbReportSelect9112').getValue(),"");
						}
					}
			    }
			}).load(),
			beforeLabelTextTpl : required
		},
		{
			xtype:'container',
	        margin:'0 0 0 20',
	        items:[
	        {
				xtype:'button',
				id:'btnSearchK201',
			  	text: '查询',
			  	margin : '5 5 5 5'
			},
			{
				xtype:'button',
				id:'btnProjectSetK201',
			  	text: '项目设定',
			  	margin : '5 5 5 5'
			}
	        ]
		}]
	},{
    	xtype : 'panel',
		id : 'panelReportColumnK201',
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