/**
 * 模块名称：分拨出库
 * 模块编码：C201
 * 创建：HKL
 */

var rowindexC201=0;
var isCanEditC201=false;

var strOANO = "";
var saveTypeC201="";
Ext.define('cms.controller.acdata.acdata_OutStockController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.acdata.acdata_OutStockUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'acdata_OutStockUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录userPrevButton
			'acdata_OutStockUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'acdata_OutStockUI button[name=userNextButton]':{
				click:this.userNext
			},///Grid双击
			'acdata_OutStockUI grid[id=grid_01_C201]':{
				itemdblclick:this.grid_01_C201Click
			},//TAB页切换
			'acdata_OutStockUI tabpanel[id=tabPIdC201]':{
				tabchange:this.tabPidchange
			},//新单
			'acdata_OutStockUI button[name=userAddButton]':{
				click:this.addClick
			},//撤销
			'acdata_OutStockUI button[name=userUndoButton]':{
				click:this.undoClick
			},//保存
			'acdata_OutStockUI button[name=userSaveButton]':{
				click:this.saveClick
			},//选择单号按扭
			'acdata_OutStockUI button[id=btnSelectNoC201]':{
				click:this.btnSelectNoC201Click
			},//选择单号确认
	    	'acdata_OutStockWindow button[name=butSelectForC201]':{
				click:this.butSelectForC201
			},//货主单选择
			'acdata_OutStockUI remoteCombo[id=source_noC201]':
			{
				focus:this.cmbSourceNoC201Focus,
				select:this.cmbSourceNoC201Select
			},//点击审核
			'acdata_OutStockUI button[name=userSendButton]':{
				click:this.butTruesC201
			},
			//出库数量
			'acdata_OutStockUI grid[id=grid_02_C201]':
			{
				beforeedit:this.grid_02_C201Beforeedit,
				edit:this.grid_02_C201edit
			},
			
		});
	},
	initializtion:function(){
		isCanEditC201=false;
	},
	grid_02_C201Beforeedit:function(){
		if(!isCanEditC201)
	    {
	        editor.cancel = true;
	        return  false;  
	    }
	},
	grid_02_C201edit:function(editor,e,eOpts){
		inQty = e.record.data.inQty;
		alreadyQty = e.record.data.alreadyQty;
		ostockQty = e.record.data.ostockQty;
		if(inQty-alreadyQty<ostockQty)
		{
			Ext.example.msg($i18n.prompt,"该货物出库数量超量，请重新输入");
			return false;
		}
		return true;
	},
	//查找
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='C201';
		queryGrid='grid_01_C201';	
	},
	//上单下单
	userPrev:function(){
		rowindexC201=rowindexC201-1;
		editExpC201(rowindexC201);
	},
	
	userNext:function(){
		rowindexC201=rowindexC201+1;
		editExpC201(rowindexC201);
	},
	//切换页面
	grid_01_C201Click:function(th, record,  item,  index, e, eOpts){
		Ext.getCmp('tabPIdC201').items.items[1].setVisible(true);
		isCanEditC201=false;
		//debugger
		commonEditButton('menuC201','dbclick');
		var record = Ext.getCmp('grid_01_C201').getSelectionModel().getSelection();
		var status =record[0].data.statusText;
		
		if(status == "完结"){
			disableButtonFunc(1,'#menuC201 [name=userSendButton]','审核');
		}
		//Ext.getCmp('toolbarC201').items.items[0].disable(true);
		//Ext.getCmp('toolbarC201').items.items[1].disable(true);
	},
	//明细页面加载
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		Ext.getCmp('house_noC201').getStore().load();
		if(newCard.itemId=='tabPIdC201i'){
			var data = Ext.getCmp('grid_01_C201').getSelectionModel().getSelection();
			if(data.length!=0){
				
				editExpC201(data[0].index);
				rowindexC201=data[0].index;
			
				isCanEditC201=false;
				commonEditButton('menuC201','dbclick');
				var record = Ext.getCmp('grid_01_C201').getSelectionModel().getSelection();
				var status =record[0].data.statusText;
				
				if(status == "完结"){
					disableButtonFunc(1,'#menuC201 [name=userSendButton]','审核');
				}
				//Ext.getCmp('toolbarC201').items.items[0].disable(true);
				//Ext.getCmp('toolbarC201').items.items[1].disable(true);
			}
		}
	},
	//新增前加载
	addClick:function()
	{
		Ext.getCmp('form_01_C201').getForm().reset();
		Ext.getCmp('grid_02_C201').getStore().removeAll();
		
		commonSetMsterReadOnlyByArray(
		new Array('order_noC201','source_noC201','ownerC201',
				'custC201','remarkC201'),false);
		
	    Ext.getCmp('out_noC201').setValue('保存自动生成');	
	    if(Ext.getCmp('house_noC201').getStore().data.length>0)
		{
			Ext.getCmp('house_noC201').setValue(Ext.getCmp('house_noC201').getStore().getAt(0).data.value);		
		}
		//disableButtonFunc(1,'#menuC201 [name=userUndoButton]','撤销');
	    commonEditButton('menuC201','add');
	    isCanEditC201=true;
	
	},
	//撤销
	undoClick:function()
	{
		Ext.getCmp('form_01_C201').getForm().reset();
		Ext.getCmp('grid_02_C201').getStore().removeAll();
		Ext.getCmp('out_noC201').setValue('保存自动生成');	
		commonSetMsterReadOnlyByArray(
			new Array('out_noC201','order_noC201',
					'house_noC201','source_noC201','ownerC201',
					'custC201','remarkC201'),
			true);
		commonEditButton('menuC201','undo');
		isCanEditC201=false;
	},
	//选择单号按钮
	btnSelectNoC201Click:function()
	{
		Ext.create('cms.view.acdata.window.acdata_OutStockWindow',{
			title:$i18n.titlebrowse
		}).show();
		/*var strWheresql = {
			strFlag : '1'//1代表建议单
		};*/
		Ext.apply(Ext.getCmp('grid_01_C201_m').getStore().proxy.extraParamss);
		Ext.getCmp('grid_01_C201_m').getStore().removeAll();
		Ext.getCmp('grid_01_C201_m').getStore().load();
	},
	//选择单号确认
	butSelectForC201:function(){
	 	var record = Ext.getCmp('grid_01_C201_m').getSelectionModel()
					.getSelection();
			if (record.length == 0) {Ext.example.msg("提示","请先选择您要操作的行!");
				return;
			} else {
				//var store = Ext.getCmp("menuC201").getStore();
				//var count=store.getCount();
					var r = Ext.create('cms.model.acdata.acdata_Order_MModel', {
					});
					r.set('orderNo',record[0].data.orderNo);
					r.set('sourceNo',record[0].data.sourceNo);
					r.set('ownerAlias',record[0].data.ownerAlias);
					r.set('custAlias',record[0].data.custAlias);
					Ext.getCmp('source_noC201').setValue(r.data.sourceNo);
					//alert(r.data.sourceNo);
					Ext.getCmp('order_noC201').setValue(r.data.orderNo);
					Ext.getCmp('ownerC201').setValue(r.data.ownerAlias);
					Ext.getCmp('custC201').setValue(r.data.custAlias);
					//store.add(r);
					//Ext.getCmp('grid_01_C201_m').editingPlugin.startEdit(0,6);
					//store.getAt(count+i).set('checkColumn',true);
				
				var params=
				{
				    sourceNo:record[0].data.sourceNo,
				    orderNo:record[0].data.orderNo,	
				};
				Ext.Ajax.request({
					method:'POST',
					url:'acdata_OutStockAction_getContent_List',
					params:params,
					async:false,
					success:function(response)
					{
						//alert(response.responseText);
						var data = Ext.decode(response.responseText);
				
						for ( var i = 0; i < data.rootList.length; i++) {
							var articleName = data.rootList[i].articleName;
							var barcodeNo = data.rootList[i].barcodeNo;
							var inQty = data.rootList[i].inQty;
							var inWt = data.rootList[i].inWt;
							var inVl = data.rootList[i].inVl;
							var alreadyQty = data.rootList[i].alreadyQty;
							var alreadyWt = data.rootList[i].alreadyWt;
							var alreadyVl = data.rootList[i].alreadyVl;
 
							var r = Ext.create('cms.model.acdata.acdat_StockcontentModel', {});
							r.set('articleName',articleName);
							r.set('barcodeNo',barcodeNo);
							r.set('inQty',inQty);
							r.set('inWt',inWt);
							r.set('inVl',inVl);
							r.set('alreadyQty',alreadyQty);
							r.set('alreadyWt',alreadyWt);
							r.set('alreadyVl',alreadyVl);
							r.set('ostockQty',0);
							r.set('ostockWt',0);
							r.set('ostockVl',0);

						    Ext.getCmp('grid_02_C201').getStore().add(r);
						}
					}
				});
			}
			Ext.getCmp('acdata_OutStockWindow').close();

			/*var wheresql={
					sourceNo:record[0].data.sourceNo,
					orderNo:record[0].data.orderNo,
				};*/
			/*Ext.apply(Ext.getCmp('grid_02_C201').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_C201').getStore().removeAll();
			Ext.getCmp('grid_02_C201').getStore().load();*/
			//alert(record[0].data.sourceNo);
		
	 	},
	 	//输入货主单号
	 	cmbSourceNoC201Focus:function()
		{
	 		var strSourceNo = {
	 				strSourceNo : Ext.getCmp('source_noC201').getValue()
	 		};
			Ext.apply(Ext.getCmp('source_noC201').getStore().proxy.extraParams,strSourceNo,strWheresql);
			Ext.getCmp('source_noC201').getStore().removeAll();
			Ext.getCmp('source_noC201').getStore().load();
		},
		//选择货主单号
		cmbSourceNoC201Select:function(combo)
		{
			
			var params=
			{
				sourceNo:combo.getValue()	
			};
			Ext.Ajax.request({
				method:'POST',
				url:'acdata_OutStockAction_getOutMBySourceNo',
				async:false,
				params:params,
				success:function(response)
				{
					var data = Ext.decode(response.responseText);
					
				    Ext.getCmp('order_noC201').setValue(data.rootList[0].orderNo);
				    Ext.getCmp('ownerC201').setValue(data.rootList[0].ownerAlias);
				    Ext.getCmp('custC201').setValue(data.rootList[0].custAlias);
				
				}
			});
				var orderNo = Ext.getCmp('order_noC201').getValue();
				var sourceNo = Ext.getCmp('source_noC201').getValue();
			
			var params=
			{
			    orderNo:orderNo,	
			    sourceNo:sourceNo,
			};
			//debugger;
			Ext.Ajax.request({
				method:'POST',
				url:'acdata_OutStockAction_getContent_List',
				params:params,
				async:false,
				success:function(response)
				{
					//alert(response.responseText);
					var data = Ext.decode(response.responseText);
			
					for ( var i = 0; i < data.rootList.length; i++) {
						var articleName = data.rootList[i].articleName;
						var barcodeNo = data.rootList[i].barcodeNo;
						var inQty = data.rootList[i].inQty;
						var inWt = data.rootList[i].inWt;
						var inVl = data.rootList[i].inVl;
						var alreadyQty = data.rootList[i].alreadyQty;
						var alreadyWt = data.rootList[i].alreadyWt;
						var alreadyVl = data.rootList[i].alreadyVl;

						var r = Ext.create('cms.model.acdata.acdat_StockcontentModel', {});
						r.set('articleName',articleName);
						r.set('barcodeNo',barcodeNo);
						r.set('inQty',inQty);
						r.set('inWt',inWt);
						r.set('inVl',inVl);
						r.set('alreadyQty',alreadyQty);
						r.set('alreadyWt',alreadyWt);
						r.set('alreadyVl',alreadyVl);

					    Ext.getCmp('grid_02_C201').getStore().add(r);
					}
				}
			});
			
		},
		
	 	
	 	//保存
	 	saveClick:function(){
	 		//debugger
	 		//alert(123);
			if(!commonCheckIsInputAll('form_01_C201'))
			{
				return;
			}
			var gridcount=Ext.getCmp("grid_02_C201").getStore().getCount();
			if(gridcount<0)
			{
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
			Ext.Msg.confirm("提示", "确定保存？",function(button, text) 
			{
				if (button == 'yes') 
				{
					var msgShow = commonMegShow("正在保存,请稍等...");
					var outstockNo= Ext.getCmp('out_noC201').getValue();		
					var warehouseNo= Ext.getCmp('house_noC201').getValue();
					var orderNo= Ext.getCmp('order_noC201').getValue();
					var sourceNO=Ext.getCmp('source_noC201').getValue();
					var ownerAlias =Ext.getCmp('ownerC201').getValue();
					var custAlias = Ext.getCmp('custC201').getValue();
					var remark	=Ext.getCmp('remarkC201').getValue();	
					//alert(orderNo);
					var master=
					{
						id:
						{
							warehouseNo:warehouseNo,
							outStockNO:outstockNo,
						},
						
						orderNo:orderNo,			
						sourceNO:sourceNO,				
						ownerAlias:ownerAlias,
						custAlias:custAlias,
						remark:remark,
						status:'10',
						rgstName:Ext.get('workerNo').getValue(),
						rgstDate:new Date(),
						updtName:Ext.get('workerNo').getValue(),
						updtDate:new Date(),
					};	
					//alert(master);
					var detail1 = [];
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_C201').getStore().getAt(i);
						var d={		
							id:
							{
								warehouseNo:warehouseNo,
								outStockNO:outstockNo,
								articleName:record.get('articleName'),
							},
				
							barcodeNo:record.get('barcodeNo'),
							inQty:record.get('inQty'),
							inWt:record.get('inWt'),
							inVl:record.get('inVl'),
							alreadyQty:record.get('alreadyQty'),
							alreadyWt:record.get('alreadyWt'),
							alreadyVl:record.get('alreadyVl'),
							ostockQty:record.get('ostockQty')==undefined?  '0':record.get('ostockQty'),
							ostockWt:record.get('ostockWt')==undefined?  '0':record.get('ostockWt'),
							ostockVl:record.get('ostockVl')==undefined?  '0':record.get('ostockVl'),
						    remark:record.get('remark')
							
						};
							detail1.push(d);
					};
					if(detail1.length!=0)
					{
						var OUTM = Ext.encode(master);
						var OUTD = Ext.encode(detail1);
						var params = 
						{
								OUTM:OUTM,
								OUTD:OUTD
						};
						Ext.Ajax.request({
							method:'POST',
							url:'acdata_OutStockAction_saveOut',
							params:params,
							success:function(response)
							{
								//debugger;
								msgShow.hide();
								var data = Ext.decode(response.responseText);
								if(data.isSucc)
								{
									Ext.example.msg('提示',data.msg);
									if(typeof(Ext.getCmp('grid_01_C201'))!=='undefined'){
										Ext.getCmp('grid_01_C201').store.reload();
									}
									Ext.getCmp('out_noC201').setValue(data.obj);

									commonEditButton('menuC201','send');
									isCanEditC201=false;
								}else
								{
									Ext.example.msg('提示',data.msg+data.obj);
								}				
							}
						});
					}else
					{
						msgShow.hide();
						Ext.example.msg('提示','保存失败');
					}
				}
			});
		},
		//点击审核
		butTruesC201:function(){
			var orderNo = Ext.getCmp('order_noC201').getValue();
			var sourceNo = Ext.getCmp('source_noC201').getValue();
			var strOutStockNO = Ext.getCmp('out_noC201').getValue();
			var params=
			{
			    sourceNo:sourceNo,
			    orderNo:orderNo,
			    strOutStockNO:strOutStockNO,
			};
			//debugger;
			Ext.Ajax.request({
				method:'POST',
				url:'acdata_OutStockAction_saveTrues',
				params:params,
				//同步加载async:false,
				success:function(response)
				{
					var data=Ext.decode(response.responseText);
					if(data.isSucc){	
						//debugger;
						Ext.example.msg($i18n.prompt,data.msg);
						if(typeof(Ext.getCmp('grid_01_C201'))!=='undefined'){
							Ext.getCmp('grid_01_C201').store.reload();
						}
						disableButtonFunc(1,'#menuC201 [name=userSendButton]','审核');

					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}

					}
				});
		}
});
/*//新增前加载
function addExpC201(){
	Ext.getCmp('form_01_C201').getForm().reset();
	Ext.getCmp('').setValue('保存时自动生成');
	Ext.getCmp('source_noC201').setValue('OE');
	Ext.getCmp('priorityC201').setValue('100');
	Ext.getCmp('fast_flagC201').setValue('0');
	Ext.getCmp('grid_02_C201').getStore().removeAll();
	if(Ext.getCmp('house_noC201').getStore().data.length>0)
	{
		Ext.getCmp('house_noC201').setValue(Ext.getCmp('house_noC201').getStore().getAt(0).data.value);		
	}
	commonSetMsterReadOnlyByArray(
			new Array('out_noC201','order_noC201','custC201',
			'house_noC201','contactor_nameC201','source_noC201','ownerC201',
			'custC201','cust_addressC201','priorityC201','fast_flagC201',
			'remarkC201','sanpl_noC201')
			,false);
	commonSetMsterReadOnlyByArray(
			new Array('out_noC201','priorityC201')
			,true);

	commonEditButton('menuC201','add');
	Ext.getCmp('toolbarC201').items.items[0].enable(true);
	Ext.getCmp('toolbarC201').items.items[1].enable(true);
	Ext.getCmp('order_noC201').focus();
	isCanEditC201=true;
};*/

//查看数据
function editExpC201(rowindexC201){
	if(rowindexC201==0)
	{
		Ext.getCmp('menuC201').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menuC201').items.items[0].enable(true);
	}
	if(rowindexC201==Ext.getCmp('grid_01_C201').getStore().getCount()-1)
	{		
		Ext.getCmp('menuC201').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menuC201').items.items[1].enable(true);
	}
	//debugger
	var record=Ext.getCmp('grid_01_C201').getStore().getAt(rowindexC201-(Ext.getCmp('grid_01_C201').getStore().currentPage-1)*appConfig.getPageSize());
	var status =record.data.statusText;
	if(status == "完结"){
		disableButtonFunc(1,'#menuC201 [name=userSendButton]','审核');
	}else if(status == "接单"){
		disableButtonFunc(0,'#menuC201 [name=userSendButton]','审核');

	}
	Ext.getCmp('out_noC201').setValue(record.data.outstockNo);
    Ext.getCmp('house_noC201').setValue(record.data.warehouseNo);
    Ext.getCmp('source_noC201').setValue(record.data.sourceNo);
    Ext.getCmp('order_noC201').setValue(record.data.orderNo);
    Ext.getCmp('ownerC201').setValue(record.data.ownerAlias);
    Ext.getCmp('custC201').setValue(record.data.custAlias);
    Ext.getCmp('remarkC201').setValue(record.data.remark);
    	commonSetMsterReadOnlyByArray(
				new Array('out_noC201','order_noC201',
				'house_noC201','source_noC201','ownerC201',
				'custC201','remarkC201')
				,true);
  
	var wheresql={
		wheresql:record.data.outstockNo
	};
	Ext.apply(Ext.getCmp('grid_02_C201').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_C201').getStore().removeAll();
	Ext.getCmp('grid_02_C201').getStore().load();

};
