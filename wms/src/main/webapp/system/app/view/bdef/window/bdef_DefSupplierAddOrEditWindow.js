Ext.define('cms.view.bdef.window.bdef_DefSupplierAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.bdef_DefSupplierAddOrEditWindow',
 	layout:'border',
    width : 900,
	height : 550,
 	modal:true,
 	id:'bdef_DefSupplierAddOrEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'bdef_DefSupplierAddOrEditForm',
 	  	frame : true,
    items:[
    	{	xtype:'fieldset',
			title:$i18n.baseInfo,//
		    layout: 
		    {
		        type: 'table',
		        columns: 3
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
	 	    	id:'owner_no1601',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	 	    	readOnly:false
			},{
	 	    	fieldLabel:$i18n.supplier_no,
	 	    	id:'supplier_no1601',
	 	    	//maxLength:10,
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.status,
	 	    	id:'status1601',
	    		store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"N,DEF_STATUS"}
				})
 	        },{
	 	    	fieldLabel:$i18n.supplier_name,
	 	    	id:'supplier_name1601',
	 	    	maxLength:180,
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	colspan:2,
	 	    	width:526
	 	    },{
	 	    	fieldLabel:$i18n.supplier_alias,
	 	    	id:'supplier_alias1601',
	 	    	//maxLength:90
	 	    },{
	 	    	fieldLabel:$i18n.real_supplier_no,
	 	    	id:'real_supplier_no1601',
	 	    	maxLength:10,
	 	    	colspan:2,
	 	    	width:526
	 	    },{
	 	    	fieldLabel:$i18n.real_supplier_name,
	 	    	id:'real_supplier_name1601' 	    	
	 	    	//maxLength:90
	 	    },{
	 	    	fieldLabel:$i18n.supplier_note_code,
	 	    	id:'supplier_note_code1601',
	 	    	maxLength:50,
	 	    	colspan:2,
	 	    	width:526
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.unload_flag,
	 	    	id:'unload_flag1601',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_DEFSUPPLIER,UNLOAD_FLAG"}
				})
	 	    }/*{
	 	    	fieldLabel:$i18n.supplier_name,
	 	    	id:'real_supplier_name1601',
	 	    	maxLength:90,
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required
	 	    },*/,{
	 	    	fieldLabel:$i18n.supplier1,
	 	    	id:'supplier11601',
	 	    	maxLength:15
	 	    },{
	 	    	fieldLabel:$i18n.supplier_phone1,
	 	    	id:'supplier_phone11601',
	 	    	maxLength:50
	 	    },{
	 	    	fieldLabel:$i18n.supplier_fax1,
	 	    	id:'supplier_fax11601',
	 	    	maxLength:50
	 	    },{
	 	    	fieldLabel:$i18n.supplier_address1,
	 	    	id:'supplier_address11601',
	 	    	maxLength:180,
	 	    	width:795,
	 	    	colspan:3
	 	    },{
	 	    	fieldLabel:$i18n.supplier2,
	 	    	id:'supplier21601',
	 	    	maxLength:15
	 	    },{
	 	    	fieldLabel:$i18n.supplier_phone2,
	 	    	id:'supplier_phone21601',
	 	    	maxLength:50
	 	    },{
	 	    	fieldLabel:$i18n.supplier_fax2,
	 	    	id:'supplier_fax21601',
	 	    	maxLength:50
	 	    },{
	 	    	fieldLabel:$i18n.supplier_address2,
	 	    	id:'supplier_address21601',
	 	    	maxLength:180,
	 	    	width:795,
	 	    	colspan:3
	 	    },{
	 	    	fieldLabel:$i18n.invoice_no,
	 	    	id:'invoice_no1601',
	 	    	maxLength:20
	 	    },{
	 	    	fieldLabel:$i18n.invoice_header,
	 	    	id:'invoice_header1601',
	 	    	maxLength:50,
	 	    	colspan:2
	 	    },{
	 	    	fieldLabel:$i18n.invoice_addr,
	 	    	id:'invoice_addr1601',
	 	    	maxLength:100,
	 	    	width:795,
	 	    	colspan:3
	 	    },{
	 	    	fieldLabel:$i18n.supplier_remark,
	 	    	id:'supplier_remark1601',
	 	    	width:795,
	 	    	colspan:3
	 	    	//maxLength:50
	 	    }]
		  },{
			xtype:'fieldset',
			//region:'center',
			//colspan:2,
            title:'预留属性',
            layout: 
		    {
		        type: 'table',
		        columns: 3
		    },
            defaults:{
	    	   	xtype:'textfield',
	    	   	/*margin:'0 0 4 0',
	    	   	labelWidth : 70,*/
	    	   	labelWidth : 100,
                margin:'2 5 5 5',
	    	   	labelAlign : 'right'
	       	},
	       	items : [{
	       		fieldLabel:$i18n.supplier_rsv_var1,
	 	    	id:'rsv_var11601',
	 	    	maxLength:50
	       	},{	
	       		fieldLabel:$i18n.supplier_rsv_var2,
	 	    	id:'rsv_var21601',
	 	    	maxLength:50
	 	    },{
	 	    	fieldLabel:$i18n.supplier_rsv_var3,
	 	    	id:'rsv_var31601',
	 	    	maxLength:50	
	 	    },{
	 	    	fieldLabel:$i18n.supplier_rsv_var4,
	 	    	id:'rsv_var41601',
	 	    	maxLength:50	
	 	    },{
	 	    	fieldLabel:$i18n.supplier_rsv_var5,
	 	    	id:'rsv_var51601',
	 	    	maxLength:50	
	 	    },{
	 	    	fieldLabel:$i18n.supplier_rsv_var6,
	 	    	id:'rsv_var61601',
	 	    	maxLength:50	
	 	    }]
 	    }]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget1601'
 	}]
});