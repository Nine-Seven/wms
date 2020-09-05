/*
 * 出货日期确认
 * hkl
 * 3922
 */
var Odata_CustSendDate=Ext.create('cms.store.odata.odata_ExpMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_CustSendDateAction_getOdata_CustSendDate.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
 		},
 		extraParams:{
 			strQuery : "1"
		}
	}
});

Ext.define('cms.view.odata.odata_CustSendDateUI',{
	alias:'widget.odata_CustSendDateUI',
	title:$i18n.title3922,//出货日期确认
	id:'odata_CustSendDateUI3922',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	    	  'cms.view.common.remoteCombo',
	    	  'cms.view.common.bdef_DefCustCombo',

	          ],
	items:[{
				xtype:'toolbar',
			    region:'north',
				items:[
				{
					text : '刷新',
					iconCls : 'refresh',
					id : 'refresh'
				}]
           },{
				xtype : 'form',
				id:'formSelectCondition3922',
				layout : {
					 type : 'table',
					 columns :6
				 },
				region : 'north',
				frame : true,
				defaults:{
			       labelAlign:'right',
			       labelWidth : 60,
			       margin:'0 0 0 2'
		        },
				items:[
				{
					xtype:'bdef_DefOwnerCombo',
					fieldLabel : $i18n.owner_no,//委托业主编号
					id : 'owner_no3922',
					width : 180,
					displayField : 'dropValue',
	    		    valueField : 'value',
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_queryOwnerCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
					}).load()
		  		},{
					xtype:'wms_DefFieldValCombo',
    	    		fieldLabel:$i18n.exp_type,//出货单别
    	    		hidden:true,
    	    		id:'exp_type3922',
    	    		store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_queryExpTypeCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
					}).load()
		  		},{
					xtype:'bdef_DefOwnerCombo',
		  			fieldLabel:$i18n.cust,//客户
		  			id:'cust3922',
		  			width : 180,
		  			displayField : 'dropValue',
	       			valueField : 'value',
		  			store:Ext.create('cms.store.bdef.bdef_DefCustComboStore',{
		  				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CustSendDateAction_queryCustCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
		  			}).load()
		  		},{

		  	        xtype: 'combo',
		  	        fieldLabel : '　确认标识',
		  	        labelWidth : 70,
		  	        width : 150,
		  	        id:'confirmType3922',
		  	        displayField: 'name',
		  	   		valueField: 'id',
		  			forceSelection : true,
		  			store:Ext.create('Ext.data.Store', {
		  	        fields: ['id', 'name'],
		  		    data:[
		  		          {id:'1',name:'未确认'},
		  				  {id:'2',name:'已确认'}
		  		          ]
		  	    	}),
		  	    	value:'1'
		  		},{
		  	        xtype: 'combo',
		  	        fieldLabel : $i18n.last_custsend_Date, //出货最晚日期
		  	        labelWidth : 100,
		  	        width : 190,
		  	        id:'select_term3922',
		  	        displayField: 'name',
		  	   		valueField: 'id',
		  			forceSelection : true,
		  			store:Ext.create('Ext.data.Store', {
		  	        fields: ['id', 'name'],
		  		    data:[
		  		          {id:'1',name:'等于'},
		  				  {id:'2',name:'大于'},
		  				  {id:'3',name:'小于'},
		  				  {id:'4',name:'大于等于'},
		  				  {id:'5',name:'小于等于'},
		  				  {id:'6',name:'不等于'}
		  		          ]
		  	    	}),
		  	    	value:'5'
		  	    },{
					xtype : 'datefield',
					id : 'last_custsend_Date3922',							
					format : 'Y-m-d',
				},{
					xtype: 'button',
	            	text: $i18n.query,
	            	margin : '5 3 5 3',
	            	id:'btnQuery3922'	
				}]
		   },{
		    	xtype : 'form',
				region:'north',
				layout: 
				{
					type: 'table',
					columns: 5
				},
				defaults:{
				       labelAlign:'right',
				       labelWidth : 100,
				       margin:'0 0 0 5',
			    },
			    items :[{  
			    	xtype : 'datefield',
					fieldLabel : $i18n.custsend_Date,//出货确认日期
					id : 'custsend_Date3922',
					format : 'Y-m-d',
					beforeLabelTextTpl : required
					},
				{
					xtype:'container',
					margin:'0 0 0 55',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_volumn
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumVolumn3922_1',
						margin:'0 0 0 15',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 100',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_weight
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumWeight3922_1',
						margin:'0 0 0 15',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 100',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_packing_qty
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumBoxQty3922_1',
						margin:'0 0 0 15',
						text:'0'
					}]
				},{

					xtype:'container',
					items:[
			        {
						xtype: 'button',
		            	text: $i18n.confirm,
		            	margin : '5 3 5 90',
		            	id:'btnConfirm3922'
					}]
				
				}]
		     
		   },{
			    xtype : 'grid',
				id:'gridOdata_CustSendDate3922',
				store:Odata_CustSendDate,
				region : 'center',
				multiSelect: true,  
				selModel: {  
				    selType:'checkboxmodel',
				    checkOnly:true
				},
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 80,
					text : $i18n.owner,
					dataIndex : 'ownerNo'
				},{
					width : 150,
					text : $i18n.source_no,//出货单号（原单号）
					dataIndex : 'sourceexpNo'
				},{
					width : 100,
					text : $i18n.sourceexp_type,//出货类型
					dataIndex : 'expType'
				},{
					width : 80,
					text : $i18n.cust_no,//客户编码
					dataIndex : 'custNo'
				},{
					width : 160,
					text : $i18n.cust_name,//客户名称
					dataIndex : 'custName'
				},{
	    	       	width:100,
	    	       	text:$i18n.last_custsend_Date,//出货最晚日期
	    	       	dataIndex:'lastCustsendDate'
	    	    },{
	    	       	width:100,
	    	       	text:$i18n.custsend_Date,//出货确认日期
	    	       	dataIndex:'custsendDate'
	    	    }]
			},{
		    	xtype : 'form',
				region:'south',
				layout: 
				{
					type: 'table',
					columns: 5
				},
				defaults : 
				{
					xtype : 'textfield',
					labelWidth : 110,
					margin:'8 3 8 90',
					labelAlign:'right'			
			    },
			    items :[
				{
					xtype:'container',
					margin:'8 3 8 90',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_volumn
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumVolumn3922_2',
						margin:'0 0 0 15',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'8 3 8 90',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_weight
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumWeight3922_2',
						margin:'0 0 0 15',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'8 3 8 90',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_packing_qty
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumBoxQty3922_2',
						margin:'0 0 0 15',
						text:'0'
					}]
				}
	            ]
		    }]          
});