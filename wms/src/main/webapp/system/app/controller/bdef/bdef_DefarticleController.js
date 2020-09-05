/**
 * 商品资料
 * 模块编码   商品主档：1401  商品包装:1401_d2   商品条码:1401_d3
 * @author JUN
 * 修改By Panzx
 */

var g_intSaveType1401=0;//0=新增  1=修改 2=查看
var g_intRowIndex1401=0;//列表行数
var flagText=0;
Ext.define('cms.controller.bdef.bdef_DefarticleController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_DefarticleUI',
	          'cms.view.bdef.window.bdef_DefarticleAddOrEditWindow'
	          ],
	init:function(){
		this.control({
			  //新增
			'bdef_DefarticleUI button[name=detailAdd]':
			{
				click:this.detailAdd  
			},//编辑
			'bdef_DefarticleUI button[name=detailEdit]':
			{
				click:this.detailEdit
			},//删除
			'bdef_DefarticleUI button[name=detailDelete]':
			{
				click:this.detailDelete
			},//浏览
			'bdef_DefarticleUI button[name=detailBrowse]':
			{
				click:this.detailBrowse
			},//查找
			'bdef_DefarticleUI button[name=detailQuery]':
			{
				click:this.detailQuery
			},//上一条记录
			'bdef_DefarticleAddOrEditWindow button[name=prev]':
			{
				click:this.prev
			},//下一条记录
			'bdef_DefarticleAddOrEditWindow button[name=next]':
			{
				click:this.next
			},//新增
			'bdef_DefarticleAddOrEditWindow button[name=add]':
			{
				click:this.add
			},//保存
			'bdef_DefarticleAddOrEditWindow button[name=save]':
			{
				click:this.save
			},//关闭
			'bdef_DefarticleAddOrEditWindow button[name=close]':
			{
				click:this.close
			},//TAB页切换
			'bdef_DefarticleAddOrEditWindow tabpanel[id=tabPid1401]':
			{
				tabchange:this.tabchange
			},//商品包装grid单击
			'bdef_DefarticleAddOrEditWindow grid[id=grid_01_1401_d2]':
			{
				itemclick:this.packingGridClick
			},
			//拣货位包装grid单击
			'bdef_DefarticleAddOrEditWindow grid[id=grid_06_1401]':
			{
				itemclick:this.JHpackingGridClick1401
			},
			//商品条码grid单击
			'bdef_DefarticleAddOrEditWindow grid[id=grid_01_1401_d3]':
			{
				itemclick:this.barcodeGridClick
			},//商品类别选择事件
			'bdef_DefarticleAddOrEditWindow bdef_DefGroupNoCombo[id=cmbGroupNo1401]':
			{                                                       
				beforequery:this.cmbGroupNo1401Beforequery,
				select:this.cmbGroupNo1401Select
			},//批次管理类型选择事件
			'bdef_DefarticleAddOrEditWindow wms_DefFieldValCombo[id=cmbBatchManage1401]':
			{                                                       
				select:this.cmbBatchManage1401Select
			},//grid双击
			'bdef_DefarticleUI grid[id=grid_01_1401]':
			{
				itemdblclick:this.detailBrowse
			},
			'bdef_DefarticleAddOrEditWindow field':{
				specialkey:this.boxkeydown
			},//窗口关闭事件
			'bdef_DefarticleAddOrEditWindow':{
				beforeclose:this.bdef_DefarticleAddOrEditWindowBeforeClose
			},//包装选择
			'bdef_PackingQtyCombo[id=numPackingQty1401_d3]':{
				focus:this.packingQtyfocus
			},
			//状态选择
			'bdef_DefarticleUI combo[id=statusText1401]':{
				select:this.selectStatus
			},
			//商品编码选择
			'remoteCombo[id=identifierOrBarcode1401]':{
				beforequery:this.articleNoBeforeQuery
			},//小类编码选择
			'remoteCombo[id=sGroupNo1401]':{
				beforequery:this.sGroupNo1401BeforeQuery
			},
			//查询按扭
			'bdef_DefarticleUI button[id=btnSearch1401]':{
				click:this.btnSearch1401Click
			},			
			//拣货类型选择
			'cset_CellArticleAddOrEditWindow textfield[id=o_type1401]':{
				blur:this.o_type1401Blur,
				select:this.o_type1401Select
			},//库区选择
			'bdef_DefarticleAddOrEditWindow cdef_DefWareCombo[id=ware_no1401]':{
				select:this.selectWare
			},//储区选择
			'bdef_DefarticleAddOrEditWindow cdef_DefAreaCombo[id=area_no1401]':{
				select:this.selectArea
			},//货位编码选择
			'cdef_DefCellCombo[id=cell_no1401]':{
				beforequery:this.cellNoBeforeQuery
			},//线路前加载（保拣线）
			'cset_LineCombo[id=cmbLineId1401]':{
				focus:this.cmbLineId1401Focus

			}
		});
	},
	initializtion:function()
	{
		Ext.getCmp('cmbFormOwner1401').setValue(null);
		Ext.getCmp('cmbFormOwner1401').getStore().load();
	},
	//线路前加载
	cmbLineId1401Focus:function(queryEvent,eOpts){
		var listDetail1 = [];
		var d={
			columnId:'cabd.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.ware_No',
			value:Ext.getCmp('ware_no1401').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.area_no',
			value:Ext.getCmp('area_no1401').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var strwheresql = {
			str : strJson
		};
		Ext.getCmp('cmbLineId1401').getStore().proxy.extraParams.str = strJson;
		Ext.getCmp('cmbLineId1401').getStore().load();
	},
	//货位选择
	cellNoBeforeQuery:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('ware_no1401').getValue())){
			var d1={
					columnId:'a.ware_no',
					value:Ext.getCmp('ware_no1401').getValue()
				};
			listDetail1.push(d1);
		}if(!Ext.isEmpty(Ext.getCmp('area_no1401').getValue())){
			var d1={
					columnId:'a.area_no',
					value:Ext.getCmp('area_no1401').getValue()
				};
			listDetail1.push(d1);
		}if(!Ext.isEmpty(Ext.getCmp('stock_no1401').getValue())){
			var d1={
					columnId:'a.stock_no',
					value:Ext.getCmp('stock_no1401').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				str:Ext.encode(listDetail1), 
			};
		Ext.apply(Ext.getCmp('cell_no1401').getStore().proxy.extraParams,params);
		Ext.getCmp('cell_no1401').getStore().removeAll();
		Ext.getCmp('cell_no1401').getStore().load();
	},
	//储区选择
	selectArea:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('ware_no1401').getValue())){
			var d1={
					columnId:'a.ware_no',
					value:Ext.getCmp('ware_no1401').getValue()
				};
			listDetail1.push(d1);
		}if(!Ext.isEmpty(Ext.getCmp('area_no1401').getValue())){
			var d1={
					columnId:'a.area_no',
					value:Ext.getCmp('area_no1401').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				str:Ext.encode(listDetail1), 
			};
		Ext.apply(Ext.getCmp('stock_no1401').getStore().proxy.extraParams,params);
		Ext.getCmp('stock_no1401').getStore().removeAll();
		Ext.getCmp('stock_no1401').getStore().load();
		var listDetail1 = [];
		var d={
			columnId:'cabd.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.ware_No',
			value:Ext.getCmp('ware_no1401').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'cabd.area_no',
			value:Ext.getCmp('area_no1401').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var strwheresql = {
			str : strJson
		};
		Ext.getCmp('cmbLineId1401').getStore().proxy.extraParams.str = strJson;
		Ext.getCmp('cmbLineId1401').getStore().removeAll();
		Ext.getCmp('cmbLineId1401').getStore().load();
	},
	//库区选择
	selectWare:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('ware_no1401').getValue())){
			var d1={
					columnId:'a.ware_no',
					value:Ext.getCmp('ware_no1401').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				str:Ext.encode(listDetail1), 
			};
		Ext.apply(Ext.getCmp('area_no1401').getStore().proxy.extraParams,params);
		Ext.getCmp('area_no1401').getStore().removeAll();
		Ext.getCmp('area_no1401').getStore().load();
	},
	 //商品资料维护-商品编码加载 
	articleNoBeforeQuery:function(){
		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1401').getValue())
 				&&Ext.getCmp('cmbFormOwner1401').getValue()!='ALL'){
			var d1={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1401').getValue()
				};
			listDetail1.push(d1);
		}
 		if(!Ext.isEmpty(Ext.getCmp('statusText1401').getValue())){
			var d1={
					columnId:'a.status',
					value:Ext.getCmp('statusText1401').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				strQuery:Ext.encode(listDetail1), 
				str:Ext.getCmp('identifierOrBarcode1401').getValue()//客户编码
			};
		Ext.apply(Ext.getCmp('identifierOrBarcode1401').getStore().proxy.extraParams,params);
		Ext.getCmp('identifierOrBarcode1401').getStore().removeAll();
		Ext.getCmp('identifierOrBarcode1401').getStore().load();
  	  },
  	//商品资料维护-商品编码加载 
  	sGroupNo1401BeforeQuery:function(){
  		var listDetail1  =  [];
   		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1401').getValue())
   				&&Ext.getCmp('cmbFormOwner1401').getValue()!='ALL'){
  			var d1={
  					columnId:'a.owner_no',
  					value:Ext.getCmp('cmbFormOwner1401').getValue()
  				};
  			listDetail1.push(d1);
  		}
   		if(!Ext.isEmpty(Ext.getCmp('statusText1401').getValue())){
  			var d1={
  					columnId:'a.status',
  					value:Ext.getCmp('statusText1401').getValue()
  				};
  			listDetail1.push(d1);
  		}
  		var params={
  				strQuery:Ext.encode(listDetail1), 
  				str:Ext.getCmp('sGroupNo1401').getValue()//小类编码
  			};
  		Ext.apply(Ext.getCmp('sGroupNo1401').getStore().proxy.extraParams,params);
  		Ext.getCmp('sGroupNo1401').getStore().removeAll();
  		Ext.getCmp('sGroupNo1401').getStore().load();
    	  },
	//状态选择
	selectStatus:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1401').getValue())){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1401').getValue()
				};
			strDetail11.push(d2);
			var params={
					strQuery:Ext.encode(strDetail11),
					strOwnerNo:Ext.getCmp('cmbFormOwner1401').getValue()
				};
			Ext.apply(Ext.getCmp('statusText1401').getStore().proxy.extraParams,params);
			Ext.getCmp('statusText1401').getStore().removeAll();
			Ext.getCmp('statusText1401').getStore().load();
		}		
	},
	
	//查询
	btnSearch1401Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1401').getValue())
				&&Ext.getCmp('cmbFormOwner1401').getValue()!='ALL'){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1401').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusText1401').getValue())){
			var d2={
					columnId:'a.status',
					value:Ext.getCmp('statusText1401').getValue()
				};
			listDetail1.push(d2);
		}
 		if(!Ext.isEmpty(Ext.getCmp('identifierOrBarcode1401').getValue())){
			var d2={
					columnId:'a.article_no',
					value:Ext.getCmp('identifierOrBarcode1401').getValue()
				};
			listDetail1.push(d2);
		}
 		if(!Ext.isEmpty(Ext.getCmp('sGroupNo1401').getValue())){
			var d2={
					columnId:'a.group_no',
					value:Ext.getCmp('sGroupNo1401').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('cmbFormOwner1401').getValue()
			};
		Ext.apply(Ext.getCmp('grid_01_1401').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1401').getStore().removeAll();
		Ext.getCmp('grid_01_1401').getStore().load();
		
	},
	
	detailAdd:function()
	{
		Ext.create('cms.view.bdef.window.bdef_DefarticleAddOrEditWindow',
		{
			title:$i18n.titleadd
		}).show();
		commonSetMsterReadOnlyByArray(
				new Array('txtArticleNo1401'),true);

		g_intSaveType1401=0;//把保存模式改为0  0=新增  1=修改 2=查看
		articleAdd1401();
		addCommMenu5('bdef_MenuWidget1401');
	},
	
	detailEdit:function()
	{	
		var data=Ext.getCmp('grid_01_1401').getSelectionModel().getSelection();
		if(data.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else
		{
			Ext.create('cms.view.bdef.window.bdef_DefarticleAddOrEditWindow',
			{
				title:$i18n.titleupdate//修改
			}).show();
			g_intSaveType1401=1;//把保存模式改为1 0=新增  1=修改 2=查看
			g_intRowIndex1401=data[0].index;
			commonSetFieldReadOnly('form_01_1401',false);
			commonSetFieldReadOnly('form_02_1401',false);
			commonSetFieldReadOnly('form_03_1401',false);
			commonSetFieldReadOnly('form_04_1401',false);
			loadArticleData1401(g_intRowIndex1401);
			commonMenu5PrevOrNext('bdef_MenuWidget1401','grid_01_1401',0);
		}
		updateCommMenu5('bdef_MenuWidget1401');
	},
	
	/*
	 * 商品资料删除
	 */
	detailDelete:function(){
		var objDate=Ext.getCmp('grid_01_1401').getSelectionModel().getSelection();
		if(objDate.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else
		{
			Ext.Ajax.request({
				method:'post',
				url:'bdef_DefarticleAction_deleteArticleNo',
				params:
				{
					strArticleNo:objDate[0].get('articleNo'),
					strOwnerNo:objDate[0].get('ownerNo')
			    },
			    success:function(response)
			    {
			    	var data=Ext.decode(response.responseText);
					if(data.isSucc)
					{
						Ext.getCmp('grid_01_1401').getStore().load();
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
	
	detailBrowse:function()
	{
		var data=Ext.getCmp('grid_01_1401').getSelectionModel().getSelection();
		if (data.length == 0) 
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
		else
		{
        	Ext.create('cms.view.bdef.window.bdef_DefarticleAddOrEditWindow',
			{
        		title:$i18n.titlebrowse //浏览
			}).show();
        	g_intSaveType1401=2;//把保存模式改为1 0=新增  1=修改 2=查看
        	g_intRowIndex1401=data[0].index;
        	loadArticleData1401(g_intRowIndex1401);
			commonSetFieldReadOnly('form_01_1401',true);
			commonSetFieldReadOnly('form_02_1401',true);
			commonSetFieldReadOnly('form_03_1401',true);
			commonSetFieldReadOnly('form_04_1401',true);
			commonMenu5PrevOrNext('bdef_MenuWidget1401','grid_01_1401',0);
        }
		browseCommMenu5('bdef_MenuWidget1401');
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1401';
		queryGrid='grid_01_1401';
	},
	
	prev:function()
	{
		commonMenu5PrevOrNext('bdef_MenuWidget1401','grid_01_1401',-1);
		loadArticleData1401();
	},
	
	next:function()
	{
		commonMenu5PrevOrNext('bdef_MenuWidget1401','grid_01_1401',1);
		loadArticleData1401();
	},
	
	add:function()
	{
		if(Ext.getCmp('tabPid1401').getActiveTab().id=="tabPId_1401_d2")
		{
			addPacking1401();//商品包装
		}else if(Ext.getCmp('tabPid1401').getActiveTab().id=="tabPId_1401_d3")
		{
			addBarcode1401();//商品条码
		}else if(Ext.getCmp('tabPid1401').getActiveTab().id=="tabPId_1401_d4")
		{
			addCell1401();//商品拣货位
		}else
		{
			articleAdd1401();//商品主档
		}
		
	},
	
	save:function()
	{
		if(Ext.getCmp('tabPid1401').getActiveTab().id=="tabPId_1401_d2")
		{
			if(Ext.getCmp('txtArticleNo1401').getValue()==$i18n_prompt.autogenerationWhenSave){
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.saveAritcle);
			}else{
				savePacking1401(); //保存包装
			}
		}else if(Ext.getCmp('tabPid1401').getActiveTab().id=="tabPId_1401_d3")
		{
			if(Ext.getCmp('txtArticleNo1401').getValue()==$i18n_prompt.autogenerationWhenSave){
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.saveAritcle);
			}else{
				saveBarcode1401();//保存条码
			}
			
		}else if(Ext.getCmp('tabPid1401').getActiveTab().id=="tabPId_1401_d4")
		{
			if(Ext.getCmp('txtArticleNo1401').getValue()==$i18n_prompt.autogenerationWhenSave){
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.saveAritcle);
			}else{
				saveJHPacking1401(); //保存拣货位
			}
		}else
		{
			saveArticle1401();//保存商品
		}
	},
	
	close:function()
	{
		closeWindow1401();
	},
	
	tabchange:function(tab)
	{
		if(tab.getActiveTab().id=="tabPid1401_02")
		{
			setbdef_MenuWidget1401Tab2();
		}else if(tab.getActiveTab().id=="tabPid1401_03")
		{
			setbdef_MenuWidget1401Tab3();
		}
		else if(tab.getActiveTab().id=="tabPId_1401_d2")
		{
			setbdef_MenuWidget1401Tab4();
			Ext.getCmp('numPackingQty1401_d2').focus(false, 10);
		}else if(tab.getActiveTab().id=="tabPId_1401_d3")
		{
			setbdef_MenuWidget1401Tab5();
			Ext.getCmp('numPackingQty1401_d3').focus(false, 10);
		}else if(tab.getActiveTab().id=="tabPid1401_01"){
			setbdef_MenuWidget1401Tab1();
		}
		//库存信息按钮tabPId_1401_d5
		else if(tab.getActiveTab().id=="tabPId_1401_d5"){
			setbdef_MenuWidget1401Tab7();
		}
		else if(tab.getActiveTab().id=="tabPId_1401_d4"){
			setbdef_MenuWidget1401Tab6();
			//Ext.getCmp('packing_qty1401').focus(false, 10);
		}
		//************//
		
		var data = Ext.getCmp('grid_01_1401').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			var strArticleNo=Ext.getCmp('txtArticleNo1401').getValue();//window
			var wheresql={
				strArticleNo:strArticleNo
			};
//库cun传入商品编码
			var listDetail1  =  [];
	 		
				var d2={
						columnId:'a.article_no',
						value:strArticleNo
					};
				listDetail1.push(d2);
			
			var params={
					str:Ext.encode(listDetail1)
				};
			//拣货位传入商品编码
			var listDetail1  =  [];
			var d2={
					columnId:'a.article_no',
					value:strArticleNo
				};
			listDetail1.push(d2);
		
		    var param={
				strQuery:Ext.encode(listDetail1)
			};
		//packing
			if(tab.getActiveTab().id=="tabPid1401_02")
			{
				setbdef_MenuWidget1401Tab2();
			}else if(tab.getActiveTab().id=="tabPid1401_03")
			{
				setbdef_MenuWidget1401Tab3();
			}
			else if(tab.getActiveTab().id=="tabPId_1401_d2")
			{
				Ext.apply(Ext.getCmp('grid_01_1401_d2').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('grid_01_1401_d2').getStore().removeAll();
				Ext.getCmp('grid_01_1401_d2').getStore().load();
			}else if(tab.getActiveTab().id=="tabPId_1401_d3")
			{
				var strSql={
					strArticleNo:strArticleNo,
					strOwnerNo:Ext.getCmp('cmbOwnerNo1401').getValue()
				};
				Ext.apply(Ext.getCmp('grid_01_1401_d3').getStore().proxy.extraParams,strSql);
				Ext.getCmp('grid_01_1401_d3').getStore().removeAll();
				Ext.getCmp('grid_01_1401_d3').getStore().load();
			}
			//加载库区表格信息
			else if(tab.getActiveTab().id=="tabPId_1401_d5")
			{
				Ext.apply(Ext.getCmp('grid_07_1401_d2').getStore().proxy.extraParams,params);
				Ext.getCmp('grid_07_1401_d2').getStore().removeAll();
				Ext.getCmp('grid_07_1401_d2').getStore().load();
			}
			//加载拣货位表格信息
			else if(tab.getActiveTab().id=="tabPId_1401_d4")
			{
				Ext.apply(Ext.getCmp('grid_06_1401').getStore().proxy.extraParams,param);
				Ext.getCmp('packing_qty1401').getStore().proxy.extraParams.strWheresql = 
					Ext.getCmp('txtArticleNo1401').getValue();
				Ext.getCmp('packing_qty1401').getStore().removeAll();
				Ext.getCmp('packing_qty1401').getStore().load();
				Ext.getCmp('grid_06_1401').getStore().removeAll();
				Ext.getCmp('grid_06_1401').getStore().load();
				Ext.getCmp('ware_no1401').getStore().removeAll();
				Ext.getCmp('ware_no1401').getStore().load();
				Ext.getCmp('area_no1401').getStore().removeAll();
				Ext.getCmp('area_no1401').getStore().load();
				var listDetail1 = [];
				var d={
					columnId:'cabd.warehouse_no',
					value:Ext.get('warehouseNo').getValue()
				};
				listDetail1.push(d);		
				var strJson = Ext.encode(listDetail1);
				var strwheresql = {
					str : strJson
				};
				Ext.getCmp('cmbLineId1401').getStore().proxy.extraParams.str = strJson;
				Ext.getCmp('cmbLineId1401').getStore().removeAll();
				Ext.getCmp('cmbLineId1401').getStore().load();				
				
			}
			
		}else {
			
			var strArticleNo=Ext.getCmp('txtArticleNo1401').getValue();
			var wheresql={
				strArticleNo:strArticleNo
			};
			
			var listDetail1  =  [];
	 		
			var d2={
					columnId:'a.article_no',
					value:strArticleNo
				};
			listDetail1.push(d2);
		
		    var params={
				str:Ext.encode(listDetail1)
			};	
		    var param={
				strQuery:Ext.encode(listDetail1)
			};
			if(tab.getActiveTab().id=="tabPId_1401_d2")
			{
				Ext.apply(Ext.getCmp('grid_01_1401_d2').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('grid_01_1401_d2').getStore().removeAll();
				Ext.getCmp('grid_01_1401_d2').getStore().load();
			}else if(tab.getActiveTab().id=="tabPId_1401_d3")
			{
				var strSql={
					strArticleNo:strArticleNo,
					strOwnerNo:Ext.getCmp('cmbOwnerNo1401').getValue()
				};
				Ext.apply(Ext.getCmp('grid_01_1401_d3').getStore().proxy.extraParams,strSql);
				Ext.getCmp('grid_01_1401_d3').getStore().removeAll();
				Ext.getCmp('grid_01_1401_d3').getStore().load();
			}
			//加载库区表格信息
			else if(tab.getActiveTab().id=="tabPId_1401_d5")
			{
				Ext.apply(Ext.getCmp('grid_07_1401_d2').getStore().proxy.extraParams,params);
				Ext.getCmp('grid_07_1401_d2').getStore().removeAll();
				Ext.getCmp('grid_07_1401_d2').getStore().load();				
			}
			//加载拣货位表格信息
			else if(tab.getActiveTab().id=="tabPId_1401_d4")
			{
				Ext.apply(Ext.getCmp('grid_06_1401').getStore().proxy.extraParams,param);
				Ext.getCmp('grid_06_1401').getStore().removeAll();
				Ext.getCmp('grid_06_1401').getStore().load();
				Ext.getCmp('ware_no1401').getStore().removeAll();
				Ext.getCmp('ware_no1401').getStore().load();
			}
		}
		
	},
	
	packingGridClick:function()
	{
		packingGridClick1401();
	},
	
	JHpackingGridClick1401:function()
	{
		JHWpackingGridClick1401();
	},
	barcodeGridClick:function()
	{
		barcodeGridClick1401();
	},
	
	cmbGroupNo1401Beforequery:function(){
		var strWheresql={
			strOwnerNo:Ext.getCmp('cmbOwnerNo1401').getValue()
		};
		Ext.apply(Ext.getCmp('cmbGroupNo1401').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbGroupNo1401').getStore().removeAll();
		Ext.getCmp('cmbGroupNo1401').getStore().load();
	},
	
	cmbGroupNo1401Select:function(combo)
	{
		queryControlAndTactics(combo);
	},
	
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) 
		{
	  		if(th.id=="cmbAOut1401")
	  		{
	  			Ext.getCmp('tabPid1401').setActiveTab(0);
	  			Ext.getCmp('txtArticleEname1401').focus();
	  		}else if(th.id=="txtArticleEname1401")
	  		{
	  			Ext.getCmp('txtArticleAlias1401').focus();
	  		}else if(th.id=="txtArticleAlias1401")
	  		{
	  			Ext.getCmp('txtArticleOname1401').focus();
	  		}else if(th.id=="numSellPrice1401")
	  		{
	  			Ext.getCmp('rsvAttr1_1401').focus();
	  		}else if(th.id=="rsvAttr20_1401")
	  		{
	  			Ext.getCmp('tabPid1401').setActiveTab(1);
	  			Ext.getCmp('cmbCheckQtyFlag1401').focus();
	  		}else if(th.id=="numQcRate1401")
	  		{
	  			Ext.getCmp('numCheckExcess1401').focus();
	  		}else if(th.id=="numDivideExcess1401")
	  		{
	  			Ext.getCmp('numAlarmrate1401').focus();
	  		}else if(th.id=="numExpiryDays1401")
	  		{
	  			Ext.getCmp('cmbABC1401').focus();
	  		}else if(th.id=="txtDivideBox1401")
	  		{
	  			Ext.getCmp('tabPid1401').setActiveTab(2);
	  			Ext.getCmp('cmbIStrategy1401').focus();
	  		}else if(th.id=="rsvStrategy6_1401")
	  		{
	  			Ext.getCmp('cmbIStrategy1401').focus();
	  			Ext.getCmp('bdef_MenuWidget1401').items.items[4].focus();
	  		}else if(th.id=="cmbRuleFlag1401_d2")
	  		{
	  			Ext.getCmp('bdef_MenuWidget1401').items.items[4].focus();
	  		}/*else if(th.id=="cmbPrimaryFlag1401_d3")
	  		{
	  			Ext.getCmp('bdef_MenuWidget1401').items.items[4].focus();
	  		}*/else{
				th.nextSibling().focus();
			}
        }
	},
	
	bdef_DefarticleAddOrEditWindowBeforeClose:function(){
		Ext.getCmp('grid_01_1401').getStore().reload();
	},
	
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('txtArticleNo1401').getValue();
		th.getStore().load();
	},
	
	//批次管理类型选择
	cmbBatchManage1401Select:function(){
		var BatchManage = Ext.getCmp('cmbBatchManage1401').getValue();
		if(BatchManage == '1'){
			Ext.getCmp('numExpiryDays1401').setValue('0');
			Ext.getCmp('numExpiryDays1401').minValue='0';
		}else if(BatchManage == '4'){
			Ext.getCmp('numExpiryDays1401').setValue('-1');
			commonSetMsterReadOnlyByArray(
					new Array('numExpiryDays1401'),true);
		}else if(BatchManage == '2'){
			Ext.getCmp('numExpiryDays1401').setValue('0');
			Ext.getCmp('numExpiryDays1401').minValue='0';
			
		}else if(BatchManage == '3'){
			Ext.getCmp('numExpiryDays1401').setValue('0');
			Ext.getCmp('numExpiryDays1401').minValue='0';
		}
		
	}
		
});

/**
 * 窗口打开时加载
 */
function articleAdd1401()
{
	Ext.getCmp('form_01_1401').getForm().reset();
	Ext.getCmp('form_02_1401').getForm().reset();
	Ext.getCmp('form_03_1401').getForm().reset();
	Ext.getCmp('form_04_1401').getForm().reset();
	if(Ext.getCmp('cmbOwnerNo1401').getStore().data.length>0)
	{
		Ext.getCmp('cmbOwnerNo1401').setValue(Ext.getCmp('cmbOwnerNo1401').getStore().getAt(0).data.value);		
	}
	Ext.getCmp('txtOwnerArticleNo1401').setValue($i18n_prompt.autoSaveYesNo);
	Ext.getCmp('txtArticleNo1401').setValue($i18n_prompt.autogenerationWhenSave);
	Ext.getCmp('numQminOperatePacking1401').setValue(1);
	Ext.getCmp('numUnitVolumn1401').setValue(0);
	Ext.getCmp('numUnitWeight1401').setValue(0);
	Ext.getCmp('numCumulativeVolumn1401').setValue(0);
	Ext.getCmp('cmbVirtualFlag1401').setValue('0');
	Ext.getCmp('cmbCodeType1401').setValue('0');
	Ext.getCmp('numSellPrice1401').setValue(0);
	
	Ext.getCmp('cmbABC1401').setValue('C');
	Ext.getCmp('cmbScanFlag1401').setValue('0');
	Ext.getCmp('cmbDeliverType1401').setValue('1');
	Ext.getCmp('cmbRuleFlag1401').setValue('1');
	Ext.getCmp('txtDivideBox1401').setValue('0');
	Ext.getCmp('cmbPrintFlag1401').setValue('0');
	Ext.getCmp('cmbBatchManage1401').setValue('2');
	Ext.getCmp('cmbAOut1401').setValue('0');
	Ext.getCmp('status1401').setValue('0');
	Ext.getCmp('cmbOperateType1401').setValue('C');
	
	Ext.getCmp('txtOwnerArticleNo1401').focus(false, 10);
};

/**
 * 保存商品资料
 */
function saveArticle1401()
{
	if(Ext.getCmp('form_01_1401').getForm().isValid())
	{
		
	}else
	{
		if(!commonCheckIsInputAll('form_01_1401'))
		{
			return;
		}else
		{
			return;
		}
	}
	if(Ext.getCmp('form_02_1401').getForm().isValid())
	{
		
	}else
	{
		if(!commonCheckIsInputAll('form_02_1401'))
		{
			return;
		}else
		{
			return;
		}
	}
	if(Ext.getCmp('form_03_1401').getForm().isValid())
	{
		
	}else
	{
		if(!commonCheckIsInputAll('form_03_1401'))
		{
			return;
		}else
		{
			return;
		}
	}
	
	var poArticle={
		
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			articleNo:Ext.getCmp('txtArticleNo1401').getValue(),
		
		ownerNo:Ext.getCmp('cmbOwnerNo1401').getValue(),
		ownerArticleNo:Ext.getCmp('txtOwnerArticleNo1401').getValue(),
		articleName:Ext.getCmp('txtArticleName1401').getValue(),
		articleEname:Ext.getCmp('txtArticleEname1401').getValue(),
		articleOname:Ext.getCmp('txtArticleOname1401').getValue(),
		articleAlias:Ext.getCmp('txtArticleAlias1401').getValue(),
		groupNo:Ext.getCmp('cmbGroupNo1401').getValue(),
		batchId:Ext.getCmp('txtBatchId1401').getValue(),
		articleIdentifier:Ext.getCmp('txtArticleIdentifier1401').getValue(),
		unit:Ext.getCmp('txtUnit1401').getValue(),//基本包装单位
		qminOperatePacking:Ext.isEmpty(Ext.getCmp('numQminOperatePacking1401').getValue())?Ext.getCmp('unitPacking1401').getValue():Ext.getCmp('numQminOperatePacking1401').getValue(),
		expiryDays:Ext.getCmp('numExpiryDays1401').getValue(),
		alarmrate:Ext.getCmp('numAlarmrate1401').getValue(),
		freezerate:Ext.getCmp('numFreezerate1401').getValue(),
		abc:Ext.getCmp('cmbABC1401').getValue(),
		unitVolumn:Ext.getCmp('numUnitVolumn1401').getValue(),
		unitWeight:Ext.getCmp('numUnitWeight1401').getValue(),
		cumulativeVolumn:Ext.getCmp('numCumulativeVolumn1401').getValue(),
		AOut:Ext.getCmp('cmbAOut1401').getValue(),
		ruleFlag:Ext.getCmp('cmbRuleFlag1401').getValue(),
		spec:Ext.getCmp('txtSpec1401').getValue(),
		sellPrice:Ext.getCmp('numSellPrice1401').getValue(),
		status:Ext.getCmp('status1401').getValue(),
		createFlag:'1',
		virtualFlag:Ext.getCmp('cmbVirtualFlag1401').getValue(),
		turnOverRule:Ext.getCmp('cmbTurnOverRule1401').getValue(),
		checkExcess:Ext.getCmp('numCheckExcess1401').getValue(),
		umCheckExcess:Ext.getCmp('numUmCheckExcess1401').getValue(),
		pickExcess:Ext.getCmp('numPickExcess1401').getValue(),
		divideExcess:Ext.getCmp('numDivideExcess1401').getValue(),
		temperatureFlag:Ext.getCmp('cmbTemperatureFlag1401').getValue(),
		measureMode:Ext.getCmp('cmbMeasureMode1401').getValue(),
		scanFlag:Ext.getCmp('cmbScanFlag1401').getValue(),
		checkQtyFlag:Ext.getCmp('cmbCheckQtyFlag1401').getValue(),
		checkQtyRate:Ext.getCmp('numCheckQtyRate1401').getValue(),
		checkWeightFlag:Ext.getCmp('cmbCheckWeightFlag1401').getValue(),
		checkWeightRate:Ext.getCmp('numCheckWeightRate1401').getValue(),
		qcFlag:Ext.getCmp('cmbQcFlag1401').getValue(),
		qcRate:Ext.getCmp('numQcRate1401').getValue(),
		mixFlag:Ext.getCmp('cmbMixFlag1401').getValue(),
		printFlag:Ext.getCmp('cmbPrintFlag1401').getValue(),
		lotType:Ext.getCmp('cmbBatchManage1401').getValue(),
		supplierNo:(Ext.getCmp('supplierNo1401').getValue()==null || Ext.getCmp('supplierNo1401').getValue()=="")?"N":Ext.getCmp('supplierNo1401').getValue(),
		
		doubleCheck:Ext.getCmp('cmbDoubleCheck1401').getValue(),
		codeType:Ext.getCmp('cmbCodeType1401').getValue(),
		divideBox:Ext.getCmp('txtDivideBox1401').getValue(),
		deliverType:Ext.getCmp('cmbDeliverType1401').getValue(),
		deptNo:'N',
		IStrategy:Ext.getCmp('cmbIStrategy1401').getValue(),
		OStrategy:Ext.getCmp('cmbOStrategy1401').getValue(),
		MStrategy:Ext.getCmp('cmbMStrategy1401').getValue(),
		riStrategy:Ext.getCmp('cmbRiStrategy1401').getValue(),
		roStrategy:Ext.getCmp('cmbRoStrategy1401').getValue(),
		fcStrategy:Ext.getCmp('cmbFcStrategy1401').getValue(),
		rsvStrategy1:Ext.getCmp('rsvStrategy1_1401').getValue(),
		rgstName:Ext.get('workerNo').getValue(),
		rgstDate:new Date(),
		updtName:Ext.get('workerNo').getValue(),
		updtDate:new Date(),
		rsvAttr1:Ext.getCmp('rsvAttr1_1401').getValue(),
		rsvAttr2:Ext.getCmp('rsvAttr2_1401').getValue(),
		rsvAttr3:Ext.getCmp('rsvAttr3_1401').getValue(),
		rsvAttr4:Ext.getCmp('rsvAttr4_1401').getValue(),
		rsvAttr5:Ext.getCmp('rsvAttr5_1401').getValue(),
		rsvAttr6:Ext.getCmp('rsvAttr6_1401').getValue(),
		rsvAttr7:Ext.getCmp('rsvAttr7_1401').getValue(),
		rsvAttr8:Ext.getCmp('rsvAttr8_1401').getValue(),
		rsvAttr9:Ext.getCmp('rsvAttr9_1401').getValue(),
		rsvAttr10:Ext.getCmp('rsvAttr10_1401').getValue(),
		rsvAttr11:Ext.getCmp('rsvAttr11_1401').getValue(),
		rsvAttr12:Ext.getCmp('rsvAttr12_1401').getValue(),
		rsvAttr13:Ext.getCmp('rsvAttr13_1401').getValue(),
		rsvAttr14:Ext.getCmp('rsvAttr14_1401').getValue(),
		rsvAttr15:Ext.getCmp('rsvAttr15_1401').getValue(),
		rsvAttr16:Ext.getCmp('rsvAttr16_1401').getValue(),
		rsvAttr17:Ext.getCmp('rsvAttr17_1401').getValue(),
		rsvAttr18:Ext.getCmp('rsvAttr18_1401').getValue(),
//		rsvAttr19:Ext.getCmp('rsvAttr19_1401').getValue(),
//		rsvAttr20:Ext.getCmp('rsvAttr20_1401').getValue(),
		status:Ext.getCmp('status1401').getValue(),
		barcode:Ext.getCmp('txtBarcode1401').getValue(),
		operateType:Ext.getCmp('cmbOperateType1401').getValue(),
		rsvControl1:Ext.getCmp('rsv_control1_1401').getValue(),
		rsvControl2:Ext.getCmp('rsv_control2_1401').getValue(),
		rsvControl3:Ext.getCmp('rsv_control3_1401').getValue(),
		rsvControl4:Ext.getCmp('rsv_control4_1401').getValue(),
		rsvControl5:Ext.getCmp('rsv_control5_1401').getValue(),
		qminOperatePackingUnit:Ext.isEmpty(Ext.getCmp('qminUnit1401').getValue())?Ext.getCmp('txtUnit1401').getValue():Ext.getCmp('qminUnit1401').getValue(),
		unitPacking:Ext.getCmp('unitPacking1401').getValue(),
		
			
	};
	
	var strArticle=Ext.encode(poArticle);
	Ext.Ajax.request({
		url:'bdef_DefarticleAction_saveBdefDefarticle',
		method:'post',
		params:
		{
			strArticle:strArticle,
			strSaveType:g_intSaveType1401
		},
		success:function(response)
		{
			var data=Ext.decode(response.responseText);
			if(data.isSucc)
			{
				Ext.getCmp('txtArticleNo1401').setValue(data.msg);
				if(Ext.getCmp('txtOwnerArticleNo1401').getValue()=='可自动生成'){
					Ext.getCmp('txtOwnerArticleNo1401').setValue(data.msg);
				}
				if(Ext.getCmp('txtBarcode1401').getValue()==''){
					Ext.getCmp('txtBarcode1401').setValue(data.msg);
				}
				Ext.example.msg($i18n_prompt.prompt,$i18n.savesuccess);
			}
			else
			{
				Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
			}
			
		}
	});
}

/**
 * 填充商品资料
 */
function loadArticleData1401()
{   
	var objEditData=Ext.getCmp('grid_01_1401').getSelectionModel().getSelection();
	if(objEditData.length>0)
	{
		Ext.getCmp('cmbOwnerNo1401').setValue(objEditData[0].data.ownerNo);
		Ext.getCmp('txtOwnerArticleNo1401').setValue(objEditData[0].data.ownerArticleNo);
		Ext.getCmp('txtArticleNo1401').setValue(objEditData[0].data.articleNo);
		Ext.getCmp('txtArticleName1401').setValue(objEditData[0].data.articleName);
		Ext.getCmp('cmbAOut1401').setValue(objEditData[0].data.AOut);
		Ext.getCmp('txtArticleEname1401').setValue(objEditData[0].data.articleEname);
		Ext.getCmp('txtArticleAlias1401').setValue(objEditData[0].data.articleAlias);
		Ext.getCmp('txtArticleOname1401').setValue(objEditData[0].data.articleOname);
		Ext.getCmp('cmbGroupNo1401').getStore().add({
			value:objEditData[0].data.groupNo,
			dropValue:'['+objEditData[0].data.groupNo+']'+objEditData[0].data.groupName,
			text:objEditData[0].data.groupName
	    });
		Ext.getCmp('cmbGroupNo1401').setValue(objEditData[0].data.groupNo);
		Ext.getCmp('txtBatchId1401').setValue(objEditData[0].data.batchId);
		Ext.getCmp('txtArticleIdentifier1401').setValue(objEditData[0].data.articleIdentifier);
		Ext.getCmp('txtUnit1401').setValue(objEditData[0].data.unit);
		Ext.getCmp('txtSpec1401').setValue(objEditData[0].data.spec);
		Ext.getCmp('numQminOperatePacking1401').setValue(objEditData[0].data.qminOperatePacking);
		Ext.getCmp('numUnitVolumn1401').setValue(objEditData[0].data.unitVolumn);
		Ext.getCmp('numUnitWeight1401').setValue(objEditData[0].data.unitWeight);
		Ext.getCmp('numCumulativeVolumn1401').setValue(objEditData[0].data.cumulativeVolumn);
		Ext.getCmp('numSellPrice1401').setValue(objEditData[0].data.sellPrice);
		Ext.getCmp('cmbCheckQtyFlag1401').setValue(objEditData[0].data.checkQtyFlag);
		Ext.getCmp('numCheckQtyRate1401').setValue(objEditData[0].data.checkQtyRate);
		Ext.getCmp('cmbCheckWeightFlag1401').setValue(objEditData[0].data.checkWeightFlag);
		Ext.getCmp('numCheckWeightRate1401').setValue(objEditData[0].data.checkWeightRate);
		Ext.getCmp('cmbDoubleCheck1401').setValue(objEditData[0].data.doubleCheck);
		Ext.getCmp('cmbQcFlag1401').setValue(objEditData[0].data.qcFlag);
		Ext.getCmp('numQcRate1401').setValue(objEditData[0].data.qcRate);
		Ext.getCmp('numCheckExcess1401').setValue(objEditData[0].data.checkExcess);
		Ext.getCmp('numUmCheckExcess1401').setValue(objEditData[0].data.umCheckExcess);
		Ext.getCmp('numPickExcess1401').setValue(objEditData[0].data.pickExcess);
		Ext.getCmp('numDivideExcess1401').setValue(objEditData[0].data.divideExcess);
		Ext.getCmp('numAlarmrate1401').setValue(objEditData[0].data.alarmrate);
		Ext.getCmp('numFreezerate1401').setValue(objEditData[0].data.freezerate);
		Ext.getCmp('cmbTurnOverRule1401').setValue(objEditData[0].data.turnOverRule);
		Ext.getCmp('numExpiryDays1401').setValue(objEditData[0].data.expiryDays);
		Ext.getCmp('cmbABC1401').setValue(objEditData[0].data.abc);
		Ext.getCmp('cmbTemperatureFlag1401').setValue(objEditData[0].data.temperatureFlag);
		Ext.getCmp('cmbScanFlag1401').setValue(objEditData[0].data.scanFlag);
		Ext.getCmp('cmbMeasureMode1401').setValue(objEditData[0].data.measureMode);
		Ext.getCmp('cmbMixFlag1401').setValue(objEditData[0].data.mixFlag);
		Ext.getCmp('cmbDeliverType1401').setValue(objEditData[0].data.deliverType);
		Ext.getCmp('cmbRuleFlag1401').setValue(objEditData[0].data.ruleFlag);
		Ext.getCmp('cmbVirtualFlag1401').setValue(objEditData[0].data.virtualFlag);
		Ext.getCmp('cmbCodeType1401').setValue(objEditData[0].data.codeType);
		Ext.getCmp('txtDivideBox1401').setValue(objEditData[0].data.divideBox);
		Ext.getCmp('cmbIStrategy1401').setValue(objEditData[0].data.IStrategy);
		Ext.getCmp('cmbOStrategy1401').setValue(objEditData[0].data.OStrategy);
		Ext.getCmp('cmbMStrategy1401').setValue(objEditData[0].data.MStrategy);
		Ext.getCmp('cmbRiStrategy1401').setValue(objEditData[0].data.riStrategy);
		Ext.getCmp('cmbRoStrategy1401').setValue(objEditData[0].data.roStrategy);
		Ext.getCmp('cmbFcStrategy1401').setValue(objEditData[0].data.fcStrategy);
		Ext.getCmp('rsvStrategy1_1401').setValue(objEditData[0].data.rsvStrategy1);
		Ext.getCmp('cmbPrintFlag1401').setValue(objEditData[0].data.printFlag);
		Ext.getCmp('cmbBatchManage1401').setValue(objEditData[0].data.lotType);
		
		Ext.getCmp('supplierNo1401').getStore().add({
			value:objEditData[0].data.supplierNo,
			dropValue:objEditData[0].data.supplierNo,
			text:objEditData[0].data.supplierNo
	    });
		Ext.getCmp('supplierNo1401').setValue(objEditData[0].data.supplierNo);
		
		Ext.getCmp('rsvAttr1_1401').setValue(objEditData[0].data.rsvAttr1);
		Ext.getCmp('rsvAttr2_1401').setValue(objEditData[0].data.rsvAttr2);
		Ext.getCmp('rsvAttr3_1401').setValue(objEditData[0].data.rsvAttr3);
		Ext.getCmp('rsvAttr4_1401').setValue(objEditData[0].data.rsvAttr4);
		Ext.getCmp('rsvAttr5_1401').setValue(objEditData[0].data.rsvAttr5);
		Ext.getCmp('rsvAttr6_1401').setValue(objEditData[0].data.rsvAttr6);
		Ext.getCmp('rsvAttr7_1401').setValue(objEditData[0].data.rsvAttr7);
		Ext.getCmp('rsvAttr8_1401').setValue(objEditData[0].data.rsvAttr8);
		Ext.getCmp('rsvAttr9_1401').setValue(objEditData[0].data.rsvAttr9);
		Ext.getCmp('rsvAttr10_1401').setValue(objEditData[0].data.rsvAttr10);
		Ext.getCmp('rsvAttr11_1401').setValue(objEditData[0].data.rsvAttr11);
		Ext.getCmp('rsvAttr12_1401').setValue(objEditData[0].data.rsvAttr12);
		Ext.getCmp('rsvAttr13_1401').setValue(objEditData[0].data.rsvAttr13);
		Ext.getCmp('rsvAttr14_1401').setValue(objEditData[0].data.rsvAttr14);
		Ext.getCmp('rsvAttr15_1401').setValue(objEditData[0].data.rsvAttr15);
		Ext.getCmp('rsvAttr16_1401').setValue(objEditData[0].data.rsvAttr16);
		Ext.getCmp('rsvAttr17_1401').setValue(objEditData[0].data.rsvAttr17);
		Ext.getCmp('rsvAttr18_1401').setValue(objEditData[0].data.rsvAttr18);
		/*Ext.getCmp('rsvAttr19_1401').setValue(objEditData[0].data.rsvAttr19);
		Ext.getCmp('rsvAttr20_1401').setValue(objEditData[0].data.rsvAttr20);*/
		Ext.getCmp('txtBarcode1401').setValue(objEditData[0].data.barcode);
		Ext.getCmp('cmbOperateType1401').setValue(objEditData[0].data.operateType);
		Ext.getCmp('rsv_control1_1401').setValue(objEditData[0].data.rsvControl1);
		Ext.getCmp('rsv_control2_1401').setValue(objEditData[0].data.rsvControl2);
		Ext.getCmp('rsv_control3_1401').setValue(objEditData[0].data.rsvControl3);
		Ext.getCmp('rsv_control4_1401').setValue(objEditData[0].data.rsvControl4);
		Ext.getCmp('rsv_control5_1401').setValue(objEditData[0].data.rsvControl5);
		Ext.getCmp('status1401').setValue(objEditData[0].data.status);
		Ext.getCmp('qminUnit1401').setValue(objEditData[0].data.qminUnit);//最小包装单位
		Ext.getCmp('unitPacking1401').setValue(objEditData[0].data.basePacking);//基本包装数
		//Ext.getCmp('txtUnit1401').setValue(objEditData[0].data.unit);//基本单位
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNo1401','txtOwnerArticleNo1401','txtArticleNo1401'),true);
	}
	
}

/**
 * 关闭窗口
 */
function closeWindow1401()
{
	Ext.getCmp('bdef_DefArticleAddOrEditWindow').close();
}

function addCell1401(){
	/*commonSetFieldReadOnly('form_01_1401_d2',false);
	Ext.getCmp('form_01_1401_d2').getForm().reset();*/
	Ext.getCmp('packing_qty1401').setValue('');
	Ext.getCmp('o_type1401').setValue('');
	Ext.getCmp('ware_no1401').setValue('');
	Ext.getCmp('area_no1401').setValue('');
	Ext.getCmp('stock_no1401').setValue('');
	Ext.getCmp('cell_no1401').setValue('');
	Ext.getCmp('cmbLineId1401').setValue('');
	Ext.getCmp('keep_cells1401').setValue('');
	Ext.getCmp('max_qty_na1401').setValue('');
	Ext.getCmp('supp_qty_na1401').setValue('');
	Ext.getCmp('alert_qty_na1401').setValue('');
	commonSetMsterReadOnlyByArray(
			new Array('o_type1401'),false);
	Ext.getCmp('packing_qty1401').focus(false, 10);
}
function addPacking1401(){
	commonSetFieldReadOnly('form_01_1401_d2',false);
	Ext.getCmp('form_01_1401_d2').getForm().reset();
	Ext.getCmp('numALength1401_d2').setValue(0);
	Ext.getCmp('numAWidth1401_d2').setValue(0);
	Ext.getCmp('numAHeight1401_d2').setValue(0);
	Ext.getCmp('cmbRuleFlag1401_d2').setValue('1');
	Ext.getCmp('numPackingQty1401_d2').setValue(0);
	Ext.getCmp('numPackingWeight1401_d2').setValue(0);
	//Ext.getCmp('numMpackingQty1401_d2').setValue(0);
	//Ext.getCmp('numMpackingUnit1401_d2').setValue('0');
	//Ext.getCmp('numPalHeightQbox1401_d2').setValue(0);
	//Ext.getCmp('numPalBaseQbox1401_d2').setValue(0);
	Ext.getCmp('cmbSorterFlag1401_d2').setValue('0');
	
	Ext.getCmp('numPackingQty1401_d2').focus(false, 10);
}
/**
 * 保存商品包装
 */
function savePacking1401(){
	if(Ext.getCmp('form_01_1401_d2').getForm().isValid())
	{
		
	}else
	{
		if(!commonCheckIsInputAll('form_01_1401_d2'))
		{
			return;
		}else
		{
			return;
		}
	}
	var boolSorterFlag='';//上分拣机标识
	if(Ext.isEmpty(Ext.getCmp('cmbSorterFlag1401_d2').getValue())){
		strSorterFlag='';
	}
	else if(Ext.getCmp('cmbSorterFlag1401_d2').getValue()=='1')
	{
		boolSorterFlag=true;
	}else if(Ext.getCmp('cmbSorterFlag1401_d2').getValue()=='0')
	{
		boolSorterFlag=false;
	}
	var poPacking={
		id:{
			enterpriseNo:Ext.get('enterpriseNo').getValue(),	
			articleNo : Ext.getCmp('txtArticleNo1401').getValue(),
			packingQty : Ext.getCmp('numPackingQty1401_d2').getValue()
		},
		packingUnit : Ext.getCmp('txtPackingUnit1401_d2').getValue(),
		spec : Ext.getCmp('txtSpec1401_d2').getValue(),
		mpackingQty : Ext.getCmp('numMpackingQty1401_d2').getValue(),
		mpackingUnit : Ext.getCmp('numMpackingUnit1401_d2').getValue(),
		ALength : Ext.getCmp('numALength1401_d2').getValue(),
		AWidth : Ext.getCmp('numAWidth1401_d2').getValue(),
		AHeight : Ext.getCmp('numAHeight1401_d2').getValue(),
		packingWeight : Ext.getCmp('numPackingWeight1401_d2').getValue(),
		palBaseQbox : Ext.getCmp('numPalBaseQbox1401_d2').getValue(),
		palHeightQbox : Ext.getCmp('numPalHeightQbox1401_d2').getValue(),
		sorterFlag : boolSorterFlag,
		ruleFlag : Ext.getCmp('cmbRuleFlag1401_d2').getValue(),
		qpalette : Ext.getCmp('numPackingQty1401_d2').getValue()*Ext.getCmp('numPalBaseQbox1401_d2').getValue()*Ext.getCmp('numPalHeightQbox1401_d2').getValue(),
		createFlag : '1',
		rgstName : Ext.get('workerNo').getValue(),
		rgstDate:new Date(),
		rsvPking1:Ext.getCmp('rsv_pking1_1401').getValue(),
		rsvPking2:Ext.getCmp('rsv_pking2_1401').getValue(),
		rsvPking3:Ext.getCmp('rsv_pking3_1401').getValue(),
		rsvPking4:Ext.getCmp('rsv_pking4_1401').getValue(),
		rsvPking5:Ext.getCmp('rsv_pking5_1401').getValue(),
			
	};
	
	var strPacking=Ext.encode(poPacking);
	Ext.Ajax.request({
		url:'bdef_DefarticleAction_saveBdefArticlePacking',
		method:'post',
		params:
		{
			strPacking:strPacking
		},
		success:function(response)
		{
			var data=Ext.decode(response.responseText);
			if(data.isSucc)
			{
				Ext.getCmp('grid_01_1401_d2').getStore().load();
				Ext.example.msg($i18n.prompt,data.msg);
			}
			else
			{
				Ext.example.msg($i18n.prompt,data.msg);
			}
		}
	});
	
	//保存仓别标准堆叠
	var houseHeightQbox = Ext.getCmp('houseHeightQbox1401_d2').getValue();
    var houseBaseQbox = Ext.getCmp('houseBaseQbox1401_d2').getValue();
	
    if(houseHeightQbox != null && houseBaseQbox != null){
    	var housePacking={
    			id:{
    				enterpriseNo:Ext.get('enterpriseNo').getValue(),
    				articleNo : Ext.getCmp('txtArticleNo1401').getValue(),
    				packingQty : Ext.getCmp('numPackingQty1401_d2').getValue()
    			},
    			packingUnit : Ext.getCmp('txtPackingUnit1401_d2').getValue(),
    			palBaseQbox : Ext.getCmp('houseBaseQbox1401_d2').getValue(),
    			palHeightQbox : Ext.getCmp('houseHeightQbox1401_d2').getValue(),
    			qpalette : Ext.getCmp('numPackingQty1401_d2').getValue()*Ext.getCmp('houseBaseQbox1401_d2').getValue()*Ext.getCmp('houseHeightQbox1401_d2').getValue(),
    			rgstName : Ext.get('workerNo').getValue(),
    			rgstDate:new Date()    			
    		};
    		var strPacking=Ext.encode(housePacking);
    		Ext.Ajax.request({
    			url:'bdef_DefarticleAction_saveBdefWarehousePacking',
    			method:'post',
    			params:
    			{
    				strPacking:strPacking
    			},
    			success:function(response)
    			{
    				var data=Ext.decode(response.responseText);
    				if(data.isSucc)
    				{
    					Ext.getCmp('grid_01_1401_d2').getStore().load();
    					Ext.example.msg($i18n.prompt,data.msg);
    				}
    				else
    				{
    					Ext.example.msg($i18n.prompt,data.msg);
    				}
    			}
    		});
    }
	
}

/**
 * 保存拣货位信息 by Panzx
 */
function saveJHPacking1401(){
	if(Ext.getCmp('cset_Cell_ArticleAddOrEditForm1401').getForm().isValid())
	{
		
	}else
	{
		if(!commonCheckIsInputAll('cset_Cell_ArticleAddOrEditForm1401'))
		{
			return;
		}else
		{
			return;
		}
	}
	
	
	var str={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),	
				articleNo : Ext.getCmp('txtArticleNo1401').getValue(),
				ownerNo:Ext.getCmp('cmbOwnerNo1401').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				pickType:Ext.getCmp('o_type1401').getValue()
			},
			wareNo:Ext.getCmp('ware_no1401').getValue(),
			areaNo:Ext.getCmp('area_no1401').getValue(),
			stockNo:Ext.getCmp('stock_no1401').getValue(),
			AStockNo:Ext.getCmp('ware_no1401').getValue()+
			Ext.getCmp('area_no1401').getValue()+
			Ext.getCmp('stock_no1401').getValue(),
			//stockX:Ext.getCmp('cell_no1401').getValue().substring(4,6),
			cellNo:Ext.getCmp('cell_no1401').getValue(),
			articleNo:Ext.getCmp('txtArticleNo1401').getValue(),
			packingQty:Ext.getCmp('packing_qty1401').getValue(),
			lineId:Ext.getCmp('cmbLineId1401').getValue(),
			
			maxQtyA:Ext.getCmp('max_qty_na1401').getValue()*Ext.getCmp('packing_qty1401').getValue(),
			alertQtyA:Ext.getCmp('alert_qty_na1401').getValue()*Ext.getCmp('packing_qty1401').getValue(),
			suppQtyA:Ext.getCmp('supp_qty_na1401').getValue()*Ext.getCmp('packing_qty1401').getValue(),
			maxQtyNa:Ext.getCmp('max_qty_na1401').getValue()*Ext.getCmp('packing_qty1401').getValue(),
			alertQtyNa:Ext.getCmp('alert_qty_na1401').getValue()*Ext.getCmp('packing_qty1401').getValue(),
			suppQtyNa:Ext.getCmp('supp_qty_na1401').getValue()*Ext.getCmp('packing_qty1401').getValue(),
			
			keepCells:Ext.getCmp('keep_cells1401').getValue(),
			keepCellsA:Ext.getCmp('keep_cells1401').getValue(),
			
			
			rgstName:Ext.get('workerName').getValue(),
			rgstDate:Ext.Date.format(new Date,'Y-m-d h:m:s'),
			updtName:Ext.get('workerName').getValue(),
			updtDate:Ext.Date.format(new Date,'Y-m-d h:m:s')
	};
	var jsonStr = Ext.encode(str);
	Ext.Ajax.request({
		url:'cset_CellArticleAction_saveOrUpdateCset_Cell_Article.action',
		params : {
			str:jsonStr
		},
		success:function(response){
			var data = Ext.decode(response.responseText);
			if(data.isSucc){
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('grid_06_1401').getStore().removeAll();
				Ext.getCmp('grid_06_1401').getStore().load();
					commonSetMsterReadOnlyByArray(
						new Array('txtArticleNo1401','cmbOwnerNo1401','o_type1401'),true);
			}else{
				Ext.example.msg($i18n.prompt,data.msg);
			}
		}
	});

	
}

//条码新增前加载
function addBarcode1401(){
	commonSetFieldReadOnly('form_01_1401_d3',false);
	Ext.getCmp('form_01_1401_d3').getForm().reset();
	Ext.getCmp('numPackingQty1401_d3').focus(false, 10);
}

/**
 * 包装grid单击事件
 */
function packingGridClick1401()
{
	if(g_intSaveType1401==0)
	{
		commonSetFieldReadOnly('form_01_1401_d2',false);
	}else if(g_intSaveType1401==1)
	{
		commonSetFieldReadOnly('form_01_1401_d2',false);
	}else if(g_intSaveType1401==2)
	{
		commonSetFieldReadOnly('form_01_1401_d2',true);
	}
	var packing=Ext.getCmp('grid_01_1401_d2').getSelectionModel().getSelection();
	var strSorterFlag='';//上分拣机标识
	if(Ext.isEmpty(packing[0].get('sorterFlag'))){
		strSorterFlag='';
	}
	else if(packing[0].get('sorterFlag')==false)
	{
		strSorterFlag='0';
	}else if(packing[0].get('sorterFlag')==true)
	{
		strSorterFlag='1';
	}
	
	Ext.getCmp('numPackingQty1401_d2').setValue(packing[0].get('packingQty'));
	Ext.getCmp('txtPackingUnit1401_d2').setValue(packing[0].get('packingUnit'));
	Ext.getCmp('txtSpec1401_d2').setValue(packing[0].get('spec'));
	Ext.getCmp('numPackingWeight1401_d2').setValue(packing[0].get('packingWeight'));
	Ext.getCmp('numALength1401_d2').setValue(packing[0].get('ALength'));
	Ext.getCmp('numAWidth1401_d2').setValue(packing[0].get('AWidth'));
	Ext.getCmp('numAHeight1401_d2').setValue(packing[0].get('AHeight'));
	Ext.getCmp('numMpackingQty1401_d2').setValue(packing[0].get('mpackingQty')=="0"?null:packing[0].get('mpackingQty'));
	Ext.getCmp('numMpackingUnit1401_d2').setValue(packing[0].get('mpackingUnit'));
	Ext.getCmp('numPalHeightQbox1401_d2').setValue(packing[0].get('palHeightQbox')=="0"?null:packing[0].get('palHeightQbox'));
	Ext.getCmp('numPalBaseQbox1401_d2').setValue(packing[0].get('palBaseQbox')=="0"?null:packing[0].get('palBaseQbox'));
	Ext.getCmp('cmbSorterFlag1401_d2').setValue(strSorterFlag);
	Ext.getCmp('cmbRuleFlag1401_d2').setValue(packing[0].get('ruleFlag'));
	
	Ext.getCmp('rsv_pking1_1401').setValue(packing[0].get('rsvPking1'));
	Ext.getCmp('rsv_pking2_1401').setValue(packing[0].get('rsvPking2'));
	Ext.getCmp('rsv_pking3_1401').setValue(packing[0].get('rsvPking3'));
	Ext.getCmp('rsv_pking4_1401').setValue(packing[0].get('rsvPking4'));
	Ext.getCmp('rsv_pking5_1401').setValue(packing[0].get('rsvPking5'));

	var params=
	{
			strArticleNo:packing[0].data.articleNo,
			strPackingQty:packing[0].data.packingQty
	};
	
	Ext.Ajax.request({
		method:'POST',
		url:'bdef_DefarticleAction_queryBdefWarehousePacking',
		async:false,
		params:params,
		success:function(response)
		{
			var data = Ext.decode(response.responseText);
			if(data.length>0){
		    	Ext.getCmp('houseHeightQbox1401_d2').setValue(data[0].palHeightQbox);
		    	Ext.getCmp('houseBaseQbox1401_d2').setValue(data[0].palBaseQbox);
			}else{
				Ext.getCmp('houseHeightQbox1401_d2').setValue('');
		    	Ext.getCmp('houseBaseQbox1401_d2').setValue('');
			}			
		}
	});
	commonSetMsterReadOnlyByArray(
			new Array('numPackingQty1401_d2'),true);
}

/**
 * 拣货位grid单击事件
 */
function JHWpackingGridClick1401()
{
	if(g_intSaveType1401==0)
	{
		commonSetFieldReadOnly('cset_Cell_ArticleAddOrEditForm1401',false);
	}else if(g_intSaveType1401==1)
	{
		commonSetFieldReadOnly('cset_Cell_ArticleAddOrEditForm1401',false);
		/*commonSetMsterReadOnlyByArray(
				new Array('o_type1401'),true);*/
		
	}else if(g_intSaveType1401==2)
	{
		commonSetFieldReadOnly('cset_Cell_ArticleAddOrEditForm1401',true);
		
	}
	var obj=Ext.getCmp('grid_06_1401').getSelectionModel().getSelection()[0];
	Ext.getCmp('packing_qty1401').setValue(String(obj.data.packingQty));
	Ext.getCmp('o_type1401').setValue(obj.data.pickType);
	Ext.getCmp('ware_no1401').setValue(obj.data.wareNo);
	Ext.getCmp('area_no1401').setValue(obj.data.areaNo);
	Ext.getCmp('stock_no1401').setValue(obj.data.stockNo);
	Ext.getCmp('cell_no1401').getStore().removeAll();
	Ext.getCmp('cell_no1401').getStore().add({
    	value:obj.data.cellNo,
    	dropValue:obj.data.cellNo,
    	text:obj.data.cellNo
    });
	Ext.getCmp('cell_no1401').setValue(obj.data.cellNo);	
	Ext.getCmp('cmbLineId1401').setValue(String(obj.data.lineId));
	Ext.getCmp('keep_cells1401').setValue(obj.data.keepCells);
	Ext.getCmp('max_qty_na1401').setValue(obj.data.maxQtyBox);
	Ext.getCmp('supp_qty_na1401').setValue(obj.data.suppQtyBox);
	Ext.getCmp('alert_qty_na1401').setValue(obj.data.alertQtyBox);	

}
/**
 * 保存商品条码
 */
function saveBarcode1401()
{
	if(Ext.getCmp('form_01_1401_d3').getForm().isValid())
	{
		
	}else
	{
		if(!commonCheckIsInputAll('form_01_1401_d3'))
		{
			return;
		}else
		{
			return;
		}
	}
	//if(Ext.getCmp('cmbPrimaryFlag1401_d3').getValue()==1){
		/*Ext.Ajax.request({
		url:'bdef_DefarticleAction_checkSaveBarcode',
		async:false,
		method:'post',
		params:
		{
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			strArticleNo:Ext.getCmp('txtArticleNo1401').getValue(),
			strPackingQty:Ext.getCmp('numPackingQty1401_d3').getValue(),
			strBarcode:Ext.getCmp('txtBarcode1401_d3').getValue
		},
		success:function(response)
		{
			var data=Ext.decode(response.responseText);
			if(data.isSucc)
			{
				var poBarcode={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						barcode:Ext.getCmp('txtBarcode1401_d3').getValue(),	
						ownerNo:'',
						articleNo:Ext.getCmp('txtArticleNo1401').getValue(),
						packingQty:Ext.getCmp('numPackingQty1401_d3').getValue()
					},
					//primaryFlag:Ext.getCmp('cmbPrimaryFlag1401_d3').getValue(),
					createFlag:'1',
					rgstName:Ext.get('workerNo').getValue(),
					rgstDate:new Date()
				};
				
				var strBarcode=Ext.encode(poBarcode);
				Ext.Ajax.request({
					url:'bdef_DefarticleAction_saveOrUpdateArticleBarcode',
					method:'post',
					params:
					{
						strBarcode:strBarcode
					},
					success:function(response)
					{
						var data=Ext.decode(response.responseText);
						if(data.isSucc)
						{
							Ext.getCmp('grid_01_1401_d3').getStore().load();
							Ext.example.msg($i18n.prompt,data.msg);
						}
						else
						{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
			else
			{
				Ext.example.msg($i18n.prompt,data.msg);
				return;
			}
		}
	});*/
	//}
		//else{
		var poBarcode={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				barcode:Ext.getCmp('txtBarcode1401_d3').getValue(),	
				ownerNo:'',
				articleNo:Ext.getCmp('txtArticleNo1401').getValue(),
				packingQty:Ext.getCmp('numPackingQty1401_d3').getValue()
			},
			//primaryFlag:Ext.getCmp('cmbPrimaryFlag1401_d3').getValue(),
			createFlag:'1',
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date()
		};
		
		var strBarcode=Ext.encode(poBarcode);
		Ext.Ajax.request({
			url:'bdef_DefarticleAction_saveOrUpdateArticleBarcode',
			method:'post',
			params:
			{
				strBarcode:strBarcode
			},
			success:function(response)
			{
				var data=Ext.decode(response.responseText);
				if(data.isSucc)
				{
					Ext.example.msg($i18n.prompt,data.msg);
				}
				else
				{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	//}
}

/**
 * 条码GRID单击事件
 */
function barcodeGridClick1401()
{	
	if(g_intSaveType1401==0)
	{
		commonSetFieldReadOnly('form_01_1401_d3',false);
	}else if(g_intSaveType1401==1)
	{
		commonSetFieldReadOnly('form_01_1401_d3',false);
	}else if(g_intSaveType1401==2)
	{
		commonSetFieldReadOnly('form_01_1401_d3',true);
	}
	var barcode=Ext.getCmp('grid_01_1401_d3').getSelectionModel().getSelection();
	
	Ext.getCmp('numPackingQty1401_d3').getStore().add({
		value:String(barcode[0].get('packingQty')),
		dropValue:String(barcode[0].get('packingQty')),
		text:String(barcode[0].get('packingQty'))
    });
	Ext.getCmp('numPackingQty1401_d3').setValue(String(barcode[0].get('packingQty')));
	
	Ext.getCmp('txtBarcode1401_d3').setValue(barcode[0].get('barcode'));
	//Ext.getCmp('cmbPrimaryFlag1401_d3').setValue(String(barcode[0].get('primaryFlag')));
	
	commonSetMsterReadOnlyByArray(
			new Array('numPackingQty1401_d3','txtBarcode1401_d3'),true);
}

/**
 * 填充控制信息和策略管理信息
 */
function queryControlAndTactics(combo)
{
	Ext.Ajax.request({
		method:'post',
		url:'bdef_ArticleGroupAction_queryControlAndTactics',
		params:
		{
			strGroupNo:combo.getValue()
	    },
	    success:function(response){
	    	var res = Ext.decode(response.responseText);
	    	Ext.getCmp('cmbCheckQtyFlag1401').setValue(res[0].checkQtyFlag);
	    	Ext.getCmp('numCheckQtyRate1401').setValue(res[0].checkQtyRate);
	    	Ext.getCmp('cmbCheckWeightFlag1401').setValue(res[0].checkWeightFlag);	
	    	Ext.getCmp('numCheckWeightRate1401').setValue(res[0].checkWeightRate);
	    	Ext.getCmp('cmbDoubleCheck1401').setValue(res[0].doubleCheck);
	    	Ext.getCmp('cmbQcFlag1401').setValue(res[0].qcFlag);
	    	Ext.getCmp('numQcRate1401').setValue(res[0].qcRate);
	    	
	    	Ext.getCmp('numCheckExcess1401').setValue(res[0].checkExcess);
	    	Ext.getCmp('numUmCheckExcess1401').setValue(res[0].umCheckExcess);
	    	Ext.getCmp('numPickExcess1401').setValue(res[0].pickExcess);
	    	Ext.getCmp('numDivideExcess1401').setValue(res[0].divideExcess);
	    	
	    	Ext.getCmp('numAlarmrate1401').setValue(res[0].alarmrate);
	    	Ext.getCmp('numFreezerate1401').setValue(res[0].freezerate);
	    	Ext.getCmp('cmbTurnOverRule1401').setValue(res[0].turnOverRule);
	    	
	    	Ext.getCmp('cmbTemperatureFlag1401').setValue(res[0].temperatureFlag);
	    	Ext.getCmp('cmbMeasureMode1401').setValue(res[0].measureMode);
	    	Ext.getCmp('cmbMixFlag1401').setValue(res[0].mixFlag);
	    	
	    	Ext.getCmp('cmbIStrategy1401').setValue(res[0].IStrategy);
	    	Ext.getCmp('cmbOStrategy1401').setValue(res[0].OStrategy);
	    	Ext.getCmp('cmbMStrategy1401').setValue(res[0].MStrategy);
	    	Ext.getCmp('cmbRiStrategy1401').setValue(res[0].riStrategy);
	    	Ext.getCmp('cmbRoStrategy1401').setValue(res[0].roStrategy);
	    	Ext.getCmp('cmbFcStrategy1401').setValue(res[0].fcStrategy);
	    	Ext.getCmp('rsvStrategy1_1401').setValue("1");
	    	//Ext.getCmp('rsvStrategy1_1401').setValue(res[0].rsvStrategy1);
	    }
	});
}

/**
 * 
 */
function setbdef_MenuWidget1401Tab1(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}


/**
 * 设置控制信息下的按扭显示
 */
function setbdef_MenuWidget1401Tab2(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}

/**
 * 设置策略信息下的按扭显示
 */
function setbdef_MenuWidget1401Tab3(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}

/**
 * 设置包装下的按扭显示
 */
function setbdef_MenuWidget1401Tab4(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
		Ext.getCmp('grid_01_1401_d2').getStore().removeAll();
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}
/**
 * 设置拣货位信息下的按扭显示
 */
function setbdef_MenuWidget1401Tab6(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
		Ext.getCmp('grid_06_1401').getStore().removeAll();
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}
//库存设置按钮
function setbdef_MenuWidget1401Tab7(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
		Ext.getCmp('grid_07_1401_d2').getStore().removeAll();
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}
/**
 * 设置条码下的按扭显示
 */
function setbdef_MenuWidget1401Tab5(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
		Ext.getCmp('grid_01_1401_d3').getStore().removeAll();
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(true);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('bdef_MenuWidget1401').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[3].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[4].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401').items.items[5].setVisible(true);
	}
}