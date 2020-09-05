/**
 * 模块名称：手工生成费用明细Action
 * 模块编码：B903
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_ExpensesListModel;
import com.sealinkin.cost.service.ICost_ManualCostService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Cost_ManualCostAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_ManualCostService cost_ManualCostImpl;	
	private String str;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strJoin;
	private String strQuery;
	private String buildDate;
	private String beginDate;
	private String endDate;
	
	//获取UI的科目
	public void getAccountNoForUI(){
		try 
		{
			List<ComboxBo> list = cost_ManualCostImpl.getAccountNoForUI(
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
				ExtListDataBo<Cost_ExpensesListModel> list=cost_ManualCostImpl.getExpList(
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
				List list=cost_ManualCostImpl.getExpList2(
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
	//手工生成费用明细
	public void saveManualCost(){
		try {
			MsgRes msg=cost_ManualCostImpl.saveManualCost(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,
					strJoin);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public ICost_ManualCostService getCost_ManualCostImpl() {
		return cost_ManualCostImpl;
	}
	public void setCost_ManualCostImpl(ICost_ManualCostService cost_ManualCostImpl) {
		this.cost_ManualCostImpl = cost_ManualCostImpl;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
	public String getStrJoin() {
		return strJoin;
	}
	public void setStrJoin(String strJoin) {
		this.strJoin = strJoin;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
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
}
