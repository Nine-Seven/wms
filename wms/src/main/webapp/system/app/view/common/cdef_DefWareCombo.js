/**
 * 仓库、公用組件
 * lich
 */
Ext.define('cms.view.common.cdef_DefWareCombo',{
	extend:'Ext.form.ComboBox',
	alias:'widget.cdef_DefWareCombo',
    queryMode: 'local',
    displayField: 'dropValue',
    valueField: 'value',
	forceSelection : true,
	store:Ext.create('cms.store.cdef.cdef_DefWareComboStore'),
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