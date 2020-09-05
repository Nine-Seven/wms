package com.sealinkin.ridata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckConfirmGsdService;
import com.sealinkin.util.ExceptionUtil;

public class Ridata_CheckConfirmGsdAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRidata_CheckConfirmGsdService ridata_CheckConfirmGsdImpl;
	private String strQuery;
	private String sCheckNo;
	private String strWheresql;
	private String SUntreadNo;
	private String SCheckNo;
	private String ownerNo;
	private String dockNo;
	private String workerNo;
	private String jsonDetail;
	
	//获取头档信息
	public void queryCheckM(){
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Ridata_CheckMModel> list=ridata_CheckConfirmGsdImpl.queryCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strQuery,
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取明细
	public void queryCheckD(){
		try 
		{
			ExtListDataBo<Ridata_CheckPalTmpModel> list=ridata_CheckConfirmGsdImpl.queryCheckD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),this.getStrWheresql());
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//整单确认
	public void checkPalTmp(){
		try 
		{
			MsgRes msg  =ridata_CheckConfirmGsdImpl.tscCheckPalTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),this.getOwnerNo(),
					this.getSUntreadNo(),  this.getSCheckNo(),this.getWorkerNo(),this.getDockNo()
					);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取商品品质
	public void getQuatity(){
		try 
		{
			List<ComboxBo> list=ridata_CheckConfirmGsdImpl.getQuatity();
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	//修改明细
	public void saveChenkPalTmp(){
		try {
			MsgRes msg  =ridata_CheckConfirmGsdImpl.saveChenkPalTmp(
					this.getJsonDetail());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	
	
	public IRidata_CheckConfirmGsdService getRidata_CheckConfirmGsdImpl() {
		return ridata_CheckConfirmGsdImpl;
	}
	public void setRidata_CheckConfirmGsdImpl(
			IRidata_CheckConfirmGsdService ridata_CheckConfirmGsdImpl) {
		this.ridata_CheckConfirmGsdImpl = ridata_CheckConfirmGsdImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getsCheckNo() {
		return sCheckNo;
	}
	public void setsCheckNo(String sCheckNo) {
		this.sCheckNo = sCheckNo;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getSUntreadNo() {
		return SUntreadNo;
	}
	public void setSUntreadNo(String sUntreadNo) {
		SUntreadNo = sUntreadNo;
	}
	public String getSCheckNo() {
		return SCheckNo;
	}
	public void setSCheckNo(String sCheckNo) {
		SCheckNo = sCheckNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getDockNo() {
		return dockNo;
	}
	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public String getJsonDetail() {
		return jsonDetail;
	}

	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}
}
