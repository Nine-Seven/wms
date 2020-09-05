/**
 * 模块名称：商品资料维护
 * 模块编码   商品主档：1401  商品包装:1401_d2   商品条码:1401_d3
 * @author JUN
 * 修改By Panzx
 */
var storePacking1401_d2=Ext.create('cms.store.bdef.bdef_ArticlePackingStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1401_d2').getStore().count()>0){
				Ext.getCmp('grid_01_1401_d2').getSelectionModel().select(0);
				Ext.getCmp('grid_01_1401_d2').fireEvent('itemclick','');
			}
		}
	}
});
var storeBarcode1401_d3=Ext.create('cms.store.bdef.bdef_ArticleBarcodeStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1401_d3').getStore().count()>0){
				Ext.getCmp('grid_01_1401_d3').getSelectionModel().select(0);
				Ext.getCmp('grid_01_1401_d3').fireEvent('itemclick','');
				
			}
		}
	}
});

var cset_CellArticleStore=Ext.create('cms.store.cset.cset_CellArticleStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_06_1401').getStore().count()>0){
				Ext.getCmp('grid_06_1401').getSelectionModel().select(0);
				Ext.getCmp('grid_06_1401').fireEvent('itemclick','');
			}
		}
	}


});

Ext.define('cms.view.bdef.window.bdef_DefarticleAddOrEditWindow', 
{
	extend : 'Ext.window.Window',
	alias : 'widget.bdef_DefarticleAddOrEditWindow',
	layout : 'border',
	width : 1000,
	height : 645,
	modal : true,
	id : 'bdef_DefArticleAddOrEditWindow',
	items : [
	{
		xtype : 'form',
		id : 'form_01_1401',
		region : 'north',
		height:60,
		frame : true,
		layout: 
        {
        	type: 'table',
        	columns: 3
        },
		defaults : 
        {
        	xtype : 'textfield',
        	labelWidth : 110,
        	labelAlign:'right'			
 	    },
		items:[
		{
			xtype:'bdef_DefOwnerCombo',
			fieldLabel : $i18n.owner_no,//货主编号
			id:'cmbOwnerNo1401',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
			displayField : 'dropValue',
		    valueField : 'value',
	        allowBlank : false,
	        editable:false,
	        beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.owner_article_no,//货主商品编码
			maxLength:25,
			id : 'txtOwnerArticleNo1401',
	        allowBlank : false,
	        beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.article_no,//商品编码
			maxLength:15,
			id : 'txtArticleNo1401',
	        allowBlank : false,
	        beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.article_name,//商品名称
	        allowBlank : false,
	        maxLength:120,
	        id:'txtArticleName1401',
	        colspan:2,
	        width:530,
	        beforeLabelTextTpl : required
		},{
			xtype:'wms_DefFieldValCombo',
			fieldLabel:$i18n.a_out,//出货标识
			maxLength:20,
			editable:false,
			hidden:true,
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"K,A_OUT"}
	        }),
			id : 'cmbAOut1401'
		},{
			xtype:'wms_DefFieldValCombo',
			id : 'status1401',
			fieldLabel:$i18n.manage_status,//商品状态
			maxLength:20,
			editable:false,
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"BDEF_DEFARTICLE,STATUS"}
	        })
		}
		]
	},
	{
		xtype : 'tabpanel',
	    region:'center',
	    id:'tabPid1401',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.title200001window,//新增基本信息
	        layout:'border',
	        id:'tabPid1401_01',
	        items:[
            {
            	xtype : 'form',
                region:'center',
                id:'form_02_1401',
                frame : true,
                items :[
                {
                    xtype:'fieldset',
                    layout: 
                    {
                    	type: 'table',
                    	columns: 3
                    },
                    defaults : 
                    {
                    	xtype : 'textfield',
                    	labelWidth : 110,
                    	margin:'5 0 0 0 ',
                    	labelAlign:'right'			
	  	       	    },
	       	        items:[
	       	        {
	       	        	xtype:'form',
	       	        	frame : true,
	       	        	colspan:3,
	       	        	baseCls:'my-panel-no-border',
	       	        	layout : "form",
	       	        	margin:'-1 0 0 0 ',
	       	        	items : [
	       	        	{ 
		       	            layout : "column", // 从左往右的布局
		       	            baseCls:'my-panel-no-border',
		       	            items : [
		       	            {
					       	    columnWidth : .5, // 该列有整行中所占百分比
					       	    layout : "form", // 从上往下的布局
					       	    baseCls:'my-panel-no-border',
					       	    items : [
						       	    {	
						       	        xtype:'textfield',
						       	    	fieldLabel : $i18n.article_alias,//商品简称
							       	    id : 'txtArticleAlias1401',
							       	    labelAlign:'right',
							       	    maxLength:60,
							       	    labelWidth : 110
						       	    }]
					       	 },
		       	            {
		       	            	columnWidth : .5, // 该列有整行中所占百分比
		       	                layout : "form", // 从上往下的布局
		       	                baseCls:'my-panel-no-border',
		       	                items : [
		       	                {	
		       	                	xtype:'textfield',
				       	    	    fieldLabel : $i18n.article_ename,//英文名称
					       	        id : 'txtArticleEname1401',
					       	        labelAlign:'right',
					       	        labelWidth : 110,
					       	        maxLength:120
				       	        }
		       	                ]
		       	            }]
	       	        	}]
	       	        },{

	       	        	xtype:'form',
	       	        	frame : true,
	       	        	colspan:3,
	       	        	baseCls:'my-panel-no-border',
	       	        	layout : "form",
	       	        	margin:'-1 0 0 0 ',
	       	        	items : [
	       	        	{ 
		       	            layout : "column", // 从左往右的布局
		       	            baseCls:'my-panel-no-border',
		       	            items : [
		       	            {
					       	    columnWidth : .5, // 该列有整行中所占百分比
					       	    layout : "form", // 从上往下的布局
					       	    baseCls:'my-panel-no-border',
					       	    items : [
						       	    {	
						       	    	xtype:'textfield',
					       	    	    fieldLabel : $i18n.article_oname,//其它名称
						       	        id : 'txtArticleOname1401',
							       	    labelAlign:'right',
							       	    maxLength:60,
							       	    labelWidth : 110
						       	    }]
					       	 },
		       	            {
		       	            	columnWidth : .5, // 该列有整行中所占百分比
		       	                layout : "form", // 从上往下的布局
		       	                baseCls:'my-panel-no-border',
		       	                items : [
		       	                {	
		       	                	xtype:'bdef_DefSupplierCombo',
		    						fieldLabel : $i18n.supplier_no,//供应商编号
		    					 	id:'supplierNo1401',
		    					    store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
		    			        	displayField : 'dropValue',
		    			        	valueField : 'value',
					       	        labelAlign:'right',
					       	        labelWidth : 110,
					       	        maxLength:120
				       	        }
		       	                ]
		       	            }]
	       	        	}]
	       	        
	       	        },
	       	        {	
	       	        	xtype:'bdef_DefGroupNoCombo',
	       	    	    fieldLabel : $i18n.article_class,//商品类别
		       	        id : 'cmbGroupNo1401',
		       	        store:Ext.create("cms.store.bdef.bdef_ArticleGroupComboStore"),
		       	        displayField : 'dropValue',
	        			valueField : 'value',						
			   			allowBlank :false,	
			   			beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'wms_DefFieldValCombo',
	       	        	fieldLabel:$i18n.article_batchId,//商品批次编号
	       	        	id : 'txtBatchId1401',
	       	        	store:Ext.create("cms.store.common.comboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url : 'bdef_ArticleGroupAction_getBatchIdCombo.action',
						        reader: {
						    		type:'json',
						            root: 'rootList',    
						            totalProperty: 'totalCount'
						        }
						    }
						}).load(),
				        allowBlank : false,
				        beforeLabelTextTpl : required
	       	        },
	       	        {	
	       	    	    fieldLabel : $i18n.aricle_identifier1,//商品识别码
	       	    	    //colspan:2,
	       	    	    allowBlank : false,
	       	    	    maxLength:50,
		       	        id : 'txtArticleIdentifier1401',
		       	        beforeLabelTextTpl : required
	       	        },
	       	        
	       	        {	
	       	    	    fieldLabel : $i18n.spec,//规格
		       	        id : 'txtSpec1401',
		       	        maxLength:50,
		       	        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },	       	        
	       	     {	
	       	        	xtype:'numberfield',
	       	    	    fieldLabel : "基本包装数量",//基本包装数量
		       	        id : 'unitPacking1401',
		       	        allowBlank : false,
		       	        minValue:1,
		       	        beforeLabelTextTpl : required
	       	        },
	       	     {	
	       	    	    fieldLabel : $i18n.Unit,//基本包装单位
		       	        id : 'txtUnit1401',
		       	        maxLength:50,
		       	        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },{	
	       	        	xtype:'numberfield',
	       	    	    fieldLabel : $i18n.qmin_packing_controller,//最小操作包装数
		       	        id : 'numQminOperatePacking1401',
		       	       // allowBlank : false,
		       	        minValue:1,
		       	       // beforeLabelTextTpl : required
	       	        },
	       	     {	
	       	    	    fieldLabel : "最小操作包装单位",//最小操作包装单位
		       	        id : 'qminUnit1401',
		       	        maxLength:50,
		       	       // allowBlank : false,
		       	       // beforeLabelTextTpl : required
	       	        },
	       	     /*{	
	       	    	    fieldLabel : "基本包装单位",//基本包装单位
		       	        id : 'unit1401',
		       	        maxLength:50,
		       	        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },*/
	       	        {	
	       	        	xtype:'numberfield',
	       	    	    fieldLabel : $i18n.unit_volumn+"(CM3)",//单位材积
		       	        id : 'numUnitVolumn1401',
		       	        allowBlank:false,
		       	        minValue:0,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {	
	       	        	xtype:'numberfield',
	       	    	    fieldLabel : $i18n.unit_weight,//单位重量
		       	        id : 'numUnitWeight1401',
		       	        allowBlank:false,
		       	        minValue:0,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {	
	       	        	xtype:'numberfield',
	       	    	    fieldLabel : $i18n.cumulative_volumn+"(CM3)",//累加材积
		       	        id : 'numCumulativeVolumn1401',
		       	        allowBlank:false,
		       	        minValue:0,
		       	        beforeLabelTextTpl : required
	       	        },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.virtual_flag,//是否虚拟商品
				        id : 'cmbVirtualFlag1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFARTICLE,VIRTUAL_FLAG"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.code_type,//是否组合商品
				        id : 'cmbCodeType1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFARTICLE,CODE_TYPE"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
	       	        {	
	       	        	xtype:'numberfield',
	       	    	    fieldLabel : $i18n.sell_price,//采购价格
	       	    	    minValue:0,
		       	        id : 'numSellPrice1401'
	       	        },{
					    fieldLabel : $i18n.barcode,//商品条码
						id : 'txtBarcode1401',
						//allowBlank : false,
						maxLength:25,
						//beforeLabelTextTpl : required
					}
					
	       	        ]
                },
                {
                	xtype:'fieldset',
                	title:$i18n.article_virtue,
                    layout: 
                    {
                    	type: 'table',
                    	columns: 3
                    },
                    defaults : 
                    {
                    	xtype : 'textfield',
                    	labelWidth : 110,
                    	margin:'5 4 1 4',
                    	labelAlign:'right'			
	  	       	    },
	       	        items:[
					{
						fieldLabel : $i18n.reserved1,//预留字段1
				        id : 'rsvAttr1_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved2,//预留字段2
				        id : 'rsvAttr2_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved3,//预留字段3
				        id : 'rsvAttr3_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved4,//预留字段4
				        id : 'rsvAttr4_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved5,//预留字段5
				        id : 'rsvAttr5_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved6,//预留字段6
				        id : 'rsvAttr6_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved7,//预留字段7
				        id : 'rsvAttr7_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved8,//预留字段8
				        id : 'rsvAttr8_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved9,//预留字段9
				        id : 'rsvAttr9_1401'
				    }]
                },{

                	xtype:'fieldset',
                    layout: 
                    {
                    	type: 'table',
                    	columns: 3
                    },
                    defaults : 
                    {
                    	xtype : 'textfield',
                    	labelWidth : 110,
                    	margin:'5 4 1 4',
                    	labelAlign:'right'			
	  	       	    },
	       	        items:[
				    {
				        fieldLabel : $i18n.reserved10,//预留字段10
				        id : 'rsvAttr10_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved11,//预留字段11
				        id : 'rsvAttr11_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved12,//预留字段12
				        id : 'rsvAttr12_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved13,//预留字段13
				        id : 'rsvAttr13_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved14,//预留字段14
				        id : 'rsvAttr14_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved15,//预留字段15
				        id : 'rsvAttr15_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved16,//预留字段16
				        id : 'rsvAttr16_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved17,//预留字段17
				        id : 'rsvAttr17_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved18,//预留字段18
				        id : 'rsvAttr18_1401'
				    }
				   /* {
				        fieldLabel : $i18n.reserved19,//预留字段19
				        id : 'rsvAttr19_1401'
				    },
				    {
				        fieldLabel : $i18n.reserved20,//预留字段20
				        id : 'rsvAttr20_1401'
				    } */      
   	                ]
                }
                ]
            }]
	    },{
	    	title:$i18n.control_message, //控制信息
	    	id:'tabPid1401_02',
        	layout:'border',
        	items:[
	        {
        		xtype : 'form',
        		id : 'form_03_1401',
				region:'center',
				frame : true,
				items :[
		        {
					xtype:'fieldset',
					title:$i18n.quality_manage,//质控管理
				    layout: 
				    {
				        type: 'table',
				        columns: 4
				    },
				    defaults : 
				    {
				        xtype : 'textfield',
		                labelWidth : 110,
		                width:230,
		                margin:'3 4 1 4',
			            labelAlign:'right'			
				    },
				    items:[
		            {
		            	xtype:'wms_DefFieldValCombo',
       	    	        fieldLabel : $i18n.check_qty_flag,//数量抽检标识
		       	        id : 'cmbCheckQtyFlag1401',
		       	        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,CHECK_QTY_FLAG"}
				        }),
				        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'numberfield',
       	    	        fieldLabel : $i18n.check_qty_rate,//数量抽检比率
		       	        id : 'numCheckQtyRate1401',
		       	        //colspan:2,
		       	        allowBlank : false,
		       	        minValue:0,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'wms_DefFieldValCombo',
       	    	        fieldLabel : $i18n.check_weigh_flag,//重量抽检标识
		       	        id : 'cmbCheckWeightFlag1401',
		       	        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,CHECK_WEIGHT_FLAG"}
				        }),
				        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'numberfield',
       	    	        fieldLabel : $i18n.check_weight_rate,//重量抽检比率
		       	        id : 'numCheckWeightRate1401',
		       	        //colspan:2,
		       	        allowBlank : false,
		       	        minValue:0,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'wms_DefFieldValCombo',
       	    	        fieldLabel : $i18n.double_check,//双人验收标识
		       	        id : 'cmbDoubleCheck1401',
		       	        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,DOUBLE_CHECK"}
				        }),
				        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'wms_DefFieldValCombo',
       	    	        fieldLabel : $i18n.qc_flag,//质检标识
		       	        id : 'cmbQcFlag1401',
		       	        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,QC_FLAG"}
				        }),
				        allowBlank : false,
		       	        beforeLabelTextTpl : required
	       	        },
	       	        {
	       	        	xtype:'numberfield',
       	    	        fieldLabel : $i18n.qc_rate,//质检比率
		       	        id : 'numQcRate1401',
		       	        allowBlank : false,
		       	        minValue:0,
		       	        beforeLabelTextTpl : required
	       	        }
				    
					]
		        },
            	{
	       		    xtype:'fieldset',
	       		    title:$i18n.excess_rule,//超量规则
	       	        layout: 
	       	        {
	  	       	        type: 'table',
	  	       	        columns: 4
	  	       	    },
	  	       	    defaults : 
	  	       	    {
	  	       	        xtype : 'numberfield',
	  	       	        labelWidth : 110,
	  	       	        width:230,
	  	       	        margin:'3 4 1 4',
	  	       	        labelAlign:'right'			
	  	       	    }, 
	       	        items:[
				    {
		    	        fieldLabel : $i18n.check_excess,//验收超量
		    	        id : 'numCheckExcess1401',
		    	        allowBlank : false,
		    	        minValue:0,
		    	        beforeLabelTextTpl : required
				    },
				    {
			    	    fieldLabel : $i18n.um_check_excess,//返配超量
				        id : 'numUmCheckExcess1401',
				        allowBlank : false,
				        minValue:0,
				        beforeLabelTextTpl : required
			        },
			        {
			    	    fieldLabel : $i18n.pick_excess,//拣货超量
				        id : 'numPickExcess1401',
				        allowBlank : false,
				        minValue:0,
				        beforeLabelTextTpl : required
			        },
			        {
			    	    fieldLabel : $i18n.divide_excess,//分播超量
				        id : 'numDivideExcess1401',
				        allowBlank : false,
				        minValue:0,
				        beforeLabelTextTpl : required
			        }
	       	        ]
            	},
                {
	       		    xtype:'fieldset',
	       		    title:$i18n.product_manage,//保质期管理
	       	        layout: 
	       	        {
		  	       	    type: 'table',
		  	       	    columns: 4
	  	       	    },
	  	       	    defaults : 
	  	       	    {
	  	       	        xtype : 'numberfield',
	  	       	        labelWidth : 110,
	  	       	        width:230,
	  	       	        margin:'3 4 1 4',
	  	       	        labelAlign:'right'			
	  	       	    },
	  	       	    items:[
					{
					    fieldLabel : $i18n.expiry_days,//保质期
					  	id : 'numExpiryDays1401',
					  	allowBlank : false,
					    beforeLabelTextTpl : required
					},
					{
						xtype: 'label',
						colspan:3,
						margin:'4 0 0 0',
						style:'color:red;',
					    text: '(-1表示：不做保质期管理；0表示：同一商品存在多个保质期；大于0表示：保质期天数)'
					},
       	    	    {
				        fieldLabel : $i18n.alarmrate,//报警比率
				        id : 'numAlarmrate1401',
				        allowBlank : false,
				        minValue:0,
					    beforeLabelTextTpl : required
				    },
				    {
					    fieldLabel : $i18n.freezerate,//冻结比率
				      	id : 'numFreezerate1401',
				      	allowBlank : false,
				      	minValue:0,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
					    fieldLabel : $i18n.turn_over_rule,//周转规则
				      	id : 'cmbTurnOverRule1401',
				      	editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,TURN_OVER_RULE"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
					    fieldLabel : $i18n.batch_manage,//批次管理类型
				      	id : 'cmbBatchManage1401',
				      	editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFARTICLE,LOT_TYPE"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    }				    
				    ]
                },
                {
                	xtype:'fieldset',
                	title:$i18n.other_manage,//其它管理
                	layout:
                	{
                		type:'table',
                		columns:4
                	},
                	defaults : 
	  	       	    {
	  	       	        xtype : 'textfield',
	  	       	        labelWidth : 110,
	  	       	        width:230,
  	       	        	margin:'3 4 1 4',
	  	       	        labelAlign:'right'			
	  	       	    },
                	items:[
					{
						xtype:'wms_DefFieldValCombo',
					    fieldLabel : $i18n.operate_type2,//商品拣货方式
					    id : 'cmbOperateType1401',
					    editable:false,
					    store:Ext.create("cms.store.common.comboStore").load(
					    {
					    	params:{str:"N,OPERATE_TYPE"}
					    }),
					    allowBlank : false,
					    beforeLabelTextTpl : required
					},
					{
						xtype:'wms_DefFieldValCombo',
			            fieldLabel : $i18n.abc,//ABC分类
				        id : 'cmbABC1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFARTICLE,ABC"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.storage_condition,//存储条件
				        id : 'cmbTemperatureFlag1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,TEMPERATURE_FLAG"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {	
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.scan_flag,//扫描标识
				        id : 'cmbScanFlag1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,SCAN_FLAG"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.measure_mode,//计量方式
				        id : 'cmbMeasureMode1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,MEASURE_MODE"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.mix_flag,//混属性标识
				        id : 'cmbMixFlag1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,MIX_FLAG"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.deliver_type1,//拆零标识
				        id : 'cmbDeliverType1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFARTICLE,DELIVER_TYPE"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.rule_flag1,//不规则商品
				        id : 'cmbRuleFlag1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,RULE_FLAG"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				        fieldLabel : $i18n.divide_box,//分箱条件
				        id : 'txtDivideBox1401',
				        maxLenght:50,
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.wms_print_flag,//吊牌标识
				        id : 'cmbPrintFlag1401',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFARTICLE,PRINT_FLAG"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
				    }
                	]
                },{

                	xtype:'fieldset',
                	title:$i18n.rsv_control,//预留字段
                	layout:
                	{
                		type:'table',
                		columns:4
                	},
                	defaults : 
	  	       	    {
	  	       	        xtype : 'textfield',
	  	       	        labelWidth : 110,
	  	       	        width:230,
  	       	        	margin:'3 4 1 4',
	  	       	        labelAlign:'right'			
	  	       	    },
                	items:[
					{
						 fieldLabel : $i18n.rsv_control1,//预留字段1
					        id : 'rsv_control1_1401'
					},
					{
						 fieldLabel : $i18n.rsv_control2,//预留字段2
					        id : 'rsv_control2_1401'
				    },
				    {
				    	 fieldLabel : $i18n.rsv_control3,//预留字段3
					        id : 'rsv_control3_1401'
				    },
				    {	
				    	 fieldLabel : $i18n.rsv_control4,//预留字段4
					        id : 'rsv_control4_1401'
				    },
				    {
				    	 fieldLabel : $i18n.rsv_control5,//预留字段5
					        id : 'rsv_control5_1401'
				    }
                	]
                
                }
				]	
	        }
			]
	    },
	    {
	    	title:$i18n.policy_info,//策略管理
	    	id:'tabPid1401_03',
	        layout:'border',
	        items :[
	        {
	        	xtype : 'form',
	        	id : 'form_04_1401',
				region:'center',
				frame : true,
				items :[
		        {
		        	xtype:'fieldset',
		        	layout: {
						type: 'table',
						columns: 2
					},
					defaults : {
						xtype : 'textfield',
						labelWidth : 110,
						width:400,
						margin:'5 4 1 4',
						labelAlign:'right'			
					},
		       	    items:[
					{
						xtype:'wms_DefStrategyCombo',
				 	    fieldLabel : $i18n.i_strategy,//上架策略
					    id : 'cmbIStrategy1401',
					    editable:false,
				        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"I"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefStrategyCombo',
				 	    fieldLabel : $i18n.o_strategy,//下架策略
					    id : 'cmbOStrategy1401',
					    editable:false,
					    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"O"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefStrategyCombo',
				 	    fieldLabel : $i18n.m_strategy,//补货策略
					    id : 'cmbMStrategy1401',
					    editable:false,
					    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"M"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefStrategyCombo',
				 	    fieldLabel : $i18n.ri_strategy,//返配策略
					    id : 'cmbRiStrategy1401',
					    editable:false,
					    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"RI"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefStrategyCombo',
				 	    fieldLabel : $i18n.ro_strategy,//退货策略
					    id : 'cmbRoStrategy1401',
					    editable:false,
					    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"RO"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				 	    fieldLabel : $i18n.fc_strategy,//盘点策略
					    id : 'cmbFcStrategy1401',
					    editable:false,
					    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"FC"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				 	    fieldLabel : $i18n.rsvStrategy1,//预留策略1(拣货位策略)
					    id : 'rsvStrategy1_1401',
				    	editable:false,
					    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"PICKCELL"}
				        }),
				        allowBlank : false,
					    beforeLabelTextTpl : required
				    },
				    {
				 	    fieldLabel : $i18n.rsvStrategy2,//预留策略2
					    id : 'rsvStrategy2_1401'
				    },
				    {
				 	    fieldLabel : $i18n.rsvStrategy3,//预留策略3
					    id : 'rsvStrategy3_1401'
				    },
				    {
				 	    fieldLabel : $i18n.rsvStrategy4,//预留策略4
					    id : 'rsvStrategy4_1401'
				    },
				    {
				 	    fieldLabel : $i18n.rsvStrategy5,//预留策略5
					    id : 'rsvStrategy5_1401'
				    },
				    {
				 	    fieldLabel : $i18n.rsvStrategy6,//预留策略6
					    id : 'rsvStrategy6_1401'
				    }
		       	    ]
		        }
		        ]
	        }
	        ]
	    },
	    {
	    	
	    	
	    	title:$i18n.title200002window,// 包装信息
	        layout:'border',
	        id:'tabPId_1401_d2',
	        items : [ 
            {
    	   	    xtype:'grid',
    		    id:'grid_01_1401_d2',
    		    height:200,
    		    region:'north',
    		    store:storePacking1401_d2,
    		    columns:[
    		    {			
    		        xtype : 'rownumberer',
    			    width : 30
    		    },
    		    {
    			    width:120,
    			    text : $i18n.article_no,
    			    dataIndex:'articleNo'			
    		    },
    		    {
				    width:300,
				    text : $i18n.article_name,
				    dataIndex:'articleName'			
    		    },
    		    {
				    width:90,
				    text:$i18n.packing_qty2,
				    dataIndex:'packingQty'
    		    },
    		    {
				    width:90,
				    text:$i18n.packing_qty1,
				    dataIndex:'packingUnit'
    		    },
    		    {
				    width:110,
				    text:$i18n.sorter_flag,
				    dataIndex:'sorterflagText'
    		    },
    		    {
				    width:110,
				    text:$i18n.rule_flag,
				    dataIndex:'ruleflagText'
    		    }
    		    ],
			    dockedItems : [{
			       xtype : 'pagingtoolbar',
			       dock : 'bottom',
			       store:storePacking1401_d2,
			       displayInfo : true
			    }]
            },
            {
            	xtype : 'form',
	            region:'center',
	   		    id:'form_01_1401_d2',
	   		    frame : true,
	   		    items : [
	   		    {
			        xtype : 'container',
				    layout: {
	   		            type: 'table',
	   		            columns: 3
	   		        },
    	   		    defaults : {
	   			        xtype : 'textfield',
	   				    labelWidth : 120,
	   				    margin : '4 4 1 4',
	   				    labelAlign:'right'			
    	   		    }, 
				    items:[
				    /*{
				    	xtype:'bdef_DefArticleCombo',
				    	fieldLabel : $i18n.article_no,//商品编码
				    	id : 'cmbArticleNo1401_d2',
		   			    store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
			   			displayField : 'value',
			   			valueField : 'value',						
			   			maxLength:15,
			   			allowBlank :false,	
			   			beforeLabelTextTpl : required
				    },
				    {
	   			        fieldLabel : $i18n.article_name,//商品名称
	   			        id : 'txtArticleName1401_d2',
	   			        colspan:2,
	   			        width:560,
	   				    readOnly:true,
	   			        allowBlank: false,
	   				    beforeLabelTextTpl : required
				    },*/
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.packing_qty,//包装数量
				    	id : 'numPackingQty1401_d2',
				    	minValue:1,
		   			    allowBlank : false,
		   			    beforeLabelTextTpl : required
				    },
				    {
				    	fieldLabel : $i18n.packing_unit,//包装单位
				    	id : 'txtPackingUnit1401_d2',
				    	allowBlank : false,
		   			    maxLength:20,
		   			    beforeLabelTextTpl : required
				    },
				    {
				    	fieldLabel : $i18n.spec,//规格
				    	id : 'txtSpec1401_d2',
				    	maxLength:100
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.length,//长(CM)
				    	id : 'numALength1401_d2',
				    	minValue:0,
		   			    allowBlank : false,
		   			    beforeLabelTextTpl : required
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.width,//宽(CM)
				    	id : 'numAWidth1401_d2',
				    	minValue:0,
		   			    allowBlank : false,
		   			    beforeLabelTextTpl : required
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.height,//高(CM)
				    	id : 'numAHeight1401_d2',
				    	minValue:0,
		   			    allowBlank : false,
		   			    beforeLabelTextTpl : required
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.weight+"(KG)",//重量
				    	id : 'numPackingWeight1401_d2',
				    	minValue:0
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.mpacking_qty,//中包装数量
				    	id : 'numMpackingQty1401_d2',
				    	minValue:1
				    },
				    {
				    	fieldLabel : $i18n.mpacking_unit,//中包装单位
				    	id : 'numMpackingUnit1401_d2',
				    	maxLength:20
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.pal_height_qbox,//堆叠箱单位层数
				    	id : 'numPalHeightQbox1401_d2',
				    	minValue:1
				    },
				    {
				    	xtype : 'numberfield',
				    	fieldLabel : $i18n.pal_base_qbox,//每层堆叠箱单位数
				    	id : 'numPalBaseQbox1401_d2',
				    	colspan:2,
				    	minValue:1
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel : $i18n.sorter_flag,//上分拣机标识
				    	id : 'cmbSorterFlag1401_d2',
				    	store:Ext.create("cms.store.common.comboStore").load(
	        	 	    {
	        	 		    params:{str:"BDEF_ARTICLE_PACKING,SORTER_FLAG"}
	        	        }),
	        	        editable:false
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel : $i18n.rule_flag1,//不规则商品
				    	id : 'cmbRuleFlag1401_d2',
				    	colspan:2,
				    	store:Ext.create("cms.store.common.comboStore").load(
	   		     	    {
	   		     	        params:{str:"N,RULE_FLAG"}
	   		     	    }),
	   		     	    editable:false,
	   		     	    allowBlank:false,
	   		     	    beforeLabelTextTpl:required
				    },{
		       		    xtype:'fieldset',
		       		    colspan:3,
		       		    title:$i18n.wms_house_pack,//仓别标准堆叠
		       	        layout: 
		       	        {
			  	       	    type: 'table',
			  	       	    columns: 3
		  	       	    },
		  	       	    defaults : 
		  	       	    {
		  	       	        xtype : 'numberfield',
		  	       	        labelWidth : 120,
		  	       	        //margin:'3 4 1 4',
		  	       	        labelAlign:'right'			
		  	       	    },
		  	       	    items:[
	       	    	    {
	       	    	        margin:'0 0 1 0',
					        fieldLabel : $i18n.pal_height_qbox,//仓别堆叠箱单位层数
					        id : 'houseHeightQbox1401_d2',
					        //allowBlank : false,
					        minValue:1
					    },
					    {
						    fieldLabel : $i18n.pal_base_qbox,//仓别每层堆叠箱单位数
					      	id : 'houseBaseQbox1401_d2',
					      	//allowBlank : false,
					      	minValue:1
					    }]
	                },{
		       		    xtype:'fieldset',
		       		    colspan:4,
		       		    title:$i18n.rsv,//预留
		       	        layout: 
		       	        {
			  	       	    type: 'table',
			  	       	    columns: 4
		  	       	    },
		  	       	    defaults : 
		  	       	    {
		  	       	        xtype : 'textfield',
		  	       	        labelWidth : 120,
		  	       	        width : 230,
		  	       	        labelAlign:'right'			
		  	       	    },
		  	       	    items:[
	       	    	    {
					        fieldLabel : $i18n.rsv_pking1, 
					        id : 'rsv_pking1_1401',
					    },
					    {
						    fieldLabel : $i18n.rsv2,
					      	id : 'rsv_pking2_1401',
					    },{
					    	 fieldLabel : $i18n.rsv3,
						     id : 'rsv_pking3_1401',	
					    },{
					    	 fieldLabel : $i18n.rsv4,
						     id : 'rsv_pking4_1401',
					    },{
					    	 fieldLabel : $i18n.rsv5,
					    	 hidden:true,
						     id : 'rsv_pking5_1401',
					    }]
	                
	                }/*
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel : $i18n.create_flag1,//是否ERP下传
				    	id : 'cmbCreateFlag1401_d2',
				    	store:Ext.create("cms.store.common.comboStore").load(
	   		     	    {
	   		     	        params:{str:"N,CREATE_FLAG"}
	   		     	    }),
	   		     	    editable:false,
	   		     	    allowBlank:false,
	   		     	    beforeLabelTextTpl:required
				    }*/
				    ]
   	            }
	   		    ]
            }
            ]
	    },{
	    	title:$i18n.title200003window,//新增条码信息
	        id:'tabPId_1401_d3',
	        layout:'border',
	        items : [
	        {
		        xtype:'grid',
			    id:'grid_01_1401_d3',
			    region:'north',
			    height:230,
			    store:storeBarcode1401_d3,
			    columns:[{			
			        xtype : 'rownumberer',
				    width : 30
			    },
			    {
				    width:100,
				    text : $i18n.owner_no,
				    dataIndex:'ownerNo'			
			    },
			    {
				    width:100,
				    text : $i18n.owner_article_no,
				    dataIndex:'ownerArticleNo'
			    },
			    {
				    width:120,
				    text : $i18n.article_no,
				    dataIndex:'articleNo'
			    },
			    {
				    width:250,
				    text : $i18n.article_name,
				    dataIndex:'articleName'
			    },
			    {
				    width:120,
				    text : $i18n.barcode,
				    dataIndex:'barcode'
			    },
			    {
				    width:90,
				    text : $i18n.new_packing_qty,
				    dataIndex:'packingQty'
			    }/*,
			    {
				    width:90,
				    text : $i18n.primary_flag,
				    dataIndex:'primaryflagText'
			    }*/
			    ],
			    dockedItems : [
			    {
			        xtype : 'pagingtoolbar',
			        dock : 'bottom',
				    store:storeBarcode1401_d3,
				    displayInfo : true
			    }
			    ]
	        },{
	        	xtype : 'form',
	        	region:'center',
	        	id:'form_01_1401_d3',
	        	frame : true,
	        	layout:
	        	{
	        		type:'table',
	        		columns:3
	        	},
	        	defaults : 
	        	{
	        		xtype : 'textfield',
	        		labelWidth : 120,
	        		margin:'4 4 1 4',
	        		labelAlign:'right'			
	        	},
	        	items : [
				{
				   	xtype : 'bdef_PackingQtyCombo',
				    fieldLabel : $i18n.new_packing_qty,//包装数量
				    store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
					id : 'numPackingQty1401_d3',
					allowBlank : false,
					beforeLabelTextTpl : required
				},{
				    fieldLabel : $i18n.barcode,//商品条码
					id : 'txtBarcode1401_d3',
					allowBlank : false,
					maxLength:25,
					beforeLabelTextTpl : required
				}/*,{
				   	xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.primary_flag,//主条码标识
					id : 'cmbPrimaryFlag1401_d3',//
					hidden:true,
					store:Ext.create("cms.store.common.comboStore").load(
					{
						params:{str:"N,PRIMARY_FLAG"}
					}),
				 	editable:false,
				 	allowBlank:false,
				 	beforeLabelTextTpl:required
				}*/
    	        ]
	        }
	        ]
        },
        //*******************//
        
        {

	    	title:$i18n.title200002window1,// 拣货位信息
	        layout:'border',
	        id:'tabPId_1401_d4',
	        items : [ 
            {
        		xtype:'grid',	
        		region:'center',
        		id:'grid_06_1401',
        		//width:'100%',
        		height:200,
        		store:cset_CellArticleStore,
        		columns:[{
        			xtype : 'rownumberer',
        			width : 30
        		}, /*{
        			width : 120,
        			text : $i18n.owner_name,//委托业主名称
        			//align:'center',
        			dataIndex : 'ownerName'
        		}, */{
        			width : 100,
        			text : $i18n.owner_no,//委托业主编号
        			dataIndex : 'ownerNo'
        		}, {
        			width : 100,
        			text : $i18n.owner_article_no,//业主商品编码
        			dataIndex : 'ownerArticleNo'
        		}, {
        			width : 120,
        			text : $i18n.article_no,//商品编码
        			dataIndex : 'articleNo'
        		}, {
        			width : 180,
        			text : $i18n.article_name,//商品名称
        			dataIndex : 'articleName'
        		}, {
        			width : 60,
        			text : $i18n.ware_no,//仓区
        			dataIndex : 'wareNo'
        		}, {
        			width : 80,
        			text : $i18n.cell_no,//货位
        			dataIndex : 'cellNo'
        		}, {
        			width : 80,
        			text : $i18n.stock_no,//通道代码
        			dataIndex : 'stockNo'
        		}, {
        			width : 80,
        			text : $i18n.area_no,//储区代码
        			dataIndex : 'areaNo'
        		}, {
        			width : 80,
        			text : $i18n.line_no,//线路编号
        			dataIndex : 'lineId'
        		},/* {
        			width : 80,
        			text : '线路名称',//线路名称
        			dataIndex : 'lineName'
        		},*/ {
        			width : 80,
        			text : $i18n.packing_qty,//包装数量
        			dataIndex : 'packingQty'       			
        		}, {
        			width : 100,
        			text : $i18n.max_qty_box,//最大存储量（箱）
        			dataIndex : 'maxQtyBox'
        		}, {
        			width : 100,
        			text : $i18n.alert_qty_box,//补货警示量（箱）
        			dataIndex : 'alertQtyBox'
        		}, {
        			width : 130,
        			text : $i18n.supp_qty_box, //'循环补货触发量（箱）
        			dataIndex : 'suppQtyBox'
        		}, {
        			width : 90,
        			text : $i18n.keep_cells,//可用货位数
        			dataIndex : 'keepCells'
        		}, {
        			width : 80,
        			text : $i18n.pick_type,//拣货类型
        			dataIndex : 'pickType'
        		}],
        		dockedItems : [{
        			xtype : 'pagingtoolbar',
        			store:cset_CellArticleStore,
        			dock : 'bottom',
        			displayInfo : true
        		}] 
        	},//拣货位修改信息
            {
	    	   xtype:'form',
	    	   region:'south',
	    	   id:'cset_Cell_ArticleAddOrEditForm1401',
	    	   frame:true,
	    	   layout:'column',
	       	   items:[
	       	      {  
	               layout: {
	 	   	          type: 'table',
	 	   	          columns: 2
	       	   	   },
	               xtype:'fieldset',  
	                id:'down1401window',
	               autoHeight:true,  
	               width:'99%',
	               defaults:{
		    	   		xtype:'textfield',
		    	   		margin:'3 3 0 3',
		    	   		labelAlign:'right',
		    	   		labelWidth:130
		       	   },
	               items:[
	                {
			 	    	fieldLabel:$i18n.packing_qty,//包装数量
			 	    	maxLength:18,
			 	    	allowBlank:false,
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qty1401',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	xtype:'cdef_DefWareCombo',
			 	    	fieldLabel:'拣货类型',//拣货类型
			 	    	id:'o_type1401',
			 	    	allowBlank:false,
			 	    	beforeLabelTextTpl : required,
			    		store:Ext.create("cms.store.cdef.cdef_DefWareComboStore",{
			    			proxy:{
								type:'ajax',
								method:'post',
								async:false,
								url:'cset_CellArticleAction_getOtypeo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
						   listConfig: {
					           loadingText: '查询中...',
					           emptyText: '没有找到相应的数据！' ,
					           getInnerTpl: function() {
					        	   return '{value}';
					           }
					       },
					       autoLoad:true
			    		})
			 	    },{
			 	    	xtype:'cdef_DefWareCombo',
			 	    	fieldLabel:$i18n.ware_no,//库区
			 	    	id:'ware_no1401',
			 	    	allowBlank:false,
			 	    	beforeLabelTextTpl : required,
						store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',
						{
							proxy:{
								type:'ajax',
								method:'post',
								async:false,
								url:'cset_AreaBackupAction_getCdef_DefAreaCombo2.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "3"
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
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	xtype:'cdef_DefAreaCombo',
			 	    	fieldLabel:$i18n.area_no,//储区代码
			 	    	id:'area_no1401',
			 	    	allowBlank:false,
			 	    	store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'cset_AreaBackupAction_getCdef_DefAreaCombo2.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "2"
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
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	xtype:'cdef_DefStockCombo',
			 	    	fieldLabel:$i18n.stock_no,//通道全称
			 	    	id:'stock_no1401',
			 	    	//maxLength:5,
			 	    	allowBlank:false,
			 	    	store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
			 	    		proxy:{
								type:'ajax',
								method:'post',
								url:'cset_AreaBackupAction_getCdef_DefStockCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "1"
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
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	fieldLabel:$i18n.cell_no,//货位
			 	    	id:'cell_no1401',
			 	    	maxLength:24,
			 	    	xtype: 'cdef_DefCellCombo',
		                displayField : 'value',
		    			valueField : 'value',
		    			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
		    			   proxy:{
								type:'ajax',
								method:'post',
								url:'cset_AreaBackupAction_getCdef_DefCellCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
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
			 	    	id:'stockX1401',
			 	    	hidden:true
			 	    },
			 	    {
			 	    	xtype:'cset_LineCombo',
			 	    	fieldLabel:$i18n.line,//线路
			 	    	id:'cmbLineId1401',
			 	    	colspan:2,
			 	    	//allowBlank:false,
			 	    	store:Ext.create('cms.store.cset.cset_LineComboStore',{
			 	    		listeners:
		    		    	{  
		    		    		'load':function(th,records,successful,eOpts )
		    		    		{
		    		    			if(th.count()==0){
		    		    				Ext.example.msg($i18n.prompt,'该区域没有设置保拣线！');
		    		    			}
		    		    		}
		    		    	}
			 	    		
			 	    	}),
			 	    	//beforeLabelTextTpl : required
			 	    }
		    ]},{
		    	  
	               layout: {
	 	   	          type: 'table',
	 	   	          columns: 2
	       	   	   },
	               xtype:'fieldset',  
	                id:'down2202window',
	               autoHeight:true,  
	               width:'99%',
	               defaults:{
		    	   		xtype:'textfield',
		    	   		margin:'3 3 0 3',
		    	   		labelAlign:'right',
		    	   		labelWidth:130
		       	   },
	               items:[
	              {
		 	    	fieldLabel:$i18n.keep_cells,//非A类最大可用货位数
		 	    	id:'keep_cells1401',
		 	    	margin:'3 3 0 3',
		 	    	maxLength:3,
		 	    	beforeLabelTextTpl : required
		 	    },{
		 	    	fieldLabel:$i18n.max_qty_box,//最大存储量（箱）
		 	    	id:'max_qty_na1401',
		 	    	maxLength:18,
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required
		 	    },{
		 	    	fieldLabel:$i18n.supp_qty_box, //'循环补货触发量（箱）
		 	    	id:'supp_qty_na1401',
		 	    	maxLength:18,
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required
		 	    },{
		 	    	fieldLabel:$i18n.alert_qty_box,//补货警示量（箱）
		 	    	id:'alert_qty_na1401',
		 	    	maxLength:18,
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required
		 	    }			                
		    ]}
		    ]}
            ]
	    
        },
        {
	    	title:$i18n.title200002window2,// 库存信息
	        layout:'border',
	        id:'tabPId_1401_d5',
	        items : [ 
            {//货位商品库存信息
		    	xtype : 'grid',
		    	height:'40%',
		    	region:'center',
				id : 'grid_07_1401_d2',
				store : Ext.create('cms.store.stock.stock_ContentStore').load(),
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},
				{
					width : 100,
					text : "库区",
					dataIndex : 'wareNo'
				},
				{
					width : 100,
					text : "库区名称",
					dataIndex : 'wareName'
				},
				{
					width : 100,
					text : "储区",
					dataIndex : 'areaNo'
				},
				{
					width : 100,
					text : "储区名称",
					dataIndex : 'areaName'
				},{
					width : 100,
					text : $i18n.cell,
					dataIndex : 'cellNo'
				},{
					width : 110,
					text : $i18n.article_no,
					dataIndex : 'articleNo'
				},{
					width : 200,
					text : $i18n.article_name,
					dataIndex : 'articleName'
				},{
					width : 120,
					text : $i18n.barcode,
					dataIndex : 'barcode'
				},{
					width : 60,
					text : $i18n.packing_qty,
					dataIndex : 'packingQty'
				},{
					width : 60,
					text : $i18n.Unit,
					dataIndex : 'unit'
				},{
					width : 60,
					text : $i18n.spec,
					dataIndex : 'spec'
				},{
					width : 80,
					text : $i18n.qty,
					dataIndex : 'qty'
				},{
					width : 80,
					text : $i18n.instock_article_qty,
					dataIndex : 'instockQty'
				},{
					width : 80,
					text : $i18n.outstock_article_qty,
					dataIndex : 'outstockQty'
				},{
					width : 90,
					text : $i18n.porduce_date,
					dataIndex : 'produceDate'
				},{
					width : 90,
					text : $i18n.expire_date,
					dataIndex : 'expireDate'
				}]
		    }
           
            ]
        }
	    
       
	    ]
	},{
    	region:'south',
    	xtype:'commMenuWidget5',
    	border:0,
    	id:'bdef_MenuWidget1401'
    }
	]
});