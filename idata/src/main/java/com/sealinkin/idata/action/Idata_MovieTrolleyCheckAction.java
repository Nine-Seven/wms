package com.sealinkin.idata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.service.Iidata_MovieTrolleyCheckService;
import com.sealinkin.util.ExceptionUtil;

public class Idata_MovieTrolleyCheckAction extends CommAction {
	
	private static final long serialVersionUID = 1L;
	private Iidata_MovieTrolleyCheckService idata_MovieTrolleyCheckImpl;
	private String strWheresql;
	private String str;
	private String strJsonDetail1;
	private String strJsonMaster;
	private String importNo;
	private String articleNo;
	
	
	/**
	 * 填充货主下拉
	 */
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_MovieTrolleyCheckImpl.queryOwnerCombo(
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
	 //获取供应商
	public void  getSupplierNo(){
		try 
		{
			List<ComboxBo> list = idata_MovieTrolleyCheckImpl.getSupplierNo(
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
	/**
	 * 填充采购单号下拉
	 */
	public void getArticleIdentifierList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_MovieTrolleyCheckImpl.getArticleIdentifierList(
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
	 * 填充采购单号下拉
	 */
	public void getPoNoList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_MovieTrolleyCheckImpl.getPoNoList(
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
	 * 填充助记码/条码
	 */
	public void getIdentifierOrBarcode1List()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=idata_MovieTrolleyCheckImpl.getIdentifierOrBarcode1List(
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
	 * 获得单据列表
	 * @throws Exception
	 */
	public void getPoNoAndSImportNoList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportMModel> list=idata_MovieTrolleyCheckImpl.getPoNoAndSImportNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					 strWheresql,str, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得采购单明细
	 * @throws Exception
	 */
	public void getImportDList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_ImportDModel> list=idata_MovieTrolleyCheckImpl.getImportDList(
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
	 * 获得验收板明细
	 * @throws Exception
	 */
	public void getCheckPalList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Idata_CheckPalModel> list=idata_MovieTrolleyCheckImpl.getCheckPalList(
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
	 * 验收保存
	 * @throws Exception
	 */
	public void save(){
		try 
		{
			MsgRes msg=this.idata_MovieTrolleyCheckImpl.save(this.strJsonMaster,this.strJsonDetail1);
			super.writeObj(msg);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 客户与电子标签储位校验
	 * @throws Exception
	 */
	public void checkDpsCellNo(){
		try {
			MsgRes msg =idata_MovieTrolleyCheckImpl.checkDpsCellNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					importNo,articleNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public Iidata_MovieTrolleyCheckService getIdata_MovieTrolleyCheckImpl() {
		return idata_MovieTrolleyCheckImpl;
	}
	public void setIdata_MovieTrolleyCheckImpl(
			Iidata_MovieTrolleyCheckService idata_MovieTrolleyCheckImpl) {
		this.idata_MovieTrolleyCheckImpl = idata_MovieTrolleyCheckImpl;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrJsonDetail1() {
		return strJsonDetail1;
	}
	public void setStrJsonDetail1(String strJsonDetail1) {
		this.strJsonDetail1 = strJsonDetail1;
	}
	public String getStrJsonMaster() {
		return strJsonMaster;
	}
	public void setStrJsonMaster(String strJsonMaster) {
		this.strJsonMaster = strJsonMaster;
	}
	public String getImportNo() {
		return importNo;
	}
	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	
}
