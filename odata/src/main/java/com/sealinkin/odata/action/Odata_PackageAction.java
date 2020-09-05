package com.sealinkin.odata.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_PackageDModel;
import com.sealinkin.odata.model.Odata_PackageMModel;
import com.sealinkin.odata.service.IOdata_PackageService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial","unused"})
public class Odata_PackageAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IOdata_PackageService odata_PackageServiceImpl;
	private String strWheresql;
	private String strQuery;
	private String str;
	private String ownerNo;
	private String poNo;
	private String sourceExpNo;
	private String operateWorker;
	private String operateType;
	private String reportType;
	private String paperType;
	private File file;
	
	public IOdata_PackageService getOdata_PackageServiceImpl() {
		return odata_PackageServiceImpl;
	}
	public void setOdata_PackageServiceImpl(
			IOdata_PackageService odata_PackageServiceImpl) {
		this.odata_PackageServiceImpl = odata_PackageServiceImpl;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getSourceExpNo() {
		return sourceExpNo;
	}
	public void setSourceExpNo(String sourceExpNo) {
		this.sourceExpNo = sourceExpNo;
	}
	public String getOperateWorker() {
		return operateWorker;
	}
	public void setOperateWorker(String operateWorker) {
		this.operateWorker = operateWorker;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getPaperType() {
		return paperType;
	}
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public void queryPackageM(){
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_PackageMModel> list=odata_PackageServiceImpl.queryPackageM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery,
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void queryPackageD()
	{
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_PackageDModel> list=odata_PackageServiceImpl.queryPackageD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql,
					strQuery,
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void getOwnerCombo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_PackageServiceImpl.getOwnerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取状态
	public void  getStatusList(){
		try 
		{
			List<ComboxBo> list = odata_PackageServiceImpl.getStatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery
					);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//提单号模糊查询
	public void getPackageInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_PackageServiceImpl.getPackageInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql,
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//入库、出库
	public void PackageIntoOrOutStock(){
		try {
			MsgRes msg=odata_PackageServiceImpl.PackageIntoOrOutStock(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					ownerNo,poNo,sourceExpNo,operateWorker,operateType);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	//打印面单、清单
	public void printPackageInfo(){
		try {
			MsgRes msg=odata_PackageServiceImpl.printPackageInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					reportType,
					sourceExpNo,
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					paperType,
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	//上传
	public void upLoad(){
		try {
			MsgRes msg = odata_PackageServiceImpl.upLoad(
					file,super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}

}
