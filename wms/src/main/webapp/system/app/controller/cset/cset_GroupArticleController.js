/**
 * 模块名称：类别储位对应关系
 * 模块编码：2401
 * 创建：hekl
 */
Ext.define('cms.controller.cset.cset_GroupArticleController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cset.cset_GroupArticleUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//新增
			'cset_GroupArticleUI button[name=detailAdd]':{
	    		click:this.detailAdd
	    	},
	    	//查找
	    	'cset_GroupArticleUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			//选择商品类别，带出类别信息
	    	'cset_GroupArticleAddOrEditWindow form combo[id=article_group2401]':{
				beforequery:this.article_groupbeforequery,
				select : this.article_group2401select,
				blur:this.article_group2401Blur
			},
			//修改
			'cset_GroupArticleUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//浏览
			'cset_GroupArticleUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			
			//上一条
			'cset_GroupArticleAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条
			'cset_GroupArticleAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//关闭
			'cset_GroupArticleAddOrEditWindow button[name=close]':{
				click:this.close
			},
			//保存
			'cset_GroupArticleAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//窗口-->新增
			'cset_GroupArticleAddOrEditWindow button[name=add]':{
				click:this.add2401window
			},
			//线路前加载
			'cset_LineCombo[id=cmbLineId2401]':{
				focus:this.cmbLineId2401Focus
			},
			//窗口-->改变库区编码
		    'cset_GroupArticleAddOrEditWindow cdef_DefWareCombo[id=ware_no2401]':{
				select:this.ware_no2401change
			},
			//窗口-->改变储区编码操作
			'cset_GroupArticleAddOrEditWindow cdef_DefAreaCombo[id=area_no2401]':{
				select:this.area_no2401change
			},
			//主键校验
			'cset_GroupArticleAddOrEditWindow textfield[id=owner_no2401]':{
				blur:this.owner_no2401Blur
			},
			'cset_GroupArticleAddOrEditWindow textfield[id=o_type2401]':{
				blur:this.o_type2401Blur,
				select:this.o_type2401Select
			},//储位选择事件
			'cset_GroupArticleAddOrEditWindow cdef_DefCellCombo[id=cell_no2401]':{
				beforequery : this.cell_no2401beforequery
			}
		});
	},
	
	initializtion:function()
	{
		Ext.getCmp('menu2401').items.items[3].setVisible(false);
	},
	
	
	//窗口-->新增
 	add2401window:function()
 	{
 		Ext.getCmp('cset_Group_ArticleAddOrEditForm').getForm().reset();
 		Ext.getCmp('grid_01_2401').getStore().load();
		commonSetMsterReadOnlyByArray(
			new Array('owner_no2401','o_type2401'),false);
		if(Ext.getCmp('owner_no2401').getStore().data.length>0)
		{
		Ext.getCmp('owner_no2401').setValue(Ext.getCmp('owner_no2401').getStore().getAt(0).data.value);	
		}
		Ext.getCmp('owner_no2401').focus(false, 10);
		bindEnterSkip($('#cset_Group_ArticleAddOrEditForm'));//调用键盘处理方法
		button2401='add';
 	},
 	
 	/**
	 * 查找
	 */
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=2401;
		queryGrid='grid_01_2401';					
	},
 	
 	/**
	 * 储位选择事件
	 * @param {} th
	 * @param {} newValue
	 * @param {} oldValue
	 * @param {} eOpts
	 */
	ware_no2401change:function(th,newValue,oldValue,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'a.ware_no',
			value:Ext.getCmp('ware_no2401').getValue()
		};
		listDetail1.push(d);
		if(!Ext.isEmpty(Ext.getCmp('o_type2401').getValue())){
			var d={
			columnId:'a.o_type',
			value:Ext.getCmp('o_type2401').getValue()
			};
			listDetail1.push(d);
		}
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('area_no2401')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('area_no2401').getStore().load();
		if(Ext.getCmp('area_no2401').getStore().load().totalCount==0){
			Ext.example.msg('提示', "在对应的拣货类型和库区下没有储区，请重新选择");
			Ext.getCmp('area_no2401').setValue('');
			Ext.getCmp('stock_no2401').setValue('');
			Ext.getCmp('cell_no2401').setValue('');
		}
	},

	/**
	 * 储位、储区下拉选择事件
	 * @param {} th
	 * @param {} newValue
	 * @param {} oldValue
	 * @param {} eOpts
	 */
	area_no2401change:function(th,newValue,oldValue,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('ware_no2401').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('area_no2401').getValue()
		};
		listDetail1.push(d);
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('stock_no2401')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('stock_no2401').getStore().load();
	},
	//线路前加载
	cmbLineId2401Focus:function(queryEvent,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'cabd.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.ware_No',
			value:Ext.getCmp('ware_no2401').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.area_no',
			value:Ext.getCmp('area_no2401').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var strwheresql = {
			str : strJson
		};
		Ext.getCmp('cmbLineId2401').getStore().proxy.extraParams.str = strJson;
	},
	//储位前加载
	cell_no2401beforequery:function(queryEvent,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('ware_no2401').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('area_no2401').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.stock_no',
			value:Ext.getCmp('stock_no2401').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var wheresql = {
			str : strJson
		};
		Ext.getCmp('cell_no2401').getStore().proxy.extraParams.str = strJson;
	},
 
 	
 	   //自动传入货主
 		article_groupbeforequery:function(){
		Ext.getCmp('article_group2401').getStore().proxy.extraParams.strOwnerNo = Ext
		.getCmp('owner_no2401').getValue();
		},
 	
 	/*
	 * 新增商品储位对应关系
	 *
	 */
 		detailAdd:function(){
			Ext.create('cms.view.cset.window.cset_GroupArticleAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			Ext.getCmp('barcode2401').setVisible(false);
			Ext.getCmp('article_no2401').setVisible(false);
			Ext.getCmp('article_name2401').setVisible(false);
			Ext.getCmp('article_group_no2401').setVisible(false);
			Ext.getCmp('article_group_name2401').setVisible(false);
			Ext.getCmp('packing_qty2401').setVisible(false);
			
			addCset_Cell_Article();
			
			commonSetMsterReadOnlyByArray(
				new Array('group_no2401','group_name2401'
				),true);
			addCommMenu5('menuWidget52401');
			Ext.getCmp('owner_no2401').focus(false, 10);
			bindEnterSkip($('#cset_Group_ArticleAddOrEditForm'));//调用键盘处理方法
			button2401='add';
	},

	//选择业主商品编码，带出商品信息
	article_group2401select:function(combo,records,eOpts){
	
		Ext.getCmp("group_no2401").setValue(records[0].data.value);
		Ext.getCmp("group_name2401").setValue(records[0].data.text);	
	
	},
	/**
	 * 修改商品储位对应关系
	 *
	 */
	detailEdit:function(){	
		var data = Ext.getCmp('grid_01_2401').getSelectionModel().getSelection();
			if (data.length != 0) {
				if(typeof(Ext.getCmp('cset_GroupArticleAddOrEditWindow'))=='undefined'){
					Ext.create('cms.view.cset.window.cset_GroupArticleAddOrEditWindow',{
						title:$i18n.titleupdate
					}).show(); 
					
					Ext.getCmp('article_group2401').setVisible(false);
					Ext.getCmp('group_no2401').setVisible(false);
					Ext.getCmp('group_name2401').setVisible(false);
					
					rowindex2401=data[0].index;
					loadCset_Group_Article(rowindex2401);
					commonSetMsterReadOnlyByArray(
							new Array('o_type2401'),true);
					commonSetFormReadOnly('up2401window',true);
					packingQtybefore();
					commonSetCommMenu5PrevOrNext('menuWidget52401','grid_01_2401',rowindex2401);
					updateCommMenu5('menuWidget52401');
				}
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
		button2401='update';
	},
	/**
	 * 浏览商品储位对应关系
	 * 
	 */
	detailBrowse:function(){	
		if(Ext.getCmp('grid_01_2401'))
		{
			var data = Ext.getCmp('grid_01_2401').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cset.window.cset_GroupArticleAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				Ext.getCmp('article_group2401').setVisible(false);
				Ext.getCmp('group_no2401').setVisible(false);
				Ext.getCmp('group_name2401').setVisible(false);
				
				rowindex2401=data[0].index;
				loadCset_Group_Article(rowindex2401);
				commonSetFormReadOnly('up2401window',true);
				commonSetFormReadOnly('down2401window',true);
				commonSetCommMenu5PrevOrNext('menuWidget52401','grid_01_2401',rowindex2401);
				browseCommMenu5('menuWidget52401');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
		
	},
	

	/*
	 * 上一条记录
	 * @param {} th
	 */
	prev:function(th){
		rowindex2401=rowindex2401-1;
		loadCset_Group_Article(rowindex2401);
		commonSetCommMenu5PrevOrNext('menuWidget52401','grid_01_2401',rowindex2401);
	},
	/**
	 * 下一条记录
	 * @param {} th
	 */
	next:function(th){
		rowindex2401=rowindex2401+1;
		loadCset_Group_Article(rowindex2401);
		commonSetCommMenu5PrevOrNext('menuWidget52401','grid_01_2401',rowindex2401);
	},
	
	//保存
	save:function(th){
		saveCset_Group_Article(th);
	},
	
	/**
	 * 关闭
	 * @param {} th
	 */
	close:function(th){
		closeWindow();
	},
	
	owner_no2401Blur:function(th){
		blurvar='owner_no';		
		cset_group_article2401Blur(th);
	},
	
	article_group2401Blur:function(th){
		blurvar='group_no';	
		cset_group_article2401Blur(th);
	},
	
	o_type2401Blur:function(th){
		blurvar='pick_type';
		cset_group_article2401Blur(th);
	},
	
	/*
	 * 拣货类型选择事件
	 */
	o_type2401Select:function(){
		
		var listDetail1 = [];
		var d={
			columnId:'a.o_type',
			value:Ext.getCmp("o_type2401").getValue()
		};
		listDetail1.push(d);
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('ware_no2401')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('ware_no2401').getStore().load();
		Ext.Ajax.request({
				url:'cset_GroupArticleAction_existsAreaList.action',
				params : {
					queryStr:Ext.encode(listDetail1)
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('area_no2401').setValue('');
						Ext.getCmp('stock_no2401').setValue('');
						Ext.getCmp('cell_no2401').setValue('');
					}
				}
			});
	},
	

});

/**
 * 新增商品储位对应关系初始化
 * 
 */
function addCset_Cell_Article()
{
	Ext.getCmp('cset_Group_ArticleAddOrEditForm').getForm().reset();
	if(Ext.getCmp('owner_no2401').getStore().data.length>0)
	{
		Ext.getCmp('owner_no2401').setValue(Ext.getCmp('owner_no2401').getStore().getAt(0).data.value);		
	}
}

/**
 * 保存商品储位对应关系
	
 */
function saveCset_Group_Article(th){
	var but=th;
	debugger;
	if(!Ext.getCmp('cset_Group_ArticleAddOrEditForm').getForm().isValid())
{		
		var str={
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				groupNo:Ext.getCmp('group_no2401').getValue(),
				articleNo:Ext.getCmp('article_no2401').getValue(),
				packingQty:Ext.getCmp('packing_qty2401').getValue(),
				ownerNo:Ext.getCmp('owner_no2401').getValue(),
				pickType:Ext.getCmp('o_type2401').getValue(),
				wareNo:Ext.getCmp('ware_no2401').getValue(),
				areaNo:Ext.getCmp('area_no2401').getValue(),
				stockNo:Ext.getCmp('stock_no2401').getValue(),
				AStockNo:Ext.getCmp('ware_no2401').getValue()+
				Ext.getCmp('area_no2401').getValue()+
				Ext.getCmp('stock_no2401').getValue(),
				//stockX:Ext.getCmp('cell_no2401').getValue().substring(4,5),
				cellNo:Ext.getCmp('cell_no2401').getValue(),
				lineId:Ext.getCmp('cmbLineId2401').getValue(),
				maxQtyA:Ext.getCmp('max_qty_na2401').getValue(),
				alertQtyA:Ext.getCmp('alert_qty_na2401').getValue(),
				suppQtyA:Ext.getCmp('supp_qty_na2401').getValue(),
				maxQtyNa:Ext.getCmp('max_qty_na2401').getValue(),
				alertQtyNa:Ext.getCmp('alert_qty_na2401').getValue(),
				suppQtyNa:Ext.getCmp('supp_qty_na2401').getValue(),
				
				keepCells:Ext.getCmp('keep_cells2401').getValue(),
				keepCellsA:Ext.getCmp('keep_cells2401').getValue(),
				
				
				rgstName:Ext.get('workerName').getValue(),
				rgstDate:new Date(),
				updtName:Ext.get('workerName').getValue(),
				updtDate:new Date()
		};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url:'cset_GroupArticleAction_saveOrUpdateCset_Group_Article.action',
			params : {
				str:jsonStr
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('grid_01_2401').getStore().load();
						commonSetMsterReadOnlyByArray(
							new Array('group_no2401','owner_no2401','o_type2401'),true);
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
	Ext.getCmp('cset_GroupArticleAddOrEditWindow').close();
}


/**
 * 填充商品储位对应关系数据
	 * zhouhuan
 */
function loadCset_Group_Article(rowindex2401)
{
	var record=Ext.getCmp('grid_01_2401').getStore().getAt(rowindex2401-
	(Ext.getCmp('grid_01_2401').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('owner_no2401').setValue(record.data.ownerNo);
	Ext.getCmp('article_group_no2401').setValue(record.data.groupNo);
	Ext.getCmp('article_group_name2401').setValue(record.data.groupName);
	Ext.getCmp('ware_no2401').getStore().add({
    	value:record.data.wareNo,
    	dropValue:"["+record.data.wareNo+"]"+record.data.wareName,
    	text:record.data.wareName
    });
	Ext.getCmp('ware_no2401').setValue(record.data.wareNo);
	Ext.getCmp('area_no2401').getStore().add({
    	value:record.data.areaNo,
    	dropValue:"["+record.data.areaNo+"]"+record.data.areaName,
    	text:record.data.areaName
    });
	Ext.getCmp('area_no2401').setValue(record.data.areaNo);
	Ext.getCmp('stock_no2401').setValue(record.data.stockNo);
	Ext.getCmp('cell_no2401').getStore().add({
    	value:record.data.cellNo,
    	dropValue:record.data.cellNo,
    	text:record.data.cellNo
    });
	Ext.getCmp('cell_no2401').setValue(record.data.cellNo);
	Ext.getCmp('barcode2401').setValue(record.data.barcode);
	Ext.getCmp('article_no2401').setValue(record.data.articleNo);
	Ext.getCmp('article_name2401').setValue(record.data.articleName);
	Ext.getCmp('packing_qty2401').getStore().add({
    	value:String(record.data.packingQty),
    	dropValue:String(record.data.packingQty),
    	text:String(record.data.packingQty)
    });
	Ext.getCmp('packing_qty2401').setValue(String(record.data.packingQty));
	Ext.getCmp('cmbLineId2401').getStore().add({
    	value:record.data.lineId,
    	dropValue:"["+record.data.lineId+"]"+record.data.lineName,
    	text:record.data.lineName
    });
	Ext.getCmp('cmbLineId2401').setValue(record.data.lineId);
	Ext.getCmp('o_type2401').setValue(record.data.pickType);
	Ext.getCmp('keep_cells2401').setValue(String(record.data.keepCells));
	Ext.getCmp('max_qty_na2401').setValue(record.data.maxQtyBox);
	Ext.getCmp('supp_qty_na2401').setValue(record.data.suppQtyBox);
	Ext.getCmp('alert_qty_na2401').setValue(record.data.alertQtyBox);
//	Ext.getCmp('keep_cells_a2401').setValue(record.data.keepCellsA);
//	Ext.getCmp('max_qty_a2401').setValue(record.data.maxQtyA);
//	Ext.getCmp('supp_qty_a2401').setValue(record.data.suppQtyA);
//	Ext.getCmp('alert_qty_a2401').setValue(String(record.data.alertQtyA));
}

//校验是否重复
function cset_group_article2401Blur(th){
	if(button2401=='add'){
		if(!Ext.isEmpty(Ext.getCmp("owner_no2401").getValue())
	   && !Ext.isEmpty(Ext.getCmp("group_no2401").getValue())
	   && !Ext.isEmpty(Ext.getCmp("o_type2401").getValue()))
	{
		var listDetail1 = [];
		var p={
			columnId:'a.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
		};
		listDetail1.push(p);
			var p={
			columnId:'a.owner_no',
			value:Ext.getCmp("owner_no2401").getValue()
		};
		listDetail1.push(p);
		
		var p={
			columnId:'a.pick_type',
			value:Ext.getCmp("o_type2401").getValue()
		};
		listDetail1.push(p);
		var strGroupNo={
				strGroupNo :Ext.getCmp("group_no2401").getValue()
			};
		if(!Ext.isEmpty(th.getValue())){
			Ext.Ajax.request({
			url:'cset_GroupArticleAction_existsCsetCellArticle.action',
			params : {
				strQuery:Ext.encode(listDetail1),
				strGroupNo:strGroupNo
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					if(blurvar=='owner_no'){
						Ext.getCmp('owner_no2401').setValue('');
					}else if(blurvar=='group_no'){
						Ext.getCmp('group_no2401').setValue('');
						Ext.getCmp('article_group2401').setValue('');
						Ext.getCmp('group_name2401').setValue('');
					}else if(blurvar=='pick_type'){
						Ext.getCmp('o_type2401').setValue('');
					}
				}
			}
		});
		}
	}
		
	}		
}
	
/*
 * 获得对应商品的包装数量下拉
 */
function packingQtybefore(){
	var strArticleNo = Ext.getCmp('article_no2401').getValue();
	var strWheresql={
		strWheresql:strArticleNo
	};
	Ext.apply(Ext.getCmp('packing_qty2401').getStore().proxy.extraParams,strWheresql);
	Ext.getCmp('packing_qty2401').getStore().removeAll();
	Ext.getCmp('packing_qty2401').getStore().load();
}


