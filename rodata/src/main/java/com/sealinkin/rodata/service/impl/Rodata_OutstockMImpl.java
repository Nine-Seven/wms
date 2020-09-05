/*
 * 退厂回单和退厂确认
 * zhouhuan
 */
package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.odata.ArticleDateModel;
import com.sealinkin.protocolExchange.odata.ListOutStockAnswerModel;
import com.sealinkin.protocolExchange.odata.OdataOutStockRequestModel;
import com.sealinkin.protocolExchange.odata.OutStockDateAnswerModel;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;
import com.sealinkin.rodata.service.IRodata_OutstockMService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_OutstockMImpl implements IRodata_OutstockMService{
    private static final Logger logger = Logger.getLogger(Rodata_OutstockMImpl.class);
		
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
	/*
	 * 获得退厂回单单头
	 * zhouhuan
	 */
	public ExtListDataBo<Rodata_OutstockMModel> getRodata_OutstockM(
			String enterpriseNo,String strWarehouseNo,Integer intStart, Integer intLimit,String strQuery) {
        
		String sql=" SELECT A.enterprise_no," 
			+"      A.warehouse_no,"
			+"      A.OWNER_NO,"
			+"      A.OUTSTOCK_NO,"
			+"      A.OPERATE_DATE,"
			+"      A.OPERATE_TYPE,"
			+"      A.STATUS,"
			+" f_get_fieldtext('N','STATUS',A.STATUS) as statusText,"
			+"      A.RGST_NAME,"
			+"      A.RGST_DATE,"
			+"      A.UPDT_NAME,"
			+"      A.UPDT_DATE,"
			+"      WDV.TEXT AS OPERATE_TYPE_DESC,"
			+"      BD.WORKER_NAME,"
			+"      D.OWNER_NAME,"
			+"      NVL(C.TEXT, A.STATUS) STATUS_DESC"
			+" FROM RODATA_OUTSTOCK_M A"
			+" INNER JOIN BDEF_DEFOWNER D ON A.OWNER_NO = D.OWNER_NO AND a.enterprise_no = d.enterprise_no "
			+" LEFT JOIN WMS_DEFFIELDVAL C ON UPPER(C.TABLE_NAME) = 'N'"
			+"                            AND UPPER(C.COLNAME) = 'STATUS'"
			+"                            AND A.STATUS = C.VALUE"
			+" LEFT JOIN WMS_DEFFIELDVAL WDV ON UPPER(WDV.TABLE_NAME) = 'N'"
			+"                              AND UPPER(WDV.COLNAME) = 'OPERATE_TYPE'"
			+"                              AND TRIM(NVL(A.OPERATE_TYPE, '')) ="
			+"                                  WDV.VALUE"
			+" LEFT JOIN BDEF_DEFWORKER BD ON A.RGST_NAME = BD.WORKER_NO  AND A.ENTERPRISE_NO = BD.ENTERPRISE_NO "
			+" WHERE 1 = 1"
			+"  AND A.warehouse_no = '"+strWarehouseNo+"'"
			+"  AND A.enterprise_no = '"+enterpriseNo+"'"
			+"  AND A.STATUS = '10'";
		String totsql="select count(*) " 
			+" FROM RODATA_OUTSTOCK_M A"
			+" INNER JOIN BDEF_DEFLOC B ON A.warehouse_no = B.warehouse_no AND a.enterprise_no = b.enterprise_no "
			+" INNER JOIN BDEF_DEFOWNER D ON A.OWNER_NO = D.OWNER_NO AND a.enterprise_no = d.enterprise_no "
			+" LEFT JOIN WMS_DEFFIELDVAL C ON UPPER(C.TABLE_NAME) = 'N'"
			+"                            AND UPPER(C.COLNAME) = 'STATUS'"
			+"                            AND A.STATUS = C.VALUE"
			+" LEFT JOIN WMS_DEFFIELDVAL WDV ON UPPER(WDV.TABLE_NAME) = 'N'"
			+"                              AND UPPER(WDV.COLNAME) = 'OPERATE_TYPE'"
			+"                              AND TRIM(NVL(A.OPERATE_TYPE, '')) ="
			+"                                  WDV.VALUE"
			+" LEFT JOIN BDEF_DEFWORKER BD ON A.RGST_NAME = BD.WORKER_NO AND A.ENTERPRISE_NO = BD.ENTERPRISE_NO "
			+" WHERE 1 = 1"
			+"  AND A.warehouse_no = '"+strWarehouseNo+"'"
			+"  AND A.enterprise_no = '"+enterpriseNo+"'"
			+"  AND A.STATUS = '10'"; ;
		    if(strQuery!=null && !strQuery.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql+ws;
				totsql=totsql+ws;
			}
		sql+=" ORDER BY A.OUTSTOCK_NO desc " ;
	  	   List<Rodata_OutstockMModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockMModel.class, intStart,intLimit);
	  	 Integer count = genDao.getCountByNativeSql(totsql);
	  	   ExtListDataBo<Rodata_OutstockMModel> extListBo = new ExtListDataBo<Rodata_OutstockMModel>(list, count);
	  	   logger.info("leval queryRodata_OutStock_MModel");
	  	   return extListBo;
	}

	/*
	 * 获得退厂回单明细
	 * zhouhuan
	 * update by czh 2016.5.30
	 */
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockD(String str,
			Integer intStart, Integer intLimit) {
		String sql="";
		String sql1= "SELECT DISTINCT V.*,       "   
			+" E.REAL_SUPPLIER_NO,  "   
			+" E.SUPPLIER_NAME,  "   
			//+"nvl(pk.packing_unit, decode(v.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"
			//+"nvl(pk.spec, '1*' || v.packing_qty|| bda.unit) spec," 
			+" f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,v.packing_qty) packingUnit,"
			+" f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,bda.qmin_operate_packing) packingUnitQmin,"
			+" f_get_packingunit(v.enterprise_no,v.owner_no,v.article_no,bda.unit_packing) Unit,"
			+" f_get_spec(v.enterprise_no,v.owner_no,v.article_no,v.packing_qty) packingSpec,"
			+" f_get_spec(v.enterprise_no,v.owner_no,v.article_no,bda.qmin_operate_packing) packingSpecQmin,"
			+" f_get_spec(v.enterprise_no,v.owner_no,v.article_no,bda.unit_packing) spec,"
			+" BDA.OWNER_ARTICLE_NO,  "   
			+" BDA.ARTICLE_NAME,  "   
			+" BDA.ARTICLE_ENAME,  "   
			+" BDA.ARTICLE_IDENTIFIER,  "   
			+" BDA.ARTICLE_ONAME,  "   
			+" BDA.ARTICLE_ALIAS,  "   
		//	+" BDA.SPEC,  "   
			+"trunc(v.article_qty/v.packing_qty) as planBox,"+
			"trunc(mod(v.article_qty,v.packing_qty)/BDA.QMIN_OPERATE_PACKING) as planQmin,"+
			"(v.article_qty - trunc(v.article_qty/v.packing_qty) * v.packing_qty - trunc(mod(v.article_qty,v.packing_qty)/BDA.QMIN_OPERATE_PACKING) * BDA.QMIN_OPERATE_PACKING) as planDis,"
			+"trunc(v.article_qty/v.packing_qty) as realBox,"+
			"trunc(mod(v.article_qty,v.packing_qty)/BDA.QMIN_OPERATE_PACKING) as realQmin,"+
			"(v.article_qty - trunc(v.article_qty/v.packing_qty) * v.packing_qty - trunc(mod(v.article_qty,v.packing_qty)/BDA.QMIN_OPERATE_PACKING) * BDA.QMIN_OPERATE_PACKING) as realDis,"
			
			+" BDA.QMIN_OPERATE_PACKING,  "   
			+" BDA.RULE_FLAG,  "   
			+" BDA.ABC,  "   
			+" BDA.QC_FLAG,  "   
			+" BDA.MEASURE_MODE,  "   
			+" BDA.TEMPERATURE_FLAG,  "   
			+" BDA.VIRTUAL_FLAG,  "   
			+" BDA.SCAN_FLAG,  "   
			+" BDA.UNIT,  "   
			+" BDA.DOUBLE_CHECK,  "   
			+" BDA.EXPIRY_DAYS "   
			+" FROM (SELECT  A.enterprise_no, " 
			+" A.warehouse_no,  "   
			+" A.OWNER_NO,  "   
			+" A.OUTSTOCK_NO,  "   
			+" A.SOURCE_NO,  "   
			+" A.S_CELL_NO,  "   
			+" A.S_LABEL_NO,  "   
			+" A.D_CELL_NO,  "   
			+" A.OUTSTOCK_CELL_NO,  "   
			+" A.OUTSTOCK_CONTAINER_NO,  "   
			+" A.PACKING_QTY,  "   
			+" D.Article_No,  "   
			+" RRM.Supplier_No,  "   
			+" D.BARCODE,  " 
			+" D.Quality,"
			+" D.Produce_Date,"
            +" D.Expire_Date,"
            +" D.Lot_No,"
            +" D.Rsv_Batch1,"
            +" D.Rsv_Batch2,"
            +" D.Rsv_Batch3,"
            +" D.Rsv_Batch4,"
            +" D.Rsv_Batch5,"
            +" D.Rsv_Batch6,"
            +" D.Rsv_Batch7,"
            +" D.Rsv_Batch8,"
			+" RRD.RECEDE_NO,"
			+" SUM(A.ARTICLE_QTY) as ARTICLE_QTY,  " 
			/*+" (sum(a.article_qty)/ a.packing_qty) plan_wholeNum," 
			+" (sum(a.article_qty)/ a.packing_qty) realWholenum," */			
			
			+" sum(A.REAL_QTY) as REAL_QTY,  "   
			+" sum(RRD.RECEDE_QTY) RECEDE_QTY  "   
			+" FROM RODATA_OUTSTOCK_D A  "   
			+" INNER JOIN STOCK_ARTICLE_INFO D  "   
			+" ON A.ARTICLE_NO = D.ARTICLE_NO  "   
			+" AND A.ARTICLE_ID = D.ARTICLE_ID  " 
			+" AND A.ENTERPRISE_NO = D.ENTERPRISE_NO "
			+" INNER JOIN RODATA_RECEDE_D RRD   "   
			+" ON RRD.warehouse_no = A.warehouse_no  "  
			+" AND RRD.ENTERPRISE_NO = A.ENTERPRISE_NO  "
			+" AND RRD.RECEDE_NO = A.SOURCE_NO  "   
			+" AND RRD.ARTICLE_NO = A.ARTICLE_NO  "   
			+" LEFT JOIN RODATA_RECEDE_M RRM   "   
			+" ON RRM.warehouse_no = A.warehouse_no  "
			+" AND RRM.ENTERPRISE_NO = A.ENTERPRISE_NO "
			+" AND RRM.RECEDE_NO = A.SOURCE_NO  "   
			+" WHERE 1=1 ";
String sql2= " GROUP BY  A.enterprise_no," 
		    +" A.warehouse_no,  "   
			+" A.OWNER_NO,  "   
			+" A.OUTSTOCK_NO,  "   
			+" A.SOURCE_NO,  "   
			+" A.S_CELL_NO,  "   
			+" A.S_LABEL_NO,  "   
			+" A.D_CELL_NO,  "    
			+" A.OUTSTOCK_CELL_NO,  "   
			+" A.OUTSTOCK_CONTAINER_NO,  "   
			+" A.PACKING_QTY,  "   
			+" D.BARCODE,  "   
			+" D.Quality,"
			+" D.Produce_Date,"
            +" D.Expire_Date,"
            +" D.Lot_No,"
            +" D.Rsv_Batch1,"
            +" D.Rsv_Batch2,"
            +" D.Rsv_Batch3,"
            +" D.Rsv_Batch4,"
            +" D.Rsv_Batch5,"
            +" D.Rsv_Batch6,"
            +" D.Rsv_Batch7,"
            +" D.Rsv_Batch8,"
			+" D.Article_No,  "   
			+" RRD.RECEDE_NO,"   
			+" RRM.Supplier_No) V  "   
			+" INNER JOIN bdef_defarticle BDA   "   
			+" ON V.ARTICLE_NO = BDA.ARTICLE_NO  "  
			+" AND V.enterprise_no = BDA.enterprise_no "
			+" LEFT JOIN BDEF_DEFSUPPLIER E   "   
			+" ON V.OWNER_NO = E.OWNER_NO  "
			+" AND V.enterprise_no = E.ENTERPRISE_NO "
			+" AND V.SUPPLIER_NO = E.SUPPLIER_NO  " + 
			"  left join bdef_article_packing pk " +
			" on v.article_no=pk.article_no" +
			" and v.packing_qty=pk.packing_qty" +
			" and v.enterprise_no=pk.enterprise_no "
			+" ORDER BY V.S_CELL_NO, V.ARTICLE_NO";
		if(str!=null && !str.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql1+ws+sql2;
			}else{
				sql=sql1+sql2;
			}
	  	List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
	  	logger.info("leval queryRodata_OutStock_MModel");
	  	return extListBo;
	}

	@Override
	public MsgRes save(String strWorkerNo,String strOutUserId,String str,String strWorkSpaceNo) throws Exception {
		List<Rodata_OutstockDModel> odd=JSON.parseArray(str,Rodata_OutstockDModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		for (Rodata_OutstockDModel i : odd) {
			List  inList=new ArrayList();
			inList.add(i.getId().getEnterpriseNo());
			inList.add(i.getId().getWarehouseNo());
			inList.add(i.getId().getOwnerNo());
			inList.add(i.getOutstockNo());
			inList.add(i.getSLabelNo()); //strLabelNo
			
			inList.add(i.getArticleNo());
			inList.add(i.getBarcode());
			inList.add(i.getPackingQty());
			inList.add(i.getQuality());//strQuality
			inList.add(i.getProduceDate());//dtProduceDate
			inList.add(i.getExpireDate());//dtExpireDate
			inList.add(i.getLotNo());//strLotNo
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch1())?"N":i.getRsvBatch1());//strRSV_BATCH1
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch2())?"N":i.getRsvBatch2());//strRSV_BATCH2
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch3())?"N":i.getRsvBatch3());//strRSV_BATCH3
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch4())?"N":i.getRsvBatch4());//strRSV_BATCH4
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch5())?"N":i.getRsvBatch5());//strRSV_BATCH5
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch6())?"N":i.getRsvBatch6());//strRSV_BATCH6
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch7())?"N":i.getRsvBatch7());//strRSV_BATCH7
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch8())?"N":i.getRsvBatch8());//strRSV_BATCH8
			
			inList.add(i.getSCellNo());
			inList.add(i.getArticleQty());
			inList.add(i.getRealQty());
			inList.add(i.getDCellNo());
			inList.add(strOutUserId);
			inList.add(strWorkerNo);
			inList.add(strWorkSpaceNo);
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_RO_OutstockReturn");
			System.out.println(resultList.get(0).toString());
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
	
		return new MsgRes(true,TipUtil.getTipsByKey("E25401"),null);//回单成功
	}
/*	@Override
	public MsgRes tscRoComfirm(String enterpriseNo,String strWarehouseNo, String strOwnerNo,
			String strRecedeNo, String strUserID, String strDock_No)
			throws Exception {
		List  inList=new ArrayList();
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(enterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strRecedeNo);
		inList.add(strUserID);
		inList.add(strDock_No);
				
		resultList=genDao.execProc(inList, outList, "pklg_rodata.P_RO_Confirm");
	
		System.out.println(resultList.get(0).toString());
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25401"),null);//回单成功
	}
	*//**
	 * 退厂确认 查询头档信息
	 * @param warehouseNo
	 * @author lich
	 * @param start
	 * @param limit
	 * @param queryStr
	 * @return
	 *//*
	@Override
	public ExtListDataBo<Rodata_OutstockMModel> getRodata_OutstockMComfirm(
			String enterpriseNo,
			String strWarehouseNo, 
			Integer start, 
			Integer limit, 
			String strQuery) {
		String sql=" select distinct " +
					"c.po_no," +
				    "c.recede_no,"+
					"d.supplier_no," +
					"d.supplier_name," +
					"f_get_fieldtext('N','STATUS',a.STATUS) as statusText ," +
					"a.rgst_date," +
					"a.rgst_name," +
					"a.owner_no " +
				"from " +
					"rodata_outstock_m a," +
					"rodata_outstock_d b," +
					"rodata_recede_m c," +
					"bdef_defsupplier d " +
				"where " +
					"a.enterprise_no=b.enterprise_no "+
				    "and a.warehouse_no=b.warehouse_no "+
					"and a.outstock_no=b.outstock_no " +
				    "and b.enterprise_no=c.enterprise_no "+
					"and b.warehouse_no=c.warehouse_no " +
					"and b.source_no=c.recede_no " +
					"and c.enterprise_no=d.enterprise_no "+
					"and c.supplier_no=d.supplier_no "+
					"and a.status = '13' " +
					"and c.status='14' " +
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+strWarehouseNo+"' ";
			
	    if(!StringUtil.isEmpty(strQuery))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		sql+=" order by a.rgst_date desc " ;
  	   	List<Rodata_OutstockMModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockMModel.class, start,limit);
  	   	Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
  	   	ExtListDataBo<Rodata_OutstockMModel> extListBo = new ExtListDataBo<Rodata_OutstockMModel>(list, count);
  	   	logger.info("leval queryRodata_OutStock_MModel");
  	   	return extListBo;
	}
	*//**
	 * 退厂确认 查询明细信息
	 * @author lich
	 * @param string
	 * @param start
	 * @param limit
	 * @return
	 *//*
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockDComfirm(
			String str, Integer start, Integer limit) {
		String sql= "   select    "+
				"        v.*,   "+
				"        bda.owner_article_no,   "+
				"        bda.article_name,   "+
				"        bda.article_ename,   "+
				"        bda.article_oname,   "+
				"        bda.article_alias,   "+
				"        bda.spec,   "+
				"        bda.qmin_operate_packing,         "+
				"        bda.unit   "+
				"    from   "+
				"        (select   "+
				"            a.enterprise_no,  "+
				"            a.warehouse_no,   "+
				"            a.owner_no,              "+
				"            a.packing_qty,   "+
				"            d.article_no,   "+
				"            d.barcode,   "+
				"            sum(a.article_qty) as article_qty,   "+
				"            trunc(sum(a.article_qty)/ a.packing_qty) plan_wholenum,   "+
				"            mod(sum(a.article_qty),a.packing_qty) plan_scatterednum,   "+
				"            trunc(sum(a.real_qty)/ a.packing_qty) realwholenum,   "+
				"            mod(sum(a.real_qty),a.packing_qty) realscatterednum,   "+
				"            sum(a.real_qty) as real_qty,   "+
				"            sum(rrd.recede_qty) recede_qty      "+
				"        from   "+
				"            rodata_outstock_d a      "+
				"        inner join   "+
				"            stock_article_info d      "+
				"                on a.article_no = d.article_no      "+
				"                and a.article_id = d.article_id      "+
				"                 and a.enterprise_no=d.enterprise_no "+
				"        inner join   "+
				"            rodata_recede_d rrd       "+
				"                on rrd.warehouse_no = a.warehouse_no      "+
				"                and rrd.recede_no = a.source_no      "+
				"                and rrd.article_no = a.article_no      "+
				"                and rrd.enterprise_no = a.enterprise_no "+
				"        left join   "+
				"            rodata_recede_m rrm       "+
				"                on rrm.warehouse_no = a.warehouse_no      "+
				"                and rrm.recede_no = a.source_no      "+
				"                and rrm.enterprise_no=a.enterprise_no "+
				"        where   "+
				"            1=1   %s1   "+
				"        group by   "+
				"            a.enterprise_no,  "+
				"            a.warehouse_no,   "+
				"            a.owner_no,             "+
				"            a.packing_qty,   "+
				"            d.barcode,   "+
				"            d.article_no   "+
				"            ) v      "+
				"    inner join   "+
				"        bdef_defarticle bda       "+
				"            on v.article_no = bda.article_no         "+
				"            and v.enterprise_no = bda.enterprise_no "+
				"    order by   "+
				"        v.article_no ";
		if(!StringUtil.isEmpty(str))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql.replace("%s1", ws);
		}else{
			sql=sql.replace("%s1", " ");
		}
	  	List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
	  	logger.info("leval queryRodata_OutStock_MModel");
	  	return extListBo;
	}
	
	*//**
     * 退厂确认 货主、供应商……下拉
     * @author lich
	 * @param  
     *//*
	@Override
	public List<ComboxBo> getRodata_OutstockComfirmCombo(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str) {
		String strSql="";
		if(!StringUtil.isEmpty(strFlag) && strFlag.equals("1"))//1:货主
		{
			strSql = "select distinct " +
						"b.owner_no as value," +
						"c.owner_name  as text," +
						"'['||b.owner_no||']' ||c.owner_name as dropValue " +
					"from " +
						"rodata_outstock_m a," +
						"rodata_outstock_d b," +
						"bdef_defowner c " +
					"where " +
						"a.enterprise_no=b.enterprise_no "+
					    "and a.warehouse_no=b.warehouse_no "+
						"and a.outstock_no=b.outstock_no " +
					    "and b.enterprise_no=c.enterprise_no "+
						"and b.owner_no=c.owner_no " +
						"and a.status='13' " ;			
		}else if(!StringUtil.isEmpty(strFlag) && strFlag.equals("2"))//2:供应商
		{
			strSql = "select distinct " +
						"d.supplier_no as value," +
						"d.supplier_name as text," +
						"'['||d.supplier_no||']' ||d.supplier_name as dropValue " +
					"from " +
						"rodata_outstock_m a," +
						"rodata_outstock_d b," +
						"rodata_recede_m c," +
						"bdef_defsupplier d " +
					"where " +
						"a.enterprise_no=b.enterprise_no "+
					    "and a.warehouse_no=b.warehouse_no "+
						"and a.outstock_no=b.outstock_no " +
					    "and b.enterprise_no=c.enterprise_no "+
						"and b.warehouse_no=c.warehouse_no " +
						"and b.source_no=c.recede_no " +
						"and c.enterprise_no=d.enterprise_no " +
						"and c.supplier_no=d.supplier_no " +
						"and a.status='13' " +
						"and c.status='14' " ;	
		}
		if(!StringUtil.isEmpty(str) && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql += ws;
		}
		if(!StringUtil.isEmpty(enterpriseNo) && !enterpriseNo.equals(""))
		{
			strSql +=" and a.enterprise_No ='" + enterpriseNo + "' ";
		}else
		{
			strSql += " and 1=2 ";
		}
		if(!StringUtil.isEmpty(strWarehouseNo) && !strWarehouseNo.equals(""))
		{
			strSql +=" and a.warehouse_No ='" + strWarehouseNo + "' ";
		}else
		{
			strSql += " and 1=2 ";
		}
		if(!StringUtil.isEmpty(strWorkerOwner) && !strWorkerOwner.equals(""))
		{
			strSql += " and b.owner_no in("+strWorkerOwner+") ";
		}else{
			strSql += " and 1=2 ";
		}
		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}*/
	
	
	/**
	 * RF退厂回单 扫标签获取下架信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes ROdataCheckLabelNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strOutstockNo=reqMod.getReqObj();		
		
		//取拣货数据
		Map<Integer, OutStockDateAnswerModel> map=new HashMap<Integer, OutStockDateAnswerModel>();
		ListOutStockAnswerModel listOutStockMod=new ListOutStockAnswerModel();
		String strSql="SELECT A.*,CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER,BDA.OWNER_ARTICLE_NO, " +
					" BDA.ARTICLE_NAME,BDA.ARTICLE_ENAME,BDA.ARTICLE_IDENTIFIER,BDA.ARTICLE_ONAME, " +
					" BDA.ARTICLE_ALIAS,BDA.UNIT_PACKING," +
					//" nvl(a.spec, '1*' || a.PACKQTY || bda.unit) spec," +
					" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.PACKQTY) packingUnit,"+
					" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
					" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"+
					" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.PACKQTY) packingSpec,"+
					" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
					" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"+
					"BDA.QMIN_OPERATE_PACKING,BDA.RULE_FLAG,BDA.ABC, " +
					" BDA.QC_FLAG,BDA.MEASURE_MODE,BDA.TEMPERATURE_FLAG,BDA.VIRTUAL_FLAG,BDA.SCAN_FLAG, " +
					" BDA.DOUBLE_CHECK,BDA.PICK_EXCESS,BDA.EXPIRY_DAYS " +
					" FROM (SELECT OOD.ENTERPRISE_NO, OOD.WAREHOUSE_NO, OOD.SOURCE_NO," +
					" OOD.ARTICLE_NO, OOD.PACKING_QTY AS PACKQTY, OOD.S_LABEL_NO LABEL_NO, " +
					" OOD.STOCK_TYPE, OOD.S_CELL_NO, OOD.D_CELL_NO, " +
					" SUM(OOD.ARTICLE_QTY) AS ARTICLE_QTY, SUM(OOD.REAL_QTY) REAL_QTY, " +
					" SAI.BARCODE, /*SAI.IMPORT_BATCH_NO,*/ SAI.RSV_BATCH1, SAI.RSV_BATCH2, " +
					" SAI.RSV_BATCH3, SAI.RSV_BATCH4, SAI.RSV_BATCH5, SAI.RSV_BATCH6, " +
					" SAI.RSV_BATCH7, SAI.RSV_BATCH8, TO_CHAR(SAI.EXPIRE_DATE, 'YYYYMMDD') EXPIRE_DATE, " +
					" TO_CHAR(SAI.PRODUCE_DATE, 'YYYYMMDD') PRODUCE_DATE, SAI.LOT_NO, SAI.QUALITY, " +
					" F_GET_FIELDTEXT('N', 'QUALITY', SAI.QUALITY) TEXTQUALITY, OOD.OUTSTOCK_NO, OOD.OWNER_NO " +
					" FROM RODATA_OUTSTOCK_D OOD " +
					" JOIN RODATA_OUTSTOCK_M OOM " +
					" ON OOD.WAREHOUSE_NO = OOM.WAREHOUSE_NO    AND OOD.ENTERPRISE_NO = OOM.ENTERPRISE_NO " +
					" AND OOD.OUTSTOCK_NO = OOM.OUTSTOCK_NO    AND OOD.OWNER_NO = OOM.OWNER_NO " +
					" LEFT JOIN BDEF_ARTICLE_PACKING BAP " +
					" ON OOD.ARTICLE_NO = BAP.ARTICLE_NO    AND OOD.PACKING_QTY = BAP.PACKING_QTY " +
					" AND OOD.ENTERPRISE_NO = BAP.ENTERPRISE_NO " +
					" INNER JOIN STOCK_ARTICLE_INFO SAI " +
					" ON OOD.ARTICLE_NO = SAI.ARTICLE_NO    AND OOD.ARTICLE_ID = SAI.ARTICLE_ID " +
					" AND OOD.ENTERPRISE_NO = SAI.ENTERPRISE_NO " +
					" WHERE OOD.STATUS = '10' " +
					" AND OOM.TASK_TYPE = '1' " +
					" AND OOM.OUTSTOCK_NO = '" + strOutstockNo + "'" +
					" AND OOM.WAREHOUSE_NO = '" + strWarehouseNo + "'" +
					" AND OOM.ENTERPRISE_NO = '" + strEnterpriseNo + "'" +
					" GROUP BY OOD.ENTERPRISE_NO,    OOD.WAREHOUSE_NO,    OOD.OWNER_NO,    OOD.OUTSTOCK_NO, " +
					" OOD.SOURCE_NO,    OOD.ARTICLE_NO,    OOD.PACKING_QTY,    OOD.S_LABEL_NO, " +
					" OOD.STOCK_TYPE,    OOM.OPERATE_TYPE,    OOD.S_CELL_NO,    OOD.D_CELL_NO, " +
					" SAI.EXPIRE_DATE,    SAI.LOT_NO,    SAI.PRODUCE_DATE,    SAI.QUALITY, " +
					" SAI.BARCODE,    /*SAI.IMPORT_BATCH_NO,*/    SAI.RSV_BATCH1,    SAI.RSV_BATCH2, " +
					" SAI.RSV_BATCH3,    SAI.RSV_BATCH4,    SAI.RSV_BATCH5,    SAI.RSV_BATCH6, " +
					" SAI.RSV_BATCH7,    SAI.RSV_BATCH8) A " +
					" INNER JOIN BDEF_DEFARTICLE BDA " +
					" ON BDA.ARTICLE_NO = A.ARTICLE_NO   AND BDA.OWNER_NO = A.OWNER_NO " +
					" AND BDA.ENTERPRISE_NO = A.ENTERPRISE_NO " +
					" INNER JOIN CDEF_DEFCELL CDC " +
					" ON CDC.ENTERPRISE_NO = A.ENTERPRISE_NO   AND CDC.WAREHOUSE_NO = A.WAREHOUSE_NO " +
					" AND CDC.CELL_NO = A.S_CELL_NO " +
					" ORDER BY A.WAREHOUSE_NO,   A.SOURCE_NO,   CDC.WARE_NO,   CDC.AREA_NO, " +
					" CDC.STOCK_NO,   CDC.PICK_ORDER,   A.S_CELL_NO,   A.ARTICLE_NO,   A.D_CELL_NO";
		List<OutStockDateAnswerModel> list = genDao.getListByNativeSql(strSql, OutStockDateAnswerModel.class);
		
		for (int i=0;i<list.size();i++) 
		{
			map.put(i, list.get(i));
		}
		
		if(map.size()>0)
		{
			msgRes.setIsSucc(true);
			listOutStockMod.setArrStuOutStock(map);
			msgRes.setObj(JSONObject.toJSON(listOutStockMod));
		}else
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("没有获取到拣货明细数据！");
		}

		return msgRes;
	}
	/**
	 * RF退厂回单 扫条码校验
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes ROdataCheckBarcode(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		ArticleDateModel reqMod=JSON.parseObject(strRecvData, ArticleDateModel.class);
		msgRes=getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(), 
				reqMod.getBarcode(), 
				reqMod.getOwnerNo(),
				reqMod.getEnterpriseNo());
		if(msgRes.getIsSucc())
		{
			if(msgRes.getObj().toString().indexOf(reqMod.getArticleNo())==-1)
			{
				reqMod.setArticleNo(msgRes.getObj().toString());
			}
			msgRes.setObj(JSON.toJSON(reqMod));
		}
		return msgRes;
	}
	/**
	 * RF退厂回单 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscROPickReceipt(String strRecvData) throws Exception {
		//请求结构转换
		OdataOutStockRequestModel reqMod=JSON.parseObject(strRecvData, OdataOutStockRequestModel.class);
		
		//RF拣货回单
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		
		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(reqMod.getWarehouseNo());//strWarehose_No
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getOutStockNo());//strOutStockNo
		inList.add(reqMod.getLabelNo());//strsLabelNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getPackQty());   //nPackingQTY
		inList.add(StringUtil.isStrEmpty(reqMod.getQuality())?"0":reqMod.getQuality());//strQuality
		inList.add(DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd")==null? 
				DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"));//dtProduceDate
		inList.add(DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd")==null? 
				DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"));//dtExpireDate
		inList.add(StringUtil.isStrEmpty(reqMod.getLotNo())?"N":reqMod.getLotNo());//strLotNo
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());//strRSV_BATCH1
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());//strRSV_BATCH2
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());//strRSV_BATCH3
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());//strRSV_BATCH4
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());//strRSV_BATCH5
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());//strRSV_BATCH6
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());//strRSV_BATCH7
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());//strRSV_BATCH8
		inList.add(reqMod.getScellNo());//strScellNo
		inList.add(reqMod.getArticleQty());//nArticleQTY
		inList.add(reqMod.getOutStockQty());//nRealQTY
		inList.add(reqMod.getDcellNo());//strRealCellNo
		inList.add(reqMod.getUserId());//strOutUserID
		inList.add(reqMod.getUserId());//strUserID
		inList.add("N");//strDock_No
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_rodata.P_MulRsvSaveOutstock");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList); 
	}
}
