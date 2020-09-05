package com.sealinkin.idata.service;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.idata.model.Idata_ImportMModel;
import com.sealinkin.idata.po.Idata_ImportTmp;

public interface Iidata_ImPort {
	
	/**
	 * 进货通知单列
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Idata_ImportMModel> getImPort_MList(
			String strEnterpriseNo,
			String warehouseNo,String workerOwner,
			String queryStr,PageBo pageBo)throws Exception;
	
	/**
	 * 进货通知单明细
	 * @param str
	 * @param pageBo
	 * @return
	 */
	public ExtListDataBo<Idata_ImportDModel> getImPort_DList(String strEnterpriseNo,String wheresql,PageBo pageBo)throws Exception;
	//获取直通配量
	public ExtListDataBo<Idata_ImportDModel> getImPortAllot(String strEnterpriseNo,String wheresql,String articleNo,PageBo pageBo)throws Exception;

	//根据进货单号获得验收量   8-17
	public MsgRes getCheckNumber(String strEnterpriseNo, String strWareNo , String importNo)throws Exception;
	
	//获取进货明细
	public ExtListDataBo<Idata_ImportDModel> getImPortTth_DList(String strEnterpriseNo,String wheresql,PageBo pageBo)throws Exception;

	public MsgRes saveImPort(String jsonMaster, String jsonDetail)throws Exception;
	
	public MsgRes saveImPort2(String jsonMaster,String jsonDetail,String jsonDetail2)throws Exception;
	//天天惠
	public MsgRes saveImPortTth2(String jsonMaster,String jsonDetail,String jsonDetail2,String DockNo)throws Exception;
	
	//天天惠保存分播明细
	public MsgRes saveImPortAllotTth(String importNo,String jsonDetail2)throws Exception;
	
	//集单
	public MsgRes tscidataOrderTime(String importNo,String jsonMaster)throws Exception;
		
	public void deleteImPort(String strEnterpriseNo,String importNo)throws Exception;
	
	public List<Idata_ImportDModel> getImportArticle(String strEnterpriseNo,String articleNo)throws Exception;
	
	public String checkPoNo(String strEnterpriseNo,String warehouseNo,String poNo)throws Exception;
	
	public void updatePrinter(String strEnterpriseNo,String importNo,String importType,String workerNo)throws Exception;
	
	// 上传Excel导入数据库
	public MsgRes upLoad(String importType,File file,String strWarehouseNo,String strEnterpriseNo, String string)throws Exception;
	
	//Excel数据转List(存储)
	public List<Idata_ImportTmp> changeexcelBeanIS(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception;
	
	//Excel数据转List(直通)
	public List<Idata_ImportTmp> changeexcelBeanID(String fileName,String strWarehouseNo,String strEnterpriseNo)throws Exception;
		
	// 解析Excel
	@SuppressWarnings("rawtypes")
	public List<List> impExcelIS(String execelFile)throws Exception;
	
	// 解析Excel
	@SuppressWarnings("rawtypes")
	public List<List> impExcelID(String execelFile)throws Exception;
	//取消订单
	public MsgRes closeOrder(String strEnterpriseNo,String warehouseNo, String workerNo, String importNo, String ownerNo)throws Exception;

	//集单打印
	public MsgRes tscSetAndPrint(String currEnterpriseNo, String warehouseNo,
			String workerNo, String workSpaceNo, String importNo,
			String printFlag)throws Exception;
    
	/**差异确认
	 * huangb 20160721
	 */
	public MsgRes tscDiffConfirm(String strEnterpriseNo, String strWarehouseNo,
			String strOwnerNo, String strImportNo, String strNewPoNo,
			String strUserId) throws Exception;


}
