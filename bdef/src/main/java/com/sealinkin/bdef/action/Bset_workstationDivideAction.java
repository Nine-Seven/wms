/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
package com.sealinkin.bdef.action;


import com.sealinkin.bdef.service.IBset_workstationDivideService;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.bset.model.Bset_WorkstationDivideModel;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public class Bset_workstationDivideAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBset_workstationDivideService bset_workstationDivideImpl;
	private String str;
	private String strQuery;
	private String wheresql;
	private String warehouseNo;
	private String deviceGroupNo;
	private String workstationNo;

	//获取工作站信息
	public void getBsetPrinterWorkstationList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_Printer_WorkstationModel> list=bset_workstationDivideImpl.getBsetPrinterWorkstationList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//设备组列表
	public void getBsetWorkstationDivideList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bset_WorkstationDivideModel> list=bset_workstationDivideImpl.getBsetWorkstationDivideList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), wheresql, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//设备组添加工作站
	public void saveWorkstationDivide(){
		try {
			bset_workstationDivideImpl.saveWorkstationDivide(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}
	//设备组移除工作站
	public void deleteWorkstationDivide(){
		try {
			bset_workstationDivideImpl.deleteWorkstationDivide(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),warehouseNo,deviceGroupNo, workstationNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public IBset_workstationDivideService getBset_workstationDivideImpl() {
		return bset_workstationDivideImpl;
	}

	public void setBset_workstationDivideImpl(
			IBset_workstationDivideService bset_workstationDivideImpl) {
		this.bset_workstationDivideImpl = bset_workstationDivideImpl;
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

	public String getWheresql() {
		return wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getDeviceGroupNo() {
		return deviceGroupNo;
	}

	public void setDeviceGroupNo(String deviceGroupNo) {
		this.deviceGroupNo = deviceGroupNo;
	}

	public String getWorkstationNo() {
		return workstationNo;
	}

	public void setWorkstationNo(String workstationNo) {
		this.workstationNo = workstationNo;
	}

}
