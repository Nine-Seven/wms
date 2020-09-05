/**
 * 模块名称：返配波次管理
 * 模块编码：6502
 * 创建：hkl
 */
var ridata_WaveStore=Ext.create('cms.store.ridata.ridata_WaveStore',{autoLoad:true});
Ext.define('cms.view.ridata.ridata_WaveUI',{
	alias:'widget.ridata_WaveUI',
	title:$i18n.title6502,//反配波次管理
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
	                		 name:'butEndSweep6502',
	                		 margin:'3 0 0 3',
	                		 text : $i18n.endsweep6502 //结束波次
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
	             			xtype:'wms_DefFieldValCombo',
	             			id:'status6502',
	             			fieldLabel : $i18n.manage_status,//状态
	             	        editable:false,
	             	        store:Ext.create("cms.store.common.comboStore").load(
	             	        {
	             	        	params:{str:"BSET_DEFBATCH,STATUS"}
	             	        }),
	             	        allowBlank : false,
	             	        beforeLabelTextTpl : required
	                	 }]
	                 },{
	                	 xtype:'grid',
	                	 id:'grid_01_6502',
	                	 region:'center',
	                	 store : ridata_WaveStore,
	                	 multiSelect: true,  
	                	 columns:[
            	          {			
            	        	  xtype : 'rownumberer',
            	        	  width : 30
            	          },
            	          {
            	        	  width:200,
            	        	  text :$i18n.operaterDate,
            	        	  dataIndex:'operateDate',//操作日期	
        	        		  renderer:function(value){   
        	  				    if(value instanceof Date){   				 
        	  				        return Ext.Date.format(value,'Y-m-d');   
        	  				    }else{				        
        	  				        return value;   
        	  				    }  
        	  				}
            	          },
            	          {
            	        	  width:200,
            	        	  text :$i18n.locate_no,
            	        	  dataIndex:'waveNo'	//波次		
            	          },
            	          {
            	        	  width:200,
            	        	  text :$i18n.massflag,
            	        	  dataIndex:'waveTypeText'//波次类型		
            	          },{
            	        	  width:200,
            	        	  text :$i18n.batch_no,
            	        	  dataIndex:'currBatch'	//批次		
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
            	        		   store : ridata_WaveStore,
            	        		   dock : 'bottom',
            	        		   displayInfo : true
            	        	   }
            	        	   ]

	                 }]
});

