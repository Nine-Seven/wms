Ext.define('cms.view.mdata.window.mdataUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.mdataUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'mdataUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadForm5101',
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
            id: 'file5101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file', 
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
        	xtype:'button',
        	id:'upLoad_w5101',
        	text:'上传',
        	width : 50,
        	handler: function() {
        		debugger;
	            var form = Ext.getCmp('uploadForm5101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'mdata_PlanMAction_upload.action',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);
	                        Ext.getCmp('mdataUploadWindow').close();
	                        //Ext.getCmp('mdata_Plan_MUI5101_1b').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);
	                    	Ext.getCmp('mdataUploadWindow').close();
	                        //Ext.getCmp('mdata_Plan_MUI5101_1b').getStore().load();
                        }
	                });
	            }
	        }
        }]
	}
	]
});
