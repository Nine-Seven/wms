/**
 * 模块名称：租仓费用重算Action
 * 模块编码：B401
 * 创建：lich 
 */
package com.sealinkin.bdef.action;

import java.util.List;

import com.sealinkin.bdef.service.IBill_ResetBillService;
import com.sealinkin.bset.model.Bill_Cost_ListModel;
import com.sealinkin.bset.model.Bill_Expenses_ListModel;
import com.sealinkin.bset.model.Bill_FinancialModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class Bill_ResetBillAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBill_ResetBillService bill_ResetBillImpl;	
	private String warehouseNo;
	private String ownerNo;
	private String beginDate;
	private String endDate;
	private String str;
	private Integer requestFlag = 1;//1：查询2：导出
	private String jsonStr;
	
	public IBill_ResetBillService getBill_ResetBillImpl() {
		return bill_ResetBillImpl;
	}
	public void setBill_ResetBillImpl(IBill_ResetBillService bill_ResetBillImpl) {
		this.bill_ResetBillImpl = bill_ResetBillImpl;
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
	public void tscResetExp(){
		try {
			MsgRes msg=bill_ResetBillImpl.tscResetExp(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getOwnerNo(), 
					getBeginDate(), 
					getEndDate());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void tscResetCost(){
		try {
			MsgRes msg=bill_ResetBillImpl.tscResetCost(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getOwnerNo(), 
					getBeginDate(), 
					getEndDate());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void tscResetFin(){
		try {
			MsgRes msg=bill_ResetBillImpl.tscResetFin(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getOwnerNo(), 
					getBeginDate());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public void getExpList(){
		try {
			if(requestFlag==1){
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Bill_Expenses_ListModel> list=bill_ResetBillImpl.getExpList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(), 
					pageBo,
					getRequestFlag());
			
			
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				List list=bill_ResetBillImpl.getExpList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						getStr(), 
						getRequestFlag());
				String title = "租仓消费清单";
				String[] threads = new String[]{"sheet1","租仓消费清单",	
						"仓别,货主编码,计费项目,开始日期,结束日期,消费值,面积(平方米)," +
						"托盘数,件数,体积(立方米),重量(吨)"};			
				commExportAction(title, threads,list);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void getCostList(){
		try {
		if(requestFlag==1){
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_Cost_ListModel> list=bill_ResetBillImpl.getCostList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(), 
					pageBo,
					getRequestFlag());
			
			
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){	
				List list=bill_ResetBillImpl.getCostList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						getStr(), 
						getRequestFlag());
				String title = "租仓费用明细";
				String[] threads = new String[]{"sheet1","租仓费用明细",
						"仓别,货主编码,计费项目,计费日期,计费单位," +
						"计费单价,计费值,金额(元),附加条件,值1,值2,状态"};
				  commExportAction(title, threads, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public void getFinList(){
		try {
		if(requestFlag==1){
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bill_FinancialModel> list=bill_ResetBillImpl.getFinList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					getStr(), 
					pageBo,
					getRequestFlag());
			
				super.writeStr(covtObjectToJson(list));
			}else if(requestFlag==2){
				List list=bill_ResetBillImpl.getFinList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						getStr(), 
						getRequestFlag());
				String title = "租仓帐单";
				String[] threads = new String[]{"sheet1","租仓帐单",
						"仓别,货主编码,月份,科目代码,金额(元),优惠金额(元)"};
				  commExportAction(title, threads, list);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//费用上缴
	public void paidCost(){
		try 
		{
			MsgRes msg=this.bill_ResetBillImpl.paidCost(jsonStr);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//取消费用
	public void undoCost(){
		try 
		{
			MsgRes msg=this.bill_ResetBillImpl.undoCost(jsonStr);
			super.writeObj(msg);
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
			List<ComboxBo> list = bill_ResetBillImpl.getStatusList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					str);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
}
