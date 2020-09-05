Ext.define('cms.view.odata.window.odata_checkPackMeteWindow',{
	extend:'Ext.window.Window',
	alias:'widget.odata_checkPackMeteWindow',
	layout:'border',
	id:'odata_checkPackMeteWindow',
	width:400,
	height:150,
	modal:true,
	items:[
	{
	    xtype:'form',
	    region:'center',
	    id:'odata_checkPackMeteForm',
	    frame:true,	    
	    items:[
	    {
            xtype:'fieldset',
  			layout: {
      		type: 'table',
      	        columns: 1
      	    },
      	    defaults:{
    	   		xtype:'textfield',
    	   		margin:'5 4 1 4',
    	   		labelAlign:'right',
    	   		labelWidth:120
       	    },
       	    items:[
	        {
        	    fieldLabel:'包裹号' ,//包裹号
        	    id:'packLabelNo',
        	    readOnly : true,
        	    maxLength:30
	        },
	        {
        	    fieldLabel:'建议包材名称',//建议包材名称
        	    id:'packMeteName',
        	    readOnly : true,
        	    maxLength:35        	    
	        },
	        {
        	    fieldLabel:'实际包材条码',//实际包材条码
        	    id:'packMeteBarcode',
        	   // allowBlank:false,
        	    maxLength:20,
        	    enableKeyEvents:true
        	    //beforeLabelTextTpl:required
	        },
	        {
        	    fieldLabel:'实际包材编码',//实际包材编码
        	    id:'packMeteArticleNo',
        	    hidden : true,
        	    maxLength:35             	    
	        },
	        {
        	    fieldLabel:'实际包材名称',//实际包材名称
        	    id:'packMeterRealName',
        	    hidden : true,
        	    maxLength:35        	    
	        },
	        {
	        	xtype:'numberfield',
	        	fieldLabel:'包材数量',  //实际包材数量
        	    id:'packMeteQTY',
        	    maxLength:90,
        	    allowBlank:false,
        	    beforeLabelTextTpl:required,
        	    value : 1,
        	    hidden:true
            }]
	    },]
    }/*,
    {
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menu3916Pack'
	}*/]
});
