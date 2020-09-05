/**
 * 模块名称：重置账单UI
 * 模块编码：B401
 * 创建：lich 
 */
Ext.define('cms.view.bset.bill_ResetBillUI',{
	alias:'widget.bill_ResetBillUI',
	title: $i18n.titleB401, //重置账单UI
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.bdef_DefOwnerCombo'
	         ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menuB401',
	    region:'north'
	},{
		xtype:'panel',
		region:'center',
		height: 35,
		/*layout: {
		    type: 'table',
	        columns: 1
		},*/
		layout:'vbox',
	    defaults : {
			margin : '3 3 30 3',
			width : 850
		},
		items:[{
	        xtype:'fieldset',
	        columnWidth: 0.5,
	        title: '重算消费清单',
	        collapsible: true,
	        width : 850,
	        defaults: {
	        	labelAlign:'right',
	        	width : 230,
	        	labelWidth : 90
	        },
	        layout: 'hbox',
	        items :[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoExpB401',
				xtype:'bdef_DefOwnerCombo',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
				beforeLabelTextTpl : required
			},{
	        	xtype:'datefield',
	            fieldLabel: '开始日期',
	            format : 'Y-m-d',
	            id:'dtBeginDateExpB401',
	            beforeLabelTextTpl : required
	        }, {
	        	xtype:'datefield',
	            fieldLabel: '结束日期',
	            format : 'Y-m-d',
	            id:'dtEndDateExpB401',
	            beforeLabelTextTpl : required
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	//margin : '3 3 20 200',
	        	name:'btnExpB401',
	            text : '重算'
	        }]
		},{
	        xtype:'fieldset',
	        columnWidth: 0.5,
	        title: '重算费用明细',
	        collapsible: true,
	        defaultType: 'textfield',
	        width : 850,
	        defaults: {
	        	labelAlign:'right',
	        	width : 230,
	        	labelWidth : 90
	        },
	        layout: 'hbox',
	        items :[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoCostB401',
				xtype:'bdef_DefOwnerCombo',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
				beforeLabelTextTpl : required
			},{
	        	xtype:'datefield',
	            fieldLabel: '开始日期',
	            format : 'Y-m-d',
	            id:'dtBeginDateCostB401',
	            beforeLabelTextTpl : required
	        }, {
	        	xtype:'datefield',
	            fieldLabel: '结束日期',
	            format : 'Y-m-d',
	            id:'dtEndDateCostB401',
	            beforeLabelTextTpl : required
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	//margin : '3 3 20 200',
	        	name:'btnCostB401',
	            text : '重算'
	        }]
		},{
	        xtype:'fieldset',
	        columnWidth: 0.5,
	        title: '重算账单',
	        collapsible: true,
	        width : 850,
	        defaults: {
	        	labelAlign:'right',
	        	width : 230,
	        	labelWidth : 90
	        },
	        layout: 'hbox',//'anchor',
	        items :[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoFinB401',
				xtype:'bdef_DefOwnerCombo',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
				beforeLabelTextTpl : required
			},{
				xtype: 'monthfield', 
				fieldLabel: '月份', 
				id:'monthB401',
				editable: false, 
				labelAlign: 'right',
                format: 'Ym',
                beforeLabelTextTpl : required
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	//margin : '3 3 20 200',
	        	name:'btnFinB401',
	            text : '重算'
	        }]
		}
		]
	},
	{
    	region:'south'
	}]
});