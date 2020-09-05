/**
 * 模块名称：库存调账手建单
 * 模块编码：D201
 * 创建：hcx
 */
var rowindexD201=0;
var isCanEditD201=false;
var saveTypeD201="";  //8-4添加
Ext.define('cms.controller.stock.stock_PlanImportController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.stock.stock_PlanImportUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'stock_PlanImportUI button[name=detailQuery]':{
				click:this.detailQuery
			},//单据列表--》货位选择
			'cdef_DefCellCombo[id=cellNoD201]':{
				beforequery:this.cellNoD101select,/*
				blur:this.cellNoBlur*/
			},//定位
			'stock_PlanImportUI button[id=sendD201]':{
				click:this.sendD201Clik
			},//Grid双击
			'stock_PlanImportUI grid[id=stockPlanMGridD201]':{
				itemdblclick:this.griddblclick,
				selectionchange : this.selectionchangeD201
			},//新增前加载
			'stock_PlanImportUI button[name=userAddButton]':{
				click:this.newAdd
			},//TAB页切换
			'stock_PlanImportUI tabpanel[id=tabPIdD201000]':{
				tabchange:this.tabPIdtabchange
			},//上一条记录
			'stock_PlanImportUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'stock_PlanImportUI button[name=userNextButton]':{
				click:this.userNext
			},//撤销
			'stock_PlanImportUI button[name=userUndoButton]':{
				click:this.undo
			},//验证原调帐单号
			'stock_PlanImportUI form textfield[id=poNoD201]':{
				blur:this.poNoBlur
			},//新增明细
			'stock_PlanImportUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除明细
			'stock_PlanImportUI button[name=detailDelete]':{
				click:this.detailDelete
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_noD201]':{
				beforequery:this.article_noD201BeforeQuery,
				select:this.article_noSelect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qtyD201]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//批号下拉选择
			'cdef_DefCellCombo[id=lotNoD201]':{
				beforequery:this.lotNoD201BeforeQuery
			},//单据明细--》货位选择
			'cdef_DefCellCombo[id=cmCellNoD201]':{
				beforequery:this.cellNoselect
			},//GRID编辑
			'stock_PlanImportUI grid[id=stockPlanDgridD201]':{
				beforeedit:this.stockPlanDgridD201beforeedit,
				edit:this.stockPlanDgridD201edit
			},//保存
			'stock_PlanImportUI button[name=userSaveButton]':{
				click:this.save
			},//取消订单
			'stock_PlanImportUI button[id=undoOrderD201]':{
				click:this.closeOrder
			},//修改      8-4
			'stock_PlanImportUI button[name=userEditButton]':{
				click:this.edit
			}
		});
	},
	
	initializtion:function(){
		
		//显示变量0为不显示，1为显示
		var planBox_D201=commonGetModuleField('D201','planBox')[0].flag;
		var planQmin_D201=commonGetModuleField('D201','planQmin')[0].flag;
		var planDis_D201=commonGetModuleField('D201','planDis')[0].flag;
		var packingUnit_D201=commonGetModuleField('D201','packingUnit')[0].flag;
		var packingSpec_D201=commonGetModuleField('D201','packingSpec')[0].flag;
		var packingUnit_D201_1=commonGetModuleField('D201','packingUnit')[0].flag;
		var packingSpec_D201_1=commonGetModuleField('D201','packingSpec')[0].flag;
		var realBox_D201=commonGetModuleField('D201','realBox')[0].flag;
		var realQmin_D201=commonGetModuleField('D201','realQmin')[0].flag;
		var realDis_D201=commonGetModuleField('D201','realDis')[0].flag;
		
		if(planBox_D201==0){
			Ext.getCmp('planBox_D201').setVisible(false);
		}
		if(planQmin_D201==0){
			Ext.getCmp('planQmin_D201').setVisible(false);
		}
		if(planDis_D201==0){
			Ext.getCmp('planDis_D201').setVisible(false);
		}
		if(packingUnit_D201==0){
			Ext.getCmp('packingUnit_D201').setVisible(false);
		}
		if(packingSpec_D201==0){
			Ext.getCmp('packingSpec_D201').setVisible(false);
		}
		if(packingUnit_D201_1==0){
			Ext.getCmp('packingUnit_D201_1').setVisible(false);
		}
		if(packingSpec_D201_1==0){
			Ext.getCmp('packingSpec_D201_1').setVisible(false);
		}
		if(realBox_D201==0){
			Ext.getCmp('realBox_D201').setVisible(false);
		}
		if(realQmin_D201==0){
			Ext.getCmp('realQmin_D201').setVisible(false);
		}
		if(realDis_D201==0){
			Ext.getCmp('realDis_D201').setVisible(false);
		}
		
	},
	
	//修改    8-4
	edit:function(){
				saveTypeD201='change';
				disableButtonFunc(1,'#menuD201 [name=userPrevButton]','上单');
				disableButtonFunc(1,'#menuD201 [name=userNextButton]','下单');
				debugger;
				//8-13修改
				var data = Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
				var test = Ext.util.Format.trim(data[0].data.createFlag);
				if(test == '0'){//wms自建
					if(data[0].data.status == '10'){//状态为10
						commonSetMsterReadOnlyByArray(
								new Array('cmbOwnerNoD201','poNoD201','planDateD201',
										'orgNoD201','memoD201'),false);
						isCanEditD201 = true;
					}else if(data[0].data.status > '10'){
						commonSetMsterReadOnlyByArray(
								new Array('memoD201'),false);
						isCanEditD201 = false;
					}
				}else if(test == '1'){//ERP下传单据
					commonSetMsterReadOnlyByArray(
							new Array('memoD201'),false);
					isCanEditD201 = false;
				}
				
				commonEditButton('menuD201','edit');
				Ext.getCmp('toolbarD201').items.items[0].enable(true);
				Ext.getCmp('toolbarD201').items.items[1].enable(true);
				Ext.getCmp('stockPlanDgridD201').editingPlugin.startEdit(0,1);
	},
	
	//查找
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='D201';
		queryGrid='stockPlanMGridD201';
	},
	//单据列表--》货位选择
	cellNoD101select:function(){
		var data = Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
		var strWheresql={
				str:Ext.getCmp('cellNoD201').getValue(),
				strQuery:data[0].get('orgNo')
			};
		Ext.apply(Ext.getCmp('cellNoD201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cellNoD201').getStore().removeAll();
		Ext.getCmp('cellNoD201').getStore().load();
	
    },
    //校验所输储位商品是否有库存
    cellNoBlur:function(){
    	if(!Ext.isEmpty(Ext.getCmp('cellNoD201').getValue())){
    		var cellNo=Ext.getCmp('cellNoD201').getValue();
    		var gridcount=Ext.getCmp('gridArticleInfoD201').getStore().getCount();
    		for(var i=0;i<gridcount;i++){
    			var record=Ext.getCmp('gridArticleInfoD201').getStore().getAt(i);
    			var articleNo=record.get('articleNo');
    			var planQty=record.get('recedeQty');
    			if(planQty<0){
    				Ext.Ajax.request({
        				method:'post',
        				url:'stock_PlanImportAction_checkCellNo',
        				params:{
        					cellNo:cellNo,
        					articleNo:articleNo
        			    },
        			    success:function(response){
        			    	var res = Ext.decode(response.responseText);
        			    	if(res=='0'){
        			    		Ext.example.msg('提示',articleNo+'商品在该储位没有库存，请重新选择储位');
        			    		Ext.getCmp('cellNoD201').setValue('');
        			    		Ext.getCmp('cellNoD201').focus();
        			    		return;
        			    	}
        			    }
        			});
    			}
    			
    		};
			
		};	
    },
    //定位
    sendD201Clik:function(){
    	debugger;
    	var data = Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
    	if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		if(data[0].get('status')=='13')
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.isConfirm);
			return;
		}	
//		if(Ext.isEmpty(Ext.getCmp('cellNoD201').getValue())){
//    		Ext.example.msg($i18n.prompt,$i18n_prompt.inputCellNo);
//			return;
//    	}
    	var gridcount=Ext.getCmp('gridArticleInfoD201').getStore().getCount();
		if(gridcount==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
//		var totCount=Ext.getCmp('gridArticleInfoD201').getStore().getCount();
//		var lackCount=Ext.getCmp('gridArticleInfoD201').getStore().query('noEnoughQty',0).getCount();
//		if(totCount-lackCount>0)
//		{
//			Ext.Msg.confirm($i18n_prompt.prompt,'存在库存不足的商品，确定定位吗？',function(button,text)
//			{
//				if(button=='yes'){
//				sendLocateParam();
//				}
//		    });
//	    }else
//	    {
		
		//这里不再校验储位，因为储位有多个。。。所以直接在底层加上校验。 update by sunl 2016年8月1日
		sendLocateParam();
		
//	    	if(!Ext.isEmpty(Ext.getCmp('cellNoD201').getValue())){
//	    		var count=0;
//	    		var a=[];
//	    		var cellNo=Ext.getCmp('cellNoD201').getValue();
//	    		var gridcount=Ext.getCmp('gridArticleInfoD201').getStore().getCount();
//	    		for(var i=0;i<gridcount;i++){
//	    			var record=Ext.getCmp('gridArticleInfoD201').getStore().getAt(i);
//	    			var articleNo=record.get('articleNo');
//	    			var planQty=record.get('recedeQty');
//	    			if(!Ext.isEmpty(record.get('ownerArticleNo'))){
//	    				if(planQty<0){
//		    				Ext.Ajax.request({
//		        				method:'post',
//		        				url:'stock_PlanImportAction_checkCellNo',
//		        				async:false,
//		        				params:{
//		        					cellNo:cellNo,
//		        					articleNo:articleNo
//		        			    },
//		        			    success:function(response){
//		        			    	var res = Ext.decode(response.responseText);
//		        			    	if(res=='0'){
//		        			    		count=count+1;
//		        			    		a.push(articleNo);
//		        			    	}
//		        			    }
//		        			});
//		    			}
//	    			}
//	    		};
//	    		if(count>0){
//		    		Ext.example.msg($i18n_prompt.prompt,'['+a+']'+$i18n_prompt.noStockQty);
//	    			Ext.getCmp('cellNoD201').setValue('');
//		    		Ext.getCmp('cellNoD201').focus();
//		    		return;
//	    		}else{
//    				Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.locateOrNot,function(button,text)
//			    			{
//			    				if(button=='yes'){
//			    				sendLocateParam();
//			    				}
//			    		    });
//    			}
//	    	};	

    },
	//grid双击
	griddblclick:function(th, record,  item,  index, e, eOpts){
		Ext.getCmp('tabPIdD201000').items.items[1].setVisible(true);
		commonEditButton('menuD201','dbclick');
	},
	//选中调账手建单单据列表，加载商品库存信息
	selectionchangeD201:function(th,selected,eOpts){
		var objdata = Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
		var detail=[];
		if(objdata.length!=0){
			detail.push(objdata[0].get('planNo'));	
			var wheresql = { str : detail};	
			Ext.apply(Ext.getCmp('gridArticleInfoD201').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('gridArticleInfoD201').getStore().removeAll();
			Ext.getCmp('gridArticleInfoD201').getStore().load();	
		}
	},
	//新单
	newAdd:function(){
		addUntreadD201();
		saveTypeD201 = 'add';	//8-4添加
		bindEnterSkip($('#formStockPlanMD201'));//调用键盘处理方法
	},
	//TAB页切换 
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPidd2_D201i'){
			var data = Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
			if(data.length!=0){
				commonEditButton('menuD201','dbclick');
				editUntreadD201(data[0].index);
				rowindexD201=data[0].index;
				
				commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNoD201','planTypeD201',
						'poNoD201','planDateD201','orgNoD201','memoD201'),true);
				debugger;
				
				//8-16添加 
				var createFlagStr = Ext.util.Format.trim(data[0].data.createFlag);
				if(createFlagStr == '0'){ //wms自建
					 //当状态为结案和取消时，不允许修改，其他状态可以
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menuD201 [name=userEditButton]','修改');	
					}else{
						disableButtonFunc(0,'#menuD201 [name=userEditButton]','修改');
					}
				}else if(createFlagStr == '1'){ //ERP下传单据
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menuD201 [name=userEditButton]','修改');
					}else{
						disableButtonFunc(0,'#menuD201 [name=userEditButton]','修改');
					}
				}
			
				Ext.getCmp('toolbarD201').items.items[0].disable(true);
				Ext.getCmp('toolbarD201').items.items[1].disable(true);
				
			}
		}
	},
	userPrev:function(){
		Ext.getCmp('stockPlanMGridD201').getSelectionModel().deselectAll();
		rowindexD201=rowindexD201-1;
		editUntreadD201(rowindexD201);
	},
	
	userNext:function(){
		Ext.getCmp('stockPlanMGridD201').getSelectionModel().deselectAll();
		rowindexD201=rowindexD201+1;
		editUntreadD201(rowindexD201);
	},
	undo:function(){
		isCanEditD201=false;
		addUntreadD201();
		Ext.getCmp('stockPlanDgridD201').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNoD201','planTypeD201',
						'poNoD201','planDateD201','orgNoD201','memoD201'),true);
		Ext.getCmp('toolbarD201').items.items[0].disable(true);
		Ext.getCmp('toolbarD201').items.items[1].disable(true);

		commonEditButton('menuD201','undo');
	},//验证原调帐单号
	poNoBlur:function(){
		if(Ext.getCmp('planNoD201').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
			method:'post',
			url:'stock_PlanImportAction_checkPoNo',
			params:{
				poNo:Ext.getCmp('poNoD201').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		
		    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.isExist);
		    		Ext.getCmp('poNoD201').setValue('');
		    		Ext.getCmp('poNoD201').focus();
		    	}
		    }
			});
		};	
	},
	//新增明细
	detailAdd:function(){
		var store = Ext.getCmp('stockPlanDgridD201').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
			
		}else{
			if(!commonCheckIsInputAll('formStockPlanMD201')){
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('cmbOwnerNoD201','planTypeD201',
					'poNoD201','planDateD201','orgNoD201')
			,true);
		}
		var r=Ext.create('cms.model.stock.stock_PlanDModel',{});
		store.add(r);
		Ext.getCmp('stockPlanDgridD201').editingPlugin.startEdit(count,1);
	},
	//删除明细
	detailDelete:function(){
		var data = Ext.getCmp('stockPlanDgridD201').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.deleteOrNot,function(button,text){
			if(button=='yes'){
				Ext.getCmp('stockPlanDgridD201').getStore().remove(data);					
				if(Ext.getCmp("stockPlanDgridD201").getStore().getCount()==0)
				{
					commonSetMsterReadOnlyByArray(
					new Array('cmbOwnerNoD201','planTypeD201',
							'poNoD201','planDateD201','orgNoD201')
					,false);
				};
			}
			});
		}else{
			Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.choseDeleteRecord);
			return;
		}
	},
	//商品加载前
	article_noD201BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("cmbOwnerNoD201").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_noD201').getStore().proxy.extraParams,strWheresql);
	},
	article_noSelect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'stock_PlanImportAction_getArticle',
			params:{
				articleNo:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('stockPlanDgridD201').getSelectionModel().getSelection();
				data[0].set('articleName',res[0].articleName);
		    	data[0].set('barcode',res[0].barcode);
		    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);
		    	data[0].set('articleQty',0);
		    	data[0].set('planBox',0);
		    	data[0].set('planQmin',0);
		    	data[0].set('planDis',0);
		    	data[0].set('qminOperatePacking',res[0].qminOperatePacking);
		    	data[0].set('guarantee',res[0].guarantee);
		    	data[0].set('lotType',res[0].lotType);
		    	
		    	var lotType = res[0].lotType;
		    	if(lotType == '1'){
		    		data[0].set('produceDateText','1900-01-01');
		    		data[0].set('expireDateText','1900-01-01');
		    		data[0].set('lotNo','');
		    	}else if(lotType == '2'){
		    		data[0].set('produceDateText','');
		    		data[0].set('expireDateText','');
		    		data[0].set('lotNo','N');
		    	}else if(lotType == '3'){
		    		data[0].set('produceDateText','');
		    		data[0].set('expireDateText','');
		    		data[0].set('lotNo','N');
		    	}else if(lotType == '4'){
		    		data[0].set('produceDateText','1900-01-01');
		    		data[0].set('expireDateText','1900-01-01');
		    		data[0].set('lotNo','N');
		    	}
//		    	Ext.getCmp('qualityD201').setValue('0');
		     	Ext.Ajax.request({
					method:'post',
					url:'bdef_DefarticleAction_getMaxArticlePacking',
					params:{
						strWheresql:combo.getValue()
				    },
				    success:function(response){
				    	var res = Ext.decode(response.responseText);
				    	data[0].set('packingQty',res);
				    	
				    	Ext.Ajax.request({
							method:'post',
							url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
							params:{
								strArticleNo:combo.getValue(),
								strPackingQty:res
						    },
						    success:function(response){
						    	var flagCount=0;
						    	var gridcount=Ext.getCmp('stockPlanDgridD201').getStore().getCount();
						    	for(var i=0;i<gridcount;i++){
						    		var exp=Ext.getCmp('stockPlanDgridD201').getStore().getAt(i);    		
						    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==data[0].get('packingQty')){
						    			flagCount=flagCount+1;
						    		}
						    	}
						    	var res = Ext.decode(response.responseText);
						    	data[0].set('packingUnit',res[0].packingUnit);
						    	data[0].set('packingSpec',res[0].spec);
						    }
						});
				    }
				    
		    	});
		    }
		});
	},
	//包装加载前
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('stockPlanDgridD201').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	//包装选择
	packingQtyselect:function(combo){
		var data = Ext.getCmp('stockPlanDgridD201').getSelectionModel().getSelection();
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_getPackingUnit',
			params:{
				articleNo:data[0].get('articleNo'),
				packingQty:combo.getValue(),
				type:'1'
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	data[0].set('packingUnit',res[0].packingUnit);
		    	data[0].set('packingSpec',res[0].spec);
		    	data[0].set('articleQty',null);
		    }
		});
	},
	//批号加载前
	lotNoD201BeforeQuery:function(){
		 
			var strWheresql={
				articleNo:Ext.getCmp('article_noD201').getValue(),
				produceDate:Ext.Date.format(Ext.getCmp('produceDateD201').getValue(),'Y/m/d'),
				lotNo:Ext.getCmp('lotNoD201').getValue()==undefined?'N':Ext.getCmp('lotNoD201').getValue()
			};
			Ext.apply(Ext.getCmp('lotNoD201').getStore().proxy.extraParams,strWheresql);
	},
	//单据明细--》货位选择
	cellNoselect:function(){
		var strWheresql={
				str:Ext.getCmp('cmCellNoD201').getValue()
			};
		Ext.apply(Ext.getCmp('cmCellNoD201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmCellNoD201').getStore().removeAll();
		Ext.getCmp('cmCellNoD201').getStore().load();
	
    },
	//网格编辑前
	stockPlanDgridD201beforeedit:function(e){
		if(!isCanEditD201)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	//网格编辑
	stockPlanDgridD201edit:function(editor,e,eOpts){
		if(e.field=='produceDateText')
		{
			if(!Ext.isEmpty(e.value))
			{
				if(Ext.Date.format(e.value,'Y-m-d')>Ext.Date.format(new Date(),'Y-m-d'))
				{
					editor.context.record.set('produceDateText',editor.context.originalValue);
					Ext.example.msg($i18n.prompt,$i18n_prompt.productTimeCannotMoreThanToday);
					return;
				}
				var lotType = editor.context.record.data.lotType;
				if(lotType == '1' || lotType == '4'){
					editor.context.record.set('produceDateText','1900-01-01');
					editor.context.record.set('expireDateText','1900-01-01');
		    	}else{
		    		if(editor.grid.getStore().
							findBy(function(record, id) {  
									return record.internalId!=editor.context.record.internalId 
									&& record.get('articleNo')==editor.context.record.data.articleNo
									&& record.get('packingQty')==editor.context.record.data.packingQty
									&& Ext.Date.format(record.get('produceDateText'),'Y-m-d')==Ext.Date.format(editor.context.record.data.produceDateText,'Y-m-d')
									&& record.get('lotNo')==editor.context.record.data.lotNo;
								})!=-1)				
					{
						Ext.example.msg($i18n.prompt, $i18n_prompt.check1);
		        		editor.context.record.set('produceDateText',null);
						return;
					}else{
						var guarantee = editor.context.record.data.guarantee;
				        if(guarantee>0)
				        	{
				        		editor.context.record.set('expireDateText',Ext.Date.format
				        				(Ext.Date.add(new Date(e.value), Ext.Date.DAY, parseInt(guarantee)),'Y-m-d'));
//				        		checkDateD201(e,editor);
				        	 }else{
				        		Ext.example.msg($i18n.prompt, $i18n_prompt.guaranteeLessZero);
				        		editor.context.record.set('produceDateText',null);
				        		return;
				        	 }
					}
		    	}
			}
		
		}else if(e.field=='packingQty' || e.field=='articleNo'){
			if(!Ext.isEmpty(e.value)){
				if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('articleNo')==editor.context.record.data.articleNo
						&& record.get('packingQty')==editor.context.record.data.packingQty
						&& record.get('produceDateText')==editor.context.record.data.produceDateText
						&& record.get('lotNo')==editor.context.record.data.lotNo;
					})!=-1)				
				{
					editor.context.record.set('packingQty','');
					Ext.example.msg($i18n.prompt, $i18n_prompt.check1);
				}
			}
		}else if(e.field=='lotNo'){
			if(!Ext.isEmpty(e.value)){
				if(editor.context.record.data.lotType == '2' 
					|| editor.context.record.data.lotType == '4'){
					editor.context.record.set('lotNo','N');
		    	}
			}
		}
	},
	//保存
	save:function(){
		if(!commonCheckIsInputAll('formStockPlanMD201'))
		{
			return;
		}
		var gridcount=Ext.getCmp('stockPlanDgridD201').getStore().getCount();
		if(gridcount==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		var master={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:Ext.getCmp("cmbOwnerNoD201").getValue(),
					planNo:Ext.getCmp("planNoD201").getValue()
				},
				planType:Ext.getCmp("planTypeD201").getValue(),
				poNo:Ext.getCmp("poNoD201").getValue(),
				planDate:Ext.util.Format.date(Ext.getCmp('planDateD201').getValue(), 'Y-m-d'),
				status:'10',
				createFlag:'0',
				sendFlag:'10',
				orgNo:Ext.getCmp("orgNoD201").getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				remark:Ext.getCmp("memoD201").getValue()
			};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('stockPlanDgridD201').getStore().getAt(i);
			var ownerNo='';
			if(!Ext.isEmpty(record.get('articleNo'))){
				Ext.Ajax.request({
				method:'post',
				url:'bdef_DefarticleAction_checkOwnerNo',
				async:false,
				params:{
					strArticleNo:record.get('articleNo')
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	ownerNo=res.msg;
			    }
				});
				if(ownerNo!=Ext.getCmp("cmbOwnerNoD201").getValue()){
		    		Ext.example.msg($i18n.prompt,'【'+record.get('articleNo')+
		    				'】商品不属于'+Ext.getCmp("cmbOwnerNoD201").getValue()+'货主商品');
					return;
		    	}else{
		    		if(Ext.isEmpty(record.get('packingQty')) || record.get('packingQty')<=0){
						Ext.example.msg($i18n.prompt,$i18n_prompt.choicePakingQty);
						return;
					}
		    		debugger;
					if(record.get('planBox')*record.get('packingQty')
							+record.get('planQmin')*record.get('qminOperatePacking')
							+record.get('planDis')==0){
						Ext.example.msg($i18n.prompt,$i18n_prompt.inputQty);
						return;
					}
					if(Ext.isEmpty(record.get('produceDateText'))){
						Ext.example.msg($i18n.prompt,$i18n_prompt.inputProductDate);
						return;
					}
					if(Ext.isEmpty(record.get('lotNo'))){
						Ext.example.msg($i18n.prompt,$i18n_prompt.inputLotNo);
		                return;
		            }
					var d={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:Ext.get('warehouseNo').getValue(),
								ownerNo:Ext.getCmp("cmbOwnerNoD201").getValue(),
								planNo:Ext.getCmp("planNoD201").getValue(),
								rowid:i
							},
							articleNo:record.get('articleNo'),
						    packingQty:record.get('packingQty'),
						    produceDate:record.get('produceDateText'),
						    expireDate:record.get('expireDateText'),
						    quality:'0',
						    lotNo:record.get('lotNo'),
						    importBatchNo:'N',
						    rsvBatch1:'N',
						    rsvBatch2:'N',
						    rsvBatch3:'N',
						    rsvBatch4:'N',
						    rsvBatch5:'N',
							rsvBatch6:'N',
							rsvBatch7:'N',
							rsvBatch8:'N',
						    cellNo:record.get('cellNo')==undefined?  '':record.get('cellNo'),
						    planQty:record.get('planBox')*record.get('packingQty')
							        +record.get('planQmin')*record.get('qminOperatePacking')
							        +record.get('planDis'),
						    realQty:'0',
						    stockType:1,
						    stockValue:'N',
						    labelNo:'N',
						    status:'10',
						    rgstName:Ext.get('workerNo').getValue(),
							rgstDate:new Date()
						};
						detail.push(d);
		    	}
			};
			
		};
		var M=Ext.encode(master);
		var D=Ext.encode(detail);
		
		var params={
			strJsonMaster:M,
			strJsonDetail:D
		};

		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,function(button, text){
			if (button == 'yes') {
				//8-4添加
				if(saveTypeD201=='change'){
					Ext.Ajax.request({
						method:'post',
						url:'stock_PlanImportAction_changeStock',
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg('提示',data.msg);
								Ext.getCmp('planNoD201').setValue(data.obj);
								/*for(var i=0;i<gridcount;i++ ){
									var record  = Ext.getCmp('grid_02_E101').getStore().getAt(i);
									record.set('expNo',data.obj);
								}*/
								if(typeof(Ext.getCmp('stockPlanMGridD201'))!=='undefined'){
									Ext.getCmp('stockPlanMGridD201').store.reload();
								}
								commonEditButton('menuD201','save');
								isCanEditD201=false;
								Ext.getCmp('toolbarD201').items.items[0].disable(true);
								Ext.getCmp('toolbarD201').items.items[1].disable(true);
							}else{
								Ext.example.msg('提示',data.msg);
								disableButtonFunc(0,'#menuD201 [name=userSaveButton]','保存');
							}
						}
					});
				}else if(saveTypeD201=='add'){
					Ext.Ajax.request({
						method:'POST',
						url:'stock_PlanImportAction_save.action',
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('planNoD201').setValue(data.obj);
								commonEditButton('menuD201','save');
								if(typeof(Ext.getCmp('stockPlanMGridD201'))!=='undefined'){
									Ext.getCmp('stockPlanMGridD201').store.reload();
								}
							}else{
								Ext.example.msg($i18n.prompt,data.msg);
							}
						}
					});	
				}
			}
			
			if (button == 'no') {
				return;
			}
		});   
	
	},
	//取消
	closeOrder:function(){
		var rec = Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
		if(rec.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.Msg.confirm('提示','确定取消订单？',function(button,text){
				if(button=='yes'){
					if(rec[0].get('status') > '10'){
						Ext.example.msg($i18n.prompt,'该订单不能取消');
						return;
					}else if(rec[0].get('status') == '10'){
						var detail=[];
						var no={
								    enterpriseNo:Ext.get('enterpriseNo').getValue(),
								    warehouseNo:Ext.get('warehouseNo').getValue(),
							        ownerNo:rec[0].get('ownerNo'),
							        planNo:rec[0].get('planNo'),
									rgstName:Ext.get('workerNo').getValue()
							  };
					    detail.push(no);
					    var no=Ext.encode(detail);
						   var params = {
								strQuery:no
						   };
						Ext.Ajax.request({
							method:'post',
							url:'stock_PlanImportAction_closeOrder',
							params:params,
						    success:function(response){
						    	var data = Ext.decode(response.responseText);
						    	if(data.isSucc){					
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('stockPlanMGridD201').getStore().removeAll();
							 	  	Ext.getCmp('stockPlanMGridD201').getStore().reload();
							 	  	Ext.getCmp('gridArticleInfoD201').getStore().removeAll();
								}else{
									Ext.example.msg($i18n.prompt,data.msg+data.obj);
								}
						    }
						});
					}
				}
			});		
		}		
	}
});
//新增前加载
function addUntreadD201(){
	Ext.getCmp('formStockPlanMD201').getForm().reset();
	Ext.getCmp('planNoD201').setValue('保存时自动生成');
	Ext.getCmp('cmbOwnerNoD201').setValue('001');
	Ext.getCmp('planTypeD201').setValue('1');
	Ext.getCmp('orgNoD201').setValue('001');
	Ext.getCmp('planDateD201').setValue(new Date());
	Ext.getCmp('stockPlanDgridD201').getStore().removeAll();
	commonSetMsterReadOnlyByArray(
	new Array('cmbOwnerNoD201','planTypeD201',
	'poNoD201','planDateD201','orgNoD201','memoD201'),false);
	commonSetMsterReadOnlyByArray(
	new Array('planNoD201'),true);
	
	commonEditButton('menuD201','add');
	Ext.getCmp('toolbarD201').items.items[0].enable(true);
	Ext.getCmp('toolbarD201').items.items[1].enable(true);
	Ext.getCmp('poNoD201').focus();
	isCanEditD201=true;
};
//填充数据
function editUntreadD201(rowindexD201){
	if(rowindexD201==0)
	{
		Ext.getCmp('menuD201').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menuD201').items.items[0].enable(true);
	}
	if(rowindexD201==Ext.getCmp('stockPlanMGridD201').getStore().getCount()-1)
	{		
		Ext.getCmp('menuD201').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menuD201').items.items[1].enable(true);
	}
	var record=Ext.getCmp('stockPlanMGridD201').getStore().getAt(rowindexD201-(Ext.getCmp('stockPlanMGridD201').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('planNoD201').setValue(record.data.planNo);
	Ext.getCmp('cmbOwnerNoD201').setValue(record.data.ownerNo);
	Ext.getCmp('planTypeD201').setValue(record.data.planType);
	Ext.getCmp('poNoD201').setValue(record.data.poNo);
	Ext.getCmp('planDateD201').setValue(record.data.planDate);
	Ext.getCmp('orgNoD201').setValue(record.data.orgNo);
	Ext.getCmp('memoD201').setValue(record.data.remark);

	if(record.data.planNo!=null||record.data.planNo!=''){
		var detail1 = [];
		var d={
			    columnId:'a.plan_no',
			    value:record.data.planNo
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql={
			str:jsonDetail1
		};
	}
	Ext.apply(Ext.getCmp('stockPlanDgridD201').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('stockPlanDgridD201').getStore().removeAll();
	Ext.getCmp('stockPlanDgridD201').getStore().load();
	isCanEditD201=false;
};
function sendLocateParam(){
	
	var objdata=Ext.getCmp('stockPlanMGridD201').getSelectionModel().getSelection();
	var detail=[];
	if(objdata.length!=0){
		var no={
				ownerNo:objdata[0].get('ownerNo'),
				planNo:objdata[0].get('planNo'),
				cellNo:'N'//Ext.getCmp('cellNoD201').getValue() --取明细储位时，传N
			};
			detail.push(no);
		
	}
	var no=Ext.encode(detail);
	var params = {
		str:no,
		strQuery:Ext.getCmp('rdoCheckTypeD201').getValue()
	};
	Ext.Ajax.request({
		method:'POST',
		url:'stock_PlanImportAction_send.action',
		params:params,
		success:function(response){
			var data = Ext.decode(response.responseText);
			if(data.isSucc){
				
				Ext.example.msg($i18n_prompt.prompt,data.msg);
			    Ext.getCmp('stockPlanMGridD201').getStore().removeAll();
			    Ext.getCmp('gridArticleInfoD201').getStore().removeAll();
				Ext.getCmp('stockPlanMGridD201').getStore().reload();
				//Ext.getCmp('cellNoD201').setValue('');
			
			}else{
				Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
			}				
		},
		failure:function(response){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.CannotSubForWeb);
		}
	});	
};