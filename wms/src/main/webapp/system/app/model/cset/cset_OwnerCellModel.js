Ext.define('cms.model.cset.cset_OwnerCellModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'warehouseNo'},
            {name:'ownerNo'},
            {name:'cellNo'},
            {name:'updtDate'},
            {name:'enterpriseNo'}
   ],
   idProperty:'warehouseNo,enterpriseNo,ownerNo,cellNo,updtDate'
});