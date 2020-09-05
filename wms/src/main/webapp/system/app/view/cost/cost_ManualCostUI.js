/**
 * 模块名称：手工生成费用明细
 * 模块编码：B903
 * 创建：hcx 
 */
var cost_ExpensesListStore=Ext.create('cms.store.cost.cost_ExpensesListStore',{
	proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_ManualCostAction_getExpList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    },
	listeners:{ 	 
				'load':function(th,records,successful,eOpts ){
					var arrayObj = new Array();
					arrayObj[0]='area';
					arrayObj[1]='tray';
					arrayObj[2]='qty';
					arrayObj[3]='volume';
					arrayObj[4]='weight';
					arrayObj[5]='reserved1';
					arrayObj[6]='reserved2';
					countList2('grid_Exp_B903',arrayObj,'billingProject');
				}
			}
	  });
Ext.define('cms.view.cost.cost_ManualCostUI',{
	alias:'widget.cost_ManualCostUI',
	title: $i18n.titleB903, //手工生成费用明细
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu4',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.remoteCombo',
			'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
    	xtype : 'toolbar',
 	    region:'north',
   	   items : [{
   			    text : $i18n.refresh,//刷新
   			    iconCls : 'refresh',
   			    id:'refreshB903_1'
   		    },
   		    {
   		    	text : $i18n.find,//查找
			    iconCls : 'query',
   			    id:'queryB903_1'
   		    },
   		    {
   				text : $i18n.createCost,//生成费用
				icon: 'system/extjs/resources/icons/fam/application_split.png',
   				id:'retryB903_1'
   			}]	
        },{
	   		xtype : 'form',
	   		region : 'north',
	   		layout:'column',
	   		border:false,
	   		width:'100%',
	   		frame : true,
	   		items:[
	   		{
				xtype : 'radiogroup',
				id : 'rdoCheckTypeB903',
				margins: '3 3 3 3',
				fieldLabel :$i18n.checkType,//是否使用优惠策略
				width : 320,
				columns : 2,
				items : [
		        {
					boxLabel : $i18n.notUse,//不使用
					name : 'rd',
					inputValue : '0',
					checked:true
				},
				{
					boxLabel : $i18n.use,//使用
					name : 'rd',
					inputValue : '1'
				}
				]
		   }]
     },{
		xtype:'panel',
		region:'north',
		height: 60,
		layout: {
		    type: 'table',
	        columns: 4
		},
	    defaults : {
			xtype : 'textfield',
			margin : '3 3 3 3',
			labelAlign:'right',
			allowBlank: true,
			width : 280,
			labelWidth : 90
		},
		items:[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoB903',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	            beforeLabelTextTpl : required
			},{
				fieldLabel :$i18n.account_no, //科目代码
				id:'accountB903',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
 					type:'ajax',
 					method:'post',
 					url:'cost_ManualCostAction_getAccountNoForUI',
 					reader:{
						root:'rootList',
 						totalProperty:'totalCount'
						}
					}
			    })
			},{
     			fieldLabel :$i18n.billingProject, //计费项目
     			id:'billingProjectB903',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
	            colspan:2,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'cost_ExpensesListAction_getBillingProjectForUI',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    }).load(),
	            beforeLabelTextTpl : required
     		},{
	        	xtype:'datefield',
	            fieldLabel: $i18n.begin_date,
	            format : 'Y-m-d',
	            id: 'dtBeginDateExpB903',
	            beforeLabelTextTpl : required
	        }, {
	        	xtype:'datefield',
	            fieldLabel:  $i18n.end_time,
	            format : 'Y-m-d',
	            id: 'dtEndDateExpB903',
	            beforeLabelTextTpl : required
	        },{
				xtype : 'remoteCombo',
				fieldLabel : $i18n.source_plan_no,// 来源单号
				id:'sourceNoB903',
				store : Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url:'cost_ExpensesListAction_getSourceNoList.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
    				}
				})
			},{
	        	xtype: 'button',
	        	width : 80,
	        	name:'btnQueryB903',
	            text : $i18n.query
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Exp_B903',
	    multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
	    store:cost_ExpensesListStore,
	    columns:[
	    	   	    {			
	    				xtype : 'rownumberer',
	    				width : 30
	    		    },{
	    				width : 80,
	    				text : $i18n.warehouse,//仓别
	    				dataIndex:'warehouseNo'
	    		    },{
	    		    	width: 150,
	    	  		    text : $i18n.owner_no,  //货主编号
	    	  		    dataIndex:'ownerNoText'		
	    		    },{
		    	    	width: 130,
		      		    text :  $i18n.account_no,  //科目代码
		      		    dataIndex:'accountNoText'		
		    		},{
	    		    	width: 130,
	    	  		    text :  $i18n.billingProject,  //计费项目
	    	  		    dataIndex:'billingProjectText'		
	    			},{
	    		    	width: 100,
	    	  		    text :  $i18n.billingType,  //计费类型
	    	  		    dataIndex:'billingTypeText'		
	    			},{
	    				width: 130,
	    	  		    text : $i18n.articleFamilyNo1,  //商品群组
	    	  		    dataIndex:'familyNoText'		
	    			},{
	    				width: 150,
	    	  		    text : $i18n.source_plan_no,  //来源单号
	    	  		    dataIndex:'sourceNo'		
	    			},{
	    				width: 90,
	    	  		    text : $i18n.product_date,  //生成日期
	    	  		    dataIndex:'buildDate'		
	    			},{
	    				width: 130,
	    				align:'right',
	    	  		    text : $i18n.area1,  //面积(平方米)
	    	  		    dataIndex:'area'		
	    			},{
	    				width: 120,
	    				align:'right',
	    	  		    text : $i18n.tray,  //托盘数
	    	  		    dataIndex:'tray'		
	    			},{
	    				width: 120,
	    				align:'right',
	    	  		    text : $i18n.box,  //件数
	    	  		    dataIndex:'qty'		
	    			},{
	    				width: 120,
	    				align:'right',
	    	  		    text : $i18n.volume1,  //体积(立方米)
	    	  		    dataIndex:'volume'		
	    			},{
	    		    	width: 120,
	    				align:'right',
	    	  		    text : $i18n.weight1,  //重量(吨)
	    	  		    dataIndex:'weight'		
	    			},{
	    				width:100,
	    				text:$i18n.costPrice,//'预留字段1',货值
	    				align:'right',
	    				dataIndex:'reserved1'
	    			},{
	    				width:100,
	    				text:$i18n.cell_no,//'预留字段2',货位
	    				align:'right',
	    				dataIndex:'reserved2'
	    			},{
             		    width:90,
             		    text : $i18n.manage_status,  //状态
             		    dataIndex:'statusText'			
             	    },{
	    				width:100,
	    				text:$i18n.reserved3,//'预留字段3',
	    				hidden:$i18n.rsvBatch3Hidden,
	    				dataIndex:'rsvBatch3'
	    			},
	    			{
	    				width:100,
	    				text:$i18n.reserved4,//'预留字段4',
	    				hidden:$i18n.rsvBatch4Hidden,
	    				dataIndex:'rsvBatch4'
	    			},
	    			{
	    				width:100,
	    				text:$i18n.reserved5,//'预留字段5',
	    				hidden:$i18n.rsvBatch5Hidden,
	    				dataIndex:'rsvBatch5'
	    			},
	    			{
	    				width:100,
	    				text:$i18n.reserved6,//'预留字段6',
	    				hidden:$i18n.rsvBatch6Hidden,
	    				dataIndex:'rsvBatch6'
	    			}]/*,
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:cost_ExpensesListStore,
			displayInfo : true
	    }]*/
	},{
    	region:'south'
	}]
});