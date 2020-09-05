package com.sealinkin.odata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportMModel;

import com.sealinkin.odata.model.Odata_CarReceiptModel;
import com.sealinkin.odata.service.IOdata_CarReceiptService;
import com.sealinkin.util.ExceptionUtil;

public class Odata_CarReceiptAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IOdata_CarReceiptService odata_CarReceiptImpl;
    private String strQuery;
	//取配送物流箱信息列表
	public void getCarReceiptList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_CarReceiptModel> list=odata_CarReceiptImpl.getCarReceiptList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//保存
	public void save(){
		try {
			MsgRes msg=odata_CarReceiptImpl.save(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IOdata_CarReceiptService getOdata_CarReceiptImpl() {
		return odata_CarReceiptImpl;
	}
	public void setOdata_CarReceiptImpl(
			IOdata_CarReceiptService odata_CarReceiptImpl) {
		this.odata_CarReceiptImpl = odata_CarReceiptImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
}
