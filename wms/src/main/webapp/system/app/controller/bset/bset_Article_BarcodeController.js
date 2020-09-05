/**
 * 模块名称：条码信息采集
 * 模块编码：1O01
 * 创建：lich 
 */
var rowindex1O01=0;
var isCanEdit1O01=false;


Ext.define('cms.controller.bset.bset_Article_BarcodeController', {
	extend : 'Ext.app.Controller',
	id:'bset.bset_Article_BarcodeController',
	requires : [ 'cms.view.bset.bset_Article_BarcodeUI'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({
			//新单
			'bset_Article_BarcodeUI button[name=userAddButton]':{	
				click:this.newAdd
			},//保存
			'bset_Article_BarcodeUI button[name=userSaveButton]':{	
				click:this.save
			},//明细新增			
			'bset_Article_BarcodeUI button[name=detailAdd]':{
				click:this.detailAdd
			},//明细删除			
			'bset_Article_BarcodeUI button[name=detailDelete]':{
				click:this.detailDelete
			},//上一条记录
			'bset_Article_BarcodeUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'bset_Article_BarcodeUI button[name=userNextButton]':{
				click:this.userNext
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_no1O01]':{
				beforequery:this.article_no1O01BeforeQuery,
				select:this.article_noselect
			},//单据列表 双击
			'bset_Article_BarcodeUI grid[id=grid_01_1O01]':{
				itemdblclick:this.grid_01_1O01Click			
			},
			'bset_Article_BarcodeUI grid[id=grid_02_1O01]':{
				beforeedit:this.grid_02_1O01beforeedit,
				edit:this.grid_02_1O01edit
			},
			'bset_Article_BarcodeUI tabpanel':{
				tabchange:this.tabPidchange
			}
		});
	},
	newAdd:function(){		
		Ext.getCmp('form_01_1O01').getForm().reset();
		Ext.getCmp('serialNo1O01').setValue('保存时自动生成');		
		Ext.getCmp('grid_02_1O01').getStore().removeAll();
		if(Ext.getCmp('owner_no1O01').getStore().data.length>0)
		{
			Ext.getCmp('owner_no1O01').setValue(Ext.getCmp('owner_no1O01').getStore().getAt(0).data.value);		
		}
		Ext.get('rgstName1O01').dom.innerHTML=Ext.get('workerNo').getValue();
		Ext.get('rgstDate1O01').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
		Ext.get('updtName1O01').dom.innerHTML='';
		Ext.get('updtDate1O01').dom.innerHTML='';
		commonSetMsterReadOnlyByArray(
		new Array('task_no1O01',
				  'owner_no1O01'),false);
		commonEditButton('menu1O01','add');
		Ext.getCmp('toolbar1O01').items.items[0].enable(true);
		Ext.getCmp('toolbar1O01').items.items[1].enable(true);			
		Ext.getCmp('task_no1O01').focus();
		isCanEdit1O01=true;
	},
	save:function(){
		var grid=Ext.getCmp('grid_02_1O01');
		var gridcount=Ext.getCmp('grid_02_1O01').getStore().getCount();
		if(gridcount>0){
			if(!commonCheckdetailgrid('grid_02_1O01',0,gridcount-1))
			{
				return;
			}
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		var paperNo=Ext.getCmp('task_no1O01').getValue();
		var serialNo=Ext.getCmp('serialNo1O01').getValue();
		var ownerNo=Ext.getCmp('owner_no1O01').getValue();				
		var master={
			//id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:ownerNo,
				paperNo:paperNo,
				serialNo:1,			
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				updtName:Ext.get('workerNo').getValue(),
				updtDate:new Date()
		};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('grid_02_1O01').getStore().getAt(i);
			var d={
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					serialNo:1,
					articleNo:record.get('articleNo'),
					status:'10'
			};
			detail.push(d);
		};
			
		disableButtonFunc(1,'#menu1O01 [name=userSaveButton]','保存');
		var M=Ext.encode(master);
		var D=Ext.encode(detail);
		
		var params={
			jsonMaster:M,
			jsonDetail:D
		};
		Ext.Ajax.request({
			method:'post',
			url:'bset_Article_BarcodeAction_save',
			params:params,
			success:function(response){
				debugger;
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('serialNo1O01').setValue(data.obj);					
					commonEditButton('menu1O01','save');
					isCanEdit1O01=false;
					Ext.getCmp('toolbar1O01').items.items[0].disable(true);
					Ext.getCmp('toolbar1O01').items.items[1].disable(true);
				}else{
					Ext.example.msg('提示',data.msg);
					disableButtonFunc(0,'#menu1O01 [name=userSaveButton]','保存');
				}
			}
		});
	},	
	detailAdd:function(){		
		var store = Ext.getCmp('grid_02_1O01').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
		}else{
			if(!commonCheckIsInputAll('form_01_1O01')){
				return;
			}
			commonSetMsterReadOnlyByArray(
			           new Array('task_no1O01',
			          'owner_no1O01'),
			          true);
		}
		var r=Ext.create('cms.model.bset.bset_Article_Barcode_DModel',{});	
		store.add(r);
		Ext.getCmp('grid_02_1O01').editingPlugin.startEdit(count,1);
	},
	detailDelete:function(){
		var data = Ext.getCmp('grid_02_1O01').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm('提示','确定删除数据',function(button,text){
			if(button=='yes'){
				Ext.getCmp('grid_02_1O01').getStore().remove(data);					
				if(Ext.getCmp("grid_02_1O01").getStore().getCount()==0)
				{
					  commonSetMsterReadOnlyByArray(
			           new Array('task_no1O01',
			          'owner_no1O01'),
			          false);
				};
			}
			});
		}else{
			Ext.example.msg('提示', '请先选择您要删除的行');
			return;
		}
	},
	userPrev:function(){
		rowindex1O01=rowindex1O01-1;
		editExp1O01(rowindex1O01);
	},
	
	userNext:function(){
		rowindex1O01=rowindex1O01+1;
		editExp1O01(rowindex1O01);
	},	
    //商品加载前
	article_no1O01BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_no1O01").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_no1O01').getStore().proxy.extraParams,strWheresql);
	},	
	article_noselect:function(combo,records){		
		var data = Ext.getCmp('grid_02_1O01').getSelectionModel().getSelection();
		data[0].set('articleName',records[0].data.text);		    
	},
	grid_01_1O01Click:function(th, record,  item,  index, e, eOpts ){
		isCanEdit1O01=false;
		commonEditButton('menu1O01','dbclick');
		Ext.ComponentQuery.query('bset_Article_BarcodeUI tabpanel')[0].items.items[1].setVisible(true);
		Ext.getCmp('toolbar1O01').items.items[0].disable(true);
		Ext.getCmp('toolbar1O01').items.items[1].disable(true);
	},
	grid_02_1O01beforeedit:function(e){
		if(!isCanEdit1O01)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	grid_02_1O01edit:function(editor,e,eOpts){
		if(e.field=='articleNo'){
			if(!Ext.isEmpty(e.value)){
				if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('articleNo')==editor.context.record.data.articleNo;
					})!=-1)				
				{
					editor.context.record.set('articleNo',editor.context.originalValue);
					Ext.example.msg('提示', "【商品编码】不能重复，请重新输入！");
				}
			}
		}
	},
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		Ext.getCmp('owner_no1O01').getStore().load();
		if(newCard.title=='单据明细'){
			var data = Ext.getCmp('grid_01_1O01').getSelectionModel().getSelection();
			if(data.length!=0){
				editExp1O01(data[0].index);
				rowindex1O01=data[0].index;				
				isCanEdit1O01=false;
				commonEditButton('menu1O01','dbclick');
				Ext.getCmp('toolbar1O01').items.items[0].disable(true);
				Ext.getCmp('toolbar1O01').items.items[1].disable(true);
			}
		}
	}
});

//编辑数据
function editExp1O01(rowindex1O01){
	if(rowindex1O01==0)
	{
		Ext.getCmp('menu1O01').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu1O01').items.items[0].enable(true);
	}
	if(rowindex1O01==Ext.getCmp('grid_01_1O01').getStore().getCount()-1)
	{		
		Ext.getCmp('menu1O01').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu1O01').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_1O01').getStore().getAt(rowindex1O01-(Ext.getCmp('grid_01_1O01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('serialNo1O01').setValue(record.data.serialNo);
	Ext.getCmp('task_no1O01').setValue(record.data.paperNo);
	Ext.getCmp('owner_no1O01').setValue(record.data.ownerNo);
  
	commonSetMsterReadOnlyByArray(
			new Array('serialNo1O01','task_no1O01',
			'owner_no1O01')
			,true);
	var wheresql={
		wheresql:record.data.serialNo
	};
	Ext.apply(Ext.getCmp('grid_02_1O01').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_1O01').getStore().removeAll();
	Ext.getCmp('grid_02_1O01').getStore().load();
};