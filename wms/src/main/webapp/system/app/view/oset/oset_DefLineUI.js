/**
 * 线路
 * 创建人:Jun
 */
var defLine=Ext.create('cms.store.oset.oset_DefLineStore',{autoLoad:true,
	listeners  :{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1J01').getStore().count()>0){
				Ext.getCmp('grid_01_1J01').getSelectionModel().select(0);
			}
		}
	}
});
var lineCust=Ext.create('cms.store.oset.oset_Line_CustStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_03_1J01_Id').getValue()!=''&&Ext.getCmp('grid_03_1J01_Id').getValue()!=null){
				var cust=Ext.getCmp('grid_03_1J01_Id').getValue();
				for(var i=0 ; i<th.count();i++){
					var record  = Ext.getCmp('grid_03_1J01').getStore().getAt(i);
					if(cust==record.get('custNo')){
						Ext.getCmp('grid_03_1J01').getSelectionModel().select(i);
						return;
					}				
				}		
			}
		}
	}

});
var cust=Ext.create('cms.store.oset.oset_DefCustStore',{autoLoad:true});
Ext.define('cms.view.oset.oset_DefLineUI',{
	alias:'widget.oset_DefLineUI',
	title:$i18n.title1400001,
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.commMenu8',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
	{
	    xtype:'commMenuWidget2',
	    id:'menu1J01',
	    region:'north'
	},
	{
		xtype:'grid',
		region:'north',
		height:240,
		id:'grid_01_1J01',
		store:defLine,
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:120,
			text:$i18n.deliver_type,//配送方式
			dataIndex:'delivertypeText'
		},{
			width:150,
			text:$i18n.transportation,//运输方式
			dataIndex:'transporttypeText'
		},{
			width:150,
			text:$i18n.line_no,//线路代码
			dataIndex:'lineNo'
		},{
			width:150,
			text:$i18n.line_name1,//线路名称
			dataIndex:'lineName'
		},{
			width:150,
			text:$i18n.line_full_name,//线路全称
			dataIndex:'lineFname'
		}]},{
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
			items : [
			{
				xtype:'button',
				margin:'80 0 0 8',
				text:'>>>',
				id:'right1J01'
			},{
				xtype:'button',
				text:'<<<',
				id:'left1J01'
			}			
			]
		    },{
			xtype:'panel',
			width:'47%',
			region:'west',
			layout:'border',
			defaults : {
				labelWidth : 90,
				margin : '2 2 2 2',
				labelAlign : 'right'
			},
			items:[{
		   	    xtype:'form',
		   	    region:'north',
		   		//frame : true,
		   	    layout:{
		      			type:'table',
		      			columns:2
		   	    },
		   	    defaults : {
		   	   		labelWidth : 65,
		   	   		labelAlign:'right'			
		   	   	},
		   	    items:[{
		   			xtype:'bdef_DefOwnerCombo',
		   			fieldLabel:$i18n.owner_no,//货主编号
		   		    id:'cmbFormOwnerUI1J01',
		   			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
		   			displayField : 'dropValue',
		   			valueField : 'value'
		   		},{
		   			xtype:'textfield',
		   		    id:'grid_03_1J01_Id',
			        hidden:true
		   		}]
		   	},{
				xtype:'grid',
				id:'grid_02_1J01',
				title:'客户',
				width:'47%',
				region:'center',
				store:cust,
				multiSelect: true,  
			    selModel: {  
			        selType:'checkboxmodel'  
			    },
				columns:[{			
					xtype : 'rownumberer',
					width : 30
				},{
					width:85,
					text:$i18n.cust_no,//客户编号
					dataIndex:'custNo'
				},{
					width:250,
					text:$i18n.cust_name,//客户名称
					dataIndex:'custName'
				}]
			}]},{
				xtype:'grid',
				id:'grid_03_1J01',
				title:'线路明细',
				width:'47%',
				region:'east',
				store:lineCust,
				multiSelect: true,  
			    selModel: {  
			        selType:'checkboxmodel'  
			    },
				columns:[{			
					xtype : 'rownumberer',
					width : 30
				},{
					width:85,
					text:$i18n.cust_no,//客户编号
					dataIndex:'custNo'
				},{
					width:180,
					text:$i18n.cust_name,//客户名称
					dataIndex:'custName'
				},{
					width:80,
					text:$i18n.line_no,//线路代码
					dataIndex:'lineNo'
				},{
					width:80,
					text:$i18n.line_seq_no,//路顺
					dataIndex:'lineSeqNo'
				}],
				dockedItems : [{
					xtype : 'commMenuWidget8',
					region:'north'
		}]   

	},{
		region:'south'
	}]
});