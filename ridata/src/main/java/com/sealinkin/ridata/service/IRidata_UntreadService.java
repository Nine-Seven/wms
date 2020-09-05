package com.sealinkin.ridata.service;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.po.Idata_ImportTmp;
import com.sealinkin.ridata.model.Ridata_UntreadDModel;
import com.sealinkin.ridata.model.Ridata_UntreadMModel;
import com.sealinkin.ridata.po.Ridata_UntreadTmp;

public interface IRidata_UntreadService {

	/**
	 * 返配手建单列
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_UntreadMModel> getRidata_UntreadMList(
			String enterpriseNo,
			String warehouseNo,String workerOwner,
			String queryStr,PageBo pageBo)throws Exception;
	
	/**
	 * 返配手建单明细
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Ridata_UntreadDModel> getRidata_UntreadDList(String enterpriseNo,String wheresql,PageBo pageBo)throws Exception;
	
	//根据返配单号获得验收量   8-17
	public MsgRes getCheckNumber(String strEnterpriseNo, String strWareNo , String untreadNo)throws Exception;
	
	public MsgRes saveRidata_Untread(String jsonMaster,String jsonDetail,String warehouseNo,String enterpriseNo)throws Exception;
	
	public void deleteUntread(String untreadNo,String enterpriseNo)throws Exception;
	
	public List<Ridata_UntreadDModel> getUntreadArticle(String articleNo,String enterpriseNo)throws Exception;
	//取消订单			 
	public MsgRes cancelOrder(String currEnterpriseNo,String warehouseNo,String WorkerNo, String untreadNo)throws Exception;
	
	
	//获得返配汇总单号
	public List<ComboxBo> getUntreadNoList(
			String strOwnerNo,String warehouseNo,String pageSql,String enterpriseNo,String poType)throws Exception;
	
/*	public void updatePrinter(String untreadNo,String SUntreadNo,String workerNo,String enterpriseNo)throws Exception;
*/	//打印返配预计到货标签
	public MsgRes print(String enterpriseNo, String warehouseNo,String workStationNo, String rgstName, String poNo,String untreadNo) throws Exception;
	//打印返配预计验收单
    public MsgRes printCheckPlan(String enterpriseNo, String warehouseNo,String ownerNo,String strSourceNo, String rgstName, String strDockNo) throws Exception;

	public String checkUntreadNo(String enterpriseNo, String warehouseNo, String poNo)throws Exception;
	// 上传Excel导入数据库
	public MsgRes upLoad(File file,String strWarehouseNo,String strEnterpriseNo, String workNo,String strWorkSpaceNo)throws Exception;
		
	//Excel数据转List
	public List<Ridata_UntreadTmp> changeexcelBean(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception;
		
	// 解析Excel
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;
	//审空单
	public MsgRes tscEmptyList(String strEnterpriseNo,String strWarehouseNo,
				String wheresql)throws Exception;
}
