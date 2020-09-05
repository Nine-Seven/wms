/**
 * 模块名称：拣货策略配置
 * 模块编码：I803
 * 创建：MM
 */

var rowindexI803=0;
var typeI803='';      //用于判断是否重新加载数据，值为“edit”则加载数据，值为“add”不加载数据                     
Ext.define('cms.controller.odata.odata_wmsOutPickStrategyController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.odata.odata_WmsOutPickStrategyUI',
	          'cms.view.odata.window.odata_wmsOutPickStrategyAddOrEditWindow'],
	init:function(){
		this.control({
			//新增
			'odata_WmsOutPickStrategyUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//修改
			'odata_WmsOutPickStrategyUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			/*//双击
			'odata_WmsOutPickStrategyUI grid[id=wmsOutPickStrategyI803]':{
				itemdblclick:this.detailEdit
			},	*/		
			//关闭窗口
			'odata_wmsOutPickStrategyAddOrEditWindow button[name=close]':{
				click:this.colse
			},
			//重新加载添加窗口
			'odata_wmsOutPickStrategyAddOrEditWindow button[name=add]':{
				click:this.add
			},
			//保存用户信息
			'odata_wmsOutPickStrategyAddOrEditWindow button[name=save]':{
				click:this.save
			}, 
			//上一条记录
			'odata_wmsOutPickStrategyAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'odata_wmsOutPickStrategyAddOrEditWindow button[name=next]':{
				click:this.next
			},//查询 
			'odata_WmsOutPickStrategyUI button[name=detailQuery]':{
				click:this.detailQuery
			},//刷新
			'odata_WmsOutPickStrategyUI button[name=refresh]':{
				click:this.refresh
			}
		});
	},
	
	//调用新增窗口
	detailAdd:function(){
		//debugger;
		Ext.create('cms.view.odata.window.odata_wmsOutPickStrategyAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show(); 
		addCommMenu5('odata_wmsOutPickStrategyI803');
		typeI803='add';
	},
	//点击保存按钮，用于保存货主信息
	save:function(){
		saveOutPickData();
	},
	//调用编辑窗口
	detailEdit:function(){ 
		var data = Ext.getCmp('wmsOutPickStrategyI803').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.odata.window.odata_wmsOutPickStrategyAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			rowindexI803=data[0].index;
			loadOutPickData(rowindexI803);
			commonSetCommMenu5PrevOrNext('odata_wmsOutPickStrategyI803','wmsOutPickStrategyI803',rowindexI803);	
			commonSetMsterReadOnlyByArray(
					new Array('pickStrategyIDI803'),true);
			updateCommMenu5('odata_wmsOutPickStrategyI803');
		}
		typeI803='edit';	
	},	

	//关闭窗口
	colse:function(){
		Ext.getCmp('odata_wmsOutPickStrategyAddOrEditWindow').close();
	},
	
	//实现新增功能（清空窗口的内容）	
	add:function(){
		Ext.getCmp('IdFormI803').getForm().reset();
		bindEnterSkip($('IdFormI803'));//调用键盘处理方法
	},
	//刷新
	refresh:function(){
		Ext.getCmp('wmsOutPickStrategyI803').getStore().removeAll();
		Ext.getCmp('wmsOutPickStrategyI803').getStore().load();
	},
	detailQuery:function()
	{
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='I803';
		queryGrid='wmsOutPickStrategyI803';
	},
	     
	//实现上一页功能
	prev:function(){
		commonMenu5PrevOrNext('odata_wmsOutPickStrategyI803','wmsOutPickStrategyI803',-1);
		loadOutPickData(rowindexI803);
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('odata_wmsOutPickStrategyI803','wmsOutPickStrategyI803',+1);
		loadOutPickData(rowindexI803);
	},
	
	getItemType:function(){
		return typeI803;
	}
});

//保存
 function saveOutPickData(){
    if(!commonCheckIsInputAll('IdFormI803')){
		return;
	} 
	if(Ext.getCmp('IdFormI803').getForm().isValid()){  
		var data1 = Ext.getCmp('wmsOutPickStrategyI803').getSelectionModel().getSelection()[0]; 
			 //debugger;
		var bowms_OutPickS={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				pickStrategyID:Ext.getCmp('pickStrategyIDI803').getValue() 
			},	
			pickStrategyName:Ext.getCmp('pickStrategyNameI803').getValue(),
			pickDiffFlag:Ext.getCmp('pickDiffFlagI803').getValue(),
			pickAutoFlag:Ext.getCmp('pickAutoFlagI803').getValue(),
			autoGetdivideFlag:Ext.getCmp('autoGetdivideFlagI803').getValue(),
			autoDividesaveFlag:Ext.getCmp('autoDividesaveFlagI803').getValue(),
			autoGetdivideFlag:Ext.getCmp('autoGetdivideFlagI803').getValue(),
			rsvValue1:Ext.getCmp('rsvValue1I803').getValue(),
			rsvValue2:Ext.getCmp('rsvValue2I803').getValue(),
			rsvValue3:Ext.getCmp('rsvValue3I803').getValue(),
			rsvValue4:Ext.getCmp('rsvValue4I803').getValue(),
			rsvValue5:Ext.getCmp('rsvValue5I803').getValue(),  
			rgstName:typeI803=='add'?Ext.get('workerNo').getValue():data1.data.rgstName,
			rgstDate:typeI803=='add'?new Date():data1.data.rgstDate,
			updtName:typeI803=='add'?'':Ext.get('workerNo').getValue(),
			updtDate:typeI803=='add'?'':new Date()
		};
		
		Ext.Ajax.request({
			url:'wms_outPickStrategyAction_saveWmsOutPick.action',
			method:'post',
			params:{
				str:Ext.encode(bowms_OutPickS)
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){					
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('wmsOutPickStrategyI803').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});	
	}
}	

//加载数据
 function loadOutPickData(rowindexI803){
 	var outPick = Ext.getCmp('wmsOutPickStrategyI803').getSelectionModel().getSelection()[0];
	
	Ext.getCmp('pickStrategyIDI803').setValue(outPick.data.pickStrategyId);
	Ext.getCmp('pickStrategyNameI803').setValue(outPick.data.pickStrategyName);
	//Ext.getCmp('pickDiffFlagI803').setValue(outPick.data.pickDiffFlag); 
	/*Ext.getCmp('pickDiffFlagI803').getStore().add({
		id:outPick.data.pickDiffName,
		name:outPick.data.pickDiffFlag 
    });*/
	Ext.getCmp('pickDiffFlagI803').setValue(outPick.data.pickDiffName);
	
	Ext.getCmp('pickAutoFlagI803').setValue(outPick.data.pickAutoName);
	Ext.getCmp('autoGetdivideFlagI803').setValue(outPick.data.autoGetdivideName);	
	Ext.getCmp('autoDividesaveFlagI803').setValue(outPick.data.autoDividesaveName); 
	Ext.getCmp('rsvValue1I803').setValue(outPick.data.rsvValue1); 
	Ext.getCmp('rsvValue2I803').setValue(outPick.data.rsvValue2); 
	Ext.getCmp('rsvValue3I803').setValue(outPick.data.rsvValue3); 
	Ext.getCmp('rsvValue4I803').setValue(outPick.data.rsvValue4); 
	Ext.getCmp('rsvValue5I803').setValue(outPick.data.rsvValue5);  
}





