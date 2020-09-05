/**
 * 模块名称：杂项费用维护UI
 * 模块编码：B203
 * 创建：hcx 
 */
var cost_OtherListStore=Ext.create('cms.store.cost.cost_OtherListStore',{autoLoad:true});
Ext.define('cms.view.cost.cost_OtherUI',{
	alias:'widget.cost_OtherUI',
	title: $i18n.titleB203, //杂项费用维护
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
	    id:'menuB203',
	    region:'north'
	},{
		xtype:'panel',
			region:'north',
			height: 43,
			layout: {
			    type: 'table',
		        columns: 3
			},
		    defaults : {
				xtype : 'textfield',
				margin : '10 3 3 3',
				labelAlign:'right',
				allowBlank: true,
				width : 280,
				labelWidth : 90
			},
			items:[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoUIB203',
				xtype:'wms_DefFieldValCombo',
				 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
						type:'ajax',
						method:'post',
						url:'cost_OtherAction_getOwnerNoForQuery',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
					}
					}
		        }).load(),
			    displayField : 'dropValue',
			    valueField : 'value'
			},{
				fieldLabel :$i18n.cost_no, //费用代码
				id:'costNoUIB203',
				xtype:'wms_DefFieldValCombo',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
 					type:'ajax',
 					method:'post',
 					url:'cost_OtherAction_getCostNoForUI',
 					reader:{
						root:'rootList',
 						totalProperty:'totalCount'
						}
					}
			    }).load(),
			    displayField : 'dropValue',
			    valueField : 'value'
			}]
		},
		{
		    xtype:'grid',
			store:cost_OtherListStore,
			id:'costOtherListB203',
		    region:'center',
		    columns:[
		 		    {			
						xtype : 'rownumberer',
						width : 30
				    },{
						width : 100,
						text : $i18n.warehouse,//仓别
						dataIndex:'warehouseNo'
				    },{
				    	width: 150,
			  		    text : $i18n.owner_no,  //货主编号
			  		    dataIndex:'ownerNoText'		
				    },{
				    	width: 150,
				  		text : $i18n.cost_other,  //杂项费用
				  		dataIndex:'costNoText'		
					},{
				    	width: 110,
				  		text : $i18n.cost_value,  //费用值
				  		dataIndex:'costValue'		
					},{
				    	width: 90,
				  		text : $i18n.cost_date,  //费用日期
				  		dataIndex:'costDateText'		
					},{
				    	width: 80,
				  		text : $i18n.amountFlag,  //费用标识
				  		dataIndex:'costFlagText'		
					},{
		     		    width:90,
		     		    text : $i18n.manage_status,  //状态
		     		    dataIndex:'statusText'			
		     	    },{
		     	    	width:150,
						text:$i18n.source_plan_no,  //来源单号
						dataIndex:'sourceNo'	
					},{
		     	    	width:150,
						text:$i18n.check_no,  //对账单号
						dataIndex:'checkNo'	
					},{
				    	width: 200,
				  		text : $i18n.remark,  //备注
				  		dataIndex:'remark_c'		
					}]/*,
		    dockedItems : [{
				xtype : 'pagingtoolbar',
				dock : 'bottom',
				store:cost_OtherListStore,
				displayInfo : true
		    }]*/
		},{
     		region:'south'
        }]
});