/**
 * 模块名称：盘点计划单
 * 模块编码：8101
 * 创建：周欢
 */
var strRowindex=0;
var g_blnIsCanEdit8101=false;//是否可编辑：true：可编辑，false：不可编辑
var g_strStatus = '';//获取结案盘点单状态：0：盘点单处在以成需求但未定位状态；1：其他状态
Ext.define('cms.controller.fcdata.fcdata_PlanController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.fcdata.fcdata_PlanUI'
	         ],
	model:'',
	store:'',
	init:function(){
		this.control({
			
			//手建盘点计划单》单据列表》选择货主查询相应单据
			'fcdata_PlanUI combo[id=cmbOwnerNo8101_M]':{
				change:this.cmbOwnerNo8101_MChange
			},
			//手建盘点计划单》单据列表》选择盘点类型查询相应单据
			'fcdata_PlanUI radiogroup[id=raidioFcdata_typeMain8101]':{
				change:this.cmbOwnerNo8101_MChange
			},
			//手建盘点计划单》单据列表》查找
			'fcdata_PlanUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			//手建盘点计划单》单据列表》定位
			'fcdata_PlanUI toolbar[id=menu_M_8101] button[name=detailSend]':{
				click:this.detailSendClick
			},
			//手建盘点计划单》单据列表》结案
			'fcdata_PlanUI toolbar[id=menu_M_8101] button[name=detailEdit]':{
				click:this.detailEditClick
			},
			//取消订单  undoOrder
			'fcdata_PlanUI button[name=undoOrder]':{
				click:this.undoOrder
			},
			//手建盘点计划单》选择单据
			'fcdata_PlanUI grid[id=grid_M_8101]':{
				itemdblclick:this.griddblclick,
				selectionchange:this.grid_M_8101Selectionchange
			},
			//手建盘点计划单》上一单
			'fcdata_PlanUI button[name=userPrevButton]':{
				click:this.userPrevButton
			},
			//手建盘点计划单》下一单
			'fcdata_PlanUI button[name=userNextButton]':{
				click:this.userNextButton
			},
			//手建盘点计划单》新单
			'fcdata_PlanUI button[name=userAddButton]':{
				click:this.newSheet
			},
			//手建盘点计划单》保存
			'fcdata_PlanUI button[name=userSaveButton]':{
				click:this.save
			},
			//手建盘点计划单》撤销
			'fcdata_PlanUI button[name=userUndoButton]':{
				click:this.undo
			},
			//手建盘点计划单》删除
			'fcdata_PlanUI button[name=userDelButton]':{
				click:this.del
			},
			//手建盘点计划单》盘点类型
			'fcdata_PlanUI radiogroup[id=plan_type8101]':{
				change:this.plan_typeChange
			},
			//手建盘点计划单》盘点开始日期
			'fcdata_PlanUI form datefield[id=plan_date8101]':{
				blur:this.plan_dateBlur
			},
			//手建盘点计划单》盘点结束日期
			'fcdata_PlanUI form datefield[id=end_date8101]':{
				blur:this.end_timeBlur
			},
			//手建盘点计划单》储位盘点表格新增
			'fcdata_PlanUI grid[id=grid_03_8101]' : {
				beforeedit:this.grid_03_8101beforeedit,
				edit:this.grid_03_8101edit
			},
			//手建盘点计划单》新增
			'fcdata_PlanUI grid[id=grid_02_8101] button[name=detailAdd]':{
				click:this.detailAdd
			},
			//手建盘点计划单》删除
			'fcdata_PlanUI grid[id=grid_02_8101] button[name=detailDelete]':{
				click:this.detailDelete2
			},
			//手建盘点计划单》商品选择
			'bdef_DefArticleCombo[id=article_no8101]':{
				beforequery:this.beforequeryArticle_no,
				select:this.article_noselect
			},
			//手建盘点计划单》储位盘点》新增
			'fcdata_PlanUI grid[id=grid_03_8101] button[name=detailAdd]':{
				click:this.detailAdd
			},
			//手建盘点计划单》储位盘点》删除
			'fcdata_PlanUI grid[id=grid_03_8101] button[name=detailDelete]':{
				click:this.detailDelete2
			},
			//手建盘点计划单》储位盘点》仓区选择
			'combo[id=warearea8101]':{
				focus:this.warearea8101focus,
				select:this.warearea8101select
			},
			//手建盘点计划单》储位盘点》储区选择
			'combo[id=s_area_no8101]':{
				focus:this.s_area_no8101beforequery,
				select:this.s_area_no8101select
			},
			//手建盘点计划单》储位盘点》通道选择
			'combo[id=stock_no8101]':{
				focus:this.stock_no8101beforequery,
				select:this.stock_no8101select
			},
			//手建盘点计划单》商品盘点》商品类别选择
			'combo[id=group_no8101]':{
				focus:this.group_no8101focus,
				select:this.group_no8101select
			},
			//手建盘点计划单》储位盘点》储位选择
			'combo[id=cellNo8101]':{
				beforequery:this.cellNo8101beforequery
			},
			//手建盘点计划单》Tab切换
			'fcdata_PlanUI tabpanel[id=tabPId8101]':{
				tabchange:this.tabPIdtabchange
			},
			//盘点类型切换
			'fcdata_PlanUI [id=fcdata_type8101]':{
				change:this.fcdata_typeChange
			},
			'fcdata_PlanUI grid[id=grid_02_8101]':{
				beforeedit:this.grid_02_8101beforeedit,
				edit:this.grid_02_8101edit
			},//修改      7-13
			'fcdata_PlanUI button[name=userEditButton]':{
				click:this.edit
			}
		});
	},
	//控制网格是否可编辑
	grid_02_8101beforeedit:function(e)
	{  
	    if(!g_blnIsCanEdit8101)
	    {
	        e.cancel = true;
	        return  false;  
	    }
	},
	
	//修改      7-13
	edit:function(){
				g_blnIsCanEdit8101=true;
				//7-13添加
				//1、设置头档按钮
				commonEditButton('menu8101','add');
				//2、设置明细按钮
				Ext.getCmp('menu_03_8101').setDisabled(false);
				//3、设置头档FORM可以修改
				commonSetFieldReadOnly('fcdata_Plan_MForm',false);
				//4、设置头档某些字段不能修改
				commonSetMsterReadOnlyByArray(new Array('plan_no8101'),true);
				//5、设置表格可以修改
				g_blnIsCanEdit8101=true;
	},
	
	
	//商品检测新增》》编辑前校验
	grid_02_8101edit:function(editor,e,eOpts){
	if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('groupNo')==editor.context.record.data.groupNo;
				})!=-1 && editor.context.record.data.articleNo=='all')				
			{
		        editor.context.record.set('articleNo',null);
		        editor.context.record.set('articleName',null);
				Ext.example.msg($i18n_prompt.prompt,/*$i18n_prompt.thisGroupAlreadyExist*/'该商品类别下已有商品存在，不能选择all');
			}
	if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('articleNo')=='all'
					&& record.get('groupNo')==editor.context.record.data.groupNo;
				})!=-1)				
			{
		        editor.context.record.set('articleNo',null);
		        editor.context.record.set('articleName',null);
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.thisGroupAlreadyExist);
			}
	if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('articleNo')==editor.context.record.data.articleNo
					&& record.get('groupNo')==editor.context.record.data.groupNo
					&& record.get('articleNo')!='all';
				})!=-1)				
			{
		        editor.context.record.set('articleNo',null);
		        editor.context.record.set('articleName',null);
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.articleAlreadyExistRewrite);
			}
	if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('groupNo')!=null && record.get('groupNo')!=''
					&& editor.context.record.data.articleNo=='all';
				})!=-1)				
			{
		        editor.context.record.set('articleNo',null);
		        editor.context.record.set('articleName',null);
		        editor.context.record.set('groupNo',null);
		        editor.context.record.set('groupName',null);
				Ext.example.msg($i18n_prompt.prompt,'已有盘点商品存在，不能选all');
			}
	},
	
	//手建盘点计划单》单据列表》选择货主查询相应单据
	cmbOwnerNo8101_MChange:function(th,newValue,oldValue,eOpts)
	{
		//var strFcdataType = Ext.getCmp('raidioFcdata_typeMain8101').getValue().ft;
		var detail = [];
		var d={
				columnId:'a.fcdata_type',
				value:Ext.getCmp('raidioFcdata_typeMain8101').getValue().ft
			};
		detail.push(d);
		/*if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo8101_M').getValue()))
		{
			var d={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbOwnerNo8101_M').getValue()
				};
				detail.push(d);
		}*/
		var jsonDetail = Ext.encode(detail);
		var strWheresql = {
			str : jsonDetail,
			strOwnerNo : Ext.getCmp('cmbOwnerNo8101_M').getValue()
		};
		Ext.getCmp('grid_M_8101').getStore().currentPage=1;
		Ext.apply(Ext.getCmp('grid_M_8101')
				.getStore().proxy.extraParams,
				strWheresql);
		Ext.getCmp('grid_M_8101').getStore()
				.removeAll();
		Ext.getCmp('grid_M_8101').getStore()
				.load();
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('cmbOwnerNo8101_M').getStore().load();
		//7-18   初始化加载相应盘点类型的数据
		var detail = [];
		var d={
				columnId:'a.fcdata_type',
				value:Ext.getCmp('raidioFcdata_typeMain8101').getValue().ft
			};
		detail.push(d);
		var jsonDetail = Ext.encode(detail);
		var strWheresql = {
			str : jsonDetail,
			strOwnerNo : Ext.getCmp('cmbOwnerNo8101_M').getValue()
		};
		Ext.getCmp('grid_M_8101').getStore().currentPage=1;
		Ext.apply(Ext.getCmp('grid_M_8101')
				.getStore().proxy.extraParams,
				strWheresql);
		Ext.getCmp('grid_M_8101').getStore()
				.removeAll();
		Ext.getCmp('grid_M_8101').getStore()
				.load();
	},
	
	//定位
	detailSendClick:function()
	{
		var data = Ext.getCmp('grid_M_8101').getSelectionModel().getSelection();
		
		if(data.length!='0'){
			Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.locateOrNot,function(button,text){
				if(button=='yes')
				{
				
					var strMaster=
					{
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:data[0].data.warehouseNo,
							planNo:data[0].data.planNo
						},
						ownerNo:data[0].data.ownerNo,
						rgstName:Ext.get('workerNo').getValue(),
					
					};
					var objPlanM=Ext.encode(strMaster);
					
					var strParams=
					{
						objPlanM:objPlanM
					};
					Ext.Ajax.request({
						method:'post',
						url:'fcdata_PlanAction_sendFcdataPlan',
						params:strParams,
						success:function(response)
						{
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n_prompt.prompt,data.msg);
								Ext.getCmp('grid_M_8101').store.reload();
							}else
							{
								Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
							}
						}
					});
				}
			});
		}
	},
	
	//结案
	detailEditClick:function()
	{
		var objRecord = Ext.getCmp('grid_M_8101').getSelectionModel().getSelection();
		
		if(objRecord.length!='0'){
			var strParams={
					strPlanNo:objRecord[0].data.planNo
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_PlanAction_getStatus',
					params:strParams,
					success:function(response)
					{
						var ListStr = Ext.decode(response.responseText);
						if(ListStr[0]=='1')
						{
							g_strStatus = '0';
						}else
						{
							g_strStatus = '1';
						}
					}
				});
			if(g_strStatus == '0')
			{
				Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.theDataBetweenRequestAndLocat,function(button,text)
				{
					if(button=='yes')
					{
						closeAjax();
					}else
					{
						return;
					}
					
				});
			}else
			{
				Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.closeOrNot,function(button,text)
				{
					if(button=='yes')
					{
					   closeAjax();
					}
				});
			}
			
		}else
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choseCloseData);
			return;
		}
	},
	/**
	 * 新增
	 */
	newSheet:function(th,e,eOpts){
		addPlan(th);
		Ext.getCmp('tabPId8101').items.items[1].setVisible(true);
		commonEditButton('menu8101','add');
	},
	
	/**
	 * 查找
	 */
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=8101;
		queryGrid='grid_M_8101';					
	},
	
	/**
	 * 保存计划单
	 */
	save:function(th,e,eOpts)
	{
		var gridcount=Ext.getCmp('grid_02_8101').getStore().getCount();
		var grid2count=Ext.getCmp("grid_03_8101").getStore().getCount();
		var pdType= Ext.getCmp('plan_type8101').getValue().rb;
		
		if(pdType=='0')
		{	
			if(gridcount>0)
			{
				if(!commonCheckdetailgrid('grid_02_8101',0,gridcount-1))
				{
					return;
				}	
			}else
			{			
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.tableCannotBeNull);
				return;
			}			
		}else if(pdType=='1')
		{	
			if(grid2count>0)
			{
				if(!commonCheckdetailgrid('grid_03_8101',0,grid2count-1))
				{
					return;
				}	
			}else{			
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.tableCannotBeNull);
				return;
			}	
		}
		
		var strPlanNo=Ext.getCmp('plan_no8101').getValue();
		var strOwner=Ext.getCmp('owner8101').getValue();
		var planDate=Ext.getCmp('plan_date8101').getValue();
		var beginDate=Ext.getCmp('begin_date8101').getValue();
		var endDate=Ext.getCmp('end_date8101').getValue();
		var strPlanType=Ext.getCmp('plan_type8101').getValue().rb;
		var strRecedeDes=Ext.getCmp('recede_des8101').getValue();
		var strFcdataType=Ext.getCmp('fcdata_type8101').getValue().ft;
		var orgNo=Ext.getCmp('orgNo8101').getValue();
		
		var strMaster=
		{
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				planNo:strPlanNo
			},
			ownerNo:strOwner,
			planDate:beginDate,
			status:'10',
			createFlag:'0',
			sendFlag:'10',
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:Ext.Date.format(new Date,'Y-m-d H:m:s'),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:Ext.Date.format(new Date,'Y-m-d H:m:s'),
			beginDate:beginDate,
			endDate:endDate,
			planType:strPlanType,
			planRemark:strRecedeDes,
			fcdataType:strFcdataType,
			orgNo:orgNo
		};
		
		var strDetail1=[];
		if(pdType=='0')
		{
			for(var i=0;i<gridcount;i++)
			{
				var record=Ext.getCmp('grid_02_8101').getStore().getAt(i);
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						ownerNo:Ext.getCmp('owner8101').getValue(),
						planNo:strPlanNo,
						wareNo:'N',
						areaNo:'N',
						stockNo:'N',
						cellNo:'N',
						groupNo:record.get('groupNo'),
						articleNo:Ext.isEmpty(record.get('articleNo'))?'ALL':record.get('articleNo')
					},
					planId:i+1
				};
				strDetail1.push(d);
			};
		}else
		{
			for(var i=0;i<grid2count;i++)
			{
				var record=Ext.getCmp('grid_03_8101').getStore().getAt(i);
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						ownerNo:Ext.getCmp('owner8101').getValue(),
						planNo:strPlanNo,
						wareNo:Ext.isEmpty(record.get('wareNo'))?'ALL':record.get('wareNo'),
						areaNo:Ext.isEmpty(record.get('areaNo'))?'ALL':record.get('areaNo'),
						stockNo:Ext.isEmpty(record.get('stockNo'))?'ALL':record.get('stockNo'),
						cellNo:Ext.isEmpty(record.get('cellNo'))?'ALL':record.get('cellNo'),
						groupNo:'N',
						articleNo:'N'
					},
					planId:i+1
				};
				strDetail1.push(d);
			};
		}
		var objPlanM=Ext.encode(strMaster);
		var objPlanD1=Ext.encode(strDetail1);
		
		var params={
			objPlanM:objPlanM,
			objPlanD1:objPlanD1
		};
		Ext.Ajax.request({
			method:'post',
			url:'fcdata_PlanAction_saveFcdata_Plan',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n_prompt.prompt,data.msg);
					Ext.getCmp('plan_no8101').setValue(data.obj);
					Ext.getCmp('grid_M_8101').store.reload();
					commonEditButton('menu8101','save');
					g_blnIsCanEdit8101=false;
					commonSetFieldReadOnly('fcdata_Plan_MForm',true);
				}else{
					Ext.example.msg($i18n_prompt.prompt,data.msg);
				}
			}
		});
		
	},
	
	/**
	 * 撤销
	 */
	undo:function(){
		editPlan8101(strRowindex);
		commonEditButton('menu8101','undo');
		Ext.getCmp('menu8101').items.items[3].enable(true);
	},
	/**
	 * 删除
	 */
	del:function()
	{
		var planNo=Ext.String.trim(Ext.getCmp('plan_no8101').getValue());
		Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.deleteOrNot,function(button,text)
		{
			if(button=='yes')
			{
				var params={
					planNo:planNo
				};
				Ext.Ajax.request({
					method:'post',
					url:'fcdata_PlanAction_deleteFcdata_Plan',
					params:params,
					success:function(response)
					{
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							if(typeof(Ext.getCmp('grid_M_8101'))!="undefined")
							{
								var g=Ext.getCmp('grid_M_8101');
								var atindex=g.getStore().findExact('planNo',Ext.getCmp('plan_no8101').getValue());
								g.getStore().removeAt(atindex);
								var count=g.store.getCount();
									
								if(count==0)
								{
									addPlan();
								}else if(atindex==-1 && count!=0)
								{
									editPlan8101(0);
								}else if(atindex<count)
								{
									editPlan8101(atindex);
								}else 
								{
									editPlan8101(count-1);
								};
							};
						};
					}
				});
			};
		});
	},
		
	/**
	 * radio切换
	 * @param {} obj
	 * @param {} newValue
	 * @param {} oldValue
	 * @param {} eOpts
	 */
	plan_typeChange:function(obj,newValue,oldValue,eOpts)
	{
		if(newValue.rb=='0')
		{
				Ext.getCmp('grid_03_8101').hide();
				Ext.getCmp('grid_02_8101').show();		
				Ext.getCmp('grid_02_8101').setVisible(true);
				Ext.getCmp('grid_03_8101').getStore().removeAll();
		}else if(newValue.rb=='1'){
				Ext.getCmp('grid_02_8101').hide();
				Ext.getCmp('grid_03_8101').show();	
				Ext.getCmp('grid_03_8101').setVisible(true);
				Ext.getCmp('grid_02_8101').getStore().removeAll();
		}else{
			Ext.getCmp('grid_02_8101').hide();
			Ext.getCmp('grid_03_8101').hide();
		}
	},
	
	/**
	 * 盘点类别切换
	 */
	fcdata_typeChange:function(obj,newValue,oldValue,eOpts)
	{
		if(newValue.ft=='1')
		{
			Ext.getCmp('plan_type8101').items.items[0].show();
		}else if(newValue.ft=='2'){
			Ext.getCmp('plan_type8101').items.items[0].show();
		}else{
			Ext.getCmp('plan_type8101').items.items[1].setValue(true);
			Ext.getCmp('plan_type8101').items.items[0].hide();
		}
	},
	
	/**
	 * 盘点日期判断
	 */
	plan_dateBlur:function()
	{
		if(Ext.getCmp('end_date8101').getValue()!=null)
		{
			if(Ext.getCmp('end_date8101').getValue()!=''
				&& Ext.getCmp('end_date8101').getValue()<
					Ext.getCmp('plan_date8101').getValue())
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.planDateCheck);
				Ext.getCmp('plan_date8101').setValue('');		
			}
		};
	},
	
	/**
	 * 结束日期判断
	 */
	end_timeBlur:function()
	{
		if(Ext.getCmp('plan_date8101').getValue()!=null)
		{
			if(Ext.getCmp('plan_date8101').getValue()!=''
				&& Ext.getCmp('plan_date8101').getValue()>
					Ext.getCmp('end_date8101').getValue())
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.endDateShouldBelate);
				Ext.getCmp('end_date8101').setValue('');
			}
		};
	},
	
	//控制网格是否可编辑
	grid_03_8101beforeedit:function(e)
	{  
	    if(!g_blnIsCanEdit8101)
	    {
	        e.cancel = true;
	        return  false;  
	    }
	},
	
	//储位检测新增》》编辑前校验
	grid_03_8101edit:function(editor,e,eOpts)
	{
		if(editor.grid.getStore().
					findBy(function(record, id) {  
							return record.internalId!=editor.context.record.internalId 
							&& record.get('wareNo')==editor.context.record.data.wareNo;
						})!=-1 && editor.context.record.data.areaNo == 'all')				
					{
						Ext.example.msg($i18n_prompt.prompt,/*$i18n_prompt.thisWarehouseNoNoAlreadyExist*/'该仓区下已有储区，不能选all');
						editor.context.record.set('areaNo',null);
						editor.context.record.set('areaName',null);
						editor.context.record.set('stockNo',null);
						editor.context.record.set('cellNo',null);
						//return
					}
		if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('wareNo')==editor.context.record.data.wareNo
						&& record.get('areaNo')==editor.context.record.data.areaNo;
					})!=-1 && editor.context.record.data.stockNo == 'all')				
				{
					Ext.example.msg($i18n_prompt.prompt,/*$i18n_prompt.thisWarehouseNoNoAlreadyExist*/'该储区下已有通道，不能选all');
					editor.context.record.set('stockNo',null);
					editor.context.record.set('cellNo',null);
					//return
				}
		if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('wareNo')==editor.context.record.data.wareNo
						&& record.get('areaNo')==editor.context.record.data.areaNo
						&& record.get('stockNo')==editor.context.record.data.stockNo;
					})!=-1 && editor.context.record.data.cellNo == 'all')				
				{
					Ext.example.msg($i18n_prompt.prompt,/*$i18n_prompt.thisWarehouseNoNoAlreadyExist*/'该通道下已有储位，不能选all');
					editor.context.record.set('cellNo',null);
				}
	    if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('wareNo')=='all';
				})!=-1)				
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.thisWarehouseNoNoAlreadyExist);
				editor.context.record.set('wareNo',null);
				editor.context.record.set('wareName',null);
				//return
			}
	    if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('wareNo')==editor.context.record.data.wareNo
						&& record.get('areaNo')=='all';
					})!=-1)				
				{
					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.thisWareNoAlreadyExist);
					editor.context.record.set('stockNo',null);
				}
	    if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('wareNo')==editor.context.record.data.wareNo
						&& record.get('areaNo')==editor.context.record.data.areaNo
						&& record.get('stockNo')=='all';
					})!=-1)				
				{
					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.thisAreaNoAlreadyExist);
					//return
					editor.context.record.set('stockNo',null);
				}
	    if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('wareNo')==editor.context.record.data.wareNo
						&& record.get('areaNo')==editor.context.record.data.areaNo
						&& record.get('stockNo')==editor.context.record.data.stockNo
						&& record.get('cellNo')=='all';
					})!=-1)				
				{
					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.thisStockNoAlreadyExist);
					editor.context.record.set('cellNo',null);
				}
	    if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('wareNo')==editor.context.record.data.wareNo
						&& record.get('areaNo')==editor.context.record.data.areaNo
						&& record.get('stockNo')==editor.context.record.data.stockNo
						&& record.get('cellNo')!='all'
						&& record.get('cellNo')==editor.context.record.data.cellNo;
					})!=-1)				
				{
					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.thisCellNoAlreadyExist);
					//return
					editor.context.record.set('cellNo',null);
				}
		if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('wareNo')!=null && record.get('wareNo')!=''
					&& editor.context.record.data.wareNo=='all';
				})!=-1)				
			{
		        editor.context.record.set('wareNo',null);
		        editor.context.record.set('wareName',null);
		        editor.context.record.set('areaNo',null);
		        editor.context.record.set('areaName',null);
		        editor.context.record.set('stockNo',null);
		        editor.context.record.set('cellNo',null);
				Ext.example.msg($i18n_prompt.prompt,'已有盘点区存在，不能选all');
			}
	},
	
	/**
	 * grid双击
	 */
	griddblclick:function(th, record,  item,  index, e, eOpts)
	{
		Ext.getCmp('tabPId8101').items.items[1].setVisible(true);	
		strRowindex=index;
		this.tabPIdtabchange;
	},
	
	//盘点单单头切换
	grid_M_8101Selectionchange:function(th,selected,eOpts)
	{
		if(selected.length!='0'){
			if(selected[0].get('status')=='10'){
				Ext.getCmp('menu_M_8101').items.items[1].enable(true);
				Ext.getCmp('menu_M_8101').items.items[2].disable(true);
				var strFcdataType = Ext.getCmp('grid_M_8101').getSelectionModel().getSelection()[0].get('fcdataType');
			
			}else if(selected[0].get('status')=='11'){
			
				Ext.getCmp('menu_M_8101').items.items[1].disable(true);
				Ext.getCmp('menu_M_8101').items.items[2].enable(true);
			}else if(selected[0].get('status')=='13'){
				Ext.getCmp('menu_M_8101').items.items[1].disable(true);
				Ext.getCmp('menu_M_8101').items.items[2].disable(true);
			}			
		}
	},
	
	//上一条
	userPrevButton:function(){
		strRowindex=strRowindex-1;
		editPlan8101(strRowindex);
	},
	
	//下一条
	userNextButton:function(){
		strRowindex=strRowindex+1;
		editPlan8101(strRowindex);
	},
	
	//取消订单
	undoOrder:function(){
		debugger;
		var data = Ext.getCmp('grid_M_8101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			if(data[0].data.status!='10'){
				Ext.example.msg("提示","该盘点单不能取消");
			}else{
				Ext.Msg.confirm('提示','确定取消该盘点订单？',function(button,text){
					if(button=='yes'){
						var strParams=
						{
						    strPlanNo:data[0].data.planNo
						};
						Ext.Ajax.request({
							method:'post',
							url:'fcdata_PlanAction_closeOrder',
							params:strParams,
						    success:function(response){
						    	var data = Ext.decode(response.responseText);
						    	if(data.isSucc){					
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('grid_M_8101').getStore().removeAll();
							 	  	Ext.getCmp('grid_M_8101').getStore().reload();
								}else{
									Ext.example.msg($i18n.prompt,data.msg);
								}
						    }
						});
					}
				});
			}
		}		
	},
	
	/**
	 * 表体新增
	 */
	detailAdd:function(){
		if(Ext.getCmp('plan_type8101').getValue().rb=='0'){
			var store = Ext.getCmp('grid_02_8101').getStore();
			var count = store.getCount();
			var j = count * 1 - 1;
			if(j>=0){
			}else{
				if(!commonCheckIsInputAll('fcdata_Plan_MForm')){
					return;
				}
				commonSetMsterReadOnlyByArray(
				new Array('plan_type8101','plan_no8101','fcdata_type8101'),true);
			}
			
			var r=Ext.create('cms.model.fcdata.fcdata_PlanDModel',{});
			store.add(r);
		
			Ext.getCmp('grid_02_8101').editingPlugin.startEdit(count,1);
		}else if(Ext.getCmp('plan_type8101').getValue().rb=='1')
		{
			var store = Ext.getCmp('grid_03_8101').getStore();
			var count = store.getCount();
			var j = count * 1 - 1;
			if(j>=0)
			{
			}else
			{
				if(!commonCheckIsInputAll('fcdata_Plan_MForm'))
				{
					return;
				}
				commonSetMsterReadOnlyByArray(
				new Array('plan_type8101','plan_no8101'),true);
			}
			var r=Ext.create('cms.model.fcdata.fcdata_PlanDModel',{});
			store.add(r);
			Ext.getCmp('grid_03_8101').editingPlugin.startEdit(count,1);
		}
	},
	
	/**
	 * 表体删除
	 */
	detailDelete2:function()
	{
		if(Ext.getCmp('plan_type8101').getValue().rb=='0')
		{
			var data = Ext.getCmp('grid_02_8101').getSelectionModel()
				.getSelection();
			if(data.length!='0'){
				Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.deleteOrNot,function(button,text)
				{
					if(button=='yes')
					{
						Ext.getCmp('grid_02_8101').getStore().remove(data);					
						if(Ext.getCmp("grid_02_8101").getStore().getCount()==0)
						{
							commonSetMsterReadOnlyByArray(new Array('plan_type8101',
							'plan_no8101'),false);
						};
					}
				});
			}else{
				Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.choseDeleteRecord);
				return;
			}
		}else{
			var data = Ext.getCmp('grid_03_8101').getSelectionModel()
				.getSelection();
			if(data.length!='0')
			{
				Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.deleteOrNot,function(button,text){
					if(button=='yes')
					{
						Ext.getCmp('grid_03_8101').getStore().remove(data);					
						if(Ext.getCmp("grid_03_8101").getStore().getCount()==0)
						{
							commonSetMsterReadOnlyByArray(new Array('plan_type8101',
							'plan_no8101'),false);
						};
					}
				});
			}else{
				Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.choseDeleteRecord);
				return;
			}
		}
		
	},
	/**
	 * 手建盘点计划单》商品加载前事件
	 * @param {} queryEvent
	 * @param {} eOpts
	 */
	beforequeryArticle_no:function(queryEvent,eOpts)
	{
		debugger;
		var wheresql = {
				strGroupNo : Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].get('groupNo')
			};
			Ext.apply(Ext.getCmp('article_no8101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('article_no8101').getStore().removeAll();
			Ext.getCmp('article_no8101').getStore().load();
	},
	
	/**
	 * 商品选择
	 * @param {} combo
	 */
	article_noselect:function(combo,records,eOpts)
	{
		Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].set('articleName', records[0].data.text);
		Ext.Ajax.request({
			method:'post',
			async :  false,
			url:'fcdata_PlanAction_getGroupByArticle',
			params:{
				strArticleNo:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('grid_02_8101').getSelectionModel().getSelection();
		    	data[0].set('groupNo',res[0].groupNo);
		    	data[0].set('groupName',res[0].groupName);
		    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);
		    }
		});
	},
	
	//加载仓区下拉数据
	warearea8101focus:function()
	{
		var strDetail1 = [];
		var d={
			columnId:'a.org_No',
			value:Ext.getCmp('orgNo8101').getValue()
		};
		strDetail1.push(d);
		var jsonDetail1 = Ext.encode(strDetail1);
		var wheresql = {
				str : jsonDetail1,
				strOwnerNo : Ext.getCmp('owner8101').getValue()
			};
		Ext.apply(Ext.getCmp('warearea8101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('warearea8101').getStore().removeAll();
		Ext.getCmp('warearea8101').getStore().load();
	},
	
	//选中仓区
	warearea8101select:function(combo,records,eOpts){
		Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('wareName', records[0].data.text);
		if(Ext.getCmp('warearea8101').getValue()=='all')
		{
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('areaNo', 'all');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('areaName', 'all');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('stockNo', 'all');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('cellNo', 'all');
			commonSetFieldReadOnly('s_area_no8101',true);
			commonSetFieldReadOnly('stock_no8101',true);
			commonSetFieldReadOnly('cellNo8101',true);
		}else
		{
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('areaNo', '');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('areaName', '');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('stockNo', '');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('cellNo', '');
		}
		
	},
	
	//加载商品类别下拉数据
	group_no8101focus:function()
	{
		var wheresql = {
			strOwnerNo : Ext.getCmp('owner8101').getValue()
		};
		Ext.apply(Ext.getCmp('group_no8101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('group_no8101').getStore().removeAll();
		Ext.getCmp('group_no8101').getStore().load();
	},
	
	//填充商品类别名称
	group_no8101select:function(combo,records,eOpts)
	{
		Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].set('groupName', records[0].data.text);
		if(Ext.getCmp('grid_02_8101').getStore().findExact('groupNo','all')>=0)
		{
			Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.allGroupNoSelected);
			combo.clearValue();
			return;
		};
		if(Ext.getCmp('group_no8101').getValue()=='all'){
			Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].set('articleNo', 'all');
			Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].set('articleName', 'all');
			commonSetFieldReadOnly('article_no8101',true);
		}else
		{
			Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].set('articleNo', '');
			Ext.getCmp('grid_02_8101').getSelectionModel().getSelection()[0].set('articleName', '');
		}
	},
	/**
	 * 手建盘点计划单》储区加载前事件
	 * @param {} queryEvent
	 * @param {} eOpts
	 */
	s_area_no8101beforequery:function(queryEvent,eOpts)
	{
		var strDetail1 = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].get('wareNo')
		};
		strDetail1.push(d);
		var jsonDetail1 = Ext.encode(strDetail1);
		var wheresql = {
			str : jsonDetail1,
			strOwnerNo:Ext.getCmp('owner8101').getValue()
		};
		
		Ext.apply(Ext.getCmp('s_area_no8101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('s_area_no8101').getStore().removeAll();
		Ext.getCmp('s_area_no8101').getStore().load();
	},
	
	//选中盘点储区，给相应字段赋值
	s_area_no8101select:function(combo,records,eOpts)
	{
		Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('areaName', records[0].data.text);
		if(Ext.getCmp('s_area_no8101').getValue()=='all'){
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('stockNo', 'all');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('cellNo', 'all');
			commonSetFieldReadOnly('stock_no8101',true);
			commonSetFieldReadOnly('cellNo8101',true);
		}else
		{
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('stockNo', '');
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('cellNo', '');
		}
	},
	
	//通道下拉数据加载
	stock_no8101beforequery:function(queryEvent,eOpts)
	{
		var strDetail1 = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].get('wareNo')
		};
		strDetail1.push(d);
		var d={
			columnId:'a.area_No',
			value:Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].get('areaNo')
		};
		strDetail1.push(d);
		var jsonDetail1 = Ext.encode(strDetail1);
		var wheresql = {
			str : jsonDetail1,
			strOwnerNo:Ext.getCmp('owner8101').getValue()
		};
		
		Ext.apply(Ext.getCmp('stock_no8101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('stock_no8101').getStore().removeAll();
		Ext.getCmp('stock_no8101').getStore().load();
	},
	
	//选中盘点通道，给相应字段赋值
	stock_no8101select:function(combo,records,eOpts)
	{
		if(Ext.getCmp('stock_no8101').getValue()=='all'){
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('cellNo', 'all');
			commonSetFieldReadOnly('cellNo8101',true);
		}else
		{
			Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].set('cellNo', '');
		}
	},
	//储位数据加载
	cellNo8101beforequery:function(queryEvent,eOpts)
	{
		var strDetail1 = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].get('wareNo')
		};
		strDetail1.push(d);
		var d={
			columnId:'a.area_No',
			value:Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].get('areaNo')
		};
		strDetail1.push(d);
		var d={
			columnId:'a.stock_No',
			value:Ext.getCmp('grid_03_8101').getSelectionModel().getSelection()[0].get('stockNo')
		};
		strDetail1.push(d);
		var jsonDetail1 = Ext.encode(strDetail1);
		var strWheresql = {
			strJson : jsonDetail1,
			strOwnerNo:Ext.getCmp('owner8101').getValue()

		};
		Ext.apply(Ext.getCmp('cellNo8101').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cellNo8101').getStore().removeAll();
		Ext.getCmp('cellNo8101').getStore().load();
	},
	/*deleteArticle:function(){
		var data = Ext.getCmp('grid_02_8101').getSelectionModel().getSelection();
		if(data.length==0){
			alert('请选择要删除的行');
			return
		}else{
			Ext.Msg.confirm("提示", "确定删除数据？", function(button, text) {
				if (button == 'yes') {
					Ext.getCmp('grid_02_8101').getStore().remove(data);
					if(Ext.getCmp("grid_02_8101").getStore().getCount()==0)
					{
						commonSetMsterReadOnlyByArray(
						new Array('plan_type8101','plan_no8101'),false);
					}
				}
			})
		}
	}*/
	//tab页面切换
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts )
	{
		debugger;
		if(newCard.itemId=='tabPId8101i'){
			var data = Ext.getCmp('grid_M_8101').getSelectionModel().getSelection();
			if(data.length!=0){
				strRowindex=data[0].index;
				editPlan8101(strRowindex);
			}
		}
	}
});

/**
 * 新增前加载
 */
function addPlan(th)
{
	Ext.getCmp('fcdata_Plan_MForm').getForm().reset();
	Ext.getCmp("plan_no8101").setValue($i18n_prompt.autogenerationWhenSave);
	if(Ext.getCmp('owner8101').getStore().data.length>0)
	{
		Ext.getCmp('owner8101').setValue(Ext.getCmp('owner8101').getStore().getAt(0).data.value);		
	}
	Ext.getCmp('plan_date8101').setValue(new Date());
	Ext.getCmp('begin_date8101').setValue(new Date());
	Ext.getCmp('end_date8101').setValue(new Date());
	Ext.getCmp('orgNo8101').setValue('001');
	Ext.getCmp('plan_type8101').items.items[1].setValue(true);
	Ext.getCmp('fcdata_type8101').items.items[1].setValue(true);
	Ext.get('rgstName8101').dom.innerHTML=Ext.get('workerNo').getValue();
	Ext.get('rgstDate8101').dom.innerHTML=Ext.Date.format(new Date(),'Y-m-d H:m:s');
	Ext.getCmp('grid_02_8101').getStore().removeAll();
	Ext.getCmp('grid_03_8101').getStore().removeAll();
	//1、设置头档按钮
	commonEditButton('menu8101','add');
	//2、设置明细按钮
	Ext.getCmp('menu_03_8101').setDisabled(false);
	//3、设置头档FORM可以修改
	commonSetFieldReadOnly('fcdata_Plan_MForm',false);
	//4、设置头档某些字段不能修改
	commonSetMsterReadOnlyByArray(new Array('plan_no8101'),true);
	//5、设置表格可以修改
	g_blnIsCanEdit8101=true;
};

/**
 * 填充数据
 * @param {} grid
 * @param {} strRowindex
 * @param {} colIndex
 */
function editPlan8101(strRowindex)
{
	//1、控制上下单
	if(strRowindex==0)
	{
		Ext.getCmp('menu8101').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu8101').items.items[0].enable(true);
	}
	if(strRowindex==Ext.getCmp('grid_M_8101').getStore().getCount()-1)
	{		
		Ext.getCmp('menu8101').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu8101').items.items[1].enable(true);
	}
	commonEditButton('menu8101','dbclick');
	//2、控制头档只读
	commonSetFieldReadOnly('fcdata_Plan_MForm',true);
	//3、控制表格只读
	g_blnIsCanEdit8101=false;
	//4、控制明细表按钮
	var record=Ext.getCmp('grid_M_8101').getStore().getAt(strRowindex-(Ext.getCmp('grid_M_8101').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('plan_no8101').setValue(record.data.planNo);
	Ext.getCmp('owner8101').setValue(record.data.ownerNo);
	Ext.getCmp('plan_date8101').setValue(record.data.beginDate);
	Ext.getCmp('begin_date8101').setValue(record.data.beginDate);
	Ext.getCmp('end_date8101').setValue(record.data.endDate);
	Ext.getCmp('orgNo8101').setValue(record.data.orgNo);
	Ext.getCmp('fcdata_type8101').items.items[parseInt(record.data.fcdataType)-1].setValue(true);
	Ext.getCmp('plan_type8101').items.items[parseInt(record.data.planType)].setValue(true);
	Ext.getCmp('recede_des8101').setValue(record.data.planRemark);
	Ext.get('rgstName8101').dom.innerHTML=record.data.rgstName;
	Ext.get('rgstDate8101').dom.innerHTML=Ext.Date.format(record.data.rgstDate,'Y-m-d H:m:s');  
	Ext.get('updtName8101').dom.innerHTML=record.data.updtName;
	Ext.get('updtDate8101').dom.innerHTML=Ext.Date.format(record.data.updtDate,'Y-m-d H:m:s');  
	//5.根据盘点类型和状态控制修改按钮的状态        7-18添加
	if(record.data.fcdataType == '1' && record.data.status == '10'){
		Ext.getCmp('menu8101').items.items[3].enable(true);
	}else{
		Ext.getCmp('menu8101').items.items[3].disable(true);
	}
	
	var sql='';
	var warehouseNo=record.data.warehouseNo;
	var planNo=record.data.planNo;
	var sql={
		warehouseNo:warehouseNo,
		planNo:planNo
	};
	var wheresql={
		wheresql:sql
	};
	if(Ext.getCmp('plan_type8101').getValue().rb=='0'){
		Ext.getCmp('grid_03_8101').hide();
		Ext.getCmp('grid_02_8101').show();	
		Ext.apply(Ext.getCmp('grid_02_8101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_02_8101').getStore().removeAll();
		Ext.getCmp('grid_02_8101').getStore().load();
	}else if(Ext.getCmp('plan_type8101').getValue().rb=='1'){
		Ext.getCmp('grid_02_8101').hide();
		Ext.getCmp('grid_03_8101').show();	
		Ext.apply(Ext.getCmp('grid_03_8101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_03_8101').getStore().removeAll();
		Ext.getCmp('grid_03_8101').getStore().load();
		Ext.getCmp('menu_03_8101').setDisabled(true);
	}
};

//结案调用
function closeAjax()
{
	var objRecord = Ext.getCmp('grid_M_8101').getSelectionModel().getSelection();
	var ownerNo;
	if(objRecord[0].data.ownerNo=='ALL'){
		ownerNo='N';
	}else{
		ownerNo=objRecord[0].data.ownerNo;
	}
	var strMaster=
	{
		id:{
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			warehouseNo:objRecord[0].data.warehouseNo,
			planNo:objRecord[0].data.planNo
		},
		ownerNo:ownerNo,
		rgstName:Ext.get('workerNo').getValue()
	};
	var objPlanM=Ext.encode(strMaster);
	
	var strParams=
	{
		objPlanM:objPlanM
	};
	Ext.Ajax.request({
		method:'post',
		url:'fcdata_PlanAction_sendClosePlan',
		params:strParams,
		success:function(response){
			var objData = Ext.decode(response.responseText);
			if(objData.isSucc){
				Ext.example.msg($i18n_prompt.prompt,objData.msg);
				Ext.getCmp('grid_M_8101').store.reload();
			}else{
				Ext.example.msg($i18n_prompt.prompt,objData.msg+objData.obj);
			}
		}
	});
}
