package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefCustService {
	
	/**
	 * 获得客户资料列
	 * @param str
	 * @param pageBo
	 * @return
	 */                                                              
	public ExtListDataBo<Bdef_DefCustModel> getBdef_DefCust(String enterpriseNo,String workerOwner,String str,PageBo pageBo,Integer requestFlag)throws Exception;
	
	/**
	 * 保存或修改客户
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateCust(String str,String workNo)throws Exception;
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteCust(String enterpriseNo,String id)throws Exception;
	
	
	public List<ComboxBo> getBdef_DefCustComboList(String enterpriseNo,String workerOwner,String strQuery,String pageSql)throws Exception;
	
	public List<String> getCustName(String enterpriseNo,String custNo)throws Exception;
	
	public List<ComboxBo> getOwnerComboList(String enterpriseNo,String pageSql)throws Exception;
	
	public List<ComboxBo> getCarCombo(String enterpriseNo,String warehouseNo,String pageSql)throws Exception;
	
	public String checkCustNo(String enterpriseNo,String custNo)throws Exception;
	//货主客户代码校验
	public String checkOwnerCustNo(String enterpriseNo,String strOwnerNo,String sttrOwnerCustNo)throws Exception;
	public List<ComboxBo> getCustInfo(String strEnterpriseNo,String strOwnerNo,
			 String str,
			String strQuery) throws Exception;

}
