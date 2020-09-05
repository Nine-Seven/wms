package com.sealinkin.comm.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleInfoModel;
import com.sealinkin.comm.model.AnsArticlePackingInfoModel;
import com.sealinkin.comm.model.MsgRes;

public interface IGetArticleForBarcodeService {
	
	/**
	 * 校验条码
	 * @param strWarehouseNo
	 * @param strBarcode
	 * @param strOwnerNo
	 * @param strSImportNo
	 * @return
	 * @throws Exception
	 */
	/*public MsgRes checkBarcode(String strWarehouseNo, String strBarcode,
			String strOwnerNo, String strEnterpriseNo) throws Exception;*/
	
	public MsgRes checkBarcode(String strWarehouseNo, 
			String strBarcode,
			String strOwnerNo,String strEnterpriseNo) throws Exception;
	
	public MsgRes checkBarcode(String strWarehouseNo, 
			String strBarcode,String strEnterpriseNO) throws Exception;	
	
	public List<Bdef_ArticleInfoModel> getArticleByScanNo(String strWarehouseNo, 
			String strBarcode,
			String strOwnerNo,String strEnterpriseNo) throws Exception;
	
	/**
	 * 根据商品编号获取商品信息 huangb 20160525
	 * 返回信息：商品包装，单位，规格，委托业主信息
	 * 商品编码，商品包装
	 */
	public List<AnsArticlePackingInfoModel> getArticlePackingInfoByScanNo(
			String strEnterpriseNo, String strArticleNo)
			throws Exception;
}
