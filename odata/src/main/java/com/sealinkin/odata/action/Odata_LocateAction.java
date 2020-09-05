package com.sealinkin.odata.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.model.Odata_LocateMModel;
import com.sealinkin.odata.model.Odata_LocateShortLogModel;
import com.sealinkin.odata.service.IOdata_LocataService;
import com.sealinkin.util.ExceptionUtil;

/**
 * 出货调度
 * @author JUN
 */
public class Odata_LocateAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IOdata_LocataService odata_LocateImpl;
	private String strOwnerNo;
	private String strExpType;
	private String strWheresql;
	private String strFlag;
	private String strExpNo;
	private String expDate;
	private String strCustNo;
	private String strIsNotEnought;
	private String strDetail;
	private String linkValue;
	private String articleNo;
	private String str;
	private String strCell;
	private String strQuery;
	private String pageSql;
	private String calFlag;
	private String expType;
	private String  strOrdertype;
	
	/**
	 * 获取货主下拉
	 */
	public void queryOwnerCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.queryOwnerCombo(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取机构
	 */
	public void queryOrgNoCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.queryOrgNoCombo(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取客户下拉
	 */
	public void getCust()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.getCust(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strOwnerNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	

	/**
	 * 获取备注下拉
	 */
	public void getExpRemark()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.getExpRemark(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strOwnerNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取出货单别下拉(公用)
	 */
	public void queryExpTypeCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.queryExpTypeCombo(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strFlag);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取配送方式
	public void queryDeliverTypeCombo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.queryDeliverTypeCombo(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取商品编码
	public void getlocateArticleNo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.getlocateArticleNo(super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getArticleNo(),this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取客户编码
	public void getlocateCustNo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = odata_LocateImpl.getlocateCustNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getPageSql(),
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取集单头档
	 */
	public void queryLocateM()
	{
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpMModel> list=odata_LocateImpl.queryLocateM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strWheresql,strFlag, poPageBo,linkValue,str
					);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取集单明细
	 */
	public void queryLocateD()
	{
		try 
		{
			ExtListDataBo<Odata_ExpDModel> list=odata_LocateImpl.queryLocateD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strFlag,strExpNo,strCustNo,strIsNotEnought);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取客户线路
	 */
	public void queryTactics()
	{
		try 
		{
			List<String> list=odata_LocateImpl.queryTactics(super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 重算月台资源v2
	 */
	public void tscBackrollres()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.tscBackrollres(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,strExpType,strFlag);//strFlag为IP
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 获取月台可用货位数v2
	 */
	public void tscBufferQty()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.tscBufferQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strFlag);//strFlag为IP
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 保存调度临时表
	 */
	public void saveOdataTmpLocateSelect()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.saveOdataTmpLocateSelect(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strDetail, strFlag,expDate,linkValue);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 保存调度临时表V2(电商版) 
	 */
	public void saveOdataTmpLocateSelectV2()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.saveOdataTmpLocateSelectV2(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strDetail, strFlag,calFlag,expDate,linkValue);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 删除调度临时表v2电商
	 */
	public void deleteOdataTmpLocateSelectV2(){

		try 
		{
			MsgRes msg=odata_LocateImpl.deleteOdataTmpLocateSelectV2(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strDetail, strFlag,calFlag);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	//改变月台试算，重整月台
	public void changeCalculation(){
		try 
		{
			MsgRes msg=odata_LocateImpl.changeCalculation(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrOwnerNo(),
					this.getExpType(),calFlag);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 删除调度临时表
	 */
	public void deleteOdataTmpLocateSelect()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.deleteOdataTmpLocateSelect(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strDetail, strFlag);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 定位
	 */
	public void tscFixed(){
		try 
		{
			MsgRes msg=odata_LocateImpl.tscFixed(strDetail);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 删除临时表
	 */
	public void deleteTmpTable()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.deleteTmpTable(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 获取临时表长度
	 */
	public void queryTmpTableLength()
	{
		try 
		{
			MsgRes msg=odata_LocateImpl.queryTmpTableLength(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 定位，过程 管事务
	 */
	public void fixed(){
		try 
		{
			MsgRes msg=odata_LocateImpl.fixed(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),strWheresql);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 计算勾选的单据详情
	 */
	public void countDetail(){
		try {
			List<Odata_ExpMModel> list = odata_LocateImpl.countDetail(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
			super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 检查是否有商品缺量
	 */
	public void checkNoEnoght(){
		try {
			MsgRes msg = odata_LocateImpl.checkNoEnoght(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strFlag
					);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取出货续调信息
	 */
	public void getOdata_Locate_M(){
		try{		
			List<Odata_LocateMModel> list=odata_LocateImpl.getOdata_Locate_M(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStrWheresql(), 
					0, 10000);
			super.writeArray(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	

	//获取路线
	public void getLink(){
		try 
		{
			List<ComboxBo> list = odata_LocateImpl.getLink(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrQuery());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取缺量的单号
	public void checkOrder(){
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_ExpMModel> list=odata_LocateImpl.checkOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), poPageBo,this.getArticleNo());
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	
	//为要定位的商品添加储位
	public void changeArticle(){
		try {
			MsgRes msg = odata_LocateImpl.changeArticle(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),this.getStrOwnerNo(),this.getStrCell());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "", ""));
		}
	}
	
	
	//获取储位下拉
	 
	public void getCdef_DefCellCombo() {
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = odata_LocateImpl.getCdef_DefCellCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), str, strWheresql,0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取定位失败原因
	public void getLocateFail(){
		try 
		{
			PageBo poPageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Odata_LocateShortLogModel> list=odata_LocateImpl.getLocateFail(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strQuery,
					poPageBo);
			super.writeObj(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取定位失败查询-波次号下拉
	public void getLocateNoForQuery(){
		try 
		{
			List<ComboxBo> list = odata_LocateImpl.getLocateNoForQuery(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取定位失败查询-商品编码下拉
	public void getArticleNoForUI()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_LocateImpl.getArticleNoForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取定位条件承运商下拉
	public void getlocateSanplNo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_LocateImpl.getlocateSanplNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取波次规则下拉v2
	public void queryOrdertypeCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=odata_LocateImpl.queryOrdertypeCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery,strExpType);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	//根据波次规则写临时表（过滤）
	public void tscOdataTmpLocateSelect(){
		try {
			MsgRes msg = odata_LocateImpl.tscOdataTmpLocateSelect(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),strOwnerNo,
					strOrdertype,strExpType);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "", ""));
		}
	}
	
	public void setOdata_LocateImpl(IOdata_LocataService odata_LocateImpl) {
		this.odata_LocateImpl = odata_LocateImpl;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getStrExpType() {
		return strExpType;
	}
	public void setStrExpType(String strExpType) {
		this.strExpType = strExpType;
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

	public String getStrExpNo() {
		return strExpNo;
	}
	public void setStrExpNo(String strExpNo) {
		this.strExpNo = strExpNo;
	}

	public String getStrCustNo() {
		return strCustNo;
	}
	public void setStrCustNo(String strCustNo) {
		this.strCustNo = strCustNo;
	}

	public String getStrIsNotEnought() {
		return strIsNotEnought;
	}
	public void setStrIsNotEnought(String strIsNotEnought) {
		this.strIsNotEnought = strIsNotEnought;
	}

	public String getStrDetail() {
		return strDetail;
	}
	public void setStrDetail(String strDetail) {
		this.strDetail = strDetail;
	}

	public String getLinkValue() {
		return linkValue;
	}

	public void setLinkValue(String linkValue) {
		this.linkValue = linkValue;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrCell() {
		return strCell;
	}

	public void setStrCell(String strCell) {
		this.strCell = strCell;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getPageSql() {
		return pageSql;
	}

	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}

	public String getCalFlag() {
		return calFlag;
	}

	public void setCalFlag(String calFlag) {
		this.calFlag = calFlag;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getStrOrdertype() {
		return strOrdertype;
	}

	public void setStrOrdertype(String strOrdertype) {
		this.strOrdertype = strOrdertype;
	}
}
