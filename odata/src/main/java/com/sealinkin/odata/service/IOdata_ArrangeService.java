package com.sealinkin.odata.service;

import java.util.Date;
import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.protocolExchange.odata.OdataSkuLabelCancelModel;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

public interface IOdata_ArrangeService {
	// 检查源容器
	public MsgRes ExistsSLabelNo(String strEnterpriseNo,String strWarehouseNo,String strSLabelNo)throws Exception;
	
	// 检查目的容器
	public MsgRes tscExistsDLabelNo(String strEnterpriseNo,String strWarehouseNo, String strSLabelNo,String strDLabelNo)throws Exception;
	
	//获取源容器商品信息
	public MsgRes GetSLabelNoArticle(String strEnterpriseNo,String strWarehouseNo,String strSLabelNo,String strBarcode)throws Exception;
	
	// 获取源容器商品信息
	public MsgRes tscArrange(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strSLabelNo,//源容器号
			String strDLabelNo,//目的容器号
			String strArticleNo,//商品编码
			Double nPackingQty,//商品包装
			Double nMoveQty,//移动数量 0：表示整箱移 
			String strBatchNo, //批次号
			String strQuality,//品质
			Date dtProduceDate,//生产日期
			Date dtExpireDate,//到期日期
			String strLotNo,//批号
			String strRsvBatch1, //预留批属性1
			String strRsvBatch2, //预留批属性2
			String strRsvBatch3, //预留批属性3
			String strRsvBatch4, //预留批属性4
			String strRsvBatch5, //预留批属性5
			String strRsvBatch6, //预留批属性6
			String strRsvBatch7, //预留批属性7
			String strRsvBatch8, //预留批属性8 
			String strUserId,//操作人员
			String strTerminalFlag//操作设备
			)throws Exception;
	
	// 获取标签号内部号
	public MsgRes GetLabelNoContainerNo(String strEnterpriseNo,String strWarehouseNo,String strLabelNo)throws Exception;
	
	//检查源容器是否已整理完毕
	public MsgRes isFinishComplete(String strEnterpriseNo,String strWarehouseNo,String strSLabelNo)throws Exception;	
	
	//整理确认-检查
	public MsgRes tscPOM_AerrangeConfirmScanLabel(String strEnterpriseNo,String strWarehouseNo,String strLabelNo,String strWorkerNo, String custNo)throws Exception;
	
	//整理确认
	public MsgRes tscPOM_AerrangeConfirm(String strEnterpriseNo,String strWarehouseNo,String strLabelNo,String strWorkerNo)throws Exception;
	
	
	//整箱转移
	public MsgRes tscOmArrangeBoxs(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strSLabelNo,//源容器号
			String strDLabelNo,//目的容器号
			String strArticleNo,//商品编码
			Double nPackingQty,//商品包装
			Double nMoveQty,//移动数量 0：表示整箱移 
			String strBatchNo, //批次号
			String strQuality,//品质
			Date dtProduceDate,//生产日期
			Date dtExpireDate,//到期日期
			String strLotNo,//批号
			String strRsvBatch1, //预留批属性1
			String strRsvBatch2, //预留批属性2
			String strRsvBatch3, //预留批属性3
			String strRsvBatch4, //预留批属性4
			String strRsvBatch5, //预留批属性5
			String strRsvBatch6, //预留批属性6
			String strRsvBatch7, //预留批属性7
			String strRsvBatch8, //预留批属性8 
			String strUserId,//操作人员
			String strTerminalFlag//操作设备
			)throws Exception;
	
	//检查标签是否可销毁
	public MsgRes tscCheckLableCancel(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo//源容器号			
			)throws Exception;
	
	//标签销毁
	public MsgRes tscLableCancel(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo,//源容器号			
			String strUserId//操作人员
			)throws Exception;
	
	//扫描SKU标签销毁商品
	public MsgRes tscScanSkuCancel(String strRecvData)throws Exception;
	
	//扫描SKU标签销毁商品不管生产日期
	public MsgRes tscScanSkuCancelWithoutDate(String strRecvData)throws Exception;
	
	
	//检查SKU标签是否可销毁
	public MsgRes tscCheckSkuLableCancel(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo//源容器号	
			
			)throws Exception;
	
	//SKU标签销毁
	public MsgRes tscSkuLableCancel(OdataSkuLabelCancelModel reqSkuLabelCancel
			)throws Exception;
	
	public List<Stock_LabelMModel> getOdata_CusLabel(String strEnterpriseNo,String strWarehouseNo,
			String strLabelNo,
			String strQuery,String strPrinterGroupNo);

	// 新取封箱标签
	public MsgRes tscGetNewLabel(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo//源容器号			
			,String strContainerType,String strWorkerNo)throws Exception;
	
	//扫描 条码
	public MsgRes tscCheckBarcode(String currEnterpriseNo, String warehouseNo,
			String str, String strSlabel, String strDlabel, Integer sacnNum,
			String strWorkerNo,String strFlag,String strPrinterGroupNo
			)  throws Exception ;
	//取标签的数量
	public MsgRes tscBoxQty(String currEnterpriseNo, String warehouseNo,
			String str)  throws Exception ;
	
	//修改复合单头档状态为10
	public MsgRes updateCheckM(String currEnterpriseNo, String warehouseNo,
			String str)  throws Exception ;

	public List<Stock_LabelDModel> getStockLabelD(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo,
			String strFlag//源容器号	
			)throws Exception ;
	
	// 出货整理打包》封箱
	public MsgRes tscArrangeCutbox(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strDLabelNo,
			String strPrinterGroupNo,
			String strUserId//操作人员
			)  throws Exception ;	
	
	// 出货整理打包》检查源容器
	public MsgRes CheckSLabelNo(String strEnterpriseNo,
			String strWarehouseNo,String strSLabelNo, String workerNo,String strQuery)throws Exception;
	// 出货整理打包》校验扫描台号
    public MsgRes checkDock(String strEnterpriseNo,String strWarehouseNo,String str)throws Exception;

	//获取未复合商品明细
	public ExtListDataBo<Odata_CheckLabelDModel> getUnCheckLabelD(
			String currEnterpriseNo, String warehouseNo, String str)throws Exception ;
	//校验码头
	public MsgRes CheckDock(String strRecvData) throws Exception;	

	//打印目的容器
	public MsgRes tscPrintDLabelNo(String strRecvData) throws Exception;

	public MsgRes tscPOM_AerrangeConfirmCheckCustNo(String strRecvData);

	//回单
	public MsgRes tscReceipt(String currEnterpriseNo, String warehouseNo,
			String strSlabel) throws Exception;
    //重扫
	public MsgRes tscAnewScan(String currEnterpriseNo, String warehouseNo,
			String strDlabel,String strWorkerNo) throws Exception;
	

	//容器整理取号
	public MsgRes GetContainerArrangeNO(String strEnterpriseNo,String strWarehouseNo,String strSLabelNo,
			String strmType,String strDockNo,String strWorkerNo)throws Exception;
	
    //RF 标签检查  Add by sunl 2016年2月25日
	public MsgRes tscCheckLabel(String strRecvData) throws Exception;

	//获取待整理确认的标签信息
	public MsgRes GetAerrangeConfirmWaitLabel(String strRecvData) throws Exception;
	//获取当前板标签下的箱标签个数
	public MsgRes GetAerrangeConfirmLabelBoxCount(String strRecvData) throws Exception;

	//整理确认 扫描板标签后，返回当前板标签下的波次号，配送对象，暂存区，总箱数
	public MsgRes tscGetPlateLabelInfo(String strRecvData) throws Exception;
	// 自动装并板+整理确认
	public MsgRes tscSaveMergePalAndAerrange(String strRecvData) throws Exception;
	// 获取待整理的数据
	public MsgRes GetWaitAerrangeInfo(String strRecvData) throws Exception;
    
	/** 保存标签重量信息
	 *  huangb 20160720
	 */
	public MsgRes tscSaveLabelWeigh(String strRecvData) throws Exception;
    
	/** 称重获取标签信息
	 *  huangb 20160720
	 */
    public MsgRes getLabelInfo(String strRecvData) throws Exception;
}
