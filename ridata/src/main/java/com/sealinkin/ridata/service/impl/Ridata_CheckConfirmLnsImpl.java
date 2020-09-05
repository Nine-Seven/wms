/**
 * 返配验收确认
 * csr
 */
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
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckConfirmLnsService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_CheckConfirmLnsImpl implements
		IRidata_CheckConfirmLnsService {
private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public ExtListDataBo<Ridata_CheckMModel> queryCheckM(String enterpriseNo,String warehouseNo,
			String strQuery, PageBo poPageBo) throws Exception {
		
		String strSql="select a.warehouse_no,a.s_check_no,a.check_no," +
				"a.owner_no,a.untread_type,a.untread_no, " +
				"a.dock_no,a.check_worker,a.status," +
				"a.check_start_date, a.check_end_date," +
				"a.printer_group_no,a.check_tools," +
				"a.send_flag,a.print_times, a.rgst_name," +
				"a.rgst_date,a.updt_name,a.updt_date,b.owner_name," +
				"f_get_fieldtext('N','STATUS'," +
				"a.status)statusText ,iim.po_no,t.cust_no,t.cust_name,sm.s_untread_no " +
				"from RIDATA_CHECK_M a,ridata_untread_m iim,ridata_untread_sm sm, " +
				"bdef_defowner b,BDEF_DEFCUST T " +
				"where a.warehouse_no=iim.warehouse_no " +
				"and a.untread_no=iim.untread_no " +
				"and a.owner_no = iim.owner_no " +
				"and a.warehouse_no = sm.warehouse_no " +
				"and a.untread_no = sm.untread_no " +
				"and a.owner_no=b.owner_no " +
				"and iim.owner_no = t.owner_no " +
				"and iim.cust_no = t.cust_no " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no=iim.enterprise_no " +
				"and a.enterprise_no=sm.enterprise_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=t.enterprise_no " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.status < 13 ";
			
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		strSql += " order by a.untread_no desc";
	    String strTotsql="select count(*) from ("+strSql+")";
		List<Ridata_CheckMModel> list = genDao.getListByNativeSql(
				strSql,Ridata_CheckMModel.class,poPageBo.getStart(), poPageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Ridata_CheckMModel> extListBo= new ExtListDataBo<Ridata_CheckMModel>(list, count);
        return extListBo;
	}
	@Override
	public ExtListDataBo<Ridata_CheckDModel> queryCheckD(String enterpriseNo,String warehouseNo,
			String sCheckNo) throws Exception {
		String strSql="select a.enterprise_no,a.warehouse_no, a.owner_no,b.owner_article_no as ownerArticleNo, a.check_no, a.row_id, a.article_no, a.packing_qty,"+ 
			    "a.barcode, a.produce_date, a.expire_date, a.quality, a.lot_no, "+
				"a.rsv_batch1, a.rsv_batch2, a.rsv_batch3, a.rsv_batch4, "+
				"a.rsv_batch5, a.rsv_batch6, a.rsv_batch7, a.rsv_batch8, "+
				"a.stock_type, a.stock_value, a.dept_no, a.check_qty,"+
				"a.check_start_date, a.check_end_date, bd.worker_name workerNo,"+
		        "b.article_name," +
		       //" nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
                //"nvl(c.spec, '1*' || a.packing_qty|| b.unit) spec,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+
		        "und.untread_qty,  "+
		        "(a.check_qty/a.packing_qty) as checkBox,"+
		        "f_get_fieldtext('N','QUALITY',a.quality)as qualityText "+
		        "from ridata_check_d a,bdef_defarticle b,bdef_article_packing c," +
		        "ridata_check_m m,ridata_untread_d und,bdef_defworker bd  "+
		        "where a.article_no=b.article_no "+
		        "and a.packing_qty=c.packing_qty(+) "+
		        "and a.article_no=c.article_no(+) "+
		        "and a.enterprise_no=b.enterprise_no " +
		        "and a.enterprise_no=c.enterprise_no(+) " +
		        "and a.enterprise_no=m.enterprise_no "+
	            "and a.warehouse_no=m.warehouse_no "+
	            "and a.check_no=m.check_no "+
	            "and m.enterprise_no=und.enterprise_no "+
	            "and m.warehouse_no=und.warehouse_no "+
	            "and m.untread_no=und.untread_no "+
	            "and a.article_no=und.article_no " +
	            "and a.enterprise_no=bd.enterprise_no " +
                "and a.check_worker=bd.worker_no "+
		        "and a.enterprise_no='"+enterpriseNo+"' "+
				"and m.s_check_no='"+sCheckNo+"' " +
				"and a.warehouse_no='"+warehouseNo+"'";		
	List<Ridata_CheckDModel> list = genDao.getListByNativeSql(
			strSql,Ridata_CheckDModel.class,0, 10000);
	ExtListDataBo<Ridata_CheckDModel> extListBo= new ExtListDataBo<Ridata_CheckDModel>(list, 0);
    return extListBo;
}
	@Override
	public MsgRes tscCheckPalTmp(String enterpriseNo,String warehouseNo, String ownerNo,
			String sUntreadNo, String sCheckNo, String workerNo, String dockNo)
			throws Exception {
			
		List outList=new ArrayList();	
		outList.add("S");
				
		List inList=new ArrayList();
		List resultList=new ArrayList();
		
		inList.add(enterpriseNo);//strEnterPriseNo
		inList.add(warehouseNo);//strWAREHOUSE_NO
		inList.add(sUntreadNo);//strS_untread_no
		inList.add(sCheckNo);//strS_Check_no
		inList.add(workerNo);//strWorkerNo
		inList.add(dockNo);//strDockNo
		inList.add("1");//strComfirFlag
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_RIDATA.P_comfireCheck");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
	
		return new MsgRes(true,"确认成功","");
	}
		@Override
	public MsgRes saveChenkPalTmp(String jsonDetail) throws Exception {
		System.out.println(jsonDetail);
		List<Ridata_CheckPalTmpModel> listd = JSON.parseArray(jsonDetail,
				Ridata_CheckPalTmpModel.class);
		
		String sql="delete from  ridata_check_pal_tmp a where "
				+" a.warehouse_no ='"+listd.get(0).getWarehouseNo()+
				"' and a.s_check_no='"+listd.get(0).getSCheckNo()+"' " +
				"and a.enterprise_no='"+listd.get(0).getEnterpriseNo()+"' ";
		genDao.updateBySql(sql);
		
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		outList.add("S");
		
		for (int i = 0; i < listd.size(); i++) {	
			if(listd.get(i).getCheckQty()!=0){
				List inList = new ArrayList();	
				inList.add(listd.get(i).getEnterpriseNo());//strEnterpriseNo
				inList.add(listd.get(i).getWarehouseNo());//strWarehouseNo
				inList.add(listd.get(i).getOwnerNo());//strOwnerNo
				inList.add(listd.get(i).getSUntreadNo());//strsUntreadNo
				inList.add(listd.get(i).getArticleNo());//strArticleNo
				inList.add(listd.get(i).getBarcode());//strBarcode
				inList.add(listd.get(i).getPackingQty());//nPackingQty
				inList.add(listd.get(i).getCheckQty());//nCheckQty
				inList.add(listd.get(i).getPrinterGroupNo());//strPrinterGroupNo
				inList.add(listd.get(i).getDockNo());//strDockNo
				inList.add(listd.get(i).getWorkerNo());//strWorkerNo
				inList.add(listd.get(i).getCheckTools());//strCheckTools
				inList.add("1");//nIsAdd
				inList.add(listd.get(i).getQuality());//strQuality
				inList.add(listd.get(i).getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):listd.get(i).getProduceDate());
				inList.add(listd.get(i).getExpireDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):listd.get(i).getExpireDate());			
				inList.add(StringUtil.isStrEmpty(listd.get(i).getLotNo())?"N":listd.get(i).getLotNo());//strLotNo
				inList.add(listd.get(i).getRsvBatch1());//strRSV_BATCH1
				inList.add(listd.get(i).getRsvBatch2());//strRSV_BATCH2
				inList.add(listd.get(i).getRsvBatch3());//strRSV_BATCH3
				inList.add(listd.get(i).getRsvBatch4());//strRSV_BATCH4
				inList.add(listd.get(i).getRsvBatch5());//strRSV_BATCH5
				inList.add(listd.get(i).getRsvBatch6());//strRSV_BATCH6
				inList.add(listd.get(i).getRsvBatch7());//strRSV_BATCH7
				inList.add(listd.get(i).getRsvBatch8());//strRSV_BATCH8
				inList.add(listd.get(i).getLabelNo());//strLabelNo
				inList.add(listd.get(i).getSubLabelNo());//strSubLabelNo
				inList.add(listd.get(i).getSupplierNo());//strSupplierNo
				inList.add(listd.get(i).getFixpalFlag());//strFixPalFlag
				inList.add(listd.get(i).getBusinessType());//strBusinessType
					
				resultList = genDao.execProc(inList, outList,"pklg_ridata.P_SaveCheck");
				System.out.println(resultList);
				if (resultList.get(0).toString().indexOf("N|") != -1) {
					throw new Exception();
				}		
			}
		}
		
		
		return new MsgRes(true,"保存成功","");
	}
	
}
