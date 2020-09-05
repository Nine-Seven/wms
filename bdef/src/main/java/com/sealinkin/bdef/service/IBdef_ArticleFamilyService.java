package com.sealinkin.bdef.service;

import java.io.File;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticleFamilyDModel;
import com.sealinkin.bdef.model.Bdef_ArticleFamilyMModel;
import com.sealinkin.bdef.model.Bdef_DefarticleModel;
import com.sealinkin.bdef.po.Bdef_ArticleFamilyTmp;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface IBdef_ArticleFamilyService {
	public ExtListDataBo<Bdef_ArticleFamilyMModel> getArticleFamily_MList(
			String enterpriseNo,String queryStr,
			PageBo pageBo)throws Exception;
	
	public ExtListDataBo<Bdef_DefarticleModel> getBdefDefarticleList(
			String enterpriseNo,String strOwnerNo,
			String strQuery,String str,String wheresql,String ownerNo, PageBo poPageBo)throws Exception;
	
	public boolean saveOrUpdateFamily(String str)throws Exception;
	
	public boolean saveArticle_Family(String str)throws Exception;
	
	public ExtListDataBo<Bdef_ArticleFamilyDModel> getArticleFamily_DList(
			String enterpriseNo,String wheresql,String str, PageBo pageBo)throws Exception;
	

	public boolean deleteArticle_Family(
			String enterpriseNo,String ownerNo,String familyNo,String articleNo)throws Exception;
	
	public String checkArticleFamilyNo(String enterpriseNo,String ownerNo,String familyNo)throws Exception;
	/*
	 * @func 获取groupNo下拉列表
	 * @return groupNo列表
	 */
	public List<ComboxBo> getArticleGroupNoCombo(String enterpriseNo,String ownerNo)throws Exception;
	
	
	public List<ComboxBo> getArticleNoForUI(String enterpriseNo,String ownerNo,
			String str,String strQuery)throws Exception;
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public MsgRes upLoad(File file,String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo)throws Exception;
	
	/**
	 * Excel数据转List
	 * @return
	 * @throws Exception
	 */
	public List<Bdef_ArticleFamilyTmp> changeexcelBean(String fileName,String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo)throws Exception;
	
	/**
	 * 解析Excel
	 * @param execelFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;

}

