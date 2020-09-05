/**
 * 模块名称：病单处理
 * 模块代码：3917
 * @author hcx
 */
var rowindex3917=0;
var isCanEdit3917=false;
Ext.define('cms.controller.odata.odata_ExpCancelController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_ExpCancelUI'
	],
	init:function(){
		this.control({//刷新
			'odata_ExpCancelUI button[id=btnRefresh3917]':{
				click:this.btnRefresh3917Click
			},//货主下拉选择
			'odata_ExpCancelUI[id=owner3917]':{
				select:this.owner3917Select
		    },//单据状态下拉选择
			'odata_ExpCancelUI combo[id=statusText3917]':{
				select:this.statusText3917Select
		    },//病单来源下拉选择
			'odata_ExpCancelUI combo[id=suorceType3917]':{
				select:this.suorceType3917Select
		    },//日期选择
			'odata_ExpCancelUI datefield[id=operateDate3917]':{
				change:this.operateDate3917Select
		    },//撕票单号选择
			'odata_ExpCancelUI combo[id=cancelNo3917]':{
				select:this.cancelNo3917Select
		    },//是否历史单据选择
			'odata_ExpCancelUI checkboxfield[id=cbHistoryFlag3917]':{
				change:this.cbHistoryFlag3917Change
		    },//TAB页切换
			'odata_ExpCancelUI tabpanel[id=tabPId3917_1]':{
				tabchange:this.tabPIdtabchange
			},//单据列表 双击
			'odata_ExpCancelUI grid[id=oecmGrid3917]':{
				itemdblclick:this.oecmGrid3917Click			
			},//上单
			'odata_ExpCancelUI button[id=userPrevButton]':{
				click:this.userPrev
			},//下单
			'odata_ExpCancelUI button[id=userNextButton]':{
				click:this.userNext
			},//上传
			'odata_ExpCancelUI button[id=btnUplocad3917]':{
				click:this.btnUplocad3917click
			},//补拣
			'odata_ExpCancelUI button[id=btnRepeatLocate3917]':{
				click:this.btnRepeatLocate3917click
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		isCanEditC101=false;
		
		//显示变量0为不显示，1为显示
		var planBox_3917=commonGetModuleField('3917','planBox')[0].flag;	//计划
		var planQmin_3917=commonGetModuleField('3917','planQmin')[0].flag;
		var planDis_3917=commonGetModuleField('3917','planDis')[0].flag;
		var packingUnit_3917=commonGetModuleField('3917','packingUnit')[0].flag;
		var packingSpec_3917=commonGetModuleField('3917','packingSpec')[0].flag;
		
		var realBox_3917=commonGetModuleField('3917','realBox')[0].flag;	//实际
		var realQmin_3917=commonGetModuleField('3917','realQmin')[0].flag;
		var realDis_3917=commonGetModuleField('3917','realDis')[0].flag;
		
		var differenceBox_3917=commonGetModuleField('3917','differenceBox')[0].flag;	//差异
		var differenceQmin_3917=commonGetModuleField('3917','differenceQmin')[0].flag;
		var differenceDis_3917=commonGetModuleField('3917','differenceDis')[0].flag;
		
		if(planBox_3917==0){
			Ext.getCmp('planBox_3917').setVisible(false);
		}
		if(planQmin_3917==0){
			Ext.getCmp('planQmin_3917').setVisible(false);
		}
		if(planDis_3917==0){
			Ext.getCmp('planDis_3917').setVisible(false);
		}
		if(packingUnit_3917==0){
			Ext.getCmp('packingUnit_3917').setVisible(false);
		}
		if(packingSpec_3917==0){
			Ext.getCmp('packingSpec_3917').setVisible(false);
		}
		
		if(realBox_3917==0){
			Ext.getCmp('realBox_3917').setVisible(false);
		}
		if(realQmin_3917==0){
			Ext.getCmp('realQmin_3917').setVisible(false);
		}
		if(realDis_3917==0){
			Ext.getCmp('realDis_3917').setVisible(false);
		}
		
		if(differenceBox_3917==0){
			Ext.getCmp('differenceBox_3917').setVisible(false);
		}
		if(differenceQmin_3917==0){
			Ext.getCmp('differenceQmin_3917').setVisible(false);
		}
		if(differenceDis_3917==0){
			Ext.getCmp('differenceDis_3917').setVisible(false);
		}
		
	},
	//刷新
	btnRefresh3917Click:function(th,newValue,oldValue,eOpts)
	{
		Refresh3917();
	},
	//货主下拉选择
	owner3917Select:function(){
    	if(!Ext.isEmpty(Ext.getCmp('owner3917').getValue())){
			var ownerNo = Ext.getCmp('owner3917').getValue();
			var detail1 = [];
			var d={
				    columnId:'a.owner_no',
				    value:ownerNo
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var operateDate = '';
			var Flag = Ext.getCmp('cbHistoryFlag3917').getValue()==true ? 1:0;
			var params = {
					strFlag : Flag,
					str : jsonDetail1,
					strOperateDate : operateDate
				};
			Ext.apply(Ext.getCmp('statusText3917').getStore().proxy.extraParams,params);
			Ext.getCmp('statusText3917').getStore().removeAll();
			Ext.getCmp('statusText3917').getStore().load();
			Ext.apply(Ext.getCmp('oecmGrid3917').getStore().proxy.extraParams,params);
			Ext.getCmp('oecmGrid3917').getStore().removeAll();
			Ext.getCmp('oecmGrid3917').getStore().load();
    	}
	},
	statusText3917Select:function(){
		if(!Ext.isEmpty(Ext.getCmp('owner3917').getValue())){
			var ownerNo = Ext.getCmp('owner3917').getValue();
			var detail1 = [];
			var d={
				    columnId:'a.owner_no',
				    value:ownerNo
			};
			detail1.push(d);
			if(!Ext.isEmpty(Ext.getCmp('statusText3917').getValue())){
				var status = Ext.getCmp('statusText3917').getValue();
				var e={
					    columnId:'a.status',
					    value:status
				};
				detail1.push(e);
				var jsonDetail1 = Ext.encode(detail1);
				var Flag = Ext.getCmp('cbHistoryFlag3917').getValue()==true ? 1:0;
				var params = {
						strFlag : Flag,
						str : jsonDetail1
					};
				Ext.apply(Ext.getCmp('suorceType3917').getStore().proxy.extraParams,params);
				Ext.getCmp('suorceType3917').getStore().removeAll();
				Ext.getCmp('suorceType3917').getStore().load();
				Ext.apply(Ext.getCmp('oecmGrid3917').getStore().proxy.extraParams,params);
				Ext.getCmp('oecmGrid3917').getStore().removeAll();
				Ext.getCmp('oecmGrid3917').getStore().load();
			}
			
    	}
	},
	//病单来源下拉选择
	suorceType3917Select:function(){
		suorceTypeOroperateDateOrCancelNo3917Select();
	},
	//日期选择
	operateDate3917Select:function(){
		suorceTypeOroperateDateOrCancelNo3917Select();
	},
	//撕票单号选择
	cancelNo3917Select:function(){
		suorceTypeOroperateDateOrCancelNo3917Select();
	},
	//是否历史单据选择
	cbHistoryFlag3917Change:function(){
		var Flag = Ext.getCmp('cbHistoryFlag3917').getValue()==true ? 1:0;
		if(Flag == 1){
			Ext.getCmp('statusText3917').setValue('审核确认');
//			commonSetMsterReadOnlyByArray(
//					new Array('statusText_3917'),true);
			if(!Ext.isEmpty(Ext.getCmp('owner3917').getValue())){
				var ownerNo = Ext.getCmp('owner3917').getValue();
				var detail1 = [];
				var d={
					    columnId:'a.owner_no',
					    value:ownerNo
				};
				detail1.push(d);
				var jsonDetail1 = Ext.encode(detail1);
				var params = {
					strFlag : Flag,
						str : jsonDetail1
					};
				Ext.apply(Ext.getCmp('suorceType3917').getStore().proxy.extraParams,params);
				Ext.getCmp('suorceType3917').setValue('');
				Ext.getCmp('suorceType3917').getStore().removeAll();
				Ext.getCmp('suorceType3917').getStore().load();
				Ext.getCmp('operateDate3917').setValue('');
				Ext.getCmp('cancelNo3917').setValue('');
				Ext.getCmp('cancelNo3917').getStore().removeAll();
				Ext.apply(Ext.getCmp('oecmGrid3917').getStore().proxy.extraParams,params);
				Ext.getCmp('oecmGrid3917').getStore().removeAll();
				Ext.getCmp('oecmGrid3917').getStore().load();
				}
			}else{
				Refresh3917();
			}
	},

	//TAB也切换
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPId3917_2'){
			var data = Ext.getCmp('oecmGrid3917').getSelectionModel().getSelection();
			if(data.length!=0){
				editExp3917(data[0].index);
				rowindex3917=data[0].index;				
				isCanEdit3917=false;
			}
		}
	},
	//grid双击
	oecmGrid3917Click:function(th, record,  item,  index, e, eOpts ){
		Ext.getCmp('tabPId3917_1').items.items[1].setVisible(true);
		isCanEditC101=false;
	},
	//上单
	userPrev:function(){
		rowindex3917=rowindex3917-1;
		editExp3917(rowindex3917);
	},
	//下单
	userNext:function(){
		rowindex3917=rowindex3917+1;
		editExp3917(rowindex3917);
	},
	//上传
	btnUplocad3917click:function(){

		var record = Ext.getCmp('oecmGrid3917').getSelectionModel().getSelection();
        if(record.length>0){
        	var Detail={
        		warehouseNo:Ext.get('warehouseNo').getValue(),	
        		ownerNo:Ext.getCmp('owner3917').getValue(),
        		cancelNo:record[0].get("cancelNo"),
        		expNo:record[0].get("expNo"),
        		updtName:Ext.get('workerNo').getValue()
        	};
        	var strDetail = Ext.encode(Detail);
        	var gridcount=Ext.getCmp('oecdGrid3917').getStore().getCount();
        	if(gridcount==0){
    			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseOrder);
    			return;
    		}
        	var params = 
    		{
    			strDetail:strDetail,
    		};
        	Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureUplocad,function(button, text){
				if (button == 'yes') {
					Ext.Ajax.request({
		    			method:'POST',
		    			async:false,
		    			params:params,
		    			url:'odata_ExpCancelAction_upLocad.action',
		    			success:function(response)
		    			{
		    				data = Ext.decode(response.responseText);
		    				if(data.isSucc){
								Ext.example.msg($i18n.prompt,$i18n_prompt.uplocadSuccess);
								Refresh3917();
							}else{
								Ext.Msg.alert($i18n.prompt,data.msg);

								//Ext.example.msg($i18n.prompt,data.msg);
							}
		    			}
		    		});
				}
			});    		
        }
	},
	//补拣
	btnRepeatLocate3917click:function(){
		var a = [];
    	var gridcount = Ext.getCmp('oecdGrid3917').getStore().getCount();
    	if(gridcount==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseOrder);
			return;
		}
    	for(var i=0;i<gridcount;i++ ){
        	var record1 = Ext.getCmp('oecdGrid3917').getStore().getAt(i);
        	var noEnoughQty = record1.get('noEnoughQty');
        	if(noEnoughQty > 0){
            	a.push(noEnoughQty);
        	}
    	}
    	if(a.length>0){
    		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.repeatLocate,function(button, text){
				if (button == 'yes') {
					Fixed3917();
				}
			});
    	}else{
    		Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureRepeatLocate,function(button, text){
				if (button == 'yes') {
					Fixed3917();
				}
			});
    	}
	}
});
function suorceTypeOroperateDateOrCancelNo3917Select(){
	if(!Ext.isEmpty(Ext.getCmp('owner3917').getValue())){
		var ownerNo = Ext.getCmp('owner3917').getValue();
		if(!Ext.isEmpty(Ext.getCmp('statusText3917').getValue())){
			var status = Ext.getCmp('statusText3917').getValue();
			if(!Ext.isEmpty(Ext.getCmp('suorceType3917').getValue())){
				var suorceType = Ext.getCmp('suorceType3917').getValue();
				var detail1 = [];
				var detail2 = [];
				var d={
					    columnId:'a.owner_no',
					    value:ownerNo
				};
				detail1.push(d);
				detail2.push(d);
				var Flag = Ext.getCmp('cbHistoryFlag3917').getValue()==true ? 1:0;
				if(Flag=='0'){
					var b={
						    columnId:'a.status',
						    value:status
					};
					detail1.push(b);
					detail2.push(b);
				}
//				var b={
//					    columnId:'a.status',
//					    value:status
//				};
//				detail1.push(b);
//				detail2.push(b);
				var a={
						columnId:'a.source_type',
						value:suorceType	
				};
				detail1.push(a);
				detail2.push(a);
				var jsonDetail2 = Ext.encode(detail2);
				if(!Ext.isEmpty(Ext.getCmp('operateDate3917').getValue())){
					var operateDate = Ext.Date.format( Ext.getCmp('operateDate3917').getValue(),'Y/m/d');
				}else{
					var operateDate = '';
				}
				if(!Ext.isEmpty(Ext.getCmp('cancelNo3917').getValue())){
					var cancelNo = Ext.getCmp('cancelNo3917').getValue();
					var c={
						    columnId:'a.cancel_no',
						    value:cancelNo
					};
					detail1.push(c);
				}
				
				var jsonDetail1 = Ext.encode(detail1);
				var params = {
					    strFlag : Flag,
						str : jsonDetail1,
						strOperateDate : operateDate
					};
				
				var jsonDetail = {
						strFlag : Flag,
						str : jsonDetail2,
						strOperateDate : operateDate
					};
				
				Ext.apply(Ext.getCmp('cancelNo3917').getStore().proxy.extraParams,jsonDetail);
				Ext.getCmp('cancelNo3917').getStore().removeAll();
				Ext.getCmp('cancelNo3917').getStore().load();
				
				Ext.apply(Ext.getCmp('oecmGrid3917').getStore().proxy.extraParams,params);
				Ext.getCmp('oecmGrid3917').getStore().removeAll();
				Ext.getCmp('oecmGrid3917').getStore().load();
			}
		}
		
	}
};
//填充数据
function editExp3917(rowindex3917){
	var record = Ext.getCmp('oecmGrid3917').getStore().getAt(rowindex3917-(Ext.getCmp('oecmGrid3917').getStore().currentPage-1)*appConfig.getPageSize());
    if(record.length == 0)
    {
		Ext.getCmp('oecdGrid3917').getStore().removeAll();
    }else{
    	if(rowindex3917 == 0){
			disableButtonFunc(1,'#userPrevButton',$i18n.userPrev);
    	}else{
			disableButtonFunc(0,'#userPrevButton',$i18n.userPrev);
    	}
    	if(rowindex3917==Ext.getCmp('oecmGrid3917').getStore().getCount()-1){
			disableButtonFunc(1,'#userNextButton',$i18n.userNext);
    	}else{
			disableButtonFunc(0,'#userNextButton',$i18n.userNext);
    	}
    	Ext.getCmp('owner_3917').setValue(record.data.ownerNo);
    	Ext.getCmp('statusText_3917').setValue(record.data.statusText);
    	Ext.getCmp('suorceType_3917').setValue(record.data.suorceTypeText);
    	Ext.getCmp('operateDate_3917').setValue(record.data.operateDate);
    	Ext.getCmp('cancelNo_3917').setValue(record.data.cancelNo);
		var Flag = Ext.getCmp('cbHistoryFlag3917').getValue()==true ? 1:0;
    	var wheresql = {
    			strFlag : Flag,
			str :record.data.cancelNo
		};
		Ext.apply(Ext.getCmp('oecdGrid3917').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('oecdGrid3917').getStore().removeAll();
		Ext.getCmp('oecdGrid3917').getStore().load();
		var status = record.data.statusText;
		if(status != $i18n.assign){
			disableButtonFunc(1,'#btnUplocad3917',$i18n.uplocad);
			disableButtonFunc(1,'#btnRepeatLocate3917',$i18n.repeatLocate);
			}else{
				disableButtonFunc(0,'#btnUplocad3917',$i18n.uplocad);
				disableButtonFunc(0,'#btnRepeatLocate3917',$i18n.repeatLocate);
			}
    }
};
function Fixed3917(){
	var record = Ext.getCmp('oecmGrid3917').getSelectionModel().getSelection();
	if(record.length>0){
    	var Detail={
    		warehouseNo:Ext.get('warehouseNo').getValue(),	
    		ownerNo:Ext.getCmp('owner3917').getValue(),
    		cancelNo:record[0].get("cancelNo"),
    		expNo:record[0].get("expNo"),
    		sourceType:record[0].get('sourceType'),
    		updtName:Ext.get('workerNo').getValue()
    	};
    	//alert(Detail);
    	var strDetail = Ext.encode(Detail);
    	var params = 
		{
			strDetail:strDetail
		};
    	Ext.Ajax.request({
			method:'POST',
			async:false,
			params:params,
			url:'odata_ExpCancelAction_repeatLocate.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,$i18n_prompt.repeatLocateSuccess);
					Refresh3917();
				}else{
					Ext.Msg.alert($i18n.prompt,data.msg);

					//Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};
function Refresh3917(){
	Ext.getCmp('owner3917').setValue('');
	Ext.getCmp('statusText3917').setValue(null);
	Ext.getCmp('statusText3917').getStore().removeAll();
	Ext.getCmp('suorceType3917').setValue(null);
	Ext.getCmp('suorceType3917').getStore().removeAll();
	Ext.getCmp('operateDate3917').setValue(null);
	Ext.getCmp('cancelNo3917').setValue(null);
	Ext.getCmp('cancelNo3917').getStore().removeAll();
	Ext.getCmp('owner3917').getStore().load();
	Ext.getCmp('cbHistoryFlag3917').setValue(false);
	Ext.getCmp('oecmGrid3917').getStore().removeAll();
	Ext.getCmp('oecmGrid3917').getStore().load();
	Ext.getCmp('oecdGrid3917').getStore().removeAll();
	Ext.getCmp('owner_3917').setValue(null);
	Ext.getCmp('statusText_3917').setValue(null);
	Ext.getCmp('suorceType_3917').setValue(null);
	Ext.getCmp('operateDate_3917').setValue(null);
	Ext.getCmp('cancelNo_3917').setValue(null);

};
