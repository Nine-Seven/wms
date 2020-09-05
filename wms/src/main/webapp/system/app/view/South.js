Ext.define('cms.view.South',{ 
    extend: 'Ext.Toolbar', 
    initComponent : function(){ 
        Ext.apply(this,{ 
            id:"bottom", 
            //frame:true, 
            region:"south", 
            height:23, 
            html:'<div class="foot">当前用户：'+Ext.get('workerName').getValue()+'&nbsp;&nbsp;&nbsp;当前作业仓别：'+'<span style="padding-right:10px;" id="currentWarehouseNo">'+Ext.get('warehouseNo').getValue()+'</span></div>'
	        }); 
	        this.callParent(arguments); 
	    } 
	}); 
