/**
 * 创建人:JUN
 */
Ext.define('cms.model.idata.idata_CheckPalModel', {
    extend: 'Ext.data.Model',
    fields: [
		{name:'warehouseNo'},     
		{name:'ownerNo'},         
		{name:'SCheckNo'},        
		{name:'checkNo'},         
		{name:'checkRowId'},      
		{name:'containerNo'},     
		{name:'labelNo'},         
		{name:'subLabelNo'},      
		{name:'articleNo'},       
		{name:'packingQty'},      
		{name:'checkQty'},        
		{name:'status'},          
		{name:'printerGroupNo'},  
		{name:'dockNo'},          
		{name:'barcode'},         
		{name:'produceDate'},     
		{name:'expireDate'},      
		{name:'quality'},         
		{name:'lotNo'},           
		{name:'rsvBatch1'},       
		{name:'rsvBatch2'},       
		{name:'rsvBatch3'},       
		{name:'rsvBatch4'},       
		{name:'rsvBatch5'},       
		{name:'rsvBatch6'},       
		{name:'rsvBatch7'},       
		{name:'rsvBatch8'},       
		{name:'stockType'},       
		{name:'stockValue'},      
		{name:'deptNo'},          
		{name:'fixpalFlag'},      
		{name:'custNo'},          
		{name:'subCustNo'},       
		{name:'businessType'},    
		{name:'rgstName'},        
		{name:'rgstDate'},        
		{name:'updtName'},        
		{name:'updtDate'},        
		{name:'price'},           
			                        
		{name:'articleName'},     
		{name:'packingUnit'},     
		{name:'spec'},            
		{name:'barcode'},         
		{name:'pkQty'},        
		{name:'ownerArticleNo'},
		{name:'poNo'},
		{name:'temperature'},
		{name:'packingUnitQmin'},//中包装单位
		{name:'Unit'},//基本包装单位
		{name:'packingSpec'},//箱包装规格
		{name:'packingSpecQmin'},//中包装规格
		{name:'realBox'},//实际箱数
		{name:'realDis'},//实际散数
		{name:'realQmin'},//实际中包数
		{name:'planBox'},//计划箱数
		{name:'planDis'},//计划散数
		{name:'planQmin'},//计划中包数
		{name:'qminOperatePacking'},//最小操作包装数量
		{name:'unitPacking'}//基本包装数量
    ],
    idProperty:'warehouseNo,ownerNo,SCheckNo,checkNo,checkRowId,containerNo,labelNo,subLabelNo'
});
