/**
 * 模块名称：车辆信息维护
 * 模块编码：1V01
 * 创建：hcx 
 */
var saveType1V01_1='';
var checkType1V01_1='';
var saveType1V01_2='';
var checkType1V01_2='';
Ext.define('cms.controller.bdef.bdef_DefCarController', {
	extend : 'Ext.app.Controller',
	requires : [ 'cms.view.bdef.bdef_DefCarUI'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({//车辆类型-新增
			'bdef_DefCarUI button[id=add]':{
				click:this.addClick  
			},//车辆类型-修改
			'bdef_DefCarUI button[id=edit]':{
				click:this.editClick  
			},//车辆类型-删除
			'bdef_DefCarUI button[id=delete]':{
				click:this.deleteClick
			},//车辆类型-查找
			'bdef_DefCarUI button[id=query]':{
				click:this.carTypeQuery	
			},//车辆类型-刷新
			'bdef_DefCarUI button[id=refresh]':{
				click:this.carTypeRefresh
			},//Grid双击
			'bdef_DefCarUI grid[id=grid_01_1V01]':{
				itemdblclick:this.grid_01_1V01Click
			},//TAB页切换
			'bdef_DefCarUI tabpanel[id=tabPId1V01]':{
				tabchange:this.tabPIdtabchange
			},//车辆类型窗口-->新增
			'bdef_DefCarTypeAddOrEditWindow button[name=add]':{
				click:this.carTypeAdd
			},//判断车辆类型代码是否唯一
			'bdef_DefCarTypeAddOrEditWindow textfield[id=cartypeNo1V01]':{
				blur:this.cartypeNo1V01Check
			},//车辆类型-保存
			'bdef_DefCarTypeAddOrEditWindow button[name=save]':{
				click:this.carTypeSave
			},//车辆类型-上一条记录
			'bdef_DefCarTypeAddOrEditWindow button[name=prev]':{
				click:this.carTypePrev
			},//车辆类型-下一条记录
			'bdef_DefCarTypeAddOrEditWindow button[name=next]':{
				click:this.carTypeNext
			},//车辆类型-关闭窗口
			'bdef_DefCarTypeAddOrEditWindow button[name=close]':{
				click:this.carTypeColse
			},//车辆信息-新增
			'bdef_DefCarUI button[id=detailAdd]':{
				click:this.detailAdd  
			},//车辆信息-修改
			'bdef_DefCarUI button[id=detailEdit]':{
				click:this.detailEdit  
			},//车辆信息-查找
			'bdef_DefCarUI button[id=detailQuery]':{
				click:this.carQuery	
			},//车辆信息-刷新
			'bdef_DefCarUI button[id=detailRefresh]':{
				click:this.carRefresh
			},//车辆类型窗口-->新增
			'bdef_DefCarAddOrEditWindow button[name=add]':{
				click:this.carAdd
			},//判断车辆类型代码是否唯一
			'bdef_DefCarAddOrEditWindow textfield[id=carNo1V01]':{
				blur:this.carNo1V01Check
			},//车辆信息-保存
			'bdef_DefCarAddOrEditWindow button[name=save]':{
				click:this.carSave
			},//车辆信息-上一条记录
			'bdef_DefCarAddOrEditWindow button[name=prev]':{
				click:this.carPrev
			},//车辆信息-下一条记录
			'bdef_DefCarAddOrEditWindow button[name=next]':{
				click:this.carNext
			},//车辆信息-关闭窗口
			'bdef_DefCarAddOrEditWindow button[name=close]':{
				click:this.carColse
			},//设置光标跳到下一输入框     7-7添加  车辆类型
			'bdef_DefCarTypeAddOrEditWindow field':{
				specialkey:this.boxkeydown
			},//设置光标跳到下一输入框     7-7添加  车辆信息
			'bdef_DefCarAddOrEditWindow field':{
				specialkey:this.boxkeydown
			},//查询按扭  组合查询
			'bdef_DefCarUI button[id=btnSearch1V01]':{
				click:this.btnSearch1V01Click
			},//查询按扭2  组合查询
			'bdef_DefCarUI button[id=btnSearch1V012]':{
				click:this.btnSearch1V012Click
			}

			
		});
	},
	//车辆类型-新增
	addClick:function(){
		Ext.create('cms.view.bdef.window.bdef_DefCarTypeAddOrEditWindow',{
			title:$i18n.titleadd
		}).show();
		addCommMenu5('menuWidget1V01_1');
		this.add_1();
		saveType1V01_1='add';
		checkType1V01_1='1';
	},
	//车辆类型-修改
	editClick:function(){
		var data=Ext.getCmp('grid_01_1V01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_DefCarTypeAddOrEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			this.loadCarTypeData1V01();
			commonMenu5PrevOrNext('menuWidget1V01_1','grid_01_1V01',0);
			updateCommMenu5('menuWidget1V01_1');
			saveType1V01_1='edit';
		}
	},
	
	//查询  7-9添加  hj
	btnSearch1V01Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('carTypeNo1V01').getValue())&&Ext.getCmp('carTypeNo1V01').getValue()!='ALL'){
			var d2={
					columnId:'a.cartype_no',
					value:Ext.getCmp('carTypeNo1V01').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('typeName1V01').getValue())){
			var d2={
					columnId:'a.cartype_name',
					value:Ext.getCmp('typeName1V01').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('carTypeNo1V01').getValue()
			};
		Ext.apply(Ext.getCmp('grid_01_1V01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1V01').getStore().removeAll();
		Ext.getCmp('grid_01_1V01').getStore().load();
	},
	
	//查询2  7-9添加  hj
	btnSearch1V012Click:function(){
		debugger;
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('carTypeNo1V012').getValue())&&Ext.getCmp('carTypeNo1V012').getValue()!='ALL'){
			var d2={
					columnId:'a.cartype_no',
					value:Ext.getCmp('carTypeNo1V012').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('carName1V011').getValue())){
			var d2={
					columnId:'a.car_name',
					value:Ext.getCmp('carName1V011').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('carPlate1V011').getValue())){
			var d2={
					columnId:'a.car_plate',
					value:Ext.getCmp('carPlate1V011').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('deiverWorker1V011').getValue())){
			var d2={
					columnId:'a.driver_worker',
					value:Ext.getCmp('deiverWorker1V011').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('carTypeNo1V012').getValue()
			};
		Ext.apply(Ext.getCmp('grid_02_1V01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_02_1V01').getStore().removeAll();
		Ext.getCmp('grid_02_1V01').getStore().load();
		
	},
	
	//车辆类型-删除
	deleteClick:function(){
		var rec=Ext.getCmp('grid_01_1V01').getSelectionModel().getSelection();
		if(rec.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);
		}else{
			Ext.Msg.confirm($i18n.prompt,$i18n.prompt_sure_delete,function(button,text){
				if(button=='yes'){
					Ext.Ajax.request({
						url:'bdef_DefCarAction_deleteCarType',
						params:{
							strQuery:rec[0].get('cartypeNo')
						},
						success:function(response){
							var data=Ext.decode(response.responseText);					
							if(data.isSucc){
								Ext.getCmp('grid_01_1V01').getStore().load();
								Ext.example.msg($i18n.prompt,data.msg);
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);							
							}
						}
					});
				}
			});
		}
	},
	//车辆类型-查找
	carTypeQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1V01_1';
		queryGrid='grid_01_1V01';
	},
	//车辆类型-刷新
	carTypeRefresh:function(){
		this.refresh();
	},
	//车辆类型-Grid双击
	grid_01_1V01Click:function(th, record,  item,  index, e, eOpts ){
		Ext.getCmp('tabPId1V01').items.items[1].setVisible(true);
    },
	//车辆类型-Tab页切换
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPIdd2_1V01i'){
			var rec = Ext.getCmp('grid_01_1V01').getSelectionModel().getSelection();
			if(rec.length!=0){
				var listDetail = [];
				if(!Ext.isEmpty(rec[0].data.cartypeNo))
				{
					var strDtl={
							columnId:'a.cartype_no',
							value:rec[0].data.cartypeNo
						};
					listDetail.push(strDtl);
				}
				var strJson = Ext.encode(listDetail);
				var wheresql = {
					strQuery : null,
					str:strJson
				};
				Ext.apply(Ext.getCmp('grid_02_1V01').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('grid_02_1V01').getStore().removeAll();
				Ext.getCmp('grid_02_1V01').getStore().load();		
			}
		}
	},
	//车辆类型窗口-新增
	carTypeAdd:function(th){
		commonSetMsterReadOnlyByArray(
				new Array('cartypeNo1V01'),false);
		Ext.getCmp('bdef_DefCarTypeAddOrEditForm').getForm().reset();
	    bindEnterSkip($('#bdef_DefCarTypeAddOrEditForm'));//调用键盘处理方法
		saveType1V01_1='add';
		checkType1V01_1='1';
		Ext.getCmp('cartypeNo1V01').focus(false, 1);
    },
    
    //设置光标跳到下一输入框     7-7添加
    boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=='amount_DateB102'){
				//Ext.getCmp('valueB102').focus();
			}else{
				th.nextSibling().focus();
			}
        }
	},

    
	//判断车辆类型是否唯一
	cartypeNo1V01Check: function(){
		if(saveType1V01_1=='add' && checkType1V01_1=='1'
			&& Ext.getCmp('cartypeNo1V01').getValue()!="" 
			&& Ext.getCmp('cartypeNo1V01').getValue()!=null){			
			Ext.Ajax.request({
				url : 'bdef_DefCarAction_carTypeCheck',
				params : {
					strQuery:Ext.getCmp('cartypeNo1V01').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
						Ext.example.msg($i18n.prompt,$i18n_prompt.carTypeIsExist);
			    		Ext.getCmp('cartypeNo1V01').setValue(null);
			    	}
				}
			});
		}
	},
	//车辆类型-保存
    carTypeSave:function(){
		
		if(!commonCheckIsInputAll('bdef_DefCarTypeAddOrEditForm')){
			return;
		}
		
		if(Ext.getCmp('bdef_DefCarTypeAddOrEditForm').getForm().isValid()){
			var rec=Ext.getCmp('grid_01_1V01').getSelectionModel().getSelection()[0];
			carTypeStr={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					cartypeNo:Ext.getCmp('cartypeNo1V01').getValue()
				},	
				cartypeName:Ext.getCmp('cartypeName1V01').getValue(),
				cartypeWeight:Ext.getCmp('cartypeWeight1V01').getValue(),
				cartypeLength:Ext.getCmp('cartypeLength1V01').getValue(),
				cartypeWidth:Ext.getCmp('cartypeWidth1V01').getValue(),
				cartypeHeight:Ext.getCmp('cartypeHeight1V01').getValue(),
				maxLayer:Ext.getCmp('maxLayer1V01').getValue(),
				rgstDate:saveType1V01_1=='add'?new Date():rec.data.rgstDate,
				rgstName:saveType1V01_1=='add'?Ext.get('workerNo').getValue():rec.data.rgstName,
				updtDate:saveType1V01_1=='add'?'':new Date(),
				updtName:saveType1V01_1=='add'?'':Ext.get('workerNo').getValue()
			};								
			Ext.Ajax.request({
				url:'bdef_DefCarAction_saveCarType',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(carTypeStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){					
						commonSetMsterReadOnlyByArray(
								new Array('cartypeNo1V01'),true);
						if(saveType1V01_1=='add'){
							checkType1V01_1='0';
						}else{
							checkType1V01_1='1';
						}
						Ext.example.msg($i18n.prompt,data.msg);

					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
		}
	},
	//车辆类型-上一条记录
	carTypePrev:function(){		
		commonMenu5PrevOrNext('menuWidget1V01_1','grid_01_1V01',-1);
		this.loadCarTypeData1V01();
	},
	//车辆类型-下一条记录
	carTypeNext:function(){
		commonMenu5PrevOrNext('menuWidget1V01_1','grid_01_1V01',1);
		this.loadCarTypeData1V01();
	},
	//车辆类型-关闭窗口
	carTypeColse:function(){
		Ext.getCmp('bdef_DefCarTypeAddOrEditWindow').close();
		Ext.getCmp('grid_01_1V01').getStore().reload();
	},
	//车辆类型-新增前加载
	add_1:function(){
		Ext.getCmp('bdef_DefCarTypeAddOrEditForm').getForm().reset();
		commonSetMsterReadOnlyByArray(
				new Array('cartypeNo1V01'),false);
		Ext.getCmp('cartypeNo1V01').focus(false,1);
	
	},
	//车辆类型-填充数据
	loadCarTypeData1V01:function(){
		var rec=Ext.getCmp('grid_01_1V01').getSelectionModel().getSelection();
		if(rec.length>0){
			Ext.getCmp('cartypeNo1V01').setValue(rec[0].data.cartypeNo);
			Ext.getCmp('cartypeName1V01').setValue(rec[0].data.cartypeName);
			Ext.getCmp('cartypeWeight1V01').setValue(rec[0].data.cartypeWeight);
			Ext.getCmp('cartypeLength1V01').setValue(rec[0].data.cartypeLength);
			Ext.getCmp('cartypeWidth1V01').setValue(rec[0].data.cartypeWidth);
			Ext.getCmp('cartypeHeight1V01').setValue(rec[0].data.cartypeHeight);
			Ext.getCmp('maxLayer1V01').setValue(rec[0].data.maxLayer);
			commonSetMsterReadOnlyByArray(
					new Array('cartypeNo1V01'),true);
			Ext.getCmp('cartypeName1V01').focus(false, 2);
		}
	},
	
	//车辆信息-新增
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_DefCarAddOrEditWindow',{
			title:$i18n.titleadd
		}).show();
		addCommMenu5('menuWidget1V01_2');
		this.add_2();
		saveType1V01_2='add';
		checkType1V01_2='1';
		Ext.getCmp('cartype_no1V01').getStore().load();
	},
	//车辆信息-修改
	detailEdit:function(){
		var data=Ext.getCmp('grid_02_1V01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_DefCarAddOrEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			this.loadCarData1V01();
			commonMenu5PrevOrNext('menuWidget1V01_2','grid_02_1V01',0);
			updateCommMenu5('menuWidget1V01_2');
			saveType1V01_2='edit';
		}
	},
	//车辆信息-查找
	carQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1V01_2';
		queryGrid='grid_02_1V01';
	},
	//车辆信息-刷新
	carRefresh:function(){
		this.refresh();
	},
	//车辆信息窗口-新增
	carAdd:function(th){
		commonSetMsterReadOnlyByArray(
				new Array('cartype_no1V01','carNo1V01'),false);
	    bindEnterSkip($('#bdef_DefCarAddOrEditForm'));//调用键盘处理方法
		Ext.getCmp('bdef_DefCarAddOrEditForm').getForm().reset();
		saveType1V01_2='add';
		checkType1V01_2='1';
		Ext.getCmp('cartype_no1V01').focus(false, 1);
    },
	//判断车辆类型是否唯一
	carNo1V01Check: function(){
		if(saveType1V01_2=='add' && checkType1V01_2=='1' 
			&& Ext.getCmp('carNo1V01').getValue()!="" 
			&& Ext.getCmp('carNo1V01').getValue()!=null){			
			Ext.Ajax.request({
				url : 'bdef_DefCarAction_carCheck',
				params : {
					strQuery:Ext.getCmp('carNo1V01').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
						Ext.example.msg($i18n.prompt,$i18n_prompt.carIsExist);
			    		Ext.getCmp('carNo1V01').setValue(null);
			    	}
				}
			});
		}
	},
	//车辆信息-保存
    carSave:function(){
		
		if(!commonCheckIsInputAll('bdef_DefCarAddOrEditWindow')){
			return;
		}
		
		if(Ext.getCmp('bdef_DefCarAddOrEditForm').getForm().isValid()){
			var rec=Ext.getCmp('grid_02_1V01').getSelectionModel().getSelection()[0];
			carStr={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					carNo:Ext.getCmp('carNo1V01').getValue()
				},	
				cartypeNo:Ext.getCmp('cartype_no1V01').getValue(),
				carName:Ext.getCmp('carName1V01').getValue(),
				carPlate:Ext.getCmp('carPlate1V01').getValue(),
				oilConsume:Ext.getCmp('oilConsume1V01').getValue(),
				careMile:Ext.getCmp('careMile1V01').getValue(),
				mile:Ext.getCmp('mile1V01').getValue(),
				careDate:Ext.util.Format.date(Ext.getCmp('careDate1V01').getValue(), 'Y-m-d'),
				careWorker:Ext.getCmp('careWorker1V01').getValue(),
				sanplNo:Ext.getCmp('sanplNo1V01').getValue(),
				carClass:Ext.getCmp('carClass1V01').getValue(),
				driverWorker:Ext.getCmp('driverWorker1V01').getValue(),
				rgstDate:saveType1V01_2=='add'?new Date():rec.data.rgstDate,
				rgstName:saveType1V01_2=='add'?Ext.get('workerNo').getValue():rec.data.rgstName,
				updtDate:saveType1V01_2=='add'?'':new Date(),
				updtName:saveType1V01_2=='add'?'':Ext.get('workerNo').getValue()
			};								
			Ext.Ajax.request({
				url:'bdef_DefCarAction_saveCar',
				method:'post',
				async:false,
				params:{
					strQuery:Ext.encode(carStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){		
						commonSetMsterReadOnlyByArray(
								new Array('cartype_no1V01','carNo1V01'),true);
						if(saveType1V01_2=='add'){
							checkType1V01_2='0';
						}else{
							checkType1V01_2='1';
						}
						Ext.example.msg($i18n.prompt,data.msg);
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
		}
	},
	//车辆信息-上一条记录
	carPrev:function(){		
		commonMenu5PrevOrNext('menuWidget1V01_2','grid_02_1V01',-1);
		this.loadCarData1V01();
	},
	
	//车辆信息-下一条记录
	carNext:function(){
		commonMenu5PrevOrNext('menuWidget1V01_2','grid_02_1V01',1);
		this.loadCarData1V01();
	},
	//车辆信息-关闭窗口
	carColse:function(){
		Ext.getCmp('bdef_DefCarAddOrEditWindow').close();
		Ext.getCmp('grid_02_1V01').getStore().reload();
	},
	//车辆信息-新增前加载
	add_2:function(){
		Ext.getCmp('bdef_DefCarAddOrEditForm').getForm().reset();
		commonSetMsterReadOnlyByArray(
				new Array('cartype_no1V01','carNo1V01'),false);
		Ext.getCmp('cartype_no1V01').focus(false,1);
	
	},
	//车辆信息-填充数据
	loadCarData1V01:function(){
		var rec=Ext.getCmp('grid_02_1V01').getSelectionModel().getSelection();
		if(rec.length>0){
			Ext.getCmp('cartype_no1V01').getStore().add({
		    	value:rec[0].data.cartypeNo,
		    	dropValue:'['+rec[0].data.cartypeNo+']'+rec[0].data.cartypeName,
		    	text:rec[0].data.cartypeName
		    });
			Ext.getCmp('cartype_no1V01').setValue(rec[0].data.cartypeNo);
			Ext.getCmp('carNo1V01').setValue(rec[0].data.carNo);
			Ext.getCmp('carName1V01').setValue(rec[0].data.carName);
			Ext.getCmp('carPlate1V01').setValue(rec[0].data.carPlate);
			Ext.getCmp('oilConsume1V01').setValue(rec[0].data.oilConsume);
			Ext.getCmp('careMile1V01').setValue(rec[0].data.careMile);
			Ext.getCmp('mile1V01').setValue(rec[0].data.mile);
			Ext.getCmp('careDate1V01').setValue(rec[0].data.careDate);
			Ext.getCmp('carPlate1V01').setValue(rec[0].data.carPlate);
			Ext.getCmp('careWorker1V01').setValue(rec[0].data.careWorker);
			Ext.getCmp('carPlate1V01').setValue(rec[0].data.carPlate);
			Ext.getCmp('sanplNo1V01').setValue(rec[0].data.sanplNo);
			Ext.getCmp('carClass1V01').setValue(rec[0].data.carClass);
			Ext.getCmp('driverWorker1V01').getStore().add({
		    	value:rec[0].data.driverWorker,
		    	dropValue:'['+rec[0].data.driverWorker+']'+rec[0].data.workerName,
		    	text:rec[0].data.workerName
		    });
			Ext.getCmp('driverWorker1V01').setValue(rec[0].data.driverWorker);
			commonSetMsterReadOnlyByArray(
					new Array('cartype_no1V01','carNo1V01'),true);
			Ext.getCmp('carName1V01').focus(false, 3);
		}
	},
	//刷新
	refresh:function(){
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('grid_01_1V01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_01_1V01').getStore().removeAll();
		Ext.getCmp('grid_01_1V01').getStore().reload();
		var wheresql2 = {
			strQuery : null,
			str:null
		};
		Ext.apply(Ext.getCmp('grid_02_1V01').getStore().proxy.extraParams,wheresql2);
		Ext.getCmp('grid_02_1V01').getStore().removeAll();
		Ext.getCmp('grid_02_1V01').getStore().reload();	
	}
});

