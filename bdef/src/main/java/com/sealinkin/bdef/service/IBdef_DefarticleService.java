/**
 * 商品service
 */
package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleBarcodeModel;
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.model.Bdef_WarehousePackingModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_DefarticleService {
	
	/**
	 * 获取商品资料列
	 * @param strWorkerOwner
	 * @param strQueryStr
	 * @param poPageBo
	 * @param intRequestFlag
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Bdef_DefarticleModel> query_BdefDefarticleList(
			String enterpriseNo,String strOwnerNo,String strQuery,PageBo poPageBo, Integer intRequestFlag)throws Exception;
	
	/**
	 * 保存商品主档
	 * @param strArticle
	 * @param strSaveType
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveArticle(String strArticle, String strSaveType, String workerNo)throws Exception;
	
	/**
	 * 删除商品
	 * @return
	 * @throws Exception
	 */
	public MsgRes deleteArticleNo(String strArticleNo, String strOwnerNo, String enterpriseNo)throws Exception;
	
	
	
	/**
	 * 获取商品包装列
	 * @param wheresql
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Bdef_ArticlePackingModel> query_BdefArticlePackingList(
			String enterpriseNo,String strArticleNo,PageBo poPageBo)throws Exception;
	
	/**
	 * 获取仓别堆叠
	 * @param wheresql
	 * @return
	 * @throws Exception
	 */
	public List<Bdef_WarehousePackingModel> queryBdefWarehousePacking(
			String enterpriseNo,String warehouseNo,String strArticleNo,String strPackingQty)throws Exception;
	/**
	 * 保存商品包装
	 * @param strPacking
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateArticlePacking(String strPacking, String strWorkerNo)throws Exception;
	
	/**
	 * 保存商品仓别堆叠
	 * @param strPacking
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateWarehousePacking(String strPacking, String WarehouseNo)throws Exception;
	
	/**
	 * 获取商品条码列
	 * @param strWheresql
	 * @param poPageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Bdef_ArticleBarcodeModel> query_BdefArticleBarcodeList(
			String enterpriseNo,String strWheresql,String strOwnerNo,PageBo poPageBo)throws Exception;
	
	/**
	 * 保存商品条码
	 * @param strBarcode
	 * @param strBarcode2 
	 * @return
	 * @throws Exception
	 */
	public MsgRes saveOrUpdateArticleBarcode(String strBarcode)throws Exception;
	
	/**
	 * 获取商品下拉
	 * @param strOwnerNo
	 * @param strFlag
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> query_BdefArticleCombo(
			String enterpriseNo,String strOwnerNo,String strQuery,String strWheresql)throws Exception;
	
	/**
	 * 获取包装下拉
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> qyery_ArticlePackingCombo(String enterpriseNo,String strWheresql)throws Exception;
	
	/**
	 * 验证商品编码
	 * @param strArticleNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes check_ArticleNo(String enterpriseNo,String strArticleNo)throws Exception;
	
	/**
	 * 下拉获取商品名称
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<String> get_ArticleName(String enterpriseNo,String strWheresql)throws Exception;
	
	/**
	 * 根据商品编码和包装获取规格和单位
	 * @param strArticleNo
	 * @param strPackingQty
	 * @param strType
	 * @return
	 * @throws Exception
	 */
	public List<Bdef_ArticlePackingModel> queryPackingUnitAndSpec(
			String enterpriseNo,String strArticleNo,String strPackingQty,String strType)throws Exception;
	/**
	 * 根据商品编码获取包装数量、规格和单位
	 * @param enterpriseNo
	 * @param strArticleNo
	 * @param strPackingQty
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<Bdef_ArticlePackingModel> queryPackingQtyAndUnitAndSpec(
			String enterpriseNo,String strArticleNo,String str)throws Exception;

	/**
	 * 主条码标识为1的不允许保存
	 * @param strArticleNo
	 * @param strPackingQty
	 * @param strBarcode
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkSaveBarcode(String enterpriseNo,String strArticleNo,String strPackingQty,String strBarcode)throws Exception;

	public String getMaxArticlePacking(String enterpriseNo,String strWheresql);
	
	//货主编码校验
	public MsgRes checkOwnerNo(String strEnterpriseNo,String strArticleNo)throws Exception;

	/*
	 * RF堆叠采集》条码扫描
	 */
	public MsgRes ArticleQpaletteBarcodeInput(String strRecvData)throws Exception;
	
	/*
	 * RF堆叠采集》获取商品包装
	 */
	public MsgRes GetArticlePacking(String strRecvData)throws Exception;
	
	/*
	 * RF堆叠采集》更新商品堆叠
	 */
	public MsgRes updateArticleQpalette(String strRecvData)throws Exception;
	/*
	 * RF堆叠采集》新增商品包装/条码
	 */
	public MsgRes InsertArticleQpalette(String strRecvData)throws Exception;
	
	/*
	 * RF堆叠采集》扫描编码
	 */
	public MsgRes InsertArticleNOQpalette(String strRecvData)throws Exception;
	
	//堆叠采集》新增商品信息资料
	public MsgRes GetPackingArticleQpalette(String strRecvData)throws Exception;
	//商品编码
	public List<ComboxBo> getArticleInfo(String strEnterpriseNo,String strOwnerNo,String str,String strQuery)throws Exception;

	//填充状态下拉
	public List<ComboxBo> getStatusList(String strEnterpriseNo,
					String strOwnerNo,String str)throws Exception;

	/* Add by sunl 获取商品的所有包装数量及单位 */ 
	public MsgRes SelectArticlePackingInfo(String strRecvData) throws Exception;
}
