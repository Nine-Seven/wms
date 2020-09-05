/*
 * 退厂确认
 * hekangli
 */
package com.sealinkin.rodata.action;


import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;
import com.sealinkin.rodata.service.IRodata_OutstockComfirmService;
import com.sealinkin.util.ExceptionUtil;



public class Rodata_OutstockComfirmAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IRodata_OutstockComfirmService rodata_OutstockComfirmImpl;
	private String str;
	private String outUserId;
	private String strSuppliersNo;
	private String strOwnerNo;
	private String strRecedeNo;
	private String strFlag;
	private String strPoNo;
	private String strGrossWeight;//毛重
	/*
	 * 退厂确认 查询头档信息
	 * 
	 */
	public void getRodata_OutstockMComfirm(){
		try{
			ExtListDataBo<Rodata_OutstockMModel> list = 
					rodata_OutstockComfirmImpl.getRodata_OutstockMComfirm(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							getStart(),
							getLimit(),
							strSuppliersNo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 退厂确认 查询明细信息
	 * 
	 */
	public void getRodata_OutstockDComfirm(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=
					rodata_OutstockComfirmImpl.getRodata_OutstockDComfirm(strPoNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),	
					getStart(),
					getLimit());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	/*
	 * 退厂确认 货主、供应商……下拉
	 * 
	 */
	public void getRodata_OutstockComfirmCombo(){
		try{
			List<ComboxBo> list=
					rodata_OutstockComfirmImpl.getRodata_OutstockComfirmCombo(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							super.getMdBdef_DefWorker().getWorkerOwner(),
							getStrFlag(),
							getStr());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	/*
	 * 退厂确认
	 */
	public void tscRoComfirm(){
		try {
			MsgRes msg=new MsgRes();
			
				msg = rodata_OutstockComfirmImpl.tscRoComfirm(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(), 
						getStrOwnerNo(), 
						getStrRecedeNo(), 
						strGrossWeight,
						super.getMdBdef_DefWorker().getWorkerNo(), 
						super.getMdBdef_DefWorker().getWorkSpaceNo());
				
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	
	
	
	public IRodata_OutstockComfirmService getRodata_OutstockComfirmImpl() {
		return rodata_OutstockComfirmImpl;
	}
	public void setRodata_OutstockComfirmImpl(
			IRodata_OutstockComfirmService rodata_OutstockComfirmImpl) {
		this.rodata_OutstockComfirmImpl = rodata_OutstockComfirmImpl;
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
	
	public String getStrSuppliersNo() {
		return strSuppliersNo;
	}
	public void setStrSuppliersNo(String strSuppliersNo) {
		this.strSuppliersNo = strSuppliersNo;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrRecedeNo() {
		return strRecedeNo;
	}
	public void setStrRecedeNo(String strRecedeNo) {
		this.strRecedeNo = strRecedeNo;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrPoNo() {
		return strPoNo;
	}
	public void setStrPoNo(String strPoNo) {
		this.strPoNo = strPoNo;
	}
	public String getStrGrossWeight() {
		return strGrossWeight;
	}
	public void setStrGrossWeight(String strGrossWeight) {
		this.strGrossWeight = strGrossWeight;
	}

	
}
