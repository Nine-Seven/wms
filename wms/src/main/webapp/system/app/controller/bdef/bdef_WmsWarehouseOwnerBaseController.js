/**
 * 模块名称：货主仓别参数配置Controller
 * 模块编码：1N01
 * 创建：chensr
 */
var rowindex1N01=0;
var type1N01='';      //用于判断是否重新加载数据，值为“edit”则加载数据，值为“add”不加载数据         
Ext.define('cms.controller.bdef.bdef_WmsWarehouseOwnerBaseController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_WmsWarehouseOwnerBaseUI'],
	init:function(){
		this.control({
			//新增
			'bdef_WmsWarehouseOwnerBaseUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//修改
			'bdef_WmsWarehouseOwnerBaseUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//双击
			'bdef_WmsWarehouseOwnerBaseUI grid[id=wmsWarehouseOwnerBase1N01]':{
					itemdblclick:this.detailEdit
			},			
			//关闭窗口
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow button[name=close]':{
				click:this.colse
			},
			//重新加载添加窗口
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow button[name=add]':{
				click:this.add
			},
			//保存用户信息
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//业务切换(window)
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow combo[id=groupNo1N01]':{
				change:this.group_noChange
			},
			//业务切换（UI）并查找相应的信息
			'bdef_WmsWarehouseOwnerBaseUI combo[id=groupNoUI1N01]':{
				change:this.group_noselect_UI
			}, 
			//选择子业务查询相应的信息
			'bdef_WmsWarehouseOwnerBaseUI combo[id=subGroupNoUI1N01]':{
				select:this.selectWithCondition
			},
			//colName切换
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow combo[id=colName1N01]':{
				change:this.colName_noChange
			},
			//上一条记录
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_WmsWarehouseOwnerBaseAddOrEditWindow button[name=next]':{
				click:this.next
			}
		});
	},
	//调用新增窗口
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_WmsWarehouseOwnerBaseAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		Ext.getCmp('warehouseNo1N01').setValue(Ext.get('warehouseNo').getValue());
		commonSetMsterReadOnlyByArray(
				new Array('memo1N01'),true);
		addCommMenu5('bdef_WmsWarehouseOwner1N01');
		type1N01='add';
	},
	
	//根据group_no加载sub_group_no(用于窗口的加载)
	group_noChange:function()
	{
		var wheresql=
		{
			strQuery:Ext.getCmp('groupNo1N01').getValue()
		};
		Ext.apply(Ext.getCmp('subGroupNo1N01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('subGroupNo1N01').getStore().removeAll();
		Ext.getCmp('subGroupNo1N01').setValue(null);
		Ext.getCmp('subGroupNo1N01').getStore().load();
					
		//加载colName
		var strDetail1 = [];
		var d={
			columnId:'t1.group_no',
			value:Ext.getCmp('groupNo1N01').getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('colName1N01').getStore().proxy.extraParams,str);
		Ext.getCmp('colName1N01').getStore().removeAll();
		Ext.getCmp('colName1N01').getStore().load();	
			
		if(type1N01=='edit'){
			this.loadOwnerBaseData(rowindex1N01);
		}
	},
	
	//根据group_no加载sub_group_no(用于UI界面的加载)
	group_noselect_UI:function()
	{	
		var wheresql=
		{
			strQuery:Ext.getCmp('groupNoUI1N01').getValue()
		};
		Ext.apply(Ext.getCmp('subGroupNoUI1N01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('subGroupNoUI1N01').getStore().removeAll();
		Ext.getCmp('subGroupNoUI1N01').getStore().load();
		
		var strDetail1 = [];
		var d={
			columnId:'t1.group_no',
			value:Ext.getCmp('groupNoUI1N01').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('groupNoUI1N01').getValue()) &&
				Ext.getCmp('groupNoUI1N01').getValue()!='ALL'){
			strDetail1.push(d);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strWheresql : jsonDetail
		};
		Ext.apply(Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().removeAll();
		Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().load();
		
	},
	
	//选择子业务查询相应的信息
	selectWithCondition:function(){
		var strDetail1 = [];
		var d={
			columnId:'t1.group_no',
			value:Ext.getCmp('groupNoUI1N01').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('groupNoUI1N01').getValue()) &&
				Ext.getCmp('groupNoUI1N01').getValue()!='ALL'){
			strDetail1.push(d);
		}
		var d2={
			columnId:'t1.sub_group_no',
			value:Ext.getCmp('subGroupNoUI1N01').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('subGroupNoUI1N01').getValue()) &&
				Ext.getCmp('groupNoUI1N01').getValue()!='ALL'){
			strDetail1.push(d2);
		}	
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strWheresql : jsonDetail
		};
		Ext.apply(Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().removeAll();
		Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().load();
		
	},
	//根据colname加载ndefine和memo
	colName_noChange:function()
	{
		var wheresql=
		{
			strQuery:Ext.getCmp('colName1N01').getValue()
		};
		Ext.apply(Ext.getCmp('sdefine1N01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('sdefine1N01').getStore().removeAll();
		Ext.getCmp('sdefine1N01').setValue(null);
		Ext.getCmp('sdefine1N01').getStore().load();
			
		Ext.Ajax.request({
			url : 'bdef_WmsOwnerBaseAction_getMemo',
			params : {
				strQuery:Ext.getCmp('colName1N01').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res){
		    		Ext.getCmp('memo1N01').setValue(res[0]);
		    	}
			}
		});
		if(type1N01=='edit'){
			this.loadOwnerBaseData(rowindex1N01);
		}
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('bdef_WmsWarehouseOwnerBaseAddOrEditWindow').close();
	},
	
	//实现新增功能（清空窗口的内容）	
	add:function(){
		Ext.getCmp('IdForm1N01').getForm().reset();
		bindEnterSkip($('IdForm1N01'));//调用键盘处理方法
		saveType1F01='add';
	},
	
	//点击保存按钮，用于保存货主仓别信息
	save:function(){
		if(!commonCheckIsInputAll('IdForm1N01')){
			return;
		}
		if(Ext.getCmp('IdForm1N01').getForm().isValid()){
			var boBdef_DefOwner={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					ownerNo:Ext.getCmp('ownerBaseNo1N01').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),	
					colName:Ext.getCmp('colName1N01').getValue()
				},	
				groupNo:Ext.getCmp('groupNo1N01').getValue(),
				subGroupNo:Ext.getCmp('subGroupNo1N01').getValue(),
				ndefine:Ext.getCmp('ndefine1N01').getValue(),
				sdefine:Ext.getCmp('sdefine1N01').getValue(),
				memo:Ext.getCmp('memo1N01').getValue() 
				
			};
			Ext.Ajax.request({
				url:'bdef_WmsWarehouseOwnerBaseAction_saveWarehouseOwnerBase.action',
				method:'post',
				params:{
					str:Ext.encode(boBdef_DefOwner)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){					
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('wmsWarehouseOwnerBase1N01').getStore().load();
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}
	},
	//调用编辑窗口
	detailEdit:function(){
		var data = Ext.getCmp('wmsWarehouseOwnerBase1N01').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_WmsWarehouseOwnerBaseAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			rowindex1N01=data[0].index;
			this.loadOwnerBaseData(rowindex1N01);
			commonSetCommMenu5PrevOrNext('bdef_WmsWarehouseOwner1N01','wmsWarehouseOwnerBase1N01',rowindex1N01);	
			commonSetMsterReadOnlyByArray(
					new Array('ownerBaseNo1N01','colName1N01','groupNo1N01',
					'subGroupNo1N01','memo1N01','warehouseNo1N01'),true);
			updateCommMenu5('bdef_WmsWarehouseOwner1N01');
		}  
		type1N01='edit';
	},
	//加载数据
	loadOwnerBaseData: function(rowindex1N01){		
		var cust = Ext.getCmp('wmsWarehouseOwnerBase1N01').getSelectionModel().getSelection()[0];
		Ext.getCmp('ownerBaseNo1N01').setValue(cust.data.ownerNo);
		Ext.getCmp('warehouseNo1N01').setValue(cust.data.warehouseNo);
		Ext.getCmp('groupNo1N01').setValue(cust.data.groupNo);
		Ext.getCmp('ndefine1N01').setValue(cust.data.ndefine);
		Ext.getCmp('memo1N01').setValue(cust.data.memo);
		

		var wheresql=
		{
			strQuery:Ext.getCmp('colName1N01').getValue()
		};
		Ext.apply(Ext.getCmp('sdefine1N01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('sdefine1N01').getStore().removeAll();
		Ext.getCmp('sdefine1N01').setValue(null);
		Ext.getCmp('sdefine1N01').getStore().load();		
		Ext.getCmp('sdefine1N01').setValue(String(cust.data.sdefine));
	},
	
	//根据groupNo和subGroupNo加载colName
	subGroupNoChange:function(){
		var strDetail1 = [];
		var d={
			columnId:'t1.group_no',
			value:Ext.getCmp('groupNo1N01').getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('colName1N01').getStore().proxy.extraParams,str);
		Ext.getCmp('colName1N01').getStore().removeAll();
		Ext.getCmp('colName1N01').getStore().load();	
	},	
	
	//实现上一页功能
	prev:function(){
		commonMenu5PrevOrNext('bdef_WmsWarehouseOwner1N01','wmsWarehouseOwnerBase1N01',-1);
		this.loadOwnerBaseData(rowindex1N01);	
	},
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('bdef_WmsWarehouseOwner1N01','wmsWarehouseOwnerBase1N01',+1);
		this.loadOwnerBaseData(rowindex1N01);	
	},
	getItemType:function(){
		return type1N01;
	}
});