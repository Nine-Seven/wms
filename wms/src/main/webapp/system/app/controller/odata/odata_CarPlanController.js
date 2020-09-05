/**
 * 模块名称：出货装车
 * 模块代码：3801
 * @author JUN
 */
var g_LoadCarLevel3801='';//建装车建议单的级别：1：按承运商；2：按线路；3：配送对象
Ext.define('cms.controller.odata.odata_CarPlanController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_CarPlanUI'
	],
	init:function(){
		this.control({
			//装车建议单按扭
			'odata_CarPlanUI button[id=btn_01_3801]':
			{
				click:this.btn_01_3801Click
			},//TAB1 左下网格
			'odata_CarPlanUI grid[id=grid_01_3801]':
			{
				select:this.grid_01_3801Select,//选中
				deselect:this.grid_01_3801Deselect//取消选中
			},//TAB1 右下网格
			'odata_CarPlanUI grid[id=grid_02_3801]':
			{
				deselect:this.grid_02_3801Deselect//取消选中
			},//TAB3 左下网格
			'odata_CarPlanUI grid[id=grid_01_3801_T3]':
			{
				select:this.grid_01_3801_T3Select,//选中
				deselect:this.grid_01_3801_T3Deselect//取消选中
			},//线路下拉选择事件
			'odata_CarPlanUI combo[id=cmbLineNo3801]':
			{
				select:this.cmbLineNo3801Select
			},//承运商下拉选择事件
			'odata_CarPlanUI combo[id=cmbShipperNo3801]':
			{
				select:this.cmbShipperNo3801Select
			},//承运商下的线路选择事件
			'odata_CarPlanUI combo[id=cmbLineNo3801_d2]':{
				select:this.cmbLineNo3801_d2
			},//配送对象选择事件
			'odata_CarPlanUI combo[id=cmbDeliverObj3801]':{
				select:this.cmbDeliverObj3801Select
			},//派车单选择事件
			'odata_CarPlanUI combo[id=cmbcarPlanNo3801]':{
				select:this.cmbcarPlanNo3801Select
			},//消取按扭
			'odata_CarPlanUI button[id=btnCancel3801]':{
				click:this.btnCancel3801Click
			},//TAB第一页的查看明细按扭
			'odata_CarPlanUI button[id=btnSearchDetail3801]':{
				click:this.btnSearchDetail3801Click
			},//TAB第二页的查找方式
			'odata_CarPlanUI radiogroup[id=searchType3801_d2]':{
				change : this.searchType3801_d2Change
			},//TAB第二页承运商选择事件
			'odata_CarPlanUI combo[id=cmbShipperNo3801_d2]':{
				select : this.cmbShipperNo3801_d2Select
			},//TAB第二页线路下拉选择事件
			'odata_CarPlanUI combo[id=cmbLineNo3801_d3]':{
				select : this.cmbLineNo3801_d3Select
			},//TAB第二页线路下拉选择事件(承运商下的线路)
			'odata_CarPlanUI combo[id=cmbLineNo3801_d4]':{
				select : this.cmbLineNo3801_d4Select
			},//建议单下拉选择
			'odata_CarPlanUI combo[id=cmbLoadproposeNo3801]':{
				select : this.cmbLoadproposeNo3801Select
			},//TAB第二页左下网格
			'odata_CarPlanUI grid[id=grid_03_3801]':
			{
				select:this.grid_03_3801Select,//选中
				deselect:this.grid_03_3801Deselect//取消选中
			},//装车按扭
			'odata_CarPlanUI button[id=btnLoadCar3801]':{
				click:this.btnLoadCar3801Click
			},//刷新
			'odata_CarPlanUI button[name=refresh]':{
				click:this.refreshClick
			},//Tab第二页的查看明细
			'odata_CarPlanUI button[id=btnSearchDetail3801_d2]':{
				click:this.btnSearchDetail3801_d2Click
			},
			'odata_CarPlanUI field':{
				specialkey:this.boxkeydown
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		getSystemPara3801();//调用获取系统参数
		if(g_LoadCarLevel3801=='1')//承运商
		{
			Ext.getCmp('cmbShipperNo3801').setVisible(true);
			Ext.getCmp('cmbLineNo3801_d2').setVisible(true);
			Ext.getCmp('cmbLineNo3801').setVisible(false);
			Ext.getCmp('grid_01_3801').getStore().removeAll();
			Ext.getCmp('grid_02_3801').getStore().removeAll();
			Ext.getCmp('cmbLineNo3801').setValue(null);
			Ext.getCmp('cmbDeliverObj3801').setValue(null);
		}else if(g_LoadCarLevel3801=='2')//线路
		{
			Ext.getCmp('cmbLineNo3801').setVisible(true);
			Ext.getCmp('cmbLineNo3801_d2').setVisible(false);
			Ext.getCmp('cmbShipperNo3801').setVisible(false);
			Ext.getCmp('grid_01_3801').getStore().removeAll();
			Ext.getCmp('grid_02_3801').getStore().removeAll();
			Ext.getCmp('cmbShipperNo3801').setValue(null);
			Ext.getCmp('cmbDeliverObj3801').setValue(null);
		}else if(g_LoadCarLevel3801=='3'){//配送对象
			Ext.getCmp('cmbDeliverObj3801').setVisible(true);
			Ext.getCmp('cmbLineNo3801_d2').setVisible(false);
			Ext.getCmp('cmbShipperNo3801').setVisible(false);
			Ext.getCmp('grid_01_3801').getStore().removeAll();
			Ext.getCmp('grid_02_3801').getStore().removeAll();
			Ext.getCmp('cmbShipperNo3801').setValue(null);
			Ext.getCmp('cmbLineNo3801').setValue(null);
			
			//配送对象加载数据
			Ext.getCmp('cmbDeliverObj3801').getStore().load();
		}
		//派车单下拉加载数据
		Ext.getCmp('cmbcarPlanNo3801').getStore().load();
		
		Ext.getCmp('cmbSourceExpNo3801').setVisible(false);
	},
	
	btn_01_3801Click:function()
	{
		if(Ext.getCmp('grid_02_3801').getStore().data.length==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.checkTheDocument);
			return;                      
		}
		if(Ext.isEmpty(Ext.getCmp('cmbCarNo3801').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inCar);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3801').getValue())){
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
				var strDetail=
				{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					shipperNo:Ext.getCmp('cmbShipperNo3801').getValue()==null ? 'N':Ext.getCmp('cmbShipperNo3801').getValue(),
					lineNo:Ext.getCmp('cmbLineNo3801').getValue()==null ? 'N':Ext.getCmp('cmbLineNo3801').getValue(),
					deliverObj:Ext.getCmp('cmbDeliverObj3801').getValue()==null ? 'N':Ext.getCmp('cmbDeliverObj3801').getValue(),
					carNo:Ext.getCmp('cmbCarNo3801').getValue(),
					userId:Ext.get('workerNo').getValue(),
					dockNo:workSpaceNo,
					loadtype:g_LoadCarLevel3801,
					paperUserId:Ext.getCmp('cmbWorkerNo3801').getValue()
				};
				var params = 
				{
					strWheresql:Ext.encode(strDetail)
				};
				Ext.Ajax.request({
					method:'POST',
					async:false,
					params:params,
					url:'odata_CarPlanAction_closeCar.action',
					success:function(response)
					{debugger;
						var data = Ext.decode(response.responseText);
						if(data.isSucc)
						{
				 			Ext.example.msg($i18n.prompt,data.msg);
							//刷新界面							
							Ext.ComponentQuery.query('odata_CarPlanUI button[name=refresh]')[0].fireEvent('click','');
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
							//Ext.example.msg($i18n.prompt,data.msg+data.obj);
						}																
					}
				});
			}
		});	
	},
	
	grid_01_3801_T3Select:function(row,record,index,eOpts){
		/*var num=Ext.getCmp('grid_02_3801_T3').getStore().find('deliverObj',record.data.deliverObj);
		if(num!=-1){
			Ext.getCmp('grid_01_3801_T3').getSelectionModel().deselect(
					Ext.getCmp('grid_01_3801_T3').getStore().find('deliverObj',record.data.deliverObj),
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
		Ext.apply(Ext.getCmp('grid_02_3801_T3').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_02_3801_T3').getStore().removeAll();
		Ext.getCmp('grid_02_3801_T3').getStore().load();
		
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_CarPlanAction_getItems.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				Ext.getCmp('lblStItems3801_T3').setText(data[0].stItems);
				Ext.getCmp('lblArticleItems3801_T3').setText(data[0].articleItems);
				Ext.getCmp('lblBoxQty3801_T3').setText(data[0].boxQty);
				Ext.getCmp('lblVolumn3801_T3').setText(data[0].volumn);
				Ext.getCmp('lblWeight3801_T3').setText(data[0].weight);
			}
		});
	},
	grid_01_3801_T3Deselect:function(row,record,index,eOpts){
		var count;
		for(var i=0;i<Ext.getCmp('grid_02_3801_T3').getStore().getCount();i++){
			if(record.data.deliverObj==Ext.getCmp('grid_02_3801_T3').getStore().getAt(i).data.deliverObj){
				count=Ext.getCmp('grid_02_3801_T3').getStore().getAt(i);
			}
		}
		Ext.getCmp('grid_02_3801_T3').getStore().remove(count);

		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_CarPlanAction_getItems.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				Ext.getCmp('lblStItems3801_T3').setText(data[0].stItems);
				Ext.getCmp('lblArticleItems3801_T3').setText(data[0].articleItems);
				Ext.getCmp('lblBoxQty3801_T3').setText(data[0].boxQty);
				Ext.getCmp('lblVolumn3801_T3').setText(data[0].volumn);
				Ext.getCmp('lblWeight3801_T3').setText(data[0].weight);
			}
		});
	},
	grid_01_3801Select:function(row,record,index,eOpts)
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
		
		var num=Ext.getCmp('grid_02_3801').getStore().find('labelNo',record.data.labelNo);
		if(num==-1){
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
		}else{
			Ext.getCmp('grid_01_3801').getSelectionModel().deselect(
					Ext.getCmp('grid_01_3801').getStore().find('labelNo',record.data.labelNo),
					true);
		}
		Ext.getCmp('grid_02_3801').getStore().removeAll();
		Ext.getCmp('grid_02_3801').getStore().load();
		
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_CarPlanAction_getItems.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				Ext.getCmp('lblStItems3801').setText(data[0].stItems);
				Ext.getCmp('lblArticleItems3801').setText(data[0].articleItems);
				Ext.getCmp('lblBoxQty3801').setText(data[0].boxQty);
				Ext.getCmp('lblVolumn3801').setText(data[0].volumn);
				Ext.getCmp('lblWeight3801').setText(data[0].weight);
			}
		});
	},
	
	grid_01_3801Deselect:function(row,record,index,eOpts)
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
		Ext.getCmp('grid_02_3801').getStore().removeAll();
		Ext.getCmp('grid_02_3801').getStore().load();
		
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_CarPlanAction_getItems.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				Ext.getCmp('lblStItems3801').setText(data[0].stItems);
				Ext.getCmp('lblArticleItems3801').setText(data[0].articleItems);
				Ext.getCmp('lblBoxQty3801').setText(data[0].boxQty);
				Ext.getCmp('lblVolumn3801').setText(data[0].volumn);
				Ext.getCmp('lblWeight3801').setText(data[0].weight);
			}
		});
	},
	
	grid_02_3801Deselect:function(row,record,index,eOpts)
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
		Ext.getCmp('grid_02_3801').getStore().removeAll();
		Ext.getCmp('grid_02_3801').getStore().load();
		
		Ext.getCmp('grid_01_3801').getSelectionModel().deselect(
				Ext.getCmp('grid_01_3801').getStore().find('labelNo',record.data.labelNo),
				true);
	},
	
	cmbLineNo3801Select:function(combo)
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
		Ext.apply(Ext.getCmp('grid_01_3801').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3801').getStore().removeAll();
		Ext.getCmp('grid_01_3801').getStore().load();
	},
	
	cmbShipperNo3801Select:function(combo)
	{		
		var strWheresql={
			strShipperNo:combo.getValue()
		};
		Ext.apply(Ext.getCmp('cmbLineNo3801_d2').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbLineNo3801_d2').getStore().removeAll();
		Ext.getCmp('cmbLineNo3801_d2').getStore().load();		
	},
	
	cmbLineNo3801_d2:function(combo){
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
		Ext.apply(Ext.getCmp('grid_01_3801').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3801').getStore().removeAll();
		Ext.getCmp('grid_01_3801').getStore().load();
	},
	
	cmbcarPlanNo3801Select:function(combo){
		//查询数据
		
		var strWheresql = {
			strWheresql:combo.getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_3801_T3').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3801_T3').getStore().removeAll();
		Ext.getCmp('grid_01_3801_T3').getStore().load();
	},
	cmbDeliverObj3801Select:function(combo){
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
					Ext.getCmp('cmbSourceExpNo3801').setVisible(true);
					Ext.getCmp('cmbSourceExpNo3801').setValue(data.obj);
				}else{
					Ext.getCmp('cmbSourceExpNo3801').setVisible(false);
				}
			}
		});
		//删除临时表
		clearTmp3801();
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
		Ext.apply(Ext.getCmp('grid_01_3801').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3801').getStore().removeAll();
		Ext.getCmp('grid_01_3801').getStore().load();
	},
	
	btnCancel3801Click:function()
	{
		clearTmp3801();
		Ext.getCmp('grid_02_3801').getStore().removeAll();
		Ext.getCmp('grid_02_3801').getStore().load();
		Ext.getCmp('grid_01_3801').getStore().removeAll();
		Ext.getCmp('grid_01_3801').getStore().load();
	},
	
	btnSearchDetail3801Click:function()
	{
		Ext.create('cms.view.odata.window.odata_CarPlanWindow',{
			title:$i18n.titlebrowse
		}).show();
		var strWheresql = {
			strFlag : '1'//1代表建议单
		};
		Ext.apply(Ext.getCmp('grid_01_3801_d').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3801_d').getStore().removeAll();
		Ext.getCmp('grid_01_3801_d').getStore().load();
	},
	
	searchType3801_d2Change:function(th)
	{
		if(th.getValue().rd==2)//线路
		{
			console.log(2);
			Ext.getCmp('cmbLineNo3801_d3').setVisible(true);
			Ext.getCmp('cmbShipperNo3801_d2').setVisible(false);
			Ext.getCmp('cmbLoadproposeNo3801').setVisible(false);
			Ext.getCmp('cmbLineNo3801_d4').setVisible(false);
			Ext.getCmp('cmbShipperNo3801_d2').setValue(null);
			Ext.getCmp('cmbLoadproposeNo3801').setValue(null);
			Ext.getCmp('cmbLineNo3801_d4').setValue(null);
			
			Ext.getCmp('grid_03_3801').getStore().removeAll();
			Ext.getCmp('grid_04_3801').getStore().removeAll();
			
		}else if(th.getValue().rd==1)//承运商
		{
			console.log(1);
			Ext.getCmp('cmbShipperNo3801_d2').setVisible(true);
			Ext.getCmp('cmbLineNo3801_d4').setVisible(true);
			Ext.getCmp('cmbLineNo3801_d3').setVisible(false);
			Ext.getCmp('cmbLoadproposeNo3801').setVisible(false);
			Ext.getCmp('cmbLineNo3801_d3').setValue(null);
			Ext.getCmp('cmbLoadproposeNo3801').setValue(null);
			
			Ext.getCmp('grid_03_3801').getStore().removeAll();
			Ext.getCmp('grid_04_3801').getStore().removeAll();
		}else if(th.getValue().rd==3)//建议单
		{
			console.log(3);
			Ext.getCmp('cmbLoadproposeNo3801').setVisible(true);
			Ext.getCmp('cmbShipperNo3801_d2').setVisible(false);
			Ext.getCmp('cmbLineNo3801_d3').setVisible(false);
			Ext.getCmp('cmbLineNo3801_d4').setVisible(false);
			Ext.getCmp('cmbShipperNo3801_d2').setValue(null);
			Ext.getCmp('cmbLineNo3801_d3').setValue(null);
			Ext.getCmp('cmbLineNo3801_d4').setValue(null);
			
			Ext.getCmp('grid_03_3801').getStore().removeAll();
			Ext.getCmp('grid_04_3801').getStore().removeAll();
		}
	},
	
	cmbShipperNo3801_d2Select:function(combo){
		var strWheresql={
			strShipperNo:combo.getValue()
		};
		Ext.apply(Ext.getCmp('cmbLineNo3801_d4').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbLineNo3801_d4').getStore().removeAll();
		Ext.getCmp('cmbLineNo3801_d4').getStore().load();
	},
	
	cmbLineNo3801_d3Select:function(combo){
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
		Ext.apply(Ext.getCmp('grid_03_3801').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_03_3801').getStore().removeAll();
		Ext.getCmp('grid_03_3801').getStore().load();
	},
	
	cmbLineNo3801_d4Select:function(combo){
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
		Ext.apply(Ext.getCmp('grid_03_3801').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_03_3801').getStore().removeAll();
		Ext.getCmp('grid_03_3801').getStore().load();
	},
	
	cmbLoadproposeNo3801Select:function(combo)
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
		Ext.apply(Ext.getCmp('grid_03_3801').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_03_3801').getStore().removeAll();
		Ext.getCmp('grid_03_3801').getStore().load();
	},
	
	grid_03_3801Select:function(row,record,index,eOpts)
	{
		var r = Ext.create('cms.model.odata.odata_LoadproposeMModel', {
		});
		r.set('loadproposeNo',record.data.loadproposeNo);
		r.set('labelNo',record.data.labelNo);
		r.set('lineNo',record.data.lineNo);
		r.set('currArea',record.data.currArea);
		r.set('custNo',record.data.custNo);
		r.set('custName',record.data.custName);
		Ext.getCmp('grid_04_3801').store.insert(Ext.getCmp('grid_04_3801').getStore().data.length+1,r);
	},
	
	grid_03_3801Deselect:function(row,record,index,eOpts)
	{
		Ext.getCmp('grid_04_3801').getStore().removeAt(
		  Ext.getCmp('grid_04_3801').getStore().find('loadproposeNo',record.data.loadproposeNo));
	},
	
	btnLoadCar3801Click:function()
	{
		if(Ext.getCmp('grid_04_3801').getStore().data.length==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.checkTheDocument);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3801_d2').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inLoadPeople);
			return;
		}
		var detail1 = [];
		for(var i=0;i<Ext.getCmp("grid_04_3801").getStore().getCount();i++ )
		{
			var record  = Ext.getCmp('grid_04_3801').getStore().getAt(i);
			var d=
			{	
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				loadproposeNo:record.get('loadproposeNo'),
				userId:Ext.get('workerNo').getValue(),
				paperUserId:Ext.getCmp('cmbWorkerNo3801_d2').getValue()
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
			url:'odata_CarPlanAction_loadCar.action',
			params:params,
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				if(data.isSucc)
				{
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('grid_03_3801').getStore().removeAll();
					Ext.getCmp('grid_04_3801').getStore().removeAll();
					
					Ext.getCmp('cmbLineNo3801_d3').getStore().removeAll();
					Ext.getCmp('cmbLineNo3801_d3').getStore().load();
					Ext.getCmp('cmbLineNo3801_d3').setValue(null);
					
					Ext.getCmp('cmbShipperNo3801_d2').getStore().removeAll();
					Ext.getCmp('cmbShipperNo3801_d2').getStore().load();
					Ext.getCmp('cmbShipperNo3801_d2').setValue(null);
					
					Ext.getCmp('cmbLineNo3801_d4').getStore().removeAll();
					Ext.getCmp('cmbLineNo3801_d4').getStore().load();
					Ext.getCmp('cmbLineNo3801_d4').setValue(null);
					
					Ext.getCmp('cmbLoadproposeNo3801').getStore().removeAll();
					Ext.getCmp('cmbLoadproposeNo3801').getStore().load();
					Ext.getCmp('cmbLoadproposeNo3801').setValue(null);
				}else
				{
					Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
					//Ext.example.msg($i18n.prompt,data.msg+data.obj);
				}			
			}
		});
	},
	
	refreshClick:function(){
		if(Ext.getCmp('tabPId3801').getActiveTab().id=="tabPId3801_T1")
		{
			//清空表格数据
			Ext.getCmp('grid_01_3801').getStore().removeAll();
			Ext.getCmp('grid_02_3801').getStore().removeAll();
								
			//按配送对象
			if(g_LoadCarLevel3801=='3')
			{
				Ext.getCmp('cmbDeliverObj3801').setValue(null);
				Ext.getCmp('cmbDeliverObj3801').getStore().removeAll();
				Ext.getCmp('cmbDeliverObj3801').getStore().load();
			}
		}else if(Ext.getCmp('tabPId3801').getActiveTab().id=="tabPId3801_T2")
		{
			Ext.getCmp('grid_03_3801').getStore().removeAll();
			Ext.getCmp('grid_04_3801').getStore().removeAll();
			
			Ext.getCmp('cmbLineNo3801_d3').getStore().removeAll();
			Ext.getCmp('cmbLineNo3801_d3').getStore().load();
			Ext.getCmp('cmbLineNo3801_d3').setValue(null);
			
			Ext.getCmp('cmbShipperNo3801_d2').getStore().removeAll();
			Ext.getCmp('cmbShipperNo3801_d2').getStore().load();
			Ext.getCmp('cmbShipperNo3801_d2').setValue(null);
			
			Ext.getCmp('cmbLineNo3801_d4').getStore().removeAll();
			Ext.getCmp('cmbLineNo3801_d4').getStore().load();
			Ext.getCmp('cmbLineNo3801_d4').setValue(null);
			
			Ext.getCmp('cmbLoadproposeNo3801').getStore().removeAll();
			Ext.getCmp('cmbLoadproposeNo3801').getStore().load();
			Ext.getCmp('cmbLoadproposeNo3801').setValue(null);
		}else if(Ext.getCmp('tabPId3801').getActiveTab().id=="tabPId3801_T3"){
			Ext.getCmp('grid_01_3801_T3').getStore().removeAll();
			Ext.getCmp('grid_01_3801_T3').getStore().load();
			Ext.getCmp('grid_02_3801_T3').getStore().removeAll();
			Ext.getCmp('grid_02_3801_T3').getStore().load();
		}
	},
	
	btnSearchDetail3801_d2Click:function()
	{
		var strWheresql='';
		for(var i=0;i<Ext.getCmp('grid_04_3801').getStore().data.length;i++){
			var record  = Ext.getCmp('grid_04_3801').getStore().getAt(i);
			strWheresql+=record.get('labelNo');
			if(i!=Ext.getCmp('grid_04_3801').getStore().data.length){
				strWheresql+"','";
			}
		}
		Ext.create('cms.view.odata.window.odata_CarPlanWindow',{
			title:$i18n.titlebrowse
		}).show();
		var strWheresql = {
			strFlag : '2',//2装车
			strWheresql:"'"+strWheresql+"'"
		};
		Ext.apply(Ext.getCmp('grid_01_3801_d').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3801_d').getStore().removeAll();
		Ext.getCmp('grid_01_3801_d').getStore().load();
	},
	
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) 
		{
			th.nextSibling().focus();
        }
	}
});

/**
 * 删除临时表
 */
function clearTmp3801(){
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
function getSystemPara3801()
{
	g_LoadCarLevel3801 = 
		commonGetSystemParams('N','LoadCarLevel','O','O_LOADCAR')[0].sdefine;//建装车建议单的级别：1：按承运商；2：按线路；3：配送对象
}
