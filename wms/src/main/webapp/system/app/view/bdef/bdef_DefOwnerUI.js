/**
 * 模块名称：货主资料维护
 * 模块编码：1D01
 * 创建：lich
 */
var storeBdef_DefOwner1D01=Ext.create('cms.store.bdef.bdef_DefOwnerStore');
Ext.define('cms.view.bdef.bdef_DefOwnerUI',{
	alias:'widget.bdef_DefOwnerUI',
	title:$i18n.title1D01,//货主资料维护
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.wms_DefStrategyCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1D01',
	    region:'north'
    },{
	    xtype:'form',
	    id:'formOwner1D01',
	    layout:'column',
		frame : true,
		region : 'north',
		width : '100%',
		height:'8%',
		items:[{
		    layout:{
				type : 'table',
				columns : 2
			},
			xtype:'container',
			defaults:{
				margin : '10 2 2 0',
				labelAlign : 'right',
				xtype:'textfield'
			},
	    items:[	
        {
	   		xtype:'textfield',
			xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner_no,//货主编号
		    id:'cmbFormOwner1D01',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
			displayField : 'dropValue',
			valueField : 'value'
		},{
 			fieldLabel :$i18n.status, //状态
 			id:'statusText1D01',
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
        }
	    ]}
		]},{
	    xtype:'grid',
	    id:'gridBdefDefOwner1D01',
	    region:'center',
	    store:storeBdef_DefOwner1D01,
	    columns:[{			
	        xtype : 'rownumberer',
		    width : 30
	    },{
		    width:80,
		    text : $i18n.owner_no,//货主编码
		    dataIndex:'ownerNo'			
	    },{
		    width:160,
		    text : $i18n.owner_name,//货主名称
		    dataIndex:'ownerName'			
	    },{
	    	width:100,
	    	text: $i18n.owner_alias,//货主简称
	    	dataIndex:'ownerAlias'
	    },{
		    width:140,
		    text : $i18n.owner_address,//货主地址
		    dataIndex:'ownerAddress'			
	    },{
		    width:100,
		    text : $i18n.owner_phone,//货主电话
		    dataIndex:'ownerPhone'			
	    },{
		    width : 100,
		    text : $i18n.owner_fax,//货主传真
		    dataIndex : 'ownerFax'
	    },{
		    width:100,
		    text : $i18n.owner_contact,//货主联系人
		    dataIndex:'ownerContact'			
	    },{
	    	 width:100,
			 text : '储位管理类型',//储位管理类型
			 dataIndex:'cellType'
	    },{
	    	 width:100,
			 text : '默认储位',//默认储位
			 dataIndex:'typeValue'
	    },{
		    width:100,
		    text : $i18n.status,//状态
		    dataIndex:'statusText'			
	    },{
		    width:100,
		    text : '报关号',//报关号
		    dataIndex:'rsvVar2'			
	    },{
		    width:100,
		    text : '账册',//账册
		    dataIndex:'rsvVar3'			
	    }],
	    dockedItems : 
	    [
		    {
		        xtype : 'pagingtoolbar',
			    store : storeBdef_DefOwner1D01,
			    dock : 'bottom',
			    displayInfo : true
			}
	    ]
	},{
		region:'south'
	}]
});