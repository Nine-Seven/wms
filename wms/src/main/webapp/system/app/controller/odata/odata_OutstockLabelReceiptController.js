/**
 * 模块名称：拣货任务标签回单
 * 模块编码：3501
 * 创建：周欢
 */
var g_strIisCanEdit3501 = false;
var g_Refresh = false;//回单成功后是否刷新 huangb 20160801
Ext.define('cms.controller.odata.odata_OutstockLabelReceiptController',{
	extend:'Ext.app.Controller',
	controllerId:'odata_OutstockLabelReceiptController',
	requires:[
          'cms.view.odata.odata_OutstockLabelReceiptUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//拣货任务标签回单》货主选择
			'odata_OutstockLabelReceiptUI bdef_DefOwnerCombo[id = cmbOwnerNo3501]':{
				beforeselect:this.cmbOwnerNo3501beforeselect,
				select:this.cmbOwnerNo3501Select
			},
			//历史单据查询》货主选择
			'odata_OutstockLabelReceiptUI bdef_DefOwnerCombo[id = cmbOwnerNoCheck3501]':{
				select:this.cmbOwnerNoCheck3501Select
			},
			//拣货任务标签回单》修改
			'odata_OutstockLabelReceiptUI button[name=edit]':{
				click:this.editClick
			},
			//拣货任务标签回单》撤销
			'odata_OutstockLabelReceiptUI button[name=undo]':{
				click:this.undoClick
			},
			//拣货任务标签回单》查找
			'odata_OutstockLabelReceiptUI button[name=query]':{
				click:this.queryClick
			},
			//拣货任务标签回单》刷新
			'odata_OutstockLabelReceiptUI button[name=refresh]':{
				click:this.TabpanelChange
			},
			//拣货任务标签回单》波次选择
			'odata_OutstockLabelReceiptUI combo[id=cmbWave_no3501]':{
				select:this.cmbWave_no3501change
			},
			//历史单据查询》波次选择
			'odata_OutstockLabelReceiptUI remoteCombo[id=cmbWave_noCheck3501]':{
				beforequery:this.cmbWave_noCheck3501Beforequery,
				select:this.cmbWave_noCheck3501change
			},
			//拣货任务标签回单》批次选择
			'odata_OutstockLabelReceiptUI combo[id=cmbBatch_no3501]':{
				select:this.cmbBatch_no3501change
			},
			//历史单据查询》批次选择
			'odata_OutstockLabelReceiptUI combo[id=cmbBatch_noCheck3501]':{
				select:this.cmbBatch_noCheck3501change
			},
			//拣货任务标签回单》作业类型选择
			'odata_OutstockLabelReceiptUI combo[id=cmbOperate_type3501]':{
				select:this.cmbOperate_type3501change
			},
			//历史单据查询》作业类型选择
			'odata_OutstockLabelReceiptUI combo[id=cmbOperate_typeCheck3501]':{
				select:this.cmbOperate_typeCheck3501change
			},
			//拣货任务标签回单》下架单号选中
			'odata_OutstockLabelReceiptUI combo[id=cmbOutstockNo3501]':{
				select:this.cmbOutstockNo3501change
			},
			//历史单据查询》下架单号选中
			'odata_OutstockLabelReceiptUI combo[id=cmbOutstockNoCheck3501]':{
				select:this.cmbOutstockNoCheck3501change
			},
			//拣货任务标签回单》单据选择
			'odata_OutstockLabelReceiptUI grid[id=gridOutstockM3501]':{
				beforeselect:this.gridOutstockM3501Beforeselect,
				selectionchange:this.gridOutstockM3501selectionchange
			},
			//历史单据查询》单据选择
			'odata_OutstockLabelReceiptUI grid[id=gridOutstockMCheck3501]':{
				selectionchange:this.gridOutstockMCheck3501selectionchange
			},
			//拣货任务标签回单》商品数量较验等
			'odata_OutstockLabelReceiptUI grid[id=gridOutstockD3501]':{
				beforeedit:this.gridOutstockD3501beforeedit,
				edit:this.gridOutstockD3501edit
			},
			//拣货任务标签回单》回单
			'odata_OutstockLabelReceiptUI button[id=butReceipt3501]':{
				click:this.butReceipt3501click
			},
			//拣货任务标签回单》关闭前事件
			'odata_OutstockLabelReceiptUI':{
				beforeclose:this.odata_OutstockLabelReceiptUIBeforeclose
			},
			//TAB页切换
			'odata_OutstockLabelReceiptUI tabpanel[id=tabpanel3501]':{
				tabchange:this.TabpanelChange
			}
		});	
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		g_strIisCanEdit3501=false;
		Ext.getCmp('cmbOwnerNo3501').setValue(null);
		Ext.getCmp('cmbOwnerNo3501').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var articleQty_1=commonGetModuleField('3501','articleQty')[0].flag;
		var planBox3501_1=commonGetModuleField('3501','planBox')[0].flag;
		var planQmin3501_1=commonGetModuleField('3501','planQmin')[0].flag;
		var planDis3501_1=commonGetModuleField('3501','planDis')[0].flag;
		var realQty_1=commonGetModuleField('3501','realQty')[0].flag;
		var realBox3501_1=commonGetModuleField('3501','realBox')[0].flag;
		var realQmin3501_1=commonGetModuleField('3501','realQmin')[0].flag;
		var realDis3501_1=commonGetModuleField('3501','realDis')[0].flag;
		var packingUnit3501_1=commonGetModuleField('3501','packingUnit')[0].flag;
		var packingSpec3501_1=commonGetModuleField('3501','packingSpec')[0].flag;
		
		var articleQty_2=commonGetModuleField('3501','articleQty')[0].flag;
		var planBox3501_2=commonGetModuleField('3501','planBox')[0].flag;
		var planQmin3501_2=commonGetModuleField('3501','planQmin')[0].flag;
		var planDis3501_2=commonGetModuleField('3501','planDis')[0].flag;
		var realQty_2=commonGetModuleField('3501','realQty')[0].flag;
		var realBox3501_2=commonGetModuleField('3501','realBox')[0].flag;
		var realQmin3501_2=commonGetModuleField('3501','realQmin')[0].flag;
		var realDis3501_2=commonGetModuleField('3501','realDis')[0].flag;
		var packingUnit3501_2=commonGetModuleField('3501','packingUnit')[0].flag;
		var packingSpec3501_2=commonGetModuleField('3501','packingSpec')[0].flag;

		if(articleQty_1==0){
			Ext.getCmp('articleQty_1').setVisible(false);
		}
		if(planBox3501_1==0){
			Ext.getCmp('planBox3501_1').setVisible(false);
		}
		if(planQmin3501_1==0){
			Ext.getCmp('planQmin3501_1').setVisible(false);
		}
		if(planDis3501_1==0){
			Ext.getCmp('planDis3501_1').setVisible(false);
		}
		if(realQty_1==0){
			Ext.getCmp('realQty_1').setVisible(false);
		}
		if(realBox3501_1==0){
			Ext.getCmp('realBox3501_1').setVisible(false);
		}
		if(realQmin3501_1==0){
			Ext.getCmp('realQmin3501_1').setVisible(false);
		}
		if(realDis3501_1==0){
			Ext.getCmp('realDis3501_1').setVisible(false);
		}
		if(packingUnit3501_1==0){
			Ext.getCmp('packingUnit3501_1').setVisible(false);
		}
		if(packingSpec3501_1==0){
			Ext.getCmp('packingSpec3501_1').setVisible(false);
		}

		if(articleQty_2==0){
			Ext.getCmp('articleQty_2').setVisible(false);
		}
		if(planBox3501_2==0){
			Ext.getCmp('planBox3501_2').setVisible(false);
		}
		if(planQmin3501_2==0){
			Ext.getCmp('planQmin3501_2').setVisible(false);
		}
		if(planDis3501_2==0){
			Ext.getCmp('planDis3501_2').setVisible(false);
		}
		if(realQty_2==0){
			Ext.getCmp('realQty_2').setVisible(false);
		}
		if(realBox3501_2==0){
			Ext.getCmp('realBox3501_2').setVisible(false);
		}
		if(realQmin3501_2==0){
			Ext.getCmp('realQmin3501_2').setVisible(false);
		}
		if(realDis3501_2==0){
			Ext.getCmp('realDis3501_2').setVisible(false);
		}
		if(packingUnit3501_2==0){
			Ext.getCmp('packingUnit3501_2').setVisible(false);
		}
		if(packingSpec3501_2==0){
			Ext.getCmp('packingSpec3501_2').setVisible(false);
		}
		//end add
	},
	
	//TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabpanel3501').getActiveTab().id=='outstockAutoReceiptTab3501' )
		{
			disableButtonFunc(0,'#menu3501 [name=edit]','修改');
			Ext.getCmp('cmbOwnerNo3501').focus();
			Ext.getCmp('cmbOwnerNo3501').setValue(null);
			Ext.getCmp('cmbOutstockNo3501').setValue(null);
			Ext.getCmp('cmbOwnerNo3501').getStore().load();
		}else if(Ext.getCmp('tabpanel3501').getActiveTab().id=='outstockAutoCheckTab3501')
		{
			historyCommMenu4('menu3501');
			Ext.getCmp('cmbOwnerNoCheck3501').focus();
			Ext.getCmp('cmbOwnerNoCheck3501').setValue(null);
			Ext.getCmp('cmbOwnerNoCheck3501').getStore().load();
			Ext.getCmp('cmbWave_noCheck3501').setValue(null);
			Ext.getCmp('cmbWave_noCheck3501').getStore().load();
			Ext.getCmp('cmbBatch_noCheck3501').setValue(null);
			Ext.getCmp('cmbBatch_noCheck3501').getStore().load();
			Ext.getCmp('cmbOperate_typeCheck3501').setValue(null);
			Ext.getCmp('cmbOperate_typeCheck3501').getStore().load();
			Ext.getCmp('cmbOutstockNoCheck3501').setValue(null);
			Ext.getCmp('cmbOutstockNoCheck3501').getStore().load();
			Ext.getCmp('gridOutstockMCheck3501').getStore()
			.removeAll();
		}else if(Ext.getCmp('tabpanel3501').getActiveTab().id=='tabPId3501i'){
			bdef_RepairPrint = Ext.create('cms.view.common.bdef_RepairPrint');
			Ext.getCmp('tabPId3501i').add(bdef_RepairPrint);
			Ext.getCmp('tabPId3501i').doLayout();
			queryModuleId='3501';
			Ext.Ajax.request({
				url:'wms_PnfsetModuleReportQueryAction_getModuleReportQuery',
				method:'get',
				params:
				{
					strModuleId:queryModuleId
				},
				success:function(response)
				{
					var data=Ext.decode(response.responseText);
					for(var i=0;i<data.length;i++){
						var getQueryPanel = Ext.create('cms.view.common.reportQueryPanel');
						getQueryPanel.items.items[1].getStore().add({
							columnid:data[i].columnid,
							columnname:data[i].columnname
					    });
						getQueryPanel.items.items[1].setValue(data[i].columnid);
						if(data[i].xtype=='datefield'){
							getQueryPanel.remove(getQueryPanel.items.items[3]);
							getQueryPanel.add({
						        xtype : 'datefield',
								fieldLabel : '值',							
								format : 'Y-m-d',
						        labelWidth : 20,
						        width : 195
							});
						}
						getQueryPanel.items.items[3].setValue('');
						getQueryPanel.items.items[1].setReadOnly(true);
						bdef_RepairPrint.items.items[2].add(getQueryPanel);					
					}
				}
			});
		}
	},
	
	//拣货任务标签回单》货主选择
	cmbOwnerNo3501Select:function()
    {
		var listDetail   =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3501').getValue()))
		{
			//有混合业主的情况 如果为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNo3501').getValue() != "ALL"){
				var strDtl  =  {
				    columnId:'oom.owner_no',
					value:Ext.getCmp('cmbOwnerNo3501').getValue()
				};
				listDetail.push(strDtl);
			}
			var strJson  =  Ext.encode(listDetail);
			var strWheresql  =  {
				str : strJson
			};
			Ext.apply(Ext.getCmp('cmbWave_no3501')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbWave_no3501').getStore()
					.removeAll();
			Ext.getCmp('cmbWave_no3501').getStore()
					.load();
		}else
		{
			return;
		}
    },
    
    //历史单据查询》货主选择
    cmbOwnerNoCheck3501Select:function()
    {
		Ext.getCmp('cmbWave_noCheck3501').setValue(null);
		Ext.getCmp('cmbBatch_noCheck3501').setValue(null);
		Ext.getCmp('cmbBatch_noCheck3501').getStore().load();
		Ext.getCmp('cmbOperate_typeCheck3501').setValue(null);
		Ext.getCmp('cmbOperate_typeCheck3501').getStore().load();
		Ext.getCmp('cmbOutstockNoCheck3501').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3501').getStore().load();			
		loadgridOutstockMCheck3501();		
    },
	
    //修改
	editClick:function()
	{
		var record = Ext.getCmp('gridOutstockM3501').getSelectionModel().getSelection();
        if(record.length != 0)
        {
    		commonMenu4Button('menu3501','edit');
			g_strIisCanEdit3501=true;
			commonSetMsterReadOnlyByArray(
			new Array('cmbWorkerNo3501'),
			false);
			commonSetMsterReadOnlyByArray(
					new Array( 'cmbOwnerNo3501','cmbWave_no3501',
							'cmbBatch_no3501','cmbOperate_type3501','cmbOutstockNo3501'),
					true);
			Ext.getCmp('cmbWorkerNo3501').focus();
			Ext.getCmp('butReceipt3501').setDisabled(false);
			Ext.getCmp('outstockAutoCheckTab3501').setDisabled(true);
        }
		
	},
	
	//撤销
	undoClick:function()
	{
		commonMenu4Button('menu3501','undo');
		Ext.getCmp('butReceipt3501').setDisabled(true);
		Ext.getCmp('gridOutstockD3501').getStore().load();
		commonSetMsterReadOnlyByArray(
		new Array('cmbWorkerNo3501'),
		true);
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNo3501','cmbWave_no3501',
						'cmbBatch_no3501','cmbOperate_type3501','cmbOutstockNo3501'),
				false);
		g_strIisCanEdit3501=false;
		Ext.getCmp('outstockAutoCheckTab3501').setDisabled(false);
	},
	
	//查找
	queryClick:function()
	{
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=3501;
		if(Ext.getCmp('tabpanel3501').getActiveTab().id=='outstockAutoReceiptTab3501' )
		{
			queryGrid='gridOutstockM3501';	
		}else if(Ext.getCmp('tabpanel3501').getActiveTab().id=='outstockAutoCheckTab3501')
		{
			queryGrid='gridOutstockMCheck3501';
		}
		
	},
	
	//使网格不可编辑
	cmbOwnerNo3501beforeselect:function(){
	    if(g_strIisCanEdit3501)
	    {
	        return  false;  
	    }
	},
	
	//拣货任务标签回单》波次选择
	cmbWave_no3501change:function(){
       var listDetail1 = [];
       listDetail1 = Ext.decode(Ext.getCmp('cmbWave_no3501').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbWave_no3501').getValue()))
       {
    	   var d={
    				columnId:'ood.wave_no',
    				value:Ext.getCmp('cmbWave_no3501').getValue()
    			};
    	   listDetail1.push(d);
    			var jsonStr = Ext.encode(listDetail1);
    			var strwheresql = {
    				str : jsonStr
    			};
    			Ext.apply(Ext.getCmp('cmbBatch_no3501')
    					.getStore().proxy.extraParams,
    					strwheresql);
    			Ext.getCmp('cmbBatch_no3501').getStore()
    					.removeAll();
    			Ext.getCmp('cmbBatch_no3501').getStore()
    					.load();
    			//loadgridOutstockM3501();
       }else
	   {
	   		return;
	   }
		
	},
	cmbWave_noCheck3501Beforequery:function(){
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3501').getValue()))
		{
			var strDtl  =  {
			    columnId:'ood.owner_no',
				value:Ext.getCmp('cmbOwnerNoCheck3501').getValue()
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('cmbWave_noCheck3501').getValue()))
		{
			var strDtl  =  {
			    columnId:'ood.Wave_no',
			    condition:'7',
				value:Ext.getCmp('cmbWave_noCheck3501').getValue()
			};
			listDetail.push(strDtl);
		}		
		var strJson  =  Ext.encode(listDetail);
		var strWheresql  =  {
			str : strJson
		};
		Ext.apply(Ext.getCmp('cmbWave_noCheck3501')
				.getStore().proxy.extraParams,
				strWheresql);
	},
	//历史单据查询》波次选择
	cmbWave_noCheck3501change:function(){
		Ext.getCmp('cmbBatch_noCheck3501').setValue(null);
		Ext.getCmp('cmbOperate_typeCheck3501').setValue(null);
		Ext.getCmp('cmbOperate_typeCheck3501').getStore().load();
		Ext.getCmp('cmbOutstockNoCheck3501').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3501').getStore().load();
       var listDetail1 = [];
     
       if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3501').getValue()))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNoCheck3501').getValue()
			};
			listDetail1.push(strdtl);
		}
	   var d={
				columnId:'ood.wave_no',
				value:Ext.getCmp('cmbWave_noCheck3501').getValue()
			};
	   listDetail1.push(d);
		var jsonStr = Ext.encode(listDetail1);
		var strwheresql = {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('cmbBatch_noCheck3501')
				.getStore().proxy.extraParams,
				strwheresql);
		Ext.getCmp('cmbBatch_noCheck3501').getStore()
				.removeAll();
		Ext.getCmp('cmbBatch_noCheck3501').getStore()
				.load();
    	loadgridOutstockMCheck3501();		
	},
	
	//拣货任务标签回单》批次选择
	cmbBatch_no3501change:function(th,newValue,oldValue,eOpts)
	{
		 if(!Ext.isEmpty(Ext.getCmp('cmbBatch_no3501').getValue()))
	     {
		 var listDetail1 = [];
		 listDetail1 = Ext.decode(Ext.getCmp('cmbBatch_no3501').getStore().getProxy().extraParams.str);
			var strdtl={
				columnId:'ood.batch_no',
				value:Ext.getCmp('cmbBatch_no3501').getValue()
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('cmbOperate_type3501')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cmbOperate_type3501').getStore()
					.removeAll();
			Ext.getCmp('cmbOperate_type3501').getStore()
					.load();
	   }else
	   {
	   		return;
	   }
	},
	
	//拣货任务标签回单》批次选择
	cmbBatch_noCheck3501change:function(th,newValue,oldValue,eOpts)
	{
		Ext.getCmp('cmbOperate_typeCheck3501').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3501').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3501').getStore().load();
		
		 var listDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3501').getValue()))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNoCheck3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbWave_noCheck3501').getValue()))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:Ext.getCmp('cmbWave_noCheck3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		 var strdtl={
			columnId:'ood.batch_no',
			value:Ext.getCmp('cmbBatch_noCheck3501').getValue()
		};
		listDetail1.push(strdtl);
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('cmbOperate_typeCheck3501')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('cmbOperate_typeCheck3501').getStore()
				.removeAll();
		Ext.getCmp('cmbOperate_typeCheck3501').getStore()
				.load();
		 loadgridOutstockMCheck3501();
	},
	
	//拣货任务标签回单》作业类型选择
	cmbOperate_type3501change:function()
	{
       var listDetail1 = [];
       listDetail1 = Ext.decode(Ext.getCmp('cmbOperate_type3501').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbOperate_type3501').getValue()))
       {
    	   var d={
    				columnId:'oom.operate_type',
    				value:Ext.getCmp('cmbOperate_type3501').getValue()
    			};
    	   listDetail1.push(d);
    			var jsonStr = Ext.encode(listDetail1);
    			var strwheresql = {
    				str : jsonStr 
    			};
    			Ext.apply(Ext.getCmp('cmbOutstockNo3501')
    					.getStore().proxy.extraParams,
    					strwheresql);
    			Ext.getCmp('cmbOutstockNo3501').getStore()
    					.removeAll();
    			Ext.getCmp('cmbOutstockNo3501').getStore()
    					.load();
    			loadgridOutstockM3501();
       }else
	   {
	   		return;
	   }
	},
	
	//历史单据查询》作业类型选择
	cmbOperate_typeCheck3501change:function()
	{
	   Ext.getCmp('cmbOutstockNoCheck3501').setValue(null);
       var listDetail1 = [];
       //listDetail1 = Ext.decode(Ext.getCmp('cmbOperate_typeCheck3501').getStore().getProxy().extraParams.str);
       /*if(!Ext.isEmpty(Ext.getCmp('cmbOperate_typeCheck3501').getValue()))
       {*/
       if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3501').getValue()))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNoCheck3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbWave_noCheck3501').getValue()))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:Ext.getCmp('cmbWave_noCheck3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		   	
	   	if(!Ext.isEmpty(Ext.getCmp('cmbBatch_noCheck3501').getValue()))
	   	{
	   		var strdtl={
	   		columnId:'ood.batch_no',
	   		value:Ext.getCmp('cmbBatch_noCheck3501').getValue()
	   		};
	   		listDetail1.push(strdtl);
	   	}
	   var d={
				columnId:'oom.operate_type',
				value:Ext.getCmp('cmbOperate_typeCheck3501').getValue()
			};
	   listDetail1.push(d);
		var jsonStr = Ext.encode(listDetail1);
		var strwheresql = {
			str : jsonStr 
		};
		Ext.apply(Ext.getCmp('cmbOutstockNoCheck3501')
				.getStore().proxy.extraParams,
				strwheresql);
		Ext.getCmp('cmbOutstockNoCheck3501').getStore()
				.removeAll();
		Ext.getCmp('cmbOutstockNoCheck3501').getStore()
				.load();
		loadgridOutstockMCheck3501();
	},
	
	//拣货任务标签回单》下架单号选中
	cmbOutstockNo3501change:function()
	{
		loadgridOutstockM3501();
	},
	
	//历史单据查询》下架单号选中
	cmbOutstockNoCheck3501change:function()
	{
		loadgridOutstockMCheck3501();
	},
	
	//拣货任务标签回单》单据选择前事件
	gridOutstockM3501Beforeselect:function()
	{
	    if(g_strIisCanEdit3501)
	    {
	        return  false;  
	    }
	},
	
	//拣货任务标签回单》单据选择
	gridOutstockM3501selectionchange:function()
	{
		var objRecord = Ext.getCmp('gridOutstockM3501').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockD3501').getStore().removeAll();
        }else{
        	if(objRecord[0].get('outstockType')=='1')
        	{
        		Ext.getCmp('gridOutstockD3501').columns[3].setVisible(true);
        	}else
        	{
        		Ext.getCmp('gridOutstockD3501').columns[3].setVisible(false);
        	}
            var listDetail1 = [];
			var strdtl={
				columnId:'m.source_no',
				value:objRecord[0].get("outstockNo")
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('gridOutstockD3501')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockD3501').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockD3501').getStore()
					.load();
        }
	},
	
	//历史单据查询》单据选择
	gridOutstockMCheck3501selectionchange:function()
	{
		var objRecord = Ext.getCmp('gridOutstockMCheck3501').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockDCheck3501').getStore().removeAll();
        }else{
            var listDetail1 = [];
			var strdtl={
				columnId:'ood.outstock_no',
				value:objRecord[0].get("outstockNo")
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('gridOutstockDCheck3501')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDCheck3501').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDCheck3501').getStore()
					.load();
        }
	},
	
	//编辑前事件
	gridOutstockD3501beforeedit:function(editor,e,eOpts)
	{
		 if(!g_strIisCanEdit3501)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
			var record = Ext.getCmp('gridOutstockM3501').getSelectionModel().getSelection();
	        if((record[0].data.operateType=='P' || record[0].data.operateType=='M') 
	        	&&(e.field=='realBox' || e.field=='realQty'))
	        {
		        editor.cancel = true;
		        return  false; 
	        }
	    }
	},
	
	//网格编辑事件
	gridOutstockD3501edit:function(editor,e,eOpts)
	{
		/**
		 * odata_outstock_m OPERATE_TYPE
		 * 1、P和M型拣货不允许差异回单
		 * 2、C型和B型拣货可差异回单。实际数量不可超过计划数量，但可少于计划数量
		 */
		var objRecord = Ext.getCmp('gridOutstockM3501').getSelectionModel().getSelection();
		if(objRecord[0].get('outstockType')=='1' || objRecord[0].get('operateType')=='P' || objRecord[0].get('operateType')=='M')
    	{
			if(e.record.data.realQty != e.record.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityIsdifferentPlan);
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='realBox'){
    				editor.context.record.set('realQty',
    						editor.context.originalValue*e.record.data.packingQty
    						+e.record.data.realQmin*e.record.data.qminOperatePacking
    						+e.record.data.realDis);
    			}else if(e.field=='realQmin'){
    				editor.context.record.set('realQty',
    						e.record.data.realBox*e.record.data.packingQty
    						+editor.context.originalValue*e.record.data.qminOperatePacking
    						+e.record.data.realDis);
    			}else if(e.field=='realDis'){
    				editor.context.record.set('realQty',
    						e.record.data.realBox*e.record.data.packingQty
    						+e.record.data.realQmin.originalValue*e.record.data.qminOperatePacking
    						+editor.context.originalValue);
    			}
			}
    	}else
    	{
    		/*var a = e.record.data.realDis%e.record.data.qminOP;
    		if(a!=0){//散数不是最新操作包装的整数倍
    			Ext.example.msg($i18n.prompt,'输入的散数有误，请重新输入!');
    			editor.context.record.set(e.field,editor.context.originalValue);
    			if(e.field=='realBox'){
    				editor.context.record.set('realQty',editor.context.originalValue*e.record.data.packingQty);
    			}
    		};*/
    		if(e.record.data.realQty>e.record.data.articleQty)
    		{
    			Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityCannotMoreThanPlan);
    			editor.context.record.set(e.field,editor.context.originalValue);
    			if(e.field=='realBox'){
    				editor.context.record.set('realQty',
    						editor.context.originalValue*e.record.data.packingQty
    						+e.record.data.realQmin*e.record.data.qminOperatePacking
    						+e.record.data.realDis);
    			}else if(e.field=='realQmin'){
    				editor.context.record.set('realQty',
    						e.record.data.realBox*e.record.data.packingQty
    						+editor.context.originalValue*e.record.data.qminOperatePacking
    						+e.record.data.realDis);
    			}else if(e.field=='realDis'){
    				editor.context.record.set('realQty',
    						e.record.data.realBox*e.record.data.packingQty
    						+e.record.data.realQmin.originalValue*e.record.data.qminOperatePacking
    						+editor.context.originalValue);
    			}
    		}
    	}
		
	},
	//表单回单
	butReceipt3501click:function()
	{
		var outstock_name = Ext.getCmp('cmbWorkerNo3501').getValue();
		var objOutstockM = Ext.getCmp('gridOutstockM3501').getSelectionModel().getSelection();
		var gridcount=Ext.getCmp('gridOutstockM3501').getStore().getCount();
		if(Ext.isEmpty(outstock_name))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.realOutstockWork);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		var intgridcount=Ext.getCmp("gridOutstockD3501").getStore().getCount();
		if(intgridcount>0)
		{
			if(!commonCheckdetailgrid('gridOutstockD3501',0,intgridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureToReceipt,
			function(button, text) {
			if (button == 'yes') {
				var msgShow = commonMegShow($i18n_prompt.receiptting);
				var listDetail1 = [];
				for(var i=0;i<intgridcount;i++ ){
					var objRecord  = Ext.getCmp('gridOutstockD3501').getStore().getAt(i);
					var strDtl={		
							outstockNo:objOutstockM[0].get('outstockNo'),
							warehouseNo:objRecord.get('warehouseNo'),
							//用下架明细的owner_no huangb 20160803
							//ownerNo:objOutstockM[0].get('ownerNo'),
							ownerNo:objRecord.get('ownerNo'),
							articleNo:objRecord.get('articleNo'),
							packingQty:objRecord.get('packingQty'),
							SCellNo:objRecord.get('SCellNo'),
							custContainerNo:objRecord.get('labelNo'),
							articleQty:objRecord.get('articleQty'),
							realQty:objRecord.get('realQty'),
							quality : objRecord.get('quality'),
							produceDate : objRecord.get('produceDate'),
							expireDate : objRecord.get('expireDate'),
							lotNo : objRecord.get('lotNo'),
							rsv_batch1 : objRecord.get('rsv_batch1'),
							rsv_batch2 : objRecord.get('rsv_batch2'),
						    rsv_batch3 : objRecord.get('rsv_batch3'),
							rsv_batch4 : objRecord.get('rsv_batch4'),
							rsv_batch5 : objRecord.get('rsv_batch5'),
							rsv_batch6 : objRecord.get('rsv_batch6'),
							rsv_batch7 : objRecord.get('rsv_batch7'),
							rsv_batch8 : objRecord.get('rsv_batch8'),
							labelNo:objRecord.get('labelNo')
					};
					listDetail1.push(strDtl);
				}
						
				var jsonStr = Ext.encode(listDetail1);		
				var params = {
				    strPickType:objOutstockM[0].data.pickType,
					strOutstockNo:outstock_name,
					str:jsonStr
				};
				Ext.Ajax.request({
					method:'POST',
					url:'odata_OutstockMReceiptAction_saveLabel.action',
					async:false,
					params:params,
					success:function(response){
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							g_Refresh = true;
							
//							Ext.getCmp('cmbOwnerNo3501').setValue(null);
//							Ext.getCmp('cmbOwnerNo3501').getStore().load();
							g_strIisCanEdit3501=false;
							commonMenu4Button('menu3501','save');
							commonSetMsterReadOnlyByArray(
									new Array('cmbWorkerNo3501'),
									true);
							commonSetMsterReadOnlyByArray(
									new Array( 'cmbOwnerNo3501','cmbWave_no3501',
											'cmbBatch_no3501','cmbOperate_type3501','cmbOutstockNo3501'),
									false);
							Ext.getCmp('cmbOwnerNo3501').focus();
							Ext.getCmp('butReceipt3501').setDisabled(true);
							Ext.getCmp('outstockAutoCheckTab3501').setDisabled(false);
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
	
							//Ext.example.msg($i18n.prompt,data.msg+data.obj);
						}				
					}
	//			,
	//				failure:function(response){
	//					msgShow.hide();
	//					Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
	//				}
				});
				
				if(g_Refresh == true){
					g_Refresh = false;
					//只有当前波次,批次 ,作业类型下的所有下架单据都回单了 才整个的全部刷新 huangb 20160801
					if(gridcount <= 1){
						Ext.getCmp('cmbOwnerNo3501').setValue(null);
						Ext.getCmp('cmbOwnerNo3501').getStore().load();
					}else{
						Ext.getCmp('gridOutstockM3501').getStore().removeAll();
						Ext.getCmp('gridOutstockM3501').getStore().load();
					}
				}
			}
		});
	},
	//拣货任务标签回单》关闭前事件
	odata_OutstockLabelReceiptUIBeforeclose:function(){
		g_strIisCanEdit3501=false;
		Ext.getCmp('gridOutstockD3501').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3501').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3501').setValue(null);
	}
});

//表单回单》加载单据参数传递
function loadgridOutstockM3501()
{
	var ownerNo3501 = Ext.getCmp('cmbOwnerNo3501').getValue();
	var wave_no3501 = Ext.getCmp('cmbWave_no3501').getValue();
	var batch_no3501 = Ext.getCmp('cmbBatch_no3501').getValue();
	var operate_type3501 = Ext.getCmp('cmbOperate_type3501').getValue();
	var listDetail1 = [];
	if(!Ext.isEmpty(ownerNo3501)
	   && !Ext.isEmpty(wave_no3501)
	   && !Ext.isEmpty(batch_no3501)
	   && !Ext.isEmpty(operate_type3501))
	{
		if(!Ext.isEmpty(ownerNo3501))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNo3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(wave_no3501))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:Ext.getCmp('cmbWave_no3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(batch_no3501))
		{
			var strdtl={
			columnId:'ood.batch_no',
			value:Ext.getCmp('cmbBatch_no3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(operate_type3501))
		{
			var strdtl={
			columnId:'oom.operate_type',
			value:Ext.getCmp('cmbOperate_type3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbOutstockNo3501').getValue()))
		{
			var strdtl={
			columnId:'oom.outstock_no',
			value:Ext.getCmp('cmbOutstockNo3501').getValue()
			};
			listDetail1.push(strdtl);
		}
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			strQuery : jsonStr
		};
		Ext.apply(Ext.getCmp('gridOutstockM3501')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('gridOutstockM3501').getStore()
				.removeAll();
		Ext.getCmp('gridOutstockM3501').getStore()
				.load();
	}else
	{
		return;
	}
}

//历史单据查询》加载单据参数传递
function loadgridOutstockMCheck3501()
{
	var ownerNoCheck3501 = Ext.getCmp('cmbOwnerNoCheck3501').getValue();
	var wave_noCheck3501 = Ext.getCmp('cmbWave_noCheck3501').getValue();
	var batch_noCheck3501 = Ext.getCmp('cmbBatch_noCheck3501').getValue();
	var operate_typeCheck3501 = Ext.getCmp('cmbOperate_typeCheck3501').getValue();
	var listDetail1 = [];
	if(!Ext.isEmpty(ownerNoCheck3501))
	{
		var strdtl={
		columnId:'oom.owner_no',
		value:ownerNoCheck3501
		};
		listDetail1.push(strdtl);
	}
	
	if(!Ext.isEmpty(wave_noCheck3501))
	{
		var strdtl={
		columnId:'ood.wave_no',
		value:wave_noCheck3501
		};
		listDetail1.push(strdtl);
	}
	
	if(!Ext.isEmpty(batch_noCheck3501))
	{
		var strdtl={
		columnId:'ood.batch_no',
		value:batch_noCheck3501
		};
		listDetail1.push(strdtl);
	}
	
	if(!Ext.isEmpty(operate_typeCheck3501))
	{
		var strdtl={
		columnId:'oom.operate_type',
		value:operate_typeCheck3501
		};
		listDetail1.push(strdtl);
	}
	
	if(!Ext.isEmpty(Ext.getCmp('cmbOutstockNoCheck3501').getValue()))
	{
		var strdtl={
		columnId:'oom.outstock_no',
		value:Ext.getCmp('cmbOutstockNoCheck3501').getValue()
		};
		listDetail1.push(strdtl);
	}
	var jsonStr = Ext.encode(listDetail1);
	var wheresql = {
		strQuery : jsonStr
	};
	Ext.apply(Ext.getCmp('gridOutstockMCheck3501')
			.getStore().proxy.extraParams,
			wheresql);
	Ext.getCmp('gridOutstockMCheck3501').getStore()
			.removeAll();
	Ext.getCmp('gridOutstockMCheck3501').getStore()
			.load();
}