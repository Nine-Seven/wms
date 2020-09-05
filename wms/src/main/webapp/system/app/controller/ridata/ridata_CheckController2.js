/**
 * 模块名称：返配表单验收
 * 模块编码：6401
 * 创建：Jun
 */
var isCanEdit6401=false;
var g_RICheckSelectQuality6401='';//是否选择品质: 1不选择 ;0：选择]
Ext.define('cms.controller.ridata.ridata_CheckController2',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_CheckUI2'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'ridata_CheckUI2 button[name=add]':{
				click:this.add
			},//修改
			'ridata_CheckUI2 button[name=edit]':{
				click:this.edit
			},//撤消
			'ridata_CheckUI2 button[name=undo]':{
				click:this.undo
			},//查找
			'ridata_CheckUI2 button[name=query]':{
				click:this.query
			},//保存
			'ridata_CheckUI2 button[name=save]':{
				click:this.save
			},//刷新
			'ridata_CheckUI2 button[name=refresh]':{
				click:this.refresh
			},//Grid选择
			'ridata_CheckUI2 grid[id=grid_01_6401]':{
				beforeselect:this.grid_01_6401before,
				selectionchange:this.grid_01_6401change
			},//商品明细编辑 
			'ridata_CheckUI2 grid[id=grid_02_6401]':{
				beforeedit:this.checkDbefore,
				edit:this.ridataCheckEdit,
				itemdblclick:this.checkDgridclick
			},//返配汇总单号选择
			'ridata_CheckUI2 ridata_UntreadNoCombo[id=s_untread_no16401]':{
				expand:this.untreadNoexpand,
				select:this.untreadNoselect
			},
			'ridata_CheckUI2 form field':{
				specialkey:this.boxkeydown
			},//包装选择
			'bdef_PackingQtyCombo[id=packing_qty6401]':{
				focus:this.packingQtyfocus,
				select:this.packingQtyselect
			}
		});
	},
	
	initializtion:function(){
		isCanEdit6401=false;
		
		//显示变量0为不显示，1为显示
		var planBox_6401=commonGetModuleField('6401','planBox')[0].flag;
		var planQmin_6401=commonGetModuleField('6401','planQmin')[0].flag;
		var planDis_6401=commonGetModuleField('6401','planDis')[0].flag;
		var packingUnit_6401=commonGetModuleField('6401','packingUnit')[0].flag;
		var packingSpec_6401=commonGetModuleField('6401','packingSpec')[0].flag;
		
		if(planBox_6401==0){
			Ext.getCmp('planBox_6401').setVisible(false);
		}
		if(planQmin_6401==0){
			Ext.getCmp('planQmin_6401').setVisible(false);
		}
		if(planDis_6401==0){
			Ext.getCmp('planDis_6401').setVisible(false);
		}
		if(packingUnit_6401==0){
			Ext.getCmp('packingUnit_6401').setVisible(false);
		}
		if(packingSpec_6401==0){
			Ext.getCmp('packingSpec_6401').setVisible(false);
		}
		
	},
	
	packingQtyfocus:function(th){
		th.getStore().proxy.extraParams.strWheresql = 
		Ext.getCmp('grid_02_6401').getSelectionModel().getSelection()[0].get('articleNo');
		th.getStore().load();
	},
	
	packingQtyselect:function(combo){
		var data = Ext.getCmp('grid_02_6401').getSelectionModel().getSelection();
		Ext.Ajax.request({
			method:'post',
			url:'bdef_DefarticleAction_queryPackingUnitAndSpec',
			params:{
				strArticleNo:data[0].get('articleNo'),
				strPackingQty:combo.getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	data[0].set('unit',res[0].packingUnit);
		    	data[0].set('packingSpec',res[0].spec);
		    }
		});
	},
	add:function(){
		commonMenu4Button('menu6401','add');
		Ext.getCmp('form_01_6401').getForm().reset();
		Ext.getCmp('grid_02_6401').getStore().removeAll();
		commonSetMsterReadOnlyByArray(
		new Array('s_untread_no16401','check_worker16401'),
		false);
		Ext.get('rgstName6401').dom.innerHTML=Ext.get('workerNo').getValue();
		Ext.get('rgstDate6401').dom.innerHTML=Ext.Date.format(new Date(),'Y-d-m H:m:s');
		Ext.get('updtName6401').dom.innerHTML='';
		Ext.get('updtDate6401').dom.innerHTML='';
		Ext.getCmp('s_untread_no16401').focus();
		isCanEdit6401=true;
		//Ext.getCmp('grid_01_6401').setDisabled(true);
	},
	
	edit:function(){
		commonMenu4Button('menu6401','edit');
		isCanEdit6401=true;
	},
	
	undo:function(){
		isCanEdit6401=false;
		Ext.getCmp('form_01_6401').getForm().reset();
		Ext.getCmp('grid_02_6401').getStore().removeAll();
		commonMenu4Button('menu6401','undo');
		commonSetMsterReadOnlyByArray(
		new Array('s_untread_no16401','check_worker16401'),
		true);
	},
	
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=6401;
		queryGrid='grid_01_6401';	
	},
	
	grid_01_6401before:function(){
		if(isCanEdit6401)
	    {
	        return false;  
	    }
	},
	
	grid_01_6401change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var sql='';
			sql={
				flag:'1',
				whereSql:record.data.checkNo
			};
			var whereSql={
				whereSql:sql
			};
			Ext.apply(Ext.getCmp('grid_02_6401').getStore().proxy.extraParams,whereSql);
			Ext.getCmp('grid_02_6401').getStore().removeAll();
			Ext.getCmp('grid_02_6401').getStore().load();
			
			Ext.getCmp('s_untread_no16401').getStore().add({
	    		value:record.data.SUntreadNo,
	    		dropValue:record.data.SUntreadNo,
	    		text:record.data.SUntreadNo
	   	    });
	   	    Ext.getCmp('s_untread_no16401').setValue(record.data.SUntreadNo);
	   	    
	   	    Ext.getCmp('check_worker16401').getStore().add({
	    		value:record.data.checkWorker,
	    		dropValue:'['+record.data.checkWorker+']'+record.data.workerName,
	    		text:record.data.workerName
	   	    });
			Ext.getCmp('check_worker16401').setValue(record.data.checkWorker);
			Ext.get('rgstName6401').dom.innerHTML=record.data.rgstName;
			Ext.get('rgstDate6401').dom.innerHTML=record.data.rgstDate;
			Ext.get('updtName6401').dom.innerHTML=record.data.updtName;
			Ext.get('updtDate6401').dom.innerHTML=record.data.updtDate;
			
			commonSetMsterReadOnlyByArray(
			new Array('s_untread_no16401','check_worker16401'),
			true);
			isCanEdit6401=false;
		}else{
			Ext.getCmp('grid_02_6401').getStore().removeAll();
		}
	},
	
	save:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		};
		if(!commonCheckMster('form_01_6401'))
		{
			return;
		};
		Ext.Msg.confirm("提示", "确定保存？",function(button, text) {
			if (button == 'yes') {
				var sUntreadNo=Ext.getCmp('s_untread_no16401').getValue();
				var checkWorker=Ext.getCmp('check_worker16401').getValue();
				
				var master={
					sUntreadNo:sUntreadNo,
					dockNo:workSpaceNo,
					checkWorker:checkWorker
				};
				
				var gridcount=Ext.getCmp("grid_02_6401").getStore().getCount();
				if(gridcount>0)
				{
					if(!commonCheckdetailgrid('grid_02_6401',0,gridcount-1))
					{
						return;
					}	
				}else{			
					Ext.example.msg('提示',"表体不允许为空，请输入表体！");
					return;
				}
				var msgShow = commonMegShow("正在保存,请稍等...");
				var detail = [];
				for(var i=0;i<gridcount;i++){
					var record  = Ext.getCmp('grid_02_6401').getStore().getAt(i);
					var d={
						enterpriseNo:Ext.get('enterpriseNo').getValue(),	
						warehouseNo:record.get('warehouseNo'),
						ownerNo:record.get('ownerNo'),
						supplierNo:record.get('supplierNo'),
						untreadNo:record.get('untreadNo'),
						articleNo:record.get('articleNo'),
						barcode:record.get('barcode'),
						packingQty:record.get('packingQty'),
						checkQty:record.get('planBox')*record.get('packingQty') + 
							record.get('planQmin')*record.get('qminOperatePacking') + 
							record.get('planDis'),
						dockNo:workSpaceNo,
						workerNo:Ext.getCmp('check_worker16401').getValue(),
						produceDate:record.get('produceDate'),
						expireDate:record.get('expireDate'),
						lotNo:record.get('lotNo'),
						quality:record.get('quality')==''?'0':record.get('quality'),
						SUntreadNo:record.get('SUntreadNo'),
						qualityFlag:record.get('qualityFlag'),
						classType:record.get('classType'),
						untreadType:record.get('untreadType'),
						strPrintFlag:Ext.getCmp('rdoCheckType6401').getValue().rd
					};
					detail.push(d);
				}
				
				var jsonMaster = Ext.encode(master);
				var jsonDetail = Ext.encode(detail);
				var params = {
					jsonMaster:jsonMaster,
					jsonDetail:jsonDetail
				};
				Ext.Ajax.request({
					method:'POST',
					url:'ridata_CheckAction2_save',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						msgShow.hide();
						if(data.isSucc){
							commonMenu4Button('menu6401','save');
							Ext.example.msg('提示',data.msg);
							isCanEdit6401=false;
							commonSetMsterReadOnlyByArray(
								new Array('s_untread_no16401','check_worker16401'),
							true);
							Ext.getCmp('check_worker16401').setValue(null);
							Ext.getCmp('s_untread_no16401').setValue(null);
							Ext.getCmp('grid_01_6401').getStore().load();
							Ext.getCmp('grid_02_6401').getStore().load();
						}else{
							Ext.example.msg('提示',data.msg+data.obj);
						}				
					},
					failure:function(response){
						Ext.example.msg('提示',"提交不上,可能是网络问题");
					}
				});
				}
		});
	},
	
	refresh:function(){
		Ext.getCmp('grid_01_6401').getStore().load();
	},
	
	checkDbefore:function(editor,e,eOpts){
		if(!isCanEdit6401)
	    {
	        e.cancel = true;
	        return  false;  
	    }else{
	    	if(e.field=='produceDate' && (e.record.data.lotType==1 || e.record.data.lotType==4)){
	    		editor.cancel = true;
	        	return  false; 
	    	}
	    	if(e.field=='lotNo' && (e.record.data.lotType==2 || e.record.data.lotType==4)){
	    		editor.cancel = true;
	        	return  false; 
	    	}
	    }
	},
	
	ridataCheckEdit:function(editor,e,eOpts){
		if(e.field=='produceDate'){
			/*if(!Ext.isEmpty(e.value)){
				editor.context.record.set('expireDate',Ext.Date.format(Ext.Date.add(new Date(e.value), Ext.Date.DAY, parseInt(e.record.data.expiryDays)),'Y-m-d'));
				if(editor.grid.getStore().
					findBy(function(record, id){  
						return record.internalId!=editor.context.record.internalId 
						&& record.get('articleNo')==editor.context.record.data.articleNo
						&& record.get('packingQty')==editor.context.record.data.packingQty
						&& Ext.Date.format(new Date(record.get('expireDate')),'Y-m-d') ==Ext.Date.format(new Date(editor.context.record.data.expireDate),'Y-m-d')
						&& record.get('lot_no')==editor.context.record.data.lotNo;
					})!=-1)				
				{
					editor.context.record.set('produceDate',editor.context.originalValue);
					Ext.example.msg('提示', "【商品编码】、【包装单位】、【生产日期】、【批号】不能重复，请重新输入！");
				}
			}*/
		}else if(e.field=='checkbox' ){
			if(e.record.data.checkQty>e.record.data.untreadQty){
				Ext.example.msg('提示',"不能超量验收！");
				editor.context.record.set(e.field,editor.context.originalValue);
				if(e.field=='checkbox'){
					editor.context.record.set('checkQty',editor.context.originalValue*e.record.data.packingQty);
				}
			}
		}
	},
	
	untreadNoexpand:function(){
		Ext.getCmp('s_untread_no16401').getStore().removeAll();
		Ext.getCmp('s_untread_no16401').getStore().load();
	},
	
	untreadNoselect:function(){
		if(!Ext.isEmpty(Ext.getCmp('s_untread_no16401').getValue())){
			var strOwnerNo='';
			Ext.Ajax.request({
				method:'post',
				url:'ridata_CheckAction2_getOwnerNo',
				async:false,
				params:{
					strSUntreadNo:Ext.getCmp('s_untread_no16401').getValue()
			    },
			    success:function(response){
			    	var res = Ext.decode(response.responseText);
			    	if(res.isSucc){
			    		strOwnerNo=res.msg;
					}else{
						Ext.example.msg($i18n_prompt.prompt,res.msg+res.obj);
					}
			    	
			    }
			});
		}
		if(!Ext.isEmpty(strOwnerNo)){
			getSystemPara4301(strOwnerNo);
			if(!Ext.isEmpty(g_RICheckSelectQuality6401)){
				if(g_RICheckSelectQuality6401=='0'){
					Ext.getCmp('qualityFlagList').setVisible(false);
				}else{
					Ext.getCmp('qualityFlagList').setVisible(true);
				}
			}else{
				Ext.getCmp('qualityFlagList').setVisible(true);
			}
			var sql='';
			sql={
				flag:'0',
				whereSql:Ext.getCmp('s_untread_no16401').getValue()
			};
			var whereSql={
				whereSql:sql
			};
			Ext.apply(Ext.getCmp('grid_02_6401').getStore().proxy.extraParams,whereSql);
			Ext.getCmp('grid_02_6401').getStore().removeAll();
			Ext.getCmp('grid_02_6401').getStore().load();
		}
	},
	
	checkDgridclick:function(th,record,item,index,e,eOpts){
		if(isCanEdit6401){
			//if(record.data.expiryDays!=-1){ 
			Ext.Msg.confirm("提示", "确定拆笔？",function(button, text) {
				if (button == 'yes') {
					var data = Ext.getCmp('grid_02_6401').getSelectionModel().getSelection();
					var r = Ext.create('cms.model.ridata.ridata_CheckDModel', {
					});
					r.set('articleNo',data[0].get('articleNo'));
					r.set('articleName',data[0].get('articleName'));
					r.set('barcode',data[0].get('barcode'));
					r.set('pobox',data[0].get('pobox'));
					r.set('checkQty',0);
					r.set('planBox',0);
					r.set('planQmin',0);
					r.set('planDis',0);
					r.set('qminOperatePacking',data[0].get('qminOperatePacking'));
					r.set('packingQty',data[0].get('packingQty'));
					r.set('packingUnit',data[0].get('packingUnit'));
					r.set('packingSpec',data[0].get('packingSpec'));		
					r.set('untreadQty',data[0].get('untreadQty'));
					r.set('rowId',data[0].get('rowId'));
					r.set('expiryDays',data[0].get('expiryDays'));
					r.set('quality',data[0].get('quality'));
					r.set('qualityFlag',data[0].get('qualityFlag'));
					r.set('classType',data[0].get('classType'));
					
					r.set('warehouseNo',data[0].get('warehouseNo'));
					r.set('ownerNo',data[0].get('ownerNo'));
					r.set('supplierNo',data[0].get('supplierNo'));
					r.set('untreadNo',data[0].get('untreadNo'));
					//r.set('workerNo',Ext.getCmp('check_worker16401').getValue());
					//r.set('produceDate',data[0].get('produceDate'));
					r.set('SUntreadNo',data[0].get('SUntreadNo'));
					//r.set('rowId',data[0].data.rowId);		
					//r.set('guarantee',data[0].data.guarantee);
					Ext.getCmp('grid_02_6401').store.insert(data[0].index+1,r);
					
					for(var i=1;i<=Ext.getCmp('grid_02_6401').getStore().getCount();i++ ){
						Ext.getCmp('grid_02_6401').getStore().getAt(i-1).set('rowId',i);
						Ext.getCmp('grid_02_6401').getStore().getAt(i-1).index=i;
					}
				}
			});
			//}
		}
	},
	
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) 
		{
			if(th.id=="s_untread_no16401"){
				Ext.getCmp('check_worker16401').focus();
			}else if(th.id=="check_worker16401"){
				Ext.getCmp('s_untread_no16401').focus();
			}else{
				th.nextSibling().focus();
			}
        }
	}
});
//取系统参数
function getSystemPara4301(strOwnerNo){
	g_RICheckSelectQuality6401
	=commonGetSystemParams(strOwnerNo,'RI_Check_SelectQuality','RI','RI_IC')[0].sdefine;

}
