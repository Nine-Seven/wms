/**
* 模块名称：手建盘点计划单
* 模块编码：8101
* 创建：周欢
*/
var fcdata_plan_m8101 = Ext.create('cms.store.fcdata.fcdata_PlanMStore', {
autoLoad : true
});
Ext.define('cms.view.fcdata.fcdata_PlanUI', {
	alias : 'widget.fcdata_PlanUI',
	title : $i18n.title8101,//手建盘点计划单
	width : '100%',
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
			'cms.view.common.commMenu', 
			'cms.view.common.commMenu3',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.commMenu6',
			'cms.view.common.bdef_DefArticleCombo',
			'cms.view.common.remoteCombo',
			'cms.view.common.cdef_DefAreaCombo',
			'cms.view.common.cdef_DefWareCombo',
			'cms.view.common.cdef_DefStockCombo',
			'cms.view.common.cdef_DefCellCombo',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.bdef_DefGroupNoCombo'
	],
	items : [{
		xtype : 'tabpanel',
		id : 'tabPId8101',
		region : 'center',
		items : [{
			title : $i18n.titleM,
			layout : 'border',
			items : [
			{
			xtype : 'toolbar',
			region : 'north',
			id:'menu_M_8101',
			items : [ {
				text : $i18n.find,
				iconCls : 'query',
				name : 'detailQuery'
			},{
				text : $i18n.start_se,
				iconCls : 'send',
				name : 'detailSend'
			},{
				text : $i18n.closed,
				id : 'closedTest',		//7-16添加
				iconCls : 'edit',
				name : 'detailEdit'
			},{
				text : $i18n.cancel,
				iconCls : 'undo',
				name : 'undoOrder'
			}]
		},{
			xtype : 'form',
			id : 'fcdata_selectByOwnerNo',
			region : 'north',
			layout:'column',
			frame : true,
			items : [ {
				layout : {
				type : 'table',
				columns : 2
				},
				xtype : 'container',
				defaults : {
					labelWidth : 90,
					margin : '2 2 2 2',
					labelAlign : 'right',
					width : 300
				},
				items : [{
				 xtype:'combo',
				 fieldLabel:$i18n.owner,//货主编号
            	 queryMode:'local',
	             allowBlank:true,
	             id:'cmbOwnerNo8101_M',
	             displayField: 'dropValue',
				 valueField: 'value',
				 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	             //beforeLabelTextTpl:required
	         },{
				xtype : 'radiogroup',
				id : 'raidioFcdata_typeMain8101',
				fieldLabel : $i18n.fcdata_type,//盘点类型
				columns : 3,
				items : [{
					boxLabel : $i18n.fcdata,//盘点
					name : 'ft',
					inputValue : '1',
					checked : true
					},
					{
					boxLabel : $i18n.circle_plane,//循环盘
					name : 'ft',
					inputValue : '2'
					//checked : true		7-16屏蔽的
					},
					{
						boxLabel : $i18n.untieSell_plan,//动销盘
						name : 'ft',
						inputValue : '3'
					}//储位盘
				],
				beforeLabelTextTpl : required,
				//7-16添加
				 listeners:{  
						'change':function(th,item,eOpts ){
							//debugger;
							//获得下拉框的value值
							var t = Ext.getCmp('raidioFcdata_typeMain8101').getValue();
							if(t.ft == '2' || t.ft == '3')
	                        {
								Ext.getCmp('closedTest').setDisabled(true);
	                        }else{
	                        	Ext.getCmp('closedTest').setDisabled(false);
	                        }
						}
					}   
				},{
				xtype : 'datefield',
				fieldLabel : $i18n.begin_date,//日期范围
				id : 'date_scope8101',
				format : 'Y-m-d',
				hidden:true,
				beforeLabelTextTpl : required
				}]
			}]
		}, {
			region : 'center',
			xtype : 'grid',
			id : 'grid_M_8101',
			store : fcdata_plan_m8101,
			columns : [{
			xtype : 'rownumberer',
			width : 30
			},{
				width : 80,
				text : $i18n.owner_no,//货主
				dataIndex : 'ownerNo'
			},{
			width : 150,
			text : $i18n.plan_no,//计划单号
			dataIndex : 'planNo'
			}, {
			width : 150,
			text : $i18n.plan_type,//盘点类型
			dataIndex : 'planType',
			renderer : function(v) {
				if (v == '0') {
				return $i18n.artplan;//商品盘
				} else if (v == '1') {
				return $i18n.cellplan;//储位盘
				}
			}
			}, 
			{
			width : 150,
			text : $i18n.begin_time,//开始日期
			dataIndex : 'beginDate'
			}, {
			width : 150,
			text : $i18n.end_time,//结束日期
			dataIndex : 'endDate'
			}, {
			width : 150,
			text : $i18n.status2,//操作状态
			dataIndex : 'statusText'
			}]
			}],
			dockedItems : [{
				xtype : 'pagingtoolbar',
				store : fcdata_plan_m8101,
				dock : 'bottom',
				displayInfo : true
			}]
		}, {
			title : $i18n.titleD,
			layout : 'border',
			itemId : 'tabPId8101i',
				items : [{
					xtype : 'commMenuWidget',
					id : 'menu8101',
					region : 'north'
				}, {
					xtype : 'form',
					id : 'fcdata_Plan_MForm',
					region : 'north',
					frame : true,
					layout : {
						type : 'table',
						columns : 2
					},
					defaults : {
						labelWidth : 90,
						margin : '2 2 2 2',
						labelAlign : 'right',
						width : 400
					},
					items : [{
							xtype : 'textfield',
							fieldLabel : $i18n.plan_no,//计划单号
							id : 'plan_no8101',
							readOnly : true,
							beforeLabelTextTpl : required
						}, {
							xtype : 'bdef_DefOwnerCombo',
							fieldLabel : $i18n.owner,// 委托业主
							id : 'owner8101',
							store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
							beforeLabelTextTpl : required
						},{
							xtype : 'radiogroup',
							id : 'fcdata_type8101',
							fieldLabel : $i18n.fcdata_type,//盘点类型
							columns : 3,
							items : [{
								boxLabel : $i18n.fcdata,//盘点
								name : 'ft',
								inputValue : '1'
								},
								{
								boxLabel : $i18n.circle_plane,//循环盘
								name : 'ft',
								inputValue : '2'
								},
								{
									boxLabel : $i18n.untieSell_plan,//动销盘
									name : 'ft',
									inputValue : '3'
								}//储位盘
							],
							beforeLabelTextTpl : required
						}, {
						xtype : 'radiogroup',
						id : 'plan_type8101',
						fieldLabel : $i18n.plan_type,//盘点类别
						columns : 3,
						items : [{
							boxLabel : $i18n.artplan,
							name : 'rb',
							inputValue : '0'
							},//商品盘
							{
							boxLabel : $i18n.cellplan,
							name : 'rb',
							inputValue : '1'
							}//储位盘
						],
						beforeLabelTextTpl : required
					   },{
						xtype : 'datefield',
						fieldLabel : $i18n.plan_date,//盘点日期
						id : 'plan_date8101',
						format : 'Y-m-d',
						beforeLabelTextTpl : required
						}, 
						{
						xtype : 'datefield',
						fieldLabel : $i18n.begin_time,//开始日期
						id : 'begin_date8101',
						format : 'Y-m-d',
						beforeLabelTextTpl : required
						}, {
						xtype : 'datefield',
						fieldLabel : $i18n.end_time,//结束日期
						id : 'end_date8101',
						format : 'Y-m-d',
						beforeLabelTextTpl : required
						},{
						 	xtype:'wms_DefFieldValCombo',
			       	      	fieldLabel : $i18n.orgNo,// 机构代码
							id : 'orgNo8101',	
				       	    //editable:false,
				       	    readOnly:true,
				       	    store:Ext.create("cms.store.common.comboStore").load(
				       	    {
				       	         params:{str:"N,ORG_NO"}
				       	    }),
				       	    allowBlank : false,
				       	    beforeLabelTextTpl : required
						},{
						xtype : 'textfield',
						fieldLabel : $i18n.recede_des,//备注
						id : 'recede_des8101'
						}]
					}, {
					xtype : 'grid',//商品检查
					region : 'center',
					id : 'grid_02_8101',
					loadMask : true, // 加载时有加载的图标
					store : Ext.create('cms.store.fcdata.fcdata_PlanDStore'),
					selModel : {
						selType : 'cellmodel'
					},
					plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
						clicksToEdit : 1,
						onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.fcdata.fcdata_PlanController',e.getKey());
						}
					})],
					columns : [{
						xtype : 'rownumberer',
						width : 30
					   }, 
					   {
						width : 140,
						text : $i18n.group_no,//类别编码
						cls : 'notnull',
						field : {
							id : 'group_no8101',
							xtype : 'bdef_DefArticleCombo',
							store:Ext.create('cms.store.bdef.bdef_DefGroupNoComboStore'),
							displayField : 'value',
							valueField : 'value',
							allowBlank : false
						},
						dataIndex : 'groupNo'
						},{
						width : 140,
						text : $i18n.group_name,//类别名称
						dataIndex : 'groupName'
						},{
						width : 120,
						text : $i18n.article_no,//商品编码
						dataIndex : 'articleNo',
						cls : 'notnull',
						field : {
							id : 'article_no8101',
							xtype : 'bdef_DefArticleCombo',
							store : Ext.create("cms.store.bdef.bdef_DefArticleComboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'fcdata_PlanAction_queryBdefArticleCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								},
								autoLoad:true
			   				}),
							displayField : 'value',
							valueField : 'value',
							allowBlank : false
						}
						}, {
						width : 140,
						text : $i18n.owner_article_no,//货主商品编码
						dataIndex : 'ownerArticleNo'
						}, {
						width : 250,
						text : $i18n.article_name,//商品名称
						dataIndex : 'articleName'
						}],
						dockedItems : [{
							xtype : 'commMenuWidget6',
							id : 'menu_d_8101',
							region : 'north'
						}
						]
					}, {
					xtype : 'grid',//储位检查
					id : 'grid_03_8101',
					region : 'center',
					loadMask : true, // 加载时有加载的图标
					store : Ext.create('cms.store.fcdata.fcdata_PlanDStore'),
					selModel : {
						selType : 'cellmodel'
					},
					plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
						clicksToEdit : 1,
						onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.fcdata.fcdata_PlanController',e.getKey());
						}
					})],
					columns : [{
						xtype : 'rownumberer',
						width : 30
						}, {
						width : 120,
						text : $i18n.ware_no,//仓区
						dataIndex : 'wareNo',
						cls : 'notnull',
						field : {
							xtype : 'cdef_DefWareCombo',
							id : 'warearea8101',
							store : Ext.create('cms.store.cdef.cdef_DefWareComboStore'),//.load()
							displayField : 'dropValue',
						    valueField : 'value',
			    			minChars : 0,// 多少字符请求1次
			    			allowBlank :false
						}
						}, {
						width : 140,
						text : $i18n.ware_name,//仓区名称
						dataIndex : 'wareName'
						}, {
						width : 120,
						text : $i18n.s_area_no,//储区代码
						dataIndex : 'areaNo',
						cls : 'notnull',
						field : {
							xtype : 'cdef_DefAreaCombo',
							id : 's_area_no8101',
							store : Ext.create('cms.store.cdef.cdef_DefAreaComboStore'),//.load(),
							displayField : 'dropValue',
						    valueField : 'value',
			    			minChars : 0,// 多少字符请求1次
			    			allowBlank :false
						}
						}, {
						width : 140,
						text : $i18n.area_name,//储区名称
						dataIndex : 'areaName'
						}, {
						width : 120,
						text : $i18n.stock_no,//通道代码
						dataIndex : 'stockNo',
						cls : 'notnull',
						field : {
							xtype : 'cdef_DefStockCombo',
							id : 'stock_no8101',
							store : Ext.create('cms.store.cdef.cdef_DefStockComboStore'),//.load(),
							displayField : 'value',
			    			valueField : 'value',
			    			minChars : 0,// 多少字符请求1次
			    			allowBlank :false
						}
						},{
						width : 120,
						text : $i18n.cell_no,//储位
						dataIndex : 'cellNo',
						cls : 'notnull',
						field : {
							xtype: 'cdef_DefCellCombo',
							id:'cellNo8101',
			                displayField : 'value',
			    			valueField : 'value',
			    			store : Ext.create("cms.store.cdef.cdef_DefCellComboStore"),
			    			minChars : 0,// 多少字符请求1次
			    			allowBlank :false
						}
						}],
						dockedItems : [{
							xtype : 'commMenuWidget6',
							id:'menu_03_8101',
							region : 'north'
						}]
					},{
					xtype : 'panel',
					id : 'msterForm8101',
					region:'south',
					html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
							+ 'background-color:#C1D5ED; text-align: left;">'
							+ '<span><label>'+$i18n.addPeople+':</label><label id="rgstName8101"></label> </span> '
							+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.addTime+'：</label><label id="rgstDate8101"></label></span>'
							+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.local_path+'：</label><label id="updtName8101"></label> </span> '
							+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.editTime+'：</label><label id="updtDate8101"></label> </span></div>'
				}]
				}]
			}]
	});
