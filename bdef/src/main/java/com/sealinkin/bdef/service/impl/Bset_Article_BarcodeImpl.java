package com.sealinkin.bdef.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.bdef.service.IBset_Article_BarcodeService;
import com.sealinkin.bset.model.Bset_ArticleBarcodeDModel;
import com.sealinkin.bset.model.Bset_ArticleBarcodeMModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Bset_Article_BarcodeImpl implements IBset_Article_BarcodeService {
	
    
	private IGenericManager genDao;
     
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }

   
	/**
	 * 获取条码采集头档
	 */
	
	public ExtListDataBo<Bset_ArticleBarcodeDModel> getBset_Article_Barcode_DList(String enterpriseNo,String wheresql,
			PageBo pageBo)throws Exception {
		String sql="select  distinct a.article_no,b.article_name from bset_Article_Barcode_d a,bdef_defarticle b " +
				" where a.article_no=b.article_no " +
				" and a.serial_no="+wheresql+
				" and a.enterprise_no=b.enterprise_no " +
				" and a.enterprise_no='"+enterpriseNo+"' ";

		String strTotsql = "select count(*) from bset_Article_Barcode_d a,bdef_defarticle b " +
				"where a.article_no=b.article_no" +
				" and a.serial_no="+wheresql+
				" and a.enterprise_no=b.enterprise_no " +
				" and a.enterprise_no='"+enterpriseNo+"' ";
		
		List<Bset_ArticleBarcodeDModel> list = genDao.getListByNativeSql(sql,Bset_ArticleBarcodeDModel.class,pageBo.getStart(), 1000);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bset_ArticleBarcodeDModel> extListBo= new ExtListDataBo<Bset_ArticleBarcodeDModel>(list, intCount);
		return extListBo;
		
	}
	
	/**
	 * 保存
	 */
	
	@Override
	public MsgRes save(String jsonMaster, String jsonDetail) throws Exception {
		Bset_ArticleBarcodeMModel articleBarcodeM=(Bset_ArticleBarcodeMModel)JSON.parseObject(jsonMaster,Bset_ArticleBarcodeMModel.class);
		List<Bset_ArticleBarcodeDModel> articleBarcodeD=JSON.parseArray(jsonDetail,Bset_ArticleBarcodeDModel.class);
		
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		if(articleBarcodeM.getSerialNo()==1){
			for(int i=0; i<articleBarcodeD.size();i++){
				List inList=new ArrayList();
				inList.add(articleBarcodeD.get(i).getEnterpriseNo());
				inList.add(articleBarcodeD.get(i).getWarehouseNo());
				inList.add(articleBarcodeM.getOwnerNo());
				inList.add(articleBarcodeM.getPaperNo());
				inList.add(articleBarcodeD.get(i).getArticleNo());				
				inList.add(i==0?1:0);
				inList.add(articleBarcodeM.getRgstName());
				inList.add(i==0?-1:Integer.parseInt(resultList.get(0).toString()));
				System.out.println(inList);
				resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.P_Insert_article_barcode");
				if(resultList.get(1).toString().indexOf("N|")!=-1){
					throw new Exception(resultList.get(1).toString());
				}
			}		
		}
		return new MsgRes(true, "保存成功！", resultList.get(0).toString());
	}
	
	@Override
	public ExtListDataBo<Bset_ArticleBarcodeMModel> getBset_Article_Barcode_MList(String enterpriseNo,String str,
			PageBo pageBo,String warehouseNo) throws Exception {
		
		String sql="select a.owner_no,a.serial_no,a.paper_no,a.status," +
				"f_get_fieldtext('N','STATUS',a.status) statusText," +
				"b.owner_name " +
				"from BSET_ARTICLE_BARCODE_M a,bdef_defOwner b " +
				"where a.owner_no=b.owner_no "+
				"and a.enterprise_no=b.enterprise_no "+
				"and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
				 
		String strTotsql = "select count(1) from BSET_ARTICLE_BARCODE_M a where 1=1 "+
				"and a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}					
		List<Bset_ArticleBarcodeMModel> list = genDao.getListByNativeSql(sql,Bset_ArticleBarcodeMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bset_ArticleBarcodeMModel> extListBo= new ExtListDataBo<Bset_ArticleBarcodeMModel>(list, intCount);
		return extListBo;
	}
	@Override
	public List<ComboxBo> getArticle(String enterpriseNo,
			String strOwnerNo, String strQuery, String strWheresql)
			throws Exception {
		String strSql="select distinct t1.article_no value,t1.article_name text," +
				"'['|| ltrim(t1.owner_article_no)||']'||t1.article_name dropValue " +
				"from bdef_defarticle t1,bdef_article_barcode t2 " +
				"where t1.article_no=t2.article_no(+) " +
			//	"and t2.primary_flag=1 " +
				"and t1.enterprise_no=t2.enterprise_no(+) " +
				"and t1.enterprise_no='"+enterpriseNo+"' ";
		//商品编码、条码、名称、货主商品编码
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and (t1.article_no like '%"+strWheresql+"%' " +
					"or t1.article_name like '%"+strWheresql+"%' " +
							"or t2.barcode like '%"+strWheresql+"%' " +
							"or t1.owner_article_no like '%"+strWheresql+"%')";
		}
		//货主权限
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		//货主过滤等条件
		if(strQuery!=null && !strQuery.equals(""))
		{
			String strW=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql+=strW;
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
}
