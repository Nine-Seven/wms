/**
 * 模块名称：复盘/三盘回单
 * 模块编码：8501
 * 创建：Jun
 */
var g_isCanEdit8501=false;
var g_fcdata_type=1;//是否肓盘
var g_strSubLabelNo='N';
var lotType8501;

var selectFlag='';  //7-22   
var serialnoFlag='';  //7-22

Ext.define('cms.controller.fcdata.fcdata_SecondThirdRequestController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.fcdata.fcdata_SecondThirdRequestUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//复盘/三盘回单>>选择盘点单头
			'fcdata_SecondThirdRequestUI grid[id=grid_01_8501]':
			{
				beforeselect:this.grid_01_8501Beforeselect,
				selectionchange:this.grid_01_8501Selectionchange
			},//复盘回单>>选择盘点明细
			'fcdata_SecondThirdRequestUI grid[id=grid_02_8501]':
			{
				beforeedit:this.grid_02_8501Beforeedit
			},//三盘回单>>选择盘点明细
			'fcdata_SecondThirdRequestUI grid[id=grid_03_8501]':
			{
				beforeedit:this.grid_03_8501Beforeedit
			},//修改
			'fcdata_SecondThirdRequestUI button[name=edit]':
			{
				click:this.editClick
			},//撤销
			'fcdata_SecondThirdRequestUI button[name=undo]':
			{
				click:this.undoClick
			},//保存
			'fcdata_SecondThirdRequestUI button[name=save]':
			{
				click:this.saveClick
			},//查找
			'fcdata_SecondThirdRequestUI button[name=query]':
			{
				click:this.queryClick
			},//刷新
			'fcdata_SecondThirdRequestUI button[name=refresh]':
			{
				click:this.refreshClick
			},//新增品项
			'fcdata_SecondThirdRequestUI button[id=btnNewitem8501]':
			{
				click:this.btnNewitem8501Click
			},//零回单
			'fcdata_SecondThirdRequestUI button[id=btnZeroreceipt8501]':
			{
				click:this.btnZeroreceipt8501Click
			},//无差异保存
			'fcdata_SecondThirdRequestUI button[id=btnNoDifference8501]':
			{
				click:this.btnNoDifference8501Click
			},//确认
			'fcdata_SecondThirdRequestUI button[id=btnConfirm8501]':
			{
				click:this.btnConfirm8501Click
			},//复盘/三盘筛选
			'fcdata_SecondThirdRequestUI radiogroup[id=rdoCheckType8501]':
			{
				change:this.rdoCheckType8501Change
			},//填充储位
			'cdef_DefCellCombo[id=cmbCellNo8501]':
			{
				beforequery:this.cmbCellNo8501Beforequery,
				select:this.ownerCellNo8501
			},//商品选择
			'bdef_DefArticleCombo[id=cmbArticleNo8501]':{
				beforequery:this.cmbArticleNo8501forequery,
				select:this.articleNo8501Select
			},//包装选择
			'bdef_PackingQtyCombo[id=cmbPackingQty8501]':{
				select:this.cmbPackingQty8501Select
			},
			//商品下拉失去隹点
			'fcdata_SecondThirdRequestWindow bdef_DefArticleCombo[id=cmbArticleNo8501]':
			{
				blur:this.checkUnique8501
			},//储位下拉失去隹点
			'fcdata_SecondThirdRequestWindow cdef_DefCellCombo[id=cmbCellNo8501]':
			{
				blur:this.checkUnique8501
			},//商品下拉失去隹点
			'fcdata_SecondThirdRequestWindow cmbArticleNo8501[id=bdef_DefArticleCombo]':
			{
				blur:this.checkUnique8501
			},//包装下拉失去隹点
			'fcdata_SecondThirdRequestWindow bdef_PackingQtyCombo[id=cmbPackingQty8501]':
			{
				blur:this.checkUnique8501
			},//批号选择
			'fcdata_SecondThirdRequestWindow textfield[id=txtLotNo8501]':
			{
				blur:this.txtLotNo8501Blur
			},//生产日期先择
			'fcdata_SecondThirdRequestWindow datefield[id=dateProduceDate8501]':
			{
				blur:this.dateProduceDate8501Change
			},//有效期选择
			'fcdata_SecondThirdRequestWindow datefield[id=dateExpireDate8501]':
			{
				blur:this.dateExpireDate8501Change
			},//保存并新增
			'fcdata_SecondThirdRequestWindow button[id=saveAndAdd8501]':
			{
				click:this.saveAndAdd8501Click
			},//选择是否填写标签号
			'fcdata_SecondThirdRequestWindow wms_DefFieldValCombo[id=cmbWheeherWrite8501]':
			{
				select:this.cmbWheeherWrite8501Select
			},//校验主板号
			'fcdata_SecondThirdRequestWindow textfield[id=txtLabelNo8501]':
			{
				blur:this.txtLabelNo8501Blur
			},//校验子板号
			'fcdata_SecondThirdRequestWindow textfield[id=txtSubLabelNo8501]':
			{
				blur:this.txtSubLabelNo8501Blur
			},
			'fcdata_SecondThirdRequestWindow field':
			{
				specialkey:this.boxkeydown
			}
			
		});
	},
	
	initializtion:function()
	{
		//显示变量0为不显示，1为显示
		var thirdPlanBox8501=commonGetModuleField('8501','thirdPlanBox')[0].flag;
		var thirdPlanqQmin8501=commonGetModuleField('8501','thirdPlanqQmin')[0].flag;
		var thirdPlanDis8501=commonGetModuleField('8501','thirdPlanDis')[0].flag;
		var rePlanBox8501=commonGetModuleField('8501','rePlanBox')[0].flag;
		var rePlanQmin8501=commonGetModuleField('8501','rePlanQmin')[0].flag;
		var rePlanDis8501=commonGetModuleField('8501','rePlanDis')[0].flag;

		var packingUnit8501_1=commonGetModuleField('8501','packingUnit')[0].flag;
		var packingSpec8501_1=commonGetModuleField('8501','packingSpec')[0].flag;
		var packingUnit8501_2=commonGetModuleField('8501','packingUnit')[0].flag;
		var packingSpec8501_2=commonGetModuleField('8501','packingSpec')[0].flag;
		if(rePlanBox8501==0){
			Ext.getCmp('rePlanBox8501').setVisible(false);
		}
		if(rePlanQmin8501==0){
			Ext.getCmp('rePlanQmin8501').setVisible(false);
		}
		if(rePlanDis8501==0){
			Ext.getCmp('rePlanDis8501').setVisible(false);
		}
		if(thirdPlanBox8501==0){
			Ext.getCmp('thirdPlanBox8501').setVisible(false);
		}
		if(thirdPlanqQmin8501==0){
			Ext.getCmp('thirdPlanqQmin8501').setVisible(false);
		}
		if(thirdPlanDis8501==0){
			Ext.getCmp('thirdPlanDis8501').setVisible(false);
		}
		if(packingUnit8501_2==0){
			Ext.getCmp('packingUnit8501_2').setVisible(false);
		}
		if(packingUnit8501_2==0){
			Ext.getCmp('packingUnit8501_2').setVisible(false);
		}
		if(packingUnit8501_1==0){
			Ext.getCmp('packingUnit8501_2').setVisible(false);
		}
		if(packingUnit8501_1==0){
			Ext.getCmp('packingUnit8501_2').setVisible(false);
		}
	},
	
	grid_01_8501Beforeselect:function()
	{
		if(g_isCanEdit8501)
	    {
	        return  false;  
	    }
	},
	
	grid_01_8501Selectionchange:function()
	{
		var Objdata = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
		if(Objdata.length!=0)
		{
			var strWheresql={
				strWheresql:Objdata[0].get('checkNo')
			};
			Ext.apply(Ext.getCmp('grid_02_8501').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_8501').getStore().removeAll();
			Ext.getCmp('grid_02_8501').getStore().load();
			
			Ext.get('rgstName8501').dom.innerHTML=Objdata[0].get('rgstName');
			Ext.get('rgstDate8501').dom.innerHTML=Objdata[0].get('rgstDate');
			Ext.get('updtName8501').dom.innerHTML=Objdata[0].get('updtName');
			Ext.get('updtDate8501').dom.innerHTML=Objdata[0].get('updtDate');
			if(Objdata[0].get('checkType')==2)
			{
				Ext.getCmp('grid_02_8501').setVisible(true);
				Ext.getCmp('grid_03_8501').setVisible(false);
				Ext.getCmp('btnNoDifference8501').setDisabled(false);
				Ext.getCmp('btnZeroreceipt8501').setDisabled(false);
			}else if(Objdata[0].get('checkType')==3)
			{
				Ext.getCmp('grid_02_8501').setVisible(false);
				Ext.getCmp('grid_03_8501').setVisible(true);
				Ext.getCmp('btnNoDifference8501').setDisabled(true);
				Ext.getCmp('btnZeroreceipt8501').setDisabled(true);
			}
		}else
		{
			Ext.getCmp('grid_02_8501').getStore().removeAll();
			Ext.get('rgstName8501').dom.innerHTML="";
			Ext.get('rgstDate8501').dom.innerHTML="";
			Ext.get('updtName8501').dom.innerHTML="";
			Ext.get('updtDate8501').dom.innerHTML="";
		}
	},
	
	grid_02_8501Beforeedit:function(editor,e,eOpts)
	{
		if(!g_isCanEdit8501)
	    {
	        editor.cancel = true;
	        return  false;  
	    }
	},
	
	grid_03_8501Beforeedit:function(editor,e,eOpts)
	{
		if(!g_isCanEdit8501)
	    {
	        editor.cancel = true;
	        return  false;  
	    }
	},
	
	editClick:function()
	{
		var data = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
        if(data.length != 0)
        {
			commonMenu4Button('menu8501','edit');
			g_isCanEdit8501=true;
			Ext.getCmp('cmbWorkerNo8501').focus();
			Ext.getCmp('btnNewitem8501').setDisabled(true);
			Ext.getCmp('btnNoDifference8501').setDisabled(true);
			Ext.getCmp('btnZeroreceipt8501').setDisabled(true);
			Ext.getCmp('btnConfirm8501').setDisabled(true);
        }
	},
	
	undoClick:function()
	{
		commonMenu4Button('menu8501','undo');
		g_isCanEdit8501=false;
		Ext.getCmp('grid_01_8501').fireEvent('selectionchange');
		Ext.getCmp('btnNewitem8501').setDisabled(false);
		Ext.getCmp('btnNoDifference8501').setDisabled(false);
		Ext.getCmp('btnZeroreceipt8501').setDisabled(false);
		Ext.getCmp('btnConfirm8501').setDisabled(false);
	},
	
	saveClick:function()
	{
		if(!commonCheckIsInputAll('form_01_8501'))
		{
			return;
		}
		
		var gridcount=Ext.getCmp("grid_02_8501").getStore().getCount();
		var gridcount3=Ext.getCmp("grid_03_8501").getStore().getCount();
		if(Ext.getCmp('rdoCheckType8501').getValue().rd==2){//复盘
			if(gridcount>0)
			{
				if(!commonCheckdetailgrid('grid_02_8501',0,gridcount-1))
				{
					return;
				}	
			}else
			{			
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
		}else{//三盘
			if(gridcount3>0)
			{
				if(!commonCheckdetailgrid('grid_03_8501',0,gridcount3-1))
				{
					return;
				}	
			}else
			{			
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
		}
		
		
		
		Ext.Msg.confirm("提示", "确定保存？",function(button, text) 
		{
			if (button == 'yes') {
				var msgShow = commonMegShow("正在保存,请稍等...");
				var objData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection()[0];
				var detail1=[];
				for(var i=0;i<gridcount;i++){
					if(objData.data.checkType==2)
					{
						var record=Ext.getCmp('grid_02_8501').getStore().getAt(i);
						var d={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:objData.data.warehouseNo,
								checkNo:objData.data.checkNo,
								rowId:i
							},
							ownerNo:record.get('ownerNo'),
							checkType:objData.data.checkType,
							checkWorker:Ext.getCmp('cmbWorkerNo8501').getValue(),
							cellNo:record.get('cellNo'),
							articleNo:record.get('articleNo'),
							packingQty:record.get('packingQty'),
							produceDate:record.get('produceDate'),
							expireDate:record.get('expireDate'),
							quality:record.get('quality'),
							lotNo:record.get('lotNo'),
							checkQty:record.get('packingQty')*record.get('rePlanBox')
							+record.get('rePlanQmin')*record.get('qminOperatePacking')
						    +record.get('rePlanDis'),
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
					}else if(objData.data.checkType==3)
					{
						var record=Ext.getCmp('grid_03_8501').getStore().getAt(i);
						var d={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:objData.data.warehouseNo,
								checkNo:objData.data.checkNo,
								rowId:i
							},
							ownerNo:record.get('ownerNo'),
							checkType:objData.data.checkType,
							checkWorker:Ext.getCmp('cmbWorkerNo8501').getValue(),
							cellNo:record.get('cellNo'),
							articleNo:record.get('articleNo'),
							packingQty:record.get('packingQty'),
							produceDate:record.get('produceDate'),
							expireDate:record.get('expireDate'),
							quality:record.get('quality'),
							lotNo:record.get('lotNo'),
							checkQty:record.get('packingQty')*record.get('thirdPlanBox')
							+record.get('thirdPlanQmin')*record.get('qminOperatePacking')
						    +record.get('thirdPlanDis'),
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
					}
				};
				
				var strDetail=Ext.encode(detail1);
				
				var params={
					strDetail:strDetail
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_RequestAction_saveFcdata_CheckD',
					params:params,
					success:function(response){
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							commonMenu4Button('menu8501','save');
							Ext.example.msg('提示',data.msg);
							Ext.getCmp('btnNewitem8501').setDisabled(false);
							Ext.getCmp('btnZeroreceipt8501').setDisabled(false);
							Ext.getCmp('btnNoDifference8501').setDisabled(false);
							Ext.getCmp('btnConfirm8501').setDisabled(false);
							Ext.getCmp('grid_01_8501').getStore().reload();
							g_isCanEdit8501=false;
						}else{
							Ext.example.msg('提示',data.msg);
						}
						
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
		queryModuleId=8501;
		queryGrid='grid_01_8501';	
	},
	refreshClick:function()
	{
		Ext.getCmp('grid_01_8501').getStore().reload();
	},
	
	btnNewitem8501Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8501'))
		{
			return;
		}
		Ext.create('cms.view.fcdata.window.fcdata_SecondThirdRequestWindow',{
			title:$i18n.titleadd//新增
		}).show();
		Ext.getCmp('cmbCellNo8501').focus(false, 10);
		Ext.getCmp('cmbWheeherWrite8501').setValue('1');
		Ext.getCmp('cmbQuality8501').setValue('0');
		var rec = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
		if(rec.length!=0){
			/*var detail1 = [];
			var d={
				columnId:'t1.owner_no',
				value:rec[0].get('ownerNo')
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('cmbArticleNo8501').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('cmbArticleNo8501').getStore().removeAll();
			Ext.getCmp('cmbArticleNo8501').getStore().load();*/
		}
	},
	
	btnZeroreceipt8501Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8501'))
		{
			return;
		}
    	var objData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
		if(objData.length!=0){
			Ext.Msg.confirm("提示", "确定零回单？",function(button, text){
				if (button == 'yes')
				{
					var msgShow = commonMegShow("正在操作,请稍等...");
					var params = {
						strWheresql:objData[0].get('checkNo')+","+objData[0].get('checkType')+","+Ext.getCmp('cmbWorkerNo8501').getValue()
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
								Ext.getCmp('cmbWorkerNo8501').setValue("");
								Ext.getCmp('grid_01_8501').getStore().load();
							}else{
								Ext.example.msg('提示',objData.msg+objData.obj);
							}				
						}
					});	
				}
			});
		}
	},
	
	btnNoDifference8501Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8501'))
		{
			return;
		}
    	var objData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
		if(objData.length!=0){
			Ext.Msg.confirm("提示", "确定无差异保存？",function(button, text){
				if (button == 'yes')
				{
					var msgShow = commonMegShow("正在操作,请稍等...");
					var params = {
						strWheresql:objData[0].get('checkNo')+","+objData[0].get('checkType')+","+Ext.getCmp('cmbWorkerNo8501').getValue()
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
								//Ext.getCmp('cmbWorkerNo8501').setValue("");//7-22屏蔽
								selectFlag = '1';
								var selectData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
								serialnoFlag = selectData[0].get("serialNo");
								
								Ext.getCmp('grid_01_8501').getStore().load();
							}else{
								Ext.example.msg('提示',objData.msg+objData.obj);
							}				
						}
					});	
				}
			});
		}
	},
	
	btnConfirm8501Click:function()
	{
		if(!commonCheckIsInputAll('form_01_8501'))
		{
			return;
		}
		/*if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}*/
		var gridcount=Ext.getCmp("grid_02_8501").getStore().getCount();
		var gridcount3=Ext.getCmp("grid_03_8501").getStore().getCount();
		if(Ext.getCmp('rdoCheckType8501').getValue().rd==2){//复盘
			if(gridcount>0)
			{
				if(!commonCheckdetailgrid('grid_02_8501',0,gridcount-1))
				{
					return;
				}	
			}else
			{			
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
		}else{//三盘
			if(gridcount3>0)
			{
				if(!commonCheckdetailgrid('grid_03_8501',0,gridcount3-1))
				{
					return;
				}	
			}else
			{			
				Ext.example.msg('提示',"表体不允许为空，请输入表体！");
				return;
			}
		}
		
    	var objData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection();
		if(objData.length!=0)
		{
			Ext.Msg.confirm("提示", "确定确认？",function(button, text)
			{
				if (button == 'yes') {
					var msgShow = commonMegShow("正在操作,请稍等...");
					var params = {
						strWheresql:objData[0].get('checkNo')+","+objData[0].get('checkType')+","+Ext.getCmp('cmbWorkerNo8501').getValue()
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
								Ext.getCmp('cmbWorkerNo8501').setValue("");
								Ext.getCmp('grid_01_8501').getStore().reload();
							}else{
								Ext.example.msg('提示',objData.msg+objData.obj);
							}				
						}
					});	
				}
			});
		}
	},
	
	rdoCheckType8501Change:function(th)
	{
		if(th.getValue().rd==2)
		{
			var strWheresql={
				strCheckType:2
			};
			Ext.apply(Ext.getCmp('grid_01_8501').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_01_8501').getStore().removeAll();
			Ext.getCmp('grid_01_8501').getStore().load();
		}else if(th.getValue().rd==3)
		{
			var strWheresql={
				strCheckType:3
			};
			Ext.apply(Ext.getCmp('grid_01_8501').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_01_8501').getStore().removeAll();
			Ext.getCmp('grid_01_8501').getStore().load();
		}
	},
	
	cmbCellNo8501Beforequery:function()
	{
		Ext.getCmp('cmbCellNo8501').getStore().proxy.extraParams.strCheckNo = 
			Ext.getCmp('grid_01_8501').getSelectionModel().getSelection()[0].get('checkNo');
	},
	
	//根据货位cset_owner_cell找货主
	ownerCellNo8501:function(){
		Ext.Ajax.request({
			method:'post',
			url:'fcdata_RequestAction_queryOwnerCellNo',
			params:
			{
				strWheresql:Ext.getCmp('cmbCellNo8501').getValue()
		    },
		    success:function(response)
		    {
		    	var res = Ext.decode(response.responseText);
		        if(res.isSucc){
		        	Ext.getCmp('cmbOwnerNo8501').setValue(res.obj);
		        	Ext.getCmp('cmbArticleNo8501').focus();
		        }
		    }
		});
	},
	 //商品下拉(如果货位有绑定货主则只能下拉该货主的商品)
	cmbArticleNo8501forequery:function(){
		//绑定货主
		if(Ext.getCmp('cmbOwnerNo8501').getValue() != null && Ext.getCmp('cmbOwnerNo8501').getValue()!=''){
			var detail1 = [];
			var d={
				columnId:'t1.owner_no',
				value:Ext.getCmp('cmbOwnerNo8501').getValue()
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('cmbArticleNo8501').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('cmbArticleNo8501').getStore().removeAll();
			Ext.getCmp('cmbArticleNo8501').getStore().load();
		}else{//不绑定货主，则判断盘点单头档的货主是否为N，不为N则只能选择该盘点单头档的货主下的商品，为N不管
			var objData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection()[0];
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
				Ext.apply(Ext.getCmp('cmbArticleNo8501').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('cmbArticleNo8501').getStore().removeAll();
				Ext.getCmp('cmbArticleNo8501').getStore().load();
				
			}else{
				var wheresql = {
						strQuery : null
				};
				Ext.apply(Ext.getCmp('cmbArticleNo8501').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('cmbArticleNo8501').getStore().removeAll();
				Ext.getCmp('cmbArticleNo8501').getStore().load();
			}
			
		}
	},
	articleNo8501Select:function(combo)
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
		   
		    	var res = Ext.decode(response.responseText);
		    	Ext.getCmp('cmbOwnerNo8501').setValue(res[0].ownerNo);
		    	Ext.getCmp('txtBarcode8501').setValue(res[0].barcode);
		    	Ext.getCmp('txtArticleName8501').setValue(res[0].articleName);
		    	Ext.getCmp('guarantee8501').setValue(res[0].guarantee);
		    	Ext.getCmp('txqminOperatePacking8501').setValue(res[0].qminOperatePacking);
		        lotType8501 = res[0].lotType;
		    	queryBatchDisplay8501(res[0].field);
		    	if(lotType8501 == '1'){
		    		Ext.getCmp('dateProduceDate8501').setValue('1900-01-01');
		    		Ext.getCmp('dateExpireDate8501').setValue('1900-01-01');
		    		Ext.getCmp('dateProduceDate8501').setReadOnly(true);
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8501').getStore().removeAll();
		    		var model = {'lotNo':'N'};
		    		Ext.getCmp('txtLotNo8501').getStore().add(model);
		    		
		    		Ext.getCmp('txtLotNo8501').setValue('');
		    		Ext.getCmp('txtLotNo8501').getStore().removeAll();
		    	}else if(lotType8501 == '2'){
		    		Ext.getCmp('dateProduceDate8501').setValue('');
		    		Ext.getCmp('dateExpireDate8501').setValue('');
		    		Ext.getCmp('dateProduceDate8501').setReadOnly(false);
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(false);
		    		
		    		Ext.getCmp('txtLotNo8501').getStore().removeAll();
		    		var model = {'lotNo':'N'};
		    		Ext.getCmp('txtLotNo8501').getStore().add(model);
		    		
		    		Ext.getCmp('txtLotNo8501').setValue('N');
		    		Ext.getCmp('txtLotNo8501').setReadOnly(true);
		    	}else if(lotType8501 == '3'){
		    		Ext.getCmp('dateProduceDate8501').setValue('');
		    		Ext.getCmp('dateExpireDate8501').setValue('');
		    		Ext.getCmp('txtLotNo8501').setValue('');
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8501').setReadOnly(true);
		    	}else if(lotType8501 == '4'){
		    		Ext.getCmp('dateProduceDate8501').setValue('1900-01-01');
		    		Ext.getCmp('dateExpireDate8501').setValue('1900-01-01');
		    		Ext.getCmp('dateProduceDate8501').setReadOnly(true);
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8501').getStore().removeAll();
		    		
		    		var model = {'lotNo':'N'};
		    		Ext.getCmp('txtLotNo8501').getStore().add(model);
		    		
		    		Ext.getCmp('txtLotNo8501').setValue('N');
		    		Ext.getCmp('txtLotNo8501').setReadOnly(true);
		    	}
		    	
	/*	    	if(res[0].guarantee=='-1')
		    	{
		    		Ext.getCmp('dateProduceDate8501').setValue('1900-01-01');
		    		Ext.getCmp('dateExpireDate8501').setValue('1900-01-01');
		    		Ext.getCmp('dateProduceDate8501').setReadOnly(true);
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(true);
		    		Ext.getCmp('txtLotNo8501').setValue('N');
		    	}else if(res[0].guarantee=='0')
		    	{
		    		
		    		Ext.getCmp('txtLotNo8501').setValue('');
		    		Ext.getCmp('dateProduceDate8501').setValue('');
		    		Ext.getCmp('dateExpireDate8501').setValue('');
		    		Ext.getCmp('dateProduceDate8501').setReadOnly(false);
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(false);
		    		Ext.Ajax.request({
		    			method:'post',
		    			url:'fcdata_RequestAction_queryLot',
		    			params:
		    			{
		    				strWheresql:combo.getValue()
		    			},
			    		success:function(response)
					    {
					    	var res = Ext.decode(response.responseText);
					    	Ext.getCmp('txtLotNo8501').setValue(res[0].lotNo);
					    	Ext.getCmp('dateProduceDate8501').setValue(res[0].produceDate);
					    	Ext.getCmp('dateExpireDate8501').setValue(res[0].expireDate);
					    }
			    		});
		    	}else
		    	{
		    		Ext.getCmp('txtLotNo8501').setValue('N');
		    		Ext.getCmp('dateProduceDate8501').setValue('');
		    		Ext.getCmp('dateExpireDate8501').setValue('');
		    		Ext.getCmp('dateProduceDate8501').setReadOnly(false);
		    		Ext.getCmp('dateExpireDate8501').setReadOnly(false);
		    	}*/
		    	Ext.getCmp('cmbPackingQty8501').setValue('');
		    	Ext.getCmp('cmbPackingQty8501').getStore().proxy.extraParams.strWheresql =combo.getValue();
				Ext.getCmp('cmbPackingQty8501').getStore().removeAll();
				Ext.getCmp('cmbPackingQty8501').getStore().load();
		    }
		});
	},
	
	/*cmbPackingQty8501Focus:function(th)
	{
		
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('cmbArticleNo8501').getValue();
		th.getStore().load();
	},*/
	
	cmbPackingQty8501Select:function(combo)
	{
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
			params:{
				strArticleNo:Ext.getCmp('cmbArticleNo8501').getValue(),
				strPackingQty:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	Ext.getCmp('txtPackingUnit8501').setValue(res[0].packingUnit);
		    	Ext.getCmp('txtSpec8501').setValue(res[0].spec);
		    	Ext.getCmp('numPoBox8501').setValue(0);
		    }
		});
	},
	
	txtLotNo8501Blur:function(th)
	{
		this.checkUnique8501(th);
//		Ext.Ajax.request({
//			method:'post',
//			url:'fcdata_RequestAction_queryLot',
//			params:
//			{
//				strWheresql:Ext.getCmp('cmbArticleNo8501').getValue()+","+th.getValue()
//			},
//			success:function(response)
//		    {
//		    	var res = Ext.decode(response.responseText);
//		    	//console.log(res);
//		    	if(res.length>0)
//		    	{
//		    		Ext.getCmp('dateProduceDate8501').setValue(res[0].produceDate);
//			    	Ext.getCmp('dateExpireDate8501').setValue(res[0].expireDate);
//		    	}else
//		    	{
//		    		Ext.getCmp('dateProduceDate8501').setValue('');
//			    	Ext.getCmp('dateExpireDate8501').setValue('');
//		    	}
//		    	//Ext.getCmp('txtLotNo8501').setValue(res[0].lotNo);
//		    	
//		    }
//		});
	},
	
	//7-22添加
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
	
	dateProduceDate8501Change:function(th)
	{
		if(!this.checkUnique8501(th))
		{
			if(!Ext.isEmpty(th.getValue()) && Ext.getCmp('guarantee8501').getValue()!=-1)
	
			{
				Ext.getCmp('dateExpireDate8501').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, Ext.getCmp('guarantee8501').getValue()*1),'Y-m-d'));	
			}	
		}
		//
		if(lotType8501 == '3'){
			if(!Ext.isEmpty(Ext.getCmp('dateProduceDate8501').getValue())){
				var articleNo = Ext.getCmp('cmbArticleNo8501').getValue();
				var produceDate = Ext.getCmp('dateProduceDate8501').getValue();
				var strWheresql={
						articleNo:articleNo,
						produceDate:Ext.Date.format(produceDate,'Y/m/d')
					};
				
					Ext.apply(Ext.getCmp('txtLotNo8501').getStore().proxy.extraParams,strWheresql);
					
					//Ext.getCmp('txtLotNo8501').getStore().removeAll();
					Ext.getCmp('txtLotNo8501').getStore().load();
					Ext.getCmp('dateExpireDate8501').setReadOnly(false);
		    		Ext.getCmp('txtLotNo8501').setReadOnly(false);
		    	/*	Ext.Ajax.request({
					method:'post',
					url:'fcdata_RequestAction_queryLot',
					params:
					{
						strWheresql:combo.getValue()+","+Ext.getCmp('dateProduceDate8501').getValue()
					},
		    		success:function(response)
				    {
				    	var res = Ext.decode(response.responseText);
				    	if(res.length>0){
				    		
				    		
					    	Ext.getCmp('txtLotNo8501').setValue(res[0].lotNo);
					    	Ext.getCmp('dateExpireDate8501').setValue(res[0].expireDate);
				    	}else{
				    		Ext.getCmp('txtLotNo8501').setValue('');
					    	Ext.getCmp('dateExpireDate8501').setValue('');
				    	}
				    }
				});*/
		 }else{
		 Ext.example.msg('提示',"请输入生产日期");
	     }
	}	
	
		
	},
	
	dateExpireDate8501Change:function(th)
	{
		if(!this.checkUnique8501(th))
		{
			if(!Ext.isEmpty(th.getValue()) && Ext.getCmp('guarantee8501').getValue()!=-1)
			{
				Ext.getCmp('dateProduceDate8501').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, Ext.getCmp('guarantee8501').getValue()*-1),'Y-m-d'));
			}
		}
	},
	
	checkUnique8501:function(th){
		var cmbCellNo8501 = Ext.getCmp('cmbCellNo8501').getValue();
		var cmbArticleNo8501 = Ext.getCmp('cmbArticleNo8501').getValue();
		var cmbPackingQty8501 = Ext.getCmp('cmbPackingQty8501').getValue();
		var dateProduceDate8501 = Ext.getCmp('dateProduceDate8501').getValue();
		var dateExpireDate8501 = Ext.getCmp('dateExpireDate8501').getValue();
		var cmblotNo=Ext.getCmp('txtLotNo8501').getValue();
		
		if(!Ext.isEmpty(cmbCellNo8501)
				&&!Ext.isEmpty(cmbArticleNo8501)
					&&!Ext.isEmpty(cmbPackingQty8501)
						&&(!Ext.isEmpty(dateProduceDate8501)
								||!Ext.isEmpty(dateExpireDate8501))
		){
			if(th.fieldLabel==$i18n.lot_no)
			{
				if(Ext.getCmp('grid_02_8501').getStore().findBy(function(record, id) {  
				return (typeof(record.get('expireDate'))=="string"?record.get('expireDate'):Ext.Date.format(new Date(record.get('expireDate')),'Y-m-d'))==
					(typeof(dateExpireDate8501)=="string"?dateExpireDate8501:Ext.Date.format(new Date(dateExpireDate8501),'Y-m-d'))
				&& record.get('cellNo')== cmbCellNo8501
				&& record.get('packingQty')== cmbPackingQty8501
				&& record.get('articleNo')== cmbArticleNo8501
				&& record.get('lotNo')== cmblotNo;
				})!=-1)	
				{
					th.setValue('');
					th.focus();
					Ext.example.msg('提示', "【储位】、【商品内码】、【包装数量】、【生产日期】、【批次】不能重复，请重新输入！");
					return false;
				}
			}else{
				if(Ext.getCmp('grid_02_8501').getStore().findBy(function(record, id) {  
				return (typeof(record.get('produceDate'))=="string"?record.get('produceDate'):Ext.Date.format(new Date(record.get('produceDate')),'Y-m-d'))==
					(typeof(dateProduceDate8501)=="string"?dateProduceDate8501:Ext.Date.format(new Date(dateProduceDate8501),'Y-m-d'))
				&& record.get('cellNo')== cmbCellNo8501
				&& record.get('packingQty')== cmbPackingQty8501
				&& record.get('articleNo')== cmbArticleNo8501;
				})!=-1)	
				{//debugger;
					th.setValue('');
					th.focus();
					Ext.example.msg('提示', "【储位】、【商品内码】、【包装数量】、【生产日期】不能重复，请重新输入！");
					return false;
				}
			}
		}
	},
	
	saveAndAdd8501Click:function()
	{
		debugger;
		if(!CheckIsInputAll('form_02_8501'))
		{
			return;
		}
		/*if(Ext.getCmp('numPoBox8501').getValue()==0 )
		{
			Ext.example.msg('提示','数量不能为0,请重新录入');
			Ext.getCmp('numPoBox8501').focus(false, 10);
			return;
		}*/
		//7-22
		//总数量判断
		var totalQty = Ext.getCmp('numPoBox8501').getValue() + Ext.getCmp('numPoDis8501').getValue()
			+ Ext.getCmp('numPoMin8501').getValue();
		if(totalQty == 0){
			Ext.example.msg('提示','总数不能为0,请重新录入');
			Ext.getCmp('numPoBox8501').focus(false, 10);
			return;
		}
		
		
		if(Ext.getCmp('cmbWheeherWrite8501').getValue()==2)
		{
			g_strSubLabelNo=Ext.getCmp('txtLabelNo8501').getValue();
			if(Ext.isEmpty(Ext.getCmp('txtLabelNo8501').getValue()) || Ext.getCmp('txtLabelNo8501').getValue()=='N')
			{
				Ext.example.msg('提示','主标签号不能为N');
				return;
			}
		}else if(Ext.getCmp('cmbWheeherWrite8501').getValue()==3)
		{
			g_strSubLabelNo=Ext.getCmp('txtSubLabelNo8501').getValue();
			if((Ext.isEmpty(Ext.getCmp('txtLabelNo8501').getValue())|| Ext.getCmp('txtLabelNo8501').getValue()=='N')
					||(Ext.isEmpty(Ext.getCmp('txtSubLabelNo8501').getValue())|| Ext.getCmp('txtSubLabelNo8501').getValue()=='N'))
			{
				Ext.example.msg('提示','主标签号或子标签号不能为N');
				return;
			}
		}
		
		//var intSubOrderId=Ext.getCmp('grid_02_8501').getStore().query('cellNo',Ext.getCmp('cmbCellNo8501').getValue()).length+1;
		var msgShow = commonMegShow("正在保存,请稍等...");
		var objData = Ext.getCmp('grid_01_8501').getSelectionModel().getSelection()[0];
		var detail1=[];
		var poD={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:objData.data.warehouseNo,//record.get('warehouseNo'),
				checkNo:objData.data.checkNo,//record.get('checkNo'),
				rowId:i//record.get('rowId')
			},
			ownerNo:Ext.getCmp('cmbOwnerNo8501').getValue(),
			checkType:objData.data.checkType,
			checkWorker:Ext.getCmp('cmbWorkerNo8501').getValue(),
			cellNo:Ext.getCmp('cmbCellNo8501').getValue(),
			articleNo:Ext.getCmp('cmbArticleNo8501').getValue(),
			packingQty:Ext.getCmp('cmbPackingQty8501').getValue(),
			produceDate:Ext.getCmp('dateProduceDate8501').getValue(),
			expireDate:Ext.getCmp('dateExpireDate8501').getValue(),
			quality:Ext.getCmp('cmbQuality8501').getValue(),
			lotNo:Ext.getCmp('txtLotNo8501').getValue(),
			//checkQty:Ext.getCmp('cmbPackingQty8501').getValue()*Ext.getCmp('numPoBox8501').getValue(),
			//7-19修改
			checkQty:Ext.getCmp('cmbPackingQty8501').getValue()*Ext.getCmp('numPoBox8501').getValue()
				+Ext.getCmp('numPoMin8501').getValue()*Ext.getCmp('minPackingQty8501').getValue()
				+Ext.getCmp('numPoDis8501').getValue(),
			
			labelNo:Ext.getCmp('txtLabelNo8501').getValue(),
			subLabelNo:g_strSubLabelNo,
			stockType:'1',
			addFlag:1,
			stockValue:'N',
			rsvBatch1:'N',
			rsvBatch2:'N',
			rsvBatch3:'N',
			rsvBatch4:'N',
			rsvBatch5:'N',
			rsvBatch6:'N',
			rsvBatch7:'N',
			rsvBatch8:'N'
		};
		detail1.push(poD);
		var strDetail=Ext.encode(detail1);
		
		var params=
		{
			strDetail:strDetail
		};
		Ext.Ajax.request({
			method:'post',
			url:'fcdata_RequestAction_saveFcdata_CheckD',
			params:params,
			success:function(response){
				msgShow.hide();
				var data = Ext.decode(response.responseText);
				if(data.isSucc)
				{
					Ext.getCmp('form_02_8501').getForm().reset();
					if(Ext.getCmp('rdoCheckType8501').getValue().rd==2){//复盘
						Ext.getCmp('grid_02_8501').store.reload({
							callback:function(){
								var gridcount=Ext.getCmp('grid_02_8501').getStore().getCount();
						   		if(gridcount>0){
						   			for(var i=0;i<gridcount;i++){
						   				var data2 = Ext.getCmp('grid_02_8501').getStore().getAt(i);
						   				if(data2.get('rowId')==data.obj){
						   					Ext.getCmp('grid_02_8501').getSelectionModel().select(i);
						   				}
						   			}
						        }
						     }
					    });
					}else if(Ext.getCmp('rdoCheckType8501').getValue().rd==3){//三盘
						Ext.getCmp('grid_03_8501').store.reload({
							callback:function(){
								var gridcount=Ext.getCmp('grid_03_8501').getStore().getCount();
						   		if(gridcount>0){
						   			for(var i=0;i<gridcount;i++){
						   				var data2 = Ext.getCmp('grid_03_8501').getStore().getAt(i);
						   				if(data2.get('rowId')==data.obj){
						   					Ext.getCmp('grid_03_8501').getSelectionModel().select(i);
						   				}
						   			}
						        }
						     }
					    });
					}
					Ext.getCmp('cmbWheeherWrite8501').setValue('1');
					Ext.getCmp('cmbQuality8501').setValue('0');
					Ext.getCmp('cmbCellNo8501').focus(false, 10);		
					g_strSubLabelNo='N';
				}else
				{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	},
	
	cmbWheeherWrite8501Select:function(combo)
	{
		if(combo.getValue()==1)
		{
			Ext.getCmp('txtLabelNo8501').setReadOnly(true);
			Ext.getCmp('txtSubLabelNo8501').setReadOnly(true);
			Ext.getCmp('txtLabelNo8501').setValue('N');
			Ext.getCmp('txtSubLabelNo8501').setValue('N');
		}else if(combo.getValue()==2)
		{
			Ext.getCmp('txtLabelNo8501').setReadOnly(false);
			Ext.getCmp('txtSubLabelNo8501').setReadOnly(true);
			Ext.getCmp('txtLabelNo8501').setValue('');
			Ext.getCmp('txtLabelNo8501').focus(false, 10);
		}else if(combo.getValue()==3)
		{
			Ext.getCmp('txtLabelNo8501').setReadOnly(false);
			Ext.getCmp('txtSubLabelNo8501').setReadOnly(false);
			Ext.getCmp('txtLabelNo8501').setValue('');
			Ext.getCmp('txtSubLabelNo8501').setValue('');
			Ext.getCmp('txtLabelNo8501').focus(false, 10);
		}
	},
	
	txtLabelNo8501Blur:function()
	{
		if(Ext.getCmp('cmbWheeherWrite8501').getValue()=='2')
		{
			if(Ext.isEmpty(Ext.getCmp('cmbCellNo8501').getValue()))
			{
				Ext.getCmp('cmbCellNo8501').focus(false, 10);
				Ext.getCmp('txtLabelNo8501').setValue('N');
				Ext.example.msg('提示','请先输入储位');
			}else
			{
				
				var params=
				{
					strCellNo:Ext.getCmp('cmbCellNo8501').getValue(),
					strLabelNo:Ext.getCmp('txtLabelNo8501').getValue()
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
							Ext.getCmp('txtLabelNo8501').setValue('');
							Ext.getCmp('txtLabelNo8501').focus(false, 10);
						}else{
							Ext.getCmp('txtSubLabelNo8501').focus(false, 10);
						}
					}
				});
			}
		}else if(Ext.getCmp('cmbWheeherWrite8501').getValue()=='3')
		{
			if(Ext.isEmpty(Ext.getCmp('cmbCellNo8501').getValue()))
			{
				Ext.getCmp('cmbCellNo8501').focus(false, 10);
				Ext.getCmp('txtLabelNo8501').setValue('N');
				Ext.getCmp('txtSubLabelNo8501').setValue('N');
				Ext.example.msg('提示','请先输入储位');
			}else
			{
				
				var params=
				{
					strCellNo:Ext.getCmp('cmbCellNo8501').getValue(),
					strLabelNo:Ext.getCmp('txtLabelNo8501').getValue(),
					strSubLabelNo:Ext.getCmp('txtSubLabelNo8501').getValue()
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
							Ext.getCmp('txtLabelNo8501').setValue('');
							Ext.getCmp('txtSubLabelNo8501').setValue('');
							Ext.getCmp('txtLabelNo8501').focus(false, 10);
						}else{
							Ext.getCmp('txtSubLabelNo8501').focus(false, 10);
						}
					}
				});
			}
		}
	},
	
	txtSubLabelNo8501Blur:function()
	{
		if(Ext.getCmp('cmbWheeherWrite8501').getValue()=='3')
		{
			if(Ext.isEmpty(Ext.getCmp('cmbCellNo8501').getValue()))
			{
				Ext.getCmp('cmbCellNo8501').focus(false, 10);
				Ext.getCmp('txtLabelNo8501').setValue('N');
				Ext.getCmp('txtSubLabelNo8501').setValue('N');
				Ext.example.msg('提示','请先输入储位');
			}else
			{
				
				var params=
				{
					strCellNo:Ext.getCmp('cmbCellNo8501').getValue(),
					strLabelNo:Ext.getCmp('txtLabelNo8501').getValue(),
					strSubLabelNo:Ext.getCmp('txtSubLabelNo8501').getValue()
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
							Ext.getCmp('txtSubLabelNo8501').setValue('');
							Ext.getCmp('txtSubLabelNo8501').focus(false, 10);
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
			if(th.id=='cmbArticleNo8501'){
				Ext.getCmp('cmbPackingQty8501').focus();
			}else if(th.id=='cmbPackingQty8501'){
				Ext.getCmp('numPoBox8501').focus();
			}else if(th.id=='txtSubLabelNo8501'){
				Ext.getCmp('dateProduceDate8501').focus();
			}else{
				th.nextSibling().focus();
			}
		}
	}
});

/**
 * 设置批属性是否显示
 */
function queryBatchDisplay8501(field)
{
	var fields=[];
	fields=field.split(',');
	Ext.getCmp('dateProduceDate8501').setVisible(false);
	Ext.getCmp('dateExpireDate8501').setVisible(false);
	Ext.getCmp('cmbQuality8501').setVisible(false);
	Ext.getCmp('txtLotNo8501').setVisible(false);
	Ext.getCmp('txtRsvBatch18501').setVisible(false);
	Ext.getCmp('txtRsvBatch28501').setVisible(false);
	Ext.getCmp('txtRsvBatch38501').setVisible(false);
	Ext.getCmp('txtRsvBatch48501').setVisible(false);
	Ext.getCmp('txtRsvBatch58501').setVisible(false);
	Ext.getCmp('txtRsvBatch68501').setVisible(false);
	Ext.getCmp('txtRsvBatch78501').setVisible(false);
	Ext.getCmp('txtRsvBatch88501').setVisible(false);
	for (var i=0;i<fields.length;i++)
	{
		if(fields[i]=='PRODUCE_DATE')
		{
			Ext.getCmp('dateProduceDate8501').setVisible(true);
		}else if(fields[i]=='EXPIRE_DATE')
		{
			Ext.getCmp('dateExpireDate8501').setVisible(true);
		}else if(fields[i]=='QUALITY')
		{
			Ext.getCmp('cmbQuality8501').setVisible(true);
		}else if(fields[i]=='LOT_NO')
		{
			Ext.getCmp('txtLotNo8501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH1')
		{
			Ext.getCmp('txtRsvBatch18501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH2')
		{
			Ext.getCmp('txtRsvBatch28501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH3')
		{
			Ext.getCmp('txtRsvBatch38501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH4')
		{
			Ext.getCmp('txtRsvBatch48501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH5')
		{
			Ext.getCmp('txtRsvBatch58501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH6')
		{
			Ext.getCmp('txtRsvBatch68501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH7')
		{
			Ext.getCmp('txtRsvBatch78501').setVisible(true);
		}else if(fields[i]=='RSV_BATCH8')
		{
			Ext.getCmp('txtRsvBatch88501').setVisible(true);
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