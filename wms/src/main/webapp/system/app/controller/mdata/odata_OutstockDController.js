/**
 * 模块名称：移库管理——>移库回单
 * 模块编码：5301
 * zhouhuan
 */
 var isCanEdit5301=false;
 Ext.define('cms.controller.mdata.odata_OutstockDController',{
 	extend:'Ext.app.Controller',
 	requires:[
 			'cms.view.mdata.odata_OutstockDUI'
 	         ],
 	model:'cms.model.mdata.odata_OutstockDModel',
 	store:'',
 	init:function(){
 		 this.control({
 		 	//移库回单》修改
			'odata_OutstockDUI button[name=edit]':{
				click:this.editClick
			},//移库回单》撤销
			'odata_OutstockDUI button[name=undo]':{
				click:this.undoClick
			},//移库回单》刷新
			'odata_OutstockDUI button[name=refresh]':{
				click:this.refreshClick
			},//移库回单》查找
			'odata_OutstockDUI button[name=query]':{
				click:this.queryClick
			},//移库回单》查询
	    	'odata_OutstockDUI button[id=butQuery5301]':{
	    		click:this.butQuery5301
	    	},//移库回单》查询
	    	'odata_OutstockDUI button[id=butClear5301]':{
	    		click:this.butClear5301
	    	},//移库回单》单据选择
			'odata_OutstockDUI grid[id=gridOdata_OutstockM5301]':{
				beforeselect:this.gridOdata_OutstockM5301Beforeselect,
				selectionchange:this.gridOdata_OutstockM5301selectionchange
			},//移库回单》商品数量较验等
			'odata_OutstockDUI grid[id=gridOdata_OutstockD5301]':{
				beforeedit:this.gridOdata_OutstockD5301beforeedit,
				edit:this.gridOdata_OutstockD5301edit
			},//移库回单》回单
			'odata_OutstockDUI button[id=butReceipt5301]':{
				click:this.butReceipt5301click
			}
	    });
	    bindEnterSkip($('#formMan5301'));//调用键盘处理方法
 	},
 	
 	initializtion:function(){
 		
 		//显示变量0为不显示，1为显示
		var planBox_5301=commonGetModuleField('5301','planBox')[0].flag;	//计划
		var planQmin_5301=commonGetModuleField('5301','planQmin')[0].flag;
		var planDis_5301=commonGetModuleField('5301','planDis')[0].flag;
		var packingUnit_5301=commonGetModuleField('5301','packingUnit')[0].flag;
		var packingSpec_5301=commonGetModuleField('5301','packingSpec')[0].flag;
		
		var realBox_5301=commonGetModuleField('5301','realBox')[0].flag;	//验收
		var realQmin_5301=commonGetModuleField('5301','realQmin')[0].flag;
		var realDis_5301=commonGetModuleField('5301','realDis')[0].flag;
		
		if(planBox_5301==0){
			Ext.getCmp('planBox_5301').setVisible(false);
		}
		if(planQmin_5301==0){
			Ext.getCmp('planQmin_5301').setVisible(false);
		}
		if(planDis_5301==0){
			Ext.getCmp('planDis_5301').setVisible(false);
		}
		if(packingUnit_5301==0){
			Ext.getCmp('packingUnit_5301').setVisible(false);
		}
		if(packingSpec_5301==0){
			Ext.getCmp('packingSpec_5301').setVisible(false);
		}
		
		if(realBox_5301==0){
			Ext.getCmp('realBox_5301').setVisible(false);
		}
		if(realQmin_5301==0){
			Ext.getCmp('realQmin_5301').setVisible(false);
		}
		if(realDis_5301==0){
			Ext.getCmp('realDis_5301').setVisible(false);
		}
 		
 	},
 	
 	
 	editClick:function(){
		var record = Ext.getCmp('gridOdata_OutstockM5301').getSelectionModel().getSelection();
        if(record.length != 0){
    		commonMenu4Button('menu5301','edit');
			isCanEdit5301=true;
			Ext.getCmp('cmbReal_outstockworker5301').focus();
			commonSetMsterReadOnlyByArray(
			new Array('cmbInstock_name5301','cmbReal_outstockworker5301'),
			false);
			Ext.getCmp('butReceipt5301').setDisabled(false);
        }
		
	},
	undoClick:function(){
		commonMenu4Button('menu5301','undo');
		Ext.getCmp('butReceipt5301').setDisabled(true);
		Ext.getCmp('cmbReal_outstockworker5301').setValue("");
		Ext.getCmp('cmbInstock_name5301').setValue("");
		Ext.getCmp('gridOdata_OutstockD5301').getStore().load();
		commonSetMsterReadOnlyByArray(
		new Array('cmbInstock_name5301','cmbReal_outstockworker5301'),
		true);
		isCanEdit5301=false;
	},
	//刷新
	refreshClick:function(){
		Ext.getCmp('gridOdata_OutstockM5301').getStore().reload();
	},
	//查询
	queryClick:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=5301;
		queryGrid='gridOdata_OutstockM5301';	
	},	
 	/*
 	 * 查询移库回单单头
 	 */
	butQuery5301:function(){		
	    var detail1 = [];
		if(!Ext.isEmpty(Ext.getCmp("txtOutstock_no5301").getValue())){
			var d={
				columnId:'b.outstock_no',
				value:Ext.getCmp("txtOutstock_no5301").getValue()
			};
			detail1.push(d);
		}
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			strQuery : jsonStr
		};
	
		Ext.apply(Ext.getCmp('gridOdata_OutstockM5301').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridOdata_OutstockM5301').getStore().removeAll();
		Ext.getCmp('gridOdata_OutstockM5301').getStore().load();
	},
	
	butClear5301:function(){
		Ext.getCmp('txtOutstock_no5301').setValue('');
	},
	gridOdata_OutstockM5301Beforeselect:function(){
	    if(isCanEdit5301)
	    {
	        return  false;  
	    }
	},
	gridOdata_OutstockM5301selectionchange:function(){
		var record = Ext.getCmp('gridOdata_OutstockM5301').getSelectionModel().getSelection();
		var store = Ext.getCmp('gridOdata_OutstockD5301').getStore();
		var count = store.getCount();
		if(record.length == 0){
			Ext.getCmp('gridOdata_OutstockD5301').getStore().removeAll();
		}else{
		    var detail1 = [];
			var d={
				columnId:'ood.outstock_no',
				value:record[0].get("outstockNo")
			};
			detail1.push(d);
			var jsonStr = Ext.encode(detail1);
			var wheresql = {
				str : jsonStr
			};
			
			Ext.apply(Ext.getCmp('gridOdata_OutstockD5301')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOdata_OutstockD5301').getStore()
					.removeAll();
			Ext.getCmp('gridOdata_OutstockD5301').getStore()
						.load();
			
		    Ext.get('Editor5301').dom.innerHTML=record[0].data.rgstName;
			Ext.get('EditDate5301').dom.innerHTML=record[0].data.rgstDate;
			Ext.get('Checker5301').dom.innerHTML=record[0].data.updtName;
			Ext.get('Checkdate5301').dom.innerHTML=record[0].data.updtDate;
		    }
		    Ext.getCmp('gridOdata_OutstockD5301').editingPlugin.startEdit(0,8);
	},
	gridOdata_OutstockD5301beforeedit:function(editor,e,eOpts){
		//实际箱数和实际零散数默认为计划数量，出货量补货不允许修改数量回单，人工移库和安全量补货回单数量可小于等于计划数量
	 	if(!isCanEdit5301)
	    {
	        editor.cancel = true;
	        return  false;  
	    }else{
			var record = Ext.getCmp('gridOdata_OutstockM5301').getSelectionModel().getSelection();
	        if(record[0].data.outstockType=='1'){
		        editor.cancel = true;
		        return  false; 
	        }
	    }
	},
	//移库回单》商品数量较验等
	gridOdata_OutstockD5301edit:function(editor,e,eOpts){
		if(e.field=='realBox' || e.field=='realQty' || e.field=='realQmin' || e.field=='realDis'){
			if(e.record.data.realQty>e.record.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,"总数量不能大于计划数量！");
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='realBox'){
					editor.context.record.set('realQty',editor.context.originalValue*e.record.data.packingQty+e.record.data.realQmin*e.record.data.qminOperatePacking+e.record.data.realDis);
				}if(e.field=='realQmin'){
					editor.context.record.set('realQty',e.record.data.realBox*e.record.data.packingQty+editor.context.originalValue*e.record.data.qminOperatePacking+e.record.data.realDis);
				}if(e.field=='realDis'){
					editor.context.record.set('realQty',e.record.data.realBox*e.record.data.packingQty+e.record.data.realQmin*e.record.data.qminOperatePacking+editor.context.originalValue);
				}else if(e.field=='realQty'){
					editor.context.record.set('realBox',(editor.context.originalValue/e.record.data.packingQty));
				}
			}
		}
		
		if(e.field=='checkBox' || e.field=='checkDis' || e.field=='checkQty'){
			if(!checkqty(e.record.data.articleNo)){
				//Ext.example.msg($i18n.prompt,"总数量不能大于计划数量！");
				//Ext.example.msg($i18n.prompt,"总数量不能大于计划数量！");
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='checkBox'){
					editor.context.record.set('checkQty',editor.context.originalValue*e.record.data.packingQty+e.record.data.checkDis);
				}else if(e.field=='checkDis'){
					editor.context.record.set('checkQty',editor.context.originalValue+e.record.data.packingQty*e.record.data.checkBox);
				}else if(e.field=='checkQty'){
					editor.context.record.set('checkBox',parseInt(editor.context.originalValue/e.record.data.packingQty));
					editor.context.record.set('checkDis',editor.context.originalValue%e.record.data.packingQty);
				}
			}
		}
	},
	butReceipt5301click:function(){
		var outstock_name= Ext.getCmp('cmbReal_outstockworker5301').getValue();
		var instock_name= Ext.getCmp('cmbInstock_name5301').getValue();
		if(Ext.isEmpty(outstock_name))
		{
			Ext.example.msg($i18n.prompt,"请输入实际下架人员！");
			return;
		}
		if(Ext.isEmpty(instock_name))
		{
			Ext.example.msg($i18n.prompt,"请输入上架人员！");
			return;
		}
		var gridcount=Ext.getCmp("gridOdata_OutstockD5301").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('gridOdata_OutstockD5301',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg($i18n.prompt,"表体不允许为空，请输入表体！");
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,"请设置工作站！");
			return;
		}
		Ext.Msg.confirm($i18n.prompt, "确定回单？",
		function(button, text) {
		if (button == 'yes') {
			var msgShow = commonMegShow("正在回单,请稍等...");
			var detail1 = [];
			var objOustockM = Ext.getCmp('gridOdata_OutstockM5301').getSelectionModel().getSelection();
			var taskType = objOustockM[0].get("taskType");
			for(var i=0;i<gridcount;i++ ){
				var record  = Ext.getCmp('gridOdata_OutstockD5301').getStore().getAt(i);
				var d={
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						outstockNo:record.get('outstockNo'),
						warehouseNo:record.get('warehouseNo'),
						ownerNo:record.get('ownerNo'),
						articleNo:record.get('articleNo'),
						barcode:record.get('barcode'),
						SCellNo:record.get('SCellNo'),
						DCellNo:record.get('DCellNo'),
						articleQty:record.get('planBox')* record.get('packingQty'),
						realQty:record.get('realBox')* record.get('packingQty')
						+record.get('realQmin')* record.get('qminOperatePacking')
						+record.get('realDis'),
						quality:record.get('quality'),
						outstockName:outstock_name,
						instockName:instock_name,
						userId:Ext.get('workerNo').getValue(),
						produceDate:record.get('produceDate'),
						expireDate:record.get('expireDate'),
						lotNo : record.get('lotNo'),
						rsv_batch1 : record.get('rsv_batch1'),
						rsv_batch2 : record.get('rsv_batch2'),
					    rsv_batch3 : record.get('rsv_batch3'),
						rsv_batch4 : record.get('rsv_batch4'),
						rsv_batch5 : record.get('rsv_batch5'),
						rsv_batch6 : record.get('rsv_batch6'),
						rsv_batch7 : record.get('rsv_batch7'),
						rsv_batch8 : record.get('rsv_batch8'),
						labelNo : record.get('labelNo'),
						packingQty : record.get('packingQty'),
						dockNo : workSpaceNo
				};
				detail1.push(d);
			}
			var jsonStr = Ext.encode(detail1);		
			var params = {
					str:jsonStr,
					strTaskType:taskType
			};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_OutstockDAction_save.action',
				params:params,
				success:function(response){
					msgShow.hide();
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						commonMenu4Button('menu5301','save');
						Ext.example.msg($i18n.prompt,data.msg);
						commonSetMsterReadOnlyByArray(
						new Array('cmbInstock_name5301','cmbReal_outstockworker5301'),
						true);
						isCanEdit5301=false;
						Ext.getCmp('gridOdata_OutstockM5301').store.reload();
						Ext.getCmp('butReceipt5301').setDisabled(true);
						//Ext.getCmp('gridOdata_OutstockD5301').store.reload();
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}				
				},
				failure:function(response){
					msgShow.hide();
					Ext.example.msg($i18n.prompt,"提交不上,可能是网络问题");
				}
			});
		}
		});
	}
 	});
