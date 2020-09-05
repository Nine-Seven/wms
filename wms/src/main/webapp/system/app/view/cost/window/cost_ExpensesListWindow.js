/**
 * 模块名称：未出账费用明细查询-查看清单
 * 模块编码：B403
 * hcx
 */
Ext.define('cms.view.cost.window.cost_ExpensesListWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.cost_ExpensesListWindow',
	id:'cost_ExpensesListWindow',
	layout:'border',
    height:500,
	width:1105,
	modal:true,
    items: [{

  	   xtype : 'toolbar',
 	   region:'north',
 	   items : [{
 			    text : $i18n.refresh,//刷新
 			    iconCls : 'refresh',
 			    id:'refreshB403'
 		    },
 		    {
 				text : $i18n.export1,//导出
 				iconCls : 'export',
 				id:'exportB403'
 			}]	
    },{
		xtype : 'form',
		region : 'north',
		width:'100%',
		frame : true,
		items : [ {
			layout : {
			type : 'table',
			columns : 6
			},
			xtype : 'container',
			defaults : {
				xtype : 'textfield',
				margin : '2 2 2 0',
				labelAlign : 'right'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : $i18n.owner_no,// 货主
				id : 'ownerNoB403_2',	
				hidden:true,
				readOnly:true
		    },{
				xtype : 'textfield',
				fieldLabel : $i18n.billingProject,// 计费项目
				id : 'billingProjectB403_2',	
				hidden:true,
				readOnly:true
		    },{
				xtype : 'textfield',
				fieldLabel : $i18n.serialno,//流水号
				id : 'serialNoB403_2',	
				hidden:true,
				readOnly:true
		    },/*{
				xtype : 'textfield',
				fieldLabel : $i18n.acdata_status,// 状态
				id : 'statusB403_2',	
				hidden:true,
				readOnly:true
		    },*/{
				xtype : 'datefield',
				fieldLabel : $i18n.product_date,//生成日期
				id : 'builDdateB403',
				format : 'Y-m-d'
			},{
				xtype : 'remoteCombo',
				fieldLabel : $i18n.source_plan_no,// 来源单号
				id:'sourceNoB403',
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
			}]
		}]

	},{
		  region:'center',
    	  xtype:'grid',
    	  id:'gridExpensesListB403',
    	  store:Ext.create('cms.store.cost.cost_ExpensesListStore',{
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
   					countList2('gridExpensesListB403',arrayObj,'billingProject');
   				}
   			}
    	  }),
    	  width:'100%',
    	  height:'100%',
    	  columns:[
    	   	    {			
    				xtype : 'rownumberer',
    				width : 30
    		    },/*{
    				width : 80,
    				text : $i18n.warehouse,//仓别
    				dataIndex:'warehouseNo'
    		    },{
    		    	width: 150,
    	  		    text : $i18n.owner_no,  //货主编号
    	  		    dataIndex:'ownerNoText'		
    		    },*/{
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
    			},
    			{
    				width:100,
    				text:$i18n.reserved3,//'预留字段3',
    				hidden:$i18n.rsvBatch3Hidden,
    				dataIndex:'reserved3'
    			},
    			{
    				width:100,
    				text:$i18n.reserved4,//'预留字段4',
    				hidden:$i18n.rsvBatch4Hidden,
    				dataIndex:'reserved4'
    			},
    			{
    				width:100,
    				text:$i18n.reserved5,//'预留字段5',
    				hidden:$i18n.rsvBatch5Hidden,
    				dataIndex:'reserved5'
    			},
    			{
    				width:100,
    				text:$i18n.reserved6,//'预留字段6',
    				hidden:$i18n.rsvBatch6Hidden,
    				dataIndex:'reserved6'
    			}]
			}]
});
