/**
 * 模块名称：储位货主关系维护
 * 模块编码：2601
 * 创建：hkl 
 */

 var cset_DefCellStore=Ext.create('cms.store.cset.cset_DefCellStore',{autoLoad:false});
 var cset_cell_ownerStore=Ext.create('cms.store.cset.cset_cell_ownerStore');
Ext.define('cms.view.cset.cset_CellOwnerUI',{
	alias:'widget.cset_CellOwnerUI',
	title:$i18n.title2601,//储位货主关系维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[       
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.cdef_DefAreaCombo',
	          'cms.view.common.cdef_DefWareCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
    	xtype : 'form',
	    id : 'form_01_2601',
	    region:'north',
	    frame : true,
	    layout : 'column',
	    defaults : {
			labelWidth : 90,
			margin : '2 2 2 2',
			labelAlign : 'right',
			width : 400
	    },
	    items :[
	    {
        	xtype:'bdef_DefOwnerCombo',
			fieldLabel : $i18n.owner_no,//货主编号
			id:'owner_no2601',
			displayField : 'dropValue',
		    valueField : 'value',
	        //allowBlank : false,
	        //editable:false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				proxy:{
					type:'ajax',
					method:'POST',
					url:'cset_CellOwnerAction_getOwnerCombo',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				},
				listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('owner_no2601').setValue(records[0].data.value);		
							var wheresql={
									ownerNo:Ext.getCmp('owner_no2601').getValue()
								};
							Ext.apply(Ext.getCmp('grid_02_2601').getStore().proxy.extraParams,wheresql);
							Ext.getCmp('grid_02_2601').getStore().removeAll();
							Ext.getCmp('grid_02_2601').getStore().load();
					}
					}
				},
			   autoLoad:true
			}),
	        beforeLabelTextTpl : required
		}]
    },{
    	title:$i18n.cellList,//货位列表
    	layout:'border',
		width:'47%',
		region:'west',
		items:[{
			xtype:'panel',
			region:'north',
			height: 30,
			layout: {
			    type: 'table',
		        columns: 3
			},
		    defaults : {
				xtype : 'textfield',
				margin : '3 0 3 3',
				labelAlign:'right',
				allowBlank: true,
				width : 200,
				labelWidth : 100
			},
			items:[
		    {
				fieldLabel:$i18n.cell,//货位
				id:'cell_no2601',
			},{
				xtype: 'button',
            	text: $i18n.query,
            	width : 50,
            	margin : '3 3 3 20',
            	id:'btnQuery2601'
		    }]
		},{
		xtype:'grid',
		id:'grid_01_2601',
		region:'center',
		store:cset_DefCellStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 50
		},{
			width:85,
			text:$i18n.ware_no,//库区
			dataIndex:'wareNo',
		},{
			width:150,
			text:$i18n.area_no,//储区
			dataIndex:'areaNo'
		},{
			width:100,
			text:$i18n.cell,//货位
			dataIndex:'cellNo'
		}]
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
			margin:'10 0 0 8'
		},
		items : [{
			xtype:'button',
			margin:'100 0 80 8',
			text:'>>>',
			id:'right2601'
		},
		{
			xtype:'button',			
			text:'<<<',
			id:'left2601'
		}]
	},{
		title:$i18n.cell_owner,//储位货主关系列表
    	layout:'border',
    	width:'47%',
  	    region:'east',
		items:[{
			xtype:'panel',
			region:'north',
			height: 30,
			layout: {
			    type: 'table',
		        columns: 3
			},
		    defaults : {
				xtype : 'textfield',
				margin : '3 0 3 3',
				labelAlign:'right',
				allowBlank: true,
				width : 200,
				labelWidth : 100
			},
			items:[
		    {
				fieldLabel:$i18n.cell,//货位
				id:'cell_no2601_2',
			},{
				xtype: 'button',
            	text: $i18n.query,
            	width : 50,
            	margin : '3 3 3 20',
            	id:'btnQuery2601_2'
		    }]
		},
		{
	    xtype:'grid',
	    id:'grid_02_2601',
	    region:'center',
	    store:cset_cell_ownerStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:$i18n.owner_no,//货主
			dataIndex:'ownerNo'
	    },{
			width:120,
			text:$i18n.owner_name,//货主名称
			dataIndex:'ownerName'
	    },{
			width:200,
			text:$i18n.addcell_no,//货位
			dataIndex:'cellNo'
	    }],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:cset_cell_ownerStore,
			displayInfo : true
		}]
		}]
	},{
    	region:'south'
	}]
});