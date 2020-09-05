/**
 * 模块名称：固定板人工封板
 * 模块编码：4401
 * 创建：Jun
 */
var g_ICAcrossAreaBox4401='';//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
var g_ICFreezeRate4401='';//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICAlarmRate4401='';//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICCheckPickCell4401='';//存储验收时是否需要校验拣货位;0----不校验；1----校验
var g_ICCheckQpalette4401='';//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
var g_IsCanEdit4401=false;
var g_OverQtyFlag = '0';//是否超量验收0:不超量；1：超量；2：超品 huangb 20160726
Ext.define('cms.controller.idata.idata_FixedArtificialCloseController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.idata.idata_FixedArtificialCloseUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//新增
			'idata_FixedArtificialCloseUI button[name=add]':{
				click:this.addClick
			},//撤销
			'idata_FixedArtificialCloseUI button[name=undo]':{
				click:this.undoClick
			},//保存
			'idata_FixedArtificialCloseUI button[name=save]':{
				click:this.saveClick
			},//进货汇总单选择
			'idata_FixedArtificialCloseUI remoteCombo[id=cmbSImportNo4401]':
			{
				focus:this.cmbSImportNo4401Focus,
				select:this.cmbSImportNo4401Select
			},//进货单号选择
			/*'idata_FixedArtificialCloseUI remoteCombo[id=cmbImportNo4401]':
			{
				select:this.cmbImportNo4401Select
			},*/
			//货主下拉选择
			'idata_FixedArtificialCloseUI bdef_DefOwnerCombo[id=cmbOwnerNo4401]':
			{
				change:this.cmbOwnerNo4401Change
			},//验收网格编辑
			'idata_FixedArtificialCloseUI grid[id=grid_01_4401]':{
				beforeedit:this.grid_01_4401Beforeedit,
				edit:this.grid_01_4401edit
			},
			'idata_FixedArtificialCloseUI checkboxfield[id=cbFixedCell4401]':{
				change:this.cbFixedCell4401Change
			},
			'idata_FixedArtificialCloseUI field':{
				specialkey:this.boxkeydown
			},
			'idata_FixedArtificialCloseUI grid[id=grid_01_4401] actioncolumn':{
				click:this.actioncolumnClick
			}
			
		});
	},
	
	initializtion:function()
	{
		//显示变量0为不显示，1为显示
		var planBox4401=commonGetModuleField('4401','planBox')[0].flag;
		var planQmin4401=commonGetModuleField('4401','planQmin')[0].flag;
		var planDis4401=commonGetModuleField('4401','planDis')[0].flag;
		var packingUnit4401=commonGetModuleField('4401','packingUnit')[0].flag;
		var packingSpec4401=commonGetModuleField('4401','packingSpec')[0].flag;
		
		if(planBox4401==0){
			Ext.getCmp('planBox4401').setVisible(false);
		}
		if(planQmin4401==0){
			Ext.getCmp('planQmin4401').setVisible(false);
		}
		if(planDis4401==0){
			Ext.getCmp('planDis4401').setVisible(false);
		}
		if(packingUnit4401==0){
			Ext.getCmp('packingUnit4401').setVisible(false);
		}
		if(packingSpec4401==0){
			Ext.getCmp('packingSpec4401').setVisible(false);
		}
		
	},
	
	addClick:function()
	{
		Ext.getCmp('form_01_4401').getForm().reset();
		Ext.getCmp('grid_01_4401').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
		new Array('cmbOwnerNo4401','cmbSImportNo4401','cmbCheckWorker4401'),
		false);
		if(Ext.getCmp('cmbOwnerNo4401').getStore().data.length>0)
		{
			Ext.getCmp('cmbOwnerNo4401').setValue(Ext.getCmp('cmbOwnerNo4401').getStore().getAt(0).data.value);		
		}
//		Ext.getCmp('cmbImportType4401').setValue('IS');
		Ext.getCmp('cmbStatus4401').setValue('10');
		Ext.getCmp('dateCheckStartDate4401').setValue(new Date());
		Ext.getCmp('cmbCheckTools4401').setValue('1');
		commonMenu4Button('menu4401','add');
		Ext.getCmp('cmbSImportNo4401').focus();
		g_IsCanEdit4401=true;
		
		Ext.get('rgstName4401').dom.innerHTML=Ext.get('workerNo').getValue();//'admin';
		Ext.get('rgstDate4401').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
		Ext.get('updtName4401').dom.innerHTML='';
		Ext.get('updtDate4401').dom.innerHTML='';
	},
	
	undoClick:function()
	{
		Ext.getCmp('form_01_4401').getForm().reset();
		Ext.getCmp('grid_01_4401').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
			new Array('cmbOwnerNo4401','cmbImportType4401','cmbSImportNo4401','cmbSupplierNo4401',
					'cmbCheckWorker4401','dateCheckStartDate4401','cmbCheckTools4401'),
			true);
		commonMenu4Button('menu4401','undo');
		g_IsCanEdit4401=false;
	},
	
	saveClick:function()
	{
		if(!commonCheckIsInputAll('form_01_4401'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_01_4401").getStore().getCount();
		if(gridcount>0)
		{
			if(!Checkdetailgrid4401('grid_01_4401',0,gridcount-1))
			{
				return;
			}
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		Ext.Msg.confirm("提示", "确定保存？",function(button, text) 
		{
			if (button == 'yes') 
			{
				var msgShow = commonMegShow("正在保存,请稍等...");
				var strSImportNo= Ext.getCmp('cmbSImportNo4401').getValue();
				var strDockNo= workSpaceNo;		
				var strCheckWorker= Ext.getCmp('cmbCheckWorker4401').getValue();
				var strOwnerNo=Ext.getCmp('cmbOwnerNo4401').getValue();
				var strCheckTools=Ext.getCmp('cmbCheckTools4401').getValue();
							
				var master=
				{
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
					var record  = Ext.getCmp('grid_01_4401').getStore().getAt(i);
					var d=
					{		
						id:
						{
							warehouseNo:record.get('warehouseNo'),
							checkNo:'',
							ownerNo:record.get('ownerNo'),
							rowId:i
						},
						articleNo:record.get('articleNo'),
						packingQty:record.get('packingQty'),
						qcNo:record.get('qcNo'),
						barcode:record.get('barcode'),
						produceDate:record.get('produceDate')==undefined?  '1900-01-01':record.get('produceDate'),
						expireDate:record.get('expireDate')==undefined?  '1900-01-01':record.get('expireDate'),
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
						cellNo:record.get('cellNo'),
						labelNo:record.get('labelNo'),
						subLabelNo:record.get('labelNo'),
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
						strJsonDetail1:strJsonDetail1
					};
					Ext.Ajax.request({
						method:'POST',
						url:'idata_CheckAction_saveCheck2.action',
						params:params,
						success:function(response)
						{
							msgShow.hide();
							var data = Ext.decode(response.responseText);
							if(data.isSucc)
							{
								commonMenu4Button('menu4401','save');
								Ext.example.msg('提示',data.msg);
							}else
							{
								Ext.Msg.alert('提示',data.msg+data.obj);
							}				
						}
					});
				}else
				{
					msgShow.hide();
					Ext.Msg.alert('提示','此次没有验收任何商品');
				}
				
			}
		});
	},
	
	cmbSImportNo4401Focus:function()
	{
		var strOwnerNo = {
			strOwnerNo : Ext.getCmp('cmbOwnerNo4401').getValue()
		};
		Ext.apply(Ext.getCmp('cmbSImportNo4401').getStore().proxy.extraParams,strOwnerNo);
		Ext.getCmp('cmbSImportNo4401').getStore().removeAll();
		Ext.getCmp('cmbSImportNo4401').getStore().load();
	},
	
	cmbSImportNo4401Select:function(combo)
	{
		var params=
		{
			strOwnerNo:Ext.getCmp('cmbOwnerNo4401').getValue(),
			strSImportNo:combo.getValue()	
		};
		Ext.Ajax.request({
			method:'POST',
			url:'idata_CheckAction_getImportNo.action',
			params:params,
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				/*Ext.getCmp('cmbImportNo4401').getStore().add({
			    	value:data[0].importNo,
			    	dropValue:data[0].importNo,
			    	text:data[0].importNo
			    });
			    Ext.getCmp('cmbImportNo4401').setValue(data[0].importNo);*/
			    
			    Ext.getCmp('cmbSupplierNo4401').getStore().add({
			    	value:data[0].supplierNo,
			    	dropValue:'['+data[0].supplierNo+']'+data[0].supplierName,
			    	text:data[0].supplierNo
			    });
			    Ext.getCmp('cmbSupplierNo4401').setValue(data[0].supplierNo);
			    Ext.getCmp('cmbOwnerNo4401').setValue(data[0].ownerNo);
			    Ext.getCmp('cmbImportType4401').setValue(data[0].importType);
			    g_OverQtyFlag = data[0].overQtyFlag;
			}
		});
		var strDetail1 = [];
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNo4401').getValue()
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
		Ext.apply(Ext.getCmp('grid_01_4401').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_4401').getStore().removeAll();
		Ext.getCmp('grid_01_4401').getStore().load();
		Ext.getCmp('cmbOwnerNo4401').setReadOnly(true);
		Ext.getCmp('cmbSImportNo4401').setReadOnly(true);
	},
	
	cmbImportNo4401Select:function(combo){
		var params=
		{
			strImportNo:combo.getValue()	
		};
		Ext.Ajax.request({
			method:'POST',
			url:'idata_CheckAction_getSImportNo.action',
			async:false,
			params:params,
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				Ext.getCmp('cmbSImportNo4401').getStore().add({
			    	value:data,
			    	dropValue:data,
			    	text:data
			    });
			    Ext.getCmp('cmbSImportNo4401').setValue(data);
			}
		});
		
		var strWheresql = 
		{
			strWheresql:Ext.getCmp('cmbSImportNo4401').getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_4401').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_4401').getStore().removeAll();
		Ext.getCmp('grid_01_4401').getStore().load();
		Ext.getCmp('cmbOwnerNo4401').setReadOnly(true);
	},
	
	cmbOwnerNo4401Change:function(combo)
	{
		getSystemPara4401(combo.getValue());
	},

	grid_01_4401Beforeedit:function(editor,e,eOpts)
	{
		if(!g_IsCanEdit4401)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
	    	//直通验收时，生产日期默认为1900-01-01，不需要输入
	    	if(
	    	(/*Ext.getCmp('cmbSImportNo4401').getValue().substring(0,2)=='SD' 
	    		|| e.record.data.expiryDays==-1*/
    			commonGetIdataType(Ext.getCmp('cmbOwnerNo4401').getValue(),
    	    			Ext.getCmp('cmbImportType4401').getValue(),
    			        'class_type')[0].columnValue=='1' 
    		        || e.record.data.expiryDays==-1) 
	    	&& (e.field=='produceDate' || e.field=='expireDate'))
	    	{
		        editor.cancel = true;
	        	return  false; 
	    	}
	    	else if((e.record.data.lotNo=='N' ) && (e.field=='lotNo'))
	    	{
	    		editor.cancel = true;
	        	return  false; 
	    	}
	    }
	},
	
	grid_01_4401edit:function(editor,e,eOpts)
	{
		if(e.field=='produceDate')
		{
			if(!Ext.isEmpty(e.value))
			{
				if(e.record.data.expiryDays!=0){
					if(editor.grid.getStore().findBy(function(record, id) 
					{  
							return record.internalId!=editor.context.record.internalId 
							&& record.get('articleNo')==editor.context.record.data.articleNo
							&& record.get('packingQty')==editor.context.record.data.packingQty
							&& Ext.Date.format(new Date(record.get('produceDate')),'Y-m-d') 
							==Ext.Date.format(new Date(editor.context.record.data.produceDate),'Y-m-d');
						})!=-1)				
					{
						editor.context.record.set('produceDate',editor.context.originalValue);
						Ext.example.msg('提示', "【商品编码】、【生产日期】不能重复，请重新输入！");
					}else
					{
						if(Ext.Date.format(e.value,'Y-m-d')>Ext.Date.format(new Date(),'Y-m-d'))
						{
							editor.context.record.set('produceDate',editor.context.originalValue);
							Ext.example.msg('提示', "生产日期不能大于今天");
						}
						
						if(e.record.data.expiryDays==-1)
						{
							
						}else if(e.record.data.expiryDays==0)
						{
							
						}else if(e.record.data.expiryDays>0)
						{
							editor.context.record.set('expireDate',
									Ext.Date.format(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays),'Y-m-d'));
							checkDate4401(e,editor);
						}
					}
				}
			}
		}else if(e.field=='expireDate')
		{
			if(!Ext.isEmpty(e.value))
			{
				if(e.record.data.expiryDays!=0)
				{
					if(editor.grid.getStore().findBy(function(record, id) 
					{  
							return record.internalId!=editor.context.record.internalId 
							&& record.get('articleNo')==editor.context.record.data.articleNo
							&& record.get('packingQty')==editor.context.record.data.packingQty
							&& Ext.Date.format(new Date(record.get('expireDate')),'Y-m-d') ==Ext.Date.format(new Date(editor.context.record.data.expireDate),'Y-m-d');
						})!=-1)				
					{
						editor.context.record.set('expireDate',editor.context.originalValue);
						Ext.example.msg('提示', "【商品编码】、【生产日期】不能重复，请重新输入！");
					}else
					{
						
						if(e.record.data.expiryDays==-1)
						{
							
						}else if(e.record.data.expiryDays==0)
						{
							
						}else if(e.record.data.expiryDays>0)
						{
							editor.context.record.set('produceDate',Ext.Date.format
									(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays*-1),'Y-m-d'));
							checkDate4401(e,editor);
						}
						
						if(Ext.Date.format(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays*-1),'Y-m-d')
								>Ext.Date.format(new Date(),'Y-m-d'))
						{
							editor.context.record.set('expireDate',editor.context.originalValue);
							editor.context.record.set('produceDate',null);
							Ext.example.msg('提示', "生产日期不能大于今天");
						}
					}
				}
			}
		}else if(e.field=='planBox' || e.field=='planQmin' ||  e.field=='planDis'|| e.field=='checkQty')
		{
			if(!checkQty4401(e.record.data.articleNo))
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
					checkLabelNo4401(editor,e);
					if(g_ICCheckPickCell4401==1){
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
									Ext.example.msg('提示', "商品未设拣货位 不允许验收！");
								}
							}
						}
					}
					if(g_ICCheckQpalette4401==1)
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
									Ext.example.msg('提示', "商品未设标准堆叠 不允许验收！");
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
					if(g_ICCheckPickCell4401==1){
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
					if(g_ICCheckQpalette4401==1)
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
					if(g_ICCheckPickCell4401==1){
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
					if(g_ICCheckQpalette4401==1)
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
				}else if(e.field=='checkQty')
				{
					checkLabelNo4401(editor,e);
				}
			}
		}else if(e.field=='lotNo')
		{
			var params=
			{
				strLotNo:e.value,
				strArticleNo:e.record.data.articleNo
			};
			Ext.Ajax.request({
				method:'POST',
				url:'idata_CheckAction_queryLotProduceDate.action',
				params:params,
				success:function(response)
				{
					var data = Ext.decode(response.responseText);
					if(data!=''){
						editor.context.record.set('produceDate',data[0].produceDate);
						editor.context.record.set('expireDate',data[0].expireDate);
					}
				}
			});
		}else if(e.field=='labelNo')
		{
			var params=
			{
				strLabelNo:e.value
			};
			Ext.Ajax.request({
				method:'POST',
				url:'idata_CheckAction_IdataCheckLabelNo.action',
				params:params,
				success:function(response)
				{
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,"该板号不可用");
						editor.context.record.set('labelNo',null);
					}
				}
			});
			checkLabelNo4401(editor,e);
		}else if(e.field=='cellNo')
		{
			for(var i=editor.context.record.index;i<Ext.getCmp('grid_01_4401').getStore().data.length;i++)
			{
				Ext.getCmp('grid_01_4401').getStore().getAt(i).set('cellNo',e.value);
			}
		}
	},
	
	cbFixedCell4401Change:function(check)
	{
		if(check.getValue()==true){
			Ext.getCmp('colCellNo4401').setVisible(true);
			for(var i=0;i<Ext.getCmp('grid_01_4401').getStore().totalCount;i++)
			{
				Ext.getCmp('grid_01_4401').getStore().data.items[i].set('cellNo',null);
			}
		}else{
			Ext.getCmp('colCellNo4401').setVisible(false);
			for(var i=0;i<Ext.getCmp('grid_01_4401').getStore().totalCount;i++)
			{
				Ext.getCmp('grid_01_4401').getStore().data.items[i].set('cellNo','N');
			}
		}
	},
	
	boxkeydown:function(th,e,eOpts)
	{
		if (e.getKey() == e.ENTER) 
		{
			//th.nextSibling().focus();
			if(th.id=="cmbSImportNo4401")
	  		{
	  			Ext.getCmp('cmbSupplierNo4401').focus();
	  		}else
  			{
	  			th.nextSibling().focus();
  			}
        }
	},
	
	actioncolumnClick:function(grid, rowIndex, colIndex)
	{
		var data = grid.getStore().getAt(colIndex);
		if(g_IsCanEdit4401)
	    {	  	    
	    	Ext.Msg.confirm("提示", "确定拆笔？", function(button, text) 
			{
				if (button == 'yes') 
				{
					var r = Ext.create('cms.model.idata.idata_ImportSdModel', {
					});
					r.set('articleNo',data.data.articleNo);
					r.set('articleName',data.data.articleName);	
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
					r.set('lotNo',data.data.lotNo);
					r.set('produceDate',data.data.produceDate);
					r.set('expireDate',data.data.expireDate);
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
					//Ext.getCmp('grid_01_4401').store.insert(colIndex+1,r);
					Ext.getCmp('grid_01_4401').store.insert(colIndex+1,r);
					for(var i=1;i<=Ext.getCmp('grid_01_4401').getStore().getCount();i++ )
					{
						Ext.getCmp('grid_01_4401').getStore().getAt(i-1).set('rowId',i);
						Ext.getCmp('grid_01_4401').getStore().getAt(i-1).index=i;
					}
					//Ext.getCmp('grid_01_4401').getView().refresh();
				}
			});
	    }
	}
});

/**
 * 校验验收是否超量
 * @param articleNo
 * @returns {Boolean}
 */
function checkQty4401(articleNo)
{
	var store=Ext.getCmp('grid_01_4401').getStore().queryBy(function(record){  
		return record.get('articleNo') ==articleNo; 
	});
	var checkQty=0;
	for(var i=0;i<store.length;i++)
	{
		checkQty+=store.items[i].get('checkQty');
	}
	if(store.items[0].get('noCheckQty')-checkQty<0){
		if(g_OverQtyFlag == "0"){
			Ext.example.msg($i18n.prompt, "该单据类型不允许超量");
			return false;
		}
	}
	if(store.items[0].get('checkExcess')*store.items[0].get('noCheckQty')*0.01+store.items[0].get('noCheckQty')-checkQty<0)
	{
		Ext.example.msg($i18n.prompt,"该商品超量值为:"+store.items[0].get('checkExcess')+"%"+" 已超过验收超量比率,请重新输入");
		return false;
	}
	return true;
}

/**
 * 校验报警天数和冻结天数
 * @param e
 */
function checkDate4401(e,editor)
{
	var intProduce=Math.round(//计算输入的日期与今天的日期的相隔天数
			new Date(Ext.Date.format(e.value,'Y-m-d')).getTime()
			-new Date(Ext.Date.format(new Date(),'Y-m-d')).getTime())
			/86400000*-1;
	var intFreezeRate=e.record.data.expiryDays*e.record.data.freezerate*0.01;//计算冻结比率的天数
	var intICAlarmRate=e.record.data.expiryDays*e.record.data.alarmrate*0.01;//计算报警比率的天数
	
	if(g_ICAlarmRate4401==1)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			editor.context.record.set('produceDate',null);
			editor.context.record.set('expireDate',null);
			Ext.example.msg('提示', "商品己到冻结比率！");
		}else
		{
			if(g_ICFreezeRate4401==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					editor.context.record.set('produceDate',null);
					editor.context.record.set('expireDate',null);
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}else if(g_ICFreezeRate4401==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}
		}
	}else if(g_ICAlarmRate4401==2)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			Ext.example.msg('提示', "商品己到冻结比率！");
		}else
		{
			if(g_ICFreezeRate4401==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					editor.context.record.set('produceDate',null);
					editor.context.record.set('expireDate',null);
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}else if(g_ICFreezeRate4401==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}
		}
	}
}

/**
 * 不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许
 * @param e
 */
function checkLabelNo4401(editor,e)
{
	if(Ext.isEmpty(e.record.data.labelNo))
	{
		return;
	}
	if(g_ICAcrossAreaBox4401==0)
	{
		if(parseInt(e.record.data.checkQty)%parseInt(e.record.data.packingQty)!=1)
		{
			var objData=editor.grid.getStore().query('labelNo',e.record.data.labelNo);
			for(var i=0;i<objData.length;i++){
				//console.log(objData.items[i].data.wareArea);
				if(objData.items[i].data.wareArea!=objData.items[0].data.wareArea)
				{
					objData.items[i].set('labelNo',null);
					Ext.example.msg($i18n.prompt,"不同储区的商品不能放在同一物流箱上");
				}
			}
		}
	}
}


function Checkdetailgrid4401(gridid,beginindex,endindex){
	var grid=Ext.getCmp(gridid);
	for ( var i =beginindex; i <=endindex; i++) {
		var record = grid.getStore().getAt(i);
		for(var j=0;j<grid.columns.length;j++)
		{
			if(grid.columns[j].cls==="notnull")
			{
				if(grid.getStore().getAt(i).get(grid.columns[8].dataIndex)=='0' 
					|| grid.getStore().getAt(i).get(grid.columns[8].dataIndex)==undefined)
				{
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[j].dataIndex)))
					{
						if(j==9 || j==10 || j==11 || j==12 || j==13 || j==14 || j==15)
						{
						}else{
							Ext.example.msg('提示','请输入'+grid.columns[j].text+'！');
							return false;
						}
					}
				}else
				{
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[j].dataIndex)))
					{
						Ext.example.msg('提示','请输入'+grid.columns[j].text+'！');
						return false;
					}
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
function getSystemPara4401(strOwnerNo)
{
	g_ICAcrossAreaBox4401
		=commonGetSystemParams(strOwnerNo,'IC_AcrossAreaBox','I','IC')[0].sdefine;//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
	g_ICFreezeRate4401
		=commonGetSystemParams(strOwnerNo,'IC_FreezeRate','I','IC')[0].sdefine;//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICAlarmRate4401
		=commonGetSystemParams(strOwnerNo,'IC_AlarmRate','I','IC')[0].sdefine;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICCheckPickCell4401
		=commonGetSystemParams(strOwnerNo,'IC_CheckPickCell','I','IC')[0].sdefine;//存储验收时是否需要校验拣货位;0----不校验；1----校验
}

