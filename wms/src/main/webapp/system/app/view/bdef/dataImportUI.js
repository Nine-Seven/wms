//数据导入、文件上传下载
Ext.define('cms.view.bdef.dataImportUI',{
	alias:'widget.dataImportUI',
	title:'数据导入',
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1A01',
	    region:'north'
    },{
       	xtype:'fieldset',
       	title: '数据导入',       
        layout: 'anchor',
        defaultType: 'radiofield',
        region:'north',
        defaults: {
            margin : '2 2 15 2'
        },
        items: [{
        	xtype : 'radiogroup',
			id : 'raidio1A01',
			layout: 'anchor',
			defaults: {
            	margin : '2 2 15 2'
        	},
			items : [/*{
				xtype:'bdef_DefOwnerCombo',
    			fieldLabel : $i18n.owner_no,//货主编号
    			id:'cmbOwnerNo1A01',
    			displayField : 'dropValue',
    		    valueField : 'value',
    	        allowBlank : false,
    	        editable:false,
    	        beforeLabelTextTpl : required,
    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load()
	  		},*/{
	  			 boxLabel  : '货主资料导入',
	             name      : 't',
	             inputValue: 'owner'
	  		},{
                boxLabel  : '商品类别导入',
                name      : 't',
                inputValue: 'gro'
            }, {
                boxLabel  : '商品资料导入',
                name      : 't',
                inputValue: 'art'
            },{
                boxLabel  : '商品资料导入（商品名称拼为：货主商品编码+商品名称）',
                name      : 't',
                inputValue: 'artName'
            }, {
                boxLabel  : '客户资料导入',
                name      : 't',
                inputValue: 'cus'
            }, {
                boxLabel  : '供应商资料导入',
                name      : 't',
                inputValue: 'sup'
            }, {
                boxLabel  : '商品储位对应关系导入',
                name      : 't',
                inputValue: 'cell'
            }/*, {
                boxLabel  : '入库手建单导入',
                name      : 't',
                inputValue: 'imp'
            }, {
                boxLabel  : '出库手建单导入',
                name      : 't',
                inputValue: 'exp'
            }*/]
    	},            
    	{
			xtype:'form',
			id:'uploadForm1A01',
	        frame:true,
	        defaults : {
				margin : '2 2 2 2',
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
	            id: 'file1A01',  
	            emptyText: '请选择Excel文件',  
	            fieldLabel: '请选择Excel文件',  
	            name: 'file',  
	            buttonText: '选择',
	            buttonCfg: {  
	                iconCls: 'upload-icon'  
	            } 
			},{
				xtype:'button',
	        	id:'import1A01',
	        	text:'导入',
	        	width : 50
			}]
		}]
	},{
       	xtype:'fieldset',
       	title: '上传下载文件模板',       
        layout: 'anchor',
        region:'north',
        defaults: {
            margin : '2 2 15 2'
        },
        items: [           
    	{
			xtype:'form',
			id:'uploadForm1A01_1',
	        frame:true,
	        defaults : {
				margin : '2 2 2 2',
				width : 450
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
	            id: 'uploadFile1A011',  
	            emptyText: '请选择Excel文件',  
	            fieldLabel: '请选择Excel文件',  
	            name: 'file',  
	            buttonText: '选择',
	            buttonCfg: {  
	                iconCls: 'upload-icon'  
	            } 
			},{
				xtype:'button',
	        	id:'butUploadA011',
	        	text:'上传',
	        	width : 50,
	        	handler: function() {
	        		if(Ext.isEmpty(Ext.getCmp('uploadFile1A011').getValue()))
	        		{
	        			Ext.example.msg($i18n_prompt.prompt,'请选择上传的Excel文件！');       
	        			return;
	        		}
	        		
		            var form = Ext.getCmp('uploadForm1A01_1').getForm();
		            if(form.isValid()){
		                form.submit({
		                    url: 'dataImportAction_upload',
		                    success: function(fp, o) {
		                    	var data = Ext.decode(o.response.responseText);
		                    	Ext.example.msg($i18n_prompt.prompt,data.msg); 
		                    	Ext.getCmp('download1A01').getStore().removeAll();
		    					Ext.getCmp('download1A01').getStore().load();
	                    	},  
	                        failure : function (fp,o){ 
	                        	var data = Ext.decode(o.response.responseText);
		                    	Ext.example.msg($i18n_prompt.prompt,data.msg); 
		                    	Ext.getCmp('download1A01').getStore().removeAll();
		    					Ext.getCmp('download1A01').getStore().load();
	                        }
		                });
		            }
		        }
			}]
		},{

			xtype:'form',
			id:'downloadFrom1A01',
	        frame:true,
	        defaults : {
				margin : '2 2 2 2',
				width : 400
		    },
		    layout: 
	        {
	        	type: 'table',
	        	columns: 3
	        },
	        fileUpload: true, 
	        items: [{
				xtype:'wms_DefFieldValCombo',
    			fieldLabel : '请选择Excel文件',
    			id:'download1A01',
    			displayField : 'dropValue',
    		    valueField : 'value',
    		    store:Ext.create("cms.store.common.comboStore",{
			    	    proxy:{
							type:'ajax',
							async:false,
							method:'post',
							url:'dataImportAction_getDownloadList',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
				    }).load(),
				    displayField : 'dropValue',
				    valueField : 'value',
	  		
			},{
				xtype:'button',
	        	id:'butDownload1A01',
	        	text:'下载',
	        	width : 50
			},{
				xtype:'button',
	        	id:'delete1A01',
	        	text:'删除',
	        	width : 50
			}]
		
		}]
	}]
});