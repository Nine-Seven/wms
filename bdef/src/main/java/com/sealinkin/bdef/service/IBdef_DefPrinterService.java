package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefprinterModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefPrinterService {
	public ExtListDataBo<Bdef_DefprinterModel> getPrinterList(
			String enterpriseNo,String warehouseNo,String queryStr,
			PageBo pageBo,Integer requestFlag)throws Exception;
	
	public boolean saveOrUpdatePrinter(String str)throws Exception;
	
	public String checkPrinterNo(String enterpriseNo,String warehouseNo,String printerNo)throws Exception;
	/**
	 * 填充打印机类型下拉
	 */
	public List<ComboxBo> getPrinterTypeList(String enterpriseNo,String warehouseNo,String str)throws Exception;
	//打印机编码模糊查询打印机信息
	public List<ComboxBo> getPrinterInfo(String strEnterpriseNo,String strWarehouseNo,String str,String strQuery,String wheresql)throws Exception;
	//获取打印机类型下拉
	public List<ComboxBo> getTypeList(String strEnterpriseNo,String strWarehouseNo,String strQuery)throws Exception;
	//填充状态下拉
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
					String strWarehouseNo,String str)throws Exception;
}
