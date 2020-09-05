package com.sealinkin.odata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;

/**
 * 模块名称：流水标签回单
 * 模块编码：3927
 * 创建：潘振兴
 */
public interface IOdata_OutstockMWaterService {
	/**
	 * 获取下架单头档信息
	 * @author 潘振兴
	 */
	public ExtListDataBo<Odata_OutstockMModel> getWaterM(String enterpriseNo, String warehouseNo,String owner,String flag,String strWave,
			String strBatch, int start,
			int pagesize);
	/**
	 * 获取下架单明细标签列表
	 * @author 潘振兴
	 */
	public ExtListDataBo<Odata_OutstockDModel> getWaterDLabel(String enterpriseNo,String warehouseNo,String owner,String flag,String str,
			String strSendFlag, int start,
			int pagesize) throws Exception;
	
	/**
	 * 获取下架单明细信息
	 * @author 潘振兴
	 */
	public ExtListDataBo<Odata_OutstockDModel> getWaterD(String enterpriseNo,String warehouseNo,String owner,String flag,String str,
			String strSendFlag, int start,
			int pagesize) throws Exception;
	

	/**
     * 流水标签回单 波次……批次下拉
     * @author 潘振兴
	 * 
     */
	public List<ComboxBo> getBatchWaterCombo(String enterpriseNo,String warehouseNo,String workerOwner,String flag,String strWave,
			String strSendFlag, String strCheckFlag, int start,
			int pagesize) ;
	/**
     * 流水标签回单 波次……批次下拉
     * @author 潘振兴
	 * 
     */
	public List<ComboxBo> getWaveWaterCombo(String enterpriseNo,String warehouseNo,String workerOwner,String flag,String str,
			String strSendFlag, String strCheckFlag, String strB2CYesOrNo,int start,
			int pagesize) ;
	
	/**
     * 流水标签回单 保存接口
     * @author 潘振兴
     */
	public MsgRes saveLabel(String enterpriseNo,String strWarehouseNo,String strOutstockName,
			 String strOutstockNo)throws Exception;
	
	
	/**
     * 流水标签零回单 保存接口
     * 
     */
	public MsgRes saveLabelZero(String enterpriseNo,String strWarehouseNo,String workSpaceNo,
			String strOutstockName,
			 String strOutstockNo)throws Exception;
}
