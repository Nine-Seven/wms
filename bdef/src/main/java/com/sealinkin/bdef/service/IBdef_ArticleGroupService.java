package com.sealinkin.bdef.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleGroupModel;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.ExtTreeLeafBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_ArticleGroupService {
	
	/*
	 * @func 保存商品类别信息
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public MsgRes saveGroup(String str)throws Exception;
	
	/*
	 * @func 删除商品类别信息
	 */
	public MsgRes deleteGroupNo(String strGroupNo,
			String strGroupLevel,String strOwnerNo,String strEnterpriseNo);
	
	/*
	 * @func 获得商品类别树信息
	 * @param node 节点编号
	 * @return void
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ExtTreeLeafBo> getBdef_ArticleModuleTree(String enterpriseNo,String str, String ownerNo)throws Exception;
	
	/*
	 * @func 获得相应类别下的商品明细
	 * @param start 起始数，limit 每页最多显示数目
	 * @return list  查询获得的商品
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public ExtListDataBo<Bdef_ArticleGroupModel> getGroupList(String enterpriseNo,String strWheresql,String strQuery, PageBo pageBo)throws Exception;
	
	/*
	 * @func 获得大类商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ComboxBo> getLGroupComboList(String enterpriseNo,String strOwnerNo, int start,int pagesize)throws Exception;
	
	/*
	 * @func 根据输入的值获得中类商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的中类商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ComboxBo> getMGroupComboList(String enterpriseNo,String wheresql,String strOwnerNo,int start,int pagesize)throws Exception;

	/*
	 * @func 根据输入的值获得商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public List<ComboxBo> getGroupNoList(String enterpriseNo,String wheresql, String workerOwner,
			int start, int pagesize)throws Exception;
	
	/*
	 * @func 查询数据库中是否已存在新增的类别
	 * @param 0,100
	 * @return 0：不存在，1：存在
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public String checkGroupNo(String enterpriseNo,String ownerNo,String groupNo)throws Exception;

	/*
	 * @func 获取货主下拉列表
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	//public  List<ComboxBo> getOwnerComboList(String enterpriseNo,String workerOwner,String strOwnerNo) throws Exception;

	/*
	 * @func 从Wms_deffieldval表中获取货主下拉列表
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<ComboxBo> getCombo(String[] split, int i, int j);

	/*
	 * @func 根据货主编号获得对于的策略
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<Bdef_DefOwnerModel> getStrategyByOwnerNo(String enterpriseNo,String strOwnerNo);

	/*
	 * @func 根据类别编码获得级别、大、中类编码
	 * @return list 
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<Bdef_ArticleGroupModel> getParentNo(String enterpriseNo,String strGroupNo);
	
	/**
	 * 商品类别小类COMBO
	 * @param strOwnerNo
	 * @param strFlag
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> query_GroupCombo(
			String enterpriseNo,String strOwnerNo,String strFlag,String strWheresql)throws Exception;
	
	/**
	 * 获取商品类别控制信息和策略管理
	 * @param strGroupNo
	 * @return
	 * @throws Exception
	 */
	public List<Bdef_ArticleGroupModel> query_ControlAndTactics(
			String enterpriseNo,String strOwnerNo,String strGroupNo)throws Exception;
	
	/**
	 * 填充策略下拉
	 * @param strWheresql
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> query_WmsDefStrategyCombo(String strWheresql)throws Exception;


	public List<ComboxBo> getGroupNoComboList(String enterpriseNo,String wheresql, String strOwnerNo, int i, int j);

	/*
	 * @func 获取批次下拉列表
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public List<ComboxBo> getBatchIdCombo(int i, int j);

	//获取大类
	public Bdef_ArticleGroupModel getLOrMgroup(String enterpriseNo,String strGroupNo,
			String strOwnerNo, String strGroupLevel);

	//判断商品编码是否唯一
	public List<String> groupNo1301Check(String enterpriseNo,String strOwnerNo,
			String strGroupLevel, String strGroupNo)throws Exception;
	
	//根据编码模糊查询类别
	/*
	 * @author czh
	 * @builddate 2016-7-9
	 */
	public List<ComboxBo> getGroupInfo(String strEnterpriseNo,String strOwnerNo,String strFlag,String str,String strQuery)throws Exception;
}