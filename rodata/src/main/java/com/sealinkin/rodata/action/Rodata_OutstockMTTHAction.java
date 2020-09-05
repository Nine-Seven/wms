/**
 * 天天惠退厂回单
 */
package com.sealinkin.rodata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;
import com.sealinkin.rodata.service.IRodata_OutstockMTTHService;
import com.sealinkin.util.ExceptionUtil;

public class Rodata_OutstockMTTHAction extends CommAction {
	
	private static final long serialVersionUID = 1L;
	private IRodata_OutstockMTTHService rodata_OutstockMTTHImpl;
	private String strQuery;
	private String flag;
	private String str;
	private String outUserId;
	
	//获取退厂回单单头
	public void getRodataOutstockM(){
		try{
			ExtListDataBo<Rodata_OutstockMModel> list = 
					this.rodata_OutstockMTTHImpl.getRodataOutstockM(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							getStart(),getLimit(),strQuery);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 获取退厂回单明细
	
	public void getRodata_OutstockD(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=this.rodata_OutstockMTTHImpl.getRodata_OutstockD(
					this.getStrQuery(),this.getFlag(),getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 保存退厂回单(表单)
	 */
	public void savePaper(){
		try {
			MsgRes msg = this.rodata_OutstockMTTHImpl.savePaper(
					super.getMdBdef_DefWorker().getWorkerNo(),
					outUserId,getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//保存失败
			e.printStackTrace();
		}
	}
	
	public void saveLabel(){
		try {
			MsgRes msg = this.rodata_OutstockMTTHImpl.saveLabel(
					super.getMdBdef_DefWorker().getWorkerNo(),
					outUserId,getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//保存失败
			e.printStackTrace();
		}
	}
	
	public IRodata_OutstockMTTHService getRodata_OutstockMTTHImpl() {
		return rodata_OutstockMTTHImpl;
	}
	public void setRodata_OutstockMTTHImpl(
			IRodata_OutstockMTTHService rodata_OutstockMTTHImpl) {
		this.rodata_OutstockMTTHImpl = rodata_OutstockMTTHImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getOutUserId() {
		return outUserId;
	}
	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
	}
	
}
