/**
 * 模块名称：拣货表单回单
 * 模块编码：3601
 * 创建：周欢
 */
var g_strIisCanEdit3601=false;
Ext.define('cms.controller.odata.odata_OutstockFormReceiptController',{
	extend:'Ext.app.Controller',
	controllerId:'odata_OutstockFormReceiptController',
	requires:[
          'cms.view.odata.odata_OutstockFormReceiptUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//拣货表单回单》货主选择
			'odata_OutstockFormReceiptUI bdef_DefOwnerCombo[id = cmbOwnerNo3601]':{
				beforeselect:this.cmbOwnerNo3601beforeselect,
				select:this.cmbOwnerNo3601Select
			},
			//历史单据查询》货主选择
			'odata_OutstockFormReceiptUI bdef_DefOwnerCombo[id = cmbOwnerNoCheck3601]':{
				select:this.cmbOwnerNoCheck3601Select
			},
			//拣货表单回单》修改
			'odata_OutstockFormReceiptUI button[name=edit]':{
				click:this.editClick
			},
			//拣货表单回单》撤销
			'odata_OutstockFormReceiptUI button[name=undo]':{
				click:this.undoClick
			},
			//拣货表单回单》查找
			'odata_OutstockFormReceiptUI button[name=query]':{
				click:this.queryClick
			},
			//拣货表单回单》刷新
			'odata_OutstockFormReceiptUI button[name=refresh]':{
				click:this.TabpanelChange
			},
			//历史单据查询》波次选择
			'odata_OutstockFormReceiptUI combo[id=cmbWave_noCheck3601]':{
				select:this.cmbWave_noCheck3601change
			},
			//拣货表单回单》波次选择
			'odata_OutstockFormReceiptUI combo[id=cmbWave_no3601]':{
				select:this.cmbWave_no3601change
			},
			//拣货表单回单》批次选择
			'odata_OutstockFormReceiptUI combo[id=cmbBatch_no3601]':{
				select:this.cmbBatch_no3601change
			},
			//历史单据查询》批次选择
			'odata_OutstockFormReceiptUI combo[id=cmbBatch_noCheck3601]':{
				select:this.cmbBatch_noCheck3601change
			},
			//拣货表单回单》作业类型选择
			'odata_OutstockFormReceiptUI combo[id=cmbOperate_type3601]':{
				select:this.cmbOperate_type3601change
			},
			//历史单据查询》作业类型选择
			'odata_OutstockFormReceiptUI combo[id=cmbOperate_typeCheck3601]':{
				change:this.cmbOperate_typeCheck3601change
			},
			//拣货表单回单》下架单号选中
			'odata_OutstockFormReceiptUI combo[id=cmbOutstockNo3601]':{
				select:this.cmbOutstockNo3601change
			},
			//历史单据查询》下架单号选中
			'odata_OutstockFormReceiptUI combo[id=cmbOutstockNoCheck3601]':{
				select:this.cmbOutstockNoCheck3601change
			},
			//拣货表单回单》单据选择
			'odata_OutstockFormReceiptUI grid[id=gridOutstockM3601]':{
				beforeselect:this.gridOutstockM3601Beforeselect,
				selectionchange:this.gridOutstockM3601selectionchange
			},
			//拣货表单回单》单据选择
			'odata_OutstockFormReceiptUI grid[id=gridOutstockMCheck3601]':{
				selectionchange:this.ggridOutstockMCheck3601selectionchange
			},
			//拣货表单回单》商品数量较验等
			'odata_OutstockFormReceiptUI grid[id=gridOutstockD3601]':{
				beforeedit:this.gridOutstockD3601beforeedit,
				edit:this.gridOutstockD3601edit
			},
			//拣货表单回单》回单
			'odata_OutstockFormReceiptUI button[id=butReceipt3601]':{
				click:this.butReceipt3601click
			},
			//拣货表单回单》关闭前事件
			'odata_OutstockFormReceiptUI':{
				beforeclose:this.odata_OutstockFormReceiptUIBeforeclose
			},
			//TAB页切换
			'odata_OutstockFormReceiptUI tabpanel[id=tabpanel3601]':{
				tabchange:this.TabpanelChange
			}
		});	
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		g_strIisCanEdit3601=false;
		Ext.getCmp('cmbOwnerNo3601').setValue(null);
		Ext.getCmp('cmbOwnerNo3601').getStore().load();
	},
	
	//刷新
	
	//TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabpanel3601').getActiveTab().id=='outstockFormReceiptTab3601' )
		{
			commonMenu4Button('menu3601','save');
			Ext.getCmp('cmbOwnerNo3601').focus();
			Ext.getCmp('cmbOwnerNo3601').setValue(null);
			Ext.getCmp('cmbOutstockNo3601').setValue(null);
			Ext.getCmp('cmbOwnerNo3601').getStore().load();
		}else if(Ext.getCmp('tabpanel3601').getActiveTab().id=='outstockFormCheckTab3601')
		{
			historyCommMenu4('menu3601');
			Ext.getCmp('cmbOwnerNoCheck3601').focus();
			Ext.getCmp('cmbOwnerNoCheck3601').setValue(null);
			Ext.getCmp('cmbOwnerNoCheck3601').getStore().load();
			Ext.getCmp('cmbWave_noCheck3601').setValue(null);
			Ext.getCmp('cmbWave_noCheck3601').getStore().load();
			Ext.getCmp('cmbBatch_noCheck3601').setValue(null);
			Ext.getCmp('cmbBatch_noCheck3601').getStore().load();
			Ext.getCmp('cmbOperate_typeCheck3601').setValue(null);
			Ext.getCmp('cmbOperate_typeCheck3601').getStore().load();
			Ext.getCmp('cmbOutstockNoCheck3601').setValue(null);
			Ext.getCmp('cmbOutstockNoCheck3601').getStore().load();
			Ext.getCmp('gridOutstockMCheck3601').getStore()
			.removeAll();
		}
		
	},
	
	//拣货表单回单》货主选择
	cmbOwnerNo3601Select:function()
    {
		var listDetail   =  [];
		Ext.getCmp('cmbOutstockNo3601').setValue(null);
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3601').getValue()))
		{
			var strDtl  =  {
				    columnId:'oom.owner_no',
					value:Ext.getCmp('cmbOwnerNo3601').getValue()
				};
				listDetail.push(strDtl);
				var strJson  =  Ext.encode(listDetail);
				var strWheresql  =  {
					str : strJson
				};
				Ext.apply(Ext.getCmp('cmbWave_no3601')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbWave_no3601').getStore()
						.removeAll();
				Ext.getCmp('cmbWave_no3601').getStore()
						.load();
		}else
		{
			return;
		}
    },
	
  //历史单据查询》货主选择
    cmbOwnerNoCheck3601Select:function()
    {
		Ext.getCmp('cmbWave_noCheck3601').setValue(null);
		Ext.getCmp('cmbBatch_noCheck3601').setValue(null);
		Ext.getCmp('cmbBatch_noCheck3601').getStore().load();
		Ext.getCmp('cmbOperate_typeCheck3601').setValue(null);
		Ext.getCmp('cmbOperate_typeCheck3601').getStore().load();
		Ext.getCmp('cmbOutstockNoCheck3601').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3601').getStore().load();
		var listDetail   =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3601').getValue()))
		{
			var strDtl  =  {
				    columnId:'oom.owner_no',
					value:Ext.getCmp('cmbOwnerNoCheck3601').getValue()
				};
				listDetail.push(strDtl);
		}
		var strJson  =  Ext.encode(listDetail);
		var strWheresql  =  {
			str : strJson
		};
		Ext.apply(Ext.getCmp('cmbWave_noCheck3601')
				.getStore().proxy.extraParams,
				strWheresql);
		Ext.getCmp('cmbWave_noCheck3601').getStore()
				.removeAll();
		Ext.getCmp('cmbWave_noCheck3601').getStore()
				.load();
		loadgridOutstockMCheck3601();
    },
    
    //修改
	editClick:function()
	{
		var record = Ext.getCmp('gridOutstockM3601').getSelectionModel().getSelection();
        if(record.length != 0)
        {
    		commonMenu4Button('menu3601','edit');
			g_strIisCanEdit3601=true;
			commonSetMsterReadOnlyByArray(
			new Array('cmbWorkerNo3601'),
			false);
			commonSetMsterReadOnlyByArray(
					new Array( 'cmbOwnerNo3601','cmbWave_no3601',
							'cmbBatch_no3601','cmbOperate_type3601','cmbOutstockNo3601'),
					true);
			Ext.getCmp('cmbWorkerNo3601').focus();
			Ext.getCmp('butReceipt3601').setDisabled(false);
			Ext.getCmp('outstockFormCheckTab3601').setDisabled(true);
        }
		
	},
	
	//撤销
	undoClick:function()
	{
		commonMenu4Button('menu3601','undo');
		Ext.getCmp('butReceipt3601').setDisabled(true);
		Ext.getCmp('gridOutstockD3601').getStore().load();
		commonSetMsterReadOnlyByArray(
		new Array('cmbWorkerNo3601'),
		true);
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNo3601','cmbWave_no3601',
						'cmbBatch_no3601','cmbOperate_type3601','cmbOutstockNo3601'),
				false);
		g_strIisCanEdit3601=false;
		Ext.getCmp('outstockFormCheckTab3601').setDisabled(false);
	},
	
	//查找
	queryClick:function()
	{
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=3601;
		if(Ext.getCmp('tabpanel3601').getActiveTab().id=='outstockFormReceiptTab3601' )
		{
			queryGrid='gridOutstockM3601';	
		}else if(Ext.getCmp('tabpanel3601').getActiveTab().id=='outstockFormCheckTab3601')
		{
			queryGrid='gridOutstockMCheck3601';
		}
	},
	
	//刷新
	refreshClick:function(){
		Ext.getCmp('cmbOwnerNo3601').setValue(null);
		Ext.getCmp('cmbWave_no3601').setValue(null);
		Ext.getCmp('cmbBatch_no3601').setValue(null);
		Ext.getCmp('cmbOperate_type3601').setValue(null);
		Ext.getCmp('cmbOwnerNo3601').getStore().reload();
	},
	
	//使网格不可编辑
	cmbOwnerNo3601beforeselect:function(){
	    if(g_strIisCanEdit3601)
	    {
	        return  false;  
	    }
	},
	
	//拣货表单回单》波次选择
	cmbWave_no3601change:function(){
	   Ext.getCmp('cmbOutstockNo3601').setValue(null);
       var listDetail1 = [];
       listDetail1 = Ext.decode(Ext.getCmp('cmbWave_no3601').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbWave_no3601').getValue()))
       {
    	   var d={
    				columnId:'ood.wave_no',
    				value:Ext.getCmp('cmbWave_no3601').getValue()
    			};
    	   listDetail1.push(d);
    			var jsonStr = Ext.encode(listDetail1);
    			var strwheresql = {
    				str : jsonStr
    			};
    			Ext.apply(Ext.getCmp('cmbBatch_no3601')
    					.getStore().proxy.extraParams,
    					strwheresql);
    			Ext.getCmp('cmbBatch_no3601').getStore()
    					.removeAll();
    			Ext.getCmp('cmbBatch_no3601').getStore()
    					.load();
       }else
	   {
			return;
	   }
		
	},
	
	//历史单据查询》波次选择
	cmbWave_noCheck3601change:function(){
		Ext.getCmp('cmbBatch_noCheck3601').setValue(null);
		Ext.getCmp('cmbOperate_typeCheck3601').setValue(null);
		Ext.getCmp('cmbOperate_typeCheck3601').getStore().load();
		Ext.getCmp('cmbOutstockNoCheck3601').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3601').getStore().load();
       var listDetail1 = [];
	       if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3601').getValue()))
			{
				var strdtl={
				columnId:'oom.owner_no',
				value:Ext.getCmp('cmbOwnerNoCheck3601').getValue()
				};
				listDetail1.push(strdtl);
			}
    	   var strdtl={
    				columnId:'ood.wave_no',
    				value:Ext.getCmp('cmbWave_noCheck3601').getValue()
    			};
    	   listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var strwheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('cmbBatch_noCheck3601')
					.getStore().proxy.extraParams,
					strwheresql);
			Ext.getCmp('cmbBatch_noCheck3601').getStore()
					.removeAll();
			Ext.getCmp('cmbBatch_noCheck3601').getStore()
					.load();
			loadgridOutstockMCheck3601();	
	},
	
	//拣货表单回单》批次选择
	cmbBatch_no3601change:function(th,newValue,oldValue,eOpts)
	{
		 Ext.getCmp('cmbOutstockNo3601').setValue(null);
		 var listDetail1 = [];
		 listDetail1 = Ext.decode(Ext.getCmp('cmbBatch_no3601').getStore().getProxy().extraParams.str);
		 if(!Ext.isEmpty(Ext.getCmp('cmbBatch_no3601').getValue()))
	     {
			
			var strdtl={
				columnId:'ood.batch_no',
				value:Ext.getCmp('cmbBatch_no3601').getValue()
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('cmbOperate_type3601')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cmbOperate_type3601').getStore()
					.removeAll();
			Ext.getCmp('cmbOperate_type3601').getStore()
					.load();
		}else
	    {
			return;
	    }
	},
	
	//历史单据查询》批次选择
	cmbBatch_noCheck3601change:function(th,newValue,oldValue,eOpts)
	{
		Ext.getCmp('cmbOperate_typeCheck3601').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3601').setValue(null);
		Ext.getCmp('cmbOutstockNoCheck3601').getStore().load();
		 var listDetail1 = [];
		    if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3601').getValue()))
			{
				var strdtl={
				columnId:'oom.owner_no',
				value:Ext.getCmp('cmbOwnerNoCheck3601').getValue()
				};
				listDetail1.push(strdtl);
			}
			
			if(!Ext.isEmpty(Ext.getCmp('cmbWave_noCheck3601').getValue()))
			{
				var strdtl={
				columnId:'ood.wave_no',
				value:Ext.getCmp('cmbWave_noCheck3601').getValue()
				};
				listDetail1.push(strdtl);
			}
			var strdtl={
				columnId:'ood.batch_no',
				value:Ext.getCmp('cmbBatch_noCheck3601').getValue()
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('cmbOperate_typeCheck3601')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cmbOperate_typeCheck3601').getStore()
					.removeAll();
			Ext.getCmp('cmbOperate_typeCheck3601').getStore()
					.load();
		loadgridOutstockMCheck3601();
	},
	
	//拣货表单回单》作业类型选择
	cmbOperate_type3601change:function()
	{
		Ext.getCmp('cmbOutstockNo3601').setValue(null);
       var listDetail1 = [];
       listDetail1 = Ext.decode(Ext.getCmp('cmbOperate_type3601').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbOperate_type3601').getValue()))
       {
    	   var d={
    				columnId:'oom.operate_type',
    				value:Ext.getCmp('cmbOperate_type3601').getValue()
    			};
    	   listDetail1.push(d);
    			var jsonStr = Ext.encode(listDetail1);
    			var strwheresql = {
    				str : jsonStr 
    			};
    			Ext.apply(Ext.getCmp('cmbOutstockNo3601')
    					.getStore().proxy.extraParams,
    					strwheresql);
    			Ext.getCmp('cmbOutstockNo3601').getStore()
    					.removeAll();
    			Ext.getCmp('cmbOutstockNo3601').getStore()
    					.load();
    			loadgridOutstockM3601();
       }else
	   {
			return;
	   }
		
	},
	
	//历史单据查询》作业类型选择
	cmbOperate_typeCheck3601change:function()
	{
	   Ext.getCmp('cmbOutstockNoCheck3601').setValue(null);
       var listDetail1 = [];
       if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoCheck3601').getValue()))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNoCheck3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbWave_noCheck3601').getValue()))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:Ext.getCmp('cmbWave_noCheck3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		   	
	   	if(!Ext.isEmpty(Ext.getCmp('cmbBatch_noCheck3601').getValue()))
	   	{
	   		var strdtl={
	   		columnId:'ood.batch_no',
	   		value:Ext.getCmp('cmbBatch_noCheck3601').getValue()
	   		};
	   		listDetail1.push(strdtl);
	   	}
	   var d={
				columnId:'oom.operate_type',
				value:Ext.getCmp('cmbOperate_typeCheck3601').getValue()
			};
	   listDetail1.push(d);
			var jsonStr = Ext.encode(listDetail1);
			var strwheresql = {
				str : jsonStr 
			};
			Ext.apply(Ext.getCmp('cmbOutstockNoCheck3601')
					.getStore().proxy.extraParams,
					strwheresql);
			Ext.getCmp('cmbOutstockNoCheck3601').getStore()
					.removeAll();
			Ext.getCmp('cmbOutstockNoCheck3601').getStore()
					.load();
			loadgridOutstockMCheck3601();
	},
	
	//拣货表单回单》下架单号选中
	cmbOutstockNo3601change:function()
	{
		loadgridOutstockM3601();
	},
	
	//拣货表单回单》下架单号选中
	cmbOutstockNoCheck3601change:function()
	{
		loadgridOutstockMCheck3601();
	},
	
	//拣货表单回单》单据选择前事件
	gridOutstockM3601Beforeselect:function()
	{
	    if(g_strIisCanEdit3601)
	    {
	        return  false;  
	    }
	},
	
	//拣货表单回单》单据选择
	gridOutstockM3601selectionchange:function()
	{
		var objRecord = Ext.getCmp('gridOutstockM3601').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockD3601').getStore().removeAll();
        }else{
            var listDetail1 = [];
			var strdtl={
				columnId:'m.outstock_no',
				value:objRecord[0].get("outstockNo")
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('gridOutstockD3601')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockD3601').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockD3601').getStore()
					.load();
        }
	},
	
	//拣货表单回单》单据选择
	ggridOutstockMCheck3601selectionchange:function()
	{
		var objRecord = Ext.getCmp('gridOutstockMCheck3601').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockDCheck3601').getStore().removeAll();
        }else{
            var listDetail1 = [];
			var strdtl={
				columnId:'m.outstock_no',
				value:objRecord[0].get("outstockNo")
			};
			listDetail1.push(strdtl);
			var jsonStr = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonStr
			};
			Ext.apply(Ext.getCmp('gridOutstockDCheck3601')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDCheck3601').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDCheck3601').getStore()
					.load();
        }
	},
	
	//编辑前事件
	gridOutstockD3601beforeedit:function(editor,e,eOpts)
	{
		 if(!g_strIisCanEdit3601)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
			var record = Ext.getCmp('gridOutstockM3601').getSelectionModel().getSelection();
	        if((record[0].data.operateType=='P' || record[0].data.operateType=='M') 
	        		&&(e.field=='realBox' || e.field=='realQty'))
	        {
		        editor.cancel = true;
		        return  false; 
	        }
	    }
	},
	
	//网格编辑事件
	gridOutstockD3601edit:function(editor,e,eOpts)
	{
		/**
		 * odata_outstock_m OPERATE_TYPE
		 * 1、P型拣货不允许差异回单
		 * 2、C型和B型拣货可差异回单。实际数量不可超过计划数量，但可少于计划数量
		 */
		if(e.field=='realBox' || e.field=='realQty')
		{
			var objRecord = Ext.getCmp('gridOutstockM3601').getSelectionModel().getSelection();
			if(objRecord[0].get('operateType')=='P' || objRecord[0].get('operateType')=='M')
	    	{
				if(e.record.data.realQty != e.record.data.articleQty)
				{
					Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityIsdifferentPlan);
					editor.context.record.set(e.field,editor.context.originalValue);
	    			if(e.field=='realBox'){
	    				editor.context.record.set('realQty',editor.context.originalValue*e.record.data.packingQty);
	    			}else if(e.field=='realQty'){
	    				editor.context.record.set('realBox',editor.context.originalValue/e.record.data.packingQty);
	    			}
				}
	    	}else
	    	{
	    		if(e.record.data.realQty>e.record.data.articleQty)
	    		{
	    			Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityCannotMoreThanPlan);
					editor.context.record.set(e.field,editor.context.originalValue);
					if(e.field=='realBox'){
						editor.context.record.set('realQty',editor.context.originalValue*e.record.data.packingQty);
					}else if(e.field=='realQty'){
						editor.context.record.set('realBox',editor.context.originalValue/e.record.data.packingQty);
					}
	    		}
	    	}
		}else if(e.field=='custContainerNo'){
			/*
			 * 1、若在标签表存着的号，需检查是不是出货标签，并且是此客户的，若不是，给予拦截；
			 * 2、若标签表不存着此号，可录入
			*/
			if(!Ext.isEmpty(e.value)){
				if(e.value.toUpperCase()=='N'){
					editor.context.record.set(e.field,editor.context.originalValue);
					Ext.example.msg($i18n.prompt,$i18n_prompt.labelNoCanNotBeN);
					return;
				}
				for(var i=editor.context.record.index;i<Ext.getCmp('gridOutstockD3601').getStore().data.length;i++)
				{
					Ext.getCmp('gridOutstockD3601').getStore().getAt(i).set('custContainerNo',e.value);
				}
			}
		}
	},
	
	//回单
	butReceipt3601click:function()
	{
		var outstock_name = Ext.getCmp('cmbWorkerNo3601').getValue();
		var objOutstockM = Ext.getCmp('gridOutstockM3601').getSelectionModel().getSelection();
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
		var intgridcount=Ext.getCmp("gridOutstockD3601").getStore().getCount();
		if(intgridcount>0)
		{
			if(!commonCheckdetailgrid('gridOutstockD3601',0,intgridcount-1))
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
				var objRecord  = Ext.getCmp('gridOutstockD3601').getStore().getAt(i);
				var strDtl={		
						outstockNo:objRecord.get('outstockNo'),
						warehouseNo:objRecord.get('warehouseNo'),
						ownerNo:objRecord.get('ownerNo'),
						custNo:objRecord.get('custNo'),
						articleNo:objRecord.get('articleNo'),
						packingQty:objRecord.get('packingQty'),
						SCellNo:objRecord.get('SCellNo'),
						custContainerNo:objRecord.get('custContainerNo'),
						articleQty:objRecord.get('articleQty'),
						realQty:objRecord.get('packingQty') * objRecord.get('realBox')  ,
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
						rsv_batch8 : objRecord.get('rsv_batch8')			 
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
				url:'odata_OutstockMReceiptAction_save.action',
				params:params,
				success:function(response){
					msgShow.hide();
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						g_strIisCanEdit3601=false;
						commonSetMsterReadOnlyByArray(
								new Array( 'cmbOwnerNo3601','cmbWave_no3601',
										'cmbBatch_no3601','cmbOperate_type3601','cmbOutstockNo3601'),
								false);
						commonMenu4Button('menu3601','save');
						commonSetMsterReadOnlyByArray(
								new Array('cmbWorkerNo3601'),
								true);
						Ext.getCmp('cmbOwnerNo3601').focus();
						Ext.getCmp('cmbOwnerNo3601').setValue(null);
						Ext.getCmp('cmbOutstockNo3601').setValue(null);
						Ext.getCmp('cmbOwnerNo3601').getStore().load();
						Ext.getCmp('butReceipt3601').setDisabled(true);
						Ext.getCmp('outstockFormCheckTab3601').setDisabled(false);
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
		}
		});
	},
	
	//拣货表单回单》关闭前事件
	odata_OutstockFormReceiptUIBeforeclose:function(){
		g_strIisCanEdit3601=false;
		Ext.getCmp('gridOutstockD3601').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3601').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo3601').setValue(null);
	}
});

//表单回单》加载单据参数传递
function loadgridOutstockM3601()
{
	var ownerNo3601 = Ext.getCmp('cmbOwnerNo3601').getValue();
	var wave_no3601 = Ext.getCmp('cmbWave_no3601').getValue();
	var batch_no3601 = Ext.getCmp('cmbBatch_no3601').getValue();
	var operate_type3601 = Ext.getCmp('cmbOperate_type3601').getValue();
	var listDetail1 = [];
	if(!Ext.isEmpty(ownerNo3601)
	   && !Ext.isEmpty(wave_no3601)
	   && !Ext.isEmpty(batch_no3601)
	   && !Ext.isEmpty(operate_type3601))
	{
		if(!Ext.isEmpty(ownerNo3601))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNo3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(wave_no3601))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:Ext.getCmp('cmbWave_no3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(batch_no3601))
		{
			var strdtl={
			columnId:'ood.batch_no',
			value:Ext.getCmp('cmbBatch_no3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(operate_type3601))
		{
			var strdtl={
			columnId:'oom.operate_type',
			value:Ext.getCmp('cmbOperate_type3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbOutstockNo3601').getValue()))
		{
			var strdtl={
			columnId:'oom.outstock_no',
			value:Ext.getCmp('cmbOutstockNo3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			strQuery : jsonStr
		};
		Ext.apply(Ext.getCmp('gridOutstockM3601')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('gridOutstockM3601').getStore()
				.removeAll();
		Ext.getCmp('gridOutstockM3601').getStore()
				.load();
	}else
	{
		return;
	}
	
}

//历史单据查询》加载单据参数传递
function loadgridOutstockMCheck3601()
{
	var ownerNoCheck3601 = Ext.getCmp('cmbOwnerNoCheck3601').getValue();
	var wave_noCheck3601 = Ext.getCmp('cmbWave_noCheck3601').getValue();
	var batch_noCheck3601 = Ext.getCmp('cmbBatch_noCheck3601').getValue();
	var operate_typeCheck3601 = Ext.getCmp('cmbOperate_typeCheck3601').getValue();
	var listDetail1 = [];
	if(!(Ext.isEmpty(ownerNoCheck3601)
	   && Ext.isEmpty(wave_noCheck3601)
	   && Ext.isEmpty(batch_noCheck3601)
	   && Ext.isEmpty(operate_typeCheck3601)
	   && Ext.isEmpty(Ext.getCmp('cmbOutstockNoCheck3601').getValue())))
	{
		if(!Ext.isEmpty(ownerNoCheck3601))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:ownerNoCheck3601
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(wave_noCheck3601))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:wave_noCheck3601
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(batch_noCheck3601))
		{
			var strdtl={
			columnId:'ood.batch_no',
			value:batch_noCheck3601
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(operate_typeCheck3601))
		{
			var strdtl={
			columnId:'oom.operate_type',
			value:operate_typeCheck3601
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbOutstockNoCheck3601').getValue()))
		{
			var strdtl={
			columnId:'oom.outstock_no',
			value:Ext.getCmp('cmbOutstockNoCheck3601').getValue()
			};
			listDetail1.push(strdtl);
		}
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			strQuery : jsonStr
		};
		Ext.apply(Ext.getCmp('gridOutstockMCheck3601')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('gridOutstockMCheck3601').getStore()
				.removeAll();
		Ext.getCmp('gridOutstockMCheck3601').getStore()
				.load();
	}
}