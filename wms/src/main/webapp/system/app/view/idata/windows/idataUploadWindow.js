Ext.define('cms.view.idata.windows.idataUploadWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.idataUploadWindow',
	layout:'border',
    height:90,
	width:490,
	modal : true,
	id:'idataUploadWindow',
	title:'test',
	items:[{
    	xtype : 'radiogroup',
		id : 'raidio4101',
		region:'north',
		columns: 3,
		fieldLabel :'单据类型',
		margin : '0 0 0 70',
       // border:1,
        vertical: false,
		items : [{
  			 boxLabel  : '存储单导入',
             name      : 't',
             inputValue: 'IS',
             checked:true
  		},{
            boxLabel  : '直通单导入',
            name      : 't',
            inputValue: 'ID'
        }]
	   },{
		xtype:'form',
		region:'south',
		id:'uploadForm4101',
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
            id: 'file4101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传',  
            name: 'file',  
            buttonText: '选择',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'upLoad_w4101',
        	text:'上传',
        	width : 50,
        	handler: function() {
        		if(Ext.isEmpty(Ext.getCmp('raidio4101').getValue().t))
        		{
        			Ext.example.msg($i18n_prompt.prompt,'请选择单据类型！');       
        			return;
        		}
        		if(Ext.isEmpty(Ext.getCmp('file4101').getValue()))
        		{
        			Ext.example.msg($i18n_prompt.prompt,'请选择导入的Excel文件！');       
        			return;
        		}
	            var form = Ext.getCmp('uploadForm4101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'idata_ImPortAction_upLoad',
	                    params:{
	                    	importType:Ext.getCmp('raidio4101').getValue().t
					     },
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
//	                    	Ext.example.msg('提示',data.msg);                  	
//	                        Ext.getCmp('idataUploadWindow').close();
//	                		Ext.getCmp('grid_01_4102').getStore().load();
	                    	if(data.isSucc){
                        		Ext.example.msg('提示',data.msg);   
    	                    	Ext.getCmp('idataUploadWindow').close();
    	                        Ext.getCmp('grid_01_4102').getStore().load();
            				}else{
            					Ext.Msg.alert('提示',data.msg+data.obj);    
    	                    	Ext.getCmp('idataUploadWindow').close();
            				}

                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
                        	if(data.isSucc){
                        		Ext.example.msg('提示',data.msg);   
    	                    	Ext.getCmp('idataUploadWindow').close();
    	                        Ext.getCmp('grid_01_4102').getStore().load();
            				}else{
            					Ext.Msg.alert('提示',data.msg+data.obj);    
    	                    	Ext.getCmp('idataUploadWindow').close();
            				}
                        }
	                });
	            }
	        }
		}]
	}
	]
});
