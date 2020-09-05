package com.sealinkin.process.impl;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.ridata.service.IRidata_CheckService;
import com.sealinkin.ridata.service.IRidata_InstockService;
import com.sealinkin.util.ExceptionUtil;

public class RIdataRfImpl implements IBaseRfService {

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
				case SessionDefine.GC_SESSION_TYPE_RIDATA_InPutSerialNo://返配扫描流水号	
					msgRes=InPutSerialNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_InPutBarcodeNo://返配扫描条码	
					msgRes=InPutBarcodeNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_CheckSave://返配验收保存
					msgRes=CheckSave(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_RIDATA_CheckLabel://返配验收校验板号
					msgRes=CheckLabel(strRecvData);	
					break;	
					
				case SessionDefine.GC_SESSION_TYPE_RIDATA_ScanDock:  //扫描码头号(天天惠)
					msgRes=CheckDock(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_ScanUntreadNoAndWallNo:  //资源试算
					msgRes=tscResourceCalculation(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_GetPoNO:		//获取单号
					msgRes=GetPoNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_ScanBarcodeNo:  //扫描条码确认
					msgRes=tscScanBarcodeNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_GetCellNo:   //获取过季品建议储位
					msgRes=getCellNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_GetQualityByWallNo:
					msgRes=getqualityByWallNo(strRecvData);              //根据分播墙获取商品的类型
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_CloseLabel:    //封箱
					msgRes=tscClosBox(strRecvData);
					break;
					
				case SessionDefine.GC_SESSION_TYPE_RIDATA_CHECKLABELANDGETINFO://检验标签号并且获取商品信息
					msgRes=tscCheckLabelAndGetInfo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_ARRANGECOMFIRE://返配上架整理确认
					msgRes=tscArrangeComfire(strRecvData);
					break;
					
					
				//铁越  Add by sunl
				case SessionDefine.GC_SESSION_TYPE_RIDATA_RI_ScanBarcodeNO:  //扫描条码确认
					msgRes=GetMixRIScanBarcodeNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_RI_GetLotInfo:  //获取批次信息
					msgRes=GetMixRILotInfo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_RI_CheckSave:  //验收保存
					msgRes=tscMixRICheckSave(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_RI_ClosePal:  //封板
					msgRes=tscMixRIClosePal(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_RIDATA_RI_GetCheckInfo:  //获取已验收信息（）
					msgRes=GetMixRICheckInfo(strRecvData);
					break;
			     
				default:
					msgRes.setIsSucc(false);
					msgRes.setMsg("功能未定义！");
					break;
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
	 * 扫描流水号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes InPutSerialNo(String strRecvData) throws Exception {
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.InPutSerialNo(strRecvData);
	}	
	/***
	 * 扫描条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes InPutBarcodeNo(String strRecvData) throws Exception {
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.InPutBarcode(strRecvData);
	}	
	/***
	 * 扫描条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes 	CheckSave(String strRecvData) throws Exception {
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.tscRfCheckSave(strRecvData);
	}
	
	/***
	 * 校验板号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes 	CheckLabel(String strRecvData) throws Exception {
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.CheckLabel(strRecvData);
	}
	
	
	/***
	 * 码头号（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes 	CheckDock(String strRecvData) throws Exception {
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.CheckDock(strRecvData);
	}
	/***
	 * 资源试算，并返回相应的单据信息（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscResourceCalculation(String strRecvData)throws Exception {
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.tscResourceCalculation(strRecvData);
	}
	
	/***
	 * 扫描条码确认（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscScanBarcodeNo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.tscScanBarcodeNo(strRecvData);
	}
	
	/***
	 * RF获取过季品预上储位（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes getCellNo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.getCellNo(strRecvData);
	}
	
	/***
	 * RF根据分播墙获取商品的类型（天天惠）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes getqualityByWallNo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.getqualityByWallNo(strRecvData);
	}
	/***
	 * 返配封箱（天天惠） 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscClosBox(String strRecvData)throws Exception{
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.tscClosBox(strRecvData);
	}
	
	/***
	 * 校验标签号并且获取标签商品信息（天天惠） 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscCheckLabelAndGetInfo(String strRecvData)throws Exception{
		IRidata_InstockService ridata_instockImpl = (IRidata_InstockService) SpringContextUtil.getBean("ridata_InstockImpl");
		return (MsgRes)ridata_instockImpl.tscCheckLabelAndGetInfo(strRecvData);
	}
	
	/***
	 * 返配上架整理确认（天天惠） 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscArrangeComfire(String strRecvData)throws Exception{
		IRidata_InstockService ridata_instockImpl = (IRidata_InstockService) SpringContextUtil.getBean("ridata_InstockImpl");
		return (MsgRes)ridata_instockImpl.tscArrangeComfire(strRecvData);
	}
	
	private MsgRes GetPoNo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_CheckImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_CheckImpl.GetPoNo(strRecvData);
	}  

	/***
	 * 铁越反配扫条码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetMixRIScanBarcodeNo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_instockImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_instockImpl.GetMixRIScanBarcodeNo(strRecvData);
	}

	/***
	 * 铁越RF反配验收取批次信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetMixRILotInfo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_instockImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_instockImpl.GetMixRILotInfo(strRecvData);
	}  

	/***
	 * 铁越RF反配验收
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscMixRICheckSave(String strRecvData)throws Exception{
		IRidata_CheckService ridata_instockImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_instockImpl.tscMixRICheckSave(strRecvData);
	}  

	/***
	 * 铁越RF获取已验收信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetMixRICheckInfo(String strRecvData)throws Exception{
		IRidata_CheckService ridata_instockImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_instockImpl.GetMixRICheckInfo(strRecvData);
	}  


	/***
	 * 铁越RF反配封板
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscMixRIClosePal(String strRecvData)throws Exception{
		IRidata_CheckService ridata_instockImpl = (IRidata_CheckService) SpringContextUtil.getBean("ridata_CheckImpl");
		return (MsgRes)ridata_instockImpl.tscMixRIClosePal(strRecvData);
	}  
	
}
