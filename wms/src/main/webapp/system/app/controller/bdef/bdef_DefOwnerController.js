/**
 * 货主资料维护
 * 模块编码   1D01
 * @author lich
 */
 
var g_strType1D01='add';
Ext.define('cms.controller.bdef.bdef_DefOwnerController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_DefOwnerUI',
	          'cms.view.bdef.window.bdef_DefOwnerAddOrEditWindow'
	          ],
	init:function(){
		this.control({
			  //新增
			'bdef_DefOwnerUI button[name=detailAdd]':{
				click:this.detailAdd  
			},//修改
			'bdef_DefOwnerUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bdef_DefOwnerUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查询 
			'bdef_DefOwnerUI button[name=detailQuery]':{
				click:this.detailQuery
			},//删除  
			'bdef_DefOwnerUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			'bdef_DefOwnerAddOrEditWindow field':{
				specialkey:this.boxkeydown
			},//新增初始化
			'bdef_DefOwnerAddOrEditWindow button[name=add]':{
				click:this.add
			},//保存
			'bdef_DefOwnerAddOrEditWindow button[name=save]':{
				click:this.save
			},//刷新
			'bdef_DefOwnerUI button[name=refresh]':{
				click:this.refresh
			},//上一条记录
			'bdef_DefOwnerAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_DefOwnerAddOrEditWindow button[name=next]':{
				click:this.next
			},//关闭
			'bdef_DefOwnerAddOrEditWindow button[name=close]':{
				click:this.close
			},//判断是否可以修改货主状态
			'bdef_DefOwnerAddOrEditWindow wms_DefFieldValCombo[id=cmbStatus1D01]':{
				select:this.selectCmbStatus1D01
			},//判断货主编码是否存在
			'bdef_DefOwnerAddOrEditWindow textfield[id=txtOwnerNo1D01]':{
				blur:this.txtOwnerNo1D01Blur
			},//选择储位管理类型
			'bdef_DefOwnerAddOrEditWindow wms_DefFieldValCombo[id=cellManagerType1D01]':{
				change:this.selectCellManagerType1D01
			},//选择仓别级储位管理类型
			'bdef_DefOwnerAddOrEditWindow wms_DefFieldValCombo[id=WcellManagerType1D01]':{
				change:this.selectWCellManagerType1D01
			},
			//关闭前事件
			'bdef_DefOwnerAddOrEditWindow':{
				beforeclose:this.bdef_DefOwnerAddOrEditWindowBeforeclose
			},
			//货主编号选择
			'bdef_DefOwnerUI combo[id=cmbFormOwner1D01]':{
				select:this.selectByOwnerNo
			},
			//状态选择
			'bdef_DefOwnerUI combo[id=statusText1D01]':{
				select:this.selectStatus
			}
		});
	},
	initializtion:function(){
		Ext.getCmp('gridBdefDefOwner1D01').getStore().load();
	},
	refresh:function(){
		Ext.getCmp('gridBdefDefOwner1D01').getStore().removeAll();
		Ext.getCmp('gridBdefDefOwner1D01').getStore().load();
	},
	//根据货主编码查找货主资料
	selectByOwnerNo:function(){	
		var strDetail11 = [];
		if(Ext.getCmp('cmbFormOwner1D01').getValue()!="ALL"){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1D01').getValue()
					
			};
			strDetail11.push(d2);
		}		
		var jsonDetail1 = Ext.encode(strDetail11);
		var str1 = {
				strQuery:jsonDetail1,
		};
		Ext.apply(Ext.getCmp('gridBdefDefOwner1D01').getStore().proxy.extraParams,str1);
		Ext.getCmp('gridBdefDefOwner1D01').getStore().removeAll();
		Ext.getCmp('gridBdefDefOwner1D01').getStore().load();
		Ext.getCmp('statusText1D01').setValue('');
		Ext.getCmp('statusText1D01').getStore().removeAll();
		Ext.apply(Ext.getCmp('statusText1D01').getStore().proxy.extraParams,str1);		
		Ext.getCmp('statusText1D01').getStore().load();
	},
	//状态选择
	selectStatus:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1D01').getValue())&&
				Ext.getCmp('cmbFormOwner1D01').getValue()!="ALL"){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbFormOwner1D01').getValue()
				};
			strDetail11.push(d2);
		}		
		var d2={
				columnId:'a.status'	,
				value:Ext.getCmp('statusText1D01').getValue()
					
		};
		strDetail11.push(d2);
		var jsonDetail1 = Ext.encode(strDetail11);
		var str1 = {
				strQuery:jsonDetail1
		};
		
		Ext.apply(Ext.getCmp('gridBdefDefOwner1D01').getStore().proxy.extraParams,str1);
		Ext.getCmp('gridBdefDefOwner1D01').getStore().removeAll();
		Ext.getCmp('gridBdefDefOwner1D01').getStore().load();		
	},
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=="cmbStatus1D01"){
	  			Ext.getCmp('tab1D01').setActiveTab(0);
	  			Ext.getCmp('txtOwnerAddress1D01').focus();
	  		}else if(th.id=="txtRemark1D01"){
	  			Ext.getCmp('tab1D01').setActiveTab(1);
	  			Ext.getCmp('cmbIStrategy1D01').focus();
	  		}else if(th.id=="cmbRsvStrategy61D01"){
	  			Ext.getCmp('bdef_MenuWidget1D01').items.items[4].focus();
	  		}else{
				th.nextSibling().focus();
			}
        }
		
	},
	detailAdd:function(){  
		Ext.create('cms.view.bdef.window.bdef_DefOwnerAddOrEditWindow',{
			title:$i18n.titleadd
		}).show();
		addCommMenu5('bdef_MenuWidget1D01');
		this.add();
		g_strType1D01='add';
	},
	
	detailEdit:function(){
		var objEditData=Ext.getCmp('gridBdefDefOwner1D01').getSelectionModel().getSelection();
		if(objEditData.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else
		{
			Ext.create('cms.view.bdef.window.bdef_DefOwnerAddOrEditWindow',
				{
					title:$i18n.titleupdate
				}
			).show();
			g_strType1D01='edit';
			updateCommMenu5('bdef_MenuWidget1D01');
			commonMenu5PrevOrNext('bdef_MenuWidget1D01','gridBdefDefOwner1D01',0);
			commonSetMsterReadOnlyByArray(
				new Array('txtOwnerNo1D01'),true);
			this.loadBdefDefOwner();
			Ext.getCmp('WcellNo1D01').setVisible(false);
		}
	},
	detailBrowse:function(){
		var objBrowseData = Ext.getCmp('gridBdefDefOwner1D01').getSelectionModel().getSelection();
		if (objBrowseData.length != 0) {
			Ext.create('cms.view.bdef.window.bdef_DefOwnerAddOrEditWindow',
				{
					title:$i18n.titlebrowse
				}
			).show();
			g_strType1D01='browse';
			browseCommMenu5('bdef_MenuWidget1D01');
			commonMenu5PrevOrNext('bdef_MenuWidget1D01','gridBdefDefOwner1D01',0);
			commonSetFieldReadOnly('formHeard1D01',true);
			commonSetFieldReadOnly('formBasicInfo1D01',true);
			commonSetFieldReadOnly('formPolicyInfo1D01',true);
			this.loadBdefDefOwner();
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
        browseCommMenu5('bdef_MenuWidget1301_1');
	},	
	
	//判断是否可以修改货主状态
	selectCmbStatus1D01:function(){		
		if(g_strType1D01=='edit' && Ext.getCmp('cmbStatus1D01').getValue()=='0'){

			Ext.Ajax.request({
				url:'bdef_DefOwnerAction_isCanChange.action',
				params:{
					'str':Ext.getCmp('txtOwnerNo1D01').getValue()
				},
				success:function(response){
					var data=Ext.decode(response.responseText);					
					if(!data.isSucc){
						Ext.getCmp('cmbStatus1D01').setValue('1');
						Ext.example.msg($i18n.prompt,'该货主有库存，不能禁用');
					}
				}				
			});				
		}
	},
	
	save:function(){
		if(!commonCheckIsInputAll('formHeard1D01') 
		||!Ext.getCmp('formHeard1D01').getForm().isValid())
		{
			return;
		}
		if(!commonCheckIsInputAll('formBasicInfo1D01')
		||!Ext.getCmp('formBasicInfo1D01').getForm().isValid())
		{
			return;
		}
		if(!commonCheckIsInputAll('formPolicyInfo1D01')
		||!Ext.getCmp('formPolicyInfo1D01').getForm().isValid())
		{
			return;
		}	
		
		if(Ext.getCmp('cellManagerType1D01').getValue()=='1' &&
			(Ext.getCmp('cellNo1D01').getValue()=='' ||	Ext.getCmp('cellNo1D01').getValue()==null)){
			Ext.example.msg($i18n.prompt,'请输入默认储位');
			return;
		}
		
		if(Ext.getCmp('WcellManagerType1D01').getValue()=='1' &&
				(Ext.getCmp('WcellNo1D01').getValue()=='' ||	Ext.getCmp('WcellNo1D01').getValue()==null)){
				Ext.example.msg($i18n.prompt,'请输入默认储位(仓别级)');
				return;
		}
					
		var boBdef_DefOwner={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				ownerNo:Ext.getCmp('txtOwnerNo1D01').getValue()
			},
			
			ownerName:Ext.getCmp('txtOwnerName1D01').getValue(),
			ownerAlias:Ext.getCmp('txtOwnerAlias1D01').getValue(),
			ownerAddress:Ext.getCmp('txtOwnerAddress1D01').getValue(),
			ownerPhone:Ext.getCmp('txtOwnerPhone1D01').getValue(),
			ownerFax:Ext.getCmp('txtOwnerFax1D01').getValue(),
			ownerContact:Ext.getCmp('txtOwnerContact1D01').getValue(),
			ownerRemark:Ext.getCmp('txtRemark1D01').getValue(),
			invoiceNo:Ext.getCmp('txtInvoiceNo1D01').getValue(),
			invoiceAddr:Ext.getCmp('txtInvoiceAddr1D01').getValue(),
			invoiceHeader:Ext.getCmp('txtInvoiceHeader1D01').getValue(),
			turnOverRule:Ext.getCmp('cmbTurnOverRule1D01').getValue(),
			status:Ext.getCmp('cmbStatus1D01').getValue(),
			scanFlag:Ext.getCmp('cmbScanFlag1D01').getValue(),
			fixedcellFlag:Ext.getCmp('cmbFixedcellFlag1D01').getValue(),
			authorityType:Ext.getCmp('cmbAuthorityType1D01').getValue(),
			IStrategy:Ext.getCmp('cmbIStrategy1D01').getValue(),
			OStrategy:Ext.getCmp('cmbOStrategy1D01').getValue(),
			MStrategy:Ext.getCmp('cmbMStrategy1D01').getValue(),
			riStrategy:Ext.getCmp('cmbRiStrategy1D01').getValue(),
			roStrategy:Ext.getCmp('cmbRoStrategy1D01').getValue(),
			fcStrategy:Ext.getCmp('cmbFcStrategy1D01').getValue(),
			rsvStrategy1:Ext.getCmp('cmbRsvStrategy11D01').getValue(),
			rsvStrategy2:Ext.getCmp('cmbRsvStrategy21D01').getValue(),
			rsvStrategy3:Ext.getCmp('cmbRsvStrategy31D01').getValue(),
			rsvStrategy4:Ext.getCmp('cmbRsvStrategy41D01').getValue(),
			rsvStrategy5:Ext.getCmp('cmbRsvStrategy51D01').getValue(),
			rsvStrategy6:Ext.getCmp('cmbRsvStrategy61D01').getValue(),
			cellManagerType:Ext.getCmp('cellManagerType1D01').getValue(),
			typeValue:Ext.getCmp('cellManagerType1D01').getValue()=='0'?'':Ext.getCmp('cellNo1D01').getValue(),
		    //7-21添加
			rsvVar2:Ext.getCmp('rsvVar21D01').getValue(),		
			rsvVar3:Ext.getCmp('rsvVar31D01').getValue(),
		};

		//操作bset_owner_cell_manage表 Add by sunl 2016年8月3日
		var strWcellManagerType ='';
		var strWtypeValue ='';
		if(Ext.getCmp('WcellManagerType1D01').getValue() != '')
		{
			if(Ext.getCmp('WcellManagerType1D01').getValue() != '0')
			{
				strWcellManagerType = Ext.getCmp('WcellManagerType1D01').getValue();
				strWtypeValue = Ext.getCmp('WcellNo1D01').getValue();
			}
			else
			{
				strWcellManagerType = Ext.getCmp('WcellManagerType1D01').getValue();
			}
		}
		
		Ext.Ajax.request({
			url:'bdef_DefOwnerAction_saveOrUpdateOwner.action',
			method:'post',
			params:{
				str:Ext.encode(boBdef_DefOwner),
				strWareHouseNO:Ext.get('warehouseNo').getValue(),
				strWcellManagerType:strWcellManagerType,
				strWtypeValue:strWtypeValue
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,$i18n[data.msg]);
					Ext.getCmp('cmbFormOwner1D01').removeAll();
					Ext.getCmp('cmbFormOwner1D01').load();
					
				}else{
					Ext.example.msg($i18n.prompt,$i18n[data.msg]);
				}
			}
		});
	},
	detailQuery:function()
	{
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1D01';
		queryGrid='gridBdefDefOwner1D01';
	},
	
	//删除货主
	detailDelete:function(){
		var data=Ext.getCmp('gridBdefDefOwner1D01').getSelectionModel().getSelection();
		if(data.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);
		}
		Ext.Msg.confirm($i18n.prompt,$i18n.prompt_sure_delete,function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url:'bdef_DefOwnerAction_deleteOwner.action',
					params:{
						'str':data[0].get('ownerNo')
					},
					success:function(response){
						var data=Ext.decode(response.responseText);					
						if(data.isSucc){
							Ext.getCmp('gridBdefDefOwner1D01').getStore().load();
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}				
				});		
			}
		});
	},
	
	prev:function()
	{		
		commonMenu5PrevOrNext('bdef_MenuWidget1D01','gridBdefDefOwner1D01',-1);
		this.loadBdefDefOwner();
	},
	next:function()
	{
		commonMenu5PrevOrNext('bdef_MenuWidget1D01','gridBdefDefOwner1D01',1);
		this.loadBdefDefOwner();
	},	
	close:function()
	{
		Ext.getCmp('bdef_DefOwnerAddOrEditWindow').close();
	},
	//新增前加载
	add:function(){
		Ext.getCmp('formHeard1D01').getForm().reset();
		Ext.getCmp('formBasicInfo1D01').getForm().reset();
		Ext.getCmp('formPolicyInfo1D01').getForm().reset();
		Ext.getCmp('cmbAuthorityType1D01').setValue('1');
		if(Ext.getCmp('cmbTurnOverRule1D01').getStore().data.length>0)
		{
			Ext.getCmp('cmbTurnOverRule1D01').setValue(Ext.getCmp('cmbTurnOverRule1D01').getStore().getAt(0).data.value);	
		}
		Ext.getCmp('cmbStatus1D01').setValue('1');
		Ext.getCmp('cmbScanFlag1D01').setValue('0');
		Ext.getCmp('cmbFixedcellFlag1D01').setValue('0');
		Ext.getCmp('cmbIStrategy1D01').setValue('1');
		Ext.getCmp('cmbOStrategy1D01').setValue('1');
		Ext.getCmp('cmbMStrategy1D01').setValue('1');
		Ext.getCmp('cmbRiStrategy1D01').setValue('1');
		Ext.getCmp('cmbRoStrategy1D01').setValue('1');
		Ext.getCmp('cmbFcStrategy1D01').setValue('1');
		Ext.getCmp('cellManagerType1D01').setValue('0');
		Ext.getCmp('txtOwnerNo1D01').focus(false,10);
		Ext.getCmp('cellNo1D01').setVisible(false);

		Ext.getCmp('WcellManagerType1D01').setValue('0');
		Ext.getCmp('WcellNo1D01').setVisible(false);
	},
	loadBdefDefOwner:function()
	{
		var objEditData=Ext.getCmp('gridBdefDefOwner1D01').getSelectionModel().getSelection();
		if(objEditData.length>0)
		{			
			Ext.getCmp('txtOwnerNo1D01').setValue(objEditData[0].data.ownerNo);
			Ext.getCmp('txtOwnerName1D01').setValue(objEditData[0].data.ownerName);
			Ext.getCmp('txtOwnerAlias1D01').setValue(objEditData[0].data.ownerAlias);
			Ext.getCmp('cmbAuthorityType1D01').setValue(objEditData[0].data.authorityType);
			Ext.getCmp('cmbStatus1D01').setValue(objEditData[0].data.status);
			Ext.getCmp('txtOwnerAddress1D01').setValue(objEditData[0].data.ownerAddress);
			Ext.getCmp('txtOwnerPhone1D01').setValue(objEditData[0].data.ownerPhone);
			Ext.getCmp('txtOwnerFax1D01').setValue(objEditData[0].data.ownerFax);
			Ext.getCmp('txtOwnerContact1D01').setValue(objEditData[0].data.ownerContact);
			Ext.getCmp('txtInvoiceNo1D01').setValue(objEditData[0].data.invoiceNo);
			Ext.getCmp('txtInvoiceAddr1D01').setValue(objEditData[0].data.invoiceAddr);
			Ext.getCmp('txtInvoiceHeader1D01').setValue(objEditData[0].data.invoiceHeader);
			Ext.getCmp('cmbFixedcellFlag1D01').setValue(objEditData[0].data.fixedcellFlag);
			Ext.getCmp('cmbScanFlag1D01').setValue(objEditData[0].data.scanFlag);
			Ext.getCmp('cmbTurnOverRule1D01').setValue(objEditData[0].data.turnOverRule);
			Ext.getCmp('txtRemark1D01').setValue(objEditData[0].data.ownerRemark);
			Ext.getCmp('cmbIStrategy1D01').setValue(objEditData[0].data.IStrategy);
			Ext.getCmp('cmbOStrategy1D01').setValue(objEditData[0].data.OStrategy);
			Ext.getCmp('cmbMStrategy1D01').setValue(objEditData[0].data.MStrategy);
			Ext.getCmp('cmbRiStrategy1D01').setValue(objEditData[0].data.riStrategy);
			Ext.getCmp('cmbRoStrategy1D01').setValue(objEditData[0].data.roStrategy);
			Ext.getCmp('cmbFcStrategy1D01').setValue(objEditData[0].data.fcStrategy);
			Ext.getCmp('cmbRsvStrategy11D01').setValue(objEditData[0].data.rsvStrategy1);
			Ext.getCmp('cmbRsvStrategy21D01').setValue(objEditData[0].data.rsvStrategy2);
			Ext.getCmp('cmbRsvStrategy31D01').setValue(objEditData[0].data.rsvStrategy3);
			Ext.getCmp('cmbRsvStrategy41D01').setValue(objEditData[0].data.rsvStrategy4);
			Ext.getCmp('cmbRsvStrategy51D01').setValue(objEditData[0].data.rsvStrategy5);
			Ext.getCmp('cmbRsvStrategy61D01').setValue(objEditData[0].data.rsvStrategy6);
			Ext.getCmp('cellManagerType1D01').setValue(objEditData[0].data.cellManagerType);
			Ext.getCmp('cellNo1D01').setValue(objEditData[0].data.typeValue);
			//7-22添加
			Ext.getCmp('rsvVar21D01').setValue(objEditData[0].data.rsvVar2);
			Ext.getCmp('rsvVar31D01').setValue(objEditData[0].data.rsvVar3);
		 
			Ext.getCmp('WcellManagerType1D01').setValue(objEditData[0].data.wcellManagerType);
			Ext.getCmp('WcellNo1D01').setValue(objEditData[0].data.wtypeValue);
		}
	},
	txtOwnerNo1D01Blur:function(th)
	{
		if(g_strType1D01=='add')
		{
			if(!Ext.isEmpty(th.getValue())){
				Ext.Ajax.request({
				url:'bdef_DefOwnerAction_existsOwnerNo.action',
				params : {
					str:th.getValue()
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,$i18n[data.msg]);
						th.setValue('');
						th.focus();
					}
				}
			});
			}	
		}
	},
	bdef_DefOwnerAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('gridBdefDefOwner1D01').getStore().load();
	},
	selectCellManagerType1D01:function(){
		
		if(Ext.getCmp('cellManagerType1D01').getValue()=='0'){
			Ext.getCmp('cellNo1D01').setVisible(false);		
		}else{
			Ext.getCmp('cellNo1D01').setVisible(true);
			Ext.getCmp('cellNo1D01').setFieldLabel("<font color='red'>*</font>默认储位");
		}
	},
	selectWCellManagerType1D01:function(){
		if(Ext.getCmp('WcellManagerType1D01').getValue()== '1'){
			Ext.getCmp('WcellNo1D01').setVisible(true);
			Ext.getCmp('WcellNo1D01').setFieldLabel("<font color='red'>*</font>默认储位");
		}else{
			Ext.getCmp('WcellNo1D01').setVisible(false);
		}
	}
});