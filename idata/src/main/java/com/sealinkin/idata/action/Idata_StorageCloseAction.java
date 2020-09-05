package com.sealinkin.idata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.idata.model.Idata_ImportSdModel;
import com.sealinkin.idata.service.Iidata_StorageCloseService;
import com.sealinkin.util.ExceptionUtil;

public class Idata_StorageCloseAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Iidata_StorageCloseService idata_StorageCloseImpl;
	private String str;
	private String strWheresql;
	private String strFlag;
	private String strOwnerNo;
	private String strJsonMaster;
	private String strJsonDetail1;
	private String cellNo;
	private String date;
	private String SImportNo;
	private String articleNo;
	private String strQuery;
	
	//委托业主下拉 
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_StorageCloseImpl.queryOwnerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	// 获取进货汇总单下拉
	public void queryIdataImportMMCombo()
	{
		try 
		{
			List<Idata_ImportSdModel> list=idata_StorageCloseImpl.queryIdataImportMMCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,
					strFlag,
					strWheresql,
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取储位
	public void getCdef_DefCellCombo(){
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = idata_StorageCloseImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					str, strWheresql,0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    //获取验收明细 
	public void queryIdataImportSd()
	{
		try 
		{
			ExtListDataBo<Idata_ImportSdModel> list=idata_StorageCloseImpl.queryIdataImportSd(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	// 保存流水板
	public void saveCheck()
	{
		try {
			MsgRes msg=idata_StorageCloseImpl.tscSaveCheck(strJsonMaster, strJsonDetail1,this.getCellNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void queryLotProduceDate(){
		try {
			
			List<String> list=idata_StorageCloseImpl.queryLotProduceDate(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					this.getDate(), this.getSImportNo(),this.getArticleNo());
			super.writeStr(covtListToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}

	public Iidata_StorageCloseService getIdata_StorageCloseImpl() {
		return idata_StorageCloseImpl;
	}
	public void setIdata_StorageCloseImpl(
			Iidata_StorageCloseService idata_StorageCloseImpl) {
		this.idata_StorageCloseImpl = idata_StorageCloseImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrJsonMaster() {
		return strJsonMaster;
	}

	public void setStrJsonMaster(String strJsonMaster) {
		this.strJsonMaster = strJsonMaster;
	}

	public String getStrJsonDetail1() {
		return strJsonDetail1;
	}

	public void setStrJsonDetail1(String strJsonDetail1) {
		this.strJsonDetail1 = strJsonDetail1;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSImportNo() {
		return SImportNo;
	}

	public void setSImportNo(String sImportNo) {
		SImportNo = sImportNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	
}
