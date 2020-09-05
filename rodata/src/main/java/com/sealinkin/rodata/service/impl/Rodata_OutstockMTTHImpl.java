package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.model.Rodata_OutstockMModel;
import com.sealinkin.rodata.service.IRodata_OutstockMTTHService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_OutstockMTTHImpl implements IRodata_OutstockMTTHService {
	private static final Logger logger = Logger.getLogger(Rodata_OutstockMImpl.class);
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	@Override
	public ExtListDataBo<Rodata_OutstockMModel> getRodataOutstockM(
			String enterpriseNo,String warehouseNo, Integer start, Integer limit, String strQuery)
			throws Exception {
        
		String sql=" SELECT  A.enterprise_no," 
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
			+" INNER JOIN BDEF_DEFOWNER D ON A.OWNER_NO = D.OWNER_NO AND A.ENTERPRISE_NO = D.ENTERPRISE_NO "
			+" LEFT JOIN WMS_DEFFIELDVAL C ON UPPER(C.TABLE_NAME) = 'N'"
			+"                            AND UPPER(C.COLNAME) = 'STATUS'"
			+"                            AND A.STATUS = C.VALUE"
			+" LEFT JOIN WMS_DEFFIELDVAL WDV ON UPPER(WDV.TABLE_NAME) = 'N'"
			+"                              AND UPPER(WDV.COLNAME) = 'OPERATE_TYPE'"
			+"                              AND TRIM(NVL(A.OPERATE_TYPE, '')) ="
			+"                                  WDV.VALUE"
			+" LEFT JOIN BDEF_DEFWORKER BD ON A.RGST_NAME = BD.WORKER_NO AND A.ENTERPRISE_NO = BD.ENTERPRISE_NO "
			+" WHERE 1 = 1"
			+"  AND A.enterprise_no = '"+enterpriseNo+"'"
			+"  AND A.warehouse_no = '"+warehouseNo+"'"
			+"  AND A.STATUS = '10'";
		String totsql="select count(*) " 
			+" FROM RODATA_OUTSTOCK_M A"
			+" INNER JOIN BDEF_DEFLOC B ON A.warehouse_no = B.warehouse_no AND A.ENTERPRISE_NO = B.ENTERPRISE_NO "
			+" INNER JOIN BDEF_DEFOWNER D ON A.OWNER_NO = D.OWNER_NO AND A.ENTERPRISE_NO = D.ENTERPRISE_NO "
			+" LEFT JOIN WMS_DEFFIELDVAL C ON UPPER(C.TABLE_NAME) = 'N'"
			+"                            AND UPPER(C.COLNAME) = 'STATUS'"
			+"                            AND A.STATUS = C.VALUE"
			+" LEFT JOIN WMS_DEFFIELDVAL WDV ON UPPER(WDV.TABLE_NAME) = 'N'"
			+"                              AND UPPER(WDV.COLNAME) = 'OPERATE_TYPE'"
			+"                              AND TRIM(NVL(A.OPERATE_TYPE, '')) ="
			+"                                  WDV.VALUE"
			+" LEFT JOIN BDEF_DEFWORKER BD ON A.RGST_NAME = BD.WORKER_NO  AND A.ENTERPRISE_NO = BD.ENTERPRISE_NO "
			+" WHERE 1 = 1 "
			+"  AND A.enterprise_no = '"+enterpriseNo+"'"
			+"  AND A.warehouse_no = '"+warehouseNo+"'"
			+"  AND A.STATUS = '10'"; 
		    if(strQuery!=null && !strQuery.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql+ws;
				totsql=totsql+ws;
			}else{
				sql=sql+" AND A.Task_Type=1";
				totsql=totsql+" AND A.Task_Type=1";
			}
		sql+=" ORDER BY A.OUTSTOCK_NO desc " ;
	  	   List<Rodata_OutstockMModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockMModel.class, start,10000);
	  	 Integer count = genDao.getCountByNativeSql(totsql);
	  	   ExtListDataBo<Rodata_OutstockMModel> extListBo = new ExtListDataBo<Rodata_OutstockMModel>(list, count);
	  	   logger.info("leval queryRodata_OutStock_MModel");
	  	   return extListBo;
	}
	
	@Override
	public ExtListDataBo<Rodata_OutstockDModel> getRodata_OutstockD(
			String strQuery, String flag, Integer start, Integer limit)
			throws Exception {
		String sql="";
		
		if(flag.equals("1")){
			String sql1= "SELECT DISTINCT V.*,       "   
				+" E.REAL_SUPPLIER_NO,  "   
				+" E.SUPPLIER_NAME,  "
				//"nvl(pk.packing_unit, decode(v.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
                //"nvl(pk.spec, '1*' || v.packing_qty || bda.unit) spec,"
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
				//+" BDA.SPEC,  "   
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
				+" FROM (SELECT  A.enterprise_no," 
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
				+" D.BARCODE,  " +
				" d.produce_date,d.expire_date,d.quality,d.lot_no,d.rsv_batch1," +
				" d.rsv_batch2,d.rsv_batch3,d.rsv_batch4,d.rsv_batch5,d.rsv_batch6," +
				" d.rsv_batch7,d.rsv_batch8,"   
				+" RRD.RECEDE_NO,"
				+" SUM(A.ARTICLE_QTY) as ARTICLE_QTY,  " 
				+" (sum(a.article_qty)/ a.packing_qty) plan_wholeNum," 
				+" (sum(a.article_qty)/ a.packing_qty) realWholenum," 
				+" sum(A.REAL_QTY) as REAL_QTY,  "   
				+" sum(RRD.RECEDE_QTY) RECEDE_QTY,a.label_no  "   
				+" FROM RODATA_OUTSTOCK_D A  "   
				+" INNER JOIN STOCK_ARTICLE_INFO D  "   
				+" ON A.ARTICLE_NO = D.ARTICLE_NO  "   
				+" AND A.ARTICLE_ID = D.ARTICLE_ID  " 
				+" AND A.ENTERPRISE_NO = D.ENTERPRISE_NO "
				+" INNER JOIN RODATA_RECEDE_D RRD   "   
				+" ON RRD.warehouse_no = A.warehouse_no  " 
				+" AND RRD.ENTERPRISE_NO = A.ENTERPRISE_NO "
				+" AND RRD.RECEDE_NO = A.SOURCE_NO  "   
				+" AND RRD.ARTICLE_NO = A.ARTICLE_NO  "   
				+" LEFT JOIN RODATA_RECEDE_M RRM   "   
				+" ON RRM.warehouse_no = A.warehouse_no  "
				+" AND RRM.ENTERPRISE_NO = A.ENTERPRISE_NO "
				+" AND RRM.RECEDE_NO = A.SOURCE_NO  "   
				+" WHERE 1=1 and a.status='10' ";
	String sql2= " GROUP BY  A.ENTERPRISE_NO," 
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
				+" D.Article_No,a.label_no,  "   
				+" RRD.RECEDE_NO,"   
				+" RRM.Supplier_No," +
				" d.produce_date,d.expire_date,d.quality,d.lot_no,d.rsv_batch1," +
				" d.rsv_batch2,d.rsv_batch3,d.rsv_batch4,d.rsv_batch5,d.rsv_batch6," +
				" d.rsv_batch7,d.rsv_batch8) V  "   
				+" INNER JOIN bdef_defarticle BDA   "   
				+" ON V.ARTICLE_NO = BDA.ARTICLE_NO  " 
				+" AND V.ENTERPRISE_NO = BDA.enterprise_no "   
				+" LEFT JOIN BDEF_DEFSUPPLIER E   "   
				+" ON V.OWNER_NO = E.OWNER_NO  "   
				+" AND V.SUPPLIER_NO = E.SUPPLIER_NO " 
				+" AND V.ENTERPRISE_NO = E.ENTERPRISE_NO "+ 
				"  left join bdef_article_packing pk " +
				" on v.article_no=pk.article_no" +
				" and v.packing_qty=pk.packing_qty" +
				" and v.enterprise_no=pk.enterprise_no "
				+" ORDER BY V.S_CELL_NO,V.S_LABEL_NO,V.ARTICLE_NO";
			if(strQuery!=null && !strQuery.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql1+ws+sql2;
			}else{
				sql=sql1+sql2;
			}
		}else{
			sql="select distinct a.warehouse_no, a.owner_no, a.outstock_no,a.s_label_no labelNo " +
					"from rodata_outstock_d a where 1=1 and a.status <>'13' ";
			if(strQuery!=null && !strQuery.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql+ws;
			}
		}
	  	List<Rodata_OutstockDModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDModel.class);
	  	ExtListDataBo<Rodata_OutstockDModel> extListBo = new ExtListDataBo<Rodata_OutstockDModel>(list, 0);
	  	logger.info("leval queryRodata_OutStock_MModel");
	  	return extListBo;
	}
	@Override
	public MsgRes savePaper(String workerNo, String outUserId, String str,
			String workSpaceNo) throws Exception {

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
			inList.add(i.getSLabelNo());//来源标签�?
			inList.add(i.getArticleNo());
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
			inList.add(outUserId);
			inList.add(workerNo);
			inList.add(workSpaceNo);
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_MulRsvSaveOutstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25401"),null);//回单成功
	
	}
	@Override
	public MsgRes saveLabel(String workerNo, String outUserId, String str,
			String workSpaceNo) throws Exception {

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
			inList.add(i.getSLabelNo());
			inList.add(outUserId);
			inList.add(workerNo);
			inList.add(workSpaceNo);
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_RO_LabelSave");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25401"),null);//回单成功
	
	}

}
