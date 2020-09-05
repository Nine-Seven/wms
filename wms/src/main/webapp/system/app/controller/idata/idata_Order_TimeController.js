/**
 * 模块名称：预约进货
 * 模块编码：4201
 * 创建：LICH 
 */

var isCanEdit4201=false;
Ext.define('cms.controller.idata.idata_Order_TimeController', {
	extend : 'Ext.app.Controller',
	requires : [ 'cms.view.idata.idata_Order_TimeUI'],
	model : '',
	store : '',
	init : function() {
		this.control({//预约进货》保存
			'idata_Order_TimeUI commMenuWidget4[id=menu4201] button[name=save]':{
				click:this.saveClick
			},//预约进货》修改（修改预约时间）
			'idata_Order_TimeUI commMenuWidget4[id=menu4201] button[name=edit]':{
				click:this.editClick
			},//刷新
			'idata_Order_TimeUI commMenuWidget4[id=menu4201] button[name=refresh]':{
				click:this.refreshClick
			},//tab切换
			'idata_Order_TimeUI tabpanel[id=tabPId4201]':{
				tabchange:this.tabPId4201Tabchange
			},//预约状况》数据选择
			'idata_Order_TimeUI grid[id=grid_01_4201]':{
				selectionchange:this.grid_01_4201Selectionchange
			},//预约状况》grid2选择
			'idata_Order_TimeUI grid[id=grid_02_4201]':{
				selectionchange:this.grid_02_4201Selectionchange
			},//预约状况》日期选择
			'idata_Order_TimeUI datefield[id=appoint_date4201]':{
				change:this.appoint_date4201Select
			},//预约状况》委托业主下拉
			'idata_Order_TimeUI combo[id=owner4201]':{
				select:this.owner4201Select
			},//预约状况》码头下拉
			'idata_Order_TimeUI combo[id=dockNo4201_1]':{
				select:this.dockNo4201_1Select
			},//预约进货》委托业主下拉
			'idata_Order_TimeUI combo[id=tab3_owner4201]':{
				select:this.tab3_owner4201Select
			},//预约进货》供应商下拉
			'idata_Order_TimeUI combo[id=tab3_supplierNo4201]':{
				select:this.tab3_supplierNo4201Select
			},//预约进货》单据类型下拉
			'idata_Order_TimeUI combo[id=tab3_outStockType4201]':{
				select:this.tab3_outStockType4201Select
			},//预约进货》日期选择
			'idata_Order_TimeUI datefield[id=tab3_appoint_date4201]':{
				change:this.tab3_appoint_date4201Select
			},//预约进货》时间段选择
			'idata_Order_TimeUI grid[id=grid_06_4201]':{
				beforeselect:this.grid_06_4201Beforeselect
			},//选择要预约的数据
			'idata_Order_TimeUI grid[id=grid_05_4201]':{
				select:this.grid_01_3201Select,//选中事件
				deselect:this.grid_01_3201Deselect,
				itemdblclick:this.detailBrowse//双击事件
			},//关闭
			'idata_Order_TimeEditWindow button[name=close]':
			{
				click:this.close
			},//保存
			'idata_Order_TimeEditWindow button[name=save]':
			{
				click:this.editSave
			},//预约进货修改》日期选择
			'idata_Order_TimeEditWindow datefield[id=appoint_date4201_win]':{
				change:this.appoint_date4201Select_win
			}
		});
	},
	
	grid_01_3201Select:function(){
		var arrayObj = new Array();
		arrayObj[0]='articleItems';
		arrayObj[1]='pkQty';
		countListBySelect('grid_05_4201',arrayObj,'ownerNo',-1);
	},
	grid_01_3201Deselect:function(){
		var arrayObj = new Array();
		arrayObj[0]='articleItems';
		arrayObj[1]='pkQty';
		countListBySelect('grid_05_4201',arrayObj,'ownerNo',-1);
	},
	detailBrowse:function(){
		var data=Ext.getCmp('grid_05_4201').getSelectionModel().getSelection();
		if (data.length == 0) 
		{
			Ext.example.msg($i18n.prompt,'请勾选单据后双击查看明细！');
        }
		else
		{
        	Ext.create('cms.view.idata.windows.idata_Order_Time_DListWindow',
			{
        		title:'单据明细'
			}).show();
        	var record=Ext.getCmp('grid_05_4201').getSelectionModel().getSelection();
        	var strWheresql = {
        			wheresql:record[0].get('importNo')
    		};
    		Ext.apply(Ext.getCmp('grid_05_4201_d').getStore().proxy.extraParams,strWheresql);
    		Ext.getCmp('grid_05_4201_d').getStore().removeAll();
    		Ext.getCmp('grid_05_4201_d').getStore().load();
		}
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		//显示变量0为不显示，1为显示
		var planBox4201=commonGetModuleField('4201','planBox')[0].flag;
		var planQmin4201=commonGetModuleField('4201','planQmin')[0].flag;
		var planDis4201=commonGetModuleField('4201','planDis')[0].flag;
		var packingUnit4201=commonGetModuleField('4201','packingUnit')[0].flag;
		var packingSpec4201=commonGetModuleField('4201','packingSpec')[0].flag;

		if(planBox4201==0){
			Ext.getCmp('planBox4201').setVisible(false);
		}
		if(planQmin4201==0){
			Ext.getCmp('planQmin4201').setVisible(false);
		}
		if(planDis4201==0){
			Ext.getCmp('planDis4201').setVisible(false);
		}
		if(packingUnit4201==0){
			Ext.getCmp('packingUnit4201').setVisible(false);
		}
		if(packingSpec4201==0){
			Ext.getCmp('packingSpec4201').setVisible(false);
		}
		
		Ext.getCmp('appoint_date4201').setValue(new Date);
		Ext.getCmp('appoint_date4201').fireEvent('change','');
		if(Ext.getCmp('menu4201').items.items[4].isVisible()){
			Ext.getCmp('menu4201').items.items[4].disable(true);				
		}
	},
	editClick:function(){
		Ext.create('cms.view.idata.windows.idata_Order_TimeEditWindow',
				{
					title:$i18n.titleupdate//修改
				}).show();
		Ext.getCmp('bdef_MenuWidget1401_win').items.items[1].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401_win').items.items[2].setVisible(false);
		Ext.getCmp('bdef_MenuWidget1401_win').items.items[3].setVisible(false);
		var record = Ext.getCmp('grid_01_4201').getSelectionModel().getSelection();
		var record2 = Ext.getCmp('grid_02_4201').getSelectionModel().getSelection();
		Ext.getCmp('order_serial4201_win').setValue(record2[0].get('orderSerial'));
		Ext.getCmp('appoint_date4201_win').setValue(Ext.getCmp('appoint_date4201').getValue());
		Ext.getCmp('dockNo4201_2_win').setValue(record[0].get('dockNo'));
	},
	saveClick:function(th){
		var record = Ext.getCmp('grid_05_4201').getSelectionModel().getSelection();
        if(record.length == 0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.clooseOrder);
        }else{
        	if(Ext.isEmpty(Ext.getCmp('dockNo4201_2').getValue())){
				Ext.example.msg($i18n.prompt,'请输入码头号');
				return;
			}
        	var rec = Ext.getCmp('grid_06_4201').getSelectionModel().getSelection();
        	if(rec.length==0){
        		Ext.example.msg($i18n.prompt,$i18n_prompt.clooseTime);
        		return;
        	}
        	        	
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
				return;
			}
			
			if(Ext.isEmpty(Ext.getCmp('tab3_appoint_date4201').getValue())){
				Ext.example.msg($i18n.prompt,$i18n_prompt.clooseAppointmentTime);
				return;
			}else{
				if(Ext.Date.add(Ext.getCmp('tab3_appoint_date4201').getValue(),Ext.Date.SECOND,24*60*60-1)<new Date()){
					Ext.example.msg($i18n.prompt,$i18n_prompt.appointTimeCannotLessThanToday);
					Ext.getCmp('tab3_appoint_date4201').focus();
					return;
				}
			}
			
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,
			function(button, text) {
			if (button == 'yes') {
				var msgShow = commonMegShow($i18n_prompt.saving_wait);
				var ids = "";
	            for(var i = 0; i < record.length; i++){
	                ids += record[i].get("importNo");
	                if(i<record.length-1){
	                    ids = ids + ",";
	                }
	            }
				var params = {
						str:ids,
						time:Ext.getCmp('tab3_appoint_date4201').getValue()+","+rec[0].data.startTime+","+rec[0].data.endTime,
						flag:commonGetSystemParams(Ext.getCmp('owner4201').getValue(),'Idata_collectOrder','I','IC')[0].sdefine,
						printFlag:Ext.getCmp('radiogroup4201').getValue().rb,
						strDockNo:Ext.getCmp('dockNo4201_2').getValue()==undefined? 'N':Ext.getCmp('dockNo4201_2').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'idata_Order_TimeAction_save.action',
					params:params,
					success:function(response){
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.MessageBox.alert("提示", "流水号： "+data.obj);
							Ext.getCmp('grid_05_4201').getStore().removeAll();
							Ext.getCmp('tab3_owner4201').setValue('');
							Ext.getCmp('tab3_owner4201').store.reload();
							if(Ext.isEmpty(Ext.getCmp('tab3_owner4201').getValue())){
								Ext.getCmp('tab3_supplierNo4201').setValue('');
								Ext.getCmp('tab3_supplierNo4201').getStore().removeAll();
								Ext.getCmp('tab3_outStockType4201').setValue('');
								Ext.getCmp('tab3_outStockType4201').getStore().removeAll();
							}
								
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg);
						}				
					},
					failure:function(response){
						Ext.Msg.alert($i18n.prompt,$i18n_prompt.CannotSubForWeb);
						msgShow.hide();
					}
				});	
			}
			});
        }
	},
	refreshClick:function(){
		if(Ext.getCmp('tabPId4201').getActiveTab().title==$i18n.order_condition){
			if(Ext.isEmpty(Ext.getCmp('appoint_date4201').getValue())){
				this.initializtion();
			}else{
				Ext.getCmp('appoint_date4201').fireEvent('change','');
			}
		}else{
			Ext.getCmp('tab3_owner4201').getStore().load();	
			Ext.getCmp('grid_06_4201').getStore().load();
		}
	},
	tabPId4201Tabchange:function(tab){
		if(tab.getActiveTab().title==$i18n.order_condition){
			if(Ext.getCmp('menu4201').items.items[4].isVisible()){
				Ext.getCmp('menu4201').items.items[4].disable(true);						
			}
			if(Ext.getCmp('menu4201').items.items[1].isVisible()){
				Ext.getCmp('menu4201').items.items[1].enable(true);						
			}
		}else{
			Ext.getCmp('tab3_owner4201').getStore().removeAll();
			Ext.getCmp('tab3_owner4201').getStore().load();
			if(Ext.getCmp('menu4201').items.items[4].isVisible()){
				Ext.getCmp('menu4201').items.items[4].enable(true);				
			}
			if(Ext.getCmp('menu4201').items.items[1].isVisible()){
				Ext.getCmp('menu4201').items.items[1].disable(true);				
			}
			Ext.getCmp('grid_06_4201').getStore().load();
		}
	},
	grid_01_4201Selectionchange:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var detail1 = [];
			detail1=Ext.decode(Ext.getCmp('grid_01_4201').getStore().getProxy().extraParams.str);
			var d={
				columnId:'a.start_Time',
				value:record.data.startTime
			};
			detail1.push(d);
			var d={
				columnId:'a.end_Time',
				value:record.data.endTime
			};
			detail1.push(d);
			var c={
					columnId:'a.dock_no',
					value:record.data.dockNo
				};
				detail1.push(c);
			var d={
					columnId:'a.owner_no',
					value:record.data.ownerNo
				};
				detail1.push(d);	
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				flag:'2',
				str : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('grid_02_4201').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_4201').getStore().removeAll();
			Ext.getCmp('grid_02_4201').getStore().load();
		}else{
			Ext.getCmp('grid_02_4201').getStore().removeAll();
		}
	},
	grid_02_4201Selectionchange:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var detail1 = [];
			var d={
				columnId:'a.order_Serial',
				value:record.data.orderSerial
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
				str : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('grid_03_4201').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_4201').getStore().removeAll();
			Ext.getCmp('grid_03_4201').getStore().load();
		}else{
			Ext.getCmp('grid_03_4201').getStore().removeAll();
		}
	},
	appoint_date4201Select:function(){
		if(!Ext.isDate(Ext.getCmp('appoint_date4201').getValue())){
			return;
		}
		var detail1 = [];
		var d={
			columnId:'a.REQUEST_DATE',
			valueType:3,
			value:Ext.Date.format( Ext.getCmp('appoint_date4201').getValue(),'Y-m-d')
		};
		detail1.push(d);
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('owner4201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('owner4201').getStore().removeAll();
		Ext.getCmp('owner4201').getStore().load();
	},
	owner4201Select:function(){
		var detail1 = [];
		detail1=Ext.decode(Ext.getCmp('owner4201').getStore().getProxy().extraParams.str);
		if(!Ext.isEmpty(Ext.getCmp('owner4201').getValue())&&
				Ext.getCmp('owner4201').getValue()!='ALL')
		{
			var d={
					columnId:'a.OWNER_NO',
					value:Ext.getCmp('owner4201').getValue()
				};
				detail1.push(d);
		}
		
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			str : jsonStr
		};
		
		Ext.getCmp('dockNo4201_1').setValue('');
		Ext.apply(Ext.getCmp('dockNo4201_1').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('dockNo4201_1').getStore().removeAll();
		Ext.getCmp('dockNo4201_1').getStore().load();
		
		Ext.apply(Ext.getCmp('grid_01_4201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_01_4201').getStore().removeAll();
		Ext.getCmp('grid_01_4201').getStore().load();			
	},
	dockNo4201_1Select:function(){
		var detail1 = [];
		detail1=Ext.decode(Ext.getCmp('owner4201').getStore().getProxy().extraParams.str);
		if(!Ext.isEmpty(Ext.getCmp('owner4201').getValue())&&
				Ext.getCmp('owner4201').getValue()!='ALL')
		{
			var d={
					columnId:'a.OWNER_NO',
					value:Ext.getCmp('owner4201').getValue()
				};
				detail1.push(d);
		}
		if(!Ext.isEmpty(Ext.getCmp('dockNo4201_1').getValue())){
			var c={
					columnId:'a.dock_no',
					value:Ext.getCmp('dockNo4201_1').getValue()
				};
				detail1.push(c);
		}
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			str : jsonStr
		};
				
		Ext.apply(Ext.getCmp('grid_01_4201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_01_4201').getStore().removeAll();
		Ext.getCmp('grid_01_4201').getStore().load();			
	},
	tab3_owner4201Select:function(){
		var detail1 = [];
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('tab3_owner4201').getValue()
		};
		detail1.push(d);
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('tab3_supplierNo4201').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('tab3_supplierNo4201').getStore().removeAll();
		Ext.getCmp('tab3_supplierNo4201').getStore().load();
	},
	tab3_supplierNo4201Select:function(){
		var detail1 = [];
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('tab3_owner4201').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.supplier_no',
			value:Ext.getCmp('tab3_supplierNo4201').getValue()
		};
		detail1.push(d);
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('tab3_outStockType4201')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('tab3_outStockType4201').getStore()
				.removeAll();
		Ext.getCmp('tab3_outStockType4201').getStore()
				.load();
	},
	tab3_outStockType4201Select:function(){
		var tab3_owner4201=Ext.getCmp('tab3_owner4201').getValue();
		var tab3_supplierNo4201=Ext.getCmp('tab3_supplierNo4201').getValue();
		var tab3_outStockType4201=Ext.getCmp('tab3_outStockType4201').getValue();
		var detail1 = [];
		if(!Ext.isEmpty(tab3_owner4201)){
			var d={
			columnId:'a.owner_no',
			value:tab3_owner4201
			};
			detail1.push(d);
		}
		if(!Ext.isEmpty(tab3_supplierNo4201)){
			var d={
			columnId:'a.supplier_no',
			value:tab3_supplierNo4201
			};
			detail1.push(d);
		}
		if(!Ext.isEmpty(tab3_outStockType4201)){
			var d={
			columnId:'a.import_type',
			value:tab3_outStockType4201
			};
			detail1.push(d);
		}		
		var jsonStr = Ext.encode(detail1);
		var wheresql = {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('grid_05_4201')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('grid_05_4201').getStore()
				.removeAll();
		Ext.getCmp('grid_05_4201').getStore()
				.load();
	},
	tab3_appoint_date4201Select:function(){
		if(!Ext.isDate(Ext.getCmp('tab3_appoint_date4201').getValue())){
			return;
		}
		if(Ext.getCmp('tab3_appoint_date4201').getValue()>=new Date()){
		    Ext.getCmp('grid_06_4201').getStore().each(function(r){
		        Ext.getCmp('grid_06_4201').getView().removeRowCls(r,'x-grid-record-red');
	    	});
		}else{
			Ext.getCmp('grid_06_4201').getStore().each(function(r){
                //禁用数据显示红色   
                if(r.data.endTime<Ext.Date.format(new Date(), 'H:i')){   
                    Ext.getCmp('grid_06_4201').getView().addRowCls(r,'x-grid-record-red');
                 }else{   
                    Ext.getCmp('grid_06_4201').getView().removeRowCls(r,'x-grid-record-red');
                 }
    		});
		}
	},
	grid_06_4201Beforeselect:function(selected,record,index,eOpts){
		if(Ext.getCmp('tab3_appoint_date4201').getValue()<=new Date()){
			if(record.raw.endTime<Ext.Date.format(new Date(), 'H:i')){   
	            return  false;  
			}
		}
	},
	close:function(){
		Ext.getCmp('idata_Order_TimeEditWindow').close();
	},
	editSave:function(){
		if(Ext.isEmpty(Ext.getCmp('dockNo4201_2_win').getValue())){
			Ext.example.msg($i18n.prompt,'请输入码头号');
			return;
		}
		
		if(Ext.isEmpty(Ext.getCmp('appoint_date4201_win').getValue())){
			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseAppointmentTime);
			return;
		}else{
			if(Ext.Date.add(Ext.getCmp('appoint_date4201_win').getValue(),Ext.Date.SECOND,24*60*60-1)<new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.appointTimeCannotLessThanToday);
				Ext.getCmp('appoint_date4201_win').focus();
				return;
			}
		}
		var rec = Ext.getCmp('grid_06_4201_win').getSelectionModel().getSelection();
    	if(rec.length==0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.clooseTime);
    		return;
    	}
    	Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,
			function(button, text) {
			if (button == 'yes') {
				var params = {
						str:Ext.getCmp('order_serial4201_win').getValue(),
						time:Ext.getCmp('appoint_date4201_win').getValue()+","+rec[0].data.startTime+","+rec[0].data.endTime,
						strDockNo:Ext.getCmp('dockNo4201_2_win').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'idata_Order_TimeAction_editSave.action',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('grid_01_4201').getStore().removeAll();
							Ext.getCmp('grid_01_4201').store.reload();
								
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}				
					},
					failure:function(response){
						Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
					}
				});	
			}
			});
    	
	},
	appoint_date4201Select_win:function(){
		if(!Ext.isDate(Ext.getCmp('appoint_date4201_win').getValue())){
			return;
		}
		if(Ext.getCmp('appoint_date4201_win').getValue()>=new Date()){
		    Ext.getCmp('grid_06_4201_win').getStore().each(function(r){
		        Ext.getCmp('grid_06_4201_win').getView().removeRowCls(r,'x-grid-record-red');
	    	});
		}else{
			Ext.getCmp('grid_06_4201_win').getStore().each(function(r){
                //禁用数据显示红色   
                if(r.data.endTime<Ext.Date.format(new Date(), 'H:i')){   
                    Ext.getCmp('grid_06_4201_win').getView().addRowCls(r,'x-grid-record-red');
                 }else{   
                    Ext.getCmp('grid_06_4201_win').getView().removeRowCls(r,'x-grid-record-red');
                 }
    		});
		}
	},
});