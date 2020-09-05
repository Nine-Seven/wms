package com.sealinkin.odata.action;


import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDirectModel;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.util.ExceptionUtil;

/**
 * 模块名称：拣货发单
 * 模块编码：3301和3401
 * 创建：周欢
 * 修改：贺康利
 */
public class Odata_OutstockMAction extends CommAction{
	private static final long serialVersionUID = 1L;
	private  IOdata_OutstockM odata_OutstockMImpl;
	private String str;
	private String workerNo;
	private String dockNo;
	private String strFlag;
	private String strSendFlag;//1:自动发单，2：手动发单
	private String strJsonDetail;
	private String strOwnerNo;
	private String strPrintPaperType;//是否打印表单标识，0：不打印，1，打印  
	private String strOutStockType;//下架类型
	private String ownerNo;
	private String expType;
	private String waveNo;
	private String areaNo;
	private String operateType;
	private String industryFlag;
	private String batchNo;
	
	
	/**
	 * 获取拣货发单信息
	 * @author 周欢
	 */
	public void getOdata_OutstockDirect()
	{
		try{		
			ExtListDataBo<Odata_OutstockDirectModel> list=odata_OutstockMImpl.getOdataOutStockDirect(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),
					getStr(),
					strOwnerNo,
					strSendFlag,getIndustryFlag());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * 拣货按客户发单过程
	 * 
	 */
	public void send()
	{
		try{		
			MsgRes msg=odata_OutstockMImpl.send(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strOutStockType,strPrintPaperType,workerNo,
					str,ownerNo,expType,waveNo,areaNo,operateType,batchNo);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));
			e.printStackTrace();
		}
	}
	/**
	 * 拣货按客户发单过程--按配送对象发单
	 * 
	 */
	public void sendObj()
	{
		try{		
			MsgRes msg=odata_OutstockMImpl.sendObj(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					strOutStockType,
					strPrintPaperType,
					getWorkerNo(),
					getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			super.writeObj(new MsgRes(false,"发单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	
    /**
     * 拣货发单 出货单别、波次……下拉
     * @author 周欢
     */
	public void getOdataGetCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_OutstockMImpl.getOdata_GetCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(),getStr(),strSendFlag,0,100,getIndustryFlag());
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 拣货批量发单过程
	 */
	public void sendTscAuto(){
		try {
			MsgRes msg=odata_OutstockMImpl.sendTscAuto(
					strJsonDetail,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	//自动发单    2016.06.22 by czh
	public void sendOrderAuto(){
		try {
			MsgRes msg=odata_OutstockMImpl.sendOrderAuto(
					strJsonDetail,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
	}
	
	//获取自动发单的批次单号
	public void getBatchSendOrder(){
		try {
			List<Odata_OutstockDirectModel> list = odata_OutstockMImpl.getBatchSendOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), str);
			super.writeArray(list);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false,e.getMessage(),""));
		}
		
	}
	

	public IOdata_OutstockM getOdata_OutstockMImpl() {
		return odata_OutstockMImpl;
	}

	public void setOdata_OutstockMImpl(IOdata_OutstockM odata_OutstockMImpl) {
		this.odata_OutstockMImpl = odata_OutstockMImpl;
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

	public String getWorkerNo() {
		return workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public String getDockNo() {
		return dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	public String getStrSendFlag() {
		return strSendFlag;
	}

	public void setStrSendFlag(String strSendFlag) {
		this.strSendFlag = strSendFlag;
	}

	public String getStrJsonDetail() {
		return strJsonDetail;
	}
	public void setStrJsonDetail(String strJsonDetail) {
		this.strJsonDetail = strJsonDetail;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrPrintPaperType() {
		return strPrintPaperType;
	}
	public void setStrPrintPaperType(String strPrintPaperType) {
		this.strPrintPaperType = strPrintPaperType;
	}
	public String getStrOutStockType() {
		return strOutStockType;
	}
	public void setStrOutStockType(String strOutStockType) {
		this.strOutStockType = strOutStockType;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getExpType() {
		return expType;
	}
	public void setExpType(String expType) {
		this.expType = expType;
	}
	public String getWaveNo() {
		return waveNo;
	}
	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getIndustryFlag() {
		return industryFlag;
	}
	public void setIndustryFlag(String industryFlag) {
		this.industryFlag = industryFlag;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	
}
