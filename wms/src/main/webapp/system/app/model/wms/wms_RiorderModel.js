Ext.define('cms.model.wms.wms_RiorderModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'enterpriseNo'
					}, {
						name : 'untreadType'
					}, {
						name : 'untreadTypeText'
					}, {
						name : 'classTypeText'
					}, {
						name : 'qualityFlagText'
					}, {
						name : 'overQtyFlagText'
					}, {
						name : 'autoInstockText'
					}, {
						name : 'advanceLocateText'
					}, {
						name : 'deviceComputeText'
					}, {
						name : 'mixOrdercheckText'
					}, {
						name : 'rsvLabelFlagText'
					}, {
						name : 'directCellFlagText'
					}, {
						name : 'printCheckFlagText'
					}, {
						name : 'autoCheckComfirFlagText'
					}, {
						name : 'directCellNoText'
					}, {
						name : 'classType'
					}, {
						name : 'qualityFlag'
					}, {
						name : 'overQtyFlag'
					}, {
						name : 'autoInstock'
					}, {
						name : 'advanceLocate'
					}, {
						name : 'deviceCompute'
					}, {
						name : 'strategyId'
					}, {
						name : 'mixOrdercheck'
					}, {
						name : 'rsvLabelFlag'
					}, {
						name : 'directCellFlag'
					}, {
						name : 'printCheckFlag'
					}, {
						name : 'autoCheckComfirFlag'
					}, {
						name : 'directCellNo'
					},

					{
						name : 'orderRsv01'
					}, {
						name : 'orderRsv02'
					}, {
						name : 'orderRsv03'
					}, {
						name : 'orderRsv04'
					}, {
						name : 'orderRsv05'
					}, {
						name : 'orderRsv06'
					}, {
						name : 'orderRsv07'
					}, {
						name : 'orderRsv08'
					}, {
						name : 'rgstName'
					}, {
						name : 'rgstDate'
					}, {
						name : 'updtName'
					}, {
						name : 'updtDate'
					}],	
			idProperty : 'enterpriseNo,classType,untreadType,qualityFlag'
		});