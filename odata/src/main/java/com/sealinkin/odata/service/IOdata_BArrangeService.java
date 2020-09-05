/**
 * 模块名称：拆零容器整理打包（小嘴，按客户复核）
 * 模块代码：3928
 * @author hkl
 */
package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.stock.model.Stock_LabelDModel;

public interface IOdata_BArrangeService {

	// 新取封箱标签
	public MsgRes tscGetNewLabel(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo,String strWorkerNo			
			)throws Exception;
	
	//出货整理打包》输入数量回车
	public MsgRes tscCheckBarcodeAndSave(String currEnterpriseNo, String warehouseNo,
			String str, String strSlabel, String strDlabel, Integer sacnNum,
			String strWorkerNo,String strFlag,String strPrinterGroupNo
			)  throws Exception ;
	//取标签的数量
	public MsgRes tscBoxQty(String currEnterpriseNo, String warehouseNo,
			String str)  throws Exception ;
	
	//修改复合单头档状态为10
	public MsgRes updateCheckM(String currEnterpriseNo, String warehouseNo,
			String str)  throws Exception ;

	public List<Odata_CheckLabelDModel> getStockLabelD(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strLabelNo,
			String strFlag//源容器号	
			)throws Exception ;
	
	// 出货整理打包》封箱
	public MsgRes tscArrangeCutbox(String strEnterpriseNo,String strWarehouseNo,//仓别
			String strDLabelNo,
			String strPrinterGroupNo,
			String strUserId,//操作人员
			double strWeight
			)  throws Exception ;	
	//获取未封箱明细
		public List<Odata_CheckLabelDModel> getCheckLabelDList(String strEnterpriseNo,
				String strWarehouseNo)throws Exception;
		
	// 出货整理打包》检查源容器
	public MsgRes CheckSLabelNo(String strEnterpriseNo,
			String strWarehouseNo,String strSLabelNo, String workerNo,String strQuery)throws Exception;
	
	//获取未复合商品明细
	public ExtListDataBo<Odata_CheckLabelDModel> getUnCheckLabelD(
			String currEnterpriseNo, String warehouseNo, String str)throws Exception ;

	//回单
	public MsgRes tscReceipt(String currEnterpriseNo, String warehouseNo,
			String strSlabel) throws Exception;
    //重扫
	public MsgRes tscAnewScan(String currEnterpriseNo, String warehouseNo,
			String strDlabel,String strWorkerNo) throws Exception;
	
}
