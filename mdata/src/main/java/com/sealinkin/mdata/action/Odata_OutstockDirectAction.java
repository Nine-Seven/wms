/*
 * 移库发单
 * zhouhuan
 */
package com.sealinkin.mdata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.mdata.service.IOdata_OutstockDirectService;
import com.sealinkin.odata.model.Odata_OutstockDirectModel;

@SuppressWarnings("serial")
public class Odata_OutstockDirectAction extends CommAction{
	//private static final Logger logger = Logger.getLogger(Odata_OutstockDirectAction.class);
	private IOdata_OutstockDirectService odata_OutstockDirectImpl;
	private MsgRes msgRes;
	private String str;
	private String str2;
	private String areaNo;
	private String ownerNo;
	private String outstockType;
	private String celltype;
	private String outstockworker;
	private String workerNo;
	private String operateType;
	

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getWorkerNo() {
		return workerNo;
	}

	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}

	public String getCelltype() {
		return celltype;
	}

	public void setCelltype(String celltype) {
		this.celltype = celltype;
	}

	public String getOutstockworker() {
		return outstockworker;
	}

	public void setOutstockworker(String outstockworker) {
		this.outstockworker = outstockworker;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public String getOutstockType() {
		return outstockType;
	}

	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}

	public IOdata_OutstockDirectService getOdata_OutstockDirectImpl() {
		return odata_OutstockDirectImpl;
	}

	public void setOdata_OutstockDirectImpl(
			IOdata_OutstockDirectService odata_OutstockDirectImpl) {
		this.odata_OutstockDirectImpl = odata_OutstockDirectImpl;
	}

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

	/*
	 * 获取移库储区
	 * zhouhuan
	 */
	public void getOdata_OutstockDirect(){
		try{
			ExtListDataBo<Odata_OutstockDirectModel> list=odata_OutstockDirectImpl.getAreaList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					getStr(),super.getMdBdef_DefWorker().getWarehouseNo(),getStart(),getLimit());
			//super.writeObj(list);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获取移库发单信息
	 * zhouhuan
	 */
	public void getOdata_OutstockDirectList(){
		
		try{
			ExtListDataBo<Odata_OutstockDirectModel> list
			=odata_OutstockDirectImpl.getOdata_OutstockDirectList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					areaNo,super.getMdBdef_DefWorker().getWarehouseNo(),
					ownerNo,outstockType,getStart(),getLimit());
			//super.writeObj(list);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 移库发单->人工移库和安全量补货发单
	 * zhouhuan
	 */
	public void send(){
		try{		
			MsgRes msg=odata_OutstockDirectImpl.send(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					workerNo,celltype,outstockworker,getStr(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),this.getOutstockType());
			super.writeObj(msg);
		}catch (Exception e) {
			super.writeObj(new MsgRes(false,"发单失败",e.getMessage()));
			e.printStackTrace();
		}
	}
	
	
	/*//波次号下拉
	public void getOdata_GetWaveCombo() {
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_OutstockDirectImpl.getOdata_GetWaveCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}
