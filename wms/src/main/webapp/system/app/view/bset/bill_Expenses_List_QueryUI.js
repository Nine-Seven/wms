/**
 * 模块名称：租仓消费清单查询
 * 模块编码：B501
 * 创建：lich 
 */
var bill_Expenses_ListStore=Ext.create('cms.store.bset.bill_Expenses_ListStore',{autoLoad:false});
Ext.define('cms.view.bset.bill_Expenses_List_QueryUI',{
	alias:'widget.bill_Expenses_List_QueryUI',
	title: $i18n.titleB501, //租仓消费清单查询
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
	    id:'menuB501',
	    region:'north'
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
				id:'ownerNoB501',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load()
			},{
     			fieldLabel :$i18n.billingProject, //计费项目
     			id:'billingProjectB501',
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
   			    }).load(),
   			    colspan:2
     		},{
	        	xtype:'datefield',
	            fieldLabel: '开始日期',
	            format : 'Y-m-d',
	            id: 'dtBeginDateExpB501',
	            beforeLabelTextTpl : required
	        }, {
	        	xtype:'datefield',
	            fieldLabel: '结束日期',
	            format : 'Y-m-d',
	            id: 'dtEndDateExpB501',
	            beforeLabelTextTpl : required
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	name:'btnQueryB501',
	            text : '查询'
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Exp_B501',
	    store:bill_Expenses_ListStore,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 80,
			text : $i18n.warehouse,//仓别
			dataIndex:'warehouseNo'
	    },{
	    	width: 80,
  		    text : $i18n.owner_no,  //货主编号
  		    dataIndex:'ownerNo'		
	    },{
	    	width: 180,
  		    text :  $i18n.billingProject,  //计费项目
  		    dataIndex:'billingProject'		
		},{
			width: 100,
  		    text : $i18n.begin_date,  //开始日期
  		    dataIndex:'beginDate'		
		},{
			width: 100,
  		    text : $i18n.end_time,  //结束日期
  		    dataIndex:'endDate'		
		},{
			width: 90,
  		    text : '消费值',  //
  		    dataIndex:'value'		
		},{
			width: 130,
  		    text : '面积(平方米)',  //面积(平方米)
  		    dataIndex:'area'		
		},{
			width: 120,
  		    text : '托盘数',  //托盘数
  		    dataIndex:'tray'		
		},{
			width: 80,
  		    text : '件数',  //件数
  		    dataIndex:'qty'		
		},{
			width: 80,
  		    text : '体积(立方米)',  //体积(立方米)
  		    dataIndex:'volume'		
		},{
	    	width: 110,
  		    text : '重量(吨)',  //重量(吨)
  		    dataIndex:'weight'		
		}],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bill_Expenses_ListStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});