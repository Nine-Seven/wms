/*
 * 退厂回单
 * zhouhuan
 * 7301
 */
var rowindex7301=0;
var isCanEdit7301=false;
Ext.getCmp('menu7301').items.items[3].setDisabled(true);
Ext.getCmp('menu7301').items.items[4].setDisabled(true);
Ext.define('cms.controller.rodata.rodata_OutstockMController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_OutstockMReceiptUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control(
		    //选中退厂回单单头，加载商品明细信息
		    {
			'rodata_OutstockMReceiptUI grid[id=rodata_OutstockM7301_1]' : {
				beforeselect:this.rodata_OutstockM7301_1Beforeselect,
				selectionchange : this.selectionchange7301_1
			},
			//退厂回单数据校验
			'rodata_OutstockMReceiptUI grid[id=gridRodata_OutstockM7301_3]':{
				beforeedit:this.beforeedit7301_2,
				edit:this.rodata_OutStock_MReceiptUIcompare
			},
			//点击修改按钮
			'rodata_OutstockMReceiptUI button[name=edit]':{
				click:this.edit
			},
			//保存
			'rodata_OutstockMReceiptUI button[name=save]':{
				click:this.save
			},
			'rodata_OutstockMReceiptUI button[name=undo]':{
				click:this.undo
			},//撤销
			'rodata_OutstockMReceiptUI button[name=query]':{
				click:this.query
			},//查询
			'rodata_OutstockMReceiptUI button[name=refresh]':{
				click:this.refresh
			},//刷新
			'rodata_OutstockMReceiptUI grid[id=gridRodata_OutstockM7301_3]':{
				beforeedit:this.gridRodata_OutstockM7301_3beforeedit,
				edit:this.gridRodata_OutstockM7301_3
			}//网格编辑
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		isCanEdit7301=false;
		//显示变量0为不显示，1为显示
		var realBox7301=commonGetModuleField('7301','realBox')[0].flag;
		var realDis7301=commonGetModuleField('7301','realDis')[0].flag;
		var realQmin7301=commonGetModuleField('7301','realQmin')[0].flag;
		var planBox7301=commonGetModuleField('7301','planBox')[0].flag;
		var planDis7301=commonGetModuleField('7301','planDis')[0].flag;
		var planQmin7301=commonGetModuleField('7301','planQmin')[0].flag;

		var packingUnit7301=commonGetModuleField('7301','packingUnit')[0].flag;
		var packingSpec7301=commonGetModuleField('7301','packingSpec')[0].flag;
		if(realBox7301==0){
			Ext.getCmp('realBox7301').setVisible(false);
		}
		if(realDis7301==0){
			Ext.getCmp('realDis7301').setVisible(false);
		}
		if(realQmin7301==0){
			Ext.getCmp('realQmin7301').setVisible(false);
		}
		if(planBox7301==0){
			Ext.getCmp('planBox7301').setVisible(false);
		}
		if(planDis7301==0){
			Ext.getCmp('planDis7301').setVisible(false);
		}
		if(planQmin7301==0){
			Ext.getCmp('planQmin7301').setVisible(false);
		}
		if(packingUnit7301==0){
			Ext.getCmp('packingUnit7301').setVisible(false);
		}
		if(packingSpec7301==0){
			Ext.getCmp('packingSpec7301').setVisible(false);
		}
		Ext.getCmp('rodata_OutstockM7301_1').getStore().load();
	},
	
	gridRodata_OutstockM7301_3beforeedit:function(e){
		if(!isCanEdit7301)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//退厂回单》商品数量较验等
	gridRodata_OutstockM7301_3:function(editor,e,eOpts){
		if(e.field=='realBox' || e.field=='realQty' || e.field=='realQmin' || e.field=='realDis'){
			if(e.record.data.realQty>e.record.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityCannotMoreThanPlan);
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='realBox'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}else if(e.field=='realQty'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}else if(e.field=='realQmin'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}
				else if(e.field=='realDis'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}
			}
		}
	},
	
	/*
	 * 刷新
	 */
	  refresh:function(){
 	  	Ext.getCmp('gridRodata_OutstockM7301_3').getStore().removeAll();
 	  	Ext.getCmp('rodata_OutstockM7301_1').getStore().reload();
		Ext.getCmp('cmbOutstock_name7301_2').setValue();
 	  },
 	  
	/**
	 * 查找
	 */
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=7301;
		queryGrid='rodata_OutstockM7301_1';					
	},
	
	undo:function(){
		commonMenu4Button('menu7301','undo');
		Ext.getCmp('rodata_OutstockM7301_1').fireEvent('selectionchange','');
	},
	
	/*
	 * 点击保存按钮，回单
	 */
	save:function(){
			var gridcount=Ext.getCmp("gridRodata_OutstockM7301_3").getStore().getCount();
			if(gridcount>0)
			{
				if(!commonCheckdetailgrid('gridRodata_OutstockM7301_3',0,gridcount-1))
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

		if(commonCheckMster('formRodata_outstockm7301_2'))
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,
			function(button, text) {
			if (button == 'yes') {
				var msgShow = commonMegShow($i18n_prompt.saving_wait);
				var listDetail1 = [];
				for(var i=0;i<gridcount;i++ ){
					var objRecord  = Ext.getCmp('gridRodata_OutstockM7301_3').getStore().getAt(i);
					var d={	
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:objRecord.get('warehouseNo'),
								ownerNo:objRecord.get('ownerNo')
							},
							outstockNo:objRecord.get('outstockNo'),
							articleNo:objRecord.get('articleNo'),
							barcode:objRecord.get('barcode'),
							packingQty:objRecord.get('packingQty'),
					    	SCellNo:objRecord.get('SCellNo'),
							SLabelNo:objRecord.get('SLabelNo'),
							articleQty:objRecord.get('articleQty'),
							realQty: objRecord.get('planBox') * objRecord.get('packingQty')
							+objRecord.get('planQmin') * objRecord.get('qminOperatePacking')
							+objRecord.get('planDis'),
							DCellNo:objRecord.get('DCellNo'),
							outstockNo:objRecord.get('outstockNo'),
							produceDate:objRecord.get('produceDate'),
							expireDate:objRecord.get('expireDate'),
							lotNo:objRecord.get('lotNo'),
							quality:objRecord.get('quality'),
							rsvBatch1:objRecord.get('rsvBatch1'),
							rsvBatch2:objRecord.get('rsvBatch2'),
							rsvBatch3:objRecord.get('rsvBatch3'),
							rsvBatch4:objRecord.get('rsvBatch4'),
							rsvBatch5:objRecord.get('rsvBatch5'),
							rsvBatch6:objRecord.get('rsvBatch6'),
							rsvBatch7:objRecord.get('rsvBatch7'),
							rsvBatch8:objRecord.get('rsvBatch8')
							
					};
					listDetail1.push(d);
				}
					
				var jsonlistDetail1 = Ext.encode(listDetail1);		
				var outUserId=Ext.getCmp('cmbOutstock_name7301_2').getValue();
				var params = {
						str:jsonlistDetail1,
						outUserId:outUserId
				};
				Ext.Ajax.request({
					method:'POST',
					url:'rodata_OutstockMAction_save.action',
					params:params,
					success:function(response){
						msgShow.hide();
						var data1 = Ext.decode(response.responseText);
						if(data1.isSucc){
							commonMenu4Button('menu7301','save');
							Ext.example.msg($i18n.prompt,data1.msg);
							isCanEdit7301=false;
							Ext.getCmp('rodata_OutstockM7301_1').getStore().reload();
							Ext.getCmp('gridRodata_OutstockM7301_3').getStore().reload();
						}else{
							Ext.example.msg($i18n.prompt,data1.msg+data1.obj);
						}				
					},
					failure:function(response){
						msgShow.hide();
						Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
					}
				});	
			}});
		}
	},
	
	
	/*
	 * 点击修改按钮,改为可编辑
	 */
	edit:function()
	{
		commonMenu4Button('menu7301','edit');
		isCanEdit7301=true;
		Ext.getCmp('gridRodata_OutstockM7301_3').editingPlugin.startEdit(0,10);
	},
	beforeedit7301_2:function(e){
		if(!isCanEdit7301)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//退厂回单数据校验
	rodata_OutStock_MReceiptUIcompare:function(editor,e,eOpts)
	{
		/*if(e.field=='realBox')
		{*/
			if(e.objRecord.data.realQty>e.objRecord.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotLargeToArticleQty);
				editor.context.objRecord.set(e.field,editor.context.originalValue);
				if(e.field=='realBox'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}else if(e.field=='realQmin'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}else if(e.field=='realDis'){
					editor.context.objRecord.set('realQty',
							e.objRecord.data.realBox*e.objRecord.data.packingQty
							+editor.context.originalValue*e.objRecord.data.qminOperatePacking
							+e.objRecord.data.realDis);
				}
			}
		//}
	},
	
	//网格选择前事件
	rodata_OutstockM7301_1Beforeselect:function(){
	    if(isCanEdit7301)
	    {
	        return  false;  
	    }
	},
	
	/*
	 *网格显示
	 *zhouhuan
	 */
	selectionchange7301_1:function(th,selected,eOpts)
	{
		var objRecord = Ext.getCmp('rodata_OutstockM7301_1').getSelectionModel().getSelection();
		if(objRecord.length!=0){
			Ext.getCmp('txtOutstock_no7301_2').setValue(objRecord[0].data.outstockNo);
			Ext.get('rgstName7301').dom.innerHTML=objRecord[0].data.rgstName;
			Ext.get('rgstDate7301').dom.innerHTML=objRecord[0].data.rgstDate;
			Ext.get('updtName7301').dom.innerHTML=objRecord[0].data.updtName;
			Ext.get('updtDate7301').dom.innerHTML=objRecord[0].data.updtDate;
			
		var listDetail1 = [];
		var strDtl={
				columnId:'a.enterprise_no',
				value:Ext.get('enterpriseNo').getValue()
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.warehouse_no',
			value:objRecord[0].get("warehouseNo")
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.owner_no',
			value:objRecord[0].get("ownerNo")
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.outstock_no',
			value:objRecord[0].get("outstockNo")
		};
		listDetail1.push(strDtl);
		var jsonlistDetail1 = Ext.encode(listDetail1);
		var strWheresql = {
			str : jsonlistDetail1
		};
		
		Ext.apply(Ext.getCmp('gridRodata_OutstockM7301_3').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('gridRodata_OutstockM7301_3').getStore().removeAll();
		Ext.getCmp('gridRodata_OutstockM7301_3').getStore().load();
		commonMenu4Button('menu7301','undo');
		isCanEdit7301=false;
		}
			
	}
});

    /**
	 * 填充
	 * @param {} grid
	 * @param {} rowindex7301
	 * @param {} colIndex
	 */
	function editOutStockD(rowindex7301){
		 var objRecord=Ext.getCmp('rodata_OutstockM7301_1').getStore().getAt(rowindex7301);
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
			
			Ext.apply(Ext.getCmp('gridRodata_OutstockM7301_3')
				.getStore().proxy.extraParams,
				wheresql);
			Ext.getCmp('gridRodata_OutstockM7301_3').getStore()
				.removeAll();
			Ext.getCmp('gridRodata_OutstockM7301_3').getStore()
				.load();
			isCanEdit7301=false;
		
	}
