/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
package com.sealinkin.bdef.service;


import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.bset.model.Bset_WorkstationDivideModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public interface IBset_workstationDivideService {
    //获取工作站信息
	public ExtListDataBo<Bset_Printer_WorkstationModel> getBsetPrinterWorkstationList(
			String enterpriseNo,String warehouseNo,String wheresql,PageBo pageBo)throws Exception;
	//获取设备组列表
	public ExtListDataBo<Bset_WorkstationDivideModel> getBsetWorkstationDivideList(
			String enterpriseNo,String warehouseNo,String wheresql,PageBo pageBo)throws Exception;
	//设备组添加工作站
	public boolean saveWorkstationDivide(String str)throws Exception;
	//设备组移除工作站
	public boolean deleteWorkstationDivide(
			String enterpriseNo,String warehouseNo,String deviceGroupNo,String workstationNo)throws Exception;

}
