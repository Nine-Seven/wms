/**
 * 模块名称：退货拼箱打包
 * 模块编码：7401
 * 创建：HKL 
 */
 var rodata_SlabelNoListStore=Ext.create('cms.store.rodata.rodata_SlabelNoListStore');
 var rodata_sLabelNoArticleStore=Ext.create('cms.store.rodata.rodata_sLabelNoArticleStore');
 var rodata_dLabelNoArticleStore=Ext.create('cms.store.rodata.rodata_dLabelNoArticleStore');
Ext.define('cms.view.rodata.rodata_LabelPackUI',{
	alias:'widget.rodata_LabelPackUI',
	title:$i18n.title7401,//退货拼箱打包
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu10',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.ridata_UntreadNoCombo',

	          ],
	items:[
    {
	    xtype:'commMenuWidget10',
	    id:'menu7401',
	    region:'north'
	},{
		xtype : 'form',
		layout:'column',
		region : 'west',
		frame : true,
		width : '50%',
		items:[
		       {
			layout:{
			type : 'table',
			columns : 2
			},
			xtype:'container',
			margin:'10 0 0 0',
			defaults:{
				labelWidth : 90,
				margin : '5 0 5 0',
				labelAlign : 'right',
				xtype:'textfield'
			},
			items:[
			{
				xtype : 'bdef_DefWorkerCombo',
				fieldLabel : '扫描人',// 扫描人
				//width:240,
				id:'workerNo7401',
				store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
				beforeLabelTextTpl : required
			},{
				xtype : 'ridata_UntreadNoCombo',
				fieldLabel :$i18n.recede_no,// 退货单号
				id:'recedeNo7401',
				displayField: 'dropValue',
				valueField: 'value',
				store:Ext.create('cms.store.rodata.rodata_ScanTTHStore',
						 {
							 proxy:{
									type:'ajax',
									method:'post',
									url:'rodata_LabelPackAction_getRecedeNo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							 },
						 }),
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.s_label_no,//源标签
				xtype : 'textfield',
				colspan:2,
				id:'SlabelNo7401',
				beforeLabelTextTpl : required
			},{
				fieldLabel:$i18n.d_label_no,//目的标签
				xtype : 'textfield',
				id:'DlabelNo7401'
			},{
				xtype: 'button',
				//region:'east',
            	text: '取号',
            	margin : '3 3 3 20',
            	width:50,
            	id:'btnDlabelNo7401'
			},{
				xtype: 'button',
				//region:'east',
            	text: '目的标签回库',
            	margin : '3 3 3 20',
            	width:100,
            	id:'btnMoveQty7401'
			}]
		}]
     },{
	    xtype:'grid',
	    region:'east',
		width:'50%',
	    id:'grid_01_7401',
	    store:rodata_SlabelNoListStore,
	    columns:[
	     {			
	        xtype : 'rownumberer',
		    width : 30
	     },{
		    width:200,
		    text : $i18n.recede_no,// 退货单号
		    dataIndex:'recedeNo'			
	     },
	     {
		    width:105,
		    text : $i18n.s_label_no,//源标签
		    dataIndex:'labelNo'			
	     }]
	},{
	 	xtype:'panel',
    	region:'south',
    	layout:'border',
    	height:400,
    	items:[
			{
			xtype:'grid',
			id:'grid_02_7401',
			title:$i18n.s_Label_article,//来源标签商品列表
			width:'45%',
			region:'west',
			store:rodata_sLabelNoArticleStore,
			multiSelect: true,  
		    selModel: {  
		    	selType:'checkboxmodel'  
		    },
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			},{
				width:85,
				text:$i18n.label_no,//标签号
				dataIndex:'labelNo',
			},{
				width:100,
				text:$i18n.article_name,//商品名称
				dataIndex:'articleName'
			},{
			    width:110,
			    text : $i18n.barcode,//商品条码
			    dataIndex:'barcode'			
		    },{
			    width:60,
			    text : $i18n.qty1,//数量
			    dataIndex:'articleQty'			
		    },{
				width : 80,
				text : $i18n.packing_qty,//包装数量
				dataIndex:'packingQty'
			},{
				width : 80,
				text : $i18n.packing_unit,
				dataIndex:'packingUnit',
				id:'packingUnit7401_1'},
				{
					width : 85,
					text : $i18n.packingSpec,//规格
					dataIndex : 'packingSpec',
					id:'packingSpec7401_1'
				}],			
			dockedItems : [{
				xtype : 'pagingtoolbar',
				dock : 'bottom',
				store:rodata_sLabelNoArticleStore,
				displayInfo : true
			}]
       },{
		xtype : 'form',
		region : 'center',
		layout:{
			type:'table',
			columns:1
		},
		width:'10%',
		frame : true,
		defaults:{
			margin:'10 0 0 8'
		},
		items : [{
			xtype:'button',
			margin:'80 0 50 8',
			text:'单品转移>',
			id:'right7401'
		},
		{
			xtype:'button',	
			margin:'0 0 50 8',
			text:'整箱转移>',
			id:'rightList7401'
		},{
			xtype:'button',			
			text:'单品拆移>',
			id:'rightQty7401'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_7401',
	    title:$i18n.d_Label_article,//"目的标签商品列表"
	    width:'47%',
	    region:'east',
	    store:rodata_dLabelNoArticleStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:85,
			text:$i18n.label_no,//标签号
			dataIndex:'labelNo',
		},{
			width:100,
			text:$i18n.article_name,//商品名称
			dataIndex:'articleName'
		},{
		    width:110,
		    text : $i18n.barcode,//商品条码
		    dataIndex:'barcode'			
	    },{
		    width:60,
		    text : $i18n.qty1,//数量
		    dataIndex:'articleQty'			
	    },{
			width : 80,
			text : $i18n.packing_qty,//包装数量
			dataIndex:'packingQty'
		},{
			width : 80,
			text : $i18n.packing_unit,//包装单位
			dataIndex:'packingUnit',
			id:'packingUnit7401_2'}]
	  },{
			width : 85,
			text : $i18n.packingSpec,//规格
			dataIndex : 'packingSpec',
			id:'packingSpec7401_2'
		}]
	}]
});