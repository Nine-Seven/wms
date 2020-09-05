/**
 * 商品资料action
 * @author JUN
 */
package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleBarcodeModel;
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.model.Bdef_WarehousePackingModel;
import com.sealinkin.bdef.service.IBdef_DefarticleService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bdef_DefarticleAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBdef_DefarticleService bdef_DefarticleImpl;
	private String strOwnerNo;
	private String strArticle;
	private String strArticleNo;
	private String strPackingQty;
	private String strSaveType;
	private String strPacking;
	private String strBarcode;
	private String strWheresql;
	private String strQuery;// 查找时用得字段
	private Integer intRequestFlag = 1;//1：查询2：导出
	private String str;
	private String ownerNo;
	/**
	 * 获取商品主档列
	 */
	public void queryBdefDefarticleList()
	{
		try 
		{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefarticleModel> pageListBo = bdef_DefarticleImpl.query_BdefDefarticleList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), strQuery, poPagebo, 1);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存商品主档
	 */
	public void saveBdefDefarticle()
	{
		try 
		{		
			MsgRes poMsg=bdef_DefarticleImpl.saveArticle(
					strArticle, strSaveType,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(poMsg);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 删除商品
	 */
	
	public void deleteArticleNo()
	{
		try 
		{
			MsgRes msg=bdef_DefarticleImpl.deleteArticleNo(strArticleNo,
	    	strOwnerNo,super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 验证商品编码唯一性
	 */
	public void checkArticleNo()
	{
		try 
		{
			MsgRes poMsg=bdef_DefarticleImpl.check_ArticleNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strArticleNo);
			super.writeObj(poMsg);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存商品包装
	 */
	public void saveBdefArticlePacking()
	{
		try 
		{
			MsgRes poMsg = bdef_DefarticleImpl.saveOrUpdateArticlePacking(strPacking,super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(poMsg);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	/**
	 * 保存商品仓别标准堆叠
	 */
	public void saveBdefWarehousePacking()
	{
		try 
		{
			MsgRes poMsg = bdef_DefarticleImpl.saveOrUpdateWarehousePacking(strPacking,super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(poMsg);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取商品包装列
	 */
	public void queryBdefArticlePackingList()
	{
		try 
		{
			PageBo poPagebo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_ArticlePackingModel> pageListBo = 
					bdef_DefarticleImpl.query_BdefArticlePackingList(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							this.getStrArticleNo(), poPagebo);
			super.writeObj(pageListBo);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取仓别包装信息
	 */
	public void queryBdefWarehousePacking()
	{
		
		try 
		{
			List<Bdef_WarehousePackingModel> list=bdef_DefarticleImpl.queryBdefWarehousePacking(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strArticleNo,strPackingQty);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存商品条码
	 */
	public void saveOrUpdateArticleBarcode(){
		try {
			MsgRes poMsg = bdef_DefarticleImpl.saveOrUpdateArticleBarcode(strBarcode);
			super.writeObj(poMsg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取商品条码列
	 */
	public void queryBdefArticleBarcodeList()
	{
		try 
		{
			PageBo poPagebo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_ArticleBarcodeModel> pageListBo = 
					bdef_DefarticleImpl.query_BdefArticleBarcodeList(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							strArticleNo,strOwnerNo, poPagebo);
			super.writeObj(pageListBo);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取商品下拉
	 */
	public void queryBdefArticleCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = bdef_DefarticleImpl.query_BdefArticleCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					getStrQuery(), 
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取商品下拉名称
	 */
	public void getArticleName()
	{
		try {
			List<String> list=new ArrayList<String>();
			list = bdef_DefarticleImpl.get_ArticleName(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取包装下拉
	 */
	public void queryPackingCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefarticleImpl.qyery_ArticlePackingCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 根据商品编码和包装获取包装单位和规格
	 */
	public void queryPackingUnitAndSpec()
	{
		try 
		{
			List<Bdef_ArticlePackingModel> list=bdef_DefarticleImpl.queryPackingUnitAndSpec(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strArticleNo, strPackingQty,"");
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 根据商品编码获取包装、包装单位和规格
	 */
	public void queryPackingQtyAndUnitAndSpec()
	{
		try 
		{
			List<Bdef_ArticlePackingModel> list=bdef_DefarticleImpl.queryPackingQtyAndUnitAndSpec(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strArticleNo,"");
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	
	public void checkSaveBarcode(){
		try {
			MsgRes msg=bdef_DefarticleImpl.checkSaveBarcode(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strArticleNo, strPackingQty, strBarcode);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取商品最大包装数
	public void getMaxArticlePacking(){
		try 
		{
			String max=bdef_DefarticleImpl.getMaxArticlePacking(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql);
			super.writeStr(max);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//货主编码校验
	public void checkOwnerNo(){
		try {
			MsgRes msg=bdef_DefarticleImpl.checkOwnerNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strArticleNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void getArticleInfo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_DefarticleImpl.getArticleInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取状态
	public void  getStatusList(){
		try 
		{
			List<ComboxBo> list = bdef_DefarticleImpl.getStatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void setBdef_DefarticleImpl(IBdef_DefarticleService bdef_DefarticleImpl) {
		this.bdef_DefarticleImpl = bdef_DefarticleImpl;
	}

	public String getStrArticle() {
		return strArticle;
	}
	public void setStrArticle(String strArticle) {
		this.strArticle = strArticle;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}
	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}

	public String getStrSaveType() {
		return strSaveType;
	}
	public void setStrSaveType(String strSaveType) {
		this.strSaveType = strSaveType;
	}

	public String getStrPacking() {
		return strPacking;
	}
	public void setStrPacking(String strPacking) {
		this.strPacking = strPacking;
	}

	public String getStrBarcode() {
		return strBarcode;
	}
	public void setStrBarcode(String strBarcode) {
		this.strBarcode = strBarcode;
	}

	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public Integer getIntRequestFlag() {
		return intRequestFlag;
	}
	public void setIntRequestFlag(Integer intRequestFlag) {
		this.intRequestFlag = intRequestFlag;
	}

	public String getStrPackingQty() {
		return strPackingQty;
	}
	public void setStrPackingQty(String strPackingQty) {
		this.strPackingQty = strPackingQty;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getOwnerNo() {
		return ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	
	
	
}
