/**
 * 模块名称：返配手建单
 * 模块编码：6101
 * 创建：周欢
 */
var rowindex6101=0;
var isCanEdit6101=false;
var type6101='';


Ext.define('cms.controller.ridata.ridata_UntreadController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_UntreadUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'ridata_UntreadUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导入
			'ridata_UntreadUI button[name=upload]':{
				click:this.uploadClick
			},//打印
			'ridata_UntreadUI button[name=detailPrint]':{
				click:this.detailPrint
			},//取消订单
			'ridata_UntreadUI button[id=undo6101]':{
				click:this.undo6101Click
			}/*,//取消订单
			'ridata_UntreadUI button[id=cancel6101]':{
				click:this.cancel6101Click
			}*/,//Grid双击
			'ridata_UntreadUI grid[id=gridRidata_UntreadM6101]':{
				itemdblclick:this.griddblclick
			},//TAB页切换
			'ridata_UntreadUI tabpanel[id=tabPId1200000]':{
				tabchange:this.tabPIdtabchange
			},//编辑商品Grid
			'ridata_UntreadUI grid[id=gridRidata_untreadD6101]':{
				beforeedit:this.ridata_Untread_Dbeforeedit,
				edit:this.ridata_Untread_DEdit
			},//上一条记录
			'ridata_UntreadUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'ridata_UntreadUI button[name=userNextButton]':{
				click:this.userNext
			},//新增前加载
			'ridata_UntreadUI button[name=userAddButton]':{
				click:this.newAdd
			},//编辑
			'ridata_UntreadUI button[name=userEditButton]':{
				click:this.edit
			},//保存
			'ridata_UntreadUI button[name=userSaveButton]':{
				click:this.save
			},//撤消
			'ridata_UntreadUI button[name=userUndoButton]':{
				click:this.undo
			},//删除
			'ridata_UntreadUI button[name=userDelButton]':{
				click:this.del
			},//新增明细
			'ridata_UntreadUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除明细
			'ridata_UntreadUI button[name=detailDelete]':{
				click:this.detailDelete
			},//验证返配建单日期
			'ridata_UntreadUI form datefield[id=dateUntread_date6101]':{
				blur:this.untreadDateBlur
			},//验证返配发单日期
			'ridata_UntreadUI form datefield[id=dateRequest_date6101]':{
				blur:this.requestDateBlur
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_no6101]':{
				beforequery:this.article_no6101BeforeQuery,
				select:this.article_noSelect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qty6101]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//客户选择
			'bdef_DefCustCombo[id=cmbCust6101]':{
				beforequery:this.cmbCust6101BeforeQuery
			},//验证原返配单号
			'ridata_UntreadUI form textfield[id=txtP_no6101]':{
				blur:this.UntreadNoBlur
			}
		});
	},
	
	//验证原返配单号
	UntreadNoBlur:function(){
		if(Ext.getCmp('txtUntread_id6101').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
			method:'post',
			url:'ridata_UntreadAction_checkUntreadNo',
			params:{
				poNo:Ext.getCmp('txtP_no6101').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg('提示','该单号已有，请重新录入');
		    		Ext.getCmp('txtP_no6101').setValue('');
		    		Ext.getCmp('txtP_no6101').focus();
		    	}
		    }
			});
		};	
	},
	
	//商品加载前
	article_no6101BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("cmbOwnerNo6101").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_no6101').getStore().proxy.extraParams,strWheresql);
	},
	
	initializtion:function(){
		isCanEdit6101=false;
		
		//显示变量0为不显示，1为显示
		var planBox_6101=commonGetModuleField('6101','planBox')[0].flag;
		var planQmin_6101=commonGetModuleField('6101','planQmin')[0].flag;
		var planDis_6101=commonGetModuleField('6101','planDis')[0].flag;
		var packingUnit_6101=commonGetModuleField('6101','packingUnit')[0].flag;
		var packingSpec_6101=commonGetModuleField('6101','packingSpec')[0].flag;
		
		if(planBox_6101==0){
			Ext.getCmp('planBox_6101').setVisible(false);
		}
		if(planQmin_6101==0){
			Ext.getCmp('planQmin_6101').setVisible(false);
		}
		if(planDis_6101==0){
			Ext.getCmp('planDis_6101').setVisible(false);
		}
		if(packingUnit_6101==0){
			Ext.getCmp('packingUnit_6101').setVisible(false);
		}
		if(packingSpec_6101==0){
			Ext.getCmp('packingSpec_6101').setVisible(false);
		}
		
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=6101;
		queryGrid='gridRidata_UntreadM6101';
	},
	//导入
	uploadClick:function(){
		
		Ext.create('cms.view.ridata.windows.ridataUploadWindow',
		{
			title:'上传'
		}).show();
	},
	
	detailPrint:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		var record=Ext.getCmp('gridRidata_UntreadM6101').getSelectionModel().getSelection();
		if(record.length<=0){
			Ext.example.msg('提示','请选择要打印的单据');
		}else {
			Ext.Ajax.request({
				url:'ridata_UntreadAction_printCheckPlan',
				method:'post',
				params:{
					poNo:record[0].get('poNo')
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc)
					{
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('gridRidata_UntreadM6101').getStore().load();
					}
					else
					{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});	
		}
	},
	//审空单
	undo6101Click:function(){
		var data=Ext.getCmp('gridRidata_UntreadM6101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseOrder);
		}else{
			Ext.Msg.confirm($i18n.prompt,'确定取消该订单？',function(button,text){
				if(button=='yes'){
					var detail=[];
					var no={
							ownerNo:data[0].get('ownerNo'),
							SUntreadNo:data[0].get('SUntreadNo'),
							untreadNo:data[0].get('untreadNo'),
							updtName:Ext.get('workerNo').getValue()
						};
					detail.push(no);
					var no=Ext.encode(detail);
					   var params = {
							  wheresql:no
					   };
					Ext.Ajax.request({
						url:'ridata_UntreadAction_tscEmptyList.action',
						params:params,
						success:function(response){
							var data=Ext.decode(response.responseText);					
							if(data.isSucc){
								Ext.getCmp('gridRidata_UntreadM6101').getStore().load();
								Ext.example.msg($i18n.prompt,data.msg);
							}else{
								Ext.example.msg($i18n.prompt,data.msg);							
							}
						}
					});
				}
			});
		}
	},

	/*cancel6101Click:function(){
		debugger;
		var data = Ext.getCmp('gridRidata_UntreadM6101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.Msg.confirm('提示','确定取消订单？',function(button,text){
					debugger;
					if(data[0].data.status !="10")
						{Ext.example.msg($i18n.prompt,"只有初始状态的订单才能取消！");
						return;}
					if(button=='yes'){
						debugger;
						Ext.Ajax.request({
							method:'post',
							url:'ridata_UntreadAction_cancelOrder.action',
							async:false,
							params:{
								warehouseNo:data[0].data.warehouseNo,
								untreadNo:data[0].data.untreadNo
						    },
						    success:function(response){
						    	debugger;
						    	var data = Ext.decode(response.responseText);
						    	if(data.isSucc){					
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('gridRidata_UntreadM6101').getStore().removeAll();
							 	  	Ext.getCmp('gridRidata_UntreadM6101').getStore().reload();
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg);

									//Ext.example.msg($i18n.prompt,data.msg);
								}
						    }
						});
					}
				});
		}		
	},*/

	griddblclick:function(th, record,  item,  index, e, eOpts){
		Ext.getCmp('tabPId1200000').items.items[1].setVisible(true);
		commonEditButton('menu6101','dbclick');
		Ext.getCmp('toolbar6101').items.items[0].disable(true);
		Ext.getCmp('toolbar6101').items.items[1].disable(true);
	},
	
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPidd2_6101i'){
			var data = Ext.getCmp('gridRidata_UntreadM6101').getSelectionModel().getSelection();
			if(data.length!=0){
				
				editUntread6101(data[0].index);//填充数据
				rowindex6101=data[0].index;
				debugger;
				//8-16添加 
				if(data[0].data.createFlag == '0'){ //wms自建
					 //当状态为结案和取消时，不允许修改，其他状态可以
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menu6101 [name=userEditButton]','修改');	
					}else{
						disableButtonFunc(0,'#menu6101 [name=userEditButton]','修改');
					}
				}else if(data[0].data.createFlag == '1'){ //ERP下传单据
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menu6101 [name=userEditButton]','修改');
					}else{
						disableButtonFunc(0,'#menu6101 [name=userEditButton]','修改');
					}
				}
				
				commonSetMsterReadOnlyByArray(
				new Array('txtP_no6101','cmbCust6101','cmbUntread_flag6101',
				'txtUntread_id6101','cmbU_type6101','cmbClass6101','cmbQ_type6101','takeType6101',
				'carPlanNo6101','txtRemark6101')
				,true);
				Ext.getCmp('tabPId1200000').items.items[1].setVisible(true);
				
				commonEditButton('menu6101','dbclick');
				Ext.getCmp('toolbar6101').items.items[0].disable(true);
				Ext.getCmp('toolbar6101').items.items[1].disable(true);
			}
		}
	},
	
	ridata_Untread_Dbeforeedit:function(e){
		if(!isCanEdit6101)
	    {
	        e.cancel = true;
	        return  false;  
	    }
	},
	
	ridata_Untread_DEdit:function(editor,e,eOpts){
		if(e.field=='packingQty'){
			if(!Ext.isEmpty(e.value)){
				if(editor.grid.getStore().
				findBy(function(record, id) {  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('articleNo')==editor.context.record.data.articleNo
						&& record.get('packingQty')==editor.context.record.data.packingQty;
					})!=-1)				
				{
					editor.context.record.set('packingQty',editor.context.originalValue);
					Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
				}
			}
		}
	},
	
	userPrev:function(){
		rowindex6101=rowindex6101-1;
		editUntread6101(rowindex6101);
	},
	
	userNext:function(){
		rowindex6101=rowindex6101+1;
		editUntread6101(rowindex6101);
	},
	
	newAdd:function(){
		addUntread6101();
		bindEnterSkip($('#formRidata_UntreadM6101'));//调用键盘处理方法
		type6101='';
	},
	
	edit:function(){
		type6101='edit';
		//8-16修改
		var data = Ext.getCmp('gridRidata_UntreadM6101').getSelectionModel().getSelection();
		if(data[0].data.createFlag=='0'){//wms自建
			if(data[0].data.status =='10'){//状态为10，验收数量为0
				
				Ext.Ajax.request({
					method:'post',
					async:false,
					url:'ridata_UntreadAction_getCheckNumber',
					params:{
						untreadNo:data[0].data.untreadNo,
				    },
				    success:function(response){
				    	debugger;
				    	var data = Ext.decode(response.responseText);
				    	if(data.isSucc){
				    		//判断验收量是否为0 
				    		if(data.obj == '0'){
				    			commonSetMsterReadOnlyByArray(
										new Array('cmbOwnerNo6101','txtP_no6101','cmbUntread_flag6101',
												'cmbClass6101','cmbQ_type6101','dateUntread_date6101',
												'dateRequest_date6101','orgNo6101','takeType6101',
												'carPlanNo6101','txtRemark6101'),false);
								//控制明细
								Ext.getCmp('toolbar6101').items.items[0].enable(true);
								Ext.getCmp('toolbar6101').items.items[1].enable(true);
								isCanEdit6101 = true;	//这个全局变量有控制表格是否可编辑
				    		}else{
				    			commonSetMsterReadOnlyByArray(
										new Array('txtRemark6101'),false);
								isCanEdit6101 = false;
				    		}
				    	}
				    }
				});
			}else{
				commonSetMsterReadOnlyByArray(
						new Array('txtRemark6101'),false);
				isCanEdit6101 = false;
			}
		}else if(data[0].data.createFlag=='1'){//ERP下传单据
			commonSetMsterReadOnlyByArray(
					new Array('txtRemark6101'),false);
			isCanEdit6101 = false;	
		}
		
		//isCanEdit6101=true;
		commonEditButton('menu6101','edit');
		Ext.getCmp('toolbar6101').items.items[0].enable(true);
		Ext.getCmp('toolbar6101').items.items[1].enable(true);
	},
	
	undo:function(){
		if(type6101=='edit'){
		   editUntread6101(rowindex6101);
		   commonEditButton('menu6101','undo');
		   commonSetMsterReadOnlyByArray(
					new Array('cmbOwnerNo6101','cmbCust6101','txtP_no6101','cmbU_type6101','cmbClass6101','dateRequest_date6101',
							'orgNo6101','cmbQ_type6101','cmbUntread_flag6101','takeType6101', 'txtRemark6101','carPlanNo6101')
					,true);
		   Ext.getCmp('menu6101').items.items[3].enable(true);
		   Ext.getCmp('toolbar6101').items.items[0].disable(true);
		   Ext.getCmp('toolbar6101').items.items[1].disable(true);
		}else{
		isCanEdit6101=false;
		addUntread6101();
		Ext.getCmp('gridRidata_untreadD6101').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
		new Array('txtP_no6101','cmbCust6101','cmbUntread_flag6101',
		'txtUntread_id6101','cmbU_type6101','cmbClass6101','cmbQ_type6101','orgNo6101')
		,true);
		commonEditButton('menu6101','undo');
		Ext.getCmp('toolbar6101').items.items[0].disable(true);
		Ext.getCmp('toolbar6101').items.items[1].disable(true);
		}
	},
	
	save:function(){
		var gridcount=Ext.getCmp('gridRidata_untreadD6101').getStore().getCount();
		if(gridcount>0){
			if(!commonCheckdetailgrid('gridRidata_untreadD6101',0,gridcount-1))
			{
				return;
			}
		}else{
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
	
		var master={
			id:{
				untreadNo:Ext.getCmp('txtUntread_id6101').getValue()
			},
			ownerNo:Ext.getCmp('cmbOwnerNo6101').getValue(),
			untreadType:Ext.getCmp('cmbU_type6101').getValue(),
			classType:Ext.getCmp('cmbClass6101').getValue(),
			poType:'UM',
			quality:Ext.getCmp('cmbQ_type6101').getValue(),
			poNo:Ext.getCmp('txtP_no6101').getValue(),
			custNo:Ext.getCmp('cmbCust6101').getValue(),
			untreadDate:Ext.getCmp('dateUntread_date6101').getValue(),
			requestDate:Ext.getCmp('dateRequest_date6101').getValue(),
			status:'10',
			createFlag:'0',
			untreadRemark:Ext.getCmp('txtRemark6101').getValue(),
			untreadFlag:Ext.getCmp('cmbUntread_flag6101').getValue(),
			stockType:'1',
			stockValue:'N',
			deptNo:'N',
			sendFlag:'10',
			expNo:'0',
			carPlanNo:Ext.getCmp('carPlanNo6101').getValue(),
			orgNo:Ext.getCmp('orgNo6101').getValue(),
			takeType:Ext.getCmp('takeType6101').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date()
		};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var untread=Ext.getCmp('gridRidata_untreadD6101').getStore().getAt(i);
			var d={
				id:{
					ownerNo:Ext.getCmp('cmbOwnerNo6101').getValue(),
					untreadNo:Ext.getCmp('txtUntread_id6101').getValue(),
					poId:i
				},
				supplierNo:untread.get('supplierNo'),
				articleNo:untread.get('articleNo'),
				packingQty:untread.get('packingQty'),
				
				//untreadQty:untread.get('packingQty')*untread.get('pobox'),
				untreadQty:untread.get('planBox')*untread.get('packingQty')
						   +untread.get('planQmin')*untread.get('qminOperatePacking')
						   +untread.get('planDis'),
				
				checkQty:0,
				status:'10',
				quality:'0',
				unitCost:untread.get('unitCost')==undefined? 0:untread.get('unitCost')
			};
			if((untread.get('planBox')*untread.get('packingQty')
					   +untread.get('planQmin')*untread.get('qminOperatePacking')
					   +untread.get('planDis'))==0){
				Ext.example.msg('提示','返配数量不能为0');
				return;
			}
			detail.push(d);
		}
		disableButtonFunc(1,'#menu6101 [name=userSaveButton]','保存');
		var jsonMaster=Ext.encode(master);
		var jsonDetail=Ext.encode(detail);
		
		var params={
			jsonMaster:jsonMaster,
			jsonDetail:jsonDetail
		};
		Ext.Ajax.request({
			method:'post',
			url:'ridata_UntreadAction_saveRidata_Untread',
			params:params,
			async:false,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					if(typeof(Ext.getCmp('gridRidata_UntreadM6101'))!=='undefined'){
						Ext.getCmp('gridRidata_UntreadM6101').store.reload();
					}
					Ext.getCmp('txtUntread_id6101').setValue(data.obj);
					commonEditButton('menu6101','save');
					isCanEdit6101=false;
					Ext.getCmp('toolbar6101').items.items[0].disable(true);
					Ext.getCmp('toolbar6101').items.items[1].disable(true);
					var str={
							poNo:Ext.getCmp('txtP_no6101').getValue()
						};
					
//					if(type6101=='add'){
//						Ext.Ajax.request({
//							method:'post',
//							url:'ridata_UntreadAction_printCheckPlan',
//							params:str,
//							success:function(response){
//								var r = Ext.decode(response.responseText);
//								if(r.isSucc){
//																
//								}else{
//									Ext.example.msg('提示',r.msg+r.obj);
//								}
//							}
//						});
//					}
				
				}else{
					Ext.example.msg('提示',data.msg+data.obj);
					disableButtonFunc(0,'#menu6101 [name=userSaveButton]','保存');
				}
			}
		});
	},
	
	del:function(){
		var untreadNo=Ext.String.trim(Ext.getCmp('txtUntread_id6101').getValue());
		Ext.Msg.confirm('提示','确定删除数据？',function(button,text){
			if(button=='yes'){
				var params={
					untreadNo:untreadNo
				};
				Ext.Ajax.request({
					method:'post',
					url:'ridata_UntreadAction_deleteUntread',
					params:params,
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							if(typeof(Ext.getCmp('gridRidata_UntreadM6101'))!="undefined"){
									
								var g=Ext.getCmp('gridRidata_UntreadM6101');
								var atindex=g.getStore().findExact('untreadNo',Ext.getCmp('txtUntread_id6101').getValue());
								g.getStore().removeAt(atindex);
								var count=g.store.getCount();
									
								if(count==0){
									addUntread6101();
								}else if(atindex==-1 && count!=0){
									editUntread6101(0);
								}else if(atindex<count){
									editUntread6101(atindex);
								}else {
									editUntread6101(count-1);
								};
							};
						};
					}
				});
			};
		});
	},
	
	detailAdd:function(){
		type6101='add';
		var store = Ext.getCmp('gridRidata_untreadD6101').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
			
		}else{
			if(!commonCheckIsInputAll('formRidata_UntreadM6101')){
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('txtP_no6101','cmbCust6101','cmbUntread_flag6101',
			'cmbOwnerNo6101','txtUntread_id6101','cmbU_type6101','cmbClass6101','cmbQ_type6101',
			'dateUntread_date6101','dateRequest_date6101','orgNo6101')
			,true);
		}
		var r=Ext.create('cms.model.ridata.ridata_UntreadDModel',{});
		store.add(r);
		Ext.getCmp('gridRidata_untreadD6101').editingPlugin.startEdit(count,1);
	},
	
	detailDelete:function(){
		var data = Ext.getCmp('gridRidata_untreadD6101').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm('提示','确定删除数据',function(button,text){
			if(button=='yes'){
				Ext.getCmp('gridRidata_untreadD6101').getStore().remove(data);					
				if(Ext.getCmp("gridRidata_untreadD6101").getStore().getCount()==0)
				{
					commonSetMsterReadOnlyByArray(
					new Array('txtP_no6101','cmbCust6101','cmbUntread_flag6101',
					'cmbOwnerNo6101','txtUntread_id6101','cmbU_type6101','cmbClass6101','cmbQ_type6101',
					'dateUntread_date6101','dateRequest_date6101','orgNo6101')
					,false);
				};
			}
			});
		}else{
			Ext.example.msg('提示', '请先选择您要删除的行');
			return;
		}
	},
	
	untreadDateBlur:function(){
		if(Ext.getCmp('dateRequest_date6101').getValue()!=null){
			if(Ext.getCmp('dateRequest_date6101').getValue()!=''
				&& Ext.getCmp('dateRequest_date6101').getValue()<
					Ext.getCmp('dateUntread_date6101').getValue()){
				Ext.example.msg('提示','发单日期不能小于建单日期');
				Ext.getCmp('dateUntread_date6101').setValue('');		
			}
		};
	},
	
	requestDateBlur:function(){
		if(Ext.getCmp('dateUntread_date6101').getValue()!=null){
			if(Ext.getCmp('dateUntread_date6101').getValue()!=''
				&& Ext.getCmp('dateUntread_date6101').getValue()>
					Ext.getCmp('dateRequest_date6101').getValue()){
				Ext.example.msg('提示','建单日期不能大于发单日期');
				Ext.getCmp('dateRequest_date6101').setValue('');
			}
		};
	},
	
	article_noSelect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'ridata_UntreadAction_getUntreadArticle',
			params:{
				articleNo:combo.getValue()
		    },
		    success:function(response){
				console.log(response);
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('gridRidata_untreadD6101').getSelectionModel().getSelection();
		    	data[0].set('articleName',res[0].articleName);
		    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);		    	
		    	data[0].set('supplierNo',res[0].supplierNo);
		    	data[0].set('articleName',res[0].articleName);
		    	data[0].set('barcode',res[0].barcode);
		    	data[0].set('packingUnit',null);
		    	
		    	data[0].set('qminOperatePacking',res[0].qminOperatePacking);		    	
		    	data[0].set('unitPacking',res[0].unitPacking);
		    	
		    	data[0].set('planBox',0);
		    	data[0].set('planQmin',0);
		    	data[0].set('planDis',0);
		    	
		    	data[0].set('unitCost',0);
		    	
		     	Ext.Ajax.request({
					method:'post',
					url:'bdef_DefarticleAction_getMaxArticlePacking',
					params:{
						strWheresql:combo.getValue()
				    },
				    success:function(response){
				    	var res = Ext.decode(response.responseText);
				    	data[0].set('packingQty',res);
				    	
				    	Ext.Ajax.request({
							method:'post',
							url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
							params:{
								strArticleNo:combo.getValue(),
								strPackingQty:res
						    },
						    success:function(response){
						    	var flagCount=0;
						    	var gridcount=Ext.getCmp('gridRidata_untreadD6101').getStore().getCount();
						    	for(var i=0;i<gridcount;i++){
						    		var exp=Ext.getCmp('gridRidata_untreadD6101').getStore().getAt(i);    		
						    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==data[0].get('packingQty')){
						    			flagCount=flagCount+1;
						    		}
						    	}
						    	var res = Ext.decode(response.responseText);
						    	data[0].set('packingUnit',res[0].packingUnit);
						    	data[0].set('packingSpec',res[0].spec);
						    	
						    	if(flagCount==0){
						    		var res = Ext.decode(response.responseText);
							    	data[0].set('packingUnit',res[0].packingUnit);
							    	data[0].set('packingSpec',res[0].spec);
						    	}else{
						    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
						    		data[0].set('packingQty',null);
						    	}  	
						    }
						});
				    }
				    
		    	});
		    }
		});
	},
	
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('gridRidata_untreadD6101').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	
	packingQtyselect:function(combo){
		var data = Ext.getCmp('gridRidata_untreadD6101').getSelectionModel().getSelection();
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
			params:{
				strArticleNo:data[0].get('articleNo'),
				strPackingQty:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	data[0].set('packingUnit',res[0].packingUnit);
		    	data[0].set('packingSpec',res[0].spec);
		    }
		});
	},
	cmbCust6101BeforeQuery:function(){
        var listDetail1  =  [];
        if(!Ext.isEmpty(Ext.getCmp("cmbOwnerNo6101").getValue())
        		&&Ext.getCmp("cmbOwnerNo6101").getValue()!='ALL'){
        	var strDtl = {
        			columnId:'t1.owner_no',
        			value:Ext.getCmp("cmbOwnerNo6101").getValue()
        		};
        		listDetail1.push(strDtl);
        }
		var strWheresql={
			strQuery:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('cmbCust6101').getStore().proxy.extraParams,strWheresql);
	}
});

//新增前加载
function addUntread6101(){
	Ext.getCmp('formRidata_UntreadM6101').getForm().reset();
	Ext.getCmp('cmbUntread_flag6101').setValue('0');
	Ext.getCmp('txtUntread_id6101').setValue('保存时自动生成');
	//Ext.getCmp('cmbOwnerNo6101').setValue('001');
	Ext.getCmp('cmbQ_type6101').setValue('A');
	Ext.getCmp('cmbClass6101').setValue('0');
	Ext.getCmp('cmbU_type6101').setValue('UM');
	Ext.getCmp('orgNo6101').setValue('001');
	Ext.getCmp('takeType6101').setValue('0');
	Ext.getCmp('dateUntread_date6101').setValue(new Date());
	Ext.getCmp('dateRequest_date6101').setValue(new Date());
	Ext.get('rgstName6101').dom.innerHTML=Ext.get('workerNo').getValue();
	Ext.get('rgstDate6101').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');;
	Ext.get('updtName6101').dom.innerHTML='';
	Ext.get('updtDate6101').dom.innerHTML='';
	Ext.getCmp('gridRidata_untreadD6101').getStore().removeAll();
	commonSetMsterReadOnlyByArray(
	new Array('txtP_no6101','cmbCust6101','cmbUntread_flag6101',
	'cmbOwnerNo6101','txtUntread_id6101','cmbU_type6101','cmbClass6101','cmbQ_type6101',
	'dateUntread_date6101','dateRequest_date6101','orgNo6101','takeType6101')
	,false);
	commonSetMsterReadOnlyByArray(
	new Array('txtUntread_id6101'),true);
	
	commonEditButton('menu6101','add');
	Ext.getCmp('toolbar6101').items.items[0].enable(true);
	Ext.getCmp('toolbar6101').items.items[1].enable(true);
	Ext.getCmp('cmbCust6101').focus();
	isCanEdit6101=true;
};

//填充数据
function editUntread6101(rowindex6101){
	if(rowindex6101==0)
	{
		Ext.getCmp('menu6101').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu6101').items.items[0].enable(true);
	}
	if(rowindex6101==Ext.getCmp('gridRidata_UntreadM6101').getStore().getCount()-1)
	{		
		Ext.getCmp('menu6101').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu6101').items.items[1].enable(true);
	}
	var record=Ext.getCmp('gridRidata_UntreadM6101').getStore().getAt(rowindex6101-(Ext.getCmp('gridRidata_UntreadM6101').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('txtP_no6101').setValue(record.data.poNo);
	Ext.getCmp('cmbCust6101').getStore().add({
    	value:record.data.custNo,
    	dropValue:'['+record.data.custNo+']'+record.data.custName,
    	text:record.data.custName
    });
	Ext.getCmp('cmbCust6101').setValue(record.data.custNo);
	Ext.getCmp('cmbOwnerNo6101').setValue(record.data.ownerNo);
	Ext.getCmp('cmbUntread_flag6101').setValue(record.data.untreadFlag);
	Ext.getCmp('txtUntread_id6101').setValue(record.data.untreadNo);
	Ext.getCmp('cmbU_type6101').setValue(record.data.untreadType);
	Ext.getCmp('cmbClass6101').setValue(record.data.classType);
	Ext.getCmp('cmbQ_type6101').setValue(record.data.quality);
	Ext.getCmp('dateUntread_date6101').setValue(record.data.untreadDate);
	Ext.getCmp('dateRequest_date6101').setValue(record.data.requestDate);
	Ext.getCmp('txtRemark6101').setValue(record.data.untreadRemark);
	Ext.getCmp('orgNo6101').setValue(record.data.orgNo);
	Ext.getCmp('takeType6101').setValue(record.data.takeType);
	Ext.getCmp('carPlanNo6101').setValue(record.data.carPlanNo);	
	Ext.get('rgstName6101').dom.innerHTML=record.data.rgstName;
	Ext.get('rgstDate6101').dom.innerHTML=record.data.rgstDate;
	Ext.get('updtName6101').dom.innerHTML=record.data.updtName;
	Ext.get('updtDate6101').dom.innerHTML=record.data.updtDate;
	
	var wheresql={
		wheresql:record.data.untreadNo
	};
	Ext.apply(Ext.getCmp('gridRidata_untreadD6101').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('gridRidata_untreadD6101').getStore().removeAll();
	Ext.getCmp('gridRidata_untreadD6101').getStore().load();
	isCanEdit6101=false;
};

//打印后改状态
/*function tips6101(){
	var record=Ext.getCmp('gridRidata_UntreadM6101').getSelectionModel().getSelection();
	if(record.length<=0){
		return;
	}else {
		Ext.Ajax.request({
			method:'post',
			url:'ridata_UntreadAction_updatePrinter',
			params:{
				untreadNo:record[0].get('untreadNo'),
				SUntreadNo:record[0].get('SUntreadNo')
		    },
		    success:function(response){
		    	Ext.getCmp('gridRidata_UntreadM6101').getStore().load();
		    }
		});
	}
};*/
	