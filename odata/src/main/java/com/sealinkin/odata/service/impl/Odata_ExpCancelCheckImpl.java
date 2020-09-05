package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpCancelDModel;
import com.sealinkin.odata.model.Odata_ExpCancelMModel;
import com.sealinkin.odata.service.IOdata_ExpCancelCheckService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_ExpCancelCheckImpl implements IOdata_ExpCancelCheckService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	/**
	 * 病单处理》货主、病单单号下拉事件
	 * strFlag='1':货主
	 * strFlag='2':下架单号
	 */
	@Override
	public List<ComboxBo> getOdata_CancelCheckCombo(String enterpriseNo,String strWarehouseNo,String strWorkerOwner,
			String strFlag, String str,int intStart, int intPagesize) {
		String strSql = "";
		if(strFlag != null && strFlag.equals("1")){
			strSql = "select distinct ood.owner_no value, t2.owner_name text,  " +
					"'['|| ltrim(ood.owner_no)||']'||t2.owner_name dropValue  " +
					" from ODATA_EXP_CANCEL_d ood,ODATA_EXP_CANCEL_M oom, bdef_defowner t2 " +
					" where ood.CANCEL_NO=oom.CANCEL_NO " +
					" and ood.enterprise_no=oom.enterprise_no " +
					" and ood.enterprise_no=t2.enterprise_no " +
					" and ood.owner_no = t2.owner_no  " +
					//" and oom.status=14 " +
					" %s0 %s1 %s2 %s3 order by ood.owner_no";
		}
		else if(strFlag != null && strFlag.equals("2"))
		{
			strSql = "select distinct oom.CANCEL_NO value, oom.CANCEL_NO text,  " +
					" oom.CANCEL_NO dropValue  " +
					" from ODATA_EXP_CANCEL_M oom, ODATA_EXP_CANCEL_d ood " +
					" where ood.WAREHOUSE_NO=oom.warehouse_no " +
					" and ood.enterprise_no=oom.enterprise_no " +
					" and ood.CANCEL_NO=oom.CANCEL_NO " +
					" and oom.status=14  " +
					" %s0 %s1 %s2 %s3 order by oom.CANCEL_NO ";
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
		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	@Override
	public ExtListDataBo<Odata_ExpCancelMModel> getCancelCheck_MList(
			String enterpriseNo, String warehouseNo, String owner, 
			String str, int start, int pagesize) {

		String strTotsql="";
		String strSql = "select oom.cancel_no,oom.exp_type," +
				"oom.exp_no,oom.cust_no,oom.operate_date,oom.handle_flag," +
				"f_get_fieldtext('ODATA_EXP_CANCEL_M','HANDLEFLAG',oom.handle_flag)handleFlagText " +
				"from ODATA_EXP_CANCEL_m oom,Odata_Exp_m a " +
				"where oom.enterprise_no=a.enterprise_no " +
				"and oom.warehouse_no=a.warehouse_no " +
				"and oom.owner_no=a.owner_no " +
				"and oom.exp_no=a.exp_no " +
				//"and oom.status='14' %s1 " +
				" %s1 " +
				"and oom.enterprise_no= '"+enterpriseNo+"' " +
				"and oom.warehouse_no= '"+warehouseNo+"' " +
				"and oom.owner_no in ("+owner+") ORDER BY oom.cancel_no";
        	
		if(str!=null && !str.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(str);
			strSql = strSql.replace("%s1", ws);
		}else{
			strSql = strSql.replace("%s1", "");
		}
		strTotsql = "select count(*) from (" + strSql + ")";
		List<Odata_ExpCancelMModel> list = genDao.getListByNativeSql(strSql,Odata_ExpCancelMModel.class,start,pagesize);
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Odata_ExpCancelMModel> extListBo= new ExtListDataBo<Odata_ExpCancelMModel>(list, count);
        return extListBo;
	
	}
	
	@Override
	public ExtListDataBo<Odata_ExpCancelDModel> getCancelCheck_DList(
			String enterpriseNo, String warehouseNo,
			String str, int start, int pagesize) throws Exception {

		String strSql = "select d.cancel_no,d.article_no," +
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,e.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,e.unit_packing) Unit,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,e.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,e.unit_packing) spec,"+
				"d.packing_qty,d.article_qty/d.packing_qty as article_qty," +
				"d.real_qty/d.packing_qty as real_qty," +
				"(d.article_qty-d.real_qty)/d.packing_qty as differenceQty," +
				"e.OWNER_ARTICLE_NO,e.ARTICLE_NAME,e.BARCODE " +
				"from ODATA_EXP_CANCEL_d d,bdef_defarticle e " +
				"where d.enterprise_no=e.enterprise_no " +
				"and d.owner_no=e.OWNER_NO " +
				"and d.article_no=e.ARTICLE_NO " +
				"and d.enterprise_no= '"+enterpriseNo+"' " +
				"and d.warehouse_no= '"+warehouseNo+"' " +
			    "and d.cancel_no='"+str+"' ";
	
		String strTotsql = "select count(*) from ("+ strSql +")";
	    List<Odata_ExpCancelDModel> list=genDao.getListByNativeSql(strSql, Odata_ExpCancelDModel.class, start,pagesize);
	    Integer count = genDao.getCountByNativeSql(strTotsql);
	    ExtListDataBo<Odata_ExpCancelDModel> extListBo = new ExtListDataBo<Odata_ExpCancelDModel>(list, count);
	    return extListBo;
	
	}
	@Override
	public MsgRes sendCancel(String currEnterpriseNo, String warehouseNo,
			String workerNo,String ownerNo,String workSpaceNo,String expNo,String cancelNo,String handleflag) throws Exception {

			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			
			inList.add(currEnterpriseNo);
			inList.add(warehouseNo);
			inList.add(ownerNo);
			inList.add(cancelNo);
			inList.add(expNo);
			inList.add(workerNo);
			inList.add(workSpaceNo);
			inList.add(handleflag);
			
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKLG_ODATA_EXPCANCEL.p_cancel_check");
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}		
		return new MsgRes(true,"审核成功","");
	}



}
