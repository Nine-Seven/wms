package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.bdef.model.Bdef_DeflocModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefWorkerService {

	public MsgRes saveBdef_DefWorker(String str)throws Exception;
	
	public ExtListDataBo<Bdef_DefWorkerModel> getBdef_DefWorker(String str,PageBo pageBo);
	
	/**
	 * 获取员工下拉
	 * @param strWheresql
	 * @param strWarehouseNo 
	 * @param strOwnerNo 
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> query_WorkerCombo(String enterpriseNo, String strWarehouseNo, String strOwnerNo, String strWheresql)throws Exception;
	
	public List<ComboxBo> getBdef_DefWorker();
	
	public ExtListDataBo<Bdef_DefWorkerModel> getWorkerList(String strEnterpriseNo,
			String strQuery,
			String strWarehouseNo, 
			PageBo pageBo,
			String flag	//分页标识1：不分页
			)throws Exception;
	
	public ExtListDataBo<Bdef_DeflocModel> getWarehouseList(String str,PageBo pageBo)throws Exception;
	
	//通过用户编号获取仓别列表
	public ExtListDataBo<Bdef_DeflocModel> getWarehouseListByWorkerNo(String str,String strWheresql,PageBo pageBo)throws Exception; 
	
	public ExtListDataBo<Bdef_DefOwnerModel> getOwnerList(String str,PageBo pageBo)throws Exception;
	
	//通过用户编号获取货主列表
	public ExtListDataBo<Bdef_DefOwnerModel> getOwnerListByWorkerNo(String str,String strWheresql,PageBo pageBo)throws Exception;
	
	public ExtListDataBo<Bdef_DefWorkerModel> getWorkerList2(String strEnterpriseNo,String strWheresql, PageBo pageBo)throws Exception;
}
