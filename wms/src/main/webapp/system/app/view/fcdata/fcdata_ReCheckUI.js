/**
 * 模块名称：盘点复盘、三盘发单
 * 模块编码：8301
 * 创建：周欢
 */
//var checkDStore=Ext.create('cms.store.fcdata.fcdata_CheckDStore');
var fcdata_CheckMStore = Ext.create('cms.store.fcdata.fcdata_CheckMStore',
{    autoLoad:false,
	 listeners:{  
			'load':function(th,records,successful,eOpts ){
				if(th.count()>0){
					Ext.getCmp('gridCheckM8301').getSelectionModel().select(0);
				}
			}
		}
});

var checkboxModel = Ext.create('Ext.selection.CheckboxModel', {
	id : 'checkboxModel',
	mode : 'SIMPLE',
	listeners : {
		'select' : function(obj, record, index) 
		{
		},
		'deselect' : function(obj, record, index) 
		{
			
		},
		'beforedeselect':function(obj, record, index)
		{
			if(g_map8201.get(index) != undefined)
			{
				return false;
			}
		}
	}
});

Ext.define('cms.view.fcdata.fcdata_ReCheckUI',{
	alias:'widget.fcdata_ReCheckUI',
	title:$i18n.title8202,//盘点发单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu3',
	          'cms.view.common.commMenu4',
	          'cms.view.common.commMenu6',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[{
			 xtype:'commMenuWidget4',
			 id:'menu8301',
			 region:'north'
		 },
       	 
   {
	 xtype : 'form',
	 id:'formReCheck8301',
	 region : 'north',
	 layout : {
		 type : 'table',
		 columns : 3
	 },
	 defaults : {
		labelWidth : 90,
		margin : '2 2 2 2',
		labelAlign : 'right'
	 },
	 frame : true,
	 items : [
        {
		xtype : 'combo',
		fieldLabel : $i18n.qty_time,// 盘点次数
		queryMode : 'local',
		displayField: 'dropValue',
		valueField: 'value',
		id:'cmbCheck_type8301',
		store:Ext.create("cms.store.common.comboStore",{
			listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(th.count()>0){
						Ext.getCmp('cmbCheck_type8301').setValue(th.getAt(0).data.value);
					}else{
						Ext.getCmp('cmbCheck_type8301').setValue("");
					}
				}
			}
		}).load(
				{
					params:{str:"FCDATA_CHECK_M,CHECK_TYPE"}
					
				}),
		width : 300,
		beforeLabelTextTpl : required
		},{
		 xtype : 'combo',
		 fieldLabel : $i18n.different_remark,// 差异标识
		 id:'cmbDifferent_flag8301',
		 queryMode : 'local',
		 displayField: 'dropValue',
		 valueField: 'value',
		 xtype:'wms_DefFieldValCombo',
		 store:Ext.create("cms.store.common.comboStore",{
			 listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('cmbDifferent_flag8301').setValue(th.getAt(1).data.value);
						}else{
							Ext.getCmp('cmbDifferent_flag8301').setValue("");
						}
					}
				}
		 }).load(
			{
				params:{str:"N,DIFFERENT_FLAG"}
			}),
		width : 300,
		beforeLabelTextTpl : required
	 },{
			xtype : 'radiogroup',
			id : 'radioPrint_type8301',
			fieldLabel : $i18n.print_type,//"打印类型",
			columns : 3,
			width : 320,
			items : [{
				boxLabel : $i18n.print_report,//打印报表
				name : 'pt',
				inputValue : '1',
				checked:true
				},
				{
				boxLabel : $i18n.print_label,//打印标签
				name : 'pt',
				inputValue : '2'
				},
				{
					boxLabel : '不打印',//不打印
					name : 'pt',
					inputValue : '0'
				}
			],
			beforeLabelTextTpl : required
		}]
	 },
	 {
		xtype : 'form',
		region : 'north',
		layout : {
		type : 'table',
		columns : 3
		},
		frame : true,
		items : [ {
		layout : 'column',
		xtype : 'container',
		defaults : {
		labelWidth : 90,
		margin : '2 2 2 2',
		labelAlign : 'right',
		width : 300
		},
		items : [
		{
			xtype : 'bdef_DefWorkerCombo',
			fieldLabel : $i18n.yworker_name,// 员工姓名
			id:'cmbWorkerNo8301',
			labelAlign : 'right',
			store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
			beforeLabelTextTpl : required
		}]
		}, 
		{
			xtype : 'button',
			margin:'0 0 0 25',
			id:'ButReSend8301',
			text : $i18n.releases //发单
		}, 
		{
			xtype : 'button',
			margin:'0 0 0 25',
			id:'butEndlooprecheck_8301',
			text : $i18n.endlooprecheck //结束盘点
		}]
		},
		{//盘点发单》作业预览》头档
			xtype : 'grid',
			region : 'center',
			width : '100%',
			id:'gridCheckM8301',
			store:fcdata_CheckMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			},
			{
				width : 140,
				text : $i18n.chcheck_no,//盘点单号
				dataIndex:'checkNo'
			}]
		},
		{//盘点发单》作业预览》明细档
		  xtype : 'grid',
		  region : 'south',
		  width : '100%',
		  height:200,
		  id:'gridCheckD8301',
		  store:Ext.create('cms.store.fcdata.fcdata_CheckDStore'),
		  selModel : checkboxModel,
		  columns : [
		  {
			width : 100,
			text : $i18n.ware_no,//库区
			dataIndex:'wareNo'
		  }, 
		  {
			width : 100,
			text : $i18n.area_no,//储区
			dataIndex:'areaNo'
		  }, 
		  {
			width : 120,
			text : $i18n.stock_no,//通道
			dataIndex:'stockNo'
		  }, 
		  {
			width : 140,
			text : $i18n.cell_no,//储位
			dataIndex:'cellNo'
		  },
		  {
			width : 140,
			text : $i18n.different_remark,//是否有差异
			dataIndex:'differentFlagText'
		  }]
	}]
});