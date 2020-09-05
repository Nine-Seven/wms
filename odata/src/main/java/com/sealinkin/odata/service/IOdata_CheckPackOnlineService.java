package com.sealinkin.odata.service;

import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

public interface IOdata_CheckPackOnlineService {
	
	//校验任务号或者快递面单
	MsgRes tscCheckTaskNo(String currEnterpriseNo, String warehouseNo,
			String strCheckNo, String strWorkerNo,
			String strPrinterGroupNo,String str,String strAutoOutstock)throws Exception;
	
	//获取未复核明细
	List<Odata_CheckLabelDModel> getCheckD(String currEnterpriseNo,
			String warehouseNo, String strCheckNo,String strCheckType,
			String strScanNo)throws Exception;

	//获取目的标签明细（已复核明细）
	List<Stock_LabelDModel> getStockLabelD(String currEnterpriseNo,
			String warehouseNo, String strCheckNo,String strCheckType,
			String strScanNo)throws Exception;
	
	//获取未复核单数/配送对象
	MsgRes getUnCheckObj(String currEnterpriseNo, String warehouseNo,
			String strCheckNo,
			String strCheckType,
			String strScanNo)throws Exception;
	
	//获取未复核列表（弹出框）
	List<Stock_LabelMModel> getUnCheckObjList(String currEnterpriseNo,
				String warehouseNo, 
				String strCheckNo,
				String strCheckType,
				String strScanNo)throws Exception;
	
/*	//获取复核单头档配送对象的箱码
	List<Stock_LabelMModel> getCheckPackLabel(String currEnterpriseNo,
			String warehouseNo, String str, String checkNo)throws Exception;
	*/
	//扫描商品条码
	MsgRes tscCheckBarcode(String str, String strCheckType,String strScanNo, 
			String sacnNum, String strWorkerNo, String strPrinterGroupNo,
			String strPrintWayBill,String strPrintPackList,String strPrintInVoice) throws Exception;

	//封箱
	MsgRes tscArrangeCutbox(String currEnterpriseNo, String warehouseNo,
			String strDlabel, String strPrinterGroupNo, String strWorkerNo)throws Exception;

	//转病单   8-12修改
	MsgRes tscChangeSickOrder(String currEnterpriseNo, String warehouseNo,
			String strCheckNo, String strDeliverObj, String strWorkerNo)throws Exception;
	
	//包材出货
	public MsgRes tscOutPackMete(String currEnterpriseNo, String warehouseNo,
			String strLabelNo,String str2,
			String articleQTY,
			String strWorkerNo)throws Exception;
	

	//获取未复核的商品信息
	MsgRes tscCheckUnfinished(String currEnterpriseNo,
			String warehouseNo, 
			String strCheckNo,
			String strCheckType,
			String strScanNo)throws Exception;

	//根据快递单号或者配送对象取布控标志
	MsgRes getECbukong(String currEnterpriseNo,
			String warehouseNo,
			String strCheckNo,
			String strDeliverObj)throws Exception;
	
	

}
