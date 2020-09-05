Ext.define('cms.view.rodata.window.rodataUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.rodataUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'rodataUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadForm7101',
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
            id: 'file7101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'upLoad_w7101',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadForm7101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'rodata_RecedeMTTHAction_upLoad',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);                  	
	                    
	                        Ext.getCmp('rodataUploadWindow').close();
	                        Ext.getCmp('gridRodata_RecedeM7101').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg+data.obj);    
	                    	
	                    	Ext.getCmp('rodataUploadWindow').close();
	                    	Ext.getCmp('cmbRecede_type7101').getStore().load();
	                    	Ext.getCmp('gridRodata_RecedeM7101').getStore().load();
                        }
	                });
	            }
	        }
		}]
	}
	]
});
