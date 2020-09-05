/**
 * 模块名称：消费清单查询Action
 * 模块编码：B803
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.cost.service.ICost_ExpensesListService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Cost_ExpensesListAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_ExpensesListService cost_ExpensesListImpl;	
	private String buildDate;
	private String beginDate;
	private String endDate;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strWheresql;
	private String strOwnerNo;
	private String billingProject;
	private String strQuery;
	
	//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = cost_ExpensesListImpl.getOwnerNoForQuery(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI的计费项目
	public void getBillingProjectForUI(){
		try 
		{
			List<ComboxBo> list = cost_ExpensesListImpl.getBillingProjectForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取消费清单信息
	public void getExpList(){
		try {
			if(requestFlag==1){
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Cost_ExpensesListModel> list=cost_ExpensesListImpl.getExpList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, 
					buildDate,
					beginDate,
					endDate,
					pageBo,
					getRequestFlag());
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				List list=cost_ExpensesListImpl.getExpList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, 
						buildDate,
						beginDate,
						endDate,
						getRequestFlag());
				String title = "消费清单";
				String[] threads = new String[]{"sheet1","消费清单",	
						"仓别,货主编码,科目代码,计费项目,项目类型,商品群组,来源单号,生成日期,面积(平方米)," +
						"托盘数,件数,体积(立方米),重量(吨),货值(元),货位,状态"};			
				commExportAction(title, threads,list);
			}			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//获取状态
	public void  getStatusList(){
		try 
		{
			List<ComboxBo> list = cost_ExpensesListImpl.getStatusList(
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
	//取来源单号下拉
	public void getSourceNoList()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=cost_ExpensesListImpl.getSourceNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strWheresql,strQuery, 
					buildDate,
					beginDate,
					endDate);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//重算消费清单
	public void tscResetExp(){
		try {
			MsgRes msg=cost_ExpensesListImpl.tscResetExp(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strOwnerNo,billingProject,beginDate,endDate,
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//删除消费清单
	public void deleteExpensesList(){
		try {
			MsgRes msg = cost_ExpensesListImpl.deleteExpensesList(strQuery,buildDate);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public ICost_ExpensesListService getCost_ExpensesListImpl() {
		return cost_ExpensesListImpl;
	}
	public void setCost_ExpensesListImpl(
			ICost_ExpensesListService cost_ExpensesListImpl) {
		this.cost_ExpensesListImpl = cost_ExpensesListImpl;
	}
	public String getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	public String getBillingProject() {
		return billingProject;
	}
	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
}
