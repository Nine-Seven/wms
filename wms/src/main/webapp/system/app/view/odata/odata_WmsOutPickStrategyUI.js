/**
 * 模块名称：拣货策略配置
 * 模块编码：I803
 * 创建：MM
 */

var odata_WmsOutPickStrategyStore=Ext.create('cms.store.wms.wms_outPickStrategyStore',{autoLoad:true});
Ext.define('cms.view.odata.odata_WmsOutPickStrategyUI',{
	alias:'widget.odata_WmsOutPickStrategyUI',
	title: $i18n.titleI803,//"拣货策略配置",
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu5', 
	          'cms.view.common.wms_DefFieldValCombo'
	          ],	          
	          items:[
	         	   	{
	         	   	  xtype:'commMenuWidget2',
	             	    id:'menuI803',
	             	    region:'north'
	         		},{
	         			xtype:'grid',
		           	    id:'wmsOutPickStrategyI803',
		           	    region:'center',
		           	    store: odata_WmsOutPickStrategyStore,
	       				columns:[{			
		           	        xtype : 'rownumberer',
	            		    width : 30
	            		},{
		             	    width:80,
		           		    text : $i18n.pick_strategy_id,  //拣货策略ID
		           		    dataIndex:'pickStrategyId'			
		           	    },{
		           	    	width:150,
		           	    	text: $i18n.pick_strategy_name,  //拣货策略名称
		           	    	dataIndex:'pickStrategyName'
		           	    },{
		           		    width:150,
		           		    text : $i18n.pick_diff_flag,  //是否允许拣货差异
		           		    dataIndex:'pickDiffFlag'			
		           	    },{
		           		    width:150,
		           		    text : $i18n.pick_auto_flag,  //是否允许拣货自动回单
		           		    dataIndex:'pickAutoFlag'			
		           	    },{
		           		    width:150,
		           		    text : $i18n.auto_getdivide_flag,     //是否允许自动分播发单
		           		    dataIndex:'autoGetdivideFlag'			
		           	    },{
		           		    width : 150,
		           		    text : $i18n.auto_dividesave_flag,     //是否允许自动分播回单
		           		    dataIndex : 'autoDividesaveFlag'
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rsv1,     //
		           		    dataIndex : 'rsvValue1' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rsv2,     //
		           		    dataIndex : 'rsvValue2' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rsv3,     //
		           		    dataIndex : 'rsvValue3' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rsv4,     //
		           		    dataIndex : 'rsvValue4' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rsv5,     //
		           		    dataIndex : 'rsvValue5' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rgst_name,     //新增人
		           		    dataIndex : 'rgstName'
		           	    },{
		           		    width : 150,
		           		    text : $i18n.rgst_date,     //新增日期
		           		    dataIndex : 'rgstDate' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.updt_name,     //更新人
		           		    dataIndex : 'updtName' 
		           	    },{
		           		    width : 150,
		           		    text : $i18n.updtdate,     //更新时间
		           		    dataIndex : 'updtDate' 
		           	    }],
	       			dockedItems : [{
	       				xtype : 'pagingtoolbar',
           			    store : odata_WmsOutPickStrategyStore,
           			    dock : 'bottom',
           			    displayInfo : true
	       			}] 
	       	   }]
	         });