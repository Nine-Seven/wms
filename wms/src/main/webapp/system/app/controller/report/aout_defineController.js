/**
 * 模块名称：报表设置
 * 创建：chensr
 */
Ext.define('cms.controller.report.aout_defineController',{
	extend:'Ext.app.Controller',
	requires:[
			 'cms.view.report.aout_defineUI'
			 ],
	init:function(){
		this.control({
			//选择菜单
			'aout_defineUI combo[id=modubleIdG102]':{
				change:this.modubleIdChange
			},
			//选择子菜单
			'aout_defineUI grid[id=grid_01_G102]':{
				selectionchange:this.grid_01_G102Selection
			},
			//TAB页切换
			'aout_defineUI tabpanel[id=tabPIdG102]':{
				tabchange:this.tabPidchange
			},
			//新增子报表
			'aout_defineUI button[name=add]':{
				click:this.newAddSubReport
			},
			//新增子报表
			'aout_defineUI button[name=edit]':{
				click:this.editSubReport
			},
			//保存子报表
			'aout_defineUI button[name=save]':{
				click:this.saveAddSubReport
			},
			//修改子报表是否可见
			'wms_DefFieldValCombo[id=showG102]':{
				select:this.changeShow
			},
			//撤销维护子报表
			'aout_defineUI button[name=undo]':{
				click:this.undoSubReport
			},
					
			//wms_defreportformenu上移
			'aout_defineUI button[name=prevButton]':{
				click:this.prevButton
			},
			//wms_defreportformenu下移
			'aout_defineUI button[name=nextButton]':{
				click:this.nextButton
			},
			//保存参数
			'aout_defineUI button[id=btnG102]':{
				click:this.saveButton
			}
			
		});
	},
	//选择菜单
	modubleIdChange:function(){
		Ext.getCmp('customIdG102').setValue("");
		Ext.getCmp('customNameG102').setValue("");
		
		var strDetail = [];
		var d={
			columnId:'a.module_id',
			value:Ext.getCmp('modubleIdG102').getValue()
		};
		strDetail.push(d);
		var str = {
				strQuery : Ext.encode(strDetail)
		};
		Ext.apply(Ext.getCmp('grid_01_G102').getStore().proxy.extraParams,str);
		Ext.getCmp('grid_01_G102').getStore().removeAll();
		Ext.getCmp('grid_01_G102').getStore().load();		
	},	
	
	
	//选择子菜单
	grid_01_G102Selection:function(){
		if(Ext.getCmp('modubleIdG102').getValue()=='保存时自动生成'){
			return;
		}
		
		var data = Ext.getCmp('grid_01_G102').getSelectionModel().getSelection();
		if(data != 0){
			Ext.getCmp('customIdG102').setValue(data[0].get("customId"));
			Ext.getCmp('customNameG102').setValue(data[0].get("customName"));
				
		}
	},
	
	//tab页切换
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts){
		if(newCard.itemId=='tabPIdG102_1'){
			if(Ext.getCmp('modubleIdG102').getValue()!=null && Ext.getCmp('modubleIdG102').getValue()!=''
			   && Ext.getCmp('customIdG102').getValue()!=null && Ext.getCmp('customIdG102').getValue()!=''&&
			   Ext.getCmp('customIdG102').getValue()!='保存时自动生成'){
			   var params = 
				{
					customId:Ext.getCmp('customIdG102').getValue()
				};
				
				Ext.Ajax.request({
					method:'POST',
					params:params,
					url:'auto_SetAction_getCustomD',
					success:function(response)
					{
						var data = Ext.decode(response.responseText);
						for(var i=0; i<data.length;i++){							
							Ext.getCmp('fieldId'+i).setValue(data[i].paramName);
							Ext.getCmp('fieldType'+i).setValue(data[i].paramType);
							Ext.getCmp('tableName'+i).setValue(data[i].tablename);
							Ext.getCmp('fieldName'+i).setValue(data[i].columnname);							
						}
						
						for(i=data.length;i<10;i++){
							Ext.getCmp('fieldId'+i).setValue('');
							Ext.getCmp('fieldType'+i).setValue('');
							Ext.getCmp('tableName'+i).setValue('');
							Ext.getCmp('fieldName'+i).setValue('');	
						}
					}
				});		
				
				
			}else{
				Ext.example.msg('提示','没选择对应的子菜单');
				for(var i=0; i<10;i++){
					Ext.getCmp('fieldId'+i).setValue('');
					Ext.getCmp('fieldType'+i).setValue('');
					Ext.getCmp('tableName'+i).setValue('');
					Ext.getCmp('fieldName'+i).setValue('');							
				}
			}
		}
	},
	
	//新增子报表
	newAddSubReport:function(){
		if(Ext.getCmp('modubleIdG102').getValue()==null || Ext.getCmp('modubleIdG102').getValue()=="" ){
			Ext.example.msg($i18n_prompt.prompt,'请选择菜单');
			return;		
		}
		
		Ext.getCmp('customIdG102').setValue("保存时自动生成");
		Ext.getCmp('customNameG102').setValue("");
		commonSetMsterReadOnlyByArray(new Array('modubleIdG102'),true);
		
		
		disableButtonFunc(1,'#menuG102 [name=add]','新增');	
		disableButtonFunc(1,'#menuG102 [name=edit]','修改');	
		disableButtonFunc(0,'#menuG102 [name=save]','保存');	
		disableButtonFunc(0,'#menuG102 [name=undo]','撤销');	
		disableButtonFunc(1,'#menuG102 [name=refresh]','刷新');	
	},
	
	editSubReport:function(){
		if(Ext.getCmp('modubleIdG102').getValue()==null || Ext.getCmp('modubleIdG102').getValue()=="" 
		   ||Ext.getCmp('customIdG102').getValue()==null || Ext.getCmp('customIdG102').getValue()==""){
			Ext.example.msg($i18n_prompt.prompt,'请选择要修改的子报表');
			return;		
		}
		commonSetMsterReadOnlyByArray(new Array('modubleIdG102'),true);
	
		disableButtonFunc(1,'#menuG102 [name=add]','新增');	
		disableButtonFunc(1,'#menuG102 [name=edit]','修改');	
		disableButtonFunc(0,'#menuG102 [name=save]','保存');	
		disableButtonFunc(0,'#menuG102 [name=undo]','撤销');	
		disableButtonFunc(1,'#menuG102 [name=refresh]','刷新');	
	},
	
	//保存子报表
	saveAddSubReport:function(){
		
		var params = 
		{
			modubleId:Ext.getCmp('modubleIdG102').getValue(),
			customId:Ext.getCmp('customIdG102').getValue(),
			customName:Ext.getCmp('customNameG102').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			params:params,
			url:'auto_SetAction_saveSubCustom',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_G102').getStore().load();
				}else{
					Ext.example.msg($i18n_prompt.prompt,data.msg);
				}			
			}
		});		
			
		commonSetMsterReadOnlyByArray(new Array('modubleIdG102'),false);
		
		disableButtonFunc(0,'#menuG102 [name=add]','新增');
		disableButtonFunc(0,'#menuG102 [name=edit]','修改');	
		disableButtonFunc(1,'#menuG102 [name=save]','保存');	
		disableButtonFunc(1,'#menuG102 [name=undo]','撤销');	
		disableButtonFunc(0,'#menuG102 [name=refresh]','刷新');
	},
	
	//撤销维护子报表
	undoSubReport:function(){	
		disableButtonFunc(0,'#menuG102 [name=add]','新增');
		disableButtonFunc(0,'#menuG102 [name=edit]','修改');	
		disableButtonFunc(1,'#menuG102 [name=save]','保存');	
		disableButtonFunc(1,'#menuG102 [name=undo]','撤销');	
		disableButtonFunc(0,'#menuG102 [name=refresh]','刷新');
		
		commonSetMsterReadOnlyByArray(new Array('modubleIdG102'),false);
	},
	//修改子报表是否可见
	changeShow:function(){
		var data = Ext.getCmp('grid_01_G102').getSelectionModel().getSelection();
		if(data != 0){
			debugger;
			Ext.getCmp('customIdG102').setValue(data[0].get("customId"));
			Ext.getCmp('customNameG102').setValue(data[0].get("customName"));
			
			var params = 
			{
				modubleId:Ext.getCmp('modubleIdG102').getValue(),
				customId:Ext.getCmp('customIdG102').getValue(),
				show:Ext.getCmp('showG102').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'auto_SetAction_changeShow',
				success:function(response)
				{
					data = Ext.decode(response.responseText);					
					if(data.isSucc){
						Ext.getCmp('grid_01_G102').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}				
				}
			});		
		}		
	},
	

	//wms_defreportformenu上移
	prevButton:function(){
		this.sortCustomformenuModel(1);
	},
	
	//wms_defreportformenu下移
	nextButton:function(){
		this.sortCustomformenuModel(-1);
	},
	
	sortCustomformenuModel:function(flag){
		
		var data = Ext.getCmp('grid_01_G102').getSelectionModel().getSelection();
		if(data.length!=0){
			debugger;
			var params = 
			{
				modubleId:Ext.getCmp('modubleIdG102').getValue(),
				customId:Ext.getCmp('customIdG102').getValue(),
				orderNo:data[0].get("seq"),
				flag:flag
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'auto_SetAction_seqencingDfm',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_01_G102').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}			
				}
			});				
			
		}else{
			Ext.example.msg($i18n_prompt.prompt,'请选择要移动的数据');
		}
	},
	
	//保存参数明细
	saveButton:function(){
		var detail=[];
		var count=0;
		for(var i=0;i<10;i++){
			if(Ext.getCmp('fieldId'+i).getValue()!=null && Ext.getCmp('fieldId'+i).getValue()!=""){
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						customId:Ext.getCmp('customIdG102').getValue(),
						paramName:Ext.getCmp('fieldId'+i).getValue()
					},
					paramType:Ext.getCmp('fieldType'+i).getValue(),
					tablename:Ext.getCmp('tableName'+i).getValue(),
					columnname:Ext.getCmp('fieldName'+i).getValue(),
					seq:count++
				}
				detail.push(d);				
			}			
		}	
		
		var params={
			wmsCustomD:Ext.encode(detail)
		}
			
		Ext.Ajax.request({
			method:'post',
			url:'auto_SetAction_saveCustomD',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);				
				Ext.example.msg('提示',data.msg);				
			}
		});
	}
	
});

