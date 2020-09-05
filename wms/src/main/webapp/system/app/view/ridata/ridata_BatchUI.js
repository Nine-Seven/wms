/**
 * 模块名称：返配批次管理
 * 模块编码：6501
 * 创建：hcx
 */
var ridata_BatchStore=Ext.create('cms.store.ridata.ridata_BatchStore',{autoLoad:true});
Ext.define('cms.view.ridata.ridata_BatchUI',{
	alias:'widget.ridata_BatchUI',
	title:$i18n.title6501,//反配批次管理
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	          items:[
	                 {
	                	 xtype : 'form',
	                	 region : 'north',
	                	 width:'100%',
	                	 frame : true,
	                	 items : [{	
	                		 xtype : 'button',
	                		 name:'butEndSweep6501',
	                		 margin:'3 0 0 3',
	                		 text : $i18n.endsweep6501 //结束扫描
	                	 }]
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
	                	 items:[
	                	        {
	                		 xtype:'combo',
	                		 fieldLabel : $i18n.warehouse,//仓别
	                		 id:'cmbWarehouse6501',
	                		 readOnly:true,
	                		 displayField: 'dropValue',
	                		 valueField: 'value',
	                		 queryMode: 'local',
	                		 store:Ext.create("cms.store.common.comboStore",{
	                			 proxy:{
	                				 type:'ajax',
	                				 method:'post',
	                				 url : 'bset_ValuePolicySetAction_getHouseNameComboList.action',
	                				 reader: {
	                					 type:'json',
	                					 root: 'rootList',    
	                					 totalProperty: 'totalCount'
	                				 }
	                			 },listeners:{  
	                				 'load':function(th,records,successful,eOpts ){
	                					 if(th.count()>0){
	                						 Ext.getCmp('cmbWarehouse6501').setValue(th.getAt(0).data.value);
	                					 }
	                				 }
	                			 }
	                		 }).load()
	                	 },
	                	 {
	                		 xtype : 'datefield',
	                		 fieldLabel : $i18n.operaterDate,//操作日期
	                		 id : 'operateDate6501',
	                		 format : 'Y-m-d'
	                	 },{
	                		 fieldLabel :$i18n.batch_no, //批次
	                		 id:'batchNo6501',
	                		 xtype:'wms_DefFieldValCombo',
	                		 
	                		 store:Ext.create('cms.store.ridata.ridata_BatchStore',{
	                			 proxy:{
	                				 type:'ajax',
	                				 method:'post',
	                				 url:'ridata_BatchAction_getBatchNoForUI.action'
	                			 }
	                		 }).load(),
	                		 displayField : 'dropValue',
				   			 valueField : 'value'
	                	 }]
	                 },{
	                	 xtype:'grid',
	                	 id:'grid_01_6501',
	                	 region:'center',
	                	 store : ridata_BatchStore,
	                	 multiSelect: true,  
//	                	 selModel: {  
//	                		 selType:'checkboxmodel',
//	                		 checkOnly:true
//	                	 },
	                	 columns:[
	                	          {			
	                	        	  xtype : 'rownumberer',
	                	        	  width : 30
	                	          },
	                	          {
	                	        	  width:200,
	                	        	  text :$i18n.warehouse,//仓别
	                	        	  dataIndex:'warehouseText'			
	                	          },
	                	          {
	                	        	  width:200,
	                	        	  text :$i18n.operaterDate,
	                	        	  dataIndex:'operateDateText'//操作日期	
	                	          },
	                	          {
	                	        	  width:200,
	                	        	  text :$i18n.batch_no,
	                	        	  dataIndex:'batchNo'	//批次		
	                	          },
	                	          {
	                	        	  width:200,
	                	        	  text :$i18n.status,
	                	        	  dataIndex:'statusText'//状态		
	                	          }],
	                	          dockedItems : 
	                	        	  [
	                	        	   {
	                	        		   xtype : 'pagingtoolbar',
	                	        		   store : ridata_BatchStore,
	                	        		   dock : 'bottom',
	                	        		   displayInfo : true
	                	        	   }
	                	        	   ]

	                 }]
});

