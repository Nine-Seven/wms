/**
 * 模块名称：移库手键单
 * 模块编码：5101
 * zhouhuan
 */
 var rowindex5101=0;
 
 Ext.define('cms.controller.mdata.mdata_PlanMController',{
 	extend:'Ext.app.Controller',
 	requires:[
 			'cms.view.mdata.mdata_PlanMUI',
 			'cms.view.mdata.window.mdata_querySCellWindow'
 	         ],
 	model:'cms.model.cset.cset_CellArticleModel',
 	store:'',
 	init:function(){
 		 this.control({
	    	'mdata_PlanMUI button[name=query5101_1]':{
	    		click:this.query5101_1
	    	},//安全量移库查询
	    	'mdata_PlanMUI form button[name=butQueryForMan5101]':{
				click:this.butQueryForMan5101
			},//人工移库查询
	    	'mdata_querySCellWindow button[name=butSureForManW5101]':{
				click:this.butSureForManW5101
			},//人工移库--》查询--》确认
			'mdata_querySCellWindow button[name=butQueryForManW5101]':{
				click:this.butQueryForManW5101
			},//人工移库-->查询--》查询
			'mdata_PlanMUI combo[id=owner5101]':{
				change:this.owner5101change
			},//安全量补货，改变委托业主加载数据
			'mdata_PlanMUI combo[id=pick_type5101]':{
				change:this.pick_type5101change
			},//安全量补货，改变拣货类型加载数据
			'mdata_PlanMUI combo[id=ware_no5101]':{
				change:this.ware_no5101change
			},//安全量补货，改变仓区加载数据
			'mdata_PlanMUI combo[id=area_no5101]':{
				change:this.area_no5101change
			},
			'mdata_PlanMUI button[name=create5101_1]':{
				click:this.create5101_1
			},//安全量移库--》建单
			'mdata_PlanMUI button[name=refresh]':{
				click:this.refresh
			},//刷新
			'mdata_PlanMUI button[name=export]':{
				click:this.exportClick
			},//导出
			'mdata_PlanMUI button[name=upload]':{
				click:this.uploadClick
			},//导入
			'mdata_PlanMUI button[name=butCreateForMan5101]':{
				click:this.butCreateForMan5101
			},//人工移库--》建单
			'mdata_PlanMUI tabpanel[id=mdata_Plan_MUI5101]':{
				tabchange:this.tabPIdtabchange
			},//切换
			'mdata_querySCellWindow combo[id=cmbOwnerArticleNoForManW5101]':{
				select:this.cmbOwnerArticleNoForManW5101select
			},
			'mdata_PlanMUI grid[id=gridMdata_PlanMForMan5101]':{
				edit:this.gridMdata_PlanM
			},//编辑储位
			'cdef_DefCellCombo[id=D_CellNo]':{
				beforequery:this.DCellNo5101BeforeQuery
			},//目的储位选择	
	    });
	 
 		 if(Ext.getCmp('mdata_Plan_MUI5101').getActiveTab().id=="tabMdata_PlanMForMan5101"){
 			Ext.getCmp('cmbOwnerForMan5101').focus(false, 10);
			bindEnterSkip($('#formMdata_PlanMForMan5101'));//调用键盘处理方法
 		}
	  
 	},
	//目的储位选择
 	DCellNo5101BeforeQuery:function(){
 		var data = Ext.getCmp('gridMdata_PlanMForMan5101').getSelectionModel().getSelection();
			var strWheresq={
	    		wheresql:data[0].get('cellNo'),
				str:Ext.getCmp('D_CellNo').getValue()
			};
			Ext.apply(Ext.getCmp('D_CellNo').getStore().proxy.extraParams,strWheresq);
			Ext.getCmp('D_CellNo').getStore().removeAll();
			Ext.getCmp('D_CellNo').getStore().load();
	},
	
 	//编辑储位
 	gridMdata_PlanM:function(editor,e,eOpts){
		if(e.field=='DCellNo'){
			if(!Ext.isEmpty(e.value)){
				if(e.value.toUpperCase()==''||e.value.toUpperCase()==null){
					editor.context.record.set(e.field,editor.context.originalValue);					
					return;
				}
				for(var i=editor.context.rowIdx;i<Ext.getCmp('gridMdata_PlanMForMan5101').getStore().data.length;i++)
				{
					//if(Ext.isEmpty(Ext.getCmp('gridMdata_PlanMForMan5101').getStore().getAt(i).get('DCellNo'))){
						Ext.getCmp('gridMdata_PlanMForMan5101').getStore().getAt(i).set('DCellNo',e.value);
					//}
				}
			}
		}
		
	},
 	
 	
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('cmbOwnerForMan5101').getStore().load();
		
		//显示变量0为不显示，1为显示
		var packingSpec_5101=commonGetModuleField('5101','packingSpec')[0].flag;
		var packingUnit_5101=commonGetModuleField('5101','packingUnit')[0].flag;
		var planBox5101_1=commonGetModuleField('5101','planBox')[0].flag;
		var planQmin5101_1=commonGetModuleField('5101','planQmin')[0].flag;
		var planDis5101_1=commonGetModuleField('5101','planDis')[0].flag;
		var planBox5101_2=commonGetModuleField('5101','planBox')[0].flag;
		var planQmin5101_2=commonGetModuleField('5101','planQmin')[0].flag;
		var planDis5101_2=commonGetModuleField('5101','planDis')[0].flag;
		var planBox5101_3=commonGetModuleField('5101','planBox')[0].flag;
		var planQmin5101_3=commonGetModuleField('5101','planQmin')[0].flag;
		var planDis5101_3=commonGetModuleField('5101','planDis')[0].flag;
		
		if(packingSpec_5101==0){
			Ext.getCmp('packingSpec_5101').setVisible(false);
			Ext.getCmp('packingSpec_5101_1').setVisible(false);
			Ext.getCmp('packingSpec_5101_2').setVisible(false);
		}
		if(packingUnit_5101==0){
			Ext.getCmp('packingUnit_5101').setVisible(false);
			Ext.getCmp('packingUnit_5101_1').setVisible(false);
			Ext.getCmp('packingUnit_5101_2').setVisible(false);
		}
		if(planBox5101_1==0){
			Ext.getCmp('planBox5101_1').setVisible(false);
		}
		if(planQmin5101_1==0){
			Ext.getCmp('planQmin5101_1').setVisible(false);
		}
		if(planDis5101_1==0){
			Ext.getCmp('planDis5101_1').setVisible(false);
		}
		if(planBox5101_2==0){
			Ext.getCmp('planBox5101_2').setVisible(false);
		}
		if(planQmin5101_2==0){
			Ext.getCmp('planQmin5101_2').setVisible(false);
		}
		if(planDis5101_2==0){
			Ext.getCmp('planDis5101_2').setVisible(false);
		}if(planBox5101_3==0){
			Ext.getCmp('planBox5101_2').setVisible(false);
		}
		if(planQmin5101_3==0){
			Ext.getCmp('planQmin5101_2').setVisible(false);
		}
		if(planDis5101_3==0){
			Ext.getCmp('planDis5101_2').setVisible(false);
		}
		
	},
 	
 	exportClick:function(){
 		if(Ext.getCmp('mdata_Plan_MUI5101').getActiveTab().id=="mdata_Plan_MUI5101_1"){
 			commExport('mdata_Plan_MUI5101_1b');
 		}else if(Ext.getCmp('mdata_Plan_MUI5101').getActiveTab().id=="tabMdata_PlanMForMan5101"){
 			commExport('gridMdata_PlanMForMan5101');
 		}
 	},
 	//导入
 	uploadClick:function(){
		Ext.create('cms.view.mdata.window.mdataUploadWindow',
		{
			title:'上传'
		}).show();
		
	},
 	  refresh:function(){
 	  	Ext.getCmp('gridMdata_PlanMForMan5101').getStore().removeAll();
 	 
	    if(Ext.getCmp('cmbOwnerForMan5101').getStore().data.length>0)
		{
			Ext.getCmp('cmbOwnerForMan5101').setValue(Ext.getCmp('cmbOwnerForMan5101').getStore().getAt(0).data.value);		
		}
		Ext.getCmp('txtOwnerArticleNoForMan5101').setValue();
		Ext.getCmp('txtBarcodeForMan5101').setValue();
	    Ext.getCmp('txtCellNoForMan5101').setValue();
	    
	    Ext.getCmp('gridMdata_movefailList5101').getStore().removeAll();
        Ext.getCmp('gridMdata_movefailList5101').getStore().load();
 	  },
 	
 	  tabPIdtabchange:function(tabPanel, newCard, oldCard) {
	        if(newCard.title=="安全量补货") {
	            bindEnterSkip($('#mdata_Plan_MForm5101_1a'));//调用键盘处理方法
	            Ext.getCmp('owner5101').focus(false, 10);
	        }else  if(newCard.title=="人工移库") {
	            bindEnterSkip($('#formMdata_PlanMForMan5101'));//调用键盘处理方法
	            Ext.getCmp('cmbOwnerForMan5101').focus(false, 10);
	        }else if(newCard.title=="移库导入失败数据查询"){
	    		Ext.getCmp('gridMdata_movefailList5101').store.reload();
	        }
	   },
 	
 	butQueryForManW5101:function(th,e,eOpts){
 		var detail1 = [];
		var detail2 = [];
		var detail3 = [];
		var detailOld = [];
		 var p={
			columnId:'sc.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
				};
		            
		detail1.push(p);
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerArticleNoForManW5101').getValue())){
				var p={
				columnId:'bda.owner_article_no',
				value:Ext.getCmp("cmbOwnerArticleNoForManW5101").getValue()
				};
				detail3.push(p);
			}else if(!Ext.isEmpty(Ext.getCmp('txtOwnerArticleNoForMan5101').getValue())){
				var r={
				columnId:'bda.owner_article_no',
				condition:'7',
				value:Ext.getCmp("txtOwnerArticleNoForMan5101").getValue()
				};
				detail3.push(r);
			}
		if(!Ext.isEmpty(Ext.getCmp('cmnSCellNoForManW5101').getValue())){
				var p={
				columnId:'sc.cell_no',
				condition:'7',
				value:Ext.getCmp("cmnSCellNoForManW5101").getValue()
				};
				detail1.push(p);
			}else if(!Ext.isEmpty(Ext.getCmp('txtCellNoForMan5101').getValue())){
				var p={
				columnId:'sc.cell_no',
				condition:'7',
				value:Ext.getCmp("txtCellNoForMan5101").getValue()
				};
				detail1.push(p);
			}
		if(!Ext.isEmpty(Ext.getCmp('cmbBarcodeForManW5101').getValue())){
				var q={
				columnId:'sai.barcode',
				value:Ext.getCmp("cmbBarcodeForManW5101").getValue()
				};
				detail2.push(q);
			}else if(!Ext.isEmpty(Ext.getCmp('txtBarcodeForMan5101').getValue())){
				var q={
				columnId:'sai.barcode',
				value:Ext.getCmp("txtBarcodeForMan5101").getValue()
				};
				detail2.push(q);
			}
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerForMan5101').getValue())){
				var p={
				columnId:'sc.owner_no',
				value:Ext.getCmp("cmbOwnerForMan5101").getValue()
				};
				detail1.push(p);
			}
		
		if(!Ext.isEmpty(Ext.getCmp('qulity1401').getValue())){
			var p={
					columnId:'sai.quality',
					value:Ext.getCmp("qulity1401").getValue()
					};
					detail1.push(p);
		}
		var jsonDetail1 = Ext.encode(detail1);
		var barcode = Ext.encode(detail2);
		var ownerArticleNo = Ext.encode(detail3);
		
//////////////////////////获取已存在/////////////////////////////////////////
		var gridcount = Ext.getCmp('gridMdata_PlanMForMan5101').getStore().getCount();

		for(var i=0;i<gridcount;i++ ){
			var record=Ext.getCmp('gridMdata_PlanMForMan5101').getStore().getAt(i);
			var d={
				articleNo:record.get('articleNo'),
				cellNo:record.get('cellNo'),
				produceDate:record.get('produceDate'),
				expireDate:record.get('expireDate'),
				lotNo:record.get('lotNo'),
				quality:record.get('quality'),
				packingQty:record.get('packingQty'),
				labelNo:record.get('labelNo')
		};
		detailOld.push(d);
	};
	
	/////////////////////////////////////
	var oldData =Ext.encode(detailOld);
		
		var wheresql = {
			str : jsonDetail1,
			ownerArticleNo:ownerArticleNo,
			barcode:barcode,
			oldData:oldData
		};
	
		Ext.apply(Ext.getCmp('gridMdataPlanMForManW5101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridMdataPlanMForManW5101').getStore().removeAll();
		Ext.getCmp('gridMdataPlanMForManW5101').getStore().load();
 	},
 	cmbOwnerArticleNoForManW5101select:function(combo,records,eOpts){
 		var detail1 = [];
		var d={
			columnId:'v1.owner_article_no',
			value:records[0].data.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str2 : jsonDetail1
		};
		Ext.apply(Ext.getCmp('cmbBarcodeForManW5101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbBarcodeForManW5101').getStore().removeAll();
		Ext.getCmp('cmbBarcodeForManW5101').getStore().load();
 	},
 
 	/*
 	 * 安全量补货建单
 	 */
 	create5101_1:function(){
 		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		var record = Ext.getCmp('mdata_Plan_MUI5101_1b').getSelectionModel().getSelection();
		var store = Ext.getCmp('mdata_Plan_MUI5101_1b').getStore();
		if(record.length == 0){
    		Ext.example.msg('提示',"请选择建单的商品！");
        }else{
        	Ext.Msg.confirm("提示", "确定建单？",
        	function(button, text) {
				if (button == 'yes') {
					var msgShow = commonMegShow("正在建单,请稍等...");
					var detail1 = [];
					for(var i=0;i<record.length;i++ ){

						var d={
							ownerNo:Ext.getCmp("owner5101").getValue(),
							articleNo:record[i].get('articleNo'),
							produceDate:'1990-01-01',
							expireDate:'1990-01-01',
							qty:record[i].get('demandQty')*record[i].get('packingQty'),	
							stockType:'1',
							stockValue:'N',
							SCellNo:'N',
							labelNo:'N',
							subLabelNo:'N',
							DCellNo:record[i].get('cellNo'),
							rgstName:Ext.get("workerNo").getValue(),
							packingQty:record[i].get('packingQty'),
						};
						detail1.push(d);
						var workerNo=Ext.get("workerNo").getValue();
					};
						var jsonDetail1 = Ext.encode(detail1);		
						var flag="1";
				    	var params = {
				    		workerNo:workerNo,
							str:jsonDetail1,
							flag:flag
					    };
						Ext.Ajax.request({
							method:'POST',
							url:'mdata_PlanMAction_tscSend.action',
							params:params,
							success:function(response){
								msgShow.hide();
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.example.msg('提示',data.msg);
									var record = Ext.getCmp('mdata_Plan_MUI5101_1b').getSelectionModel().getSelection();
									var store = Ext.getCmp('mdata_Plan_MUI5101_1b').getStore();
									store.remove(record);
								}else{
									Ext.example.msg('提示',data.msg+data.obj);
								}				
							},
							failure:function(response){
								msgShow.hide();
								Ext.example.msg('提示',"提交不上,可能是网络问题！！！");
							}
						});	
					}
			     }
            );
       }
	},
	/*
	 * 人工移库建单
	 */
	butCreateForMan5101:function(){
		var store = Ext.getCmp('gridMdata_PlanMForMan5101').getStore();
		var record=store.query('checkColumn',true);//获得选中对象
		if(record.length == 0){
    		Ext.example.msg('提示',"请选择建单的商品！");
        }else{
        	var grid=Ext.getCmp(gridMdata_PlanMForMan5101);
        	for(var i=0;i<record.length;i++){
        		if(Ext.isEmpty(record.getAt(i).get('DCellNo'))||Ext.isEmpty(record.getAt(i).get('planBox')*record.getAt(i).get('packingQty')
        				+record.getAt(i).get('planQmin')*record.getAt(i).get('qminOperatePacking')
        				+record.getAt(i).get('planDis'))){
        			Ext.example.msg('提示','目的储位,整件数和零散数不能为空！');
        			return false;
        		}
        	}
        	if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt, $i18n_prompt.setWorkSpace);
				return;
			}
        	Ext.Msg.confirm("提示", "确定建单？",
        	function(button, text) {
				if (button == 'yes') {
					var msgShow = commonMegShow("正在建单,请稍等...");
					var detail1 = [];
					for(var i=0;i<record.length;i++ ){
							var d={
								ownerNo:Ext.getCmp("cmbOwnerForMan5101").getValue(),
								articleNo:record.getAt(i).get('articleNo'),
								produceDate:record.getAt(i).get('produceDate'),
								expireDate:record.getAt(i).get('expireDate'),
								qty:record.getAt(i).get('planBox')*record.getAt(i).get('packingQty')
		        				+record.getAt(i).get('planQmin')*record.getAt(i).get('qminOperatePacking')
		        				+record.getAt(i).get('planDis'),
								stockType:record.getAt(i).get('stockType'),
								stockValue:record.getAt(i).get('stockValue'),
								SCellNo:record.getAt(i).get('cellNo'),
								labelNo:record.getAt(i).get('labelNo'),
								subLabelNo:record.getAt(i).get('subLabelNo'),
								DCellNo:record.getAt(i).get('DCellNo'),
								rgstName:Ext.get("workerNo").getValue(),
								packingQty:record.getAt(i).get('packingQty'),
								lotNo:record.getAt(i).get('lotNo'),
								quality:record.getAt(i).get('quality')
						};
						detail1.push(d);
					};
						var workerNo=Ext.get("workerNo").getValue();
						var jsonDetail1 = Ext.encode(detail1);
						var flag="2";
				    	var params = {
				    		workerNo:workerNo,
							str:jsonDetail1,
							flag:flag
					    };
						Ext.Ajax.request({
							method:'POST',
							url:'mdata_PlanMAction_tscSend.action',
							params:params,
							success:function(response){
								msgShow.hide();
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.example.msg('提示',data.msg);
										for(var i=0;i<record.length;i++){
											store.remove(record.getAt(i));
										}
								}else{
									Ext.example.msg('提示',data.msg+data.obj);
								}				
							},
							failure:function(response){
								msgShow.hide();
								Ext.example.msg('提示',"提交不上,可能是网络问题");
							}
						});	
					};
            });
       }
	},
 	
	/*
	 * 改变委托业主后操作
	 */
	owner5101change:function(th,newValue,oldValue,eOpts){
		var detail1 = [];
		var d={
			columnId:'v1.owner_no',
			value:newValue
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('pick_type5101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('pick_type5101').getStore().removeAll();
		Ext.getCmp('pick_type5101').getStore().load();
	},
	/*
	 * 改变拣货类型
	 */
	pick_type5101change:function(th,newValue,oldValue,eOpts){
		var detail1 = [];
		var d={
			columnId:'v1.pick_type',
			value:newValue
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('ware_no5101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('ware_no5101').getStore().removeAll();
		Ext.getCmp('ware_no5101').getStore().load();
		
	},
	isCheck5101change:function(th,newValue,oldValue,eOpts){
		Ext.Ajax.request({
    			url:'mdata_PlanMAction_getCset_Cell_ArticleList.action',
    			params :{
    				isReplenishment:newValue
    			},
    			success : function(response) {
    				console.log(newValue);
    			}
    	});
	},
	/*
	 * 改变仓区
	 */
	ware_no5101change:function(th,newValue,oldValue,eOpts){
		var detail1 = [];
		var d={
			columnId:'v1.ware_no',
			value:newValue
		};
		detail1.push(d);
		
		var d1={
			columnId:'v1.pick_type',
			value:Ext.getCmp('pick_type5101').getValue()	
		};
		detail1.push(d1);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('area_no5101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('area_no5101').getStore().removeAll();
		Ext.getCmp('area_no5101').getStore().load();
	},
	/*
	 * 改变储区
	 */
	area_no5101change:function(th,newValue,oldValue,eOpts){
		var detail1 = [];
		var d={
			columnId:'v1.area_no',
			value:newValue
		};
		detail1.push(d);
		
		var d1={
				columnId:'v1.pick_type',
				value:Ext.getCmp('pick_type5101').getValue()	
		};
		detail1.push(d1);
		
		var d2={
				columnId:'v1.ware_no',
				value:Ext.getCmp('ware_no5101').getValue()	
			};
			detail1.push(d2);
		
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('stock_no5101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('stock_no5101').getStore().removeAll();
		Ext.getCmp('stock_no5101').getStore().load();
	},
	
 	/*
 	 * 查询安全补货量信息
 	 * zhouhuan
 	 */
 		query5101_1:function(){
    		var isReplenishment=Ext.getCmp('replenishment5101').getValue();
 			var detail1 = [];
			var d={
			columnId:'v1.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
			};
			detail1.push(d);
			if(!Ext.isEmpty(Ext.getCmp('owner5101').getValue())){
				var d={
				columnId:'cca.owner_no',
				value:Ext.getCmp('owner5101').getValue()
				};
				detail1.push(d);
			}
			if(!Ext.isEmpty(Ext.getCmp('pick_type5101').getValue())){
				var d={
				columnId:'cca.pick_type',
				value:Ext.getCmp('pick_type5101').getValue()
				};
				detail1.push(d);
			}
		 	if(!Ext.isEmpty(Ext.getCmp('ware_no5101').getValue())){
				var d={
				columnId:'cca.ware_no',
				value:Ext.getCmp('ware_no5101').getValue()
				};
				detail1.push(d);
			}
			if(!Ext.isEmpty(Ext.getCmp('area_no5101').getValue())){
				var d={
				columnId:'cca.area_no',
				value:Ext.getCmp('area_no5101').getValue()
				};
				detail1.push(d);
			}
			if(!Ext.isEmpty(Ext.getCmp('stock_no5101').getValue())){
				var d={
				columnId:'cca.stock_no',
				value:Ext.getCmp('stock_no5101').getValue()
				};
				detail1.push(d);
			}
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				str1: isReplenishment,
				str : jsonDetail1
			};
			Ext.apply(Ext.getCmp('mdata_Plan_MUI5101_1b')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('mdata_Plan_MUI5101_1b').getStore()
					.removeAll();
			Ext.getCmp('mdata_Plan_MUI5101_1b').getStore()
					.load();		
 			
		},
		
	/*
 	 * 查询人工移库信息
 	 */
 		butQueryForMan5101:function(){
 			var detail1 = [];
 			var detail2 = [];
 			var detail3 = [];
 			var detailOld = [];
            var p={
			columnId:'sc.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
			};
            
			detail1.push(p);
			if(!Ext.isEmpty(Ext.getCmp('cmbOwnerForMan5101').getValue())){
				var p={
				columnId:'sc.owner_no',
				value:Ext.getCmp("cmbOwnerForMan5101").getValue()
				};
				detail1.push(p);
			}
			if(!Ext.isEmpty(Ext.getCmp('txtCellNoForMan5101').getValue())){
				var p={
				columnId:'sc.cell_no',
				condition:'7',
				value:Ext.getCmp("txtCellNoForMan5101").getValue()
				};
				detail1.push(p);
			}
			if(!Ext.isEmpty(Ext.getCmp('txtBarcodeForMan5101').getValue())){
				var q={
				columnId:'sai.barcode',
				condition:'7',
				value:Ext.getCmp("txtBarcodeForMan5101").getValue()
				};
				detail2.push(q);
			}
			if(!Ext.isEmpty(Ext.getCmp('txtOwnerArticleNoForMan5101').getValue())){
				var r={
				columnId:'bda.owner_article_no',
				condition:'7',
				value:Ext.getCmp("txtOwnerArticleNoForMan5101").getValue()
				};
				detail3.push(r);
			}
			
			var jsonDetail1 = Ext.encode(detail1);
			var barcode = Ext.encode(detail2);
			var ownerArticleNo = Ext.encode(detail3);
			
			//////////////////////////获取已存在/////////////////////////////////////////
			var gridcount = Ext.getCmp('gridMdata_PlanMForMan5101').getStore().getCount();
	
			for(var i=0;i<gridcount;i++ ){
				var record=Ext.getCmp('gridMdata_PlanMForMan5101').getStore().getAt(i);
				var d={
					articleNo:record.get('articleNo'),
					cellNo:record.get('cellNo'),
					produceDate:record.get('produceDate'),
					expireDate:record.get('expireDate'),
					lotNo:record.get('lotNo'),
					quality:record.get('quality'),
					packingQty:record.get('packingQty'),
					labelNo:record.get('labelNo')
			};
			detailOld.push(d);
		};
		var oldData =Ext.encode(detailOld);
			
			//////////////////////////////////////////////////////////////////////////////
			
			var wheresql = {
				str : jsonDetail1,
				ownerArticleNo:ownerArticleNo,
				barcode:barcode,
				oldData:oldData
			};
		
			Ext.create('cms.view.mdata.window.mdata_querySCellWindow',{
				title:$i18n.s_cell_query
			}).show();	
			Ext.apply(Ext.getCmp('gridMdataPlanMForManW5101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('gridMdataPlanMForManW5101').getStore().removeAll();
			Ext.getCmp('gridMdataPlanMForManW5101').getStore().load();
			
			Ext.getCmp('qulity1401').setValue('0');
        
		},
		
		butSureForManW5101:function(){
	 	var record = Ext.getCmp('gridMdataPlanMForManW5101').getSelectionModel()
					.getSelection();
			if (record.length == 0) {Ext.example.msg("提示","请先选择您要操作的行!");
				return;
			} else {
				var store = Ext.getCmp("gridMdata_PlanMForMan5101").getStore();
				var count=store.getCount();
				for(var i=0;i<record.length;i++ ){
					var label = record[i].data.labelNo;
					if(label!="N"){
						Ext.example.msg($i18n.prompt,"标签库存不允许移库，请重新选择！");
						return 
					}
					
					var r = Ext.create('cms.model.stock.stock_ContentModel', {
					});
					r.set('articleNo',record[i].data.articleNo);
					r.set('ownerArticleNo',record[i].data.ownerArticleNo);	
					r.set('articleName',record[i].data.articleName);
					r.set('qty',record[i].data.pkQty);
					
					r.set('planBox',record[i].data.planBox);
					r.set('planQmin',record[i].data.planQmin);
					r.set('planDis',record[i].data.planDis);
					r.set('qminOperatePacking',record[i].data.qminOperatePacking);
					
					r.set('availableQty',record[i].data.availableQty);
					r.set('cellNo',record[i].data.cellNo);
					r.set('stockType',record[i].data.stockType);
					r.set('stockValue',record[i].data.stockValue);
					r.set('pkQty',record[i].data.pkQty);
					r.set('unit',record[i].data.unit);
					r.set('barcode',record[i].data.barcode);
					r.set('spec',record[i].data.spec);
					r.set('packingQty',record[i].data.packingQty);
					r.set('quality',record[i].data.quality);
					r.set('textQuality',record[i].data.textQuality);
					r.set('labelNo',record[i].data.labelNo);
					r.set('subLabelNo',record[i].data.subLabelNo);
					r.set('produceDate',record[i].data.produceDate);
					r.set('expireDate',record[i].data.expireDate);
					r.set('rgstName',record[i].data.rgstName);
					r.set('lotNo',record[i].data.lotNo);
					
					r.set('packingSpec',record[i].data.packingSpec);
					r.set('packingUnit',record[i].data.packingUnit);
					
					store.add(r);
					Ext.getCmp('gridMdata_PlanMForMan5101').editingPlugin.startEdit(0,6);
					store.getAt(count+i).set('checkColumn',true);
					
				}
				Ext.getCmp('mdata_querySCellWindow').close();
			}
	 	},
	 	/**
		 * 获取grid选中行
		 * @returns
		 */
		getGridSelIndex:function(){
			var select = Ext.getCmp('gridMdata_PlanMForMan5101').getSelectionModel().getSelection()[0];
			var index = Ext.getCmp('gridMdata_PlanMForMan5101').getStore().indexOf(select);
			return index;
		}
 	});
 	
 /**
 * 加载移库手建单》安全量补货明细信息
 */
function query5101_1(){
	var detail1 = [];
	if(!Ext.isEmpty(Ext.getCmp('exp_type900002').getValue())){
		var d={
		columnId:'a.exp_type',
		value:Ext.getCmp('exp_type900002').getValue()
		};
		detail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('locate_no900002').getValue())){
		var d={
		columnId:'v1.locate_no',
		value:Ext.getCmp('locate_no900002').getValue()
		};
		detail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('m_batch_no900002').getValue())){
		var d={
		columnId:'v1.batch_no',
		value:Ext.getCmp('m_batch_no900002').getValue()
		};
		detail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('area_no900002').getValue())){
		var d={
		columnId:'v1.s_cell_no',
		value:Ext.getCmp('area_no900002').getValue()
		};
		detail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('operate_type900002').getValue())){
		var d={
		columnId:'a.operate_type',
		value:Ext.getCmp('operate_type900002').getValue()
		};
		detail1.push(d);
	}
	var jsonDetail1 = Ext.encode(detail1);
	var wheresql = {
		str : jsonDetail1
	};
	Ext.apply(Ext.getCmp('grid_01_900002')
			.getStore().proxy.extraParams,
			wheresql);
	Ext.getCmp('grid_01_900002').getStore()
			.removeAll();
	Ext.getCmp('grid_01_900002').getStore()
			.load();
}
 	