Ext.define('cms.view.Header2', {
	extend: 'Ext.Toolbar',
	height:40,
	width:1000,
	region: 'north',
	id:'headerPan',
	html:'<div class="top_div">'+
		//			'<div style="float:left;margin-top:5px;margin-left:20px;color:#FFFFFF;"><img src="system/extjs/resources/images/logo_n.gif" /></div>'+
		'<div style="float:right; padding-top:20px;padding-right:10px;padding-left:10px;color:#FFFFFF;text-decoration: none;">'+
		'<a href="javascript:updatePwd();" style="color:#FFFFFF;text-decoration: none;">修改密码              </a>'+
		'<a href="javascript:setWorkSpace();" style="color:#FFFFFF;text-decoration: none;">设置工作站</a><span style="padding-right:10px;" id="workSpaceTips">(暂未设置)</span>'+
		'<a href="javascript:doLogout()" style="color:#FFFFFF;text-decoration: none;">注销系统</a></div><div style="both:clear;"></div></div>'
});
//构建左边的菜单
Ext.Ajax.request({
	url:'authAction_getOneLevelMenu.action',
	success : function(response, options) {//success
		var arr = Ext.decode(response.responseText);
		var menu=Ext.decode(arr.msg);
		for(var i=0;i<menu.length;i++){//for循环
			var mar='0px';
			if(i==0){
				mar='200px';
			}
			var but = new Ext.button.Button({
				text:'<div style="color: #FFFFFF">'+menu[i].menuCaption+'</div>',
				data:menu[i].folderId,
				style: {
					marginLeft: mar
				},
				listeners:{//listeners
					'click':function(){//click
						Ext.Ajax.request({//获取子菜单
							url:'authAction_getBset_ModuleTree.action',
							params : {
								node:this.data
							},
							success:function(response){
								var data = Ext.decode(response.responseText);
								var panel = new Ext.tree.TreePanel({
									title : '菜单',
									autoScroll : true,
									border : false,
									autoShow :true,
									store: Ext.create('Ext.data.TreeStore', {
										root: {
											expanded: true,
											children:data
										}
									}),
									rootVisible : false
								});
								Ext.ComponentQuery.query('mmrAccordionMenu')[0].removeAll();
								Ext.ComponentQuery.query('mmrAccordionMenu')[0].add(panel);
								Ext.ComponentQuery.query('mmrAccordionMenu treepanel')[0].expand();
							}
						});//获取子菜单
					}//click
				}//listeners
			});
			Ext.getCmp('headerPan').add(but);
		}//for循环
		if(Ext.getCmp('headerPan').items.items.length>0)
		{
			Ext.getCmp('headerPan').items.items[0].fireEvent('click');
		}
	}//success
});