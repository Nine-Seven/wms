Ext.define('cms.model.ridata.ridata_CheckPalTmpModel', {
    extend: 'Ext.data.Model',
    fields: [
    		{name:'warehouseNo'},
			{name:'SCheckNo'},
			{name:'rowId'},
			{name:'labelId'},
			{name:'labelNo'},
			{name:'ownerNo'},
			{name:'SUntreadNo'},
			{name:'articleNo'},
			{name:'packingQty'},
			{name:'checkQty'},
			{name:'status'},
			{name:'printerGroupNo'},
			{name:'dockNo'},
			{name:'subLabelNo'},
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
			{name:'checkTools'},
			{name:'untreadType'},
			{name:'supplierNo'},
			{name:'fixpalFlag'},
			{name:'businessType'},
			{name:'deptNo'},
			{name:'cellNo'},
			{name:'batchNo'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'operateDate'},
			
			{name:'workerNo'},
			{name:'workerName'},
			{name:'articleName'},
			{name:'qualityText'},
			{name:'checkBox'},
			{name:'checkpcs'},
			{name:'ownerArticleNo'},
			
			{name:'planBox'},		 
			{name:'planQmin'},
			{name:'planDis'},
			{name:'realBox'},
			{name:'realDis'},
			{name:'realQmin'},
			{name:'packingUnit'},
			{name:'packingUnitQmin'},
			{name:'unit'},
			{name:'packingSpec'},	
			{name:'qminOperatePacking'},
			{name:'unitPacking'},
			{name:'packingSpecQmin'},
			{name:'spec'}
			

    ],
    idProperty:'warehouseNo,SCheckNo,rowId,labelNo'
 });