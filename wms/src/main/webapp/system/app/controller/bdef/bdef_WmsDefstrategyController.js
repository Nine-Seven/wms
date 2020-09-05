/**
 * 模块名称：策略配置头档
 * 模块编码：I101
 * hcx
 */
 var rowindexI101=0;
 var g_varbuttonI101 = '';
 var g_varbuttonI101_2 = '';
 var hideFlag=[];
 var ruleOrderFlag='';
 Ext.define('cms.controller.bdef.bdef_WmsDefstrategyController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.bdef.bdef_WmsDefstrategyUI',
 	'cms.view.bdef.window.bdef_WmsDefstrategyAddOrEditWindow',
 	'cms.view.bdef.window.bdef_WmsDefstrategyDAddOrEditWindow'
 	],
 	model:'',
 	store:'',
 	init:function(){
 		 this.control(//新增策略头档
 	 		{'bdef_WmsDefstrategyUI commMenuWidget2[id=menuI101] button[name=detailAdd]':{
				click:this.detailAdd
			},//策略头档-删除
			'bdef_WmsDefstrategyUI commMenuWidget2[id=menuI101] button[name=detailDelete]':{
				click:this.strategydetailDeleteClik  
			},//策略头档-修改
			'bdef_WmsDefstrategyUI commMenuWidget2[id=menuI101] button[name=detailEdit]':{
				click:this.detailEditClik  
			},//策略头档-查找
			'bdef_WmsDefstrategyUI button[name=detailQuery]':{
				click:this.strategyQuery
			},//策略头档窗口-->新增
			'bdef_WmsDefstrategyAddOrEditWindow button[name=add]':{
				click:this.strategyWinAdd
			},//策略头档-上一条记录
			'bdef_WmsDefstrategyAddOrEditWindow button[name=prev]':{
				click:this.strategyPrev
			},//策略头档-下一条记录
			'bdef_WmsDefstrategyAddOrEditWindow button[name=next]':{
				click:this.strategyNext
			},//策略头档-关闭窗口
			'bdef_WmsDefstrategyAddOrEditWindow button[name=close]':{
				click:this.strategyColse
			},//策略头档-保存
			'bdef_WmsDefstrategyAddOrEditWindow button[name=save]':{
				click:this.strategySave
			},//策略类型选择
			'wms_DefFieldValCombo[id=strategyTypeUII101]':{
				select:this.strategyTypeUISelect
			},//默认标识选择
			'wms_DefFieldValCombo[id=defaultFlagUII101]':{
				select:this.defaultFlagUISelect
			},//策略id/名称选择
			'remoteCombo[id=strategyIdOrNameI101]':{
				beforequery:this.strategyIdOrNameBeforeQuery,
				select:this.strategyIdOrNameSelect
			},//选择一条保拣线加载对应的保拣级别
 			'bdef_WmsDefstrategyUI grid[id=wms_defstrategyGridI101]':{
 				selectionchange:this.editWms_defstrategyGridI101
 			},//新增策略明细
 			'bdef_WmsDefstrategyUI commMenuWidget6[id=menu_I101_2] button[name=detailAdd]':{
	    		click:this.detailAddI101_2
	    	},//策略明细-修改
			'bdef_WmsDefstrategyUI commMenuWidget6[id=menu_I101_2] button[name=detailEdit]':{
				click:this.strategyDdetailEditClik  
			},//策略明细-删除
			'bdef_WmsDefstrategyUI commMenuWidget6[id=menu_I101_2] button[name=detailDelete]':{
				click:this.strategyDdetailDeleteClik  
			},//策略明细-上移
			'bdef_WmsDefstrategyUI commMenuWidget6[id=menu_I101_2] button[name=prevButton]':{
				click:this.strategyDPrevButton
			},//策略明细-下移
			'bdef_WmsDefstrategyUI commMenuWidget6[id=menu_I101_2] button[name=nextButton]':{
				click:this.strategyDNextButton
			},//策略明细窗口-->新增
			'bdef_WmsDefstrategyDAddOrEditWindow button[name=add]':{
				click:this.strategyDWinAdd
			},//策略明细-上一条记录
			'bdef_WmsDefstrategyDAddOrEditWindow button[name=prev]':{
				click:this.strategyDPrev
			},//策略明细-下一条记录
			'bdef_WmsDefstrategyDAddOrEditWindow button[name=next]':{
				click:this.strategyDNext
			},//策略明细-关闭窗口
			'bdef_WmsDefstrategyDAddOrEditWindow button[name=close]':{
				click:this.strategyDColse
			},//策略明细-保存
			'bdef_WmsDefstrategyDAddOrEditWindow button[name=save]':{
				click:this.strategyDSave
			}
 		    
 	    });
 	},
 	//新增策略头档
 	detailAdd:function()
		{
		Ext.create('cms.view.bdef.window.bdef_WmsDefstrategyAddOrEditWindow',{
			title:$i18n.titleadd
		}).show();
		addWmsDefstrategy();
		//控制菜单显示项
		addCommMenu5('menuWidgetI101_1');
		Ext.getCmp('cmbStrategyTypeI101').focus(false, 2);
		bindEnterSkip($('#bdef_WmsDefstrategyAddOrEditI101'));//调用键盘处理方法
		g_varbuttonI101='add';
		Ext.getCmp('cmbDefaultFlagI101').setValue('1');
		Ext.getCmp('strategyIdI101').setValue($i18n_prompt.autogenerationWhenSave);
		commonSetMsterReadOnlyByArray(
				new Array('strategyIdI101'),true);
    },
    //策略头档-修改
    detailEditClik:function(){
		var data=Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_WmsDefstrategyAddOrEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			this.loadWmsDefstrategyI101();
			commonMenu5PrevOrNext('menuWidgetI101_1','wms_defstrategyGridI101',0);
			updateCommMenu5('menuWidgetI101_1');
			g_varbuttonI101='edit';
		}
	},
	//策略头档-删除
	strategydetailDeleteClik:function()
	{
		var objData = Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();  
        if (objData.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {
        	var listDetail = [];
    		var strDtl = {
    				columnId:'a.strategy_type',
    				value:objData[0].get("strategyType")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.strategy_id',
    				value:objData[0].get("strategyId")	
    		};
    		listDetail.push(strDtl);
    		var jsonDetail = Ext.encode(listDetail);
        	Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        		if(button=='yes')
    			{
    				Ext.Ajax.request({
    					method:'POST',
    					url:'bdef_WmsDefstrategyAction_deleteStrategy',
    					params :{ 
    						strQuery : jsonDetail
    					},
    					success : function(response) {
    						var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('wms_defstrategyGridI101').getStore().load();
								Ext.getCmp('strategyTypeUII101').getStore().removeAll();
						 		Ext.getCmp('strategyTypeUII101').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}  	
						}
    				});
    			}			
            });
        }
	},
	//策略头档-查找
	strategyQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='I101';
		queryGrid='wms_defstrategyGridI101';
		Ext.getCmp('strategyTypeUII101').setValue('');
		Ext.getCmp('defaultFlagUII101').setValue('');
		Ext.getCmp('strategyIdOrNameI101').setValue('');
	},
	//策略头档窗口-新增
	strategyWinAdd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('cmbStrategyTypeI101'),false);
	    bindEnterSkip($('#bdef_DefCarTypeAddOrEditForm'));//调用键盘处理方法
	    addWmsDefstrategy();
	    Ext.getCmp('strategyIdI101').setValue($i18n_prompt.autogenerationWhenSave);
		g_varbuttonI101='add';
		Ext.getCmp('cmbStrategyTypeI101').focus(false, 2);
		Ext.getCmp('cmbDefaultFlagI101').setValue('1');
    },
	//策略头档-上一条记录
	strategyPrev:function(){		
		commonMenu5PrevOrNext('menuWidgetI101_1','wms_defstrategyGridI101',-1);
		this.loadWmsDefstrategyI101();
	},
	//策略头档-下一条记录
	strategyNext:function(){
		commonMenu5PrevOrNext('menuWidgetI101_1','wms_defstrategyGridI101',1);
		this.loadWmsDefstrategyI101();
	},
	//策略头档-关闭窗口
	strategyColse:function(){
		Ext.getCmp('bdef_WmsDefstrategyAddOrEditWindow').close();
		Ext.getCmp('wms_defstrategyGridI101').getStore().reload();
	},
	//策略头档-填充数据
	loadWmsDefstrategyI101:function(){
		var rec=Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();
		if(rec.length>0){
			Ext.getCmp('strategyIdI101').setValue(rec[0].data.strategyId);
			Ext.getCmp('cmbStrategyTypeI101').setValue(rec[0].data.strategyType);
			Ext.getCmp('strategyTypeNameI101').setValue(rec[0].data.strategyName);
			Ext.getCmp('cmbDefaultFlagI101').setValue(rec[0].data.defaultFlag);
			commonSetMsterReadOnlyByArray(
					new Array('strategyIdI101','cmbStrategyTypeI101'),true);
			Ext.getCmp('strategyTypeNameI101').focus(false, 3);
		}
	},
	//策略头档-保存
	strategySave:function(){
		
		if(!commonCheckIsInputAll('bdef_WmsDefstrategyAddOrEditI101')){
			return;
		}
		
		if(Ext.getCmp('bdef_WmsDefstrategyAddOrEditI101').getForm().isValid()){
			var rec=Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection()[0];
			var strategyId='';
			if(Ext.getCmp('strategyIdI101').getValue()==$i18n_prompt.autogenerationWhenSave){
				strategyId=null;
			}else{
				strategyId=Ext.getCmp('strategyIdI101').getValue();
			}
			strategyDStr={
				id:{
					strategyId:strategyId,
					strategyType:Ext.getCmp('cmbStrategyTypeI101').getValue()
				},	
				strategyName:Ext.getCmp('strategyTypeNameI101').getValue(),
				defaultFlag:Ext.getCmp('cmbDefaultFlagI101').getValue(),
				rgstDate:g_varbuttonI101=='add'?new Date():rec.data.rgstDate,
				rgstName:g_varbuttonI101=='add'?Ext.get('workerNo').getValue():rec.data.rgstName
			};								
			Ext.Ajax.request({
				url:'bdef_WmsDefstrategyAction_saveStrategy',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(strategyDStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						if(Ext.getCmp('strategyIdI101').getValue()==$i18n_prompt.autogenerationWhenSave){
							Ext.getCmp('strategyIdI101').setValue(data.obj);
						}
						commonSetMsterReadOnlyByArray(
								new Array('cmbStrategyTypeI101'),true);
						Ext.example.msg($i18n.prompt,data.msg);
						
				 		Ext.getCmp('strategyTypeUII101').getStore().removeAll();
				 		Ext.getCmp('strategyTypeUII101').getStore().reload();
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
		}
	},
	//策略类型选择
	strategyTypeUISelect:function(){
  		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('strategyTypeUII101').getValue())
 				&& Ext.getCmp('strategyTypeUII101').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.strategy_type',
 					value:Ext.getCmp("strategyTypeUII101").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		
 		var params={
 			strQuery:Ext.encode(listDetail1)
 		};
 		var wheresql={
 			str:'',
 	 		strQuery:Ext.encode(listDetail1)
 	 	};
 		//默认标识下拉加载
 		Ext.getCmp('defaultFlagUII101').setValue('');
 		Ext.apply(Ext.getCmp('defaultFlagUII101').getStore().proxy.extraParams,params);
 		Ext.getCmp('defaultFlagUII101').getStore().removeAll();
 		Ext.getCmp('defaultFlagUII101').getStore().reload();
 		//策略id/名称下拉加载
 		Ext.getCmp('strategyIdOrNameI101').setValue('');
 		Ext.apply(Ext.getCmp('strategyIdOrNameI101').getStore().proxy.extraParams,wheresql);
 		Ext.getCmp('strategyIdOrNameI101').getStore().removeAll();
 		Ext.getCmp('strategyIdOrNameI101').getStore().reload();
 		//策略头档列表加载
 		Ext.apply(Ext.getCmp('wms_defstrategyGridI101').getStore().proxy.extraParams,params);
 		Ext.getCmp('wms_defstrategyGridI101').getStore().removeAll();
 		Ext.getCmp('wms_defstrategyGridI101').getStore().reload();
    },
  	//默认标识选择
  	defaultFlagUISelect:function(){
  		var listDetail1  =  [];
  		var strDt2 = {
				columnId:'a.default_flag',
				value:Ext.getCmp("defaultFlagUII101").getValue()
		};
		listDetail1.push(strDt2);
 		if(!Ext.isEmpty(Ext.getCmp('strategyTypeUII101').getValue())
 				&& Ext.getCmp('strategyTypeUII101').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.strategy_type',
 					value:Ext.getCmp("strategyTypeUII101").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		
 		var params={
 			strQuery:Ext.encode(listDetail1)
 		};
 		var wheresql={
 			str:'',
 	 		strQuery:Ext.encode(listDetail1)
 	 	};
 		//策略id/名称下拉加载
 		Ext.getCmp('strategyIdOrNameI101').setValue('');
 		Ext.apply(Ext.getCmp('strategyIdOrNameI101').getStore().proxy.extraParams,wheresql);
 		Ext.getCmp('strategyIdOrNameI101').getStore().removeAll();
 		Ext.getCmp('strategyIdOrNameI101').getStore().reload();
 		//策略头档列表加载
 		Ext.apply(Ext.getCmp('wms_defstrategyGridI101').getStore().proxy.extraParams,params);
 		Ext.getCmp('wms_defstrategyGridI101').getStore().removeAll();
 		Ext.getCmp('wms_defstrategyGridI101').getStore().reload();
  	},
	//策略id/名称加载
	strategyIdOrNameBeforeQuery:function(){
 		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('strategyTypeUII101').getValue()) 
				&& Ext.getCmp('strategyTypeUII101').getValue() !='ALL'){
			var strDt2 = {
					columnId:'a.strategy_type',
					value:Ext.getCmp("strategyTypeUII101").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('defaultFlagUII101').getValue())){
			var strDt2 = {
					columnId:'a.default_flag',
					value:Ext.getCmp("defaultFlagUII101").getValue()
				};
				listDetail1.push(strDt2);
		}
		var params={
			str:Ext.getCmp("strategyIdOrNameI101").getValue(),   
			strQuery:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('strategyIdOrNameI101').getStore().proxy.extraParams,params);
		Ext.getCmp('strategyIdOrNameI101').getStore().removeAll();
		Ext.getCmp('strategyIdOrNameI101').getStore().load();
 	 },
 	//策略id/名称选择
 	strategyIdOrNameSelect:function(){
  		var listDetail1  =  [];
  		var strDt2 = {
				columnId:'a.strategy_id',
				value:Ext.getCmp("strategyIdOrNameI101").getValue()
			};
		listDetail1.push(strDt2);
 		if(!Ext.isEmpty(Ext.getCmp('strategyTypeUII101').getValue())
 				&& Ext.getCmp('strategyTypeUII101').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.strategy_type',
 					value:Ext.getCmp("strategyTypeUII101").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		if(!Ext.isEmpty(Ext.getCmp('defaultFlagUII101').getValue())){
 			var strDt2 = {
 					columnId:'a.default_flag',
 					value:Ext.getCmp("defaultFlagUII101").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		var params={
 			strQuery:Ext.encode(listDetail1)
 		};
 		Ext.apply(Ext.getCmp('wms_defstrategyGridI101').getStore().proxy.extraParams,params);
 		Ext.getCmp('wms_defstrategyGridI101').getStore().removeAll();
 		Ext.getCmp('wms_defstrategyGridI101').getStore().reload();
  	 },
 	//选择一条策略加载对应的策略明细
 	editWms_defstrategyGridI101:function(){
 		var objData = Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();
 		if(objData.length!=0){
 			var listDetail1 = [];
 			var strdtl={
 					columnId:'a.strategy_type',
 					value:objData[0].get("strategyType")
 				};
 				listDetail1.push(strdtl);
			var strdtl={
				columnId:'a.strategy_id',
				value:objData[0].get("strategyId")
			};
			listDetail1.push(strdtl);
			var jsonDetail1 = Ext.encode(listDetail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
		Ext.apply(Ext.getCmp('wms_defstrategyDGridI101')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('wms_defstrategyDGridI101').getStore()
				.removeAll();
		Ext.getCmp('wms_defstrategyDGridI101').getStore()
				.load();
 		}
 		
 	},
 	//新增策略明细
 	detailAddI101_2:function()
	{
		g_varbuttonI101_2='add';
		var objData = Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();
		if(objData.length==0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.choseStrategyForAdd); 
	            return; 
		}else{
				Ext.create('cms.view.bdef.window.bdef_WmsDefstrategyDAddOrEditWindow',{
					title:$i18n.titleadd
				}).show();
				
				addWmsDefstrategyD();
				addCommMenu5('menuWidgetI101_2');
				Ext.getCmp('strategyTypeI101_2').setValue(objData[0].data.strategyType);
				Ext.getCmp('strategyIdI101_2').setValue(objData[0].data.strategyId);
				Ext.getCmp('ruleOrderI101').setValue($i18n_prompt.autogenerationWhenSave);
				Ext.getCmp('climmitMixbatchI101').setValue('0');
				Ext.getCmp('limmitMixarticleI101').setValue('0');
				Ext.getCmp('limmitMaxqtyI101').setValue('0');
				Ext.getCmp('limmitMaxcaseI101').setValue('0');
				Ext.getCmp('limmitMaxweightI101').setValue('0');
				Ext.getCmp('limmitMaxgroupnoI101').setValue('0');
				Ext.getCmp('limmitCelluseI101').setValue('0');
				commonSetMsterReadOnlyByArray(
						new Array('strategyTypeI101_2','strategyIdI101_2','ruleOrderI101'),true);
				Ext.getCmp('cmbRuleIdI101').focus(false, 3);
				bindEnterSkip($('#bdef_WmsDefstrategyDEditI101'));//调用键盘处理方法
				//加载则略明细窗口规则ID下拉
			    getRuleIdQuery();
			}
	},
	//策略明细-修改
	strategyDdetailEditClik:function(){
		var data=Ext.getCmp('wms_defstrategyDGridI101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_WmsDefstrategyDAddOrEditWindow',{
					title:$i18n.titleupdate//修改
			}).show();
			this.loadWmsDefstrategyDI101();
			commonMenu5PrevOrNext('menuWidgetI101_2','wms_defstrategyDGridI101',0);
			updateCommMenu5('menuWidgetI101_2');
			g_varbuttonI101_2='edit';
		}
	},
	//策略明细-删除
	strategyDdetailDeleteClik:function()
	{
		var objData = Ext.getCmp('wms_defstrategyDGridI101').getSelectionModel().getSelection();  
        if (objData.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {
        	var listDetail = [];
    		var strDtl = {
    				columnId:'a.strategy_type',
    				value:objData[0].get("strategyType")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.strategy_id',
    				value:objData[0].get("strategyId")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.rule_id',
    				value:objData[0].get("ruleId")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.rule_order',
    				value:objData[0].get("ruleOrder")	
    		};
    		listDetail.push(strDtl);
    		var jsonDetail = Ext.encode(listDetail);
        	Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        		if(button=='yes')
    			{
    				Ext.getCmp('wms_defstrategyDGridI101').getStore().remove(objData);
    				Ext.Ajax.request({
    					method:'POST',
    					url:'bdef_WmsDefstrategyAction_deleteStrategyD',
    					params :{ 
    						strQuery : jsonDetail
    					},
    					success : function(response) {
    						var data = Ext.decode(response.responseText);
    						if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('wms_defstrategyDGridI101').getStore().load();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							} 
    					}
    				});
    			}			
            });
        }
	},
	//策略明细-上移
	strategyDPrevButton:function(){
		this.strategyDModel(1);
	},
	//策略明细-下移
	strategyDNextButton:function(){
		this.strategyDModel(-1);
	},
	//策略明细-顺序移动
	strategyDModel:function(flag,rowindexI101){
		var data = Ext.getCmp('wms_defstrategyDGridI101').getSelectionModel().getSelection();
		if(data.length!=0){
			if(flag==1){
				rowindexI101=data[0].index-1;
			}else if(flag==-1){
				rowindexI101=data[0].index+1;
			}
			if(rowindexI101==-1){
				Ext.example.msg($i18n_prompt.prompt,'已是第一条数据');
				return;
			}else if(rowindexI101==Ext.getCmp('wms_defstrategyDGridI101').getStore().getCount()){
				Ext.example.msg($i18n_prompt.prompt,'已是最后一条数据');
				return;
			}else{
				hideFlag.push(data[0].get("strategyType"));
				hideFlag.push(data[0].get("strategyId"));
				hideFlag.push(data[0].get("ruleId"));
				var record=Ext.getCmp('wms_defstrategyDGridI101').getStore().getAt(rowindexI101-(Ext.getCmp('wms_defstrategyDGridI101').getStore().currentPage-1)*appConfig.getPageSize());
				ruleOrderFlag=record.data.ruleOrder;
				var params = 
				{
					strStrategyType:data[0].get("strategyType"),
					strStrategyId:data[0].get("strategyId"),
					strRuleOrder:record.data.ruleOrder,
					strRuleOrderChoice:data[0].get("ruleOrder"),
					flag:flag
				};
				Ext.Ajax.request({
					method:'POST',
					params:params,
					url:'bdef_WmsDefstrategyAction_ruleOrderMove',
					success:function(response)
					{
						data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.getCmp('wms_defstrategyDGridI101').getStore().load();
						}else{
							Ext.example.msg($i18n_prompt.prompt,data.msg);
						}			
					}
				});			
			}			
		}else{
			Ext.example.msg($i18n_prompt.prompt,'请选择要移动的数据');
		}
	},
	//策略明细窗口-新增
	strategyDWinAdd:function(){
		Ext.getCmp('cmbRuleIdI101').setValue('');
		Ext.getCmp('ruleOrderI101').setValue($i18n_prompt.autogenerationWhenSave);
		Ext.getCmp('climmitMixbatchI101').setValue('0');
		Ext.getCmp('limmitMixarticleI101').setValue('0');
		Ext.getCmp('limmitMaxqtyI101').setValue('0');
		Ext.getCmp('limmitMaxcaseI101').setValue('0');
		Ext.getCmp('limmitMaxweightI101').setValue('0');
		Ext.getCmp('limmitMaxgroupnoI101').setValue('0');
		Ext.getCmp('limmitCelluseI101').setValue('0');
		commonSetMsterReadOnlyByArray(
				new Array('ruleOrderI101','cmbRuleIdI101'),false);
		Ext.getCmp('cmbRuleIdI101').focus(false, 3);
		bindEnterSkip($('#bdef_WmsDefstrategyDEditI101'));//调用键盘处理方法
    },
	//策略明细-上一条记录
	strategyDPrev:function(){		
		commonMenu5PrevOrNext('menuWidgetI101_2','wms_defstrategyDGridI101',-1);
		this.loadWmsDefstrategyDI101();
	},
	//策略明细-下一条记录
	strategyDNext:function(){
		commonMenu5PrevOrNext('menuWidgetI101_2','wms_defstrategyDGridI101',1);
		this.loadWmsDefstrategyDI101();
	},
	//策略明细-关闭窗口
	strategyDColse:function(){
		Ext.getCmp('bdef_WmsDefstrategyDAddOrEditWindow').close();
		Ext.getCmp('wms_defstrategyDGridI101').getStore().reload();
	},
	//策略明细-填充数据
	loadWmsDefstrategyDI101:function(){
		var data=Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();
		var rec=Ext.getCmp('wms_defstrategyDGridI101').getSelectionModel().getSelection();
		if(rec.length>0){
			Ext.getCmp('strategyTypeI101_2').setValue(data[0].data.strategyType);
			Ext.getCmp('strategyIdI101_2').setValue(data[0].data.strategyId);
			
			Ext.getCmp('cmbRuleIdI101').getStore().removeAll();
			Ext.getCmp('cmbRuleIdI101').getStore().add({
		    	value:rec[0].data.ruleId,
		    	dropValue:"["+rec[0].data.ruleId+"]"+rec[0].data.ruleName,
		    	text:rec[0].data.ruleName
		    });
			Ext.getCmp('cmbRuleIdI101').setValue(rec[0].data.ruleId);
			Ext.getCmp('ruleOrderI101').setValue(rec[0].data.ruleOrder);
			Ext.getCmp('climmitMixbatchI101').setValue(rec[0].data.limmitMixbatch);
			Ext.getCmp('limmitMixarticleI101').setValue(rec[0].data.limmitMixarticle);
			Ext.getCmp('limmitMaxqtyI101').setValue(rec[0].data.limmitMaxqty);
			Ext.getCmp('limmitMaxcaseI101').setValue(rec[0].data.limmitMaxcase);
			Ext.getCmp('limmitMaxweightI101').setValue(rec[0].data.limmitMaxweight);
			Ext.getCmp('limmitMaxgroupnoI101').setValue(rec[0].data.limmitMaxgroupno);
			Ext.getCmp('limmitCelluseI101').setValue(rec[0].data.limmitCelluse);
			Ext.getCmp('memoI101').setValue(rec[0].data.memo);

			commonSetMsterReadOnlyByArray(
					new Array('strategyTypeI101_2','strategyIdI101_2','cmbRuleIdI101','ruleOrderI101'),true);
			Ext.getCmp('climmitMixbatchI101').focus(false, 5);
		}
	},
	//策略明细-保存
	strategyDSave:function(){
		
		if(!commonCheckIsInputAll('bdef_WmsDefstrategyDEditI101')){
			return;
		}
		
		if(Ext.getCmp('bdef_WmsDefstrategyDEditI101').getForm().isValid()){
			var rec=Ext.getCmp('wms_defstrategyDGridI101').getSelectionModel().getSelection()[0];
			var ruleOrder='';
			if(Ext.getCmp('ruleOrderI101').getValue()==$i18n_prompt.autogenerationWhenSave){
				ruleOrder=null;
			}else{
				ruleOrder=Ext.getCmp('ruleOrderI101').getValue();
			}
			strategyDStr={
				id:{
					strategyType:Ext.getCmp('strategyTypeI101_2').getValue(),
					strategyId:Ext.getCmp('strategyIdI101_2').getValue(),
					ruleOrder:ruleOrder
				},	
				ruleId:Ext.getCmp('cmbRuleIdI101').getValue(),
				limmitMaxqty:Ext.getCmp('limmitMaxqtyI101').getValue(),
				limmitMixbatch:Ext.getCmp('climmitMixbatchI101').getValue(),
				limmitMixarticle:Ext.getCmp('limmitMixarticleI101').getValue(),
				limmitMaxcase:Ext.getCmp('limmitMaxcaseI101').getValue(),
				limmitMaxweight:Ext.getCmp('limmitMaxweightI101').getValue(),
				limmitCelluse:Ext.getCmp('limmitCelluseI101').getValue(),
				limmitMaxgroupno:Ext.getCmp('limmitMaxgroupnoI101').getValue(),
				limmitRsv01:Ext.getCmp('limmitRsv01').getValue()==undefined? '0':Ext.getCmp('limmitRsv01').getValue(),
				limmitRsv02:Ext.getCmp('limmitRsv02').getValue()==undefined? '0':Ext.getCmp('limmitRsv02').getValue(),
				limmitRsv03:Ext.getCmp('limmitRsv03').getValue()==undefined? '0':Ext.getCmp('limmitRsv03').getValue(),
				limmitRsv04:Ext.getCmp('limmitRsv04').getValue()==undefined? '0':Ext.getCmp('limmitRsv04').getValue(),
				limmitRsv05:Ext.getCmp('limmitRsv05').getValue()==undefined? '0':Ext.getCmp('limmitRsv05').getValue(),
				limmitRsv06:Ext.getCmp('limmitRsv06').getValue()==undefined? '0':Ext.getCmp('limmitRsv06').getValue(),
				memo:Ext.getCmp('memoI101').getValue(),
				rgstDate:g_varbuttonI101_2=='add'?new Date():rec.data.rgstDate,
				rgstName:g_varbuttonI101_2=='add'?Ext.get('workerNo').getValue():rec.data.rgstName,
				updtDate:g_varbuttonI101_2=='add'?'':new Date(),
				updtName:g_varbuttonI101_2=='add'?'':Ext.get('workerNo').getValue()
			};								
			Ext.Ajax.request({
				url:'bdef_WmsDefstrategyAction_saveStrategyD',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(strategyDStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						if(Ext.getCmp('ruleOrderI101').getValue()==$i18n_prompt.autogenerationWhenSave){
							Ext.getCmp('ruleOrderI101').setValue(data.obj);
						}
						commonSetMsterReadOnlyByArray(
								new Array('cmbRuleIdI101'),true);
						Ext.example.msg($i18n.prompt,data.msg);

					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
		}
	},
	getHideFlag:function(){
  		return hideFlag;
  	 },
  	getRuleOrderFlag:function(){
  		return ruleOrderFlag;
  	 }
  	
 });
 
//新增策略头档初始化
function addWmsDefstrategy(){
	Ext.getCmp('bdef_WmsDefstrategyAddOrEditI101').getForm().reset();
}
//新增策略明细初始化
function addWmsDefstrategyD(){
	Ext.getCmp('bdef_WmsDefstrategyDEditI101').getForm().reset();
}
//加载则略明细窗口规则ID下拉
function getRuleIdQuery(){
	var data = Ext.getCmp('wms_defstrategyGridI101').getSelectionModel().getSelection();
	var listDetail1  =  [];
	var listDetail2  =  [];
	var strDtl = {
		columnId:'a.strategy_type',
		value:data[0].data.strategyType
	};
	listDetail1.push(strDtl);
	listDetail2.push(strDtl);
	var strDtl = {
			columnId:'a.strategy_id',
			value:data[0].data.strategyId
		};
	listDetail2.push(strDtl);
	var wheresql={
			strQuery:Ext.encode(listDetail1),
			str:Ext.encode(listDetail2)
		};
	Ext.apply(Ext.getCmp('cmbRuleIdI101').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('cmbRuleIdI101').getStore().removeAll();
	Ext.getCmp('cmbRuleIdI101').getStore().load();
}



