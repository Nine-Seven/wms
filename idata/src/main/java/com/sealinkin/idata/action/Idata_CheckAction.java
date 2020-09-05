package com.sealinkin.idata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.idata.model.Idata_CheckDModel;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.idata.model.Idata_Check_ResulModel;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.model.Idata_ImportSdModel;
import com.sealinkin.idata.service.Iidata_CheckService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 验收
 * @author JUN
 */
public class Idata_CheckAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private Iidata_CheckService idata_CheckImpl;
	private String strWheresql;
	private String strSImportNo;
	private String strImportNo;
	private String strLotNo;
	private String strArticleNo;
	private String strJsonMaster;
	private String strJsonDetail1;
	private String strJsonDetail2;
	private String strLabelNo;
	private String strFlag;
	private String strOwnerNo;
	private String strQuery;
	private String str;
	private String orderNo;
	private Integer requestFlag = 1;//1：查询2：导出 huangb 20160718
	private String strPoNo;//采购单号 huangb 20160718

	/**
	 * 根据生成日期获取批次
	 */
	
	public void getlotNo(){
		try 
		{
			List<ComboxBo> list = idata_CheckImpl.getlotNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrQuery(),this.getStrWheresql());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 填充进货汇总单下拉
	 */
	public void queryIdataImportMMCombo()
	{
		try 
		{
			List<Idata_ImportSdModel> list=idata_CheckImpl.queryIdataImportMMCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,
					strFlag,
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取供应商(混合板验收)
	public void  getSupplierNo(){
		try 
		{
			List<ComboxBo> list = idata_CheckImpl.getSupplierNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取单据类型(混合板验收)
	public void  getImportType(){
		try 
		{
			List<ComboxBo> list = idata_CheckImpl.getImportType(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	
	//获取单据类型(混合板验收)
	public void  getQualityCombo(){
		try 
		{
			List<ComboxBo> list = idata_CheckImpl.getQualityCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 校验预约流水号(混合板验收)
	 */
	public void checkOrderSerial()
	{
		try{	
			MsgRes msg=idata_CheckImpl.checkOrderSerial(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 填充采购单号下拉(混合板验收)
	 */
	public void getPoNoList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_CheckImpl.getPoNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql,str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获得单据列表(混合板验收)
	 * @throws Exception
	 */
	public void getPoNoAndSImportNoList(){ 
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportMModel> list=idata_CheckImpl.getPoNoAndSImportNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					 strWheresql,str,
					 orderNo,
					 pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得汇总单明细(混合板验收)
	 * @throws Exception
	 */
	public void getImportSDList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportDModel> list=idata_CheckImpl.getImportSDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 填充助记码/条码(混合板验收)
	 */
	public void getIdentifierOrBarcode1List()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_CheckImpl.getIdentifierOrBarcode1List(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql,str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取验收明细
	 */
	public void queryIdataImportSd()
	{
		try 
		{
			ExtListDataBo<Idata_ImportSdModel> list=idata_CheckImpl.queryIdataImportSd(
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
	
	/**
	 * 获取进货单号、供应商等信息
	 */
	public void getImportNo()
	{
		try {
			List<Idata_ImportMModel> list=idata_CheckImpl.getImportNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),strSImportNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取进货汇总单号
	 */
	public void getSImportNo()
	{
		try {
			List<Idata_ImportMModel> list=idata_CheckImpl.getSImportNo(
					super.getMdBdef_DefWorker().getWorkerOwner(),strImportNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 根据商品编号和批号获取相应的生产日期
	 */
	public void queryLotProduceDate(){
		try {
			//MsgRes msgRes=idata_CheckImpl.queryLotProduceDate(strLotNo, strArticleNo);
			//super.writeStr(covtListToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存(流水板)
	 */
	public void saveCheck()
	{
		try {
			MsgRes msg=idata_CheckImpl.tscSaveCheck(strJsonMaster, strJsonDetail1,this.getStrFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//前台单品验收（混合板）校验是否能验收
	public void tscCheckExists()
	{
		try {
			MsgRes msg=idata_CheckImpl.tscCheckExists(strSImportNo,strJsonDetail1);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 保存(混合板)
	 */
	public void saveCheck3()
	{
		try {
			MsgRes msg=idata_CheckImpl.tscSaveCheck3(strJsonMaster, strJsonDetail1,this.getStrFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 保存(人工板)
	 */
	public void saveCheck2()
	{
		try {
			MsgRes msg=idata_CheckImpl.tscSaveCheck2(strJsonMaster, strJsonDetail1);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 校验板号
	 */
	public void IdataCheckLabelNo()
	{
		try {
			MsgRes msg=idata_CheckImpl.IdataCheckLabelNo(
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strLabelNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/////////////////////////////////////验收确认////////////////////////////////////////////////////
	//验收确认货主下拉
	public void ownerForConfirmCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_CheckImpl.ownerForConfirmCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//判断验收确认单是否存在 
	public void checkNoCheck(){
		try 
		{
			List<String> list = idata_CheckImpl.checkNoCheck(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery
					);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取验收确认单头档
	 */
	public void queryCheckM()
	{
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_CheckMModel> list=idata_CheckImpl.queryCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					strQuery,
					poPageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取验收确认单明细
	 */
	public void queryCheckD()
	{
		try 
		{
			ExtListDataBo<Idata_CheckDModel> list=idata_CheckImpl.queryCheckD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取验收确认板明细
	 */
	public void queryCheckPal()
	{
		try 
		{
			ExtListDataBo<Idata_CheckPalModel> list=idata_CheckImpl.queryCheckPal(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 验收确认
	 */
	public void checkConfirm()
	{
		try 
		{
			MsgRes msg=idata_CheckImpl.tscConfirm(strJsonMaster,this.getStrFlag());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 填充委托业主下拉(验收确认)
	 */
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_CheckImpl.queryOwnerCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 *  校验临时表中是否还有数据
	 */
	public void checkPalTmp(){
		try {
			MsgRes msg = idata_CheckImpl.checkPalTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strSImportNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取验收确认未封板明细
	 */
	public void queryUnCheckPal()
	{
		try 
		{
			ExtListDataBo<Idata_CheckPalModel> list=idata_CheckImpl.queryUnCheckPal(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//取消验收未封板数据
	public void delcheckPalTmp()
	{
		try {
			MsgRes msg=idata_CheckImpl.delcheckPalTmp(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),"",strWheresql,strSImportNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//打印验收清单
	public void printCheckNo(){
		try{
			MsgRes msg=idata_CheckImpl.tscPrintCheckNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					strWheresql);
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/** 获取验收结果 用来导出
	 * huangb 20160716
	 */
	public void tscGetCheckResult(){
		try{
			 List<Idata_Check_ResulModel> list= idata_CheckImpl.tscGetCheckResult(
			         super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
			         strOwnerNo,
					 super.getMdBdef_DefWorker().getWarehouseNo(),
					 strPoNo);
			String title = "[" + strPoNo + "]验收结果";
			String[] threads = new String[]{"sheet1","[" + strPoNo + "]验收结果",
					"仓别,货主,采购单号,备案编码,商品条码,货主商品编码,商品名称," +
					"包装数量,包装单位,包装规格,采购计划量,验收量"};			
			commExportAction(title, threads,list);
		}catch(Exception e){
            e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public Integer getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	
	public void setIdata_CheckImpl(Iidata_CheckService idata_CheckImpl) {
		this.idata_CheckImpl = idata_CheckImpl;
	}

	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrSImportNo() {
		return strSImportNo;
	}
	public void setStrSImportNo(String strSImportNo) {
		this.strSImportNo = strSImportNo;
	}

	public String getStrImportNo() {
		return strImportNo;
	}
	public void setStrImportNo(String strImportNo) {
		this.strImportNo = strImportNo;
	}

	public String getStrLotNo() {
		return strLotNo;
	}
	public void setStrLotNo(String strLotNo) {
		this.strLotNo = strLotNo;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}
	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
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

	public String getStrJsonDetail2() {
		return strJsonDetail2;
	}
	public void setStrJsonDetail2(String strJsonDetail2) {
		this.strJsonDetail2 = strJsonDetail2;
	}

	public String getStrLabelNo() {
		return strLabelNo;
	}
	public void setStrLabelNo(String strLabelNo) {
		this.strLabelNo = strLabelNo;
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

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getStrPoNo() {
		return strPoNo;
	}


	public void setStrPoNo(String strPoNo) {
		this.strPoNo = strPoNo;
	}
	
}
