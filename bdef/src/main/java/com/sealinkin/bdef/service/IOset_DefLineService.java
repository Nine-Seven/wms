package com.sealinkin.bdef.service;


import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.oset.model.Oset_LineCustModel;

public interface IOset_DefLineService {

	public ExtListDataBo<Oset_DeflineModel> getDefLine(String enterpriseNo,String warehouseNo,String queryStr,PageBo pagebo,Integer requestFlag)throws Exception;
	
	public ExtListDataBo<Oset_LineCustModel> getLineCust(String enterpriseNo,String wheresql,String strWarehouseNo)throws Exception;
	
	public ExtListDataBo<Oset_LineCustModel> getCust(String enterpriseNo,String warehouseNo,PageBo pagebo, String workerOwner, String strQuery)throws Exception;
	
	public boolean saveOset_DefLine(String str)throws Exception;
	
	public boolean deleteOset_DefLine(String enterpriseNo,String warehouseNo,String lineNo)throws Exception;
	
	public boolean saveOset_Line_Cust(String str)throws Exception;
	
	public boolean deleteOSet_Line_Cust(String enterpriseNo,String warehouseNo,String lineNo,String custNo)throws Exception;
	
	public String checkLineNo(String enterpriseNo,String warehouseNo,String lineNo)throws Exception;
	
	public MsgRes updateSearchD(String strEnterpriseNo,
			String strWarehouseNo,
			String lineNo,
			String custNo,
			String lineSeqNo,String str)throws Exception;
	
}
