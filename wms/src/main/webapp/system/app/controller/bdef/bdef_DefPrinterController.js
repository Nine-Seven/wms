/**
 * 模块名称：打印机维护
 * 模块编码：1F01
 * 创建：Jun
 * 修改：czh
 * 时间：2016-5-11
 * 原因：整合之前打印机维护四个模块为tab
 */
var rowindex1F01=0;
var saveType1F01='';
Ext.define('cms.controller.bdef.bdef_DefPrinterController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_DefPrinterUI',
	          'cms.view.bdef.window.bdef_DefPrinterAddorEditWindow'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'bdef_DefPrinterUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'bdef_DefPrinterUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bdef_DefPrinterUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'bdef_DefPrinterUI button[name=detailQuery]':{
				click:this.detailQuery	
			},//导出
			'bdef_DefPrinterUI button[name=detailExport]':{
				click:this.detailExport
			},//tab切换
			'bdef_DefPrinterUI tabpanel[id=tabPId1F01]':{
				tabchange:this.tabchange
			},//GRID选择
			'bdef_DefPrinterUI grid[id=grid_02_01_1F01]':{
				//itemclick:this.grid_01_1G01Click
				selectionchange:this.grid_02_01_1F01change
			},//GRID选择
			'bdef_DefPrinterUI grid[id=grid_04_01_1F01]':{
				//itemclick:this.grid_01_1G01Click
				selectionchange:this.grid_04_01_1F01change
			},//上一条记录(打印机)
			'bdef_DefPrinterAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录(打印机)
			'bdef_DefPrinterAddorEditWindow button[name=next]':{
				click:this.next
			},//上一条记录(打印机组)
			'bset_GroupAddorEditWindow button[name=prev]':{
				click:this.prevBset_Group
			},//下一条记录(打印机组)
			'bset_GroupAddorEditWindow button[name=next]':{
				click:this.nextBset_Group
			},//上一条记录(工作站)
			'bdef_DefWorkstationAddorEditWindow button[name=prev]':{
				click:this.prevBdef_DefWorkstation
			},//下一条记录(工作站)
			'bdef_DefWorkstationAddorEditWindow button[name=next]':{
				click:this.nextBdef_DefWorkstation
			},//上一条记录(打印机群组)
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=prev]':{
				click:this.prevPntdef_PrinterGrpgather
			},//下一条记录(打印机群组)
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=next]':{
				click:this.nextPntdef_PrinterGrpgather
			},//新增前加载(打印机)
			'bdef_DefPrinterAddorEditWindow button[name=add]':{
				click:this.add
			},//新增前加载(打印机组)
			'bset_GroupAddorEditWindow button[name=add]':{
				click:this.addBset_Group
			},//新增前加载(工作站)
			'bdef_DefWorkstationAddorEditWindow button[name=add]':{
				click:this.addBdef_DefWorkstation
			},//新增前加载(打印机群组)
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=add]':{
				click:this.addPntdef_PrinterGrpgather
			},//保存打印机
			'bdef_DefPrinterAddorEditWindow button[name=save]':{
				click:this.save
			},//窗口保存打印机组:bset_GroupAddorEditWindow
			'bset_GroupAddorEditWindow button[name=save]':{
				click:this.saveBset_Group
			},//窗口保存工作站:bdef_DefWorkstationAddorEditWindow
			'bdef_DefWorkstationAddorEditWindow button[name=save]':{
				click:this.saveBdef_DefWorkstation
			},//窗口保存打印机群组:pntdef_PrinterGrpgatherAddorEditWindow
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=save]':{
				click:this.savePntdef_PrinterGrpgather
			},//打印机组添加打印机按扭
			'bdef_DefPrinterUI button[id=right01_1F01]':{
				click:this.right01_1F01
			},//打印机组移除打印机按扭
			'bdef_DefPrinterUI button[id=left01_1F01]':{
				click:this.left01_1F01
			},//群组添加打印机按扭
			'bdef_DefPrinterUI button[id=right02_1F01]':{
				click:this.right02_1F01
			},//群组移除打印机组按扭
			'bdef_DefPrinterUI button[id=left02_1F01]':{
				click:this.left02_1F01
			},//关闭
			'bdef_DefPrinterAddorEditWindow button[name=close]':{
				click:this.close
			},//关闭窗口(打印机组与打印机关系维护)
			'bset_GroupAddorEditWindow button[name=close]':{
				click:this.closeBset_Group
			},//关闭窗口(工作站与打印机组关系维护)
			'bdef_DefWorkstationAddorEditWindow button[name=close]':{
				click:this.closeBdef_DefWorkstation
			},//关闭窗口(打印机群组与打印机组关系维护)
			'pntdef_PrinterGrpgatherAddorEditWindow button[name=close]':{
				click:this.closePntdef_PrinterGrpgather
			},//验证打印机编号的唯一性
			'bdef_DefPrinterAddorEditWindow form textfield[id=printer_no1F01]':{
				blur:this.printerNoBlur
			},//验证工作站编号唯一性
			'bdef_DefWorkstationAddorEditWindow form textfield[id=workstation_no1H01]':{
				blur:this.workstationNoBlur
			},//验证群组代码唯一性
			'pntdef_PrinterGrpgatherAddorEditWindow form textfield[id=grpgather_no1T01]':{
				blur:this.grpgatherNoBlur
			},//验证组代码唯一性
			'bset_GroupAddorEditWindow form textfield[id=printer_group_no1G01]':{
				blur:this.printerGroupNoBlur
			},//打印机群组与打印机组关系维护-设置光标跳到下一输入框
			'bdef_DefPrinterAddorEditWindow field':{
				specialkey:this.boxkeydown
			},//工作站与打印机组关系维护-设置光标跳到下一输入框
			'bdef_DefWorkstationAddorEditWindow field':{
				specialkey:this.boxkeydown
			},//打印机编码选择
			'remoteCombo[id=printerNo1F01]':{
				beforequery:this.printerNoBeforeQuery1F01,//打印机编码加载
				select:this.printerNoSelect1F01//打印机编码选择
			},//打印机类型选择
			'wms_DefFieldValCombo[id=printerTypeCombo1F01]':{				
				select:this.printerTypeComboSelect1F01//打印机类型选择
			},//打印机状态选择
			'wms_DefFieldValCombo[id=statusCombo1F01]':{				
				select:this.statusCombo1F01Select1F01//打印机类型选择
			},//工作站编码选择
			'remoteCombo[id=workStation1H01]':{
				beforequery:this.workStationNoBeforeQuery,//工作站编码加载
				select:this.workStationNoSelect1H01//工作站编码选择
			},//打印机群组编码选择
			'remoteCombo[id=printerGroupNo1H01]':{
				beforequery:this.printerGroupNoBeforeQuery,//打印机群组编码加载
				select:this.printerGroupNoSelect1H01//打印机群组编码选择
			},//打印机编码选择
			'remoteCombo[id=printerNo1G01]':{
				beforequery:this.printerNoBeforeQuery1G01,//打印机编码加载
				select:this.printerNoSelect1G01//打印机编码选择
			},//打印机组编码选择
			'remoteCombo[id=printerGroupNo1T01]':{
				beforequery:this.printerGroupNoBeforeQuery1T01,//打印机编码加载
				select:this.printerGroupNoSelect1T01//打印机编码选择
			},//打印机类型选择
			'wms_DefFieldValCombo[id=printerTypeCombo1G01]':{				
				select:this.printerTypeComboSelect1G01//打印机类型选择
			}
			
		});
	},
	/**
	 * 点击tab页
	 */
	tabchange:function(tab){
		if(tab.getActiveTab().id=="tabPIdd2_1F01"){
			Ext.getCmp('grid_02_01_1F01').getStore().load();
		}else if(tab.getActiveTab().id=="tabPIdd4_1F01"){
			Ext.getCmp('grid_04_01_1F01').getStore().load();
		}
	},//打印机组编码选择 1T01
	printerGroupNoSelect1T01:function(){
		var record = Ext.getCmp('grid_04_01_1F01').getSelectionModel().getSelection()[0];
		var wheresql={
				wheresql:record.data.grpgatherNo
			};
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('printerGroupNo1T01').getValue())
				&&Ext.getCmp('printerGroupNo1T01').getValue()!="ALL"){
			var d2={
					columnId:'a.printer_group_no',
					value:Ext.getCmp('printerGroupNo1T01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				str:jsonDetail1  
  			};		
		Ext.apply(Ext.getCmp('grid_04_02_1F01').getStore().proxy.extraParams,params);
		Ext.apply(Ext.getCmp('grid_04_02_1F01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_04_02_1F01').getStore().removeAll();
		Ext.getCmp('grid_04_02_1F01').getStore().load();
  	  },//打印机组编码加载  1T01
	printerGroupNoBeforeQuery1T01:function(){
		//Ext.getCmp('printerGroupNo1H01').setValue('');
		var record = Ext.getCmp('grid_04_01_1F01').getSelectionModel().getSelection()[0];
		var wheresql={
				wheresql:record.data.grpgatherNo
			};
		var params={
				str:Ext.getCmp('printerGroupNo1T01').getValue()//客户编码
			};
		Ext.apply(Ext.getCmp('printerGroupNo1T01').getStore().proxy.extraParams,params);
		Ext.apply(Ext.getCmp('printerGroupNo1T01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('printerGroupNo1T01').getStore().removeAll();
		Ext.getCmp('printerGroupNo1T01').getStore().load();
  	  },
	//打印机类型选择 1G01
	printerTypeComboSelect1G01:function(){
		var record = Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection()[0];
		var wheresql={
				wheresql:record.data.printerGroupNo
			};
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('printerNo1G01').getValue())
				&&Ext.getCmp('printerNo1G01').getValue()!="ALL"){
			var d2={
					columnId:'a.printer_no',
					value:Ext.getCmp('printerNo1G01').getValue()
				};
			strDetail11.push(d2);
		}if(!Ext.isEmpty(Ext.getCmp('printerTypeCombo1G01').getValue())){
			var d2={
					columnId:'a.paper_type_no',
					value:Ext.getCmp('printerTypeCombo1G01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				str:jsonDetail1  
  			};		
		Ext.apply(Ext.getCmp('grid_02_02_1F01').getStore().proxy.extraParams,params);
		Ext.apply(Ext.getCmp('grid_02_02_1F01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_02_02_1F01').getStore().removeAll();
		Ext.getCmp('grid_02_02_1F01').getStore().load();
  	  },
	//打印机编码选择 1G01
	printerNoSelect1G01:function(){
		var record = Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection()[0];
		var wheresql={
				wheresql:record.data.printerGroupNo
			};
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('printerNo1G01').getValue())
				&&Ext.getCmp('printerNo1G01').getValue()!="ALL"){
			var d2={
					columnId:'a.printer_no',
					value:Ext.getCmp('printerNo1G01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				str:jsonDetail1  
  			};		
		Ext.apply(Ext.getCmp('grid_02_02_1F01').getStore().proxy.extraParams,params);
		Ext.apply(Ext.getCmp('grid_02_02_1F01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_02_02_1F01').getStore().removeAll();
		Ext.getCmp('grid_02_02_1F01').getStore().load();
  	  },
	//打印机编码加载  1G01
  	printerNoBeforeQuery1G01:function(){
		//Ext.getCmp('printerGroupNo1H01').setValue('');
  		var obj=Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection()[0];
		var params={
				str:Ext.getCmp('printerNo1G01').getValue(),//客户编码
				wheresql:obj.data.printerGroupNo
			};
		Ext.apply(Ext.getCmp('printerNo1G01').getStore().proxy.extraParams,params);
		Ext.getCmp('printerNo1G01').getStore().removeAll();
		Ext.getCmp('printerNo1G01').getStore().load();
  	  },
	//打印机状态选择 
	statusCombo1F01Select1F01:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('printerNo1F01').getValue())
				&&Ext.getCmp('printerNo1F01').getValue()!="ALL"){
			var d2={
					columnId:'a.printer_no',
					value:Ext.getCmp('printerNo1F01').getValue()
				};
			strDetail11.push(d2);
		}if(!Ext.isEmpty(Ext.getCmp('printerTypeCombo1F01').getValue())){
			var d2={
					columnId:'a.paper_type_no',
					value:Ext.getCmp('printerTypeCombo1F01').getValue()
				};
			strDetail11.push(d2);
		}if(!Ext.isEmpty(Ext.getCmp('statusCombo1F01').getValue())){
			var d2={
					columnId:'a.status',
					value:Ext.getCmp('statusCombo1F01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				strQuery:jsonDetail1  
  			};		
		Ext.apply(Ext.getCmp('grid_01_1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1F01').getStore().removeAll();
		Ext.getCmp('grid_01_1F01').getStore().load();
  	  },
	//打印机类型选择 
	printerTypeComboSelect1F01:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('printerNo1F01').getValue())
				&&Ext.getCmp('printerNo1F01').getValue()!="ALL"){
			var d2={
					columnId:'a.printer_no',
					value:Ext.getCmp('printerNo1F01').getValue()
				};
			strDetail11.push(d2);
		}if(!Ext.isEmpty(Ext.getCmp('printerTypeCombo1F01').getValue())){
			var d2={
					columnId:'a.paper_type_no',
					value:Ext.getCmp('printerTypeCombo1F01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				strQuery:jsonDetail1  
  			};		
		Ext.apply(Ext.getCmp('grid_01_1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1F01').getStore().removeAll();
		Ext.getCmp('grid_01_1F01').getStore().load();
		Ext.apply(Ext.getCmp('statusCombo1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('statusCombo1F01').getStore().removeAll();
		Ext.getCmp('statusCombo1F01').getStore().load();
  	  },
	//打印机编码选择 
	printerNoSelect1F01:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('printerNo1F01').getValue())
				&&Ext.getCmp('printerNo1F01').getValue()!="ALL"){
			var d2={
					columnId:'a.printer_no',
					value:Ext.getCmp('printerNo1F01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				strQuery:jsonDetail1  
  			};		
		Ext.apply(Ext.getCmp('grid_01_1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_01_1F01').getStore().removeAll();
		Ext.getCmp('grid_01_1F01').getStore().load();
		Ext.apply(Ext.getCmp('printerTypeCombo1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('printerTypeCombo1F01').getStore().removeAll();
		Ext.getCmp('printerTypeCombo1F01').getStore().load();
		Ext.apply(Ext.getCmp('statusCombo1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('statusCombo1F01').getStore().removeAll();
		Ext.getCmp('statusCombo1F01').getStore().load();
  	  },
	//打印机编码加载 
  	printerNoBeforeQuery1F01:function(){
		//Ext.getCmp('printerGroupNo1H01').setValue(''); 		
		var params={
				str:Ext.getCmp('printerNo1F01').getValue(),//打印机编码				
			};
		Ext.apply(Ext.getCmp('printerNo1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('printerNo1F01').getStore().removeAll();
		Ext.getCmp('printerNo1F01').getStore().load();
  	  },
	//工作站编码选择 
	workStationNoSelect1H01:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('workStation1H01').getValue())){
			var d2={
					columnId:'a.workstation_no',
					value:Ext.getCmp('workStation1H01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
		var params={
  				strQuery:jsonDetail1  //工作站编号
  			};		
		Ext.apply(Ext.getCmp('grid_03_1F01').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_03_1F01').getStore().removeAll();
		Ext.getCmp('grid_03_1F01').getStore().load();
  	  },
	//工作站编码加载 
	workStationNoBeforeQuery:function(){
		Ext.getCmp('printerGroupNo1H01').setValue('');
		var params={
				str:Ext.getCmp('workStation1H01').getValue()//客户编码
			};
		Ext.apply(Ext.getCmp('workStation1H01').getStore().proxy.extraParams,params);
		Ext.getCmp('workStation1H01').getStore().removeAll();
		Ext.getCmp('workStation1H01').getStore().load();
  	  },
  	//打印机群组编码选择 
  	printerGroupNoSelect1H01:function(){
  		var strDetail11 = [];
  		if(!Ext.isEmpty(Ext.getCmp('workStation1H01').getValue())){
  			var d2={
  					columnId:'a.workstation_no',
  					value:Ext.getCmp('workStation1H01').getValue()
  				};
  			strDetail11.push(d2);
  		}
  		if(!Ext.isEmpty(Ext.getCmp('printerGroupNo1H01').getValue())){
  			var d2={
  					columnId:'a.printer_group_no',
  					value:Ext.getCmp('printerGroupNo1H01').getValue()
  				};
  			strDetail11.push(d2);
  		}
  		var jsonDetail1 = Ext.encode(strDetail11);
  		var params={
    				strQuery:jsonDetail1  //工作站编号
    			};		
  		Ext.apply(Ext.getCmp('grid_03_1F01').getStore().proxy.extraParams,params);
  		Ext.getCmp('grid_03_1F01').getStore().removeAll();
  		Ext.getCmp('grid_03_1F01').getStore().load();
    	  },
  	//打印机组编码加载 
  	printerGroupNoBeforeQuery:function(){
  		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('workStation1H01').getValue())){
			var d2={
					columnId:'a.workstation_no',
					value:Ext.getCmp('workStation1H01').getValue()
				};
			strDetail11.push(d2);
		}
		var jsonDetail1 = Ext.encode(strDetail11);
  		var params={
  				str:Ext.getCmp('printerGroupNo1H01').getValue(),//打印机群组编码
  				strQuery:jsonDetail1  //工作站编号
  			};
  		Ext.apply(Ext.getCmp('printerGroupNo1H01').getStore().proxy.extraParams,params);
  		Ext.getCmp('printerGroupNo1H01').getStore().removeAll();
  		Ext.getCmp('printerGroupNo1H01').getStore().load();
    	  },
	/**
	 * 新增
	 */
	detailAdd:function(){
		if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd1_1F01")
		{
			Ext.create('cms.view.bdef.window.bdef_DefPrinterAddorEditWindow',{
				title:$i18n.titleadd//新增
			}).show();
			Ext.getCmp('printer_name1F01').focus();
			//bindEnterSkip($('#form_01_1F01'));//调用键盘处理方法
			addCommMenu5('menuWidget51F01');
			addPrinter1F01();
			saveType1F01='add';
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd2_1F01"){
			Ext.create('cms.view.bset.window.bset_GroupAddorEditWindow',{
				title:$i18n.titleadd//新增
			}).show();
			addGroup1F01();
			Ext.getCmp('printer_group_no1G01').focus(false, 10);
			bindEnterSkip($('#form_01_1G01'));//调用键盘处理方法
			addCommMenu5('menuWidget51G01');
			saveType1F01='add';
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd3_1F01"){
			if(typeof(Ext.getCmp('bdef_DefWorkstationAddorEditWindow'))=='undefined'){
				Ext.create('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
					title:$i18n.titleadd
				}).show();
				addWorkstation1F01();
				Ext.getCmp('workstation_no1H01').focus(false, 10);
				//bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
				commonSetMsterReadOnlyByArray(
					new Array('workstation_no1H01'),false);
				}
			addCommMenu5('menuWidget1H01');
			saveType1F01='add';
		}else{
			Ext.create('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow',{
				title:$i18n.titleadd//新增
			}).show();
			addPrinterGrpgather1F01();
			Ext.getCmp('grpgather_no1T01').focus(false,10);
			bindEnterSkip($('#form_01_1T01'));//调用键盘处理方法
			addCommMenu5('menuWidget51T01');
			saveType1T01='add';
		}
	},
	grid_04_01_1F01change:function(th,selected,eOpts){
		Ext.getCmp('printerGroupNo1T01').setValue('');
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.grpgatherNo
			};
			Ext.apply(Ext.getCmp('grid_04_02_1F01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_04_02_1F01').getStore().removeAll();
			Ext.getCmp('grid_04_02_1F01').getStore().load();
			Ext.apply(Ext.getCmp('grid_04_03_1F01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_04_03_1F01').getStore().removeAll();
			Ext.getCmp('grid_04_03_1F01').getStore().load();
		}else{
			Ext.getCmp('grid_04_03_1F01').getStore().removeAll();
		}
	},
	grid_02_01_1F01change:function(th,selected,eOpts){
		Ext.getCmp('printerNo1G01').setValue('');
		Ext.getCmp('printerTypeCombo1G01').setValue('');
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.printerGroupNo
			};
			Ext.apply(Ext.getCmp('grid_02_02_1F01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_02_1F01').getStore().removeAll();
			Ext.getCmp('grid_02_02_1F01').getStore().load();
			
			Ext.apply(Ext.getCmp('grid_02_03_1F01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_03_1F01').getStore().removeAll();
			Ext.getCmp('grid_02_03_1F01').getStore().load();		
		}else{
			Ext.getCmp('grid_02_02_1F01').getStore().removeAll();
		}
	},
	/**
	 * 修改
	 */
	detailEdit:function(){	
		if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd1_1F01")
		{
			var data=Ext.getCmp('grid_01_1F01').getSelectionModel().getSelection();
			if(data.length==0){
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}else{
				Ext.create('cms.view.bdef.window.bdef_DefPrinterAddorEditWindow',{
					title:$i18n.titleupdate//修改
				}).show();
				rowindex1F01=data[0].index;
				loadPrinter1F01(rowindex1F01);
				Ext.getCmp('printer_name1F01').focus(false, 10);
				commonSetCommMenu5PrevOrNext('menuWidget51F01','grid_01_1F01',rowindex1F01);
				Ext.getCmp('printer_no1F01').disable();
				updateCommMenu5('menuWidget51F01');
				saveType1F01='edit';
			}
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd2_1F01")
		{
			var data=Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection();
			if(data.length==0){
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}else{
				Ext.create('cms.view.bset.window.bset_GroupAddorEditWindow',{
					title:$i18n.titleupdate//修改
				}).show();
				rowindex1F01=data[0].index;
				loadGroup1F01(rowindex1F01);
				Ext.getCmp('printer_group_name1G01').focus(false, 10);
				commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_02_01_1F01',rowindex1F01);
				Ext.getCmp('printer_group_no1G01').disable();
				updateCommMenu5('menuWidget51G01');
				saveType1F01='edit';
			}
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd3_1F01")
		{
			var data=Ext.getCmp('grid_03_1F01').getSelectionModel().getSelection();
			if(data.length!=0){
				if(typeof(Ext.getCmp('bdef_DefWorkstationAddorEditWindow'))=='undefined'){
					Ext.create('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
						title:$i18n.titleupdate
					}).show();
					rowindex1F01=data[0].index;
					loadWorkstationData1F01(rowindex1F01);
					commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_03_1F01',rowindex1F01);
					Ext.getCmp('workstation_no1H01').disable();
					updateCommMenu5('menuWidget1H01');
					saveType1F01='edit';
				}
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd4_1F01"){
			var data=Ext.getCmp('grid_04_01_1F01').getSelectionModel().getSelection();
			if(data.length==0){
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}else{
				Ext.create('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow',{
					title:$i18n.titleupdate//修改
				}).show();
				rowindex1F01=data[0].index;
				loadPrinterGrpgather1F01(rowindex1F01);
				commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_04_01_1F01',rowindex1F01);
				Ext.getCmp('grpgather_no1T01').disable();
				updateCommMenu5('menuWidget51T01');
				saveType1F01='edit';
			}
		}
	},
	
	/**
	 * 浏览
	 */
	detailBrowse:function(){	
		if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd1_1F01")
		{
			var data = Ext.getCmp('grid_01_1F01').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.bdef.window.bdef_DefPrinterAddorEditWindow',{
					title:$i18n.titlebrowse
				}).show();
				browseCommMenu5('menuWidget51F01'); 
				rowindex1F01=data[0].index;
				loadPrinter1F01(rowindex1F01);
				commonSetCommMenu5PrevOrNext('menuWidget51F01','grid_01_1F01',rowindex1F01);
				commonSetFieldReadOnly('bdef_DefPrinterAddorEditWindow',true);
				saveType1F01='browse';
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd2_1F01")
		{
			var data=Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection();
			if(data.length==0){
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
			}else{
				Ext.create('cms.view.bset.window.bset_GroupAddorEditWindow',{
					title:$i18n.titlebrowse
				}).show();
				rowindex1F01=data[0].index;
				loadGroup1F01(rowindex1F01);
				commonSetFieldReadOnly('bset_GroupAddorEditWindow',true);
				commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_02_01_1F01',rowindex1F01);
				browseCommMenu5('menuWidget51G01');
			}
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd3_1F01"){
			if(!Ext.isEmpty(Ext.getCmp('grid_03_1F01'))){
				var data = Ext.getCmp('grid_03_1F01').getSelectionModel().getSelection();
				if (data.length != 0) {
					Ext.create('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
						title:$i18n.titlebrowse
					}).show(); 
					rowindex1F01=data[0].index;
					loadWorkstationData1F01(rowindex1F01);
					commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_03_1F01',rowindex1F01);
					commonSetFormReadOnly('form_01_1H01',true);
					browseCommMenu5('menuWidget1H01');
		        }else{
		        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
		        }
			}
			//browseCommMenu5('menuWidget1H01');
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd4_1F01"){
			var data=Ext.getCmp('grid_04_01_1F01').getSelectionModel().getSelection();
			if(data.length==0){
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
			}else{
				Ext.create('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow',{
					title:$i18n.titlebrowse
				}).show();
				rowindex1F01=data[0].index;
				loadPrinterGrpgather1F01(rowindex1F01);
				commonSetFieldReadOnly('pntdef_PrinterGrpgatherAddorEditWindow',true);
				commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_04_01_1F01',rowindex1F01);
				browseCommMenu5('menuWidget51T01');
				saveType1F01='browse';

			}
		}
	},
	detailQuery:function(){
		if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd1_1F01")
		{
			Ext.create('cms.view.common.queryWindow',{
			}).show();
			Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
			queryModuleId='1F01';
			queryGrid='grid_01_1F01';
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd2_1F01")
		{
			Ext.create('cms.view.common.queryWindow',{
			}).show();
			Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
			queryModuleId='1G01';
			queryGrid='grid_02_01_1F01';
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd3_1F01"){
			Ext.create('cms.view.common.queryWindow',{
			}).show();
			Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
			queryModuleId='1H01';
			queryGrid='grid_03_1F01';
		}else if(Ext.getCmp('tabPId1F01').getActiveTab().id=="tabPIdd4_1F01"){
			Ext.create('cms.view.common.queryWindow',{
			}).show();
			Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
			queryModuleId='1T01';
			queryGrid='grid_04_01_1F01';
		}
	},
	
	detailExport:function(){
		commExport('grid_01_1F01');
	},
	//上一条记录(打印机)
	prev:function(){
		rowindex1F01=rowindex1F01-1;
		loadPrinter1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget51F01','grid_01_1F01',rowindex1F01);
		
	},
	//上一条记录(打印机组)
	prevBset_Group:function(){
		rowindex1F01=rowindex1F01-1;
		loadGroup1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_02_01_1F01',rowindex1F01);
	},
	//上一条记录(工作站)
	prevBdef_DefWorkstation:function(th){
		rowindex1F01=rowindex1F01-1;
		loadWorkstationData1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_03_1F01',rowindex1F01);
	},
	//上一条记录(打印机群组)
	prevPntdef_PrinterGrpgather:function(){
		rowindex1F01=rowindex1F01-1;
		loadPrinterGrpgather1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_04_01_1F01',rowindex1F01);
	},
	//下一条记录(打印机)
	next:function(){
		rowindex1F01=rowindex1F01+1;
		loadPrinter1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget51F01','grid_01_1F01',rowindex1F01);
	},
	//下一条记录(打印机组)
	nextBset_Group:function(){
		rowindex1F01=rowindex1F01+1;
		loadGroup1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget51G01','grid_02_01_1F01',rowindex1F01);
	},
	//下一条记录(工作站)
	nextBdef_DefWorkstation:function(th){
		rowindex1F01=rowindex1F01+1;
		loadWorkstationData1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget1H01','grid_03_1F01',rowindex1F01);
	},
	//下一条记录(打印机群组)
	nextPntdef_PrinterGrpgather:function(){
		rowindex1F01=rowindex1F01+1;
		loadPrinterGrpgather1F01(rowindex1F01);
		commonSetCommMenu5PrevOrNext('menuWidget51T01','grid_04_01_1F01',rowindex1F01);
	},
	
	//新增前加载(工作站)
	add:function(){
		addPrinter1F01();
	},	
	//新增前加载(打印机组)
	addBset_Group:function(){
		addGroup1F01();
		saveType1F01='add';
	},
	//新增前加载(工作站)
	addBdef_DefWorkstation:function(){
		commonSetMsterReadOnlyByArray(new Array('workstation_no1H01'),false);
		bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
		addWorkstation1F01();
		saveType1F01='add';
	},
	//新增前加载(打印机群组)
	addPntdef_PrinterGrpgather:function(){
		Ext.getCmp('grpgather_no1T01').focus();
		bindEnterSkip($('#form_01_1T01'));//调用键盘处理方法
		addPrinterGrpgather1F01();
		saveType1F01='add';
	},
	//保存打印机
	save:function(){
		savePrinter1F01();
	},
	//保存打印机组
	saveBset_Group:function(){
		saveGroup1F01();
	},
	//保存工作站
	saveBdef_DefWorkstation:function(){
		saveWorkstation1F01();
	},
	//保存打印机群组
	savePntdef_PrinterGrpgather:function(){
		savePrinterGrpgather1F01();
	},
	right02_1F01:function(){
		var grid01=Ext.getCmp('grid_04_01_1F01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_04_02_1F01').getSelectionModel().getSelection();		

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
						Ext.getCmp('grid_04_02_1F01').getStore().load();
						Ext.getCmp('grid_04_03_1F01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	
	},
	
	left02_1F01:function(){
		var grid01=Ext.getCmp('grid_04_01_1F01').getSelectionModel().getSelection();
		var grid03=Ext.getCmp('grid_04_03_1F01').getSelectionModel().getSelection();
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
						Ext.getCmp('grid_04_02_1F01').getStore().load();
						Ext.getCmp('grid_04_03_1F01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	},
	right01_1F01:function(){
		var grid01=Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection();
		var grid02=Ext.getCmp('grid_02_02_1F01').getSelectionModel().getSelection();
		var printerType = "";
		var flag="";
		for(var i=0;i<grid02.length;i++){
			if(printerType.indexOf(grid02[i].get('printertypeText')) != -1 && printerType != ""){
				//alert(grid02[i].get('printerType'));
				Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt1);
				return;
			}
			printerType += grid02[i].get('printertypeText');
		}
		//**************************************************************		
		for(var i=0;i<grid02.length;i++){
			Ext.Ajax.request({
				url:'bset_GroupAction_checkPrinterType',
				params:{
					warehouseNo:Ext.get('warehouseNo').getValue(),
					printerGroupNo:grid01[0].get('printerGroupNo'),
					printerType:grid02[i].get('paperTypeNo')
					
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
						if(Ext.getCmp('grid_02_03_1F01').getStore().findExact('printerType',grid02[i].get('printerType'))=='-1'){
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
							Ext.getCmp('grid_02_02_1F01').getStore().load();
							Ext.getCmp('grid_02_03_1F01').getStore().load();
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
	
	left01_1F01:function(){
		var grid01=Ext.getCmp('grid_02_01_1F01').getSelectionModel().getSelection();
		var grid03=Ext.getCmp('grid_02_03_1F01').getSelectionModel().getSelection();
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
						Ext.getCmp('grid_02_02_1F01').getStore().load();
						Ext.getCmp('grid_02_03_1F01').getStore().load();
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
			}
		}else{
			Ext.example.msg($i18n.prompt,$i18n_prompt.bset_group_prompt2);
		}
	},
	//关闭窗口
	close:function(){
		Ext.getCmp('bdef_DefPrinterAddorEditWindow').close();
	},
	//关闭窗口(打印机组与打印机关系维护)
	closeBset_Group:function(){
		Ext.getCmp('bset_GroupAddorEditWindow').close();
	},
	//关闭窗口(工作站与打印机组关系维护)
	closeBdef_DefWorkstation:function(){
		Ext.getCmp('bdef_DefWorkstationAddorEditWindow').close();
	},
	//关闭窗口(打印机群组与打印机组关系维护)
	closePntdef_PrinterGrpgather:function(){
		Ext.getCmp('pntdef_PrinterGrpgatherAddorEditWindow').close();
	},
	printerNoBlur:function(){
		if(saveType1F01=='add'){
			Ext.Ajax.request({
			method:'post',
			url:'bdef_DefPrinterAction_checkPrinterNo',
			params:{
				printerNo:Ext.getCmp('printer_no1F01').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg('提示','该打印机代码已有，请重新录入！');
		    		Ext.getCmp('printer_no1F01').setValue('');
		    		Ext.getCmp('printer_no1F01').focus();
		    	}
		    }
			});
		}
	},
	printerGroupNoBlur:function(){
		if(saveType1F01=='add'){
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
	},
	grpgatherNoBlur:function(){
		if(saveType1F01=='add'){
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
	},
	workstationNoBlur:function(){
		if(saveType1F01=='add'){
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
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
		   th.nextSibling().focus();
        }
	}
});

//新增前加载
function addPrinter1F01(){
	Ext.getCmp('form_01_1F01').getForm().reset();
	Ext.getCmp('warehouse1F01').setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('printer_type1F01').setValue('B');
	Ext.getCmp('status1F01').setValue('1');
	//bindEnterSkip($('#form_01_1F01'));//调用键盘处理方法
	Ext.getCmp('printer_no1F01').focus(false, 10);
	saveType1F01='add';
};
function addGroup1F01(){
	Ext.getCmp('form_01_1G01').getForm().reset();
	Ext.getCmp('warehouse1G01').setValue(Ext.get('warehouseNo').getValue());
};
function addWorkstation1F01(){
	Ext.getCmp('form_01_1H01').getForm().reset();
	Ext.getCmp('warehouse_no1H01').setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('workstation_no1H01').focus(false, 10);
	//bindEnterSkip($('#form_01_1H01'));//调用键盘处理方法
};
function addPrinterGrpgather1F01(){
	Ext.getCmp('form_01_1T01').getForm().reset();
	Ext.getCmp('warehouse1T01').setValue(Ext.get('warehouseNo').getValue());
	saveType1F01='add';
};

//填充数据
function loadPrinter1F01(rowindex1F01){
	var printer=Ext.getCmp('grid_01_1F01').getStore().getAt(rowindex1F01-(Ext.getCmp('grid_01_1F01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('warehouse1F01').setValue(printer.data.warehouseNo);
	Ext.getCmp('printer_no1F01').setValue(printer.data.printerNo);
	Ext.getCmp('printer_name1F01').setValue(printer.data.printerName);
	Ext.getCmp('printer_type1F01').setValue(String(printer.data.paperTypeNo));
	
	var status='1';
	if(printer.data.status){
		status='1';
	}else{
		status='0';
	}
	Ext.getCmp('status1F01').setValue(status);
};
function loadGroup1F01(rowindex1F01){
	var group=Ext.getCmp('grid_02_01_1F01').getStore().getAt(rowindex1F01-(Ext.getCmp('grid_02_01_1F01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('warehouse1G01').setValue(group.data.warehouseNo);
	Ext.getCmp('printer_group_no1G01').setValue(group.data.printerGroupNo);
	Ext.getCmp('printer_group_name1G01').setValue(group.data.printerGroupName);
};
function loadWorkstationData1F01(rowindex1F01){
	var record=Ext.getCmp('grid_03_1F01').getStore().getAt(rowindex1F01-(Ext.getCmp('grid_03_1F01').getStore().currentPage-1)*appConfig.getPageSize());	
	Ext.getCmp('warehouse_no1H01').setValue(record.data.warehouseNo);
	Ext.getCmp('workstation_no1H01').setValue(record.data.workstationNo);
	Ext.getCmp('workstation_name1H01').setValue(record.data.workstationName);	
	Ext.getCmp('printer_group1H01').setValue(record.data.printerGroupNo);
};
function loadPrinterGrpgather1F01(rowindex1F01){
	var group=Ext.getCmp('grid_04_01_1F01').getStore().getAt(rowindex1F01-(Ext.getCmp('grid_04_01_1F01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('warehouse1T01').setValue(group.data.warehouseNo);
	Ext.getCmp('grpgather_no1T01').setValue(group.data.grpgatherNo);
	Ext.getCmp('grpgather_name1T01').setValue(group.data.grpgatherName);
};
//保存打印机
function savePrinter1F01(){
	if(!commonCheckIsInputAll('form_01_1F01')){
		return;
	}
	if(Ext.getCmp('form_01_1F01').getForm().isValid()){
		
		var stat=false;
		if(Ext.getCmp('status1F01').getValue()=='1'){
			stat=true;
		}
		var printer={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse1F01').getValue(),
				printerNo:Ext.getCmp('printer_no1F01').getValue()
			},
			printerName:Ext.getCmp('printer_name1F01').getValue(),
			paperTypeNo:Ext.getCmp('printer_type1F01').getValue(),
			status:stat,
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(printer);
		Ext.Ajax.request({
			url:'bdef_DefPrinterAction_savePrinter',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1F01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
					commonSetMsterReadOnlyByArray(
							new Array('printer_no1F01'),true);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};
//保存打印机组
function saveGroup1F01(){
	if(!commonCheckIsInputAll('form_01_1G01')){
		return;
	}
	 
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
					Ext.getCmp('grid_02_01_1F01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
					commonSetMsterReadOnlyByArray(
							new Array('printer_group_no1G01'),true);
				}
			}
		});
	};
};
//保存工作站
function saveWorkstation1F01(th){
	var but=th;
	if(!commonCheckIsInputAll('form_01_1H01')){
		return;
	}
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
					Ext.getCmp('grid_03_1F01').getStore().load();
					commonSetMsterReadOnlyByArray(
					new Array('workstation_no1H01'),true);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};
//保存打印机群组
function savePrinterGrpgather1F01(){
	if(!commonCheckIsInputAll('form_01_1T01')){
		return;
	}
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
					Ext.getCmp('grid_04_01_1F01').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	};
};

