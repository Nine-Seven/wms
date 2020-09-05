/**
 * 模块名称：返配批次管理
 * 模块编码：6501
 * hcx
 */ 
 Ext.define('cms.controller.ridata.ridata_BatchController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.ridata.ridata_BatchUI'],
 	model:'',
 	store:'',
 	init:function(){
		this.control({
			  //根据仓别和操作时间加载批次
			'ridata_BatchUI datefield[id=operateDate6501]':{
				change:this.selectAndGetBatchNo
			},//根据仓别、操作时间和批次加载返配数据
			'ridata_BatchUI combo[id=batchNo6501]':{
				select:this.getDataWithCondition
			},//结束扫描
			'ridata_BatchUI button[name=butEndSweep6501]':{
				click:this.butEndSweep6501
			}
		});
 	},
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('operateDate6501').setValue(new Date);
		Ext.getCmp('operateDate6501').fireEvent('change','');
		selectAndGetBatchNo();
	},
 	selectAndGetBatchNo: function(){	
 		var operateDate={
 				operateDate:Ext.Date.format( Ext.getCmp('operateDate6501').getValue(),'Y/m/d')
 			};
		if(Ext.getCmp('operateDate6501').getValue()!="" && Ext.getCmp('operateDate6501').getValue()!=null){
			Ext.apply(Ext.getCmp('batchNo6501').getStore().proxy.extraParams,operateDate);
			Ext.getCmp('batchNo6501').getStore().removeAll();
			Ext.getCmp('batchNo6501').getStore().load();
		}else{
			Ext.getCmp('batchNo6501').setValue(null);
			Ext.getCmp('batchNo6501').getStore().removeAll();
		}
		Ext.apply(Ext.getCmp('grid_01_6501').getStore().proxy.extraParams,operateDate);
		Ext.getCmp('grid_01_6501').getStore().removeAll();
		Ext.getCmp('grid_01_6501').getStore().load();	
	},
	getDataWithCondition:function(){		
		var params=
		{
				operateDate:Ext.Date.format( Ext.getCmp('operateDate6501').getValue(),'Y/m/d'),
				batchNo:Ext.getCmp('batchNo6501').getValue()
		};
		        Ext.apply(Ext.getCmp('grid_01_6501').getStore().proxy.extraParams,params);
				Ext.getCmp('grid_01_6501').getStore().removeAll();
				Ext.getCmp('grid_01_6501').getStore().load();
	},
	butEndSweep6501:function(){
		var record = Ext.getCmp('grid_01_6501').getSelectionModel()
		.getSelection();
		if (record.length == 0) {Ext.example.msg("提示","请先选择您要操作的行!");
		return;
	    }else{
	    	var operateDate = record[0].data.operateDateText;
	    	var batchNo = record[0].data.batchNo;
	    	var params={
	    			operateDate:operateDate,
	    			batchNo:batchNo
				};
	    	Ext.Ajax.request({
				method:'post',
				url:'ridata_BatchAction_endSweep.action',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg('提示',"结束扫描成功");
						if(typeof(Ext.getCmp('grid_01_6501'))!=='undefined'){
							Ext.getCmp('grid_01_6501').store.reload();
						}
					}else{
						Ext.example.msg('提示',"结束扫描失败");
					}
				}
			});
	    }
	}
 	});
 	

 