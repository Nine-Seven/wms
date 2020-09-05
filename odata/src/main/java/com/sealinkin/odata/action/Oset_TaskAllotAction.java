package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.service.IOset_TaskAllotService;
import com.sealinkin.oset.model.Oset_TaskAllotDModel;
import com.sealinkin.oset.model.Oset_TaskAllotMModel;

public class Oset_TaskAllotAction extends CommAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IOset_TaskAllotService oset_TaskAllotImpl;
	private String str;
	private String warehouseNo;
	private String wareNo;
	private String areaNo;
	private String taskId;
	
	
	//获取切单规则头档
	public void getTaskAllotM(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Oset_TaskAllotMModel> taskAllotMList = oset_TaskAllotImpl.getAccountList(super.getMdBdef_DefWorker().getWarehouseNo(), pageBo);
			super.writeObj(taskAllotMList);		
		} catch (Exception e){
			e.printStackTrace();
		};
	}
	
	//判断当前仓别tasId是否唯一
	public void checkTaskId(){
		try 
		{		
			List<String> list = oset_TaskAllotImpl.billingProjectCheck(super.getMdBdef_DefWorker().getWarehouseNo(),this.getStr());	
			super.writeArray(list);	
		} catch (Exception e){
			e.printStackTrace();
		};
	}
	
	//获取默认配置的下拉
	public void getDefaultFlagmComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getDefaultFlagmComboList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//保存或更新切单规则头档
	public void saveOrupdateTaskAllotM(){
		try
		{	
			oset_TaskAllotImpl.saveOrupdateTaskAllotM(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}

	//获取储区(匹配了规则或没匹配规则的储区，区别在于this.getStr()有木有值)
	public void getDefarea(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Cdef_DefareaModel> accountList = oset_TaskAllotImpl.getDefarea(super.getMdBdef_DefWorker().getWarehouseNo(),pageBo,this.getStr());
			super.writeObj(accountList);		
		} catch (Exception e){
			e.printStackTrace();
		};
	}
	
	//跟新切单规则和储区的关系
	public void updateAllotAndDefareaRelation(){
		try {
			oset_TaskAllotImpl.updateAllotAndDefareaRelation(this.getTaskId(),this.getWarehouseNo(),this.getWareNo(),this.getAreaNo());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}	
	}
	
	//根据切单规则头档获取切单规则明细
	public void getTaskAllotD(){
		try 
		{		
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Oset_TaskAllotDModel> taskAllotD = oset_TaskAllotImpl.getAccountList( pageBo,this.getStr());
			super.writeObj(taskAllotD);	
		} catch (Exception e){
			e.printStackTrace();
		};
	}
	
	//获取下架类型下拉
	public void getOutstockTypeComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getOutstockTypeComboList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//获取切单范围下拉
	public void getAllotRuleComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getAllotRuleComboList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取切单规则下拉
	public void getBoxFlagComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getBoxFlagComboList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取拣货打单方式下拉
	public void getTaskTypeComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getTaskTypeComboList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取作业类型1
	public void getSourceTypeComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getSourceTypeComboList(this.getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取作业类型2
	public void getOoperateTypeComboList(){
		try 
		{
			List<ComboxBo> list = oset_TaskAllotImpl.getOoperateTypeComboList();
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//保存或修改订单规则明细
	public void saveOrupdateTaskAllotD(){
		try
		{	
			oset_TaskAllotImpl.saveOrupdateTaskAllotD(this.getStr()) ;
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, "保存失败", ""));
		}		
	}
	
	public IOset_TaskAllotService getOset_TaskAllotImpl() {
		return oset_TaskAllotImpl;
	}
	public void setOset_TaskAllotImpl(IOset_TaskAllotService oset_TaskAllotImpl) {
		this.oset_TaskAllotImpl = oset_TaskAllotImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getWareNo() {
		return wareNo;
	}
	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}
