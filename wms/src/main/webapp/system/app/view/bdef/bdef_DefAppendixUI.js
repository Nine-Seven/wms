/**
 * 模块名称：附件管理
 * 模块编码：1B01
 */
/*var defAppendixStore = Ext.create("cms.store.bdef.bdef_DefAppendixStore",{
		autoLoad:true,
		listeners:{  
		   	 'load':function(th,records,successful,eOpts ){   
		   		 if(th.count()>0){
		   			 Ext.getCmp('grid_01_1B01').getSelectionModel().select(0);		   			 
		   		 }
		   	 }
		}
	});*/

Ext.define('cms.view.bdef.bdef_DefAppendixUI',{
	alias:'widget.bdef_DefAppendixUI',
	title:'附件管理',
	width :'100%',	
	height:'95%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    /*{
	    xtype:'commMenuWidget2',
	    id:'menu1B01',
	    region:'north'
	},*/{ 			
		xtype: 'button',
    	text: '查询',
    	//margin : '3 3 3 80',
    	width:100
	},{ 			
		xtype: 'button',
    	text: '打印',
    	//margin : '3 3 3 80',
    	width:100
	},{
		xtype:'panel',	
		region : 'north',
		frame : true,
		height:150,
		autoScroll: true,
		width:800,		
		items : []
	},{
	    xtype:'grid',
	    region:'west',
	    width:'60%',
	    id:'grid_01_1B01',
	    title : '数据明细',
	    //store:defAppendixStore,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 90,
			text : '货主',
			dataIndex:'ownerNo'
	    },{
			width : 160,
			text : '文件名称',
			dataIndex:'fileName'
	    },{
			width : 60,
			text : '附件类型',
			dataIndex:'typeText'
	    },{
	    	width : 120,
			text : '货主单号',
			dataIndex:'relateOrderno'
	    },{
	    	width : 60,
			text : '所属分类',
			dataIndex:'relateClassText'
	    },{
	    	width : 170,
			text : '备注',
			dataIndex:'meno'
	    },{
	    	xtype:'actioncolumn',
	    	width:60,
	    	align:'center',
			text:'操作',
            icon: 'system/images/down.png',  
            tooltip: '下载',
 
            handler: function(grid, rowIndex, colIndex) {
                var data = grid.getStore().getAt(rowIndex); 
                if(data.get('type')=='1'){
                	 window.location.href = data.get('filePath'); 
                }else{
                	window.open(data.get('filePath'));
                }
               
            }
		    
	    }]
	},{

		    xtype:'grid',
		    //id:'grid_02_03_1F01',
		    title:$i18n.printer_group_list,//打印机群组列表
		    width:'40%',
		    region:'west',
		    //store:bset_Printer_GroupStore,
		    multiSelect: true,  
		    selModel: {  
		        selType:'checkboxmodel'  
		    },
		    columns:[{			
				xtype : 'rownumberer',
				width : 30
		    },{
				width:120,
				text:$i18n.printer_group_no,//打印机群组代码
				dataIndex:'printerGroupNo'
		    },{
				width:120,
				text:$i18n.printer_no,//打印机代码
				dataIndex:'printerNo'
		    },{
				width:200,
				text:$i18n.printer_name,//打印机名称
				dataIndex:'printerName'
		    }]
		
	},
		{

			   xtype:'panel',
			   region : 'center',
			   layout : 'column',
			   region:'south',
			   items  : [ {
					xtype : 'grid',
					//region : 'west',
					height:135,
					columnWidth : 0.6,	
					autoScroll: true,
					title : '数据明细',
					//store:print_ReportDataStore1AAA,
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 200,
						text : '订单号',//订单号
						dataIndex : 'poNo'
					} , {
						width : 120,
						text : "客户/供应商",//客户/供应商
						dataIndex : 'custNo'
					}, {
						width : 150,
						text : '商品信息',//商品信息
						dataIndex : 'articleName'
					} ,{
						width : 200,
						text : '波次号',//波次号
						dataIndex : 'waveNo'
					}]
				},{

					xtype : 'grid',
					//region : 'west',
					height:135,
					columnWidth : 0.4,	
					autoScroll: true,
					title : '数据明细',
					//store:print_ReportDataStore1AAA,
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 200,
						text : '订单号',//订单号
						dataIndex : 'poNo'
					} , {
						width : 120,
						text : "客户/供应商",//客户/供应商
						dataIndex : 'custNo'
					}, {
						width : 150,
						text : '商品信息',//商品信息
						dataIndex : 'articleName'
					} ,{
						width : 200,
						text : '波次号',//波次号
						dataIndex : 'waveNo'
					}]
				
				}]}
		/*{  
		xtype : 'box', 
		width:'30%',
		height:'50%',
		region:'east',
		id : 'browseImage',  
		fieldLabel : "预览图片",  
		autoEl : {  
			width : 300,  
			height : 340,  
			tag : 'img',  
			src : "",  
			style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',  
			complete : 'off',  
			id : 'imageBrowse' 
		}  
   }*/]
});