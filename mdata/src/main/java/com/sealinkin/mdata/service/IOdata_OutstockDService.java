package com.sealinkin.mdata.service;

import java.util.Date;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.model.Odata_OutstockMModel;

public interface IOdata_OutstockDService {
	ExtListDataBo<Odata_OutstockDModel> getOdata_OutstockD(String enterpriseNo,String warehouseNo,String ownerNo,String flag,String str,
			Integer start, Integer pagesize);

	ExtListDataBo<Odata_OutstockMModel> getOdata_OutstockM(String enterpriseNo,String warehouseNo,String ownerNo,String flag,String str,
			Integer start, Integer pagesize);
	
/*	ExtListDataBo<Odata_OutstockDModel> getOdata_OutstockDDetail(String enterpriseNo,String warehouseNo,String ownerNo,String str,
			Integer start, Integer limit);*/

	MsgRes save(String workerNo, String str, String strTaskType) throws Exception;
    
	public MsgRes tscPTaskLabelSaveOdataOutstock(
			String strEnterpriseNo,
			String strWarehoseNo  ,
			String strOutstockNo  ,
			String strLabelNo,
			String strArticleNo  ,
			Double nPackingQty,
			String strSCellNo  ,
			Double articleQTY,
			Double nRealQTY  ,
			String strQuality  ,
			Date dtProduceDate  ,
			Date dtExpireDate  ,
			String strLotNo  ,
			String strRsvBatch1  ,
			String strRsvBatch2  ,
			String strRsvBatch3  ,
			String strRsvBatch4  ,
			String strRsvBatch5  ,
			String strRsvBatch6  ,
			String strRsvBatch7  ,
			String strRsvBatch8  ,
			String strDockNo,
			String strUserID  ,
			String strOutstockID, 
			String strInstockID
			
			) throws Exception;
	
	public MsgRes tscFormSaveOdataOutstock(
			String strEnterpriseNo,
			String strWarehoseNo  ,
			String strOwnerNo,
			String strOutstockNo  ,
			String strArticleNo  ,
			String strBarcode  ,
			Date dtProduceDate  ,
			Date dtExpireDate  ,
			String strQuality  ,
			String strLotNo  ,
			String strRsvBatch1  ,
			String strRsvBatch2  ,
			String strRsvBatch3  ,
			String strRsvBatch4  ,
			String strRsvBatch5  ,
			String strRsvBatch6  ,
			String strRsvBatch7  ,
			String strRsvBatch8  ,
			String strSCellNo  ,
			String strDCellNo  ,
			Double articleQTY, 
			Double nRealQTY  ,
			String strUserID  ,
			String strInstockID,
			String strOutstockID 
			) throws Exception;
	MsgRes HMScanLabelNo(String strEnterpriseNo,String strWarehouseNo,
			String strOwnerNo, 
			String strLabelNo,
			String strStatus) throws Exception;
	
	//Rf移库下架 更新状态
	public MsgRes tscRfOutstock(
			String strEnterpriseNo,
			String strWarehoseNo  ,
			String strOutstockNo  ,
			String strArticleNo  ,
			Double nPackingQty,
			Date dtProduceDate  ,
			Date dtExpireDate  ,
			String strQuality  ,
			String strLotNo  ,
			String strRsvBatch1  ,
			String strRsvBatch2  ,
			String strRsvBatch3  ,
			String strRsvBatch4  ,
			String strRsvBatch5  ,
			String strRsvBatch6  ,
			String strRsvBatch7  ,
			String strRsvBatch8  ,
			Double nQTY  ,
			String strSCellNo  ,
			String strDCellNo  ,			
			String strLabelNo,
			String strUserID 
			) throws Exception;
	
	public MsgRes HMOutCellNo(String strRecvData) throws Exception;
	
	public MsgRes HMBarcode(String strRecvData) throws Exception;	

	public MsgRes HMInCellNo(String strRecvData) throws Exception;
	
	public MsgRes tscSaveMoveCell(
			    String strEnterpriseNo,
			    String strWarehouseNo,
				String strArticleNo,
				String strSCellNo,
				String strLabelNo,
				String strDCellNo,
				Double nPackingQty,
				Date dtProduceDate  ,
				Date dtExpireDate  ,
				String strQuality  ,
				String strLotNo  ,
				String strRsvBatch1  ,
				String strRsvBatch2  ,
				String strRsvBatch3  ,
				String strRsvBatch4  ,
				String strRsvBatch5  ,
				String strRsvBatch6  ,
				String strRsvBatch7  ,
				String strRsvBatch8  ,  
				Double nRealQty,
				String strTerminalFlag,
				String strSourceType,
				String strUserID) throws Exception;
	
	public MsgRes tscRfSave(String strRecvData) throws Exception;

	public MsgRes tscSaveRIMoveCell(String strEnterpriseNo, String strWarehouseNo,
			String strLabelNo, String strDCellNo,
			String strTerminalFlag, String strSourceType, String strUserID)
					throws Exception;

	public MsgRes tscRfRISave(String strRecvData) throws Exception;

	public MsgRes HMGetRIDCellNo(String strRecvData) throws Exception;
	//即时移库获取建议储位
	public MsgRes tscGetHMDCell(String strRecvData)throws Exception;
	//过季转应季标签整理获取标签信息
	public MsgRes tscGetHMLabelInfo(String strRecvData)throws Exception;
	//过季转应季标签整理商品整理
	public MsgRes tscHMArticleArrange(String strRecvData)throws Exception;
	//库存录标签获取下架明细
	public MsgRes GetHMStockSetLabelInfo(String strRecvData)throws Exception;
	//库存录标签扫描保存
	public MsgRes saveHMScaninfo(String strRecvData)throws Exception;
	//库存录标签封箱
	public MsgRes tscHMCloseBox(String strRecvData)throws Exception;
	//库存录标签校验码头
	public MsgRes checkDockValidate(String strRecvData)throws Exception;
	//库存录标签校验储位
	public MsgRes checkCellNoValidate(String strRecvData)throws Exception;
	//库存录标签校验标签号
	MsgRes checkLabelNoValidate(String strRecvData) throws Exception;
}
