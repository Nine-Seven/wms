/**
 * 模块名称：返配次品扫描验收
 * 模块编码：6901
 * 创建：hekl
 */
Ext.define('cms.view.ridata.ridata_CheckBadUI',{
	alias:'widget.ridata_CheckBadUI',
	title:$i18n.title6901,//返配次品验收
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
	    id:'menu6901'
	 },{
		xtype : 'form',
		layout:'column',
		region : 'east',
		width:'30%',
		frame : true,
		id:'formCondition6901',
		items:[{
		    layout:{
				type : 'table',
				columns : 1
			},
			xtype:'container',
			margin:'20 0 0 0',
			defaults:{
				labelWidth : 90,
				margin : '0 0 5 20',
				labelAlign : 'right',
				xtype:'textfield'
		    },
			items:[
			{
			 xtype:'bdef_DefOwnerCombo',
			 fieldLabel:$i18n.owner,//货主编号
			 queryMode:'local',
		     allowBlank:true,
		     id:'cmbOwnerNo6901',
		     displayField: 'dropValue',
			 valueField: 'value',
			 //readOnly:true,
			 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
			 {
				 proxy:{
						type:'ajax',
						method:'post',
						url:'ridata_CheckBadAction_getComboList',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
				 },
			  	 extraParams:{
			         strFlag : "1"
			     },
				 listeners:{  
					 'load':function(th,records,successful,eOpts ){
						 if(th.count()>0){
							 Ext.getCmp('cmbOwnerNo6901').setValue(th.getAt(0).data.value);
						 }
					 }
				 }
				 }),
			    beforeLabelTextTpl:required
			},{
				fieldLabel:$i18n.scanStation,//扫描台
				id : 'cmbDockNo6901',	
			   beforeLabelTextTpl : required
			},{
				xtype : 'bdef_DefWorkerCombo',
				fieldLabel : $i18n.dp_worker,//  验收人
				id : 'cmbWorkerNo6901',
				//readOnly:true,
				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
				beforeLabelTextTpl : required
			},
			{
   	    	xtype:'ridata_UntreadNoCombo',
	   	    fieldLabel:'原返配单号',//原返配单号
	   	    id:'cmbUntreadNo6901',
	   	    displayField: 'dropValue',
		    valueField: 'value',
		    store:Ext.create("cms.store.ridata.ridata_UntreadNoComboStore"),
		    //readOnly:true,
	   	    beforeLabelTextTpl : required
	    },{
			xtype : 'remoteCombo',
			fieldLabel:$i18n.dest_cell_no,//上架储位
			id : 'cmbCellNo6901',	
			displayField: 'dropValue',
			valueField: 'value',
			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
			   proxy:{
					type:'ajax',
					method:'post',
					url:'ridata_CheckBadAction_getCdef_DefCellCombo',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				},
				 listeners:{  
					 'load':function(th,records,successful,eOpts ){
						 if(th.count()>0){
							 Ext.getCmp('cmbCellNo6901').setValue(th.getAt(0).data.value);
						 }
					 }
				 },
			   listConfig: {
		           loadingText: '查询中...',
		           emptyText: '没有找到相应的数据！' ,
		           getInnerTpl: function() {
		        	   return '{value}';
		           }
		       }
			}),
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
		},{
			fieldLabel:$i18n.scanBarcode,//扫条码
		    xtype : 'textfield',		    
			id:'txtBarcode6901'
			//readOnly:true
			//beforeLabelTextTpl : required,
			//colspan:2
		},{
			xtype:'container',
			margin:'0 0 0 70',
			items:[
			{
				xtype : 'button',
				id:'btnClosePal6901',
				text : '　确认　'
			}] 
		}]
		}]
        },{
    		xtype : 'grid',
    		id:'grid_01_6901',
			region : 'east',
			width:'70%',
			store:Ext.create('cms.store.ridata.ridata_CheckBadDStore'),
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
				width : 130,
				text : $i18n.s_untread_no,//返配汇总单号
				dataIndex : 'SUntreadNo'
			},{
				width : 180,
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
			 }]
    	},{
    		xtype : 'grid',
    		id:'grid_02_6901',
    		store:Ext.create('cms.store.ridata.ridata_CheckPalTmpStore',{
    				 proxy:{
    						type:'ajax',
    						method:'post',
    						url:'ridata_CheckBadAction_queryCheckPalTmp',
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
				text : $i18n.qty_no,//数量
				dataIndex : 'checkQty'
				},{
					 width : 60,
					 text : $i18n.packing_qty,//包装数量
					 dataIndex : 'packingQty'
				 },
				 {
						width:80,
						text:$i18n.packingSpec,//规格
						id:'packingSpec_6901',
						dataIndex:'packingSpec'
					},
					{
						width : 60,
						text : $i18n.packing_qty1,//包装单位
						id:'packingUnit_6901',
						dataIndex:'packingUnit'
					}]
	    	
	   
	 }]
});