/**
 * 模块名称：货主参数配置 window
 * 模块编码：I803
 * 创建：MM
 */
/*Ext.regModel('PostInfo',{   
fields:[{name:'id'},{name:'name'}]   
});   */

var states = Ext.create('Ext.data.Store', {
	//model:'PostInfo',   
    fields: ['id', 'name'],
    data : [ 
        {"id":"0", "name":"[0]不允许"} ,
        {"id":"1", "name":"[1]允许"} 
    ]
});
Ext.define('cms.view.odata.window.odata_wmsOutPickStrategyAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.odata_wmsOutPickStrategyAddOrEditWindow',
	layout:'border',
	id:'odata_wmsOutPickStrategyAddOrEditWindow',
	width:600,
	height:260,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdFormI803',
	     		layout:'border',
	     		height:250,
	     		frame : true,
	     	    	 layout:{
	     		   			type:'table',
	     		   			columns:2
	     	   		},
	     	   		defaults : {
	     	   			xtype : 'textfield',
	     	   			labelWidth : 120,
	     	   			labelAlign:'right'			
	     	   		},
	     	   		items:[ {	     	   				
			     	   		xtype:'textfield',
		 					fieldLabel:$i18n.pick_strategy_id,   //拣货策略ID
		 					id:'pickStrategyIDI803',
		 					beforeLabelTextTpl : required
	     				},
	     				{
	     					xtype:'textfield',
	     					fieldLabel:$i18n.pick_strategy_name,   //拣货策略名称
	     					id:'pickStrategyNameI803',
	     					beforeLabelTextTpl : required
	     				},
	     				{ 
	     					xtype:'combo',
	     				    fieldLabel:$i18n.pick_diff_flag, //是否允许拣货差异
	     				    id:'pickDiffFlagI803', 
	     				    store : states,
	     				    queryMode: 'local',
	     				    displayField : 'name',
	     				    valueField : 'id', 
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required 
	     				},
	     				{
	     					xtype:'combo',
	     				    fieldLabel:$i18n.pick_auto_flag, //是否允许拣货自动回单
	     				    id:'pickAutoFlagI803', 
	     				    store : states,
	     				    queryMode: 'local',
	     				    displayField : 'name',
	     				    valueField : 'id',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required
	     				},
	     				{
	     					xtype:'combo',
	     				    fieldLabel:$i18n.auto_getdivide_flag, //是否允许自动分播发单
	     				    id:'autoGetdivideFlagI803', 
	     				    store : states,
	     				    queryMode: 'local',
	     				    displayField : 'name',
	     				    valueField : 'id',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required
	     				},
	     				{
	     					xtype:'combo',
	     				    fieldLabel:$i18n.auto_dividesave_flag, //是否允许自动分播回单
	     				    id:'autoDividesaveFlagI803', 
	     				    store : states,
	     				    queryMode: 'local',
	     				    displayField : 'name',
	     				    valueField : 'id',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required
	     			        
	     				},
	     				{
	     					xtype:'textfield',
	     					fieldLabel:$i18n.rsv1,   //
	     					id:'rsvValue1I803' 
	     				},
	     				{
	     					xtype:'textfield',
	     					fieldLabel:$i18n.rsv2,   //
	     					id:'rsvValue2I803' 
	     				},
	     				{
	     					xtype:'textfield',
	     					fieldLabel:$i18n.rsv3,   //
	     					id:'rsvValue3I803' 
	     				},
	     				{
	     					xtype:'textfield',
	     					fieldLabel:$i18n.rsv4,   //
	     					id:'rsvValue4I803' 
	     				},
	     				{
	     					xtype:'textfield',
	     					fieldLabel:$i18n.rsv5,   //
	     					id:'rsvValue5I803' 
	     				} ]
	     	},	
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'odata_wmsOutPickStrategyI803'
	     	}]
	     });