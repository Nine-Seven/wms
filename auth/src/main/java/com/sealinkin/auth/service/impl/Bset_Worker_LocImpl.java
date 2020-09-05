package com.sealinkin.auth.service.impl;

import java.util.Collection;
import java.util.List;

import com.sealinkin.auth.service.IBset_Worker_Loc;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.bset.po.Bset_WorkerOwner;
import com.sealinkin.bset.po.Bset_Worker_Loc;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.MyDateMorpher;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;


@SuppressWarnings({"rawtypes","unchecked"})
public class Bset_Worker_LocImpl implements IBset_Worker_Loc{
	private IGenericManager genDao;
    
	public IGenericManager getGenDao() {
		return genDao;
	}
	
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	@Override
	public List<ComboxBo> getBset_Worker_LocCombo(String workerNo,String enterpriseNo,
			int start,int pagesize) {
		String strSql="select a.warehouse_no value,b.warehouse_name text," +
				"'['|| ltrim(a.warehouse_no)||']'||b.warehouse_name dropValue " +
				"from bset_worker_loc a,bdef_defloc b " +
				"where a.warehouse_no=b.warehouse_no " +
				"and a.enterprise_no=b.enterprise_no " +
				"and a.worker_no='"+workerNo+"' and a.enterprise_no='"+enterpriseNo+"' ";
		List<ComboxBo> list=genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	}

	@Override
	public boolean saveBset_Worker_Loc(String str) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Bset_Worker_Loc> bb=JSONArray.toCollection(JSONArray.fromObject(str),Bset_Worker_Loc.class);
		List<Bset_Worker_Loc> bwl=(List)bb;
		
		String deleteSql="delete from bset_worker_loc a " +
				         "where a.enterprise_no='"+bwl.get(0).getId().getEnterpriseNo()+"' " +
				         "  and a.warehouse_no='"+bwl.get(0).getId().getWarehouseNo()+"' ";
		genDao.updateBySql(deleteSql);

		this.genDao.flush();
		this.genDao.saveList(bwl);
		return true;
	}
	
	@Override
	public boolean saveBset_Loc_Worker(String str) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Bset_Worker_Loc> bb=JSONArray.toCollection(JSONArray.fromObject(str),Bset_Worker_Loc.class);
		List<Bset_Worker_Loc> bwl=(List)bb;
		
		String deleteSql="delete from bset_worker_loc a " +
				         "where a.enterprise_no='"+bwl.get(0).getId().getEnterpriseNo()+"' " +
				         "  and a.worker_no='"+bwl.get(0).getId().getWorkerNo()+"' ";
		genDao.updateBySql(deleteSql);

		this.genDao.flush();
		this.genDao.saveList(bwl);
		return true;
	}
	
	public void deleteBset_Worker_Loc(String enterpriseNo,String warehouseNo){
		String delsql="delete from bset_worker_loc " +
				      "where warehouse_no='"+warehouseNo+"' " +
				      "and enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
	}
	
	public void deleteBset_Loc_Worker(String enterpriseNo,String workerNo){
		String delsql="delete from bset_worker_loc " +
				      "where worker_no='"+workerNo+"' " +
				      "and enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
	}

	@Override
	public boolean saveBset_Worker_Owner(String str) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Bset_WorkerOwner> bb=JSONArray.toCollection(JSONArray.fromObject(str),Bset_WorkerOwner.class);
		List<Bset_WorkerOwner> bwo=(List)bb;
		
		String deleteSql="delete from bset_worker_owner a " +
		         "where a.enterprise_no='"+bwo.get(0).getId().getEnterpriseNo()+"' " +
		         "  and a.owner_no='"+bwo.get(0).getId().getOwnerNo()+"' ";
		genDao.updateBySql(deleteSql);
		this.genDao.flush();
		this.genDao.saveList(bwo);
		return true;
	}
	
	@Override
	public boolean saveBset_Owner_Worker(String str) throws Exception {
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		Collection<Bset_WorkerOwner> bb=JSONArray.toCollection(JSONArray.fromObject(str),Bset_WorkerOwner.class);
		List<Bset_WorkerOwner> bwo=(List)bb;
		
		String deleteSql="delete from bset_worker_owner a " +
		         "where a.enterprise_no='"+bwo.get(0).getId().getEnterpriseNo()+"' " +
		         "  and a.worker_no='"+bwo.get(0).getId().getWorkerNo()+"' ";
		genDao.updateBySql(deleteSql);
		this.genDao.flush();
		this.genDao.saveList(bwo);
		return true;
	}

	@Override
	public void deleteBset_Worker_Owner(String enterpriseNo,String ownerNo) throws Exception {
		String delsql="delete from bset_worker_owner " +
				      "where owner_no='"+ownerNo+"' " +
				      "and enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
	}
	
	@Override
	public void deleteBset_Owner_Worker(String enterpriseNo,String workerNo) throws Exception {
		String delsql="delete from bset_worker_owner " +
				      "where worker_no='"+workerNo+"' " +
				      "and enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
	}

	@Override
	public List<Bdef_DefOwnerModel> queryOwnerCombo(String enterpriseNo) throws Exception {
		String strSql="select owner_no,owner_name from bdef_defowner " +
				      "where enterprise_no='"+enterpriseNo+"' " ;
		List<Bdef_DefOwnerModel> list = genDao.getListByNativeSql(strSql,Bdef_DefOwnerModel.class,0, 1000);
        return list;
	}
}

