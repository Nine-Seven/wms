/*
 * 码头维护
 * 1I01
 * zhouhuan
 */
var rowindex=0;
var button1I01='add';
Ext.define('cms.controller.bdef.bdef_DefDockController',{
	extend:'Ext.app.Controller',
	requires:[
			  'cms.view.bdef.bdef_DefDockUI',
			  'cms.view.bdef.window.bdef_DefDockAddOrEditWindow'
			  ],
	model:'cms.model.bdef.bdef_DefDockModel',
	store:'cms.store.bdef.bdef_DefDockStore',
	init:function(){
	    this.control({
	    	'bdef_DefDockUI button[name=detailAdd]':{
	    		click:this.detailAdd
	    	},
			'bdef_DefDockUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			'bdef_DefDockUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			'bdef_DefDockUI button[name=detailDelete]':{
				click:this.detailDelete
			},
			'bdef_DefDockUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			'bdef_DefDockUI button[name=detailExport]':{
				click:this.detailExport
			},
			'bdef_DefDockAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			'bdef_DefDockAddOrEditWindow button[name=next]':{
				click:this.next
			},
			'bdef_DefDockAddOrEditWindow button[name=add]':{
				click:this.add
			},
			'bdef_DefDockAddOrEditWindow button[name=save]':{
				click:this.save
			},
			'bdef_DefDockAddOrEditWindow button[name=close]':{
				click:this.close
			},//码头号blur
			'bdef_DefDockAddOrEditWindow textfield[id=dock_no1I01]':{
				blur:this.dock_no1I01Blur
			},//查询按扭
			'bdef_DefDockUI button[id=btnSearch1I01]':{
				click:this.btnSearch1I01Click
			}
	    });
	},
	
	/**
	 * 导出
	 */
	detailExport:function(){
		commExport('dockGrid1I01');
		//alert('11')
	},
	
	/**
	 * 查找
	 */
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1I01';
		queryGrid='dockGrid1I01';	
		button1I01='add';
	},
	
	//查询  7-7添加  hj
	btnSearch1I01Click:function(){
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('dockNo1I01').getValue())&&Ext.getCmp('dockNo1I01').getValue()!='ALL'){
			var d2={
					columnId:'a.dock_no',
					value:Ext.getCmp('dockNo1I01').getValue()
				};
			listDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('dock_type1I011').getValue())){
			var d2={
					columnId:'a.dock_type',
					value:Ext.getCmp('dock_type1I011').getValue()
				};
			listDetail1.push(d2);
		}
		var params={
				strQuery:Ext.encode(listDetail1),
				strOwnerNo:Ext.getCmp('dockNo1I01').getValue()
			};
		Ext.apply(Ext.getCmp('dockGrid1I01').getStore().proxy.extraParams,params);
		Ext.getCmp('dockGrid1I01').getStore().removeAll();
		Ext.getCmp('dockGrid1I01').getStore().load();
		
	},
	
	/*
	 * 新增码头
	 * zhouhuan
	 */
	detailAdd:function(){
		if(typeof(Ext.getCmp('bdef_DefDockAddOrEditWindow'))=='undefined'){
			Ext.create('cms.view.bdef.window.bdef_DefDockAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			adddock();
			Ext.getCmp('dock_no1I01').focus(false, 10);
			bindEnterSkip($('#bdef_DefDockAddOrEditForm1I01'));//调用键盘处理方法
			commonSetMsterReadOnlyByArray(
							new Array('dock_no1I01'),false);
			}
		addCommMenu5('menuWidget1I01');
		button1I01='add';
		//bindEnterSkip($('#bdef_DefDockAddOrEditForm1I01'));//调用键盘处理方法
	
	},
	/*
	 * 修改码头信息
	 * zhouhuan
	 */
	detailEdit:function(){
		var data=Ext.getCmp('dockGrid1I01').getSelectionModel().getSelection();
		if(data.length!=0){
			if(typeof(Ext.getCmp('bdef_DefDockAddOrEditWindow'))=='undefined'){
				Ext.create('cms.view.bdef.window.bdef_DefDockAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show();
				rowindex=data[0].index;
				loadSuppData(rowindex);
				commonSetMsterReadOnlyByArray(
						new Array('dock_no1I01'),true);
				commonSetCommMenu5PrevOrNext('menuWidget1I01','dockGrid1I01',rowindex);		
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}
		updateCommMenu5('menuWidget1I01');
		button1I01='update';
	},
	/**
	 * 浏览
	 * zhouhuan
	 */
	detailBrowse:function(){
		button1I01 = 'browse';
		if(!Ext.isEmpty(Ext.getCmp('dockGrid1I01'))){
			var data = Ext.getCmp('dockGrid1I01').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.bdef.window.bdef_DefDockAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex=data[0].index;
				loadSuppData(rowindex);
				commonSetCommMenu5PrevOrNext('menuWidget1I01','dockGrid1I01',rowindex);
				commonSetFormReadOnly('bdef_DefDockAddOrEditForm1I01',true);
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
		browseCommMenu5('menuWidget1I01');
	},
	/*
	 * 删除码头
	 * zhouhuan
	 */
	detailDelete:function (){
		var data = Ext.getCmp('dockGrid1I01').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
    			if(button=='yes')
				{
    				Ext.Ajax.request({
    					url : 'bdef_DefdockAction_delete.action',
    					params : {
    						dockNo:data[0].get('dockNo')
    					},
    					success : function(response) {
    						Ext.getCmp('dockGrid1I01').getStore().load();
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
		loadSuppData(rowindex);
		commonSetCommMenu5PrevOrNext('menuWidget1I01','dockGrid1I01',rowindex);
	},
		/**
	 * 下一条记录
	 * @param {} th
	 */
	next:function(th){
		rowindex=rowindex+1;
		loadSuppData(rowindex);
		commonSetCommMenu5PrevOrNext('menuWidget1I01','dockGrid1I01',rowindex);
	},
	
	add:function(th){
			commonSetMsterReadOnlyByArray(
							new Array('dock_no1I01'),false);
		bindEnterSkip($('#bdef_DefDockAddOrEditForm1I01'));//调用键盘处理方法
			adddock();
	},

	save:function(){
			savedock();
	},
	
	close:function(){
		closeSuppWindow();
	},
	
	//码头号blur
	dock_no1I01Blur:function(th){
		if(button1I01=='add'){
			
			if(!Ext.isEmpty(th.getValue())){
				Ext.Ajax.request({
				url:'bdef_DefDockAction_existsDockNo.action',
				params : {
					str:th.getValue()
				},
				success:function(response){
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg('提示',"码头编号已经存在");
			    		Ext.getCmp('dock_no1I01').setValue(null);
			    		Ext.getCmp('dock_no1I01').focus();
			    	}
				}
			});
			}	
		}		
	}
});

/**
 * 新增码头初始化
 * zhouhuan
 */
function adddock(){
	Ext.getCmp('bdef_DefDockAddOrEditForm1I01').getForm().reset();
	Ext.getCmp('dock_type1I01').setValue('0');
	Ext.getCmp('locate_type1I01').setValue('0');
	Ext.getCmp('adjust_board1I01').setValue('1');
	Ext.getCmp('dock_no1I01').focus(false, 10);
	bindEnterSkip($('#bdef_DefDockAddOrEditForm1I01'));//调用键盘处理方法
};

/*
 * 保存码头
 * zhouhuan
 */
function savedock(th){
			var but=th;
			if(Ext.getCmp('bdef_DefDockAddOrEditForm1I01').getForm().isValid()){
				var str={
					id:{
					 enterpriseNo:Ext.get('enterpriseNo').getValue(),
					 warehouseNo:Ext.get('warehouseNo').getValue(),
					 dockNo:Ext.getCmp('dock_no1I01').getValue()
					},
					 dockName:Ext.getCmp('dock_name1I01').getValue(),
					 dockType:Ext.getCmp('dock_type1I01').getValue(),
					 adjustBoard:Ext.getCmp('adjust_board1I01').getValue(),
					 locateType:Ext.getCmp('locate_type1I01').getValue(),
					 rgstName:Ext.get('workerNo').getValue(),
					 rgstDate:new Date(),
					 updtName:Ext.get('workerNo').getValue(),
					 updtDate:new Date()
				};
				var jsonStr = Ext.encode(str);
				Ext.Ajax.request({
					url : 'bdef_DefDockAction_saveOrUpdatedock.action',
					params:{
						str:jsonStr
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg('提示',data.msg);
							Ext.getCmp('dockGrid1I01').getStore().load();
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
		}else{
			//清空必填项
			Ext.getCmp('dock_no1I01').setValue(null);
			Ext.getCmp('dock_name1I01').setValue(null);
			//Ext.getCmp('dock_no1I01').setValue = '';		//7-11
			//Ext.getCmp('dock_name1I01').setValue = '';
			Ext.example.msg($i18n.prompt,'请输入必填项！');
		}
}
/**
 * 关闭码头界面
 */
function closeSuppWindow(){
	Ext.getCmp('bdef_DefDockAddOrEditWindow').close();
}


/**
 * 填充数据
 */
function loadSuppData(rowindex){
	var record=Ext.getCmp('dockGrid1I01').getStore().getAt(rowindex-(Ext.getCmp('dockGrid1I01').getStore().currentPage-1)*appConfig.getPageSize());	
	Ext.getCmp('dock_no1I01').setValue(record.data.dockNo);
	Ext.getCmp('dock_name1I01').setValue(record.data.dockName);	
	Ext.getCmp('dock_type1I01').setValue(record.data.dockType);
	Ext.getCmp('adjust_board1I01').setValue(record.data.adjustBoard);
	Ext.getCmp('locate_type1I01').setValue(String(record.data.locateType));
};

	