/**
 * 模块名称：打印机维护
 * 模块编码：1F01
 * 创建：Jun
 */
Ext.define('cms.view.bdef.window.bdef_DefPrinterAddorEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bdef_DefPrinterAddorEditWindow',
	layout:'border',
	id:'bdef_DefPrinterAddorEditWindow',
	width:500,
	height:205,
	modal:true,
	items:[
	{
	    xtype:'form',
	    region:'center',
	    id:'form_01_1F01',
	  	frame : true,
		layout : 'form',
		defaults : {
	    	xtype : 'textfield',
	    	margin : '5 5 5 5',
			labelWidth :100,
			labelAlign:'right'	
	    },
		items:[
		{
			fieldLabel:$i18n.warehouse,
			id:'warehouse1F01',
			allowBlank:false,
			readOnly:true,
			beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.printer_no,
			id:'printer_no1F01',
			allowBlank:false,
			beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.printer_name,
			id:'printer_name1F01',
		},{
			xtype:'wms_DefFieldValCombo',
		    fieldLabel:$i18n.printer_type,
		    id:'printer_type1F01',
		    displayField: 'dropValue',
			valueField: 'value',
		    store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_DefPrinterAction_getPrinterTypeList.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
		   	}).load(),
		    editable:false,
		    allowBlank:false,
		    beforeLabelTextTpl:required
		},{
			xtype:'wms_DefFieldValCombo',
		    fieldLabel:$i18n.status,
		    id:'status1F01',
		    store:Ext.create("cms.store.common.comboStore").load(
		    {
					params:{str:"N,DEF_STATUS"}
		    }),
		    editable:false,
		    allowBlank:false,
		    beforeLabelTextTpl:required
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget51F01'
	}]
});