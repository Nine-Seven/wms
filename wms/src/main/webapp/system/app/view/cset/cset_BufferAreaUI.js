/**
\ * 模块名称：暂存区维护UI
 * 模块编码：2701
 * 创建：panzhenxing
 */
var cset_BufferStore=Ext.create('cms.store.cset.cset_BufferStore',{autoLoad:true
		
});
Ext.define('cms.view.cset.cset_BufferAreaUI',{
	alias:'widget.cset_BufferAreaUI',
	title: $i18n.title2701, 
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.commMenu5',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.wms_DefStrategyCombo',
			'cms.view.common.cdef_DefAreaCombo','cms.view.common.cdef_DefWareCombo',
			'cms.view.common.cdef_DefStockCombo','cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.cdef_DefCellCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu2701',
	    region:'north'
	},{
		xtype:'panel',
			region:'north',
			height: 43,
			layout: {
			    type: 'table',
		        columns: 3
			},
		    defaults : {
				xtype : 'textfield',
				margin : '10 3 3 3',
				labelAlign:'right',
				allowBlank: true,
				width : 280,
				labelWidth : 90
			},

			
			items:[{
				xtype:'cdef_DefWareCombo',
				fieldLabel: $i18n.ware_no,//库区
				id : 'd4_wareno2701',
				store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
					 proxy:{
							type:'ajax',
							method:'post',
							url:'cset_BufferAction_getBufferWareCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
					},
					listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('d4_wareno2701').setValue(th.getAt(0).data.value);//控制器
								_myAppGlobal.getController('cms.controller.cset.cset_BufferController').d4_wareno2701change(Ext.getCmp('d4_wareno2701'));
							
								}
						}
						}
				}),
				beforeLabelTextTpl : required
			},{
				xtype:'cdef_DefAreaCombo',
				fieldLabel: '储区',
				id : 'd4_areano2701',
				store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',{
					 proxy:{
							type:'ajax',
							method:'post',
							url:'cset_BufferAction_getBufferAreaCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
					},
					listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('d4_areano2701').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.cset.cset_BufferController').d4_areano2701change(Ext.getCmp('d4_areano2701'));
						}
					}
					}
				}),
				beforeLabelTextTpl : required
			},
               {
			xtype:'cdef_DefStockCombo',
			fieldLabel: '通道',
			id : 'd4_stock2701',
			store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
				 proxy:{
						type:'ajax',
						method:'post',
						url:'cset_BufferAction_getBufferStockCombo',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
				},		
				listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(th.count()>0){
						Ext.getCmp('d4_stock2701').setValue(th.getAt(0).data.value);
						_myAppGlobal.getController('cms.controller.cset.cset_BufferController').d4_stock2701change(Ext.getCmp('d4_stock2701'));
					}
				}
				}
			}),
			beforeLabelTextTpl : required
			}
		
			]
		},
		{
		    xtype:'grid',
			store:cset_BufferStore,
			id:'costOtherList2701',
		    region:'center',
		    columns:[
		 		    {			
						xtype : 'rownumberer',
						width : 30
				    },
				    {
				    	width: 150,
			  		    text : $i18n.ware_no,  //库区
			  		    dataIndex:'wareNo'	
				    },{
				    	width: 150,
				  		text : $i18n.area_no01,  //储区
				  		dataIndex:'areaNo'
					},{
				    	width: 150,
				  		text : $i18n.stock_no01,  //通道
				  		dataIndex:'stockNo'	
					},{
				    	width: 110,
				  		text : $i18n.bufferName,  //暂存区编码
				  		dataIndex:'bufferName'		
					},{
				    	width: 110,
				  		text : $i18n.cell_no01,  //货位
				  		dataIndex:'cellNo'
					},{
				    	width: 110,
				  		text : $i18n.status_buffer,  //暂存区状态
				  		dataIndex:'statusText'		
					},{
				    	width: 110,
				  		text : $i18n.usevolumn,  //可用体积
				  		dataIndex:'useVolumn'		
					},{
				    	width: 110,
				  		text : $i18n.useweight,  //可用重量
				  		dataIndex:'useWeight'		
					},{
				    	width: 110,
				  		text : $i18n.useboxnum,  //可用箱数
				  		dataIndex:'useBoxnum'		
					}]
		},{
     		region:'south'
        }]
});