Ext.define('cms.view.odata.window.odataUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.odataUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'odataUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadForm3101',
        frame:true,
        defaults : {
			labelWidth : 90,
			margin : '2 2 2 2',
			labelAlign : 'right',
			width : 400
	    },
	    layout: 
        {
        	type: 'table',
        	columns: 2
        },
        fileUpload: true, 
        items: [
    	{ 
    		xtype: 'fileuploadfield',  
            id: 'file3101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
        	xtype:'button',
        	id:'upLoad_w3101',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadForm3101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'odata_ExpAction_upload',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);
	                        Ext.getCmp('odataUploadWindow').close();
	                      //  Ext.getCmp('grid_01_3101').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);
	                    	Ext.getCmp('odataUploadWindow').close();
	                     //   Ext.getCmp('grid_01_3101').getStore().load();
                        }
	                });
	            }
	        }
        }]
	}
	]
});