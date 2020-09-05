package com.sealinkin.report.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.report.model.ArticleInvAccReportModel;
import com.sealinkin.report.model.Idata_Check_DSourceReportModel;
import com.sealinkin.wms.model.Wms_DefSearch_DModel;
import com.sealinkin.wms.model.Wms_DefsearchDSetModel;

@SuppressWarnings({ "rawtypes"})
public interface IReport_Service {
	public ExtListDataBo<Idata_Check_DSourceReportModel> getSourceReportList()throws Exception;
	
	public ExtListDataBo getGridData(
			String enterpriseNo,String warehouseNo,String ownerNo,String pgmId, 
			String str,PageBo pageBo, Integer requestFlag, String strName)throws Exception;
	
	public List<Wms_DefSearch_DModel> getGridColumModle(String str, String strName)throws Exception;
	
	public MsgRes getTotalData(String enterpriseNo,
			String warehouseNo,
			String ownerNo,
			String pgmId,
			String str, 
			String strName)throws Exception;

	//获得对应模块下的报表
	public List<ComboxBo> getReportList(
			String strEnterpriseNo,String moduleId);

	//获得对应模块下的参考项
	public List<ComboxBo> getReferenceItemList(String referenceItem);

	//或取高阶查询设置项
	public ExtListDataBo<Wms_DefsearchDSetModel> getWmsDefsearckDSetList(
			String strName, String referenceItem);

	/**
	 * 参数设置保存
	 */
	public MsgRes SaveReportSet(String strParams);

	//重命名
	public MsgRes SaveRename(String strNewName, String strOldName,
			String strPgmId);

	//另存为
	public MsgRes SaveOtherReportSet(String str);

	//检验参考项目名称是否已经存在
	public MsgRes checkProjectName(String strPgmId, String strName)throws Exception;
	
	//获取商品库存账数据
	public ExtListDataBo<ArticleInvAccReportModel> getArticleInvAccData(
			String warehouseNo,
			String ownerNo,
			String beginDate, 
			String endDate,
			String str,
			PageBo pageBo, 
			Integer requestFlag) throws Exception;
	
	//获取商品库存账数据
	public List exportArticleInvAccData(
			String warehouseNo,
			String ownerNo,
			String strDate, 
			String strQuery,
			int beginYear,
			int beginMonth,
			int beginDay,
			int endYear,
			int endMonth,
			int endDay,
			String str) throws Exception;

	public MsgRes deleteProject(String strPgmId, String strOldName)throws Exception;	
	//获取当前月数的天数
    public int getDaysByYearMonth(int year, int month);
}
