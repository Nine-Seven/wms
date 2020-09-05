/**
 * 模块名称：返配手建单
 * 模块编码：6101
 * 创建：周欢
 */
var ridata_Untread_MStore=Ext.create('cms.store.ridata.ridata_UntreadMStore',{autoLoad:true});
Ext.define('cms.view.ridata.ridata_UntreadUI',{
	alias:'widget.ridata_UntreadUI',
	title:$i18n.title6101,//返配手建单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			  'cms.view.common.commMenu',
	          'cms.view.common.commMenu3',
	          'cms.view.common.commMenu6',
	          'cms.view.common.bdef_DefArticleCombo',
	          'cms.view.common.bdef_DefCustCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo'
	          ],
	items:[
    {
	    xtype:'tabpanel',
	    id:'tabPId1200000',
	    region:'center',
	    items:[
		{
        	title: $i18n.titleM,//主档
		    id:'tabPid6101',
		    itemId:'tabPid6101i',
		    layout:'border',
		    items: [
		    {
	   	        xtype:'commMenuWidget3',
	   	     items: [{
					text : $i18n.find,//查找
					iconCls : 'query',
					name : 'detailQuery'
				},{
					text : '导入',
					icon:  'system/extjs/resources/icons/fam/application_split.png',
					name : 'upload'
				},{
					text : '打印',
					iconCls : 'print',
					name : 'detailPrint'
				},{
					  text : '取消订单',    //8-17修改
					  iconCls : 'undo',
					  id : 'undo6101'
				    }/*,{
						  text : '取消订单',
						  iconCls : 'undo',
						  id : 'cancel6101'
					    }*/],
	            region:'north'
   	        },{
   		        region:'center',
          		xtype:'grid',
          		id:'gridRidata_UntreadM6101',
          		store:ridata_Untread_MStore,
          		columns:[
          		{
              		xtype:'rownumberer',
        	  		width:30
          		},{
        	  		width:200,
        	 		 text:$i18n.untread_no,//返配单号
        	  		dataIndex:'untreadNo'
          		},{
        	  		width:130,
        	  		text:$i18n.qualityType,//品质类型
        	  		dataIndex:'qualityText'
          		},{
        	  		width:130,
        	  		text:$i18n.p_no,//原返配单号
        	 		 dataIndex:'poNo'
          		},{
        	  		width:130,
        	  		text:$i18n.cust_no,//客户编号
        	  		dataIndex:'custNo'
          		},{
        	 		 width:200,
        	  		text:$i18n.cust_name,//客户名称
        	  		dataIndex:'custName'
                },{
        	  		width:80,
        	  		text:$i18n.serialno,//流水号
        	  		dataIndex:'serialNo'
          		},{
          			width:130,
        	  		text:'派车单号',//派车单号
        	 	    dataIndex:'carPlanNo'
          		},
          		{
        	  		width:80,
        	  		text:$i18n.status_desc,//验收单状态
        	  		dataIndex:'statusText'
          		}],
           		dockedItems:[{
        			xtype : 'pagingtoolbar',
        			dock : 'bottom',
        			store:ridata_Untread_MStore,
        			displayInfo : true
          		}]
    		}]
		},{
			title: $i18n.titleD,//明细
			id:'tabPidd2_6101',
			itemId:'tabPidd2_6101i',
			layout:'border',
			items:[
			{
				xtype:'commMenuWidget',
				region:'north',
				id:'menu6101'
			},{
			    xtype : 'form',
			    id : 'formRidata_UntreadM6101',
			    region : 'north',
			    frame : true,
			    layout : {
				    type : 'table',
				    columns : 3
			    },
			    defaults : {
				  xtype:'textfield',
				  abelWidth : 90,
				  margin : '2 2 4 2',
				  labelAlign : 'right'
			    },
			    items : [{
					fieldLabel:$i18n.untread_no,//返配单号
					id:'txtUntread_id6101',
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
				  	xtype:'bdef_DefOwnerCombo',
				  	fieldLabel:$i18n.owner,//委托业主
				  	id:'cmbOwnerNo6101',
				  	displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'bdef_DefOwnerAction_queryOwnerCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
					}).load(),
	    	        readOnly:true,
	    	        allowBlank : false,
	    	        beforeLabelTextTpl : required
			    },{
				  	xtype:'bdef_DefCustCombo',
				  	fieldLabel:$i18n.cust,//客户编号
				  	id:'cmbCust6101',
				  	store:Ext.create('cms.store.bdef.bdef_DefCustComboStore'),
			        displayField : 'dropValue',
			        valueField : 'value',
			        readOnly:true,
			        beforeLabelTextTpl : required
			    },{
					fieldLabel:$i18n.p_no,//原返配单号
					id:'txtP_no6101',
					maxLength:20,
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
					xtype:'wms_DefFieldValCombo',
		    	    fieldLabel:$i18n.untread_flag,//返配标识
		    	    id:'cmbUntread_flag6101',
		    	    store:Ext.create("cms.store.common.comboStore").load(
		    	    {
		    	       	 params:{str:"RIDATA_UNTREAD_M,UNTREAD_FLAG"}
		    	    }),
		    	    editable:false,
		    	    allowBlank:false,
		    	    readOnly:true,
		    	    beforeLabelTextTpl:required
			    },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel:$i18n.paper_type,//单据类型
					id:'cmbU_type6101',
					store:Ext.create("cms.store.common.comboStore").load(
		    	    {
		    	       	 params:{str:"RIDATA_UNTREAD_M,UNTREAD_TYPE"}
		    	    	 
		    	    	
		    	    }),
		    	    editable:false,
		    	    allowBlank:false,
		    	    readOnly:true,
		    	    beforeLabelTextTpl:required
			    },{
					xtype:'wms_DefFieldValCombo',
					fieldLabel:$i18n.class_,//是否直通
					id:'cmbClass6101',
					store:Ext.create("cms.store.common.comboStore").load(
		    	    {
		    	       	 params:{str:"RIDATA_UNTREAD_M,CLASS"}
		    	    }),
		    	    editable:false,
		    	    allowBlank:false,
		    	    readOnly:true,
		    	    beforeLabelTextTpl:required
			    },{
					xtype:'wms_DefFieldValCombo',
		    	    fieldLabel:$i18n.qualityType,//品质类型
		    	    id:'cmbQ_type6101',
		    	    store:Ext.create("cms.store.common.comboStore").load(
		    	    {
//		    	       	 params:{str:"RIDATA_UNTREAD_M,QUALITY"}
		    	    	 params:{str:"RIDATA_CHECK_PAL,QUALITY_FLAG"}
		    	    }),
		    	    editable:false,
		    	    allowBlank:false,
		    	    readOnly:true,
		    	    beforeLabelTextTpl:required
			    },{
					xtype:'datefield',
					fieldLabel:$i18n.untread_date,//返配建单日期
					id:'dateUntread_date6101',
					format : 'Y-m-d',
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
					xtype:'datefield',
					fieldLabel:$i18n.request_date,//返配发单日期
					id:'dateRequest_date6101',
					format : 'Y-m-d',
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.orgNo,// 机构代码
					id : 'orgNo6101',	
		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,ORG_NO"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.take_type,// 提货类型
					id : 'takeType6101',	
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"RIDATA_UNTREAD_M,TAKE_TYPE"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
					fieldLabel:'派车单号',
					id : 'carPlanNo6101'
				},{
					fieldLabel:$i18n.remark,//备注
					id:'txtRemark6101'
			    }]
			},{
		   		region:'center',
		        xtype:'grid',
		        id:'gridRidata_untreadD6101',
		        store:Ext.create('cms.store.ridata.ridata_UntreadDStore',{
//		        	listeners:{
//	    	    		'load':function(th,records,successful,eOpts ){
//	    	    			var arrayObj = new Array();
//         					arrayObj[0]='pobox';
//         					arrayObj[1]='popcs';
//         					countList('gridRidata_untreadD6101',arrayObj,'articleNo');
//	    	    		}
//	    	    	}
		        }),
		        selModel : {
			        	selType : 'cellmodel'
			    },
			    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
		        	clicksToEdit : 1,
			        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.ridata.ridata_UntreadController',e.getKey());
					}
			    })],
		        columns:[
		        {
				    xtype:'rownumberer',
				    width:30
				},{
				    width : 120,
			        text : $i18n.article_no,//商品编码
			        dataIndex:'articleNo',
			        cls : 'notnull',
			        field : {							
			        	id : 'article_no6101',
			        	xtype:'bdef_DefArticleCombo',
			        	store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
		        	   	displayField : 'value',
		        	   	valueField : 'value',	
			        	allowBlank :false	
			        }
				},{
					width:120,
				    text:'货主商品编码',//供应商编码
				    dataIndex:'ownerArticleNo'
				},{
				    width:120,
				    text:$i18n.wm_super_no,//供应商编码
				    dataIndex:'supplierNo'
				},{
				    width:200,
				    text:$i18n.article_name,//商品名称
				    dataIndex:'articleName'
				},{
				    width:120,
				    text:$i18n.barcode,//商品条码
				    dataIndex:'barcode'
				},{
				    width:80,
				    text:$i18n.new_packing_qty,//包装数量
				    dataIndex:'packingQty',
				    cls : 'notnull',
				    field:{
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qty6101',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
						editable:false,
						allowBlank :false
					}
				},{
				    width:80,
				    text:$i18n.packingUnit,//包装单位
				    id:'packingUnit_6101',
				    dataIndex:'packingUnit'
				},{
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_6101',
					dataIndex:'packingSpec'
				},{
					width : 85,
					text : $i18n.planBox,	//计划箱数
					dataIndex : 'planBox',
					//hidden:true,
					id:'planBox_6101',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 85,
					text : $i18n.planQmin,//计划中包数
					dataIndex : 'planQmin',
					id:'planQmin_6101',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 85,
					text : $i18n.planDis,//计划散数
					dataIndex : 'planDis',
					id:'planDis_6101',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 50,
					text:$i18n.unit_cost,//单价
					dataIndex : 'unitCost',
					cls : 'notnull',
					field : {
    	        		xtype : 'numberfield',
    	        		minValue:0
    	        	}
				}],
		        dockedItems:[{
			        	xtype:'commMenuWidget6',
			        	region:'north',
			        	id:'toolbar6101'
			    }]
			},{
				xtype : 'panel',
				id : 'msterForm6101',
				region:'south',
				html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
						+ 'background-color:#C1D5ED; text-align: left;">'
						+ '<span><label>新增人:</label><label id="rgstName6101"></label> </span> '
						+ '<span><label>&nbsp;&nbsp;&nbsp;新增日期：</label><label id="rgstDate6101"></label></span>'
						+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName6101"></label> </span> '
						+ '<span><label>&nbsp;&nbsp;&nbsp;修改日期：</label><label id="updtDate6101"></label> </span></div>'
			}]
		}]
	}]          
});
