/**
 * 模块名称：商品类别维护 grid_1301_01
 * 模块编码：1301
 * 创建：周欢
 */
var g_IntRowindex1301=0;
var g_intSaveType1301=0;
var type1301='';
Ext.define('cms.controller.bdef.bdef_ArticleGroupController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_ArticleGroupUI',
	          'cms.view.bdef.window.bdef_ArticleGroupAddorEditWindow'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'bdef_ArticleGroupUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'bdef_ArticleGroupUI button[name=detailEdit]':{
				click:this.detailEdit
			},//删除
			'bdef_ArticleGroupUI button[name=detailDelete]':{
				click:this.detailDelete
			},//浏览
			'bdef_ArticleGroupUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'bdef_ArticleGroupUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导出
			'bdef_ArticleGroupUI button[name=detailExport]':{
				click:this.detailExport
			},//上一条记录
			'bdef_ArticleGroupAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_ArticleGroupAddorEditWindow button[name=next]':{
				click:this.next
			},//加载新增状态
			'bdef_ArticleGroupAddorEditWindow button[name=add]':{
				click:this.add
			},//保存
			'bdef_ArticleGroupAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bdef_ArticleGroupAddorEditWindow button[name=close]':{
				click:this.close
			},//商品类别树节点切换
			'bdef_ArticleGroupUI treepanel[id=moduleTree1301]':{
				beforeload:this.moduleTree1301Beforeload,
				selectionchange:this.moduleselectionchange
			},//商品大类切换
			'bdef_ArticleGroupAddorEditWindow combo[id=cmbLgroupNo1301]':{
				select:this.l_group_noselect
			},//加载中类商品
			'bdef_ArticleGroupAddorEditWindow combo[id=cmbMgroupNo1301]':{
				select:this.m_group_noselect
			},
			 //商品级别切换
			'bdef_ArticleGroupAddorEditWindow combo[id=txtGroupLevel1301]':{
				select:this.group_levelselect
			},
			'bdef_ArticleGroupAddorEditWindow form textfield[id=txtGroupNo1301]':{
				blur:this.groupNoBlur
			},//grid双击
			'bdef_ArticleGroupUI grid[id=grid_1301_01]':{
				itemdblclick:this.detailBrowse
			},
			'bdef_ArticleGroupAddorEditWindow field':{
				specialkey:this.boxkeydown
			},
			'bdef_ArticleGroupAddorEditWindow combo[id=cmbOwnerNo1301]':{
				select:this.cmbOwnerNo1301Select,
				change:this.groupNo1301Check
			},
			//选择货主加载树
			'bdef_ArticleGroupUI bdef_DefOwnerCombo[id=cmbFormOwner1301]':{
				select:this.cmbFormOwner1301Select
			},
			//判断商品编码是否唯一（用于填写编号判断）
			'bdef_ArticleGroupAddorEditWindow textfield[id=txtGroupNo1301]':{
				blur:this.groupNo1301Check
			},//大类编码选择
			'remoteCombo[id=lGroupNo1301]':{
				beforequery:this.lGroupNo1301BeforeQuery
			},//中类编码选择
			'remoteCombo[id=mGroupNo1301]':{
				beforequery:this.mGroupNo1301BeforeQuery
			},//小类编码选择
			'remoteCombo[id=sGroupNo1301]':{
				beforequery:this.sGroupNo1301BeforeQuery
			},
			//查询按扭
			'bdef_ArticleGroupUI button[id=btnSearch1301]':{
				click:this.btnSearch1301Click
			}
		});
	},
	initializtion:function()
	{
	   /*Ext.getCmp("cmbFormOwner1301").getStore().load();*/
	},
	//查询
	btnSearch1301Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1301').getValue())
				&&Ext.getCmp('cmbFormOwner1301').getValue()!='ALL'){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1301').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('lGroupNo1301').getValue())){
			var d2={
					columnId:'a.l_group_no',
					value:Ext.getCmp('lGroupNo1301').getValue()
				};
			listDetail1.push(d2);
		}
 		if(!Ext.isEmpty(Ext.getCmp('mGroupNo1301').getValue())){
			var d2={
					columnId:'a.m_group_no',
					value:Ext.getCmp('mGroupNo1301').getValue()
				};
			listDetail1.push(d2);
		}if(!Ext.isEmpty(Ext.getCmp('sGroupNo1301').getValue())){
			var d2={
					columnId:'a.group_no',
					value:Ext.getCmp('sGroupNo1301').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('cmbFormOwner1301').getValue()
			};
		Ext.apply(Ext.getCmp('grid_1301_01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_1301_01').getStore().removeAll();
		Ext.getCmp('grid_1301_01').getStore().load();
		
	},
	//小类编码加载 
	sGroupNo1301BeforeQuery:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1301').getValue())
 				&&Ext.getCmp('cmbFormOwner1301').getValue()!='ALL'){
			var d1={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1301').getValue()
				};
			listDetail1.push(d1);
		}		
		var params={
				strQuery:Ext.encode(listDetail1), 
				str:Ext.getCmp('sGroupNo1301').getValue()//小类编码
			};
		Ext.apply(Ext.getCmp('sGroupNo1301').getStore().proxy.extraParams,params);
		Ext.getCmp('sGroupNo1301').getStore().removeAll();
		Ext.getCmp('sGroupNo1301').getStore().load();
  	  },
	//中类编码加载 
	mGroupNo1301BeforeQuery:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1301').getValue())
 				&&Ext.getCmp('cmbFormOwner1301').getValue()!='ALL'){
			var d1={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1301').getValue()
				};
			listDetail1.push(d1);
		}		
		var params={
				strQuery:Ext.encode(listDetail1), 
				str:Ext.getCmp('mGroupNo1301').getValue()//中类编码
			};
		Ext.apply(Ext.getCmp('mGroupNo1301').getStore().proxy.extraParams,params);
		Ext.getCmp('mGroupNo1301').getStore().removeAll();
		Ext.getCmp('mGroupNo1301').getStore().load();
  	  },
	//大类编码加载 
	lGroupNo1301BeforeQuery:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1301').getValue())
 				&&Ext.getCmp('cmbFormOwner1301').getValue()!='ALL'){
			var d1={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1301').getValue()
				};
			listDetail1.push(d1);
		}		
		var params={
				strQuery:Ext.encode(listDetail1), 
				str:Ext.getCmp('lGroupNo1301').getValue()//大类编码
			};
		Ext.apply(Ext.getCmp('lGroupNo1301').getStore().proxy.extraParams,params);
		Ext.getCmp('lGroupNo1301').getStore().removeAll();
		Ext.getCmp('lGroupNo1301').getStore().load();
  	  },
	//判断科目代码是否唯一
	groupNo1301Check:function(){
		if(type1301=='add' &&Ext.getCmp('cmbOwnerNo1301').getValue()!="" && Ext.getCmp('cmbOwnerNo1301').getValue()!=null && 
				Ext.getCmp('txtGroupNo1301').getValue()!="" && Ext.getCmp('txtGroupNo1301').getValue()!=null){
			
			Ext.Ajax.request({
				url : 'bdef_ArticleGroupAction_groupNo1301Check',
				params : {
					strOwnerNo:Ext.getCmp('cmbOwnerNo1301').getValue(),
					strGroupLevel:Ext.getCmp('txtGroupLevel1301').getValue(),
					strGroupNo:Ext.getCmp('txtGroupNo1301').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.articleNoHaveExist);
			    		Ext.getCmp('txtGroupNo1301').setValue(null);
			    	}
				}
			});
		}
	},
	
	moduleTree1301Beforeload:function()
	{
		var strOwnerNo = Ext.getCmp("cmbFormOwner1301").getValue();
		var strWheresql={
			strOwnerNo:strOwnerNo
		};
		Ext.apply(Ext.getCmp('moduleTree1301').getStore().proxy.extraParams,strWheresql);
	},
	
	//选择货主加载树
	cmbFormOwner1301Select:function()
	{
		var strOwnerNo = Ext.getCmp("cmbFormOwner1301").getValue();
		Ext.getCmp('moduleTree1301').getStore().load({params:{strOwnerNo:strOwnerNo}});
	},
	
	/*
	 * @商品类别新增
	 */
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_ArticleGroupAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		type1301='add';
		
		var strOwnerNo = Ext.getCmp("cmbFormOwner1301").getValue();
		//Ext.getCmp('cmbOwnerNo1301').getStore().load({params:{strOwnerNo:strOwnerNo}});
		//Ext.getCmp('cmbOwnerNo1301').setValue(String(strOwnerNo));
		
		addCommMenu5('bdef_MenuWidget1301');
		this.setGroupdisable(false);
		commonSetMsterReadOnlyByArray(
				new Array('cmbLgroupNo1301','cmbMgroupNo1301'),true);
		g_intSaveType1301=0;
		this.addGroup1301();
	},
	
	//选择货主加载策略
	cmbOwnerNo1301Select:function()
	{
		var strOwnerNo = Ext.getCmp("cmbOwnerNo1301").getValue();
		var wheresql = 
			{
					strOwnerNo:strOwnerNo
			};
			Ext.apply(Ext.getCmp('cmbLgroupNo1301')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cmbLgroupNo1301').getStore()
					.removeAll();
			Ext.getCmp('cmbLgroupNo1301').getStore().load();
		Ext.Ajax.request
		({
			method:'post',
			url:'bdef_ArticleGroupAction_getStrategyByOwnerNo',
			params:
			{
				strOwnerNo:Ext.getCmp('cmbOwnerNo1301').getValue()
		    },
		    success:function(response)
		    {
		    	var varAddRes = Ext.decode(response.responseText);
		    		Ext.getCmp('cmbIStrategy1301').setValue(varAddRes[0].IStrategy);
		    		Ext.getCmp('cmbOStrategy1301').setValue(varAddRes[0].OStrategy);
		    		Ext.getCmp('cmbMStrategy1301').setValue(varAddRes[0].MStrategy);
		    		Ext.getCmp('cmbRiStrategy1301').setValue(varAddRes[0].riStrategy);
		    		Ext.getCmp('cmbRoStrategy1301').setValue(varAddRes[0].roStrategy);
		    		Ext.getCmp('cmbFcStrategy1301').setValue(varAddRes[0].fcStrategy);
		    		Ext.getCmp('cmbRsvStrategy11301').setValue(varAddRes[0].rsvStrategy1);
					Ext.getCmp('cmbRsvStrategy21301').setValue(varAddRes[0].rsvStrategy2);
					Ext.getCmp('cmbRsvStrategy31301').setValue(varAddRes[0].rsvStrategy3);
					Ext.getCmp('cmbRsvStrategy41301').setValue(varAddRes[0].rsvStrategy4);
					Ext.getCmp('cmbRsvStrategy51301').setValue(varAddRes[0].rsvStrategy5);
					Ext.getCmp('cmbRsvStrategy61301').setValue(varAddRes[0].rsvStrategy6);
					//Ext.getCmp('cmbTurnOverRule301').setValue(varAddRes[0].turnOverRule);
		    }
		});
	},
	
	/*
	 * 商品类别修改
	 */
	detailEdit:function(){
		type1301='edit';
		var objDate=Ext.getCmp('grid_1301_01').getSelectionModel().getSelection();
		if(objDate.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else
		{
			Ext.create('cms.view.bdef.window.bdef_ArticleGroupAddorEditWindow',
			{
				title:$i18n.titleupdate//修改
			}).show();
			commonMenu5PrevOrNext('bdef_MenuWidget1301','grid_1301_01',0);
			this.loadGroupData();
			this.setGroupdisable(true);
			Ext.getCmp('txtGroupNo1301').disable();
			Ext.getCmp('cmbLgroupNo1301').disable();
			Ext.getCmp('cmbMgroupNo1301').disable();
			commonSetMsterReadOnlyByArray(
					new Array('cmbOwnerNo1301'
					,'txtGroupLevel1301'
					,'txtGroupName1301','txtBatchId1301'),true);
			this.groupLevelChange(Ext.getCmp('txtGroupLevel1301').getValue());
			
			
			updateCommMenu5('bdef_MenuWidget1301');
			g_intSaveType1301=1;
		}
	},
	
	
	/*
	 * 商品类别删除
	 */
	detailDelete:function(){
		type1301='delete';
		var objDate=Ext.getCmp('grid_1301_01').getSelectionModel().getSelection();
		if(objDate.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else
		{
			Ext.Ajax.request({
				method:'post',
				url:'bdef_ArticleGroupAction_deleteGroupNo',
				params:
				{
					strGroupNo:objDate[0].get('groupNo'),
					strGroupLevel:objDate[0].get('grouplevelText'),
					strOwnerNo:Ext.getCmp('cmbFormOwner1301').getValue()
			    },
			    success:function(response)
			    {
			    	var data=Ext.decode(response.responseText);
					if(data.isSucc)
					{
						Ext.getCmp('grid_1301_01').getStore().load();
						Ext.example.msg($i18n.prompt,data.msg);
					}
					else
					{
						Ext.example.msg($i18n.prompt,data.msg);
					}
			    }
				});
			
			commonMenu5PrevOrNext('bdef_MenuWidget1301','grid_1301_01',0);
			this.loadGroupData();
			this.groupLevelChange(Ext.getCmp('txtGroupLevel1301').getValue());
			
			
			
		}
	},
	
	/*
	 * 商品类别浏览
	 */
	detailBrowse:function()
	{
		type1301='edit';
		var data = Ext.getCmp('grid_1301_01').getSelectionModel().getSelection();
		if (data.length != 0) 
		{//是否有选择中浏览的对象
			Ext.create('cms.view.bdef.window.bdef_ArticleGroupAddorEditWindow',{
				title:$i18n.titlebrowse//浏览
			}).show();
			commonMenu5PrevOrNext('bdef_MenuWidget1301','grid_1301_01',0);
			this.loadGroupData();
			this.groupLevelChange(Ext.getCmp('txtGroupLevel1301').getValue());
			commonSetFieldReadOnly('IdForm1301',true);
			commonSetFieldReadOnly('form_1301_02',true);
			commonSetFieldReadOnly('form_1301_03',true);
			
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
        browseCommMenu5('bdef_MenuWidget1301');
	},
	
	detailQuery:function()
	{
		Ext.create('cms.view.common.queryWindow',
		{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=1301;
		queryGrid='grid_1301_01';
	},
	
	detailExport:function()
	{
		
	},
	
	/**
	 * 上一条记录
	 * @param {} th
	 */
	prev:function(th){
		commonMenu5PrevOrNext('bdef_MenuWidget1301','grid_1301_01',-1);
		this.loadGroupData();
	},
	
	//键盘跳转
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=="txtGroupLevel1301" && th.value==2){
  				Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('txtGroupNo1301').focus();
	  		}else if(th.id=="txtGroupLevel1301" && th.value==1){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('cmbLgroupNo1301').focus();
	  		}else if(th.id=="cmbLgroupNo1301" && Ext.getCmp('txtGroupLevel1301').value==1){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('txtGroupNo1301').focus();
	  		}
	  		else if(th.id=="cmbStatus1301"){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('cmbCheckQtyFlag1301').focus();
	  		}else if(th.id=="cmbDoubleCheck1301"){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('txtAlarmrate1301').focus();
	  		}else if(th.id=="cmbTurnOverRule301"){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('numCheckExcess1301').focus();
	  		}else if(th.id=="numDivideExcess1301"){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(0);
	  			Ext.getCmp('cmbTemperatureFlag1301').focus();
	  		}else if(th.id=="cmbMixFlag1301"){
	  			Ext.getCmp('WindowTabId1301').setActiveTab(1);
	  			Ext.getCmp('cmbIStrategy1301').focus();
	  		}else if(th.id=="cmbRsvStrategy61301"){
	  			Ext.getCmp('bdef_MenuWidget1301').items.items[4].focus();
	  		}else{
				th.nextSibling().focus();
			}
        }
		
	},
	
    /**
	 * 下一条记录
	 * @param {} th
	 */
	next:function(th){
		commonMenu5PrevOrNext('bdef_MenuWidget1301','grid_1301_01',1);
		this.loadGroupData();
	},
	
	add:function()
	{
		this.addGroup1301();
		g_intSaveType1301=0;
	},
	
	save:function()
	{
		this.saveGroup1301();
	},
	
	close:function()
	{
		Ext.getCmp('bdef_ArticleGroupAddorEditWindow').close();
		Ext.getCmp('grid_1301_01').getStore().reload();
		
	},
	
	/*
	 * 商品类别树节点切换
	 */
	moduleselectionchange:function(emodel,selected)
	{
		if(selected.length>0)
		{					
			var varSql="";
			varSql=
			{
				groupLevel:'1',
				groupNo:selected[0].get('id'),
				ownerNo:Ext.getCmp('cmbFormOwner1301').getValue()
			};
			var wheresql = 
			{
					strWheresql:varSql
			};
			Ext.apply(Ext.getCmp('grid_1301_01')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('grid_1301_01').getStore()
					.removeAll();
			Ext.getCmp('grid_1301_01').getStore().load();
		}
	},
	
	/*
	 * 商品大类切换
	 */
	l_group_noselect:function()
	{
		var wheresql=
		{
			strQuery:Ext.getCmp('cmbLgroupNo1301').getValue(),
			strOwnerNo:Ext.getCmp('cmbOwnerNo1301').getValue()
		};
		Ext.apply(Ext.getCmp('cmbMgroupNo1301').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbMgroupNo1301').getStore().removeAll();
		Ext.getCmp('cmbMgroupNo1301').setValue(null);
		Ext.getCmp('cmbMgroupNo1301').getStore().load();
		if(Ext.getCmp('txtGroupLevel1301').getValue()==1 && type1301=='add'){
		   inheritParent(2);
		}	
	},
	
	//加载中类
	m_group_noselect:function(){
		if(type1301=='add'){
			inheritParent(1);
		}
		
	},
	
	/*
	 * 商品级别切换
	 */
	group_levelselect:function(combo)
	{
		this.groupLevelChange(combo.getValue());
	},
	
	groupNoBlur:function()
	{
		if(g_intSaveType1301==0)
		{
			Ext.Ajax.request({
			method:'post',
			url:'bdef_ArticleGroupAction_checkGroupNo',
			params:
			{
				groupNo:Ext.getCmp('txtGroupNo1301').getValue(),
				ownerNo:Ext.getCmp('cmbOwnerNo1301').getValue()
		    },
		    success:function(response)
		    {
		    	var varRres = Ext.decode(response.responseText);
		    	if(varRres=='1')
		    	{
		    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.articleGroupHaveExist);
		    		Ext.getCmp('txtGroupNo1301').setValue('');
		    		Ext.getCmp('txtGroupNo1301').focus();
		    	}
		    }
			});
		}
	},
	
	
	//新增前加载
	addGroup1301:function()
	{// 匹配开始：258   匹配开始：371
		Ext.getCmp('IdForm1301').getForm().reset();
		Ext.getCmp('form_1301_02').getForm().reset();
		Ext.getCmp('form_1301_03').getForm().reset();				
		//判断新增时是否有选中树节点
		if(Ext.getCmp('moduleTree1301').getSelectionModel( ).getSelection()[0] == undefined )
		{
			Ext.getCmp('cmbCheckQtyFlag1301').setValue('0');
			Ext.getCmp('numCheckQtyRate301').setValue(0);
			Ext.getCmp('cmbCheckWeightFlag1301').setValue('0');
			Ext.getCmp('numCheckWeightRate1301').setValue(0);
			Ext.getCmp('cmbQcRate1301').setValue(0);
			Ext.getCmp('cmbQcFlag1301').setValue('0');
			Ext.getCmp('cmbDoubleCheck1301').setValue('0');
			Ext.getCmp('txtAlarmrate1301').setValue('0');
			Ext.getCmp('txtFreezerate1301').setValue('0');
			if(Ext.getCmp('cmbTurnOverRule301').getStore().data.length>0)
			{
				Ext.getCmp('cmbTurnOverRule301').setValue(Ext.getCmp('cmbTurnOverRule301').getStore().getAt(0).data.value);	
			}
			Ext.getCmp('numCheckExcess1301').setValue(0);		
			Ext.getCmp('numUmCheckExcess1301').setValue(0);
			Ext.getCmp('numPickExcess1301').setValue(0);
			Ext.getCmp('cmbTemperatureFlag1301').setValue('0');
			Ext.getCmp('cmbScanFlag1301').setValue('0');
			Ext.getCmp('txtMeasureMode1301').setValue('0');
			Ext.getCmp('cmbPrintFlag1301').setValue('0');
			Ext.getCmp('numDivideExcess1301').setValue(0);
			Ext.getCmp('cmbMixFlag1301').setValue('1');
		}else
		{			
			// 匹配开始：276   匹配开始：328
			if(Ext.getCmp('moduleTree1301').getSelectionModel( ).getSelection()[0].get("id") == 0 )
			{				
				Ext.getCmp('cmbCheckQtyFlag1301').setValue('0');
				Ext.getCmp('numCheckQtyRate301').setValue(0);
				Ext.getCmp('cmbCheckWeightFlag1301').setValue('0');
				Ext.getCmp('numCheckWeightRate1301').setValue(0);
				Ext.getCmp('cmbQcRate1301').setValue(0);
				Ext.getCmp('cmbQcFlag1301').setValue('0');
				Ext.getCmp('cmbDoubleCheck1301').setValue('0');
				Ext.getCmp('txtAlarmrate1301').setValue('0');
				Ext.getCmp('txtFreezerate1301').setValue('0');
				if(Ext.getCmp('cmbTurnOverRule301').getStore().data.length>0)
				{
					Ext.getCmp('cmbTurnOverRule301').setValue(Ext.getCmp('cmbTurnOverRule301').getStore().getAt(0).data.value);	
				}
				Ext.getCmp('numCheckExcess1301').setValue(0);		
				Ext.getCmp('numUmCheckExcess1301').setValue(0);
				Ext.getCmp('numPickExcess1301').setValue(0);
				Ext.getCmp('cmbTemperatureFlag1301').setValue('0');
				Ext.getCmp('cmbScanFlag1301').setValue('0');
				Ext.getCmp('txtMeasureMode1301').setValue('0');
				Ext.getCmp('cmbPrintFlag1301').setValue('0');
				Ext.getCmp('numDivideExcess1301').setValue(0);
				Ext.getCmp('cmbMixFlag1301').setValue('1');
			}else
			{
				Ext.Ajax.request
				({
					method:'post',
					//获得选中节点的父节点
					url:'bdef_ArticleGroupAction_getParentNo',
					params:
					{
						strGroupNo:Ext.getCmp('moduleTree1301').getSelectionModel().getSelection()[0].get("id")
				    },
				    success:function(response)
				    {
				    	var varRes = Ext.decode(response.responseText);	
				    	Ext.getCmp('cmbCheckQtyFlag1301').setValue(varRes[0].checkQtyFlag);
						Ext.getCmp('numCheckQtyRate301').setValue(varRes[0].checkQtyRate);
						Ext.getCmp('cmbCheckWeightFlag1301').setValue(varRes[0].checkWeightFlag);
						Ext.getCmp('numCheckWeightRate1301').setValue(varRes[0].checkWeightRate);
						Ext.getCmp('cmbQcRate1301').setValue(varRes[0].qcRate);
						Ext.getCmp('cmbQcFlag1301').setValue(varRes[0].qcFlag);
						Ext.getCmp('cmbDoubleCheck1301').setValue(varRes[0].doubleCheck);
						Ext.getCmp('txtAlarmrate1301').setValue(varRes[0].alarmrate);
						Ext.getCmp('txtFreezerate1301').setValue(varRes[0].freezerate);
						Ext.getCmp('cmbTurnOverRule301').setValue(varRes[0].turnOverRule);
						Ext.getCmp('numCheckExcess1301').setValue(varRes[0].checkExcess);		
						Ext.getCmp('numUmCheckExcess1301').setValue(varRes[0].umCheckExcess);
						Ext.getCmp('numPickExcess1301').setValue(varRes[0].pickExcess);
						Ext.getCmp('cmbTemperatureFlag1301').setValue(varRes[0].temperatureFlag);
						Ext.getCmp('cmbScanFlag1301').setValue(varRes[0].scanFlag);
						Ext.getCmp('txtMeasureMode1301').setValue(varRes[0].measureMode);
						Ext.getCmp('cmbPrintFlag1301').setValue(varRes[0].printFlag);
						Ext.getCmp('numDivideExcess1301').setValue(varRes[0].divideExcess);
						Ext.getCmp('cmbMixFlag1301').setValue(varRes[0].mixFlag);
				    		if(varRes[0].groupLevel==2)
				    		{
				    			Ext.getCmp('cmbLgroupNo1301').getStore().add
				    			({
				    				value:varRes[0].groupNo,
				    				dropValue:'['+varRes[0].groupNo+']'+varRes[0].groupName,
				    				text:varRes[0].groupName
				    		    });
				    			Ext.getCmp('cmbLgroupNo1301').setValue(varRes[0].groupNo);
				    			Ext.getCmp('cmbMgroupNo1301').setValue("");
				    			Ext.getCmp('txtGroupLevel1301').setValue((parseInt(varRes[0].groupLevel)-1).toString());
				    			commonSetMsterReadOnlyByArray(
				    					new Array('cmbLgroupNo1301','cmbMgroupNo1301'),true);
				    		}else if(varRes[0].groupLevel==1)
				    		{
				    			Ext.getCmp('cmbLgroupNo1301').getStore().add
				    			({
				    				value:varRes[0].LGroupNo,
				    				dropValue:'['+varRes[0].LGroupNo+']'+varRes[0].LGroupName,
				    				text:varRes[0].LGroupName
				    		    });
				    			Ext.getCmp('cmbLgroupNo1301').setValue(varRes[0].LGroupNo);
				    			Ext.getCmp('cmbLgroupNo1301').fireEvent('select',this.l_group_noselect);
				    			Ext.getCmp('cmbMgroupNo1301').getStore().add
				    			({
				    				value:varRes[0].MGroupNo,
				    				dropValue:'['+varRes[0].groupNo+']'+varRes[0].groupName,
				    				text:varRes[0].groupName
				    		    });
				    			Ext.getCmp('cmbMgroupNo1301').setValue(varRes[0].groupNo);
				    			Ext.getCmp('txtGroupLevel1301').setValue((parseInt(varRes[0].groupLevel)-1).toString());
				    			
				    			commonSetMsterReadOnlyByArray(
				    					new Array('cmbLgroupNo1301'),false);
				    			commonSetMsterReadOnlyByArray(
				    					new Array('cmbMgroupNo1301'),true);
				    		}else if(varRes[0].groupLevel==0)
				    		{
				    			Ext.getCmp('cmbLgroupNo1301').getStore().add
				    			({
				    				value:varRes[0].LGroupNo,
				    				dropValue:'['+varRes[0].LGroupNo+']'+varRes[0].LGroupName,
				    				text:varRes[0].LGroupName
				    		    });
				    			Ext.getCmp('cmbLgroupNo1301').setValue(varRes[0].LGroupNo); 
				    			Ext.getCmp('cmbLgroupNo1301').fireEvent('select',this.l_group_noselect);
				    			Ext.getCmp('cmbMgroupNo1301').getStore().add
				    			({
				    				value:varRes[0].MGroupNo,
				    				dropValue:'['+varRes[0].MGroupNo+']'+varRes[0].MGroupName,
				    				text:varRes[0].MGroupName
				    		    });
				    			Ext.getCmp('cmbMgroupNo1301').setValue(varRes[0].MGroupNo);
				    			Ext.getCmp('txtGroupLevel1301').setValue(varRes[0].groupLevel);
				    			
				    			commonSetMsterReadOnlyByArray(
				    					new Array('cmbLgroupNo1301','cmbMgroupNo1301'),false);
				    		}
				    		Ext.getCmp('txtBatchId1301').setValue(varRes[0].batchId);
				    		//Ext.getCmp('cmbTurnOverRule301').setValue(varRes[0].turnOverRule);
				    }
				});
			}
			
		};// 匹配开始：276   匹配开始：328
		Ext.getCmp('cmbStatus1301').setValue('1');
		this.groupLevelChange(Ext.getCmp('txtGroupLevel1301').getValue());
		var strOwnerNo = Ext.getCmp('cmbFormOwner1301').getValue();
		var params={
				strOwnerNo:strOwnerNo
			};
		Ext.apply(Ext.getCmp('cmbOwnerNo1301').getStore().proxy.extraParams,params);
		Ext.getCmp('cmbOwnerNo1301').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo1301').getStore().load();
		Ext.getCmp('cmbOwnerNo1301').setValue(strOwnerNo);
	},// 匹配开始：258   匹配开始：371
	
	//保存商品类别
	saveGroup1301:function()
	{// 匹配开始:375   匹配开始：519
		
		if(Ext.getCmp('txtGroupLevel1301').getValue()=='1')
		{
			if(Ext.getCmp('cmbLgroupNo1301').getValue()==null)
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.enterLgroup);
				return;
			}
		}else if(Ext.getCmp('txtGroupLevel1301').getValue()=='0')
		{
			if(Ext.getCmp('cmbLgroupNo1301').getValue()==null)
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.enterLgroup);
				return;
			}else if(Ext.getCmp('cmbMgroupNo1301').getValue()==null)
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.enterMgroup);
				return;
			}
		}
		
		var strLGroupNo = "";
		var strLGroupName = "";
		var strMGroupNo = "";
		var strMGroupName = "";
		if(Ext.getCmp('txtGroupLevel1301').getValue()==1)
		{
			strLGroupNo = this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).substring(this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).indexOf("[")+1,
					this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).indexOf("]"));
			strLGroupName = this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).substring(this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).indexOf("]")+1,
					this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).length);
			strMGroupNo = Ext.getCmp('txtGroupNo1301').getValue();
			strMGroupName = Ext.getCmp('txtGroupName1301').getValue();
			
		}else if(Ext.getCmp('txtGroupLevel1301').getValue()==2){
			strLGroupNo=Ext.getCmp('txtGroupNo1301').getValue();
			strLGroupName=Ext.getCmp('txtGroupName1301').getValue();
		}else if(Ext.getCmp('txtGroupLevel1301').getValue()==0)
		{
			strLGroupNo = this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).substring(this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).indexOf("[")+1,
					this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).indexOf("]"));
			strLGroupName = this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).substring(this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).indexOf("]")+1,
					this.getComboDisplay1301(Ext.getCmp('cmbLgroupNo1301')).length);
			debugger;
			strMGroupNo = this.getComboDisplay1301(Ext.getCmp('cmbMgroupNo1301')).substring(this.getComboDisplay1301(Ext.getCmp('cmbMgroupNo1301')).indexOf("[")+1,
					this.getComboDisplay1301(Ext.getCmp('cmbMgroupNo1301')).indexOf("]"));
			strMGroupName = this.getComboDisplay1301(Ext.getCmp('cmbMgroupNo1301')).substring(this.getComboDisplay1301(Ext.getCmp('cmbMgroupNo1301')).indexOf("]")+1,
					this.getComboDisplay1301(Ext.getCmp('cmbMgroupNo1301')).length);
		}
		
		if(Ext.getCmp('IdForm1301').getForm().isValid() && Ext.getCmp('form_1301_02').getForm().isValid())
		{
			
		}else
		{
			if(!commonCheckIsInputAll('IdForm1301') || !commonCheckIsInputAll('form_1301_02'))
			{
				return;
			}else
			{
				return;
			}
		}
		
		if(Ext.getCmp('form_1301_03').getForm().isValid())
		{
			
		}else
		{
			if(!commonCheckIsInputAll('form_1301_03'))
			{
				return;
			}else
			{
				return;
			}
		}
		
			var group=
			{// 匹配开始:447   匹配开始：495
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					ownerNo:Ext.getCmp('cmbOwnerNo1301').getValue(),
					groupNo:Ext.getCmp('txtGroupNo1301').getValue(),
					groupLevel:Ext.getCmp('txtGroupLevel1301').getValue()
				},
				groupName:Ext.getCmp('txtGroupName1301').getValue(),
				batchId:Ext.getCmp('txtBatchId1301').getValue(),
				status:Ext.getCmp('cmbStatus1301').getValue(),
				
				LGroupNo:strLGroupNo,
				LGroupName:strLGroupName,
				MGroupNo:strMGroupNo,
				MGroupName:strMGroupName,
				
				checkQtyFlag:Ext.getCmp('cmbCheckQtyFlag1301').getValue(),
				checkQtyRate:Ext.getCmp('numCheckQtyRate301').getValue(),
				checkWeightFlag:Ext.getCmp('cmbCheckWeightFlag1301').getValue(),
				checkWeightRate:Ext.getCmp('numCheckWeightRate1301').getValue(),
				qcFlag:Ext.getCmp('cmbQcFlag1301').getValue(),
				qcRate:Ext.getCmp('cmbQcRate1301').getValue(),
				doubleCheck:Ext.getCmp('cmbDoubleCheck1301').getValue(),
				alarmrate:Ext.getCmp('txtAlarmrate1301').getValue(),
				freezerate:Ext.getCmp('txtFreezerate1301').getValue(),
				turnOverRule:Ext.getCmp('cmbTurnOverRule301').getValue(),
				checkExcess:Ext.getCmp('numCheckExcess1301').getValue(),
				umCheckExcess:Ext.getCmp('numUmCheckExcess1301').getValue(),
				pickExcess:Ext.getCmp('numPickExcess1301').getValue(),
				divideExcess:Ext.getCmp('numDivideExcess1301').getValue(),
				temperatureFlag:Ext.getCmp('cmbTemperatureFlag1301').getValue(),
				scanFlag:Ext.getCmp('cmbScanFlag1301').getValue(),
				measureMode:Ext.getCmp('txtMeasureMode1301').getValue(),
				mixFlag:Ext.getCmp('cmbMixFlag1301').getValue(),
				printFlag:Ext.getCmp('cmbPrintFlag1301').getValue(),
				
				IStrategy:Ext.getCmp('cmbIStrategy1301').getValue(),
				OStrategy:Ext.getCmp('cmbOStrategy1301').getValue(),
				MStrategy:Ext.getCmp('cmbMStrategy1301').getValue(),
				riStrategy:Ext.getCmp('cmbRiStrategy1301').getValue(),
				roStrategy:Ext.getCmp('cmbRoStrategy1301').getValue(),
				fcStrategy:Ext.getCmp('cmbFcStrategy1301').getValue(),
				rsvStrategy1:Ext.getCmp('cmbRsvStrategy11301').getValue(),
				rsvStrategy2:Ext.getCmp('cmbRsvStrategy21301').getValue(),
				rsvStrategy3:Ext.getCmp('cmbRsvStrategy31301').getValue(),
				rsvStrategy4:Ext.getCmp('cmbRsvStrategy41301').getValue(),
				rsvStrategy5:Ext.getCmp('cmbRsvStrategy51301').getValue(),
				rsvStrategy6:Ext.getCmp('cmbRsvStrategy61301').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date()
			};// 匹配开始:447   匹配开始：495
			
			var str=Ext.encode(group);
			Ext.Ajax.request
			({
				url:'bdef_ArticleGroupAction_saveGroup',
				method:'post',
				params:
				{
					str:str
				},
				success:function(response)
				{
					var data=Ext.decode(response.responseText);
					if(data.isSucc)
					{
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('grid_1301_01').getStore().removeAll();
						//Ext.getCmp('grid_1301_01').setValue('');
						Ext.getCmp('grid_1301_01').getStore().load();
						Ext.getCmp("cmbFormOwner1301").setValue(Ext.getCmp("cmbOwnerNo1301").getValue());
						var strOwnerNo = Ext.getCmp("cmbFormOwner1301").getValue();
						Ext.getCmp('moduleTree1301').getStore().load({params:{strOwnerNo:strOwnerNo}});
						
					}else
					{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
			
			//Ext.getCmp('cmbFormOwner1301').fireEvent('select',this.cmbFormOwner1301Select);
		
	},// 匹配开始:375   匹配开始：519
	
	//获得Combo显示的值
	getComboDisplay1301:function(combo) {
	    var value = combo.getValue();
	    var valueField = combo.valueField;
	    var record;
	    combo.getStore().each(function(r)
	    {
	        if(r.data[valueField] == value)
	        {
	            record = r;
	            return false;
	        }
	    });
	    return record ? record.get(combo.displayField) : null;
	},
	
	//级别切换
	groupLevelChange:function(combo)
	{
		if(type1301=='add' && combo=='1'  && Ext.getCmp('cmbLgroupNo1301').getValue()!=null && Ext.getCmp('cmbLgroupNo1301').getValue()!=''){
			inheritParent(2);
		}
		if(combo=='2')
		{
			Ext.getCmp('cmbLgroupNo1301').setValue(null);
			Ext.getCmp('cmbMgroupNo1301').setValue(null);
			commonSetMsterReadOnlyByArray(
					new Array('cmbLgroupNo1301','cmbMgroupNo1301'),true);
		
		}else if(combo=='1')
		{
			Ext.getCmp('cmbMgroupNo1301').setValue(null);
			
			commonSetMsterReadOnlyByArray(
					new Array('cmbLgroupNo1301'),false);
			commonSetMsterReadOnlyByArray(
					new Array('cmbMgroupNo1301'),true);

		}else if(combo=='0')
		{
			commonSetMsterReadOnlyByArray(
					new Array('cmbLgroupNo1301','cmbMgroupNo1301'),false);
		}
	},// 匹配开始:539   匹配开始：574
	
	/*
	 * 设置对应的输入框是否可编辑
	 */
	setGroupdisable:function(flag)
	{
		commonSetMsterReadOnlyByArray(
				new Array('cmbLgroupNo1301','cmbMgroupNo1301','txtGroupLevel1301'),flag);
	},
	
	//加载类别数据
	loadGroupData:function()
	{// 匹配开始:588   匹配开始：634
		var objLoadGroup=Ext.getCmp('grid_1301_01').getSelectionModel().getSelection()[0];
		Ext.getCmp('cmbOwnerNo1301').setValue(objLoadGroup.data.ownerNo);
		Ext.getCmp('txtGroupNo1301').setValue(objLoadGroup.data.groupNo);
		Ext.getCmp('txtGroupLevel1301').setValue(objLoadGroup.data.groupLevel);
		Ext.getCmp('txtGroupName1301').setValue(objLoadGroup.data.groupName);
		Ext.getCmp('txtBatchId1301').setValue(objLoadGroup.data.batchId),
		Ext.getCmp('cmbStatus1301').setValue(objLoadGroup.data.status),
		Ext.getCmp('cmbLgroupNo1301').getStore().add
		({
			value:objLoadGroup.data.LGroupNo,
			dropValue:'['+objLoadGroup.data.LGroupNo+']'+objLoadGroup.data.LGroupName,
			text:objLoadGroup.data.LGroupName
	    });
		Ext.getCmp('cmbLgroupNo1301').setValue(objLoadGroup.data.LGroupNo);
		Ext.getCmp('cmbMgroupNo1301').getStore().add
		({
			value:objLoadGroup.data.MGroupNo,
			dropValue:'['+objLoadGroup.data.MGroupNo+']'+objLoadGroup.data.MGroupName,
			text:objLoadGroup.data.MGroupName
	    });
		Ext.getCmp('cmbMgroupNo1301').setValue(objLoadGroup.data.MGroupNo);
		Ext.getCmp('cmbCheckQtyFlag1301').setValue(objLoadGroup.data.checkQtyFlag);
		Ext.getCmp('numCheckQtyRate301').setValue(objLoadGroup.data.checkQtyRate);
		Ext.getCmp('cmbCheckWeightFlag1301').setValue(objLoadGroup.data.checkWeightFlag);
		Ext.getCmp('numCheckWeightRate1301').setValue(objLoadGroup.data.checkWeightRate);
		Ext.getCmp('cmbQcFlag1301').setValue(objLoadGroup.data.qcFlag);
		Ext.getCmp('cmbQcRate1301').setValue(objLoadGroup.data.qcRate);
		Ext.getCmp('cmbDoubleCheck1301').setValue(objLoadGroup.data.doubleCheck);
		Ext.getCmp('txtAlarmrate1301').setValue(objLoadGroup.data.alarmrate);
		Ext.getCmp('txtFreezerate1301').setValue(objLoadGroup.data.freezerate);
		Ext.getCmp('cmbTurnOverRule301').setValue(objLoadGroup.data.turnOverRule);
		Ext.getCmp('numCheckExcess1301').setValue(objLoadGroup.data.checkExcess);
		Ext.getCmp('numUmCheckExcess1301').setValue(objLoadGroup.data.umCheckExcess);
		Ext.getCmp('numPickExcess1301').setValue(objLoadGroup.data.pickExcess);
		Ext.getCmp('numDivideExcess1301').setValue(objLoadGroup.data.divideExcess);
		Ext.getCmp('cmbTemperatureFlag1301').setValue(objLoadGroup.data.temperatureFlag);
		Ext.getCmp('txtMeasureMode1301').setValue(objLoadGroup.data.measureMode);
		Ext.getCmp('cmbScanFlag1301').setValue(objLoadGroup.data.scanFlag);
		Ext.getCmp('cmbMixFlag1301').setValue(objLoadGroup.data.mixFlag);
		Ext.getCmp('cmbPrintFlag1301').setValue(objLoadGroup.data.printFlag);
		Ext.getCmp('cmbIStrategy1301').setValue(objLoadGroup.data.IStrategy);
		Ext.getCmp('cmbOStrategy1301').setValue(objLoadGroup.data.OStrategy);
		Ext.getCmp('cmbMStrategy1301').setValue(objLoadGroup.data.MStrategy);
		Ext.getCmp('cmbRiStrategy1301').setValue(objLoadGroup.data.riStrategy);
		Ext.getCmp('cmbRoStrategy1301').setValue(objLoadGroup.data.roStrategy);
		Ext.getCmp('cmbFcStrategy1301').setValue(objLoadGroup.data.fcStrategy);
		Ext.getCmp('cmbRsvStrategy11301').setValue(objLoadGroup.data.rsvStrategy1);
		Ext.getCmp('cmbRsvStrategy21301').setValue(objLoadGroup.data.rsvStrategy2);
		Ext.getCmp('cmbRsvStrategy31301').setValue(objLoadGroup.data.rsvStrategy3);
		Ext.getCmp('cmbRsvStrategy41301').setValue(objLoadGroup.data.rsvStrategy4);
		Ext.getCmp('cmbRsvStrategy51301').setValue(objLoadGroup.data.rsvStrategy5);
		Ext.getCmp('cmbRsvStrategy61301').setValue(objLoadGroup.data.rsvStrategy6);
	}// 匹配开始:588   匹配开始：634

});

function inheritParent(flag){
	var SGroupNo='';
	if(flag==2){
		 SGroupNo = Ext.getCmp('cmbLgroupNo1301').getValue();
	}else if(flag==1){
		 SGroupNo = Ext.getCmp('cmbMgroupNo1301').getValue();
	}
	Ext.Ajax.request({
		url:'bdef_ArticleGroupAction_getLOrMgroup',
		method:'post',
		params:{
			strGroupLevel:flag,
			strGroupNo:SGroupNo,
			strOwnerNo:Ext.getCmp('cmbOwnerNo1301').getValue()
		},
		success:function(response){
			var data=Ext.decode(response.responseText);
			
			Ext.getCmp('cmbCheckQtyFlag1301').setValue(data.checkQtyFlag);
			Ext.getCmp('numCheckQtyRate301').setValue(data.checkQtyRate);
			Ext.getCmp('cmbCheckWeightFlag1301').setValue(data.checkWeightFlag);
			Ext.getCmp('numCheckWeightRate1301').setValue(data.checkWeightRate);
			Ext.getCmp('cmbQcFlag1301').setValue(data.qcFlag);
			Ext.getCmp('cmbQcRate1301').setValue(data.qcRate);
			Ext.getCmp('cmbDoubleCheck1301').setValue(data.doubleCheck);
			
			Ext.getCmp('txtAlarmrate1301').setValue(data.alarmrate);
			Ext.getCmp('txtFreezerate1301').setValue(data.freezerate);	
			if(Ext.getCmp('cmbTurnOverRule301').getStore().data.length>0)
			{
				Ext.getCmp('cmbTurnOverRule301').setValue(data.turnOverRule);	
			}
			
			Ext.getCmp('numCheckExcess1301').setValue(data.checkExcess);
			Ext.getCmp('numUmCheckExcess1301').setValue(data.umCheckExcess);
			Ext.getCmp('numPickExcess1301').setValue(data.pickExcess);
			Ext.getCmp('numDivideExcess1301').setValue(data.divideExcess);
			
			Ext.getCmp('cmbTemperatureFlag1301').setValue(data.temperatureFlag);			

						
			Ext.getCmp('cmbScanFlag1301').setValue(data.scanFlag);
			Ext.getCmp('txtMeasureMode1301').setValue(data.measureMode);
			Ext.getCmp('cmbMixFlag1301').setValue(data.mixFlag);
			
			Ext.getCmp('cmbIStrategy1301').setValue(data.IStrategy);
			Ext.getCmp('cmbOStrategy1301').setValue(data.OStrategy);
			Ext.getCmp('cmbMStrategy1301').setValue(data.MStrategy);
			Ext.getCmp('cmbRiStrategy1301').setValue(data.riStrategy);
			Ext.getCmp('cmbRoStrategy1301').setValue(data.roStrategy);
			Ext.getCmp('cmbFcStrategy1301').setValue(data.fcStrategy);
			Ext.getCmp('cmbRsvStrategy11301').setValue(data.rsvStrategy1);
			Ext.getCmp('cmbRsvStrategy21301').setValue(data.rsvStrategy2);
			Ext.getCmp('cmbRsvStrategy31301').setValue(data.rsvStrategy3);
			Ext.getCmp('cmbRsvStrategy41301').setValue(data.rsvStrategy4);
			Ext.getCmp('cmbRsvStrategy51301').setValue(data.rsvStrategy5);
			Ext.getCmp('cmbRsvStrategy61301').setValue(data.rsvStrategy6);				
		}
	});
}