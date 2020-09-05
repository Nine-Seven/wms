
/**
 * 模块名称：员工资料维护
 * 模块编码   商品主档：1401  商品包装:1401_d2   商品条码:1401_d3
 * @author JUN
 */
/*var storePacking1401_d2=Ext.create('cms.store.bdef.bdef_ArticlePackingStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1401_d2').getStore().count()>0){
				Ext.getCmp('grid_01_1401_d2').getSelectionModel().select(0);
				Ext.getCmp('grid_01_1401_d2').fireEvent('itemclick','');
			}
		}
	}
});*/
/*var storeBarcode1401_d3=Ext.create('cms.store.bdef.bdef_ArticleBarcodeStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1401_d3').getStore().count()>0){
				Ext.getCmp('grid_01_1401_d3').getSelectionModel().select(0);
				Ext.getCmp('grid_01_1401_d3').fireEvent('itemclick','');
				
			}
		}
	}
});*/
/*var bset_Role_ListStore=Ext.create('cms.store.bset.bset_Role_ListStore',{
	autoLoad:true
});*/
Ext.define('cms.view.bdef.window.bdef_DefWorkerAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.bdef_DefWorkerAddOrEditWindow',
	layout:'border',
	width : 650,
	height : 400,
	modal : true,
	id : 'bdef_DefWorkerAddOrEditWindow',
	items : [
	{
		xtype : 'form',
		id : 'formBdef_DefWorker1101',
		region : 'north',
		height : 30,
		frame : true,
		layout: 
        {
        	type: 'table',
        	columns: 2
        },
		defaults : 
        {
        	xtype : 'textfield',
        	labelWidth : 70,
        	labelAlign:'right'			
 	    },
		items:[
		{
			fieldLabel:$i18n.worker_no,//员工代码
	        id:'txtWorkerName1101',
	        allowBlank : false,
	        beforeLabelTextTpl : required
		}]
	},
	
	{
		xtype : 'tabpanel',
	    region:'center',
	    id:'tabPid1101',
	    flex : 4,
	    items : [
	    {
	    	title:"员工基本信息",			//员工基本信息
	        layout:'border',
	        id:'tabPid1101_01',
	        items:[
            {

        		xtype : 'form',
        		baseCls:'my-panel-no-border',
        		id : 'formBdef_DefWorker1102',
        		region : 'north',
        		margin:'2 2 2 2 ',
        		frame : true,
        		layout: 
                {
                	type: 'table',
                	columns: 2
                },
        		defaults : 
                {
                	xtype : 'textfield',
                	labelWidth : 70,
                	labelAlign:'right'			
         	    },
        		items:[
        		{	
        			xtype:'textfield',
	  			  	fieldLabel : "员工姓名",
	  			  	id : 'txtWorkerAlias1401',
					labelAlign:'right',
					allowBlank : false,
					beforeLabelTextTpl : required,
				},{	
	                	xtype:'textfield',
   	                	fieldLabel : "口令",
   	                	id : 'txtWorkerAlias1402',
		       	        labelAlign:'right',
		       	        allowBlank : false,
		       	        beforeLabelTextTpl : required,
		       	       /* renderer:function(){		
							return '******';
						}*/
	       	        },{	
			       	    	xtype:'combo',
				       	    fieldLabel : "性别",
					       	id : 'cmbSex1101',
					       	labelAlign:'right',
					       	displayField: 'name',
					       	valueField: 'id',
						    store:Ext.create('Ext.data.Store', {
							     fields: ['id', 'name'],
								 data:[
								        {id:'0',name:'男'},
										{id:'2',name:'女'}
								     ]
							    })
				       	    },{	
					       	        xtype:'textfield',
						       	    fieldLabel : "电话",
							       	id : 'txtWorkerAlias1403',
							       	labelAlign:'right',
						    },{	
					       	        xtype:'textfield',
						       	    	fieldLabel : "地址",
							       	    id : 'txtWorkerAlias1404',
							       	    labelAlign:'right',
						       	    },{	
   						       	        xtype:'textfield',
   						       	    	fieldLabel : "职务",
   							       	    id : 'txtWorkerAlias1405',
   							       	    labelAlign:'right',
   						       	    },{	
   						       	    	xtype:'wms_DefFieldValCombo',
   						       	    	fieldLabel : "员工类型",
   							       	    id : 'txtWorkerAlias1406',
   							       	    store:Ext.create("cms.store.common.comboStore").load(
   									        {
   									        	params:{str:"BDEF_DEFWORKER,AUTHORITY_TYPE"}
   									        }),
   									     allowBlank: false,
   								         beforeLabelTextTpl : required,
   								         labelAlign:'right',
  							       	     maxLength:70,
  							       	     labelWidth : 70,
   						       	    },{	
   						       	    	xtype:'wms_DefFieldValCombo',
   						       	    	fieldLabel : "状态",
   							       	    id : 'txtWorkerAlias1407',
   							       	    store:Ext.create("cms.store.common.comboStore").load(
   									        {
   									        	params:{str:"N,DEF_STATUS"}
   									        }),
   									        allowBlank : false,
   							       	    labelAlign:'right',
   							       	    maxLength:70,
   							       	    labelWidth : 70,
   						       	    }]
            }]
	    },
	    
	    {
	    	title:"员工角色指定",			//员工角色指定
	        layout:'border',
	        id:'tabPid1101_02',
	        items:[
		   	    	{
					xtype:'grid',
					id:'bset_Role_ListGrid21',	//6-30修改的
					title : $i18n.title1101tab2grid1,	//角色列表
					//store : bset_Role_UserStore,
					store : Ext.create('cms.store.bset.bset_Role_UserStore',{autoLoad:true}),
					region:'west',
					width : '100%',
					columns:[{
		        	  	xtype: 'checkcolumn',
						width : 50,
						columnHeaderCheckbox: true,//必须定义如下store
						store: Ext.data.StoreManager.lookup('bset_Role_UserStore'),
						dataIndex:'flag'
			        },{
						width : 60,
						text : $i18n.partno,//角色编码
						dataIndex : 'roleId',
						id : 'roleId'
						},{
						width : 500,
						text : $i18n.partname,//角色名称
						dataIndex : 'roleName'
						//id : 'roleId'
						}]
				}]
	    },
	    
	    {
	    	title:"用户与货主",			
	        layout:'border',
	        id:'tabPid1101_03',
	        items:[
		   	    	{
							xtype : 'grid',
							id : 'grid_03_1101',
							title : $i18n.title1201tab2grid1,//用户与货主
							region:'west',
							width : '100%',
							store : Ext.create('cms.store.bdef.bdef_DefWorkerOwnerStore',{autoLoad:true}),
							columns : [{
				        	  	xtype: 'checkcolumn',
								width : 50,
								columnHeaderCheckbox: true,//必须定义如下store							
								store: Ext.data.StoreManager.lookup('bdef_DefWorkerOwnerStore'),
								dataIndex:'flag'
					        }, {
								width : 60,
								text : $i18n.owner_no,//委托业主代码 
								dataIndex : 'ownerNo',
								id : 'ownerNo'
							},{
								width : 520,
								text : $i18n.owner_name,//委托业主名称
								dataIndex : 'ownerName',
								id : 'ownerName'
							}]
						}]
	    },
	    
	    {
	    	title:"用户与仓别",			//用户与仓别
	        layout:'border',
	        id:'tabPid1101_04',
	        items:[
	   	    	{
	   				xtype : 'grid',
	   				id : 'grid_04_1101',
	   				title : $i18n.title1201tab1grid1,//仓别列表
	   				region:'west',
	   				width : '100%',
	   				store : Ext.create('cms.store.bdef.bdef_DeflocStore',{
						proxy:{
							type:'ajax',
							method:'post',
							url:'bdef_DefWorkerAction_getWarehouseListByWorkerNo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				},
	    				autoLoad:true,
	    				storeId:'bdef_DefWorkerLocStore',
					}),
	   				columns : [{
		        	  	xtype: 'checkcolumn',
						width : 50,
						columnHeaderCheckbox: true,//必须定义如下store
						store: Ext.data.StoreManager.lookup('bdef_DefWorkerLocStore'),
						dataIndex:'flag',
			        }, {
	   					width : 60,
	   					text : $i18n.warehouse_no,//仓别代码
	   					dataIndex : 'warehouseNo'
	   				},{
	   					width : 520,
	   					text : $i18n.warehouse_name,//仓别名称
	   					dataIndex : 'warehouseName'
	   				}]
	   			}]
	    }
	    ]
	},{
    	region:'south',
    	xtype:'commMenuWidget5',
    	border:0,
    	id:'menuWidget51101'
    }
	]
});








