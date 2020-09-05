/**
 * 模块名称：盘点复盘、三盘发单
 * 模块编码：8301
 */
var g_map8201 = new Ext.util.HashMap();//选中的数据
var g_strFCSRecheckCycleFlag = "";//1:运行循环复盘，0：不允许循环复盘
Ext.define('cms.controller.fcdata.fcdata_ReCheckController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.fcdata.fcdata_ReCheckUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//选择盘点次数
			'fcdata_ReCheckUI combo[id=cmbCheck_type8301]':{
				change:this.CheckType8301change
			},
			//选择差异标识
			'fcdata_ReCheckUI wms_DefFieldValCombo[id=cmbDifferent_flag8301]':{
				change:this.DifferentFlag8301change
			},
			//头档单据选择
			'fcdata_ReCheckUI grid[id=gridCheckM8301]':{
				selectionchange:this.gridCheckM8301Selectionchange
			},
			//复盘、三盘发单》派单》发单
			'fcdata_ReCheckUI button[id=ButReSend8301]':{
				click:this.sendAgain_8301
			},
			//盘点发单》刷新
			'fcdata_ReCheckUI button[name=refresh]':{
				click:this.refreshClick
			},
			//复盘、三盘发单》结束盘点
			'fcdata_ReCheckUI button[id=butEndlooprecheck_8301]':{
				click:this.butEndlooprecheck_8301Click
			}
		});
	},
	
	// 初始化界面
	initializtion:function(){
		Ext.getCmp('butEndlooprecheck_8301').setVisible(false);
	},
	
	//复盘、三盘发单》结束盘点
	butEndlooprecheck_8301Click:function(){
		 if(!Ext.isEmpty(Ext.getCmp('gridCheckM8301').getSelectionModel().getSelection())){
       	Ext.Msg.confirm($i18n_prompt.prompt, $i18n_prompt.closeFcdataOrNot,
			function(button, text) {
			if (button == 'yes') {
				var objRecord = Ext.getCmp('gridCheckM8301').getSelectionModel().getSelection();
				var params = {
						str:objRecord[0].data.checkNo
				};
				Ext.Ajax.request({
					method:'POST',
					url:'fcdata_CheckAction_sendEndFcdata.action',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							Ext.getCmp('cmbCheck_type8301').getStore().reload();
							Ext.getCmp('cmbDifferent_flag8301').getStore().reload();
							Ext.getCmp('gridCheckM8301').getStore().reload();
						}else{
							Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
						}				
					},
					failure:function(response){
						Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.CannotSubForWeb);
					}
				});	
			}
			});
       }else{
       	Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choseDateToCloseFcdata);
       }
	},
	
	//头档单据选择
	gridCheckM8301Selectionchange:function(th,selected,eOpts)
	{
		
		var objRecord = Ext.getCmp('gridCheckM8301').getSelectionModel().getSelection();
		
		if(objRecord.length!=0)
		{
			var strOwnerNo = objRecord[0].get('ownerNo');
			g_strFCSRecheckCycleFlag = commonGetSystemParams(strOwnerNo,'FCS_RecheckCycleFlag','FC','FCS')[0].sdefine;
			if(g_strFCSRecheckCycleFlag == "1")
			{
				Ext.getCmp('butEndlooprecheck_8301').setVisible(true);
			}else
			{
				Ext.getCmp('butEndlooprecheck_8301').setVisible(false);
			}
			var strParamsForD = {
					strWheresql:objRecord[0].data.checkNo,
					strCheckType:Ext.getCmp('cmbCheck_type8301').getValue()
				};
				Ext.apply(Ext.getCmp('gridCheckD8301')
						.getStore().proxy.extraParams,
						strParamsForD);
				Ext.getCmp('gridCheckD8301').getStore()
						.removeAll();
				Ext.getCmp('gridCheckD8301').getStore().load();
		}else
		{
			Ext.getCmp('gridCheckD8301').getStore()
						.removeAll();
		}
		
	},
	
	// 选中盘点类型	 
	CheckType8301change:function(th,newValue,oldValue,eOpts)
	{
		if(newValue=='3')
		{
			Ext.getCmp('cmbDifferent_flag8301').hide();
		}else
		{
			Ext.getCmp('cmbDifferent_flag8301').show();
		}
		this.FcdataCheckMChange(th,newValue,oldValue,eOpts);
	},
	
	// 选中差异标识
	DifferentFlag8301change:function(th,newValue,oldValue,eOpts)
	{
		this.FcdataCheckMChange(th,newValue,oldValue,eOpts);
	},
	
	//加载盘点计划单数据
	FcdataCheckMChange:function(th,newValue,oldValue,eOpts)
	{
		var strCheckType = '';
		var strDifferentFlag = '';
		strCheckType = Ext.getCmp('cmbCheck_type8301').getValue();
		if(Ext.getCmp('cmbCheck_type8301').getValue()=='3')
		{
			strDifferentFlag = '1';
		}else
		{
			strDifferentFlag = Ext.getCmp('cmbDifferent_flag8301').getValue();
		}
		if(Ext.getCmp('formReCheck8301').getForm().isValid())
		{
		}else
		{
			if(!commonCheckIsInputAll('formReCheck8301'))
			{
				return;
			}else
			{
				return;
			}
		}
		var strWheresql = {
			strCheckType : strCheckType,
			strDifferentFlag : strDifferentFlag
		};
		Ext.apply(Ext.getCmp('gridCheckM8301')
				.getStore().proxy.extraParams,
				strWheresql);
		Ext.getCmp('gridCheckM8301').getStore()
				.removeAll();
		Ext.getCmp('gridCheckM8301').getStore()
				.load();
		if(Ext.isEmpty(Ext.getCmp('gridCheckM8301').getSelectionModel().getSelection()))
		{
			Ext.getCmp('gridCheckD8301').getStore()
			.removeAll();
		}
		
	},
	
	//发单 
	sendAgain_8301:function(){
        if(!Ext.isEmpty(Ext.getCmp('gridCheckD8301').getSelectionModel()
			      .getSelection())){
        	if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo8301').getValue())){
        		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.inWorkerNo);
        		return;
        	}
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				return;
			}
        	Ext.Msg.confirm($i18n_prompt.prompt, $i18n_prompt.sendOrNot,
			function(button, text) 
			{
			  if (button == 'yes') 
			  {
				var objSelectCheckD = Ext.getCmp('gridCheckD8301').getSelectionModel().getSelection();
				
				var objSelectCheckM = Ext.getCmp('gridCheckM8301').getSelectionModel().getSelection();
				var strDetail = [];
				for(var i=0;i<objSelectCheckD.length;i++)
				{
					var strD=
					{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:objSelectCheckD[i].get('warehouseNo'),
						ownerNo:objSelectCheckM[0].get('ownerNo'),
						rgstName:Ext.getCmp('cmbWorkerNo8301').getValue(),
						checkNo:objSelectCheckM[0].get('checkNo'),
						checkType:Ext.getCmp('cmbCheck_type8301').getValue(),
						fcdataType:objSelectCheckD[i].get('fcdataType'),
						printType:Ext.getCmp('radioPrint_type8301').getValue().pt,
						cellNo:objSelectCheckD[i].get('cellNo')
					};
					strDetail.push(strD);
				};
				var josonDetail = Ext.encode(strDetail);
				var strParams = {
					str:josonDetail,
					strCheckType:Ext.getCmp('cmbCheck_type8301').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'fcdata_CheckAction_sendAgain.action',
					params:strParams,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							if(Ext.isEmpty(Ext.getCmp('gridCheckM8301').getSelectionModel().getSelection()))
							{
								Ext.getCmp('gridCheckD8301').getStore().removeAll();
							}else{
								Ext.getCmp('gridCheckM8301').getStore().load();
							}
						}else{
							Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
						}				
					}
				});	
			}
			});
        }else{
        	Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.selectNodeForSend);
        }
	},
	
	// 刷新	
	refreshClick:function()
	{
		Ext.getCmp('gridCheckD8301').getStore()
						.removeAll();
		Ext.getCmp('cmbCheck_type8301').getStore().reload();
		Ext.getCmp('cmbDifferent_flag8301').getStore().reload();
		Ext.getCmp('gridCheckM8301').getStore().reload();
	}
	
});
