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
		var stores=Ext.getCmp('gridOutstockDSend3302').getSelectionModel().getStore().data.items;
		//var data2=Ext.getCmp('gridOutstockMAreaNoSend3302').getSelectionModel().getSelection();
		//判断单据要根据发单显示明细来 而不是未发单指示显示来 huangb 20160808
		//var objOutstockDCount = Ext.getCmp('gridOutstockDCheck3302').getStore().count();
		//console.log();

		//console.log(stores);
		/*pick(stores);
		$.ajax({
			url: "localPrinterAciton_getExpNos.action",
			type: "POST",
			dataType: "json",
			data: {waveNo: data[0].data.waveNo},
			success: function (data) {
				printOrder(data)
			}
		});*/

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
						outStockType:data[0].data.outstockType
						/*printPaperType:Ext.getCmp('checkBoxPrint_type3302').getValue()==false ? 0:1,
						printFaceSheet:Ext.getCmp('printFaceSheet3302').getValue()==false ? 0:1,
						printBuilt:Ext.getCmp('printBuilt3302').getValue()==false ? 0:1,
						printInvoice:Ext.getCmp('printInvoice3302').getValue()==false ? 0:1*/
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
							var resultdata = Ext.decode(response.responseText);
							if(resultdata.isSucc)
							{
								Ext.example.msg('提示',resultdata.msg);
								Ext.getCmp('gridOutstockMWaveNoSend3302').getStore()
			    					.load();
								pick(stores);
								$.ajax({
									url: "localPrinterAciton_getExpNos.action",
									type: "POST",
									dataType: "json",
									data: {waveNo: data[0].data.waveNo},
									success: function (data) {
										printOrder(data)
									}
								});

							}else
							{
								Ext.Msg.alert($i18n.prompt,resultdata.msg+resultdata.obj);

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
//打印拣货单 begin
function pick(data){


	var d = new Date();
	var date = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
	//传 base64 或者 url ,但是url地址 必须http或者https开头

	var msg = '{'
		+'"method":"' + 'printreport' +'"'  /*报表类型 gridreport fastreport reportmachine 为空 将默认为gridreport  */

		+',"ReportType":"' + 'gridreport' +'"'  /*报表类型 gridreport fastreport reportmachine 为空 将默认为gridreport  */
		+',"ReportName":"' + 'picking.grf' +'"' /*报表文件名 收费单(自动分页) */

		+',"ReportVersion":"' + '1' +'"' /*可选。报表版本, 为空则默认1  如果本地报表的版本过低 将从 ReportUrl 地址进行下载更新*/
		+',"ReportUrl":"' + '' +'"' /*可选。为空 将不更新本地报表 , 如果本地报表不存在可以从该地址自动下载*/
		//+',"ReportUrl":"' + 'http://localhost/test.grf' +'"' /*可选。为空 将不更新本地报表 , 如果本地报表不存在可以从该地址自动下载*/
		+',"Copies":"' + '1' +'"' /*可选。打印份数，支持指定打印份数。默认1份,如果为零,不打印,只返回报表生成的pdf,jpg等文件*/
		+',"PrinterName":"' + '' +'"' /*可选。指定打印机，为空的话 使用默认打印机, 请在 控制面板 -> 设备和打印机 中查看您的打印机的名称 */
		+',"PrintOffsetX":"' + '0' +'"' /*可选。打印右偏移，单位厘米。报表的水平方向上的偏移量，向右为正，向左为负。*/
		+',"PrintOffsetY":"' + '0' +'"' /*可选。打印下偏移，单位厘米。 报表的垂直方向上的偏移量，向下为正，向上为负。*/
		+',"Preview":"' + '0' +'"'  /*可选。是否预览，和主界面设置的效果一样 为空默认不预览,   0：不预览，1：预览(弹出导出的pdf,jpg等文件)。*/
		+',"token":"' + 'aa' +'"' /*可选。只要token值在列表中 方可打印*/
		+',"taskId":"' + (new Date()).valueOf()+Math.random()*100 +'"' /*可选。多个打印任务同时打印时，根据该id确定返回的是哪个打印任务。 */
		+',"exportfilename":"' + '' +'"'  /*可选。自定义 导出 文件名称 为空 或者 自定义名称 如 test */
		+',"exportfiletype":"' + '' +'"'  /*可选。自定义 导出 文件格式 为空 或者 自定义名称 如 pdf  */

		+',"Control": ['  ///*部件框，可选值：AsStaticBox ,AsMemoBox,AsRichTextBox,AsPictureBox (url或base64格式),AsBarcode*/
		//静态框 AsStaticBox  综合文字框 AsMemoBox, RTF文本框 AsRichTextBox,图像框 AsPictureBox (url或base64格式), 条码二维码 AsBarcode*


		+'{"type": "AsStaticBox", "name": "reporttitle","value": "拣货单","required": false},'
		+'{"type": "AsStaticBox", "name": "id","value": "'+data[0].data.importBatchNo+'","required": false},'
		+'{"type": "AsStaticBox", "name": "username","value": "'+$('#workerName').val()+'","required": false},'
		+'{"type": "AsStaticBox", "name": "date","value": "'+date+'","required": false},'

		+']'

		+',"Field": ['  ///*字段， type ftBlob (base64格式) ,ftString ftInteger ftBoolean, ftFloat, ftCurrency,ftDateTime,  size (ftString 设置为实际长度,其他的设置为0,例如 ftInteger ftBlob 等设置为0 )

		+'{"type": "ftString", "name": "id","size": 255,"required": true},'
		+'{"type": "ftString", "name": "SCellNo","size": 255,"required": false},'
		+'{"type": "ftString", "name": "articleName","size": 255,"required": false},'
		+'{"type": "ftString", "name": "ownerArticleNo","size": 255,"required": false},'
		+'{"type": "ftString", "name": "realQty","size": 255,"required": false},'
		+'{"type": "ftString", "name": "spec","size": 255,"required": false},'
		+'{"type": "ftString", "name": "unit","size": 255,"required": false},'
		+'{"type": "ftString", "name": "beizhu","size": 255,"required": false}'

		+']'

		+',"Data": [' ; ///*数据行
	let n = 1;
	for (let i = 0; i <data.length; i++) {
		if(i>0 && data[i].data.expNo===data[i-1].data.expNo){
			n=n-1;
		}
		msg	+='{"id": "'+n+'", "SCellNo": "'+data[i].data.SCellNo+'","articleName": "'+data[i].data.articleName
			+'","ownerArticleNo": "'+data[i].data.ownerArticleNo+'","realQty": "'+data[i].data.locateQty
			+'","spec": "'+data[i].data.spec+'","unit": "'+data[i].data.packingUnit+'","beizhu": ""},'
		n++;
	}
	msg+=']}';
//	console.log(msg);
	jQuery.support.cors = true;         //写到$.ajax $.get $.post 前面 解决 jQuery.Ajax IE8,9 无效（CORS跨域）
	$.ajax({
		async : false, //循环打印时,　async 必须设置为 false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
		url : "http://127.0.0.1:12345/",
		type : "POST",
		contentType: "application/x-www-form-urlencoded", //要这样设置
		//contentType: "application/json", //错误方式
		dataType : "json", //设置为 json 格式
		//dataType : "text", //设置为 text 格式 也可以,但是返回的结果需要自己解析判断
		//crossDomain: true,  //crossDomain true 或者false 无所谓,不用设置的
		data : msg,
		beforeSend: function (XMLHttpRequest) {
			// beforeSend 函数里 不允许添加任何 东西
			//XMLHttpRequest.setRequestHeader("token", 'abcd1234'); //不允许 使用jQuery发送AJAX请求时在header中添加Token
		},
		success: function(data){
			console.log(JSON.stringify(data));
			if(data.status=="ok"){
				alert("打印成功:"+data.data);
			}else{
				alert("打印失败:"+data.data);
			}
		},
		error: function(data){
			//console.log(status, response);
			console.log(JSON.stringify(data));
			alert("连接HttpPrinter失败:"+JSON.stringify(data));
		}
	});


}
//打印收费单 end

//打印快递单
function printOrder(ids) {

	$.ajax({
		url: "localPrinterAciton_printWaybill.action",
		type: "POST",
		dataType: "json",
		data: {expNos: ids},
		success: function (data) {
			console.log(data);

			pdd(data);

		}
	});
}

//拼多多打印组件
function pdd(data) {

	let owner = data[0].owner;

	let strData = "{\n" +
		"    \"ERPId\":\"" + owner.rsvVar5 + "\",\n" +
		"    \"cmd\":\"print\",\n" +
		"    \"requestID\":\"" + (new Date()).valueOf()+Math.random()*100 + "\",\n" +
		"    \"task\":{\n" +
		"        \"documents\":[\n" ;
	for (let i = 0; i < data.length; i++) {
		let expM = data[i].expMS;
		let expD = data[i].expDS;
		strData +=   "            {\n" +
			"                \"contents\":[\n" +
			"                    {\n" +
			"                        \"encryptedData\":\"" + expM.rsvVarod5 + "\",\n" +
			"                        \"signature\":\"" + expM.rsvVarod6 + "\",\n" +
			"                        \"templateUrl\":\"" + expM.rsvVarod7 + "\",\n" +
			"                        \"userid\":\"" + owner.rsvVar4 + "\",\n" +
			"                        \"ver\":\"" + expM.rsvVarod8 + "\"\n" +
			"                    },{\n" +
			"                        \"data\":{\n \"userdata\":\"";
		if(expM.ownerNo==="ZXNB"){
			for (let n = 0; n < expD.length; n++) {
				strData += expD[n].ownerArticleNo + " * " + expD[n].planBox + '\r\n';
			}
		}
		else{
			for (let n = 0; n < expD.length; n++) {
				strData += expD[n].articleName + " * " + expD[n].planBox + '\r\n';
			}
		}
		strData+="\"},\n" +
			"                        \"templateURL\":\""+owner.rsvVar6+"\"\n" +
			"                    }\n" +
			"                ],\n" +
			"                \"documentID\":\""+expM.sourceexpNo+"\"\n" +
			"            }\n" ;
		if(i<data.length-1) strData +=  ",";
	}
	strData +=  "     ],   \"notifyType\":[\n" +
		"            \"print\"\n" +
		"        ],\n" +
		"        \"preview\":false,\n" +
		"        \"previewType\":\"image\",\n" +
		"        \"printer\":\""+owner.rsvVar7+"\",\n" +
		"        \"taskID\":\""+(new Date()).valueOf()+Math.random()*100+"\"\n" +
		"    },\n" +
		"    \"version\":\"1.0\"\n" +
		"}";



	var ws = new WebSocket("ws://127.0.0.1:5000");

	ws.onopen = function(evt) {
		console.log("打开成功");
		ws.send(strData);
	};

	ws.onmessage = function(evt) {
		console.log( "Received Message: " + evt.data);
		ws.close();
	};

	ws.onclose = function(evt) {
		console.log("服务关闭");
	};
}
//自定义打印组件
function myPrint(data) {
	let expM = data.expMS;
	let expD = data.expDS;
	var msg = '{'
		+ '"method":"' + 'printreport' + '"'  /*报表类型 gridreport fastreport reportmachine 为空 将默认为gridreport  */

		+ ',"ReportType":"' + 'gridreport' + '"'  /*报表类型 gridreport fastreport reportmachine 为空 将默认为gridreport  */
		+ ',"ReportName":"' + expM.shipperNo + '.grf"' /*报表文件名 快递单套打 */
		//	+',"ReportName":"shentong.grf"'
		+ ',"ReportVersion":"' + '1' + '"' /*可选。报表版本, 为空则默认1  如果本地报表的版本过低 将从 ReportUrl 地址进行下载更新*/
		+ ',"ReportUrl":"' + '' + '"' /*可选。为空 将不更新本地报表 , 如果本地报表不存在可以从该地址自动下载*/
		//+',"ReportUrl":"' + 'http://localhost/test.grf' +'"' /*可选。为空 将不更新本地报表 , 如果本地报表不存在可以从该地址自动下载*/
		+ ',"Copies":"' + '1' + '"' /*可选。打印份数，支持指定打印份数。默认1份,如果为零,不打印,只返回报表生成的pdf,jpg等文件*/
		+ ',"PrinterName":""' /*可选。指定打印机，为空的话 使用默认打印机, 请在 控制面板 -> 设备和打印机 中查看您的打印机的名称 */
		+ ',"PrintOffsetX":"' + '0' + '"' /*可选。打印右偏移，单位厘米。报表的水平方向上的偏移量，向右为正，向左为负。*/
		+ ',"PrintOffsetY":"' + '0' + '"' /*可选。打印下偏移，单位厘米。 报表的垂直方向上的偏移量，向下为正，向上为负。*/
		+ ',"Preview":"' + '0' + '"'  /*可选。是否预览，和主界面设置的效果一样 为空默认不预览,   0：不预览，1：预览(弹出导出的pdf,jpg等文件)。*/
		+ ',"token":"' + 'aa' + '"' /*可选。只要token值在列表中 方可打印*/
		+ ',"taskId":"' + (new Date()).valueOf()+Math.random()*100 + '"' /*可选。多个打印任务同时打印时，根据该id确定返回的是哪个打印任务。 */
		+ ',"exportfilename":"' + '' + '"'  /*可选。自定义 导出 文件名称 为空 或者 自定义名称 如 test */
		+ ',"exportfiletype":"' + '' + '"'  /*可选。自定义 导出 文件格式 为空 或者 自定义名称 如 pdf  */
		+ ',"Parameter": ['  ///*参数，type 默认为空即可,已经在报表端设置了 备用字段
		+ '{"type": "", "name": "contactorName","value": "' + expM.contactorName + '","required": false},'
		+ '{"type": "", "name": "custPhone","value": "' + expM.custPhone + '","required": false},'
		+ '{"type": "", "name": "receiveCity","value": "' + expM.receiveCity + '","required": false},'
		+ '{"type": "", "name": "receiveCountry","value": "' + expM.receiveCountry + '","required": false},'
		+ '{"type": "", "name": "receiveProvince","value": "' + expM.receiveProvince + '","required": false},'
		+ '{"type": "", "name": "receiveZone","value": "' + expM.receiveZone + '","required": false},'
		+ '{"type": "", "name": "custAddress","value": "' + expM.custAddress + '","required": false},'
		+ '{"type": "", "name": "sendName","value": "' + expM.sendName + '","required": false},'
		+ '{"type": "", "name": "sendCity","value": "' + expM.sendCity + '","required": false},'
		+ '{"type": "", "name": "sendCountry","value": "' + expM.sendCountry + '","required": false},'
		+ '{"type": "", "name": "sendProvince","value": "' + expM.sendProvince + '","required": false},'
		+ '{"type": "", "name": "sendZone","value": "' + expM.sendZone + '","required": false},'
		+ '{"type": "", "name": "sendAddress","value": "' + expM.sendAddress + '","required": false},'
		+ '{"type": "", "name": "sendCompanyName","value": "' + expM.sendCompanyName + '","required": false},'
		+ '{"type": "", "name": "shipperDeliverNo","value": "' + expM.shipperDeliverNo + '","required": false},'
		+ '{"type": "", "name": "sendMobilePhone","value": "' + expM.sendMobilePhone + '","required": false},'
		+ '{"type": "", "name": "deliverAddress","value": "' + expM.deliverAddress + '","required": false},'
		+ '{"type": "", "name": "expRemark","value": "' + expM.expRemark + '","required": false},'
		+ '{"type": "", "name": "article","value": "';
	for (i = 0; i < expD.length; i++) {
		msg += expD[i].articleName + ' * ' + expD[i].planBox + '\r\n';
	}
	msg += '","required": false},] }';
	//	console.log(msg);
	//发请求到本地打印组件
	jQuery.support.cors = true;         //写到$.ajax $.get $.post 前面 解决 jQuery.Ajax IE8,9 无效（CORS跨域）
	$.ajax({
		async: false, //循环打印时,　async 必须设置为 false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
		url: "http://127.0.0.1:12345/",
		type: "POST",
		contentType: "application/x-www-form-urlencoded", //要这样设置
		//contentType: "application/json", //错误方式
		dataType: "json", //设置为 json 格式
		//dataType : "text", //设置为 text 格式 也可以,但是返回的结果需要自己解析判断
		//crossDomain: true,  //crossDomain true 或者false 无所谓,不用设置的
		data: msg,
		beforeSend: function (XMLHttpRequest) {
			// beforeSend 函数里 不允许添加任何 东西
			//XMLHttpRequest.setRequestHeader("token", 'abcd1234'); //不允许 使用jQuery发送AJAX请求时在header中添加Token
		},
		success: function (data) {
			//	console.log(JSON.stringify(data));
			if (data.status == "ok") {
				console.log("打印成功:" + data.data);
			} else {
				console.log("打印失败:" + data.data);
			}
		},
		error: function (data) {
			//console.log(status, response);
			console.log(JSON.stringify(data));
			alert("连接HttpPrinter失败:" + JSON.stringify(data));
		}
	});
}



