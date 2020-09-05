/**
 * 模块名称：返配上架回单
 * 模块编码：6301
 * 创建：周欢
 */
var g_strIsCanEdit6301=false;
Ext.define('cms.controller.ridata.ridata_InstockController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_InstockUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//修改
			'ridata_InstockUI button[name=edit]':
			{
				click:this.edit
			},//撤消
			'ridata_InstockUI button[name=undo]':{
				click:this.undo
			},//保存
			'ridata_InstockUI button[name=save]':{
				click:this.save
			},//刷新
			'ridata_InstockUI button[name=refresh]':{
				click:this.refresh
			},//查找
			'ridata_InstockUI button[name=query]':{
				click:this.query
			},//Grid选择
			'ridata_InstockUI grid[id=gridInstockM6301]':{
				beforeselect:this.gridInstockM6301before,
				selectionchange:this.gridInstockM6301change
			},//商品明细编辑
			'ridata_InstockUI grid[id=gridInstockD6301]':{
				beforeedit:this.gridInstockD6301before,
				edit:this.gridInstockD
			},//根据质量类型加载单头
			'ridata_InstockUI combo[id=cmbQ_type6301]':{
				select:this.selectcmbQ_type6301
			},//根据波次号加载单头
			'ridata_InstockUI combo[id=cmbLocate_no6301]':{
				select:this.selectcmbLocate_no6301
			},//取消上架
			'ridata_InstockUI button[id=butCloseInstock6301]':{
				click:this.butCloseInstock6301
			},//回单保存
			'ridata_InstockUI button[id=butsaveInstock6301]':{
				click:this.butsaveInstock6301
			}
		});
	},
	//根据质量类型加载单头
	selectcmbQ_type6301:function(){
		var listDetail1 = [];
		var strDtl={
				columnId:'a.quality_flag',
				value:Ext.getCmp('cmbQ_type6301').getValue()
		};
		listDetail1.push(strDtl);
		
		if(Ext.getCmp('cmbLocate_no6301').getValue() != null){
			var strDtl={
					columnId:'d.wave_no',
					value:Ext.getCmp('cmbLocate_no6301').getValue()
			};
			listDetail1.push(strDtl);
		}
		var jsonlistDetail1 = Ext.encode(listDetail1);
		
		var str = {
			strQuery: jsonlistDetail1 
		};
		//加载该类型下的单据列表
		Ext.apply(Ext.getCmp('gridInstockM6301').getStore().proxy.extraParams,str);
		Ext.getCmp('gridInstockM6301').getStore().removeAll();
		Ext.getCmp('gridInstockM6301').getStore().load();
		
	},
	
	//根据波次加载单头
	selectcmbLocate_no6301:function(){
		var listDetail1 = [];
		var strDtl={
				columnId:'d.wave_no',
				value:Ext.getCmp('cmbLocate_no6301').getValue()
		};
		listDetail1.push(strDtl);
		
		if(Ext.getCmp('cmbQ_type6301').getValue() != null){
			var strDtl={
					columnId:'a.quality_flag',
					value:Ext.getCmp('cmbQ_type6301').getValue()
			};
			listDetail1.push(strDtl);
		}
		var jsonlistDetail1 = Ext.encode(listDetail1);
		
		var str = {
			strQuery: jsonlistDetail1 
		};
		Ext.apply(Ext.getCmp('gridInstockM6301').getStore().proxy.extraParams,str);
		Ext.getCmp('gridInstockM6301').getStore().removeAll();
		Ext.getCmp('gridInstockM6301').getStore().load();
		
		
	},
	
	//取消上架
	butCloseInstock6301:function(){
		var data = Ext.getCmp('gridInstockM6301').getSelectionModel().getSelection();
        if(data.length == 0){
        	Ext.example.msg($i18n.prompt,'请选择单据！');
        }else{
        	Ext.Msg.confirm('提示','确定取消？',function(button,text){
    			if(button=='yes'){
    				Ext.Ajax.request({
    					method:'POST',
    					url:'ridata_InstockAction_tscCloseInstock',
    					params:{strWheresql:data[0].get("instockNo")},
    					success:function(response){
    						var data = Ext.decode(response.responseText);
    						if(data.isSucc){
    						
    							Ext.example.msg('提示',data.msg);
    							
    							Ext.getCmp('cmbInstock_worker6301').setValue(null);
    							Ext.getCmp('gridInstockM6301').getStore().load();
    							Ext.getCmp('gridInstockD6301').getStore().removeAll();
    						}else{
    							Ext.example.msg('提示',data.msg+data.obj);
    							
    						}				
    					},
    				});
    			}
        	});       	
        }
	},
	
	//回单保存
	butsaveInstock6301:function(){
		if(Ext.getCmp('cmbInstock_worker6301').getValue() == null || Ext.getCmp('cmbInstock_worker6301').getValue() == ""){
			Ext.example.msg('提示','请输入上架人员');
			return;
		}
		
		if(Ext.getCmp('cellNo6301').getValue() == null || Ext.getCmp('cellNo6301').getValue() == ""){
			Ext.example.msg('提示','请输入储位');
			return;
		}
		
		var data = Ext.getCmp('gridInstockM6301').getSelectionModel().getSelection();
        if(data.length == 0){
        	Ext.example.msg($i18n.prompt,'请选择单据！');
        }else{
        	
        	Ext.Msg.confirm('提示','确定上架？',function(button,text){
    			if(button=='yes'){
    				Ext.Ajax.request({
    					method:'POST',
    					url:'ridata_InstockAction_saveInstock',
    					params:{
    						instockNo:data[0].get("instockNo"),
    						cellNo:Ext.getCmp('cellNo6301').getValue(),
    						workerNo:Ext.getCmp('cmbInstock_worker6301').getValue()
    					},
    					success:function(response){
    						var data = Ext.decode(response.responseText);
    						if(data.isSucc){					
    							Ext.example.msg('提示',data.msg);						
    							Ext.getCmp('cmbInstock_worker6301').setValue(null);
    							Ext.getCmp('gridInstockM6301').getStore().load();
    							Ext.getCmp('gridInstockD6301').getStore().removeAll();
    						}else{
    							Ext.example.msg('提示',data.msg+data.obj);
    							
    						}				
    					},
    				});
    				
    			}
        	});        	        	          	
        }	
	},
	
	gridInstockD:function(editor,e,eOpts){
		
		if(e.field=='realCellNo'){
			if(!Ext.isEmpty(e.value)){
				if(e.value.toUpperCase()==''||e.value.toUpperCase()==null){
					editor.context.record.set(e.field,editor.context.originalValue);
					Ext.example.msg($i18n.prompt,'实际上架储位不能为空！');
					return;
				}
				for(var i=editor.context.record.index;i<Ext.getCmp('gridInstockD6301').getStore().data.length;i++)
				{
					Ext.getCmp('gridInstockD6301').getStore().getAt(i).set('realCellNo',e.value);
				}
			}
		}
		
	},
	
	initializtion:function(){
		g_strIsCanEdit6301=false;
		
		//显示变量0为不显示，1为显示
		var planBox_6301=commonGetModuleField('6301','planBox')[0].flag;
		var planQmin_6301=commonGetModuleField('6301','planQmin')[0].flag;
		var planDis_6301=commonGetModuleField('6301','planDis')[0].flag;
		var packingUnit_6301=commonGetModuleField('6301','packingUnit')[0].flag;
		var packingSpec_6301=commonGetModuleField('6301','packingSpec')[0].flag;
		
		var realBox_6301=commonGetModuleField('6301','realBox')[0].flag;
		var realQmin_6301=commonGetModuleField('6301','realQmin')[0].flag;
		var realDis_6301=commonGetModuleField('6301','realDis')[0].flag;
		
		if(planBox_6301==0){
			Ext.getCmp('planBox_6301').setVisible(false);
		}
		if(planQmin_6301==0){
			Ext.getCmp('planQmin_6301').setVisible(false);
		}
		if(planDis_6301==0){
			Ext.getCmp('planDis_6301').setVisible(false);
		}
		if(packingUnit_6301==0){
			Ext.getCmp('packingUnit_6301').setVisible(false);
		}
		if(packingSpec_6301==0){
			Ext.getCmp('packingSpec_6301').setVisible(false);
		}
		if(realBox_6301==0){
			Ext.getCmp('realBox_6301').setVisible(false);
		}
		if(realBox_6301==0){
			Ext.getCmp('realBox_6301').setVisible(false);
		}
		if(realDis_6301==0){
			Ext.getCmp('realDis_6301').setVisible(false);
		}
		
	},
	
	//修改
	edit:function(){
		var data = Ext.getCmp('gridInstockM6301').getSelectionModel().getSelection();
        if(data.length != 0){
			commonMenu4Button('menu6301','edit');
			g_strIsCanEdit6301=true;
			Ext.getCmp('cmbInstock_worker6301').focus();
			commonSetMsterReadOnlyByArray(
			new Array('cmbInstock_worker6301','dateInstock_date6301'),
			false);
        }
	},
	
	//撤销
	undo:function(){
		commonMenu4Button('menu6301','undo');
		g_strIsCanEdit6301=false;
		commonSetMsterReadOnlyByArray(
		new Array('cmbInstock_worker6301','dateInstock_date6301'),
		true);
		Ext.getCmp('cmbInstock_worker6301').setValue(null);
	},
	
	//保存
	save:function(){
		if(!commonCheckMster('formCondition6301')){
			return;
		}
		var gridcount=Ext.getCmp("gridInstockD6301").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('gridInstockD6301',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		Ext.Msg.confirm("提示", "确定保存？",
		function(button, text) {
		if (button == 'yes') {
			var instockWorker=Ext.getCmp('cmbInstock_worker6301').getValue();
			var instockDate=Ext.getCmp('dateInstock_date6301').getValue();
			var detail1 = [];
			for(var i=0;i<gridcount;i++){
				var record=Ext.getCmp('gridInstockD6301').getStore().getAt(i);
				var d={
					warehouseNo:record.get('warehouseNo'),
					instockNo:record.get('instockNo'),
					destCellNo:record.get('destCellNo'),
					realCellNo:record.get('realCellNo'),
					labelNo:record.get('labelNo'),
					articleNo:record.get('articleNo'),
					produceDate:record.get('produceDate'),
					packingQty:record.get('packingQty'),
					articleQty:record.get('articleQty')*record.get('packingQty'),
					userId:instockWorker
				};
				detail1.push(d);
			}
			var jsonDetail1 = Ext.encode(detail1);
			var params={
				jsonDetail1:jsonDetail1
			};
			Ext.Ajax.request({
				method:'POST',
				url:'ridata_InstockAction_save',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						commonMenu4Button('menu6301','save');
						Ext.example.msg('提示',data.msg);
						commonSetMsterReadOnlyByArray(
						new Array('cmbInstock_worker6301','dateInstock_date6301'),
						true);
						g_strIsCanEdit6301=false;
						Ext.getCmp('cmbInstock_worker6301').setValue(null);
						Ext.getCmp('gridInstockM6301').getStore().load();
						Ext.getCmp('gridInstockD6301').getStore().removeAll();
					}else{
						Ext.example.msg('提示',data.msg+data.obj);
						commonSetMsterReadOnlyByArray(
						new Array('cmbInstock_worker6301','dateInstock_date6301'),
						true);
					}				
				},
				failure:function(response){
					Ext.example.msg('提示',"提交不上,可能是网络问题");
				}
			});
		}
		});
	},
	
	//刷新
	refresh:function(){
		Ext.getCmp('cmbQ_type6301').setValue('');
		Ext.getCmp('cmbLocate_no6301').setValue('');
		var str = {
				strQuery: null 
		};
		Ext.apply(Ext.getCmp('gridInstockM6301').getStore().proxy.extraParams,str);
		Ext.getCmp('gridInstockM6301').getStore().removeAll();
		Ext.getCmp('gridInstockM6301').getStore().load();
	},
	
	//查询
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=6301;
		queryGrid='gridInstockM6301';	
	},
	
	gridInstockM6301before:function(){
		if(g_strIsCanEdit6301)
	    {
	        return false;  
	    }
	},
	
	gridInstockM6301change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.instockNo
			};
			Ext.apply(Ext.getCmp('gridInstockD6301').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('gridInstockD6301').getStore().removeAll();
			Ext.getCmp('gridInstockD6301').getStore().load();
			
			Ext.get('rgstName6301').dom.innerHTML=record.data.rgstName;
			Ext.get('rgstDate6301').dom.innerHTML=record.data.rgstDate;
			Ext.get('updtName6301').dom.innerHTML=record.data.updtName;
			Ext.get('updtDate6301').dom.innerHTML=record.data.updtDate;
		}else{
			Ext.getCmp('gridInstockD6301').getStore().removeAll();
		}
	},
	
	gridInstockD6301before:function(){
		if(!g_strIsCanEdit6301)
	    {
	        //e.cancel = true;
	        return  false;  
	    }
	}
});