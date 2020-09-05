/**
 * 模块名称：1、系统参数配置
 * 模块编码：B201
 * 创建：zhm
 */

var workstationStore=Ext.create('cms.store.bset.bset_ValuePolicySetStore',{autoLoad:true});
Ext.define('cms.view.bset.bset_ValuePolicySetUI',{
	alias:'widget.bset_ValuePolicySetUI',
	title:$i18n.titleB201,//工作站与打印机组关系维护
	layout:'border',
	extend:'Ext.panel.Panel',
	
	requires:[
		'cms.view.common.commMenu2',
		'cms.view.common.commMenu5',
		'cms.store.common.comboStore'
		],
	items:[
	   	{
			xtype : 'commMenuWidget2',
			id:'menuB201',
	    	region:'north'
		},	
		{
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
				xtype : 'combo',
				fieldLabel : $i18n.owner,// 货主
				id : 'cmbOwnerB201',
				displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
			   store:Ext.create("cms.store.common.comboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url : 'bset_ValuePolicySetAction_getAllOwnerComboList.action',
				        reader: {
				    		type:'json',
				            root: 'rootList',    
				            totalProperty: 'totalCount'
				        }
				    }
					}).load()
			}]
		},
		{
			xtype:'grid',		
			region:'center',
			id:'grid_01_B201',
			store:workstationStore,
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			},{
				width : 100,
				text : $i18n.warehouse,
				dataIndex : 'warehouseNoText'
			}
			,{
				width :150,
				text : $i18n.owner,
				dataIndex : 'ownerNoText'
			},
			{
				width : 120,
				text : $i18n.billingProject,
				dataIndex : 'billingProjectText'
			},{
				width : 200,
				text : $i18n.billingUnit,
				dataIndex : 'billingUnitText'
			},{
				width : 100,
				text : $i18n.valueFlag,
				dataIndex : 'valueFlagText'
			},{
				width : 500,
				text : $i18n.memo,
				dataIndex : 'remark'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store : workstationStore,
			dock : 'bottom',
			displayInfo : true
		}] 
	   },
	 	{
		   region:'south'
		}]
});