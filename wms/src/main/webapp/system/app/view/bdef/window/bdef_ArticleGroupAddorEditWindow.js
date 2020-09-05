/**
 * 模块名称：商品类别维护 window
 * 模块编码：1301
 * 创建：周欢
 */

Ext.define('cms.view.bdef.window.bdef_ArticleGroupAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.bdef_ArticleGroupAddorEditWindow',
	layout : 'border',
	width : 950,
	height : 615,
	modal : true,
	id : 'bdef_ArticleGroupAddorEditWindow',
	items : [
	{
		xtype : 'form',
		region : 'north',
		id:'IdForm1301',
		layout:'border',
		height:100,
		frame : true,
	    	 layout:{
		   			type:'table',
		   			columns:3
	   		},
	   		defaults : {
	   			xtype : 'textfield',
	   			labelWidth : 120,
	   			labelAlign:'right'			
	   		},
	   		items:[
	   			{
				    xtype:'bdef_DefOwnerCombo',
				    fieldLabel:$i18n.owner_no,//货主编号
				    id:'cmbOwnerNo1301',
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
						listeners:{  
							'load':function(th,records,successful,eOpts ){						
								if(Ext.getCmp("cmbFormOwner1301").getValue()!=null &&Ext.getCmp("cmbFormOwner1301").getValue()!=""){
									//Ext.getCmp('cmbOwnerNo1301').setValue(Ext.getCmp("cmbFormOwner1301").getValue());
									_myAppGlobal.getController('cms.controller.bdef.bdef_ArticleGroupController').cmbOwnerNo1301Select();
								}else if(th.count()>0){
									//Ext.getCmp('cmbOwnerNo1301').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.bdef.bdef_ArticleGroupController').cmbOwnerNo1301Select();
								}
							}
						}
					}),
				    displayField : 'dropValue',
				    valueField : 'value',
				    allowBlank : false,
				    beforeLabelTextTpl : required
				},{
					fieldLabel:$i18n.group_level,//类别级别
					id:'txtGroupLevel1301',
					xtype:'wms_DefFieldValCombo',
			        store:Ext.create("cms.store.common.comboStore").load(
			        {
			        	params:{str:"BDEF_ARTICLE_GROUP,GROUP_LEVEL"}
			        }),
			        allowBlank : false,
			        beforeLabelTextTpl : required
				},{
						xtype : 'combo',
						fieldLabel:$i18n.l_group,//商品大类代码
						id:'cmbLgroupNo1301',
						displayField: 'dropValue',
						valueField: 'value',
					    store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'bdef_ArticleGroupAction_getLGroupComboList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
							//autoLoad:true
						})
					},{
						xtype : 'combo',
						fieldLabel:$i18n.m_group,//商品中类代码
						id:'cmbMgroupNo1301',
						displayField: 'dropValue',
						valueField: 'value',
					    store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'bdef_ArticleGroupAction_getMGroupComboList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
						})
					},{
					fieldLabel:$i18n.group_no,//类别编码
					id:'txtGroupNo1301',
					maxLength:20,
			        allowBlank : false,
			        beforeLabelTextTpl : required
				},{
				    xtype:'textfield',
					fieldLabel:$i18n.group_name,//类别名称
					id:'txtGroupName1301',
					labelAlign:'right',
					maxLength:45,
			        allowBlank : false,
			        beforeLabelTextTpl : required
				},{
				    xtype:'wms_DefFieldValCombo',
					fieldLabel:'业态',//业态
					id:'txtBatchId1301',
					store:Ext.create("cms.store.common.comboStore",{
						proxy:{
							type:'ajax',
							method:'post',
							url : 'bdef_ArticleGroupAction_getBatchIdCombo.action',
					        reader: {
					    		type:'json',
					            root: 'rootList',    
					            totalProperty: 'totalCount'
					        }
					    }
					}).load(),
			        allowBlank : false,
			        beforeLabelTextTpl : required
				} ,{	
				    xtype:'wms_DefFieldValCombo',
					fieldLabel:$i18n.status,//状态
					id:'cmbStatus1301',
					store:Ext.create("cms.store.common.comboStore").load(
			        {
			       	 params:{str:"N,DEF_STATUS"}
			        }),
			        allowBlank : false,
			        beforeLabelTextTpl : required
				} ]
	},
	{
    	xtype : 'tabpanel',
		id:'WindowTabId1301',
		region:'center',
		items:[
         {

    	   title:$i18n.controll_manage,// 控制管理
    	   layout:'fit',
	       id:'tabPId1301_2',
	       height : 350,
	       itemId:'tabPId1301_2item',
	      items:[
	      {
	    	  xtype : 'form',
	          region:'center',
	   		  id:'form_1301_02',
	   		  frame : true,
	       	  items : [
	       	  {
	       	      xtype:'fieldset',
	       	      title:$i18n.quality_info,
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
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.check_qty_flag,//数量抽检标识
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,CHECK_QTY_FLAG"}
 						       }),
     	    			  id : 'cmbCheckQtyFlag1301',
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'numberfield',
     	    			  fieldLabel : $i18n.check_qty_rate,//数量抽检比率
     	    			  id : 'numCheckQtyRate301',
     	    			  colspan:2,
     	    			  minValue:0,
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.check_weight_flag,//重量抽检标识
     	    			  id : 'cmbCheckWeightFlag1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,CHECK_WEIGHT_FLAG"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'numberfield',
     	    			  fieldLabel : $i18n.check_weight_rate,//重量抽检比率
     	    			  minValue:0,
     	    			  id : 'numCheckWeightRate1301',
     	    			  colspan:2,
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.qc_flag,//质检标识
     	    			  id : 'cmbQcFlag1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,QC_FLAG"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'numberfield',
     	    			  fieldLabel : $i18n.qc_rate,//质检比率
     	    			  minValue:0,
     	    			  id : 'cmbQcRate1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"BDEF_ARTICLE_GROUP,QC_RATE"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.double_check,//双人验收标识
     	    			  id : 'cmbDoubleCheck1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,DOUBLE_CHECK"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  }]
	       	  },{
	       	      xtype:'fieldset',
	       	      title:$i18n.gurantee_manage,
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
	       	    	  xtype : 'numberfield',
   	    			  fieldLabel : $i18n.alarmrate,//报警比率
   	    			  minValue:0,
   	    			  id : 'txtAlarmrate1301',
   	    			  allowBlank : false,
			          beforeLabelTextTpl : required
	       		  },
	       		 {		
	       	    	  xtype : 'numberfield',
   	    			  fieldLabel : $i18n.freezerate,//冻结比率
   	    			  minValue:0,
   	    			  id : 'txtFreezerate1301',
   	    			  allowBlank : false,
			          beforeLabelTextTpl : required
	       		  },
	       		 {		
	       	    	  xtype : 'wms_DefFieldValCombo',
   	    			  fieldLabel : $i18n.turn_over_rule,//周转规则
   	    			  id : 'cmbTurnOverRule301',
   	    			  store:Ext.create("cms.store.common.comboStore").load(
					       {
					       	params:{str:"N,TURN_OVER_RULE"}
					       }),
   	    			  allowBlank : false,
				      beforeLabelTextTpl : required
	       		  }]
	       	   },{
		       	      xtype:'fieldset',
		       	      title:$i18n.exceed_rule,
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
		       	    	  xtype : 'numberfield',
	   	    			  fieldLabel : $i18n.check_excess,//超量比率
	   	    			  minValue:0,
	   	    			  id : 'numCheckExcess1301',
	   	    			  allowBlank : false,
				          beforeLabelTextTpl : required
		       		  },
		       		 {		
		       	    	  xtype : 'numberfield',
	   	    			  fieldLabel : $i18n.um_check_excess,//返配超量
	   	    			  minValue:0,
	   	    			  id : 'numUmCheckExcess1301',
	   	    			  allowBlank : false,
				          beforeLabelTextTpl : required
		       		  },
		       		 {		
		       	    	  xtype : 'numberfield',
	   	    			  fieldLabel : $i18n.pick_excess,//拣货超量
	   	    			  minValue:0,
	   	    			  id : 'numPickExcess1301',
	   	    			  allowBlank : false,
				          beforeLabelTextTpl : required
		       		  },
		       		 {		
			       	    	  xtype : 'numberfield',
		   	    			  fieldLabel : $i18n.divide_excess,//分播超量
		   	    			  minValue:0,
		   	    			  id : 'numDivideExcess1301',
		   	    		  	  allowBlank : false,
 					          beforeLabelTextTpl : required
			       	 }]
		       	   },{
	       		   xtype:'fieldset',
	       	   	   title:$i18n.others,
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
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.store_condition,//存储条件
     	    			  id : 'cmbTemperatureFlag1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
					      {
					       	  params:{str:"N,TEMPERATURE_FLAG"}
					      }),
     	    			  allowBlank : false,
				          beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.scan_flag,//扫描标识
     	    			  id : 'cmbScanFlag1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,SCAN_FLAG"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.measure_model,//计量方式
     	    			  id : 'txtMeasureMode1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,MEASURE_MODE"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{		
	  	       	    	  xtype : 'wms_DefFieldValCombo',
     	    			  fieldLabel : $i18n.mix_flag,//混属性标识
     	    			  id : 'cmbMixFlag1301',
     	    			  store:Ext.create("cms.store.common.comboStore").load(
 						       {
 						       	params:{str:"N,MIX_FLAG"}
 						       }),
     	    			  allowBlank : false,
	  				      beforeLabelTextTpl : required
	  	       		  },{
					    	xtype:'wms_DefFieldValCombo',
					        fieldLabel : $i18n.wms_print_flag,//吊牌标识
					        id : 'cmbPrintFlag1301',
					        editable:false,
					        store:Ext.create("cms.store.common.comboStore").load(
					        {
					        	params:{str:"BDEF_DEFARTICLE,PRINT_FLAG"}
					        }),
					        allowBlank : false,
					        beforeLabelTextTpl : required
					    }]
	       	   }]
			  }]
		       },{

		 	      title:$i18n.strategy_info,//策略信息
		 	      id:'tabPId1301_3',
		 	      itemId:'tabPId1301_3i',
		 	      region:'center',
		 	      layout:'border',
		 	      items : [
		 	      {
		 	   	    xtype : 'form',
		 	   		region:'center',
		 	   		id:'form_1301_03',
		 	   		frame : true,
		 	   		layout:{
		 	   			type:'table',
		 	   			columns:2
		 	   		},
		 	   		defaults : {
		 	   			xtype : 'textfield',
			 	   		labelWidth : 110,
						width:400,
		  	       	    margin:'5 4 1 4',
		 	   			labelAlign:'right'			
		 	   		},
		 	   		items : [
		             {
		             	xtype : 'wms_DefStrategyCombo',
		     		    fieldLabel : $i18n.i_strategy,//上架策略
		     		    store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
					        {
					        	params:{str:"I"}
					        }),
		     		    id:'cmbIStrategy1301',
		     		    allowBlank : false,
 					    beforeLabelTextTpl : required			
		     	    },{
		     	    	xtype : 'wms_DefStrategyCombo',
		     	    	fieldLabel : $i18n.o_strategy,//下架策略
		     	    	store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
					        {
					        	params:{str:"O"}
					        }),
		     	    	id:'cmbOStrategy1301',
		     	    	allowBlank : false,
 					    beforeLabelTextTpl : required		
		     	    },{
		     	    	xtype : 'wms_DefStrategyCombo',
		     	    	fieldLabel : $i18n.m_strategy,//补货策略
		     	    	store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
				        {
				        	params:{str:"M"}
				        }),
		     	    	id:'cmbMStrategy1301',
		     	    	allowBlank : false,
 					    beforeLabelTextTpl : required			
		     	    },{
		     	    	xtype : 'wms_DefStrategyCombo',
		     	    	fieldLabel : $i18n.ri_strategy,//返配策略
		     	    	 store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
 				        {
 				        	params:{str:"RI"}
 				        }),
		     	    	id:'cmbRiStrategy1301',
		     	    	allowBlank : false,
 					    beforeLabelTextTpl : required			
		     	    },{
		     	    	xtype : 'wms_DefStrategyCombo',
		     	    	fieldLabel : $i18n.ro_strategy,//退货策略
		     	    	 store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
		 				        {
		 				        	params:{str:"RO"}
		 				        }),
		     	    	id:'cmbRoStrategy1301',
		     	    	allowBlank : false,
 					    beforeLabelTextTpl : required			
		     	    },{
		     	    	xtype : 'wms_DefStrategyCombo',
		     	    	fieldLabel : $i18n.fc_strategy,//盘点策略
		     	    	store:Ext.create("cms.store.wms.wms_DefStrategyComboStore").load(
						        {
						        	params:{str:"FC"}
						        }),
		     	    	id:'cmbFcStrategy1301',
		     	    	allowBlank : false,
 					    beforeLabelTextTpl : required			
		     	    },{
		     	    	xtype : 'textfield',
		     	    	fieldLabel : $i18n.rsv_strategy1,//预留策略1
		     	    	id:'cmbRsvStrategy11301',
		     	    	allowBlank : true			
		     	    },{
		     	    	xtype : 'textfield',
		     	    	fieldLabel : $i18n.rsv_strategy2,//预留策略2
		     	    	id:'cmbRsvStrategy21301',
		     	    	allowBlank : true			
		     	    },{
		     	    	xtype : 'textfield',
		     	    	fieldLabel : $i18n.rsv_strategy3,//预留策略3
		     	    	id:'cmbRsvStrategy31301',
		     	    	allowBlank : true			
		     	    },{
		     	    	xtype : 'textfield',
		     	    	fieldLabel : $i18n.rsv_strategy4,//预留策略4
		     	    	id:'cmbRsvStrategy41301',
		     	    	allowBlank : true			
		     	    },{
		     	    	xtype : 'textfield',
		     	    	fieldLabel : $i18n.rsv_strategy5,//预留策略5
		     	    	id:'cmbRsvStrategy51301',
		     	    	allowBlank : true			
		     	    },{
		     	    	xtype : 'textfield',
		     	    	fieldLabel : $i18n.rsv_strategy6,//预留策略6
		     	    	id:'cmbRsvStrategy61301',
		     	    	allowBlank : true			
		     	    }]
		 	    }]
		   }]
	},
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'bdef_MenuWidget1301'
	}]
});