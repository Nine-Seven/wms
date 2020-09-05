/**
 * 模块名称：分拨订单管理
 * 模块编码：C301
 * 创建：csr
 */

var rowindexC301=0;
var isCanEditC301=false;

Ext.define('cms.controller.acdata.acdata_OrderController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.acdata.acdata_OrderUI'
	         ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'acdata_OrderUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'acdata_OrderUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'acdata_OrderUI button[name=userNextButton]':{
				click:this.userNext
			},//TAB页切换
			'acdata_OrderUI tabpanel[id=tabPIdC301]':{
				tabchange:this.tabPIdtabchange
			},//单据列表 双击
			'acdata_OrderUI grid[id=grid_01_C301]':{
				itemdblclick:this.grid_01_C301Click			
			},//修改
			'acdata_OrderUI button[name=userEditButton]':{
				click:this.edit
			},//撤销
			'acdata_OrderUI button[name=userUndoButton]':{
				click:this.undo
			},//订单明细编辑
			'acdata_OrderUI grid[id=grid_02_C301]':{
				beforeedit:this.grid_02_C301beforeedit
			},//保存
			'acdata_OrderUI button[name=userSaveButton]':{
				click:this.save
			},//接单确认
			'acdata_OrderUI button[name=orderCheck]':{
				click:this.orderCheck
			},//回单确认
			'acdata_OrderUI button[name=orderOver]':{
				click:this.orderOver
			}
			
		});
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='C301';
		queryGrid='grid_01_C301';	
	},
	
	orderOver:function(){
		saveOrCheck(13);
		commonSetMsterReadOnlyByArray(
				new Array('sourceNoC301','orderNoC301',
				'sdateC301','ownerAliasC301','ownerAddrC301',
				'ownerLinkmanC301','ownerPhoneC301','custAliasC301','custAddrC301',
				'custLinkmanC301','custPhoneC301'),true);	
		Ext.getCmp('menuC301').items.items[0].enable(true);
		Ext.getCmp('menuC301').items.items[1].enable(true);
		Ext.getCmp('menuC301').items.items[2].enable(true);
		Ext.getCmp('menuC301').items.items[3].disable(true);
		Ext.getCmp('menuC301').items.items[4].disable(true);
	
	
		Ext.getCmp('menuC301').items.items[5].disable(true);
		Ext.getCmp('menuC301').items.items[6].disable(true);
	
	},
	
	orderCheck:function(){
		saveOrCheck(12);
		commonSetMsterReadOnlyByArray(
				new Array('sourceNoC301','orderNoC301',
				'sdateC301','ownerAliasC301','ownerAddrC301',
				'ownerLinkmanC301','ownerPhoneC301','custAliasC301','custAddrC301',
				'custLinkmanC301','custPhoneC301'),true);	
		Ext.getCmp('menuC301').items.items[0].enable(true);
		Ext.getCmp('menuC301').items.items[1].enable(true);
		Ext.getCmp('menuC301').items.items[2].enable(true);
		Ext.getCmp('menuC301').items.items[3].disable(true);
		Ext.getCmp('menuC301').items.items[4].disable(true);
		
		
		Ext.getCmp('menuC301').items.items[5].disable(true);
		Ext.getCmp('menuC301').items.items[6].enable(true);
	
	},
	
	save:function(){
		saveOrCheck(10);
		commonSetMsterReadOnlyByArray(
				new Array('sourceNoC301','orderNoC301',
				'sdateC301','ownerAliasC301','ownerAddrC301',
				'ownerLinkmanC301','ownerPhoneC301','custAliasC301','custAddrC301',
				'custLinkmanC301','custPhoneC301'),true);	
		Ext.getCmp('menuC301').items.items[0].enable(true);
		Ext.getCmp('menuC301').items.items[1].enable(true);
		Ext.getCmp('menuC301').items.items[2].enable(true);
		Ext.getCmp('menuC301').items.items[3].disable(true);
		Ext.getCmp('menuC301').items.items[4].disable(true);
		
		//Ext.getCmp('menuC301').items.items[5].disable(true);
		//Ext.getCmp('menuC301').items.items[6].disable(true);
		var data = Ext.getCmp('grid_01_C301').getSelectionModel().getSelection();
		if(data[0].data.status<12)
		{
			Ext.getCmp('menuC301').items.items[5].enable(true);
		}else
		{
			Ext.getCmp('menuC301').items.items[5].disable(true);
		}
		
		if(data[0].data.status!=13)
		{
			Ext.getCmp('menuC301').items.items[6].enable(true);
		}else
		{
			Ext.getCmp('menuC301').items.items[6].disable(true);
		}
	},
	
	
	
	initializtion:function(){
		isCanEdit3101=false;
	},
	
	
	////////////////////////////////////////
	grid_02_C301beforeedit:function(editor,e,eOpts){
		if(!isCanEditC301)
	    {
			editor.cancel = true;
	        return  false;  
	    }else{
	    	var data = Ext.getCmp('grid_01_C301').getSelectionModel().getSelection();
			if(data.length!=0){
				if(data[0].data.status=='12' && e.field=='inQty')
				{
			        editor.cancel = true;
		        	return  false; 
				}
				if(data[0].data.status=='13')
				{
			        editor.cancel = true;
		        	return  false; 
				}
			}
	    }
	},
	///////////////////////////////////////////
	undo:function(){
		commonSetMsterReadOnlyByArray(
				new Array('sourceNoC301','orderNoC301',
				'sdateC301','ownerAliasC301','ownerAddrC301',
				'ownerLinkmanC301','ownerPhoneC301','custAliasC301','custAddrC301',
				'custLinkmanC301','custPhoneC301'),true);
		
		var data = Ext.getCmp('grid_01_C301').getSelectionModel().getSelection();
		if(data.length!=0){
			editExpC301(data[0].index);
		}
		
		Ext.getCmp('menuC301').items.items[0].enable(true);
		Ext.getCmp('menuC301').items.items[1].enable(true);
		Ext.getCmp('menuC301').items.items[2].enable(true);
		Ext.getCmp('menuC301').items.items[3].disable(true);
		Ext.getCmp('menuC301').items.items[4].disable(true);
		
		//Ext.getCmp('menuC301').items.items[5].disable(true);
		//Ext.getCmp('menuC301').items.items[6].disable(true);
		
		if(data[0].data.status<12)
		{
			Ext.getCmp('menuC301').items.items[5].enable(true);
		}else
		{
			Ext.getCmp('menuC301').items.items[5].disable(true);
		}
		
		if(data[0].data.status!=13)
		{
			Ext.getCmp('menuC301').items.items[6].enable(true);
		}else
		{
			Ext.getCmp('menuC301').items.items[6].disable(true);
		}
	},
	
	edit:function(){
		commonSetMsterReadOnlyByArray(
				new Array('ownerAliasC301','ownerAddrC301',
				'ownerLinkmanC301','ownerPhoneC301','custAliasC301','custAddrC301',
				'custLinkmanC301','custPhoneC301'),false);
		
		Ext.getCmp('menuC301').items.items[0].disable(true);
		Ext.getCmp('menuC301').items.items[1].disable(true);
		Ext.getCmp('menuC301').items.items[2].disable(true);
		Ext.getCmp('menuC301').items.items[3].enable(true);
		Ext.getCmp('menuC301').items.items[4].enable(true);
		Ext.getCmp('menuC301').items.items[5].disable(true);
		Ext.getCmp('menuC301').items.items[6].disable(true);
		
		isCanEditC301=true;
	},
	grid_01_C301Click:function(){
		Ext.getCmp('tabPIdC301').items.items[1].setVisible(true);
	},
	
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){		
		commonSetMsterReadOnlyByArray(
				new Array('sourceNoC301','orderNoC301',
				'sdateC301','ownerAliasC301','ownerAddrC301',
				'ownerLinkmanC301','ownerPhoneC301','custAliasC301','custAddrC301',
				'custLinkmanC301','custPhoneC301'),true);
		
		Ext.getCmp('menuC301').items.items[0].enable(true);
		Ext.getCmp('menuC301').items.items[1].enable(true);
		Ext.getCmp('menuC301').items.items[2].enable(true);
		Ext.getCmp('menuC301').items.items[3].disable(true);
		Ext.getCmp('menuC301').items.items[4].disable(true);
		Ext.getCmp('menuC301').items.items[5].disable(true);
		Ext.getCmp('menuC301').items.items[6].disable(true);
		
		
		if(newCard.itemId=='tabPIdC301i'){
			var data = Ext.getCmp('grid_01_C301').getSelectionModel().getSelection();
			if(data.length!=0){
				editExpC301(data[0].index);
			}else{
				if(Ext.getCmp('grid_01_C301').getStore().getCount()>0)
				{
					Ext.getCmp('grid_01_C301').getSelectionModel().select(0);
					editExpC301(0);
				}
			}
		}else{
			Ext.getCmp('grid_01_C301').getStore().reload();	
		}
	},
	
	userPrev:function(){
		rowindexC301=rowindexC301-1;
		Ext.getCmp('grid_01_C301').getSelectionModel().select(rowindexC301);
		editExpC301(rowindexC301);
	},
	
	userNext:function(){
		rowindexC301=rowindexC301+1;
		Ext.getCmp('grid_01_C301').getSelectionModel().select(rowindexC301);
		editExpC301(rowindexC301);
	}
	
	
});

//填充数据
function editExpC301(rowindexC301){
	var record=Ext.getCmp('grid_01_C301').getStore().getAt(rowindexC301-(Ext.getCmp('grid_01_C301').getStore().currentPage-1)*appConfig.getPageSize());
	
	if(record.data.status<12)
	{
		Ext.getCmp('menuC301').items.items[5].enable(true);
	}else
	{
		Ext.getCmp('menuC301').items.items[5].disable(true);
	}
	
	if(record.data.status!=13)
	{
		Ext.getCmp('menuC301').items.items[6].enable(true);
	}else
	{
		Ext.getCmp('menuC301').items.items[6].disable(true);
	}
	
	Ext.getCmp('sourceNoC301').setValue(record.data.sourceNo);
    Ext.getCmp('orderNoC301').setValue(record.data.orderNo);
    Ext.getCmp('sdateC301').setValue(record.data.sdateText);
    Ext.getCmp('ownerAliasC301').setValue(record.data.ownerAlias);
    Ext.getCmp('ownerAddrC301').setValue(record.data.ownerAddr);
    Ext.getCmp('ownerLinkmanC301').setValue(record.data.ownerLinkman);
    Ext.getCmp('ownerPhoneC301').setValue(record.data.ownerPhone);
    
    Ext.getCmp('custAliasC301').setValue(record.data.custAlias);
    Ext.getCmp('custAddrC301').setValue(record.data.custAddr);
    Ext.getCmp('custLinkmanC301').setValue(record.data.custLinkman);
    Ext.getCmp('custPhoneC301').setValue(record.data.custPhone);
    
	var wheresql={
		strOrderNo:record.data.orderNo
	};
	Ext.apply(Ext.getCmp('grid_02_C301').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_C301').getStore().removeAll();
	Ext.getCmp('grid_02_C301').getStore().load();		
};

function saveOrCheck(flag){	 
	var gridcount=Ext.getCmp('grid_02_C301').getStore().getCount();
	if(gridcount>0){
		if(!commonCheckdetailgrid('grid_02_C301',0,gridcount-1))
		{
			return;
		}
	}else{			
		Ext.example.msg('提示',"表体不允许为空，请输入表体！");
		return;
	}
	
	var da=Ext.getCmp('grid_01_C301').getSelectionModel().getSelection();
	var master={
	
		orderNo:Ext.getCmp('orderNoC301').getValue(), 
		ownerAlias:Ext.getCmp('ownerAliasC301').getValue(),
		ownerAddr:Ext.getCmp('ownerAddrC301').getValue(),
		ownerLinkman:Ext.getCmp('ownerLinkmanC301').getValue(),
		ownerPhone:Ext.getCmp('ownerPhoneC301').getValue(),
		
		custAlias:Ext.getCmp('custAliasC301').getValue(),
		custAddr:Ext.getCmp('custAddrC301').getValue(),
		custLinkman:Ext.getCmp('custLinkmanC301').getValue(),
		custPhone:Ext.getCmp('custPhoneC301').getValue()
	};
	var detail=[];
	for(var i=0;i<gridcount;i++){

		var orderm=Ext.getCmp('grid_02_C301').getStore().getAt(i);	
		var d={
			id:{
				orderNo:Ext.getCmp('orderNoC301').getValue(),
				articleName:orderm.get('articleName'),
				barcodeNo:orderm.get('barcodeNo')
			},
			orderQty:orderm.get('orderQty'),
			orderVl:orderm.get('orderVl'),
			orderWt:orderm.get('orderWt'),
			inQty:orderm.get('inQty'),
			inWt:orderm.get('inWt'),
			inVl:orderm.get('inVl'),
			
			signQty:orderm.get('signQty'),
			signWt:orderm.get('signWt'),
			signVl:orderm.get('signVl'),
			lostQty:orderm.get('lostQty')==null?0:orderm.get('lostQty'),
			lostWt:orderm.get('lostWt')==null?0:orderm.get('lostWt'),
			lostVl:orderm.get('lostVl')==null?0:orderm.get('lostVl'),
			remark:orderm.get('remark')
			
		};
		detail.push(d);
	};
	
	var params={
		orderM:Ext.encode(master),
		orderD:Ext.encode(detail),
		flag:flag
	};
	
	Ext.Ajax.request({
	method:'post',
	url:'acdata_OrderAction_saveOrUpdate',
	params:params,
	success:function(response){
		var data = Ext.decode(response.responseText);
		
		if(data.isSucc){
			if(data.obj=='10'){
				Ext.example.msg('提示',"保存成功！");
			}else if(data.obj=='12'){
				Ext.example.msg('提示',"接单确认成功！");
			}else if(data.obj=='13'){
				Ext.example.msg('提示',"回单确认成功！");
			}
			
			Ext.getCmp('grid_02_C301').getStore().removeAll();
			Ext.getCmp('grid_02_C301').getStore().load();	
		}
	}});	
}

