package com.sealinkin.comm.action;

import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetSystemParameterService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_DefbaseModel;
import com.sealinkin.wms.model.Wms_IDataTypeModel;

public class GetSystemParameterAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	
	private IGetSystemParameterService getSystemParameterImpl;
	private String strColName;
	private String strGroupNo;
	private String strSubGroupNo;
	private String strOwnerNo;
	private String strModuleId;
	private String strFieldName;
	private String strImportType;
	private String strColumnName;
	/**
	 * 获取系统参数
	 */
	public void getSystemParameterList()
	{
		try {
			List<Wms_DefbaseModel> list=getSystemParameterImpl.getParameterList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,strColName, strGroupNo, strSubGroupNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	/**
	 * 获取系统界面显示配置hkl2016/5/27
	 */
	public void getGetModuleFieldList()
	{
		try {
			List<Wms_DefbaseModel> list=getSystemParameterImpl.getGetModuleFieldList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strModuleId,strFieldName);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	
	/** 获取进货单据类型策略
	 * huangb 20160715
	 * enterpriseNo 企业编号 必传
	 * strWarehouseNo 仓别号
	 * strOwnerNo 货主编码
	 * strImportType 单据类型 必传
	 * strColumnName 需要取值的列名 必传
	 */
	public void getSystemIdataTypeStrategy()
	{
		try {
			List<Wms_IDataTypeModel> list=getSystemParameterImpl.getIdataTypeStrategy(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,strImportType, strColumnName);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}

	public void setGetSystemParameterImpl(
			IGetSystemParameterService getSystemParameterImpl) {
		this.getSystemParameterImpl = getSystemParameterImpl;
	}

	public String getStrColName() {
		return strColName;
	}
	public void setStrColName(String strColName) {
		this.strColName = strColName;
	}
	public String getStrGroupNo() {
		return strGroupNo;
	}

	public void setStrGroupNo(String strGroupNo) {
		this.strGroupNo = strGroupNo;
	}
	public String getStrSubGroupNo() {
		return strSubGroupNo;
	}
	public void setStrSubGroupNo(String strSubGroupNo) {
		this.strSubGroupNo = strSubGroupNo;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrFieldName() {
		return strFieldName;
	}
	public void setStrFieldName(String strFieldName) {
		this.strFieldName = strFieldName;
	}
	public String getStrImportType() {
		return strImportType;
	}
	public void setStrImportType(String strImportType) {
		this.strImportType = strImportType;
	}
	public String getStrColumnName() {
		return strColumnName;
	}
	public void setStrColumnName(String strColumnName) {
		this.strColumnName = strColumnName;
	}
	
}
