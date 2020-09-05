package com.sealinkin.cset.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.po.Cset_CellArticle;
import com.sealinkin.cset.service.ICset_GroupArticleService;

public class Cset_GroupArticleAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private ICset_GroupArticleService cset_GroupArticleImpl;
	private String str;
	private MsgRes msgRes;
	private Cset_CellArticle cset_Cell_Article;
	private String strEnterpriseNo;
	private String strWarehouseNo;
	private String strOwnerNo;
	private String strArticleNo;
	private String strPickType;
	private String wheresql;
	private String strWheresql;
	private String strQuery;
	private Integer requestFlag = 1;//1：查询2：导出
	private String strGroupNo;
	/*
	 * 保存、修改商品储位对应关系
	 */
	public void saveOrUpdateCset_Group_Article(){
		try{					
			cset_GroupArticleImpl.saveCset_Group_Article(super.getMdBdef_DefWorker().getWorkerNo(),getStr());
			super.writeObj(new MsgRes(true, "保存成功", ""));
		}catch (Exception  e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, e.getMessage(), ""));
		}
	}
	
	/*
	 * 获取商品储位对应关系列表
	 */
	public void getCset_Cell_ArticleList(){
		try{
			PageBo pageBo=new PageBo(getStart(),getLimit());
			 ExtListDataBo<Cset_CellArticleModel> list =cset_GroupArticleImpl.getCset_Cell_ArticleList(
					         super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							 super.getMdBdef_DefWorker().getWarehouseNo(),
						     super.getMdBdef_DefWorker().getWorkerOwner(),
						     strQuery, 
						     pageBo,
						     requestFlag);
			 if(requestFlag==1){
					super.writeStr(covtObjectToJson(list));
			 }else if(requestFlag==2){
				
				}
		}catch(Exception e){
            e.printStackTrace();
		}
	}
	
	/**
	 * 校验新增商品类别储位对应关系是否重复
	 */
	public void existsCsetCellArticle(){
		try{		
			ExtListDataBo<Cset_CellArticleModel> list=cset_GroupArticleImpl.existsCsetCellArticle(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery,strGroupNo);
			if(list.getRootList().size()>0){
				super.writeObj(new MsgRes(false, "该类别储位对应关系已存在，请重新输入！", ""));
			}else{
				super.writeObj(new MsgRes(true, "", ""));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验对应的拣货类型下是否有储区
	 */
	public void existsAreaList(){
		try{		
			ExtListDataBo<ComboxBo> list=cset_GroupArticleImpl.existsAreaList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					strQuery);
			if(list.getRootList().size()<=0){
				super.writeObj(new MsgRes(false, "在对应的拣货类型下没有储区，请重新选择！", ""));
			}else{
				super.writeObj(new MsgRes(true, "", ""));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取商品类别信息下拉
	 */
	public void queryArticleGroupList(){
		try {
			List<ComboxBo> list = cset_GroupArticleImpl.queryArticleGroupList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strOwnerNo, strWheresql);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public Cset_CellArticle getCset_Cell_Article() {
		return cset_Cell_Article;
	}

	public void setCset_Cell_Article(Cset_CellArticle cset_Cell_Article) {
		this.cset_Cell_Article = cset_Cell_Article;
	}

	public String getStrOwnerNo() {
		return strOwnerNo;
	}

	public void setStrOwnerNo(String strOwnerNo) {
		this.strOwnerNo = strOwnerNo;
	}

	public String getWheresql() {
		return wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public Integer getRequestFlag() {
		return requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	
	public void setCset_GroupArticleImpl(
			ICset_GroupArticleService cset_GroupArticleImpl) {
		this.cset_GroupArticleImpl = cset_GroupArticleImpl;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrWarehouseNo() {
		return strWarehouseNo;
	}

	public void setStrWarehouseNo(String strWarehouseNo) {
		this.strWarehouseNo = strWarehouseNo;
	}

	public String getStrArticleNo() {
		return strArticleNo;
	}

	public void setStrArticleNo(String strArticleNo) {
		this.strArticleNo = strArticleNo;
	}

	public String getStrPickType() {
		return strPickType;
	}

	public void setStrPickType(String strPickType) {
		this.strPickType = strPickType;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getStrEnterpriseNo() {
		return strEnterpriseNo;
	}

	public void setStrEnterpriseNo(String strEnterpriseNo) {
		this.strEnterpriseNo = strEnterpriseNo;
	}

	public String getStrGroupNo() {
		return strGroupNo;
	}

	public void setStrGroupNo(String strGroupNo) {
		this.strGroupNo = strGroupNo;
	}
	
}
