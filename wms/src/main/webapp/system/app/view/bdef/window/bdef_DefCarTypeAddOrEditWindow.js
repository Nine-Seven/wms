/**
 * 模块名称：车辆类型
 * 模块编码：1V01
 * 创建：hcx
 */
Ext.define('cms.view.bdef.window.bdef_DefCarTypeAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.bdef_DefCarTypeAddOrEditWindow',
 	layout:'border',
    width : 600,
	height : 213,
 	modal:true,
 	id:'bdef_DefCarTypeAddOrEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'bdef_DefCarTypeAddOrEditForm',
 	  	frame : true,
    items:[
    	{	xtype:'fieldset',
		    layout: 
		    {
		        type: 'table',
		        columns: 2
		    },
		    defaults : 
		    {
		        xtype : 'textfield',
                labelWidth : 100,
                margin:'2 5 5 5',
	            labelAlign:'right'			
		    },
		    items:[
            {
	 	    	fieldLabel:$i18n.cartype_no,//车辆类型代码
	 	    	id:'cartypeNo1V01',
		        beforeLabelTextTpl : required
	 	    },{
	 	    	fieldLabel:$i18n.type_name,//类型名称
	 	    	id:'cartypeName1V01'
	 	    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.cartype_weight,//车重量
				minValue:0,
				id : 'cartypeWeight1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.cartype_length,//车长度
				minValue:0,
				id : 'cartypeLength1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.cartype_width,//车宽度
				minValue:0,
				id : 'cartypeWidth1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.cartype_height,//车高度
				minValue:0,
				id : 'cartypeHeight1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.max_layer,//最大载重
				minValue:0,
				id : 'maxLayer1V01'
		    }]
		  }]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget1V01_1'
 	}]
});