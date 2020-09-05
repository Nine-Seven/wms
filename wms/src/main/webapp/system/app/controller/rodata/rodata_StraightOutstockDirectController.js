/*
 * 退厂发单（天天惠）
 * hcx
 * 7501
 */
var rowindex7501=0;
bindEnterSkip($('#formRodata_OutstockM7501'));//调用键盘处理方法
Ext.define('cms.controller.rodata.rodata_StraightOutstockDirectController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_StraightOutstockDirectUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//更改供应商，加载数据
			'rodata_StraightOutstockDirectUI combo[id=cmbSuppliers7501]':{
				change:this.cmbSuppliers7501change
			},
			//选退货类型加载数据
			'rodata_StraightOutstockDirectUI combo[id=classType7501]':{
				change:this.classType7501change
			},
			//选泼次号加载数据
			'rodata_StraightOutstockDirectUI combo[id=cmbWaveNo7501]':{
				change:this.cmbWaveNo7501change
			},
			//发单
			'rodata_StraightOutstockDirectUI button[name=send]':{
				click:this.send
			},//查找
			'rodata_StraightOutstockDirectUI button[name=query]':{
				click:this.query
			},//刷新
			'rodata_StraightOutstockDirectUI button[name=refresh]':{
				click:this.refresh
			}
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		var packingUnit7501=commonGetModuleField('7501','packingUnit')[0].flag;
		var packingSpec7501=commonGetModuleField('7501','packingSpec')[0].flag;
		if(packingUnit7501==0){
			Ext.getCmp('packingUnit7501').setVisible(false);
		}
		if(packingSpec7501==0){
			Ext.getCmp('packingSpec7501').setVisible(false);
		}
		Ext.getCmp('cmbSuppliers7501').getStore().load();
	},
	/*
	 * 刷新
	 */
	 refresh:function(){
		 Ext.getCmp('cmbSuppliers7501').store.reload();
		 Ext.getCmp('classType7501').store.reload();
		 Ext.getCmp('cmbWaveNo7501').store.reload();
		 Ext.getCmp('gridRodata_OutstockD7501').store.reload();
 	  },
	/*
	 * 退厂发单
	 */
	send:function(){
		var gridcount=Ext.getCmp("gridRodata_OutstockD7501").getStore().getCount();
		if(gridcount == 0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.selectSendCustom);
        }else {
        	if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
				return;
			}
        	if(commonCheckMster('formRodata_OutstockM7501')){
        		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sendOrNot,
		function(button, text) {
		if (button == 'yes') {
			var msgShow = commonMegShow($i18n_prompt.sending_wait);
			var sendWorker=Ext.getCmp("cmbWorker7501").getValue();
			var supplierNo=Ext.getCmp("cmbSuppliers7501").getValue();
			if(!Ext.isEmpty(Ext.getCmp('cmbWaveNo7501').getValue()) &&
					Ext.getCmp('cmbWaveNo7501').getValue()!='ALL'){
				var waveNo = Ext.getCmp('cmbWaveNo7501').getValue();
			}
			var params = {
				    sendWorker:sendWorker,
				    strWaveNo:waveNo,
				    strSupplierNo:supplierNo,
				    strClassType:Ext.getCmp("classType7501").getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				url:'rodata_StraightOutstockDirectAction_send.action',
				params:params,
				success:function(response){
					msgShow.hide();
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,$i18n_prompt.sendSuccess);
						Ext.getCmp('cmbSuppliers7501').store.reload();
						Ext.getCmp('cmbWaveNo7501').store.reload();
						Ext.getCmp('gridRodata_OutstockD7501').store.reload();
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
	 /**
		 * 查找
		 */
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='7501';
		queryGrid='gridRodata_OutstockD7501';
		cmbSuppliers7501change();
		},
	//选择退货类型加载数据
    classType7501change:function(){
    	if(!Ext.isEmpty(Ext.getCmp('cmbSuppliers7501').getValue())){
			var supplierNo = Ext.getCmp('cmbSuppliers7501').getValue();
			var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.supplier_no',
				value:supplierNo
			};
			listDetail1.push(strDtl);
			if(!Ext.isEmpty(Ext.getCmp('classType7501').getValue())){
				var classType = Ext.getCmp('classType7501').getValue();
				var strDt2 = {
					columnId:'t1.class_type',
					value:classType
				};
				listDetail1.push(strDt2);
				var wheresql = {
						str : Ext.encode(listDetail1)
					};
				    Ext.apply(Ext.getCmp('cmbWaveNo7501').getStore().proxy.extraParams,wheresql);
					Ext.getCmp('cmbWaveNo7501').getStore().removeAll();
				    Ext.getCmp('cmbWaveNo7501').getStore().load();
					Ext.apply(Ext.getCmp('gridRodata_OutstockD7501').getStore().proxy.extraParams,wheresql);
					Ext.getCmp('gridRodata_OutstockD7501').getStore().removeAll();
					Ext.getCmp('gridRodata_OutstockD7501').getStore().load();

			}
		}
    },
	//选择泼次号加载数据
	cmbWaveNo7501change:function(combo,records,eOpts){
		if(!Ext.isEmpty(Ext.getCmp('cmbSuppliers7501').getValue())){
			var supplierNo = Ext.getCmp('cmbSuppliers7501').getValue();
			var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.supplier_no',
				value:supplierNo
			};
			listDetail1.push(strDtl);
			if(!Ext.isEmpty(Ext.getCmp('classType7501').getValue())){
				var classType = Ext.getCmp('classType7501').getValue();
				var strDt2 = {
					columnId:'t1.class_type',
					value:classType
				};
				listDetail1.push(strDt2);
				if(!Ext.isEmpty(Ext.getCmp('cmbWaveNo7501').getValue()) &&
					Ext.getCmp('cmbWaveNo7501').getValue()!='ALL'){
					var waveNo = Ext.getCmp('cmbWaveNo7501').getValue();
					var strDt3 = {
							columnId:'t1.wave_no',
							value:waveNo
						};
						listDetail1.push(strDt3);
				}
				var wheresql = {
						str : Ext.encode(listDetail1)
					};
				Ext.apply(Ext.getCmp('gridRodata_OutstockD7501').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('gridRodata_OutstockD7501').getStore().removeAll();
				Ext.getCmp('gridRodata_OutstockD7501').getStore().load();

			}
		}
	},
	
	cmbSuppliers7501change:function(combo,records,eOpts){
		if(!Ext.isEmpty(Ext.getCmp('cmbSuppliers7501').getValue())){
			var supplierNo = Ext.getCmp('cmbSuppliers7501').getValue();
			var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.supplier_no',
				value:supplierNo
			};
			listDetail1.push(strDtl);
			var wheresql = {
					str : Ext.encode(listDetail1)
				};
			    Ext.apply(Ext.getCmp('classType7501').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('classType7501').getStore().removeAll();
			    Ext.getCmp('classType7501').getStore().load();
			    Ext.apply(Ext.getCmp('cmbWaveNo7501').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('cmbWaveNo7501').getStore().removeAll();
			    Ext.getCmp('cmbWaveNo7501').getStore().load();
				Ext.apply(Ext.getCmp('gridRodata_OutstockD7501').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('gridRodata_OutstockD7501').getStore().removeAll();
				Ext.getCmp('gridRodata_OutstockD7501').getStore().load();

		}
	}
});