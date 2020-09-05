package com.sealinkin.stock.action;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ADjDModel;
import com.sealinkin.stock.service.IStock_AdjustAccountsService;
import com.sealinkin.util.ExceptionUtil;



public class Stock_AdjustAccountsAction extends CommAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IStock_AdjustAccountsService stock_AdjustAccountsServiceImpl;
	private String strWheresql;
	private String str;
	private String strOwnerNo;
	private String strQuery;
	private String articleNo;
	private String produceDate;
	private String jsonMaster;
	private String jsonDetail;
	/**
	 *储位下拉
	 */
	public void getCdef_DefCellCombo(){
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = stock_AdjustAccountsServiceImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str, strWheresql,
					0, 100);
			super.writeArray(list);
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	/**
	 *商品下拉选择
	 */
	public void getArticleCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = stock_AdjustAccountsServiceImpl.getArticleCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,strQuery,strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 *生产日期和批次下拉选择
	 */
	public void getProductDateAndLotNo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = stock_AdjustAccountsServiceImpl.getProductDateAndLotNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 *库存调账明细列表
	 */
	public void getStockADjDList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Stock_ADjDModel> list = stock_AdjustAccountsServiceImpl.getStockADjDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo, strQuery,pageBo);
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) {
             e.printStackTrace();
		}
	}
	/**
	 *新增品项》商品下拉选择
	 */
	public void queryArticleInfo()
	{
		try 
		{
			List<Stock_ADjDModel> list=stock_AdjustAccountsServiceImpl.queryArticleInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql,strOwnerNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));	
		}
	}
	
	/**
	 * 新增品项》根据商品编号、生产日期查批号下拉
	 */
	public void queryLot()
		{
			try 
			{
				List<Stock_ADjDModel> list=stock_AdjustAccountsServiceImpl.queryLot(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						articleNo,produceDate);
				super.writeArrayFtDate(list);
			} catch (Exception e) 
			{
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
		}
	//获取商品内码
	public void getOwnerArticleNO()
	{
		try 
		{
			List<Bdef_DefarticleModel> list=stock_AdjustAccountsServiceImpl.getOwnerArticleNO(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					articleNo,strOwnerNo);
			super.writeArrayFtDate(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void save(){
		try {
			MsgRes msg=stock_AdjustAccountsServiceImpl.save(jsonMaster,jsonDetail) ;
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IStock_AdjustAccountsService getStock_AdjustAccountsServiceImpl() {
		return stock_AdjustAccountsServiceImpl;
	}
	public void setStock_AdjustAccountsServiceImpl(
			IStock_AdjustAccountsService stock_AdjustAccountsServiceImpl) {
		this.stock_AdjustAccountsServiceImpl = stock_AdjustAccountsServiceImpl;
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
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}
}
