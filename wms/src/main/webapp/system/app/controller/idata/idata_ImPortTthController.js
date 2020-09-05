/**
 * 模块名称：进货手建单
 * 模块编码：4102
 * 创建：hkl 
 */
var rowindex4102=0;
var isCanEdit4102=false;
var saveType4102 =0;
var addEdit4102="";
var flag='1';
var g_ClassType4501='';
var g_canEditPoNo='';

Ext.define('cms.controller.idata.idata_ImPortTthController', {
	extend : 'Ext.app.Controller',
	id:'idata.idata_ImPortTthController',
	requires : [ 'cms.view.idata.idata_ImPortTthUI'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({//查找
			'idata_ImPortTthUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			//取消订单  undoOrder
			'idata_ImPortTthUI button[name=undoOrder]':{
				click:this.undoOrder},
			//修改
			'idata_ImPortTthUI button[name=userEditButton]':{
				click:this.newEdit
			},//新增前加载
			'idata_ImPortTthUI button[name=userAddButton]':{
				click:this.newAdd
			},//上一条记录
			'idata_ImPortTthUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'idata_ImPortTthUI button[name=userNextButton]':{
				click:this.userNext
			},
			//保存
			'idata_ImPortTthUI button[name=userSaveButton]':{
				click:this.save
			},
			//供应商选择
			'bdef_DefSupplierCombo[id=supplierNo4102]':{
				beforequery:this.supplierNo4102BeforeQuery
			},	
			//撤销
			'idata_ImPortTthUI button[name=userUndoButton]':{
				click:this.undo
			},
			//Grid双击
			'idata_ImPortTthUI grid[id=grid_01_4102]':{
				itemdblclick:this.grid_01_4102Click			
			},//TAB页切换
			'idata_ImPortTthUI tabpanel[id=tabPId4102]':{
				tabchange:this.tabPIdtabchange
			},//TAB页切换采购订单和分播明细
			/*'idata_ImPortTthUI tabpanel[id=tabPId800001]':{
				tabchange:this.tabPIdtabchange02
			},*///新增明细
			'idata_ImPortTthUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除
			'idata_ImPortTthUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			//选择存储或直通
			'idata_ImPortTthUI wms_DefFieldValCombo[id=receiveType4102]':{
				select:this.receiveTypeSelect
			},
			//GRID不可编辑
			'idata_ImPortTthUI grid[id=grid_02_4102]':{
				beforeedit:this.grid_02_4102beforeedit,
			},//编辑分播明细grid
			'idata_ImPortTthUI grid[id=grid_03_4102]':{
				edit:this.grid_03_4102Edit
			},
			//验证采购单号
			'idata_ImPortTthUI form textfield[id=po_no14102]':{
				blur:this.poNoBlur
			},//验证采购发单日
			'idata_ImPortTthUI form datefield[id=orderDate4102]':{
				blur:this.orderDateBlur
			},//验证预定到货日
			'idata_ImPortTthUI form datefield[id=requestDate4102]':{
				blur:this.requestDateBlur
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_no4102]':{
				beforequery:this.article_no4102BeforeQuery,
				select:this.article_noselect
			},//商品下拉选择(分播)
			'bdef_DefArticleCombo[id=article_no4102_2]':{
				beforequery:this.article_no4102BeforeQuery,
				select:this.article_noselect
			},//客户下拉选择
			'bdef_DefCustCombo[id=cust_no4102]':{
				select:this.custnoSelect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qty4102]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//包装选择（分播）
			'bdef_PackingQtyCombo[id=packing_qty4102_2]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},
			//上传
			'idata_ImPortTthUI button[name=upload]':{
				click:this.uploadClick
			},
			/*,//提交
			'idataUploadWindow button[id=upLoad_w4102]':{
				click:this.upLoad_wClick
			}*/
			//确认差异事件
			'idata_ImPortTthUI button[id=btnDiffConfirm4102]':{
				click:this.DiffConfirm
			},
			
		});
	},
	//供应商选择
	supplierNo4102BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp('owner_no4102').getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1),
				strWheresql:Ext.getCmp('supplierNo4102').getValue()
			};
			Ext.apply(Ext.getCmp('supplierNo4102').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('supplierNo4102').getStore().removeAll();
			Ext.getCmp('supplierNo4102').getStore().load();
	},
	
	uploadClick:function(){
		Ext.create('cms.view.idata.windows.idataUploadWindow',
		{
			title:'上传'
		}).show();
	},
	
	initializtion:function(){
		isCanEdit4102=false;
		//显示变量0为不显示，1为显示
		var planBox4102_1=commonGetModuleField('4102','planBox')[0].flag;
		var planQmin4102_1=commonGetModuleField('4102','planQmin')[0].flag;
		var planDis4102_1=commonGetModuleField('4102','planDis')[0].flag;
		var packingUnit4102_1=commonGetModuleField('4102','packingUnit')[0].flag;
		var packingSpec4102_1=commonGetModuleField('4102','packingSpec')[0].flag;

		var planBox4102_2=commonGetModuleField('4102','planBox')[0].flag;
		var planQmin4102_2=commonGetModuleField('4102','planQmin')[0].flag;
		var planDis4102_2=commonGetModuleField('4102','planDis')[0].flag;
		var packingUnit4102_2=commonGetModuleField('4102','packingUnit')[0].flag;
		var packingSpec4102_2=commonGetModuleField('4102','packingSpec')[0].flag;

		
		if(planBox4102_1==0){
			Ext.getCmp('planBox4102_1').setVisible(false);
		}
		if(planQmin4102_1==0){
			Ext.getCmp('planQmin4102_1').setVisible(false);
		}
		if(planDis4102_1==0){
			Ext.getCmp('planDis4102_1').setVisible(false);
		}
		if(packingUnit4102_1==0){
			Ext.getCmp('packingUnit4102_1').setVisible(false);
		}
		if(packingSpec4102_1==0){
			Ext.getCmp('packingSpec4102_1').setVisible(false);
		}
		if(planBox4102_2==0){
			Ext.getCmp('planBox4102_2').setVisible(false);
		}
		if(planQmin4102_2==0){
			Ext.getCmp('planQmin4102_2').setVisible(false);
		}
		if(planDis4102_2==0){
			Ext.getCmp('planDis4102_2').setVisible(false);
		}
		if(packingUnit4102_2==0){
			Ext.getCmp('packingUnit4102_2').setVisible(false);
		}
		if(packingSpec4102_2==0){
			Ext.getCmp('packingSpec4102_2').setVisible(false);
		}
		
	},
	//打印预计到货单，目前应该没用了
	detailPrint:function(){
		var record=Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
		if(record.length<=0){
			Ext.example.msg('提示','请选择要打印的单据');
		}else {
			if(record[0].get('classType')=='1'){
				commPrint( record[0].get('SImportNo'), 'ID9100R' , null, tips4102);
			}else if(record[0].get('classType')=='0'){
				commPrint( record[0].get('SImportNo'), $reportId.L_IS_READY_CHECK , null , tips4102);
			}
		}
 	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=4102;
		queryGrid='grid_01_4102';	
	},
	
	newEdit:function(){
	/*	g_canEditPoNo
		=commonGetIdataType(Ext.getCmp('owner_no4102').getValue(),
				Ext.getCmp('receiveType4102').getValue(),'modify_flag')[0].columnValue;//修改原单标识: 1允许;0：不允许]
		if(g_canEditPoNo=='0'){
			Ext.example.msg($i18n.prompt,'该单据类型不允许修改原单');
			return;
		}else{*/
			var gridcount=Ext.getCmp('grid_02_4102').getStore().getCount();	
			var lastDate=Ext.getCmp('grid_02_4102').getStore().getAt(gridcount-1);
			if(lastDate.get('articleNo')=='合计'){
			Ext.getCmp('grid_02_4102').getStore().remove(lastDate);
			}
			var gridcount1=Ext.getCmp('grid_03_4102').getStore().getCount();
			if(gridcount1>0){
			var lastDate1=Ext.getCmp('grid_03_4102').getStore().getAt(gridcount1-1);
			if(lastDate1.get('custNo')=='合计'){
			Ext.getCmp('grid_03_4102').getStore().remove(lastDate1);
			}}

			addEdit4102='change';
			//'po_no14102', 不允许修改原单号 huangb 20160721	
			debugger;
			//8-17修改
			var data = Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
			if(data[0].data.createFlag=='0'){//wms自建
				if(data[0].data.status =='10'){//状态为10，验收量为0
					Ext.Ajax.request({
						method:'post',
						async:false,
						url:'idata_ImPortAction_getCheckNumber',
						params:{
							importNo:data[0].data.importNo,
					    },
					    success:function(response){
					    	var data = Ext.decode(response.responseText);
					    	if(data.isSucc){
					    		//判断验收量是否为0 
					    		if(data.obj == '0'){
					    			commonSetMsterReadOnlyByArray(
											new Array('po_no14102','owner_no4102','takeType4102',
													'orderDate4102','requestDate4102','endDate4102',
													'orgNo4102','rsvVarod1_4102','importRemark4102'),false);
									//控制明细
									Ext.getCmp('toolbar4102').items.items[0].enable(true);
									Ext.getCmp('toolbar4102_2').items.items[0].enable(true);
									Ext.getCmp('toolbar4102').items.items[1].enable(true);
									Ext.getCmp('toolbar4102_2').items.items[1].enable(true);
									Ext.getCmp('tabPId4102_1').setDisabled(false);
									Ext.getCmp('tabPId4102_2').setDisabled(false);
									isCanEdit4102 = true;	//这个全局变量有控制表格是否可编辑
					    		}else{
					    			commonSetMsterReadOnlyByArray(
											new Array('importRemark4102'),false);
									isCanEdit4102 = false;
					    		}
					    	}
					    }
					});
				}else if(data[0].data.status =='11'){
					commonSetMsterReadOnlyByArray(
							new Array('endDate4102','orderDate4102','importRemark4102'),false);
					isCanEdit4102 = false;
				}else{
					commonSetMsterReadOnlyByArray(
							new Array('importRemark4102'),false);
					isCanEdit4102 = false;
				}
			}else if(data[0].data.createFlag=='1'){//ERP下传单据
				commonSetMsterReadOnlyByArray(
						new Array('importRemark4102'),false);
				isCanEdit4102 = false;
			}
			
			commonEditButton('menu4102','add');
			
			Ext.getCmp('po_no14102').focus();
			bindEnterSkip($('#form_01_4102'));//调用键盘处理方法
		
	},
	
	newAdd:function(){
		addImport4102();
		saveType3101='add';
		addEdit4102='add',
		bindEnterSkip($('#form_01_4102'));//调用键盘处理方法
	},
	
	userPrev:function(){
		Ext.getCmp('grid_02_4102').getSelectionModel().deselectAll();
		rowindex4102=rowindex4102-1;
		editImport4102(rowindex4102);
	},
	
	//取消订单
	undoOrder:function(){
		debugger;
		var data = Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.Msg.confirm('提示','确定取消订单？',function(button,text){
					if(button=='yes'){
						debugger;
						Ext.Ajax.request({
							method:'post',
							url:'idata_ImPortAction_closeOrder',
							params:{
								importNo:data[0].data.importNo,
								ownerNo:data[0].data.ownerNo
						    },
						    success:function(response){
						    	debugger;
						    	var data = Ext.decode(response.responseText);
						    	if(data.isSucc){					
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('grid_01_4102').getStore().removeAll();
							 	  	Ext.getCmp('grid_01_4102').getStore().reload();
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg);
								}
						    }
						});
					}
				});
		}		
	},
	userNext:function(){
		Ext.getCmp('grid_02_4102').getSelectionModel().deselectAll();
		rowindex4102=rowindex4102+1;
		editImport4102(rowindex4102);
	},
	
	
	save:function(){
		/*if(!commonCheckIsInputAll('form_01_4102')){
			return;
		}*/
		//判断分播数量不能大于总采购数量
		var receiveType=Ext.getCmp('receiveType4102').getValue();
		/*if(receiveType == "ID"){
			var grid1=Ext.getCmp('grid_02_4102');
			var gridcount1=Ext.getCmp('grid_02_4102').getStore().getCount();
			
			var grid2=Ext.getCmp('grid_03_4102');
			var gridcount2=Ext.getCmp('grid_03_4102').getStore().getCount();

	            for(var i=0;i<gridcount1;i++){
			     	var a1 = grid1.getStore().getAt(i).get(grid1.columns[1].dataIndex);
			     	
			     	for(var i=0;i<gridcount2;i++){
						var a2 = grid2.getStore().getAt(i).get(grid2.columns[3].dataIndex);
			     	    //如果为同一个商品
						if(a1==a2){
					     	var qtypacking1 = grid1.getStore().getAt(i).get(grid1.columns[5].dataIndex);
					     	var qtybox1 = grid1.getStore().getAt(i).get(grid1.columns[8].dataIndex);
					     	var qtypcs1 = grid1.getStore().getAt(i).get(grid1.columns[9].dataIndex);
                            //采购订单的采购数量
					     	var qty1 = qtybox1*qtypacking1+qtypcs1;
			     	        
					     	var qtypacking2 = grid2.getStore().getAt(i).get(grid2.columns[7].dataIndex);
					     	var qtybox2 = grid2.getStore().getAt(i).get(grid2.columns[11].dataIndex);
					     	var qtypcs2 = grid2.getStore().getAt(i).get(grid2.columns[12].dataIndex);
						    //分播明细的采购数量
					     	var qty2 = qtybox2*qtypacking2+qtypcs2;
					     	if(qty2>qty1){
					     		Ext.example.msg('提示','该商品'+a1+'总采购数量小于分配数量，请重新分配');
								return false;
					     	}

						}
			     	}
        
	            }
	            
			
		}
		*/
/*		debugger
		//判断采购数商品中有分播商品
		var article = [];
		if(receiveType == "ID"){
			var grid1=Ext.getCmp('grid_02_4102');
			var gridcount1=Ext.getCmp('grid_02_4102').getStore().getCount();
			
			var grid2=Ext.getCmp('grid_03_4102');
			var gridcount2=Ext.getCmp('grid_03_4102').getStore().getCount();
			 for(var i=0;i<gridcount1;i++){
			       var a1 = grid1.getStore().getAt(i).get(grid1.columns[1].dataIndex);
			       article.push(a1);
				  
			  }
	            for(var i=0;i<gridcount2;i++){
					var a2 = grid2.getStore().getAt(i).get(grid2.columns[3].dataIndex);
					   if(article){
				     		Ext.example.msg('提示','该商品不在采购订单里，请重新分配');
							return false;
		     	       }
					
				}
        
	       }
	            */
        var grid=Ext.getCmp('grid_02_4102');
	    var gridcount=Ext.getCmp('grid_02_4102').getStore().getCount();
	    var grid2=Ext.getCmp('grid_03_4102');
		var gridcount2=Ext.getCmp('grid_03_4102').getStore().getCount();
		if(g_ClassType4501 == "0"){
			if(gridcount>0){
				for(var i=0;i<gridcount;i++){
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[1].dataIndex))){
						Ext.example.msg('提示','请输入商品编码');
						return;
					}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[5].dataIndex))){
						Ext.example.msg('提示','请输入包装数量');
						return;
					}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[8].dataIndex))){
						Ext.example.msg('提示','请输入采购箱数');
						return;
					}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[9].dataIndex))){
						Ext.example.msg('提示','请输入采购中包数');
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
		 }else if(g_ClassType4501 == "1"){

			if(gridcount>0){
				if(!commonCheckdetailgrid('grid_02_4102',0,gridcount-1))
				{
					return;
				}
				
			}else{			
				Ext.example.msg('提示',"采购订单表体不允许为空，请输入表体！");
				return;
			}
			if(gridcount2>0){
				if(!commonCheckdetailgrid('grid_03_4102',0,gridcount2-1)){
					return;
				}
			}
		}
	
		var importNo=Ext.getCmp('import_no4102').getValue();
		var poNo=Ext.getCmp('po_no14102').getValue();
		var supplierNo=Ext.getCmp('supplierNo4102').getValue();
		var ownerNo=Ext.getCmp('owner_no4102').getValue();
		var receiveType=Ext.getCmp('receiveType4102').getValue();
		var orderDate= Ext.util.Format.date(Ext.getCmp('orderDate4102').getValue(), 'Y-m-d');
		var requestDate= Ext.util.Format.date(Ext.getCmp('requestDate4102').getValue(), 'Y-m-d');
		var endDate=Ext.getCmp('endDate4102').getValue();
		var orderEndDate=Ext.Date.add(new Date(Ext.getCmp('orderDate4102').getValue()), Ext.Date.DAY, Ext.getCmp('endDate4102').getValue());
		var importRemark=Ext.getCmp('importRemark4102').getValue();
		var orgNo=Ext.getCmp('orgNo4102').getValue();
		var takeType=Ext.getCmp('takeType4102').getValue();
        var rsvVarod1=Ext.getCmp('rsvVarod1_4102').getValue()==undefined? '':Ext.getCmp('rsvVarod1_4102').getValue();
        var rsvVarod2=Ext.getCmp('po_no14102').getValue();//huangb20160721 海关单号 默认原单号
		
        var receive2='';
		if(g_ClassType4501=='0'){
			receive2=0;
			saveType4102=0;
		}else if(g_ClassType4501=='1'){
			receive2=1;
			saveType4102=1;
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
			takeType:takeType,
			rsvVarod1:rsvVarod1,
			rsvVarod2:rsvVarod2
		};
		var detail1=[];
		var detail=[];
		
		//详情MAP
		var detailMap = new Ext.util.HashMap();
		var realDetail = [];
		//存储类型明细
		if(saveType4102=='0'){
			for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('grid_02_4102').getStore().getAt(i);
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
				poQty:record.get('planBox')*record.get('packingQty')
				      +record.get('planQmin')*record.get('qminOperatePacking')
				      +record.get('planDis'),
				importQty:0,
				status:'10',
				outStockFlag:'0',
				itemType:'0',
				qcFlag:'0',
				qcStatus:'0',
				unitCost:record.get('unitCost')
			};
			if(record.get('planBox')*record.get('packingQty')
				      +record.get('planQmin')*record.get('qminOperatePacking')
				      +record.get('planDis')==0){
				Ext.example.msg('提示','采购数不能为0');
				return;
			}
			detail.push(d);
			};
		//直通类型明细：包括采购订单和分播明细
		}else if(saveType4102=='1'){
			//采购订单
			for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('grid_02_4102').getStore().getAt(i);
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
				poQty:record.get('planBox')*record.get('packingQty')
			      +record.get('planQmin')*record.get('qminOperatePacking')
			      +record.get('planDis'),
				importQty:0,
				status:'10',
				outStockFlag:'0',
				itemType:'0',
				qcFlag:'0',
				qcStatus:'0',
				unitCost:record.get('unitCost')
			};
			if(record.get('planBox')*record.get('packingQty')
				      +record.get('planQmin')*record.get('qminOperatePacking')
				      +record.get('planDis')==0){
				Ext.example.msg('提示','采购数不能为0');
				return;
			}
			realDetail.push(d);
			};
			//分播明细
			for(var i=0;i<gridcount2;i++){
				var record=Ext.getCmp('grid_03_4102').getStore().getAt(i);
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						ownerNo:Ext.getCmp('owner_no4102').getValue(),
						importNo:record.get('importNo'),
						articleNo:record.get('articleNo'),
						packingQty:record.get('packingQty'),
						subCustNo:record.get('custNo'),
						custNo:record.get('custNo'),
						poNo:record.get('poNo')
					},
					takeType:record.get('takeType'),
					poQty:record.get('planBox')*record.get('packingQty')
				      +record.get('planQmin')*record.get('qminOperatePacking')
				      +record.get('planDis'),
					allotQty:'0',
					status:10,
					rgstName:Ext.get('workerNo').getValue(),
					qcStatus:'0',
					rgstDate:new Date()
				};
				detail1.push(d);
			};
		}
		disableButtonFunc(1,'#menu4102 [name=userSaveButton]','保存');
		var imPortM=Ext.encode(master);
		var imPortD1=Ext.encode(detail);
		var imPortD2=Ext.encode(realDetail);
		var imPortD3=Ext.encode(detail1);
		if(saveType4102=='0'){
			save4102(gridcount,gridcount2,imPortM,imPortD1,imPortD2,imPortD3,
					saveType4102,g_ClassType4501,importNo);
		}else if(saveType4102=='1'){
			if(gridcount2==0){
				Ext.Msg.confirm($i18n.prompt, '没有添加分播信息，是否保存？',function(button, text){
					if (button == 'yes') {
						save4102(gridcount,gridcount2,imPortM,imPortD1,imPortD2,imPortD3,
								saveType4102,receiveType,importNo);
					}
					if (button == 'no') {
						return;
					}
				});  
			}else{
				save4102(gridcount,gridcount2,imPortM,imPortD1,imPortD2,imPortD3,
						saveType4102,g_ClassType4501,importNo);
			}
		}
		
		Ext.getCmp('toolbar4102').items.items[0].disable(true);
		Ext.getCmp('toolbar4102').items.items[1].disable(true);
	},
	del:function(){
		var importNo=Ext.String.trim(Ext.getCmp('import_no4102').getValue());
		var data = Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
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
							if(typeof(Ext.getCmp('grid_01_4102'))!="undefined"){
								var atindex=Ext.getCmp('grid_01_4102').getStore().findExact('importNo',Ext.getCmp('import_no4102').getValue());
								Ext.getCmp('grid_01_4102').getStore().removeAt(atindex);
								var count=Ext.getCmp('grid_01_4102').store.getCount();
									
								if(count==0){
									addImport4102();
								}else if(atindex==-1 && count!=0){
									editImport4102(0);
								}else if(atindex<count){
									editImport4102(atindex);
								}else {
									editImport4102(count-1);
								};
							};
						};
					}
				});
			};
		});
	},
	
	grid_02_4102beforeedit:function(e){
		if(!isCanEdit4102)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	grid_03_4102Edit:function(editor,e,eOpts){
		if(e.field=='poNo'){
			if(!Ext.isEmpty(e.value)){
				if(editor.grid.getStore().findBy(function(record, id){  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('custNo')!=editor.context.record.data.custNo
					&& record.get('poNo')==editor.context.record.data.poNo;
			    })!=-1){
					Ext.Msg.alert($i18n.prompt, '不同的客户出货单号不允许一样！');
					editor.context.record.set('poNo',null);
					return;
				}
				if(editor.grid.getStore().findBy(function(record, id){  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('custNo')==editor.context.record.data.custNo
					&& record.get('poNo')!=editor.context.record.data.poNo;
			    })!=-1){
					Ext.Msg.alert($i18n.prompt, '相同的客户出货单号必须一致！');
					editor.context.record.set('poNo',null);
					return;
				}
//				for(var i=editor.context.rowIdx;i<Ext.getCmp('grid_03_4102').getStore().data.length;i++)
//				{
//					if(e.value=e.value){
//						Ext.getCmp('grid_03_4102').getStore().getAt(i).set('poNo',e.value);
//
//					}
//				}
			}
		}
		if(e.field=='takeType'){
			if(!Ext.isEmpty(e.value)){
				if(editor.grid.getStore().findBy(function(record, id){  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('custNo')==editor.context.record.data.custNo
					&& record.get('takeType')!=editor.context.record.data.takeType;
			    })!=-1){
					Ext.Msg.alert($i18n.prompt, '相同的客户提货方式必须一致！');
					editor.context.record.set('takeType',null);
					return;
				}
			}
		}
	},
	
	/*grid_03_4102beforeedit:function(e){
		if(!isCanEdit4102)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},*/
	
	edit:function(){
		isCanEdit4102=true;
		addEdit4102='change';
		commonEditButton('menu4102','edit');
		
		var data = Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
		if(data[0].data.createFlag=='0'){
			commonSetMsterReadOnlyByArray(
			new Array('orderDate4102','requestDate4102',
			'endDate4102','importRemark4102')
			,false);
			if(data[0].data.status=='10'){
				isCanEdit4102=true;
				Ext.getCmp('toolbar4102').items.items[0].enable(true);
			Ext.getCmp('toolbar4102').items.items[1].enable(true);
			}else{
				isCanEdit4102=false;
			}
		}
	
	},
	
	undo:function(){
		if(addEdit4102=='change'){
		  isCanEdit4102=false;
		  commonSetMsterReadOnlyByArray(
					new Array('import_no4102','po_no14102','supplierNo4102',
					'owner_no4102','receiveType4102','endDate4102','importRemark4102',
					'orderDate4102','requestDate4102','orgNo4102','rsvVarod1_4102'),true);
		  Ext.getCmp('grid_02_4102').getStore().reload();
		  Ext.getCmp('grid_03_4102').getStore().reload();
		  Ext.getCmp('toolbar4102').items.items[0].disable(true);
		  Ext.getCmp('toolbar4102_2').items.items[0].disable(true);
		  Ext.getCmp('toolbar4102').items.items[1].disable(true);
		  Ext.getCmp('toolbar4102_2').items.items[1].disable(true);
		  commonEditButton('menu4102','dbclick');
		  Ext.getCmp('menu4102').items.items[1].enable(true);
		}else{
		isCanEdit4102=false;
		addImport4102();
		Ext.getCmp('grid_02_4102').getStore().removeAll();
		Ext.getCmp('grid_03_4102').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
				new Array('import_no4102','po_no14102','supplierNo4102',
				'owner_no4102','receiveType4102','endDate4102',
				'orderDate4102','requestDate4102','orgNo4102','rsvVarod1_4102'),true);
		Ext.getCmp('toolbar4102').items.items[0].disable(true);
		Ext.getCmp('toolbar4102_2').items.items[0].disable(true);
        //列表新增，删除图标
		Ext.getCmp('toolbar4102').items.items[1].disable(true);
		Ext.getCmp('toolbar4102_2').items.items[1].disable(true);
		commonEditButton('menu4102','undo');
		}
	},

	grid_01_4102Click:function(th, record,  item,  index, e, eOpts ){
		    flag='1';
			Ext.getCmp('tabPId4102').items.items[1].setVisible(true);
	},
	
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPIdd2_4102i'){
			var data = Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
			if(data.length!=0){
			    commonEditButton('menu4102','dbclick');
				editImport4102(data[0].index);
				rowindex4102=data[0].index;
				
				commonSetMsterReadOnlyByArray(
				new Array('import_no4102','po_no14102','supplierNo4102',
				'owner_no4102','receiveType4102','endDate4102','importRemark4102',
				'orderDate4102','requestDate4102','orgNo4102','takeType4102','rsvVarod1_4102'),true);
				//debugger;
				//8-17添加 
				if(data[0].data.createFlag == '0'){ //wms自建
					 //当状态为结案和取消时，不允许修改，其他状态可以
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menu4102 [name=userEditButton]','修改');	
					}else{
						disableButtonFunc(0,'#menu4102 [name=userEditButton]','修改');
					}
				}else if(data[0].data.createFlag == '1'){ //ERP下传单据
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menu4102 [name=userEditButton]','修改');
					}else{
						disableButtonFunc(0,'#menu4102 [name=userEditButton]','修改');
					}
				}
				
				Ext.getCmp('toolbar4102').items.items[0].disable(true);
				Ext.getCmp('toolbar4102').items.items[1].disable(true);
			}else{
				Ext.getCmp('rsvVarod2_4102').setVisible(false);
				Ext.getCmp('btnDiffConfirm4102').setVisible(false);
				
				Ext.getCmp('grid_02_4102').getStore().removeAll();
				Ext.getCmp('grid_03_4102').getStore().removeAll();
			}
		}
	},
	//选择采购订单商品查看分播明细
	/*tabPIdtabchange02:function(tabPanel,newCard,oldCard,eOpts ){
		var data = Ext.getCmp('grid_02_4102').getSelectionModel().getSelection();
		var importNo = Ext.getCmp('import_no4102').getValue();
		if(data.length != 0){
			if(newCard.itemId=='tabPId4102i_2'){
				articleNo = data[0].get('articleNo');
				if(data.length!=0){
					var wheresql={
							wheresql:importNo,
							articleNo:articleNo
						};
				
				    Ext.apply(Ext.getCmp('grid_03_4102').getStore().proxy.extraParams,wheresql);
				    Ext.getCmp('grid_03_4102').getStore().removeAll();
					Ext.getCmp('grid_03_4102').getStore().load();
				}
			}
		}else{
			var wheresql={
					wheresql:importNo,
					articleNo:''
				};
			 Ext.apply(Ext.getCmp('grid_03_4102').getStore().proxy.extraParams,wheresql);
			 Ext.getCmp('grid_03_4102').getStore().removeAll();
		     Ext.getCmp('grid_03_4102').getStore().load();		     
		}		
	},*/
	detailAdd:function(){
		if(Ext.getCmp('endDate4102').getValue()<1){
			Ext.example.msg('提示','到期日必需大于等于1');
			Ext.getCmp('endDate4102').focus();
			return ;
		}
		//debugger
		var tabPId4102 = Ext.getCmp('tabPId4102_1').isVisible();
		if(tabPId4102 == true){
		var store = Ext.getCmp('grid_02_4102').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
		}else{
			if(!commonCheckIsInputAll('form_01_4102')){
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('import_no4102','po_no14102','supplierNo4102',
			'owner_no4102','receiveType4102','endDate4102',
			'orderDate4102','requestDate4102','orgNo4102',
			'takeType4102','rsvVarod1_4102'),true);
		}
		//store.removeAt(1); 
		var r=Ext.create('cms.model.idata.idata_ImPort_DModel',{});
		r.set('importNo',Ext.getCmp('import_no4102').getValue());
		r.set('ownerNo',Ext.getCmp('owner_no4102').getValue());
		store.add(r);
		Ext.getCmp('grid_02_4102').editingPlugin.startEdit(count,1);
		}else if(tabPId4102 == false){
			var store = Ext.getCmp('grid_03_4102').getStore();
			var count = store.getCount();
			var j = count * 1 - 1;
			if(j>=0){
			}else{
				if(!commonCheckIsInputAll('form_01_4102')){
					return;
				}
				commonSetMsterReadOnlyByArray(
				new Array('import_no4102','po_no14102','supplierNo4102',
				'owner_no4102','receiveType4102','endDate4102',				'orderDate4102','requestDate4102','orgNo4102',
				'takeType4102','rsvVarod1_4102'),true);
			}

			if(flag == '1'){
			Ext.getCmp('grid_03_4102').getStore().removeAll();
			    flag = '0';
			}
			var r=Ext.create('cms.model.idata.idata_ImPort_DModel',{});
			r.set('importNo',Ext.getCmp('import_no4102').getValue());
			r.set('ownerNo',Ext.getCmp('owner_no4102').getValue());
			store.add(r);
			Ext.getCmp('grid_03_4102').editingPlugin.startEdit(count,1);
			
			
			disableButtonFunc(0,'#menu4102 [name=userSaveButton]','保存');
			disableButtonFunc(0,'#menu4102 [name=userUndoButton]','撤销');
		}
	},
	
	detailDelete:function(){
		//debugger
		var tabPId4102 = Ext.getCmp('tabPId4102_1').isVisible();
		
		var data = Ext.getCmp('grid_02_4102').getSelectionModel()
			.getSelection();
		var data2 = Ext.getCmp('grid_03_4102').getSelectionModel()
		.getSelection();
		
		if(tabPId4102 == true){
			if(data.length!='0'){
				Ext.Msg.confirm('提示','确定删除数据',function(button,text){
				if(button=='yes'){
					Ext.getCmp('grid_02_4102').getStore().remove(data);					
					if(Ext.getCmp("grid_02_4102").getStore().getCount()==0)
					{
						commonSetMsterReadOnlyByArray(
						new Array('import_no4102','po_no14102','supplierNo4102',
						'owner_no4102','receiveType4102',
						'orderDate4102','requestDate4102','orgNo4102',
						'takeType4102','rsvVarod1_4102'),false);
					};
				}
				});
			}else{
				Ext.example.msg('提示', '请先选择您要删除的行');
				return;
			}
		}else if(tabPId4102 == false){
			if(data2.length!='0'){
				Ext.Msg.confirm('提示','确定删除数据',function(button,text){
				if(button=='yes'){
					Ext.getCmp('grid_03_4102').getStore().remove(data2);					
					if(Ext.getCmp("grid_03_4102").getStore().getCount()==0)
					{
						commonSetMsterReadOnlyByArray(
						new Array('import_no4102','po_no14102','supplierNo4102',
						'owner_no4102','receiveType4102',
						'orderDate4102','requestDate4102','orgNo4102',
						'takeType4102','rsvVarod1_4102'),false);
					};
				}
				});
			}else{
				Ext.example.msg('提示', '请先选择您要删除的行');
				return;
			}
		}
	},
	
	receiveTypeSelect:function(combo){
		getClassType4501(Ext.getCmp('owner_no4102').getValue(),combo.getValue());
		//存储
		if(g_ClassType4501=='0'){
			Ext.getCmp('tabPId4102_2').setDisabled(true);
			saveType4102=0;
		//直通
		}else if(g_ClassType4501=='1'){
			Ext.getCmp('tabPId4102_1').setDisabled(false);
			Ext.getCmp('tabPId4102_2').setDisabled(false);
			saveType4102=1;

		}
	},
	
	poNoBlur:function(){
		if(Ext.getCmp('import_no4102').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_checkPoNo',
			params:{
				poNo:Ext.getCmp('po_no14102').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg('提示','该单号已有，请重新录入');
		    		Ext.getCmp('po_no14102').setValue('');
		    		Ext.getCmp('po_no14102').focus();
		    	}
		    }
			});
		};
		
	},
	
	orderDateBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('orderDate4102').getValue())
		&& !Ext.isEmpty(Ext.getCmp('requestDate4102').getValue())){
			if(Ext.getCmp('requestDate4102').getValue()!=''
				&& Ext.getCmp('requestDate4102').getValue()<
					Ext.getCmp('orderDate4102').getValue()){
				Ext.example.msg('提示','预订到货日不能小于采购发单日');
				Ext.getCmp('orderDate4102').setValue('');		
				Ext.getCmp('orderDate4102').focus();
			}
		};
	},
	
	requestDateBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('orderDate4102').getValue())
		&& !Ext.isEmpty(Ext.getCmp('requestDate4102').getValue())){
			if(Ext.getCmp('orderDate4102').getValue()!=''
				&& Ext.getCmp('orderDate4102').getValue()>
					Ext.getCmp('requestDate4102').getValue()){
				Ext.example.msg('提示','采购发单日不能大于预订到货日');
				Ext.getCmp('requestDate4102').setValue('');
				Ext.getCmp('requestDate4102').focus();
			}
		};
	},
	
	packingQtyfocus:function(th){
		var tabPId4102 = Ext.getCmp('tabPId4102_1').isVisible();
		//判断在哪个grid
		if(tabPId4102 == true){
		th.getStore().proxy.extraParams.strWheresql = 
		    Ext.getCmp('grid_02_4102').getSelectionModel().getSelection()[0].get('articleNo');
		}else if(tabPId4102 == false){
			th.getStore().proxy.extraParams.strWheresql = 
			Ext.getCmp('grid_03_4102').getSelectionModel().getSelection()[0].get('articleNo');
		}
		th.getStore().load();
		
	},
	
	packingQtyselect:function(combo){
		var tabPId4102 = Ext.getCmp('tabPId4102_1').isVisible();
		//判断在哪个grid
		if(tabPId4102 == true){
		var data = Ext.getCmp('grid_02_4102').getSelectionModel().getSelection();
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
		    	data[0].set('packingSpec',res[0].spec);
		    	//data[0].set('planBox',0);
		    	//data[0].set('planQmin',0);
		    }
		});
		}else if(tabPId4102 == false){
			var data = Ext.getCmp('grid_03_4102').getSelectionModel().getSelection();
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
			    	data[0].set('packingSpec',res[0].spec);
			    	//data[0].set('pobox',0);
			    	//data[0].set('popcs',0);
			    }
			});
		}
	},
	//商品加载前
	article_no4102BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_no4102").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			//debugger
			var tabPId4102 = Ext.getCmp('tabPId4102_1').isVisible();
	    	//判断在哪个grid
			if(tabPId4102 == true){
			    Ext.apply(Ext.getCmp('article_no4102').getStore().proxy.extraParams,strWheresql);
			    Ext.getCmp('article_no4102').getStore().removeAll();
				Ext.getCmp('article_no4102').getStore().reload();
			}else if(tabPId4102 == false){
				Ext.apply(Ext.getCmp('article_no4102_2').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('article_no4102_2').getStore().removeAll();
				Ext.getCmp('article_no4102_2').getStore().reload();

			}
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
		    	var tabPId4102 = Ext.getCmp('tabPId4102_1').isVisible();
		    	//判断在哪个grid
				if(tabPId4102 == true){
			    	var data = Ext.getCmp('grid_02_4102').getSelectionModel().getSelection();
			    	data[0].set('articleName',res[0].articleName);
			    	data[0].set('barcode',res[0].barcode);
			    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);
			    	data[0].set('planBox',0);
			    	data[0].set('planQmin',0);
			    	data[0].set('planDis',0);
			    	data[0].set('unitCost',0);
			     	data[0].set('qminOperatePacking',res[0].qminOperatePacking);
			    	data[0].set('unitPacking',res[0].unitPacking);
		    	
				 }else if(tabPId4102 == false){
					 var data = Ext.getCmp('grid_03_4102').getSelectionModel().getSelection();

			    	 data[0].set('articleName',res[0].articleName);
			    	 data[0].set('barcode',res[0].barcode);
			    	 data[0].set('ownerArticleNo',res[0].ownerArticleNo);
		    		data[0].set('planBox',0);
			    	data[0].set('planQmin',0);
			    	data[0].set('planDis',0);
			    	data[0].set('qminOperatePacking',res[0].qminOperatePacking);
			    	data[0].set('unitPacking',res[0].unitPacking);
				 }
				
				var receiveType=Ext.getCmp('receiveType4102').getValue();
				Ext.Ajax.request({
					method:'post',
					url:'bdef_DefarticleAction_queryPackingQtyAndUnitAndSpec',
					params:{
						strArticleNo:combo.getValue()
				    },
				    success:function(response){
			    		var res = Ext.decode(response.responseText);
			    		if(tabPId4102 == true){
			    			var flagCount=0;
					    	var gridcount=Ext.getCmp('grid_02_4102').getStore().getCount();
					    	for(var i=0;i<gridcount;i++){
					    		var exp=Ext.getCmp('grid_02_4102').getStore().getAt(i);    		
					    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==res[0].packingQty){
					    			flagCount=flagCount+1;
					    		}
					    	}
					    	if(flagCount==0){
					    		data[0].set('packingQty',res[0].packingQty);
						    	data[0].set('packingUnit',res[0].packingUnit);
						    	data[0].set('packingSpec',res[0].spec);
					    	}else{
					    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
					    		data[0].set('packingQty',null);
					    	}  	
			    		}else if(tabPId4102 == false){
					    		data[0].set('packingQty',res[0].packingQty);
						    	data[0].set('packingUnit',res[0].packingUnit);
						    	data[0].set('packingSpec',res[0].spec);
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
					    		    	if(tabPId4102 == true){
					    		    		var flagCount=0;
									    	var gridcount=Ext.getCmp('grid_02_4102').getStore().getCount();
									    	for(var i=0;i<gridcount;i++){
									    		var exp=Ext.getCmp('grid_02_4102').getStore().getAt(i);    		
									    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')=='1'){
									    			flagCount=flagCount+1;
									    		}
									    	}
									    	if(flagCount==0){
									    		data[0].set('packingQty','1');
							    		    	data[0].set('packingUnit',res[0].packingUnit);
							    		    	data[0].set('packingSpec',res[0].spec);
							    		    	data[0].set('planBox',0);
										    	data[0].set('planQmin',0);
										    	data[0].set('planDis',0);
									    	}else{
									    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
									    		data[0].set('packingQty',null);
									    	}  	
					    		    	}else if(tabPId4102 == false){
					    		    		
								    		   data[0].set('packingQty','1');
							    		    	data[0].set('packingUnit',res[0].packingUnit);
							    		    	data[0].set('packingSpec',res[0].spec);
							    		    	data[0].set('planBox',0);
										    	data[0].set('planQmin',0);
										    	data[0].set('planDis',0);
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
								    	if(tabPId4102 == true){
								    		var flagCount=0;
									    	var gridcount=Ext.getCmp('grid_02_4102').getStore().getCount();
									    	for(var i=0;i<gridcount;i++){
									    		var exp=Ext.getCmp('grid_02_4102').getStore().getAt(i);    		
									    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==data[0].get('packingQty')){
									    			flagCount=flagCount+1;
									    		}
									    	}
									    	if(flagCount==0){
									    		var res = Ext.decode(response.responseText);
										    	data[0].set('packingUnit',res[0].packingUnit);
										    	data[0].set('packingSpec',res[0].spec);
									    	}else{
									    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
									    		data[0].set('packingQty',null);
									    	}
								    	}else if(tabPId4102 == false){
									    		var res = Ext.decode(response.responseText);
										    	data[0].set('packingUnit',res[0].packingUnit);
										    	data[0].set('packingSpec',res[0].spec);
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
		    	var data = Ext.getCmp('grid_02_4102').getSelectionModel().getSelection();
		    	var data = Ext.getCmp('grid_03_4102').getSelectionModel().getSelection();

		    	data[0].set('custName',res[0]);
		    }
		});
	},
	
	//差异确认 huangb 20160721
	DiffConfirm:function(){
		if(Ext.getCmp("rsvVarod2_4102").getValue() != ""){
			Ext.Msg.confirm($i18n.prompt, "确定差异确认?", function(button,text) {
				if(button=='yes'){
					Ext.Ajax.request({
						method:'POST',
						url:'idata_ImPortAction_tscDiffConfirm',
						params :{ 
							strOwnerNo : Ext.getCmp("owner_no4102").getValue(),
							importNo : Ext.getCmp("import_no4102").getValue(),
							newPoNo : Ext.getCmp("rsvVarod2_4102").getValue(),
							userId : Ext.get('workerNo').getValue(),
						},
						success : function(response) {
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								editImport4102(rowindex4102);
								Ext.getCmp('grid_01_4102').getStore().load();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							} 
						}
					});
				}
			});
		}else{
			Ext.example.msg($i18n.prompt,"请输入新来源单号");
		}
	}
});

//新增前加载
function addImport4102(){
	debugger;
	Ext.getCmp('form_01_4102').getForm().reset();
	Ext.getCmp('import_no4102').setValue('保存时自动生成');
	Ext.getCmp('orderDate4102').setValue(new Date());
	Ext.getCmp('requestDate4102').setValue(new Date());
	//Ext.getCmp('receiveType4102').setValue('IO');
	Ext.getCmp('orgNo4102').setValue('001');
	Ext.getCmp('takeType4102').setValue('0');
	Ext.getCmp('grid_02_4102').getStore().removeAll();
	Ext.getCmp('grid_03_4102').getStore().removeAll();

	if(Ext.getCmp('owner_no4102').getStore().data.length>0)
	{
		Ext.getCmp('owner_no4102').setValue(Ext.getCmp('owner_no4102').getStore().getAt(0).data.value);		
	}
	if(Ext.getCmp('receiveType4102').getStore().data.length>0)
	{
		Ext.getCmp('receiveType4102').setValue(Ext.getCmp('receiveType4102').getStore().getAt(0).data.value);		
	}
	Ext.getCmp('endDate4102').setValue(7);
	Ext.get('rgstName4102').dom.innerHTML=Ext.get('workerNo').getValue();
	Ext.get('rgstDate4102').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
	Ext.get('updtName4102').dom.innerHTML='';
	Ext.get('updtDate4102').dom.innerHTML='';
	commonSetMsterReadOnlyByArray(
	new Array('po_no14102','supplierNo4102',
	'owner_no4102','receiveType4102','endDate4102','importRemark4102',
	'orderDate4102','requestDate4102','orgNo4102','takeType4102','rsvVarod1_4102'),false);
	commonSetMsterReadOnlyByArray(
	new Array('import_no4102'),true);
	
	commonEditButton('menu4102','add');
	Ext.getCmp('toolbar4102').items.items[0].enable(true);
	Ext.getCmp('toolbar4102_2').items.items[0].enable(true);
	Ext.getCmp('toolbar4102').items.items[1].enable(true);
	Ext.getCmp('toolbar4102_2').items.items[1].enable(true);
	
	getClassType4501(Ext.getCmp('owner_no4102').getValue(),Ext.getCmp('receiveType4102').getValue());
	//存储
	if(g_ClassType4501=='0'){
		Ext.getCmp('tabPId4102_2').setDisabled(true);
	//直通
	}else if(g_ClassType4501=='1'){
		Ext.getCmp('tabPId4102_1').setDisabled(false);
		Ext.getCmp('tabPId4102_2').setDisabled(false);
	}
	
	
	saveType4102=0;
	Ext.getCmp('po_no14102').focus();
	isCanEdit4102=true;
};

//填充数据
function editImport4102(rowindex4102){
	if(rowindex4102==0)
	{
		Ext.getCmp('menu4102').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu4102').items.items[0].enable(true);
	}
	if(rowindex4102==Ext.getCmp('grid_01_4102').getStore().getCount()-1)
	{		
		Ext.getCmp('menu4102').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu4102').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_4102').getStore().getAt(rowindex4102-(Ext.getCmp('grid_01_4102').getStore().currentPage-1)*appConfig.getPageSize());
	if(record.data.createFlag==0){
		Ext.getCmp('menu4102').items.items[6].enable(true);
		if(record.data.status==10){
			Ext.getCmp('menu4102').items.items[6].enable(true);
		}else{
			Ext.getCmp('menu4102').items.items[6].disable(true);
		}
	}else if(record.data.createFlag==1){
		Ext.getCmp('menu4102').items.items[6].disable(true);
	}
    Ext.getCmp('owner_no4102').setValue(record.data.ownerNo);
    
    Ext.getCmp('supplierNo4102').getStore().add({
    	value:record.data.supplierNo,
    	dropValue:'['+record.data.supplierNo+']'+record.data.supplierName,
    	text:record.data.supplierName
    });
    Ext.getCmp('supplierNo4102').setValue(record.data.supplierNo);
    
    Ext.getCmp('import_no4102').setValue(record.data.importNo);
	//Ext.getCmp('po_no14102').setValue(record.data.poNo);
	Ext.getCmp('receiveType4102').setValue(String(record.data.poType));
	Ext.getCmp('orderDate4102').setValue(record.data.orderDate);
	Ext.getCmp('requestDate4102').setValue(record.data.requestDate);
	Ext.getCmp('endDate4102').setValue(record.data.endDate);
	Ext.getCmp('importRemark4102').setValue(record.data.importRemark);
	Ext.getCmp('orgNo4102').setValue(record.data.orgNo);
    Ext.getCmp('takeType4102').setValue(record.data.takeType);
    Ext.getCmp('rsvVarod1_4102').setValue(record.data.rsvVarod1);
	Ext.get('rgstName4102').dom.innerHTML=record.data.rgstName;
	Ext.get('rgstDate4102').dom.innerHTML=record.data.rgstDate;
	Ext.get('updtName4102').dom.innerHTML=record.data.updtName;
	Ext.get('updtDate4102').dom.innerHTML=record.data.updtDate;
	
	g_ClassType4501=record.data.classType;
	//存储
	if(g_ClassType4501=='0'){
		Ext.getCmp('tabPId4102_2').setDisabled(true);
	//直通
	}else if(g_ClassType4501=='1'){
		Ext.getCmp('tabPId4102_1').setDisabled(false);
		Ext.getCmp('tabPId4102_2').setDisabled(false);
	}
	var sql='';
	var importNo=record.data.importNo;
	var sql={
		importNo:importNo
		//receiveType:Ext.getCmp('receiveType4102').getValue()
	};
	var wheresql={
		wheresql:sql,
		articleNo:''
	};
	if(g_ClassType4501=='0'){
	Ext.apply(Ext.getCmp('grid_02_4102').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_4102').getStore().removeAll();
	Ext.getCmp('grid_02_4102').getStore().load();
	}else if(g_ClassType4501=='1'){
		Ext.apply(Ext.getCmp('grid_02_4102').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_02_4102').getStore().removeAll();
		Ext.getCmp('grid_02_4102').getStore().load();
		
		Ext.apply(Ext.getCmp('grid_03_4102').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_03_4102').getStore().removeAll();
		Ext.getCmp('grid_03_4102').getStore().load();
	}

	isCanEdit4102=false;
};

//打印后更改状态
function tips4102(){
	var record=Ext.getCmp('grid_01_4102').getSelectionModel().getSelection();
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
		    	Ext.getCmp('grid_01_4102').getStore().load();
		    }
		});
	}
};
function save4102(gridcount,gridcount2,imPortM,imPortD1,imPortD2,imPortD3,saveType4102,classType,importNo){
	if(importNo == "保存时自动生成"||!Ext.isEmpty(importNo)){
		var params={
			jsonMaster:imPortM,
			jsonDetail1:imPortD1,
			jsonDetail2:imPortD2,
			jsonDetail3:imPortD3,
			saveType:saveType4102
		};
		Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_saveImPortTth',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					
					Ext.example.msg('提示',data.msg);
					commonSetMsterReadOnlyByArray(
							new Array('po_no14102','supplierNo4102',
							'owner_no4102','receiveType4102','endDate4102','importRemark4102',
							'orderDate4102','requestDate4102','orgNo4102','takeType4102','rsvVarod1_4102'),true);
					Ext.getCmp('import_no4102').setValue(data.obj);
					if(classType == "0"){
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_4102').getStore().getAt(i);
						record.set('importNo',data.obj);
					}
					}else if(classType == "1"){
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_4102').getStore().getAt(i);
						record.set('importNo',data.obj);
					}
					for(var i=0;i<gridcount;i++ ){
						if(gridcount2>0){
							var record  = Ext.getCmp('grid_03_4102').getStore().getAt(i);
							record.set('importNo',data.obj);
						}
					}
					}
					if(typeof(Ext.getCmp('grid_01_4102'))!=='undefined'){
						Ext.getCmp('grid_01_4102').store.reload();
					}
					commonEditButton('menu4102','save');
					isCanEdit4102=false;
					flag = '1';
					Ext.getCmp('toolbar4102').items.items[0].disable(true);
					Ext.getCmp('toolbar4102').items.items[1].disable(true);
										
				}else{
					Ext.Msg.alert('提示',data.msg);
					disableButtonFunc(0,'#menu4102 [name=userSaveButton]','保存');
				}
			}
		});
	//查询新增分播明细保存
	}else{
		var params={	
				importNo:importNo,
				jsonDetail3:imPortD3
			};
		Ext.Ajax.request({
			method:'post',
			url:'idata_ImPortAction_saveImPortAllotTth',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('import_no4102').setValue(data.obj);
					if(classType == "0"){
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_4102').getStore().getAt(i);
						record.set('importNo',data.obj);
					}
					}else if(classType == "1"){
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_4102').getStore().getAt(i);
						record.set('importNo',data.obj);
					}
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_03_4102').getStore().getAt(i);
						record.set('importNo',data.obj);
					}
					}
					if(typeof(Ext.getCmp('grid_01_4102'))!=='undefined'){
						Ext.getCmp('grid_01_4102').store.reload();
					}
					commonEditButton('menu4102','save');
					isCanEdit4102=false;
					flag = '1';
					Ext.getCmp('toolbar4102').items.items[0].disable(true);
					Ext.getCmp('toolbar4102').items.items[1].disable(true);
				
				}else{
					Ext.Msg.alert('提示',data.msg);
					//flag = '1';
					disableButtonFunc(0,'#menu4102 [name=userSaveButton]','保存');
				}
			}
		});
		
	}
};

/**
 * 根据货主获取相应的系统参数
 * @param strOwnerNo
 */
function getSystemPara4501(strOwnerNo)
{
	g_ICAcrossAreaBox4501
		=commonGetSystemParams(strOwnerNo,'IC_AcrossAreaBox','I','IC')[0].sdefine;//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
	//g_ICFreezeRate4501
	//	=commonGetSystemParams(strOwnerNo,'IC_FreezeRate','I','IC')[0].sdefine;//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	//g_ICAlarmRate4501
	//	=commonGetSystemParams(strOwnerNo,'IC_AlarmRate','I','IC')[0].sdefine;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICCheckPickCell4501
		=commonGetSystemParams(strOwnerNo,'IC_CheckPickCell','I','IC')[0].sdefine;//存储验收时是否需要校验拣货位;0----不校验；1----校验
	g_ICCheckQpalette4501
		=commonGetSystemParams(strOwnerNo,'IC_CheckQpalette','I','IC')[0].sdefine;//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
};
//取进货类型
function getClassType4501(strOwnerNo,strImportType)
{
	g_ClassType4501
		=commonGetIdataType(strOwnerNo,strImportType,'class_type')[0].columnValue;//进货类型: 1直通;0：存储]
};
