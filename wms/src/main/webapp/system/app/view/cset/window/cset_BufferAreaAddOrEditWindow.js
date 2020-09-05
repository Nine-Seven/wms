Ext.define('cms.view.cset.window.cset_BufferAreaAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.cset_BufferAreaAddOrEditWindow',
	layout : 'border',
	id : 'cset_BufferAreaAddOrEditWindow',
	height:300,
	width:600,	
	modal:true,
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.cdef_DefAreaCombo',
			  'cms.view.common.cdef_DefWareCombo',
			  'cms.view.common.cdef_DefStockCombo',
			  'cms.store.common.comboStore'
			 
	],
	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'cset_BufferAreaAddOrEditForm',
 	  	frame : true,
    items:[
    	{	xtype:'fieldset',
		    layout: 
		    {
		        type: 'table',
		        columns: 2
		    },
		    defaults : 
		    {
		        xtype : 'textfield',
                labelWidth : 100,
                margin:'2 5 5 5',
	            labelAlign:'right'			
		    },
		    items:[
            {
				xtype:'cdef_DefWareCombo',
				fieldLabel: $i18n.ware_no,
				id : 'd4_wareno2701_1',
				store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
					 proxy:{
							type:'ajax',
							method:'post',
							url:'cset_BufferAction_getBufferAddWareCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
					},
					listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('d4_wareno2701_1').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.cset.cset_BufferController').d4_wareno2701_1change(Ext.getCmp('d4_wareno2701_1'));
							}
						}
						}
				}).load(),
				beforeLabelTextTpl : required
			},{
				xtype:'cdef_DefAreaCombo',
				fieldLabel: '储区',
				id : 'd4_areano2701_1',
				store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',{
					 proxy:{
							type:'ajax',
							method:'post',
							url:'cset_BufferAction_getBufferAddAreaCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
					},
					listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('d4_areano2701_1').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.cset.cset_BufferController').d4_areano2701_1change(Ext.getCmp('d4_areano2701_1'));
						}
					}
					}
				}),
				beforeLabelTextTpl : required
			},{
				xtype:'cdef_DefStockCombo',
				fieldLabel: '通道',
				id : 'd4_stockno2701_1',
				store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
					 proxy:{
							type:'ajax',
							method:'post',
							url:'cset_BufferAction_getBufferAddStockCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
					},		
					listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('d4_stockno2701_1').setValue(th.getAt(0).data.value);
						}
					}
					}
				}),
				beforeLabelTextTpl : required
				},{
				fieldLabel : $i18n.bufferName,//暂存区编码
				minValue:0,
				id : 'bufferName2701' ,
				beforeLabelTextTpl : required
		    },{

	 	    	fieldLabel:$i18n.cell_no,//货位
	 	    	id:'cell_no2701',
	 	    	maxLength:24,
	 	    	xtype: 'cdef_DefCellCombo',
                displayField : 'value',
    			valueField : 'value',
    			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
    			   proxy:{
						type:'ajax',
						method:'post',
						url:'cset_BufferAction_getBufferAddCellCombo.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
					,
				   listConfig: {
			           loadingText: '查询中...',
			           emptyText: '没有找到相应的数据！' ,
			           getInnerTpl: function() {
			        	   return '{value}';
			           }
				   }
		    }).load() ,
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required
	 	    
		    },{
				xtype : 'wms_DefFieldValCombo',
				fieldLabel : $i18n.status_buffer,//暂存区状态
				minValue:0,
				id : 'status2701',
				store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"OSET_BUFFER,STATUS"}
					   	}),
					   	beforeLabelTextTpl : required
		    },{
		    	xtype:'numberfield',
				fieldLabel : $i18n.usevolumn,//可用体积
				minValue:0,
				id : 'useVolumn2701'
		    },{
		    	xtype:'numberfield',
				fieldLabel : $i18n.useweight,//可用重量
				minValue:0,
				id : 'useWeight2701'
		    },{
		    	xtype:'numberfield',
				fieldLabel : $i18n.useboxnum,//可用箱数
				minValue:0,
				id : 'useBoxnum2701'
		    }]
		  }]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget2701'
 	}]
});
