package com.sealinkin.comm.action;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;

public class GetArticleForBarcodeAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	private String strBarcode="";
	private String strOwnerNo="";
	private String strSImportNo="";
	
	/**
	 * 扫描条码
	 */
	public void queryBarcode(){
		try {
			MsgRes msg=getArticleForBarcodeImpl.checkBarcode(
					super.getMdBdef_DefWorker().getWarehouseNo(), strBarcode, strOwnerNo, strSImportNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}

	public String getStrBarcode() {
		return strBarcode;
	}

	public void setStrBarcode(String strBarcode) {
		this.strBarcode = strBarcode;
	}

	public String getStrSImportNo() {
		return strSImportNo;
	}
	public void setStrSImportNo(String strSImportNo) {
		this.strSImportNo = strSImportNo;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	
}
