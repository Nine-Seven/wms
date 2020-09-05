Ext.define('cms.view.cdef.window.cdef_UploadCellWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.cdef_UploadCellWindow',
	layout:'border',
	height:100,
	width:490,
	modal : true,
	id:'cdef_UploadCellWindow2101',
	title:'储位导入',
	items:[
	{
		xtype:'form',
		id:'uploadCellForm2101',
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
        	xtype:'bdef_DefOwnerCombo',
        	fieldLabel : $i18n.owner_no,//委托业主编号
        	id : 'selectOwnerNo2101',
        	colspan:2,
        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
            beforeLabelTextTpl : required	        
        },{ 
    		xtype: 'fileuploadfield',  
            id: 'cellFile2101',  
            emptyText: '请选择Excel文件',  
            fieldLabel: '上传文件',  
            name: 'file',  
            buttonText: '',  
            buttonCfg: {  
                iconCls: 'upload-icon'  
            } 
		},{
			xtype:'button',
        	id:'cellUpLoad_2101',
        	text:'上传',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('uploadCellForm2101').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'cdef_DefWareAction_defcellUpLoad',
	                    params:{
	                    	ownerNo:Ext.getCmp('selectOwnerNo2101').getValue()
	        		    },
	                    success: function(fp, o) {
	                    	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);                  	
	                    
	                        Ext.getCmp('cdef_UploadCellWindow2101').close();
                    	},  
                        failure : function (fp,o){ 
                        	var data = Ext.decode(o.response.responseText);
	                    	Ext.example.msg('提示',data.msg);    
	                    	
	                    	Ext.getCmp('cdef_UploadCellWindow2101').close();
                        }
	                });
	            }
	        }
		}]
	}]
});
