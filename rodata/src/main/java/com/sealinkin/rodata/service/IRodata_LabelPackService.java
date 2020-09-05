package com.sealinkin.rodata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_BoxDModel;
import com.sealinkin.rodata.model.Rodata_BoxMModel;

/*
 * 退厂拼箱打包
 * hekangli
 */

public interface IRodata_LabelPackService {
	//单品转移
	MsgRes saveArticleMoveLabel(String str) throws Exception;

	//整箱转移
	MsgRes saveMoveLabel(String str) throws Exception;

	/**
	    退厂拼箱打包源标签商品列表
	 * @return
	 */
	ExtListDataBo<Rodata_BoxDModel> getRodata_sLabelNoArticleList(
			String enterpriseNo,
			String warehouseNo,String strRecedeNo,String strLabelNo,
			Integer start, 
			Integer limit);
	/**
          退厂拼箱打包目的标签商品列表
   * @return
   */
    ExtListDataBo<Rodata_BoxDModel> getRodata_dLabelNoArticleList(
		String enterpriseNo,
		String warehouseNo,String strRecedeNo,String strLabelNo);

	/**
	 * 退厂拼箱打包标签列表
	 * @param string
	 * @param start
	 * @param limit
	 * @return
	 */
	ExtListDataBo<Rodata_BoxMModel> getRodata_sLabelList(String strRecedeNo, 
			String enterpriseNo,
			String warehouseNo);	
	
	/**
     * 退货单号下拉
	 * @param  
     */
	public List<ComboxBo> getRecedeNo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strRecedeNo) ;
	
	//取箱号
	MsgRes getLoadBox(String enterpriseNo,String warehouseNo, String strQuery)throws Exception;

	//回库
	MsgRes saveMoveQty(String enterpriseNo,String warehouseNo,String WorkerNo, String str)throws Exception;

}
