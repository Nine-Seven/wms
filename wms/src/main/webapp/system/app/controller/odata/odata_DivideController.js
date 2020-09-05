/*
 * 分播回单
 * 3701
 * zhouhuan
 */
var g_strIsCanEdit3701 = false;
var g_strRowindex3701 = 0;
var g_strDivideFixPal = "";
var g_strDivideCheckWave = "";
var g_strDivideDiffQtyFlag = "";
Ext.getCmp('cmbWave_no3701').focus();
bindEnterSkip($('#form3701'));
Ext.define('cms.controller.odata.odata_DivideController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_DivideUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//波次号选择
			'combo[id=cmbWave_no3701]' : {
				select:this.waveNoChange
			},
			//历史单据查询》波次号选择
			'combo[id=cmbWave_noCheck3701]' : {
				select:this.cmbWave_noCheck3701Change
			},
			//分播回单》批次号选择
			'wms_DefFieldValCombo[id=cmbBatch_no3701]' : {
				select:this.mBatchNoChange
			},
			//历史单据查询》批次号选择
			'wms_DefFieldValCombo[id=cmbBatch_noCheck3701]' : {
				select:this.cmbBatch_noCheck3701Change
			},//分播回单》商品条码
			'combo[id=barcode3701]':{
				select:this.formDivideM3701change
			},
			//分播回单》单据选择
			'odata_DividUI grid[id=gridDivideM3701]':{
				beforeselect:this.gridDivideM3701Beforeselect,
				selectionchange:this.gridDivideM3701selectionchange
			},
			//分播回单》修改
			'odata_DividUI button[name=edit]':{
				click:this.edit
			},
			//查询
			'odata_DividUI button[name=query]':{
				click:this.query
			},
			//分播回单》保存
			'odata_DividUI button[name=save]':{
				click:this.save
			},
			//刷新
			'odata_DividUI button[name=refresh]':{
				click:this.refresh
			},
			//网格编辑
			'odata_DividUI grid[id=gridDivide_d3701]':{
				beforeedit:this.gridDivide_d3701beforeedit,
				edit:this.gridDivide_d3701edit
			},//目的标签取号
			'odata_DividUI actioncolumn[id=queryDContainerNo3701]':{
				click:this.queryDContainerNo3701
			},
			
			//撤销
			'odata_DividUI button[name=undo]':{
				click:this.undo
			},
			//TAB页切换
			'odata_DividUI tabpanel[id=tabpanel3701]':{
				tabchange:this.TabpanelChange
			},
			/*//历史单据查询》标签号选择
			'odata_DividUI combo[id=cmbLabel_noCheck3701]':{
				select:this.cmbLabel_noCheck3701select
			},*/
			//历史单据查询》分播单号选择
			'odata_DividUI combo[id=cmbDivide_noCheck3701]':{
				select:this.cmbDivide_noCheck3701select
			},
			//分播回单》历史单据选择
			'odata_DividUI grid[id=gridDivideMCheck3701]':{
				selectionchange:this.gridDivideMCheck3701selectionchange
			},
			//取销
			'odata_DividUI button[id=cancel_divide]':{
				click:this.cancel_divideClick
			}
		});
		
	},
	
	//刷新
	refresh:function(){
		if(Ext.getCmp('tabpanel3701').getActiveTab().id=='tabDivide3701' )
		{
			Ext.getCmp('cmbBatch_no3701').setValue(null);
		    Ext.getCmp('cmbWave_no3701').setValue(null);
		    Ext.getCmp('cmbBatch_no3701').getStore().reload();
			Ext.getCmp('cmbWave_no3701').getStore().reload();
			Ext.getCmp('cmbDivide_no3701').setValue(null);
			Ext.getCmp('cmbStatus3701').setValue(null);
			Ext.getCmp('cmbDivide_type3701').setValue(null);
			Ext.getCmp('cmbOperateDate3701').setValue(null);
			Ext.getCmp('gridDivideM3701').getStore().removeAll();
			Ext.getCmp('gridDivide_d3701').getStore().removeAll();
			Ext.getCmp('cmbWave_no3701').setValue(null);
			Ext.getCmp('cmbWave_no3701').getStore().reload();
			Ext.getCmp('barcode3701').setValue(null);
			Ext.getCmp('barcode3701').getStore().reload();
			Ext.getCmp('gridDivideM3701').getStore().reload();			
			//Ext.getCmp('gridDivide_d3701').getStore().reload();
			gridDivideM3701selectionchange();
		}else if(Ext.getCmp('tabpanel3701').getActiveTab().id=='tabDivideCheck3701')
		{
			Ext.getCmp('gridDivideMCheck3701').getStore().removeAll();
			Ext.getCmp('gridDivideDCheck3701').getStore().removeAll();
			Ext.getCmp('cmbDivide_noCheck3701').setValue(null);
			Ext.getCmp('cmbDivide_noCheck3701').getStore().load();
		}
		
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('cmbDivide_name3701').setReadOnly(true); 
		Ext.getCmp('gridDivideM3701').getStore().load();
		g_strIsCanEdit3701=false;
		Ext.getCmp('menu3701').items.items[0].setDisabled(true);
		Ext.getCmp('menu3701').items.items[3].setDisabled(true);
		Ext.getCmp('menu3701').items.items[4].setDisabled(true);
		
		//显示变量0为不显示，1为显示  add by huangcx at 20160601
		var articleQty_1=commonGetModuleField('3701','articleQty')[0].flag;
		var planBox3701_1=commonGetModuleField('3701','planBox')[0].flag;
		var planQmin3701_1=commonGetModuleField('3701','planQmin')[0].flag;
		var planDis3701_1=commonGetModuleField('3701','planDis')[0].flag;
		var realQty_1=commonGetModuleField('3701','realQty')[0].flag;
		var realBox3701_1=commonGetModuleField('3701','realBox')[0].flag;
		var realQmin3701_1=commonGetModuleField('3701','realQmin')[0].flag;
		var realDis3701_1=commonGetModuleField('3701','realDis')[0].flag;
		var packingUnit3701_1=commonGetModuleField('3701','packingUnit')[0].flag;
		var packingSpec3701_1=commonGetModuleField('3701','packingSpec')[0].flag;
		
		var articleQty_2=commonGetModuleField('3701','articleQty')[0].flag;
		var planBox3701_2=commonGetModuleField('3701','planBox')[0].flag;
		var planQmin3701_2=commonGetModuleField('3701','planQmin')[0].flag;
		var planDis3701_2=commonGetModuleField('3701','planDis')[0].flag;
		var realQty_2=commonGetModuleField('3701','realQty')[0].flag;
		var realBox3701_2=commonGetModuleField('3701','realBox')[0].flag;
		var realQmin3701_2=commonGetModuleField('3701','realQmin')[0].flag;
		var realDis3701_2=commonGetModuleField('3701','realDis')[0].flag;
		var packingUnit3701_2=commonGetModuleField('3701','packingUnit')[0].flag;
		var packingSpec3701_2=commonGetModuleField('3701','packingSpec')[0].flag;

		if(articleQty_1==0){
			Ext.getCmp('articleQty_1').setVisible(false);
		}
		if(planBox3701_1==0){
			Ext.getCmp('planBox3701_1').setVisible(false);
		}
		if(planQmin3701_1==0){
			Ext.getCmp('planQmin3701_1').setVisible(false);
		}
		if(planDis3701_1==0){
			Ext.getCmp('planDis3701_1').setVisible(false);
		}
		if(realQty_1==0){
			Ext.getCmp('realQty_1').setVisible(false);
		}
		if(realBox3701_1==0){
			Ext.getCmp('realBox3701_1').setVisible(false);
		}
		if(realQmin3701_1==0){
			Ext.getCmp('realQmin3701_1').setVisible(false);
		}
		if(realDis3701_1==0){
			Ext.getCmp('realDis3701_1').setVisible(false);
		}
		if(packingUnit3701_1==0){
			Ext.getCmp('packingUnit3701_1').setVisible(false);
		}
		if(packingSpec3701_1==0){
			Ext.getCmp('packingSpec3701_1').setVisible(false);
		}

		if(articleQty_2==0){
			Ext.getCmp('articleQty_2').setVisible(false);
		}
		if(planBox3701_2==0){
			Ext.getCmp('planBox3701_2').setVisible(false);
		}
		if(planQmin3701_2==0){
			Ext.getCmp('planQmin3701_2').setVisible(false);
		}
		if(planDis3701_2==0){
			Ext.getCmp('planDis3701_2').setVisible(false);
		}
		if(realQty_2==0){
			Ext.getCmp('realQty_2').setVisible(false);
		}
		if(realBox3701_2==0){
			Ext.getCmp('realBox3701_2').setVisible(false);
		}
		if(realQmin3701_2==0){
			Ext.getCmp('realQmin3701_2').setVisible(false);
		}
		if(realDis3701_2==0){
			Ext.getCmp('realDis3701_2').setVisible(false);
		}
		if(packingUnit3701_2==0){
			Ext.getCmp('packingUnit3701_2').setVisible(false);
		}
		if(packingSpec3701_2==0){
			Ext.getCmp('packingSpec3701_2').setVisible(false);
		}
		//end add
	},
	
	//历史单据查询》标签号选择
	/*cmbLabel_noCheck3701select:function()
	{
		var listDetail = [];
		var strDtl  =  {
		    columnId:'clm.label_no',
			value:Ext.getCmp('cmbLabel_noCheck3701').getValue()
		};
		listDetail.push(strDtl);
		var strJson  =  Ext.encode(listDetail);
		var strWheresql  =  {
			str : strJson
		};
		Ext.apply(Ext.getCmp('cmbDivide_noCheck3701')
				.getStore().proxy.extraParams,
				strWheresql);
		Ext.getCmp('cmbDivide_noCheck3701').getStore()
				.removeAll();
		Ext.getCmp('cmbDivide_noCheck3701').getStore()
				.load();
		loadgrid_gridDivideMCheck3701();
	},*/
	
	//历史单据查询》分播单号选择
	cmbDivide_noCheck3701select:function()
	{
		loadgrid_gridDivideMCheck3701();
	},
	
	
	//TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabpanel3701').getActiveTab().id=='tabDivide3701' )
		{
			Ext.getCmp('cmbWave_no3701').focus();
			Ext.getCmp('cmbWave_no3701').setValue(null);
			Ext.getCmp('cmbWave_no3701').getStore().load();
		}else if(Ext.getCmp('tabpanel3701').getActiveTab().id=='tabDivideCheck3701')
		{
			Ext.getCmp('cmbWave_noCheck3701').focus();
			Ext.getCmp('gridDivideMCheck3701').getStore().removeAll();
			Ext.getCmp('gridDivideDCheck3701').getStore().removeAll();
			Ext.getCmp('cmbWave_noCheck3701').setValue(null);
			Ext.getCmp('cmbBatch_noCheck3701').setValue(null);
			Ext.getCmp('cmbDivide_noCheck3701').setValue(null);
			Ext.getCmp('cmbWave_noCheck3701').getStore().load();
			
		}
		
	},
	
	focus3701:function(){
		Ext.getCmp('menu3701').items.items[3].setDisabled(true);
		Ext.getCmp('menu3701').items.items[4].setDisabled(true);
		Ext.getCmp('cmbWave_no3701').focus();
		bindEnterSkip($('#form3701'));
	},
	
	/**
	 * 查找
	 */
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=3701;
		queryGrid='gridDivideM3701';					
	},
	
	/*
	 * 撤销
	 */
	undo:function(){
		commonMenu4Button('menu3701','undo');
		Ext.getCmp('gridDivideM3701').fireEvent('selectionchange','');
		Ext.getCmp('cmbDivide_name3701').setReadOnly(true);
		g_strIsCanEdit3701=false;
	},
	
	/*
	 * 检查不同客户是否有相同标签号
	 */
	gridDivide_d3701edit:function(editor,e,eOpts){
		if(e.field=='custContainerNo'){
			if(!Ext.isEmpty(e.value)){
				if(e.value.toUpperCase()=='N')
				{
					
					editor.context.record.set(e.field,editor.context.originalValue);
					Ext.example.msg($i18n.prompt,$i18n_prompt.labelNoCanNotBeN);//标签号不能为N
					editor.context.record.set(e.field,null);
					return;
				}
				if(editor.grid.getStore().findBy(function(record, id) 
				{  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('custNo')!=editor.context.record.data.custNo
					&& record.get('custContainerNo')==editor.context.record.data.custContainerNo;
			    })!=-1)				
				{
					Ext.example.msg($i18n.prompt, $i18n_prompt.difCustDiffLabelNo);//不同客户标签号不能一样！
					editor.context.record.set('custContainerNo',null);
					var intIndex = this.getGridSelIndex();
					return;
				}
		/////////////////////////////////////////////////////////////////////
//				commonCheckLabelNo(e.record.data.SContainerNo,e.value,
//					e.record.data.custNo,
//					e.record.data.articleNo);
				

				var params=
				{
					strSContainerNo:e.record.data.SContainerNo,
					strCustContainerNo:e.value,
					strCustNo:e.record.data.custNo,
					strArticleNo:e.record.data.articleNo,
					deliverObj:e.record.data.deliverObj,
					ownerNo:e.record.data.ownerNo
				};
				Ext.Ajax.request({
					method:'POST',
					url:'checkDivideLabelNoAction_checkDivideLabelNo.action',
					params:params,
					async:false,
					success:function(response)
					{
						var data=Ext.decode(response.responseText);
						if(!data.isSucc)
						{
							Ext.Msg.alert($i18n.prompt,data.msg);
						//	Ext.example.msg($i18n.prompt,data.msg);
							editor.context.record.set('custContainerNo',null);
						}
					}
				});

				
				
	   //////////////////////////////////////////////////////////////////////
			}
		}
		else if(e.field=='realWholenum')
		{
			var strDivideExcess = e.record.data.divideExcess;
			var numMaxQty = e.record.data.qty * strDivideExcess + e.record.data.qty;
			if(e.record.data.realQty>numMaxQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyLargerDivideQty);//总数量已超出允许的最大超量比率！
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='realWholenum'){
					objdata[0].set('realQty',editor.context.originalValue*
							e.record.data.packingQty);
				}
			return;
			}
		}
	},
	queryDContainerNo3701:function(){
		if(Ext.isEmpty(workSpaceNo)){
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		var obj = Ext.getCmp('gridDivide_d3701').getSelectionModel().getSelection();
		var master={
				EnterpriseNo:Ext.get('enterpriseNo').getValue(),
				WarehouseNo:Ext.get('warehouseNo').getValue(),
				DivideNO:obj[0].data.divideNo,
				LableNO:obj[0].data.labelNo,
				CustNo:obj[0].data.deliverObj,
				ArticleNO:obj[0].data.articleNo,
				DockNo:workSpaceNo,
				MType:'P',
				WorkerNO:Ext.get('workerNo').getValue()
			};
		var str=Ext.encode(master);
		var params={
				str:str
		};
		Ext.Ajax.request({
			method:'post',
			url:'odata_DivideAction_printDContainerNo',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					obj[0].set('custContainerNo',data.obj);
				}else{
					Ext.Msg.alert($i18n.prompt,data.msg);
					//Ext.example.msg('提示',data.msg);
				}
			}
		});
	},
	//波次号选择
	waveNoChange:function(th,newValue,oldValue,eOpts){
		if(!Ext.isEmpty(Ext.getCmp('cmbWave_no3701').getValue()))
		{
			var listDetail = [];
			var strDtl  =  {
			    columnId:'odd.wave_no',
				value:Ext.getCmp('cmbWave_no3701').getValue()
			};
			listDetail.push(strDtl);
			var strJson  =  Ext.encode(listDetail);
			var strWheresql  =  {
				str : strJson
			};
			Ext.apply(Ext.getCmp('cmbBatch_no3701')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbBatch_no3701').getStore()
					.removeAll();
			Ext.getCmp('cmbBatch_no3701').getStore()
					.load();
			loadgrid_gridDivideM3701();
		}else
		{
			Ext.getCmp('cmbBatch_no3701').setValue(null);
		}
   },
   
 //历史单据查询》波次号选择
   cmbWave_noCheck3701Change:function(th,newValue,oldValue,eOpts){
		if(!Ext.isEmpty(Ext.getCmp('cmbWave_noCheck3701').getValue()))
		{
			var listDetail = [];
			var strDtl  =  {
			    columnId:'odd.wave_no',
				value:Ext.getCmp('cmbWave_noCheck3701').getValue()
			};
			listDetail.push(strDtl);
			var strJson  =  Ext.encode(listDetail);
			var strWheresql  =  {
				str : strJson
			};
			Ext.apply(Ext.getCmp('cmbBatch_noCheck3701')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbBatch_noCheck3701').getStore()
					.removeAll();
			Ext.getCmp('cmbBatch_noCheck3701').getStore()
					.load();
			//loadgrid_gridDivideMCheck3701();
		}else
		{
			Ext.getCmp('cmbBatch_noCheck3701').setValue(null);
			Ext.getCmp('cmbDivide_noCheck3701').setValue(null);
		}
  },
		
	//批次号选择
	mBatchNoChange:function(th,newValue,oldValue,eOpts){
		loadgrid_gridDivideM3701();
	},
	
	//历史单据查询》批次号选择
	cmbBatch_noCheck3701Change:function(th,newValue,oldValue,eOpts){
	if(!Ext.isEmpty(Ext.getCmp('cmbBatch_noCheck3701').getValue()))
	{
		var listDetail = [];
		listDetail1 = Ext.decode(Ext.getCmp('cmbBatch_noCheck3701').getStore().getProxy().extraParams.str);
		var strDtl  =  {
		    columnId:'odd.batch_no',
			value:Ext.getCmp('cmbBatch_noCheck3701').getValue()
		};
		listDetail.push(strDtl);
		var strDt2  =  {
			    columnId:'odd.wave_no',
				value:Ext.getCmp('cmbWave_noCheck3701').getValue()
			};
		listDetail.push(strDt2);
		var strJson  =  Ext.encode(listDetail);
		var strWheresql  =  {
			str : strJson
		};
		Ext.apply(Ext.getCmp('cmbDivide_noCheck3701')
				.getStore().proxy.extraParams,
				strWheresql);
		Ext.getCmp('cmbDivide_noCheck3701').getStore()
				.removeAll();
		Ext.getCmp('cmbDivide_noCheck3701').getStore()
				.load();
		loadgrid_gridDivideMCheck3701();
	}else
	{
		Ext.getCmp('cmbDivide_noCheck3701').setValue(null);
	}
  },
  formDivideM3701change:function(){
	  loadgrid_gridDivideM3701();
  },
	gridDivideM3701Beforeselect:function(){
	    if(g_strIsCanEdit3701)
	    {
	        return  false;  
	    }
	},
	
	//分播回单》单据选择
	gridDivideM3701selectionchange:function(){
		var objRecord = Ext.getCmp('gridDivideM3701').getSelectionModel().getSelection();
        debugger;
		if(objRecord.length <= 0){
    		Ext.getCmp('gridDivide_d3701').getStore().removeAll();
    		
    		Ext.getCmp('menu3701').items.items[1].setDisabled(true);
        }else{
        	Ext.getCmp('menu3701').items.items[1].setDisabled(false);
        	Ext.getCmp('cmbDivide_no3701').setValue(objRecord[0].data.divideNo);
			Ext.getCmp('cmbStatus3701').setValue(objRecord[0].data.statusText);
			Ext.getCmp('cmbDivide_type3701').setValue(objRecord[0].data.divideType);
			Ext.getCmp('cmbOperateDate3701').setValue(objRecord[0].data.operateDate);
        	Ext.get('rgstName3701').dom.innerHTML=objRecord[0].data.rgstName;
			Ext.get('rgstDate3701').dom.innerHTML=objRecord[0].data.rgstDate;
			Ext.get('updtName3701').dom.innerHTML=objRecord[0].data.updtName;
			Ext.get('updtDate3701').dom.innerHTML=objRecord[0].data.updtDate;
			commonSetMsterReadOnlyByArray(
					new Array('cmbDivide_no3701','cmbStatus3701','cmbDivide_type3701','cmbOperateDate3701'),true);
			
            var listdetail1 = [];
            var strdtl={
			columnId:'a.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
			};
            listdetail1.push(strdtl);
			var strdtl={
				columnId:'a.divide_no',
				value:objRecord[0].get("divideNo")
			};
			listdetail1.push(strdtl);
			var jsonDetail1 = Ext.encode(listdetail1);
			var wheresql = {
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridDivide_d3701')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridDivide_d3701').getStore()
					.removeAll();
			Ext.getCmp('gridDivide_d3701').getStore()
					.load();
        }
	},
	
	//分播回单》历史单据选择
	gridDivideMCheck3701selectionchange:function(){
		var objRecord = Ext.getCmp('gridDivideMCheck3701').getSelectionModel().getSelection();
        if(objRecord.length == 0){
    		Ext.getCmp('gridDivideDCheck3701').getStore().removeAll();
        }else{
            var listdetail1 = [];
            var strdtl={
			columnId:'a.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
			};
            listdetail1.push(strdtl);
			var strdtl={
				columnId:'a.divide_no',
				value:objRecord[0].get("divideNo")
			};
			listdetail1.push(strdtl);
			var jsonDetail1 = Ext.encode(listdetail1);
			var wheresql = {
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridDivideDCheck3701')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridDivideDCheck3701').getStore()
					.removeAll();
			Ext.getCmp('gridDivideDCheck3701').getStore()
					.load();
        }
	},
	
	/**
	 * 保存分播回单
	 */
   save:function()
   {
	   var intGridcount = Ext.getCmp("gridDivide_d3701").getStore().getCount();
	   for(var i=0;i<intGridcount;i++)
	   {
		   var objRecord = Ext.getCmp("gridDivide_d3701").getStore().getAt(i);
		   if(objRecord.data.custContainerNo == "N")
		   {
			   objRecord.set("custContainerNo",null);
			   return;
		   }
	   }
	  
		Ext.Msg.confirm("提示", "确定保存？",
		function(button, text) 
		{
			if (button == 'yes') 
			{
			//var msgShow=commonMegShow("正在保存,请稍等...");
				if(Ext.isEmpty(Ext.getCmp("cmbDivide_name3701").getValue()))
				{
					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.inputRealDivider); //请输入实际分播人员
					return;
				}
						
				if(intGridcount>0)
				{
					if(!commonCheckdetailgrid('gridDivide_d3701',0,intGridcount-1))
					{
						return;
					}else
					{
						var listDetail1 = [];
						for(var i=0;i<intGridcount;i++ ){
							var objRecord  = Ext.getCmp('gridDivide_d3701').getStore().getAt(i);
							var d={	
									id:{
										enterpriseNo:objRecord.get('enterpriseNo'),
										divideNo:objRecord.get('divideNo'),
										warehouseNo:objRecord.get('warehouseNo'),
										ownerNo:objRecord.get('ownerNo'),
										custContainerNo:objRecord.get('custContainerNo')
										//divideId:objRecord.get('divideId')
									},
									divideName:Ext.getCmp("cmbDivide_name3701").getValue(),
									articleNo:objRecord.get('articleNo'),
									articleQty:parseFloat(objRecord.get('planWholenum'))*parseFloat(objRecord.get('packingQty')),
									realQty:objRecord.get('realQty'),
									batchNo:objRecord.get('batchNo'),
									SCellNo:objRecord.get('SCellNo'),
									SContainerNo:objRecord.get('SContainerNo'),
									custNo:objRecord.get('custNo'),
									deliverObj:objRecord.get('deliverObj'),
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
							listDetail1.push(d);
						}
							
						var jsonDetail1 = Ext.encode(listDetail1);		
						var params = {
							    updtName:Ext.get('workerNo').getValue(),
								str:jsonDetail1
						};
						Ext.Ajax.request({
							method:'POST',
							url:'odata_DivideAction_save.action',
							params:params,
							success:function(response){
								//msgShow.hide();
								var objData1 = Ext.decode(response.responseText);
								if(objData1.isSucc){
									Ext.example.msg($i18n_prompt.prompt,objData1.msg);
									g_strIsCanEdit3701=false;
									commonMenu4Button('menu3701','save');
									Ext.getCmp('gridDivideM3701').store.reload();
								}else{
									Ext.Msg.alert($i18n.prompt,objData1.msg+objData1.obj);
									//Ext.example.msg($i18n_prompt.prompt,objData1.msg+objData1.obj);
								}				
							}/*,
							failure:function(response){
								msgShow.hide();
								Ext.example.msg('提示',"提交不上,可能是网络问题");
									}*/
						});	
					}
					}else{			
						Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.tableCannotBeNull);
						return;
					}
				}else
				{
					return;
				}
						
			});
	},
	
	gridDivide_d3701beforeedit:function(e)
	{
		 if(!g_strIsCanEdit3701)
	    {
	        e.cancel = true;
	        return  false;  
	    }
	},
   
    //分播回单》修改
	edit:function()
	{
		var objRecord = Ext.getCmp('gridDivideM3701').getSelectionModel().getSelection();
        if(objRecord.length != 0){
        	commonMenu4Button('menu3701','edit');
        	g_strIsCanEdit3701 = true;
        	Ext.getCmp('cmbDivide_name3701').setReadOnly(false);
        	Ext.getCmp('cmbDivide_name3701').focus();
			//Ext.getCmp('gridDivide_d3701').editingPlugin.startEdit(0,9);
        }
	},
	/**
	 * 获取grid选中行
	 * @returns
	 */
	getGridSelIndex:function()
	{
		var select = Ext.getCmp('gridDivide_d3701').getSelectionModel().getSelection()[0];
		var index = Ext.getCmp('gridDivide_d3701').getStore().indexOf(select);
		return index;
	},
	cancel_divideClick:function()
	{			  
		Ext.Msg.confirm("提示", "确定取消分播？",
		function(button, text) 
		{
			if (button == 'yes') 
			{
				//var msgShow=commonMegShow("正在保存,请稍等...");		
				
				var objRecord = Ext.getCmp('gridDivideM3701').getSelectionModel().getSelection();
		        if(objRecord.length == 0){
			        var listDetail1={	
						id:{
							divideNo:objRecord.get('divideNo'),
							warehouseNo:objRecord.get('warehouseNo'),
							ownerNo:objRecord.get('ownerNo')
						},
						divideName:Ext.get('workerNo').getValue()													
					};
					
					var jsonDetail1 = Ext.encode(listDetail1);		
					var params = {
						    updtName:Ext.get('workerNo').getValue(),
							str:jsonDetail1
					};
					Ext.Ajax.request({
						method:'POST',
						url:'odata_DivideAction_cancelDivide.action',
						params:params,
						success:function(response){
							//msgShow.hide();
							var objData1 = Ext.decode(response.responseText);
							if(objData1.isSucc){
								Ext.example.msg($i18n_prompt.prompt,objData1.msg);
								g_strIsCanEdit3701=false;
								commonMenu4Button('menu3701','save');
								Ext.getCmp('gridDivideM3701').store.reload();
							}else{
								Ext.Msg.alert($i18n.prompt,objData1.msg+objData1.obj);

								//Ext.example.msg($i18n_prompt.prompt,objData1.msg+objData1.obj);
							}				
						}
//					,
//						failure:function(response){
//							msgShow.hide();
//							Ext.example.msg('提示',"提交不上,可能是网络问题");
//						}
					});
		        }								
			}
		});
	}
});

//分播回单》加载分播单头档
function loadgrid_gridDivideM3701()
{
	var listdetail1 = [];
	var strdtl={
		columnId:'odd.warehouse_no',
		value:Ext.get("warehouseNo").getValue()
	};
	listdetail1.push(strdtl);
	if(!Ext.isEmpty(Ext.getCmp('cmbWave_no3701').getValue())){
		var strdtl={
		columnId:'odd.wave_no',
		value:Ext.getCmp('cmbWave_no3701').getValue()
		};
		listdetail1.push(strdtl);
	}
	if(!Ext.isEmpty(Ext.getCmp('cmbBatch_no3701').getValue())){
		var strdtl={
		columnId:'odd.batch_no',
		value:Ext.getCmp('cmbBatch_no3701').getValue()
		};
		listdetail1.push(strdtl);
	}
	var jsonDetail1 = Ext.encode(listdetail1);
	if(!Ext.isEmpty(Ext.getCmp('barcode3701').getValue())){
		var barcode =Ext.getCmp('barcode3701').getValue();
	}else{
		var barcode=null;
	}
	var wheresql = {
		strQuery : jsonDetail1,
		strBarcode : barcode
	};
	Ext.apply(Ext.getCmp('gridDivideM3701')
			.getStore().proxy.extraParams,
			wheresql);
	Ext.getCmp('gridDivideM3701').getStore()
			.removeAll();
	Ext.getCmp('gridDivideM3701').getStore()
			.load();
}

//历史单据查询》加载分播单头档
function loadgrid_gridDivideMCheck3701(){
	var listdetail1 = [];
	var cmbWave_noCheck3701 = Ext.getCmp('cmbWave_noCheck3701').getValue();
	var cmbBatch_noCheck3701 = Ext.getCmp('cmbBatch_noCheck3701').getValue();
	if(!Ext.isEmpty(cmbBatch_noCheck3701)
			   && !Ext.isEmpty(cmbWave_noCheck3701))
	{
		if(!Ext.isEmpty(cmbWave_noCheck3701)){
			var strdtl={
			columnId:'odd.wave_no',
			value:cmbWave_noCheck3701
			};
			listdetail1.push(strdtl);
		}
		if(!Ext.isEmpty(cmbBatch_noCheck3701)){
			var strdtl={
			columnId:'odd.batch_no',
			value:cmbBatch_noCheck3701
			};
			listdetail1.push(strdtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('cmbDivide_noCheck3701').getValue())){
			var strdtl={
			columnId:'odd.divide_no',
			value:Ext.getCmp('cmbDivide_noCheck3701').getValue()
			};
			listdetail1.push(strdtl);
		}
	}
	var jsonDetail1 = Ext.encode(listdetail1);
	var wheresql = {
	   strQuery : jsonDetail1
	};
	Ext.apply(Ext.getCmp('gridDivideMCheck3701')
			.getStore().proxy.extraParams,
			wheresql);
	Ext.getCmp('gridDivideMCheck3701').getStore()
			.removeAll();
	Ext.getCmp('gridDivideMCheck3701').getStore()
			.load();
}
