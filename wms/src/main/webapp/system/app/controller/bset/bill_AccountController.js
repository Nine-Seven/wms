/**
 * 模块名称：科目和计费项目的关系维护UI
 * 模块编码：B301
 * 创建：chensr 
 */
rowindexB301=0;
typeB301='';
Ext.define('cms.controller.bset.bill_AccountController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bset.bill_AccountUI'
	          ],
	init:function(){
		this.control({//新增
			'bill_AccountUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			
			//判断科目编码是否唯一（用于选择填写编号判断）
			'bill_AccountAddOrEditWindow textfield[id=accountNoB301]':{
				blur:this.accountNoCheck
			},
			
			//判断科目编码是否唯一（用于选择货主下拉判断）
			'bill_AccountAddOrEditWindow combo[id=ownerNoB301]':{
				change:this.accountNoCheck
			},
			//重新加载添加窗口
			'bill_AccountAddOrEditWindow button[name=add]':{
				click:this.add
			},
				
			//用于判断值1、值2和优惠计费项目是否可以为空
			/*'bill_AccountAddOrEditWindow combo[id=discountFlagB301]':{
				change:this.valueIsNull
			},*/
			
			//保存科目信息
			'bill_AccountAddOrEditWindow button[name=save]':{
				click:this.save
			},
			
			//修改
			'bill_AccountUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			
			//浏览
			'bill_AccountUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//关闭窗口
			'bill_AccountAddOrEditWindow button[name=close]':{
				click:this.colse
			},
			//上一条记录
			'bill_AccountAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			
			//下一条记录
			'bill_AccountAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//根据货主和仓别加载科目，并且查找相应的信息
			'bill_AccountUI combo[id=ownerNoUIB301]':{
				change:this.selectAndGetAccount
			},
			
			//根据货主、仓别和科目查找对应的科目信息
			'bill_AccountUI combo[id=accountUIB301]':{
				select:this.getAccountWithCondition
			},
			
			//选择一行，显示未分配计费项目和计费项目与科目的关系
			'bill_AccountUI grid[id=account_MUIB301]':{	
				selectionchange:this.showRelaction
			},
			
			//添加科目和计费项目的关系
			'bill_AccountUI button[id=rightB301]':{
				click:this.rightB301
			},
			
			//删除科目和计费项目的关系
			'bill_AccountUI button[id=leftB301]':{
				click:this.leftB301
			},
			
			//优惠方式选择
			'bill_AccountAddOrEditWindow combo[id=discountFlagB301]':{
				select:this.discountFlagB301Select
			},
			
			//关闭前事件
			'bill_AccountAddOrEditWindow':{
				beforeclose:this.bill_AccountAddOrEditWindowBeforeclose
			},//TAB页切换
			'bill_AccountAddOrEditWindow tabpanel[id=tabB301]':
			{
				tabchange:this.tabchange
			},
			//阶梯选择
			'bill_AccountAddOrEditWindow grid[id=grid_B301_d3]':{
				selectionchange:this.grid_B301_d3Selectionchange
			},
			//验证优惠阶梯
			'bill_AccountAddOrEditWindow textfield[id=accountLadder_2]':{
				blur:this.accountLadderBlur
			},//阶梯优惠-优惠方式选择
			'bill_AccountAddOrEditWindow combo[id=discountFlagB301_2]':{
				select:this.discountFlagB301_2Select
			}
		});
	},
	
	//调用新增页面
	detailAdd:function(){
		Ext.create('cms.view.bset.window.bill_AccountAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('bill_AccountB301');
		typeB301='add'; 
	},
	
	//判断科目代码是否唯一
	accountNoCheck:function(){
		if(typeB301=='add' 		
		&& !Ext.isEmpty(Ext.getCmp('accountNoB301').getValue())
		&& !Ext.isEmpty(Ext.getCmp('ownerNoB301').getValue()) )
		{
			Ext.Ajax.request({
				url : 'bill_AccountAction_accountNoCheck',
				params : {
					str:Ext.getCmp('accountNoB301').getValue(),
					ownerNo:Ext.getCmp('ownerNoB301').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg('提示',"科目代码已经存在");
			    		Ext.getCmp('accountNoB301').setValue(null);
			    	}
				}
			});
		}
	},
	
	//根据优惠方式，判断值1、值2和优惠代码是否可以为空
	valueIsNull: function(){	
		var flag =Ext.getCmp('discountFlagB301').getValue();
		if(flag=='1')
		{
			/*Ext.getCmp('value1B301').allowBlank=false;
			Ext.getCmp('value2B301').allowBlank=true;
			Ext.getCmp('discountAccountNoB301').allowBlank=true;*/	
			
			if(Ext.isEmpty(Ext.getCmp('value1B301').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
		}else if (flag=='2')
		{
			if(Ext.isEmpty(Ext.getCmp('value1B301').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
		}else if (flag=='3')
		{
			/*Ext.getCmp('value1B301').allowBlank=false;
			Ext.getCmp('value2B301').allowBlank=false;
			Ext.getCmp('discountAccountNoB301').allowBlank=true;*/
			
			if(Ext.isEmpty(Ext.getCmp('value1B301').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('value2B301').getValue()))
			{
				Ext.example.msg('提示',"值2不能为空！");
				return false;
			}
			
		}else if(flag=='4')
		{
			/*Ext.getCmp('value1B301').allowBlank=false;
			Ext.getCmp('value2B301').allowBlank=true;
			Ext.getCmp('discountAccountNoB301').allowBlank=false;*/
			if(Ext.isEmpty(Ext.getCmp('value1B301').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('discountAccountNoB301').getValue()))
			{
				Ext.example.msg('提示',"优惠计费项目不能为空！");
				return false;
			}
		}else if(flag=='5'){
		/*	Ext.getCmp('value1B301').allowBlank=false;
			Ext.getCmp('value2B301').allowBlank=false;
			Ext.getCmp('discountAccountNoB301').allowBlank=false;	*/
			if(Ext.isEmpty(Ext.getCmp('value1B301').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('value2B301').getValue()))
			{
				Ext.example.msg('提示',"值2不能为空！");
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('discountAccountNoB301').getValue()))
			{
				Ext.example.msg('提示',"优惠计费项目不能为空！");
				return false;
			}
		}
		
		return true;
	},
	
	//实现新增功能（清空窗口的内容）
	add:function(){
		if(Ext.getCmp('tabB301').getActiveTab().id=="tabB301_1")
		{
			Ext.getCmp('IdFormB301').getForm().reset();
			bindEnterSkip($('IdFormB301'));//调用键盘处理方法
		}else if(Ext.getCmp('tabB301').getActiveTab().id=="tabB301_2")
		{
			commonSetMsterReadOnlyByArray(
					new Array('accountLadder_2'),false);
			Ext.getCmp('accountLadder_2').setValue('');
			Ext.getCmp('discountFlagB301_2').setValue('');
			Ext.getCmp('value1B301_2').setValue('');
			Ext.getCmp('value2B301_2').setValue('');
			Ext.getCmp('discountAccountNoB301_2').setValue('');
			Ext.getCmp('remarkB301_2').setValue('');
    		Ext.getCmp('accountLadder_2').focus();
		}
		typeB301='windowAdd'; 
	},
	
	//保存科目信息
	save: function(){
		debugger;
		if(!commonCheckIsInputAll('IdFormB301')){
			return;
		}
		if(!this.valueIsNull())
		{
			return;
		}
		
		if(Ext.getCmp('IdFormB301').getForm().isValid()){								
			var cust=Ext.getCmp('account_MUIB301').getSelectionModel().getSelection()[0];
			var bill_Account_MStr={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:Ext.getCmp('ownerNoB301').getValue(),
					accountNo:Ext.getCmp('accountNoB301').getValue()
				},	
				accountName:Ext.getCmp('accountNameB301').getValue(),
				accountType:Ext.getCmp('accountTypeB301').getValue(),
			
				discountFlag:Ext.getCmp('discountFlagB301').getValue(),
				value1:Ext.getCmp('value1B301').getValue(),
				value2:Ext.getCmp('value2B301').getValue(),
				discountAccountNo:Ext.getCmp('discountAccountNoB301').getValue(),					
				remark:Ext.getCmp('remarkB301').getValue(),
				rgstDate:typeB301=='add'?new Date():cust.data.rgstDate,
				rgstName:typeB301=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
				updtDate:typeB301=='add'?'':new Date(),
				updtName:typeB301=='add'?'':Ext.get('workerNo').getValue()
			};
			if(!Ext.isEmpty(Ext.getCmp('discountFlagB301_2').getValue())){
				var s = Ext.getCmp('discountFlagB301_2').getValue();
				if(s.length!=0){
					if(s.length>1){
						   var str = s;
						   var spstr = str.split("");
						   var discountFlag = spstr[1];
						   var bill_Account_Str={
									id:{
										warehouseNo:Ext.get('warehouseNo').getValue(),
										ownerNo:Ext.getCmp('ownerNoB301').getValue(),
										accountNo:Ext.getCmp('accountNoB301').getValue(),
										accountLadder:Ext.getCmp('accountLadder_2').getValue(),
										enterpriseNo:Ext.get('enterpriseNo').getValue()
									},	
									discountFlag:discountFlag,
									value1:Ext.getCmp('value1B301_2').getValue(),
									value2:Ext.getCmp('value2B301_2').getValue(),
									discountAccountNo:Ext.getCmp('discountAccountNoB301_2').getValue(),					
									remark:Ext.getCmp('remarkB301_2').getValue(),
									rgstDate:typeB301=='add'?new Date():cust.data.rgstDate,
									rgstName:typeB301=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
									updtDate:typeB301=='add'?'':new Date(),
									updtName:typeB301=='add'?'':Ext.get('workerNo').getValue()
								};
						}else{
							var bill_Account_Str={
									id:{
										warehouseNo:Ext.get('warehouseNo').getValue(),
										ownerNo:Ext.getCmp('ownerNoB301').getValue(),
										accountNo:Ext.getCmp('accountNoB301').getValue(),
										accountLadder:Ext.getCmp('accountLadder_2').getValue(),
										enterpriseNo:Ext.get('enterpriseNo').getValue()
									},	
									discountFlag:Ext.getCmp('discountFlagB301_2').getValue(),
									value1:Ext.getCmp('value1B301_2').getValue(),
									value2:Ext.getCmp('value2B301_2').getValue(),
									discountAccountNo:Ext.getCmp('discountAccountNoB301_2').getValue(),					
									remark:Ext.getCmp('remarkB301_2').getValue(),
									rgstDate:typeB301=='add'?new Date():cust.data.rgstDate,
									rgstName:typeB301=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
									updtDate:typeB301=='add'?'':new Date(),
									updtName:typeB301=='add'?'':Ext.get('workerNo').getValue()
								};
						}
				}
			}
			if(Ext.isEmpty(Ext.getCmp('accountLadder_2').getValue()) && 
					Ext.isEmpty(Ext.getCmp('discountFlagB301_2').getValue())){
				Ext.Ajax.request({
					url:'bill_AccountAction_saveAccount_m',
					method:'post',
					params:{
						str:Ext.encode(bill_Account_MStr)
					},
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}else if(!Ext.isEmpty(Ext.getCmp('accountLadder_2').getValue()) && 
					Ext.isEmpty(Ext.getCmp('discountFlagB301_2').getValue())){
				Ext.example.msg($i18n.prompt,$i18n_prompt.pleaseChoiceDiscountFlag);
				return;
			}else{
				Ext.Ajax.request({
					url:'bill_AccountAction_saveAccount_m',
					method:'post',
					params:{
						str:Ext.encode(bill_Account_MStr)
					},
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
				Ext.Ajax.request({
					url:'bill_AccountAction_saveAccount',
					method:'post',
					params:{
						str:Ext.encode(bill_Account_Str)
					},
					success:function(response){
						if(typeof(Ext.getCmp('grid_B301_d3'))!=='undefined'){
							Ext.getCmp('grid_B301_d3').store.reload();
						}
					}
				});	
			}
		} 
	},
	//修改科目信息
	detailEdit:function(){
		var data = Ext.getCmp('account_MUIB301').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.bset.window.bill_AccountAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB301='edit';
			updateCommMenu5('bill_AccountB301');
			commonMenu5PrevOrNext('bill_AccountB301','account_MUIB301',0);
			commonSetMsterReadOnlyByArray(
				new Array('ownerNoB301','accountTypeB301','accountNoB301','accountLadder_2'),true);
			this.loadDataB301();
		}
	},
	
	/**
	 * 浏览
	 */
	detailBrowse:function(){
		if(!Ext.isEmpty(Ext.getCmp('account_MUIB301'))){
			var data = Ext.getCmp('account_MUIB301').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.bset.window.bill_AccountAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				typeB301='browse';
				browseCommMenu5('bill_AccountB301');
				commonMenu5PrevOrNext('bill_AccountB301','account_MUIB301',0);
				commonSetFieldReadOnly('IdFormB301',true);
				commonSetMsterReadOnlyByArray(
						new Array('discountFlagB301','value1B301','value2B301',
								'discountAccountNoB301','remarkB301',
								'accountLadder_2','discountFlagB301_2',
								'value1B301_2','value2B301_2',
								'discountAccountNoB301_2','remarkB301_2'),true);
				this.loadDataB301();
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
	},	
	//关闭窗口
	colse:function(){
		Ext.getCmp('bill_AccountAddOrEditWindow').close();
	},
	//实现上一页功能
	prev:function(){
		commonMenu5PrevOrNext('bill_AccountB301','account_MUIB301',-1);
		this.loadDataB301();
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('bill_AccountB301','account_MUIB301',1);
		this.loadDataB301();
	},
	
	//根据货主和仓别加载科目，并且查找相应的信息
	selectAndGetAccount:function(){
		//获取科目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB301').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB301').getValue()) &&
				Ext.getCmp('ownerNoUIB301').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB301').getValue()) &&
				Ext.getCmp('ownerNoUIB301').getValue()!='ALL'){
			Ext.apply(Ext.getCmp('accountUIB301').getStore().proxy.extraParams,str);
			Ext.getCmp('accountUIB301').getStore().removeAll();
			Ext.getCmp('accountUIB301').getStore().load();
		}else{
			Ext.getCmp('accountUIB301').setValue(null);
			Ext.getCmp('accountUIB301').getStore().removeAll();
		}
		
		//查询计费公式
		Ext.apply(Ext.getCmp('account_MUIB301').getStore().proxy.extraParams,str);
		Ext.getCmp('account_MUIB301').getStore().removeAll();
		Ext.getCmp('account_MUIB301').getStore().load();		
	},
	
	//根据货主、仓别和科目查找对应的科目信息
	getAccountWithCondition:function(){
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB301').getValue()
		};
		strDetail1.push(d1);
		
/*		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.getCmp('warehouseUIB301').getValue()
		};
		strDetail1.push(d2);*/
		
		var d3={
				columnId:'t1.account_no',
				value:Ext.getCmp('accountUIB301').getValue()
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('account_MUIB301').getStore().proxy.extraParams,str);
		Ext.getCmp('account_MUIB301').getStore().removeAll();
		Ext.getCmp('account_MUIB301').getStore().load();		
	},
	
	//选择一行，根据货主和仓别显示未分配计费项目和计费项目与科目的关系
	showRelaction:function(){
		var data = Ext.getCmp('account_MUIB301').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.getCmp('grid_Formulaset_B301').getStore().removeAll();
			Ext.getCmp('grid_AccountRelaction_B301').getStore().removeAll();
		}else{
			//获取选择行的货主和仓别
			var strDetail1 = [];
			var strDetail2 = [];
			var d1={
				columnId:'t1.owner_no',
				value:data[0].get('ownerNo')
			};
			strDetail1.push(d1);
			strDetail2.push(d1);
			var d2={
					columnId:'t1.warehouse_no',
					value:data[0].get('warehouseNo')
			};
			strDetail1.push(d2);
			strDetail2.push(d2);
			var d4={
					columnId:'t1.status',
					value:0
			};
			strDetail2.push(d4);
			var jsonDetail = Ext.encode(strDetail1);
			var jsonDetai2 = Ext.encode(strDetail2);
			var str = {
					str  : jsonDetail,
					accountNo: data[0].get('accountNo')
			};	
			var str2 = {
					str  : jsonDetai2,
					accountNo: data[0].get('accountNo')
			};
			//获取为分配的计费项目
			Ext.apply(Ext.getCmp('grid_Formulaset_B301').getStore().proxy.extraParams,str2);
			Ext.getCmp('grid_Formulaset_B301').getStore().removeAll();
			Ext.getCmp('grid_Formulaset_B301').getStore().load();	
			
			//显示计费项目与科目的关系
			var d3={
					columnId:'t1.account_no',
					value:data[0].get('accountNo')
			};
			strDetail1.push(d3);
			jsonDetail = Ext.encode(strDetail1);
			str = {
					str  : jsonDetail
			};	
			
			Ext.apply(Ext.getCmp('grid_AccountRelaction_B301').getStore().proxy.extraParams,str);
			Ext.getCmp('grid_AccountRelaction_B301').getStore().removeAll();
			Ext.getCmp('grid_AccountRelaction_B301').getStore().load();	
		}
	},
	
	//添加科目和计费项目的关系
	rightB301:function(){
		var gridA = Ext.getCmp('account_MUIB301').getSelectionModel().getSelection();
		var gridF=Ext.getCmp('grid_Formulaset_B301').getSelectionModel().getSelection();
	
		if(gridA.length!=0){
			if(gridF.length!=0){
				var detail=[];
				for(var i=0;i<gridF.length;i++){
					var account_D={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:gridA[0].get('warehouseNo'),
							ownerNo:gridA[0].get('ownerNo'),
							accountNo:gridA[0].get('accountNo'),
							billingProject:gridF[i].get('billingProject')
						},
						rgstName:Ext.get('workerNo').getValue(),
						rgstDate:new Date()
					};
				detail.push(account_D);
				}
				var str=Ext.encode(detail);
				Ext.Ajax.request({
					url:'bill_AccountAction_saveAccount_D',
					params:{
						str:str
					},
					success:function(response){
						Ext.getCmp('grid_Formulaset_B301').getStore().load();
						Ext.getCmp('grid_AccountRelaction_B301').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.select_bill_formulaset);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows);
		}		
	},
	
	//删除科目和计费项目的关系
	leftB301:function(){
		var gridA = Ext.getCmp('account_MUIB301').getSelectionModel().getSelection();
		var gridD =Ext.getCmp('grid_AccountRelaction_B301').getSelectionModel().getSelection();
		if(gridA.length!=0){
			if(gridD.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				var detail4=[];
				for(var i=0;i<gridD.length;i++){
					detail1.push(gridD[i].get('warehouseNo'));
					detail2.push(gridD[i].get('ownerNo'));
					detail3.push(gridD[i].get('accountNo'));
					detail4.push(gridD[i].get('billingProject'));	
				}
				
				Ext.Ajax.request({
					url:'bill_AccountAction_deleteAccount_D',
					params:{
						warehouseNo:detail1,
						ownerNo:detail2,
						accountNo:detail3,
						billingProject:detail4
					},
					success:function(response){
						Ext.getCmp('grid_Formulaset_B301').getStore().load();
						Ext.getCmp('grid_AccountRelaction_B301').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.select_bill_account_formulaset_relation);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows);
		}
	},

	//优惠方式选择
	discountFlagB301Select:function()
	{
		//清空
		Ext.getCmp('value1B301').setValue('');
		Ext.getCmp('value2B301').setValue('');
		Ext.getCmp('discountAccountNoB301').setValue('');
		//设置只读属性
		if(Ext.getCmp('discountFlagB301').getValue()=='0')
		{
			Ext.getCmp('value1B301').setDisabled(true);
			Ext.getCmp('value2B301').setDisabled(true);
			Ext.getCmp('discountAccountNoB301').setDisabled(true);
		}else if(Ext.getCmp('discountFlagB301').getValue()=='1')
		{
			Ext.getCmp('value1B301').setDisabled(false);
			Ext.getCmp('value2B301').setDisabled(true);
			Ext.getCmp('discountAccountNoB301').setDisabled(true);			
		}else if(Ext.getCmp('discountFlagB301').getValue()=='2')
		{
			Ext.getCmp('value1B301').setDisabled(false);
			Ext.getCmp('value2B301').setDisabled(false);
			Ext.getCmp('discountAccountNoB301').setDisabled(true);			
		}else if(Ext.getCmp('discountFlagB301').getValue()=='3')
		{
			Ext.getCmp('value1B301').setDisabled(false);
			Ext.getCmp('value2B301').setDisabled(false);
			Ext.getCmp('discountAccountNoB301').setDisabled(true);			
		}
		else if(Ext.getCmp('discountFlagB301').getValue()=='4')
		{
			Ext.getCmp('value1B301').setDisabled(false);
			Ext.getCmp('value2B301').setDisabled(true);
			Ext.getCmp('discountAccountNoB301').setDisabled(false);			
		}else if(Ext.getCmp('discountFlagB301').getValue()=='5')
		{
			Ext.getCmp('value1B301').setDisabled(false);
			Ext.getCmp('value2B301').setDisabled(false);
			Ext.getCmp('discountAccountNoB301').setDisabled(false);			
		}
	},
	
	//加载修改页面的数据
	loadDataB301:function (){
		var cust=Ext.getCmp('account_MUIB301').getSelectionModel().getSelection();
		
		if(cust.length>0)
		{	
			Ext.getCmp('ownerNoB301').setValue(cust[0].data.ownerNo);
			Ext.getCmp('accountNoB301').setValue(cust[0].data.accountNo);	
			Ext.getCmp('accountNameB301').setValue(cust[0].data.accountName);
			Ext.getCmp('accountTypeB301').setValue(cust[0].data.accountType);
			//Ext.getCmp('cycleB301').setValue(cust[0].data.cycle),
			//Ext.getCmp('nextAccountDateB301').setValue(cust[0].data.nextAccountDate),
			Ext.getCmp('discountFlagB301').setValue(cust[0].data.discountFlag);
			Ext.getCmp('discountFlagB301').fireEvent('select');
			Ext.getCmp('value1B301').setValue(cust[0].data.value1);
			Ext.getCmp('value2B301').setValue(cust[0].data.value2);	
			Ext.getCmp('discountAccountNoB301').setValue(cust[0].data.discountAccountNo);
			Ext.getCmp('remarkB301').setValue(cust[0].data.remark);
			var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.account_no',
				value:cust[0].data.accountNo
			};
			listDetail1.push(strDtl);
			var params={
					str:Ext.encode(listDetail1)
				};
			Ext.apply(Ext.getCmp('grid_B301_d3').getStore().proxy.extraParams,params);
			Ext.getCmp('grid_B301_d3').getStore().removeAll();
			Ext.getCmp('grid_B301_d3').getStore().load();
		}
		
	},
	bill_AccountAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('account_MUIB301').getStore().load();
	},
	tabchange:function(tab)
	{
		if(typeB301=='add')
		{
			Ext.getCmp('bill_AccountB301').items.items[1].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[2].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[3].setVisible(true);
			Ext.getCmp('bill_AccountB301').items.items[4].setVisible(true);
			Ext.getCmp('bill_AccountB301').items.items[5].setVisible(true);
			Ext.getCmp('grid_B301_d3').getStore().removeAll();
		}else if(typeB301=='edit')
		{
			Ext.getCmp('bill_AccountB301').items.items[1].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[2].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[3].setVisible(true);
			Ext.getCmp('bill_AccountB301').items.items[4].setVisible(true);
			Ext.getCmp('bill_AccountB301').items.items[5].setVisible(true);
		}else if(typeB301=='browse')
		{
			Ext.getCmp('bill_AccountB301').items.items[1].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[2].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[3].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[4].setVisible(false);
			Ext.getCmp('bill_AccountB301').items.items[5].setVisible(true);
		}
	},
	//优惠阶梯选择
	grid_B301_d3Selectionchange:function(){
		
		var rec=Ext.getCmp('grid_B301_d3').getSelectionModel().getSelection();
		if(rec.length>0)
		{	
			Ext.getCmp('accountLadder_2').setValue(rec[0].data.accountLadder);
			Ext.getCmp('discountFlagB301_2').setValue(rec[0].data.discountFlag);
			Ext.getCmp('discountFlagB301_2').fireEvent('select');
			Ext.getCmp('value1B301_2').setValue(rec[0].data.value1);
			Ext.getCmp('value2B301_2').setValue(rec[0].data.value2);	
			Ext.getCmp('discountAccountNoB301_2').setValue(rec[0].data.discountAccountNo);
			Ext.getCmp('remarkB301_2').setValue(rec[0].data.remark);
		}
	},
	//验证优惠阶梯
	accountLadderBlur:function(editor,e,eOpts){
		if(typeB301=='browse' || typeB301=='edit'){
			return
		}else if(typeB301=='windowAdd'){
			var gridcount=Ext.getCmp('grid_B301_d3').getStore().getCount();
	    	for(var i=0;i<gridcount;i++){
	    		var exp=Ext.getCmp('grid_B301_d3').getStore().getAt(i);    		
	    		if(exp.get('accountLadder')==Ext.getCmp('accountLadder_2').getValue()){
	    			Ext.example.msg($i18n.prompt,$i18n_prompt.accountLadderAlreadyExists);
		    		Ext.getCmp('accountLadder_2').setValue('');
		    		Ext.getCmp('accountLadder_2').focus();
	    		}
	    	}
		}
	},
	//阶梯优惠—优惠方式选择
	discountFlagB301_2Select:function()
	{
//		alert(Ext.getCmp('discountFlagB301_2').getValue());
		var s = Ext.getCmp('discountFlagB301_2').getValue();
		if(s.length>1){
		   var str = s;
		   var spstr = str.split("");
		   var discountFlag = spstr[1];
		}else{
		   var discountFlag = Ext.getCmp('discountFlagB301_2').getValue();  
		}
		//清空
		Ext.getCmp('value1B301_2').setValue('');
		Ext.getCmp('value2B301_2').setValue('');
		Ext.getCmp('discountAccountNoB301_2').setValue('');
		//设置只读属性
		if(discountFlag=='1' ||
				Ext.getCmp('discountFlagB301_2').getValue()=='2' ||
				Ext.getCmp('discountFlagB301_2').getValue()=='5')
		{
			Ext.getCmp('value1B301_2').setDisabled(false);
			Ext.getCmp('value2B301_2').setDisabled(false);
			Ext.getCmp('discountAccountNoB301_2').setDisabled(true);
		}else if(discountFlag=='3')
		{
			Ext.getCmp('value1B301_2').setDisabled(false);
			Ext.getCmp('value2B301_2').setDisabled(true);
			Ext.getCmp('discountAccountNoB301_2').setDisabled(false);			
		}else if(discountFlag=='4')
		{
			Ext.getCmp('value1B301_2').setDisabled(false);
			Ext.getCmp('value2B301_2').setDisabled(false);
			Ext.getCmp('discountAccountNoB301_2').setDisabled(true);			
		}
	}
});
