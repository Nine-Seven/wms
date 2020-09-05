/**
 * 模块名称：手工出账Action
 * 模块编码：B603
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
import com.sealinkin.cost.service.ICost_ManualAccountService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Cost_ManualAccountAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_ManualAccountService cost_ManualAccountImpl;	
	private String str;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strWheresql;
	private String strJoin;
	private String strQuery;
	private String strMenuType;

	//获取UI的科目
	public void getAccountNoForUI(){
		try 
		{
			List<ComboxBo> list = cost_ManualAccountImpl.getAccountNoForUI(
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
				ExtListDataBo<Cost_CostListModel> list=cost_ManualAccountImpl.getNoAccountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, 
					strMenuType,
					pageBo,
					getRequestFlag());
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				List list=cost_ManualAccountImpl.getNoAccountList2(
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
	//手工出账
	public void saveManualAccount(){
		try {
			MsgRes msg=cost_ManualAccountImpl.saveManualAccount(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str,
					strWheresql,
					strJoin);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	public ICost_ManualAccountService getCost_ManualAccountImpl() {
		return cost_ManualAccountImpl;
	}
	public void setCost_ManualAccountImpl(
			ICost_ManualAccountService cost_ManualAccountImpl) {
		this.cost_ManualAccountImpl = cost_ManualAccountImpl;
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
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
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
	public String getStrMenuType() {
		return strMenuType;
	}
	public void setStrMenuType(String strMenuType) {
		this.strMenuType = strMenuType;
	}
}
