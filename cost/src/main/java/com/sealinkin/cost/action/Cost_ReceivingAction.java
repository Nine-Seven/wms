/**
 * 模块名称：收款Action
 * 模块编码：B703
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_FinancialModel;
import com.sealinkin.cost.model.Cost_ReturnAmountModel;
import com.sealinkin.cost.service.ICost_ReceivingService;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Cost_ReceivingAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_ReceivingService cost_ReceivingImpl;	
	private String warehouseNo;
	private String ownerNo;
	private String buildDate;
	private String beginDate;
	private String endDate;
	private String str;
	private Integer requestFlag = 1;//1：查询2：导出
	private String jsonStr;
	private String strWheresql;
	private String strMonth;
	private String strCheckNo;
	private String strStatus;
	private String strQuery;
	private String strCostFlag;
	
	//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = cost_ReceivingImpl.getOwnerNoForQuery(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取UI界面的对账单号
	public void getCheckNoForUI(){
		try 
		{
			List<ComboxBo> list = cost_ReceivingImpl.getCheckNoForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取已出账账单信息列表
	public void getFinList(){
		try {
			if(requestFlag==1){
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Cost_FinancialModel> list=cost_ReceivingImpl.getFinList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, 
					pageBo,
					getRequestFlag());
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				List list=cost_ReceivingImpl.getFinList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, 
						getRequestFlag());
				String title = "收款";
				String[] threads = new String[]{"sheet1","收款",	
						"仓别,货主编码,对账单号,应收/付标识,生成日期,开始日期,结束日期,应收金额（元）,回款金额（元）,应收剩余金额（元）,状态"};			
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
			List<ComboxBo> list = cost_ReceivingImpl.getStatusList(
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
	//取回款信息列表
	public void getReturnAmountList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_ReturnAmountModel> accountList = cost_ReceivingImpl.getReturnAmountList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery, pageBo
					);
			super.writeStr(covtObjectToJson(accountList));
		} catch (Exception e){
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//保存回款信息
	public void saveReturnAmount(){
		try
		{	
			cost_ReceivingImpl.saveReturnAmount(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}		
	}
	//获取回款信息rowid
	public void getRowId(){
		try 
		{
			String max=cost_ReceivingImpl.getRowId(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStr());
			super.writeStr(max);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//修改账单状态
	public void update(){
		try 
		{
			MsgRes msg=this.cost_ReceivingImpl.update(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strCheckNo,
					strCostFlag,
					strStatus);
			super.writeObj(msg);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public ICost_ReceivingService getCost_ReceivingImpl() {
		return cost_ReceivingImpl;
	}
	public void setCost_ReceivingImpl(ICost_ReceivingService cost_ReceivingImpl) {
		this.cost_ReceivingImpl = cost_ReceivingImpl;
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
	public String getStrMonth() {
		return strMonth;
	}
	public void setStrMonth(String strMonth) {
		this.strMonth = strMonth;
	}
	public String getStrCheckNo() {
		return strCheckNo;
	}
	public void setStrCheckNo(String strCheckNo) {
		this.strCheckNo = strCheckNo;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrCostFlag() {
		return strCostFlag;
	}
	public void setStrCostFlag(String strCostFlag) {
		this.strCostFlag = strCostFlag;
	}
}
