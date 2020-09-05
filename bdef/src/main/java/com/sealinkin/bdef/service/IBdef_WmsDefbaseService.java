package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_WmsDefbaseModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;



public interface IBdef_WmsDefbaseService {
	/**
	 * 获取系统参数列
	 * @param strQueryStr
	 * @param poPageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Bdef_WmsDefbaseModel> getWmsDefbaseList(
			String enterpiseNo,String strGroupNo,String strSubGroupNo,PageBo poPageBo)throws Exception;
	
	/**
	 * 保存系统参数
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveWmsDefbase(String strColnameNo)throws Exception;
	
//	/**
//	 * 获取系统参数配置的 GroupNo 下拉
//	 * @param string 
//	 * @return
//	 * @throws Exception
//	 * */
//	public List<ComboxBo> getGroupNoCombo(String enterpiseNo);
	
	/**
	 * 获取系统参数配置的 SubGroupNo 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getSubGroupNoCombo(String enterpiseNo,String strGroupNo);
	
	/**
	 * 获取系统参数配置的 sdefine 下拉
	 * @return
	 * @throws Exception
	 * */
	public List<ComboxBo> getSdefineCombo(String colname);

	/**
	 * 通过模块加载全部数据
	 * @param string 
	 * */
	public List<ComboxBo> getAllFromGroupNoCombo(String enterpiseNo);
	
}
