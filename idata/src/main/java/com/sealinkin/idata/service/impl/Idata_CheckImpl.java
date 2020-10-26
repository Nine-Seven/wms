package com.sealinkin.idata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_ArticleInfoModel;
import com.sealinkin.bdef.model.Bdef_WmsTaskallotRuleMModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.comm.service.IGetSystemParameterService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_CheckDModel;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.idata.model.Idata_Check_ResulModel;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.model.Idata_ImportSdModel;
import com.sealinkin.idata.service.Iidata_CheckService;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.idata.BarcodeRequestModel;
import com.sealinkin.protocolExchange.idata.IdataCheckExistsAnswer;
import com.sealinkin.protocolExchange.idata.IdataCheckExistsRequestModel;
import com.sealinkin.protocolExchange.idata.IdataCheckLabelRequestMode;
import com.sealinkin.protocolExchange.idata.IdataCheckRequestModel;
import com.sealinkin.protocolExchange.idata.IdataCloseLablRequestModel;
import com.sealinkin.protocolExchange.idata.IdataCloseLablRequestModelTTH;
import com.sealinkin.protocolExchange.idata.IdataDockIDModel;
import com.sealinkin.protocolExchange.idata.IdataGetArticleInfoIDAnswerModel;
import com.sealinkin.protocolExchange.idata.IdataGetArticlePackingInfo;
import com.sealinkin.protocolExchange.idata.IdataGetBarcodeRequestModel;
import com.sealinkin.protocolExchange.idata.IdataGetLotAnswerModel;
import com.sealinkin.protocolExchange.idata.IdataGetLotRequestModel;
import com.sealinkin.protocolExchange.idata.IdataIMLogisticsCodeRequestModel;
import com.sealinkin.protocolExchange.idata.IdataInStockBarcodeRequestModel;
import com.sealinkin.protocolExchange.idata.IdataInStockLabelAnswerModel;
import com.sealinkin.protocolExchange.idata.IdataSerialNoRequestModel;
import com.sealinkin.protocolExchange.idata.IdataStockCheckArticleInfoModel;
import com.sealinkin.protocolExchange.idata.SerialNoAnswerModel;
import com.sealinkin.protocolExchange.idata.WmsDeffieldvalModel;
import com.sealinkin.report.conf.ReportDefine;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.MyDateMorpher;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.TipUtil;
import com.sealinkin.wms.model.Wms_DefbaseModel;
import com.sealinkin.wms.model.Wms_IDataTypeModel;

/**
 * 验收
 * @author JUN
 */
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class Idata_CheckImpl implements Iidata_CheckService{
	private IGenericManager genDao;
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	private IGetSystemParameterService getSystemParameterImpl;
	
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
	
	public IGetSystemParameterService getGetSystemParameterImpl() {
		return getSystemParameterImpl;
	}
	public void setGetSystemParameterImpl(
			IGetSystemParameterService getSystemParameterImpl) {
		this.getSystemParameterImpl = getSystemParameterImpl;
	}
	/**
	 * 填充进货汇总单下拉
	 */
	@Override
	public List<Idata_ImportSdModel> queryIdataImportMMCombo(String enterpriseNo,
			String strWarehouseNo,String strOwnerNo,String strFlag,String strWheresql) throws Exception {
		String strSql="";
		strSql="select a.warehouse_no, a.import_type, a.s_import_no, a.owner_no, a.dept_no, " +
				"a.po_type,a.supplier_no, a.status, a.serial_no, a.stock_type, a.stock_value, " +
				"a.printer_name,a.printer_date, a.rgst_name, a.rgst_date, a.updt_name, a.updt_date " +
				"from idata_import_mm a " +
				"where a.WAREHOUSE_NO='"+strWarehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+
				"' and a.status not in('13','16') ";
		if(strFlag.equals("SS"))
		{
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				strSql+=" and a.s_import_no like '%"+strWheresql+"%'";
			}
		}else if(strFlag.equals("IS"))
		{
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				strSql+=" and b.import_no like '%"+strWheresql+"%'";
			}
		}
		
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in('"+strOwnerNo+"')";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list=genDao.getListByNativeSql(strSql,Idata_ImportSdModel.class);
		return (List<Idata_ImportSdModel>) list;
	}
	
	/**
	 * 获取单据类型(混合板验收)
	 */
	@Override
	public List<ComboxBo> getImportType(String enterpriseNo,
			String workerOwner, String warehouseNo, String str)
			throws Exception {
		String strSql="select distinct a.import_type value,l.text text,"+
				  " '['|| ltrim(a.import_type)||']'||l.text dropValue  " +
				  "from idata_import_sm a,wms_deffieldval l " +
				  "where a.import_type=l.value and l.table_name='N' " +
				  "and l.colname='IMPORT_TYPE' "+
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
	 * 填充供应商下拉(混合板验收)
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
			
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 200);
			return (List<ComboxBo>) list;
	}
	/**
	 * 校验预约流水号
	 */
	@Override
	public MsgRes checkOrderSerial(String strEnterpriseNo, String strWarehouseNo,String workerOwner,
			String str) throws Exception {
		String strSql="select a.serial_no " +
				     "from idata_import_mm a,idata_import_m m,idata_import_sm sm  " +
		             "where a.enterprise_no=m.enterprise_no " +
		             "and a.warehouse_no=m.warehouse_no and a.s_import_no=sm.s_import_no " +
		             "and m.enterprise_no=sm.enterprise_no " +
		             "and m.warehouse_no=sm.warehouse_no and m.import_no=sm.import_no " +
				     "and a.status not in ('13','16') "+
					 "   and a.enterprise_no='" + strEnterpriseNo + "' "+
					 "   and a.warehouse_No='" + strWarehouseNo + "' "+ 
					 " and a.owner_no in(" + workerOwner + ") ";
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql + ws;
		}
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0){
			return new MsgRes(false,"该预约流水号不存在","该预约流水号不存在");
		}else{
			return new MsgRes(true,"",JSON.toJSONString(list));
		}
	}
	/**
	 * 填充采购单号(混合板验收)
	 */
	@Override
	public List<ComboxBo> getPoNoList(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str) throws Exception {
		String sql1 = "select a.po_no value,a.po_no text,a.po_no dropValue  "
				+ "from idata_import_m a, idata_import_sm b,idata_import_mm iim "
				+ " where a.enterprise_no=b.enterprise_no " 
				+ " and a.warehouse_No=b.warehouse_No " 
				+ " and a.owner_no=b.owner_no " 
				+ " and a.import_no=b.import_no " 
				+ " and a.enterprise_no=iim.enterprise_no " 
				+ " and a.warehouse_No=iim.warehouse_No " 
				+ " and a.owner_no=iim.owner_no " 
				+ " and b.s_import_no=iim.s_import_no " 
				+ " and a.status not in ('13','16') "
				+ " and a.enterprise_no='" + enterpriseNo + "' "
				+ " and a.warehouse_No='" + warehouseNo + "' "
				+ " and a.owner_no in(" + ownerNo + ") ";
				
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql1 = sql1 + ws;
		}
		if (strWheresql != null && !strWheresql.equals("")) {
			sql1 = sql1 + "and a.po_no like '%" + strWheresql + "%' ";
		}
		
//		String sql2 ="select a.serial_no value, a.serial_no text, a.serial_no dropValue " +
//				     "  from idata_import_mm a " +
//				     " where a.status not in ('13','16') "+
//					 "   and a.enterprise_no='" + enterpriseNo + "' "+
//					 "   and a.warehouse_No='" + warehouseNo + "' "+ 
//					 " and a.owner_no in(" + ownerNo + ") ";
//		if (str != null && !str.equals("")) {
//			String ws = CommUtil.covtCollectionToWhereSql(str);
//			sql2 = sql2 + ws;
//		}
//		if (strWheresql != null && !strWheresql.equals("")) {
//			sql2 = sql2 + "and a.serial_no like '%" + strWheresql + "%' ";
//		}		
//		
//		String sql=sql1+" union "+sql2;
//		
		List list = genDao.getListByNativeSql(sql1, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//获取单据列表(混合板验收)
	@Override
	public ExtListDataBo<Idata_ImportMModel> getPoNoAndSImportNoList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String strWheresql,String str,String orderNo, PageBo pageBo) throws Exception {
			String sql="select distinct a.owner_no, " +
		            "a.po_type,a.po_no,a.s_import_no,a.import_no,m.class_type, " +
		            "f_get_fieldtext('IDATA_IMPORT_M','PO_TYPE',a.po_type)potypeText  " +
		            "from idata_import_sm a,idata_import_sd b, " +
		            "bdef_defarticle c ,idata_import_mm iim , idata_import_m m  " +
		            "where a.enterprise_no=b.enterprise_no  " +
		            "and a.warehouse_no=b.warehouse_no and a.owner_no=b.owner_no  " +
		            "and a.s_import_no=b.s_import_no and b.enterprise_no=c.enterprise_no " + 
		            "and b.owner_no=c.owner_no and b.article_no=c.article_no  " +
		            "and a.status not in ('13','16')  " +
		            "and a.enterprise_no=iim.enterprise_no  " +
		            "and a.warehouse_no=iim.warehouse_no  " +
		            "and a.s_import_no=iim.s_import_no " +
		            "and a.enterprise_no=m.enterprise_no " +
		            "and a.warehouse_no=m.warehouse_no " +
		            "and a.import_no=m.import_no " +
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
	//汇总单明细(混合板验收)
	@Override
	public ExtListDataBo<Idata_ImportDModel> getImportSDList(
				String strEnterpriseNo,String strWarehouseNo,String strOwnerNo, 
				String str, PageBo pageBo)
				throws Exception {
		String sql="select a.po_no,a.owner_no,a.article_no,a.owner_article_no,a.barcode,a.article_name,a.article_identifier,a. printFlag,a.packing_qty, "+
		           " a.packingUnit, a.packingUnitQmin, a.Unit, a.packingSpec, a.packingSpecQmin, a.spec,a.qmin_operate_packing, " +
		           " a.inQty,a.checkQty,a.noCheckQty,a.canCheckQty,a.poQty, a.planBox,a.planQmin,a.planDis,a.articleText, "+
		           " a.produceDateText,a.expireDateText,a.lotNo,a.lot_type,a.expiry_days,a.alarmrate,a.freezerate,a.check_excess, "+
		           " a.temperature_flag,nvl(a.pal_base_qbox||'*'||a.pal_height_qbox,9999*999)qpaletteText,a.qpalette," +
		           " nvl(a.pal_base_qbox,999)pal_base_qbox,nvl(a.pal_height_qbox,999)pal_height_qbox,a.boxPickType,a.disPickType  "+ 
		           " from(select b.article_no,c.owner_article_no,c.barcode,a.po_no,a.owner_no,  "+
		                   "c.article_name,c.article_identifier," +
		                   "f_get_fieldtext('BDEF_DEFARTICLE','PRINT_FLAG',c.print_flag) printFlag, " +
		                   "b.packing_qty,c.qmin_operate_packing," +
		                   //"nvl(bap.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packingUnit," +
		                   "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
		                   "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
		                   "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
		                   "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
		                   "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
		                   "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"+
		                   "b.po_qty inQty,b.import_qty checkQty,  "+
		                   " case when  b.po_qty - b.import_qty - nvl(icpt.check_qty,0)<0 " +
		                        "then 0   "+
	                        "else  "+
	                           "b.po_qty - b.import_qty - nvl(icpt.check_qty, 0) " +
	                        "end noCheckQty,  "+
						" trunc((case     " +
						"         when b.po_qty - b.import_qty - nvl(icpt.check_qty, " +
						"         0)<0 then 0     " +
						"         else b.po_qty - b.import_qty - nvl(icpt.check_qty, " +
						"         0)  " +
						"     end)/b.packing_qty) as planBox, " +
						"    trunc(mod((case     " +
						"         when b.po_qty - b.import_qty - nvl(icpt.check_qty, " +
						"         0)<0 then 0     " +
						"         else b.po_qty - b.import_qty - nvl(icpt.check_qty, " +
						"         0)    " +
						"     end), " +
						"     b.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin, " +
						"     ((case when b.po_qty - b.import_qty - nvl(icpt.check_qty, 0)<0 then 0 else b.po_qty - b.import_qty - nvl(icpt.check_qty,0) end) " +
						"       - trunc((case when b.po_qty - b.import_qty - nvl(icpt.check_qty,0)<0 then 0 else b.po_qty - b.import_qty - nvl(icpt.check_qty,0) end)/ b.packing_qty) * b.packing_qty " +
						"       - trunc(mod((case when b.po_qty - b.import_qty - nvl(icpt.check_qty,0)<0 then 0 else b.po_qty - b.import_qty - nvl(icpt.check_qty,0) end),b.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis, " +
//						"     mod((case     " +
//						"         when b.po_qty - b.import_qty - nvl(icpt.check_qty, " +
//						"         0)<0 then 0     " +
//						"         else b.po_qty - b.import_qty - nvl(icpt.check_qty, " +
//						"         0)    " +
//						"     end), " +
//						"    c.QMIN_OPERATE_PACKING) as planDis, " +
					     /*  " trunc((case   "+
					           " when b.po_qty - b.import_qty - nvl(icpt.check_qty, 0)<0 " +
					                 "then 0   "+
					           " else " +
					                 "b.po_qty - b.import_qty - nvl(icpt.check_qty, 0) end)/b.packing_qty)" +
					        " as pobox,  "+
					       " mod((case   "+
					           " when b.po_qty - b.import_qty - nvl(icpt.check_qty,  "+
					           " 0)<0 then 0   "+
					           " else b.po_qty - b.import_qty - nvl(icpt.check_qty,  "+
					           " 0)  "+
					           " end),  "+
					           " b.packing_qty) as popcs,  "+*/
					        "(b.PO_QTY*c.check_excess*0.01+ b.po_qty-b.import_qty-nvl(icpt.check_qty,0)) as canCheckQty," +
		                   "(select nvl(sum(iia.po_qty), 0)  "+
		                       "from idata_import_allot iia  "+
		                      "where iia.import_no = a.import_no  "+
		                      "and iia.article_no = b.article_no) as poQty,  "+
		                 "  '[' || ltrim(b.article_no) || ']' || c.article_name articleText,  "+
		             "case   "+
		             " when  c.EXPIRY_DAYS=-1 then to_date ('1900-01-01','yyyy-mm-dd')   "+
		                " when  c.lot_type=1 or c.lot_type=4 then to_date ('1900-01-01','yyyy-mm-dd')  "+    
		                " when  c.lot_type=2 or c.lot_type=3 and (b.po_qty - b.import_qty - nvl(icpt.check_qty,0)>0) then null    "+
		             "else null   "+
		            "end produceDateText,  "+
		            "case   "+
		            "  when c.EXPIRY_DAYS=-1 then to_date('1900-01-01','yyyy-mm-dd')    "+
		            "  when c.lot_type=1 or c.lot_type=4 then to_date ('1900-01-01','yyyy-mm-dd')    "+
		            "  when c.lot_type=2 or c.lot_type=3 and (b.po_qty - b.import_qty - nvl(icpt.check_qty,0)>0)  then null     "+
		            " else null   "+
		            "end expireDateText,  "+
		            "case   "+
		               " when iim.class_type='1' then 'N'   "+
		                "when iim.class_type='0'   "+
		                "and  (c.lot_type=2   "+
		               " or c.lot_type=4) then 'N'   "+
		               " when (b.po_qty - b.import_qty - nvl(icpt.check_qty,  "+
		            "0)<=0) then 'N'   "+
		               " else null   "+
		           " end lotNo,  "+
		            "c.lot_type,c.expiry_days,c.alarmrate," +
		            "c.freezerate,c.check_excess,c.temperature_flag," +
		          "  nvl(bwp.qpalette,bap.qpalette) as qpalette, " +
			      "  nvl(bwp.pal_base_qbox,bap.pal_base_qbox) as pal_base_qbox, " +
			      "  nvl(bwp.pal_height_qbox,bap.pal_height_qbox) as pal_height_qbox, " +
		            "g.pick_type as boxPickType, " +
					"h.pick_type as disPickType " +
			        "from idata_import_sm a    "+
			        "inner join idata_import_m iim   " +     
		                "on a.enterprise_no =iim.enterprise_no  " +
		                "and a.warehouse_no = iim.warehouse_no  " +      
		                "and a.owner_no = iim.owner_no  " +
		                "and a.import_no = iim.import_no  " + 
			        "inner join idata_import_sd b   "+
		            "    on a.enterprise_no = b.enterprise_no and a.warehouse_no = b.warehouse_no   "+
		            "    and a.owner_no = b.owner_no and a.s_import_no = b.s_import_no "+
			        "inner join v_bdef_defarticle c   "+
		            "    on b.enterprise_no = c.enterprise_no and b.owner_no = c.owner_no   "+
		            "    and b.article_no = c.article_no        "+
			        "left join(select t.enterprise_no, "+
		            "           t.warehouse_no,t.owner_no,t.s_import_no, "+
		            "           t.article_no,sum(t.check_qty) check_qty, t.packing_qty  "+ 
		            "          from idata_check_pal_tmp t  "+
			        "        group by "+
			        "            t.enterprise_no,t.warehouse_no,t.owner_no, "+
			        "           t.s_import_no,t.article_no,t.packing_qty)  icpt   "+
	                "on b.enterprise_no = icpt.enterprise_no   "+
	                "and b.warehouse_no = icpt.warehouse_no   "+
	                "and b.s_import_no = icpt.s_import_no   "+
	                "and b.article_no = icpt.article_no   "+
	                "and b.owner_no = icpt.owner_no        "+ 
	               "left join bdef_article_packing bap "+
			           "on b.enterprise_no = bap.enterprise_no "+
			           "and b.article_no = bap.article_no "+
			           "and b.packing_qty = bap.packing_qty "+
			        "   left join "+
			        "    bdef_warehouse_packing bwp  "+
			        "        on b.enterprise_no = bwp.enterprise_no  "+
			        "        and b.article_no = bwp.article_no  "+
			        "       and b.packing_qty = bwp.packing_qty       "+   
	               "left join (select enterprise_no,warehouse_no,article_no,ware_no,area_no,pick_type from cset_cell_article where warehouse_no='"+strWarehouseNo+"' " +
			        "and enterprise_no='"+strEnterpriseNo+"' "+
					"and pick_type='B') g " +
						"on  b.enterprise_no=g.enterprise_no " +
						"and b.warehouse_no=g.warehouse_no " +
						"and b.article_no=g.article_no " +
				   "left join (select enterprise_no,warehouse_no,article_no,ware_no,area_no,pick_type from cset_cell_article where warehouse_no='"+strWarehouseNo+"' " +
			        "and enterprise_no='"+strEnterpriseNo+"' "+
					"and pick_type='C') h " +
						"on  b.enterprise_no=h.enterprise_no " +
						"and b.warehouse_no=h.warehouse_no " +
						"and b.article_no=h.article_no " +
	            "where  a.status not in ('13','16') " +
				   "and a.warehouse_no='"+strWarehouseNo+"' " +
				   "and a.enterprise_no='"+strEnterpriseNo+"' " +
				   "and a.owner_no in("+strOwnerNo+"))a where 1=1 " ;		
		        
	    if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}

	    sql =sql+" order by  a.article_no,a.packing_qty ";
	    String totsql = "select count(*) from (" + sql + ") " ;	
	    List<Idata_ImportDModel> list = genDao.getListByNativeSql(sql,Idata_ImportDModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Idata_ImportDModel> extListBo= new ExtListDataBo<Idata_ImportDModel>(list, count);
	    return extListBo;
	}
	/**
	 * 填充助记码/条码(混合板验收)
	 */
	@Override
	public List<ComboxBo> getIdentifierOrBarcode1List(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str) throws Exception {
		String sql =" select distinct c.barcode, "+
				" '['|| ltrim(c.article_no)||']'||c.article_name articleText,c.rsv_attr2 "+
				 "from idata_import_sm a,idata_import_sd b, idata_import_m m,bdef_defarticle c "+
			    " where a.enterprise_no=b.enterprise_no  "+
			    " and a.warehouse_No=b.warehouse_No  "+
			    " and a.owner_no=b.owner_no  "+
			    " and a.s_import_no=b.s_import_no "+
			    " and b.enterprise_no=c.enterprise_no  "+
			    " and b.owner_no=c.owner_no  "+
			    " and b.article_no=c.article_no  " +
			    " and a.enterprise_no=m.enterprise_no " +
			    "and a.warehouse_no=m.warehouse_no and a.import_no=m.import_no  "+
				" and a.enterprise_no='" + enterpriseNo + "' "
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
	
	/**
	 * 获取验收明细（界面直通验收、流水板自动封板、直通验收作业）
	 */
	@Override
	public ExtListDataBo<Idata_ImportSdModel> queryIdataImportSd(
			String enterpriseNo,String strWarehouseNo, String strOwner, String strWheresql)
			throws Exception 
	{	
		String strSql="select a.enterprise_no,a.warehouse_no,a.owner_no,a.s_import_no,a.row_id,a.article_no," +
				"a.packing_qty,a.po_qty,a.import_qty,a.qc_qty,a.check_name,a.status,a.check_date," +
				"a.qc_status,a.out_stock_flag,a.outpace_article_flag,a.item_type,a.plan_across_qty," +
				"a.check_across_qty,a.qc_flag,b.unit_packing,b.QMIN_OPERATE_PACKING," +
				"case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end inQty," +
				"case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end checkQty," +
				"case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))>0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))*-1 end excessQty, " +
				"(a.PO_QTY*b.check_excess*0.01+ a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) as noCheckQty," +
				
				//"trunc((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end)/a.packing_qty) as checkBox, " +
				//"mod((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end),a.packing_qty) as checkDis, " +
				"trunc((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end)/a.packing_qty) as planBox,  " +
			    "trunc(mod((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end),a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,  " +
			    "((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end) - trunc((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end)/ a.packing_qty) * a.packing_qty - trunc(mod((case when (a.po_qty-a.import_qty-nvl(tmp.check_qty,0))<0 then 0 else (a.po_qty-a.import_qty-nvl(tmp.check_qty,0)) end),a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis, " +

			    "case when c.class_type='1' then to_date('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and (b.lot_type=1 or b.lot_type=4) then to_date ('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and a.po_qty-a.import_qty=0 then to_date ('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and (b.lot_type=2 or b.lot_type=3) and (a.po_qty-a.import_qty<>0) then null " +
				"else null " +
				"end produceDate," +
				"case when c.class_type='1' then to_date('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and (b.lot_type=1 or b.lot_type=4) then to_date ('1900-01-01', 'yyyy-mm-dd') " +
				"when c.class_type='0' and a.po_qty-a.import_qty=0 then to_date ('1900-01-01','yyyy-mm-dd') " +
				"when c.class_type='0' and (b.lot_type=2 or b.lot_type=3) and (a.po_qty-a.import_qty<>0) then null " +
				"else null " +
				"end expireDate," +
				"case when g.ware_no is null then 'N' else g.ware_no||g.area_no  end wareArea," +
				"case when c.class_type='1' then 'N' " +
				"when c.class_type='0' and  (b.lot_type=2 or b.lot_type=4) then 'N' " +
				"when a.po_qty-a.import_qty=0 then 'N' " +	
				"else null " +
				"end lotNo,b.expiry_days,b.article_name, b.owner_article_no as ownerArticleNo," +
				//被屏蔽的为错误代码，做参考所以暂时不删除
				//"case when b.packing_qty<>null and b.packing_qty=a.packing_qty then b.packing_unit " +
				//"when exists(select bap.article_no from bdef_article_packing bap " +
				//"             where bap.enterprise_no=a.enterprise_no " +
				//"               and bap.article_no=a.article_no and bap.packing_qty=a.packing_qty) " +
				//"then (select bap.packing_unit from bdef_article_packing bap " +
				//"       where bap.enterprise_no=a.enterprise_no " +
				//"         and bap.article_no=a.article_no and bap.packing_qty=a.packing_qty) " +
				//"else b.unit end packingUnit, " +	
				//huangb 20160528
				//"nvl(pk.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packingUnit," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
				"nvl(b.barcode,'N') as barcode, " +
				"b.alarmrate,b.freezerate,b.check_excess,b.temperature_flag, " +
				"g.pick_type as boxPickType, " +
				"h.pick_type as disPickType, " +
				"b.lot_type as lotType "+
				"from idata_import_sd a,bdef_defarticle b,idata_import_mm c,bdef_article_packing pk," +
				"(select article_no,ware_no,area_no,pick_type from cset_cell_article where warehouse_no='"+strWarehouseNo+"' " +
				        "and enterprise_no='"+enterpriseNo+"' "+
						"and pick_type='B') g," +
				"(select article_no,ware_no,area_no,pick_type from cset_cell_article where warehouse_no='"+strWarehouseNo+"' " +
				        "and enterprise_no='"+enterpriseNo+"' "+
						"and pick_type='C') h, " +
				" (select t.enterprise_no,t.warehouse_no,t.owner_no,t.s_import_no,t.article_no,sum(t.check_qty) check_qty,t.packing_qty " +
				" from idata_check_pal_tmp t group by t.enterprise_no,t.warehouse_no,t.owner_no,t.s_import_no,t.article_no,t.packing_qty) tmp " +
				"where a.article_no=b.article_no and a.s_import_no=c.s_import_no and a.warehouse_no=c.warehouse_no " +
				"and a.enterprise_no=c.enterprise_no and a.enterprise_no=b.enterprise_no "+			
				"and a.article_no=g.article_no(+) and a.article_no=h.article_no(+) " +
				" and a.enterprise_no = tmp.enterprise_no(+) " +
				" and a.warehouse_no = tmp.warehouse_no(+) " +
				" and a.owner_no = tmp.owner_no(+) " +
				" and a.s_import_no = tmp.s_import_no(+) " +
				" and a.article_no = tmp.article_no(+) " +
				" and a.packing_qty = tmp.packing_qty(+) " +	
				" and a.article_no=pk.article_no(+)" +
				" and a.packing_qty=pk.packing_qty(+) "+
			    " and a.enterprise_no=pk.enterprise_no(+) "+
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
			strSql=strSql+ft;
		}
		strSql+="order by a.article_no";
		List<Idata_ImportSdModel> list = genDao.getListByNativeSql(strSql,Idata_ImportSdModel.class,0,10000);
		ExtListDataBo<Idata_ImportSdModel> extListBo=new ExtListDataBo<Idata_ImportSdModel>(list,0);
		return extListBo;
	}
	
	/**
	 * 获取进货单号、供应商等信息
	 */
	@Override
	public List<Idata_ImportMModel> getImportNo(String enterpriseNo,String warehouseNo,String strOwnerNo,String strSImportNo) throws Exception 
	{
		String strSql="select a.owner_no,a.import_no,b.supplier_no,c.supplier_name,a.import_type " +
				"from idata_import_sm a,idata_import_mm b,bdef_defsupplier c " +
				"where a.s_import_no='"+strSImportNo+"' " +
				"and a.enterprise_no = b.enterprise_no "+
				"and a.enterprise_no = c.enterprise_no "+
				"and a.warehouse_no = b.warehouse_no "+
				"and a.s_import_no=b.s_import_no " +				
				"and b.supplier_no=c.supplier_no "+
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_no='"+warehouseNo+"' ";
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+")";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List<Idata_ImportMModel> list=genDao.getListByNativeSql(strSql,Idata_ImportMModel.class);
		//获取是否允许超量验收参数
		List<Wms_IDataTypeModel> listOverQtyFlag = getSystemParameterImpl.getIdataTypeStrategy
				(enterpriseNo, warehouseNo, list.get(0).getOwnerNo(), list.get(0).getImportType(), "over_qty_flag");
		
		for(int i = 0;i < list.size(); i++)
		{
			list.get(i).setOverQtyFlag(listOverQtyFlag.get(0).getColumnValue());
		}
		
		return list;
	}
	
	/**
	 * 获取进货汇总单号
	 */
	@Override
	public List<Idata_ImportMModel> getSImportNo(String strOwnerNo,String strImportNo) throws Exception {
		String strSql="select a.s_import_no,b.supplier_no,c.supplier_name " +
				"from idata_import_sm a,idata_import_mm b,bdef_defsupplier c " +
				"where a.s_import_no='"+strImportNo+"' " +
				"and a.s_import_no=b.s_import_no " +
				"and b.supplier_no=c.supplier_no ";
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and a.owner_no in("+strOwnerNo+")";
		}else{
			strSql=strSql+" and 1=2";
		}
		List<Idata_ImportMModel> list=genDao.getListByNativeSql(strSql,Idata_ImportMModel.class);
		return list;
	}
	
	/**
	 * 根据商品编号和批号获取相应的生产日期
	 */
	@Override
	public MsgRes queryLotProduceDate(String strRecvData) throws Exception {		
		IdataGetLotRequestModel reqMod=JSON.parseObject(strRecvData, IdataGetLotRequestModel.class);
		String strArticleNo=reqMod.getArticleNo();
		String strProduceDate=reqMod.getProduceDate();
		String strSImportNo=reqMod.getSimportNo();
		String strSql="select    "+
					"distinct   "+
					"    article_no,   "+
					"    lot_no               "+
					"from   "+
					"    bdef_article_lot_manage    "+
					"where   "+
					"    article_no='"+strArticleNo+"'    "+
					"    and to_char(produce_date,'yyyymmdd')='"+strProduceDate+"'   "+
					"union    "+
					"select distinct    "+
					"	iil.article_no,   "+
					"	iil.lot_no   "+
					"from    "+
					"	idata_import_sm ism,   "+
					"	Idata_import_Lot iil   "+
					"where    "+
					"	ism.import_no=iil.import_no   "+
					"	and iil.article_no='"+strArticleNo+"'   "+
					"	and ism.s_import_no='"+strSImportNo+"'";	
		
		List<IdataGetLotAnswerModel> list = genDao.getListByNativeSql(strSql,IdataGetLotAnswerModel.class,0,10);
		return new MsgRes(true,"",JSONArray.fromObject(list));
	}
	
	/**
	 * 保存(流水板)
	 */
	@Override
	public MsgRes tscSaveCheck(String strJsonMaster, String strJsonDetail1,String printFlag)
			throws Exception 
	{
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
		List<Idata_CheckDModel> list=JSON.parseArray(strJsonDetail1,Idata_CheckDModel.class);
		
		List saveCheckDataISResult=new ArrayList();//普通保存验收数据返回结果
//		List closePalMainResult=new ArrayList();//封板验收主程序返回结果
//		List idataSCheckLocateInstockResult=new ArrayList();//根据验收汇总单写标签数据、定位指示和库存返回结果
//		List pLocateMainResult=new ArrayList();//进货定位程序入口返回结果
//		List pInsertInstockResult=new ArrayList();//上架发单返回结果
		for(int i=0;i<list.size();i++){
			saveCheckDataISResult=
					tscSaveCheckDataIS(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),poMaster.getSImportNo(),
					list.get(i).getArticleNo(),list.get(i).getBarcode(),list.get(i).getPackingQty(),list.get(i).getCheckQty(),"N",poMaster.getDockNo(),
					poMaster.getCheckWorker(),poMaster.getCheckTools(),i==0 ? 0:1,list.get(i).getQuality(),
					list.get(i).getProduceDate(),list.get(i).getExpireDate(),list.get(i).getLotNo(),
					list.get(i).getRsvBatch1(),list.get(i).getRsvBatch2(),list.get(i).getRsvBatch3(),list.get(i).getRsvBatch4(),
					list.get(i).getRsvBatch5(),list.get(i).getRsvBatch6(),list.get(i).getRsvBatch7(),list.get(i).getRsvBatch8(),
					"N","N","2","0",list.get(i).getTemperature());
			if(saveCheckDataISResult.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(saveCheckDataISResult.get(1).toString());
			}
		}
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(poMaster.getEnterpriseNo());//strWareHouseNo
		inList.add(poMaster.getWarehouseNo());//strWareHouseNo
		inList.add(poMaster.getOwnerNo());//strOwnerNo
		inList.add(poMaster.getSImportNo());//strsImportNo
		inList.add(saveCheckDataISResult.get(0).toString());//strsCheckNo
		inList.add("N");//strLabelNo
		inList.add(poMaster.getCheckWorker());//strWorkerNo
		inList.add(printFlag);//strPrintType
		inList.add("2");//strFixPalFlag
		inList.add(poMaster.getDockNo());//strDockNo
		
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_RF_Close_Pal");

		System.out.println(inList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}	
//		closePalMainResult=
//				tscClosePalMain(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),poMaster.getSImportNo(),
//				saveCheckDataISResult.get(0).toString(),"N",poMaster.getCheckWorker(),"2");
//				if(closePalMainResult.get(0).toString().indexOf("N|")!=-1)
//				{
//					throw new Exception(closePalMainResult.get(0).toString());
//				}
//		
//		
//		idataSCheckLocateInstockResult=
//				tscIdataSCheckLocateInstock(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),saveCheckDataISResult.get(0).toString(),
//				poMaster.getCheckWorker(),poMaster.getCheckTools(),"N","N");
//				if(idataSCheckLocateInstockResult.get(1).toString().indexOf("N|")!=-1)
//				{
//					throw new Exception(idataSCheckLocateInstockResult.get(1).toString());
//				}
//		System.out.println(poMaster.getEnterpriseNo());
//		System.out.println(poMaster.getEnterpriseNo());
//		System.out.println(poMaster.getOwnerNo());
//		System.out.println(idataSCheckLocateInstockResult.get(0).toString());
//		System.out.println("0");
//		System.out.println(poMaster.getCheckWorker());
//		pLocateMainResult=tscLocateMain(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),
//				idataSCheckLocateInstockResult.get(0).toString(),"0",poMaster.getCheckWorker());
//				if(pLocateMainResult.get(1).toString().indexOf("N|")!=-1)
//				{
//					throw new Exception(pLocateMainResult.get(0).toString());
//				}
//		
//		pInsertInstockResult=tscInsertInstock(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getCheckWorker(),
//				idataSCheckLocateInstockResult.get(0).toString(),poMaster.getDockNo(),printFlag);
//				if(pInsertInstockResult.get(1).toString().indexOf("N|")!=-1)
//				{
//					throw new Exception(pInsertInstockResult.get(1).toString());
//				}
		
		return new MsgRes(true, "保存成功","");//保存成功
	}
	//前台单品验收校验是否能验收
	@Override
	public MsgRes tscCheckExists(String strSImportNo,String strJsonDetail1) throws Exception {
		Idata_CheckDModel req=JSON.parseObject(strJsonDetail1,Idata_CheckDModel.class);
		MsgRes msgRes=new MsgRes();
		msgRes=CheckExcess(req.getEnterpriseNo(),
				req.getWarehouseNo(), 
				req.getOwnerNo(), 
				strSImportNo, 
				req.getArticleNo(), 
				req.getPackingQty(),
				req.getProduceDate(),
				req.getExpireDate(),
				req.getCheckQty(),
				"0"//req.getOverFlag()//超品标识，0-正常；1-超品 暂时不改前台 写死
				);
		if(msgRes.getIsSucc())
		{
			Idata_CheckDModel existsAns=new Idata_CheckDModel();
			List list=(List) msgRes.getObj();
			existsAns.setPromptType(list.get(0)!=null?list.get(0).toString():"");
			existsAns.setPromptFlag(list.get(1)!=null?list.get(1).toString():"");
			msgRes.setObj(JSON.toJSON(existsAns));
		}
		return msgRes;
	}
	
	/**
	 * 保存(混合板，界面的单品验收)
	 */
	@Override
	public MsgRes tscSaveCheck3(String strJsonMaster, String strJsonDetail1,String printFlag)
			throws Exception 
	{
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
		List<Idata_CheckDModel> list=JSON.parseArray(strJsonDetail1,Idata_CheckDModel.class);
		
		if(poMaster.getClassType().equals("0")){//存储单据保存
			String strLabelNo="N";
			List<Comparable> inList=new ArrayList<Comparable>();
			List<String> outList=new ArrayList<String>();
			List resultList=new ArrayList();
			
			outList.add("S");
			outList.add("S");
			outList.add("S");
			outList.add("S");
			
			inList.add(poMaster.getEnterpriseNo());//strEnterpriseNo
			inList.add(poMaster.getWarehouseNo());//strWareHouseNo
			inList.add("P");//strPaperType
			inList.add(poMaster.getCheckWorker());//strUser_ID
			inList.add("D");//strGetType
			inList.add("1");//nGetNum
			inList.add("2");//strUse_Type
			inList.add("31");//strContainer_Material
			
			resultList = genDao.execProc(inList, outList, "pklg_wms_base.p_get_ContainerNoBase");
			if(resultList.get(3).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(3).toString());
			}	
			strLabelNo=resultList.get(0).toString();
			List saveCheckDataISResult=new ArrayList();//普通保存验收数据返回结果
			for(int i=0;i<list.size();i++){
				saveCheckDataISResult=
						tscSaveCheckDataIS(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),poMaster.getSImportNo(),
						list.get(i).getArticleNo(),list.get(i).getBarcode(),list.get(i).getPackingQty(),list.get(i).getCheckQty(),"N",poMaster.getDockNo(),
						poMaster.getCheckWorker(),poMaster.getCheckTools(),i==0 ? 0:1,list.get(i).getQuality(),
						list.get(i).getProduceDate(),list.get(i).getExpireDate(),list.get(i).getLotNo(),
						list.get(i).getRsvBatch1(),list.get(i).getRsvBatch2(),list.get(i).getRsvBatch3(),list.get(i).getRsvBatch4(),
						list.get(i).getRsvBatch5(),list.get(i).getRsvBatch6(),list.get(i).getRsvBatch7(),list.get(i).getRsvBatch8(),
						strLabelNo,strLabelNo,"3","0",list.get(i).getTemperature());
				if(saveCheckDataISResult.get(1).toString().indexOf("N|")!=-1)
				{
					throw new Exception(saveCheckDataISResult.get(1).toString());
				}
			}
			//验收封板、定位、发单
			List<Comparable> inList2=new ArrayList<Comparable>();
			List<String> outList2=new ArrayList<String>();
			List resultList2=new ArrayList();
			
			outList2.add("S");
			
			inList2.add(poMaster.getEnterpriseNo());//strWareHouseNo
			inList2.add(poMaster.getWarehouseNo());//strWareHouseNo
			inList2.add(poMaster.getOwnerNo());//strOwnerNo
			inList2.add(poMaster.getSImportNo());//strsImportNo
			inList2.add(saveCheckDataISResult.get(0).toString());//strsCheckNo
			inList2.add(strLabelNo);//strLabelNo
			inList2.add(poMaster.getCheckWorker());//strWorkerNo
			inList2.add(printFlag);//strPrintType
			inList2.add("3");//strFixPalFlag
			inList2.add(poMaster.getDockNo());//strDockNo
			System.out.println(inList2);
			resultList2 = genDao.execProc(inList2, outList2, "PKLG_IDATA.P_RF_Close_Pal");
			System.out.println(resultList2);
			if(resultList2.get(0).toString().contains("N|"))
			{
				throw new Exception(resultList2.get(0).toString());
			}	

		}else if(poMaster.getClassType().equals("1")){//直通单据保存
			List<Comparable> inList3=new ArrayList<Comparable>();
			
			List resultList3=new ArrayList();
			
			
			for(int i=0;i<list.size();i++){
				List<String> outList3=new ArrayList<String>();
				outList3.add("S");
				inList3.add(poMaster.getEnterpriseNo());//strEnterpriseNo
				inList3.add(poMaster.getWarehouseNo());//strWareHouseNo
				inList3.add(poMaster.getOwnerNo());//strOwnerNo
				inList3.add(poMaster.getSImportNo());//strsImportNo
				inList3.add(list.get(i).getArticleNo());//strArticleNo
				inList3.add(list.get(i).getBarcode());//strBarcode
				inList3.add(list.get(i).getPackingQty());//nPackingQty
				inList3.add(list.get(i).getCheckQty());//nCheckQty
				inList3.add("N");//strPrinterGroupNo
				inList3.add(poMaster.getDockNo());//strDockNo
				inList3.add(poMaster.getCheckWorker());//strWorkerNo
				inList3.add(poMaster.getCheckTools());//strCheckTools
				inList3.add(i==0 ? 0:1);//nIsAdd
				inList3.add(list.get(i).getQuality());
				inList3.add(list.get(i).getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):list.get(i).getProduceDate());
				inList3.add(list.get(i).getExpireDate()==null?DateUtil.GetDate("19000101", "yyyyMMdd"):list.get(i).getExpireDate());
				inList3.add(list.get(i).getLotNo());
				inList3.add(list.get(i).getRsvBatch1());
				inList3.add(list.get(i).getRsvBatch2());
				inList3.add(list.get(i).getRsvBatch3());
				inList3.add(list.get(i).getRsvBatch4());
				inList3.add(list.get(i).getRsvBatch5());
				inList3.add(list.get(i).getRsvBatch6());
				inList3.add(list.get(i).getRsvBatch7());
				inList3.add(list.get(i).getRsvBatch8());	
				//inList3.add(strTemperature==null?"N":strTemperature);//温度
				inList3.add("N");//strLabelNo
				inList3.add("N");//strSupLabelNo
				inList3.add(printFlag);//strPrintFlag
				inList3.add("3");//strFixPalFlag
				inList3.add("1");//strBusinessType
				inList3.add(printFlag);//strPrintType
				
				System.out.println(inList3);
				resultList3 = genDao.execProc(inList3, outList3, "PKLG_IDATA_ID.P_SAVE_IDCHECK");
				
				if(resultList3.get(0).toString().indexOf("N|")!=-1)
				{
					throw new Exception(resultList3.get(0).toString());
				}
			}
			
		}else{
			throw new Exception("进货类型不存在");
		}
		
		
		return new MsgRes(true, "保存成功","");//保存成功
	}
	/**
	 * 保存(人工板)
	 */
	@Override
	public MsgRes tscSaveCheck2(String strJsonMaster, String strJsonDetail1)
			throws Exception 
	{
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
		List<Idata_CheckDModel> list=JSON.parseArray(strJsonDetail1,Idata_CheckDModel.class);
		
		List saveCheckDataISResult=new ArrayList();//普通保存验收数据返回结果
		List closePalMainResult=new ArrayList();//封板验收主程序返回结果
		List idataSCheckLocateInstockResult=new ArrayList();//根据验收汇总单写标签数据、定位指示和库存返回结果
		List pLocateMainResult=new ArrayList();//进货定位程序入口返回结果
		List pInsertInstockResult=new ArrayList();//上架发单返回结果
		
		for(int i=0;i<list.size();i++)
		{
			saveCheckDataISResult=
					tscSaveCheckDataIS(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),poMaster.getSImportNo(),list.get(i).getArticleNo(),
					list.get(i).getBarcode(),list.get(i).getPackingQty(),list.get(i).getCheckQty(),"N",poMaster.getDockNo(),
					poMaster.getCheckWorker(),poMaster.getCheckTools(),i==0 ? 0:1,
					list.get(i).getQuality(),list.get(i).getProduceDate(),list.get(i).getExpireDate(),list.get(i).getLotNo(),
					list.get(i).getRsvBatch1(),list.get(i).getRsvBatch2(),list.get(i).getRsvBatch3(),list.get(i).getRsvBatch4(),
					list.get(i).getRsvBatch5(),list.get(i).getRsvBatch6(),list.get(i).getRsvBatch7(),list.get(i).getRsvBatch8(),
					list.get(i).getLabelNo(),list.get(i).getSubLabelNo(),"1","0",list.get(i).getTemperature()
					);
			if(saveCheckDataISResult.get(1).toString().indexOf("N|")!=-1)
			{
				throw new Exception(saveCheckDataISResult.get(1).toString());
			}
		}
		String strLabel="";
		for(int i=0;i<list.size();i++)
		{
			if(!strLabel.equals(list.get(i).getLabelNo()))
			{
				strLabel=list.get(i).getLabelNo();
				closePalMainResult=
						tscClosePalMain(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),poMaster.getSImportNo(),
						saveCheckDataISResult.get(0).toString(),strLabel,poMaster.getCheckWorker(),"1");
				if(closePalMainResult.get(0).toString().indexOf("N|")!=-1)
				{
					throw new Exception(closePalMainResult.get(0).toString());
				}
			}
		}
		
		idataSCheckLocateInstockResult=
				tscIdataSCheckLocateInstock(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),saveCheckDataISResult.get(0).toString(),
						poMaster.getCheckWorker(),poMaster.getCheckTools(),"N","N");
		
				if(idataSCheckLocateInstockResult.get(1).toString().indexOf("N|")!=-1)
				{
					throw new Exception(idataSCheckLocateInstockResult.get(1).toString());
				}
		
		pLocateMainResult=
				tscLocateMain(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getOwnerNo(),
						idataSCheckLocateInstockResult.get(0).toString(),"0",poMaster.getCheckWorker());
				if(pLocateMainResult.get(1).toString().indexOf("N|")!=-1)
				{
					throw new Exception(pLocateMainResult.get(0).toString());
				}
		
		pInsertInstockResult=
				tscInsertInstock(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getCheckWorker(),
						idataSCheckLocateInstockResult.get(0).toString(),poMaster.getDockNo(),"0");
				if(pInsertInstockResult.get(1).toString().indexOf("N|")!=-1)
				{
					throw new Exception(pInsertInstockResult.get(1).toString());
				}
		
		return new MsgRes(true, TipUtil.getTipsByKey("E30404"),"");//保存成功
	}
	
	/**
	 * 普通保存验收数据
	 */
	public List tscSaveCheckDataIS(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strsImportNo,
			String strArticleNo,
			String strBarcode,
			Double nPackingQty,
			Double nCheckQty,
			String strPrinterGroupNo,
			String strDockNo,
			String strWorkerNo,
			String strCheckTools,
			Integer nIsAdd,
			String strQuality,
			Date dtProduceDate,
			Date dtExpireDate,
			String strLotNo,
			String strRSV_BATCH1,
			String strRSV_BATCH2,
			String strRSV_BATCH3,
			String strRSV_BATCH4,
			String strRSV_BATCH5,
			String strRSV_BATCH6,
			String strRSV_BATCH7,
			String strRSV_BATCH8,
			String strLabelNo,
			String strSubLabelNo,
			String strFixPalFlag,
			String strBusinessType,
			String strTemperature
			)throws Exception
	{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strsImportNo);//strsImportNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strBarcode);//strBarcode
		inList.add(nPackingQty);//nPackingQty
		inList.add(nCheckQty);//nCheckQty
		inList.add(strPrinterGroupNo);//strPrinterGroupNo
		inList.add(strDockNo);//strDockNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strCheckTools);//strCheckTools
		inList.add(nIsAdd);//nIsAdd
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH1)?"N":strRSV_BATCH1);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH2)?"N":strRSV_BATCH2);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH3)?"N":strRSV_BATCH3);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH4)?"N":strRSV_BATCH4);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH5)?"N":strRSV_BATCH5);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH6)?"N":strRSV_BATCH6);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH7)?"N":strRSV_BATCH7);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH8)?"N":strRSV_BATCH8);	
		inList.add(strTemperature==null?"N":strTemperature);//温度
		inList.add(strLabelNo);//strLabelNo
		inList.add(strSubLabelNo);//strSupLabelNo
		inList.add(strFixPalFlag);//strFixPalFlag
		inList.add(strBusinessType);//strBusinessType
		
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_SaveCheckDataIS");
		System.out.println(inList);
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		return resultList;
	}
	
	/**
	 * 封板验收主程序
	 */
	public List tscClosePalMain(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strsImportNo,
			String strSCheckNo,
			String strLabelNo,
			String strWorkerNo,
			String strFixPalFlag)throws Exception
	{
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strsImportNo);//strsImportNo
		inList.add(strSCheckNo);
		inList.add(strLabelNo);
		inList.add(strWorkerNo);
		inList.add(strFixPalFlag);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.P_Close_Pal_Main");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return resultList;
	}
	
	/**
	 * 根据验收汇总单写标签数据、定位指示和库存
	 * @return
	 * @throws Exception
	 */
	public List tscIdataSCheckLocateInstock(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strScheckNo,
			String strUserId,
			String strCheckTool,
			String strspecifyCellNo,
			String strLabelNo)throws Exception
	{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strScheckNo);//strScheckNo
		inList.add(strUserId);//strUser_ID
		inList.add(strCheckTool);//strCheckTool
		inList.add(strspecifyCellNo);//strspecify_cell_no
		inList.add(strLabelNo);//strLabelNo
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.p_idata_sCheckLocateInstock");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		System.out.println(resultList);
		return resultList;
	}
	
	/**
	 * 进货定位程序入口
	 * @return
	 * @throws Exception
	 */
	public List tscLocateMain(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strOwnerNo,
			String strLocateNo,
			String strPrintFlag,
			String strWorkNo)throws Exception
	{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strLocateNo);//strLocateNo
		inList.add(strPrintFlag);//strPrintFlag
		inList.add(strWorkNo);//strWorkNo
		
		resultList=genDao.execProc(inList, outList, "PKLG_ILOCATE.p_locate_main");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		System.out.println(resultList);
		return resultList;
	}
	
	/**
	 * 上架发单
	 * @return
	 * @throws Exception
	 */
	public List tscInsertInstock(
			String strEnterpriseNo,
			String strWareHouseNo,
			String strWorkerNo,
			String strLocateNo,
			String strDockNo,
			String strPrintType)throws Exception{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strLocateNo);//strLocateNo
		inList.add(strDockNo);//strDockNo
		inList.add("0");//strPrintType0:不打印；1:打印报表；2：打印标签      
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.P_InsertInstock");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		
		if(strPrintType.equals("1")){
			resultList=tscPrint(strEnterpriseNo,strWareHouseNo,resultList.get(0).toString(),strWorkerNo,ReportDefine.L_INSTOCK,strDockNo);
		}else if(strPrintType.equals("2")){
			resultList=tscPrint(strEnterpriseNo,strWareHouseNo,resultList.get(0).toString(),strWorkerNo,ReportDefine.B_I_INSTOCKP,strDockNo);
		}	
		
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		return resultList;
	}
	
	//验收确认货主下拉
	@Override
	public List<ComboxBo> ownerForConfirmCombo(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.owner_no in( " +
				"select owner_no from idata_check_m a " + 
				"where "+
				"a.warehouse_no='"+warehouseNo+"' "+
				"and a.owner_no in ("+workerOwner+") "+
				"and a.status not in ('13','16')) " +
				"and t1.enterprise_no='"+enterpriseNo+"' " ;
			
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	//判断验收确认单是否存在 
	@Override
	public List<String> checkNoCheck(String strEnterpriseNo,
			String strWarehouseNo, String strWorkerOwner, String strQuery)
			throws Exception {
		String strSql="select  a.check_no from idata_check_m a " +
				"where a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.owner_no in("+strWorkerOwner+")"+
			       "and a.status not in('13','16') ";
			
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		List<String> list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	/**
	 * 获取验收确认头档
	 */
	@Override
	public ExtListDataBo<Idata_CheckMModel> queryCheckM(String enterpriseNo,String strWarehouseNo,
			String strWheresql,PageBo poPageBo) throws Exception {
		String strSql="select a.enterprise_no, a.warehouse_no,a.check_no,a.owner_no,a.s_import_no,a.s_check_no,a.import_type,a.import_no, "+
		        "a.supplier_no,sup.supplier_name,a.check_worker,wr.worker_name,a.status," +
		        "nvl(sum(cd.check_qty),0) sumCheckQty, " +
		        "(select sum(iid.po_qty) " +
		        "  from idata_import_d iid where iid.enterprise_no = a.enterprise_no " +
		        "  and iid.warehouse_no = a.warehouse_no and iid.import_no = a.import_no) sumPoQty, " +
		        "(select sum(iid.import_qty) " +
		        "  from idata_import_d iid where iid.enterprise_no = a.enterprise_no  " +
		        "  and iid.warehouse_no = a.warehouse_no and iid.import_no = a.import_no) sumImportQty,"+
		        "f_get_fieldtext('N','STATUS',a.status)statusText ,iim.po_no,  " +
		        " f_get_fieldtext('N', 'SEND_FLAG', iim.send_flag) sendFlagText, " +
		        " (case when (select count(1) from idata_import_d iid where iid.enterprise_no = a.enterprise_no "+
		        " and iid.warehouse_no = a.warehouse_no and iid.owner_no = a.owner_no and iid.import_no = a.import_no "+
		        " and iid.po_qty - iid.import_qty <> 0) > 0 then 1 else 0 end) as isDiffFlag "+
		        "from idata_check_m a,idata_import_m iim,idata_check_d cd, "+
		        "bdef_defsupplier sup ,bdef_defworker wr  "+
		        "where  a.warehouse_no=iim.warehouse_no  "+
		        "and a.enterprise_no=iim.enterprise_no  "+
		        "and a.enterprise_no=cd.enterprise_no(+) "+
		        "and a.warehouse_no=cd.warehouse_no(+) "+
		        "and a.check_no=cd.check_no(+) "+
		        "and a.import_no=iim.import_no  "+
		        "and a.enterprise_no=sup.enterprise_no "+
		        "and a.owner_no=sup.owner_no "+
		        "and a.supplier_no=sup.supplier_no  " +
		        "and a.enterprise_no=wr.enterprise_no " +
		        //"and a.warehouse_no=wr.warehouse_no " +    7-26屏蔽
		        "and a.check_worker=wr.worker_no "+
		    	"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
		        "and a.status<>'13' ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
			strSql=strSql+ft;
		}
		strSql=strSql+" group by a.enterprise_no, a.warehouse_no,a.check_no,a.owner_no,a.s_import_no,a.s_check_no,a.import_type,a.import_no, "+
		        "a.supplier_no,sup.supplier_name,a.check_worker,wr.worker_name,a.status,"+
		        "a.status,iim.po_no,iim.send_flag ";
		strSql=strSql+" order by iim.po_no, a.check_no desc";
		
		String strTotsql = "select count(*) from ("+strSql+") ";
		
		List<Idata_CheckMModel> list = genDao.getListByNativeSql(
				strSql,Idata_CheckMModel.class,poPageBo.getStart(),100);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Idata_CheckMModel> extListBo= new ExtListDataBo<Idata_CheckMModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 获取验收确认明细
	 */
	@Override
	public ExtListDataBo<Idata_CheckDModel> queryCheckD(String enterpriseNo,String strWarehouseNo,String strCheckNo)
			throws Exception {
		String strSql=" select"+
			        "    a.enterprise_no, a.warehouse_no,a.owner_no,"+
			        "    b.owner_article_no as ownerArticleNo,a.check_no,"+
			        "    a.article_no,a.packing_qty,a.barcode,a.produce_date,"+
			        "    a.expire_date,a.quality, a.lot_no,"+
			        "    a.rsv_batch1,a.rsv_batch2,a.rsv_batch3,"+
			        "    a.rsv_batch4,a.rsv_batch5,a.rsv_batch6,a.rsv_batch7,"+
			        "    a.rsv_batch8,a.temperature,a.stock_type,a.stock_value,"+
			        "    sum(a.check_qty) as check_qty, b.article_name,"+
			        
			        "    trunc(sum(a.check_qty)/a.packing_qty) as planBox, "+
			        "    trunc(mod(sum(a.check_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin, "+
			        "    (sum(a.check_qty) - trunc(sum(a.check_qty)/ a.packing_qty) * a.packing_qty - trunc(mod(sum(a.check_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis, " +
			        "    b.unit_packing,b.QMIN_OPERATE_PACKING,"+
			   	
			        //"    nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packingUnit,"+
			        //"    nvl(c.spec, '1*' || a.packing_qty || b.unit) spec," +
			        "    f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit, "+
			        "    f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin, "+
			        "    f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit, "+
			        "    f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec, "+
			        "    f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin, "+
			        "    f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec, "+
			        "    f_get_fieldtext('N', 'QUALITY', a.quality)as qualityText  "+
			       " from "+
			        "    idata_check_d a, "+
			        "    bdef_defarticle b, "+
			        "    bdef_article_packing c     "+
			       " where "+
			        "    a.article_no=b.article_no  "+
			         "   and a.packing_qty=c.packing_qty(+)  "+
			         "   and a.enterprise_no=b.enterprise_no  "+
			         "   and a.enterprise_no=c.enterprise_no(+)  "+
			          "  and a.article_no=c.article_no(+)  "+
			         "   and a.check_no='"+strCheckNo+"'  "+
			         "   and a.warehouse_no='"+strWarehouseNo+"'  "+
			         "   and a.enterprise_no='"+enterpriseNo+"' "+
			      "  group by  a.enterprise_no, "+
			         "   a.warehouse_no, a.owner_no, b.owner_article_no,"+
			         "   a.check_no, a.article_no,a.packing_qty, a.barcode,"+
			         "   a.produce_date,a.expire_date,  a.quality,  a.lot_no,"+
			         "   a.rsv_batch1, a.rsv_batch2,a.rsv_batch3,a.rsv_batch4,"+
			         "   a.rsv_batch5,  a.rsv_batch6, a.rsv_batch7,a.rsv_batch8,a.temperature,a.stock_type,"+
			         "   a.stock_value, b.article_name, c.packing_unit, b.unit,b.unit_packing,b.qmin_operate_packing,b.qmin_operate_packing_unit,"+
			         "   c.spec " +
			         "  order by a.article_no ,a.packing_qty ";
		
		List<Idata_CheckDModel> list = genDao.getListByNativeSql(
				strSql,Idata_CheckDModel.class,0, 10000);
		ExtListDataBo<Idata_CheckDModel> extListBo= new ExtListDataBo<Idata_CheckDModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 获取验收确认板明细
	 */
	@Override
	public ExtListDataBo<Idata_CheckPalModel> queryCheckPal(String enterpriseNo,String strWarehouseNo,String strCheckNo)
			throws Exception {
		String strSql="select a.warehouse_no, a.owner_no, a.s_check_no, a.check_no, a.check_row_id, a.article_no, " +
				"a.packing_qty, a.status, a.label_no, a.printer_group_no, a.dock_no, a.sub_label_no, " +
				"a.barcode, a.produce_date, a.expire_date, f_get_fieldtext('N', 'QUALITY', a.quality)as quality , a.lot_no, a.rsv_batch1, a.rsv_batch2, " +
				"a.rsv_batch3, a.rsv_batch4, a.rsv_batch5, a.rsv_batch6, a.rsv_batch7, a.rsv_batch8, a.stock_type, " +
				"a.stock_value, a.dept_no, a.fixpal_flag, a.container_no, a.business_type, " +
				"a.rgst_name, a.rgst_date, a.updt_name, a.updt_date, a.price, b.article_name,b.owner_article_no as ownerArticleNo," +
				"trunc(sum(a.check_qty)/a.packing_qty) as planBox,  "+
			    "trunc(mod(sum(a.check_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin, "+
			    "(sum(a.check_qty) - trunc(sum(a.check_qty)/ a.packing_qty) * a.packing_qty - trunc(mod(sum(a.check_qty),a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis, " +
			    "b.unit_packing,b.QMIN_OPERATE_PACKING," +
				
				//"nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packingUnit,"+
				//"nvl(c.spec, '1*' || a.packing_qty || b.unit) spec,
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
				"d.temperature "+
				"from idata_check_pal a,bdef_defarticle b,bdef_article_packing c ,idata_check_d d " +
				"where a.article_no=b.article_no and a.article_no=c.article_no(+) " +
				"and a.status<>'16' " +
				"and a.packing_qty=c.packing_qty(+) " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no(+) " +
				"and a.enterprise_no=d.enterprise_no " +
				"and a.warehouse_no=d.warehouse_no " +
				"and a.check_no=d.check_no " +
				"and a.owner_no=d.owner_no " +
				"and a.check_row_id=d.row_id  " +
				"and a.check_no='"+strCheckNo+"' " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"group by a.enterprise_no,a.warehouse_no, a.owner_no, a.s_check_no, a.check_no, a.check_row_id, a.article_no,  "+
		        "a.packing_qty, a.status, a.label_no, a.printer_group_no, a.dock_no, a.sub_label_no,  "+
		        "a.barcode, a.produce_date, a.expire_date, a.quality, a.lot_no, a.rsv_batch1, a.rsv_batch2,  "+
		        "a.rsv_batch3, a.rsv_batch4, a.rsv_batch5, a.rsv_batch6, a.rsv_batch7, a.rsv_batch8, a.stock_type,  "+
		        "a.stock_value, a.dept_no, a.fixpal_flag, a.container_no, a.business_type, "+
		        "a.rgst_name, a.rgst_date, a.updt_name, a.updt_date, a.price, b.article_name,b.owner_article_no,"+
		        "c.packing_unit,b.qmin_operate_packing,b.qmin_operate_packing_unit,c.spec, b.unit,b.unit_packing,d.temperature   order by  a.label_no,a.article_no,a.rgst_date";
		
		List<Idata_CheckPalModel> list = genDao.getListByNativeSql(
				strSql,Idata_CheckPalModel.class,0, 10000/*pageBo.getPagesize()*/);
		ExtListDataBo<Idata_CheckPalModel> extListBo= new ExtListDataBo<Idata_CheckPalModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 验收确认
	 */
	@Override
	public MsgRes tscConfirm(String strJsonMaster, String flag) throws Exception {
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
	    if(!poMaster.getMemo().equals("")){
	    	String sql ="update idata_check_m m " +
	    			"set m.memo='"+poMaster.getMemo()+"' " +
	    			"where m.enterprise_no='"+poMaster.getEnterpriseNo()+"' " +
	    			"and m.warehouse_no='"+poMaster.getWarehouseNo()+"' " +
	    			"and m.s_check_no='"+poMaster.getSCheckNo()+"' ";
	    	genDao.updateBySql(sql);
	    }
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(poMaster.getEnterpriseNo());//strENTERPRISE_NO
		inList.add(poMaster.getWarehouseNo());//strWAREHOUSE_NO
		inList.add(poMaster.getSImportNo());//strS_import_no
		inList.add(poMaster.getSCheckNo());//strS_Check_no
//		inList.add(poMaster.getPoNo());//strPoNo
		inList.add(poMaster.getCheckWorker());//strWorkerNo
		inList.add(poMaster.getUnloadWorker());//卸货人员 新增的参数
		inList.add(poMaster.getDockNo());//strDockNo
		System.out.println(strJsonMaster);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.P_Comfire_Idata_Check");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		
		if(flag.equals("1")){
			tscPrintCheckNo(poMaster.getEnterpriseNo(),poMaster.getWarehouseNo(),poMaster.getDockNo(),
					        poMaster.getCheckWorker(),poMaster.getSCheckNo());
		}
		
		return new MsgRes(true,"确认成功","");
	}
	
	/**
	 * 填充货主下拉
	 */
	@Override
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo,String workerOwner) throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.owner_no in( " +
				"select owner_no from idata_import_mm a " + 
				"where "+
				"a.warehouse_no='"+warehouseNo+"' "+
				"and a.owner_no in ("+workerOwner+") "+
				"and a.status not in ('13','16')) " +
				"and t1.enterprise_no='"+enterpriseNo+"' " ;
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 校验板号
	 */
	@Override
	public MsgRes IdataCheckLabelNo(String strWarehouseNo,String strLabelNo) throws Exception {
		//检查临时板表
		String strSql="select icpt.s_check_no " +
				"from idata_check_pal_tmp icpt  " +
				"where icpt.warehouse_no='"+strWarehouseNo+"' " +
				"and icpt.label_no='"+strLabelNo+"' and rownum<2";
		List<String> list=genDao.getListByNativeSql(strSql);
		if(list.size()!=0)
		{
			return new MsgRes(false,"该板号已被使用","");
		}
		//检查标签表
		strSql="select label_no from stock_label_m " +
				"where label_no='"+strLabelNo+"' " +
				"and status<>'0' " +
				"and warehouse_no='"+strWarehouseNo+"'";
		list=genDao.getListByNativeSql(strSql);
		if(list.size()!=0)
		{
			return new MsgRes(false,"该板号已被使用","");
		}
		//检查库存表
		strSql="select label_no from stock_content " +
				"where label_no='"+strLabelNo+"' " +
				"and outstock_qty>0 and instock_qty>0 and unusual_qty>0 " +
				"and warehouse_no='"+strWarehouseNo+"'";
		list=genDao.getListByNativeSql(strSql);
		if(list.size()!=0)
		{
			return new MsgRes(false,"该板号已有商品","");
		}
		return new MsgRes(true,"","");
	}
	
	/**
	 * RF扫描流水号
	 */
	@Override
	public MsgRes idataCheckSerialNo(String strRecvData,String strClassType) throws Exception {
		IdataSerialNoRequestModel reqMod=JSON.parseObject(strRecvData, IdataSerialNoRequestModel.class);
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strOwnerPermissions=reqMod.getOwnerPermissions();
		String strSerialNo=reqMod.getSerialNo();
		
		MsgRes msgRes=new MsgRes();
		String strSql="select iimm.warehouse_no," +
					"iimm.import_type as importType," +
					"iimm.owner_no as OwnerNo," +
					"iimm.s_import_no as simportNo," +
					"iimm.supplier_no as SupplierNo," +
					"iimm.dept_no as DeptNo," +
					"sysdate as DateNow  " +
				"from " +
					"idata_import_mm iimm " +
				"where " +
					"iimm.status in('10','11','12','14','15') " +
					//"and iimm.import_type='" +strImportType+"' "+
					"and iimm.serial_no='"+strSerialNo+"' " +
					"and iimm.warehouse_no='"+strWarehouseNo+"' " +
					"and iimm.enterprise_no='"+strEnterpriseNo+"' " +
					"and iimm.owner_no in("+strOwnerPermissions+")";
		List<SerialNoAnswerModel> list = genDao.getListByNativeSql(strSql, SerialNoAnswerModel.class);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("流水号不存在！");
			return msgRes;
		}else
		{
			//获取单据类型判断是否存储 huangb 20160715
			List<Wms_IDataTypeModel> listClassType = getSystemParameterImpl.getIdataTypeStrategy
					(strEnterpriseNo, strWarehouseNo, list.get(0).getOwnerNo(), list.get(0).getImportType(), "class_type");
			if(listClassType.size()==0){
				msgRes.setIsSucc(false);
				msgRes.setMsg("订单类型没有配置！");
				return msgRes;
			}
            if(!listClassType.get(0).getColumnValue().equals(strClassType)){
            	if(strClassType.equals("0")){
	            	msgRes.setIsSucc(false);
					msgRes.setMsg("该流水号不是存储类型！");
					return msgRes;
            	}else{
            		msgRes.setIsSucc(false);
					msgRes.setMsg("该流水号不是直通类型！");
					return msgRes;
            	}
            }
			
			//获取品质参数 huangb 20160715
			String strCheckOverFlag = "";
			List<Wms_IDataTypeModel> listCheckOverFlag = getSystemParameterImpl.getIdataTypeStrategy
					(strEnterpriseNo, strWarehouseNo, list.get(0).getOwnerNo(), list.get(0).getImportType(), "quality_flag");
			if(listCheckOverFlag.size()==0){
				strCheckOverFlag="0";
			}else{
				strCheckOverFlag=listCheckOverFlag.get(0).getColumnValue();
			}
			list.get(0).setQualityFlag(strCheckOverFlag);
			
			msgRes.setIsSucc(true);
			msgRes.setObj(JSON.toJSONString(list.get(0)));
		}
		return msgRes;
	}
	
	/**
	 * 查询批属性(RF)
	 * @param strArticleNo
	 * @param strLotNo
	 * @return
	 */
	public List<IdataGetLotAnswerModel> queryBdefArticleLotManage(String strArticleNo,String strLotNo)throws Exception
	{
		String strSql="select * from bdef_article_lot_manage a where a.article_no='"+strArticleNo+"' and a.lot_no='"+strLotNo+"'";
		List<IdataGetLotAnswerModel> list = genDao.getListByNativeSql(strSql, IdataGetLotAnswerModel.class);
		return list;
	}
	
	/**
	 * 直通验收扫条码找单(RF)
	 */
	@Override
	public MsgRes queryRFCheckD(String strRecvData)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		BarcodeRequestModel reqMod=JSON.parseObject(strRecvData, BarcodeRequestModel.class);
		
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strOwnerNo=reqMod.getOwnerNo();
		String barcode=reqMod.getBarcode();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strSImportNo=reqMod.getSImportNo();
		
		String strSql="";
		//先根据采购单号查询
		strSql=" select distinct iim.po_no  " +
				"from idata_import_m iim, idata_import_d iid , bdef_defarticle bda " +
				   " where iid.enterprise_no = bda.enterprise_no " +
				   " and iid.article_no= bda.article_no " +
				   " and iim.po_no like '%"+ barcode +"'" +
				   " and iid.enterprise_no=iim.enterprise_no " +
				   " and iid.warehouse_no=iim.warehouse_no " +
				   " and iid.import_no=iim.import_no " +
				   " and iim.order_end_date>trunc(sysdate) " +
				   " and iim.enterprise_no='"+strEnterpriseNo+"' " +
				   " and iim.warehouse_no='"+strWarehouseNo+"' " +
				   " and iim.owner_no in ("+strOwnerNo+") " +
				   " and iim.status not in ('13','16') " +
				   " and iim.po_type='ID' ";
		List<IdataInStockBarcodeRequestModel> listPoNo = genDao.getListByNativeSql(strSql,IdataInStockBarcodeRequestModel.class);
		if(listPoNo.size()>0){
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONArray.fromObject(listPoNo));
			return msgRes;
		}
		else{
			msgRes.setIsSucc(false);
			msgRes.setMsg("找不到该采购单");
			return msgRes;
		}
		/*//根据单号获取不到数据则按条码查询
		//校验条码
		msgRes = getArticleForBarcodeImpl.checkBarcode(strWarehouseNo, barcode, strEnterpriseNo);
		if(!msgRes.getIsSucc())
		{
			msgRes.setMsg("输入条码有误！");
			return msgRes;
		}
		if(msgRes.getObj().toString().equals("") || msgRes.getObj() == null)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码不存在！");
			return msgRes;
		}
		//根据扫描的商品条码查找商品信息
		strSql=" select distinct iim.po_no  " +
				"from idata_import_m iim, idata_import_d iid , bdef_defarticle bda " +
				   " where iid.enterprise_no = bda.enterprise_no " +
				   " and iid.article_no= bda.article_no " +
				   " and iid.article_no in ("+ msgRes.getObj().toString() +")" +
				   //" and bda.barcode='"+barcode+"' " +
				   " and iid.enterprise_no=iim.enterprise_no " +
				   " and iid.warehouse_no=iim.warehouse_no " +
				   " and iid.import_no=iim.import_no " +
				   " and iim.order_end_date>trunc(sysdate) " +
				   " and iim.enterprise_no='"+strEnterpriseNo+"' " +
				   " and iim.warehouse_no='"+strWarehouseNo+"' " +
				   " and iim.owner_no in ("+strOwnerNo+") " +
				   " and iim.status not in ('13','16') " +
				   " and iim.po_type='ID' ";
		List<IdataInStockBarcodeRequestModel> list1 = genDao.getListByNativeSql(strSql,IdataInStockBarcodeRequestModel.class);
		System.out.println(JSONArray.fromObject(list1));
		MsgRes msg = new MsgRes();
		if(list1.size()>0){
			msg.setIsSucc(true);
			msg.setObj(JSONArray.fromObject(list1));
			return msg;
		}else{
			msg.setIsSucc(false);
			msg.setMsg("找不到该条码");
			return msg;
		}*/
	}
	@Override
	public MsgRes IdataISDockValidate(String strRecvData)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		
		String strSql="select * " +
					  "from " +
							"PNTSET_PRINTER_WORKSTATION a " +
					  "where " +
							"a.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
							"and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
							"and a.workstation_no='"+reqMod.getReqObj()+"'";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入的码头不存在！");
		}
		else
		{msgRes.setIsSucc(true);}		
		return msgRes;
	}
	@Override
	public MsgRes IdataDockValidate(String strRecvData)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataDockIDModel reqMod=JSON.parseObject(strRecvData, IdataDockIDModel.class);
		
		String strSql="select * " +
					  "from " +
							"PNTSET_PRINTER_WORKSTATION a " +
					  "where " +
							"a.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
							"and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
							"and a.workstation_no='"+reqMod.getDock()+"'";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入的码头不存在！");
		}else
		{
			strSql="select distinct a.sub_label_no,a.article_no,c.po_no,a.barcode " +
					" from idata_check_pal_tmp a ,idata_import_sm b ,idata_import_m c " +
					"where a.enterprise_no ='"+reqMod.getEnterpriseNo()+"' " +
					"  and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					"  and a.dock_no='"+reqMod.getDock()+"' " +
					"  and a.enterprise_no=b.enterprise_no " +
					"  and a.warehouse_no=b.warehouse_no " +
					"  and a.s_import_no = b.s_import_no " +
					"  and b.enterprise_no = c.enterprise_no " +
					"  and b.warehouse_no = c.warehouse_no " +
					"  and b.import_no = c.import_no ";
			
			List<IdataDockIDModel> exitsTmp = genDao.getListByNativeSql(strSql,IdataDockIDModel.class);	
			
			if(exitsTmp.size()==0){
				msgRes.setIsSucc(true);				
			}else{
				msgRes.setIsSucc(true);
				msgRes.setObj(JSONArray.fromObject(exitsTmp.get(0)));
			}
			
			
			
		}
		return msgRes;
	}
	//RF封板及写标签数据、定位指示和库存
	@Override
	public MsgRes tscRfClosePalWriteLocate(String strRecvData)throws Exception {
		IdataCloseLablRequestModel resMod=JSON.parseObject(strRecvData, IdataCloseLablRequestModel.class);
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(resMod.getEnterpriseNo());
		inList.add(resMod.getWarehouseNo());
		inList.add(resMod.getOwnerNo());
		inList.add(resMod.getSimportNo());
		inList.add(resMod.getScheckNo());
		inList.add(resMod.getLabelNo());
		inList.add(resMod.getWorkerNo());
		inList.add("2");
		inList.add(resMod.getFixPalFlag());
		inList.add("001");
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_IDATA.P_RF_Close_Pal");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"封板成功","");		
	}
	//直通验收保存
	@Override
	public List tscSaveCheckDataID(String strEnterpriseNo,
									String strWareHouseNo, 
									String strOwnerNo,
									String strsImportNo, 
									String strArticleNo, 
									String strBarcode,
									Double nPackingQty, 
									Double nCheckQty,
									String strPrinterGroupNo,
									String strDockNo,
									String strWorkerNo,
									String strCheckTools,
									Integer nIsAdd,
									String strQuality,
									Date dtProduceDate,
									Date dtExpireDate,
									String strLotNo,
									String strRSV_BATCH1,
									String strRSV_BATCH2,
									String strRSV_BATCH3,
									String strRSV_BATCH4,
									String strRSV_BATCH5,
									String strRSV_BATCH6,
									String strRSV_BATCH7,
									String strRSV_BATCH8,
									String strLabelNo,
									String strSubLabelNo,
									String strFixPalFlag,
									String strBusinessType) throws Exception {
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strsImportNo);//strsImportNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strBarcode);//strBarcode
		inList.add(nPackingQty);//nPackingQty
		inList.add(nCheckQty);//nCheckQty
		inList.add(strPrinterGroupNo);//strPrinterGroupNo
		inList.add(strDockNo);//strDockNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strCheckTools);//strCheckTools
		inList.add(nIsAdd);//nIsAdd
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH1)?"N":strRSV_BATCH1);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH2)?"N":strRSV_BATCH2);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH3)?"N":strRSV_BATCH3);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH4)?"N":strRSV_BATCH4);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH5)?"N":strRSV_BATCH5);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH6)?"N":strRSV_BATCH6);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH7)?"N":strRSV_BATCH7);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH8)?"N":strRSV_BATCH8);		
		inList.add(strLabelNo);//strLabelNo
		inList.add(strSubLabelNo);//strSupLabelNo
		inList.add(strFixPalFlag);//strFixPalFlag
		inList.add(strBusinessType);//strBusinessType
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_SaveCheckDataID_C");
		System.out.println(inList);
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		return resultList;
	}
	//直通验收确认,一单一验，可直接对进货单做结案
	@Override
	public List tscDataComfireCheck(String strEnterpriseNo,
			String strWareHouseNo, 
			String strsImportNo, 
			String strSCheckNo, 
			String strWorkerNo, 
			String strDockNo) throws Exception {
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strsImportNo);//strsImportNo
		inList.add(strSCheckNo);//strsImportNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strDockNo);//strDockNo
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_idata_comfireCheck");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return resultList;
	}
	@Override
	public List tscOdataDivideBegin(String strEnterpriseNo,String strWareHouseNo, 
			String strOwnerNo,
			String strDockNo, 
			String strSourceNo, //来源单号
			String strSContainerNo,//来源容器
			String strWorkerNo, //预定分播人员
			String strUserId, //系统操作人员
			String strPrintFlag //--是否打印 0=否 1=是
			) throws Exception {
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOWNER_NO
		inList.add(strDockNo);//strDockNo
		inList.add(strSourceNo);//strSource_No
		inList.add(strSContainerNo);//strS_CONTAINER_NO
		inList.add(strWorkerNo);//strAssign_No
		inList.add(strUserId);//strUserID
		inList.add(strPrintFlag);//strPrintFlag
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DIVIDE.P_O_ODATA_DIVIDE_BEGIN");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return resultList;
	}
	
	//直通封板业务处理
	@Override
	public MsgRes IdataCloseLabelID(String strEnterpriseNo,String strWareHouseNo, String strsImportNo,
			String strSCheckNo, String strWorkerNo, String strDockNo)
			throws Exception {
		
		return null;
	}
	@Override
	public MsgRes IdataCheckLabelNo(String strEnterpriseNo, 
			String strWarehouseNo, 
			String strSImportNo,
			String strLabelNo) throws Exception {
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(strLabelNo);
		inList.add(strSImportNo);
		
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_CheckLabelNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"",resultList.get(0).toString());
		
	}
	//校验是否超量
	//huangb 20160711 新增strOverFlag传参 是否超品
	@Override
	public MsgRes CheckExcess(String strEnterpriseNo, 
			String strWareHouseNo,
			String strOwnerNo,
			String strSimportNo,
			String strArticleNo,
			Double strPackingQty,
			Date dtProduceDate,
			Date dtExpireDate,
			Double ncheckQty,
			String strOverFlag) throws Exception {
		MsgRes msgRes=new MsgRes();		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOWNER_NO
		inList.add(strSimportNo);//
		inList.add(strArticleNo);//
		inList.add(strPackingQty);
		inList.add(dtProduceDate);//
		inList.add(dtExpireDate);//dtExpireDate
		inList.add(ncheckQty);//ncheckQty
		inList.add(strOverFlag);//超品标识，0-正常；1-超品
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_CheckExists");
		if(resultList.get(2).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(2).toString());
		}		
		return new MsgRes(true,"校验通过！",resultList);
	}
	@Override
	public MsgRes IdataCheckBarcode(String strWarehouseNo, String strBarcode,
			String strOwnerNo, String strSImportNo) throws Exception {
		
		return null;
	}
	@Override
	public MsgRes tscPLocateMain(String strEnterpriseNo,
			String strWareHouseNo, 
			String strOwnerNo,
			String strLocateNo, 
			String strPrintFlag, 
			String strWorkNo)
			throws Exception {
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);//strWareHouseNo
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOWNER_NO
		inList.add(strLocateNo);//strLocateNo
		inList.add(strPrintFlag);//strPrintFlag
		inList.add(strWorkNo);//strWorkNo
		
		resultList = genDao.execProc(inList, outList, "PKLG_ILOCATE.p_locate_main");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		return new MsgRes(true,"定位成功！","");
	}
	
	/**
	 * 扫物流码过程
	 */
	public MsgRes tscInsert_ScanLabelNoLog(
			String strEnterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strScanBarcode,
			String strSourceNo,
			String strArticleNo,
			String strCellNo,
			String strCheckType,
			String strWorkerNo,
			String strLabelNo,
			String strType) throws Exception{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);//strWarehouseNo
		inList.add(strWarehouseNo);//strWarehouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strScanBarcode);//strScanBarcode
		inList.add(strSourceNo);//strSourceNo
		inList.add(strArticleNo);//strSourceNo
		inList.add(strCellNo);//strCellNo
		inList.add(strCheckType);//strCheckType
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strLabelNo);//strLabelNo
		inList.add(strType);//strType
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.P_Insert_ScanLabelNoLog");
		if(resultList.get(0).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"扫物流成功",""); 
	}
	
	/**
	 * 删除bdef_scan_log表
	 */
	public MsgRes tscDeleteBdefScanLog(String strRecvData) throws Exception{
		IdataIMLogisticsCodeRequestModel req=JSON.parseObject(strRecvData, IdataIMLogisticsCodeRequestModel.class);
		String strEnterpriseNo=req.getEnterpriseNo();
		String strWarehouseNo=req.getWarehouseNo(); 
		String strSourceNo=req.getSourceNo(); 
		String strLabelNo=req.getLabelNo();
		String strArticleNo=req.getArticleNo();
		String strRgstName=req.getWorkerNo();
		
		String strSql="update " +
							"stock_box_m a " +
					  "set " +
					  		"a.status='0'," +
					  		"a.s_import_no=null " +
					  "where " +
					  		"exists (select 1 " +
					  				"from " +
					  					"bdef_scan_log b " +
					  				"where " +
					  					"a.s_import_no=b.source_no " +
										"and a.box_no=b.scan_barcode " +
										"and b.label_no='"+strLabelNo+"' " +
										"and b.rgst_name='"+strRgstName+"' " +
										"and b.source_no='"+strSourceNo+"' " +
										"and b.ARTICLE_NO='" +strArticleNo+"' "+
										"and b.warehouse_no='"+strWarehouseNo+"' " +
										"and b.enterprise_no='"+strEnterpriseNo+"' " +
										"and b.merge_box_no=b.scan_barcode" +
									")";
		genDao.updateBySql(strSql);
		strSql="delete " +
					"bdef_scan_log bsl " +
				"where " +
					"bsl.warehouse_no='"+strWarehouseNo+"' " +
					"and bsl.enterprise_no='"+strEnterpriseNo+"' " +
					"and bsl.source_no='"+strSourceNo+"' " +
					"and bsl.label_no='"+strLabelNo+"' " +
					"and bsl.ARTICLE_NO='" +strArticleNo+"' "+
					"and bsl.rgst_name='"+strRgstName+"'";
		genDao.updateBySql(strSql);
		return new MsgRes(true,"删除成功","");
	}
	@Override
	public MsgRes tscISSaveAndColse(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes(true, "", null);
		IdataCheckRequestModel reqMod=JSON.parseObject(strRecvData, IdataCheckRequestModel.class);
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo
		inList.add(reqMod.getWarehouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSimportNo());//strsImportNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getBarcode());//strBarcode
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty
		inList.add(Double.parseDouble(reqMod.getCheckQty()));//nCheckQty
		inList.add(reqMod.getPrinterGroupNo());//strPrinterGroupNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getWorkerNo());//strWorkerNo
		inList.add(reqMod.getCheckTools());//strCheckTools
		inList.add(Integer.parseInt(reqMod.getIsAdd()));//nIsAdd
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
		inList.add(StringUtil.isStrEmpty(reqMod.getTemperature())?"N":reqMod.getTemperature());//温度
		inList.add(reqMod.getLabelNo());//strLabelNo
		inList.add(reqMod.getSupLabelNo());//strSupLabelNo
		inList.add("2");//strPrintType
		inList.add(reqMod.getFixPalFlag());//strFixPalFlag
		inList.add(reqMod.getBusinessType());//strBusinessType
		inList.add(reqMod.getCheckType());//strBusinessType
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.P_RF_SaveCheckDataIS");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}				
		/*//普通保存验收数据
		List listR=new ArrayList();//普通保存验收数据返回结果
		listR=tscSaveCheckDataIS(
				strEnterpriseNo,
				strWareHouseNo, 
				strOwnerNo, 
				strsImportNo, 
				strArticleNo, 
				strBarcode, 
				nPackingQty, 
				nCheckQty, 
				strPrinterGroupNo, 
				strDockNo, 
				strWorkerNo, 
				strCheckTools, 
				nIsAdd, 
				strQuality, 
				dtProduceDate, 
				dtExpireDate, 
				strLotNo, 
				strRSV_BATCH1, 
				strRSV_BATCH2, 
				strRSV_BATCH3, 
				strRSV_BATCH4, 
				strRSV_BATCH5, 
				strRSV_BATCH6, 
				strRSV_BATCH7, 
				strRSV_BATCH8, 
				strLabelNo, 
				strSubLabelNo, 
				strFixPalFlag, 
				strBusinessType);
		
		//F2:扫商品扫板、F3:整单验收，则调用封板
		if("F2".equals(strCheckType) || "F3".equals(strCheckType))
		{
			//調用封板程序
			msgRes=tscRfClosePalWriteLocate(
					strEnterpriseNo,
					strWareHouseNo, 
					strOwnerNo, 
					strsImportNo, 
					listR.get(0).toString(), 
					strLabelNo, 
					strWorkerNo, 
					strFixPalFlag, 
					strDockNo);
		}*/
		//F2:扫商品扫板，则判断是否还有未验数据
		if("F2".equals(reqMod.getCheckType()))
		{
			String strQty=getArcticNoCheckInfo(reqMod.getEnterpriseNo(),
					reqMod.getWarehouseNo(), 
					reqMod.getOwnerNo(), 
					reqMod.getSimportNo(), 
					reqMod.getArticleNo(), 
					Double.parseDouble(reqMod.getPackingQty()));
			CommSingleDataRequestModel singAnsMod=new CommSingleDataRequestModel();
			singAnsMod.setReqObj(strQty);
			singAnsMod.setWarehouseNo(reqMod.getWarehouseNo());
			msgRes.setObj(JSON.toJSONString(singAnsMod));
		}
		return msgRes;
	}
	//读取商品未验量	
	private String getArcticNoCheckInfo(String strEnterpriseNo,
			String strWarehouseNo,
			String strOwnerNo,
			String strSImportNo,
			String strArticleNo,
			Double nPackingQty)
	{
		String strSql="select        " +
				"a.po_qty-a.import_qty po_qty        " +
				"from idata_import_sd a        " +
				"where a.po_qty-a.import_qty>0 " +
				"and a.warehouse_no = '"+strWarehouseNo+"' " +
				"and a.enterprise_no='" +strEnterpriseNo+"' "+
				"and a.owner_no = '"+strOwnerNo+"' " +
				"and a.s_import_no = '"+strSImportNo+"' " +
				"and a.article_no='"+strArticleNo+"' " +
				"and a.packing_qty="+nPackingQty+" ";
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return list.get(0).toString();
		}else{		
			return "0";
		}
	}
	
	/**
	 * 校验临时表中是否还有数据
	 */
	@Override
	public MsgRes checkPalTmp(String enterpriseNo,String strWarehouseNo, String strSImportNo)
			throws Exception {
		String strSql="select warehouse_no " +
				"from idata_check_pal_tmp icpt " +
				"where icpt.warehouse_no='"+strWarehouseNo+"' " +
				"and icpt.enterprise_no='"+enterpriseNo+"' "+
				"and icpt.s_import_no='"+strSImportNo+"'";
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(false,"","");
		}else{
			return new MsgRes(true,"","");
		}
	}
	
	//验收确认已验收未封板明细
	@Override
	public ExtListDataBo<Idata_CheckPalModel> queryUnCheckPal(String enterpriseNo,String warehouseNo,String sImportNo)
			throws Exception {
		String strSql="select a.warehouse_no, a.owner_no, a.s_check_no, a.article_no, " +
				"a.packing_qty, a.check_qty, a.status, a.label_no, a.printer_group_no, a.dock_no, a.sub_label_no, " +
				"a.barcode, a.produce_date, a.expire_date, a.quality, a.lot_no, a.rsv_batch1, a.rsv_batch2, " +
				"a.rsv_batch3, a.rsv_batch4, a.rsv_batch5, a.rsv_batch6, a.rsv_batch7, a.rsv_batch8, a.stock_type, " +
				"a.stock_value, a.dept_no, a.fixpal_flag,  a.cust_no, a.business_type, " +
				"a.rgst_name, a.rgst_date,  b.article_name,b.owner_article_no as ownerArticleNo," +
				
				"trunc(a.check_qty/a.packing_qty) as planBox,  "+
				"trunc(mod(a.check_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin, "+
				"(a.check_qty - trunc(a.check_qty/ a.packing_qty) * a.packing_qty - trunc(mod(a.check_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis, " +
				"b.unit_packing,b.QMIN_OPERATE_PACKING," +

				//"nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packingUnit,"+
				//"nvl(c.spec, '1*' || a.packing_qty || b.unit) spec," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
				"a.temperature " +
				"from idata_check_pal_tmp a,bdef_defarticle b,bdef_article_packing c " +
				"where a.article_no=b.article_no and a.article_no=c.article_no(+) " +
				"and a.packing_qty=c.packing_qty(+) " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no(+) " +
				"and a.s_import_no='"+sImportNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		
		List<Idata_CheckPalModel> list = genDao.getListByNativeSql(
				strSql,Idata_CheckPalModel.class,0, 10000);
		ExtListDataBo<Idata_CheckPalModel> extListBo= new ExtListDataBo<Idata_CheckPalModel>(list, 0);
        return extListBo;
	}
	
	//获取批次
	@Override
	public List<ComboxBo> getlotNo(String enterpriseNo,String warehouseNo,String strQuery,String strWheresql) throws Exception {
		String strSql="select a.lot_no value,a.lot_no text,a.lot_no dropValue from bdef_article_lot_manage a "+
				"where 1=1 "+
				" and lot_no <>'N' " +
				" and enterprise_no='"+enterpriseNo+"' " +
				" and warehouse_no='"+warehouseNo+"' ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ws;
			
		}
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and a.lot_no like '%"+strWheresql+"%'";
		}
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes tscIdataCheckIDTTH(String strEnterpriseNo,String strWareHouseNo, String strOwnerNo,
			String strsImportNo, String strArticleNo, String strBarcode,
			Double nPackingQty, Double nCheckQty, String strPrinterGroupNo,
			String strDockNo, String strWorkerNo, String strCheckTools,
			Integer nIsAdd, String strQuality, Date dtProduceDate,
			Date dtExpireDate, String strLotNo, String strRSV_BATCH1,
			String strRSV_BATCH2, String strRSV_BATCH3, String strRSV_BATCH4,
			String strRSV_BATCH5, String strRSV_BATCH6, String strRSV_BATCH7,
			String strRSV_BATCH8, String strLabelNo, String strSubLabelNo,
			String strFixPalFlag, String strBusinessType) throws Exception {
		List list=new ArrayList();//普通保存验收数据返回结果
		MsgRes msgRes=new MsgRes();
		//校验板号是否可用
		msgRes=IdataCheckLabelNo(strEnterpriseNo,strWareHouseNo,
				strsImportNo, 
				strLabelNo);
		if(!msgRes.getIsSucc())
		{
			return msgRes;
		}
		//保存
		list=tscSaveCheckDataID(strEnterpriseNo,strWareHouseNo, 
				strOwnerNo, 
				strsImportNo, 
				strArticleNo,
				strBarcode, 
				nPackingQty,
				nCheckQty,
				strPrinterGroupNo,
				strDockNo,
				strWorkerNo,
				strCheckTools,
				nIsAdd,
				strQuality,
				dtProduceDate,
				dtExpireDate,
				strLotNo,
				strRSV_BATCH1, 
				strRSV_BATCH2,
				strRSV_BATCH3,
				strRSV_BATCH4,
				strRSV_BATCH5,
				strRSV_BATCH6,
				strRSV_BATCH7,
				strRSV_BATCH8,
				strLabelNo,
				strSubLabelNo,
				strFixPalFlag,
				strBusinessType);
		if(list.get(0).toString().indexOf("N|")!=-1)
		{
			msgRes.setIsSucc(false);
			msgRes.setObj("保存失败！");
		}else
		{
			msgRes.setIsSucc(true);
			msgRes.setObj("保存成功！");
		}
		return msgRes;
	}
	@Override
	public List tscSaveCheckDataIDTTH(String strEnterpriseNo,String strWareHouseNo, String strOwnerNo,
			String strsImportNo, String strArticleNo, String strBarcode,
			Double nPackingQty, Double nCheckQty, String strPrinterGroupNo,
			String strDockNo, String strWorkerNo, String strCheckTools,
			Integer nIsAdd, String strQuality, Date dtProduceDate,
			Date dtExpireDate, String strLotNo, String strRSV_BATCH1,
			String strRSV_BATCH2, String strRSV_BATCH3, String strRSV_BATCH4,
			String strRSV_BATCH5, String strRSV_BATCH6, String strRSV_BATCH7,
			String strRSV_BATCH8, String strLabelNo, String strSubLabelNo,
			String strFixPalFlag, String strBusinessType) throws Exception {
		MsgRes msgRes=new MsgRes();
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(strEnterpriseNo);//strEnterpriseNo
		inList.add(strWareHouseNo);//strWareHouseNo
		inList.add(strOwnerNo);//strOwnerNo
		inList.add(strsImportNo);//strsImportNo
		inList.add(strArticleNo);//strArticleNo
		inList.add(strBarcode);//strBarcode
		inList.add(nPackingQty);//nPackingQty
		inList.add(nCheckQty);//nCheckQty
		inList.add(strPrinterGroupNo);//strPrinterGroupNo
		inList.add(strDockNo);//strDockNo
		inList.add(strWorkerNo);//strWorkerNo
		inList.add(strCheckTools);//strCheckTools
		inList.add(nIsAdd);//nIsAdd		
		inList.add(StringUtil.isStrEmpty(strQuality)?"0":strQuality);
		inList.add(dtProduceDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtProduceDate);
		inList.add(dtExpireDate==null? DateUtil.GetDate("19000101", "yyyyMMdd"):dtExpireDate);
		inList.add(StringUtil.isStrEmpty(strLotNo)?"N":strLotNo);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH1)?"N":strRSV_BATCH1);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH2)?"N":strRSV_BATCH2);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH3)?"N":strRSV_BATCH3);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH4)?"N":strRSV_BATCH4);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH5)?"N":strRSV_BATCH5);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH6)?"N":strRSV_BATCH6);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH7)?"N":strRSV_BATCH7);
		inList.add(StringUtil.isStrEmpty(strRSV_BATCH8)?"N":strRSV_BATCH8);		
		inList.add(strLabelNo.trim().equals("")? "N":strLabelNo);//strLabelNo
		inList.add(strSubLabelNo.trim().equals("")? "N":strSubLabelNo);//strSupLabelNo
		inList.add(strFixPalFlag);//strFixPalFlag
		inList.add(strBusinessType);//strBusinessType
		
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA.Proc_save_idcheck");
		System.out.println(inList);
				
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		return resultList;
	}
	@Override
	public MsgRes tscRfInsert_ScanLabelNoLog(String strRecvData)
			throws Exception {
		IdataIMLogisticsCodeRequestModel req=JSON.parseObject(strRecvData, IdataIMLogisticsCodeRequestModel.class);
		return tscInsert_ScanLabelNoLog(
				req.getEnterpriseNo(),
				req.getWarehouseNo(),
				req.getOwnerNo(),
				req.getScanBarcode(),
				req.getSourceNo(),
				req.getArticleNo(),
				"N",
				"1",
				req.getWorkerNo(),
				req.getLabelNo(),
				req.getType());
	}
	
	//rf校验是否能验收
	@Override
	public MsgRes tscRfCheckExists(String strRecvData) throws Exception {
		IdataCheckExistsRequestModel req=JSON.parseObject(strRecvData, IdataCheckExistsRequestModel.class);
		MsgRes msgRes=new MsgRes();
		msgRes=CheckExcess(req.getEnterpriseNo(),
				req.getWareHouseNo(), 
				req.getOwnerNo(), 
				req.getSimportNo(), 
				req.getArticleNo(), 
				req.getPackingQty(),
				DateUtil.GetDate(req.getProduceDate(), "yyyyMMdd"),
				DateUtil.GetDate(req.getExpireDate(), "yyyyMMdd"),
				Double.parseDouble(req.getCheckQty()),
				req.getOverFlag());
		if(msgRes.getIsSucc())
		{
			IdataCheckExistsAnswer existsAns=new IdataCheckExistsAnswer();
			List list=(List) msgRes.getObj();
			existsAns.setPromptType(list.get(0)!=null?list.get(0).toString():"");
			existsAns.setWarnType(list.get(1)!=null?list.get(1).toString():"");
			msgRes.setObj(JSON.toJSON(existsAns));
		}
		return msgRes;
	}
	
	@Override
	public MsgRes tscRfCheckLabelNo(String strRecvData) throws Exception {
		IdataCheckLabelRequestMode resMod=JSON.parseObject(strRecvData, IdataCheckLabelRequestMode.class);
		//新增品质检查 update huangb 20160714
		MsgRes msg = new MsgRes();
		msg = IdataCheckLabelNo(resMod.getEnterpriseNo(),
				resMod.getWarehouseNo(),
				resMod.getSimportNo(),
				resMod.getLabelNo());
		if(resMod.getQuality() != null && !resMod.getQuality().equals("")){
			if(!msg.getIsSucc()){
				return msg;
			}else{
				//获取临时表中当前板上的品质信息
				String sql = " select icpt.quality "+
							 "   from idata_check_pal_tmp icpt "+
						     "  where icpt.warehouse_no = '" + resMod.getWarehouseNo() + "' "+
						     "    and icpt.enterprise_no = '" + resMod.getEnterpriseNo() + "' "+
						     "    and icpt.label_no = '" + resMod.getLabelNo() + "' "+
						     "    and icpt.s_import_no = '" + resMod.getSimportNo() + "' ";
				List<String> list=genDao.getListByNativeSql(sql);
				if(list.size() <= 0){
					return msg;
				}
				else{
					if(!list.get(0).equals(resMod.getQuality())){
						return new MsgRes(false,"一块板不能验收多种品质","");	
					}else{
						return msg;
					}
				}
			}
		}else{
			return msg;
		}
	}
	@Override
	public MsgRes tscRfIdataCheckID(String strRecvData) throws Exception {
		IdataCheckRequestModel reqMod=JSON.parseObject(strRecvData, IdataCheckRequestModel.class);
		MsgRes msgRes=new MsgRes();
		//校验板号是否可用
		msgRes=IdataCheckLabelNo(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(),
				reqMod.getSimportNo(), 
				reqMod.getLabelNo());
		if(!msgRes.getIsSucc())
		{
			return msgRes;
		}
		//保存
		tscSaveCheckDataID(reqMod.getEnterpriseNo(), reqMod.getWarehouseNo(), 
							reqMod.getOwnerNo(), 
							reqMod.getSimportNo(), 
							reqMod.getArticleNo(),
							reqMod.getBarcode(), 
							Double.parseDouble(reqMod.getPackingQty()),
							Double.parseDouble(reqMod.getCheckQty()),
							reqMod.getPrinterGroupNo(),
							reqMod.getDockNo(),
							reqMod.getWorkerNo(),
							reqMod.getCheckTools(),
							Integer.parseInt(reqMod.getIsAdd()),
							reqMod.getQuality(),
							DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"),
							DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"),
							reqMod.getLotNo(),
							reqMod.getRsvBatch1(), 
							reqMod.getRsvBatch2(),
							reqMod.getRsvBatch3(),
							reqMod.getRsvBatch4(),
							reqMod.getRsvBatch5(),
							reqMod.getRsvBatch6(),
							reqMod.getRsvBatch7(),
							reqMod.getRsvBatch8(),
							reqMod.getLabelNo(),
							reqMod.getSupLabelNo(),
							reqMod.getFixPalFlag(),
							reqMod.getBusinessType());
		return msgRes;
	}
	@Override
	public MsgRes tscRfIdataCloseLabelID(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataCloseLablRequestModelTTH reqMod=JSON.parseObject(strRecvData, IdataCloseLablRequestModelTTH.class);
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo
		inList.add(reqMod.getWarehouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSimportNo());//strsImportNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getBarcode());//strBarcode
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty
		inList.add(Double.parseDouble(reqMod.getCheckQty()));//nCheckQty
		inList.add(reqMod.getPrinterGroupNo());//strPrinterGroupNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getWorkerNo());//strWorkerNo
		inList.add(reqMod.getCheckTools());//strCheckTools		
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
		inList.add(reqMod.getLabelNo().trim().equals("")? "N":reqMod.getLabelNo().trim());//strLabelNo
		inList.add(reqMod.getSupLabelNo().trim().equals("")? "N":reqMod.getSupLabelNo().trim());//strSupLabelNo
		inList.add("N");
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA_ID.P_RF_IDCLOSEBOX");
				
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		
		msgRes.setIsSucc(true);
		msgRes.setObj(resultList.get(0).toString());		
	
		return new MsgRes(true, "封板成功！", "");
	}
	//直通存储验收事件(天天惠)
	@Override
	public MsgRes tscRfIdataCheckIDTTH(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataCheckRequestModel reqMod=JSON.parseObject(strRecvData, IdataCheckRequestModel.class);
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo
		inList.add(reqMod.getWarehouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSimportNo());//strsImportNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getBarcode());//strBarcode
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty
		inList.add(Double.parseDouble(reqMod.getCheckQty()));//nCheckQty
		inList.add(reqMod.getPrinterGroupNo());//strPrinterGroupNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getWorkerNo());//strWorkerNo
		inList.add(reqMod.getCheckTools());//strCheckTools
		inList.add(Integer.parseInt(reqMod.getIsAdd()));//nIsAdd		
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
		inList.add(reqMod.getLabelNo().trim().equals("")? "N":reqMod.getLabelNo().trim());//strLabelNo
		inList.add(reqMod.getSupLabelNo().trim().equals("")? "N":reqMod.getSupLabelNo().trim());//strSupLabelNo
		inList.add(reqMod.getFixPalFlag());//strFixPalFlag
		inList.add(reqMod.getBusinessType());//strBusinessType
				
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA_ID.P_SCAN_IDCHECK");
		System.out.println(resultList);	
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		
		msgRes.setIsSucc(true);
		msgRes.setObj(resultList.get(0).toString());
		return msgRes;
	}
	//RF直通验收获取商品信息（天天惠）
	public MsgRes IDGetArticleNoInfoByBarcodeAndPoNo(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataGetBarcodeRequestModel reqMod = JSON.parseObject(strRecvData, IdataGetBarcodeRequestModel.class);
		//根据扫描的商品条码查找商品信息
		msgRes =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getBarcode(),reqMod.getEnterpriseNo());			
		if(!msgRes.getIsSucc())
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码有误！");
			return msgRes;
		}
		if(msgRes.getObj().toString().equals("") || msgRes.getObj() == null)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("输入条码不存在！");
			return msgRes;
		}
		
		
		String strSql="select distinct t.barcode from idata_check_pal_tmp t " +
				" where t.enterprise_no='"+reqMod.getEnterpriseNo()+"'" +
				" and t.warehouse_no='"+reqMod.getWarehouseNo()+"'" +
				" and t.dock_no='"+reqMod.getDockNo()+"'" +
				" and t.printer_group_no='"+reqMod.getPrinterGroupNo()+"'" +
				" and t.article_no not in("+msgRes.getObj().toString() +")";
		List listTmp=genDao.getListByNativeSql(strSql);	
		if(listTmp.size()>0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("已在验收其他商品！["+listTmp.get(0).toString()+"]");
			return msgRes;
		}
		
		
		String sql=" select nvl(icpt.sub_label_no,'N') as deviceNo,iim.import_no, " +
				   " iid.packing_qty,iid.article_no,iim.owner_no,bda.article_name, bda.barcode,bda.UNIT_PACKING as unitPacking," +
				   //"nvl(bap.spec, '1*' || iid.packing_qty || bda.unit) spec,"+
				   //"nvl(bap.packing_unit, decode(iid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit," +
				   " f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingUnit, "+
				   " f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingUnitQmin, "+
				   " f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) Unit, "+
				   " f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packingSpec, "+
				   " f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.qmin_operate_packing) packingSpecQmin, "+
				   " f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,bda.unit_packing) spec, "+
                   " case when iid.packing_qty=1 then 0 " +
                   " else (iid.po_qty-iid.import_qty-sum(nvl(icpt.check_qty,0)))/iid.packing_qty end unCheckBoxs, " +
                   " case when iid.packing_qty=1 " +
                   " then (iid.po_qty-iid.import_qty-sum(nvl(icpt.check_qty,0)))/iid.packing_qty  " +
                   " else mod((iid.po_qty-iid.import_qty-sum( nvl(icpt.check_qty,0))),iid.packing_qty)end unCheckPcs," +
                   
                   " (select sum(nvl(iia.po_qty, 0) - nvl(iia.allot_qty, 0)) " +
                   "  from cset_cust_dpscell ccd, idata_import_allot iia " +
                   "  where iia.enterprise_no = ccd.enterprise_no " +
                   "  and iia.warehouse_no = ccd.warehouse_no" +
                   "  and iia.article_no= bda.article_no " +
                   "  and iia.cust_no = ccd.cust_no " +
                   "  and iia.import_no = iim.import_no " +
                   "  and ccd.device_no = icpt.sub_label_no" +
                   "  and ccd.enterprise_no = '"+reqMod.getEnterpriseNo()+"' " +
                   "  and ccd.warehouse_no = '"+reqMod.getWarehouseNo()+"')-sum(nvl(icpt.check_qty, 0)) unDeviceCheckPcs,"+
                                     
                   " case when iid.packing_qty=1 then 0 " +
                   " else sum((nvl(icpt.check_qty,0))/iid.packing_qty)  end checkBoxs, " +
                   " case when iid.packing_qty=1 " +
                   " then sum((nvl(icpt.check_qty,0)))/iid.packing_qty  " +
                   " else mod(sum(nvl(icpt.check_qty,0)),iid.packing_qty) end checkPsc, "+
			       " nvl(bap.qpalette,999*999) as qpalette,iis.s_import_no as sImportNo " +
			       " from idata_import_m iim " +
			       " join idata_import_d iid " +
			       "   on iim.enterprise_no = iid.enterprise_no " +
			       "  and iim.warehouse_no= iid.warehouse_no " +
			       "  and iim.import_no = iid.import_no " +
			       "  and iim.owner_no = iid.owner_no " +
			       " join bdef_defarticle bda " +
			       "   on iid.enterprise_no = bda.enterprise_no " +
			       "  and iid.article_no = bda.article_no " +
			       "  and bda.article_no in("+msgRes.getObj().toString() +") " +
			       " left join bdef_article_packing bap " +
			       "        on iid.enterprise_no = bap.enterprise_no " +
			       "       and iid.article_no =bap.article_no " +
			       "       and iid.packing_qty=bap.packing_qty " +
			       " join idata_import_sm iis " +
			       "   on iim.enterprise_no = iis.enterprise_no " +
			       "  and iim.warehouse_no = iis.warehouse_no " +
			       "  and iim.import_no = iis.import_no " +
			       "  and iim.owner_no = iis.owner_no " +
			       " left join idata_check_pal_tmp icpt " +
			       "        on iim.enterprise_no = icpt.enterprise_no " +
			       "       and iim.warehouse_no = icpt.warehouse_no " +
			       "       and iis.s_import_no = icpt.s_import_no " +
			       "       and bda.article_no=icpt.article_no  " +
			       "       and icpt.owner_no = icpt.owner_no " +
			       "	   and icpt.DOCK_NO='"+reqMod.getDockNo()+"'" +
			       "	   and icpt.PRINTER_GROUP_NO='"+reqMod.getPrinterGroupNo()+"'" +
			       " where iim.po_no='"+reqMod.getPoNo()+"' " +
			       "   and iim.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
			       "   and iim.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
			       "   and iim.po_type='ID'   " +
			       " group by iid.enterprise_no,iid.owner_no,bda.qmin_operate_packing,bda.unit_packing, " +
			       " iid.packing_qty," +
			       " icpt.sub_label_no, "+
                   " iim.import_no," +
			       " iid.article_no," +
			       " iim.owner_no," +
			       " bda.article_name, " +
			       " bda.barcode," +
				   //" nvl(bap.spec, '1*' || iid.packing_qty || '*'|| bda.unit),"+
				   //" nvl(bap.packing_unit, decode(iid.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)),"+
			       " nvl(bap.qpalette, 999*999)," +
			       " iis.s_import_no," +
			       " iid.po_qty," +
			       " iid.import_qty," +
			       " bda.article_no " ;
		List<IdataGetArticleInfoIDAnswerModel> list = genDao.getListByNativeSql(sql,IdataGetArticleInfoIDAnswerModel.class);	
		if(list.size()==1){
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONArray.fromObject(list.get(0)));
			
		}else{
			msgRes.setIsSucc(false);
			msgRes.setObj("读取数据失败！");
		}
		return msgRes;
	}
	
	//Rf直通验收确定商品是否可以验收保存
	public MsgRes IDCheckBarcodeForSave(String strRecvData) throws Exception{
		MsgRes msgRes=new MsgRes();
		IdataDockIDModel reqMod=JSON.parseObject(strRecvData, IdataDockIDModel.class);
		
		//根据扫描的商品条码查找商品信息
		msgRes =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getBarcode(),reqMod.getEnterpriseNo());			
		if(!msgRes.getIsSucc())
		{
			msgRes.setIsSucc(true);
			msgRes.setMsg("-1");
			return msgRes;
		}
		if(msgRes.getObj().toString().equals("") || msgRes.getObj() == null)
		{
			msgRes.setIsSucc(true);
			msgRes.setMsg("-1");
			return msgRes;
		}
		String strArticleNos = msgRes.getObj().toString();
			String strSql=" select iid.article_no " +
					      "   from idata_import_m iim ,idata_import_d iid,bdef_defarticle bda  " +
					      "  where iim.po_no='"+reqMod.getPoNo()+"' " +
					      "    and iim.enterprise_no='"+reqMod.getEnterpriseNo()+"' " +
					      "    and iim.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
					      "    and iim.enterprise_no=iid.enterprise_no " +
					      "    and iim.warehouse_no=iid.warehouse_no " +
					      "    and iim.import_no=iid.import_no " +
					      "    and iid.enterprise_no=bda.enterprise_no " +
					      "    and iid.article_no= bda.article_no " +
					      //"    and bda.barcode='"+reqMod.getBarcode()+"' " +
			      		  "    and iid.article_no in ("+msgRes.getObj().toString()+")";
		   List<IdataDockIDModel> list =genDao.getListByNativeSql(strSql,IdataDockIDModel.class);

		   if(list.size()==0){
			   msgRes.setIsSucc(true);
			   msgRes.setObj("-1");
			   return msgRes;
		   }else{
			   strSql="select distinct a.sub_label_no,a.barcode,c.po_no,a.article_no " +
						" from idata_check_pal_tmp a ,idata_import_sm b ,idata_import_m c " +
						"where a.enterprise_no ='"+reqMod.getEnterpriseNo()+"' " +
						"  and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
						"  and a.dock_no='"+reqMod.getDock()+"' " +
						"  and a.enterprise_no=b.enterprise_no " +
						"  and a.warehouse_no=b.warehouse_no " +
						"  and a.s_import_no = b.s_import_no " +
						"  and b.enterprise_no = c.enterprise_no " +
						"  and b.warehouse_no = c.warehouse_no " +
						"  and a.article_no not in ("+ strArticleNos +")" +
						"  and b.import_no = c.import_no ";
			   List<IdataDockIDModel> exitsTmp = genDao.getListByNativeSql(strSql,IdataDockIDModel.class);	
			   if(exitsTmp.size()==0){
					msgRes.setIsSucc(true);	
					msgRes.setObj("1");
				}else{
					msgRes.setIsSucc(true);
					msgRes.setObj("0");
				}
		   }	
		   return msgRes;		
	}

	
	//未封箱验收数据取消
	@Override
	public MsgRes delcheckPalTmp(String strEnterpriseNo, String strWareHouseNo,
			String strOwnerNo,String strCheckNo, String strsImportNo) throws Exception {
		String delsql="delete idata_check_pal_tmp a where a.enterprise_no='"+strEnterpriseNo+"' " +
				"and a.wareHouse_no='"+strWareHouseNo+"' " +
			    "and a.s_import_no='"+strsImportNo+"' ";
		genDao.updateBySql(delsql);
		String sql="select * from idata_check_pal b " +
				"where b.enterprise_no='"+strEnterpriseNo+"' " +
				  "and b.wareHouse_no='"+strWareHouseNo+"' " +
				  "and b.check_no='"+strCheckNo+"' ";
		List<Idata_CheckPalModel> list = genDao.getListByNativeSql(sql,Idata_CheckPalModel.class);
		if(list.size()==0){
			String delsql2="delete idata_check_m b " +
				" where b.enterprise_no='"+strEnterpriseNo+"' " +
				  "and b.wareHouse_no='"+strWareHouseNo+"' " +
				  "and b.check_no='"+strCheckNo+"' ";
			genDao.updateBySql(delsql2);
		}
		return new MsgRes(true, "取消成功！", "");
	}
	
	//打印验收单
	@Override
	public MsgRes tscPrintCheckNo(String strEnterpriseNo,
			String strWareHouseNo,String workSpaceNo,String workerNo, String strSCheckNo) throws Exception {
		
		String sql=" select count(*) from PNTSET_PRINTER_WORKSTATION where WAREHOUSE_NO='"+strWareHouseNo+ 
					"' and WORKSTATION_NO='"+workSpaceNo+"' and enterprise_no='"+strEnterpriseNo+"' ";   
		Integer intCount = genDao.getCountByNativeSql(sql);
		if(intCount==0){
			return new MsgRes(false,"工作站与打印机组对应关系没有维护","");
		}
		
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		List<Comparable> inList=new ArrayList<Comparable>();
		
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add("PT");
		
		resultList=genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_getsheetno");
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(1).toString());
		}
		
		String taskNo=resultList.get(0).toString();

		sql=" select PRINTER_GROUP_NO from PNTSET_PRINTER_WORKSTATION where WAREHOUSE_NO='"+strWareHouseNo+ 
					"' and WORKSTATION_NO='"+workSpaceNo+"' and enterprise_no='"+strEnterpriseNo+"' ";   
		
		List<String> list = genDao.getListByNativeSql(sql);
		
		String printerGroupNo=list.get(0);
		
		sql="INSERT INTO JOB_PRINTTASK_M(enterprise_no,Warehouse_No,TASK_NO,SOURCE_NO,BACK_FLAG,TASK_TYPE,REPRINT_FLAG, REPORT_ID,PRINTER_GROUP_NO,OPERATE_DATE,RGST_NAME,RGST_DATE)"+
	        " values ('"+
	            strEnterpriseNo+"','"+
	            strWareHouseNo+"','"+
				taskNo+"','"+
				strSCheckNo+"',"+
				"'0','L','0','" +
				ReportDefine.L_I_CHECK+"','"+
				printerGroupNo+"',sysdate,'"+
				workerNo+"',sysdate)";
		
		genDao.exceuteSql(sql);
			
		return new MsgRes(true,"打印指令发送成功","");	
	}
	
	//RF
	@Override
	public MsgRes IdataISCheckBarcode(String strRecvData) throws Exception {
		BarcodeRequestModel reqMod=JSON.parseObject(strRecvData, BarcodeRequestModel.class);
		
		String strEnterpriseNo=reqMod.getEnterpriseNo();
		String strOwnerNo=reqMod.getOwnerNo();
		String barcode=reqMod.getBarcode();
		String strWarehouseNo=reqMod.getWarehouseNo();
		String strSImportNo=reqMod.getSImportNo();
		//根据扫描的商品条码查找商品信息
		MsgRes msg =getArticleForBarcodeImpl.checkBarcode(strWarehouseNo,
				barcode,
				strOwnerNo,
				strEnterpriseNo);
		if(!msg.getIsSucc())
		{
			return msg;
		}
		//取系统参数 预留
		String strIcAlarmRate="2";//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
		String strIcFreezeRate="2";//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
		String strSql="";
				
		//获取单据类型 huangb 20160715
		String strImportType = "";
		strSql = " select a.import_type from idata_import_mm a" +
				"   where a.warehouse_no = '"+strWarehouseNo+"' " +
				"     and a.enterprise_no='"+strEnterpriseNo+"' " +
				"     and a.owner_no = '"+strOwnerNo+"' " +
				"     and a.s_import_no = '"+strSImportNo+"' ";
		List<String> listImportType=genDao.getListByNativeSql(strSql);
		if(listImportType.size() <= 0){
			msg.setIsSucc(false);
			msg.setMsg("汇总单[" + strSImportNo + "]头档不存在数据");
			return msg;
		}
		strImportType = listImportType.get(0);
		
		String flag="";//扫描标志
		List<Wms_IDataTypeModel> listFlag = getSystemParameterImpl.getIdataTypeStrategy
				(strEnterpriseNo, strWarehouseNo, strOwnerNo, strImportType, "scan_flag");
		if(listFlag.size()==0){
			flag="0";
		}else{
			flag=listFlag.get(0).getColumnValue();
		}
		
		//获取是否允许超品验收系统参数 huangb 20160712
		String strCheckOverFlag = "";
		List<Wms_IDataTypeModel> listCheckOverFlag = getSystemParameterImpl.getIdataTypeStrategy
				(strEnterpriseNo, strWarehouseNo, strOwnerNo, strImportType, "over_qty_flag");
		if(listCheckOverFlag.size()==0){
			strCheckOverFlag="0";
		}else{
			strCheckOverFlag=listCheckOverFlag.get(0).getColumnValue();
		}
		
		//从进货汇总表查询商品信息
		//log hkl 取堆叠先从仓库包装中取，取不到从包装表取，取不到给999*999
		//log 如果计划量为0 即为超品 huangb 20160712
		strSql="select decode(po_qty_flag,0,'1','0') checkOverFlag,'0' as checkOverFlagUI, " +
				"  a.warehouse_no,a.owner_no,a.s_import_no,a.row_id,a.article_no," +
		        "  a.packing_qty,a.po_qty,a.import_qty,a.qc_qty,a.check_name," +
		        "  a.status,a.check_date,a.qc_status,a.out_stock_flag,a.outpace_article_flag," +
		        "  a.item_type,a.plan_across_qty,a.check_across_qty," +
		        "  a.qc_flag,a.expiry_days,a.article_name,a.check_excess,a.TEMPERATURE_FLAG," +
		        "  a.icAlarmrate,a.icFreezerate,a.alarmrate,a.freezerate,a.barcode," +
		        "  a.qmin_operate_packing," +
		        //"  a.spec,a.packingUnit," +
		        "  a.packingUnit, a.packingUnitQmin, a.Unit, a.packingSpec, a.packingSpecQmin, a.spec, " +
		        "  nvl(a.qpalette,999*999) qpalette,a.UnitPacking," +
		        "  nvl(a.pal_base_qbox,999) as palBaseQbox,nvl(a.pal_height_qbox,999) as palHeightQbox,a.boxPickType,a.disPickType,a.scan_flag,a.lot_type " + 
		    " from (select a.warehouse_no," +
					"a.owner_no," +
					"a.s_import_no," +
					"a.row_id," +
					"a.article_no," +
					"a.packing_qty," +
					//如果计划量为0 即为超品 huangb 20160712
					//"a.po_qty - a.import_qty - nvl(tmp.check_qty,0) po_qty," +
					" a.po_qty as po_qty_flag, " +
					//" decode(a.po_qty,0,0,a.po_qty - a.import_qty - nvl(tmp.check_qty, 0)) po_qty," +
					" decode(a.po_qty," +
					"        0, " +
					"        0, " +
					"        (case " +
					"         when a.po_qty - a.import_qty - nvl(tmp.check_qty, 0) < 0 then " +
					"         0 " +
					"         else " +
					"         a.po_qty - a.import_qty - nvl(tmp.check_qty, 0) " +
					"         end)) po_qty, " +
					"a.import_qty," +
					"a.qc_qty," +
					"a.check_name," +
					"a.status," +
					"a.check_date," +
					"a.qc_status," +
					"a.out_stock_flag," +
					"a.outpace_article_flag," +
					"a.item_type," +
					"a.plan_across_qty," +
					"a.check_across_qty," +
					"a.qc_flag," +
					"b.expiry_days," +
					"b.article_name," +
					"b.check_excess,b.TEMPERATURE_FLAG," +
					"case when b.expiry_days>0 then b.expiry_days*b.alarmrate*0.01 else 0 end icAlarmrate,"+
					"case when b.expiry_days>0 then b.expiry_days*b.freezerate*0.01 else 0 end icFreezerate,"+
					"b.alarmrate," +
					"b.freezerate," +
					"b.barcode," +
					"b.qmin_operate_packing,b.UNIT_PACKING UnitPacking," +
				    //"nvl(d.spec, '1*' || a.packing_qty || b.unit) spec,"+
				   //" nvl(d.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packingUnit," +
				    "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
				    "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
				    "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
				    "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
				    "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
				    "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
					"nvl(bwp.Qpalette,d.Qpalette) Qpalette, " +
					"nvl(bwp.pal_base_qbox,d.pal_base_qbox) as pal_base_qbox," +
			        "nvl(bwp.pal_height_qbox,d.pal_height_qbox) as pal_height_qbox," +
					"g.pick_type as boxPickType," +
					"h.pick_type as disPickType," +
					"case when "+flag+"='1' and b.scan_flag='1' then 1 else 0 end scan_flag,"+
					"b.lot_type  " +
				" from " +
					"idata_import_sd a," +
					"bdef_defarticle b," +
					"idata_import_mm c," +
					"bdef_article_packing d," +
					"bdef_warehouse_packing bwp," +
					"(select " +
						"article_no, ware_no, area_no, pick_type " +
					"from " +
					   	"cset_cell_article " +
					" where " +
						"warehouse_no = '"+strWarehouseNo+"' " +
						"and enterprise_no='"+strEnterpriseNo+"' " +
						"and pick_type = 'B'" +
					") g," +
					"(select " +
							"article_no, ware_no, area_no, pick_type " +
					  "from " +
					   		"cset_cell_article " +
					   "where " +
					   		"warehouse_no = '"+strWarehouseNo+"' " +
					   		"and enterprise_no='"+strEnterpriseNo+"' " +
					   		"and pick_type = 'C'" +
			   		") h," +
			   	//log 151027 wyf 添加关联idata_check_pal_tmp表验收数量，去未验量	
			   		" (select t.enterprise_no,t.warehouse_no,t.owner_no,t.s_import_no,t.article_no,sum(t.check_qty) check_qty,t.packing_qty " +
			   		" from idata_check_pal_tmp t group by t.enterprise_no,t.warehouse_no,t.owner_no,t.s_import_no,t.article_no,t.packing_qty) tmp " +
				//end log 					
				"where " +
					"a.enterprise_no=b.enterprise_no " +
					"and a.article_no = b.article_no " +
					"and a.enterprise_no=c.enterprise_no " +
					"and a.s_import_no = c.s_import_no " +
					"and a.warehouse_no = c.warehouse_no" +
					//log 151027 wyf 添加关联idata_check_pal_tmp表验收数量，去未验量
					" and a.enterprise_no = tmp.enterprise_no(+) " +
					" and a.warehouse_no = tmp.warehouse_no(+) " +
					" and a.owner_no = tmp.owner_no(+) " +
					" and a.s_import_no = tmp.s_import_no(+) " +
					" and a.article_no = tmp.article_no(+) " +
					" and a.packing_qty = tmp.packing_qty(+) " +					
					//end log 
					"and a.enterprise_no = d.enterprise_no(+) " +
					"and a.article_no = d.article_no(+) " +
					"and a.packing_qty = d.packing_qty(+) " +
			        "and a.enterprise_no = bwp.enterprise_no(+) " +
			        "and a.article_no=bwp.article_no(+) " +
			        "and a.packing_qty=bwp.packing_qty(+) " +
					"and a.article_no = g.article_no(+) " +
					"and a.article_no = h.article_no(+) " +
					//如果单据类型为超品 则 不判断
					"and ((case " +
					"      when '"+strCheckOverFlag+"' = '1' or '"+strCheckOverFlag+"' = '0' then " +
					"       a.po_qty * (1 + b.check_excess * 0.01) - a.import_qty - nvl(tmp.check_qty, 0) " +
					"       else " +
					"       1 " +
					"       end) > 0 or a.po_qty = 0) " +					
//					"and ((a.po_qty * (1 + b.check_excess * 0.01) - a.import_qty - nvl(tmp.check_qty,0) > 0) " +
//					"      or a.po_qty = 0) " +
					"and a.warehouse_no = '"+strWarehouseNo+"' " +
					"and a.enterprise_no='"+strEnterpriseNo+"' " +
					"and a.owner_no = '"+strOwnerNo+"' " +
					"and a.s_import_no = '"+strSImportNo+"' " +
					"and a.article_no in("+msg.getObj().toString()+")  " +
					"order by a.article_no) a";
		List<IdataStockCheckArticleInfoModel> list1 = genDao.getListByNativeSql(strSql,IdataStockCheckArticleInfoModel.class);
		System.out.println(JSONArray.fromObject(list1));
		MsgRes msgR = new MsgRes();
		if(list1.size()>0){
			msgR.setIsSucc(true);
			msgR.setObj(JSONArray.fromObject(list1));
			return msgR;
		}else{
			//如果允许超品 则判断当前商品是否为超量商品 huangb 20160712
			if(strCheckOverFlag.equals("2")){
				
				strSql = " select a.s_import_no from idata_import_sd a" +
						"   where a.warehouse_no = '"+strWarehouseNo+"' " +
						"     and a.enterprise_no='"+strEnterpriseNo+"' " +
						"     and a.owner_no = '"+strOwnerNo+"' " +
						"     and a.s_import_no = '"+strSImportNo+"' " +
						"     and a.article_no in("+msg.getObj().toString()+")" ;
				List<String> list=genDao.getListByNativeSql(strSql);
				//如果查询不到数据 则 为超品
				if(list.size() == 0){
					strSql = " select a.article_no, a.article_name, nvl(b.packing_qty, 1) packing_qty" +
							 "  , '1' as checkOverFlag,'1' as checkOverFlagUI " +
							 "   from bdef_defarticle a, bdef_article_packing b " +
							 "  where b.enterprise_no(+) = a.enterprise_no " +
							 "    and b.article_no(+) = a.article_no " +
							 "    and a.enterprise_no='"+strEnterpriseNo+"' " + 
							 "    and a.article_no in("+msg.getObj().toString()+")" ;
					List<IdataStockCheckArticleInfoModel> listPacingQty = genDao.getListByNativeSql(strSql,IdataStockCheckArticleInfoModel.class);
					if(listPacingQty.size()>0){
						msgR.setIsSucc(true);
						msgR.setObj(JSONArray.fromObject(listPacingQty));
						return msgR;
					}else{
						msgR.setIsSucc(false);
						msgR.setMsg("该商品不存在");
						return msgR;
					}
				}
			}
			msgR.setIsSucc(false);
			msgR.setMsg("找不到该条码");
			return msgR;
		}
	}
	
	//发单打印
	public List tscPrint(String strEnterpriseNo, String strWareHouseNo,
						 String strSourceNo, String strWorkerNo,
						 String strReportId, String strDockNo) throws Exception{
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add(strSourceNo);
		inList.add("0");
		inList.add(strReportId);
		inList.add(strDockNo);
		inList.add("0");
		inList.add(strWorkerNo);
		
		resultList=genDao.execProc(inList, outList, "PKOBJ_PRINTTASK.p_insert_taskmaster");
		if(resultList.get(1).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(1).toString());
		}
		return resultList;
	}
	//RF直通验收并封板
	@Override
	public MsgRes tscIDCheckSaveAndClosePal(String strRecvData)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataCheckRequestModel reqMod=JSON.parseObject(strRecvData, IdataCheckRequestModel.class);
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		//outList.add("S");
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo
		inList.add(reqMod.getWarehouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSimportNo());//strsImportNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(reqMod.getBarcode());//strBarcode
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty
		inList.add(Double.parseDouble(reqMod.getCheckQty()));//nCheckQty
		inList.add(reqMod.getPrinterGroupNo());//strPrinterGroupNo
		inList.add(reqMod.getDockNo());//strDockNo
		inList.add(reqMod.getWorkerNo());//strWorkerNo
		inList.add(reqMod.getCheckTools());//strCheckTools
		inList.add(Integer.parseInt(reqMod.getIsAdd()));//nIsAdd		
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
		inList.add(reqMod.getLabelNo().trim().equals("")? "N":reqMod.getLabelNo().trim());//strLabelNo
		inList.add(reqMod.getSupLabelNo().trim().equals("")? "N":reqMod.getSupLabelNo().trim());//strSupLabelNo
		inList.add("N");//strPrintFlag
		inList.add(reqMod.getFixPalFlag());//strFixPalFlag
		inList.add(reqMod.getBusinessType());//strBusinessType
		inList.add("2");//strPrintFlag
				
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_IDATA_ID.P_SAVE_IDCHECK");
		System.out.println(resultList);	
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		
		msgRes.setIsSucc(true);
		msgRes.setObj("验收成功");
		return msgRes;
	}
	
	/**
	 * 获取商品各包装信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscGetArticlePacking(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataGetArticlePackingInfo reqMod=JSON.parseObject(strRecvData, IdataGetArticlePackingInfo.class);
		
		//获取当前商品对应的单据包装
		String strSql1 = "SELECT d.packing_qty FROM idata_import_mm mm ,idata_import_sm sm,idata_import_sd d " +
				" WHERE mm.warehouse_no = sm.warehouse_no AND mm.enterprise_no = sm.enterprise_no AND mm.s_import_no = sm.s_import_no" +
				" AND sm.warehouse_no = d.warehouse_no AND sm.enterprise_no = d.enterprise_no AND sm.s_import_no = d.s_import_no" +
				" AND mm.serial_no = '"+reqMod.getSerialNO()+"' AND mm.warehouse_no ='"+reqMod.getWarehouseNO()+"'" +
				" AND sm.enterprise_no='"+reqMod.getEnterpriseNO()+"' AND d.article_no = '"+reqMod.getArticleNO()+"' AND d.row_id ='"+reqMod.getRowID()+"'";
		List<Bdef_ArticleInfoModel> list1 = genDao.getListByNativeSql(strSql1,Bdef_ArticleInfoModel.class);
		if(list1.size() == 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("获取商品订单包装数量失败！"); 
			return msgRes;
		}
		System.out.println(list1.get(0).getPackingQty());
		String strSql11 = "SELECT p.packing_unit FROM bdef_article_packing p " +
				" WHERE p.article_no = '"+reqMod.getArticleNO()+"' AND p.enterprise_no = '"+reqMod.getEnterpriseNO()+"'  " +
						"AND p.packing_qty = '"+list1.get(0).getPackingQty()+"'";
		List<String> list11 = genDao.getListByNativeSql(strSql11);
		if(list11.size() == 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("获取商品订单包装单位失败！"); 
			return msgRes;
		}
		
		String strSql21 = "SELECT p.QMIN_OPERATE_PACKING_UNIT FROM bdef_defarticle p " +
				" WHERE p.article_no = '"+reqMod.getArticleNO()+"'";
		List<String> list21 = genDao.getListByNativeSql(strSql21);
		if(list21.size() == 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("获取商品最小操作包装单位失败！"); 
			return msgRes;
		}
		
		String strSql3 = "SELECT MIN(p.packing_qty) packing_qty  FROM bdef_article_packing p" +
				" WHERE p.article_no = '"+reqMod.getArticleNO()+"' AND p.enterprise_no = '"+reqMod.getEnterpriseNO()+"' ";
		List<Bdef_ArticleInfoModel> list3 = genDao.getListByNativeSql(strSql3,Bdef_ArticleInfoModel.class);
		if(list3.size() == 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("获取商品订单最小包装数量失败！"); 
			return msgRes;
		}
		
		String strSql31 = "SELECT p.packing_unit FROM bdef_article_packing p " +
				" WHERE p.article_no = '"+reqMod.getArticleNO()+"' AND p.enterprise_no = '"+reqMod.getEnterpriseNO()+"'  AND p.packing_qty = '"+list3.get(0).getPackingQty()+"'";
		List<String> list31 = genDao.getListByNativeSql(strSql31);
		if(list31.size() == 0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("获取商品最小包装单位失败！"); 
			return msgRes;
		}
		
		
		//拼接返回值
		IdataGetArticlePackingInfo info= new IdataGetArticlePackingInfo();
		info.setWarehouseNO(reqMod.getWarehouseNO());
		info.setEnterpriseNO(reqMod.getEnterpriseNO());
		info.setArticleNO(reqMod.getArticleNO());
		info.setRowID(reqMod.getRowID());
		info.setSerialNO(reqMod.getSerialNO());
		info.setPackingOrder(list1.get(0).getPackingQty());
		info.setUnitOrder(list11.get(0).toString());
		info.setPackingMINOPERATE(reqMod.getPackingMINOPERATE());
		info.setUnitMINOPERATE(list21.get(0).toString());
		info.setPackingMin(list3.get(0).getPackingQty());
		info.setUnitMin(list31.get(0).toString());

		msgRes.setIsSucc(true);
		msgRes.setMsg("验收成功");
		msgRes.setObj(JSON.toJSON(info));
		
		return msgRes;
	}
	
	//超品验收--新增进货单以及汇总单明细 huangbin 20160712
	@Override
	public MsgRes tscImportDetailByOver(String strRecvData) throws Exception {
		MsgRes msgRes=new MsgRes();
		IdataCheckRequestModel reqMod=JSON.parseObject(strRecvData, IdataCheckRequestModel.class);
		
		List<Comparable> inList=new ArrayList<Comparable>();
		List<String> outList=new ArrayList<String>();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(reqMod.getEnterpriseNo());//strEnterpriseNo
		inList.add(reqMod.getWarehouseNo());//strWareHouseNo
		inList.add(reqMod.getOwnerNo());//strOwnerNo
		inList.add(reqMod.getSimportNo());//strsImportNo
		inList.add(reqMod.getArticleNo());//strArticleNo
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty
				
		resultList = genDao.execProc(inList, outList, "pkobj_idata.p_Inset_ImportDetailByOver");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		
		msgRes.setIsSucc(true);
		msgRes.setObj("新增成功");
		return msgRes;
	}
	
	/** 获取品质码表数据
	 * huangb 20160712
	 */
	@Override
	public MsgRes getDeffieldvalInfo(String strRecvData) throws Exception {
		
		MsgRes msgRes=new MsgRes();
		WmsDeffieldvalModel reqMod=JSON.parseObject(strRecvData, WmsDeffieldvalModel.class);
		
		String strSql=" select t.table_name,t.colname,t.value,t.text,t.sort_order,t.memo,t.status " +
				      "   from wms_deffieldval t " +
				      "  where upper(t.table_name) = upper('" + reqMod.getTableName() + "') " +
				      "    and upper(t.colname) = upper('" + reqMod.getColname() + "') ";
		List<WmsDeffieldvalModel> listVal = genDao.getListByNativeSql(strSql,WmsDeffieldvalModel.class);
		if(listVal.size()==0)
		{
			msgRes.setIsSucc(false);
			msgRes.setMsg("table_name[" + reqMod.getTableName() + "]colname[" + reqMod.getColname() + "]在码表中不存在");
		}
		else{
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONArray.fromObject(listVal));
		}		
		return msgRes;
	}
	
	/**获取验收结果 用来导出
	 * huangb 20160716
	 */
	@Override
	public List<Idata_Check_ResulModel> tscGetCheckResult(
			String strEnterpriseNo,String strOwnerNo, String strWarehouseNo, String strPoNo)
			throws Exception {
		
	    	String strSql="";
	    	strSql = " select iim.warehouse_no, "+
	    			 "        '[' || bdo.owner_no || ']' || bdo.owner_name as owner, "+
	    			 "        iim.po_no, "+
	    			 "        bda.article_identifier, "+
	    			 "        bda.barcode, "+
	    			 "        bda.article_no, "+
	    			 "        bda.article_name, "+
	    			 "        iid.packing_qty, " +
	    			 "        f_get_packingunit(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packing_unit, " +
	    			 "        f_get_spec(iid.enterprise_no,iid.owner_no,iid.article_no,iid.packing_qty) packing_spec, " +
	    			 "        iid.po_qty, "+
	    			 "        iid.import_qty "+
	    			 "   from idata_import_m iim, idata_import_d iid,bdef_defowner bdo, bdef_defarticle bda "+
	    			 "  where iid.enterprise_no = iim.enterprise_no "+
	    			 "    and iid.warehouse_no = iim.warehouse_no "+
	    			 "    and iid.owner_no = iim.owner_no "+
	    			 "    and iid.import_no = iim.import_no "+
	    			 "    and bdo.enterprise_no = iim.enterprise_no "+
	    			 "    and bdo.owner_no = iim.owner_no "+
	    			 "    and bda.enterprise_no = iid.enterprise_no "+
	    			 "    and bda.owner_no = iid.owner_no "+
	    			 "    and bda.article_no = iid.article_no " +
	    			 "    and iim.enterprise_no = '"+ strEnterpriseNo +"' "+
	    			 "    and iim.owner_no = '"+ strOwnerNo +"' "+
	    			 "    and iim.warehouse_no = '"+ strWarehouseNo +"' "+
	    			 "    and iim.po_no = '"+ strPoNo +"' ";
	   	
	   	    strSql += "    order by iim.po_no,bda.article_no ";
	   	    List<Idata_Check_ResulModel> list=genDao.getListByNativeSql(strSql);
	   	    //如果导出成功 则 改状态为以导出
	   	    if(list.size() > 0)
	   	    {
	   	    	strSql = " update idata_import_m iim set iim.send_flag = '13' " +
	   	    			 "  where iim.enterprise_no = '"+ strEnterpriseNo +"' "+
		    			 "    and iim.owner_no = '"+ strOwnerNo +"' "+
		    			 "    and iim.warehouse_no = '"+ strWarehouseNo +"' "+
		    			 "    and iim.po_no = '"+ strPoNo +"' ";
	   	    	genDao.exceuteSql(strSql);
	   	    }
			return list;
			
	}
	
	//取品质（公用）
	@Override
	public List<ComboxBo> getQualityCombo(String enterpriseNo,
			String warehouseNo, String strOwnerNo, String str) throws Exception {
		List<Wms_IDataTypeModel> list = getSystemParameterImpl.getIdataTypeStrategy(enterpriseNo, warehouseNo, strOwnerNo, str, "QUALITY_FLAG");
		String a =  list.get(0).getColumnValue();
		String sql = "select t.value,t.text, " +
				  " '['|| ltrim(t.value)||']'||t.text dropValue  " +
				"from wms_deffieldval t where t.table_name='N' " +
				"AND T.COLNAME='QUALITY' and t.status='1' ";
		if(a.equals("0")){
			sql = sql + " and t.value='0' ";
		}
		List list1 = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list1;
	}
}
