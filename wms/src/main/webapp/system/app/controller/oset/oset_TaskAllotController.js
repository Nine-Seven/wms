/**
 * 模块名称：切单规则配置
 * 模块编码：3929
 * 创建：chensr
 */
var type3929M='';
var type3929D='';
var rowindex3929M=0;
var rowindex3929D=0;
Ext.define('cms.controller.oset.oset_TaskAllotController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.oset.oset_TaskAllotUI'],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			//新增
			'oset_TaskAllotUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//重新加载添加窗口
			'oset_TaskAllotAddorEditWindow button[name=add]':{
				click:this.add
			},
			//判断当前仓别的任务分配id是否唯一
			'oset_TaskAllotAddorEditWindow textfield[id=taskIdM3929]':{
				blur:this.checkTaskId
			},
			//保存订单规则头档
			'oset_TaskAllotAddorEditWindow button[name=save]':{
				click:this.saveTaskAllotm
			},
			//修改
			'oset_TaskAllotUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//上一条记录
			'oset_TaskAllotAddorEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条记录
			'oset_TaskAllotAddorEditWindow button[name=next]':{
				click:this.next
			},
			//关闭窗口
			'oset_TaskAllotAddorEditWindow button[name=close]':{
				click:this.colse
			},
			//浏览
			'oset_TaskAllotUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			
            ///////////////////////////////////////////////////////////////////////////////	
			
			//选择一行规则头档信息，显示相应的头档明细
			'oset_TaskAllotUI grid[id=oset_TaskAllotMUI3929]':{	
				selectionchange:this.showaskAllotD
			},
			
			//新增
			'oset_TaskAllotUI button[name=detailAddD]':{
				click:this.detailAddD
			},
			//重新加载添加窗口
			'oset_TaskAllotDAddorEditWindow button[name=add]':{
				click:this.addDWindow
			},
			//保存订单规则明细
			'oset_TaskAllotDAddorEditWindow button[name=save]':{
				click:this.saveTaskAllotd
			},
			
			//关闭窗口
			'oset_TaskAllotDAddorEditWindow button[name=close]':{
				click:this.colseD
			},
			//修改
			'oset_TaskAllotUI button[name=detailEditD]':{
				click:this.detailEditD
			},
			//浏览
			'oset_TaskAllotUI button[name=detailBrowseD]':{
				click:this.detailBrowseD
			},
			//上一条记录
			'oset_TaskAllotDAddorEditWindow button[name=prev]':{
				click:this.prevD
			},
			//下一条记录
			'oset_TaskAllotDAddorEditWindow button[name=next]':{
				click:this.nextD
			},
			
			//选择作业类型，设置切单规则
			'oset_TaskAllotDAddorEditWindow combo[id=operateType3929]':{
				change:this.setAllotRule
			},
			
			//根据下架类型，加载作业类型1
			'oset_TaskAllotDAddorEditWindow combo[id=outstockTypeD3929]':{
				change:this.setSourceType
			},
			
			
			///////////////////////////////////////////////////////////////////////////////
			
			//选择一行，显示分配给该切单规则的储区
			'oset_TaskAllotUI grid[id=oset_TaskAllotMUI3929_1]':{	
				selectionchange:this.showRelaction
			},
			//设置切单规则和储区的关系
			'oset_TaskAllotUI button[id=right3929]':{
				click:this.right3929
			},
			//解除切单规则和储区的关系
			'oset_TaskAllotUI button[id=left3929]':{
				click:this.left3929
			}
		});
	},
	
	//根据下架类型，加载作业类型1
	setSourceType:function(){
		var str=
		{
			str:Ext.getCmp('outstockTypeD3929').getValue()
		};
		Ext.apply(Ext.getCmp('sourceTypeD3929').getStore().proxy.extraParams,str);
		Ext.getCmp('sourceTypeD3929').getStore().removeAll();
		Ext.getCmp('sourceTypeD3929').setValue(null);
		Ext.getCmp('sourceTypeD3929').getStore().load();
	},
	
	//选择作业类型，设置切单规则
	setAllotRule:function(){
		if(Ext.getCmp('operateType3929').getValue()=='P'){
			Ext.getCmp('boxFlagD3929').setValue('6');
			commonSetMsterReadOnlyByArray(
					new Array('boxFlagD3929'),true);
		}else{	
			commonSetMsterReadOnlyByArray(
				new Array('boxFlagD3929'),false);
		}
	},
	
	//调用新增明细窗口
	detailAddD:function(){
		var data = Ext.getCmp('oset_TaskAllotMUI3929').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg('提示','请选择切单规则头档');
		}else{		
			Ext.create('cms.view.oset.window.oset_TaskAllotDAddorEditWindow',{
				title:$i18n.titleadd//新增
			}).show();
			type3929D='add';
			Ext.getCmp('warehouseNoD3929').setValue(Ext.get('warehouseNo').getValue());
			Ext.getCmp('taskIdD3929').setValue(data[0].get('taskId'));
			commonSetMsterReadOnlyByArray(
					new Array('warehouseNoD3929','taskIdD3929'),true);
			addCommMenu5('oset_taskAllotD3929');
		}		
	},
	
	//调用新增规则头档窗口
	detailAdd:function(){
		Ext.create('cms.view.oset.window.oset_TaskAllotAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		type3929M='add';
		Ext.getCmp('warehouseNoM3929').setValue(Ext.get('warehouseNo').getValue());
		commonSetMsterReadOnlyByArray(
				new Array('warehouseNoM3929'),true);
		addCommMenu5('oset_taskAllotM3929');	
	},
	//实现新增功能（清空头档窗口的内容）
	add:function(){
		Ext.getCmp('IdForm3929M').getForm().reset();
		bindEnterSkip($('IdForm3929M'));//调用键盘处理方法
		Ext.getCmp('warehouseNoM3929').setValue(Ext.get('warehouseNo').getValue());		
	},
	
	//实现新增功能（清空明细窗口的内容）
	addDWindow:function(){
		var data = Ext.getCmp('oset_TaskAllotMUI3929').getSelectionModel().getSelection();
		Ext.getCmp('IdForm3929D').getForm().reset();
		bindEnterSkip($('IdForm3929D'));//调用键盘处理方法
		Ext.getCmp('warehouseNoD3929').setValue(Ext.get('warehouseNo').getValue());		
		Ext.getCmp('taskIdD3929').setValue(data[0].get('taskId'));
	},
	//判断当前仓别的任务分配id是否唯一
	checkTaskId:function(){
		if(type3929M=='add'){
			Ext.Ajax.request({
				url : 'oset_TaskAllotAction_checkTaskId',
				params : {
					str:Ext.getCmp('taskIdM3929').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg('提示',"当前仓别已存在该任务Id");
			    		Ext.getCmp('taskIdM3929').setValue(null);
			    	}
				}
			});
		}
	},	
	//保存订单规则头档
	saveTaskAllotm:function(){
		saveTaskAllotM();
	},
	//保存订单规则明细
	saveTaskAllotd:function(){
		saveTaskAllotD();
	},
	//修改订单规则头档
	detailEdit:function(){
		var data = Ext.getCmp('oset_TaskAllotMUI3929').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.oset.window.oset_TaskAllotAddorEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			rowindex3929M=data[0].index;
			loadData3929M(rowindex3929M);
			
			commonSetMsterReadOnlyByArray(
					new Array('warehouseNoM3929','taskIdM3929'),true);
			commonSetCommMenu5PrevOrNext('oset_taskAllotM3929','oset_TaskAllotMUI3929',rowindex3929M);
			updateCommMenu5('oset_taskAllotM3929');
			type3929M='edit';
		}
	},
	//修改切单规则明细
	detailEditD:function(){
		var data = Ext.getCmp('oset_TaskAllotDUI3929').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.oset.window.oset_TaskAllotDAddorEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			var dataD = Ext.getCmp('oset_TaskAllotDUI3929').getSelectionModel().getSelection();

			if(dataD.length==0){
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}else{
				rowindex3929D=dataD[0].index;
				loadData3929D(rowindex3929D);
				
				commonSetMsterReadOnlyByArray(
						new Array('warehouseNoD3929','taskIdD3929','outstockTypeD3929','sourceTypeD3929','operateType3929'),true);
				commonSetCommMenu5PrevOrNext('oset_taskAllotD3929','oset_TaskAllotDUI3929',rowindex3929D);
				updateCommMenu5('oset_taskAllotD3929');
				type3929D='edit';
			}
		}
	},
	
	//实现上一页功能(切单规则明细)
	prevD:function(){
		commonMenu5PrevOrNext('oset_taskAllotD3929','oset_TaskAllotDUI3929',-1);	
		loadData3929D(rowindex3929D);
	},
	
	//实现下一页功能(切单规则明细)
	nextD:function(){
		commonMenu5PrevOrNext('oset_taskAllotD3929','oset_TaskAllotDUI3929',+1);	
		loadData3929D(rowindex3929D);
	},
	
	//实现上一页功能(切单规则头档)
	prev:function(){
		rowindex3929M=rowindex3929M-1;
		loadData3929M(rowindex3929M);
		commonSetCommMenu5PrevOrNext('oset_taskAllotM3929','oset_TaskAllotMUI3929',rowindex3929M);
	},
	
	//实现下一页功能(切单规则头档)
	next:function(){
		rowindex3929M=rowindex3929M+1;
		loadData3929M(rowindex3929M);
		commonSetCommMenu5PrevOrNext('oset_taskAllotM3929','oset_TaskAllotMUI3929',rowindex3929M);
	},
	
	//关闭切单头档窗口
	colse:function(){
		Ext.getCmp('oset_TaskAllotAddorEditWindow').close();
	},
	//关闭切单明细窗口
	colseD:function(){
		Ext.getCmp('oset_TaskAllotDAddorEditWindow').close();
	},
	
	//浏览(切单明细)
	detailBrowseD:function(){
		var data = Ext.getCmp('oset_TaskAllotDUI3929').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.oset.window.oset_TaskAllotDAddorEditWindow',{
				title:$i18n.titlebrowse
		    }).show();
			rowindex3929D=data[0].index;
			loadData3929D(rowindex3929D);
			commonSetFieldReadOnly('oset_TaskAllotDAddorEditWindow',true);
			commonSetCommMenu5PrevOrNext('oset_taskAllotD3929','oset_TaskAllotDUI3929',rowindex3929D);
			updateCommMenu5('oset_taskAllotD3929');
		}
		rowindex3929D='edit';
		
	},
	
	//浏览(切单头档)
	detailBrowse:function(){
		var data = Ext.getCmp('oset_TaskAllotMUI3929').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.oset.window.oset_TaskAllotAddorEditWindow',{
				title:$i18n.titlebrowse
		    }).show();
			rowindex3929M=data[0].index;
			loadData3929M(rowindex3929M);
			commonSetFieldReadOnly('oset_TaskAllotAddorEditWindow',true);
			commonSetCommMenu5PrevOrNext('oset_taskAllotM3929','oset_TaskAllotMUI3929',rowindex3929M);
			browseCommMenu5('oset_taskAllotM3929');
		}
	},
	//显示分配给该切单规则的储区
	showRelaction:function(){
		var data = Ext.getCmp('oset_TaskAllotMUI3929_1').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.getCmp('defareaWhitAllot').getStore().removeAll();
		}else{
			var str = {
				str  : data[0].get('taskId'),
			};	
			Ext.apply(Ext.getCmp('defareaWhitAllot').getStore().proxy.extraParams,str);
			Ext.getCmp('defareaWhitAllot').getStore().removeAll();
			Ext.getCmp('defareaWhitAllot').getStore().load();		
		}
	},
	
	//设置切单规则和储区的关系
	right3929:function(){
		var gridA = Ext.getCmp('oset_TaskAllotMUI3929_1').getSelectionModel().getSelection();
		var gridB = Ext.getCmp('defareaWithoutAllot').getSelectionModel().getSelection();
	
		if(gridA.length!=0){
			if(gridB.length!=0){

				var detail1=[];
				var detail2=[];
				var detail3=[];
				var detail4=[];
				for(var i=0;i<gridB.length;i++){
					detail1.push(gridA[0].get('taskId'));
					detail2.push(gridB[i].get('warehouseNo'));
					detail3.push(gridB[i].get('wareNo'));
					detail4.push(gridB[i].get('areaNo'));
				}
				Ext.Ajax.request({
					url:'oset_TaskAllotAction_updateAllotAndDefareaRelation',
					params:{
						taskId:detail1,
						warehouseNo:detail2,
						wareNo:detail3,
						areaNo:detail4
					},
					success:function(response){
						Ext.getCmp('defareaWithoutAllot').getStore().load();
						Ext.getCmp('defareaWhitAllot').getStore().load();
					}
				});
			}else{
				Ext.example.msg('提示','请选择未分配储区');
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows);
		}		
	},
	//解除储区和切单规则的关系
	left3929:function(){
		var gridA = Ext.getCmp('oset_TaskAllotMUI3929_1').getSelectionModel().getSelection();
		var gridB = Ext.getCmp('defareaWhitAllot').getSelectionModel().getSelection();
	
		if(gridA.length!=0){
			if(gridB.length!=0){

				var detail1=[];
				var detail2=[];
				var detail3=[];
				var detail4=[];
				for(var i=0;i<gridB.length;i++){
					detail1.push(null);
					detail2.push(gridB[i].get('warehouseNo'));
					detail3.push(gridB[i].get('wareNo'));
					detail4.push(gridB[i].get('areaNo'));
				}
				Ext.Ajax.request({
					url:'oset_TaskAllotAction_updateAllotAndDefareaRelation',
					params:{
						taskId:detail1,
						warehouseNo:detail2,
						wareNo:detail3,
						areaNo:detail4
					},
					success:function(response){
						Ext.getCmp('defareaWithoutAllot').getStore().load();
						Ext.getCmp('defareaWhitAllot').getStore().load();
					}
				});
			}else{
				Ext.example.msg('提示','请选择未分配储区');
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows);
		}		
	},
	//显示切单明细
	showaskAllotD:function(){
		var data = Ext.getCmp('oset_TaskAllotMUI3929').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.getCmp('oset_TaskAllotDUI3929').getStore().removeAll();
		}else{		
			var strDetail1 = [];
			var d1={
				columnId:'t.task_id',
				value:data[0].get('taskId')
			};
			strDetail1.push(d1);
			
			var d2={
					columnId:'t.warehouse_no',
					value:data[0].get('warehouseNo')
			};
			strDetail1.push(d2);
			
			var jsonDetail = Ext.encode(strDetail1);
			var str = {
					str  : jsonDetail
			};				
			Ext.apply(Ext.getCmp('oset_TaskAllotDUI3929').getStore().proxy.extraParams,str);
			Ext.getCmp('oset_TaskAllotDUI3929').getStore().removeAll();
			Ext.getCmp('oset_TaskAllotDUI3929').getStore().load();		
		}
	},
	
	getItemType:function(){
		return type3929D;
	}
});

//保存或修改切单规则明细
function saveTaskAllotD(){
	if(!commonCheckIsInputAll('IdForm3929D')){
		return;
	}
	if(Ext.getCmp('IdForm3929D').getForm().isValid()){
		var oset_taskAllotMStr='';
		if(type3929D=='add'){
			oset_taskAllotDStr={
				id:{
					warehouseNo:Ext.getCmp('warehouseNoD3929').getValue(),
					taskId:Ext.getCmp('taskIdD3929').getValue(),
					outstockType:Ext.getCmp('outstockTypeD3929').getValue(),
					operateType:Ext.getCmp('operateType3929').getValue(),
					sourceType:Ext.getCmp('sourceTypeD3929').getValue()
				},
				allotRule:Ext.getCmp('allotRuleD3929').getValue(),
				boxFlag:Ext.getCmp('boxFlagD3929').getValue(),
				paraValue:Ext.getCmp('paraValueD3929').getValue(),
				taskType:Ext.getCmp('taskTypeD3929').getValue(),
				memo:Ext.getCmp('memoD3929').getValue() ,
				rgstDate:new Date(),
				rgstName:Ext.get('workerNo').getValue()
			};
		}else{			
			var cust=Ext.getCmp('oset_TaskAllotDUI3929').getStore().
			getAt(rowindex3929D-(Ext.getCmp('oset_TaskAllotDUI3929').
			getStore().currentPage-1)*appConfig.getPageSize());
			
			oset_taskAllotDStr={
				id:{
					warehouseNo:Ext.getCmp('warehouseNoD3929').getValue(),
					taskId:Ext.getCmp('taskIdD3929').getValue(),
					outstockType:Ext.getCmp('outstockTypeD3929').getValue(),
					operateType:Ext.getCmp('operateType3929').getValue(),
					sourceType:Ext.getCmp('sourceTypeD3929').getValue()
				},
				allotRule:Ext.getCmp('allotRuleD3929').getValue(),
				boxFlag:Ext.getCmp('boxFlagD3929').getValue(),
				paraValue:Ext.getCmp('paraValueD3929').getValue(),
				taskType:Ext.getCmp('taskTypeD3929').getValue(),
				memo:Ext.getCmp('memoD3929').getValue(),
				rgstDate:cust.data.rgstDate.time,
				rgstName:cust.data.rgstName,
				updtDate:new Date(),
				updtName:Ext.get('workerNo').getValue()
			};
		}
		Ext.Ajax.request({
			url:'oset_TaskAllotAction_saveOrupdateTaskAllotD',
			method:'post',
			params:{
				str:Ext.encode(oset_taskAllotDStr)
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){					
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('oset_TaskAllotDUI3929').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});	
	}
}

//保存或修改切单规则头档
function saveTaskAllotM(){
	if(!commonCheckIsInputAll('IdForm3929M')){
		return;
	}
	if(Ext.getCmp('IdForm3929M').getForm().isValid()){
		var oset_taskAllotMStr='';
		if(type3929M=='add'){
			oset_taskAllotMStr={
				id:{
					warehouseNo:Ext.getCmp('warehouseNoM3929').getValue(),
					taskId:Ext.getCmp('taskIdM3929').getValue()
					
				},				
				taskName:Ext.getCmp('taskNameM3929').getValue(),
				defaultFlag:Ext.getCmp('defaultFlagm3929').getValue(),
				memo:Ext.getCmp('memom3929').getValue(),
				rgstDate:new Date(),
				rgstName:Ext.get('workerNo').getValue()
			};
		}else
		{			
			var cust=Ext.getCmp('oset_TaskAllotMUI3929').getStore().
			getAt(rowindex3929M-(Ext.getCmp('oset_TaskAllotMUI3929').
			getStore().currentPage-1)*appConfig.getPageSize());
			
			oset_taskAllotMStr={
				id:{
					warehouseNo:Ext.getCmp('warehouseNoM3929').getValue(),
					taskId:Ext.getCmp('taskIdM3929').getValue()
				},	
				taskName:Ext.getCmp('taskNameM3929').getValue(),
				defaultFlag:Ext.getCmp('defaultFlagm3929').getValue(),
				memo:Ext.getCmp('memom3929').getValue() ,
				rgstDate:cust.data.rgstDate.time,
				rgstName:cust.data.rgstName,
				updtDate:new Date(),
				updtName:Ext.get('workerNo').getValue()
			};
		}
		Ext.Ajax.request({
			url:'oset_TaskAllotAction_saveOrupdateTaskAllotM',
			method:'post',
			params:{
				str:Ext.encode(oset_taskAllotMStr)
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){					
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('oset_TaskAllotMUI3929').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});	
	}
}

//加载修改页面的数据(切单规则明细)
function loadData3929D(rowindex3929D){
	var cust = Ext.getCmp('oset_TaskAllotDUI3929').getSelectionModel().getSelection()[0];
		
	Ext.getCmp('warehouseNoD3929').setValue(cust.data.warehouseNo);
	Ext.getCmp('taskIdD3929').setValue(cust.data.taskId);
	Ext.getCmp('outstockTypeD3929').setValue(cust.data.outstockType);
	Ext.getCmp('sourceTypeD3929').setValue(cust.data.sourceType);
	Ext.getCmp('operateType3929').setValue(cust.data.operateType);
	
	Ext.getCmp('allotRuleD3929').setValue(cust.data.allotRule);
	Ext.getCmp('boxFlagD3929').setValue(cust.data.boxFlag); 
	Ext.getCmp('paraValueD3929').setValue(cust.data.boxFlag);
	Ext.getCmp('taskTypeD3929').setValue(cust.data.taskType);
	Ext.getCmp('memoD3929').setValue(cust.data.memo);	
}

//加载修改页面的数据(切单规则头档)
function loadData3929M(rowindex3929M){	
	var cust=Ext.getCmp('oset_TaskAllotMUI3929').getStore().
	getAt(rowindex3929M-(Ext.getCmp('oset_TaskAllotMUI3929').
	getStore().currentPage-1)*appConfig.getPageSize());

	Ext.getCmp('warehouseNoM3929').setValue(cust.data.warehouseNo);
	Ext.getCmp('taskIdM3929').setValue(cust.data.taskId);
	Ext.getCmp('taskNameM3929').setValue(cust.data.taskName);
	Ext.getCmp('defaultFlagm3929').setValue(cust.data.defaultFlag);
	Ext.getCmp('memom3929').setValue(cust.data.memo);	
}