/**
 * 模块名称：仓库
 * 模块编码：2101
 * 创建：lich 
 */
var rowindex2101=0;
var but2101='';
var selectCellNo2101='N';
var color_r2101=255;
var	color_g2101=255;
var color_b2101=255;
Ext.define('cms.controller.cdef.cdef_DefWareController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cdef.cdef_DefWareUI'],
	model:'cms.model.cdef.cdef_DefWareModel',
	store:'',
	init:function(){
		this.control({//仓库维护》新增
			'cdef_DefWareUI button[name=detailAdd]':{
				click:this.detailAdd
			},//仓库维护》修改
			'cdef_DefWareUI button[name=detailEdit]':{
				click:this.detailEdit
			},//仓库维护》浏览
			'cdef_DefWareUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//仓库维护》删除`
			'cdef_DefWareUI button[name=detailDelete]':{
				click:this.detailDelete
			},//仓库维护》导出
			'cdef_DefWareUI button[name=detailExport]':{
				click:this.detailExport
			},//仓库维护》tab切换
			'cdef_DefWareUI tabpanel[id=tab2101]':{
				tabchange:this.tabchange
			},//仓库维护》仓库》上一条记录
			'cdef_DefWareAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			//仓库维护》仓库》下一下记录
			'cdef_DefWareAddOrEditWindow button[name=next]':{
				click:this.next
			},
			//仓库维护》储位》校验新增储区是否重复
			'cdef_DefWareAddOrEditWindow textfield[id=ware_No2101]':{
				blur:this.wareNoBlur
			},
			//仓库维护》仓库》新增
			'cdef_DefWareAddOrEditWindow button[name=add]':{
				click:this.add
			},//仓库维护》仓库》保存
			'cdef_DefWareAddOrEditWindow button[name=save]':{
				click:this.save
			},//仓库维护》仓库》关闭
			'cdef_DefWareAddOrEditWindow button[name=close]':{
				click:this.close
			},
			//仓库维护》仓库》网格双击
			'cdef_DefWareUI grid[id=cdef_DefWareGrid2101]':{
				itemdblclick:this.detailBrowse
			},
	     //////////////////////////////////////////储区//////////////////////////////////////
			//选择库区加载储区
			'cdef_DefWareUI cdef_DefWareCombo[id=d3_wareno2101]':{
				select:this.d3_wareno2101change
			},
			//仓库维护》储区》上一条记录
			'cdef_DefAreaAddOrEditWindow button[name=prev]':{
				click:this.prevCdef_DefArea
			},//仓库维护》储区》下一条记录
			'cdef_DefAreaAddOrEditWindow button[name=next]':{
				click:this.nextCdef_DefArea
			},
			//仓库维护》储区》新增
			'cdef_DefAreaAddOrEditWindow button[name=add]':{
				click:this.addCdef_DefArea
			},//仓库维护》储区》保存
			'cdef_DefAreaAddOrEditWindow button[name=save]':{
				click:this.saveCdef_DefArea
			},//仓库维护》储区》关闭
			'cdef_DefAreaAddOrEditWindow button[name=close]':{
				click:this.closeCdef_DefArea
			},
			//仓库维护》储区》网格双击
			'cdef_DefWareUI grid[id=cdef_DefAreaGrid2101]':{
				itemdblclick:this.detailBrowse
			},
			//仓库维护》储区》储区用途选择
			'cdef_DefAreaAddOrEditWindow wms_DefFieldValCombo[id=d2_area_usetype2101]':{
				change:this.d2_area_usetype2101Change
			},
			//仓库维护》储区》储区属性选择
			'cdef_DefAreaAddOrEditWindow wms_DefFieldValCombo[id=d2_area_attribute2101]':{
				change:this.d2_area_attribute2101Change
			},
			//仓库维护》储区》限制入库类型选择选择
			'cdef_DefAreaAddOrEditWindow wms_DefFieldValCombo[id=d2_limit_type2101]':{
				change:this.d2_limit_type2101Change
			},
			//仓库维护》储区》下架方式选择
			'cdef_DefAreaAddOrEditWindow wms_DefFieldValCombo[id=d2_o_type2101]':{
				change:this.dd2_o_type2101Change
			},
			//仓库维护》储位》校验新增储区是否重复已经储区编码长度
			'cdef_DefAreaAddOrEditWindow textfield[id=d2_area_no2101]':{
				blur:this.areaNoBlur
			},
	        //////////////////////////////储位////////////////////////////////////////////////
			//选择库区（UI）
			'cdef_DefWareUI cdef_DefWareCombo[id=d4_wareno2101]':{
				select:this.d4_wareno2101change
			},//选择储区（UI）
			'cdef_DefWareUI cdef_DefAreaCombo[id=d4_areano2101]':{
				select:this.d4_areano2101change
			},//选择通道（UI）
			'cdef_DefWareUI cdef_DefStockCombo[id=d4_stock2101]':{
				select:this.d4_stock2101change
			},//改变格(UI)
			'cdef_DefWareUI combo[id=d4_stockX2101]':{
				change:this.d4_stockX2101change
			},//改变位(UI)
			'cdef_DefWareUI combo[id=d4_bayX2101]':{
				change:this.bayX_stockYchange
			},//改变层(UI)
			'cdef_DefWareUI combo[id=d4_stockY2101]':{
				change:this.bayX_stockYchange
			},//禁用储位
			'cdef_DefWareUI button[id=forbidBut2101]':{
				click:this.forbidBut2101
			},//冻结储位
			'cdef_DefWareUI button[id=freezeBut2101]':{
				click:this.freezeBut2101
			},//解禁储位
			'cdef_DefWareUI button[id=useBut2101]':{
				click:this.useBut2101
			},
			
			//仓库维护》储位》修改保存
			'cdef_DefCellAddOrEditWindow button[name=save]':{
				click:this.saveCdef_DefCell
			},//仓库维护》储位》修改框关闭
			'cdef_DefCellAddOrEditWindow button[name=close]':{
				click:this.closeCdef_DefCell
			},//选择库区（window）
			'cdef_DefStockAndCellAddOrEditWindow cdef_DefWareCombo[id=wareno2101]':{
				select:this.wareno2101change
			},//选择储区（window）
			'cdef_DefStockAndCellAddOrEditWindow cdef_DefAreaCombo[id=areano2101]':{
				select:this.areano2101change
			},//生成储位
			'cdef_DefStockAndCellAddOrEditWindow button[name=save]':{
				click:this.produceCell
			},//关闭
			'cdef_DefStockAndCellAddOrEditWindow button[name=close]':{
				click:this.closeStockAndCellAddOrEditWindow
			},//新增储位（window）
			'cdef_DefStockAndCellAddOrEditWindow button[name=add]':{
				click:this.addStockAndCellAddOrEditWindow
			},
			////////////////////////////////////////////////////////////////
			//储位变化
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=stockMin]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=stockMax]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=cellMin]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=cellMax]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=bayXMin]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=bayXMax]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=floorMin]':{
				blur:this.changeStockAndCell
			},
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=floorMax]':{
				blur:this.changeStockAndCell
			},
			
			//点缀改变
			'cdef_DefStockAndCellAddOrEditWindow wms_DefFieldValCombo[id=perf1]':{
				change:this.showCell
			},
			'cdef_DefStockAndCellAddOrEditWindow wms_DefFieldValCombo[id=perf2]':{
				change:this.showCell
			},
			'cdef_DefStockAndCellAddOrEditWindow wms_DefFieldValCombo[id=perf3]':{
				change:this.showCell
			},
			'cdef_DefStockAndCellAddOrEditWindow wms_DefFieldValCombo[id=perf4]':{
				change:this.showCell
			},
			
			
			'cdef_DefStockAndCellAddOrEditWindow textfield[id=perfix]':{
				change:this.showCell
			},//储位导入
			'cdef_DefWareUI button[name=detailImport]':{
				click:this.importDefcell
			},
			//////////////////////////////货位信息查询////////////////////////////////////////////////
			//选择货位（UI）
			'cdef_DefWareUI cdef_DefCellCombo[id=cell_no2101_d5]':{
				select:this.cell_no2101_d5change
			},
			//////////////////////////////转区////////////////////////////////////////////////
			
			//选择库区（UI）
			'cdef_DefWareUI cdef_DefWareCombo[id=d6_wareno2101]':{
				select:this.d6_wareno2101change
			},//选择储区（UI）
			'cdef_DefWareUI cdef_DefAreaCombo[id=d6_areano2101]':{
				select:this.d6_areano2101change
			},//选择通道（UI）
			'cdef_DefWareUI combo[id=d6_stock2101]':{
				change:this.d6_stock2101change
			},//改变格(UI)
			'cdef_DefWareUI combo[id=d6_stockX2101]':{
				change:this.d6_stockX2101change
			},//改变位(UI)
			'cdef_DefWareUI combo[id=d6_bayX2101]':{
				change:this.d6_bayX_stockYchange
			},//改变层(UI)
			'cdef_DefWareUI combo[id=d4_stockY2101]':{
				change:this.d6_bayX_stockYchange
			},	//转区
			'cdef_DefWareUI button[id=updateBut2101]':{
				click:this.updateBut2101
			},//选择库区2（UI）
			'cdef_DefWareUI cdef_DefWareCombo[id=d6_wareno2101_2]':{
				select:this.d6_wareno2101_2change
			}
		});
	},
	///////////////////////////////////////////////////////////
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		selectCellNo2101="N";
	},
	//仓库维护》储区》下架方式选择
	dd2_o_type2101Change:function()
	{
		var OType = Ext.getCmp('d2_o_type2101').getValue();
		if(OType == 'B')
		{
			Ext.getCmp('d2_b_divide_flag2101').setValue('0');
			Ext.getCmp('d2_b_divide_flag2101').setDisabled(false);
		}else
		{
			Ext.getCmp('d2_b_divide_flag2101').setDisabled(true);
		}
	},
	
	//仓库维护》储区》限制入库类型选择选择
	d2_limit_type2101Change:function(th,newValue,oldValue,eOpts)
	{
		if(newValue=='0')
		{
			Ext.getCmp('d2_limit_rate2101').setFieldLabel($i18n.limit_rate);
		}else
		{
			Ext.getCmp('d2_limit_rate2101').setFieldLabel($i18n.limit_number);
		}
	},
	
	//仓库维护》储区》储区属性选择
	d2_area_attribute2101Change:function(th,newValue,oldValue,eOpts)
	{
		var areaAttribute = Ext.getCmp('d2_area_attribute2101').getValue();
		//作业区
		if(areaAttribute==0)
		{
		    //普通、异常、贵重品区储区属性类型只能是0存储
			var wheresql = {
				str:"CDEF_DEFAREA,ATTRIBUTE_TYPE1"
		    };
			Ext.apply(Ext.getCmp('d2_attribute_type2101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('d2_attribute_type2101').getStore().removeAll();
			Ext.getCmp('d2_attribute_type2101').getStore().load();
			
			Ext.getCmp('detailInfor2101').setVisible(true);
			Ext.getCmp('detailInfor2101_1').setVisible(true);
			Ext.getCmp('d2_area_pick2101').setVisible(true);
			Ext.getCmp('d2_attribute_type2101').setValue('0');
			
			Ext.getCmp('d2_pick_flag2101').setVisible(true);
			Ext.getCmp('d2_locate_time2101').setVisible(true);
			Ext.getCmp('d2_b_divide_flag2101').setVisible(true);
			Ext.getCmp('d2_divide_flag_a2101').setVisible(true);
			Ext.getCmp('d2_max_qty2101').setVisible(true);
			Ext.getCmp('d2_max_case2101').setVisible(true);
			Ext.getCmp('d2_a_flag2101').setVisible(true);
			Ext.getCmp('d2_area_quality2101').setVisible(true);
		//暂存区
		}else if(areaAttribute==1)
		{
			//普通、异常、贵重品区以外的储区属性类型只能是大于0存储的属性
			var wheresql = {
				str:"CDEF_DEFAREA,ATTRIBUTE_TYPE2"
		    };
			Ext.apply(Ext.getCmp('d2_attribute_type2101')
						.getStore().proxy.extraParams,
						wheresql);
			Ext.getCmp('d2_attribute_type2101').getStore()
					.removeAll();
			Ext.getCmp('d2_attribute_type2101').getStore()
					.load();
			Ext.getCmp('detailInfor2101').setVisible(false);
			Ext.getCmp('detailInfor2101_1').setVisible(false);
			Ext.getCmp('d2_area_pick2101').setVisible(false);
			
			Ext.getCmp('d2_pick_flag2101').setVisible(false);
			Ext.getCmp('d2_locate_time2101').setVisible(false);
			Ext.getCmp('d2_b_divide_flag2101').setVisible(false);
			Ext.getCmp('d2_divide_flag_a2101').setVisible(false);
			Ext.getCmp('d2_max_qty2101').setVisible(false);
			Ext.getCmp('d2_max_case2101').setVisible(false);
			Ext.getCmp('d2_a_flag2101').setVisible(false);
			Ext.getCmp('d2_area_quality2101').setVisible(false);
	    //问题区和虚拟区
		}else 
		{
			//普通、异常、贵重品区以外的储区属性类型只能是大于0存储的属性
			var wheresql = {
				str:"CDEF_DEFAREA,ATTRIBUTE_TYPE"
		    };
			Ext.apply(Ext.getCmp('d2_attribute_type2101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('d2_attribute_type2101').getStore().removeAll();
			Ext.getCmp('d2_attribute_type2101').getStore().load();
			Ext.getCmp('detailInfor2101').setVisible(false);
			Ext.getCmp('detailInfor2101_1').setVisible(false);
			Ext.getCmp('d2_area_pick2101').setVisible(true);
			
			Ext.getCmp('d2_pick_flag2101').setVisible(false);
			Ext.getCmp('d2_locate_time2101').setVisible(false);
			Ext.getCmp('d2_b_divide_flag2101').setVisible(false);
			Ext.getCmp('d2_divide_flag_a2101').setVisible(false);
			Ext.getCmp('d2_max_qty2101').setVisible(false);
			Ext.getCmp('d2_max_case2101').setVisible(false);
			Ext.getCmp('d2_a_flag2101').setVisible(false);
			Ext.getCmp('d2_area_quality2101').setVisible(false);			
		}		
	},
	
	//仓库维护》储区》储区用途选择
	d2_area_usetype2101Change:function(th,newValue,oldValue,eOpts)
	{
		if(newValue==1||newValue==5||newValue==6)
		{
			Ext.getCmp('d2_area_attribute2101').setVisible(true);
			Ext.getCmp('d2_area_type2101').setVisible(true);
			Ext.getCmp('d2_o_type2101').setVisible(true);
			Ext.getCmp('d2_attribute_type2101').setVisible(true);
			Ext.getCmp('typeFlag2101').setVisible(true);
			Ext.getCmp('d2_area_pick2101').setVisible(true);
			if(newValue==5)
			{
				Ext.getCmp('d2_area_pick2101').setVisible(false);
				Ext.getCmp('d2_area_pick2101').setValue('0');
			}
			var wheresql = '';
			if(newValue==5||newValue==6)
			{
				//异常、贵重品区储区属性只能是作业区、问题区和虚拟区
				wheresql = {
					str:"CDEF_DEFAREA,AREA_ATTRIBUTE1"
			    };
				
			}else
			{
				wheresql = {
					str:"CDEF_DEFAREA,AREA_ATTRIBUTE"
			    };
			}
			Ext.apply(Ext.getCmp('d2_area_attribute2101').getStore().proxy.extraParams,
							wheresql);
			Ext.getCmp('d2_area_attribute2101').getStore().removeAll();
			Ext.getCmp('d2_area_attribute2101').getStore().load();
		}else
		{
			Ext.getCmp('d2_area_type2101').setVisible(false);
			Ext.getCmp('d2_o_type2101').setVisible(false);
			Ext.getCmp('d2_area_type2101').setValue('3');
			Ext.getCmp('d2_o_type2101').setValue('B');
			Ext.getCmp('d2_area_attribute2101').setValue('0');
			Ext.getCmp('d2_attribute_type2101').setValue('0');
			Ext.getCmp('d2_area_pick2101').setValue('0');
			wheresql = {
				str:"CDEF_DEFAREA,AREA_ATTRIBUTE"
		    };
			Ext.apply(Ext.getCmp('d2_area_attribute2101').getStore().proxy.extraParams,
							wheresql);
			Ext.getCmp('d2_area_attribute2101').getStore().removeAll();
			Ext.getCmp('d2_area_attribute2101').getStore().load();
		}
	},
	
	wareNoBlur:function(th){
		if(but2101=='add'){
			var detail1 = [];
			var d={
				columnId:'a.ware_no',
				value:th.getValue()
			};
			detail1.push(d);
			if(!Ext.isEmpty(th.getValue())){
				Ext.Ajax.request({
				url:'cdef_DefWareAction_existsWareList.action',
				params : {
					queryStr:Ext.encode(detail1)
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('ware_No2101').setValue('');
						Ext.getCmp('ware_No2101').focus();
					}
				}
			});
			}	
		}		
	},
	
	areaNoBlur:function(th){
		if(but2101=='add'){
			var detail1 = [];
			var d={
				columnId:'a.area_no',
				value:th.getValue()
			};
			detail1.push(d);
			var d={
				columnId:'a.ware_no',
				value:Ext.getCmp('d2_ware_no2101').getValue()
			};
			detail1.push(d);
			if(!Ext.isEmpty(th.getValue())){
				Ext.Ajax.request({
				url:'cdef_DefWareAction_existsAreaList.action',
				params : {
					queryStr:Ext.encode(detail1)
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('d2_area_no2101').setValue('');
						Ext.getCmp('d2_area_no2101').focus();
					}
				}
			});
			}	
		}		
	},
	
	/**
	 * 新增
	 */
	detailAdd:function(){
		if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefWareGrid2101")
		{
			but2101='add';
			Ext.create('cms.view.cdef.window.cdef_DefWareAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			addCdef_DefWare();
			addCommMenu5('menuWidget52101');
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d3_tap2101"){
			but2101='add';
			Ext.create('cms.view.cdef.window.cdef_DefAreaAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			addCdef_DefArea();
			addCommMenu5('d2_menuWidget52101');
		}else{
			Ext.create('cms.view.cdef.window.cdef_DefStockAndCellAddOrEditWindow',{
				title:$i18n.titleadd
			}).show();
			addCommMenu5('d3_menuWidget52101');
			Ext.getCmp('perf1').setValue('1');
			Ext.getCmp('perf2').setValue('1');
			Ext.getCmp('perf3').setValue('1');
			Ext.getCmp('perf4').setValue('1');
		}
	},
	/**
	 * 修改
	 */
	detailEdit:function(){	
		if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefWareGrid2101")
		{
			but2101='update';
			var data = Ext.getCmp('cdef_DefWareGrid2101').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cdef.window.cdef_DefWareAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show(); 
				rowindex2101=data[0].index;
				loadWare(rowindex2101);
				commonSetCommMenu5PrevOrNext('menuWidget52101','cdef_DefWareGrid2101',rowindex2101);
				updateCommMenu5('menuWidget52101');
				bindEnterSkip($('#cdef_DefWareAddOrEditForm'));//调用键盘处理方法
				commonSetMsterReadOnlyByArray(
				new Array('ware_No2101'),true);
				Ext.getCmp('ware_Name2101').focus();
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d3_tap2101")
		{
			but2101='update';
			var data = Ext.getCmp('cdef_DefAreaGrid2101').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cdef.window.cdef_DefAreaAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show(); 
				rowindex2101=data[0].index;
				loadArea(rowindex2101);
				commonSetCommMenu5PrevOrNext('d2_menuWidget52101','cdef_DefAreaGrid2101',rowindex2101);
				updateCommMenu5('d2_menuWidget52101');
				bindEnterSkip($('#cdef_DefAreaAddOrEditForm'));//调用键盘处理方法
				commonSetMsterReadOnlyByArray(
				new Array('d2_area_no2101','d2_mix_owner2101'),true);
				Ext.getCmp('d2_area_name2101').focus();
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefStockGrid2101")
		{
			var data = Ext.getCmp('cdef_DefStockGrid2101').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cdef.window.cdef_DefStockAddOrEditWindow',{
					title:$i18n.titleupdate
				}).show();
				
				rowindex2101=data[0].index;
				loadStock(rowindex2101);
				commonSetCommMenu5PrevOrNext('d3_menuWidget52101','cdef_DefStockGrid2101',rowindex2101);
				updateCommMenu5('d3_menuWidget52101');
				bindEnterSkip($('#cdef_DefStockAddOrEditForm'));//调用键盘处理方法
				Ext.getCmp('d3_q_stock_x2101').focus();
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
	        }
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d4_tap2101"){
			if(Ext.isEmpty(selectCellNo2101.trim()) || selectCellNo2101.trim()=='N')
			{
				Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
				return;
			}
			
			Ext.create('cms.view.cdef.window.cdef_DefCellAddOrEditWindow',{
				title:$i18n.titleupdate
			}).show();
			updateCommMenu5('d4_menuWidget52101');
			var detail1 = [];			
			d={
				columnId:'a.cell_no',
				value:selectCellNo2101.trim()
			};
			detail1.push(d);

			var jsonDetail1 = Ext.encode(detail1);
			Ext.Ajax.request({
				url:'cdef_DefWareAction_getCdef_DefCellDetails.action',
				params : {
					str:jsonDetail1
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					loadCdef_DefCell(data[0]);
				}
			});
		}
	},
	/**
	 * 浏览
	 */
	detailBrowse:function(){	
		if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefWareGrid2101")
		{
			but2101='update';
			var data = Ext.getCmp('cdef_DefWareGrid2101').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cdef.window.cdef_DefWareAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex2101=data[0].index;
				loadWare(rowindex2101);
				commonSetFormReadOnly('cdef_DefWareAddOrEditForm',true);
				commonSetCommMenu5PrevOrNext('menuWidget52101','cdef_DefWareGrid2101',rowindex2101);
				browseCommMenu5('menuWidget52101');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d3_tap2101")
		{
			but2101='update';
			var data = Ext.getCmp('cdef_DefAreaGrid2101').getSelectionModel().getSelection();
			if (data.length != 0) {
				Ext.create('cms.view.cdef.window.cdef_DefAreaAddOrEditWindow',{
					title:$i18n.titlebrowse
				}).show(); 
				rowindex2101=data[0].index;
				loadArea(rowindex2101);
				commonSetFormReadOnly('areaId2101',true);
				commonSetFormReadOnly('useAttrType2101',true);
				commonSetFormReadOnly('typeFlag2101',true);
				commonSetFormReadOnly('detailInfor2101',true);
				commonSetCommMenu5PrevOrNext('d2_menuWidget52101','cdef_DefAreaGrid2101',rowindex2101);
				browseCommMenu5('d2_menuWidget52101');
	        }else{
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
	        }
		}
	},
	/**
	 * 删除
	 */
	detailDelete:function(){
		if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefWareGrid2101"){
			var data = Ext.getCmp('cdef_DefWareGrid2101').getSelectionModel().getSelection();  
	        if (data.length == 0) {  
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	            return;  
	        } else {  
	    		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
	    			if(button=='yes')
					{
						Ext.getCmp('cdef_DefWareGrid2101').getStore().remove(data);
						Ext.getCmp('cdef_DefWareGrid2101').store.reload();
						
						var params={
							strWareNo:data[0].get("wareNo")
						};
						Ext.Ajax.request({
							method:'post',
							url:'cdef_DefWareAction_deleteCdefDefware',
							params:params,
							success:function(response)
							{
								var data = Ext.decode(response.responseText);
								if(data.isSucc)
								{
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('cdef_DefWareGrid2101').getStore().remove();
									Ext.getCmp('cdef_DefWareGrid2101').store.reload();
								}else
								{
									Ext.example.msg($i18n.prompt,data.msg);
								}
							}
						});
					}			
	            });
        	}
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d3_tap2101"){
			var data = Ext.getCmp('cdef_DefAreaGrid2101').getSelectionModel().getSelection();  
	        if (data.length == 0) {  
	        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
	            return;  
	        } else {  
	    		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
	    			if(button=='yes')
					{
						var params={
							strWareNo:data[0].get("wareNo"),
							strAreaNo:data[0].get("areaNo")
						};
						Ext.Ajax.request({
							method:'post',
							url:'cdef_DefWareAction_deleteCdefDefarea',
							params:params,
							success:function(response)
							{
								var data = Ext.decode(response.responseText);
								if(data.isSucc)
								{
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp('cdef_DefAreaGrid2101').getStore().remove(data);
									Ext.getCmp('cdef_DefAreaGrid2101').store.reload();
								}else
								{
									Ext.example.msg($i18n.prompt,data.msg);
								}
							}
						});
					}			
	            });
        	}
		}
	},
	
	//导出
	detailExport:function(){
		if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefWareGrid2101"){
			commExport('cdef_DefWareGrid2101');
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d3_tap2101"){
			commExport('cdef_DefAreaGrid2101');
		}else if(Ext.getCmp('tab2101').getActiveTab().id=="d4_tap2101"){
			return;
		}
	},
	/**
	 * 点击tab页
	 */
	tabchange:function(tab){
		var detail1 = [];
		if(tab.getActiveTab().id=="cdef_DefWareGrid2101"){
			Ext.getCmp('menu2101').items.items[0].enable(true);
			Ext.getCmp('menu2101').items.items[1].enable(true);
			Ext.getCmp('menu2101').items.items[2].enable(true);
			Ext.getCmp('menu2101').items.items[3].enable(true);
		}else if(tab.getActiveTab().id=="d3_tap2101"){
			Ext.getCmp('d3_wareno2101').getStore().load();	
			Ext.getCmp('d3_wareno2101').setValue('all');
			
			//加载所有
			var wheresql = {
					queryStr : ''
			};
			Ext.apply(Ext.getCmp('cdef_DefAreaGrid2101').getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cdef_DefAreaGrid2101').getStore().load();
			Ext.getCmp('menu2101').items.items[0].enable(true);
			Ext.getCmp('menu2101').items.items[1].enable(true);
			Ext.getCmp('menu2101').items.items[2].enable(true);
			Ext.getCmp('menu2101').items.items[3].enable(true);
		}else if(tab.getActiveTab().id=="d4_tap2101"){
			Ext.getCmp('menu2101').items.items[0].enable(true);
			Ext.getCmp('menu2101').items.items[1].enable(true);
			Ext.getCmp('menu2101').items.items[2].disable(true);
			Ext.getCmp('menu2101').items.items[3].disable(true);
			Ext.getCmp('d4_wareno2101').getStore().load();
		}else if(tab.getActiveTab().id=="d5_tap2101"){
			Ext.getCmp('menu2101').items.items[0].disable(true);
			Ext.getCmp('menu2101').items.items[1].disable(true);
			Ext.getCmp('menu2101').items.items[2].disable(true);
			Ext.getCmp('menu2101').items.items[3].disable(true);
		}else if(tab.getActiveTab().id=="d6_tap2101"){
			Ext.getCmp('menu2101').items.items[0].disable(true);
			Ext.getCmp('menu2101').items.items[1].disable(true);
			Ext.getCmp('menu2101').items.items[2].disable(true);
			Ext.getCmp('menu2101').items.items[3].disable(true);
			Ext.getCmp('d6_wareno2101').getStore().load();
		}
	},
	/**======================仓库===============================
	 * 上一条记录
	 */
	prev:function(th){
		rowindex2101=rowindex2101-1;
		loadWare(rowindex2101);
		commonSetCommMenu5PrevOrNext('menuWidget52101','cdef_DefWareGrid2101',rowindex2101);
	},
	/**
	 * 下一条记录
	 */
	next:function(th){
		rowindex2101=rowindex2101+1;
		loadWare(rowindex2101);
		commonSetCommMenu5PrevOrNext('menuWidget52101','cdef_DefWareGrid2101',rowindex2101);
	},
	
	
	/**
	 * 关闭
	 */
	close:function(th){
		closeWindow();
	},
	/**
	 * 保存并新增
	 */
	add:function(th){
		Ext.getCmp('cdef_DefWareAddOrEditForm').getForm().reset();
		Ext.getCmp('warehouse_no2101').setValue(Ext.get('warehouseNo').getValue());
	},
	/**
	 * 保存并关闭
	 */
	save:function(th){
		if(Ext.getCmp('tab2101').getActiveTab().id=="cdef_DefWareGrid2101")
		{
			saveCdef_DefWare(th);
		}
	},
	/**========================储区=============================
	 * 储区，上一条记录
	 */
	prevCdef_DefArea:function(th){
		rowindex2101=rowindex2101-1;
		loadArea(rowindex2101);
		commonSetCommMenu5PrevOrNext('d2_menuWidget52101','cdef_DefAreaGrid2101',rowindex2101);
	},
	/**
	 * 储区，下一条记录
	 */
	nextCdef_DefArea:function(th){
		rowindex2101=rowindex2101+1;
		loadArea(rowindex2101);
		commonSetCommMenu5PrevOrNext('d2_menuWidget52101','cdef_DefAreaGrid2101',rowindex2101);	
	},
	
	/**
	 * 保存,并新增储区
	 */
	addCdef_DefArea:function(th){
		addCdef_DefArea();
	},
	
	/**
	 * 储区、保存
	 */
	saveCdef_DefArea:function(th){
		saveCdef_DefArea(th);
	},
	
	closeCdef_DefArea:function(){
		closeCdef_DefAreaWindow();
	},
	/***
	 * 储区，选择库区加载
	 */
	d3_wareno2101change:function(combo){
		var detail1 = [];
		if(combo.value == 'all'){
			var wheresql = {
					queryStr : ''
				};
		}else{
			var d={
					columnId:'a.ware_No',
					value:combo.value
				};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
		
			var wheresql = {
					queryStr : jsonDetail1
				};
		}
		Ext.apply(Ext.getCmp('cdef_DefAreaGrid2101').getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('cdef_DefAreaGrid2101').getStore().load();
		
	},
	d6_wareno2101_2change:function(combo){

		
		var wheresql = {
			str : Ext.getCmp('d6_wareno2101_2').getValue()
		};
		Ext.apply(Ext.getCmp('d6_areano2101_2')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('d6_areano2101_2').getStore().load();
	
	},
	d6_wareno2101change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_No',
			value:combo.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('d6_areano2101')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('d6_areano2101').getStore().load();
	},
	/**
	 * 库区选择事件（UI）
	 */
	d4_wareno2101change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_No',
			value:combo.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('d4_areano2101')
				.getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('d4_areano2101').getStore().load();
	},
	/**
	 * 库区选择事件（window）
	 */
	wareno2101change:function(combo){
		var wheresql = {
			str : combo.value
		};
		Ext.apply(Ext.getCmp('areano2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('areano2101').getStore().load();
		this.changeStockAndCell();
	},
	
	
	cell_no2101_d5change:function(combo){
			var detail1 = [];
			var d={
				columnId:'a.cell_no',
				value:combo.value
			};
			detail1.push(d);
			var jsonDetail1 = Ext.encode(detail1);
			var wheresql = {
					str : jsonDetail1
			};
			Ext.Ajax.request({
			url:'cdef_DefWareAction_getCdef_DefCellDetails.action',
			params : wheresql,
			success:function(response){
				var data=Ext.decode(response.responseText);
				Ext.getCmp('d5_wareno2101').setValue(data[0].wareName);
				Ext.getCmp('d5_areano2101').setValue(data[0].areaName);
				Ext.getCmp('d5_stock2101').setValue(data[0].stockNo);
				Ext.getCmp('d5_stockX2101').setValue(data[0].stockX);
				Ext.getCmp('d5_bayX2101').setValue(data[0].bayX);
				Ext.getCmp('d5_stockY2101').setValue(data[0].stockY);
				Ext.getCmp('d5_cell2101').setValue(data[0].cellNo);
				Ext.getCmp('d5_dispCellNo2101').setValue(data[0].dispCellNo);
				Ext.getCmp('d5_max_qty2101').setValue(data[0].maxQty);	
				Ext.getCmp('d5_max_volume2101').setValue(data[0].maxVolume);
				Ext.getCmp('d5_max_weight2101').setValue(data[0].maxWeight);
				Ext.getCmp('d5_max_case2101').setValue(data[0].maxCase);
				Ext.getCmp('d5_cell_status2101').setValue(data[0].cellStatus);
				Ext.getCmp('d5_check_status2101').setValue(data[0].checkStatus);
				Ext.getCmp('d5_mix_flag2101').setValue(String(data[0].mixFlag));
				Ext.getCmp('d5_limit_type2101').setValue(String(data[0].limitType));
				Ext.getCmp('d5_limit_rate2101').setValue(data[0].limitRate);
				Ext.getCmp('d5_b_pick2101').setValue(data[0].BPick);
				Ext.getCmp('d5_a_flag2101').setValue(data[0].AFlag);
				Ext.getCmp('d5_mix_supplier2101').setValue(data[0].mixSupplier);
				Ext.getCmp('d5_mix_owner2101').setValue(data[0].mixOwner);
				Ext.getCmp('d5_pick_flag2101').setValue(data[0].pickFlag);
			}
		});
			
	},
	
	d6_areano2101change:function(combo){
		getCellNoGrid();
		var detail1 = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('d6_wareno2101').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.area_no',
			value:combo.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('d6_stock2101')
				.getStore().proxy.extraParams,
				wheresql);	
		Ext.getCmp('d6_stock2101').getStore().load();
		
	},
	/**
	 * 储区下拉选择事件(UI)
	 */
	d4_areano2101change:function(combo){
		var detail1 = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('d4_wareno2101').getValue()
		};
		detail1.push(d);
		var d={
			columnId:'a.area_no',
			value:combo.value
		};
		detail1.push(d);
		var jsonDetail1 = Ext.encode(detail1);
		var wheresql = {
			str : jsonDetail1
		};
		Ext.apply(Ext.getCmp('d4_stock2101')
				.getStore().proxy.extraParams,
				wheresql);	
		Ext.getCmp('d4_stock2101').getStore().load();
		
	},
	
	/**
	 * 储区下拉选择事件(window)
	 */
	areano2101change:function(){
		
		Ext.Ajax.request({
			method:'post',
			url:'cdef_DefWareAction_getAttribute.action',
			params : {
				strWareNo:Ext.getCmp('wareno2101').getValue(),
				strAreaNo:Ext.getCmp('areano2101').getValue()
			},
			success:function(response){			
				var data = Ext.decode(response.responseText);
				Ext.getCmp('d4_mix_flag2101_1').setValue(data.rootList[0].mixFlag.toString());
				Ext.getCmp('d4_b_pick2101_1').setValue(data.rootList[0].BPick);
				Ext.getCmp('d4_limit_type2101_1').setValue(data.rootList[0].limitType);
				Ext.getCmp('d4_limit_rate2101_1').setValue(data.rootList[0].limitRate);
				Ext.getCmp('d4_a_flag2101_1').setValue(data.rootList[0].AFlag);
				Ext.getCmp('d4_pick_flag2101_1').setValue(data.rootList[0].pickFlag);
				Ext.getCmp('d4_mix_supplier2101_1').setValue(data.rootList[0].mixSupplier);
				Ext.getCmp('d4_mix_owner2101_1').setValue(data.rootList[0].mixOwner);
				Ext.getCmp('d4_cell_status2101_1').setValue('0');
				Ext.getCmp('d4_check_status2101_1').setValue('0');
				Ext.getCmp('d4_max_qty2101_1').setValue(data.rootList[0].maxQty);
				Ext.getCmp('d4_max_volume2101_1').setValue(data.rootList[0].maxVolume);
				Ext.getCmp('d4_max_weight2101_1').setValue(data.rootList[0].maxWeight);
				Ext.getCmp('d4_max_case2101_1').setValue(data.rootList[0].maxCase);
				
				Ext.getCmp('d4_keep_label2101_1').setValue(data.rootList[0].keepLabelFlag);
				
				if(data.rootList[0].areaAttribute=="0"){
					Ext.getCmp('perfix').setFieldLabel("<font color='red'>*</font>前缀");
					Ext.getCmp('perfix').allowBlank=false;
					
					Ext.getCmp('stockMin').setFieldLabel("<font color='red'>*</font>通道最小值");
					Ext.getCmp('stockMin').allowBlank=false;
					Ext.getCmp('stockMax').setFieldLabel("<font color='red'>*</font>通道最大值");
					Ext.getCmp('stockMax').allowBlank=false;
					
					Ext.getCmp('floorMin').setFieldLabel("<font color='red'></font>层最小值");
					Ext.getCmp('floorMin').allowBlank=true;
					Ext.getCmp('floorMax').setFieldLabel("<font color='red'></font>层最大值");
					Ext.getCmp('floorMax').allowBlank=true;
				}else{
					Ext.getCmp('perfix').setFieldLabel("<font color='red'></font>前缀");
					Ext.getCmp('perfix').allowBlank=true;
					
					Ext.getCmp('stockMin').setFieldLabel("<font color='red'></font>通道最小值");
					Ext.getCmp('stockMin').allowBlank=true;
					Ext.getCmp('stockMax').setFieldLabel("<font color='red'></font>通道最大值");
					Ext.getCmp('stockMax').allowBlank=true;
					
					Ext.getCmp('floorMin').setFieldLabel("<font color='red'></font>层最小值");
					Ext.getCmp('floorMin').allowBlank=true;
					Ext.getCmp('floorMax').setFieldLabel("<font color='red'></font>层最大值");
					Ext.getCmp('floorMax').allowBlank=true;
				}
			}
		});
		this.changeStockAndCell();
	},

//////////////////////////////////////////////////////////////////////////////////////////////	
	//生成储位
	produceCell:function(){ 

		if(!commonCheckIsInputAll('cdef_DefStockAndCellAddOrEditForm')){
			return;
		}
	
		if(Ext.getCmp('cdef_DefStockAndCellAddOrEditForm').getForm().isValid()){
			
			if( Ext.getCmp('stockMin').getValue()>Ext.getCmp('stockMax').getValue() ||
			    Ext.getCmp('cellMin').getValue()>Ext.getCmp('cellMax').getValue()||
				Ext.getCmp('floorMin').getValue()> Ext.getCmp('floorMax').getValue()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.minimunCanotGreaterThanMaximun);
				return;
			}
			
			if( Ext.getCmp('bayXMin').getValue()!=null && Ext.getCmp('bayXMin').getValue()!='' &&	
				    Ext.getCmp('bayXMax').getValue()!=null && Ext.getCmp('bayXMax').getValue()!='' ){
				   
					if( Ext.getCmp('bayXMin').getValue()>Ext.getCmp('bayXMax').getValue()){
						Ext.example.msg($i18n.prompt,$i18n_prompt.minimunCanotGreaterThanMaximun);
						return;
					}
			}
			var str={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:Ext.get('warehouseNo').getValue(),
						cellNo:'N'
					},
					mixFlag:Ext.getCmp('d4_mix_flag2101_1').getValue(),
					mixSupplier:Ext.getCmp('d4_mix_supplier2101_1').getValue(),
					mixOwner:Ext.getCmp('d4_mix_owner2101_1').getValue(),
					maxQty:Ext.getCmp('d4_max_qty2101_1').getValue(),
					maxWeight:Ext.getCmp('d4_max_weight2101_1').getValue(),
					maxVolume:Ext.getCmp('d4_max_volume2101_1').getValue(),
					maxCase:Ext.getCmp('d4_max_case2101_1').getValue(),
					limitType:Ext.getCmp('d4_limit_type2101_1').getValue(),
					limitRate:Ext.getCmp('d4_limit_rate2101_1').getValue(),
					BPick:Ext.getCmp('d4_b_pick2101_1').getValue(),
					cellStatus:Ext.getCmp('d4_cell_status2101_1').getValue(),
					checkStatus:Ext.getCmp('d4_check_status2101_1').getValue(),
					AFlag:Ext.getCmp('d4_a_flag2101_1').getValue(),
					pickFlag:Ext.getCmp('d4_pick_flag2101_1').getValue(),
					rgstName:Ext.get('workerNo').getValue(),
					
					keepLabelFlag:Ext.getCmp('d4_keep_label2101_1').getValue(),

					wareNo:Ext.getCmp('wareno2101').getValue(),
					areaNo:Ext.getCmp('areano2101').getValue()
					

				};
		    	var jsonStr = Ext.encode(str);
		    	Ext.Ajax.request({
					method:'post',
					url:'cdef_DefWareAction_produceCell.action',
					params : {
						str:jsonStr,					
						minStockNo:Ext.getCmp('stockMin').getValue(),
						maxStockNo:Ext.getCmp('stockMax').getValue(),
						minStockY:Ext.getCmp('floorMin').getValue(),
						maxStockY:Ext.getCmp('floorMax').getValue(),
						minStockX:Ext.getCmp('cellMin').getValue(),
						maxStockX:Ext.getCmp('cellMax').getValue(),
						minBayX:Ext.getCmp('bayXMin').getValue(),
						maxBayX:Ext.getCmp('bayXMax').getValue(),
						strCodePrefix:Ext.getCmp('perfix').getValue(),
						perf1:Ext.getCmp('perf1').getValue(),
						perf2:Ext.getCmp('perf2').getValue(),
						perf3:Ext.getCmp('perf3').getValue(),
						perf4:Ext.getCmp('perf4').getValue()
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('d4_wareno2101').getStore().load();
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			
		}
	},
	closeStockAndCellAddOrEditWindow:function()
	{
		Ext.getCmp('cdef_DefStockAndCellAddOrEditWindow').close();
	},
	
	addStockAndCellAddOrEditWindow:function(){
		Ext.getCmp('cdef_DefStockAndCellAddOrEditForm').getForm().reset();
		Ext.getCmp('perf1').setValue('1');
		Ext.getCmp('perf2').setValue('1');
		Ext.getCmp('perf3').setValue('1');
		Ext.getCmp('perf4').setValue('1');	
	},
//////////////////////////////////////////////////////////////////////////////////////////////	
	d6_stock2101change:function(combo){
		getCellNoGrid();//获取货位列表
		var detail = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('d6_wareno2101').getValue()
		};
		detail.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('d6_areano2101').getValue()
		};
		detail.push(d);
		var d={
				columnId:'a.stock_no',
				value:Ext.getCmp('d6_stock2101').getValue()
		};
		detail.push(d);
				
		var wheresql = {
				str : Ext.encode(detail)
		};
				
		//格下拉
		Ext.apply(Ext.getCmp('d6_stockX2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d6_stockX2101').getStore().removeAll();
		Ext.getCmp('d6_stockX2101').getStore().load();
		
		//位下拉
		Ext.apply(Ext.getCmp('d6_bayX2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d6_bayX2101').getStore().removeAll();
		Ext.getCmp('d6_bayX2101').getStore().load();
				
		//层下拉
		Ext.apply(Ext.getCmp('d6_stockY2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d6_stockY2101').getStore().removeAll();
		Ext.getCmp('d6_stockY2101').getStore().load();			
	},
	//通道选择事件(UI)
	d4_stock2101change:function(combo){
		
		selectCellNo2101="N";
		getCellNo();
		var detail = [];
		var d={
			columnId:'a.ware_No',
			value:Ext.getCmp('d4_wareno2101').getValue()
		};
		detail.push(d);
		var d={
			columnId:'a.area_no',
			value:Ext.getCmp('d4_areano2101').getValue()
		};
		detail.push(d);
		var d={
				columnId:'a.stock_no',
				value:Ext.getCmp('d4_stock2101').getValue()
		};
		detail.push(d);
				
		var wheresql = {
				str : Ext.encode(detail)
		};
				
		//格下拉
		Ext.apply(Ext.getCmp('d4_stockX2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d4_stockX2101').getStore().removeAll();
		Ext.getCmp('d4_stockX2101').getStore().load();
		
		//位下拉
		Ext.apply(Ext.getCmp('d4_bayX2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d4_bayX2101').getStore().removeAll();
		Ext.getCmp('d4_bayX2101').getStore().load();
				
		//层下拉
		Ext.apply(Ext.getCmp('d4_stockY2101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('d4_stockY2101').getStore().removeAll();
		Ext.getCmp('d4_stockY2101').getStore().load();				
	},
	d6_stockX2101change:function(){
		
		getCellNoGrid();
		if(Ext.getCmp('d6_stockX2101').getValue()!=null && Ext.getCmp('d6_stockX2101').getValue()!=""){
			var detail = [];
			var d={
				columnId:'a.ware_No',
				value:Ext.getCmp('d6_wareno2101').getValue()
			};
			detail.push(d);
			var d={
				columnId:'a.area_no',
				value:Ext.getCmp('d6_areano2101').getValue()
			};
			detail.push(d);
				d={
					columnId:'a.stock_no',
					value:Ext.getCmp('d6_stock2101').getValue()
			};
			detail.push(d);
			
			d={
					columnId:'a.stock_x',
					value:Ext.getCmp('d6_stockX2101').getValue()
			};
			detail.push(d);	
					
			var wheresql = {
					str : Ext.encode(detail)
			};
			
			Ext.apply(Ext.getCmp('d6_bayX2101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('d6_bayX2101').getStore().load();
		}
	
	},
	//格改变
	d4_stockX2101change:function(){
		selectCellNo2101="N";
		getCellNo();
		
		if(Ext.getCmp('d4_stockX2101').getValue()!=null && Ext.getCmp('d4_stockX2101').getValue()!=""){
			var detail = [];
			var d={
				columnId:'a.ware_No',
				value:Ext.getCmp('d4_wareno2101').getValue()
			};
			detail.push(d);
			var d={
				columnId:'a.area_no',
				value:Ext.getCmp('d4_areano2101').getValue()
			};
			detail.push(d);
				d={
					columnId:'a.stock_no',
					value:Ext.getCmp('d4_stock2101').getValue()
			};
			detail.push(d);
			
			d={
					columnId:'a.stock_x',
					value:Ext.getCmp('d4_stockX2101').getValue()
			};
			detail.push(d);	
					
			var wheresql = {
					str : Ext.encode(detail)
			};
			
			Ext.apply(Ext.getCmp('d4_bayX2101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('d4_bayX2101').getStore().load();
		}
	},
	
	//改变位
	bayX_stockYchange:function(){
		selectCellNo2101="N";
		getCellNo();
	},
	d6_bayX_stockYchange:function(){
		getCellNoGrid();
	},
	//修改储位
	saveCdef_DefCell:function(){
		debugger
		if(Ext.getCmp('cdef_DefCellAddOrEditForm').getForm().isValid()){		
			var str={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					cellNo:Ext.getCmp('d4_cell2101').getValue()
				},
				mixFlag:Ext.getCmp('d4_mix_flag2101').getValue(),
				mixSupplier:Ext.getCmp('d4_mix_supplier2101').getValue(),
				mixOwner:Ext.getCmp('d4_mix_owner2101').getValue(),
				maxQty:Ext.getCmp('d4_max_qty2101').getValue(),
				maxWeight:Ext.getCmp('d4_max_weight2101').getValue(),
				maxVolume:Ext.getCmp('d4_max_volume2101').getValue(),
				maxCase:Ext.getCmp('d4_max_case2101').getValue(),
				limitType:Ext.getCmp('d4_limit_type2101').getValue(),
				limitRate:Ext.getCmp('d4_limit_rate2101').getValue(),
				BPick:Ext.getCmp('d4_b_pick2101').getValue(),
				cellStatus:Ext.getCmp('d4_cell_status2101').getValue(),
				checkStatus:Ext.getCmp('d4_check_status2101').getValue(),
				AFlag:Ext.getCmp('d4_a_flag2101').getValue(),
				pickFlag:Ext.getCmp('d4_pick_flag2101').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				keepLabelFlag:Ext.getCmp('d4_keep_label2101').getValue()
				
				//rgstDate:Ext.Date.format(new Date,'Y-m-d H:i:s')
			};
		var jsonStr = Ext.encode(str);
		Ext.Ajax.request({
			method:'post',
			url:'cdef_DefWareAction_saveCdef_DefCell.action',
			params : {
				str:jsonStr
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
	},
	closeCdef_DefCell:function(){
		Ext.getCmp('cdef_DefCellAddOrEditWindow').close();
	},
	
	getbut2101:function(){
		return but2101;
	},
	
	getRowNum:function(){
		return rowindex2101;
	},
	
	
	changeStockAndCell:function(){
		var stockMin='';
		var stockMax='';
		var cellMin='';
		var cellMax='';
		var bayXMin='';
		var bayXMax='';
		var floorMin='';
		var floorMax='';
		if((Ext.getCmp('stockMin').getValue()!=null && Ext.getCmp('stockMin').getValue()!='' &&
		   Ext.getCmp('stockMax').getValue()!=null && Ext.getCmp('stockMax').getValue()!='')||
		   ((Ext.getCmp('stockMin').getValue()==null || Ext.getCmp('stockMin').getValue()=='')&&
		    (Ext.getCmp('stockMax').getValue()==null || Ext.getCmp('stockMax').getValue()==''))){
				if(Ext.getCmp('stockMin').getValue()!=null && Ext.getCmp('stockMin').getValue()!=''){
					//判断非字符串
					if(isNaN(Ext.getCmp('stockMin').getValue()) || isNaN(Ext.getCmp('stockMax').getValue())){
						Ext.example.msg($i18n.prompt,'输入的通道不能有字母');
						Ext.getCmp('stockMin').setValue(null);
						Ext.getCmp('stockMax').setValue(null);
						return;
					}						
					//判断长度
					if(Ext.getCmp('stockMin').getValue().length !=Ext.getCmp('stockMax').getValue().length){
						Ext.example.msg($i18n.prompt,'通道长度不一致');
						Ext.getCmp('stockMax').setValue(null);
						return;
					}
									
					//判断大小
					var min =parseInt(Ext.getCmp('stockMin').getValue(), 10);
					var max =parseInt(Ext.getCmp('stockMax').getValue(), 10);					
					if(min>max){
						Ext.example.msg($i18n.prompt,'通道最小值不能大于最大值');
						Ext.getCmp('stockMin').setValue(null);
						Ext.getCmp('stockMax').setValue(null);
						return;
					}	
				}
				
				stockMin=Ext.getCmp('stockMin').getValue();
				stockMax=Ext.getCmp('stockMax').getValue();
		}else{
			return;
		}
		
		if((Ext.getCmp('cellMin').getValue()!=null && Ext.getCmp('cellMin').getValue()!='' &&
		   Ext.getCmp('cellMax').getValue()!=null && Ext.getCmp('cellMax').getValue()!='')||
		   (((Ext.getCmp('cellMin').getValue()==null || Ext.getCmp('cellMin').getValue()=='') &&
		   (Ext.getCmp('cellMax').getValue()==null || Ext.getCmp('cellMax').getValue()=='')))){
				if(Ext.getCmp('cellMin').getValue()!=null && Ext.getCmp('cellMax').getValue()!=''){
				
					if(isNaN(Ext.getCmp('cellMin').getValue()) || isNaN(Ext.getCmp('cellMax').getValue())){
						Ext.example.msg($i18n.prompt,'输入的格不能有字母');
						Ext.getCmp('cellMin').setValue(null);
						Ext.getCmp('cellMax').setValue(null);
						return;
					}
					
					//判断长度
					if(Ext.getCmp('cellMin').getValue().length !=Ext.getCmp('cellMax').getValue().length){
						Ext.example.msg($i18n.prompt,'格长度不一致');
						Ext.getCmp('cellMax').setValue(null);
						return;
					}
					
					//判断大小
					var min =parseInt(Ext.getCmp('cellMin').getValue(), 10);
					var max =parseInt(Ext.getCmp('cellMax').getValue(), 10);				
					if(min>max){
						Ext.example.msg($i18n.prompt,'格最小值不能大于最大值');
						Ext.getCmp('cellMin').setValue(null);
						Ext.getCmp('cellMax').setValue(null);
						return;
					}	
				}
				
				cellMin=Ext.getCmp('cellMin').getValue();
				cellMax=Ext.getCmp('cellMax').getValue();
		}else{
			return;
		}
		
		if((Ext.getCmp('bayXMin').getValue()!=null && Ext.getCmp('bayXMin').getValue()!='' &&
		   Ext.getCmp('bayXMax').getValue()!=null && Ext.getCmp('bayXMax').getValue()!='')||
		   ((Ext.getCmp('bayXMin').getValue()==null || Ext.getCmp('bayXMin').getValue()=='') &&
		   (Ext.getCmp('bayXMax').getValue()==null || Ext.getCmp('bayXMax').getValue()==''))){
			
			if(Ext.getCmp('bayXMin').getValue()!=null && Ext.getCmp('bayXMax').getValue()!=''){
				
				if(isNaN(Ext.getCmp('bayXMin').getValue()) || isNaN(Ext.getCmp('bayXMax').getValue())){
					Ext.example.msg($i18n.prompt,'输入的位不能有字母');
					Ext.getCmp('bayXMin').setValue(null);
					Ext.getCmp('bayXMax').setValue(null);
					return;
				}	
				
				//判断长度
				if(Ext.getCmp('bayXMin').getValue().length !=Ext.getCmp('bayXMax').getValue().length){
					Ext.example.msg($i18n.prompt,'位长度不一致');
					Ext.getCmp('bayXMax').setValue(null);
					return;
				}
				
				//判断大小
				var min =parseInt(Ext.getCmp('bayXMin').getValue(), 10);
				var max =parseInt(Ext.getCmp('bayXMax').getValue(), 10);			
				if(min>max){
					Ext.example.msg($i18n.prompt,'位最小值不能大于最大值');
					Ext.getCmp('bayXMin').setValue(null);
					Ext.getCmp('bayXMax').setValue(null);
					return;
				}	
			}
				bayXMin=Ext.getCmp('bayXMin').getValue();
				bayXMax=Ext.getCmp('bayXMax').getValue();
		}else{
			return;
		}
		
		if((Ext.getCmp('floorMin').getValue()!=null && Ext.getCmp('floorMin').getValue()!='' &&
		   Ext.getCmp('floorMax').getValue()!=null && Ext.getCmp('floorMax').getValue()!='')||
		   ((Ext.getCmp('floorMin').getValue()==null || Ext.getCmp('floorMin').getValue()=='') &&
		    (Ext.getCmp('floorMax').getValue()==null || Ext.getCmp('floorMax').getValue()==''))){
				if(Ext.getCmp('floorMin').getValue()!=null && Ext.getCmp('floorMax').getValue()!=''){
				
					if(isNaN(Ext.getCmp('floorMin').getValue()) || isNaN(Ext.getCmp('floorMax').getValue())){
						Ext.example.msg($i18n.prompt,'输入的层不能有字母');
						Ext.getCmp('floorMin').setValue(null);
						Ext.getCmp('floorMax').setValue(null);
						return;
					}
					
					//判断长度
					if(Ext.getCmp('floorMin').getValue().length !=Ext.getCmp('floorMax').getValue().length){
						Ext.example.msg($i18n.prompt,'层长度不一致');
						Ext.getCmp('floorMax').setValue(null);
						return;
					}
					
					//判断大小
					var min =parseInt(Ext.getCmp('floorMin').getValue(), 10);
					var max =parseInt(Ext.getCmp('floorMax').getValue(), 10);			
					if(min>max){
						Ext.example.msg($i18n.prompt,'层的最小值不能大于最大值');
						Ext.getCmp('floorMin').setValue(null);
						Ext.getCmp('floorMax').setValue(null);
						return;
					}	
				}				
			    floorMin=Ext.getCmp('floorMin').getValue();
				floorMax=Ext.getCmp('floorMax').getValue();
		}else{
			return;
		}
		
    	Ext.Ajax.request({
			method:'post',
			url:'cdef_DefWareAction_checkCell.action',
			params : {				
				minStockNo:stockMin,
				maxStockNo:stockMax,
				minStockY:floorMin,
				maxStockY:floorMax,
				minStockX:cellMin,
				maxStockX:cellMax,
				minBayX:bayXMin,
				maxBayX:bayXMax,
				strWareNo:Ext.getCmp('wareno2101').getValue(),
				strAreaNo:Ext.getCmp('areano2101').getValue()
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					if(data.msg=='1'){
						Ext.example.msg($i18n.prompt,$i18n_prompt.stockRepeat);
					}else if (data.msg=='2'){
						Ext.example.msg($i18n.prompt,$i18n_prompt.stockXRepeat);
					}else if(data.msg=='3'){
						Ext.example.msg($i18n.prompt,$i18n_prompt.bayX);
					}else if(data.msg=='4'){
						Ext.example.msg($i18n.prompt,$i18n_prompt.stockYRepeat);
						Ext.getCmp('floorMin').setValue(null);
						Ext.getCmp('floorMax').setValue(null);
					}
	
				}
			}
		});		
		this.showCell();
	},
	
	showCell:function(){
		if(Ext.getCmp('cellMin').getValue()==null || Ext.getCmp('cellMin').getValue()=='' ||	
		   Ext.getCmp('cellMax').getValue()==null || Ext.getCmp('cellMax').getValue()=='' ){
			return;
		}
		
		var cell=Ext.getCmp('perfix').getValue();
		var dispCell=Ext.getCmp('perfix').getValue();
		
		if(Ext.getCmp('stockMin').getValue()!=null &&Ext.getCmp('stockMin').getValue()!='' &&
		  Ext.getCmp('stockMax').getValue()!=null && Ext.getCmp('stockMax').getValue()!=''){
			cell=cell+Ext.getCmp('stockMin').getValue();
			
			if (Ext.getCmp('perf1').getValue()=='2' &&  dispCell!=null && dispCell!=''){
				dispCell=dispCell+"-"+Ext.getCmp('stockMin').getValue();
			}else if(Ext.getCmp('perf1').getValue()=='3'){
				dispCell=dispCell+"("+Ext.getCmp('stockMin').getValue()+")";
			}else if(Ext.getCmp('perf1').getValue()=='1'){
				dispCell=dispCell+Ext.getCmp('stockMin').getValue();
			}			
		}
		
		cell=cell+Ext.getCmp('cellMin').getValue();
		
		if (Ext.getCmp('perf2').getValue()=='2' &&  dispCell!=null && dispCell!=''){
			dispCell=dispCell+"-"+Ext.getCmp('cellMin').getValue();
		}else if(Ext.getCmp('perf2').getValue()=='3'){
			dispCell=dispCell+"("+Ext.getCmp('cellMin').getValue()+")";
		}else if(Ext.getCmp('perf2').getValue()=='1'){
			dispCell=dispCell+Ext.getCmp('cellMin').getValue();
		}
		
		if( Ext.getCmp('bayXMin').getValue()!=null && Ext.getCmp('bayXMin').getValue()!='' &&	
		    Ext.getCmp('bayXMax').getValue()!=null && Ext.getCmp('bayXMax').getValue()!='' ){		   
			cell=cell+Ext.getCmp('bayXMin').getValue();	
			
			if (Ext.getCmp('perf3').getValue()=='2'){
				dispCell=dispCell+"-"+Ext.getCmp('bayXMin').getValue();
			}else if(Ext.getCmp('perf3').getValue()=='3'){
				dispCell=dispCell+"("+Ext.getCmp('bayXMin').getValue()+")";
			}else{
				dispCell=dispCell+Ext.getCmp('bayXMin').getValue();
			}
		}
		
		if(Ext.getCmp('floorMin').getValue()!=null && Ext.getCmp('floorMin').getValue()!='' &&
		   Ext.getCmp('floorMax').getValue()!=null && Ext.getCmp('floorMax').getValue()!=''){
			cell=cell+Ext.getCmp('floorMin').getValue();
			
			if (Ext.getCmp('perf4').getValue()=='2'){
				dispCell=dispCell+"-"+Ext.getCmp('floorMin').getValue();
			}else if(Ext.getCmp('perf4').getValue()=='3'){
				dispCell=dispCell+"("+Ext.getCmp('floorMin').getValue()+")";
			}else{
				dispCell=dispCell+Ext.getCmp('floorMin').getValue();
			}
		}
		
		Ext.getCmp('showDispCell').setValue(dispCell);
		Ext.getCmp('showCell').setValue(cell);
	},
	
	importDefcell:function(){
		Ext.create('cms.view.cdef.window.cdef_UploadCellWindow',
		{
			title:'上传'
		}).show();
	},
	
	forbidBut2101:function(){
		if(selectCellNo2101!="N"){
			Ext.Msg.confirm($i18n.prompt,'是否禁用  '+selectCellNo2101.trim()+"?",function(button, text)
			{
				if (button == 'yes') 
				{
					updateCellStatus(1);
					getCellNo();
				}
			});			
		}else{
			Ext.Msg.confirm($i18n.prompt,'是否批量禁用?',function(button, text)
			{
				if (button == 'yes') 
				{
					updateCellStatus(1);
					getCellNo();
				}
			});
		}	
	},
	
	freezeBut2101:function(){
		if(selectCellNo2101!="N"){
			Ext.Msg.confirm($i18n.prompt,'是否冻结 '+selectCellNo2101.trim()+"?",function(button, text)
			{
				if (button == 'yes') 
				{
					updateCellStatus(2);
					getCellNo();
				}
			});
			
		}else{
			Ext.Msg.confirm($i18n.prompt,'是否批量冻结?',function(button, text)
			{
				if (button == 'yes') 
				{
					updateCellStatus(2);
					getCellNo();
				}
			});
		}	
	},
	
	//确认转区
	updateBut2101:function(){
		Ext.Msg.confirm($i18n.prompt,'确认将选中的货位转移到该区域？',function(button, text)
		{
			if (button == 'yes') 
			{
				var grid01=Ext.getCmp('d6_grid_2101').getSelectionModel().getSelection();		
				if(grid01.length!=0){
					var detail=[];
					for(var i=0;i<grid01.length;i++){
						var cell={
							cellNo:grid01[i].get('cellNo'),
						};
						detail.push(cell);
					}
				}
				var str=Ext.encode(detail);
				Ext.Ajax.request({
					url:'cdef_DefWareAction_updateCellWareArea.action',
					params : {
						str:str,
						strWareNo:Ext.getCmp('d6_wareno2101_2').getValue(),
						strAreaNo:Ext.getCmp('d6_areano2101_2').getValue()
					},
					async : false,
					success : function(response, options) {  
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('d6_grid_2101').getStore().load();
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('d6_grid_2101').getStore().load();
						}
					}
				});
			}
		});
	},
	useBut2101:function(){

		if(selectCellNo2101!="N"){
			Ext.Msg.confirm($i18n.prompt,'是否使用'+selectCellNo2101.trim()+"?",function(button, text)
			{
				if (button == 'yes') 
				{
					updateCellStatus(0);
					getCellNo();
				}
			});
			
		}else{
			Ext.Msg.confirm($i18n.prompt,'是否批量使用',function(button, text)
			{
				if (button == 'yes') 
				{
					updateCellStatus(0);
					getCellNo();
				}
			});
		}	
	}
	
	
});


//修改储位状态
function updateCellStatus(flag){
	var detail = [];
	if(selectCellNo2101!="N"){
		var d={
				columnId:'b.cell_no',
				value: selectCellNo2101.trim()
			};
			detail.push(d);
	}else{		
		var d={
				columnId:'b.ware_No',
				value:Ext.getCmp('d4_wareno2101').getValue()
			};
			detail.push(d);
			var d={
				columnId:'b.area_no',
				value:Ext.getCmp('d4_areano2101').getValue()
			};
			detail.push(d);
			var d={
					columnId:'b.stock_no',
					value:Ext.getCmp('d4_stock2101').getValue()
			};
			detail.push(d);
			
			if(Ext.getCmp('d4_stockX2101').getValue()!=null && Ext.getCmp('d4_stockX2101').getValue()!=' ' && Ext.getCmp('d4_stockX2101').getValue()!=''){
				var d={
						columnId:'b.stock_x',
						value:Ext.getCmp('d4_stockX2101').getValue()
				};
				detail.push(d);		
			}
			
			if(Ext.getCmp('d4_bayX2101').getValue()!=null && Ext.getCmp('d4_bayX2101').getValue()!=' '){
				var d={
						columnId:'b.bay_x',
						value:Ext.getCmp('d4_bayX2101').getValue()
				};
				detail.push(d);			
			}
			
			if(Ext.getCmp('d4_stockY2101').getValue()!=null && Ext.getCmp('d4_stockY2101').getValue()!=' '){
				var d={
						columnId:'b.stock_y',
						value:Ext.getCmp('d4_stockY2101').getValue()
				};
				detail.push(d);			
			}			
	}
	
	Ext.Ajax.request({
		url:'cdef_DefWareAction_updateCellStatus.action',
		params : {
			str:Ext.encode(detail),
			flag:flag
		},
		async : false,
		success : function(response, options) {  
			var data = Ext.JSON.decode(response.responseText);//将字符串转换为json类型
			if(data.isSucc){
				Ext.example.msg($i18n.prompt,data.msg);
				selectCellNo2101='N';
			}else{
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('cdef_DefCellGrid2101').removeAll();
			}
		}
	});
}

/**
 * 新增仓库初始化
 * lich
 */
function addCdef_DefWare(){
	Ext.getCmp('cdef_DefWareAddOrEditForm').getForm().reset();
	Ext.getCmp('warehouse_no2101').setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('orgNo2101').setValue('A0000L');
}

/**
 * 新增储区初始化
 * lich
 */
function addCdef_DefArea(){
	Ext.getCmp('cdef_DefAreaAddOrEditForm').getForm().reset();
	Ext.getCmp('d2_ware_no2101').getStore().load();
	
	Ext.getCmp('d2_ware_no2101').setValue(Ext.getCmp('d3_wareno2101').getValue());
	Ext.getCmp('d2_o_type2101').setValue("B");
	Ext.getCmp('d2_area_type2101').setValue("1");
	Ext.getCmp('d2_area_usetype2101').setValue("1");
	Ext.getCmp('d2_area_attribute2101').setValue("0");
	Ext.getCmp('d2_area_quality2101').setValue("0");
	Ext.getCmp('d2_mix_flag2101').setValue("2");
	Ext.getCmp('d2_mix_supplier2101').setValue("1");
	Ext.getCmp('d2_mix_owner2101').setValue("0");
	
	Ext.getCmp('d2_max_qty2101').setValue(1);
	Ext.getCmp('d2_divide_flag_a2101').setValue("0");
	Ext.getCmp('d2_b_divide_flag2101').setValue("0");
	Ext.getCmp('d2_attribute_type2101').setValue("1");
	Ext.getCmp('d2_limit_type2101').setValue("0");
	Ext.getCmp('d2_limit_rate2101').setValue(0);
	Ext.getCmp('d2_pal_out_rate2101').setValue(100);
	Ext.getCmp('d2_b_pick2101').setValue("1");
	Ext.getCmp('d2_area_pick2101').setValue("1");
	Ext.getCmp('d2_a_flag2101').setValue("1");
	Ext.getCmp('d2_io_buffer_flag2101').setValue("0");
	Ext.getCmp('d2_pick_flag2101').setValue("1");
	Ext.getCmp('d2_floor2101').setValue(1);
	Ext.getCmp('d2_advancer_pick_flag2101').setValue("0");
	Ext.getCmp('d2_max_case2101').setValue("0");
	
	Ext.getCmp('d2_locate_time2101').setValue("1");
	Ext.getCmp('d2_b_replenish_type2101').setValue("0");
	Ext.getCmp('d2_b_replenish_rule2101').setValue("1");
	Ext.getCmp('d2_c_replenish_type2101').setValue("0");
	Ext.getCmp('d2_c_replenish_rule2101').setValue("1");
	
	Ext.getCmp('d2_divide_line_flag2101').setValue("0");
	Ext.getCmp('d2_replenish_task_rule2101').setValue("1");
	
	Ext.getCmp('d2_b_replenish_type2101').setValue('1');
	Ext.getCmp('d2_c_replenish_type2101').setValue('1');
	
	Ext.getCmp('d2_keep_label2101').setValue("0");
	Ext.getCmp('d2_max_weight2101').setValue("0");
	Ext.getCmp('d2_max_volume2101').setValue("0");
	Ext.getCmp('d2_item_type2101').setValue("0");
	Ext.getCmp('d2_pick_level2101').setValue("0");

	
}

/**
 * 保存仓库
 * lich
 */
function saveCdef_DefWare(th){
	var but=th;
	if(Ext.getCmp('cdef_DefWareAddOrEditForm').getForm().isValid()){		
		var str={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse_no2101').getValue(),
				wareNo:Ext.getCmp('ware_No2101').getValue()
			},
			wareName:Ext.getCmp('ware_Name2101').getValue(),
			orgNo:Ext.getCmp('orgNo2101').getValue(),
			wareRemark:Ext.getCmp('ware_Remark2101').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date()
		};
		var jsonStr = Ext.encode(str);
		
		var str2={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse_no2101').getValue(),
				wareNo:Ext.getCmp('ware_No2101').getValue(),
				wareName:Ext.getCmp('ware_Name2101').getValue(),
				wareRemark:Ext.getCmp('ware_Remark2101').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date()
			}
		};
		var jsonStr2 = Ext.encode(str2);
		Ext.Ajax.request({
			url:'cdef_DefWareAction_saveCdef_DefWare.action',
			params : {
				str:jsonStr,
				strLog:jsonStr2
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('cdef_DefWareGrid2101').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
}

/**
 * 保存储区
 * lich
 */
function saveCdef_DefArea(th){
 	var but=th;
	if(Ext.getCmp('cdef_DefAreaAddOrEditForm').getForm().isValid()){
		if(Ext.getCmp('d2_area_pick2101').getValue()=='1' 
			&& Ext.getCmp('d2_item_type2101').getValue()=='1'){
			Ext.example.msg($i18n.prompt,'只有保管区才能设置为免检区！');
			return;
		}
		var str={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				wareNo:Ext.getCmp('d2_ware_no2101').getValue(),
				areaNo:Ext.getCmp('d2_area_no2101').getValue()
			},
			areaName:Ext.getCmp('d2_area_name2101').getValue(),
			areaRemark:Ext.getCmp('d2_area_Remark2101').getValue(),
			OType:Ext.getCmp('d2_o_type2101').getValue(),
			areaType:Ext.getCmp('d2_area_type2101').getValue(),
			areaUsetype:Ext.getCmp('d2_area_usetype2101').getValue(),
			areaQuality:Ext.getCmp('d2_area_quality2101').getValue(),
			mixFlag:Ext.getCmp('d2_mix_flag2101').getValue(),
			mixSupplier:Ext.getCmp('d2_mix_supplier2101').getValue(),
			mixOwner:Ext.getCmp('d2_mix_owner2101').getValue(),
			
			maxQty:Ext.getCmp('d2_max_qty2101').getValue(),
			stockNum:"1",
			divideFlag:Ext.getCmp('d2_divide_flag_a2101').getValue(),
			BDivideFlag:Ext.getCmp('d2_b_divide_flag2101').getValue(),
			areaAttribute:Ext.getCmp('d2_area_attribute2101').getValue(),
			attributeType:Ext.getCmp('d2_attribute_type2101').getValue(),
			limitType:Ext.getCmp('d2_limit_type2101').getValue(),
			limitRate:Ext.getCmp('d2_limit_rate2101').getValue(),
			palOutRate:Ext.getCmp('d2_pal_out_rate2101').getValue(),
			BPick:Ext.getCmp('d2_b_pick2101').getValue(),
			areaPick:Ext.getCmp('d2_area_pick2101').getValue(),
			AFlag:Ext.getCmp('d2_a_flag2101').getValue(),
			ioBufferFlag:Ext.getCmp('d2_io_buffer_flag2101').getValue(),
			pickFlag:Ext.getCmp('d2_pick_flag2101').getValue(),
			floor:Ext.getCmp('d2_floor2101').getValue(),
			advancerPickFlag:Ext.getCmp('d2_advancer_pick_flag2101').getValue()=='0'?0:1,
			maxCase:Ext.getCmp('d2_max_case2101').getValue(),
			itemType:Ext.getCmp('d2_area_attribute2101').getValue(),
			divideLineFlag:Ext.getCmp('d2_divide_line_flag2101').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date(),
			
			locateTime:Ext.getCmp('d2_locate_time2101').getValue(),
			BReplenishType:Ext.getCmp('d2_b_replenish_type2101').getValue(),
			BReplenishRule:Ext.getCmp('d2_b_replenish_rule2101').getValue(),
			CReplenishType:Ext.getCmp('d2_c_replenish_type2101').getValue(),
			CReplenishRule:Ext.getCmp('d2_c_replenish_rule2101').getValue(),
			replenishTaskRule:Ext.getCmp('d2_replenish_task_rule2101').getValue(),
			
			keepLabelFlag:Ext.getCmp('d2_keep_label2101').getValue(),
			
			maxWeight:Ext.getCmp('d2_max_weight2101').getValue(),
			maxVolume:Ext.getCmp('d2_max_volume2101').getValue(),
			itemType:Ext.getCmp('d2_item_type2101').getValue(),
			pickLevel:Ext.getCmp('d2_pick_level2101').getValue(),
			maxqtyStrategyId:Ext.getCmp('d2_maxqty_strategy_id2101').getValue()
		};
		var jsonStr = Ext.encode(str);
		
		var str2={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				wareNo:Ext.getCmp('d2_ware_no2101').getValue(),
				areaNo:Ext.getCmp('d2_area_no2101').getValue(),
				areaName:Ext.getCmp('d2_area_name2101').getValue(),
				areaRemark:Ext.getCmp('d2_area_Remark2101').getValue(),
				OType:Ext.getCmp('d2_o_type2101').getValue(),
				areaType:Ext.getCmp('d2_area_type2101').getValue(),
				areaUsetype:Ext.getCmp('d2_area_usetype2101').getValue(),
				areaQuality:Ext.getCmp('d2_area_quality2101').getValue(),
				mixFlag:Ext.getCmp('d2_mix_flag2101').getValue(),
				mixSupplier:Ext.getCmp('d2_mix_supplier2101').getValue(),
				mixOwner:Ext.getCmp('d2_mix_owner2101').getValue(),
				maxQty:Ext.getCmp('d2_max_qty2101').getValue(),
				stockNum:"1",
				divideFlag:Ext.getCmp('d2_divide_flag_a2101').getValue(),
				BDivideFlag:Ext.getCmp('d2_b_divide_flag2101').getValue(),
				areaAttribute:Ext.getCmp('d2_area_attribute2101').getValue(),
				attributeType:Ext.getCmp('d2_attribute_type2101').getValue(),
				limitType:Ext.getCmp('d2_limit_type2101').getValue(),
				limitRate:Ext.getCmp('d2_limit_rate2101').getValue(),
				palOutRate:Ext.getCmp('d2_pal_out_rate2101').getValue(),
				BPick:Ext.getCmp('d2_b_pick2101').getValue(),
				areaPick:Ext.getCmp('d2_area_pick2101').getValue(),
				AFlag:Ext.getCmp('d2_a_flag2101').getValue(),
				ioBufferFlag:Ext.getCmp('d2_io_buffer_flag2101').getValue(),
				pickFlag:Ext.getCmp('d2_pick_flag2101').getValue(),
				floor:Ext.getCmp('d2_floor2101').getValue(),
				advancerPickFlag:Ext.getCmp('d2_advancer_pick_flag2101').getValue()=='0'?0:1,
				maxCase:Ext.getCmp('d2_max_case2101').getValue(),
				itemType:Ext.getCmp('d2_area_attribute2101').getValue(),
				divideLineFlag:Ext.getCmp('d2_divide_line_flag2101').getValue(),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:new Date(),
				
				locateTime:Ext.getCmp('d2_locate_time2101').getValue(),
				BReplenishType:Ext.getCmp('d2_b_replenish_type2101').getValue(),
				BReplenishRule:Ext.getCmp('d2_b_replenish_rule2101').getValue(),
				CReplenishType:Ext.getCmp('d2_c_replenish_type2101').getValue(),
				CReplenishRule:Ext.getCmp('d2_c_replenish_rule2101').getValue(),
				replenishTaskRule:Ext.getCmp('d2_replenish_task_rule2101').getValue()
			}
			
		};
		var jsonStr2 = Ext.encode(str2);
		Ext.Ajax.request({
			method:'post',
			url:'cdef_DefWareAction_saveCdef_DefArea.action',
			params : {
				str:jsonStr,
				strLog:jsonStr2
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('cdef_DefAreaGrid2101').getStore().load();
					Ext.example.msg($i18n.prompt,data.msg);
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
}

/**
 * 关闭仓库界面
 */
function closeWindow(){
	Ext.getCmp('cdef_DefWareAddOrEditWindow').close();
}

/**
 * 填充仓库数据
 */
function loadWare(rowindex2101){
	var record=Ext.getCmp('cdef_DefWareGrid2101').getStore().getAt(rowindex2101);	
	Ext.getCmp('warehouse_no2101').setValue(record.data.warehouseNo);
	Ext.getCmp('ware_No2101').setValue(record.data.wareNo);
	Ext.getCmp('ware_Name2101').setValue(record.data.wareName);	
	Ext.getCmp('orgNo2101').setValue(record.data.orgNo);	
	Ext.getCmp('ware_Remark2101').setValue(record.data.wareRemark);
}

/**
 * 关闭储区界面
 * lich
 */
function closeCdef_DefAreaWindow(){
	Ext.getCmp('cdef_DefAreaAddOrEditWindow').close();
}

/**
 * 填充储区数据
 * lich
 */
function loadArea(rowindex2101){
	var record=Ext.getCmp('cdef_DefAreaGrid2101').getStore().
	getAt(rowindex2101-(Ext.getCmp('cdef_DefAreaGrid2101').
	getStore().currentPage-1)*appConfig.getPageSize());
	if(record.data.limitType=='0')
	{
		Ext.getCmp('d2_limit_rate2101').setFieldLabel($i18n.limit_rate);
	}else
	{
		Ext.getCmp('d2_limit_rate2101').setFieldLabel($i18n.limit_number);
	}
	if(record.data.OType == 'B')
	{
		Ext.getCmp('d2_b_divide_flag2101').setDisabled(false);
	}else
	{
		Ext.getCmp('d2_b_divide_flag2101').setValue('0');
		Ext.getCmp('d2_b_divide_flag2101').setDisabled(true);
	}//保管区 拣货区级别默认为0,拣货区才能设置最大取值策略2016 07 27 by czh
	if(record.data.areaPick == '1')
	{
		Ext.getCmp('d2_pick_level2101').setValue(record.data.pickLevel);
		Ext.getCmp('d2_pick_level2101').setDisabled(false);
		Ext.getCmp('d2_maxqty_strategy_id2101').setValue(record.data.maxqtyStrategyId);
		Ext.getCmp('d2_maxqty_strategy_id2101').setDisabled(false);
	}else
	{
		Ext.getCmp('d2_pick_level2101').setValue('0');
		Ext.getCmp('d2_pick_level2101').setDisabled(true);
		Ext.getCmp('d2_maxqty_strategy_id2101').setDisabled(true);
	}
	Ext.getCmp('d2_ware_no2101').setValue(record.data.wareNo);
	Ext.getCmp('d2_area_no2101').setValue(record.data.areaNo);	
	Ext.getCmp('d2_area_name2101').setValue(record.data.areaName);
	Ext.getCmp('d2_o_type2101').setValue(record.data.OType);
	Ext.getCmp('d2_area_usetype2101').setValue(record.data.areaUsetype);
	Ext.getCmp('d2_area_attribute2101').setValue(record.data.areaAttribute);
	Ext.getCmp('d2_area_type2101').setValue(record.data.areaType);
	Ext.getCmp('d2_mix_flag2101').setValue(String(record.data.mixFlag));
	Ext.getCmp('d2_mix_supplier2101').setValue(record.data.mixSupplier);
	Ext.getCmp('d2_mix_owner2101').setValue(record.data.mixOwner);
	Ext.getCmp('d2_max_qty2101').setValue(record.data.maxQty);
	Ext.getCmp('d2_divide_flag_a2101').setValue(record.data.divideFlag);
	Ext.getCmp('d2_b_divide_flag2101').setValue(record.data.BDivideFlag);
	Ext.getCmp('d2_attribute_type2101').setValue(record.data.attributeType);
	Ext.getCmp('d2_area_quality2101').setValue(record.data.areaQuality);
	Ext.getCmp('d2_limit_type2101').setValue(record.data.limitType);
	Ext.getCmp('d2_limit_rate2101').setValue(record.data.limitRate);
	Ext.getCmp('d2_pal_out_rate2101').setValue(record.data.palOutRate);
	Ext.getCmp('d2_b_pick2101').setValue(record.data.BPick);
	Ext.getCmp('d2_area_pick2101').setValue(record.data.areaPick);
	Ext.getCmp('d2_a_flag2101').setValue(record.data.AFlag);
	Ext.getCmp('d2_io_buffer_flag2101').setValue(record.data.ioBufferFlag);
	Ext.getCmp('d2_pick_flag2101').setValue(record.data.pickFlag);
	Ext.getCmp('d2_floor2101').setValue(record.data.floor);
	Ext.getCmp('d2_advancer_pick_flag2101').setValue(record.data.advancerPickFlag);
	Ext.getCmp('d2_max_case2101').setValue(record.data.maxCase);
	Ext.getCmp('d2_divide_line_flag2101').setValue(record.data.divideLineFlag);
	Ext.getCmp('d2_locate_time2101').setValue(record.data.locateTime);
	Ext.getCmp('d2_b_replenish_type2101').setValue(record.data.BReplenishType);
	Ext.getCmp('d2_b_replenish_rule2101').setValue(record.data.BReplenishRule);
	Ext.getCmp('d2_c_replenish_type2101').setValue(record.data.CReplenishType);
	Ext.getCmp('d2_c_replenish_rule2101').setValue(record.data.CReplenishRule);
	Ext.getCmp('d2_replenish_task_rule2101').setValue(record.data.replenishTaskRule);
	Ext.getCmp('d2_area_Remark2101').setValue(record.data.areaRemark);
	
	Ext.getCmp('d2_keep_label2101').setValue(record.data.keepLabelFlag);
	Ext.getCmp('d2_max_weight2101').setValue(record.data.maxWeight);
	Ext.getCmp('d2_max_volume2101').setValue(record.data.maxVolume);
	Ext.getCmp('d2_item_type2101').setValue(record.data.itemType);
	//Ext.getCmp('d2_pick_level2101').setValue(record.data.pickLevel);
	//Ext.getCmp('d2_maxqty_strategy_id2101').setValue(record.data.maxqtyStrategyId);
}

function loadCdef_DefCell(record){
	Ext.getCmp('d4_cell2101').setValue(record.cellNo);
	Ext.getCmp('d4_dispCellNo2101').setValue(record.dispCellNo);
	Ext.getCmp('d4_max_qty2101').setValue(record.maxQty);	
	Ext.getCmp('d4_max_volume2101').setValue(record.maxVolume);
	Ext.getCmp('d4_max_weight2101').setValue(record.maxWeight);
	Ext.getCmp('d4_max_case2101').setValue(record.maxCase);
	Ext.getCmp('d4_cell_status2101').setValue(record.cellStatus);
	Ext.getCmp('d4_check_status2101').setValue(record.checkStatus);
	Ext.getCmp('d4_mix_flag2101').setValue(String(record.mixFlag));
	Ext.getCmp('d4_limit_type2101').setValue(String(record.limitType));
	Ext.getCmp('d4_limit_rate2101').setValue(record.limitRate);
	Ext.getCmp('d4_b_pick2101').setValue(record.BPick);
	Ext.getCmp('d4_a_flag2101').setValue(record.AFlag);
	Ext.getCmp('d4_mix_supplier2101').setValue(record.mixSupplier);
	Ext.getCmp('d4_mix_owner2101').setValue(record.mixOwner);
	Ext.getCmp('d4_pick_flag2101').setValue(record.pickFlag);
	Ext.getCmp('d4_keep_label2101').setValue(record.keepLabelFlag);
	
}

function changeSelectCellBg(td)
{
	if(selectCellNo2101!='N')
	{
		for(var i=0;i<Ext.query("#cdef_DefCellGrid2101 td div" ).length;i++)
		{
			if(Ext.query("#cdef_DefCellGrid2101 td div" )[i].innerText==selectCellNo2101.trim())
			{
				Ext.query("#cdef_DefCellGrid2101 td div" )[i].style.cssText= "text-align: left; background-color: rgb("+color_r2101+", "+color_g2101+","+color_b2101+");";				
			}
		}
	}
	selectCellNo2101=td.innerText;
	if(td.innerHTML.indexOf("#F3939")!=-1|| td.innerHTML.indexOf("background-color: rgb(255, 255, 255")!=-1)
	{
		color_r2101=255;
		color_g2101=255;
		color_b2101=255;
	}else if(td.innerHTML.indexOf("#F78709")!=-1 || td.innerHTML.indexOf("background-color: rgb(247, 135, 9")!=-1)//橙色
	{
		color_r2101=247;
		color_g2101=135;
		color_b2101=9;
	}else if(td.innerHTML.indexOf("#42E61A")!=-1 || td.innerHTML.indexOf("background-color: rgb(66, 230, 26)")!=-1)//绿色
	{
		color_r2101=66;
		color_g2101=230;
		color_b2101=26;
	}else if(td.innerHTML.indexOf("#0000EE")!=-1 || td.innerHTML.indexOf("background-color: rgb(0, 0, 238)")!=-1)//蓝色
	{
		color_r2101=0;
		color_g2101=0;
		color_b2101=238;
	}	
	
	td.innerHTML="<div class='x-grid-cell-inner ' style='text-align: left; background-color:#FFFF00;'>"+td.innerText.trim()+"</div>";
}
//货位储位列表（转区tab用）
function getCellNoGrid(){

	var detail = [];
	var d={
		columnId:'a.ware_No',
		value:Ext.getCmp('d6_wareno2101').getValue()
	};
	detail.push(d);
	var d={
		columnId:'a.area_no',
		value:Ext.getCmp('d6_areano2101').getValue()
	};
	detail.push(d);
	if(Ext.getCmp('d6_stock2101').getValue()!=null && Ext.getCmp('d6_stock2101').getValue()!=' '){
		var d={
				columnId:'a.stock_no',
				value:Ext.getCmp('d6_stock2101').getValue()
		};
		detail.push(d);
	}
	if(Ext.getCmp('d6_stockX2101').getValue()!=null && Ext.getCmp('d6_stockX2101').getValue()!=' '){
		var d={
				columnId:'a.stock_x',
				value:Ext.getCmp('d6_stockX2101').getValue()
		};
		detail.push(d);		
	}
	
	if(Ext.getCmp('d6_bayX2101').getValue()!=null && Ext.getCmp('d6_bayX2101').getValue()!=' '){
		var d={
				columnId:'a.bay_x',
				value:Ext.getCmp('d6_bayX2101').getValue()
		};
		detail.push(d);			
	}
	
	if(Ext.getCmp('d6_stockY2101').getValue()!=null && Ext.getCmp('d6_stockY2101').getValue()!=' '){
		var d={
				columnId:'a.stock_y',
				value:Ext.getCmp('d6_stockY2101').getValue()
		};
		detail.push(d);			
	}
	var jsonDetail1 = Ext.encode(detail);
	var wheresql = {
		str : jsonDetail1
	};
	Ext.apply(Ext.getCmp('d6_grid_2101')
			.getStore().proxy.extraParams,
			wheresql);	
	Ext.getCmp('d6_grid_2101').getStore().load();

}
//获取储位
function getCellNo(){
	var detail = [];
	var d={
		columnId:'a.ware_No',
		value:Ext.getCmp('d4_wareno2101').getValue()
	};
	detail.push(d);
	var d={
		columnId:'a.area_no',
		value:Ext.getCmp('d4_areano2101').getValue()
	};
	detail.push(d);
	var d={
			columnId:'a.stock_no',
			value:Ext.getCmp('d4_stock2101').getValue()
	};
	detail.push(d);
	
	if(Ext.getCmp('d4_stockX2101').getValue()!=null && Ext.getCmp('d4_stockX2101').getValue()!=' ' && Ext.getCmp('d4_stockX2101').getValue()!=''){
		var d={
				columnId:'a.stock_x',
				value:Ext.getCmp('d4_stockX2101').getValue()
		};
		detail.push(d);		
	}
	
	if(Ext.getCmp('d4_bayX2101').getValue()!=null && Ext.getCmp('d4_bayX2101').getValue()!=' '){
		var d={
				columnId:'a.bay_x',
				value:Ext.getCmp('d4_bayX2101').getValue()
		};
		detail.push(d);			
	}
	
	if(Ext.getCmp('d4_stockY2101').getValue()!=null && Ext.getCmp('d4_stockY2101').getValue()!=' '){
		var d={
				columnId:'a.stock_y',
				value:Ext.getCmp('d4_stockY2101').getValue()
		};
		detail.push(d);			
	}
	
	Ext.Ajax.request({
	url:'cdef_DefWareAction_getCdef_DefCell.action',
	params : {
		str:Ext.encode(detail)
	},
	success : function(response, options) {  
		var data = Ext.JSON.decode(response.responseText);//将字符串转换为json类型
		if(data.isSucc){
			var json=Ext.JSON.decode(data.obj);
			var cm = new Ext.grid.column.Column(json.columModle);    
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
			            	'cellclick':function(th,td,cellIndex,record,tr,rowindex,e,eOpts ){
			            	    //库存查询
			            		var detail1 = [];
			            		var d={
										columnId:'a.enterprise_no',
										value:Ext.get('enterpriseNo').getValue()
									};
									detail1.push(d);
			            		var d={
									columnId:'a.warehouse_no',
									value:record.data.col0
								};
								detail1.push(d);
								d={
									columnId:'a.cell_no',
									value:Ext.isIE8?td.innerText:td.textContent
								};
								detail1.push(d);
						
								var jsonDetail1 = Ext.encode(detail1);
								var wheresql = {
									str : jsonDetail1
								};
								
								//拣货位查询
								var detail2 = [];
			            		var a={
										columnId:'c.enterprise_no',
										value:Ext.get('enterpriseNo').getValue()
									};
								detail2.push(a);
			            		var a={
									columnId:'c.warehouse_no',
									value:record.data.col0
								};
								detail2.push(a);
								var a={
									columnId:'c.cell_no',
									value:Ext.isIE8?td.innerText:td.textContent
								};
								detail2.push(a);
								var jsonDetail2 = Ext.encode(detail2);
								var strQuery = {
										strQuery : jsonDetail2
								};
								Ext.apply(Ext.getCmp('stock_contentGrid2101').getStore().proxy.extraParams,
										wheresql);
								Ext.getCmp('stock_contentGrid2101').getStore().load();
								Ext.apply(Ext.getCmp('cset_article_cellGrid2101').getStore().proxy.extraParams,
										strQuery);
								Ext.getCmp('cset_article_cellGrid2101').getStore().load();
								changeSelectCellBg(td);
			            		}
			            	}
	            		});
			
			Ext.getCmp('cdef_DefCellGrid2101').removeAll();
			Ext.getCmp('cdef_DefCellGrid2101').add(grid);  
			Ext.getCmp('cdef_DefCellGrid2101').doLayout();
		}else{
			Ext.getCmp('cdef_DefCellGrid2101').removeAll();
		}
	}
});

	
	
}