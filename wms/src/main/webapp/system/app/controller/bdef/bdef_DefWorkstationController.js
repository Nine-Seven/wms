/*
 * 工作站维护
 * 1H01
 * zhouhuan
 */
var rowindex1H01=0;
var saveType1H01='';
Ext.define('cms.controller.bdef.bdef_DefWorkstationController',{
	extend:'Ext.app.Controller',
	requires:[
			  'cms.view.bdef.bdef_DefWorkstationUI',
			  'cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow'
			  ],
	model:'',
	store:'',
	init:function(){
	    this.control({//新增
	    	'bdef_DefWorkstationUI button[name=detailAdd]':{
	    		click:this.detailAdd
	    	},//修改
			'bdef_DefWorkstationUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bdef_DefWorkstationUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//删除
			'bdef_DefWorkstationUI button[name=detailDelete]':{
				click:this.detailDelete
			},//查找
			'bdef_DefWorkstationUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导出
			'bdef_DefWorkstationUI button[name=detailExport]':{
				click:this.detailExport
			},//上一条记录
			'bdef_DefWorkstationAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bdef_DefWorkstationAddorEditWindow button[name=next]':{
				click:this.next
			},//新增前加载
			'bdef_DefWorkstationAddorEditWindow button[name=add]':{
				click:this.add
			},//保存
			'bdef_DefWorkstationAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bdef_DefWorkstationAddorEditWindow button[name=close]':{
				click:this.close
			},//选择打印机群组
			'bdef_DefWorkstationAddorEditWindow bset_GroupCombo[id=printer_group1H01] ':{
				select:this.printer_group1H01select
			},//验证工作站编号唯一性
			'bdef_DefWorkstationAddorEditWindow form textfield[id=workstation_no1H01]':{
				blur:this.workstationNoBlur
			}
	    });
	},
	
	detailExport:function(){
		commExport('grid_01_1H01');
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1H01';
		queryGrid='grid_01_1H01';					
	},
	
	detailAdd:function(){
		if(typeof(Ext.getCmp('bdef_DefWorkstationAddorEditWindow'))=='undefined'){
			Ext.create('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
				title:$i18n.titleadd
			}).show();
			addWorkstation1H01();
			Ext.getCmp('workstation_no1H01').focus(false, 10);
			bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
			commonSetMsterReadOnlyByArray(
				new Array('workstation_no1H01'),false);
			}
		addCommMenu5('menuWidget1H01');
		saveType1H01='add';
	},
	
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1H01').getSelectionModel().getSelection();
		if(data.length!=0){
			if(typeof(Ext.getCmp('bdef_DefWorkstationAddorEditWindow'))=='undefined'){
				Ext.create('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
					title:$i18n.titleupdate
				}).show();
				rowindex1H01=data[0].index;
				loadWorkstationData1H01(rowindex1H01);
				commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_01_1H01',rowindex1H01);
				Ext.getCmp('workstation_no1H01').disable();
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}
		bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
		Ext.getCmp('workstation_no1H01').focus();
		updateCommMenu5('menuWidget1H01');
		saveType1H01='edit';
	},
	
	detailBrowse:function(){
		if(!Ext.isEmpty(Ext.getCmp('grid_01_1H01'))){
			var data = Ext.getCmp('grid_01_1H01').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex1H01=data[0].index;
				loadWorkstationData1H01(rowindex1H01);
				commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_01_1H01',rowindex1H01);
				commonSetFormReadOnly('form_01_1H01',true);
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
		browseCommMenu5('menuWidget1H01');
	},
	
	detailDelete:function (){
		var data = Ext.getCmp('grid_01_1H01').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
    			if(button=='yes')
				{
    				Ext.Ajax.request({
    					url : 'bdef_DefWorkstationAction_delete.action',
    					params : {
    						warehouseNo:data[0].get('warehouseNo'),
    						workstationNo:data[0].get('workstationNo')
    					},
    					success : function(response) {
    						Ext.getCmp('grid_01_1H01').getStore().load();
    					}
    				});
				}			
          });
	  }else{
		  Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	  }
	},
	
	prev:function(th){
		rowindex1H01=rowindex1H01-1;
		loadWorkstationData1H01(rowindex1H01);
		commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_01_1H01',rowindex1H01);
	},
	
	next:function(th){
		rowindex1H01=rowindex1H01+1;
		loadWorkstationData1H01(rowindex1H01);
		commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_01_1H01',rowindex1H01);
	},
	
	add:function(th){
		commonSetMsterReadOnlyByArray(new Array('workstation_no1H01'),false);
		bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
		addWorkstation1H01();
		saveType1H01='add';
	},

	save:function(){
		saveWorkstation1H01();
	},
	
	close:function(){
		closeWorkstationWindow1H01();
	},
	
	printer_group1H01select:function(combo){
		Ext.Ajax.request({
			url : 'bset_GroupAction_checkBset_GroupCombo',
			params : {
				printerGroupNo:combo.getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg($i18n.prompt,$i18n_prompt.workstation_select_prompt);
		    		Ext.getCmp('printer_group1H01').setValue(null);
		    	}
			}
		});
	},
	
	workstationNoBlur:function(){
		if(saveType1H01=='add'){
			Ext.Ajax.request({
			method:'post',
			url:'bdef_DefWorkstationAction_checkWorkstationNo',
			params:{
				workstationNo:Ext.getCmp('workstation_no1H01').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg('提示','该工作站编号已有，请重新录入！');
		    		Ext.getCmp('workstation_no1H01').setValue('');
		    		Ext.getCmp('workstation_no1H01').focus();
		    	}
		    }
			});
		}
	}
});

//新增工作站初始化
function addWorkstation1H01(){
	Ext.getCmp('form_01_1H01').getForm().reset();
	Ext.getCmp('warehouse_no1H01').setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('workstation_no1H01').focus(false, 10);
	bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
};

//保存工作站
function saveWorkstation1H01(th){
	var but=th;
	if(Ext.getCmp('form_01_1H01').getForm().isValid()){
		var str={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse_no1H01').getValue(),
				workstationNo:Ext.getCmp('workstation_no1H01').getValue()
			},
				printerGroupNo:Ext.getCmp('printer_group1H01').getValue(),
				workstationName:Ext.getCmp('workstation_name1H01').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				updtName:Ext.get('workerNo').getValue(),
				updtDate:new Date()
			};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url : 'bdef_DefWorkstationAction_saveOrUpdateWorkstation.action',
			method:'post',
			params:{
				str:jsonStr
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('printer_group1H01').getStore().load();
					Ext.getCmp('grid_01_1H01').getStore().load();
					commonSetMsterReadOnlyByArray(
					new Array('workstation_no1H01'),true);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};

//关闭工作站界面
function closeWorkstationWindow1H01(){
	Ext.getCmp('bdef_DefWorkstationAddorEditWindow').close();
};

//填充数据
function loadWorkstationData1H01(rowindex1H01){
	var record=Ext.getCmp('grid_01_1H01').getStore().getAt(rowindex1H01-(Ext.getCmp('grid_01_1H01').getStore().currentPage-1)*appConfig.getPageSize());	
	Ext.getCmp('warehouse_no1H01').setValue(record.data.warehouseNo);
	Ext.getCmp('workstation_no1H01').setValue(record.data.workstationNo);
	Ext.getCmp('workstation_name1H01').setValue(record.data.workstationName);	
	Ext.getCmp('printer_group1H01').setValue(record.data.printerGroupNo);
};

	