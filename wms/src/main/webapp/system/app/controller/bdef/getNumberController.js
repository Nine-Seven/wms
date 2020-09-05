/**
 * 模块名称：取号
 * 模块编码：1Z01
 * 创建：lich
 */
Ext.define('cms.controller.bdef.getNumberController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bdef.getNumberUI'],
	init:function(){
		this.control({//选择标签用途
			'getNumberUI radiogroup[id=useType1Z01]':{
				change:this.changeRadio
			}
		});
	},
	//初始化
	initializtion:function(){	
		Ext.getCmp('cust1Z01').setVisible(false);		
	},
	//选择标签用途
	changeRadio:function(obj,newValue,oldValue,eOpts){
		if(newValue.rb=='1'){
			Ext.getCmp('cust1Z01').setVisible(true);
		}else{
			Ext.getCmp('cust1Z01').setVisible(false);
		}
	}
});