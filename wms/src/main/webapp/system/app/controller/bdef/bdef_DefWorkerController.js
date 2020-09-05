var rowindex1101=0;
var g_strType1101='add';
var g_intSaveType1401=0;//0=新增  1=修改 2=查看  ---hj
var saveFlag1=0;		//该标志位用于控制保存员工信息时弹出de提示
var saveFlag2=0;
var saveFlag3=0;
var saveFlag4=0;

Ext.define('cms.controller.bdef.bdef_DefWorkerController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_DefWorkerUI'],
	init:function(){
		this.control({//用户管理》新增---hj
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid1Menu1101] button[name=detailAdd]':{
				click:this.detailAdd
			},//用户管理》修改---hj
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid1Menu1101] button[name=detailEdit]':{
				click:this.detailEdit
			},//用户管理》浏览
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid1Menu1101] button[name=detailBrowse]':{
				click:this.detailBrowse
			},//用户管理》删除
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid1Menu1101] button[name=detailDelete]':{
				click:this.detailDelete
			},//用户管理》查询
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid1Menu1101] button[name=detailQuery]':{
				click:this.detailQuery
			},//用户管理》上一条
			'bdef_DefWorkerAddOrEditWindow button[name=prev]':{
				click:this.prevBdef_DefWorker
			},//用户管理》下一条
			'bdef_DefWorkerAddOrEditWindow button[name=next]':{
				click:this.nextBdef_DefWorker
			},//用户管理》保存
			'bdef_DefWorkerAddOrEditWindow button[name=save]':{
				click:this.saveBdef_DefWorker
			},//用户管理》员工代码是否存在的判断---hj
			'bdef_DefWorkerAddOrEditWindow textfield[id=txtWorkerName1101]':{
				blur:this.txtWorker_no1101Blur
			},//用户管理》新增
			'bdef_DefWorkerAddOrEditWindow button[name=add]':{
				click:this.addBdef_DefWorker
			},//用户管理》关闭
			'bdef_DefWorkerAddOrEditWindow button[name=close]':{
				click:this.closeBdef_DefWorker
			},//角色管理》新增
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid2Menu1101] button[name=detailAdd]':{
				click:this.detailAddRole
			},//角色管理》修改
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid2Menu1101] button[name=detailEdit]':{
				click:this.detailEditRole
			},//角色管理》浏览
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid2Menu1101] button[name=detailBrowse]':{
				click:this.detailBrowseRole
			},//角色管理》查询
			'bdef_DefWorkerUI commMenuWidget2[id=tab1Grid2Menu1101] button[name=detailQuery]':{
				click:this.detailQueryRole
			},//角色管理》上一条
			'bset_Role_ListAddOrEditWindow button[name=prev]':{
				click:this.prevBset_Role_List
			},//角色管理》下一条
			'bset_Role_ListAddOrEditWindow button[name=next]':{
				click:this.nextBset_Role_List
			},//角色管理》保存
			'bset_Role_ListAddOrEditWindow button[name=save]':{
				click:this.saveBset_Role_List
			},
			'bset_Role_ListAddOrEditWindow button[name=saveAndNext]':{
				click:this.saveAndNextBset_Role_List
			},
			'bset_Role_ListAddOrEditWindow button[name=add]':{
				click:this.addBset_Role_List
			},
			'bset_Role_ListAddOrEditWindow button[name=saveAndClose]':{
				click:this.saveAndCloseBset_Role_List
			},//角色管理》关闭
			'bset_Role_ListAddOrEditWindow button[name=close]':{
				click:this.closeBset_Role_List
			},//角色管理》角色选择刷新用户信息
			'bdef_DefWorkerUI grid[id=bset_Role_ListGrid2]':{
				//beforedeselect:this.bset_Role_ListGrid2beforedeselect,
				selectionchange:this.bset_Role_ListGrid2selectionchange
			},
			//用户角色指定》保存
			'bdef_DefWorkerUI button[id=userRoleSave1101]':{
				click:this.userRoleSave
			},
			//角色权限指定》模块菜单选择
			'bdef_DefWorkerUI treepanel[id=moduleTree1101]':{
				selectionchange:this.moduleselectionchange
			},//角色权限指定》角色选择
			'bdef_DefWorkerUI grid[id=roleListGrid3]':{
				selectionchange:this.roleselectionchange
			},//角色权限指定》保存
			'bdef_DefWorkerUI button[id=roleModuleSave1101]':{
				click:this.roleModuleSave1101
			},//权限管理》tab切换
			'bdef_DefWorkerUI tabpanel[id=tabPId1101]':{
				tabchange:this.tabPId1101Tabchange
			},//window窗口tab切换---hj
			'bdef_DefWorkerAddOrEditWindow tabpanel[id=tabPid1101]':
			{
				tabchange:this.tabchange
			},//用户角色指定tab页，员工代码模糊查询     8-15
			'bdef_DefWorkerUI textfield[id=workerNo1101]':{
				//focus:this.divideNo3915Focus,
				keypress:this.workerNo1101Keypress
			},//刷新按钮    8-15
			'bdef_DefWorkerUI button[id=refresh1101]':{
				click:this.refreshWorker1101
			}
		});
	},
	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('tab1Grid1Menu1101').items.items[3].setVisible(false);
		Ext.getCmp('tab1Grid2Menu1101').items.items[3].setVisible(false);
		Ext.getCmp('tab1Grid2Menu1101').items.items[4].setVisible(false);
	},
	
	//刷新按钮    8-15
	refreshWorker1101:function(){
		var objdata = Ext.getCmp('bset_Role_ListGrid2').getSelectionModel().getSelection();
		
		var strWhereSql={
				strWorkerNo:'',
				strRoleId:objdata[0].data.roleId
			};
		Ext.apply(Ext.getCmp('bset_User_Role_Grid').getStore().proxy.extraParams,strWhereSql);
		Ext.getCmp('bset_User_Role_Grid').getStore().removeAll();
		Ext.getCmp('bset_User_Role_Grid').getStore().load();
	},
	
	//8-15     用户角色指定tab页，员工代码模糊查询 
	workerNo1101Keypress:function(th,e,eOpts){
		if(e.keyCode == 13){
			debugger;
			var workerNoTest = Ext.getCmp('workerNo1101').getValue();  //获得员工代码
			var objdata = Ext.getCmp('bset_Role_ListGrid2').getSelectionModel().getSelection();
			
			var strWhereSql={
					strWorkerNo:workerNoTest,
					strRoleId:objdata[0].data.roleId
				};
			Ext.apply(Ext.getCmp('bset_User_Role_Grid').getStore().proxy.extraParams,strWhereSql);
			Ext.getCmp('bset_User_Role_Grid').getStore().removeAll();
			Ext.getCmp('bset_User_Role_Grid').getStore().load();
		}
	},
	
	//window窗口tab切换---hj
	tabchange:function(tab)
	{
		//alert('tabchange');
		if(tab.getActiveTab().id=="tabPid1101_02")
		{
			setbdef_MenuWidget1401Tab2();
		}else if(tab.getActiveTab().id=="tabPid1101_03")
		{
			setbdef_MenuWidget1401Tab3();
		}
		else if(tab.getActiveTab().id=="tabPid1101_04")
		{
			setbdef_MenuWidget1401Tab4();
			//Ext.getCmp('numPackingQty1401_d2').focus(false, 10);
		}else if(tab.getActiveTab().id=="tabPid1101_01"){
			setbdef_MenuWidget1401Tab1();
		}
		
		var data = Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			var strWorkerNo=Ext.getCmp('txtWorkerName1101').getValue();
			var strWhereSql={
					strWhereSql:strWorkerNo
				};
			if(tab.getActiveTab().id=="tabPid1101_02")
			{
				//对应window6-30修改的
				Ext.apply(Ext.getCmp('bset_Role_ListGrid21').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('bset_Role_ListGrid21').getStore().removeAll();
				Ext.getCmp('bset_Role_ListGrid21').getStore().load();
				setbdef_MenuWidget1401Tab2();
			}else if(tab.getActiveTab().id=="tabPid1101_03")
			{				
				Ext.apply(Ext.getCmp('grid_03_1101').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('grid_03_1101').getStore().removeAll();
				Ext.getCmp('grid_03_1101').getStore().load();
				setbdef_MenuWidget1401Tab3();
			}
			else if(tab.getActiveTab().id=="tabPid1101_04")
			{
				Ext.apply(Ext.getCmp('grid_04_1101').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('grid_04_1101').getStore().removeAll();
				Ext.getCmp('grid_04_1101').getStore().load();
				setbdef_MenuWidget1401Tab4();
			}else if(tab.getActiveTab().id=="tabPId_1401_d3")
			{
				var strSql={
					strArticleNo:strArticleNo,
					strOwnerNo:Ext.getCmp('cmbOwnerNo1401').getValue()
				};
				Ext.apply(Ext.getCmp('grid_01_1401_d3').getStore().proxy.extraParams,strSql);
				Ext.getCmp('grid_01_1401_d3').getStore().removeAll();
				Ext.getCmp('grid_01_1401_d3').getStore().load();
			}
		}else{
			return;					
		}
	},
	
	//新增员工---hj
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_DefWorkerAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
		g_intSaveType1401 = 0;  //把保存模式改为0  0=新增  1=修改 2=查看 ---hj
		//workerAdd1401();		//打开窗口时加载   ---hj
		
		this.addBdef_DefWorker();
		addCommMenu5('menuWidget51101');
		bindEnterSkip($('#formBdef_DefWorker1101'));//调用键盘处理方法
		setbdef_MenuWidget1401Tab1();
		g_strType1101='add';
	},
	//编辑员工---hj
	detailEdit:function(){	
		var data = Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if (data.length != 0) {  
			Ext.create('cms.view.bdef.window.bdef_DefWorkerAddOrEditWindow',{
				title:$i18n.titleupdate
			}).show();
			g_strType1101='update';
			g_intSaveType1401=1;//把保存模式改为1 0=新增  1=修改 2=查看   ---hj
			g_intRowIndex1401=data[0].index;
			loadWorkerData1401();		//加载员工基本信息
			commonSetFieldReadOnly('formBdef_DefWorker1101',true);
			
			updateCommMenu5('menuWidget51101');
			commonMenu5PrevOrNext('menuWidget51101','gridBdefDefWorker1101',0);
			/*commonSetMsterReadOnlyByArray(
			new Array('txtWorker_no1101'),true)*/;
			commonSetMsterReadOnlyByArray(
					new Array('txtWorkerName1101'),true);
			
			//this.loadBdef_DefWorker();
        }else{
        	Ext.example.msg($i18n.prompt,'请选择一条数据修改！');
        } 
	},
	detailBrowse:function(){	
		//debugger;
		var data = Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.create('cms.view.bdef.window.bdef_DefWorkerAddOrEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
			g_strType1D01='browse';
			g_intSaveType1401 = 2;   //查看---hj
			commonSetFormReadOnly('formBdef_DefWorker1101',true);
			commonSetFormReadOnly('formBdef_DefWorker1102',true);
			Ext.getCmp('grid_04_1101').disabled = true;
			Ext.getCmp('grid_03_1101').disabled = true;
			Ext.getCmp('bset_Role_ListGrid21').disabled = true;
			
			commonMenu5PrevOrNext('menuWidget51101','gridBdefDefWorker1101',0);
			browseCommMenu5('menuWidget51101');
			this.loadBdef_DefWorker();
			//setbdef_MenuWidget1401Tab1();
			
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
	},
	detailDelete:function(){	
		var data = Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.Msg.confirm("提示", "确定删除数据？", function(button, text) {
						if (button == 'yes') {
							Ext.Ajax.request({
										url : 'authAction_deleteUser.action',
										params : {
											'userId' : data[0].get('userId')
										},
										success : function(response) {
										    Ext.getCmp('gridBdefDefWorker1101').getStore().load();
										}
									});
						}
					});
		} else {
			Ext.example.msg($i18n.prompt, '请先选择您要删除的行');
		}
	},
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=1101;
		queryGrid='gridBdefDefWorker1101';	
	},
	detailRoleDelete:function(){	
		var data = Ext.getCmp('roleListGrid').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.Msg.confirm("提示", "确定删除数据？", function(button, text) {
						if (button == 'yes') {
							Ext.Ajax.request({
										url : 'authAction_deleteRole.action',
										params : {
											'qroleId' : data[0].get('roleId')
										},
										success : function(response) {
											Ext.getCmp('roleListGrid')
													.getStore().load();
										}
									});
						}
					});
		} else {
			Ext.example.msg($i18n.prompt, '请先选择您要删除的行');
		}
	},
	txtWorker_no1101Blur:function(th){
		//alert('blur触发');
		//if(g_strType1101=='add'){
		if(g_intSaveType1401=='0'){		//新增的时候
			//alert('=0');
			var detail1 = [];
			var d={
				columnId:'a.worker_No',
				value:th.getValue()
			};
			detail1.push(d);
			var d={
					columnId:'a.enterprise_no',
					value:Ext.get('enterpriseNo').getValue()
				};
			detail1.push(d);
			if(!Ext.isEmpty(th.getValue())){
				Ext.Ajax.request({
				url:'bdef_DefWorkerAction_existsWorkerNo.action',
				params : {
					text:Ext.encode(detail1)
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,$i18n[data.msg]);
						Ext.getCmp('txtWorkerName1101').setValue('');
						Ext.getCmp('txtWorkerName1101').focus();
					}
				}
			});
			}	
		}		
	},
	prevBdef_DefWorker:function(th)
	{
		commonMenu5PrevOrNext('menuWidget51101','gridBdefDefWorker1101',-1);
		//this.loadBdef_DefWorker();
		loadWorkerData1401();
		//7-6添加  hj
		var data = Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			var strWorkerNo=Ext.getCmp('txtWorkerName1101').getValue();
			var strWhereSql={
					strWhereSql:strWorkerNo
				};
				//对应window6-30修改的
				Ext.apply(Ext.getCmp('bset_Role_ListGrid21').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('bset_Role_ListGrid21').getStore().removeAll();
				Ext.getCmp('bset_Role_ListGrid21').getStore().load();
				setbdef_MenuWidget1401Tab2();
				Ext.apply(Ext.getCmp('grid_03_1101').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('grid_03_1101').getStore().removeAll();
				Ext.getCmp('grid_03_1101').getStore().load();
				setbdef_MenuWidget1401Tab3();
				Ext.apply(Ext.getCmp('grid_04_1101').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('grid_04_1101').getStore().removeAll();
				Ext.getCmp('grid_04_1101').getStore().load();
				setbdef_MenuWidget1401Tab4();
			}
	},
	nextBdef_DefWorker:function(th)
	{
		//debugger;
		commonMenu5PrevOrNext('menuWidget51101','gridBdefDefWorker1101',1);
		//this.loadBdef_DefWorker();
		loadWorkerData1401();
		//7-6添加  hj
		var data = Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			var strWorkerNo=Ext.getCmp('txtWorkerName1101').getValue();
			var strWhereSql={
					strWhereSql:strWorkerNo
				};
				//对应window6-30修改的
				Ext.apply(Ext.getCmp('bset_Role_ListGrid21').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('bset_Role_ListGrid21').getStore().removeAll();
				Ext.getCmp('bset_Role_ListGrid21').getStore().load();
				setbdef_MenuWidget1401Tab2();
				Ext.apply(Ext.getCmp('grid_03_1101').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('grid_03_1101').getStore().removeAll();
				Ext.getCmp('grid_03_1101').getStore().load();
				setbdef_MenuWidget1401Tab3();
				Ext.apply(Ext.getCmp('grid_04_1101').getStore().proxy.extraParams,strWhereSql);
				Ext.getCmp('grid_04_1101').getStore().removeAll();
				Ext.getCmp('grid_04_1101').getStore().load();
				setbdef_MenuWidget1401Tab4();
			}
	},
	saveBdef_DefWorker:function(){
		//debugger
		if(Ext.getCmp('tabPid1101').getActiveTab().id=="tabPid1101_02")
		{
			if(!Ext.getCmp('formBdef_DefWorker1102').getForm().isValid()){
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.saveWorker);
			}else{
				saveWorker1101();//保存基本信息
				saveRole1101(); //保存角色
				//提示框判断
				if(saveFlag1==1 && saveFlag2==1){
					Ext.example.msg($i18n.prompt,'保存成功！');
					Ext.getCmp('gridBdefDefWorker1101').getStore().load();
					//关闭window
					Ext.getCmp('bdef_DefWorkerAddOrEditWindow').close();
					/*commonSetFormReadOnly('formBdef_DefWorker1101',true);
					commonSetFormReadOnly('formBdef_DefWorker1102',true);
					Ext.getCmp('grid_04_1101').disabled = true;	//没起作用
					Ext.getCmp('grid_03_1101').disabled = true;
					Ext.getCmp('bset_Role_ListGrid21').disabled = true;*/
				}else{
					Ext.example.msg($i18n.prompt,'保存失败！');
				}
			}
		}else if(Ext.getCmp('tabPid1101').getActiveTab().id=="tabPid1101_03")
		{
			if(!Ext.getCmp('formBdef_DefWorker1102').getForm().isValid()){
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.saveWorker);
			}else{
				saveWorker1101();//保存基本信息
				saveRole1101(); //保存角色
				saveOwner1101();//保存货主
				//提示框判断
				if(saveFlag1==1 && saveFlag2==1 && saveFlag3==1){
					Ext.example.msg($i18n.prompt,'保存成功！');
					Ext.getCmp('gridBdefDefWorker1101').getStore().load();
					//关闭window
					Ext.getCmp('bdef_DefWorkerAddOrEditWindow').close();
					/*commonSetFormReadOnly('formBdef_DefWorker1101',true);
					commonSetFormReadOnly('formBdef_DefWorker1102',true);
					Ext.getCmp('grid_04_1101').disabled = true;
					Ext.getCmp('grid_03_1101').disabled = true;
					Ext.getCmp('bset_Role_ListGrid21').disabled = true;*/
				}else{
					Ext.example.msg($i18n.prompt,'保存失败！');
				}
			}
		}else if(Ext.getCmp('tabPid1101').getActiveTab().id=="tabPid1101_04")
		{
			if(!Ext.getCmp('formBdef_DefWorker1102').getForm().isValid()){
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.saveWorker);
			}else{
				saveWorker1101();//保存基本信息
				saveRole1101(); //保存角色
				saveOwner1101();//保存货主
				saveLoc1101();//保存仓别
				//提示框判断
				if(saveFlag1==1 && saveFlag2==1 && saveFlag3==1 && saveFlag4==1){
					Ext.example.msg($i18n.prompt,'保存成功！');
					Ext.getCmp('gridBdefDefWorker1101').getStore().load();
					//关闭window
					Ext.getCmp('bdef_DefWorkerAddOrEditWindow').close();
					/*commonSetFormReadOnly('formBdef_DefWorker1101',true);
					commonSetFormReadOnly('formBdef_DefWorker1102',true);
					Ext.getCmp('grid_04_1101').disabled = true;
					Ext.getCmp('grid_03_1101').disabled = true;
					Ext.getCmp('bset_Role_ListGrid21').disabled = true;*/
				}else{
					Ext.example.msg($i18n.prompt,'保存失败！');
				}
			}
		}else{
			if(!Ext.getCmp('formBdef_DefWorker1102').getForm().isValid()){
				Ext.example.msg($i18n_prompt.prompt,'请输入必填项信息！');
			}else{
				saveWorker1101();//保存基本信息
				//提示框判断
				if(saveFlag1==1){
					Ext.example.msg($i18n.prompt,'保存成功！');
					Ext.getCmp('gridBdefDefWorker1101').getStore().load();
					//关闭window
					Ext.getCmp('bdef_DefWorkerAddOrEditWindow').close();
					/*commonSetFormReadOnly('formBdef_DefWorker1101',true);
					commonSetFormReadOnly('formBdef_DefWorker1102',true);
					Ext.getCmp('grid_04_1101').disabled = true;
					Ext.getCmp('grid_03_1101').disabled = true;
					Ext.getCmp('bset_Role_ListGrid21').disabled = true;*/
				}else{
					Ext.example.msg($i18n.prompt,'保存失败！');
				}
			}
		}
	},
	addBdef_DefWorker:function(){
		Ext.getCmp('formBdef_DefWorker1101').getForm().reset();
		Ext.getCmp('txtWorkerAlias1407').setValue('1');		//页面加载选中
		Ext.getCmp('txtWorkerAlias1406').setValue('1');		
		Ext.getCmp('txtWorkerName1101').focus(false, 10);
		//Ext.getCmp('cmbSex1101').setValue('0');
		//Ext.getCmp('cmbAuthorityType1101').setValue('1');
		//Ext.getCmp('cmbStatus1101').setValue('1');
	},
	closeBdef_DefWorker:function(){
		Ext.getCmp('bdef_DefWorkerAddOrEditWindow').close();
	},
	//角色管理
	/**
	 * 新增角色
	 */
	detailRoleAdd:function(){
		if(typeof(Ext.getCmp('roleListAddOrEditWindow'))=='undefined')
		{
			Ext.create('cms.view.bset.window.roleListAddOrEditWindow',{
				title:'新增角色资料'
			}).show();					
			Ext.getCmp("roleId1101").setValue('保存时自动生成');
		}
	},
	/**
	 * 角色，上一条记录
	 * @param {} th
	 */
	prevBset_Role_List:function(th){
		rowindex1101=rowindex1101-1;
		loadBset_Role_List(rowindex1101);
		commonSetCommMenu5PrevOrNext('menuWidget51101','bset_Role_ListGrid',rowindex1101);
	},
	/**
	 * 角色，下一条记录
	 * @param {} th
	 */
	nextBset_Role_List:function(th){
		rowindex1101=rowindex1101+1;
		loadBset_Role_List(rowindex1101);
		commonSetCommMenu5PrevOrNext('menuWidget51101','bset_Role_ListGrid',rowindex1101);
	},
	/**
	 * 角色，保存并上一条记录
	 * @param {} th
	 */
	saveBset_Role_List:function(th){
		saveBset_Role_List(th);
	},
	/**
	 * 角色，保存并下一条记录
	 * @param {} th
	 */
	saveAndNextBset_Role_List:function(th){
		saveBset_Role_List(th);
	},
	/**
	 * 角色，保存并新增
	 * @param {} th
	 */
	addBset_Role_List:function(th){
		addBset_Role_List();
	},
	/**
	 * 角色，保存并关闭
	 * @param {} th
	 */
	saveAndCloseBset_Role_List:function(th){
		saveBset_Role_List(th);
	},
	/**
	 * 角色，关闭
	 * @param {} th
	 */
	closeBset_Role_List:function(){
		closeBset_Role_List();
	},
	saveRoleListAddOrEditWindow:function(){
		if(Ext.getCmp('roleListAddOrEditForm').getForm().isValid()){	
			var roleId= Ext.getCmp('roleId1101').getValue();		
			var roleName= Ext.String.trim(Ext.getCmp('roleName1101').getValue());
			var notes= Ext.String.trim(Ext.getCmp('notes1101').getValue());
			var detail1={
					roleId:roleId,
					roleName:roleName,
					notes:notes
			};
			if(detail1.roleId=='保存时自动生成'){
				detail1.roleId=null;
			}
			var jsonDetail1 = Ext.encode(detail1);
			Ext.Ajax.request({
				url:'authAction_saveRole.action',
				params : {
					jsonDetail1:jsonDetail1
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('roleListAddOrEditWindow').close();
						Ext.getCmp('roleListGrid').getStore().load();
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}
	},
	detailRoleEdit:function(){	
		var data = Ext.getCmp('roleListGrid').getSelectionModel().getSelection();
		if (data.length != 0) {  
		if(typeof(Ext.getCmp('roleListAddOrEditWindow'))=='undefined')
		{
			Ext.create('cms.view.bset.window.roleListAddOrEditWindow',{
				title:'修改角色资料'
			}).show();
			Ext.getCmp('roleId1101').setValue(data[0].get('roleId'));
			Ext.getCmp('roleName1101').setValue(data[0].get('roleName'));
			Ext.getCmp('notes1101').setValue(data[0].get('notes'));
		}
        }else{
        	Ext.example.msg($i18n.prompt,'请选择一条数据修改！');
        } 
	},
	closeRoleListAddOrEditWindow:function(){
		if(typeof(Ext.getCmp('roleListAddOrEditWindow'))!='undefined')
		{
			Ext.getCmp('roleListAddOrEditWindow').close();
		}
	},
	userRoleSave:function(){
		//debugger;
		var data = Ext.getCmp('bset_Role_ListGrid2').getSelectionModel().getSelection();
		if (data.length != 0) {
			var detail1 = [];
			var record=Ext.getCmp('bset_User_Role_Grid').getStore().query('flag',true);
			for(i=0;i<record.length;i++){
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						roleId:data[0].get('roleId'),
						workerNo:record.items[i].get('workerNo')
					},
					rgstName:Ext.get('workerNo').getValue()
				};
				detail1.push(d);
			}
			if(detail1.length!=0){
				var jsonDetail1 = Ext.encode(detail1);			
				Ext.Ajax.request({
					url:'authAction_saveBset_User_Role.action',
					params : {
						strDetail:jsonDetail1
					},
					success:function(response){
						//debugger;
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}else{
				Ext.Ajax.request({
					url:'authAction_deleteUserRole.action',
					params : {
						str:data[0].get('roleId')
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
		}else{
			Ext.example.msg($i18n.prompt,'请选择用户分配角色后再保存！');
		}
	},
	userselectionchange:function(emodel,selected){
		if(selected.length>0){					
			var sql="";
			var quserId=selected[0].get('userId');		
			if(quserId!=null && quserId!="")
			{
				sql=" and b.userId="+quserId;		
			}
			var wheresql = {
					wheresql : sql
			};
			Ext.apply(Ext.getCmp('roleListGrid2')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('roleListGrid2').getStore()
					.removeAll();
			Ext.getCmp('roleListGrid2').getStore().load();
		}
	},
	
	detailAddRole:function(){
		Ext.create('cms.view.bset.window.bset_Role_ListAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();					
		addBset_Role_List();
		addCommMenu5('menuWidget51101');
	},
	detailEditRole:function(){	
		var data = Ext.getCmp('bset_Role_ListGrid').getSelectionModel().getSelection();
		if (data.length != 0) {  
			Ext.create('cms.view.bset.window.bset_Role_ListAddOrEditWindow',{
				title:$i18n.titleupdate
			}).show();
			rowindex1101=data[0].index;
			loadBset_Role_List(rowindex1101);
			commonSetCommMenu5PrevOrNext('menuWidget51101','bset_Role_ListGrid',rowindex1101);
			updateCommMenu5('menuWidget51101');
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
        } 
	},
	detailBrowseRole:function(){	
		var data = Ext.getCmp('bset_Role_ListGrid').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.create('cms.view.bset.window.bset_Role_ListAddOrEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
			rowindex1101=data[0].index;
			loadBset_Role_List(rowindex1101);
			commonSetFormReadOnly('bset_Role_ListAddOrEditForm',true);
			commonSetCommMenu5PrevOrNext('menuWidget51101','bset_Role_ListGrid',rowindex1101);
			browseCommMenu5('menuWidget51101');
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
	},
	detailQueryRole:function(){
		debugger;
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=110102;
		queryGrid='bset_Role_ListGrid';	
	},
	bset_Role_ListGrid2beforedeselect:function(){
		if(Ext.getCmp('bset_User_Role_Grid').getStore().getUpdatedRecords().length>0)
		{
			Ext.Msg.confirm("提示", "数据已被修改,是否保存？", 
			function(button, text) {
				if (button == 'yes') {
					console.log('sdfs');
					this.userRoleSave;
				}
			});
			/*Ext.example.msg($i18n.prompt,"数据已被修改");
			return;*/
		}
	},
	bset_Role_ListGrid2selectionchange:function(emodel,selected){
		if(selected.length>0){					
			var wheresql = {
				strRoleId : selected[0].get('roleId')
			};
			Ext.apply(Ext.getCmp('bset_User_Role_Grid').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('bset_User_Role_Grid').getStore().removeAll();
			Ext.getCmp('bset_User_Role_Grid').getStore().load();
		}
	},
	/*
	 * 角色权限指定、选择模块
	 */
	moduleselectionchange:function(emodel,selected){
		var roledata=Ext.getCmp('roleListGrid3').getSelectionModel().getSelection();
		if(roledata.length>0){
			if(selected.length>0){					
				var sql="";
				var qmoduleid=selected[0].get('id');
				var qroleId=roledata[0].get('roleId');	
				
				var wheresql = {
					strWhereSql : qroleId+","+qmoduleid
				};
				Ext.apply(Ext.getCmp('moduleAuthGrid').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('moduleAuthGrid').getStore().removeAll();
				Ext.getCmp('moduleAuthGrid').getStore().load();
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}
		
	},
	
	//角色权限指定、选择角色
	roleselectionchange:function(emodel,selected){
		Ext.getCmp('moduleAuthGrid').getStore().removeAll();
	},
	
	//角色权限指定，保存
	roleModuleSave1101:function(){
		var data = Ext.getCmp('roleListGrid3').getSelectionModel().getSelection();
		if (data.length != 0) {
			var detail1 = [];
		    Ext.getCmp('moduleAuthGrid').store.each(function(record){
	        	var d={
					id:{
						roleId:data[0].get('roleId'),
						moduleId:record.get('moduleId'),
						butId:record.get('butId')
					},
					rightName:record.data.rightName,
					flag:record.data.flag?1:0
				};
				detail1.push(d);
		    });
		    var jsonDetail1 = Ext.encode(detail1);			
					Ext.Ajax.request({
						url:'authAction_saveBset_Role_Module.action',
						params : {
							strDetail:jsonDetail1
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
		}else{
			Ext.example.msg($i18n.prompt,'请选择角色分配模块权限后再保存！');
		}
	},
	tabPId1101Tabchange:function(tabPanel,newCard, oldCard,eOpts){
		//alert('tabPId1101Tabchange');
		if(newCard.title=="用户角色指定"){
			if(Ext.getCmp('bset_Role_ListGrid2').getStore().count()>0){
				Ext.getCmp('bset_Role_ListGrid2').getSelectionModel().select(0);
			}
		}else if(newCard.title=="角色权限指定"){
			if(Ext.getCmp('roleListGrid3').getStore().count()>0){
				Ext.getCmp('roleListGrid3').getSelectionModel().select(0);
			}
		}
	},
	/**
	 * 加载用户信息
	 * @param {} rowindex1101
	 */
	 loadBdef_DefWorker:function(){
		// debugger;
		var objEditData=Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if(objEditData.length>0)
		{		
			Ext.getCmp('txtWorkerName1101').setValue(objEditData[0].data.workerNo);
			Ext.getCmp('txtWorkerAlias1401').setValue(objEditData[0].data.workerName);
			Ext.getCmp('txtWorkerAlias1402').setValue('******');   //8-17修改  改为*
			//Ext.getCmp('owner_name1101').setValue(objEditData[0].data.ownerNo);
			//Ext.getCmp('warehouse_name1101').setValue(objEditData[0].data.locno);
			Ext.getCmp('cmbSex1101').setValue(String(objEditData[0].data.sex));
			Ext.getCmp('txtWorkerAlias1403').setValue(objEditData[0].data.tel);
			Ext.getCmp('txtWorkerAlias1404').setValue(objEditData[0].data.address);
			Ext.getCmp('txtWorkerAlias1405').setValue(objEditData[0].data.title);
			Ext.getCmp('txtWorkerAlias1406').setValue(String(objEditData[0].data.authorityType));
			Ext.getCmp('txtWorkerAlias1407').setValue(String(objEditData[0].data.status));
		}
	},
	/**
	 * 加载用户角色信息
	 * @param {} 
	 */
	 loadBset_UserRole:function(){
		var objEditData=Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
		if(objEditData.length>0)
		{		
			record=selected[0];
			var strWheresql={
				strWheresql:record.data.ownerNo
			};
			Ext.apply(Ext.getCmp('bset_Role_ListGrid2').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('bset_Role_ListGrid2').getStore().removeAll();
			Ext.getCmp('bset_Role_ListGrid2').getStore().load();
		}
	}
	
});
//员工信息保存
function saveWorker1101(){
	//debugger;
	var workerNo= Ext.getCmp('txtWorkerName1101').getValue();		
	var workerName= Ext.String.trim(Ext.getCmp('txtWorkerAlias1401').getValue());
	var pwd= Ext.String.trim(Ext.getCmp('txtWorkerAlias1402').getValue());
	var sex= Ext.getCmp('cmbSex1101').getValue();	
	var tel= Ext.getCmp('txtWorkerAlias1403').getValue();
	var address= Ext.getCmp('txtWorkerAlias1404').getValue();	
	var title= Ext.getCmp('txtWorkerAlias1405').getValue();
	var authorityType= Ext.getCmp('txtWorkerAlias1406').getValue();	
	var status= Ext.getCmp('txtWorkerAlias1407').getValue();	
	var detail1={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				workerNo:workerNo
			},
		    //warehouseNo:Ext.get('warehouseNo').getValue(),				
			workerName:workerName,
			pwd:pwd,
			tel:tel,
			sex:sex,
			address:address,
			title:title,
			workerRfid:'*',
			authorityType:authorityType,
			status:status,
			//7-5添加
			/*rgstDate:g_strType1101=='add'?new Date():objPlanD.data.rgstDate.time,
		 	rgstName:g_strType1101=='add'?Ext.get('workerNo').getValue():objPlanD.data.rgstName,
		 	updtDate:g_strType1101=='add'?'':new Date(),
		 	updtName:g_strType1101=='add'?'':Ext.get('workerNo').getValue()*/
			//rgstDate:new Date(),
			rgstName:Ext.get('workerNo').getValue(),
			//updtDate:new Date(),
			updtName:Ext.get('workerNo').getValue()
	};
	var jsonDetail1 = Ext.encode(detail1);
	Ext.Ajax.request({
		url:'bdef_DefWorkerAction_saveBdef_DefWorker.action',
		async:false,
		params : {
			text:jsonDetail1
		},
		success:function(response){
			var data=Ext.decode(response.responseText);
			if(data.isSucc){
				//Ext.example.msg($i18n.prompt,$i18n[data.msg]);
				saveFlag1 = 1;
				commonSetMsterReadOnlyByArray(
						new Array('txtWorkerName1101'),true);
				//Ext.getCmp('txtWorkerName1101').setValue('');
				//Ext.getCmp('bset_Role_ListGrid2').getStore().load();
				//Ext.getCmp('grid_03_1101').getStore().load();
				//Ext.getCmp('grid_04_1101').getStore().load();
			}else{
				saveFlag1 = 0;
				//Ext.example.msg($i18n.prompt,$i18n[data.msg]);
			}
		}});
}
//角色保存
function saveRole1101(){

	var detail1 = [];
	var record=Ext.getCmp('bset_Role_ListGrid21').getStore().query('flag',true);
	for(i=0;i<record.length;i++){
		var d={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				roleId:record.items[i].get('roleId'),
				workerNo:Ext.getCmp('txtWorkerName1101').getValue()
			},
			rgstName:Ext.get('workerNo').getValue()
		};
		detail1.push(d);
	}
	if(detail1.length!=0){
		var jsonDetail1 = Ext.encode(detail1);			
		Ext.Ajax.request({
			url:'authAction_saveBset_Role_User.action',
			async:false,
			params : {
				strDetail:jsonDetail1
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag2 = 1;
				}else{
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag2 = 0;
				}
			}
		});
	}else{
		Ext.Ajax.request({
			url:'authAction_deleteRoleUser.action',
			async:false,
			params : {
				strDetail:Ext.getCmp('txtWorkerName1101').getValue()
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag2 = 1;
				}else{
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag2 = 0;
				}
			}
		});
	}

}
//货主保存
function saveOwner1101(){
	var detail1=[];
	var record=Ext.getCmp('grid_03_1101').getStore().query('flag',true);
	for(var i=0;i<record.length;i++){
		var d={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				ownerNo:record.items[i].get('ownerNo'),
				workerNo:Ext.getCmp('txtWorkerName1101').getValue()
			},
			sortOrder:'1',
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date()
		};
		detail1.push(d);
	}
	
	if(detail1.length!=0){
		var jsonDetail1 = Ext.encode(detail1);			
		Ext.Ajax.request({
			url:'bset_Worker_LocAction_saveBset_Owner_Worker',
			async:false,
			params : {
				str:jsonDetail1
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag3 = 1;
				}else{
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag3 = 0;
				}
			}
		});
	}else{
		Ext.Ajax.request({
			url:'bset_Worker_LocAction_deleteBset_Owner_Worker',
			async:false,
			params : {
				str:Ext.getCmp('txtWorkerName1101').getValue()
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag3 = 1;
				}else{
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag3 = 0;
				}
			}
		});
	}
}
//仓别保存
function saveLoc1101(){
	var detail1=[];
	var record=Ext.getCmp('grid_04_1101').getStore().query('flag',true);
	for(var i=0;i<record.length;i++){
		var d={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:record.items[i].get('warehouseNo'),
				workerNo:Ext.getCmp('txtWorkerName1101').getValue()
			},
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date()
		};
		detail1.push(d);
	}
	if(detail1.length!=0){
		var jsonDetail1 = Ext.encode(detail1);			
		Ext.Ajax.request({
			url:'bset_Worker_LocAction_saveBset_Loc_Worker',
			async:false,
			params : {
				str:jsonDetail1
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag4 = 1;
				}else{
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag4 = 0;
				}
			}
		});
	}else{
		Ext.Ajax.request({
			url:'bset_Worker_LocAction_deleteBset_Loc_Worker',
			async:false,
			params : {
				str:Ext.getCmp('txtWorkerName1101').getValue()
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag4 = 1;
				}else{
					//Ext.example.msg($i18n.prompt,data.msg);
					saveFlag4 = 0;
				}
			}
		});
	}
}

/**
 * 用户、保存
 * @param {} th
 */
function saveBdef_DefWorker(th){
	var but=th;
	if(Ext.getCmp('formBdef_DefWorker1101').getForm().isValid()){
		var workerNo= Ext.getCmp('txtWorker_no1101').getValue();		
		var workerName= Ext.String.trim(Ext.getCmp('txtWorker_name1101').getValue());
		var pwd= Ext.String.trim(Ext.getCmp('txtPwd1101').getValue());
		//var ownerName= Ext.String.trim(Ext.getCmp('owner_name1101').getValue());	
		//var locname= Ext.getCmp('warehouse_name1101').getValue();	
		var sex= Ext.getCmp('cmbSex1101').getValue();	
		var tel= Ext.getCmp('txtTel1101').getValue();
		var address= Ext.getCmp('txtAddress1101').getValue();	
		var title= Ext.getCmp('txtTitle1101').getValue();
		var authorityType= Ext.getCmp('cmbAuthorityType1101').getValue();	
		var status= Ext.getCmp('cmbStatus1101').getValue();	
		var detail1={
				workerNo:workerNo,
				workerName:workerName,
				pwd:pwd,
				//ownerNo:ownerName,
				//locno:locname,
				tel:tel,
				sex:sex,
				address:address,
				title:title,
				workerRfid:'*',
				authorityType:authorityType,
				status:status
		};
		var jsonDetail1 = Ext.encode(detail1);
		Ext.Ajax.request({
			url:'authAction_saveBdef_DefWorker.action',
			params : {
				text:jsonDetail1
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,$i18n[data.msg]);
				}else{
					Ext.example.msg($i18n.prompt,$i18n[data.msg]);
				}
			}
		});
	}
};



/**
 * 角色、新增初始化
 */
function addBset_Role_List(){
	Ext.getCmp('bset_Role_ListAddOrEditForm').getForm().reset();
	Ext.getCmp('partno1101').setValue('保存时自动生成');
};

/**
 * 角色、保存
 */
function saveBset_Role_List(th){
	var but=th;
	if(Ext.getCmp('bset_Role_ListAddOrEditForm').getForm().isValid()){	
		var roleId= Ext.getCmp('partno1101').getValue();		
		var roleName= Ext.String.trim(Ext.getCmp('partname1101').getValue());
		var detail1={
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				roleId:roleId,
				roleName:roleName				
		};
		if(detail1.roleId=='保存时自动生成'){
			detail1.roleId=null;
		}
		var jsonDetail1 = Ext.encode(detail1);
		Ext.Ajax.request({
			url:'authAction_saveBset_Role_List.action',
			params : {
				strRole:jsonDetail1
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('bset_Role_ListGrid').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};
/**
 * 角色、加载信息
 * @param {} rowindex1101
 */
function loadBset_Role_List(rowindex1101){
	var record=Ext.getCmp('bset_Role_ListGrid').getStore().getAt(rowindex1101);	
	Ext.getCmp('partno1101').setValue(record.data.roleId);
	Ext.getCmp('partname1101').setValue(record.data.roleName);
}
/**
 * 角色、关闭
 */
function closeBset_Role_List(){
	Ext.getCmp('bset_Role_ListAddOrEditWindow').close();
}


/**
 * 设置员工基本信息按钮---hj
 */
function setbdef_MenuWidget1401Tab1(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}
}


/**
 * 设置员工角色指定的按扭显示---hj
 */
function setbdef_MenuWidget1401Tab2(){
	//debugger;
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}
}

/**
 * 设置用户与货主下的按扭显示---hj
 */
function setbdef_MenuWidget1401Tab3(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}
}

/**
 * 设置用户与仓别下的按扭显示---hj
 */
function setbdef_MenuWidget1401Tab4(){
	if(g_intSaveType1401==0)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
		//Ext.getCmp('grid_01_1401_d2').getStore().removeAll();
	}else if(g_intSaveType1401==1)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}else if(g_intSaveType1401==2)
	{
		Ext.getCmp('menuWidget51101').items.items[1].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[2].setVisible(true);
		Ext.getCmp('menuWidget51101').items.items[3].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[4].setVisible(false);
		Ext.getCmp('menuWidget51101').items.items[5].setVisible(true);
	}
}


/**
 * 填充员工资料---hj
 */
function loadWorkerData1401()
{   
	//alert('填充员工资料');
	var objEditData=Ext.getCmp('gridBdefDefWorker1101').getSelectionModel().getSelection();
	//var t = objEditData[0].data.workerNo;
	//alert('t:' + t);
	
	if(objEditData.length>0)
	{		
		Ext.getCmp('txtWorkerName1101').setValue(objEditData[0].data.workerNo);
		Ext.getCmp('txtWorkerAlias1401').setValue(objEditData[0].data.workerName);
		Ext.getCmp('txtWorkerAlias1402').setValue('******');   //8-18修改   改为*号
		//Ext.getCmp('owner_name1101').setValue(objEditData[0].data.ownerNo);
		//Ext.getCmp('warehouse_name1101').setValue(objEditData[0].data.locno);
		Ext.getCmp('cmbSex1101').setValue(String(objEditData[0].data.sex));
		Ext.getCmp('txtWorkerAlias1403').setValue(objEditData[0].data.tel);
		Ext.getCmp('txtWorkerAlias1404').setValue(objEditData[0].data.address);
		Ext.getCmp('txtWorkerAlias1405').setValue(objEditData[0].data.title);
		Ext.getCmp('txtWorkerAlias1406').setValue(String(objEditData[0].data.authorityType));
		Ext.getCmp('txtWorkerAlias1407').setValue(String(objEditData[0].data.status));
	}
	
}




