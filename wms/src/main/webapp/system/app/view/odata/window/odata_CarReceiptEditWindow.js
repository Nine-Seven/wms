/**
 * 模块名称：出车回单
 * 模块编码：3926
 * 创建：hcx
 */
var rowindex1501=0;
Ext.define('cms.view.odata.window.odata_CarReceiptEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.odata_CarReceiptEditWindow',
 	layout:'border',
    width : 600,
	height : 240,
 	modal:true,
 	id:'odata_CarReceiptEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'odata_CarReceiptEditForm',
 	  	frame : true,
    items:[
    	{	xtype:'fieldset',
		    layout: 
		    {
		        type: 'table',
		        columns: 2
		    },
		    defaults : 
		    {
		        xtype : 'textfield',
                labelWidth : 100,
                margin:'2 5 5 5',
	            labelAlign:'right'			
		    },
		    items:[
            {
	          	xtype:'bdef_DefOwnerCombo',
				fieldLabel:$i18n.owner,//委托业主
	 	    	id:'owner_no3926',
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	 	    	readOnly:true
			},{
	 	    	fieldLabel:$i18n.locate_no,//波次号
	 	    	id:'wave_no3926',
	 	    	//maxLength:90,
		 	    readOnly:true
	 	    },{
	 	    	fieldLabel:$i18n.split_no,//配送单号
	 	    	id:'deliver_no3926',
		 	    readOnly:true
	 	    },{
	 	    	xtype:'bdef_DefCustCombo',
				fieldLabel:$i18n.cust,//客户
	 	    	id:'cust_no3926',
	  			store:Ext.create('cms.store.bdef.bdef_DefCustComboStore'),
	  			displayField : 'dropValue',
       			valueField : 'value',
	  			readOnly:true
	 	    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.deliverBox,//配送物流箱数
				minValue:0,
				id : 'deliver_box3926',
		 	    readOnly:true
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.backBox,//返回物流箱数
				minValue:0,
				id : 'back_box3926'
		    },{
	 	    	fieldLabel:$i18n.car_no,//车辆代码
	 	    	id:'car_no3926',
		 	    readOnly:true
	 	    },{
	 	    	fieldLabel:$i18n.deiver_worker,//司机
	 	    	id:'deiver_worker3926',
		 	    readOnly:true
	 	    },{
		    	xtype:'wms_DefFieldValCombo',
		        fieldLabel : $i18n.status,//状态
		        id : 'status3926',
		        store:Ext.create("cms.store.common.comboStore").load(
		        {
		        	params:{str:"ODATA_CAR_RECEIPT,STATUS"}
		        }),
	  			readOnly:true
			}]
		  }]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget3926'
 	}]
});