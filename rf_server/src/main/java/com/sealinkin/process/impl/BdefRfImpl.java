package com.sealinkin.process.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.service.IBdef_DefarticleService;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.cset.service.ICset_CellArticleService;
import com.sealinkin.listener.SpringContextUtil;

import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.fcdata.AnsPackingModel;
import com.sealinkin.util.ExceptionUtil;
/**
 * 基础信息采集RF
 * @author lich
 *
 */
@SuppressWarnings("unchecked")
public class BdefRfImpl implements IBaseRfService{

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
				case SessionDefine.GC_SESSION_TYPE_BM_ArticleQpaletteBarcodeInput://堆叠采集条码扫描
					msgRes=ArticleQpaletteBarcodeInput(strRecvData);	
					break;				
				case SessionDefine.GC_SESSION_TYPE_BM_GetPackingInfoByArticleNo://堆叠采集条码扫描
					msgRes=GetPackingInfoByArticleNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_UpdateArticleQpalette://更新堆叠
					msgRes=updateArticleQpalette(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_GetPackingArticleQpalettes://新增商品条码/资料
					msgRes=InsertArticleQpalette(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_GetPackingArticleNOQpalettes://堆叠采集编码扫描
					msgRes=InsertArticleNOQpalette(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_BM_GetPackingArticleQpalette://堆叠采集》新增商品资料
					
					msgRes=GetPackingArticleQpalette(strRecvData);	
					break;
					
                  
				case SessionDefine.GC_SESSION_TYPE_BM_PickCellBarcodeInput://储位对照信息采集》条码扫描
					msgRes=PickCellBarcodeInput(strRecvData);	
					break;		
				case SessionDefine.GC_SESSION_TYPE_BM_UpdatePickCell://更新储位对照
					msgRes=UpdatePickCell(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_BM_SelectArticlePackingInfo://获取商品的所有包装数量及单位
					msgRes=SelectArticlePackingInfo(strRecvData);	
					break;
				
				case SessionDefine.GC_SESSION_TYPE_BM_GetArticlePackingInfo://拣货位采集-获取商品包装，单位，规格，委托业主，商品名称 huangb 20160525
					msgRes=getArticlePackingInfo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_getArticleCellInfo://拣货位采集-获取商品拣货位信息 huangb 20160525
					msgRes=getArticleCellInfo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_getCheckArticleCell://拣货位采集-判断商品储位的合法性 huangb 20160526
					msgRes=getCheckArticleCell(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_tscDeleteArticleCell://拣货位采集-删除当前储位与原有商品的对应关系 huangb 20160526
					msgRes=tscDeleteArticleCell(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_BM_tscSaveArticleCell://拣货位采集-保存当前储位与商品的对应关系 huangb 20160526
					msgRes=tscSaveArticleCell(strRecvData);	
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
	private MsgRes ArticleQpaletteBarcodeInput(String strRecvData) throws Exception {
		IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");		
		return (MsgRes) bdef_DefarticleImpl.ArticleQpaletteBarcodeInput(strRecvData);
	}
	private MsgRes GetPackingInfoByArticleNo(String strRecvData) throws Exception {
		MsgRes msg=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");
		msg=bdef_DefarticleImpl.GetArticlePacking(reqMod.getReqObj());
		List<AnsPackingModel> list=(List<AnsPackingModel>) msg.getObj();
		msg.setObj(JSON.toJSONString(list));
		return msg;
	}	
	private MsgRes updateArticleQpalette(String strRecvData) throws Exception {
		IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");		
		return (MsgRes) bdef_DefarticleImpl.updateArticleQpalette(strRecvData);
	}	
	//新增商品包装/条码
	private MsgRes InsertArticleQpalette(String strRecvData) throws Exception {
		IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");		
		return (MsgRes) bdef_DefarticleImpl.InsertArticleQpalette(strRecvData);
	}	
	//商品编码扫描
		private MsgRes InsertArticleNOQpalette(String strRecvData) throws Exception {
			IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");		
			return (MsgRes) bdef_DefarticleImpl.InsertArticleNOQpalette(strRecvData);
		}	
	
	//堆叠采集》扫描商品信息

	private MsgRes GetPackingArticleQpalette(String strRecvData) throws Exception {
		IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");		
		return (MsgRes) bdef_DefarticleImpl.GetPackingArticleQpalette(strRecvData);
	}	
	
	private MsgRes PickCellBarcodeInput(String strRecvData) throws Exception {
		ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.PickCellBarcodeInput(strRecvData);
	}	
	
	private MsgRes UpdatePickCell(String strRecvData) throws Exception {
		ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.UpdatePickCell(strRecvData);
	}


	/* Add by sunl 获取商品的所有包装数量及单位 */ 
	private MsgRes SelectArticlePackingInfo(String strRecvData) throws Exception {
		IBdef_DefarticleService bdef_DefarticleImpl = (IBdef_DefarticleService) SpringContextUtil.getBean("bdef_DefarticleImpl");		
		return (MsgRes) bdef_DefarticleImpl.SelectArticlePackingInfo(strRecvData);
	}
	
	//拣货位采集-获取商品包装，单位，规格，委托业主，商品名称 huangb 20160525
	private MsgRes getArticlePackingInfo(String strRecvData) throws Exception {
		ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.getArticlePackingInfo(strRecvData);
	}
		
	//拣货位采集-获取商品拣货位信息 huangb 20160525
	private MsgRes getArticleCellInfo(String strRecvData) throws Exception {
			ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.getArticleCellInfo(strRecvData);
	}
		
	//拣货位采集-判断商品储位的合法性 huangb 20160526
	private MsgRes getCheckArticleCell(String strRecvData) throws Exception {
		ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.getCheckArticleCell(strRecvData);
	}
		
	//拣货位采集-删除当前储位与原有商品的对应关系 huangb 20160526
    private MsgRes tscDeleteArticleCell(String strRecvData) throws Exception {
		ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.tscDeleteArticleCell(strRecvData);
	}
	    
	//拣货位采集-保存当前储位与商品的对应关系 huangb 20160526
	private MsgRes tscSaveArticleCell(String strRecvData) throws Exception {
		ICset_CellArticleService cset_CellArticleImpl = (ICset_CellArticleService) SpringContextUtil.getBean("cset_CellArticleImpl");		
		return (MsgRes) cset_CellArticleImpl.tscSaveArticleCell(strRecvData);
    }
}
