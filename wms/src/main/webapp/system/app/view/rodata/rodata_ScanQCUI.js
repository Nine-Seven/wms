/**
 * 模块名称：退厂扫描
 * 模块编码：7305
 * 创建 ： chensr
 */   

var unScanStore=Ext.create('cms.store.rodata.rodata_ScanDQCStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('unCheckQty7305').setText(records[0].get('scanQty')); 
			}
		  }
	}
});

var scanStore=Ext.create('cms.store.rodata.rodata_ScanDTTHStore',{
	  autoLoad:false,
	  proxy:{
			type:'ajax',
			method:'post',
			url:'rodata_ScanQCAction_ScanDNot.action',
			reader:{
				root:'rootList',
				totalProperty:'totalCount'
			}
	  },listeners:{  
			'load':function(th,records,successful,eOpts ){
				if(th.count()>0){
					Ext.getCmp('checkQty7305').setText(records[0].get('scanQty')); 
				}
			  }
		}
});

Ext.define('cms.view.rodata.rodata_ScanQCUI',{
	alias:'widget.rodata_ScanQCUI',
	title:'清场退货扫描',//退厂扫描
	id:'rodata_ScanQCUI7305',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.ridata_UntreadNoCombo',
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget4',
	   		   region:'north',
	   		   id:'menu7305'
	       },{
				xtype : 'form',
				layout:'column',
				region : 'center',
				frame : true,
				width : '51%',
				items:[
				       {
					layout:{
					type : 'table',
					columns : 2
					},
					xtype:'container',
					margin:'20 0 0 0',
					defaults:{
						labelWidth : 90,
						margin : '2 0 5 0',
						labelAlign : 'right',
						xtype:'textfield'
					},
					items:[
					{
						xtype : 'bdef_DefWorkerCombo',
						fieldLabel : '扫描人',
						width:360,
						id:'scanNo7305',
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
						beforeLabelTextTpl : required
					},{
						xtype : 'bdef_DefWorkerCombo',
						fieldLabel :'退厂单号',
						id:'poNo7305',
						width:360,
						beforeLabelTextTpl : required,
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore",{
               			 proxy:{
            				 type:'ajax',
            				 method:'post',
            				 url : 'rodata_ScanQCAction_getPoNoQC.action',
            				 reader: {
            					 type:'json',
            					 root: 'rootList',    
            					 totalProperty: 'totalCount'
            				 }
               			 }
            		 }).load(),
					},
					{
						xtype : 'textfield',
						fieldLabel : '货主',// 货主
						id:'ownerNo7305',
						width:360,
						beforeLabelTextTpl:required,
						hidden:true						
					},
					{
						xtype : 'textfield',
						fieldLabel : '退厂单号',// 货主
						id:'recedeNo7305',
						width:360,
						beforeLabelTextTpl:required,
						hidden:true						
					},
					{
						xtype : 'textfield',
						fieldLabel : '供应商',// 货主
						id:'suppelier7305',
						width:360,
						beforeLabelTextTpl:required,
						hidden:true						
					},
					{
						xtype : 'textfield',
						width:360,
						fieldLabel : $i18n.barcode,// 商品条码
						id:'barcode7305',	
						beforeLabelTextTpl:required
						
					},{
						xtype : 'textfield',
						width:360,
						fieldLabel : $i18n.article_no,//商品编码
						id:'articleNo7305',
						readOnly:true
					},{
						xtype : 'textfield',
						fieldLabel : $i18n.article_name,//商品名称
						id:'articleName7305',
						width:720,
						colspan:2,
						readOnly:true
					},{
						fieldLabel:'扫描基准量',//扫描基准量
						xtype:'numberfield',
						id:'sacnNum7305',
						width:360,
						colspan:2,
						beforeLabelTextTpl:required,	
					},{

						xtype:'container',
						margin:'5 0 0 83',
						items:[
						{
							xtype:'label',
							readOnly:true,
							cls:'classDiv1',
							text:'未扫数量：'
						},
						{
							xtype:'label',
							readOnly:true,
							id:'unCheckQty7305',
							margin:'0 0 0 10',
							cls:'classDiv1',
							text:'0'
						}]					
					},{
						xtype:'container',
						margin:'5 0 0 83',
						items:[
						{
							xtype:'label',
							readOnly:true,
							cls:'classDiv1',
							text:'已扫数量：'
						},
						{
							xtype:'label',
							readOnly:true,
							id:'checkQty7305',
							margin:'0 0 0 10',
							cls:'classDiv1',
							text:'0'
						}]
					},{
						xtype: 'button',
		            	text: '换箱',
		            	margin : '3 3 3 80',
		            	width:100,
		            	id:'recede7305'					
						
					},{
						xtype: 'button',
		            	text: '重扫描',
		            	margin : '3 3 3 80',
		            	width:100,
		            	id:'recedeAgain7305'					
						
					}]
				}]
	         },{

	        	xtype:'panel',
	        	region:'south',
	        	layout:'border',
	        	height:320,
	        	items:[
	        	{
	    	    	xtype:'grid',
	    	    	region:'west',
	    	    	width:'51%',
	    	    	id:'gridrodata_ScanTTH7305_3',
	    	    	title:'未扫描商品信息',
	    	    	store:unScanStore,	    
	    		    columns:[
	    		    {			
	    		        xtype : 'rownumberer',
	    			    width : 30
	    		    },
	    		    {
	    			    width:110,
	    			    text : '退厂单号',//商品条码
	    			    dataIndex:'recedeNo',
	    			    hidden:true
	    		    },
	    		    {
	    			    width:110,
	    			    text : $i18n.barcode,//商品条码
	    			    dataIndex:'barcode'			
	    		    },
	    		    {
	    			    width:260,
	    			    text : $i18n.article_name,//商品名称
	    			    dataIndex:'articleName'			
	    		    },
	    		    {
	    			    width:60,
	    			    text : $i18n.qty1,//未扫描数量
	    			    dataIndex:'unCheckQty'			
	    		    },{

	    			    width:60,
	    			    text : $i18n.packing_qty,
	    			    dataIndex:'packingQty'			
	    		    
	    		    },{
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7305_3'
					},{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7305_3'
					}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
						dock : 'bottom',
						store:unScanStore,
						displayInfo : true
					}]
	    	    },
	    	    {
	    	    	xtype:'grid',
	    	    	region:'east',
	    	    	width:'49%',
	    	    	id:'gridrodata_ScanTTH7305_4',
	    	    	title:'已扫描商品信息',
	    			store:scanStore,	    	
	    		    columns:[
	    		    {			
	    		        xtype : 'rownumberer',
	    			    width : 30
	    		    }, 
	    		    {
	    			    width:110,
	    			    text : $i18n.barcode,//商品条码
	    			    dataIndex:'barcode'			
	    		    },
	    		    {
	    			    width:250,
	    			    text : $i18n.article_name,//商品名称
	    			    dataIndex:'articleName'			
	    		    },
	    		    {
	    			    width:60,
	    			    text : $i18n.qty1,//数量
	    			    dataIndex:'checkQty'			
	    		    },
	    		    {
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7305_4'
					},{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7305_4'
					}
	    		],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
					store:scanStore,
					displayInfo : true
				}]}
	          ]}
	       ]          
});