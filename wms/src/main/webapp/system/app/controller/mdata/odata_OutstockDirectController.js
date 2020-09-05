/**
 * 模块名称：移库发单
 * 模块编码：5201
 * zhouhuan
 */
 var rowindex5201=0;
 Ext.define('cms.controller.mdata.odata_OutstockDirectController',{
 	extend:'Ext.app.Controller',
 	requires:[
 			'cms.view.mdata.odata_OutstockDirectUI'
 	         ],
 	model:'cms.model.odata.odata_OutStock_DirectModel',
 	store:'',
 	init:function(){
 		 this.control({
	    	'odata_OutstockDirectUI form combo[id=cmbOwnerNo5201]':{
	    		change:this.cmbOwnerNo5201change
	    	},//改变委托业主，加载数据
	    	'odata_OutstockDirectUI form combo[id=cmbWave_no5201]':{
	    		change:this.waveNo5201change
	    	},//改变波次号业主，加载数据
	    	'odata_OutstockDirectUI radiogroup[id=cmbOutstock_type5201]':{
	    		change:this.radiogroupChang5201
	    	},//改变移库类型，加载数据
			'odata_OutstockDirectUI grid[id=gridOdata_OutstockDirectM5201]':{
				selectionchange : this.gridOdata_OutstockDirectM5201select
			},//选择储区grid，加载明细
			'odata_OutstockDirectUI button[name=create5201]':{
				click:this.create5201
			},//发单
			'odata_OutstockDirectUI button[name=refresh]':{
				click:this.refresh
			}//刷新
	    });
 	},
 	
 	
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('cmbOwnerNo5201').getStore().load();
		if(Ext.getCmp("cmbOutstock_type5201").getValue().movetype==1){
			Ext.getCmp('cmbWave_no5201').getStore().load();
		};
		
		//显示变量0为不显示，1为显示
		var packingSpec_5201=commonGetModuleField('5201','packingSpec')[0].flag;
		var packingUnit_5201=commonGetModuleField('5201','packingUnit')[0].flag;
		
		if(packingSpec_5201==0){
			Ext.getCmp('packingSpec_5201').setVisible(false);
		}
		if(packingUnit_5201==0){
			Ext.getCmp('packingUnit_5201').setVisible(false);
		}
		
		
		
	},
 	
 	/*
 	 * 刷新
 	 */
 	 refresh:function(){
 	  	Ext.getCmp('gridOdata_OutstockDirectM5201').getStore().removeAll();
 	  	Ext.getCmp('gridOdata_OutstockDirectD5201').getStore().removeAll();
 	  	Ext.getCmp('cmbOwnerNo5201').setValue();
 	  	Ext.getCmp('cmbOwnerNo5201').getStore().load();
 	  	if(Ext.getCmp('cmbOwnerNo5201').getStore().data.length>0)
		{
			Ext.getCmp('cmbOwnerNo5201').setValue(Ext.getCmp('cmbOwnerNo5201').getStore().getAt(0).data.value);		
		}
	    Ext.getCmp('cmbOutstockworker5201').setValue();
 	  },
 	
 	/*
 	 * 查询移库储区
 	 */
 		cmbOwnerNo5201change:function(th,newValue,oldValue,eOpts){
			var sql="";
			var detail1 = [];
			var d={
				columnId:'ood.owner_no',
				value:newValue
			};
			detail1.push(d);
			var d={
				columnId:'ood.outstock_type',
				value:Ext.getCmp("cmbOutstock_type5201").getValue().movetype
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridOdata_OutstockDirectM5201')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOdata_OutstockDirectM5201').getStore()
					.removeAll();
			Ext.getCmp('gridOdata_OutstockDirectM5201').getStore()
					.load();
		},
		
		radiogroupChang5201:function(obj,newValue,oldValue,eOpts){
			
			var sql="";
			var detail1 = [];
			if(Ext.getCmp('cmbOutstock_type5201').getValue().movetype==1){
 				Ext.getCmp('cmbWave_no5201').setVisible(true);
 				Ext.getCmp('cmbWave_no5201').getStore().load();
 				var d={
				columnId:'ood.wave_no',
				value:Ext.get("cmbWave_no5201").getValue()
			};
			detail1.push(d);
 			}else{
 				Ext.getCmp('cmbWave_no5201').setVisible(false);
 			}
			
			var d={
				columnId:'ood.owner_no',
				value:Ext.getCmp('cmbOwnerNo5201').getValue()
			};
			detail1.push(d);
			var d={
				columnId:'ood.outstock_type',
				value:newValue.movetype
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('gridOdata_OutstockDirectM5201')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOdata_OutstockDirectM5201').getStore()
					.removeAll();
			Ext.getCmp('gridOdata_OutstockDirectM5201').getStore()
					.load();
		},
		/*
	 	 * 查询移库发单信息
	 	 */
 		gridOdata_OutstockDirectM5201select:function(){
 			
			var sql="";
			var data = Ext.getCmp('gridOdata_OutstockDirectM5201').getSelectionModel().getSelection();
			if(data.length == 0){
    			Ext.getCmp('gridOdata_OutstockDirectD5201').getStore().removeAll();
        	}else{
			var areaNo=data[0].get("areaNo");
			var warehouseNo=Ext.get("warehouse_no");
			var ownerNo=Ext.getCmp("cmbOwnerNo5201").getValue();
			var outstockType=Ext.getCmp("cmbOutstock_type5201").getValue().movetype;
			var str = {
				areaNo : areaNo,
				warehouseNo : warehouseNo,
				ownerNo:ownerNo,
				outstockType:outstockType
			};
			Ext.apply(Ext.getCmp('gridOdata_OutstockDirectD5201').getStore().proxy.extraParams,str);
			Ext.getCmp('gridOdata_OutstockDirectD5201').getStore().removeAll();
			Ext.getCmp('gridOdata_OutstockDirectD5201').getStore().load();
        	}
		},
		
		
	/*
	 * 移库发单建单
	 * zhouhuan
	 */
	create5201:function(){

		var gridcount=Ext.getCmp("gridOdata_OutstockDirectD5201").getStore().getCount();
		var record1 = Ext.getCmp('gridOdata_OutstockDirectM5201').getSelectionModel().getSelection();
		if(gridcount == 0){
    		Ext.example.msg('提示',"请选择发单客户！");
        }else {
        	if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				return;
			}
        	if(Ext.isEmpty(Ext.getCmp('cmbOutstockworker5201').getValue())){
        		Ext.example.msg('提示',"请选择发单人员！");
        	}else{
				Ext.Msg.confirm("提示", "确定发单吗？",
				function(button, text) {
				if (button == 'yes') {
					Ext.getCmp('butCreate5201').setDisabled(true);
					var msgShow = commonMegShow("正在发单,请稍等...");
					var detail1=[];
					var detail2=[];
					for(var i=0;i<gridcount;i++){
						var record=Ext.getCmp('gridOdata_OutstockDirectD5201').getStore().getAt(i);
						var d={
									warehouseNo:Ext.get("warehouseNo").getValue(),
									ownerNo:Ext.getCmp("cmbOwnerNo5201").getValue(),
									operateDate:record.get("operateDate"),
									waveNo:record.get('waveNo')
							};
							detail2.push(d);
					};
					var d={
							ownerNo:Ext.getCmp('cmbOwnerNo5201').getValue()
						};
					detail1.push(d);
					var operateType=record1[0].get("operateType");
					var areaNo=record1[0].get("areaNo");
					var celltype=Ext.getCmp("radioCelltype5201").getValue().celltype;
					var outstockworker=Ext.getCmp("cmbOutstockworker5201").getValue();
					var outstockType=Ext.getCmp("cmbOutstock_type5201").getValue().movetype;
					var workerNo=Ext.get("workerNo").getValue();
					var jsonDetail1 = Ext.encode(detail1);	
				
					var jsonStr = Ext.encode(detail2);	
					var params = {
							areaNo:areaNo,
							operateType:operateType,
							celltype:celltype,
						    outstockworker:outstockworker,
						    workerNo:workerNo,
							str:jsonDetail1,
							str2:jsonStr,
							outstockType:Ext.getCmp("cmbOutstock_type5201").getValue().movetype
							
					};
						Ext.Ajax.request({
						method:'POST',
						url:'odata_OutstockDirectAction_send.action',
						params:params,
						success:function(response){
							Ext.getCmp('butCreate5201').setDisabled(false);
							msgShow.hide();
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg('提示',data.msg);
								Ext.getCmp('gridOdata_OutstockDirectM5201').store.reload();
								Ext.getCmp('gridOdata_OutstockDirectD5201').store.reload();
							}else{
								Ext.example.msg('提示',data.msg+data.obj);
							}	
						},
						failure:function(response){
							Ext.getCmp('butCreate5201').setDisabled(false);
							msgShow.hide();
							Ext.example.msg('提示',"提交不上,可能是网络问题");
						}
					});	
					
				}});
		   }
	    }
	}
});
 	
