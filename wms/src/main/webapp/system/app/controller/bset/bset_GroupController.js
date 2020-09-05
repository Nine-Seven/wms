/**
 * 模块名称：打印机与打印机组关系维护
 * 模块编码：1G01
 * 创建：Jun 
 */
var rowindex1G01=0;
var saveType1G01='add';
Ext.define('cms.controller.bset.bset_GroupController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bset.bset_GroupUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'bset_GroupUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'bset_GroupUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bset_GroupUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'bset_GroupUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导出
			'bset_GroupUI button[name=detailExport]':{
				click:this.detailExport
			},//GRID选择
			'bset_GroupUI grid[id=grid_01_1G01]':{
				//itemclick:this.grid_01_1G01Click
				selectionchange:this.grid_01_1G01change
			},//上一条记录
			'bset_GroupAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bset_GroupAddorEditWindow button[name=next]':{
				click:this.next
			},//新增前加载
			'bset_GroupAddorEditWindow button[name=add]':{
				click:this.add
			},//保存
			'bset_GroupAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bset_GroupAddorEditWindow button[name=close]':{
				click:this.close
			},//机组添加打印机按扭
			'bset_GroupUI button[id=right1G01]':{
				click:this.right1G01
			},//机组移除打印机按扭
			'bset_GroupUI button[id=left1G01]':{
				click:this.left1G01
			},//验证群组代码唯一性
			'bset_GroupAddorEditWindow form textfield[id=printer_group_no1G01]':{
				blur:this.printerGroupNoBlur
			}
		});
	},
	
	detailAdd:function(){
		Ext.create('cms.view.bset.window.bset_GroupAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addGroup1G01();
		addCommMenu5('menuWidget51G01');
		saveType1G01='add';
	},
	
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1G01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bset.window.bset_GroupAddorEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			rowindex1G01=data[0].index;
			loadGroup1G01(rowindex1G01);
			commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_01_1G01',rowindex1G01);
			Ext.getCmp('printer_group_no1G01').disable();
			updateCommMenu5('menuWidget51G01');
			saveType1G01='edit';
		}
		
	},
	
	detailBrowse:function(){
		var data=Ext.getCmp('grid_01_1G01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
		}else{
			Ext.create('cms.view.bset.window.bset_GroupAddorEditWindow',{
				title:$i18n.titlebrowse
			}).show();
			rowindex1G01=data[0].index;
			loadGroup1G01(rowindex1G01);
			commonSetFieldReadOnly('bset_GroupAddorEditWindow',true);
			commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_01_1G01',rowindex1G01);
			browseCommMenu5('menuWidget51G01');
		}
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1G01';
		queryGrid='grid_01_1G01';
	},
	
	detailExport:function(){
		commExport('grid_01_1G01');
	},
	
	grid_01_1G01change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.printerGroupNo
			};
			Ext.apply(Ext.getCmp('grid_03_1G01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_1G01').getStore().removeAll();
			Ext.getCmp('grid_03_1G01').getStore().load();
			
			Ext.apply(Ext.getCmp('grid_02_1G01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_1G01').getStore().removeAll();
			Ext.getCmp('grid_02_1G01').getStore().load();		
		}else{
			Ext.getCmp('grid_03_1G01').getStore().removeAll();
		}
	},
	
	prev:function(){
		rowindex1G01=rowindex1G01-1;
		loadGroup1G01(rowindex1G01);
		commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_01_1G01',rowindex1G01);
	},
	
	next:function(){
		rowindex1G01=rowindex1G01+1;
		loadGroup1G01(rowindex1G01);
		commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_01_1G01',rowindex1G01);
	},
	
	add:function(){
		addGroup1G01();
		saveType1G01='add';
	},
	
	save:function(){
		saveGroup1G01();
	},
	
	close:function(){
		Ext.getCmp('bset_GroupAddorEditWindow').close();
	},
	
	right1G01:function(){
		var grid01=Ext.getCmp('grid_01_1G01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_02_1G01').getSelectionModel().getSelection();
		var printerType = "";
		var flag="";
		for(var i=0;i<grid02.length;i++){
			if(printerType.indexOf(grid02[i].get('printerType')) != -1 && printerType != ""){
				//alert(grid02[i].get('printerType'));
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt1);
				return;
			}
			printerType += grid02[i].get('printerType');
		}
		//**************************************************************
		for(var i=0;i<grid02.length;i++){
			Ext.Ajax.request({
				url:'bset_GroupAction_checkPrinterType',
				params:{
					warehouseNo:Ext.get('warehouseNo').getValue(),
					printerGroupNo:grid01[0].get('printerGroupNo'),
					printerType:grid02[i].get('paper_type_no')
					
				},
				async : false,
				success:function(response){
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		flag=res;
			    		return ;
			    	}
				}
			});
		}
		if(flag!=''){
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt1);
		}
		//**************************************************************
		if(flag=='' || flag==null){
			if(grid01.length!=0){
				if(grid02.length!=0){
					var detail=[];
					for(var i=0;i<grid02.length;i++){
						var printerGroup={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:Ext.get('warehouseNo').getValue(),
								printerGroupNo:grid01[0].get('printerGroupNo'),
								printerNo:grid02[i].get('printerNo')
							},
							rgstName:Ext.get('workerNo').getValue(),
							rgstDate:new Date()
						};
						if(Ext.getCmp('grid_03_1G01').getStore().findExact('printerType',grid02[i].get('printerType'))=='-1'){
							detail.push(printerGroup);
						}
					}
					var str=Ext.encode(detail);
					Ext.Ajax.request({
						url:'bset_GroupAction_savePrinter_Group',
						params:{
							str:str
						},
						success:function(response){
							Ext.getCmp('grid_02_1G01').getStore().load();
							Ext.getCmp('grid_03_1G01').getStore().load();
						}
					});
				}else{
					Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
				}
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
			}
		}
	},
	
	left1G01:function(){
		var grid01=Ext.getCmp('grid_01_1G01').getSelectionModel().getSelection();
		var grid03=Ext.getCmp('grid_03_1G01').getSelectionModel().getSelection();
		if(grid01.length!=0){
			if(grid03.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				for(var i=0;i<grid03.length;i++){
					detail1.push(grid03[i].get('warehouseNo'));
					detail2.push(grid03[i].get('printerGroupNo'));
					detail3.push(grid03[i].get('printerNo'));
				}
				
				Ext.Ajax.request({
					url:'bset_GroupAction_deletePrinter_Group',
					params:{
						warehouseNo:detail1,
						printerGroupNo:detail2,
						printerNo:detail3
					},
					success:function(response){
						Ext.getCmp('grid_02_1G01').getStore().load();
						Ext.getCmp('grid_03_1G01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	},
	
	printerGroupNoBlur:function(){
		if(saveType1G01=='add'){
			Ext.Ajax.request({
				method:'post',
				url:'bset_GroupAction_checkPrinterGroupNo',
				params:{
					printerGroupNo:Ext.getCmp('printer_group_no1G01').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res=='1'){
			    		Ext.example.msg('提示','该群组代码已有，请重新录入！');
			    		Ext.getCmp('printer_group_no1G01').setValue('');
			    		Ext.getCmp('printer_group_no1G01').focus();
			    	}
			    }
			});
		}
	}
});

//新增前加载
function addGroup1G01(){
	Ext.getCmp('form_01_1G01').getForm().reset();
	Ext.getCmp('warehouse1G01').setValue(Ext.get('warehouseNo').getValue());
};

//填充数据
function loadGroup1G01(rowindex1G01){
	var group=Ext.getCmp('grid_01_1G01').getStore().getAt(rowindex1G01-(Ext.getCmp('grid_01_1G01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('warehouse1G01').setValue(group.data.warehouseNo);
	Ext.getCmp('printer_group_no1G01').setValue(group.data.printerGroupNo);
	Ext.getCmp('printer_group_name1G01').setValue(group.data.printerGroupName);
};

//保存
function saveGroup1G01(){
	 
	if(Ext.getCmp('form_01_1G01').getForm().isValid()){
		
		var group={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse1G01').getValue(),
				printerGroupNo:Ext.getCmp('printer_group_no1G01').getValue()
			},
			printerGroupName:Ext.getCmp('printer_group_name1G01').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(group);
		Ext.Ajax.request({
			url:'bset_GroupAction_saveOrUpdateGroup',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1G01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	};
};