/* 模块名称：复核策略配置
 * 模块编码：I804
 * 作者： liucl 20160813
 */
 var rowindexI804=0;
 var g_varbuttonI804 = '';
 var hideFlag=[];
 var ruleOrderFlag='';
 Ext.define('cms.controller.odata.odata_CheckStrategyController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.odata.odata_CheckStrategyUI',
 	'cms.view.odata.window.odata_CheckStrategyAddOrEditWindow'
 	],
 	model:'',
 	store:'',
 	init:function(){
 		 this.control(//新增策略头档
 	 		{'odata_CheckStrategyUI commMenuWidget2[id=menuI804] button[name=detailAdd]':{
				click:this.detailAdd
			},//策略头档-删除
			'odata_CheckStrategyUI commMenuWidget2[id=menuI804] button[name=detailDelete]':{
				click:this.strategydetailDeleteClik  
			},//策略头档-修改
			'odata_CheckStrategyUI commMenuWidget2[id=menuI804] button[name=detailEdit]':{
				click:this.detailEditClik  
			},//策略头档-查找
			'odata_CheckStrategyUI button[name=detailQuery]':{
				click:this.strategyQuery
			},//策略头档窗口-->新增
			'odata_CheckStrategyAddOrEditWindow button[name=add]':{
				click:this.strategyWinAdd
			},//策略头档窗口-上一条记录
			'odata_CheckStrategyAddOrEditWindow button[name=prev]':{
				click:this.strategyPrev
			},//策略头档窗口-下一条记录
			'odata_CheckStrategyAddOrEditWindow button[name=next]':{
				click:this.strategyNext
			},//策略头档窗口-关闭窗口
			'odata_CheckStrategyAddOrEditWindow button[name=close]':{
				click:this.strategyColse
			},//策略头档窗口-保存
			'odata_CheckStrategyAddOrEditWindow button[name=save]':{
				click:this.strategySave
			},//策略类型选择
			'wms_DefFieldValCombo[id=checkTypeUII804]':{    // 要改
				select:this.checkTypeUISelect
			},//复核级别选择
			'wms_DefFieldValCombo[id=checkLevelUII804]':{    // 要改
				select:this.checkLevelUISelect
			},//策略id/名称       
			'remoteCombo[id=checkStrategyIdAndNameUII804]':{   // 要改
				beforequery:this.locateStrategyIdBeforeQuery,
				select:this.checkStrategyIdAndNameSelect
			}
 		    
 	    });
 	},
 	
 	//新增策略头档(cg)
 	detailAdd:function()
		{ 
		Ext.create('cms.view.odata.window.odata_CheckStrategyAddOrEditWindow',{
			//title:$i18n.titleadd   //指定窗口名
		}).show();
		addoutLocateStrategy();   //新增策略头档初始化
		//控制菜单显示项
		addCommMenu5('menuWidgetI804_1');//里面填odata_CheckStrategyAddOrEditWindow窗体里面的
		//Ext.getCmp('cmbStrategyTypeI804').focus(false, 2);
		bindEnterSkip($('#odata_CheckStrategyAddOrEditI804'));//调用键盘处理方法
		g_varbuttonI804='add';
		Ext.getCmp('checkTypeI804').setValue('B');//默认值设为
		Ext.getCmp('checkStrategyIdI804').setValue($i18n_prompt.autogenerationWhenSave);//$i18n_prompt.autogenerationWhenSave 保存时自动生成
		Ext.getCmp('skipPickFlagI804').setValue('0');//默认值设为0
		Ext.getCmp('checkLevelI804').setValue('1');//默认值设为
		Ext.getCmp('autoCloseBoxI804').setValue('0');//默认值设为
		Ext.getCmp('packAdvanceI804').setValue('0');//默认值设为
		Ext.getCmp('weightFlagI804').setValue('0');//默认值设为
		Ext.getCmp('volumFlagI804').setValue('0');//默认值设为
				
		commonSetMsterReadOnlyByArray(
				new Array('checkStrategyIdI804'),true);
    },
    //策略头档-修改(改动)
    detailEditClik:function(){
		var data=Ext.getCmp('odata_CheckStrategyGridI804').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.odata.window.odata_CheckStrategyAddOrEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			this.loadCheckStrategyI804();
			commonMenu5PrevOrNext('menuWidgetI804_1','odata_CheckStrategyGridI804',0);
			updateCommMenu5('menuWidgetI804_1');
			g_varbuttonI804='edit';
		}
	},
	//策略头档-删除(修改中)
	strategydetailDeleteClik:function()
	{
		debugger;
		var objData = Ext.getCmp('odata_CheckStrategyGridI804').getSelectionModel().getSelection();  
        if (objData.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {
        	var listDetail = [];
    		var strDtl = {
    				columnId:'a.enterprise_no',   //不知是否要改
    				value:objData[0].get("enterpriseNo")	
    		};
    		listDetail.push(strDtl);
    		var strDtl = {
    				columnId:'a.check_strategy_id',
    				value:objData[0].get("checkStrategyId")	
    		};
    		listDetail.push(strDtl);
    		var jsonDetail = Ext.encode(listDetail);
        	Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        		if(button=='yes')
    			{
    				Ext.Ajax.request({
    					method:'POST',
    					url:'Odata_CheckStrategyAction_deleteStrategy',//
    					params :{ 
    						strQuery : jsonDetail
    					},
    					success : function(response) {
    						var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('odata_CheckStrategyGridI804').getStore().load();
								Ext.getCmp('checkStrategyIdAndNameUII804').getStore().removeAll();
						 		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}  	
						}
    				});
    			}			
            });
        }
	},
	//策略头档-查找 (改动)
	strategyQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='I804';
		queryGrid='odata_CheckStrategyGridI804';
		Ext.getCmp('checkTypeUII804').setValue('');
		Ext.getCmp('checkLevelUII804').setValue('');
		Ext.getCmp('checkStrategyIdAndNameUII804').setValue('');
	},
	//策略头档窗口-新增(cg)
	strategyWinAdd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('checkStrategyNameI804'),false);
		bindEnterSkip($('#odata_CheckStrategyAddOrEditI804'));//调用键盘处理方法
	    addoutLocateStrategy();   //新增策略头档初始化	    
		g_varbuttonI804='add';
		Ext.getCmp('checkTypeI804').setValue('B');//默认值设为
		Ext.getCmp('checkStrategyIdI804').setValue($i18n_prompt.autogenerationWhenSave);//$i18n_prompt.autogenerationWhenSave 保存时自动生成
		Ext.getCmp('skipPickFlagI804').setValue('0');//默认值设为0
		Ext.getCmp('checkLevelI804').setValue('1');//默认值设为
		Ext.getCmp('autoCloseBoxI804').setValue('0');//默认值设为
		Ext.getCmp('packAdvanceI804').setValue('0');//默认值设为
		Ext.getCmp('weightFlagI804').setValue('0');//默认值设为
		Ext.getCmp('volumFlagI804').setValue('0');//默认值设为
		Ext.getCmp('checkStrategyNameI804').setValue('');//默认值设为
		
    },
	//策略头档-上一条记录 ()
	strategyPrev:function(){		
		commonMenu5PrevOrNext('menuWidgetI804_1','odata_CheckStrategyGridI804',-1);
		this.loadCheckStrategyI804();
	},
	//策略头档-下一条记录()
	strategyNext:function(){
		commonMenu5PrevOrNext('menuWidgetI804_1','odata_CheckStrategyGridI804',1);
		this.loadCheckStrategyI804();
	},
	//策略头档-关闭窗口(cg)
	strategyColse:function(){
		Ext.getCmp('odata_CheckStrategyAddOrEditWindow').close();
		Ext.getCmp('odata_CheckStrategyGridI804').getStore().reload();
	},
	
	//策略头档-填充数据    (0813)
	loadCheckStrategyI804:function(){
		var rec=Ext.getCmp('odata_CheckStrategyGridI804').getSelectionModel().getSelection();
		if(rec.length>0){//里面的Id貌似是窗口里的
			Ext.getCmp('checkTypeI804').setValue(rec[0].data.checkType);
			Ext.getCmp('checkStrategyIdI804').setValue(rec[0].data.checkStrategyId);
			Ext.getCmp('checkStrategyNameI804').setValue(rec[0].data.checkStrategyName);
			Ext.getCmp('skipPickFlagI804').setValue(rec[0].data.skipPickFlag);
			Ext.getCmp('checkLevelI804').setValue(rec[0].data.checkLevel);
			Ext.getCmp('autoCloseBoxI804').setValue(rec[0].data.autoCloseBox);
			Ext.getCmp('packAdvanceI804').setValue(rec[0].data.packAdvance);
			Ext.getCmp('weightFlagI804').setValue(rec[0].data.weightFlag);
			Ext.getCmp('volumFlagI804').setValue(rec[0].data.volumFlag);
			commonSetMsterReadOnlyByArray(
					new Array('checkTypeI804','checkStrategyIdI804'),true);//主键存在
			Ext.getCmp('checkStrategyNameI804').focus(false, 3);
		}
	},
	
	//策略头档-保存 (改动)
	strategySave:function(){
		
		if(!commonCheckIsInputAll('odata_CheckStrategyAddOrEditI804')){
			return;
		}
		
		if(Ext.getCmp('odata_CheckStrategyAddOrEditI804').getForm().isValid()){
			var rec=Ext.getCmp('odata_CheckStrategyGridI804').getSelectionModel().getSelection()[0];
			var checkStrategyId='';
			if(Ext.getCmp('checkStrategyIdI804').getValue()==$i18n_prompt.autogenerationWhenSave){
				checkStrategyId=null;
			}else{
				checkStrategyId=Ext.getCmp('checkStrategyIdI804').getValue();
			}
			strategyDStr={
				id:{
					checkStrategyId:checkStrategyId,
					checkType:Ext.getCmp('checkTypeI804').getValue(),
					enterpriseNo:Ext.get('enterpriseNo').getValue()//由获取从编辑面板传来的企业编号，改为获取用户自带的
				},	
				checkStrategyName:Ext.getCmp('checkStrategyNameI804').getValue(),
				skipPickFlag:Ext.getCmp('skipPickFlagI804').getValue(),
				checkLevel:Ext.getCmp('checkLevelI804').getValue(),
				autoCloseBox:Ext.getCmp('autoCloseBoxI804').getValue(),
				packAdvance:Ext.getCmp('packAdvanceI804').getValue(),
				weightFlag:Ext.getCmp('weightFlagI804').getValue(),
				volumFlag:Ext.getCmp('volumFlagI804').getValue(),
				rgstDate:g_varbuttonI804=='add'?new Date():rec.data.rgstDate,
				rgstName:g_varbuttonI804=='add'?Ext.get('workerNo').getValue():rec.data.rgstName,
				updtDate:g_varbuttonI804=='add'?'':new Date(),
				updtName:g_varbuttonI804=='add'?'':Ext.get('workerNo').getValue()
			};								
			Ext.Ajax.request({
				url:'Odata_CheckStrategyAction_saveStrategy',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(strategyDStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						if(Ext.getCmp('checkStrategyIdI804').getValue()==$i18n_prompt.autogenerationWhenSave){
							Ext.getCmp('checkStrategyIdI804').setValue(data.obj);
						}
						commonSetMsterReadOnlyByArray(
								new Array('checkStrategyIdI804'),true);
						Ext.example.msg($i18n.prompt,data.msg);
						
				 		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().removeAll();
				 		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().reload();
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
		}
	},
	//策略类型选择(修改中)
	checkTypeUISelect:function(){
  		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('checkTypeUII804').getValue())
 				&& Ext.getCmp('checkTypeUII804').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.check_type',
 					value:Ext.getCmp("checkTypeUII804").getValue()
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
 		//复核级别下拉加载
 		Ext.getCmp('checkLevelUII804').setValue('');
 		Ext.apply(Ext.getCmp('checkLevelUII804').getStore().proxy.extraParams,params);
 		Ext.getCmp('checkLevelUII804').getStore().removeAll();
 		Ext.getCmp('checkLevelUII804').getStore().reload();
 		//策略id下拉加载
 		Ext.getCmp('checkStrategyIdAndNameUII804').setValue('');
 		Ext.apply(Ext.getCmp('checkStrategyIdAndNameUII804').getStore().proxy.extraParams,wheresql);
 		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().removeAll();
 		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().reload();
 		//策略头档列表加载
 		Ext.apply(Ext.getCmp('odata_CheckStrategyGridI804').getStore().proxy.extraParams,params);
 		Ext.getCmp('odata_CheckStrategyGridI804').getStore().removeAll();
 		Ext.getCmp('odata_CheckStrategyGridI804').getStore().reload();
    },
  	//复核级别选择 ()
  	checkLevelUISelect:function(){
  		var listDetail1  =  [];
  		var strDt2 = {
				columnId:'a.check_level',
				value:Ext.getCmp("checkLevelUII804").getValue()
		};
		listDetail1.push(strDt2);
 		if(!Ext.isEmpty(Ext.getCmp('checkStrategyIdAndNameUII804').getValue())
 				&& Ext.getCmp('checkStrategyIdAndNameUII804').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'a.check_strategy_id',
 					value:Ext.getCmp("checkStrategyIdAndNameUII804").getValue()
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
 		//策略类型下拉加载
 		Ext.getCmp('checkTypeUII804').setValue('');
 		Ext.apply(Ext.getCmp('checkTypeUII804').getStore().proxy.extraParams,wheresql);
 		Ext.getCmp('checkTypeUII804').getStore().removeAll();
 		Ext.getCmp('checkTypeUII804').getStore().reload();
 		//策略头档列表加载
 		Ext.apply(Ext.getCmp('odata_CheckStrategyGridI804').getStore().proxy.extraParams,params);
 		Ext.getCmp('odata_CheckStrategyGridI804').getStore().removeAll();
 		Ext.getCmp('odata_CheckStrategyGridI804').getStore().reload();
  	},
	//策略id加载(修改中)
  	locateStrategyIdBeforeQuery:function(){
 		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('checkTypeUII804').getValue()) 
				&& Ext.getCmp('checkTypeUII804').getValue() !='ALL'){
			var strDt2 = {
					columnId:'a.check_type',
					value:Ext.getCmp("checkTypeUII804").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('checkLevelUII804').getValue())){
			var strDt2 = {
					columnId:'a.check_level',
					value:Ext.getCmp("checkLevelUII804").getValue()
				};
				listDetail1.push(strDt2);
		}
		var params={
			str:Ext.getCmp("checkStrategyIdAndNameUII804").getValue(),   
			strQuery:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('checkStrategyIdAndNameUII804').getStore().proxy.extraParams,params);
		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().removeAll();
		Ext.getCmp('checkStrategyIdAndNameUII804').getStore().load();
 	 },
 	//策略id选择
 	checkStrategyIdAndNameSelect:function(){
  		var listDetail1  =  [];
  		var strDt2 = {
				columnId:'check_strategy_id',
				value:Ext.getCmp("checkStrategyIdAndNameUII804").getValue()
			};
		listDetail1.push(strDt2);
 		if(!Ext.isEmpty(Ext.getCmp('checkTypeUII804').getValue())
 				&& Ext.getCmp('checkTypeUII804').getValue() !='ALL'){
 			var strDt2 = {
 					columnId:'check_type',
 					value:Ext.getCmp("checkTypeUII804").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		if(!Ext.isEmpty(Ext.getCmp('checkLevelUII804').getValue())){
 			var strDt2 = {
 					columnId:'check_level',
 					value:Ext.getCmp("checkLevelUII804").getValue()
 				};
 				listDetail1.push(strDt2);
 		}
 		var params={
 			strQuery:Ext.encode(listDetail1)
 		};
 		Ext.apply(Ext.getCmp('odata_CheckStrategyGridI804').getStore().proxy.extraParams,params);
 		Ext.getCmp('odata_CheckStrategyGridI804').getStore().removeAll();
 		Ext.getCmp('odata_CheckStrategyGridI804').getStore().reload();
  	 }
  	 
 });
 
//新增策略头档初始化（已修改）
function addoutLocateStrategy(){  //注意，这个odata_CheckStrategyAddOrEditWindowI804，就是odata_CheckStrategyAddOrEditWindow的 item的ID
	Ext.getCmp('odata_CheckStrategyAddOrEditI804').getForm().reset();
}               

