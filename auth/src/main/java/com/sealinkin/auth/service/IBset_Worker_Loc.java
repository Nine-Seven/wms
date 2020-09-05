package com.sealinkin.auth.service;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.comm.model.ComboxBo;

public interface IBset_Worker_Loc {
	public List<ComboxBo> getBset_Worker_LocCombo(
			String workerNo,String enterpriseNo,
			int start,
			int pagesize) ;
	
	public boolean saveBset_Worker_Loc(String str)throws Exception;
	
	public boolean saveBset_Loc_Worker(String str) throws Exception;
	
	public void deleteBset_Worker_Loc(String enterpriseNo,String warehouseNo)throws Exception;
	
	public void deleteBset_Loc_Worker(String enterpriseNo,String workerNo);
	
	public boolean saveBset_Worker_Owner(String str)throws Exception;
	
	public boolean saveBset_Owner_Worker(String str) throws Exception;
	
	public void deleteBset_Worker_Owner(String enterpriseNo,String ownerNo)throws Exception;
	
	public void deleteBset_Owner_Worker(String enterpriseNo,String workerNo) throws Exception;
	
	public List<Bdef_DefOwnerModel> queryOwnerCombo(String enterpriseNo)throws Exception;
	
}

