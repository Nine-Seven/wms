/**
 * 模块名称：仓别出货策略配置
 * 模块代码：3920
 * @author hekl
 */
var g_intRowIndex3920=0;
var g_isCanEdit3920=false;
Ext.define('cms.controller.odata.odata_WasehouseTacticsConfigureController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_WasehouseTacticsConfigureUI'
	],
	init:function(){
		this.control({
			//点击新单
			'odata_WasehouseTacticsConfigureUI button[name=userAddButton]':{
				click:this.userAddButtonClick
			},
			//保存
			'odata_WasehouseTacticsConfigureUI button[name=userSaveButton]':{
				click:this.userSaveButtonClick
			},
			 //双击进入出货明细
			'odata_WasehouseTacticsConfigureUI grid[id=grid_01_3920]':{
				itemdblclick:this.grid_01_3920Dblclick
			},
			//选择波次规则号，带出波规则信息
	    	'odata_WasehouseTacticsConfigureUI form combo[id=cmbstrategyId3920]':{
				select:this.cmbstrategyId3920select,
			},
			 //导航栏的切换
			'odata_WasehouseTacticsConfigureUI tabpanel[id=tabPId3920]':{
				tabchange:this.tabPidchange
			},
			//出货单别的判断
			'odata_WasehouseTacticsConfigureUI wms_DefFieldValCombo[id=cmbExpType3920]':{
				select:this.cmbExpType3920Select
			},
			//编辑
			'odata_WasehouseTacticsConfigureUI button[name=userEditButton]':{
				click:this.userEditButtonClick
			},
			//撤退
			'odata_WasehouseTacticsConfigureUI button[name=userUndoButton]':{
				click:this.userUndoButtonClick
			},
			//上一条信息
			'odata_WasehouseTacticsConfigureUI button[name=userPrevButton]':{
				click:this.userPrevButtonClick
			},
			//下一条信息
			'odata_WasehouseTacticsConfigureUI button[name=userNextButton]':{
				click:this.userNextButtonClick
			}
		});
	},
	
	//点击新单
	userAddButtonClick:function(){
		addTactics3920();
		commonEditButton('menu3920','add');
		commonSetMsterReadOnly('form_01_3920',false);
		commonSetMsterReadOnlyByArray(
				new Array('owner_no3920','cmbExpType3920','cmbPriority3920','cmbDeliverObjLevel3920'),false);
		Ext.getCmp('grid_02_3920').getStore().removeAll();
	},
	
	//保存
	userSaveButtonClick:function()
	{   
		if(!commonCheckIsInputAll('tabPId3920_T2')){
			return;
		}	
		var record = Ext.getCmp('grid_02_3920').getStore().query('flag',true);;
        if (record.length == 0) {Ext.example.msg("提示","请选择工作流!");
	        return;
        }
        else {
			saveExpType3920();
			saveTactics3920();
			commonEditButton('menu3920','save');
		}
	},
	
	//双击进入出货明细
	grid_01_3920Dblclick:function(){
		Ext.getCmp('tabPId3920').items.items[1].setVisible(true);
		//isCanEdit3101=false;
		commonEditButton('menu3920','dbclick');
	},
	
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts){
		if(newCard.id=='tabPId3920_T2'){
			var data = Ext.getCmp('grid_01_3920').getSelectionModel().getSelection();
			if(data.length!=0){
				loadTactics3920(data[0].index);
				g_intRowIndex3920=data[0].index;
				
				//isCanEdit3101=false;
				var wheresql={
					strExpType:data[0].data.expType,
					ownerNo:data[0].data.ownerNo,
					strType:'0'
				};
				Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('grid_02_3920').getStore().removeAll();
				Ext.getCmp('grid_02_3920').getStore().load();
				commonEditButton('menu3920','dbclick');
			}
		}
	},
	
	//出货单别的判断
	cmbExpType3920Select:function(combo){
		Ext.Ajax.request({
			url:'odata_TacticsAction_checkExpType',
			params : {
				strExpType:combo.getValue(),
				ownerNo:Ext.getCmp('owner_no3920').getValue(),
				wheresql:2
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('cmbExpType3920').setValue(null);
				}
			}
		});
		
		//加载波次规则号
		var wheresql={
				strExpType:Ext.getCmp('cmbExpType3920').getValue()
		};
		Ext.apply(Ext.getCmp('cmbstrategyId3920').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbstrategyId3920').getStore().removeAll();
		Ext.getCmp('cmbstrategyId3920').getStore().load();
		
	},
	
	//选择波次规则号，带出波次规则信息
	cmbstrategyId3920select:function(combo,records,eOpts){
		Ext.getCmp("strategyName3920").setValue(records[0].data.strategyName);
		Ext.getCmp("betweenTimes3920").setValue(records[0].data.betweenTimes);	
		commonSetMsterReadOnlyByArray(
				new Array('strategyName3920','betweenTimes3920'),true);

	},
	
	//修改
	userEditButtonClick:function(){
		//isCanEdit3101=true;
		commonEditButton('menu3920','edit');
		commonSetMsterReadOnly('form_01_3920',false);
		commonSetMsterReadOnlyByArray(
				new Array('owner_no3920','cmbExpType3920','cmbPriority3920','cmbDeliverObjLevel3920','strategyName3920','betweenTimes3920'),true);
	},
	//撤退
	userUndoButtonClick:function(){
		var data = Ext.getCmp('grid_01_3920').getSelectionModel().getSelection();
		if(data.length!=0){
			loadTactics3920(data[0].index);
			g_intRowIndex3920=data[0].index;
			
			var wheresql={
				strExpType:data[0].data.expType,
				ownerNo:data[0].data.ownerNo,
				strType:'0'
			};
			Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_3920').getStore().removeAll();
			Ext.getCmp('grid_02_3920').getStore().load();
		}
	
		commonSetMsterReadOnly('form_01_3920',true);
		commonSetMsterReadOnlyByArray(
				new Array('owner_no3920','cmbExpType3920','cmbPriority3920','cmbDeliverObjLevel3920','strategyName3920','betweenTimes3920'),true);
		commonEditButton('menu3920','undo');
       
	},
	
	//上一条信息
	userPrevButtonClick:function(){
		g_intRowIndex3920=g_intRowIndex3920-1;
		loadTactics3920(g_intRowIndex3920);
	},
	
	//下一条信息
	userNextButtonClick:function(){
		g_intRowIndex3920=g_intRowIndex3920+1;
		loadTactics3920(g_intRowIndex3920);
	},
	

	
});

function saveExpType3920(){
	if(Ext.getCmp('form_01_3920').getForm().isValid()){
			var detail={
				id : {
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.getCmp('cmbwarehouseNo3920').getValue(),
					ownerNo:Ext.getCmp('owner_no3920').getValue(),
					expType:Ext.getCmp('cmbExpType3920').getValue()	
				},
				
				priority:Ext.getCmp('cmbPriority3920').getValue(),
				deliverObjLevel:Ext.getCmp('cmbDeliverObjLevel3920').getValue(),
				MFlag:Ext.getCmp('cmbMflag3920').getValue(),
				WFlag:Ext.getCmp('cmbWflag3920').getValue(),
				SFlag:Ext.getCmp('cmbSflag3920').getValue(),
				DFlag:Ext.getCmp('cmbDflag3920').getValue(),
				BDivideFlag:Ext.getCmp('cmbBdivideFlag3920').getValue(),
				CDivideFlag:Ext.getCmp('cmbCdivideFlag3920').getValue(),
				recheckCompute:Ext.getCmp('cmbRecheckCompute3920').getValue(),
				sendbufCompute:Ext.getCmp('cmbSendbufCompute3920').getValue(),
				commitType:Ext.getCmp('cmbCommitType3920').getValue(),
				shortqtyType:Ext.getCmp('cmbShortqtyType3920').getValue(),
				sorterCompute:Ext.getCmp('cmbSorterCompute3920').getValue(),
				batchCompute:Ext.getCmp('cmbBatchCompute3920').getValue(),
				lineFlag:Ext.getCmp('cmbLineFlag3920').getValue(),
				PFlag:Ext.getCmp('pFlag_3920').getValue(),
				batchComputeType:Ext.getCmp('batchComputeType_3920').getValue(),
				sendComputeLevel:Ext.getCmp('sendComputeLevel_3920').getValue(),
				BWorkFlowFlag:Ext.getCmp('bWorkFlowFlag_3920').getValue(),
				CWorkFlowFlag:Ext.getCmp('cWorkFlowFlag_3920').getValue(),
				autodivideflag:Ext.getCmp('autodivideflag_3920').getValue(),
				autooutstockflag:Ext.getCmp('autooutstockflag_3920').getValue(),
				orderRsv01:Ext.getCmp('txtOrderRsv01_3920').getValue(),
				orderRsv02:Ext.getCmp('txtOrderRsv02_3920').getValue(),
				orderRsv03:Ext.getCmp('txtOrderRsv03_3920').getValue(),
				orderRsv04:Ext.getCmp('txtOrderRsv04_3920').getValue(),
				orderRsv05:Ext.getCmp('txtOrderRsv05_3920').getValue(),
				orderRsv06:Ext.getCmp('txtOrderRsv06_3920').getValue(),
				orderRsv07:Ext.getCmp('txtOrderRsv07_3920').getValue(),
				orderRsv08:Ext.getCmp('txtOrderRsv08_3920').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				abnormalFlag:Ext.getCmp('cmbAbnormalFlag3920').getValue(),
				strategyId:Ext.getCmp('cmbstrategyId3920').getValue()

			};
			var strJsonDetail=Ext.encode(detail);
			Ext.Ajax.request({
				url:'odata_TacticsAction_saveOrUpdateWasehouseTactics.action',
				method:'post',
				params:{
					strJsonDetail:strJsonDetail
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_01_3920').getStore().load();
						var wheresql={
							strExpType:Ext.getCmp('cmbExpType3920').getValue(),
							ownerNo:Ext.getCmp('owner_no3920').getValue(),
							strType:'0'
						};
						Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
						Ext.getCmp('grid_02_3920').getStore().removeAll();
						Ext.getCmp('grid_02_3920').getStore().load();
						Ext.example.msg($i18n.prompt,data.msg);
					}else{
						Ext.Msg.alert($i18n.prompt,data.msg);

						//Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}
};

function saveTactics3920(){
	 //判断是否有勾选工作流
		var detail1=[];
		//全部的
		var gridcount=Ext.getCmp('grid_02_3920').getStore().getCount();
		for(var i=0;i<gridcount;i++){
			var exp=Ext.getCmp('grid_02_3920').getStore().getAt(i);
			var d={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.getCmp('cmbwarehouseNo3920').getValue(),
					expType:Ext.getCmp('cmbExpType3920').getValue(),
					flowOrder:i,
					ownerNo:Ext.getCmp('owner_no3920').getValue(),
					
				},
				flowValue:exp.get('flowValue'),
				flowFlag:exp.get('flag')?'1':'0',
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date()
			};
			detail1.push(d);
		}
		if(detail1.length!=0){
			var jsonDetail1 = Ext.encode(detail1);			
			Ext.Ajax.request({
				url:'odata_TacticsAction_saveWasehouseTactics',
				params : {
					strJsonDetail:jsonDetail1
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						//Ext.example.msg($i18n.prompt,data.msg);
						var wheresql={
							strExpType:Ext.getCmp('cmbExpType3920').getValue(),
							ownerNo:Ext.getCmp('owner_no3920').getValue(),
							strType:'0'
						};
						Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
						Ext.getCmp('grid_02_3920').getStore().removeAll();
						Ext.getCmp('grid_02_3920').getStore().load();
					}else{
						//Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}else{
			Ext.Ajax.request({
				url:'odata_TacticsAction_deleteTactics',
				params : {
					strExpType:data[0].get('expType')
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						//Ext.example.msg($i18n.prompt,data.msg);
						var wheresql={
							strExpType:Ext.getCmp('cmbExpType3920').getValue(),
							ownerNo:Ext.getCmp('owner_no3920').getValue(),
							strType:'0'
						};
						Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
						Ext.getCmp('grid_02_3920').getStore().removeAll();
						Ext.getCmp('grid_02_3920').getStore().load();
					}else{
						//Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}
}

function addTactics3920(){
	Ext.getCmp('cmbwarehouseNo3920').setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('cmbExpType3920').setValue(null);
	Ext.getCmp('cmbPriority3920').setValue(null);
	Ext.getCmp('cmbDeliverObjLevel3920').setValue('1');
	
	Ext.getCmp('cmbMflag3920').setValue('1');
	Ext.getCmp('cmbWflag3920').setValue('1');
	Ext.getCmp('cmbSflag3920').setValue('1');
	Ext.getCmp('cmbDflag3920').setValue('1');
	
	Ext.getCmp('cmbBdivideFlag3920').setValue('1');
	Ext.getCmp('cmbCdivideFlag3920').setValue('1');
	Ext.getCmp('cmbSendbufCompute3920').setValue('1');
	Ext.getCmp('cmbCommitType3920').setValue('1');
	Ext.getCmp('cmbShortqtyType3920').setValue('1');
	Ext.getCmp('cmbLineFlag3920').setValue('1');
	Ext.getCmp('cmbAbnormalFlag3920').setValue('1');
	
	Ext.getCmp('cmbRecheckCompute3920').setValue('1');
	Ext.getCmp('cmbSorterCompute3920').setValue('1');
	Ext.getCmp('cmbBatchCompute3920').setValue('1');
	
	Ext.getCmp('pFlag_3920').setValue('1');
	Ext.getCmp('batchComputeType_3920').setValue('0');
	Ext.getCmp('sendComputeLevel_3920').setValue('0');
	
	//加载工作流
	var wheresql={
			strType:1
	};
	Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_3920').getStore().removeAll();
	Ext.getCmp('grid_02_3920').getStore().load();
	
	
};

function loadTactics3920(g_intRowIndex3920)
{
	/*var article=Ext.getCmp('grid_01_1401').getStore().getAt(
			g_intRowIndex1401-(Ext.getCmp('grid_01_1401').getStore().currentPage-1)*appConfig.getPageSize());*/
	if(g_intRowIndex3920==0)
	{
		Ext.getCmp('menu3920').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu3920').items.items[0].enable(true);
	}
	if(g_intRowIndex3920==Ext.getCmp('grid_01_3920').getStore().getCount()-1)
	{		
		Ext.getCmp('menu3920').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu3920').items.items[1].enable(true);
	}
	
	    var record=Ext.getCmp('grid_01_3920').getStore().getAt(g_intRowIndex3920-(Ext.getCmp('grid_01_3920').getStore().currentPage-1)*appConfig.getPageSize());
	    Ext.getCmp('cmbwarehouseNo3920').setValue(record.data.warehouseNo);
	    Ext.getCmp('owner_no3920').setValue(record.data.ownerNo);
	    Ext.getCmp('cmbExpType3920').setValue(record.data.expType);
		Ext.getCmp('cmbPriority3920').setValue(String(record.data.priority));
		Ext.getCmp('cmbDeliverObjLevel3920').setValue(record.data.deliverObjLevel);
		
		Ext.getCmp('cmbMflag3920').setValue(record.data.MFlag);
		Ext.getCmp('cmbWflag3920').setValue(record.data.WFlag);
		Ext.getCmp('cmbSflag3920').setValue(record.data.SFlag);
		Ext.getCmp('cmbDflag3920').setValue(record.data.DFlag);
		
		Ext.getCmp('cmbBdivideFlag3920').setValue(record.data.BDivideFlag);
		Ext.getCmp('cmbCdivideFlag3920').setValue(record.data.CDivideFlag);
		Ext.getCmp('cmbSendbufCompute3920').setValue(record.data.sendbufCompute);
		Ext.getCmp('cmbCommitType3920').setValue(record.data.commitType);
		Ext.getCmp('cmbShortqtyType3920').setValue(record.data.shortqtyType);
		Ext.getCmp('cmbLineFlag3920').setValue(record.data.lineFlag);
		Ext.getCmp('cmbAbnormalFlag3920').setValue(record.data.abnormalFlag);
		
		Ext.getCmp('cmbRecheckCompute3920').setValue(record.data.recheckCompute);
		Ext.getCmp('cmbSorterCompute3920').setValue(record.data.sorterCompute);
		Ext.getCmp('cmbBatchCompute3920').setValue(record.data.batchCompute);
		
		//加载波次规则号
		var wheresql={
				strExpType:Ext.getCmp('cmbExpType3920').getValue()
		};
		Ext.apply(Ext.getCmp('cmbstrategyId3920').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbstrategyId3920').getStore().removeAll();
		Ext.getCmp('cmbstrategyId3920').getStore().load();
		
		Ext.getCmp('cmbstrategyId3920').setValue(record.data.dropValue);
		Ext.getCmp('strategyName3920').setValue(record.data.strategyName);
		Ext.getCmp('betweenTimes3920').setValue(record.data.betweenTimes);
		
		Ext.getCmp('pFlag_3920').setValue(record.data.PFlag);
		Ext.getCmp('batchComputeType_3920').setValue(record.data.batchComputeType);
		Ext.getCmp('sendComputeLevel_3920').setValue(record.data.sendComputeLevel);
		Ext.getCmp('bWorkFlowFlag_3920').setValue(record.data.BWorkFlowFlag);
		Ext.getCmp('cWorkFlowFlag_3920').setValue(record.data.CWorkFlowFlag);
		Ext.getCmp('autodivideflag_3920').setValue(record.data.autodivideflag);
		Ext.getCmp('autooutstockflag_3920').setValue(record.data.autooutstockflag);
		
		Ext.getCmp('txtOrderRsv01_3920').setValue(record.data.orderRsv01);
		Ext.getCmp('txtOrderRsv02_3920').setValue(record.data.orderRsv02);
		Ext.getCmp('txtOrderRsv03_3920').setValue(record.data.orderRsv03);
		Ext.getCmp('txtOrderRsv04_3920').setValue(record.data.orderRsv04);
		Ext.getCmp('txtOrderRsv05_3920').setValue(record.data.orderRsv05);
		Ext.getCmp('txtOrderRsv06_3920').setValue(record.data.orderRsv06);
		Ext.getCmp('txtOrderRsv07_3920').setValue(record.data.orderRsv07);
		Ext.getCmp('txtOrderRsv08_3920').setValue(record.data.orderRsv08);
		commonSetMsterReadOnlyByArray(
				new Array('cmbExpType3920','strategyName3920','betweenTimes3920'),true);
	
	var wheresql={
		strExpType:Ext.getCmp('cmbExpType3920').getValue(),
		ownerNo:Ext.getCmp('owner_no3920').getValue(),
		strType:'0'
	};
	Ext.apply(Ext.getCmp('grid_02_3920').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_3920').getStore().removeAll();
	Ext.getCmp('grid_02_3920').getStore().load();
	
}
