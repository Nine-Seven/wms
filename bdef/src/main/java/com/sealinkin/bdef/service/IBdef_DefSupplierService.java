package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.bdef.model.Bdef_DefsupplierModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefSupplierService {
	
	/**
	 * 填充供应商下拉控件
	 * @param strOwnerNo
	 * @param strWheresql
	 * @param strQuery 
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryBdefDefSupplierCombo(
			String enterpriseNo,String strOwnerNo,String strWheresql, String strQuery)throws Exception;
	
	//保存
	public MsgRes save(String str, String workerNo)throws Exception;

	public ExtListDataBo<Bdef_DefsupplierModel> getBdef_DefSupplierList(String enterpriseNo,String ownerNo, String queryStr, PageBo pageBo, Integer requestFlag)throws Exception;

	public MsgRes delete(String enterpriseNo,String ownerNo, String supplierNo);

	public List<ComboxBo> getSupplierComboList(String enterpriseNo, String ownerNo,String str,
			 String wheresql, int start,int pagesize);

	public String existsSupplierNo(String enterpriseNo, String ownerNo,String supplierNo);
	
}
