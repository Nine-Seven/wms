Ext.define('cms.view.bdef.window.bdef_DefAppendixAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.bdef_DefAppendixAddOrEditWindow',
	id : 'bdef_DefAppendixAddOrEditWindow',
	width : 500,
	height : 295,
	modal:true,
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_01_1B01',
		frame : true,
		layout:'form',
		defaults : {
			xtype : 'textfield',
			margin : '5 5 5 5',
			labelAlign:'right',
			allowBlank: true,
			labelWidth : 100
		},
		items:[
		{
			xtype:'bdef_DefOwnerCombo',
			name:'ownerNo',
			fieldLabel : '货主编号',//货主编号
			id:'owner1B01',
			displayField : 'dropValue',
		    valueField : 'value',       
	        store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	        allowBlank : false,
	        beforeLabelTextTpl : required
		},{
			fieldLabel:'文件名称',//文件名称
			name:'name',
    	    id:'fileName1B01', 
		 	allowBlank:false,
		 	beforeLabelTextTpl : required
		},{
 	    	xtype: 'wms_DefFieldValCombo',
 	    	fieldLabel: '附件类型',  //附件类型
 	    	id:'type1B01',
 	    	name:'type',
 	    	store:Ext.create("cms.store.common.comboStore").load(
		 	{
		 			params:{str:"N,FILE_TYPE"}
		 	}), 
		 	allowBlank:false,
		 	beforeLabelTextTpl : required
 	    },{
 	    	fieldLabel:'货主单号',//货主单号
    	    id:'relateOrderNo1B01',
    	    name:'relateOrderNo'
 	    },{
 	    	xtype: 'wms_DefFieldValCombo',
 	    	fieldLabel: '所属分类',  //所属分类
 	    	id:'relateClass1B01',
 	    	name:'relateClass',
 	    	store:Ext.create("cms.store.common.comboStore").load(
		 	{
		 			params:{str:"N,RELATE_CLASS"}
		 	}), 
		 	allowBlank:false,
		 	beforeLabelTextTpl : required
 	    },{
			 
    		xtype: 'fileuploadfield',  
            id: 'uploadFile1B01',  
//            beforeLabelTextTpl : required,
            emptyText: '请选择上传文件',  
            fieldLabel: '上传文件',  
            name: 'file',  
            buttonText: '选择',
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			 xtype:'textareafield',
			fieldLabel:'备注',//所属单号
    	    id:'meno1B01',
    	    name:'meno'
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget51B01'
	}]
});