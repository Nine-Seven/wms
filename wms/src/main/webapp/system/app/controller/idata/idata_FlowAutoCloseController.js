/**
 * 模块名称：流水板自动封板
 * 模块编码：4301
 * 创建：Jun
 */
var g_ICAcrossAreaBox4301='';//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
var g_ICFreezeRate4301='';//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICAlarmRate4301='';//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICCheckPickCell4301='';//存储验收时是否需要校验拣货位;0----不校验；1----校验
var g_ICCheckQpalette4301='';//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
var g_IsCanEdit4301=false;
var g_OverQtyFlag = '0';//是否超量验收0:不超量；1：超量；2：超品 huangb 20160726
Ext.define('cms.controller.idata.idata_FlowAutoCloseController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.idata.idata_FlowAutoCloseUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//新增
			'idata_FlowAutoCloseUI button[name=add]':{
				click:this.addClick
			},//撤销
			'idata_FlowAutoCloseUI button[name=undo]':{
				click:this.undoClick
			},//保存
			'idata_FlowAutoCloseUI button[name=save]':{
				click:this.saveClick
			},//进货汇总单选择
			'idata_FlowAutoCloseUI remoteCombo[id=cmbSImportNo4301]':
			{
				focus:this.cmbSImportNo4301Focus,
				select:this.cmbSImportNo4301Select
			},//货主下拉选择
			'idata_FlowAutoCloseUI bdef_DefOwnerCombo[id=cmbOwnerNo4301]':
			{
				change:this.cmbOwnerNo4301Change
			},//验收网格编辑
			'idata_FlowAutoCloseUI grid[id=grid_01_4301]':{
				beforeedit:this.grid_01_4301Beforeedit,
				edit:this.grid_01_4301edit
			},
			'idata_FlowAutoCloseUI form[id=form_01_4301] field':{
				specialkey:this.boxkeydown
			},//拆笔
			'idata_FlowAutoCloseUI grid[id=grid_01_4301] actioncolumn':{
				click:this.actioncolumnClick
			},
			'remoteCombo[id=lotNo4301]':{
			focus:this.lotNofocus
			},//TAB页切换
			'idata_FlowAutoCloseUI tabpanel[id=tabPid4301]':{
				tabchange:this.tabPidchange
			}
		});
	},
	//界面初始化
	initializtion:function(){
		//显示变量0为不显示，1为显示
		var planBox4301=commonGetModuleField('4301','planBox')[0].flag;
		var planQmin4301=commonGetModuleField('4301','planQmin')[0].flag;
		var planDis4301=commonGetModuleField('4301','planDis')[0].flag;
		var packingUnit4301=commonGetModuleField('4301','packingUnit')[0].flag;
		var packingSpec4301=commonGetModuleField('4301','packingSpec')[0].flag;
		
		if(planBox4301==0){
			Ext.getCmp('planBox4301').setVisible(false);
		}
		if(planQmin4301==0){
			Ext.getCmp('planQmin4301').setVisible(false);
		}
		if(planDis4301==0){
			Ext.getCmp('planDis4301').setVisible(false);
		}
		if(packingUnit4301==0){
			Ext.getCmp('packingUnit4301').setVisible(false);
		}
		if(packingSpec4301==0){
			Ext.getCmp('packingSpec4301').setVisible(false);
		}
	},
	
	lotNofocus:function(th){
		var listDetail1  =  [];
		var strDtl = {
			columnId:"to_char(a.produce_date,'yyyy/mm/dd')",
			value:Ext.Date.format(Ext.getCmp('grid_01_4301').getSelectionModel().getSelection()[0].get('produceDate'),'Y/m/d')
		};
		listDetail1.push(strDtl);
		
		var strDt2 = {
				columnId:"a.article_no",
				value:Ext.getCmp('grid_01_4301').getSelectionModel().getSelection()[0].get('articleNo')
		};
		listDetail1.push(strDt2);
		
		var strQuery={
			strQuery:Ext.encode(listDetail1)
		};
		
		Ext.apply(th.getStore().proxy.extraParams,strQuery);
		
	},
	
	addClick:function()
	{
		Ext.getCmp('form_01_4301').getForm().reset();
		Ext.getCmp('grid_01_4301').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
		new Array('cmbOwnerNo4301','cmbSImportNo4301','cmbCheckWorker4301'),
		false);
		if(Ext.getCmp('cmbOwnerNo4301').getStore().data.length>0)
		{
			Ext.getCmp('cmbOwnerNo4301').setValue(Ext.getCmp('cmbOwnerNo4301').getStore().getAt(0).data.value);		
		}
		if(Ext.getCmp('cmbImportType4301').getStore().data.length>0)
		{
			Ext.getCmp('cmbImportType4301').setValue(Ext.getCmp('cmbImportType4301').getStore().getAt(0).data.value);		
		}
		
		Ext.getCmp('cmbStatus4301').setValue('10');
		Ext.getCmp('dateCheckStartDate4301').setValue(new Date());
		Ext.getCmp('cmbCheckTools4301').setValue('1');
		Ext.getCmp('cmbCheckTools4301').setValue('1');
		commonMenu4Button('menu4301','add');
		Ext.getCmp('cmbSImportNo4301').focus();
		g_IsCanEdit4301=true;
		
//		Ext.get('rgstName4301').dom.innerHTML=Ext.get('workerNo').getValue();
//		Ext.get('rgstDate4301').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
//		Ext.get('updtName4301').dom.innerHTML='';
//		Ext.get('updtDate4301').dom.innerHTML='';
	},
	
	undoClick:function()
	{
		Ext.getCmp('form_01_4301').getForm().reset();
		Ext.getCmp('grid_01_4301').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
			new Array('cmbOwnerNo4301','cmbImportType4301','cmbSImportNo4301',
					'cmbSupplierNo4301','cmbCheckWorker4301','dateCheckStartDate4301','cmbCheckTools4301'),
			true);
		commonMenu4Button('menu4301','undo');
		g_IsCanEdit4301=false;
	},
	
	saveClick:function()
	{
		if(!commonCheckIsInputAll('form_01_4301'))
		{
			return;
		}	

		var gridcount=Ext.getCmp("grid_01_4301").getStore().getCount();
		for( var i=0;i<gridcount;i++ ){
			 var data  = Ext.getCmp('grid_01_4301').getStore().getAt(i);
			 
			 if(data.get('checkQty')!=0 && data.get('checkQty')!=undefined &&
			    (data.get('produceDate')==null || data.get('produceDate')==''||
			     data.get('expireDate')==null || data.get('expireDate')==''))
			 {
				 Ext.example.msg($i18n.prompt,'验收数量不为零，生产日期不能是空');
				 return;
			 }
			 
		}
		if(gridcount>0)
		{
			if(!Checkdetailgrid4301('grid_01_4301',0,gridcount-1))
			{
				return;
			}
		}else
		{			
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		
		Ext.Msg.confirm($i18n.prompt,$i18n.saveOrNot,function(button, text) 
		{
			if (button == 'yes') 
			{
				var msgShow = commonMegShow($i18n_prompt.saving_wait);
				var strSImportNo= Ext.getCmp('cmbSImportNo4301').getValue();
				var strDockNo= workSpaceNo;		
				var strCheckWorker= Ext.getCmp('cmbCheckWorker4301').getValue();
				var strOwnerNo=Ext.getCmp('cmbOwnerNo4301').getValue();
				var strCheckTools=Ext.getCmp('cmbCheckTools4301').getValue();
							
				var master=
				{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					SImportNo:strSImportNo,			
					dockNo:strDockNo,				
					checkWorker:strCheckWorker,
					ownerNo:strOwnerNo,
					CheckTools:strCheckTools
				};
							
				var detail1 = [];
				for(var i=0;i<gridcount;i++ )
				{
					var record  = Ext.getCmp('grid_01_4301').getStore().getAt(i);
					var d=
					{		
						id:
						{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:record.get('warehouseNo'),
							checkNo:'',
							ownerNo:record.get('ownerNo'),
							rowId:i
						},
						articleNo:record.get('articleNo'),
						packingQty:record.get('packingQty'),
						qcNo:record.get('qcNo'),
						barcode:record.get('barcode')==''?  'N':record.get('barcode'),
						produceDate:record.get('produceDate')==undefined? '1900-01-01':record.get('produceDate'),
						expireDate:record.get('expireDate')==undefined? '1900-01-01':record.get('expireDate'),
						quality:record.get('quality')==undefined?  '0':record.get('quality'),
						lotNo:record.get('lotNo')==undefined?  'N':record.get('lotNo'),
						rsvBatch1:record.get('rsvBatch1')==undefined?  'N':record.get('rsvBatch1'),
						rsvBatch2:record.get('rsvBatch2')==undefined?  'N':record.get('rsvBatch2'),
						rsvBatch3:record.get('rsvBatch3')==undefined?  'N':record.get('rsvBatch3'),
						rsvBatch4:record.get('rsvBatch4')==undefined?  'N':record.get('rsvBatch4'),
						rsvBatch5:record.get('rsvBatch5')==undefined?  'N':record.get('rsvBatch5'),
						rsvBatch6:record.get('rsvBatch6')==undefined?  'N':record.get('rsvBatch6'),
						rsvBatch7:record.get('rsvBatch7')==undefined?  'N':record.get('rsvBatch7'),
						rsvBatch8:record.get('rsvBatch8')==undefined?  'N':record.get('rsvBatch8'),
						temperature:record.get('temperature')==undefined?  'N':record.get('temperature'),		
						stockType:'',
						stockValue:'',
						deptNo:'',
						checkQty:record.get('checkQty'),
						checkWorker1:'',
						qcWorker:'',
						checkStartDate:'',
						checkEndDate:'',
						iqcStatus:'',
						unloadWorker:'',
						authorizedWorker:'',
						checkWorker2:''
					};
					
					
					
					if(record.get('checkQty')!=0 && record.get('checkQty')!=undefined)
					{
						detail1.push(d);
					}
				}
				if(detail1.length!=0)
				{
					var strJsonMaster = Ext.encode(master);
					var strJsonDetail1 = Ext.encode(detail1);
					var params = 
					{
						strJsonMaster:strJsonMaster,
						strJsonDetail1:strJsonDetail1,
						strFlag:Ext.getCmp('radiogroup4301').getValue().rb
					};
					Ext.Ajax.request({
						method:'POST',
						url:'idata_CheckAction_saveCheck.action',
						params:params,
						success:function(response)
						{
							msgShow.hide();
							var data = Ext.decode(response.responseText);
							if(data.isSucc)
							{
								commonMenu4Button('menu4301','save');
								Ext.getCmp('grid_01_4301').getStore().reload();
								Ext.example.msg($i18n.prompt,data.msg);
								g_IsCanEdit4301=false;
							}else
							{
								Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
							}				
						}
					});
				}else
				{
					msgShow.hide();
					Ext.Msg.alert($i18n.prompt,$i18n_prompt.theAcceptanceOfAnyCommodity);
				}
			}
		});
	},
	
	cmbSImportNo4301Focus:function()
	{
		var strOwnerNo = {
			strOwnerNo : Ext.getCmp('cmbOwnerNo4301').getValue()
		};
		Ext.apply(Ext.getCmp('cmbSImportNo4301').getStore().proxy.extraParams,strOwnerNo);
		Ext.getCmp('cmbSImportNo4301').getStore().removeAll();
		Ext.getCmp('cmbSImportNo4301').getStore().load();
	},
	
	cmbSImportNo4301Select:function(combo)
	{
		var params=
		{
			strSImportNo:combo.getValue()	
		};
		Ext.Ajax.request({
			method:'POST',
			url:'idata_CheckAction_getImportNo.action',
			params:params,
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
			    Ext.getCmp('cmbSupplierNo4301').getStore().add({
			    	value:data[0].supplierNo,
			    	dropValue:'['+data[0].supplierNo+']'+data[0].supplierName,
			    	text:data[0].supplierNo
			    });
			    Ext.getCmp('cmbSupplierNo4301').setValue(data[0].supplierNo);
			    Ext.getCmp('cmbOwnerNo4301').setValue(data[0].ownerNo);
			    Ext.getCmp('cmbImportType4301').setValue(data[0].importType);
			    g_OverQtyFlag = data[0].overQtyFlag;
			}
		});
		
		var strDetail1 = [];
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNo4301').getValue()
		};
		strDetail1.push(d);
		d={
			columnId:'a.s_import_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strWheresql : jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_01_4301').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_4301').getStore().removeAll();
		Ext.getCmp('grid_01_4301').getStore().load();
		Ext.getCmp('cmbOwnerNo4301').setReadOnly(true);
		Ext.getCmp('cmbSImportNo4301').setReadOnly(true);
	},
	
	cmbOwnerNo4301Change:function(combo)
	{
		getSystemPara4301(combo.getValue());
	},

	grid_01_4301Beforeedit:function(editor,e,eOpts)
	{
		if(!g_IsCanEdit4301)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
	    	//直通验收时，生产日期默认为1900-01-01，不需要输入
	    	if(
	    	(commonGetIdataType(Ext.getCmp('cmbOwnerNo4301').getValue(),
	    			Ext.getCmp('cmbImportType4301').getValue(),
			        'class_type')[0].columnValue=='1' 
		        || e.record.data.expiryDays==-1) 
	    	&& (e.field=='produceDate' || e.field=='expireDate'))
	    	{
		        editor.cancel = true;
	        	return  false; 
	    	}
	    	
	    	var data = Ext.getCmp('grid_01_4301').getSelectionModel().getSelection();
	    	
	    	if(data[0].get('lotType')=='1' && (e.field=='produceDate' || e.field=='expireDate')){
	    		editor.cancel = true;
	        	return  false; 
	    	}else if(data[0].get('lotType')=='2'  && (e.field=='lotNo')){
	    		editor.cancel = true;
	        	return  false; 
	    	}else if(data[0].get('lotType')=='4'  && (e.field=='produceDate' || e.field=='expireDate' ||e.field=='lotNo')){
	    		editor.cancel = true;
	        	return  false; 
	    	}
	    	if(data[0].get('temperatureFlag')=='0'&& (e.field=='temperature')){
	    		editor.cancel = true;
	        	return  false; 
	    	}
	    	
	    }
		
		if(e.field=='quality'){
			var str = {
					strOwnerNo : Ext.getCmp('cmbOwnerNo4301').getValue(),
					str : Ext.getCmp('cmbImportType4301').getValue()
			};
			Ext.apply(Ext.getCmp('quality4301').getStore().proxy.extraParams,str);
			Ext.getCmp('quality4301').getStore().removeAll();
			Ext.getCmp('quality4301').getStore().load();
		}
	},
	
	grid_01_4301edit:function(editor,e,eOpts)
	{
		var data = Ext.getCmp('grid_01_4301').getSelectionModel().getSelection();

		if(e.field=='produceDate')
		{
			if(!Ext.isEmpty(e.value))
			{
				if(Ext.Date.format(e.value,'Y-m-d')>Ext.Date.format(new Date(),'Y-m-d'))
				{
					editor.context.record.set('produceDate',editor.context.originalValue);
					Ext.example.msg($i18n.prompt,$i18n_prompt.productTimeCannotMoreThanToday);
					return;
				}
				if(checkIsRepeat4301(editor,e))
				{
					if(e.record.data.expiryDays!=0)
					{
						if(e.record.data.expiryDays>0)
						{
							editor.context.record.set('expireDate',null);
							
							editor.context.record.set('expireDate',Ext.Date.format
									(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays),'Y-m-d'));
							var data = Ext.getCmp('grid_01_4301').getSelectionModel().getSelection();
							if(data[0].get('lotType')=='3'){
							}else{
								editor.context.record.set('lotNo','N');
							}
							checkDate4301(e,editor);
						}
					}
				}
			}
		}else if(e.field=='expireDate')
		{
			if(!Ext.isEmpty(e.value))
			{
				if(checkIsRepeat4301(editor,e))
				{
					if(e.record.data.expiryDays!=0)	
					{
						if(e.record.data.expiryDays>0){
							if(Ext.Date.format(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays*-1),'Y-m-d')
								>Ext.Date.format(new Date(),'Y-m-d'))
							{
								editor.context.record.set('expireDate',editor.context.originalValue);
								editor.context.record.set('produceDate',null);
								Ext.example.msg($i18n.prompt, $i18n_prompt.productTimeCannotMoreThanToday);
							}else{
								editor.context.record.set('produceDate',Ext.Date.format
										(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays*-1),'Y-m-d'));
								checkDate4301(e,editor);
							}
						}
					}
				}
			}
		}else if(e.field=='planBox' || e.field=='planQmin' ||  e.field=='planDis'|| e.field=='checkQty')
		{
			if(!checkQty4301(e.record.data.articleNo))
			{
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='planBox'){
					editor.context.record.set('checkQty',editor.context.originalValue*e.record.data.packingQty+e.record.data.planQmin*e.record.data.qminOperatePacking+e.record.data.planDis);
				}else if(e.field=='planQmin'){
					editor.context.record.set('checkQty',editor.context.originalValue*e.record.data.qminOperatePacking+e.record.data.packingQty*e.record.data.planBox+e.record.data.planDis);
				}else if(e.field=='planDis'){
					editor.context.record.set('checkQty',editor.context.originalValue+e.record.data.planQmin*e.record.data.qminOperatePacking+e.record.data.packingQty*e.record.data.planBox);
				}
			}else
			{
				if(e.field=='planBox')
				{
					if(Ext.isEmpty(e.value))
					{
						editor.context.record.set('planBox',0);
					}
					if(g_ICCheckPickCell4301==1){
						if(!Ext.isEmpty(e.value))
						{
							if(e.value>0 || e.record.data.planQmin>0 || e.record.data.planDis>0)
							{
								if(e.record.data.boxPickType=='' && e.record.data.disPickType=='')
								{
									editor.context.record.set('planBox',0);
									editor.context.record.set('planQmin',0);
									editor.context.record.set('planDis',0);
									editor.context.record.set('checkQty',0);
									Ext.example.msg($i18n.prompt,$i18n_prompt.notSetPickLocateAndNotAllowAcceptance);
								}
							}
						}
					}
					if(g_ICCheckQpalette4301==1)
					{
						if(!Ext.isEmpty(e.value))
						{
							if(e.value>0 || e.record.data.planQmin>0 || e.record.data.planDis>0)
							{
								if(e.record.data.qpalette==0)
								{
									editor.context.record.set('planBox',0);
									editor.context.record.set('planQmin',0);
									editor.context.record.set('planDis',0);
									editor.context.record.set('checkQty',0);
									Ext.example.msg($i18n.prompt, $i18n_prompt.notSetStandardsStackNotAllowAcceptance);
								}
							}
						}
					}
				}else if(e.field=='planQmin')
				{
					if(Ext.isEmpty(e.value))
					{
						editor.context.record.set('planQmin',0);
					}
					if(g_ICCheckPickCell4301==1){
						if(!Ext.isEmpty(e.value)){
							if(e.value>0 || e.record.data.planBox>0 || e.record.data.planDis>0)
							{
								if(e.record.data.boxPickType=='' && e.record.data.disPickType=='')
								{
									editor.context.record.set('planBox',0);
									editor.context.record.set('planQmin',0);
									editor.context.record.set('planDis',0);
									editor.context.record.set('checkQty',0);
									Ext.example.msg($i18n.prompt,$i18n_prompt.notSetPickLocateAndNotAllowAcceptance);
								}
							}
						}
					}
					if(g_ICCheckQpalette4301==1)
					{
						if(!Ext.isEmpty(e.value))
						{
							if(e.value>0 || e.record.data.planBox>0 || e.record.data.planDis>0)
							{
								if(e.record.data.qpalette==0)
								{
									editor.context.record.set('planBox',0);
									editor.context.record.set('planQmin',0);
									editor.context.record.set('planDis',0);
									editor.context.record.set('checkQty',0);
									Ext.example.msg($i18n.prompt, $i18n_prompt.notSetStandardsStackNotAllowAcceptance);
								}
							}
						}
					}
				}else if(e.field=='planDis')
				{
					if(Ext.isEmpty(e.value))
					{
						editor.context.record.set('planDis',0);
					}
					if(g_ICCheckPickCell4301==1){
						if(!Ext.isEmpty(e.value)){
							if(e.value>0 || e.record.data.planBox>0 || e.record.data.planQmin>0)
							{
								if(e.record.data.boxPickType=='' && e.record.data.disPickType=='')
								{
									editor.context.record.set('planBox',0);
									editor.context.record.set('planQmin',0);
									editor.context.record.set('planDis',0);
									editor.context.record.set('checkQty',0);
									Ext.example.msg($i18n.prompt,$i18n_prompt.notSetPickLocateAndNotAllowAcceptance);
								}
							}
						}
					}
					if(g_ICCheckQpalette4301==1)
					{
						if(!Ext.isEmpty(e.value))
						{
							if(e.value>0 || e.record.data.planBox>0 || e.record.data.planQmin>0)
							{
								if(e.record.data.qpalette==0)
								{
									editor.context.record.set('planBox',0);
									editor.context.record.set('planQmin',0);
									editor.context.record.set('planDis',0);
									editor.context.record.set('checkQty',0);
									Ext.example.msg($i18n.prompt, $i18n_prompt.notSetStandardsStackNotAllowAcceptance);
								}
							}
						}
					}
				}
			}
		}else if(e.field=='lotNo')
		{
			if(!Ext.isEmpty(e.value))
			{
				checkIsRepeat4301(editor,e);
			}
		}else if(e.field=='quality')
		{
			if(!Ext.isEmpty(e.value))
			{
				checkIsRepeat4301(editor,e);
			}
		}
	},

	boxkeydown:function(th,e,eOpts)
	{
		if (e.getKey() == e.ENTER) 
		{
			if(th.id=="cmbSImportNo4301")
	  		{
	  			Ext.getCmp('cmbCheckWorker4301').focus();
	  		}else
  			{
	  			th.nextSibling().focus();
  			}
			
        }
	},
	
	actioncolumnClick:function(grid, rowIndex, colIndex)
	{
		var data = grid.getStore().getAt(colIndex);
		if(g_IsCanEdit4301)
	    {	//不管保质期的不允许拆笔
	    	if(data.data.expiryDays!=-1){	    	    
		    	Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureToSplit, function(button, text) {
				if (button == 'yes') {
					var r = Ext.create('cms.model.idata.idata_ImportSdModel', {
					});
					r.set('articleNo',data.data.articleNo);
					r.set('articleName',data.data.articleName);	
					r.set('ownerArticleNo',data.data.ownerArticleNo);
					r.set('barcode',data.data.barcode);
					r.set('packingQty',data.data.packingQty);
					r.set('packingSpec',data.data.packingSpec);	
					r.set('importQty',data.data.importQty);
					r.set('labelNo',data.data.labelNo);
					r.set('cellNo',data.data.cellNo);
					r.set('checkQty',0);
					r.set('planBox',0);
					r.set('planQmin',0);
					r.set('planDis',0);
					r.set('quality',data.data.quality);
					r.set('expiryDays',data.data.expiryDays);
					r.set('rsvBatch1',data.data.rsvBatch1);
					r.set('rsvBatch2',data.data.rsvBatch2);
					r.set('rsvBatch3',data.data.rsvBatch3);
					r.set('rsvBatch4',data.data.rsvBatch4);
					r.set('rsvBatch5',data.data.rsvBatch5);
					r.set('rsvBatch6',data.data.rsvBatch6);
					r.set('rsvBatch7',data.data.rsvBatch7);
					r.set('rsvBatch8',data.data.rsvBatch8);
					r.set('rowId',data.data.rowId);	
					r.set('wareArea',data.data.wareArea);
					r.set('packingUnit',data.data.packingUnit);
					r.set('inQty',data.data.inQty);
					Ext.getCmp('grid_01_4301').store.insert(colIndex+1,r);
					
					for(var i=1;i<=Ext.getCmp('grid_01_4301').getStore().getCount();i++ ){
						Ext.getCmp('grid_01_4301').getStore().getAt(i-1).set('rowId',i);
						Ext.getCmp('grid_01_4301').getStore().getAt(i-1).index=i;
					}
				}
				});
	    	}
	    }
	},
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts){
		if(newCard.itemId=='tabPId4301i'){
			bdef_RepairPrint = Ext.create('cms.view.common.bdef_RepairPrint');
			bdef_RepairPrint.items.items[3].items.items[0].getStore().removeAll();
			bdef_RepairPrint.items.items[3].items.items[1].getStore().removeAll();
			bdef_RepairPrint.items.items[4].items.items[0].getStore().removeAll();
			bdef_RepairPrint.items.items[4].items.items[1].getStore().removeAll();
			Ext.getCmp('tabPId4301i').add(bdef_RepairPrint);
			Ext.getCmp('tabPId4301i').doLayout();
			queryModuleId='4301';
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
						getQueryPanel = Ext.create('cms.view.common.reportQueryPanel');
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
	}
});

/**
 * 校验报警天数和冻结比率
 * @param e
 */
function checkDate4301(e,editor){
	var intProduce=Math.round(//计算输入的日期与今天的日期的相隔天数
			new Date(Ext.Date.format(e.value,'Y-m-d')).getTime()
			-new Date(Ext.Date.format(new Date(),'Y-m-d')).getTime())
			/86400000*-1;
	var intFreezeRate=e.record.data.expiryDays*e.record.data.freezerate*0.01;//计算冻结比率的天数
	var intICAlarmRate=e.record.data.expiryDays*e.record.data.alarmrate*0.01;//计算报警比率的天数
	if(g_ICAlarmRate4301==1)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			editor.context.record.set('produceDate',null);
			editor.context.record.set('expireDate',null);
			Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToFreezeRate);
		}else
		{
			if(g_ICFreezeRate4301==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					editor.context.record.set('produceDate',null);
					editor.context.record.set('expireDate',null);
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}else if(g_ICFreezeRate4301==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}
		}
	}else if(g_ICAlarmRate4301==2)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToFreezeRate);
		}else
		{
			if(g_ICFreezeRate4301==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					editor.context.record.set('produceDate',null);
					editor.context.record.set('expireDate',null);
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}else if(g_ICFreezeRate4301==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}
		}
	}
}
/**
 * 校验商品是否超量
 * @param articleNo
 * @returns {Boolean}
 */
function checkQty4301(articleNo){
	var store=Ext.getCmp('grid_01_4301').getStore().queryBy(function(record) {  
		return record.get('articleNo') ==articleNo; 
	});
	var checkQty=0;
	for(var i=0;i<store.length;i++){
		checkQty+=store.items[i].get('checkQty');
	}
	
	if(store.items[0].get('noCheckQty')-checkQty<0)
	{
		if(g_OverQtyFlag == "0"){
			Ext.example.msg($i18n.prompt, "该单据类型不允许超量");
			return false;
		}
		Ext.example.msg($i18n.prompt,$i18n_prompt.theExcessValueOfCommodity+store.items[0].get('checkExcess')+"%"+$i18n_prompt.theAcceptanceRatioHasExceededOverInputAgain);
		return false;
	}
	return true;
}

function Checkdetailgrid4301(gridid,beginindex,endindex){
	var grid=Ext.getCmp(gridid);
	for ( var i =beginindex; i <=endindex; i++) {
		var record = grid.getStore().getAt(i);
		for(var j=0;j<grid.columns.length;j++)
		{
			if(grid.columns[j].cls==="notnull" && !grid.columns[j].hidden)
			{
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[j].dataIndex)))
					{
						Ext.example.msg($i18n.prompt,$i18n.input+grid.columns[j].text+'！');
						return false;
					}
			}
		}					
	}
	return true;
}

/**
 * 根据货主获取相应的系统参数
 * @param strOwnerNo
 */
function getSystemPara4301(strOwnerNo)
{
	g_ICAcrossAreaBox4301
		=commonGetSystemParams(strOwnerNo,'IC_AcrossAreaBox','I','IC')[0].sdefine;//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
	g_ICFreezeRate4301
		=commonGetSystemParams(strOwnerNo,'IC_FreezeRate','I','IC')[0].sdefine;//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICAlarmRate4301
		=commonGetSystemParams(strOwnerNo,'IC_AlarmRate','I','IC')[0].sdefine;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICCheckPickCell4301
		=commonGetSystemParams(strOwnerNo,'IC_CheckPickCell','I','IC')[0].sdefine;//存储验收时是否需要校验拣货位;0----不校验；1----校验
	g_ICCheckQpalette4301
		=commonGetSystemParams(strOwnerNo,'IC_CheckQpalette','I','IC')[0].sdefine;//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
}

/*
 * 检查数据是否重复
 */
function checkIsRepeat4301(editor,e)
{
	if(!Ext.isEmpty(e.value))
	{	
		if(!Ext.isEmpty(e.record.data.produceDate)
		&&!Ext.isEmpty(e.record.data.expireDate)
		&&!Ext.isEmpty(e.record.data.lotNo)
		&&!Ext.isEmpty(e.record.data.quality))
		{
			if(editor.grid.getStore().findBy(function(record, id)
			{  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('articleNo')==editor.context.record.data.articleNo
					&& record.get('packingQty')==editor.context.record.data.packingQty
					&& Ext.Date.format(new Date(record.get('produceDate')),'Y-m-d') 
					==Ext.Date.format(new Date(editor.context.record.data.produceDate),'Y-m-d')
					&& record.get('lotNo')==editor.context.record.data.lotNo
					&& record.get('quality')==editor.context.record.data.quality;
			})!=-1)				
			{
				editor.context.record.set(e.field,editor.context.originalValue);
				Ext.example.msg($i18n.prompt, $i18n.notTheSame);
				return false;
			}		
		}
	}
	return true;
}
