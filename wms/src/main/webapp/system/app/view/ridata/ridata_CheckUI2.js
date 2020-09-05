/**
 * 模块名称：返配表单验收
 * 模块编码：6401
 * 创建：Jun
 */
var ridata_check_MStore=Ext.create('cms.store.ridata.ridata_CheckMStore2',{autoLoad:true});
Ext.define('cms.view.ridata.ridata_CheckUI2',{
	alias:'widget.ridata_CheckUI2',
	title:$i18n.title6401,//返配表单验收
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.ridata_UntreadNoCombo',
	          'cms.view.common.bdef_DefDockCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget4',
	    region:'north',
	    id:'menu6401'
	},{
        xtype : 'grid',
        region : 'east',
	    width : '75%',
	    id:'grid_01_6401',
	    store:ridata_check_MStore,
	    columns:[
	    {
		    xtype : 'rownumberer',
			width : 30
	    },{
			width : 130,
			text : $i18n.i_check_no,
			dataIndex : 'checkNo'
	    },{
			width : 90,
			text : $i18n.cust_no,
			dataIndex : 'custNo' 
	    },{
			width : 140,
			text : $i18n.cust_name,
			dataIndex : 'custName' 
	    },{
			width : 140,
			text : '返配单号',
			dataIndex : 'untreadNo'
	    },{
	    	width : 140,
			text : '返配汇总单号',
			dataIndex : 'SUntreadNo'
	    },{
			width : 140,
			hidden:true,
			text : $i18n.s_check_no,
			dataIndex : 'SCheckNo'
	    },{
			width : 80,
			text : $i18n.dock_no,
			dataIndex : 'dockNo'
	    },{
			width : 80,
			text : $i18n.status,	
			dataIndex : 'statusText'
	    }]
	},{
   	    xtype : 'form',
	    id:'form_01_6401',
	    layout:'column',
	    region : 'west',
	    frame : true,
	    width : '25%',
	    items:[
	    {
	   	    xtype:'container',
		    margin:'20 0 0 0',
		    defaults:{
			    labelWidth : 90,
			    margin : '2 0 8 0',
			    labelAlign : 'rigth',
			    readOnly:true,
			    xtype:'textfield'
		     },
		     items:[{
					xtype : 'radiogroup',
					id : 'rdoCheckType6401',
					width : 250,
					columns : 3,
					items : [
			        {
						boxLabel : $i18n.no_print,//不打印
						name : 'rd',
						inputValue : '0'
					},
					{
						boxLabel : '打表单',
						name : 'rd',
						inputValue : '1',
						checked:true
					},{
						boxLabel : '打标签',
						name : 'rd',
						inputValue : '2'	
					}
					]
		   		   
			     },
		     {
		   	     xtype:'ridata_UntreadNoCombo',
		   	     fieldLabel:$i18n.s_untread_no1,//Wms返配汇总单号
		   	     id:'s_untread_no16401',
		   	     displayField: 'value',
			     valueField: 'value',
			     store:Ext.create("cms.store.ridata.ridata_UntreadNoComboStore",{
				     proxy:{
						type:'ajax',
						method:'post',
						url:'ridata_CheckAction2_getUntreadNoList.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
				     }
				 }),
		   	     beforeLabelTextTpl : required
		     },{
		   	     xtype : 'bdef_DefWorkerCombo',
			     fieldLabel : $i18n.check_worker1,//验收员
			     id : 'check_worker16401',	
			     store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
			     beforeLabelTextTpl : required
		     }]
	   }]
	},{
   	    xtype:'grid',
   	    region:'south',
   	    height:300,
   	    loadMask : true, // 加载时有加载的图标
   	    id:'grid_02_6401',
   	    store:Ext.create('cms.store.ridata.ridata_CheckDStore',{
   	    	proxy:{
				type:'ajax',
				method:'post',
				url:'ridata_CheckAction2_getRidata_Check_DList.action',
				reader:{
					root:'rootList',
					totalProperty:'totalCount'
				}
		     }
   	    }),
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
			width : 110,
			text : $i18n.article_no,//商品编码
			dataIndex : 'articleNo'
		},{
			width : 180,
			text : $i18n.article_name,//商品名称
			dataIndex : 'articleName'
		},{
			width : 120,
			text : $i18n.barcode,//条码
			dataIndex : 'barcode'
		},/*{
			width : 60,
			text : $i18n.qty1,//整件数
			dataIndex : 'pobox'
		},*/
		{
			width : 80,
			text : $i18n.check_qty,//验收数量
		    id:'checkQty6401',
			dataIndex : 'checkQty'
		},{
			width : 85,
			text : $i18n.import_box,	//验收箱数
			dataIndex : 'planBox',
			//hidden:true,
			id:'planBox_6401',
			cls : 'notnull',
			field : {
	    		xtype : 'numberfield',
	    		minValue:0,
	    		listeners:{  
					'change': function(obj, newValue, oldValue, eOpts) {
						if(newValue!=oldValue){
							var data = Ext.getCmp('grid_02_6401').getSelectionModel()
								.getSelection();
							data[0].set('checkQty', newValue
								* data[0].get('packingQty')
								+ data[0].get('planQmin') * data[0].get('qminOperatePacking')
								+ data[0].get('planDis'));
						}
					}
      			}
	    	}
		},{
			width : 85,
			text : $i18n.checkQmin,//验收中包数
			dataIndex : 'planQmin',
			id:'planQmin_6401',
			cls : 'notnull',
			field : {
	    		xtype : 'numberfield',
	    		minValue:0,
	    		listeners:{  
					'change': function(obj, newValue, oldValue, eOpts) {
						if(newValue!=oldValue){
							var data = Ext.getCmp('grid_02_6401').getSelectionModel()
								.getSelection();
							data[0].set('checkQty', 
									data[0].get('planBox')* data[0].get('packingQty')
								  + newValue * data[0].get('qminOperatePacking')
								  + data[0].get('planDis'));
						}
					}
      			}
	    	}
		},{
			width : 85,
			text : $i18n.import_pcs,//验收散数
			dataIndex : 'planDis',
			id:'planDis_6401',
			cls : 'notnull',
			field : {
	    		xtype : 'numberfield',
	    		minValue:0,
	    		listeners:{  
					'change': function(obj, newValue, oldValue, eOpts) {
						if(newValue!=oldValue){
							var data = Ext.getCmp('grid_02_6401').getSelectionModel()
								.getSelection();
							data[0].set('checkQty', 
									data[0].get('planBox')* data[0].get('packingQty')
								  + data[0].get('planQmin') * data[0].get('qminOperatePacking')
								  + newValue);
						}
					}
      			}
	    	}
		},/*{
			width : 60,
			text : $i18n.check_number,//验收箱数
			dataIndex : 'checkbox',
			cls:'notnull',
			field: {
				xtype: 'numberfield',
            	minValue:0,
            	listeners:{  
					'change': function(obj, newValue, oldValue, eOpts) {
						if(newValue!=oldValue){
							var data = Ext.getCmp('grid_02_6401').getSelectionModel().getSelection();
								Ext.getCmp('grid_02_6401').getSelectionModel().getSelection();
							data[0].set('checkQty', newValue * data[0].get('packingQty'));
						}
					}
      			}
			}
		},*/{
			width : 80,
			text : $i18n.packing_qty,//包装数
			dataIndex : 'packingQty',
		    cls : 'notnull',
		    field:{
				xtype : 'bdef_PackingQtyCombo',
				id : 'packing_qty6401',
				store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore')
			}
		},{
			width : 60,
			text : $i18n.packing_unit,//单位
			id:'packingUnit_6401',
			dataIndex : 'packingUnit'
		},{
			width : 80,
			text : $i18n.spec,//规格
			id:'packingSpec_6401',
			dataIndex:'packingSpec'
		},{
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
		    width:160,
		    text: $i18n.lot_no,//生产批号
//		    hidden:$i18n.lotNoHidden,
		    dataIndex:'lotNo',
		    cls:'notnull',
		    field: {
		    	xtype: 'textfield',
		    	maxLength:32
            }
	    },
//	    {
//			width:60,
//			text:$i18n.quality,//'品质',
//			hidden:$i18n.qualityHidden,
//			dataIndex:'quality',
//			cls:'notnull',
//			field: {
//				xtype:'wms_DefFieldValCombo',
//		        editable:false,
//		        store:Ext.create("cms.store.common.comboStore").load(
//		        {
//		        	params:{str:"N,QUALITY"}
//		        }),
//		        allowBlank : false,
//		        beforeLabelTextTpl : required
//            },	                
//            renderer: function(value,metadata,record){  
//            	if(value=='0')
//            	{
//            		return '良品';
//            	}else if(value=='A')
//            	{
//            		return '不良品';
//            	}
//     		}
//		},	
	    {
			width:100,
			text:$i18n.quality,//'品质',
			hidden:$i18n.qualityHidden,
			dataIndex:'qualityFlag',
			id:'qualityFlagList',
			cls:'notnull',
			field: {
				xtype:'wms_DefFieldValCombo',
		        editable:false,
//				forceSelection : false,
		        store:Ext.create("cms.store.common.comboStore").load(
		        {
		        	params:{str:"RIDATA_CHECK_PAL,QUALITY_FLAG"}
		        })
	        },	                
	        renderer: function(value,metadata,record){  
	        	var a=[];
				    	Ext.Ajax.request({
   				url:'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
   				params:{str:"RIDATA_CHECK_PAL,QUALITY_FLAG"},
				async : false,
   				success:function(response){
   					   a = Ext.decode(response.responseText);
     				    }
   				});
		    	for(var i=0;i<a.length;i++){
				   if(value==a[i].value){
					   return a[i].dropValue;
				   }
			     }
	 		}
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
		}]
	},{
	    xtype : 'panel',
	    id : 'msterForm6401',
	    region:'south',
	    html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
			+ 'background-color:#C1D5ED; text-align: left;">'
			+ '<span><label>新增人:</label><label id="rgstName6401"></label> </span> '
			+ '<span><label>&nbsp;&nbsp;&nbsp;新增日期：</label><label id="rgstDate6401"></label></span>'
			+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName6401"></label> </span> '
			+ '<span><label>&nbsp;&nbsp;&nbsp;修改日期：</label><label id="updtDate6401"></label> </span></div>'
	}]
});