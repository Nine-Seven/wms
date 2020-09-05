package com.sealinkin.bdef.action;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleGroupModel;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.service.IBdef_ArticleGroupService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.ExtTreeLeafBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

/**
 * @商品类别维护Action
 * @author 周欢
 *
 */
public class Bdef_ArticleGroupAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBdef_ArticleGroupService bdef_ArticleGroupImpl;
	private String str;
	private String node;
	private String wheresql;
	private String queryStr;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strGroupNo;
	private String strOwnerNo;
	private String strGroupLevel;
	private String strWheresql;
	private String strFlag;
	private String strQuery;
	
	public IBdef_ArticleGroupService getBdef_ArticleGroupImpl() {
		return bdef_ArticleGroupImpl;
	}
	public void setBdef_ArticleGroupImpl(
			IBdef_ArticleGroupService bdef_ArticleGroupImpl) {
		this.bdef_ArticleGroupImpl = bdef_ArticleGroupImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}


	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Integer getRequestFlag() {
		return requestFlag;
	}
	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getStrGroupNo() {
		return strGroupNo;
	}
	public void setStrGroupNo(String strGroupNo) {
		this.strGroupNo = strGroupNo;
	}
	public String getStrOwnerNo() {
		return strOwnerNo;
	}
	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}
	
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	
	public String getStrGroupLevel() {
		return strGroupLevel;
	}
	public void setStrGroupLevel(String strGroupLevel) {
		this.strGroupLevel = strGroupLevel;
	}
	/*
	 * @func 保存商品类别信息
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void saveGroup()
	{
		try 
		{
			MsgRes msg=bdef_ArticleGroupImpl.saveGroup(str);
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));//数据保存失败！
		}
	}
	
	/*
	 * @func 删除商品类别信息
	 */
	public void deleteGroupNo()
	{
		try 
		{
			MsgRes msg=bdef_ArticleGroupImpl.deleteGroupNo(strGroupNo,
			strGroupLevel,strOwnerNo,
			super.getMdBdef_DefWorker().getCurrEnterpriseNo()
		    );
			super.writeObj(msg);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/*
	 * @func 获得商品类别树信息
	 * @param node 节点编号
	 * @return void
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void getBdef_ArticleModuleTree()
	{
		try 
		{
			List<ExtTreeLeafBo> list =bdef_ArticleGroupImpl.getBdef_ArticleModuleTree(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					node,strOwnerNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 获得相应类别下的商品明细
	 * @param start 起始数，limit 每页最多显示数目
	 * @return list  查询获得的商品
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void getGroupList()
	{
		try 
		{
			PageBo pageBo = new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_ArticleGroupModel> list = 
					bdef_ArticleGroupImpl.getGroupList(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							strWheresql,strQuery, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 获得大类商品类别下拉列表
	 * @param 0,100
	 * @return list  查询获得的商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void getLGroupComboList()
	{
		try 
		{
			List<ComboxBo> list=bdef_ArticleGroupImpl.getLGroupComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strOwnerNo,0, 100);
			writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 根据输入的值获得中类商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的中类商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void getMGroupComboList()
	{
		try 
		{
			List<ComboxBo> list=bdef_ArticleGroupImpl.getMGroupComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery,strOwnerNo, 0, 100);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 根据输入的值获得商品类别下拉
	 * @param 0,100
	 * @return list  查询获得的商品类别下拉
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void getGroupNoList()
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_ArticleGroupImpl.getGroupNoList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql,strOwnerNo,0,10);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 查询数据库中是否已存在新增的类别
	 * @param 0,100
	 * @return 0：不存在，1：存在
	 * @author 周欢 
	 * @builddate 2014-4-2
	 */
	public void checkGroupNo()
	{
		try 
		{
			String no=bdef_ArticleGroupImpl.checkGroupNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strOwnerNo,strGroupNo);
			super.writeStr(no);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 获取货主下拉列表
	 * @return list 货主列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
//	public void getOwnerComboList()
//	{
//		try {
//			List<ComboxBo> list=bdef_ArticleGroupImpl.getOwnerComboList(
//					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
//					super.getMdBdef_DefWorker().getWorkerOwner(), strOwnerNo);
//			writeArray(list);
//		} catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//	}
	
	
	/*
	 * @func 从Wms_deffieldval表中获取货主下拉列表
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public void getWmsDeffieldvalCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_ArticleGroupImpl.getCombo(getStr().split(","),0, 100);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 根据货主编号获得对于的策略
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public void getStrategyByOwnerNo() 
	{
		try{
			List<Bdef_DefOwnerModel> list=bdef_ArticleGroupImpl.getStrategyByOwnerNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strOwnerNo);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 根据类别编码获得级别、大、中类编码
	 * @return list 
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public void getParentNo() 
	{
		try{
			List<Bdef_ArticleGroupModel> list=bdef_ArticleGroupImpl.getParentNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),strGroupNo);
			super.writeArray(list);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取商品类别小类
	 */
	public void queryGroupCombo()
	{
		try 
		{
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = bdef_ArticleGroupImpl.query_GroupCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strOwnerNo,"", strWheresql);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取商品类别控制信息和策略管理
	 */
	public void queryControlAndTactics()
	{
		try 
		{
			List<Bdef_ArticleGroupModel> list = bdef_ArticleGroupImpl.query_ControlAndTactics(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),strGroupNo);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取策略下拉
	 */
	public void queryWmsDefStrategyCombo()
	{
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_ArticleGroupImpl.query_WmsDefStrategyCombo(str);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得商品类别下拉
	 * zhouhuan
	 */
	public void getGroupNoComboList()throws Exception{
		try {
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_ArticleGroupImpl.getGroupNoComboList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strWheresql,strOwnerNo,0,10);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @func 获取批次下拉列表
	 * @return list 下拉列表
	 * @author 周欢 
	 * @builddate 2014-4-4
	 */
	public void getBatchIdCombo() 
	{
		try{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_ArticleGroupImpl.getBatchIdCombo(0, 100);
			super.writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//获取大类信息
	public void getLOrMgroup(){
		
		try{
			Bdef_ArticleGroupModel articleGroup=bdef_ArticleGroupImpl.getLOrMgroup(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStrGroupNo(),this.getStrOwnerNo(),this.getStrGroupLevel());
			super.writeObj(articleGroup);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//判断商品编码是否唯一
	public void groupNo1301Check(){
		try 
		{
			List<String> list = bdef_ArticleGroupImpl.groupNo1301Check(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getStrOwnerNo(),this.getStrGroupLevel(),this.getStrGroupNo());	
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getGroupInfo(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list=bdef_ArticleGroupImpl.getGroupInfo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strFlag,			
					str,strQuery);
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
}



