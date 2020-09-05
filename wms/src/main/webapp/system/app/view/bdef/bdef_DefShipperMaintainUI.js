/**
 * 模块名称：承运商资料维护
 * 模块编码：1801
 * 创建：hj
 */
var defShipperMaintainStore = Ext.create("cms.store.bdef.bdef_DefShipperStore",{
	autoLoad:true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_DefShipperAction_getDefShipperMaintainList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1801').getStore().count()>0){
				Ext.getCmp('grid_01_1801').getSelectionModel().select(0);
			}
		}
	}
});

/*var oset_DefLineStore=Ext.create('cms.store.oset.oset_DefLineStore',{autoLoad:true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_DefShipperAction_queryLineList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

var oset_ShipperLineStore=Ext.create('cms.store.oset.oset_ShipperLineStore');*/

Ext.define('cms.view.bdef.bdef_DefShipperMaintainUI',{
	alias:'widget.bdef_DefShipperMaintainUI',
	title:'承运商信息维护',//承运商信息维护
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1801',
	    region:'north'
    },{
	    xtype:'form',
	    id:'formShipper1801',
	   /* region:'north',
	    right: 0,
		frame : true,*/
	    layout:'column',
		frame : true,
		region : 'north',
		width : '100%',
		height:'7%',
		items:[{
		    layout:{
				type : 'table',
				columns : 4
			},
			xtype:'container',
			defaults:{
				margin : '2 2 2 0',
				labelAlign : 'right',
				xtype:'textfield'
			},
	    items:[
	    {
			xtype : 'remoteCombo',
			fieldLabel:$i18n.shipper_no,		//承运商编号
			id:'shipperNo1801',
			store : Ext.create("cms.store.idata.idata_PoNoStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_DefShipperAction_queryBdefDefShipperCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			})
	    },{
 			fieldLabel :$i18n.status, //状态
 			id:'statusText1801',
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
        	fieldLabel :$i18n.shipper_type, //承运商类型
        	xtype:'wms_DefFieldValCombo',
    	    id:'shipperType1801',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
				params:{str:"BDEF_DEFSHIPPER,SHIPPER_TYPE"}
			})
        
        },{
			xtype:'button',
			id:'btnSearch1801',
		  	text: '查询'
		}
	    ]}
		]
	
    },{
    	region:'center',
	    xtype:'grid',
	    id:'grid_01_1801',
	    height:250,
	    store:defShipperMaintainStore,
	    columns:[
	    {
	        xtype:'rownumberer',
	        width:30
	    },{
	        width:80,
	        text:$i18n.shipper_no,//承运商编号
	        dataIndex:'shipperNo'
	    },{
	        width:90,
	        text:$i18n.shipper_name,//承运商名称
	        dataIndex:'shipperName'
	    },{
	        width:110,
	        text:$i18n.shipper_type,//承运商类型
	        dataIndex:'shipperTypeText'
	    },{
	        width:160,
	        text:$i18n.address,//地址
	        dataIndex:'address'
	    },{
	        width:60,
	        text:$i18n.contact,//联系人
	        dataIndex:'contact'
	    },{
	        width:120,
	        text:$i18n.cust_phone1,//联系电话
	        dataIndex:'tel'
	    },{
	        width:65,
	        text:$i18n.status,//状态
	        dataIndex:'statusText'
	    },{
	        width:65,
	        text:$i18n.paper_type,//面单类型
	        dataIndex:'paperTypeText'
	    },{
	        width:65,
	        text:$i18n.disprice,//里程单价
	        dataIndex:'disprice'
	    },{
	        width:65,
	        text:$i18n.graprice,//重量单价
	        dataIndex:'graprice'
	    },{
	        width:65,
	        text:$i18n.volprice,//材积单价
	        dataIndex:'volprice'
	    },{
	        width:80,
	        text:$i18n.compact_date,//合同日期
	        dataIndex:'compactDate'
	    },{
	        width:80,
	        text:$i18n.end_date,//到期日
	        dataIndex:'endDate'
	    }],
	    dockedItems : [{
	        xtype : 'pagingtoolbar',
	        store : defShipperMaintainStore,
	        dock : 'bottom',
	        displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});