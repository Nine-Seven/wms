package com.sealinkin.odata.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ArticlePackingModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.idata.model.Idata_ImportDModel;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.po.Odata_ExpTmp;


public interface IOdata_ExpService {
	
	/**
	 * 出货手建单M
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_ExpMModel> getExp_MList(String currEnterpriseNo,
			String warehouseNo,String workerOwner, String queryStr,PageBo pageBo,
			String strExpNothy,String expNo,String poNo,
			String strDeliverNo,
			String strShopNo,
			String strSkucount,
			String strCustExpNo,
			String strSendName,
			String strReceiveName,
			String strTakeName,
			String strErpoperateDate1,
			String strErpoperateDate2,
			String strCustsendDate1,
			String strCustsendDate2,
			String strLastCustsendDate1,
			String strLastCustsendDate2)throws Exception;
	
	/**
	 * 出货手建单D
	 * @param str
	 * @param pageBo
	 * @return
	 * @throws Exception
	 */
	public ExtListDataBo<Odata_ExpDModel> getExp_DList(String currEnterpriseNo,String wheresql,PageBo pageBo)throws Exception;
	
	public MsgRes saveExp(String expM,String expD)throws Exception;
	
	public MsgRes changeExp(String expM,String expD)throws Exception;
	
	public void deleteExp(String currEnterpriseNo,String expNo)throws Exception;
	//回写进货单头档品项数
	public MsgRes updateExpM(String strEnterpriseNo,String strWarehouseNo,String strOwner,String strExpNo)throws Exception;
	/**
	 * 校验出货单是否定过位
	 * @param file
	 * @return 
	 * @throws Exception
	 */
	public MsgRes editExp(String currEnterpriseNo,String expNo)throws Exception;
	
	
	public String checkPoNo(String currEnterpriseNo,String warehouseNo,String poNo)throws Exception;

	public List<Bdef_ArticlePackingModel> getPackingUnit(String currEnterpriseNo,String articleNo,
			String packingQty, String type) throws Exception;
	public List<ComboxBo> getExp_MCombo(String currEnterpriseNo,String warehouseNo,String pageSql)throws Exception;
	
	/**
	 * 选择客户时加载数据
	 */
	public List<Odata_ExpMModel> queryCust(String currEnterpriseNo,String strCustNo) throws Exception;

/*	public List<ComboxBo> getDefCustComboList(String workerOwner, String pageSql);
*/	
	/**
	 * 上传Excel导入数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public MsgRes upLoad(File file,String strWarehouseNo,String strCurrEnterpriseNo,String strWorkerNo)throws Exception;
	
	/**
	 * Excel数据转List
	 * @return
	 * @throws Exception
	 */
	public List<Odata_ExpTmp> changeexcelBean(String fileName,String strWarehouseNo,String strCurrEnterpriseNo)throws Exception;
	
	/**
	 * 解析Excel
	 * @param execelFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<List> impExcel(String execelFile)throws Exception;

	//关单
	public MsgRes closeOrder(String currEnterpriseNo,String warehouseNo, String workerNo, String expNo, String flag)throws Exception;

	//取消订单			7-12
	public MsgRes cancelOrder(String currEnterpriseNo,String warehouseNo, String workerNo, String expNo, String flag)throws Exception;
	
	public List<Idata_ImportDModel> getArticle(String articleNo,String currEnterpriseNo)throws Exception;

	//打印拣货单
	public MsgRes tscPrintPickingNo(String currEnterpriseNo, String warehouseNo,
			String workSpaceNo, String workerNo,
			String expNo,String type,String ownerNo,String strQuery) throws Exception;
	//校验该货主是否为大货位管理
	public MsgRes checkCell(String enterpriseNo ,String ownerNo)throws Exception;
	/**
	 * 检查是否有商品缺量
	 * @param string 
	 * @return
	 * @throws Exception
	 */
	public MsgRes checkNoEnoght(String enterpriseNo,String strWarehouseNo,String strQuery)throws Exception;
	//获得出货申请单商品详细信息列表
	public ExtListDataBo<Odata_ExpDModel> getOrder_GoodsDetailList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			PageBo poPageBo)throws Exception;
	//写出货单状态跟踪
	public MsgRes tscExpStatus(String enterpriseNo,String strWarehouseNo,
			String workerNo,String expNo)throws Exception;

	//获得出货查询所有出货申请单列表
	public ExtListDataBo<Odata_ExpDModel> getAllOrderList(String strEnterpriseNo,String strWarehouseNo,
			String strWorkerOwner,
			String strQuery,
			PageBo poPageBo)throws Exception;
	
	//获取单据类型下拉（公用）
	public List<ComboxBo> queryExpTypeCombo(String enterpriseNo,String flag)throws Exception;
}
















