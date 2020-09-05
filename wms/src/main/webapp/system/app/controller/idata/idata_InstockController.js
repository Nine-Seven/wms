/**
 * 模块名称：上架单
 * 模块编码：4701
 * 创建：周欢 
 */
var g_IsCanEdit4701 = false;
//var g_IsCanDiffQtyInstock4701 = '0';//1:上架回单时回单数量允许差异,0：上架回单时回单数量不允许差异
Ext.define('cms.controller.idata.idata_InstockController', {
	extend : 'Ext.app.Controller',
	requires : [ 
	             'cms.view.idata.idata_InstockUI'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({
			//上架回单》修改
			'idata_InstockUI commMenuWidget4[id=menu4701] button[name=edit]':{
				click:this.edit
			},
			//上架回单》撤销
			'idata_InstockUI commMenuWidget4[id=menu4701] button[name=undo]':{
				click:this.undo
			},
			//上架回单》保存
			'idata_InstockUI commMenuWidget4[id=menu4701] button[name=save]':{
				click:this.save
			},
			//上架回单》查询
			'idata_InstockUI commMenuWidget4[id=menu4701] button[name=query]':{
				click:this.query
			},
			//上架回单》刷新
			'idata_InstockUI commMenuWidget4[id=menu4701] button[name=refresh]':{
				click:this.comTabpanelChange
			},
			//上架回单》选择货主查询相应单据
			'idata_InstockUI bdef_DefOwnerCombo[id=comOwnerNo4701]':{
				select:this.comOwnerNo4701Select
			},
			//上架回单》明细信息界面上架单选择事件
			'idata_InstockUI remoteCombo[id=cmbInstock_noM4701]':{
				select:this.loadInstockMInInfo
			},
			//TAB页切换
			'idata_InstockUI tabpanel[id=tabPIdInfo4701]':{
				tabchange:this.comTabpanelChange
			},
			//明细信息界面单据切换时间
			'idata_InstockUI grid[id=gridIdata_instockMOP4701]':{
				beforeselect:this.gridIdata_instockMOP4701Beforeselect,
				selectionchange:this.gridIdata_instockMOP4701Selectionchange
			},
			//历史查询界面单据切换时间
			'idata_InstockUI grid[id=gridIdata_instockMCheck4701]':{
				selectionchange:this.gridIdata_instockMCheck4701Selectionchange
			},
			//编辑事件
			'idata_InstockUI grid[id=gridInstockDOP4701]':{
				beforeedit:this.gridInstockDOP4701beforeedit,
				edit:this.gridInstockDOP4701edit
			},
			//上架回单》关闭前
			'idata_InstockUI':{
				beforeclose:this.idata_InstockUIBeforeclose
			},
			//拆笔
			'idata_InstockUI grid[id=gridInstockDOP4701] actioncolumn':{
				click:this.actioncolumnClick
			},
			//上架回单》实际储位选择
			'cdef_DefCellCombo[id=cmbRealCellNo4701]':{
				focus:this.cmbRealCellNo4701focus
			},
			//上架回单》查询
			'idata_InstockUI button[id=butCheckDate4701]':{
				click:this.queryCheckM
			},
			//上架回单》查询
			'idata_InstockUI remoteCombo[id=cmbInstock_noCheck4701]':{
				select:this.queryCheckM
			},//根据类型加载单头
			'idata_InstockUI combo[id=cmbQ_type4701]':{
				select:this.selectcmbQ_type4701
			}
			
		});
		bindEnterSkip($('#formInstockMCheck4701'));//调用键盘处理方法
	},
	/**
	 * 初始化界面
	 */
	initializtion:function()
	{
		g_IsCanEdit4701=false;		
		this.ownerNoLoad();
		
		
		//显示变量0为不显示，1为显示
		var planBox4701=commonGetModuleField('4701','planBox')[0].flag;
		var planQmin4701=commonGetModuleField('4701','planQmin')[0].flag;
		var planDis4701=commonGetModuleField('4701','planDis')[0].flag;
		var packingUnit4701=commonGetModuleField('4701','packingUnit')[0].flag;
		var packingSpec4701=commonGetModuleField('4701','packingSpec')[0].flag;
		
		if(planBox4701==0){
			Ext.getCmp('planBox4701').setVisible(false);
			Ext.getCmp('planBox4701_2').setVisible(false);
		}
		if(planQmin4701==0){
			Ext.getCmp('planQmin4701').setVisible(false);
			Ext.getCmp('planQmin4701_2').setVisible(false);
		}
		if(planDis4701==0){
			Ext.getCmp('planDis4701').setVisible(false);
			Ext.getCmp('planDis4701_2').setVisible(false);
		}
		if(packingUnit4701==0){
			Ext.getCmp('packingUnit4701').setVisible(false);
		}
		if(packingSpec4701==0){
			Ext.getCmp('packingSpec4701').setVisible(false);
		}
	},
	selectcmbQ_type4701:function(){
		var listdetail = [];
		if(!Ext.isEmpty(Ext.getCmp("cmbQ_type4701").getValue()))
		{
		    var strd={
				columnId:'a.locate_type',
				value:Ext.getCmp("cmbQ_type4701").getValue()
			};
			listdetail.push(strd);
		}
		if(!Ext.isEmpty(Ext.getCmp("cmbInstock_noM4701").getValue()))
		{
		    var strd={
				columnId:'a.instock_no',
				value:Ext.getCmp("cmbInstock_noM4701").getValue()
			};
			listdetail.push(strd);
		}
		if(!Ext.isEmpty(Ext.getCmp("comOwnerNo4701").getValue()))
		{
		    var strd={
				columnId:'a.owner_no',
				value:Ext.getCmp("comOwnerNo4701").getValue()
			};
			listdetail.push(strd);
		}
		var jsonDetail1 = Ext.encode(listdetail);
		var strWheresql = {
				strFlag : "1",
				strQuery : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridIdata_instockMOP4701')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridIdata_instockMOP4701').getStore()
					.removeAll();
			Ext.getCmp('gridIdata_instockMOP4701').getStore()
					.load();
		
		
	},
	//货主加载
	ownerNoLoad:function()
    {
		if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockInfoTab4701' )
		{
			var strFlag = '1';
		}else if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockCheckTab4701')
		{
			var strFlag = '2';
		}
		  var strParm = 
			{
				strFlag : strFlag	
			};
		Ext.apply(Ext.getCmp('comOwnerNo4701').getStore().proxy.extraParams,strParm);
		Ext.getCmp('comOwnerNo4701').getStore().removeAll();
		Ext.getCmp('comOwnerNo4701').getStore().load();
    },
    
	//上架回单》实际储位加载
	cmbRealCellNo4701focus:function(queryEvent,eOpts)
	{
	  var objInstockD = Ext.getCmp('gridInstockDOP4701').getSelectionModel().getSelection();
	  var strParm = 
		{
			strOwnerNo : objInstockD[0].get('ownerNo')	
		};
		Ext.apply(Ext.getCmp('cmbRealCellNo4701').getStore().proxy.extraParams,strParm);
		Ext.getCmp('cmbRealCellNo4701').getStore().removeAll();
		Ext.getCmp('cmbRealCellNo4701').getStore().load();
	},
	
	//按条件查询单据
	queryCheckM:function()
	{
		var listdetail = [];
		if(!Ext.isEmpty(Ext.getCmp("comOwnerNo4701").getValue()))
		{
		    var strd={
				columnId:'a.owner_no',
				value:Ext.getCmp("comOwnerNo4701").getValue()
			};
			listdetail.push(strd);
		}
		if(!Ext.isEmpty(Ext.getCmp("cmbInstock_noCheck4701").getValue()))
		{
		    var strd={
				columnId:'a.instock_no',
				value:Ext.getCmp("cmbInstock_noCheck4701").getValue()
			};
			listdetail.push(strd);
		}
		if(!Ext.isEmpty(Ext.getCmp("dateBegin_date4701").getValue()) 
				&& !Ext.isEmpty(Ext.getCmp("dateBegin_date4701").getValue()))
		{
		    var strd={
				columnId:"to_date(to_char(a.dispatch_date,'yyyy-mm-dd'),'yyyy-mm-dd')",
				condition:'4',
				valueType:'3',
				value:Ext.util.Format.date(Ext.getCmp("dateBegin_date4701").getValue(),'Y-m-d')
			};
			listdetail.push(strd);
			var strd={
					columnId:"to_date(to_char(a.dispatch_date,'yyyy-mm-dd'),'yyyy-mm-dd')",
					condition:'5',
					valueType:'3',
					value:Ext.util.Format.date(Ext.getCmp("dateEnd_date4701").getValue(),'Y-m-d')
				};
				listdetail.push(strd);
		}
		var jsonDetail1 = Ext.encode(listdetail);
		var strWheresql = {
				strFlag : "2",
				strQuery : jsonDetail1
			};
		Ext.apply(Ext.getCmp('gridIdata_instockMCheck4701').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('gridIdata_instockMCheck4701').getStore().removeAll();
		Ext.getCmp('gridIdata_instockMCheck4701').getStore().load();
	},
	
	//实际储位编辑前校验
	gridInstockDOP4701edit:function(editor,e,eOpts)
	{
		var objInstockD = Ext.getCmp('gridInstockDOP4701').getSelectionModel().getSelection();		
	   	if(e.field == 'realCellNo')
	   	{
	    	//自动定位标识。1-自动定位，上架不允许修改储位；0-手工指定储位
		   if(objInstockD.length!=0 && objInstockD[0].data.autoLocateFlag=='1')
		   {
			   Ext.Msg.alert($i18n_prompt.prompt,$i18n_prompt.aotuLocateFlagIs1);
			   editor.context.record.set(e.field,editor.context.originalValue);
		   }else
		   {
			   if(editor.grid.getStore().
						findBy(function(record, id) {  
								return record.internalId != editor.context.record.internalId 
								&& record.get('articleNo') == editor.context.record.data.articleNo
								&& record.get('labelNo') == editor.context.record.data.labelNo
								&& record.get('barcode') == editor.context.record.data.barcode
								&& record.get('destCellNo') == editor.context.record.data.destCellNo
								&& record.get('packingQty') == editor.context.record.data.packingQty
								&& record.get('produceDate') == editor.context.record.data.produceDate
								&& record.get('expireDate') == editor.context.record.data.expireDate
								&& record.get('realCellNo') == objInstockD[0].data.realCellNo
								&& !Ext.isEmpty(record.get('realCellNo'));
							}) != -1)				
						{
					        editor.context.record.set(e.field,editor.context.originalValue);
							Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.canNotBeTheSameWithOriginalStorege);
						}
		   }
	   }else if(e.field=='planBox' || e.field=='planQmin' || e.field=='planDis')
	   {
			var store=Ext.getCmp('gridInstockDOP4701').getStore().queryBy(function(objrecord) {  
				 if(objrecord.get('articleNo') == objInstockD[0].data.articleNo
					&& objrecord.get('labelNo') == objInstockD[0].data.labelNo
					&& objrecord.get('barcode') == objInstockD[0].data.barcode
					&& objrecord.get('destCellNo') == editor.context.record.data.destCellNo
					&& objrecord.get('packingQty') == editor.context.record.data.packingQty
					&& objrecord.get('produceDate') == editor.context.record.data.produceDate
					&& objrecord.get('expireDate') == editor.context.record.data.expireDate)
	             {
					 return true;
	             }else 
	             {
	            	 return false;
	             }
					
			});
		/*	var numRealQty=0;
			for(var i=0;i<store.length;i++){
				numRealQty+=store.items[i].get('planBox')* store.items[i].get('packingQty')+
				         store.items[i].get('planQmin')* store.items[i].get('qminOperatePacking')+
				         store.items[i].get('planDis');
			}*/
			
			/*if(g_IsCanDiffQtyInstock4701 == '1' && store.items[0].get('articleQty')-numRealQty != 0)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotDifferentWithArticleQty);
				editor.context.record.set(e.field,editor.context.originalValue);
			}*/
		}
	},
	//拆笔
	actioncolumnClick:function(grid, rowIndex, colIndex)
	{
		var objData = grid.getStore().getAt(colIndex);
		if(g_IsCanEdit4701)
	    {	//不管保质期的不允许拆笔
	    	if(objData.data.expiryDays!=-1 && objData.data.autoLocateFlag == '1')
	        {	    	    
		    	Ext.Msg.confirm($i18n_prompt.prompt, $i18n_prompt.sureToSplit, function(button, text) {
				if (button == 'yes') {
					var objIinstokD = Ext.create('cms.model.idata.idata_InstockDModel', {
					});
					objIinstokD.set('labelNo',objData.data.labelNo);
					objIinstokD.set('articleNo',objData.data.articleNo);
					objIinstokD.set('articleName',objData.data.articleName);	
					objIinstokD.set('barcode',objData.data.barcode);
					objIinstokD.set('destCellNo',objData.data.destCellNo);
					objIinstokD.set('packingQty',objData.data.packingQty);
					objIinstokD.set('packingUnit',objData.data.packingUnit);
					objIinstokD.set('packingSpec',objData.data.packingSpec);
					
					objIinstokD.set('articleQty',objData.data.articleQty);
					objIinstokD.set('realCellNo',null);
					objIinstokD.set('planBox',0);
					objIinstokD.set('planQmin',0);
					objIinstokD.set('planDis',0);
					objIinstokD.set('lotNo',objData.data.lotNo);
					objIinstokD.set('produceDate',objData.data.produceDate);
					objIinstokD.set('expireDate',objData.data.expireDate);
					objIinstokD.set('quality',objData.data.quality);
					objIinstokD.set('textQuality',objData.data.textQuality);
					objIinstokD.set('IMPORT_BATCH_NO',objData.data.expiryDays);
					objIinstokD.set('rsvBatch1',objData.data.rsvBatch1);
					objIinstokD.set('rsvBatch2',objData.data.rsvBatch2);
					objIinstokD.set('rsvBatch3',objData.data.rsvBatch3);
					objIinstokD.set('rsvBatch4',objData.data.rsvBatch4);
					objIinstokD.set('rsvBatch5',objData.data.rsvBatch5);
					objIinstokD.set('rsvBatch6',objData.data.rsvBatch6);
					objIinstokD.set('rsvBatch7',objData.data.rsvBatch7);
					objIinstokD.set('rsvBatch8',objData.data.rsvBatch8);
					objIinstokD.set('instockId',objData.data.instockId);	
					objIinstokD.set('splitFlag','1');
					objIinstokD.set('ownerNo',objData.data.ownerNo);
					objIinstokD.set('instockNo',objData.data.instockNo);
					objIinstokD.set('warehouseNo',objData.data.warehouseNo);
					Ext.getCmp('gridInstockDOP4701').store.insert(colIndex+1,objIinstokD);
					for(var i=1;i<=Ext.getCmp('gridInstockDOP4701').getStore().getCount();i++ )
					{
						Ext.getCmp('gridInstockDOP4701').getStore().getAt(i-1).set('rowId',i);
						Ext.getCmp('gridInstockDOP4701').getStore().getAt(i-1).index=i;
					}
				}
				});
	    	}else
	    	{
	    		Ext.example.msg($i18n.prompt,$i18n_prompt.aotuLocateFlagCanNotSplit);
	    		return;
	    	}
	    }
	},
	
	//上架回单》选择货主查询相应单据
	comOwnerNo4701Select:function(combo,records,eOpts)
	{
		if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockInfoTab4701' )
		{
			var strdetail1 = [];
			var d={
				columnId:'a.owner_no',
				value:Ext.getCmp('comOwnerNo4701').getValue()
			};
			strdetail1.push(d);
			var jsonDetail1 = Ext.encode(strdetail1);
			var strwheresql = 
			{
				strQuery : jsonDetail1
			};
			Ext.apply(Ext.getCmp('cmbInstock_noM4701').getStore().proxy.extraParams,strwheresql);
			Ext.getCmp('cmbInstock_noM4701').getStore().removeAll();
			Ext.getCmp('cmbInstock_noM4701').getStore().load();
			this.loadInstockMInInfo();
			
			//取系统参数
			//g_IsCanDiffQtyInstock4701 = commonGetSystemParams(Ext.getCmp('comOwnerNo4701').getValue(),'Instock_diffQty','I','INSTOCK')[0].sdefine;
		}else if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockCheckTab4701')
		{
			var strdetail1 = [];
			var d={
				columnId:'a.owner_no',
				value:Ext.getCmp('comOwnerNo4701').getValue()
			};
			strdetail1.push(d);
			var jsonDetail1 = Ext.encode(strdetail1);
			var strwheresql = 
			{
				strQuery : jsonDetail1
			};
			Ext.apply(Ext.getCmp('cmbInstock_noCheck4701').getStore().proxy.extraParams,strwheresql);
			Ext.getCmp('cmbInstock_noCheck4701').getStore().removeAll();
			Ext.getCmp('cmbInstock_noCheck4701').getStore().load();
				
		}
		
	},
	//上架回单》选择货主查询相应单据
	comTabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockInfoTab4701' )
		{
			Ext.getCmp('comOwnerNo4701').setValue(null);
			Ext.getCmp('cmbQ_type4701').setValue(null);
			Ext.getCmp('cmbInstock_noM4701').setValue(null);
		    this.ownerNoLoad();
		    if(Ext.isEmpty(Ext.getCmp('comOwnerNo4701').getValue()))
		    {
		    	var strParm = 
				{
					strFlag : "1",
					strQuery : null
				};
				Ext.apply(Ext.getCmp('gridIdata_instockMOP4701').getStore().proxy.extraParams,strParm);
				Ext.getCmp('gridIdata_instockMOP4701').getStore().removeAll();
				Ext.getCmp('gridIdata_instockMOP4701').getStore().load();
		    }
			Ext.apply(Ext.getCmp('cmbInstock_noM4701').getStore().proxy.extraParams,strParm);
			Ext.getCmp('cmbInstock_noM4701').getStore().removeAll();
			Ext.getCmp('cmbInstock_noM4701').getStore().load();
		}else if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockCheckTab4701')
		{
			Ext.getCmp('comOwnerNo4701').setValue(null);
			Ext.getCmp('cmbInstock_noCheck4701').setValue(null);
			Ext.getCmp('dateBegin_date4701').setValue(null);
			this.ownerNoLoad();
			var strWheresql = {
				strQuery : null
			};
			Ext.getCmp('gridIdata_instockMCheck4701').getStore()
			.removeAll();
			Ext.apply(Ext.getCmp('cmbInstock_noCheck4701')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbInstock_noCheck4701').getStore()
					.removeAll();
			Ext.getCmp('cmbInstock_noCheck4701').getStore()
					.load();
		}
		
	},
	//修改
	edit:function(){
		var data = Ext.getCmp('gridIdata_instockMOP4701').getSelectionModel().getSelection();
        if(data.length != 0){
			commonMenu4Button('menu4701','edit');
			g_IsCanEdit4701=true;
			Ext.getCmp('comOwnerNo4701').setReadOnly(true); 
			Ext.getCmp('cmbQ_type4701').setReadOnly(true); 
			Ext.getCmp('cmbInstock_noM4701').setReadOnly(true); 
			Ext.getCmp('InstockCheckTab4701').setDisabled(true);
			Ext.getCmp('cmbWorkerNo4701').focus();
        }
	},
	//上架回单》撤销
	undo:function(){
		commonMenu4Button('menu4701','undo');
		g_IsCanEdit4701=false;
		Ext.getCmp('comOwnerNo4701').setReadOnly(false); 
		Ext.getCmp('cmbQ_type4701').setReadOnly(false); 
		Ext.getCmp('cmbInstock_noM4701').setReadOnly(false); 
		Ext.getCmp('InstockCheckTab4701').setDisabled(false);
		Ext.getCmp('gridIdata_instockMOP4701').fireEvent('selectionchange');
	},
	
	//保存
	save:function(){
		var objInstockD = Ext.getCmp("gridInstockDOP4701").getStore();
		var gridcount = objInstockD.getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('gridInstockDOP4701',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.tableCannotBeNull);
			return;
		}
		
		for(var i = 0; i < gridcount; i++ )
		{
			var store=Ext.getCmp('gridInstockDOP4701').getStore().queryBy(function(objrecord) {  
				 if(objrecord.get('articleNo') == objInstockD.getAt(i).data.articleNo
					&&objrecord.get('labelNo') == objInstockD.getAt(i).data.labelNo
					&&objrecord.get('barcode') == objInstockD.getAt(i).data.barcode
					&&objrecord.get('produceDate') == objInstockD.getAt(i).data.produceDate
					&&objrecord.get('expireDate') == objInstockD.getAt(i).data.expireDate)
	             {
					 return true;
	             }else 
	             {
	            	 return false;
	             }
					
			});
		/*	var numRealQty=0;
			for(var j=0;j<store.length;j++){
				numRealQty+=store.items[i].get('planBox')* store.items[i].get('packingQty')+
		         store.items[i].get('planQmin')* store.items[i].get('qminOperatePacking')+
		         store.items[i].get('planDis');			
			}
			if(g_IsCanDiffQtyInstock4701 == '0' && objInstockD.getAt(i).data.articleQty - numRealQty != 0)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotDifferentWithArticleQty);
				Ext.getCmp("gridInstockDOP4701").getStore().getAt(i).set("realBox",null);
				Ext.getCmp("gridInstockDOP4701").getStore().getAt(i).set("realDis",null);
				return;
			}
			*/
			if(objInstockD.getAt(i).data.planBox * objInstockD.getAt(i).data.packingQty+
					objInstockD.getAt(i).data.planQmin * objInstockD.getAt(i).data.qminOperatePacking + objInstockD.getAt(i).data.planDis == 0)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotBeNull);
				Ext.getCmp("gridInstockDOP4701").getStore().getAt(i).set("planBox",null);
				Ext.getCmp("gridInstockDOP4701").getStore().getAt(i).set("planQmin",null);
				Ext.getCmp("gridInstockDOP4701").getStore().getAt(i).set("planDis",null);
				return;
			}
		};
		if(!commonCheckMster('formInstockMOP4701'))
		{
			return;
		}
		
		Ext.Msg.confirm($i18n_prompt.prompt, $i18n_prompt.saveOrNot,
		function(button, text) {
		if (button == 'yes') {
			var instockWorker= Ext.getCmp('cmbWorkerNo4701').getValue();
			var objdata=Ext.getCmp('gridIdata_instockMOP4701').getSelectionModel().getSelection();			
			var strmaster={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						instockNo:objdata[0].get('instockNo'),
						warehouseNo:objdata[0].get('warehouseNo'),
						ownerNo:objdata[0].get('ownerNo')
					},
					instockWorker:instockWorker	,
					updtName:Ext.get('workerNo').getValue()
			};
			var listDetail1 = [];
			for(var i=0;i<gridcount;i++ ){
				var objrecord  = Ext.getCmp('gridInstockDOP4701').getStore().getAt(i);
				var d={		
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							instockNo:objrecord.get('instockNo'),
							warehouseNo:objrecord.get('warehouseNo'),
							ownerNo:objrecord.get('ownerNo'),
							instockId:objrecord.get('instockId')
						},
						directSerial:objrecord.get('directSerial'),
						instockType:objrecord.get('instockType'),
						cellNo:objrecord.get('cellNo'),
						cellId:objrecord.get('cellId'),
						containerNo:objrecord.get('containerNo'),
						articleNo:objrecord.get('articleNo'),
						articleId:objrecord.get('articleId'),
						packingQty:objrecord.get('packingQty'),
						destCellNo:objrecord.get('destCellNo'),
						destCellId:objrecord.get('destCellId'),
						articleQty:objrecord.get('articleQty'),
						realCellNo:objrecord.get('realCellNo'),
						realQty:objrecord.get('planBox')* objrecord.get('packingQty') + 
						        objrecord.get('planQmin')* objrecord.get('qminOperatePacking') +
						        objrecord.get('planDis'),
						sourceNo:objrecord.get('sourceNo'),
						status:objrecord.get('status'),
						authorizedWorker:objrecord.get('authorizedWorker'),
						scanLabelNo:objrecord.get('scanLabelNo'),
						labelNo:objrecord.get('labelNo'),
						businessType:objrecord.get('businessType'),
						produceDate:objrecord.get('produceDate'),
						quality:objrecord.get('quality'),
						lotNo:objrecord.get('lotNo'),
						importBatchNo:objrecord.get('importBatchNo'),
						expireDate:objrecord.get('expireDate'),
						rsvBatch1:objrecord.get('rsvBatch1'),
						rsvBatch2:objrecord.get('rsvBatch2'),
						rsvBatch3:objrecord.get('rsvBatch3'),
						rsvBatch4:objrecord.get('rsvBatch4'),
						rsvBatch5:objrecord.get('rsvBatch5'),
						rsvBatch6:objrecord.get('rsvBatch6'),
						rsvBatch7:objrecord.get('rsvBatch7'),
						rsvBatch8:objrecord.get('rsvBatch8'),
						splitFlag:objrecord.get('splitFlag')
				};
				listDetail1.push(d);
			}
			var jsonMaster = Ext.encode(strmaster);
			var jsonDetail1 = Ext.encode(listDetail1);		
			var params = {
					strInstockM:jsonMaster,
					strInstockD:jsonDetail1
			};
			Ext.Ajax.request({
				method:'POST',
				url:'idata_InstockAction_save.action',
				params:params,
				success:function(response){
					var objdata = Ext.decode(response.responseText);
					if(objdata.isSucc){
						commonMenu4Button('menu4701','save');
						Ext.example.msg($i18n_prompt.prompt,objdata.msg);
						g_IsCanEdit4701=false;
						Ext.getCmp('gridIdata_instockMOP4701').store.reload();
						Ext.getCmp('InstockCheckTab4701').setDisabled(false);
						Ext.getCmp('gridInstockDOP4701').getStore().removeAll();
						
						Ext.getCmp('comOwnerNo4701').setReadOnly(false); 
						Ext.getCmp('cmbQ_type4701').setReadOnly(false); 
						Ext.getCmp('cmbInstock_noM4701').setReadOnly(false); 
					}else{
						Ext.Msg.alert($i18n_prompt.prompt,objdata.msg+objdata.obj);
					}
				},
				failure:function(response){
					Ext.Msg.alert($i18n_prompt.prompt,$i18n_prompt.CannotSubForWeb);
				}
			});	
		}});
	},
	
	//查询
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=4701;
		queryGrid='gridIdata_instockMOP4701';	
	},
	
	//刷新
	refresh:function(){
		
		if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockCheckTab4701' )
		{
			Ext.getCmp('cmbInstock_noCheck4701').setValue(null);
			Ext.getCmp('dateBegin_date4701').setValue(null);
		}else if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockInfoTab4701' )
		{
			Ext.getCmp('cmbInstock_noM4701').setValue("");
		}
		Ext.getCmp('comOwnerNo4701').setValue(null);
		Ext.getCmp('comOwnerNo4701').getStore().load();
		
	},
	detailAdd:function(){
		console.log('detaildAdd');
	},
	
	//单据切换前将网格设为可编辑状态
	gridIdata_instockMOP4701Beforeselect:function()
    {
	    if(g_IsCanEdit4701)
	    {
	        return  false;  
	    }
	},
	
	//明细界面单据切换
	gridIdata_instockMOP4701Selectionchange:function()
	{
		var objdata = Ext.getCmp('gridIdata_instockMOP4701').getSelectionModel().getSelection();
        if(objdata.length == 0){
    		Ext.getCmp('gridInstockDOP4701').getStore().removeAll();
        }else{
        	var strdetail1 = [];
			var d={
				columnId:'iid.instock_no',
				value:objdata[0].get('instockNo')
			};
			strdetail1.push(d);
			if(!Ext.isEmpty(Ext.getCmp('comOwnerNo4701').getValue()))
			{
				var d={
					columnId:'iid.owner_no',
					value:Ext.getCmp('comOwnerNo4701').getValue()
				};
				strdetail1.push(d);
			}
			var jsonDetail1 = Ext.encode(strdetail1);
			var strwheresql = 
			{
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridInstockDOP4701').getStore().proxy.extraParams,strwheresql);
			Ext.getCmp('gridInstockDOP4701').getStore().removeAll();
			Ext.getCmp('gridInstockDOP4701').getStore().load();
			Ext.get('rgstName4701').dom.innerHTML = objdata[0].data.rgstName;
			Ext.get('rgstDate4701').dom.innerHTML = objdata[0].data.rgstDate;
			Ext.get('updtName4701').dom.innerHTML = objdata[0].data.updtName;
			Ext.get('updtDate4701').dom.innerHTML = objdata[0].data.updtDate;
        }
	},
	
	//理解查询界面单据切换
	gridIdata_instockMCheck4701Selectionchange:function()
	{
		var objdata = Ext.getCmp('gridIdata_instockMCheck4701').getSelectionModel().getSelection();
        if(objdata.length == 0){
    		Ext.getCmp('gridInstockDCheck4701').getStore().removeAll();
        }else{
        	var strdetail1 = [];
			var d={
				columnId:'iid.instock_no',
				value:objdata[0].get('instockNo')
			};
			strdetail1.push(d);
			var jsonDetail1 = Ext.encode(strdetail1);
			var strwheresql = 
			{
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridInstockDCheck4701').getStore().proxy.extraParams,strwheresql);
			Ext.getCmp('gridInstockDCheck4701').getStore().removeAll();
			Ext.getCmp('gridInstockDCheck4701').getStore().load();
			
        }
	},
	
	//编辑前事件
	gridInstockDOP4701beforeedit:function(e){  
	    if(!g_IsCanEdit4701)
	    {
	        e.cancel = true;
	        return  false;  
	    }
	},
	
	//界面关闭前事件
	idata_InstockUIBeforeclose:function(){
		Ext.getCmp('comOwnerNo4701').setValue(null);
	},
	
	//加载明细界面头档信息
	loadInstockMInInfo:function()
	{
		var listdetail = [];
		if(!Ext.isEmpty(Ext.getCmp("cmbQ_type4701").getValue()))
		{
		    var strd={
				columnId:'a.locate_type',
				value:Ext.getCmp("cmbQ_type4701").getValue()
			};
			listdetail.push(strd);
		}
		if(!Ext.isEmpty(Ext.getCmp("cmbInstock_noM4701").getValue()))
		{
		    var strd={
				columnId:'a.instock_no',
				value:Ext.getCmp("cmbInstock_noM4701").getValue()
			};
			listdetail.push(strd);
		}
		if(!Ext.isEmpty(Ext.getCmp("comOwnerNo4701").getValue()))
		{
		    var strd={
				columnId:'a.owner_no',
				value:Ext.getCmp("comOwnerNo4701").getValue()
			};
			listdetail.push(strd);
		}
		var jsonDetail1 = Ext.encode(listdetail);
		var strWheresql = {
				strFlag : "1",
				strQuery : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridIdata_instockMOP4701')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridIdata_instockMOP4701').getStore()
					.removeAll();
			Ext.getCmp('gridIdata_instockMOP4701').getStore()
					.load();
	},
	//加载历史界面头档信息
	loadInstockMInHtyCheck:function()
	{
		 var strd={
				columnId:'a.owner_no',
				value:Ext.getCmp("comOwnerNo4701").getValue()
			};
			listdetail.push(strd);
		var jsonDetail1 = Ext.encode(listdetail);
		var strWheresql = {
				strQuery : jsonDetail1 
			};
			Ext.getCmp('gridIdata_instockMCheck4701').getStore()
			.removeAll();
			Ext.apply(Ext.getCmp('cmbInstock_noCheck4701')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbInstock_noCheck4701').getStore()
					.removeAll();
			Ext.getCmp('cmbInstock_noCheck4701').getStore()
					.load();
	}
});
 