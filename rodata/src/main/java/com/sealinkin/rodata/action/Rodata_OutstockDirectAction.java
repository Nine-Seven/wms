/*
 * 退厂发单
 */
package com.sealinkin.rodata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDirectModel;
import com.sealinkin.rodata.service.IRodata_OutstockDirectService;
import com.sealinkin.util.ExceptionUtil;


public class Rodata_OutstockDirectAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IRodata_OutstockDirectService rodata_OutstockDirectImpl;
	private String str;
	private String recedeNo;
	private String supplierNo;
	private String wheresql;
	private String flag;
	private String supplier;
	private String sendWorker;
	private String sourceNo;
	
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getSendWorker() {
		return sendWorker;
	}
	public void setSendWorker(String sendWorker) {
		this.sendWorker = sendWorker;
	}
	public String getRecedeNo() {
		return recedeNo;
	}
	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public IRodata_OutstockDirectService getRodata_OutstockDirectImpl() {
		return rodata_OutstockDirectImpl;
	}
	public void setRodata_OutstockDirectImpl(
			IRodata_OutstockDirectService rodata_OutstockDirectImpl) {
		this.rodata_OutstockDirectImpl = rodata_OutstockDirectImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	/*
	 * 获取退厂发单
	 * zhouhuan
	 */
	public void getRodata_OutstockDirect(){
		try{
			System.out.println(getStr());
			ExtListDataBo<Rodata_OutstockDirectModel> list = 
					rodata_OutstockDirectImpl.getRodata_OutstockDirect(str,getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 供应商和退货单号下拉
	 */
	public void getSupplierAndRecedeNoCombo()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=rodata_OutstockDirectImpl.getSupplierAndRecedeNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,wheresql,flag,0,10);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 发单
	 */
	public void send(){
		try{		
			MsgRes msg=rodata_OutstockDirectImpl.send(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					sendWorker,str,
					super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//发单失败
			e.printStackTrace();
		}
	}
}
