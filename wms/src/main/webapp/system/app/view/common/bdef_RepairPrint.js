/**
 * 通用补印 1AAA
 * czh
 * 2016.6.16
 */
var print_RepairLabelStore1AAA=Ext.create('cms.store.wms.wms_RepairPrintGetSourceNoListStore',{
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(bdef_RepairPrint.items.items[3].items.items[0].getStore().count()>0){
				if(!Ext.isEmpty(bdef_RepairPrint.items.items[3].items.items[0].getStore().data.items[0].data.containerNo)){
					bdef_RepairPrint.items.items[3].items.items[0].columns[2].show();
				}
				bdef_RepairPrint.items.items[3].items.items[0].getSelectionModel().select(0);
				bdef_RepairPrint.items.items[3].items.items[0].fireEvent("itemclick",bdef_RepairPrint.items.items[3].items.items[0],records[0],0);
			}else{
				bdef_RepairPrint.items.items[3].items.items[1].getStore().removeAll();
				bdef_RepairPrint.items.items[4].items.items[0].getStore().removeAll();
				bdef_RepairPrint.items.items[4].items.items[1].getStore().removeAll();
			}
		}
	}
});
var print_LabelStore1AAA=Ext.create('cms.store.wms.wms_RepairPrintGetLabelNoListStore');
var print_ReportStore1AAA=Ext.create('cms.store.wms.wms_RepairPrintgetReportSqlBySetStore');
var print_ReportDataStore1AAA=Ext.create('cms.store.wms.wms_RepairPrintgetReportDataStore');
Ext.define('cms.view.common.bdef_RepairPrint', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.bdef_RepairPrint',
	width : '100%',
	frame : true,
	items : [ { 			
		xtype: 'button',
    	text: '查询',
    	margin : '3 3 3 80',
    	width:100,
    	handler : function(){		
        	var detail1 = [];
     		for(var i=0;i<bdef_RepairPrint.items.items[2].items.length;i++ ){
     			if(Ext.isEmpty(bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value)){
     				continue;
     			}
     			if(!Ext.isEmpty(bdef_RepairPrint.items.items[2].items.items[i].items.items[1].value) 
     				&& !Ext.isEmpty(bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value))
     			{
					var d={
						columnId : bdef_RepairPrint.items.items[2].items.items[i].items.items[1].value,
						condition : bdef_RepairPrint.items.items[2].items.items[i].items.items[2].value,				
						value : bdef_RepairPrint.items.items[2].items.items[i].items.items[3].xtype=='datefield'?Ext.Date.format(bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value,'Y-m-d'):bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value, 
						logic : bdef_RepairPrint.items.items[2].items.items[i].items.items[0].value,
						valueType : bdef_RepairPrint.items.items[2].items.items[i].items.items[3].xtype=='datefield'?3:(bdef_RepairPrint.items.items[2].items.items[i].items.items[3].xtype=='combo'?4:1)
					};
					detail1.push(d);
     			}else if(!Ext.isEmpty(bdef_RepairPrint.items.items[2].items.items[i].items.items[1].value) 
         				&& Ext.isEmpty(bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value))
     			{
     				var d={
							columnId : bdef_RepairPrint.items.items[2].items.items[i].items.items[1].value,
							value : bdef_RepairPrint.items.items[2].items.items[i].items.items[2].value=='1'?bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value=' is null':bdef_RepairPrint.items.items[2].items.items[i].items.items[3].value=' is not null', 
							condition : bdef_RepairPrint.items.items[2].items.items[i].items.items[2].value=10,				
							logic : bdef_RepairPrint.items.items[2].items.items[i].items.items[0].value,
							valueType : bdef_RepairPrint.items.items[2].items.items[i].items.items[3].xtype=2
     				};
     				detail1.push(d);
     			}
			}	
			var sql = Ext.encode(detail1);	
			var strQuery = {
				strQuery : sql,
				strModuleId:queryModuleId,
			};
			Ext.apply(bdef_RepairPrint.items.items[3].items.items[0]
			.getStore().proxy.extraParams,
			strQuery);
			bdef_RepairPrint.items.items[3].items.items[0].getStore().currentPage=1;
			bdef_RepairPrint.items.items[3].items.items[0].getStore().load();
    	}
	},{ 			
		xtype: 'button',
    	text: '打印',
    	margin : '3 3 3 80',
    	width:100,
    	handler : function(){
    		if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				return;
			}
    		var record = bdef_RepairPrint.items.items[4].items.items[1].getSelectionModel().getSelection();//选中的报表
    		var record1 = bdef_RepairPrint.items.items[3].items.items[1].getSelectionModel().getSelection();//选中的标签
    		var record2 = bdef_RepairPrint.items.items[3].items.items[0].getSelectionModel().getSelection();//选中的单据 		
    		var detail = [];
    		if(record.length != 0){
    			for(var i=0;i<record.length;i++){
    				obj = record[i].data;  					
    				if(obj.taskType=='B'){
    					if(record1.length==0){
    						Ext.example.msg('提示',"请先选择标签");
    						return;
    					};
    					for(var j=0;j<record1.length;j++){
    						var d={
    	    						enterpriseNo:Ext.get('enterpriseNo').getValue(),
    								warehouseNo:Ext.get('warehouseNo').getValue(),
    								sourceNo:record2[0].data.sourceNo,
    								reportId:obj.reportId,
    								dockNo:workSpaceNo,
    								containerNo:record1[j].data.labelNo
    	    					};
    						detail.push(d);
    						var jsonStr = Ext.encode(d);
    						var params = 
    						{
    							str:jsonStr
    						};
    						Ext.Ajax.request({
    							method:'POST',
    							url:'wms_PnfsetModuleReportQueryAction_printReportOrLabel',
    							params:params,
    							success:function(response)
    							{
    								var data = Ext.decode(response.responseText);
    								if(data.isSucc)
    								{
    									Ext.example.msg('提示',data.obj+data.msg);
    									
    								}else
    								{    									
    									Ext.Msg.alert('提示',"打印失败"+data.msg);
    								}				
    							}
    						});
    					}   					
    				}else{
    					var d={
	    						enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:Ext.get('warehouseNo').getValue(),
								sourceNo:record2[0].data.sourceNo,
								reportId:obj.reportId,
								dockNo:workSpaceNo,
								containerNo:"N"
	    					};
    					detail.push(d);
    					var jsonStr = Ext.encode(d);
    					var params = 
    					{
    						str:jsonStr
    					};
    					Ext.Ajax.request({
    						method:'POST',
    						url:'wms_PnfsetModuleReportQueryAction_printReportOrLabel',
    						params:params,
    						success:function(response)
    						{
    							var data = Ext.decode(response.responseText);
    							if(data.isSucc)
    							{
    								Ext.example.msg('提示',data.obj+data.msg);
    								
    							}else
    							{
    								Ext.Msg.alert('提示',"打印失败"+data.msg);
    							}				
    						}
    					});
    				}
    			}			   			
    			
    		}else{
    			Ext.example.msg('提示',"请选择报表");
    			return;
    		}   		
    	}
	},{
		xtype:'panel',	
		region : 'north',
		frame : true,
		height:150,
		autoScroll: true,
		width:800,		
		items : []
	},{
	    xtype:'panel',
		//region : 'center',
	    //region : 'west',
	    //width:'100%',
		layout : 'column',
		items : [ {
			xtype : 'grid',
			//region : 'west',
			title : '单据选择',
			//width : 600,
			//width:'50%',
		    //region:'west',
			height:200,
			store:print_RepairLabelStore1AAA,
			listeners: {
		        itemclick: function (me, record, item, index, e, eOpts) {
		        	var strQuery = {
		    				strModuleId:queryModuleId,
		    				sourceNo:record.data.sourceNo,
		    				containerNo:record.data.containerNo,
		    			};
		    			Ext.apply(bdef_RepairPrint.items.items[3].items.items[1].getStore().proxy.extraParams,strQuery);
		    			bdef_RepairPrint.items.items[3].items.items[1].getStore().removeAll();
		    			bdef_RepairPrint.items.items[3].items.items[1].getStore().load();
	    			var strQuery = {
		    				strModuleId:queryModuleId,
		    				sourceNo:record.data.sourceNo,
		    				containerNo:record.data.containerNo,
		    			};
		    			Ext.apply(bdef_RepairPrint.items.items[4].items.items[0].getStore().proxy.extraParams,strQuery);
		    			bdef_RepairPrint.items.items[4].items.items[0].getStore().removeAll();
		    			bdef_RepairPrint.items.items[4].items.items[0].getStore().load();
	    			var str = {
	    					id:{
	    						enterpriseNo:Ext.get('enterpriseNo').getValue(),
	    						reportId:'N',
	    						reportType:record.data.reportType==''?  'N':record.data.reportType,
								paperType:record.data.paperType==''?  'N':record.data.paperType,
	    					},	
	    					moduleId:queryModuleId,
	    					enterpriseNo:Ext.get('enterpriseNo').getValue(),
	    					warehouseNo:Ext.get('warehouseNo').getValue(),
		    				sourceNo:record.data.sourceNo==''?  'N':record.data.sourceNo,	    					    					    				    				
		    			};
	    			var jsonStr = Ext.encode(str);
	    			params={
						str:jsonStr
					},
	    			Ext.apply(bdef_RepairPrint.items.items[4].items.items[1].getStore().proxy.extraParams,params);
	    			bdef_RepairPrint.items.items[4].items.items[1].getStore().removeAll();
	    			bdef_RepairPrint.items.items[4].items.items[1].getStore().load();
	    			var hd_checker = bdef_RepairPrint.items.items[3].items.items[1].getEl().select('div.x-column-header-checkbox'); 
	    			var hd = hd_checker.first(); 
	    			//清空表格头的checkBox 
	    			if(hd.hasCls('x-grid-hd-checker-on')){ 
	    			hd.removeCls('x-grid-hd-checker-on'); 
	    			}
	    			var hd_checker = bdef_RepairPrint.items.items[4].items.items[1].getEl().select('div.x-column-header-checkbox'); 
	    			var hd = hd_checker.first(); 
	    			//清空表格头的checkBox 
	    			if(hd.hasCls('x-grid-hd-checker-on')){ 
	    			hd.removeCls('x-grid-hd-checker-on'); 
	    			}
		        }
		    },
			columns : [ {
				xtype : 'rownumberer',
				width : 40
			}, {
				width : 200,
				text : $i18n.source_plan_no,
				dataIndex : 'sourceNo'
			},
			{
				width : 200,
				text : $i18n.container_no,
				dataIndex : 'containerNo',
				hidden : true
			}, {
				width : 125,
				dataIndex : 'paperType',
				hidden : true
			},{
				width : 125,
				dataIndex : 'reportType',
				hidden : true
			} ]
			
		}, {
			xtype : 'grid',
			//region : 'east',
			//width : 522,
			//width:'50%',
		    //region:'west',
			height:200,
			title : '标签选择',
			store:print_LabelStore1AAA,
			multiSelect : true,
			selModel : {
				selType : 'checkboxmodel',
				checkOnly : true
			},
			columns : [ {
				xtype : 'rownumberer',
				width : 40
			}, {
				width : 125,
				text : $i18n.label_no,//标签号
				dataIndex : 'labelNo'
			} ]
		} ]
	},{

	   xtype:'panel',
	   //region : 'center',
	   region:'south',
	   layout : 'column',
	   items  : [ {
			xtype : 'grid',
			//region : 'west',
			height:135,
			//width : 600,
			columnWidth : 0.5,
			autoScroll: true,
			title : '数据明细',
			store:print_ReportDataStore1AAA,
			columns : [ {
				xtype : 'rownumberer',
				width : 30
			},{
				width : 200,
				text : '订单号',//订单号
				dataIndex : 'poNo'
			} , {
				width : 120,
				text : "客户/供应商",//客户/供应商
				dataIndex : 'custNo'
			}, {
				width : 150,
				text : '商品信息',//商品信息
				dataIndex : 'articleName'
			} ,{
				width : 200,
				text : '波次号',//波次号
				dataIndex : 'waveNo'
			}]
		}, {
			xtype : 'grid',
			//region : 'east',
			//width : 522,
			columnWidth : 0.5,
			height:133,
			autoScroll: true,
			title : '报表列表',
			store:print_ReportStore1AAA,
			multiSelect : true,
			listeners: {
				beforeselect : function(me, record, index, eOpts){
					if(record.data.taskType=='B'){
						if(bdef_RepairPrint.items.items[3].items.items[1].getSelectionModel().getSelection().length==0){
							Ext.example.msg($i18n.prompt,'请先选择标签号！');
							selectRecords(record);
							return;
						}
					}
				}
			},
			selModel : {
				selType : 'checkboxmodel',
				checkOnly : true
			},
			columns : [ {
				xtype : 'rownumberer',
				width : 30
			}, {
				dataIndex : 'reportId',
				width : 200,
				text : '报表ID'
			}, {
				dataIndex : 'reportName',
				text : '报表名'
			},{
				dataIndex : 'taskType',
				hidden : true
			}]
		}]
	
	} ]
});

Ext.define('cms.view.common.reportQueryPanel',{
	extend : 'Ext.panel.Panel',
	requires : ['cms.view.common.bdef_DefArticleCombo'],
	frame :true,
	region:'north',
	width:'100%',
	layout: {
        type: 'table',
        columns: 4
    },
	defaults : {
		labelWidth : 80,
		margin : '2 2 2 2',
		labelAlign : 'right',
		width : 180
	},
    items: [{
        xtype: 'combo',
        fieldLabel: '逻辑',
        labelWidth : 30,
        width : 90,
        displayField: 'name',
   		valueField: 'id',
   		forceSelection : true,
		store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
	    data:[
	          {id:'1',name:'与'},
			  {id:'2',name:'或'}					  
	          ]
    	}),
    	value:'1'
    },{
        xtype: 'combo',
        fieldLabel: '字段名称',
        labelAlign : 'right',
        labelWidth : 60,
        width : 220,
        displayField: 'columnname',
   		valueField: 'columnid',
        store:Ext.create('cms.store.wms.wms_PntsetmodulereportqueryStore',{autoLoad:false}),
    	listeners:{  
    		'beforequery':function(){
    			this.store.proxy.extraParams.strModuleId=queryModuleId;
    		},
			'select': function(combo,records,eOpts) {
				if(records[0].data.xtype=='textfield'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
					this.ownerCt.add({
				        xtype: 'textfield',
				        fieldLabel: '值',
				        labelWidth : 20,
				        width : 195
				    });
				}else if(records[0].data.xtype=='numberfield'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'numberfield',
						fieldLabel : '值',		
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='datefield'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'datefield',
						fieldLabel : '值',							
						format : 'Y-m-d',
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='combo'){					
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'combo',
						fieldLabel : '值',	
						displayField: 'dropValue',
    					valueField: 'value',
						store:Ext.create("cms.store.common.comboStore"),
				        labelWidth : 20,
				        width : 195,
						listeners:{  
			    		'beforequery':function(){
			    			this.store.proxy.extraParams.str=this.ownerCt.items.items[2].findRecordByValue(this.ownerCt.items.items[2].value).data.fieldtable+","+
			    			this.ownerCt.items.items[2].findRecordByValue(this.ownerCt.items.items[2].value).data.fieldcolumn;
			    		}}
					});
				}else if(records[0].data.xtype=='bdef_DefArticleCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'bdef_DefArticleCombo',
						fieldLabel : '值',		
						store : Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='categoryCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'categoryCombo',
						fieldLabel : '值',		
						store:Ext.create('cms.store.baseinfo.categoryComboStore'),
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='brandCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'brandCombo',
						fieldLabel : '值',		
						store:Ext.create('cms.store.baseinfo.brandComboStore',{autoLoad:true}),
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='goodsCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'goodsCombo',
						fieldLabel : '值',		
						store:Ext.create('cms.store.baseinfo.goodsComboStore'),
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='guestCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'guestCombo',
						fieldLabel : '值',		
						store:Ext.create('cms.store.stockData.guestComboStore',{autoLoad:true}),
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='stockCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'stockCombo',
						fieldLabel : '值',		
						store:Ext.create('cms.store.stockData.stockComboStore',{autoLoad:true}),
				        labelWidth : 20,
				        width : 195
					});
				}else if(records[0].data.xtype=='workerCombo'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
						this.ownerCt.add({
				        xtype : 'workerCombo',
						fieldLabel : '值',		
						store :  Ext.create("cms.store.baseinfo.workerComboStore"),
				        labelWidth : 20,
				        width : 195
					});
				}
			}
		}
    },{
        xtype: 'combo',
        fieldLabel: '条件',
        labelWidth : 30,
        width : 120,
        displayField: 'name',
   		valueField: 'id',
		forceSelection : true,
		store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
	    data:[
	          {id:'1',name:'等于'},
			  {id:'2',name:'大于'},
			  {id:'3',name:'小于'},
			  {id:'4',name:'大于等于'},
			  {id:'5',name:'小于等于'},
			  {id:'6',name:'不等于'},
			  {id:'7',name:'包含'},
			  {id:'11',name:'不包含'},		
			  {id:'8',name:'存在'},
			  {id:'9',name:'不存在'}
			  
	          ]
    	}),
    	value:'1'
    },{
        xtype: 'textfield',
        fieldLabel: '值',
        labelWidth : 20,
        width : 195,
    }]    
});
