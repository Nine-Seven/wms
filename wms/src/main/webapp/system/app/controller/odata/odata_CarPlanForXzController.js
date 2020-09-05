/**
 * 模块名称：出货装车
 * 模块代码：3802
 * @author hcx
 */
var g_LoadCarLevel3802='';//建装车建议单的级别：1：按承运商；2：按线路；3：配送对象
var flag='0';
Ext.define('cms.controller.odata.odata_CarPlanForXzController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_CarPlanForXzUI'
	],
	init:function(){
		this.control({
			//装车建议单按扭
			'odata_CarPlanForXzUI button[id=btn_01_3802]':
			{
				click:this.btn_01_3802Click
			},//TAB1 左下网格
			'odata_CarPlanForXzUI grid[id=grid_01_3802]':
			{
				select:this.grid_01_3802Select,//选中
				deselect:this.grid_01_3802Deselect//取消选中
			},//TAB1 右下网格
			'odata_CarPlanForXzUI grid[id=grid_02_3802]':
			{
				deselect:this.grid_02_3802Deselect//取消选中
			},//TAB3 左下网格
			'odata_CarPlanForXzUI grid[id=grid_01_3802_T3]':
			{
				select:this.grid_01_3802_T3Select,//选中
				deselect:this.grid_01_3802_T3Deselect//取消选中
			},//线路下拉选择事件
			'odata_CarPlanForXzUI combo[id=cmbLineNo3802]':
			{
				select:this.cmbLineNo3802Select
			},//承运商下拉选择事件
			'odata_CarPlanForXzUI combo[id=cmbShipperNo3802]':
			{
				select:this.cmbShipperNo3802Select
			},//承运商下的线路选择事件
			'odata_CarPlanForXzUI combo[id=cmbLineNo3802_d2]':{
				select:this.cmbLineNo3802_d2
			},//配送对象选择事件
			'odata_CarPlanForXzUI combo[id=cmbDeliverObj3802]':{
				select:this.cmbDeliverObj3802Select
			},//派车单选择事件
			'odata_CarPlanForXzUI combo[id=cmbcarPlanNo3802]':{
				select:this.cmbcarPlanNo3802Select
			},//消取按扭
			'odata_CarPlanForXzUI button[id=btnCancel3802]':{
				click:this.btnCancel3802Click
			},//TAB第一页的查看明细按扭
			'odata_CarPlanForXzUI button[id=btnSearchDetail3802]':{
				click:this.btnSearchDetail3802Click
			},//TAB第二页的查找方式
			'odata_CarPlanForXzUI radiogroup[id=searchType3802_d2]':{
				change : this.searchType3802_d2Change
			},//TAB第二页承运商选择事件
			'odata_CarPlanForXzUI combo[id=cmbShipperNo3802_d2]':{
				select : this.cmbShipperNo3802_d2Select
			},//TAB第二页线路下拉选择事件
			'odata_CarPlanForXzUI combo[id=cmbLineNo3802_d3]':{
				select : this.cmbLineNo3802_d3Select
			},//TAB第二页线路下拉选择事件(承运商下的线路)
			'odata_CarPlanForXzUI combo[id=cmbLineNo3802_d4]':{
				select : this.cmbLineNo3802_d4Select
			},//建议单下拉选择
			'odata_CarPlanForXzUI combo[id=cmbLoadproposeNo3802]':{
				select : this.cmbLoadproposeNo3802Select
			},//TAB第二页左下网格
			'odata_CarPlanForXzUI grid[id=grid_03_3802]':
			{
				select:this.grid_03_3802Select,//选中
				deselect:this.grid_03_3802Deselect//取消选中
			},//装车按扭
			'odata_CarPlanForXzUI button[id=btnLoadCar3802]':{
				click:this.btnLoadCar3802Click
			},//刷新
			'odata_CarPlanForXzUI button[name=refresh]':{
				click:this.refreshClick
			},//Tab第二页的查看明细
			'odata_CarPlanForXzUI button[id=btnSearchDetail3802_d2]':{
				click:this.btnSearchDetail3802_d2Click
			},
			'odata_CarPlanForXzUI field':{
				specialkey:this.boxkeydown
			},//TAB第二页车辆代码下拉选择事件
			'odata_CarPlanForXzUI bdef_DefOwnerCombo[id=car_no3802]':{
				select : this.cmbcarNo3802_Select
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		getSystemPara3802();//调用获取系统参数
		if(g_LoadCarLevel3802=='1')//承运商
		{
			Ext.getCmp('cmbShipperNo3802').setVisible(true);
			Ext.getCmp('cmbLineNo3802_d2').setVisible(true);
			Ext.getCmp('cmbLineNo3802').setVisible(false);
			Ext.getCmp('grid_01_3802').getStore().removeAll();
			Ext.getCmp('grid_02_3802').getStore().removeAll();
			Ext.getCmp('cmbLineNo3802').setValue(null);
			Ext.getCmp('cmbDeliverObj3802').setValue(null);
		}else if(g_LoadCarLevel3802=='2')//线路
		{
			Ext.getCmp('cmbLineNo3802').setVisible(true);
			Ext.getCmp('cmbLineNo3802_d2').setVisible(false);
			Ext.getCmp('cmbShipperNo3802').setVisible(false);
			Ext.getCmp('grid_01_3802').getStore().removeAll();
			Ext.getCmp('grid_02_3802').getStore().removeAll();
			Ext.getCmp('cmbShipperNo3802').setValue(null);
			Ext.getCmp('cmbDeliverObj3802').setValue(null);
		}else if(g_LoadCarLevel3802=='3'){//配送对象
			Ext.getCmp('cmbDeliverObj3802').setVisible(true);
			Ext.getCmp('cmbLineNo3802_d2').setVisible(false);
			Ext.getCmp('cmbShipperNo3802').setVisible(false);
			Ext.getCmp('grid_01_3802').getStore().removeAll();
			Ext.getCmp('grid_02_3802').getStore().removeAll();
			Ext.getCmp('cmbShipperNo3802').setValue(null);
			Ext.getCmp('cmbLineNo3802').setValue(null);
			
			//配送对象加载数据
			Ext.getCmp('cmbDeliverObj3802').getStore().load();
		}
		//删除临时表
		clearTmp3802();
		//派车单下拉加载数据
		Ext.getCmp('cmbcarPlanNo3802').getStore().load();
		
		Ext.getCmp('cmbSourceExpNo3802').setVisible(false);
	},
	
	btn_01_3802Click:function()
	{
		if(Ext.getCmp('grid_02_3802').getStore().data.length==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.checkTheDocument);
			return;                      
		}
		if(Ext.isEmpty(Ext.getCmp('cmbCarNo3802').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inCar);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3802').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inLoadPeople);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}		
		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,function(button, text) 
		{
			if (button == 'yes') 
			{
				var strM=
				{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					shipperNo:'N',
					lineNo:'N',
					deliverObj:'N',
					carNo:Ext.getCmp('cmbCarNo3802').getValue(),
					userId:Ext.get('workerNo').getValue(),
					dockNo:workSpaceNo,
					loadtype:g_LoadCarLevel3802,
					paperUserId:Ext.getCmp('cmbWorkerNo3802').getValue(),
					printFlag:Ext.getCmp('rdoCheckType3802').getValue().rd
				};
				var objdata=Ext.getCmp('grid_02_3802').getSelectionModel().getSelection();
				var detail=[];
				if(objdata.length!=0){
					for(var i=0; i<objdata.length; i++){
						var strD={
								labelNo:objdata[i].get('labelNo')
						};
						detail.push(strD);
					}
				  }
				var params = 
				{
					strWheresql:Ext.encode(strM),
					strQuery:Ext.encode(detail)
				};
				Ext.Ajax.request({
					method:'POST',
					async:false,
					params:params,
					url:'odata_CarPlanForXzAction_closeCar.action',
					success:function(response)
					{
						var data = Ext.decode(response.responseText);
						if(data.isSucc)
						{
				 			Ext.example.msg($i18n.prompt,data.msg);
							//刷新界面							
							Ext.ComponentQuery.query('odata_CarPlanForXzUI button[name=refresh]')[0].fireEvent('click','');
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
							//Ext.example.msg($i18n.prompt,data.msg+data.obj);
						}																
					}
				});
			}
		});	
	},
	
	grid_01_3802_T3Select:function(row,record,index,eOpts){
		/*var num=Ext.getCmp('grid_02_3802_T3').getStore().find('deliverObj',record.data.deliverObj);
		if(num!=-1){
			Ext.getCmp('grid_01_3802_T3').getSelectionModel().deselect(
					Ext.getCmp('grid_01_3802_T3').getStore().find('deliverObj',record.data.deliverObj),
					true);
		}*/
		var strDetail1 = [];
		var d={
			columnId:'slm.deliver_obj',
			value:record.data.deliverObj
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLineNo : '',
			strShipperNo:'',
			strDeliverObj:jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_02_3802_T3').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_02_3802_T3').getStore().removeAll();
		Ext.getCmp('grid_02_3802_T3').getStore().load();
		
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_CarPlanAction_getItems.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				Ext.getCmp('lblStItems3802_T3').setText(data[0].stItems);
				Ext.getCmp('lblArticleItems3802_T3').setText(data[0].articleItems);
				Ext.getCmp('lblBoxQty3802_T3').setText(data[0].boxQty);
				Ext.getCmp('lblVolumn3802_T3').setText(data[0].volumn);
				Ext.getCmp('lblWeight3802_T3').setText(data[0].weight);
			}
		});
	},
	grid_01_3802_T3Deselect:function(row,record,index,eOpts){
		var count;
		for(var i=0;i<Ext.getCmp('grid_02_3802_T3').getStore().getCount();i++){
			if(record.data.deliverObj==Ext.getCmp('grid_02_3802_T3').getStore().getAt(i).data.deliverObj){
				count=Ext.getCmp('grid_02_3802_T3').getStore().getAt(i);
			}
		}
		Ext.getCmp('grid_02_3802_T3').getStore().remove(count);

		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_CarPlanForXzAction_getItems.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				Ext.getCmp('lblStItems3802_T3').setText(data[0].stItems);
				Ext.getCmp('lblArticleItems3802_T3').setText(data[0].articleItems);
				Ext.getCmp('lblBoxQty3802_T3').setText(data[0].boxQty);
				Ext.getCmp('lblVolumn3802_T3').setText(data[0].volumn);
				Ext.getCmp('lblWeight3802_T3').setText(data[0].weight);
			}
		});
	},
	grid_01_3802Select:function(row,record,index,eOpts)
	{
		var listDetail = [];
    	var a={
				columnId:'tmp.label_no',
				value:record.data.labelNo
			};
		listDetail.push(a);
		var b={
				columnId:'tmp.container_no',
				value:record.data.containerNo
			};
		listDetail.push(b);
		var strJson = Ext.encode(listDetail);
		var params2 = {
			strQuery : strJson
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_CarPlanForXzAction_labelTmpCheck.action',
			params:params2,
			async:false,
			success:function(response)
			{
				var res = Ext.decode(response.responseText);
		    	if(res==''){
		    		var strDetail=
		    		{
		    			labelNo:record.data.labelNo,
		    			containerNo:record.data.containerNo
		    		};
		    		var params = 
		    		{
		    			strWheresql:strDetail
		    		};
		    		Ext.Ajax.request({
						method:'POST',
						async:false,
						params:params,
						url:'odata_CarPlanAction_saveLabelTmp.action',
						success:function(response)
						{
							data = Ext.decode(response.responseText);
						}
					});
		    	}			
			}
		});
		Ext.getCmp('grid_02_3802').getStore().removeAll();
		Ext.getCmp('grid_02_3802').getStore().load();
		//取配送条数等信息
		getItems();
	},
	
	grid_01_3802Deselect:function(row,record,index,eOpts)
	{
		var strDetail=
		{
			labelNo:record.data.labelNo,
			containerNo:record.data.containerNo
		};
		var params = 
		{
			strWheresql:strDetail
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			params:params,
			url:'odata_CarPlanAction_deleteLabelTmp.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
			}
		});
		Ext.getCmp('grid_02_3802').getStore().removeAll();
		Ext.getCmp('grid_02_3802').getStore().load();
		
		//取配送条数等信息
		getItems();
	},
	
	grid_02_3802Deselect:function(row,record,index,eOpts)
	{		
		var strDetail=
		{
			labelNo:record.data.labelNo,
			containerNo:record.data.containerNo
		};
		var params = 
		{
			strWheresql:strDetail
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			params:params,
			url:'odata_CarPlanAction_deleteLabelTmp.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
			}
		});
		Ext.getCmp('grid_02_3802').getStore().removeAll();
		Ext.getCmp('grid_02_3802').getStore().load();
		
		Ext.getCmp('grid_01_3802').getSelectionModel().deselect(
				Ext.getCmp('grid_01_3802').getStore().find('labelNo',record.data.labelNo),
				true);
		//取配送条数等信息
		getItems();
	},
	
	cmbLineNo3802Select:function(combo)
	{
		var strDetail1 = [];
		var d={
			columnId:'slm.line_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLineNo : jsonDetail,
			strShipperNo:'',
			strDeliverObj:''
		};
		Ext.apply(Ext.getCmp('grid_01_3802').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3802').getStore().removeAll();
		Ext.getCmp('grid_01_3802').getStore().load();
	},
	
	cmbShipperNo3802Select:function(combo)
	{		
		var strWheresql={
			strShipperNo:combo.getValue()
		};
		Ext.apply(Ext.getCmp('cmbLineNo3802_d2').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbLineNo3802_d2').getStore().removeAll();
		Ext.getCmp('cmbLineNo3802_d2').getStore().load();		
	},
	
	cmbLineNo3802_d2:function(combo){
		var strDetail1 = [];
		var d={
			columnId:'slm.line_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLineNo : jsonDetail,
			strShipperNo:'',
			strDeliverObj:''
		};
		Ext.apply(Ext.getCmp('grid_01_3802').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3802').getStore().removeAll();
		Ext.getCmp('grid_01_3802').getStore().load();
	},
	
	cmbcarPlanNo3802Select:function(combo){
		//查询数据
		
		var strWheresql = {
			strWheresql:combo.getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_3802_T3').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3802_T3').getStore().removeAll();
		Ext.getCmp('grid_01_3802_T3').getStore().load();
	},
	cmbDeliverObj3802Select:function(combo){
		flag='1';
		var params = 
		{
			strWheresql:combo.getValue()
		};
		//检查配送对象是否与客户一至
		Ext.Ajax.request({
			method:'POST',
			async:false,
			params:params,
			url:'odata_CarPlanAction_checkDeliverObj.action',
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					Ext.getCmp('cmbSourceExpNo3802').setVisible(true);
					Ext.getCmp('cmbSourceExpNo3802').setValue(data.obj);
				}else{
					Ext.getCmp('cmbSourceExpNo3802').setVisible(false);
				}
			}
		});
//		//删除临时表
//		clearTmp3802();
		//查询数据
		var strDetail1 = [];
		var d={
			columnId:'slm.deliver_obj',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLineNo : '',
			strShipperNo:'',
			strDeliverObj:jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_01_3802').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3802').getStore().removeAll();
		Ext.getCmp('grid_01_3802').getStore().load();
	},
	
	btnCancel3802Click:function()
	{
		clearTmp3802();
		Ext.getCmp('grid_02_3802').getStore().removeAll();
		Ext.getCmp('grid_02_3802').getStore().load();
		Ext.getCmp('grid_01_3802').getStore().removeAll();
		Ext.getCmp('grid_01_3802').getStore().load();
	},
	
	btnSearchDetail3802Click:function()
	{
		Ext.create('cms.view.odata.window.odata_CarPlanForXzWindow',{
			title:$i18n.titlebrowse
		}).show();
		var strWheresql = {
			strFlag : '1'//1代表建议单
		};
		Ext.apply(Ext.getCmp('grid_01_3802_d').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3802_d').getStore().removeAll();
		Ext.getCmp('grid_01_3802_d').getStore().load();
	},
	
	searchType3802_d2Change:function(th)
	{
		if(th.getValue().rd==2)//线路
		{
			console.log(2);
			Ext.getCmp('cmbLineNo3802_d3').setVisible(true);
			Ext.getCmp('cmbShipperNo3802_d2').setVisible(false);
			Ext.getCmp('cmbLoadproposeNo3802').setVisible(false);
			Ext.getCmp('cmbLineNo3802_d4').setVisible(false);
			Ext.getCmp('cmbShipperNo3802_d2').setValue(null);
			Ext.getCmp('cmbLoadproposeNo3802').setValue(null);
			Ext.getCmp('cmbLineNo3802_d4').setValue(null);
			
			Ext.getCmp('grid_03_3802').getStore().removeAll();
			Ext.getCmp('grid_04_3802').getStore().removeAll();
			
		}else if(th.getValue().rd==1)//承运商
		{
			console.log(1);
			Ext.getCmp('cmbShipperNo3802_d2').setVisible(true);
			Ext.getCmp('cmbLineNo3802_d4').setVisible(true);
			Ext.getCmp('cmbLineNo3802_d3').setVisible(false);
			Ext.getCmp('cmbLoadproposeNo3802').setVisible(false);
			Ext.getCmp('cmbLineNo3802_d3').setValue(null);
			Ext.getCmp('cmbLoadproposeNo3802').setValue(null);
			
			Ext.getCmp('grid_03_3802').getStore().removeAll();
			Ext.getCmp('grid_04_3802').getStore().removeAll();
		}else if(th.getValue().rd==3)//建议单
		{
			console.log(3);
			Ext.getCmp('cmbLoadproposeNo3802').setVisible(true);
			Ext.getCmp('cmbShipperNo3802_d2').setVisible(false);
			Ext.getCmp('cmbLineNo3802_d3').setVisible(false);
			Ext.getCmp('cmbLineNo3802_d4').setVisible(false);
			Ext.getCmp('cmbShipperNo3802_d2').setValue(null);
			Ext.getCmp('cmbLineNo3802_d3').setValue(null);
			Ext.getCmp('cmbLineNo3802_d4').setValue(null);
			
			Ext.getCmp('grid_03_3802').getStore().removeAll();
			Ext.getCmp('grid_04_3802').getStore().removeAll();
		}
	},
	
	cmbShipperNo3802_d2Select:function(combo){
		var strWheresql={
			strShipperNo:combo.getValue()
		};
		Ext.apply(Ext.getCmp('cmbLineNo3802_d4').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbLineNo3802_d4').getStore().removeAll();
		Ext.getCmp('cmbLineNo3802_d4').getStore().load();
	},
	
	cmbLineNo3802_d3Select:function(combo){
		var strDetail1 = [];
		var d={
			columnId:'olm.line_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLineNo : jsonDetail,
			strLoadproposeNo:''
		};
		Ext.apply(Ext.getCmp('grid_03_3802').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_03_3802').getStore().removeAll();
		Ext.getCmp('grid_03_3802').getStore().load();
	},
	
	cmbLineNo3802_d4Select:function(combo){
		var strDetail1 = [];
		var d={
			columnId:'olm.line_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLineNo : jsonDetail,
			strLoadproposeNo:''
		};
		Ext.apply(Ext.getCmp('grid_03_3802').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_03_3802').getStore().removeAll();
		Ext.getCmp('grid_03_3802').getStore().load();
	},
	
	cmbLoadproposeNo3802Select:function(combo)
	{
		var strDetail1 = [];
		var d={
			columnId:'olm.loadpropose_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strLoadproposeNo : jsonDetail,
			strLineNo:''
		};
		Ext.apply(Ext.getCmp('grid_03_3802').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_03_3802').getStore().removeAll();
		Ext.getCmp('grid_03_3802').getStore().load();
	},
cmbcarNo3802_Select:function(combo){
		
		//取车辆表的司机
		var strWheresql = {
				strQuery : combo.getValue()
		};
	
		Ext.Ajax.request({
			method:'POST',
			url:'odata_CarPlanForXzAction_getDriverName',
			params:strWheresql,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('cmbWorkerNo3802_d2').getStore().add({
				    	value:data.obj[0].workerNo,
				    	dropValue:'['+data.obj[0].workerNo+']'+data.obj[0].workerName,
				    	text:data.obj[0].workerName
				    });
					
					Ext.getCmp('cmbWorkerNo3802_d2').setValue(data.obj[0].workerNo);
				}else{
					Ext.getCmp('cmbWorkerNo3802_d2').setValue('');

				}
			}
		});
		
		
	},
	
	grid_03_3802Select:function(row,record,index,eOpts)
	{
		var r = Ext.create('cms.model.odata.odata_LoadproposeMModel', {
		});
		r.set('loadproposeNo',record.data.loadproposeNo);
		r.set('labelNo',record.data.labelNo);
		r.set('lineNo',record.data.lineNo);
		r.set('currArea',record.data.currArea);
		r.set('custNo',record.data.custNo);
		r.set('custName',record.data.custName);
		r.set('custName',record.data.custName);
		r.set('deliverBox',0);
		Ext.getCmp('grid_04_3802').store.insert(Ext.getCmp('grid_04_3802').getStore().data.length+1,r);
	},
	
	grid_03_3802Deselect:function(row,record,index,eOpts)
	{
		Ext.getCmp('grid_04_3802').getStore().removeAt(
		  Ext.getCmp('grid_04_3802').getStore().find('loadproposeNo',record.data.loadproposeNo));
	},
	btnLoadCar3802Click:function()
	{
		if(Ext.getCmp('grid_04_3802').getStore().data.length==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.checkTheDocument);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3802_d2').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inLoadPeople);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('car_no3802').getValue())){
			Ext.example.msg($i18n.prompt,'请选择车辆代码!');
			return;
		}
		var detail1 = [];
		for(var i=0;i<Ext.getCmp("grid_04_3802").getStore().getCount();i++ )
		{
			var record  = Ext.getCmp('grid_04_3802').getStore().getAt(i);
		/*	if(record.get('deliverBox')==0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.deliverBoxIsNotZero);
				return;
			}*/
			var d=
			{	
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				loadproposeNo:record.get('loadproposeNo'),
				labelNo:record.get('labelNo'),
				deliverBox:record.get('deliverBox'),
				userId:Ext.get('workerNo').getValue(),
				paperUserId:Ext.getCmp('cmbWorkerNo3802_d2').getValue(),
				carNo:Ext.getCmp('car_no3802').getValue()
			};
			detail1.push(d);
		}
		var strJsonDetail1 = Ext.encode(detail1);
		var params = 
		{
			strJsonDetail1:strJsonDetail1
		};
		console.log(strJsonDetail1);
		Ext.Ajax.request({
			method:'POST',
			url:'odata_CarPlanForXzAction_loadCar.action',
			params:params,
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				if(data.isSucc)
				{
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('grid_03_3802').getStore().removeAll();
					Ext.getCmp('grid_04_3802').getStore().removeAll();
					
					Ext.getCmp('cmbLineNo3802_d3').getStore().removeAll();
					Ext.getCmp('cmbLineNo3802_d3').getStore().load();
					Ext.getCmp('cmbLineNo3802_d3').setValue(null);
					
					Ext.getCmp('cmbShipperNo3802_d2').getStore().removeAll();
					Ext.getCmp('cmbShipperNo3802_d2').getStore().load();
					Ext.getCmp('cmbShipperNo3802_d2').setValue(null);
					
					Ext.getCmp('cmbLineNo3802_d4').getStore().removeAll();
					Ext.getCmp('cmbLineNo3802_d4').getStore().load();
					Ext.getCmp('cmbLineNo3802_d4').setValue(null);
					
					Ext.getCmp('cmbLoadproposeNo3802').getStore().removeAll();
					Ext.getCmp('cmbLoadproposeNo3802').getStore().load();
					Ext.getCmp('cmbLoadproposeNo3802').setValue(null);
				}else
				{
					Ext.Msg.alert($i18n.prompt,data.msg+data.obj);

					//Ext.example.msg($i18n.prompt,data.msg+data.obj);
				}			
			}
		});
	},
	
	refreshClick:function(){
		flag='1';
		if(Ext.getCmp('tabPId3802').getActiveTab().id=="tabPId3802_T1")
		{
			//清空表格数据
			Ext.getCmp('grid_01_3802').getStore().removeAll();
			Ext.getCmp('grid_02_3802').getStore().removeAll();
								
			//按配送对象
			if(g_LoadCarLevel3802=='3')
			{
				Ext.getCmp('cmbDeliverObj3802').setValue(null);
				Ext.getCmp('cmbDeliverObj3802').getStore().removeAll();
				Ext.getCmp('cmbDeliverObj3802').getStore().load();
			}
		}else if(Ext.getCmp('tabPId3802').getActiveTab().id=="tabPId3802_T2")
		{
			Ext.getCmp('grid_03_3802').getStore().removeAll();
			Ext.getCmp('grid_04_3802').getStore().removeAll();
			
			Ext.getCmp('cmbLineNo3802_d3').getStore().removeAll();
			Ext.getCmp('cmbLineNo3802_d3').getStore().load();
			Ext.getCmp('cmbLineNo3802_d3').setValue(null);
			
			Ext.getCmp('cmbShipperNo3802_d2').getStore().removeAll();
			Ext.getCmp('cmbShipperNo3802_d2').getStore().load();
			Ext.getCmp('cmbShipperNo3802_d2').setValue(null);
			
			Ext.getCmp('cmbLineNo3802_d4').getStore().removeAll();
			Ext.getCmp('cmbLineNo3802_d4').getStore().load();
			Ext.getCmp('cmbLineNo3802_d4').setValue(null);
			
			Ext.getCmp('cmbLoadproposeNo3802').getStore().removeAll();
			Ext.getCmp('cmbLoadproposeNo3802').getStore().load();
			Ext.getCmp('cmbLoadproposeNo3802').setValue(null);
		}else if(Ext.getCmp('tabPId3802').getActiveTab().id=="tabPId3802_T3"){
			Ext.getCmp('grid_01_3802_T3').getStore().removeAll();
			Ext.getCmp('grid_01_3802_T3').getStore().load();
			Ext.getCmp('grid_02_3802_T3').getStore().removeAll();
			Ext.getCmp('grid_02_3802_T3').getStore().load();
		}
		//删除临时表
		clearTmp3802();
		Ext.getCmp('car_no3802').setValue('');
		Ext.getCmp('car_no3802').getStore().removeAll();
		Ext.getCmp('car_no3802').getStore().reload();
		Ext.getCmp('cmbWorkerNo3802_d2').setValue('');
		Ext.getCmp('cmbWorkerNo3802_d2').getStore().removeAll();
		Ext.getCmp('cmbWorkerNo3802_d2').getStore().reload();
	
		
	},
	
	btnSearchDetail3802_d2Click:function()
	{
		var strWheresql='';
		for(var i=0;i<Ext.getCmp('grid_04_3802').getStore().data.length;i++){
			var record  = Ext.getCmp('grid_04_3802').getStore().getAt(i);
			strWheresql+=record.get('labelNo');
			if(i!=Ext.getCmp('grid_04_3802').getStore().data.length){
				strWheresql+"','";
			}
		}
		Ext.create('cms.view.odata.window.odata_CarPlanForXzWindow',{
			title:$i18n.titlebrowse
		}).show();
		var strWheresql = {
			strFlag : '2',//2装车
			strWheresql:"'"+strWheresql+"'"
		};
		Ext.apply(Ext.getCmp('grid_01_3802_d').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3802_d').getStore().removeAll();
		Ext.getCmp('grid_01_3802_d').getStore().load();
	},
	
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) 
		{
			th.nextSibling().focus();
        }
	},
	getFlag:function(){
  		return flag;
  	},
  	 
  	setFlag:function(value){
  		flag =value;
  	},
});
/**
 * 删除临时表
 */
function clearTmp3802(){
	Ext.Ajax.request({
		method:'POST',
		async:false,
		url:'odata_CarPlanAction_deleteTmp.action',
		success:function(response)
		{
			data = Ext.decode(response.responseText);
		}
	});
}

/**
 * 根据货主获取相应的系统参数
 * @param strOwnerNo
 */
function getSystemPara3802()
{
	g_LoadCarLevel3802 = 
		commonGetSystemParams('N','LoadCarLevel','O','O_LOADCAR')[0].sdefine;//建装车建议单的级别：1：按承运商；2：按线路；3：配送对象
}
//取配送条数等信息
function getItems(){
	Ext.Ajax.request({
		method:'POST',
		async:false,
		url:'odata_CarPlanForXzAction_getItems.action',
		success:function(response)
		{
			data = Ext.decode(response.responseText);
			Ext.getCmp('lblStItems3802').setText(data[0].stItems);
			Ext.getCmp('lblArticleItems3802').setText(data[0].articleItems);
			Ext.getCmp('lblBoxQty3802').setText(data[0].boxQty);
			Ext.getCmp('lblVolumn3802').setText(data[0].volumn);
			Ext.getCmp('lblWeight3802').setText(data[0].weight);
		}
	});
}