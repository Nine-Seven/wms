package com.sealinkin.sodata.service;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.sodata.model.Sodata_OutStockDModel;
import com.sealinkin.sodata.model.Sodata_OutStockMModel;

/**
 * 报损回单
 * @author hekangli
 *
 */
public interface ISodata_OutStockService {
	
	/**
	 * 获取报损回单头档
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Sodata_OutStockMModel> getOutStock_MList(String currEnterpriseNo,
			String warehouseNo,String workerOwner, String queryStr)throws Exception;
	
	/**
	 * 报损回单D
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Sodata_OutStockDModel> getOutStock_DList(String currEnterpriseNo,String warehouseNo,String wheresql)throws Exception;
	
	//发单
	public MsgRes save(
			    String workerNo, String strDockNo,
			    String strQuery,String type)throws Exception;


}
