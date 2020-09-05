package com.sealinkin.bdef.service;


import java.util.List;

import com.sealinkin.bdef.model.Wms_LogiboxRuleModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;


public interface IBdef_WmsLogiboxRuleService {
	
	/**
	 * 获得物流箱规则资料
	 * @param string 
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Wms_LogiboxRuleModel> getWms_LogiboxRule(String enterpriseNo,
			String strOwnerNo,
			String strWorkerNo,
			String strQuery,
		    PageBo pageBo,
			Integer requestFlag)throws Exception;
	
	/**
	 * 保存或修改物流箱规则
	 * @param strQuery
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateWmsLogiboxRule(String strQuery,String strWorkerNo)throws Exception;
	
	
	
	/**
	 * 获取物流箱规则下拉数据	
	 * @param strOwnerNo
	 * @param strQuery
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getWms_LogiboxRuleModelCombo(String strOwnerNo,
			String strQuery)throws Exception;
	/**
	 * 新增时检查物流箱规则编码是否存在
	 * @param strOwnerNo
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes existsOwnerNo(String enterpriseNo,String strOwnerNo)throws Exception;
	
 
 
	//删除物流箱规则
	public MsgRes deleteWmsLogiboxRule(String currEnterpriseNo, String str)throws Exception;

 }
