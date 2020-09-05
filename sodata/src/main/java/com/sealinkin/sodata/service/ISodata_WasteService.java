package com.sealinkin.sodata.service;

import java.io.File;
import java.util.List;
import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.sodata.model.Sodata_WasteDModel;
import com.sealinkin.sodata.model.Sodata_WasteMModel;
import com.sealinkin.stock.model.Stock_ContentModel;

/**
 * 报损中心
 * @author hekangli
 *
 */
public interface ISodata_WasteService {
	
	/**
	 * 报损手建单M
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Sodata_WasteMModel> getWaste_MList(String currEnterpriseNo,
			String warehouseNo,String workerOwner, String queryStr,PageBo pageBo)throws Exception;
	
	/**
	 * 报损手建单D
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Sodata_WasteDModel> getWaste_DList(String currEnterpriseNo,String warehouseNo,String wheresql,PageBo pageBo)throws Exception;
	
	/**
	 * 报损手建单库存
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Stock_ContentModel> getSodataWasteList(String currEnterpriseNo,String warehouseNo,String wheresql,PageBo pageBo)throws Exception;
	
	//保存
	public MsgRes saveWaste(String wasteM,String wasteD)throws Exception;
	
	//7-14 添加   修改
	public MsgRes changeWaste(String wasteM,String wasteD)throws Exception;
	
	//7-14 删除
	public void deleteWaste(String currEnterpriseNo,String WasteNo)throws Exception;
	
	//校验单号
	public String checkWatesNo(String currEnterpriseNo,String warehouseNo,String poNo)throws Exception;

	public List<Bdef_ArticlePackingModel> getPackingUnit(String currEnterpriseNo,String articleNo,
			String packingQty, String type) throws Exception;
	//发单
	public MsgRes tscBillPrint(String currEnterpriseNo,String warehouseNo,
			    String workerNo, String strDockNo,
			    String wasteNo,String ownerNo,String type)throws Exception;

	public List<Idata_ImportDModel> getArticle(String articleNo,String currEnterpriseNo)throws Exception;
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public MsgRes tscUpLoad(File file,String strWarehouseNo,String strEnterpriseNo,String strWorkerNo)throws Exception;
	//获取系统参数判断WMS手建单是否要ERP审核
	public String getSodataAuditFlag(String strEnterpriseNo,String strWarehouseNo,String strOwnerNo) throws Exception;
	
	public MsgRes closeOrder(String strEnterpriseNo,String warehouseNo, String workerNo,
			String wasteNo, String ownerNo) throws Exception;

}
