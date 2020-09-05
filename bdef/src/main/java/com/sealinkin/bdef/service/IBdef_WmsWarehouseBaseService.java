package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WarehouseBaseModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_WmsWarehouseBaseService {
	/**
	 * 获取系统参数列
	 * @param strQueryStr
	 * @param poPageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Bdef_WarehouseBaseModel> getWmsWarehouseBaseList(String enterpriseNo,
			String strWarehouseNo,String strGroupNo,String strSubGroupNo,PageBo poPageBo)throws Exception;
	
	/**
	 * 保存系统参数
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveWmsWarehouseBase(String strColnameNo)throws Exception;
	
	/**
	 * 获取系统参数配置的 GroupNo 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getGroupNoCombo();
	
	/**
	 * 获取系统参数配置的 SubGroupNo 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getSubGroupNoCombo(String enterpriseNo,
			String warehouseNo,String strGroupNo);
	
	
	/**
	 * 获取系统参数配置的 GroupNo 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getAllGroupNoCombo();
	
	/**
	 * 获取系统参数配置的 SubGroupNo 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getAllSubGroupNoCombo(String strGroupNo);
	
	/**
	 * 获取系统参数配置的 sdefine 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getSdefineCombo(String colname);

	/**
	 * 通过模块加载全部数据
	 * @param string2 
	 * @param string 
	 * */
	public List<ComboxBo> getAllFromGroupNoCombo(String enterpriseNo,
			String strWarehouseNo);
	
	/**
	 * 加载全部列名 colname
	 * */
	public List<ComboxBo> getColnameCombo(String strGroupNo);
	/**
	 * 加载 Memo
	 * */
	public List<String> getMemoCombo(String strColname);

}
