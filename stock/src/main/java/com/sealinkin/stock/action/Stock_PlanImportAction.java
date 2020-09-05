package com.sealinkin.stock.action;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ContentModel;
import com.sealinkin.stock.model.Stock_PlanDModel;
import com.sealinkin.stock.model.Stock_PlanMModel;
import com.sealinkin.stock.service.IStock_PlanImportService;
import com.sealinkin.util.ExceptionUtil;

public class Stock_PlanImportAction extends CommAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IStock_PlanImportService stock_PlanImportServiceImpl;
	private String str;
	private String strQuery;
	private String poNo;
	private String cellNo;
	private String articleNo;
	private String strJsonMaster;
	private String strJsonDetail;
	private String strJson;
	private String produceDate;
	private String lotNo;
	//获取调账单头档列表
	public void getStockPlanMList()
	{
		try 
			{
				PageBo pageBo=new PageBo(getStart(),getLimit());
				ExtListDataBo<Stock_PlanMModel> list  = stock_PlanImportServiceImpl.getStockPlanMList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						str,strQuery,pageBo);
				super.writeStr(covtObjectToJson(list));
			} catch (Exception e) 
			{
				e.printStackTrace();
				super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
			}
	}
	// 获得商品缺量信息
	public void getStockPlanArticleList() {
		try {
			    ExtListDataBo<Stock_ContentModel> list = stock_PlanImportServiceImpl.getStockPlanArticleList(
			    super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
				super.getMdBdef_DefWorker().getWarehouseNo(),
				str);
				super.writeStr(covtObjectToJson(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	//8-4添加      修改
	public void changeStock(){
		try {
			MsgRes msg=stock_PlanImportServiceImpl.changeStock(strJsonMaster, strJsonDetail);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,"保存失败！",""));
		}
	}	
	
   //获取调帐单明细
   public void getStockPlanDList(){
		try{
			   PageBo pageBo=new PageBo(getStart(),getLimit());
			   List<Stock_PlanDModel> list = stock_PlanImportServiceImpl.getStockPlanDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str ,pageBo);
			   super.writeArray(list);
		}catch (Exception e) {
	            e.printStackTrace();
		}
	}
   //检查原调帐单号是否存在
   public void checkPoNo(){

		try {
			String po=stock_PlanImportServiceImpl.checkPoNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),poNo);
			super.writeStr(po);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
   public void getArticle(){
		try {
			List<Stock_PlanDModel> list=stock_PlanImportServiceImpl.getArticle(articleNo,
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   //批号下拉 
   public void queryLot()
	{
		try 
		{
			List<ComboxBo> list=stock_PlanImportServiceImpl.queryLot(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					articleNo,produceDate,lotNo);
			super.writeArrayFtDate(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
   /**
    * 储位下拉
    */
	public void getCdef_DefCellCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=stock_PlanImportServiceImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strJson,str, strQuery, 0, 100);
			super.writeArray(list);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	 //检查所输储位商品是否有库存
	   public void checkCellNo(){

			try {
				String po=stock_PlanImportServiceImpl.checkCellNo(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),cellNo,articleNo);
				super.writeStr(po);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
   /**
	 * 保存
	 * @throws Exception
	 */
	public void save(){
		try 
		{
			MsgRes msg=this.stock_PlanImportServiceImpl.save(this.strJsonMaster,this.strJsonDetail);
			super.writeObj(msg);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//定位
	public void send(){try {
		MsgRes msg = stock_PlanImportServiceImpl.send(
		   super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
		   super.getMdBdef_DefWorker().getWorkerNo(),
		   super.getMdBdef_DefWorker().getWarehouseNo(), 
		   super.getMdBdef_DefWorker().getWorkSpaceNo(),
		   str,strQuery);
		super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));// 定位失败！
			e.printStackTrace();
	}}
	//取消订单
	public void closeOrder(){
		try {
			MsgRes msg = stock_PlanImportServiceImpl.closeOrder(strQuery);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IStock_PlanImportService getStock_PlanImportServiceImpl() {
		return stock_PlanImportServiceImpl;
	}
	public void setStock_PlanImportServiceImpl(
			IStock_PlanImportService stock_PlanImportServiceImpl) {
		this.stock_PlanImportServiceImpl = stock_PlanImportServiceImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getStrJsonMaster() {
		return strJsonMaster;
	}
	public void setStrJsonMaster(String strJsonMaster) {
		this.strJsonMaster = strJsonMaster;
	}
	public String getStrJsonDetail() {
		return strJsonDetail;
	}
	public void setStrJsonDetail(String strJsonDetail) {
		this.strJsonDetail = strJsonDetail;
	}
	public String getStrJson() {
		return strJson;
	}
	public void setStrJson(String strJson) {
		this.strJson = strJson;
	}
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
}
