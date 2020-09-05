package com.sealinkin.report.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.wms.model.Wms_DefSearch_DModel;
import com.sealinkin.wms.model.Wms_DefSearch_MModel;
import com.sealinkin.wms.model.Wms_DefreportformenuModel;

public interface IReport_SetService {

	List<ComboxBo> getModubleMenu(String currEnterpriseNo)throws Exception;

	ExtListDataBo<Wms_DefreportformenuModel> getReportformenu(
			String currEnterpriseNo, String strQuery,PageBo pageBo)throws Exception;

	Wms_DefSearch_MModel getDefSerchM(String currEnterpriseNo, String pmgId)throws Exception;

	ExtListDataBo<Wms_DefSearch_DModel> getWmsDefsearchD(String enterpriseNo,String strQuery)throws Exception;

	MsgRes saveSubReport(String enterpriseNo, String workerNo,
			String modubleId, String pmgId, String procName,
			String needWarehouseNo, String needOwner)throws Exception;

	MsgRes changeShow(String enterpriseNo, String modubleId, String pmgId,
			String show)throws Exception;

	MsgRes changeSql(String enterpriseNo, String pmgId, String beforeTreatment,
			String preparedSql, String afterTreatment)throws Exception;

	MsgRes saveSearchD(String searchD, String workerNo)throws Exception;

	MsgRes deleteSearchD(String currEnterpriseNo, String pmgId, String fieldId, String seq)throws Exception;

	List<ComboxBo> getFieldType()throws Exception;

	MsgRes changeSearchD(String searchD, String workerNo)throws Exception;

	List<ComboxBo> getXType()throws Exception;

	MsgRes saveQuery(String searchD)throws Exception;

	//删除查询列
	MsgRes deleteQuery(String currEnterpriseNo, String moduleId, String columnid, String orderNo)throws Exception;

	//修改查询列
	MsgRes changeQuery(String searchD)throws Exception;

	//wms_defreportformenu移动
	MsgRes seqencingDfm(String currEnterpriseNo, String modubleId,
			String pmgId,String orderNo, String flag);

	//wms_defmodulequerycolumn移动
	MsgRes seqencingQuery(String currEnterpriseNo, String moduleId,
			String columnid, String orderNo, String flag);

	MsgRes seqencingSearchD(String currEnterpriseNo, String pmgId,
			String fieldId, String seq, String flag)throws Exception;

}
