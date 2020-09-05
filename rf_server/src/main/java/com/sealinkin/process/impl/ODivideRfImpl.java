package com.sealinkin.process.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.odata.service.IOdata_DivideService;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.odata.ReqCutBoxModel;
import com.sealinkin.protocolExchange.odata.ReqGetTaskLabel;
import com.sealinkin.protocolExchange.odata.ReqSaveODivideModel;
import com.sealinkin.protocolExchange.odata.StuReqBoxUserListModel;
import com.sealinkin.util.ExceptionUtil;

public class ODivideRfImpl implements IBaseRfService{

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
				case SessionDefine.GC_Session_Type_ODIVIDE_CheckFlag://判断与读取物流箱信息
					msgRes=CheckFlag(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_ODIVIDE_Get_Divide_Detail://分播物流箱数据
					msgRes=getDivideDetail(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_ODIVIDE_Save_Divide_Data://分播数据保存
					msgRes=saveDivideData(strRecvData);	
					break;	
				case SessionDefine.GC_Session_Type_ODIVIDE_Cut_BOX://封箱
					msgRes=cutBOX(strRecvData);	
					break;
				case SessionDefine.GC_Session_Type_ODIVIDE_GetUserTask://读取用户当前区域任务
					msgRes = GetUserTask(strRecvData);
					break;
				case SessionDefine.GC_Session_Type_ODIVIDE_Change_Group://更换设备组
					msgRes = ChangeGroup(strRecvData);
					break;
				case SessionDefine.GC_Session_Type_ODIVIDE_GetBox_UserList: //读取物流箱已分播用户
					msgRes = GetBoxUserList(strRecvData);
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
	private MsgRes CheckFlag(String strRecvData) throws Exception {
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.CheckFlag(strRecvData);
	}
	private MsgRes getDivideDetail(String strRecvData) throws Exception {
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.getDivideDetail(strRecvData);
	}
	
	private MsgRes saveDivideData(String strRecvData) throws Exception {
		ReqSaveODivideModel reqMod=JSON.parseObject(strRecvData, ReqSaveODivideModel.class);
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");
		return (MsgRes) odata_DivideImpl.tscSaveDivideData(reqMod.getEnterpriseNo(), 
				reqMod.getWareHouseNo(), 
				reqMod.getSourceNo(),
				reqMod.getArticleNo(),
				reqMod.getCellNo(),
				reqMod.getBarcode(),
				reqMod.getRealQty(),
				reqMod.getUserId(),
				reqMod.getCustNo(),
				reqMod.getLabelNo(),
				reqMod.getAddupFlag());
	}
	
	private MsgRes cutBOX(String strRecvData) throws Exception {
		ReqCutBoxModel resMod=JSON.parseObject(strRecvData, ReqCutBoxModel.class);
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.tscCutBox(resMod.getEnterpriseNo(),
				resMod.getWareHouseNo(), 
				resMod.getCellNo(), 
				resMod.getUserId(), 
				resMod.getPaperUserId(), 
				resMod.getOperateTools(), 
				resMod.getPntName());
	}
	
	/**
	 * 读取用户当前区域任务
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetUserTask(String strRecvData) throws Exception {
		ReqGetTaskLabel resMod=JSON.parseObject(strRecvData, ReqGetTaskLabel.class);
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.GetUserTask(resMod.getEnterpriseNo(),
				resMod.getWareHouseNo(), 
				resMod.getUserId(), 
				resMod.getAreaNo(), 
				resMod.getStockNo(),
				resMod.getCtrlNo(),
				resMod.getUseType());
	}
	
	/**
	 * 更换设备组
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes ChangeGroup(String strRecvData) throws Exception {
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.ChangeGroup(strRecvData);
	}
	
	/**
	 * 读取物流箱已分播用户
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetBoxUserList(String strRecvData) throws Exception {
		StuReqBoxUserListModel resMod=JSON.parseObject(strRecvData, StuReqBoxUserListModel.class);
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.GetBoxUserList(resMod.getEnterpriseNo(),
				resMod.getWareHouseNo(), 
				resMod.getUseType(),
				resMod.getLabelNo());
	}
}
