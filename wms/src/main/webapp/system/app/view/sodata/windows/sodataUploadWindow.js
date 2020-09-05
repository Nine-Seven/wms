Ext.define('cms.view.sodata.windows.sodataUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.sodataUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'sodataUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadFormE101',
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
            id: 'fileE101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'upLoad_wE101',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadFormE101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'sodata_WasteAction_upLoad',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);                  	
	                    
	                        Ext.getCmp('sodataUploadWindow').close();
	                        Ext.getCmp('grid_01_E101').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
                        	Ext.example.msg('提示',data.msg+data.obj);  
	                    	
	                    	Ext.getCmp('sodataUploadWindow').close();
	                        Ext.getCmp('grid_01_E101').getStore().load();
                        }
	                });
	            }
	        }
		}]
	}
	]
});
