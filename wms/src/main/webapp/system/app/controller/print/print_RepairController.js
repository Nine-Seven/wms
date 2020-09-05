/**
 *补印中心控制器 
 */

var flag = 1;
Ext.define('cms.controller.print.print_RepairController',{
	extend:'Ext.app.Controller',
	init:function(){
		this.control({
			//查询
			'print_repairUI button[name=printRepairQueryBtn]':{
				click:this.queryFunc
			},
			//报表》打印
			'print_repairUI button[name=printRepairPrintBtn]':{
				click:this.printFunc
			},
			//radiogroupHtyFlag切换
			'print_repairUI [id=radiogroupHtyFlag]':{
				change:this.queryFunc
			},
			
			'print_repairUI button[name=printPreviewBtn]':{
				click:this.printPreviewFunc
			},			
			'print_repairUI combo[id=defReportCombo]':{
				change:this.changeReportFunc
			},
			'print_repairUI [id=repairForm] radiogroup':{
				change:this.changeRadio
			},
			'print_repairUI [id=sourceNoStr]':{
				render:this.renderFunc
			},//标签查询			
			'print_repairUI button[name=printLabelQueryBtn]':{
				click:this.printLabelQueryBtnClick
			},
			//radiogroupLabelHtyFlag切换
			'print_repairUI [id=radiogroupLabelHtyFlag]':{
				change:this.printLabelQueryBtnClick
			},
			'print_repairUI [id=labelForm11101] radiogroup':{
				change:this.labelForm11101ChangeRadio
			},//标签打印 单据选择
			'print_repairUI grid[id=sheetGrid11101]':{
				select:this.sheetGrid11101Select
			},//标签打印 打印
			'print_repairUI button[name=printLabelPrintBtn]':{
				click:this.printLabelPrintBtnClick
			},//标签打印 报表类型选择
			'print_repairUI combo[id=defLabelCombo11101]':{
				change:this.defLabelCombo11101Change
			}
		});
	},
	queryFunc:function(){
		var reportId = Ext.getCmp('reportId').getValue();
		if(Ext.String.trim(reportId)==""){
			Ext.example.msg("提示","请选择一个报表!");
			return ;
		}
		var store = Ext.getCmp('RepairReportGrid').getStore();
		Ext.apply(store.proxy.extraParams,{
			reportId:reportId,
			reportType:'L',
			sourceNo:Ext.getCmp('sourceNoStr').getValue(),
			date:Ext.getCmp('dateStr').getValue(),
			hty:Ext.getCmp('radiogroupHtyFlag').getValue().rb
		});
		Ext.getCmp('RepairReportGrid').getStore().removeAll();
		Ext.getCmp('RepairReportGrid').getStore().reload();
	},
	changeRadio:function(field, newVal, oldVal){
		if(newVal.rb==2){
			this.showDate();
		}else if(newVal.rb==1){
			this.showInput();
		}
	},
	showInput:function(){
		Ext.getCmp('sourceNoStr').show();
		Ext.getCmp('dateStr').hide();
		Ext.getCmp('dateStr').setValue("");
	},
	showDate:function(){
		Ext.getCmp('sourceNoStr').hide();
		Ext.getCmp('dateStr').show();
		Ext.getCmp('sourceNoStr').setValue("");
		Ext.getCmp('dateStr').setValue(new Date());
	},
	renderFunc:function(){
		Ext.getCmp("sourceNoStr").focus();
	},
	printFunc:function(){
		
		var store = Ext.getCmp('RepairReportGrid').getStore().query('checkColumn',true);
		var array = new Array();
		for ( var i = 0; i < store.getCount(); i++) {
			var bo = new ReportInfoBo();
			bo.sourceNo = store.getAt(i).get('sourceNo');
			bo.reportId = Ext.getCmp('reportId').getValue();
			array.push(bo);
		}
		if(array.length > 0){		
			commPrint(array,Ext.getCmp('radiogroupHtyFlag').getValue().rb,'N',1);
		}else{
			Ext.example.msg("提示","请选择要打印的单据");
		}
	},
	printPreviewFunc:function(){		
		if(Ext.getCmp('RepairReportGrid').getStore().query('checkColumn',true).items.length > 0){
			var url="system/app/view/print/print_repair.jsp?sheetid="+Ext.getCmp('RepairReportGrid').getStore().query('checkColumn',true).items[0].internalId+"&reportType="+Ext.getCmp('defReportCombo').getRawValue();
	  		var Cwin=window.open(url,"","left=10,top=10");
	  		Cwin.resizeTo(screen.availWidth-20,screen.availHeight-20); 
		}else{
			Ext.example.msg("提示","请选择要打印的单据");
		}
	},
	changeReportFunc:function(field,newValue,oldValue){
		Ext.getCmp('reportId').setValue(newValue);
		Ext.getCmp('sourceNoStr').setValue('');
		Ext.getCmp('RepairReportGrid').getStore().removeAll();
	},
	
	//类型切换（标签）
	printLabelQueryBtnClick:function(){
		if(flag!=3){
			var reportId = Ext.getCmp('defLabelCombo11101').getValue();
			if(Ext.isEmpty(reportId)){
				Ext.example.msg("提示","请选择一个报表!");
				return ;
			}
			var store = Ext.getCmp('sheetGrid11101').getStore();
			Ext.apply(store.proxy.extraParams,{
				reportId:reportId,
				reportType:'B',
				sourceNo:Ext.getCmp('sourceNoStr11101').getValue(),
				date:Ext.getCmp('dateStr11101').getValue(),
				hty:Ext.getCmp('radiogroupLabelHtyFlag').getValue().rb
			});
			Ext.getCmp('sheetGrid11101').getStore().removeAll();
			Ext.getCmp('sheetGrid11101').getStore().load();
			Ext.getCmp('printNumber11101').setValue(1);
		}	
	},
	
	//radio切换
	labelForm11101ChangeRadio:function(field, newVal, oldVal){
		if(newVal.rb==2){
			flag=2;
			this.labelForm11101ShowDate();
			Ext.getCmp('defLabelCombo11101').getStore().removeAll();
			Ext.getCmp('defLabelCombo11101').getStore().load();
			Ext.getCmp('defLabelCombo11101').disabled=false;
			Ext.getCmp('printLabelQueryBtn').show();
			commonSetMsterReadOnlyByArray(
					new Array('printNumber11101'),false);
		}else if(newVal.rb==1){
			flag=1;
			this.labelForm11101ShowInput();
			Ext.getCmp('defLabelCombo11101').getStore().removeAll();
			Ext.getCmp('defLabelCombo11101').getStore().load();
			Ext.getCmp('defLabelCombo11101').disabled=false;
			Ext.getCmp('printLabelQueryBtn').show();
			commonSetMsterReadOnlyByArray(
					new Array('printNumber11101'),false);
		}else if(newVal.rb==3){
			flag=3;
			this.labelForm11101ShowInput();
			Ext.getCmp('defLabelCombo11101').getStore().removeAll();
			Ext.getCmp('defLabelCombo11101').setValue(null);
			Ext.getCmp('defLabelCombo11101').disabled=true;
			Ext.getCmp('printLabelQueryBtn').hide();
			commonSetMsterReadOnlyByArray(
					new Array('printNumber11101'),true);
	   
		}
	},
	labelForm11101ShowInput:function(){
		Ext.getCmp('sourceNoStr11101').show();
		Ext.getCmp('dateStr11101').hide();
		Ext.getCmp('dateStr11101').setValue("");
	},
	labelForm11101ShowDate:function(){
		Ext.getCmp('sourceNoStr11101').hide();
		Ext.getCmp('dateStr11101').show();
		Ext.getCmp('sourceNoStr11101').setValue("");
		Ext.getCmp('dateStr11101').setValue(new Date());
	},
	sheetGrid11101Select:function(th,record,index,eOpts ){
	
		var strOwnerNo = {
			reportId : Ext.getCmp('defLabelCombo11101').getValue(),
			sourceNo:record.data.sourceNo,
			hty:Ext.getCmp('radiogroupLabelHtyFlag').getValue().rb
		};
		Ext.apply(Ext.getCmp('print_LabelStore11101').getStore().proxy.extraParams,strOwnerNo);
		Ext.getCmp('print_LabelStore11101').getStore().removeAll();
		Ext.getCmp('print_LabelStore11101').getStore().load();
	},
	printLabelPrintBtnClick:function(){
		if(flag!=3){
			var record = Ext.getCmp('sheetGrid11101').getSelectionModel().getSelection();
	        if(record.length == 0){
	    		Ext.example.msg('提示',"请选择补印数据！");
	        }else{
	        	var printNum=Ext.getCmp('printNumber11101').getValue();
	        	var labelNoList="";
	        	var data = Ext.getCmp('print_LabelStore11101').getSelectionModel().getSelection();
	    	
	        	if(data.length<1){
	        		Ext.example.msg('提示',"请选择标签！");
	        		return;
	        	}
	        	
	        	for ( var i = 0; i < data.length; i++) {
	    			if(i==0){
	    				labelNoList=data[i].get('sourceNo');
	    			}else{
	    				labelNoList=labelNoList+"|"+data[i].get('sourceNo');
	    			}
	    		}
	        	
	        	var array = new Array();
	        	var bo = new ReportInfoBo();
				bo.sourceNo = record[0].data.sourceNo;
				bo.reportId = Ext.getCmp('defLabelCombo11101').getValue();
				array.push(bo);
			
				commPrint(array,Ext.getCmp('radiogroupLabelHtyFlag').getValue().rb,labelNoList,printNum);			
	        }    		
		}else if(flag==3){
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
				return;
			}
			if(Ext.getCmp('sourceNoStr11101').getValue()!=null && Ext.getCmp('sourceNoStr11101').getValue()!=''){				
				Ext.Ajax.request({
					method:'post',
					url:'printerAction_printLabel.action',
					params:{
						labelNo:Ext.getCmp('sourceNoStr11101').getValue()
				    },
				    success:function(response){
				    	var data = Ext.decode(response.responseText);
				    	Ext.example.msg("提示",data.msg);
				    }
				});
			}else{
				Ext.example.msg($i18n.prompt,'请输入标签号！');
			}
        }    
		
	},
	defLabelCombo11101Change:function(field,newValue,oldValue){
		Ext.getCmp('sourceNoStr11101').setValue('');
		Ext.getCmp('sheetGrid11101').getStore().removeAll();
		Ext.getCmp('print_LabelStore11101').getStore().removeAll();
	}
});