package com.sealinkin.bdef.service;


import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;


public interface IBdef_DefOwnerService {
	
	/**
	 * 获得货主资料
	 * @param string 
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Bdef_DefOwnerModel> getBdef_DefOwner(String enterpriseNo,
			String strOwnerNo,
			String strWorkerNo,
			String strQuery,
		    PageBo pageBo,
			Integer requestFlag)throws Exception;
	
	/**
	 * 保存或修改货主
	 * @param strQuery
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateOwner(String strBo, String strWorkNo,String strWareHouseNO,String strWcellManagerType,String strWtypeValue)
    throws Exception;
	
	
	
	/**
	 * 获取货主下拉数据	
	 * @param strOwnerNo
	 * @param strQuery
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getBdef_DefOwnerCombo(String strOwnerNo,
			String strQuery)throws Exception;
	/**
	 * 新增时检查货主编码是否存在
	 * @param strOwnerNo
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes existsOwnerNo(String enterpriseNo,String strOwnerNo)throws Exception;
	
	//填充货主下拉
	public List<ComboxBo> queryOwnerCombo(
			String enterpriseNo,String strOwnerNo,String strFlag,String strWheresql)throws Exception;

	//填充承运商下拉
	public List<ComboxBo> queryShipperCombo(
			String enterpriseNo,String strOwnerNo,String strFlag,String strWheresql)throws Exception;
	
	//删除货主
	public MsgRes deleteOwner(String currEnterpriseNo, String str)throws Exception;

	//判断是否可以修改货主状态
	public MsgRes isCanChange(String currEnterpriseNo, String warehouseNo,
			String str)throws Exception;
	public List<ComboxBo> getStatusList(String strEnterpriseNo,String strOwnerNo,String strQuery)throws Exception;
}
