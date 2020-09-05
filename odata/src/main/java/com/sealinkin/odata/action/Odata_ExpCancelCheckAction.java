package com.sealinkin.odata.action;


import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_ExpCancelDModel;
import com.sealinkin.odata.model.Odata_ExpCancelMModel;
import com.sealinkin.odata.service.IOdata_ExpCancelCheckService;
import com.sealinkin.util.ExceptionUtil;

public class Odata_ExpCancelCheckAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IOdata_ExpCancelCheckService odata_ExpCancelCheckImpl;
	private String str;
	private String strFlag;
	private String strQuery;
	private String expNo;
	private String cancelNo;
	private String handleflag;
	private String ownerNo;
	
	
	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getHandleflag() {
		return handleflag;
	}

	public void setHandleflag(String handleflag) {
		this.handleflag = handleflag;
	}

	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	public String getCancelNo() {
		return cancelNo;
	}

	public void setCancelNo(String cancelNo) {
		this.cancelNo = cancelNo;
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

	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public IOdata_ExpCancelCheckService getOdata_ExpCancelCheckImpl() {
		return odata_ExpCancelCheckImpl;
	}

	public void setOdata_ExpCancelCheckImpl(
			IOdata_ExpCancelCheckService odata_ExpCancelCheckImpl) {
		this.odata_ExpCancelCheckImpl = odata_ExpCancelCheckImpl;
	}

	/**
	 * 获取病单头档信息
	 */
	public void getCancelCheck_MList(){
		try{		
			ExtListDataBo<Odata_ExpCancelMModel> list=odata_ExpCancelCheckImpl.getCancelCheck_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrQuery(),
					0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取病单明细信息
	 */
	public void getCancelCheck_DList(){
		try{		
			ExtListDataBo<Odata_ExpCancelDModel> list=odata_ExpCancelCheckImpl.getCancelCheck_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),
					0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 病单审核，货主、病单单号下拉
     */
	public void getOdata_CancelCheckCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_ExpCancelCheckImpl.getOdata_CancelCheckCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					0,100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//审核
		public void sendCancel(){
			try {
				MsgRes msg = odata_ExpCancelCheckImpl.sendCancel(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerNo(),
						ownerNo,
						super.getMdBdef_DefWorker().getWorkSpaceNo(),
						expNo,cancelNo,handleflag);
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
		}
}
