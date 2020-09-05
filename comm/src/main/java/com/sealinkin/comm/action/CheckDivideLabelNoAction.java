package com.sealinkin.comm.action;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.ICheckDivideLabelNoService;
import com.sealinkin.util.ExceptionUtil;

public class CheckDivideLabelNoAction extends CommAction{
	
	private static final long serialVersionUID = 1L;
	
	private ICheckDivideLabelNoService checkDivideLabelNoImpl;
	private String strCustContainerNo;
	private String strCustNo;
	private String strSContainerNo;
	private String strArticleNo;
	private String deliverObj;
	private String ownerNo;
	/**
	 *分播回单校验目的标签号
	 */
	public void checkDivideLabelNo()
	{
		try {
			MsgRes msg = checkDivideLabelNoImpl.checkDivideLabelNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),ownerNo,
					strSContainerNo,
					strCustContainerNo,
					strCustNo,
					strArticleNo,deliverObj);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}

	public ICheckDivideLabelNoService getCheckDivideLabelNoImpl() {
		return checkDivideLabelNoImpl;
	}

	public void setCheckDivideLabelNoImpl(
			ICheckDivideLabelNoService checkDivideLabelNoImpl) {
		this.checkDivideLabelNoImpl = checkDivideLabelNoImpl;
	}


	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getStrCustContainerNo() {
		return strCustContainerNo;
	}

	public void setStrCustContainerNo(String strCustContainerNo) {
		this.strCustContainerNo = strCustContainerNo;
	}

	public String getStrCustNo() {
		return strCustNo;
	}

	public void setStrCustNo(String strCustNo) {
		this.strCustNo = strCustNo;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}

	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}

	public String getStrSContainerNo() {
		return strSContainerNo;
	}

	public void setStrSContainerNo(String strSContainerNo) {
		this.strSContainerNo = strSContainerNo;
	}

	public String getDeliverObj() {
		return deliverObj;
	}

	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}

}
