/**
 * 模块名称：计费公式管理 window
 * 模块编码：B103
 * 创建：hcx
 */
var cost_OtherSetStore=Ext.create('cms.store.cost.cost_OtherSetStore',{autoLoad:false});

Ext.define('cms.view.cost.window.cost_OtherListAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.cost_OtherListAddOrEditWindow',
 	layout:'border',
    width : 800,
	height : 420,
 	modal:true,
 	id:'cost_OtherListAddOrEditWindow',
 	items:[{

    	region:'west',
	    width : '50%',
    	layout:'border',
    	items:[
			{
			 	   xtype : 'toolbar',
					region:'north',
					items : [{
						    text : $i18n.titleadd,//新增
						    iconCls : 'add',
						    id:'addB203'
					    },
					    {
							text : $i18n.titleupdate,//修改
							iconCls : 'edit',
							id:'editB203'
						},
						{
							text : $i18n.delete_1,//删除
							iconCls : 'delete',
							id:'deleteB203'
						},
						{
						    text : $i18n.find,//查找
						    iconCls : 'query',
						    id:'queryB203'
					    }]
			},
			{
				xtype : 'grid',
				region:'center',
				store:cost_OtherSetStore,
				id : 'costOtherSetB203',
				columns : [ {
					xtype : 'rownumberer',
					width : 20
				},{
			    	width: 100,
			  		text : $i18n.cost_no,  //费用代码
			  		dataIndex:'costNo'		
				},{
			    	width:160,
			  		text : $i18n.cost_name,  //费用名称
			  		dataIndex:'costName'		
				},{
			    	width: 150,
			  		text : $i18n.remark,  //备注
			  		dataIndex:'remark'		
				}],
				dockedItems : [{
						xtype : 'pagingtoolbar',
						dock : 'bottom',
						store:cost_OtherSetStore,
						displayInfo : true
				    }]
			}]
 	},{

		xtype : 'form',
		id:'costOtherSetFormB203',
		region : 'east',
		width : '50%',
		frame : true,
		layout: 
        {
        	type: 'table',
        	columns: 1
        },
		defaults : 
        {
			xtype:'textfield',
       	   	margin:'15 4 1 4',
       	   	labelAlign:'right',
       	   	labelWidth:120			
 	    },	
 	    items:[
        {

			fieldLabel : $i18n.owner_no,
			id:'ownerNoB203',
			xtype:'wms_DefFieldValCombo',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
			{
				listeners:{  
					'load':function(th,records,successful,eOpts ){
					if(th.count()>0){
						Ext.getCmp('ownerNoB203').setValue(th.getAt(0).data.value);
						}
					}
				}
			}).load(),
		    displayField : 'dropValue',
		    valueField : 'value',
            beforeLabelTextTpl:required
        }
//        ,{
//			xtype : 'textfield',
//			fieldLabel : $i18n.cost_no,// 费用编码
//			id : 'costNoB203',
//             beforeLabelTextTpl:required
//	    }
        ,{

			fieldLabel :$i18n.cost_no, //费用代码
			id:'costNoB203',
			xtype:'wms_DefFieldValCombo',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 proxy:{
					type:'ajax',
					method:'post',
					url:'cost_OtherAction_getCostNoForWindown',
					reader:{
					root:'rootList',
						totalProperty:'totalCount'
					}
				}
		    }).load(),
		    displayField : 'dropValue',
		    valueField : 'value',
		    beforeLabelTextTpl:required
		
        },{

			xtype : 'datefield',
			fieldLabel : $i18n.cost_date,//费用日期
			id : 'costDateB203',								
			format : 'Y-m-d',
			beforeLabelTextTpl : required	
		},{
	    	xtype:'numberfield',   
		    fieldLabel:$i18n.cost_value,//费用
		    minValue:0,
		    id:'costValueB203',
			beforeLabelTextTpl : required	
		},{
 			fieldLabel:$i18n.source_plan_no, //来源单号
 			id:'sourceNoB203',
 			xtype:'textfield',
 			maxLength:20
 		},{
			xtype:'wms_DefFieldValCombo',
			   fieldLabel:$i18n.amountFlag,  //费用标识
			   id:'costFlagB203',
			   store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"COST_OTHER_LIST,COST_FLAG"}
			   	}),
			   	allowBlank : false,
			   	readOnly:true,
				beforeLabelTextTpl : required	
		},{
   			   xtype:'wms_DefFieldValCombo',
   			   fieldLabel:$i18n.manage_status,  //状态
   			   id:'statusB203',
   			   store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"COST_OTHER_LIST,STATUS"}
			   	}),
			   	allowBlank : false,
				beforeLabelTextTpl : required
   		   },{
			xtype:'textareafield',   
			fieldLabel:$i18n.remark,   //备注
			id:'remarkB203',
	        allowBlank : true
		}]
	
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'cost_OtherListB203'
 	}]
});