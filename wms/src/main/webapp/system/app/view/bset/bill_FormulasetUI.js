/**
 * 模块名称：计费公式管理UI
 * 模块编码：B101
 * 创建：chensr 
 */
var best_FormulasetStore=Ext.create('cms.store.bset.bill_FormulasetStore',{autoLoad:true});
Ext.define('cms.view.bset.bill_FormulasetUI',{
	alias:'widget.bill_FormulasetUI',
	title: $i18n.titleB101,//计费项目管理
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'best_FormulasetUI',
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.bdef_DefOwnerCombo',
			  'cms.view.common.wms_DefFieldValCombo',
			  'cms.view.common.wms_DefStrategyCombo'
	         ],
	         items:[
	                {
	             	    xtype:'commMenuWidget2',
	             	    id:'menuB101',
	             	    region:'north'
	                 },{
		         		xtype:'panel',
		         		region:'north',
		         		height: 35,
		         		layout: {
		       			    type: 'table',
		        	        columns: 3
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
		         			id:'ownerNoUIB101',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     					 proxy:{
			     					type:'ajax',
			     					method:'post',
			     					url:'bill_FormulasetAction_getOwnerNoForQuery',
			   						reader:{
			     						root:'rootList',
			     						totalProperty:'totalCount'
			    					}
			   					}
			   			    }).load(),
			   			    displayField : 'dropValue',
		    			    valueField : 'value'
	         			},{
		         			fieldLabel :$i18n.billingProject, //计费项目
		         			id:'billingProjectUIB101',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    					 proxy:{
				   					type:'ajax',
				    				method:'post',
				     				url:'bill_FormulasetAction_getBillingProjectForUI',
				     				reader:{
				   						root:'rootList',
				   						totalProperty:'totalCount'
			  						}
			   					}
			   			    }).load(),
			   			    displayField : 'dropValue',
			   			    valueField : 'value'
		         		}/*,{
	         				fieldLabel :$i18n.warehouse, //仓别		         				
		         			xtype:'textfield',
		         			id:'warehouseUIB101',
		         			value:Ext.get('warehouseNo').getValue(),
		         			readOnly:true
			     				  
		         		}*/]
		         	},{
	             	    xtype:'grid',
	             	    id:'formulasetUIB101',
	             	    region:'center',
	             	    store : best_FormulasetStore,
	             	    columns:[{			
	             	        xtype : 'rownumberer',
	             		    width : 30
	             	    },{
	             		    width:60,
	             		    text : $i18n.warehouse,  //仓别
	             		    dataIndex:'warehouseNo'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.owner_no,  //货主编号
	             		    dataIndex:'ownerNo'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.billingProject,  //计费项目
	             		    dataIndex:'projectText'			
	             	    },{
	             		    width:100,
	             		    text : "计费类型",  //计费类型
	             		    dataIndex:'billingTypeText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.articleFamilyNo1,  //商品群组
	             		    dataIndex:'familyText'			
	             	    },{
	             		    width:60,
	             		    text : $i18n.billingCycle,  //计费周期
	             		    dataIndex:'billingCycleText'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.billingFlag,  //计费方式
	             		    dataIndex:'billingFlagText'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.billingUnit,  //计费单位
	             		    dataIndex:'billingUnitText'			
	             	    },{
	             		    width:60,
	             		    text : $i18n.unitPrice,  //默认单价
	             		    dataIndex:'unitPrice'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.amountDate,  //结算日期
	             		    dataIndex:'balanceDayText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.endDay,  //截止日期
	             		    dataIndex:'endDateText'			
	             	    },{
	             		    width:240,
	             		    text : $i18n.appendCondition,  //附加条件
	             		    dataIndex:'appendConditionText'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.value1,  //值1
	             		    dataIndex:'appendValue1'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.value2,  //值2
	             		    dataIndex:'appendValue2'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.manage_status,  //状态
	             		    dataIndex:'statusText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.remark,  //备注
	             		    dataIndex:'remark'			
	             	    }],
	             	    dockedItems : 
	             	    [
	             		    {
	             		        xtype : 'pagingtoolbar',
	             			    store : best_FormulasetStore,
	             			    dock : 'bottom',
	             			    displayInfo : true
	             			}
	             	    ]
	             	},{
	             		region:'south'
	             }]          
});