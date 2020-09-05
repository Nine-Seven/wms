/**
 * 模块名称：租仓费用明细查询
 * 模块编码：B601
 * 创建：lich 
 */
var bill_Cost_ListStore=Ext.create('cms.store.bset.bill_Cost_ListStore',{autoLoad:false});
Ext.define('cms.view.bset.bill_Cost_List_QueryUI',{
	alias:'widget.bill_Cost_List_QueryUI',
	title: $i18n.titleB601, //租仓费用明细查询
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
	    xtype : 'toolbar',
		region:'north',
		items : [{
       	            text : $i18n.refresh,
		            iconCls : 'refresh',
		            id:'refresh'
                },
                {
		             text : '导出',
		             iconCls : 'export',
		             id:'export'
                 },
                 {
			         text : $i18n.titleupdate,//修改
			         iconCls : 'edit',
			         id : 'edit'
	             },
	             {
	            	 text : $i18n.undo,//撤销
	     			 iconCls : 'undo',
	     			 id : 'undo'
	             },
	             {
	            	 text : '上缴',
					 icon: 'system/extjs/resources/icons/fam/application_split.png',
	     			 id : 'paid'
	             },
	             {
	            	 text : $i18n.cancel1,//取消
	     			 iconCls : 'undo',
	     			 id : 'undoCost'
	             }]
	 },{
		xtype:'panel',
		region:'north',
		height: 60,
		layout: {
		    type: 'table',
	        columns: 3
		},
	    defaults : {
			xtype : 'textfield',
			margin : '3 3 3 3',
			labelAlign:'right',
			allowBlank: true,
			width : 280,
			labelWidth : 90
		},
		items:[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoB601',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load()
			},{
     			fieldLabel :$i18n.billingProject, //计费项目
     			id:'billingProjectB601',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'bill_FormulasetAction_getBillingProjectForUI',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    }).load()
     		},{
     			fieldLabel :$i18n.status, //状态
     			id:'statusB601',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'bill_ResetBillAction_getStatusList',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    }).load()
     		},{
	        	xtype:'datefield',
	            fieldLabel: '开始日期',
	            format : 'Y-m-d',
	            id: 'dtBeginDateCostB601',
	            beforeLabelTextTpl : required
	        }, {
	        	xtype:'datefield',
	            fieldLabel: '结束日期',
	            format : 'Y-m-d',
	            id: 'dtEndDateCostB601',
	            beforeLabelTextTpl : required
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	name:'btnQueryB601',
	            text : '查询'
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Cost_B601',
	    store:bill_Cost_ListStore,
	    multiSelect: true,  
		  selModel: {  
			   selType:'checkboxmodel'  
		  },
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 70,
			text : $i18n.warehouse,//仓别
			dataIndex:'warehouseNo'
	    },{
	    	width: 70,
  		    text : $i18n.owner_no,  //货主编号
  		    dataIndex:'ownerNo'		
	    },{
	    	width: 110,
  		    text :  $i18n.billingProject,  //计费项目
  		    dataIndex:'billingProject'		
		},{
			width: 100,
  		    text : '计费日期',  //计费日期
  		    dataIndex:'billingDate'		
		},{
			width: 80,
  		    text : $i18n.billingUnit,  //计费单位
  		    dataIndex:'billingUnit'		
		},{
			width: 70,
  		    text : '计费单价',//$i18n.unitPrice,  //计费单价
  		    dataIndex:'unitPrice'		
		},{
			width: 90,
  		    text : '计费值',  //
  		    dataIndex:'qty'		
		},
//		{
//			width: 90,
//  		    text : '消费值',  //
//  		    dataIndex:'value'		
//		},
		{
			width: 100,
  		    text : '金额（元）',  //金额（元）
  		    dataIndex:'amount'		
		},{
			width: 130,
			text : $i18n.appendCondition,  //附加条件
  		    dataIndex:'appendCondition'		
		},{
			width: 80,
			text : $i18n.value1,  //值1
  		    dataIndex:'appendValue1'		
		},{
			width: 80,
			text : $i18n.value2,  //值2
  		    dataIndex:'appendValue2'		
		},{
			width: 80,
			text : $i18n.status,  //状态
  		    dataIndex:'statusText'		
		}],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bill_Cost_ListStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});