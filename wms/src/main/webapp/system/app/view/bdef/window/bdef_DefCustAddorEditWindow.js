/**
 * 模块名称：客户资料维护 模块编码：1501 创建：Jun
 */
Ext.define('cms.view.bdef.window.bdef_DefCustAddorEditWindow', {
			extend : 'Ext.window.Window',
			alias : 'widget.bdef_DefCustAddorEditWindow',
			layout : 'border',
			id : 'bdef_DefCustAddorEditWindow',
			width : 900,
			height : 600,
			modal : true,
			items : [{
				xtype : 'form',
				region : 'center',
				id : 'bdef_DefCustAddorEditForm',
				frame : true,
				items : [{
					xtype : 'fieldset',
					layout : {
						type : 'table',
						columns : 3
					},
					defaults : {
						xtype : 'textfield',
						margin : '5 4 1 4',
						labelAlign : 'right',
						labelWidth : 120
					},
					items : [{
						xtype : 'bdef_DefOwnerCombo',
						fieldLabel : $i18n.owner_no,// 委托业主编号
						id : 'ownerNo1501',
						store : Ext
								.create('cms.store.bdef.bdef_DefOwnerComboStore')
								.load(),
						displayField : 'dropValue',
						valueField : 'value',
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.custFlag,// 客户标识
						id : 'if_main_customer1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,CUST_FLAG"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.cust_type,// 客户类型
						id : 'custType1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,CUST_TYPE"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						fieldLabel : $i18n.owner_cust_no,// 委托业主客户代码
						id : 'ownerCustNo1501',
						maxLength : 15,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						fieldLabel : $i18n.cust_no,// 客户编号
						id : 'custNo1501',
						allowBlank : false,
						maxLength : 20,
						beforeLabelTextTpl : required
					}, {
						fieldLabel : $i18n.supplier_note_code,// 助记码资料维护
						id : 'cust_noteCode11501',
						maxLength : 20
					}, {
						fieldLabel : $i18n.cust_name,// 客户名称
						id : 'custName1501',
						width : 560,
						colspan : 2,
						maxLength : 90,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						fieldLabel : $i18n.cust_alias,// 客户简称
						id : 'cust_alias1501',
						maxLength : 90
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.status,// 状态
						id : 'status1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "N,DEF_STATUS"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}]
				}, {
					xtype : 'fieldset',
					layout : {
						type : 'table',
						columns : 3
					},
					defaults : {
						xtype : 'textfield',
						margin : '5 4 1 4',
						labelAlign : 'right',
						labelWidth : 120
					},
					items : [{
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.shipping_method,// 客户出货方式
						id : 'shipping_method1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,SHIPPING_METHOD"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.box_deliver,// 物流箱发货标记
						id : 'boxDeliver1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,BOX_DELIVER"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.container_material,// 容器材质
						id : 'container_material1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "N,CONTAINER_MATERIAL"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.prio_type,// 优先方式
						id : 'prioType1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,PRIO_TYPE"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'numberfield',
						fieldLabel : $i18n.prio_level,// 配量优先等级
						id : 'prioLevel1501',
						maxLength : 3,
						minValue : 0,
						colspan : 2,
						// editable:false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.collect_flag,// 是否采集材积
						id : 'collect_flag1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,COLLECT_FLAG"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.warn_flag,// 标签是否有特殊标记
						id : 'warn_flag1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,WARN_FLAG"
									}
								}),
						colspan : 2,
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.only_date_flag,// 要求单一到期日
						id : 'only_date_flag1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,ONLY_DATE_FLAG"
									}
								}),
						colspan : 3,
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : '出货生产日期控制类型',
						id : 'control_type1501',
						store : Ext.create("cms.store.common.comboStore").load(
								{
									params : {
										str : "BDEF_DEFCUST,CONTROL_TYPE"
									}
								}),
						editable : false,
						allowBlank : false,
						beforeLabelTextTpl : required
					}, {
						xtype : 'numberfield',
						fieldLabel : '出货生产日期控制值',
						id : 'control_value1501'
					}, {
						xtype : 'label',
						labelAlign : 'right',						 
						style : 'color:red;position:relative;left:20px;',
						text : '(注：控制类型按比例是，控制值输入整数，比如50%，只需输入50)'
					}, {
						xtype : 'numberfield',
						fieldLabel : $i18n.max_car_tonnage,// 允许车辆最大吨位
						id : 'max_car_tonnage1501'
					}, {
						fieldLabel : $i18n.receiving_hours,// 收货时段
						id : 'receiving_hours1501',
						colspan:2,
						maxLength : 20
					},{
			       	    fieldLabel:$i18n.cust_province,//省份
			    	    id:'custProvince1501',
			    	    maxLength:200
			        },{
			    	    fieldLabel:$i18n.cust_city,//城市
			    	    id:'custCity1501',
			    	    maxLength:200
			        },{
			    	    fieldLabel:$i18n.cust_zone,//区域
			    	    id:'cust_zone1501',
			    	    maxLength:200
			        }, {
						fieldLabel : $i18n.cust_address,// 客户地址
						id : 'cust_address1501',
						maxLength : 100,
						colspan:3,
						width:841
					}, {
						fieldLabel : $i18n.delivery_address,// 送货地址
						id : 'delivery_address1501',
						maxLength : 180,
						colspan:3,
						width:841
					}, {
						fieldLabel : $i18n.invoice_no,// 发票号
						id : 'invoice_no1501',
						maxLength : 10
					}, {
						fieldLabel : $i18n.invoice_header,// 发票抬头
						id : 'invoice_header1501',
						maxLength : 20,
						colspan:2,
						width:558
					}, {
						fieldLabel : $i18n.invoice_addr,// 发票地址
						id : 'invoice_addr1501',
						maxLength : 20,
						colspan:3,
						width:841
					}, {
						fieldLabel : $i18n.contact,// 联系人
						id : 'contact1501',
						maxLength : 50
					}, {
						fieldLabel : $i18n.cust_phone1,// 联系电话
						id : 'custPhone11501',
						maxLength : 50
					}, {
						fieldLabel : $i18n.cust_fax1,// 客户传真
						id : 'cust_fax11501',
						maxLength : 20
					}, {
						fieldLabel : $i18n.cust_email1,// 客户EMAIL
						id : 'cust_email1501',
						maxLength : 50
					}, {
						fieldLabel : $i18n.cust_postcode,// 客户邮编
						id : 'cust_postcode1501',
						maxLength : 10
					}, {
						fieldLabel : $i18n.memo,// 备注
						id : 'memo1501',
						maxLength : 225
					}]
				}]
			}, {
				region : 'south',
				xtype : 'commMenuWidget5',
				border : 0,
				id : 'bdef_MenuWidget1501'
			}]
		});
