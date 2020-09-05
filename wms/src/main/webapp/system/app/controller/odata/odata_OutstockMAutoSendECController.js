/**
 * 模块名称：电商拣货批量发单
 * 模块编码：3302
 * 创建：huangb 20160705
 * 作业类型由下拉框改为网格，区域不需要选择 huangb 20160706
 */
Ext.define('cms.controller.odata.odata_OutstockMAutoSendECController',{
	extend:'Ext.app.Controller',
	requires:[
          'cms.view.odata.odata_OutstockMAutoSendECUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//拣货发单自动成单》货主选择
			'odata_OutstockMAutoSendECUI bdef_DefOwnerCombo[id=cmbOwnerNoSend3302]':{
				change:this.cmbOwnerNoSend3302Select
			},
			//拣货发单自动成单》出货单别下拉
			'odata_OutstockMAutoSendECUI combo[id=cmbExp_typeSend3302]':{
				change:this.cmbExp_typeSendChange
			},
			//拣货发单自动成单》作业类型
//			'odata_OutstockMAutoSendECUI combo[id=cmbOperate_type3302]':{
//				change:this.operate_type3302change
//			},
			//拣货发单自动成单》波次号选择
			'odata_OutstockMAutoSendECUI grid[id=gridOutstockMWaveNoSend3302]':{
				selectionchange:this.gridOutstockMWaveNoSend3302change
			},		
			//拣货批量发单》储区选择
//			'odata_OutstockMAutoSendECUI grid[id = gridOutstockMAreaNoSend3302]':{
//				selectionchange:this.gridOutstockMAreaNoSend3302selectionchange
//			},
			//拣货批量发单》波次号选择
			'odata_OutstockMAutoSendECUI grid[id=gridOutstockMWaveNoCheck3302]':{
				selectionchange:this.gridOutstockMLocateNoCheck3302change
			},
			//查找
	    	/*'odata_OutstockMAutoSendECUI button[name=detailQuery]':{
				click:this.detailQuery
			},*/
			//拣货发单自动成单》发单
			'odata_OutstockMAutoSendECUI button[id=butSend3302]':{
				click:this.menu3302sendclick
			},
			//拣货发单自动成单》刷新
			'odata_OutstockMAutoSendECUI commMenuWidget4[id=menu3302] [name=refresh]':{
				click:this.menu3302RefreshClick
			},//拣货发单自动成单》关闭前事件
			'odata_OutstockMAutoSendECUI':{
				beforeclose:this.odata_OutstockMAutoSendECUIBeforeclose
			},
			//TAB页切换
			'odata_OutstockMAutoSendECUI tabpanel[id=tabpanel3302]':{
				tabchange:this.TabpanelChange
			}
		});	
		/*Ext.getCmp('grid_01_3302').getStore().addListener({
			beforeload:function(store,records,options){
				store.proxy.extraParams.flag = "1";
			}
		});*/
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){		
		Ext.getCmp('cmbOwnerNoSend3302').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var packingUnit3302_1=commonGetModuleField('3302','packingUnit')[0].flag;
		var packingSpec3302_1=commonGetModuleField('3302','packingSpec')[0].flag;
		var packingUnit3302_2=commonGetModuleField('3302','packingUnit')[0].flag;
		var packingSpec3302_2=commonGetModuleField('3302','packingSpec')[0].flag;

		if(packingUnit3302_1==0){
			Ext.getCmp('packingUnit3302_1').setVisible(false);
		}
		if(packingSpec3302_1==0){
			Ext.getCmp('packingSpec3302_1').setVisible(false);
		}
		if(packingUnit3302_2==0){
			Ext.getCmp('packingUnit3302_2').setVisible(false);
		}
		if(packingSpec3302_2==0){
			Ext.getCmp('packingSpec3302_2').setVisible(false);
		}
		//end add
	},
	
	/**
	 * 查找
	 */
	/*detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=3302;
		queryGrid='gridOutstockMWaveNoSend3302';					
	},*/
	
	//拣货批量发单》储区选择
/*	gridOutstockMAreaNoSend3302selectionchange:function(){
		var objrecord  =  Ext.getCmp('gridOutstockMAreaNoSend3302').getSelectionModel().getSelection();
        if(objrecord.length  ==  0){
    		Ext.getCmp('gridOutstockDSend3302').getStore().removeAll();
        }else{
            var varIds  =  "";
            for(var i  =  0; i < objrecord.length; i++){
            	varIds +=  objrecord[i].get("wareaNo");
                if(i<objrecord.length-1){
                	varIds  =  varIds + "','";
                }
            }
            var listDetail1  =  [];
            listDetail1 = Ext.decode(Ext.getCmp('gridOutstockMAreaNoSend3302').getStore().getProxy().extraParams.str);
			var strDtl = {
				columnId:'cdc.ware_no||cdc.area_no',
				condition:8,
				value:varIds
			};
			listDetail1.push(strDtl);
			var jsonStr  =  Ext.encode(listDetail1);
			var strwheresql  =  {
				str : jsonStr,
				strSendFlag:"auto"
			};
			Ext.apply(Ext.getCmp('gridOutstockDSend3302')
					.getStore().proxy.extraParams,
					strwheresql);
			Ext.getCmp('gridOutstockDSend3302').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDSend3302').getStore()
					.load();
        }
	},*/
	
	//TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabpanel3302').getActiveTab().id=='tabAutoSend3302' )
		{
			Ext.getCmp('cmbOwnerNoSend3302').setValue(null);
			Ext.getCmp('cmbOwnerNoSend3302').getStore().load();
		}else if(Ext.getCmp('tabpanel3302').getActiveTab().id=='tabAutoCheck3302')
		{
			var strWheresql = {
					str : null,
					strSendFlag : "auto",
					strOwnerNo:Ext.getCmp('cmbOwnerNoSend3401').getValue()
			};
			Ext.apply(Ext.getCmp('gridOutstockMWaveNoCheck3302')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridOutstockMWaveNoCheck3302').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockMWaveNoCheck3302').getStore()
					.load();
		}
		
	},
	
	//拣货发单自动成单》货主选择
	cmbOwnerNoSend3302Select:function()
    {
		var listDetail  = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoSend3302').getValue()))
		{
			//有混合业主的情况 如果 为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNoSend3302').getValue()!='ALL'){
				var strDtl = {
					    columnId:'ood.owner_no',
						value:Ext.getCmp('cmbOwnerNoSend3302').getValue()
					};
					listDetail.push(strDtl);
			}
			
			var strJson = Ext.encode(listDetail);
			var strWheresql = {
				str : strJson,
				strSendFlag : "auto"
			};
			Ext.apply(Ext.getCmp('cmbExp_typeSend3302')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbExp_typeSend3302').getStore()
					.removeAll();
			Ext.getCmp('cmbExp_typeSend3302').getStore()
					.load();
			Ext.apply(Ext.getCmp('gridOutstockMWaveNoSend3302')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridOutstockMWaveNoSend3302').getStore()
			.removeAll();
	        Ext.getCmp('gridOutstockMWaveNoSend3302').getStore()
			.load();
				
		}else
		{
			Ext.getCmp('cmbExp_typeSend3302').getStore().removeAll();
			Ext.getCmp('cmbExp_typeSend3302').setValue(null);
		}
	
		//loadgrid_01_3302();
    },
    
    //拣货发单自动成单》关闭前事件
	odata_OutstockMAutoSendECUIBeforeclose:function()
	{
		Ext.getCmp('cmbOwnerNoSend3302').store.removeAll();
	},
	
	//拣货发单自动成单》出货单别下拉选择
	cmbExp_typeSendChange:function()
	{
       var listDetail = [];
       listDetail = Ext.decode(Ext.getCmp('cmbExp_typeSend3302').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3302').getValue()))
       {
    	   var strdtl={
    				columnId:'ood.exp_type',
    				value:Ext.getCmp('cmbExp_typeSend3302').getValue()
    			};
    	       listDetail.push(strdtl);
    			var strJson = Ext.encode(listDetail);
    			var strWheresql = {
    				str : strJson,
    				strSendFlag:'auto',
    				strOwnerNo:Ext.getCmp('cmbOwnerNoSend3302').getValue()
    			};
    			Ext.apply(Ext.getCmp('gridOutstockMWaveNoSend3302')
    					.getStore().proxy.extraParams,
    					strWheresql);
    			Ext.getCmp('gridOutstockMWaveNoSend3302').getStore()
    					.removeAll();
    			Ext.getCmp('gridOutstockMWaveNoSend3302').getStore()
    					.load();
       }else
       {
    	   Ext.getCmp('gridOutstockMWaveNoSend3302').getStore().removeAll();
       }
		
	},
	
	//波次号选择
	gridOutstockMLocateNoCheck3302change:function()
	{
		var objRecord = Ext.getCmp('gridOutstockMWaveNoCheck3302').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockDCheck3302').getStore().removeAll();
        }else{
            var listDetail = [];
			var strDtl={
				columnId:'ood.wave_no',
				value:objRecord[0].data.waveNo
			};
			listDetail.push(strDtl);
			var strDtl={
					columnId:'ood.batch_no',
					value:objRecord[0].data.batchNo
				};
			listDetail.push(strDtl);
			var strJson = Ext.encode(listDetail);
			var wheresql = {
				str : strJson,
				strSendFlag:"auto"//自动发单标识
			};
			Ext.apply(Ext.getCmp('gridOutstockDCheck3302')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDCheck3302').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDCheck3302').getStore()
					.load();
        }
	},
	
	//波次号选择
	gridOutstockMWaveNoSend3302change:function()
	{
		//debugger; 
		var objRecord = Ext.getCmp('gridOutstockMWaveNoSend3302').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
//Ext.getCmp('cmbOperate_type3302').setValue(null);
//Ext.getCmp('cmbOperate_type3302').getStore().removeAll();
    		//Ext.getCmp('gridOutstockMOperateType3302').setValue(null);
    		Ext.getCmp('gridOutstockMOperateType3302').getStore().removeAll();
    		Ext.getCmp('gridOutstockMAreaNoSend3302').getStore().removeAll();
    		Ext.getCmp('gridOutstockDSend3302').getStore().removeAll();
        }else{
            var listDetail = [];
            listDetail=Ext.decode(Ext.getCmp('gridOutstockMWaveNoSend3302').getStore().getProxy().extraParams.str);
			var strDtl={
				columnId:'ood.wave_no',
				value:objRecord[0].data.waveNo
			};
			listDetail.push(strDtl);
			var strDtl={
					columnId:'ood.batch_no',
					value:objRecord[0].data.batchNo
				};
			listDetail.push(strDtl);
			var strDtl={
				columnId:'ood.outstock_type',
				value:objRecord[0].data.outstockType
			};
			listDetail.push(strDtl);
			var strJson = Ext.encode(listDetail);
			var wheresql = {
				str : strJson
			};
//			Ext.apply(Ext.getCmp('cmbOperate_type3302')
//					.getStore().proxy.extraParams,
//					wheresql);
//			Ext.getCmp('cmbOperate_type3302').setValue(null);
//			Ext.getCmp('cmbOperate_type3302').getStore()
//					.removeAll();
//			Ext.getCmp('cmbOperate_type3302').getStore()
//					.load();
			//加载作业类型
			Ext.apply(Ext.getCmp('gridOutstockMOperateType3302')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockMOperateType3302').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockMOperateType3302').getStore()
					.load();
			
			//加载区域信息
			Ext.apply(Ext.getCmp('gridOutstockMAreaNoSend3302')
    				.getStore().proxy.extraParams,
    				wheresql);
    		Ext.getCmp('gridOutstockMAreaNoSend3302').getStore()
    				.removeAll();
    		Ext.getCmp('gridOutstockMAreaNoSend3302').getStore()
    				.load();
    		
    		//加载明细信息
			Ext.apply(Ext.getCmp('gridOutstockDSend3302')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDSend3302').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDSend3302').getStore()
					.load();
        }
	},
	
	//拣货发单自动成单》作业类型
//	operate_type3302change:function(th,newValue,oldValue,eOpts)
//	{
//        var listDetail = [];
//        listDetail=Ext.decode(Ext.getCmp('cmbOperate_type3302').getStore().getProxy().extraParams.str);
//        if(!Ext.isEmpty(Ext.getCmp('cmbOperate_type3302').getValue()))
//        {
//        	var strDtl={
//        			columnId:'ood.operate_type',
//        			value:Ext.getCmp('cmbOperate_type3302').getValue()
//        		};
//        		listDetail.push(strDtl);
//        		var strJson = Ext.encode(listDetail);
//        		var wheresql = {
//        			str : strJson
//        		};
//        		Ext.apply(Ext.getCmp('gridOutstockMAreaNoSend3302')
//        				.getStore().proxy.extraParams,
//        				wheresql);
//        		Ext.getCmp('gridOutstockMAreaNoSend3302').getStore()
//        				.removeAll();
//        		Ext.getCmp('gridOutstockMAreaNoSend3302').getStore()
//        				.load();
//        		
//        		/*Ext.apply(Ext.getCmp('gridOutstockDSend3302')
//        				.getStore().proxy.extraParams,
//        				wheresql);
//        		Ext.getCmp('gridOutstockDSend3302').getStore()
//        				.removeAll();
//        		Ext.getCmp('gridOutstockDSend3302').getStore()
//        				.load();*/
//        }else
//        {
//        	Ext.getCmp('gridOutstockMAreaNoSend3302').getStore().removeAll();
//    		Ext.getCmp('gridOutstockDSend3302').getStore().removeAll();
//        }
//	},
	
	menu3302sendclick:function()
	{
		var data=Ext.getCmp('gridOutstockMWaveNoSend3302').getSelectionModel().getSelection();
		//var data2=Ext.getCmp('gridOutstockMAreaNoSend3302').getSelectionModel().getSelection();
		//判断单据要根据发单显示明细来 而不是未发单指示显示来 huangb 20160808
		//var objOutstockDCount = Ext.getCmp('gridOutstockDCheck3302').getStore().count();
		var objOutstockDCount = Ext.getCmp('gridOutstockDSend3302').getStore().count();
		if(objOutstockDCount==0)
		{
			Ext.example.msg('提示',"请选择要发的单据！");
			return;
		}else
		{
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				return;
			}
			if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3302').getValue())){
				Ext.example.msg('提示','请输入下架人员');
				return;	
			}
			
			Ext.Msg.confirm("提示", "确定发单吗？",
			function(button, text) {
				if(button == 'yes')
				{
					var detail=[];
					//for(var i=0;i<data2.length;i++){
						
					Ext.getCmp('butSend3302').setDisabled(true);
				    var msgShow = commonMegShow("正在发单,请稍等...");
					//var obj=data2[i];
					var d={
						warehouseNo:Ext.get('warehouseNo').getValue(),
						ownerNo:Ext.getCmp('cmbOwnerNoSend3302').getValue(),
						expType:Ext.getCmp('cmbExp_typeSend3302').getValue(),
						sourceType:data[0].data.sourceType,
						waveNo:data[0].data.waveNo,
						batchNo:data[0].data.batchNo,
//						wareaNo:obj.data.wareaNo,
//						areaNo:obj.data.areaNo,
						wareaNo:'ALL',
						areaNo:'ALL',
						dockNo:workSpaceNo,
						//operateType:Ext.getCmp('gridOutstockMOperateType3302').getValue(),
						operateType:'ALL',
						workerNo:Ext.get('workerNo').getValue(),
						outStockType:data[0].data.outstockType,
						printPaperType:Ext.getCmp('checkBoxPrint_type3302').getValue()==false ? 0:1,
						printFaceSheet:Ext.getCmp('printFaceSheet3302').getValue()==false ? 0:1,
						printBuilt:Ext.getCmp('printBuilt3302').getValue()==false ? 0:1,
						printInvoice:Ext.getCmp('printInvoice3302').getValue()==false ? 0:1
						/*printPaperType:'0',
						printFaceSheet:'0',
						printBuilt:'0',
						printInvoice:'0'*/		
					};
					detail.push(d);
					//}
					var strJsonDetail = Ext.encode(detail);
					var params = 
					{
						strJsonDetail:strJsonDetail
					};
					Ext.Ajax.request({
						method:'POST',
						url:'odata_OutstockMAction_sendTscAuto',
						params:params,
						success:function(response)
						{
							msgShow.hide();
							Ext.getCmp('butSend3302').setDisabled(false);
							var data = Ext.decode(response.responseText);
							if(data.isSucc)
							{
								Ext.example.msg('提示',data.msg);
								Ext.getCmp('gridOutstockMWaveNoSend3302').getStore()
			    					.load();
							}else
							{
								Ext.Msg.alert($i18n.prompt,data.msg+data.obj);

								//Ext.example.msg('提示',data.msg+data.obj);
							}				
						}
				/*	,
						failure:function(response){
							Ext.getCmp('butSend3302').setDisabled(false);
							msgShow.hide();
							Ext.example.msg('提示',"提交不上,可能是网络问题");
						}*/
					});
				}
			});
		}
		
	},
	
	//刷新
	menu3302RefreshClick:function()
	{
		this.TabpanelChange();
	}
});

