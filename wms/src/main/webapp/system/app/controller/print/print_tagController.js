Ext.define('cms.controller.print.print_tagController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.print.print_tagUI'
	          ],
	init:function(){
		this.control({
			//商品下拉选择
			'bdef_DefArticleCombo[id=article_no11102]':{
				select:this.article_noselect
			},
			//打印
			'print_tagUI button[name=printTagPrintBtn]':{
				click:this.printFunc
			}
		});
	},
	
	article_noselect:function(){	
		var strDetail1 = [];
		var d1={
			columnId:'a.article_no',
			value:Ext.getCmp('article_no11102').getValue()
		};
		strDetail1.push(d1);
		var jsonDetail = Ext.encode(strDetail1);
		var strQuery = {
				strQuery  : jsonDetail
		};
		Ext.apply(Ext.getCmp('grid11102').getStore().proxy.extraParams,strQuery);
		Ext.getCmp('grid11102').getStore().removeAll();
		Ext.getCmp('grid11102').getStore().load();		    
	},
	
	
	printFunc:function(){
		if(!commonCheckIsInputAll('repairForm11102')){
			return;
		}
		
		var gridcount=Ext.getCmp("grid11102").getStore().getCount();
		if(gridcount==0){
			Ext.example.msg('提示',"请选择要打印的商品");
			return;
		}
		
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		var barcodeText="";
		var record=Ext.getCmp('grid11102').getStore().getAt(0);
		if(Ext.getCmp("radiogroup11102").getValue().rb==1){
			barcodeText=record.get('barcode');	
		}else if(Ext.getCmp("radiogroup11102").getValue().rb==2){
			barcodeText=record.get('ownerArticleNo');
		}else{
			barcodeText=record.get('articleIdentifier');
		}
		
		
			
		Ext.Ajax.request({
			url:'printerTagAction_sendTask',
			method:'post',
			params:{
				printNumber:Ext.getCmp('printNumber11102').getValue(),
				barcode:barcodeText,
				articleNo:record.get('articleNo')
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc)
				{
					Ext.example.msg($i18n.prompt,data.msg);
				}
				else
				{
					Ext.example.msg($i18n.prompt,data.msg);
				}
				
			}
		});	
	
	}
	
});