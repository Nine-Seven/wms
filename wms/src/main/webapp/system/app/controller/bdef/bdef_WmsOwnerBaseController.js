/**
 * 模块名称：货主参数配置
 * 模块编码：1M01
 * 创建：chensr
 */

var rowindex1M01=0;
var type1M01='';      //用于判断是否重新加载数据，值为“edit”则加载数据，值为“add”不加载数据                     
Ext.define('cms.controller.bdef.bdef_WmsOwnerBaseController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_WmsOwnerBaseUI'],
	init:function(){
		this.control({
			//新增
			'bdef_WmsOwnerBaseUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//修改
			'bdef_WmsOwnerBaseUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//双击
			'bdef_WmsOwnerBaseUI grid[id=wmsOwnerBase1M01]':{
				itemdblclick:this.detailEdit
			},			
			//关闭窗口
			'bdef_WmsOwnerBaseAddOrEditWindow button[name=close]':{
				click:this.colse
			},
			//重新加载添加窗口
			'bdef_WmsOwnerBaseAddOrEditWindow button[name=add]':{
				click:this.add
			},
			//保存用户信息
			'bdef_WmsOwnerBaseAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//业务切换(window)
			'bdef_WmsOwnerBaseAddOrEditWindow combo[id=groupNo1M01]':{
				change:this.group_noChange
			},
			//业务切换（UI）并查询相应的信息
			'bdef_WmsOwnerBaseUI combo[id=groupNoUI1M01]':{
				change:this.group_noselect_UI
			},
			//选择子业务查询相应的信息
			'bdef_WmsOwnerBaseUI combo[id=subGroupNoUI1M01]':{
				select:this.selectWithCondition
			},
			
			//colName切换
			'bdef_WmsOwnerBaseAddOrEditWindow combo[id=colName1M01]':{
				change:this.colName_noChange
			},
			//上一条记录
			'bdef_WmsOwnerBaseAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_WmsOwnerBaseAddOrEditWindow button[name=next]':{
				click:this.next
			}
		});
	},
	
	//调用新增窗口
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_WmsOwnerBaseAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		commonSetMsterReadOnlyByArray(
				new Array('memo1M01'),true);
		addCommMenu5('bdef_ownerBaseNo1M01');
		type1M01='add';
	},
	//点击保存按钮，用于保存货主信息
	save:function(){
		saveOwnerBaseData();
	},
	//调用编辑窗口
	detailEdit:function(){
		var data = Ext.getCmp('wmsOwnerBase1M01').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_WmsOwnerBaseAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			rowindex1M01=data[0].index;
			loadOwnerBaseData(rowindex1M01);
			commonSetCommMenu5PrevOrNext('bdef_ownerBaseNo1M01','wmsOwnerBase1M01',rowindex1M01);	
			commonSetMsterReadOnlyByArray(
					new Array('ownerBaseNo1M01','colName1M01','groupNo1M01',
					'subGroupNo1M01','memo1M01'),true);
			updateCommMenu5('bdef_ownerBaseNo1M01');
		}
		type1M01='edit';	
	},	

	//关闭窗口
	colse:function(){
		Ext.getCmp('bdef_WmsOwnerBaseAddOrEditWindow').close();
	},
	
	//实现新增功能（清空窗口的内容）	
	add:function(){
		Ext.getCmp('IdForm1M01').getForm().reset();
		bindEnterSkip($('IdForm1M01'));//调用键盘处理方法
	},
	//根据group_no加载sub_group_no和colname(用于窗口的加载)
	group_noChange:function()
	{	//获取sub_group_no
		var wheresql=
		{
			strQuery:Ext.getCmp('groupNo1M01').getValue()
		};
		Ext.apply(Ext.getCmp('subGroupNo1M01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('subGroupNo1M01').getStore().removeAll();
		Ext.getCmp('subGroupNo1M01').setValue(null);
		Ext.getCmp('subGroupNo1M01').getStore().load();
		
		//获取colname
		Ext.apply(Ext.getCmp('colName1M01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('colName1M01').getStore().removeAll();
		Ext.getCmp('colName1M01').setValue(null);
		Ext.getCmp('colName1M01').getStore().load();
		
	},
	//根据group_no加载sub_group_no(用于UI界面的加载)，并查询相应的信息
	group_noselect_UI:function()
	{
		var wheresql=
		{
			strQuery:Ext.getCmp('groupNoUI1M01').getValue()
		};
		Ext.apply(Ext.getCmp('subGroupNoUI1M01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('subGroupNoUI1M01').getStore().removeAll();
		Ext.getCmp('subGroupNoUI1M01').getStore().load();
		
		//用于查询
		var strDetail1 = [];
		var d={
			columnId:'wob.group_no',
			value:Ext.getCmp('groupNoUI1M01').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('groupNoUI1M01').getValue()) &&
				Ext.getCmp('groupNoUI1M01').getValue()!='ALL'){
			strDetail1.push(d);
		}
		
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str : jsonDetail
		};
		Ext.apply(Ext.getCmp('wmsOwnerBase1M01').getStore().proxy.extraParams,str);
		Ext.getCmp('wmsOwnerBase1M01').getStore().removeAll();
		Ext.getCmp('wmsOwnerBase1M01').getStore().load();
		
	},
	
	//选择子业务查询相应的信息
	selectWithCondition:function(){
		var strDetail1 = [];
		var d={
			columnId:'wob.group_no',
			value:Ext.getCmp('groupNoUI1M01').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('groupNoUI1M01').getValue()) &&
				Ext.getCmp('groupNoUI1M01').getValue()!='ALL'){
			strDetail1.push(d);
		}
		var d2={
			columnId:'wob.sub_group_no',
			value:Ext.getCmp('subGroupNoUI1M01').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('subGroupNoUI1M01').getValue())&&
				Ext.getCmp('groupNoUI1M01').getValue()!='ALL'){
			strDetail1.push(d2);
		}	
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str : jsonDetail
		};
		Ext.apply(Ext.getCmp('wmsOwnerBase1M01').getStore().proxy.extraParams,str);
		Ext.getCmp('wmsOwnerBase1M01').getStore().removeAll();
		Ext.getCmp('wmsOwnerBase1M01').getStore().load();
		
	},
	//根据colname加载sdefine和memo
	colName_noChange:function()
	{
		var wheresql=
		{
			strQuery:Ext.getCmp('colName1M01').getValue()
		};
		Ext.apply(Ext.getCmp('sdefine1M01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('sdefine1M01').getStore().removeAll();
		Ext.getCmp('sdefine1M01').setValue(null);
		Ext.getCmp('sdefine1M01').getStore().load();
			
		Ext.Ajax.request({
			url : 'bdef_WmsOwnerBaseAction_getMemo',
			params : {
				strQuery:Ext.getCmp('colName1M01').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res){
		    		Ext.getCmp('memo1M01').setValue(res[0]);
		    	}
			}
		});
	},
	//实现上一页功能
	prev:function(){
		commonMenu5PrevOrNext('bdef_ownerBaseNo1M01','wmsOwnerBase1M01',-1);
		loadOwnerBaseData(rowindex1M01);
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('bdef_ownerBaseNo1M01','wmsOwnerBase1M01',+1);
		loadOwnerBaseData(rowindex1M01);
	},
	
	getItemType:function(){
		return type1M01;
	}
});

//保存
 function saveOwnerBaseData(){
    if(!commonCheckIsInputAll('IdForm1M01')){
		return;
	}
	if(Ext.getCmp('IdForm1M01').getForm().isValid()){
		var boBdef_DefOwner={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				ownerNo:Ext.getCmp('ownerBaseNo1M01').getValue(),
				colName:Ext.getCmp('colName1M01').getValue()
			},	
			groupNo:Ext.getCmp('groupNo1M01').getValue(),
			subGroupNo:Ext.getCmp('subGroupNo1M01').getValue(),
			ndefine:Ext.getCmp('ndefine1M01').getValue(),
			sdefine:Ext.getCmp('sdefine1M01').getValue(),
			memo:Ext.getCmp('memo1M01').getValue() 					
		};
		Ext.Ajax.request({
			url:'bdef_WmsOwnerBaseAction_saveWmsOwnerBase.action',
			method:'post',
			params:{
				str:Ext.encode(boBdef_DefOwner)
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){					
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('wmsOwnerBase1M01').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});	
	}
}	

//加载数据
 function loadOwnerBaseData(rowindex1M01){
 	var cust = Ext.getCmp('wmsOwnerBase1M01').getSelectionModel().getSelection()[0];
	
	Ext.getCmp('ownerBaseNo1M01').setValue(cust.data.ownerNo);
	Ext.getCmp('groupNo1M01').setValue(cust.data.groupNo);
	Ext.getCmp('ndefine1M01').setValue(cust.data.ndefine);
	Ext.getCmp('memo1M01').setValue(cust.data.memo);	
	
	var wheresql=
	{
		strQuery:Ext.getCmp('colName1M01').getValue()
	};
	Ext.apply(Ext.getCmp('sdefine1M01').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('sdefine1M01').getStore().removeAll();
	Ext.getCmp('sdefine1M01').setValue(null);
	Ext.getCmp('sdefine1M01').getStore().load();		
}





