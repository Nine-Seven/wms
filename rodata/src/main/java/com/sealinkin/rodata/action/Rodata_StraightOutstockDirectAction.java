/*
 * 退厂发单(天天惠)
 */
package com.sealinkin.rodata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDirectModel;
import com.sealinkin.rodata.service.IRodata_StraightOutstockDirectService;
import com.sealinkin.util.ExceptionUtil;


public class Rodata_StraightOutstockDirectAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IRodata_StraightOutstockDirectService rodata_StraightOutstockDirectImpl;
	private String str;
	private String strQuery;
	private String recedeNo;
	private String supplierNo;
	private String wheresql;
	private String flag;
	private String supplier;
	private String sendWorker;
	private String sourceNo;
	private String strWaveNo;
	private String strSupplierNo;
	private String strClassType;
	//获取退厂发单
	public void getRodata_OutstockDirect(){
		try{
			ExtListDataBo<Rodata_OutstockDirectModel> list = 
					rodata_StraightOutstockDirectImpl.getRodata_OutstockDirect(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							str,getStart(),getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 获得泼次号下拉
	public void getWaveNoComboList()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=rodata_StraightOutstockDirectImpl.getWaveNoComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,0,100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 供应商下拉
	 */
	public void getSupplierCombo()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=rodata_StraightOutstockDirectImpl.getSupplierCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,wheresql,flag,0,100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 获得退货类型下拉
	public void getClassTypeComboList()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=rodata_StraightOutstockDirectImpl.getClassTypeComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,wheresql,flag,0,100);
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
			MsgRes msg=rodata_StraightOutstockDirectImpl.send(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					sendWorker,strWaveNo,strSupplierNo,
					super.getMdBdef_DefWorker().getWorkSpaceNo(),strClassType);
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//发单失败
			e.printStackTrace();
		}
	}
	
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
	public IRodata_StraightOutstockDirectService getRodata_StraightOutstockDirectImpl() {
		return rodata_StraightOutstockDirectImpl;
	}
	public void setRodata_StraightOutstockDirectImpl(
			IRodata_StraightOutstockDirectService rodata_StraightOutstockDirectImpl) {
		this.rodata_StraightOutstockDirectImpl = rodata_StraightOutstockDirectImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
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
	public String getStrWaveNo() {
		return strWaveNo;
	}
	public void setStrWaveNo(String strWaveNo) {
		this.strWaveNo = strWaveNo;
	}
	public String getStrSupplierNo() {
		return strSupplierNo;
	}
	public void setStrSupplierNo(String strSupplierNo) {
		this.strSupplierNo = strSupplierNo;
	}
	public String getStrClassType() {
		return strClassType;
	}
	public void setStrClassType(String strClassType) {
		this.strClassType = strClassType;
	}
	
}
