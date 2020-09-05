/**
 * 模块名称：返配扫描验收
 * 模块编码：6201
 * 创建：周欢
 */
var g_strIsCanEdit6201=false;
Ext.define('cms.controller.ridata.ridata_CheckController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_CheckUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//新增
			'ridata_CheckUI button[name=add]':{
				click:this.add
			},
			//修改
			'ridata_CheckUI button[name=edit]':{
				click:this.edit
			},
			//撤消
			'ridata_CheckUI button[name=undo]':{
				click:this.undo
			},
			//保存
			'ridata_CheckUI button[name=save]':{
				click:this.save
			},
			//查找
			'ridata_CheckUI button[name=query]':{
				click:this.query
			},
			//刷新
			'ridata_CheckUI button[name=refresh]':{
				click:this.refresh
			},
			//货主选择事件
			'ridata_CheckUI bdef_DefOwnerCombo[id=cmbOwnerNo6201]':{
				click:this.cmbOwnerNo6201Change
			},
			//商品明细编辑 
			'ridata_CheckUI grid[id=gridRidataCheckD6201]':{
				beforeedit:this.checkDbefore,
				edit:this.ridataCheckEdit,
				itemdblclick:this.checkDgridclick
			},
			//原返配单号选择
			'ridata_CheckUI ridata_UntreadNoCombo[id=cmbUntreadNo6201]':{
				beforequery:this.cmbUntreadNo6201beforequery,
				select:this.untreadNoselect
			},
			//扫描条码选择
			'ridata_CheckUI textfield[id=txtBarcode6201]':{
				blur:this.txtBarcode6201Blur
			},
			'ridata_CheckUI field':{
				specialkey:this.boxkeydown
			},
			//封箱界面
			'ridata_CheckUI grid[id=grid_03_6201]':{
				selectionchange:this.grid_03_6201selectionchange
				/*select:this.grid_03_6201Select,
				deselect:this.grid_03_6201Deselect*/
			},
			// 封箱按钮
			'ridata_CheckUI button[id=btnClosePal6201]':{
				click:this.btnClosePal6201Click
			}
		});
	},
	
	//界面初始化
	initializtion:function()
	{
		g_strIsCanEdit6201=false;
		Ext.getCmp('cmbOwnerNo6201').getStore().load();
		
		//显示变量0为不显示，1为显示
		var packingUnit_6201=commonGetModuleField('6201','packingUnit')[0].flag;
		var packingSpec_6201=commonGetModuleField('6201','packingSpec')[0].flag;
		
		if(packingUnit_6201==0){
			Ext.getCmp('packingUnit_6201').setVisible(false);
		}
		if(packingSpec_6201==0){
			Ext.getCmp('packingSpec_6201').setVisible(false);
		}
		
	},
	
	//新增
	add:function(){
		/*g_strIsCanEdit6201=true;
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNo6201','cmbDockNo6201','cmbWorkerNo6201',
				'cmbUntreadNo6201','txtBarcode6201'),false);
		Ext.getCmp('grid_01_6201').getStore().removeAll();
		Ext.getCmp('grid_02_6201').getStore().removeAll();
		Ext.getCmp('cmbOwnerNo6201').getStore().load();
		Ext.getCmp('cmbDockNo6201').focus();*/
	},
	
	//货主选择事件
	cmbOwnerNo6201Change:function()
	{
		/*var listDetail = [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNo6201').getValue()
		};
		listDetail.push(strDtl);
		var strJson  =  Ext.encode(listDetail);
		var strWheresql  =  {
			strOwnerNo:Ext.getCmp('cmbOwnerNo6201').getValue(),
			strWhereSql : strJson
		};
		Ext.apply(Ext.getCmp('cmbUntreadNo6201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbUntreadNo6201').getStore().removeAll();
		Ext.getCmp('cmbUntreadNo6201').getStore().load();*/
	},
	
	//扫描条码选择
	txtBarcode6201Blur:function()
	{
		if(!commonCheckIsInputAll('form_01_6201')){
			Ext.getCmp('txtBarcode6201').setValue(null);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo6201').getValue())){
			Ext.example.msg('提示',"请先输入验收人员");
			Ext.getCmp('txtBarcode6201').setValue(null);
			return;
		}
		if( !Ext.isEmpty(Ext.getCmp('cmbUntreadNo6201').getValue()) &&
		    !Ext.isEmpty(Ext.getCmp('txtBarcode6201').getValue()) )
		{
			var record  = Ext.getCmp('grid_01_6201').getStore().getAt(0);
			var params={
				strBarcode:Ext.getCmp('txtBarcode6201').getValue(),
				strOwnerNo:Ext.getCmp('cmbOwnerNo6201').getValue(),
				strSImportNo:'',
				strSUntreadNo:record.data.SUntreadNo
			};
			var labelNo='';
			var dtOperateDate;
			//  校验条码
			Ext.Ajax.request({
				method:'POST',
				url:'ridata_CheckAction_queryBarcode',
				params:params,
				async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						//获取试算日期
						var record  = Ext.getCmp('grid_01_6201').getStore().getAt(0);
						var strSUntreadNo = record.data.SUntreadNo;
						//获取箱号
						var par={
							strArticleNo:data.obj[0][0],
							strWorkerNo:Ext.getCmp('cmbWorkerNo6201').getValue(),
							strDeviceNo:Ext.getCmp('txtdeviceNo6201').getValue(),
							//dtOperateDate:dtOperateDate,
							strSUntreadNo:strSUntreadNo,
							strOwnerNo:Ext.getCmp('cmbOwnerNo6201').getValue()
						};
						Ext.Ajax.request({
							method:'POST',
							url:'ridata_CheckAction_tscGetLabelNo',
							params:par,
							async:false,
							success:function(response){
								var msg = Ext.decode(response.responseText);
								if(!msg.isSucc){
									Ext.example.msg('提示',msg.msg);
									Ext.getCmp('txtBarcode6201').setValue('');
									return;
								}else{
									var strlabel=msg.obj.split(",");
									Ext.getCmp('lblBoxText6201').setText(strlabel[0]);
									var record2  = Ext.getCmp('grid_01_6201').getStore().getAt(0);
									var master={
										ownerNo:Ext.getCmp('cmbOwnerNo6201').getValue(),
										SUntreadNo:strSUntreadNo,
										articleNo:data.obj[0][0],
										barcode:Ext.getCmp('txtBarcode6201').getValue(),
										packingQty:data.obj[0][2],
										checkQty:1*data.obj[0][2],
										printerGroupNo:'',
										dockNo:Ext.getCmp('cmbDockNo6201').getValue(),
										workerNo:Ext.getCmp('cmbWorkerNo6201').getValue(),
										checkTools:'',
										isAdd:'1',
										labelNo:'N',
										subLabelNo:'N',
										supplierNo:data.obj[0][1],
										qualityFlag:record2.data.qualityFlag,
										deviceNo:Ext.getCmp('txtdeviceNo6201').getValue(),
										labelId:strlabel[0],
										classType : record2.data.classType,
										rsvAttr2 : data.obj[0][4]
									};
									var par={
										jsonDetail:Ext.encode(master)
									};
									Ext.Ajax.request({
										method:'POST',
										url:'ridata_CheckAction_save',
										params:par,
										async:false,
										success:function(response){
											var data = Ext.decode(response.responseText);
											if(data.isSucc){
												Ext.getCmp('grid_01_6201').getStore().removeAll();
												Ext.getCmp('grid_01_6201').getStore().load();
												Ext.getCmp('cmbOwnerNo6201').getStore().load();
												
												var strWhereSql  =  {
													strDeviceNo : Ext.getCmp('txtdeviceNo6201').getValue(),
													strLabelId : strlabel[0]
												};
												Ext.apply(Ext.getCmp('grid_02_6201').getStore().proxy.extraParams,strWhereSql);
												Ext.getCmp('grid_02_6201').getStore().removeAll();
												Ext.getCmp('grid_02_6201').getStore().load();
												Ext.getCmp('txtBarcode6201').setValue('');
												Ext.getCmp('txtBarcode6201').focus();
											}else{
												Ext.example.msg('提示',data.msg);
											}
										}
									});
								}
							}
						});
						//
					}else{
						Ext.example.msg('提示',data.msg);
						Ext.getCmp('txtBarcode6201').setValue('');
						Ext.getCmp('txtBarcode6201').focus();
					}			
				}
			});
			
			/*var listDetail = [];
			var strDtl = {
			columnId:'a.s_untread_no',
			value:Ext.getCmp('cmbUntreadNo6201').getValue()
			};
			listDetail.push(strDtl);
			var strDtl = {
			columnId:'d.barcode',
			value:Ext.getCmp('txtBarcode6201').getValue()
			};
			listDetail.push(strDtl);
			var strJson  =  Ext.encode(listDetail);
			var strWhereSql  =  {
				strWhereSql : strJson
			};
			Ext.Ajax.request({
				method:'POST',
				url:'ridata_CheckAction_getRidata_CheckDList',
				params:strWhereSql,
				success:function(response){
					var listData = Ext.decode(response.responseText);
					if(listData.size != 0)
					{
						var store = Ext.getCmp('grid_02_6201').getStore().queryBy(function(objRecord) {  
							 if(objRecord.get('barcode') == listData.rootList[0].barcode)
				             {
								 return true;
				             }else 
				             {
				            	 return false;
				             }
						});
						if(store.length != 0)
						{
							store.items[0].set('qty',store.items[0].get('qty')+1);
						}else
						{
							var objStore = Ext.getCmp('grid_02_6201').getStore();
							var intCount = objStore.getCount();
							var r=Ext.create('cms.model.ridata.ridata_CheckDModel',
								{barcode:listData.rootList[0].barcode,
								articleName:listData.rootList[0].articleName,qty:1});
							objStore.add(r);
						}
					}
				}
			});*/
		}
	},
	
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) 
		{
			if(th.id=="cmbWorkerNo6201")
	  		{
	  			Ext.getCmp('cmbUntreadNo6201').focus();
	  		}else
  			{
	  			th.nextSibling().focus();
  			}
        }
	},
	
	grid_03_6201selectionchange:function(th,selected,eOpts){
		var record = Ext.getCmp('grid_03_6201').getSelectionModel().getSelection();
        if(record.length == 0){
    		Ext.getCmp('grid_04_6201').getStore().removeAll();
        }else{
            var ids = "";
            for(var i = 0; i < record.length; i++){
                ids += record[i].get("labelNo");
                if(i<record.length-1){
                    ids = ids + "','";
                }
            }
            var strWhereSql  =  {
				strDockNo : Ext.getCmp('cmbDockNo6201').getValue(),
				strLabelNo: "'"+ids+"'"
			};
			Ext.apply(Ext.getCmp('grid_04_6201').getStore().proxy.extraParams,strWhereSql);
			Ext.getCmp('grid_04_6201').getStore().removeAll();
			Ext.getCmp('grid_04_6201').getStore().load();
        }
        
		/*var r = Ext.create('cms.model.ridata.ridata_CheckPalTmpModel', {
		});
		r.set('labelNo',record.data.labelNo);
		r.set('SUntreadNo',record.data.SUntreadNo);
		r.set('SCheckNo',record.data.SCheckNo);
		r.set('articleNo',record.data.articleNo);
		r.set('articleName',record.data.articleName);
		r.set('checkQty',record.data.checkQty);
		Ext.getCmp('grid_04_6201').store.insert(Ext.getCmp('grid_04_6201').getStore().data.length+1,r);*/
		
	},
	
	grid_03_6201Deselect:function(row,record,index,eOpts){
		/*Ext.getCmp('grid_04_6201').getStore().removeAt(
		  Ext.getCmp('grid_04_6201').getStore().find('labelNo',record.data.labelNo));*/
	},
	
	//封板按扭
	btnClosePal6201Click:function(){
		if(!commonCheckIsInputAll('form_01_6201')){
			return;
		}
		var detail1 = [];
		var data = Ext.getCmp('grid_03_6201').getSelectionModel().getSelection();
		
		for(var i=0;i<data.length;i++ )
		{
			var record = Ext.getCmp('grid_03_6201').getSelectionModel().getSelection()[i];
			var d=
			{	
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:Ext.getCmp('cmbOwnerNo6201').getValue(),
				/*SUntreadNo:record.data.SUntreadNo,
				SCheckNo:record.data.SCheckNo,*/
				labelNo:record.data.labelNo,
				workerNo:Ext.getCmp('cmbWorkerNo6201').getValue(),
				dockNo:Ext.getCmp('cmbDockNo6201').getValue()
			};
			detail1.push(d);
		}
		var strDetail = Ext.encode(detail1);
		var params={
			jsonDetail:strDetail
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'ridata_CheckAction_tscClosePal',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('grid_02_6201').getStore().removeAll();
					Ext.getCmp('grid_03_6201').getStore().removeAll();
					Ext.getCmp('grid_03_6201').getStore().load();
					Ext.getCmp('grid_04_6201').getStore().removeAll();
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	    //刷新
		var strWhereSql  =  {
				strSUntreadNo2:Ext.getCmp('cmbUntreadNo6201').getValue()
			};
			Ext.apply(Ext.getCmp('grid_01_6201').getStore().proxy.extraParams,strWhereSql);
			Ext.getCmp('grid_01_6201').getStore().removeAll();
			Ext.getCmp('grid_01_6201').getStore().load();
		
		/*for(var i=0;i<Ext.getCmp('grid_04_6201').getStore().data.length;i++ )
		{
			var record  = Ext.getCmp('grid_04_6201').getStore().getAt(i);
			var d=
			{	
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:Ext.getCmp('cmbOwnerNo6201').getValue(),
				SUntreadNo:record.get('SUntreadNo'),
				SCheckNo:record.get('SCheckNo'),
				labelNo:record.get('labelNo'),
				workerNo:Ext.getCmp('cmbWorkerNo6201').getValue(),
				dockNo:Ext.getCmp('cmbDockNo6201').getValue()
			};
			detail1.push(d);
		}
		var strDetail = Ext.encode(detail1);
		var params={
			jsonDetail:strDetail
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'ridata_CheckAction_tscClosePal',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});*/
	},
	
	//修改
	edit:function(){
		commonMenu4Button('menu6201','edit');
		g_strIsCanEdit6201=true;
	},
	
	//撤消
	undo:function(){
		/*g_strIsCanEdit6201=false;
		commonMenu4Button('menu6201','undo');
		commonSetMsterReadOnlyByArray(
				new Array('cmbOwnerNo6201','cmbDockNo6201','cmbWorkerNo6201',
				'cmbUntreadNo6201','txtBarcode6201'),true);
		Ext.getCmp('cmbUntreadNo6201').setValue(null);
		Ext.getCmp('txtBarcode6201').setValue(null);
		Ext.getCmp('grid_01_6201').getStore().removeAll();
		Ext.getCmp('grid_02_6201').getStore().removeAll();*/
	},
	
	//查找
	query:function()
	{
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=6201;
		queryGrid='gridRidataCheckM6201';	
	},
	
	//保存
	save:function(){
		/*if(Ext.isEmpty(Ext.getCmp('cmbDockNo6201').getValue()))
		{
			Ext.example.msg('提示',"扫描台不能为空，请设置工作站！");
			return;
		};
		if(!commonCheckMster('formCondition6201'))
		{
			return;
		};
		Ext.Msg.confirm("提示", "确定保存？",function(button, text) {
			if (button == 'yes') {
				var sUntreadNo=Ext.getCmp('cmbUntreadNo6201').getValue();
				var dockNo=workSpaceNo;
				var checkWorker=Ext.getCmp('cmbCheck_worker16201').getValue();
				
				var master={
					sUntreadNo:sUntreadNo,
					dockNo:Ext.getCmp('cmbDockNo6201').getValue(),
					checkWorker:checkWorker
				};
				
				var intGridcount=Ext.getCmp("grid_02_6201").getStore().getCount();
				if(intGridcount > 0)
				{
				}else{			
					Ext.example.msg('提示',"表体不允许为空，请输入表体！");
					return;
				}
				var msgShow = commonMegShow("正在保存,请稍等...");
				var listDetail = [];
				for(var i=0;i<intGridcount;i++){
					var objRecord = Ext.getCmp('grid_02_6201').getStore().getAt(i);
					var strDtl={
						id:{
							warehouseNo:Ext.get('warehouseNo'),
							ownerNo:Ext.getCmp("cmbOwnerNo6201").getValue(),
							supplierNo:objRecord.get('supplierNo')
						},
						untreadNo:objRecord.get('untreadNo'),
						articleNo:objRecord.get('articleNo'),
						barcode:objRecord.get('barcode'),
						packingQty:objRecord.get('packingQty'),
						checkQty:objRecord.get('qty')*objRecord.get('packingQty'),
						dockNo:Ext.getCmp("cmbDockNo6201").getValue(),
						workerNo:Ext.getCmp('cmbWorkerNo6201').getValue(),
						expireDate:objRecord.get('expireDate'),
						SUntreadNo:Ext.getCmp("cmbUntreadNo6201").getValue(),
						labelNo:Ext.getCmp('txtBoxNo6201').getValue(),
						subLabelNo:Ext.getCmp('txtBoxNo6201').getValue()
					};
					listDetail.push(strDtl);
				}
				
				var jsonDetail = Ext.encode(listDetail);
				var params = {
					jsonDetail:jsonDetail
				};
				
				Ext.Ajax.request({
					method:'POST',
					url:'ridata_CheckAction_save',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							msgShow.hide();
							commonMenu4Button('menu6201','save');
							Ext.example.msg('提示',data.msg);
							g_strIsCanEdit6201=false;
							commonSetMsterReadOnlyByArray(
								new Array('cmbUntreadNo6201','cmbCheck_worker16201'),
							true);
							Ext.getCmp('cmbCheck_worker16201').setValue(null);
							Ext.getCmp('cmbUntreadNo6201').setValue(null);
							Ext.getCmp('gridRidataCheckD6201').getStore().load();
							msgShow.hide();
						}else{
							msgShow.hide();
							Ext.example.msg('提示',data.msg+data.obj);
						}				
					},
					failure:function(response){
						Ext.example.msg('提示',"提交不上,可能是网络问题");
					}
				});
				}
		});*/
	},
	
	//刷新
	refresh:function(){
		if(Ext.getCmp('tabPId6201').getActiveTab().id=="tabPId6201_T1")
		{
			Ext.getCmp('cmbOwnerNo6201').getStore().load();
		}
		else if(Ext.getCmp('tabPId6201').getActiveTab().id=="tabPId6201_T2")
		{
			var strWhereSql  =  {
				strDeviceNo : Ext.getCmp('txtdeviceNo6201').getValue()
			};
			Ext.apply(Ext.getCmp('grid_03_6201').getStore().proxy.extraParams,strWhereSql);
			Ext.getCmp('grid_03_6201').getStore().removeAll();
			Ext.getCmp('grid_03_6201').getStore().load();
			Ext.getCmp('grid_04_6201').getStore().removeAll();
		}
		
	},
	
	cmbUntreadNo6201beforequery:function(){
		var strWheresql  =  {
			strOwnerNo:Ext.getCmp('cmbOwnerNo6201').getValue()
		};
		Ext.apply(Ext.getCmp('cmbUntreadNo6201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbUntreadNo6201').getStore().removeAll();
		Ext.getCmp('cmbUntreadNo6201').getStore().load();
	},
	
	//原返配单号选择
	untreadNoselect:function(combo){
		/*var params={
			strSUntreadNo2:combo.getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'ridata_CheckAction_getSUntreadNoStatus',
			params:params,
			async:false,
			success:function(response){
				var data = Ext.decode(response.responseText);
				console.log(data);
			}
		});*/
		/*var listDetail = [];
		var strDtl = {
		columnId:'a.s_untread_no',
		value:Ext.getCmp('cmbUntreadNo6201').getValue()
		};
		listDetail.push(strDtl);
		var strJson  =  Ext.encode(listDetail);*/
		if(Ext.isEmpty(Ext.getCmp('txtdeviceNo6201').getValue())){
			Ext.example.msg('提示',"请先输入扫描墙号");
			Ext.getCmp('cmbUntreadNo6201').setValue(null);
			return;
		}
		var strWhereSql  =  {
			strUntreadNo2:combo.getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_6201').getStore().proxy.extraParams,strWhereSql);
		Ext.getCmp('grid_01_6201').getStore().removeAll();
		Ext.getCmp('grid_01_6201').getStore().load();
		//查询预备箱数
		var params=
		{
			strUntreadNo2:combo.getValue(),
		    strDockNo:Ext.getCmp('cmbDockNo6201').getValue(),
		    strUser_Id:Ext.getCmp('cmbWorkerNo6201').getValue(),
		    strDeviceNo:Ext.getCmp('txtdeviceNo6201').getValue(),
		    strOwnerNo:Ext.getCmp('cmbOwnerNo6201').getValue()
		};
		
		Ext.Ajax.request({
			method:'POST',
			url:'ridata_CheckAction_getSupperAllotCell',
			async:false,
			params:params,
			success:function(response)
			{
				var msg = Ext.decode(response.responseText);
				if(!msg.isSucc){
					Ext.example.msg('提示,请检查输入',msg.msg);
					
					//Ext.getCmp('SupperAllotCell6201').setValue('');
					return;
				}else{
					var strlabel=msg.obj.split(",");
					//Ext.getCmp('SupperAllotCell6201').setValue(strlabel[0]);
					Ext.getCmp('SupperAllotCell6201').setText(strlabel[0]);
					//labelNo=strlabel[1];
				}
			}
		});
	}
});

