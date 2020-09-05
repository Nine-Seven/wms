/* 模块名称：出货定位策略配置头档
 * 模块编码：I802
 * 作者： liucl 20160801
 */
 var rowindexI802=0;
 var g_varbuttonI802 = '';
 var g_varbuttonI802_2 = '';
 var hideFlag=[];
 var ruleOrderFlag='';
 Ext.define('cms.controller.odata.odata_outLocateStrategyController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.odata.odata_outLocateStrategyUI',
 	'cms.view.odata.window.odata_outLocateStrategyAddOrEditWindow',
 	'cms.view.odata.window.odata_outLocateStrategyDAddOrEditWindow'
 	],
 	model:'',
 	store:'',
 	init:function(){
 		 this.control(//新增策略头档
 	 		{'odata_outLocateStrategyUI commMenuWidget2[id=menuI802] button[name=detailAdd]':{
				click:this.detailAdd
			},//策略头档-删除
			'odata_outLocateStrategyUI commMenuWidget2[id=menuI802] button[name=detailDelete]':{
				click:this.strategydetailDeleteClik  
			},//策略头档-修改
			'odata_outLocateStrategyUI commMenuWidget2[id=menuI802] button[name=detailEdit]':{
				click:this.detailEditClik  
			},//策略头档-查找
			'odata_outLocateStrategyUI button[name=detailQuery]':{
				click:this.strategyQuery
			},//策略头档窗口-->新增
			'odata_outLocateStrategyAddOrEditWindow button[name=add]':{
				click:this.strategyWinAdd
			},//策略头档窗口-上一条记录
			'odata_outLocateStrategyAddOrEditWindow button[name=prev]':{
				click:this.strategyPrev
			},//策略头档窗口-下一条记录
			'odata_outLocateStrategyAddOrEditWindow button[name=next]':{
				click:this.strategyNext
			},//策略头档窗口-关闭窗口
			'odata_outLocateStrategyAddOrEditWindow button[name=close]':{
				click:this.strategyColse
			},//策略头档窗口-保存
			'odata_outLocateStrategyAddOrEditWindow button[name=save]':{
				click:this.strategySave
			},//策略名选择
			'wms_DefFieldValCombo[id=strategyNameUII802]':{    // 要改
				select:this.strategyNameUISelect
			},//默认标识选择
			'wms_DefFieldValCombo[id=defaultFlagUII802]':{    // 要改
				select:this.defaultFlagUISelect
			},//策略id       
			'remoteCombo[id=locateStrategyIdUII802]':{   // 要改
				beforequery:this.locateStrategyIdBeforeQuery,
				select:this.strategyIdSelect
			},//点击头表记录，加载头表对应的详细
 			'odata_outLocateStrategyUI grid[id=odata_WmsOutlocateStrategyGridI802]':{
 				selectionchange:this.editLocateStrategyGridI802
 			},//新增策略明细
 			'odata_outLocateStrategyUI commMenuWidget6[id=menu_I802_2] button[name=detailAdd]':{
	    		click:this.detailAddI802_2
	    	},//策略明细-修改
			'odata_outLocateStrategyUI commMenuWidget6[id=menu_I802_2] button[name=detailEdit]':{
				click:this.strategyDdetailEditClik  
			},//策略明细-删除
			'odata_outLocateStrategyUI commMenuWidget6[id=menu_I802_2] button[name=detailDelete]':{
				click:this.strategyDdetailDeleteClik  
			},//策略明细-上移
			'odata_outLocateStrategyUI commMenuWidget6[id=menu_I802_2] button[name=prevButton]':{
				click:this.strategyDPrevButton
			},//策略明细-下移
			'odata_outLocateStrategyUI commMenuWidget6[id=menu_I802_2] button[name=nextButton]':{
				click:this.strategyDNextButton
			},//策略明细窗口-->新增
			'odata_outLocateStrategyDAddOrEditWindow button[name=add]':{
				click:this.strategyDWinAdd
			},//策略明细-上一条记录
			'odata_outLocateStrategyDAddOrEditWindow button[name=prev]':{
				click:this.strategyDPrev
			},//策略明细-下一条记录
			'odata_outLocateStrategyDAddOrEditWindow button[name=next]':{
				click:this.strategyDNext
			},//策略明细-关闭窗口
			'odata_outLocateStrategyDAddOrEditWindow button[name=close]':{
				click:this.strategyDColse
			},//策略明细-保存
			'odata_outLocateStrategyDAddOrEditWindow button[name=save]':{
				click:this.strategyDSave
			}
 		    
 	    });
 	},/*  之前没有问题，需要修改三个搜索框所对应的部分*/
 	
 	//新增策略头档(成功)
 	detailAdd:function()
		{ 
		Ext.create('cms.view.odata.window.odata_outLocateStrategyAddOrEditWindow',{
			title:$i18n.titleadd   //指定窗口名
		}).show();
		addoutLocateStrategy();   //新增策略头档初始化
		//控制菜单显示项
		addCommMenu5('menuWidgetI802_1');//CommMenu5,之前UI里面引入的Menu组件
		//Ext.getCmp('cmbStrategyTypeI802').focus(false, 2);
		bindEnterSkip($('#odata_outLocateStrategyAddOrEditI802'));//调用键盘处理方法
		g_varbuttonI802='add';
		Ext.getCmp('cmbDefaultFlagI802').setValue('1');//默认值设为1
		//Ext.getCmp('enterpriseNoI802').setValue('8888');//默认值设为8888
		Ext.getCmp('locateStrategyIdI802').setValue($i18n_prompt.autogenerationWhenSave);//$i18n_prompt.autogenerationWhenSave 保存时自动生成
		commonSetMsterReadOnlyByArray(
				new Array('locateStrategyIdI802'),true);
    },
    //策略头档-修改(改动)
    detailEditClik:function(){
		var data=Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.odata.window.odata_outLocateStrategyAddOrEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			this.loadoutLocateStrategyI802();
			commonMenu5PrevOrNext('menuWidgetI802_1','odata_WmsOutlocateStrategyGridI802',0);
			updateCommMenu5('menuWidgetI802_1');
			g_varbuttonI802='edit';
		}
	},
	//策略头档-删除(cg)
	strategydetailDeleteClik:function()
	{
		var objData = Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();  
        if (objData.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {
        	var listDetail = [];
    		var strDtl = {
    				columnId:'a.enterprise_no',
    				value:objData[0].get("enterpriseNo")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.locate_strategy_id',
    				value:objData[0].get("locateStrategyId")	
    		};
    		listDetail.push(strDtl);
    		var jsonDetail = Ext.encode(listDetail);
        	Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        		if(button=='yes')
    			{
    				Ext.Ajax.request({
    					method:'POST',
    					url:'Odata_outLocateStrategyAction_deleteStrategy',
    					params :{ 
    						strQuery : jsonDetail
    					},
    					success : function(response) {
    						var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().load();
								Ext.getCmp('locateStrategyIdUII802').getStore().removeAll();
						 		Ext.getCmp('locateStrategyIdUII802').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}  	
						}
    				});
    			}			
            });
        }
	},
	//策略头档-查找 (已修改 三个搜索框的)
	strategyQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='I802';
		queryGrid='odata_WmsOutlocateStrategyGridI802';
		Ext.getCmp('strategyNameUII802').setValue('');
		Ext.getCmp('defaultFlagUII802').setValue('');
		Ext.getCmp('locateStrategyIdUII802').setValue('');
	},
	//策略头档窗口-新增(cg)
	strategyWinAdd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('strategyNameI802'),false);
	    bindEnterSkip($('#bdef_DefCarTypeAddOrEditForm'));//调用键盘处理方法
	    addoutLocateStrategy();   //新增策略头档初始化
	    Ext.getCmp('locateStrategyIdI802').setValue($i18n_prompt.autogenerationWhenSave);
		g_varbuttonI802='add';
		Ext.getCmp('enterpriseNoI802').setValue('8888');//默认值设为8888
		Ext.getCmp('cmbDefaultFlagI802').setValue('1');
    },
	//策略头档-上一条记录 (已修改)
	strategyPrev:function(){		
		commonMenu5PrevOrNext('menuWidgetI802_1','odata_WmsOutlocateStrategyGridI802',-1);
		this.loadoutLocateStrategyI802();
	},
	//策略头档-下一条记录(已修改)
	strategyNext:function(){
		commonMenu5PrevOrNext('menuWidgetI802_1','odata_WmsOutlocateStrategyGridI802',1);
		this.loadoutLocateStrategyI802();
	},
	//策略头档-关闭窗口(cg)
	strategyColse:function(){
		Ext.getCmp('odata_outLocateStrategyAddOrEditWindow').close();
		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().reload();
	},
	
	//策略头档-填充数据    (cg)
	loadoutLocateStrategyI802:function(){
		var rec=Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();
		if(rec.length>0){//里面的Id貌似是窗口里的
			Ext.getCmp('locateStrategyIdI802').setValue(rec[0].data.locateStrategyId);
			Ext.getCmp('strategyNameI802').setValue(rec[0].data.strategyName);
			Ext.getCmp('cmbDefaultFlagI802').setValue(rec[0].data.defalutFlag);
			commonSetMsterReadOnlyByArray(
					new Array('locateStrategyIdI802'),true);//主键存在
			Ext.getCmp('strategyNameI802').focus(false, 2);
		}
	},
	
	//策略头档-保存 (cg)
	strategySave:function(){
		
		if(!commonCheckIsInputAll('odata_outLocateStrategyAddOrEditI802')){
			return;
		}
		
		if(Ext.getCmp('odata_outLocateStrategyAddOrEditI802').getForm().isValid()){
			var rec=Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection()[0];
			var locateStrategyId='';
			if(Ext.getCmp('locateStrategyIdI802').getValue()==$i18n_prompt.autogenerationWhenSave){
				locateStrategyId=null;
			}else{
				locateStrategyId=Ext.getCmp('locateStrategyIdI802').getValue();
			}
			strategyDStr={
				id:{
					locateStrategyId:locateStrategyId,
					//enterpriseNo:Ext.getCmp('enterpriseNoI802').getValue()
					enterpriseNo:Ext.get('enterpriseNo').getValue()//由获取从编辑面板传来的企业编号，改为获取用户自带的
				},	
				strategyName:Ext.getCmp('strategyNameI802').getValue(),
				defalutFlag:Ext.getCmp('cmbDefaultFlagI802').getValue(),
				rgstDate:g_varbuttonI802=='add'?new Date():rec.data.rgstDate,
				rgstName:g_varbuttonI802=='add'?Ext.get('workerNo').getValue():rec.data.rgstName
			};								
			Ext.Ajax.request({
				url:'Odata_outLocateStrategyAction_saveStrategy',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(strategyDStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						if(Ext.getCmp('locateStrategyIdI802').getValue()==$i18n_prompt.autogenerationWhenSave){
							Ext.getCmp('locateStrategyIdI802').setValue(data.obj);
						}
						commonSetMsterReadOnlyByArray(
								new Array('locateStrategyIdI802'),true);
						Ext.example.msg($i18n.prompt,data.msg);
						
				 		Ext.getCmp('locateStrategyIdUII802').getStore().removeAll();
				 		Ext.getCmp('locateStrategyIdUII802').getStore().reload();
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
		}
	},
	//定位策略名选择(cg )
	strategyNameUISelect:function(){
  		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('strategyNameUII802').getValue())
 				&& Ext.getCmp('strategyNameUII802').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.strategy_name',
 					value:Ext.getCmp("strategyNameUII802").getValue()
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
 		Ext.getCmp('defaultFlagUII802').setValue('');
 		Ext.apply(Ext.getCmp('defaultFlagUII802').getStore().proxy.extraParams,params);
 		Ext.getCmp('defaultFlagUII802').getStore().removeAll();
 		Ext.getCmp('defaultFlagUII802').getStore().reload();
 		//策略id下拉加载
 		Ext.getCmp('locateStrategyIdUII802').setValue('');
 		Ext.apply(Ext.getCmp('locateStrategyIdUII802').getStore().proxy.extraParams,wheresql);
 		Ext.getCmp('locateStrategyIdUII802').getStore().removeAll();
 		Ext.getCmp('locateStrategyIdUII802').getStore().reload();
 		//策略头档列表加载
 		Ext.apply(Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().proxy.extraParams,params);
 		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().removeAll();
 		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().reload();
    },
  	//默认标识选择 (cg)
  	defaultFlagUISelect:function(){
  		var listDetail1  =  [];
  		var strDt2 = {
				columnId:'a.defalut_flag',
				value:Ext.getCmp("defaultFlagUII802").getValue()
		};
		listDetail1.push(strDt2);
 		if(!Ext.isEmpty(Ext.getCmp('locateStrategyIdUII802').getValue())
 				&& Ext.getCmp('locateStrategyIdUII802').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.locate_strategy_id',
 					value:Ext.getCmp("locateStrategyIdUII802").getValue()
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
 		//策略名称下拉加载
 		Ext.getCmp('strategyNameUII802').setValue('');
 		Ext.apply(Ext.getCmp('strategyNameUII802').getStore().proxy.extraParams,wheresql);
 		Ext.getCmp('strategyNameUII802').getStore().removeAll();
 		Ext.getCmp('strategyNameUII802').getStore().reload();
 		//策略头档列表加载
 		Ext.apply(Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().proxy.extraParams,params);
 		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().removeAll();
 		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().reload();
  	},
	//策略id加载(修改中)
  	locateStrategyIdBeforeQuery:function(){
 		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('strategyNameUII802').getValue()) 
				&& Ext.getCmp('strategyNameUII802').getValue() !='ALL'){
			var strDt2 = {
					columnId:'a.strategy_name',
					value:Ext.getCmp("strategyNameUII802").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('defaultFlagUII802').getValue())){
			var strDt2 = {
					columnId:'a.defalut_flag',
					value:Ext.getCmp("defaultFlagUII802").getValue()
				};
				listDetail1.push(strDt2);
		}
		var params={
			str:Ext.getCmp("locateStrategyIdUII802").getValue(),   
			strQuery:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('locateStrategyIdUII802').getStore().proxy.extraParams,params);
		Ext.getCmp('locateStrategyIdUII802').getStore().removeAll();
		Ext.getCmp('locateStrategyIdUII802').getStore().load();
 	 },
 	//策略id选择
 	strategyIdSelect:function(){
  		var listDetail1  =  [];
  		var strDt2 = {
				columnId:'locate_strategy_id',
				value:Ext.getCmp("locateStrategyIdUII802").getValue()
			};
		listDetail1.push(strDt2);
 		if(!Ext.isEmpty(Ext.getCmp('strategyNameUII802').getValue())
 				&& Ext.getCmp('strategyNameUII802').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'strategy_name',
 					value:Ext.getCmp("strategyNameUII802").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		if(!Ext.isEmpty(Ext.getCmp('defaultFlagUII802').getValue())){
 			var strDt2 = {
 					columnId:'defalut_flag',
 					value:Ext.getCmp("defaultFlagUII802").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		var params={
 			strQuery:Ext.encode(listDetail1)
 		};
 		Ext.apply(Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().proxy.extraParams,params);
 		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().removeAll();
 		Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getStore().reload();
  	 },
 	//选择一条策略加载对应的策略明细 (cg)
 	editLocateStrategyGridI802:function(){
 		var objData = Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();
 		if(objData.length!=0){
 			var listDetail1 = [];
 			//var strdtl={
 			//		columnId:'a.strategy_type',
 			//		value:objData[0].get("strategyType")
 			//	};
 			//	listDetail1.push(strdtl);
			var strdtl={
				columnId:'a.locate_strategy_id',
				value:objData[0].get("locateStrategyId")
			};
			listDetail1.push(strdtl);
			var jsonDetail1 = Ext.encode(listDetail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
		Ext.apply(Ext.getCmp('odata_WmsOutlocateStrategyDGridI802')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore()
				.removeAll();
		Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore()
				.load();
 		}
 		
 	},
 	//新增策略明细   (cg)
 	detailAddI802_2:function()
	{
 		
		g_varbuttonI802_2='add';
		var objData = Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();
		if(objData.length==0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.choseStrategyForAdd); 
	            return; 
		}else{
				Ext.create('cms.view.odata.window.odata_outLocateStrategyDAddOrEditWindow',{
					title:$i18n.titleadd
				}).show();
				
				addWmsDefstrategyD();
				addCommMenu5('menuWidgetI802_2');
				//Ext.getCmp('enterpriseNoI802_2').setValue(objData[0].data.enterpriseNo);
				Ext.getCmp('locateStrategyIdI802_2').setValue(objData[0].data.locateStrategyId);
				Ext.getCmp('cmblocateRuleNameI802').setValue(objData[0].data.strategyName);
				
				Ext.getCmp('locateRuleIdI802_2').setValue('');
				//Ext.getCmp('cmblocateRuleNameI802').setValue('0');
				Ext.getCmp('deliverObjLevelI802').setValue('0');
				Ext.getCmp('PFlagI802').setValue('0');
				Ext.getCmp('MFlagI802').setValue('0');
				Ext.getCmp('WFlagI802').setValue('0');
				Ext.getCmp('SFlagI802').setValue('0');
				Ext.getCmp('DFlagI802').setValue('0');
				//Ext.getCmp('BDivideFlagI802').setValue('0');
				//Ext.getCmp('CDivideFlagI802').setValue('0');
				Ext.getCmp('shortqtyTypeI802').setValue('1');
				Ext.getCmp('commitTypeI802').setValue('0');
				commonSetMsterReadOnlyByArray(
						new Array('enterpriseNoI802_2','locateStrategyIdI802_2','cmblocateRuleNameI802'),true);
				Ext.getCmp('locateRuleIdI802_2').focus(false, 2);
				bindEnterSkip($('#odata_outLocateStrategyDEditI802'));//调用键盘处理方法
				//加载则略明细窗口规则ID下拉
			    getRuleIdQuery();
			}
	},
	//策略明细-修改(有问题)
	strategyDdetailEditClik:function(){
		var data=Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.odata.window.odata_outLocateStrategyDAddOrEditWindow',{
					title:$i18n.titleupdate//修改
			}).show();
			this.loadoutLocateStrategyDI802();
			
			commonMenu5PrevOrNext('menuWidgetI802_2','odata_WmsOutlocateStrategyDGridI802',0);
			updateCommMenu5('menuWidgetI802_2');
			g_varbuttonI802_2='edit';
		}
	},
	//策略明细-删除
	strategyDdetailDeleteClik:function()
	{
		var objData = Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getSelectionModel().getSelection();  
        if (objData.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {
        	var listDetail = [];
    		var strDtl = {
    				columnId:'a.locate_strategy_id',
    				value:objData[0].get("locateStrategyId")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.enterprise_no',
    				value:objData[0].get("enterpriseNo")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.locate_rule_id',
    				value:objData[0].get("locateRuleId")	
    		};
    		//listDetail.push(strDtl);
    		//var strDtl = {
    		//		columnId:'a.rule_order',
    		//		value:objData[0].get("ruleOrder")	
    		//};
    		listDetail.push(strDtl);
    		var jsonDetail = Ext.encode(listDetail);
        	Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        		if(button=='yes')
    			{
    				Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore().remove(objData);
    				Ext.Ajax.request({
    					method:'POST',
    					url:'Odata_outLocateStrategyAction_deleteStrategyD',
    					params :{ 
    						strQuery : jsonDetail
    					},
    					success : function(response) {
    						var data = Ext.decode(response.responseText);
    						if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore().load();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							} 
    					}
    				});
    			}			
            });
        }
	},

	//策略明细窗口-新增(cg)
	strategyDWinAdd:function(){
		//Ext.getCmp('enterpriseNoI802_2').setValue('8888');
		//Ext.getCmp('locateStrategyIdI802_2').setValue('');
		//Ext.getCmp('cmblocateRuleNameI802').setValue('');
		Ext.getCmp('locateRuleIdI802_2').setValue('');
		Ext.getCmp('deliverObjLevelI802').setValue('0');
		Ext.getCmp('PFlagI802').setValue('0');
		Ext.getCmp('MFlagI802').setValue('0');
		Ext.getCmp('WFlagI802').setValue('0');
		Ext.getCmp('SFlagI802').setValue('0');
		Ext.getCmp('DFlagI802').setValue('0');
		Ext.getCmp('shortqtyTypeI802').setValue('1');
		Ext.getCmp('commitTypeI802').setValue('0');
		commonSetMsterReadOnlyByArray(
				new Array('locateRuleIdI802_2'),false);
		Ext.getCmp('locateRuleIdI802_2').focus(false, 1);
		//Ext.getCmp('memoI802').setValue('');
		bindEnterSkip($('#odata_outLocateStrategyDEditI802'));//调用键盘处理方法
		
    },
	//策略明细-上一条记录
	strategyDPrev:function(){		
		commonMenu5PrevOrNext('menuWidgetI802_2','odata_WmsOutlocateStrategyDGridI802',-1);
		this.loadOutlocateStrategyDI802();
	},
	//策略明细-下一条记录
	strategyDNext:function(){
		commonMenu5PrevOrNext('menuWidgetI802_2','odata_WmsOutlocateStrategyDGridI802',1);
		this.loadOutlocateStrategyDI802();
	},
	//策略明细-关闭窗口
	strategyDColse:function(){
		Ext.getCmp('odata_outLocateStrategyDAddOrEditWindow').close();
		Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getStore().reload();
	},
	//策略明细-填充数据 (cg)
	loadoutLocateStrategyDI802:function(){
		debugger;
		var data=Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();
		//var data=Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getSelectionModel().getSelection();
		var rec=Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getSelectionModel().getSelection();
		if(rec.length>0){
		
			Ext.getCmp('locateStrategyIdI802_2').setValue(data[0].data.locateStrategyId);
			
			//Ext.getCmp('locateRuleIdI802_2').getStore().removeAll();
			//Ext.getCmp('locateRuleIdI802_2').getStore().add({
		    //	value:rec[0].data.locateRuleId,
		    //	dropValue:"["+rec[0].data.locateRuleId+"]"+rec[0].data.locateRuleName,
		    //	text:rec[0].data.locateRuleName
		    //});
			Ext.getCmp('locateRuleIdI802_2').setValue(rec[0].data.locateRuleId);
			Ext.getCmp('cmblocateRuleNameI802').setValue(rec[0].data.locateRuleName);
			Ext.getCmp('deliverObjLevelI802').setValue(rec[0].data.deliverObjLevel);
			Ext.getCmp('PFlagI802').setValue(rec[0].data.PFlag);
			Ext.getCmp('MFlagI802').setValue(rec[0].data.MFlag);
			Ext.getCmp('WFlagI802').setValue(rec[0].data.WFlag);
			Ext.getCmp('SFlagI802').setValue(rec[0].data.SFlag);
			Ext.getCmp('DFlagI802').setValue(rec[0].data.DFlag);
			//Ext.getCmp('BDivideFlagI802').setValue(rec[0].data.BDivideFlag);
			//Ext.getCmp('CDivideFlagI802').setValue(rec[0].data.CDivideFlag);
			Ext.getCmp('shortqtyTypeI802').setValue(rec[0].data.shortqtyType);
			Ext.getCmp('commitTypeI802').setValue(rec[0].data.commitType);

			commonSetMsterReadOnlyByArray(
					new Array('locateStrategyIdI802_2','locateRuleIdI802_2'),true);
			Ext.getCmp('cmblocateRuleNameI802').focus(false, 3);
			
		}
	},
	//策略明细-保存(已修改)
	strategyDSave:function(){
		
		if(!commonCheckIsInputAll('odata_outLocateStrategyDAddOrEditWindowI802')){
			return;
		}
		
		if(Ext.getCmp('odata_outLocateStrategyDAddOrEditWindowI802').getForm().isValid()){
			var rec=Ext.getCmp('odata_WmsOutlocateStrategyDGridI802').getSelectionModel().getSelection()[0];
			var locateStrategyId='';
			//if(Ext.getCmp('locateStrategyIdI802_2').getValue()){
			//	locateStrategyId=null;//==$i18n_prompt.autogenerationWhenSave
			//}else{
			//	locateStrategyId=Ext.getCmp('locateStrategyIdI802_2').getValue();
			//}
			
			strategyDStr={
				id:{
					locateStrategyId:Ext.getCmp('locateStrategyIdI802_2').getValue(),
					//locateStrategyId:locateStrategyId,
					//enterpriseNo:Ext.getCmp('enterpriseNoI802_2').getValue(),
					enterpriseNo:Ext.get('enterpriseNo').getValue(),//由获取从编辑面板传来的企业编号，改为获取用户自带的
					locateRuleId:Ext.getCmp('locateRuleIdI802_2').getValue()
				},	
				locateRuleName:Ext.getCmp('cmblocateRuleNameI802').getValue(),
				deliverObjLevel:Ext.getCmp('deliverObjLevelI802').getValue(),
				PFlag:Ext.getCmp('PFlagI802').getValue(),
				MFlag:Ext.getCmp('MFlagI802').getValue(),
				WFlag:Ext.getCmp('WFlagI802').getValue(),
				SFlag:Ext.getCmp('SFlagI802').getValue(),
				DFlag:Ext.getCmp('DFlagI802').getValue(),
				commitType:Ext.getCmp('commitTypeI802').getValue(),
				shortqtyType:Ext.getCmp('shortqtyTypeI802').getValue(),
				//memo:Ext.getCmp('memoI802').getValue(),
				rgstDate:g_varbuttonI802_2=='add'?new Date():rec.data.rgstDate,
				rgstName:g_varbuttonI802_2=='add'?Ext.get('workerNo').getValue():rec.data.rgstName,
				updtDate:g_varbuttonI802_2=='add'?'':new Date(),
				updtName:g_varbuttonI802_2=='add'?'':Ext.get('workerNo').getValue()
			};								
			Ext.Ajax.request({
				url:'Odata_outLocateStrategyAction_saveStrategyD',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(strategyDStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						if(Ext.getCmp('locateStrategyIdI802_2').getValue()==$i18n_prompt.autogenerationWhenSave){
							Ext.getCmp('locateStrategyIdI802_2').setValue(data.obj);
						}
						commonSetMsterReadOnlyByArray(
								new Array('locateRuleIdI802_2'),true);
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
 
//新增策略头档初始化（已修改）
function addoutLocateStrategy(){  //注意，这个odata_outLocateStrategyAddOrEditWindowI802，就是odata_outLocateStrategyAddOrEditWindow的 ID
	Ext.getCmp('odata_outLocateStrategyAddOrEditI802').getForm().reset();
}               
//新增策略明细初始化(修改)
function addWmsDefstrategyD(){
	Ext.getCmp('odata_outLocateStrategyDAddOrEditWindowI802').getForm().reset();
}
//加载则略明细窗口规则ID下拉(已修改)
function getRuleIdQuery(){
	var data = Ext.getCmp('odata_WmsOutlocateStrategyGridI802').getSelectionModel().getSelection();
	var listDetail1  =  [];
	var listDetail2  =  [];
	var strDtl = {
		columnId:'a.locate_rule_id',
		value:data[0].data.locateRuleId
	};
	listDetail1.push(strDtl);
	listDetail2.push(strDtl);
	var strDtl = {
			columnId:'a.locate_strategy_id',
			value:data[0].data.strategyId
		};
	listDetail2.push(strDtl);
	var wheresql={
			strQuery:Ext.encode(listDetail1),
			str:Ext.encode(listDetail2)
		};
	Ext.apply(Ext.getCmp('locateRuleIdI802_2').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('locateRuleIdI802_2').getStore().removeAll();
	Ext.getCmp('locateRuleIdI802_2').getStore().load();
}



