Ext.define('cms.view.bdef.window.bdef_ArticleFamilyWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.bdef_ArticleFamilyWindow',
	id : 'bdef_ArticleFamilyWindow',
	width : 500,
	height : 160,
	modal:true,
	layout:'border',
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_01_1R01',
		frame : true,
		layout:'form',
		defaults : {
			xtype : 'textfield',
			margin : '5 5 5 5',
			labelAlign:'right',
			allowBlank: true,
			labelWidth : 120
		},
		items:[
		{
			xtype:'bdef_DefOwnerCombo',
			fieldLabel : $i18n.owner_no,//货主编号
			id:'owner1R01',
			displayField : 'dropValue',
		    valueField : 'value',
	        allowBlank : false,
	        editable:false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				proxy:{
					type:'ajax',
					method:'post',
					url:'bdef_DefOwnerAction_queryOwnerCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			}).load(),
	        readOnly:true,
	        allowBlank : false,
	        beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.articleFamilyNo,//商品群组代码
			id : 'article_family_no1R01',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.articleFamilyName,//商品群组名称
			id : 'article_family_name1R01',
			allowBlank: false,
			maxLength:200,
			beforeLabelTextTpl : required
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget51R01'
	}]
});