/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
var rowindex1U01=0;
var saveType1U01='add';
Ext.define('cms.controller.bset.bset_WorkstationDivideController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bset.bset_WorkstationDivideUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'bset_WorkstationDivideUI button[name=detailQuery]':{
				click:this.detailQuery
			},//GRID选择
			'bset_WorkstationDivideUI grid[id=grid_01_1U01]':{
				//itemclick:this.grid_01_1U01Click
				selectionchange:this.grid_01_1U01change
			},//设备组添加工作站按扭
			'bset_WorkstationDivideUI button[id=right1U01]':{
				click:this.right1U01
			},//设备组移除工作站按扭
			'bset_WorkstationDivideUI button[id=left1U01]':{
				click:this.left1U01
			}
		});
	},
	
	
	
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1U01';
		queryGrid='grid_01_1U01';
	},
		
	grid_01_1U01change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.deviceGroupNo
			};
			Ext.apply(Ext.getCmp('grid_03_1U01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_1U01').getStore().removeAll();
			Ext.getCmp('grid_03_1U01').getStore().load();
		}else{
			Ext.getCmp('grid_03_1U01').getStore().removeAll();
		}
	},
	
	
	right1U01:function(){
		var grid01=Ext.getCmp('grid_01_1U01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_02_1U01').getSelectionModel().getSelection();		

		if(grid01.length!=0){
			if(grid02.length!=0){
				var detail=[];
				for(var i=0;i<grid02.length;i++){
					var printerGroup={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							workstationNo:grid02[i].get('workstationNo'),
							deviceGroupNo:grid01[0].get('deviceGroupNo')
						},
						deviceGroupName:grid01[0].get('deviceGroupName'),
						rgstName:Ext.get('workerNo').getValue(),
						rgstDate:new Date()
					};
					detail.push(printerGroup);

				}
				var str=Ext.encode(detail);
				Ext.Ajax.request({
					url:'bset_workstationDivideAction_saveWorkstationDivide',
					params:{
						str:str
					},
					success:function(response){
						Ext.getCmp('grid_02_1U01').getStore().load();
						Ext.getCmp('grid_03_1U01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	
	},
	
	left1U01:function(){
		var grid01=Ext.getCmp('grid_01_1U01').getSelectionModel().getSelection();
		var grid03=Ext.getCmp('grid_03_1U01').getSelectionModel().getSelection();
		if(grid01.length!=0){
			if(grid03.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				for(var i=0;i<grid03.length;i++){
					detail1.push(grid03[i].get('warehouseNo'));
					detail2.push(grid03[i].get('deviceGroupNo'));
					detail3.push(grid03[i].get('workstationNo'));
				}
				
				Ext.Ajax.request({
					url:'bset_workstationDivideAction_deleteWorkstationDivide',
					params:{
						warehouseNo:detail1,
						deviceGroupNo:detail2,
						workstationNo:detail3
					},
					success:function(response){
						Ext.getCmp('grid_02_1U01').getStore().load();
						Ext.getCmp('grid_03_1U01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	}
	
});

