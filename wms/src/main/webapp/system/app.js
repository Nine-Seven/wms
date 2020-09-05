/**
 * 系统全局变量
 */
var msgRes = Ext.create('system.util.msgRes');// 系统提示信息
var appConfig = Ext.create('system.util.appConfig');// app系统参数
var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';// 必录项前缀
var queryModuleId=999999;

Ext.Loader.setConfig({
	enabled : true
}); // 开启动态加载
Ext.Loader.setPath
(
	{
		'Ext.ux':'./system/extjs/resources/js/content/ux',
		'Ext.uiwidget':'./system/extjs/uiwidget'
	}
);
Ext.require([ 'Ext.grid.*', 
	'Ext.data.*', 
	'Ext.util.*',
	'Ext.selection.CellModel', 
	'Ext.state.*', 
	'Ext.form.*',
	'Ext.ux.CheckColumn',
	'Ext.ux.MonthField',
	'Ext.toolbar.Paging',
	'Ext.ux.PreviewPlugin',
	'Ext.ModelManager', 
	'Ext.tip.QuickTipManager',
	'Ext.uiwidget.form.sealink_form',				// 表单
	'Ext.uiwidget.form.field.sealink_checkboxfield',// 多选
	'Ext.uiwidget.form.field.sealink_combobox',		// 下拉
	'Ext.uiwidget.form.field.sealink_datefield',	// 日期
	'Ext.uiwidget.form.field.sealink_hiddenfield',	// 隐藏
	'Ext.uiwidget.form.field.sealink_numberfield',	// 数字
	'Ext.uiwidget.form.field.sealink_radiofield',	// 单选
	'Ext.uiwidget.form.field.sealink_textfield',	// 文本
	'Ext.uiwidget.button.sealink_button',			// 按钮
	'Ext.uiwidget.grid.sealink_grid',				// 表格
	'Ext.uiwidget.panel.sealink_panel',				// Panel
	'Ext.uiwidget.tab.sealink_tabpanel',			// 树
	'Ext.uiwidget.tree.sealink_treepanel',			// 标签页
	'Ext.uiwidget.window.sealink_window',			// window
	'Ext.uiwidget.date.DateTimePicker',			// 日期
	'Ext.uiwidget.date.DateTime'			// 日期
	
	
	]);
/**
 * 主程序
 */
Ext.application({
	name : 'cms', // 命名空间名称
	appFolder : 'system/app', // 主目录
	controllers : // 控制器
	[ 
	  	'AccordionMenu'
	],
	launch : function() {
		_myAppGlobal = this;
		var win;
		
		if (document.getElementById('workerName') == null || 
				document.getElementById('workerName').value == "") {
			return false;
		} else {
			var pageShow = new Ext.LoadMask(Ext.getBody(), {
				msg : "页面构建中,请稍后..."
			});
			pageShow.show();
			setTimeout(function() {
				pageShow.hide();
			}, 1000);
			Ext.create('cms.view.Viewport', {
				layout : 'fit',
				items : {
					xtype : 'cmsmenu'
				}
			});
			setWorkNameTips();
			Ext.Ajax.request({
				url:'loginAction_getBset_Worker_Loc.action',
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						// 设置切换仓别可见
						Ext.select(".top_div *{float=right} a:first").show(true);
						// 选择作业仓别
						commonSelectWareHouseNO();
						/*
						 * Ext.create('Ext.window.Window', { title: '请选择作业仓别',
						 * modal:true, closable:false, height: 100, width: 350,
						 * layout:'hbox', items: [ { xtype:'combo', fieldLabel :
						 * '作业仓别',//$i18n.sex, labelAlign:'right', labelWidth :
						 * 90, forceSelection : true, margin : '20 5 5 5',
						 * displayField: 'dropValue', valueField: 'value',
						 * store:Ext.create('Ext.data.Store', { fields:
						 * ['dropValue', 'text', 'value'], proxy: { type:
						 * 'ajax', url:
						 * 'bset_Worker_LocAction_getBset_Worker_LocCombo.action',
						 * reader: { type: 'json', root: 'rootList' } } }),
						 * beforeLabelTextTpl : required },{ xtype: 'button',
						 * margin : '20 5 5 5', text : '确定', handler: function() {
						 * if(this.previousSibling().getValue()==null){
						 * Ext.example.msg('提示','请选择作业仓别！'); }else{
						 * Ext.Ajax.request({ method:'POST',
						 * url:'loginAction_setSelectWarehouseNo.action',
						 * params:{ str:this.previousSibling().getValue() } });
						 * document.getElementById('warehouseNo').value=this.previousSibling().getValue();
						 * document.getElementById('warehouseName').value=this.previousSibling().getRawValue();
						 * document.getElementById('currentWarehouseNo').innerHTML=this.previousSibling().getValue();
						 * this.ownerCt.close(); } } }] }).show();
						 */
					}else{
						if(data.obj.length==1)
						{
							document.getElementById('warehouseNo').value=Ext.decode(response.responseText).obj[0].warehouseNo;
							document.getElementById('warehouseName').value=Ext.decode(response.responseText).obj[0].warehouseName;
							document.getElementById("currentWarehouseNo").innerHTML=Ext.decode(response.responseText).obj[0].warehouseNo;
						}
					}
				}
			});
		}
	 Ext.tip.QuickTipManager.init();
	 

	 Ext.Ajax.on('requestcomplete', checkUserSessionStatus, this);
		function checkUserSessionStatus(conn, response, options) 
		{
			var sessionStatus;
			try{
				sessionStatus = response
					.getResponseHeader("sessionstatus");
			}
			catch(error){
				return;
			}
			
			if (typeof (sessionStatus) != "undefined") {
				Ext.Msg.alert('提示', '会话超时，请重新登录!', function(btn, text) 
				{
					if (btn == 'ok') {
						var redirect = 'index.jsp';
						window.location = redirect;
					}
				});
			}
		};
	},

	localStorage : window.localStorage


});

