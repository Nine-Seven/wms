package com.sealinkin.cset.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.service.ICset_GroupArticleService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cset_GroupArticleImpl implements ICset_GroupArticleService{
	
	private IGenericManager genDao;
	
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}	

	/*
	 * 保存、修改商品类别储位对应关系
	 */
	public MsgRes saveCset_Group_Article(String workerNo, String str)
			throws Exception {
		Cset_CellArticleModel cca=(Cset_CellArticleModel)JSON.parseObject(str,Cset_CellArticleModel.class);
		
		if(cca.getArticleNo()==null || cca.getArticleNo().equals("")){
			cca.setArticleNo("N");
		}
		if(cca.getPackingQty()==null || cca.getPackingQty().equals("")){
			cca.setPackingQty(0.0);
		}
		if(cca.getCellNo()==null || cca.getCellNo().equals("")){
			cca.setCellNo("N");
		}
		    List resultList=new ArrayList();
		    List outList=new ArrayList();
		    List inList=new ArrayList();
		
		    outList.add("S");

		    inList.add(cca.getEnterpriseNo());
			inList.add(cca.getWarehouseNo());
			inList.add(cca.getOwnerNo());
			inList.add(cca.getWareNo());
			inList.add(cca.getAreaNo());
			inList.add(cca.getStockNo());
			inList.add(cca.getAStockNo());
			inList.add(cca.getCellNo());
			inList.add(cca.getGroupNo());
			inList.add(cca.getArticleNo());
			inList.add(cca.getPackingQty());
			inList.add(cca.getLineId());
			inList.add(cca.getMaxQtyA());
			inList.add(cca.getAlertQtyA());
			inList.add(cca.getSuppQtyA());
			inList.add(cca.getMaxQtyNa());
			inList.add(cca.getAlertQtyNa());
			inList.add(cca.getSuppQtyNa());
			inList.add(cca.getKeepCells());
			inList.add(cca.getPickType());
			inList.add(cca.getKeepCellsA());
			inList.add(cca.getRgstName());
		    
			resultList=genDao.execProc(inList, outList, "PKOBJ_CDEF.saveOrUpdateCset_Cell_Article");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		
		return new MsgRes(true,TipUtil.getTipsByKey("E30404"),"");//保存成功！
	}

    /*
     * 获得已有商品储位对应关系信息列表
     * zhouhuan
     */
	@Override
	public ExtListDataBo<Cset_CellArticleModel> getCset_Cell_ArticleList(
			String enterpriseNo,String warehouseNo, String ownerNo, 
			String strQuery, PageBo pageBo,Integer requestFlag) throws Exception {
		String sql=" select"
			+" C.*,bar.barcode,bar.primary_flag,cabm.line_name,"
			+" a.owner_article_no,a.article_name, a.GROUP_NO,a.GROUP_NAME,"
			+" d.owner_name,CDW.WARE_NAME,CDA.AREA_NAME, " 
			+" ceil(C.MAX_QTY_A/C.PACKING_QTY) as maxQtyBox," 
			+" ceil(C.Alert_Qty_a/C.Packing_Qty) as alertQtyBox," 
			+" ceil(C.Supp_Qty_a/C.Packing_Qty) as suppQtyBox "
			+" FROM"
			+" Cset_Cell_Article C"
			+" LEFT JOIN"
			+" bdef_defarticle A "
			+" ON C.OWNER_NO = A.OWNER_NO"
			+" AND C.ARTICLE_NO = A.ARTICLE_NO"
			+" AND C.ENTERPRISE_NO = A.ENTERPRISE_NO"
			+" LEFT JOIN"
			+" BDEF_ARTICLE_BARCODE BAR "
			+" ON C.OWNER_NO = BAR.OWNER_NO"
			+" AND C.ARTICLE_NO = BAR.ARTICLE_NO"
			+" AND C.ENTERPRISE_NO = BAR.ENTERPRISE_NO"
			+" AND BAR.PRIMARY_FLAG = '1'"
			+" INNER JOIN"
	        +" bdef_defowner d "
	        +" ON C.OWNER_NO = D.OWNER_NO "
	        +" AND C.ENTERPRISE_NO = D.ENTERPRISE_NO"
			+" INNER JOIN"
			+" BDEF_DEFOWNER O "
			+" ON C.OWNER_NO = O.OWNER_NO "
			+" AND C.ENTERPRISE_NO = O.ENTERPRISE_NO"
			+" INNER JOIN"
			+" CDEF_DEFWARE CDW "
			+" ON C.WAREHOUSE_NO = CDW.WAREHOUSE_NO "
			+" and C.ENTERPRISE_NO = CDW.ENTERPRISE_NO"
			+" and C.WARE_NO = CDW.WARE_NO "
			+" INNER JOIN"
			+" CDEF_DEFAREA CDA "
			+" ON C.WAREHOUSE_NO = CDA.WAREHOUSE_NO "
			+" and C.WARE_NO = CDA.WARE_NO "
			+" and C.AREA_NO = CDA.AREA_NO "
			+" and C.ENTERPRISE_NO = CDA.ENTERPRISE_NO"
			+" INNER JOIN"
			+" cset_area_backup_m cabm "
			+" ON C.WAREHOUSE_NO = cabm.WAREHOUSE_NO "
			+" and C.enterprise_no = cabm.enterprise_no"
			+" and C.line_id = cabm.line_id "
			+" where"
			+" c.warehouse_no = '"+warehouseNo+"'"
			+" and c.enterprise_no='"+enterpriseNo+"' "
			+" and c.owner_no in ("+ownerNo+" )";
			
			String totallSql="select count(1) "
				+" FROM Cset_Cell_Article C"
				+" LEFT JOIN bdef_defarticle A "
				+" ON C.OWNER_NO = A.OWNER_NO"
				+" AND C.ARTICLE_NO = A.ARTICLE_NO"
				+" AND C.ENTERPRISE_NO = A.ENTERPRISE_NO"
				+" LEFT JOIN BDEF_ARTICLE_BARCODE BAR "
				+" ON C.OWNER_NO = BAR.OWNER_NO"
				+" AND C.ARTICLE_NO = BAR.ARTICLE_NO"
				+" AND C.ENTERPRISE_NO = BAR.ENTERPRISE_NO"
				+" AND BAR.PRIMARY_FLAG = '1'"
				+" INNER JOIN BDEF_DEFOWNER O "
				+" ON C.OWNER_NO = O.OWNER_NO"
				+" AND C.ENTERPRISE_NO = O.ENTERPRISE_NO"
				+" INNER JOIN"
				+" CDEF_DEFWARE CDW "
				+" ON C.WAREHOUSE_NO = CDW.WAREHOUSE_NO "
				+" and C.ENTERPRISE_NO = CDW.ENTERPRISE_NO"
				+" and C.WARE_NO = CDW.WARE_NO "
				+" INNER JOIN"
				+" CDEF_DEFAREA CDA "
				+" ON C.WAREHOUSE_NO = CDA.WAREHOUSE_NO "
				+" and C.WARE_NO = CDA.WARE_NO "
				+" and C.AREA_NO = CDA.AREA_NO "
				+" and C.ENTERPRISE_NO = CDA.ENTERPRISE_NO"
				+" where c.warehouse_no = '"+warehouseNo+"'"
				+" and c.enterprise_no='"+enterpriseNo+"' "
				+" and c.owner_no in ("+ownerNo+")";
			  if(strQuery!=null && !strQuery.equals(""))
				{
					String ws=CommUtil.covtCollectionToWhereSql(strQuery);
					sql=sql+ws;
					totallSql=totallSql+ws;
				}
			sql+=" order by c.warehouse_no, c.owner_no ";
			List<Cset_CellArticleModel> list =null; 
			Integer count = 0; 
			ExtListDataBo<Cset_CellArticleModel> extListBo=null;
			if(requestFlag==1){
				list = genDao.getListByNativeSql(sql, Cset_CellArticleModel.class,pageBo.getStart(), pageBo.getPagesize());
				count = genDao.getCountByNativeSql(totallSql);
			}else if(requestFlag==2){
				list = genDao.getListByNativeSql(sql,Cset_CellArticleModel.class);
				count = list.size();
			}
			extListBo= new ExtListDataBo<Cset_CellArticleModel>(list, count);
	        return extListBo;
	}


	/**
	 * 校验新增商品储位对应关系是否重复
	 */
	public ExtListDataBo<Cset_CellArticleModel> existsCsetCellArticle(
			String enterpriseNo,
			String warehouseNo,String ownerNo,
			String strQuery,String strGroupNo) throws Exception{
		String sql="select a.* from Cset_Cell_Article a where 1=1 " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.owner_no in("+ownerNo+")  " +
				"and a.article_no in (select b.ARTICLE_NO " +
				"from bdef_defarticle b where b.group_no='"+strGroupNo+"' " +
				"and a.enterprise_no=b.enterprise_no )";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		List<Cset_CellArticleModel> list = genDao.getListByNativeSql(sql, Cset_CellArticleModel.class,0, 1000);
		ExtListDataBo<Cset_CellArticleModel> extListBo= new ExtListDataBo<Cset_CellArticleModel>(list, 0);
        return extListBo;
	}

	/**
	 * 校验对应的拣货类型下是否有储区
	 */
	public ExtListDataBo<ComboxBo> existsAreaList(
			String enterpriseNo,
			String warehouseNo,
			String workerOwner, String queryStr)throws Exception {
		String sql="select a.ware_no value,b.ware_Name text,'['|| ltrim(a.ware_no)||']'||b.ware_Name dropValue  " +
			" from cdef_defarea a " +
			" inner join cdef_defware b " +
			" on a.warehouse_no=b.warehouse_no " +
			" and a.enterprise_no = b.enterprise_no " +
			" and a.ware_no=b.ware_no" +
			" and a.enterprise_no='"+enterpriseNo+"' "+
			" and a.warehouse_No='"+warehouseNo+"' " +
			" and a.AREA_USETYPE='1' " +
			" and a.AREA_ATTRIBUTE='0' " +
			" and a.AREA_PICK='1'";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		List<ComboxBo> list = genDao.getListByNativeSql(sql, ComboxBo.class,0, 1000);
		ExtListDataBo<ComboxBo> extListBo= new ExtListDataBo<ComboxBo>(list, 0);
        return extListBo;
	}
	
	
	/**
	 * 获取商品类别下拉
	 */
	@Override
	public List<ComboxBo> queryArticleGroupList(
			String enterpriseNo,
			String strOwnerNo,
			String strWheresql) throws Exception {
		String strSql="select distinct t1.group_no value, t1.group_name text, " +
				"'['|| ltrim(t1.group_no)||']'||t1.group_name dropValue " +
				"from bdef_article_group t1 " +
				"where t1.group_level=0 ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and ( t1.group_no like '%"+strWheresql+"%' or t1.group_name like '%"+strWheresql+"%' )";
		}
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no = '"+strOwnerNo+"'";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and t1.enterprise_no = '"+enterpriseNo+"'";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	

}
