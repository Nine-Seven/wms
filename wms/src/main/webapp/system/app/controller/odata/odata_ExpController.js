/**
 * 模块名称：出货手建单
 * 模块编码：3101
 * 创建：Jun
 */
var rowindex3101=0;
var isCanEdit3101=false;
var strOANO = "";
var saveType3101="";

Ext.define('cms.controller.odata.odata_ExpController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_ExpUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'odata_ExpUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'odata_ExpUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'odata_ExpUI button[name=userNextButton]':{
				click:this.userNext
			},//新增前加载
			'odata_ExpUI button[name=userAddButton]':{
				click:this.newAdd
			},//加载客户
			'odata_ExpUI form textfield[id=cust3101]':{
				beforequery:this.custBeforeQuery
			},//Grid点击和双击
			'odata_ExpUI grid[id=grid_01_3101]':{
				//selectionchange:this.checkPrintPickingNo,
				itemdblclick:this.grid_01_3101Click
			},//TAB页切换
			'odata_ExpUI tabpanel[id=tabPId3101]':{
				tabchange:this.tabPidchange
			},//验证订单号
			'odata_ExpUI form textfield[id=po_nomark3101]':{
				blur:this.po_nomarkBlur
			},//保存
			'odata_ExpUI button[name=userSaveButton]':{
				click:this.save
			},//删除
			'odata_ExpUI button[name=userDelButton]':{
				click:this.del
			},//编辑
			'odata_ExpUI button[name=userEditButton]':{
				click:this.edit
			},//撤消
			'odata_ExpUI button[name=userUndoButton]':{
				click:this.undo
			},//新增明细
			'odata_ExpUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除明细
			'odata_ExpUI button[name=detailDelete]':{
				click:this.detailDelete
			},//商品Grid编辑
			'odata_ExpUI grid[id=grid_02_3101]':{
				beforeedit:this.grid_02_3101beforeedit
				//edit:this.grid_02_3101Edit
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_no3101]':{
				beforequery:this.article_no3101BeforeQuery,
				select:this.article_noselect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qty3101]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//客户选择
			'odata_ExpUI bdef_DefCustCombo[id=cust3101]':{
				select:this.cust3101Select
			},//上传
			'odata_ExpUI button[name=upload]':{
				click:this.uploadClick
			},//回车键事件
			'odata_ExpUI form[id=form_01_3101] field':{
				specialkey:this.boxkeydown
			},//选择货主，清空客户
			'odata_ExpUI bdef_DefOwnerCombo[id=owner_no3101]':{
				select:this.owner_noselect
			},//关单
			'odata_ExpUI button[name=closeOrder]':{
				click:this.closeOrder
			},///打印拣货单
			'odata_ExpUI button[id=printPickingNo]':{
				click:this.printPickingNo
			},///刷新
			'odata_ExpUI button[name=refresh]':{
				click:this.refresh
			},///查询按钮
			'odata_ExpUI button[id=btnQuery3101]':{
				click:this.btnQuery3101
			},///清除查询条件按钮
			'odata_ExpUI button[id=btnNew3101]':{
				click:this.btnNew3101
			},///选择日期事件--创建日期
			'odata_ExpUI radiogroup[id=rdoCheckType3101_1]':{
				change:this.rdoCheckType3101_1
			
			},///选择日期事件--预计发货日期
			'odata_ExpUI radiogroup[id=rdoCheckType3101_2]':{
				change:this.rdoCheckType3101_2
			
			},///选择日期事件--实际发货日期
			'odata_ExpUI radiogroup[id=rdoCheckType3101_3]':{
				change:this.rdoCheckType3101_3
			},//取消订单  undoOrder   7-13
			'odata_ExpUI button[name=undoOrder]':{
				click:this.undoOrder
			}
		});
	},
	
	initializtion:function(){
		isCanEdit3101=false;
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var planBox3101=commonGetModuleField('3101','planBox')[0].flag;
		var planQmin3101=commonGetModuleField('3101','planQmin')[0].flag;
		var planDis3101=commonGetModuleField('3101','planDis')[0].flag;
		var packingUnit3101=commonGetModuleField('3101','packingUnit')[0].flag;
		var packingSpec3101=commonGetModuleField('3101','packingSpec')[0].flag;
		
		if(planBox3101==0){
			Ext.getCmp('planBox3101').setVisible(false);
		}
		if(planQmin3101==0){
			Ext.getCmp('planQmin3101').setVisible(false);
		}
		if(planDis3101==0){
			Ext.getCmp('planDis3101').setVisible(false);
		}
		if(packingUnit3101==0){
			Ext.getCmp('packingUnit3101').setVisible(false);
		}
		if(packingSpec3101==0){
			Ext.getCmp('packingSpec3101').setVisible(false);
		}
		//end add
	},
	refresh:function(){
		Ext.apply(Ext.getCmp('grid_01_3101').getStore().proxy.extraParams);
		Ext.getCmp('grid_01_3101').getStore().removeAll();
		Ext.getCmp('grid_01_3101').getStore().reload();
	},
	
	//取消订单			7-13添加     
	undoOrder:function(){
		debugger;
		var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
				Ext.Msg.confirm('提示','确定取消订单？',function(button,text){
					debugger;
					if(button=='yes'){
						//8-15添加
						if(data[0].data.status > 10){
							Ext.Msg.alert($i18n.prompt,'改单据不能取消！');
						}else{
							//校验该单是否定过位
						    var params={
						    		expNo:data[0].data.expNo
								};
							Ext.Ajax.request({
								method:'post',
								url:'odata_ExpAction_editExp',
								params:params,
								async:false,
								success:function(response){
									var data2 = Ext.decode(response.responseText);
									if(data2.isSucc){
										//取消订单
										Ext.Ajax.request({
											method:'post',
											url:'odata_ExpAction_cancelOrder.action',
											async:false,
											params:{
												importNo:data[0].data.importNo,
												ownerNo:data[0].data.ownerNo,
												expNo:data[0].data.expNo
										    },
										    success:function(response){
										    	debugger;
										    	var data3 = Ext.decode(response.responseText);
										    	if(data3.isSucc){					
													Ext.example.msg($i18n.prompt,data3.msg);
													Ext.getCmp('grid_01_3101').getStore().removeAll();
											 	  	Ext.getCmp('grid_01_3101').getStore().reload();
												}else{
													Ext.Msg.alert($i18n.prompt,data3.msg);
												}
										    }
										});
									}else{
										Ext.Msg.alert($i18n.prompt,'改单据已定位不能取消！');
									}
								}
							});
						}
					}
				});
		}		
	},
	
	btnQuery3101:function(){
		//下拉选择的筛选条件
		var listDetail1  =  [];
		if(Ext.getCmp("owner_no3101_query").getValue()!=null && Ext.getCmp("owner_no3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.owner_no',
					value:Ext.getCmp("owner_no3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("exp_type3101_query").getValue()!=null && Ext.getCmp("exp_type3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.exp_type',
					value:Ext.getCmp("exp_type3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("takeType3101_query").getValue()!=null && Ext.getCmp("takeType3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.take_type',
					value:Ext.getCmp("takeType3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		if(Ext.getCmp("cust3101_query").getValue()!=null && Ext.getCmp("cust3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.cust_no',
					value:Ext.getCmp("cust3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		if(Ext.getCmp("sanpl_no3101_query").getValue()!=null && Ext.getCmp("sanpl_no3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.shipper_no',
					value:Ext.getCmp("sanpl_no3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		if(Ext.getCmp("Order_source3101_query").getValue()!=null && Ext.getCmp("Order_source3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.order_source',
					value:Ext.getCmp("Order_source3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("orgNo3101_query").getValue()!=null && Ext.getCmp("orgNo3101_query").getValue() != ""){
			var strDtl = {
					columnId:'a.org_no',
					value:Ext.getCmp("orgNo3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("curr_status3101_query").getValue()!=null && Ext.getCmp("curr_status3101_query").getValue() != ""){
			var strDtl = {
					columnId:'s.curr_status',
					value:Ext.getCmp("curr_status3101_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		var strWheresql={
			strQuery:Ext.encode(listDetail1),
			strExpNothy:Ext.getCmp('expNothy3101').getValue()==true ? 1:0,
			expNo:Ext.getCmp("exp_no3101_query").getValue(),
			poNo:Ext.getCmp("po_nomark3101_query").getValue(),
			strDeliverNo:Ext.getCmp("deliver_no3101_query").getValue(),
			strShopNo:Ext.getCmp("shopNo3101_query").getValue(),
			strSkucount:Ext.getCmp("skucount3101_query").getValue(),
			strCustExpNo:Ext.getCmp("custExpNo3101_query").getValue(),
			strSendName:Ext.getCmp("sendName3101_query").getValue(),
			strReceiveName:Ext.getCmp("receiveName3101_query").getValue(),
			strTakeName:Ext.getCmp("takeName3101_query").getValue(),
			
			strErpoperateDate1:Ext.Date.format(Ext.getCmp("rgst_Date3101_1").getValue(), 'Y-m-d H:i:s'),
			strErpoperateDate2:Ext.Date.format(Ext.getCmp("rgst_Date3101_2").getValue(),'Y-m-d H:i:s'),
			
			strCustsendDate1:Ext.Date.format(Ext.getCmp("expect_Date3101_1").getValue(),'Y-m-d H:i:s'),
			strCustsendDate2:Ext.Date.format(Ext.getCmp("expect_Date3101_2").getValue(),'Y-m-d H:i:s'),
			
			strLastCustsendDate1:Ext.Date.format(Ext.getCmp("reality_Date3101_1").getValue(),'Y-m-d H:i:s'),
			strLastCustsendDate2:Ext.Date.format(Ext.getCmp("reality_Date3101_2").getValue(),'Y-m-d H:i:s'),
			 
			         
		};
      
		
		Ext.apply(Ext.getCmp('grid_01_3101').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3101').getStore().removeAll();
		Ext.getCmp('grid_01_3101').getStore().load();
		
		var pagingTool=Ext.getCmp("pagingtoolbar3101");
        pagingTool.moveFirst();//跳至第一页
		
	},
	btnNew3101:function(){
		Ext.getCmp("exp_no3101_query").setValue('');
		Ext.getCmp("po_nomark3101_query").setValue('');
		Ext.getCmp("owner_no3101_query").setValue('');
		//Ext.getCmp("exp_type3101_query").setValue('');
		Ext.getCmp("takeType3101_query").setValue('');
		Ext.getCmp("cust3101_query").setValue('');
		Ext.getCmp("sanpl_no3101_query").setValue('');
		Ext.getCmp("deliver_no3101_query").setValue('');
		Ext.getCmp("Order_source3101_query").setValue('');
		Ext.getCmp("orgNo3101_query").setValue('');
		Ext.getCmp("shopNo3101_query").setValue('');
		Ext.getCmp("skucount3101_query").setValue('');	
		Ext.getCmp("custExpNo3101_query").setValue('');
		Ext.getCmp("sendName3101_query").setValue('');	
		Ext.getCmp("receiveName3101_query").setValue('');
		Ext.getCmp("takeName3101_query").setValue('');
		Ext.getCmp("curr_status3101_query").setValue('');
		
		Ext.getCmp("rgst_Date3101_1").setValue('');	
		Ext.getCmp("rgst_Date3101_2").setValue('');
		
		Ext.getCmp("expect_Date3101_1").setValue('');	
		Ext.getCmp("expect_Date3101_2").setValue('');
		
		Ext.getCmp("reality_Date3101_1").setValue('');	
		Ext.getCmp("reality_Date3101_2").setValue('');
		
		Ext.getCmp("rdoCheckType3101_1").setValue({'rg':'all'});
		Ext.getCmp("rdoCheckType3101_2").setValue({'yj':'all'});
		Ext.getCmp("rdoCheckType3101_3").setValue({'sj':'all'});
		
		Ext.getCmp("expNothy3101").setValue(false);

		
	},
	rdoCheckType3101_1:function(){
		var check1 = Ext.getCmp("rdoCheckType3101_1").getValue().rg;
		if(check1==0){//今天
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1==1){//昨天
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d 23:59:59'));
		}if(check1=='d1'){//近一周
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-7*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='d2'){//近2周
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-14*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='d3'){//近3周
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-21*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='m1'){//近1月
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-30*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='m2'){//近2月
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-60*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='m3'){//近3月
			Ext.getCmp("rgst_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-90*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}
	
	},
	rdoCheckType3101_2:function(){
		
		var check2 = Ext.getCmp("rdoCheckType3101_2").getValue().yj;
		if(check2==0){//今天
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2==1){//昨天
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d 23:59:59'));
		}if(check2=='d1'){//近一周
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-7*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='d2'){//近2周
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-14*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='d3'){//近3周
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-21*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='m1'){//近1月
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-30*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='m2'){//近2月
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-60*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='m3'){//近3月
			Ext.getCmp("expect_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-90*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}
	},
	rdoCheckType3101_3:function(){
		var check3 = Ext.getCmp("rdoCheckType3101_3").getValue().sj;
		if(check3==0){//今天
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3==1){//昨天
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d 23:59:59'));
		}if(check3=='d1'){//近一周
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-7*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='d2'){//近2周
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-14*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='d3'){//近3周
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-21*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='m1'){//近1月
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-30*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='m2'){//近2月
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-60*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='m3'){//近3月
			Ext.getCmp("reality_Date3101_1").setValue(Ext.Date.format(new Date(new Date()-90*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3101_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}
	},
	 //校验该单据的货主是否为大储位管理
	checkPrintPickingNo:function(){
		var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
		if(data.length>0){
			Ext.Ajax.request({
				url:'odata_ExpAction_checkCell',
				method:'post',
				params:{
					ownerNo:data[0].data.ownerNo
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						 Ext.getCmp('printPickingNo').setDisabled(false); 
					}
					else{
						 Ext.getCmp('printPickingNo').setDisabled(true); 
					}
					
				}
			});	
		}
	},
	//打印拣货单
	printPickingNo:function(){
		if(Ext.isEmpty(workSpaceNo)){
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		
		var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
		if(data.length==0){ 
			Ext.example.msg('提示',"请选择要打印的单据！");
		}else{
			Ext.Ajax.request({
				url:'odata_ExpAction_tscPrintPickingNo',
				method:'post',
				params:{
					expNo:data[0].data.expNo,
					type:data[0].data.sourceexpType,
					ownerNo:data[0].data.ownerNo,
					strQuery:data[0].data.orgNo
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('grid_01_3101').getStore().removeAll();
						Ext.getCmp('grid_01_3101').getStore().reload();
					}
					else{
						Ext.Msg.alert($i18n.prompt,data.msg);

						//Ext.example.msg($i18n.prompt,data.msg);
					}
					
				}
			});	
		}		
	},

	//关单
	closeOrder:function(){
		var rec = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
		if(rec.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			if(rec[0].data.locateQty==0 && Ext.isEmpty(rec[0].data.waveNo)){
				var flag = '1';//取消
				Ext.Msg.confirm('提示','该单未做过定位，确定关单？',function(button,text){
					if(button=='yes'){
						closeOrder(flag,rec[0].data.expNo);
					}
				});	
			}else if(rec[0].data.locateQty==0 && !Ext.isEmpty(rec[0].data.waveNo)){
				var flag = '1';//取消
				Ext.Msg.confirm('提示','该单整单缺量，确定关单？',function(button,text){
					if(button=='yes'){
						closeOrder(flag,rec[0].data.expNo);
					}
				});
			}else{
				var flag = '2';//关单
				Ext.Ajax.request({
					method:'POST',
					async:false,
					url:'odata_ExpAction_checkNoEnoght.action',
					params:{
						strQuery:rec[0].data.expNo
				    },
					success:function(response)
					{
						var data = Ext.decode(response.responseText);
						if(!data.isSucc){
							Ext.Msg.confirm('提示','单内有商品缺量，是否强制关单？',function(button,text){
								if(button=='yes'){
									closeOrder(flag,rec[0].data.expNo);
								}
							});	
						}else{
							Ext.Msg.confirm('提示','确定关单？',function(button,text){
								if(button=='yes'){
									closeOrder(flag,rec[0].data.expNo);
								}
							});	
						}
					}
				});
			}
		/*var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			if(data[0].data.status=='10'){
				var flag = '1';//取消
			}else{
				var flag = '2';//关单
			}
			Ext.Msg.confirm('提示','确定关单？',function(button,text){
				if(button=='yes'){
					Ext.Ajax.request({
						method:'post',
						url:'odata_ExpAction_closeOrder',
						params:{
							expNo:data[0].data.expNo,
							flag:flag
					    },
					    success:function(response){
					    	var data = Ext.decode(response.responseText);
					    	if(data.isSucc){					
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('grid_01_3101').getStore().removeAll();
						 	  	Ext.getCmp('grid_01_3101').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg);
							}
					    }
					});
				}
			});*/		
		}		
	},
	//选择货主，清空客户
	owner_noselect:function(){
	   Ext.getCmp('cust3101').setValue("");
	   Ext.getCmp('contactor_name3101').setValue("");
	   Ext.getCmp('cust_phone3101').setValue("");
	   Ext.getCmp('cust_email3101').setValue("");
	   Ext.getCmp('cust_address3101').setValue("");	
	},
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) {
	  		if(th.id=="exp_remark3101"){	  			
	  			Ext.getCmp('toolbar3101').items.items[0].focus();
	  		}else{
				th.nextSibling().focus();
			}
        }
	},
	
	//商品加载前
	article_no3101BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_no3101").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_no3101').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('article_no3101').getStore().removeAll();
			Ext.getCmp('article_no3101').getStore().reload();
	},
	
	custBeforeQuery:function(){		
		var listDetail1  =  [];
		var strDtl = {
			columnId:'t1.owner_no',
			value:Ext.getCmp("owner_no3101").getValue()
		};
		listDetail1.push(strDtl);
		var strWheresql={
			strQuery:Ext.encode(listDetail1)
		};		
		Ext.apply(Ext.getCmp('cust3101').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cust3101').getStore().removeAll();
		Ext.getCmp('cust3101').getStore().load();
	},
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='3101';
		queryGrid='grid_01_3101';	
	},
	
	userPrev:function(){
		rowindex3101=rowindex3101-1;
		editExp3101(rowindex3101);
	},
	
	userNext:function(){
		rowindex3101=rowindex3101+1;
		editExp3101(rowindex3101);
	},
	
	newAdd:function(){
		addExp3101();
		saveType3101='add';
		//bindEnterSkip($('#form_01_3101'));//调用键盘处理方法
	},
	
	grid_01_3101Click:function(th, record,  item,  index, e, eOpts){
	
		Ext.getCmp('tabPId3101').items.items[1].setVisible(true);
		
		isCanEdit3101=false;
		commonEditButton('menu3101','dbclick');
		
		//8-15添加  
		if(record.get('createFlag') == '0'){ //wms自建
			 //当状态为结案和取消时，不允许修改，其他状态可以
			if(record.get('status')=='13' || record.get('status')=='16'){
				disableButtonFunc(1,'#menu3101 [name=userEditButton]','修改');	
			}else{
				disableButtonFunc(0,'#menu3101 [name=userEditButton]','修改');
			}
		}else if(record.get('createFlag') == '1'){ //ERP下传单据
			if(record.get('status')=='13' || record.get('status')=='16'){
				disableButtonFunc(1,'#menu3101 [name=userEditButton]','修改');
			}else{
				disableButtonFunc(0,'#menu3101 [name=userEditButton]','修改');
			}
		}
		
		Ext.getCmp('toolbar3101').items.items[0].disable(true);
		Ext.getCmp('toolbar3101').items.items[1].disable(true);
	},
	
	
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		Ext.getCmp('owner_no3101').getStore().load();
		Ext.getCmp('exp_type3101').getStore().load();
		if(newCard.itemId=='tabPId3101i'){
			var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
			if(data.length!=0){
				editExp3101(data[0].index);
				rowindex3101=data[0].index;
					
				isCanEdit3101=false;
				commonEditButton('menu3101','dbclick');
				//8-15添加
				commonSetMsterReadOnlyByArray(new Array(
						'custsend_Date3101','last_custsend_Date3101'
				),true);
				
				//8-15添加 
				if(data[0].data.createFlag == '0'){ //wms自建
					 //当状态为结案和取消时，不允许修改，其他状态可以
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menu3101 [name=userEditButton]','修改');	
					}else{
						disableButtonFunc(0,'#menu3101 [name=userEditButton]','修改');
					}
				}else if(data[0].data.createFlag == '1'){ //ERP下传单据
					if(data[0].data.status=='13' || data[0].data.status=='16'){
						disableButtonFunc(1,'#menu3101 [name=userEditButton]','修改');
					}else{
						disableButtonFunc(0,'#menu3101 [name=userEditButton]','修改');
					}
				}
				
				Ext.getCmp('toolbar3101').items.items[0].disable(true);
				Ext.getCmp('toolbar3101').items.items[1].disable(true);
			}
		}
	},
	
	po_nomarkBlur:function(){
		if(Ext.getCmp('exp_no3101').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
				method:'post',
				url:'odata_ExpAction_checkPoNo',
				params:{
					poNo:Ext.getCmp('po_nomark3101').getValue()
		    	},
		    	success:function(response){
		    		var res = Ext.decode(response.responseText);
		    		if(res=='1'){
		    			Ext.example.msg('提示','该单号已有，请重新录入');
		    			Ext.getCmp('po_nomark3101').setValue('');
		    			Ext.getCmp('po_nomark3101').focus();
		    		}
		   		}
			});
		}
		
	},
	
	save:function(){ 
		var gridcount=Ext.getCmp('grid_02_3101').getStore().getCount();
		if(gridcount>0){
			if(!commonCheckdetailgrid('grid_02_3101',0,gridcount-1))
			{
				return;
			}
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		var expNo=Ext.getCmp('exp_no3101').getValue();
		var sourceexpNo=Ext.getCmp('po_nomark3101').getValue();
		var custNo=Ext.getCmp('cust3101').getValue();
		var ownerNo=Ext.getCmp('owner_no3101').getValue();
		var sourceexpType=Ext.getCmp('exp_type3101').getValue();
		var takeType = Ext.getCmp('takeType3101').getValue();
		var priority=Ext.getCmp('priority3101').getValue();
		var fastFlag=Ext.getCmp('fast_flag3101').getValue();
		var expRemark=Ext.getCmp('exp_remark3101').getValue();
		var shipperNo=Ext.getCmp('sanpl_no3101').getValue();
		var shipperDeliverNo=Ext.getCmp('deliver_no3101').getValue();
		var deliverAddress=Ext.getCmp('Deliver_address3101').getValue();
		var printBillFlag=Ext.getCmp('Print_bill_flag3101').getValue();
		var orderSource=Ext.getCmp('Order_source3101').getValue();
		var orgNo=Ext.getCmp('orgNo3101').getValue();
		//var lastCustsendDate=Ext.getCmp('last_custsend_Date3101').getValue();
		var lastCustsendDate=Ext.getCmp('last_custsend_Date3101').getValue()==null?'':Ext.getCmp('last_custsend_Date3101').getValue();
		var custsendDate=Ext.getCmp('custsend_Date3101').getValue();
		var custExpNo = Ext.getCmp('cust_exp_no3101').getValue();
		
		var contactorName=Ext.getCmp('contactor_name3101').getValue();
		var custPhone=Ext.getCmp('cust_phone3101').getValue();
		var custMail=Ext.getCmp('cust_email3101').getValue();
		var custAddress=Ext.getCmp('cust_address3101').getValue();
		
		//var da=Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
		var master={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				expNo:expNo,
				expType:sourceexpType
			},
			ownerNo:ownerNo,
			ownerCustNo:Ext.getCmp('ownerCustNo3101').getValue(),
			custNo:custNo,
			subCustNo:custNo,
			sourceexpType:sourceexpType,
			sourceexpNo:sourceexpNo,
			lastCustsendDate:lastCustsendDate,
			custsendDate:custsendDate,
			expDate:new Date(),
			fastFlag:fastFlag,
			takeType:takeType,
			status:'10',
			expStatus:'00',
			batchNo:'N',
			lineNo:'N',
			stockType:'1',
			priority:priority,
			addExpNo:'N',
			//classType:'0',
			deliverType:'2',
			transportType:'1',
			createFlag:'0',
			returnFlag:'0',
			belongFlag:'0',
			sendFlag:'10',
			specialArticleGroup:'0',
			custExpNo:custExpNo,
			expRemark:expRemark,
			deptNo:'N',
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date(),
			shipperNo:shipperNo,
			shipperDeliverNo:shipperDeliverNo,
			deliverAddress:deliverAddress,
			printBillFlag:printBillFlag,
			orderSource:orderSource,
			orgNo:orgNo,
			erpoperateDate:new Date(),
			
			contactorName:contactorName,
			custPhone:custPhone,
			custMail:custMail,
			custAddress:custAddress,
			receiveTelephone:Ext.getCmp('receive_telephone3101').getValue(),
			receiveProvince:Ext.getCmp('receive_province3101').getValue(),
			receiveCompanyName:Ext.getCmp('receive_company_name3101').getValue(),
			receiveJpn:Ext.getCmp('receive_jpn3101').getValue(),
			receiveCity:Ext.getCmp('receive_city3101').getValue(),
			receiveZone:Ext.getCmp('receive_zone3101').getValue(),
			receiveCountry:Ext.getCmp('receive_country3101').getValue(),
			
			takeName:Ext.getCmp('take_name3101').getValue(),
			takeMobilePhone:Ext.getCmp('take_mobile_phone3101').getValue(),
			takeTelephone:Ext.getCmp('take_telephone3101').getValue(),
			takePostcode:Ext.getCmp('take_postcode3101').getValue(),
			takeCompanyName:Ext.getCmp('take_company_name3101').getValue(),
			takeJpn:Ext.getCmp('take_jpn3101').getValue(),
			takeAddress:Ext.getCmp('take_address3101').getValue(),
			takeProvince:Ext.getCmp('take_province3101').getValue(),
			takeCity:Ext.getCmp('take_city3101').getValue(),
			takeZone:Ext.getCmp('take_zone3101').getValue(),
			takeCountry:Ext.getCmp('take_country3101').getValue(),
			
			takeName:Ext.getCmp('take_name3101').getValue(),
			takeMobilePhone:Ext.getCmp('take_mobile_phone3101').getValue(),
			takeTelephone:Ext.getCmp('take_telephone3101').getValue(),
			takePostcode:Ext.getCmp('take_postcode3101').getValue(),
			takeCompanyName:Ext.getCmp('take_company_name3101').getValue(),
			takeJpn:Ext.getCmp('take_jpn3101').getValue(),
			takeAddress:Ext.getCmp('take_address3101').getValue(),
			takeProvince:Ext.getCmp('take_province3101').getValue(),
			takeCity:Ext.getCmp('take_city3101').getValue(),
			takeZone:Ext.getCmp('take_zone3101').getValue(),
			takeCountry:Ext.getCmp('take_country3101').getValue(),
			
			sendName:Ext.getCmp('send_name3101').getValue(),
			sendMobilePhone:Ext.getCmp('send_mobile_phone3101').getValue(),
			sendTelephone:Ext.getCmp('send_telephone3101').getValue(),
			sendPostcode:Ext.getCmp('send_postcode3101').getValue(),
			sendCompanyName:Ext.getCmp('send_company_name3101').getValue(),
			sendJpn:Ext.getCmp('send_jpn3101').getValue(),
			sendAddress:Ext.getCmp('send_address3101').getValue(),
			sendProvince:Ext.getCmp('send_province3101').getValue(),
			sendCity:Ext.getCmp('send_city3101').getValue(),
			sendZone:Ext.getCmp('send_zone3101').getValue(),
			sendCountry:Ext.getCmp('send_country3101').getValue(),
			skucount:'0'
		};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var exp=Ext.getCmp('grid_02_3101').getStore().getAt(i);
			var d={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					expNo:expNo,
					rowId:i
				},
				ownerNo:ownerNo,
				articleNo:exp.get('articleNo'),
				packingQty:exp.get('packingQty'),
				condition:'N',
				specialBatch:'0',
				articleQty:exp.get('planBox')*exp.get('packingQty')
				          +exp.get('planQmin')*exp.get('qminOperatePacking')
				          +exp.get('planDis'), //update by huangcx at 20160528
				scheduleQty:'0.00000',
				locateQty:'0.0000',
				realQty:'0.0000',
				unitCost:exp.get('unitCost'),
				errorStatus:'000',
				rgstDate:new Date(),
				expDate:new Date(),
				itemType:'0',
				ownerArticleNo:exp.get('ownerArticleNo'),
				status:'10',
				quality:'0',
				
				produceCondition:exp.get('produceCond')==undefined?  '' : exp.get('produceCond'),
				produceValue1:exp.get('produceV1')==undefined?  '' : exp.get('produceV1'),
				produceValue2:exp.get('produceV2')==undefined?  '' : exp.get('produceV2'),	
				lotnoCondition:exp.get('lotnoCondition')==undefined?  '' : exp.get('lotnoCondition'),
				lotnoValue1:exp.get('lotnoValue1')==undefined?  '' : exp.get('lotnoValue1'),
				lotnoValue2:exp.get('lotnoValue2')==undefined?  '' : exp.get('lotnoValue2'),
				specifyField:exp.get('specifyField')==undefined?  '' : exp.get('specifyField'),
				specifyCondition:exp.get('specifyCondition')==undefined?  '' : exp.get('specifyCondition'),
				specifyValue1:exp.get('specifyValue1')==undefined?  '' : exp.get('specifyValue1'),
				specifyValue2:exp.get('specifyValue2')==undefined?  '' : exp.get('specifyValue2')
			};
			if((exp.get('planBox')*exp.get('packingQty')
			   +exp.get('planQmin')*exp.get('qminOperatePacking')
			   +exp.get('planDis'))==0){
				Ext.example.msg('提示','计划数量不能为0');
				return;
			}
			detail.push(d);
		};
		disableButtonFunc(1,'#menu3101 [name=userSaveButton]','保存');
		var expM=Ext.encode(master);
		var expD=Ext.encode(detail);
		var params={
			expM:expM,
			expD:expD
		};
		if(saveType3101=='change'){
			Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_changeExp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
                    Ext.example.msg('提示', data.msg);
					Ext.getCmp('exp_no3101').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_3101').getStore().getAt(i);
						record.set('expNo',data.obj);
					}
					if(typeof(Ext.getCmp('grid_01_3101'))!=='undefined'){
						Ext.getCmp('grid_01_3101').store.reload();
					}
					commonEditButton('menu3101','save');
					isCanEdit3101=false;
					Ext.getCmp('toolbar3101').items.items[0].disable(true);
					Ext.getCmp('toolbar3101').items.items[1].disable(true);
					//7-13   点击保存之后，设为只读
					commonSetMsterReadOnlyByArray(
							new Array('exp_no3101','po_nomark3101','cust3101','last_custsend_Date3101',
							'custsend_Date3101','takeType3101',
							'owner_no3101','contactor_name3101','exp_type3101','cust_phone3101',
							'cust_email3101','cust_address3101','priority3101','fast_flag3101','sanpl_no3101',
							'exp_remark3101','deliver_no3101','Deliver_address3101','Print_bill_flag3101',
							'Order_source3101','orgNo3101','cust_exp_no3101'
							,'receive_telephone3101','receive_company_name3101','receive_jpn3101',
							'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
							,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
							'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
							'take_city3101','take_zone3101','take_country3101'
							,'send_name3101','send_mobile_phone3101','send_telephone3101',
							'send_postcode3101','send_company_name3101','send_jpn3101',
							'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
							,true);
				}else{
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
					disableButtonFunc(0,'#menu3101 [name=userSaveButton]','保存');
				}
			}
		});
		
		}else if(saveType3101=='add'){
			Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_saveExp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					//回写出货单头档品项数（该品项数指总数）
					Ext.Ajax.request({
						method:'post',
						url:'odata_ExpAction_updateExpM',
						params:{
							ownerNo:ownerNo,
							expNo:data.obj
						},
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(!data.isSucc){
								Ext.example.msg('提示','回写品项数失败！');
							}
						}
					});
					
					//写出货单状态跟踪表
					Ext.Ajax.request({
						method:'post',
						url:'odata_ExpAction_tscExpStatus',
						params:{
							expNo:data.obj
						},
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(!data.isSucc){
								Ext.example.msg('提示','写出货单状态跟踪表失败！');
							}
						}
					});
					Ext.getCmp('exp_no3101').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_3101').getStore().getAt(i);
						record.set('expNo',data.obj);
					}
					/*if(typeof(Ext.getCmp('grid_01_3101'))!=='undefined'){
						var wheresql={
								strExpNothy:Ext.getCmp('expNothy3101').getValue()==true ? 1:0,

						};
						Ext.apply(Ext.getCmp('grid_01_3101').getStore().proxy.extraParams,wheresql);
						Ext.getCmp('grid_01_3101').getStore().removeAll();
						Ext.getCmp('grid_01_3101').getStore().load();	
					}*/
					commonEditButton('menu3101','save');
					isCanEdit3101=false;
					Ext.getCmp('toolbar3101').items.items[0].disable(true);
					Ext.getCmp('toolbar3101').items.items[1].disable(true);
					disableButtonFunc(1,'#menu3101 [name=userSaveButton]','保存');
				}else{
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
				}
			}
		});
		}
	},
	
	del:function(){
		var expNo=Ext.String.trim(Ext.getCmp('exp_no3101').getValue());
		Ext.Msg.confirm('提示','确定删除数据？',function(button,text){
			if(button=='yes'){
				var params={
					expNo:expNo
				};
				Ext.Ajax.request({
					method:'post',
					url:'odata_ExpAction_deleteExp',
					params:params,
					success:function(response){
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							if(typeof(Ext.getCmp('grid_01_3101'))!="undefined"){
									
								var g=Ext.getCmp('grid_01_3101');
								var atindex=g.getStore().findExact('expNo',Ext.getCmp('exp_no3101').getValue());
								g.getStore().removeAt(atindex);
								var count=g.store.getCount();
									
								if(count==0){
									addExp3101();
								}else if(atindex==-1 && count!=0){
									editExp3101(0);
								}else if(atindex<count){
									editExp3101(atindex);
								}else {
									editExp3101(count-1);
								};
							};
						};
					}
				});
			};
		});
	},
	
	grid_02_3101beforeedit:function(e){
		if(!isCanEdit3101)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//校验商品是否重复，因电商支持一张单一个商品可以重复，所以把这段代码屏蔽
/*	grid_02_3101Edit:function(editor,e,eOpts){
		if(e.field=='packingQty' ||e.field=='lotnoValue1' || e.field=='lotnoValue2' || e.field=='lotnoCondition'){
			
			if(editor.grid.getStore().
			findBy(function(record, id) {  
					return record.internalId!=editor.context.record.internalId 
					&& record.get('articleNo')==editor.context.record.data.articleNo
					&& record.get('packingQty')==editor.context.record.data.packingQty;
				})!=-1){
				if(e.field=='packingQty'){
					editor.context.record.set('packingQty',editor.context.originalValue);
				}
				Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
			}
		}
	},*/
	
	edit:function(){
		var gridcount=Ext.getCmp('grid_02_3101').getStore().getCount();	
		var lastDate=Ext.getCmp('grid_02_3101').getStore().getAt(gridcount-1);
		if(lastDate.get('articleNo')=='合计'){
		Ext.getCmp('grid_02_3101').getStore().remove(lastDate);
		}
		//校验该单是否定过位
	    var params={
	    		expNo:Ext.getCmp('exp_no3101').getValue()
			};
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_editExp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					commonEditButton('menu3101','edit');
					saveType3101='change';
					
					disableButtonFunc(1,'#menu3101 [name=userPrevButton]','上单');
					disableButtonFunc(1,'#menu3101 [name=userNextButton]','下单');
					//Ext.getCmp('toolbar3101').items.items[0].enable(true);
					//Ext.getCmp('toolbar3101').items.items[1].enable(true);
					//新增和删除两个按钮   7-11
					disableButtonFunc(0,'#toolbar3101 [name=detailAdd]','新增');
					disableButtonFunc(0,'#toolbar3101 [name=detailDelete]','删除');
					
					var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
					//8-13修改
					if(data[0].data.createFlag=='0'){//wms自建
						if(data[0].data.waveNo == '' && data[0].data.status =='10'){//状态为10，未产生波次号
							commonSetMsterReadOnlyByArray(
									new Array('po_nomark3101',
									'last_custsend_Date3101','custsend_Date3101','takeType3101',
									'owner_no3101','contactor_name3101','cust_phone3101',
									'cust_email3101','cust_address3101','priority3101','fast_flag3101','cust_exp_no3101',
									'sanpl_no3101','exp_remark3101','deliver_no3101','Deliver_address3101',
									'Print_bill_flag3101','Order_source3101','orgNo3101','receive_telephone3101','receive_company_name3101','receive_jpn3101',
									'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
									,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
									'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
									'take_city3101','take_zone3101','take_country3101'
									,'send_name3101','send_mobile_phone3101','send_telephone3101',
									'send_postcode3101','send_company_name3101','send_jpn3101',
									'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
									,false);
							isCanEdit3101 = true;
						}else{
							commonSetMsterReadOnlyByArray(
									new Array(
									'contactor_name3101','cust_phone3101',
									'cust_email3101','cust_address3101','priority3101',
									'exp_remark3101',
									'orgNo3101','receive_telephone3101','receive_company_name3101','receive_jpn3101',
									'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
									,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
									'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
									'take_city3101','take_zone3101','take_country3101'
									,'send_name3101','send_mobile_phone3101','send_telephone3101',
									'send_postcode3101','send_company_name3101','send_jpn3101',
									'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
									,false);
							isCanEdit3101 = false;
						}
					}else if(data[0].data.createFlag=='1'){//ERP下传单据
						commonSetMsterReadOnlyByArray(
								new Array(
								'contactor_name3101','cust_phone3101',
								'cust_email3101','cust_address3101','priority3101',
								'exp_remark3101',
								'orgNo3101','receive_telephone3101','receive_company_name3101','receive_jpn3101',
								'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
								,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
								'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
								'take_city3101','take_zone3101','take_country3101'
								,'send_name3101','send_mobile_phone3101','send_telephone3101',
								'send_postcode3101','send_company_name3101','send_jpn3101',
								'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
								,false);
						isCanEdit3101 = false;
					}
				}else{
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
					disableButtonFunc(0,'#menu3101 [name=userSaveButton]','保存');
					isCanEdit3101 = false;
				}
			}
		});
		
	},
	undo:function(){
		
		if(saveType3101=='change'){
			disableButtonFunc(0,'#menu3101 [name=userEditButton]','修改');	
			disableButtonFunc(0,'#menu3101 [name=userPrevButton]','上单');
			disableButtonFunc(0,'#menu3101 [name=userNextButton]','下单');
			var data = Ext.getCmp('grid_01_3101').getSelectionModel().getSelection();
			if(data.length!=0){
				editExp3101(data[0].index);
				rowindex3101=data[0].index;
				isCanEdit3101=false;
				commonEditButton('menu3101','dbclick');
			}
		}else{
			isCanEdit3101=false;
			addExp3101();
			Ext.getCmp('grid_02_3101').getStore().removeAll();
			commonSetMsterReadOnlyByArray(
			new Array('exp_no3101','po_nomark3101','cust3101','last_custsend_Date3101',
			'custsend_Date3101','takeType3101',
			'owner_no3101','contactor_name3101','exp_type3101','cust_phone3101',
			'cust_email3101','cust_address3101','priority3101','fast_flag3101','sanpl_no3101',
			'exp_remark3101','deliver_no3101','Deliver_address3101','Print_bill_flag3101',
			'Order_source3101','orgNo3101','cust_exp_no3101'
			,'receive_telephone3101','receive_company_name3101','receive_jpn3101',
			'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
			,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
			'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
			'take_city3101','take_zone3101','take_country3101'
			,'send_name3101','send_mobile_phone3101','send_telephone3101',
			'send_postcode3101','send_company_name3101','send_jpn3101',
			'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
			,true);
			Ext.getCmp('toolbar3101').items.items[0].disable(true);
			Ext.getCmp('toolbar3101').items.items[1].disable(true);
			commonEditButton('menu3101','undo');
			disableButtonFunc(1,'#menu3101 [name=userEditButton]','修改');	
		}
		
	},
	
	detailAdd:function(){
		var store = Ext.getCmp('grid_02_3101').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
			//7-13
			if(!commonCheckIsInputAll('form_01_3101'))
			{
				return;
			}
			
		}else{
			if(!commonCheckIsInputAll('form_01_3101'))
			{
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('exp_no3101','po_nomark3101','cust3101',
			'last_custsend_Date3101','custsend_Date3101','takeType3101',
			'owner_no3101','contactor_name3101','exp_type3101','cust_phone3101',
			'cust_email3101','cust_address3101','priority3101','fast_flag3101','cust_exp_no3101',
			'sanpl_no3101','exp_remark3101','deliver_no3101','Deliver_address3101',
			'Print_bill_flag3101','Order_source3101','orgNo3101','receive_telephone3101','receive_company_name3101','receive_jpn3101',
			'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
			,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
			'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
			'take_city3101','take_zone3101','take_country3101'
			,'send_name3101','send_mobile_phone3101','send_telephone3101',
			'send_postcode3101','send_company_name3101','send_jpn3101',
			'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
			,true);
		}
		var r=Ext.create('cms.model.odata.odata_ExpDModel',{});
		r.set('expNo',Ext.getCmp('exp_no3101').getValue());
		store.add(r);
		Ext.getCmp('grid_02_3101').editingPlugin.startEdit(count,1);
	},
	
	detailDelete:function(){
		var data = Ext.getCmp('grid_02_3101').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm('提示','确定删除数据',function(button,text){
			if(button=='yes'){
				Ext.getCmp('grid_02_3101').getStore().remove(data);					
				if(Ext.getCmp("grid_02_3101").getStore().getCount()==0)
				{
					commonSetMsterReadOnlyByArray(
					new Array('exp_no3101','po_nomark3101','cust3101','takeType3101',
					'owner_no3101','contactor_name3101','exp_type3101','cust_phone3101',
					'cust_email3101','cust_address3101','priority3101','fast_flag3101',
					'exp_remark3101','sanpl_no3101','cust_exp_no3101')
					,false);
				};
			}
			});
		}else{
			Ext.example.msg('提示', '请先选择您要删除的行');
			return;
		}
	},
	
	article_noselect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_getArticle',
			params:{
				articleNo:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	var data = Ext.getCmp('grid_02_3101').getSelectionModel().getSelection();
				data[0].set('articleName',res[0].articleName);
		    	data[0].set('barcode',res[0].barcode);
		    	data[0].set('ownerArticleNo',res[0].ownerArticleNo);
		    	//update by huangcx at 20160528
		    	data[0].set('planBox',0);
		    	data[0].set('planQmin',0);
		    	data[0].set('planDis',0);
		    	data[0].set('unitCost',0);
		    	data[0].set('qminOperatePacking',res[0].qminOperatePacking);
		    	data[0].set('unitPacking',res[0].unitPacking);
		    	//end update
		     	Ext.Ajax.request({
					method:'post',
					url:'bdef_DefarticleAction_getMaxArticlePacking',
					params:{
						strWheresql:combo.getValue()
				    },
				    success:function(response){
				    	var res = Ext.decode(response.responseText);
				    	data[0].set('packingQty',res);
				    	
				    	Ext.Ajax.request({
							method:'post',
							url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
							params:{
								strArticleNo:combo.getValue(),
								strPackingQty:res
						    },
						    success:function(response){
						    	var flagCount=0;
						    	var gridcount=Ext.getCmp('grid_02_3101').getStore().getCount();
						    	for(var i=0;i<gridcount;i++){
						    		var exp=Ext.getCmp('grid_02_3101').getStore().getAt(i);    		
						    		if(exp.get('articleNo')==combo.getValue()&&exp.get('packingQty')==data[0].get('packingQty')){
						    			flagCount=flagCount+1;
						    		}
						    	}
						    	var res = Ext.decode(response.responseText);
						    	data[0].set('packingUnit',res[0].packingUnit);
						    	data[0].set('packingSpec',res[0].spec); //update by huangcx at 20160528
						    	
						    	//因电商支持一张单一个商品可以重复，所以把这段代码屏蔽
						    	/*if(flagCount==0){
						    		var res = Ext.decode(response.responseText);
							    	data[0].set('packingUnit',res[0].packingUnit);
							    	data[0].set('spec',res[0].spec);
						    	}else{
						    		Ext.example.msg('提示', "【商品编码】、【商品包装】不能重复，请重新输入！");
						    		data[0].set('packingQty',null);
						    	}  	*/
						    }
						});
				    }
				    
		    	});
		    }
		});
	},
	
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('grid_02_3101').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	
	packingQtyselect:function(combo){
		var data = Ext.getCmp('grid_02_3101').getSelectionModel().getSelection();
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_getPackingUnit',
			params:{
				articleNo:data[0].get('articleNo'),
				packingQty:combo.getValue(),
				type:'1'
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	data[0].set('packingUnit',res[0].packingUnit);
		    	data[0].set('packingSpec',res[0].spec);//update by huangcx at 20160528
		    	data[0].set('articleQty',null);
		    	
		    }
		});
	},
	
	cust3101Select:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_queryCust',
			params:{
				strCustNo:combo.getValue()
		    },
		    success:function(response){

		    	var res = Ext.decode(response.responseText);
		    	Ext.getCmp('contactor_name3101').setValue(res[0].contactorName);
		    	Ext.getCmp('cust_phone3101').setValue(res[0].custPhone);
		    	Ext.getCmp('cust_email3101').setValue(res[0].custMail);
		    	Ext.getCmp('cust_address3101').setValue(res[0].custAddress);
		    	Ext.getCmp('ownerCustNo3101').setValue(res[0].ownerCustNo);
		    }
		});
	},
	
	uploadClick:function(){
		Ext.create('cms.view.odata.window.odataUploadWindow',
		{
			title:'上传'
		}).show();
	}
	
});

//新增前加载
function addExp3101(){
	Ext.getCmp('form_01_3101').getForm().reset();
	Ext.getCmp('exp_no3101').setValue('保存时自动生成');
	if(Ext.getCmp('exp_type3101').getStore().data.length>0)
	{
		Ext.getCmp('exp_type3101').setValue(Ext.getCmp('exp_type3101').getStore().getAt(0).data.value);		
	}
	
	Ext.getCmp('takeType3101').setValue('0');
	
	Ext.getCmp('priority3101').setValue('100');
	Ext.getCmp('fast_flag3101').setValue('0');
	Ext.getCmp('Print_bill_flag3101').setValue('0');
	Ext.getCmp('Order_source3101').setValue('0');
	Ext.getCmp('orgNo3101').setValue('001');
//	Ext.getCmp('last_custsend_Date3101').setValue(new Date());
	Ext.getCmp('grid_02_3101').getStore().removeAll();
	if(Ext.getCmp('owner_no3101').getStore().data.length>0)
	{
		Ext.getCmp('owner_no3101').setValue(Ext.getCmp('owner_no3101').getStore().getAt(0).data.value);		
	}
	commonSetMsterReadOnlyByArray(
			new Array('exp_no3101','po_nomark3101','cust3101',
			'last_custsend_Date3101','custsend_Date3101','last_custsend_Date3101',
			'owner_no3101','contactor_name3101','exp_type3101','takeType3101','cust_phone3101',
			'cust_email3101','cust_address3101','priority3101','fast_flag3101',
			'exp_remark3101','cust_exp_no3101','sanpl_no3101','deliver_no3101','Deliver_address3101',
			'Print_bill_flag3101','Order_source3101','orgNo3101',
			'receive_telephone3101','receive_company_name3101','receive_jpn3101',
			'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
			,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
			'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
			'take_city3101','take_zone3101','take_country3101'
			,'send_name3101','send_mobile_phone3101','send_telephone3101',
			'send_postcode3101','send_company_name3101','send_jpn3101',
			'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
			,false);
	commonSetMsterReadOnlyByArray(
			new Array('exp_no3101','priority3101')
			,true);
			
	commonEditButton('menu3101','add');
	Ext.getCmp('toolbar3101').items.items[0].enable(true);
	Ext.getCmp('toolbar3101').items.items[1].enable(true);
	Ext.getCmp('po_nomark3101').focus();
	isCanEdit3101=true;
};

//编辑数据
function editExp3101(rowindex3101){
	if(rowindex3101==0)
	{
		Ext.getCmp('menu3101').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu3101').items.items[0].enable(true);
	}
	if(rowindex3101==Ext.getCmp('grid_01_3101').getStore().getCount()-1)
	{		
		Ext.getCmp('menu3101').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu3101').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_3101').getStore().getAt(rowindex3101-(Ext.getCmp('grid_01_3101').getStore().currentPage-1)*appConfig.getPageSize());
	
	if(record.data.status=='10'){
		disableButtonFunc(0,'#menu3101 [name=userEditButton]','修改');	
	}else{
		disableButtonFunc(1,'#menu3101 [name=userEditButton]','修改');
	}
	
	Ext.getCmp('exp_no3101').setValue(record.data.expNo);
	Ext.getCmp('po_nomark3101').setValue(record.data.sourceexpNo);
	
	Ext.getCmp('cust3101').getStore().add({
    	value:record.data.custNo,
    	dropValue:'['+record.data.custNo+']'+record.data.custName,
    	text:record.data.custName
    });
	if(record.data.shipperNo != ''){
		 Ext.getCmp('sanpl_no3101').getStore().add({
		    	value:record.data.shipperNo,
		    	dropValue:'['+record.data.shipperNo+']'+record.data.shipperName,
		    	text:record.data.shipperName
		    });
	}
    Ext.getCmp('cust3101').setValue(record.data.custNo);
    Ext.getCmp('owner_no3101').setValue(record.data.ownerNo);
    Ext.getCmp('contactor_name3101').setValue(record.data.contactorName);
    Ext.getCmp('takeType3101').setValue(record.data.takeType);
    Ext.getCmp('exp_type3101').setValue(record.data.expType);
    Ext.getCmp('cust_phone3101').setValue(record.data.custPhone);
    Ext.getCmp('cust_email3101').setValue(record.data.custMail);
    Ext.getCmp('cust_address3101').setValue(record.data.custAddress);
    Ext.getCmp('priority3101').setValue(record.data.priority);
    Ext.getCmp('fast_flag3101').setValue(record.data.fastFlag);
    Ext.getCmp('exp_remark3101').setValue(record.data.expRemark);
    Ext.getCmp('sanpl_no3101').setValue(record.data.shipperNo);
    Ext.getCmp('deliver_no3101').setValue(record.data.shipperDeliverNo);
    Ext.getCmp('Deliver_address3101').setValue(record.data.deliverAddress);
    Ext.getCmp('Print_bill_flag3101').setValue(record.data.printBillFlag);
    Ext.getCmp('Order_source3101').setValue(record.data.orderSource);
    Ext.getCmp('orgNo3101').setValue(record.data.orgNo);
    Ext.getCmp('last_custsend_Date3101').setValue(record.data.lastCustsendDate);
    Ext.getCmp('custsend_Date3101').setValue(record.data.custsendDate);
    Ext.getCmp('cust_exp_no3101').setValue(record.data.custExpNo);
    
	Ext.getCmp('receive_telephone3101').setValue(record.data.receiveTelephone);
	Ext.getCmp('receive_province3101').setValue(record.data.receiveProvince);
	Ext.getCmp('receive_company_name3101').setValue(record.data.receiveCompanyName);
	Ext.getCmp('receive_jpn3101').setValue(record.data.receiveJpn);
	Ext.getCmp('receive_city3101').setValue(record.data.receiveCity);
	Ext.getCmp('receive_zone3101').setValue(record.data.receiveZone);
	Ext.getCmp('receive_country3101').setValue(record.data.receiveCountry);
	
	Ext.getCmp('take_name3101').setValue(record.data.takeName);
	Ext.getCmp('take_mobile_phone3101').setValue(record.data.takeMobilePhone);
	Ext.getCmp('take_telephone3101').setValue(record.data.takeTelephone);
	Ext.getCmp('take_postcode3101').setValue(record.data.takePostcode);
	Ext.getCmp('take_company_name3101').setValue(record.data.takeCompanyName);
	Ext.getCmp('take_jpn3101').setValue(record.data.takeJpn);
	Ext.getCmp('take_address3101').setValue(record.data.takeAddress);
	Ext.getCmp('take_province3101').setValue(record.data.takeProvince);
	Ext.getCmp('take_city3101').setValue(record.data.takeCity);
	Ext.getCmp('take_zone3101').setValue(record.data.takeZone);
	Ext.getCmp('take_country3101').setValue(record.data.takeCountry);
	
	Ext.getCmp('take_name3101').setValue(record.data.takeName);
	Ext.getCmp('take_mobile_phone3101').setValue(record.data.takeMobilePhone);
	Ext.getCmp('take_telephone3101').setValue(record.data.takeTelephone);
	Ext.getCmp('take_postcode3101').setValue(record.data.takePostcode);
	Ext.getCmp('take_company_name3101').setValue(record.data.takeCompanyName);
	Ext.getCmp('take_jpn3101').setValue(record.data.takeJpn);
	Ext.getCmp('take_address3101').setValue(record.data.takeAddress);
	Ext.getCmp('take_province3101').setValue(record.data.takeProvince);
	Ext.getCmp('take_city3101').setValue(record.data.takeCity);
	Ext.getCmp('take_zone3101').setValue(record.data.takeZone);
	Ext.getCmp('take_country3101').setValue(record.data.takeCountry);
	
	Ext.getCmp('send_name3101').setValue(record.data.sendName);
	Ext.getCmp('send_mobile_phone3101').setValue(record.data.sendMobilePhone);
	Ext.getCmp('send_telephone3101').setValue(record.data.sendTelephone);
	Ext.getCmp('send_postcode3101').setValue(record.data.sendPostcode);
	Ext.getCmp('send_company_name3101').setValue(record.data.sendCompanyName);
	Ext.getCmp('send_jpn3101').setValue(record.data.sendJpn);
	Ext.getCmp('send_address3101').setValue(record.data.sendAddress);
	Ext.getCmp('send_province3101').setValue(record.data.sendProvince);
	Ext.getCmp('send_city3101').setValue(record.data.sendCity);
	Ext.getCmp('send_zone3101').setValue(record.data.sendZone);
	Ext.getCmp('send_country3101').setValue(record.data.sendCountry);
    
    commonSetMsterReadOnlyByArray(
				new Array('exp_no3101','po_nomark3101','cust3101','takeType3101',
				'owner_no3101','contactor_name3101','exp_type3101','cust_phone3101',
				'cust_email3101','cust_address3101','priority3101','fast_flag3101','sanpl_no3101',
				'exp_remark3101','cust_exp_no3101','deliver_no3101','Deliver_address3101',
				'Print_bill_flag3101','Order_source3101','orgNo3101','receive_telephone3101','receive_company_name3101','receive_jpn3101',
				'receive_province3101','receive_city3101','receive_zone3101','receive_country3101'
				,'take_name3101','take_mobile_phone3101','take_telephone3101','take_postcode3101',
				'take_company_name3101','take_jpn3101','take_address3101','take_province3101',
				'take_city3101','take_zone3101','take_country3101'
				,'send_name3101','send_mobile_phone3101','send_telephone3101',
				'send_postcode3101','send_company_name3101','send_jpn3101',
				'send_address3101','send_province3101','send_city3101','send_zone3101','send_country3101')
				,true);
	var wheresql={
		wheresql:record.data.expNo
	};
	Ext.apply(Ext.getCmp('grid_02_3101').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_3101').getStore().removeAll();
	Ext.getCmp('grid_02_3101').getStore().load();		
};
//关单
function closeOrder(flag,expNo){
	Ext.Ajax.request({
		method:'post',
		url:'odata_ExpAction_closeOrder',
		params:{
			expNo:expNo,
			flag:flag
	    },
	    success:function(response){
	    	var data = Ext.decode(response.responseText);
	    	if(data.isSucc){					
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('grid_01_3101').getStore().removeAll();
		 	  	Ext.getCmp('grid_01_3101').getStore().reload();
			}else{
				Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
				//Ext.example.msg($i18n.prompt,data.msg+data.obj);
			}
	    }
	});
}