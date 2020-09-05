/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
var rowindex1T01=0;
var saveType1T01='add';
Ext.define('cms.controller.bdef.pntdef_PrinterGrpgatherController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.pntdef_PrinterGrpgatherUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'pntdef_PrinterGrpgatherUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'pntdef_PrinterGrpgatherUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'pntdef_PrinterGrpgatherUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'pntdef_PrinterGrpgatherUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导出
			'pntdef_PrinterGrpgatherUI button[name=detailExport]':{
				click:this.detailExport
			},//GRID选择
			'pntdef_PrinterGrpgatherUI grid[id=grid_01_1T01]':{
				//itemclick:this.grid_01_1T01Click
				selectionchange:this.grid_01_1T01change
			},//上一条记录
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=next]':{
				click:this.next
			},//新增前加载
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=add]':{
				click:this.add
			},//保存
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=close]':{
				click:this.close
			},//群组添加打印机组按扭
			'pntdef_PrinterGrpgatherUI button[id=right1T01]':{
				click:this.right1T01
			},//群组移除打印机组按扭
			'pntdef_PrinterGrpgatherUI button[id=left1T01]':{
				click:this.left1T01
			},//验证群组代码唯一性
			'pntdef_PrinterGrpgatherAddorEditWindow form textfield[id=grpgather_no1T01]':{
				blur:this.grpgatherNoBlur
			}
		});
	},
	
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addGroup1T01();
		addCommMenu5('menuWidget51T01');
		saveType1T01='add';
	},
	
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1T01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			rowindex1T01=data[0].index;
			loadGroup1T01(rowindex1T01);
			commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_01_1T01',rowindex1T01);
			Ext.getCmp('grpgather_no1T01').disable();
			updateCommMenu5('menuWidget51T01');
			saveType1T01='edit';
		}
		
	},
	
	detailBrowse:function(){
		var data=Ext.getCmp('grid_01_1T01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
		}else{
			Ext.create('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow',{
				title:$i18n.titlebrowse
			}).show();
			rowindex1T01=data[0].index;
			loadGroup1T01(rowindex1T01);
			commonSetFieldReadOnly('pntdef_PrinterGrpgatherAddorEditWindow',true);
			commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_01_1T01',rowindex1T01);
			browseCommMenu5('menuWidget51T01');
			saveType1T01='browse';

		}
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1T01';
		queryGrid='grid_01_1T01';
	},
	
	detailExport:function(){
		commExport('grid_01_1T01');
	},
	
	grid_01_1T01change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.grpgatherNo
			};
			Ext.apply(Ext.getCmp('grid_03_1T01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_1T01').getStore().removeAll();
			Ext.getCmp('grid_03_1T01').getStore().load();
		}else{
			Ext.getCmp('grid_03_1T01').getStore().removeAll();
		}
	},
	
	prev:function(){
		rowindex1T01=rowindex1T01-1;
		loadGroup1T01(rowindex1T01);
		commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_01_1T01',rowindex1T01);
	},
	
	next:function(){
		rowindex1T01=rowindex1T01+1;
		loadGroup1T01(rowindex1T01);
		commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_01_1T01',rowindex1T01);
	},
	
	add:function(){
		addGroup1T01();
		saveType1T01='add';
	},
	
	save:function(){
		saveGroup1T01();
	},
	
	close:function(){
		Ext.getCmp('pntdef_PrinterGrpgatherAddorEditWindow').close();
	},
	
	right1T01:function(){
		var grid01=Ext.getCmp('grid_01_1T01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_02_1T01').getSelectionModel().getSelection();		

		if(grid01.length!=0){
			if(grid02.length!=0){
				var detail=[];
				for(var i=0;i<grid02.length;i++){
					var printerGroup={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							grpgatherNo:grid01[0].get('grpgatherNo'),
							printerGroupNo:grid02[i].get('printerGroupNo')
						},
						rgstName:Ext.get('workerNo').getValue(),
						rgstDate:new Date()
					};
					detail.push(printerGroup);

				}
				var str=Ext.encode(detail);
				Ext.Ajax.request({
					url:'pntdef_PrinterGrpgatherAction_savePrinter_Group',
					params:{
						str:str
					},
					success:function(response){
						Ext.getCmp('grid_02_1T01').getStore().load();
						Ext.getCmp('grid_03_1T01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	
	},
	
	left1T01:function(){
		var grid01=Ext.getCmp('grid_01_1T01').getSelectionModel().getSelection();
		var grid03=Ext.getCmp('grid_03_1T01').getSelectionModel().getSelection();
		if(grid01.length!=0){
			if(grid03.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				for(var i=0;i<grid03.length;i++){
					detail1.push(grid03[i].get('warehouseNo'));
					detail2.push(grid03[i].get('grpgatherNo'));
					detail3.push(grid03[i].get('printerGroupNo'));
				}
				
				Ext.Ajax.request({
					url:'pntdef_PrinterGrpgatherAction_deletePrinter_Group',
					params:{
						warehouseNo:detail1,
						grpgatherNo:detail2,
						printerGroupNo:detail3
					},
					success:function(response){
						Ext.getCmp('grid_02_1T01').getStore().load();
						Ext.getCmp('grid_03_1T01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	},
	
	grpgatherNoBlur:function(){
		if(saveType1T01=='add'){
			Ext.Ajax.request({
				method:'post',
				url:'pntdef_PrinterGrpgatherAction_checkGrpgatherNo',
				params:{
					grpgatherNo:Ext.getCmp('grpgather_no1T01').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg('提示','该群组代码已有，请重新录入！');
			    		Ext.getCmp('grpgather_no1T01').setValue('');
			    		Ext.getCmp('grpgather_no1T01').focus();
			    	}
			    }
			});
		}
	}
});

//新增前加载
function addGroup1T01(){
	Ext.getCmp('form_01_1T01').getForm().reset();
	Ext.getCmp('warehouse1T01').setValue(Ext.get('warehouseNo').getValue());
};

//填充数据
function loadGroup1T01(rowindex1T01){
	var group=Ext.getCmp('grid_01_1T01').getStore().getAt(rowindex1T01-(Ext.getCmp('grid_01_1T01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('warehouse1T01').setValue(group.data.warehouseNo);
	Ext.getCmp('grpgather_no1T01').setValue(group.data.grpgatherNo);
	Ext.getCmp('grpgather_name1T01').setValue(group.data.grpgatherName);
};

//保存
function saveGroup1T01(){
	 
	if(Ext.getCmp('form_01_1T01').getForm().isValid()){
		
		var group={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse1T01').getValue(),
				grpgatherNo:Ext.getCmp('grpgather_no1T01').getValue()
			},
			grpgatherName:Ext.getCmp('grpgather_name1T01').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(group);
		Ext.Ajax.request({
			url:'pntdef_PrinterGrpgatherAction_saveOrUpdateGroup',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1T01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	};
};