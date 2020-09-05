package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
@SuppressWarnings("rawtypes")
public interface IBdef_CsetCustDpscellService {	
	
	public List getCdef_DefCell(String enterpriseNo,String warehouseNo,String strCustNo,String str);
	//新增
	public MsgRes add(String jsonMaster)throws Exception;
	//删除
	public MsgRes delete(String enterpriseNo,String warehouseNo,String strCustNo,String str)throws Exception; 
    //检索电子标签客户
	public String checkCustNo(String enterpriseNo,String warehouseNo,String strCustNo,String wheresql);
	//检索电子标签储位
		public String checkDpsCellNo(String enterpriseNo,String warehouseNo,String strDpsCellNo);
	//获取客户列表
	public ExtListDataBo<Bdef_DefCustModel> getBdefCsetCust(String enterpriseNo,String warehouseNo,
			String workerOwner,String strAssignedCustomer,String wheresql,String str,String strFlag,PageBo pageBo)throws Exception;
	//获取货主编号列表
	public  List<ComboxBo> getOwnerComboList(String enterpriseNo,String str) throws Exception;
	//获取分播组
	public  List<ComboxBo> getDeviceGroupNoList(String enterpriseNo,String warehouseNo,String str) throws Exception;
	//获取分播线
	public  List<ComboxBo> getDeviceNoList(String enterpriseNo,String warehouseNo,String str) throws Exception;
	//检索未分播完成客户
	public MsgRes checkDeliverCustNo(String enterpriseNo,String warehouseNo,String ownerNo,String strCustNo)throws Exception;


	
}