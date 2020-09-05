package com.sealinkin.idata.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_CheckPalModel;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;

public interface Iidata_MovieTrolleyCheckService {

	/**
	 * 填充货主下拉
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param strWorkerOwner
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> queryOwnerCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner)throws Exception;
	/**
	 * 填充供应商下拉
	 * @param enterpriseNo
	 * @param workerOwner
	 * @param warehouseNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getSupplierNo(String enterpriseNo,String workerOwner, String warehouseNo,String str)throws Exception;
	/**
	 * 填充助记码下拉
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param ownerNo
	 * @param strWheresql
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getArticleIdentifierList(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str)throws Exception;
	/**
	 * 填充采购单号下拉
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param ownerNo
	 * @param strWheresql
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getPoNoList(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str)throws Exception;
	/**
	 * 填充助记码/条码
	 * @param enterpriseNo
	 * @param warehouseNo
	 * @param ownerNo
	 * @param strWheresql
	 * @param str
	 * @return
	 * @throws Exception
	 */
	
	public List<ComboxBo> getIdentifierOrBarcode1List(String enterpriseNo,String warehouseNo,String ownerNo,String strWheresql,String str)throws Exception;
	/**
	 * 获取单据列表
	 * @param strEnterpriseNo
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	 
	public ExtListDataBo<Idata_ImportMModel> getPoNoAndSImportNoList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String strWheresql,String str,PageBo pageBo)throws Exception;

	/**
	 * 获取采购单明细
	 * @param strEnterpriseNo
	 * @param wheresql
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	 
	public ExtListDataBo<Idata_ImportDModel> getImportDList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;
	/**
	 * 获取验收板明细
	 * @param strEnterpriseNo
	 * @param wheresql
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	 
	public ExtListDataBo<Idata_CheckPalModel> getCheckPalList(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,PageBo pageBo)throws Exception;

	/**
     * 保存
     * @param strJsonMaster
     * @param strJsonDetail1
     * @return
     * @throws Exception
     */
	public MsgRes save(String strJsonMaster, String strJsonDetail1) throws Exception;
    /**
     * 客户与电子标签储位校验
     * @param strEnterpriseNo
     * @param warehouseNo
     * @param poNo
     * @return
     * @throws Exception
     */
	public MsgRes checkDpsCellNo(String strEnterpriseNo,String warehouseNo,String importNo,String articleNo)throws Exception;

}
