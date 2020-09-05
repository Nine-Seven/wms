/** 模块名称：任务切分规则配置
 * 模块编码：I201
 * 创建：huangb 20160608
 * 新增onedeliveronepick(同一个配送对象一次性拣货：0-不一次拣货；1-要求一次拣货) huangb 20160801
 */
var g_varbuttonI201 = 'add';//add:增加保拣线；
var g_intRowindexI201 = '';
var g_varRgstName = 'admin';
var g_RgstDate = '';
Ext.define('cms.controller.bdef.bdef_WmsTaskallotRuleController',{
		extend:'Ext.app.Controller',
		requires:['cms.view.bdef.bdef_WmsTaskallotRuleUI'],
		
		init:function(){
			this.control({
					/*-----------------------------------头档工具条事件--------------------------------------------*/
					//选中一条头档 加载明细
					'bdef_WmsTaskallotRuleUI grid[id=bdef_WmsTaskallotRuleUIGridI201]':{
						selectionchange:this.showWmsTaskallotRuleController
					},
					//新增配置头档
			    	'bdef_WmsTaskallotRuleUI commMenuWidget2[id=menuI201] button[name=detailAdd]':{
						click:this.MasterAdd
					},
					//头档配置窗口新增
					'bdef_WmsTaskallotRuleMAddOrEditWindow button[name=add]':{
						click:this.MasterAddWindow
					},
					//修改配置头档
					'bdef_WmsTaskallotRuleUI commMenuWidget2[id=menuI201] button[name=detailEdit]':{
						click:this.MasterUpdate
					},
					//头档配置窗口上一条
					'bdef_WmsTaskallotRuleMAddOrEditWindow button[name=prev]':{
						click:this.MasterWindowPrev
					},
					//头档配置窗口下一条
					'bdef_WmsTaskallotRuleMAddOrEditWindow button[name=next]':{
						click:this.MasterWindowNext
					},
					//头档配置窗口保存
					'bdef_WmsTaskallotRuleMAddOrEditWindow button[name=save]':{
						click:this.MasterWindowSave
					},
					//查找配置头档
					'bdef_WmsTaskallotRuleUI commMenuWidget2[id=menuI201] button[name=detailQuery]':{
						click:this.QueryWmsTaskallotRuleM	
					},
					//头档窗口关闭
					'bdef_WmsTaskallotRuleMAddOrEditWindow button[name=close]':{
						click:this.close_WmsTaskallotRuleMAddOrEditWindow
					},
					//删除配置头档
					'bdef_WmsTaskallotRuleUI commMenuWidget2[id=menuI201] button[name=detailDelete]':{
						click:this.MasterDelete
					},
					
					/*-----------------------------------明细工具条事件--------------------------------------------*/
					//新增配置明细
					'bdef_WmsTaskallotRuleUI commMenuWidget6[id=menuI201_2] button[name=detailAdd]':{
						click:this.DetailsAdd
					},
					//明细配置窗口新增
					'bdef_WmsTaskallotRuleDAddOrEditWindow button[name=add]':{
						click:this.DetailsWindowAdd
					},
					//修改配置明细
					'bdef_WmsTaskallotRuleUI commMenuWidget6[id=menuI201_2] button[name=detailEdit]':{
						click:this.DetailsUpdate
					},
					//明细配置窗口上一条
					'bdef_WmsTaskallotRuleDAddOrEditWindow button[name=prev]':{
						click:this.DetailsWindowPrev
					},
					//明细配置窗口下一条
					'bdef_WmsTaskallotRuleDAddOrEditWindow button[name=next]':{
						click:this.DetailsWindowNext
					},
					//明细配置窗口保存
					'bdef_WmsTaskallotRuleDAddOrEditWindow button[name=save]':{
						click:this.DetailsWindowSave
					},
					//明细窗口关闭
					'bdef_WmsTaskallotRuleDAddOrEditWindow button[name=close]':{
						click:this.close_WmsTaskallotRuleDAddOrEditWindow
					},
					//删除配置头档
					'bdef_WmsTaskallotRuleUI commMenuWidget6[id=menuI201_2] button[name=detailDelete]':{
						click:this.DetailsDelete
					}
					
			     });
		},
		
		/*-----------------------------------头档工具条方法--------------------------------------------*/
		//选中一条头档 加载明细
		showWmsTaskallotRuleController : function(){
			var objData = Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getSelectionModel().getSelection();
			if(objData.length!=0){
				if(objData[0].get("ruleType") == '1' 
					|| objData[0].get("ruleType") == '4'
				    || objData[0].get("ruleType") == '6'){
					var wheresql = {
						strQuery : null
					};
					//传查询条件
					Ext.apply(Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().proxy.extraParams,wheresql);
					//清空数据
					Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().removeAll();
					//加载数据
					Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().load();
					//控制按钮不屏蔽
					Ext.getCmp('menuI201_2').items.items[0].setDisabled(false);
					Ext.getCmp('menuI201_2').items.items[1].setDisabled(false);
					Ext.getCmp('menuI201_2').items.items[2].setDisabled(false);
				}else{
					Ext.getCmp('menuI201_2').items.items[0].setDisabled(true);
					Ext.getCmp('menuI201_2').items.items[1].setDisabled(true);
					Ext.getCmp('menuI201_2').items.items[2].setDisabled(true);
					Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().removeAll();
				}
			}
		},
		
		//新增配置头档
		MasterAdd:function(){
			Ext.create('cms.view.bdef.window.bdef_WmsTaskallotRuleMAddOrEditWindow',{title:$i18n.titleadd}).show();
			//重置窗口数据显示
			Ext.getCmp('bdef_WmsTaskallotRuleMAddOrEditWindowI201').getForm().reset();
			//控制菜单显示项
			addCommMenu5('menuWidget5I201');
			//设置焦点 10-失去焦点或获得焦点的时间延长
			Ext.getCmp('textTaskRuleidI201').focus(false, 10);
			//调用键盘处理方法 比如回车键
			bindEnterSkip($('#bdef_WmsTaskallotRuleMAddOrEditWindowI201'));
			g_varbuttonI201='add';
		},
		
		//头档配置窗口新增
		MasterAddWindow:function(){
			//重置窗口数据显示
			Ext.getCmp('bdef_WmsTaskallotRuleMAddOrEditWindowI201').getForm().reset();
			//控制菜单显示项
			addCommMenu5('menuWidget5I201');
			//设置焦点 10-失去焦点或获得焦点的时间延长
			Ext.getCmp('textTaskRuleidI201').focus(false, 10);
			//调用键盘处理方法 比如回车键
			bindEnterSkip($('#bdef_WmsTaskallotRuleMAddOrEditWindowI201'));
			g_varbuttonI201='add';
		},
		
		//修改配置头档
		MasterUpdate:function(){
			var objData = Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getSelectionModel().getSelection();
			if(objData.length > 0){
				Ext.create('cms.view.bdef.window.bdef_WmsTaskallotRuleMAddOrEditWindow',{title:$i18n.titleupdate}).show();
				//重置窗口数据显示
				Ext.getCmp('bdef_WmsTaskallotRuleMAddOrEditWindowI201').getForm().reset();
				//控制菜单显示项
				updateCommMenu5('menuWidget5I201');
				//设置焦点 10-失去焦点或获得焦点的时间延长
				Ext.getCmp('cmbRuleTypeI201').focus(false, 10);
				//调用键盘处理方法 比如回车键
				bindEnterSkip($('#bdef_WmsTaskallotRuleMAddOrEditWindowI201'));
				g_varbuttonI201='update';
				//获取当前行数
				g_intRowindexI201=objData[0].index;
				//加载界面数据
				loadWmsTaskallotRuleM(g_intRowindexI201);
				//控制主键只读
				commonSetMsterReadOnlyByArray(new Array('textTaskRuleidI201'),true);
				commonSetCommMenu5PrevOrNext('menuWidget5I201','bdef_WmsTaskallotRuleUIGridI201',g_intRowindexI201);
				
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		},
		
		//头档配置窗口上一条
		MasterWindowPrev:function(){
			g_intRowindexI201=g_intRowindexI201-1;
			loadWmsTaskallotRuleM(g_intRowindexI201);
			commonSetCommMenu5PrevOrNext('menuWidget5I201','bdef_WmsTaskallotRuleUIGridI201',g_intRowindexI201);	
		},
		
		//头档配置窗口下一条
		MasterWindowNext:function(){
			g_intRowindexI201=g_intRowindexI201+1;
			loadWmsTaskallotRuleM(g_intRowindexI201);
			commonSetCommMenu5PrevOrNext('menuWidget5I201','bdef_WmsTaskallotRuleUIGridI201',g_intRowindexI201);
		},
		
		//头档配置窗口保存
		MasterWindowSave:function(){
			if(Ext.getCmp('bdef_WmsTaskallotRuleMAddOrEditWindowI201').getForm().isValid()){
				var group={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							taskRuleid:Ext.getCmp('textTaskRuleidI201').getValue()
						},
						ruleType:Ext.getCmp('cmbRuleTypeI201').getValue(),
						paraValue:Ext.getCmp('textParaValueI201').getValue(),
						memo:Ext.getCmp('textMemoI201').getValue(),
						rgstName:g_varbuttonI201=='add'?Ext.get('workerNo').getValue():g_varRgstName,
						rgstDate:g_varbuttonI201=='add'?new Date():g_RgstDate,
					    taskType:Ext.getCmp('cmbTaskTypeI201').getValue(),
					    printType:Ext.getCmp('cmbPrintTypeI201').getValue(),
					    onedeliveronepick:Ext.getCmp('cmbOnedeliveronepickI201').getValue(),
					    taskGetType:Ext.getCmp('cmbTaskGetTypeI201').getValue()
				    };
				var str=Ext.encode(group);
				Ext.Ajax.request({
					url:'bdef_WmsTaskallotRuleMAction_saveWmsTaskallotRuleM',
					method:'post',
					params:{
						str:str
					},
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							//修改才设置只读
							if(g_varbuttonI201=='update'){
								commonSetMsterReadOnlyByArray(new Array('textTaskRuleidI201'),true);
							}
							//刷新
							Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getStore().load();
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
		},
		
		//查找配置头档
		QueryWmsTaskallotRuleM:function(){
			Ext.create('cms.view.common.queryWindow').show();
			Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
			queryModuleId='I201';
			queryGrid='bdef_WmsTaskallotRuleUIGridI201';
		},
		
		//关闭配置头档窗口
		close_WmsTaskallotRuleMAddOrEditWindow:function(th){
			Ext.getCmp('bdef_WmsTaskallotRuleMAddOrEditWindow').close();
		},
		
		//删除配置头档
		MasterDelete:function(){
			var objData = Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getSelectionModel().getSelection();  
	        if (objData.length == 0) {  
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	            return;  
	        }else{
	        	var listDetail = [];
	        	var strDtl = {
    				columnId:'wtr.task_Ruleid',
    				value:objData[0].get("taskRuleid")	
	    		};
	    		listDetail.push(strDtl);
	    		
	    		var jsonDetail = Ext.encode(listDetail);
	    		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
	    			if(button=='yes'){
	    				Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getStore().remove(objData);
	    				Ext.Ajax.request({
	    					method:'POST',
	    					url:'bdef_WmsTaskallotRuleMAction_deleteWmsTaskallotRuleM',
	    					params :{ 
	    						strQuery : jsonDetail
	    					},
	    					success : function(response) {
	    						var data=Ext.decode(response.responseText);
	    						if(data.isSucc){
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getStore().load();
								}else{
									Ext.example.msg($i18n.prompt,data.msg+data.obj);
								} 
	    					}
	    				});
	    			}
	    		});
	        }
		},
		
		/*-----------------------------------明细工具条方法--------------------------------------------*/
		//新增配置明细
		DetailsAdd:function(){
			Ext.create('cms.view.bdef.window.bdef_WmsTaskallotRuleDAddOrEditWindow',{title:$i18n.titleadd}).show();
			//重置窗口数据显示
			Ext.getCmp('bdef_WmsTaskallotRuleDAddOrEditWindowI201').getForm().reset();
			//控制菜单显示项
			addCommMenu5('menuWidget5I201_2');
			//设置焦点 10-失去焦点或获得焦点的时间延长
			Ext.getCmp('cmbOutstockTypeI201_2').focus(false, 10);
			//调用键盘处理方法 比如回车键
			bindEnterSkip($('#bdef_WmsTaskallotRuleDAddOrEditWindowI201'));
			g_varbuttonI201='add';
		},
		
		//头档配置窗口新增
		DetailsWindowAdd:function(){
			//重置窗口数据显示
			Ext.getCmp('bdef_WmsTaskallotRuleDAddOrEditWindowI201').getForm().reset();
			//设置焦点 10-失去焦点或获得焦点的时间延长
			Ext.getCmp('cmbOutstockTypeI201_2').focus(false, 10);
			//调用键盘处理方法 比如回车键
			bindEnterSkip($('#bdef_WmsTaskallotRuleDAddOrEditWindowI201'));
			g_varbuttonI201='add';
		},
		
		//修改配置明细
		DetailsUpdate:function(){
			var objData = Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getSelectionModel().getSelection();
			if(objData.length > 0){
				Ext.create('cms.view.bdef.window.bdef_WmsTaskallotRuleDAddOrEditWindow',{title:$i18n.titleupdate}).show();
				//重置窗口数据显示
				Ext.getCmp('bdef_WmsTaskallotRuleDAddOrEditWindowI201').getForm().reset();
				//控制菜单显示项
				updateCommMenu5('menuWidget5I201_2');
				//设置焦点 10-失去焦点或获得焦点的时间延长
				Ext.getCmp('cmbAllotRuleI201_2').focus(false, 10);
				//调用键盘处理方法 比如回车键
				bindEnterSkip($('#bdef_WmsTaskallotRuleDAddOrEditWindowI201'));
				g_varbuttonI201='update';
				//获取当前行数
				g_intRowindexI201=objData[0].index;
				//加载界面数据
				loadWmsTaskallotRuleD(g_intRowindexI201);
				//控制主键只读
				commonSetMsterReadOnlyByArray(new Array('cmbOutstockTypeI201_2','cmbOperateTypeI201_2'),true);
				commonSetCommMenu5PrevOrNext('menuWidget5I201_2','bdef_WmsTaskallotRuleDUIGridI201',g_intRowindexI201);
				
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		},
		
		//明细配置窗口上一条
		DetailsWindowPrev:function(){
			g_intRowindexI201=g_intRowindexI201-1;
			loadWmsTaskallotRuleD(g_intRowindexI201);
			commonSetCommMenu5PrevOrNext('menuWidget5I201_2','bdef_WmsTaskallotRuleDUIGridI201',g_intRowindexI201);	
		},
		
		//明细配置窗口下一条
		DetailsWindowNext:function(){
			g_intRowindexI201=g_intRowindexI201+1;
			loadWmsTaskallotRuleD(g_intRowindexI201);
			commonSetCommMenu5PrevOrNext('menuWidget5I201_2','bdef_WmsTaskallotRuleDUIGridI201',g_intRowindexI201);
		},
		
		//明细配置窗口保存
		DetailsWindowSave:function(){
			if(Ext.getCmp('bdef_WmsTaskallotRuleDAddOrEditWindowI201').getForm().isValid()){
				var group={
						id:{
							operateType:Ext.getCmp('cmbOperateTypeI201_2').getValue(),
							outstockType:Ext.getCmp('cmbOutstockTypeI201_2').getValue()
						},
						allotRule:Ext.getCmp('cmbAllotRuleI201_2').getValue(),
						boxFlag:Ext.getCmp('cmbBoxFlagI201_2').getValue(),
						paraValue:Ext.getCmp('textParaValueI201_2').getValue(),
						taskType:Ext.getCmp('cmbTaskTypeI201_2').getValue(),
						memo:Ext.getCmp('textMemoI201_2').getValue(),
						rgstName:g_varbuttonI201=='add'?Ext.get('workerNo').getValue():g_varRgstName,
						rgstDate:g_varbuttonI201=='add'?new Date():g_RgstDate,
						printType:Ext.getCmp('cmbPrintTypeI201_2').getValue(),
						taskGetType:Ext.getCmp('cmbTaskGetTypeI201_2').getValue()
				    };
				var str=Ext.encode(group);
				Ext.Ajax.request({
					url:'bdef_WmsTaskallotRuleMAction_saveWmsTaskallotRuleD',
					method:'post',
					params:{
						str:str
					},
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							if(g_varbuttonI201=='update'){
								commonSetMsterReadOnlyByArray(new Array('cmbOutstockTypeI201_2','cmbOperateTypeI201_2'),true);
							};
							//刷新
							Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().load();
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
		},
		
		//关闭配置明细窗口
		close_WmsTaskallotRuleDAddOrEditWindow:function(th){
			Ext.getCmp('bdef_WmsTaskallotRuleDAddOrEditWindow').close();
		},
		
		//删除配置明细
		DetailsDelete:function(){
			var objData = Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getSelectionModel().getSelection();  
	        if (objData.length == 0) {  
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	            return;  
	        }else{
	        	var listDetail = [];
	        	var strDtl = {
    				columnId:'wtr.operate_type',
    				value:objData[0].get("operateType")	
	    		};
	    		listDetail.push(strDtl);
	    		var strDtl = {
    				columnId:'wtr.outstock_type',
    				value:objData[0].get("outstockType")	
	    		};
	    		listDetail.push(strDtl);
	    		
	    		var jsonDetail = Ext.encode(listDetail);
	    		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
	    			if(button=='yes'){
	    				Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().remove(objData);
	    				Ext.Ajax.request({
	    					method:'POST',
	    					url:'bdef_WmsTaskallotRuleMAction_deleteWmsTaskallotRuleD',
	    					params :{ 
	    						strQuery : jsonDetail
	    					},
	    					success : function(response) {
	    						var data=Ext.decode(response.responseText);
	    						if(data.isSucc){
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().load();
								}else{
									Ext.example.msg($i18n.prompt,data.msg+data.obj);
								} 
	    					}
	    				});
	    			}
	    		});
	        }
		},
		
		
    });

//填充配置头档数据
function loadWmsTaskallotRuleM(g_intRowindexI201){
	var objRecord=Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getStore().getAt(g_intRowindexI201);
    Ext.getCmp('textTaskRuleidI201').setValue(objRecord.data.taskRuleid);
	Ext.getCmp('cmbRuleTypeI201').setValue(objRecord.data.ruleType);
	Ext.getCmp('textParaValueI201').setValue(objRecord.data.paraValue);
	Ext.getCmp('cmbTaskTypeI201').setValue(objRecord.data.taskType);
	Ext.getCmp('cmbPrintTypeI201').setValue(objRecord.data.printType);
	Ext.getCmp('cmbOnedeliveronepickI201').setValue(objRecord.data.onedeliveronepick);
    Ext.getCmp('cmbTaskGetTypeI201').setValue(objRecord.data.taskGetType);
    Ext.getCmp('textMemoI201').setValue(objRecord.data.memo);
    g_varRgstName=objRecord.data.rgstName;
    g_RgstDate=objRecord.data.rgstDate;
}

//填充配置明细数据
function loadWmsTaskallotRuleD(g_intRowindexI201){
	var objRecord=Ext.getCmp('bdef_WmsTaskallotRuleDUIGridI201').getStore().getAt(g_intRowindexI201);
    Ext.getCmp('cmbOutstockTypeI201_2').setValue(objRecord.data.outstockType);
	Ext.getCmp('cmbOperateTypeI201_2').setValue(objRecord.data.operateType);
	Ext.getCmp('cmbAllotRuleI201_2').setValue(objRecord.data.allotRule);
	Ext.getCmp('cmbBoxFlagI201_2').setValue(objRecord.data.boxFlag);
	Ext.getCmp('textParaValueI201_2').setValue(objRecord.data.paraValue);
    Ext.getCmp('cmbTaskTypeI201_2').setValue(objRecord.data.taskType);
    Ext.getCmp('cmbPrintTypeI201_2').setValue(objRecord.data.printType);
    Ext.getCmp('cmbTaskGetTypeI201_2').setValue(objRecord.data.taskGetType);
    Ext.getCmp('textMemoI201_2').setValue(objRecord.data.memo);
    
    g_varRgstName=objRecord.data.rgstName;
    g_RgstDate=objRecord.data.rgstDate;
}

