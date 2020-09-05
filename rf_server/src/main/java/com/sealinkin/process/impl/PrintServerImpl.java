package com.sealinkin.process.impl;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.print.service.IPrinterTagService;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.util.ExceptionUtil;
/**
 * 打印程序
 * @author lich
 *
 */
public class PrintServerImpl implements IBaseRfService{

	@Override
	public CBizAnswerModel doRfApplication(Integer nReqType, String strRecvData) {
		CBizAnswerModel bizAnsMod=new CBizAnswerModel();
		MsgRes msgRes=new MsgRes();
		try {
			switch(nReqType)
			{
				case SessionDefine.GC_Session_Type_Get_PrintTask://获取打印任务
					msgRes=Get_PrintTask(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_Update_PrintTask://更新打印任务
					msgRes=Update_PrintTask(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_Get_ReportInfo://获取报表信息
					msgRes=Get_ReportInfo(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_Get_FieldInfo://获取字段信息表
					msgRes=Get_FieldInfo(strRecvData);	
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
	private MsgRes Get_PrintTask(String strRecvData) throws Exception {
		IPrinterTagService printerTagImpl = (IPrinterTagService) SpringContextUtil.getBean("printerTagImpl");		
		return (MsgRes) printerTagImpl.Get_PrintTask(strRecvData);
	}
	
	private MsgRes Update_PrintTask(String strRecvData) throws Exception {
		IPrinterTagService printerTagImpl = (IPrinterTagService) SpringContextUtil.getBean("printerTagImpl");		
		return (MsgRes) printerTagImpl.Update_PrintTask(strRecvData);
	}
	
	private MsgRes Get_ReportInfo(String strRecvData) throws Exception {
		IPrinterTagService printerTagImpl = (IPrinterTagService) SpringContextUtil.getBean("printerTagImpl");		
		return (MsgRes) printerTagImpl.Get_ReportInfo(strRecvData);
	}
	private MsgRes Get_FieldInfo(String strRecvData) throws Exception
	{
		IPrinterTagService printerTagImpl = (IPrinterTagService) SpringContextUtil.getBean("printerTagImpl");		
		return (MsgRes) printerTagImpl.Get_FieldInfo(strRecvData);
	}
}
