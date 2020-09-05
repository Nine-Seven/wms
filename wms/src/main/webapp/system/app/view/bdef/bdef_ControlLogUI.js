/**
 * 模块名称：监控日志查询bdef_ControlLogUI.js
 * 模块编码：I401
 * 创建：HKL 
 */

var interfaceLogList=Ext.create('cms.store.bdef.bdef_ControlLogStore',{autoLoad:true});
var interfaceLogSumList=Ext.create('cms.store.bdef.bdef_ControlLogStore',{
	 proxy: {
	        type: 'ajax',
	        method: 'post',
	        url: 'bdef_ControlLogAction_getInterfaceLogSumList',
	    	reader : {
	    		root : 'rootList',
	    		totalProperty : 'totalCount'
	    	}
      },
	autoLoad:true
});


Ext.define('cms.view.bdef.bdef_ControlLogUI', {
	alias : 'widget.bdef_ControlLogUI',
	id:'bdef_ControlLogUI',
	title:'接口监控日志', 
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
	            'cms.view.common.commMenu10'
	         
				],
	items : [{
		xtype:'commMenuWidget10',
	    id:'menuI401',
	    region:'north'
	},
	{
		xtype : 'tabpanel',
	    id:'tabPIdI401',
	    region:'center',
	    items : [
	    {
	    	title:'接口异常日志',
	    	layout:'border',
	    	items:[{
				xtype : 'grid',
				region:'center',
				id : 'grid_01_I401',
				width : '100%',
				height : '100%',
				store :interfaceLogList,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 120,
					text : '接入机构类型',//委托业主编号
					dataIndex : 'platType' 
				},{
					width : 120,
					text : '接入机构编号',//委托业主名称
					dataIndex : 'platNo'
				},{
					width:60,
					text:'企业号',//单据类型
					dataIndex:'enterpriseNo'
				},{
					width : 60,
					text : '仓库编号',//采购单号
					dataIndex : 'warehourceNo'
				},{
					width : 135,
					text : 'API编号',//手建单号
					dataIndex : 'apiNo'
				},{
					width : 120,
					text : 'API名称',//供应商编号
					dataIndex : 'apiDesc' 
				},{
					width : 120,
					text : '执行时间',//供应商名称
					dataIndex : 'execTime'
				},{
					width : 120,
					text : '错误日志描述',//预采购发单日期
					dataIndex : 'execDesc'
				},{
					width : 90,
					text : '错误序号',//预定到货日
					dataIndex : 'seqNo'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : interfaceLogList,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    },{
	    	title:'接口汇总信息监控',
	    	layout:'border',
	    	id:'tabPIdd2_I401',
	    	itemId:'tabPIdd2_I401i',
	    	items:[{
				xtype : 'grid',
				region:'center',
				id : 'grid_02_I401',
				width : '100%',
				height : '100%',
				store :interfaceLogSumList,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 120,
					text : '日期',//委托业主编号
					dataIndex : 'operateDate' 
				},{
					width : 120,
					text : '接入机构类型',//委托业主名称
					dataIndex : 'platType'
				},{
					width:100,
					text:'接入机构编号',//单据类型
					dataIndex:'platNo'
				},{
					width : 60,
					text : '企业号',//采购单号
					dataIndex : 'enterpriseNo'
				},{
					width : 60,
					text : '仓库编号',//手建单号
					dataIndex : 'warehourceNo'
				},{
					width : 130,
					text : 'API编号',//供应商编号
					dataIndex : 'apiNo' 
				},{
					width : 130,
					text : 'API名称',//供应商名称
					dataIndex : 'apiDesc'
				},{
					width : 60,
					text : '执行次数',//预采购发单日期
					dataIndex : 'execTimeQty'
				},{
					width : 60,
					text : '成功次数',//预定到货日
					dataIndex : 'yesTimeQty'
				},{
					width : 60,
					text : '错误次数',//状态	
					dataIndex : 'noTimeQty'
				},{
					width : 125,
					text : '上次执行时间',//汇总进货单号
					dataIndex : 'lastTime'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : interfaceLogSumList,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    }]
	}]
});