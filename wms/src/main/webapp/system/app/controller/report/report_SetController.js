/**
 * 模块名称：报表设置
 * 创建：chensr
 */
Ext.define('cms.controller.report.report_SetController',{
	extend:'Ext.app.Controller',
	requires:[
			 'cms.view.report.report_SetUI'
			 ],
	init:function(){
		this.control({
			//选择菜单
			'report_SetUI combo[id=modubleId]':{
				change:this.modubleIdChange
			},
			//选择子菜单
			'report_SetUI grid[id=grid_01_9113]':{
				selectionchange:this.grid_01_9113Selection
			},
			//TAB页切换
			'report_SetUI tabpanel[id=tabPId9113]':{
				tabchange:this.tabPidchange
			},
			//新增子报表
			'report_SetUI button[name=add]':{
				click:this.newAddSubReport
			},
			//新增子报表
			'report_SetUI button[name=edit]':{
				click:this.editSubReport
			},
			//保存子报表
			'report_SetUI button[name=save]':{
				click:this.saveAddSubReport
			},
			//修改子报表是否可见
			'wms_DefFieldValCombo[id=show9113]':{
				select:this.changeShow
			},
			//撤销维护子报表
			'report_SetUI button[name=undo]':{
				click:this.undoSubReport
			},
			//修改sql语句
			'report_SetUI button[name=editSql]':{
				click:this.editSql
			},
			//保存sql语句
			'report_SetUI button[name=saveSql]':{
				click:this.saveSql
			},
			//保存表的列
			'report_SetUI button[id=button3913]':{
				click:this.saveSearchD
			},
			//删除表的列 
			'report_SetUI button[name=detailDelete]':{
				click:this.deleteSearchD
			},
			//修改字段类型
			'wms_DefFieldValCombo[id=fieldType9113]':{
				select:this.changeFieldType
			},
			//网格编辑
			'report_SetUI grid[id=grid_02_9113]':{
				selectionchange:this.grid_02_9113Selection,
				edit:this.grid_02_9113edit
			},
			//选择查询字段类型
			'wms_DefFieldValCombo[id=xtype]':{
				select:this.changeXtype
			},
			//保存查询数据
			'report_SetUI button[id=button3913_1]':{
				click:this.saveQuery
			},
			//修改查询数据
			'report_SetUI button[name=deleteQuery]':{
				click:this.deleteQuery
			},
			//网格编辑(查询列) 
			'report_SetUI grid[id=grid_03_9113]':{
				selectionchange:this.grid_03_9113Selection,
				beforeedit:this.grid_03_9113Beforeedit,
				edit:this.grid_03_9113edit
			},
			//wms_defreportformenu上移
			'report_SetUI button[name=prevButton]':{
				click:this.prevButton
			},
			//wms_defreportformenu下移
			'report_SetUI button[name=nextButton]':{
				click:this.nextButton
			},
			//wms_defmodulequerycolumn上移
			'report_SetUI button[name=prevQuery]':{
				click:this.prevQuery
			},
			//wms_defmodulequerycolumn下移
			'report_SetUI button[name=nextQuery]':{
				click:this.nextQuery
			},
			//wms_defsearch_d上移
			'report_SetUI button[name=prev]':{
				click:this.prevD
			},
			//wms_defsearch_d下移
			'report_SetUI button[name=next]':{
				click:this.nextD
			}		
		});
	},
	//选择菜单
	modubleIdChange:function(){
		Ext.getCmp('pgmId').setValue("");
		Ext.getCmp('procName').setValue("");
		
		var strDetail = [];
		var d={
			columnId:'a.module_id',
			value:Ext.getCmp('modubleId').getValue()
		};
		strDetail.push(d);
		var str = {
				strQuery : Ext.encode(strDetail)
		};
		Ext.apply(Ext.getCmp('grid_01_9113').getStore().proxy.extraParams,str);
		Ext.getCmp('grid_01_9113').getStore().removeAll();
		Ext.getCmp('grid_01_9113').getStore().load();		
	},	
	//选择子菜单
	grid_01_9113Selection:function(){
		if(Ext.getCmp('modubleId').getValue()=='保存时自动生成'){
			return;
		}
		
		var data = Ext.getCmp('grid_01_9113').getSelectionModel().getSelection();
		if(data != 0){
			Ext.getCmp('pgmId').setValue(data[0].get("pgmId"));
			Ext.getCmp('procName').setValue(data[0].get("procName"));
			
			var params = 
			{
				pmgId:Ext.getCmp('pgmId').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_getDefSerchM',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data['needLoc']=='1'){
						Ext.getCmp('needWarehouseNo').setValue(true);
					}else{
						Ext.getCmp('needWarehouseNo').setValue(false);
					}
					
					if(data['needOwner']=='1'){
						Ext.getCmp('needOwner').setValue(true);
					}else{
						Ext.getCmp('needOwner').setValue(false);
					}			
				}
			});		
		}
	},
	//选择显示字段
	grid_02_9113Selection:function(){
		var data = Ext.getCmp('grid_02_9113').getSelectionModel().getSelection();
		if(data != 0){
			Ext.getCmp('hideFieldId').setValue(data[0].get("fieldId"));
		}
		
	},
	
	//选择查询列
	grid_03_9113Selection:function(){
		var data = Ext.getCmp('grid_03_9113').getSelectionModel().getSelection();
		if(data != 0){
			Ext.getCmp('hideColumnId').setValue(data[0].get("columnid"));
		}	
	},
	//tab页切换
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts){
		if(newCard.itemId=='tabPId9113_1'){
			if(Ext.getCmp('modubleId').getValue()!=null && Ext.getCmp('modubleId').getValue()!=''
			   && Ext.getCmp('pgmId').getValue()!=null && Ext.getCmp('pgmId').getValue()!=''&&
			   Ext.getCmp('pgmId').getValue()!='保存时自动生成'){
				
				var params = 
				{
					pmgId:Ext.getCmp('pgmId').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					params:params,
					url:'report_setAction_getDefSerchM',
					success:function(response)
					{
						data = Ext.decode(response.responseText);
						Ext.getCmp('beforeTreatment').setValue(data['beforeTreatment']);
						Ext.getCmp('preparedSql').setValue(data['preparedSql']);
						Ext.getCmp('afterTreatment').setValue(data['afterTreatment']);				
					}
				});		
				
				var strDetail = [];
				var d={
					columnId:'a.pgm_id',
					value:Ext.getCmp('pgmId').getValue()
				};
				strDetail.push(d);
				var str = {
						strQuery : Ext.encode(strDetail)
				};
				Ext.apply(Ext.getCmp('grid_02_9113').getStore().proxy.extraParams,str);
				Ext.getCmp('grid_02_9113').getStore().removeAll();
				Ext.getCmp('grid_02_9113').getStore().load();		
				
				
			}
		}else if(newCard.itemId=='tabPId9113_2'){
			if(Ext.getCmp('modubleId').getValue()!=null && Ext.getCmp('modubleId').getValue()!=''
				   && Ext.getCmp('pgmId').getValue()!=null && Ext.getCmp('pgmId').getValue()!=''&&
				   Ext.getCmp('pgmId').getValue()!='保存时自动生成'){
				var str = {

						moduleId:Ext.getCmp('pgmId').getValue()
				};
				Ext.apply(Ext.getCmp('grid_03_9113').getStore().proxy.extraParams,str);
				Ext.getCmp('grid_03_9113').getStore().removeAll();
				Ext.getCmp('grid_03_9113').getStore().load();	
			}
		}
	},
	
	//新增子报表
	newAddSubReport:function(){
		if(Ext.getCmp('modubleId').getValue()==null || Ext.getCmp('modubleId').getValue()=="" ){
			Ext.example.msg($i18n_prompt.prompt,'请选择菜单');
			return;		
		}
		
		Ext.getCmp('pgmId').setValue("保存时自动生成");
		Ext.getCmp('procName').setValue("");
		commonSetMsterReadOnlyByArray(new Array('modubleId'),true);
		commonSetMsterReadOnlyByArray(new Array('needWarehouseNo','needOwner'),false);
		
		disableButtonFunc(1,'#menu9113 [name=add]','新增');	
		disableButtonFunc(1,'#menu9113 [name=edit]','修改');	
		disableButtonFunc(0,'#menu9113 [name=save]','保存');	
		disableButtonFunc(0,'#menu9113 [name=undo]','撤销');	
		disableButtonFunc(1,'#menu9113 [name=refresh]','刷新');	
	},
	
	editSubReport:function(){
		if(Ext.getCmp('modubleId').getValue()==null || Ext.getCmp('modubleId').getValue()=="" 
		   ||Ext.getCmp('pgmId').getValue()==null || Ext.getCmp('pgmId').getValue()==""){
			Ext.example.msg($i18n_prompt.prompt,'请选择要修改的子报表');
			return;		
		}
		commonSetMsterReadOnlyByArray(new Array('modubleId'),true);
		commonSetMsterReadOnlyByArray(new Array('needWarehouseNo','needOwner'),false);
		disableButtonFunc(1,'#menu9113 [name=add]','新增');	
		disableButtonFunc(1,'#menu9113 [name=edit]','修改');	
		disableButtonFunc(0,'#menu9113 [name=save]','保存');	
		disableButtonFunc(0,'#menu9113 [name=undo]','撤销');	
		disableButtonFunc(1,'#menu9113 [name=refresh]','刷新');	
	},
	
	//保存子报表
	saveAddSubReport:function(){
		
		var params = 
		{
			modubleId:Ext.getCmp('modubleId').getValue(),
			pmgId:Ext.getCmp('pgmId').getValue(),
			procName:Ext.getCmp('procName').getValue(),
			needWarehouseNo:Ext.getCmp('needWarehouseNo').getValue()==true?1:0,
			needOwner:Ext.getCmp('needOwner').getValue()==true?1:0
		};
		Ext.Ajax.request({
			method:'POST',
			params:params,
			url:'report_setAction_saveSubReport',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_9113').getStore().load();
				}else{
					Ext.example.msg($i18n_prompt.prompt,data.msg);
				}			
			}
		});		
			
		commonSetMsterReadOnlyByArray(new Array('modubleId'),false);
		commonSetMsterReadOnlyByArray(new Array('needWarehouseNo','needOwner'),true);
		disableButtonFunc(0,'#menu9113 [name=add]','新增');
		disableButtonFunc(0,'#menu9113 [name=edit]','修改');	
		disableButtonFunc(1,'#menu9113 [name=save]','保存');	
		disableButtonFunc(1,'#menu9113 [name=undo]','撤销');	
		disableButtonFunc(0,'#menu9113 [name=refresh]','刷新');
	},
	
	//撤销维护子报表
	undoSubReport:function(){	
		disableButtonFunc(0,'#menu9113 [name=add]','新增');
		disableButtonFunc(0,'#menu9113 [name=edit]','修改');	
		disableButtonFunc(1,'#menu9113 [name=save]','保存');	
		disableButtonFunc(1,'#menu9113 [name=undo]','撤销');	
		disableButtonFunc(0,'#menu9113 [name=refresh]','刷新');
		commonSetMsterReadOnlyByArray(new Array('needWarehouseNo','needOwner'),true);
		commonSetMsterReadOnlyByArray(new Array('modubleId'),false);
	},
	//修改子报表是否可见
	changeShow:function(){
		var data = Ext.getCmp('grid_01_9113').getSelectionModel().getSelection();
		if(data != 0){
			Ext.getCmp('pgmId').setValue(data[0].get("pgmId"));
			Ext.getCmp('procName').setValue(data[0].get("procName"));
			
			var params = 
			{
				modubleId:Ext.getCmp('modubleId').getValue(),
				pmgId:Ext.getCmp('pgmId').getValue(),
				show:Ext.getCmp('show9113').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_changeShow',
				success:function(response)
				{
					data = Ext.decode(response.responseText);					
					if(data.isSucc){
						Ext.getCmp('grid_01_9113').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}				
				}
			});		
		}		
	},
	
	//修改SQL
	editSql:function(){
		if(Ext.getCmp('modubleId').getValue()==null || Ext.getCmp('modubleId').getValue()=="" 
			   ||Ext.getCmp('pgmId').getValue()==null || Ext.getCmp('pgmId').getValue()==""){
				Ext.example.msg($i18n_prompt.prompt,'请选择要修改的子报表');
				return;		
		}
		commonSetMsterReadOnlyByArray(new Array('beforeTreatment','preparedSql','afterTreatment'),false);
	},
	
	//保存SQL
	saveSql:function(){
		
		if(Ext.getCmp('modubleId').getValue()==null || Ext.getCmp('modubleId').getValue()=="" 
			   ||Ext.getCmp('pgmId').getValue()==null || Ext.getCmp('pgmId').getValue()==""){
				Ext.example.msg($i18n_prompt.prompt,'请选择要修改的子报表');
				return;		
		}
		
		var params = 
		{
			pmgId:Ext.getCmp('pgmId').getValue(),
			beforeTreatment:Ext.getCmp('beforeTreatment').getValue(),
			preparedSql:Ext.getCmp('preparedSql').getValue(),
			afterTreatment:Ext.getCmp('afterTreatment').getValue()
			
		};
		Ext.Ajax.request({
			method:'POST',
			params:params,
			url:'report_setAction_changeSql',
			success:function(response)
			{
				data = Ext.decode(response.responseText);		
				if(data.isSucc){
					Ext.example.msg($i18n_prompt.prompt,'保存成功');;
				}else{
					Ext.example.msg($i18n_prompt.prompt,data.msg);
				}				
			}
		});		
		commonSetMsterReadOnlyByArray(new Array('beforeTreatment','preparedSql','afterTreatment'),true);
	},
	//保存查询列
	saveSearchD:function(){
	
		if(Ext.getCmp('modubleId').getValue()==null || Ext.getCmp('modubleId').getValue()=="" 
			   ||Ext.getCmp('pgmId').getValue()==null || Ext.getCmp('pgmId').getValue()==""){
				Ext.example.msg($i18n_prompt.prompt,'请选择要修改的子报表');
				return;		
		}
		var master={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					pgmId:Ext.getCmp('pgmId').getValue(),
					fieldId:Ext.getCmp('fieldId').getValue()
				},
				fieldName:Ext.getCmp('fieldName').getValue(),
				fieldType:Ext.getCmp('fieldType').getValue(),
				subPgmId:0,
				width:Ext.getCmp('width').getValue(),
				statisticsFlag:Ext.getCmp('statisticsFlag').getValue()
		};
		
		var params={
				searchD:Ext.encode(master)
		};
		
		Ext.Ajax.request({
			method:'POST',
			params:params,
			url:'report_setAction_saveSearchD',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n_prompt.prompt,'保存成功');
					Ext.getCmp('grid_02_9113').getStore().removeAll();
					Ext.getCmp('grid_02_9113').getStore().load();
					///////////////////////////////////////////
//					Ext.getCmp('pgmId').setValue("");
//					Ext.getCmp('fieldId').setValue("");
				
				    Ext.getCmp('fieldName').setValue("");
			    	Ext.getCmp('fieldType').setValue("");				
				    Ext.getCmp('width').setValue("");
				    Ext.getCmp('statisticsFlag').setValue("");
					
				}else{
					Ext.example.msg($i18n_prompt.prompt,'保存失败');
				}
			}
		});		
	},
	//删除表的列 
	deleteSearchD:function(){
		var data = Ext.getCmp('grid_02_9113').getSelectionModel().getSelection();
		if(data!=0){
			var params = 
			{
				pmgId:data[0].get("pgmId"),
				fieldId:data[0].get("fieldId"),
				seq:data[0].get("seq")
				
				
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_deleteSearchD',
				success:function(response)
				{
					data = Ext.decode(response.responseText);		
					if(data.isSucc){
						Ext.example.msg($i18n_prompt.prompt,'删除成功');
						Ext.getCmp('grid_02_9113').getStore().removeAll();
						Ext.getCmp('grid_02_9113').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}	
				}
			});	
		}
	},
	//编辑网格
	grid_02_9113edit:function(editor,e,eOpts){
		var fieldName="";
		var fieldType="";
		var width="";
		var statisticsFlag="";
		var data = Ext.getCmp('grid_02_9113').getSelectionModel().getSelection();
		if(e.field=='fieldName'){
			fieldName=Ext.getCmp('fieldName9113').getValue();
		}else{
			fieldName=data[0].get("fieldName");
		}
		
		if(e.field=='width'){
			width=Ext.getCmp('width9113').getValue();
		}else{
			width=data[0].get("width");
		}
		
		if(e.field=='fieldType'){
			fieldType=Ext.getCmp('fieldType9113').getValue();
		}else{
			fieldType=data[0].get("fieldType");
		}
		
		if(e.field=='statisticsFlagText'){
			statisticsFlag=Ext.getCmp('statisticsFlag9113').getValue();
		}else{
			statisticsFlag=data[0].get("statisticsFlag");
		}	
		this.changeSearchD(fieldName,fieldType,width,statisticsFlag,e.rowIdx);
	},
	
	//修改WMS_DEFSEARCHE_D
	changeSearchD:function(fieldName,fieldType,width,statisticsFlag,indexRow){
		var data = Ext.getCmp('grid_02_9113').getStore().getAt(indexRow);
		if(data!=0){
			var master={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						pgmId:data.get("pgmId"),
						fieldId:data.get("fieldId")
					},
					fieldName:fieldName,
					fieldType:fieldType,
					width:width,
					statisticsFlag:statisticsFlag
			};
			
			var params={
					searchD:Ext.encode(master)
			};
			
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_changeSearchD',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_02_9113').getStore().removeAll();
						Ext.getCmp('grid_02_9113').getStore().load();					
					}
				}
			});			
		}		
	},
	//选择查询列的数据类型
	changeXtype:function(){
		if(Ext.getCmp('xtype').getValue()=="combo"){
			commonSetMsterReadOnlyByArray(new Array('fieldtable','fieldcolumn'),false);
		}else{
			commonSetMsterReadOnlyByArray(new Array('fieldtable','fieldcolumn'),true);
			Ext.getCmp('fieldtable').setValue('');
			Ext.getCmp('fieldcolumn').setValue('');
		}
	},
	//保存查询数据
	saveQuery:function(){	
		if(Ext.getCmp('modubleId').getValue()==null || Ext.getCmp('modubleId').getValue()=="" 
			   ||Ext.getCmp('pgmId').getValue()==null || Ext.getCmp('pgmId').getValue()==""){
				Ext.example.msg($i18n_prompt.prompt,'请选择要修改的子报表');
				return;		
		}
		var master={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					moduleId:Ext.getCmp('pgmId').getValue(),
					columnid:Ext.getCmp('columnId').getValue()
				},
				columnname:Ext.getCmp('columnname').getValue(),			
				xtype:Ext.getCmp('xtype').getValue(),				
				fieldtable:Ext.getCmp('fieldtable').getValue(),
				fieldcolumn:Ext.getCmp('fieldcolumn').getValue()
		};
		
		var params={
				searchD:Ext.encode(master)
		};
		
		Ext.Ajax.request({
			method:'POST',
			params:params,
			url:'report_setAction_saveQuery',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n_prompt.prompt,'保存成功');
					Ext.getCmp('grid_03_9113').getStore().removeAll();
					Ext.getCmp('grid_03_9113').getStore().load();
					///////////////////////////////////////////
					Ext.getCmp('columnname').setValue("");
					Ext.getCmp('fieldNameOfQuery').setValue("");
				
				    Ext.getCmp('xtype').setValue("");
			    	Ext.getCmp('fieldtable').setValue("");				
				    Ext.getCmp('fieldcolumn').setValue("");			
				}else{
					Ext.example.msg($i18n_prompt.prompt,'保存失败');
				}
			}
		});		
	
	},
	//删除查询数据
	deleteQuery:function(){
		var data = Ext.getCmp('grid_03_9113').getSelectionModel().getSelection();
		if(data!=0){

			var params = 
			{
				moduleId:Ext.getCmp('pgmId').getValue(),
				columnid:data[0].get("columnid"),
				orderNo:data[0].get("orderno")
				
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_deleteQuery',
				success:function(response)
				{
					data = Ext.decode(response.responseText);		
					if(data.isSucc){
						Ext.example.msg($i18n_prompt.prompt,'删除成功');
						Ext.getCmp('grid_03_9113').getStore().removeAll();
						Ext.getCmp('grid_03_9113').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}	
				}
			});	
		
		}
	},
	//编辑前(修改查询列)
	grid_03_9113Beforeedit:function(editor,e,eOpts){
		if(e.field=='fieldtable' || e.field=='fieldcolumn'){
			if(e.record.data.xtype=='combo'){
				 editor.cancel = false;
		        	return  true; 
			}else{
				 editor.cancel = true;
		        	return  false; 
			}
		}
	},
	//编辑，并且修改
	grid_03_9113edit:function(editor,e,eOpts){
		var columnname="";
		var xtype="";
		var fieldtable="";
		var fieldcolumn="";
		
		var data = Ext.getCmp('grid_03_9113').getStore().getAt(e.rowIdx);
		
//		var data = Ext.getCmp('grid_03_9113').getSelectionModel().getSelection();
		if(e.field=='columnname'){
			columnname=Ext.getCmp('columnname9113').getValue();
		}else{
			columnname=data.get("columnname");
		}
		
		if(e.field=='fieldtable'){
			fieldtable=Ext.getCmp('fieldtable9113').getValue();
		}else{
			fieldtable=data.get("fieldtable");
		}
		
		if(e.field=='fieldcolumn'){
			fieldcolumn=Ext.getCmp('fieldcolumn9113').getValue();
		}else{
			fieldcolumn=data.get("fieldcolumn");
		}
		
		if(e.field=='xtype'){
			xtype=Ext.getCmp('xtype9113').getValue();
			if(xtype!='combo'){
				 fieldtable="";
				 fieldcolumn="";				
			}
		}else{
			xtype=data.get("xtype");
		}		
		if(data!=0){			
			var master={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						moduleId:Ext.getCmp('pgmId').getValue(),
						columnid:data.get("columnid")
					},
					columnname:columnname,			
					xtype:xtype,				
					fieldtable:fieldtable,
					fieldcolumn:fieldcolumn
			};			
			var params={
					searchD:Ext.encode(master)
			};			
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_changeQuery',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_03_9113').getStore().removeAll();
						Ext.getCmp('grid_03_9113').getStore().load();	
					}else{
						Ext.example.msg($i18n_prompt.prompt,'更新失败');
					}
				}
			});		
		}		
	},
	
	//wms_defreportformenu上移
	prevButton:function(){
		this.sortDefreportformenuModel(1);
	},
	
	//wms_defreportformenu下移
	nextButton:function(){
		this.sortDefreportformenuModel(-1);
	},
	
	sortDefreportformenuModel:function(flag){
		
		var data = Ext.getCmp('grid_01_9113').getSelectionModel().getSelection();
		if(data.length!=0){
			var params = 
			{
				modubleId:Ext.getCmp('modubleId').getValue(),
				pmgId:Ext.getCmp('pgmId').getValue(),
				orderNo:data[0].get("orderNo"),
				flag:flag
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_seqencingDfm',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_01_9113').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}			
				}
			});				
			
		}else{
			Ext.example.msg($i18n_prompt.prompt,'请选择要移动的数据');
		}
	},
	//wms_defmodulequerycolumn上移
	prevQuery:function(){
		this.sortQueryColumn(1);
	},
	//wms_defmodulequerycolumn下移
	nextQuery:function(){
		this.sortQueryColumn(-1);
	},
	
	sortQueryColumn:function(flag){
		var data = Ext.getCmp('grid_03_9113').getSelectionModel().getSelection();
		if(data.length!=0){
			var params = 
			{				
				moduleId:Ext.getCmp('pgmId').getValue(),
				columnid:data[0].get("columnid"),
				orderNo:data[0].get("orderno"),
				flag:flag
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_seqencingQuery',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_03_9113').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}			
				}
			});				
			
		}else{
			Ext.example.msg($i18n_prompt.prompt,'请选择要移动的数据');
		}
	},
	
	//wms_defsearch_d上移
	prevD:function(){
		this.sortSearchD(1);
	},
	
	//wms_defsearch_d下移
	nextD:function(){
		this.sortSearchD(-1);
	},
	
	sortSearchD:function(flag){

		var data = Ext.getCmp('grid_02_9113').getSelectionModel().getSelection();
		if(data.length!=0){
			var params = 
			{				
				pmgId:data[0].get("pgmId"),
				fieldId:data[0].get("fieldId"),
				seq:data[0].get("seq"),
				flag:flag
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'report_setAction_seqencingSearchD',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_02_9113').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}			
				}
			});				
			
		}else{
			Ext.example.msg($i18n_prompt.prompt,'请选择要移动的数据');
		}
	
	}
	
});

