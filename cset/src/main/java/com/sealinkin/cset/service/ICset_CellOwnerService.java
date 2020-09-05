package com.sealinkin.cset.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_AreaBackupMModel;

public abstract interface ICset_CellOwnerService {
	
	/**
	 * 货主下拉
	 */
	public List<ComboxBo> getOwnerCombo(String enterpriseNo,String strOwnerNo)throws Exception;

	//获取货位列表，只取作业区（除异常区）并且不允许混货主的的货位
	public abstract ExtListDataBo<Cset_AreaBackupMModel> getCset_CellList(
			String enterpriseNo,
			String warehouseNo, String cellNo);
	
	// 货主货位关系列表
	public abstract ExtListDataBo<Cset_AreaBackupMModel> getCset_Cell_OwnerList(
			String enterpriseNo,
			String warehouseNo, String ownerNo,String cellNo,PageBo pageBo);
	//保存货主货位关系
	public abstract boolean saveCellOwner(String str);
	
	//deleteCellOwner移除货主货位关系
	public abstract boolean deleteCellOwner(String enterpriseNo,
			String warehouseNo, String ownerNo,String cellNo);
}