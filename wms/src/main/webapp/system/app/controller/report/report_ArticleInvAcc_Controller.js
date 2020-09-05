/**
 * 模块名称：商品库存账查询
 * 创建：lich
 */
var saveFlag9111=0;
Ext.define('cms.controller.report.report_ArticleInvAcc_Controller',{
	extend:'Ext.app.Controller',
	requires:[
			 'cms.view.report.report_ArticleInvAcc_UI'
			 ],
	init:function(){
		this.control({
			//查找
			'report_ArticleInvAcc_UI button[name=query]':{
				click:this.queryClick
			},
			//刷新
			'report_ArticleInvAcc_UI button[name=refresh]':{
				click:this.refresh
			},
			//导出
			'report_ArticleInvAcc_UI button[name=export]':{
				click:this.detailExport
			},
			//开始日期选择
			'report_ArticleInvAcc_UI datefield[id=date9111]':{
				select:this.date9111Blur,
				blur:this.date9111Blur
			},
			//结束日期选择
			'report_ArticleInvAcc_UI datefield[id=date9112]':{
				select:this.date9111Blur,
				blur:this.date9111Blur
			},			
			//查询按扭
			'report_ArticleInvAcc_UI button[id=btnSearch9111]':{
				click:this.btnSearch9111Click
			}		
		});
	},
 	/**
	 * 初始化界面
	 */
	initializtion:function(){
		
		Ext.getCmp('date9111').setValue(Ext.Date.getFirstDateOfMonth(new Date()));
		Ext.getCmp('date9112').setValue(Ext.Date.add(new Date(), Ext.Date.DAY, -1));
		this.date9111Blur();
	},
	//查找
	queryClick:function(){
		if(Ext.isEmpty(Ext.getCmp('date9111').getValue())){
			Ext.example.msg($i18n.prompt,'请先选择日期');
			return;
		}
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=9111;
		queryGrid='gridReportColumn9111';	
	},
	//刷新
	refresh:function(){
		Ext.getCmp('gridReportColumn9111').getStore().removeAll();
		Ext.getCmp('gridReportColumn9111').getStore().reload();
	},	
	detailExport:function(){
		commExport('gridReportColumn9111');
	},	
	//报表选择事件
	date9111Blur:function(combo){
		Ext.Ajax.request({
			url:'report_Action_getArticleInvAccGridColumModle.action',
			params : {
				beginYear:Ext.getCmp('date9111').getValue().getFullYear(),
				beginMonth:Ext.getCmp('date9111').getValue().getMonth()+1,
				beginDay:Ext.getCmp('date9111').getValue().getDate(),
				endYear:Ext.getCmp('date9112').getValue().getFullYear(),
				endMonth:Ext.getCmp('date9112').getValue().getMonth()+1	,
				endDay:Ext.getCmp('date9112').getValue().getDate()		
			},
			success : function(response, options) {  
				var data = Ext.JSON.decode(response.responseText);//将字符串转换为json类型
				if(data.isSucc){
					var json=Ext.JSON.decode("{"+data.obj+"}");
					var cm = new Ext.grid.column.Column(json.columModle);    
		     		var ds = new Ext.data.JsonStore({
		     			pageSize : appConfig.getPageSize(),
						proxy: {
						    type: 'ajax',
							method: 'post',
							url: 'report_Action_getArticleInvAccData.action',
							timeout	:300000,
							reader : {
								type:'json',
								root : 'rootList',
								totalProperty : 'totalCount'
							}
						},
		    			fields:json.fieldsNames
		     		});
		                                       
					var grid = Ext.create('Ext.grid.Panel',{
			            split: true,
			            border:false,
			            id : 'gridReportColumn9111',
			            columnLines : true,
			            columns:json.columModle,
			            store:ds,
						dockedItems : [{
							xtype : 'pagingtoolbar',
							store : ds,
							dock : 'bottom',
							displayInfo : true
						}]
	        		});
	        		Ext.getCmp('panelReportColumn9111').removeAll();
					Ext.getCmp('panelReportColumn9111').add(grid);  
					Ext.getCmp('panelReportColumn9111').doLayout();
				}else{
					Ext.getCmp('panelReportColumn9111').removeAll();
				}
			}
		});
	},	
	//查询
	btnSearch9111Click:function(){
	
		if((Ext.getCmp('date9112').getValue().getTime()-Ext.getCmp('date9111').getValue().getTime())/1000/60/60/24>60){
			Ext.example.msg($i18n.prompt,'查询日期不能超过2个月');
			return;
		}
		var params = {
			beginYear:Ext.getCmp('date9111').getValue().getFullYear(),
			beginMonth:Ext.getCmp('date9111').getValue().getMonth()+1,
			beginDay:Ext.getCmp('date9111').getValue().getDate(),
			endYear:Ext.getCmp('date9112').getValue().getFullYear(),
			endMonth:Ext.getCmp('date9112').getValue().getMonth()+1	,
			endDay:Ext.getCmp('date9112').getValue().getDate(),		
			
			beginDate:Ext.Date.format(Ext.getCmp('date9111').getValue(),'Y-m-d'),
			endDate:Ext.Date.format(Ext.getCmp('date9112').getValue(),'Y-m-d')
		};
		Ext.apply(Ext.getCmp('gridReportColumn9111').getStore().proxy.extraParams,params);
		Ext.getCmp('gridReportColumn9111').getStore().removeAll();
		Ext.getCmp('gridReportColumn9111').getStore().load();
	}		
});