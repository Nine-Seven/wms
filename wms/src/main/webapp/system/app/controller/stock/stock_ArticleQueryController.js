/**
 * 模块名称：报表控制器
 * 创建：czh
 * 2016.6.12
 */
Ext.define('cms.controller.stock.stock_ArticleQueryController',{
	extend:'Ext.app.Controller',
	requires:[
			 'cms.view.report.window.reportCheckSetWindow',
			 'cms.view.report.window.renameWindow',
			 'cms.view.stock.stock_ArticleQueryUI'
			 ],
	init:function(){
		this.control({
			//查找
			'stock_ArticleQueryUI button[name=query]':{
				click:this.queryClick
			},
			//刷新
			'stock_ArticleQueryUI button[name=refresh]':{
				click:this.refresh
			},
			//导出
			'stock_ArticleQueryUI button[name=export]':{
				click:this.exportClick
			},			
			//参考项目选择
			'stock_ArticleQueryUI combo[id=cmbProjectSelectK201]':{
				change:this.cmbProjectSelectK201Select
			},
			//查询按扭
			'stock_ArticleQueryUI button[id=btnSearchK201]':{
				click:this.btnSearchK201Click
			},
			//项目设定按扭
			'stock_ArticleQueryUI button[id=btnProjectSetK201]':{
				click:this.btnProjectSetK201Click
			}
		});
	},
	
	//查找
	queryClick:function(){
		if(Ext.isEmpty(Ext.getCmp('cmbProjectSelectK201').getValue())){
			Ext.example.msg($i18n.prompt,'请先选择参考项目');
			return;
		}
		Ext.create('cms.view.common.queryWindow',{
			
		}).show();
		var getQueryWindow = Ext.getCmp('queryWindow');		
		/*var mess = Ext.decode(localStorage.getItem("commonLocal"));
		if(mess!=null){
			for ( var i = 0; i <mess.length; i++) {
				if(mess[i].value==" is null" || mess[i].value==" is not null"){
					mess[i].value = null;
					mess[i].condition = "1";
				}
				var getQueryPanel = Ext.create('cms.view.common.queryPanel');
				getQueryPanel.items.items[1].setValue(mess[i].logic);
				getQueryPanel.items.items[2].setValue(mess[i].columnId);
				getQueryPanel.items.items[3].setValue(mess[i].condition);
				getQueryPanel.items.items[4].setValue(mess[i].value);
				getQueryPanel.items.items[0].hide();
				getQueryWindow.add(getQueryPanel);
			}
			localStorage.removeItem("commonLocal");	
		}*/				
		queryModuleId='K201';
		queryGrid='gridReportColumnK201';
		Ext.Ajax.request({
			url:'wms_DefModuleQueryColumnmAction_getModuleQueryColumn',
			method:'get',
			params:
			{
				moduleId:queryModuleId
			},
			success:function(response)
			{
				var data=Ext.decode(response.responseText);
				for(var i=0;i<data.length;i++){
					var getQueryPanel = Ext.create('cms.view.common.queryPanel');
					getQueryPanel.items.items[2].getStore().add({
						columnid:data[i].columnid,
						columnname:data[i].columnname
				    });
					getQueryPanel.items.items[2].setValue(data[i].columnid);
					getQueryPanel.items.items[4].setValue('');
					getQueryPanel.items.items[2].setReadOnly(true);
					getQueryPanel.items.items[0].hide();
					Ext.getCmp('buttonQueryAdd').hide();
					getQueryWindow.add(getQueryPanel);
				}
			}
		});
		Ext.getCmp('gridReportColumnK201').getStore().proxy.extraParams.moduleId = 'K201';
		
		if(Ext.getCmp('cmbProjectSelectK201').getValue()!="" && Ext.getCmp('cmbProjectSelectK201').getValue()!=null){
			Ext.getCmp('gridReportColumnK201').getStore().proxy.extraParams.strName = encodeURIComponent(Ext.getCmp('cmbProjectSelectK101').getValue());
		}
	},
	//刷新
	refresh:function(){
		Ext.getCmp('gridReportColumnK201').getStore().reload();
	},
	exportClick:function()
	{
		commExport('gridReportColumnK201');
	},	
	
	
	//参考项目选择事件
	cmbProjectSelectK201Select:function(combo){
		this.commonReportTitleK201("K201",combo.getValue());
	},
	
	//查询
	btnSearchK201Click:function(){
		var strName;
		if(Ext.getCmp('cmbProjectSelectK201').getValue()!=null){
			strName =encodeURIComponent(Ext.getCmp('cmbProjectSelectK201').getValue());
		}else{
			strName =Ext.getCmp('cmbProjectSelectK201').getValue();
		}
		var params = {
			moduleId:'K201',
			strName:strName
		};
		Ext.apply(Ext.getCmp('gridReportColumnK201').getStore().proxy.extraParams,params);
		Ext.getCmp('gridReportColumnK201').getStore().removeAll();
		Ext.getCmp('gridReportColumnK201').getStore().load();
	},
	
	//项目设定
	btnProjectSetK201Click:function(){
		Ext.create('cms.view.report.window.reportCheckSetWindow',{
			title:$i18n.checkSet//高阶查询设置
		}).show();
		Ext.getCmp('moduleId').setValue('K201');
		var cmbReportSelectId = 'K201';
		var cmbProjectSelectId = Ext.getCmp('cmbProjectSelectK201').getValue();
		var params = {
			strReferenceItem:cmbProjectSelectId
		};
		if(!Ext.isEmpty(cmbProjectSelectId))
		{
			Ext.getCmp('cmbProjectSelect_w').setValue(cmbProjectSelectId);
		}
		cmbProjectSelect_wSelect(cmbReportSelectId,cmbProjectSelectId);
		Ext.getCmp('textReport_W').setValue(cmbReportSelectId);
	},
	commonReportTitleK201:function(strReportId,strProjectId)
	{
		Ext.Ajax.request({
			url:'report_Action_getGridColumModle.action',
			params : {
				strReportId:strReportId,
				strProjectId:strProjectId
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
							url: 'report_Action_getGridData.action',
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
			            id : 'gridReportColumnK201',
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
	        		Ext.getCmp('panelReportColumnK201').removeAll();
					Ext.getCmp('panelReportColumnK201').add(grid);  
					Ext.getCmp('panelReportColumnK201').doLayout();
				}else{
					Ext.getCmp('panelReportColumnK201').removeAll();
				}
			}
		});
	}
	
});



function cmbProjectSelect_wSelect(cmbReportSelectId,cmbProjectSelectId){
	var params = {
		strReportId:cmbReportSelectId,
		strProjectId:cmbProjectSelectId
	};
	Ext.apply(Ext.getCmp('searchDSetGrid').getStore().proxy.extraParams,params);
	Ext.getCmp('searchDSetGrid').getStore().removeAll(); 
	Ext.getCmp('searchDSetGrid').getStore().load();
}

//检验参考项目名称是否已经存在
function checkProjectName(strPgmId,strName)
{
	var checkFlag=false;
	var strParams = {
		strPgmId:strPgmId,
		strName:strName
	};
	 Ext.Ajax.request({
		  method:'POST',
		  url:'report_Action_checkProjectName.action',
		  params:strParams,
		  async:false,
		  success:function(response){
			  var data = Ext.decode(response.responseText);
			  if(data.isSucc){
				  checkFlag=true;
			  }else{
				  Ext.example.msg($i18n_prompt.prompt,data.msg);
				  checkFlag=false;
			  }				
		  }
	  });	
	  return checkFlag;
};
