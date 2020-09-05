/**
 * 模块名称：车辆信息
 * 模块编码：1V01
 * 创建：hcx
 */
Ext.define('cms.view.bdef.window.bdef_DefCarAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.bdef_DefCarAddOrEditWindow',
 	layout:'border',
    width : 600,
	height : 270,
 	modal:true,
 	id:'bdef_DefCarAddOrEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'bdef_DefCarAddOrEditForm',
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
	          	xtype:'bdef_DefOwnerCombo',
				fieldLabel:$i18n.cartype_no,//车辆类型代码
	 	    	id:'cartype_no1V01',
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_DefCarAction_getCarTypeQuery',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
			   	})
			},{
	 	    	fieldLabel:$i18n.car_no,//车辆代码
	 	    	id:'carNo1V01',
	 	    	beforeLabelTextTpl : required
	 	    },{
				fieldLabel : $i18n.car_name,//车辆名称
				id : 'carName1V01'
		    },{
				fieldLabel : $i18n.car_plate,//车牌号
				id : 'carPlate1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.oil_consume,//百公里油耗
				minValue:0,
				id : 'oilConsume1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.care_mile,//保养里程数
				minValue:0,
				id : 'careMile1V01'
		    },{
				xtype : 'numberfield',
				fieldLabel : $i18n.mile,//里程数
				minValue:0,
				id : 'mile1V01'
		    },{
				xtype : 'datefield',
				fieldLabel : $i18n.care_date,//保养日期
				id : 'careDate1V01',							
				format : 'Y-m-d'
			},{
				fieldLabel : $i18n.car_plate,//车牌号
				id : 'carPlate1V01'
		    },{
				fieldLabel : $i18n.care_worker,//保养人
				id : 'careWorker1V01'
		    },{
				fieldLabel : $i18n.car_plate,//车牌号
				id : 'carPlate1V01'
		    },{
				fieldLabel : $i18n.sanpl_no,//承运商
				id : 'sanplNo1V01'
		    },{

		    	xtype:'wms_DefFieldValCombo',
		        fieldLabel : $i18n.car_type,//车辆类型
		        id : 'carClass1V01',
		        store:Ext.create("cms.store.common.comboStore").load(
		        {
		        	params:{str:"BDEF_DEFCAR,CAR_CLASS"}
		        })
		    },{
				xtype : 'bdef_DefWorkerCombo',
				fieldLabel : $i18n.driver_worker,//司机
				id : 'driverWorker1V01',
				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore')
		    }]
		  }]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget1V01_2'
 	}]
});