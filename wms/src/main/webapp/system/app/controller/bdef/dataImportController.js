/**
 * 模块名称：数据导入Controller
 * 模块编码：1A01
 * 创建：lich
 */
Ext.define('cms.controller.bdef.dataImportController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.dataImportUI'],
	init:function(){
		this.control({//导入
			'dataImportUI button[id=import1A01]':{
				click:this.import1A01Click
			},//下载模板
			'dataImportUI button[id=butDownload1A01]':{
				click:this.butDownload1A01Click
			},//删除模板
			'dataImportUI button[id=delete1A01]':{
				click:this.delete1A01Click
			}
		});
	},
	import1A01Click:function(){
		//debugger;
		if(Ext.isEmpty(Ext.getCmp('raidio1A01').getValue().t))
		{
			Ext.example.msg($i18n_prompt.prompt,'请选择导入的类型！');       
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('file1A01').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,'请选择导入的Excel文件！');       
			return;
		}
		var form = Ext.getCmp('uploadForm1A01').getForm();
        if(form.isValid()){
        	var msgShow = commonMegShow('正在导入，请稍等...');
            form.submit({
                url: 'dataImportAction_dataImport',
                timeout:1800000,
                params:{
						dataType:Ext.getCmp('raidio1A01').getValue().t
						//ownerNo:Ext.getCmp('cmbOwnerNo1A01').getValue()
				},
                success: function(fp, o) {
                	var data = Ext.decode(o.response.responseText);
                	Ext.example.msg($i18n_prompt.prompt,data.msg); 
                	msgShow.hide();
            	},  
                failure : function (fp,o){ 
                	var data = Ext.decode(o.response.responseText);
                	Ext.example.msg($i18n_prompt.prompt, data.msg); 
                	msgShow.hide();
                }
            });
        }
	},
	
	//下载模板
	butDownload1A01Click:function(){
		if(Ext.getCmp('download1A01').getValue()==null || Ext.getCmp('download1A01').getValue()==""){
			Ext.example.msg($i18n_prompt.prompt,'请选择要下载的模板'); 
			return;
		}
		window.location.href = Ext.getCmp('download1A01').getValue();
	},
	
	//删除模板
	delete1A01Click:function(){
		if(Ext.getCmp('download1A01').getValue()==null || Ext.getCmp('download1A01').getValue()==""){
			Ext.example.msg($i18n_prompt.prompt,'请选择要删除的模板'); 
			return;
		}
		
		Ext.Ajax.request({
			url:'dataImportAction_deleteFile.action',
			params : {
				filePath:Ext.getCmp('download1A01').getValue()
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,'删除成功');
					Ext.getCmp('download1A01').getStore().removeAll();
					Ext.getCmp('download1A01').getStore().load();
					
				}else{
					Ext.example.msg($i18n.prompt,'删除失败');
				}
			}
		});
	}
});