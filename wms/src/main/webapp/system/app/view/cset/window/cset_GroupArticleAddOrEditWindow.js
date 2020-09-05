/**
 * 模块名称：商品类别货位对照关系
 * 模块编码：2401
 * hekl
 */
Ext.define('cms.view.cset.window.cset_GroupArticleAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.cset_GroupArticleAddOrEditWindow',
	layout:'border',
	id:'cset_GroupArticleAddOrEditWindow',
    height:390,
	width:680,
	modal:true,
	items:[
	       {
	    	   xtype:'form',
	    	   region:'center',
	    	   id:'cset_Group_ArticleAddOrEditForm',
	    	   frame:true,
	    	   layout:'column',
	       	   items:[
	       	      {  
	       	       id:'up2401window',
	               layout: {
	 	   	          type: 'table',
	 	   	          columns: 2
	       	   	   },
	               xtype:'fieldset',  
	               autoHeight:true,  
	               width:'99%',
	               defaults:{
		    	   		xtype:'textfield',
		    	   		margin:'3 3 0 3',
		    	   		labelAlign:'right',
		    	   		labelWidth:130
		       	   },
	               items:[
	                  {
	                  	xtype:'bdef_DefOwnerCombo',
						fieldLabel:$i18n.owner,//委托业主
			 	    	id:'owner_no2401',
			 	    	displayField : 'dropValue',
					    valueField : 'value',
				        allowBlank : false,
				        editable:false,
			 	    	beforeLabelTextTpl : required,
			 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 	    	listeners:{  
		    		    		'load':function(th,records,successful,eOpts){
		    		    			if(th.count()>0){
		    		    				Ext.getCmp('owner_no2401').setValue(records[0].data.value);
		    		    			}
		    		    		}
		    		    	}
			 	    	}).load(),
			 	    	readOnly:false
					}/*,{
						fieldLabel:$i18n.article_no_or_barcode,//商品编码或条码
						id : 'owner_article_no2401',
						xtype:'bdef_DefArticleCombo',
						displayField : 'value',
						valueField : 'value',
						store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'cset_CellArticleAction_queryOwnerArticleNoList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
						})
						beforeLabelTextTpl : required
			 	    }*/,{
						fieldLabel:$i18n.group_no_or_name,//类别编码或名称
						id : 'article_group2401',
						xtype:'bdef_DefGroupNoCombo',
						displayField : 'dropValue',
						valueField : 'value',
						store:Ext.create("cms.store.bdef.bdef_ArticleGroupComboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'cset_GroupArticleAction_queryArticleGroupList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
						}),
						beforeLabelTextTpl : required
			 	    },{
			 	    	fieldLabel:$i18n.group_no,//类别编码
			 	    	id:'group_no2401',
			 	    	maxLength:20,
			 	    	allowBlank:false
			 	    },{
			 	    	fieldLabel:$i18n.group_name,//类别名称
			 	    	id:'group_name2401',
			 	    	maxLength:13,
			 	    	allowBlank:false
			 	    },{
			 	    	fieldLabel:$i18n.barcode,//商品条码
			 	    	id:'barcode2401',
			 	    	maxLength:20,
			 	    	allowBlank:false
			 	    },{
			 	    	fieldLabel:$i18n.article_no,//商品编码
			 	    	id:'article_no2401',
			 	    	maxLength:13,
			 	    	allowBlank:false
			 	    },{
			 	    	fieldLabel:$i18n.article_name,//商品名称
			 	    	id:'article_name2401',
			 	    	maxLength:120,
			 	    	allowBlank:false,
			 	    },{
			 	    	fieldLabel:$i18n.article_group_no,//所属类别编码
			 	    	id:'article_group_no2401',
			 	    	maxLength:13,
			 	    	allowBlank:false,
			 	    },{
			 	    	fieldLabel:$i18n.article_group_name,//所属类别名称
			 	    	id:'article_group_name2401',
			 	    	maxLength:120,
			 	    	allowBlank:false,
			 	    }   
	                ]  
	              },{  
	               layout: {
	 	   	          type: 'table',
	 	   	          columns: 2
	       	   	   },
	               xtype:'fieldset',  
	                id:'down2401window',
	               autoHeight:true,  
	               width:'99%',
	               defaults:{
		    	   		xtype:'textfield',
		    	   		margin:'3 3 0 3',
		    	   		labelAlign:'right',
		    	   		labelWidth:130
		       	   },
	               items:[
	                {
			 	    	fieldLabel:$i18n.packing_qty,//包装数量
			 	    	maxLength:18,
			 	    	allowBlank:false,
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qty2401',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	xtype:'wms_DefFieldValCombo',
			 	    	fieldLabel:'拣货类型',//拣货类型
			 	    	id:'o_type2401',
			 	    	allowBlank:false,
			 	    	beforeLabelTextTpl : required,
			    		store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"CSET_CELL_ARTICLE,PICK_TYPE"}
						})
			 	    },{
			 	    	xtype:'cdef_DefWareCombo',
			 	    	fieldLabel:$i18n.ware_no,//库区
			 	    	id:'ware_no2401',
			 	    	allowBlank:false,
			 	    	beforeLabelTextTpl : required,
						store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',
						{
							proxy:{
								type:'ajax',
								method:'post',
								async:false,
								url:'cset_AreaBackupAction_getCdef_DefAreaCombo2.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "3"
								}
							},
						   listConfig: {
					           loadingText: '查询中...',
					           emptyText: '没有找到相应的数据！' ,
					           getInnerTpl: function() {
					        	   return '{value}';
					           }
					       }
					   	}),
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	xtype:'cdef_DefAreaCombo',
			 	    	fieldLabel:$i18n.area_no,//储区代码
			 	    	id:'area_no2401',
			 	    	allowBlank:false,
			 	    	store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'cset_AreaBackupAction_getCdef_DefAreaCombo2.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "2"
								}
							},
						   listConfig: {
					           loadingText: '查询中...',
					           emptyText: '没有找到相应的数据！' ,
					           getInnerTpl: function() {
					        	   return '{value}';
					           }
					       }
					   	}),
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	xtype:'cdef_DefStockCombo',
			 	    	fieldLabel:$i18n.stock_no,//通道全称
			 	    	id:'stock_no2401',
			 	    	//maxLength:5,
			 	    	allowBlank:false,
			 	    	store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
			 	    		proxy:{
								type:'ajax',
								method:'post',
								url:'cset_AreaBackupAction_getCdef_DefStockCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "1"
								}
							},
						   listConfig: {
					           loadingText: '查询中...',
					           emptyText: '没有找到相应的数据！' ,
					           getInnerTpl: function() {
					        	   return '{value}';
					           }
					       }
			 	    	}),
			 	    	beforeLabelTextTpl : required
			 	    },{
			 	    	fieldLabel:$i18n.cell_no,//货位
			 	    	id:'cell_no2401',
			 	    	maxLength:24,
			 	    	xtype: 'cdef_DefCellCombo',
		                displayField : 'value',
		    			valueField : 'value',
		    			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
		    			   proxy:{
								type:'ajax',
								method:'post',
								url:'cset_AreaBackupAction_getCdef_DefCellCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
						   listConfig: {
					           loadingText: '查询中...',
					           emptyText: '没有找到相应的数据！' ,
					           getInnerTpl: function() {
					        	   return '{value}';
					           }
					       }
		    			}),
			 	    	//allowBlank:false,
			 	    	//beforeLabelTextTpl : required
			 	    },{
			 	    	id:'stockX2401',
			 	    	hidden:true
			 	    },
			 	    {
			 	    	xtype:'cset_LineCombo',
			 	    	fieldLabel:$i18n.line,//线路
			 	    	id:'cmbLineId2401',
			 	    	colspan:2,
			 	    	allowBlank:false,
			 	    	store:Ext.create('cms.store.cset.cset_LineComboStore',{
			 	    		listeners:
		    		    	{  
		    		    		'load':function(th,records,successful,eOpts )
		    		    		{
		    		    			if(th.count()>0){
		    		    				
		    		    			}else{
		    		    				Ext.example.msg($i18n.prompt,'该区域没有设置保拣线！');
		    		    			}
		    		    		}
		    		    	}
			 	    		
			 	    	}),
			 	    	beforeLabelTextTpl : required
			 	    }
		    ]},{
		    	  
	               layout: {
	 	   	          type: 'table',
	 	   	          columns: 2
	       	   	   },
	               xtype:'fieldset',  
	                id:'down2202window',
	               autoHeight:true,  
	               width:'99%',
	               defaults:{
		    	   		xtype:'textfield',
		    	   		margin:'3 3 0 3',
		    	   		labelAlign:'right',
		    	   		labelWidth:130
		       	   },
	               items:[
	              {
		 	    	fieldLabel:$i18n.keep_cells,//非A类最大可用货位数
		 	    	id:'keep_cells2401',
		 	    	margin:'3 3 0 3',
		 	    	maxLength:3,
		 	    	beforeLabelTextTpl : required
		 	    },{
		 	    	fieldLabel:$i18n.max_qty_box,//最大存储量（箱）
		 	    	id:'max_qty_na2401',
		 	    	maxLength:18,
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required
		 	    },{
		 	    	fieldLabel:$i18n.supp_qty_box, //'循环补货触发量（箱）
		 	    	id:'supp_qty_na2401',
		 	    	maxLength:18,
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required
		 	    },{
		 	    	fieldLabel:$i18n.alert_qty_box,//补货警示量（箱）
		 	    	id:'alert_qty_na2401',
		 	    	maxLength:18,
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required
		 	    }			                
		    ]}
		    ]},{
		 			region:'south',
					xtype:'commMenuWidget5',
					border:0,
					id:'menuWidget52401'
		 	}
  ]
});
