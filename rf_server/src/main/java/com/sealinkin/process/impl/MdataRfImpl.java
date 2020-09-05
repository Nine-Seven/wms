package com.sealinkin.process.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.mdata.service.IOdata_OutstockDService;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.mdata.HMMoveInStockRequestModel;
import com.sealinkin.protocolExchange.mdata.HMMoveLabelNoRequestModel;
import com.sealinkin.protocolExchange.mdata.HMMoveOutStockRequestModel;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.ExceptionUtil;
/**
 * 移库RF
 * @author lich
 *
 */
public class MdataRfImpl implements IBaseRfService{

	@Override
	public CBizAnswerModel doRfApplication(Integer nReqType, String strRecvData) {
		CBizAnswerModel bizAnsMod=new CBizAnswerModel();
		MsgRes msgRes=new MsgRes();
		System.out.println("******传入******");
		System.out.println(nReqType);
		System.out.println("***************");
		try {
			switch(nReqType)
			{
				case SessionDefine.GC_SESSION_TYPE_HM_ScanLabelNo://移库扫描标签
					msgRes=HMScanLabelNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_OutStock://移库下架
					msgRes=HMOutStock(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_HM_InStock://移库上架
					msgRes=HMInStock(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_HM_OutCellNo://即时移库扫描储位
					msgRes=HMOutCellNo(strRecvData);	
					break;					
				case SessionDefine.GC_SESSION_TYPE_HM_Barcode://即时移库扫描条码
					msgRes=HMBarcode(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_HM_InCellNo://即时移库扫描目的储位
					msgRes=HMInCellNo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_HM_SaveMoveCell://即时移库保存
					msgRes=HMSaveMoveCell(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_HM_RIGetDCellNo://返配标签移库获取建议储位
					msgRes=HMGetRIDCellNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_RISaveMoveCell://返配标签移库保存
					msgRes=tscSaveRIMoveCell(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_HM_GetDCellNo://即时移库获取建议储位
					msgRes=tscGetHMDCell(strRecvData);	
					break;		
				case SessionDefine.GC_SESSION_TYPE_HM_GetLabelInfo://过季转应季标签整理获取标签信息
					msgRes=tscGetHMLabelInfo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_ArticleArrange://过季转应季标签整理商品整理
					msgRes=tscHMArticleArrange(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_StockSetLabel_DockValidate://库存录标签校验码头
					msgRes=checkDockValidate(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_StockSetLabel_CellNoValidate://库存录标签校验储位
					msgRes=checkCellNoValidate(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_StockSetLabel_LabelNoValidate://库存录标签校验标签
					msgRes=checkLabelNoValidate(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_StockSetLabel_GetStockInfo://库存录标签获取库存信息
					msgRes=GetHMStockSetLabelInfo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_StockSetLabel_ScanSave://库存录标签扫描保存
					msgRes=saveHMScaninfo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_HM_StockSetLabel_CloseBox://库存录标签封箱
					msgRes=tscHMCloseBox(strRecvData);
					break;
				default:
					msgRes.setIsSucc(false);
					msgRes.setMsg("功能未定义！");
			}				
			bizAnsMod.setStrResult(msgRes.getIsSucc()?"Y":"N");
			bizAnsMod.setNRequestType(nReqType+SessionDefine.PACK_ACK_RADIX);
			bizAnsMod.setStrData(msgRes.getIsSucc()?(msgRes.getObj()!=null?msgRes.getObj().toString():""):msgRes.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			bizAnsMod.setStrResult("N");
			bizAnsMod.setNRequestType(nReqType+SessionDefine.PACK_ACK_RADIX);
			/*bizAnsMod.setStrData(e.getMessage());*/
			bizAnsMod.setStrData(ExceptionUtil.getCacseMessage(e));
		}
		return bizAnsMod;
	}
	private MsgRes HMScanLabelNo(String strRecvData) throws Exception {
		HMMoveLabelNoRequestModel reqMod=JSON.parseObject(strRecvData, HMMoveLabelNoRequestModel.class);
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.HMScanLabelNo(reqMod.getEnterpriseNo(),reqMod.getWareHouseNo(),
				"",
				reqMod.getLabelNo(),
				reqMod.getStatus());
	}
	
	private MsgRes HMOutStock(String strRecvData) throws Exception {
		HMMoveOutStockRequestModel reqMod=JSON.parseObject(strRecvData, HMMoveOutStockRequestModel.class);
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");
		return (MsgRes) odata_OutstockDImpl.tscRfOutstock(reqMod.getEnterpriseNo(),reqMod.getWarehouseNo(), 
				reqMod.getStockNo(), 
				reqMod.getArticleNo(), 
				Double.parseDouble(reqMod.getPackingQty()), 
				DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"),
				DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"), 
				reqMod.getQuality(), 
				reqMod.getLotNo(), 
				reqMod.getRsvBatch1(), 
				reqMod.getRsvBatch2(), 
				reqMod.getRsvBatch3(), 
				reqMod.getRsvBatch4(), 
				reqMod.getRsvBatch5(), 
				reqMod.getRsvBatch6(), 
				reqMod.getRsvBatch7(), 
				reqMod.getRsvBatch8(),
				Double.parseDouble(reqMod.getRealOutStockQty()), 
				reqMod.getRealOutStockCellNo(), 
				reqMod.getInStockCellNo(), 
				reqMod.getLabelNo(), 
				"N"//strUserID
				);
	}
	
	private MsgRes HMInStock(String strRecvData) throws Exception {
		HMMoveInStockRequestModel reqMod=JSON.parseObject(strRecvData, HMMoveInStockRequestModel.class);
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscPTaskLabelSaveOdataOutstock(
				reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(), 
				reqMod.getStockNo(), 
				reqMod.getLabelNo(), 
				reqMod.getArticleNo(), 
				Double.parseDouble(reqMod.getPackingQty()), 
				reqMod.getRealOutStockCellNo(), 
				Double.parseDouble(reqMod.getArticleQty()),
				Double.parseDouble(reqMod.getRealInStockQty()), 
				reqMod.getQuality(), 
				DateUtil.GetDate(reqMod.getProduceDate(), "yyyyMMdd"),
				DateUtil.GetDate(reqMod.getExpireDate(), "yyyyMMdd"),				
				reqMod.getLotNo(), 
				reqMod.getRsvBatch1(), 
				reqMod.getRsvBatch2(), 
				reqMod.getRsvBatch3(), 
				reqMod.getRsvBatch4(), 
				reqMod.getRsvBatch5(), 
				reqMod.getRsvBatch6(), 
				reqMod.getRsvBatch7(), 
				reqMod.getRsvBatch8(), 
				"N", //strDockNo, 
				reqMod.getUserID(), 
				reqMod.getUserID(), 
				reqMod.getUserID());
	}
	private MsgRes HMOutCellNo(String strRecvData) throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.HMOutCellNo(strRecvData);
	}	
	private MsgRes HMInCellNo(String strRecvData) throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.HMInCellNo(strRecvData);
	}
	private MsgRes HMBarcode(String strRecvData) throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.HMBarcode(strRecvData);
	}	
	private MsgRes HMSaveMoveCell(String strRecvData) throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscRfSave(strRecvData);
	}	
	
	private MsgRes HMGetRIDCellNo(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.HMGetRIDCellNo(strRecvData);
	}
	
	private MsgRes tscSaveRIMoveCell(String strRecvData) throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscRfRISave(strRecvData);
	}
	
	private MsgRes tscGetHMDCell(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscGetHMDCell(strRecvData);
	}
	
	private MsgRes tscGetHMLabelInfo(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscGetHMLabelInfo(strRecvData);
	}
	private MsgRes tscHMArticleArrange(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscHMArticleArrange(strRecvData);
	}
	

	private MsgRes checkDockValidate(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.checkDockValidate(strRecvData);
	}
	
	private MsgRes checkCellNoValidate(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.checkCellNoValidate(strRecvData);
	}
	private MsgRes checkLabelNoValidate(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.checkLabelNoValidate(strRecvData);
	}
	private MsgRes GetHMStockSetLabelInfo(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.GetHMStockSetLabelInfo(strRecvData);
	}
	
	private MsgRes saveHMScaninfo(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.saveHMScaninfo(strRecvData);
	}
	
	private MsgRes tscHMCloseBox(String strRecvData)throws Exception {
		IOdata_OutstockDService odata_OutstockDImpl = (IOdata_OutstockDService) SpringContextUtil.getBean("odata_OutstockDImpl");		
		return (MsgRes) odata_OutstockDImpl.tscHMCloseBox(strRecvData);
	}

}
