/**
 * 模块名称：电商流水标签回单
 * 模块编码：3504
 * 创建：hkl
 */

var g_strIisCanEdit3504 = false;
var g_Refresh = false;//回单成功后是否刷新 huangb 20160801
Ext.define('cms.controller.odata.odata_B2COutstockLabelWaterController',{
	extend:'Ext.app.Controller',
	controllerId:'odata_B2COutstockLabelWaterController',
	requires:[
          'cms.view.odata.odata_B2COutstockLabelWaterUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			
			
			//流水标签回单》修改
			'odata_B2COutstockLabelWaterUI button[name=edit]':{
				click:this.editClick
			},
			//流水标签回单》撤销
			'odata_B2COutstockLabelWaterUI button[name=undo]':{
				click:this.undoClick
			},
			
			//流水标签回单》刷新
			'odata_B2COutstockLabelWaterUI button[name=refresh]':{
				click:this.TabpanelChange
			},
			//流水标签回单》波次选择
			'odata_B2COutstockLabelWaterUI combo[id=cmbWave_no3504]':{
				select:this.cmbWave_no3504change
			},
			
			//流水标签回单》批次选择
			'odata_B2COutstockLabelWaterUI combo[id=cmbBatch_no3504]':{
				select:this.cmbBatch_no3504change
			},
			//流水标签回单》单据选择
			'odata_B2COutstockLabelWaterUI grid[id=gridOutstockM3504]':{
				beforeselect:this.gridOutstockM3504Beforeselect,
				selectionchange:this.gridOutstockM3504selectionchange
			},
			
			//流水标签回单》回单
			'odata_B2COutstockLabelWaterUI button[id=butReceipt3504]':{
				click:this.butReceipt3504click
			},//流水标签回单》零回单
			'odata_B2COutstockLabelWaterUI button[id=butReceiptZero3504]':{
				click:this.butReceiptZero3504click
			},
			//流水标签回单》关闭前事件
			'odata_B2COutstockLabelWaterUI':{
				beforeclose:this.odata_B2COutstockLabelWaterUIBeforeclose
			},//TAB页切换
			'odata_OutstockLabelReceiptUI tabpanel[id=tabpanel3504]':{
				tabchange:this.TabpanelChange
			}
			
		});	
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		g_strIisCanEdit3504=false;
		Ext.getCmp('cmbWave_no3504').setValue(null);
		Ext.getCmp('cmbWave_no3504').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var articleQty3504=commonGetModuleField('3504','articleQty')[0].flag;
		var planBox3504=commonGetModuleField('3504','planBox')[0].flag;
		var planQmin3504=commonGetModuleField('3504','planQmin')[0].flag;
		var planDis3504=commonGetModuleField('3504','planDis')[0].flag;
		var packingUnit3504=commonGetModuleField('3504','packingUnit')[0].flag;
		var packingSpec3504=commonGetModuleField('3504','packingSpec')[0].flag;

		if(articleQty3504==0){
			Ext.getCmp('articleQty3504').setVisible(false);
		}
		if(planBox3504==0){
			Ext.getCmp('planBox3504').setVisible(false);
		}
		if(planQmin3504==0){
			Ext.getCmp('planQmin3504').setVisible(false);
		}
		if(planDis3504==0){
			Ext.getCmp('planDis3504').setVisible(false);
		}
		if(packingUnit3504==0){
			Ext.getCmp('packingUnit3504').setVisible(false);
		}
		if(packingSpec3504==0){
			Ext.getCmp('packingSpec3504').setVisible(false);
		}
		//end add
	},
	
	//流水标签回单》货主选择
	cmbOwnerNo3504Select:function()
    {
		var listDetail   =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3504').getValue()))
		{
			var strDtl  =  {
				    columnId:'ood.owner_no',
					value:Ext.getCmp('cmbOwnerNo3504').getValue()
				};
				listDetail.push(strDtl);
				var strJson  =  Ext.encode(listDetail);
				var strWheresql  =  {
					str : strJson
				};
				Ext.apply(Ext.getCmp('cmbWave_no3504')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbWave_no3504').getStore()
						.removeAll();
				Ext.getCmp('cmbWave_no3504').getStore()
						.load();
		}else
		{
			return;
		}
    },
  //TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		
		{
			disableButtonFunc(0,'#menu3504 [name=edit]','修改');
			Ext.getCmp('cmbWave_no3504').focus();
			Ext.getCmp('cmbWave_no3504').setValue(null);
			Ext.getCmp('cmbWave_no3504').getStore().load();
		}
	},
	
	
    //修改
	editClick:function()
	{
		var record = Ext.getCmp('gridOutstockM3504').getSelectionModel().getSelection();
        if(record.length != 0)
        {
    		commonMenu4Button('menu3504','edit');
			g_strIisCanEdit3504=true;
			commonSetMsterReadOnlyByArray(
			new Array('cmbWorkerNo3504'),
			false);
			commonSetMsterReadOnlyByArray(
					new Array('cmbWave_no3504',
							'cmbBatch_no3504'),
					true);
			Ext.getCmp('cmbWorkerNo3504').focus();
			Ext.getCmp('butReceipt3504').setDisabled(false);
			Ext.getCmp('butReceiptZero3504').setDisabled(false);
        }
		
	},
	
	//撤销
	undoClick:function()
	{
		commonMenu4Button('menu3504','undo');
		Ext.getCmp('butReceipt3504').setDisabled(true);
		Ext.getCmp('butReceiptZero3504').setDisabled(true);
		Ext.getCmp('gridOutstockD3504').getStore().load();
		commonSetMsterReadOnlyByArray(
		new Array('cmbWorkerNo3504'),
		true);
		commonSetMsterReadOnlyByArray(
				new Array('cmbWave_no3504',
						'cmbBatch_no3504' ),
				false);
		g_strIisCanEdit3504=false;
	},
	
	
	
	//使网格不可编辑
	cmbOwnerNo3504beforeselect:function(){
	    if(g_strIisCanEdit3504)
	    {
	        return  false;  
	    }
	},
	
	//流水标签回单》波次选择
	cmbWave_no3504change:function(){
      // var listDetail1 = [];
       if(!Ext.isEmpty(Ext.getCmp('cmbWave_no3504').getValue()))
       {
    			var strwheresql = {
        				strWave : Ext.getCmp('cmbWave_no3504').getValue()
        			};
    			
    			var strwheresql2 = {
    				strWave : Ext.getCmp('cmbWave_no3504').getValue(),
    				strBatch : Ext.getCmp('cmbBatch_no3504').getValue()
    				
    			};
    			//加载批次
    			Ext.apply(Ext.getCmp('cmbBatch_no3504')
    					.getStore().proxy.extraParams,
    					strwheresql);
    			Ext.getCmp('cmbBatch_no3504').getStore()
    					.removeAll();
    			Ext.getCmp('cmbBatch_no3504').getStore()
    					.load();
    			// Mgrid
    			Ext.apply(Ext.getCmp('gridOutstockM3504')
    					.getStore().proxy.extraParams,
    					strwheresql2);
    			Ext.getCmp('gridOutstockM3504').getStore().removeAll();
    			Ext.getCmp('gridOutstockM3504').getStore().load();
    			//loadgridOutstockM3504();
       }else
	   {
	   		return;
	   }
		
	},
	
	//流水标签回单》批次选择
	cmbBatch_no3504change:function(th,newValue,oldValue,eOpts)
	{
		 if(!Ext.isEmpty(Ext.getCmp('cmbBatch_no3504').getValue()))
	     {
			 var strwheresql = {
 				strWave : Ext.getCmp('cmbWave_no3504').getValue(),
				strBatch : Ext.getCmp('cmbBatch_no3504').getValue()
			};
				
 			// Mgrid
 			Ext.apply(Ext.getCmp('gridOutstockM3504')
 					.getStore().proxy.extraParams,
 					strwheresql);
 			Ext.getCmp('gridOutstockM3504').getStore().removeAll();
 			Ext.getCmp('gridOutstockM3504').getStore().load();
			
			 }else
	   {
	   		return;
	   }
	},
	
	
	

	
	
	
	
	
	
	//流水标签回单》单据选择前事件
	gridOutstockM3504Beforeselect:function()
	{
	    if(g_strIisCanEdit3504)
	    {
	        return  false;  
	    }
	},
	
	//流水标签回单》单据选择
	gridOutstockM3504selectionchange:function()
	{
		var objRecord = Ext.getCmp('gridOutstockM3504').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockDlabel3504').getStore().removeAll();
        }else{
            
			var wheresql = {
				str : objRecord[0].get("outstockNo")
			};
			Ext.apply(Ext.getCmp('gridOutstockDlabel3504')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDlabel3504').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDlabel3504').getStore()
					.load();
			//加载下架单明细列表
			Ext.apply(Ext.getCmp('gridOutstockD3504')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockD3504').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockD3504').getStore()
					.load();
			
        }
	},

	
	//编辑前事件
	gridOutstockD3504beforeedit:function(editor,e,eOpts)
	{
		 if(!g_strIisCanEdit3504)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
			var record = Ext.getCmp('gridOutstockM3504').getSelectionModel().getSelection();
	        if((record[0].data.operateType=='P' || record[0].data.operateType=='M') 
	        	&&(e.field=='realBox' || e.field=='realDis' || e.field=='realQty'))
	        {
		        editor.cancel = true;
		        return  false; 
	        }
	    }
	},
	//零回
	butReceiptZero3504click:function(){
		var strOutstockName = Ext.getCmp('cmbWorkerNo3504').getValue();
		var objOutstockM = Ext.getCmp('gridOutstockM3504').getSelectionModel().getSelection();
		var gridcount=Ext.getCmp('gridOutstockM3504').getStore().getCount();
		if(Ext.isEmpty(strOutstockName))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.realOutstockWork);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureToReceipt,
		function(button, text) {
		if (button == 'yes') {
			var msgShow = commonMegShow($i18n_prompt.receiptting);
			
			var params = {
				strOutstockName:strOutstockName,//下架人
				strOutstockNo:objOutstockM[0].data.outstockNo
			};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_OutstockMWaterAction_saveLabelZero.action',
				async:false,
				params:params,
				success:function(response){
					msgShow.hide();
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						g_Refresh = true;

//						Ext.getCmp('cmbWave_no3504').setValue(null);
//						Ext.getCmp('cmbWave_no3504').getStore().load();
						g_strIisCanEdit3504=false;
						commonMenu4Button('menu3504','save');
						commonSetMsterReadOnlyByArray(
								new Array('cmbWorkerNo3504'),
								true);
						commonSetMsterReadOnlyByArray(
								new Array( 'cmbWave_no3504',
										'cmbBatch_no3504'),
								false);
						Ext.getCmp('cmbWave_no3504').focus();
						Ext.getCmp('butReceipt3504').setDisabled(true);
						Ext.getCmp('butReceiptZero3504').setDisabled(true);
					}else{
						Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
						//Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}				
				}
			});
			
			if(g_Refresh == true){
				g_Refresh = false;
				//只有当前波次,批次 ,下的所有下架单据都回单了 才整个的全部刷新 huangb 20160801
				if(gridcount <= 1){
					Ext.getCmp('cmbWave_no3504').setValue(null);
					Ext.getCmp('cmbWave_no3504').getStore().load();
				}else{
					Ext.getCmp('gridOutstockM3504').getStore().removeAll();
					Ext.getCmp('gridOutstockM3504').getStore().load();
				}
			}
		}
		});
	},
	//表单回单
	butReceipt3504click:function()
	{//debugger;
		var strOutstockName = Ext.getCmp('cmbWorkerNo3504').getValue();
		var objOutstockM = Ext.getCmp('gridOutstockM3504').getSelectionModel().getSelection();
		var gridcount=Ext.getCmp('gridOutstockM3504').getStore().getCount();
		if(Ext.isEmpty(strOutstockName))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.realOutstockWork);
			return;
		}
		
		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureToReceipt,
		function(button, text) {
		if (button == 'yes') {
			var msgShow = commonMegShow($i18n_prompt.receiptting);
			
			var params = {
				strOutstockName:strOutstockName,//下架人
				strOutstockNo:objOutstockM[0].data.outstockNo
			};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_OutstockMWaterAction_saveLabel.action',
				async:false,
				params:params,
				success:function(response){
					msgShow.hide();
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						g_Refresh = true;

//						Ext.getCmp('cmbWave_no3504').setValue(null);
//						Ext.getCmp('cmbWave_no3504').getStore().load();
						g_strIisCanEdit3504=false;
						commonMenu4Button('menu3504','save');
						commonSetMsterReadOnlyByArray(
								new Array('cmbWorkerNo3504'),
								true);
						commonSetMsterReadOnlyByArray(
								new Array( 'cmbWave_no3504',
										'cmbBatch_no3504'),
								false);
						Ext.getCmp('cmbWave_no3504').focus();
						Ext.getCmp('butReceipt3504').setDisabled(true);
						Ext.getCmp('butReceiptZero3504').setDisabled(true);
					}else{
						Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
						//Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}				
				}
			});
			
			if(g_Refresh == true){
				g_Refresh = false;
				//只有当前波次,批次 ,下的所有下架单据都回单了 才整个的全部刷新 huangb 20160801
				if(gridcount <= 1){
					Ext.getCmp('cmbWave_no3504').setValue(null);
					Ext.getCmp('cmbWave_no3504').getStore().load();
				}else{
					Ext.getCmp('gridOutstockM3504').getStore().removeAll();
					Ext.getCmp('gridOutstockM3504').getStore().load();
				}
			}
		}
		});
	},
	//流水标签回单》关闭前事件
	odata_B2COutstockLabelWaterUIBeforeclose:function(){
		g_strIisCanEdit3504=false;
		Ext.getCmp('gridOutstockD3504').getStore().removeAll();
		//Ext.getCmp('cmbOwnerNo3504').getStore().removeAll();
		//Ext.getCmp('cmbOwnerNo3504').setValue(null);
	}
});

//表单回单》加载单据参数传递
function loadgridOutstockM3504()
{
	var ownerNo3504 = Ext.getCmp('cmbOwnerNo3504').getValue();
	var wave_no3504 = Ext.getCmp('cmbWave_no3504').getValue();
	var batch_no3504 = Ext.getCmp('cmbBatch_no3504').getValue();
	var operate_type3504 = Ext.getCmp('cmbOperate_type3504').getValue();
	var listDetail1 = [];
	if(!Ext.isEmpty(ownerNo3504)
	   && !Ext.isEmpty(wave_no3504)
	   && !Ext.isEmpty(batch_no3504)
	   && !Ext.isEmpty(operate_type3504))
	{
		if(!Ext.isEmpty(ownerNo3504))
		{
			var strdtl={
			columnId:'oom.owner_no',
			value:Ext.getCmp('cmbOwnerNo3504').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(wave_no3504))
		{
			var strdtl={
			columnId:'ood.wave_no',
			value:Ext.getCmp('cmbWave_no3504').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(batch_no3504))
		{
			var strdtl={
			columnId:'ood.batch_no',
			value:Ext.getCmp('cmbBatch_no3504').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(operate_type3504))
		{
			var strdtl={
			columnId:'oom.operate_type',
			value:Ext.getCmp('cmbOperate_type3504').getValue()
			};
			listDetail1.push(strdtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbOutstockNo3504').getValue()))
		{
			var strdtl={
			columnId:'oom.outstock_no',
			value:Ext.getCmp('cmbOutstockNo3504').getValue()
			};
			listDetail1.push(strdtl);
		}
		var jsonStr = Ext.encode(listDetail1);
		var wheresql = {
			strQuery : jsonStr
		};
		Ext.apply(Ext.getCmp('gridOutstockM3504')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('gridOutstockM3504').getStore()
				.removeAll();
		Ext.getCmp('gridOutstockM3504').getStore()
				.load();
	}else
	{
		return;
	}
}

