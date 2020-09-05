/**
 * 模块名称：附件管理
 * 模块编码：1B01
 */
var rowindex1B01=0;
var saveType1B01='add';
Ext.define('cms.controller.bdef.bdef_DefAppendixController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_DefAppendixUI'
	          ],
	init:function(){
		this.control({//新增
			'bdef_DefAppendixUI button[name=detailAdd]':{
				click:this.detailAdd
			},//保存
			'bdef_DefAppendixAddOrEditWindow button[name=save]':{
				click:this.save
			},//删除
			'bdef_DefAppendixUI button[name=detailDelete]':{
				click:this.detailDelete
			},//GRID选择
			'bdef_DefAppendixUI grid[id=grid_01_1B01]':{
				selectionchange:this.grid_01_1B01change
			},//修改
			'bdef_DefAppendixUI button[name=detailEdit]':{
				click:this.detailEdit
			},//查找	
			'bdef_DefAppendixUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'bdef_DefAppendixAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_DefAppendixAddOrEditWindow button[name=next]':{
				click:this.next
			},//新增前加载
			'bdef_DefAppendixAddOrEditWindow button[name=add]':{
				click:this.add
			},//关闭窗口
			'bdef_DefAppendixAddOrEditWindow button[name=close]':{
				click:this.close
			}
		});
	},
	
	//新增按钮
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_DefAppendixAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addAppendix1B01();
		commonSetMsterReadOnlyByArray(
				new Array('owner1B01','fileName1B01'),false);
		addCommMenu5('menuWidget51B01');
		saveType1B01='add';
	},
	//保存
	save:function(){
		saveAppendix1B01();
	},
	
	//删除
	detailDelete:function(){
		var data=Ext.getCmp('grid_01_1B01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);	
		}else{			
			Ext.Ajax.request({
				url:'bdef_DefAppendixAction_delete',
				method:'post',
				params:{
					ownerNo:data[0].get('ownerNo'),
					name:data[0].get('fileName')
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_01_1B01').getStore().load();
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}
	},
	
	//选择网格
	grid_01_1B01change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			if(record.data.type=='2'){
				Ext.get('browseImage').dom.src =record.data.filePath;
			}else{
				Ext.get('browseImage').dom.src ="";
			}
		}
	},
	
	//修改
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1B01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_DefAppendixAddOrEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			rowindex1B01=data[0].index;
			loadAppendix1B01(rowindex1B01);
			commonSetMsterReadOnlyByArray(
					new Array('owner1B01','fileName1B01'),true);
			commonSetCommMenu5PrevOrNext('menuWidget51B01','grid_01_1B01',rowindex1B01);
			updateCommMenu5('menuWidget51B01');
			saveType1B01='edit';
		}		
	},
	
	//查找
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1B01';
		queryGrid='grid_01_1B01';
	},
	
	//上一页
	prev:function(){
		rowindex1B01=rowindex1B01-1;
		loadAppendix1B01(rowindex1B01);
		commonSetCommMenu5PrevOrNext('menuWidget51B01','grid_01_1B01',rowindex1B01);
	},
	
	//下一页
	next:function(){
		rowindex1B01=rowindex1B01+1;
		loadAppendix1B01(rowindex1B01);
		commonSetCommMenu5PrevOrNext('menuWidget51B01','grid_01_1B01',rowindex1B01);
	},
	
	add:function(){
		addAppendix1B01();
		saveType1B01='add';
	},
	
	
	
	close:function(){
		Ext.getCmp('bdef_DefAppendixAddOrEditWindow').close();
	}
});

//新增前加载
function addAppendix1B01(){
	Ext.getCmp('form_01_1B01').getForm().reset();
};

//填充数据
function loadAppendix1B01(rowindex1B01){
	var data=Ext.getCmp('grid_01_1B01').getStore().getAt(rowindex1B01-(Ext.getCmp('grid_01_1B01').getStore().currentPage-1)*appConfig.getPageSize());
		
	Ext.getCmp('owner1B01').setValue(data.data.ownerNo);
	Ext.getCmp('fileName1B01').setValue(data.data.fileName);
	Ext.getCmp('type1B01').setValue(data.data.type);
	Ext.getCmp('relateOrderNo1B01').setValue(data.data.relateOrderno);	
	Ext.getCmp('relateClass1B01').setValue(data.data.relateClass);
	Ext.getCmp('meno1B01').setValue(data.data.meno);
	
};

//保存
function saveAppendix1B01(){	
	var form =Ext.getCmp('form_01_1B01').getForm();	
	if(form.isValid()){
		if(saveType1B01=='add'){
			if(Ext.isEmpty(Ext.getCmp('uploadFile1B01').getValue()))
			{
				Ext.example.msg($i18n_prompt.prompt,'请选择导入文件！');       
				return;
			}
			
			form.submit({
	            url: 'bdef_DefAppendixAction_save',
	            success: function(fp, o) {
	            	var data = Ext.decode(o.response.responseText);
	            	Ext.getCmp('grid_01_1B01').getStore().load();
	            	Ext.example.msg($i18n_prompt.prompt,data.msg); 
	        	},  
	            failure : function (fp,o){ 
	            	var data = Ext.decode(o.response.responseText);
	            	Ext.example.msg($i18n_prompt.prompt,data.msg); 
	            	Ext.getCmp('grid_01_1B01').getStore().load();
	            }
	        });
		}else{
			form.submit({
	            url: 'bdef_DefAppendixAction_update',
	            success: function(fp, o) {
	            	var data = Ext.decode(o.response.responseText);
	            	Ext.getCmp('grid_01_1B01').getStore().load();
	            	Ext.example.msg($i18n_prompt.prompt,data.msg); 
	        	},  
	            failure : function (fp,o){ 
	            	var data = Ext.decode(o.response.responseText);
	            	Ext.getCmp('grid_01_1B01').getStore().load();
	            	Ext.example.msg($i18n_prompt.prompt,data.msg); 
	            }
	        });
		}       
	}
};