/**
 * 模块名称：出车回单
 * 模块编码：3926
 * 创建：hcx
 */
var Odata_CarReceiptStore=Ext.create('cms.store.odata.odata_CarReceiptStore',{autoLoad:true});
Ext.define('cms.view.odata.odata_CarReceiptUI',{
	alias:'widget.odata_CarReceiptUI',
	title:'周转箱管理',
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
		      'cms.view.common.bdef_DefCustCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget4',
	    id:'menu3926',
	    region:'north'
    },{
	    region:'center',
	    xtype:'grid',
	    id:'grid_01_3926',
	    store:Odata_CarReceiptStore,
	    columns:[
	    {
	        xtype:'rownumberer',
	        width:30
	    },{
	        width:70,
	        text : $i18n.owner_no,//委托业主编号
			dataIndex : 'ownerNo' 
	    },{
			width : 120,
			text : $i18n.owner_name,//委托业主名称
			dataIndex : 'ownerName'
		},{
	        width:130,
	        text:$i18n.locate_no,//波次号
	        dataIndex:'waveNo'
	    },{
	        width:130,
	        text:'配送对象',
	        dataIndex:'deliverNo'
	    },{
	        width:200,
	        text:$i18n.cust,//客户
	        dataIndex:'custNoText'
	    },{
	        width:90,
	        text:$i18n.deliverBox,//配送物流箱数
	        dataIndex:'deliverBox'
	    },{
	        width:90,
	        text:$i18n.backBox,//返回物流箱数
	        dataIndex:'backBox'
	    },{
	        width:90,
	        text:$i18n.car_no,//车辆代码
	        dataIndex:'carNo'
	    },{
	        width:90,
	        text:$i18n.deiver_worker,//司机
	        dataIndex:'driverWorker'
	    },{
	        width:90,
	        text:$i18n.status,//状态
	        dataIndex:'statusText'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			store : Odata_CarReceiptStore,
			dock : 'bottom',
			displayInfo : true
	    }] 
   },{
   	   region:'south'
   }]
});