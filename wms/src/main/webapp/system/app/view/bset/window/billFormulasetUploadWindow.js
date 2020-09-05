Ext.define('cms.view.bset.window.billFormulasetUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.billFormulasetUploadWindow',
	layout:'border',
    height:80,
	width:490,
	modal : true,
	id:'billFormulasetUploadWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'uploadFormB101',
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
            id: 'fileB101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'upLoad_wB101',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadFormB101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'bill_FormulasetAction_upLoad',
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);                  	
	                    
	                        Ext.getCmp('billFormulasetUploadWindow').close();
	                        Ext.getCmp('formulasetUIB101').getStore().load();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg+data.obj);    
	                    	
	                    	Ext.getCmp('billFormulasetUploadWindow').close();
	                        Ext.getCmp('formulasetUIB101').getStore().load();
                        }
	                });
	            }
	        }
		}]
	}
	]
});
