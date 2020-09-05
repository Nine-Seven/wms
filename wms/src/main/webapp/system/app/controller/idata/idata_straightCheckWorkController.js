/**
 * 模块名称：直通验收作业Controller
 * 模块编码：4901
 * 创建：chensr
 */
var g_ICAcrossAreaBox4901='';//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
var g_ICFreezeRate4901='';//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICAlarmRate4901='';//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICCheckPickCell4901='';//存储验收时是否需要校验拣货位;0----不校验；1----校验
var g_ICCheckQpalette4901='';//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
var g_IsCanEdit4901=false;
Ext.define('cms.controller.idata.idata_straightCheckWorkController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.idata.idata_straightCheckWorkUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//新增
			'idata_straightCheckWorkUI button[name=add]':{
				click:this.addClick
			},//撤销
			'idata_straightCheckWorkUI button[name=undo]':{
				click:this.undoClick
			},//保存
			'idata_straightCheckWorkUI button[name=save]':{
				click:this.saveClick
			},//进货汇总单选择
			'idata_straightCheckWorkUI remoteCombo[id=cmbSImportNo4901]':
			{
				focus:this.cmbSImportNo4901Focus,
				select:this.cmbSImportNo4901Select
			},//进货单号选择
			'idata_straightCheckWorkUI bdef_DefOwnerCombo[id=cmbOwnerNo4901]':
			{
				change:this.cmbOwnerNo4901Change
			},//验收网格编辑
			'idata_straightCheckWorkUI grid[id=grid_01_4901]':{
				beforeedit:this.grid_01_4901Beforeedit,
				edit:this.grid_01_4901edit
			},
			'idata_straightCheckWorkUI form[id=form_01_4901] field':{
				specialkey:this.boxkeydown
			},//拆笔
			'idata_straightCheckWorkUI grid[id=grid_01_4901] actioncolumn':{
				click:this.actioncolumnClick
			}
			
		});
	},
	
	initializtion:function()
	{
		//显示变量0为不显示，1为显示
		var planBox4901=commonGetModuleField('4901','planBox')[0].flag;
		var planQmin4901=commonGetModuleField('4901','planQmin')[0].flag;
		var planDis4901=commonGetModuleField('4901','planDis')[0].flag;
		var packingUnit4901=commonGetModuleField('4901','packingUnit')[0].flag;
		var packingSpec4901=commonGetModuleField('4901','packingSpec')[0].flag;
		
		if(planBox4901==0){
			Ext.getCmp('planBox4901').setVisible(false);
		}
		if(planQmin4901==0){
			Ext.getCmp('planQmin4901').setVisible(false);
		}
		if(planDis4901==0){
			Ext.getCmp('planDis4901').setVisible(false);
		}
		if(packingUnit4901==0){
			Ext.getCmp('packingUnit4901').setVisible(false);
		}
		if(packingSpec4901==0){
			Ext.getCmp('packingSpec4901').setVisible(false);
		}
	},
	
	addClick:function()
	{
		Ext.getCmp('form_01_4901').getForm().reset();
		Ext.getCmp('grid_01_4901').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
		new Array('cmbOwnerNo4901','cmbSImportNo4901','cmbCheckWorker4901'),
		false);
		if(Ext.getCmp('cmbOwnerNo4901').getStore().data.length>0)
		{
			Ext.getCmp('cmbOwnerNo4901').setValue(Ext.getCmp('cmbOwnerNo4901').getStore().getAt(0).data.value);		
		}
		Ext.getCmp('cmbImportType4901').setValue('ID');
		Ext.getCmp('cmbStatus4901').setValue('10');
		Ext.getCmp('dateCheckStartDate4901').setValue(new Date());
		Ext.getCmp('cmbCheckTools4901').setValue('1');
		Ext.getCmp('cmbCheckTools4901').setValue('1');
		commonMenu4Button('menu4901','add');
		Ext.getCmp('cmbSImportNo4901').focus();
		g_IsCanEdit4901=true;
		
		Ext.get('rgstName4901').dom.innerHTML=Ext.get('workerNo').getValue();//'admin';
		Ext.get('rgstDate4901').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
		Ext.get('updtName4901').dom.innerHTML='';
		Ext.get('updtDate4901').dom.innerHTML='';
	},
	
	undoClick:function()
	{
		Ext.getCmp('form_01_4901').getForm().reset();
		Ext.getCmp('grid_01_4901').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
			new Array('cmbOwnerNo4901','cmbSImportNo4901',
					'cmbSupplierNo4901','cmbCheckWorker4901','dateCheckStartDate4901','cmbCheckTools4901'),
			true);
		commonMenu4Button('menu4901','undo');
		g_IsCanEdit4901=false;
	},
	
	saveClick:function()
	{
		if(!commonCheckIsInputAll('form_01_4901'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_01_4901").getStore().getCount();
		if(gridcount>0)
		{
			if(!Checkdetailgrid4901('grid_01_4901',0,gridcount-1))
			{
				return;
			}
		}else
		{			
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
				var strSImportNo= Ext.getCmp('cmbSImportNo4901').getValue();
				var strDockNo= workSpaceNo;		
				var strCheckWorker= Ext.getCmp('cmbCheckWorker4901').getValue();
				var strOwnerNo=Ext.getCmp('cmbOwnerNo4901').getValue();
				var strCheckTools=Ext.getCmp('cmbCheckTools4901').getValue();
				var master=
				{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					SImportNo:Ext.getCmp('cmbSImportNo4901').getValue(),		
					dockNo:workSpaceNo,				
					checkWorker:Ext.getCmp('cmbCheckWorker4901').getValue(),
					ownerNo:Ext.getCmp('cmbOwnerNo4901').getValue(),
					CheckTools:Ext.getCmp('cmbCheckTools4901').getValue()
				};
							
				var detail1 = [];
				for(var i=0;i<gridcount;i++ )
				{
					var record  = Ext.getCmp('grid_01_4901').getStore().getAt(i);
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
						strJsonDetail1:strJsonDetail1
					};
                    ////////////////////////////////////////////////////////
					
					if(Ext.getCmp('cmbImportType4901').getValue()=='ID'){
						Ext.Ajax.request({
							method:'POST',
							url:'idata_StraightCheckWorkAction_save',
							params:params,
							success:function(response)
							{
								msgShow.hide();
								var data = Ext.decode(response.responseText);
								if(data.isSucc)
								{
									commonMenu4Button('menu4901','save');
									Ext.example.msg('提示',data.msg);
									g_IsCanEdit4901=false;
								}else
								{
									Ext.Msg.alert('提示',data.msg+data.obj);
								}				
							}
						});
					}else if(Ext.getCmp('cmbImportType4901').getValue()=='IS'){
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
									commonMenu4Button('menu4901','save');
									Ext.example.msg('提示',data.msg);
									g_IsCanEdit4301=false;
								}else
								{
									Ext.Msg.alert('提示',data.msg+data.obj);
								}				
							}
						});
					}
                ////////////////////////////////////////////////////////	
				}else
				{
					msgShow.hide();
					Ext.Msg.alert('提示','此次没有验收任何商品');
				}
			}
		});
	},
	
	cmbSImportNo4901Focus:function()
	{
		var strOwnerNo = {
			strOwnerNo : Ext.getCmp('cmbOwnerNo4901').getValue()
		};
		Ext.apply(Ext.getCmp('cmbSImportNo4901').getStore().proxy.extraParams,strOwnerNo);
		Ext.getCmp('cmbSImportNo4901').getStore().removeAll();
		Ext.getCmp('cmbSImportNo4901').getStore().load();
	},
	
	cmbSImportNo4901Select:function(combo)
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
				
			    Ext.getCmp('cmbSupplierNo4901').getStore().add({
			    	value:data[0].supplierNo,
			    	dropValue:'['+data[0].supplierNo+']'+data[0].supplierName,
			    	text:data[0].supplierNo
			    });
			    Ext.getCmp('cmbSupplierNo4901').setValue(data[0].supplierNo);
			    Ext.getCmp('cmbOwnerNo4901').setValue(data[0].ownerNo);
			    Ext.getCmp('cmbImportType4901').setValue(data[0].importType);
			}
		});
		
		var strDetail1 = [];
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNo4901').getValue()
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
		Ext.apply(Ext.getCmp('grid_01_4901').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_4901').getStore().removeAll();
		Ext.getCmp('grid_01_4901').getStore().load();
		Ext.getCmp('cmbOwnerNo4901').setReadOnly(true);
		Ext.getCmp('cmbSImportNo4901').setReadOnly(true);
	},
	
	cmbImportNo4901Select:function(combo){
		var params=
		{
			strImportNo:combo.getValue()	
		};
		Ext.Ajax.request({
			method:'POST',
			url:'idata_CheckAction_getSImportNo.action',
			params:params,
			async:false,
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				Ext.getCmp('cmbSImportNo4901').getStore().add({
			    	value:data,
			    	dropValue:data,
			    	text:data
			    });
			    Ext.getCmp('cmbSImportNo4901').setValue(data);
			}
		});
		
		var strWheresql = 
		{
			strWheresql:Ext.getCmp('cmbSImportNo4901').getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_4901').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_4901').getStore().removeAll();
		Ext.getCmp('grid_01_4901').getStore().load();
	},
	
	cmbOwnerNo4901Change:function(combo)
	{
		getSystemPara4901(combo.getValue());
	},

	grid_01_4901Beforeedit:function(editor,e,eOpts)
	{
		if(!g_IsCanEdit4901)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
	    	//直通验收时，生产日期默认为1900-01-01，不需要输入
	    	if(
	    	(Ext.getCmp('cmbSImportNo4901').getValue().substring(0,2)=='SD' 
	    		|| e.record.data.expiryDays==-1) 
	    	&& (e.field=='produceDate' || e.field=='expireDate'))
	    	{
		        editor.cancel = true;
	        	return  false; 
	    	}else if((e.record.data.lotNo=='N' ) && (e.field=='lotNo'))
	    	{
	    		editor.cancel = true;
	        	return  false; 
	    	}
	    }
	},
	
	grid_01_4901edit:function(editor,e,eOpts)
	{
		if(e.field=='produceDate')
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
							editor.context.record.set('expireDate',null);
							
							editor.context.record.set('expireDate',Ext.Date.format
									(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays),'Y-m-d'));
////////////////////////////////////////////////////////////////////////////////////////
							var params=
							{
								strQuery:Ext.Date.format(e.value,'Y/m/d')	
							};
							Ext.Ajax.request({
								method:'POST',
								url:'idata_CheckAction_getlotNo.action',
								params:params,
								success:function(response)
								{
									var res = Ext.decode(response.responseText);
							    	if(res!=''){
							    		editor.context.record.set('lotNo',res[0]);
							    	}else{
							    		editor.context.record.set('lotNo',null);
							    	}
								}
							});
////////////////////////////////////////////////////////////////////////////////////////							
							
							checkDate4901(e,editor);
						}
					}
				}
			}
		}else if(e.field=='expireDate')
		{
			if(!Ext.isEmpty(e.value))
			{
				if(e.record.data.expiryDays!=0){
					if(editor.grid.getStore().findBy(function(record, id) 
					{  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('articleNo')==editor.context.record.data.articleNo
						&& record.get('packingQty')==editor.context.record.data.packingQty
						&& Ext.Date.format(new Date(record.get('expireDate')),'Y-m-d') 
						==Ext.Date.format(new Date(editor.context.record.data.expireDate),'Y-m-d');
						})!=-1)				
					{
						editor.context.record.set('expireDate',editor.context.originalValue);
						Ext.example.msg('提示', "【商品编码】、【生产日期】不能重复，请重新输入！");
					}else
					{
						if(e.record.data.expiryDays==-1){
							
						}else if(e.record.data.expiryDays==0){
							
						}else if(e.record.data.expiryDays>0){
							editor.context.record.set('produceDate',Ext.Date.format
									(Ext.Date.add(new Date(e.value), Ext.Date.DAY, e.record.data.expiryDays*-1),'Y-m-d'));
							checkDate4901(e,editor);
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
			if(!checkQty4901(e.record.data.articleNo))
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
					if(g_ICCheckPickCell4901==1){
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
					if(g_ICCheckQpalette4901==1)
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
					if(g_ICCheckPickCell4901==1){
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
					if(g_ICCheckQpalette4901==1)
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
					if(g_ICCheckPickCell4901==1){
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
					if(g_ICCheckQpalette4901==1)
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
		}
	},

	boxkeydown:function(th,e,eOpts)
	{
		if (e.getKey() == e.ENTER) 
		{
			if(th.id=="cmbSImportNo4901")
	  		{
	  			Ext.getCmp('cmbCheckWorker4901').focus();
	  		}else
  			{
	  			th.nextSibling().focus();
  			}
			
        }
	},
	
	actioncolumnClick:function(grid, rowIndex, colIndex)
	{
		var data = grid.getStore().getAt(colIndex);
        //alert("Edit " + data.get('articleNo'));
		if(g_IsCanEdit4901)
	    {	//不管保质期的不允许拆笔
	    	if(data.data.expiryDays!=-1){	    	    
		    	Ext.Msg.confirm("提示", "确定拆笔？", function(button, text) {
				if (button == 'yes') {
					//var data = Ext.getCmp('grid_02_800002').getSelectionModel().getSelection();
					var r = Ext.create('cms.model.idata.idata_ImportSdModel', {
					});
					r.set('articleNo',data.data.articleNo);
					r.set('articleName',data.data.articleName);	
					r.set('ownerArticleNo',data.data.ownerArticleNo);
					r.set('barcode',data.data.barcode);
					r.set('packingQty',data.data.packingQty);
					r.set('spec',data.data.spec);	
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
					Ext.getCmp('grid_01_4901').store.insert(colIndex+1,r);
					
					for(var i=1;i<=Ext.getCmp('grid_01_4901').getStore().getCount();i++ ){
						Ext.getCmp('grid_01_4901').getStore().getAt(i-1).set('rowId',i);
						Ext.getCmp('grid_01_4901').getStore().getAt(i-1).index=i;
					}
				}
				});
	    	}
	    }
	}
});

/**
 * 校验报警天数和冻结比率
 * @param e
 */
function checkDate4901(e,editor){
	var intProduce=Math.round(//计算输入的日期与今天的日期的相隔天数
			new Date(Ext.Date.format(e.value,'Y-m-d')).getTime()
			-new Date(Ext.Date.format(new Date(),'Y-m-d')).getTime())
			/86400000*-1;
	var intFreezeRate=e.record.data.expiryDays*e.record.data.freezerate*0.01;//计算冻结比率的天数
	var intICAlarmRate=e.record.data.expiryDays*e.record.data.alarmrate*0.01;//计算报警比率的天数
	if(g_ICAlarmRate4901==1)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			editor.context.record.set('produceDate',null);
			editor.context.record.set('expireDate',null);
			Ext.example.msg('提示', "商品己到冻结比率！");
		}else
		{
			if(g_ICFreezeRate4901==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					editor.context.record.set('produceDate',null);
					editor.context.record.set('expireDate',null);
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}else if(g_ICFreezeRate4901==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}
		}
	}else if(g_ICAlarmRate4901==2)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			Ext.example.msg('提示', "商品己到冻结比率！");
		}else
		{
			if(g_ICFreezeRate4901==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					editor.context.record.set('produceDate',null);
					editor.context.record.set('expireDate',null);
					Ext.example.msg('提示', "商品己到报警比率！");
				}
			}else if(g_ICFreezeRate4901==2)
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
 * 校验商品是否超量
 * @param articleNo
 * @returns {Boolean}
 */
function checkQty4901(articleNo){
	var store=Ext.getCmp('grid_01_4901').getStore().queryBy(function(record) {  
		return record.get('articleNo') ==articleNo; 
	});
	var checkQty=0;
	for(var i=0;i<store.length;i++){
		checkQty+=store.items[i].get('checkQty');
	}
	
	if(store.items[0].get('noCheckQty')-checkQty<0)
	{
		Ext.example.msg($i18n.prompt,"该商品超量值为:"+store.items[0].get('checkExcess')+"%"+" 已超过验收超量比率,请重新输入");
		return false;
	}
	return true;
}

function Checkdetailgrid4901(gridid,beginindex,endindex){
	var grid=Ext.getCmp(gridid);
	for ( var i =beginindex; i <=endindex; i++) {
		var record = grid.getStore().getAt(i);
		for(var j=0;j<grid.columns.length;j++)
		{
			if(grid.columns[j].cls==="notnull" && !grid.columns[j].hidden)
			{
				/*if(grid.getStore().getAt(i).get(grid.columns[8].dataIndex)=='0' 
					|| grid.getStore().getAt(i).get(grid.columns[8].dataIndex)==undefined)
				{
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[j].dataIndex)))
					{
						if(j==8 || j==9 || j==10 || j==11 || j==12 || j==13)
						{
						}else{
							Ext.example.msg('提示','请输入'+grid.columns[j].text+'！');
							return false;
						}
					}
				}else
				{*/
					if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[j].dataIndex)))
					{
						Ext.example.msg('提示','请输入'+grid.columns[j].text+'！');
						return false;
					}
				//}
			}
		}					
	}
	return true;
}

/**
 * 根据货主获取相应的系统参数
 * @param strOwnerNo
 */
function getSystemPara4901(strOwnerNo)
{
	g_ICAcrossAreaBox4901
		=commonGetSystemParams(strOwnerNo,'IC_AcrossAreaBox','I','IC')[0].sdefine;//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
	g_ICFreezeRate4901
		=commonGetSystemParams(strOwnerNo,'IC_FreezeRate','I','IC')[0].sdefine;//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICAlarmRate4901
		=commonGetSystemParams(strOwnerNo,'IC_AlarmRate','I','IC')[0].sdefine;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICCheckPickCell4901
		=commonGetSystemParams(strOwnerNo,'IC_CheckPickCell','I','IC')[0].sdefine;//存储验收时是否需要校验拣货位;0----不校验；1----校验
	g_ICCheckQpalette4901
		=commonGetSystemParams(strOwnerNo,'IC_CheckQpalette','I','IC')[0].sdefine;//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
}
