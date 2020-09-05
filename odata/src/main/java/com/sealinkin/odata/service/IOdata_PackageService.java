package com.sealinkin.odata.service;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.odata.model.Odata_PackageDModel;
import com.sealinkin.odata.model.Odata_PackageMModel;
import com.sealinkin.odata.po.Odata_PackageTmp;

/**
 * 2016.7.15
 * @author czh
 * 模块名称:集货作业
 * 模块编码:3931
 */
@SuppressWarnings({"rawtypes"})
public interface IOdata_PackageService {

	/**
	 * 获取包裹单头档
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_PackageMModel> queryPackageM(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strQuery,PageBo poPageBo)throws Exception;
	
	/**
	 * 获取包裹单明细
	 * @param strPackageNo
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_PackageDModel> queryPackageD(String enterpriseNo,String strWarehouseNo,String strOwnerNo,String strPackageNo,String strQuery,PageBo poPageBo)throws Exception;
	/**
	 * 集货作业货主下拉
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getOwnerCombo(String enterpriseNo,String warehouseNo, String strWorkerOwner)throws Exception;
	/**
	 * 集货作业状态下拉
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getStatusList(String strEnterpriseNo,String warehouseNo,String strOwnerNo,String strQuery)throws Exception;
	/**
	 * 集货作业提单号模糊查询
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public List<ComboxBo> getPackageInfo(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo,String str,String strQuery)throws Exception;
	/**
	 * 集货作业出库、入库
	 * @param strWarehouseNo
	 * @param strOwnerNo
	 * @return
	 * @throws Exception
	 */
	public MsgRes PackageIntoOrOutStock(String enterpriseNo, String warehouseNo,
			String ownerNo, String poNo, String sourceExpNo,
			String operateWorker, String operateType) throws Exception;
	
	public MsgRes printPackageInfo(String enterpriseNo, String warehouseNo,
			String reportType, String sourceNo, String dockNo,
			String paperType, String userId) throws Exception;
	
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public MsgRes upLoad(File file,String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo)throws Exception;	
		
	//Excel数据转List
	public List<Odata_PackageTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception;
		
	// 解析Excel
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;
}
