/**
 * 模块名称：
 * zhouhuan
 */
Ext.define('cms.view.report.window.renameWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.renameWindow',
	id:'renameWindow',
	//title:$i18n.checkSet,
	layout:'border',
    height:100,
	width:300,
	modal:true,
    items: [
	{
		xtype : 'form',
		region : 'north',
		width:'100%',
		frame : true,
		items : [ 
		{
			layout : {
				type : 'table',
				columns : 1
			},
			xtype : 'container',
			defaults : {
				xtype : 'textfield',
				margin : '2 2 2 0',
				labelAlign : 'right'
			},
			items : [{
				xtype:'textfield',
				fieldLabel : '请输入新名称',
				margin : '5 5 5 5',
				labelAlign:'right',
				id:'txtRname',
				allowBlank:false
			},
			{
				xtype:'textfield',
				id:'tModuleId',
				hidden:true
			}]
			}]

	},{
		region:'south',
		xtype:'toolbar',
		border:0,
		items: ['->',{
			text : '保存',
			iconCls : 'save',
			id : 'saveRename',
			listeners:{
				click:function(){
					var strNewName = Ext.getCmp('txtRname').getValue();
					var strOldName = Ext.getCmp('cmbProjectSelect_w').getValue();
					var strPgmId = Ext.getCmp('textReport_W').getValue();
					if(strNewName==strOldName)
					{
						Ext.example.msg($i18n_prompt.prompt,"新名称不能与原名称一样");
						return;
					}
					var flag=checkProjectName(strPgmId,strNewName);
					if(flag)
					{
						
					}else
					{
						return;
					}
					if(Ext.getCmp('tModuleId').getValue()==0)
					{
						var strParams = {
							strNewName:strNewName,
							strOldName:strOldName,
							strPgmId:strPgmId
						};
						Ext.Ajax.request({
							method:'POST',
							url:'report_Action_SaveRename.action',
							params:strParams,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.example.msg($i18n_prompt.prompt,data.msg);
									Ext.getCmp('renameWindow').close();
									Ext.getCmp('cmbProjectSelect_w').getStore().load();
									Ext.getCmp('cmbProjectSelect_w').setValue(strNewName);
								}else{
									Ext.example.msg($i18n_prompt.prompt,data.msg);
									Ext.getCmp('searchDSetGrid').getStore().load();
								}				
							}
						});	
					}else
					{
						var store=Ext.getCmp('searchDSetGrid').getStore().query('showFlag',true);
						var strItemName = Ext.getCmp('txtRname').getValue();
						var strPgmId = Ext.getCmp('textReport_W').getValue();
						var flag = checkProjectName(strPgmId,strItemName);
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
								  url:'report_Action_SaveOtherReportSet.action',
								  params:strParams,
								  success:function(response){
									  var data = Ext.decode(response.responseText);
									  if(data.isSucc){
										  Ext.example.msg($i18n_prompt.prompt,data.msg);
										  Ext.getCmp('cmbProjectSelect_w').getStore().reload();
										  Ext.getCmp('renameWindow').close();
									  }else{
										  Ext.example.msg($i18n_prompt.prompt,data.msg);
									  }				
								  }
							  });	
						    }
						});
					}
				}
			}
		},{
			text : '取消',
			iconCls : 'close',
			id : 'saveClose',
			listeners:{
				click:function(){
					Ext.getCmp('renameWindow').close();
				}
			}
		}]
	}]
});

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

