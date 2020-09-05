/**
 * 模块名称：虚拟库存调整
 * 模块编码：D101
 * 创建：hcx
 */
var isCanEditD101=true;
var lotTypeD101;
Ext.define('cms.controller.stock.stock_AdjustAccountsController',{
	extend:'Ext.app.Controller',
	requires:[
		'cms.view.stock.stock_AdjustAccountsUI',
		'cms.view.stock.window.stock_NewitemWindow'
	],
	model:'',
	store:'',
	init:function(){
		this.control({//储位下拉选择
			'cdef_DefCellCombo[id=cellNoD101]':{
				select:this.cellNoD101select
			},//商品编码（内码/条码）下拉选择
			'bdef_DefArticleCombo[id=articleNoOwnerArticleNoBarcodeD101]':{
				select:this.article_select
			},//查询
			'stock_AdjustAccountsUI button[name=queryD101]':
				{
					click:this.btnQueryD101Click
				},//保存
			'stock_AdjustAccountsUI button[id=saveD101]':
				{
					click:this.btnSaveD101Click
				},//新增品项
			'stock_AdjustAccountsUI button[id=btnNewitemD101]':
				{
					click:this.btnNewitemD101Click
				},//刷新
			'stock_AdjustAccountsUI button[id=btnRefreshD101]':
				{
					click:this.btnRefreshD101Click
				},//设置光标跳到下一输入框
			'stock_NewitemWindow field':{
				specialkey:this.boxkeydown
			},//新增品项>>商品编码下拉选择
			'bdef_DefArticleCombo[id=cmbArticleNoD101]':{
				beforequery:this.article_noD101BeforeQuery,
				select:this.article_noselect
			},//包装选择
			'bdef_PackingQtyCombo[id=cmbPackingQtyD101]':{
				select:this.cmbPackingQtyD101Select
			},//生产日期选择
			'stock_NewitemWindow datefield[id=dateProduceDateD101]':
				{
					blur:this.dateProduceDateD101Change
				},//有效期选择
			'stock_NewitemWindow datefield[id=dateExpireDateD101]':
				{
					blur:this.dateExpireDateD101Change
				},//新增品项》保存
			'stock_NewitemWindow button[id=nSaveD101]':
				{
					click:this.newitemAdd
				},//关闭
			'stock_NewitemWindow button[id=btnCloseD101]':
				{
					click:this.close
				}
		});
	},

	initializtion:function(){
		Ext.getCmp('cellNoD101').focus(false, 10);
		disableButtonFunc(1,'#saveD101',$i18n.save);
		disableButtonFunc(1,'#btnNewitemD101',$i18n.newitem);

		//显示变量0为不显示，1为显示
		var planBox_D101=commonGetModuleField('D101','planBox')[0].flag;
		var planQmin_D101=commonGetModuleField('D101','planQmin')[0].flag;
		var planDis_D101=commonGetModuleField('D101','planDis')[0].flag;
		var packingUnit_D101=commonGetModuleField('D101','packingUnit')[0].flag;
		var packingSpec_D101=commonGetModuleField('D101','packingSpec')[0].flag;
		var realBox_D101=commonGetModuleField('D101','realBox')[0].flag;
		var realQmin_D101=commonGetModuleField('D101','realQmin')[0].flag;
		var realDis_D101=commonGetModuleField('D101','realDis')[0].flag;

		if(planBox_D101==0){
			Ext.getCmp('planBox_D101').setVisible(false);
		}
		if(planQmin_D101==0){
			Ext.getCmp('planQmin_D101').setVisible(false);
		}
		if(planDis_D101==0){
			Ext.getCmp('planDis_D101').setVisible(false);
		}
		if(packingUnit_D101==0){
			Ext.getCmp('packingUnit_D101').setVisible(false);
		}
		if(packingSpec_D101==0){
			Ext.getCmp('packingSpec_D101').setVisible(false);
		}
		if(realBox_D101==0){
			Ext.getCmp('realBox_D101').setVisible(false);
		}
		if(realQmin_D101==0){
			Ext.getCmp('realQmin_D101').setVisible(false);
		}
		if(realDis_D101==0){
			Ext.getCmp('realDis_D101').setVisible(false);
		}


	},
	//储位选择
	cellNoD101select:function(){
		if(!commonCheckIsInputAll('form_01_D101'))
		{
			Ext.getCmp('cellNoD101').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('ownerD101').getValue())){
			var ownerNo = Ext.getCmp('ownerD101').getValue();
			if(!Ext.isEmpty(Ext.getCmp('cellNoD101').getValue())){
				var detail1 = [];
				var d={
					columnId:'t3.cell_no',
					value:Ext.getCmp('cellNoD101').getValue()
				};
				detail1.push(d);
				var jsonDetail1 = Ext.encode(detail1);
				var params = {
					strQuery : jsonDetail1,
					strOwnerNo : ownerNo
				};
				Ext.apply(Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getStore().proxy.extraParams,params);
				Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getStore().load();
				Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').setValue('');
				Ext.getCmp('productDateAndLotNoD101').setValue(null);
				Ext.getCmp('gridD101').getStore().removeAll();
				disableButtonFunc(0,'#btnNewitemD101',$i18n.newitem);
				disableButtonFunc(0,'#saveD101',$i18n.save);
			}
		}
	},
	//商品编码下拉选择
	article_select:function(){
		if(!commonCheckIsInputAll('form_01_D101'))
		{
			Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('ownerD101').getValue())){
			var ownerNo = Ext.getCmp('ownerD101').getValue();
			if(!Ext.isEmpty(Ext.getCmp('cellNoD101').getValue())){
				var detail1 = [];
				var d={
					columnId:'t2.cell_no',
					value:Ext.getCmp('cellNoD101').getValue()
				};
				detail1.push(d);
				if(!Ext.isEmpty(Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getValue())){
					var d={
						columnId:'t2.article_no',
						value:Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getValue()
					};
					detail1.push(d);
					var jsonDetail1 = Ext.encode(detail1);
					var params = {
						strQuery : jsonDetail1,
						strOwnerNo : ownerNo
					};
					Ext.apply(Ext.getCmp('productDateAndLotNoD101').getStore().proxy.extraParams,params);
					Ext.getCmp('productDateAndLotNoD101').getStore().removeAll();
					Ext.getCmp('productDateAndLotNoD101').setValue(null);
					Ext.getCmp('productDateAndLotNoD101').getStore().load();
					Ext.getCmp('gridD101').getStore().removeAll();
				}
			}
		}
	},
	//查询
	btnQueryD101Click:function(){
		if(!commonCheckIsInputAll('form_01_D101'))
		{
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('ownerD101').getValue())){
			var ownerNo = Ext.getCmp('ownerD101').getValue();
			if(!Ext.isEmpty(Ext.getCmp('cellNoD101').getValue())){
				var detail1 = [];
				var d={
					columnId:'v.cell_no',
					value:Ext.getCmp('cellNoD101').getValue()
				};
				detail1.push(d);
				if(!Ext.isEmpty(Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getValue())){
					var d={
						columnId:'v.article_no',
						value:Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getValue()
					};
					detail1.push(d);
					if(!Ext.isEmpty(Ext.getCmp('productDateAndLotNoD101').getValue())){
						var strs= new Array();
						strs=Ext.getCmp('productDateAndLotNoD101').getValue().split(",");
						var d={
							columnId:"to_char(v.produce_date,'yyyy-mm-dd')",
							value:strs[0]
						};
						detail1.push(d);
						var d={
							columnId:'v.lot_no',
							value:strs[1]
						};
						detail1.push(d);
					}
				}
				var jsonDetail1 = Ext.encode(detail1);
				var params = {
					strQuery : jsonDetail1,
					strOwnerNo : ownerNo
				};
				Ext.apply(Ext.getCmp('gridD101').getStore().proxy.extraParams,params);
				Ext.getCmp('gridD101').getStore().removeAll();
				Ext.getCmp('gridD101').getStore().reload();
			}
		}
	},
	//保存
	btnSaveD101Click:function(){

		var gridcount=Ext.getCmp('gridD101').getStore().getCount();
		if(gridcount==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.selectOrAdd);
			return;
		}
		var master={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				adjNo:'N',
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:Ext.getCmp('ownerD101').getValue()
			},
			adjType :0,
			poNo:'',
			adjDate:new Date(),
			status:10,
			createFlag:0,
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:'',
			updtDate:'',
			sendFlag:10
		};
		var detail=[];
		var a=[];
		for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('gridD101').getStore().getAt(i);
			var planQty = record.get('planQty');
			var realQty = record.get('realQty');
			if(planQty==realQty){
				a.push(planQty);
			}
			if(planQty!=realQty){
				var quality=record.get('quality');
				if(quality==$i18n.goods){
					quality='0';
				}
				if(quality==$i18n.QA){
					quality='A';
				}
				if(quality==$i18n.DM){
					quality='B';
				}
				var d={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						adjNo:'N',
						warehouseNo:Ext.get('warehouseNo').getValue(),
						rowId:i
					},
					ownerNo:Ext.getCmp('ownerD101').getValue(),
					articleNo:record.get('articleNo'),
					articleId:'',
					packingQty:record.get('packingQty'),
					produceDate:record.get('produceDate'),
					expireDate:record.get('expireDate'),
					quality:quality,
					lotNo:record.get('lotNo'),
					importBatchNo:'',
					rsvBatch1:'N',
					rsvBatch2:'N',
					rsvBatch3:'N',
					rsvBatch4:'N',
					rsvBatch5:'N',
					rsvBatch6:'N',
					rsvBatch7:'N',
					rsvBatch8:'N',
					cellNo:record.get('cellNo'),
					planQty:planQty,
					realQty:realQty,
					adjDes:'',
					barcode:record.get('barcode'),
					stockType:1,
					stockValue:'N',
					labelNo:'N',
					status:10
				};
				detail.push(d);
			}
		};
		var M=Ext.encode(master);
		var D=Ext.encode(detail);

		var params={
			jsonMaster:M,
			jsonDetail:D
		};
		if(a.length==gridcount){
			Ext.example.msg($i18n.prompt,$i18n_prompt.planQtyAndRearQty);
			return;
		}
		if(a.length>0){
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.planQtyEqualRearQty,function(button, text){
				if (button == 'yes') {
					Ext.Ajax.request({
						method:'post',
						url:'stock_AdjustAccountsAction_save',
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('gridD101').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg);
							}
						}
					});
				}
				if (button == 'no') {
					return;
				}
			});
		}
		if(a.length==0){

			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,function(button, text){
				if (button == 'yes') {
					Ext.Ajax.request({
						method:'post',
						url:'stock_AdjustAccountsAction_save',
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('gridD101').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg);
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
	//新增品项
	btnNewitemD101Click:function()
	{
		if(!commonCheckIsInputAll('form_01_D101'))
		{
			return;
		}
		Ext.create('cms.view.stock.window.stock_NewitemWindow',{
			title:$i18n.titleNewitem//新增品项
		}).show();
		Ext.getCmp('cmbArticleNoD101').focus(false, 10);
		Ext.getCmp('cmbCellNoD101').setValue(Ext.getCmp('cellNoD101').getValue());
		Ext.getCmp('cmbQualityD101').setValue('0');
	},
	//刷新
	btnRefreshD101Click:function(){
		Ext.getCmp('ownerD101').getStore().reload();
		Ext.getCmp('cellNoD101').setValue('');
		Ext.getCmp('cellNoD101').getStore().reload();
		Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').getStore().removeAll();
		Ext.getCmp('articleNoOwnerArticleNoBarcodeD101').setValue('');
		Ext.getCmp('productDateAndLotNoD101').getStore().removeAll();
		Ext.getCmp('productDateAndLotNoD101').setValue('');
		Ext.getCmp('gridD101').getStore().removeAll();
		Ext.getCmp('cellNoD101').focus(false, 10);
		disableButtonFunc(1,'#saveD101',$i18n.save);
		disableButtonFunc(1,'#btnNewitemD101',$i18n.newitem);
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) {
			if(th.id=='cmbArticleNoD101'){
				Ext.getCmp('cmbPackingQtyD101').focus();
			}else if(th.id=='cmbPackingQtyD101'){
				Ext.getCmp('numPoBoxD101').focus();
			}else if(th.id=='txtSubLabelNoD101'){
				Ext.getCmp('dateProduceDateD101').focus();
			}else{
				th.nextSibling().focus();
			}
		}
	},
	//新增品项》商品加载前
	article_noD101BeforeQuery:function(){
		var listDetail1  =  [];
		var strDtl = {
			columnId:'t1.owner_no',
			value:Ext.getCmp("ownerD101").getValue()
		};
		listDetail1.push(strDtl);
		var strWheresql={
			strQuery:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('cmbArticleNoD101').getStore().proxy.extraParams,strWheresql);
	},
	//新增品项》商品编码下拉
	article_noselect:function(combo){
		Ext.Ajax.request({
			method:'post',
			url:'stock_AdjustAccountsAction_queryArticleInfo',
			params:{
				strWheresql:combo.getValue(),
				strOwnerNo:Ext.getCmp('ownerD101').getValue()
			},
			success:function(response){
				var res = Ext.decode(response.responseText);
				Ext.getCmp('txtBarcodeD101').setValue(res[0].barcode);
				Ext.getCmp('txtArticleNameD101').setValue(res[0].articleName);
				Ext.getCmp('guaranteeD101').setValue(res[0].guarantee);
				lotTypeD101 = res[0].lotType;
				if(lotTypeD101 == '1'){
					Ext.getCmp('dateProduceDateD101').setValue('1900-01-01');
					Ext.getCmp('dateExpireDateD101').setValue('1900-01-01');
					Ext.getCmp('dateProduceDateD101').setReadOnly(true);
					Ext.getCmp('dateExpireDateD101').setReadOnly(true);
					Ext.getCmp('txtLotNoD101').setValue('N');
					txtLotNoD101();
				}else if(lotTypeD101 == '2'){
					Ext.getCmp('dateProduceDateD101').setValue('');
					Ext.getCmp('dateExpireDateD101').setValue('');
					Ext.getCmp('dateProduceDateD101').setReadOnly(false);
					Ext.getCmp('dateExpireDateD101').setReadOnly(false);

					Ext.getCmp('txtLotNoD101').getStore().removeAll();
					var model = {'lotNo':'N'};
					Ext.getCmp('txtLotNoD101').getStore().add(model);

					Ext.getCmp('txtLotNoD101').setValue('N');
					Ext.getCmp('txtLotNoD101').setReadOnly(true);
				}else if(lotTypeD101 == '3'){
					Ext.getCmp('dateProduceDateD101').setValue('');
					Ext.getCmp('dateExpireDateD101').setValue('');
					Ext.getCmp('txtLotNoD101').setValue('');
					Ext.getCmp('dateProduceDateD101').setReadOnly(false);
					Ext.getCmp('dateExpireDateD101').setReadOnly(true);
					Ext.getCmp('txtLotNoD101').setReadOnly(true);
				}else if(lotTypeD101 == '4'){
					Ext.getCmp('dateProduceDateD101').setValue('1900-01-01');
					Ext.getCmp('dateExpireDateD101').setValue('1900-01-01');
					Ext.getCmp('dateProduceDateD101').setReadOnly(true);
					Ext.getCmp('dateExpireDateD101').setReadOnly(true);
					Ext.getCmp('txtLotNoD101').getStore().removeAll();

					var model = {'lotNo':'N'};
					Ext.getCmp('txtLotNoD101').getStore().add(model);

					Ext.getCmp('txtLotNoD101').setValue('N');
					Ext.getCmp('txtLotNoD101').setReadOnly(true);
				}
				Ext.getCmp('cmbPackingQtyD101').getStore().proxy.extraParams.strWheresql =combo.getValue();
				Ext.getCmp('cmbPackingQtyD101').getStore().removeAll();
				Ext.getCmp('cmbPackingQtyD101').getStore().load();

				Ext.Ajax.request({
					method:'post',
					url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
					params:{
						strArticleNo:Ext.getCmp('cmbArticleNoD101').getValue(),
						strPackingQty:res[0].packingQty
					},
					success:function(response){
						var r = Ext.decode(response.responseText);
						Ext.getCmp('txtPackingUnitD101').setValue(r[0].packingUnit);
						Ext.getCmp('txtSpecD101').setValue(r[0].spec);
						Ext.getCmp('numPoBoxD101').setValue(0);
					}
				});
			}
		});
	},
	//包装选择
	cmbPackingQtyD101Select:function(combo)
	{
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
			params:{
				strArticleNo:Ext.getCmp('cmbArticleNoD101').getValue(),
				strPackingQty:combo.getValue()
			},
			success:function(response){
				var res = Ext.decode(response.responseText);
				Ext.getCmp('txtPackingUnitD101').setValue(res[0].packingUnit);
				Ext.getCmp('txtSpecD101').setValue(res[0].spec);
				Ext.getCmp('numPoBoxD101').setValue(0);
			}
		});
	},
	//生产日期选择
	dateProduceDateD101Change:function(th)
	{

		if(!Ext.isEmpty(th.getValue()) && Ext.getCmp('guaranteeD101').getValue()!=-1)
		{
			Ext.getCmp('dateExpireDateD101').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, Ext.getCmp('guaranteeD101').getValue()*1),'Y-m-d'));
		}

		if(lotTypeD101 == '3'){

			if(!Ext.isEmpty(Ext.getCmp('dateProduceDateD101').getValue())){
				txtLotNoD101();
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.inputProductDate);
			}
		}
	},
	//有效期至选择
	dateExpireDateD101Change:function()
	{
		if(!Ext.isEmpty(th.getValue()) && Ext.getCmp('guaranteeD101').getValue()!=-1)
		{
			Ext.getCmp('dateProduceDateD101').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, Ext.getCmp('guaranteeD101').getValue()*-1),'Y-m-d'));
		}
		if(!Ext.isEmpty(Ext.getCmp('dateProduceDateD101').getValue())){
			txtLotNoD101();
		}
	},
	//新增品项》保存
	newitemAdd:function(th)
	{
		var d = Ext.create('cms.model.stock.stock_ADjDModel', {});
		var ownerNo=Ext.getCmp('ownerD101').getValue();
		var cellNo=Ext.getCmp('cmbCellNoD101').getValue();
		var articleNo=Ext.getCmp('cmbArticleNoD101').getValue();
		var barcode=Ext.getCmp('txtBarcodeD101').getValue();
		var articleName=Ext.getCmp('txtArticleNameD101').getValue();
		var packingQty=Ext.getCmp('cmbPackingQtyD101').getValue();
		var packingUnit=Ext.getCmp('txtPackingUnitD101').getValue();
		var numPoBox=Ext.getCmp('numPoBoxD101').getValue();
		var planQty='0';
		var realQty=numPoBox*packingQty;
		var produceDate=Ext.Date.format( Ext.getCmp('dateProduceDateD101').getValue(),'Y-m-d');
		var expireDate=Ext.Date.format( Ext.getCmp('dateExpireDateD101').getValue(),'Y-m-d');
		var quality=Ext.getCmp('cmbQualityD101').getValue();
		if(quality=='0'){
			quality=$i18n.goods;
		}
		if(quality=='A'){
			quality=$i18n.QA;
		}
		if(quality=='B'){
			quality=$i18n.DM;
		}
		var lotNo=Ext.getCmp('txtLotNoD101').getValue();
		if(articleNo==null){Ext.example.msg($i18n.prompt,$i18n_prompt.inputArticleNoOrBarcode);
			return;}
		if(packingQty==null){Ext.example.msg($i18n.prompt,$i18n_prompt.choicePakingQty);
			return;}
		if(numPoBox==null){Ext.example.msg($i18n.prompt,$i18n_prompt.inputBoxQty);
			return;}
		if(numPoPsc==null){Ext.example.msg($i18n.prompt,$i18n_prompt.inputRoolQty);
			return;}
		if(produceDate==''||produceDate==null){Ext.example.msg($i18n.prompt,$i18n_prompt.inputProductDate);
			return;}
		if(expireDate==''||expireDate==null){Ext.example.msg($i18n.prompt,$i18n_prompt.inputEndDate);
			return;}
		if(lotNo==''||lotNo==null){Ext.example.msg($i18n.prompt,$i18n_prompt.inputLotNo);
			return;}
		if(numPoBox=='0' && numPoPsc=='0'){Ext.example.msg($i18n.prompt,$i18n_prompt.inputQty);
			return;}
		var gridcount=Ext.getCmp('gridD101').getStore().getCount();
		if(gridcount>0){
			for(var i=0;i<gridcount;i++){
				var record = Ext.getCmp('gridD101').getStore().getAt(i);
				if(record.get('cellNo')== cellNo
					&& record.get('articleNo')== articleNo
					&& record.get('packingQty')== packingQty
					&& record.get('produceDate')== produceDate
					&& record.get('expireDate')== expireDate
					&& record.get('lotNo')== lotNo){
					Ext.example.msg($i18n.prompt,$i18n_prompt.check);
					return;
				}
			}
		}
		Ext.Ajax.request({
			method:'post',
			url:'stock_AdjustAccountsAction_getOwnerArticleNO',
			params:{
				articleNo:articleNo,
				strOwnerNo:ownerNo
			},
			success:function(response){
				var res = Ext.decode(response.responseText);
				var ownerArticleNo=res[0].ownerArticleNo;
				d.set('cellNo',cellNo);
				d.set('articleNo',articleNo);
				d.set('ownerArticleNo',ownerArticleNo);
				d.set('barcode',barcode);
				d.set('articleName',articleName);
				d.set('packingQty',packingQty);
				d.set('packingUnit',packingUnit);
				d.set('planQty',planQty);
				d.set('realQty',realQty);
				d.set('produceDate',produceDate);
				d.set('expireDate',expireDate);
				d.set('quality',quality);
				d.set('lotNo',lotNo);
				Ext.getCmp("gridD101").getStore().add(d);
				Ext.getCmp('stock_NewitemWindow').close();
			}
		});
	},
	//新增品项》关闭
	close:function()
	{
		Ext.getCmp('stock_NewitemWindow').close();
	}
});
//根据商品编码和生产日期获取批号
function txtLotNoD101(){
	var articleNo = Ext.getCmp('cmbArticleNoD101').getValue();
	var produceDate = Ext.getCmp('dateProduceDateD101').getValue();
	var strWheresql={
		articleNo:articleNo,
		produceDate:Ext.Date.format(produceDate,'Y/m/d')
	};
	Ext.apply(Ext.getCmp('txtLotNoD101').getStore().proxy.extraParams,strWheresql);
	Ext.getCmp('txtLotNoD101').getStore().load();
	Ext.getCmp('dateExpireDateD101').setReadOnly(false);
	Ext.getCmp('txtLotNoD101').setReadOnly(false);

};
