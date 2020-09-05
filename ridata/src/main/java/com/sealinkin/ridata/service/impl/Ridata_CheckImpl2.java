package com.sealinkin.ridata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.service.IRidata_CheckService2;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Ridata_CheckImpl2 implements IRidata_CheckService2 {

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/**
	 * 返配表单验收列
	 */
	@Override
	public ExtListDataBo<Ridata_CheckDModel> getRidata_Check_DList(
			String enterpriseNo,String warehouseNo, 
			String whereSql, PageBo pageBo) {
		String[] str = whereSql.split(",");
		String sql = "";
		if (str[0].trim().equals("0")) {//未验收明细（grid2）
			sql = "select b.article_no,c.article_name,c.barcode,b.untread_qty checkQty,"
					+"trunc(b.untread_qty/b.packing_qty) as planBox,"
					+"trunc(mod(b.untread_qty,b.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin,"
					+"(b.untread_qty - trunc(b.untread_qty/b.packing_qty) * b.packing_qty - trunc(mod(b.untread_qty,b.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis,"
					+"c.unit_packing,c.qmin_operate_packing," + 
					//+"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"
					 "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
	                 "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
	                 "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
	                 "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
	                 "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
	                 "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"
					//+ "(b.untread_qty/b.packing_qty) as pobox,"
					//+ "(b.untread_qty/b.packing_qty) as checkbox,"
					+ "b.untread_qty,c.expiry_days,c.lot_Type,"
					+ "case when c.lot_type=1 or c.lot_type=4 " +
					"		then to_date('1900-01-01','yyyy-mm-dd') " +
					"	else null " +
					"  end as produceDate," +
					"  case when c.lot_type=1 or c.lot_type=4 " +
					"		then to_date('1900-01-01','yyyy-mm-dd') " +
					"    else null " +
					"  end as expireDate," +
					"  case when c.lot_type=2 or c.lot_type=4 " +
					"    then 'N' " +
					"    else null " +
					"  end as lot_no,b.quality,mm.quality as quality_flag,mm.class_type,A.UNTREAD_TYPE,"
					+ "b.packing_qty," 
					//"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) unit,"+
                    //"nvl(e.spec, '1*' || b.packing_qty || c.unit) spec,"
					+ "a.warehouse_no,a.owner_no,"
					+ "b.supplier_no,a.untread_no,a.s_untread_no "
					+ "from ridata_untread_sm a,ridata_untread_mm mm,ridata_untread_d b,Bdef_Defarticle c,"
					+ "bdef_article_packing e "
					+ "where a.untread_no=b.untread_no " +
					"  and a.s_untread_no=mm.s_untread_no and a.enterprise_no=mm.enterprise_no and a.warehouse_no=mm.warehouse_no " +
					" and b.article_no=c.article_no "
					+ "and e.article_no(+)=b.article_no and e.packing_qty(+)=b.packing_qty and "
					+ "b.untread_qty-b.check_qty>0 and " + "a.s_untread_no='"
					+ str[1].trim() + "' " + "and a.warehouse_no='"
					+ warehouseNo + "' and a.enterprise_no=b.enterprise_no " +
					"and a.enterprise_no=c.enterprise_no " +
					"and b.enterprise_no=e.enterprise_no(+) and a.enterprise_no='"+enterpriseNo+"' " +
				    "  order by b.article_no ,b.packing_qty ";
		} else if (str[0].trim().equals("1")) {//已验收明细（grid1）
			sql = "select b.article_no,c.article_name,c.barcode,"
					+"trunc(b.check_qty/b.packing_qty) as planBox,"
					+"trunc(mod(b.check_qty,b.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin,"
					+"(b.check_qty - trunc(b.check_qty/b.packing_qty) * b.packing_qty - trunc(mod(b.check_qty,b.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis,"
					+"c.unit_packing,c.qmin_operate_packing," + 
					//+"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) packing_unit,"
					"f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
	                 "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
	                 "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
	                 "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
	                 "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
	                 "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"
					//+ "(b.check_qty/b.packing_qty) as pobox,"
					+ "(b.check_qty/b.packing_qty) as checkbox,"
					+ "b.check_qty,b.produce_date as produceDate,"
					+ "b.expire_date as expireDate,"
					+ "b.packing_qty "
					+ "from ridata_check_m a,ridata_check_d b,"
					+ "bdef_defarticle c "
					+ "where a.check_no=b.check_no  "
					+ "and b.article_no=c.article_no " 
					+ "and b.check_no='"+ str[1].trim() + "' " 
					+ "and a.warehouse_no='"+ warehouseNo + "' " 
				    + "and a.enterprise_no=b.enterprise_no " 
					+ "and a.enterprise_no=c.enterprise_no " 
					+ "and a.enterprise_no='"+enterpriseNo+"'";
		}
		List<Ridata_CheckDModel> list = genDao.getListByNativeSql(sql,
				Ridata_CheckDModel.class, pageBo.getStart(), 1000);
		ExtListDataBo<Ridata_CheckDModel> extListBo = new ExtListDataBo<Ridata_CheckDModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 返配表单验收列
	 */
	@Override
	public ExtListDataBo<Ridata_CheckMModel> getRidata_Check_MList(
			String enterpriseNo,
			String warehouseNo, String workerOwner, String queryStr,
			PageBo pageBo) {
		String sql = "select a.check_no,b.cust_no,c.cust_name,a.untread_no,a.s_check_no,"
				+ "f_get_fieldtext('N','STATUS',a.status) statusText,"
				+ "a.dock_no,d.s_untread_no,a.check_worker,e.worker_name,"
				+ "a.rgst_name,a.rgst_date,a.updt_name,a.updt_date "
				+ "from ridata_check_m a,ridata_untread_m b,bdef_defcust c,"
				+ "ridata_untread_sm d,bdef_defworker e "
				+ "where a.untread_no=b.untread_no and b.cust_no=c.cust_no and "
				+ "d.untread_no=b.untread_no and e.worker_no=a.check_worker "
				+ "and a.warehouse_no='" + warehouseNo + "' " +
			      "and a.enterprise_no=b.enterprise_no and a.enterprise_no=c.enterprise_no " +
			      "and a.enterprise_no=d.enterprise_no and a.enterprise_no=e.enterprise_no " +
			      "and a.enterprise_no='"+enterpriseNo+"' ";
		String totsql = "select count(1) "
				+ "from ridata_check_m a,ridata_untread_m b,bdef_defcust c,"
				+ "ridata_untread_sm d ,bdef_defworker e "
				+ "where a.untread_no=b.untread_no and b.cust_no=c.cust_no and "
				+ "d.untread_no=b.untread_no and e.worker_no=a.check_worker "
				+ "and a.warehouse_no='" + warehouseNo + "' " +
				  "and a.enterprise_no=b.enterprise_no and a.enterprise_no=c.enterprise_no " +
			      "and a.enterprise_no=d.enterprise_no and a.enterprise_no=e.enterprise_no " +
			      "and a.enterprise_no='"+enterpriseNo+"' ";

		if (workerOwner != null && !workerOwner.equals("")) {
			sql = sql + " and a.owner_no in(" + workerOwner + ") ";
			totsql = totsql + "and a.owner_no in(" + workerOwner + ")";
		} else {
			sql = sql + " and 1=2";
			totsql = totsql + " and 1=2";
		}
		if (queryStr != null && !queryStr.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(queryStr);
			sql = sql + ws;
			totsql = totsql + ws;
			if (ws.indexOf("a.status") == -1) {
				sql += " and a.status<>13 ";
			}
		} else {
			sql += " and a.status<>13 ";
			totsql += " and a.status<>13 ";
		}
		List<Ridata_CheckMModel> list = genDao.getListByNativeSql(sql,
				Ridata_CheckMModel.class, pageBo.getStart(),
				pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Ridata_CheckMModel> extListBo = new ExtListDataBo<Ridata_CheckMModel>(
				list, count);
		return extListBo;
	}

	//表单验收保存
	@Override
	public void save(String jsonMaster, String jsonDetail) throws Exception {
		List<Ridata_CheckDModel> listd = JSON.parseArray(jsonDetail,
				Ridata_CheckDModel.class);
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		outList.add("S");
		outList.add("S");
		List outList2 = new ArrayList();
		List resultList2 = new ArrayList();
		outList2.add("S");
		for (int i = 0; i < listd.size(); i++) {
			List inList = new ArrayList();
			inList.add(listd.get(i).getEnterpriseNo());//strEnterpriseNo
			inList.add(listd.get(i).getWarehouseNo());//strWarehouseNo
			inList.add(listd.get(i).getOwnerNo());//strOwnerNo
			inList.add(listd.get(i).getSUntreadNo());//strsUntreadNo
			inList.add(listd.get(i).getUntreadType());//strUntreadType
			inList.add(listd.get(i).getArticleNo());//strArticleNo
			inList.add(listd.get(i).getBarcode());//strBarcode
			inList.add(listd.get(i).getPackingQty());//nPackingQty
			inList.add(listd.get(i).getCheckQty());//nCheckQty
			inList.add("N");//strPrinterGroupNo
			inList.add(listd.get(i).getDockNo());//strDockNo
			inList.add(listd.get(i).getWorkerNo());//strWorkerNo
			inList.add("1");//strCheckTools
			inList.add("1");//nIsAdd
			inList.add(StringUtil.isStrEmpty(listd.get(i).getQuality())?"0":listd.get(i).getQuality());
			inList.add(listd.get(i).getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):listd.get(i).getProduceDate());
			inList.add(listd.get(i).getExpireDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):listd.get(i).getExpireDate());			
			inList.add(StringUtil.isStrEmpty(listd.get(i).getLotNo())?"N":listd.get(i).getLotNo());//strLotNo
			inList.add("N");//strRSV_BATCH1
			inList.add("N");//strRSV_BATCH2
			inList.add("N");//strRSV_BATCH3
			inList.add("N");//strRSV_BATCH4
			inList.add("N");//strRSV_BATCH5
			inList.add("N");//strRSV_BATCH6
			inList.add("N");//strRSV_BATCH7
			inList.add("N");//strRSV_BATCH8
			inList.add("N");//strLabelNo
			inList.add("N");//strSubLabelNo
			inList.add(listd.get(i).getSupplierNo());//strSupplierNo
			inList.add("1");//strFixPalFlag
			inList.add("0");//strBusinessType
			inList.add("N");
			inList.add("N");
			inList.add(listd.get(i).getQualityFlag());
			inList.add("N");
			inList.add(listd.get(i).getClassType());
			
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList,"pklg_ridata.P_RF_SaveCheck");
			System.out.println(resultList);
			if (resultList.get(1).toString().indexOf("N|") != -1) {
				throw new Exception(resultList.get(1).toString());
			}
		}
		String sql="select distinct t1.quality_flag,t1.label_no " +
          "from ridata_check_pal_tmp t1 " +
	       "where t1.enterprise_no= '"+listd.get(0).getEnterpriseNo()+"' " +
	       	"and t1.warehouse_no= '"+listd.get(0).getWarehouseNo()+"' " +
	       	"and t1.owner_no= '"+listd.get(0).getOwnerNo()+"' " +
	       	"and t1.s_untread_no= '"+listd.get(0).getSUntreadNo()+"' ";
		List<Ridata_CheckDModel> list = genDao.getListByNativeSql(sql,Ridata_CheckDModel.class);
		if(list.size()>0){
			for(int j=0;j<list.size();j++){
				List inList2 = new ArrayList();
				inList2.add(listd.get(0).getEnterpriseNo());
				inList2.add(listd.get(0).getWarehouseNo());
				inList2.add(listd.get(0).getOwnerNo());
				inList2.add(listd.get(0).getSUntreadNo());
				inList2.add(listd.get(0).getUntreadType());
				inList2.add(listd.get(0).getClassType());
				inList2.add(list.get(j).getQualityFlag());//strQualityFlag
				inList2.add(resultList.get(0));//strsCheckNo
				inList2.add(list.get(j).getLabelNo());
				inList2.add(listd.get(0).getWorkerNo());
				inList2.add(listd.get(0).getDockNo());
				inList2.add(listd.get(0).getStrPrintFlag());//strPrintFlag
				System.out.println(inList2);
				resultList2 = genDao.execProc(inList2, outList2,"PKLG_RIDATA.P_SaveClosePal_main");
				if (resultList2.get(0).toString().indexOf("N|") != -1) {
					throw new Exception(resultList2.get(0).toString());
				}
			}
		}else{
			throw new Exception("找不到未封板数据");
		}
		
	}

	@Override
	public List<ComboxBo> getUntreadNoList(String enterpriseNo,String strOwnerNo,
			String strWarehouseNo, String strPageSql) throws Exception {
		String sql = "select a.s_untread_no as value,"
				+ "a.s_untread_no as text," + "a.s_untread_no dropValue "
				+ "from ridata_untread_mm a where a.warehouse_no='"
				+ strWarehouseNo + "' " + "and a.status not in('13','16') " +
				"and a.enterprise_no='"+enterpriseNo+"' ";
		if (strOwnerNo != null && !strOwnerNo.equals("")) {
			sql = sql + " and a.owner_no in(" + strOwnerNo + ")";
		} else {
			sql = sql + " and 1=2 ";
		}
		if (strPageSql != null && strPageSql != "") {
			sql += " and a.s_untread_no like '%" + strPageSql + "%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	//根据返配汇总单号获取货主编码
	@Override
	public MsgRes getOwnerNo(String strEnterpriseNo, String strWarehouseNo,
			String strSUntreadNo) throws Exception {
		String sql="select distinct a.owner_no from ridata_untread_mm a where a.s_untread_no='"+strSUntreadNo+"' " +
				" and a.enterprise_no='"+strEnterpriseNo+"' " +
				" and a.warehouse_no='"+strWarehouseNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()>0){
			return new MsgRes(true,list.get(0),"");
		}else{
			return new MsgRes(true,"","");
		}
	}
}
