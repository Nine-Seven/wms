/**
 * 模块名称：杂项费用维护UI
 * 模块编码：B203
 * 创建：hcx 
 */
rowindexB203=0;
typeB203='';
typeB203_2='';
var checkFlag='0';
Ext.define('cms.controller.cost.cost_OtherController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cost.cost_OtherUI'
	          ],
	init:function(){
		this.control({//新增
			'cost_OtherUI button[name=detailAdd]':{
				click:this.detailAdd
			},//保存杂项费用清单信息
			'cost_OtherListAddOrEditWindow button[name=save]':{
				click:this.save
			},//修改
			'cost_OtherUI button[name=detailEdit]':{
				click:this.detailEdit
			},//删除
			'cost_OtherUI button[name=detailDelete]':{
				click:this.detailDelete
			},//查找
			'cost_OtherUI button[name=detailQuery]':{
				click:this.otherListDetailQuery
			},//导出
			'cost_OtherUI button[name=detailExport]':{
				click:this.exportClick
			},//关闭窗口
			'cost_OtherListAddOrEditWindow button[name=close]':{
				click:this.colse
			},//上一条记录
			'cost_OtherListAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'cost_OtherListAddOrEditWindow button[name=next]':{
				click:this.next
			},//根据货主和仓别加载科目，并且查找相应的信息
			'cost_OtherUI combo[id=ownerNoUIB203]':{
				change:this.ownerNoUIB203Select
			},//根据货主、仓别和费用代码查找对应的杂项费用信息
			'cost_OtherUI combo[id=costNoUIB203]':{
				select:this.costNoUIB203Select
			},//关闭前事件
			'cost_OtherListAddOrEditWindow':{
				beforeclose:this.cost_OtherListAddOrEditWindowBeforeclose
			},
			//点击其他费用信息列表数据
			'cost_OtherListAddOrEditWindow grid[id=costOtherSetB203]':{
				selectionchange:this.costOtherSetB203Selectionchange//单击
			},//新增其他费用信息
			'cost_OtherListAddOrEditWindow button[id=addB203]':{
				click:this.addB203Clike
			},//修改其他费用信息
			'cost_OtherListAddOrEditWindow button[id=editB203]':{
				click:this.editB203Clike
			},//其他费用-删除   
			'cost_OtherListAddOrEditWindow button[id=deleteB203]':{
				click:this.deleteB203Clike
			},//其他费用-上一条记录
			'cost_OtherSetAddorEditWindow button[name=prev]':{
				click:this.otherSetPrev
			},//其他费用-下一条记录
			'cost_OtherSetAddorEditWindow button[name=next]':{
				click:this.otherSetNext
			},//其他费用-关闭前事件
			'cost_OtherSetAddorEditWindow':{
				beforeclose:this.cost_OtherSetAddOrEditWindowBeforeclose
			},//其他费用-关闭窗口
			'cost_OtherSetAddorEditWindow button[name=close]':{
				click:this.cost_OtherSetAddorEditWindowColse
			},//查找
			'cost_OtherListAddOrEditWindow button[id=queryB203]':{
				click:this.otherSetDetailQuery
			},//保存其他费用信息
			'cost_OtherSetAddorEditWindow button[name=save]':{
				click:this.otherSetSave
			},//验证费用代码唯一性
			'cost_OtherSetAddorEditWindow form textfield[id=costNo_2]':{
				blur:this.costNo_2Blur
			},//重新加载添加窗口
			'cost_OtherSetAddorEditWindow button[name=add]':{
				click:this.otherSetadd
			}
		});
	},
	
	//调用新增页面
	detailAdd:function(){
		Ext.create('cms.view.cost.window.cost_OtherListAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_OtherListB203');
		typeB203='add'; 
		setcost_MenuWidgetB203Tab1();
		commonSetFieldReadOnly('costOtherSetFormB203',true);
		Ext.getCmp('costOtherSetB203').getStore().removeAll();
		Ext.getCmp('ownerNoB203').setValue('');
		disableButtonFunc(0,'#addB203',$i18n.titleadd);
		disableButtonFunc(0,'#editB203',$i18n.titleupdate);
		disableButtonFunc(0,'#deleteB203',$i18n.delete_1);
		disableButtonFunc(0,'#queryB203',$i18n.find);
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('costOtherSetB203').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('costOtherSetB203').getStore().removeAll();
		Ext.getCmp('costOtherSetB203').getStore().load();
	},
	
	//保存杂项费用清单信息
	save: function(){
		debugger;
		if(!commonCheckIsInputAll('costOtherSetFormB203')){
			return;
		}
	
		if(Ext.getCmp('costOtherSetFormB203').getForm().isValid()){
			var cust=Ext.getCmp('costOtherListB203').getSelectionModel().getSelection()[0];
			var rec=Ext.getCmp('costOtherSetB203').getSelectionModel().getSelection()[0];
			var sourceNo='N';
			if(typeB203=='add'){
			    sourceNo=Ext.get('sourceNoB203').getValue()==undefined?'N':Ext.get('sourceNoB203').getValue();
			}
			var cost_OtherList_Str={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						ownerNo:Ext.getCmp('ownerNoB203').getValue(),
						costNo:Ext.getCmp('costNoB203').getValue(),
						costDate:Ext.util.Format.date(Ext.getCmp('costDateB203').getValue(), 'Y-m-d'),
						sourceNo:typeB203=='add'?sourceNo:cust.data.sourceNo
					},	
					costValue:Ext.getCmp('costValueB203').getValue(),					
					costFlag:Ext.getCmp('costFlagB203').getValue(),					
					status:Ext.getCmp('statusB203').getValue(),					
					remark:Ext.getCmp('remarkB203').getValue(),
					createFlag:typeB203=='add'?'0':cust.data.createFlag,
					checkNo:typeB203=='add'?'':cust.data.checkNo,
					rgstDate:typeB203=='add'?new Date():cust.data.rgstDate,
					rgstName:typeB203=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
					updtDate:typeB203=='add'?'':new Date(),
					updtName:typeB203=='add'?'':Ext.get('workerNo').getValue()
				};			
			var imPort=Ext.encode(cost_OtherList_Str);
			Ext.Ajax.request({
				url:'cost_OtherAction_saveCostOtherList',
				method:'post',
				params:{
					str:imPort
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){					
						Ext.example.msg($i18n.prompt,data.msg);
//						Ext.getCmp('formulasetUIB203').getStore().reload();
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});			
		}
	},
	//修改杂项费用信息
	detailEdit:function(){
		var data = Ext.getCmp('costOtherListB203').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_OtherListAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB203='edit';
			updateCommMenu5('cost_OtherListB203');
			commonMenu5PrevOrNext('cost_OtherListB203','costOtherListB203',0);
			disableButtonFunc(1,'#addB203',$i18n.titleadd);
			disableButtonFunc(1,'#editB203',$i18n.titleupdate);
			disableButtonFunc(1,'#deleteB203',$i18n.delete_1);
			disableButtonFunc(1,'#queryB203',$i18n.find);
			this.loadDatabB203();
			checkFlag='0';
		}
	},
	//删除杂项费用清单
	detailDelete:function(){
		var cust=Ext.getCmp('costOtherListB203').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			if(cust[0].data.status != '10'){
				Ext.example.msg($i18n.prompt,$i18n_prompt.canNotDelete);
	            return;
			}
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var strDetail1 = [];
					var d1={
						columnId:'t1.enterprise_no',
						value:cust[0].data.enterpriseNo
					};
					strDetail1.push(d1);
					var d2={
							columnId:'t1.warehouse_no',
							value:cust[0].data.warehouseNo
					};
					strDetail1.push(d2);
					var d3={
							columnId:'t1.owner_no',
							value:cust[0].data.ownerNo
						};
					strDetail1.push(d3);
					var d4={
							columnId:'t1.cost_no',
							value:cust[0].data.costNo
						};
					strDetail1.push(d4);	
					var d5={
							columnId:'t1.source_no',
							value:cust[0].data.sourceNo
						};
					strDetail1.push(d5);	
					var jsonDetail = Ext.encode(strDetail1);
					var params = {
							str  : jsonDetail,
							strCostDate:cust[0].data.costDate
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_OtherAction_deleteCostOtherList',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('costOtherListB203').getStore().reload();
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
	//查找
	otherListDetailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoUIB203').setValue(null);
		Ext.getCmp('costNoUIB203').setValue(null);
		queryModuleId='B203';
		queryGrid='costOtherListB203';
	},
	//导出
	exportClick:function(){
		commExport('costOtherListB203');
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('cost_OtherListAddOrEditWindow').close();
	},
	//实现上一页功能
	prev:function(){
		commonMenu5PrevOrNext('cost_OtherListB203','costOtherListB203',-1);
		this.loadDatabB203();
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('cost_OtherListB203','costOtherListB203',1);
		this.loadDatabB203();
	},
	
	//根据货主和仓别加载费用编码，并且查找相应的信息
	ownerNoUIB203Select:function(){
		//获取费用编码
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB203').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB203').getValue()) &&
				Ext.getCmp('ownerNoUIB203').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB203').getValue()) &&
				Ext.getCmp('ownerNoUIB203').getValue()!='ALL'){
			Ext.apply(Ext.getCmp('costNoUIB203').getStore().proxy.extraParams,str);
			Ext.getCmp('costNoUIB203').getStore().removeAll();
			Ext.getCmp('costNoUIB203').getStore().load();
		}else{
			Ext.getCmp('costNoUIB203').setValue(null);
			Ext.getCmp('costNoUIB203').getStore().removeAll();
		}
		
		//查询计费公式
		Ext.apply(Ext.getCmp('costOtherListB203').getStore().proxy.extraParams,str);
		Ext.getCmp('costOtherListB203').getStore().removeAll();
		Ext.getCmp('costOtherListB203').getStore().load();		
	},
	
	//根据货主、仓别和费用代码查找对应的杂项费用信息
	costNoUIB203Select:function(){
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB203').getValue()) &&
				Ext.getCmp('ownerNoUIB203').getValue()!='ALL'){
			var d1={
					columnId:'t1.owner_no',
					value:Ext.getCmp('ownerNoUIB203').getValue()
				};
				strDetail1.push(d1);
		}	
		var d3={
				columnId:'t1.cost_no',
				value:Ext.getCmp('costNoUIB203').getValue()
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		
		Ext.apply(Ext.getCmp('costOtherListB203').getStore().proxy.extraParams,str);
		Ext.getCmp('costOtherListB203').getStore().removeAll();
		Ext.getCmp('costOtherListB203').getStore().load();		
	},
	
	//加载修改页面的数据
	loadDatabB203:function (){
		var cust=Ext.getCmp('costOtherListB203').getSelectionModel().getSelection();
		if(cust.length>0)
		{	
			Ext.getCmp('ownerNoB203').setValue(cust[0].data.ownerNo);
			Ext.getCmp('costNoB203').setValue(cust[0].data.costNo);	
			var a=Ext.getCmp('costNoB203').getValue();
			Ext.getCmp('costDateB203').setValue(cust[0].data.costDateText);
			Ext.getCmp('costValueB203').setValue(cust[0].data.costValue);
			Ext.getCmp('costFlagB203').setValue(cust[0].data.costFlag);
			Ext.getCmp('costFlagB203').fireEvent("select");
			Ext.getCmp('statusB203').setValue(cust[0].data.status);
			Ext.getCmp('statusB203').fireEvent("select");
			Ext.getCmp('sourceNoB203').setValue(cust[0].data.sourceNo);
			Ext.getCmp('remarkB203').setValue(cust[0].data.otherCost1);
			Ext.getCmp('costValueB203').focus(false,4);
			
			var detail = [];
			var a={
					columnId:'t1.cost_no',
					value:cust[0].data.costNo
				};
			detail.push(a);
			
			var jsonDetail = Ext.encode(detail);
				
			var wheresql = {
				strQuery : jsonDetail
			};
			
			Ext.apply(Ext.getCmp('costOtherSetB203').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('costOtherSetB203').getStore().removeAll();
			Ext.getCmp('costOtherSetB203').getStore().load();
			if(cust[0].get('status')=='10'){
				commonSetMsterReadOnlyByArray(
						new Array('ownerNoB203','costNoB203','costDateB203','sourceNoB203','statusB203'),true);
				commonSetMsterReadOnlyByArray(
						new Array('costValueB203','costFlagB203'),false);
			}else{
				commonSetMsterReadOnlyByArray(
						new Array('ownerNoB203','costNoB203','costDateB203','costValueB203','sourceNoB203','costFlagB203','statusB203'),true);
			}
		}
	},
	cost_OtherListAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('costOtherListB203').getStore().load();
	},
	//加载其他费用修改页面的数据
	loadDatabB203_2:function (){
		var cust=Ext.getCmp('costOtherSetB203').getSelectionModel().getSelection();
		
		if(cust.length>0)
		{	
			Ext.getCmp('costNo_2').setValue(cust[0].data.costNo);
			Ext.getCmp('costName_2').setValue(cust[0].data.costName);	
			Ext.getCmp('costName_2').focus(false,2);
		}
	},
	cost_OtherListAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('costOtherListB203').getStore().load();
	},
	//其他费用信息列表数据选择
	costOtherSetB203Selectionchange:function(){
		if(typeB203=='add'){
			var cust=Ext.getCmp('costOtherSetB203').getSelectionModel().getSelection();
			if(cust.length>0)
			{	
				commonSetMsterReadOnlyByArray(
						new Array('ownerNoB203','costDateB203','costValueB203','sourceNoB203','costFlagB203'),false);
				Ext.getCmp('ownerNoB203').getStore().removeAll();
				Ext.getCmp('ownerNoB203').getStore().load();	
				Ext.getCmp('costNoB203').setValue(cust[0].data.costNo);
				Ext.getCmp('costDateB203').setValue(new Date());
				Ext.getCmp('costValueB203').focus(false,4);
				Ext.getCmp('costFlagB203').setValue('0');
				Ext.getCmp('statusB203').setValue('10');
			}
		}
	},
	//新增其他费用
	addB203Clike:function(){
		Ext.create('cms.view.cost.window.cost_OtherSetAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_OtherSetB203');
		typeB203_2='add';
		Ext.getCmp('costNo_2').focus(false,1);
		checkFlag='1';
	},
	//修改其他费用信息
	editB203Clike:function(){
		var data = Ext.getCmp('costOtherSetB203').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_OtherSetAddorEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB203_2='edit';
			updateCommMenu5('cost_OtherSetB203');
			commonMenu5PrevOrNext('cost_OtherSetB203','costOtherSetB203',0);
			commonSetMsterReadOnlyByArray(
				new Array('costNo_2'),true);
			this.loadDatabB203_2();
		}
	},
	//其他费用-删除
	deleteB203Clike:function(){
		var cust=Ext.getCmp('costOtherSetB203').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var params = {
							str  : null,
							strCostNo : cust[0].data.costNo
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_OtherAction_deleteCostOtherSet',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('costOtherSetB203').getStore().reload();
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
	//其他费用-实现上一页功能
	otherSetPrev:function(){
		commonMenu5PrevOrNext('cost_OtherSetB203','costOtherSetB203',-1);
		this.loadDatabB203_2();
	},
	
	//其他费用-实现下一页功能
	otherSetNext:function(){
		commonMenu5PrevOrNext('cost_OtherSetB203','costOtherSetB203',1);
		this.loadDatabB203_2();
	},
	//其他费用-窗口关闭前事件
	cost_OtherSetAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('costOtherSetB203').getStore().load();
	},
	//关闭窗口
	cost_OtherSetAddorEditWindowColse:function(){
		Ext.getCmp('cost_OtherSetAddorEditWindow').close();
	},
	otherSetDetailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='B203_2';
		queryGrid='costOtherSetB203';
	},
	otherSetSave:function(){
		otherSetSave();
	},
	//验证费用代码
	costNo_2Blur:function(){
		if(typeB203_2=='add'){
			if(checkFlag=='1'){
				Ext.Ajax.request({
					method:'post',
					url:'cost_OtherAction_checkCostNo',
					params:{
						strCostNo:Ext.getCmp('costNo_2').getValue()
				    },
				    success:function(response){
				    	var res = Ext.decode(response.responseText);
				    	if(res=='1'){
				    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingProjectIsExist);
				    		Ext.getCmp('costNo_2').setValue('');
				    		Ext.getCmp('costNo_2').focus();
				    	}
				    }
				});
			}
		}
	},
	//其他费用录入重新加载添加窗口
	otherSetadd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('costNo_2'),false);
		Ext.getCmp('costNo_2').setValue('');
		Ext.getCmp('costName_2').setValue('');
		Ext.getCmp('costNo_2').focus();
	}
});
/**
 * 设置弹出窗口按扭显示
 */
function setcost_MenuWidgetB203Tab1(){
	if(typeB203=='add')
	{
		Ext.getCmp('cost_OtherListB203').items.items[1].setVisible(false);
		Ext.getCmp('cost_OtherListB203').items.items[2].setVisible(false);
		Ext.getCmp('cost_OtherListB203').items.items[3].setVisible(false);
		Ext.getCmp('cost_OtherListB203').items.items[4].setVisible(true);
		Ext.getCmp('cost_OtherListB203').items.items[5].setVisible(true);
	}else if(typeB203=='edit')
	{
		Ext.getCmp('cost_OtherListB203').items.items[1].setVisible(true);
		Ext.getCmp('cost_OtherListB203').items.items[2].setVisible(true);
		Ext.getCmp('cost_OtherListB203').items.items[3].setVisible(false);
		Ext.getCmp('cost_OtherListB203').items.items[4].setVisible(true);
		Ext.getCmp('cost_OtherListB203').items.items[5].setVisible(true);
	}
	
};
//保存
function otherSetSave(){
	 
	if(Ext.getCmp('form_02_B203').getForm().isValid()){
		var cust=Ext.getCmp('costOtherSetB203').getSelectionModel().getSelection()[0];
		var group={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				costNo:Ext.getCmp('costNo_2').getValue()
			},
			costName:Ext.getCmp('costName_2').getValue(),
			rgstDate:typeB203_2=='add'?new Date():cust.data.rgstDate,
		    rgstName:typeB203_2=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
		    updtDate:typeB203_2=='add'?'':new Date(),
			updtName:typeB203_2=='add'?'':Ext.get('workerNo').getValue(),
			createFlag:typeB203_2=='add'?'0':cust.data.createFlag           
		};
		var str=Ext.encode(group);
		Ext.Ajax.request({
			url:'cost_OtherAction_saveCostOtherSet',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					commonSetMsterReadOnlyByArray(
							new Array('costNo_2'),true);
					Ext.getCmp('costNoB203').getStore().reload();
					checkFlag='0';
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	};
};
