/**
 * 模块名称：科目和计费项目的关系维护UI
 * 模块编码：B301
 * 创建：chensr 
 */
var bill_Account_MStore=Ext.create('cms.store.bset.bill_Account_MStore',{autoLoad:true});
var bill_Account_DStore=Ext.create('cms.store.bset.bill_Account_DStore',{autoLoad:false});
var bill_Account_FormulasetStore=Ext.create('cms.store.bset.bill_Account_FormulasetStore',{autoLoad:false});
Ext.define('cms.view.bset.bill_AccountUI',{
	alias:'widget.bill_AccountUI',
	title: $i18n.titleB301, //科目和计费项目的关系维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.commMenu5',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.wms_DefStrategyCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menuB301',
	    region:'north'
	},{
		xtype:'panel',
			region:'north',
			height: 35,
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
				id:'ownerNoUIB301',
				xtype:'wms_DefFieldValCombo',
				 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
						type:'ajax',
						method:'post',
						url:'bill_AccountAction_getOwnerNoForQuery',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
					}
					}
		    }).load(),
			    displayField : 'dropValue',
			    valueField : 'value'
			},{
				fieldLabel :$i18n.account, //科目
				id:'accountUIB301',
				xtype:'wms_DefFieldValCombo',
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
			    }).load(),
			    displayField : 'dropValue',
			    valueField : 'value'
			}]
		},{
	    xtype:'grid',
	    region:'north',
	    height:240,
	    id:'account_MUIB301',
	    store:bill_Account_MStore,
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
	  		    text :  $i18n.account_no,  //科目代码
	  		    dataIndex:'accountNo'		
		},{
	    	 width:160,
	  		    text : $i18n.account_name,  //科目名称
	  		    dataIndex:'accountName'		
		},{
	    	 width: 110,
	  		    text : $i18n.account_type,  //科目类型
	  		    dataIndex:'accountTypeText'		
		},/*{
	    	 width: 60,
	  		    text : $i18n.account_cycle,  //科目周期
	  		    dataIndex:'cycle'		
		},{
	    	 width: 130,
	  		    text : $i18n.next_account_date,  //下一次生成账务日期
	  		    dataIndex:'nextAccountDate'		
		},*/{
	    	 width: 120,
	  		    text : $i18n.discount_flag,  //优惠方式
	  		    dataIndex:'discountFlagText'		
		},{
	    	 width: 80,
	  		    text : $i18n.value1,  //值1
	  		    dataIndex:'value1'		
		},{
	    	 width: 80,
	  		    text : $i18n.value2,  //值2
	  		    dataIndex:'value2'		
		},{
	    	 width: 110,
	  		    text : $i18n.discountAccountNo,  //优惠科目代码
	  		    dataIndex:'discountAccountNo'		
		},{
	    	 width: 110,
	  		    text : $i18n.remark,  //备注
	  		    dataIndex:'remark'		
		}],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bill_Account_MStore,
			displayInfo : true
	    }]
	},{
		xtype:'grid',
		id:'grid_Formulaset_B301',
		title:$i18n.billingProject,//"计费项目",
		width:'47%',
		region:'west',
		store:bill_Account_FormulasetStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:160,
			text:$i18n.billingProjectNo,  //计费项目编号
			dataIndex:'billingProject',
			sortable: false
		},{
			width:160,
			text:$i18n.projectName,  //计费项目名称
			dataIndex:'projectName',
			sortable: false
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bill_Account_FormulasetStore,
			displayInfo : true
		}]
	},{
		xtype : 'form',
		region : 'center',
		layout:{
			type:'table',
			columns:1
		},
		width:'6%',
		frame : true,
		defaults:{
			margin:'10 0 0 0'
		},
		items : [
		{
			xtype:'button',
			margin:'80 0 0 0',
			text:'<<<',
			id:'leftB301'
		},{
			xtype:'button',
			text:'>>>',
			id:'rightB301'
		}]
	},{
	    xtype:'grid',
	    id:'grid_AccountRelaction_B301',
	    title:$i18n.accountRelaction,//计费项目与科目的关系
	    width:'47%',
	    region:'east',
	    store:bill_Account_DStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:$i18n.account,    //科目
			dataIndex:'accountName'
	    },{
			width:120,
			text:$i18n.billingProjectNo,  //计费项目编号
			dataIndex:'billingProject',
			sortable: false
		},{
			width:120,
			text:$i18n.projectName,  //计费项目名称
			dataIndex:'projectName',
			sortable: false
		}],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bill_Account_DStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});