/**
 * 模块名称：手工录入消费清单
 * 模块编码：B905
 * 创建：hcx 20160707
 */
package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_HandleListModel;
import com.sealinkin.cost.service.ICost_HandleListService;
import com.sealinkin.util.ExceptionUtil;

public class Cost_HandleListAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ICost_HandleListService cost_HandleListImpl;
	private String str;
	private String billingProject;
	private String amountDate;
	private String billingCycle;
    private String strOwnerNo;
    private String strSerialNo;
    private Integer requestFlag = 1;//1：查询2：导出
    private String strQuery;
    private String strBillingProject;
    private String strAmountDate;
    private String strWheresql;
    private String strSelectFlag;
    
	public void getSerialNo(){
		try {
			MsgRes msg=cost_HandleListImpl.getSerialNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存手工录入消费清单
	public void saveCostHandleList(){
		try
		{	
			cost_HandleListImpl.saveCostHandleList(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}		
	}
	//获取手工录入消费清单信息
	public void getCostHandleList(){
		try 
		{
			if(requestFlag==1){
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Cost_HandleListModel> costHandleList = cost_HandleListImpl.getCostHandleList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						strQuery,
						pageBo,
						super.getMdBdef_DefWorker().getWorkerOwner());
				super.writeObj(costHandleList);		
			}else if(requestFlag==2){
				List list=cost_HandleListImpl.getCostHandleList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						strQuery, 
						super.getMdBdef_DefWorker().getWorkerOwner());
				String title = "手工录入消费清单";
				String[] threads = new String[]{"sheet1","手工录入消费清单",	
						"仓别,货主编码,流水号,科目代码,计费项目,结算日期,默认单价,消费值,来源单号,费用标识,状态"};			
				commExportAction(title, threads,list);
			}
			
		} catch (Exception e){
				e.printStackTrace();
		}
	}
	//获取新增窗口货主下拉
	public void getOwnerNoForWindow(){
		try 
		{
			List<ComboxBo> list = cost_HandleListImpl.getOwnerNoForWindow(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	//获取UI的计费项目
	public void getBillingProjectForWindow(){
		try 
		{
			List<ComboxBo> list = cost_HandleListImpl.getBillingProjectForWindow(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据计费项目获取默认单价和费用标识
	 */
	public void getUnitPriceAndcostFlag()
	{
		try 
		{
			List<Cost_HandleListModel> list=cost_HandleListImpl.getUnitPriceAndcostFlag(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除消费清单信息
	public void deleteHandleList(){
		try {
			MsgRes msg = cost_HandleListImpl.deleteHandleList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,
					strSerialNo);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//判断项目编码是否唯一
	public void billingProjectCheck(){
		try 
		{
			List<String> list = cost_HandleListImpl.billingProjectCheck(
					this.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,
					strBillingProject,
					strAmountDate);	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取UI界面的查询条件下拉
	public void getSelectForUI(){
		try 
		{
			List<ComboxBo> list = cost_HandleListImpl.getSelectForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strSelectFlag,
					strQuery,
					strWheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ICost_HandleListService getCost_HandleListImpl() {
		return cost_HandleListImpl;
	}
	public void setCost_HandleListImpl(ICost_HandleListService cost_HandleListImpl) {
		this.cost_HandleListImpl = cost_HandleListImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public String getAmountDate() {
		return amountDate;
	}
	public void setAmountDate(String amountDate) {
		this.amountDate = amountDate;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getStrSerialNo() {
		return strSerialNo;
	}
	public void setStrSerialNo(String strSerialNo) {
		this.strSerialNo = strSerialNo;
	}
	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrBillingProject() {
		return strBillingProject;
	}
	public void setStrBillingProject(String strBillingProject) {
		this.strBillingProject = strBillingProject;
	}
	public String getStrAmountDate() {
		return strAmountDate;
	}
	public void setStrAmountDate(String strAmountDate) {
		this.strAmountDate = strAmountDate;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrSelectFlag() {
		return strSelectFlag;
	}
	public void setStrSelectFlag(String strSelectFlag) {
		this.strSelectFlag = strSelectFlag;
	}
}
