package com.sealinkin.comm.action;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.ICheckCellService;
import com.sealinkin.util.ExceptionUtil;

public class CheckCellAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private ICheckCellService checkCellImpl;
	private String strOwnerNo;
	private String strCellNo;
	
	public ICheckCellService getCheckCellImpl() {
		return checkCellImpl;
	}
	public void setCheckCellImpl(ICheckCellService checkCellImpl) {
		this.checkCellImpl = checkCellImpl;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrCellNo() {
		return strCellNo;
	}
	public void setStrCellNo(String strCellNo) {
		this.strCellNo = strCellNo;
	}
	/**
	 * 储位校验
	 */
	public void checkCell(){
		try {
			MsgRes msg=checkCellImpl.checkCell(
					strOwnerNo,strCellNo,
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseName());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
}
