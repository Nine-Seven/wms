/**
 * 模块名称：直通验收批次管理
 * 模块编码：4903
 * 创建：hcx
 */
var idata_StraightCheckBatchStore=Ext.create('cms.store.idata.idata_StraightCheckBatchStore',{autoLoad:true});
Ext.define('cms.view.idata.idata_StraightCheckBatchUI',{
	alias:'widget.idata_StraightCheckBatchUI',
	title:$i18n.title4903,//直通验收批次管理
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
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
	                		 name:'butEndBatch4903',
	                		 margin:'3 0 0 3',
	                		 text : $i18n.endbatch //结束批次
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
	                		 xtype : 'datefield',
	                		 fieldLabel : $i18n.operaterDate,//操作日期
	                		 id : 'operateDate4903',
	                		 format : 'Y-m-d'
	                	 },{
	                		 fieldLabel :$i18n.batch_no, //批次
	                		 id:'batchNo4903',
	                		 xtype:'wms_DefFieldValCombo',
	                		 
	                		 store:Ext.create('cms.store.idata.idata_StraightCheckBatchStore',{
	                			 proxy:{
	                				 type:'ajax',
	                				 method:'post',
	                				 url:'idata_StraightCheckBatchAction_getBatchNoForUI.action'
	                			 }
	                		 }).load(),
	                		 displayField : 'dropValue',
				   			 valueField : 'value'
	                	 }]
	                 },{
	                	 xtype:'grid',
	                	 id:'grid_01_4903',
	                	 region:'center',
	                	 store : idata_StraightCheckBatchStore,
	                	 multiSelect: true,  
	                	 columns:[
	                	          {			
	                	        	  xtype : 'rownumberer',
	                	        	  width : 30
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
	                	        		   store : idata_StraightCheckBatchStore,
	                	        		   dock : 'bottom',
	                	        		   displayInfo : true
	                	        	   }
	                	        	   ]
	                 }]
});

