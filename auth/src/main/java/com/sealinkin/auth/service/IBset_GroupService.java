package com.sealinkin.auth.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.bset.model.Bset_GroupModel;
import com.sealinkin.bset.model.Bset_Printer_GroupModel;
import com.sealinkin.bset.model.Bset_Printer_WorkstationModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public interface IBset_GroupService {
	public ExtListDataBo<Bset_GroupModel> getBset_GroupList(
			String enterpriseNo,String warehouseNo,String queryStr,
			PageBo pageBo,Integer requestFlag)throws Exception;
	
	public boolean saveOrUpdateGroup(String str)throws Exception;
	
	public boolean savePrinter_Group(String str)throws Exception;
	
	public ExtListDataBo<Bset_Printer_GroupModel> getBset_Printer_GroupList(
			String enterpriseNo,String warehouseNo,String wheresql,PageBo pageBo)throws Exception;
	
	public ExtListDataBo<Bdef_DefprinterModel> getBdef_DefPrinterList(
			String enterpriseNo,String warehouseNo,String wheresql,String str, PageBo pageBo)throws Exception;
	
	public ExtListDataBo<Bset_Printer_WorkstationModel> getBset_Printer_WorkstationList(
			String enterpriseNo,String warehouseNo,String wheresql,PageBo pageBo)throws Exception;
	
	public List<ComboxBo> getBset_GroupComboList(
			String enterpriseNo,String warehouseNo,String str, int start,int pagesize)throws Exception;
	
	public boolean deletePrinter_Group(
			String enterpriseNo,String warehouseNo,String printerGroupNo,String printerNo)throws Exception;
	
	public ExtListDataBo<Bset_GroupModel> getBset_GroupList2(
			String enterpriseNo,String warehouseNo,PageBo pageBo)throws Exception;
	
	public String checkBset_GroupCombo(String enterpriseNo,String warehouseNo,String printerGroupNo);
	
	public String checkPrinterGroupNo(String enterpriseNo,String warehouseNo,String printerGroupNo)throws Exception;

	//检查打印机组是否有相同来类型
	public List<String> checkPrinterType(String enterpriseNo,String warehouseNo,
			String printerGroupNo, String printerType);
	//打印机组编码模糊查询打印机组信息
	public List<ComboxBo> getPrinterGroupInfo(String strEnterpriseNo,String strWarehouseNo,String str,String strQuery,String wheresql)throws Exception;
}
