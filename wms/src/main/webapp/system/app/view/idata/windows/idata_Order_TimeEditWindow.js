/**
 * 预约时间修改
 * 模块编码：4201
 * 创建：hekl 
 */

Ext.define('cms.view.idata.windows.idata_Order_TimeEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.idata_Order_TimeEditWindow',
 	layout:'border',
    width : 350,
	height : 430,
 	modal:true,
 	id:'idata_Order_TimeEditWindow',
 	items:[{
 		xtype : 'form',
		id : 'form_01_4201_win',
		region : 'north',
		height:100,
		frame : true,
		layout: 
        {
        	type: 'table',
        	columns: 1
        },
		defaults : 
        {
        	xtype : 'textfield',
        	labelWidth : 110,
        	labelAlign:'right'			
 	    },
		items :[{
			fieldLabel : $i18n.order_serial,//流水号
			id : 'order_serial4201_win',
			beforeLabelTextTpl : required,
			readOnly:true
		 },{

		 	xtype:'combo',
			fieldLabel : $i18n.dock,//码头
		 	id:'dockNo4201_2_win',
	        displayField: 'dropValue',
			valueField: 'value',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_DefDockAction_getDockComboList.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
				}).load(),
				beforeLabelTextTpl : required
	       },{
	        	xtype : 'datefield',
				fieldLabel : $i18n.appoint_date,//预约日期
				id : 'appoint_date4201_win',
				format : 'Y-m-d',
				value: new Date(),
				minValue:new Date(),
				beforeLabelTextTpl : required
	       }]		
 	      },{
			//预约状况>>已约信息
	        xtype: 'grid',
	        region: 'center',
	        width:350,
	        split: true,
	        margins: '0 0 0 5',
	        store:Ext.create('cms.store.idata.dock_SheetStore',{autoLoad:true}),
	        id: 'grid_06_4201_win',
	        viewConfig : 
	        {   
	             forceFit : true,   
	             getRowClass : function(record,rowIndex,rowParams,store){
	             if(Ext.getCmp('appoint_date4201_win').getValue()<new Date()){
	                //禁用数据显示红色   
	                if(record.data.endTime<Ext.Date.format(new Date(), 'H:i')){   
	                    return 'x-grid-record-red';   
	                 }else{   
	                    return '';   
	                 }   
	               }  
	            } 
	        },  
	        columns : [ {
				xtype : 'rownumberer',
				width : 30
				},{
				width : 120,
				text : $i18n.start_time,//开始时间
				dataIndex : 'startTime'
				},{
				width : 120,
				text : $i18n.endtime,
				dataIndex : 'endTime'
				}]
			},{
				region:'south',
		    	xtype:'commMenuWidget5',
		    	border:0,
		    	id:'bdef_MenuWidget1401_win'
			}]
});