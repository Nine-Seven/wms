Ext.define('cms.model.cset.cset_deflocModel', {
    extend: 'Ext.data.Model',
    fields: [
             {name:'enterpriseNo'},
             {name:'warehouseNo'},
             {name:'warehouseName'},
             {name:'memo'},
             {name:'createFlag'},
             {name:'rgstName'},
             {name:'rgstDate'},
             {name:'updtName'},
             {name:'updtDate'},
             {name:'adress'},
             {name:'linkman'},
             {name:'tel'},
             {name:'manageName'},
             {name:'province'},
             {name:'zone'},
             {name:'city'},
             {name:'companyNo'},
             {name:'creatFlagText'}
    ],
    idProperty:'warehouseNo,enterpriseNo'
});
