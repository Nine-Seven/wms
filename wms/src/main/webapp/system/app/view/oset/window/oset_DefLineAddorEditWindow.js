/**
 * 模块名称：客户与线路维护
 * 模块编码：1J01
 * 创建：Jun
 */
Ext.define('cms.view.oset.window.oset_DefLineAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.oset_DefLineAddorEditWindow',
	layout : 'border',
	width : 450,
	height : 285,
	modal : true,
	id : 'oset_DefLineAddorEditWindow',
	items : [
			{
			   xtype:'form',
	    	   region:'center',
	    	   id:'form_01_1J01',
	    	   frame:true,
	    	   layout:'form',
	       	   defaults:{
	    	    xtype:'textfield',
	    	    margin:'4 4 1 4',
	    	    labelAlign:'right',
	    	    labelWidth:90
	    	   },
	       	   items:[
	       	   {
	       	   	fieldLabel:$i18n.warehouse,//仓别
	       	   	id:'warehouse1J01',
	       	   	allowBlank : false,
	       	   	maxLength:5,
	       	   	readOnly:true,
	    	    beforeLabelTextTpl:required
	       	   },
	       	   {
	       	   	xtype:'wms_DefFieldValCombo',
	       	   	fieldLabel:$i18n.deliver_type,//配送方式
	       	   	id:'deliver_type1J01',
	       	   	store:Ext.create("cms.store.common.comboStore").load(
	    	    {
	    	       	 params:{str:"N,DELIVER_TYPE"}
	    	    }),
	    	    editable:false,
	    	    allowBlank:false,
	    	    beforeLabelTextTpl:required
	       	   },
	       	   {
	       	   	xtype:'wms_DefFieldValCombo',
	       	   	fieldLabel:$i18n.transportation,//运输方式
	       	   	id:'transportation1J01',
	       	   	store:Ext.create("cms.store.common.comboStore").load(
	    	    {
	    	       	 params:{str:"N,TRANSPORT_TYPE"}
	    	    }),
	    	    editable:false,
	    	    allowBlank:false,
	    	    beforeLabelTextTpl:required
	       	   },
	       	   {
	       	   	fieldLabel:$i18n.line_no,//线路代码
	       	   	id:'line_no1J01',
	       	   	allowBlank : false,
	       	   	maxLength:4,
	    	    beforeLabelTextTpl:required
	       	   },
	       	   {
	       	   	fieldLabel:'线路名称',//线路名称
	       	   	id:'line_name1J01'
	       	   },
	       	   {
	       	   	fieldLabel:$i18n.line_full_name,//线路全称
	       	   	id:'line_full_name1J01',
	       	   	readOnly:true
	       	   },
	       	   {
	       	   	xtype:'textareafield',
	       	   	fieldLabel:$i18n.line_remark,//线路备注
	       	   	id:'line_remark1J01'
	       	   }
	       	   ]
			},
			{
				region:'south',
				xtype:'commMenuWidget5',
				border:0,
				id:'oset_DefLine1J01'
			}
			]
});