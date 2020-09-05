package com.sealinkin.fcdata.action;

import java.util.List;

import javax.management.MXBean;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.fcdata.model.Fcdata_CheckDModel;
import com.sealinkin.fcdata.model.Fcdata_CheckMModel;
import com.sealinkin.fcdata.service.IFcdata_RequestService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 盘点回单Action
 * @author JUN
 *
 */
public class Fcdata_RequestAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IFcdata_RequestService fcdata_RequestImpl;
	private String strWheresql;
	private String strCheckNo;
	private String strDetail;
	private String strCheckType="2";
	private String strFcdataType="1";
	private String strFlag;
	private String strLabelNo;
	private String strSubLabelNo;
	private String strCellNo;
	private String articleNo;
	private String produceDate;
	
	////////////////////////////////////////初盘回单///////////////////////////////////////////////////
	
	// 获取初盘回单头档 
	public void queryFcdataCheckM()
	{
		try 
		{
			PageBo poPageBo = new PageBo(getStart(),getLimit());
			ExtListDataBo<Fcdata_CheckMModel> list = fcdata_RequestImpl.queryFcdataCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strFcdataType, poPageBo);
			super.writeObj(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	// 获取初盘回单明细
	public void queryFcdataCheckD()
	{
		try 
		{
			ExtListDataBo<Fcdata_CheckDModel> list = fcdata_RequestImpl.queryFcdataCheckD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					"", strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//盘点回单》保存
	public void saveFcdata_CheckD()
	{
		MsgRes msg=null;
		try 
		{
			msg=fcdata_RequestImpl.tscSaveFcdataCheckD(strDetail);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();	
			msg=new MsgRes(false,ExceptionUtil.getCacseMessage(e),"");
			super.writeObj(msg);
		}
	}
	
	//根据单号填充储位
	public void queryCdefDefCellCombo()
	{
		try 
		{
			List<ComboxBo> list=fcdata_RequestImpl.queryCdefDefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strFlag,strCheckNo, strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));	
		}
	}
	//盘点回单--新增品项--根据货位找货主
	public void queryOwnerCellNo(){
		try{
			MsgRes msg = fcdata_RequestImpl.queryOwnerCellNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));	
		}
	}
	
	//盘点回单>>商品下拉选择
	public void queryArticleInfo()
	{
		try 
		{
			List<Fcdata_CheckDModel> list=fcdata_RequestImpl.queryArticleInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));	
		}
	}
	
	// 根据商品编号、生产日期查批号下拉
	public void queryLot()
	{
		try 
		{
			List<Fcdata_CheckDModel> list=fcdata_RequestImpl.queryLot(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					articleNo,
					produceDate);
			super.writeArrayFtDate(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	// 无差异保存 
	public void noDifferenceSave()
	{
		try {
			MsgRes msg=fcdata_RequestImpl.tscNoDifferenceSave(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	// 回单确认
	public void fcdataConfirm()
	{
		try {
			MsgRes msg=fcdata_RequestImpl.tscFcdataconfirm(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	// 零回单
	public void zeroReceipt()
	{
		try 
		{
			MsgRes msg=fcdata_RequestImpl.tscZeroReceiptSave(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//校验板号是否存在库存表，并且是否与储位匹配
	
	public void checkLabelNo()
	{
		try {
			MsgRes msg=fcdata_RequestImpl.checkLabelNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strLabelNo, 
					strCellNo);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 校验板号与子板号是否存在库存表，并且是否与储位匹配
	 */
	public void checkLabelNoAndSubLabelNo()
	{
		try {
			MsgRes msg=fcdata_RequestImpl.checkLabelNoAndSubLabelNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strLabelNo,
					strSubLabelNo, 
					strCellNo);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 获取复盘/三盘回单头档
	 */
	public void querySecondFcdataCheckM()
	{
		try 
		{
			PageBo poPageBo = new PageBo(getStart(),getLimit());
			ExtListDataBo<Fcdata_CheckMModel> list = fcdata_RequestImpl.querySecondFcdataCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strCheckType, poPageBo);
			super.writeObj(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 根据批号查日期
	 */
	public void getExpireDateByLot()
	{
		try 
		{
			List<Fcdata_CheckDModel> list=fcdata_RequestImpl.getExpireDateByLot(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeArrayFtDate(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	

	public void setFcdata_RequestImpl(IFcdata_RequestService fcdata_RequestImpl) {
		this.fcdata_RequestImpl = fcdata_RequestImpl;
	}

	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrCheckNo() {
		return strCheckNo;
	}
	public void setStrCheckNo(String strCheckNo) {
		this.strCheckNo = strCheckNo;
	}

	public String getStrDetail() {
		return strDetail;
	}
	public void setStrDetail(String strDetail) {
		this.strDetail = strDetail;
	}

	public String getStrCheckType() {
		return strCheckType;
	}
	public void setStrCheckType(String strCheckType) {
		this.strCheckType = strCheckType;
	}

	public String getStrFcdataType() {
		return strFcdataType;
	}
	public void setStrFcdataType(String strFcdataType) {
		this.strFcdataType = strFcdataType;
	}

	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrLabelNo() {
		return strLabelNo;
	}
	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
	}

	public String getStrSubLabelNo() {
		return strSubLabelNo;
	}
	public void setStrSubLabelNo(String strSubLabelNo) {
		this.strSubLabelNo = strSubLabelNo;
	}

	public String getStrCellNo() {
		return strCellNo;
	}
	public void setStrCellNo(String strCellNo) {
		this.strCellNo = strCellNo;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	
	
	
	
	
}
