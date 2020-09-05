/**
 * 模块名称：返配分播回单
 * 模块编码：6801
 * 创建：HCX
 */
Ext.define('cms.controller.ridata.ridata_DivideReceiptController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_DivideReceiptUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//分播
			'ridata_DivideReceiptUI button[id=butDivide6801]':{
				click:this.butDivide6801Click
			},//封箱
			'ridata_DivideReceiptUI button[id=butCloseBox6801]':{
				click:this.butCloseBox6801Click
			}
		});
	},
	butDivide6801Click:function(){
		var labelNo=Ext.getCmp('labelNo6801').getValue();
	    var params={
	    		strLabelNo:labelNo
		};
	    Ext.Ajax.request({
			method:'post',
			url:'ridata_DivideReceiptAction_divide',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('labelNo6801').setValue('');
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	},
	butCloseBox6801Click:function(){
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		var cellNo=Ext.getCmp('cellNo6801').getValue();
		var params={
	    		strCellNo:cellNo
		};
	    Ext.Ajax.request({
			method:'post',
			url:'ridata_DivideReceiptAction_closeBox',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('cellNo6801').setValue('');
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
		
	}
});