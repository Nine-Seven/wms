package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_ExpMModel;

/**
 * 模块名称：自动出货调度
 * 模块代码：3202
 * @author hkl
 */

public interface IOdata_LocateAutoService {
	/**
	 * 根据筛选的条件写临时表
	 */
	public MsgRes saveLocateSelectTem(String enterpriseNo,
			String warehouseNo,
			String strWheresql,String strFlag,String B2Cflag) throws Exception;
	
	/**
	 * 根据筛选的条件写临时表（手动）
	 */
	public MsgRes saveLocateSelectTem_manual(String enterpriseNo,
			String warehouseNo,
			String strWheresql,String strFlag,String B2Cflag) throws Exception;
	/**
	 * 获取临时表数据
	 */
	
	public ExtListDataBo<Odata_ExpMModel> getLocateM(
			String enterpriseNo,String strWarehouseNo,
			String strFlag,PageBo poPageBo)throws Exception;
	
	
	/**
	 * 修改临时表的数据为选中
	 */
	public MsgRes updateTmpLocateSelect1(String enterpriseNo,
			String warehouseNo,
			String strFlag,String calFlag,String expNo,String custNo,
			String expType,String ownerNo) throws Exception;
	/**
	 * 修改临时表的数据为不选中
	 */
	public MsgRes updateTmpLocateSelect0(String enterpriseNo,
			String warehouseNo,
			String strFlag,String calFlag,String expNo,String custNo,
			String expType,String ownerNo) throws Exception;
	
	
	//获取月台可用货位数
	public MsgRes tscBufferQty(String enterpriseNo,String warehouseNo,
			 String strFlag)throws Exception;
	/**
	 * 计算勾选的单据详情
	 * @param string 
	 */
	public List<Odata_ExpMModel> countDetail(String enterpriseNo,String strWarehouseNo)throws Exception;
	
	//删除临时表状态为0的数据
	public MsgRes deleteLocateSelect(String enterpriseNo,String warehouseNo)throws Exception;
	
	////定位-切批次；集单；定位
	public MsgRes tscFixed(String strDetail)throws Exception;
	
	//删除临时表状态为0的数据
	public MsgRes updateTmpLocateSelectAll(String enterpriseNo,String warehouseNo,
			String strWheresql,String strFlag,String calFlag)throws Exception;
		
}
