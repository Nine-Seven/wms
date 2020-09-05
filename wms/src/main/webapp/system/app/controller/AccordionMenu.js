var arrayObj = new Array();
Ext.define('cms.controller.AccordionMenu', {
	extend : 'Ext.app.Controller',
	alias : 'widget.AccordionMenu',
	//requires : [ 'Ext.view.AccordionMenu' ],
	init : function() {
		this.control({			
			'mmrAccordionMenu treepanel' : {
				itemmousedown : this.accordionLoadMenu
			}
		});
	},
	accordionLoadMenu : function(selModel, record) {
		if (record.get('leaf')) {					
			var main = Ext.getCmp("content-panel");
			var panel = main.getComponent(record.get('id'));
			if (!panel) {							
				if(!inArray(record.get('id'))){
					arrayObj.push(record.get('id'));
					var application = this.application;//获得当前的application
					Ext.require(record.get('qtitle'),
					function(){
						var mycontrol = application.getController(record.get('qtitle'));
						mycontrol.init(application);
					},this);	
				}
				
				//panel = Ext.create(queryViewLocation(record.raw.url), {
				panel = Ext.create(record.raw.url, {
					closable : true,
					closeAction : 'destory',
					autoDestory : true
				});
				openTab(panel, record.get('id'));
				setAuth(record.get('id'));
				if(typeof(_myAppGlobal.getController(record.get('qtitle')).initializtion)=='function'){
					_myAppGlobal.getController(record.get('qtitle')).initializtion();
				}
			} else {
				var main = Ext.getCmp('content-panel');
				main.setActiveTab(panel);
			}
		}
	}
});
function createPanel(title, html) {
	return panel = {
		title : title,
		html : html,
		closable : true,
		border : false
	/*,
			iconCls : 'tabs'*/
	};
};
function openTab(panel, id) {
	var o = (typeof panel == "string" ? panel : id || panel.id);
	var main = Ext.getCmp("content-panel");
	var tab = main.getComponent(o);
	if (tab) {
		main.setActiveTab(tab);
	} else if (typeof panel != "string") {
		panel.id = o;
		var p = main.add(panel);
		main.setActiveTab(p);
	}
};
function openOrCloseTab(panel, id) {
	var o = (typeof panel == "string" ? panel : id || panel.id);
	var main = Ext.getCmp("content-panel");
	var tab = main.getComponent(o);
	if (tab) {
		tab.close();
		panel.id = o;
		var p = main.add(panel);
		main.setActiveTab(p);
	} else if (typeof panel != "string") {
		panel.id = o;
		var p = main.add(panel);
		main.setActiveTab(p);
	}
};
function queryViewLocation(url) {
	var result = "";
	Ext.Ajax.request({
		url : url,
		method : 'get',
		async : false,
		success : function(response) {
			result = Ext.String.trim(response.responseText);
		}
	});
	return result;
}

function inArray(id){
	for ( var i=0 ; i < arrayObj.length ; ++i ) 
	{ 
		if ( arrayObj[i] == id) 
		{ 
			return true;
		}
	}
	return false;
}

function setAuth(id,authValue)
{
	Ext.Ajax.request({
		url:'authAction_getWorkerModuleRight.action',
		params : {
			strModuleId:id
		},
		success:function(response){
			var data = Ext.decode(response.responseText);
			for(var i=0;i<data.length;i++){
				if(Ext.ComponentQuery.query(data[i].butId).length>0)
				{
					Ext.ComponentQuery.query(data[i].butId)[0].setVisible(data[i].flag);
				}
			}
		}
	});
}
