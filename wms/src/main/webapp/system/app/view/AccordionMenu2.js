//左边放一个手风琴菜单  
    var cascadeMenu =//Ext.define('cms.view.AccordionMenu',{
    	new Ext.Panel({  
		//extend : 'Ext.panel.Panel',
    	alias : 'widget.mmrAccordionMenu',
        region: 'west',  
        resizable:true,  
        title: '功能区',  
        //id:'westMenu',  
        layout:'accordion',  
        collapsible:true,  
        collapseMode:'mini',  
        collapseFirst:true,  
        split:true,  
        width:200,  
        minSize: 175,  
        maxSize: 500,  
        layoutConfig: {        
            titleCollapse: true,  
            animate: false,  
            activeOnTop: false  
        }  
    }); //手风琴菜单结束  
      
    // 构建左边的菜单  
	Ext.Ajax.request({
		url:'authAction_getMenu.action',
		params : {
			quserId:userId
		},
		success : function(response, options) {  
        	var arr = Ext.decode(response.responseText);  
         	for (var i = 0; i < arr.length; i++) {  
         		var panel = new Ext.tree.TreePanel({  
          		id : i,//arr[i].id,  
             	title :i,// arr[i].text,  
          		//iconCls : arr[i].iconCls,  
           		autoScroll : true,  
             	border : false,  
        		/*loader : new MenuLoader({  
          			dataUrl : 'authAction_getMenuItem.action=sub&id='+ arr[i].id  
           		}),  */
         		/*root : new Ext.tree.AsyncTreeNode({  
             		expanded : true  
               	}),  */
				rootVisible : false  
        	});  
           	cascadeMenu.add(panel);  
      		panel.on('expand', function(p) {  
      			var expires = new Date();  
          		 expires.setDate(expires.getDate() + 30);  
         		});  
        	}  
        	cascadeMenu.doLayout();  
            }  
});  