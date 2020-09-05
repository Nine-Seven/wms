package com.sealinkin.fcdata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.fcdata.model.Fcdata_CheckDModel;
import com.sealinkin.fcdata.model.Fcdata_CheckDirectModel;
import com.sealinkin.fcdata.model.Fcdata_CheckMModel;
import com.sealinkin.fcdata.service.IFcdata_CheckService;
import com.sealinkin.util.ExceptionUtil;

/*
 *  @初盘发单action
 *  @author 周欢
 */
public class Fcdata_CheckAction extends CommAction{

	private static final long serialVersionUID = 1L;

	private IFcdata_CheckService fcdata_CheckImpl;
	private String strWheresql;
	private String str;
	private String strFlag;
	private String strFcdataType;
	private String strCheckType;
	private String strDifferentFlag;
	private String strPlanNo;
	private String strOwnerNo;
	public void setfcdata_CheckImpl(IFcdata_CheckService fcdataCheckImpl) {
		fcdata_CheckImpl = fcdataCheckImpl;
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

	public IFcdata_CheckService getfcdata_CheckImpl() {
		return fcdata_CheckImpl;
	}

	public String getStrFcdataType() {
		return strFcdataType;
	}

	public void setStrFcdataType(String strFcdataType) {
		this.strFcdataType = strFcdataType;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrCheckType() {
		return strCheckType;
	}

	public void setStrCheckType(String strCheckType) {
		this.strCheckType = strCheckType;
	}

	public String getStrDifferentFlag() {
		return strDifferentFlag;
	}

	public void setStrDifferentFlag(String strDifferentFlag) {
		this.strDifferentFlag = strDifferentFlag;
	}

	public String getStrPlanNo() {
		return strPlanNo;
	}

	public void setStrPlanNo(String strPlanNo) {
		this.strPlanNo = strPlanNo;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	/////////////////////////////////初盘发单/////////////////////////////////////////////////////
	//获得盘点类别
	public void getFcdataTypeCombo(){
		try {
			List<ComboxBo> list = fcdata_CheckImpl.getFcdataTypeCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),getStrFlag(), 0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//获得计划单号
	public void getPlanNoCombo(){
		try {
			List<ComboxBo> list = fcdata_CheckImpl.getPlanNoCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrFlag(), strFcdataType, 0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获得盘点成单条件和派单的明细
	public void getCheckDirect(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Fcdata_CheckDirectModel> list=fcdata_CheckImpl.getCheckDirect(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(),
					strFlag, 
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 切单 
	public void tscCut8201(){
		try {
			MsgRes msg=fcdata_CheckImpl.tscCut8201(
					getStr(), 
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获得盘点类别
	public void CheckType(){
		try {
			List<String> list = fcdata_CheckImpl.CheckType(
				super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				super.getMdBdef_DefWorker().getWorkerOwner(), 
				strPlanNo, 0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 初盘发单
	public void send(){
		try {
			MsgRes msg=fcdata_CheckImpl.send(getStr(),super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////复盘、三盘回单/////////////////////////////////////////
	
	//获得盘点单头档
	public void getCheckM(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Fcdata_CheckMModel> list=fcdata_CheckImpl.getCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strCheckType,strDifferentFlag,pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获得盘点单明细
	public void getCheckD(){
		try {
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Fcdata_CheckDModel> list=fcdata_CheckImpl.getCheckD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql, strCheckType,
					pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复盘/三盘发单
	 */
	public void sendAgain(){
		try {
			MsgRes msg=fcdata_CheckImpl.sendAgain(getStr(),strCheckType,super.getMdBdef_DefWorker().getWorkSpaceNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 结束盘点
	 */
	public void sendEndFcdata(){
		try {
			MsgRes msg=fcdata_CheckImpl.sendEndFcdata(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(), 
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
    ////////////////////////////////////////////////////////////////////////////////////////////	
}
