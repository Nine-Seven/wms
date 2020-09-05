Ext.define('cms.view.bdef.window.DeviceDivideDAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.DeviceDivideDAddOrEditWindow',
	id : 'DeviceDivideDAddOrEditWindow',
	width : 600,
	height : 470,
	layout:'border',
	modal:true,
	items:[{
		xtype:'form',
		region:'center',
		id:'DeviceDivideDForm1S01',
		frame : true,
		layout:'form',
		defaults : {
			 xtype : 'textfield',
             labelWidth : 100,
             width:250,
             labelAlign:'right'	,
             margin : '5 5 5 5'
		},
		items:[{
               	xtype:'fieldset',
               	title:$i18n.baseInfo,
                layout: 
                {
                   type: 'table',
                   columns: 2
                },
                defaults : 
                {
                	xtype : 'textfield',
                    labelWidth : 100,
                    width:250,
                    labelAlign:'right'	
	  	        },
	       	       items:[{
						xtype:'combo',
						fieldLabel: '设备组',
						id : 'DeviceDivideGroupD1S01',
						store:Ext.create("cms.store.common.comboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url : 'Divice_DivideAction_getDeviceDivideGroupCombo.action',
						        reader: {
						    		type:'json',
						            root: 'rootList',    
						            totalProperty: 'totalCount'
						        } 
						    },
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('DeviceDivideGroupD1S01').setValue(th.getAt(0).data.value);
									}
								}
							}
						}),
						displayField : 'dropValue',
     				    valueField : 'value',
			     	    allowBlank : false,
			     	    beforeLabelTextTpl : required
					},
				    {
						xtype:'combo',
						fieldLabel: '设备',
						id : 'deviceNoD1S01',
						store:Ext.create('cms.store.common.comboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'Divice_DivideAction_getDeviceDivideMCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('deviceNoD1S01').setValue(th.getAt(0).data.value);
									}
								}
							}
						}),
						displayField : 'dropValue',
    				    valueField : 'value',
			     	    allowBlank : false,
			     	    beforeLabelTextTpl : required
					}] 
		       },{
	               	xtype:'fieldset',
	               	title:$i18n.cellSet,
	               	id:'setCellAndStock',
	                layout: 
	                {
	                   type: 'table',
	                   columns: 2
	                },
	                defaults : 
	                {
	                	 xtype : 'textfield',
	                     labelWidth : 100,
	                     width:250,
	                     labelAlign:'right'
		  	       	 },
		       	     items:[
					 {
						 fieldLabel: $i18n.perfix,
						 id : 'perfix1S01',
						 colspan:2
					 },{
						 fieldLabel:$i18n.bayXMin,
						 id : 'bayXMin1S01',						
						 beforeLabelTextTpl : required
						
					 },{
						  fieldLabel:$i18n.bayXmax,
						  id : 'bayXMax1S01',
						  beforeLabelTextTpl : required
							  
					},{
						fieldLabel:$i18n.floorMin,
						id : 'floorMin1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.floorMax,
						id : 'floorMax1S01',
						beforeLabelTextTpl : required
					}
//					,{
//						fieldLabel: $i18n.showCell,
//						id : 'showCell1S01',
//						colspan:2,
//						width:500,
//						readOnly:true,
//						beforeLabelTextTpl : required
//					}
					] 
		       },
		       {
	               	xtype:'fieldset',
	               	title:$i18n.cellAttribute,
	                layout: 
	                {
	                   type: 'table',
	                   columns: 2
	                },
	                defaults : 
	                {
	                   xtype : 'textfield',
	                   labelWidth : 100,
	                   width:250,
	                   labelAlign:'right'			
		  	       	 },
		       	     items:[{
						xtype:'wms_DefFieldValCombo',
						colspan:2,
						fieldLabel: $i18n.cell_status,
						id : 'statusD1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"CDEF_DEFCELL,CELL_STATUS"}
		   				}),
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_qty,
						id : 'maxQty1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_volume,
						id : 'maxVolume1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_weight,
						id : 'maxWeight1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_case,
						id : 'maxCase1S01',
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_supplier,
						id : 'mixSupplier1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_SUPPLIER"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_flag,
						id : 'mixFlag1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_FLAG"}
		   				}),
						beforeLabelTextTpl : required
					}]        
		       }]
	},
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidgetD1S01'
	}]
});
