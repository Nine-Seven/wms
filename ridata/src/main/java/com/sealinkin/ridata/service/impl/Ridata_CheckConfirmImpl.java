package com.sealinkin.ridata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckMModel;
import com.sealinkin.ridata.model.Ridata_CheckPalModel;
import com.sealinkin.ridata.service.IRidata_CheckConfirmService;
import com.sealinkin.util.CommUtil;

/**
 * 返配验收确认
 * @author hkl
 */
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class Ridata_CheckConfirmImpl implements IRidata_CheckConfirmService{
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

	

	/**
	 * 获取验收确认头档
	 */
	@Override
	public ExtListDataBo<Ridata_CheckMModel> queryCheckM(String strenterpriseNo,String strWarehouseNo,
			String strWheresql,PageBo poPageBo) throws Exception {
		String strSql="select a.warehouse_no,a.s_check_no,a.check_no," +
				"a.owner_no,a.untread_type,a.untread_no, " +
				"a.dock_no,a.check_worker,a.status," +
				"a.check_start_date, a.check_end_date," +
				"a.printer_group_no,a.check_tools," +
				"a.send_flag,a.print_times, a.rgst_name," +
				"a.rgst_date,a.updt_name,a.updt_date,b.owner_name," +
				"c.worker_name, f_get_fieldtext('N','STATUS'," +
				"a.status)statusText ,iim.po_no,t.cust_no,t.cust_name," +
				"sm.s_untread_no,iim.class_type,iim.quality " +
				"from RIDATA_CHECK_M a,ridata_untread_m iim,ridata_untread_sm sm," +
				"bdef_defowner b,bdef_defworker c,BDEF_DEFCUST T " +
				"where a.warehouse_no=iim.warehouse_no " +
				"and a.untread_no=iim.untread_no " +
				"and a.owner_no = iim.owner_no " +
				"and a.warehouse_no = sm.warehouse_no " +
				"and a.untread_no = sm.untread_no " +
				"and a.owner_no=b.owner_no " +
				"and a.check_worker=c.worker_no " +
				"and iim.owner_no = t.owner_no " +
				"and iim.cust_no = t.cust_no " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no=iim.enterprise_no " +
				"and a.enterprise_no=sm.enterprise_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.enterprise_no=t.enterprise_no " +
				"and a.enterprise_no='"+strenterpriseNo+"' " +
				"and a.status<>13 ";
		/*String strTotsql = "select count(1) " +
				"from RIDATA_CHECK_M a,bdef_defowner b,bdef_defworker c  " +
				"where a.owner_no=b.owner_no " +
				"and a.check_worker=c.worker_no " +
				"and a.warehouse_no='"+strWarehouseNo+"' " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.enterprise_no=c.enterprise_no " +
				"and a.enterprise_no='"+strenterpriseNo+"' " +
				"and a.status<>13  ";			*/
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strWheresql);
			strSql=strSql+ft;
			//strTotsql=strTotsql+ft;
		}
		strSql=strSql+" order by iim.po_no, a.check_no desc";
		List<Ridata_CheckMModel> list = genDao.getListByNativeSql(
				strSql,Ridata_CheckMModel.class,poPageBo.getStart(), 10000);
		//Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Ridata_CheckMModel> extListBo= new ExtListDataBo<Ridata_CheckMModel>(list,0);
        return extListBo;
	}
	
	/**
	 * 获取验收确认明细
	 */
	@Override
	public ExtListDataBo<Ridata_CheckDModel> queryCheckD(
			String strenterpriseNo,String strWarehouseNo,String strCheckNo)
			throws Exception {
		String strSql="select a.enterprise_no,a.warehouse_no, a.owner_no,b.owner_article_no as ownerArticleNo, a.check_no, a.row_id, a.article_no, a.packing_qty,"+ 
					"trunc(und.untread_qty/und.packing_qty) as planBox,"+
					"trunc(mod(und.untread_qty,und.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
					"(und.untread_qty - trunc(und.untread_qty/und.packing_qty) * und.packing_qty - trunc(mod(und.untread_qty,und.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis,"+
					"b.unit_packing,b.qmin_operate_packing,"+
					
					"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
					"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin,"+
					"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit,"+
					"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
					"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin,"+
					"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec,"+

					
					"trunc(und.untread_qty/und.packing_qty) as realBox,"+
					"trunc(mod(und.untread_qty,und.packing_qty)/b.QMIN_OPERATE_PACKING) as realQmin,"+
					"(und.untread_qty - trunc(und.untread_qty/und.packing_qty) * und.packing_qty - trunc(mod(und.untread_qty,und.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as realDis,"+
					
					"a.barcode, a.produce_date, a.expire_date, a.quality, a.lot_no, "+
					"a.rsv_batch1, a.rsv_batch2, a.rsv_batch3, a.rsv_batch4, "+
					"a.rsv_batch5, a.rsv_batch6, a.rsv_batch7, a.rsv_batch8, "+
					"a.stock_type, a.stock_value, a.dept_no, a.check_qty,"+
					"a.check_start_date, a.check_end_date,"+
//					"nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
//                    " nvl(c.spec, '1*' || a.packing_qty || b.unit) spec,"+
			        "b.article_name,und.untread_qty as untread_qty,  "+
			        "(a.check_qty/a.packing_qty) as checkBox,"+
			        "f_get_fieldtext('N','QUALITY',a.quality)as qualityText "+
			        "from ridata_check_d a,bdef_defarticle b,bdef_article_packing c," +
			        "ridata_check_m m,ridata_untread_d und "+
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
		            "and a.article_no=und.article_no "+
			        "and a.enterprise_no='"+strenterpriseNo+"' "+
					"and a.check_no='"+strCheckNo+"' " +
					"and a.warehouse_no='"+strWarehouseNo+"' " +
					"  order by und.article_no,und.packing_qty ";		
		List<Ridata_CheckDModel> list = genDao.getListByNativeSql(
				strSql,Ridata_CheckDModel.class,0, 10000);
		ExtListDataBo<Ridata_CheckDModel> extListBo= new ExtListDataBo<Ridata_CheckDModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 获取验收确认箱明细
	 */
	@Override
	public ExtListDataBo<Ridata_CheckPalModel> queryCheckPal(String strEnterpriseNo,String strCheckNo)
			throws Exception {
		String strSql="select a.warehouse_no,a.owner_no,b.owner_article_no,a.check_no, a.check_row_id, a.article_no,"+
		       " a.packing_qty, a.check_qty, a.status, a.label_no, a.printer_group_no, a.dock_no, a.sub_label_no,"+ 
		       " a.quality,b.barcode,"+
		       " a.fixpal_flag, a.container_no,a.business_type,"+
		       " b.article_name,b.owner_article_no as ownerArticleNo," +
		       "nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
             " nvl(c.spec, '1*' || a.packing_qty || b.unit) spec,"+
		       "(a.check_qty/a.packing_qty) as checkBox, "+
             
			   "trunc(a.check_qty/a.packing_qty) as planBox,"+
			   "trunc(mod(a.check_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) as planQmin,"+
			   "(a.check_qty - trunc(a.check_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.check_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as planDis,"+
			   "b.unit_packing,b.qmin_operate_packing,b.spec as packingSpec, "+
			   
			   "trunc(a.check_qty/a.packing_qty) as realBox,"+
			   "trunc(mod(a.check_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) as realQmin,"+
		       "(a.check_qty - trunc(a.check_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.check_qty,a.packing_qty)/b.QMIN_OPERATE_PACKING) * b.QMIN_OPERATE_PACKING) as realDis"+
		       
		       " from ridata_check_pal a,bdef_defarticle b,bdef_article_packing c "+
		       " where a.article_no=b.article_no and a.article_no=c.article_no(+) "+
		       " and a.packing_qty=c.packing_qty(+) "+
		       " and a.enterprise_no=b.enterprise_no " +
		       " and a.enterprise_no=c.enterprise_no(+) " +
		       " and a.enterprise_no='"+strEnterpriseNo+"'"+
			   " and a.check_no='"+strCheckNo+"' order by a.label_no,a.article_no";
		
		List<Ridata_CheckPalModel> list = genDao.getListByNativeSql(
				strSql,Ridata_CheckPalModel.class,0, 10000/*pageBo.getPagesize()*/);
		ExtListDataBo<Ridata_CheckPalModel> extListBo= new ExtListDataBo<Ridata_CheckPalModel>(list, 0);
        return extListBo;
	}
	/**
	 * 获取验收确认未封箱明细
	 */
	@Override
	public ExtListDataBo<Ridata_CheckPalModel> queryUnCheckPal(
			String strEnterpriseNo,String strSUntreadNo)
			throws Exception {
		String strSql="select a.warehouse_no, a.owner_no, a.s_check_no, a.article_no,"+
				" a.packing_qty, a.check_qty, a.status, a.label_no, a.printer_group_no, a.dock_no, a.sub_label_no,"+
				" a.barcode, a.produce_date, a.expire_date, a.quality, a.lot_no, a.rsv_batch1, a.rsv_batch2," +
				" a.rsv_batch3, a.rsv_batch4, a.rsv_batch5, a.rsv_batch6, a.rsv_batch7, a.rsv_batch8, a.stock_type," +
		        " a.stock_value, a.dept_no, a.fixpal_flag, a.business_type,"+
		        " a.rgst_name, a.rgst_date,  b.article_name,b.owner_article_no as ownerArticleNo," +
		        "nvl(c.packing_unit, decode(a.packing_qty,b.qmin_operate_packing,b.qmin_operate_packing_unit,b.unit)) packing_unit,"+
                " nvl(c.spec, '1*' || a.packing_qty || b.unit) spec,"+
		        " (a.check_qty/a.packing_qty) as checkBox "+
		        " from ridata_check_pal_tmp a,bdef_defarticle b,bdef_article_packing c "+
		        " where a.article_no=b.article_no and a.article_no=c.article_no(+) "+
		        " and a.packing_qty=c.packing_qty(+) "+
		        " and a.enterprise_no=b.enterprise_no " +
		        " and a.enterprise_no=c.enterprise_no(+) " +
		        " and a.enterprise_no='"+strEnterpriseNo+"'"+
			    " and a.s_untread_no='"+strSUntreadNo+"'  order by a.label_no,a.article_no";
		
		List<Ridata_CheckPalModel> list = genDao.getListByNativeSql(
				strSql,Ridata_CheckPalModel.class,0, 10000);
		ExtListDataBo<Ridata_CheckPalModel> extListBo= new ExtListDataBo<Ridata_CheckPalModel>(list, 0);
        return extListBo;
	}
	
	/**
	 * 验收确认
	 */
	@Override
	public MsgRes tscConfirm(String strJsonMaster) throws Exception {
		Ridata_CheckMModel poMaster=(Ridata_CheckMModel) JSON.parseObject(strJsonMaster, Ridata_CheckMModel.class);
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(poMaster.getEnterpriseNo());//strEnterprise_NO
		inList.add(poMaster.getWarehouseNo());//strWAREHOUSE_NO
		inList.add(poMaster.getSUntreadNo());//strS_import_no
		inList.add(poMaster.getUntreadType());
		inList.add(poMaster.getClassType());
		inList.add(poMaster.getQuality());
		inList.add(poMaster.getOwnerNo());
		inList.add(poMaster.getSCheckNo());//strS_Check_no
		inList.add(poMaster.getCheckWorker());//strWorkerNo
		inList.add(poMaster.getDockNo());//strDockNo
		inList.add("1");//strComfirFlag
		System.out.println(strJsonMaster);
		resultList=genDao.execProc(inList, outList, "PKLG_RIDATA.P_comfireCheckTTH");
		System.out.println(resultList);
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,"确认成功","");
	}
	/**
	 * 校验临时表中是否还有数据
	 */
	@Override
	public MsgRes checkPalTmp(String strEnterpriseNo,String strWarehouseNo, String strSUntreadNo)
			throws Exception {
		String strSql="select warehouse_no " +
				"from ridata_check_pal_tmp icpt " +
				"where icpt.warehouse_no='"+strWarehouseNo+"' " +
				"and icpt.s_untread_no='"+strSUntreadNo+"' " +
				"and icpt.enterprise_no='"+strEnterpriseNo+"'";
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(false,"","");
		}else{
			return new MsgRes(true,"","");
		}
	}
	/**
	 * 校验计划量与验收量是否相等
	 */
	@Override
	public MsgRes checkQty(String strEnterpriseNo, String strWarehouseNo,
			String strWheresql) throws Exception {
		String strSql="select * from ridata_untread_d d " +
				"where d.untread_no='"+strWheresql+"' " +
				"and d.untread_qty<>d.check_qty " +
				"and d.enterprise_no='"+strEnterpriseNo+"' " +
				"and d.warehouse_no='"+strWarehouseNo+"' ";
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(true,"","");
		}else{
			return new MsgRes(false,"","");
		}
	}

}
