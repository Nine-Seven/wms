package com.sealinkin.process.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.odata.service.IDPS_SystemService;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.dpsdata.ReqGetDivideSuppBoxModel;
import com.sealinkin.protocolExchange.odata.ReqCutBoxModel;
import com.sealinkin.protocolExchange.odata.ReqSaveDivideSuppModel;
import com.sealinkin.util.ExceptionUtil;

public class RIDivideImpl implements IBaseRfService{

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
				case SessionDefine.GC_Session_Type_RISUP_Get_Divide_Detail://分播物流箱数据
					msgRes=getDivideDetail(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_RISUP_Save_Divide_Data://分播数据保存
					msgRes=saveDivideData(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_RISUP_Cut_BOX://封箱
					msgRes=cutBOX(strRecvData);	
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
	private MsgRes getDivideDetail(String strRecvData) throws Exception {
		ReqGetDivideSuppBoxModel resMod=JSON.parseObject(strRecvData, ReqGetDivideSuppBoxModel.class);
		IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");		
		return (MsgRes) dps_SystemImpl.getDivideDetail(resMod.getEnterpriseNo(), 
				resMod.getWarehouseNo(), 
				resMod.getUseType(), 
				resMod.getCtrlNo(), 
				resMod.getAreaNo(), 
				resMod.getStockNo(), 
				resMod.getBoxNo());
	}
	
	private MsgRes saveDivideData(String strRecvData) throws Exception {
		ReqSaveDivideSuppModel resMod=JSON.parseObject(strRecvData, ReqSaveDivideSuppModel.class);
		IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");		
		return (MsgRes) dps_SystemImpl.tscSaveDivideData(resMod.getEnterpriseNo(),
				resMod.getWareHouseNo(), 
				resMod.getInstockNo(), resMod.getLabelNo(), 
				resMod.getArticleNo(), 
				resMod.getBarcode(), resMod.getPackingQty(), resMod.getRealQty(), 
				resMod.getUserId(), 
				resMod.getPaperUserId(), 
				resMod.getOperateTools());
	}
	
	private MsgRes cutBOX(String strRecvData) throws Exception {
		ReqCutBoxModel resMod=JSON.parseObject(strRecvData, ReqCutBoxModel.class);
		IDPS_SystemService dps_SystemImpl = (IDPS_SystemService) SpringContextUtil.getBean("dps_SystemImpl");		
		return (MsgRes) dps_SystemImpl.tscCutBOX(resMod.getEnterpriseNo(),
				resMod.getWareHouseNo(), 
				resMod.getCellNo(), 
				resMod.getUserId(), 
				resMod.getPaperUserId(), 
				resMod.getOperateTools(), 
				resMod.getPntName());
	}
}
