Ext.define('cms.view.odata.window.odataPackageUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.odataPackageUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'odataPackageUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadForm3931',
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
            id: 'file3931',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'upLoad_w3931',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadForm3931').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'odata_PackageAction_upLoad',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);                  	
	                    
	                        Ext.getCmp('odataPackageUploadWindow').close();
	                        Ext.getCmp('grid_01_3931').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg+data.obj);    
	                    	
	                    	Ext.getCmp('odataPackageUploadWindow').close();
	                        Ext.getCmp('grid_01_3931').getStore().load();
                        }
	                });
	            }
	        }
		}]
	}
	]
});
