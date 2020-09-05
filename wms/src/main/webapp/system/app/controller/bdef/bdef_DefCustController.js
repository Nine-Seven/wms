/**
 * 模块名称：客户资料维护
 * 模块编码：1501
 * 创建：Jun
 */
var rowindex1501=0;
var saveType1501=0;
Ext.define('cms.controller.bdef.bdef_DefCustController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_DefCustUI'
	          //'cms.view.bdef.window.bdef_DefCustAddorEditWindow'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'bdef_DefCustUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'bdef_DefCustUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bdef_DefCustUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'bdef_DefCustUI button[name=detailQuery]':{
				click:this.detailQuery
			},//删除
			'bdef_DefCustUI button[name=detailDelete]':{
				click:this.detailDelete
			},//导出
			'bdef_DefCustUI button[name=detailExport]':{
				click:this.detailExport
			},//上一条记录
			'bdef_DefCustAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_DefCustAddorEditWindow button[name=next]':{
				click:this.next
			},//加载新增状态
			'bdef_DefCustAddorEditWindow button[name=add]':{
				click:this.add
			},//保存
			'bdef_DefCustAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bdef_DefCustAddorEditWindow button[name=close]':{
				click:this.close
			},//验证货主客户代码唯一性
			'bdef_DefCustAddorEditWindow form textfield[id=ownerCustNo1501]':{
				blur:this.ownerCustNoBlur
			},//验证客户编号唯一性
			'bdef_DefCustAddorEditWindow form textfield[id=custNo1501]':{
				blur:this.custNoBlur
			},
			//状态选择
			'bdef_DefCustUI combo[id=statusText1501]':{
				select:this.selectStatus
			},
			//商品编码选择
			'remoteCombo[id=custInfoNo1501]':{
				beforequery:this.custNoBeforeQuery
			},
			//查询按扭
			'bdef_DefCustUI button[id=btnSearch1501]':{
				click:this.btnSearch1501Click
			},//出货生产日期控制
			'bdef_DefCustAddorEditWindow textfield[id=control_value1501]':{
				blur:this.controlValue
			},//选择生产日期控制类型
			'bdef_DefCustAddorEditWindow combo[id=control_type1501]':{
				select:this.controlType
			}
		});
	},
	
	//选择生产日期控制类型
	controlType:function(){
		 var contorlType=Ext.getCmp('control_type1501').getValue();
		  if(contorlType=='1' || contorlType=='2'){
				commonSetMsterReadOnlyByArray(
						new Array('control_value1501'),false);
		  }else{
			  Ext.getCmp('control_value1501').setValue(0);
			  commonSetMsterReadOnlyByArray(
						new Array('control_value1501'),true);
		  }
	},
	
	//出货生产日期控制
	controlValue:function(){
	  var contorlType=Ext.getCmp('control_type1501').getValue();
	  if(contorlType=='1' || contorlType=='2'){
		  if(Ext.getCmp('control_value1501').getValue()=="" || Ext.getCmp('control_value1501').getValue()==null ){
			  Ext.example.msg('提示','出货生产日期控制值不能为空，必须大于0！');
			  return;
		  }
		  
		  if(Ext.getCmp('control_value1501').getValue()<=0){
			  Ext.example.msg('提示','出货生产日期控制值必须大于0！');
			  Ext.getCmp('control_value1501').setValue("");
			  return;
		  }
	  }
	  
	},
	//商品资料维护-商品编码加载 
	custNoBeforeQuery:function(){
 		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1501').getValue())
 				&&Ext.getCmp('cmbFormOwner1501').getValue()!='ALL'){
			var d1={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1501').getValue()
				};
			listDetail1.push(d1);
		}
 		if(!Ext.isEmpty(Ext.getCmp('statusText1501').getValue())){
			var d1={
					columnId:'a.status',
					value:Ext.getCmp('statusText1501').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				strQuery:Ext.encode(listDetail1), 
				str:Ext.getCmp('custInfoNo1501').getValue()//客户编码
			};
		Ext.apply(Ext.getCmp('custInfoNo1501').getStore().proxy.extraParams,params);
		Ext.getCmp('custInfoNo1501').getStore().removeAll();
		Ext.getCmp('custInfoNo1501').getStore().load();
  	  },
	//状态选择
	selectStatus:function(){
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1501').getValue())){
			var strDetail11 = [];
			var d2={
					columnId:'a.owner_no'	,
					value:Ext.getCmp('cmbFormOwner1501').getValue()
						
			};
			strDetail11.push(d2);
			var params={
					strQuery:Ext.encode(strDetail11),
					strOwnerNo:Ext.getCmp('cmbFormOwner1501').getValue()
				};
			Ext.apply(Ext.getCmp('statusText1501').getStore().proxy.extraParams,params);
			Ext.getCmp('statusText1501').getStore().removeAll();
			Ext.getCmp('statusText1501').getStore().load();
		}					
	},
	//查询
	btnSearch1501Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1501').getValue())&&Ext.getCmp('cmbFormOwner1501').getValue()!='ALL'){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1501').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusText1501').getValue())){
			var d2={
					columnId:'a.status',
					value:Ext.getCmp('statusText1501').getValue()
				};
			listDetail1.push(d2);
		}
 		if(!Ext.isEmpty(Ext.getCmp('custInfoNo1501').getValue())){
			var d2={
					columnId:'a.cust_no',
					value:Ext.getCmp('custInfoNo1501').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('cmbFormOwner1501').getValue()
			};
		Ext.apply(Ext.getCmp('grid_01_1501').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1501').getStore().removeAll();
		Ext.getCmp('grid_01_1501').getStore().load();
		
	},
	
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_DefCustAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addbdef_cust1501();
		addCommMenu5('bdef_MenuWidget1501');
		saveType1501=0;
	},
	
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1501').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_DefCustAddorEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			Ext.getCmp('custNo1501').disable();
			Ext.getCmp('ownerCustNo1501').disable();
			rowindex1501=data[0].index;
			loadCustData1501(rowindex1501);
			//commonSetCommMenu5PrevOrNext('bdef_MenuWidget1501','grid_01_1501',rowindex1501);
			updateCommMenu5('bdef_MenuWidget1501');
			saveType1501=1;
		}
	},
	
	detailBrowse:function(){
		var data = Ext.getCmp('grid_01_1501').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.create('cms.view.bdef.window.bdef_DefCustAddorEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
			rowindex1501=data[0].index;
			loadCustData1501(rowindex1501);
			commonSetFieldReadOnly('bdef_DefCustAddorEditForm',true);
			Ext.getCmp('ownerCustNo1501').setDisabled(true);
			Ext.getCmp('custNo1501').setDisabled(true);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1501','grid_01_1501',rowindex1501);
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
        browseCommMenu5('bdef_MenuWidget1501');
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=1501;
		queryGrid='grid_01_1501';
	},
	
	detailExport:function(){
		commExport('grid_01_1501');
	},
	
	detailDelete:function(){
		var data=Ext.getCmp('grid_01_1501').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);
		}else{
			Ext.Msg.confirm($i18n.prompt,$i18n.prompt_sure_delete,function(button,text){
				if(button=='yes'){
					Ext.Ajax.request({
						url:'bdef_DefCustAction_deleteCust.action',
						params:{
							'custId':data[0].get('custNo')
						},
						success:function(response){
							var data=Ext.decode(response.responseText);					
							if(data.isSucc){
								Ext.getCmp('grid_01_1501').getStore().load();
								Ext.example.msg($i18n.prompt,data.msg);
							}else{
								Ext.example.msg($i18n.prompt,data.msg);							
							}
						}
					});
				}
			});
		}
	},
	
	prev:function(){
		rowindex1501=rowindex1501-1;
		loadCustData1501(rowindex1501);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1501','grid_01_1501',rowindex1501);
	},
	
	next:function(){
		rowindex1501=rowindex1501+1;
		loadCustData1501(rowindex1501);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1501','grid_01_1501',rowindex1501);
	},
	
	add:function(){
		addbdef_cust1501();
		saveType1501=0;
	},
	
	save:function(){
		savebdef_cust1501();
	},
	
	close:function(){
		closeCustWindow1501();
	},
	//验证货主客户代码
	ownerCustNoBlur:function(){
		debugger;
			Ext.Ajax.request({
				method:'post',
				url:'bdef_DefCustAction_checkOwnerCustNo',
				params:{
					strOwnerCustNo:Ext.getCmp('ownerCustNo1501').getValue(),
					strOwnerNo:Ext.getCmp('ownerNo1501').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg('提示','该货主客户代号已有，请重新录入！');
			    		Ext.getCmp('ownerCustNo1501').setValue('');
			    		Ext.getCmp('ownerCustNo1501').focus();
			    	}
			    }
			});
	},
	custNoBlur:function(){
		if(saveType1501==0){
			Ext.Ajax.request({
				method:'post',
				url:'bdef_DefCustAction_checkCustNo',
				params:{
					custNo:Ext.getCmp('custNo1501').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg('提示','该客户编号已有，请重新录入！');
			    		Ext.getCmp('custNo1501').setValue('');
			    		Ext.getCmp('custNo1501').focus();
			    	}
			    }
			});
		}
	}
});

//新增初始化
function addbdef_cust1501(){
	Ext.getCmp('bdef_DefCustAddorEditForm').getForm().reset();
	Ext.getCmp('custType1501').setValue('0');
	Ext.getCmp('status1501').setValue('1');
	Ext.getCmp('prioType1501').setValue('0');
	Ext.getCmp('boxDeliver1501').setValue('0');
	Ext.getCmp('if_main_customer1501').setValue('0');
	Ext.getCmp('shipping_method1501').setValue('0');
	Ext.getCmp('container_material1501').setValue('11');
	Ext.getCmp('collect_flag1501').setValue('1');
	Ext.getCmp('only_date_flag1501').setValue('0');
	Ext.getCmp('warn_flag1501').setValue('Y');
	Ext.getCmp('max_car_tonnage1501').setValue(0);
	Ext.getCmp('ownerNo1501').setValue('001');
	Ext.getCmp('prioLevel1501').setValue(0);
	bindEnterSkip($('#bdef_DefCustAddorEditForm'));//调用键盘处理方法
	Ext.getCmp('cust_alias1501').focus(false, 10);
};

//保存客户
function savebdef_cust1501(){
	if(!commonCheckIsInputAll('bdef_DefCustAddorEditForm')){
		return;
	}
	if(Ext.getCmp('bdef_DefCustAddorEditForm').getForm().isValid()){
		  var contorlType=Ext.getCmp('control_type1501').getValue();
		  if(contorlType=='1' || contorlType=='2'){
			  if(Ext.getCmp('control_value1501').getValue()=="" || Ext.getCmp('control_value1501').getValue()==null ){
				  Ext.example.msg('提示','出货生产日期控制值不能为空，必须大于0！');
				  return;
			  }
			  
			  if(Ext.getCmp('control_value1501').getValue()<=0){
				  Ext.example.msg('提示','出货生产日期控制值必须大于0！');
				  Ext.getCmp('control_value1501').setValue("");
				  return;
			  }
		  }
		
		if(saveType1501==0){
			Ext.Ajax.request({
				method:'post',
				async:false,
				url:'bdef_DefCustAction_checkCustNo',
				params:{
					custNo:Ext.getCmp('custNo1501').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg('提示','该客户编号已有，请重新录入！');
			    		Ext.getCmp('custNo1501').setValue('');
			    		Ext.getCmp('custNo1501').focus();
			    		return;
			    	}
			    }
			});
		}
		var cust={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				custNo:Ext.getCmp('custNo1501').getValue()
			},
			ownerNo:Ext.getCmp('ownerNo1501').getValue(),
			custType:Ext.getCmp('custType1501').getValue(),
			custAlias:Ext.getCmp('cust_alias1501').getValue(),
			ownerCustNo:Ext.getCmp('ownerCustNo1501').getValue(),	
			custName:Ext.getCmp('custName1501').getValue(),
			custProvince:Ext.getCmp('custProvince1501').getValue(),
			custCity:Ext.getCmp('custCity1501').getValue(),
			custZone:Ext.getCmp('cust_zone1501').getValue(),
			custFlag:Ext.getCmp('if_main_customer1501').getValue(),
			boxDeliver:Ext.getCmp('boxDeliver1501').getValue(),
			status:Ext.getCmp('status1501').getValue(),
			prioType:Ext.getCmp('prioType1501').getValue(),
			shippingMethod:Ext.getCmp('shipping_method1501').getValue(),
			containerMaterial:Ext.getCmp('container_material1501').getValue(),
			collectFlag:Ext.getCmp('collect_flag1501').getValue(),
			onlyDateFlag:Ext.getCmp('only_date_flag1501').getValue(),
			warnFlag:Ext.getCmp('warn_flag1501').getValue(),
			deliveryAddress:Ext.getCmp('delivery_address1501').getValue(),
			custPhone1:Ext.getCmp('custPhone11501').getValue(),
			custFax1:Ext.getCmp('cust_fax11501').getValue(),
			invoiceNo:Ext.getCmp('invoice_no1501').getValue(),
			contactorName1:Ext.getCmp('contact1501').getValue(),
			maxCarTonnage:Ext.getCmp('max_car_tonnage1501').getValue(),
			prioLevel:Ext.getCmp('prioLevel1501').getValue(),
			custEmail1:Ext.getCmp('cust_email1501').getValue(),
			invoiceHeader:Ext.getCmp('invoice_header1501').getValue(),
			receivingHours:Ext.getCmp('receiving_hours1501').getValue(),
			createFlag:'1',
			custPostcode:Ext.getCmp('cust_postcode1501').getValue(),
			custAddress:Ext.getCmp('cust_address1501').getValue(),
			invoiceAddr:Ext.getCmp('invoice_addr1501').getValue(),
			remark:Ext.getCmp('memo1501').getValue(),
			custPhone1:Ext.getCmp('custPhone11501').getValue(),
			tradeFlag:'0',
			custNotecode:Ext.getCmp('cust_noteCode11501').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date(),
			controlType:Ext.getCmp('control_type1501').getValue(),
			controlValue:Ext.getCmp('control_value1501').getValue()
		};
		var str=Ext.encode(cust);
		Ext.Ajax.request({
			url:'bdef_DefCustAction_saveOrUpdateTactics.action',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1501').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};


//关闭客户窗口
function closeCustWindow1501(){
	Ext.getCmp('bdef_DefCustAddorEditWindow').close();
};

//填充数据
function loadCustData1501(rowindex1501){
	var cust=Ext.getCmp('grid_01_1501').getStore().
	getAt(rowindex1501-(Ext.getCmp('grid_01_1501').
	getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('ownerNo1501').setValue(cust.data.ownerNo);
	Ext.getCmp('custType1501').setValue(String(cust.data.custType));
	Ext.getCmp('cust_alias1501').setValue(cust.data.custAlias);
	Ext.getCmp('ownerCustNo1501').setValue(cust.data.ownerCustNo);
	Ext.getCmp('custNo1501').setValue(cust.data.custNo);
	Ext.getCmp('custName1501').setValue(cust.data.custName);
	Ext.getCmp('custProvince1501').setValue(cust.data.custProvince);
	Ext.getCmp('custCity1501').setValue(cust.data.custCity);
	Ext.getCmp('cust_zone1501').setValue(cust.data.custZone);
	Ext.getCmp('if_main_customer1501').setValue(cust.data.custFlag);
	Ext.getCmp('prioLevel1501').setValue(cust.data.prioLevel);
	Ext.getCmp('boxDeliver1501').setValue(cust.data.boxDeliver);
	/*Ext.getCmp('status1501').getStore().add
	({
		value:cust.data.status,
		dropValue:"["+cust.data.status+"]"+cust.data.statusText,
		text:cust.data.statusText
    });*/
	Ext.getCmp('status1501').setValue(cust.data.status);
	Ext.getCmp('prioType1501').setValue(String(cust.data.prioType));
	/*Ext.getCmp('prioType1501').getStore().add
	({
		value:cust.data.prioType,
		dropValue:"["+cust.data.prioType+"]"+cust.data.priotypeText,
		text:cust.data.priotypeText
    });*/
	Ext.getCmp('prioType1501').setValue(cust.data.prioType);
	Ext.getCmp('shipping_method1501').setValue(cust.data.shippingMethod);
	Ext.getCmp('container_material1501').setValue(cust.data.containerMaterial);
	Ext.getCmp('collect_flag1501').setValue(cust.data.collectFlag);
	Ext.getCmp('only_date_flag1501').setValue(cust.data.onlyDateFlag);
	Ext.getCmp('warn_flag1501').setValue(cust.data.warnFlag);
	Ext.getCmp('delivery_address1501').setValue(cust.data.deliveryAddress);
	Ext.getCmp('custPhone11501').setValue(cust.data.custPhone1);
	Ext.getCmp('cust_fax11501').setValue(cust.data.custFax1);
	Ext.getCmp('invoice_no1501').setValue(cust.data.invoiceNo);
	Ext.getCmp('contact1501').setValue(cust.data.contactorName1);
	Ext.getCmp('max_car_tonnage1501').setValue(cust.data.maxCarTonnage);
	Ext.getCmp('cust_email1501').setValue(cust.data.custEmail1);
	Ext.getCmp('invoice_header1501').setValue(cust.data.invoiceHeader);
	Ext.getCmp('receiving_hours1501').setValue(cust.data.receivingHours);
	Ext.getCmp('cust_postcode1501').setValue(cust.data.custPostcode);
	Ext.getCmp('cust_address1501').setValue(cust.data.custAddress);
	Ext.getCmp('invoice_addr1501').setValue(cust.data.invoiceAddr);
	Ext.getCmp('cust_noteCode11501').setValue(cust.data.custNotecode);
	Ext.getCmp('memo1501').setValue(cust.data.remark);
	Ext.getCmp('control_type1501').setValue(cust.data.controlType);
	Ext.getCmp('control_value1501').setValue(cust.data.controlValue);
};