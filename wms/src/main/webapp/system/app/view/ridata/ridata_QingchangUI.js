/**
 * 模块名称：返配快速扫描验收
 * 模块编码：6902
 * 创建：hekl
 */
var CheckBadDStore= Ext.create('cms.store.ridata.ridata_CheckBadDStore',{
				proxy:{
				type:'ajax',
				method:'post',
				url:'ridata_QingchangAction_getRidata_QingchangDList',
				reader:{
					root:'rootList',
					totalProperty:'totalCount'
				    }
			    }
			});
Ext.define('cms.view.ridata.ridata_QingchangUI',{
	alias:'widget.ridata_QingchangUI',
	title:$i18n.title6902,//返配清场扫描验收
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu10',
	          'cms.view.common.ridata_UntreadNoCombo',
	          'cms.view.common.bdef_DefDockCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
	 {
	    xtype:'commMenuWidget10',
	    region:'north',
	    id:'menu6902'
	 },{
		xtype : 'form',
		layout:'column',
		region : 'east',
		width:'37%',
		frame : true,
		id:'formCondition6902',
		items:[{
		    layout:{
				type : 'table',
				columns : 2
			},
			xtype:'container',
			margin:'20 0 0 0',
			defaults:{
				labelWidth : 80,
				margin : '0 0 5 0',
				labelAlign : 'right',
				xtype:'textfield'
		    },
			items:[
			{
			 xtype:'bdef_DefOwnerCombo',
			 fieldLabel:$i18n.owner,//货主编号
			 queryMode:'local',
		     allowBlank:true,
		     id:'cmbOwnerNo6902',width:200,
		     displayField: 'dropValue',
			 valueField: 'value',
			 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
			 {
				 proxy:{
						type:'ajax',
						method:'post',
						url:'ridata_QingchangAction_getComboList',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
				 },
				 listeners:{  
					 'load':function(th,records,successful,eOpts ){
						 if(th.count()>0){
							 Ext.getCmp('cmbOwnerNo6902').setValue(th.getAt(0).data.value);
						 }
					 }
				 }
				 }),
			    beforeLabelTextTpl:required
			},{
		    	fieldLabel:$i18n.u_type,
				id : 'cmbQuality6902',
				width:200,
				disabled:true,
		    },{
				fieldLabel:$i18n.scanStation,//扫描台
				id : 'cmbDockNo6902',width:200,
			    beforeLabelTextTpl : required
			},{
				xtype : 'bdef_DefWorkerCombo',
				fieldLabel : $i18n.dp_worker,//  验收人
				id : 'cmbWorkerNo6902',width:200,
				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
				beforeLabelTextTpl : required
			},
			{
   	    	xtype:'ridata_UntreadNoCombo',
	   	    fieldLabel:'原返配单号',//原返配单号
	   	    id:'cmbUntreadNo6902',
	   	    width:400,
	   	    displayField: 'dropValue',
		    valueField: 'value',
		    store:Ext.create("cms.store.ridata.ridata_UntreadNoComboStore"),
		    colspan:2,
		    //readOnly:true,
	   	    beforeLabelTextTpl : required
	    },{
			fieldLabel:$i18n.scanBarcode,//扫条码
		    xtype : 'textfield',		    
			id:'txtBarcode6902',width:200
		},{
			fieldLabel:'扫描基准量',//扫描基准量
			xtype:'numberfield',
			id:'sacnNum6902',
			width:200,
			beforeLabelTextTpl:required,		
		},{

			xtype:'container',
			margin:'0 0 0 80',
			items:[
			{
				xtype:'label',
				readOnly:true,
				cls:'classDiv1',
				text:'未验数量：'
			},
			{
				xtype:'label',
				readOnly:true,
				id:'unQty6902',
				margin:'0 5 0 10',
				cls:'classDiv1',
				text:'0'
			}]
		
		},{
			xtype:'container',
			margin:'0 0 0 80',
			items:[
			{
				xtype:'label',
				readOnly:true,
				cls:'classDiv1',
				text:'已验数量：'
			},
			{
				xtype:'label',
				readOnly:true,
				id:'nQty6902',
				margin:'0 5 0 10',
				cls:'classDiv1',
				text:'0'
			}]
		},{

			xtype:'fieldset',
			title:'封箱',
			colspan:2,
		    layout: 
		    {
		        type: 'table',
		        columns: 2
		    },
		    defaults : 
		    {
		    	labelWidth : 80,
				margin : '0 0 0 20',
				labelAlign : 'lefth',
				xtype:'textfield'		
		    },
		    items:[
            {
            	xtype : 'remoteCombo',
    			fieldLabel:$i18n.dest_cell_no,//上架储位
    			width:200,
    			id : 'cmbCellNo6902',	
    			displayField: 'dropValue',
    			maxLength:50,
    			valueField: 'value',
    			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore'),
   	        },
   	        {
   				xtype:'container',
   				margin:'0 0 0 10',
   				items:[
   				{
   					xtype : 'button',
   					id:'btnClosePal6902',
   					text : '　封箱　'
   				}] 
   			}]
		}]
		}]
        },{
    		xtype : 'grid',
    		id:'grid_01_6902',
			region : 'east',
			width:'63%',
			store:CheckBadDStore,
	   	    selModel : {
				selType : 'cellmodel'
		    },
		    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
		 		clicksToEdit : 1
		    })],
		    columns:[
	   	    {
				xtype : 'rownumberer',
				width : 30
			},{
				width : 100,
				text : $i18n.s_untread_no1,//返配汇总单号
				dataIndex : 'SUntreadNo'
			},{
				width : 150,
				text : $i18n.article_name,//商品名称
				dataIndex : 'articleName'
			},{
				width : 120,
				text : $i18n.barcode,//条码
				dataIndex : 'barcode'
			},{
				width : 90,
				text : $i18n.cust_no,
				dataIndex : 'custNo' 
		    },{
				width : 140,
				text : $i18n.cust_name,
				dataIndex : 'custName' 
		    },{
				width : 60,
				text : $i18n.unCheckQty,//未验量
				dataIndex : 'untreadQty'
			},{
				 width : 60,
				 text : $i18n.packing_qty,//包装数量
				 dataIndex : 'packingQty'
			 }],
			dockedItems : [{
				xtype : 'pagingtoolbar',
				dock : 'bottom',
				store:CheckBadDStore,
				displayInfo : true
			}]
    	},{
    		xtype : 'grid',
    		id:'grid_02_6902',
    		store:Ext.create('cms.store.ridata.ridata_CheckPalTmpStore',{
    				 proxy:{
    						type:'ajax',
    						method:'post',
    						url:'ridata_QingchangAction_queryCheckPalTmp',
    						reader:{
    							root:'rootList',
    							totalProperty:'totalCount'
    						}
    					},
    				autoLoad:false}),
			region : 'south',
			width : '100%',
			height:300,
			columns:[
	   	    {
				xtype : 'rownumberer',
				width : 30
			},{
				width: 110,
				text : '箱号',
				dataIndex:'labelId'
			},{
				width: 110,
				text : '标签号',
				dataIndex:'labelNo'
			},{
				width : 120,
				text : $i18n.barcode,//条码
				dataIndex : 'barcode'
			},{
			    width : 200,
				text : $i18n.article_name,//商品编码
				dataIndex : 'articleName'
			},{
				width : 90,
				text : $i18n.qty1,//数量
				dataIndex : 'checkQty'
				},{
					 width : 60,
					 text : $i18n.packing_qty,//包装数量
					 dataIndex : 'packingQty'
				 },
				 {
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_6902',
					dataIndex:'packingSpec'
				},
				{
					width : 60,
					text : $i18n.packing_qty1,//包装单位
					id:'packingUnit_6902',
					dataIndex:'packingUnit'
				}
			]
	    	
	   
	 }]
});