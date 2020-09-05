/**
 * 模块名称：
 * zhouhuan
 */
Ext.define('cms.view.report.window.reportCheckSetWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.reportCheckSetWindow',
	requires : [
		'cms.view.common.wms_DefFieldValCombo'
	],
	layout:'border',
	id:'reportCheckSetWindow',
    height:370,
	width:820,
	modal:true,
    items: [{
		xtype : 'form',
		region : 'north',
		width:'100%',
		frame : true,
		items : [ 
		{
			layout : {
				type : 'table',
				columns : 2
			},
			xtype : 'container',
			defaults : {
				xtype : 'textfield',
				margin : '2 2 2 0',
				labelAlign : 'right'
			},
			items : [
			{
				xtype : 'combo',
				fieldLabel : '参考项目',
				displayField: 'dropValue',
				valueField: 'value',
				mode : 'remote', // 远程访问
				queryMode : 'remote',// [remote]非本地
				id : 'cmbProjectSelect_w',
			    store:Ext.create("cms.store.common.comboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url : 'report_Action_getReferenceItemList.action',
				        reader: {
				    		type:'json',
				            root: 'rootList',    
				            totalProperty: 'totalCount'
				        }
				    },
					listeners:{
						 'load':function(th,records,successful,eOpts ){
								if(th.count()>0 &&
										(Ext.getCmp('cmbProjectSelect_w').getValue()==null ||Ext.getCmp('cmbProjectSelect_w').getValue()=="")){
									Ext.getCmp('cmbProjectSelect_w').setValue(th.getAt(0).data.value);
									var cmbReportSelectId = Ext.getCmp('textReport_W').getValue();
									var cmbProjectSelectId = th.getAt(0).data.value;
									Ext.getCmp('cmbProjectSelect_w').setValue(th.getAt(0).data.value);
									cmbProjectSelect_wSelect(cmbReportSelectId,cmbProjectSelectId);
								}else{
									cmbProjectSelect_wSelect(Ext.getCmp('textReport_W').getValue(),Ext.getCmp('cmbProjectSelect_w').getValue());
								}
						}
					}
				}),
				listeners:{
					'focus':function(){
			    		var textReport_W = Ext.getCmp('textReport_W').getValue();
						var params = {
							strReferenceItem:textReport_W
						};
						Ext.apply(Ext.getCmp('cmbProjectSelect_w').getStore().proxy.extraParams,params);
						Ext.getCmp('cmbProjectSelect_w').getStore().removeAll();
						Ext.getCmp('cmbProjectSelect_w').getStore().load();
			    	},
			    	'select':function(combo){
			    		var cmbReportSelectId = Ext.getCmp('textReport_W').getValue();
						var cmbProjectSelectId = combo.getValue();
						cmbProjectSelect_wSelect(cmbReportSelectId,cmbProjectSelectId);
			    	}
				}
			},{
				xtype:'button',
				 text : '删除',
				 iconCls : 'delete',
				 id : 'delete',
				 listeners:{
				 	click:function(){
				    	Ext.Msg.confirm($i18n_prompt.prompt, "确定删除？", function(button, text) 
						{
						  if (button == 'yes') 
						  {
							  var strParams = {
									strOldName:Ext.getCmp('cmbProjectSelect_w').getValue(),
									strPgmId:Ext.getCmp('textReport_W').getValue()
							  };
							  Ext.Ajax.request({
								method:'POST',
								url:'report_Action_deleteProject.action',
								params:strParams,
								success:function(response){
									var data = Ext.decode(response.responseText);
									if(data.isSucc){
										Ext.example.msg($i18n_prompt.prompt,data.msg);
										Ext.getCmp('cmbProjectSelect_w').setValue("");
									//	Ext.getCmp('cmbProjectSelect_w').getStore().removeAll();
										Ext.getCmp('cmbProjectSelect_w').getStore().load();
										
									}else{
										Ext.example.msg($i18n_prompt.prompt,data.msg);
										Ext.getCmp('searchDSetGrid').getStore().removeAll();
										Ext.getCmp('searchDSetGrid').getStore().reload();
									}			
								}
							});	
						  }
						});
				 	}
				 }
			 
			},{
				xtype:'textfield',
				fieldLabel : '报表id',
				margin : '5 5 5 5',
				labelAlign:'right',
				hidden:true,
				id:'textReport_W'
			},{
				xtype:'textfield',
				hidden:true,
				id:'moduleId'
			}]
		}]
	},{
		  region:'center',
    	  xtype:'grid',
    	  id:'searchDSetGrid',
    	  selModel : {
			selType : 'cellmodel'
		  },
		  plugins : [
			  Ext.create('Ext.grid.plugin.CellEditing', {
				  clicksToEdit : 1,
				  onSpecialKey:function(ed,field,e){
					  commEnterGridStatEdit(this.grid,false,'',e.getKey());
				  }
			  })
		  ],
    	  store:Ext.create('cms.store.wms.wms_DefsearchDSetStore'),
    	  width:'100%',
    	  height:'100%',
    	  columns:[
    	  {
   	          xtype:'rownumberer',
   	          width:30 
          },{
    	      width:140,
    	      text:$i18n.fieldName,//显示字段
    	      dataIndex:'fieldName'
          },{
       	      xtype : 'checkcolumn',
    	      text:$i18n.showFlag,//可见性
              dataIndex: 'showFlag',
              width: 60
          },{
    	      width:100,
    	      text:$i18n.statisticsFlag,//统计公示
    	      dataIndex:'statisticsFormulae',
    	      field : {							
        		  xtype:'wms_DefFieldValCombo',
        		  forceSelection : false,
			      store:Ext.create("cms.store.common.comboStore").load(
	       	      {
	       	           params:{str:"WMS_DEFSEARCH_D_SET,STATISTICS_FORMULAE"}
	       	      }),
	       	  	  listeners:{
				 	beforeselect:function(combo,record,index,eOpts){
				 		debugger;
					 	if(record.data.text=='求和' && this.ownerCt.floatParent.getSelectionModel().getSelection()[0].data.fieldType.toUpperCase()!='NUMBER'){
					 		Ext.example.msg('提示',"该字段不能求和！");
					 		return false;
					 	}
					 }
				  }
	          },
	          renderer:function( value)
	       	  {
	       	    	if(value==1)
	       	    	{
	       	    		return "计数";
	       	    	}else if(value==2)
	       	    	{
	       	    		return "求和";
	       	    	}else if(value==3)
	       	    	{
	       	    		return "分组";
	       	    	}else if(value==4)
	       	    	{
	       	    		return "最大";
	       	    	}else if(value==5)
	       	    	{
	       	    		return "最小";
	       	    	}else if(value==6)
	       	    	{
	       	    		return "非重复计数";
	       	    	}
	       	  }
          },{
    	      width:80,
    	      text:$i18n.orderType,//排序类型
    	      field : {							
        		  xtype:'wms_DefFieldValCombo',
        		  forceSelection : false,
			      store:Ext.create("cms.store.common.comboStore").load(
	       	      {
	       	           params:{str:"WMS_DEFSEARCH_D_SET,ORDER_TYPE"}
	       	      })
	          },
	          renderer:function( value)
	       	  {
	       	    	if(value==1)
	       	    	{
	       	    		return "升序";
	       	    	}else if(value==2)
	       	    	{
	       	    		return "降序";
	       	    	}
	       	  },
    	      dataIndex:'orderType'
         },{		
			  text : $i18n.showWidth,//显示宽度
			  field:{
				  xtype: 'numberfield',
				  minValue:0
			  },
			  dataIndex : 'width'
   		  },{		
			  hidden:true,
			  dataIndex : 'fieldType'
   		  }]
     },{
	     region:'south',
		 xtype:'toolbar',
		 border:0,
		 items: ['->',
	 	 {
			 text : '重命名',
			 iconCls : 'save',
			 id : 'rename',
			 listeners:{
			 	click:function(){
				 	Ext.create('cms.view.report.window.renameWindow',{
						title:$i18n.rename//重命名
					}).show();
					Ext.getCmp('tModuleId').setValue('0');
				 }
			 }
			 
		 },{
			 text : '另存为',
			 iconCls : 'save',
			 id : 'saveother',
			 listeners:{
			 	click:function(){
				 	Ext.create('cms.view.report.window.renameWindow',{
						title:$i18n.othersave//另存为
					}).show();
					Ext.getCmp('tModuleId').setValue('1');
				 }
			 }
		 },{
			 text : '保存',
			 iconCls : 'save',
			 id : 'save',
			 listeners:{
			 	click:function(){
			 		if(Ext.isEmpty(Ext.getCmp('cmbProjectSelect_w').getValue())){
			    		Ext.example.msg($i18n_prompt.prompt,'请选择另存为');
			    		return;
			    	}
			    	var store=Ext.getCmp('searchDSetGrid').getStore().query('showFlag',true);
					var strItemName = Ext.getCmp('cmbProjectSelect_w').getValue();
			    	Ext.Msg.confirm($i18n_prompt.prompt, "确定保存？", function(button, text) 
					{
					  if (button == 'yes') 
					  {
					      var strDetail = [];
						  for(var i=0;i<store.length;i++)
						  {
							 var statisticsFlag = '';
							  if(store.items[i].get('statisticsFlagText')==true)
							  {
							  	statisticsFlag = '1';
							  }else
							  {
							  	statisticsFlag = '0';
							  }
							  var strD=
							  {
								  id:{
									  pgmId:store.items[i].get('pgmId'),
									  name:strItemName,
									  fieldId:store.items[i].get('fieldId'),
									  rgstName:Ext.get('workerNo').getValue()
								  },
								  seq:store.items[i].get('seq'),
								  orderType:store.items[i].get('orderType'),
								  statisticsFlag:statisticsFlag,
								  width:store.items[i].get('width'),
								  statisticsFormulae:store.items[i].get('statisticsFormulae'),
								  rgstDate:new Date()
							  };
							  strDetail.push(strD);
						  };
						  var josonDetail = Ext.encode(strDetail);
						  var strParams = {
							  str:josonDetail
						  };
						  Ext.Ajax.request({
							  method:'POST',
							  url:'report_Action_SaveReportSet.action',
							  params:strParams,
							  success:function(response){
								  var data = Ext.decode(response.responseText);
								  if(data.isSucc){
									  Ext.example.msg($i18n_prompt.prompt,data.msg);
									  Ext.getCmp('cmbReportSelect'+Ext.getCmp('moduleId').getValue()).getStore().load();
									  Ext.getCmp('cmbProjectSelect'+Ext.getCmp('moduleId').getValue()).getStore().load();
								  }else{
									  Ext.example.msg($i18n_prompt.prompt,data.msg);
									  Ext.getCmp('searchDSetGrid').getStore().load();
								  }				
							  }
						  });	
					    }
					});
			 	}
			 }
		 },{
			 text : '关闭',
			 iconCls : 'close',
			 id : 'close',
			 listeners:{
			 	click:function(){
			 		Ext.getCmp('reportCheckSetWindow').close();
			 	}
			 }
		 }]
	}
	]
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
