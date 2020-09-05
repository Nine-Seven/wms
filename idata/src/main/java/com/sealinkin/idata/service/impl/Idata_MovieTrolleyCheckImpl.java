package com.sealinkin.idata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_CheckDModel;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.service.Iidata_MovieTrolleyCheckService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"}) 
public class Idata_MovieTrolleyCheckImpl implements
Iidata_MovieTrolleyCheckService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 填充货主下拉
	 */
	@Override
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception {
		String strSql="select distinct t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 ,idata_import_m a " + 
				"where t1.enterprise_no=a.enterprise_no " +
				"and t1.owner_no=a.owner_no "+
				"and a.warehouse_no='"+warehouseNo+"' "+
				"and a.owner_no in ("+workerOwner+") "+
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.status not in ('13','16') ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	/**
	 * 填充供应商下拉
	 */
	@Override
	public List<ComboxBo> getSupplierNo(String enterpriseNo,
			String workerOwner, String warehouseNo, String str)
			throws Exception {
		String strSql="select distinct a.supplier_no value,bds.supplier_name text,"+
				"'['|| ltrim(a.supplier_no)||']'||bds.supplier_name dropValue "+
				" from idata_import_m a,idata_import_sm b,bdef_defsupplier bds "+
				" where a.enterprise_no=b.enterprise_no " +
				" and a.warehouse_no=b.warehouse_no " +
				" and a.owner_no=b.owner_no " +
				" and a.import_no=b.import_no " + 
				" and a.owner_no=bds.owner_no " +
				" and a.supplier_no=bds.supplier_no " +
				" and a.enterprise_no=bds.enterprise_no "+
				" and a.enterprise_no ='"+enterpriseNo+
				"' and a.warehouse_no ='"+warehouseNo+
				"' and a.STATUS NOT IN ('13', '16') ";

			if(workerOwner!=null && !workerOwner.equals(""))
			{
				strSql=strSql+" and a.owner_no in("+workerOwner+") ";
			}
			
			if (str != null && !str.equals("")) {
				String ws = CommUtil.covtCollectionToWhereSql(str);
				strSql = strSql + ws;
			}
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
	}
	/**
	 * 填充助记码
	 */
	@Override
	public List<ComboxBo> getArticleIdentifierList(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str) throws Exception {
		String sql = "select distinct  b.article_identifier value ,b.article_identifier text," +
				           "b.article_identifier dropValue " +
				 " from idata_import_d a,bdef_defarticle b "+
                 "where a.enterprise_no=b.enterprise_no "+
                   "and a.article_no=b.article_no "+
                   "and a.status not in('13','16') "
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.owner_no in(" + ownerNo + ") ";
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and b.article_identifier like '%" + strWheresql + "%' ";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	/**
	 * 填充采购单号
	 */
	@Override
	public List<ComboxBo> getPoNoList(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str) throws Exception {
		String sql = "select a.po_no value,a.po_no text,a.po_no dropValue  "
				+ "from idata_import_m a "
				+ " where a.status not in ('13','16') "
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.owner_no in(" + ownerNo + ") ";
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and a.po_no like '%" + strWheresql + "%' ";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	/**
	 * 填充助记码/条码
	 */
	@Override
	public List<ComboxBo> getIdentifierOrBarcode1List(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str) throws Exception {
		String sql = "select distinct c.barcode," +
				" '['|| ltrim(c.article_no)||']'||c.article_name articleText,c.rsv_attr2 "
				+ "from idata_import_m a,idata_import_d b,bdef_defarticle c "
				+ " where a.enterprise_no=b.enterprise_no " 
				+ " and a.warehouse_No=b.warehouse_No " 
				+ " and a.owner_no=b.owner_no " 
				+ " and a.import_no=b.import_no " 
				+ " and b.enterprise_no=c.enterprise_no " 
				+ " and b.owner_no=c.owner_no " 
				+ " and b.article_no=c.article_no " 
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.owner_no in(" + ownerNo + ") " +
				  " and a.status not in('13','16') ";
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and ( c.barcode like '%" + strWheresql + "%' " +
					"or c.article_identifier like '%" + strWheresql + "%') ";
		}
        sql=" select h.barcode value,h.barcode text,h.articleText dropValue "+
            "from( "+sql+ ") h order by h.rsv_attr2";
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取单据列表
	@Override
	public ExtListDataBo<Idata_ImportMModel> getPoNoAndSImportNoList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String strWheresql,String str, PageBo pageBo) throws Exception {
		String sql="select distinct a.po_no,d.s_import_no,a.import_no,a.owner_no,a.import_type "+
                "from idata_import_m a,idata_import_d b," +
                     "bdef_defarticle c ,idata_import_sm d "+
                "where a.enterprise_no=b.enterprise_no "+
                "and a.warehouse_no=b.warehouse_no "+
                "and a.owner_no=b.owner_no "+
                "and a.import_no=b.import_no "+
                "and b.enterprise_no=c.enterprise_no " +
                "and b.owner_no=c.owner_no " +
                "and b.article_no=c.article_no " +
                "and a.status not in ('13','16') " +
                "and a.enterprise_no=d.enterprise_no " +
                "and a.warehouse_no=d.warehouse_no " +
                "and a.import_no=d.import_no " +
				   "and a.warehouse_no='"+strWarehouseNo+"' " +
				   "and a.enterprise_no='"+strEnterpriseNo+"' " +
				   "and a.owner_no in("+strOwnerNo+") " ;		
     
        if(str!=null && !str.equals(""))
		    {
			    String ws=CommUtil.covtCollectionToWhereSql(str);
			    sql=sql+ws;
		    }
        if (strWheresql != null && !strWheresql.equals("")) {
			sql = sql + "and ( c.barcode like '%" + strWheresql + "%' " +
					"or c.article_identifier like '%" + strWheresql + "%') ";
        }
	    sql=sql+" order by a.po_no ";
        String totsql = "select count(*) from (" + sql + ") " ;	
		List<Idata_ImportMModel> list = genDao.getListByNativeSql(sql,Idata_ImportMModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_ImportMModel> extListBo= new ExtListDataBo<Idata_ImportMModel>(list, count);
		return extListBo;
	}
	//采购单明细
	@Override
	public ExtListDataBo<Idata_ImportDModel> getImportDList(
			String strEnterpriseNo,String strWarehouseNo,String strOwnerNo, 
			String str, PageBo pageBo)
			throws Exception {
		String sql="select b.article_no,c.owner_article_no,c.barcode,  "+
                   "c.article_name,c.article_identifier," +
                   //"nvl(pk.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit," +
                   "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
                   "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
                   "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
                   "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
                   "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
                   "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"+
                   "f_get_fieldtext('BDEF_DEFARTICLE','PRINT_FLAG',c.print_flag) printFlag, " +
                   "b.packing_qty," +
                   "sum(b.po_qty)/b.packing_qty as inQty,sum(b.import_qty)/b.packing_qty as checkQty,  "+
                   "sum(b.po_qty - b.import_qty - nvl(icpt.check_qty, 0))/b.packing_qty as noCheckQty,  "+
                   "nvl((select sum(iia.po_qty)/iia.packing_qty  "+
                       "from idata_import_allot iia  "+
                      "where iia.import_no = a.import_no  "+
                      "and iia.article_no = b.article_no and iia.packing_qty=b.packing_qty group by iia.packing_qty),0) as poQty,  "+
                   "'[' || ltrim(b.article_no) || ']' || c.article_name articleText  "+
                   "from idata_import_m a  "+
                   "inner join idata_import_d b  "+
                    "on a.enterprise_no = b.enterprise_no  "+
                  " and a.warehouse_no = b.warehouse_no  "+
                   "and a.owner_no = b.owner_no  "+
                   "and a.import_no = b.import_no  "+
                  "inner join bdef_defarticle c  "+
                    "on b.enterprise_no = c.enterprise_no  "+
                   "and b.owner_no = c.owner_no  "+
                   "and b.article_no = c.article_no  "+
                  "inner join idata_import_sm iis  "+
                    "on a.enterprise_no = iis.enterprise_no  "+
                   "and a.warehouse_no = iis.warehouse_no  "+
                   "and a.import_no = iis.import_no  "+
                   "and a.owner_no = iis.owner_no  "+
                  "left join idata_check_pal_tmp icpt  "+
                    "on a.enterprise_no = icpt.enterprise_no  "+
                   "and a.warehouse_no = icpt.warehouse_no  "+
                   "and iis.s_import_no = icpt.s_import_no  "+
                   "and b.article_no = icpt.article_no  "+
                   "and a.owner_no = icpt.owner_no " +
              "   left join bdef_article_packing pk on pk.enterprise_no=b.enterprise_no " +
                   "and pk.article_no=b.article_no and pk.packing_qty=b.packing_qty "+
                "where  a.status not in ('13','16') " +
				   "and a.warehouse_no='"+strWarehouseNo+"' " +
				   "and a.enterprise_no='"+strEnterpriseNo+"' " +
				   "and a.owner_no in("+strOwnerNo+") " ;		
        
        if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
        sql=sql+" group by b.enterprise_no,b.owner_no,c.unit_packing,a.import_no,a.po_no,b.article_no,b.packing_qty,pk.packing_unit, c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit,"+
                "c.owner_article_no,c.barcode,c.article_no,c.article_name,c.article_identifier,c.print_flag, c.rsv_attr2 ";
        sql =sql+" order by c.article_identifier,c.rsv_attr2 ";
        String totsql = "select count(*) from (" + sql + ") " ;	
		List<Idata_ImportDModel> list = genDao.getListByNativeSql(sql,Idata_ImportDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_ImportDModel> extListBo= new ExtListDataBo<Idata_ImportDModel>(list, count);
        return extListBo;
	}
	//获取验收板明细
	@Override
	public ExtListDataBo<Idata_CheckPalModel> getCheckPalList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String str, PageBo pageBo) throws Exception {
		String sql="select d.label_no,a.po_no, d.article_no,c.owner_article_no, " +
                   "c.barcode,c.article_name,sum(d.check_qty) checkQty " +
                   "from idata_import_m a,idata_check_m b, " +
                        "idata_check_pal d,bdef_defarticle c " +
                   "where a.enterprise_no=b.enterprise_no  " +
                     "and a.warehouse_no=b.warehouse_no " +
                     "and a.owner_no=b.owner_no " +
                     "and a.import_no=b.import_no " +
                     "and b.enterprise_no=d.enterprise_no " +
                     "and b.warehouse_no=d.warehouse_no " +
                     "and b.owner_no=d.owner_no " +
                     "and b.check_no=d.check_no " +
                     "and d.enterprise_no=c.enterprise_no " +
                     "and d.owner_no=c.owner_no " +
                     "and d.article_no=c.article_no " +
				     "and a.warehouse_no='"+strWarehouseNo+"' " +
				     "and a.enterprise_no='"+strEnterpriseNo+"' " +
				     "and a.owner_no in("+strOwnerNo+") " +
				     "and a.status not in ('13','16') " ;		
     
     if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
     sql=sql+" group by a.po_no,d.label_no,d.article_no,d.packing_qty, "+
             "c.owner_article_no,c.barcode,c.article_name ";
     String totsql = "select count(*) from (" + sql + ") " ;	
		List<Idata_CheckPalModel> list = genDao.getListByNativeSql(sql,Idata_CheckPalModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_CheckPalModel> extListBo= new ExtListDataBo<Idata_CheckPalModel>(list, count);
     return extListBo;
	}
	@Override
	public MsgRes save(String strJsonMaster, String strJsonDetail1)
			throws Exception {
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
		List<Idata_CheckDModel> list=JSON.parseArray(strJsonDetail1,Idata_CheckDModel.class);
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(poMaster.getEnterpriseNo());//strEnterpriseNo
		inList.add(poMaster.getWarehouseNo());//strWareHouseNo
		inList.add(poMaster.getOwnerNo());//strOwnerNo
		inList.add(poMaster.getSImportNo());//strsImportNo
		inList.add(list.get(0).getArticleNo());//strArticleNo
		inList.add(list.get(0).getBarcode());//strBarcode
		inList.add(list.get(0).getPackingQty());//nPackingQty
		inList.add(list.get(0).getCheckQty());//nCheckQty
		inList.add("N");//strPrinterGroupNo
		inList.add(poMaster.getDockNo());//strDockNo
		inList.add(poMaster.getCheckWorker());//strWorkerNo
		inList.add(poMaster.getCheckTools());//strCheckTools
		inList.add(list.get(0).getQuality());
		inList.add(list.get(0).getProduceDate());
		inList.add(list.get(0).getExpireDate());
		inList.add(list.get(0).getLotNo());
		inList.add(list.get(0).getRsvBatch1());
		inList.add(list.get(0).getRsvBatch2());
		inList.add(list.get(0).getRsvBatch3());
		inList.add(list.get(0).getRsvBatch4());
		inList.add(list.get(0).getRsvBatch5());
		inList.add(list.get(0).getRsvBatch6());
		inList.add(list.get(0).getRsvBatch7());
		inList.add(list.get(0).getRsvBatch8());		
		inList.add("N");//strLabelNo
		inList.add("N");//strSupLabelNo
		inList.add(list.get(0).getPrintFlag());//strPrintFlag
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA_ID.P_PC_SAVE_IDCHECK");
		System.out.println(inList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "保存成功","");
	}
	//客户与电子标签储位校验
	@Override
	public MsgRes checkDpsCellNo(String strEnterpriseNo, String warehouseNo,
			String importNo,String articleNo) throws Exception {
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(importNo);//strOwnerNo
		inList.add(articleNo);//strImportNo
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_CheckDpsCellNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "",resultList.get(0).toString());
	}
	
	

}
