package com.sealinkin.process.impl;


import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.socket.CBizAnswerModel;
import com.sealinkin.listener.SpringContextUtil;
import com.sealinkin.odata.service.IOdata_ArrangeService;
import com.sealinkin.odata.service.IOdata_CarPlanService;
import com.sealinkin.odata.service.IOdata_DivideService;
import com.sealinkin.odata.service.IOdata_MergePalService;
import com.sealinkin.odata.service.IOdata_OutstockMReceipt;
import com.sealinkin.process.IBaseRfService;
import com.sealinkin.protocolExchange.SessionDefine;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.protocolExchange.comm.EmployeeOwnerPermissions;
import com.sealinkin.protocolExchange.odata.ArrangeBoxsRequestModel;
import com.sealinkin.protocolExchange.odata.ArrangeRequestModel;
import com.sealinkin.protocolExchange.odata.LabelCancelRequestModel;
import com.sealinkin.protocolExchange.odata.OdataExistsDLabelNoRequestModel;
import com.sealinkin.protocolExchange.odata.OdataExistsSLabelNoRequestModel;
import com.sealinkin.protocolExchange.odata.OdataGetSLabelNoArticleoRequestModel;
import com.sealinkin.protocolExchange.odata.OdataScanLabelModel;
import com.sealinkin.protocolExchange.odata.OdataContainerArrangeGetNOModel;
import com.sealinkin.protocolExchange.odata.OdataSkuLabelCancelModel;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.odata.service.IOdata_ExpCheckService;

public class OdataRfImpl implements IBaseRfService {

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
				case SessionDefine.GC_SESSION_TYPE_OM_GetArticleInfo://拣货回单  获取商品信息	
					msgRes=GetArticleInfo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_GetOutStockDateInfo://获取拣货信息
					msgRes=GetOutStockDateInfo(strRecvData);	
					break;		
					
				case SessionDefine.GC_SESSION_TYPE_OM_GetOutStockDateInfoExpNo://获取拣货信息（边拣边分）
					msgRes=GetOutStockDateInfoExpNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_GetOutStockDateExpNo: //扫下架储位（边拣边分）
					msgRes=GetOutStockDateExpNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_GetOutStockQtyExpNo://扫描标签号 获取分播信息(边检边分)	
					msgRes=GetDivideSumQty(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_OM_PickReceipt://拣货回单
					msgRes=OmPickReceipt(strRecvData);	
					break;					
				case SessionDefine.GC_SESSION_TYPE_OM_CHECKWorkerNo: //校验员工号
					msgRes=CheckWorkerNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_GetDivideSumDate://扫描标签号 获取分播信息	
					msgRes=GetDivideSumDate(strRecvData);	
					break;
				
					
				case SessionDefine.GC_SESSION_TYPE_OM_SaveDivide://分播保存
					msgRes=SaveDivide(strRecvData);	
					break;						
				case SessionDefine.GC_SESSION_TYPE_OM_SaveDivideDPS://分播保存
					msgRes=SaveDivideDPS(strRecvData);	
					break;					
				case SessionDefine.GC_SESSION_TYPE_OM_GetNO://分播取号
					msgRes=GetDivideNO(strRecvData);	
					break;	
					
				case SessionDefine.GC_SESSION_TYPE_OM_ARRANGE_ExistsSLabelNo://检查源容器
					msgRes=OmArrangeExistsSLabelNo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_ARRANGE_ExistsDLabelNo://检查目的容器
					msgRes=OmArrangeExistsDLabelNo(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_ARRANGE_GetSLabelNoArticle://获取源容器商品信息
					msgRes=OmArrangeGetSLabelNoArticle(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_Arrange://容器整理
					msgRes=OmArrange(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_ArrangeBoxs://整箱转移
					msgRes=OmArrangeBoxs(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_GetLoadproposeInfo://获取装车信息
					msgRes=GetLoadproposeInfo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_InsertLoadproposeM://新增装车主挡
					msgRes=InsertLoadproposeM(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_InsertLoadproposeD://新增装车明细
					msgRes=InsertLoadproposeD(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_SaveDeliver://封车
					msgRes=SaveDeliver(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_ARRANGECONFIRM_ScanLabel://整理确认-检查
					msgRes=pOM_AerrangeConfirmScanLabel(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_ARRANGECONFIRM://整理确认
					msgRes=tscPOM_AerrangeConfirm(strRecvData);					break;
				
				case SessionDefine.GC_SESSION_TYPE_OM_CHECKCUSTNO://检验客户
					msgRes=tscPOM_AerrangeConfirmCheckCustNo(strRecvData);
					break;		
				case SessionDefine.GC_SESSION_TYPE_OM_Check_LABEL_CANCEL://检查标签是否可销毁
					msgRes=tscCheckLableCancel(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LABEL_CANCEL://标签销毁
					msgRes=tscLableCancel(strRecvData);
					break;			
				case SessionDefine.GC_SESSION_TYPE_OM_CheckSku_LABEL_CANCEL://检查SKU标签是否可销毁
					msgRes=tscCheckSkuLableCancel(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_ScanSkuCancel:   //扫描SKU销毁商品
					msgRes=tscScanSkuCancel(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_ScanSkuCancelWithoutDate:   //扫描SKU销毁商品不管生产日期
					msgRes=tscScanSkuCancelWithoutDate(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_SkuLABEL_CANCEL://Sku标签销毁
					msgRes=tscSkuLableCancel(strRecvData);
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_GetLoadproposeM://天天惠获取装车建议单号，获取不到则新增装车建议单
					msgRes=tscGetLoadproposeM(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_CheckCustLabelNo://天天惠获取获校验客户/标签以及取装车客户装车信息
					msgRes=CheckCustLabelNo(strRecvData);				
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_CustClose://天天惠装车客户扫描确认
					msgRes=tscCustClose(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_CreateLoadItem://天天惠标签装车
					msgRes=tscCreateLoadItem(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_CloseCar_TTH://天天封车
					msgRes=tscCloseCarTTH(strRecvData);
					break;
					
				case SessionDefine.GC_SESSION_TYPE_OM_CHECKDOCK://校验码头号
					msgRes=CheckDock(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_PRINTDLABEL://打印目的容器
					msgRes=tscPrintDLabelNo(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_CloseCar_Save://铁越封车
					msgRes=tscCloseCar(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_CONTAINERNO://铁越装车确认
					msgRes=tscContainer(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LABEL_LABLENO: //派车单号,并校验是否可装车的过程
					msgRes=tscNotRead(strRecvData);
					break;
				case SessionDefine.GC_SESSION_TYPE_ID_DOCKNO://铁越扫码头号
					msgRes=GETDock(strRecvData);
					break;
			
			 
				//case SessionDefine.GC_SESSION_TYPE_OM_GetSelectIn: //已扫标签
					//msgRes=GETWsweep(strRecvData);
					//break;
				//case SessionDefine.GC_SESSION_TYPE_WM_GetSelectIn: //未扫标签
					//msgRes=GETNoweep(strRecvData);
					//break;
					

				case SessionDefine.GC_SESSION_TYPE_OM_GetContainerArrangeNO: //容器整理取号
					msgRes=GetContainerArrangeNO(strRecvData);
					break;
					
					
				case SessionDefine.GC_SESSION_TYPE_OM_ReceiptPicking://RF拣货回单 sunl 2016年2月25日
					msgRes=OmReceiptPicking(strRecvData);	
					break;

				case SessionDefine.GC_SESSION_TYPE_OM_CheckLabel://RF标签检查 sunl 2016年2月25日
					msgRes=OmCheckLabel(strRecvData);	
					break;
					
 
				case SessionDefine.GC_SESSION_TYPE_OM_MergePal_ScanPlateNo://RF装并板 扫板号 sunl 2016年3月1日
					msgRes=OmScanPlateNo(strRecvData);	
					break;

				case SessionDefine.GC_SESSION_TYPE_OM_GetOutStockInfo://RF拣货回单 查询下架单数据 sunl 2016年3月8日
					msgRes=OmGetOutStockInfo(strRecvData);	
					break;
					

				case SessionDefine.GC_SESSION_TYPE_OM_GetBufferNo://RF装车获取暂存区编码
					msgRes=OMGetBufferNo(strRecvData);	
					break;
				//按线路装车
				case SessionDefine.GC_SESSION_TYPE_OM_LineScanLineNo://按线路装车---扫描线路
					msgRes=OMLineScanLineNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LineScanCustNo://按线路装车---扫描客户编码/客户箱标签
					msgRes=OMLineScanCustNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LineScanAreaNo://按线路装车---扫描 暂存区编码（月台号）+输入物流箱数
					msgRes=OMLineScanAreaNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LineCloseCar://按线路装车---封车
					msgRes=OMLineCloseCar(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LineCustScanOver://按线路装车---客户扫描完成
					msgRes=OMLineCustScanOver(strRecvData);	
					break;


				case SessionDefine.GC_SESSION_TYPE_OM_SScanCustNo://小嘴普通装车模式---扫描客户、标签
					msgRes=OMSScanLabel(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_SLoadCar://小嘴普通装车模式---装车
					msgRes=OMSLoadCar(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_SCloseCar://小嘴普通装车模式---封车
					msgRes=OMSCloseCar(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_SCloseCust://小嘴普通装车模式---客户扫描完成
					msgRes=OMSCloseCust(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_WaitLabel://获取待整理确认的标签信息
					msgRes=GetAerrangeConfirmWaitLabel(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_LabelBoxCount://扫描板标签后，返回当前板标签下的标签个数
					msgRes=GetAerrangeConfirmLabelBoxCount(strRecvData);	
					break;
					

				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_GetPlateLabelInfo://整理确认 扫描板标签后，返回当前板标签下的波次号，配送对象，暂存区，总箱数
					msgRes=GetPlateLabelInfo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_SaveMergePalAndAerrange://自动装并板+整理确认
					msgRes=SaveMergePalAndAerrange(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_GetWaitAerrangeInfo://获取待整理的数据
					msgRes=GetWaitAerrangeInfo(strRecvData);	
					break;

				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_OutStockRegister://下架单登记
					msgRes=SaveOutStockRegister(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_AerrangeConfirm_GetOutStockArticleInfo://获取下架单所属的商品明细信息
					msgRes=GetOutStockArticleInfo(strRecvData);	
					break;
				
				case SessionDefine.GC_SESSION_TYPE_OM_Check_GetSourceExpNo://获取上位系统下传的出货单号 huangb 20160519
					msgRes=GetSourceExpNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_Check_ExpCheckScanOrder://出库扫描-整单扫描 huangb 20160520
					msgRes=tscExpCheckScanOrder(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_Check_GetArticleInfoByScan://出库扫描-整单扫描 huangb 20160520
					msgRes=GetArticleInfoByScan(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_Check_ExpCheckScanBarcode://出库扫描-商品扫描保存数据 huangb 20160523
					msgRes=tscExpCheckScanBarcode(strRecvData);	
					break;
					

				case SessionDefine.GC_SESSION_TYPE_OM_CheckClaimDock://拣货索单 校验码头号 sunl 20160526
					msgRes=tscCheckClaimDock(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_ClaimOrder://拣货索单 索单 sunl 20160526
					msgRes=tscClaimOrder(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_OM_Load_GetLoadNoByShipperNo://面单交接-根据承运商编号获取装车单号 huangb 20160606
					msgRes=tscGetLoadNoByShipperNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_Load_InserLoadDByShipper://面单交接-新增装车建议单明细 huangb 20160606
					msgRes=tscInserLoadDByShipper(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_Load_DeliverCarByShipper://面单交接-根据承运商封车 huangb 20160606
					msgRes=tscDeliverCarByShipper(strRecvData);	
					break;
					
				case SessionDefine.GC_SESSION_TYPE_OM_CheckScanDataByWave://按波次装车--扫描客户或标签 huangb 20160707
					msgRes=checkScanDataByWave(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_LoadCarByWaveNo://按波次装车  huangb 20160707
					msgRes=tscLoadCarByWaveNo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_GetCarInfo://按波次装车--获取车辆或司机信息  huangb 20160708
					msgRes=getCarInfo(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_DeliverCarByWaveNo://按波次封车  huangb 20160708
					msgRes=tscDeliverCarByWaveNo(strRecvData);	
					break;	
				

				case SessionDefine.GC_SESSION_TYPE_OM_CollectGoods_ScanPO_NO://按波次封车  huangb 20160708
					msgRes=tscCollectGoods_ScanPO_NO(strRecvData);	
					break;
				case SessionDefine.GC_SESSION_TYPE_OM_CollectGoods_ScanSourceExpNO://按波次封车  huangb 20160708
					msgRes=tscCollectGoods_ScanSourceExpNO(strRecvData);	
					break;	
					
				case SessionDefine.GC_SESSION_TYPE_OM_SaveLabelWeigh://保存标签重量  huangb 20160720
					msgRes=tscSaveLabelWeigh(strRecvData);	
					break;	
				case SessionDefine.GC_SESSION_TYPE_OM_GetLabelInfo://称重获取标签信息  huangb 20160720
					msgRes=getLabelInfo(strRecvData);	
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
	 * 拣货回单  获取商品信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetArticleInfo(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return odata_OutstockMReceiptImpl.IdataCheckBarcode(strRecvData);
	}
	/***
	 * 扫描标签号 获取分播信息	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetDivideSumDate(String strRecvData) throws Exception {
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");		
		return (MsgRes) odata_DivideImpl.queryOdataDivideD(strRecvData);
	}
	/***
	 * 根据标签号获取分播数据（边拣边分）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetDivideSumQty(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return odata_OutstockMReceiptImpl.queryOdataDivideDQty(strRecvData);
	}
	
	/***
	 * 分播保存
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes SaveDivide(String strRecvData) throws Exception {		
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");
		return odata_DivideImpl.tscSaveDivide(strRecvData);
	} 
	
	/***
	 * 分播保存dps
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes SaveDivideDPS(String strRecvData) throws Exception {		
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");
		return odata_DivideImpl.tscSaveDivide_dps(strRecvData);
	} 
	/***
	 * 分播取号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetDivideNO(String strRecvData) throws Exception {		
		IOdata_DivideService odata_DivideImpl = (IOdata_DivideService) SpringContextUtil.getBean("odata_DivideImpl");
		return odata_DivideImpl.tscDivideGetNO(strRecvData);
	} 
	/***
	 * 扫描标签号 获取拣货回单信息	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetOutStockDateInfo(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return odata_OutstockMReceiptImpl.IdataCheckLabelNo(strRecvData);
	}
	/***
	 * 拣货回单（边拣边分）
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetOutStockDateInfoExpNo(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return odata_OutstockMReceiptImpl.IdataCheckLabelNoExpNo(strRecvData);
	}
	
	
	/***
	 * 拣货回单确认	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmPickReceipt(String strRecvData) throws Exception {		
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return (MsgRes) odata_OutstockMReceiptImpl.tscOmPickReceipt(strRecvData);
	}
	
	/***
	 * 拣货回单确认(边拣边分)	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetOutStockDateExpNo(String strRecvData) throws Exception {		
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return (MsgRes) odata_OutstockMReceiptImpl.tscOmPickTypeExpNo(strRecvData);
	}

	
	
	
	/***
	 * 校验员工号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes CheckWorkerNo(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
			return (MsgRes) odata_OutstockMReceiptImpl.CheckWorkerNo(strRecvData);
	}
	
	/***
	 * 检查源容器	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmArrangeExistsSLabelNo(String strRecvData) throws Exception {
		System.out.println(EmployeeOwnerPermissions.getEomap().get("1022"));
		OdataExistsSLabelNoRequestModel reqMod=JSON.parseObject(strRecvData, OdataExistsSLabelNoRequestModel.class);
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) odata_ArrangeImpl.ExistsSLabelNo(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(),
				reqMod.getSlabelNo());
	}
	

	/***
	 * 容器整理取号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetContainerArrangeNO(String strRecvData) throws Exception { 
		OdataContainerArrangeGetNOModel reqMod=JSON.parseObject(strRecvData, OdataContainerArrangeGetNOModel.class);
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) odata_ArrangeImpl.GetContainerArrangeNO(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(),
				reqMod.getSlabelNo(),reqMod.getContainerType(),reqMod.getDockNo(),reqMod.getWorkerNO());
	}
	
	/***
	 * 检查目的容器	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmArrangeExistsDLabelNo(String strRecvData) throws Exception {
		OdataExistsDLabelNoRequestModel reqMod=JSON.parseObject(strRecvData, OdataExistsDLabelNoRequestModel.class);
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) odata_ArrangeImpl.tscExistsDLabelNo(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(),
				reqMod.getSlabelNo(),
				reqMod.getDlabelNo());
	}	
	
	/***
	 * 获取源容器商品信息	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmArrangeGetSLabelNoArticle(String strRecvData) throws Exception {
		OdataGetSLabelNoArticleoRequestModel reqMod=JSON.parseObject(strRecvData, OdataGetSLabelNoArticleoRequestModel.class);
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) odata_ArrangeImpl.GetSLabelNoArticle(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(),
				reqMod.getSlabelNo(),
				reqMod.getBarcode());
	}	
	/***
	 * 容器整理	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmArrange(String strRecvData) throws Exception {
		ArrangeRequestModel reqMod=JSON.parseObject(strRecvData, ArrangeRequestModel.class);
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		//调用过程
		return (MsgRes)odata_ArrangeImpl.tscArrange(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(), 
				reqMod.getSlabelNo(), 
				reqMod.getDlabelNo(), 
				reqMod.getArticleNo(), 
				Double.parseDouble(reqMod.getPackingQty()), 
				Double.parseDouble(reqMod.getRealQty()), 
				reqMod.getImportBatchNo(), 
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
				reqMod.getRsvBatch7(), 
				reqMod.getUserId(), 
				reqMod.getTerminalFlag());			
	}	
	
	/***
	 * 整箱转移
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmArrangeBoxs(String strRecvData) throws Exception {
		ArrangeBoxsRequestModel reqMod=JSON.parseObject(strRecvData, ArrangeBoxsRequestModel.class);
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		return (MsgRes) odata_ArrangeImpl.tscOmArrangeBoxs(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(), 
				reqMod.getSlabelNo(), 
				reqMod.getDlabelNo(), 
				"N",//reqMod.getArticleNo(), 
				0.00,//Double.parseDouble(0.00), 
				0.00,//Double.parseDouble(0.00), 
				"N",//reqMod.getImportBatchNo(), 
				"N",//reqMod.getQuality(), 
				DateUtil.GetDate("19000101", "yyyyMMdd"),
				DateUtil.GetDate("19000101", "yyyyMMdd"),
				"N",//reqMod.getLotNo(), 
				"N",//reqMod.getRsvBatch1(), 
				"N",//reqMod.getRsvBatch2(), 
				"N",//reqMod.getRsvBatch3(), 
				"N",//reqMod.getRsvBatch4(), 
				"N",//reqMod.getRsvBatch5(), 
				"N",//reqMod.getRsvBatch6(), 
				"N",//reqMod.getRsvBatch7(), 
				"N",//reqMod.getRsvBatch7(), 
				reqMod.getUserId(), 
				reqMod.getTerminalFlag());
	}		
	/***
	 * 获取装车信息	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetLoadproposeInfo(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return (MsgRes) odata_CarPlanImpl.GetLoadproposeInfo(strRecvData);
	}
	
	/***
	 * 新增装车主挡	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes InsertLoadproposeM(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscRfInsertLoadproposeM(strRecvData);
	}
	
	/***
	 * 新增装车明细	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes InsertLoadproposeD(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscRfInsertLoadproposeD(strRecvData);
	}
	
	/***
	 * 封车	
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes SaveDeliver(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return  odata_CarPlanImpl.tscRfSaveDeliver(strRecvData);
	}
	
	/**
	 * 整理确认-检查
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes pOM_AerrangeConfirmScanLabel(String strRecvData)throws Exception{
		OdataScanLabelModel oslMod=JSON.parseObject(strRecvData, OdataScanLabelModel.class);
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscPOM_AerrangeConfirmScanLabel(oslMod.getEnterpriseNo(),oslMod.getWarehouseNo(), oslMod.getLabelNo(), oslMod.getWorkerNo(),oslMod.getCustNo());
		return msg;
	}
	
	/**
	 * 整理确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscPOM_AerrangeConfirm(String strRecvData)throws Exception{
		OdataScanLabelModel oslMod=JSON.parseObject(strRecvData, OdataScanLabelModel.class);
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscPOM_AerrangeConfirm(oslMod.getEnterpriseNo(),oslMod.getWarehouseNo(), oslMod.getLabelNo(), oslMod.getWorkerNo());
		return msg;
	}
	
	//检验客户
	private MsgRes tscPOM_AerrangeConfirmCheckCustNo(String strRecvData) {
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscPOM_AerrangeConfirmCheckCustNo(strRecvData);
		return msg;
	}
	/**
	 * 检查标签是否可销毁
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscCheckLableCancel(String strRecvData)throws Exception{
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscCheckLableCancel(reqMod.getEnterpriseNo(),reqMod.getWarehouseNo(), reqMod.getReqObj());
		return msg;
	}
	
	/**
	 * 标签销毁
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscLableCancel(String strRecvData)throws Exception{
		LabelCancelRequestModel reqMod=JSON.parseObject(strRecvData, LabelCancelRequestModel.class);
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscLableCancel(reqMod.getEnterpriseNo(),reqMod.getWareHouseNo(), reqMod.getLabelNo(), reqMod.getUserNo());
		return msg;
	}	
	
	/**
	 * 检查SKU标签销毁
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscCheckSkuLableCancel(String strRecvData)throws Exception{
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscCheckSkuLableCancel(reqMod.getEnterpriseNo(),reqMod.getWarehouseNo(), reqMod.getReqObj());
		return msg;
	}
	
	
	/**
	 * 扫描SKU销毁商品
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscScanSkuCancel(String strRecvData)throws Exception{
		IOdata_ArrangeService IOdata_ArrangeServiceImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) IOdata_ArrangeServiceImpl.tscScanSkuCancel(strRecvData);
	}
	
	/**
	 * 扫描SKU销毁商品不管生产日期
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscScanSkuCancelWithoutDate(String strRecvData)throws Exception{
		IOdata_ArrangeService IOdata_ArrangeServiceImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) IOdata_ArrangeServiceImpl.tscScanSkuCancelWithoutDate(strRecvData);
	}
	
	
	/**
	 * SKU标签销毁
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscSkuLableCancel(String strRecvData)throws Exception{
		OdataSkuLabelCancelModel reqMod=JSON.parseObject(strRecvData, OdataSkuLabelCancelModel.class);
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		MsgRes msg = odata_ArrangImpl.tscSkuLableCancel(reqMod);
		return msg;
	}
	
	
	
	/***
	 * 天天惠获取装车建议单号，获取不到则新增装车建议单
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscGetLoadproposeM(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscGetLoadproposeM(strRecvData);
	}
	/***
	 * 天天惠获取获校验客户/标签以及取装车客户装车信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes CheckCustLabelNo(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.CheckCustLabelNo(strRecvData);
	}
	/***
	 * 天天惠装车客户扫描确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscCustClose(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscCustClose(strRecvData);
	}
	/***
	 * 装车获取暂存区编码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OMGetBufferNo(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.OMGetBufferNo(strRecvData);
	} 
	
	/***
	 * 天天惠标签装车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscCreateLoadItem(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscCreateLoadItem(strRecvData);
	}
	/***
	 * 天天惠封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes tscCloseCarTTH(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscCloseCarTTH(strRecvData);
	}
	//校验码头
	private MsgRes CheckDock(String strRecvData) throws Exception {
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");			
		return odata_ArrangeImpl.CheckDock(strRecvData);
	}
	
	
	//打印目的容器
	private MsgRes tscPrintDLabelNo(String strRecvData) throws Exception {
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");			
		return odata_ArrangeImpl.tscPrintDLabelNo(strRecvData);
	}
	/***
	 * 铁越封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */

	private MsgRes tscCloseCar(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscCloseCarP(strRecvData);
		
}
	/***
	 * 铁越装车确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */

	private MsgRes tscContainer(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscContainer(strRecvData);
}
	/***
	 * 扫描派车单号,获取不到则新增装车建议单
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */

	private MsgRes tscNotRead(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscNotRead(strRecvData);
}
	/***
	 * 扫描码头号
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */


	private MsgRes GETDock(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.GETDock(strRecvData);
} 
	
	/***
	 * 未扫标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */

	private MsgRes GETNoweep(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.GETNoweep(strRecvData);
	}

	/***
	 * 已扫标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */

	private MsgRes GETWsweep(String strRecvData) throws Exception {
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.GETWsweep(strRecvData);
	}
	

	/***
	 * RF拣货回单 sunl 2016年2月25日
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmReceiptPicking(String strRecvData) throws Exception {		
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return (MsgRes) odata_OutstockMReceiptImpl.tscOmReceiptPicking(strRecvData);
	}

	/***
	 * RF拣货回单 sunl 2016年2月25日
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmGetOutStockInfo(String strRecvData) throws Exception {		
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return (MsgRes) odata_OutstockMReceiptImpl.tscOmGetOutStockInfo(strRecvData);
	}

	/***
	 * RF标签检查 sunl 2016年2月25日
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmCheckLabel(String strRecvData) throws Exception {		
		IOdata_ArrangeService IOdata_ArrangeServiceImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) IOdata_ArrangeServiceImpl.tscCheckLabel(strRecvData);
	}
	
 
	/***
	 * RF装并板 扫板号 sunl 2016年3月1日
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes OmScanPlateNo(String strRecvData) throws Exception {		
		IOdata_MergePalService ServiceImpl= (IOdata_MergePalService) SpringContextUtil.getBean("Odata_MergePalImpl");		
		return (MsgRes) ServiceImpl.tscScanPlateNo(strRecvData);
	} 
	
 
	/* 按线路装车---扫描线路
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMLineScanLineNo(String strRecvData)throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscLineScanLineNo(strRecvData);
	}

	/* 按线路装车---扫描客户编码/客户箱标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMLineScanCustNo(String strRecvData)throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscLineScanCustNo(strRecvData);
	}

	/* 按线路装车---扫描 暂存区编码（月台号）+输入物流箱数
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMLineScanAreaNo(String strRecvData)throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscLineScanAreaNo(strRecvData);
	}
	
	/* 按线路装车---封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMLineCloseCar(String strRecvData)throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscLineCloseCar(strRecvData);
	}
	/* 按线路装车---客户扫描完成
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMLineCustScanOver(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscLineCustScanOver(strRecvData);
	}
	
	/* 小嘴普通装车模式---客户扫描确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMSCloseCust(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscSCloseCust(strRecvData);
	}
	
	/* 小嘴普通装车模式---装车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMSLoadCar(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscSLoadCar(strRecvData);
	}
	/* 小嘴普通装车模式---扫描客户或标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMSScanLabel(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscSScanLabel(strRecvData);
	}
	/* 小嘴普通装车模式---封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	public MsgRes OMSCloseCar(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscSCloseCar(strRecvData);
	}
	
	
	/* RF容器整理 Add by sunl 2016年4月7日
	 * 扫描板标签后，返回当前板标签下的标签个数
	 */
	private MsgRes GetAerrangeConfirmLabelBoxCount(String strRecvData)throws Exception{
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		return odata_ArrangImpl.GetAerrangeConfirmLabelBoxCount(strRecvData); 
	}
	
	/* RF容器整理 Add by sunl 2016年4月7日
	 * 获取待整理确认的标签信息
	 */
	private MsgRes GetAerrangeConfirmWaitLabel(String strRecvData)throws Exception{
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		return odata_ArrangImpl.GetAerrangeConfirmWaitLabel(strRecvData); 
	}
	

	/* RF容器整理 Add by sunl 2016年4月10日
	 * 整理确认 扫描板标签后，返回当前板标签下的波次号，配送对象，暂存区，总箱数
	 */
	private MsgRes GetPlateLabelInfo(String strRecvData)throws Exception{
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		return odata_ArrangImpl.tscGetPlateLabelInfo(strRecvData); 
	}
	/* RF容器整理 Add by sunl 2016年4月10日
	 * 自动装并板+整理确认
	 */
	private MsgRes SaveMergePalAndAerrange(String strRecvData)throws Exception{
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		return odata_ArrangImpl.tscSaveMergePalAndAerrange(strRecvData); 
	}

	/* RF容器整理 Add by sunl 2016年4月10日
	 * 获取待整理的数据
	 */
	private MsgRes GetWaitAerrangeInfo(String strRecvData)throws Exception{
		IOdata_ArrangeService odata_ArrangImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");
		return odata_ArrangImpl.GetWaitAerrangeInfo(strRecvData); 
	}
	
	/***
	 * 下架单登记
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes SaveOutStockRegister(String strRecvData) throws Exception {		
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return (MsgRes) odata_OutstockMReceiptImpl.tscOutStockRegister(strRecvData);
	}
	
	/***
	 * 获取下架单所属的商品明细信息
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	private MsgRes GetOutStockArticleInfo(String strRecvData) throws Exception {		
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
		return (MsgRes) odata_OutstockMReceiptImpl.GetOutStockArticleInfo(strRecvData);
	}
	
	/**
	 * RF获取上位系统下传的出货单号
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes GetSourceExpNo(String strRecvData) throws Exception {		
		IOdata_ExpCheckService odata_ExpCheckService= (IOdata_ExpCheckService) SpringContextUtil.getBean("odata_ExpCheckImpl");		
		return (MsgRes) odata_ExpCheckService.GetSourceExpNo(strRecvData);
	}
	
	/**
	 * 出库扫描-整单扫描
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscExpCheckScanOrder(String strRecvData) throws Exception {		
		IOdata_ExpCheckService odata_ExpCheckService= (IOdata_ExpCheckService) SpringContextUtil.getBean("odata_ExpCheckImpl");		
		return (MsgRes) odata_ExpCheckService.tscExpCheckScanOrder(strRecvData);
	}
	
	/**
	 * 出库扫描-商品条码扫描
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes GetArticleInfoByScan(String strRecvData) throws Exception {		
		IOdata_ExpCheckService odata_ExpCheckService= (IOdata_ExpCheckService) SpringContextUtil.getBean("odata_ExpCheckImpl");		
		return (MsgRes) odata_ExpCheckService.GetArticleInfoByScan(strRecvData);
	}
	
	/**
	 * 出库扫描-商品扫描保存数据 huangb 20160523
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscExpCheckScanBarcode(String strRecvData) throws Exception {		
		IOdata_ExpCheckService odata_ExpCheckService= (IOdata_ExpCheckService) SpringContextUtil.getBean("odata_ExpCheckImpl");		
		return (MsgRes) odata_ExpCheckService.tscExpCheckScanBarcode(strRecvData);
	}
	
	/**
	 * 拣货索单 校验码头号 sunl 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscCheckClaimDock(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
			return (MsgRes) odata_OutstockMReceiptImpl.tscCheckClaimDock(strRecvData);
	}
	/**
	 * 拣货索单 索单 sunl 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscClaimOrder(String strRecvData) throws Exception {
		IOdata_OutstockMReceipt odata_OutstockMReceiptImpl= (IOdata_OutstockMReceipt) SpringContextUtil.getBean("odata_OutstockMReceiptImpl");		
			return (MsgRes) odata_OutstockMReceiptImpl.tscClaimOrder(strRecvData);
	}
	
	/**
	 * 面单交接-根据承运商编号获取装车单号 huangb 20160606
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscGetLoadNoByShipperNo(String strRecvData) throws Exception {		
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return (MsgRes) odata_CarPlanImpl.tscGetLoadNoByShipperNo(strRecvData);
	}
	
	/**
	 * 面单交接-新增装车建议单明细 huangb 20160606
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscInserLoadDByShipper(String strRecvData) throws Exception {		
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return (MsgRes) odata_CarPlanImpl.tscInserLoadDByShipper(strRecvData);
	}
	
	/**
	 * 面单交接-根据承运商封车 huangb 20160606
	 * @param strRecvData
	 * @throws Exception
	 */
	private MsgRes tscDeliverCarByShipper(String strRecvData) throws Exception {		
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return (MsgRes) odata_CarPlanImpl.tscDeliverCarByShipper(strRecvData);
	}
	
	/* 按波次装车--扫描客户或标签
	 * huangb 20160707
	 */
	public MsgRes checkScanDataByWave(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.checkScanDataByWave(strRecvData);
	}
	
	/* 按波次装车
	 * huangb 20160707
	 */
	public MsgRes tscLoadCarByWaveNo(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscLoadCarByWaveNo(strRecvData);
	}
	
	/* 按波次装车--获取车辆或司机信息
	 * huangb 20160708
	 */
	public MsgRes getCarInfo(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.getCarInfo(strRecvData);
	}
	
	/* 按波次封车
	 * huangb 20160708
	 */
	public MsgRes tscDeliverCarByWaveNo(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscDeliverCarByWaveNo(strRecvData);
	}
	
	/* 集货作业--扫提单号
	 * sunl 20160714
	 */
	public MsgRes tscCollectGoods_ScanPO_NO(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscCollectGoods_ScanPO_NO(strRecvData);
	}
	
	/* 集货作业--扫订单号
	 * sunl 20160714
	 */
	public MsgRes tscCollectGoods_ScanSourceExpNO(String strRecvData) throws Exception{
		IOdata_CarPlanService odata_CarPlanImpl= (IOdata_CarPlanService) SpringContextUtil.getBean("odata_CarPlanImpl");		
		return odata_CarPlanImpl.tscCollectGoods_ScanSourceExpNO(strRecvData);
	}
	
	/**保存标签重量信息	
	 * haungb 20160720
	 */
	private MsgRes tscSaveLabelWeigh(String strRecvData) throws Exception {
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) odata_ArrangeImpl.tscSaveLabelWeigh(strRecvData);
	}
	
	/**称重获取标签信息	
	 * haungb 20160720
	 */
	private MsgRes getLabelInfo(String strRecvData) throws Exception {
		IOdata_ArrangeService odata_ArrangeImpl= (IOdata_ArrangeService) SpringContextUtil.getBean("odata_ArrangeImpl");		
		return (MsgRes) odata_ArrangeImpl.getLabelInfo(strRecvData);
	}
 
}

