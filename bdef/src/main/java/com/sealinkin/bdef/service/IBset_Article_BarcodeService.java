package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bset.model.Bset_ArticleBarcodeDModel;
import com.sealinkin.bset.model.Bset_ArticleBarcodeMModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBset_Article_BarcodeService {
	
	/**
	 * 获取采集商品信息
	 * @param str
	 * @param pageBo
	 * @param warehouseNo 
	 * @return
	 */
	public ExtListDataBo<Bset_ArticleBarcodeMModel> getBset_Article_Barcode_MList(
			String enterpriseNo,String str,PageBo pageBo, String warehouseNo)throws Exception;
	
	/**
	 * 单明细
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Bset_ArticleBarcodeDModel> getBset_Article_Barcode_DList(String enterpriseNo,String wheresql,PageBo pageBo)throws Exception;
	
	/**
	 * 保存
	 * @return
	 */
	public MsgRes save(String jsonMaster, String jsonDetail)throws Exception;

	public List<ComboxBo> getArticle(String currEnterpriseNo,
			String workerOwner, String strQuery, String strWheresql)throws Exception;
	
	
}
