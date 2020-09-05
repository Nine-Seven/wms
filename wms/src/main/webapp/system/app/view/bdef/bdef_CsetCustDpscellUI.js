/**
 * 客户与电子标签储位对应关系
 * 模块编码   1P01
 * @author hcx
 */
var csetCustStore = Ext.create("cms.store.bdef.bdef_CsetCustStore",{autoLoad:true});
Ext.define('cms.view.bdef.bdef_CsetCustDpscellUI',{
	alias:'widget.bdef_CsetCustDpscellUI',
	title:$i18n.title1P01,//客户与电子标签储位对应关系
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires : [
	            'cms.view.common.commMenu6',
		        'cms.view.common.bdef_DefOwnerCombo'
	            ],
	items:[{
    	     xtype : 'commMenuWidget6',
	         id:'menu1P01',
	         region:'north'
           },{
        	 xtype:'form',
       	     id:'form1P01',
       	     region:'north',
       	     height:27,
       		 frame : true,
       	     layout:{
          			type:'table',
          			columns:1
       	    },
        	 items : [{
             	xtype: 'text',
	            html: '说明：<font  color="#42E61A"> [绿色]</font >代表该储位已分配客户 <font color="#F3939">  [橙色]</font>代表所选客户对应的储位<font>  [白色]</font>代表该储位没有分配客户'
              }]
         },{  
	        		 xtype:'form',
	        		 id:'formOwner1P01',
	        		 region:'north',
	        		 right: 0,
	        		 frame : true,
	        		 layout:{
	        	   	 type:'table',
	        	   	 columns:2
	        	},
	        	defaults : {
	        		 margin : '3 3 3 30',
	        		 labelWidth : 65,
	        		 labelAlign:'right'			
	        	},
	        	items:[{
	        		 xtype:'bdef_DefOwnerCombo',
	        		 fieldLabel:$i18n.owner_no,//货主编号
	        		 id:'cmbFormOwner1P01',
	        		 labelWidth : 80,
	        		 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				    	    proxy:{
								type:'ajax',
								method:'post',
								url:'bdef_CsetCustDpscellAction_getOwnerComboList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbFormOwner1P01').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.bdef.bdef_CsetCustDpscellController').selectCustByOwner();
								}
							}
						}}).load(),
	        		 displayField : 'dropValue',
	        		 valueField : 'value'
	        	},{
	        		xtype:'bdef_DefOwnerCombo',
	        		 fieldLabel:$i18n.device_group_no,//分播组
	        		 labelWidth : 80,
	        		 id:'deviceGroupNo1P01',
	        		 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				    	    proxy:{
								type:'ajax',
								method:'post',
								url:'bdef_CsetCustDpscellAction_getDeviceGroupNoList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('deviceGroupNo1P01').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.bdef.bdef_CsetCustDpscellController').deviceGroupNo1P01Select();
								}
							}
						}}).load(),
	        		 displayField : 'dropValue',
	        		 valueField : 'value'
	        	},{
	        		xtype:'bdef_DefOwnerCombo',
	        		 fieldLabel:$i18n.device_no,//分播线
	        		 labelWidth : 80,
	        		 id:'deviceNo1P01',
	        		 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				    	    proxy:{
								type:'ajax',
								method:'post',
								url:'bdef_CsetCustDpscellAction_getDeviceNoList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('deviceNo1P01').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.bdef.bdef_CsetCustDpscellController').deviceNo1P01Select();
								}
							}
						}}).load(),
	        		 displayField : 'dropValue',
	        		 valueField : 'value'
	        	},{
	    			xtype : 'radiogroup',
	    			id : 'rdoCheckType1P01',
	    			width : 200,
	    			columns : 2,
	    			items : [
	    	        {
	    				boxLabel : $i18n.dps_NotAssignedCustomer,//未分配客户
	    				name : 'rd',
	    				checked: true,
	    				inputValue : '1',
	    			},
	    			{
	    				boxLabel : $i18n.dps_AssignedCustomer,//已分配客户
	    				name : 'rd',
	    				inputValue : '2'
	    			}]
	        	}]
	        	},{
					xtype:'grid',
					id:'bdef_Cust_ListGrid2',
					title : $i18n.dps_custList,
					store : csetCustStore,
					region:'west',
					width : '30%',
					columns:[{
						xtype : 'rownumberer',
						width : 30
					 },{
						width : 170,
						text : $i18n.cust_name,//客户名称
						dataIndex : 'custName'
						},{
							width : 50,
							text : $i18n.device_no1,//分播线
							dataIndex : 'deviceNo'
						},{
							width : 60,
							text : $i18n.dps_cell_no,//分配储位
							dataIndex : 'dpsCellNo'
							}],
						dockedItems : [{
					        xtype : 'pagingtoolbar',
					        store : csetCustStore,
					        dock : 'bottom',
					        displayInfo : true
					    }]
						
				},{//诸位
					xtype : 'panel',
					id : 'bdef_CCDCellGrid1P01',
					title : $i18n.addcell_no,
					region:'center',
					layout:'fit',
					frame:false,
					items: []
				
				}]
			
});