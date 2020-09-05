Ext.define('cms.controller.cset.cset_CellArticleController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cset.cset_CellArticleUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//新增
			'cset_CellArticleUI button[name=detailAdd]':{
	    		click:this.detailAdd
	    	},
	    	//查找
	    	'cset_CellArticleUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			//导出
			'cset_CellArticleUI button[name=detailExport]':{
				click:this.detailExport
			},
			//选择业主商品编码，带出商品信息
	    	'cset_CellArticleAddOrEditWindow form combo[id=owner_article_no2201]':{
				beforequery:this.owner_article_nobeforequery,
				select : this.owner_article_no2201select,
				blur:this.article_no2201Blur
			},
			//修改
			'cset_CellArticleUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//浏览
			'cset_CellArticleUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//删除
			'cset_CellArticleUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			//上一条
			'cset_CellArticleAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条
			'cset_CellArticleAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//关闭
			'cset_CellArticleAddOrEditWindow button[name=close]':{
				click:this.close
			},
			//保存
			'cset_CellArticleAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//窗口-->新增
			'cset_CellArticleAddOrEditWindow button[name=add]':{
				click:this.add2201window
			},
			//线路前加载（保拣线）
			'cset_LineCombo[id=cmbLineId2201]':{
				focus:this.cmbLineId2201Focus
			},
			//窗口-->改变库区编码
		    'cset_CellArticleAddOrEditWindow cdef_DefWareCombo[id=ware_no2201]':{
				select:this.ware_no2201change
			},
			//窗口-->改变储区编码操作
			'cset_CellArticleAddOrEditWindow cdef_DefAreaCombo[id=area_no2201]':{
				select:this.area_no2201change
			},
			//主键校验
			'cset_CellArticleAddOrEditWindow textfield[id=owner_no2201]':{
				blur:this.owner_no2201Blur
			},
			'cset_CellArticleAddOrEditWindow textfield[id=o_type2201]':{
				blur:this.o_type2201Blur,
				select:this.o_type2201Select
			},//储位选择事件
			'cset_CellArticleAddOrEditWindow cdef_DefCellCombo[id=cell_no2201]':{
				beforequery : this.cell_no2201beforequery,
				select:this.cell_no2201Select
			}
		});
	},
	
	initializtion:function()
	{
		Ext.getCmp('menu2201').items.items[3].setVisible(false);
	},
	
	/**
	 * 导出
	 */
	detailExport:function(){
		commExport('grid_01_2201');
	},
 	
	//窗口-->新增
 	add2201window:function()
 	{
 		Ext.getCmp('cset_Cell_ArticleAddOrEditForm').getForm().reset();
 		Ext.getCmp('grid_01_2201').getStore().load();
		commonSetMsterReadOnlyByArray(
			new Array('article_no2201','owner_no2201','o_type2201'),false);
		if(Ext.getCmp('owner_no2201').getStore().data.length>0)
		{
		Ext.getCmp('owner_no2201').setValue(Ext.getCmp('owner_no2201').getStore().getAt(0).data.value);	
		}
		Ext.getCmp('owner_no2201').focus(false, 10);
		bindEnterSkip($('#cset_Cell_ArticleAddOrEditForm'));//调用键盘处理方法
		button2201='add';
 	},
 	
 	/**
	 * 查找
	 */
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=2201;
		queryGrid='grid_01_2201';					
	},
 	
 	/**
	 * 储位选择事件
	 * @param {} th
	 * @param {} newValue
	 * @param {} oldValue
	 * @param {} eOpts
	 */
	ware_no2201change:function(th,newValue,oldValue,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'a.ware_no',
			value:Ext.getCmp('ware_no2201').getValue()
		};
		listDetail1.push(d);
		if(!Ext.isEmpty(Ext.getCmp('o_type2201').getValue())){
			var d={
			columnId:'a.o_type',
			value:Ext.getCmp('o_type2201').getValue()
			};
			listDetail1.push(d);
		}
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1,
			ownerNo:Ext.getCmp("owner_no2201").getValue()
		};
		Ext.apply(Ext.getCmp('area_no2201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('area_no2201').getStore().load();
		if(Ext.getCmp('area_no2201').getStore().load().totalCount==0){
			Ext.example.msg('提示', "在对应的拣货类型和库区下没有储区，请重新选择");
			Ext.getCmp('area_no2201').setValue('');
			Ext.getCmp('stock_no2201').setValue('');
			Ext.getCmp('cell_no2201').setValue('');
		}
	},

	/**
	 * 储位、储区下拉选择事件
	 * @param {} th
	 * @param {} newValue
	 * @param {} oldValue
	 * @param {} eOpts
	 */
	area_no2201change:function(th,newValue,oldValue,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('ware_no2201').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('area_no2201').getValue()
		};
		listDetail1.push(d);
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1,
			ownerNo:Ext.getCmp("owner_no2201").getValue()
		};
		Ext.apply(Ext.getCmp('stock_no2201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('stock_no2201').getStore().load();
	},
	//线路前加载
	cmbLineId2201Focus:function(queryEvent,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'cabd.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.ware_No',
			value:Ext.getCmp('ware_no2201').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.area_no',
			value:Ext.getCmp('area_no2201').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var strwheresql = {
			str : strJson
		};
		Ext.getCmp('cmbLineId2201').getStore().proxy.extraParams.str = strJson;
		Ext.getCmp('cmbLineId2201').getStore().load();
	},
	//储位前加载
	cell_no2201beforequery:function(queryEvent,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('ware_no2201').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('area_no2201').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.stock_no',
			value:Ext.getCmp('stock_no2201').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var wheresql = {
			str : strJson,
			ownerNo:Ext.getCmp("owner_no2201").getValue(),
			strArticle:Ext.getCmp("article_no2201").getValue()
		};
		Ext.apply(Ext.getCmp('cell_no2201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cell_no2201').getStore().load();
	},
 
 	
 	/*
 	 * 自动加载业主供应商列表
 	 */
 		owner_article_nobeforequery:function(){
		Ext.getCmp('owner_article_no2201').getStore().proxy.extraParams.strOwnerNo = Ext
		.getCmp('owner_no2201').getValue();
		},
 	
 	/*
	 * 新增商品储位对应关系
	 * zhouhuan
	 */
 		detailAdd:function(){
			Ext.create('cms.view.cset.window.cset_CellArticleAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			
			addCset_Cell_Article();
			
			commonSetMsterReadOnlyByArray(
				new Array('barcode2201','article_no2201',
				'article_name2201'),true);
			addCommMenu5('menuWidget52201');
			Ext.getCmp('owner_no2201').focus(false, 10);
			bindEnterSkip($('#cset_Cell_ArticleAddOrEditForm'));//调用键盘处理方法
			button2201='add';
	},

	//选择业主商品编码，带出商品信息
	owner_article_no2201select:function(combo,records,eOpts){
		Ext.Ajax.request({
			method : 'POST',
			url : 'cset_CellArticleAction_getCset_BarcodeAndArticleNoCombo.action',
			params : {
				wheresql:combo.getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
				console.log(res);
				if(res!=""){
					Ext.getCmp("barcode2201").setValue(res[0].barcode);
					Ext.getCmp("article_no2201").setValue(res[0].articleNo);	
					Ext.getCmp("article_name2201").setValue(res[0].articleName);
					packingQtybefore();
				}
			}
		});
	},
	/**
	 * 修改商品储位对应关系
	 * zhouhuan
	 */
	detailEdit:function(){	
		var data = Ext.getCmp('grid_01_2201').getSelectionModel().getSelection();
			if (data.length != 0) {
				if(typeof(Ext.getCmp('cset_CellArticleAddOrEditWindow'))=='undefined'){
					Ext.create('cms.view.cset.window.cset_CellArticleAddOrEditWindow',{
						title:$i18n.titleupdate
					}).show(); 
					rowindex2201=data[0].index;
					//packingQtybefore();
					loadCset_Cell_Article(rowindex2201);
					commonSetMsterReadOnlyByArray(
							new Array('o_type2201'),true);
					commonSetFormReadOnly('up2201window',true);
					
					this.ware_no2201change();
					commonSetCommMenu5PrevOrNext('menuWidget52201','grid_01_2201',rowindex2201);
					updateCommMenu5('menuWidget52201');
				}
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
		button2201='update';
	},
	/**
	 * 浏览商品储位对应关系
	 * zhouhuan
	 */
	detailBrowse:function(){	
		if(Ext.getCmp('grid_01_2201'))
		{
			var data = Ext.getCmp('grid_01_2201').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cset.window.cset_CellArticleAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex2201=data[0].index;
				loadCset_Cell_Article(rowindex2201);
				commonSetFormReadOnly('up2201window',true);
				commonSetFormReadOnly('down2201window',true);
				commonSetCommMenu5PrevOrNext('menuWidget52201','grid_01_2201',rowindex2201);
				browseCommMenu5('menuWidget52201');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
		
	},
	/**
	 * 删除商品储位对应关系
	 * zhouhuan
	 */
	detailDelete:function(){
		var data = Ext.getCmp('grid_01_2201').getSelectionModel().getSelection();  
        if (data.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.getCmp('grid_01_2201').getStore().remove(data);
    					Ext.Ajax.request({
    						url:'cset_CellArticleAction_delete.action',
    						params :{
    							strEnterpriseNo:Ext.get('enterpriseNo').getValue(),
    							strWarehouseNo:Ext.get("warehouseNo").getValue(),
    							strOwnerNo: data[0].get("ownerNo"),
    							strArticleNo: data[0].get("articleNo"),
    							strPickType: data[0].get("pickType")
    						},
    						success : function(response) {
    						Ext.getCmp('grid_01_2201').getStore().load();
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
		rowindex2201=rowindex2201-1;
		loadCset_Cell_Article(rowindex2201);
		commonSetCommMenu5PrevOrNext('menuWidget52201','grid_01_2201',rowindex2201);
	},
	/**
	 * 下一条记录
	 * @param {} th
	 */
	next:function(th){
		rowindex2201=rowindex2201+1;
		loadCset_Cell_Article(rowindex2201);
		commonSetCommMenu5PrevOrNext('menuWidget52201','grid_01_2201',rowindex2201);
	},
	
	//保存
	save:function(th){
		saveCset_Cell_Article(th);
	},
	
	/**
	 * 关闭
	 * @param {} th
	 */
	close:function(th){
		closeWindow();
	},
	
	owner_no2201Blur:function(th){
		blurvar='owner_no';		
		cset_cell_article2201Blur(th);
	},
	
	article_no2201Blur:function(th){
		blurvar='article_no';	
		cset_cell_article2201Blur(th);
	},
	
	o_type2201Blur:function(th){
		blurvar='pick_type';
		cset_cell_article2201Blur(th);
	},
	
	/*
	 * 拣货类型选择事件
	 */
	o_type2201Select:function(){
		
		var listDetail1 = [];
		var d={
			columnId:'a.o_type',
			value:Ext.getCmp("o_type2201").getValue()
		};
		listDetail1.push(d);
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1,
			ownerNo:Ext.getCmp("owner_no2201").getValue()
		};
		Ext.apply(Ext.getCmp('ware_no2201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('ware_no2201').getStore().load();
		
		Ext.Ajax.request({
				url:'cset_CellArticleAction_existsAreaList.action',
				params : {
					queryStr:Ext.encode(listDetail1)
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('area_no2201').setValue('');
						Ext.getCmp('stock_no2201').setValue('');
						Ext.getCmp('cell_no2201').setValue('');
					}
				}
			});
	},
	
	//储位选择
	cell_no2201Select:function(combo){
		Ext.Ajax.request({
			method:'post',
			async:false,
			url:'cset_CellArticleAction_queryStoctX',
			params:
			{
				wheresql:combo.getValue()
		    },
		    success:function(response)
		    {
		    	var data = Ext.decode(response.responseText);
		    	Ext.getCmp('stockX2201').setValue(data[0]);
		    }
		});
	}
});

/**
 * 新增商品储位对应关系初始化
 * zhouhuan
 */
function addCset_Cell_Article()
{
	Ext.getCmp('cset_Cell_ArticleAddOrEditForm').getForm().reset();
	if(Ext.getCmp('owner_no2201').getStore().data.length>0)
	{
		Ext.getCmp('owner_no2201').setValue(Ext.getCmp('owner_no2201').getStore().getAt(0).data.value);		
	}
}

/**
 * 保存商品储位对应关系
	 * zhouhuan
 */
function saveCset_Cell_Article(th){
	var but=th;
	if(Ext.getCmp('cset_Cell_ArticleAddOrEditForm').getForm().isValid()){		
		var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					articleNo:Ext.getCmp('article_no2201').getValue(),
					ownerNo:Ext.getCmp('owner_no2201').getValue(),
					pickType:Ext.getCmp('o_type2201').getValue()
				},
				wareNo:Ext.getCmp('ware_no2201').getValue(),
				areaNo:Ext.getCmp('area_no2201').getValue(),
				stockNo:Ext.getCmp('stock_no2201').getValue(),
				AStockNo:Ext.getCmp('ware_no2201').getValue()+
				Ext.getCmp('area_no2201').getValue()+
				Ext.getCmp('stock_no2201').getValue(),
				//stockX:Ext.getCmp('cell_no2201').getValue().substring(4,6),
				cellNo:Ext.getCmp('cell_no2201').getValue(),
				articleNo:Ext.getCmp('article_no2201').getValue(),
				packingQty:Ext.getCmp('packing_qty2201').getValue(),
				lineId:Ext.getCmp('cmbLineId2201').getValue(),
				
				maxQtyA:Ext.getCmp('max_qty_na2201').getValue()*Ext.getCmp('packing_qty2201').getValue(),
				alertQtyA:Ext.getCmp('alert_qty_na2201').getValue()*Ext.getCmp('packing_qty2201').getValue(),
				suppQtyA:Ext.getCmp('supp_qty_na2201').getValue()*Ext.getCmp('packing_qty2201').getValue(),
				maxQtyNa:Ext.getCmp('max_qty_na2201').getValue()*Ext.getCmp('packing_qty2201').getValue(),
				alertQtyNa:Ext.getCmp('alert_qty_na2201').getValue()*Ext.getCmp('packing_qty2201').getValue(),
				suppQtyNa:Ext.getCmp('supp_qty_na2201').getValue()*Ext.getCmp('packing_qty2201').getValue(),
				
				keepCells:Ext.getCmp('keep_cells2201').getValue(),
				keepCellsA:Ext.getCmp('keep_cells2201').getValue(),
				
				
				rgstName:Ext.get('workerName').getValue(),
				rgstDate:Ext.Date.format(new Date,'Y-m-d h:m:s'),
				updtName:Ext.get('workerName').getValue(),
				updtDate:Ext.Date.format(new Date,'Y-m-d h:m:s')
		};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url:'cset_CellArticleAction_saveOrUpdateCset_Cell_Article.action',
			params : {
				str:jsonStr
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('grid_01_2201').getStore().load();
						commonSetMsterReadOnlyByArray(
							new Array('article_no2201','owner_no2201','o_type2201'),true);
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
	Ext.getCmp('cset_CellArticleAddOrEditWindow').close();
}

/**
 * 关闭界面
 */
function closeCdef_DefAreaWindow(){
	Ext.getCmp('cset_CellArticleAddOrEditWindow').close();
}

/**
 * 填充商品储位对应关系数据
	 * zhouhuan
 */
function loadCset_Cell_Article(rowindex2201)
{
	var record=Ext.getCmp('grid_01_2201').getStore().getAt(rowindex2201-(Ext.getCmp('grid_01_2201').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('owner_no2201').setValue(record.data.ownerNo);
	Ext.getCmp('owner_article_no2201').getStore().add({
    	value:record.data.ownerArticleNo,
    	dropValue:record.data.ownerArticleNo,
    	text:record.data.ownerArticleNo
    });
	Ext.getCmp('owner_article_no2201').setValue(record.data.ownerArticleNo);
	Ext.getCmp('ware_no2201').getStore().removeAll();
	Ext.getCmp('ware_no2201').getStore().add({
    	value:record.data.wareNo,
    	dropValue:"["+record.data.wareNo+"]"+record.data.wareName,
    	text:record.data.wareName
    });	
	Ext.getCmp('ware_no2201').setValue(record.data.wareNo);
	
	Ext.getCmp('area_no2201').getStore().removeAll();
	Ext.getCmp('area_no2201').getStore().add({
    	value:record.data.areaNo,
    	dropValue:"["+record.data.areaNo+"]"+record.data.areaName,
    	text:record.data.areaName
    });
	Ext.getCmp('area_no2201').setValue(record.data.areaNo);
	
	Ext.getCmp('stock_no2201').getStore().removeAll();
	Ext.getCmp('stock_no2201').getStore().add({
    	value:record.data.stockNo,
    	dropValue:record.data.stockNo,
    	text:record.data.stockNo
    });
	Ext.getCmp('stock_no2201').setValue(record.data.stockNo);
	
	Ext.getCmp('cell_no2201').getStore().removeAll();
	Ext.getCmp('cell_no2201').getStore().add({
    	value:record.data.cellNo,
    	dropValue:record.data.cellNo,
    	text:record.data.cellNo
    });
	Ext.getCmp('cell_no2201').setValue(record.data.cellNo);
	Ext.getCmp('barcode2201').setValue(record.data.barcode);
	Ext.getCmp('article_no2201').setValue(record.data.articleNo);
	Ext.getCmp('article_name2201').setValue(record.data.articleName);
	packingQtybefore();
	Ext.getCmp('packing_qty2201').setValue(String(record.data.packingQty));
	Ext.getCmp('cmbLineId2201').getStore().add({
    	value:record.data.lineId,
    	dropValue:"["+record.data.lineId+"]"+record.data.lineName,
    	text:record.data.lineName
    });
	Ext.getCmp('cmbLineId2201').setValue(record.data.lineId);
	Ext.getCmp('o_type2201').getStore().add({
    	value:record.data.pickType,
    	dropValue:"["+record.data.pickType+"]"+record.data.pickTypeText,
    	text:record.data.pickTypeText
    });
	Ext.getCmp('o_type2201').setValue(record.data.pickType);
	Ext.getCmp('keep_cells2201').setValue(String(record.data.keepCells));
	Ext.getCmp('max_qty_na2201').setValue(record.data.maxQtyBox);
	Ext.getCmp('supp_qty_na2201').setValue(record.data.suppQtyBox);
	Ext.getCmp('alert_qty_na2201').setValue(record.data.alertQtyBox);
//	Ext.getCmp('keep_cells_a2201').setValue(record.data.keepCellsA);
//	Ext.getCmp('max_qty_a2201').setValue(record.data.maxQtyA);
//	Ext.getCmp('supp_qty_a2201').setValue(record.data.suppQtyA);
//	Ext.getCmp('alert_qty_a2201').setValue(String(record.data.alertQtyA));
}

//校验
function cset_cell_article2201Blur(th){
	if(button2201=='add'){
		if(!Ext.isEmpty(Ext.getCmp("owner_no2201").getValue())
	   && !Ext.isEmpty(Ext.getCmp("article_no2201").getValue())
	   && !Ext.isEmpty(Ext.getCmp("o_type2201").getValue()))
	{
		var listDetail1 = [];
		var p={
			columnId:'a.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
		};
		listDetail1.push(p);
			var p={
			columnId:'a.owner_no',
			value:Ext.getCmp("owner_no2201").getValue()
		};
		listDetail1.push(p);
		var p={
			columnId:'a.article_no',
			value:Ext.getCmp("article_no2201").getValue()
		};
		listDetail1.push(p);
		var p={
			columnId:'a.pick_type',
			value:Ext.getCmp("o_type2201").getValue()
		};
		listDetail1.push(p);
		if(!Ext.isEmpty(th.getValue())){
			Ext.Ajax.request({
			url:'cset_CellArticleAction_existsCsetCellArticle.action',
			params : {
				strQuery:Ext.encode(listDetail1)
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					if(blurvar=='owner_no'){
						Ext.getCmp('owner_no2201').setValue('');
					}else if(blurvar=='article_no'){
						Ext.getCmp('article_no2201').setValue('');
						Ext.getCmp('owner_article_no2201').setValue('');
						Ext.getCmp('barcode2201').setValue('');
					}else if(blurvar=='pick_type'){
						Ext.getCmp('o_type2201').setValue('');
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
	var strArticleNo = Ext.getCmp('article_no2201').getValue();
	var strWheresql={
		strWheresql:strArticleNo
	};
	Ext.apply(Ext.getCmp('packing_qty2201').getStore().proxy.extraParams,strWheresql);
	Ext.getCmp('packing_qty2201').getStore().removeAll();
	Ext.getCmp('packing_qty2201').getStore().load();
}


