/*
 * 移库回单
 */
package com.sealinkin.mdata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.mdata.service.IOdata_OutstockDService;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;

@SuppressWarnings("serial")
public class Odata_OutstockDAction extends CommAction{
	private IOdata_OutstockDService odata_OutstockDImpl;
	private MsgRes msgRes;
	private String str;
	private String flag;
	private String strQuery;
	private String strTaskType;
	

	public MsgRes getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public IOdata_OutstockDService getOdata_OutstockDImpl() {
		return odata_OutstockDImpl;
	}

	public void setOdata_OutstockDImpl(IOdata_OutstockDService odata_OutstockDImpl) {
		this.odata_OutstockDImpl = odata_OutstockDImpl;
	}

	public String getStrTaskType() {
		return strTaskType;
	}

	public void setStrTaskType(String strTaskType) {
		this.strTaskType = strTaskType;
	}

	/*	
	 * 获取移库回单明细信息
	 * zhouhuan
	 */
	public void getOdata_OutstockD(){
		try{
			ExtListDataBo<Odata_OutstockDModel> list=odata_OutstockDImpl.getOdata_OutstockD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getFlag(),getStr(),getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	
	 * 获取移库回单明细信息
	 * zhouhuan
	 
	public void getOdata_OutstockDDetail(){
		try{
			ExtListDataBo<Odata_OutstockDModel> list=odata_OutstockDImpl.getOdata_OutstockDDetail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(),getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*
	 * 获取移库回单头档信息
	 * lich
	 */
	public void getOdata_OutstockM(){
		try{
			ExtListDataBo<Odata_OutstockMModel> list=odata_OutstockDImpl.getOdata_OutstockM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getFlag(), getStrQuery(), 0, 10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 移库回单》回单
     * zhouhuan
     */
	public void save(){
		try{		
			MsgRes msg=odata_OutstockDImpl.save(
					super.getMdBdef_DefWorker().getWorkerNo(),getStr(),strTaskType);
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"回单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
}
