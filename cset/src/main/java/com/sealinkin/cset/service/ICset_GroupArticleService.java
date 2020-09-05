package com.sealinkin.cset.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_CellArticleModel;

public interface ICset_GroupArticleService {
	
	// 保存、修改商品储位对应关系	 
	public MsgRes saveCset_Group_Article(String workerNo, String str) throws Exception;
	
	// 获取商品储位对应关系列表
	public ExtListDataBo<Cset_CellArticleModel> getCset_Cell_ArticleList(
			String enterpriseNo,
			String warehouseNo,String ownerNo,
			String strQuery, PageBo pageBo, Integer requestFlag)throws Exception;
	
	// 校验新增商品储位对应关系是否重复
	public ExtListDataBo<Cset_CellArticleModel> existsCsetCellArticle(String enterpriseNo,String warehouseNo,String ownerNo,
			String strQuery,String strGroupNo)throws Exception;

	//校验对应的拣货类型下是否有储区 
	public ExtListDataBo<ComboxBo> existsAreaList(String enterpriseNo,String warehouseNo,
			String workerOwner, String queryStr)throws Exception;
	
/*	// 获取线路下拉
	public List<ComboxBo> queryLineCombo(String enterpriseNo,String strWarehouseNo,String strQuery)throws Exception;
	
	// 获取stock_x
	public List<String> queryStoctX(String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strCellNo)throws Exception;*/
	
	//获取类别编码下拉
	public List<ComboxBo> queryArticleGroupList(String enterpriseNo,String OwnerNo,String strWheresql)throws Exception;

}
