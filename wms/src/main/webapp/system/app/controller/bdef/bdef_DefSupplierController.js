/*
 * 供应商维护
 * 1601
 * zhouhuan
 */
var rowindex=0;
var type1601='';
Ext.define('cms.controller.bdef.bdef_DefSupplierController',{
	extend:'Ext.app.Controller',
	requires:[
			  'cms.view.bdef.bdef_DefSupplierUI',
			  'cms.view.bdef.window.bdef_DefSupplierAddOrEditWindow'
			  ],
	model:'cms.model.bdef.bdef_DefSupplierModel',
	store:'cms.store.bdef.bdef_DefSupplierStore',
	init:function(){
	    this.control({
	    	'bdef_DefSupplierUI button[name=detailAdd]':{
	    		click:this.detailAdd//新增
	    	},
			'bdef_DefSupplierUI button[name=detailEdit]':{
				click:this.detailEdit
			},//修改
			'bdef_DefSupplierUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//浏览
			'bdef_DefSupplierUI button[name=detailDelete]':{
				click:this.detailDelete
			},//删除
			'bdef_DefSupplierUI button[name=detailQuery]':{
				click:this.detailQuery
			},//查找
			'bdef_DefSupplierUI button[name=detailExport]':{
				click:this.detailExport
			},//导出
			'bdef_DefSupplierAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//上一条
			'bdef_DefSupplierAddOrEditWindow button[name=next]':{
				click:this.next
			},//下一条
			'bdef_DefSupplierAddOrEditWindow button[name=add]':{
				click:this.add
			},//窗口-->新增
			'bdef_DefSupplierAddOrEditWindow button[name=save]':{
				click:this.save
			},//保存
			'bdef_DefSupplierAddOrEditWindow button[name=close]':{
				click:this.close
			},//关闭
			'bdef_DefSupplierAddOrEditWindow textfield[id=supplier_no1601]':{
				blur:this.supplier_no1601Blur
			},//供应商校验
			'bdef_DefSupplierUI button[id=btnSearch1601]':{
				click:this.btnSearch1601Click
			},//查询按扭
			'bdef_DefSupplierUI button[name=refresh]':{
				click:this.refresh
			}//刷新
	    });
	},
	
	/**
	 * 导出
	 */
	detailExport:function(){
		commExport('supplierGrid1601');
	},
	
	/**
	 * 查找
	 */
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=1601;
		queryGrid='supplierGrid1601';					
	},
	
	//供应商信息-刷新   7-9添加  hj
	refresh:function(){
		//this.refresh();
		//debugger;
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('supplierGrid1601').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('supplierGrid1601').getStore().removeAll();
		Ext.getCmp('supplierGrid1601').getStore().reload();
	},
	
	//查询  7-7添加  hj
	btnSearch1601Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1601').getValue())&&Ext.getCmp('cmbFormOwner1601').getValue()!='ALL'){
			var d2={
					columnId:'d.owner_no',
					value:Ext.getCmp('cmbFormOwner1601').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusText1601').getValue())){
			var d2={
					columnId:'d.status',
					value:Ext.getCmp('statusText1601').getValue()
				};
			listDetail1.push(d2);
		}
 		if(!Ext.isEmpty(Ext.getCmp('supplierNo1601').getValue())){
			var d2={
					columnId:'d.supplier_no',   
					value:Ext.getCmp('supplierNo1601').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('cmbFormOwner1601').getValue()
			};
		Ext.apply(Ext.getCmp('supplierGrid1601').getStore().proxy.extraParams,params);
		Ext.getCmp('supplierGrid1601').getStore().removeAll();
		Ext.getCmp('supplierGrid1601').getStore().load();
		
	},
	
	/*
	 * 新增供应商
	 * zhouhuan
	 */
	detailAdd:function(){
		if(typeof(Ext.getCmp('bdef_DefSupplierAddOrEditWindow'))=='undefined'){
			Ext.create('cms.view.bdef.window.bdef_DefSupplierAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			addsupplier();
			Ext.getCmp('owner_no1601').focus(false, 10);
			//bindEnterSkip($('#cset_Cell_ArticleAddOrEditForm700002_1'));//调用键盘处理方法
			commonSetMsterReadOnlyByArray(
							new Array('owner_no1601','supplier_no1601'),false);
			}
		addCommMenu5('menuWidget1601');
		//bindEnterSkip($('#bdef_DefSupplierAddOrEditForm'));//调用键盘处理方法
		type1601='add';
	},
	/*
	 * 修改供应商信息
	 * zhouhuan
	 */
	detailEdit:function(){
		//debugger;
		type300001='update';
		var data=Ext.getCmp('supplierGrid1601').getSelectionModel().getSelection();
		if(data.length!=0){
			if(typeof(Ext.getCmp('bdef_DefSupplierAddOrEditWindow'))=='undefined'){
				Ext.create('cms.view.bdef.window.bdef_DefSupplierAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show();
				rowindex=data[0].index;
				loadSuppData1601(rowindex);
				commonSetCommMenu5PrevOrNext('menuWidget1601','supplierGrid1601',rowindex);
				commonSetMsterReadOnlyByArray(
						new Array('owner_no1601','supplier_no1601'),true);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}
		type1601='';
		updateCommMenu5('menuWidget1601');
	},
	
	/**
	 * 浏览
	 * zhouhuan
	 */
	detailBrowse:function(){
		if(!Ext.isEmpty(Ext.getCmp('supplierGrid1601'))){
			var data = Ext.getCmp('supplierGrid1601').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.bdef.window.bdef_DefSupplierAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex=data[0].index;
				loadSuppData1601(rowindex);
				commonSetCommMenu5PrevOrNext('menuWidget1601','supplierGrid1601',rowindex);
				//commonSetFormReadOnly('bdef_DefSupplierAddOrEditForm',true);
				commonSetMsterReadOnlyByArray(
						new Array('owner_no1601','supplier_name1601','supplier_no1601',
								'real_supplier_no1601','real_supplier_name1601',
								'supplier_remark1601','supplier11601','supplier_phone11601',
								'supplier_fax11601','supplier_address11601','supplier21601',
								'supplier_phone21601','supplier_fax21601','supplier_address21601',
								'supplier_alias1601','unload_flag1601','invoice_no1601','invoice_header1601',
								'invoice_addr1601','supplier_note_code1601','status1601',
								'rsv_var11601','rsv_var21601','rsv_var31601','rsv_var41601',
								'rsv_var51601','rsv_var61601'),true);
				
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
		browseCommMenu5('menuWidget1601');
	},
	/*
	 * 删除供应商
	 * zhouhuan
	 */
	detailDelete:function (){
		var data = Ext.getCmp('supplierGrid1601').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
    			if(button=='yes')
				{
    				Ext.Ajax.request({
    					url : 'bdef_DefSupplierAction_delete.action',
    					params : {
    						ownerNo:data[0].get('ownerNo'),
    						supplierNo:data[0].get('supplierNo')
    					},
    					success : function(response) {

							var data=Ext.decode(response.responseText);					
							if(data.isSucc){
								Ext.getCmp('supplierGrid1601').getStore().load();
								Ext.example.msg($i18n.prompt,data.msg);
							}else{
								Ext.example.msg($i18n.prompt,data.msg);							
							} 						
    					}
    				});
				}			
          });
	  }else{
		  Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	  }
	},
	/**
	 * 上一条记录
	 * @param {} th
	 */
	prev:function(th){
		rowindex=rowindex-1;
		loadSuppData1601(rowindex);
		commonSetCommMenu5PrevOrNext('menuWidget1601','supplierGrid1601',rowindex);
	},
		/**
	 * 下一条记录
	 * @param {} th
	 */
	next:function(th){
		rowindex=rowindex+1;
		loadSuppData1601(rowindex);
		commonSetCommMenu5PrevOrNext('menuWidget1601','supplierGrid1601',rowindex);
	},
	
	add:function(th){
			commonSetMsterReadOnlyByArray(
							new Array('owner_no1601','supplier_no1601'),false);
		bindEnterSkip($('#bdef_DefSupplierAddOrEditForm'));//调用键盘处理方法
			addsupplier();
			type1601='add';
	},

	save:function(){
			savesupplier();
	},
	
	close:function(){
		closeSuppWindow();
	},
	
	/*
	 * 判断主键是否重复   
	 */
	supplier_no1601Blur:function(th){
		if(type1601=='add'){
			Ext.Ajax.request({
				method:'post',
				url:'bdef_DefSupplierAction_existsSupplierNo.action',
				params:{
					supplierNo:Ext.getCmp('supplier_no1601').getValue(),
					ownerNo:Ext.getCmp('owner_no1601').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg($i18n.prompt,$i18n_prompt.supplierHaveExist);
			    		Ext.getCmp('supplier_no1601').setValue('');
			    		Ext.getCmp('supplier_no1601').focus();
			    	}
			    }
			});
		}		
	}
});

/**
 * 新增供应商初始化
 * zhouhuan
 */
function addsupplier(){
	Ext.getCmp('bdef_DefSupplierAddOrEditForm').getForm().reset();
	Ext.getCmp('owner_no1601').setValue('001');
	Ext.getCmp('status1601').setValue('1');
	Ext.getCmp('unload_flag1601').setValue('0');
	Ext.getCmp('owner_no1601').focus(false, 10);
	bindEnterSkip($('#bdef_DefSupplierAddOrEditForm'));//调用键盘处理方法
};

/*
 * 保存供应商
 * zhouhuan
 */
function savesupplier(th){
	debugger;
			var but=th;
			if(Ext.getCmp('bdef_DefSupplierAddOrEditForm').getForm().isValid()){
				var str={
					id:{
					 enterpriseNo:Ext.get('enterpriseNo').getValue(),
					 ownerNo:Ext.getCmp('owner_no1601').getValue(),
					 supplierNo:Ext.getCmp('supplier_no1601').getValue()
					},
					 realSupplierNo:Ext.getCmp('real_supplier_no1601').getValue(),
					 supplierName:Ext.getCmp('supplier_name1601').getValue(),
					 realSupplierName:Ext.getCmp('real_supplier_name1601').getValue(),
					 supplierAlias:Ext.getCmp('supplier_alias1601').getValue(),
					 unloadFlag:Ext.getCmp('unload_flag1601').getValue(),
					 deptNo:'N',
					 status:Ext.getCmp('status1601').getValue(),
					 supplierName:Ext.getCmp('supplier_name1601').getValue(),
					 supplierRemark:Ext.getCmp('supplier_remark1601').getValue(),
					 supplier1:Ext.getCmp('supplier11601').getValue(),
					 supplierPhone1:Ext.getCmp('supplier_phone11601').getValue(),
					 supplierFax1:Ext.getCmp('supplier_fax11601').getValue(),
					 supplierAddress1:Ext.getCmp('supplier_address11601').getValue(),
					 supplier2:Ext.getCmp('supplier21601').getValue(),
					 supplierPhone2:Ext.getCmp('supplier_phone21601').getValue(),
					 supplierFax2:Ext.getCmp('supplier_fax21601').getValue(),
					 supplierAddress2:Ext.getCmp('supplier_address21601').getValue(),
					 invoiceNo:Ext.getCmp('invoice_no1601').getValue(),
					 invoiceHeader:Ext.getCmp('invoice_header1601').getValue(),
					 invoiceAddr:Ext.getCmp('invoice_addr1601').getValue(),
					 supplierNoteCode:Ext.getCmp('supplier_note_code1601').getValue(),
					 createFlag:'1',
					 rgstName:Ext.get('workerNo').getValue(),
					 rgstDate:new Date(),
					 updtName:Ext.get('workerNo').getValue(),
					 updtDate:new Date(),
					 
					 rsvVar1:Ext.getCmp('rsv_var11601').getValue(),
					 rsvVar2:Ext.getCmp('rsv_var21601').getValue(),
					 rsvVar3:Ext.getCmp('rsv_var31601').getValue(),
					 rsvVar4:Ext.getCmp('rsv_var41601').getValue(),
					 rsvVar5:Ext.getCmp('rsv_var51601').getValue(),
					 rsvVar6:Ext.getCmp('rsv_var61601').getValue()
				};
				var jsonStr = Ext.encode(str);
				Ext.Ajax.request({
					url : 'bdef_DefSupplierAction_saveOrUpdatesupplier.action',
					params:{
						str:jsonStr
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('supplierGrid1601').getStore().load();
							commonSetMsterReadOnlyByArray(
							new Array('owner_no1601','supplier_no1601'),true);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
		}
}
/**
 * 关闭供应商界面
 */
function closeSuppWindow(){
	Ext.getCmp('bdef_DefSupplierAddOrEditWindow').close();
}


/**
 * 填充数据
 */
function loadSuppData1601(rowindex){
	var record=Ext.getCmp('supplierGrid1601').
	getStore().getAt(rowindex-(Ext.getCmp('supplierGrid1601').
	getStore().currentPage-1)*appConfig.getPageSize());	
	Ext.getCmp('owner_no1601').getStore().add
	({
		value:record.data.ownerNo,
		dropValue:"["+record.data.ownerNo+"]"+record.data.ownerName,
		text:record.data.ownerName
    });
	
	Ext.getCmp('owner_no1601').setValue(record.data.ownerNo);
	Ext.getCmp('supplier_no1601').setValue(record.data.supplierNo);
	Ext.getCmp('real_supplier_no1601').setValue(record.data.realSupplierNo);	
	Ext.getCmp('real_supplier_name1601').setValue(record.data.realSupplierName);
	Ext.getCmp('supplier_name1601').setValue(record.data.supplierName);
	Ext.getCmp('supplier_alias1601').setValue(record.data.supplierAlias);
	Ext.getCmp('unload_flag1601').setValue(record.data.unloadFlag.toString());
	Ext.getCmp('status1601').setValue(record.data.status);
	Ext.getCmp('supplier_name1601').setValue(record.data.supplierName);
	Ext.getCmp('supplier_remark1601').setValue(record.data.supplierRemark);
	Ext.getCmp('supplier11601').setValue(record.data.supplier1);
	Ext.getCmp('supplier_phone11601').setValue(record.data.supplierPhone1);
	Ext.getCmp('supplier_fax11601').setValue(record.data.supplierFax1);
	Ext.getCmp('supplier_address11601').setValue(record.data.supplierAddress1);
	Ext.getCmp('supplier21601').setValue(record.data.supplier2);
	Ext.getCmp('supplier_phone21601').setValue(record.data.supplierPhone2);
	Ext.getCmp('supplier_fax21601').setValue(record.data.supplierFax2);
	Ext.getCmp('supplier_address21601').setValue(record.data.supplierAddress2);
	Ext.getCmp('invoice_no1601').setValue(record.data.invoiceNo);
	Ext.getCmp('invoice_header1601').setValue(record.data.invoiceHeader);
	Ext.getCmp('invoice_addr1601').setValue(record.data.invoiceAddr);
	Ext.getCmp('supplier_note_code1601').setValue(record.data.supplierNoteCode);
	
	Ext.getCmp('rsv_var11601').setValue(record.data.rsvVar1);
	Ext.getCmp('rsv_var21601').setValue(record.data.rsvVar2);
	Ext.getCmp('rsv_var31601').setValue(record.data.rsvVar3);
	Ext.getCmp('rsv_var41601').setValue(record.data.rsvVar4);
	Ext.getCmp('rsv_var51601').setValue(record.data.rsvVar5);
	Ext.getCmp('rsv_var61601').setValue(record.data.rsvVar6);
};

	