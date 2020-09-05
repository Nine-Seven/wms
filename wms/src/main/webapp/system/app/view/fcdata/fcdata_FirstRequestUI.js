/**
 * 模块名称：初盘回单
 * 模块编码：8401
 * 创建：Jun
 */
var fcdata_FirstRequestMStore=Ext.create('cms.store.fcdata.fcdata_FirstRequestMStore',{
	autoLoad:true,
	//7-18添加
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			debugger;
			var selectFlag=_myAppGlobal.getController('cms.controller.fcdata.fcdata_FirstRequestController').getSelectFlag();
			var serialnoFlag=_myAppGlobal.getController('cms.controller.fcdata.fcdata_FirstRequestController').getSerialnoFlag();
			
			 if(th.count()>0){
				 if(selectFlag == '1'){
					 for(var i=0; i<th.count();i++){
	   					 var data = Ext.getCmp('grid_01_8401').getStore().getAt(i);
	   					 if(data.get('serialNo')== serialnoFlag){
	   						 Ext.getCmp('grid_01_8401').getSelectionModel().select(i);
	   						 //return;
	   					 }		
	   				 }
				 }else{
					 Ext.getCmp('grid_01_8401').getSelectionModel().select(0);
				 }
			 }
		}
	}
});
var fcdata_Receipt_DStore=Ext.create('cms.store.fcdata.fcdata_FirstRequestDStore',{
	autoLoad:false
	});
Ext.define('cms.view.fcdata.fcdata_FirstRequestUI',{
	alias:'widget.fcdata_FirstRequestUI',
	title:$i18n.title8401,//盘点回单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.bdef_DefArticleCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo'
	          ],
    items:[
    {
        xtype:'commMenuWidget4',
	    id:'menu8401',
	    region:'north'
	},
	{
		xtype : 'form',
		region : 'north',
		layout:'column',
		border:false,
		width:'100%',
		frame : true,
		defaults : {
			labelWidth : 90,
			margin : '2 2 2 2',
			labelAlign : 'right'
		},
		items:[
		{
			xtype : 'radiogroup',
			id : 'rdoCheckType8401',
			fieldLabel : $i18n.fcdata_type,//盘点类型
			width : 300,
			columns : 3,
			items : [
	        {
				boxLabel : $i18n.big_plane,//大盘
				name : 'rd',
				inputValue : '1',
				checked:true
			},
			{
				boxLabel : $i18n.circle_plane,//循环盘
				name : 'rd',
				inputValue : '2'
			},
			{
				boxLabel : $i18n.untieSell_plan,//动销盘
				name : 'rd',
				inputValue : '3'
			}
			]
		}       
		]
	},
	{
	    xtype:'panel',
   		region:'north',
   		layout:'border',
   		height:200,
   		items:[
        {
	    	xtype : 'grid',
			region : 'center',//'west',
			id:'grid_01_8401',
			store:fcdata_FirstRequestMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 
			{
				width : 100,
				text : $i18n.serialno,//流水号
				dataIndex:'serialNo'
			}, 
			{
				width : 140,
				text : $i18n.chcheck_no,//盘点单号
				dataIndex:'checkNo'
			}, 
			{
				width : 100,
				text : $i18n.status2,//操作状态
				dataIndex:'statusText'
			}, 
			{
				width : 100,
				text : $i18n.fcdata_type,//盘点类型
				dataIndex:'fcdatatypeText'
			}, 
			{
				width : 100,
				text : $i18n.check_type,//盘点类别
				dataIndex:'checktypeText'
			},
			{
				width:80,
				text:$i18n.cust_zone,//区域
				dataIndex:'ware'
			}
			],
		    dockedItems : 
		    [
		    {
		        xtype : 'pagingtoolbar',
		        store : fcdata_FirstRequestMStore,
		        dock : 'bottom',
		        displayInfo : true
		    }
		    ]
        },
        {
        	xtype : 'form',
        	id:'form_01_8401',
			layout:
			{
				type : 'table',
				columns : 1
			},
			region : 'east',
			frame : true,
			width : '40%',
			items:[
	        {
		    	margin:'20 0 0 0',
			    xtype:'fieldset',  
				defaults:
				{
				  	xtype:'textfield',
				  	labelAlign:'right'
				},
			    items:[
				{
					xtype : 'bdef_DefWorkerCombo',
					id:'cmbWorkerNo8401',
					fieldLabel : $i18n.chcheck_name,// 盘点人员
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
					beforeLabelTextTpl : required
				}
				]

	        },
			{
		        margin:'20 0 0 0',
				xtype:'fieldset',  
				autoHeight:true,  
				items:[
				{
					layout:
					{
					type : 'table',
					columns : 4
					},
					xtype:'container',
					margin:'5 0 0 15',
					defaults:
					{
						margin : '2 2 2 2',
						//disabled:true,
						labelAlign : 'right'
					},
					items:[
				    {
			    	    xtype : 'button',
			    	    margin:'2 0 0 5',
			    	    id:'btnNewitem8401',
			    	    text : $i18n.newitem//新增品项
					},
					{
						xtype : 'button',
						margin:'2 0 0 20 ',
						id:'btnNoDifference8401',
						text:$i18n.nodifferent//'无差异保存'
					},
					{
			    	    xtype : 'button',
			    	    margin:'2 0 0 20',
			    	    id:'btnZeroreceipt8401',
			    	    text : $i18n.zeroreceipt //零回单
					},
					{
			    	    xtype : 'button',
			    	    margin:'2 0 0 20',
			    	    id:'btnConfirm8401',
			    	    text : $i18n.confirm //确认
					}
					]
				}
				]
			}
	        ]
		}
        ]
	},
	{
		xtype : 'grid',
		region : 'center',//'south',
		//height : 360,
		width : '100%',
		id:'grid_02_8401',
		store:fcdata_Receipt_DStore,
        viewConfig : 
        {   
             forceFit : true,   
             getRowClass : function(record,rowIndex,rowParams,store)
             {   
            	if(record.data.addFlag==1)
            	{
            		return 'x-grid-record-yellow';
            	}else if(record.data.packingQty*record.data.planBox+record.data.planDis<record.data.articleQty)
            	{
            		return 'x-grid-record-green';
            	}else if(record.data.packingQty*record.data.planBox+record.data.planDis>record.data.articleQty)
            	{
            		return 'x-grid-record-red';  
            	}else
            	{
            		return '';
            	}
             }   
        },
		plugins : [Ext.create('Ext.grid.plugin.CellEditing',{
			clicksToEdit : 1,
			onSpecialKey:function(ed,field,e)
			{
				commEnterGridStatEdit(this.grid,false,'',e.getKey());
			}
		})],
		columns : [
        {
			xtype : 'rownumberer',
			width : 30
		},
		{
			width : 70,
			text : $i18n.cell_no,//储位
			dataIndex:'cellNo'
		},
		{
			width : 105,
			text : $i18n.article_no,//商品编码
			dataIndex:'articleNo'
		},{
			width : 100,
			text : $i18n.owner_article_no,//货主商品编码
			dataIndex : 'ownerArticleNo'
		},
		{
			width : 105,
			text : $i18n.barcode,//商品条码
			dataIndex:'barcode'
		},
		{
			width : 160,
			text : $i18n.article_name,//商品名称
			dataIndex:'articleName'
		},
		{
			width : 60,
			text : $i18n.new_packing_qty,//包装数量
			dataIndex:'packingQty'
		},
		{
			width : 60,
			text : $i18n.packingUnit,//包装单位
			dataIndex:'packingUnit',
			id:'packingUnit8401'
		},
		{
			width : 85,
			text : $i18n.packingSpec,//规格
			dataIndex : 'packingSpec',
			id:'packingSpec8401'
		},
		{
			width : 85,
			text : $i18n.boxQty,//初盘箱数
			dataIndex : 'planBox',
			//hidden:true,
			id:'planBox8401',
			cls : 'notnull',
			field : {
	    		xtype : 'numberfield',
	    		minValue:0
	    	}
		},{
			width : 85,
			text : $i18n.qminQty,//初盘中包数
			dataIndex : 'planQmin',
			id:'planQmin8401',
			cls : 'notnull',
			field : {
	    		xtype : 'numberfield',
	    		minValue:0
	    	}
		},{
			width : 85,
			text : $i18n.disQty,//初盘散数
			dataIndex : 'planDis',
			id:'planDis8401',
			cls : 'notnull',
			field : {
	    		xtype : 'numberfield',
	    		minValue:0
	    	}
		},
	
		{
			width : 80,
			text : $i18n.produce_date,//生产日期
			dataIndex:'produceDate',
			hidden:$i18n.produceDateHidden,
            renderer:function(value){   
			    if(value instanceof Date){   				 
			        return Ext.Date.format(value,'Y-m-d');   
			    }else{				        
			        return value;   
			    }  
			}
		},
		{
			width : 80,
			text : $i18n.expire_date,//有效期至
			dataIndex:'expireDate',
			hidden:$i18n.expireDateHidden,
            renderer:function(value){   
			    if(value instanceof Date){   				 
			        return Ext.Date.format(value,'Y-m-d');   
			    }else{				        
			        return value;   
			    }  
			}
		},
		{
			width : 80,
			text : $i18n.label_no2,//主标签号
			dataIndex:'labelNo'
		},
		{
			width : 80,
			text : $i18n.sub_label_no,//子标签号
			dataIndex:'subLabelNo'
		},
		{
			width:60,
			text:$i18n.quality,//'品质',
			hidden:$i18n.qualityHidden,
			dataIndex:'qualityText'
		},
		{
			width:150,
			text:$i18n.lot_no,//'批号',
			hidden:$i18n.lotNoHidden,
			dataIndex:'lotNo'
		},
		{
			width:100,
			text:$i18n.reserved1,//'预留字段1',
			hidden:$i18n.rsvBatch1Hidden,
			dataIndex:'rsvBatch1'
		},
		{
			width:100,
			text:$i18n.reserved2,//'预留字段2',
			hidden:$i18n.rsvBatch2Hidden,
			dataIndex:'rsvBatch2'
		},
		{
			width:100,
			text:$i18n.reserved3,//'预留字段3',
			hidden:$i18n.rsvBatch3Hidden,
			dataIndex:'rsvBatch3'
		},
		{
			width:100,
			text:$i18n.reserved4,//'预留字段4',
			hidden:$i18n.rsvBatch4Hidden,
			dataIndex:'rsvBatch4'
		},
		{
			width:100,
			text:$i18n.reserved5,//'预留字段5',
			hidden:$i18n.rsvBatch5Hidden,
			dataIndex:'rsvBatch5'
		},
		{
			width:100,
			text:$i18n.reserved6,//'预留字段6',
			hidden:$i18n.rsvBatch6Hidden,
			dataIndex:'rsvBatch6'
		},
		{
			width:100,
			text:$i18n.reserved7,//'预留字段7',
			hidden:$i18n.rsvBatch7Hidden,
			dataIndex:'rsvBatch7'
		},
		{
			width:100,
			text:$i18n.reserved8,//'预留字段8',
			hidden:$i18n.rsvBatch8Hidden,
			dataIndex:'rsvBatch8'
		}
		]
	},
	{
		xtype : 'panel',
		id : 'msterForm8401',
		region:'south',
		html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
				+ 'background-color:#C1D5ED; text-align: left;">'
				+ '<span><label>'+$i18n.addPeople+':</label><label id="rgstName8401"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.addTime+'</label><label id="rgstDate8401"></label></span>'
				+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.local_path+'：</label><label id="updtName8401"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.editTime+'：</label><label id="updtDate8401"></label> </span></div>'
	}
	]
});
