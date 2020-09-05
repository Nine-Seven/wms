/*
 * 退厂发单
 * zhouhuan
 * 7201
 */
var rowindex7201=0;
bindEnterSkip($('#formRodata_OutstockM7201'));//调用键盘处理方法
Ext.define('cms.controller.rodata.rodata_OutstockDirectController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_OutstockDirectUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//更改供应商，加载数据
			'rodata_OutstockDirectUI combo[id=cmbSuppliers7201]':{
				change:this.cmbSuppliers7201change
			},
			//选择退厂单号加载数据
			'rodata_OutstockDirectUI combo[id=cmbRecede_no7201]':{
				select:this.cmbRecede_no7201change
			},
			//发单
			'rodata_OutstockDirectUI button[name=send]':{
				click:this.send
			},
			//刷新
			'rodata_OutstockDirectUI button[name=refresh]':{
				click:this.refresh
			}
		});
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		var packingUnit7201=commonGetModuleField('7201','packingUnit')[0].flag;
		var packingSpec7201=commonGetModuleField('7201','packingSpec')[0].flag;
		if(packingUnit7201==0){
			Ext.getCmp('packingUnit7201').setVisible(false);
		}
		if(packingSpec7201==0){
			Ext.getCmp('packingSpec7201').setVisible(false);
		}
		Ext.getCmp('cmbSuppliers7201').getStore().load();
	},

	/*
	 * 刷新
	 */
	 refresh:function(){
	 	Ext.getCmp('cmbSuppliers7201').setValue(null);
	 	Ext.getCmp('cmbSuppliers7201').getStore().reload();
 	  	if(Ext.getCmp('cmbSuppliers7201').getStore().data.length>0)
		{
			Ext.getCmp('cmbSuppliers7201').setValue(
			Ext.getCmp('cmbSuppliers7201').getStore().getAt(0).data.value);		
		}
		Ext.getCmp('cmbRecede_no7201').setValue(null);
		Ext.getCmp('cmbWorker7201').setValue(null);
		Ext.getCmp('gridRodata_OutstockD7201').getStore().removeAll();
 	  },

	/*
	 * 退厂发单
	 */
	send:function(){
		var gridcount=Ext.getCmp("gridRodata_OutstockD7201").getStore().getCount();
		if(gridcount == 0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.selectSendCustom);
        }else {
        	if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
				return;
			}
        	if(commonCheckMster('formRodata_OutstockM7201')){
        		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sendOrNot,
		function(button, text) {
		var detail1 = [];
		if (button == 'yes') {
			var msgShow = commonMegShow($i18n_prompt.sending_wait);
			var sendWorker=Ext.getCmp("cmbWorker7201").getValue();
			var sourceNo=Ext.getCmp("cmbRecede_no7201").getValue();
			var params = {
				    sendWorker:sendWorker,
					str:sourceNo
			};
			Ext.Ajax.request({
				method:'POST',
				url:'rodata_OutstockDirectAction_send.action',
				params:params,
				success:function(response){
					msgShow.hide();
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('cmbSuppliers7201').setValue(null);
						Ext.getCmp('cmbSuppliers7201').getStore().reload();
						Ext.getCmp('cmbRecede_no7201').setValue(null);
						Ext.getCmp('cmbRecede_no7201').getStore().reload();
						Ext.getCmp('cmbWorker7201').setValue(null);
						Ext.getCmp('cmbWorker7201').getStore().reload();
						Ext.getCmp('gridRodata_OutstockD7201').store.reload();
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}	
				},
				failure:function(response){
					msgShow.hide();
					Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
				}
			});	
		}});
	  }
        	}
	 },
 	
	cmbSuppliers7201change:function(th,newValue,oldValue,eOpts)
	{
       var detail1 = [];
		var d={
			columnId:'a.supplier_no',
			value:newValue
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('cmbRecede_no7201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbRecede_no7201').getStore().removeAll();
		Ext.getCmp('cmbRecede_no7201').getStore().load();
		loadgrid_7201();
	},
	
	//选择退厂单号加载数据
	cmbRecede_no7201change:function(combo,records,eOpts){
		loadgrid_7201();
	},
	
	cmbSuppliers7201:function(combo,records,eOpts){
		loadgrid_7201();
	}

});
/*
 * 加载退货单号
 */
function loadgrid_7201(){
	var detail1 = [];
	
	var d={
			columnId:'ood.enterprise_no',
			value:Ext.get('enterpriseNo').getValue()
	};
	detail1.push(d);
	var d={
		columnId:'ood.warehouse_no',
		value:Ext.get('warehouseNo').getValue()
		};
	detail1.push(d);
	if(!Ext.isEmpty(Ext.getCmp('cmbSuppliers7201').getValue())){
		var d={
		columnId:'ood.supplier_no',
		value:Ext.getCmp('cmbSuppliers7201').getValue()
		};
		detail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('cmbRecede_no7201').getValue())){
		var d={
		columnId:'ood.source_no',
		value:Ext.getCmp('cmbRecede_no7201').getValue()
		};
		detail1.push(d);
	}
	var jsonDetail1 = Ext.encode(detail1);
	var wheresql = {
		str : jsonDetail1
	};
	Ext.apply(Ext.getCmp('gridRodata_OutstockD7201').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('gridRodata_OutstockD7201').getStore().removeAll();
	Ext.getCmp('gridRodata_OutstockD7201').getStore().load();
}