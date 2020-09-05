package com.sealinkin.comm.service;

import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_PntsetModuleReportQueryModel;
import com.sealinkin.wms.model.Wms_PntsetmoduleReportModel;

@SuppressWarnings({"unchecked","rawtypes"})
public interface IWms_PnfsetModuleReportQuery {
	//获取查询条件
	public List<Wms_PntsetModuleReportQueryModel> getModuleReportQuery(String enterpriseNo,String moduleId);
	//获取来源单号列表
	public ExtListDataBo<Wms_PntsetmoduleReportModel> getSourceNoList(String enterpriseNo,String warehouseNo,String ownerNo,String moduleId,String strQuery,PageBo pageBo);
	//获取标签列表
	public ExtListDataBo<Wms_PntsetmoduleReportModel> getLabelNoList(String enterpriseNo,String warehouseNo,String ownerNo,String moduleId,String sourceNo,String containerNo,PageBo pageBo);
	//补印-通过配置表获取报表ID的Sql语句 供前台界面调用 huangb 20160617
	public MsgRes getReportSqlBySet(Wms_PntsetmoduleReportModel reqMod)
			throws Exception;
	//获取数据明细
	public ExtListDataBo<Wms_PntsetmoduleReportModel> getReportData(String enterpriseNo,String warehouseNo,String ownerNo,String moduleId,String sourceNo,String containerNo,PageBo pageBo);
	//写打印任务
	public MsgRes printReportOrLabel(Wms_PntsetmoduleReportModel reqMod,String workerNo,String workSpaceNo) throws Exception;
}
