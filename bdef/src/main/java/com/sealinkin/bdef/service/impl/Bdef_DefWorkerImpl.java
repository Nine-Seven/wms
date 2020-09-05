package com.sealinkin.bdef.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.bdef.model.Bdef_DeflocModel;
import com.sealinkin.bdef.po.Bdef_DefWorker;
import com.sealinkin.bdef.service.IBdef_DefWorkerService;
import com.sealinkin.bset.model.Bset_Role_ListModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.util.algorithm.MysqlPasswd;

@SuppressWarnings({"rawtypes","unchecked"})
public class Bdef_DefWorkerImpl implements IBdef_DefWorkerService{
	
	private IGenericManager genDao;
    
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	
	@Override
	public MsgRes saveBdef_DefWorker(String str) throws Exception {
		Bdef_DefWorker poWorker=(Bdef_DefWorker) JSONObject.toBean(JSONObject.fromObject(str),Bdef_DefWorker.class);
		String strSql="select * from bdef_defworker where WORKER_NO='"+poWorker.getId().getWorkerNo()+"'";
		
		List<Bdef_DefWorker> list = genDao.getListByNativeSql(strSql, Bdef_DefWorker.class);
		if(list.size()>0){
			//if(!list.get(0).getPwd().equals(poWorker.getPwd())){
				poWorker.setPwd(MysqlPasswd.MySQLPassword(poWorker.getPwd()));
				System.out.println("测试:"+list.get(0).getRgstDate());
				poWorker.setRgstDate(list.get(0).getRgstDate());
				poWorker.setUpdtDate(new Date());
				this.genDao.update(poWorker);//(poWorker);
			//}
		}else{
			poWorker.setPwd(MysqlPasswd.MySQLPassword(poWorker.getPwd()));
			poWorker.setRgstDate(new Date());
			//poWorker.setRgstName(rgstName)
			//poWorker.setUpdtDate(new Date());
			this.genDao.save(poWorker);
		}
		//this.genDao.saveOrUpdateObj(poWorker);
		return new MsgRes(true,"savesuccess",null);
	}

	@Override
	public ExtListDataBo<Bdef_DefWorkerModel> getBdef_DefWorker(String str,
			PageBo pageBo) {
		String strSql="select a.* from bdef_defworker a where 1=1 ";
		String strTotsql="select count(*) from bdef_defworker a where 1=1 ";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			strSql=strSql+ws;
			strTotsql=strTotsql+ws;
		}
		
		List<Bdef_DefWorkerModel> list = genDao.getListByNativeSql(strSql, Bdef_DefWorkerModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Bdef_DefWorkerModel> extListBo= new ExtListDataBo<Bdef_DefWorkerModel>(list, count);
        return extListBo;
	}
	
	/**
	 * 获取员工下拉
	 */
	@Override
	public List<ComboxBo> query_WorkerCombo(String enterpriseNo,String strWarehouseNo, String strOwnerNo, String strWheresql)
			throws Exception 
	{
		String strSql1="select distinct a.worker_no value,a.worker_name text," +
				"'[' || ltrim(a.worker_no) || ']' || a.worker_name dropValue " +
				"from bdef_defworker a, bset_worker_loc b, bset_worker_owner c " +
				"where a.worker_no = b.worker_no " +
				"and a.enterprise_no = b.enterprise_no "+
				"and a.worker_no = c.worker_no " +				
				"and a.enterprise_no = c.enterprise_no "+
				"and b.warehouse_no = '"+strWarehouseNo+"' " +
				"and a.enterprise_no= '"+enterpriseNo+"' "+
				"and a.worker_no like '%"+strWheresql+"%'";
		
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql1=strSql1+" and c.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql1=strSql1+" and 1=2";
		}	
		
		String strSql2="select distinct a.worker_no value,a.worker_name text," +
				       " '[' || ltrim(a.worker_no) || ']' || a.worker_name dropValue " +
				       "  from bdef_defworker a, bset_worker_loc b " +
				       " where a.worker_no = b.worker_no " +
				       "   and a.enterprise_no = b.enterprise_no " +
				       "   and b.warehouse_no = '"+strWarehouseNo+"' " +
					   "   and a.enterprise_no= '"+enterpriseNo+"' " +
					   "   and a.authority_type='1' "+
					   "   and a.worker_no like '%"+strWheresql+"%'";
		
		String strSql=" select distinct value,text,dropValue from(" +strSql1+ " union " + strSql2+") order by value ";
		
		List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	
	@Override
	public List<ComboxBo> getBdef_DefWorker() {
		String sql="select a.worker_no value,a.worker_name text,'['|| ltrim(a.worker_no)||']'||a.worker_name dropValue  from bdef_defworker a where 1=1 " ;		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;

	}
	
	@Override
	public ExtListDataBo<Bdef_DefWorkerModel> getWorkerList(String strEnterpriseNo,
			String strQuery,
			String strWarehouseNo,
			PageBo pageBo,
			String flag	//分页标识1：不分页
			) throws Exception {
		String sql="select " +
						"case when b.worker_no is null then 'false' else 'true' end flag," +
						"a.*," +
						"f_get_fieldtext('N','DEF_STATUS',a.status)statusText, " +
						"f_get_fieldtext('BDEF_DEFWORKER','AUTHORITY_TYPE',a.authority_type)authorityTypeText " +
					"from " +
						"bdef_defworker a," +
						"bset_worker_loc b " +
					"where " +
						"a.enterprise_no = b.enterprise_no(+) "+		//7-7修改
						"and a.WORKER_NO=b.WORKER_No(+) " +
						"and b.warehouse_no(+)='"+strWarehouseNo+"'" +
						"and a.enterprise_no='"+strEnterpriseNo+"' ";
		if(strQuery != null && !strQuery.equals(""))
		{
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql+ws;
		}
		
		sql=sql+" order by a.worker_no ";
		
		List<Bdef_DefWorkerModel> list = null;
		ExtListDataBo<Bdef_DefWorkerModel> extListBo=null;
		if(!StringUtil.isEmpty(flag) && flag.equals("1"))
		{
			list = genDao.getListByNativeSql(sql,Bdef_DefWorkerModel.class);	
		}else
		{
			list = genDao.getListByNativeSql(sql,Bdef_DefWorkerModel.class,pageBo.getStart(), pageBo.getPagesize());
		}
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
		extListBo= new ExtListDataBo<Bdef_DefWorkerModel>(list, count);
        return extListBo;
	}
	
	@Override
	public ExtListDataBo<Bdef_DeflocModel> getWarehouseList(String str,
			PageBo pageBo) throws Exception {
		String sql="select a.warehouse_no,a.warehouse_name from bdef_defloc a " +
				"where a.enterprise_no='"+str+"' " +
				"order by a.warehouse_no";
		List<Bdef_DeflocModel> list = null;
		ExtListDataBo<Bdef_DeflocModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bdef_DeflocModel.class,pageBo.getStart(), 1000);
		extListBo= new ExtListDataBo<Bdef_DeflocModel>(list, 0);
        return extListBo;
	}
	//通过用户编号获取仓别
	@Override
	public ExtListDataBo<Bdef_DeflocModel> getWarehouseListByWorkerNo(String str,String strWheresql,
			PageBo pageBo) throws Exception {
		String sql="select case when b.warehouse_no is null then 'false' else 'true' end flag," +
				"a.warehouse_no,a.warehouse_name " +
				"from bdef_defloc a,bset_worker_loc b  " +
				"where a.enterprise_no='"+str+"' " +
				"and a.warehouse_no=b.warehouse_no(+) " +
				"and b.worker_no(+)='"+strWheresql+"' " +
				"order by a.warehouse_no";
		List<Bdef_DeflocModel> list = null;
		list = genDao.getListByNativeSql(sql,Bdef_DeflocModel.class,pageBo.getStart(), 1000);
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+sql+")");
		ExtListDataBo<Bdef_DeflocModel> extListBo= new ExtListDataBo<Bdef_DeflocModel>(list, count);
        return extListBo;
	}
	
	@Override
	public ExtListDataBo<Bdef_DefOwnerModel> getOwnerList(String str,PageBo pageBo) throws Exception {
		String sql="select owner_no,owner_name from bdef_defowner " +
				"where enterprise_no='"+str+"' ";
		List<Bdef_DefOwnerModel> list = null;
		ExtListDataBo<Bdef_DefOwnerModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bdef_DefOwnerModel.class,pageBo.getStart(), 1000);
		extListBo= new ExtListDataBo<Bdef_DefOwnerModel>(list, 0);
        return extListBo;
	}
	
	//通过用户编号获取货主列表
	@Override
	public ExtListDataBo<Bdef_DefOwnerModel> getOwnerListByWorkerNo(String str,String strWheresql,PageBo pageBo)throws Exception{
		String strSql="select case when b.owner_no is null then 'false' else 'true' end flag," +
				"a.owner_no,a.owner_name " +
				"from bdef_defowner a,bset_worker_owner b  " +
				"where a.enterprise_no='"+str+"' " +
				"and a.owner_no=b.owner_no(+) " +
				"and b.worker_no(+)='"+strWheresql+"' " +
				"order by a.owner_no";
		List<Bdef_DefOwnerModel> list = genDao.getListByNativeSql(
				strSql,Bdef_DefOwnerModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql("select count(*) from ("+strSql+")");
		ExtListDataBo<Bdef_DefOwnerModel> extListBo= new ExtListDataBo<Bdef_DefOwnerModel>(list, count);
        return extListBo;
	}
	
	@Override
	public ExtListDataBo<Bdef_DefWorkerModel> getWorkerList2(String strEnterpriseNo,String str,
			PageBo pageBo) throws Exception {
		String sql="select case when b.worker_no is null then 'false' else 'true' end flag," +
				"a.worker_no,a.worker_name " +
				"from bdef_defworker a,bset_worker_owner b " +
				"where a.WORKER_NO=b.WORKER_No(+) " +
				"and a.enterprise_no = b.enterprise_no(+) "+
				"and a.enterprise_no='"+strEnterpriseNo+"' " +
				"and b.owner_no(+)='"+str+"'";
		List<Bdef_DefWorkerModel> list = null;
		ExtListDataBo<Bdef_DefWorkerModel> extListBo=null;
		list = genDao.getListByNativeSql(sql,Bdef_DefWorkerModel.class,pageBo.getStart(), 1000);
		extListBo= new ExtListDataBo<Bdef_DefWorkerModel>(list, 0);
		return extListBo;
	}

}
