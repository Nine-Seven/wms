/**
 * 模块名称：出货装车(天天惠)
 * 模块代码：3912
 * @author chensr
 */

Ext.define('cms.controller.odata.odata_CarPlanForTthController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_CarPlanForTthUI'
	],
	init:function(){
		this.control({
			//点击客户名称，加载路线
			'odata_CarPlanForTthUI combo[id=deliver_obj3912]':{
				change:this.custSelect
			},
			//点击查询按钮
			'odata_CarPlanForTthUI button[id=button_01_3912]':{
				click:this.chickButtonCust
			},
			//查看明细
			'odata_CarPlanForTthUI button[id=button_02_3912]':{
				click:this.chickdel
			},
			//刷新
			'odata_CarPlanForTthUI button[name=refresh]':{
				click:this.refresh
			},
			//给网格grid_02_3912赋值
			'odata_CarPlanForTthUI  grid[id=grid_01_3912]':{
				selectionchange:this.gridchange
			},
			//产生交运清单按扭
			'odata_CarPlanForTthUI  button[id=submit3912]':{
				click:this.submit3912
			}
		});
	},
	
	custSelect:function(){
		var wheresql=
		{
			strQuery:Ext.getCmp('deliver_obj3912').getValue()
		};
		Ext.apply(Ext.getCmp('line_no3912').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('line_no3912').getStore().removeAll();
		Ext.getCmp('line_no3912').getStore().load();
	},
	
	chickButtonCust:function(){
		if(!commonCheckIsInputAll('form_01_3912')){
			return;
		}
		var sql='';
		deliverObj=Ext.getCmp('deliver_obj3912').getValue();
		lineNo=Ext.getCmp('line_no3912').getValue();
		var sql={
				custNo:deliverObj,
			    lineNo:lineNo
		};
		
		Ext.apply(Ext.getCmp('grid_01_3912').getStore().proxy.extraParams,sql);
		
		Ext.getCmp('grid_01_3912').getStore().removeAll();
		Ext.getCmp('grid_01_3912').getStore().load();
		Ext.getCmp('grid_02_3912').getStore().removeAll();
		Ext.getCmp('line_nod2_3912').setValue(Ext.getCmp('line_no3912').getValue());
		Ext.getCmp('erpoperate_date3912').setValue(new Date());
		bindEnterSkip($('#form_02_3912'));//调用键盘处理方法
		
		Ext.getCmp('grid_01_3912').getSelectionModel().setLocked(false);
	},
	
	
	chickdel:function()
	{
		var data = Ext.getCmp('grid_01_3912').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt, $i18n_prompt.clooseStore);
		}else{
			var detail1=[];
			for(var i=0;i<data.length;i++){
				detail1.push(data[i].get('containerNo'));
			}
			Ext.create('cms.view.odata.window.odata_CarPlanForTthWindow',{
				title:$i18n.titlebrowse
			}).show();
			var wheresql={
				wheresql:detail1
			};
			Ext.apply(Ext.getCmp('grid_01_3912_d').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_01_3912_d').getStore().removeAll();
			Ext.getCmp('grid_01_3912_d').getStore().load();
		}
	},
	refresh:function(){
		Ext.getCmp('deliver_obj3912').setValue(null);
		Ext.getCmp('line_no3912').setValue(null);
		Ext.getCmp('deliver_obj3912').getStore().reload();
		
		Ext.getCmp('grid_01_3912').getStore().removeAll();
		Ext.getCmp('grid_02_3912').getStore().removeAll();
	},
	gridchange:function(){
		var record = Ext.getCmp('grid_01_3912').getSelectionModel().getSelection();
		var store = Ext.getCmp('grid_02_3912').getStore();
		var count = store.getCount();
		for(var i=0;i<record.length;i++){
			if(Ext.getCmp('grid_02_3912').getStore().findExact('labelNo',record[i].data.labelNo)=='-1'){
				var r=Ext.create('cms.model.stock.stock_LabelMModel',{});
				r.set('deliverObj',record[i].data.deliverObj);
				r.set('labelNo',record[i].data.labelNo);
				r.set('custName',record[i].data.custName);
				r.set('custNo',record[i].data.custNo);
				r.set('lineNo',record[i].data.lineNo);
				r.set('containerNo',record[i].data.containerNo);
				store.add(r);
			}
		}
	},
	
	submit3912:function(){

		if(!commonCheckIsInputAll('form_02_3912')){
				return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		
		var gridcount=Ext.getCmp('grid_02_3912').getStore().getCount();
		if(gridcount==0){
			Ext.example.msg($i18n.prompt, $i18n_prompt.tableCannotBeNull);
			return;
		}
		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,function(button, text) {
			if (button == 'yes') {
				//保存临时表
				var gridcount=Ext.getCmp('grid_01_3912').getStore().getCount();
				for(var i=0;i<gridcount;i++){
					var record=Ext.getCmp('grid_01_3912').getStore().getAt(i);
					var strDetail=
					{
						labelNo:record.data.labelNo,
						containerNo:record.data.containerNo
					};
					var params = 
					{
						str:strDetail
					};
						Ext.Ajax.request({
							method:'POST',
							async:false,
							params:params,
							url:'odata_CarPlanForTthAction_saveLabelTmp.action',
							success:function(response)
							{
								data = Ext.decode(response.responseText);
							}
						});
				};

				var data=Ext.getCmp('grid_02_3912').getStore().getAt(0);
				var d={
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					shipperNo:'N',
					lineNo:Ext.getCmp('line_no3912').getValue()==null ? 'N':Ext.getCmp('line_no3912').getValue(),
					deliverObj:data.data.deliverObj,
					carNo:Ext.getCmp('car_no3912').getValue(),
					userId:Ext.get('workerNo').getValue(),
					dockNo:workSpaceNo,
					loadtype:3,
					paperUserId:Ext.getCmp('load_name3912').getValue()	
				};
				
				var params={
					str:Ext.encode(d)
				};
				Ext.Ajax.request({
					method:'post',
					url:'odata_CarPlanForTthAction_closeCar',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('deliver_obj3912').getStore().load();
							clear3912();
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg);
							//Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
		});	
	},
	

	grid_01_3912Select:function(row,record,index,eOpts){
		var strDetail=
		{
			labelNo:record.data.labelNo,
			containerNo:record.data.containerNo
		};
		var params = 
		{
			str:strDetail
		};
		
		var num=Ext.getCmp('grid_02_3912').getStore().find('labelNo',record.data.labelNo);
		if(num==-1){
			Ext.Ajax.request({
				method:'POST',
				async:false,
				params:params,
				url:'odata_CarPlanForTthAction_saveLabelTmp.action',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
				}
			});
		}else{
			Ext.getCmp('grid_01_3912').getSelectionModel().deselect(
					Ext.getCmp('grid_01_3912').getStore().find('labelNo',record.data.labelNo),
					true);
		}
		Ext.getCmp('grid_02_3912').getStore().removeAll();
		Ext.getCmp('grid_02_3912').getStore().load();
		
	}
});
//清除
function clear3912(){
	Ext.getCmp('line_nod2_3912').setValue(null);
	Ext.getCmp('erpoperate_date3912').setValue(null);
	Ext.getCmp('load_name3912').setValue(null);
	Ext.getCmp('car_no3912').setValue(null);
	Ext.getCmp('deliver_obj3912').setValue(null);
	Ext.getCmp('line_no3912').setValue(null);
	Ext.getCmp('txtSealNo3912').setValue(null);
	Ext.getCmp('grid_01_3912').getStore().removeAll();
	Ext.getCmp('grid_02_3912').getStore().removeAll();
}


