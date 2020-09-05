/**
 * 模块名称：出货装车作业(天天惠)
 * 模块编码：3912
 * 创建：chensr
 */
var stock_label_m=Ext.create('cms.store.odata.odata_LabelMForTthStore',{autoLoad:false,
	listeners:{
		'load':function(){
			Ext.getCmp('grid_01_3912').getSelectionModel().selectAll();
			Ext.getCmp('grid_01_3912').getSelectionModel().setLocked(true);
		}
	}
});
Ext.define('cms.view.odata.odata_CarPlanForTthUI',{
	alias:'widget.odata_CarPlanForTthUI',
	title:$i18n.title3801,//出货装车作业
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			  'cms.view.common.commMenu4',
			  'cms.view.common.bdef_DefWorkerCombo',
			  'cms.view.common.wms_DefFieldValCombo',
			  'cms.view.common.bdef_DefcarCombo'
	],
	items:[
	{
		xtype : 'commMenuWidget4',
		region:'north',
		id:'menu3912'
	},{
		region:'west',
		xtype:'panel',
		width:'55%',
		layout:'border',
		items:[
		{
			xtype:'form',
			id:'form_01_3912',
			region:'north',
			frame:true,
			layout:{
    	        type:'table',
    	        columns:4
	    	},
	    	defaults : {
				xtype:'textfield',
				labelWidth : 70,
				margin : '2 2 4 2',
				labelAlign : 'right'
	  		},
			items:[
			{
				xtype : 'combo',
				fieldLabel : $i18n.cust_name,// 客户名称
				id:'deliver_obj3912',
				displayField: 'dropValue',
				valueField: 'value',
				colspan:4,
 	    	    store:Ext.create("cms.store.common.comboStore",
				{
					proxy:{
						type:'ajax',
						method:'post',
						url:'odata_CarPlanForTthAction_getCust.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					},
					listeners:{
						'load':function(th,records,successful,eOpts )
						{
							if(Ext.getCmp('deliver_obj3912').getStore().data.length>0)
							{
								Ext.getCmp('deliver_obj3912').setValue(Ext.getCmp('deliver_obj3912').getStore().getAt(0).data.value);		
							}
						}
					},
					autoLoad:true
   				}),
   				beforeLabelTextTpl : required
			},{
				xtype : 'combo',
				fieldLabel:$i18n.line_no,//线路代码
				id:'line_no3912',
				displayField: 'dropValue',
				valueField: 'value',
 	    	    store:Ext.create("cms.store.common.comboStore",
				{
					proxy:{
						type:'ajax',
						method:'post',
						url:'odata_CarPlanForTthAction_getLineNoCombo.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
   			}),
   				beforeLabelTextTpl : required
			},{
				xtype:'button',
				text:$i18n.query,//查询
				id:'button_01_3912',
				margin:'0 0 0 15'
			},{
    	    	xtype:'button',
    	    	text:$i18n.search_detail,//查看明细
    	    	id:'button_02_3912',
    	    	margin:'0 0 0 15'
    	    }]
		},{
			region:'center',
	    	xtype:'grid',
	    	id:'grid_01_3912',
	    	store:stock_label_m,
	    	multiSelect: true,  
			selModel: {  
				selType:'checkboxmodel'
			},
	    	columns:[
	    	{
	    		xtype:'rownumberer',
	    	    width:30    
	    	    
	    	},{
	    	    width:140,
	    	    text:$i18n.cust_no,//客户编码
	    	    dataIndex:'custNo'
	    	},{
	    	    width:140,
	    	    text:$i18n.cust_name,//客户名称
	    	    dataIndex:'custName'
	    	},{
	    	    width:140,
	    	    text:$i18n.label_no,//标签号码
	    	    dataIndex:'labelNo'
	    	}],
	    	dockedItems : [{
	    	    xtype : 'pagingtoolbar',
	    	    store : stock_label_m,
	    	    dock : 'bottom',
	    	    displayInfo : true
	    	}]
		}]
	},{
		region:'east',
		xtype:'panel',
		width:'45%',
		layout:'border',
		items:[
		{
			xtype:'form',
			id:'form_02_3912',
			region:'north',
			frame:true,
			layout:{
	    		type:'table',
    	        columns:1
	    	},
			defaults:{
				xtype:'textfield',
				labelWidth : 90,
				margin : '2 2 2 2',
				labelAlign : 'right',
				width:300
			},
			items:[
			{
				fieldLabel:$i18n.line_no,//线路代码
				id:'line_nod2_3912',
				readOnly:true,
				beforeLabelTextTpl : required
			},{
				xtype:'datefield',
				fieldLabel:$i18n.erpoperate_date,//配送日期
				id:'erpoperate_date3912',
				format:'Y-m-d',
				readOnly:true,
				beforeLabelTextTpl : required
			},{
				xtype:'bdef_DefWorkerCombo',
            	fieldLabel:$i18n.load_name,//配送人员
            	store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
            	id:'load_name3912',
				beforeLabelTextTpl : required
			},{
				xtype:'bdef_DefcarCombo',
            	fieldLabel:$i18n.car_no,//车辆代码
            	id : 'car_no3912',	
				store:Ext.create("cms.store.bdef.bdef_DefcarComboStore").load(),
		        beforeLabelTextTpl : required
			},{
				fieldLabel:$i18n.seal_no,//封条号
            	id:'txtSealNo3912',
            	beforeLabelTextTpl : required
			}]
		},{
			region:'center',
	    	xtype:'grid',
	    	id:'grid_02_3912',
	    	store:Ext.create('cms.store.odata.odata_LabelMForTthStore'),
	    	columns:[
	    	{
	    		xtype:'rownumberer',
	    	    width:30 
	    	},{
	    	    width:140,
	    	    text:$i18n.cust_no,//客户编码
	    	    dataIndex:'custNo'
	    	},{
	    	    width:140,
	    	    text:$i18n.cust_name,//客户名称
	    	    dataIndex:'custName'
	    	},{
	    	    width:140,
	    	    text:$i18n.label_no,
	    	    dataIndex:'labelNo'
	    	},{
    	    	width : 80,
    	    	text : $i18n.deliver_obj,//配送对象
    	    	dataIndex : 'deliverObj'
    	    }]
		},{
			xtype:'form',
			region:'south',
			frame:true,
			items:[
			{
				xtype:'button',
				text:$i18n.produce_transport_list,//产生交运清单'
				id:'submit3912',
				margin:'0 0 0 270'
			}]
		}]
	}]
});