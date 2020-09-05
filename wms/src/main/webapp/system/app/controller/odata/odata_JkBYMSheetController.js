/**
 * 模块名称：BYM出货订单接口
 * 模块代码：3911
 * @author chensr
 */

var flag='1';
var IsCanEdit3911=false;
Ext.define('cms.controller.odata.odata_JkBYMSheetController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_JkBYMSheetUI'
	],
	init:function(){
		this.control({
			//新增
			'odata_JkBYMSheetUI button[name=add]':{
				click:this.detailAdd
			},
			//保存出货订单
			'odata_JkBYMSheetUI button[name=save]':{
				click:this.save
			},
			
			//是否可编辑
			'odata_JkBYMSheetUI grid[id=JkBYMSheet3911]':{
				beforeedit:this.JkBYMSheet3911beforeedit
			},
			
			//生成
			'odata_JkBYMSheetUI button[name=BYMData]':{
				click:this.createData
			},
			
			//根据仓别加载出货订单
//			'odata_JkBYMSheetUI combo[id=warehouseUI3911]':{
//				select:this.getBYMSheetWithCondition
//			},
			//删除出货订单
			'odata_JkBYMSheetUI commMenuWidget4[id=menu3911] button[name=delete]':{
				click:this.detailDelete
			},
			//查询
			'odata_JkBYMSheetUI button[id=btnQuery3911]':{
				click:this.btnQuery3911Click
			}
		});
	},
	initializtion:function()
	{
		Ext.getCmp('warehouseUI3911').setValue(Ext.get('warehouseNo').getValue());
	},
	createData:function(){
		Ext.Ajax.request({
			url:'odata_BYMSheetAction_createData',
			success : function(response) {
				var data=Ext.decode(response.responseText);
				if(data.isSucc){					
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('JkBYMSheet3911').getStore().load();
				}else{
					Ext.Msg.alert($i18n.prompt,data.msg);

					//Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	},
	
	//添加网格窗口
	detailAdd:function(){
		IsCanEdit3911=true;
		var store = Ext.getCmp('JkBYMSheet3911').getStore();
		var count = store.getCount();
		var j = count * 1 - 1;
		
		if(j>=0){
			if(Ext.getCmp('warehouseUI3911').getValue()!=null){
				
			}else{
				Ext.example.msg("提示","请在界面选择相应的仓别");
				return;
			}
		}else{
			
		}
		if(flag=='1'){
			Ext.getCmp('JkBYMSheet3911').getStore().removeAll();
			disableButtonFunc(0,'#menu3911 [name=save]','保存');
			Ext.getCmp('corpkey3911').setVisible(false);
			Ext.getCmp('sheettype3911').setVisible(false);
			flag='0';
		}
		var r=Ext.create('cms.model.odata.odata_JKBYMSheetModel',{});
		r.set('warehouseNo',Ext.getCmp('warehouseUI3911').getValue());
		r.set('rgstName',Ext.get('workerNo').getValue());
		r.set('dateText',Ext.util.Format.date(new Date(),'Y-m-d G:i:s '));
		store.add(r);
		Ext.getCmp('JkBYMSheet3911').editingPlugin.startEdit(count,1);
		
	},
	
	//是否可编辑
	JkBYMSheet3911beforeedit:function(e){
		  if(!IsCanEdit3911)
		    {
		        e.cancel = true;
		        return  false;  
		    }
	},
	
	//保存出货订单
	save: function(){
		saveBYMsheet(); 
	},
	
	//根据仓别加载出货订单
//	getBYMSheetWithCondition:function(){
//		var strDetail1 = [];
//		var d1={
//			columnId:'t1.warehouse_no',
//			value:Ext.getCmp('warehouseUI3911').getValue()
//		};
//		strDetail1.push(d1);
//		var jsonDetail = Ext.encode(strDetail1);
//		var str = {
//				str  : jsonDetail
//		};
//		Ext.apply(Ext.getCmp('JkBYMSheet3911').getStore().proxy.extraParams,str);
//		Ext.getCmp('JkBYMSheet3911').getStore().removeAll();
//		Ext.getCmp('JkBYMSheet3911').getStore().load();
//	},
	
	//删除出货订单
	detailDelete:function(){
		var objdata = Ext.getCmp('JkBYMSheet3911').getSelectionModel().getSelection();  
        if (objdata.length == 0) {  
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete); 
            return;  
        } else {  
        	
        	  if(flag=='0'){
        		  Ext.getCmp('JkBYMSheet3911').getStore().remove(objdata);	
        	  }else{
        	
        		Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(button,text) {
        			if(button=='yes')
    				{
    					Ext.getCmp('JkBYMSheet3911').getStore().remove(objdata);
    					Ext.Ajax.request({
    						url:'odata_BYMSheetAction_deleteBYMSheet',
    						params :{
    							warehouseNo: objdata[0].get('warehouseNo'),
    							sheetNo:objdata[0].get('sheetNo'),
    							rgstName:objdata[0].get('rgstName'),
    							rgstDate:objdata[0].get('rgstDate').time,
    							corpkey:objdata[0].get('corpkey'),
    							sheettype:objdata[0].get('sheetType')
    						},
    						success : function(response) {
    						Ext.getCmp('JkBYMSheet3911').getStore().load();
    					}
    					});
    				}			
            });
         }
        }
	},
	btnQuery3911Click:function()
	{
		if(Ext.isEmpty(Ext.Date.format(Ext.getCmp('date3911').getValue(),'Y-m-d')))
		{
			Ext.example.msg('提示',"请选择操作日期查询！");
		}else
		{
			var strDetail1 = [];
			var d={
				columnId:"to_char(t1.RGST_DATE,'yyyy-mm-dd')",
				value:Ext.Date.format(Ext.getCmp('date3911').getValue(),'Y-m-d')
			};
			strDetail1.push(d);		
			var jsonDetail = Ext.encode(strDetail1);
			var strWheresql = {
				str : jsonDetail
			};
			Ext.apply(Ext.getCmp('grid_error_3911').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_error_3911').getStore().removeAll();
			Ext.getCmp('grid_error_3911').getStore().load();			
		}
	}
});

//保存出货订单
function saveBYMsheet(){

	var gridcount=Ext.getCmp('JkBYMSheet3911').getStore().getCount();
	if(gridcount>0){
		if(!commonCheckdetailgrid('JkBYMSheet3911',0,gridcount-1))
		{
			return;
		}
	}else{			
		Ext.example.msg('提示',"数据不允许为空，请输入数据！");
		return;
	}
	
	if(Ext.getCmp('company3911').getValue()==null || Ext.getCmp('company3911').getValue()==""){
		Ext.example.msg('提示',"请选择所属公司");
		return;
	}
	
	if(Ext.getCmp('sheetTypeM3911').getValue()==null || Ext.getCmp('sheetTypeM3911').getValue()==""){
		Ext.example.msg('提示',"请选择单据类型");
		return;
	}
	
	var detail=[];
	for(var i=0;i<gridcount;i++){
		var exp=Ext.getCmp('JkBYMSheet3911').getStore().getAt(i);
		var d={
			id:{
				warehouseNo:Ext.get('warehouseNo').getValue(),
				sheetNo:exp.get('sheetNo'),
				rgstName:Ext.get('workerNo').getValue(),
				rgstDate:exp.get('dateText'),
				corpkey:Ext.getCmp('company3911').getValue(),
				sheettype:Ext.getCmp('sheetTypeM3911').getValue()
			}
		};
		detail.push(d);
	};
		
	Ext.Ajax.request({
		url:'odata_BYMSheetAction_saveBYMSheet',
		method:'post',
		params:{
			str:Ext.encode(detail)
		},
		success:function(response){
			var data=Ext.decode(response.responseText);
			if(data.isSucc){					
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('JkBYMSheet3911').getStore().load();
			}else{
				Ext.Msg.alert($i18n.prompt,data.msg);

				Ext.example.msg($i18n.prompt,data.msg);
			}
		}
	});	
	disableButtonFunc(1,'#menu3911 [name=save]','保存');
	Ext.getCmp('corpkey3911').setVisible(true);
	Ext.getCmp('sheettype3911').setVisible(true);
	IsCanEdit3911=false;
	flag='1';
}
