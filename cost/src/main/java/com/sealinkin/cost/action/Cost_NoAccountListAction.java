/**
 * 模块名称：未出账费用明细查询Action
 * 模块编码：B403
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_CostListModel;
import com.sealinkin.cost.service.ICost_NoAccountListService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Cost_NoAccountListAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_NoAccountListService cost_NoAccountListImpl;	
	private String warehouseNo;
	private String ownerNo;
	private String buildDate;
	private String beginDate;
	private String endDate;
	private String str;
	private Integer requestFlag = 1;//1：查询2：导出
	private String jsonStr;
	private String strWheresql;
	private String strMenuType;
	private String strOwnerNo;
	private String billingProject;
	private String strQuery;
	
	//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = cost_NoAccountListImpl.getOwnerNoForQuery(
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
			List<ComboxBo> list = cost_NoAccountListImpl.getBillingProjectForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取未出账清单信息
	public void getNoAccountList(){
		try {
			if(requestFlag==1){
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Cost_CostListModel> list=cost_NoAccountListImpl.getNoAccountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, 
				    strMenuType,
					pageBo,
					getRequestFlag());
			
			
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				List list=cost_NoAccountListImpl.getNoAccountList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, 
						getRequestFlag());
				String title = "未出账费用明细";
				String[] threads = new String[]{"sheet1","未出账费用明细",	
						"仓别,货主编码,科目代码,计费项目,费用标识,生成日期,开始日期,结束日期,面积(平方米)," +
						"托盘数,件数,体积(立方米),重量(吨),货值（元）,货位,默认单价,费用,其他费用1,其他费用2," +
						"其他费用3,其他费用4,其他费用5,优惠费用,合计"};			
				commExportAction(title, threads,list);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}	
	//重算费用明细
	public void tscResetCost(){
		try {
			MsgRes msg=cost_NoAccountListImpl.tscResetCost(
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
	//费用明细回退
	public void tscUndoCost(){
		try {
			MsgRes msg=cost_NoAccountListImpl.tscUndoCost(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getOwnerNo(),
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public ICost_NoAccountListService getCost_NoAccountListImpl() {
		return cost_NoAccountListImpl;
	}
	public void setCost_NoAccountListImpl(
			ICost_NoAccountListService cost_NoAccountListImpl) {
		this.cost_NoAccountListImpl = cost_NoAccountListImpl;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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
	public String getJsonStr() {
		return jsonStr;
	}
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrMenuType() {
		return strMenuType;
	}
	public void setStrMenuType(String strMenuType) {
		this.strMenuType = strMenuType;
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
