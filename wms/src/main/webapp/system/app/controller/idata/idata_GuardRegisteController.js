/**
 * 模块名称：门卫登记
 * 模块编码：4B01
 * 创建：hcx 
 */

var isCanEdit4B01=false;
var saveType4B01='0';
var flag='0';
var serial_no='';
var owner_no='';
Ext.define('cms.controller.idata.idata_GuardRegisteController', {
	extend : 'Ext.app.Controller',
	requires : [ 'cms.view.idata.idata_GuardRegisteUI',
				 'cms.view.idata.windows.idata_GuardRegisteAddOrEditWindow'
	           ],
	model : '',
	store : '',
	init : function() {
		this.control({//采购单号选择
			'remoteCombo[id=orderSerial4B01]':{
				beforequery:this.orderSerial4B01BeforeQuery,
				select:this.orderSerial4B01Select
			},//日期选择
			'idata_GuardRegisteUI datefield[id=appoint_date4B01]':{
				change:this.appoint_date4B01Select
			},//货主选择
			'idata_GuardRegisteUI combo[id=owner4B01]':{
				select:this.owner4B01Select
			},//供应商选择
			'idata_GuardRegisteUI combo[id=suppliers4B01_1]':{
				select:this.suppliers4B01_1Select
			},//状态选择
			'idata_GuardRegisteUI combo[id=status4B01_1]':{
				select:this.status4B01_1Select
			},//预约状况信息列表数据选择
			'idata_GuardRegisteUI grid[id=grid4B01_1]':{
				selectionchange:this.grid4B01_1Selectionchange
			},//报到状况信息列表数据选择
			'idata_GuardRegisteUI grid[id=grid4B01_2]':{
				selectionchange:this.grid4B01_2Selectionchange
			},//报到
	    	'idata_GuardRegisteUI button[id=regist4B01]':{
	    		click:this.registClick
	    	},//修改
	    	'idata_GuardRegisteUI button[id=edit4B01]':{
	    		click:this.edit4B01Click
	    	},//刷新
			'idata_GuardRegisteUI  button[id=refresh4B01]':{
				click:this.refresh4B01Click
			},//关闭窗口
			'idata_GuardRegisteAddOrEditWindow button[name=close]':{
				click:this.colse
			},//保存报到信息
			'idata_GuardRegisteAddOrEditWindow button[name=save]':{
				click:this.save
			},//设置光标跳到下一输入框
			'idata_GuardRegisteAddOrEditWindow field':{
				specialkey:this.boxkeydown
			}
	    });
	},
	//初始化界面
	initializtion:function(){
		flag='0';
		Ext.getCmp('appoint_date4B01').setValue(new Date);
		Ext.getCmp('appoint_date4B01').fireEvent('change','');
		var data = Ext.getCmp('grid4B01_1').getSelectionModel().getSelection();
		if(data.length==0){
			disableButtonFunc(1,'#regist4B01',$i18n.registe);
			disableButtonFunc(1,'#edit4B01',$i18n.titleupdate);
		}
	},
	 //预约流水号选择
	orderSerial4B01BeforeQuery:function(){
		var params={
			strAppointDate:'',
			strWheresql:Ext.getCmp("orderSerial4B01").getValue(), 
			str:''
		};
		Ext.apply(Ext.getCmp('orderSerial4B01').getStore().proxy.extraParams,params);
		Ext.getCmp('orderSerial4B01').getStore().removeAll();
		Ext.getCmp('orderSerial4B01').getStore().load();
 	 },
 	orderSerial4B01Select:function(){
		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.order_serial',
			value:Ext.getCmp("orderSerial4B01").getValue()
		};
		listDetail1.push(strDtl);
		
		var params={
				strAppointDate:'',   
				str:Ext.encode(listDetail1)
			};
		Ext.getCmp('appoint_date4B01').setValue('');
		
		Ext.getCmp('owner4B01').setValue('');
	    Ext.getCmp('owner4B01').getStore().removeAll();
		
		Ext.getCmp('suppliers4B01_1').setValue('');
	    Ext.getCmp('suppliers4B01_1').getStore().removeAll();
	    
	    Ext.getCmp('status4B01_1').setValue('');
	    Ext.getCmp('status4B01_1').getStore().removeAll();
		
		Ext.apply(Ext.getCmp('grid4B01_1').getStore().proxy.extraParams,params);
		Ext.getCmp('grid4B01_1').getStore().removeAll();
		Ext.getCmp('grid4B01_1').getStore().load();

	 },
	//预约日期选择
	appoint_date4B01Select:function(){
		if(!Ext.isDate(Ext.getCmp('appoint_date4B01').getValue())){
			return;
		}
		var appointDate=Ext.Date.format( Ext.getCmp('appoint_date4B01').getValue(),'Y-m-d');
		var wheresql = {
				strAppointDate : appointDate
			};
	     	Ext.getCmp("orderSerial4B01").setValue('');
			Ext.apply(Ext.getCmp('owner4B01')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('owner4B01').getStore()
					.removeAll();
			Ext.getCmp('owner4B01').getStore()
					.load();
	},
	//货主选择
	owner4B01Select:function(){
		if(!Ext.isDate(Ext.getCmp('appoint_date4B01').getValue())){
			return;
		}
		var appointDate=Ext.Date.format( Ext.getCmp('appoint_date4B01').getValue(),'Y-m-d');
		var detail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('owner4B01').getValue())){
			var a={
					columnId:'a.owner_no',
					value:Ext.getCmp('owner4B01').getValue()
				};
			detail1.push(a);
			var wheresql = {
					strAppointDate : appointDate,
					str:Ext.encode(detail1)
				};
			Ext.apply(Ext.getCmp('suppliers4B01_1')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('suppliers4B01_1').getStore()
					.removeAll();
			Ext.getCmp('suppliers4B01_1').getStore()
					.load();
		}
	},
	//供应商选择
	suppliers4B01_1Select:function(){
		suppliers4B01_1Select();
	},
	//状态选择
	status4B01_1Select:function(){
		if(!Ext.isDate(Ext.getCmp('appoint_date4B01').getValue())){
			return;
		}
		var appointDate=Ext.Date.format( Ext.getCmp('appoint_date4B01').getValue(),'Y-m-d');
		var detail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('owner4B01').getValue())){
			var a={
					columnId:'a.owner_no',
					value:Ext.getCmp('owner4B01').getValue()
				};
			detail1.push(a);
		}
		if(!Ext.isEmpty(Ext.getCmp('suppliers4B01_1').getValue())){
			var b={
					columnId:'a.supplier_no',
					value:Ext.getCmp('suppliers4B01_1').getValue()
				};
			detail1.push(b);
		}
		if(!Ext.isEmpty(Ext.getCmp('status4B01_1').getValue())){
			var c={
					columnId:'a.status',
					value:Ext.getCmp('status4B01_1').getValue()
				};
			detail1.push(c);
		}
		var wheresql = {
			strAppointDate : appointDate,
			str:Ext.encode(detail1)
		};
		Ext.getCmp("orderSerial4B01").setValue('');
		Ext.apply(Ext.getCmp('grid4B01_1')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('grid4B01_1').getStore()
				.removeAll();
		Ext.getCmp('grid4B01_1').getStore()
				.load();
		if(Ext.getCmp('status4B01_1').getValue()=='10'){
			disableButtonFunc(0,'#regist4B01',$i18n.registe);
			disableButtonFunc(1,'#edit4B01',$i18n.titleupdate);
		}else if(Ext.getCmp('status4B01_1').getValue()=='13'){
			disableButtonFunc(1,'#regist4B01',$i18n.registe);
			disableButtonFunc(1,'#edit4B01',$i18n.titleupdate);
		}else{
			disableButtonFunc(1,'#regist4B01',$i18n.registe);
			disableButtonFunc(0,'#edit4B01',$i18n.titleupdate);
		}
	},
	//预约状况信息列表数据选择
	grid4B01_1Selectionchange:function(th,selected,eOpts){
		var objdata = Ext.getCmp('grid4B01_1').getSelectionModel().getSelection();
		var detail=[];
		if(objdata.length!=0){
			for(var i=0; i<objdata.length; i++){
				detail.push(objdata[i].get('orderSerial'));	
			}
		}
		var wheresql = { str : detail};	
		Ext.apply(Ext.getCmp('grid4B01_2').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid4B01_2').getStore().removeAll();
		Ext.getCmp('grid4B01_2').getStore().load();	
	},
	//报到状况信息列表数据选择
	grid4B01_2Selectionchange:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var detail1 = [];
			detail1.push(record.data.orderSerial);
			var wheresql = {
				str : detail1
			};
			
			Ext.apply(Ext.getCmp('grid4B01_3').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid4B01_3').getStore().removeAll();
			Ext.getCmp('grid4B01_3').getStore().load();
			
		}else{
			Ext.getCmp('grid4B01_3').getStore().removeAll();
		}
	},
	//报到
	registClick:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		if(typeof(Ext.getCmp('idata_GuardRegisteAddOrEditWindow'))=='undefined'){
			Ext.create('cms.view.idata.windows.idata_GuardRegisteAddOrEditWindow',{
				title:'报到'
			}).show();
			addCommMenu5('menuWidget1601');
			saveType4B01='0';
			Ext.getCmp('registeDate4B01').setValue('保存时自动生成');
			Ext.getCmp('status4B01_2').setValue('10');
			Ext.getCmp('guestCardNumber4B01').setValue(0);
			Ext.getCmp('leaveDate4B01').setVisible(false);
			var objdata = Ext.getCmp('grid4B01_1').getSelectionModel().getSelection();
			var detail=[];
			if(objdata.length!=0){
				for(var i=0; i<objdata.length; i++){
					detail.push(objdata[i].get('orderSerial'));	
					if(i==0){
						Ext.getCmp('cmbSuppliers4B01').setValue(objdata[i].get('suppliersText'));

					}
				}
			}
			var wheresql = { str : detail};	
			Ext.apply(Ext.getCmp('grid4B01_4').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid4B01_4').getStore().removeAll();
			Ext.getCmp('grid4B01_4').getStore().load();	

			commonSetMsterReadOnlyByArray(
					new Array('cmbSuppliers4B01','registeDate4B01','dockNo4B01_2','status4B01_2','leaveDate4B01'),true);
			Ext.getCmp('carNo4B01').focus(false, 3);

		}
	},
	//修改
	edit4B01Click:function(){
		if(typeof(Ext.getCmp('idata_GuardRegisteAddOrEditWindow'))=='undefined'){
			Ext.create('cms.view.idata.windows.idata_GuardRegisteAddOrEditWindow',{
				title:'修改'
			}).show();
			addCommMenu5('menuWidget1601');
			Ext.getCmp('leaveDate4B01').setVisible(true);
			Ext.getCmp('leaveDate4B01').setValue('离开状态保存时自动生成');
			saveType4B01='1';
			var rec = Ext.getCmp('grid4B01_1').getSelectionModel().getSelection();
			if(rec.length!=0){
				flag='1';
				serial_no=rec[0].get('orderSerial');
				owner_no=rec[0].get('ownerNo');
				Ext.getCmp('cmbSuppliers4B01').setValue(rec[0].get('suppliersText'));
				var data = Ext.getCmp('grid4B01_2').getSelectionModel().getSelection();
				if(data.length!=0){
					Ext.getCmp('registeDate4B01').setValue(data[0].get('registeDateText'));
					Ext.getCmp('carNo4B01').setValue(data[0].get('carNo'));
					Ext.getCmp('driverNo4B01').setValue(data[0].get('driverNo'));
					Ext.getCmp('driverLicevseNo4B01').setValue(data[0].get('driverLicenseNo'));
					Ext.getCmp('guestCardNumber4B01').setValue(data[0].get('guestCardNumber'));
//					Ext.getCmp('dockNo4B01_2').setValue(data[0].get('dockText'));
					Ext.getCmp('beginUnloadDate4B01').setValue(data[0].get('beginUnload'));
					Ext.getCmp('endUnloadDate4B01').setValue(data[0].get('endUnloadDateText'));
					Ext.getCmp('status4B01_2').setValue(data[0].get('status'));

					var objdata = Ext.getCmp('grid4B01_1').getSelectionModel().getSelection();
					var detail=[];
					if(objdata.length!=0){
						for(var i=0; i<objdata.length; i++){
							detail.push(objdata[i].get('orderSerial'));	
							if(i==0){
								Ext.getCmp('cmbSuppliers4B01').setValue(objdata[i].get('suppliersText'));

							}
						}
					}
					var wheresql = { str : detail};	
					Ext.apply(Ext.getCmp('grid4B01_4').getStore().proxy.extraParams,wheresql);
					Ext.getCmp('grid4B01_4').getStore().removeAll();
					Ext.getCmp('grid4B01_4').getStore().load();	
				}
			}
			
			commonSetMsterReadOnlyByArray(
					new Array('cmbSuppliers4B01','registeDate4B01','dockNo4B01_2','leaveDate4B01'),true);

		}
	},
	refresh4B01Click:function(){
		Ext.getCmp('appoint_date4B01').setValue(new Date);
		Ext.getCmp('appoint_date4B01').fireEvent('change','');
		
		Ext.getCmp('suppliers4B01_1').setValue('');
	    Ext.getCmp('suppliers4B01_1').getStore().removeAll();
	    Ext.getCmp('suppliers4B01_1').getStore().reload();
	    
	    Ext.getCmp('status4B01_1').setValue('');
	    Ext.getCmp('status4B01_1').getStore().removeAll();
	    Ext.getCmp('status4B01_1').getStore().reload();
	    
		Ext.getCmp('appoint_date4B01').fireEvent('change','');
			
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('idata_GuardRegisteAddOrEditWindow').close();
	},
	save:function(){
		if(!commonCheckIsInputAll('formRodata_OutstockM7501'))
		{
			return;
		}
		var gridcount=Ext.getCmp('grid4B01_4').getStore().getCount();
    	if(gridcount<=0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
    		return;
    	}
    	
		var carNo=Ext.getCmp('carNo4B01').getValue();
		var driverNo=Ext.getCmp('driverNo4B01').getValue();
		var driverLicenseNo=Ext.getCmp('driverLicevseNo4B01').getValue();
		var guestCardNumber=Ext.getCmp('guestCardNumber4B01').getValue();
//		var beginUnloadDate=Ext.util.Format.date(Ext.getCmp('beginUnloadDate4B01').getValue(), 'Y-m-d');
//		var endUnloadDate=Ext.util.Format.date(Ext.getCmp('endUnloadDate4B01').getValue(), 'Y-m-d');
		var status=Ext.getCmp('status4B01_2').getValue();		
				
		var objdata=Ext.getCmp('grid4B01_1').getSelectionModel().getSelection();
		if(objdata.length!=0){
			var detail=[];
			
			if(saveType4B01=='1'){
				var updtName=Ext.get('workerNo').getValue();
				var updtDate=new Date();
				if(status=='13'){
				    var leaveDate=updtDate;
				}else{
				    var leaveDate='';
				}
				var gridcount2=Ext.getCmp('grid4B01_2').getStore().getCount();
                if(gridcount2>0){
                	for(var l=0;l<gridcount2;l++){
                		
                		var rec = Ext.getCmp('grid4B01_2').getStore().getAt(l);
                		var rgstName=rec.get('rgstName');
    				    var rgstDate=rec.get('rgstDate');
    				    var master={
    			    			id:{
    			    				enterpriseNo:Ext.get('enterpriseNo').getValue(),
    			    				warehouseNo:Ext.get('warehouseNo').getValue(),
    			    				ownerNo:rec.get('ownerNo'),
    			    				orderSerial:rec.get('orderSerial'),
    			    				supplierNo:rec.get('supplierNo')
    			    			},
    			    			dockNo:rec.get('dockNo'),
    			    			registeDate:rgstDate,
    			    			carNo:carNo,
    			    			driverNo:driverNo,
    			    			driverLicenseNo:driverLicenseNo,
    			    			guestCardNumber:guestCardNumber,
//    			    			beginUnloadDate:beginUnloadDate,
//    			    			endUnloadDate:endUnloadDate,
    			    			leaveDate:leaveDate,
    			    			status:status,
    			    			rgstName:rgstName,
    			    			rgstDate:rgstDate,
    			    			updtName:updtName,
    			    			updtDate:updtDate
    			    		};
    					
    				    if(l==gridcount2-1){
            				for(var k=0;k<gridcount;k++){
            					var record=Ext.getCmp('grid4B01_4').getStore().getAt(k);
            					var d={
            							id:{
            								importNo:record.get('importNo')
            			    			}
            							
            						};
            					detail.push(d);
            				}
            			}
    					var imPortM=Ext.encode(master);
    					var imPortD=Ext.encode(detail);
    					
    					var params={
    							jsonMaster:imPortM,
    							jsonDetail:imPortD,
    							strSaveType:saveType4B01
    						};
    						Ext.Ajax.request({
    							method:'post',
    							url:'idata_GuardRegisteAction_save',
    							params:params,
    							async:false,
    							success:function(response){
    								var data = Ext.decode(response.responseText);
    								if(data.isSucc){
    									if(l==gridcount2-1){
    										Ext.example.msg($i18n.prompt,'数据保存成功！');
    										suppliers4B01_1Select();
//    										if(typeof(Ext.getCmp('grid4B01_1'))!=='undefined'){
//    											Ext.getCmp('grid4B01_1').store.reload();
//    										}
    										Ext.getCmp('idata_GuardRegisteAddOrEditWindow').close();
    		
    									}
    									
    								}else{
    									Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
    								}
    							}
    						});
    				
                	}
                }
				
			}else if(saveType4B01=='0'){
				for(var i=0; i<objdata.length; i++){
					var master={
			    			id:{
			    				enterpriseNo:Ext.get('enterpriseNo').getValue(),
			    				warehouseNo:Ext.get('warehouseNo').getValue(),
			    				ownerNo:objdata[i].get('ownerNo'),
			    				orderSerial:objdata[i].get('orderSerial'),
			    				supplierNo:objdata[i].get('supplierNo')
			    			},
			    			dockNo:objdata[i].get('dockNo'),
			    			registeDate:new Date(),
			    			carNo:carNo,
			    			driverNo:driverNo,
			    			driverLicenseNo:driverLicenseNo,
			    			guestCardNumber:guestCardNumber,
//			    			beginUnloadDate:beginUnloadDate,
//			    			endUnloadDate:endUnloadDate,
			    			leaveDate:leaveDate,
			    			status:status,
			    			rgstName:Ext.get('workerNo').getValue(),
			    			rgstDate:new Date(),
			    			updtName:'',
			    			updtDate:''
			    		};
					if(i==objdata.length-1){
        				for(var m=0;m<gridcount;m++){
        					var record=Ext.getCmp('grid4B01_4').getStore().getAt(m);
        					var d={
        							id:{
        								orderSerial:record.get('orderSerial'),
        								importNo:record.get('importNo')
        			    			}
        							
        						};
        					detail.push(d);
        				}
        			}
					
					var imPortM=Ext.encode(master);
					var imPortD=Ext.encode(detail);
					
					var params={
							jsonMaster:imPortM,
							jsonDetail:imPortD,
							strSaveType:saveType4B01
						};
						Ext.Ajax.request({
							method:'post',
							url:'idata_GuardRegisteAction_save',
							params:params,
							async:false,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									if(i==objdata.length-1){
										Ext.example.msg($i18n.prompt,'数据保存成功！');
										suppliers4B01_1Select();
//										if(typeof(Ext.getCmp('grid4B01_1'))!=='undefined'){
//											Ext.getCmp('grid4B01_1').store.reload();
//										}
										Ext.getCmp('idata_GuardRegisteAddOrEditWindow').close();
		
									}
									
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
								}
							}
						});
				}
				
			}
			
		}
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
		   th.nextSibling().focus();
        }
	},
	 getFlag:function(){
  		 return flag;
  	 },
  	 
  	 setFlag:function(value){
  		 flag =value;
  	 },
	 getSerial_no:function(){
  		 return  serial_no;
  	 },
  	 
  	 getOwner_no:function(){
  		return owner_no;
  	 }
});
function select4B01(){
	if(!Ext.isDate(Ext.getCmp('appoint_date4B01').getValue())){
		return;
	}
	var appointDate=Ext.Date.format( Ext.getCmp('appoint_date4B01').getValue(),'Y-m-d');
	var detail1 = [];
	var detail2 = [];
	var detail3 = [];
	if(!Ext.isEmpty(Ext.getCmp('owner4B01').getValue())){
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('owner4B01').getValue()
			};
		detail1.push(a);
		detail2.push(a);
		detail3.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('suppliers4B01_1').getValue())){
		var b={
				columnId:'a.supplier_no',
				value:Ext.getCmp('suppliers4B01_1').getValue()
			};
		detail1.push(b);
		detail3.push(b);
	}
	if(!Ext.isEmpty(Ext.getCmp('status4B01_1').getValue())){
		var c={
				columnId:'a.status',
				value:Ext.getCmp('status4B01_1').getValue()
			};
		detail1.push(c);
	}
	var wheresql = {
		strAppointDate : appointDate,
		str:Ext.encode(detail1)
	};
	var wheresql2 = {
			strAppointDate : appointDate,
			str:Ext.encode(detail2)
		};
	var wheresql3 = {
			strAppointDate : appointDate,
			str:Ext.encode(detail3)
		};
	
	if(!Ext.isEmpty(Ext.getCmp('owner4B01').getValue())){
		Ext.apply(Ext.getCmp('suppliers4B01_1')
				.getStore().proxy.extraParams,
				wheresql2);
		Ext.getCmp('suppliers4B01_1').getStore()
				.removeAll();
		Ext.getCmp('suppliers4B01_1').getStore()
				.load();
	}
	if(!Ext.isEmpty(Ext.getCmp('suppliers4B01_1').getValue())){
		Ext.apply(Ext.getCmp('status4B01_1')
				.getStore().proxy.extraParams,
				wheresql3);
		Ext.getCmp('status4B01_1').getStore()
				.removeAll();
		Ext.getCmp('status4B01_1').getStore()
				.load();
	}
	Ext.getCmp("orderSerial4B01").setValue('');
	Ext.apply(Ext.getCmp('grid4B01_1')
			.getStore().proxy.extraParams,
			wheresql);
	Ext.getCmp('grid4B01_1').getStore()
			.removeAll();
	Ext.getCmp('grid4B01_1').getStore()
			.load();
	
};
function suppliers4B01_1Select(){
	if(!Ext.isDate(Ext.getCmp('appoint_date4B01').getValue())){
		return;
	}
	var appointDate=Ext.Date.format( Ext.getCmp('appoint_date4B01').getValue(),'Y-m-d');
	var detail1 = [];
	if(!Ext.isEmpty(Ext.getCmp('owner4B01').getValue())){
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('owner4B01').getValue()
			};
		detail1.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('suppliers4B01_1').getValue())){
		var b={
				columnId:'a.supplier_no',
				value:Ext.getCmp('suppliers4B01_1').getValue()
			};
		detail1.push(b);
	}
	
	var wheresql = {
		strAppointDate : appointDate,
		str:Ext.encode(detail1)
	};
	Ext.apply(Ext.getCmp('status4B01_1')
			.getStore().proxy.extraParams,
			wheresql);
	Ext.getCmp('status4B01_1').getStore()
			.removeAll();
	Ext.getCmp('status4B01_1').getStore()
			.load();
}