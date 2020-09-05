/**
 * 模块名称：直通验收批次管理
 * 模块编码：4903
 * hcx
 */  
 Ext.define('cms.controller.idata.idata_StraightCheckBatchController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.idata.idata_StraightCheckBatchUI'],
 	model:'',
 	store:'',
 	init:function(){
		this.control({
			  //根据操作时间加载批次
			'idata_StraightCheckBatchUI datefield[id=operateDate4903]':{
				change:this.selectAndGetBatchNo
			},//根据操作时间和批次加载返配数据
			'idata_StraightCheckBatchUI combo[id=batchNo4903]':{
				select:this.getDataWithCondition
			},//结束批次
			'idata_StraightCheckBatchUI button[name=butEndBatch4903]':{
				click:this.butEndBatch4903
			}
		});
 	},
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('operateDate4903').setValue(new Date);
		Ext.getCmp('operateDate4903').fireEvent('change','');
		selectAndGetBatchNo();
	},
 	selectAndGetBatchNo: function(){
 		var operateDate={
 				operateDate:Ext.Date.format( Ext.getCmp('operateDate4903').getValue(),'Y/m/d')
 			};
		if(Ext.getCmp('operateDate4903').getValue()!="" && Ext.getCmp('operateDate4903').getValue()!=null){
			Ext.apply(Ext.getCmp('batchNo4903').getStore().proxy.extraParams,operateDate);
			Ext.getCmp('batchNo4903').getStore().removeAll();
			Ext.getCmp('batchNo4903').getStore().load();
		}else{
			Ext.getCmp('batchNo4903').setValue(null);
			Ext.getCmp('batchNo4903').getStore().removeAll();
		}
		Ext.apply(Ext.getCmp('grid_01_4903').getStore().proxy.extraParams,operateDate);
		Ext.getCmp('grid_01_4903').getStore().removeAll();
		Ext.getCmp('grid_01_4903').getStore().load();	
	},
	getDataWithCondition:function(){		
		var params=
		{
				operateDate:Ext.Date.format( Ext.getCmp('operateDate4903').getValue(),'Y/m/d'),
				batchNo:Ext.getCmp('batchNo4903').getValue()
		};
		        Ext.apply(Ext.getCmp('grid_01_4903').getStore().proxy.extraParams,params);
				Ext.getCmp('grid_01_4903').getStore().removeAll();
				Ext.getCmp('grid_01_4903').getStore().load();
	},
	butEndBatch4903:function(){
		var record = Ext.getCmp('grid_01_4903').getSelectionModel()
		.getSelection();
		var status =record[0].data.statusText;
		if(status == "扫描结束"){
			Ext.example.msg("提示","该批次已结束不能再次结束!");
			return;
		}
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
				url:'idata_StraightCheckBatchAction_endBatch.action',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg('提示',"结束批次成功");
						if(typeof(Ext.getCmp('grid_01_4903'))!=='undefined'){
							Ext.getCmp('grid_01_4903').store.reload();
						}
					}else{
						Ext.Msg.alert('提示',"结束批次失败");
					}
				}
			});
	    }
	}
 	});
 	

 