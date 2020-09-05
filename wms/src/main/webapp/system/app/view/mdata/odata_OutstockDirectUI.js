/**
 * 模块名称：移库发单
 * 模块编码：5201
 * 创建：zhouhuan
 */
var gridOdata_OutstockDirectM5201=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(Ext.getCmp('gridOdata_OutstockDirectM5201').getStore().count()>0){
						Ext.getCmp('gridOdata_OutstockDirectM5201').getSelectionModel().select(0);
					}
				}
			}
});
var gridOdata_OutstockDirectD5201=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockDirectAction_getOdata_OutstockDirectList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('cms.view.mdata.odata_OutstockDirectUI',{
	alias:'widget.odata_OutstockDirectUI',
	title:$i18n.odata_outstock_direct,//移库发单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefWorkerCombo'
	          ],
	  items:[
	       {
			   xtype : 'commMenuWidget4',
			   id:'menu5201',
			   region:'north'
		   },
		   {
			   xtype:'panel',
			   region:'north',
			   layout:'border',
			   height:150,
			   border : false,
			   items : [
			 		   {
					xtype : 'form',
					region : 'west',
					id:'formOdata_OutstockDirect5201',
					width:'35%',
					frame : true,
					items : [ {
						xtype : 'container',
						defaults : {
							margin : '2 2 2 2',
							labelAlign : 'right'
						},
				items : [{
							xtype : 'combo',
							fieldLabel : $i18n.owner,// 委托业主
							id:'cmbOwnerNo5201',
							margin:'20 10 10 50',
							//width:200,
							labelWidth:70,
							displayField: 'dropValue',
							valueField: 'value',
							beforeLabelTextTpl : required,
			 	    	    store:Ext.create("cms.store.common.comboStore",
									{
										proxy:{
											type:'ajax',
											method:'post',
											url:'mdata_PlanMAction_getOdata_GetCombo.action',
											reader:{
												root:'rootList',
												totalProperty:'totalCount'
											},
											extraParams:{
												flag : "1"
											}
										},
										//autoLoad:true,
										listeners:{  
										'load':function(th,records,successful,eOpts ){
											if(th.count()>0){
												Ext.getCmp('cmbOwnerNo5201').setValue(th.getAt(0).data.value);
											}
										}
										}
					   				}),
					   				beforeLabelTextTpl : required
						},{
					        xtype: 'radiogroup',
					        margin:'10 10 10 20',
					        id:'cmbOutstock_type5201',
					        fieldLabel: $i18n.movetype,//拣货类型
					        columns: 3,
					        items: [
					        	/*{ boxLabel: $i18n.out_replenishment, name: 'movetype', inputValue: '1' ,width:80},*///出货量补货
					            { boxLabel: $i18n.movelibrary, name: 'movetype', inputValue: '4' ,checked: true,width:80},//人工移库
					            { boxLabel: $i18n.replenishment, name: 'movetype', inputValue: '3',width:80}//安全量补货
					        ],
					        beforeLabelTextTpl : required
					    },{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.locate_no,// 波次号
							margin:'10 10 10 20',
							id:'cmbWave_no5201',
							hidden:true,
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_OutstockDirectAction_getOdata_GetWaveCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbWave_no5201').setValue(th.getAt(0).data.value);
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						}]
					}]
				},{
					xtype:'grid',
					width:'65%',
					region : 'east',
					store:gridOdata_OutstockDirectM5201,
					id:'gridOdata_OutstockDirectM5201',
					hegith:300,
					columns:[{			
						xtype : 'rownumberer',
						width : 30
					},{
						width : 150,
						id:'areaNo5201_2',
						text : $i18n.s_area_no,//储区代码
						dataIndex : 'areaNo'
					},{
						width:150,
						text :$i18n.area_name,//储区名称
						id:'areaName5201_2',
						dataIndex : 'areaName'
					},{
						width:150,
						text :$i18n.operate_type,//储区名称
						id:'operateType5201_2',
						dataIndex : 'operateType'
					}],
					dockedItems : [
			    	        {
				    	        xtype : 'pagingtoolbar',
				    	        store:gridOdata_OutstockDirectM5201,
				    	        dock : 'bottom',
				    	        displayInfo : true
				    	    } ]
				}] 
		   },{
				xtype:'grid',
				width:'100%',
				id:'gridOdata_OutstockDirectD5201',
				store:gridOdata_OutstockDirectD5201,
				region : 'center',
				columns : [ {
						xtype : 'rownumberer',
						width : 30
					}, {
						width : 160,
						text : $i18n.plan_no,//计划单号
						dataIndex : 'expNo'
					}, {
						width : 120,
						text : $i18n.article_no,//商品编码
						dataIndex : 'articleNo'
					}, {
						width : 100,
						text : $i18n.owner_article_no,//货主商品编码
						dataIndex : 'ownerArticleNo'
					},{
						width : 160,
						text : $i18n.article_name,//商品名称
						dataIndex : 'articleName'
					}, {
						width : 120,
						text : $i18n.s_cell_no,//来源储位
						dataIndex : 'SCellNo'
					   
					}, {
						width : 140,
						text : $i18n.barcode,//商品条码
						dataIndex : 'barcode'
					}, {
						width : 120,
						text : $i18n.a_cell_no,//目的储位
						dataIndex : 'DCellNo'
					}, {
						width : 80,
						text : $i18n.locate_qty,//定位数量
						dataIndex : 'locateQty'
					}, {
					    width:80,
					    text:$i18n.packingUnit,//包装单位
					    id:'packingUnit_5201',
					    dataIndex:'packingUnit'
					},{
						width:80,
						text:$i18n.packingSpec,//规格
						id:'packingSpec_5201',
						dataIndex:'packingSpec'
					},{
						width : 80,
						text : $i18n.packing_qty,//包装数量
						dataIndex : 'packingQty'
					}, {
						width : 80,
						text : $i18n.operate_type,//作业类型
						dataIndex : 'operateType'
					} ]
					/*dockedItems : [
			    	        {
				    	        xtype : 'pagingtoolbar',
				    	        dock : 'bottom',
				    	        displayInfo : true
				    	    } ]*/
			},{
				xtype : 'form',
				region : 'south',
				id:'formComdition5201',
				frame : true,
				layout:'column',
				items : [{
					layout:'column',
					xtype : 'container',
					defaults : {
						labelWidth : 90,
						margin : '2 2 2 2',
						labelAlign : 'right',
						width : 300
					},
					items : [
					{
				        xtype: 'radiogroup',
				        id : 'radioCelltype5201',
				        fieldLabel: $i18n.celltype,//成单类型
				        columns: 3,
				        items: [
				            { boxLabel: $i18n.s_area, name: 'celltype', inputValue: '1' ,checked: true,width:80},//按源储区
				            { boxLabel: $i18n.a_area, name: 'celltype', inputValue: '2',width:80},//按目的储区
				            { boxLabel: $i18n.s_a_area, name: 'celltype', inputValue: '3',width:80}//同源同目的
				        ],
				        beforeLabelTextTpl : required
				    },{
						xtype : 'bdef_DefWorkerCombo',
						margin:'3 0 0 20',
						id:'cmbOutstockworker5201',
						labelAlign:'right',
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
						fieldLabel : $i18n.outstockworker,// 下架人员
						beforeLabelTextTpl : required
					}]
				}, {
					xtype : 'button',
					margin:'3 0 0 10',
					name:'create5201',
					id:'butCreate5201',
					text : $i18n.assign//发单
				}]
			},{
			xtype : 'panel',
			id : 'msterForm5201',
			region:'south'
//			html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
//					+ 'background-color:#C1D5ED; text-align: left;">'
//					+ '<span><label>新增人:</label><label id="Editor5201"></label> </span> '
//					+ '<span><label>&nbsp;&nbsp;&nbsp;新增时间：</label><label id="EditDatev5201"></label></span>'
//					+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="Checker5201"></label> </span> '
//					+ '<span><label>&nbsp;&nbsp;&nbsp;修改时间：</label><label id="Checkdate5201"></label> </span></div>'
		}
	       ]

});