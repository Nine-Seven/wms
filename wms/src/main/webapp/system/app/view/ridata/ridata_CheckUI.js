/**
 * 模块名称：返配扫描验收
 * 模块编码：6201
 * 创建：周欢
 */
var ridata_check_MStore=Ext.create('cms.store.ridata.ridata_CheckMStore',{autoLoad:false});
Ext.define('cms.view.ridata.ridata_CheckUI',{
	alias:'widget.ridata_CheckUI',
	title:'返配扫描验收',//返配扫描验收
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.ridata_UntreadNoCombo',
	          'cms.view.common.bdef_DefDockCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
	 {
	    xtype:'commMenuWidget4',
	    region:'north',
	    id:'menu6201'
	 },
	 {
	 	xtype:'panel',
	    layout:'border',
	    region:'center',
	    items:[{
	    	xtype : 'form',
	    	id:'form_01_6201',
			layout:'column',
			region : 'north',
			frame : true,
			width : '100%',
			height:35,
			items:[{
			    layout:{
					type : 'table',
					columns : 3
				},
				xtype:'container',
				defaults:{
					labelWidth : 70,
					margin : '5 0 5 0',
					labelAlign : 'right',
					xtype:'textfield'
				},
				items:[
				{
					 xtype:'bdef_DefOwnerCombo',
					 fieldLabel:$i18n.owner,//货主编号
	            	 queryMode:'local',
		             allowBlank:true,
		             id:'cmbOwnerNo6201',
		             displayField: 'dropValue',
					 valueField: 'value',
					 //readOnly:true,
					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
					 {
						 proxy:{
								type:'ajax',
								method:'post',
								url:'ridata_CheckAction_getComboList',
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
									 Ext.getCmp('cmbOwnerNo6201').setValue(th.getAt(0).data.value);
									 _myAppGlobal.getController('cms.controller.ridata.ridata_CheckController').cmbOwnerNo6201Change();
								 }
							 }
						 }
	   				 }),
		             beforeLabelTextTpl:required
				},{
					xtype : 'remoteCombo',
					fieldLabel:$i18n.scanStation,//扫描台
					id : 'cmbDockNo6201',	
					displayField: 'dropValue',
    				valueField: 'value',
    				//readOnly:true,
					store:Ext.create("cms.store.ridata.ridata_DockComboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'ridata_CheckAction_queryDockCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
					}),
					listConfig: {
			           loadingText: '查询中...',
			           emptyText: '没有找到相应的数据！' ,
			           getInnerTpl: function() {
			        	   return '{dropValue}';
			           }
			        },
			        beforeLabelTextTpl : required
				},{
					xtype : 'bdef_DefWorkerCombo',
					fieldLabel : $i18n.dp_worker,//  验收人
					id : 'cmbWorkerNo6201',
					//readOnly:true,
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
					beforeLabelTextTpl : required
				}]
			}]
	    },
	    {
	        xtype : 'tabpanel',
		    region:'center',
		    id:'tabPId6201',
		    items:[
		    {
		    	title : $i18n.ridataCheck,//返配验收
		    	id:'tabPId6201_T1',
		    	width:'100%',
				layout:'border',
		    	items:[{
		    	    xtype : 'form',
					region : 'west',
					width:'35%',
					frame : true,
					id:'formCondition6201',
					defaults:{
						labelWidth : 90,
						margin : '0 0 5 20',
						labelAlign : 'right',
						xtype:'textfield'
				    },
				    layout:{
						type : 'table',
						columns : 2
					},
					items:[{
						fieldLabel:$i18n.deviceNo,//扫描墙号
					    xtype : 'textfield',
					    
						id:'txtdeviceNo6201',
						//readOnly:true
						beforeLabelTextTpl : required,
						colspan:2
					},
					{
			   	    	xtype:'ridata_UntreadNoCombo',
				   	    fieldLabel:'原返配单号',//原返配单号
				   	    id:'cmbUntreadNo6201',
				   	    displayField: 'dropValue',
					    valueField: 'value',
					    store:Ext.create("cms.store.ridata.ridata_UntreadNoComboStore"),
					    //readOnly:true,
				   	    beforeLabelTextTpl : required
				    },{
						xtype:'container',
						margin:'0 0 0 10',
						items:[
						{
							xtype:'label',
							cls:'classDiv1',
							text:'预备箱数:'
						},
						{
							xtype:'label',
							//readOnly:true,
							cls:'classDiv1',
							margin:'0 0 0 15',
							id:'SupperAllotCell6201',
							text:''
						}]
					},{
						fieldLabel:$i18n.scanBarcode,//扫条码
					    xtype : 'textfield',
					    
						id:'txtBarcode6201',
						//readOnly:true
						//beforeLabelTextTpl : required,
						colspan:2
					},{
						xtype:'container',
						margin:'0 0 0 70',
						items:[
						{
							xtype:'label',
							cls:'classDiv1',
							text:'箱号:'
						},
						{
							xtype:'label',
							//readOnly:true,
							cls:'classDiv1',
							margin:'0 0 0 0',
							id:'lblBoxText6201',
							text:''
						}]
					}]
		    	},{
		    		xtype : 'grid',
		    		id:'grid_01_6201',
					region : 'east',
					width:'65%',
					store:Ext.create('cms.store.ridata.ridata_CheckDStore'),
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
						width : 80,
						text : $i18n.packing_qty,//包装数
						dataIndex : 'packingQty'
					}/*{
						width : 100,
						text : $i18n.produce_date,//生产日期
						cls:'notnull',
						dataIndex : 'produceDate',
						field:{
							format : 'Y-m-d',
							xtype:'datefield'
						},	                
			            renderer:function(value){   
						    if(value instanceof Date){   				 
						        return Ext.Date.format(value,'Y-m-d');   
						    }else{				        
						        return value;   
						    }  
						}
					},{
						width : 100,
						text : $i18n.expire_date3,// 
						dataIndex : 'expireDate'
					},{
						width : 60,
						text : $i18n.packing_unit,//单位
						dataIndex : 'unit'
					},{
						width : 80,
						text : $i18n.spec,//规格
						dataIndex : 'spec'
					}*/]
		    	},{
		    		xtype : 'grid',
		    		id:'grid_02_6201',
		    		//store:Ext.create('cms.store.ridata.ridata_CheckDStore'),
		    		store:Ext.create('cms.store.ridata.ridata_CheckPalTmpStore',{autoLoad:false}),
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
						width : 80,
						text : $i18n.packing_qty,//包装数
						dataIndex : 'packingQty'
					},{
						width:80,
						text:$i18n.packingSpec,//规格
						id:'packingSpec_6201',
						dataIndex:'packingSpec'
					},
					{
						width : 60,
						text : $i18n.packing_qty1,//包装单位
						id:'packingUnit_6201',
						dataIndex:'packingUnit'
					}
					
					
					/*,{
						width : 60,
						text : $i18n.packing_unit,//单位
						dataIndex : 'unit'
					}*/]
		    	}
		    	]
		    },{
		    	title : '封箱界面',//封箱界面
		    	width:'100%',
		    	id:'tabPId6201_T2',
				layout:'border',
		    	items:[{
		    		xtype : 'grid',
			        region : 'north',
				    width : '100%',
				    height:200,
				    id:'grid_03_6201',
				    store:Ext.create('cms.store.ridata.ridata_CheckPalTmpStore',
				    {
						proxy:{
							type:'ajax',
							method:'post',
							url:'ridata_CheckAction_queryTmpLabelList.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
					}),
				    multiSelect: true,  
					selModel: {  
					    selType:'checkboxmodel',
					    checkOnly:true
					},
				    columns:[ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 90,
						text : '箱号',
						dataIndex : 'labelId'
					},{
				    	width : 90,
						text : $i18n.label_no,//标签号
						dataIndex : 'labelNo'
				    }]
		    	},{
		    		xtype : 'grid',
			        region : 'center',
				    width : '100%',
				    id:'grid_04_6201',
				    store:Ext.create('cms.store.ridata.ridata_CheckPalTmpStore',
				    {
						proxy:{
							type:'ajax',
							method:'post',
							url:'ridata_CheckAction_queryTmpLabelDetail.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
					}),
				    columns:[ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 90,
						text : '箱号',
						dataIndex : 'labelId'
					},{
				    	width : 120,
						text : $i18n.label_no,//标签号
						dataIndex : 'labelNo'
				    },
	//			    {
//				    	width : 130,
//						text : '返配汇总单号',//返配汇总单号
//						dataIndex : 'SUntreadNo'
//				    },{
//				    	width : 130,
//						text : '验收汇总单号',//验收汇总单号
//						dataIndex : 'SCheckNo'
//				    },
					{
				    	width : 120,
						text : '商品条码',//商品条码
						dataIndex : 'barcode'
				    },{
				    	width : 120,
						text : '商品编码',//商品编码
						dataIndex : 'articleNo'
				    },{
				    	width : 140,
						text : '商品名称',//商品编码
						dataIndex : 'articleName'
				    },{
				    	width : 80,
						text : '验收数量',
						dataIndex : 'checkQty'
				    }/*,{
						width : 60,
						text : $i18n.packing_unit,//单位
						dataIndex : 'unit'
					}*/,{
						width : 80,
						text : $i18n.packing_qty,//包装数
						dataIndex : 'packingQty'
					}]
		    	},{
		    		xtype : 'form',
			        region : 'south',
			        frame : true,
			        height:35,
				    width : '100%',
				    id:'formCloseBox6201',
    				 layout : {
    					 type : 'table',
    					 columns :1
    				 },
    				defaults : {
    					labelWidth : 80,
    					margin:'0 0 0 500',
    					labelAlign:'right'
    				},
    				items:[
					{
						xtype : 'button',
						id:'btnClosePal6201',
						text : '封箱'
    				}]
		    	}]
		    }]
	    }]
	   
	 }
	]
});