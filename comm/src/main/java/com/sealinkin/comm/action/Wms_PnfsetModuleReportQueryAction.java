package com.sealinkin.comm.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.po.Bdef_DefOwner;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IWms_PnfsetModuleReportQuery;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_PntsetModuleReportQueryModel;
import com.sealinkin.wms.model.Wms_PntsetmoduleReportModel;

@SuppressWarnings({ "rawtypes", "unchecked" ,"unused"})
public class Wms_PnfsetModuleReportQueryAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private String strModuleId;
	private String strQuery;
	private String sourceNo;
	private String containerNo;
	private String labelNo;
	private String str;
	private IWms_PnfsetModuleReportQuery wms_PnfsetModuleReportQueryImpl;
	
	
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public IWms_PnfsetModuleReportQuery getWms_PnfsetModuleReportQueryImpl() {
		return wms_PnfsetModuleReportQueryImpl;
	}
	public void setWms_PnfsetModuleReportQueryImpl(
			IWms_PnfsetModuleReportQuery wms_PnfsetModuleReportQueryImpl) {
		this.wms_PnfsetModuleReportQueryImpl = wms_PnfsetModuleReportQueryImpl;
	}
	//获取查询条件
	public void getModuleReportQuery(){
		try {
			List<Wms_PntsetModuleReportQueryModel> list=
					wms_PnfsetModuleReportQueryImpl.getModuleReportQuery(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							getStrModuleId());
			super.writeStr(covtListToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//获取来源单号列表
	public void getSourceNoList(){
		PageBo pageBo = new PageBo(getStart(), getLimit());
		ExtListDataBo<Wms_PntsetmoduleReportModel> extListBo = wms_PnfsetModuleReportQueryImpl.getSourceNoList(
				super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				super.getMdBdef_DefWorker().getWorkerOwner(), 
				getStrModuleId(), 
				getStrQuery(), pageBo);
		super.writeStr(covtObjectToJson(extListBo));
	}
	//获取标签列表
	public void getLabelNoList(){
		PageBo pageBo = new PageBo(getStart(), getLimit());
		ExtListDataBo<Wms_PntsetmoduleReportModel> extListBo = wms_PnfsetModuleReportQueryImpl.getLabelNoList(
				super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				super.getMdBdef_DefWorker().getWorkerOwner(), 
				getStrModuleId(),sourceNo,containerNo, pageBo);
		super.writeStr(covtObjectToJson(extListBo));
	}
	//获取数据明细
	public void getReportData(){
		PageBo pageBo = new PageBo(getStart(), getLimit());
		ExtListDataBo<Wms_PntsetmoduleReportModel> extListBo = wms_PnfsetModuleReportQueryImpl.getReportData(
				super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				super.getMdBdef_DefWorker().getWorkerOwner(), 
				getStrModuleId(),sourceNo,containerNo, pageBo);
		super.writeStr(covtObjectToJson(extListBo));
	}
	//通过配置表获取报表ID
	public void getReportSqlBySet(){
		try {
			Wms_PntsetmoduleReportModel reqMod=(Wms_PntsetmoduleReportModel)JSONObject.toBean(JSONObject.fromObject(str),Wms_PntsetmoduleReportModel.class);
			MsgRes msg=wms_PnfsetModuleReportQueryImpl.getReportSqlBySet(reqMod);
			List<Wms_PntsetmoduleReportModel> list=(List<Wms_PntsetmoduleReportModel>) msg.getObj();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//打印任务
	public void printReportOrLabel(){
		try {
			Wms_PntsetmoduleReportModel reqMod=(Wms_PntsetmoduleReportModel)JSONObject.toBean(JSONObject.fromObject(str),Wms_PntsetmoduleReportModel.class);
			MsgRes msg=wms_PnfsetModuleReportQueryImpl.printReportOrLabel(reqMod,
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			//List<Wms_PntsetmoduleReportModel> list=(List<Wms_PntsetmoduleReportModel>) msg.getObj();
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	
}
