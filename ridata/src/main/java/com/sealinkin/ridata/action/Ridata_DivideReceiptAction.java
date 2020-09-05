package com.sealinkin.ridata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.ridata.service.IRidata_DivideReceiptService;


/**
 * 返配批次管理action
 * @author hcx
 */
public class Ridata_DivideReceiptAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IRidata_DivideReceiptService ridata_DivideReceiptImpl;
	private String strLabelNo;
	private String strCellNo;
	
	public void divide(){
		try {
			MsgRes msg = ridata_DivideReceiptImpl.divide(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					strLabelNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeBox(){
		try {
			MsgRes msg = ridata_DivideReceiptImpl.closeBox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					strCellNo,
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public IRidata_DivideReceiptService getRidata_DivideReceiptImpl() {
		return ridata_DivideReceiptImpl;
	}
	public void setRidata_DivideReceiptImpl(
			IRidata_DivideReceiptService ridata_DivideReceiptImpl) {
		this.ridata_DivideReceiptImpl = ridata_DivideReceiptImpl;
	}

	public String getStrLabelNo() {
		return strLabelNo;
	}

	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
	}

	public String getStrCellNo() {
		return strCellNo;
	}

	public void setStrCellNo(String strCellNo) {
		this.strCellNo = strCellNo;
	}

}
