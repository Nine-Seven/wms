package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_ExpCancelDModel;
import com.sealinkin.odata.model.Odata_ExpCancelMModel;

public interface IOdata_ExpCancelCheckService {
	/**
	 * 获取病单头档信息
	 * @author hkl
	 */
	public ExtListDataBo<Odata_ExpCancelMModel> getCancelCheck_MList(String enterpriseNo, String warehouseNo,String owner,String str,
			int start,
			int pagesize);
	
	/**
	 * 获取病单明细信息
	 * @author hkl
	 */
	public ExtListDataBo<Odata_ExpCancelDModel> getCancelCheck_DList(String enterpriseNo,String warehouseNo,String str,
			int start,
			int pagesize) throws Exception;
	
	/**
     * 病单审核 货主、病单单号下拉
     * @author hkl
     */
	public List<ComboxBo> getOdata_CancelCheckCombo(String enterpriseNo,String warehouseNo,String workerOwner,String flag,String str,
			 int start,int pagesize) ;
	
	//审核
	public MsgRes sendCancel(String currEnterpriseNo,String warehouseNo,
			String workerNo,String ownerNo,String workSpaceNo,String expNo,
	String cancelNo,String handleflag)throws Exception;

}
