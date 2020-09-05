/**
 * 模块名称：客户主档
 * 模块编码：1501
 * 创建：Jun
 */
var defCustStore = Ext.create("cms.store.bdef.bdef_DefCustStore",{autoLoad:true});
Ext.define('cms.view.bdef.bdef_DefCustUI',{
	alias:'widget.bdef_DefCustUI',
	title:$i18n.custTitle,
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1501',
	    region:'north'
    },{
	    xtype:'form',
	    id:'formOwner1501',
	    region:'north',
	    right: 0,
		frame : true,
	    layout:{
   			type:'table',
   			columns:4
	    },
	    defaults : {
	   		labelWidth : 65,
	   		labelAlign:'right'			
	   	},
	    items:[{
	   		xtype:'textfield',
			xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner_no,//货主编号
		    id:'cmbFormOwner1501',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
			displayField : 'dropValue',
			valueField : 'value'
		},{
 			fieldLabel :$i18n.status, //状态
 			id:'statusText1501',
			xtype:'wms_DefFieldValCombo',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
			 proxy:{
				type:'ajax',
				method:'post',
 				url:'bdef_DefOwnerAction_getStatusList',
 				reader:{
					root:'rootList',
					totalProperty:'totalCount'
					}
				}
		    }).load()
        },{

			xtype : 'remoteCombo',
			fieldLabel : $i18n.cust_no,//客户编码
			id:'custInfoNo1501',
			store : Ext.create("cms.store.idata.idata_PoNoStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_DefCustAction_getCustInfo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			})
		
			
	    },{
			xtype:'button',
			id:'btnSearch1501',
		  	text: '查询',
		  	margin:'0 0 5 20'
		}]
    },{
	    region:'center',
	    xtype:'grid',
	    id:'grid_01_1501',
	    width:'100%',
	    height:'100%',
	    store:defCustStore,
	    columns:[
	    {
	        xtype:'rownumberer',
	        width:30
	    },{
	        width:90,
	        text:$i18n.owner_no,//委托业主编号
	        dataIndex:'ownerNo'
	    },{
	        width:90,
	        text:$i18n.owner_name,//货主名称
	        dataIndex:'ownerName'
	    },{
	        width:110,
	        text:$i18n.owner_cust_no,//委托业主客户代码
	        dataIndex:'ownerCustNo'
	    },{
	        width:80,
	        text:$i18n.cust_no,//客户编码
	        dataIndex:'custNo'
	    },{
	        width:160,
	        text:$i18n.cust_name,//客户名称
	        dataIndex:'custName'
	    },{
	        width:120,
	        text:$i18n.cust_alias,//客户简称
	        dataIndex:'custAlias'
	    },{
	        width:65,
	        text:$i18n.cust_type,//客户类型
	        dataIndex:'custtypeText'
	    },{
	        width:65,
	        text:$i18n.status2,//操作状态
	        dataIndex:'statusText'
	    },{
	        width:80,
	        text:$i18n.shipping_method,//客户出货方式
	        dataIndex:'shippingmethodText'
	    },{
	        width:65,
	        text:$i18n.prio_type,//优先方式
	        dataIndex:'priotypeText'
	    },{
	        width:80,
	        text:$i18n.prio_level,//配量优先等级
	        dataIndex:'prioLevel'
	    },{
	        width:100,
	        text:$i18n.box_deliver,//物流箱发货标记
	        dataIndex:'boxdeliverText'
	    },{
	        width:80,
	        text:$i18n.cust_province,//省份
	        dataIndex:'custProvince'
	    },{
	        width:80,
	        text:$i18n.cust_city,//县市
	        dataIndex:'custCity'
	    },{
	        width:100,
	        text:$i18n.cust_phone,//客户电话
	        dataIndex:'custPhone1'
	    }],
	    dockedItems : [{
	        xtype : 'pagingtoolbar',
	        store : defCustStore,
	        dock : 'bottom',
	        displayInfo : true
	    }]
   },{
   	   region:'south'
   }]
});