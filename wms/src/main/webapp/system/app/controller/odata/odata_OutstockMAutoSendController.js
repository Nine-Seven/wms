/**
 * 模块名称：拣货批量发单
 * 模块编码：3301
 * 创建：周欢
 */
Ext.define('cms.controller.odata.odata_OutstockMAutoSendController',{
	extend:'Ext.app.Controller',
	requires:[
          'cms.view.odata.odata_OutstockMAutoSendUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//拣货发单自动成单》货主选择
			'odata_OutstockMAutoSendUI bdef_DefOwnerCombo[id=cmbOwnerNoSend3301]':{
				change:this.cmbOwnerNoSend3301Select
			},
			//拣货发单自动成单》出货单别下拉
			'odata_OutstockMAutoSendUI combo[id=cmbExp_typeSend3301]':{
				change:this.cmbExp_typeSendChange
			},
			//拣货发单自动成单》作业类型
			'odata_OutstockMAutoSendUI combo[id=cmbOperate_type3301]':{
				change:this.operate_type3301change
			},
			//拣货发单自动成单》波次号选择
			'odata_OutstockMAutoSendUI grid[id=gridOutstockMWaveNoSend3301]':{
				selectionchange:this.gridOutstockMWaveNoSend3301change
			},		
			//拣货批量发单》储区选择
			'odata_OutstockMAutoSendUI grid[id = gridOutstockMAreaNoSend3301]':{
				beforeselect:this.gridOutstockMManSend3301beforeselect,//同一物流箱有不同状态,不允许发单 huangb 20160805
				selectionchange:this.gridOutstockMAreaNoSend3301selectionchange
			},
			//拣货批量发单》波次号选择
			'odata_OutstockMAutoSendUI grid[id=gridOutstockMWaveNoCheck3301]':{
				selectionchange:this.gridOutstockMLocateNoCheck3301change
			},
			//查找
	    	/*'odata_OutstockMAutoSendUI button[name=detailQuery]':{
				click:this.detailQuery
			},*/
			//拣货发单自动成单》发单
			'odata_OutstockMAutoSendUI button[id=butSend3301]':{
				click:this.menu3301sendclick
			},
			//拣货发单自动成单》刷新
			'odata_OutstockMAutoSendUI commMenuWidget4[id=menu3301] [name=refresh]':{
				click:this.menu3301RefreshClick
			},//拣货发单自动成单》关闭前事件
			'odata_OutstockMAutoSendUI':{
				beforeclose:this.odata_OutstockMAutoSendUIBeforeclose
			},
			//TAB页切换
			'odata_OutstockMAutoSendUI tabpanel[id=tabpanel3301]':{
				tabchange:this.TabpanelChange
			}
		});	
		/*Ext.getCmp('grid_01_3301').getStore().addListener({
			beforeload:function(store,records,options){
				store.proxy.extraParams.flag = "1";
			}
		});*/
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){		
		Ext.getCmp('cmbOwnerNoSend3301').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var packingUnit3301_1=commonGetModuleField('3301','packingUnit')[0].flag;
		var packingSpec3301_1=commonGetModuleField('3301','packingSpec')[0].flag;
		var packingUnit3301_2=commonGetModuleField('3301','packingUnit')[0].flag;
		var packingSpec3301_2=commonGetModuleField('3301','packingSpec')[0].flag;

		if(packingUnit3301_1==0){
			Ext.getCmp('packingUnit3301_1').setVisible(false);
		}
		if(packingSpec3301_1==0){
			Ext.getCmp('packingSpec3301_1').setVisible(false);
		}
		if(packingUnit3301_2==0){
			Ext.getCmp('packingUnit3301_2').setVisible(false);
		}
		if(packingSpec3301_2==0){
			Ext.getCmp('packingSpec3301_2').setVisible(false);
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
		queryModuleId=3301;
		queryGrid='gridOutstockMWaveNoSend3301';					
	},*/
	
	//同一物流箱有不同状态,不允许发单 huangb 20160805
	gridOutstockMManSend3301beforeselect:function(th,record,index,eOpts){
		if(record.data.isSengflag=='不可发单'){
			Ext.example.msg('提示',"该客户需补货，请先补货！");
			return false;
		}
	},
	
	//拣货批量发单》储区选择
	gridOutstockMAreaNoSend3301selectionchange:function(){
		var objrecord  =  Ext.getCmp('gridOutstockMAreaNoSend3301').getSelectionModel().getSelection();
        if(objrecord.length  ==  0){
    		Ext.getCmp('gridOutstockDSend3301').getStore().removeAll();
        }else{
            var varIds  =  "";
            for(var i  =  0; i < objrecord.length; i++){
            	varIds +=  objrecord[i].get("wareaNo");
                if(i<objrecord.length-1){
                	varIds  =  varIds + "','";
                }
            }
            var listDetail1  =  [];
            listDetail1 = Ext.decode(Ext.getCmp('gridOutstockMAreaNoSend3301').getStore().getProxy().extraParams.str);
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
			Ext.apply(Ext.getCmp('gridOutstockDSend3301')
					.getStore().proxy.extraParams,
					strwheresql);
			Ext.getCmp('gridOutstockDSend3301').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDSend3301').getStore()
					.load();
        }
	},
	
	//TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabpanel3301').getActiveTab().id=='tabAutoSend3301' )
		{
			Ext.getCmp('cmbOwnerNoSend3301').setValue(null);
			Ext.getCmp('cmbOwnerNoSend3301').getStore().load();
		}else if(Ext.getCmp('tabpanel3301').getActiveTab().id=='tabAutoCheck3301')
		{
			var strWheresql = {
					str : null,
					strSendFlag : "auto",
					strOwnerNo:Ext.getCmp('cmbOwnerNoSend3401').getValue()
			};
			Ext.apply(Ext.getCmp('gridOutstockMWaveNoCheck3301')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridOutstockMWaveNoCheck3301').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockMWaveNoCheck3301').getStore()
					.load();
		}
		
	},
	
	//拣货发单自动成单》货主选择
	cmbOwnerNoSend3301Select:function()
    {
		var listDetail  = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoSend3301').getValue()))
		{
			//有混合业主的情况 如果为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNoSend3301').getValue()!='ALL'){
				var strDtl = {
					    columnId:'ood.owner_no',
						value:Ext.getCmp('cmbOwnerNoSend3301').getValue()
					};
					listDetail.push(strDtl);
			}
			
			var strJson = Ext.encode(listDetail);
			var strWheresql = {
				str : strJson,
				strSendFlag : "auto"
			};
			Ext.apply(Ext.getCmp('cmbExp_typeSend3301')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbExp_typeSend3301').getStore()
					.removeAll();
			Ext.getCmp('cmbExp_typeSend3301').getStore()
					.load();
			Ext.apply(Ext.getCmp('gridOutstockMWaveNoSend3301')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridOutstockMWaveNoSend3301').getStore()
			.removeAll();
	        Ext.getCmp('gridOutstockMWaveNoSend3301').getStore()
			.load();
				
		}else
		{
			Ext.getCmp('cmbExp_typeSend3301').getStore().removeAll();
			Ext.getCmp('cmbExp_typeSend3301').setValue(null);
		}
	
		//loadgrid_01_3301();
    },
    
    //拣货发单自动成单》关闭前事件
	odata_OutstockMAutoSendUIBeforeclose:function()
	{
		Ext.getCmp('cmbOwnerNoSend3301').store.removeAll();
	},
	
	//拣货发单自动成单》出货单别下拉选择
	cmbExp_typeSendChange:function()
	{
       var listDetail = [];
       listDetail = Ext.decode(Ext.getCmp('cmbExp_typeSend3301').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3301').getValue()))
       {
    	   var strdtl={
    				columnId:'ood.exp_type',
    				value:Ext.getCmp('cmbExp_typeSend3301').getValue()
    			};
    	       listDetail.push(strdtl);
    			var strJson = Ext.encode(listDetail);
    			var strWheresql = {
    				str : strJson,
    				strSendFlag:'auto',
    				strOwnerNo:Ext.getCmp('cmbOwnerNoSend3301').getValue()
    			};
    			Ext.apply(Ext.getCmp('gridOutstockMWaveNoSend3301')
    					.getStore().proxy.extraParams,
    					strWheresql);
    			Ext.getCmp('gridOutstockMWaveNoSend3301').getStore()
    					.removeAll();
    			Ext.getCmp('gridOutstockMWaveNoSend3301').getStore()
    					.load();
       }else
       {
    	   Ext.getCmp('gridOutstockMWaveNoSend3301').getStore().removeAll();
       }
		
	},
	
	//波次号选择
	gridOutstockMLocateNoCheck3301change:function()
	{
		var objRecord = Ext.getCmp('gridOutstockMWaveNoCheck3301').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockDCheck3301').getStore().removeAll();
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
			Ext.apply(Ext.getCmp('gridOutstockDCheck3301')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDCheck3301').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDCheck3301').getStore()
					.load();
        }
	},
	
	//波次号选择
	gridOutstockMWaveNoSend3301change:function()
	{
		//debugger;
		var objRecord = Ext.getCmp('gridOutstockMWaveNoSend3301').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
        	Ext.getCmp('cmbOperate_type3301').setValue(null);
    		Ext.getCmp('cmbOperate_type3301').getStore().removeAll();
    		Ext.getCmp('gridOutstockMAreaNoSend3301').getStore().removeAll();
    		Ext.getCmp('gridOutstockDSend3301').getStore().removeAll();
        }else{
            var listDetail = [];
            listDetail=Ext.decode(Ext.getCmp('gridOutstockMWaveNoSend3301').getStore().getProxy().extraParams.str);
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
			Ext.apply(Ext.getCmp('cmbOperate_type3301')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cmbOperate_type3301').setValue(null);
			Ext.getCmp('cmbOperate_type3301').getStore()
					.removeAll();
			Ext.getCmp('cmbOperate_type3301').getStore()
					.load();
        }
	},
	
	//拣货发单自动成单》作业类型
	operate_type3301change:function(th,newValue,oldValue,eOpts)
	{
        var listDetail = [];
        listDetail=Ext.decode(Ext.getCmp('cmbOperate_type3301').getStore().getProxy().extraParams.str);
        if(!Ext.isEmpty(Ext.getCmp('cmbOperate_type3301').getValue()))
        {
        	var strDtl={
        			columnId:'ood.operate_type',
        			value:Ext.getCmp('cmbOperate_type3301').getValue()
        		};
        		listDetail.push(strDtl);
        		var strJson = Ext.encode(listDetail);
        		var wheresql = {
        			str : strJson
        		};
        		Ext.apply(Ext.getCmp('gridOutstockMAreaNoSend3301')
        				.getStore().proxy.extraParams,
        				wheresql);
        		Ext.getCmp('gridOutstockMAreaNoSend3301').getStore()
        				.removeAll();
        		Ext.getCmp('gridOutstockMAreaNoSend3301').getStore()
        				.load();
        		
        		/*Ext.apply(Ext.getCmp('gridOutstockDSend3301')
        				.getStore().proxy.extraParams,
        				wheresql);
        		Ext.getCmp('gridOutstockDSend3301').getStore()
        				.removeAll();
        		Ext.getCmp('gridOutstockDSend3301').getStore()
        				.load();*/
        }else
        {
        	Ext.getCmp('gridOutstockMAreaNoSend3301').getStore().removeAll();
    		Ext.getCmp('gridOutstockDSend3301').getStore().removeAll();
        }
	},
	
	menu3301sendclick:function()
	{
		var data=Ext.getCmp('gridOutstockMWaveNoSend3301').getSelectionModel().getSelection();
		var data2=Ext.getCmp('gridOutstockMAreaNoSend3301').getSelectionModel().getSelection();
		//判断单据要根据发单显示明细来 而不是未发单指示显示来 huangb 20160808
		//var objOutstockDCount = Ext.getCmp('gridOutstockDCheck3301').getStore().count();
		var objOutstockDCount = Ext.getCmp('gridOutstockDSend3301').getStore().count();
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
			if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3301').getValue())){
				Ext.example.msg('提示','请输入下架人员');
				return;	
			}
			Ext.Msg.confirm("提示", "确定发单吗？",
			function(button, text) {
				if(button == 'yes')
				{
					var detail=[];
					for(var i=0;i<data2.length;i++){
						Ext.getCmp('butSend3301').setDisabled(true);
					    var msgShow = commonMegShow("正在发单,请稍等...");
						var obj=data2[i];
						var d={
							warehouseNo:Ext.get('warehouseNo').getValue(),
							ownerNo:Ext.getCmp('cmbOwnerNoSend3301').getValue(),
							expType:Ext.getCmp('cmbExp_typeSend3301').getValue(),
							sourceType:data[0].data.sourceType,
							waveNo:data[0].data.waveNo,
							batchNo:data[0].data.batchNo,
							wareaNo:obj.data.wareaNo,
							areaNo:obj.data.areaNo,
							dockNo:workSpaceNo,
							operateType:Ext.getCmp('cmbOperate_type3301').getValue(),
							workerNo:Ext.get('workerNo').getValue(),
							outStockType:data[0].data.outstockType,
							printPaperType:Ext.getCmp('checkBoxPrint_type3301').getValue()==false ? 0:1,
							//printFaceSheet:Ext.getCmp('printFaceSheet3301').getValue()==false ? 0:1,
							//printBuilt:Ext.getCmp('printBuilt3301').getValue()==false ? 0:1,
							//printInvoice:Ext.getCmp('printInvoice3301').getValue()==false ? 0:1
							printFaceSheet:0,
							printBuilt:0,
							printInvoice:0
							/*printPaperType:'0',
							printFaceSheet:'0',
							printBuilt:'0',
							printInvoice:'0'*/		
						};
						detail.push(d);
					}
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
							Ext.getCmp('butSend3301').setDisabled(false);
							var data = Ext.decode(response.responseText);
							if(data.isSucc)
							{
								Ext.example.msg('提示',data.msg);
								Ext.getCmp('gridOutstockMWaveNoSend3301').getStore()
			    					.load();
							}else
							{
								Ext.Msg.alert($i18n.prompt,data.msg+data.obj);

								//Ext.example.msg('提示',data.msg+data.obj);
							}				
						}
//					,
//						failure:function(response){
//							Ext.getCmp('butSend3301').setDisabled(false);
//							msgShow.hide();
//							Ext.example.msg('提示',"提交不上,可能是网络问题");
//						}
					});
				}
			});
		}
		
	},
	
	//刷新
	menu3301RefreshClick:function()
	{
		this.TabpanelChange();
	}
});

