/**
 * 模块名称：杂项费用维护Action
 * 模块编码：B203
 * 创建：hcx 
 */
package com.sealinkin.cost.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cost.model.Cost_OtherListModel;
import com.sealinkin.cost.model.Cost_OtherSetModel;
import com.sealinkin.cost.service.ICost_OtherService;
import com.sealinkin.util.ExceptionUtil;

public class Cost_OtherAction extends CommAction {

	private static final long serialVersionUID = 1L;
	private ICost_OtherService cost_OtherImpl;
	private String str;
	private String strQuery;
	private String jsonMaster;
	private String jsonDetail;
	private String strCostNo;
	private String strCostDate;
	private Integer requestFlag = 1;//1：查询2：导出
		
	//获取杂项费用信息列表
	public void getCostOtherList(){
		try 
		{	
			if(requestFlag==2){
				List list=cost_OtherImpl.getCostOtherList2(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, 
						getRequestFlag());
				String title = "杂项费用清单";
				String[] threads = new String[]{"sheet1","杂项费用清单",	
						"仓别,货主编码,费用代码,费用值,费用日期,费用标识,费用状态,来源单号,对账单号,备注"};			
				commExportAction(title, threads,list);
			}else{
				PageBo pageBo = new PageBo(getStart(), getLimit());
				ExtListDataBo<Cost_OtherListModel> accountList = cost_OtherImpl.getCostOtherList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(),
						strQuery, pageBo
						);
				super.writeStr(covtObjectToJson(accountList));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//获取用于查找的货主下拉
	public void getOwnerNoForQuery(){
		try 
		{
			List<ComboxBo> list = cost_OtherImpl.getOwnerNoForQuery(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取费用代码的下拉
	public void getCostNoForUI(){
		try 
		{
			List<ComboxBo> list = cost_OtherImpl.getCostNoForUI(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取费用代码的下拉
	public void getCostNoForWindown(){
		try 
		{
			List<ComboxBo> list = cost_OtherImpl.getCostNoForWindown(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取其他费用信息列表
	public void getCostOtherSetList(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cost_OtherSetModel> accountList = cost_OtherImpl.getCostOtherSetList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery, pageBo
					);
				super.writeStr(covtObjectToJson(accountList));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//保存杂项费用清单
	public void saveCostOtherList(){
		try
		{	
			cost_OtherImpl.saveCostOtherList(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
	    }	
	}
	//删除杂项费用清单
	public void deleteCostOtherList(){
		try {
			MsgRes msg = cost_OtherImpl.deleteCostOtherList(str,strCostDate);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//保存其他费用
	public void saveCostOtherSet(){
		try
		{	
			cost_OtherImpl.saveCostOtherSet(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		   }	
	}
	//校验费用代码是否已存在
	public void checkCostNo(){
		try {
			String no=cost_OtherImpl.checkCostNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					 strCostNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除其他费用
	public void deleteCostOtherSet(){
		try {
			MsgRes msg = cost_OtherImpl.deleteCostOtherSet(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strCostNo,
					str);
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public ICost_OtherService getCost_OtherImpl() {
		return cost_OtherImpl;
	}
	public void setCost_OtherImpl(ICost_OtherService cost_OtherImpl) {
		this.cost_OtherImpl = cost_OtherImpl;
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
	public String getJsonMaster() {
		return jsonMaster;
	}
	public void setJsonMaster(String jsonMaster) {
		this.jsonMaster = jsonMaster;
	}
	public String getJsonDetail() {
		return jsonDetail;
	}
	public void setJsonDetail(String jsonDetail) {
		this.jsonDetail = jsonDetail;
	}
	public String getStrCostNo() {
		return strCostNo;
	}
	public void setStrCostNo(String strCostNo) {
		this.strCostNo = strCostNo;
	}
	public String getStrCostDate() {
		return strCostDate;
	}
	public void setStrCostDate(String strCostDate) {
		this.strCostDate = strCostDate;
	}
	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}
}
