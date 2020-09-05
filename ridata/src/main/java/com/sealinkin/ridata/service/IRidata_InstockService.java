package com.sealinkin.ridata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.ridata.model.Ridata_InstockDModel;
import com.sealinkin.ridata.model.Ridata_InstockMModel;

public interface IRidata_InstockService {
	
	/**
	 * 返配上架回单M
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_InstockMModel> getRidata_InstockMList(
			String enterpriseNo,
			String warehouseNo,String workerOwner,
			String queryStr,PageBo pageBo);
	
	/**
	 * 返配上架单单D
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_InstockDModel> getRidata_InstockDList(
			String enterpriseNo,String warehouseNo,String wheresql,PageBo pageBo);
	
	public boolean save(String enterpriseNo,String workerOwner,String jsonDetail1)throws Exception;
	
	//取消上架
	public MsgRes tscCloseInstock(String enterpriseNo,String warehouseNo,String workerOwner,String wheresql)throws Exception;

	/**
	 * 储位下拉
	 * @param strWarehouseNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryCell(String enterpriseNo,String strWarehouseNo,String strWheresql)throws Exception;
	/**
	 * 波次下拉
	 */
	public List<ComboxBo> queryWaveNo(String enterpriseNo,String strWarehouseNo,String strWheresql)throws Exception;
	
	/***
	 * 校验标签号并且获取标签商品信息（天天惠） 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscCheckLabelAndGetInfo(String strRecvData)throws Exception;


	/***
	 * 返配上架整理确认（天天惠） 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscArrangeComfire(String strRecvData)throws Exception;

	//指定储位回单
	public MsgRes saveInstock(String currEnterpriseNo, String warehouseNo,
			String instockNo, String workerNo, String cellNo)throws Exception;

}
