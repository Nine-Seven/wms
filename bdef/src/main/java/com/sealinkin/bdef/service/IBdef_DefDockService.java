package com.sealinkin.bdef.service;

import java.util.List; 

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.bdef.model.Bdef_DefdockModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefDockService {
	
	/**
	 * 获取码头下拉
	 * @param strWarehouseNo
	 * @param strFlag
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryBdefDefDock(
			String strWarehouseNo,String strFlag,String strWheresql,String enterpriseNo)throws Exception;
	
	public List<ComboxBo> getDockComboList(String enterpriseNo,String string)throws Exception;

	public Boolean add(String str)throws Exception;

	public void delete(String enterpriseNo,String warehouseNo, String dockNo)throws Exception;

	public ExtListDataBo<Bdef_DefdockModel> getBdef_DefDockList(String enterpriseNo,String warehouseNo,
			String queryStr, PageBo pageBo, Integer requestFlag)throws Exception;

	public List<String> existsDockNo(String str, String warehouseNo, String enterpriseNo) throws Exception;




}
















