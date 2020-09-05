Ext.define('cms.view.AccordionMenu3', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mmrAccordionMenu',
	id:'23232d',
    region: 'west',  
    resizable:true,  
    //title: '功能区',  
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
});