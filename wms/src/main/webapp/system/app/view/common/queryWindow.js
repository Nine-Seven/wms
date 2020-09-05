/**
 * 通用查询
 */
Ext.define('cms.view.common.queryWindow',{
	extend:'Ext.window.Window',
	alias:'widget.queryWindow',
	id:'queryWindow',
	title:'查询条件选择',
	height:300,
	width : 700,
	frame:true,
	autoScroll:true,
	modal:true,
	items:[
	{      	
		buttons : [ {
			text : '新增查询条件',
			iconCls:'add',
			id:'buttonQueryAdd',
            handler : function(){
            	 Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
        	}
		},{
			text : '查询',
			iconCls : 'query',
            handler : function(){
            	
            	var detail1 = [];
            	var detail2 = [];
         		for(var i=1;i<Ext.getCmp('queryWindow').items.length;i++ ){
         			if(Ext.isEmpty(Ext.getCmp('queryWindow').items.items[i].items.items[4].value)){
         				continue;
         			}
         			//debugger;
         			if(!Ext.isEmpty(Ext.getCmp('queryWindow').items.items[i].items.items[2].value) 
         				&& !Ext.isEmpty(Ext.getCmp('queryWindow').items.items[i].items.items[4].value))
         			{
						var d={
							columnname :  Ext.getCmp('queryWindow').items.items[i].items.items[2].rawValue,	
							columnId : Ext.getCmp('queryWindow').items.items[i].items.items[2].value,
							condition : Ext.getCmp('queryWindow').items.items[i].items.items[3].value,				
							value : Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datefield'?Ext.Date.format(Ext.getCmp('queryWindow').items.items[i].items.items[4].value,'Y-m-d'):Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datetimefield'?Ext.Date.format(Ext.getCmp('queryWindow').items.items[i].items.items[4].value,'Y-m-d H:i'):Ext.getCmp('queryWindow').items.items[i].items.items[4].value, 
							logic : Ext.getCmp('queryWindow').items.items[i].items.items[1].value,
							valueType : Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datefield'?3:Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datetimefield'?5:(Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='combo'?4:1)
						};
						var d2={
								columnname :  Ext.getCmp('queryWindow').items.items[i].items.items[2].rawValue,	
								columnId : Ext.getCmp('queryWindow').items.items[i].items.items[2].value,
								condition : Ext.getCmp('queryWindow').items.items[i].items.items[3].value,				
								value : Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datefield'?Ext.Date.format(Ext.getCmp('queryWindow').items.items[i].items.items[4].value,'Y-m-d'):Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datetimefield'?Ext.Date.format(Ext.getCmp('queryWindow').items.items[i].items.items[4].value,'Y-m-d H:i:s'):Ext.getCmp('queryWindow').items.items[i].items.items[4].value, 
								logic : Ext.getCmp('queryWindow').items.items[i].items.items[1].value,
								valueType : Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datefield'?3:Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='datetimefield'?5:(Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=='combo'?4:1)
							};
						detail1.push(d);
						detail2.push(d2);
         			}else if(!Ext.isEmpty(Ext.getCmp('queryWindow').items.items[i].items.items[2].value) 
             				&& Ext.isEmpty(Ext.getCmp('queryWindow').items.items[i].items.items[4].value))
         			{
         				var d={
         						columnname :  Ext.getCmp('queryWindow').items.items[i].items.items[2].rawValue,	
    							columnId : Ext.getCmp('queryWindow').items.items[i].items.items[2].value,
    							value : Ext.getCmp('queryWindow').items.items[i].items.items[3].value=='1'?Ext.getCmp('queryWindow').items.items[i].items.items[4].value=' is null':Ext.getCmp('queryWindow').items.items[i].items.items[4].value=' is not null', 
    							condition : Ext.getCmp('queryWindow').items.items[i].items.items[3].value=10,				
    							logic : Ext.getCmp('queryWindow').items.items[i].items.items[1].value,
    							valueType : Ext.getCmp('queryWindow').items.items[i].items.items[4].xtype=2
         				};
         				detail1.push(d);
         				detail2.push(d2);
         			}
				}		
         		//保存模块id和报表id，用于区分查询条件   7-13
         		var moduleId = Ext.decode(localStorage.getItem("moduleId"));
         		var chartId = localStorage.getItem("chartId");
         		var items = localStorage.setItem("commonLocal"+moduleId+chartId,Ext.encode(detail2));
         		localStorage.removeItem("moduleId");
         		localStorage.removeItem("chartId");
         		
				var sql = Ext.encode(detail1);	
				var strQuery = {
					strQuery : sql
				};
				Ext.apply(Ext.getCmp(queryGrid)
				.getStore().proxy.extraParams,
				strQuery);
				Ext.getCmp(queryGrid).getStore().currentPage=1;
				Ext.getCmp(queryGrid).getStore().load();
				Ext.getCmp('queryWindow').close();
        	}
		}, {
			text : '关闭',
			handler : function(){
				Ext.getCmp('queryWindow').close();
        	}
		}] 
	}
	]
});


Ext.define('cms.view.common.queryPanel',{
	extend : 'Ext.panel.Panel',
	requires : ['cms.view.common.bdef_DefArticleCombo'],
	frame :true,
	layout: {
        type: 'table',
        columns: 5
    },
	defaults : {
		labelWidth : 80,
		margin : '2 2 2 2',
		labelAlign : 'right',
		width : 180
	},
    items: [{
    	xtype:'button',
    	text:'删除',
    	width : 35,
    	handler : function(){
				this.ownerCt.ownerCt.remove(this.ownerCt.id);
        	}
    },{
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
        store:Ext.create('cms.store.wms.wms_DefModuleQueryColumnStore',{autoLoad:false}),
    	listeners:{  
    		'beforequery':function(){
    			this.store.proxy.extraParams.moduleId=queryModuleId;
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
				}else if(records[0].data.xtype=='datetimefield'){
					this.ownerCt.remove(this.ownerCt.items.items[4]);
					this.ownerCt.add({
			        xtype : 'datetimefield',
					fieldLabel : '值',							
					format : 'Y-m-d H:i:s',
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
			  {id:'11',name:'不包含'},		//5-27添加hj
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























