/**
 * 模块名称：暂存区维护UI
 * 模块编码：2701
 * 创建：panzhenxing
 */
rowindex2701=0;
type2701='';
type2701_2='';
var checkFlag='0';
var flagtype='';
Ext.define('cms.controller.cset.cset_BufferController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cset.cset_BufferAreaUI'
	          ],
	init:function(){
		this.control({//新增
			'cset_BufferAreaUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'cset_BufferAreaUI button[name=detailEdit]':{
				click:this.detailEdit
			},//删除
			'cset_BufferAreaUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			//暂存区维护》浏览
			'cset_BufferAreaUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//关闭窗口
			'cset_BufferAreaAddOrEditWindow button[name=close]':{
				click:this.colse
			},//上一条记录
			'cset_BufferAreaAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'cset_BufferAreaAddOrEditWindow button[name=next]':{
				click:this.next
			},//选择库区（UI）
			'cset_BufferAreaUI cdef_DefWareCombo[id=d4_wareno2701]':{
				select:this.d4_wareno2701change
			},//选择储区（UI）
			'cset_BufferAreaUI cdef_DefAreaCombo[id=d4_areano2701]':{
				select:this.d4_areano2701change
			},//选择通道（UI）
			'cset_BufferAreaUI cdef_DefStockCombo[id=d4_stock2701]':{
				select:this.d4_stock2701change
			},//暂存区维护-》新增界面-》保存
			'cset_BufferAreaAddOrEditWindow button[name=save]':{
				click:this.save
			},//仓库维护》仓库》新增
			'cset_BufferAreaAddOrEditWindow button[name=add]':{
				click:this.add
			},
			//选择库区（window）
			'cset_BufferAreaAddOrEditWindow cdef_DefWareCombo[id=d4_wareno2701_1]':{
				select:this.d4_wareno2701_1change
			},//选择储区（window）
			'cset_BufferAreaAddOrEditWindow cdef_DefAreaCombo[id=d4_areano2701_1]':{
				select:this.d4_areano2701_1change
			},//储位选择事件
			'cset_BufferAreaAddOrEditWindow cdef_DefCellCombo[id=cell_no2701]':{
				beforequery : this.cell_no2701beforequery,
				select : this.cellCheck
			},
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('d4_wareno2701').setValue(null);
		Ext.getCmp('d4_wareno2701').getStore().load();
	},
	//调用新增页面
	detailAdd:function(){
		 flagtype = "add";
		Ext.create('cms.view.cset.window.cset_BufferAreaAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('menuWidget2701');
		
	},
	//判断暂存区货位是否唯一
	cellCheck: function(){
		if(flagtype=='add'){			
			Ext.Ajax.request({
				url : 'cset_BufferAction_cellCheck',
				params : {
					strwareNo : Ext.getCmp('d4_wareno2701_1').getValue(),
					strareaNo : Ext.getCmp('d4_areano2701_1').getValue(),
					strstockNo : Ext.getCmp('d4_stockno2701_1').getValue(),
					strQuery:Ext.getCmp('cell_no2701').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
						Ext.example.msg($i18n.prompt,$i18n_prompt.cellBufferExist);
			    		Ext.getCmp('cell_no2701').setValue(null);
			    	}
				}
			});
		}
	},
	
	/**
	 * 库区选择事件（UI）
	 */
	d4_wareno2701change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_no',
			value:combo.value
		};
		detail1.push(d);
		var d={
				columnId:'a.area_no',
				value:Ext.getCmp('d4_areano2701').getValue()
			};
			detail1.push(d);
			var d={
				columnId:'a.stock_no',
				value:Ext.getCmp('d4_stock2701').getValue()
			};
			detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var detail12 = [];
		var d={
			columnId:'a.ware_no',
			value:combo.value
		};
		detail12.push(d);
		var jsonDetail12 = Ext.encode(detail12);
		var wheresql = {
				strQuery : jsonDetail1
		};
		var wheresql2 = {
				str : jsonDetail12
			};
		//xiala area
		Ext.apply(Ext.getCmp('d4_areano2701').getStore().proxy.extraParams,wheresql2);
		Ext.getCmp('d4_areano2701').getStore().removeAll();
		Ext.getCmp('d4_areano2701').getStore().load();	
		
		//biaoge
		Ext.apply(Ext.getCmp('costOtherList2701').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('costOtherList2701').getStore().removeAll();
		Ext.getCmp('costOtherList2701').getStore().load();	
		
		
	},

	/**
	 * 储区下拉选择事件(UI)
	 */
	d4_areano2701change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_no',
			value:Ext.getCmp('d4_wareno2701').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.area_no',
			value:combo.value
		};
		detail1.push(d);
		
		var jsonDetail1 = Ext.encode(detail1);
		
		var d={
				columnId:'a.stock_no',
				value:Ext.getCmp('d4_stock2701').getValue()
			};
			detail1.push(d);
			var jsonDetail12 = Ext.encode(detail1);
		var wheresql = {
			strQuery : jsonDetail12
		};
		var wheresql2 = {
				str : jsonDetail1
			};
		//xiala
		Ext.apply(Ext.getCmp('d4_stock2701').getStore().proxy.extraParams,wheresql2);
		Ext.getCmp('d4_stock2701').getStore().removeAll();
		Ext.getCmp('d4_stock2701').getStore().load();
		//grid
		Ext.apply(Ext.getCmp('costOtherList2701').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('costOtherList2701').getStore().removeAll();
		Ext.getCmp('costOtherList2701').getStore().load();
		
	},
	//通道选择事件(UI)
	d4_stock2701change:function(combo){
		
		selectCellNo2701="N";
		var detail = [];
		var d={
			columnId:'a.ware_no',
			value:Ext.getCmp('d4_wareno2701').getValue()
		};
		detail.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('d4_areano2701').getValue()
		};
		detail.push(d);
		var d={
				columnId:'a.stock_no',
				value:combo.value
		};
		detail.push(d);
				
		var wheresql = {
				strQuery : Ext.encode(detail)
		};
		
		//biaoge
		Ext.apply(Ext.getCmp('costOtherList2701').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('costOtherList2701').getStore().removeAll();
		Ext.getCmp('costOtherList2701').getStore().load();		
	},
	/**
	 * 库区选择事件（window）
	 */
	d4_wareno2701_1change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_no',
			value:combo.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('d4_areano2701_1').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d4_areano2701_1').getStore().removeAll();
		Ext.getCmp('d4_areano2701_1').getStore().load();
		
	},

	/**
	 * 储区下拉选择事件(window)
	 */
	d4_areano2701_1change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_no',
			value:Ext.getCmp('d4_wareno2701_1').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.area_no',
			value:combo.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('d4_stockno2701_1').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('d4_stockno2701_1').getStore().removeAll();
			Ext.getCmp('d4_stockno2701_1').getStore().load();
			
	},

	//新增-》储位前加载
	cell_no2701beforequery:function(queryEvent,eOpts){
		var listDetail1 = [];
		
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('d4_wareno2701_1').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('d4_areano2701_1').getValue()
		};
		listDetail1.push(d);
		var d={
			columnId:'a.stock_no',
			value:Ext.getCmp('d4_stockno2701_1').getValue()
		};
		listDetail1.push(d);
		var strJson = Ext.encode(listDetail1);
		var wheresql = {
			str : strJson,
			strQuery:Ext.getCmp("cell_no2701").getValue()
		};
		
		Ext.apply(Ext.getCmp('cell_no2701').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cell_no2701').getStore().removeAll();
		Ext.getCmp('cell_no2701').getStore().load();
	},
	
	/**
	 * 保存并关闭
	 */
	save:function(th){
        {
			saveCset_BufferWare(th);
		}
	},
	
	
	
	//修改暂存区维护信息
	detailEdit:function(){
		var data = Ext.getCmp('costOtherList2701').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cset.window.cset_BufferAreaAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			type2701='edit';
			rowindex2701=data[0].index;
			loadWare(rowindex2701);
			updateCommMenu5('menuWidget2701');
			commonSetMsterReadOnlyByArray(
					new Array('d4_wareno2701_1','d4_areano2701_1','d4_stockno2701_1','cell_no2701','status2701'),true);
			
		}
	},
	//浏览暂存区维护页面
	detailBrowse:function(){
		var data = Ext.getCmp('costOtherList2701').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cset.window.cset_BufferAreaAddOrEditWindow',{
				title:$i18n.titlebrowse
		    }).show();
			type2701='browse';
			rowindex2701=data[0].index;
			loadWare(rowindex2701);
			browseCommMenu5('menuWidget2701');
			commonSetMsterReadOnlyByArray(
					new Array('d4_wareno2701_1','d4_areano2701_1','d4_stockno2701_1','cell_no2701','bufferName2701',
							'useWeight2701','useBoxnum2701','useVolumn2701','status2701'),true);
			
		}
		}
	,
	//删除暂存区维护信息
	detailDelete:function(){
		var cust=Ext.getCmp('costOtherList2701').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var strDetail1 = [];
					var d3={
							columnId:'a.ware_no',
							value:cust[0].data.wareNo
						};
					strDetail1.push(d3);
					var d4={
							columnId:'a.area_no',
							value:cust[0].data.areaNo
						};
					strDetail1.push(d4);	
					var d5={
							columnId:'a.stock_no',
							value:cust[0].data.stockNo
						};
					strDetail1.push(d5);	
					var d6={
							columnId:'a.cell_no',
							value:cust[0].data.cellNo
						};
					strDetail1.push(d6);
					var jsonDetail = Ext.encode(strDetail1);
					var data = Ext.getCmp('costOtherList2701').getSelectionModel().getSelection();
					rowindex2701=data[0].index;
					var record=Ext.getCmp('costOtherList2701').getStore().getAt(rowindex2701);
					
					var params = {
							str  : jsonDetail,
							strwareNo : record.data.wareNo,
							strareaNo : record.data.areaNo,
							strstockNo : record.data.stockNo,
							strcellNo : record.data.cellNo
					};
					Ext.Ajax.request({
						method:'post',
						url:'cset_BufferAction_deleteBuffer',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								var detail1 = [];
								var d={
									columnId:'a.ware_no',
									value:Ext.getCmp('d4_wareno2701').getValue()
								};
								detail1.push(d);
								var d={
										columnId:'a.area_no',
										value:Ext.getCmp('d4_areano2701').getValue()
									};
									detail1.push(d);
									
									var d={
											columnId:'a.stock_no',
											value:Ext.getCmp('d4_stock2701').getValue()
										};
										detail1.push(d);
								var jsonDetail1 = Ext.encode(detail1);
								var wheresql = {
										strQuery : jsonDetail1
								};
								Ext.apply(Ext.getCmp('costOtherList2701').getStore().proxy.extraParams,wheresql);
								Ext.getCmp('costOtherList2701').getStore().removeAll();
								Ext.getCmp('costOtherList2701').getStore().load();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}
						}
					});
				}
				if (button == 'no') {
					return;
				}
			});   
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);
            return;
		}
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('cset_BufferAreaAddOrEditWindow').close();
		var detail1 = [];
		var d={
			columnId:'a.ware_no',
			value:Ext.getCmp('d4_wareno2701').getValue()
		};
		detail1.push(d);
		var d={
				columnId:'a.area_no',
				value:Ext.getCmp('d4_areano2701').getValue()
			};
			detail1.push(d);
			
			var d={
					columnId:'a.stock_no',
					value:Ext.getCmp('d4_stock2701').getValue()
				};
				detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
				strQuery : jsonDetail1
		};
		Ext.apply(Ext.getCmp('costOtherList2701').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('costOtherList2701').getStore().removeAll();
		Ext.getCmp('costOtherList2701').getStore().load();
	},
	
	/**======================暂存区维护修改和浏览===============================
	 * 上一条记录
	 */
	prev:function(th){
		rowindex2701=rowindex2701-1;
		loadWare(rowindex2701);
		commonSetCommMenu5PrevOrNext('menuWidget52701','costOtherList2701',rowindex2701);
	},
	/**
	 * 下一条记录
	 */
	next:function(th){
		rowindex2701=rowindex2701+1;
		loadWare(rowindex2701);
		commonSetCommMenu5PrevOrNext('menuWidget52701','costOtherList2701',rowindex2701);
	},
	
	/**
	 * 保存并新增
	 */
	add:function(th){
		Ext.getCmp('cset_BufferAreaAddOrEditForm').getForm().reset();
	},
});
/**
 * 保存暂存区
 * panzx
 */
function saveCset_BufferWare(th){ 
	if(!commonCheckIsInputAll('cset_BufferAreaAddOrEditForm')){
		return;
	}
	if(Ext.getCmp('cset_BufferAreaAddOrEditForm').getForm().isValid()){	
		var str={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),//no
				wareNo:Ext.getCmp('d4_wareno2701_1').getValue(),//jiemian id
				areaNo:Ext.getCmp('d4_areano2701_1').getValue(),
				stockNo:Ext.getCmp('d4_stockno2701_1').getValue(),
				bufferName:Ext.getCmp('bufferName2701').getValue(),
				cellNo:Ext.getCmp('cell_no2701').getValue()
				
			},
			status:Ext.getCmp('status2701').getValue(),
			useVolumn:Ext.getCmp('useVolumn2701').getValue(),
			useWeight:Ext.getCmp('useWeight2701').getValue(),
			useBoxnum:Ext.getCmp('useBoxnum2701').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:Ext.Date.format(new Date,'Y-m-d h:m:s')
		};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url:'cset_BufferAction_saveBuffer_Ware.action',
			
			params : {
				str:jsonStr,
				strwareNo : Ext.getCmp('d4_wareno2701_1').getValue(),
				strareaNo : Ext.getCmp('d4_areano2701_1').getValue(),
				strstockNo : Ext.getCmp('d4_stockno2701_1').getValue(),
				strcellNo : Ext.getCmp('cell_no2701').getValue(),
				strbufferName : Ext.getCmp('bufferName2701').getValue(),
				strstatus: Ext.getCmp('status2701').getValue()
				
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}

	
};

/**
 * 填充暂存区数据
 */
function loadWare(rowindex2701){
	var record=Ext.getCmp('costOtherList2701').getStore().getAt(rowindex2701);	
	Ext.getCmp('d4_wareno2701_1').setValue(record.data.wareNo);
	Ext.getCmp('d4_areano2701_1').setValue(record.data.areaNo);
	Ext.getCmp('d4_stockno2701_1').setValue(record.data.stockNo);
	Ext.getCmp('bufferName2701').setValue(record.data.bufferName);
	Ext.getCmp('cell_no2701').getStore().removeAll();
	Ext.getCmp('cell_no2701').getStore().add({
    	value:record.data.cellNo,
    	dropValue:record.data.cellNo,
    	text:record.data.cellNo
    });
	Ext.getCmp('cell_no2701').setValue(record.data.cellNo);
	Ext.getCmp('status2701').setValue(record.data.status);
	Ext.getCmp('useVolumn2701').setValue(record.data.useVolumn);
	Ext.getCmp('useWeight2701').setValue(record.data.useWeight);
	Ext.getCmp('useBoxnum2701').setValue(record.data.useBoxnum);	
}

