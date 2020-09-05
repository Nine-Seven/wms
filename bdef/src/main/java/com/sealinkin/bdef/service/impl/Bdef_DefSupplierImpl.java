package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefsupplierModel;
import com.sealinkin.bdef.po.Bdef_Defsupplier;
import com.sealinkin.bdef.service.IBdef_DefSupplierService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefSupplierImpl implements IBdef_DefSupplierService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 填充供应商下拉控件
	 */
	@Override
	public List<ComboxBo> queryBdefDefSupplierCombo(String enterpriseNo,String strOwnerNo,
			String strWheresql,String strQuery) throws Exception {
		String strSql="select distinct t1.supplier_no value,t1.supplier_name text," +
				"'['|| ltrim(t1.supplier_no)||']'||t1.supplier_name dropValue " +
				"from bdef_defsupplier t1 where 1=1 and t1.enterprise_no='"+enterpriseNo+"'" +
				" and t1.status='1' ";

		//货主过滤等条件
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql+ft;
		}
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and (t1.supplier_no like '%"+strWheresql+"%' " +
					"or t1.SUPPLIER_NOTE_CODE like '%"+strWheresql+"%' " +
					"or t1.supplier_name like '%"+strWheresql+"%') ";
		}
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		
		strSql=strSql+" order by t1.supplier_no";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	
	
	
	/*
	 * 保存或增加供应商
	 * zhouhuan
	 */
	public MsgRes save(String str,String workerNo) throws Exception {
		
		Bdef_Defsupplier bdef_defSupplier=(Bdef_Defsupplier) JSONObject.toBean(JSONObject.fromObject(str),Bdef_Defsupplier.class);
		String sql="select * from bdef_defsupplier a where a.supplier_no='"+bdef_defSupplier.getId().getSupplierNo()+"' " +
				" and a.owner_no='"+bdef_defSupplier.getId().getOwnerNo()+"' " +
				" and a.enterprise_no='"+bdef_defSupplier.getId().getEnterpriseNo()+"' " ;
		List<Bdef_DefsupplierModel> list = genDao.getListByNativeSql(sql,Bdef_DefsupplierModel.class,0, 2000);
		
		if(list.size()==0){
			bdef_defSupplier.setUpdtDate(null);
			bdef_defSupplier.setUpdtName(null);
		}else{
			bdef_defSupplier.setRgstDate(list.get(0).getRgstDate());
			bdef_defSupplier.setRgstName(list.get(0).getRgstName());
		}
		this.genDao.saveOrUpdateObj(bdef_defSupplier);
		String logSql=" insert into bdef_defsupplier_log"
			+" (enterprise_no,"
			+" owner_no,"
			+" supplier_no,"
			+" real_supplier_no,"
			+" real_supplier_name,"
			+" supplier_name,"
			+" supplier_alias,"
			+" supplier_address1,"
			+" supplier_phone1,"
			+" supplier_fax1,"
			+" supplier1,"
			+" supplier_address2,"
			+" supplier_phone2,"
			+" supplier_fax2,"
			+" supplier2,"
			+" supplier_remark,"
			+" invoice_no,"
			+" invoice_addr,"
			+" invoice_header,"
			+" status,"
			+" unload_flag,"
			+" area_no,"
			+" create_flag,"
			+" supplier_note_code,"
			+" dept_no,"
			+" rgst_name,"
			+" rgst_date,"
			+" updt_name,"
			+" updt_date,"
			+" serialid,"
			+" modiattr,"
			+" moditime,"
			+" modiopid)"
			+" select enterprise_no,owner_no,"
			+" supplier_no,"
			+" real_supplier_no,"
			+" real_supplier_name,"
			+" supplier_name,"
			+" supplier_alias,"
			+" supplier_address1,"
			+" supplier_phone1,"
			+" supplier_fax1,"
			+" supplier1,"
			+" supplier_address2,"
			+" supplier_phone2,"
			+" supplier_fax2,"
			+" supplier2,"
			+" supplier_remark,"
			+" invoice_no,"
			+" invoice_addr,"
			+" invoice_header,"
			+" status,"
			+" unload_flag,"
			+" area_no,"
			+" create_flag,"
			+" supplier_note_code,"
			+" dept_no,"
			+" rgst_name,"
			+" rgst_date,"
			+" updt_name,"
			+" updt_date,"
			+" SEQ_BDEF_DEFSUPPLIER_LOG.nextval," 
			+" 'U',sysdate,'"+workerNo+"' " 
			+" from bdef_defsupplier " 
			+" where supplier_no='"+bdef_defSupplier.getId().getSupplierNo()+"' "
			+" and owner_no='"+bdef_defSupplier.getId().getOwnerNo()+"' " 
			+" and enterprise_no='"+bdef_defSupplier.getId().getEnterpriseNo()+"' " ;;
	    genDao.updateBySql(logSql);
	    return new MsgRes(true,TipUtil.getTipsByKey("E21302"),null);//保存成功！		
	}

	/**
	 * 获得供应商资料
	 * zhouhuan
	 **/
	@Override
	public ExtListDataBo<Bdef_DefsupplierModel> getBdef_DefSupplierList(String enterpriseNo,String ownerNo,String str, 
			PageBo pageBo,Integer requestFlag) {
	String sql=" SELECT d.*,b.owner_name ownerName, fs.text as status_desc," 
			    +" f_get_fieldtext('BDEF_DEFSUPPLIER','UNLOAD_FLAG',d.unload_flag)unloadflagText," 
				+" f_get_fieldtext('N','DEF_STATUS',d.status)statusText,"
				+" f_get_fieldtext('N','CREATE_FLAG',d.create_flag)createFlagText,"
				+" fu.text as unload_flag_desc"
				+" from bdef_defsupplier d, "
				+" WMS_DEFFIELDVAL fs, WMS_DEFFIELDVAL fu, bdef_defowner b "
				+" where trim(nvl(d.status, '')) = fs.value(+)"
				+" and upper(fs.table_name(+)) = 'N'"
				+" and upper(fs.colname(+)) = 'DEF_STATUS'"
				+" and trim(nvl(d.unload_flag, '')) = fu.value(+)"
				+" and upper(fu.table_name(+)) = 'BDEF_DEFSUPPLIER'"
				+" and upper(fu.colname(+)) = 'UNLOAD_FLAG'"
				+" and d.owner_no in ("+ownerNo+")" 
				+" and d.owner_no = b.owner_no"
				+" and d.enterprise_no = b.enterprise_no"
				+" and d.enterprise_no='"+enterpriseNo+"' ";
        String totsql = "select count(1) " 
				        +" from bdef_defsupplier d, "
						+" WMS_DEFFIELDVAL fs, WMS_DEFFIELDVAL fu,bdef_defowner b "
						+" where trim(nvl(d.status, '')) = fs.value(+)"
						+" and upper(fs.table_name(+)) = 'N'"
						+" and upper(fs.colname(+)) = 'DEF_STATUS'"
						+" and trim(nvl(d.unload_flag, '')) = fu.value(+)"
						+" and upper(fu.table_name(+)) = 'BDEF_DEFSUPPLIER'"
						+" and upper(fu.colname(+)) = 'UNLOAD_FLAG'"
						+" and d.owner_no in ("+ownerNo+")" 
						+" and d.owner_no = b.owner_no"
						+" and d.enterprise_no = b.enterprise_no"
						+" and d.enterprise_no='"+enterpriseNo+"' ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		sql=sql+" Order By d.owner_no,d.SUPPLIER_NO ";
		List<Bdef_DefsupplierModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefsupplierModel> extListBo= new ExtListDataBo<Bdef_DefsupplierModel>(list, count);
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bdef_DefsupplierModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bdef_DefsupplierModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bdef_DefsupplierModel>(list, count);
        return extListBo;
	}
	/*
	 * 删除供应商
	 * zhouhuan
	 */
	@Override
	public MsgRes delete(String enterpriseNo,String owner_no,String supplier_no) {
		String sql=" select a.supplier_no from idata_import_m a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.supplier_no='"+supplier_no+"' " +
				   "    and a.owner_no='"+owner_no+"' " +
				   " union " +
				   " select a.supplier_no from rodata_recede_m a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.supplier_no='"+supplier_no+"' " +
				   "    and a.owner_no='"+owner_no+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该供应商有业务数据，不能删除","");
		}
		
		String deleteSql="delete from bdef_defsupplier where owner_no='" + owner_no + 
				 "' and supplier_no='" + supplier_no + "" +
				 "' and enterprise_no='" + enterpriseNo + "'";
         genDao.updateBySql(deleteSql);
         return new MsgRes(true,"删除成功","");
	}

	@Override
	public List<ComboxBo> getSupplierComboList(String enterpriseNo,String ownerNo,String str, String wheresql,
			int start, int pagesize) {
		String sql="select a.supplier_no as value,a.supplier_name as text,'['|| ltrim(a.supplier_no)||']'||a.supplier_name dropValue from bdef_defsupplier a where 1=1 " +
				   " and a.owner_no in ("+ownerNo+") " +
				   " and a.enterprise_no='"+enterpriseNo+"' ";
		if(wheresql!=null && wheresql!=""){
		    sql+="and a.supplier_no like '%"+wheresql+"%'";
		}
		List list= (List<Object>) genDao.getListByNativeSql(sql, ComboxBo.class, start, pagesize);		 
		return  (List<ComboxBo>)list;
	}
	@Override
	public String existsSupplierNo(String enterpriseNo, String ownerNo,String supplierNo) {
		String sql="select a.supplier_no from bdef_defsupplier a " +
				"where a.owner_no='"+ownerNo+
				"' and a.supplier_no='"+supplierNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
}
