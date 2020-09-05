/**
 * 模块名称：设备
 * 模块编码：1S01
 * 创建：chensr
 */
var groupRowIndex=0;
var divideMRowIndex=0;
var divideDRowIndex=0;
var saveType1S01='';
Ext.define('cms.controller.bdef.bdef_DeviceDivideController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_DeviceDivideUI'],
	model:'cms.model.cdef.cdef_DefWareModel',
	store:'',
	init:function(){
		this.control({//新增
			'bdef_DeviceDivideUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'bdef_DeviceDivideUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bdef_DeviceDivideUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//tab切换
			'bdef_DeviceDivideUI tabpanel[id=tab1S01]':{
				tabchange:this.tabchange
			},//设备组新增（window）
			'DeviceDivideGroupAddOrEditWindow button[name=add]':{
				click:this.addDeviceDivideGroup
			},//保存设备组
			'DeviceDivideGroupAddOrEditWindow button[name=save]':{
				click:this.saveDeviceDivideGroup
			},//关闭设备组（window）
			'DeviceDivideGroupAddOrEditWindow button[name=close]':{
				click:this.closeDeviceDivideGroup
			},//设备组上一页
			'DeviceDivideGroupAddOrEditWindow button[name=prev]':{
				click:this.prevDeviceDivideGroup
			},//设备组下一页
			'DeviceDivideGroupAddOrEditWindow button[name=next]':{
				click:this.nextDeviceDivideGroup
			},//双击浏览
			'bdef_DeviceDivideUI grid[id=DeviceDivideGroup1S01]':{
				itemdblclick:this.detailBrowse
			},
	        ////////////////设备//////////////////////////////////////////
			//选择设备组
			'bdef_DeviceDivideUI combo[id=DeviceDivideGroupCombo1S01]':{
				change:this.DeviceDivideGroupChange
			},//新增设备（window）
			'DeviceDivideMAddOrEditWindow button[name=add]':{
				click:this.addDeviceDivideM
			},//关闭设备（window）
			'DeviceDivideMAddOrEditWindow button[name=close]':{
				click:this.closeDeviceDivideM
			},//设备的唯一性
			'DeviceDivideMAddOrEditWindow form textfield[id=deviceNo1S01]':{
				blur:this.deviceNo1S01Blur
			},//保存设备
			'DeviceDivideMAddOrEditWindow button[name=save]':{
				click:this.saveDeviceDivideM
			},//设备上一页
			'DeviceDivideMAddOrEditWindow button[name=prev]':{
				click:this.prevDeviceDivideM
			},//设备上下页
			'DeviceDivideMAddOrEditWindow button[name=next]':{
				click:this.nextDeviceDivideM
			},//双击浏览
			'bdef_DeviceDivideUI grid[id=deviceDivideMGrid1S01]':{
				itemdblclick:this.detailBrowse
			},
			/////////////////////格子号////////////////////////////////
			//选择设备组加载设备
			'bdef_DeviceDivideUI combo[id=DeviceDivideGroupDCombo1S01]':{
				change:this.DeviceDivideGroupDCombo1S01Change
			},//选择设备组加载设备（window）
			'DeviceDivideDAddOrEditWindow combo[id=DeviceDivideGroupD1S01]':{
				change:this.DeviceDivideGroupD1S01Change
			},//设备格子（window）
			'DeviceDivideDAddOrEditWindow button[name=add]':{
				click:this.addDeviceDivideD
			},//关闭格子（window）
			'DeviceDivideDAddOrEditWindow button[name=close]':{
				click:this.closeDeviceDivideD
			},//新增格子（window）
			'DeviceDivideDAddOrEditWindow button[name=save]':{
				click:this.saveDeviceDivideD
			},//根据设备组和设备，获取格子号明细
			'bdef_DeviceDivideUI combo[id=deviceNoDCombo1S01]':{
				change:this.deviceNoDCombo1S01Change
			},//修改pickOrder
			'bdef_DeviceDivideUI grid[id=deviceDivideDGrid1S01]':{
				edit:this.changePickOrder
			},//修改设备号
			'DeviceDivideDEditWindow button[name=save]':{
				click:this.editDeviceDivideD
			},//关闭设备号（window）
			'DeviceDivideDEditWindow button[name=close]':{
				click:this.closeEditDeviceDivideD
			},//上一页设备号（window）
			'DeviceDivideDEditWindow button[name=prev]':{
				click:this.prevDeviceDivideD
			},//下一页设备号（window）
			'DeviceDivideDEditWindow button[name=next]':{
				click:this.nextDeviceDivideD
			}
//			,//双击浏览
//			'bdef_DeviceDivideUI grid[id=deviceDivideDGrid1S01]':{
//				itemdblclick:this.detailBrowse
//			}
			,//货位校验                                          
			'DeviceDivideDAddOrEditWindow textfield[id=perfix1S01]':{
				blur:this.checkCell
			},
			'DeviceDivideDAddOrEditWindow textfield[id=bayXMin1S01]':{
				blur:this.checkCell
			},
			'DeviceDivideDAddOrEditWindow textfield[id=bayXMax1S01]':{
				blur:this.checkCell
			},
			'DeviceDivideDAddOrEditWindow textfield[id=floorMin1S01]':{
				blur:this.checkCell
			},
			'DeviceDivideDAddOrEditWindow textfield[id=floorMax1S01]':{
				blur:this.checkCell
			}
		});
	},
	
	//新增
	detailAdd:function(){
		if(Ext.getCmp('tab1S01').getActiveTab().id=="DeviceDivideGroup1S01")
		{			
			Ext.create('cms.view.bdef.window.DeviceDivideGroupAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			commonSetMsterReadOnlyByArray(
					new Array('deviceGroupNo1S01','UseType1S01'),false);
			Ext.getCmp('status1S01').setValue('1');
			addCommMenu5('menuWidget1S01');
		}else if(Ext.getCmp('tab1S01').getActiveTab().id=="deviceDivideM1S01"){
			saveType1S01='add';
			Ext.create('cms.view.bdef.window.DeviceDivideMAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			commonSetMsterReadOnlyByArray(
					new Array('DeviceDivideGroupM1S01','deviceNo1S01'),false);
			Ext.getCmp('statusM1S01').setValue('0');
			Ext.getCmp('maxQty1S01').setValue(1);
			Ext.getCmp('boxItems1S01').setValue(1);
			Ext.getCmp('useTimes1S01').setValue(1);
			Ext.getCmp('custQty1S01').setValue(1);
			addCommMenu5('menuWidgetM1S01');					
		}else{	
			Ext.create('cms.view.bdef.window.DeviceDivideDAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
	
			initDivideD();	
			addCommMenu5('menuWidgetD1S01');
		}
	},
	//修改
	detailEdit:function(){
		if(Ext.getCmp('tab1S01').getActiveTab().id=="DeviceDivideGroup1S01")
		{		
			var data = Ext.getCmp('DeviceDivideGroup1S01').getSelectionModel().getSelection();
			if(data.length != 0){
				Ext.create('cms.view.bdef.window.DeviceDivideGroupAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show();
				groupRowIndex=data[0].index;
				loadDeviceDivideGroup(groupRowIndex);
				commonSetCommMenu5PrevOrNext('menuWidget1S01','DeviceDivideGroup1S01',groupRowIndex);
				commonSetMsterReadOnlyByArray(
						new Array('deviceGroupNo1S01','UseType1S01'),true);
				updateCommMenu5('menuWidget1S01');
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}			
		}else if(Ext.getCmp('tab1S01').getActiveTab().id=="deviceDivideM1S01"){
			
			saveType1S01='edit';
			var data = Ext.getCmp('deviceDivideMGrid1S01').getSelectionModel().getSelection();
			if(data.length != 0){
				Ext.create('cms.view.bdef.window.DeviceDivideMAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show();
				divideMRowIndex=data[0].index;
				loadDeviceDivideM(divideMRowIndex);
				commonSetCommMenu5PrevOrNext('menuWidgetM1S01','deviceDivideMGrid1S01',divideMRowIndex);
				commonSetMsterReadOnlyByArray(
						new Array('DeviceDivideGroupM1S01','deviceNo1S01'),true);
				updateCommMenu5('menuWidgetM1S01');
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}				
		}else{
			var data = Ext.getCmp('deviceDivideDGrid1S01').getSelectionModel().getSelection();
			if(data.length != 0){
				Ext.create('cms.view.bdef.window.DeviceDivideDEditWindow',{
					title:$i18n.titleupdate
				}).show();
				divideDRowIndex=data[0].index;
				loadDeviceDivideD(divideDRowIndex);
				commonSetCommMenu5PrevOrNext('menuWidgetDEdit1S01','deviceDivideDGrid1S01',divideDRowIndex);
				updateCommMenu5('menuWidgetDEdit1S01');
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}		
		}
	},
	//浏览
	detailBrowse:function(){
		if(Ext.getCmp('tab1S01').getActiveTab().id=="DeviceDivideGroup1S01")
		{		
			var data = Ext.getCmp('DeviceDivideGroup1S01').getSelectionModel().getSelection();
			if(data.length != 0){
				Ext.create('cms.view.bdef.window.DeviceDivideGroupAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show();
				groupRowIndex=data[0].index;
				loadDeviceDivideGroup(groupRowIndex);
				commonSetFormReadOnly('DivideGroupForm1S01',true);
				commonSetCommMenu5PrevOrNext('menuWidget1S01','DeviceDivideGroup1S01',groupRowIndex);
				browseCommMenu5('menuWidget1S01');
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
			}			
		}else if(Ext.getCmp('tab1S01').getActiveTab().id=="deviceDivideM1S01"){
			saveType1S01='edit';
			var data = Ext.getCmp('deviceDivideMGrid1S01').getSelectionModel().getSelection();
			if(data.length != 0){
				Ext.create('cms.view.bdef.window.DeviceDivideMAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show();
				divideMRowIndex=data[0].index;
				loadDeviceDivideM(divideMRowIndex);
				commonSetFormReadOnly('DeviceDivideMF11S01',true);
				commonSetFormReadOnly('DeviceDivideMF21S01',true);
				commonSetCommMenu5PrevOrNext('menuWidgetM1S01','deviceDivideMGrid1S01',divideMRowIndex);
				browseCommMenu5('menuWidgetM1S01');
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}	
		
		}else{
			var data = Ext.getCmp('deviceDivideDGrid1S01').getSelectionModel().getSelection();
			if(data.length != 0){
				Ext.create('cms.view.bdef.window.DeviceDivideDEditWindow',{
					title:$i18n.titleupdate
				}).show();
				divideDRowIndex=data[0].index;
				loadDeviceDivideD(divideDRowIndex);
				commonSetFormReadOnly('DeviceDivideDF11S01',true);
				commonSetFormReadOnly('DeviceDivideDF21S01',true);
				commonSetCommMenu5PrevOrNext('menuWidgetDEdit1S01','deviceDivideDGrid1S01',divideDRowIndex);
				browseCommMenu5('menuWidgetDEdit1S01');
			}
		}
	},
	//tab切换
	tabchange:function(){
		if(Ext.getCmp('tab1S01').getActiveTab().id=="deviceDivideM1S01"){
			Ext.getCmp('deviceDivideMGrid1S01').getStore().removeAll();
			Ext.getCmp('deviceDivideMGrid1S01').getStore().load();
		}else if(Ext.getCmp('tab1S01').getActiveTab().id=="deviceDivideD1S01"){
			var strDetail = [];
			var d={
					columnId:'a.use_type',
					value:'2'
				};
			strDetail.push(d);
			var strQuery = {
					strQuery : Ext.encode(strDetail)
				};
			Ext.apply(Ext.getCmp('DeviceDivideGroupDCombo1S01').getStore().proxy.extraParams,strQuery);
			Ext.getCmp('DeviceDivideGroupDCombo1S01').getStore().removeAll();
			Ext.getCmp('DeviceDivideGroupDCombo1S01').getStore().load();
		}
	},
	//设备组新增（window）
	addDeviceDivideGroup:function(){
		Ext.getCmp('DivideGroupForm1S01').getForm().reset();
		Ext.getCmp('status1S01').setValue('1');
	},
	//保存设备组
	saveDeviceDivideGroup:function(){
		if(!Ext.getCmp('DivideGroupForm1S01').getForm().isValid()){
			Ext.example.msg($i18n_prompt.prompt,'请输入必填项！');
		}else{
			var str={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						deviceGroupNo:Ext.getCmp('deviceGroupNo1S01').getValue(),
						useType:Ext.getCmp('UseType1S01').getValue()
					},
					deviceGroupName:Ext.getCmp('deviceGroupName1S01').getValue(),
					defaultFlag:Ext.getCmp('defaultFlag1S01').getValue(),
					//computeFlag:Ext.getCmp('computeFlag1S01').getValue(),				
					status:Ext.getCmp('status1S01').getValue(),				
					rgstName:Ext.get('workerNo').getValue(),
					rgstDate:new Date()
				};
				Ext.Ajax.request({
					url:'Divice_DivideAction_saveDeviceDivideGroup',
					method:'post',
					params:{
						str:Ext.encode(str)
					},
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.getCmp('DeviceDivideGroup1S01').getStore().load();
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
		}
	},
	//关闭设备组（window）	
	closeDeviceDivideGroup:function(){
		Ext.getCmp('DeviceDivideGroupAddOrEditWindow').close();
	},	
	//设备组上一页
	prevDeviceDivideGroup:function(){
		groupRowIndex=groupRowIndex-1;
		loadDeviceDivideGroup(groupRowIndex);
		commonSetCommMenu5PrevOrNext('menuWidget1S01','DeviceDivideGroup1S01',groupRowIndex);
	},
	//设备组下一页
	nextDeviceDivideGroup:function(){
		groupRowIndex=groupRowIndex+1;
		loadDeviceDivideGroup(groupRowIndex);
		commonSetCommMenu5PrevOrNext('menuWidget1S01','DeviceDivideGroup1S01',groupRowIndex);
	},
	//选择设备组加载设备
	DeviceDivideGroupChange:function(){		
		var strDetail = [];
		var d={
				columnId:'a.device_group_no',
				value:Ext.getCmp('DeviceDivideGroupCombo1S01').getValue()
			};
		if(!Ext.isEmpty(Ext.getCmp('DeviceDivideGroupCombo1S01').getValue())){
			strDetail.push(d);
		}
			
		var strQuery = {
			strQuery : Ext.encode(strDetail)
		};
		Ext.apply(Ext.getCmp('deviceDivideMGrid1S01').getStore().proxy.extraParams,strQuery);
		Ext.getCmp('deviceDivideMGrid1S01').getStore().removeAll();
		Ext.getCmp('deviceDivideMGrid1S01').getStore().load();	
	},	
	//设备组新增（window）
	addDeviceDivideM:function(){
		Ext.getCmp('DeviceDivideMForm1S01').getForm().reset();
		Ext.getCmp('statusM1S01').setValue('0');
		Ext.getCmp('maxQty1S01').setValue(1);
		Ext.getCmp('boxItems1S01').setValue(1);
		Ext.getCmp('useTimes1S01').setValue(1);
		Ext.getCmp('custQty1S01').setValue(1);
	},	
	//关闭设备（window）	
	closeDeviceDivideM:function(){
		Ext.getCmp('DeviceDivideMAddOrEditWindow').close();
	},
	//设备唯一性
	deviceNo1S01Blur:function(){
		if(saveType1S01=='add'){
			Ext.Ajax.request({
				method:'post',
				url:'Divice_DivideAction_checkDeviceNo1S01',
				params:{
					str:Ext.getCmp('deviceNo1S01').getValue()
				},
				success:function(response){
					var res = Ext.decode(response.responseText);
					if(res=='1'){
						Ext.example.msg('提示','该设备编码已存在，请重新录入！');
						Ext.getCmp('deviceNo1S01').setValue('');
						Ext.getCmp('deviceNo1S01').focus();
			    	}
			    }
			});	
		}
	},
	//保存设备
	saveDeviceDivideM:function(){
		var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
				    deviceNo:Ext.getCmp('deviceNo1S01').getValue()
				},
				deviceGroupNo:Ext.getCmp('DeviceDivideGroupM1S01').getValue(),
				operateType:Ext.getCmp('operateType1S01').getValue(),
				deviceType:Ext.getCmp('deviceType1S01').getValue(),				
				deviceName:Ext.getCmp('deviceName1S01').getValue(),				                        
				deviceAlias:Ext.getCmp('deviceAlias1S01').getValue(),
				maxQty:Ext.getCmp('maxQty1S01').getValue(),
				status:Ext.getCmp('statusM1S01').getValue(), 
				grade:Ext.getCmp('grade1S01').getValue(), 
				boxItems:Ext.getCmp('boxItems1S01').getValue(),
				useTimes:Ext.getCmp('useTimes1S01').getValue(),
				custQty:Ext.getCmp('custQty1S01').getValue(),			
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				produceCapacity:Ext.getCmp('Produce_Capacity1S01').getValue(),	
				produceCapacityType:Ext.getCmp('Produce_Capacity_type1S01').getValue(),	
				diviceRate:Ext.getCmp('divice_rate1S01').getValue(),	
				strategyId:Ext.getCmp('STRATEGY_ID1S01').getValue(),	
				refRateFlag:Ext.getCmp('REF_RATE_flag1S01').getValue()
			};
			Ext.Ajax.request({
				url:'Divice_DivideAction_saveDeviceDivideM',
				method:'post',
				params:{
					str:Ext.encode(str)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('deviceDivideMGrid1S01').getStore().load();
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});	
	},
	//设备组上一页
	prevDeviceDivideM:function(){
		divideMRowIndex=divideMRowIndex-1;
		loadDeviceDivideM(divideMRowIndex);
		commonSetCommMenu5PrevOrNext('menuWidgetM1S01','deviceDivideMGrid1S01',divideMRowIndex);
	},
	//设备组下一页
	nextDeviceDivideM:function(){
		divideMRowIndex=divideMRowIndex+1;
		loadDeviceDivideM(divideMRowIndex);
		commonSetCommMenu5PrevOrNext('menuWidgetM1S01','deviceDivideMGrid1S01',divideMRowIndex);
	},
	//根据设备组加载设备
	DeviceDivideGroupDCombo1S01Change:function(){
		var strDetail = [];
		var d={
				columnId:'a.device_group_no',
				value:Ext.getCmp('DeviceDivideGroupDCombo1S01').getValue()
			};
		strDetail.push(d);
		var strQuery = {
				strQuery : Ext.encode(strDetail)
			};
		Ext.apply(Ext.getCmp('deviceNoDCombo1S01').getStore().proxy.extraParams,strQuery);
		Ext.getCmp('deviceNoDCombo1S01').getStore().removeAll();
		Ext.getCmp('deviceNoDCombo1S01').getStore().load();
	},
	
	//根据设备组加载设备(window)
	DeviceDivideGroupD1S01Change:function(){
		var strDetail = [];
		var d={
				columnId:'a.device_group_no',
				value:Ext.getCmp('DeviceDivideGroupD1S01').getValue()
			};
		strDetail.push(d);
		var strQuery = {
				strQuery : Ext.encode(strDetail)
			};
		Ext.apply(Ext.getCmp('deviceNoD1S01').getStore().proxy.extraParams,strQuery);
		Ext.getCmp('deviceNoD1S01').getStore().removeAll();
		Ext.getCmp('deviceNoD1S01').getStore().load();
	},
	
	//新增格子号
	addDeviceDivideD:function(){
		Ext.getCmp('DeviceDivideDForm1S01').getForm().reset();
		initDivideD();	
	},
	
	//关闭格子号
	closeDeviceDivideD:function(){
		Ext.getCmp('DeviceDivideDAddOrEditWindow').close();
	},
	
	//保存格子号
	saveDeviceDivideD:function(){
		var str={
				deviceGroupNo:Ext.getCmp('DeviceDivideGroupD1S01').getValue(),
				deviceNo:Ext.getCmp('DeviceDivideGroupD1S01').getValue(),
				mixFlag:Ext.getCmp('mixFlag1S01').getValue(),
				mixSupplier:Ext.getCmp('mixSupplier1S01').getValue(),
				maxQty:Ext.getCmp('maxQty1S01').getValue(),
				maxWeight:Ext.getCmp('maxWeight1S01').getValue(),
				maxVolume:Ext.getCmp('maxVolume1S01').getValue(),
				maxCase:Ext.getCmp('maxCase1S01').getValue(),
				status:Ext.getCmp('statusD1S01').getValue()				
			};
			Ext.Ajax.request({
				url:'Divice_DivideAction_saveDeviceDivideD',
				method:'post',
				params:{
					str:Ext.encode(str),
					perfix:Ext.getCmp('perfix1S01').getValue(),
					bayXMin:Ext.getCmp('bayXMin1S01').getValue(),
					bayXMax:Ext.getCmp('bayXMax1S01').getValue(),
					floorMin:Ext.getCmp('floorMin1S01').getValue(),
					floorMax:Ext.getCmp('floorMax1S01').getValue()
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('deviceDivideDGrid1S01').getStore().load();						
					}
					Ext.example.msg($i18n.prompt,data.msg);
				}
			});
	},
	
	//根据设备、设备组获取格子号
	deviceNoDCombo1S01Change:function(){
		var strDetail = [];
		var d={
				columnId:'a.device_group_no',
				value:Ext.getCmp('DeviceDivideGroupDCombo1S01').getValue()
			};
		strDetail.push(d);
		
		d={
				columnId:'a.device_no',
				value:Ext.getCmp('deviceNoDCombo1S01').getValue()		
		};
		strDetail.push(d);
		var strQuery = {
				strQuery : Ext.encode(strDetail)
			};
		Ext.apply(Ext.getCmp('deviceDivideDGrid1S01').getStore().proxy.extraParams,strQuery);
		Ext.getCmp('deviceDivideDGrid1S01').getStore().removeAll();
		Ext.getCmp('deviceDivideDGrid1S01').getStore().load();
	
	},	
	//修改pickOrder 
	changePickOrder:function(editor,e,eOpts){
		if(e.field=='pickOrder'){
		    var index=editor.context.record.index;
		    var data=Ext.getCmp('deviceDivideDGrid1S01').getStore().getAt(index);
			var master={					
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					deviceNo:data.get('deviceNo'),
					deviceCellNo:data.get('deviceCellNo'),					
					pickOrder:Ext.getCmp('pickOrder1S01').getValue(),
					deviceGroupNo:data.get('deviceGroupNo')
			};
			var params={
				str:Ext.encode(master)	
			};			
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'Divice_DivideAction_changePickOrder',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('deviceDivideDGrid1S01').getStore().removeAll();
						Ext.getCmp('deviceDivideDGrid1S01').getStore().load();					
					}
				}
			});					
		}		
	},
	
	//修改设备号
	editDeviceDivideD:function(){
		var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
				    deviceNo:Ext.getCmp('deviceNoDEdit1S01').getValue(),
				    deviceCellNo:Ext.getCmp('deviceCellNo1S01').getValue()				    
				},
				deviceGroupNo:Ext.getCmp('DeviceDivideGroupDEdit1S01').getValue(),
				bayX:Ext.getCmp('bayX1S01').getValue(),
				stockY:Ext.getCmp('stockY1S01').getValue(),
				mixFlag:Ext.getCmp('mixFlagEdit1S01').getValue(),
				mixSupplier:Ext.getCmp('mixSupplierEdit1S01').getValue(),
				maxQty:Ext.getCmp('maxQtyEdit1S01').getValue(),
				maxVolume:Ext.getCmp('maxVolumeEdit1S01').getValue(),
				maxWeight:Ext.getCmp('maxWeightEdit1S01').getValue(),
				maxCase:Ext.getCmp('maxCaseEdit1S01').getValue(),
				status:Ext.getCmp('statusDEdit1S01').getValue(),
				checkStatus:Ext.getCmp('checkStatusDEdit1S01').getValue(),
				pickOrder:Ext.getCmp('pickOrderD1S01').getValue(),
				rgstName:Ext.getCmp('rgstName1S01').getValue(),
				rgstDate:Ext.getCmp('rgstDate1S01').getValue(),
				updtName:Ext.get('workerNo').getValue(),
				updtDate:new Date()			
		};
		Ext.Ajax.request({
			url:'Divice_DivideAction_editDeviceDivideD',
			method:'post',
			params:{
				str:Ext.encode(str)
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('deviceDivideDGrid1S01').getStore().load();						
				}
				Ext.example.msg($i18n.prompt,data.msg);
			}
		});	
	},
	
	//关闭格子号（window Edit）
	closeEditDeviceDivideD:function(){
		Ext.getCmp('DeviceDivideDEditWindow').close();
	},
	
	//上一页格子号（window）
	prevDeviceDivideD:function(){
		divideDRowIndex=divideDRowIndex-1;
		loadDeviceDivideD(divideDRowIndex);		
		commonSetCommMenu5PrevOrNext('menuWidgetDEdit1S01','deviceDivideDGrid1S01',divideDRowIndex);
	},
	
	//下一页格子号（window）
	nextDeviceDivideD:function(){
		divideDRowIndex=divideDRowIndex+1;
		loadDeviceDivideD(divideDRowIndex);		
		commonSetCommMenu5PrevOrNext('menuWidgetDEdit1S01','deviceDivideDGrid1S01',divideDRowIndex);
	},
	
	//检验位
	checkCell:function(){
		var perfix ='';
		var bayXMin ='';
		var bayXMax ='';
		var floorMin='';
		var floorMax='';
		
		if(Ext.getCmp('bayXMin1S01').getValue()!=null && Ext.getCmp('bayXMin1S01').getValue()!='' &&
		   Ext.getCmp('bayXMax1S01').getValue()!=null && Ext.getCmp('bayXMax1S01').getValue()!=''){
				bayXMin=Ext.getCmp('bayXMin1S01').getValue();
				bayXMax=Ext.getCmp('bayXMax1S01').getValue();				
				if(!(parseInt(bayXMin)<=parseInt(bayXMax) && !isNaN(bayXMin) && !isNaN(bayXMax))){
					if(isNaN(bayXMin)){
						Ext.getCmp('bayXMin1S01').setValue("");
					}else if(isNaN(bayXMax)){
						Ext.getCmp('bayXMax1S01').setValue("");
					}else{
						Ext.getCmp('bayXMin1S01').setValue("");
						Ext.getCmp('bayXMax1S01').setValue("");
					}
					Ext.example.msg($i18n.prompt,'最小位不能大于最大位，并且不能有字母');
					return;
				}
		}else if(Ext.getCmp('bayXMin1S01').getValue()!=null &&
				 Ext.getCmp('bayXMax1S01').getValue()==null ||
				 Ext.getCmp('bayXMin1S01').getValue()==null &&
				 Ext.getCmp('bayXMax1S01').getValue()!=null ||
				 Ext.getCmp('bayXMin1S01').getValue()!="" &&
				 Ext.getCmp('bayXMax1S01').getValue()==""||
				 Ext.getCmp('bayXMin1S01').getValue()=="" &&
				 Ext.getCmp('bayXMax1S01').getValue()!=""){
			return;
		}
		
		if((Ext.getCmp('floorMin1S01').getValue()!=null && Ext.getCmp('floorMin1S01').getValue()!='' &&
			Ext.getCmp('floorMax1S01').getValue()!=null && Ext.getCmp('floorMax1S01').getValue()!='')){
				floorMin=Ext.getCmp('floorMin1S01').getValue();
				floorMax=Ext.getCmp('floorMax1S01').getValue();
				
				if(!(parseInt(floorMin)<=parseInt(floorMax) && !isNaN(floorMin) && !isNaN(floorMax))){
					if(isNaN(floorMin)){
						Ext.getCmp('floorMin1S01').setValue("");
					}else if(isNaN(floorMax)){
						Ext.getCmp('floorMax1S01').setValue("");
					}else{
						Ext.getCmp('floorMin1S01').setValue("");
						Ext.getCmp('floorMax1S01').setValue("");
					}
					Ext.example.msg($i18n.prompt,'最小层不能大于最大层，并且不能有字母');
					return;
				}
		}else if(Ext.getCmp('floorMin1S01').getValue()!=null &&
				 Ext.getCmp('floorMax1S01').getValue()==null ||
				 Ext.getCmp('floorMin1S01').getValue()==null &&
				 Ext.getCmp('floorMax1S01').getValue()!=null ||
				 Ext.getCmp('floorMin1S01').getValue()!="" &&
				 Ext.getCmp('floorMax1S01').getValue()==""||
				 Ext.getCmp('floorMin1S01').getValue()=="" &&
				 Ext.getCmp('floorMax1S01').getValue()!=""){
			return;
		}
		
		Ext.Ajax.request({			
			method:'post',
			url:'Divice_DivideAction_checkCell',

			params : {
				perfix:Ext.getCmp('perfix1S01').getValue(),
				bayXMin:bayXMin,
				bayXMax:bayXMax,
				floorMin:floorMin,
				floorMax:floorMax,
				strDeviceDivideGroup:Ext.getCmp('DeviceDivideGroupD1S01').getValue(),
				strDeviceNo:Ext.getCmp('deviceNoD1S01').getValue(),			
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.msg=='1'){
					Ext.example.msg($i18n.prompt,'设置位有重复，请注意');
				}else if(data.msg=='2'){
					Ext.example.msg($i18n.prompt,'设置层有重复，请注意');
					Ext.getCmp('floorMin1S01').setValue("");
					Ext.getCmp('floorMax1S01').setValue("");
				}
			
			}
		});	
	}
	
});

//加载设备组信息
function loadDeviceDivideGroup(groupRowIndex){
	var record=Ext.getCmp('DeviceDivideGroup1S01').getStore().getAt(groupRowIndex);	
	Ext.getCmp('deviceGroupNo1S01').setValue(record.data.deviceGroupNo);
	Ext.getCmp('status1S01').setValue(record.data.status);
	Ext.getCmp('deviceGroupName1S01').setValue(record.data.deviceGroupName);	
	Ext.getCmp('UseType1S01').setValue(record.data.useType);	
	//Ext.getCmp('computeFlag1S01').setValue(record.data.computeFlag);
	Ext.getCmp('defaultFlag1S01').setValue(record.data.defaultFlag);	
}

//加载设备信息
function loadDeviceDivideM(divideMRowIndex){
	var record=Ext.getCmp('deviceDivideMGrid1S01').getStore().getAt(divideMRowIndex);	
	Ext.getCmp('DeviceDivideGroupM1S01').setValue(record.data.deviceGroupNo);
	Ext.getCmp('deviceNo1S01').setValue(record.data.deviceNo);
	Ext.getCmp('deviceName1S01').setValue(record.data.deviceName);	
	Ext.getCmp('deviceAlias1S01').setValue(record.data.deviceAlias);	
	Ext.getCmp('statusM1S01').setValue(record.data.status);
	Ext.getCmp('grade1S01').setValue(record.data.grade);	
	Ext.getCmp('deviceType1S01').setValue(record.data.deviceType);	
	Ext.getCmp('operateType1S01').setValue(record.data.operateType);	
	Ext.getCmp('maxQty1S01').setValue(record.data.maxQty);
	Ext.getCmp('boxItems1S01').setValue(record.data.boxItems);	
	Ext.getCmp('useTimes1S01').setValue(record.data.useTimes);	
	Ext.getCmp('custQty1S01').setValue(record.data.custQty);	
	
	Ext.getCmp('Produce_Capacity1S01').setValue(record.data.produceCapacity);	
	Ext.getCmp('Produce_Capacity_type1S01').setValue(record.data.produceCapacityType);
	Ext.getCmp('divice_rate1S01').setValue(record.data.diviceRate);	
	Ext.getCmp('REF_RATE_flag1S01').setValue(record.data.refRateFlag);	
	Ext.getCmp('STRATEGY_ID1S01').setValue(record.data.strategyId);	
}

//初始化格子号
function initDivideD(){
	var strDetail = [];
//	var d={
//			columnId:'a.use_type',
//			value:'2'
//		};
	strDetail.push(d);
	var strQuery = {
			strQuery : Ext.encode(strDetail)
		};
	Ext.apply(Ext.getCmp('DeviceDivideGroupD1S01').getStore().proxy.extraParams,strQuery);
	Ext.getCmp('DeviceDivideGroupD1S01').getStore().removeAll();
	Ext.getCmp('DeviceDivideGroupD1S01').getStore().load();	
	Ext.getCmp('statusD1S01').setValue('0');
}

//加载格子号信息
function loadDeviceDivideD(divideDRowIndex){
	var record=Ext.getCmp('deviceDivideDGrid1S01').getStore().getAt(divideDRowIndex);	
	
	Ext.getCmp('DeviceDivideGroupDEdit1S01').setValue(record.data.deviceGroupNo);
	Ext.getCmp('deviceNoDEdit1S01').setValue(record.data.deviceNo);	
	Ext.getCmp('deviceCellNo1S01').setValue(record.data.deviceCellNo);	
	Ext.getCmp('statusDEdit1S01').setValue(record.data.status);		
	Ext.getCmp('checkStatusDEdit1S01').setValue(record.data.checkStatus);			
	Ext.getCmp('bayX1S01').setValue(record.data.bayX);		
	Ext.getCmp('stockY1S01').setValue(record.data.stockY);	
	Ext.getCmp('maxQtyEdit1S01').setValue(record.data.maxQty);			
	Ext.getCmp('maxVolumeEdit1S01').setValue(record.data.maxVolume);			
	Ext.getCmp('maxWeightEdit1S01').setValue(record.data.maxWeight);		
	Ext.getCmp('maxCaseEdit1S01').setValue(record.data.maxCase);	
	Ext.getCmp('mixSupplierEdit1S01').setValue(record.data.mixSupplier);			
	Ext.getCmp('mixFlagEdit1S01').setValue(record.data.mixFlag+"");	
	Ext.getCmp('pickOrderD1S01').setValue(record.data.pickOrder);
	Ext.getCmp('rgstName1S01').setValue(record.data.rgstName);	
	Ext.getCmp('rgstDate1S01').setValue(record.data.rgstDate);
}




