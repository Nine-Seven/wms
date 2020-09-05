Ext.define('cms.view.Viewport',{ 
    extend: 'Ext.Viewport', 
    layout: 'fit', 
    hideBorders: true, 
	    initComponent : function(){ 
	        var me = this; 
	        Ext.apply(me, { 
	            items: [{ 
	                id:'desk', 
	                layout: 'border', 
	                items: [ 
	                    Ext.create('cms.view.Header'), 
	                    cascadeMenu,
	                    Ext.create('cms.view.TabPanel'), 
	                    Ext.create('cms.view.South') 
	                ] 
	            }] 
	        }); 
	        me.callParent(arguments); 
	    } 
	}) ;
		
	/*//左边放一个手风琴菜单  
    Ext.define('cms.view.AccordionMenu2',{
		extend : 'Ext.panel.Panel',
    	alias : 'widget.mmrAccordionMenu',
        region: 'west',  
        resizable:true,  
        title: '功能区',
        layout:'accordion',  
        collapsible:true,  
        collapseMode:'mini',  
        collapseFirst:true,  
        split:true,  
        width:220,
        minSize: 175,  
        maxSize: 250,  
        layoutConfig: {        
            titleCollapse: true,  
            animate: false,  
            activeOnTop: false  
        }  
    }); //手风琴菜单结束  

    var cascadeMenu =Ext.create('cms.view.AccordionMenu2');*/



	//Ext.application({
		//name : 'Fiddle',
		//launch : function() {
			Ext.define('cms.view.AccordionMenu2', {
				extend : 'Ext.panel.Panel',
				alias : 'widget.mmrAccordionMenu',
				region: 'west',						 //设置方位
				title: '功能区',  
				layout:{
					type: 'accordion',
					multi: false,					 //不允许同时显示多个子面板.
					activeOnTop : false,             //设置打开的子面板置顶
					fill : false,                    //子面板充满父面板的剩余空间
					hideCollapseTool: false,         //显示“展开收缩”按钮
					titleCollapse : true,     		 //允许通过点击子面板的标题来展开或收缩面板
					animate:false,          		 //使用动画效果
					autoWidth:true
				},	
				width:220,
				minWidth: 90,
				height: 2000, 
					
				split: true,
				collapsible: true,
				autoScroll:true,
				//scrollable : true,
				/*defaults: {
					collapsed:false,
				}*/});
	//	}
	//});

	var cascadeMenu =Ext.create('cms.view.AccordionMenu2');

  
    // 构建左边的菜单  
	Ext.Ajax.request({
		url:'authAction_getMenu.action',
		success : function(response, options) {  
			var arr = Ext.decode(response.responseText);
			var menu=Ext.decode(arr.msg);
			for(var i=0;i<menu.length;i++){
	     		var panel = new Ext.tree.TreePanel({  
	         	title : menu[i].menuCaption,  
	       		autoScroll : true,  
	         	border : false,  
				store: Ext.create('Ext.data.TreeStore', {
			   			root: {
			        	expanded: true,
						children:arr.obj[i]
			   			}
				}),
				rootVisible : false  
	    		});  
	   			cascadeMenu.add(panel);  
			}
			cascadeMenu.doLayout();
		}
	});
