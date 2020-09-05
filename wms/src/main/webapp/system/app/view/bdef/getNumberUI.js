//数据导入、文件上传下载
Ext.define('cms.view.bdef.getNumberUI',{
	alias:'widget.getNumberUI',
	title:'取号',
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.bdef_DefCustCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1Z01',
	    region:'north',
	    heigth:'5%'
    },{

	    xtype:'form',
	    region:'north',
	    heigth:'60%',
	    margin : '0 3 3 15',
	    id:'getNumberNoForm',
	    frame:true,
	    items:[
	    {
            xtype:'fieldset',
  			layout: {
  				type: 'table',
      	        columns: 2
      	    },
      	    defaults:{
    	   		xtype:'textfield',
    	   		labelAlign:'right',
    	   		labelWidth:120
       	    },
       	    items:[{
       	    		xtype:'bdef_DefOwnerCombo',
					fieldLabel:$i18n.owner,//委托业主
		 	    	id:'ownerNo1601',
		 	    	allowBlank:false,
		 	    	beforeLabelTextTpl : required,
		 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
				},{
		        	xtype:'combo',
					fieldLabel : '工作站',
					id:'dock1Z01',
					displayField: 'workstationName',
					valueField: 'workstationNo',
		        	store:Ext.create('Ext.data.Store', {
					       fields: ['workstationNo','warehouseNo','workstationName'],
						   proxy: {
						        type: 'ajax',
						        url: 'bdef_DefWorkstationAction_getWorkstationList.action',
						        reader: {
						            type: 'json',
						            root: 'rootList'
						        }
						   }
					  }),
					  listConfig : {
							getInnerTpl : function() {
						    	return '[{workstationNo}]{workstationName}';
							}
					  },
					  allowBlank : false,
				      beforeLabelTextTpl : required
		 	    }]
	   },{
   	       xtype:'fieldset',
   	       title:'标签用途',
      	   layout: {
      		   type: 'table',
   	           columns: 2
   	       },
   	       defaults:{
    	   		xtype:'textfield',
    	   		margin:'30 5 5 5',
    	   		labelAlign:'right',
    	   		labelWidth:120
       	   },
		   items:[
		   {    
			   id:'useType1Z01',
	    	   xtype:'radiogroup',
	    	   width:360,
	           columns: 3,
	           border:1,
	           vertical: false,
	    	   items:[{
	    			boxLabel: '收货标签', name:  'rb',  inputValue: '0',checked:true
	    	   },{
	    			boxLabel: '客户标签', name: 'rb',  inputValue: '1'
	    	   },{
	    			boxLabel: '移库标签', name: 'rb',  inputValue: '3'
	    	   }]	    	
		   },{
				xtype:'bdef_DefCustCombo',
	  			fieldLabel:$i18n.cust,//客户编号
	  			id:'cust1Z01',
	  			store:Ext.create('cms.store.bdef.bdef_DefCustComboStore').load(),
      		 	displayField : 'dropValue',
      			valueField : 'value',	   
		   }]
        },{
           title:'容器类型',
    	   xtype:'fieldset',
       	   layout: {
    	       type: 'table',
    	       columns: 1
    	   },
    	   defaults:{
    		   xtype:'textfield',
    		   margin:'5 4 1 4',
    		   labelAlign:'center',
    		   labelWidth:200
 	       },
 		   items:[
 		      {    
 				   id:'containType1Z01',
 		    	   xtype:'radiogroup',
 		    	   width:360,
 		           columns: 3,
 		           border:1,
 		           vertical: false,
 		    	   items:[{
 		    			boxLabel: '栈板', name:  'crb',  inputValue: '0',checked:true
 		    	   },{
 		    			boxLabel: '箱型', name: 'crb',  inputValue: '1'
 		    	   }]	    	
 			 }]
         
        },{
            xtype:'fieldset',
  			layout: {
  				type: 'table',
      	        columns: 4
      	    },
      	    defaults:{
    	   		xtype:'textfield',
    	   		labelAlign:'right',
    	   		labelWidth:120
       	    },
       	    items:[{
				fieldLabel:'批号',//批号
				id : 'lotNo1Z01',	
		        beforeLabelTextTpl : required
			},{
				xtype:'numberfield',
				fieldLabel:'数量',//批号
				id : 'numberNo1Z01',	
		        beforeLabelTextTpl : required
			},{
				xtype: 'button',
            	text: '取号',
            	margin : '0 3 3 15',
            	width:100,
            	id:'getNumber1Z01'
			},{
				xtype: 'button',
            	text: '打印',
            	margin : '0 3 3 15',
            	width:100,
            	id:'print1Z01'
			}]  	
        }]   	
    },{

    	xtype:'grid',
	    id:'grid_01_1Z01',
//	    store:bset_Printer_GroupStore,
	    width:'100%',
	    region:'west', 
	    columns:[
	    {			
            xtype : 'rownumberer',
		    width : 30
	    },
	    {
	        width : 120,
		    text : '任务号',
		    dataIndex : 'taskNo'
	    },
	    {
		    width:120,
		    text :'标签号',
		    dataIndex:'labelNo'			
	    }] 
    }]
});