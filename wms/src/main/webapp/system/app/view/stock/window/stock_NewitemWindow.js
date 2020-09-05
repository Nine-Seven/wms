/**
 * 模块名称：库存管理》新增品项
 * 模块编码：D101
 * @author hcx
 */
Ext.define('cms.view.stock.window.stock_NewitemWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.stock_NewitemWindow',
	id:'stock_NewitemWindow',
	layout:'border',
    height:310,
	width:630,
	modal:true,
    items: [
	{
		xtype : 'form',
		id:'form_02_D101',
		region : 'center',
		width:'100%',
		defaults : 
		{
			xtype : 'textfield',
			margin : '1 1 1 0',
			labelAlign : 'right'
		},
		frame : true,
		items : [
        {
			xtype:'fieldset',
			layout: 
			{
				type: 'table',
				columns: 2
			},
			defaults : 
			{
				xtype : 'textfield',
				labelWidth : 100,
				margin:'9 0 0 0 ',
				labelAlign:'right'			
				    
			},
		    items:
		    [
			{
				fieldLabel:$i18n.cell_no,//储位
				xtype: 'textfield',
				id:'cmbCellNoD101',
				readOnly:true,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.article_no_or_ownerAticleNo,//商品编码或内码
				id : 'cmbArticleNoD101',
				xtype:'bdef_DefArticleCombo',
				displayField : 'value',
				valueField : 'value',
				colspan:2,
				store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.article_name,//商品名称
				id : 'txtArticleNameD101',
				width:510,
				colspan: 2,
				readOnly:true
			},
			{
				fieldLabel:$i18n.barcode,//商品条码
				id : 'txtBarcodeD101',
				readOnly:true
			},
			{
				//fieldLabel:$i18n.barcode,//保质期天数
				id : 'guaranteeD101',
				hidden: true,
				readOnly:true
			},
			{
				fieldLabel:$i18n.new_packing_qty,//包装数量
				xtype : 'bdef_PackingQtyCombo',
				id : 'cmbPackingQtyD101',
				queryMode: 'local',
				queryParam : 'strWheresql',
				store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore',
				{
					listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('cmbPackingQtyD101').setValue(th.getAt(0).data.value);
							}
						}
					}
				}),
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.packing_qty1,//包装单位
				id : 'txtPackingUnitD101',
				readOnly:true
			},
			{
				fieldLabel:$i18n.spec,//规格
				id : 'txtSpecD101',
				readOnly:true
			},
			{
				fieldLabel:$i18n.qty1,//箱数
				xtype : 'numberfield',
				id : 'numPoBoxD101',
				minValue:0,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.produce_date,//生产日期
			    xtype: 'datefield',
			    format : 'Y-m-d',
				id : 'dateProduceDateD101',
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.expire_date,//有效日期
				xtype: 'datefield',
			    format : 'Y-m-d',
				id : 'dateExpireDateD101',
				colspan: 2,
				beforeLabelTextTpl : required
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.lot_no,//批号
				id : 'txtLotNoD101',
				hidden:$i18n.lotNoHidden,
				displayField : 'lotNo',
				valueField : 'lotNo',
				store:Ext.create("cms.store.stock.stock_ADjDStore",{
					proxy:
					{
						type:'ajax',
						method:'post',
						url:'stock_AdjustAccountsAction_queryLot'
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
			    id : 'cmbQualityD101',
			    editable:false,
		        allowBlank : false,
		        beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved1,//预留字段1
				id : 'txtRsvBatch1D101',
				value:'N',
				hidden:$i18n.rsvBatch1Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved2,//预留字段2
				id : 'txtRsvBatch2D101',
				value:'N',
				hidden:$i18n.rsvBatch2Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved3,//预留字段3
				id : 'txtRsvBatch3D101',
				value:'N',
				hidden:$i18n.rsvBatch3Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved4,//预留字段4
				id : 'txtRsvBatch4D101',
				value:'N',
				hidden:$i18n.rsvBatch4Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved5,//预留字段5
				id : 'txtRsvBatch5D101',
				value:'N',
				hidden:$i18n.rsvBatch5Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved6,//预留字段6
				id : 'txtRsvBatch6D101',
				value:'N',
				hidden:$i18n.rsvBatch6Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved7,//预留字段7
				id : 'txtRsvBatch7D101',
				value:'N',
				hidden:$i18n.rsvBatch7Hidden,
				beforeLabelTextTpl : required
			},
			{
				fieldLabel:$i18n.reserved8,//预留字段8
				id : 'txtRsvBatch8D101',
				value:'N',
				hidden:$i18n.rsvBatch8Hidden,
				beforeLabelTextTpl : required
			}]
	    }]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		items : [{
			text : $i18n.save,
   		    margin:'0 0 0 540',
			id:'nSaveD101',
		 },{
				text : $i18n.close1,
				id:'btnCloseD101'
			 }]
     
	}
	]
});
