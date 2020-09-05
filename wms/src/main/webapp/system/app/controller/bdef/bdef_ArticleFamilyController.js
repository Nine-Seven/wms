/**
 * 模块名称：商品群组关系维护
 * 模块编码：1R01
 * 创建：hekl
 */
var rowindex1R01=0;
var saveType1R01='add';
Ext.define('cms.controller.bdef.bdef_ArticleFamilyController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_ArticleFamilyUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'bdef_ArticleFamilyUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'bdef_ArticleFamilyUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bdef_ArticleFamilyUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'bdef_ArticleFamilyUI button[name=detailQuery]':{
				click:this.detailQuery
			},//GRID选择
			'bdef_ArticleFamilyUI grid[id=grid_01_1R01]':{
				selectionchange:this.grid_01_1R01change
			},//上一条记录
			'bdef_ArticleFamilyWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_ArticleFamilyWindow button[name=next]':{
				click:this.next
			},//新增前加载
			'bdef_ArticleFamilyWindow button[name=add]':{
				click:this.add
			},//保存
			'bdef_ArticleFamilyWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bdef_ArticleFamilyWindow button[name=close]':{
				click:this.close
			},//商品群组添加商品
			'bdef_ArticleFamilyUI button[id=right1R01]':{
				click:this.right1R01
			},//商品群组移除商品
			'bdef_ArticleFamilyUI button[id=left1R01]':{
				click:this.left1R01
			},//验证商品群组代码唯一性
			'bdef_ArticleFamilyWindow form textfield[id=article_family_no1R01]':{
				blur:this.articleFamilyNoBlur
			},//商品列表》商品类别选中
			'bdef_ArticleFamilyUI combo[id=cmbGroupNo1R01]':{
				select:this.cmbGroupNo1R01Select
			},//商品列表》商品回车
			'bdef_ArticleFamilyUI textfield[id=txtOwnerArticleNo1R01]':{
				blur:this.cmbArticle1R01change
			},//enter键事件
			'bdef_ArticleFamilyUI field':{
				specialkey:this.boxkeydown
			},//商品列表》刷新
			'bdef_ArticleFamilyUI button[name=refresh]':{
				click:this.TabpanelChange
			},//商品编码选择
			'remoteCombo[id=txtOwnerArticleNo1R01]':{
				beforequery:this.articleNoBeforeQuery,
				select:this.articleNoSelect
			},//导入
			'bdef_ArticleFamilyUI button[name=detailImport]':{
				click:this.detailImportClick
			}
		});
	},
	
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_ArticleFamilyWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addFamily1R01();
		addCommMenu5('menuWidget51R01');
		saveType1R01='add';
	},
	
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1R01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_ArticleFamilyWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			rowindex1R01=data[0].index;
			loadFamily1R01(rowindex1R01);
			commonSetCommMenu5PrevOrNext('menuWidget51R01','grid_01_1R01',rowindex1R01);
			Ext.getCmp('article_family_no1R01').disable();
			updateCommMenu5('menuWidget51R01');
			saveType1R01='edit';
		}
		
	},
	
	//商品与商品群组关系维护信息-刷新   7-9添加  hj
	TabpanelChange:function(){
		//this.refresh();
		//debugger;
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('grid_01_1R01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_01_1R01').getStore().removeAll();
		Ext.getCmp('grid_01_1R01').getStore().reload();
	},
	
	detailBrowse:function(){
		var data=Ext.getCmp('grid_01_1R01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
		}else{
			Ext.create('cms.view.bdef.window.bdef_ArticleFamilyWindow',{
				title:$i18n.titlebrowse
			}).show();
			rowindex1R01=data[0].index;
			loadFamily1R01(rowindex1R01);
			commonSetFieldReadOnly('bdef_ArticleFamilyWindow',true);
			commonSetCommMenu5PrevOrNext('menuWidget51R01','grid_01_1R01',rowindex1R01);
			browseCommMenu5('menuWidget51R01');
		}
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1R01';
		queryGrid='grid_01_1R01';
	},
	
	
	grid_01_1R01change:function(th,selected,eOpts){	
		if(selected.length!=0){
			record=selected[0];
			var strDetail1 = [];
			var d1={
					columnId:'a.owner_no',
					value:record.data.ownerNo
				};
				strDetail1.push(d1);
			var jsonDetail = Ext.encode(strDetail1);
			var wheresql = {
					str  : jsonDetail,
					wheresql:record.data.familyNo
			};
			
			Ext.apply(Ext.getCmp('grid_03_1R01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_1R01').getStore().removeAll();
			Ext.getCmp('grid_03_1R01').getStore().load();
			
			Ext.getCmp('cmbGroupNo1R01').setValue(null);
			Ext.getCmp('cmbGroupNo1R01').getStore().proxy.extraParams.ownerNo = record.data.ownerNo;
			Ext.getCmp('cmbGroupNo1R01').getStore().removeAll();
			Ext.getCmp('cmbGroupNo1R01').getStore().reload();
			
			Ext.getCmp('txtOwnerArticleNo1R01').setValue(null);
			Ext.getCmp('txtOwnerArticleNo1R01').getStore().proxy.extraParams.ownerNo = record.data.ownerNo;
			Ext.getCmp('txtOwnerArticleNo1R01').getStore().removeAll();
			Ext.getCmp('txtOwnerArticleNo1R01').getStore().reload();
			
			Ext.getCmp('grid_02_1R01').getStore().proxy.extraParams.wheresql = record.data.familyNo;
			Ext.getCmp('grid_02_1R01').getStore().proxy.extraParams.ownerNo = record.data.ownerNo;
			Ext.getCmp('grid_02_1R01').getStore().proxy.extraParams.str = null;
			Ext.getCmp('grid_02_1R01').getStore().proxy.extraParams.strQuery = null;
			Ext.getCmp('grid_02_1R01').getStore().removeAll();
			Ext.getCmp('grid_02_1R01').getStore().reload();
			
		}else{
			Ext.getCmp('grid_03_1R01').getStore().removeAll();
		}
	},
	
	prev:function(){
		rowindex1R01=rowindex1R01-1;
		loadFamily1R01(rowindex1R01);
		commonSetCommMenu5PrevOrNext('menuWidget51R01','grid_01_1R01',rowindex1R01);
	},
	
	next:function(){
		rowindex1R01=rowindex1R01+1;
		loadFamily1R01(rowindex1R01);
		commonSetCommMenu5PrevOrNext('menuWidget51R01','grid_01_1R01',rowindex1R01);
	},
	
	add:function(){
		addFamily1R01();
		saveType1R01='add';
	},
	
	save:function(){
		saveFamily1R01();
	},
	
	close:function(){
		Ext.getCmp('bdef_ArticleFamilyWindow').close();
	},
	
	right1R01:function(){
		var grid01=Ext.getCmp('grid_01_1R01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_02_1R01').getSelectionModel().getSelection();
		var articleNo = "";
		var flag="";
		for(var i=0;i<grid02.length;i++){
			if(articleNo.indexOf(grid02[i].get('articleNo')) != -1 && articleNo != ""){
				//alert(grid02[i].get('articleNo'));
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt1);
				return;
			}
			articleNo += grid02[i].get('articleNo');
		}
		
		if(flag=='' || flag==null){
			if(grid01.length!=0){
				if(grid02.length!=0){
					var detail=[];
					for(var i=0;i<grid02.length;i++){
						var articleFamily={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								ownerNo:grid01[0].get('ownerNo'),
								familyNo:grid01[0].get('familyNo'),
								articleNo:grid02[i].get('articleNo')
							},
							rgstName:Ext.get('workerNo').getValue(),
							rgstDate:new Date()
						};
						if(Ext.getCmp('grid_03_1R01').getStore().findExact('articleNo',grid02[i].get('articleNo'))=='-1'){
							detail.push(articleFamily);
						}
					}
					var str=Ext.encode(detail);
					Ext.Ajax.request({
						url:'bdef_ArticleFamilyAction_saveArticle_Family',
						params:{
							str:str
						},
						success:function(response){
							Ext.getCmp('grid_02_1R01').getStore().load();
							Ext.getCmp('grid_03_1R01').getStore().load();
						}
					});
				}else{
					Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
				}
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
			}
		}
	},
	
	left1R01:function(){
		var grid01=Ext.getCmp('grid_01_1R01').getSelectionModel().getSelection();
		var grid03=Ext.getCmp('grid_03_1R01').getSelectionModel().getSelection();
		if(grid01.length!=0){
			if(grid03.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				for(var i=0;i<grid03.length;i++){
					detail1.push(grid01[0].get('ownerNo'));
					detail2.push(grid03[i].get('familyNo'));
					detail3.push(grid03[i].get('articleNo'));
				}
				
				Ext.Ajax.request({
					url:'bdef_ArticleFamilyAction_deleteArticle_Family',
					params:{
						ownerNo:detail1,
						familyNo:detail2,
						articleNo:detail3
					},
					success:function(response){
						Ext.getCmp('grid_02_1R01').getStore().load();
						Ext.getCmp('grid_03_1R01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	},
	
	articleFamilyNoBlur:function(){
		if(saveType1R01=='add'){
			Ext.Ajax.request({
				method:'post',
				url:'bdef_ArticleFamilyAction_checkArticleFamilyNo',
				params:{
					ownerNo:Ext.getCmp('owner1R01').getValue(),
					familyNo:Ext.getCmp('article_family_no1R01').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg('提示','该群组代码已有，请重新录入！');
			    		Ext.getCmp('article_family_no1R01').setValue('');
			    		Ext.getCmp('article_family_no1R01').focus();
			    	}
			    }
			});
		}
	},
	cmbGroupNo1R01Select:function()
	{
		Ext.getCmp('txtOwnerArticleNo1R01').setValue('');
		loadgridArticle1R01();
	},
	cmbArticle1R01change:function()
	{
		loadgridArticle1R01();
	},
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=="txtOwnerArticleNo1R01"){
	  			this.cmbArticle1R01change();
	  		}else{
		  		th.nextSibling().focus();	  		
	  		}
        }
	},
	//商品编码加载 
	articleNoBeforeQuery:function(){
 		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbGroupNo1R01').getValue())){
			var d1={
					columnId:'a.group_no',
					value:Ext.getCmp('cmbGroupNo1R01').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				str:Ext.getCmp("txtOwnerArticleNo1R01").getValue(),   
				strQuery:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('txtOwnerArticleNo1R01').getStore().proxy.extraParams,params);
		Ext.getCmp('txtOwnerArticleNo1R01').getStore().removeAll();
		Ext.getCmp('txtOwnerArticleNo1R01').getStore().load();
  	  },
  	//商品编码选择  
  	articleNoSelect:function(){
  		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbGroupNo1R01').getValue())){
			var d1={
					columnId:'a.group_no',
					value:Ext.getCmp('cmbGroupNo1R01').getValue()
				};
			listDetail1.push(d1);
		}
 		if(!Ext.isEmpty(Ext.getCmp('txtOwnerArticleNo1R01').getValue())){
			var d1={
					columnId:'a.article_no',
					value:Ext.getCmp('txtOwnerArticleNo1R01').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				strQuery:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('grid_02_1R01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_02_1R01').getStore().removeAll();
		Ext.getCmp('grid_02_1R01').getStore().load();
  	 },
   	//导入
   	detailImportClick:function(){
 		Ext.create('cms.view.bdef.window.bdef_ArticleFamilyUploadWindow',
 		{
 			title:'上传'
 		}).show();
 	}
});

//新增前加载
function addFamily1R01(){
	
	Ext.getCmp('form_01_1R01').getForm().reset();
	Ext.getCmp('owner1R01').setValue('001');
	commonSetMsterReadOnlyByArray(
			new Array('owner1R01'),false);
};

//填充数据
function loadFamily1R01(rowindex1R01){
	var group=Ext.getCmp('grid_01_1R01').getStore().getAt(rowindex1R01-(Ext.getCmp('grid_01_1R01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('owner1R01').setValue(group.data.ownerNo);
	Ext.getCmp('article_family_no1R01').setValue(group.data.familyNo);
	Ext.getCmp('article_family_name1R01').setValue(group.data.familyName);
};

//保存
function saveFamily1R01(){
	 
	if(Ext.getCmp('form_01_1R01').getForm().isValid()){
		
		var group={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				ownerNo:Ext.getCmp('owner1R01').getValue(),
				familyNo:Ext.getCmp('article_family_no1R01').getValue()
			},
			familyName:Ext.getCmp('article_family_name1R01').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date()
		};
		var str=Ext.encode(group);
		Ext.Ajax.request({
			url:'bdef_ArticleFamilyAction_saveOrUpdateFamily',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1R01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	};
};

//加载商品列表参数传递
function loadgridArticle1R01()
{
	var cmbGroupNo1R01 = Ext.getCmp('cmbGroupNo1R01').getValue();
	var txtOwnerArticleNo1R01= Ext.getCmp('txtOwnerArticleNo1R01').getValue();
	var listDetail1 = [];
	
		if(!Ext.isEmpty(cmbGroupNo1R01))
		{
			var strdtl={
			columnId:'a.group_no',
			value:Ext.getCmp('cmbGroupNo1R01').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			strQuery : jsonStr,
			str:txtOwnerArticleNo1R01
		};
		Ext.apply(Ext.getCmp('grid_02_1R01')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('grid_02_1R01').getStore()
				.removeAll();
		Ext.getCmp('grid_02_1R01').getStore()
				.load();
	
	
};
