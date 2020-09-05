/**
 * 模块名称：波次计划规则
 * 模块编码：3914
 */
 var rowindex3914=0;
 var flag='add';
 var selectFlag='';  //7-15
 var ruleIdFlag='';  //7-19 
 var editFlag='';	 //7-19     控制试算配置表格的编辑状态

 Ext.define('cms.controller.odata.odata_WmsWavePlanController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.odata.odata_WmsWavePlanUI',
 	'cms.view.odata.window.odata_WmsWavePlanMAddOrEditWindow'
 	],
 	init:function(){
 		 this.control(
 		    //新增波次计划规则头档(UI)
 		    {
	    	'wms_warvePlanUI commMenuWidget2[id=menu3914] button[name=detailAdd]':{
				click:this.detailAdd
			},
			//波次计划规则头档
			'odata_WmsWavePlanMAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//新增波次计划规则头档(window)
			'odata_WmsWavePlanMAddOrEditWindow button[name=add]':{
				click:this.windowAdd
			},
			//关闭窗口
			'odata_WmsWavePlanMAddOrEditWindow button[name=close]':{
				click:this.close
			},
			//修改波次计划规则头档
	    	'wms_warvePlanUI commMenuWidget2[id=menu3914] button[name=detailEdit]':{
	    		click:this.detailEdit
	    	},
	    	//上一条
			'odata_WmsWavePlanMAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条
			'odata_WmsWavePlanMAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//浏览波次计划规则头档
			'wms_warvePlanUI commMenuWidget2[id=menu3914] button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//删除
			'wms_warvePlanUI commMenuWidget2[id=menu3914] button[name=detailDelete]':{
				click:this.detailDelete
			},
		
			//查找
			'wms_warvePlanUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			
			//选择一条头档，加载明细
			'wms_warvePlanUI grid[id=wmsWavePlanM3914]':{
				selectionchange:this.selectPlanM
			},
			
			//规则id选择前加载  6-22
			'wms_DefFieldValCombo[id=valueFlag3914]':{
				focus:this.valueFlag3914Focus,
				select:this.seletcRuleId
			},
			
			//新增明细
	    	'wms_warvePlanUI commMenuWidget6[id=menu_3914_2] button[name=detailAdd]':{
	    		click:this.detailAddPlanD
	    	},
	    	
	    	//选择明细，显示明细信息
	    	'wms_warvePlanUI grid[id=wmsWavePlanD3914]':{
				selectionchange:this.selectPlanD
			},
			
			//选择试算配置名称，显示详细信息   6-24
	    	'wms_warvePlanUI grid[id=wmsWavePlanD23914]':{
				selectionchange:this.selectPlanDTrial
			},
	    	
	    	//修改明细
	    	'wms_warvePlanUI commMenuWidget6[id=menu_3914_2] button[name=detailEdit]':{
	    		click:this.detailEditPlanD
	    	},	    	
	    	//保存明细
	    	'wms_warvePlanUI commMenuWidget6[id=menu_3914_2] button[name=detailSave]':{
	    		click:this.savePlanD
	    	},	    	
			//删除明细
			'wms_warvePlanUI commMenuWidget6[id=menu_3914_2] button[name=detailDelete]':{
				click:this.deletePlanD
			},
			//明细顺序向上
			'wms_warvePlanUI button[id=top3914]':{
				click:this.top
			},
			//明细顺序向下
			'wms_warvePlanUI button[id=down3914]':{
				click:this.down
			},	    	
	    	//撤销
	    	'wms_warvePlanUI commMenuWidget6[id=menu_3914_2] button[name=detailUndo]':{
	    		click:this.undoPlanD
	    	}

			//强制摘果控制  Add by sunl 2016年7月30日
			,'wms_DefFieldValCombo[id=bDivideFlag3914]':{ 
				select:this.selectbDivideFlag
			}
			
	    });
 	},
 	 //初始化界面
 	initializtion:function(){
 		//隐藏配置详细信息的所有字段列
 		Ext.getCmp('wmsWaveTrialDetail3914').columns[1].hide();	
 		Ext.getCmp('wmsWaveTrialDetail3914').columns[2].hide();	
 		Ext.getCmp('wmsWaveTrialDetail3914').columns[3].hide();	
 		Ext.getCmp('wmsWaveTrialDetail3914').columns[4].hide();	
 		Ext.getCmp('wmsWaveTrialDetail3914').columns[5].hide();	
 		
 		commonSetMsterReadOnlyByArray(
				new Array('batchRuleId3914','batchRuleName3914','skuLimmit3914','taskOrder3914',
						  'waitTimes3914','status3914','ruleFlag3914',
						  'shipperNo3914','orderSource3914','itemTypeFlag3914',
						  'areaLimmit3914','cLimmit3914','deliverType3914',     
						  'printType3914','batchComputeType3914','batchCompute3914',
						  'printEnvoice3914','printWayBill3914','printPackList3914','intervalTimes3914',
						  'skuCount3914','printValue1','printValue2','skuCountMode3914',
						  'rsvControl1','rsvControl2','rsvControl3','rsvControl4','rsvControl5',
						  'rsvValue1','rsvValue2','rsvValue3','rsvValue4','rsvValue5'
						  ,'deliverAddress3914','repeatTimes3914','minOrder3914','lastPickFlag3914',
						  'cDivideFlag3914','bDivideFlag3914','areaLimmitValue3914','areaAllow3914'),true);
 		
 		//7-19添加
 		editFlag = '1';
 		
 		disableButtonFunc(1,'#menu_3914_2 [name=detailSave]','保存');
 		disableButtonFunc(1,'#menu_3914_2 [name=detailUndo]','撤销');
	},
	
	selectbDivideFlag:function(){
		//强制摘果控制  Add by sunl 2016年7月30日
		var bDivideFlagTest = Ext.getCmp('bDivideFlag3914').getValue();
		if( bDivideFlagTest!='0'){
			var AskuCountModeTest = Ext.getCmp('skuCountMode3914').getValue();
			if ( AskuCountModeTest == "2") 
			{
				Ext.getCmp('lastPickFlag3914').setValue('N');
				Ext.getCmp('lastPickFlag3914').disable();
			}
			else
			{
				Ext.getCmp('lastPickFlag3914').enable();
			}
		}
		else
		{
			Ext.getCmp('lastPickFlag3914').enable();
		}
	},
 	//新增波次计划规则头档
	detailAdd:function(){
		Ext.create('cms.view.odata.window.odata_WmsWavePlanMAddOrEditWindow',{
			title:$i18n.titleadd
		}).show();
	
		Ext.getCmp('strategyId3914').setValue('保存时自动生成');
		addCommMenu5('menuWidgetPlanM3914');
	},
 	
 	//保存头档
 	save:function(th){
		saveWmsWavePlanM();
	},
 	
	//新增波次计划规则头档(window)
	windowAdd:function(){
 		Ext.getCmp('odata_WmsWavePlanMAddOrEditWindow3914').getForm().reset();
 		Ext.getCmp('strategyId3914').setValue('保存时自动生成');
 	},
 	
	//关闭窗口
 	close:function(th){
 		Ext.getCmp('odata_WmsWavePlanMAddOrEditWindow').close();
	},
	
	//修改波次计划规则头档
	detailEdit:function()
	{	
		var objdata = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
			if (objdata.length != 0) {					
				Ext.create('cms.view.odata.window.odata_WmsWavePlanMAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show(); 
				rowindex3914=objdata[0].index;
				
				loadData(rowindex3914);	
				commonSetMsterReadOnlyByArray(
						new Array('strategyId3914','expType3914'),true);
				commonSetCommMenu5PrevOrNext('menuWidgetPlanM3914','wmsWavePlanM3914',rowindex3914);
				updateCommMenu5('menuWidgetPlanM3914');	
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
	},
	
	//上一条
	prev:function()
	{
		rowindex3914=rowindex3914-1;
		loadData(rowindex3914);
		commonSetCommMenu5PrevOrNext('menuWidgetPlanM3914','wmsWavePlanM3914',rowindex3914);
	},
 	
	// 下一条保拣线记录
	next:function(){
		rowindex3914=rowindex3914+1;
		loadData(rowindex3914);
		commonSetCommMenu5PrevOrNext('menuWidgetPlanM3914','wmsWavePlanM3914',rowindex3914);
	},
	
	// 浏览波次计划规则
	detailBrowse:function(){			
		var data = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.create('cms.view.odata.window.odata_WmsWavePlanMAddOrEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
				rowindex3914=data[0].index;
				loadData(rowindex3914);	
				
				commonSetMsterReadOnlyByArray(
						new Array('strategyId3914','strategyName3914'),true);				
				commonSetCommMenu5PrevOrNext('menuWidgetPlanM3914','wmsWavePlanM3914',rowindex3914);
				browseCommMenu5('menuWidgetPlanM3914');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }	
	},
	
	//删除头档                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 	
	detailDelete:function(){
		//debugger;
		var objdata = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();    
        if (objdata.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.Ajax.request({
    						url:'odata_WmsWavePlanAction_deletePlanM.action',
    						params :{
    		 					strategyId: objdata[0].get("batchStrategyId"),
    		 					//expType: objdata[0].get("expType")
	 							
    						},
    						
    						success : function(response) {
    							Ext.getCmp('wmsWavePlanM3914').getStore().load();
    						}
    					});
    				}			
            });
        }
	},
	
 	// 查找	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=3914;
		queryGrid='wmsWavePlanM3914';					
	},
	
	//选择头档
 	selectPlanM:function(){
 		var objData = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
 		
 		//debugger;
 		
 		if(objData.length!=0){
 
 			var listDetail = [];
			var str={
				columnId:'a.batch_strategy_id',    
				value:objData[0].get("batchStrategyId")
			};
			listDetail.push(str);
			
			/*str={
				columnId:'a.exp_type',
				value:objData[0].get("expType")
			};
			listDetail.push(str);*/
			
			var jsonDetail = Ext.encode(listDetail);
			var wheresql = {
					str : jsonDetail
			};
		Ext.apply(Ext.getCmp('wmsWavePlanD3914').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('wmsWavePlanD3914').getStore().removeAll();
		Ext.getCmp('wmsWavePlanD3914').getStore().load();
		//7-15
		selectFlag = '1'; 
 		}
 	},
	
 	valueFlag3914Focus:function(){
 		//alert('测试');
 		//debugger;
 		var objData = Ext.getCmp('wmsWavePlanD23914').getSelectionModel().getSelection();
 		var wheresql = {
 				flagSet:objData[0].raw.value
			};
 		
 		Ext.apply(Ext.getCmp('valueFlag3914').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('valueFlag3914').getStore().removeAll();
	    Ext.getCmp('valueFlag3914').getStore().load();
 	},
 	
 	seletcRuleId:function(){
 		//debugger;
 		var selectValue = Ext.getCmp('valueFlag3914').value;
 		var objData = Ext.getCmp('wmsWavePlanD23914').getSelectionModel().getSelection();
 		
 		if(selectValue != ''){
 			if(objData[0].raw.value == '1' || objData[0].raw.value == '2'){
 				//显示相关的字段列
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].hide();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].hide();
 			}else if(objData[0].raw.value == '3'){
 				//显示相关的字段列
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].hide();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].hide();
 			}else if(objData[0].raw.value == '4'){
 				//显示相关的字段列
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].hide();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].hide();	
 				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].show();
 			}
 		var wheresql = {
		 				flagSet:objData[0].raw.value,
		 				columnId:selectValue
					};
 			
			
		Ext.apply(Ext.getCmp('wmsWaveTrialDetail3914').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('wmsWaveTrialDetail3914').getStore().removeAll();
		Ext.getCmp('wmsWaveTrialDetail3914').getStore().load();
 		}
 	},
 	
	//新增明细
	detailAddPlanD:function(){
		//debugger;
		commonSetMsterReadOnlyByArray(
				new Array('batchRuleId3914','batchRuleName3914','skuLimmit3914','taskOrder3914',
						  'waitTimes3914','status3914','ruleFlag3914',
						  'shipperNo3914','orderSource3914','itemTypeFlag3914',
						  'areaLimmit3914','cLimmit3914','deliverType3914',     
						  'printType3914','batchComputeType3914','batchCompute3914',
						  'printEnvoice3914','printWayBill3914','printPackList3914','intervalTimes3914',
						  'skuCount3914','printValue1','printValue2','skuCountMode3914',
						  'rsvControl1','rsvControl2','rsvControl3','rsvControl4','rsvControl5',
						  'rsvValue1','rsvValue2','rsvValue3','rsvValue4','rsvValue5'
						  ,'deliverAddress3914','repeatTimes3914','minOrder3914','lastPickFlag3914',
						  'cDivideFlag3914','bDivideFlag3914','areaLimmitValue3914','areaAllow3914'),false);
		
		Ext.getCmp('formWavePlan3914_1').getForm().reset();
		Ext.getCmp('formWavePlan3914_2').getForm().reset();
		Ext.getCmp('formWavePlan3914_3').getForm().reset();
		//移除试算配置中的列表
		//Ext.getCmp('wmsWavePlanD23914').getStore().reload();
		Ext.getCmp('wmsWaveTrialDetail3914').getStore().removeAll();
		
		flag='add';	
		
		//设置下拉框的默认值
		Ext.getCmp('shipperNo3914').setValue('ALL');
		Ext.getCmp('orderSource3914').setValue('0');
		Ext.getCmp('skuCountMode3914').setValue('1');
		Ext.getCmp('deliverType3914').setValue('0');
		Ext.getCmp('batchComputeType3914').setValue('0');
		Ext.getCmp('batchCompute3914').setValue('0');
		Ext.getCmp('printEnvoice3914').setValue('0');
		Ext.getCmp('printWayBill3914').setValue('0');
		Ext.getCmp('printPackList3914').setValue('0');
		//7-15添加设置默认值
		Ext.getCmp('deliverAddress3914').setValue('0');	
		Ext.getCmp('skuCount3914').setValue('0');	
		Ext.getCmp('skuLimmit3914').setValue('0');	
		Ext.getCmp('repeatTimes3914').setValue('0');	
		Ext.getCmp('taskOrder3914').setValue('0');	
		Ext.getCmp('minOrder3914').setValue('0');	
		//7-21添加设置默认值
		Ext.getCmp('lastPickFlag3914').setValue('N');
		//7-29添加设置默认值
		Ext.getCmp('cDivideFlag3914').setValue('0');
		Ext.getCmp('bDivideFlag3914').setValue('0');
		//8-15添加设置默认值
		Ext.getCmp('ruleFlag3914').setValue('2');
		
		//批次切分模式选择控制  7-16
		var batchComputeTypeTest = Ext.getCmp('batchComputeType3914').getValue();
		if(batchComputeTypeTest=='0' || batchComputeTypeTest=='1'){
			Ext.getCmp('batchCompute3914').disabled = true;
		}
		
		//是否限制区域选择控制  8-6
		var areaLimitTest = Ext.getCmp('areaLimmit3914').getValue();
		if(areaLimitTest == false){
			Ext.getCmp('areaLimmitValue3914').disabled = true;
		}
	 
		//订单类型选择控制  7-28
		var skuCountModeTest = Ext.getCmp('skuCountMode3914').getValue();
		if(skuCountModeTest=='1'){
			Ext.getCmp('taskOrder3914').disabled = true;
		}
		
		//7-19添加
		editFlag = '0';
		
		disableButtonFunc(0,'#menu_3914_2 [name=detailSave]','保存');
		disableButtonFunc(0,'#menu_3914_2 [name=detailUndo]','撤销');
		disableButtonFunc(1,'#menu_3914_2 [name=detailAdd]','新增');
		disableButtonFunc(1,'#menu_3914_2 [name=detailEdit]','修改');
		disableButtonFunc(1,'#menu_3914_2 [name=detailDelete]','删除');
	},
	
	//保存明细
	savePlanD:function(){	
		//debugger;
		saveWmsWavePlanD();	
		//进行新增或者修改的判断    7-19
		var objData = Ext.getCmp('wmsWavePlanD3914').getSelectionModel().getSelection();
		if(flag == 'edit'){
			ruleIdFlag = objData[0].get("batchRuleId");
		}else if(flag == 'add'){
			ruleIdFlag = '';
		}
		selectFlag = '0';
		
		//7-19添加
		editFlag = '0';
		
		disableButtonFunc(1,'#menu_3914_2 [name=detailSave]','保存');
		disableButtonFunc(1,'#menu_3914_2 [name=detailUndo]','撤销');
		disableButtonFunc(0,'#menu_3914_2 [name=detailAdd]','新增');
		disableButtonFunc(0,'#menu_3914_2 [name=detailEdit]','修改');
		disableButtonFunc(0,'#menu_3914_2 [name=detailDelete]','删除');
	},
	
	//选择明细
	selectPlanD:function(){
		var objData = Ext.getCmp('wmsWavePlanD3914').getSelectionModel().getSelection();
		
		debugger;
		if (objData.length != 0) {
			//7-19添加
			editFlag = '1';
			
			Ext.getCmp('batchRuleId3914').setValue(objData[0].get("batchRuleId"));
			Ext.getCmp('batchRuleName3914').setValue(objData[0].get("batchRuleName"));
			Ext.getCmp('skuLimmit3914').setValue(objData[0].get("skuLimmit"));
			Ext.getCmp('taskOrder3914').setValue(objData[0].get("taskOrder"));
			Ext.getCmp('waitTimes3914').setValue(objData[0].get("waitTimes"));
			//Ext.getCmp('seqOrder3914').setValue(objData[0].get("seqOrder"));
			
			Ext.getCmp('status3914').setValue(objData[0].get("status"));//fdaf
			Ext.getCmp('shipperNo3914').setValue(objData[0].get("shipperNo"));
			//7-16修改  三个判断
			if(objData[0].get("orderSource") == 'ALL'){
				Ext.getCmp('orderSource3914').setValue('0');
			}else{
				Ext.getCmp('orderSource3914').setValue(objData[0].get("orderSource"));
			}
			if(objData[0].get("deliverType") == 'ALL'){
				Ext.getCmp('deliverType3914').setValue('0');
			}else{
				Ext.getCmp('deliverType3914').setValue(objData[0].get("deliverType"));
			}
			if(objData[0].get("deliverAddress") == 'ALL'){
				Ext.getCmp('deliverAddress3914').setValue('0');
			}else{
				Ext.getCmp('deliverAddress3914').setValue(objData[0].get("deliverType"));
			}
			//8-15添加
			Ext.getCmp('ruleFlag3914').setValue(objData[0].get("ruleFlag"));
			
			Ext.getCmp('itemTypeFlag3914').setValue(objData[0].get("itemTypeFlag"));
			Ext.getCmp('areaLimmit3914').setValue(objData[0].get("areaLimmit"));
			//8-6添加
			Ext.getCmp('areaAllow3914').setValue(objData[0].get("areaAllow"));
			Ext.getCmp('areaLimmitValue3914').setValue(objData[0].get("areaLimmitValue"));
			
			Ext.getCmp('cLimmit3914').setValue({'rb':objData[0].get("CLimmit")});
			Ext.getCmp('rsvControl1').setValue(objData[0].get("rsvControl1"));
			Ext.getCmp('rsvControl2').setValue(objData[0].get("rsvControl2"));
			Ext.getCmp('rsvControl3').setValue(objData[0].get("rsvControl3"));
			Ext.getCmp('rsvControl4').setValue(objData[0].get("rsvControl4"));
			Ext.getCmp('rsvControl5').setValue(objData[0].get("rsvControl5"));
			Ext.getCmp('printType3914').setValue({'rb':objData[0].get("printType")});
			Ext.getCmp('batchComputeType3914').setValue(objData[0].get("batchComputeType"));
			Ext.getCmp('batchCompute3914').setValue(objData[0].get("batchCompute"));
			Ext.getCmp('rsvValue1').setValue(objData[0].get("rsvValue1"));
			Ext.getCmp('rsvValue2').setValue(objData[0].get("rsvValue2"));
			Ext.getCmp('rsvValue3').setValue(objData[0].get("rsvValue3"));
			Ext.getCmp('rsvValue4').setValue(objData[0].get("rsvValue4"));
			Ext.getCmp('rsvValue5').setValue(objData[0].get("rsvValue5"));
			
			//7-5,6添加
			Ext.getCmp('intervalTimes3914').setValue(objData[0].get("intervalTimes"));
			Ext.getCmp('skuCount3914').setValue(objData[0].get("skucount"));
			Ext.getCmp('printEnvoice3914').setValue(objData[0].get("printEnvoice"));
			Ext.getCmp('printWayBill3914').setValue(objData[0].get("printWaybill"));
			Ext.getCmp('printPackList3914').setValue(objData[0].get("printPacklist"));
			Ext.getCmp('printValue1').setValue(objData[0].get("printValue1"));
			Ext.getCmp('printValue2').setValue(objData[0].get("printValue2"));
			Ext.getCmp('skuCountMode3914').setValue(objData[0].get("skuCountMode"));
			
			//7-15添加
			Ext.getCmp('repeatTimes3914').setValue(objData[0].get("repeatTimes"));
			Ext.getCmp('minOrder3914').setValue(objData[0].get("minOrder"));
			//7-21添加  判断
			if(objData[0].get("lastPickFlag") == '0'){
				Ext.getCmp('lastPickFlag3914').setValue(true);
			}
			//7-29添加
			Ext.getCmp('cDivideFlag3914').setValue(objData[0].get("CDivideFlag"));
			Ext.getCmp('bDivideFlag3914').setValue(objData[0].get("BDivideFlag"));
			
			var mygrid = Ext.getCmp('wmsWavePlanD23914');
			var total = mygrid.getStore().getCount();//数据行数
			for(var i=0;i<total;i++){
				var eachRow = mygrid.getStore().getAt(i);
				if(eachRow.raw.value == '1'){
					eachRow.set('trialRuleId',objData[0].get("custLogiboxRuleId"));
				}else if(eachRow.raw.value == '2'){
					eachRow.set('trialRuleId',objData[0].get("divideLogiboxRuleId"));
				}else if(eachRow.raw.value == '3'){
					eachRow.set('trialRuleId',objData[0].get("taskRuleId"));
				}else if(eachRow.raw.value == '4'){
					eachRow.set('trialRuleId',objData[0].get("pickStrategyId"));
				}
			}
			//移除配置详细信息
			Ext.getCmp('wmsWaveTrialDetail3914').getStore().removeAll();
		}else{	
			//Ext.getCmp('wmsWavePlanD3914_1').getForm().reset();
			Ext.getCmp('formWavePlan3914_1').getForm().reset();
			Ext.getCmp('formWavePlan3914_2').getForm().reset();
			Ext.getCmp('formWavePlan3914_3').getForm().reset();
		}
	},
	
	//选择试算配置名称，显示详细信息   6-24
	selectPlanDTrial:function(){
		//debugger;
		var trialRuleIdTest;
		var mygrid = Ext.getCmp('wmsWavePlanD23914');
		var objData = Ext.getCmp('wmsWavePlanD23914').getSelectionModel().getSelection();
		if(objData[0].raw.value == '1'){//|| objData[0].raw.value == '2'
				//显示相关的字段列
				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].hide();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].hide();
				trialRuleIdTest = mygrid.getStore().getAt(0).data['trialRuleId'];
			}else if(objData[0].raw.value == '2'){
				//显示相关的字段列
				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].hide();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].hide();
				trialRuleIdTest = mygrid.getStore().getAt(1).data['trialRuleId'];
			}else if(objData[0].raw.value == '3'){
				//显示相关的字段列
				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].hide();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].hide();
				trialRuleIdTest = mygrid.getStore().getAt(2).data['trialRuleId'];
			}else if(objData[0].raw.value == '4'){
				//显示相关的字段列
				Ext.getCmp('wmsWaveTrialDetail3914').columns[1].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[2].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[4].show();	
				Ext.getCmp('wmsWaveTrialDetail3914').columns[3].hide();
				Ext.getCmp('wmsWaveTrialDetail3914').columns[5].show();
				trialRuleIdTest = mygrid.getStore().getAt(3).data['trialRuleId'];
			}
		
			var wheresql = {
 				flagSet:objData[0].raw.value,
 				columnId:trialRuleIdTest
			};
			Ext.apply(Ext.getCmp('wmsWaveTrialDetail3914').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('wmsWaveTrialDetail3914').getStore().removeAll();
			Ext.getCmp('wmsWaveTrialDetail3914').getStore().load();
		
	},
	
	// 修改明细
	detailEditPlanD:function(){
		//7-19添加
		editFlag = '0';
		
		commonSetMsterReadOnlyByArray(
				new Array('batchRuleId3914','batchRuleName3914','skuLimmit3914','taskOrder3914',
						  'waitTimes3914','status3914','ruleFlag3914',
						  'shipperNo3914','orderSource3914','itemTypeFlag3914',
						  'areaLimmit3914','cLimmit3914','deliverType3914',     
						  'printType3914','batchComputeType3914','batchCompute3914',
						  'printEnvoice3914','printWayBill3914','printPackList3914','intervalTimes3914',
						  'skuCount3914','printValue1','printValue2','skuCountMode3914',
						  'rsvControl1','rsvControl2','rsvControl3','rsvControl4','rsvControl5',
						  'rsvValue1','rsvValue2','rsvValue3','rsvValue4','rsvValue5'
						  ,'deliverAddress3914','repeatTimes3914','minOrder3914','lastPickFlag3914',
						  'cDivideFlag3914','bDivideFlag3914','areaLimmitValue3914','areaAllow3914'),false);
		
		flag='edit';
		ifDivede();
		disableButtonFunc(0,'#menu_3914_2 [name=detailSave]','保存');
		disableButtonFunc(0,'#menu_3914_2 [name=detailUndo]','撤销');
		disableButtonFunc(1,'#menu_3914_2 [name=detailAdd]','新增');
		disableButtonFunc(1,'#menu_3914_2 [name=detailEdit]','修改');
		disableButtonFunc(1,'#menu_3914_2 [name=detailDelete]','删除');
	},

	//删除明细
	deletePlanD:function(){
		
		//debugger;

		var objdata = Ext.getCmp('wmsWavePlanD3914').getSelectionModel().getSelection();
		var objdata2 = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
        if (objdata.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
        				//debugger;
        				
    					Ext.Ajax.request({
    						url:'odata_WmsWavePlanAction_deletePlanD.action',
    						params :{  						
    		 					strategyId: objdata2[0].get("batchStrategyId"),
    		 					//expType: objdata[0].get("expType"),
    		 					//strategyType: objdata[0].get("strategyType"),
    		 					strategyType: objdata[0].get("batchRuleId"),
    		 					seqOrder:objdata[0].get("seqOrder")
    						},
    						success : function(response) {
    							Ext.getCmp('wmsWavePlanD3914').getStore().load();
    						}
    					});
    				}			
            });
        }	
	},
	//撤销
	undoPlanD:function(){	
		commonSetMsterReadOnlyByArray(
				new Array('batchRuleId3914','batchRuleName3914','skuLimmit3914','taskOrder3914',
						  'waitTimes3914','status3914','ruleFlag3914',
						  'shipperNo3914','orderSource3914','itemTypeFlag3914',
						  'areaLimmit3914','cLimmit3914','deliverType3914',     
						  'printType3914','batchComputeType3914','batchCompute3914',
						  'printEnvoice3914','printWayBill3914','printPackList3914','intervalTimes3914',
						  'skuCount3914','printValue1','printValue2','skuCountMode3914',
						  'rsvControl1','rsvControl2','rsvControl3','rsvControl4','rsvControl5',
						  'rsvValue1','rsvValue2','rsvValue3','rsvValue4','rsvValue5'
						  ,'deliverAddress3914','repeatTimes3914','minOrder3914','lastPickFlag3914',
						  'cDivideFlag3914','bDivideFlag3914','areaLimmitValue3914','areaAllow3914'),true);
		
		/*var wheresql = {
				str : ""
		};
		Ext.apply(Ext.getCmp('strategyType3914').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('strategyType3914').getStore().removeAll();
		Ext.getCmp('strategyType3914').getStore().load();*/
		//重新填充
		this.selectPlanD();
		disableButtonFunc(1,'#menu_3914_2 [name=detailSave]','保存');
		disableButtonFunc(1,'#menu_3914_2 [name=detailUndo]','撤销');
		disableButtonFunc(0,'#menu_3914_2 [name=detailAdd]','新增');
		disableButtonFunc(0,'#menu_3914_2 [name=detailEdit]','修改');
		disableButtonFunc(0,'#menu_3914_2 [name=detailDelete]','删除');
	},
	
	//明细顺序向上
	top:function(){
		//debugger;
		var objdata = Ext.getCmp('wmsWavePlanD3914').getSelectionModel().getSelection();
		var objdata2 = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
		if (objdata.length == 0) {  
			Ext.example.msg($i18n.prompt,'请选择要上移的数据'); 
	           return;  
	    }
		Ext.Ajax.request({
			url:'odata_WmsWavePlanAction_seqencing.action',
			params :{  						
					strategyId: objdata2[0].get("batchStrategyId"),
					//expType: objdata[0].get("expType"),
					//strategyType: objdata[0].get("strategyType"),
					strategyType: objdata[0].get("batchRuleId"),
					seqOrder:objdata[0].get("seqOrder"),
					flag:'1'				
			},
			success : function(response) {
				//debugger;
				Ext.getCmp('wmsWavePlanD3914').getStore().load();
			}
		});
		
	},
	
	//7-15添加
	 getSelectFlag:function(){
  		 return selectFlag;
  	 },
  	 
  	 setSelectFlag:function(value){
  		selectFlag =value;
  	 },
  	 //7-19添加
  	 getRuleIdFlag:function(){
 		 return ruleIdFlag;
 	 },
 	 
 	 setRuleIdFlag:function(value){
 		ruleIdFlag =value;
 	 },
 	 getEditFlag:function(){
		 return editFlag;
	 },
	 
	 setEditFlag:function(value){
		 editFlag =value;
	 },
	
	down:function(){
		//debugger;
		var objdata = Ext.getCmp('wmsWavePlanD3914').getSelectionModel().getSelection();
		var objdata2 = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
		if (objdata.length == 0) {  
			Ext.example.msg($i18n.prompt,'请选择要下移的数据'); 
	           return;  
	    }
		Ext.Ajax.request({
			url:'odata_WmsWavePlanAction_seqencing.action',
			params :{  						
					strategyId: objdata2[0].get("batchStrategyId"),
					//expType: objdata[0].get("expType"),
					//strategyType: objdata[0].get("strategyType"),
					strategyType: objdata[0].get("batchRuleId"),
					seqOrder:objdata[0].get("seqOrder"),
					flag:'-1'				
			},
			success : function(response) {
				//debugger;
				Ext.getCmp('wmsWavePlanD3914').getStore().load();
			}
		});
	}	
 });
 

  // 保存波次计划头档
  function saveWmsWavePlanM(){
	if(!commonCheckIsInputAll('odata_WmsWavePlanMAddOrEditWindow3914')){
		return;
	}
	  
 	if(Ext.getCmp('odata_WmsWavePlanMAddOrEditWindow3914').getForm().isValid())
 	{
 		var planMid=Ext.getCmp('strategyId3914').getValue();
 		if(planMid=='保存时自动生成'){
 			var planMid=-1;
 		}
 		var str={
 				id:{
 					enterpriseNo:Ext.get('enterpriseNo').getValue(),
 					batchStrategyId:planMid				
 				},
 				strategyName:Ext.getCmp('strategyName3914').getValue(),
 				//betweenTimes:Ext.getCmp('betweenTimes3914').getValue(),
 				defalutFlag:'1',
 				rgstName:Ext.get('workerName').getValue(),
 				rgstDate:new Date()
 		};
 		var jsonStr = Ext.encode(str);
 		Ext.Ajax.request({
 			url:'odata_WmsWavePlanAction_saveOrUpdatePlanM.action',
 			params : {
 				str:jsonStr
 			},
 			success:function(response){
 				var data = Ext.decode(response.responseText);
 				if(data.isSucc){
 					Ext.example.msg($i18n.prompt,'保存成功');
 					Ext.getCmp('wmsWavePlanM3914').getStore().load();
 					Ext.getCmp('strategyId3914').setValue(data.msg);
 					commonSetMsterReadOnlyByArray(
 						new Array('expType3914'),true);
 				}else{
 					Ext.Msg.alert($i18n.prompt,'保存失败');
 					//Ext.example.msg($i18n.prompt,'保存失败');
 				}
 			}
 		});
 	}
 }
 
 // 填充波次计划规则
 function loadData(rowindex3914){	
	// debugger;
 	var objRecord=Ext.getCmp('wmsWavePlanM3914').getStore().getAt(rowindex3914);	
    Ext.getCmp('strategyId3914').setValue(objRecord.data.batchStrategyId);
 	Ext.getCmp('strategyName3914').setValue(objRecord.data.strategyName);
 	//Ext.getCmp('expType3914').setValue(objRecord.data.expType);
	//Ext.getCmp('betweenTimes3914').setValue(objRecord.data.betweenTimes);
 }
 
 //分播是否可选  
 function ifDivede(){
	    var objdata = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
		Ext.Ajax.request({
 			url:'odata_WmsWavePlanAction_ifDivede.action',
 			params : {
 				expType: objdata[0].get("expType")
 			},	
 			success:function(response){
 				var data = Ext.decode(response.responseText);
 				if(data.isSucc){
 					
 					var strs= new Array();
 					strs=data.msg.split(",");
 					if(strs[0]=='0'){
 						Ext.getCmp('BDivideFlag3914').setValue('0');	
 						commonSetMsterReadOnlyByArray(
 		 						new Array('BDivideFlag3914'),true);
 		 			}else{
 		 				commonSetMsterReadOnlyByArray(
 		 						new Array('BDivideFlag3914'),false);
 		 			}
 					
 					if(strs[1]=='0'){
 						Ext.getCmp('CDivideFlag3914').setValue('0');	
 						commonSetMsterReadOnlyByArray(
 		 						new Array('CDivideFlag3914'),true);
 		 			}else{
 		 				commonSetMsterReadOnlyByArray(
 		 						new Array('CDivideFlag3914'),false);
 		 			}					
 				} 					
 			}
 		});
 }
 
 //保存明细
 function saveWmsWavePlanD(){
	 debugger;
	 var lastRow;
	 var newOrder;
	 var objData = Ext.getCmp('wmsWavePlanM3914').getSelectionModel().getSelection();
	 var objPlanD =Ext.getCmp('wmsWavePlanD3914').getSelectionModel().getSelection()[0];
	 var mygrid = Ext.getCmp('wmsWavePlanD3914');
	 var size = mygrid.getStore().getCount();
	 if(size > 0){	//判断是否为第一条记录
		 lastRow = mygrid.getStore().getAt(size-1);
		 newOrder = lastRow.raw.seqOrder+1;
	 }else{
		 newOrder = 1;
	 }
	
	 if(objData.length != 0) {					 
		// if(Ext.getCmp('formWavePlan3914_1').getForm().isValid() &&
				// Ext.getCmp('formWavePlan3914_2').getForm().isValid() &&
			//	 Ext.getCmp('formWavePlan3914_3').getForm().isValid()){
		    if(Ext.getCmp('formWavePlan3914_1').getForm().isValid()){
		    	//获得试算配置里面列表的值
		 		var mygrid = Ext.getCmp('wmsWavePlanD23914');
		 		var firstValue = mygrid.getStore().getAt(0).data['trialRuleId'];
		 		var secondValue = mygrid.getStore().getAt(1).data['trialRuleId'];
		 		var threeValue = mygrid.getStore().getAt(2).data['trialRuleId'];
		 		var fourValue = mygrid.getStore().getAt(3).data['trialRuleId'];
				 
			 	var str={
			 			id:{
			 				enterpriseNo:Ext.get('enterpriseNo').getValue(),
			 				batchRuleId:Ext.getCmp('batchRuleId3914').getValue(),
			 				batchStrategyId:objData[0].get("batchStrategyId")
			 			},
			 			
			 			batchRuleName:Ext.getCmp('batchRuleName3914').getValue(),
			 			skuLimmit:Ext.getCmp('skuLimmit3914').getValue()==""?0:Ext.getCmp('skuLimmit3914').getValue(),
			 			taskOrder:Ext.getCmp('taskOrder3914').getValue()==""?0:Ext.getCmp('taskOrder3914').getValue(),
			 			waitTimes:Ext.getCmp('waitTimes3914').getValue()==""?20:Ext.getCmp('waitTimes3914').getValue(),
			 			status:Ext.getCmp('status3914').getValue()==true?'1':'0',
			 			shipperNo:Ext.getCmp('shipperNo3914').getValue()=='0'?'ALL':Ext.getCmp('shipperNo3914').getValue(),
			 			
			 			orderSource:Ext.getCmp('orderSource3914').getValue()=='0'?'ALL':Ext.getCmp('orderSource3914').getValue(),	//zy
			 			itemTypeFlag:Ext.getCmp('itemTypeFlag3914').getValue()==true?'1':'0',
			 			areaLimmit:Ext.getCmp('areaLimmit3914').getValue()==true?'1':'0',
			 			//8-6添加
			 			areaAllow:Ext.getCmp('areaAllow3914').getValue()==true?'1':'0',
			 			areaLimmitValue:Ext.getCmp('areaLimmitValue3914').getValue(),	
			 					
			 			cLimmit:Ext.getCmp('cLimmit3914').getValue().rb,	
			 			rsvControl1:Ext.getCmp('rsvControl1').getValue(),
			 			rsvControl2:Ext.getCmp('rsvControl2').getValue(),
			 			rsvControl3:Ext.getCmp('rsvControl3').getValue(),
			 			rsvControl4:Ext.getCmp('rsvControl4').getValue(),
			 			rsvControl5:Ext.getCmp('rsvControl5').getValue(),
			 			//测试
			 			deliverType:Ext.getCmp('deliverType3914').getValue()=='0'?'ALL':Ext.getCmp('deliverType3914').getValue(),
			 			printType:Ext.getCmp('printType3914').getValue().rb,
			 			batchComputeType:Ext.getCmp('batchComputeType3914').getValue()==null?'0':Ext.getCmp('batchComputeType3914').getValue(),
			 			batchCompute:Ext.getCmp('batchCompute3914').getValue()==null?'0':Ext.getCmp('batchCompute3914').getValue(),
			 			rsvValue1:Ext.getCmp('rsvValue1').getValue(),
			 			rsvValue2:Ext.getCmp('rsvValue2').getValue(),
			 			rsvValue3:Ext.getCmp('rsvValue3').getValue(),
			 			rsvValue4:Ext.getCmp('rsvValue4').getValue(),
			 			rsvValue5:Ext.getCmp('rsvValue5').getValue(),
			 			custLogiboxRuleId:firstValue,
			 			divideLogiboxRuleId:secondValue,
			 			taskRuleId:threeValue,
			 			pickStrategyId:fourValue,
			 			
			 			//7-16修改 
			 			minOrder:Ext.getCmp('minOrder3914').getValue()==""?0:Ext.getCmp('minOrder3914').getValue(),
			 			repeatTimes:Ext.getCmp('repeatTimes3914').getValue()==""?0:Ext.getCmp('repeatTimes3914').getValue(),
			 			deliverAddress:Ext.getCmp('deliverAddress3914').getValue()=='0'?'ALL':Ext.getCmp('deliverAddress3914').getValue(),
			 			//8-15添加
			 			ruleFlag:Ext.getCmp('ruleFlag3914').getValue(),
			 			//7-29修改和添加
			 			lastPickFlag:Ext.getCmp('lastPickFlag3914').getValue()==true?'0':'N',
			 			CDivideFlag:Ext.getCmp('cDivideFlag3914').getValue()==null?'0':Ext.getCmp('cDivideFlag3914').getValue(),
			 			BDivideFlag:Ext.getCmp('bDivideFlag3914').getValue()==null?'0':Ext.getCmp('bDivideFlag3914').getValue(),
			 					
			 			lineFlag:'0',
			 			//7-5,6添加
			 			printEnvoice:Ext.getCmp('printEnvoice3914').getValue()==null?'0':Ext.getCmp('printEnvoice3914').getValue(),
			 			printWaybill:Ext.getCmp('printWayBill3914').getValue()==null?'0':Ext.getCmp('printWayBill3914').getValue(),
			 			printPacklist:Ext.getCmp('printPackList3914').getValue()==null?'0':Ext.getCmp('printPackList3914').getValue(),
			 			skucount:Ext.getCmp('skuCount3914').getValue()==""?0:Ext.getCmp('skuCount3914').getValue(),
			 			printValue1:Ext.getCmp('printValue1').getValue(),
			 			printValue2:Ext.getCmp('printValue2').getValue(),
			 			intervalTimes:Ext.getCmp('intervalTimes3914').getValue()==""?20:Ext.getCmp('intervalTimes3914').getValue(),
			 			skuCountMode:Ext.getCmp('skuCountMode3914').getValue(),
			 					
			 			seqOrder:flag=='add'?newOrder:objPlanD.data.seqOrder,
			 			rgstDate:flag=='add'?new Date():objPlanD.data.rgstDate.time,
			 			rgstName:flag=='add'?Ext.get('workerNo').getValue():objPlanD.data.rgstName,
			 			updtDate:flag=='add'?'':new Date(),
			 			updtName:flag=='add'?'':Ext.get('workerNo').getValue()
			 		};
			 	
			 		//debugger;
			 	
			 		var jsonStr = Ext.encode(str);
			 		Ext.Ajax.request({
			 			url:'odata_WmsWavePlanAction_saveOrUpdatePlanD.action',
			 			params : {
			 				str:jsonStr
			 			},
			 			success:function(response){
			 				var data = Ext.decode(response.responseText);
			 				if(data.isSucc){
			 					Ext.example.msg($i18n.prompt,'保存成功');
			 					//debugger;
			 					commonSetMsterReadOnlyByArray(
			 							new Array('batchRuleId3914','batchRuleName3914','skuLimmit3914','taskOrder3914',
			 									  'waitTimes3914','status3914','ruleFlag3914',
			 									  'shipperNo3914','orderSource3914','itemTypeFlag3914',
			 									  'areaLimmit3914','cLimmit3914','deliverType3914',     
			 									  'printType3914','batchComputeType3914','batchCompute3914',
			 									  'printEnvoice3914','printWayBill3914','printPackList3914','intervalTimes3914',
			 									  'skuCount3914','printValue1','printValue2','skuCountMode3914',
			 									  'rsvControl1','rsvControl2','rsvControl3','rsvControl4','rsvControl5',
			 									  'rsvValue1','rsvValue2','rsvValue3','rsvValue4','rsvValue5'
			 									  ,'deliverAddress3914','repeatTimes3914','minOrder3914','lastPickFlag3914',
			 									 'cDivideFlag3914','bDivideFlag3914','areaLimmitValue3914','areaAllow3914'),true);
			 					Ext.getCmp('wmsWavePlanD3914').getStore().load();
			 					//重新加载试算列表
			 					//Ext.getCmp('wmsWavePlanD23914').getStore().reload();
			 					
			 					Ext.getCmp('formWavePlan3914_1').getForm().reset();
			 					Ext.getCmp('formWavePlan3914_2').getForm().reset();
			 					Ext.getCmp('formWavePlan3914_3').getForm().reset();
			 				}else{
			 					Ext.Msg.alert($i18n.prompt,'保存失败');
			 					//Ext.example.msg($i18n.prompt,'保存失败');
			 				}
			 			}
			 		});
		    }else{
		    	Ext.example.msg($i18n.prompt,'请输入必填项信息！');
		    }
			
		 //}else{
			// Ext.example.msg($i18n.prompt,'请输入必填项信息！');
		// }	
     }else{
     	Ext.example.msg($i18n.prompt,'请选择波次规则');
     }
 }
 
