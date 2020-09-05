/**
 * 模块名称：电商自动出货调度
 * 模块代码：3201
 * @author hkl
 */
var g_cbLocateByOrder3201;
var AllSelectedRecords = [];
var flag3201=1;
var flagSelAll = true;
var flagUnSelAll = true;

Ext.define('cms.controller.odata.odata_B2CLocate_autoController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_B2CLocate_autoUI'
	],
	init:function(){
		this.control({//刷新		
			'odata_B2CLocate_autoUI button[name=refresh]':{
				click:this.menu3201RefreshClick
			},//点击查询
			'odata_B2CLocate_autoUI button[id=btnQuery3201]':{
				click:this.btnQuery3201Click
			},//调度主档网格编辑
			'odata_B2CLocate_autoUI grid[id=grid_01_3201]':
			{
				select:this.grid_01_3201Select,//选中事件
				deselect:this.grid_01_3201Deselect,//取消选中事件
				selectionchange:this.grid_01_3201SelectionChange
			},//只显示缺量品项
			'odata_B2CLocate_autoUI checkboxfield[id=cbShowNoEnoughtItem3201]':{
				change:this.cbShowNoEnoughtItem3201Change
			},//显示明细
			'odata_B2CLocate_autoUI checkboxfield[id=cbShowDetail3201]':{
				change:this.cbShowDetail3201Change
			},//试算月台按钮
			'odata_B2CLocate_autoUI checkboxfield[id=calculation3201]':{
				change:this.calculation3201Change
			},//重算月台资源
			'odata_B2CLocate_autoUI button[id=btnBackroll3201]':
			{
				click:this.btnBackroll3201Click
			},//定位失败查询-波次号选择
			'odata_B2CLocate_autoUI combo[id=locateNoUI3201]':{
				change:this.locateNoUI3201Select
			},//定位失败查询-商品编码选择
			'remoteCombo[id=articleNoUI3201]':{
				beforequery:this.articleNoUI3201BeforeQuery,
				select:this.articleNoSelect
			},//加载承运商 
			'odata_B2CLocate_autoUI form textfield[id=sanpl3201]':{
				beforequery:this.sanplBeforeQuery,
				select:this.sanplSelect
			},//加载客户
			'odata_B2CLocate_autoUI form textfield[id=cust3201]':{
				beforequery:this.custBeforeQuery
			},//加载商品编码
			'odata_B2CLocate_autoUI [id=articleNo3201]':{
				beforequery:this.articleNoBeforeQuery
			},//续调
			'odata_B2CLocate_autoUI button[id=but_locateContinue_3201]':{
				click:this.but_locateContinue_3201click
			},//定位
			'odata_B2CLocate_autoUI button[id=btnFixed3201]':
			{
				click:this.btnFixed3201Click
			},//tab页切换
			'odata_B2CLocate_autoUI tabpanel[id=tabPid3201]':{
				tabchange:this.tabPid3201tabchange
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		Ext.getCmp('cmbOwnerNo3201').getStore().load();
		Ext.getCmp('orgNo3201').getStore().load();
		Ext.getCmp('cmbExpType3201').getStore().load();
		Ext.getCmp('cmbDeliverType3201').getStore().load();
		
	},
	
	menu3201RefreshClick:function(){
		Ext.getCmp('sanpl3201').setValue('');
		Ext.getCmp('cust3201').setValue('');
		Ext.getCmp('rgstDate3201').setValue('');
		Ext.getCmp('rgstDate3201_1').setValue('');
		Ext.getCmp('cust_date3201').setValue('');
		Ext.getCmp('cust_date3201_1').setValue('');
		
		Ext.getCmp('articleNo3201').setValue('');
		Ext.getCmp('linkUI3201').setValue('');
		Ext.getCmp('linkUI3201_1').setValue('');
		Ext.getCmp('fastFlag3201').setValue(false);
		//Ext.getCmp('cbLocateByOrder3201').setValue(false);
		//8-5添加
		Ext.getCmp('cmbOwnerNo3201').getStore().load();
		Ext.getCmp('orgNo3201').getStore().load();
		Ext.getCmp('cmbExpType3201').getStore().load();
		Ext.getCmp('cmbDeliverType3201').getStore().load();
	},
	
	btnQuery3201Click:function(){
		
		//写临时表数据
		
		if(!commonCheckIsInputAll('form_01_3201'))
		{
			return;
		}
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3201').getValue())&&
				Ext.getCmp('cmbOwnerNo3201').getValue()!='ALL')
		{
			var d={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbOwnerNo3201').getValue()
				};
				strDetail1.push(d);
		}
		var d={
			columnId:'a.deliver_type',
			value:Ext.getCmp('cmbDeliverType3201').getValue()
		};
		strDetail1.push(d);
		
		d={
			columnId:'a.fast_flag',
			value:Ext.getCmp('fastFlag3201').getValue()==true ? 1:0
		};
		strDetail1.push(d);

		d={
			columnId:'a.exp_type',
			value:Ext.getCmp('cmbExpType3201').getValue()
		};
		strDetail1.push(d);
		d={
			columnId:'a.org_no',
			value:Ext.getCmp('orgNo3201').getValue()
		};
		strDetail1.push(d);
	    //////////////////////////乘运商//////////////////////////
		if(Ext.getCmp('sanpl3201').getValue()!=null && Ext.getCmp('cust3201').getValue()!=""){
			d={
				columnId:'a.shipper_no',
				value:Ext.getCmp('sanpl3201').getValue()
			};
			strDetail1.push(d);
		}
		//////////////////////////订单类型//////////////////////////
		/*var strOrdertype=null;
		if(!Ext.isEmpty(Ext.getCmp('orderType3201').getValue())){
			strOrdertype=Ext.getCmp('orderType3201').getValue();
		}*/
		//////////////////////////客户//////////////////////////
		if(Ext.getCmp('cust3201').getValue()!=null && Ext.getCmp('cust3201').getValue()!=""){
			d={
				columnId:'a.cust_no',
				value:Ext.getCmp('cust3201').getValue()
			};
			strDetail1.push(d);
		}
		
		//////////////////////////线路//////////////////////////
		if(Ext.getCmp('linkUI3201').getValue()!=null && Ext.getCmp('linkUI3201').getValue()!=""){
			d={
				columnId:'a.line_no',
				value:Ext.getCmp('linkUI3201').getValue(),
				condition:4
			};
			strDetail1.push(d);
		}		
		if(Ext.getCmp('linkUI3201_1').getValue()!=null && Ext.getCmp('linkUI3201_1').getValue()!=""){
			d={
				columnId:'a.line_no',
				value:Ext.getCmp('linkUI3201_1').getValue(),
				condition:5
			};
			strDetail1.push(d);
		}
		
		/////////////////接单时间和结束时间//////////////////////////
		if(Ext.getCmp('rgstDate3201').getValue()!=null && Ext.getCmp('rgstDate3201').getValue()!=""){
			d={
				columnId:'a.rgst_date',
				value:Ext.Date.format(Ext.getCmp('rgstDate3201').getValue(),'Y/m/d')	,
				condition:4,
				valueType:3
			};
			strDetail1.push(d);
		}	
		
		if(Ext.getCmp('rgstDate3201_1').getValue()!=null && Ext.getCmp('rgstDate3201_1').getValue()!=""){
			d={
				columnId:'a.rgst_date',
				value:Ext.Date.format(Ext.getCmp('rgstDate3201_1').getValue(),'Y/m/d')	,
				condition:5,
				valueType:3
			};
			strDetail1.push(d);
		}	
		
		/////////////////出货确认时间（数据库叫要求送货时间）//////////////////////////
		if(Ext.getCmp('cust_date3201').getValue()!=null && Ext.getCmp('cust_date3201').getValue()!=""){
			d={
				columnId:'a.custsend_date',
				value:Ext.Date.format(Ext.getCmp('cust_date3201').getValue(),'Y/m/d')	,
				condition:4,
				valueType:3
			};
			strDetail1.push(d);
		}	
		
		if(Ext.getCmp('cust_date3201_1').getValue()!=null && Ext.getCmp('cust_date3201_1').getValue()!=""){
			d={
				columnId:'a.custsend_date',
				value:Ext.Date.format(Ext.getCmp('cust_date3201_1').getValue(),'Y/m/d')	,
				condition:5,
				valueType:3
			};
			strDetail1.push(d);
		}	

		//商品编码
		if(Ext.getCmp('articleNo3201').getValue()!=null && Ext.getCmp('articleNo3201').getValue()!=""){
			d={
				columnId:'b.article_no',
				value:Ext.getCmp('articleNo3201').getValue()
			};
			strDetail1.push(d);
		}
		
		g_cbLocateByOrder3201=Ext.getCmp('cbLocateByOrder3201').getValue()==true ? 1:0;
		
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strFlag:g_cbLocateByOrder3201,
			strWheresql : jsonDetail,
			B2Cflag:2
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			params:strWheresql,
			url:'odata_LocateAutoAction_saveLocateSelectTem.action',
			success:function(response)
			{
				
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('tabPid3201').items.items[1].setVisible(true);
					
					if(Ext.getCmp('cbLocateByOrder3201').getValue()==true)
					{
						Ext.getCmp('colSourceExpNo3201').setVisible(true);
						Ext.getCmp('colExpNo3201').setVisible(true);
					}else
					{
						Ext.getCmp('colSourceExpNo3201').setVisible(false);
						Ext.getCmp('colExpNo3201').setVisible(false);
					}
					Ext.apply(Ext.getCmp('grid_01_3201').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('grid_01_3201').getStore().removeAll();
					Ext.getCmp('grid_01_3201').getStore().load();
					
					Ext.getCmp('lblcustNumber3201').setText('0');
				    Ext.getCmp('lblSumVolumn3201').setText('0');
				    Ext.getCmp('lblSumWeight3201').setText('0');
				    Ext.getCmp('lblSumBoxQty3201').setText('0');
					
					
					
				}else{
					Ext.Msg.alert($i18n.prompt,'保存临时表失败！');
					//Ext.example.msg('提示','保存临时表失败！');

				}
				
			}
		});
	},
	grid_01_3201Select:function(row,record,index,eOpts){
		AllSelectedRecords.push(record);
		var expNo='N';
		var custNo='N';
		if(row.selectionMode!='MULTI'){
			expNo=record.data.expNo;
			custNo=record.data.custNo;
		}
		var params = 
		{			
			expNo:expNo,
			custNo:custNo,
			strFlag:g_cbLocateByOrder3201,
			calFlag:Ext.getCmp('calculation3201').getValue()==true ? 1:0,
			expType:Ext.getCmp('cmbExpType3201').getValue(),
			ownerNo:record.data.ownerNo
		};		
		
		if(row.selectionMode=='MULTI'){
			if (index==0 || flagSelAll == false){
				updateTmpLocateSelect1(params);
			}
			flagSelAll = true;
			return;
		}
		updateTmpLocateSelect1(params);
		flagUnSelAll = false;
		    	    
	},
	
	grid_01_3201Deselect:function(row,record,index,eOpts){
		
			var expNo='N';
			var custNo='N';
			if(row.selectionMode!='MULTI'){//全选
				expNo=record.data.expNo;
				custNo=record.data.custNo;
			}
			var params = 
			{			
				expNo:expNo,
				custNo:custNo,
				strFlag:g_cbLocateByOrder3201,
				calFlag:Ext.getCmp('calculation3201').getValue()==true ? 1:0,
				expType:Ext.getCmp('cmbExpType3201').getValue(),
				ownerNo:record.data.ownerNo
			};
			if(row.selectionMode=='MULTI'){//全选
				if (index==0 || flagUnSelAll == false){
					updateTmpLocateSelect0(params);
				}
				flagUnSelAll = true;
				return;
			}
			updateTmpLocateSelect0(params);
			flagSelAll = false;
		
	},
	
	grid_01_3201SelectionChange:function(th,selected,eOpts)
	{
		//监听全选事件
//		 var hd_checker = Ext.getCmp("grid_01_3201").getEl().select('div.x-column-header-checkbox');    
//	     var hd = hd_checker.first();   
//	     if(hd != null){   
//	    	 if(hd.hasCls(Ext.baseCSSPrefix + 'grid-hd-checker-on')){
//	    	      // hd.removeClass('x-grid3-hd-checker-on');
//		    	  //点击全选，修改所有临时表的数据
//	    		 //alert("aaa");
//    			var params = 
//    			{
//    					strWheresql:1,
//    					strFlag:g_cbLocateByOrder3201,
//    					calFlag:Ext.getCmp('calculation3201').getValue()==true ? 1:0
//    			};
//    			Ext.Ajax.request({
//    				method:'POST',
//    				async:false,
//    				params:params,
//    				url:'odata_LocateAutoAction_updateTmpLocateSelectAll',
//    				success:function(response)
//    				{
//    					data = Ext.decode(response.responseText);
//    					if(data.isSucc){
//    						Ext.getCmp('calculateQty3201').setText(data.obj);
//    						//Ext.getCmp('grid_01_3201').getStore().load();
//    					}else{
//    						Ext.example.msg('提示',data.msg);
//    					}
//    				
//    					
//    				}
//    			}); 
//		    	     
//		    	}
//	    }
//	   
	     
		var data = Ext.getCmp('grid_01_3201').getSelectionModel().getSelection();
	    if(data.length == 0){
			//去除全部勾选，修改临时表数据
			var params = 
			{
					strWheresql:0,
					strFlag:g_cbLocateByOrder3201,
					calFlag:Ext.getCmp('calculation3201').getValue()==true ? 1:0
			};
			Ext.Ajax.request({
				method:'POST',
				async:false,
				params:params,
				url:'odata_LocateAutoAction_updateTmpLocateSelectAll',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('calculateQty3201').setText(data.obj);
						//Ext.getCmp('grid_01_3201').getStore().load();
					}else{
						Ext.Msg.alert($i18n.prompt,data.msg);
						//Ext.example.msg('提示',data.msg);
					}
				
					
				}
			}); 
			Ext.getCmp('grid_02_3201').getStore().removeAll();
			Ext.getCmp('lblcustNumber3201').setText('0');
	    }else
	    {
	    	Ext.Ajax.request({
				method:'POST',
				async:false,
				url:'odata_LocateAutoAction_countDetail.action',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					Ext.getCmp('lblcustNumber3201').setText(data[0].custCount);
			        Ext.getCmp('lblSumVolumn3201').setText(data[0].sumVolumn);
			        Ext.getCmp('lblSumWeight3201').setText(data[0].sumWeight);
			        Ext.getCmp('lblSumBoxQty3201').setText(data[0].sumBoxQty);
				}
			});
    	 
			var strWheresql = {
				//strFlag  : 3201,
				strIsNotEnought:Ext.getCmp('cbShowNoEnoughtItem3201').getValue()==true ? 1:0
			};
			if(Ext.getCmp('cbShowDetail3201').getValue()==true || Ext.getCmp('cbShowNoEnoughtItem3201').getValue()==true)
			{
				Ext.apply(Ext.getCmp('grid_02_3201').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('grid_02_3201').getStore().removeAll();
				Ext.getCmp('grid_02_3201').getStore().load();
			}else
			{
				Ext.getCmp('grid_02_3201').getStore().removeAll();
			}
	    }
	},
	
	cbShowNoEnoughtItem3201Change:function(cb)
	{
		if(cb.value==true)
		{
			Ext.getCmp('cbShowDetail3201').setValue(false);
		}
		//如果有选择定位客户，则加载明细
		if(Ext.getCmp('grid_01_3201').getSelectionModel().getSelection().length>0)
		{
			var strWheresql = {
				//	strFlag  : '3201',
					strIsNotEnought:Ext.getCmp('cbShowNoEnoughtItem3201').getValue()==true ? 1:0
			};
			if(Ext.getCmp('cbShowDetail3201').getValue()==true || Ext.getCmp('cbShowNoEnoughtItem3201').getValue()==true)
			{
				Ext.apply(Ext.getCmp('grid_02_3201').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('grid_02_3201').getStore().removeAll();
				Ext.getCmp('grid_02_3201').getStore().load();
			}else
			{
				Ext.getCmp('grid_02_3201').getStore().removeAll();
			}
		}
	},
	
	
	cbShowDetail3201Change:function(cb)
	{
		if(cb.value==true)
		{
			Ext.getCmp('cbShowNoEnoughtItem3201').setValue(false);
		}
		//如果有选择定位客户，则加载明细
		if(Ext.getCmp('grid_01_3201').getSelectionModel().getSelection().length>0)
		{
			var strWheresql = {
			//	strFlag  : '3201',
				strIsNotEnought:Ext.getCmp('cbShowNoEnoughtItem3201').getValue()==true ? 1:0
			};
			if(Ext.getCmp('cbShowDetail3201').getValue()==true || Ext.getCmp('cbShowNoEnoughtItem3201').getValue()==true)
			{
				Ext.apply(Ext.getCmp('grid_02_3201').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('grid_02_3201').getStore().removeAll();
				Ext.getCmp('grid_02_3201').getStore().load();
			}else
			{
				Ext.getCmp('grid_02_3201').getStore().removeAll();
			}
		}
	},
	calculation3201Change:function(){
		if(Ext.getCmp('grid_01_3201').getSelectionModel().getSelection().length==0){
			return;
		}else{
		var params = 
		{
			expType:Ext.getCmp('cmbExpType3201').getValue(),
			calFlag:Ext.getCmp('calculation3201').getValue()==true ? 1:0,
			strOwnerNo:Ext.getCmp('cmbOwnerNo3201').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			params:params,
			url:'odata_LocateAction_changeCalculation.action',
			success:function(response)
			{
				data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('calculateQty3201').setText(data.obj);
				}else{
					Ext.Msg.alert($i18n.prompt,data.msg);
					//Ext.example.msg('提示',data.msg);
				}
			
				
			}
		});
		}
		
	},
	
	btnBackroll3201Click:function(){
		Ext.Msg.confirm('提示','确定重算月台资源？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					method:'post',
					url:'odata_LocateAction_tscBackrollres',
					params:{
						strOwnerNo:Ext.getCmp('cmbOwnerNo3201').getValue(),
						strExpType:Ext.getCmp('cmbExpType3201').getValue()
				    },
				    success:function(response){
				    	var data = Ext.decode(response.responseText);
				    	if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
							 Ext.getCmp('calculateQty3201').setText(data.obj);
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg);
							//Ext.example.msg($i18n.prompt,data.msg);
						}
				    }
				});
			}
		});		

	},
	//定位失败查询-波次号选择
	locateNoUI3201Select:function(){
		//获取商品编码下拉
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('locateNoUI3201').getValue())){
			var d1={
					columnId:'a.wave_no',
					value:Ext.getCmp('locateNoUI3201').getValue()
				};
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		Ext.getCmp("articleNoUI3201").setValue('');
		Ext.apply(Ext.getCmp('articleNoUI3201').getStore().proxy.extraParams,str);
		Ext.getCmp('articleNoUI3201').getStore().removeAll();
		Ext.getCmp('articleNoUI3201').getStore().load();
		
		//失败原因列表
		Ext.apply(Ext.getCmp('grid_04_3201').getStore().proxy.extraParams,str);
		Ext.getCmp('grid_04_3201').getStore().removeAll();
		Ext.getCmp('grid_04_3201').getStore().load();		
	},
	 //定位失败查询-商品编码加载 
	articleNoUI3201BeforeQuery:function(){
 		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('locateNoUI3201').getValue())){
			var d1={
					columnId:'a.wave_no',
					value:Ext.getCmp('locateNoUI3201').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				str:Ext.getCmp("articleNoUI3201").getValue(),   
				strQuery:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('articleNoUI3201').getStore().proxy.extraParams,params);
		Ext.getCmp('articleNoUI3201').getStore().removeAll();
		Ext.getCmp('articleNoUI3201').getStore().load();
  	  },
  	//商品编码选择  
  	articleNoSelect:function(){
  		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('locateNoUI3201').getValue())){
			var d1={
					columnId:'a.wave_no',
					value:Ext.getCmp('locateNoUI3201').getValue()
				};
			listDetail1.push(d1);
		}
 		if(!Ext.isEmpty(Ext.getCmp('articleNoUI3201').getValue())){
			var d1={
					columnId:'a.article_no',
					value:Ext.getCmp('articleNoUI3201').getValue()
				};
			listDetail1.push(d1);
		}
		var params={
				strQuery:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('grid_04_3201').getStore().proxy.extraParams,params);
		Ext.getCmp('grid_04_3201').getStore().removeAll();
		Ext.getCmp('grid_04_3201').getStore().load();
  	 },
  	 
  	sanplBeforeQuery:function(){
  		var listDetail1  =  [];	
  		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3201').getValue())&&
				Ext.getCmp('cmbOwnerNo3201').getValue()!='ALL')
		{
  			var strDtl = {
  		  			columnId:'a.owner_no',
  		  			value:Ext.getCmp("cmbOwnerNo3201").getValue()
  		  		};
  		  		listDetail1.push(strDtl);
		}
  							
  		var strWheresql={
  			str:Ext.getCmp("sanpl3201").getValue(),
  			strQuery:Ext.encode(listDetail1)
  		};		
  		Ext.apply(Ext.getCmp('sanpl3201').getStore().proxy.extraParams,strWheresql);
  		Ext.getCmp('sanpl3201').getStore().removeAll();
  		Ext.getCmp('sanpl3201').getStore().load();
  	},
  //承运商选择
	sanplSelect:function(){
		var listDetail1  =  [];	
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3201').getValue())&&
				Ext.getCmp('cmbOwnerNo3201').getValue()!='ALL')
		{
  			var strDtl = {
  		  			columnId:'a.owner_no',
  		  			value:Ext.getCmp("cmbOwnerNo3201").getValue()
  		  		};
  		  		listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('sanpl3201').getValue())){
			var d1={
					columnId:'a.shipper_no',
					value:Ext.getCmp('sanpl3201').getValue()
				};
			listDetail1.push(d1);
		}					
		var strWheresql={
			strQuery:Ext.encode(listDetail1)
		};
		Ext.getCmp('cust3201').setValue('');
		Ext.apply(Ext.getCmp('cust3201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cust3201').getStore().removeAll();
		Ext.getCmp('cust3201').getStore().load();
	},
	
	custBeforeQuery:function(){		
		var listDetail1  =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3201').getValue())&&
				Ext.getCmp('cmbOwnerNo3201').getValue()!='ALL')
		{
  			var strDtl = {
  		  			columnId:'a.owner_no',
  		  			value:Ext.getCmp("cmbOwnerNo3201").getValue()
  		  		};
  		  		listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('sanpl3201').getValue())){
			var d1={
					columnId:'a.shipper_no',
					value:Ext.getCmp('sanpl3201').getValue()
				};
			listDetail1.push(d1);
		}


		var strWheresql={
			strQuery:Ext.encode(listDetail1)
		};		
		Ext.apply(Ext.getCmp('cust3201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cust3201').getStore().removeAll();
		Ext.getCmp('cust3201').getStore().load();
	},
	
	
	
	//获取商品编码
	articleNoBeforeQuery:function(){
		var listDetail1  =  [];		
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3201').getValue())&&
				Ext.getCmp('cmbOwnerNo3201').getValue()!='ALL')
		{
  			var strDtl = {
  		  			columnId:'a.owner_no',
  		  			value:Ext.getCmp("cmbOwnerNo3201").getValue()
  		  		};
  		  		listDetail1.push(strDtl);
		}
		
		if(Ext.getCmp("sanpl3201").getValue()!=null && Ext.getCmp("cust3201").getValue()!=""){
			var strDtl = {
				columnId:'a.shipper_no',
				value:Ext.getCmp("sanpl3201").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("cust3201").getValue()!=null && Ext.getCmp("cust3201").getValue()!=""){
			var strDtl = {
				columnId:'a.cust_no',
				value:Ext.getCmp("cust3201").getValue()
			};
			listDetail1.push(strDtl);
		}
	
		var strWheresql={
			articleNo:Ext.getCmp("articleNo3201").getValue(),
			strQuery:Ext.encode(listDetail1)
		};		
		Ext.apply(Ext.getCmp('articleNo3201').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('articleNo3201').getStore().removeAll();
		Ext.getCmp('articleNo3201').getStore().load();
		
	},
	
	//续调
	but_locateContinue_3201click:function()
	{
		var record = Ext.getCmp('grid_03_3201').getSelectionModel().getSelection();
        if(record.length == 0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.checkTheDocument);
        }else{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.sureLocateAgain,
			function(button, text) {
			if (button == 'yes') {
		     
				var msgShow = commonMegShow($i18n_prompt.operating);
				Ext.Ajax.request({
					method:'POST',
					url:'odata_LocateAction_fixed.action',
					params:{
						strWheresql:record[0].get("warehouseNo")+','+record[0].get("waveNo")
					},
					success:function(response){
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('grid_03_3201').store.reload();
						}else{
							Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
							//Ext.example.msg($i18n.prompt,data.msg+data.obj);
						}				
					}
				});
			}
			});
        }
	},
	
	btnFixed3201Click:function()
	{
		if(Ext.getCmp('grid_01_3201').getStore().data.length==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_LocateAction_checkNoEnoght.action',
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					Ext.Msg.confirm($i18n.prompt,$i18n_prompt.lackArticle_SaveOrNot,function(button, text) 
					{
						if (button == 'yes') 
						{
							//删除临时表状态为0的数据
							Ext.Ajax.request({
								method:'POST',
								async:false,
								url:'odata_LocateAutoAction_deleteLocateSelect.action',
								success:function(response){
									var data = Ext.decode(response.responseText);
									if(data.isSucc){
										Fixed3201();
									}else{
										Ext.Msg.alert($i18n.prompt,'删除临时表失败！');
										//Ext.example.msg($i18n.prompt,'删除临时表失败！');
										return;
									}				
								}
							});		
							
						}
					});
				}else{
					Ext.Msg.confirm($i18n.prompt, $i18n_prompt.locateOrNot,function(button, text) 
					{
						if (button == 'yes') 
						{
							//删除临时表状态为0的数据
							Ext.Ajax.request({
								method:'POST',
								async:false,
								url:'odata_LocateAutoAction_deleteLocateSelect.action',
								success:function(response){
									var data = Ext.decode(response.responseText);
									if(data.isSucc){
										Fixed3201();
									}else{
										Ext.Msg.alert($i18n.prompt,'删除临时表失败！');
										//Ext.example.msg($i18n.prompt,'删除临时表失败！');
										return;
									}				
								}
							});		
						}
					});
				}
				
			}
		});
	},
	tabPid3201tabchange:function(tabPanel,newCard,oldCard,eOpts){
		if(tabPanel.activeTab.title==$i18n.locateagain){
			Ext.getCmp("grid_03_3201").getStore().load();
		}
	}

});
function Fixed3201(){
	var strOwnerNo='';
	if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3201').getValue())&&
			Ext.getCmp('cmbOwnerNo3201').getValue()!='ALL')
	{ 
		strOwnerNo=Ext.getCmp('cmbOwnerNo3201').getValue();
	}
	if(Ext.getCmp('cell3201').getValue()!='' && Ext.getCmp('cell3201').getValue()!=null){
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_LocateAction_changeArticle.action',
			params:{
				strCell:Ext.getCmp('cell3201').getValue(),
				strOwnerNo:strOwnerNo
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					Ext.Msg.alert($i18n.prompt,$i18n_prompt.haveProblemOfAritcle);
					//Ext.example.msg($i18n.prompt,$i18n_prompt.haveProblemOfAritcle);
					return;
				}				
			}
		});		
	}
	var msgShow = commonMegShow($i18n_prompt.saving_wait);
	
	var taskAllotRuleID='1';
	if(!Ext.isEmpty(Ext.getCmp('cmbExpType3201').getValue())){
		if(Ext.getCmp('cmbExpType3201').getValue()=='B2C'){
			taskAllotRuleID='2';
		}
	}
	var strDetail=
	{
		enterpriseNo:Ext.get('enterpriseNo').getValue(),	
		warehouseNo:Ext.get('warehouseNo').getValue(),
		ownerNo:Ext.getCmp('cmbOwnerNo3201').getValue(),
		expType:Ext.getCmp('cmbExpType3201').getValue(),
		orgNo:Ext.getCmp('orgNo3201').getValue(),
		locateName:Ext.get('workerNo').getValue(),
		locateDate:new Date(),
		divideFlag:Ext.getCmp('cbdivideFlag3201').getValue()==true ? 1:0,
		sendbufCompute:Ext.getCmp('calculation3201').getValue()==true ? 1:0,
		taskBatch:0,
		sourceType:'1',
		taskAllotRuleID:taskAllotRuleID,
		strdivideComputeFlag:Ext.getCmp('divideFlag3201').getValue()==true ? 1:0,
		strcheckComputeFlag	:Ext.getCmp('arrangePack3201').getValue()==true ? 1:0
	};
	var strDetail = Ext.encode(strDetail);
	var params = 
	{
		strDetail:strDetail
	};
	Ext.Ajax.request({
		method:'POST',
		async:false,
		params:params,
		url:'odata_LocateAutoAction_tscFixed.action',
		success:function(response)
		{
			msgShow.hide();
			var data = Ext.decode(response.responseText);
			if(!data.isSucc)
			{
				Ext.Msg.alert($i18n.prompt,data.msg);

				//Ext.example.msg($i18n.prompt,data.msg);
			}else{
				Ext.example.msg($i18n.prompt,'定位成功！');
				Ext.getCmp('btnQuery3201').fireEvent('click');
			}
		}
	});
	
	//刷新
	Ext.getCmp('cmbOwnerNo3201').getStore().load();
	Ext.getCmp('cmbExpType3201').getStore().load();
	Ext.getCmp('orgNo3201').getStore().load();
	Ext.getCmp('grid_01_3201').getStore().removeAll();
	Ext.getCmp('grid_02_3201').getStore().removeAll();  
	Ext.getCmp('lblcustNumber3201').setText(0);
    Ext.getCmp('lblSumVolumn3201').setText(0);
    Ext.getCmp('lblSumWeight3201').setText(0);
    Ext.getCmp('lblSumBoxQty3201').setText(0);
};
function updateTmpLocateSelect1(params){
	Ext.Ajax.request({
		method:'POST',
		async:false,
		params:params,
		url:'odata_LocateAutoAction_updateTmpLocateSelect1.action',
		success:function(response)
		{
			data = Ext.decode(response.responseText);
			if(data.isSucc){
				Ext.getCmp('calculateQty3201').setText(data.obj);
			}else{
				Ext.Msg.alert($i18n.prompt,data.msg);
			}
			
		}
	});
}
function updateTmpLocateSelect0(params){
	Ext.Ajax.request({
		method:'POST',
		async:false,
		params:params,
		url:'odata_LocateAutoAction_updateTmpLocateSelect0.action',
		success:function(response)
		{
			data = Ext.decode(response.responseText);
			if(data.isSucc){
				Ext.getCmp('calculateQty3201').setText(data.obj);
			}else{
				Ext.Msg.alert($i18n.prompt,data.msg);
			}
		}
	});
}