/**
 * 模块名称：报损回单
 * 模块编码：E102
 * 创建 ： 贺康利
 */
var rowindexE102=0;
var isCanEditE102=false;
Ext.getCmp('menuE102').items.items[3].setDisabled(true);
Ext.getCmp('menuE102').items.items[4].setDisabled(true);
Ext.define('cms.controller.sodata.sodata_OutStockController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.sodata.sodata_OutStockUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control(
		    //选中报损回单单头，加载商品明细信息
		    {
			'sodata_OutStockUI grid[id=sodata_OutstockME102]' : {
				beforeselect:this.sodata_OutstockME102Beforeselect,
				selectionchange : this.selectionchangeME102
			},//网格编辑
			'sodata_OutStockUI grid[id=sodata_OutstockDE102]':{
				beforeedit:this.sodata_OutstockDE102beforeedit,
				edit:this.sodata_OutstockDE102
			},
			//点击修改按钮
			'sodata_OutStockUI button[name=edit]':{
				click:this.edit
			},
			//保存
			'sodata_OutStockUI button[name=save]':{
				click:this.save
			},//撤销
			'sodata_OutStockUI button[name=undo]':{
				click:this.undo
			},//查询
			'sodata_OutStockUI button[name=query]':{
				click:this.query
			},//刷新
			'sodata_OutStockUI button[name=refresh]':{
				click:this.refresh
			}
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		isCanEditE102=false;
		//显示变量0为不显示，1为显示
		var realBoxE102=commonGetModuleField('E102','realBox')[0].flag;
		var realDisE102=commonGetModuleField('E102','realDis')[0].flag;
		var realQminE102=commonGetModuleField('E102','realQmin')[0].flag;
		var planBoxE102=commonGetModuleField('E102','planBox')[0].flag;
		var planDisE102=commonGetModuleField('E102','planDis')[0].flag;
		var planQminE102=commonGetModuleField('E102','planQmin')[0].flag;

		var packingUnitE102=commonGetModuleField('E102','packingUnit')[0].flag;
		var packingSpecE102=commonGetModuleField('E102','packingSpec')[0].flag;
		if(realBoxE102==0){
			Ext.getCmp('realBoxE102').setVisible(false);
		}
		if(realDisE102==0){
			Ext.getCmp('realDisE102').setVisible(false);
		}
		if(realQminE102==0){
			Ext.getCmp('realQminE102').setVisible(false);
		}
		if(planBoxE102==0){
			Ext.getCmp('planBoxE102').setVisible(false);
		}
		if(planDisE102==0){
			Ext.getCmp('planDisE102').setVisible(false);
		}
		if(planQminE102==0){
			Ext.getCmp('planQminE102').setVisible(false);
		}
		if(packingUnitE102==0){
			Ext.getCmp('packingUnitE102').setVisible(false);
		}
		if(packingSpecE102==0){
			Ext.getCmp('packingSpecE102').setVisible(false);
		}
		Ext.getCmp('sodata_OutstockME102').getStore().load();
	},
	
	sodata_OutstockDE102beforeedit:function(e){
		if(!isCanEditE102)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//报损回单》商品数量较验等
	sodata_OutstockDE102:function(editor,e,eOpts){
		if(e.field=='realWholenum' || e.field=='realQty'){
			if(e.record.data.realQty>e.record.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityCannotMoreThanPlan);
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='realWholenum'){
					editor.context.record.set('realQty',editor.context.originalValue*e.record.data.packingQty);
				}else if(e.field=='realQty'){
					editor.context.record.set('realWholenum',(editor.context.originalValue/e.record.data.packingQty));
				}
			}
		}
	},
	
	/*
	 * 刷新
	 */
	  refresh:function(){
 	  	Ext.getCmp('sodata_OutstockME102').getStore().removeAll();
 	  	Ext.getCmp('sodata_OutstockME102').getStore().reload();
 	  },
 	  
	/**
	 * 查找
	 */
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=E102;
		queryGrid='sodata_OutstockME102';					
	},
	
	undo:function(){
		commonMenu4Button('menuE102','undo');
		Ext.getCmp('sodata_OutstockME102').fireEvent('selectionchange','');
	},
	
	/*
	 * 点击保存按钮，回单
	 */
	save:function(){
			var gridcount=Ext.getCmp("sodata_OutstockDE102").getStore().getCount();
			if(gridcount>0)
			{
				if(!commonCheckdetailgrid('sodata_OutstockDE102',0,gridcount-1))
				{
					return;
				}	
			}else{			
				Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
				return;
			}
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
				return;
			}

			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,
			function(button, text) {
			if (button == 'yes') {
				var listDetail1 = [];
				for(var i=0;i<gridcount;i++ ){
					var objRecord  = Ext.getCmp('sodata_OutstockDE102').getStore().getAt(i);
					var d={	
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:Ext.get('warehouseNo').getValue(),
								ownerNo:objRecord.get('ownerNo'),
								outstockNo:objRecord.get('outstockNo')
							},
							articleNo:objRecord.get('articleNo'),
							packingQty:objRecord.get('packingQty'),
						    SCellNo:objRecord.get('SCellNo'),
							realQty: objRecord.get('planBox') * objRecord.get('packingQty')
							+objRecord.get('planQmin') * objRecord.get('qminOperatePacking')
							+objRecord.get('planDis') ,
							assignName:objRecord.get('assignName'),
							
					};
					listDetail1.push(d);
				}
					
				var jsonlistDetail1 = Ext.encode(listDetail1);		
				var params = {
						strQuery:jsonlistDetail1,
						type:Ext.getCmp('rdoCheckTypeE102').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'sodata_OutStockAction_save.action',
					params:params,
					success:function(response){
						var data1 = Ext.decode(response.responseText);
						if(data1.isSucc){
							commonMenu4Button('menuE102','save');
							Ext.example.msg($i18n.prompt,data1.msg);
							isCanEditE102=false;
							Ext.getCmp('sodata_OutstockME102').getStore().reload();
							Ext.getCmp('sodata_OutstockDE102').getStore().reload();
						}else{
							Ext.example.msg($i18n.prompt,data1.msg+data1.obj);
						}				
					}
					
				});	
			}});
		
	},
	
	
	/*
	 * 点击修改按钮,改为可编辑
	 */
	edit:function()
	{
		commonMenu4Button('menuE102','edit');
		isCanEditE102=true;
		//Ext.getCmp('sodata_OutstockDE102').editingPlugin.startEdit(0,10);
	},
	/*
	beforeeditE102:function(e){
		if(!isCanEditE102)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},*/
	
	//报损回单数据校验
	rodata_OutStock_MReceiptUIcompare:function(editor,e,eOpts)
	{
		if(e.field=='realWholenum' )
		{
			if(e.objRecord.data.realQty>e.objRecord.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotLargeToArticleQty);
				editor.context.objRecord.set(e.field,editor.context.originalValue);
				if(e.field=='realWholenum'){
					editor.context.objRecord.set('realQty',editor.context.originalValue*e.objRecord.data.packingQty);
				}
			}
		}
	},
	
	//网格选择前事件
	sodata_OutstockME102Beforeselect:function(){
	    if(isCanEditE102)
	    {
	        return  false;  
	    }
	},
	
	/*
	 *网格显示
	 */
	selectionchangeME102:function(th,selected,eOpts)
	{
		var objRecord = Ext.getCmp('sodata_OutstockME102').getSelectionModel().getSelection();
		if(objRecord.length!=0){
		var strWheresql = {
				wheresql : objRecord[0].get("outstockNo")
		};
		
		Ext.apply(Ext.getCmp('sodata_OutstockDE102').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('sodata_OutstockDE102').getStore().removeAll();
		Ext.getCmp('sodata_OutstockDE102').getStore().load();
		commonMenu4Button('menuE102','undo');
		isCanEditE102=false;
		}
			
	}
});

    /**
	 * 填充
	 * @param {} grid
	 * @param {} rowindexE102
	 * @param {} colIndex
	 */
	function editOutStockD(rowindexE102){
		 var objRecord=Ext.getCmp('sodata_OutstockME102').getStore().getAt(rowindexE102);
			var listDetail1 = [];
			var d={
				columnId:'a.warehouse_no',
				value:objRecord.data.warehouseNo
			};
			listDetail1.push(d);
			var d={
				columnId:'a.owner_no',
				value:objRecord.data.ownerNo
			};
			listDetail1.push(d);
			var d={
				columnId:'a.outstock_no',
				value:objRecord.data.outstockNo
			};
			listDetail1.push(d);
			var jsonlistDetail1 = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonlistDetail1
			};
			
			Ext.apply(Ext.getCmp('sodata_OutstockDE102')
				.getStore().proxy.extraParams,
				wheresql);
			Ext.getCmp('sodata_OutstockDE102').getStore()
				.removeAll();
			Ext.getCmp('sodata_OutstockDE102').getStore()
				.load();
			isCanEditE102=false;
		
	}
