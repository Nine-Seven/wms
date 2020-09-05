Ext.define('cms.view.common.wms_DefFieldValCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.wms_DefFieldValCombo',
    queryMode: 'local',
    displayField: 'dropValue',
    valueField: 'value',
	store:Ext.create("cms.store.common.comboStore"),
	forceSelection : true,
	queryParam : 'str',
	listeners:{
		beforequery : function(e) 
		{
			 var combo = e.combo;   
		        if(!e.forceAll){   
		            var query = e.query;   
		            combo.store.filterBy(function(record,id){   
		                var value1 = record.get(combo.valueField); 
		                var text = record.get(combo.displayField);
		                var  is  ;
		                if (value1.toLowerCase().indexOf(query.toLowerCase())!=-1 ||text.indexOf(query.toLowerCase())!=-1)
		                {
		                	return true;
		                }
		            });   
		            combo.expand();   
		            return false;   
		        }   
		   }
	}
});