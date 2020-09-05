/**
 * 模块名称：保拣关系设置
 * 模块编码：2301
 * zhouhuan
 */
 var g_strRowindex2301=0;
 var g_varbutton2301 = 'add';//add:增加保拣线；
 var flagText='';
 var flagdefault='0';
 Ext.define('cms.controller.cset.cset_AreaBackupController',{
 	extend:'Ext.app.Controller',
 	requires:['cms.view.cset.cset_AreaBackupUI',
 	'cms.view.cset.window.cset_AreaBackupAddOrEditWindow',
 	'cms.view.cset.window.cset_AreaBackupAddOrEditWindow2'
 	],
 	model:'cms.model.cset.cset_Area_BackupModel',
 	store:'cms.store.cset.cset_Area_BackupStore',
 	init:function(){
 		 this.control(
 		    //新增保拣线
 		    {
	    	'cset_AreaBackupUI commMenuWidget2[id=menu2301] button[name=detailAdd]':{
				click:this.detailAdd
			},
			//查找
			'cset_AreaBackupUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			'cset_AreaBackupUI button[name=detailExport]':{
				click:this.detailExport
			},//导出
			//新增保拣级别
	    	'cset_AreaBackupUI commMenuWidget6[id=menu_2301_2] button[name=detailAdd]':{
	    		click:this.detailAdd2301_2
	    	},
	    	//修改保拣线
	    	'cset_AreaBackupUI commMenuWidget2[id=menu2301] button[name=detailEdit]':{
	    		click:this.detailEdit
	    	},
	    	//修改
	    	'cset_AreaBackupUI commMenuWidget6[id=menu_2301_2] button[name=detailEdit]':{
	    		click:this.detailEdit2301_2
	    	},
	    	//浏览保拣线
			'cset_AreaBackupUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//删除保拣线
			'cset_AreaBackupUI commMenuWidget2[id=menu2301] button[name=detailDelete]':{
				click:this.detailDelete
			},
			//删除保拣级别
			'cset_AreaBackupUI commMenuWidget6[id=menu_2301_2] button[name=detailDelete]':{
				click:this.detailDelete2301_2
			},
			//保拣线窗口-->上一条
			'cset_AreaBackupAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			//保拣线窗口-->新增d
			'cset_AreaBackupAddOrEditWindow button[name=add]':{
				click:this.add2301_1
			},
			//保拣级别窗口-->新增
			'cset_AreaBackupAddOrEditWindow2 button[name=add]':{
				click:this.add2301_2
			},
			//保拣级别窗口-->上一条
			'cset_AreaBackupAddOrEditWindow2 button[name=prev]':{
				click:this.prev2301_2
			},
			//保拣线窗口-->下一条
			'cset_AreaBackupAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//保拣级别窗口-->下一条
			'cset_AreaBackupAddOrEditWindow2 button[name=next]':{
				click:this.next2301_2
			},
			//保拣线窗口-->保存
			'cset_AreaBackupAddOrEditWindow button[name=save]':{
				click:this.save
			},
			//保拣级别窗口-->保存
			'cset_AreaBackupAddOrEditWindow2 button[name=save]':{
				click:this.save2301_2
			},
			//保拣线窗口-->关闭
			'cset_AreaBackupAddOrEditWindow button[name=close]':{
				click:this.close
			},
			//保拣级别-->关闭
			'cset_AreaBackupAddOrEditWindow2 button[name=close]':{
				click:this.close2301_2
			},
			//选择一条保拣线加载对应的保拣级别
			'cset_AreaBackupUI grid[id=cset_AreaBackupMGrid2301]':{
				selectionchange:this.editCset_Area_Backup
			},
			//保拣线窗口-->选择库区代码
			'cset_AreaBackupAddOrEditWindow cdef_DefWareCombo[id=cmbWareNo2301A]':{
				change:this.ware_noChangeA
			},
			//保拣线窗口-->选择储区代码
			'cset_AreaBackupAddOrEditWindow cdef_DefAreaCombo[id=cmbAreaNo2301A]':{
				change:this.area_noChangeA
			},
			//保拣线窗口-->选择默认值
			'cset_AreaBackupAddOrEditWindow bdef_DefDockCombo[id=cmbDefaultFlag2301]':{
				change:this.DefaultFlagChange
			},//保拣级别窗口-->选择库区代码
			'cset_AreaBackupAddOrEditWindow2 cdef_DefWareCombo[id=cmbWareNo2301]':{
				change:this.ware_no2301_2change
			},
			//保拣级别窗口-->选择储区代码
			'cset_AreaBackupAddOrEditWindow2 cdef_DefAreaCombo[id=cmbAreaNo2301]':{
				change:this.area_no2301_2change
			},
			//新增保拣线检验
			'cset_AreaBackupAddOrEditWindow textfield[id=textLineId2301]':{
				blur:this.textLineId2301Blur
			},
			/*//新增保拣级别检验
			'cset_AreaBackupAddOrEditWindow2 textfield[id=txtA_level2301]':{
				blur:this.areaBackupGrade2301Blur
			},*/
			/*//新增保拣级别检验
			'cset_AreaBackupAddOrEditWindow2 textfield[id=cmbWareNo2301]':{
				blur:this.areaBackupGrade2301Blur
			},*/
			//新增保拣级别检验
			/*'cset_AreaBackupAddOrEditWindow2 textfield[id=cmbStockNo2301]':{
				blur:this.areaBackupGrade2301Blur
			}*/
	    });
 	},
 	
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('cset_AreaBackupMGrid2301').getStore().load();
	},
 	
 	/**
	 * 导出
	 */
	detailExport:function(){
		commExport('cset_AreaBackupDGrid2301');
	},
 	
 	/**
	 * 查找
	 */
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=2301;
		queryGrid='cset_AreaBackupMGrid2301';					
	},
 	
	//保拣线窗口-->新增d
 	add2301_1:function(){
 		Ext.getCmp('cset_AreaBackupAddOrEditLine2301').getForm().reset();
		Ext.getCmp('textLineId2301').focus(false, 10);
		bindEnterSkip($('#cset_AreaBackupAddOrEditLine2301'));//调用键盘处理方法
		commonSetMsterReadOnlyByArray(
						new Array('textLineId2301'),false);
		g_varbutton2301='add';
 	},
 	
 	//保拣级别窗口-->新增
 	add2301_2:function(){
		g_varbutton2301='add2';
		addCset_Area_Backup2301_2();
		commonSetMsterReadOnlyByArray(
				new Array('txtA_level2301','cmbWareNo2301',
				'cmbAreaNo2301','cmbStockNo2301'),false);
		var objData = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
		Ext.getCmp('cmbWareNo2301').getStore().load();
		Ext.getCmp('txtLine_id2301').setValue(objData[0].data.lineId);
		Ext.getCmp('txtLine_id2301').setReadOnly(true);
		Ext.getCmp('txtA_level2301').focus(false, 10);
		bindEnterSkip($('#cset_AreaBackupEditLevel2301'));//调用键盘处理方法
 	},
 	
 	//保存保拣线
 	save:function(th){
 		g_varbutton2301='save';
		saveCset_Area_Backup(th);
	},
	
	//保存级别
	save2301_2:function(th){
		saveCset_Area_Backup2301_2(th);
	},
	
	//库区选择事件(保拣线级别)
	ware_noChangeA:function(th,newValue,oldValue,eOpts)
	{
		var listDetail1 = [];
		var strDtl={
				columnId:'a.enterprise_no',
				value:Ext.get('enterpriseNo').getValue()
			};
			listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.ware_No',
			value:newValue
		};
		listDetail1.push(strDtl);
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('cmbAreaNo2301A').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cmbAreaNo2301A').getStore().load();
	},
	//选择默认值事件
	DefaultFlagChange:function(th,newValue,oldValue,eOpts){
		debugger;
		if(g_varbutton2301=='add'){
			if(newValue!='1'){
				return;
			}
			
			Ext.Ajax.request({
				url:'cset_AreaBackupAction_checkAreaNo.action',
				params :{
					str: Ext.getCmp('cmbAreaNo2301A').getValue()
				},
				success : function(response) {
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('cmbDefaultFlag2301').setValue("");
						Ext.example.msg($i18n.prompt,data.msg);
					}  							
				}
			});
			
		}else{
			debugger;
			if(flagdefault=='0'){
				flagdefault='1';
				return;
			}
			if(newValue!='1'){
				return;
			}
			
			Ext.Ajax.request({
				url:'cset_AreaBackupAction_checkAreaNo.action',
				params :{
					str: Ext.getCmp('cmbAreaNo2301A').getValue()
				},
				success : function(response) {
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('cmbDefaultFlag2301').setValue("");
						Ext.example.msg($i18n.prompt,data.msg);
					}  							
				}
			});
		}
	},	
	//储区选择事件(保拣线级别)
	area_noChangeA:function(th,newValue,oldValue,eOpts){
		var detail1 = [];
		var d={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('cmbWareNo2301A').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.area_no',
			value:newValue
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('cmbStockNo2301A')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('cmbStockNo2301A').getStore().load();
			
	},
	
	//库区选择事件(保拣线子级别)
	ware_no2301_2change:function(th,newValue,oldValue,eOpts)
	{
		var listDetail1 = [];
		var strDtl={
				columnId:'a.enterprise_no',
				value:Ext.get('enterpriseNo').getValue()
			};
			listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		listDetail1.push(strDtl);
		var strDtl={
			columnId:'a.ware_No',
			value:newValue
		};
		listDetail1.push(strDtl);
		var jsonDetail1 = Ext.encode(listDetail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('cmbAreaNo2301')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('cmbAreaNo2301').getStore().load();
	},
	
	//储位、储区下拉选择事件
	area_no2301_2change:function(th,newValue,oldValue,eOpts){
		var detail1 = [];
		var d={
			columnId:'a.warehouse_no',
			value:Ext.get('warehouseNo').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('cmbWareNo2301').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.area_no',
			value:newValue
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('cmbStockNo2301')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('cmbStockNo2301').getStore().load();
	},
	
	//选择一条保拣线加载对应的保拣级别
 	editCset_Area_Backup:function(){
 		//console.log('Double clicked on ');
 		var objData = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
 		if(objData.length!=0){
 			var sql="";
 			var listDetail1 = [];
			var strdtl={
				columnId:'cabd.line_id',
				value:objData[0].get("lineId")
			};
			listDetail1.push(strdtl);
			var jsonDetail1 = Ext.encode(listDetail1);
			var wheresql = {
				strQuery : jsonDetail1
			};
		Ext.apply(Ext.getCmp('cset_AreaBackupDGrid2301')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('cset_AreaBackupDGrid2301').getStore()
				.removeAll();
		Ext.getCmp('cset_AreaBackupDGrid2301').getStore()
				.load();
 		}
 		
 	},
    					
 	/*
	 * 新增保拣线
	 * zhouhuan
	 */
 		detailAdd:function()
 		{
			Ext.create('cms.view.cset.window.cset_AreaBackupAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			addCset_Area_Backup();
			//控制菜单显示项
			addCommMenu5('menuWidget52301_1');
			Ext.getCmp('textLineId2301').focus(false, 10);
			bindEnterSkip($('#cset_AreaBackupAddOrEditLine2301'));//调用键盘处理方法
			g_varbutton2301='add';
			flagdefault='0';
	},
	/*
	 * 新增级别
	 * zhouhuan
	 */
	detailAdd2301_2:function()
	{
		flagText='add';
		g_varbutton2301='add2';
		var objData = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
		if(objData.length==0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.choseALineForAdd); 
	            return; 
		}else{
				Ext.create('cms.view.cset.window.cset_AreaBackupAddOrEditWindow2',{
					title:$i18n.titleadd
				}).show();
				
				if(Ext.getCmp('cset_AreaBackupDGrid2301').getStore().data.length!=0){
					var firstData =Ext.getCmp('cset_AreaBackupDGrid2301').getStore().getAt(0).data.stockNo;
					if(firstData=='N'){
						Ext.getCmp('cmbStockNo2301').setValue('N');
						Ext.getCmp('cmbStockNo2301').readOnly=true;
					}else{
						Ext.getCmp('cmbStockNo2301').readOnly=false;
						Ext.getCmp('cmbStockNo2301').allowBlank=false;
					}
				}		
				Ext.getCmp('cmbWareNo2301').getStore().load();
				addCset_Area_Backup2301_2();
				addCommMenu5('menuWidget52301_2');
				Ext.getCmp('txtLine_id2301').setValue(objData[0].data.lineId);
				Ext.getCmp('txtLine_id2301').setReadOnly(true);
				Ext.getCmp('txtA_level2301').focus(false, 10);
				bindEnterSkip($('#cset_AreaBackupEditLevel2301'));//调用键盘处理方法
			}
		},
	/**
	 * 修改保拣线
	 * zhouhuan
	 */
	detailEdit:function()
	{	
		var objdata = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
			if (objdata.length != 0) {
				if(typeof(Ext.getCmp('cset_AreaBackupAddOrEditWindow'))=='undefined'){
					Ext.create('cms.view.cset.window.cset_AreaBackupAddOrEditWindow',{
						title:$i18n.titleupdate
					}).show(); 
					g_varbutton2301='update';
			        flagdefault='0';
					g_strRowindex2301=objdata[0].index;
					loadCset_Area_Backup(g_strRowindex2301);
					commonSetMsterReadOnlyByArray(
							new Array('textLineId2301'),true);
					commonSetMsterReadOnlyByArray(
							new Array('txtLineName2301','cmbDefaultFlag2301'),false);
					commonSetCommMenu5PrevOrNext('menuWidget52301_1','cset_AreaBackupMGrid2301',g_strRowindex2301);
					updateCommMenu5('menuWidget52301_1');
				}
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
	        
	},
	/**
	 * 修改保拣级别
	 * zhouhuan
	 */
	detailEdit2301_2:function(){	
		flagText='edit';
		var data = Ext.getCmp('cset_AreaBackupDGrid2301').getSelectionModel().getSelection();
			if (data.length != 0) {
				if(typeof(Ext.getCmp('cset_AreaBackupAddOrEditWindow2301_2'))=='undefined'){
					Ext.create('cms.view.cset.window.cset_AreaBackupAddOrEditWindow2',{
						title:$i18n.titleupdate
					}).show(); 
					g_strRowindex2301=data[0].index;
					loadCset_Area_Backup2301_2(g_strRowindex2301);
					commonSetMsterReadOnlyByArray(
							new Array('txtA_level2301','txtLine_id2301','cmbWareNo2301',
							'cmbAreaNo2301','cmbStockNo2301'),true);
					commonSetCommMenu5PrevOrNext('menuWidget52301_2','cset_AreaBackupDGrid2301',g_strRowindex2301);
					updateCommMenu5('menuWidget52301_2');
				}
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
	        g_varbutton2301='update';
	},
	/**
	 * 浏览保拣线
	 * zhouhuan
	 */
	detailBrowse:function(){	
		if(Ext.getCmp('cset_AreaBackupMGrid2301'))
		{
			var data = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cset.window.cset_AreaBackupAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				g_strRowindex2301=data[0].index;
				loadCset_Area_Backup(g_strRowindex2301);
				commonSetFormReadOnly('cset_AreaBackupAddOrEditLine2301',true);
				commonSetCommMenu5PrevOrNext('menuWidget52301_1','cset_AreaBackupMGrid2301',g_strRowindex2301);
				browseCommMenu5('menuWidget52301_1');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		};
	},
	
	/**
	 * 删除保拣线
	 * zhouhuan
	 */
	detailDelete:function(){
		var objdata = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();  

        if (objdata.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.Ajax.request({
    						url:'cset_AreaBackupAction_deleteLine.action',
    						params :{
    							enterpriseNo:Ext.get('enterpriseNo').getValue(),
    							warehouseNo:Ext.get('warehouseNo').getValue(),
    							strLineId: objdata[0].get("lineId")
    						},
    						success : function(response) {
    							var data = Ext.decode(response.responseText);
    							if(data.isSucc){
    								Ext.getCmp('cset_AreaBackupMGrid2301').getStore().load();
    							}else{
    								Ext.example.msg($i18n.prompt,data.msg);
    								Ext.getCmp('cset_AreaBackupMGrid2301').getStore().load();
    							}  							
    						}
    					});
    				}			
            });
        }
	},
	
	/**
	 * 删除保拣级别
	 * zhouhuan
	 */
	detailDelete2301_2:function()
	{
		var objData = Ext.getCmp('cset_AreaBackupDGrid2301').getSelectionModel().getSelection();  
		var listDetail = [];
		var strDtl = {
				columnId:'cabd.enterprise_no',
				value:Ext.get('enterpriseNo').getValue()	
		};
		listDetail.push(strDtl);
		var strDtl = {
				columnId:'cabd.line_id',
				value:objData[0].get("lineId")	
		};
		listDetail.push(strDtl);
		var strDtl = {
				columnId:'cabd.warehouse_no',
				value:Ext.get("warehouseNo").getValue()
		};
		listDetail.push(strDtl);
		var strDtl = {
				columnId:'cabd.ware_no',
				value:objData[0].get("wareNo")	
		};
		listDetail.push(strDtl);
		var strDtl = {
				columnId:'cabd.area_no',
				value:objData[0].get("areaNo")	
		};
		listDetail.push(strDtl);
		var strDtl = {
				columnId:'cabd.stock_no',
				value:objData[0].get("stockNo")	
		};
		listDetail.push(strDtl);
		var jsonDetail = Ext.encode(listDetail);
        if (objData.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.getCmp('cset_AreaBackupDGrid2301').getStore().remove(objData);
    					Ext.Ajax.request({
    						method:'POST',
    						url:'cset_AreaBackupAction_deleteGrade.action',
    						params :{ 
    							strQuery : jsonDetail
    						},
    						success : function(response) {
    						Ext.getCmp('cset_AreaBackupDGrid2301').getStore().load();
    					}
    					});
    				}			
            });
        }
	},

	/*
	 * 上一条保拣线记录
	 * @param {} th
	 */
	prev:function(th)
	{
		g_strRowindex2301=g_strRowindex2301-1;
		loadCset_Area_Backup(g_strRowindex2301);
		commonSetCommMenu5PrevOrNext('menuWidget52301_1','cset_AreaBackupMGrid2301',g_strRowindex2301);
	},
	/*
	 * 上一条保拣级别记录
	 * @param {} th
	 */
	prev2301_2:function(th)
	{
		g_strRowindex2301=g_strRowindex2301-1;
		loadCset_Area_Backup2301_2(g_strRowindex2301);
		commonSetCommMenu5PrevOrNext('menuWidget52301_2','cset_AreaBackupDGrid2301',g_strRowindex2301);
	},
	/**
	 * 下一条保拣线记录
	 * @param {} th
	 */
	next:function(th){
		g_strRowindex2301=g_strRowindex2301+1;
		loadCset_Area_Backup(g_strRowindex2301);
		commonSetCommMenu5PrevOrNext('menuWidget52301_1','cset_AreaBackupMGrid2301',g_strRowindex2301);
	},
	/**
	 * 下一条保拣级别记录
	 * @param {} th
	 */
	next2301_2:function(th){
		g_strRowindex2301=g_strRowindex2301+1;
		loadCset_Area_Backup2301_2(g_strRowindex2301);
		commonSetCommMenu5PrevOrNext('menuWidget52301_2','cset_AreaBackupDGrid2301',g_strRowindex2301);
	},

	/**
	 * 关闭保拣线窗口
	 * @param {} th
	 */
	close:function(th){
		closeWindow();
	},
	/**
	 * 关闭保拣级别窗口
	 * @param {} th
	 */
	close2301_2:function(th){
		closeWindow2301_2();
	},
	
	
	/*
	 * 新增保拣级别检验
	 */
	areaBackupGrade2301Blur:function(th){
	var record=Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
	if(g_varbutton2301=='add2'){
		var listdetail1 = [];
			if(!Ext.isEmpty(Ext.getCmp("txtA_level2301").getValue()))
			{
				var strDtl={
						columnId:'cabd.enterprise_no',
						value:Ext.get('enterpriseNo').getValue()
					};
				listdetail1.push(strDtl);
				
				var strDtl={
					columnId:'cabd.warehouse_no',
					value:Ext.get("warehouseNo").getValue()
					};
				listdetail1.push(strDtl);
				
				var strDtl={
					columnId:'cabd.line_id',
					value:Ext.getCmp("txtLine_id2301").getValue()
				};
				listdetail1.push(strDtl);
				
				var strDtl={
					columnId:'cabd.a_level',
					value:Ext.getCmp("txtA_level2301").getValue()
				};
				listdetail1.push(strDtl);
				
				Ext.Ajax.request({
					url:'cset_AreaBackupAction_existsAreaBackupLevel.action',
					params : {
						strQuery:Ext.encode(listdetail1)
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(!data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('txtA_level2301').setValue('');
						}else{
							if(Ext.getCmp("txtA_level2301").getValue()=='0'){
								var objData = Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().getSelection();
								Ext.getCmp('cmbWareNo2301').setValue(objData[0].data.SWareNo);
								Ext.getCmp('cmbAreaNo2301').setValue(objData[0].data.SAreaNo);
								Ext.getCmp('cmbStockNo2301').setValue(objData[0].data.SStockNo);
								
								commonSetMsterReadOnlyByArray(
								new Array('cmbWareNo2301','cmbAreaNo2301','cmbStockNo2301'),true);
							}else{
								commonSetMsterReadOnlyByArray(
								new Array('cmbWareNo2301','cmbAreaNo2301','cmbStockNo2301'),false);
							}
						}
					}
				});
			}
		}	
	},
	
	textLineId2301Blur:function(th){
		areaBackupLine2301Blur(th);
	}
 });
 
 /*
 * 新增保拣线检验
 */
function areaBackupLine2301Blur(th){
	if(g_varbutton2301=='add'){
		if(!Ext.isEmpty(Ext.getCmp('textLineId2301').getValue())){
			if(Ext.getCmp('textLineId2301').getValue()<0){
				Ext.example.msg($i18n.prompt,'保拣线ID不能为负数，请重新输入');
				Ext.getCmp('textLineId2301').setValue('');
				Ext.getCmp('txtLineName2301').setValue('');
				Ext.getCmp('cmbDefaultFlag2301').setValue('');
				Ext.getCmp('textLineId2301').focus(false,1);
			}
		}
		var listDetail1 = [];
		   var strDtl={
				columnId:'cabm.enterprise_no',
				value:Ext.get('enterpriseNo').getValue()
			};
			listDetail1.push(strDtl);
			var strDtl={
			columnId:'cabm.warehouse_no',
			value:Ext.get("warehouseNo").getValue()
			};
			listDetail1.push(strDtl);
			var strDtl={
			columnId:'cabm.line_id',
			value:Ext.getCmp("textLineId2301").getValue()
			};
			listDetail1.push(strDtl);
			Ext.Ajax.request({
			url:'cset_AreaBackupAction_existsAreaBackup.action',
			params : {
				strQuery:Ext.encode(listDetail1)
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					Ext.getCmp('textLineId2301').setValue('');
					Ext.getCmp('txtLineName2301').setValue('');
					Ext.getCmp('cmbDefaultFlag2301').setValue('');
					Ext.example.msg($i18n.prompt,'保拣线ID已经存在'); 
				}else{
				}
			}
		});
	}		
}
 /**
 * 新增保拣关线始化
 * zhouhuan
 */
function addCset_Area_Backup(){
	Ext.getCmp('cset_AreaBackupAddOrEditLine2301').getForm().reset();
}
/**
 * 新增保拣级别初始化
 * zhouhuan
 */
function addCset_Area_Backup2301_2(){
	Ext.getCmp('cset_AreaBackupEditLevel2301').getForm().reset();
}

/**
 * 保存保拣线
 * zhouhuan
 */
function saveCset_Area_Backup(th){
	var but=th;
	if(Ext.getCmp('cset_AreaBackupAddOrEditLine2301').getForm().isValid())
	{
		var cmbStockNo='N';
        if(Ext.getCmp('cmbStockNo2301A').getValue()!=''&&Ext.getCmp('cmbStockNo2301A').getValue()!=null){
        	cmbStockNo=Ext.getCmp('cmbStockNo2301A').getValue();
        }
        	        	
		var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					lineId:Ext.getCmp('textLineId2301').getValue()
				},
				lineName:Ext.getCmp('txtLineName2301').getValue(),
				defaultFlag:Ext.getCmp('cmbDefaultFlag2301').getValue(),
				rgstName:Ext.get('workerName').getValue(),
				rgstDate:new Date(),
				SWareNo:Ext.getCmp('cmbWareNo2301A').getValue(),
				SAreaNo:Ext.getCmp('cmbAreaNo2301A').getValue(),
				SStockNo:cmbStockNo
		};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url:'cset_AreaBackupAction_saveOrUpdatecset_AreaBackupM.action',
			params : {
				str:jsonStr
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('cset_AreaBackupMGrid2301').getStore().load();
					commonSetMsterReadOnlyByArray(
						new Array('textLineId2301'),true);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
}
/*
 * 保存保拣级别
 * zhouhuan
 */
function saveCset_Area_Backup2301_2(th){
	
		
		if(Ext.getCmp('cset_AreaBackupDGrid2301').getStore().data.length!=0){
			var firstData =Ext.getCmp('cset_AreaBackupDGrid2301').getStore().getAt(0).data.stockNo;
			if(firstData!='N'){
				if(Ext.getCmp("cmbStockNo2301").getValue()=='N' || Ext.getCmp("cmbStockNo2301").getValue()==null || Ext.getCmp("cmbStockNo2301").getValue()==''){
					Ext.example.msg("提示","请输入通道代码");
					return;
				}	
			}
		}
	    var objrecord0 = Ext.getCmp('cset_AreaBackupDGrid2301').getStore().query('ALevel','0');
		if(objrecord0.length == 0 && g_varbutton2301 == 'add2' && Ext.getCmp('txtA_level2301').getValue() != 0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.levelMustStartAt0);
				Ext.getCmp('txtA_level2301').setValue();
				return;
		}
		if(objrecord0.length != 0 && g_varbutton2301 == 'add2' && Ext.getCmp('txtA_level2301').getValue()=='0')
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.aLineOnlyOneLevel);//'一条保拣线只能有一个0级别！'
			Ext.getCmp('txtA_level2301').setValue();
			return;
		}
		var cmbStockNo2301=Ext.getCmp('cmbStockNo2301').getValue();
		if(cmbStockNo2301==null||cmbStockNo2301==''){
			Ext.getCmp('cmbStockNo2301').setValue('N');
			cmbStockNo2301='N';
		}
		var objStore = Ext.getCmp('cset_AreaBackupDGrid2301').getStore().queryBy(function(objrecord) {  
			 if(objrecord.get('wareNo') == Ext.getCmp("cmbWareNo2301").getValue()
				&& objrecord.get('areaNo') == Ext.getCmp("cmbAreaNo2301").getValue()
				&& objrecord.get('stockNo') == Ext.getCmp("cmbStockNo2301").getValue()
				&& objrecord.get('ALevel') == Ext.getCmp("txtA_level2301").getValue()
				&& objrecord.get('warehouseNo') == Ext.get('warehouseNo').getValue())
			 {
				 return true;
             }else 
             {
            	 return false;
             }
		});
		if(objStore.length != 0 && g_varbutton2301 == 'add2')
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.theLineAlreadyExist);//'该保拣线已存在！'
			Ext.getCmp('txtA_level2301').setValue('');
			Ext.getCmp('cmbWareNo2301').setValue('');
			Ext.getCmp('cmbAreaNo2301').setValue('');
			Ext.getCmp('cmbStockNo2301').setValue('');
			return;
		}
		Ext.getCmp('cmbWareNo2301').fireEvent('blur',this.areaBackupGrade2301Blur);
		var but=th;
		if(Ext.getCmp('cset_AreaBackupEditLevel2301').getForm().isValid()){	
		    var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					lineId:Ext.getCmp('txtLine_id2301').getValue(),
					wareNo:Ext.getCmp('cmbWareNo2301').getValue(),
					areaNo:Ext.getCmp('cmbAreaNo2301').getValue(),
					ALevel:Ext.getCmp('txtA_level2301').getValue(),
					stockNo:cmbStockNo2301
				},
				keepCells:Ext.getCmp('txtkeep_cells2301').getValue(),
				mergerFlag:Ext.getCmp('cmbMerger_flag2301').getValue(),
				stockFlag:Ext.getCmp('cmbStock_flag2301').getValue(),
				floorFlag:Ext.getCmp('cmbFloor_flag2301').getValue(),
				bayFlag:Ext.getCmp('cmbBay_flag2301').getValue(),
				sortFlag:Ext.getCmp('cmbSort_flag2301').getValue(),
				stockxFlag:Ext.getCmp('cmbStockX_flag2301').getValue(),
				rgstName:Ext.get('workerName').getValue(),
				rgstDate:new Date(),
				updtName:Ext.get('workerName').getValue(),
				updtDate:new Date()
		};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			url:'cset_AreaBackupAction_saveOrUpdatecsetAreaBackupLevel.action',
			params : {
				str:jsonStr,
				flag:flagText
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('cset_AreaBackupDGrid2301').getStore().load();
//						commonSetMsterReadOnlyByArray(
//							new Array('txtA_level2301','cmbWareNo2301',
//							'cmbAreaNo2301','cmbStockNo2301'),true);
					Ext.getCmp('cset_AreaBackupDGrid2301').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	  }
}

/**
 * 关闭界面
 */
function closeWindow(){
	Ext.getCmp('cset_AreaBackupAddOrEditWindow').close();
}
/**
 * 关闭级别界面
 */
function closeWindow2301_2(){
	Ext.getCmp('cset_AreaBackupAddOrEditWindow2301_2').close();
}

/**
 * 填充保拣线数据
 * zhouhuan
 */
function loadCset_Area_Backup(g_strRowindex2301){
	
	var objRecord=Ext.getCmp('cset_AreaBackupMGrid2301').getStore().getAt(g_strRowindex2301);	

    Ext.getCmp('cmbDefaultFlag2301').setValue(objRecord.data.defaultFlag);
	Ext.getCmp('textLineId2301').setValue(objRecord.data.lineId);
	Ext.getCmp('txtLineName2301').setValue(objRecord.data.lineName);
	
	Ext.getCmp('cmbWareNo2301A').setValue(objRecord.data.SWareNo);
	Ext.getCmp('cmbAreaNo2301A').setValue(objRecord.data.SAreaNo);
    Ext.getCmp('cmbStockNo2301A').setValue(objRecord.data.SStockNo);
	//alert(record.data.stockNo)
}


/**
 * 填充保拣级别数据
 * zhouhuan
 */
function loadCset_Area_Backup2301_2(g_strRowindex2301){
	var record=Ext.getCmp('cset_AreaBackupDGrid2301').getStore().getAt(g_strRowindex2301);	
	Ext.getCmp('txtLine_id2301').setValue(record.data.lineId);
	Ext.getCmp('txtA_level2301').setValue(record.data.ALevel);
	Ext.getCmp('cmbWareNo2301').setValue(record.data.wareNo);

	Ext.getCmp('cmbAreaNo2301').setValue(record.data.areaNo);
    Ext.getCmp('cmbStockNo2301').setValue(record.data.stockNo);
	Ext.getCmp('txtkeep_cells2301').setValue(String(record.data.keepCells));
	Ext.getCmp('cmbMerger_flag2301').setValue(String(record.data.mergerFlag));
	Ext.getCmp('cmbStock_flag2301').setValue(String(record.data.stockFlag));
	Ext.getCmp('cmbFloor_flag2301').setValue(String(record.data.floorFlag));
	Ext.getCmp('cmbBay_flag2301').setValue(String(record.data.bayFlag));
	Ext.getCmp('cmbSort_flag2301').setValue(String(record.data.sortFlag));
	Ext.getCmp('cmbStockX_flag2301').setValue(String(record.data.stockxFlag));
}
