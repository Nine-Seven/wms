/**
 * 模块名称：出车回单
 * 模块编码：3926
 * 创建：hcx
 */
var rowindex3926=0;
Ext.define('cms.controller.odata.odata_CarReceiptController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_CarReceiptUI',
	          'cms.view.odata.window.odata_CarReceiptEditWindow'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//修改
			'odata_CarReceiptUI button[name=edit]':{
				click:this.detailEdit
			},//查找
			'odata_CarReceiptUI button[name=query]':{
				click:this.detailQuery	
			},//刷新
			'odata_CarReceiptUI button[name=refresh]':{
				click:this.refreshClick
			},//保存
			'odata_CarReceiptEditWindow button[name=save]':{
				click:this.save
			},//上一条记录
			'odata_CarReceiptEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'odata_CarReceiptEditWindow button[name=next]':{
				click:this.next
			},//关闭窗口
			'odata_CarReceiptEditWindow button[name=close]':{
				click:this.colse
			}
		});
	},
	//修改
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_3926').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.odata.window.odata_CarReceiptEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			this.loadCustData3926();
			commonMenu5PrevOrNext('menuWidget3926','grid_01_3926',0);
			updateCommMenu5('menuWidget3926');
			Ext.getCmp('back_box3926').focus(false, 6);
		}
	},
	//查找
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=3926;
		queryGrid='grid_01_3926';
	},
	//刷新
	refreshClick:function(){
		var listDetail = [];
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : null,
		};
		Ext.apply(Ext.getCmp('grid_01_3926').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_01_3926').getStore().removeAll();
		Ext.getCmp('grid_01_3926').getStore().load();
	},
	//保存
	save:function(){
		if(!commonCheckIsInputAll('odata_CarReceiptEditForm')){
			return;
		}
		var rec=Ext.getCmp('grid_01_3926').getSelectionModel().getSelection()[0];

		odata_CarReceiptStr={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:Ext.getCmp('owner_no3926').getValue(),
					waveNo:Ext.getCmp('wave_no3926').getValue(),
					deliverNo:Ext.getCmp('deliver_no3926').getValue()
					
				},	
				custNo:Ext.getCmp('cust_no3926').getValue(),
				deliverBox:Ext.getCmp('deliver_box3926').getValue(),
				backBox:Ext.getCmp('back_box3926').getValue(),
				carNo:Ext.getCmp('car_no3926').getValue(),
				driverWorker:Ext.getCmp('deiver_worker3926').getValue()==undefined?'':Ext.getCmp('deiver_worker3926').getValue(),
				status:'13',
				rgstDate:rec.data.rgstDate,
				rgstName:rec.data.rgstName,
				updtDate:new Date(),
				updtName:Ext.get('workerNo').getValue()
			};
		Ext.Ajax.request({
			url:'odata_CarReceiptAction_save',
			method:'post',
			async:false,
			params:{
				strQuery:Ext.encode(odata_CarReceiptStr)
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){					
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('grid_01_3926').getStore().reload();
				}else{
					Ext.Msg.alert($i18n.prompt,data.msg);

					//Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	},
	//上一条记录
	prev:function(){		
		commonMenu5PrevOrNext('menuWidget3926','grid_01_3926',-1);
		this.loadCustData3926();
	},
	
	//下一条记录
	next:function(){
		commonMenu5PrevOrNext('menuWidget3926','grid_01_3926',1);
		this.loadCustData3926();
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('odata_CarReceiptEditWindow').close();
	},
	//填充数据
	loadCustData3926:function(){
		var rec=Ext.getCmp('grid_01_3926').getSelectionModel().getSelection();
		if(rec.length>0){
			Ext.getCmp('owner_no3926').setValue(rec[0].data.ownerNo);
			Ext.getCmp('wave_no3926').setValue(rec[0].data.waveNo);
			Ext.getCmp('deliver_no3926').setValue(rec[0].data.deliverNo);
			Ext.getCmp('cust_no3926').getStore().add({
		    	value:rec[0].data.custNo,
		    	dropValue:'['+rec[0].data.custNo+']'+rec[0].data.custName,
		    	text:rec[0].data.custName
		    });
			Ext.getCmp('cust_no3926').setValue(rec[0].data.custNo);
			Ext.getCmp('deliver_box3926').setValue(rec[0].data.deliverBox);
			Ext.getCmp('back_box3926').setValue(rec[0].data.backBox);
			Ext.getCmp('car_no3926').setValue(rec[0].data.carNo);
			Ext.getCmp('deiver_worker3926').setValue(rec[0].data.driverWorker);
			Ext.getCmp('status3926').setValue(rec[0].data.status);
		}
	}
});

