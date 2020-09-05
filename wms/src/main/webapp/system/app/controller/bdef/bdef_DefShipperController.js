/**
 * 模块名称：承运商资料维护
 * 模块编码：1E01
 * 创建：周欢
 */
var g_intRowindex1E01 = 0;
var g_strSaveType1E01 = 0;//0：新增；1：修改
Ext.define('cms.controller.bdef.bdef_DefShipperController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_DefShipperUI',
	          'cms.view.bdef.window.bdef_DefShipperAddorEditWindow'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//新增
			'bdef_DefShipperUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//修改
			'bdef_DefShipperUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			//浏览
			'bdef_DefShipperUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			//查找
			'bdef_DefShipperUI button[name=detailQuery]':{
				click:this.detailQuery
			},
			//导出
			'bdef_DefShipperUI button[name=detailExport]':{
				click:this.detailExport
			},
			//上一条记录
			'bdef_DefShipperAddorEditWindow button[name=prev]':{
				click:this.prev
			},
			//下一条记录
			'bdef_DefShipperAddorEditWindow button[name=next]':{
				click:this.next
			},
			//加载新增状态
			'bdef_DefShipperAddorEditWindow button[name=add]':{
				click:this.add
			},
			//保存
			'bdef_DefShipperAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bdef_DefShipperAddorEditWindow button[name=close]':{
				click:this.close
			},
			//验证承运商编号唯一性
			'bdef_DefShipperAddorEditWindow form textfield[id=txtShipperNo1E01]':{
				blur:this.shipperNoBlur
			},//网格选择
			'bdef_DefShipperUI grid[id=grid_01_1E01]':{
				selectionchange:this.grid_01_1E01Selectionchange
			},//移除关系
			'bdef_DefShipperUI button[id=left1E01]':{
				click:this.left1E01
			},//添加关系
			'bdef_DefShipperUI button[id=right1E01]':{
				click:this.right1E01
			}
		});
	},
	
	//新增
	detailAdd:function(){
		Ext.create('cms.view.bdef.window.bdef_DefShipperAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addbdef_shipper1E01();
		addCommMenu5('bdef_MenuWidget1E01');
		g_strSaveType1E01 = 0;
	},
	
	//修改
	detailEdit:function(){
		var objData=Ext.getCmp('grid_01_1E01').getSelectionModel().getSelection();
		if(objData.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			Ext.create('cms.view.bdef.window.bdef_DefShipperAddorEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			Ext.getCmp('txtShipperNo1E01').disable();
			g_intRowindex1E01 = objData[0].index;
			loadShipperData1E01(g_intRowindex1E01);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1E01','grid_01_1E01',g_intRowindex1E01);
			updateCommMenu5('bdef_MenuWidget1E01');
			g_strSaveType1E01 = 1;
		}
	},
	
	//浏览
	detailBrowse:function(){
		var objData = Ext.getCmp('grid_01_1E01').getSelectionModel().getSelection();
		if (objData.length != 0) {
			Ext.create('cms.view.bdef.window.bdef_DefShipperAddorEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
			g_intRowindex1E01 = objData[0].index;
			loadShipperData1E01(g_intRowindex1E01);
			commonSetFieldReadOnly('formBdef_DefShipperAddorEdit1E01',true);
			commonSetCommMenu5PrevOrNext('bdef_MenuWidget1E01','grid_01_1E01',g_intRowindex1E01);
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
        browseCommMenu5('bdef_MenuWidget1E01');
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1E01';
		queryGrid='grid_01_1E01';
	},
	
	detailExport:function(){
		commExport('grid_01_1E01');
	},
	
	prev:function(){
		g_intRowindex1E01=g_intRowindex1E01-1;
		loadShipperData1E01(g_intRowindex1E01);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1E01','grid_01_1E01',g_intRowindex1E01);
	},
	
	next:function(){
		g_intRowindex1E01=g_intRowindex1E01+1;
		loadShipperData1E01(g_intRowindex1E01);
		commonSetCommMenu5PrevOrNext('bdef_MenuWidget1E01','grid_01_1E01',g_intRowindex1E01);
	},
	
	//新增
	add:function(){
		Ext.getCmp('txtShipperNo1E01').enable();
		addbdef_shipper1E01();
		g_strSaveType1E01=0;
	},
	
	//保存
	save:function(){
		savebdef_defShipper1E01();
	},
	
	close:function(){
		closeCustWindow1E01();
	},
	
	//承运商编号校验
	shipperNoBlur:function(){
		if(g_strSaveType1E01 == 0){
			Ext.Ajax.request({
				method:'post',
				url:'bdef_DefShipperAction_checkShipperNo',
				params:{
					strShipperNo:Ext.getCmp('txtShipperNo1E01').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res == '1'){
			    		Ext.example.msg('提示','该承运商编号已有，请重新录入！');
			    		Ext.getCmp('txtShipperNo1E01').setValue('');
			    		Ext.getCmp('txtShipperNo1E01').focus();
			    	}
			    }
			});
		}
	},
	
	grid_01_1E01Selectionchange:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var strWheresql={
				strShipperNo:record.data.shipperNo
			};
			Ext.apply(Ext.getCmp('grid_03_1E01').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_03_1E01').getStore().removeAll();
			Ext.getCmp('grid_03_1E01').getStore().load();
		}else{
			Ext.getCmp('grid_03_1E01').getStore().removeAll();
		}
	},
	
	left1E01:function(){
		var strShipper=Ext.getCmp('grid_01_1E01').getSelectionModel().getSelection();
		var data = Ext.getCmp('grid_03_1E01').getSelectionModel().getSelection();
		if(strShipper.length!=0){
			if(data.length!=0){
				var detail1=[];
				var detail2=[];
				for(var i=0;i<data.length;i++){
					detail1.push(data[i].get('shipperNo'));
					detail2.push(data[i].get('lineNo'));
				};
				Ext.Ajax.request({
					url:'bdef_DefShipperAction_deleteShipperLine',
					params:{
						strShipperNo:detail1,
						strLineNo:detail2
					},
					success:function(response){
						Ext.getCmp('grid_03_1E01').getStore().load();
						Ext.getCmp('grid_02_1E01').getStore().load();
					}
				});
			}else{
				Ext.example.msg('提示','请先选中要编辑的行');
			}
		}else{
			Ext.example.msg('提示','请先选择一个承运商');
		}
	},
	
	right1E01:function(){
		var strShipper=Ext.getCmp('grid_01_1E01').getSelectionModel().getSelection();
		var data = Ext.getCmp('grid_02_1E01').getSelectionModel().getSelection();
		if(strShipper.length!=0){
			if(data.length!=0){
				var detail=[];
				for(var i=0;i<data.length;i++){
					var shipperLine={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							shipperNo:strShipper[0].get('shipperNo'),
							lineNo:data[i].get('lineNo')
						},
						rgstName:Ext.get('workerNo').getValue(),
						rgstDate:new Date()
					};
					detail.push(shipperLine);
				}
				var strDetail=Ext.encode(detail);
				Ext.Ajax.request({
					url:'bdef_DefShipperAction_insertShipperLine',
					params:{
						strDetail:strDetail
					},
					success:function(response){
						Ext.getCmp('grid_02_1E01').getStore().load();
						Ext.getCmp('grid_03_1E01').getStore().load();
					}
				});
			}else{
				Ext.example.msg('提示','请先选中要编辑的行');
			}
		}else{
			Ext.example.msg('提示','请先选择承运商');
		}
	}
	
});

//新增初始化
function addbdef_shipper1E01(){
	Ext.getCmp('formBdef_DefShipperAddorEdit1E01').getForm().reset();
	Ext.getCmp('cmbStatus1E01').setValue('1');
	Ext.getCmp('txtDisprice1E01').setValue(0);
	Ext.getCmp('txtGraprice1E01').setValue(0);
	Ext.getCmp('numMulti1E01').setValue(10);
	Ext.getCmp('numVolprice1E01').setValue(0);
	bindEnterSkip($('#formBdef_DefShipperAddorEdit1E01'));//调用键盘处理方法
	Ext.getCmp('txtShipperNo1E01').focus(false, 10);
};

//保存客户
function savebdef_defShipper1E01(){
	if(Ext.getCmp('formBdef_DefShipperAddorEdit1E01').getForm().isValid()){
		var strShipper = {
		    id:{
		    	enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo : Ext.get('warehouseNo').getValue(),
				shipperNo : Ext.getCmp('txtShipperNo1E01').getValue()
			},
			shipperName:Ext.getCmp('txtShipperName1E01').getValue(),
			address:Ext.getCmp('txtAddress1E01').getValue(),
			tel:Ext.getCmp('txtTel1E01').getValue(),
			contact:Ext.getCmp('txtContact1E01').getValue(),
			status:Ext.getCmp('cmbStatus1E01').getValue(),
			disprice:Ext.getCmp('txtDisprice1E01').getValue(),
			graprice:Ext.getCmp('txtGraprice1E01').getValue(),
			compactDate:Ext.getCmp('dateCompactDate1E01').getValue(),
			endDate:Ext.getCmp('dateEndDate').getValue(),
			multi:Ext.getCmp('numMulti1E01').getValue(),
			volprice:Ext.getCmp('numVolprice1E01').getValue(),
			memo:Ext.getCmp('txtMemo1E01').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(strShipper);
		Ext.Ajax.request({
			url:'bdef_DefShipperAction_saveOrUpdateShipper.action',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('grid_01_1E01').getStore().load();
					Ext.getCmp('txtShipperNo1E01').disable();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
};

//关闭客户窗口
function closeCustWindow1E01(){
	Ext.getCmp('bdef_DefShipperAddorEditWindow').close();
};

//填充数据
function loadShipperData1E01(g_intRowindex1E01){
	var shipper = Ext.getCmp('grid_01_1E01').getStore().
	getAt(g_intRowindex1E01-(Ext.getCmp('grid_01_1E01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('txtShipperNo1E01').setValue(shipper.data.shipperNo);
	Ext.getCmp('txtShipperName1E01').setValue(shipper.data.shipperName);
	Ext.getCmp('txtAddress1E01').setValue(shipper.data.address);
	Ext.getCmp('txtTel1E01').setValue(shipper.data.tel);
	Ext.getCmp('txtContact1E01').setValue(shipper.data.contact);
	Ext.getCmp('cmbStatus1E01').setValue(shipper.data.status);
	Ext.getCmp('txtDisprice1E01').setValue(shipper.data.disprice);
	Ext.getCmp('txtGraprice1E01').setValue(shipper.data.graprice);
	Ext.getCmp('dateCompactDate1E01').setValue(shipper.data.compactDate);
	Ext.getCmp('dateEndDate').setValue(shipper.data.endDate);
	Ext.getCmp('numVolprice1E01').setValue(String(shipper.data.volprice));
	Ext.getCmp('txtMemo1E01').setValue(shipper.data.memo);
};