/**
 * 模块名称：储位货主关系维护
 * 模块编码：2601
 * 创建：hkl 
 */

Ext.define('cms.controller.cset.cset_CellOwnerController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cset.cset_CellOwnerUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//货主选择
			'cset_CellOwnerUI bdef_DefOwnerCombo[id=owner_no2601]':{
				select:this.ownerNo_2601change
			},//输入货位查询,货位列表
			'cset_CellOwnerUI button[id=btnQuery2601]':{
				click:this.btnQuery2601
			},//输入货位查询,货主货位关系列表
			'cset_CellOwnerUI button[id=btnQuery2601_2]':{
				click:this.btnQuery2601_2
			},
			//货主货位关系添加货位
			'cset_CellOwnerUI button[id=right2601]':{
				click:this.right2601
			},//货主货位关系移除货位
			'cset_CellOwnerUI button[id=left2601]':{
				click:this.left2601
			}
		});
	},

	
	ownerNo_2601change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				ownerNo:record.data.value
			};
			Ext.apply(Ext.getCmp('grid_02_2601').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_2601').getStore().removeAll();
			Ext.getCmp('grid_02_2601').getStore().load();
		
		}else{
			Ext.getCmp('grid_02_2601').getStore().removeAll();
		}
	},
	
	btnQuery2601:function(){
		var wheresql={
				cellNo:Ext.getCmp('cell_no2601').getValue()
			};
			Ext.apply(Ext.getCmp('grid_01_2601').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_01_2601').getStore().removeAll();
			Ext.getCmp('grid_01_2601').getStore().load();
	},
	
	btnQuery2601_2:function(){
		var wheresql={
				ownerNo:Ext.getCmp('owner_no2601').getValue(),
				cellNo:Ext.getCmp('cell_no2601_2').getValue()
			};
			Ext.apply(Ext.getCmp('grid_02_2601').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_2601').getStore().removeAll();
			Ext.getCmp('grid_02_2601').getStore().load();
	},

	
	right2601:function(){
		var ownerNo=Ext.getCmp('owner_no2601').getValue();
		var grid01=Ext.getCmp('grid_01_2601').getSelectionModel().getSelection();
	/*	var cellNo = "";
		for(var i=0;i<grid01.length;i++){
			if(cellNo.indexOf(grid01[i].get('cellNo')) != -1 && cellNo != ""){
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt1);
				return;
			}
			cellNo += grid01[i].get('cellNo');
		}*/
		
			if(ownerNo!=null){
				if(grid01.length!=0){
					var detail=[];
					for(var i=0;i<grid01.length;i++){
						var cellOwner={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:Ext.get('warehouseNo').getValue(),
								ownerNo:ownerNo,
								cellNo:grid01[i].get('cellNo'),
								updtDate:new Date()
							}
						};
						if(Ext.getCmp('grid_02_2601').getStore().findExact('cellNo',grid01[i].get('cellNo'))=='-1'){
							detail.push(cellOwner);
						}
					}
					var str=Ext.encode(detail);
					Ext.Ajax.request({
						url:'cset_CellOwnerAction_saveCellOwner',
						timeout:1800000,
						params:{
							str:str
						},
						success:function(response){
							Ext.getCmp('grid_01_2601').getStore().load();
							Ext.getCmp('grid_02_2601').getStore().load();
						}
					});
				}else{
					Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
				}
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
			}
		
	},
	
	left2601:function(){
		var ownerNo=Ext.getCmp('owner_no2601').getValue();
		//var grid01=Ext.getCmp('grid_01_1R01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_02_2601').getSelectionModel().getSelection();
		if(ownerNo!=null){
			if(grid02.length!=0){
				var detail1=[];
				for(var i=0;i<grid02.length;i++){
					detail1.push(grid02[i].get('cellNo'));
				}
				
				Ext.Ajax.request({
					url:'cset_CellOwnerAction_deleteCellOwner',
					timeout:1800000,
					params:{
						ownerNo:ownerNo,
						cellNo:detail1,
					},
					success:function(response){
						Ext.getCmp('grid_01_2601').getStore().load();
						Ext.getCmp('grid_02_2601').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	},
	
});

