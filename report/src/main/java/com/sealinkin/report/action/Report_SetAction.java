package com.sealinkin.report.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.report.service.IReport_SetService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.wms.model.Wms_DefSearch_DModel;
import com.sealinkin.wms.model.Wms_DefSearch_MModel;
import com.sealinkin.wms.model.Wms_DefreportformenuModel;


public class Report_SetAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IReport_SetService report_setImpl;
	private String strQuery;
	private String modubleId;
	private String pmgId;
	private String procName;
	private String needWarehouseNo;
	private String needOwner;
	private String show;
	private String beforeTreatment;
	private String preparedSql;
	private String afterTreatment;
	private String searchD;
	private String fieldId;
	private String moduleId;
	private String columnid;
	private String orderNo;
	private String flag;
	private String seq;
	
	//获取主菜单
	public void getModubleMenu(){
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = report_setImpl.getModubleMenu(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	
	//获取子报表
	public void getReportformenu(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Wms_DefreportformenuModel> list=report_setImpl.getReportformenu(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//获取单条getDefSerchM信息
	public void getDefSerchM(){
		try {
			
			Wms_DefSearch_MModel list=report_setImpl.getDefSerchM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					pmgId);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取显示列表
	public void getWmsDefsearchD(){
		try {
			ExtListDataBo<Wms_DefSearch_DModel> list=report_setImpl.getWmsDefsearchD(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					strQuery);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存子报表
	public void saveSubReport(){
		try {
			MsgRes msg=report_setImpl.saveSubReport(super.getMdBdef_DefWorker().getEnterpriseNo(),
													super.getMdBdef_DefWorker().getWorkerNo(),
													this.getModubleId(),
													this.getPmgId(),
													this.getProcName(),
													this.getNeedWarehouseNo(),
													this.getNeedOwner());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	
	}
	
	//修改报表是否可见
	public void changeShow(){
		try {
			MsgRes msg=report_setImpl.changeShow(super.getMdBdef_DefWorker().getEnterpriseNo(),
												 this.getModubleId(),
												 this.getPmgId(),
												 this.getShow());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"修改失败！",""));
		}
	
	}
	
	//修改出来SQL
	public void changeSql(){
		try {
			MsgRes msg=report_setImpl.changeSql(super.getMdBdef_DefWorker().getEnterpriseNo(), 
												this.getPmgId(),
												this.getBeforeTreatment(),
												this.getPreparedSql(),
												this.getAfterTreatment());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
		
	}
	
	//保存WMS_DEFSREACH_D
	public void saveSearchD(){

		try {
			MsgRes msg=report_setImpl.saveSearchD(this.getSearchD(),
												  super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
		
	
	}
	//删除WMS_DEFSREACH_D
	public void deleteSearchD(){
		try {
			MsgRes msg=report_setImpl.deleteSearchD(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
													this.getPmgId(),
													this.getFieldId(),
													this.getSeq());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"",""));
		}
	}
	
	//获取字段类型
	public void getFieldType(){
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = report_setImpl.getFieldType();
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	
	}
	
	//修改WMS_DEFSREACH_D
	public void changeSearchD(){

		try {
			MsgRes msg=report_setImpl.changeSearchD(this.getSearchD(),
												  super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	}
	
	//获取查询数据的类型
	public void getXType(){

		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = report_setImpl.getXType();
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}	
	
	
	}
	//保存查询列
	public void saveQuery(){
		try {
			MsgRes msg=report_setImpl.saveQuery(this.getSearchD());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}
	//删除查询列
	public void deleteQuery(){
		try {
			MsgRes msg=report_setImpl.deleteQuery(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
													this.getModuleId(),
													this.getColumnid(),
													this.getOrderNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"",""));
		}
	
	}
	//修改查询列
	public void changeQuery(){
		try {
			MsgRes msg=report_setImpl.changeQuery(this.getSearchD());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//wms_defreportformenu移动
	public void seqencingDfm(){
		try {
			MsgRes msg=report_setImpl.seqencingDfm(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
												   this.getModubleId(),
												   this.getPmgId(),
												   this.getOrderNo(),
												   this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//wms_defmodulequerycolumn移动
	public void seqencingQuery(){
		try {
			MsgRes msg=report_setImpl.seqencingQuery(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
												   this.getModuleId(),
												   this.getColumnid(),
												   this.getOrderNo(),
												   this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	
	//wms_defsearch_d移动
	public void seqencingSearchD(){

		try {
			MsgRes msg=report_setImpl.seqencingSearchD(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
												       this.getPmgId(),
												       this.getFieldId(),
												       this.getSeq(),
												       this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	
	}
	
	public IReport_SetService getReport_setImpl() {
		return report_setImpl;
	}
	public void setReport_setImpl(IReport_SetService report_setImpl) {
		this.report_setImpl = report_setImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getPmgId() {
		return pmgId;
	}
	public void setPmgId(String pmgId) {
		this.pmgId = pmgId;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getNeedWarehouseNo() {
		return needWarehouseNo;
	}

	public void setNeedWarehouseNo(String needWarehouseNo) {
		this.needWarehouseNo = needWarehouseNo;
	}

	public String getNeedOwner() {
		return needOwner;
	}

	public void setNeedOwner(String needOwner) {
		this.needOwner = needOwner;
	}

	public String getModubleId() {
		return modubleId;
	}

	public void setModubleId(String modubleId) {
		this.modubleId = modubleId;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getBeforeTreatment() {
		return beforeTreatment;
	}

	public void setBeforeTreatment(String beforeTreatment) {
		this.beforeTreatment = beforeTreatment;
	}

	public String getPreparedSql() {
		return preparedSql;
	}

	public void setPreparedSql(String preparedSql) {
		this.preparedSql = preparedSql;
	}

	public String getAfterTreatment() {
		return afterTreatment;
	}

	public void setAfterTreatment(String afterTreatment) {
		this.afterTreatment = afterTreatment;
	}

	public String getSearchD() {
		return searchD;
	}

	public void setSearchD(String searchD) {
		this.searchD = searchD;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}			
}
