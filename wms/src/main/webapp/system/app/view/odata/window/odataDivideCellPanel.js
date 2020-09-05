Ext.define('cms.view.odata.window.odataDivideCellPanel',{	
	alias:'widget.odataDivideCellPanel',
	extend :'Ext.panel.Panel',
	layout: 'absolute',
    height:180,
	width:180,
	frame:true,//style:'background:#ffc',
	items:[{				//指的是格子号   items[0]
        xtype: 'label',
    	text: 'cellNO',
    	forId:'1',
    	style: {
            fontSize: '22px'
        },
    	x: '1%',
        y: '5%'
	},/*{
    	xtype: 'label',
    	text: 'xx15030001',
    	forId:'4',
    	x: '1%',
        y: '30%'    	
    },*/{
        xtype: 'label',		//已播量      7-1
    	text: '已播量:',
    	forId:'2',
    	style: {
            fontSize: '10px'
        },
    	x: '30%',
        y: '55%' 	
    },{
        xtype: 'label',		//已播量
    	text: '0',
    	forId:'2',
    	style: {
            fontSize: '22px'
        },
    	x: '50%',
        y: '50%' 	
    },{
        xtype: 'label',		//应播量      7-1
    	text: '应播量:',
    	forId:'2',
    	style: {
            fontSize: '10px'
        },
    	x: '65%',
        y: '55%' 	
    },{
        xtype: 'label',		//应播量   7-1
    	text: '0',
    	forId:'2',
    	style: {
            fontSize: '22px'
        },
    	x: '85%',
        y: '50%' 	
    },{
        xtype: 'label',			
    	//text: '1503250001',
    	forId:'3',
    	style: {
            fontSize: '14px'
        },
    	x: '30%',
        y: '10%'    	
    }, {
        xtype: 'label',
    	forId:'4',
    	hidden:true  	
    }]
});
