/**
 * 客户与电子标签储位对应关系
 * 模块编码   1P01
 * @author hcx
 */
var deviceNo1P01='';
Ext.define('cms.controller.bdef.bdef_CsetCustDpscellController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.bdef_CsetCustDpscellUI'
	          ],
	          model:'',
	          store:'',
	          init:function(){
	        	  this.control({//删除
	        		  'bdef_CsetCustDpscellUI button[name=detailDelete]':{
	        			  click:this.detailDelete
	        		  },//储位初始化
	        		  'bdef_CsetCustDpscellUI panel[id=bdef_CCDCellGrid1P01]':{
	        			  select:this.bdef_CCDCellGrid1P01change
	        		  },//选择客户加载对应储位
	        		  'bdef_CsetCustDpscellUI grid[id=bdef_Cust_ListGrid2]':{
	        			  selectionchange:this.bdef_Cust_ListGrid2click
	        		  },//根据货主查找客户
	        		  'bdef_CsetCustDpscellUI combo[id=cmbFormOwner1P01]':{
	        			  select:this.selectCustByOwner
	        		  },//已分配或未分配客户选择
	        		  'bdef_CsetCustDpscellUI radiogroup[id=rdoCheckType1P01]':
	        		  {
	        			  change:this.rdoCheckType1P01Change
	        		  },//分播组选择
	        		  'bdef_CsetCustDpscellUI combo[id=deviceGroupNo1P01]':
	        		  {
	        			  select:this.deviceGroupNo1P01Select
	        		  },//分播线选择
	        		  'bdef_CsetCustDpscellUI combo[id=deviceNo1P01]':
	        		  {
	        			  select:this.deviceNo1P01Select
	        		  }

	        	  });
	          },
	          //页面初始化
	          initializtion:function()
	          {
	        	  bdef_CCDCellGrid1P01change();
	          },
	          //删除
	          detailDelete:function(){
	        	  var record = Ext.getCmp('bdef_Cust_ListGrid2').getSelectionModel()
	        	  .getSelection();
	        	  if(Ext.getCmp("rdoCheckType1P01").getValue().rd !=2){
	        		    Ext.example.msg('提示',"请选择所选分播线下已分配储位客户！");
						return;
					}else{
						if (record.length == 0) {
			        		  Ext.example.msg('提示',"请先选择客户！");
			        		  return;
			        	  }else {
			        		  var custNo = record[0].data.custNo;
			        		  var dpsCellNo = record[0].data.dpsCellNo;
			        	  }
			        	  var params=
			        	  {
			        			  strCustNo:custNo,
			        			  str:dpsCellNo
			        	  };
			        	  var params2=
			        	  {
			        			  strOwnerNo:Ext.getCmp("cmbFormOwner1P01").getValue(),
			        			  strCustNo:custNo,
			        			  str:dpsCellNo
			        	  };
			        	  Ext.Ajax.request({
			        		  method:'post',
			        		  params:params,
			        		  url:'bdef_CsetCustDpscellAction_checkCustNo.action',
			        		  success:function(response){
			        			  var res = Ext.decode(response.responseText);
			        			  if(res == '0'){
			        				  Ext.example.msg('提示',"该客户未分配储位，不用删除");
			        			  }else{
			        				  Ext.Ajax.request({
			        	        		  method:'post',
			        	        		  params:params2,
			        	        		  url:'bdef_CsetCustDpscellAction_checkDeliverCustNo.action',
			        	        		  success:function(response){
			        	        			  var re = Ext.decode(response.responseText);
			        	        			  if(re.isSucc){
			        	        				  if(re = 'Y'){
				        	        				  
				        	        				  Ext.Msg.confirm("提示", "确定为该客户解除储位？",function(button, text) 
				        										{
				        									if (button == 'yes') 
				        									{
				        										Ext.Ajax.request({
				        				        					  method:'post',
				        				        					  url:'bdef_CsetCustDpscellAction_delete',
				        				        					  params:params,
				        				        					  success:function(response){
				        				        						  var data = Ext.decode(response.responseText);
				        				        						  if(data.isSucc){
				        				        							  Ext.example.msg('提示',"已解除储位关系");
				        				        							  if(typeof(Ext.getCmp('bdef_Cust_ListGrid2'))!=='undefined'){
				        				        								  Ext.getCmp('bdef_Cust_ListGrid2').store.reload();
				        				        							  }
				        				        						  }else{
				        				        							  Ext.example.msg('提示',"删除失败");
				        				        						  }
				        				        					  }
				        				        				  });
				        									}
				        							  });
				        	        				  
				        	        			  }
			        	        			  }else{
			        	        				  Ext.example.msg($i18n.prompt,re.msg);
			        	      					  return;
			        	        			  }
			        	        		  }
			        	        	  });
			        			  }
			        		  }
			        	  });
					}
	        	  		
	          },
	          //选择客户加载对应储位
	          bdef_Cust_ListGrid2click:function(){
  	      		  deviceNo1P01=Ext.getCmp("deviceNo1P01").getValue();
	        	  bdef_CCDCellGrid1P01change(deviceNo1P01);
	          },
	          //根据货主查找客户
	          selectCustByOwner:function(){
	        	  select1P01();
	          },
	          //已分配或未分配客户选择
	          rdoCheckType1P01Change:function(th)
	          {
	        	  select1P01();

	          },
              //分播组选择
	          deviceGroupNo1P01Select:function(){
	        	 var listDetail1  =  [];
	        	 var listDetail2  =  [];

	        	 if(!Ext.isEmpty(Ext.getCmp('deviceGroupNo1P01').getValue())){
	        		 var strDtl = {
	    	      			 columnId:'a.device_group_no',
	    	      			 value:Ext.getCmp("deviceGroupNo1P01").getValue()
	    	      		 };
	    	      		 listDetail1.push(strDtl);
	    	      		 var params={
	    	      			 str:Ext.encode(listDetail1)
	    	      		 };	    	      		 
	    	      		 Ext.apply(Ext.getCmp('deviceNo1P01').getStore().proxy.extraParams,params);
	    	      		 Ext.getCmp('deviceNo1P01').getStore().removeAll();
	    	      		 Ext.getCmp('deviceNo1P01').getStore().reload();
	    	      		 var strDtl2 = {
	   	    	      			 columnId:'c.device_group_no',
	   	    	      			 value:Ext.getCmp("deviceGroupNo1P01").getValue()
	   	    	      		 };
	   	    	      		 listDetail2.push(strDtl2);
	    	      		 if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1P01').getValue())){
	   	    	      	    var strDtl3 = {
	   	    	      			 columnId:'a.owner_no',
	   	    	      			 value:Ext.getCmp("cmbFormOwner1P01").getValue()
	   	    	      		 };
	   	    	      	    listDetail2.push(strDtl3);
	    	      		 }
	    	      		 var wheresql={
		   	    	      			wheresql:Ext.encode(listDetail2),
		   	    	      			strFlag:Ext.getCmp("deviceGroupNo1P01").getValue()
		   	    	      		 };	 
	    	      		 Ext.apply(Ext.getCmp('bdef_Cust_ListGrid2').getStore().proxy.extraParams,wheresql);
	    	      		 Ext.getCmp('bdef_Cust_ListGrid2').getStore().removeAll();
	    	      		 Ext.getCmp('bdef_Cust_ListGrid2').getStore().reload();
		    	      	 bdef_CCDCellGrid1P01change(null);
	    	      		

	        	 }
	          },
	          //分播线选择
	          deviceNo1P01Select:function(){
	        	  select1P01();

	          }
});
//查询
function select1P01(){	  
	 var listDetail1  =  [];
	 var listDetail2  =  [];
	 if(!Ext.isEmpty(Ext.getCmp('cmbFormOwner1P01').getValue())){
   		var strDt1 = {
	      			columnId:'a.owner_no',
	      		 value:Ext.getCmp("cmbFormOwner1P01").getValue()
	      		};
	        listDetail1.push(strDt1);
	 }
	 if(!Ext.isEmpty(Ext.getCmp('deviceGroupNo1P01').getValue())){
		 var strDt2 = {
     			 columnId:'c.device_group_no',
     			 value:Ext.getCmp("deviceGroupNo1P01").getValue()
     		 };
     		 listDetail1.push(strDt2);
     		var strDt5 = {
        			 columnId:'b.device_group_no',
        			 value:Ext.getCmp("deviceGroupNo1P01").getValue()
        		 };
     		 if(!Ext.isEmpty(Ext.getCmp('deviceNo1P01').getValue()) 
     				 && Ext.getCmp('deviceNo1P01').getValue() !='ALL'){
  	      	    var strDtl3 = {
  	      			 columnId:'c.device_no',
  	      			 value:Ext.getCmp("deviceNo1P01").getValue()
  	      		 };
  	      	    if(Ext.getCmp('rdoCheckType1P01').getValue().rd==2){
  	      	        var strDtl4 = {
  	  	      			 columnId:'b.device_no',
  	  	      			 value:Ext.getCmp("deviceNo1P01").getValue()
  	  	      		 };
  	  	      	    listDetail2.push(strDtl4);
  	  	      	    listDetail1.push(strDtl3);
  	        		listDetail2.push(strDt5);


  	      	    }
     		 }
	 }
	 
	 deviceNo1P01=Ext.getCmp("deviceNo1P01").getValue();
	 var params={
			 str:Ext.encode(listDetail2),
		    wheresql:Ext.encode(listDetail1),
			strAssignedCustomer:Ext.getCmp("rdoCheckType1P01").getValue(),
			strFlag:Ext.getCmp("deviceGroupNo1P01").getValue()
  	     };
	
	 Ext.apply(Ext.getCmp('bdef_Cust_ListGrid2').getStore().proxy.extraParams,params);
	 Ext.getCmp('bdef_Cust_ListGrid2').getStore().removeAll();
	 Ext.getCmp('bdef_Cust_ListGrid2').getStore().loadPage(1);
	 Ext.getCmp('bdef_Cust_ListGrid2').getStore().load();
	 bdef_CCDCellGrid1P01change(deviceNo1P01);
 
}
function bdef_CCDCellGrid1P01change(deviceNo1P01){
	var record = Ext.getCmp('bdef_Cust_ListGrid2').getSelectionModel()
	.getSelection();
	if (record.length == 0) {
		var custNo = null;
	}else {
		var custNo = record[0].data.custNo;
	}
	
	var params=
	{
			strCustNo:custNo,
			 str:deviceNo1P01
	};
	Ext.Ajax.request({
		url:'bdef_CsetCustDpscellAction_getCdef_DefCell.action',
		params:params,
		success : function(response, options) {
			var data = Ext.JSON.decode(response.responseText);//将字符串转换为json类型
			if(data.isSucc){
				var json=Ext.JSON.decode(data.obj);
				var ds = new Ext.data.JsonStore({
					data:json.data,
					fields:json.fieldsNames
				});

				var grid = Ext.create('Ext.grid.Panel',{
					split: true,
					border:false,
					hideHeaders : true,
					columnLines : true,
					columns:json.columModle,
					store:ds,
					listeners:{
						//为客户分配储位
						'cellclick':function(th,td,cellIndex,record,tr,rowindex,e,eOpts ){
							debugger;
							var warehouseNo = Ext.get('warehouseNo').getValue();
							var ownerNo = Ext.getCmp('cmbFormOwner1P01').getValue();
							var deviceGroupNo = Ext.getCmp('deviceGroupNo1P01').getValue();
							var deviceNo = Ext.getCmp('deviceNo1P01').getValue();
							var s = Ext.getCmp('bdef_Cust_ListGrid2').getSelectionModel()
							.getSelection();
							if(Ext.getCmp("rdoCheckType1P01").getValue().rd ==2){
								return;
							}else{
								if(Ext.isEmpty(deviceGroupNo)){
									Ext.example.msg('提示',"请先选择分播组！");
									return;
								}
								if(Ext.isEmpty(deviceNo) || deviceNo =='ALL'){
									Ext.example.msg('提示',"请先选择分播线！");
									return;
								}
								if(Ext.isEmpty(ownerNo)){
									Ext.example.msg('提示',"请先选择货主！");
									return;
								}
								if (s.length == 0) {
									Ext.example.msg('提示',"请先选择客户！");
									return;
								}else {
									var custNo = s[0].data.custNo;
								}
								var listDetail1  =  [];
								var strDtl1 = {
					  	      			 columnId:'a.device_group_no',
					  	      			 value:Ext.getCmp("deviceGroupNo1P01").getValue()
					  	      		 };
					  	      	listDetail1.push(strDtl1);   
								var params2=
								{
										strCustNo:custNo,
										wheresql:Ext.encode(listDetail1)
								};
								Ext.Ajax.request({
									method:'post',
									params:params2,
									url:'bdef_CsetCustDpscellAction_checkCustNo.action',
									success:function(response){
										var res = Ext.decode(response.responseText);
										if(res == '1'){
											Ext.example.msg('提示',"该客户已在"+s[0].data.deviceNo+"线分配储位");
											return;
										}else{
											var dpsCellNo = Ext.isIE8?td.innerText:td.textContent;
											var params3=
											{
													strDpsCellNo:dpsCellNo
											};
											Ext.Ajax.request({
												method:'post',
												params:params3,
												url:'bdef_CsetCustDpscellAction_checkDpsCellNo.action',
												success:function(response){
													var res = Ext.decode(response.responseText);
													if(res == '1'){
														Ext.example.msg('提示',"该储位已分配客户");
														return;
													}else{
														Ext.Msg.confirm("提示", "确定为所选客户分配储位？",function(button, text) 
																{
															if (button == 'yes') 
															{
																var master={
																		id:{
																			enterpriseNo:Ext.get('enterpriseNo').getValue(),
																			warehouseNo:warehouseNo,
																			ownerNo:ownerNo,
																			dpsCellNo:dpsCellNo
																		},
																		custNo:custNo,
																		rgstName:Ext.get('workerNo').getValue(),
																		rgstDate:new Date(),
																		status:1,
																		deviceGroupNo:deviceGroupNo,
																		deviceNo:deviceNo
																};
																var M=Ext.encode(master);
																var params1=
																{
																		jsonMaster:M
																};
																Ext.Ajax.request({
																	method:'post',
																	url:'bdef_CsetCustDpscellAction_add',
																	params:params1,
																	success:function(response){
																		var data = Ext.decode(response.responseText);
																		if(data.isSucc){
																			Ext.example.msg('提示',"分配储位成功");
																			if(typeof(Ext.getCmp('bdef_Cust_ListGrid2'))!=='undefined'){
																				Ext.getCmp('bdef_Cust_ListGrid2').store.reload();
																			}
																		}else{
																			Ext.example.msg('提示',"分配储位失败");
																		}
																	}
																});
															}
																});
													}
												}
											});
										}
									}
								});
							}
							
							
						}
					}
				});

				Ext.getCmp('bdef_CCDCellGrid1P01').removeAll();
				Ext.getCmp('bdef_CCDCellGrid1P01').add(grid);  
				Ext.getCmp('bdef_CCDCellGrid1P01').doLayout();
			}else{
				Ext.getCmp('bdef_CCDCellGrid1P01').removeAll();
			}
		}
	});
}