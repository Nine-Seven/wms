/**
 * 模块名称：用户与仓别与委托业主关系维护
 * 模块编码：1201
 * 创建：Jun 
 */
Ext.define('cms.controller.bset.bset_Worker_LocController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bset.bset_Worker_LocUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//选择仓别列表
			'bset_Worker_LocUI grid[id=grid_01_1201]':{
				selectionchange:this.grid_01_1201change
			},//保存用户与仓别关系
			'bset_Worker_LocUI button[id=Worker_LocSave1201]':{
				click:this.locsave	
			},//选择委托业主列表
			'bset_Worker_LocUI grid[id=grid_03_1201]':{
				selectionchange:this.grid_03_1201change
			},//保存用户与委托业主关系
			'bset_Worker_LocUI button[id=Worker_OwnerSave1201]':{
				click:this.ownersave
			}
		});
	},
	
	initializtion:function(){
		Ext.getCmp('grid_02_1201').getStore().removeAll();
		Ext.getCmp('grid_04_1201').getStore().removeAll();
	},
	
	grid_01_1201change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var strWheresql={
				strFlag:"1",
				strWarehouseNo:record.data.warehouseNo
			};
			Ext.apply(Ext.getCmp('grid_02_1201').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_1201').getStore().removeAll();
			Ext.getCmp('grid_02_1201').getStore().load();
		}else{
			Ext.getCmp('grid_02_1201').getStore().removeAll();
		}
	},
	
	locsave:function(){
		debugger;
		var data = Ext.getCmp('grid_01_1201').getSelectionModel().getSelection();
		if(data.length!=0){
			var detail1=[];
			var record=Ext.getCmp('grid_02_1201').getStore().query('flag',true);
			for(var i=0;i<record.length;i++){
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:data[0].get('warehouseNo'),
						workerNo:record.items[i].get('workerNo')
					},
					rgstName:Ext.get('workerNo').getValue(),
					rgstDate:new Date()
				};
				detail1.push(d);
			}
			
			if(detail1.length!=0){
				var jsonDetail1 = Ext.encode(detail1);			
				Ext.Ajax.request({
					url:'bset_Worker_LocAction_saveBset_Worker_Loc',
					params : {
						str:jsonDetail1
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
				Ext.Ajax.request({
					url:'bset_Worker_LocAction_deleteBset_Worker_Loc',
					params : {
						str:data[0].get('warehouseNo')
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
		}
	},
	
	grid_03_1201change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var strWheresql={
				strWheresql:record.data.ownerNo
			};
			Ext.apply(Ext.getCmp('grid_04_1201').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_04_1201').getStore().removeAll();
			Ext.getCmp('grid_04_1201').getStore().load();
		}else{
			Ext.getCmp('grid_04_1201').getStore().removeAll();
		}
	},
	
	ownersave:function(){
		var data = Ext.getCmp('grid_03_1201').getSelectionModel().getSelection();
		if(data.length!=0){
			var detail1=[];
			var record=Ext.getCmp('grid_04_1201').getStore().query('flag',true);
			for(var i=0;i<record.length;i++){
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						ownerNo:data[0].get('ownerNo'),
						workerNo:record.items[i].get('workerNo')
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
					url:'bset_Worker_LocAction_saveBset_Worker_Owner',
					params : {
						str:jsonDetail1
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
				Ext.Ajax.request({
					url:'bset_Worker_LocAction_deleteBset_Worker_Owner',
					params : {
						str:data[0].get('ownerNo')
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
		}
	}
});