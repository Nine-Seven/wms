/**
 * 货主资料维护
 * 模块编码   1D01
 * @author lich
 */
Ext.define('cms.view.bdef.window.bdef_DefOwnerAddOrEditWindow', 
{
	extend : 'Ext.window.Window',
	alias : 'widget.bdef_DefOwnerAddOrEditWindow',
	layout : 'border',
	width : 900,
	height : 485,
	modal : true,
	id : 'bdef_DefOwnerAddOrEditWindow',
	items : [
	{
		xtype : 'form',
		id : 'formHeard1D01',
		region : 'north',
		height:90,
		frame : true,
		layout: 
        {
        	type: 'table',
        	columns: 3
        },
		defaults : 
        {
        	xtype : 'textfield',
        	labelWidth : 110,
        	labelAlign:'right'			
 	    },
		items:[
		{
			fieldLabel:$i18n.owner_no,//货主编号
			id:'txtOwnerNo1D01',
			maxLength:10,
	        beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.owner_name,//货主名称
			id : 'txtOwnerName1D01',
			width:530,
			colspan:2,
			maxLength:60,
	        beforeLabelTextTpl : required
		},
		{
			fieldLabel:$i18n.owner_alias,//货主简称
			id : 'txtOwnerAlias1D01',
			maxLength:20
		},
		{
			xtype:'wms_DefFieldValCombo',
			fieldLabel:$i18n.authority_type,//数据权限类型
			id : 'cmbAuthorityType1D01',
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"BDEF_DEFOWNER,AUTHORITY_TYPE"}
	        }),
	        beforeLabelTextTpl : required
		},
		{
			xtype:'wms_DefFieldValCombo',
			fieldLabel:$i18n.status,//状态
	        id:'cmbStatus1D01',
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"N,DEF_STATUS"}
	        }),
	        beforeLabelTextTpl : required
		},{
			xtype:'wms_DefFieldValCombo',
			fieldLabel:'储位管理类型',
	        id:'cellManagerType1D01',
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"BDEF_DEFOWNER,CELL_MANAGERT_TYPE"}
	        }),
	        beforeLabelTextTpl : required
		},{
			
			xtype: 'cdef_DefCellCombo',
			id:'cellNo1D01',
			fieldLabel:'默认储位',
            displayField : 'value',
    		valueField : 'value',
    		store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
			    proxy:{
					type:'ajax',
					method:'post',
					url:'odata_LocateAction_getCdef_DefCellCombo',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			}).load(),
    	    minChars : 0// 多少字符请求1次  	
		}]
	},{
		    xtype:'fieldset',
   		    colspan:3,
   			region : 'north',
   			height:60,
   			frame : true,
   		    title:'仓别储位管理',
   	        layout: 
   	        {
  	       	    type: 'table',
  	       	    columns: 3
	       	    },
	       	    defaults : 
	       	    {
	       	        xtype : 'numberfield',
	       	        labelWidth : 120,
	       	        //margin:'3 4 1 4',
	       	        labelAlign:'right'			
	       	    },
	       	    items:[
	    	     {
					xtype:'wms_DefFieldValCombo',
					fieldLabel:'储位管理类型',
			        id:'WcellManagerType1D01',
			        store:Ext.create("cms.store.common.comboStore").load(
			        {
			        	params:{str:"BDEF_DEFOWNER,CELL_MANAGERT_TYPE"}
			        }),
			        beforeLabelTextTpl : required
				},{
					
					xtype: 'cdef_DefCellCombo',
					id:'WcellNo1D01',
					fieldLabel:'默认储位',
					visable:false,
		            displayField : 'value',
		    		valueField : 'value',
		    		store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
					    proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_getCdef_DefCellCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
					}).load(),
		    	    minChars : 0// 多少字符请求1次  	
		}]
        },
	{
		xtype : 'tabpanel',
		id:'tab1D01',
	    region:'center',
	    items : [
	    {
	    	title:$i18n.basic_info,//新增基本信息
	    	frame : true,
	    	layout:'fit',
	        items:[
			 {
	       	      xtype:'form',
	       	      //title:$i18n.basic_info,
	       	      id:"formBasicInfo1D01",
	       	      frame : true,
	       	      layout: {
		  	       	  type: 'table',
		  	       	  columns: 3
	  	       	  },
	  	       	  defaults : {
	  	       	     xtype : 'textfield',
	  	       	     labelWidth : 110,
	  	       	     margin:'5 4 1 4',
	  	       	     labelAlign:'right'			
	  	       	  },
	       	      items:[
					{		
						fieldLabel : $i18n.owner_address,//货主地址
						id : 'txtOwnerAddress1D01',
						width:813,
						maxLength:120,
						colspan:3
			 		},{
						fieldLabel : $i18n.owner_phone,//货主电话
						maxLength:15,
						id : 'txtOwnerPhone1D01'
			 		},{
						fieldLabel : $i18n.owner_fax,//货主传真
						maxLength:15,
						id : 'txtOwnerFax1D01'
			 		},{
						fieldLabel : $i18n.owner_contact,//货主联系人
						maxLength:20,
						id : 'txtOwnerContact1D01'
			 		},{
						fieldLabel : $i18n.invoice_no,//发票号
						maxLength:20,
						id : 'txtInvoiceNo1D01'
			 		},{
						fieldLabel : $i18n.invoice_addr,//发票地址
						id : 'txtInvoiceAddr1D01',
						width:540,
						maxLength:100,
						colspan:2
			 		},{
						fieldLabel : $i18n.invoice_header,//发票抬头
						id : 'txtInvoiceHeader1D01',
						width:540,
						maxLength:50,
						colspan:2
			 		},{
			 			xtype:'wms_DefFieldValCombo',
						fieldLabel : $i18n.fixedcell_flag,//是否绑定储位
						id : 'cmbFixedcellFlag1D01',
						store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"BDEF_DEFOWNER,FIXEDCELL_FLAG"}
				        }),
				        beforeLabelTextTpl : required
			 		},{
			 			xtype:'wms_DefFieldValCombo',
						fieldLabel : $i18n.turn_over_rule,//周转规则
						id : 'cmbTurnOverRule1D01',
						store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,TURN_OVER_RULE"}
				        }),
				        beforeLabelTextTpl : required
			 		},{
			 			xtype:'wms_DefFieldValCombo',
						fieldLabel : $i18n.scan_flag,//是否自动采集箱码
						id : 'cmbScanFlag1D01',
						store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,SCAN_FLAG"}
				        }),
				        beforeLabelTextTpl : required
			 		},{
			 			//xtype:'textarea',
						fieldLabel : $i18n.remark,//备注
						id : 'txtRemark1D01',
						maxLength:50
			 		},{			
						fieldLabel : '报关号',//报关号     7-21
						id : 'rsvVar21D01',
						maxLength:50
			 		
			 		},{
						fieldLabel : '账册',//账册       7-21
						id : 'rsvVar31D01',
						maxLength:50
			 		}]
    		}]
	},//开始行81
	{
		title:$i18n.policy_info,//策略信息
		frame : true,
		layout:'fit',
		items:[
		{
			xtype:'form',
			//title:$i18n.policy_info,
			id:"formPolicyInfo1D01",
			frame : true,
			layout: {
				type: 'table',
				columns: 2
			},
			defaults : {
				xtype : 'textfield',
				labelWidth : 110,
				width:400,
				margin:'5 4 1 4',
				labelAlign:'right'			
			},
			items:[
			{
				xtype : 'wms_DefStrategyCombo',
     		    fieldLabel : $i18n.i_strategy,//上架策略
		        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		        {
		        	params:{str:"I"}
		        }),
     		    id:'cmbIStrategy1D01',
			    beforeLabelTextTpl : required
			},
			{
				xtype : 'wms_DefStrategyCombo',
     		    fieldLabel : $i18n.o_strategy,//下架策略
		        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		        {
		        	params:{str:"O"}
		        }),
     	    	id:'cmbOStrategy1D01',
			    beforeLabelTextTpl : required
			},{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.m_strategy,//补货策略
  		        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		        {
		        	params:{str:"M"}
		        }),
     	    	id:'cmbMStrategy1D01',
			    beforeLabelTextTpl : required			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.ri_strategy,//返配策略
		        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		        {
		        	params:{str:"RI"}
		        }),
     	    	id:'cmbRiStrategy1D01',
			    beforeLabelTextTpl : required			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.ro_strategy,//退货策略
		        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		        {
		        	params:{str:"RO"}
		        }),
     	    	id:'cmbRoStrategy1D01',
			    beforeLabelTextTpl : required			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.fc_strategy,//盘点策略
		        store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		        {
		        	params:{str:"FC"}
		        }),
     	    	id:'cmbFcStrategy1D01',
			    beforeLabelTextTpl : required			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.rsv_strategy1,//预留策略1
     	    	store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_ARTICLE_GROUP,RSV_STRATEGY1"}
				}),
     	    	id:'cmbRsvStrategy11D01',
     	    	allowBlank : true			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.rsv_strategy2,//预留策略2
				store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_ARTICLE_GROUP,RSV_STRATEGY2"}
				}),
     	    	id:'cmbRsvStrategy21D01',
     	    	allowBlank : true			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.rsv_strategy3,//预留策略3
     	    	store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_ARTICLE_GROUP,RSV_STRATEGY3"}
				}),     	    	
     	    	id:'cmbRsvStrategy31D01',
     	    	allowBlank : true			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.rsv_strategy4,//预留策略4
     	    	store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_ARTICLE_GROUP,RSV_STRATEGY4"}
				}),     	    	
     	    	id:'cmbRsvStrategy41D01',
     	    	allowBlank : true			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.rsv_strategy5,//预留策略5
     	    	store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_ARTICLE_GROUP,RSV_STRATEGY5"}
				}),     	    	
     	    	id:'cmbRsvStrategy51D01',
     	    	allowBlank : true			
     	    },{
     	    	xtype : 'wms_DefStrategyCombo',
     	    	fieldLabel : $i18n.rsv_strategy6,//预留策略6
     	    	store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BDEF_ARTICLE_GROUP,RSV_STRATEGY6"}
				}),     	    	
     	    	id:'cmbRsvStrategy61D01',
     	    	allowBlank : true			
     	    }]
		}]
		}]//开始行79
	},//开始行76
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'bdef_MenuWidget1D01'
   	}
]//开始行15
});