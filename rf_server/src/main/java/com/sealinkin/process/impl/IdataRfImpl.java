package com.sealinkin.process.impl;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.idata.service.Iidata_CheckService;
import com.sealinkin.idata.service.Iidata_InstockService;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.util.ExceptionUtil;

public class IdataRfImpl implements IBaseRfService {

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
				case SessionDefine.GC_Session_Type_Idata_DockValidate://码头检验	
					msgRes=IdataISDockValidate(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK_SerialNo://存储扫描流水号	
					msgRes=IdataCheckSerialNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK_LabelNo://扫描板号
					msgRes=IdataCheckLabelNo(strRecvData);	
					break;					
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK_Barcode://验收扫描商品条码
					msgRes=IdataISCheckBarcode(strRecvData);	
					break;		
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK://存储验收事件
					msgRes=IdataCheck(strRecvData);	
					break;		
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK_GetLot://获取商品批号信息 
					msgRes=IdataCheckGetLot(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK_CheckExists://校验是否能超量验收
					msgRes=CheckExists(strRecvData);	
					break;					
				case SessionDefine.GC_SESSION_TYPE_Idata_CHECK_CloseLabel://封板事件
					msgRes=IdataCheckCloseLabel(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_DOCK://校检码头
					msgRes=IdataDockValidate(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_SerialNo://直通流水号
					msgRes=IDSerialNo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_ID_LabelNo://直通扫描板号
					msgRes=IdataCheckLabelNo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_ID_Barcode://直通扫描商品条码
					msgRes=IdataCheckBarcode(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_Check://直通验收确认
					msgRes=IdataCheckID(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_CloseLabel://直通封板
					msgRes=IdataCloseLabelID(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_CheckTTH://直通验收确认（天天惠）
					msgRes=IdataCheckIDTTH(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_GetArticleInfoByBarcodeAndPoNo://直通验收获取商品信息（天天惠）
				    msgRes=IDGetArticleNoInfoByBarcodeAndPoNo(strRecvData);
				    break;
				case SessionDefine.GC_SESSION_TYPE_ID_CheckBarcode://直通验收确定商品条码是否可以验收保存
					msgRes=IDCheckBarcode(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_INSTOCK_LabelNo://151上架校检标签
					msgRes=IdataInstockLabelNo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_INSTOCK_Receipt://152上架回单
					msgRes=IdataInstockReceipt(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_INSTOCK_Barcode://153上架扫条码
					msgRes=IdataInstockBarcode(strRecvData);	
					break;					
				case SessionDefine.GC_SESSION_TYPE_IM_CHECK_LogisticsCode://20验收扫描物流码
					msgRes=insert_ScanLabelNoLog(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_IM_CHECK_ClearLogisticsCode://21验收扫描物流码退出清除bdef_scan_log表      
					msgRes=deleteBdefScanLog(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_BoxCodeInput://22 箱码采集  扫描储位      
					msgRes=boxCodeCellInput(strRecvData);
					break;					
				case SessionDefine.GC_SESSION_TYPE_BoxCodeOK://23 箱码采集  外箱箱码确认      
					msgRes=boxCodeCellOK(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_BarCodeSerialNo://24条码采集 扫流水
					msgRes=barCodeSerialNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_BarCodeOK://25条码采集 确认
					msgRes=barCodeOK(strRecvData);
					break;

				case SessionDefine.GC_SESSION_TYPE_LogisticsCodeOK://26条码采集 物流码确认
					msgRes=logisticsCodeOK(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_BoxCodeSerialNo: //27箱码采集 扫流水
					msgRes=boxCodeSerialNo(strRecvData);
					break;
					
				case SessionDefine.GC_SESSION_TYPE_LogisticsBarcodeInput://28物流码采集 扫条码
					msgRes=LogisticsBarcodeInput(strRecvData);
					break;					

				case SessionDefine.GC_SESSION_TYPE_ID_CHECKSAVEANDCLOSEPAL://直通验收保存并封板
					msgRes=IDCheckSaveAndClosePal(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_Idata_GetArticlePacking://40 RF获取商品各包装信息
					msgRes=tscGetArticlePacking(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_ImportDetailByOver://30 超品验收--新增进货单以及汇总单明细 huangbin 20160712
					msgRes=tscImportDetailByOver(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_getDeffieldvalInfo://31 获取码表信息 huangbin 20160712
					msgRes=getDeffieldvalInfo(strRecvData);
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
	/***
	 * 存储验收码头检验
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataISDockValidate(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes) idata_CheckImpl.IdataISDockValidate(strRecvData);
	}

	/***
	 * 直通验收码头检验
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataDockValidate(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes) idata_CheckImpl.IdataDockValidate(strRecvData);
	}
	/***
	 * 存储扫描流水号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataCheckSerialNo(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return (MsgRes)idata_CheckImpl.idataCheckSerialNo(strRecvData,"0");
	} 
	
	/***
	 * 扫描板号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataCheckLabelNo(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscRfCheckLabelNo(strRecvData);
	} 
	/***
	 * 存储验收扫描商品条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */	
	private MsgRes IdataISCheckBarcode(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes)idata_CheckImpl.IdataISCheckBarcode(strRecvData);		
	} 
	/***
	 * 直通验收扫描商品条码找单
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */	
	private MsgRes IdataCheckBarcode(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes)idata_CheckImpl.queryRFCheckD(strRecvData);		
	} 
	/***
	 * 存储验收事件
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes IdataCheck(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscISSaveAndColse(strRecvData);
	} 	
	/***
	 * 获取商品批号信息 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes IdataCheckGetLot(String strRecvData) throws Exception {		
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return (MsgRes)idata_CheckImpl.queryLotProduceDate(strRecvData);
	}	
	
	/***
	 * 封板事件
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes IdataCheckCloseLabel(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return (MsgRes)idata_CheckImpl.tscRfClosePalWriteLocate(strRecvData);	
	}	
	
	/***
	 * 直通扫描流水号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IDSerialNo(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return (MsgRes)idata_CheckImpl.idataCheckSerialNo(strRecvData,"1");
	} 
	
	/***
	 * 直通存储验收事件
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes IdataCheckID(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscRfIdataCheckID(strRecvData);
	} 		
	/***
	 * 直通封板事件
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes IdataCloseLabelID(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscRfIdataCloseLabelID(strRecvData);
	}
	/***
	 * 直通存储验收事件
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes IdataCheckIDTTH(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscRfIdataCheckIDTTH(strRecvData);
	} 
	/**
	 * 直通验收获取商品信息(天天惠)
	 */
	private MsgRes IDGetArticleNoInfoByBarcodeAndPoNo(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.IDGetArticleNoInfoByBarcodeAndPoNo(strRecvData);
	} 
	
	/**
	 * 直通验收确定商品是否可以验收
	 */
	private MsgRes IDCheckBarcode(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.IDCheckBarcodeForSave(strRecvData);
	}
	
	/**
	 * 上架扫描标签号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataInstockLabelNo(String strRecvData) throws Exception 
	{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.IdataInstockLabelNo(strRecvData);
	}
	/**
	 * 上架扫描条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataInstockBarcode(String strRecvData) throws Exception 
	{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.IdataInstockBarcodeNo(strRecvData);
	}	
	/**
	 * 上架确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes IdataInstockReceipt(String strRecvData) throws Exception 
	{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return idata_InstockImpl.tscComfire(strRecvData);
	}
	/***
	 * 校验是否能验收
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */		
	private MsgRes CheckExists(String strRecvData) throws Exception {
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscRfCheckExists(strRecvData);
	}	
	
	/**
	 * 扫物流码过程
	 * @return
	 */
	private MsgRes insert_ScanLabelNoLog(String strRecvData) throws Exception{
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return idata_CheckImpl.tscRfInsert_ScanLabelNoLog(strRecvData);
	}
	
	/**
	 * 扫物流码过程
	 * @return
	 */
	private MsgRes deleteBdefScanLog(String strRecvData) throws Exception{
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");
		return idata_CheckImpl.tscDeleteBdefScanLog(strRecvData);
	}
	/**
	 * 箱码采集  扫描储位    
	 * @return
	 */
	private MsgRes boxCodeCellInput(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.RfBoxCodeInput(strRecvData);
	}
	/**
	 * 箱码采集  包箱条码Ok    
	 * @return
	 */
	private MsgRes boxCodeCellOK(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.tscRfBoxCodeOk(strRecvData);
	}
	
	/**
	 * 条码采集 扫流水  
	 * @return条码采集 扫流水
	 */
	private MsgRes barCodeSerialNo(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.barCodeSerialNo(strRecvData);
	}
	
	/**
	 * 条码采集 扫流水  
	 * @return
	 */
	private MsgRes barCodeOK(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.tscRfbarCodeOK(strRecvData);
	}

	
	/**
	 * 26条码采集 物流码确认 
	 * @return
	 */
	private MsgRes logisticsCodeOK(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.tscRflogisticsCodeOK(strRecvData);
	}
	
	/**
	 * 27箱码采集 扫流水
	 * @return
	 */
	private MsgRes boxCodeSerialNo(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.boxCodeSerialNo(strRecvData);
	}

	
	/**
	 * 28物流码采集 扫条码
	 * @return
	 */
	private MsgRes LogisticsBarcodeInput(String strRecvData) throws Exception{
		Iidata_InstockService idata_InstockImpl = (Iidata_InstockService) SpringContextUtil.getBean("idata_InstockImpl");
		return (MsgRes)idata_InstockImpl.LogisticsBarcodeInput(strRecvData);
	}
	/**
	 * 直通验收保存并封板
	 * @param strRecvData
	 * @return
	 */
	private MsgRes IDCheckSaveAndClosePal(String strRecvData) throws Exception{
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes) idata_CheckImpl.tscIDCheckSaveAndClosePal(strRecvData);
	}
	
	/**
	 * 获取商品各包装信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscGetArticlePacking(String strRecvData) throws Exception{
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes) idata_CheckImpl.tscGetArticlePacking(strRecvData);
	}
	
	//超品验收--新增进货单以及汇总单明细 huangbin 20160712
	private MsgRes tscImportDetailByOver(String strRecvData) throws Exception{
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes) idata_CheckImpl.tscImportDetailByOver(strRecvData);
	}
	
	/** 获取品质码表数据
	 * huangb 20160712
	 */
	private MsgRes getDeffieldvalInfo(String strRecvData) throws Exception{
		Iidata_CheckService idata_CheckImpl = (Iidata_CheckService) SpringContextUtil.getBean("idata_CheckImpl");		
		return (MsgRes) idata_CheckImpl.getDeffieldvalInfo(strRecvData);
	}

}
