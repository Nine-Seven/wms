/**
 * 模块名称：报损中心
 * 模块编码：E101
 * 创建：HKL
 */
var rowindexE101=0;
var isCanEditE101=false;
var strOANO = "";
var saveTypeE101="";
Ext.define('cms.controller.sodata.sodata_WasteController',{
	extend:'Ext.app.Controller',
	id:'sodata.sodata_WasteController',
	requires:[
	          'cms.view.sodata.sodata_WasteUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'sodata_WasteUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'sodata_WasteUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'sodata_WasteUI button[name=userNextButton]':{
				click:this.userNext
			},//新增前加载
			'sodata_WasteUI button[name=userAddButton]':{
				click:this.newAdd
			},//Grid双击
			'sodata_WasteUI grid[id=grid_01_E101]':{
				itemdblclick:this.grid_01_E101Click,
				selectionchange : this.selectionchangeE101
			},//TAB页切换
			'sodata_WasteUI tabpanel[id=tabPIdE101]':{
				tabchange:this.tabPidchange
			},//验证订单号
			'sodata_WasteUI form textfield[id=po_noE101]':{
				blur:this.po_nomarkBlur
			},//保存
			'sodata_WasteUI button[name=userSaveButton]':{
				click:this.save
			},//撤消
			'sodata_WasteUI button[name=userUndoButton]':{
				click:this.undo
			},//新增明细
			'sodata_WasteUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除明细
			'sodata_WasteUI button[name=detailDelete]':{
				click:this.detailDelete
			},//商品Grid编辑
			'sodata_WasteUI grid[id=grid_02_E101]':{
				beforeedit:this.grid_02_E101beforeedit
				//edit:this.grid_02_E101Edit
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_noE101]':{
				beforequery:this.article_noE101BeforeQuery,
				select:this.article_noselect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qtyE101]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//回车键事件
			'sodata_WasteUI form[id=form_01_E101] field':{
				specialkey:this.boxkeydown
			},//取消订单  undoOrder
			'sodata_WasteUI button[name=undoOrder]':{
				click:this.undoOrder
			},//发单
			'sodata_WasteUI button[name=print]':{
				click:this.print
			},//导入
			'sodata_WasteUI button[name=upload]':{
				click:this.uploadClick
			},//修改      7-14
			'sodata_WasteUI button[name=userEditButton]':{
				click:this.edit
			}
		});
	},
	//取消订单   8-16修改
	undoOrder:function(){
		var data = Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.Msg.confirm('提示','确定取消订单？',function(button,text){
					if(button=='yes'){
						if(data[0].data.status > 10){
							Ext.Msg.alert($i18n.prompt,'改单据不能取消！');
						}else if(data[0].data.status == 10){
							Ext.Ajax.request({
								method:'post',
								url:'sodata_WasteAction_closeOrder',
								params:{
									wasteNo:data[0].data.wasteNo,
									ownerNo:data[0].data.ownerNo
							    },
							    success:function(response){
							    	var data = Ext.decode(response.responseText);
							    	if(data.isSucc){					
										Ext.example.msg($i18n.prompt,data.msg);
										Ext.getCmp('grid_01_E101').getStore().removeAll();
								 	  	Ext.getCmp('grid_01_E101').getStore().reload();
									}else{
										Ext.example.msg($i18n.prompt,data.msg);
									}
							    }
							});
						}
					}
				});
		}		
	},
	//导入
	uploadClick:function(){
		Ext.create('cms.view.sodata.windows.sodataUploadWindow',
		{
			title:'上传'
		}).show();
	},
	
	//修改    7-14
	edit:function(){
				saveTypeE101='change';
				//点击修改把合计移除   
				var gridcount=Ext.getCmp('grid_02_E101').getStore().getCount();	
				var lastDate=Ext.getCmp('grid_02_E101').getStore().getAt(gridcount-1);
				if(lastDate.get('articleNo')=='合计'){
					Ext.getCmp('grid_02_E101').getStore().remove(lastDate);
				}
				//disableButtonFunc(1,'#menuE101 [name=userPrevButton]','上单');
				//disableButtonFunc(1,'#menuE101 [name=userNextButton]','下单');
				
				//8-16修改
				var data = Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
				if(data[0].data.createFlag=='0'){//wms自建
					if(data[0].data.status =='10'){//状态为10
						commonSetMsterReadOnlyByArray(
								new Array('owner_noE101','po_noE101','orgNoE101','wasteDateE101'),false);
						isCanEditE101=true;
					}else{
						isCanEditE101=false;
					}
				}else if(data[0].data.createFlag=='1'){//ERP下传单据
					isCanEditE101=false;
				}
				commonEditButton('menuE101','edit');
				//8-16修改
				Ext.getCmp('menuE101').items.items[0].disable(true);
				Ext.getCmp('menuE101').items.items[1].disable(true);
				
				Ext.getCmp('toolbarE101').items.items[0].enable(true);
				Ext.getCmp('toolbarE101').items.items[1].enable(true);
				Ext.getCmp('grid_02_E101').editingPlugin.startEdit(0,1);
	},
	
	//发单
	print:function(){
		if(Ext.isEmpty(workSpaceNo)){
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		
		var data = Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
		if(data.length==0){ 
			Ext.example.msg('提示',"请选择单据！");
		}else{
			if(data[0].data.status !='10'){
				Ext.example.msg('提示',"该单据不是审核通过状态不能发单！");
				return;
			}
			Ext.Ajax.request({
				url:'sodata_WasteAction_tscBillPrint',
				method:'post',
				params:{
					wasteNo:data[0].data.wasteNo,
					ownerNo:data[0].data.ownerNo,
					 type:Ext.getCmp("rdoCheckTypeE101").getValue()
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('grid_01_E101').getStore().removeAll();
						Ext.getCmp('grid_01_E101').getStore().load();
						Ext.getCmp('gridArticleInfoE101').getStore().removeAll();
						Ext.getCmp('gridArticleInfoE101').getStore().load();
					}
					else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
					
				}
			});	
			
		}		
	},
	
	//回车键事件
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) {
	  		if(th.id=="exp_remarkE101"){	  			
	  			Ext.getCmp('toolbarE101').items.items[0].focus();
	  		}else{
				th.nextSibling().focus();
			}
        }
	},
	initializtion:function(){
		isCanEditE101=false;
		//显示变量0为不显示，1为显示
		var planBoxE101=commonGetModuleField('E101','planBox')[0].flag;
		var planDisE101=commonGetModuleField('E101','planDis')[0].flag;
		var planQminE101=commonGetModuleField('E101','planQmin')[0].flag;

		var packingUnitE101_2=commonGetModuleField('E101','packingUnit')[0].flag;
		var packingSpecE101_2=commonGetModuleField('E101','packingSpec')[0].flag;
		var packingUnitE101_1=commonGetModuleField('E101','packingUnit')[0].flag;
		var packingSpecE101_1=commonGetModuleField('E101','packingSpec')[0].flag;
		if(planBoxE101==0){
			Ext.getCmp('planBoxE101').setVisible(false);
		}
		if(planDisE101==0){
			Ext.getCmp('planDisE101').setVisible(false);
		}
		if(planQminE101==0){
			Ext.getCmp('planQminE101').setVisible(false);
		}
		if(packingUnitE101_2==0){
			Ext.getCmp('packingUnitE101').setVisible(false);
		}
		if(packingSpecE101_2==0){
			Ext.getCmp('packingSpecE101').setVisible(false);
		}
		if(packingUnitE101_1==0){
			Ext.getCmp('packingUnitE101_1').setVisible(false);
		}
		if(packingSpecE101_1==0){
			Ext.getCmp('packingSpecE101_1').setVisible(false);
		}
	},
	
	//商品加载前
	article_noE101BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_noE101").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_noE101').getStore().proxy.extraParams,strWheresql);
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='E101';
		queryGrid='grid_01_E101';	
	},
	
	userPrev:function(){
		rowindexE101=rowindexE101-1;
		editExpE101(rowindexE101);
	},
	
	userNext:function(){
		rowindexE101=rowindexE101+1;
		editExpE101(rowindexE101);
	},
	
	newAdd:function(){
		addWasteE101();
		saveTypeE101='add';
		bindEnterSkip($('#form_01_E101'));//调用键盘处理方法
	},
	
	grid_01_E101Click:function(th, record,  item,  index, e, eOpts){
		Ext.getCmp('tabPIdE101').items.items[1].setVisible(true);
		
		isCanEditE101=false;
		commonEditButton('menuE101','dbclick');
	
		Ext.getCmp('toolbarE101').items.items[0].disable(true);
		Ext.getCmp('toolbarE101').items.items[1].disable(true);
	},
	//选中报损手建单单据列表，加载商品库存信息
	selectionchangeE101:function(th,selected,eOpts){
		var objdata = Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
		
        if(objdata.length != 0){
        	if(objdata[0].get('statusText') == '初始'){
        		Ext.getCmp('availableQty').setVisible(true);
    			Ext.getCmp('noEnoughQty').setVisible(true);
    		}else{
    			Ext.getCmp('availableQty').setVisible(false);
    			Ext.getCmp('noEnoughQty').setVisible(false);
    		}
			var wheresql = { 
					wheresql : objdata[0].get('wasteNo')
			};	
      
			Ext.apply(Ext.getCmp('gridArticleInfoE101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('gridArticleInfoE101').getStore().removeAll();
			Ext.getCmp('gridArticleInfoE101').getStore().load();	
        };
        
	},
	
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		Ext.getCmp('owner_noE101').getStore().load();
		if(newCard.itemId=='tabPIdE101i'){
			var data = Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
			if(data.length!=0){
				editExpE101(data[0].index);
				rowindexE101=data[0].index;
				isCanEditE101=false;
				commonEditButton('menuE101','dbclick');
				
				//8-16添加 
				if(data[0].data.createFlag == '0'){ //wms自建
					 //当状态为结案和取消时，不允许修改，其他状态可以
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menuE101 [name=userEditButton]','修改');	
					}else{
						disableButtonFunc(0,'#menuE101 [name=userEditButton]','修改');
					}
				}else if(data[0].data.createFlag == '1'){ //ERP下传单据
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menuE101 [name=userEditButton]','修改');
					}else{
						disableButtonFunc(0,'#menuE101 [name=userEditButton]','修改');
					}
				}
				
				Ext.getCmp('toolbarE101').items.items[0].disable(true);
				Ext.getCmp('toolbarE101').items.items[1].disable(true);
			}
		}
	},
	
	po_nomarkBlur:function(){
		if(Ext.getCmp('waste_noE101').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
				method:'post',
				url:'sodata_WasteAction_checkWatesNo',
				params:{
					poNo:Ext.getCmp('po_noE101').getValue()
		    	},
		    	success:function(response){
		    		var res = Ext.decode(response.responseText);
		    		if(res=='1'){
		    			Ext.example.msg('提示','该单号已有，请重新录入');
		    			Ext.getCmp('po_noE101').setValue('');
		    			Ext.getCmp('po_noE101').focus();
		    		}
		   		}
			});
		}
		
	},
	
	save:function(){ 
		var gridcount=Ext.getCmp('grid_02_E101').getStore().getCount();
		if(gridcount>0){
			if(!commonCheckdetailgrid('grid_02_E101',0,gridcount-1))
			{
				return;
			}
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		var wasteNo=Ext.getCmp('waste_noE101').getValue();
		var poNo=Ext.getCmp('po_noE101').getValue();
		var ownerNo=Ext.getCmp('owner_noE101').getValue();
		var wasteType=Ext.getCmp('waste_typeE101').getValue();
		var wasteDate=Ext.getCmp('wasteDateE101').getValue();
		var orgNo=Ext.getCmp('orgNoE101').getValue();
		var status='10';
		Ext.Ajax.request({
			url:'sodata_WasteAction_getSodataAuditFlag',
			method:'post',
			async:false,
			params:{
				ownerNo:ownerNo
			},
			success:function(response){
				var rec1=Ext.decode(response.responseText);
				if(rec1=='1'){
					status='11';
				}
			}
	    });
		
		var da=Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
		var master={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				wasteNo:wasteNo
			},
			poNo:poNo,
			wasteType:wasteType,
			poType:wasteType,
			ownerNo:ownerNo,
			wasteDate:wasteDate,
			requestDate:wasteDate,
			stockType:'1',
			stockValue:'N',
			status:status,
			createFlag:'0',
			sendFlag:'10',
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date(),
			orgNo:orgNo
		};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var waste=Ext.getCmp('grid_02_E101').getStore().getAt(i);
			var d={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					wasteNo:wasteNo,
					poId:i
				},
				lotNo:'N',
				quality:'0',
				ownerNo:ownerNo,
				articleNo:waste.get('articleNo'),
				packingQty:waste.get('packingQty'),
				wasteQty:waste.get('planBox')*waste.get('packingQty')
				+waste.get('planQmin')*waste.get('qminOperatePacking')
				+waste.get('planDis'),
				realQty:'0',
				locateQty:'0'
			
			};
			if(waste.get('planBox')*waste.get('packingQty')
					+waste.get('planQmin')*waste.get('qminOperatePacking')
					+waste.get('planDis')==0){
				Ext.example.msg('提示','采购数不能为0');
				return;
			}
			detail.push(d);
		};
		disableButtonFunc(1,'#menuE101 [name=userSaveButton]','保存');
		var wasteM=Ext.encode(master);
		var wasteD=Ext.encode(detail);
		var params={
			wasteM:wasteM,
			wasteD:wasteD
		};
		//7-14   之前是屏蔽的      现在开放
		if(saveTypeE101=='change'){
			Ext.Ajax.request({
			method:'post',
			url:'sodata_WasteAction_changeWaste',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('waste_noE101').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_E101').getStore().getAt(i);
						record.set('expNo',data.obj);
					}
					if(typeof(Ext.getCmp('grid_01_E101'))!=='undefined'){
						Ext.getCmp('grid_01_E101').store.reload();
					}
					commonEditButton('menuE101','save');
					isCanEditE101=false;
					Ext.getCmp('toolbarE101').items.items[0].disable(true);
					Ext.getCmp('toolbarE101').items.items[1].disable(true);
				}else{
					Ext.example.msg('提示',data.msg);
					disableButtonFunc(0,'#menuE101 [name=userSaveButton]','保存');
				}
			}
		});
		
		}else if(saveTypeE101=='add'){
			Ext.Ajax.request({
			method:'post',
			url:'sodata_WasteAction_saveWaste',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('waste_noE101').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_E101').getStore().getAt(i);
						record.set('expNo',data.obj);
					}
					if(typeof(Ext.getCmp('grid_01_E101'))!=='undefined'){
						Ext.getCmp('grid_01_E101').store.reload();
					}
					commonEditButton('menuE101','save');
					isCanEditE101=false;
					Ext.getCmp('toolbarE101').items.items[0].disable(true);
					Ext.getCmp('toolbarE101').items.items[1].disable(true);
					disableButtonFunc(1,'#menuE101 [name=userSaveButton]','保存');
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
		}
	},
	
	
	grid_02_E101beforeedit:function(e){
		if(!isCanEditE101)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//校验商品是否重复，因电商支持一张单一个商品可以重复，所以把这段代码屏蔽
/*	grid_02_E101Edit:function(editor,e,eOpts){
		if(e.field=='packingQty' ||e.field=='lotnoValue1' || e.field=='lotnoValue2' || e.field=='lotnoCondition'){
			
			if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('articleNo')==editor.context.record.data.articleNo
					&& record.get('packingQty')==editor.context.record.data.packingQty;
				})!=-1){
				if(e.field=='packingQty'){
					editor.context.record.set('packingQty',editor.context.originalValue);
				}
				Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
			}
		}
	},*/
	
	undo:function(){
		//修改暂不开放    
		//7-14  修改   开放
		if(saveTypeE101=='change'){
			disableButtonFunc(0,'#menuE101 [name=userEditButton]','修改');	
			disableButtonFunc(0,'#menuE101 [name=userPrevButton]','上单');
			disableButtonFunc(0,'#menuE101 [name=userNextButton]','下单');
			var data = Ext.getCmp('grid_01_E101').getSelectionModel().getSelection();
			if(data.length!=0){
				editExpE101(data[0].index);
				rowindexE101=data[0].index;
				isCanEditE101=false;
				commonEditButton('menuE101','dbclick');
			}
		}else{
			isCanEditE101=false;
			addWasteE101();
			Ext.getCmp('grid_02_E101').getStore().removeAll();
			commonSetMsterReadOnlyByArray(
			new Array('waste_noE101','po_noE101',
			'owner_noE101','waste_typeE101','orgNoE101')
			,true);
			Ext.getCmp('toolbarE101').items.items[0].disable(true);
			Ext.getCmp('toolbarE101').items.items[1].disable(true);
			commonEditButton('menuE101','undo');
			//disableButtonFunc(1,'#menuE101 [name=userEditButton]','修改');	
		}
		
	},
	
	detailAdd:function(){
		var store = Ext.getCmp('grid_02_E101').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
			
		}else{
			if(!commonCheckIsInputAll('form_01_E101'))
			{
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('waste_noE101','po_noE101',
			'owner_noE101','waste_typeE101','wasteDateE101','orgNoE101')
			,true);
		}
		var r=Ext.create('cms.model.sodata.sodata_WasteDModel',{});
		r.set('wasteNo',Ext.getCmp('waste_noE101').getValue());
		store.add(r);
		Ext.getCmp('grid_02_E101').editingPlugin.startEdit(count,1);
	},
	
	detailDelete:function(){
		var data = Ext.getCmp('grid_02_E101').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm('提示','确定删除数据',function(button,text){
			if(button=='yes'){
				Ext.getCmp('grid_02_E101').getStore().remove(data);					
				if(Ext.getCmp("grid_02_E101").getStore().getCount()==0)
				{
					commonSetMsterReadOnlyByArray(
					new Array('waste_noE101','po_noE101',
					'owner_noE101','waste_typeE101','orgNoE101')
					,false);
				};
			}
			});
		}else{
			Ext.example.msg('提示', '请先选择您要删除的行');
			return;
		}
	},
	
	article_noselect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'sodata_WasteAction_getArticle',
			params:{
				articleNo:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('grid_02_E101').getSelectionModel().getSelection();
				data[0].set('articleName',res[0].articleName);
		    	data[0].set('barcode',res[0].barcode);
		    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);
		    	//data[0].set('poBox',0);
		    	data[0].set('planBox',0);
		    	data[0].set('planQmin',0);
		    	data[0].set('planDis',0);
		    	data[0].set('qminOperatePacking',res[0].qminOperatePacking);
		    	data[0].set('unitPacking',res[0].unitPacking);
		    	//data[0].set('packingUnit',res[0].packingUnit);
		    	//data[0].set('packingSpec',res[0].packingSpec);
		    	
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
						    	var gridcount=Ext.getCmp('grid_02_E101').getStore().getCount();
						    	for(var i=0;i<gridcount;i++){
						    		var exp=Ext.getCmp('grid_02_E101').getStore().getAt(i);    		
						    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==data[0].get('packingQty')){
						    			flagCount=flagCount+1;
						    		}
						    	}
						    	var res = Ext.decode(response.responseText);
						    	data[0].set('packingUnit',res[0].packingUnit);
						    	data[0].set('packingSpec',res[0].spec);
						    	
						    	//因电商支持一张单一个商品可以重复，所以把这段代码屏蔽
						    	/*if(flagCount==0){
						    		var res = Ext.decode(response.responseText);
							    	data[0].set('packingUnit',res[0].packingUnit);
							    	data[0].set('spec',res[0].spec);
						    	}else{
						    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
						    		data[0].set('packingQty',null);
						    	}  	*/
						    }
						});
				    }
				    
		    	});
		    }
		});
	},
	
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('grid_02_E101').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	
	packingQtyselect:function(combo){
		var data = Ext.getCmp('grid_02_E101').getSelectionModel().getSelection();
		Ext.Ajax.request({
			method:'post',
			url:'sodata_WasteAction_getPackingUnit',
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
	
	
	
});

//新增前加载
function addWasteE101(){
	Ext.getCmp('form_01_E101').getForm().reset();
	Ext.getCmp('waste_noE101').setValue('保存时自动生成');
	Ext.getCmp('waste_typeE101').setValue('SM');
	Ext.getCmp('wasteDateE101').setValue(new Date());
	Ext.getCmp('orgNoE101').setValue('A0000C');
	Ext.getCmp('grid_02_E101').getStore().removeAll();
	if(Ext.getCmp('owner_noE101').getStore().data.length>0)
	{
		Ext.getCmp('owner_noE101').setValue(Ext.getCmp('owner_noE101').getStore().getAt(0).data.value);		
	}
	commonSetMsterReadOnlyByArray(
			new Array('waste_noE101','po_noE101','wasteDateE101',
			'owner_noE101','waste_typeE101','orgNoE101')
			,false);
	commonSetMsterReadOnlyByArray(
			new Array('waste_noE101')
			,true);
			
	commonEditButton('menuE101','add');
	Ext.getCmp('toolbarE101').items.items[0].enable(true);
	Ext.getCmp('toolbarE101').items.items[1].enable(true);
	Ext.getCmp('po_noE101').focus();
	isCanEditE101=true;
};

//编辑数据
function editExpE101(rowindexE101){
	if(rowindexE101==0)
	{
		Ext.getCmp('menuE101').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menuE101').items.items[0].enable(true);
	}
	if(rowindexE101==Ext.getCmp('grid_01_E101').getStore().getCount()-1)
	{		
		Ext.getCmp('menuE101').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menuE101').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_E101').getStore().getAt(rowindexE101-(Ext.getCmp('grid_01_E101').getStore().currentPage-1)*appConfig.getPageSize());
	
	Ext.getCmp('waste_noE101').setValue(record.data.wasteNo);
	Ext.getCmp('po_noE101').setValue(record.data.poNo);
    Ext.getCmp('owner_noE101').setValue(record.data.ownerNo);
    Ext.getCmp('wasteDateE101').setValue(record.data.wasteDate);
    Ext.getCmp('waste_typeE101').setValue(record.data.wasteType);
    Ext.getCmp('orgNoE101').setValue(record.data.orgNo);
    	commonSetMsterReadOnlyByArray(
				new Array('waste_noE101','po_noE101','wasteDateE101',
				'owner_noE101','waste_typeE101','orgNoE101')
				,true);
	var wheresql={
		wheresql:record.data.wasteNo
	};
	Ext.apply(Ext.getCmp('grid_02_E101').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_E101').getStore().removeAll();
	Ext.getCmp('grid_02_E101').getStore().load();
	
	
};