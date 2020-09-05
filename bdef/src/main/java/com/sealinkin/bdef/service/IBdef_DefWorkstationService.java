package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefWorkstationService {

	public Boolean add(String str) throws Exception;

	public ExtListDataBo<Bset_Printer_WorkstationModel> getBdef_defWorkstationList(
			String enterpriseNo, String warehouseNo, String queryStr, PageBo pageBo,
			Integer requestFlag)throws Exception;

	public void delete(String enterpriseNo, String warehouseNo, String workstationNo)throws Exception;
	
	public void saveBset_Printer_Workstation(String str)throws Exception;
	
	public void deleteBset_Printer_Workstation(
			String enterpriseNo,String warehouseNo,String workstationNo,String printerGroupNo)throws Exception;
	
	public String checkWorkstationNo(String enterpriseNo,String warehouseNo,String workstationNo)throws Exception;

	public ExtListDataBo<Bset_Printer_WorkstationModel> getWorkstationCombo(
			String currEnterpriseNo, String warehouseNo, String strQuery,
			PageBo pageBo, Integer requestFlag)throws Exception;
	//校验工作站
    public MsgRes checkWorkStation(String strEnterpriseNo,String strWarehouseNo,String str)throws Exception;
    //工作站编码模糊查询工作站
    public List<ComboxBo> getWorkStationInfo(String strEnterpriseNo,String strOwnerNo,String str,String strQuery)throws Exception;
    //打印机组编码模糊查询
    public List<ComboxBo> getprinterGroupInfo(String strEnterpriseNo,
			String strWarehouseNo, String str, String strQuery) throws Exception;
	

}
