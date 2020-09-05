/**
 * 模块名称：进度监控
 * 模块代码：3901
 * @author lich
 */
Ext.define('cms.controller.odata.scheduleControlController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.scheduleControlUI'
	],
	init:function(){
		this.control({			
			'scheduleControlUI commMenuWidget4[id = menu3901] [name = refresh]':{
				click:this.menu3901RefreshClick
			},
			'scheduleControlUI grid[id=gridWare3901]':{
				selectionchange:this.gridWare3901Selectionchange
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		Ext.ComponentQuery.query('scheduleControlUI chart')[0].getStore().loadData(generateData());
	},
	menu3901RefreshClick:function()
	{
		Ext.ComponentQuery.query('scheduleControlUI chart')[0].getStore().loadData(generateData());
	},
	gridWare3901Selectionchange:function()
	{
		Ext.ComponentQuery.query('scheduleControlUI chart')[0].getStore().loadData(generateData());
	}
});
function generateData (n, floor){
        var data = [],
            p = (Math.random() *  11) + 1,
            i;
            
        floor = (!floor && floor !== 0)? 20 : floor;
        data.push({name:'分播进度',
        data1:Math.floor(Math.max((Math.random() * 100), floor))
        });
        data.push({name:'拣货进度',
        data1:Math.floor(Math.max((Math.random() * 100), floor))
        });        
        data.push({name:'补货进度',
        data1:Math.floor(Math.max((Math.random() * 100), floor))
        });        
        /*for (i = 0; i < (n || 3); i++) {
            data.push({
                name: '拣货进度',
                name1: '补货进度',
                name2: '分播进度',
                data1: Math.floor(Math.max((Math.random() * 100), floor)),
                data2: Math.floor(Math.max((Math.random() * 100), floor)),
                data3: Math.floor(Math.max((Math.random() * 100), floor)),
                data4: Math.floor(Math.max((Math.random() * 100), floor)),
                data5: Math.floor(Math.max((Math.random() * 100), floor)),
                data6: Math.floor(Math.max((Math.random() * 100), floor)),
                data7: Math.floor(Math.max((Math.random() * 100), floor)),
                data8: Math.floor(Math.max((Math.random() * 100), floor)),
                data9: Math.floor(Math.max((Math.random() * 100), floor))
            });
        }*/
        return data;
    };