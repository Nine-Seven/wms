Ext.define('cms.view.bdef.window.bdef_ArticleFamilyUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.bdef_ArticleFamilyUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'bdef_ArticleFamilyUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadForm1R01',
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
            id: 'file1R01',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
        	xtype:'button',
        	id:'upLoad_w1R01',
        	text:'上传',
        	width : 50,
        	handler: function() {
        		debugger;
	            var form = Ext.getCmp('uploadForm1R01').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'bdef_ArticleFamilyAction_upLoad',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
                        	if(data.isSucc){
                        		Ext.example.msg('提示',data.msg);
    	                    	Ext.getCmp('bdef_ArticleFamilyUploadWindow').close();
    	                        Ext.getCmp('grid_01_1R01').getStore().load();
            				}else{
            					Ext.Msg.confirm($i18n.prompt, data.msg,function(){
    	            			});  
    	                    	Ext.getCmp('bdef_ArticleFamilyUploadWindow').close();
            				}
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
                        	if(data.isSucc){
                        		Ext.example.msg('提示',data.msg);
    	                    	Ext.getCmp('bdef_ArticleFamilyUploadWindow').close();
    	                        Ext.getCmp('grid_01_1R01').getStore().load();
            				}else{
            					Ext.Msg.alert($i18n.prompt, data.msg+data.obj);  
    	                    	Ext.getCmp('bdef_ArticleFamilyUploadWindow').close();
            				}
                        }
	                });
	            }
	        }
        }]
	}
	]
});
