/**
 * 模块名称：调拨出货手建单
 * 模块编码：3104
 * 创建：hcx
 */
var rowindex3104=0;
var isCanEdit3104=false;
var strOANO = "";
var saveType3104="";

Ext.define('cms.controller.odata.odata_ExpForAllotController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_ExpForAllotUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//查找
			'odata_ExpForAllotUI button[name=detailQuery]':{
				click:this.detailQuery
			},//上一条记录
			'odata_ExpForAllotUI button[name=userPrevButton]':{
				click:this.userPrev
			},//下一条记录
			'odata_ExpForAllotUI button[name=userNextButton]':{
				click:this.userNext
			},//新增前加载
			'odata_ExpForAllotUI button[name=userAddButton]':{
				click:this.newAdd
			},//加载客户
			'odata_ExpForAllotUI form textfield[id=cust3104]':{
				beforequery:this.custBeforeQuery
			},//Grid点击和双击
			'odata_ExpForAllotUI grid[id=grid_01_3104]':{
//				selectionchange:this.checkPrintPickingNo,
				itemdblclick:this.grid_01_3104Click
			},//TAB页切换
			'odata_ExpForAllotUI tabpanel[id=tabPId3104]':{
				tabchange:this.tabPidchange
			},//验证订单号
			'odata_ExpForAllotUI form textfield[id=po_nomark3104]':{
				blur:this.po_nomarkBlur
			},//保存
			'odata_ExpForAllotUI button[name=userSaveButton]':{
				click:this.save
			},//删除
			'odata_ExpForAllotUI button[name=userDelButton]':{
				click:this.del
			},//编辑
			'odata_ExpForAllotUI button[name=userEditButton]':{
				click:this.edit
			},//撤消
			'odata_ExpForAllotUI button[name=userUndoButton]':{
				click:this.undo
			},//新增明细
			'odata_ExpForAllotUI button[name=detailAdd]':{
				click:this.detailAdd
			},//删除明细
			'odata_ExpForAllotUI button[name=detailDelete]':{
				click:this.detailDelete
			},//商品Grid编辑
			'odata_ExpForAllotUI grid[id=grid_02_3104]':{
				beforeedit:this.grid_02_3104beforeedit
				//edit:this.grid_02_3104Edit
			},//商品下拉选择
			'bdef_DefArticleCombo[id=article_no3104]':{
				beforequery:this.article_no3104BeforeQuery,
				select:this.article_noselect
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qty3104]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			},//客户选择
			'odata_ExpForAllotUI bdef_DefCustCombo[id=cust3104]':{
				select:this.cust3104Select
			},//上传
			'odata_ExpForAllotUI button[name=upload]':{
				click:this.uploadClick
			},//回车键事件
			'odata_ExpForAllotUI form[id=form_01_3104] field':{
				specialkey:this.boxkeydown
			},//选择货主，清空客户
			'odata_ExpForAllotUI bdef_DefOwnerCombo[id=owner_no3104]':{
				select:this.owner_noselect
			},///打印拣货单
			'odata_ExpForAllotUI button[id=printPickingNo3104]':{
				click:this.printPickingNo
			},///刷新
			'odata_ExpForAllotUI button[name=refresh]':{
				click:this.refresh
			},///查询按钮
			'odata_ExpForAllotUI button[id=btnQuery3104]':{
				click:this.btnQuery3104
			},///清除查询条件按钮
			'odata_ExpForAllotUI button[id=btnNew3104]':{
				click:this.btnNew3104
			},///选择日期事件--创建日期
			'odata_ExpForAllotUI radiogroup[id=rdoCheckType3104_1]':{
				change:this.rdoCheckType3104_1
			
			},///选择日期事件--预计发货日期
			'odata_ExpForAllotUI radiogroup[id=rdoCheckType3104_2]':{
				change:this.rdoCheckType3104_2
			
			},///选择日期事件--实际发货日期
			'odata_ExpForAllotUI radiogroup[id=rdoCheckType3104_3]':{
				change:this.rdoCheckType3104_3
			}
		});
	},
	
	initializtion:function(){
		isCanEdit3104=false;
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var planBox3104=commonGetModuleField('3104','planBox')[0].flag;
		var planQmin3104=commonGetModuleField('3104','planQmin')[0].flag;
		var planDis3104=commonGetModuleField('3104','planDis')[0].flag;
		var packingUnit3104=commonGetModuleField('3104','packingUnit')[0].flag;
		var packingSpec3104=commonGetModuleField('3104','packingSpec')[0].flag;
		
		if(planBox3104==0){
			Ext.getCmp('planBox3104').setVisible(false);
		}
		if(planQmin3104==0){
			Ext.getCmp('planQmin3104').setVisible(false);
		}
		if(planDis3104==0){
			Ext.getCmp('planDis3104').setVisible(false);
		}
		if(packingUnit3104==0){
			Ext.getCmp('packingUnit3104').setVisible(false);
		}
		if(packingSpec3104==0){
			Ext.getCmp('packingSpec3104').setVisible(false);
		}
		//end add
	},
	refresh:function(){
		Ext.apply(Ext.getCmp('grid_01_3104').getStore().proxy.extraParams);
		Ext.getCmp('grid_01_3104').getStore().removeAll();
		Ext.getCmp('grid_01_3104').getStore().reload();
	},
	
	btnQuery3104:function(){
		//下拉选择的筛选条件
		var listDetail1  =  [];
		if(Ext.getCmp("owner_no3104_query").getValue()!=null && Ext.getCmp("owner_no3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.owner_no',
					value:Ext.getCmp("owner_no3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("exp_type3104_query").getValue()!=null && Ext.getCmp("exp_type3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.exp_type',
					value:Ext.getCmp("exp_type3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("takeType3104_query").getValue()!=null && Ext.getCmp("takeType3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.take_type',
					value:Ext.getCmp("takeType3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		if(Ext.getCmp("cust3104_query").getValue()!=null && Ext.getCmp("cust3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.cust_no',
					value:Ext.getCmp("cust3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		if(Ext.getCmp("sanpl_no3104_query").getValue()!=null && Ext.getCmp("sanpl_no3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.shipper_no',
					value:Ext.getCmp("sanpl_no3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		if(Ext.getCmp("Order_source3104_query").getValue()!=null && Ext.getCmp("Order_source3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.order_source',
					value:Ext.getCmp("Order_source3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}
		if(Ext.getCmp("orgNo3104_query").getValue()!=null && Ext.getCmp("orgNo3104_query").getValue() != ""){
			var strDtl = {
					columnId:'a.org_no',
					value:Ext.getCmp("orgNo3104_query").getValue()
			};
			listDetail1.push(strDtl);
		}	
		var strWheresql={
			strQuery:Ext.encode(listDetail1),
			strExpNothy:Ext.getCmp('expNothy3104').getValue()==true ? 1:0,
			expNo:Ext.getCmp("exp_no3104_query").getValue(),
			poNo:Ext.getCmp("po_nomark3104_query").getValue(),
			strDeliverNo:Ext.getCmp("deliver_no3104_query").getValue(),
			strShopNo:Ext.getCmp("shopNo3104_query").getValue(),
			strSkucount:Ext.getCmp("skucount3104_query").getValue(),
			strCustExpNo:Ext.getCmp("custExpNo3104_query").getValue(),
			strSendName:Ext.getCmp("sendName3104_query").getValue(),
			strReceiveName:Ext.getCmp("receiveName3104_query").getValue(),
			strTakeName:Ext.getCmp("takeName3104_query").getValue(),
			
			strErpoperateDate1:Ext.Date.format(Ext.getCmp("rgst_Date3104_1").getValue(), 'Y-m-d H:i:s'),
			strErpoperateDate2:Ext.Date.format(Ext.getCmp("rgst_Date3104_2").getValue(),'Y-m-d H:i:s'),
			
			strCustsendDate1:Ext.Date.format(Ext.getCmp("expect_Date3104_1").getValue(),'Y-m-d H:i:s'),
			strCustsendDate2:Ext.Date.format(Ext.getCmp("expect_Date3104_2").getValue(),'Y-m-d H:i:s'),
			
			strLastCustsendDate1:Ext.Date.format(Ext.getCmp("reality_Date3104_1").getValue(),'Y-m-d H:i:s'),
			strLastCustsendDate2:Ext.Date.format(Ext.getCmp("reality_Date3104_2").getValue(),'Y-m-d H:i:s'),
			 
			         
		};
      
		
		Ext.apply(Ext.getCmp('grid_01_3104').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_3104').getStore().removeAll();
		Ext.getCmp('grid_01_3104').getStore().load();
		
		var pagingTool=Ext.getCmp("pagingtoolbar3104");
        pagingTool.moveFirst();//跳至第一页
		
	},
	btnNew3104:function(){
		Ext.getCmp("exp_no3104_query").setValue('');
		Ext.getCmp("po_nomark3104_query").setValue('');
		Ext.getCmp("owner_no3104_query").setValue('');
	//	Ext.getCmp("exp_type3104_query").setValue('');
		Ext.getCmp("takeType3104_query").setValue('');
		Ext.getCmp("cust3104_query").setValue('');
		Ext.getCmp("sanpl_no3104_query").setValue('');
		Ext.getCmp("deliver_no3104_query").setValue('');
		Ext.getCmp("Order_source3104_query").setValue('');
		Ext.getCmp("orgNo3104_query").setValue('');
		Ext.getCmp("shopNo3104_query").setValue('');
		Ext.getCmp("skucount3104_query").setValue('');	
		Ext.getCmp("custExpNo3104_query").setValue('');
		Ext.getCmp("sendName3104_query").setValue('');	
		Ext.getCmp("receiveName3104_query").setValue('');
		Ext.getCmp("takeName3104_query").setValue('');
		
		Ext.getCmp("rgst_Date3104_1").setValue('');	
		Ext.getCmp("rgst_Date3104_2").setValue('');
		
		Ext.getCmp("expect_Date3104_1").setValue('');	
		Ext.getCmp("expect_Date3104_2").setValue('');
		
		Ext.getCmp("reality_Date3104_1").setValue('');	
		Ext.getCmp("reality_Date3104_2").setValue('');
		
		Ext.getCmp("rdoCheckType3104_1").setValue({'rg':'all'});
		Ext.getCmp("rdoCheckType3104_2").setValue({'yj':'all'});
		Ext.getCmp("rdoCheckType3104_3").setValue({'sj':'all'});
		
		Ext.getCmp("expNothy3104").setValue(false);

		
	},
	rdoCheckType3104_1:function(){
		var check1 = Ext.getCmp("rdoCheckType3104_1").getValue().rg;
		if(check1==0){//今天
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1==1){//昨天
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d 23:59:59'));
		}if(check1=='d1'){//近一周
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-7*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='d2'){//近2周
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-14*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='d3'){//近3周
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-21*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='m1'){//近1月
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-30*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='m2'){//近2月
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-60*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check1=='m3'){//近3月
			Ext.getCmp("rgst_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-90*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("rgst_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}
	
	},
	rdoCheckType3104_2:function(){
		
		var check2 = Ext.getCmp("rdoCheckType3104_2").getValue().yj;
		if(check2==0){//今天
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2==1){//昨天
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d 23:59:59'));
		}if(check2=='d1'){//近一周
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-7*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='d2'){//近2周
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-14*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='d3'){//近3周
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-21*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='m1'){//近1月
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-30*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='m2'){//近2月
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-60*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check2=='m3'){//近3月
			Ext.getCmp("expect_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-90*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("expect_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}
	},
	rdoCheckType3104_3:function(){
		var check3 = Ext.getCmp("rdoCheckType3104_3").getValue().sj;
		if(check3==0){//今天
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3==1){//昨天
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(new Date()-24*60*60*1000), 'Y-m-d 23:59:59'));
		}if(check3=='d1'){//近一周
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-7*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='d2'){//近2周
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-14*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='d3'){//近3周
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-21*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='m1'){//近1月
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-30*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='m2'){//近2月
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-60*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}if(check3=='m3'){//近3月
			Ext.getCmp("reality_Date3104_1").setValue(Ext.Date.format(new Date(new Date()-90*24*60*60*1000), 'Y-m-d'));	
			Ext.getCmp("reality_Date3104_2").setValue(Ext.Date.format(new Date(), 'Y-m-d H:i:s'));
		}
	},
	 //校验该单据的货主是否为大储位管理
	checkPrintPickingNo:function(){
		var data = Ext.getCmp('grid_01_3104').getSelectionModel().getSelection();
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
		
		var data = Ext.getCmp('grid_01_3104').getSelectionModel().getSelection();
		if(data.length==0){ 
			Ext.example.msg('提示',"请选择要打印的单据！");
		}else{
			if(data[0].data.status!=10){
				Ext.example.msg('提示',"该单据不是建单状态！");
				return;
			}
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.surePrint,function(button, text){
				if (button == 'yes') {
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
								Ext.getCmp('grid_01_3104').getStore().removeAll();
								Ext.getCmp('grid_01_3104').getStore().reload();
							}
							else{
								Ext.Msg.alert($i18n.prompt,data.msg);

								//Ext.example.msg($i18n.prompt,data.msg);
							}
							
						}
					});	
				}
				if (button == 'no') {
					return;
				}
			});
		}		
	},
	//选择货主，清空客户
	owner_noselect:function(){
	   Ext.getCmp('cust3104').setValue("");
	   Ext.getCmp('contactor_name3104').setValue("");
	   Ext.getCmp('cust_phone3104').setValue("");
	   Ext.getCmp('cust_email3104').setValue("");
	   Ext.getCmp('cust_address3104').setValue("");	
	},
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) {
	  		if(th.id=="exp_remark3104"){	  			
	  			Ext.getCmp('toolbar3104').items.items[0].focus();
	  		}else{
				th.nextSibling().focus();
			}
        }
	},
	
	//商品加载前
	article_no3104BeforeQuery:function(){
		 var listDetail1  =  [];
			var strDtl = {
				columnId:'t1.owner_no',
				value:Ext.getCmp("owner_no3104").getValue()
			};
			listDetail1.push(strDtl);
			var strWheresql={
				strQuery:Ext.encode(listDetail1)
			};
			Ext.apply(Ext.getCmp('article_no3104').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('article_no3104').getStore().removeAll();
			Ext.getCmp('article_no3104').getStore().reload();
	},
	
	custBeforeQuery:function(){		
		var listDetail1  =  [];
		var strDtl = {
			columnId:'t1.owner_no',
			value:Ext.getCmp("owner_no3104").getValue()
		};
		listDetail1.push(strDtl);
		var strWheresql={
			strQuery:Ext.encode(listDetail1)
		};		
		Ext.apply(Ext.getCmp('cust3104').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cust3104').getStore().removeAll();
		Ext.getCmp('cust3104').getStore().load();
	},
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='3104';
		queryGrid='grid_01_3104';	
	},
	
	userPrev:function(){
		rowindex3104=rowindex3104-1;
		editExp3104(rowindex3104);
	},
	
	userNext:function(){
		rowindex3104=rowindex3104+1;
		editExp3104(rowindex3104);
	},
	
	newAdd:function(){
		addExp3104();
		saveType3104='add';
		//bindEnterSkip($('#form_01_3104'));//调用键盘处理方法
	},
	
	grid_01_3104Click:function(th, record,  item,  index, e, eOpts){
	
		Ext.getCmp('tabPId3104').items.items[1].setVisible(true);
		
		isCanEdit3104=false;
		commonEditButton('menu3104','dbclick');
		if(record.get('status')=='10'){
			disableButtonFunc(0,'#menu3104 [name=userEditButton]','修改');	
		}else{
			disableButtonFunc(1,'#menu3104 [name=userEditButton]','修改');
		}
		Ext.getCmp('toolbar3104').items.items[0].disable(true);
		Ext.getCmp('toolbar3104').items.items[1].disable(true);
	},
	
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		Ext.getCmp('owner_no3104').getStore().load();
		Ext.getCmp('exp_type3104').getStore().load();
		if(newCard.itemId=='tabPId3104i'){
			var data = Ext.getCmp('grid_01_3104').getSelectionModel().getSelection();
			if(data.length!=0){
				editExp3104(data[0].index);
				rowindex3104=data[0].index;
					
				isCanEdit3104=false;
				commonEditButton('menu3104','dbclick');
				
				if(data[0].data.status=='10'){
					disableButtonFunc(0,'#menu3104 [name=userEditButton]','修改');	
				}else{
					disableButtonFunc(1,'#menu3104 [name=userEditButton]','修改');
				}
				Ext.getCmp('toolbar3104').items.items[0].disable(true);
				Ext.getCmp('toolbar3104').items.items[1].disable(true);
			}
		}
	},
	
	po_nomarkBlur:function(){
		if(Ext.getCmp('exp_no3104').getValue()=='保存时自动生成'){
			Ext.Ajax.request({
				method:'post',
				url:'odata_ExpAction_checkPoNo',
				params:{
					poNo:Ext.getCmp('po_nomark3104').getValue()
		    	},
		    	success:function(response){
		    		var res = Ext.decode(response.responseText);
		    		if(res=='1'){
		    			Ext.example.msg('提示','该单号已有，请重新录入');
		    			Ext.getCmp('po_nomark3104').setValue('');
		    			Ext.getCmp('po_nomark3104').focus();
		    		}
		   		}
			});
		}
		
	},
	
	save:function(){ 
		var gridcount=Ext.getCmp('grid_02_3104').getStore().getCount();
		if(gridcount>0){
			if(!commonCheckdetailgrid('grid_02_3104',0,gridcount-1))
			{
				return;
			}
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		var expNo=Ext.getCmp('exp_no3104').getValue();
		var sourceexpNo=Ext.getCmp('po_nomark3104').getValue();
		var custNo=Ext.getCmp('cust3104').getValue();
		var ownerNo=Ext.getCmp('owner_no3104').getValue();
		var sourceexpType=Ext.getCmp('exp_type3104').getValue();
		var takeType = Ext.getCmp('takeType3104').getValue();
		var priority=Ext.getCmp('priority3104').getValue();
		var fastFlag=Ext.getCmp('fast_flag3104').getValue();
		var expRemark=Ext.getCmp('exp_remark3104').getValue();
		var shipperNo=Ext.getCmp('sanpl_no3104').getValue();
		var shipperDeliverNo=Ext.getCmp('deliver_no3104').getValue();
		var deliverAddress=Ext.getCmp('Deliver_address3104').getValue();
		var printBillFlag=Ext.getCmp('Print_bill_flag3104').getValue();
		var orderSource=Ext.getCmp('Order_source3104').getValue();
		var orgNo=Ext.getCmp('orgNo3104').getValue();
		//var lastCustsendDate=Ext.getCmp('last_custsend_Date3104').getValue();
		var lastCustsendDate=Ext.getCmp('last_custsend_Date3104').getValue()==null?'':Ext.getCmp('last_custsend_Date3104').getValue();
		var custsendDate=Ext.getCmp('custsend_Date3104').getValue();
		var custExpNo = Ext.getCmp('cust_exp_no3104').getValue();
		
		var contactorName=Ext.getCmp('contactor_name3104').getValue();
		var custPhone=Ext.getCmp('cust_phone3104').getValue();
		var custMail=Ext.getCmp('cust_email3104').getValue();
		var custAddress=Ext.getCmp('cust_address3104').getValue();
		
		//var da=Ext.getCmp('grid_01_3104').getSelectionModel().getSelection();
		var master={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				expNo:expNo,
				expType:sourceexpType
			},
			ownerNo:ownerNo,
			ownerCustNo:Ext.getCmp('ownerCustNo3104').getValue(),
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
			receiveTelephone:Ext.getCmp('receive_telephone3104').getValue(),
			receiveProvince:Ext.getCmp('receive_province3104').getValue(),
			receiveCompanyName:Ext.getCmp('receive_company_name3104').getValue(),
			receiveJpn:Ext.getCmp('receive_jpn3104').getValue(),
			receiveCity:Ext.getCmp('receive_city3104').getValue(),
			receiveZone:Ext.getCmp('receive_zone3104').getValue(),
			receiveCountry:Ext.getCmp('receive_country3104').getValue(),
			
			takeName:Ext.getCmp('take_name3104').getValue(),
			takeMobilePhone:Ext.getCmp('take_mobile_phone3104').getValue(),
			takeTelephone:Ext.getCmp('take_telephone3104').getValue(),
			takePostcode:Ext.getCmp('take_postcode3104').getValue(),
			takeCompanyName:Ext.getCmp('take_company_name3104').getValue(),
			takeJpn:Ext.getCmp('take_jpn3104').getValue(),
			takeAddress:Ext.getCmp('take_address3104').getValue(),
			takeProvince:Ext.getCmp('take_province3104').getValue(),
			takeCity:Ext.getCmp('take_city3104').getValue(),
			takeZone:Ext.getCmp('take_zone3104').getValue(),
			takeCountry:Ext.getCmp('take_country3104').getValue(),
			
			takeName:Ext.getCmp('take_name3104').getValue(),
			takeMobilePhone:Ext.getCmp('take_mobile_phone3104').getValue(),
			takeTelephone:Ext.getCmp('take_telephone3104').getValue(),
			takePostcode:Ext.getCmp('take_postcode3104').getValue(),
			takeCompanyName:Ext.getCmp('take_company_name3104').getValue(),
			takeJpn:Ext.getCmp('take_jpn3104').getValue(),
			takeAddress:Ext.getCmp('take_address3104').getValue(),
			takeProvince:Ext.getCmp('take_province3104').getValue(),
			takeCity:Ext.getCmp('take_city3104').getValue(),
			takeZone:Ext.getCmp('take_zone3104').getValue(),
			takeCountry:Ext.getCmp('take_country3104').getValue(),
			
			sendName:Ext.getCmp('send_name3104').getValue(),
			sendMobilePhone:Ext.getCmp('send_mobile_phone3104').getValue(),
			sendTelephone:Ext.getCmp('send_telephone3104').getValue(),
			sendPostcode:Ext.getCmp('send_postcode3104').getValue(),
			sendCompanyName:Ext.getCmp('send_company_name3104').getValue(),
			sendJpn:Ext.getCmp('send_jpn3104').getValue(),
			sendAddress:Ext.getCmp('send_address3104').getValue(),
			sendProvince:Ext.getCmp('send_province3104').getValue(),
			sendCity:Ext.getCmp('send_city3104').getValue(),
			sendZone:Ext.getCmp('send_zone3104').getValue(),
			sendCountry:Ext.getCmp('send_country3104').getValue(),
			skucount:'0'
		};
		var detail=[];
		for(var i=0;i<gridcount;i++){
			var exp=Ext.getCmp('grid_02_3104').getStore().getAt(i);
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
		disableButtonFunc(1,'#menu3104 [name=userSaveButton]','保存');
		var expM=Ext.encode(master);
		var expD=Ext.encode(detail);
		var params={
			expM:expM,
			expD:expD
		};
		if(saveType3104=='change'){
			Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_changeExp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
                    Ext.example.msg('提示', data.msg);
					Ext.getCmp('exp_no3104').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_3104').getStore().getAt(i);
						record.set('expNo',data.obj);
					}
					if(typeof(Ext.getCmp('grid_01_3104'))!=='undefined'){
						Ext.getCmp('grid_01_3104').store.reload();
					}
					commonEditButton('menu3104','save');
					isCanEdit3104=false;
					Ext.getCmp('toolbar3104').items.items[0].disable(true);
					Ext.getCmp('toolbar3104').items.items[1].disable(true);
					//7-13   点击保存之后，设为只读
					commonSetMsterReadOnlyByArray(
							new Array('exp_no3104','po_nomark3104','cust3104','last_custsend_Date3104',
							'custsend_Date3104','takeType3104',
							'owner_no3104','contactor_name3104','exp_type3104','cust_phone3104',
							'cust_email3104','cust_address3104','priority3104','fast_flag3104','sanpl_no3104',
							'exp_remark3104','deliver_no3104','Deliver_address3104','Print_bill_flag3104',
							'Order_source3104','orgNo3104','cust_exp_no3104'
							,'receive_telephone3104','receive_company_name3104','receive_jpn3104',
							'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
							,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
							'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
							'take_city3104','take_zone3104','take_country3104'
							,'send_name3104','send_mobile_phone3104','send_telephone3104',
							'send_postcode3104','send_company_name3104','send_jpn3104',
							'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
							,true);
				}else{
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
					disableButtonFunc(0,'#menu3104 [name=userSaveButton]','保存');
				}
			}
		});
		
		}else if(saveType3104=='add'){
			Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_saveExp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					//回写出货单头档品项数（改品项数指总数）
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
					Ext.getCmp('exp_no3104').setValue(data.obj);
					for(var i=0;i<gridcount;i++ ){
						var record  = Ext.getCmp('grid_02_3104').getStore().getAt(i);
						record.set('expNo',data.obj);
					}
				/*	if(typeof(Ext.getCmp('grid_01_3104'))!=='undefined'){
						Ext.getCmp('grid_01_3104').store.reload();
					}*/
					commonEditButton('menu3104','save');
					isCanEdit3104=false;
					Ext.getCmp('toolbar3104').items.items[0].disable(true);
					Ext.getCmp('toolbar3104').items.items[1].disable(true);
					disableButtonFunc(1,'#menu3104 [name=userSaveButton]','保存');
				}else{
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
				}
			}
		});
		}
	},
	
	del:function(){
		var expNo=Ext.String.trim(Ext.getCmp('exp_no3104').getValue());
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
							if(typeof(Ext.getCmp('grid_01_3104'))!="undefined"){
									
								var g=Ext.getCmp('grid_01_3104');
								var atindex=g.getStore().findExact('expNo',Ext.getCmp('exp_no3104').getValue());
								g.getStore().removeAt(atindex);
								var count=g.store.getCount();
									
								if(count==0){
									addExp3104();
								}else if(atindex==-1 && count!=0){
									editExp3104(0);
								}else if(atindex<count){
									editExp3104(atindex);
								}else {
									editExp3104(count-1);
								};
							};
						};
					}
				});
			};
		});
	},
	
	grid_02_3104beforeedit:function(e){
		if(!isCanEdit3104)
	    {
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	//校验商品是否重复，因电商支持一张单一个商品可以重复，所以把这段代码屏蔽
/*	grid_02_3104Edit:function(editor,e,eOpts){
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
		var gridcount=Ext.getCmp('grid_02_3104').getStore().getCount();	
		var lastDate=Ext.getCmp('grid_02_3104').getStore().getAt(gridcount-1);
		if(lastDate.get('articleNo')=='合计'){
		Ext.getCmp('grid_02_3104').getStore().remove(lastDate);
		}
		//校验该单是否定过位
	    var params={
	    		expNo:Ext.getCmp('exp_no3104').getValue()
			};
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_editExp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					commonEditButton('menu3104','edit');
					saveType3104='change';
					
					disableButtonFunc(1,'#menu3104 [name=userPrevButton]','上单');
					disableButtonFunc(1,'#menu3104 [name=userNextButton]','下单');
					//Ext.getCmp('toolbar3104').items.items[0].enable(true);
					//Ext.getCmp('toolbar3104').items.items[1].enable(true);
					//新增和删除两个按钮   7-11
					disableButtonFunc(0,'#toolbar3104 [name=detailAdd]','新增');
					disableButtonFunc(0,'#toolbar3104 [name=detailDelete]','删除');
					
					var data = Ext.getCmp('grid_01_3104').getSelectionModel().getSelection();
					if(data[0].data.createFlag=='0'){
						/*commonSetMsterReadOnlyByArray(
						new Array('contactor_name3104','cust_phone3104','last_custsend_Date3104','sanpl_no3104',
						'custsend_Date3104','cust_email3104','cust_address3104', 'exp_remark3104'
						,'receive_telephone3104','receive_company_name3104','receive_jpn3104',
						'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
						,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
						'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
						'take_city3104','take_zone3104','take_country3104'
						,'send_name3104','send_mobile_phone3104','send_telephone3104',
						'send_postcode3104','send_company_name3104','send_jpn3104',
						'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
						,false);*/
						//7-13添加
						commonSetMsterReadOnlyByArray(
								new Array('po_nomark3104','cust3104','last_custsend_Date3104',
								'custsend_Date3104','takeType3104',
								'owner_no3104','contactor_name3104','exp_type3104','cust_phone3104',
								'cust_email3104','cust_address3104','priority3104','fast_flag3104','sanpl_no3104',
								'exp_remark3104','deliver_no3104','Deliver_address3104','Print_bill_flag3104',
								'Order_source3104','orgNo3104','cust_exp_no3104'
								,'receive_telephone3104','receive_company_name3104','receive_jpn3104',
								'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
								,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
								'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
								'take_city3104','take_zone3104','take_country3104'
								,'send_name3104','send_mobile_phone3104','send_telephone3104',
								'send_postcode3104','send_company_name3104','send_jpn3104',
								'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
								,false);
					}
					//isCanEdit3104=false;
					isCanEdit3104=true;		//7-13
				}else{
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
					disableButtonFunc(0,'#menu3104 [name=userSaveButton]','保存');
				}
			}
		});
		
	},
	undo:function(){
		
		if(saveType3104=='change'){
			disableButtonFunc(0,'#menu3104 [name=userEditButton]','修改');	
			disableButtonFunc(0,'#menu3104 [name=userPrevButton]','上单');
			disableButtonFunc(0,'#menu3104 [name=userNextButton]','下单');
			var data = Ext.getCmp('grid_01_3104').getSelectionModel().getSelection();
			if(data.length!=0){
				editExp3104(data[0].index);
				rowindex3104=data[0].index;
				isCanEdit3104=false;
				commonEditButton('menu3104','dbclick');
			}
		}else{
			isCanEdit3104=false;
			addExp3104();
			Ext.getCmp('grid_02_3104').getStore().removeAll();
			commonSetMsterReadOnlyByArray(
			new Array('exp_no3104','po_nomark3104','cust3104','last_custsend_Date3104',
			'custsend_Date3104','takeType3104',
			'owner_no3104','contactor_name3104','exp_type3104','cust_phone3104',
			'cust_email3104','cust_address3104','priority3104','fast_flag3104','sanpl_no3104',
			'exp_remark3104','deliver_no3104','Deliver_address3104','Print_bill_flag3104',
			'Order_source3104','orgNo3104','cust_exp_no3104'
			,'receive_telephone3104','receive_company_name3104','receive_jpn3104',
			'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
			,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
			'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
			'take_city3104','take_zone3104','take_country3104'
			,'send_name3104','send_mobile_phone3104','send_telephone3104',
			'send_postcode3104','send_company_name3104','send_jpn3104',
			'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
			,true);
			Ext.getCmp('toolbar3104').items.items[0].disable(true);
			Ext.getCmp('toolbar3104').items.items[1].disable(true);
			commonEditButton('menu3104','undo');
			disableButtonFunc(1,'#menu3104 [name=userEditButton]','修改');	
		}
		
	},
	
	detailAdd:function(){
		var store = Ext.getCmp('grid_02_3104').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		if(j>=0){
			//7-13
			if(!commonCheckIsInputAll('form_01_3104'))
			{
				return;
			}
			
		}else{
			if(!commonCheckIsInputAll('form_01_3104'))
			{
				return;
			}
			commonSetMsterReadOnlyByArray(
			new Array('exp_no3104','po_nomark3104','cust3104',
			'last_custsend_Date3104','custsend_Date3104','takeType3104',
			'owner_no3104','contactor_name3104','exp_type3104','cust_phone3104',
			'cust_email3104','cust_address3104','priority3104','fast_flag3104','cust_exp_no3104',
			'sanpl_no3104','exp_remark3104','deliver_no3104','Deliver_address3104',
			'Print_bill_flag3104','Order_source3104','orgNo3104','receive_telephone3104','receive_company_name3104','receive_jpn3104',
			'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
			,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
			'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
			'take_city3104','take_zone3104','take_country3104'
			,'send_name3104','send_mobile_phone3104','send_telephone3104',
			'send_postcode3104','send_company_name3104','send_jpn3104',
			'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
			,true);
		}
		var r=Ext.create('cms.model.odata.odata_ExpDModel',{});
		r.set('expNo',Ext.getCmp('exp_no3104').getValue());
		store.add(r);
		Ext.getCmp('grid_02_3104').editingPlugin.startEdit(count,1);
	},
	
	detailDelete:function(){
		var data = Ext.getCmp('grid_02_3104').getSelectionModel()
			.getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm('提示','确定删除数据',function(button,text){
			if(button=='yes'){
				Ext.getCmp('grid_02_3104').getStore().remove(data);					
				if(Ext.getCmp("grid_02_3104").getStore().getCount()==0)
				{
					commonSetMsterReadOnlyByArray(
					new Array('exp_no3104','po_nomark3104','cust3104','takeType3104',
					'owner_no3104','contactor_name3104','exp_type3104','cust_phone3104',
					'cust_email3104','cust_address3104','priority3104','fast_flag3104',
					'exp_remark3104','sanpl_no3104','cust_exp_no3104')
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
		    	var data = Ext.getCmp('grid_02_3104').getSelectionModel().getSelection();
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
						    	var gridcount=Ext.getCmp('grid_02_3104').getStore().getCount();
						    	for(var i=0;i<gridcount;i++){
						    		var exp=Ext.getCmp('grid_02_3104').getStore().getAt(i);    		
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
		Ext.getCmp('grid_02_3104').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	
	packingQtyselect:function(combo){
		var data = Ext.getCmp('grid_02_3104').getSelectionModel().getSelection();
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
	
	cust3104Select:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'odata_ExpAction_queryCust',
			params:{
				strCustNo:combo.getValue()
		    },
		    success:function(response){

		    	var res = Ext.decode(response.responseText);
		    	Ext.getCmp('contactor_name3104').setValue(res[0].contactorName);
		    	Ext.getCmp('cust_phone3104').setValue(res[0].custPhone);
		    	Ext.getCmp('cust_email3104').setValue(res[0].custMail);
		    	Ext.getCmp('cust_address3104').setValue(res[0].custAddress);
		    	Ext.getCmp('ownerCustNo3104').setValue(res[0].ownerCustNo);
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
function addExp3104(){
	Ext.getCmp('form_01_3104').getForm().reset();
	Ext.getCmp('exp_no3104').setValue('保存时自动生成');
	if(Ext.getCmp('exp_type3104').getStore().data.length>0)
	{
		Ext.getCmp('exp_type3104').setValue(Ext.getCmp('exp_type3104').getStore().getAt(0).data.value);		
	}
	
	Ext.getCmp('takeType3104').setValue('0');
	
	Ext.getCmp('priority3104').setValue('100');
	Ext.getCmp('fast_flag3104').setValue('0');
	Ext.getCmp('Print_bill_flag3104').setValue('0');
	Ext.getCmp('Order_source3104').setValue('0');
	Ext.getCmp('orgNo3104').setValue('001');
//	Ext.getCmp('last_custsend_Date3104').setValue(new Date());
	Ext.getCmp('grid_02_3104').getStore().removeAll();
	if(Ext.getCmp('owner_no3104').getStore().data.length>0)
	{
		Ext.getCmp('owner_no3104').setValue(Ext.getCmp('owner_no3104').getStore().getAt(0).data.value);		
	}
	commonSetMsterReadOnlyByArray(
			new Array('exp_no3104','po_nomark3104','cust3104',
			'last_custsend_Date3104','custsend_Date3104','last_custsend_Date3104',
			'owner_no3104','contactor_name3104','exp_type3104','takeType3104','cust_phone3104',
			'cust_email3104','cust_address3104','priority3104','fast_flag3104',
			'exp_remark3104','cust_exp_no3104','sanpl_no3104','deliver_no3104','Deliver_address3104',
			'Print_bill_flag3104','Order_source3104','orgNo3104',
			'receive_telephone3104','receive_company_name3104','receive_jpn3104',
			'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
			,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
			'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
			'take_city3104','take_zone3104','take_country3104'
			,'send_name3104','send_mobile_phone3104','send_telephone3104',
			'send_postcode3104','send_company_name3104','send_jpn3104',
			'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
			,false);
	commonSetMsterReadOnlyByArray(
			new Array('exp_no3104','priority3104')
			,true);
			
	commonEditButton('menu3104','add');
	Ext.getCmp('toolbar3104').items.items[0].enable(true);
	Ext.getCmp('toolbar3104').items.items[1].enable(true);
	Ext.getCmp('po_nomark3104').focus();
	isCanEdit3104=true;
};

//编辑数据
function editExp3104(rowindex3104){
	if(rowindex3104==0)
	{
		Ext.getCmp('menu3104').items.items[0].disable(true);	
	}else{
		Ext.getCmp('menu3104').items.items[0].enable(true);
	}
	if(rowindex3104==Ext.getCmp('grid_01_3104').getStore().getCount()-1)
	{		
		Ext.getCmp('menu3104').items.items[1].disable(true);
	}else{		
		Ext.getCmp('menu3104').items.items[1].enable(true);
	}
	var record=Ext.getCmp('grid_01_3104').getStore().getAt(rowindex3104-(Ext.getCmp('grid_01_3104').getStore().currentPage-1)*appConfig.getPageSize());
	
	if(record.data.status=='10'){
		disableButtonFunc(0,'#menu3104 [name=userEditButton]','修改');	
	}else{
		disableButtonFunc(1,'#menu3104 [name=userEditButton]','修改');
	}
	
	Ext.getCmp('exp_no3104').setValue(record.data.expNo);
	Ext.getCmp('po_nomark3104').setValue(record.data.sourceexpNo);
	
	Ext.getCmp('cust3104').getStore().add({
    	value:record.data.custNo,
    	dropValue:'['+record.data.custNo+']'+record.data.custName,
    	text:record.data.custName
    });
	if(record.data.shipperNo != ''){
		 Ext.getCmp('sanpl_no3104').getStore().add({
		    	value:record.data.shipperNo,
		    	dropValue:'['+record.data.shipperNo+']'+record.data.shipperName,
		    	text:record.data.shipperName
		    });
	}
    Ext.getCmp('cust3104').setValue(record.data.custNo);
    Ext.getCmp('owner_no3104').setValue(record.data.ownerNo);
    Ext.getCmp('contactor_name3104').setValue(record.data.contactorName);
    Ext.getCmp('takeType3104').setValue(record.data.takeType);
    Ext.getCmp('exp_type3104').setValue(record.data.expType);
    Ext.getCmp('cust_phone3104').setValue(record.data.custPhone);
    Ext.getCmp('cust_email3104').setValue(record.data.custMail);
    Ext.getCmp('cust_address3104').setValue(record.data.custAddress);
    Ext.getCmp('priority3104').setValue(record.data.priority);
    Ext.getCmp('fast_flag3104').setValue(record.data.fastFlag);
    Ext.getCmp('exp_remark3104').setValue(record.data.expRemark);
    Ext.getCmp('sanpl_no3104').setValue(record.data.shipperNo);
    Ext.getCmp('deliver_no3104').setValue(record.data.shipperDeliverNo);
    Ext.getCmp('Deliver_address3104').setValue(record.data.deliverAddress);
    Ext.getCmp('Print_bill_flag3104').setValue(record.data.printBillFlag);
    Ext.getCmp('Order_source3104').setValue(record.data.orderSource);
    Ext.getCmp('orgNo3104').setValue(record.data.orgNo);
    Ext.getCmp('last_custsend_Date3104').setValue(record.data.lastCustsendDate);
    Ext.getCmp('custsend_Date3104').setValue(record.data.custsendDate);
    Ext.getCmp('cust_exp_no3104').setValue(record.data.custExpNo);
    
	Ext.getCmp('receive_telephone3104').setValue(record.data.receiveTelephone);
	Ext.getCmp('receive_province3104').setValue(record.data.receiveProvince);
	Ext.getCmp('receive_company_name3104').setValue(record.data.receiveCompanyName);
	Ext.getCmp('receive_jpn3104').setValue(record.data.receiveJpn);
	Ext.getCmp('receive_city3104').setValue(record.data.receiveCity);
	Ext.getCmp('receive_zone3104').setValue(record.data.receiveZone);
	Ext.getCmp('receive_country3104').setValue(record.data.receiveCountry);
	
	Ext.getCmp('take_name3104').setValue(record.data.takeName);
	Ext.getCmp('take_mobile_phone3104').setValue(record.data.takeMobilePhone);
	Ext.getCmp('take_telephone3104').setValue(record.data.takeTelephone);
	Ext.getCmp('take_postcode3104').setValue(record.data.takePostcode);
	Ext.getCmp('take_company_name3104').setValue(record.data.takeCompanyName);
	Ext.getCmp('take_jpn3104').setValue(record.data.takeJpn);
	Ext.getCmp('take_address3104').setValue(record.data.takeAddress);
	Ext.getCmp('take_province3104').setValue(record.data.takeProvince);
	Ext.getCmp('take_city3104').setValue(record.data.takeCity);
	Ext.getCmp('take_zone3104').setValue(record.data.takeZone);
	Ext.getCmp('take_country3104').setValue(record.data.takeCountry);
	
	Ext.getCmp('take_name3104').setValue(record.data.takeName);
	Ext.getCmp('take_mobile_phone3104').setValue(record.data.takeMobilePhone);
	Ext.getCmp('take_telephone3104').setValue(record.data.takeTelephone);
	Ext.getCmp('take_postcode3104').setValue(record.data.takePostcode);
	Ext.getCmp('take_company_name3104').setValue(record.data.takeCompanyName);
	Ext.getCmp('take_jpn3104').setValue(record.data.takeJpn);
	Ext.getCmp('take_address3104').setValue(record.data.takeAddress);
	Ext.getCmp('take_province3104').setValue(record.data.takeProvince);
	Ext.getCmp('take_city3104').setValue(record.data.takeCity);
	Ext.getCmp('take_zone3104').setValue(record.data.takeZone);
	Ext.getCmp('take_country3104').setValue(record.data.takeCountry);
	
	Ext.getCmp('send_name3104').setValue(record.data.sendName);
	Ext.getCmp('send_mobile_phone3104').setValue(record.data.sendMobilePhone);
	Ext.getCmp('send_telephone3104').setValue(record.data.sendTelephone);
	Ext.getCmp('send_postcode3104').setValue(record.data.sendPostcode);
	Ext.getCmp('send_company_name3104').setValue(record.data.sendCompanyName);
	Ext.getCmp('send_jpn3104').setValue(record.data.sendJpn);
	Ext.getCmp('send_address3104').setValue(record.data.sendAddress);
	Ext.getCmp('send_province3104').setValue(record.data.sendProvince);
	Ext.getCmp('send_city3104').setValue(record.data.sendCity);
	Ext.getCmp('send_zone3104').setValue(record.data.sendZone);
	Ext.getCmp('send_country3104').setValue(record.data.sendCountry);
    
    commonSetMsterReadOnlyByArray(
				new Array('exp_no3104','po_nomark3104','cust3104','takeType3104',
				'owner_no3104','contactor_name3104','exp_type3104','cust_phone3104',
				'cust_email3104','cust_address3104','priority3104','fast_flag3104','sanpl_no3104',
				'exp_remark3104','cust_exp_no3104','deliver_no3104','Deliver_address3104',
				'Print_bill_flag3104','Order_source3104','orgNo3104','receive_telephone3104','receive_company_name3104','receive_jpn3104',
				'receive_province3104','receive_city3104','receive_zone3104','receive_country3104'
				,'take_name3104','take_mobile_phone3104','take_telephone3104','take_postcode3104',
				'take_company_name3104','take_jpn3104','take_address3104','take_province3104',
				'take_city3104','take_zone3104','take_country3104'
				,'send_name3104','send_mobile_phone3104','send_telephone3104',
				'send_postcode3104','send_company_name3104','send_jpn3104',
				'send_address3104','send_province3104','send_city3104','send_zone3104','send_country3104')
				,true);
	var wheresql={
		wheresql:record.data.expNo
	};
	Ext.apply(Ext.getCmp('grid_02_3104').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_3104').getStore().removeAll();
	Ext.getCmp('grid_02_3104').getStore().load();		
};
