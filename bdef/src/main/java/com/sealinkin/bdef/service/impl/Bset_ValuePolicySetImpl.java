package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.service.IBset_ValuePolicySetService;
import com.sealinkin.bset.model.Bset_ValuePolicySetModel;
import com.sealinkin.bset.po.Bset_ValuePolicySet;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bset_ValuePolicySetImpl implements IBset_ValuePolicySetService{
	private IGenericManager genDao;
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	public ExtListDataBo<Bset_ValuePolicySetModel> getValuePolicySetList(
			String strWarehouseNo, String strOwnerNo,PageBo poPageBo) throws Exception {
		String strSql="select v1.*,e1.strategy_name," +
				"'['|| ltrim(c1.warehouse_no)||']'||c1.warehouse_name warehouseNoText," +
				"'['|| ltrim(d1.owner_no)||']'||d1.owner_name ownerNoText," +
				"'['|| ltrim(v1.billing_project)||']'||f1.project_name billingProjectText," +
				"'['|| ltrim(v1.billing_unit)||']'||w1.text billingUnitText," +
				" '['|| ltrim(v1.rule_id)||']'||f_get_fieldtext('BSET_FORMULASET','BILLING_TYPE',f1.billing_type)  valueFlagText  " +
				"from bset_valuepolicyset v1 " +
				"left join bdef_defowner d1 on v1.owner_no=d1.owner_no " +
				"left join  bdef_defloc c1 on v1.warehouse_no=c1.warehouse_no " +
				"left join Bset_FormulaSet f1 on v1.billing_project=f1.billing_project " +
				"left join wms_deffieldval w1 on v1.billing_unit=w1.value " +
				"left join WMS_Billing_RULE e1 on v1.rule_id=e1.rule_id " +
				"where 1=1 and w1.colname='BILLING_UNIT'" +
				" and  f1.billing_type=e1.billing_type and v1.billing_unit=e1.billing_unit" +
				" and v1.warehouse_no='"+strWarehouseNo+"'";
		String strTotsql="select count(1)  from   bset_valuepolicyset v1 " +
				"where 1=1 and v1.warehouse_no='"+strWarehouseNo+"'";
		if(strOwnerNo!=null && !strOwnerNo.equals("")&& !strOwnerNo.equals("all"))
		{
			strSql=strSql+" and v1.owner_no in('"+strOwnerNo+"') ";
			strTotsql=strTotsql+" and v1.owner_no in('"+strOwnerNo+"')";
		}
		List<Bset_ValuePolicySetModel> list=null;
		Integer intCount = 0;
		ExtListDataBo<Bset_ValuePolicySetModel> extListBo=null;
		list = genDao.getListByNativeSql(strSql,Bset_ValuePolicySetModel.class,poPageBo.getStart(),poPageBo.getPagesize());
		intCount = genDao.getCountByNativeSql(strTotsql);
		extListBo= new ExtListDataBo<Bset_ValuePolicySetModel>(list, intCount);
        return extListBo;
	}
	
	

	@Override
	public MsgRes saveValuePolicySet(String strBsetValuePolicySet,String warehouseNo,
			String strOwnerNo,String strBillingProject) throws Exception {
		Bset_ValuePolicySet poDd = (Bset_ValuePolicySet)JSONObject.toBean(
				JSONObject.fromObject(strBsetValuePolicySet),Bset_ValuePolicySet.class);
		String sql="select * from bset_valuepolicyset v1" +
				" where 1=1 and v1.warehouse_no='"+warehouseNo+"'" +
				" and v1.owner_no='"+strOwnerNo+"'" +
				" and v1.billing_project='"+strBillingProject+"'";
		List<Bset_ValuePolicySetModel> list = genDao.getListByNativeSql(sql,Bset_ValuePolicySetModel.class,0, 2000);
		if(list.size()==0){
			poDd.setUpdtDate(null);
			poDd.setUpdtName(null);
		}else{
			poDd.setRgstDate(list.get(0).getRgstDate());
			poDd.setRgstName(list.get(0).getRgstName());
		}
		genDao.saveOrUpdateObj(poDd);
		return new MsgRes(true,"保存成功","");
		}
	
	@Override
	public List<ComboxBo> getBillingProjectCombo(String warehouseNo)throws Exception {
		String strSql="  select  b1.billing_project value,b1.project_name text," +
				"'['|| ltrim(b1.billing_project)||']'||b1.project_name dropValue " +
				" from  Bset_FormulaSet b1 where 1=1 "+
				" and b1.warehouse_no='"+warehouseNo+"'";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	public List<ComboxBo> getBillingUnitCombo()throws Exception {
		String strSql=" select  w1.value value,w1.text text," +
				"'['|| ltrim(w1.value)||']'||w1.text dropValue " +
				" from  wms_deffieldval w1 " +
				"where 1=1 and w1.colname='BILLING_UNIT'";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	public List<String> getValueFlagCombo(String strBillingUnit,String strBillingProject)throws Exception {
		String strSql="select distinct w1.rule_id value,w1.billing_type text," +
				"'['|| ltrim(w1.rule_id)||']'||f_get_fieldtext('BSET_FORMULASET','BILLING_TYPE',f1.billing_type) dropValue " +
				"from bset_formulaset f1   left join WMS_Billing_RULE w1  on f1.billing_type=w1.billing_type " +
				"where 1=1 and w1.rule_id is not null";
		
			strSql=strSql+" and f1.billing_project='"+strBillingProject+"'";
		
			strSql=strSql+" and w1.billing_unit in("+strBillingUnit+") ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<String>) list;
	}
	@Override
	public List<ComboxBo> getOwnerComboList(String workerOwner)
			throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where 1=1  ";
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	@Override
	public List<ComboxBo> getHouseNameComboList(String workerOwner)
			throws Exception {
		String strSql="select c1.warehouse_no value,c1.warehouse_name text," +
				"'['|| ltrim(c1.warehouse_no)||']'||c1.warehouse_name dropValue " +
				"from bdef_defloc c1 where 1=1";
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			strSql=strSql+" and c1.warehouse_no in("+workerOwner+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	@Override
	public List<ComboxBo> getAllOwnerComboList(String workerOwner)
			throws Exception {
		String strSql="select 'ALL' as value ,'ALL' text ,'ALL' dropvalue from bdef_defowner a union " +
				"select t1.owner_no value,t1.owner_name text," +
					"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
					"from bdef_defowner t1 where 1=1  ";
			
			if(workerOwner!=null && !workerOwner.equals(""))
			{
				strSql=strSql+" and t1.owner_no in("+workerOwner+") ";
			}else
			{
				strSql=strSql+" and 1=2";
			}
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
			return (List<ComboxBo>) list;
		}
	@Override
	public List<String> getRemarkCombo(String strBillingUnit,String strBillingProject) {
		String strSql=" select   w1.strategy_name " +
				"from  bset_formulaset f1 left join   WMS_Billing_RULE  w1   on f1.billing_type=w1.billing_type " +
				"where 1=1";
		
			strSql=strSql+" and  f1.billing_project='"+strBillingProject+"'";
		
			strSql=strSql+" and  w1.billing_unit='"+strBillingUnit+"'";
		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}
	@Override
	public MsgRes deleteLine(String strBillingProject, String strOwnerNo,
			String warehouseNo) {

		String strSql = "delete from bset_valuepolicyset v1" +
				" where v1.warehouse_no='"+warehouseNo+"'" +
				" and v1.owner_no='"+strOwnerNo+"'" +
				" and v1.billing_project='"+strBillingProject+"'";
		this.genDao.updateBySql(strSql);

		return new MsgRes(true, "", "");
		/*	String strLog = "  insert into cset_area_backup_m_log(  "
		+ "warehouse_no, line_id, line_name, default_flag, "
		+ "rgst_name, rgst_date, serialid, modiattr, moditime, "
		+ "modiopid )  select warehouse_no, line_id, line_name, "
		+ "default_flag, rgst_name, rgst_date,  "
		+ "SEQ_Cset_Area_Backup_M_LOG.nextval, 'D',sysdate,'"
		+ strWorkerNo + "' "
		+ " from cset_area_backup_m cabm where cabm.warehouse_no='"
		+ warehouseNo + "' " + " and cabm.line_id='" + strLineId + "'";
this.genDao.updateBySql(strLog);

String logSql2 = " insert into Cset_Area_Backup_D_log "
		+ "(warehouse_no, line_id, ware_no, area_no, "
		+ "stock_no, a_level, keep_cells, merger_flag, "
		+ "stock_flag, floor_flag, bay_flag, sort_flag, "
		+ "stockx_flag, rgst_name, rgst_date, updt_name, "
		+ "updt_date, serialid, modiattr, moditime, modiopid) "
		+ "select warehouse_no, line_id, ware_no, area_no, "
		+ "stock_no, a_level, keep_cells, merger_flag, "
		+ "stock_flag, floor_flag, bay_flag, sort_flag, "
		+ "stockx_flag, rgst_name, rgst_date, updt_name, "
		+ "updt_date, SEQ_Cset_Area_Backup_D_LOG.nextval, 'D',sysdate,'"
		+ strWorkerNo + "' " + " from Cset_Area_Backup_D "
		+ " where warehouse_no='" + warehouseNo + "' "
		+ " and line_id='" + strLineId + "'";
this.genDao.updateBySql(logSql2);*/

	}

}
