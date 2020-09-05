package com.sealinkin.mdata.service;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.mdata.model.Mdata_PlanMTmpModel;
import com.sealinkin.mdata.po.Mdata_PlanTmp;
import com.sealinkin.stock.model.Stock_ContentModel;

public interface IMdata_PlanMService {

	ExtListDataBo<Stock_ContentModel> getCset_CellArticleList(
			String str, Boolean str1, Integer start, Integer pageSize, String warehouseNo,String enterpriseNo);

	ExtListDataBo<Stock_ContentModel> getStockContentList(String str, String ownerArticleNo,
			String barcode, Integer start, Integer pageSize, Integer requestFlag,String enterpriseNo, String oldOdata);
	/*
	 * 获得移库导入失败信息
	 * hkl
	 */
	ExtListDataBo<Mdata_PlanMTmpModel> movefailList( String enterpriseNo,String warehouseNo);
	
	
	List<ComboxBo> getOdata_GetCombo(String enterpriseNo,String workerOwner,String warehouseNo, String flag,
			String str, Integer start, Integer pageSize);

	List<ComboxBo> getMdata_GetCombo(String enterpriseNo,String workerOwner, String warehouseNo,
			String flag, String str2, String strWheresql, int start, int pageSize);

	MsgRes tscSend(String enterpriseNo,String warehouseNo, String workerNo, String str, String flag,String strDockNo) throws Exception;
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public MsgRes cfgUpLoad(File file,String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo)throws Exception;
	
	/**
	 * Excel数据转List
	 * @return
	 * @throws Exception
	 */
	public List<Mdata_PlanTmp> changeexcelBean(String fileName,String strWarehouseNo,String strCurrEnterpriseNo)throws Exception;
	
	/**
	 * 解析Excel
	 * @param execelFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;
	// 储位下拉	
	public abstract List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String warehouseNo,
			String str, String strWheresql, int i, int j);
}
