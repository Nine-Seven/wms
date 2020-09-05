Ext.define('cms.controller.cset.cset_DeflocController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cset.cset_DeflocUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//新增
			'cset_DeflocUI button[name=detailAdd]':{
	    		click:this.detailAdd
	    	},
	    	//查找
	    	'cset_DeflocUI button[name=detailQuery]':{
				click:this.detailQuery
			},		
			//修改
			'cset_DeflocUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//浏览
			'cset_DeflocUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
		
			
			//删除
			'cset_DeflocUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			
			
			
			//上一条
			'cset_DeflocAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条
			'cset_DeflocAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//关闭
			'cset_DeflocAddOrEditWindow button[name=close]':{
				click:this.close
			},
			//保存
			'cset_DeflocAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//新增
			'cset_DeflocAddOrEditWindow button[name=add]':{
				click:this.add2501window
			}
				
	
		});
	},
	

	//新增
 	add2501window:function()
 	{
 		Ext.getCmp('cset_DeflocAddOrEditWindow2501').getForm().reset();
 		Ext.getCmp('enterpriseNo2501').setValue(Ext.get('enterpriseNo').getValue());
		button2501='add';
 	},
 	
 	// 查找
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=2501;
		queryGrid='grid_01_2501';					
	},
 	

 	// 新增仓别
	
 		detailAdd:function(){
			Ext.create('cms.view.cset.window.cset_DeflocAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			
			Ext.getCmp('enterpriseNo2501').setValue(Ext.get('enterpriseNo').getValue());	
			addCommMenu5('menuWidget52501');		
			button2501='add';
	},

	// 修改	
	detailEdit:function(){	
		var data = Ext.getCmp('grid_01_2501').getSelectionModel().getSelection();
			if (data.length != 0) {
					Ext.create('cms.view.cset.window.cset_DeflocAddOrEditWindow',{
						title:$i18n.titleupdate
					}).show(); 
					rowindex2501=data[0].index;
					
					loadDate(rowindex2501);
					commonSetMsterReadOnlyByArray(
							new Array('warehouseNo2501','enterpriseNo2501'),true);
					
					updateCommMenu5('menuWidget52501');
				
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
		button2501='update';
	},
	
	//浏览	
	detailBrowse:function(){	
			var data = Ext.getCmp('grid_01_2501').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cset.window.cset_DeflocAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex2501=data[0].index;
				loadDate(rowindex2501);
				
				commonSetMsterReadOnlyByArray(
						new Array('warehouseNo2501','enterpriseNo2501','warehouseName2501',
								'creatFlag2501','linkman2501','tel2501','manageName2501',
								'province2501','city2501','zone2501','address2501','memo2501'),true);
				
				browseCommMenu5('menuWidget52501');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		
	},
	/**
	 * 删除商品储位对应关系
	 * zhouhuan
	 */
	detailDelete:function(){
		var data = Ext.getCmp('grid_01_2501').getSelectionModel().getSelection();  
        if (data.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.getCmp('grid_01_2501').getStore().remove(data);
    					Ext.Ajax.request({
    						url:'cset_Cell_ArticleAction_delete.action',
    						params :{
    							strEnterpriseNo:Ext.get('enterpriseNo').getValue(),
    							strWarehouseNo:Ext.get("warehouseNo").getValue(),
    							strOwnerNo: data[0].get("ownerNo"),
    							strArticleNo: data[0].get("articleNo"),
    							strPickType: data[0].get("pickType")
    						},
    						success : function(response) {
    						Ext.getCmp('grid_01_2501').getStore().load();
    					}
    					});
    				}			
            });
        }
	},

	/*
	 * 上一条记录
	 * @param {} th
	 */
	prev:function(th){
		rowindex2501=rowindex2501-1;
		loadDate(rowindex2501);
		commonSetCommMenu5PrevOrNext('menuWidget52501','grid_01_2501',rowindex2501);
	},
	/**
	 * 下一条记录
	 * @param {} th
	 */
	next:function(th){
		rowindex2501=rowindex2501+1;
		loadDate(rowindex2501);
		commonSetCommMenu5PrevOrNext('menuWidget52501','grid_01_2501',rowindex2501);
	},
	
	//保存
	save:function(){
		saveDefloc();
	},
	
	// 关闭
	close:function(){
		closeWindow();
	},
	
});


/**
 * 保存仓别
 */
function saveDefloc(th){

	if(Ext.getCmp('cset_DeflocAddOrEditWindow2501').getForm().isValid())
	{		
		var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.getCmp('warehouseNo2501').getValue()
				},
				warehouseName:Ext.getCmp('warehouseName2501').getValue(),
				createFlag:Ext.getCmp('creatFlag2501').getValue(),
				linkman:Ext.getCmp('linkman2501').getValue(),
				tel:Ext.getCmp('tel2501').getValue(),
				manageName:Ext.getCmp('manageName2501').getValue(),
				province:Ext.getCmp('province2501').getValue(),
				city:Ext.getCmp('city2501').getValue(),
				zone:Ext.getCmp('zone2501').getValue(),
				adress:Ext.getCmp('address2501').getValue(),
				rgstName:Ext.get('workerName').getValue(),
				rgstDate:new Date(),
				updtName:Ext.get('workerName').getValue(),
				updtDate:new Date()
		};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url:'cset_deflocAction_saveOrUpdateDefloc.action',
			params : {
				str:jsonStr
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示','保存成功');
					Ext.getCmp('grid_01_2501').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
}

/**
 * 关闭界面
 */
function closeWindow(){
	Ext.getCmp('cset_DeflocAddOrEditWindow').close();
}


/**
 *填充数据
 */
function loadDate(rowindex2501)
{
	var record=Ext.getCmp('grid_01_2501').getStore().getAt(rowindex2501-(Ext.getCmp('grid_01_2501').getStore().currentPage-1)*appConfig.getPageSize());	
	Ext.getCmp('enterpriseNo2501').setValue(Ext.get('enterpriseNo').getValue());
	Ext.getCmp('warehouseNo2501').setValue(record.data.warehouseNo);
	Ext.getCmp('warehouseName2501').setValue(record.data.warehouseName);
	Ext.getCmp('creatFlag2501').setValue(record.data.createFlag);
	Ext.getCmp('linkman2501').setValue(record.data.linkman);
	Ext.getCmp('tel2501').setValue(record.data.tel);
	Ext.getCmp('manageName2501').setValue(record.data.manageName);
	Ext.getCmp('province2501').setValue(record.data.province);
	Ext.getCmp('address2501').setValue(record.data.adress);
	
	Ext.getCmp('city2501').setValue(record.data.city);
	Ext.getCmp('zone2501').setValue(record.data.zone);
	Ext.getCmp('memo2501').setValue(record.data.memo);
}
