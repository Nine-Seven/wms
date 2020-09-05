package com.sealinkin.cset.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_CellArticleModel;

public interface ICset_CellArticleService {
	
	// 保存、修改商品储位对应关系	 
	public MsgRes saveCset_Cell_Article(String workerNo, String str) throws Exception;
	
	// 获取商品储位对应关系列表
	public ExtListDataBo<Cset_CellArticleModel> getCset_Cell_ArticleList(
			String enterpriseNo,
			String warehouseNo,String ownerNo,
			String strQuery, PageBo pageBo, Integer requestFlag)throws Exception;
	// 获取导出的商品储位对应关系
	public List<Cset_CellArticleModel> getCset_Cell_ArticleList2(
			String enterpriseNo,String warehouseNo,String ownerNo,
			String strQuery,Integer requestFlag)throws Exception;
	//删除
	public void delete(String enterpriseNo,String warehouseNo, String ownerNo, String articleNo,
			String pickType)throws Exception;

	// 获得商品条码和商品编码
	public List<Cset_CellArticleModel> getCset_BarcodeAndArticleNoCombo(
			String enterpriseNo,String ownerNo, String wheresql)throws Exception;
	
	//获取拣货类型
	public List<ComboxBo> getOtypeo(
			String enterpriseNo,String warehouseNo)throws Exception;
	// 校验新增商品储位对应关系是否重复
	public ExtListDataBo<Cset_CellArticleModel> existsCsetCellArticle(String enterpriseNo,String warehouseNo,String ownerNo,
			String strQuery)throws Exception;

	//校验对应的拣货类型下是否有储区 
	public ExtListDataBo<ComboxBo> existsAreaList(String enterpriseNo,String warehouseNo,
			String workerOwner, String queryStr)throws Exception;
	
	// 获取线路下拉
	public List<ComboxBo> queryLineCombo(String enterpriseNo,String strWarehouseNo,String strQuery)throws Exception;
	
	// 获取stock_x
	public List<String> queryStoctX(String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strCellNo)throws Exception;
	
	//获取货主商品编码下拉
	public List<ComboxBo> queryOwnerArticleNoList(String enterpriseNo,String OwnerNo,String strWheresql)throws Exception;

	//获取货主商品编码下拉
	public MsgRes PickCellBarcodeInput(String strRecvData)throws Exception;
	
	// 获取货主商品编码下拉
	public MsgRes UpdatePickCell(String strRecvData)throws Exception;	
	
	//拣货位采集-获取商品包装，单位，规格，委托业主，商品名称 huangb 20160525
	public MsgRes getArticlePackingInfo(String strRecvData)throws Exception;
	    
	//拣货位采集-获取商品拣货位信息 huangb 20160525
	public MsgRes getArticleCellInfo(String strRecvData) throws Exception;
	    
	//拣货位采集-判断商品储位的合法性 huangb 20160526
	public MsgRes getCheckArticleCell(String strRecvData) throws Exception;
	    
	//拣货位采集-删除当前储位与原有商品的对应关系  huangb 20160526
	public MsgRes tscDeleteArticleCell(String strRecvData) throws Exception;
	    
	//拣货位采集-保存当前储位与商品的对应关系  huangb 20160526
	public MsgRes tscSaveArticleCell(String strRecvData) throws Exception;
}
