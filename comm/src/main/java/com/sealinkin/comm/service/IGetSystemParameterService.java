package com.sealinkin.comm.service;

import java.util.List;

import com.sealinkin.wms.model.Wms_DefbaseModel;
import com.sealinkin.wms.model.Wms_IDataTypeModel;

public interface IGetSystemParameterService {

	/**
	 * 获取系统参数
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @param strColName
	 * @param strGroupNo
	 * @param strSubGroupNo
	 * @return
	 */
	List<Wms_DefbaseModel> getParameterList(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strColName,String strGroupNo,String strSubGroupNo)throws Exception;

	
	/**
	 * 获取系统界面显示配置hkl2016/5/27
	 */
	List<Wms_DefbaseModel> getGetModuleFieldList(
			String enterpriseNo,String strWarehouseNo,
			String strModuleId,String strFieldName)throws Exception;

	/** 获取进货单据类型策略
	 * huangb 20160715
	 * enterpriseNo 企业编号 必传
	 * strWarehouseNo 仓别号
	 * strOwnerNo 货主编码
	 * strImportType 单据类型 必传
	 * strColumnName 需要取值的列名 必传
	 */
	public List<Wms_IDataTypeModel> getIdataTypeStrategy(String enterpriseNo,
		   String strWarehouseNo, String strOwnerNo, String strImportType,
		   String strColumnName) throws Exception;
    
	/** 获取返配单据类型策略
	 * huangb 20160815
	 * enterpriseNo 企业编号 必传
	 * strWarehouseNo 仓别号
	 * strOwnerNo 货主编码
	 * strUntreadType 单据类型 必传
	 * strClassType 必传
	 * strQualityFlag 品质
	 * strColumnName 需要取值的列名 必传
	 */
	public List<Wms_IDataTypeModel> getRidataOrderStrategy(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,
		    String strUntreadType,String strClassType,String strQualityFlag,
		    String strColumnName) throws Exception;


}
