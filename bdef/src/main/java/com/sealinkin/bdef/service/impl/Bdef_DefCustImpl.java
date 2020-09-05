package com.sealinkin.bdef.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.bdef.po.Bdef_DefCust;
import com.sealinkin.bdef.service.IBdef_DefCustService;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;


@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefCustImpl implements IBdef_DefCustService{

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 获得客户资料列
	 */
	@Override
	public ExtListDataBo<Bdef_DefCustModel> getBdef_DefCust(
			String enterpriseNo,String workerOwner,String queryStr,
			PageBo pageBo,Integer requestFlag)throws Exception {
		String sql="select distinct a.OWNER_NO,a.OWNER_CUST_NO,a.CUST_NO,a.CUST_FLAG,a.CUST_TYPE," +
			"a.SHIPPING_METHOD,a.BOX_DELIVER,a.CUST_NAME,a.CUST_ALIAS,a.CUST_ADDRESS," +
			"a.CUST_POSTCODE,a.DELIVERY_ADDRESS,a.CUST_PHONE1,a.CUST_PHONE2,a.CUST_FAX1," +
			"a.CUST_FAX2,a.CUST_EMAIL1,a.CUST_EMAIL2,a.CONTACTOR_NAME1,a.CONTACTOR_NAME2," +
			"a.INVOICE_NO,a.INVOICE_ADDR,a.INVOICE_HEADER,a.REMARK,a.STATUS,a.MAX_CAR_TONNAGE," +
			"a.RECEIVING_HOURS,a.CREATE_FLAG,a.CUST_PROVINCE,a.CUST_CITY,a.CUST_ZONE," +
			"a.TRADE_FLAG,a.ONLY_DATE_FLAG,a.COLLECT_FLAG,a.CONTAINER_MATERIAL,a.WARN_FLAG," +
			"a.RGST_NAME,a.RGST_DATE,a.UPDT_NAME,a.UPDT_DATE,a.PRIO_LEVEL,a.PRIO_TYPE,a.CUST_NOTECODE," +
			"b.owner_name,a.control_type,a.control_value," +
			"f_get_fieldtext('BDEF_DEFCUST','CUST_TYPE',a.cust_type)custtypeText," +
			"f_get_fieldtext('BDEF_DEFCUST','CUST_FLAG',a.cust_flag)custflagText," +
			"f_get_fieldtext('BDEF_DEFCUST','BOX_DELIVER',a.box_deliver)boxdeliverText," +
			"f_get_fieldtext('N','DEF_STATUS',a.status)statusText," +
			"f_get_fieldtext('BDEF_DEFCUST','PRIO_TYPE',a.prio_type)priotypeText," +
			"f_get_fieldtext('BDEF_DEFCUST','SHIPPING_METHOD',a.shipping_method)shippingmethodText," +
			"f_get_fieldtext('N','CONTAINER_MATERIAL',a.container_material)containermaterialText," +
			"f_get_fieldtext('BDEF_DEFCUST','COLLECT_FLAG',a.collect_flag)collectflagText," +
			"f_get_fieldtext('BDEF_DEFCUST','ONLY_DATE_FLAG',a.only_date_flag)onlydateflagText," +
			"f_get_fieldtext('BDEF_DEFCUST','WARN_FLAG',a.warn_flag)warnflagText " +
			"from bdef_defcust a,bdef_defowner b where a.owner_no=b.owner_no and a.enterprise_no=b.enterprise_no " +
			"and a.enterprise_no='"+enterpriseNo+"' ";
        String totsql = "select count(1) " +
        		"from bdef_defcust a,bdef_defowner b where a.owner_no=b.owner_no and a.enterprise_no=b.enterprise_no " +
        		"and a.enterprise_no='"+enterpriseNo+"' ";			
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
			totsql=totsql+" and a.owner_no in("+workerOwner+")";
		}else{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
		
		sql=sql+" order by a.owner_no,a.cust_no ";
		List<Bdef_DefCustModel> list = null;
		Integer count = 0;
		ExtListDataBo<Bdef_DefCustModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Bdef_DefCustModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Bdef_DefCustModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Bdef_DefCustModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 删除客户
	 */
	@Override
	public MsgRes deleteCust(String enterpriseNo,String id) throws Exception {
		String sql=" select a.cust_no from odata_exp_m a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.cust_no='"+id+"' " +
				   "  union " +
				   "  select a.cust_no from ridata_untread_m a " +
				   "   where a.enterprise_no='"+enterpriseNo+"' " +
				   "     and a.cust_no='"+id+"'  ";
		
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该客户有业务数据，不能删除","");
		}
		
		String deleteSql="delete from BDEF_DEFCUST where CUST_NO='"+id+"' " +
				"and enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(deleteSql);
		return new MsgRes(true,"删除成功","");
	}
	
	/**
	 * 保存或修改客户
	 */
	public MsgRes saveOrUpdateCust(String str,String workNo) throws Exception {
		
		Bdef_DefCust bd=(Bdef_DefCust)JSONObject.toBean(JSONObject.fromObject(str),Bdef_DefCust.class);
		String sqlOne="select * from bdef_defcust v1" +
		" where 1=1 and v1.cust_no='"+bd.getId().getCustNo()+"' " +
		" and v1.enterprise_no='"+bd.getId().getEnterpriseNo()+"' ";
		List<Bdef_DefCustModel> list = genDao.getListByNativeSql(sqlOne,Bdef_DefCustModel.class,0, 2000);
		if(list.size()==0){
			bd.setUpdtDate(null);
			bd.setUpdtName(null);
		}else{
			bd.setRgstDate(list.get(0).getRgstDate());
			bd.setRgstName(list.get(0).getRgstName());
		}
		this.genDao.saveOrUpdateObj(bd);
		String sql="insert into bdef_defcust_log(enterprise_no,owner_no,owner_cust_no,cust_no,cust_flag," +
				"cust_type,shipping_method,box_deliver,cust_name,cust_alias,cust_address," +
				"cust_postcode,delivery_address,cust_phone1,cust_phone2,cust_fax1,cust_fax2," +
				"cust_email1,cust_email2,contactor_name1,contactor_name2,invoice_no," +
				"invoice_addr,invoice_header,remark,status,max_car_tonnage,receiving_hours," +
				"create_flag,prio_type,cust_dept,cust_province,cust_city,cust_zone," +
				"cust_notecode,trade_flag,only_date_flag,collect_flag,container_material," +
				"warn_flag,divide_order,rgst_name,rgst_date,updt_name,updt_date,serialid," +
				"modiattr,moditime,modiopid) " +
				"select enterprise_no,owner_no,owner_cust_no,cust_no,cust_flag,cust_type,shipping_method," +
				"box_deliver,cust_name,cust_alias,cust_address,cust_postcode,delivery_address," +
				"cust_phone1,cust_phone2,cust_fax1,cust_fax2,cust_email1,cust_email2," +
				"contactor_name1,contactor_name2,invoice_no,invoice_addr,invoice_header," +
				"remark,status,max_car_tonnage,receiving_hours,create_flag,prio_type," +
				"cust_dept,cust_province,cust_city,cust_zone,cust_notecode,trade_flag," +
				"only_date_flag,collect_flag,container_material,warn_flag,divide_order," +
				"rgst_name,rgst_date,updt_name,updt_date,SEQ_BDEF_DEFCUST_LOG.nextval," +
				"'U',sysdate,'"+workNo+"' " +
				"from bdef_defcust " +
				"where cust_no='"+bd.getId().getCustNo()+"' " +
				"and enterprise_no='"+bd.getId().getEnterpriseNo()+"' ";
		genDao.updateBySql(sql);
		 return new MsgRes(true,TipUtil.getTipsByKey("E21302"),null);//保存成功！
	}
	
	@Override
	public List<ComboxBo> getBdef_DefCustComboList(String enterpriseNo,String workerOwner,
			String strQuery,
			String pageSql)throws Exception {
		String sql="select distinct t1.cust_no value,t1.cust_name text,'['|| ltrim(t1.cust_no)||']'||t1.cust_name dropValue  " +
				"from bdef_defcust t1 " +
				"where 1=1 and enterprise_no='"+enterpriseNo+"'" +
				"  and t1.status='1' ";		
		//货主权限
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and t1.owner_no in("+workerOwner+") ";
		}else{
			sql=sql+" and 1=2";
		}
		//货主过滤等条件
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ft=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ft;
		}
		//货主编码、助记码、名称
		if(pageSql!=null && pageSql!=""){
		    sql+="and (t1.cust_no like '%"+pageSql+"%' or t1.CUST_NOTECODE like '%"+pageSql+"%' or t1.cust_name like '%"+pageSql+"%') ";
		}
		
		sql=sql+" order by t1.cust_no ";
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	@Override
	public List<ComboxBo> getOwnerComboList(String enterpriseNo,String pageSql)throws Exception{
		String sql="select t1.owner_no value,t1.owner_name text,'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue from bdef_defowner t1 where enterprise_no='"+enterpriseNo+"' ";		
		if(pageSql!=null && pageSql!=""){
		    sql+="and t1.owner_no like '%"+pageSql+"%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	@Override
	public List<ComboxBo> getCarCombo(String enterpriseNo,String warehouseNo,String pageSql) throws Exception {
		String sql="select t1.car_no value,t1.car_name text," +
				"'['|| ltrim(t1.car_no)||']'||t1.car_name dropValue " +
				" from bdef_defcar t1 where t1.warehouse_no='"+warehouseNo+"' " +
				" and enterprise_no='"+enterpriseNo+"' ";		
		if(pageSql!=null && pageSql!=""){
		    sql+="and t1.car_no like '%"+pageSql+"%'";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	@Override
	public List<String> getCustName(String enterpriseNo,String custNo) throws Exception {
		String sql="select a.cust_name from bdef_defcust a where a.cust_no='"+custNo+"' " +
				"and enterprise_no='"+enterpriseNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		return list;
	}
	@Override
	public String checkCustNo(String enterpriseNo,String custNo) throws Exception {
		String sql="select a.cust_no from bdef_defcust a where a.cust_no='"+custNo+"' " +
				   "and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//货主客户代码校验
	@Override
	public String checkOwnerCustNo(String enterpriseNo, String strOwnerNo,
			String sttrOwnerCustNo) throws Exception {
		String sql="select a.owner_cust_no from bdef_defcust a where a.owner_cust_no='"+sttrOwnerCustNo+"' " +
				   "and a.enterprise_no='"+enterpriseNo+"' "+
				   "and a.owner_no='"+strOwnerNo+"' ";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	//客户编码模糊查询下拉
	@Override
	public List<ComboxBo> getCustInfo(String strEnterpriseNo,String strOwnerNo,
			 String str,
			String strQuery) throws Exception {
		String sql = "select distinct a.cust_no value,a.cust_no text,"
				+"'['|| a.cust_no || ']'||a.cust_name dropValue "
					+ "from bdef_defcust a "
					+ " where a.enterprise_no='" + strEnterpriseNo + "' "
					//+ " and a.warehouse_No='" + strWarehouseNo + "' "
					+ " and a.owner_no in(" + strOwnerNo + ") ";
					
		if (strQuery != null && !strQuery.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + 
					" and (a.cust_no like '%"+str+"%' " +
					"or a.owner_cust_no like '%"+str+"%' " +
							"or a.cust_name like '%"+str+"%' " +
							"or a.cust_notecode like '%"+str+"%' )";;
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
}
