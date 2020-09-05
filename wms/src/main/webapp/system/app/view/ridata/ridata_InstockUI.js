/**
 * 模块名称：返配上架回单
 * 模块编码：6301
 * 创建：周欢
 */
var InstockMStore=Ext.create('cms.store.ridata.ridata_InstockMStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('gridInstockM6301').getStore().count()>0){
				Ext.getCmp('gridInstockM6301').getSelectionModel().select(0);
			}
		}
	}
});
Ext.define('cms.view.ridata.ridata_InstockUI',{
	alias:'widget.ridata_InstockUI',
	title:$i18n.title6301,//返配上架回单
	id:'ridata_InstockUI',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.wms_DefFieldValCombo'

	          ],
	items:[
	{
	    xtype:'commMenuWidget4',
	    id:'menu6301',
	    region:'north'
    },{
		xtype : 'grid',
		region : 'west',
		width : '65%',
		store:InstockMStore,
		id:'gridInstockM6301',
		columns : [
		{
			xtype : 'rownumberer',
			width : 30
		},{
			width : 100,
			text : $i18n.locate_no,//波次号
			dataIndex : 'waveNo'
		},
		{
			width : 130,
			text : $i18n.instock_no,//上架单号
			dataIndex : 'instockNo'
		},{
			width : 80,
			text : $i18n.dispatch_worker,//发单人员
			dataIndex : 'rgstName' 
		},{
			width : 140,
			text : $i18n.dispatch_date,//发单日期
			dataIndex : 'rgstDate'
		},{
			width : 80,
			text : $i18n.status,	//状态
			dataIndex : 'statusText'
		}]
	},{
		xtype : 'form',
		id:'formCondition6301',
		layout:'column',
		region : 'east',
		frame : true,
		width : '35%',
		layout:{
			type : 'table',
			columns : 1
		},
		items:[{
			xtype:'fieldset',
			margin:'10 0 0 0',
			title: '过滤条件',
			defaults:{
				labelWidth : 90,
				margin : '0 0 5 0',
				labelAlign : 'right',
				//readOnly:true,
				xtype:'textfield'
		    },
			items:[{ xtype:'wms_DefFieldValCombo',
	        	 fieldLabel:'质量类型',//质量类型
	        	 id:'cmbQ_type6301',
	        	 store:Ext.create("cms.store.common.comboStore").load(
	       	     {
	       	          params:{str:"RIDATA_UNTREAD_M,QUALITY"}
	       	     })
	       	 },{
	       		xtype : 'cdef_DefCellCombo',
				fieldLabel : $i18n.locate_no, //波次号
				id : 'cmbLocate_no6301',
				displayField : 'value',
	   			valueField : 'value',
				store : Ext.create("cms.store.cdef.cdef_DefCellComboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url:'ridata_InstockAction_queryWaveNo.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
				}), 
	       	 }
	       	 ]
		},{
			xtype:'fieldset',
			margin:'0 0 0 0',
			title: '操作',
		    layout:{
				type : 'table',
				columns : 2
			},
			defaults:{
				labelWidth : 70,
				margin : '5 5 5 0',
				labelAlign : 'right',
				xtype:'textfield'
			},
			items:[{
				xtype : 'bdef_DefWorkerCombo',
				fieldLabel : $i18n.instock_worker, //上架人员
				id : 'cmbInstock_worker6301',
				colspan:2,
				store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
				beforeLabelTextTpl : required
			},{
				xtype : 'datefield',
				fieldLabel : $i18n.instock_date,//上架日期
				id : 'dateInstock_date6301',	
				format : 'Y-m-d',
				value:new Date(),
				beforeLabelTextTpl : required
			},{
				 xtype : 'button',
        		 id:'butCloseInstock6301',
        		 text : '取消上架' //取消上架
			},{
				xtype:'textfield',
	  			fieldLabel:'上架储位',//储位
	  			id:'cellNo6301'
			},{
				 xtype : 'button',
        		 id:'butsaveInstock6301',
        		 text : '指定上架' //回单
			}]
		}]
     },{
         xtype : 'grid',
		 id : 'gridInstockD6301',//
		 region:'south',
		 height : 230,
		 loadMask : true, // 加载时有加载的图标
		 store : Ext.create('cms.store.ridata.ridata_InstockDStore'),
		 selModel : {
		    selType : 'cellmodel'
		 },
		 plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
		    clicksToEdit : 1
		 })],
		 columns : [
		 {
    	 	 xtype : 'rownumberer',
	    	 width : 30
		 },{
			 width : 110,
			 text : $i18n.dest_cell_no,//目的上架储位
			 dataIndex : 'destCellNo'
		 },{
			 width : 100,
			 text : $i18n.article_no,//商品编码
			 dataIndex : 'articleNo'
		 },{
			 width : 180,
			 text : $i18n.article_name,//商品名称
			 dataIndex : 'articleName'
		 },{
			 width : 120,
			 text : $i18n.barcode,//商品条码
			 dataIndex : 'barcode'
		 },{
			 width : 60,
			 text : $i18n.packing_qty,//包装数量
			 dataIndex : 'packingQty'
		 }/*,{
			 width : 60,
			 text : $i18n.packing_unit,//包装单位
			 dataIndex : 'unit'
		 }*/,/*{
		 	 width : 60,
			 text : $i18n.spec,//规格
			 dataIndex : 'spec'
		 }*/{
				width:80,
				text:$i18n.packingSpec,//规格
				id:'packingSpec_6301',
				dataIndex:'packingSpec'
			},/*{
			 width : 80,
			 text : $i18n.article_qty,//计划数量	
			 dataIndex : 'articleQty'
		 },*/{
			    width:80,
			    text:$i18n.packingUnit,//包装单位
			    id:'packingUnit_6301',
			    dataIndex:'packingUnit'
			},{
				width : 85,
				text : $i18n.planBox,	//计划箱数
				dataIndex : 'planBox',
				//hidden:true,
				id:'planBox_6301',
			},{
				width : 85,
				text : $i18n.planQmin,//计划中包数
				dataIndex : 'planQmin',
				id:'planQmin_6301',
			},{
				width : 85,
				text : $i18n.planDis,//计划散数
				dataIndex : 'planDis',
				id:'planDis_6301',
			},{
			 width : 110,
			 text : $i18n.real_cell_no,// 实际上架储位
			 dataIndex : 'realCellNo',
			 cls:'notnull',
			 field: {
                xtype: 'cdef_DefCellCombo',
	            displayField : 'value',
	    		valueField : 'value',
	    		store : Ext.create("cms.store.cdef.cdef_DefCellComboStore",{
		    		proxy:{
						type:'ajax',
						method:'post',
						url:'ridata_InstockAction_queryCell.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
	    		}),
	    		allowBlank :false
             }
 		 },/*{
			 width : 70,
			 text : $i18n.instock_qty,//整件数
			 dataIndex : 'realBox'
		 },*/{
				width : 85,
				text : $i18n.instockBox,	//上架箱数
				dataIndex : 'realBox',
				//hidden:true,
				id:'realBox_6301',
			},{
				width : 85,
				text : $i18n.instockQmin,//上架中包数
				dataIndex : 'realQmin',
				id:'realQmin_6301',
			},{
				width : 85,
				text : $i18n.instockDis,//上架散数
				dataIndex : 'realDis',
				id:'realDis_6301',
			},{
			 width : 100,
			 text : $i18n.produce_date,//生产日期
			 dataIndex : 'produceDate'
		 },{
			width:160,
			text:$i18n.lot_no,//'批号',
//			hidden:$i18n.lotNoHidden,
			dataIndex:'lotNo'
		},{
			width:160,
			text:$i18n.quality,//'品质',
			hidden:$i18n.qualityHidden,
			dataIndex:'qualityText',
		}]
   },{
		xtype : 'panel',
		id : 'msterForm6301',
		region:'south',
		html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
				+ 'background-color:#C1D5ED; text-align: left;">'
				+ '<span><label>新增人:</label><label id="rgstName6301"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;新增日期：</label><label id="rgstDate6301"></label></span>'
				+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName6301"></label> </span> '
				+ '<span><label>&nbsp;&nbsp;&nbsp;修改日期：</label><label id="updtDate6301"></label> </span></div>'
	}]          
});