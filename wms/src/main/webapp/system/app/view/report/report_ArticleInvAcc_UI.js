Ext.define('cms.view.report.report_ArticleInvAcc_UI', {
	alias : 'widget.report_ArticleInvAcc_UI',
	title:'商品库存帐',
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu4'
				],
	items : [
	{
		xtype : 'commMenuWidget4',
		id:'menu9111',
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
			xtype : 'datefield',
			fieldLabel : '开始时间',//日期
			id : 'date9111',								
			format : 'Y-m-d',
			maxValue:Ext.Date.add(new Date(), Ext.Date.DAY, -1),
			beforeLabelTextTpl : required	
		},
		{
			xtype : 'datefield',
			fieldLabel : '结束时间',//日期
			id : 'date9112',								
			format : 'Y-m-d',
			maxValue:Ext.Date.add(new Date(), Ext.Date.DAY, -1),
			beforeLabelTextTpl : required	
		},
		{
			xtype:'container',
	        margin:'0 0 0 20',
	        items:[
	        {
				xtype:'button',
				id:'btnSearch9111',
			  	text: '查询',
			  	margin : '5 5 5 5'
			}]
		}]
	},{
    	xtype : 'panel',
		id : 'panelReportColumn9111',
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