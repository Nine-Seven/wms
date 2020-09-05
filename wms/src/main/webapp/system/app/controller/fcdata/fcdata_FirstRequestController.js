/**
 * 模块名称：初盘回单
 * 模块编码：8401
 * 创建：Jun
 */
var g_isCanEdit8401=false;
var g_strSubLabelNo='N';
var lotType8401;

var selectFlag='';  //7-18   
var serialnoFlag='';  //7-18

/*var g_FCSFirstQtyDisplay=1;//初盘是否采用盲盘；0:非盲盘；1：盲盘
var g_FCSCellCheckQtyDisplay=1;//储位检查是否采用盲盘；0：非盲盘；1:盲盘*/
Ext.define('cms.controller.fcdata.fcdata_FirstRequestController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.fcdata.fcdata_FirstRequestUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//盘点回单>>选择盘点单头
			'fcdata_FirstRequestUI grid[id=grid_01_8401]':
			{
				beforeselect:this.grid_01_8401Beforeselect,
				selectionchange:this.grid_01_8401Selectionchange
			},//盘点回单>>选择盘点明细
			'fcdata_FirstRequestUI grid[id=grid_02_8401]':
			{
				beforeedit:this.grid_02_8401Beforeedit
			},//修改
			'fcdata_FirstRequestUI button[name=edit]':
			{
				click:this.editClick
			},//撤销
			'fcdata_FirstRequestUI button[name=undo]':
			{
				click:this.undoClick
			},//保存
			'fcdata_FirstRequestUI button[name=save]':
			{
				click:this.saveClick
			},//查找
			'fcdata_FirstRequestUI button[name=query]':
			{
				click:this.queryClick
			},//刷新
			'fcdata_FirstRequestUI button[name=refresh]':
			{
				click:this.refreshClick
			},//新增品项
			'fcdata_FirstRequestUI button[id=btnNewitem8401]':
			{
				click:this.btnNewitem8401Click
			},//零回单
			'fcdata_FirstRequestUI button[id=btnZeroreceipt8401]':
			{
				click:this.btnZeroreceipt8401Click
			},//无差异保存
			'fcdata_FirstRequestUI button[id=btnNoDifference8401]':
			{
				click:this.btnNoDifference8401Click
			},//确认
			'fcdata_FirstRequestUI button[id=btnConfirm8401]':
			{
				click:this.btnConfirm8401Click
			},//盘点类型选择
			'fcdata_FirstRequestUI radiogroup[id=rdoCheckType8401]':
			{
				change:this.rdoCheckType8401Change
			},//填充储位
			'cdef_DefCellCombo[id=cmbCellNo8401]':
			{
				beforequery:this.cmbCellNo8401Beforequery,
				select:this.ownerCellNo8401
			},//商品选择
			'bdef_DefArticleCombo[id=cmbArticleNo8401]':{
				beforequery:this.cmbArticleNo8401forequery,
				select:this.articleNo8401Select
			},//包装选择
			'bdef_PackingQtyCombo[id=cmbPackingQty8401]':{
				select:this.cmbPackingQty8401Select
			},
			//商品下拉失去隹点
			'fcdata_FirstRequestWindow bdef_DefArticleCombo[id=cmbArticleNo8401]':
			{
				blur:this.checkUnique8401
			},//储位下拉失去隹点
			'fcdata_FirstRequestWindow cdef_DefCellCombo[id=cmbCellNo8401]':
			{
				blur:this.checkUnique8401
			},//商品下拉失去隹点
			'fcdata_FirstRequestWindow cmbArticleNo8401[id=bdef_DefArticleCombo]':
			{
				blur:this.checkUnique8401
			},//包装下拉失去隹点
			'fcdata_FirstRequestWindow bdef_PackingQtyCombo[id=cmbPackingQty8401]':
			{
				blur:this.checkUnique8401
			},//批号选择
			'fcdata_FirstRequestWindow wms_DefFieldValCombo[id=txtLotNo8401]':
			{
				blur:this.txtLotNo8401Blur
			},//生产日期选择
			'fcdata_FirstRequestWindow datefield[id=dateProduceDate8401]':
			{
				blur:this.dateProduceDate8401Change
			},//有效期选择
			'fcdata_FirstRequestWindow datefield[id=dateExpireDate8401]':
			{
				blur:this.dateExpireDate8401Change
			},//保存并新增
			'fcdata_FirstRequestWindow button[id=saveAndAdd8401]':
			{
				click:this.saveAndAdd8401Click
			},//选择是否填写标签号
			'fcdata_FirstRequestWindow wms_DefFieldValCombo[id=cmbWheeherWrite8401]':
			{
				select:this.cmbWheeherWrite8401Select
			},//校验主板号
			'fcdata_FirstRequestWindow textfield[id=txtLabelNo8401]':
			{
				blur:this.txtLabelNo8401Blur
			},//校验子板号
			'fcdata_FirstRequestWindow textfield[id=txtSubLabelNo8401]':
			{
				blur:this.txtSubLabelNo8401Blur
			},
			'fcdata_FirstRequestWindow field':
			{
				specialkey:this.boxkeydown
			}
			
			
			
		});
	},
	
	initializtion:function(){
		//显示变量0为不显示，1为显示
		var planBox8401=commonGetModuleField('8401','planBox')[0].flag;
		var planDis8401=commonGetModuleField('8401','planDis')[0].flag;
		var planQmin8401=commonGetModuleField('8401','planQmin')[0].flag;

		var packingUnit8401=commonGetModuleField('8401','packingUnit')[0].flag;
		var packingSpec8401=commonGetModuleField('8401','packingSpec')[0].flag;
		if(planBox8401==0){
			Ext.getCmp('planBox8401').setVisible(false);
		}
		if(planDis8401==0){
			Ext.getCmp('planDis8401').setVisible(false);
		}
		if(planQmin8401==0){
			Ext.getCmp('planQmin8401').setVisible(false);
		}
		if(packingUnit8401==0){
			Ext.getCmp('packingUnit8401').setVisible(false);
		}
		if(packingSpec8401==0){
			Ext.getCmp('packingSpec8401').setVisible(false);
		}
	},
	
	grid_01_8401Beforeselect:function()
	{
		if(g_isCanEdit8401)
	    {
	        return  false;  
	    }
	},
	
	grid_01_8401Selectionchange:function()
	{
		var data = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			var strWheresql={
				strWheresql:data[0].get('checkNo')
			};
			Ext.apply(Ext.getCmp('grid_02_8401').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_8401').getStore().removeAll();
			Ext.getCmp('grid_02_8401').getStore().load();
					
			Ext.get('rgstName8401').dom.innerHTML=data[0].get('rgstName');
			Ext.get('rgstDate8401').dom.innerHTML=data[0].get('rgstDate');
			Ext.get('updtName8401').dom.innerHTML=data[0].get('updtName');
			Ext.get('updtDate8401').dom.innerHTML=data[0].get('updtDate');
		}else
		{
			Ext.getCmp('grid_02_8401').getStore().removeAll();
			Ext.get('rgstName8401').dom.innerHTML="";
			Ext.get('rgstDate8401').dom.innerHTML="";
			Ext.get('updtName8401').dom.innerHTML="";
			Ext.get('updtDate8401').dom.innerHTML="";
		}
	},
	
	grid_02_8401Beforeedit:function(editor,e,eOpts)
	{
		if(!g_isCanEdit8401)
	    {
	        editor.cancel = true;
	        return  false;  
	    }
	},
	
	editClick:function()
	{
		var data = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
        if(data.length != 0)
        {
			commonMenu4Button('menu8401','edit');
			g_isCanEdit8401=true;
			Ext.getCmp('cmbWorkerNo8401').focus();
			Ext.getCmp('btnNewitem8401').setDisabled(true);
			Ext.getCmp('btnNoDifference8401').setDisabled(true);
			Ext.getCmp('btnZeroreceipt8401').setDisabled(true);
			Ext.getCmp('btnConfirm8401').setDisabled(true);
        }
	},
	
	undoClick:function()
	{
		commonMenu4Button('menu8401','undo');
		g_isCanEdit8401=false;
		Ext.getCmp('grid_01_8401').fireEvent('selectionchange');
		Ext.getCmp('btnNewitem8401').setDisabled(false);
		Ext.getCmp('btnNoDifference8401').setDisabled(false);
		Ext.getCmp('btnZeroreceipt8401').setDisabled(false);
		Ext.getCmp('btnConfirm8401').setDisabled(false);
	},
	
	saveClick:function()
	
	{
		if(!commonCheckIsInputAll('form_01_8401'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_02_8401").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_02_8401',0,gridcount-1))
			{
				return;
			}	
		}else
		{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		
		Ext.Msg.confirm("提示", "确定保存？",function(button, text) 
		{
			if (button == 'yes') {
				var msgShow = commonMegShow("正在保存,请稍等...");
				var objData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection()[0];
				var detail1=[];
				for(var i=0;i<gridcount;i++){
					var record=Ext.getCmp('grid_02_8401').getStore().getAt(i);
					var d={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:objData.data.warehouseNo,
							checkNo:objData.data.checkNo,
							rowId:'999'
						},
						ownerNo:record.get('ownerNo'),
						checkType:objData.data.checkType,
						checkWorker:Ext.getCmp('cmbWorkerNo8401').getValue(),
						cellNo:record.get('cellNo'),
						articleNo:record.get('articleNo'),
						packingQty:record.get('packingQty'),
						produceDate:record.get('produceDate'),
						expireDate:record.get('expireDate'),
						quality:record.get('quality'),
						lotNo:record.get('lotNo'),
						checkQty:record.get('packingQty')*record.get('planBox')
						+record.get('planQmin')*record.get('qminOperatePacking')
						+record.get('planDis'),
						labelNo:record.get('labelNo'),
						subLabelNo:record.get('subLabelNo'),
						stockType:record.get('stockType'),
						stockValue:record.get('stockValue'),
						addFlag:1,
						rsvBatch1:'N',
						rsvBatch2:'N',
						rsvBatch3:'N',
						rsvBatch4:'N',
						rsvBatch5:'N',
						rsvBatch6:'N',
						rsvBatch7:'N',
						rsvBatch8:'N'
					};
					detail1.push(d);
				};
				var strDetail=Ext.encode(detail1);
				var params={
					strDetail:strDetail
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_RequestAction_saveFcdata_CheckD',
					params:params,
					success:function(response)
					{
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc)
						{
							commonMenu4Button('menu8401','save');
							Ext.example.msg('提示',data.msg);
							Ext.getCmp('btnNewitem8401').setDisabled(false);
							Ext.getCmp('btnZeroreceipt8401').setDisabled(false);
							Ext.getCmp('btnNoDifference8401').setDisabled(false);
							Ext.getCmp('btnConfirm8401').setDisabled(false);
							Ext.getCmp('grid_01_8401').getStore().reload();
							g_isCanEdit8401=false;
						}else
						{
							Ext.example.msg('提示',data.msg);
						}
					},
					failure:function(response)
					{
						msgShow.hide();
						Ext.example.msg('提示',"提交不上,可能是网络问题");
					}
				});
			}
		});
	},
	
	queryClick:function()
	{
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=8401;
		queryGrid='grid_01_8401';	
	},
	
	refreshClick:function()
	{
		Ext.getCmp('grid_01_8401').getStore().reload();
	},
	
	btnNewitem8401Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8401'))
		{
			return;
		}
		Ext.create('cms.view.fcdata.window.fcdata_FirstRequestWindow',{
			title:$i18n.titleadd//新增
		}).show();
		Ext.getCmp('cmbCellNo8401').focus(false, 10);
		Ext.getCmp('cmbWheeherWrite8401').setValue('1');
		Ext.getCmp('cmbQuality8401').setValue('0');
	/*	var rec = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
		if(rec.length!=0){
			var detail1 = [];
			var d={
				columnId:'t1.owner_no',
				value:rec[0].get('ownerNo')
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('cmbArticleNo8401').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('cmbArticleNo8401').getStore().removeAll();
			Ext.getCmp('cmbArticleNo8401').getStore().load();
		}*/
	},
	
	btnZeroreceipt8401Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8401'))
		{
			return;
		}
    	var objData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
		if(objData.length!=0){
			Ext.Msg.confirm("提示", "确定零回单？",function(button, text){
				if (button == 'yes')
				{
					var msgShow = commonMegShow("正在操作,请稍等...");
					var params = {
						strWheresql:objData[0].get('checkNo')+","+objData[0].get('checkType')+","+Ext.getCmp('cmbWorkerNo8401').getValue()
					};
					Ext.Ajax.request({
						method:'POST',
						url:'fcdata_RequestAction_zeroReceipt',
						params:params,
						success:function(response){
							msgShow.hide();
							var objData = Ext.decode(response.responseText);
							if(objData.isSucc){
								Ext.example.msg('提示',objData.msg);
								Ext.getCmp('cmbWorkerNo8401').setValue("");
								Ext.getCmp('grid_01_8401').getStore().reload();
							}else{
								Ext.example.msg('提示',objData.msg+objData.obj);
							}				
						}
					});	
				}
			});
		}
	},
	
	btnNoDifference8401Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8401'))
		{
			return;
		}
    	var objData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
		if(objData.length!=0){
			Ext.Msg.confirm("提示", "确定无差异保存？",function(button, text){
				if (button == 'yes')
				{
					var msgShow = commonMegShow("正在操作,请稍等...");
					var params = {
						strWheresql:objData[0].get('checkNo')+","+objData[0].get('checkType')+","+Ext.getCmp('cmbWorkerNo8401').getValue()
					};
					Ext.Ajax.request({
						method:'POST',
						url:'fcdata_RequestAction_noDifferenceSave',
						params:params,
						success:function(response){
							msgShow.hide();
							var objData = Ext.decode(response.responseText);
							if(objData.isSucc){
								Ext.example.msg('提示',objData.msg);
								//Ext.getCmp('cmbWorkerNo8401').setValue("");  7-18屏蔽
								selectFlag = '1';
								var selectData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
								serialnoFlag = selectData[0].get("serialNo");
								
								Ext.getCmp('grid_01_8401').getStore().reload();
							}else{
								Ext.example.msg('提示',objData.msg+objData.obj);
							}				
						}
					});	
				}
			});
		}
	},
	
	btnConfirm8401Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8401'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_02_8401").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_02_8401',0,gridcount-1))
			{
				return;
			}	
		}else
		{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
    	var objData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection();
		if(objData.length!=0)
		{
			Ext.Msg.confirm("提示", "确定确认？",function(button, text)
			{
				if (button == 'yes') {
					var msgShow = commonMegShow("正在操作,请稍等...");
					var params = {
						strWheresql:objData[0].get('checkNo')+","+objData[0].get('checkType')+","+Ext.getCmp('cmbWorkerNo8401').getValue()
					};
					Ext.Ajax.request({
						method:'POST',
						url:'fcdata_RequestAction_fcdataConfirm',
						params:params,
						success:function(response){
							msgShow.hide();
							var objData = Ext.decode(response.responseText);
							if(objData.isSucc){
								Ext.example.msg('提示',objData.msg);
								Ext.getCmp('cmbWorkerNo8401').setValue("");
								Ext.getCmp('grid_01_8401').getStore().reload();
							}else{
								Ext.example.msg('提示',objData.msg+objData.obj);
							}				
						}
					});	
				}
			});
		}
	},
	
	rdoCheckType8401Change:function(th)
	{
		if(th.getValue().rd==1)
		{
			var strWheresql={
				strFcdataType:1
			};
			Ext.apply(Ext.getCmp('grid_01_8401').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_01_8401').getStore().removeAll();
			Ext.getCmp('grid_01_8401').getStore().load();
			
		}else if(th.getValue().rd==2)
		{
			var strWheresql={
				strFcdataType:2
			};
			Ext.apply(Ext.getCmp('grid_01_8401').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_01_8401').getStore().removeAll();
			Ext.getCmp('grid_01_8401').getStore().load();
			
		}else if(th.getValue().rd==3)
		{
			var strWheresql={
				strFcdataType:3
			};
			Ext.apply(Ext.getCmp('grid_01_8401').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_01_8401').getStore().removeAll();
			Ext.getCmp('grid_01_8401').getStore().load();
		}
	},
	
	//填充货位
	cmbCellNo8401Beforequery:function()
	{
		Ext.getCmp('cmbCellNo8401').getStore().proxy.extraParams.strCheckNo = 
			Ext.getCmp('grid_01_8401').getSelectionModel().getSelection()[0].get('checkNo');
	},
	//根据货位cset_owner_cell找货主
	ownerCellNo8401:function(){
		Ext.Ajax.request({
			method:'post',
			url:'fcdata_RequestAction_queryOwnerCellNo',
			params:
			{
				strWheresql:Ext.getCmp('cmbCellNo8401').getValue()
		    },
		    success:function(response)
		    {
		    	var res = Ext.decode(response.responseText);
		        if(res.isSucc){
		        	Ext.getCmp('cmbOwnerNo8401').setValue(res.obj);
		        	Ext.getCmp('cmbArticleNo8401').focus();
		        }
		    }
		});
	},
	
	 //商品下拉(如果货位有绑定货主则只能下拉该货主的商品)
	cmbArticleNo8401forequery:function(){
		//绑定货主
		if(Ext.getCmp('cmbOwnerNo8401').getValue() != null && Ext.getCmp('cmbOwnerNo8401').getValue()!=''){
			var detail1 = [];
			var d={
				columnId:'t1.owner_no',
				value:Ext.getCmp('cmbOwnerNo8401').getValue()
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('cmbArticleNo8401').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('cmbArticleNo8401').getStore().removeAll();
			Ext.getCmp('cmbArticleNo8401').getStore().load();
		}else{//不绑定货主，则判断盘点单头档的货主是否为N，不为N则只能选择该盘点单头档的货主下的商品，为N不管
			var objData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection()[0];
			if(objData.data.ownerNo != 'N'){
				
				var detail1 = [];
				var d={
					columnId:'t1.owner_no',
					value:objData.data.ownerNo
				};
				detail1.push(d);
				var jsonDetail1 = Ext.encode(detail1);
				var wheresql = {
					strQuery : jsonDetail1
				};
				Ext.apply(Ext.getCmp('cmbArticleNo8401').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('cmbArticleNo8401').getStore().removeAll();
				Ext.getCmp('cmbArticleNo8401').getStore().reload();
				
			}else{
				var wheresql = {
						strQuery : null
				};
				Ext.apply(Ext.getCmp('cmbArticleNo8401').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('cmbArticleNo8401').getStore().removeAll();
				Ext.getCmp('cmbArticleNo8401').getStore().load();
			}
			
		}
	},
	articleNo8401Select:function(combo)
	{
		Ext.Ajax.request({
			method:'post',
			url:'fcdata_RequestAction_queryArticleInfo',
			params:
			{
				strWheresql:combo.getValue()
		    },
		    success:function(response)
		    {
		    	debugger;
		    	var res = Ext.decode(response.responseText);
		    	//7-19添加   给中包装数量赋值
		    	Ext.getCmp('minPackingQty8401').setValue(res[0].qminOperatePacking);
		    	
		    	//Ext.getCmp('txtArticleNo8401').setValue(res[0].articleNo);
		    	Ext.getCmp('cmbOwnerNo8401').setValue(res[0].ownerNo);
		    	Ext.getCmp('txtBarcode8401').setValue(res[0].barcode);
		    	Ext.getCmp('txtArticleName8401').setValue(res[0].articleName);
		    	Ext.getCmp('txqminOperatePacking8401').setValue(res[0].qminOperatePacking);
		    	
		    	Ext.getCmp('guarantee8401').setValue(res[0].guarantee);
		        lotType8401 = res[0].lotType;
		    	queryBatchDisplay8401(res[0].field);
		    	if(lotType8401 == '1'){
		    		Ext.getCmp('dateProduceDate8401').setValue('1900-01-01');
		    		Ext.getCmp('dateExpireDate8401').setValue('1900-01-01');
		    		Ext.getCmp('dateProduceDate8401').setReadOnly(true);
		    		Ext.getCmp('dateExpireDate8401').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8401').getStore().removeAll();
		    		var model = {'lotNo':'N'};
		    		Ext.getCmp('txtLotNo8401').getStore().add(model);
		    		
		    		Ext.getCmp('txtLotNo8401').setValue('');
		    		Ext.getCmp('txtLotNo8401').getStore().removeAll();
		    	}else if(lotType8401 == '2'){
		    		Ext.getCmp('dateProduceDate8401').setValue('');
		    		Ext.getCmp('dateExpireDate8401').setValue('');
		    		Ext.getCmp('dateProduceDate8401').setReadOnly(false);
		    		Ext.getCmp('dateExpireDate8401').setReadOnly(false);
		    		
		    		Ext.getCmp('txtLotNo8401').getStore().removeAll();
		    		var model = {'lotNo':'N'};
		    		Ext.getCmp('txtLotNo8401').getStore().add(model);
		    		
		    		Ext.getCmp('txtLotNo8401').setValue('N');
		    		Ext.getCmp('txtLotNo8401').setReadOnly(true);
		    	}else if(lotType8401 == '3'){
		    		Ext.getCmp('dateProduceDate8401').setValue('');
		    		Ext.getCmp('dateExpireDate8401').setValue('');
		    		Ext.getCmp('txtLotNo8401').setValue('');
		    		Ext.getCmp('dateExpireDate8401').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8401').setReadOnly(true);
		    	}else if(lotType8401 == '4'){
		    		Ext.getCmp('dateProduceDate8401').setValue('1900-01-01');
		    		Ext.getCmp('dateExpireDate8401').setValue('1900-01-01');
		    		Ext.getCmp('dateProduceDate8401').setReadOnly(true);
		    		Ext.getCmp('dateExpireDate8401').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8401').getStore().removeAll();
		    		
		    		var model = {'lotNo':'N'};
		    		Ext.getCmp('txtLotNo8401').getStore().add(model);
		    		
		    		Ext.getCmp('txtLotNo8401').setValue('N');
		    		Ext.getCmp('txtLotNo8401').setReadOnly(true);
		    	}
		    	Ext.getCmp('cmbPackingQty8401').focus();
		    	Ext.getCmp('cmbPackingQty8401').setValue('');
		    	Ext.getCmp('cmbPackingQty8401').getStore().proxy.extraParams.strWheresql =combo.getValue();
				Ext.getCmp('cmbPackingQty8401').getStore().removeAll();
				Ext.getCmp('cmbPackingQty8401').getStore().load();
		    }
		});
	},
	
	/*cmbPackingQty8401Focus:function(th)
	{
		
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('cmbArticleNo8401').getValue();
		th.getStore().load();
	},*/
	
	//7-18添加
	 getSelectFlag:function(){
 		 return selectFlag;
 	 },
 	 
 	 setSelectFlag:function(value){
 		selectFlag =value;
 	 },							
 	getSerialnoFlag:function(){
		 return serialnoFlag;
	 },
	 
	 setSerialnoFlag:function(value){
		 serialnoFlag =value;
	 },
 	 
 	 
	
	cmbPackingQty8401Select:function(combo)
	{
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
			params:{
				strArticleNo:Ext.getCmp('cmbArticleNo8401').getValue(),
				strPackingQty:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	Ext.getCmp('txtPackingUnit8401').setValue(res[0].packingUnit);
		    	Ext.getCmp('txtSpec8401').setValue(res[0].spec);
		    	Ext.getCmp('numPoBox8401').setValue(0);
		    	//7-19添加
		    	Ext.getCmp('numPoMin8401').setValue(0);
		    	Ext.getCmp('numPoDis8401').setValue(0);
		    }
		});
	},
	
	txtLotNo8401Blur:function(th)
	{
		this.checkUnique8401(th);
	},
	
	dateProduceDate8401Change:function(th)
	{
		if(!this.checkUnique8401(th))
		{
			if(!Ext.isEmpty(th.getValue()) && Ext.getCmp('guarantee8401').getValue()!=-1)
			{
				Ext.getCmp('dateExpireDate8401').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, Ext.getCmp('guarantee8401').getValue()*1),'Y-m-d'));	
			}	
		}
		//debugger
		if(lotType8401 == '3'){
			
			if(!Ext.isEmpty(Ext.getCmp('dateProduceDate8401').getValue())){
				var articleNo = Ext.getCmp('cmbArticleNo8401').getValue();
				var produceDate = Ext.getCmp('dateProduceDate8401').getValue();
				var strWheresql={
						articleNo:articleNo,
						produceDate:Ext.Date.format(produceDate,'Y/m/d')
					};
				
					Ext.apply(Ext.getCmp('txtLotNo8401').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('txtLotNo8401').getStore().load();
					Ext.getCmp('dateExpireDate8401').setReadOnly(false);
		    		Ext.getCmp('txtLotNo8401').setReadOnly(false);
	  
		 }else{
		 Ext.example.msg('提示',"请输入生产日期");
	     }
	}	
		
	},
	
	dateExpireDate8401Change:function(th)
	{
		if(!this.checkUnique8401(th))
		{
			if(!Ext.isEmpty(th.getValue()) && Ext.getCmp('guarantee8401').getValue()!=-1)
			{
				Ext.getCmp('dateProduceDate8401').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, Ext.getCmp('guarantee8401').getValue()*-1),'Y-m-d'));
			}
		}
	},
	
	checkUnique8401:function(th){
		var cmbCellNo8401 = Ext.getCmp('cmbCellNo8401').getValue();
		var cmbArticleNo8401 = Ext.getCmp('cmbArticleNo8401').getValue();
		var cmbPackingQty8401 = Ext.getCmp('cmbPackingQty8401').getValue();
		var dateProduceDate8401 = Ext.getCmp('dateProduceDate8401').getValue();
		var dateExpireDate8401 = Ext.getCmp('dateExpireDate8401').getValue();
		var cmblotNo=Ext.getCmp('txtLotNo8401').getValue();
		
		if(!Ext.isEmpty(cmbCellNo8401)
				&&!Ext.isEmpty(cmbArticleNo8401)
					&&!Ext.isEmpty(cmbPackingQty8401)
						&&(!Ext.isEmpty(dateProduceDate8401)
								||!Ext.isEmpty(dateExpireDate8401))
		){
			if(th.fieldLabel==$i18n.lot_no)
			{	
				if(Ext.getCmp('grid_02_8401').getStore().findBy(function(record, id) {  
				return (typeof(record.get('expireDate'))=="string"?record.get('expireDate'):Ext.Date.format(new Date(record.get('expireDate')),'Y-m-d'))==
					(typeof(dateExpireDate8401)=="string"?dateExpireDate8401:Ext.Date.format(new Date(dateExpireDate8401),'Y-m-d'))
				&& record.get('cellNo')== cmbCellNo8401
				&& record.get('packingQty')== cmbPackingQty8401
				&& record.get('articleNo')== cmbArticleNo8401
				&& record.get('lotNo')== cmblotNo;
				})!=-1)	
				{
					th.setValue('');
					th.focus();
					Ext.example.msg('提示', "【储位】、【商品内码】、【包装数量】、【生产日期】、【批号】不能重复，请重新输入！");
					return false;
				}
			}else{
				if(Ext.getCmp('grid_02_8401').getStore().findBy(function(record, id) {  
				return (typeof(record.get('produceDate'))=="string"?record.get('produceDate'):Ext.Date.format(new Date(record.get('produceDate')),'Y-m-d'))==
					(typeof(dateProduceDate8401)=="string"?dateProduceDate8401:Ext.Date.format(new Date(dateProduceDate8401),'Y-m-d'))
				&& record.get('cellNo')== cmbCellNo8401
				&& record.get('packingQty')== cmbPackingQty8401
				&& record.get('articleNo')== cmbArticleNo8401
				&& record.get('lotNo')== cmblotNo;;
				})!=-1)	
				{//debugger;
					th.setValue('');
					th.focus();
					Ext.example.msg('提示', "【储位】、【商品内码】、【包装数量】、【生产日期】、【批号】不能重复，请重新输入！");
					return false;
				}
			}
		}
	},
	
	saveAndAdd8401Click:function()
	{
		if(!CheckIsInputAll('form_02_8401'))
		{
			return;
		}
		//7-22
		//总数量判断
		var totalQty = Ext.getCmp('numPoBox8401').getValue() + Ext.getCmp('numPoDis8401').getValue()
			+ Ext.getCmp('numPoMin8401').getValue();
		if(totalQty == 0){
			Ext.example.msg('提示','总数不能为0,请重新录入');
			Ext.getCmp('numPoBox8401').focus(false, 10);
			return;
		}
		 
		if(Ext.getCmp('cmbWheeherWrite8401').getValue()==2)
		{
			if(Ext.isEmpty(Ext.getCmp('txtLabelNo8401').getValue()) || Ext.getCmp('txtLabelNo8401').getValue()=='N')
			{
				Ext.example.msg('提示','主标签号不能为N');
				return;
			}
		}else if(Ext.getCmp('cmbWheeherWrite8401').getValue()==3)
		{
			if((Ext.isEmpty(Ext.getCmp('txtLabelNo8401').getValue())|| Ext.getCmp('txtLabelNo8401').getValue()=='N')
					||(Ext.isEmpty(Ext.getCmp('txtSubLabelNo8401').getValue())|| Ext.getCmp('txtSubLabelNo8401').getValue()=='N'))
			{
				Ext.example.msg('提示','主标签号或子标签号不能为N');
				return;
			}
		}
		var msgShow = commonMegShow("正在保存,请稍等...");
		var objData = Ext.getCmp('grid_01_8401').getSelectionModel().getSelection()[0];
		var detail1=[];
		if(Ext.getCmp('cmbWheeherWrite8401').getValue()==2)
		{
			g_strSubLabelNo=Ext.getCmp('txtLabelNo8401').getValue();
		}else if(Ext.getCmp('cmbWheeherWrite8401').getValue()==3)
		{
			g_strSubLabelNo=Ext.getCmp('txtSubLabelNo8401').getValue();
		}
		
		//debugger;
		var poD={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:objData.data.warehouseNo,//record.get('warehouseNo'),
				checkNo:objData.data.checkNo,//record.get('checkNo'),
				//rowId:i
			},
			ownerNo:Ext.getCmp('cmbOwnerNo8401').getValue(),
			checkType:objData.data.checkType,
			checkWorker:Ext.getCmp('cmbWorkerNo8401').getValue(),
			cellNo:Ext.getCmp('cmbCellNo8401').getValue(),
			articleNo:Ext.getCmp('cmbArticleNo8401').getValue(),
			packingQty:Ext.getCmp('cmbPackingQty8401').getValue(),
			produceDate:Ext.Date.format(Ext.getCmp('dateProduceDate8401').getValue(),'Y-m-d'),
			expireDate:Ext.Date.format(Ext.getCmp('dateExpireDate8401').getValue(),'Y-m-d'),
			quality:Ext.getCmp('cmbQuality8401').getValue(),
			lotNo:Ext.getCmp('txtLotNo8401').getValue(),
			//7-19修改
			checkQty:Ext.getCmp('cmbPackingQty8401').getValue()*Ext.getCmp('numPoBox8401').getValue()
				+Ext.getCmp('numPoMin8401').getValue()*Ext.getCmp('minPackingQty8401').getValue()
				+Ext.getCmp('numPoDis8401').getValue(),
			
			labelNo:Ext.getCmp('txtLabelNo8401').getValue(),
			subLabelNo:g_strSubLabelNo,
			stockType:1,
			addFlag:1,
			stockValue:'N',
			rsvBatch1:Ext.getCmp('txtRsvBatch18401').getValue(),
			rsvBatch2:Ext.getCmp('txtRsvBatch28401').getValue(),
			rsvBatch3:Ext.getCmp('txtRsvBatch38401').getValue(),
			rsvBatch4:Ext.getCmp('txtRsvBatch48401').getValue(),
			rsvBatch5:Ext.getCmp('txtRsvBatch58401').getValue(),
			rsvBatch6:Ext.getCmp('txtRsvBatch68401').getValue(),
			rsvBatch7:Ext.getCmp('txtRsvBatch78401').getValue(),
			rsvBatch8:Ext.getCmp('txtRsvBatch88401').getValue(),
		};
		detail1.push(poD);
		var strDetail=Ext.encode(detail1);
		var s1;
		var params=
		{
			strDetail:strDetail
		};
		Ext.Ajax.request({
			method:'post',
			url:'fcdata_RequestAction_saveFcdata_CheckD',
			params:params,
			async:false,
			success:function(response){
				msgShow.hide();
				var data = Ext.decode(response.responseText);
				if(data.isSucc)
				{
					Ext.getCmp('form_02_8401').getForm().reset();
					Ext.getCmp('grid_02_8401').store.reload({
						callback:function(){
								var gridcount=Ext.getCmp('grid_02_8401').getStore().getCount();
						   		if(gridcount>0){
						   			for(var i=0;i<gridcount;i++){
						   				var data2 = Ext.getCmp('grid_02_8401').getStore().getAt(i);
						   				if(data2.get('rowId')==data.obj){
						   					//var i = i+1;
						   	                // Ext.example.msg('提示',data.msg+'第【'+i+'】行');
											 Ext.getCmp('grid_02_8401').getSelectionModel().select(i);

						   				}

						   			}

						        }
						 
						}
					});
					Ext.getCmp('cmbWheeherWrite8401').setValue('1');
					Ext.getCmp('cmbQuality8401').setValue('0');
					Ext.getCmp('cmbCellNo8401').focus(false, 10);
					g_strSubLabelNo='N';
				}else
				{
					Ext.example.msg('提示',data.msg);
				}
			}
		
		});
		
	},
	
	cmbWheeherWrite8401Select:function(combo)
	{
		if(combo.getValue()==1)
		{
			Ext.getCmp('txtLabelNo8401').setReadOnly(true);
			Ext.getCmp('txtSubLabelNo8401').setReadOnly(true);
			Ext.getCmp('txtLabelNo8401').setValue('N');
			Ext.getCmp('txtSubLabelNo8401').setValue('N');
		}else if(combo.getValue()==2)
		{
			Ext.getCmp('txtLabelNo8401').setReadOnly(false);
			Ext.getCmp('txtSubLabelNo8401').setReadOnly(true);
			Ext.getCmp('txtLabelNo8401').setValue('');
			Ext.getCmp('txtLabelNo8401').focus(false, 10);
		}else if(combo.getValue()==3)
		{
			Ext.getCmp('txtLabelNo8401').setReadOnly(false);
			Ext.getCmp('txtSubLabelNo8401').setReadOnly(false);
			Ext.getCmp('txtLabelNo8401').setValue('');
			Ext.getCmp('txtSubLabelNo8401').setValue('');
			Ext.getCmp('txtLabelNo8401').focus(false, 10);
		}
	},
	
	txtLabelNo8401Blur:function()
	{
		if(Ext.getCmp('cmbWheeherWrite8401').getValue()=='2')
		{
			if(Ext.isEmpty(Ext.getCmp('cmbCellNo8401').getValue()))
			{
				Ext.getCmp('cmbCellNo8401').focus(false, 10);
				Ext.getCmp('txtLabelNo8401').setValue('N');
				Ext.example.msg('提示','请先输入储位');
			}else
			{
				
				var params=
				{
					strCellNo:Ext.getCmp('cmbCellNo8401').getValue(),
					strLabelNo:Ext.getCmp('txtLabelNo8401').getValue()
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_RequestAction_checkLabelNo',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(!data.isSucc)
						{
							Ext.example.msg('提示',data.msg);
							Ext.getCmp('txtLabelNo8401').setValue('');
							Ext.getCmp('txtLabelNo8401').focus(false, 10);
						}else{
							Ext.getCmp('txtSubLabelNo8401').focus(false, 10);
						}
					}
				});
			}
		}else if(Ext.getCmp('cmbWheeherWrite8401').getValue()=='3')
		{
			if(Ext.isEmpty(Ext.getCmp('cmbCellNo8401').getValue()))
			{
				Ext.getCmp('cmbCellNo8401').focus(false, 10);
				Ext.getCmp('txtLabelNo8401').setValue('N');
				Ext.getCmp('txtSubLabelNo8401').setValue('N');
				Ext.example.msg('提示','请先输入储位');
			}else
			{
				
				var params=
				{
					strCellNo:Ext.getCmp('cmbCellNo8401').getValue(),
					strLabelNo:Ext.getCmp('txtLabelNo8401').getValue(),
					strSubLabelNo:Ext.getCmp('txtSubLabelNo8401').getValue()
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_RequestAction_checkLabelNoAndSubLabelNo',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(!data.isSucc)
						{
							Ext.example.msg('提示',data.msg);
							Ext.getCmp('txtLabelNo8401').setValue('');
							Ext.getCmp('txtSubLabelNo8401').setValue('');
							Ext.getCmp('txtLabelNo8401').focus(false, 10);
						}else{
							Ext.getCmp('txtSubLabelNo8401').focus(false, 10);
						}
					}
				});
			}
		}
	},
	txtSubLabelNo8401Blur:function(){
		if(Ext.getCmp('cmbWheeherWrite8401').getValue()=='3')
		{
			if(Ext.isEmpty(Ext.getCmp('cmbCellNo8401').getValue()))
			{
				Ext.getCmp('cmbCellNo8401').focus(false, 10);
				Ext.getCmp('txtLabelNo8401').setValue('N');
				Ext.getCmp('txtSubLabelNo8401').setValue('N');
				Ext.example.msg('提示','请先输入储位');
			}else
			{
				
				var params=
				{
					strCellNo:Ext.getCmp('cmbCellNo8401').getValue(),
					strLabelNo:Ext.getCmp('txtLabelNo8401').getValue(),
					strSubLabelNo:Ext.getCmp('txtSubLabelNo8401').getValue()
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_RequestAction_checkLabelNoAndSubLabelNo',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(!data.isSucc)
						{
							Ext.example.msg('提示',data.msg);
							Ext.getCmp('txtSubLabelNo8401').setValue('');
							Ext.getCmp('txtSubLabelNo8401').focus(false, 10);
						}
					}
				});
			}
		}
	},
	
	boxkeydown:function(th,e,eOpts)
	{
		if (e.getKey() == e.ENTER) 
		{
			if(th.id=='cmbArticleNo8401'){
				Ext.getCmp('cmbPackingQty8401').focus();
			}else if(th.id=='cmbPackingQty8401'){
				Ext.getCmp('numPoBox8401').focus();
			}else if(th.id=='txtSubLabelNo8401'){
				Ext.getCmp('dateProduceDate8401').focus();
			}else{
				th.nextSibling().focus();
			}
        }
	}
});

/**
 * 设置批属性是否显示
 */
function queryBatchDisplay8401(field)
{
	var fields=[];
	fields=field.split(',');
	Ext.getCmp('dateProduceDate8401').setVisible(false);
	Ext.getCmp('dateExpireDate8401').setVisible(false);
	Ext.getCmp('cmbQuality8401').setVisible(false);
	Ext.getCmp('txtLotNo8401').setVisible(false);
	Ext.getCmp('txtRsvBatch18401').setVisible(false);
	Ext.getCmp('txtRsvBatch28401').setVisible(false);
	Ext.getCmp('txtRsvBatch38401').setVisible(false);
	Ext.getCmp('txtRsvBatch48401').setVisible(false);
	Ext.getCmp('txtRsvBatch58401').setVisible(false);
	Ext.getCmp('txtRsvBatch68401').setVisible(false);
	Ext.getCmp('txtRsvBatch78401').setVisible(false);
	Ext.getCmp('txtRsvBatch88401').setVisible(false);
	for (var i=0;i<fields.length;i++)
	{
		if(fields[i]=='PRODUCE_DATE')
		{
			Ext.getCmp('dateProduceDate8401').setVisible(true);
		}else if(fields[i]=='EXPIRE_DATE')
		{
			Ext.getCmp('dateExpireDate8401').setVisible(true);
		}else if(fields[i]=='QUALITY')
		{
			Ext.getCmp('cmbQuality8401').setVisible(true);
		}else if(fields[i]=='LOT_NO')
		{
			Ext.getCmp('txtLotNo8401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH1')
		{
			Ext.getCmp('txtRsvBatch18401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH2')
		{
			Ext.getCmp('txtRsvBatch28401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH3')
		{
			Ext.getCmp('txtRsvBatch38401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH4')
		{
			Ext.getCmp('txtRsvBatch48401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH5')
		{
			Ext.getCmp('txtRsvBatch58401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH6')
		{
			Ext.getCmp('txtRsvBatch68401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH7')
		{
			Ext.getCmp('txtRsvBatch78401').setVisible(true);
		}else if(fields[i]=='RSV_BATCH8')
		{
			Ext.getCmp('txtRsvBatch88401').setVisible(true);
		}else{
			
		}
		
		
	}	
}

//检查组件下控件必录项是否已录入
function CheckIsInputAll(id){
	var constructor=Ext.ComponentQuery.query('#'+id+' field');
	for(i=0;i<constructor.length;i++)
	{
		if(constructor[i].beforeLabelTextTpl===required){
			if(Ext.isEmpty(constructor[i].getValue()))
			{
				Ext.example.msg('提示','请输入'+constructor[i].fieldLabel+'！');		
				constructor[i].focus();
				return false;
			}
		}
	}
	return true;
}