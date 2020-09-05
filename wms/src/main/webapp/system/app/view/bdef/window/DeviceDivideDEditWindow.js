Ext.define('cms.view.bdef.window.DeviceDivideDEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.DeviceDivideDEditWindow',
	id : 'DeviceDivideDEditWindow',
	width : 600,
	height : 350,
	layout:'border',
	modal:true,
	items:[{
		xtype:'form',
		region:'center',
		id:'DeviceDivideDEditForm1S01',
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
               	id:'DeviceDivideDF11S01',
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
						id : 'DeviceDivideGroupDEdit1S01',
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
						    }
						}),
						displayField : 'dropValue',
     				    valueField : 'value',
     				    readOnly:true,
			     	    allowBlank : false,
			     	    beforeLabelTextTpl : required
					},
				    {
						xtype:'combo',
						fieldLabel: '设备',
						id : 'deviceNoDEdit1S01',
						store:Ext.create('cms.store.common.comboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'Divice_DivideAction_getDeviceDivideMCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						}),
						displayField : 'dropValue',
    				    valueField : 'value',
    				    readOnly:true,
			     	    allowBlank : false,
			     	    beforeLabelTextTpl : required
					},{
						fieldLabel: '货位',
						id : 'deviceCellNo1S01',
						beforeLabelTextTpl : required,
						readOnly:true
					},{
						xtype:'wms_DefFieldValCombo',
						colspan:2,
						fieldLabel: $i18n.cell_status,
						id : 'statusDEdit1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"CDEF_DEFCELL,CELL_STATUS"}
		   				}),
						beforeLabelTextTpl : required
					
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.check_status,
						id : 'checkStatusDEdit1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"CDEF_DEFCELL,CHECKSTATUS"}
		   				}),
						beforeLabelTextTpl : required,
						hidden:true					
					},{
						fieldLabel: '位',
						id : 'bayX1S01',
						beforeLabelTextTpl : required,
						readOnly:true
					},{
						fieldLabel: '层',
						id : 'stockY1S01',
						beforeLabelTextTpl : required,
						readOnly:true
					}] 
		       },
		       {
	               	xtype:'fieldset',
	            	id:'DeviceDivideDF21S01',
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
						fieldLabel: $i18n.max_qty,
						id : 'maxQtyEdit1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_volume,
						id : 'maxVolumeEdit1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_weight,
						id : 'maxWeightEdit1S01',
						beforeLabelTextTpl : required
					},{
						fieldLabel: $i18n.max_case,
						id : 'maxCaseEdit1S01',
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_supplier,
						id : 'mixSupplierEdit1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_SUPPLIER"}
		   				}),
						beforeLabelTextTpl : required
					},{
						xtype:'wms_DefFieldValCombo',
						fieldLabel: $i18n.mix_flag,
						id : 'mixFlagEdit1S01',
						store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"N,MIX_FLAG"}
		   				}),
						beforeLabelTextTpl : required
					},{
						fieldLabel: '拣货顺序',
						id : 'pickOrderD1S01',
						beforeLabelTextTpl : required,
						hidden:true
					},{
						fieldLabel: '创建人',
						id : 'rgstName1S01',
						beforeLabelTextTpl : required,
						hidden:true
					},{
						fieldLabel: '创建时间',
						id : 'rgstDate1S01',
						beforeLabelTextTpl : required,
						hidden:true
					}]        
		       }]
	},
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidgetDEdit1S01'
	}]
});
