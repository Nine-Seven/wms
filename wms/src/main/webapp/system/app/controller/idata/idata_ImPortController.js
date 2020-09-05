/**
 * 模块名称：进货手建单
 * 模块编码：4101
 * 创建：JUN 
 */
var rowindex4101=0;
var isCanEdit4101=false;
var saveType4101=0;
var addEdit4101="";

Ext.define('cms.controller.idata.idata_ImPortController', {
	extend : 'Ext.app.Controller',
	id:'idata.idata_ImPortController',
	requires : [ 'cms.view.idata.idata_ImPortUI'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({//查找
			'idata_ImPortUI button[name=detailQuery]':{
				click:this.detailQuery
			},//新增前加载
			'idata_ImPortUI button[name=userAddButton]':{
				click:this.newAdd
			},//上一条记录
			'idata_ImPortUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'idata_ImPortUI button[name=userNextButton]':{
				click:this.userNext
			},//保存
			'idata_ImPortUI button[name=userSaveButton]':{
				click:this.save
			},
			//供应商选择
			'bdef_DefSupplierCombo[id=supplierNo4101]':{
				beforequery:this.supplierNo4101BeforeQuery
			},//删除
			'idata_ImPortUI button[name=userDelButton]':{
				click:this.del
			},//编辑
			'idata_ImPortUI button[name=userEditButton]':{
				click:this.edit
			},//撤销
			'idata_ImPortUI button[name=userUndoButton]':{
				click:this.undo
			},//Grid双击
			'idata_ImPortUI grid[id=grid_01_4101]':{
				itemdblclick:this.grid_01_4101Click,
				selectionchange:this.checksetAndPrint
			},//TAB页切换
			'idata_ImPortUI tabpanel[id=tabPId800000]':{
				tabchange:this.tabPIdtabchange
			},//新增明细
			'idata_ImPortUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除
			'idata_ImPortUI button[name=detailDelete]':{
				click:this.detailDelete
			},//打印
			'idata_ImPortUI button[name=detailPrint]':{
				click:this.detailPrint
			},//选择存储或直通
			'idata_ImPortUI wms_DefFieldValCombo[id=receiveType4101]':{
				select:this.receiveTypeSelect
			},//GRID编辑
			'idata_ImPortUI grid[id=grid_02_4101]':{
				beforeedit:this.grid_02_4101beforeedit,
				edit:this.grid_02_4101edit
			},//验证采购单号
			'idata_ImPortUI form textfield[id=po_no14101]':{
				blur:this.poNoBlur
			},//验证采购发单日
			'idata_ImPortUI form datefield[id=orderDate4101]':{
				blur:this.orderDateBlur
			},//验证预定到货日
			'idata_ImPortUI form datefield[id=requestDate4101]':{
				blur:this.requestDateBlur
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_no4101]':{
				beforequery:this.article_no4101BeforeQuery,
				select:this.article_noselect
			},//客户下拉选择
			'bdef_DefCustCombo[id=cust_no4101]':{
				select:this.custnoSelect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qty4101]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//上传
			'idata_ImPortUI button[name=upload]':{
				click:this.uploadClick
			},//取消订单  undoOrder
			'idata_ImPortUI button[name=undoOrder]':{
				click:this.undoOrder
			},//打印集单
			'idata_ImPortUI button[name=printBut]':{
				click:this.printBut
			}
			
		});
	},
	//供应商选择
	supplierNo4101BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_no4101").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('supplierNo4101').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('supplierNo4101').getStore().removeAll();
			Ext.getCmp('supplierNo4101').getStore().load();
	},
		
	uploadClick:function(){
		Ext.create('cms.view.idata.windows.idataUploadWindow',
		{
			title:'上传'
		}).show();
	},
	
	
	//取消订单
	undoOrder:function(){
		var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.Msg.confirm('提示','确定取消订单？',function(button,text){
					if(button=='yes'){
						Ext.Ajax.request({
							method:'post',
							url:'idata_ImPortAction_closeOrder',
							params:{
								importNo:data[0].data.importNo,
								ownerNo:data[0].data.ownerNo
						    },
						    success:function(response){
						    	var data = Ext.decode(response.responseText);
						    	if(data.isSucc){					
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('grid_01_4101').getStore().removeAll();
							 	  	Ext.getCmp('grid_01_4101').getStore().reload();
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg);
								}
						    }
						});
					}
				});
		}		
	},
	
	//集单打印
	printBut:function(){
		var printFlag= 1 ;
		var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,'请选择集单数据！');
		}else{
			Ext.MessageBox.confirm = function(title, msg, fn, scope) { 
				this.show({ 
			    	title : title, 
				    msg : msg, 
			    	buttons : this.YESNOCANCEL, 
				    fn : fn, 
				    scope : scope, 
				    icon : this.QUESTION 
				    }); 
				return this; 
			};
			
			Ext.Msg.confirm('提示','是否打印？(是：打印预约单 | 否：不打印)',function(button,text){
				if(button=='yes'){
					if(Ext.isEmpty(workSpaceNo))
					{
						Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
						return;
					}
					printFlag=2;
				}else if(button=='no'){
					printFlag=1;
				}else{
					return;
				}
				Ext.Ajax.request({
					method:'post',
					url:'idata_ImPortAction_setAndPrint',
					params:{
						importNo:data[0].data.importNo,
						printFlag:printFlag
				    },
				    success:function(response){
				    	var data = Ext.decode(response.responseText);
				    	if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('grid_01_4101').getStore().removeAll();
					 	  	Ext.getCmp('grid_01_4101').getStore().reload();
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg);
						}
				    }
				});
				
			});				
		}
		
		Ext.MessageBox.confirm = function(title, msg, fn, scope) { 
			this.show({ 
		    	title : title, 
			    msg : msg, 
		    	buttons : this.YESNO, 
			    fn : fn, 
			    scope : scope, 
			    icon : this.QUESTION 
			    }); 
			return this; 
		};
	},
	
	initializtion:function(){
		isCanEdit4101=false;
	},
	//打印预计到货单，目前应该没用了
	detailPrint:function(){
		var record=Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
		if(record.length<=0){
			Ext.example.msg('提示','请选择要打印的单据');
		}else {
			if(record[0].get('importType')=='ID'){
				commPrint( record[0].get('SImportNo'), 'ID9100R' , null, tips4101);
			}else if(record[0].get('importType')=='IS'){
				commPrint( record[0].get('SImportNo'), 'L_IS_ORDER1' , null , tips4101);
			}
		}
 	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=4101;
		queryGrid='grid_01_4101';	
	},
	
	newAdd:function(){
		addImport4101();
		addEdit4101='add';
		bindEnterSkip($('#form_01_4101'));//调用键盘处理方法
	},
	
	userPrev:function(){
		rowindex4101=rowindex4101-1;
		editImport4101(rowindex4101);
	},
	
	userNext:function(){
		rowindex4101=rowindex4101+1;
		editImport4101(rowindex4101);
	},
	
	save:function(){
		var grid=Ext.getCmp('grid_02_4101');
		var gridcount=Ext.getCmp('grid_02_4101').getStore().getCount();
		if(saveType4101=='0'){
			if(gridcount>0){
				for(var i=0;i<gridcount;i++){
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[1].dataIndex))){
						Ext.example.msg('提示','请输入商品编码');
						return;
					}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[7].dataIndex))){
						Ext.example.msg('提示','请输入包装数量');
						return;
					}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[10].dataIndex))){
						Ext.example.msg('提示','请输入采购箱数');
						return;
					}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[10].dataIndex))){
						Ext.example.msg('提示','请输入采购零散数');
						return;
					}
				}
			}else{			
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
		}else if(saveType4101=='1'){
			if(gridcount>0){
				if(!commonCheckdetailgrid('grid_02_4101',0,gridcount-1))
				{
					return;
				}
			}else{			
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
		}
		var importNo=Ext.getCmp('import_no4101').getValue();
		var poNo=Ext.getCmp('po_no14101').getValue();
		var supplierNo=Ext.getCmp('supplierNo4101').getValue();
		var ownerNo=Ext.getCmp('owner_no4101').getValue();
		var receiveType=Ext.getCmp('receiveType4101').getValue();
		var orderDate= Ext.util.Format.date(Ext.getCmp('orderDate4101').getValue(), 'Y-m-d');
		var requestDate= Ext.util.Format.date(Ext.getCmp('requestDate4101').getValue(), 'Y-m-d');
		var endDate=Ext.getCmp('endDate4101').getValue();
		var orderEndDate=Ext.Date.add(new Date(Ext.getCmp('orderDate4101').getValue()), Ext.Date.DAY, Ext.getCmp('endDate4101').getValue());
		var importRemark=Ext.getCmp('importRemark4101').getValue();
		var orgNo=Ext.getCmp('orgNo4101').getValue();
		var takeType=Ext.getCmp('takeType4101').getValue();
		var receive2='';
		if(receiveType=='IS'){
			receive2=0;
		}else if(receiveType=='ID'){
			receive2=1;
		};
		
		var master={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:ownerNo,
				importNo:importNo
			},
			importType:receiveType,
			poType:receiveType,
			poNo:poNo,
			supplierNo:supplierNo,
			orderDate:orderDate,
			requestDate:requestDate,
			status:'10',
			createFlag:'0',
			importRemark:importRemark,
			endDate:endDate,
			receiveType:receive2,
			layType:'0',
			classType:receive2,
			orderEndDate:orderEndDate,
			withCodeFlag:'0',
			stockType:'1',
			stockValue:'N',
			deptNo:'N',
			sendFlag:'10',
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date(),
			orgNo:orgNo,
			takeType:takeType
		};
		var detail1=[];
		var detail=[];
		
		//详情MAP
		var detailMap = new Ext.util.HashMap();
		var realDetail = [];
		
		if(saveType4101=='0'){
			for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('grid_02_4101').getStore().getAt(i);
			var d={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					importNo:record.get('importNo'),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					poId:i
				},
				articleNo:record.get('articleNo'),
				packingQty:record.get('packingQty'),
				poQty:record.get('pkQty')*record.get('packingQty'),
				importQty:0,
				status:'10',
				outStockFlag:'0',
				itemType:'0',
				qcFlag:'0',
				qcStatus:'0',
				unitCost:record.get('unitCost')
			};
			if(record.get('pkQty')*record.get('packingQty')==0){
				Ext.example.msg('提示','采购数不能为0');
				return;
			}
			detail.push(d);
			};
		}else if(saveType4101=='1'){
			for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('grid_02_4101').getStore().getAt(i);
			var d={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					importNo:record.get('importNo'),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					poId:i
				},
				articleNo:record.get('articleNo'),
				packingQty:record.get('packingQty'),
				poQty:record.get('pkQty')*record.get('packingQty'),
				importQty:0,
				status:'10',
				outStockFlag:'0',
				itemType:'0',
				qcFlag:'0',
				qcStatus:'0'
			};
			if(record.get('pkQty')*record.get('packingQty')==0){
				Ext.example.msg('提示','采购数不能为0');
				return;
			}
			
			//MAP 唯一key
			var key = record.get('articleNo')+"&"+record.get('packingQty');
			if(detailMap.get(key) == undefined || detailMap.get(key) == null){
				detailMap.add(key,d);
			}else{
				var cuurtObj = detailMap.get(key);
				cuurtObj.poQty+=d.poQty;
				detailMap.removeAtKey(key);
				detailMap.add(key,cuurtObj);
			}
			};
			if(detailMap.getCount() >0){
				realDetail = detailMap.getValues();
			}
			for(var i=0;i<gridcount;i++){
				var record=Ext.getCmp('grid_02_4101').getStore().getAt(i);
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						ownerNo:record.get('ownerNo'),
						importNo:record.get('importNo'),
						articleNo:record.get('articleNo'),
						packingQty:record.get('packingQty'),
						subCustNo:record.get('custNo'),
						custNo:record.get('custNo'),
						poNo:'N'
					},
					poQty:record.get('pkQty')*record.get('packingQty'),
					allotQty:'0',
					status:10,
					rgstName:Ext.get('workerNo').getValue(),
					qcStatus:'0',
					rgstDate:new Date()
				};
				detail1.push(d);
			};
		}
		disableButtonFunc(1,'#menu4101 [name=userSaveButton]','保存');
		var imPortM=Ext.encode(master);
		var imPortD1=Ext.encode(detail);
		var imPortD2=Ext.encode(realDetail);
		var imPortD3=Ext.encode(detail1);
		
		var params={
			jsonMaster:imPortM,
			jsonDetail1:imPortD1,
			jsonDetail2:imPortD2,
			jsonDetail3:imPortD3,
			saveType:saveType4101
		};
		Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_saveImPort',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('import_no4101').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_4101').getStore().getAt(i);
						record.set('importNo',data.obj);
					}
					if(typeof(Ext.getCmp('grid_01_4101'))!=='undefined'){
						Ext.getCmp('grid_01_4101').store.reload();
					}
					commonEditButton('menu4101','save');
					isCanEdit4101=false;
					Ext.getCmp('toolbar4101').items.items[0].disable(true);
					Ext.getCmp('toolbar4101').items.items[1].disable(true);
				}else{
					Ext.Msg.alert('提示',data.msg);
					disableButtonFunc(0,'#menu4101 [name=userSaveButton]','保存');
				}
			}
		});
	},
	
	del:function(){
		var importNo=Ext.String.trim(Ext.getCmp('import_no4101').getValue());
		var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
		Ext.Msg.confirm('提示','确定删除数据？',function(button,text){
			if(button=='yes'){
					var params={
					importNo:importNo
					};
				Ext.Ajax.request({
					method:'post',
					url:'idata_ImPortAction_deleteImPort',
					params:params,
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							if(typeof(Ext.getCmp('grid_01_4101'))!="undefined"){
								var atindex=Ext.getCmp('grid_01_4101').getStore().findExact('importNo',Ext.getCmp('import_no4101').getValue());
								Ext.getCmp('grid_01_4101').getStore().removeAt(atindex);
								var count=Ext.getCmp('grid_01_4101').store.getCount();
									
								if(count==0){
									addImport4101();
								}else if(atindex==-1 && count!=0){
									editImport4101(0);
								}else if(atindex<count){
									editImport4101(atindex);
								}else {
									editImport4101(count-1);
								};
							};
						};
					}
				});
			};
		});
	},
	
	grid_02_4101beforeedit:function(e){
		if(!isCanEdit4101)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	grid_02_4101edit:function(editor,e,eOpts){
		if(e.field=='packingQty'){
			if(Ext.getCmp('receiveType4101').getValue()=='ID'){
				if(!Ext.isEmpty(e.value)){
					if(editor.grid.getStore().
					findBy(function(record, id) {  
							return record.internalId!=editor.context.record.internalId 
							&& record.get('articleNo')==editor.context.record.data.articleNo
							&& record.get('packingQty')==editor.context.record.data.packingQty
							&& record.get('custNo')==editor.context.record.data.custNo;
						})!=-1)				
					{
						editor.context.record.set('packingQty',editor.context.originalValue);
						Ext.example.msg('提示', "【商品编码】、【商品包装】、【客户】不能重复，请重新输入！");
					}
				}
			}else if(Ext.getCmp('receiveType4101').getValue()=='IS'){
				if(!Ext.isEmpty(e.value)){
					if(editor.grid.getStore().
					findBy(function(record, id) {  
							//.log(record);
							return record.internalId!=editor.context.record.internalId 
							&& record.get('articleNo')==editor.context.record.data.articleNo
							&& record.get('packingQty')==editor.context.record.data.packingQty;
						})!=-1)				
					{
						editor.context.record.set('packingQty',editor.context.originalValue);
						Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
					}
				}
			}
		}else if(e.field=='custNo'){
			if(Ext.getCmp('receiveType4101').getValue()=='ID'){
				if(!Ext.isEmpty(e.value)){
					if(editor.grid.getStore().
					findBy(function(record, id) {  
							return record.internalId!=editor.context.record.internalId 
							&& record.get('articleNo')==editor.context.record.data.articleNo
							&& record.get('packingQty')==editor.context.record.data.packingQty
							&& record.get('custNo')==editor.context.record.data.custNo;
						})!=-1)				
					{
						editor.context.record.set('custNo',editor.context.originalValue);
						Ext.example.msg('提示', "【商品编码】、【商品包装】、【客户】不能重复，请重新输入！");
					}
				}
			}
		}
	},
	
	edit:function(){
		isCanEdit4101=true;
		addEdit4101='change';
		commonEditButton('menu4101','edit');
		disableButtonFunc(1,'#menu4101 [name=userPrevButton]','上单');
		disableButtonFunc(1,'#menu4101 [name=userNextButton]','下单');
		Ext.getCmp('toolbar4101').items.items[0].enable(true);
		Ext.getCmp('toolbar4101').items.items[1].enable(true);
		
		var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
		if(data[0].data.createFlag=='0'){
			
			commonSetMsterReadOnlyByArray(
			new Array('orderDate4101','requestDate4101',
			'endDate4101','importRemark4101')
			,false);
		}	
	},
	
	undo:function(){
		if(addEdit4101=='change'){
			var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
			
			if(data.length!=0){
				commonEditButton('menu4101','dbclick');
				editImport4101(data[0].index);
				rowindex4101=data[0].index;
					
				commonSetMsterReadOnlyByArray(
				new Array('import_no4101','po_no14101','supplierNo4101',
				'owner_no4101','receiveType4101','endDate4101',
				'orderDate4101','requestDate4101','orgNo4101'),true);
				Ext.getCmp('toolbar4101').items.items[0].disable(true);
				Ext.getCmp('toolbar4101').items.items[1].disable(true);
			}	
			disableButtonFunc(0,'#menu4101 [name=userEditButton]','修改');
			disableButtonFunc(0,'#menu4101 [name=userPrevButton]','上单');
			disableButtonFunc(0,'#menu4101 [name=userNextButton]','下单');
		}else{	
			isCanEdit4101=false;
			addImport4101();
			Ext.getCmp('grid_02_4101').getStore().removeAll();
			commonSetMsterReadOnlyByArray(
					new Array('import_no4101','po_no14101','supplierNo4101',
					'owner_no4101','receiveType4101','endDate4101',
					'orderDate4101','requestDate4101','orgNo4101'),true);
			Ext.getCmp('toolbar4101').items.items[0].disable(true);
			Ext.getCmp('toolbar4101').items.items[1].disable(true);
			commonEditButton('menu4101','undo');
			disableButtonFunc(1,'#menu4101 [name=userEditButton]','修改');
			}	    
	},
	
	grid_01_4101Click:function(th, record,  item,  index, e, eOpts ){
		
			Ext.getCmp('tabPId800000').items.items[1].setVisible(true);
			Ext.getCmp('toolbar4101').items.items[0].disable(true);
			Ext.getCmp('toolbar4101').items.items[1].disable(true);
	},
	
	checksetAndPrint:function(){
		var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
		if(data.length!=0){
			if(data[0].data.status!=10){
				Ext.getCmp('printButton').setDisabled(true);
			}else{
				Ext.getCmp('printButton').setDisabled(false);
			}
		}
	},
	
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPIdd2_4101i'){
			var data = Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
			if(data.length!=0){
				commonEditButton('menu4101','dbclick');
				editImport4101(data[0].index);
				rowindex4101=data[0].index;
				
				commonSetMsterReadOnlyByArray(
				new Array('import_no4101','po_no14101','supplierNo4101',
				'owner_no4101','receiveType4101','endDate4101',
				'orderDate4101','requestDate4101','orgNo4101','takeType4101'),true);
				
				Ext.getCmp('toolbar4101').items.items[0].disable(true);
				Ext.getCmp('toolbar4101').items.items[1].disable(true);
			}
		}
	},
	
	detailAdd:function(){
		if(Ext.getCmp('endDate4101').getValue()<1){
			Ext.example.msg('提示','到期日必需大于等于1');
			Ext.getCmp('endDate4101').focus();
			return ;
		}
		var store = Ext.getCmp('grid_02_4101').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
		}else{
			if(!commonCheckIsInputAll('form_01_4101')){
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('import_no4101','po_no14101','supplierNo4101',
			'owner_no4101','receiveType4101','endDate4101',
			'orderDate4101','requestDate4101'),true);
		}
		var r=Ext.create('cms.model.idata.idata_ImPort_DModel',{});
		r.set('importNo',Ext.getCmp('import_no4101').getValue());
		r.set('ownerNo',Ext.getCmp('owner_no4101').getValue());
		store.add(r);
		Ext.getCmp('grid_02_4101').editingPlugin.startEdit(count,1);
	},
	
	detailDelete:function(){
		var data = Ext.getCmp('grid_02_4101').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm('提示','确定删除数据',function(button,text){
			if(button=='yes'){
				Ext.getCmp('grid_02_4101').getStore().remove(data);					
				if(Ext.getCmp("grid_02_4101").getStore().getCount()==0)
				{
					commonSetMsterReadOnlyByArray(
					new Array('import_no4101','po_no14101','supplierNo4101',
					'owner_no4101','receiveType4101',
					'orderDate4101','requestDate4101'),false);
				};
			}
			});
		}else{
			Ext.example.msg('提示', '请先选择您要删除的行');
			return;
		}
	},
	
	receiveTypeSelect:function(){
		if(Ext.getCmp('receiveType4101').getValue()=='IS'){
			Ext.getCmp('cust_no4101List').setVisible(false);
			Ext.getCmp('cust_name4101List').setVisible(false);
			saveType4101=0;
		}else if(Ext.getCmp('receiveType4101').getValue()=='ID'){
			Ext.getCmp('cust_no4101List').setVisible(true);
			Ext.getCmp('cust_name4101List').setVisible(true);
			saveType4101=1;
		}
	},
	
	poNoBlur:function(){
		if(Ext.getCmp('import_no4101').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_checkPoNo',
			params:{
				poNo:Ext.getCmp('po_no14101').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg('提示','该单号已有，请重新录入');
		    		Ext.getCmp('po_no14101').setValue('');
		    		Ext.getCmp('po_no14101').focus();
		    	}
		    }
			});
		};
		
	},
	
	orderDateBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('orderDate4101').getValue())
		&& !Ext.isEmpty(Ext.getCmp('requestDate4101').getValue())){
			if(Ext.getCmp('requestDate4101').getValue()!=''
				&& Ext.getCmp('requestDate4101').getValue()<
					Ext.getCmp('orderDate4101').getValue()){
				Ext.example.msg('提示','预订到货日不能小于采购发单日');
				Ext.getCmp('orderDate4101').setValue('');		
				Ext.getCmp('orderDate4101').focus();
			}
		};
	},
	
	requestDateBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('orderDate4101').getValue())
		&& !Ext.isEmpty(Ext.getCmp('requestDate4101').getValue())){
			if(Ext.getCmp('orderDate4101').getValue()!=''
				&& Ext.getCmp('orderDate4101').getValue()>
					Ext.getCmp('requestDate4101').getValue()){
				Ext.example.msg('提示','采购发单日不能大于预订到货日');
				Ext.getCmp('requestDate4101').setValue('');
				Ext.getCmp('requestDate4101').focus();
			}
		};
	},
	
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('grid_02_4101').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	
	packingQtyselect:function(combo){
		var data = Ext.getCmp('grid_02_4101').getSelectionModel().getSelection();
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
			params:{
				strArticleNo:data[0].get('articleNo'),
				strPackingQty:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	data[0].set('packingUnit',res[0].packingUnit);
		    	data[0].set('spec',res[0].spec);
		    	data[0].set('pkQty',0);
		    }
		});
	},
	//商品加载前
	article_no4101BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_no4101").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_no4101').getStore().proxy.extraParams,strWheresql);
	},
	article_noselect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_getImportArticle',
			params:{
				articleNo:combo.getValue()
		    },
		    
		    success:function(response){
		    	
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('grid_02_4101').getSelectionModel().getSelection();
				data[0].set('articleName',res[0].articleName);
		    	data[0].set('barcode',res[0].barcode);
		    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);
		    	data[0].set('pkQty',0);
		    	Ext.Ajax.request({
					method:'post',
					url:'bdef_DefarticleAction_queryPackingQtyAndUnitAndSpec',
					params:{
						strArticleNo:combo.getValue()
				    },
				    success:function(response){
			    		var res = Ext.decode(response.responseText);
				    	var flagCount=0;
				    	var gridcount=Ext.getCmp('grid_02_4101').getStore().getCount();
				    	for(var i=0;i<gridcount;i++){
				    		var exp=Ext.getCmp('grid_02_4101').getStore().getAt(i);    		
				    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==res[0].packingQty){
				    			flagCount=flagCount+1;
				    		}
				    	}
				    	if(flagCount==0){
				    		data[0].set('packingQty',res[0].packingQty);
					    	data[0].set('packingUnit',res[0].packingUnit);
					    	data[0].set('spec',res[0].spec);
				    	}else{
				    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
				    		data[0].set('packingQty',null);
				    	}
				    	if(res[0].packingQty==''){
					    	Ext.Ajax.request({
							method:'post',
							url:'bdef_DefarticleAction_getMaxArticlePacking',
							params:{
								strWheresql:combo.getValue()
						    },
						    success:function(response){
						    	var res = Ext.decode(response.responseText);
						    	data[0].set('packingQty',res);
						    	if(res==null){
						    		Ext.Ajax.request({
						    			method:'post',
						    			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
						    			params:{
						    				strArticleNo:combo.getValue(),
						    				strPackingQty:'1'
						    		    },
						    		    success:function(response){
						    		    	var res = Ext.decode(response.responseText);
										    	var flagCount=0;
										    	var gridcount=Ext.getCmp('grid_02_4101').getStore().getCount();
										    	for(var i=0;i<gridcount;i++){
										    		var exp=Ext.getCmp('grid_02_4101').getStore().getAt(i);    		
										    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')=='1'){
										    			flagCount=flagCount+1;
										    		}
										    	}
										    	if(flagCount==0){
										    		data[0].set('packingQty','1');
								    		    	data[0].set('packingUnit',res[0].packingUnit);
								    		    	data[0].set('spec',res[0].spec);
								    		    	data[0].set('pkQty',0);
										    	}else{
										    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
										    		data[0].set('packingQty',null);
										    	}  	
						    		    }
						    		});
						    	}else{
						    		Ext.Ajax.request({
										method:'post',
										url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
										params:{
											strArticleNo:combo.getValue(),
											strPackingQty:res
									    },
									    success:function(response){
									    	var flagCount=0;
									    	var gridcount=Ext.getCmp('grid_02_4101').getStore().getCount();
									    	for(var i=0;i<gridcount;i++){
									    		var exp=Ext.getCmp('grid_02_4101').getStore().getAt(i);    		
									    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==data[0].get('packingQty')){
									    			flagCount=flagCount+1;
									    		}
									    	}
									    	if(flagCount==0){
									    		var res = Ext.decode(response.responseText);
										    	data[0].set('packingUnit',res[0].packingUnit);
										    	data[0].set('spec',res[0].spec);
									    	}else{
									    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
									    		data[0].set('packingQty',null);
									    	}  	
									    }
									});
						    	}
						    }
				    	});
				    	}
				    }
				});
		    }
		});
	},
	
	custnoSelect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefCustAction_getCustName',
			params:{
				custNo:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('grid_02_4101').getSelectionModel().getSelection();
		    	data[0].set('custName',res[0]);
		    }
		});
	}
});

//新增前加载
function addImport4101(){
	Ext.getCmp('form_01_4101').getForm().reset();
	Ext.getCmp('import_no4101').setValue('保存时自动生成');
	Ext.getCmp('orderDate4101').setValue(new Date());
	Ext.getCmp('requestDate4101').setValue(new Date());
	Ext.getCmp('receiveType4101').setValue('IS');
	Ext.getCmp('orgNo4101').setValue('001');
	Ext.getCmp('takeType4101').setValue('0');
	Ext.getCmp('grid_02_4101').getStore().removeAll();
	if(Ext.getCmp('owner_no4101').getStore().data.length>0)
	{
		Ext.getCmp('owner_no4101').setValue(Ext.getCmp('owner_no4101').getStore().getAt(0).data.value);		
	}
	Ext.getCmp('endDate4101').setValue(7);
	Ext.get('rgstName4101').dom.innerHTML=Ext.get('workerNo').getValue();
	Ext.get('rgstDate4101').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
	Ext.get('updtName4101').dom.innerHTML='';
	Ext.get('updtDate4101').dom.innerHTML='';
	commonSetMsterReadOnlyByArray(
	new Array('po_no14101','supplierNo4101',
	'owner_no4101','receiveType4101','endDate4101','importRemark4101',
	'orderDate4101','requestDate4101','orgNo4101','takeType4101'),false);
	commonSetMsterReadOnlyByArray(
	new Array('import_no4101'),true);
	
	commonEditButton('menu4101','add');
	Ext.getCmp('toolbar4101').items.items[0].enable(true);
	Ext.getCmp('toolbar4101').items.items[1].enable(true);
	
	if(Ext.getCmp('receiveType4101').getValue()=='IS'){
		Ext.getCmp('cust_no4101List').setVisible(false);
		Ext.getCmp('cust_name4101List').setVisible(false);
	}else if(Ext.getCmp('receiveType4101').getValue()=='ID'){
		Ext.getCmp('cust_no4101List').setVisible(true);
		Ext.getCmp('cust_name4101List').setVisible(true);
	}
	saveType4101=0;
	Ext.getCmp('po_no14101').focus();
	isCanEdit4101=true;
};

//填充数据
function editImport4101(rowindex4101){
	if(rowindex4101==0)
	{
		Ext.getCmp('menu4101').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu4101').items.items[0].enable(true);
	}
	if(rowindex4101==Ext.getCmp('grid_01_4101').getStore().getCount()-1)
	{		
		Ext.getCmp('menu4101').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu4101').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_4101').getStore().getAt(rowindex4101-(Ext.getCmp('grid_01_4101').getStore().currentPage-1)*appConfig.getPageSize());
	if(record.data.createFlag==0){
		
		Ext.getCmp('menu4101').items.items[6].enable(true);
		if(record.data.status=='10'){
			Ext.getCmp('menu4101').items.items[6].enable(true);
		}else{
			Ext.getCmp('menu4101').items.items[6].disable(true);
		}
	}else if(record.data.createFlag==1){
		Ext.getCmp('menu4101').items.items[6].disable(true);
	}
	
	if(record.data.status=='10'){
		disableButtonFunc(0,'#menu4101 [name=userEditButton]','修改');	
	}else{
		disableButtonFunc(1,'#menu4101 [name=userEditButton]','修改');
	}
	
    Ext.getCmp('owner_no4101').setValue(record.data.ownerNo);
    
    Ext.getCmp('supplierNo4101').getStore().add({
    	value:record.data.supplierNo,
    	dropValue:'['+record.data.supplierNo+']'+record.data.supplierName,
    	text:record.data.supplierName
    });
    Ext.getCmp('supplierNo4101').setValue(record.data.supplierNo);
    Ext.getCmp('orgNo4101').setValue(record.data.orgNo);
    Ext.getCmp('takeType4101').setValue(record.data.takeType);
    Ext.getCmp('import_no4101').setValue(record.data.importNo);
	Ext.getCmp('po_no14101').setValue(record.data.poNo);
	Ext.getCmp('receiveType4101').setValue(String(record.data.poType));
	Ext.getCmp('orderDate4101').setValue(record.data.orderDate);
	Ext.getCmp('requestDate4101').setValue(record.data.requestDate);
	Ext.getCmp('endDate4101').setValue(record.data.endDate);
	Ext.getCmp('importRemark4101').setValue(record.data.importRemark);
	Ext.get('rgstName4101').dom.innerHTML=record.data.rgstName;
	Ext.get('rgstDate4101').dom.innerHTML=record.data.rgstDate;
	Ext.get('updtName4101').dom.innerHTML=record.data.updtName;
	Ext.get('updtDate4101').dom.innerHTML=record.data.updtDate;
	
	if(Ext.getCmp('receiveType4101').getValue()=='IS'){
		Ext.getCmp('cust_no4101List').setVisible(false);
		Ext.getCmp('cust_name4101List').setVisible(false);
	}else if(Ext.getCmp('receiveType4101').getValue()=='ID'){
		Ext.getCmp('cust_no4101List').setVisible(true);
		Ext.getCmp('cust_name4101List').setVisible(true);
	}
	
	var sql='';
	var importNo=record.data.importNo;
	var sql={
		importNo:importNo,
		receiveType:Ext.getCmp('receiveType4101').getValue()
	};
	var wheresql={
		wheresql:sql
	};
	Ext.apply(Ext.getCmp('grid_02_4101').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_4101').getStore().removeAll();
	Ext.getCmp('grid_02_4101').getStore().load();
	isCanEdit4101=false;
};

//打印后更改状态
function tips4101(){
	var record=Ext.getCmp('grid_01_4101').getSelectionModel().getSelection();
	if(record.length<=0){
		return;
	}else {
		Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_updatePrinter',
			params:{
				importNo:record[0].get('importNo'),
				importType:record[0].get('importType')
		    },
		    success:function(response){
		    	Ext.getCmp('grid_01_4101').getStore().load();
		    }
		});
	}
};

function checkqty4101(){
	var gridcount=Ext.getCmp('grid_02_4101').getStore().getCount();
	var data = Ext.getCmp('grid_02_4101').getSelectionModel().getSelection();
	for(var i=0;i<gridcount;i++){
		var record=Ext.getCmp('grid_02_4101').getStore().getAt(i);
		if(record.data.articleNo==data[0].get('articleNo')
		&& record.data.packingQty==data[0].get('packingQty')){
			return;
		}
	}
};