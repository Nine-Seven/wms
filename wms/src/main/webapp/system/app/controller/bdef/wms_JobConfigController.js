/**
 * 模块名称： 后台管理控制
 * 模块编码：I701
 * 创建：HKL
 */

Ext.define('cms.controller.bdef.wms_JobConfigController', {
	extend : 'Ext.app.Controller',
	id:'bdef.wms_JobConfigController',
	requires : [ 'cms.view.bdef.wms_JobConfigUI'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({//查找
			'wms_JobConfigUI button[id=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'wms_JobConfigUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'wms_JobConfigUI button[name=userNextButton]':{
				click:this.userNext
			},//Grid双击
			'wms_JobConfigUI grid[id=grid_01_I701]':{
				itemdblclick:this.grid_01_I701Click
			},//TAB页切换
			'wms_JobConfigUI tabpanel[id=tabPIdI701]':{
				tabchange:this.tabPIdtabchange
			},//点击启用
			'wms_JobConfigUI button[id=executeStatusI701_0]':{
				click:this.executeStatusI701_0
			},//点击禁用
			'wms_JobConfigUI button[id=executeStatusI701_1]':{
				click:this.executeStatusI701_1
			}
			
		});
	},
	
	
 	//
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=I701;
		queryGrid='grid_01_I701';	
	},
	
	
	userPrev:function(){
		rowindexI701=rowindexI701-1;
		editImportI701(rowindexI701);
	},
	
	userNext:function(){
		rowindexI701=rowindexI701+1;
		editImportI701(rowindexI701);
	},
	
	
	//
	grid_01_I701Click:function(th, record,  item,  index, e, eOpts ){
		
			Ext.getCmp('tabPIdI701').items.items[1].setVisible(true);
	},
	
	//
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPIdd2_I701i'){
			var data = Ext.getCmp('grid_01_I701').getSelectionModel().getSelection();
			if(data.length!=0){
				commonEditButton('menuI701','dbclick');
				editImportI701(data[0].index);
				rowindexI701=data[0].index;
			
			}
		}
	},
	
	executeStatusI701_0:function(){
		
		var params = {
				procName : Ext.getCmp('procNameI701').getValue(),
		        flag:'0'
		};
		Ext.Ajax.request({
			method:'POST',
			url:'wmsJobConfigAction_updateWmsJobConfig',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示','启用成功');
					Ext.getCmp('grid_01_I701').getStore().load();
					Ext.getCmp('radiogroupI701_0').setValue({'rg':'0'});
				}else{
					Ext.example.msg('提示','启用失败');
				}
			}
		});	
		
	},
	executeStatusI701_1:function(){
		var params = {
				procName : Ext.getCmp('procNameI701').getValue(),
		        flag:'1'
		};
		Ext.Ajax.request({
			method:'POST',
			url:'wmsJobConfigAction_updateWmsJobConfig',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示','禁用成功');
					Ext.getCmp('grid_01_I701').getStore().load();
					Ext.getCmp('radiogroupI701_0').setValue({'rg':'1'});
				}else{
					Ext.example.msg('提示','禁用失败');
				}
			}
		});	
	
	}
});


//填充数据
function editImportI701(rowindexI701){
	if(rowindexI701==0)
	{
		Ext.getCmp('menuI701').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menuI701').items.items[0].enable(true);
	}
	if(rowindexI701==Ext.getCmp('grid_01_I701').getStore().getCount()-1)
	{		
		Ext.getCmp('menuI701').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menuI701').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_I701').getStore().getAt(rowindexI701-(Ext.getCmp('grid_01_I701').getStore().currentPage-1)*appConfig.getPageSize());

	
    Ext.getCmp('procNameI701').setValue(record.data.procName);
    
  /*  Ext.getCmp('supplierNoI701').getStore().add({
    	value:record.data.supplierNo,
    	dropValue:'['+record.data.supplierNo+']'+record.data.supplierName,
    	text:record.data.supplierName
    });*/
    Ext.getCmp('RemarkI701').setValue(record.data.procNameDesc);
    if(record.data.executeStatus == 0){//正常
    	Ext.getCmp('radiogroupI701_0').setValue({'rg':record.data.executeStatus});
    }else{//禁用
    	Ext.getCmp('radiogroupI701_1').setValue({'rg':record.data.executeStatus});
    }
    
    Ext.getCmp('lastRunTimeI701').setValue(record.data.lastRunTime);
    Ext.getCmp('startTimeLimitI701').setValue(record.data.startTimeLimit);
    Ext.getCmp('endTimeLimitI701').setValue(record.data.endTimeLimit);
	Ext.getCmp('runTimeIntervalI701').setValue(record.data.runTimeInterval);
	Ext.getCmp('runCountTypeI701').setValue(String(record.data.runCountTypeText));
	
};
