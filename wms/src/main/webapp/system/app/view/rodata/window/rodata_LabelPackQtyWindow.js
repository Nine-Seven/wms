/**
 * 单品拆移弹出框
 * hkl
 */
Ext.define('cms.view.rodata.window.rodata_LabelPackQtyWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.rodata_LabelPackQtyWindow',
	layout:'border',
    height:80,
	width:400,
	modal : true,
	id:'rodata_LabelPackQtyWindow',
	title:'test',
	items:[
	{
		xtype:'form',
		id:'rightQtyForm7401',
        frame:true,
        defaults : {
			labelWidth : 90,
			margin : '2 2 2 2',
			labelAlign : 'right',
			width : 400
	    },
	    layout: 
        {
        	type: 'table',
        	columns: 2
        },
        fileUpload: true, 
        items: [
    	{ 
    		fieldLabel:'拆移数量',
			xtype : 'textfield',
			width : 300,
			id:'field7401',
		},{
        	xtype:'button',
        	id:'confirm7401',
        	text:'确定',
        	width : 50,
        	handler: function() {
	            var form = Ext.getCmp('rightQtyForm7401').getForm();
	    		var grid02=Ext.getCmp('grid_02_7401').getSelectionModel().getSelection();
               
                var	str=grid02[0].get('articleQty');//商品数量
                var	strQuery=Ext.getCmp('field7401').getValue();//转移数量
               
	            if(form.isValid()){
	              if(str>=strQuery){
           
        			var detail=[];
    				var articleLabe={
    					enterpriseNo:Ext.get('enterpriseNo').getValue(),
    					warehouseNo:Ext.get('warehouseNo').getValue(),
    					ownerNo:grid02[0].get('ownerNo'),
    					sLabelNo:Ext.getCmp('SlabelNo7401').getValue(),
    					dLabelNo:Ext.getCmp('DlabelNo7401').getValue(),
    					articleNo:grid02[0].get('articleNo'),
    					packingQty:grid02[0].get('packingQty'),
    					produceDate:grid02[0].get('produceDate'),
    					expireDate:grid02[0].get('expireDate'),
    					quality:grid02[0].get('quality'),
    					lotNo:grid02[0].get('lotNo'),
    					articleQty:Ext.getCmp('field7401').getValue(),
    					rgstName:Ext.get('workerNo').getValue(),
    					rgstDate:new Date()
    				};
    				detail.push(articleLabe);
    			
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
        		
            		Ext.getCmp('rodata_LabelPackQtyWindow').close();
            	}else{
            		Ext.example.msg('提示','不能超过计划量，请重新输入！');
            		//Ext.getCmp('rodata_LabelPackQtyWindow').close();
        
	            }
	            }
	        }
        }]
	}
	]
});
