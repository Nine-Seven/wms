Ext.define('cms.view.ridata.windows.ridataUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.ridataUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'ridataUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadForm6101',
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
            id: 'file6101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'upLoad_w6101',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadForm6101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'ridata_UntreadAction_upLoad',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);                  	
	                    
	                        Ext.getCmp('ridataUploadWindow').close();
	                        Ext.getCmp('gridRidata_UntreadM6101').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg+data.obj);    
	                    	
	                    	Ext.getCmp('ridataUploadWindow').close();
	                        Ext.getCmp('gridRidata_UntreadM6101').getStore().load();
                        }
	                });
	            }
	        }
		}]
	}
	]
});
