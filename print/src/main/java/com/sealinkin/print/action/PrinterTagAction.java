package com.sealinkin.print.action;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.print.service.IPrinterTagService;
import com.sealinkin.util.ExceptionUtil;


public class PrinterTagAction extends CommAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IPrinterTagService printerTagImpl;
	private String strQuery;
	private String printNumber;
	private String barcode;
	private String articleNo;
	
	public void getDefarticle(){
		try 
		{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefarticleModel> pageListBo = printerTagImpl.getDefarticle(
					super.getMdBdef_DefWorker().getWorkerOwner(), strQuery, poPagebo);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void sendTask(){
		MsgRes msgRes=null;
		try
		{	
			msgRes =printerTagImpl.sendTask(
					this.getPrintNumber(),
					this.getBarcode(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					this.getArticleNo());
			
		}catch (Exception e) {
			msgRes = new MsgRes(false,e.getMessage(),"");
			e.printStackTrace();
		}	
		super.writeObj(msgRes);
	}
	
	public void setPrinterTagImpl(IPrinterTagService printerTagImpl) {
		this.printerTagImpl = printerTagImpl;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(String printNumber) {
		this.printNumber = printNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}	
	
	
}
