/**
 * 模块名称：退货拼箱打包
 * 模块编码：7401
 * 创建：HKL 
 */
var saveType7401='add';
Ext.define('cms.controller.rodata.rodata_LabelPackController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_LabelPackUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control(
			{//设置光标跳到下一输入框
			'rodata_LabelPackUI field':{
					specialkey:this.boxkeydown
			},//选择退货单号
			'rodata_LabelPackUI ridata_UntreadNoCombo[id=recedeNo7401]':{
				beforequery:this.rodataRecedeNo7401Beforeselect,
				select:this.selectionchange7401
			},
			//扫描或者输入源标签号
			'rodata_LabelPackUI textfield[id=SlabelNo7401]':{
				blur:this.txtSlabelNo7401Blur
			},//扫描或者输入目的标签号
			'rodata_LabelPackUI textfield[id=DlabelNo7401]':{
				blur:this.txtDlabelNo7401Blur
			},
			//选择取号
			'rodata_LabelPackUI button[id=btnDlabelNo7401]':{
				click:this.btnDlabelNo7401
			
			},//选择标签列表
			'rodata_LabelPackUI grid[id=grid_01_7401]':{
				selectionchange:this.gridRodata_LabelPack7401Selectionchange
			
			},//单品转移
			'rodata_LabelPackUI button[id=right7401]':{
				click:this.right7401
			},//整箱转移
			'rodata_LabelPackUI button[id=rightList7401]':{
				click:this.rightList7401
			},//单品拆移
			'rodata_LabelPackUI button[id=rightQty7401]':{
				click:this.rightQty7401
			},//刷新
			'rodata_LabelPackUI button[name=refresh]':{
				click:this.refresh
			},//回库
			'rodata_LabelPackUI button[id=btnMoveQty7401]':{
				click:this.btnMoveQty7401
			}
		});
	},
	
	initializtion:function(){
		//显示变量0为不显示，1为显示
		var packingUnit7401_1=commonGetModuleField('7401','packingUnit')[0].flag;
		var packingSpec7401_1=commonGetModuleField('7401','packingSpec')[0].flag;
		var packingUnit7401_2=commonGetModuleField('7401','packingUnit')[0].flag;
		var packingSpec7401_2=commonGetModuleField('7401','packingSpec')[0].flag;
		if(packingUnit7401_1==0){
			Ext.getCmp('packingUnit7401_1').setVisible(false);
		}
		if(packingSpec7401_1==0){
			Ext.getCmp('packingSpec7401_1').setVisible(false);
		}
		if(packingUnit7401_2==0){
			Ext.getCmp('packingUnit7401_2').setVisible(false);
		}
		if(packingSpec7401_2==0){
			Ext.getCmp('packingSpec7401_2').setVisible(false);
		}
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=='workerNo7401'){
				Ext.getCmp('recedeNo7401').focus();
	  		}else{
		  		th.nextSibling().focus();	  		
	  		}
        }
	},
	 //刷新
	  refresh:function(){
		Ext.getCmp('recedeNo7401').setValue('');
		Ext.getCmp('SlabelNo7401').setValue('');
		Ext.getCmp('DlabelNo7401').setValue('');
		Ext.getCmp('grid_01_7401').getStore().removeAll();
		Ext.getCmp('grid_01_7401').getStore().load();
		Ext.getCmp('grid_02_7401').getStore().removeAll();
		Ext.getCmp('grid_02_7401').getStore().load();
		Ext.getCmp('grid_03_7401').getStore().removeAll();
		Ext.getCmp('grid_03_7401').getStore().load();
	  },
	  
	//选择事件
	rodataRecedeNo7401Beforeselect:function(){
		var strWheresql  =  {
				strRecedeNo:Ext.getCmp('recedeNo7401').getValue()
			};
			Ext.apply(Ext.getCmp('recedeNo7401').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('recedeNo7401').getStore().removeAll();
			Ext.getCmp('recedeNo7401').getStore().load();
	},

	txtSlabelNo7401Blur:function(){
		if(Ext.getCmp('workerNo7401').getValue()=='' || Ext.getCmp('workerNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			Ext.getCmp('SlabelNo7401').setValue('');
			return;
		}
		if(Ext.getCmp('recedeNo7401').getValue()=='' || Ext.getCmp('recedeNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择单据！");
			Ext.getCmp('SlabelNo7401').setValue('');
			return;
		}
		
		if(Ext.getCmp('SlabelNo7401').getValue()!='' && Ext.getCmp('SlabelNo7401').getValue()!=null){
			
				var strWheresql = {
						strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
						strLabelNo:Ext.getCmp('SlabelNo7401').getValue()
				};
		
				Ext.apply(Ext.getCmp('grid_02_7401').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('grid_02_7401').getStore().removeAll();
				Ext.getCmp('grid_02_7401').getStore().load();
				
			
		}
	    
	},
	
	txtDlabelNo7401Blur:function(){
	
		if(Ext.getCmp('workerNo7401').getValue()=='' || Ext.getCmp('workerNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			Ext.getCmp('DlabelNo7401').setValue('');
			return;
		}
		if(Ext.getCmp('recedeNo7401').getValue()=='' || Ext.getCmp('recedeNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择单据！");
			Ext.getCmp('DlabelNo7401').setValue('');
			return;
		}
		if(Ext.getCmp('SlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描源标签！");
			Ext.getCmp('DlabelNo7401').setValue('');
			return;
		}
		
		
		if(Ext.getCmp('DlabelNo7401').getValue()!='' && Ext.getCmp('DlabelNo7401').getValue()!=null){
			
				var strWheresql = {
						strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
						strLabelNo:Ext.getCmp('DlabelNo7401').getValue()
				};
		
				Ext.apply(Ext.getCmp('grid_03_7401').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('grid_03_7401').getStore().removeAll();
				Ext.getCmp('grid_03_7401').getStore().load();
				
			
		}
	    
	},
	gridRodata_LabelPack7401Selectionchange:function(){
		if(Ext.getCmp('workerNo7401').getValue()=='' || Ext.getCmp('workerNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			Ext.getCmp('DlabelNo7401').setValue('');
			return;
		}
		if(Ext.getCmp('recedeNo7401').getValue()=='' || Ext.getCmp('recedeNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择单据！");
			Ext.getCmp('DlabelNo7401').setValue('');
			return;
		}
		if(Ext.getCmp('SlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描源标签！");
			Ext.getCmp('DlabelNo7401').setValue('');
			return;
		}
		var data = Ext.getCmp('grid_01_7401').getSelectionModel().getSelection();
		var slabel = Ext.getCmp('SlabelNo7401').getValue();
		if(data.length!=0 && data[0].get('labelNo')!=slabel)
		{
			Ext.getCmp('DlabelNo7401').setValue(data[0].get('labelNo'));
			var strWheresql = {
					strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
					strLabelNo:data[0].get('labelNo')
			};
	
			Ext.apply(Ext.getCmp('grid_03_7401').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_03_7401').getStore().removeAll();
			Ext.getCmp('grid_03_7401').getStore().load();
			
		}else{
			//Ext.example.msg($i18n.prompt,"选择的标签号有误，请重新选择");
		}
	},
	
	btnDlabelNo7401:function(){
		if(Ext.getCmp('DlabelNo7401').getValue()=='' ||Ext.getCmp('DlabelNo7401').getValue()==null){
			var params1 = {
				strQuery:Ext.getCmp('workerNo7401').getValue()	
    		};
			
			Ext.Ajax.request({
    			method:'POST',
    			url:'rodata_LabelPackAction_getLoadBox.action',
    			params:params1,
    			success:function(response){
    				var res = Ext.decode(response.responseText);
    				if(res.isSucc){
    					Ext.getCmp('DlabelNo7401').setValue(res.msg);
    				}else{
    					var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
    					Ext.example.msg($i18n.prompt,res.msg);
    				}
    			}
    		});	
			
		}else{
			var audio = document.createElement("audio");
			audio.src="system/music/a.mp3";
			audio.play();
			Ext.example.msg("请先清空目的标签的号码，再选择新取号！");
		}
			
	},
	
	selectionchange7401:function(){
			var str = {
			     strRecedeNo: Ext.getCmp('recedeNo7401').getValue() 
			};
			Ext.apply(Ext.getCmp('grid_01_7401').getStore().proxy.extraParams,str);
			Ext.getCmp('grid_01_7401').getStore().removeAll();
			Ext.getCmp('grid_01_7401').getStore().load();
	
			
	},
	
	right7401:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		
		if(Ext.getCmp('workerNo7401').getValue()=='' || Ext.getCmp('workerNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			return;
		}
		if(Ext.getCmp('recedeNo7401').getValue()=='' || Ext.getCmp('recedeNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择单据！");
			return;
		}
		if(Ext.getCmp('SlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描源标签！");
			return;
		}
		if(Ext.getCmp('DlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描目的标签！");
			return;
		}
		var grid02=Ext.getCmp('grid_02_7401').getSelectionModel().getSelection();
		if(grid02.length!=0){
			var detail=[];
			for(var i=0;i<grid02.length;i++){
				var articleLabe={
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:grid02[i].get('ownerNo'),
					sLabelNo:Ext.getCmp('SlabelNo7401').getValue(),
					dLabelNo:Ext.getCmp('DlabelNo7401').getValue(),
					articleNo:grid02[i].get('articleNo'),
					packingQty:grid02[i].get('packingQty'),
					produceDate:grid02[i].get('produceDate'),
					expireDate:grid02[i].get('expireDate'),
					quality:grid02[i].get('quality'),
					lotNo:grid02[i].get('lotNo'),
					articleQty:grid02[i].get('articleQty')*grid02[i].get('packingQty'),
					rgstName:Ext.get('workerNo').getValue(),
					rgstDate:new Date()
				};
				detail.push(articleLabe);
				/*if(Ext.getCmp('grid_03_7401').getStore().findExact('articleNo',grid02[i].get('articleNo'))=='-1'){
					detail.push(articleLabe);
				}*/
			}
			var str=Ext.encode(detail);
			Ext.Ajax.request({
				url:'rodata_LabelPackAction_saveArticleMoveLabel',
				params:{
					str:str
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						var str = {
							     strRecedeNo: Ext.getCmp('recedeNo7401').getValue() 
							};
						Ext.apply(Ext.getCmp('grid_01_7401').getStore().proxy.extraParams,str);
						Ext.getCmp('grid_01_7401').getStore().removeAll();
						Ext.getCmp('grid_01_7401').getStore().load();
							
						var strWheresql1 = {
								strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
								strLabelNo:Ext.getCmp('SlabelNo7401').getValue()
						};
						Ext.apply(Ext.getCmp('grid_02_7401').getStore().proxy.extraParams,strWheresql1);
						Ext.getCmp('grid_02_7401').getStore().removeAll();
						Ext.getCmp('grid_02_7401').getStore().load();
						
						var strWheresql2 = {
								strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
								strLabelNo:Ext.getCmp('DlabelNo7401').getValue()
						};
						Ext.apply(Ext.getCmp('grid_03_7401').getStore().proxy.extraParams,strWheresql2);
						Ext.getCmp('grid_03_7401').getStore().removeAll();
						Ext.getCmp('grid_03_7401').getStore().load();
					}else{
						Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				
				}
			});
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}
	
	
	},
	//单品拆移
	rightQty7401:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		
		if(Ext.getCmp('workerNo7401').getValue()=='' || Ext.getCmp('workerNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			return;
		}
		if(Ext.getCmp('recedeNo7401').getValue()=='' || Ext.getCmp('recedeNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择单据！");
			return;
		}
		if(Ext.getCmp('SlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描源标签！");
			return;
		}
		if(Ext.getCmp('DlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描目的标签！");
			return;
		}
		var grid02=Ext.getCmp('grid_02_7401').getSelectionModel().getSelection();
		if(grid02.length==0){
			Ext.example.msg($i18n.prompt,"请选择要移商品！");
			return;
		}
		Ext.create('cms.view.rodata.window.rodata_LabelPackQtyWindow',
		{
			title:'输入转移数量'
		}).show();
		
	},
	btnMoveQty7401:function(){
		Ext.Ajax.request({
			url:'rodata_LabelPackAction_saveMoveQty',
			params:{str:Ext.getCmp('DlabelNo7401').getValue()},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					var str = {
						     strRecedeNo: Ext.getCmp('recedeNo7401').getValue() 
						};
					Ext.apply(Ext.getCmp('grid_01_7401').getStore().proxy.extraParams,str);
					Ext.getCmp('grid_01_7401').getStore().removeAll();
					Ext.getCmp('grid_01_7401').getStore().load();
				
					var strWheresql1 = {
							strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
							strLabelNo:Ext.getCmp('SlabelNo7401').getValue()
					};
					Ext.apply(Ext.getCmp('grid_02_7401').getStore().proxy.extraParams,strWheresql1);
					Ext.getCmp('grid_02_7401').getStore().removeAll();
					Ext.getCmp('grid_02_7401').getStore().load();
					
					var strWheresql2 = {
							strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
							strLabelNo:Ext.getCmp('DlabelNo7401').getValue()
					};
					Ext.apply(Ext.getCmp('grid_03_7401').getStore().proxy.extraParams,strWheresql2);
					Ext.getCmp('grid_03_7401').getStore().removeAll();
					Ext.getCmp('grid_03_7401').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg+data.obj);
				}
			
			}
		});
	},
	
	rightList7401:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		
		if(Ext.getCmp('workerNo7401').getValue()=='' || Ext.getCmp('workerNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			return;
		}
		if(Ext.getCmp('recedeNo7401').getValue()=='' || Ext.getCmp('recedeNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择单据！");
			return;
		}
		if(Ext.getCmp('SlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描源标签！");
			return;
		}
		if(Ext.getCmp('DlabelNo7401').getValue()=='' || Ext.getCmp('SlabelNo7401').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描目的标签！");
			return;
		}
		
		var label={
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			warehouseNo:Ext.get('warehouseNo').getValue(),
			ownerNo:'001',
			sLabelNo:Ext.getCmp('SlabelNo7401').getValue(),
			dLabelNo:Ext.getCmp('DlabelNo7401').getValue(),
			rgstName:Ext.get('workerNo').getValue()
		};
		var str={
			str:label
		};
		Ext.Ajax.request({
			url:'rodata_LabelPackAction_saveMoveLabel',
			params:str,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					var str = {
						     strRecedeNo: Ext.getCmp('recedeNo7401').getValue() 
						};
					Ext.apply(Ext.getCmp('grid_01_7401').getStore().proxy.extraParams,str);
					Ext.getCmp('grid_01_7401').getStore().removeAll();
					Ext.getCmp('grid_01_7401').getStore().load();
				
					var strWheresql1 = {
							strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
							strLabelNo:Ext.getCmp('SlabelNo7401').getValue()
					};
					Ext.apply(Ext.getCmp('grid_02_7401').getStore().proxy.extraParams,strWheresql1);
					Ext.getCmp('grid_02_7401').getStore().removeAll();
					Ext.getCmp('grid_02_7401').getStore().load();
					
					var strWheresql2 = {
							strRecedeNo:Ext.getCmp('recedeNo7401').getValue(),
							strLabelNo:Ext.getCmp('DlabelNo7401').getValue()
					};
					Ext.apply(Ext.getCmp('grid_03_7401').getStore().proxy.extraParams,strWheresql2);
					Ext.getCmp('grid_03_7401').getStore().removeAll();
					Ext.getCmp('grid_03_7401').getStore().load();
				}else{
					Ext.example.msg($i18n.prompt,data.msg+data.obj);
				}
			
			}
		});
	},
	
	
});




