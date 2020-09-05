/**
 * 模块名称：租仓账单查询
 * 模块编码：B701
 * 创建：lich 
 */
var bill_FinancialStore=Ext.create('cms.store.bset.bill_FinancialStore',{autoLoad:false});
Ext.define('cms.view.bset.bill_Financial_QueryUI',{
	alias:'widget.bill_Financial_QueryUI',
	title: $i18n.titleB701, //租仓账单查询
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu4',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget4',
	    id:'menuB701',
	    region:'north'
	},{
		xtype:'panel',
		region:'north',
		height: 45,
		layout: {
		    type: 'table',
	        columns: 4
		},
	    defaults : {
			xtype : 'textfield',
			margin : '3 1 3 1',
			labelAlign:'right',
			allowBlank: true,
			width : 240,
			labelWidth : 60
		},
		items:[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoB701',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load()
			},{
				fieldLabel :$i18n.account, //科目
				id:'accountB301',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
 					type:'ajax',
 					method:'post',
 					url:'bill_AccountAction_getAccountForUI',
 					reader:{
						root:'rootList',
 						totalProperty:'totalCount'
						}
					}
			    }).load()
			},{
	        	xtype:'monthfield',
	            fieldLabel: '月份',
	            id: 'monthB701'
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	name : 'btnQueryB701',
	            text : '查询'
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Financial_B701',
	    store:bill_FinancialStore,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 110,
			text : $i18n.warehouse,//仓别
			dataIndex:'warehouseNo'
	    },{
	    	width: 110,
  		    text : $i18n.owner_no,  //货主编号
  		    dataIndex:'ownerNo'		
	    },{
	    	width: 110,
  		    text :  '月份',  //月份
  		    dataIndex:'billingMonth'		
		},{
	    	width: 110,
  		    text :  $i18n.account_no,  //科目代码
  		    dataIndex:'accountNo'		
		},{
			width: 80,
  		    text : '金额（元）',  //金额（元）
  		    dataIndex:'amount'		
		},{
			width: 100,
  		    text : '优惠金额（元）',  //金额（元）
  		    dataIndex:'discountAmount'		
		}],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bill_FinancialStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});