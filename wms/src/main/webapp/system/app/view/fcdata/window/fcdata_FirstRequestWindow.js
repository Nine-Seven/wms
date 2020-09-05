/**
 * 模块名称：初盘回单》新增品项
 * 模块编码：8401
 * @author JUN
 */
Ext.define('cms.view.fcdata.window.fcdata_FirstRequestWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.fcdata_FirstRequestWindow',
	id:'fcdata_FirstRequestWindow',
	layout:'border',
    height:415,
	width:850,
	modal:true,
    items: [
	{
		xtype : 'form',
		id:'form_02_8401',
		region : 'center',
		width:'100%',
		defaults : 
		{
			xtype : 'textfield',
			margin : '2 2 2 0',
			labelAlign : 'right'
		},
		frame : true,
		items : [
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
		    items:
		    [
			{
				fieldLabel:$i18n.cell_no,//储位
				xtype: 'cdef_DefCellCombo',
				id:'cmbCellNo8401',
				displayField : 'value',
				valueField : 'value',
				minChars : 0,// 多少字符请求1次
				store:Ext.create("cms.store.common.comboStore",{
					proxy:
					{
						type:'ajax',
						method:'post',
						url:'fcdata_RequestAction_queryCdefDefCellCombo',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						},
						extraParams:{
							strFlag : '1'
						}
					}
				}),
				beforeLabelTextTpl : required
			},{
				fieldLabel:$i18n.owner,//货主编码
				id : 'cmbOwnerNo8401',
				readOnly:true
			},
			{
				fieldLabel:$i18n.article_no_or_barcode,//商品编码或条码
				id : 'cmbArticleNo8401',
				xtype:'bdef_DefArticleCombo',
				displayField : 'value',
				valueField : 'value',
				store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.barcode,//商品条码
				id : 'txtBarcode8401',
				readOnly:true
			},{
				fieldLabel:'最小操作包装',
				id : 'txqminOperatePacking8401',
				hidden:true
			},
			{
				//fieldLabel:$i18n.barcode,//保质期天数
				id : 'guarantee8401',
				hidden: true,
				readOnly:true
			},
			{
				fieldLabel:$i18n.article_name,//商品名称
				id : 'txtArticleName8401',
				width:530,
				colspan: 2,
				readOnly:true
			},
			{
				fieldLabel:$i18n.new_packing_qty,//包装数量
				xtype : 'bdef_PackingQtyCombo',
				id : 'cmbPackingQty8401',
				queryMode: 'local',
				queryParam : 'strWheresql',
				store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.packing_qty1,//包装单位
				id : 'txtPackingUnit8401',
				readOnly:true
			},
			{
				fieldLabel:$i18n.packingSpec,//规格
				id : 'txtSpec8401',
				readOnly:true
			},
			{
				fieldLabel:$i18n.boxQty,//箱数
				xtype : 'numberfield',
				id : 'numPoBox8401',
				minValue:0,
				beforeLabelTextTpl : required
			},{
				fieldLabel:$i18n.qminQty,//中数		7-19添加
				xtype : 'numberfield',
				id : 'numPoMin8401',
				minValue:0,
				beforeLabelTextTpl : required
			},{

				fieldLabel:'中包装数量',//中包装数量    7-19添加
				id : 'minPackingQty8401',
				readOnly:true,
				hidden:true
			},{
				fieldLabel:$i18n.disQty,//散数		7-19添加
				xtype : 'numberfield',
				id : 'numPoDis8401',
				minValue:0,
				beforeLabelTextTpl : required
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.whether_write,//是否填写标签号
				editable:false,
			    store:Ext.create("cms.store.common.comboStore").load(
			    {
			    	params:{str:"FCDATA_CHECK_D,WHETHER_WRITE"}
			    }),
				id : 'cmbWheeherWrite8401'
			},
			{
				fieldLabel:$i18n.label_no2,//主标签号
				id : 'txtLabelNo8401',
				readOnly:true,
				value:'N',
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.sub_label_no,//子标签号
				id : 'txtSubLabelNo8401',
				colspan:2,
				readOnly:true,
				value:'N',
				beforeLabelTextTpl : required
			}
			]
	    },
	    {
	    	xtype:'fieldset',
	    	title:$i18n.batch_attribute,//批属性
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
				fieldLabel:$i18n.produce_date,//生产日期
			    xtype: 'datefield',
			    format : 'Y-m-d',
				id : 'dateProduceDate8401',
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.expire_date,//有效日期
				xtype: 'datefield',
			    format : 'Y-m-d',
				id : 'dateExpireDate8401',
				colspan: 2,
				beforeLabelTextTpl : required
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.lot_no,//批号
				id : 'txtLotNo8401',
				hidden:$i18n.lotNoHidden,
				displayField : 'lotNo',
				
				valueField : 'lotNo',
				store:Ext.create("cms.store.fcdata.fcdata_CheckDwindowStore",{
					proxy:
					{
						type:'ajax',
						method:'post',
						url:'fcdata_RequestAction_queryLot'
					}
				}),
				forceSelection : false,
				beforeLabelTextTpl : required
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.quality,//品质
				editable:false,
			    store:Ext.create("cms.store.common.comboStore").load(
			    {
			    	params:{str:"N,QUALITY"}
			    }),
			    id : 'cmbQuality8401',
			    editable:false,
		        allowBlank : false,
		        beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved1,//预留字段1
				id : 'txtRsvBatch18401',
				value:'N',
				hidden:$i18n.rsvBatch1Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved2,//预留字段2
				id : 'txtRsvBatch28401',
				value:'N',
				hidden:$i18n.rsvBatch2Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved3,//预留字段3
				id : 'txtRsvBatch38401',
				value:'N',
				hidden:$i18n.rsvBatch3Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved4,//预留字段4
				id : 'txtRsvBatch48401',
				value:'N',
				hidden:$i18n.rsvBatch4Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved5,//预留字段5
				id : 'txtRsvBatch58401',
				value:'N',
				hidden:$i18n.rsvBatch5Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved6,//预留字段6
				id : 'txtRsvBatch68401',
				value:'N',
				hidden:$i18n.rsvBatch6Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved7,//预留字段7
				id : 'txtRsvBatch78401',
				value:'N',
				hidden:$i18n.rsvBatch7Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved8,//预留字段8
				id : 'txtRsvBatch88401',
				value:'N',
				hidden:$i18n.rsvBatch8Hidden,
				beforeLabelTextTpl : required
			}
            ]
	    }
        ,
        {
    		xtype : 'button',
    		margin:'10 0 0 720',
    		id:'saveAndAdd8401',
    		colspan: 2,
    		text : $i18n.save_and_add//保存并新增
    	}
        ]
	}
	]
});
