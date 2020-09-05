/**
 * 模块名称：返配波次管理
 * 模块编码：6502
 * hkl
 */ 
 Ext.define('cms.controller.ridata.ridata_WaveController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.ridata.ridata_WaveUI'],
 	model:'',
 	store:'',
 	init:function(){
		this.control({
			  //根据状态加载波次
			'ridata_WaveUI combo[id=status6502]':{
				change:this.selectAndGetWaveNo
			},//结束扫描
			'ridata_WaveUI button[name=butEndSweep6502]':{
				click:this.butEndSweep6502
			}
		});
 	},
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('status6502').setValue('0');
		Ext.getCmp('status6502').fireEvent('change','');
		selectAndGetWaveNo();
	},
 	selectAndGetWaveNo: function(){	
 		var status={
 				status:Ext.getCmp('status6502').getValue()
 			};
		Ext.apply(Ext.getCmp('grid_01_6502').getStore().proxy.extraParams,status);
		Ext.getCmp('grid_01_6502').getStore().removeAll();
		Ext.getCmp('grid_01_6502').getStore().load();	
	},

	butEndSweep6502:function(){
		var record = Ext.getCmp('grid_01_6502').getSelectionModel().getSelection();
		if (record.length == 0) {
			Ext.example.msg("提示","请先选择您要操作的行!");
		    return;
	    }else if(record[0].data.status == '15'){
	    	Ext.example.msg("提示","该波次号已经结束，请重新选择!");
		    return;
	    }else
	    {
	    	var waveNo = record[0].data.waveNo;
	    	var params={
	    			waveNo:waveNo
				};
	    	Ext.Ajax.request({
				method:'post',
				url:'ridata_WaveAction_endSweep.action',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg('提示',data.msg);
						Ext.getCmp('grid_01_6502').store.reload();
					}else{
						Ext.example.msg('提示',data.msg);
					}
				}
			});
	    }
	}
 	});
 	

 