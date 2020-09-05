/**
 * 模块名称：分拨入库
 * 模块编码：C101
 * 创建：hcx
 */

var rowindexC101=0;
var isCanEditC101=false;
var strOANO = "";
var saveTypeC101="";
Ext.define('cms.controller.acdata.acdata_INStockController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.acdata.acdata_INStockUI',
	          'cms.view.acdata.window.acdata_CheckSourceNoWindow'
	         ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'acdata_INStockUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'acdata_INStockUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'acdata_INStockUI button[name=userNextButton]':{
				click:this.userNext
			},//TAB页切换
			'acdata_INStockUI tabpanel[id=tabPIdC101]':{
				tabchange:this.tabPIdtabchange
			},//单据列表 双击
			'acdata_INStockUI grid[id=grid_01_C101]':{
				itemdblclick:this.grid_01_C101Click			
			},//新单
			'acdata_INStockUI button[name=userAddButton]':{
				click:this.addClick
			},//撤消
			'acdata_INStockUI button[name=userUndoButton]':{
				click:this.undoClick
			},//保存
			'acdata_INStockUI button[name=userSaveButton]':{
				click:this.save
			},//修改
			'acdata_INStockUI button[name=userEditButton]':{
				click:this.edit
			},//选择货主单号
			'acdata_INStockUI remoteCombo[id=sourceNoC101]':{
				select:this.sourceNoC101Select
			},//货主单号选择
			'acdata_INStockUI button[id=btnSearchSourceNoC101]':{
				click:this.btnSearchSourceNoC101Click
			},//单号选择--》确认
			'acdata_CheckSourceNoWindow button[name=butSureForManWC101]':{
				click:this.butSureForManWC101
			},//审核
			'acdata_INStockUI button[name=userSendButton]':{
				click:this.userSendButton
			}
			
		});
	},
	
	initializtion:function(){
		isCanEditC101=false;
	},
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='C101';
		queryGrid='grid_01_C101';	
	},
	
	userPrev:function(){
		rowindexC101=rowindexC101-1;
		editExpC101(rowindexC101);
	},
	
	userNext:function(){
		rowindexC101=rowindexC101+1;
		editExpC101(rowindexC101);
	},
	
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		Ext.getCmp('cmbWarehouseC101').getStore().load();
		if(newCard.itemId=='tabPIdC101i'){
			var data = Ext.getCmp('grid_01_C101').getSelectionModel().getSelection();
			if(data.length!=0){
				editExpC101(data[0].index);
				rowindexC101=data[0].index;				
				isCanEditC101=false;
				commonEditButton('menuC101','dbclick');
				var record = Ext.getCmp('grid_01_C101').getSelectionModel().getSelection();
				var status =record[0].data.statusText;
				if(status == "完结"){
					disableButtonFunc(1,'#menuC101 [name=userSendButton]','审核');
					disableButtonFunc(1,'#menuC101 [name=userEditButton]','修改');
				}
			}
		}
	},
	grid_01_C101Click:function(th, record,  item,  index, e, eOpts ){
		Ext.getCmp('tabPIdC101').items.items[1].setVisible(true);
		isCanEditC101=false;
		commonEditButton('menuC101','dbclick');
		var record = Ext.getCmp('grid_01_C101').getSelectionModel().getSelection();
		var status =record[0].data.statusText;	
		if(status == "完结"){
			disableButtonFunc(1,'#menuC101 [name=userSendButton]','审核');
			disableButtonFunc(1,'#menuC101 [name=userEditButton]','修改');
		}
	},
	addClick:function()
	{
		Ext.getCmp('form_01_C101').getForm().reset();
		Ext.getCmp('grid_02_C101').getStore().removeAll();
		Ext.getCmp('inStockNoC101').setValue('保存时自动生成');
		commonSetMsterReadOnlyByArray(
				new Array('sourceNoC101'),false);
		if(Ext.getCmp('cmbWarehouseC101').getStore().data.length>0)
		{
			Ext.getCmp('cmbWarehouseC101').setValue(Ext.getCmp('cmbWarehouseC101').getStore().getAt(0).data.value);		
		}
		
		commonEditButton('menuC101','add');
		IsCanEditC101=true;
		
	},
	undoClick:function()
	{
		Ext.getCmp('form_01_C101').getForm().reset();
		Ext.getCmp('grid_02_C101').getStore().removeAll();
		commonEditButton('menuC101','undo');
		IsCanEditC101=false;
	},
	save:function(){
		if(!commonCheckIsInputAll('form_01_C101'))
		{
			return
		}
		var gridcount=Ext.getCmp('grid_02_C101').getStore().getCount();
		if(gridcount<0){		
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		var master={
			id:{
				warehouseNo:Ext.get('warehouseNo').getValue(),
				instockNo:Ext.getCmp('inStockNoC101').getValue()
			},
				orderNo:Ext.getCmp('cmbCheckOrderNoC101').getValue(),
				sourceNo:Ext.getCmp('sourceNoC101').getValue(),
				ownerAlias:Ext.getCmp('cmbCheckOwnerAliasC101').getValue(),
				custAlias:Ext.getCmp('cmbCheckCustAliasC101').getValue(),
				status:10,
				remark:Ext.getCmp('remarkC101').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				updtName:Ext.get('workerNo').getValue(),
				updtDate:new Date()
		};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('grid_02_C101').getStore().getAt(i);
			var inQty=record.get('inQty');
			var inWt=record.get('inWt');
			var inVl=record.get('inVl');
			var istockQty=record.get('istockQty');
			var istockWt=record.get('istockWt');
			var istockVl=record.get('istockVl');
			if(istockQty>inQty || istockWt>inWt || istockVl>inVl){
				Ext.example.msg('提示',"入库数不能大于订单数");
				return;
			}else{
			var d={
				id:{
					warehouseNo:Ext.get('warehouseNo').getValue(),
					instockNo:Ext.getCmp('inStockNoC101').getValue(),
					articleName:record.get('articleName')	
				},
					barcodeNo:record.get('barcodeNo'),
					inQty:inQty,
					inWt:inWt,
					inVl:inVl,
					istockQty:istockQty,
					istockWt:istockWt,
					istockVl:istockVl,
					remark:Ext.getCmp('remarkC101').getValue(),
			};
			detail.push(d);
			}
		};
			
		disableButtonFunc(1,'#menuC101 [name=userSaveButton]','保存');
		var M=Ext.encode(master);
		var D=Ext.encode(detail);
		
		var params={
			jsonMaster:M,
			jsonDetail:D
		};
		Ext.Ajax.request({
			method:'post',
			url:'acdata_InStockAction_save',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					if(typeof(Ext.getCmp('grid_01_C101'))!=='undefined'){
						Ext.getCmp('grid_01_C101').store.reload();
					}
					Ext.getCmp('inStockNoC101').setValue(data.obj);					
					commonEditButton('menuC101','send');
					isCanEditC101=false;
					
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	},
	edit:function(){
		var instockNo = Ext.getCmp('inStockNoC101').getValue();
		var gridcount=Ext.getCmp('grid_02_C101').getStore().getCount();
		var articleName=[];
		var barcodeNo=[];
		var istockQty=[];
		var istockWt=[];
		var istockVl=[];
		for(var i=0;i<gridcount;i++){
		    var record = Ext.getCmp('grid_02_C101').getStore().getAt(i);
		    var inQty=record.get('inQty');
			var inWt=record.get('inWt');
			var inVl=record.get('inVl');
			var tistockQty=record.get('istockQty');
			var tistockWt=record.get('istockWt');
			var tistockVl=record.get('istockVl');
			if(tistockQty>inQty || tistockWt>inWt || tistockVl>inVl){
				Ext.example.msg('提示',"入库数不能大于订单数");
				return;
			}else{
		    barcodeNo.push(record.get('barcodeNo'));
		    articleName.push(record.get('articleName'));
	    	istockQty.push(tistockQty);
			istockWt.push(tistockWt);
			istockVl.push(tistockVl);
			}
		}
	    	var params={
	    			strInstockNo:instockNo,
	    			strArticleName:articleName,
	    			strBarcodeNo:barcodeNo,
	    			strIstockQty:istockQty,
	    			strIstockWt:istockWt,
	    			strIstockVl:istockVl
				};
	    	Ext.Ajax.request({
				method:'post',
				url:'acdata_InStockAction_edit.action',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg('提示',"修改成功");
						if(typeof(Ext.getCmp('grid_02_C101'))!=='undefined'){
							Ext.getCmp('grid_02_C101').store.reload();
						}
					}else{
						Ext.example.msg('提示',"修改失败");
					}
				}
			});
	},
	sourceNoC101Select:function(combo)
	{
		var params=
		{
				strSourceNo:combo.getValue()	
		};
		Ext.Ajax.request({
			method:'POST',
			url:'acdata_InStockAction_queryAcdataOrderMList.action',
			params:params,
			success:function(response)
			{
				var g = Ext.decode(response.responseText);
			            Ext.getCmp('cmbCheckOrderNoC101').setValue(g[0].orderNo);
			            Ext.getCmp('cmbCheckOwnerAliasC101').setValue(g[0].ownerAlias);
			            Ext.getCmp('cmbCheckCustAliasC101').setValue(g[0].custAlias);
					
			    var params1=
				{
			    	strOrderNo:g[0].orderNo
				};
				Ext.Ajax.request({
					method:'POST',
					url:'acdata_InStockAction_getAcdataOrderDList.action',
					params:params1,
					async:false,
					success:function(response)
					{
						var f = Ext.decode(response.responseText);
						if(f!='')
						{
							Ext.getCmp('grid_02_C101').getStore().removeAll();
							for(var i=0;i<f.length;i++){
							var articleName = f[i].articleName;
							var barcodeNo = f[i].barcodeNo;
							if(f[i].inQty == '0'){
								var inQty = f[i].orderQty;
							}else{
								var inQty = f[i].inQty;
							}
							if(f[i].inWt == '0'){
								var inWt = f[i].orderWt;
							}else{
								var inWt = f[i].inWt;
							}
							if(f[i].inVl == '0'){
								var inVl = f[i].orderVl;
							}else{
								var inVl = f[i].inVl;
							}
		                 var d = Ext.create('cms.model.acdata.acdata_Order_DModel', {
							});
		                 d.set('articleName',articleName);
		                 d.set('barcodeNo',barcodeNo);
		                 d.set('inQty',inQty);
		                 d.set('inWt',inWt);
		                 d.set('inVl',inVl);
		                 d.set('istockQty',"0");
		                 d.set('istockWt',"0");
		                 d.set('istockVl',"0");
		                 Ext.getCmp("grid_02_C101").getStore().add(d);
							}
						}
					}
				});
			}
		});
	},
	btnSearchSourceNoC101Click:function()
	{	
		var strSourceNo = Ext.getCmp('sourceNoC101').getValue();
		if(strSourceNo ==null){	
		Ext.create('cms.view.acdata.window.acdata_CheckSourceNoWindow',{
			title:$i18n.checkOrder
		}).show();	
		Ext.getCmp('grid_01_C101_d').getStore().removeAll();
		Ext.getCmp('grid_01_C101_d').getStore().load();
	}
	},
	butSureForManWC101:function(){
	 	var record = Ext.getCmp('grid_01_C101_d').getSelectionModel()
					.getSelection();
			if (record.length == 0) {Ext.example.msg("提示","请先选择您要操作的行!");
				return;
			} else {
					var r = Ext.create('cms.model.acdata.acdata_Order_MModel', {
					});
					r.set('sourceNo',record[0].data.sourceNo);
					r.set('orderNo',record[0].data.orderNo);
					r.set('ownerAlias',record[0].data.ownerAlias);
					r.set('custAlias',record[0].data.custAlias);
				    Ext.getCmp('sourceNoC101').setValue(r.data.sourceNo);
				    Ext.getCmp('cmbCheckOrderNoC101').setValue(r.data.orderNo);
				    Ext.getCmp('cmbCheckOwnerAliasC101').setValue(r.data.ownerAlias);
				    Ext.getCmp('cmbCheckCustAliasC101').setValue(r.data.custAlias);
				    var params=
					{
				    	strOrderNo:r.data.orderNo
					};
					Ext.Ajax.request({
						method:'POST',
						url:'acdata_InStockAction_getAcdataOrderDList.action',
						params:params,
						async:false,
						success:function(response)
						{
							var t = Ext.decode(response.responseText);
							if(t!='')
							{
								for(var i=0;i<t.length;i++){
								var articleName = t[i].articleName;
								var barcodeNo = t[i].barcodeNo;
								if(t[i].inQty == '0'){
									var inQty = t[i].orderQty;
								}else{
									var inQty = t[i].inQty;
								}
								if(t[i].inWt == '0'){
									var inWt = t[i].orderWt;
								}else{
									var inWt = t[i].inWt;
								}
								if(t[i].inVl == '0'){
									var inVl = t[i].orderVl;
								}else{
									var inVl = t[i].inVl;
								}
                                var d = Ext.create('cms.model.acdata.acdata_Order_DModel', {
            					});
                                d.set('articleName',articleName);
                                d.set('barcodeNo',barcodeNo);
                                d.set('inQty',inQty);
                                d.set('inWt',inWt);
                                d.set('inVl',inVl);
                                d.set('istockQty',"0");
                                d.set('istockWt',"0");
                                d.set('istockVl',"0");
                                Ext.getCmp("grid_02_C101").getStore().add(d);
								}
							}
						}
					});
				Ext.getCmp('acdata_CheckSourceNoWindow').close();
			}
	 	},
	userSendButton:function(){
			if(!commonCheckIsInputAll('form_01_C101'))
			{
				return
			}
			var gridcount=Ext.getCmp('grid_02_C101').getStore().getCount();
			if(gridcount<0){		
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
			var params={
				strWarehouseNo:Ext.get('warehouseNo').getValue(),
				strOrderNo:Ext.getCmp('cmbCheckOrderNoC101').getValue(),
				strSourceNo:Ext.getCmp('sourceNoC101').getValue(),
				strInstockNo:Ext.getCmp('inStockNoC101').getValue(),
			};
			Ext.Ajax.request({
				method:'post',
				url:'acdata_InStockAction_send',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg('提示',data.msg);
						if(typeof(Ext.getCmp('grid_01_C101'))!=='undefined'){
							Ext.getCmp('grid_01_C101').store.reload();
						}
						disableButtonFunc(1,'#menuC101 [name=userSendButton]','审核');
						
					}else{
						Ext.example.msg('提示',data.msg);
					}
				}
			});
		}
});

//填充数据
function editExpC101(rowindexC101){
	if(rowindexC101==0)
	{
		Ext.getCmp('menuC101').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menuC101').items.items[0].enable(true);
	}
	if(rowindexC101==Ext.getCmp('grid_01_C101').getStore().getCount()-1)
	{		
		Ext.getCmp('menuC101').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menuC101').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_C101').getStore().getAt(rowindexC101-(Ext.getCmp('grid_01_C101').getStore().currentPage-1)*appConfig.getPageSize());
	var status = record.data.statusText;
	if(status == "完结"){
		disableButtonFunc(1,'#menuC101 [name=userSendButton]','审核');
		disableButtonFunc(1,'#menuC101 [name=userEditButton]','修改');
	}else if(status == "接单"){
		disableButtonFunc(0,'#menuC101 [name=userSendButton]','审核');
	}
	Ext.getCmp('inStockNoC101').setValue(record.data.instockNo);
    Ext.getCmp('cmbWarehouseC101').setValue(record.data.warehouseNo);
    Ext.getCmp('sourceNoC101').setValue(record.data.sourceNo);
    Ext.getCmp('cmbCheckOrderNoC101').setValue(record.data.orderNo);
    Ext.getCmp('cmbCheckOwnerAliasC101').setValue(record.data.ownerAlias);
    Ext.getCmp('cmbCheckCustAliasC101').setValue(record.data.custAlias);
    Ext.getCmp('remarkC101').setValue(record.data.remark);
    	commonSetMsterReadOnlyByArray(
				new Array('inStockNoC101','cmbWarehouseC101',
				'sourceNoC101','cmbCheckOrderNoC101','cmbCheckOwnerAliasC101',
				'cmbCheckCustAliasC101','remarkC101')
				,true);
	var wheresql={
		wheresql:record.data.instockNo
	};
	Ext.apply(Ext.getCmp('grid_02_C101').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_C101').getStore().removeAll();
	Ext.getCmp('grid_02_C101').getStore().load();
	
		
};

