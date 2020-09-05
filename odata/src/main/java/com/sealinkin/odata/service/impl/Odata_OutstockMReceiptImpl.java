package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;
import com.sealinkin.odata.service.IOdata_OutstockM;
import com.sealinkin.odata.service.IOdata_OutstockMReceipt;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.odata.ArticleDateModel;
import com.sealinkin.protocolExchange.odata.DivideDateAnswerModel;
import com.sealinkin.protocolExchange.odata.ListOutStockAnswerModel;
import com.sealinkin.protocolExchange.odata.ODataGetOutStockInfoModel;
import com.sealinkin.protocolExchange.odata.ODataWaitAerrangeInfo;
import com.sealinkin.protocolExchange.odata.OdataOutStockRequestModel;
import com.sealinkin.protocolExchange.odata.OdataSkuLabelCancelModel;
import com.sealinkin.protocolExchange.odata.OutStockDateAnswerModel;
import com.sealinkin.protocolExchange.odata.ReqGetExpCheckInfoModel;
import com.sealinkin.protocolExchange.odata.StuGetExpCheckInfoModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.MyDateMorpher;
import com.sealinkin.util.StringUtil;

/**
 * 拣货回单
 * @author 周欢
 *
 */
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class Odata_OutstockMReceiptImpl implements IOdata_OutstockMReceipt{
	
	private IGenericManager genDao;
	private IOdata_OutstockM odata_OutstockMImpl;
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	public IOdata_OutstockM getOdata_OutstockMImpl() {
		return odata_OutstockMImpl;
	}
	public void setOdata_OutstockMImpl(IOdata_OutstockM odata_OutstockMImpl) {
		this.odata_OutstockMImpl = odata_OutstockMImpl;
	}	
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
	/**
	 * 拣货回单》获取单据信息
	 */
	@Override
	public ExtListDataBo<Odata_OutstockMModel> getOdata_OutstockM(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str, 
			String strSendFlag,
			int intStart, int intPagesize) 
	{
		String strSql="";
		String strTotsql="";
			strSql =  " select distinct  (select max(t.exp_type)"
					+" from %s6 t"
					+" where t.enterprise_no = oom.enterprise_no " +
					"  and t.Warehouse_no = oom.Warehouse_no"
					+" and t.outstock_no = oom.outstock_no) exp_type,"
					+" oom.*,k.worker_name,              "
					+" (select bdf.trade_flag"
					+" from bdef_defcust bdf"
					+" where bdf.cust_no = (select ood.cust_no"
					+" from %s6 ood"
					+" where ood.enterprise_no = oom.enterprise_no " +
					"  and ood.outstock_no = oom.Outstock_No"
					+" and rownum <= 1)" +
					"  and bdf.enterprise_no = '"+enterpriseNo+"') as trade_flag,"
					+" f_get_fieldtext('N',"
					+" 'STATUS',           "
					+" oom.status)statusText,"
					+" f_get_fieldtext('N',"
					+" 'OUTSTOCK_TYPE',    "
					+" oom.outstock_type)outstock_typeText            "
					+" from                "
					+" %s5 oom,  "
					+" %s6 ood ,bdef_defworker k     "
					+" where               "
					+" oom.warehouse_no=ood.warehouse_no               "
					+" and oom.enterprise_no=ood.enterprise_no             "
					+" and oom.outstock_no=ood.outstock_no   " +
					" and oom.enterprise_no=k.enterprise_no(+) " +
					" and oom.warehouse_no=k.warehouse_no(+) and oom.handout_name=k.worker_no(+)   "
					+" %s0 %s1 %s2 %s3 %s4 "
					+" %s7 "
					+" order by            "
					+" oom.outstock_no desc";
			
        if(enterpriseNo!=null && !enterpriseNo.equals(""))			
        {
			strSql = strSql.replace("%s0", " and oom.enterprise_no='"+enterpriseNo+"' ");
		}else
		{
			strSql = strSql.replace("%s0", "");
		}	
        	
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
		}
		if(strWarehouseNo!=null && !strWarehouseNo.equals(""))
		{
			strSql = strSql.replace("%s2", " and oom.warehouse_No='"+strWarehouseNo+"' ");
		}else
		{
			strSql = strSql.replace("%s2", "");
		}
		
		
		
		if(strWorkerOwner != null && !strWorkerOwner.equals(""))
		{
			strSql = strSql.replace("%s3", " and ood.owner_no in("+strWorkerOwner+") ");
		}else{
			strSql = strSql.replace("%s3", " and 1=2 ");
		}
		if(strSendFlag != null && !strSendFlag.equals(""))
		{
			if(strSendFlag.trim().equals("label"))
			{
				strSql = strSql.replace("%s4", " and oom.task_type=2 AND oom.OUTSTOCK_TYPE in ('0')");
			}else if(strSendFlag.trim().equals("form"))
			{
				strSql = strSql.replace("%s4", " and oom.task_type=1 AND oom.OUTSTOCK_TYPE ='0' ");
			}
			
		}else{
			strSql = strSql.replace("%s4", " ");
		}
		
		if(strFlag != null && strFlag.trim().equals("2"))
		{
			strSql = strSql.replace("%s5", "odata_outstock_mhty ");
			strSql = strSql.replace("%s6", "odata_outstock_dhty ");
			strSql = strSql.replace("%s7", " ");
		}else if(strFlag != null && strFlag.trim().equals("1")){
			strSql = strSql.replace("%s5", "odata_outstock_m ");
			strSql = strSql.replace("%s6", "odata_outstock_d ");
			strSql = strSql.replace("%s7", "AND oom.STATUS < '13' ");
		}
		strTotsql = "select count(*) from (" + strSql + ")";
		List<Odata_OutstockMModel> list = genDao.getListByNativeSql(strSql,Odata_OutstockMModel.class,intStart,intPagesize);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_OutstockMModel> extListBo= new ExtListDataBo<Odata_OutstockMModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 拣货回单》选择单据刷新商品明细信息
	 * @throws Exception 
	 */
	@Override
	public ExtListDataBo<Odata_OutstockDModel> getOdata_OutstockD(
			String enterpriseNo,
			String strWarehouseNo,
			String strWorkerOwner, 
			String strFlag, 
			String str,
			String strSendFlag,
			int intstart, int intpagesize) throws Exception {
		String strSql = "";
		String strTotsql = "";
		
		String strAttributeFields=odata_OutstockMImpl.getArticleAttrString("1").get(0);
		String strAttributeGroupByFields=odata_OutstockMImpl.getArticleAttrString("2").get(0);
		if(!"".equals(strAttributeFields))
		{
			strAttributeFields+=", ";
			if(strAttributeFields.indexOf("QUALITY")>0)
			{
				strAttributeFields+="f_get_fieldtext('N', 'quality',   sai.QUALITY) textQuality, ";
			}
		}
		
		if(!"".equals(strAttributeGroupByFields))
		{
			strAttributeGroupByFields=","+strAttributeGroupByFields;
		}
		
		
		if(strSendFlag != null && strSendFlag.trim().equals("label")){
			if(strFlag != null && !strFlag.trim().equals("2"))//拣货标签任务回单作业数据
			{
				//增加查询明细委托业主 huangb 20160803
				strSql = " SELECT a.owner_no, " +
						"A.CUST_NO,A.CUST_NAME,A.DELIVER_AREA,A.ENTERPRISE_NO,A.WAREHOUSE_NO,A.SOURCE_NO,A.CONTAINER_NO,A.CONTAINER_TYPE,A.ARTICLE_NO " +
						",A.PACKING_QTY,A.PACKING_UNIT,A.QTY,A.LABEL_NO,A.STOCK_TYPE,A.OPERATE_TYPE,A.OUTSTOCK_TYPE,A.UNBOX_FLAG,A.S_CELL_NO " +
						",A.STOCK_NO,A.DPS_AREA,A.PICK_ORDER,A.D_CELL_NO,A.ARTICLE_QTY,A.REAL_QTY," +
						//add by huangcx at 20160528
						"trunc(A.ARTICLE_QTY/A.packing_qty) as planBox," +
					    "trunc(mod(A.article_qty,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) as planQmin," +
					    "(A.article_qty - trunc(A.article_qty/A.packing_qty) * A.packing_qty - trunc(mod(A.article_qty,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) * BDA.QMIN_OPERATE_PACKING) as planDis," +
		                "trunc(A.REAL_QTY/A.packing_qty) as realBox," +
					    "trunc(mod(A.REAL_QTY,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) as realQmin," +
					    "(A.REAL_QTY - trunc(A.REAL_QTY/A.packing_qty) * A.packing_qty - trunc(mod(A.REAL_QTY,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) * BDA.QMIN_OPERATE_PACKING) as realDis," +
					    //end add
					    "A.S_CONTAINER_NO,A.PICK_TYPE " +
						",A.PRODUCE_DATE,A.EXPIRE_DATE,A.QUALITY,A.IMPORT_BATCH_NO,A.LOT_NO,A.RSV_BATCH1,A.RSV_BATCH2,A.RSV_BATCH3,A.RSV_BATCH4 " +
						",A.RSV_BATCH5,A.RSV_BATCH6,A.RSV_BATCH7,A.RSV_BATCH8,A.TEXTQUALITY,A.BARCODE, "
						+" BDA.OWNER_ARTICLE_NO,"
						+" BDA.ARTICLE_NAME,"
						+" BDA.ARTICLE_ENAME,"
						+" BDA.ARTICLE_IDENTIFIER,"
						+" BDA.ARTICLE_ONAME,"
						+" BDA.ARTICLE_ALIAS, "
						//+"nvl(a.spec, '1*' || a.packing_qty || bda.unit) spec,"
						+" BDA.QMIN_OPERATE_PACKING , "
						+" BDA.RULE_FLAG,"
						+" BDA.ABC,"
						+" BDA.QC_FLAG,"
						+" BDA.MEASURE_MODE,"
						+" BDA.TEMPERATURE_FLAG,"
						+" BDA.VIRTUAL_FLAG,"
						+" BDA.SCAN_FLAG,"
						//+" nvl(a.packing_unit, decode(a.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) UNIT,"
						+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"
						+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"
						+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"
						+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"
						+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"
						+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"
						+" BDA.DOUBLE_CHECK, "
						+" BDA.PICK_EXCESS"
						+" FROM " +
						"(SELECT " +
						//只有摘果才显示具体客户 huangb 20160727
						//"t.cust_no,t.cust_name," +
						"(case when oom.pick_type = '0' then t.cust_no else 'N' end) cust_no," +
						"(case when oom.pick_type = '0' then t.cust_name else 'N' end) cust_name," +
						"d.owner_no,m.deliver_area,D.ENTERPRISE_NO," 
						+" D.warehouse_no,"
						+" D.SOURCE_NO,"
						+" D.CONTAINER_NO,"
						+" D.CONTAINER_TYPE,"
						+" D.ARTICLE_NO,"
						+" ooD.PACKING_QTY,bap.packing_unit,bap.spec,"
						+" SUM(D.QTY) QTY,"
						+" M.LABEL_NO,"
						+" M.STOCK_TYPE, "
						+" OOM.OPERATE_TYPE,"
						+" OOM.OUTSTOCK_TYPE,"
						+" nvl(ood.unbox_flag, 0) unbox_flag,"
						+" OOD.S_CELL_NO,nvl(cdd.stock_no,0)stock_no,nvl(cdd.dps_area,0)dps_area,cell.pick_order,"
						+" OOD.D_CELL_NO,"
						+" SUM(OOD.ARTICLE_QTY) AS ARTICLE_QTY,"
						+" sum(ood.ARTICLE_QTY) REAL_QTY,"
						+" (sum(ood.ARTICLE_QTY)/ood.packing_qty)realBox,"
						+" (sum(ood.article_Qty)/ood.packing_qty) planBox,"
						+" OOD.S_CONTAINER_NO,"
						+" OOM.Pick_Type,"
						+strAttributeFields
						+" min(SAI.BARCODE) as BARCODE "
						+" FROM STOCK_LABEL_D D"
						+" INNER JOIN STOCK_LABEL_M M"
						+" ON M.warehouse_no = D.warehouse_no" 
						+" AND M.ENTERPRISE_NO = D.ENTERPRISE_NO"
						+" AND M.CONTAINER_NO = D.CONTAINER_NO"
						+" AND M.CONTAINER_TYPE = D.CONTAINER_TYPE"
						+" INNER JOIN ODATA_OUTSTOCK_D OOD"
						+" ON M.SOURCE_NO = OOD.OUTSTOCK_NO"
						+" AND M.Enterprise_No = OOD.ENTERPRISE_NO"
						+" AND OOD.warehouse_no = M.warehouse_no"
						+" AND OOD.OWNER_NO = D.OWNER_NO"
						+" AND OOD.DIVIDE_ID = D.DIVIDE_ID" 
						+" and ood.status='10' "
						+" INNER JOIN odata_outstock_m OOM"
						+" ON OOM.warehouse_no = OOD.warehouse_no"
						+" AND OOM.ENTERPRISE_NO = D.ENTERPRISE_NO"
						+" AND OOM.OUTSTOCK_NO = OOD.OUTSTOCK_NO"
						+" left JOIN BDEF_ARTICLE_PACKING BAP"
						+" ON BAP.ARTICLE_NO = D.ARTICLE_NO"
						+" AND BAP.ENTERPRISE_NO=D.ENTERPRISE_NO"
						+" AND BAP.PACKING_QTY = d.PACKING_QTY"
						+" INNER JOIN STOCK_ARTICLE_INFO SAI"
						+" ON D.ARTICLE_NO = SAI.ARTICLE_NO"
						+" AND D.ENTERPRISE_NO=SAI.ENTERPRISE_NO"
						+" AND D.ARTICLE_ID = SAI.ARTICLE_ID   " +
						"  inner join bdef_defcust t on t.owner_no=d.owner_no " +
						" and t.enterprise_no=d.enterprise_no and t.cust_no=d.cust_no "+
					   " inner join cdef_defcell cell   "+ 
	                   " on ood.enterprise_no=cell.enterprise_no " +
	                   "and ood.warehouse_no=cell.warehouse_no " +
	                   "and ood.s_cell_no=cell.cell_no      "+   
	             "  LEFT JOIN cdef_DEFCELL_DPS CDD   "+ 
	                   "           ON ood.enterprise_no = cdd.enterprise_no   "+ 
	                   "          and ood.S_CELL_NO = cdd.CELL_NO   "+ 
	                   "          AND ood.WAREHOUSE_NO = CDD.WAREHOUSE_NO   "+ 
	                   "          AND CDD.USE_TYPE in('1','2')     "+ 
						" WHERE 1=1 %s1 and m.warehouse_no='"+strWarehouseNo+
						"' and m.enterprise_no='"+enterpriseNo+"'"
						+" AND (D.STATUS = '50' OR D.STATUS = '40')"
						+" GROUP BY D.warehouse_no,d.owner_no,"
						+"   D.Enterprise_No,"
						+"   D.SOURCE_NO," +
						//"t.cust_no,t.cust_name," +
						"(case when oom.pick_type = '0' then t.cust_no else 'N' end)," +
						"(case when oom.pick_type = '0' then t.cust_name else 'N' end)," +
						"m.deliver_area,"
						+"   D.CONTAINER_NO,"
						+"   D.CONTAINER_TYPE,"
						+"   D.ARTICLE_NO,"
						+"   ooD.PACKING_QTY,bap.packing_unit,bap.spec,"
						+"   ood.unbox_flag,"
						+"   M.LABEL_NO,"
						+"   M.STOCK_TYPE, "
						+"   OOM.OPERATE_TYPE,"
						+"   OOM.OUTSTOCK_TYPE,"
						+"   OOD.S_CELL_NO,cdd.stock_no,cdd.dps_area,cell.pick_order,"
						+"   OOD.D_CELL_NO," 
						+"   OOD.S_CONTAINER_NO,"
						+"   OOM.Pick_Type, " 
						+"   sai.QUALITY"
						+strAttributeGroupByFields
						+ ") A"
						+" INNER JOIN bdef_defarticle BDA"
						+" ON BDA.ARTICLE_NO = A.ARTICLE_NO"
						+" AND BDA.enterprise_no=A.enterprise_no " +
						"  and bda.owner_no = a.owner_no "
						+" ORDER BY a.owner_no,a.label_no," 
						+" a.D_CELL_NO,"
						+" a.SOURCE_NO,a.stock_no,a.dps_area,a.pick_order,"
						+" a.S_CELL_NO,"
						+" a.ARTICLE_NO ";
			}else if(strFlag != null && strFlag.trim().equals("2")){//拣货标签任务回单历史数据
				strSql = " select  OOD.ENTERPRISE_NO, bda.article_name, bda.owner_article_no as ownerArticleNo," +
						" nvl(ood.unbox_flag,0) unbox_flag," +
						" OOD.S_CELL_NO," +
						" OOD.D_CELL_NO," +
						" ood.article_no," +
						" ood.packing_qty," +
						" SUM(OOD.ARTICLE_QTY) AS ARTICLE_QTY," +
						" sum(ood.REAL_QTY) REAL_QTY," +
						//add by huangcx at 20160528
						" f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingUnit,"+
						" f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
						" f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,bda.unit_packing) Unit,"+
						" f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingSpec,"+
						" f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
						" f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,bda.unit_packing) spec,"+
						"sum(trunc(ood.ARTICLE_QTY/ood.packing_qty)) as planBox," +
					    "sum(trunc(mod(ood.article_qty,ood.packing_qty)/bda.QMIN_OPERATE_PACKING)) as planQmin," +
					    "sum(ood.article_qty - trunc(ood.article_qty/ood.packing_qty) * ood.packing_qty - trunc(mod(ood.article_qty,ood.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as planDis," +
		                "sum(trunc(ood.REAL_QTY/ood.packing_qty)) as realBox," +
					    "sum(trunc(mod(ood.REAL_QTY,ood.packing_qty)/bda.QMIN_OPERATE_PACKING)) as realQmin," +
					    "sum(ood.REAL_QTY - trunc(ood.REAL_QTY/ood.packing_qty) * ood.packing_qty - trunc(mod(ood.REAL_QTY,ood.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as realDis," +
					    //end add
						" OOD.S_CONTAINER_NO, " +
						" ood.exp_date, "
						+strAttributeFields
						+" min(SAI.BARCODE) as BARCODE "
						+" FROM ODATA_OUTSTOCK_DHTY  OOD " +
						" INNER JOIN" +
						" STOCK_ARTICLE_INFO SAI " +
						" ON ooD.ARTICLE_NO = SAI.ARTICLE_NO " +
						" AND OOD.ENTERPRISE_NO=SAI.ENTERPRISE_NO"+
						" AND ooD.ARTICLE_ID = SAI.ARTICLE_ID " +
						" INNER join bdef_defarticle bda " +
						" on bda.article_no=ood.article_no"+
						"  AND bda.enterprise_no=ood.enterprise_no "
						+" WHERE 1=1 %s1 and ood.warehouse_no='"+strWarehouseNo
						+"' and ood.enterprise_no='"+enterpriseNo+"' "
						+" GROUP BY  ood.enterprise_no,"
						+"ood.owner_no,"
						+"bda.article_name," +
						" ood.article_no," +
						" ood.unbox_flag, " +
						" OOD.S_CELL_NO," +
						" OOD.D_CELL_NO," +
						" ood.packing_qty," +
						" bda.qmin_operate_packing," +
						" bda.unit_packing," +
						" ood.exp_date," +
						" OOD.S_CONTAINER_NO, " +
						" bda.owner_article_no,sai.QUALITY "
						+strAttributeGroupByFields;
			}
		}else if(strSendFlag != null && strSendFlag.trim().equals("form"))
		{
			strSql =  " SELECT  " +
					"A.CUST_NO,A.CUST_NAME,A.DELIVER_AREA,A.ENTERPRISE_NO,A.WAREHOUSE_NO,A.SOURCE_NO,A.CONTAINER_NO,A.CONTAINER_TYPE,A.ARTICLE_NO " +
					",A.PACKING_QTY,A.PACKING_UNIT,A.QTY,A.LABEL_NO,A.STOCK_TYPE,A.OPERATE_TYPE,A.OUTSTOCK_TYPE,A.UNBOX_FLAG,A.S_CELL_NO " +
					",A.STOCK_NO,A.DPS_AREA,A.PICK_ORDER,A.D_CELL_NO,A.ARTICLE_QTY,A.REAL_QTY," +
					//add by huangcx at 20160528
					"trunc(A.ARTICLE_QTY/A.packing_qty) as planBox," +
				    "trunc(mod(A.article_qty,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) as planQmin," +
				    "(A.article_qty - trunc(A.article_qty/A.packing_qty) * A.packing_qty - trunc(mod(A.article_qty,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) * BDA.QMIN_OPERATE_PACKING) as planDis," +
	                "trunc(A.REAL_QTY/A.packing_qty) as realBox," +
				    "trunc(mod(A.REAL_QTY,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) as realQmin," +
				    "(A.REAL_QTY - trunc(A.REAL_QTY/A.packing_qty) * A.packing_qty - trunc(mod(A.REAL_QTY,A.packing_qty)/BDA.QMIN_OPERATE_PACKING) * BDA.QMIN_OPERATE_PACKING) as realDis," +
				    //end add
					"A.S_CONTAINER_NO,A.PICK_TYPE " +
					",A.PRODUCE_DATE,A.EXPIRE_DATE,A.QUALITY,A.IMPORT_BATCH_NO,A.LOT_NO,A.RSV_BATCH1,A.RSV_BATCH2,A.RSV_BATCH3,A.RSV_BATCH4 " +
					",A.RSV_BATCH5,A.RSV_BATCH6,A.RSV_BATCH7,A.RSV_BATCH8,A.TEXTQUALITY,A.BARCODE, "
					+" BDA.OWNER_ARTICLE_NO,"
					+" BDA.ARTICLE_NAME,"
					+" BDA.ARTICLE_ENAME,"
					+" BDA.ARTICLE_IDENTIFIER,"
					+" BDA.ARTICLE_ONAME,"
					+" BDA.ARTICLE_ALIAS,"
					//+"nvl(a.spec, '1*' || a.packing_qty || bda.unit) spec,"
					+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"
					+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"
					+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"
					+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"
					+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"
					+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"
					+" BDA.QMIN_OPERATE_PACKING qminOP,"
					+" BDA.RULE_FLAG,"
					+" BDA.ABC,"
					+" BDA.QC_FLAG,"
					+" BDA.MEASURE_MODE,"
					+" BDA.TEMPERATURE_FLAG,"
					+" BDA.VIRTUAL_FLAG,"
					+" BDA.SCAN_FLAG,"
					+" BDA.UNIT,"
					+" BDA.M_GROUP_NO,"
					+" BDA.PICK_EXCESS"
					+" from ( select "
					+" M.enterprise_no,"
					+" M.warehouse_no,"
					+" M.OUTSTOCK_NO,"
					+" M.OPERATE_TYPE, "
					+" M.PICK_TYPE,"
					+" M.TASK_TYPE,"
					+" M.OUTSTOCK_TYPE,"
					+" '' AS LABEL_NO,"
					+" B.OWNER_NO,"
					+" B.D_Cell_No,"
					+" B.S_CELL_NO,"
					+" B.S_CONTAINER_NO,"
					+" B.PICK_CONTAINER_NO,"
					+" B.ARTICLE_NO,"
					+" B.PAsCKING_QTY," 
					+" sum(b.ARTICLE_QTY) ARTICLE_QTY,"
					+"%s9, "
					+" sum(b.article_Qty/b.packing_qty) planBox,"
					+" min(SAI.BARCODE) as BARCODE,"
					+strAttributeFields
					+" BAP.SORTER_FLAG,BAP.spec, "
					+" SUM(nvl(BAP.QPALETTE,999*999) * CDA.PAL_OUT_RATE / 100) MIN_PAL_QTY "
					+" FROM "
					+" %s6 M "
					+" INNER JOIN"
					+" %s5 B "
					+" ON M.warehouse_no = B.warehouse_no "
					+" AND M.OUTSTOCK_NO = B.OUTSTOCK_NO "
					+" AND M.ENTERPRISE_NO = B.ENTERPRISE_NO"
					+" INNER JOIN"
					+" STOCK_ARTICLE_INFO SAI "
					+" ON B.ARTICLE_NO = SAI.ARTICLE_NO "
					+" AND B.ARTICLE_ID = SAI.ARTICLE_ID "
					+" AND B.ENTERPRISE_NO = SAI.ENTERPRISE_NO"
					+" left JOIN"
					+" BDEF_ARTICLE_PACKING BAP "
					+" ON B.ARTICLE_NO = BAP.ARTICLE_NO "
					+" AND B.PACKING_QTY = BAP.PACKING_QTY "
					+" AND B.ENTERPRISE_NO= BAP.ENTERPRISE_NO"
					+" INNER JOIN"
					+" CDEF_DEFCELL CDC "
					+" ON CDC.warehouse_no = B.warehouse_no "
					+" AND CDC.ENTERPRISE_NO = B.ENTERPRISE_NO"
					+" AND CDC.CELL_NO = B.S_CELL_NO "
					+" INNER JOIN"
					+" CDEF_DEFAREA CDA "
					+" ON CDA.warehouse_no = CDC.warehouse_no "
					+" AND CDA.ENTERPRISE_NO = CDC.ENTERPRISE_NO"
					+" AND CDA.WARE_NO = CDC.WARE_NO "
					+" AND CDA.AREA_NO = CDC.AREA_NO "
					+" WHERE 1=1 %s0 %s1 %s2 "
					+" %s8 "
					+" GROUP BY M.enterprise_no"
					+" M.warehouse_no,"
					+" B.OWNER_NO,"
					+" B.D_Cell_No,"
					+" M.OUTSTOCK_NO,"
					+" M.OPERATE_TYPE,"
					+" M.PICK_TYPE,"
					+" M.TASK_TYPE, "
					+" M.OUTSTOCK_TYPE,"
					+" B.S_CELL_NO,"
					+" B.S_CONTAINER_NO,"
					+" B.PICK_CONTAINER_NO,"
					+" B.ARTICLE_NO,"
					+" B.PACKING_QTY,BAP.spec,"					
					+" BAP.SORTER_FLAG " 
					+strAttributeGroupByFields
					+" ) A"
					+" INNER JOIN"
					+" bdef_defarticle BDA "
					+" ON a.ARTICLE_NO = BDA.ARTICLE_NO   "
					+" AND a.enterprise_no=BDA.enterprise_no"
					+" ORDER BY"
					+" a.warehouse_no,"
					+" a.OWNER_NO,"
					+" a.OUTSTOCK_NO,"
					+" a.OPERATE_TYPE,"
					+" a.PICK_TYPE,"
					+" a.TASK_TYPE, "
					+" a.OUTSTOCK_TYPE,"
					+" a.S_CELL_NO,"
					+" a.S_CONTAINER_NO,"
					+" a.PICK_CONTAINER_NO,"
					+" a.ARTICLE_NO,"
					+" a.PACKING_QTY,"
					+" BDA.OWNER_ARTICLE_NO,"
					+" BDA.ARTICLE_NAME,"
					+" BDA.ARTICLE_ENAME,"
					+" BDA.ARTICLE_IDENTIFIER,"
					+" BDA.ARTICLE_ONAME,"
					+" BDA.ARTICLE_ALIAS,"
					+" a.spec,"
					+" BDA.QMIN_OPERATE_PACKING,"
					+" BDA.RULE_FLAG,"
					+" BDA.ABC,"
					+" BDA.QC_FLAG,"
					+" BDA.MEASURE_MODE,"
					+" BDA.TEMPERATURE_FLAG,"
					+" BDA.VIRTUAL_FLAG,"
					+" BDA.SCAN_FLAG,"
					+" BDA.UNIT,"
					+" BDA.M_GROUP_NO ";
			if(strFlag != null && strFlag.trim().equals("2"))
			{
				strSql = strSql.replace("%s5", "ODATA_OUTSTOCK_DHTY "); 
				strSql = strSql.replace("%s6", "ODATA_OUTSTOCK_MHTY ");
				strSql = strSql.replace("%s8", " ");
				strSql=strSql.replace("%s9"," sum(b.REAL_QTY) REAL_QTY ");
			}else{
				strSql = strSql.replace("%s5", "ODATA_OUTSTOCK_D ");
				strSql = strSql.replace("%s6", "ODATA_OUTSTOCK_M ");
				strSql = strSql.replace("%s8", "AND B.STATUS = '10' ");
				strSql=strSql.replace("%s9"," sum(b.ARTICLE_QTY) REAL_QTY ");
			}
		}
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql = strSql.replace("%s0", " and m.enterprise_No='"+enterpriseNo+"' ");
		}else{
			strSql = strSql.replace("%s0", "");
		}
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
		}
		if(strWarehouseNo!=null && !strWarehouseNo.equals(""))
		{
			strSql = strSql.replace("%s2", " and m.warehouse_No='"+strWarehouseNo+"' ");
		}else
		{
			strSql = strSql.replace("%s2", "");
		}
		
		strTotsql = "select count(*) from ("+ strSql +")";
	    List<Odata_OutstockDModel> list=genDao.getListByNativeSql(strSql, Odata_OutstockDModel.class, intstart,intpagesize);
	    Integer count = genDao.getCountByNativeSql(strTotsql);
	    ExtListDataBo<Odata_OutstockDModel> extListBo = new ExtListDataBo<Odata_OutstockDModel>(list, count);
	    return extListBo;
	}
	
	/**
	 * 拣货回单》波次、批次下拉事件
	 * strFlag='1':波次
	 * strFlag='2':批次
	 * strFlag-'3':操作类型
	 * strFlag='4':货主
	 * strFlag='5':下架单号
	 * strSendFlag='label':标签回单
	 * strSendFlag='form':表单回单
	 * strCheckFlag='history':历史单据查询
	 * strCheckFlag='normal':非历史单据查询
	 * strB2CYesOrNo;1传统；2电商
	 */
	@Override
	public List<ComboxBo> getOdata_OutstockMReceiptCombo(String enterpriseNo,String strWarehouseNo,String strWorkerOwner,
			String strFlag, String str,String strSendFlag,String strCheckFlag, 
			String strB2CYesOrNo,int intStart, int intPagesize) {
		String strSql = "";
		//1:取出货发单 出货单别
		if(strFlag != null && strFlag.equals("1"))
		{
			strSql = "select distinct oom.wave_no value,oom.wave_no text," +
					"oom.wave_no dropValue from " +
					"%s6 oom,%s5 ood,odata_locate_batch t where" +
					" ood.enterprise_no = oom.enterprise_no " +
					" and ood.warehouse_no = oom.warehouse_no" +
					" and ood.outstock_no = oom.outstock_no " +
					" and oom.outstock_type in('0')" +
					" and oom.enterprise_no=t.enterprise_no" +
					" and oom.warehouse_no=t.warehouse_no" +
					" and oom.wave_no=t.wave_no " +
					" and ood.batch_no = t.batch_no " +
					" %s7 %s8 " +
					" %s0 %s1 %s2 %s3 %s4 order by oom.wave_no " ;
		}else if(strFlag != null && strFlag.equals("2"))
		{
			strSql = "select distinct ood.batch_no value,ood.batch_no text," +
					"ood.batch_no dropValue from %s5 ood," +
					"%s6 oom where ood.WAREHOUSE_NO=oom.warehouse_no " +
					"and ood.enterprise_no=oom.enterprise_no " +
					"and ood.OUTSTOCK_NO=oom.outstock_no " +
					"and oom.outstock_type in( '0') %s7 " +
					" %s0 %s1 %s2 %s3 %s4 order by ood.batch_no " ;	
		}else if(strFlag != null && strFlag.equals("3"))
		{
			strSql = "select distinct oom.OPERATE_TYPE value,wdf.text text," +
					"'['|| ltrim(oom.OPERATE_TYPE)||']'||wdf.text dropValue " +
					"from %s6 oom,wms_deffieldval wdf,%s5 ood " +
					" where ood.WAREHOUSE_NO=oom.warehouse_no " +
					"and ood.enterprise_no=oom.enterprise_no " +
					"and ood.OUTSTOCK_NO=oom.outstock_no and oom.outstock_type in ( '0')" +
					" %s7 and oom.OPERATE_TYPE=wdf.value " +
					" and wdf.table_name='N' and wdf.colname='OPERATE_TYPE' " +
					" %s0 %s1 %s2 %s3 %s4  order by oom.operate_type" ;
		}
		else if(strFlag != null && strFlag.equals("4"))
		{
			//有混合业主拣货 huangb 20160803
			strSql = " select distinct oom.owner_no value, " + 
                     "                 nvl(t2.owner_name,'ALL') text, " + 
                     "                 '[' || ltrim(oom.owner_no) || ']' || nvl(t2.owner_name,'ALL') dropValue " + 
                     "   from %s6 oom,%s5 ood,bdef_defowner t2 " + 
                     "  where ood.enterprise_no = oom.enterprise_no " + 
                     "    and ood.warehouse_no = oom.warehouse_no " + 
                     "    and ood.outstock_no = oom.outstock_no " + 
                     "    and t2.enterprise_no(+) = oom.enterprise_no " + 
                     "    and t2.owner_no(+) = oom.owner_no " + 
                     "    and oom.OUTSTOCK_TYPE in ('0') " + 
                     "    %s0 %s1 %s2 %s3 %s4 %s7 " + 
                     "  order by oom.owner_no desc ";
		}
		else if(strFlag != null && strFlag.equals("5"))
		{
			strSql = "select distinct oom.outstock_no value, oom.outstock_no text,  " +
					" oom.outstock_no dropValue  " +
					" from %s6 oom, %s5 ood " +
					" where ood.WAREHOUSE_NO=oom.warehouse_no " +
					" and ood.enterprise_no=oom.enterprise_no " +
					" and ood.OUTSTOCK_NO=oom.outstock_no " +
					" and oom.outstock_type in( '0') %s7 " +
					" %s0 %s1 %s2 %s3 %s4 order by oom.outstock_no ";
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals("")){
			strSql = strSql.replace("%s0", " and oom.enterprise_no = '" + enterpriseNo + "' ");
		}
		
		if(str != null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
		}
		if(strWarehouseNo != null && !strWarehouseNo.equals(""))
		{
			strSql = strSql.replace("%s2", " and oom.warehouse_No = '" + strWarehouseNo + "' ");
		}else
		{
			strSql = strSql.replace("%s2", "");
		}
		if(strWorkerOwner != null && !strWorkerOwner.equals(""))
		{
			strSql = strSql.replace("%s3", " and ood.owner_no in("+strWorkerOwner+") ");
		}else{
			strSql = strSql.replace("%s3", " and 1=2 ");
		}
		if(strSendFlag != null && !strSendFlag.equals(""))
		{
			if(strSendFlag.trim().equals("label"))
			{
				strSql = strSql.replace("%s4", " and oom.task_type='2' ");
			}else if(strSendFlag.trim().equals("form"))
			{
				strSql = strSql.replace("%s4", " and oom.task_type='1' ");
			}
			
		}else{
			strSql = strSql.replace("%s4", " ");
		}
		
		if(strCheckFlag != null && strCheckFlag.equals("history"))
		{
			strSql = strSql.replace("%s5", "odata_outstock_dhty");
			strSql = strSql.replace("%s6", "odata_outstock_mhty");
			strSql = strSql.replace("%s7", " ");
		}else{
			strSql = strSql.replace("%s5", "odata_outstock_d");
			strSql = strSql.replace("%s6", "odata_outstock_m");
			strSql = strSql.replace("%s7", "and oom.status<'13' ");
		}
		if(strB2CYesOrNo!= null && strB2CYesOrNo.equals("2")){//电商
			strSql = strSql.replace("%s8", " and t.industry_flag='2' ");
		}else{//普通
			strSql = strSql.replace("%s8", " and t.industry_flag='1' ");
		}
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	/**
	 * 拣货表单回单》回单
	 */
	@Override
	public MsgRes save(String enterpriseNo,String workerNo,String strOutstockName,String strPickType,
			String str,String workSpaceNo) throws Exception 
	{
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		List<Odata_OutstockDModel> ood=JSON.parseArray(str,Odata_OutstockDModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		for (Odata_OutstockDModel i : ood) {
			List  inList=new ArrayList();
			inList.add(enterpriseNo);
			inList.add(i.getWarehouseNo());
			inList.add(i.getOutstockNo());
			inList.add(i.getCustContainerNo());
			inList.add(i.getArticleNo());
			inList.add(i.getPackingQty());
			inList.add(i.getSCellNo());
			inList.add(i.getArticleQty());
			inList.add(i.getRealQty());
			inList.add(StringUtil.isStrEmpty(i.getQuality())?"0":i.getQuality());
			inList.add(i.getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):i.getProduceDate());
			inList.add(i.getExpireDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):i.getExpireDate());
			inList.add(StringUtil.isStrEmpty(i.getLotNo())?"N":i.getLotNo());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch1())?"N":i.getRsvBatch1());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch2())?"N":i.getRsvBatch2());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch3())?"N":i.getRsvBatch3());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch4())?"N":i.getRsvBatch4());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch5())?"N":i.getRsvBatch5());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch6())?"N":i.getRsvBatch6());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch7())?"N":i.getRsvBatch7());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch8())?"N":i.getRsvBatch8());
			inList.add(workSpaceNo);//码头strDock_No
			inList.add(workerNo);//回单人--系统登录人
			inList.add(strOutstockName);//拣货人员strUserID
			inList.add(strOutstockName);
			
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_odata_lich.P_Save_Odata_Outstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		
		return new MsgRes(true,"回单成功！",null);
	}
	
	//拣货任务标签回单
	@Override
	public MsgRes saveLabel(String enterpriseNo,String strWorkerNo, String strOutstockName,
			String strPickType, String str, String workSpaceNo)
			throws Exception
	{
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		List<Odata_OutstockDModel> ood=JSON.parseArray(str,Odata_OutstockDModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		for (Odata_OutstockDModel i : ood) {
			List  inList=new ArrayList();
			inList.add(enterpriseNo);
			inList.add(i.getWarehouseNo());
			inList.add(i.getOutstockNo());
			inList.add(i.getCustContainerNo());
			
			inList.add(i.getLabelNo());
			
			inList.add(i.getArticleNo());
			inList.add(i.getPackingQty());
			inList.add(i.getSCellNo());
			inList.add(i.getArticleQty());
			inList.add(i.getRealQty());
			inList.add(StringUtil.isStrEmpty(i.getQuality())?"0":i.getQuality());
			inList.add(i.getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):i.getProduceDate());
			inList.add(i.getExpireDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):i.getExpireDate());
			inList.add(StringUtil.isStrEmpty(i.getLotNo())?"N":i.getLotNo());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch1())?"N":i.getRsvBatch1());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch2())?"N":i.getRsvBatch2());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch3())?"N":i.getRsvBatch3());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch4())?"N":i.getRsvBatch4());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch5())?"N":i.getRsvBatch5());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch6())?"N":i.getRsvBatch6());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch7())?"N":i.getRsvBatch7());
			inList.add(StringUtil.isStrEmpty(i.getRsvBatch8())?"N":i.getRsvBatch8());
			inList.add(workSpaceNo);//码头strDock_No
			inList.add(strWorkerNo);//回单人--系统登录人
			inList.add(strOutstockName);//拣货人员strUserID
			inList.add(strOutstockName);

			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_odata_lich.P_TaskLabelSave_Odata_Outstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}

		return new MsgRes(true,"回单成功！",null);
	}
	/***
	 * 扫描标签号 获取拣货回单信息	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes IdataCheckLabelNo(String strRecvData) throws Exception {		
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strLabelNo=reqMod.getReqObj();
		String strUserId =reqMod.getFieldEX1();
		//校验标签号
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		List  inList = new ArrayList();
		
		outList.add("S");
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strLabelNo);
		inList.add(strUserId);
		//支持下架单号回单
		resultList=genDao.execProc(inList, outList, "pkcheck_odata.P_CheckLabelNo");
		if(resultList.get(2).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(2).toString());
		}
		//huangb 20160721
		strLabelNo = resultList.get(0).toString();
		//huangb 20160720
		String strSql0 = "SELECT * FROM STOCK_LABEL_M LM, ODATA_OUTSTOCK_M M " +
				" WHERE LM.WAREHOUSE_NO = M.WAREHOUSE_NO AND LM.ENTERPRISE_NO = M.ENTERPRISE_NO AND LM.SOURCE_NO = M.OUTSTOCK_NO" +
				" AND LM.LABEL_NO = '"+strLabelNo+"' AND LM.WAREHOUSE_NO='"+strWarehouseNo+"' AND LM.ENTERPRISE_NO ='"+strEnterpriseNo+"'"
				+" AND M.PICK_TYPE = '2'";
		List list1 = genDao.getListByNativeSql(strSql0);
		if (list1.size() > 0) {
			msgRes.setIsSucc(false);
			msgRes.setMsg("当前界面不支持边拣边分回单功能！");
			return msgRes;
		}
		
	    strSql0 = "SELECT * FROM STOCK_LABEL_M LM, ODATA_OUTSTOCK_M M " +
				" WHERE LM.WAREHOUSE_NO = M.WAREHOUSE_NO AND LM.ENTERPRISE_NO = M.ENTERPRISE_NO AND LM.SOURCE_NO = M.OUTSTOCK_NO" +
				" AND LM.LABEL_NO = '"+strLabelNo+"' AND LM.WAREHOUSE_NO='"+strWarehouseNo+"' AND LM.ENTERPRISE_NO ='"+strEnterpriseNo+"'" +
				" AND M.TASK_TYPE = '0' AND M.OPERATE_TYPE = 'C'";
		List list0 = genDao.getListByNativeSql(strSql0);
		if (list0.size() > 0) {
			msgRes.setIsSucc(false);
			msgRes.setMsg("箱型流水标签请在C型拣货回单界面处理！");
			return msgRes;
		}
		
		//取拣货数据
		Map<Integer, OutStockDateAnswerModel> map=new HashMap<Integer, OutStockDateAnswerModel>();
		ListOutStockAnswerModel listOutStockMod=new ListOutStockAnswerModel();
		String strSql="SELECT   "+
				"    A.*, CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER, "+
				"    BDA.OWNER_ARTICLE_NO,  "+
				"    BDA.ARTICLE_NAME,  "+
				"    BDA.ARTICLE_ENAME,  "+
				"    BDA.ARTICLE_IDENTIFIER,  "+
				"    BDA.ARTICLE_ONAME,  "+
				"    BDA.ARTICLE_ALIAS,  "
				//"nvl(a.spec, '1*' || a.packQty || bda.unit) spec,"+
				+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingUnit,"
				+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"
				+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"
				+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingSpec,"
				+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"
				+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"+
				//"    BDA.SPEC,  "+
				"    BDA.QMIN_OPERATE_PACKING,BDA.UNIT_PACKING, "+
				"    BDA.RULE_FLAG,  "+
				"    BDA.ABC,  "+
				"    BDA.QC_FLAG,  "+
				"    BDA.MEASURE_MODE,  "+
				"    BDA.TEMPERATURE_FLAG,  "+
				"    BDA.VIRTUAL_FLAG,  "+
				"    BDA.SCAN_FLAG,  "+
				"    BDA.DOUBLE_CHECK,  "+
//				"    BDA.M_GROUP_NO,  "+
				"    BDA.PICK_EXCESS," +
				"    BDA.EXPIRY_DAYS,CDC.DISP_CELL_NO DISPCELLNO   "+
				"FROM  "+
				"    (SELECT D.ENTERPRISE_NO, "+
				"        D.warehouse_no,  "+
				"        D.SOURCE_NO, BAP.SPEC, "+
				"        D.CONTAINER_NO,  "+
				"        D.CONTAINER_TYPE,  "+
				"        D.ARTICLE_NO,  "+
				"        D.PACKING_QTY as packQty,  "+
				"        SUM(D.QTY) QTY,  "+
				"        M.LABEL_NO,  "+
				"        M.STOCK_TYPE,  "+
				"        OOM.OPERATE_TYPE,  "+
				"        OOM.OUTSTOCK_TYPE,  "+
				"        nvl(ood.unbox_flag,  "+
				"        0) unbox_flag,  "+
				"        OOD.S_CELL_NO,  "+
				"        OOD.D_CELL_NO,  "+
				"        SUM(OOD.ARTICLE_QTY) AS ARTICLE_QTY,  "+
				"        sum(ood.REAL_QTY) REAL_QTY,  "+
				"        sum(ood.REAL_QTY/d.packing_qty)realBox,  "+
				"        sum(ood.article_Qty/d.packing_qty) planBox,  "+
				"        OOD.S_CONTAINER_NO,  "+
				"        OOM.Pick_Type,  "+
				"        min(SAI.BARCODE) as BARCODE,  "+
				"        min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO,  "+
				"        min(sai.RSV_BATCH1) as RSV_BATCH1,  "+
				"        min(sai.RSV_BATCH2) as RSV_BATCH2,  "+
				"        min(sai.RSV_BATCH3) as RSV_BATCH3,  "+
				"        min(sai.RSV_BATCH4) as RSV_BATCH4,  "+
				"        min(sai.RSV_BATCH5) as RSV_BATCH5,  "+
				"        min(sai.RSV_BATCH6) as RSV_BATCH6,  "+
				"        min(sai.RSV_BATCH7) as RSV_BATCH7,  "+
				"        min(sai.RSV_BATCH8) as RSV_BATCH8,  "+
				"        to_char(sai.EXPIRE_DATE,'yyyymmdd') EXPIRE_DATE,  "+
				"        sai.LOT_NO,  "+
				"        to_char(sai.PRODUCE_DATE,'yyyymmdd') PRODUCE_DATE,  "+
				"        sai.QUALITY ,  "+
				"        f_get_fieldtext('N',  "+
				"        'quality',  "+
				"        sai.QUALITY) textQuality," +
				"        OOD.OUTSTOCK_NO," +
				"        OOD.OWNER_NO       "+
				"    FROM  "+
				"        STOCK_LABEL_D  D   "+
				"    INNER JOIN  "+
				"        STOCK_LABEL_M  M   "+
				"            ON M.warehouse_no = D.warehouse_no   " +
				"            and m.enterprise_no = d.enterprise_no "+
				"            AND M.CONTAINER_NO = D.CONTAINER_NO   "+
				"            AND M.CONTAINER_TYPE = D.CONTAINER_TYPE   "+
				"    INNER JOIN  "+
				"        ODATA_OUTSTOCK_D  OOD   "+
				"            ON M.SOURCE_NO = OOD.OUTSTOCK_NO   "+
				"            and m.enterprise_no = ood.enterprise_no "+
				"            AND OOD.warehouse_no = M.warehouse_no   "+
				"            AND OOD.OWNER_NO = D.OWNER_NO   "+
				"            AND OOD.DIVIDE_ID = D.DIVIDE_ID    "+
				"            and ood.status='10'    "+
				"    INNER JOIN  "+
				"        ODATA_OUTSTOCK_M  OOM   "+
				"            ON OOM.warehouse_no = OOD.warehouse_no   "+
				"            and OOM.enterprise_no = OOD.enterprise_no "+
				//有混合委托业主情况 头档和明细的委托业主不能关联 huangb 20160810
				//"            and OOM.owner_no = OOD.owner_no "+
				"            AND OOM.OUTSTOCK_NO = OOD.OUTSTOCK_NO   "+
				"    left JOIN  "+
				"        BDEF_ARTICLE_PACKING BAP   "+
				"            ON BAP.ARTICLE_NO = D.ARTICLE_NO   "+
				"            AND BAP.PACKING_QTY = d.PACKING_QTY   "+
				"    INNER JOIN  "+
				"        STOCK_ARTICLE_INFO SAI   "+
				"            ON D.ARTICLE_NO = SAI.ARTICLE_NO   "+
				"            AND D.ARTICLE_ID = SAI.ARTICLE_ID   "+
				"    WHERE  "+
				"        1=1    "+
				"        and m.label_no='"+strLabelNo+"'  "+
				"        and m.warehouse_No='"+strWarehouseNo+"'    "+
				"        AND (  "+
				"            D.STATUS = '50'   "+
				"            OR D.STATUS = '50'   "+
				"            OR D.STATUS = '40'  "+
				"        )   "+
				"    GROUP BY D.ENTERPRISE_NO, "+
				"        D.warehouse_no,  "+
				"        D.SOURCE_NO,  "+
				"        D.CONTAINER_NO,  "+
				"        D.CONTAINER_TYPE,  "+
				"        D.ARTICLE_NO,  "+
				"        D.PACKING_QTY,  "+
				"        ood.unbox_flag,  "+
				"        M.LABEL_NO, BAP.SPEC, "+
				"        M.STOCK_TYPE,  "+
				"        OOM.OPERATE_TYPE,  "+
				"        OOM.OUTSTOCK_TYPE,  "+
				"        OOD.S_CELL_NO,  "+
				"        OOD.D_CELL_NO,  "+
				"        OOD.S_CONTAINER_NO,  "+
				"        OOM.Pick_Type,  "+
				"        sai.EXPIRE_DATE,  "+
				"        sai.LOT_NO,  "+
				"        sai.PRODUCE_DATE,  "+
				"        sai.QUALITY, " +
				"        OOD.OUTSTOCK_NO," +
				"        OOD.OWNER_NO  ) A   "+
				" INNER JOIN  "+
				"    bdef_defarticle BDA   "+
				"        ON BDA.ARTICLE_NO = A.ARTICLE_NO  and BDA.OWNER_NO = A.OWNER_NO " +
				//关联储位表 根据下架储位PICK_ORDER排序
				" INNER JOIN CDEF_DEFCELL CDC ON CDC.ENTERPRISE_NO = A.ENTERPRISE_NO AND CDC.WAREHOUSE_NO = A.WAREHOUSE_NO AND CDC.CELL_NO = A.S_CELL_NO "+
				"ORDER BY  "+
				"    a.warehouse_no,  "+
				"    a.SOURCE_NO, CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER, "+
				"    a.S_CELL_NO,  "+
				"    a.CONTAINER_NO,  "+
				"    a.CONTAINER_TYPE,  "+
				"    a.ARTICLE_NO,  "+
				"    a.D_CELL_NO  ";
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
	@Override
	public MsgRes tscPTaskLabelSaveOdataOutstock(String strWarehoseNo  ,
			String strOutstockNo  ,
			String strFixLabelNo  ,
			String strArticleNo  ,
			Double nPackingQTY  ,
			String strSCellNo  ,
			Double nRealQTY  ,
			String strQuality  ,
			Date dtProduceDate  ,
			Date dtExpireDate  ,
			String strLotNo  ,
			String strRsvBatch1  ,
			String strRsvBatch2  ,
			String strRsvBatch3  ,
			String strRsvBatch4  ,
			String strRsvBatch5  ,
			String strRsvBatch6  ,
			String strRsvBatch7  ,
			String strRsvBatch8  ,
			String strDockNo  ,
			String strUserID  ,
			String strOutstockID  ,
			String strInstockID) throws Exception {
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		inList.add(strWarehoseNo);
		inList.add(strOutstockNo);
		inList.add(strFixLabelNo);
		inList.add(strArticleNo);
		inList.add(nPackingQTY);
		inList.add(strSCellNo);
		inList.add(nRealQTY);		
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRsvBatch1)?"N":strRsvBatch1);
		inList.add(StringUtil.isStrEmpty(strRsvBatch2)?"N":strRsvBatch2);
		inList.add(StringUtil.isStrEmpty(strRsvBatch3)?"N":strRsvBatch3);
		inList.add(StringUtil.isStrEmpty(strRsvBatch4)?"N":strRsvBatch4);
		inList.add(StringUtil.isStrEmpty(strRsvBatch5)?"N":strRsvBatch5);
		inList.add(StringUtil.isStrEmpty(strRsvBatch6)?"N":strRsvBatch6);
		inList.add(StringUtil.isStrEmpty(strRsvBatch7)?"N":strRsvBatch7);
		inList.add(StringUtil.isStrEmpty(strRsvBatch8)?"N":strRsvBatch8);		
		inList.add(strDockNo);//码头strDock_No
		inList.add(strUserID);//回单人--系统登录人
		inList.add(strOutstockID);//拣货人员strUserID
		inList.add(strInstockID);
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "pklg_odata_lich.P_TaskLabelSave_Odata_Outstock");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList);
	}
	@Override
	public MsgRes tscPOutStockDivide(String strWarehouseNo,
			String strOwnerNo, 
			String strDockNo, 
			String strSourceNo,
			String strSContainerNo, 
			String strAssignNo, 
			String strUserID,
			String strPrintFlag) throws Exception {
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		List  inList = new ArrayList();
		
		outList.add("S");
		inList.add(strWarehouseNo);
		inList.add(strOwnerNo);
		inList.add(strDockNo);//码头strDock_No
		inList.add(strSourceNo);
		inList.add(strSContainerNo);
		inList.add(strAssignNo);//预定分播人员
		inList.add(strUserID);//系统操作人员
		inList.add(strPrintFlag);//是否打印
		resultList=genDao.execProc(inList, outList, "pklg_odata_lich.P_OutStock_Divide");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList);
	}
	/**
	 * lich 20140721
	 * 容器并板
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscPMergeContainer(String strWarehoseNo   , //仓别
			String strScontainerNo       	, //源容器号
			String strDcontainerNo         	, //目的容器号
			String strArticleNo      		, //商品编码
			Double nPackingQty 				, //商品包装
			Double nMoveQty      			, //移动数量
			String strBatchNo         		, //批次号
			String strQuality        		,//品质
			Date dtProduceDate     			,//生产日期
			Date dtExpireDate      			,//到期日期
			String strLotNo          		,//批次号
			String strRsvBatch1     , //预留批属性1
			String strRsvBatch2     , //预留批属性2
			String strRsvBatch3     , //预留批属性3
			String strRsvBatch4     , //预留批属性4
			String strRsvBatch5     , //预留批属性5
			String strRsvBatch6     , //预留批属性6
			String strRsvBatch7     , //预留批属性7
			String strRsvBatch8     , //预留批属性8    
			String strUserId		,//系统操作人员
			String strTerminalFlag	//操作设备
			)
			throws Exception {
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		inList.add(strWarehoseNo);
		inList.add(strScontainerNo);
		inList.add(strDcontainerNo);
		inList.add(strArticleNo);
		inList.add(nPackingQty);
		inList.add(nMoveQty);
		/*inList.add(strBatchNo);*/		
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRsvBatch1)?"N":strRsvBatch1);
		inList.add(StringUtil.isStrEmpty(strRsvBatch2)?"N":strRsvBatch2);
		inList.add(StringUtil.isStrEmpty(strRsvBatch3)?"N":strRsvBatch3);
		inList.add(StringUtil.isStrEmpty(strRsvBatch4)?"N":strRsvBatch4);
		inList.add(StringUtil.isStrEmpty(strRsvBatch5)?"N":strRsvBatch5);
		inList.add(StringUtil.isStrEmpty(strRsvBatch6)?"N":strRsvBatch6);
		inList.add(StringUtil.isStrEmpty(strRsvBatch7)?"N":strRsvBatch7);
		inList.add(StringUtil.isStrEmpty(strRsvBatch8)?"N":strRsvBatch8);
		inList.add(strUserId);//回单人--系统登录人
		inList.add(strTerminalFlag);//
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_merge_container");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList); 
	}
	/**
	 *Rf拣货 确认
	 */
	@Override
	public MsgRes tscOmPickReceipt(String strRecvData)
			throws Exception {		
		//请求结构转换
		OdataOutStockRequestModel reqMod=JSON.parseObject(strRecvData, OdataOutStockRequestModel.class);
		
		//RF拣货回单
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());
		inList.add(reqMod.getOutStockNo());
		inList.add(reqMod.getLabelNo());
		inList.add(reqMod.getDlabelNo());
		inList.add(reqMod.getArticleNo());
		inList.add(reqMod.getPackQty());      
		inList.add(reqMod.getScellNo());
		inList.add(reqMod.getArticleQty());
		inList.add(reqMod.getOutStockQty());
		inList.add(StringUtil.isStrEmpty(reqMod.getQuality())?"0":reqMod.getQuality());
		inList.add(DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"));
		inList.add(DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"));
		inList.add(StringUtil.isStrEmpty(reqMod.getLotNo())?"N":reqMod.getLotNo());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());
		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());
		inList.add("001");//strDock_No
		inList.add(reqMod.getUserId());//回单人--系统登录人
		inList.add(reqMod.getUserId());//回单人--系统登录人
		inList.add(reqMod.getUserId());//回单人--系统登录人
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_LICH.P_OmPickReceipt");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList); 
	}
	
	public MsgRes CheckWorkerNo(String strRecvData)throws Exception{		 
		 MsgRes resultRes=new MsgRes();	 
		String sql = "SELECT worker_no FROM BDEF_DEFWORKER WHERE worker_no='"+strRecvData+"'";
	    List<String> list=genDao.getListByNativeSql(sql);		 
		if(list.size()==0)
		{			
				return new MsgRes(false,"该员工号不存在！","该员工号不存在！");			 
		}		 			
		resultRes.setIsSucc(true); 
		resultRes.setMsg("成功");
		resultRes.setObj(list.get(0));		 
		return resultRes;
	}
	
	private MsgRes tscPCheckMerge(String strWarehouseNo,
			String strSLabelNo,
			String strDLabelNo)throws Exception{
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		inList.add(strWarehouseNo);
		inList.add(strSLabelNo);
		inList.add(strDLabelNo);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.p_check_merge");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList); 
	}
	@Override
	public MsgRes IdataCheckBarcode(String strRecvData) throws Exception {
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
	 *Rf拣货回单 sunl 2016年2月25日 一箱一标签
	 */
	@Override
	public MsgRes tscOmReceiptPicking(String strRecvData) throws Exception {		
		//请求结构转换
		OdataOutStockRequestModel reqMod=JSON.parseObject(strRecvData, OdataOutStockRequestModel.class);
		
		//这里需要校验当前的下架单号是否是有效单号
		String strSql = "SELECT m.outstock_no FROM  odata_outstock_m m " +
				"WHERE m.operate_type='C' and m.task_type=0 " +
				"and m.outstock_no = '"+reqMod.getOutStockNo()+"' " +
				"AND M.WAREHOUSE_NO='"+reqMod.getWarehouseNo()+"' " +
				"AND M.ENTERPRISE_NO ='"+reqMod.getEnterpriseNo()+"' ";

		List list=genDao.getListByNativeSql(strSql);
		if(list.size() == 0 || list.get(0).toString() == "")
		{
			throw new Exception("N|下架单号无效！");
		}
		
		//RF拣货回单 调底层过程
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		List  inList=new ArrayList();
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());
		inList.add(reqMod.getOutStockNo()); 
		inList.add(reqMod.getUserId());//回单人--系统登录人
		inList.add(reqMod.getUserId());//回单人--系统登录人
		inList.add(reqMod.getUserId());//回单人--系统登录人
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_LICH.p_Save_all_outstock");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"",resultList); 
	}
	
	/**
	 *Rf拣货回单 取下架单数据 sunl 2016年3月8日 一箱一标签
	 */
	@Override
	public MsgRes tscOmGetOutStockInfo(String strRecvData) throws Exception {		
		//请求结构转换
		OdataOutStockRequestModel reqMod=JSON.parseObject(strRecvData, OdataOutStockRequestModel.class);

		MsgRes msgRes = new MsgRes();
		//这里需要校验当前的下架单号是否是有效单号
		String strSql = "SELECT m.outstock_no FROM  odata_outstock_m m WHERE  m.operate_type='C' and m.task_type=0 and m.outstock_no = '"+reqMod.getOutStockNo()+"' AND M.WAREHOUSE_NO='"+reqMod.getWarehouseNo()+"' AND M.ENTERPRISE_NO ='"+reqMod.getEnterpriseNo()+"'";

		List list=genDao.getListByNativeSql(strSql);
		if(list.size() == 0 || list.get(0).toString() == "")
		{ 
			msgRes.setIsSucc(false); 
			msgRes.setMsg("N|下架单号无效！");
			return msgRes;
		}
		String strSql1 = "SELECT M.OUTSTOCK_NO,M.OPERATE_TYPE ,SUM(D.ARTICLE_QTY / D.PACKING_QTY) QTY ,COUNT(DISTINCT D.ARTICLE_NO) ARTICLENUM " +
				" FROM  ODATA_OUTSTOCK_M M ,ODATA_OUTSTOCK_D D " +
				" WHERE M.WAREHOUSE_NO = D.WAREHOUSE_NO AND M.ENTERPRISE_NO = D.ENTERPRISE_NO AND M.OUTSTOCK_NO = D.OUTSTOCK_NO " +
				" AND M.OUTSTOCK_NO = '"+reqMod.getOutStockNo()+"' AND M.WAREHOUSE_NO='"+reqMod.getWarehouseNo()+"' AND M.ENTERPRISE_NO ='"+reqMod.getEnterpriseNo()+"' GROUP BY M.OUTSTOCK_NO,M.OPERATE_TYPE";

		List<ODataGetOutStockInfoModel> list1 = genDao.getListByNativeSql(strSql1, ODataGetOutStockInfoModel.class);
		
		if(list1.size() == 0 || list1.get(0).toString() == "")
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg("N|获取下架单信息失败！");
			return msgRes; 
		}
		if(list1.get(0).getOperateType().equals("B"))
		{
			msgRes.setIsSucc(false); 
			msgRes.setMsg("N|当前窗口只能处理C型的回单操作！");
			return msgRes; 
		}
		msgRes.setIsSucc(true); 
		msgRes.setObj(JSONObject.toJSON(list1));
		return msgRes; 
	}

	/**
	 * Rf下架单登记，Add by sunl
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscOutStockRegister(String strRecvData) throws Exception {

		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		String outstockno = reqMod.getReqObj();
		String userid = reqMod.getFieldEX1();
		String warehouseno = reqMod.getWarehouseNo();
		String enterpriseno = reqMod.getEnterpriseNo();
		
		//2、校验当前的员工是否有效！
		String sql2 ="SELECT * FROM bdef_defworker w WHERE w.worker_no ='"+userid+"'";
		List list2 = genDao.getListByNativeSql(sql2);
		if(list2.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("员工号无效！");
			return msgRes;
		}
		
		//1、校验当前的下架单号是否有效！
		String sql1 = "SELECT d.outstock_no FROM odata_outstock_d d WHERE d.outstock_no = '"+outstockno+"' AND d.warehouse_no = '"+warehouseno+"' AND d.enterprise_no='"+enterpriseno+"' " +
				" UNION ALL " +
				" SELECT d.outstock_no FROM odata_outstock_dhty d  WHERE d.outstock_no = '"+outstockno+"' AND d.warehouse_no = '"+warehouseno+"' AND d.enterprise_no='"+enterpriseno+"'";
		List list1 = genDao.getListByNativeSql(sql1);
		
		if(list1.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("下架单号无效！");
			return msgRes;
		}

		//3、校验当前的记录是否已经被登记过！
		String sql3 =" SELECT W.WORKER_NAME FROM ODATA_OUTSTOCK_REGISTER r,BDEF_DEFWORKER W WHERE R.WORKER_NO = W.WORKER_NO and r.outstock_no = '"+outstockno+"' AND  r.warehouse_no = '"+warehouseno+"' AND r.enterprise_no='"+enterpriseno+"' ";
		List list3 = genDao.getListByNativeSql(sql3);
		if(list3.size()>0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("下架单已被 "+list3.get(0).toString()+" 登记！");
			return msgRes;
		}
		
		//4、保存登记数据
		String sql=" INSERT INTO ODATA_OUTSTOCK_REGISTER VALUES('"+outstockno+"','"+userid+"',SYSDATE,'"+warehouseno+"','"+enterpriseno+"')";
		genDao.updateBySql(sql);

		msgRes.setIsSucc(true); 
		msgRes.setMsg("数据新增成功");
		return msgRes;
	}
	/**
	 * 获取下架单所属的商品明细信息，Add by sunl
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes GetOutStockArticleInfo(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		String labelno = reqMod.getReqObj(); 
		String warehouseno = reqMod.getWarehouseNo();
		String enterpriseno = reqMod.getEnterpriseNo();
		String mtype = reqMod.getFieldEX1();//1. 按下架单，2.按客户，3.按波次按门店 
		
		//这里传入的是箱码
		//先校验当前的箱码是否有效
		String sql1 = "SELECT m.label_no FROM stock_label_m m WHERE m.warehouse_no = '"+warehouseno+"' AND m.enterprise_no = '"+enterpriseno+"' AND m.label_no = '"+labelno+"' ";
		List list1 = genDao.getListByNativeSql(sql1);
		if(list1.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该标签号无效或不存在");
			return msgRes;
		} 
		//原SQL备份
//		String sql2 = "SELECT lm.label_no,ld.article_no,a.article_name,ld.source_no OutStockNo FROM stock_label_m lm,stock_label_d ld ,bdef_defarticle a " +
//				" WHERE ld.warehouse_no = '"+warehouseno+"' AND ld.enterprise_no = '"+enterpriseno+"' " +
//				" AND ld.warehouse_no = lm.warehouse_no AND lm.enterprise_no = ld.enterprise_no AND lm.container_no = ld.container_no " +
//				" and ld.enterprise_no = a.enterprise_no and ld.article_no = a.article_no " +
//				" AND EXISTS (SELECT 1 FROM stock_label_m m,stock_label_d d " +
//				" WHERE m.label_no = '"+labelno+"' AND m.warehouse_no = '"+warehouseno+"' AND m.enterprise_no ='"+enterpriseno+"' " +
//				" AND m.warehouse_no = d.warehouse_no AND m.enterprise_no = d.enterprise_no AND m.container_no = d.container_no " +
//				" AND d.source_no = ld.source_no)";
		
		//这里需要根据参数决定使用什么查询条件 //1. 按下架单，2.按客户，3.按波次按门店
		String sql2 =  "SELECT lm.label_no,ld.article_no,a.article_name,ld.source_no OutStockNo," +
				" lm.cust_no custNo,lm.wave_no waveNo,c.cust_name custName " +
				" FROM stock_label_m lm,stock_label_d ld ,bdef_defarticle a,bdef_defcust c " +
				" WHERE ld.warehouse_no = '"+warehouseno+"' AND ld.enterprise_no = '"+enterpriseno+"' " +
				" AND ld.warehouse_no = lm.warehouse_no AND lm.enterprise_no = ld.enterprise_no AND lm.container_no = ld.container_no " +
				" and ld.enterprise_no = a.enterprise_no and ld.article_no = a.article_no " +
				" and ld.enterprise_no = c.enterprise_no and ld.cust_no = c.cust_no " +
				" AND EXISTS (SELECT 1 FROM stock_label_m m,stock_label_d d " +
				" WHERE m.label_no = '"+labelno+"' AND m.warehouse_no = '"+warehouseno+"' AND m.enterprise_no ='"+enterpriseno+"' " +
				" AND m.warehouse_no = d.warehouse_no AND m.enterprise_no = d.enterprise_no AND m.container_no = d.container_no " ;
		if(mtype.equals("1"))
		{
			sql2 += " AND d.source_no = ld.source_no) ORDER BY LM.LABEL_NO,LD.ARTICLE_NO";
		}
		else if(mtype.equals("2"))
		{
			sql2 += " AND m.cust_no = lm.cust_no) ORDER BY LM.LABEL_NO,LD.ARTICLE_NO";
		}
		else if(mtype.equals("3"))
		{
			sql2 += " AND m.cust_no = lm.cust_no AND m.wave_no = lm.wave_no) ORDER BY LM.LABEL_NO,LD.ARTICLE_NO";
		}
		else
		{//默认按下架单
			sql2 += " AND d.source_no = ld.source_no) ORDER BY LM.LABEL_NO,LD.ARTICLE_NO";
		}
		
		
		List<OdataOutStockRequestModel> list2 = genDao.getListByNativeSql(sql2, OdataOutStockRequestModel.class);
		if(list2.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("未查询到数据！");
			return msgRes;
		}


		msgRes.setIsSucc(true);
		msgRes.setObj(JSONObject.toJSON(list2));
		msgRes.setMsg("查询成功！");
		return msgRes;
	}
	/**
	 * 拣货索单 校验码头号 sunl 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscCheckClaimDock(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		String dockNo = reqMod.getReqObj(); 
		String warehouseno = reqMod.getWarehouseNo();
		String enterpriseno = reqMod.getEnterpriseNo();
		
		//1. 先校验工作站是否存在
		String sql1 = "SELECT * FROM PNTSET_PRINTER_WORKSTATION D WHERE D.ENTERPRISE_NO = '"+enterpriseno+"' AND D.WAREHOUSE_NO = '"+warehouseno+"' AND D.WORKSTATION_NO = '"+dockNo+"' ";
		List list1 = genDao.getListByNativeSql(sql1);
		if(list1.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该工作站无效或不存在！");
			return msgRes;
		} 

		//2. 先校验工作站是否有任务
		String sql2 = "SELECT * FROM ODATA_OUTSTOCK_M M WHERE M.ENTERPRISE_NO = '"+enterpriseno+"' AND M.WAREHOUSE_NO = '"+warehouseno+"' AND M.DOCK_NO = '"+dockNo+"' AND M.PRINT_STATUS = '0' ";
		List list2 = genDao.getListByNativeSql(sql2);
		if(list2.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("该工作站不存在拣货任务！");
			return msgRes;
		} 

		msgRes.setIsSucc(true);
		msgRes.setMsg("工作站校验通过！");
		return msgRes;
	}
	/**
	 * 拣货索单 索单 sunl 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscClaimOrder(String strRecvData) throws Exception {
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		MsgRes msgRes=new MsgRes();
		String userNo = reqMod.getReqObj(); 
		String warehouseno = reqMod.getWarehouseNo();
		String enterpriseno = reqMod.getEnterpriseNo();
		String outStockType = reqMod.getFieldEX1(); //任务类型 :B or C 
		String dockNo = reqMod.getFieldEX2(); //任务类型 :B or C 
		
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");//错误信息
		outList.add("S");//下架单号
		
		List  inList=new ArrayList();
		inList.add(enterpriseno);
		inList.add(warehouseno); 
		inList.add(userNo);//索单人
		inList.add(outStockType);//任务类型 
		inList.add(dockNo);//码头号 
		
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_LICH.P_ClaimOrder");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}

		msgRes.setIsSucc(true);
		msgRes.setObj(JSONObject.toJSON(resultList.get(1)));
		msgRes.setMsg("索单成功！");
		return msgRes; 
	}
	/***
	 * 扫描标签号 获取拣货回单信息(边拣边分)	
	 */
	@Override
	public MsgRes IdataCheckLabelNoExpNo(String strRecvData) throws Exception {
		
		
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strLabelNo=reqMod.getReqObj();
		String strFieldEX1=reqMod.getFieldEX1();
		String strDivideType = reqMod.getFieldEX3();
		//校验标签号
		List  outList = new ArrayList();
		List  resultList = new ArrayList();
		List  inList = new ArrayList();
		outList.add("S");
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strLabelNo);
		inList.add(strFieldEX1);
		System.out.print(inList);
		resultList=genDao.execProc(inList, outList, "pkcheck_odata.P_CheckLabelNo");
		if(resultList.get(2).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(2).toString());
		}
		strLabelNo = resultList.get(0).toString();
		String strSql0 = "SELECT * FROM STOCK_LABEL_M LM, ODATA_OUTSTOCK_M M " +
				" WHERE LM.WAREHOUSE_NO = M.WAREHOUSE_NO AND LM.ENTERPRISE_NO = M.ENTERPRISE_NO AND LM.SOURCE_NO = M.OUTSTOCK_NO" +
				" AND LM.LABEL_NO = '"+strLabelNo+ 
				"' AND LM.WAREHOUSE_NO='"+strWarehouseNo+"' AND LM.ENTERPRISE_NO ='"+strEnterpriseNo+"'"
		+" AND M.PICK_TYPE = '2'";
		List list0 = genDao.getListByNativeSql(strSql0);
		if (list0.size() <= 0) {
			msgRes.setIsSucc(false);
		
			msgRes.setMsg("该拣货类型不是边拣边分！");
			return msgRes;
		}
		
		//取拣货数据
		Map<Integer, OutStockDateAnswerModel> map=new HashMap<Integer, OutStockDateAnswerModel>();
		ListOutStockAnswerModel listOutStockMod=new ListOutStockAnswerModel();
		
		String strSql="SELECT"+
				  " A.*,"+
			      " CDC.WARE_NO,"+
			      " CDC.AREA_NO,"+
			      " CDC.STOCK_NO,"+
			      " CDC.PICK_ORDER,'"+
			       resultList.get(1).toString() + "'  divideNo,"+
			      " BDA.OWNER_ARTICLE_NO,"+
			      " BDA.ARTICLE_NAME,"+
			      " BDA.ARTICLE_ENAME,"+
			      " BDA.ARTICLE_IDENTIFIER,"+
			      " BDA.ARTICLE_ONAME,"+
			      " BDA.ARTICLE_ALIAS,"+
			      " f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingUnit,"+
			      " f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
			      "  f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"+
			      "  f_get_spec(a.enterprise_no, a.owner_no, a.article_no, a.packQty) packingSpec,"+
			      " f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
			      " f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"+
			      " BDA.QMIN_OPERATE_PACKING,"+
			      " BDA.UNIT_PACKING,"+
			      "  BDA.RULE_FLAG,"+
			      " BDA.ABC,"+
			      " BDA.QC_FLAG,"+
			      "BDA.MEASURE_MODE,"+
			      " BDA.TEMPERATURE_FLAG,"+
			      " BDA.VIRTUAL_FLAG,"+
			      " BDA.SCAN_FLAG,"+
			      " BDA.DOUBLE_CHECK,"+
			      " BDA.PICK_EXCESS,"+
			      " BDA.EXPIRY_DAYS,"+
			      " CDC.DISP_CELL_NO DISPCELLNO"+
			 " FROM (SELECT D.ENTERPRISE_NO,"+
			              "   D.warehouse_no,"+
			              "   D.SOURCE_NO,"+
			              "   BAP.SPEC,"+
			              "   D.CONTAINER_NO,"+
			              "   D.CONTAINER_TYPE,"+
			              "  D.ARTICLE_NO,"+
			              "  D.PACKING_QTY as packQty,"+
			              "  SUM(D.QTY) QTY,"+
			              "  M.LABEL_NO,"+
			              "  M.STOCK_TYPE,"+
			              "  OOM.OPERATE_TYPE,"+
			              "  OOM.OUTSTOCK_TYPE,"+
			              "  nvl(ood.unbox_flag, 0) unbox_flag,"+
			              "  OOD.S_CELL_NO,"+
			              " OOD.D_CELL_NO,"+
			              "  SUM(OOD.ARTICLE_QTY)-sum(ood.REAL_QTY) AS ARTICLE_QTY,"+
			              "  sum(ood.REAL_QTY) REAL_QTY,"+
			              "  sum(ood.REAL_QTY / d.packing_qty) realBox,"+
			              "  sum(ood.article_Qty / d.packing_qty) planBox,"+
			              "  OOD.S_CONTAINER_NO,"+
			              " OOM.Pick_Type,"+
			              "  min(SAI.BARCODE) as BARCODE,"+
			              " min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO,"+
			              " min(sai.RSV_BATCH1) as RSV_BATCH1,"+
			              " min(sai.RSV_BATCH2) as RSV_BATCH2,"+
			              " min(sai.RSV_BATCH3) as RSV_BATCH3,"+
			              " min(sai.RSV_BATCH4) as RSV_BATCH4,"+
			              " min(sai.RSV_BATCH5) as RSV_BATCH5,"+
			              " min(sai.RSV_BATCH6) as RSV_BATCH6,"+
			              " min(sai.RSV_BATCH7) as RSV_BATCH7,"+
			              " min(sai.RSV_BATCH8) as RSV_BATCH8,"+
			              " to_char(sai.EXPIRE_DATE, 'yyyymmdd') EXPIRE_DATE,"+
			              " sai.LOT_NO,"+
			              " to_char(sai.PRODUCE_DATE, 'yyyymmdd') PRODUCE_DATE,"+
			               " sai.QUALITY,"+
			             "  f_get_fieldtext('N', 'quality', sai.QUALITY) textQuality,"+
			             "  OOD.OUTSTOCK_NO,"+
			             "  OOD.OWNER_NO"+
			     " FROM STOCK_LABEL_D D"+
			     " INNER JOIN STOCK_LABEL_M M"+
			        " ON M.warehouse_no = D.warehouse_no"+
			        " AND M.CONTAINER_NO = D.CONTAINER_NO"+
			        " AND M.CONTAINER_TYPE = D.CONTAINER_TYPE"+
			        " INNER JOIN ODATA_OUTSTOCK_D OOD"+
			        "  ON M.SOURCE_NO = OOD.OUTSTOCK_NO"+
			        " AND OOD.warehouse_no = M.warehouse_no"+
			        " AND OOD.OWNER_NO = D.OWNER_NO"+
			        " AND OOD.DIVIDE_ID = D.DIVIDE_ID"+
			        " INNER JOIN ODATA_OUTSTOCK_M OOM"+
			        " ON OOM.warehouse_no = OOD.warehouse_no"+
			        " AND OOM.OUTSTOCK_NO = OOD.OUTSTOCK_NO"+
			        " left JOIN BDEF_ARTICLE_PACKING BAP"+
			        " ON BAP.ARTICLE_NO = D.ARTICLE_NO"+
			        " AND BAP.PACKING_QTY = d.PACKING_QTY"+
			        " INNER JOIN STOCK_ARTICLE_INFO SAI"+
			        " ON D.ARTICLE_NO = SAI.ARTICLE_NO"+
			        " AND D.ARTICLE_ID = SAI.ARTICLE_ID"+
			        " WHERE 1 = 1"+
			    	" and m.label_no='"+strLabelNo+"' "+
					" and m.warehouse_No='"+strWarehouseNo+"' "+
			        " GROUP BY D.ENTERPRISE_NO,"+
			               "   D.warehouse_no,"+
			               "   D.SOURCE_NO,"+
			                "  D.CONTAINER_NO,"+
			                "  D.CONTAINER_TYPE,"+
			                "  D.ARTICLE_NO,"+
			                "  D.PACKING_QTY,"+
			                "  ood.unbox_flag,"+
			                "  M.LABEL_NO,"+
			                "  BAP.SPEC,"+
			                "  M.STOCK_TYPE,"+
			                "  OOM.OPERATE_TYPE,"+
			                "  OOM.OUTSTOCK_TYPE,"+
			                 " OOD.S_CELL_NO,"+
			                 " OOD.D_CELL_NO,"+
			                 " OOD.S_CONTAINER_NO,"+
			                 " OOM.Pick_Type,"+
			                 " sai.EXPIRE_DATE,"+
			                 " sai.LOT_NO,"+
			                 " sai.PRODUCE_DATE,"+
			                 " sai.QUALITY,"+
			                 " OOD.OUTSTOCK_NO,"+
			                 " OOD.OWNER_NO) A"+
			 " INNER JOIN bdef_defarticle BDA"+
			    " ON BDA.ARTICLE_NO = A.ARTICLE_NO"+
			  " and BDA.OWNER_NO = A.OWNER_NO"+
			" INNER JOIN CDEF_DEFCELL CDC"+
			  "  ON CDC.ENTERPRISE_NO = A.ENTERPRISE_NO"+
			  " AND CDC.WAREHOUSE_NO = A.WAREHOUSE_NO"+
			  " AND CDC.CELL_NO = A.S_CELL_NO"+
			 " ORDER BY a.warehouse_no,"+
			        " a.SOURCE_NO,"+
			        " CDC.WARE_NO,"+
			        " CDC.AREA_NO,"+
			        " CDC.STOCK_NO,"+
			        " CDC.PICK_ORDER,"+
			        "  a.S_CELL_NO,"+
			        "  a.CONTAINER_NO,"+
			        "  a.CONTAINER_TYPE,"+
			        " a.ARTICLE_NO,"+
			        "  a.D_CELL_NO";
				
				
				
		
		
		
		
		
		
		/*
		String strSql="SELECT   "+
				"    A.*, CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER, '" +
				resultList.get(1).toString() + "'  divideNo,"+
				"    BDA.OWNER_ARTICLE_NO,  "+
				"    BDA.ARTICLE_NAME,  "+
				"    BDA.ARTICLE_ENAME,  "+
				"    BDA.ARTICLE_IDENTIFIER,  "+
				"    BDA.ARTICLE_ONAME,  "+
				"    BDA.ARTICLE_ALIAS,  "
				//"nvl(a.spec, '1*' || a.packQty || bda.unit) spec,"+
				+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingUnit,"
				+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"
				+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"
				+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingSpec,"
				+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"
				+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"+
				//"    BDA.SPEC,  "+
				"    BDA.QMIN_OPERATE_PACKING,BDA.UNIT_PACKING, "+
				"    BDA.RULE_FLAG,  "+
				"    BDA.ABC,  "+
				"    BDA.QC_FLAG,  "+
				"    BDA.MEASURE_MODE,  "+
				"    BDA.TEMPERATURE_FLAG,  "+
				"    BDA.VIRTUAL_FLAG,  "+
				"    BDA.SCAN_FLAG,  "+
				"    BDA.DOUBLE_CHECK,  "+
//				"    BDA.M_GROUP_NO,  "+
				"    BDA.PICK_EXCESS," +
				"    BDA.EXPIRY_DAYS,CDC.DISP_CELL_NO DISPCELLNO   "+
				"FROM  "+
				"    (SELECT D.ENTERPRISE_NO, "+
				"        D.warehouse_no,  "+
				"        D.SOURCE_NO, BAP.SPEC, "+
				"        D.CONTAINER_NO,  "+
				"        D.CONTAINER_TYPE,  "+
				"        D.ARTICLE_NO,  "+
				"        D.PACKING_QTY as packQty,  "+
				"        SUM(D.QTY) QTY,  "+
				"        M.LABEL_NO,  "+
				"        M.STOCK_TYPE,  "+
				"        OOM.OPERATE_TYPE,  "+
				"        OOM.OUTSTOCK_TYPE,  "+
				"        nvl(ood.unbox_flag,  "+
				"        0) unbox_flag,  "+
				"        OOD.S_CELL_NO,  "+
				"        OOD.D_CELL_NO,  "+
				"        SUM(OOD.ARTICLE_QTY) AS ARTICLE_QTY,  "+
				"        sum(ood.REAL_QTY) REAL_QTY,  "+
				"        sum(ood.REAL_QTY/d.packing_qty)realBox,  "+
				"        sum(ood.article_Qty/d.packing_qty) planBox,  "+
				"        OOD.S_CONTAINER_NO,  "+
				"        OOM.Pick_Type,  "+
				"        min(SAI.BARCODE) as BARCODE,  "+
				"        min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO,  "+
				"        min(sai.RSV_BATCH1) as RSV_BATCH1,  "+
				"        min(sai.RSV_BATCH2) as RSV_BATCH2,  "+
				"        min(sai.RSV_BATCH3) as RSV_BATCH3,  "+
				"        min(sai.RSV_BATCH4) as RSV_BATCH4,  "+
				"        min(sai.RSV_BATCH5) as RSV_BATCH5,  "+
				"        min(sai.RSV_BATCH6) as RSV_BATCH6,  "+
				"        min(sai.RSV_BATCH7) as RSV_BATCH7,  "+
				"        min(sai.RSV_BATCH8) as RSV_BATCH8,  "+
				"        to_char(sai.EXPIRE_DATE,'yyyymmdd') EXPIRE_DATE,  "+
				"        sai.LOT_NO,  "+
				"        to_char(sai.PRODUCE_DATE,'yyyymmdd') PRODUCE_DATE,  "+
				"        sai.QUALITY ,  "+
				"        f_get_fieldtext('N',  "+
				"        'quality',  "+
				"        sai.QUALITY) textQuality," +
				"        OOD.OUTSTOCK_NO," +
				"        OOD.OWNER_NO,       "+
				"         OOD.D_CONTAINER_NO   AS dLabelNo       "+
				"    FROM  "+
				"        STOCK_LABEL_D  D   "+
				"    INNER JOIN  "+
				"        STOCK_LABEL_M  M   "+
				"            ON M.warehouse_no = D.warehouse_no   "+
				"            AND M.CONTAINER_NO = D.CONTAINER_NO   "+
				"            AND M.CONTAINER_TYPE = D.CONTAINER_TYPE   "+
				"    INNER JOIN  "+
				"        ODATA_OUTSTOCK_D  OOD   "+
				"            ON M.SOURCE_NO = OOD.OUTSTOCK_NO   "+
				"            AND OOD.warehouse_no = M.warehouse_no   "+
				"            AND OOD.OWNER_NO = D.OWNER_NO   "+
				"            AND OOD.DIVIDE_ID = D.DIVIDE_ID    "+
				"            and ood.status='10'    "+
				"    INNER JOIN  "+
				"        ODATA_OUTSTOCK_M  OOM   "+
				"            ON OOM.warehouse_no = OOD.warehouse_no   "+
				"            AND OOM.OUTSTOCK_NO = OOD.OUTSTOCK_NO   "+
				"    left JOIN  "+
				"        BDEF_ARTICLE_PACKING BAP   "+
				"            ON BAP.ARTICLE_NO = D.ARTICLE_NO   "+
				"            AND BAP.PACKING_QTY = d.PACKING_QTY   "+
				"    INNER JOIN  "+
				"        STOCK_ARTICLE_INFO SAI   "+
				"            ON D.ARTICLE_NO = SAI.ARTICLE_NO   "+
				"            AND D.ARTICLE_ID = SAI.ARTICLE_ID   "+
				"    WHERE  "+
				"        1=1    "+
				"        and m.label_no='"+strLabelNo+"'  "+
				"        and m.warehouse_No='"+strWarehouseNo+"'    "+
				"        AND (  "+
				"            D.STATUS = '50'   "+
				"            OR D.STATUS = '50'   "+
				"            OR D.STATUS = '40'  "+
				"        )   "+
				"    GROUP BY D.ENTERPRISE_NO, "+
				"        D.warehouse_no,  "+
				"        D.SOURCE_NO,  "+
				"        D.CONTAINER_NO,  "+
				"        D.CONTAINER_TYPE,  "+
				"        OOD.D_CONTAINER_NO,  "+        
				"        D.ARTICLE_NO,  "+
				"        D.PACKING_QTY,  "+
				"        ood.unbox_flag,  "+
				"        M.LABEL_NO, BAP.SPEC, "+
				"        M.STOCK_TYPE,  "+
				"        OOM.OPERATE_TYPE,  "+
				"        OOM.OUTSTOCK_TYPE,  "+
				"        OOD.S_CELL_NO,  "+
				"        OOD.D_CELL_NO,  "+
				"        OOD.S_CONTAINER_NO,  "+
				"        OOM.Pick_Type,  "+
				"        sai.EXPIRE_DATE,  "+
				"        sai.LOT_NO,  "+
				"        sai.PRODUCE_DATE,  "+
				"        sai.QUALITY, " +
				"        OOD.OUTSTOCK_NO," +
				"        OOD.OWNER_NO  ) A   "+
				" INNER JOIN  "+
				"    bdef_defarticle BDA   "+
				"        ON BDA.ARTICLE_NO = A.ARTICLE_NO  and BDA.OWNER_NO = A.OWNER_NO " +
				//关联储位表 根据下架储位PICK_ORDER排序
				" INNER JOIN CDEF_DEFCELL CDC ON CDC.ENTERPRISE_NO = A.ENTERPRISE_NO AND CDC.WAREHOUSE_NO = A.WAREHOUSE_NO AND CDC.CELL_NO = A.S_CELL_NO "+
				"ORDER BY  "+
				"    a.warehouse_no,  "+
				"    a.SOURCE_NO, CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER, "+
				"    a.S_CELL_NO,  "+
				"    a.CONTAINER_NO,  "+
				"    a.CONTAINER_TYPE,  "+
				"    a.ARTICLE_NO,  "+
				"    a.D_CELL_NO  ";*/
		/*if(strDivideType.equals("1"))
		{//1表示按配送对象
			strSql+=",D.DELIVER_OBJ,D.dps_cell_no ";
		}*/
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
	@Override
	public MsgRes tscOmPickTypeExpNo(String strRecvData) throws Exception {
		//请求结构转换
		
	    OdataOutStockRequestModel reqMod=JSON.parseObject(strRecvData, OdataOutStockRequestModel.class);
	    //RF拣货回单确认（边拣边分）
	  		List  outList=new ArrayList();
	  		List  resultList=new ArrayList();
	  		
	  		outList.add("S");
	  		List  inList=new ArrayList();
	  		inList.add(reqMod.getEnterpriseNo());//企业号
	  		inList.add(reqMod.getWarehouseNo());//仓别
	  		inList.add(reqMod.getOutStockNo());//下架单号
	  		inList.add(reqMod.getDivide_no());//下架单号
	  		inList.add(reqMod.getLabelNo());//来源标签号码
	  		inList.add(reqMod.getDpsCellNo());//目的标签号
	  		inList.add(reqMod.getDeliverObj());//配送对象
	  		inList.add(reqMod.getArticleNo());//商品编码
	  		inList.add(reqMod.getPackQty());  //包装数量    
	  		inList.add(reqMod.getScellNo());//来源储位
	  		inList.add(reqMod.getArticleQty());//计划数量
	  		inList.add(reqMod.getRealQty());//下架数量
	  		inList.add(StringUtil.isStrEmpty(reqMod.getQuality())?"0":reqMod.getQuality());//品质
	  		inList.add(DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"));//生产日期
	  		inList.add(DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd")==null? DateUtil.GetDate("19000101", "yyyyMMdd"):DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"));//到期日期
	  		inList.add(StringUtil.isStrEmpty(reqMod.getLotNo())?"N":reqMod.getLotNo());//批次号
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch1())?"N":reqMod.getRsvBatch1());//预留批属性1
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch2())?"N":reqMod.getRsvBatch2());//预留批属性2
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch3())?"N":reqMod.getRsvBatch3());//预留批属性3
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch4())?"N":reqMod.getRsvBatch4());//预留批属性4
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch5())?"N":reqMod.getRsvBatch5());//预留批属性5
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch6())?"N":reqMod.getRsvBatch6());//预留批属性6
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch7())?"N":reqMod.getRsvBatch7());//预留批属性7
	  		inList.add(StringUtil.isStrEmpty(reqMod.getRsvBatch8())?"N":reqMod.getRsvBatch8());//预留批属性8
	  		
	  		inList.add(reqMod.getUserId());//回单人--系统登录人
	  		inList.add(reqMod.getUserId());//下架人--系统登录人
	  		inList.add(reqMod.getUserId());//上架人--系统登录人
	  		
	  		System.out.println(inList);
	  		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_LICH.P_Outstock_PickDivide");
	  		if(resultList.get(0).toString().indexOf("N|")!=-1){
	  			throw new Exception(resultList.get(0).toString());
	  		}
	  		return new MsgRes(true,"",resultList); 
	    
		
	}
	
	
	 /***
     *根据标签号获取分播数据（边拣边分） 
     */
	@Override
	public MsgRes queryOdataDivideDQty(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strLabelNo=reqMod.getReqObj();
		String strDivideType = reqMod.getFieldEX1();
		//取分播数据
				
				Map<Integer, OutStockDateAnswerModel> map=new HashMap<Integer, OutStockDateAnswerModel>();
				ListOutStockAnswerModel listDivdeMod=new ListOutStockAnswerModel();
				String strSql="SELECT   "+
						"    A.*, CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER, "+
						"    BDA.OWNER_ARTICLE_NO,  "+
						"    BDA.ARTICLE_NAME,  "+
						"    BDA.ARTICLE_ENAME,  "+
						"    BDA.ARTICLE_IDENTIFIER,  "+
						"    BDA.ARTICLE_ONAME,  "+
						"    BDA.ARTICLE_ALIAS,  "
						//"nvl(a.spec, '1*' || a.packQty || bda.unit) spec,"+
						+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingUnit,"
						+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingUnitQmin,"
						+" f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) Unit,"
						+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packQty) packingSpec,"
						+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.qmin_operate_packing) packingSpecQmin,"
						+" f_get_spec(a.enterprise_no,a.owner_no,a.article_no,bda.unit_packing) spec,"+
						//"    BDA.SPEC,  "+
						"    BDA.QMIN_OPERATE_PACKING,BDA.UNIT_PACKING, "+
						"    BDA.RULE_FLAG,  "+
						"    BDA.ABC,  "+
						"    BDA.QC_FLAG,  "+
						"    BDA.MEASURE_MODE,  "+
						"    BDA.TEMPERATURE_FLAG,  "+
						"    BDA.VIRTUAL_FLAG,  "+
						"    BDA.SCAN_FLAG,  "+
						"    BDA.DOUBLE_CHECK,  "+
//						"    BDA.M_GROUP_NO,  "+
						"    BDA.PICK_EXCESS," +
						"    BDA.EXPIRY_DAYS,CDC.DISP_CELL_NO DISPCELLNO   "+
						"FROM  "+
						"    (SELECT D.ENTERPRISE_NO, "+
						"        D.warehouse_no,  "+
						"        D.SOURCE_NO, BAP.SPEC, "+
						"        D.CONTAINER_NO,  "+
						"        D.CONTAINER_TYPE,  "+
						"        D.DELIVER_OBJ,  "+
						"        D.ARTICLE_NO,  "+
						"        D.PACKING_QTY as packQty,  "+
						"        SUM(D.QTY) QTY,  "+
						"        M.LABEL_NO,  "+
						"        M.STOCK_TYPE,  "+
						"        OOM.OPERATE_TYPE,  "+
						"        OOM.OUTSTOCK_TYPE,  "+
						"        nvl(ood.unbox_flag,  "+
						"        0) unbox_flag,  "+
						"        OOD.S_CELL_NO,  "+
						"        OOD.D_CELL_NO,  "+
						"        SUM(OOD.ARTICLE_QTY) AS ARTICLE_QTY,  "+
						"        sum(ood.REAL_QTY) REAL_QTY,  "+
						"        sum(ood.REAL_QTY/d.packing_qty)realBox,  "+
						"        sum(ood.article_Qty/d.packing_qty) planBox,  "+
						"        OOD.S_CONTAINER_NO,  "+
						"        OOM.Pick_Type,  "+
						"        ood.dps_cell_no,  "+
						"        min(SAI.BARCODE) as BARCODE,  "+
						"        min(sai.IMPORT_BATCH_NO) as IMPORT_BATCH_NO,  "+
						"        min(sai.RSV_BATCH1) as RSV_BATCH1,  "+
						"        min(sai.RSV_BATCH2) as RSV_BATCH2,  "+
						"        min(sai.RSV_BATCH3) as RSV_BATCH3,  "+
						"        min(sai.RSV_BATCH4) as RSV_BATCH4,  "+
						"        min(sai.RSV_BATCH5) as RSV_BATCH5,  "+
						"        min(sai.RSV_BATCH6) as RSV_BATCH6,  "+
						"        min(sai.RSV_BATCH7) as RSV_BATCH7,  "+
						"        min(sai.RSV_BATCH8) as RSV_BATCH8,  "+
						"        to_char(sai.EXPIRE_DATE,'yyyymmdd') EXPIRE_DATE,  "+
						"        sai.LOT_NO,  "+
						"        to_char(sai.PRODUCE_DATE,'yyyymmdd') PRODUCE_DATE,  "+
						"        sai.QUALITY ,  "+
						"        f_get_fieldtext('N',  "+
						"        'quality',  "+
						"        sai.QUALITY) textQuality," +
						"        OOD.OUTSTOCK_NO," +
						"        OOD.OWNER_NO,       "+
						"         OOD.D_CONTAINER_NO   AS dLabelNo       "+
						"    FROM  "+
						"        STOCK_LABEL_D  D   "+
						"    INNER JOIN  "+
						"        STOCK_LABEL_M  M   "+
						"            ON M.warehouse_no = D.warehouse_no   "+
						"            AND M.CONTAINER_NO = D.CONTAINER_NO   "+
						"            AND M.CONTAINER_TYPE = D.CONTAINER_TYPE   "+
						"    INNER JOIN  "+
						"        ODATA_OUTSTOCK_D  OOD   "+
						"            ON M.SOURCE_NO = OOD.OUTSTOCK_NO   "+
						"            AND OOD.warehouse_no = M.warehouse_no   "+
						"            AND OOD.OWNER_NO = D.OWNER_NO   "+
						"            AND OOD.DIVIDE_ID = D.DIVIDE_ID    "+
						"            and ood.status='10'    "+
						"    INNER JOIN  "+
						"        ODATA_OUTSTOCK_M  OOM   "+
						"            ON OOM.warehouse_no = OOD.warehouse_no   "+
						"            AND OOM.OUTSTOCK_NO = OOD.OUTSTOCK_NO   "+
						"    left JOIN  "+
						"        BDEF_ARTICLE_PACKING BAP   "+
						"            ON BAP.ARTICLE_NO = D.ARTICLE_NO   "+
						"            AND BAP.PACKING_QTY = d.PACKING_QTY   "+
						"    INNER JOIN  "+
						"        STOCK_ARTICLE_INFO SAI   "+
						"            ON D.ARTICLE_NO = SAI.ARTICLE_NO   "+
						"            AND D.ARTICLE_ID = SAI.ARTICLE_ID   "+
						"    WHERE  "+
						"        1=1    "+
						"        and m.label_no='"+strLabelNo+"'  "+
						"        and m.warehouse_No='"+strWarehouseNo+"'    "+
						"        AND (  "+
						"            D.STATUS = '50'   "+
						"            OR D.STATUS = '50'   "+
						"            OR D.STATUS = '40'  "+
						"        )   "+
						"    GROUP BY D.ENTERPRISE_NO, "+
						"        D.warehouse_no,  "+
						"        D.SOURCE_NO,  "+
						"        D.CONTAINER_NO,  "+
						"        D.CONTAINER_TYPE,  "+
						"        D.DELIVER_OBJ,  "+
						"        OOD.D_CONTAINER_NO,  "+        
						"        D.ARTICLE_NO,  "+
						"        D.PACKING_QTY,  "+
						"        ood.unbox_flag,  "+
						"        ood.dps_cell_no,  "+
						"        M.LABEL_NO, BAP.SPEC, "+
						"        M.STOCK_TYPE,  "+
						"        OOM.OPERATE_TYPE,  "+
						"        OOM.OUTSTOCK_TYPE,  "+
						"        OOD.S_CELL_NO,  "+
						"        OOD.D_CELL_NO,  "+
						"        OOD.S_CONTAINER_NO,  "+
						"        OOM.Pick_Type,  "+
						"        sai.EXPIRE_DATE,  "+
						"        sai.LOT_NO,  "+
						"        sai.PRODUCE_DATE,  "+
						"        sai.QUALITY, " +
						"        OOD.OUTSTOCK_NO," +
						"        OOD.OWNER_NO  ) A   "+
						" INNER JOIN  "+
						"    bdef_defarticle BDA   "+
						"        ON BDA.ARTICLE_NO = A.ARTICLE_NO  and BDA.OWNER_NO = A.OWNER_NO " +
						//关联储位表 根据下架储位PICK_ORDER排序
						" INNER JOIN CDEF_DEFCELL CDC ON CDC.ENTERPRISE_NO = A.ENTERPRISE_NO AND CDC.WAREHOUSE_NO = A.WAREHOUSE_NO AND CDC.CELL_NO = A.S_CELL_NO "+
						"ORDER BY  "+
						"    a.warehouse_no,  "+
						"    a.SOURCE_NO, CDC.WARE_NO,CDC.AREA_NO,CDC.STOCK_NO,CDC.PICK_ORDER, "+
						"    a.S_CELL_NO,  "+
						"    a.CONTAINER_NO,  "+
						"    a.CONTAINER_TYPE,  "+
						"    a.ARTICLE_NO,  "+
						"   a.dps_cell_no,  "+
						"    a.DELIVER_OBJ ";
					
				List<OutStockDateAnswerModel> list = genDao.getListByNativeSql(strSql, OutStockDateAnswerModel.class);
				
				for (int i=0;i<list.size();i++) 
				{
					map.put(i, list.get(i));
				}
				
				if(map.size()>0)
				{
					msgRes.setIsSucc(true);
					listDivdeMod.setArrStuOutStock(map);
					msgRes.setObj(JSONObject.toJSON(listDivdeMod));
				}else
				{
					msgRes.setIsSucc(false);
					msgRes.setMsg("没有获取到拣货明细数据！");
				}
				return msgRes;
		
	}
	
	
	
}
