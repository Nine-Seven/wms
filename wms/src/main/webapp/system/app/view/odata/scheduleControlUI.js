/**
 * 模块名称：进度监控
 * 模块代码：3901
 * @author lich
 */
var store1 = Ext.create('Ext.data.JsonStore', {
	fields: ['name', 'data1']/*,
    data: generateData()*/
});

var store2 = Ext.create('Ext.data.JsonStore', {
	fields: ['locateNo', 'batchNo'],
    data : [
         {locateNo: 'LO00114061900002', batchNo: 'N'},
         {locateNo: 'LO00114061900003', batchNo: 'N'},
         {locateNo: 'LO00114061900004', batchNo: 'N'},
         {locateNo: 'LO00114061900005', batchNo: 'N'}
     ]
});
Ext.define('cms.view.odata.scheduleControlUI',{
	alias:'widget.scheduleControlUI',
	title:$i18n.title3901,//进度监控
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4'
	         ],
	items:[
	{
		xtype:'commMenuWidget4',
	    id:'menu3901',
	    region:'north'
	},
    {
    	xtype:'grid',
    	region:'center',
    	id:'gridWare3901',
    	store:store2,
	    columns:[
	    {			
	        xtype : 'rownumberer',
		    width : 30
	    },
	    {
		    width:140,
		    text : $i18n.locate_no,//波次
		    dataIndex:'locateNo'			
	    },
	    {
		    width:105,
		    text : $i18n.batch_no,//批次
		    dataIndex:'batchNo'			
	    }]
    },
    {
    	xtype:'panel',
    	region:'south',
    	layout: 'fit',
    	height:200,
    	/*tbar: [{
            text: 'Save Chart',
            handler: function() {
                Ext.MessageBox.confirm('Confirm Download', 'Would you like to download the chart as an image?', function(choice){
                    if(choice == 'yes'){
                        chart.save({
                            type: 'image/png'
                        });
                    }
                });
            }
        }, {
            text: 'Reload Data',
            handler: function() {
                store1.loadData(generateData());
            }
        }],*/
    	items:[{
    		xtype: 'chart',
            animate: true,
            style: 'background:#fff',
            shadow: false,
            store: store1,
            axes: [{
                type: 'Numeric',
                position: 'bottom',
                fields: ['data1'],
                label: {
                   renderer: Ext.util.Format.numberRenderer('0,0')
                },
                //title: 'Number of Hits',
                minimum: 0,
            	maximum: 100
            }, {
                type: 'Category',
                position: 'left',
                fields: ['name']
            }],
            series: [{
                type: 'bar',
                axis: 'bottom',
                label: {
                    display: 'insideEnd',
                    field: 'data1',
                    renderer: Ext.util.Format.numberRenderer('0'),
                    orientation: 'horizontal',
                    color: '#333',
                    'text-anchor': 'middle',
                    contrast: true
                },
                xField: ['name'],
                yField: ['data1']
                //color renderer
                /*renderer: function(sprite, record, attr, index, store) {
                    var fieldValue = Math.random() * 20 + 10;
                    var value = (record.get('data1') >> 0) % 5;
                    var color = ['rgb(213, 70, 121)', 
                                 'rgb(44, 153, 201)', 
                                 'rgb(146, 6, 157)', 
                                 'rgb(49, 149, 0)', 
                                 'rgb(249, 153, 0)'][value];
                    return Ext.apply(attr, {
                        fill: color
                    });
                }*/
            }]
    	}
        ]
    }
    ]
});