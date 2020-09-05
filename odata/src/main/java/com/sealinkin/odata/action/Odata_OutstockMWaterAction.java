package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;
import com.sealinkin.odata.service.IOdata_OutstockMReceipt;
import com.sealinkin.odata.service.IOdata_OutstockMWaterService;

/**
 * 流水标签回单
 * @author 潘振兴
 *
 */
public class Odata_OutstockMWaterAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private IOdata_OutstockMWaterService odata_OutstockMWaterImpl;
	private String str;
	private String strFlag;
	private String strQuery;
	private String strOutstockNo;
	private String strSendFlag;
	private String strPickType;
	private String strCheckFlag;
	private String strWave;
	private String strBatch;
	private String strOutstockName;
	private String strB2CYesOrNo;
	
	
	public IOdata_OutstockMWaterService getOdata_OutstockMWaterImpl() {
		return odata_OutstockMWaterImpl;
	}
	public void setOdata_OutstockMWaterImpl(
			IOdata_OutstockMWaterService odata_OutstockMWaterImpl) {
		this.odata_OutstockMWaterImpl = odata_OutstockMWaterImpl;
	}
	public String getStrB2CYesOrNo() {
		return strB2CYesOrNo;
	}
	public void setStrB2CYesOrNo(String strB2CYesOrNo) {
		this.strB2CYesOrNo = strB2CYesOrNo;
	}
	public String getStrOutstockName() {
		return strOutstockName;
	}
	public void setStrOutstockName(String strOutstockName) {
		this.strOutstockName = strOutstockName;
	}
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrOutstockNo() {
		return strOutstockNo;
	}
	public void setStrOutstockNo(String strOutstockNo) {
		this.strOutstockNo = strOutstockNo;
	}
	public String getStrSendFlag() {
		return strSendFlag;
	}
	public void setStrSendFlag(String strSendFlag) {
		this.strSendFlag = strSendFlag;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrPickType() {
		return strPickType;
	}
	public void setStrPickType(String strPickType) {
		this.strPickType = strPickType;
	}
	public String getStrCheckFlag() {
		return strCheckFlag;
	}
	public void setStrCheckFlag(String strCheckFlag) {
		this.strCheckFlag = strCheckFlag;
	}
	
	public String getStrWave() {
		return strWave;
	}
	public void setStrWave(String strWave) {
		this.strWave = strWave;
	}
	public String getStrBatch() {
		return strBatch;
	}
	public void setStrBatch(String strBatch) {
		this.strBatch = strBatch;
	}
	/**
	 * 获取下架单头档信息
	 * @author 潘振兴
	 */
	public void getWaterM(){
		try{		
			ExtListDataBo<Odata_OutstockMModel> list=odata_OutstockMWaterImpl.getWaterM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStrWave(),
					getStrBatch(),0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取下架单明细标签号码列表
	 * @author 潘振兴
	 */
	public void getWaterDLabel(){
		try{		
			ExtListDataBo<Odata_OutstockDModel> list=odata_OutstockMWaterImpl.getWaterDLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					strSendFlag,0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取下架单明细信息
	 * @author 潘振兴
	 */
	public void getWaterD(){
		try{		
			ExtListDataBo<Odata_OutstockDModel> list=odata_OutstockMWaterImpl.getWaterD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					strSendFlag,0,10000);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 流水标签回单波次……下拉
     * @author 潘振兴
     */
	public void getWaveWaterCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_OutstockMWaterImpl.getWaveWaterCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					strSendFlag,
					strCheckFlag,strB2CYesOrNo,0,100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 流水标签回单批次……下拉
     * @author 潘振兴
     */
	public void getBatchWaterCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_OutstockMWaterImpl.getBatchWaterCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStrWave(),
					strSendFlag,
					strCheckFlag,0,100);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * 流水标签回单
     * @author 潘振兴
     */
	public void saveLabel(){
		try{		
			MsgRes msg=odata_OutstockMWaterImpl.saveLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOutstockName,
					strOutstockNo);
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"回单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
     * 流水标签零回单
     * 
     */
	public void saveLabelZero(){
		try{		
			MsgRes msg=odata_OutstockMWaterImpl.saveLabelZero(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strOutstockName,
					strOutstockNo);
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"回单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
}
