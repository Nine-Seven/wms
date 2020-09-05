/**
 * 补印中心视图
 */
var print_RepairReportInfoStore=Ext.create('cms.store.print.print_RepairReportInfoStore');
var print_RepairLabelStore=Ext.create('cms.store.print.print_RepairReportInfoStore');
var print_LabelStore=Ext.create('cms.store.print.print_LabelStore');
Ext.define('cms.view.print.print_repairUI',{
	extend:'Ext.tab.Panel',
	alias:'widget.print_repairUI',
	title:$i18n.repair_title,
	id:'print_repairUI',
	requires:['cms.view.common.defReportCombo'],
	stores:['cms.store.print.print_RepairReportInfoStore','cms.store.print.print_RepairStore'],
	items:[{
		layout:'border',
		title:'报表打印',
		items:[{
	        region: 'north',     
	        xtype: 'form',
	        height: 100,
	        split: true,
	        frame:true,
	        items:[{
	        	layout:'column',
	        	frame:true,
	        	defaults:{
	        		margin: '5 5 5 5'
	        	},
	        	items:[{
	        		xtype:'defReportCombo',
	        		id:'defReportCombo',
	        		labelWidth:60,
					allowBlank: false,
				    store: Ext.create('cms.store.print.print_RepairStore',{
						   proxy:{
								type:'ajax',
								method:'post',
								url:'printerAction_AueryReportList.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									reportType : "L"	//报表
								}
						   }
				    }),
				    queryMode: 'remote',
				    displayField: 'reportName',
				    valueField: 'reportId'
	        	},{
	        		id:'radiogroupHtyFlag',
	        		xtype:'radiogroup',
	        		margin: '5 5 5 15',
	        		width:200,
	                columns: 2,
	                vertical: true,
	        		items:[{
	        			boxLabel: '作业报表', name: 'rb',  inputValue: '0',checked:true
	        		},{
	        			boxLabel: '历史报表', name: 'rb',  inputValue: '1'
	        		}]
	        	}]
	        },{
	        	xtype:'form',
	        	id:'repairForm',
	        	layout:'column',
	        	frame:true,
	        	defaults:{
	        		margin: '5 5 5 5',
	        		labelWidth:60
	        	},
	        	items:[{
	        		id:'radiogroup',
	        		xtype:'radiogroup',
	        		fieldLabel:'格式',
	        		width:250,
	                columns: 2,
	                vertical: true,
	        		items:[{
	        			boxLabel: '来源单号', name: 'rb',  inputValue: '1',checked:true
	        		},{
	        			boxLabel: '日期', name: 'rb',  inputValue: '2'
	        		}]
	        	},{
	        		xtype:'textfield',
	        		id:'sourceNoStr',
	        		name:'aaa'
        		},{
        			xtype:'datefield',
        			format:'Y-m-d',
        			id:'dateStr',
        			hidden:true
        		},{
	        		xtype:'button',
	        		name:'printRepairQueryBtn',
	        		text:'查询'
	        	},{
	        		xtype:'button',
	        		text:'打印',
	        		name:'printRepairPrintBtn'
	        	},{
	        		xtype:'button',
	        		text:'打印预览',
	        		name:'printPreviewBtn',
	        		hidden:true
	        	},{
	        		xtype:'hidden',
	        		id:'reportId'
	        	},{
	        		xtype:'hidden',
	        		id:'reportType'
	        	}]
	        }]
	    },{
	        title: '报表列表',
	        region: 'center',
	        xtype: 'grid',
	        layout: 'fit',
	        margin: '5 5 0 0',
	        store: print_RepairReportInfoStore,
	        id:'RepairReportGrid',
	        columns:[{
				xtype : 'rownumberer',
				width : 30
		   },{
			    xtype: 'checkcolumn',
				width : 50,
				columnHeaderCheckbox: true,
				store: Ext.data.StoreManager.lookup('print_RepairReportInfoStore'),
			    sortable: false,
			    hideable: false,
			    menuDisabled: true,
				dataIndex:'checkColumn',
				id:'repairCheckcolumn'
		   },{
	        	dataIndex:'sourceNo',
	        	width:200,
	        	text:'单号'
	        },
	        {
	        	dataIndex:'ownerNo',
	        	text:'委托号'
	        },
	        {
	        	dataIndex:'custNo',
	        	width:300,
	        	text:'客户/供应商 '
	        }],
		   dockedItems : [{
			   xtype : 'pagingtoolbar',
			   store: print_RepairReportInfoStore,
			   dock : 'bottom',
			   displayInfo : true
		   }]
	    }]
		},{
			layout:'border',
			title:'标签打印',
			items:[{
		        region: 'north',     
		        xtype: 'form',
		        height: 100,
		        split: true,
		        frame:true,
		        items:[{
		        	layout:'column',
		        	frame:true,
		        	defaults:{
		        		margin: '5 5 5 5'
		        	},
		        	items:[{
		        		xtype:'defReportCombo',
		        		id:'defLabelCombo11101',
		        		labelWidth:60,
		        		beforeLabelTextTpl : required,
						store: Ext.create('cms.store.print.print_RepairStore',{
						   proxy:{
								type:'ajax',
								method:'post',
								url:'printerAction_AueryReportList.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									reportType : "B"	//标签
								}
						   }
				    	}),
					    queryMode: 'remote',
					    displayField: 'reportName',
					    valueField: 'reportId'
		        	},{
		        		id:'radiogroupLabelHtyFlag',
		        		xtype:'radiogroup',
		        		margin: '5 5 5 15',
		        		width:200,
		                columns: 2,
		                vertical: true,
		        		items:[{
		        			boxLabel: '作业标签', name: 'rb',  inputValue: '0',checked:true
		        		},{
		        			boxLabel: '历史标签', name: 'rb',  inputValue: '1'
		        		}]
		        	}]
		        },{
		        	xtype:'form',
		        	id:'labelForm11101',
		        	layout:'column',
		        	frame:true,
		        	defaults:{
		        		margin: '5 5 5 5',
		        		labelWidth:60
		        	},
		        	items:[{
		        		id:'radiogroup11101',
		        		xtype:'radiogroup',
		        		fieldLabel:'格式',
		        		width:300,
		                columns: 3,
		                vertical: true,
		        		items:[{
		        			boxLabel: '来源单号', name: 'rb',  inputValue: '1',checked:true
		        		},{
		        			boxLabel: '日期', name: 'rb',  inputValue: '2'
		        		},{
		        			boxLabel: '标签', name: 'rb',  inputValue: '3'
		        		}]
		        	},{
		        		xtype:'textfield',
		        		id:'sourceNoStr11101'
		        		 
	        		},{
	        			xtype:'datefield',
	        			format:'Y-m-d',
	        			id:'dateStr11101',
	        			hidden:true
	        		},{
		        		xtype:'numberfield',   
		        		fieldLabel:'打印份数',   //打印份数
						id:'printNumber11101',						
						width:180,
						minValue:1,
						value:1,
						beforeLabelTextTpl : required,
		        	},{
		        		xtype:'button',
		        		id:'printLabelQueryBtn',
		        		name:'printLabelQueryBtn',
		        		text:'查询'
		        	},{
		        		xtype:'button',
		        		text:'打印',
		        		name:'printLabelPrintBtn'
		        	},{
		        		xtype:'button',
		        		text:'打印预览',
		        		name:'printLabelPreviewBtn',
		        		hidden:true
		        	},{
		        		xtype:'hidden',
		        		id:'reportId11101'
		        	},{
		        		xtype:'hidden',
		        		id:'reportType11101'
		        	}]
		        }]
			},{
				xtype:'panel',
				region:'center',
				layout:'border',
				items:[
				{
					xtype:'grid',
					region:'center',
					title:'单据选择',
					store:print_RepairLabelStore,
					id:'sheetGrid11101',
					singleSelect:true,				
					columns:[
		    		    {			
		    		        xtype : 'rownumberer',
		    			    width : 40
		    		    },
		    		    {
		    			    width:200,
		    			    text : '单号',
		    			    dataIndex:'sourceNo'
	    		    }],
				    dockedItems : [{
					   xtype : 'pagingtoolbar',
					   store: print_RepairLabelStore,
					   dock : 'bottom',
					   displayInfo : true
				    }]					
					},{
					xtype:'grid',
					region:'east',
					width:'50%',
					title:'标签选择',
					store:print_LabelStore,
					id:'print_LabelStore11101',
					multiSelect: true,  
					selModel: {  
					    selType:'checkboxmodel',
					    checkOnly:true
					},
					columns:[
	    		    {			
	    		        xtype : 'rownumberer',
	    			    width : 40
	    		    },
	    		    {
	    			    width:125,
	    			    text : $i18n.label_no,//标签号
	    			    dataIndex:'labelNo'
	    		    },
	    		    {
	    			    width:125,
	    			    text : $i18n.label_no,//传入后台的值
	    			    dataIndex:'sourceNo',
	    			    hidden:true
	    		    }]
				}
			]
		}]
	}]	
});
