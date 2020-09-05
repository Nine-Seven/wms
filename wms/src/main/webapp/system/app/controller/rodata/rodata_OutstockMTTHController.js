/*
 * 退厂回单（天天惠）
 * chensr
 * 7302
 */
var rowindex7302=0;
var isCanEdit7302=false;
Ext.getCmp('menu7302').items.items[3].setDisabled(true);
Ext.getCmp('menu7302').items.items[4].setDisabled(true);
Ext.define('cms.controller.rodata.rodata_OutstockMTTHController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_OutstockMReceiptTTHUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control(
		    //选中退厂回单单头，加载商品明细信息
		    {
			'rodata_OutstockMReceiptTTHUI grid[id=rodata_OutstockM7302_1]' : {
				beforeselect:this.rodata_OutstockM7302_1Beforeselect,
				selectionchange : this.selectionchange7302_1
			},
			//退厂回单数据校验
			'rodata_OutstockMReceiptTTHUI grid[id=gridRodata_OutstockM7302_3]':{
				beforeedit:this.beforeedit7302_2,
				edit:this.rodata_OutStock_MReceiptUIcompare
			},
			//点击修改按钮
			'rodata_OutstockMReceiptTTHUI button[name=edit]':{
				click:this.edit
			},
			//保存
			'rodata_OutstockMReceiptTTHUI button[name=save]':{
				click:this.save
			},
			//撤销
			'rodata_OutstockMReceiptTTHUI button[name=undo]':{
				click:this.undo
			},
			//查询
			'rodata_OutstockMReceiptTTHUI button[name=query]':{
				click:this.query
			},
			//刷新
			'rodata_OutstockMReceiptTTHUI button[name=refresh]':{
				click:this.refresh
			},
			//网格编辑
			'rodata_OutstockMReceiptTTHUI grid[id=gridRodata_OutstockM7302_3]':{
				beforeedit:this.gridRodata_OutstockM7302_3beforeedit,
				edit:this.gridRodata_OutstockM7302_3
			},
			
			//回单类型按钮
			'rodata_OutstockMReceiptTTHUI radiogroup[id=radiogroup7302]':{
				change:this.changeRodio
			}
			
		});
	},
	changeRodio:function(){
		
		if(Ext.getCmp("radiogroup7302").getValue().rb=='1'){
			Ext.getCmp('gridRodata_OutstockM7302_4').hide();
			Ext.getCmp('gridRodata_OutstockM7302_3').show();	
		}else{
			Ext.getCmp('gridRodata_OutstockM7302_3').hide();
			Ext.getCmp('gridRodata_OutstockM7302_4').show();	
		}
			
		var strDetail1 = [];
		var d1={
			columnId:'A.Task_Type',
			value:Ext.getCmp("radiogroup7302").getValue().rb
		};
		strDetail1.push(d1);
		var strQuery = {
				strQuery  : Ext.encode(strDetail1)
		};
		Ext.apply(Ext.getCmp('rodata_OutstockM7302_1').getStore().proxy.extraParams,strQuery);
		Ext.getCmp('rodata_OutstockM7302_1').getStore().removeAll();
		Ext.getCmp('rodata_OutstockM7302_1').getStore().load();	
		
		Ext.getCmp('gridRodata_OutstockM7302_3').getStore().removeAll();	
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		isCanEdit7302=false;
		//显示变量0为不显示，1为显示
		var realBox7302=commonGetModuleField('7302','realBox')[0].flag;
		var realDis7302=commonGetModuleField('7302','realDis')[0].flag;
		var realQmin7302=commonGetModuleField('7302','realQmin')[0].flag;
		var planBox7302=commonGetModuleField('7302','planBox')[0].flag;
		var planDis7302=commonGetModuleField('7302','planDis')[0].flag;
		var planQmin7302=commonGetModuleField('7302','planQmin')[0].flag;

		var packingUnit7302=commonGetModuleField('7302','packingUnit')[0].flag;
		var packingSpec7302=commonGetModuleField('7302','packingSpec')[0].flag;
		if(realBox7302==0){
			Ext.getCmp('realBox7302').setVisible(false);
		}
		if(realDis7302==0){
			Ext.getCmp('realDis7302').setVisible(false);
		}
		if(realQmin7302==0){
			Ext.getCmp('realQmin7302').setVisible(false);
		}
		if(planBox7302==0){
			Ext.getCmp('planBox7302').setVisible(false);
		}
		if(planDis7302==0){
			Ext.getCmp('planDis7302').setVisible(false);
		}
		if(planQmin7302==0){
			Ext.getCmp('planQmin7302').setVisible(false);
		}
		if(packingUnit7302==0){
			Ext.getCmp('packingUnit7302').setVisible(false);
		}
		if(packingSpec7302==0){
			Ext.getCmp('packingSpec7302').setVisible(false);
		}
		Ext.getCmp('rodata_OutstockM7302_1').getStore().load();
	},
	
	gridRodata_OutstockM7302_3beforeedit:function(e){
		if(!isCanEdit7302)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//退厂回单》商品数量较验等
	gridRodata_OutstockM7302_3:function(editor,e,eOpts){
		if(e.field=='realWholenum' || e.field=='realQty'){
			if(e.record.data.realQty>e.record.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityCannotMoreThanPlan);
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='realWholenum'){
					editor.context.record.set('realQty',editor.context.originalValue*e.record.data.packingQty);
				}else if(e.field=='realQty'){
					editor.context.record.set('realWholenum',(editor.context.originalValue/e.record.data.packingQty));
				}
			}
		}
	},
	
	/*
	 * 刷新
	 */
	  refresh:function(){
 	  	Ext.getCmp('gridRodata_OutstockM7302_3').getStore().removeAll();
 	  	Ext.getCmp('rodata_OutstockM7302_1').getStore().reload();
		Ext.getCmp('cmbOutstock_name7302_2').setValue();
 	  },
 	  
	/**
	 * 查找
	 */
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=7302;
		queryGrid='rodata_OutstockM7302_1';					
	},
	
	undo:function(){
		commonMenu4Button('menu7302','undo');
		Ext.getCmp('rodata_OutstockM7302_1').fireEvent('selectionchange','');
	},
	
	/*
	 * 点击保存按钮，回单
	 */
	save:function(){
			var gridcount=Ext.getCmp("gridRodata_OutstockM7302_3").getStore().getCount();
			var gridcount1=Ext.getCmp("gridRodata_OutstockM7302_4").getStore().getCount();
			if(gridcount>0 && Ext.getCmp("radiogroup7302").getValue().rb=='1')
			{
				if(!commonCheckdetailgrid('gridRodata_OutstockM7302_3',0,gridcount-1))
				{
					return;
				}
			}
			
			
			if(gridcount<=0 && Ext.getCmp("radiogroup7302").getValue().rb=='1'){			
				Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
				return;
			}
			
			if(gridcount1<=0 && Ext.getCmp("radiogroup7302").getValue().rb=='2'){
				Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
				return;
			}
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
				return;
			}

		if(commonCheckMster('formRodata_outstockm7302_2'))
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,
			function(button, text) {
			if (button == 'yes') {
				var msgShow = commonMegShow($i18n_prompt.saving_wait);
				var listDetail1 = [];
				if(Ext.getCmp("radiogroup7302").getValue().rb=='1'){
					for(var i=0;i<gridcount;i++ ){
						var objRecord  = Ext.getCmp('gridRodata_OutstockM7302_3').getStore().getAt(i);
						var RQ=objRecord.get('realWholenum') * objRecord.get('packingQty');
						var PQ=objRecord.get('planWholenum') * objRecord.get('packingQty');
                        if(PQ<RQ){
                        	Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotLargeToArticleQty);
            				return;
                        }
                       /* if(RQ==0){
                        	Ext.example.msg($i18n.prompt,$i18n_prompt.realQtyCanNotBeNull);
            				return;
                        }*/
						var d={	
								id:{
									enterpriseNo:Ext.get('enterpriseNo').getValue(),
									warehouseNo:objRecord.get('warehouseNo'),
									ownerNo:objRecord.get('ownerNo')
								},
								outstockNo:objRecord.get('outstockNo'),
								articleNo:objRecord.get('articleNo'),
								packingQty:objRecord.get('packingQty'),
								SCellNo:objRecord.get('SCellNo'),
								SLabelNo:objRecord.get('SLabelNo'),
								articleQty:objRecord.get('articleQty'),
								realQty: objRecord.get('realWholenum') * objRecord.get('packingQty'),
								DCellNo:objRecord.get('DCellNo'),
								produceDate:objRecord.get('produceDate'),
								expireDate:objRecord.get('expireDate'),
								lotNo:objRecord.get('lotNo'),
								quality:objRecord.get('quality'),
								rsvBatch1:objRecord.get('rsvBatch1'),
								rsvBatch2:objRecord.get('rsvBatch2'),
								rsvBatch3:objRecord.get('rsvBatch3'),
								rsvBatch4:objRecord.get('rsvBatch4'),
								rsvBatch5:objRecord.get('rsvBatch5'),
								rsvBatch6:objRecord.get('rsvBatch6'),
								rsvBatch7:objRecord.get('rsvBatch7'),
								rsvBatch8:objRecord.get('rsvBatch8')
						};
						listDetail1.push(d);
						var jsonlistDetail1 = Ext.encode(listDetail1);		
						var outUserId=Ext.getCmp('cmbOutstock_name7302_2').getValue();
						var params = {
								str:jsonlistDetail1,
								outUserId:outUserId
						};
					}
					Ext.Ajax.request({
						method:'POST',
						url:'rodata_OutstockMTTHAction_savePaper.action',
						params:params,
						success:function(response){
							msgShow.hide();
							var data1 = Ext.decode(response.responseText);
							if(data1.isSucc){
								commonMenu4Button('menu7302','save');
								Ext.example.msg($i18n.prompt,data1.msg);
								isCanEdit7302=false;
								Ext.getCmp('rodata_OutstockM7302_1').getStore().removeAll();
								Ext.getCmp('rodata_OutstockM7302_1').getStore().reload();
								Ext.getCmp('gridRodata_OutstockM7302_3').getStore().removeAll();
							}else{
								Ext.example.msg($i18n.prompt,data1.msg+data1.obj);
							}				
						},
						failure:function(response){
							msgShow.hide();
							Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
						}
					});	
				}else{
					
					var objRecord = Ext.getCmp('gridRodata_OutstockM7302_4').getSelectionModel().getSelection();
					if(objRecord.length!=0){
						for(var i=0; i<objRecord.length; i++){
							var d={	
									id:{
										enterpriseNo:Ext.get('enterpriseNo').getValue(),
										warehouseNo:objRecord[i].get('warehouseNo'),
										ownerNo:objRecord[i].get('ownerNo')
									},
									outstockNo:objRecord[i].get('outstockNo'),
									SLabelNo:objRecord[i].get('labelNo')
							};
							listDetail1.push(d);
						}
					}else{
						Ext.example.msg($i18n.prompt,$i18n_prompt.selectLabelNo);
						return;
					}
					var jsonlistDetail1 = Ext.encode(listDetail1);		
					var outUserId=Ext.getCmp('cmbOutstock_name7302_2').getValue();
					var params = {
							str:jsonlistDetail1,
							outUserId:outUserId
					};
					Ext.Ajax.request({
						method:'POST',
						url:'rodata_OutstockMTTHAction_saveLabel.action',
						params:params,
						success:function(response){
							msgShow.hide();
							var data1 = Ext.decode(response.responseText);
							if(data1.isSucc){
								commonMenu4Button('menu7302','save');
								Ext.example.msg($i18n.prompt,data1.msg);
								isCanEdit7302=false;
								Ext.getCmp('rodata_OutstockM7302_1').getStore().removeAll();
								Ext.getCmp('rodata_OutstockM7302_1').getStore().reload();
								Ext.getCmp('gridRodata_OutstockM7302_3').getStore().removeAll();
								Ext.getCmp('gridRodata_OutstockM7302_4').getStore().removeAll();
							}else{
								Ext.example.msg($i18n.prompt,data1.msg+data1.obj);
							}				
						},
						failure:function(response){
							msgShow.hide();
							Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
						}
					});	
				};
						
			}});
		}
	},
	
	
	/*
	 * 点击修改按钮,改为可编辑
	 */
	edit:function()
	{
		commonMenu4Button('menu7302','edit');
		isCanEdit7302=true;
		
	},
	beforeedit7302_2:function(e){
		if(!isCanEdit7302)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//退厂回单数据校验
	rodata_OutStock_MReceiptUIcompare:function(editor,e,eOpts)
	{
		if(e.field=='realWholenum')
		{
			if(e.objRecord.data.realQty>e.objRecord.data.articleQty)
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.totalQuantityCannotMoreThanPlan);
				editor.context.objRecord.set(e.field,editor.context.originalValue);
				if(e.field=='realWholenum'){
					editor.context.objRecord.set('realQty',editor.context.originalValue*e.objRecord.data.packingQty);
				}
			}
		}
	},
	
	//网格选择前事件
	rodata_OutstockM7302_1Beforeselect:function(){
	    if(isCanEdit7302)
	    {
	        return  false;  
	    }
	},
	
	/*
	 *网格显示
	 *zhouhuan
	 */
	selectionchange7302_1:function(th,selected,eOpts)
	{
		var objRecord = Ext.getCmp('rodata_OutstockM7302_1').getSelectionModel().getSelection();
		if(objRecord.length!=0){
			Ext.getCmp('txtOutstock_no7302_2').setValue(objRecord[0].data.outstockNo);
			Ext.get('rgstName7302').dom.innerHTML=objRecord[0].data.rgstName;
			Ext.get('rgstDate7302').dom.innerHTML=objRecord[0].data.rgstDate;
			Ext.get('updtName7302').dom.innerHTML=objRecord[0].data.updtName;
			Ext.get('updtDate7302').dom.innerHTML=objRecord[0].data.updtDate;
			
		var listDetail1 = [];
		var strDtl={
				columnId:'a.enterprise_no',
				value:Ext.get('enterpriseNo').getValue()
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.warehouse_no',
			value:objRecord[0].get("warehouseNo")
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.owner_no',
			value:objRecord[0].get("ownerNo")
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.outstock_no',
			value:objRecord[0].get("outstockNo")
		};
		listDetail1.push(strDtl);
		var jsonlistDetail1 = Ext.encode(listDetail1);
		var strWheresql = {
			strQuery: jsonlistDetail1,
			flag: Ext.getCmp("radiogroup7302").getValue().rb
		};
		if(Ext.getCmp("radiogroup7302").getValue().rb=='1'){
			Ext.apply(Ext.getCmp('gridRodata_OutstockM7302_3').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridRodata_OutstockM7302_3').getStore().removeAll();
			Ext.getCmp('gridRodata_OutstockM7302_3').getStore().load();
		}else{
			Ext.apply(Ext.getCmp('gridRodata_OutstockM7302_4').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridRodata_OutstockM7302_4').getStore().removeAll();
			Ext.getCmp('gridRodata_OutstockM7302_4').getStore().load();
		}
		
		
		commonMenu4Button('menu7302','undo');
		isCanEdit7302=false;
		}
			
	}
});


	function editOutStockD(rowindex7302){
		 var objRecord=Ext.getCmp('rodata_OutstockM7302_1').getStore().getAt(rowindex7302);
			var listDetail1 = [];
			var d={
				columnId:'a.warehouse_no',
				value:objRecord.data.warehouseNo
			};
			listDetail1.push(d);
			var d={
				columnId:'a.owner_no',
				value:objRecord.data.ownerNo
			};
			listDetail1.push(d);
			var d={
				columnId:'a.outstock_no',
				value:objRecord.data.outstockNo
			};
			listDetail1.push(d);
			var jsonlistDetail1 = Ext.encode(listDetail1);
			var wheresql = {
				str : jsonlistDetail1
			};
			
			Ext.apply(Ext.getCmp('gridRodata_OutstockM7302_3')
				.getStore().proxy.extraParams,
				wheresql);
			Ext.getCmp('gridRodata_OutstockM7302_3').getStore()
				.removeAll();
			Ext.getCmp('gridRodata_OutstockM7302_3').getStore()
				.load();
			isCanEdit7302=false;
		
	}
