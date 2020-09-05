package com.sealinkin.comm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.service.IGetSystemParameterService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_ImportSdModel;
import com.sealinkin.wms.model.Wms_DefbaseModel;
import com.sealinkin.wms.model.Wms_IDataTypeModel;

@SuppressWarnings({"unchecked","rawtypes"})
public class GetSystemParameterImpl implements IGetSystemParameterService {

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 获取系统参数
	 */
	@Override
	public List<Wms_DefbaseModel> getParameterList(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strColName,String strGroupNo, String strSubGroupNo)throws Exception {
		List inList  =  new ArrayList();
		List outList  =  new ArrayList();
		List resultList  =  new ArrayList();
		
		outList.add("S");
		outList.add("S");
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strColName);
		inList.add(strGroupNo);
		inList.add(strSubGroupNo);
		resultList  =  genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_GetBasePara");
		if(resultList.get(2).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(2).toString());
		}
		List<Wms_DefbaseModel> list=new ArrayList<Wms_DefbaseModel>();
		Wms_DefbaseModel wd=new Wms_DefbaseModel();
		wd.setSdefine(resultList.get(0).toString());
		wd.setNdefine(Double.parseDouble(resultList.get(1)==null ? "0":resultList.get(1).toString()));
		list.add(wd);
		return list;
	}
	

	/**
	 * 获取系统界面显示配置hkl2016/5/27
	 */
	@Override
	public List<Wms_DefbaseModel> getGetModuleFieldList(String enterpriseNo,
			String strWarehouseNo, String strModuleId, String strFieldName)
			throws Exception {
		String strSql="select * from wms_module_field_vaule t " +
				"where t.enterprise_no='"+enterpriseNo+"' " +
				"and t.warehouse_no='"+strWarehouseNo+"' " +
				"and t.module_id='"+strModuleId+"' " +
				"and t.field_name='"+strFieldName+"' ";
		
		List<Wms_DefbaseModel> list=genDao.getListByNativeSql(strSql,Wms_DefbaseModel.class);
		Wms_DefbaseModel wd=new Wms_DefbaseModel();
		
		if(list.size()==0){
			wd.setFlag("1");//如果没有数据则传为显示状态
			list.add(wd);
		}
		return  list;
	}
	
	/** 获取进货单据类型策略
	 * huangb 20160715
	 * enterpriseNo 企业编号 必传
	 * strWarehouseNo 仓别号
	 * strOwnerNo 货主编码
	 * strImportType 单据类型 必传
	 * strColumnName 需要取值的列名 必传
	 */
	@Override
	public List<Wms_IDataTypeModel> getIdataTypeStrategy(
		   String enterpriseNo,String strWarehouseNo,String strOwnerNo,
		   String strImportType,String strColumnName)throws Exception {
		
		List inList  =  new ArrayList();
		List outList  =  new ArrayList();
		List resultList  =  new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strImportType);
		inList.add(strColumnName);
		resultList  =  genDao.execProc(inList, outList, "PKLG_WMS_Public.P_GetIdataOrder");
		if(resultList.get(1).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		List<Wms_IDataTypeModel> list=new ArrayList<Wms_IDataTypeModel>();
		Wms_IDataTypeModel wi=new Wms_IDataTypeModel();
		wi.setColumnValue(resultList.get(0).toString());
		list.add(wi);
		return list;
	}
	
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
	@Override
	public List<Wms_IDataTypeModel> getRidataOrderStrategy(
		   String enterpriseNo,String strWarehouseNo,String strOwnerNo,
		   String strUntreadType,String strClassType,String strQualityFlag,
		   String strColumnName)throws Exception {
		
		List inList  =  new ArrayList();
		List outList  =  new ArrayList();
		List resultList  =  new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strUntreadType);
		inList.add(strClassType);
		inList.add(strQualityFlag);
		inList.add(strColumnName);
		resultList  =  genDao.execProc(inList, outList, "PKLG_WMS_Public.p_GetRIdataOrder");
		if(resultList.get(1).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		List<Wms_IDataTypeModel> list=new ArrayList<Wms_IDataTypeModel>();
		Wms_IDataTypeModel wi=new Wms_IDataTypeModel();
		wi.setColumnValue(resultList.get(0).toString());
		list.add(wi);
		return list;
	}
}
