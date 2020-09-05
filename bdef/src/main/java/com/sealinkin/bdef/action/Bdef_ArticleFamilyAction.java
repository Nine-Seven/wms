package com.sealinkin.bdef.action;

import java.io.File;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleFamilyDModel;
import com.sealinkin.bdef.model.Bdef_ArticleFamilyMModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.service.IBdef_ArticleFamilyService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.ExceptionUtil;

public class Bdef_ArticleFamilyAction extends CommAction{

	private static final long serialVersionUID = 1L;
	private IBdef_ArticleFamilyService bdef_ArticleFamilyImpl;
	private String str;
	private String strQuery;
	private String wheresql;
	private String ownerNo;
	private String familyNo;
	private String articleNo;
	private File file;

	public void getArticleFamily_MList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_ArticleFamilyMModel> list=bdef_ArticleFamilyImpl.getArticleFamily_MList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					strQuery, pageBo);
				super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取商品列（除开群组已有的商品）
	 */
	public void getBdefDefarticleList()
	{
		try 
		{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefarticleModel> pageListBo = bdef_ArticleFamilyImpl.getBdefDefarticleList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					strQuery,str, wheresql,ownerNo,poPagebo);
			super.writeObj(pageListBo);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	

	
	public void getArticleFamily_DList(){
		try {
			PageBo pageBo=new PageBo(getStart(),getLimit());
			ExtListDataBo<Bdef_ArticleFamilyDModel> list=bdef_ArticleFamilyImpl.getArticleFamily_DList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					wheresql,str, pageBo);
			super.writeStr(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取商品类别
	public void getArticleGroupNoCombo(){
		try {
			List<ComboxBo> list = bdef_ArticleFamilyImpl.getArticleGroupNoCombo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),ownerNo);		
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取商品编码
	public void getArticleNoCombo(){
		try {
			List<ComboxBo> list = bdef_ArticleFamilyImpl.getArticleNoForUI(super.getMdBdef_DefWorker().getCurrEnterpriseNo(), ownerNo, str, strQuery);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //保存商品群组
	public void saveOrUpdateFamily(){
		try {
			bdef_ArticleFamilyImpl.saveOrUpdateFamily(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}

	//添加群组关系列表
	public void saveArticle_Family(){
		try {
			bdef_ArticleFamilyImpl.saveArticle_Family(str);
			super.writeObj(new MsgRes(true, "保存成功", ""));
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(true, "保存失败", ""));
		}
	}
	
	public void deleteArticle_Family(){
		try {
			bdef_ArticleFamilyImpl.deleteArticle_Family(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					ownerNo, familyNo, articleNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void checkArticleFamilyNo(){
		try {
			String no=bdef_ArticleFamilyImpl.checkArticleFamilyNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					ownerNo, familyNo);
			super.writeStr(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导入
	public void upLoad(){
		try {
			MsgRes msg = bdef_ArticleFamilyImpl.upLoad(file,
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
		
	}
	
	public IBdef_ArticleFamilyService getbdef_ArticleFamilyImpl() {
		return bdef_ArticleFamilyImpl;
	}
	public void setbdef_ArticleFamilyImpl(
			IBdef_ArticleFamilyService bdef_ArticleFamilyImpl) {
		this.bdef_ArticleFamilyImpl = bdef_ArticleFamilyImpl;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public IBdef_ArticleFamilyService getBdef_ArticleFamilyImpl() {
		return bdef_ArticleFamilyImpl;
	}
	public void setBdef_ArticleFamilyImpl(
			IBdef_ArticleFamilyService bdef_ArticleFamilyImpl) {
		this.bdef_ArticleFamilyImpl = bdef_ArticleFamilyImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getWheresql() {
		return wheresql;
	}
	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getFamilyNo() {
		return familyNo;
	}
	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}
