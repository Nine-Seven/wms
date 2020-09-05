/**
 * 模块名称：报表控制器
 * 创建：周欢
 */
var count=0;
Ext.define('cms.controller.report.auto_Bdef_Controller',{
	extend:'Ext.app.Controller',
	requires:[

			 'cms.view.report.auto_Bdef_UI'
			 ],
	init:function(){
		this.control({
			//刷新
			'auto_Bdef_UI button[name=refresh]':{
				click:this.refresh
			},			
			//报表选择
			'auto_Bdef_UI combo[id=cmbReportSelectG201]':{
				change:this.cmbReportSelectG201Select
			},//回车键
			'auto_Bdef_UI field':{
				specialkey:this.boxkeydown
			},//执行
			'auto_Bdef_UI button[id=btnG201]':{
				click:this.buttonClick
			}
		});
	},

	//回车键
	boxkeydown:function(th,e,eOpts){						
		if (e.getKey() == e.ENTER) {
			var num=0;
			var list =new Array();
			for(var i=0; i<count;i++){
				
				if(Ext.getCmp('check'+i).getValue()==false){
					var id='value'+i;
					list[num++]=id;
				}			
			}						
			for (var i=0; i<num; i++){
				if(th.id==list[i]){
					if(th.id==list[num-1]){
						Ext.getCmp(list[0]).focus();
					}else{
						Ext.getCmp(list[i+1]).focus();
					}					
					return;
				}
			}
		}
	},
	//刷新
	refresh:function(){
		Ext.getCmp('form_01_G201').removeAll();	
		Ext.getCmp('cmbReportSelectG201').getStore().reload();
	},
	
	//报表选择事件
	cmbReportSelectG201Select:function(combo){
		 Ext.Ajax.request({
			url:'auto_Action_getCombo.action',
			params : {
				customId:combo.value
			},
			success : function(response, options) { 				
				var dataShow = Ext.JSON.decode(response.responseText);//将字符串转换为json类型
				Ext.getCmp('form_01_G201').removeAll();
			    
				count=dataShow.length;
				
				for(var i=0; i<dataShow.length;i++){
					var text={
								fieldLabel: dataShow[i].paramName,
								id : 'value'+i
					}					
					Ext.getCmp('form_01_G201').add(text);  
					
					var checkBox={
						xtype: 'checkboxfield',
	    	        	fieldLabel: '锁定',//按单号调度
		        		id:'check'+i,
		           		Value:false		
					}
					Ext.getCmp('form_01_G201').add(checkBox);  
				}	
				if(dataShow.length>0){
					var button= {
							xtype: 'button',
		            		text: '执行',
		            		width:140,
		            		margin : '3 3 3 110',
		            		id:'btnG201'
					}
					Ext.getCmp('form_01_G201').add(button);  
				}				
				Ext.getCmp('form_01_G201').doLayout();				
			}
		});
	},	
	
	//执行
	buttonClick:function(){	
		Ext.Ajax.request({
			url:'auto_Action_implement.action',
			
			params : {
				value0:Ext.getCmp('value0')== undefined?'':Ext.getCmp('value0').getValue(),
				value1:Ext.getCmp('value1')== undefined?'':Ext.getCmp('value1').getValue(),
				value2:Ext.getCmp('value2')== undefined?'':Ext.getCmp('value2').getValue(),
				value3:Ext.getCmp('value3')== undefined?'':Ext.getCmp('value3').getValue(),
				value4:Ext.getCmp('value4')== undefined?'':Ext.getCmp('value4').getValue(),
				value5:Ext.getCmp('value5')== undefined?'':Ext.getCmp('value5').getValue(),
				value6:Ext.getCmp('value6')== undefined?'':Ext.getCmp('value6').getValue(),
				value7:Ext.getCmp('value7')== undefined?'':Ext.getCmp('value7').getValue(),
				value8:Ext.getCmp('value8')== undefined?'':Ext.getCmp('value8').getValue(),
				value9:Ext.getCmp('value9')== undefined?'':Ext.getCmp('value9').getValue(),
				customId:Ext.getCmp('cmbReportSelectG201').getValue()
			},
			success : function(response, options) {
				var data = Ext.decode(response.responseText);
				Ext.example.msg('提示',data.msg);
			}
		});
	}
});



