/**
 * 模块名称：客户资料维护
 * 模块编码：1501
 * 创建：Jun
 */
Ext.define('cms.view.odata.window.odata_TacticsAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.odata_TacticsAddOrEditWindow',
	layout:'border',
	id:'odata_TacticsAddOrEditWindow',
	width:880,
	height:520,
	modal:true,
	items:[
	{
		xtype : 'form',
	    id : 'form_01_3910',
	    region:'center',
	    frame : true,
	    layout:{
    		type:'table',
    		columns:1
    	},
    	defaults : {
			xtype:'textfield',
			labelWidth : 130,
			margin : '2 2 4 2',
			labelAlign : 'right'
  		},
	    items :[
	    {
		    xtype:'fieldset',
            layout: 
            {
            	type: 'table',
            	columns: 3
            },
            defaults : 
            {
            	xtype : 'textfield',
            	labelWidth : 110,
            	margin:'5 0 0 0 ',
            	labelAlign:'right'			
       	    },
   	        items:[
   	        {
		    	xtype:'wms_DefFieldValCombo',
	    		fieldLabel:$i18n.exp_type,//出货单别
	    		id:'cmbExpType3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"N,EXP_TYPE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'订单优先级',
		    	id:'cmbPriority3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"ODATA_EXP_M,PRIORITY"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'配送对象',
		    	id:'cmbDeliverObjLevel3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,DELIVER_OBJ_LEVEL"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    }
   	        ]
        },
        {
        	xtype:'fieldset',
            layout: 
            {
            	type: 'table',
            	columns: 3
            },
            defaults : 
            {
            	xtype : 'textfield',
            	labelWidth : 110,
            	margin:'5 0 0 0 ',
            	labelAlign:'right'			
       	    },
   	        items:[
   	        {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否支持M型定位',
		    	id:'cmbMflag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,M_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否支持W型定位',
		    	id:'cmbWflag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,W_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
	    	{
	    		xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否支持S型定位',
		    	id:'cmbSflag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,S_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否支持D型定位',
		    	id:'cmbDflag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,D_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    }
   	        ]
        },
        {
        	xtype:'fieldset',
            layout: 
            {
            	type: 'table',
            	columns: 3
            },
            defaults : 
            {
            	xtype : 'textfield',
            	labelWidth : 110,
            	margin:'5 0 0 0 ',
            	labelAlign:'right'			
       	    },
   	        items:[
   	        {
	    		xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'B是否在可分播区域采',
		    	id:'cmbBdivideFlag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,B_DIVIDE_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'C是否在可分播区域',
		    	id:'cmbCdivideFlag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,C_DIVIDE_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否计算发货暂存区资源',
		    	id:'cmbSendbufCompute3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,SENDBUF_COMPUTE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'出货定位的提交方式',
		    	id:'cmbCommitType3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,COMMIT_TYPE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'定位缺量处理方式',
		    	id:'cmbShortqtyType3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,SHORTQTY_TYPE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'出货时是否对线路做管理',
		    	id:'cmbLineFlag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,LINE_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    },
		    {
		    	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否定位异常区',
		    	id:'cmbAbnormalFlag3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,ABNORMAL_FLAG"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
		    }
   	        ]
        },
        {
        	xtype:'fieldset',
            layout: 
            {
            	type: 'table',
            	columns: 3
            },
            defaults : 
            {
            	xtype : 'textfield',
            	labelWidth : 110,
            	margin:'5 0 0 0 ',
            	labelAlign:'right'			
       	    },
   	        items:[
   	        {
   	        	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否做复核台滑道资料试算',
		    	id:'cmbRecheckCompute3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,RECHECK_COMPUTE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
   	        },
   	        {
   	        	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否对分拣机滑道资源进行试算',
		    	id:'cmbSorterCompute3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,SORTER_COMPUTE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
   	        },
   	        {
   	        	xtype:'wms_DefFieldValCombo',
		    	fieldLabel:'是否做物流批次试算',
		    	id:'cmbBatchCompute3910',
	    		store:Ext.create("cms.store.common.comboStore").load(
	    		{
	       	 		params:{str:"WMS_OUTORDER,BATCH_COMPUTE"}
	    		}),
	    		editable:false,
	    		allowBlank:false,
	    		readOnly:true,
	    		beforeLabelTextTpl:required
   	        }
   	        ]
        },
        {
        	xtype:'fieldset',
            layout: 
            {
            	type: 'table',
            	columns: 3
            },
            defaults : 
            {
            	xtype : 'textfield',
            	labelWidth : 110,
            	margin:'5 0 0 0 ',
            	labelAlign:'right'			
       	    },
   	        items:[
   	        {
		    	fieldLabel:'预留字段1',
		    	id:'txtOrderRsv01_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段2',
		    	id:'txtOrderRsv02_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段3',
		    	id:'txtOrderRsv03_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段4',
		    	id:'txtOrderRsv04_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段5',
		    	id:'txtOrderRsv05_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段6',
		    	id:'txtOrderRsv06_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段7',
		    	id:'txtOrderRsv07_3910'
   	        },
   	        {
		    	fieldLabel:'预留字段8',
		    	id:'txtOrderRsv08_3910'
   	        }
   	        ]
        }
    	]
    },{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'bdef_MenuWidget3910'
	}]
});
