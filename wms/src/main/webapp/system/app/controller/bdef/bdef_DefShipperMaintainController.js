/**
 * 模块名称：承运商资料维护
 * 模块编码：1801
 * 创建：hj
 */
var g_intRowindex1801 = 0;
var g_strSaveType1801 = 0;//0：新增；1：修改
var flag;
Ext.define('cms.controller.bdef.bdef_DefShipperMaintainController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_DefShipperMaintainUI',
	          'cms.view.bdef.window.bdef_DefShipperMaintainAddorEditWindow'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//新增
			'bdef_DefShipperMaintainUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//修改
			'bdef_DefShipperMaintainUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//浏览
			'bdef_DefShipperMaintainUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//查找
			'bdef_DefShipperMaintainUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			//导出
			'bdef_DefShipperMaintainUI button[name=detailExport]':{
				click:this.detailExport
			},
			//上一条记录
			'bdef_DefShipperMaintainAddorEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条记录
			'bdef_DefShipperMaintainAddorEditWindow button[name=next]':{
				click:this.next
			},
			//加载新增状态
			'bdef_DefShipperMaintainAddorEditWindow button[name=add]':{
				click:this.add
			},
			//保存
			'bdef_DefShipperMaintainAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bdef_DefShipperMaintainAddorEditWindow button[name=close]':{
				click:this.close
			},
			//验证承运商编号唯一性
			'bdef_DefShipperMaintainAddorEditWindow form textfield[id=txtShipperNo1801]':{
				blur:this.shipperNoBlur
			},//到期日离开
			'bdef_DefShipperMaintainAddorEditWindow datefield[id=dateEndDate]':{
				blur:this.scanEndDateBlur2
			},//删除承运商
			'bdef_DefShipperMaintainUI button[name=detailDelete]':{
				click:this.detailDelete
			},//查询按扭
			'bdef_DefShipperMaintainUI button[id=btnSearch1801]':{
				click:this.btnSearch1801Click
			}
		});
	},
	
	/*
	 * 删除承运商
	 * hj
	 */
	detailDelete:function (){
		var data = Ext.getCmp('grid_01_1801').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
    			if(button=='yes')
				{
    				Ext.Ajax.request({
    					url : 'bdef_DefShipperAction_delete.action',
    					params : {
    						shipperNo:data[0].get('shipperNo')
    					},
    					success : function(response) {
							var data=Ext.decode(response.responseText);					
							if(data.isSucc){
								Ext.getCmp('grid_01_1801').getStore().load();
								Ext.example.msg($i18n.prompt,data.msg);
							}else{
								Ext.example.msg($i18n.prompt,data.msg);							
							} 						
    					}
    				});
				}			
          });
	  }else{
		  Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	  }
	},
	
	//查询  7-7添加  hj
	btnSearch1801Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('shipperNo1801').getValue())&&Ext.getCmp('shipperNo1801').getValue()!='ALL'){
			var d2={
					columnId:'bds.shipper_no',
					value:Ext.getCmp('shipperNo1801').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusText1801').getValue())){
			var d2={
					columnId:'bds.status',
					value:Ext.getCmp('statusText1801').getValue()
				};
			listDetail1.push(d2);
		}
 		if(!Ext.isEmpty(Ext.getCmp('shipperType1801').getValue())){
			var d2={
					columnId:'bds.shipper_type',   
					value:Ext.getCmp('shipperType1801').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('shipperNo1801').getValue()
			};
		Ext.apply(Ext.getCmp('grid_01_1801').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1801').getStore().removeAll();
		Ext.getCmp('grid_01_1801').getStore().load();
		
	},
	
	//到期日离开
	scanEndDateBlur2:function(field,value){
		//debugger;
		if(flag == 'browser'){
			
		}else{
			if(!Ext.isDate(Ext.getCmp('dateEndDate').getValue()))
			{
				Ext.getCmp('dateEndDate').setValue('');
				return;
			}
			if(!Ext.isEmpty(Ext.getCmp('dateEndDate').getValue()))
			{
				if(Ext.getCmp('dateEndDate').getValue()<new Date()){
					Ext.example.msg($i18n.prompt,'到期日不能小于今天');
					Ext.getCmp('dateEndDate').setValue('');
					return;
				}
				if(!this.compareExpDate2())
				{
					Ext.example.msg($i18n.prompt,'到期日不能小于合同日期');
					Ext.getCmp('dateEndDate').setValue('');
				}
			}else{
				//Ext.getCmp("outStockCheckSecond3701").setValue('');
				//Ext.getCmp("customNumber3702").setValue('');
			}
		}
	},
	
	compareExpDate2:function(){
		if(!Ext.isEmpty(Ext.getCmp('dateCompactDate1801').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dateEndDate').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dateCompactDate1801').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dateEndDate').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	
	//新增
	detailAdd:function(){
		flag = 'add';
		Ext.create('cms.view.bdef.window.bdef_DefShipperMaintainAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addbdef_shipper1801();
		addCommMenu5('bdef_MenuWidget1801');
		g_strSaveType1801 = 0;
	},
	
	//修改
	detailEdit:function(){
		flag = 'edit';
		var objData=Ext.getCmp('grid_01_1801').getSelectionModel().getSelection();
		if(objData.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_DefShipperMaintainAddorEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			Ext.getCmp('txtShipperNo1801').disable();
			g_intRowindex1801 = objData[0].index;
			loadShipperData1801(g_intRowindex1801);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1801','grid_01_1801',g_intRowindex1801);
			updateCommMenu5('bdef_MenuWidget1801');
			g_strSaveType1801 = 1;
		}
	},
	
	//浏览
	detailBrowse:function(){
		//debugger;
		flag = 'browser';
		var objData = Ext.getCmp('grid_01_1801').getSelectionModel().getSelection();
		if (objData.length != 0) {
			Ext.create('cms.view.bdef.window.bdef_DefShipperMaintainAddorEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
			g_intRowindex1801 = objData[0].index;
			loadShipperData1801(g_intRowindex1801);
			Ext.getCmp('txtShipperNo1801').disable();
			commonSetFieldReadOnly('formBdef_DefShipperMaintainAddorEdit1801',true);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1801','grid_01_1801',g_intRowindex1801);
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
        browseCommMenu5('bdef_MenuWidget1801');
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1801';
		queryGrid='grid_01_1801';
	},
	
	detailExport:function(){
		commExport('grid_01_1801');
	},
	
	prev:function(){
		g_intRowindex1801=g_intRowindex1801-1;
		loadShipperData1801(g_intRowindex1801);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1801','grid_01_1801',g_intRowindex1801);
	},
	
	next:function(){
		g_intRowindex1801=g_intRowindex1801+1;
		loadShipperData1801(g_intRowindex1801);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1801','grid_01_1801',g_intRowindex1801);
	},
	
	//新增
	add:function(){
		Ext.getCmp('txtShipperNo1801').enable();
		addbdef_shipper1801();
		g_strSaveType1801=0;
	},
	
	//保存
	save:function(){
		//debugger;
		savebdef_defShipper1801();
	},
	
	close:function(){
		closeCustWindow1801();
	},
	
	//承运商编号校验
	shipperNoBlur:function(){
		if(g_strSaveType1801 == 0){
			Ext.Ajax.request({
				method:'post',
				url:'bdef_DefShipperAction_checkShipperNo',
				params:{
					strShipperNo:Ext.getCmp('txtShipperNo1801').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res == '1'){
			    		Ext.example.msg('提示','该承运商编号已有，请重新录入！');
			    		Ext.getCmp('txtShipperNo1801').setValue('');
			    		Ext.getCmp('txtShipperNo1801').focus();
			    	}
			    }
			});
		}
	},
	
});

//新增初始化         
function addbdef_shipper1801(){
	Ext.getCmp('formBdef_DefShipperMaintainAddorEdit1801').getForm().reset();
	Ext.getCmp('cmbStatus1801').setValue('1');
	
	Ext.getCmp('cmbPaperType1801').setValue('1');
	//Ext.getCmp('cmbPrintType1801').setValue('1');
	Ext.getCmp('cmbSingleLocateFlag1801').setValue('1');
	Ext.getCmp('cmbPaperComifireFlag1801').setValue('1');
	Ext.getCmp('cmbGetPaperType1801').setValue('1');
	Ext.getCmp('cmbShipperType1801').setValue('1');
	
	Ext.getCmp('txtDisprice1801').setValue(0);
	Ext.getCmp('txtGraprice1801').setValue(0);
	Ext.getCmp('numMulti1801').setValue(10);
	Ext.getCmp('numVolprice1801').setValue(0);
	bindEnterSkip($('#formBdef_DefShipperMaintainAddorEdit1801'));//调用键盘处理方法
	Ext.getCmp('txtShipperNo1801').focus(false, 10);
};

//保存承运商信息
function savebdef_defShipper1801(){
	
	//debugger;
	
	if(Ext.getCmp('formBdef_DefShipperMaintainAddorEdit1801').getForm().isValid()){
		var strShipper = {
		    id:{
		    	enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo : Ext.get('warehouseNo').getValue(),
				shipperNo : Ext.getCmp('txtShipperNo1801').getValue()
			},
			shipperName:Ext.getCmp('txtShipperName1801').getValue(),
			reportId:Ext.getCmp('reportId1801').getValue(),
			address:Ext.getCmp('txtAddress1801').getValue(),
			tel:Ext.getCmp('txtTel1801').getValue(),
			contact:Ext.getCmp('txtContact1801').getValue(),
			status:Ext.getCmp('cmbStatus1801').getValue(),
			
			shipperType:Ext.getCmp('cmbShipperType1801').getValue(),  
			paperType:Ext.getCmp('cmbPaperType1801').getValue(),  
			//printType:Ext.getCmp('cmbPrintType1801').getValue(),  
			singleLocateFlag:Ext.getCmp('cmbSingleLocateFlag1801').getValue(),  
			paperComifireFlag:Ext.getCmp('cmbPaperComifireFlag1801').getValue(),
			getPaperType:Ext.getCmp('cmbGetPaperType1801').getValue(),
			
			disprice:Ext.getCmp('txtDisprice1801').getValue(),
			graprice:Ext.getCmp('txtGraprice1801').getValue(),
			compactDate:Ext.getCmp('dateCompactDate1801').getValue(),
			endDate:Ext.getCmp('dateEndDate').getValue(),
			multi:Ext.getCmp('numMulti1801').getValue(),
			volprice:Ext.getCmp('numVolprice1801').getValue(),
			memo:Ext.getCmp('txtMemo1801').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(strShipper);
		Ext.Ajax.request({
			url:'bdef_DefShipperAction_saveOrUpdateShipperMaintain.action',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1801').getStore().load();
					Ext.getCmp('txtShipperNo1801').disable();
					commonSetFieldReadOnly('formBdef_DefShipperMaintainAddorEdit1801',true);
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}else{
		Ext.example.msg($i18n.prompt,'请输入必填项！');
	}
};

//关闭客户窗口
function closeCustWindow1801(){
	Ext.getCmp('bdef_DefShipperMaintainAddorEditWindow').close();
};

//填充数据
function loadShipperData1801(g_intRowindex1801){
	//debugger;
	var shipper = Ext.getCmp('grid_01_1801').getStore().
	getAt(g_intRowindex1801-(Ext.getCmp('grid_01_1801').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('txtShipperNo1801').setValue(shipper.data.shipperNo);
	Ext.getCmp('txtShipperName1801').setValue(shipper.data.shipperName);
	Ext.getCmp('txtAddress1801').setValue(shipper.data.address);
	Ext.getCmp('txtTel1801').setValue(shipper.data.tel);
	Ext.getCmp('txtContact1801').setValue(shipper.data.contact);
	Ext.getCmp('cmbStatus1801').setValue(shipper.data.status);
	Ext.getCmp('txtDisprice1801').setValue(shipper.data.disprice);
	Ext.getCmp('txtGraprice1801').setValue(shipper.data.graprice);
	Ext.getCmp('dateCompactDate1801').setValue(shipper.data.compactDate);
	Ext.getCmp('dateEndDate').setValue(shipper.data.endDate);
	Ext.getCmp('numVolprice1801').setValue(String(shipper.data.volprice));
	Ext.getCmp('txtMemo1801').setValue(shipper.data.memo);
	
	Ext.getCmp('reportId1801').setValue(shipper.data.reportId);
	Ext.getCmp('cmbShipperType1801').setValue(shipper.data.shipperType);
	Ext.getCmp('cmbPaperType1801').setValue(shipper.data.paperType);
	//Ext.getCmp('cmbPrintType1801').setValue(shipper.data.printType);
	Ext.getCmp('cmbSingleLocateFlag1801').setValue(shipper.data.singleLocateFlag);
	Ext.getCmp('cmbPaperComifireFlag1801').setValue(shipper.data.paperComifireFlag);
	Ext.getCmp('cmbGetPaperType1801').setValue(shipper.data.getPaperType);
	Ext.getCmp('numMulti1801').setValue(shipper.data.multi);
	//Ext.getCmp('cmbSingleLocateFlag1801').setValue(shipper.data.singleLoccateFlag);
};





















