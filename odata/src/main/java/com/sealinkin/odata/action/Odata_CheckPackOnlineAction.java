package com.sealinkin.odata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.odata.service.IOdata_CheckPackOnlineService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

public class Odata_CheckPackOnlineAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IOdata_CheckPackOnlineService odata_CheckPackOnlineImpl;
	private String labelNo;
	private String checkNo;
	private String str;
	private String strDlabel;
	private String strPrinterGroupNo;
	private String strWorkerNo;
	private String sacnNum;
	private String strBarcode;
	private String strDeliverObj;
	private String strAutoOutstock;
	private String strPrintWayBill;
	private String strPrintPackList;
	private String strPrintInVoice;
	private String strCheckType;//复核类型
	private String strScanNo; //扫描的单号
	private String strPackMateOut;
	
	private String strPackMeteQTY;//包材数量
	private String strPackMeteNo;//包材编码
	
	private String str2;
	
	//校验任务号或者快递面单
	public void CheckTaskNo(){
		try{	
			MsgRes msg=odata_CheckPackOnlineImpl.tscCheckTaskNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrScanNo(),
					strWorkerNo,strPrinterGroupNo,this.getStrCheckType(),strAutoOutstock);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/*//获取复核单头档配送对象的箱码
	public void getCheckPackLabel(){

		try{	
			List<Stock_LabelMModel> list=odata_CheckPackOnlineImpl.getCheckPackLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),
					this.getCheckNo());
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}*/
	
	//获取未复核明细
	public void getCheckD(){
		try{	
			List<Odata_CheckLabelDModel> list=odata_CheckPackOnlineImpl.getCheckD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getCheckNo(),this.getStrCheckType(),
					this.getStrScanNo());
			//super.writeStr(covtObjectToJson(list));
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//获取标签明细，已复核明细
	public void getStockLabel(){
		try{	
			List<Stock_LabelDModel> list=odata_CheckPackOnlineImpl.getStockLabelD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getCheckNo(),this.getStrCheckType(),
					this.getStrScanNo());
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//获取未复核单数/配送对象
	public void getUnCheckObj(){
		try{	
			MsgRes msg=odata_CheckPackOnlineImpl.getUnCheckObj(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),					
					this.getCheckNo(),this.getStrCheckType(),
					this.getStrScanNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
		
	//扫描商品条码
	public void tscCheckBarcode(){
		try{	
			MsgRes msg=odata_CheckPackOnlineImpl.tscCheckBarcode(
					getStr(),
					strCheckType,
					getStrScanNo(),
					getSacnNum(),
					getStrWorkerNo(),
					getStrPrinterGroupNo(),strPrintWayBill,strPrintPackList,strPrintInVoice);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	//转病单
	public void changeSickOrder(){
		try{	
			MsgRes msg=odata_CheckPackOnlineImpl.tscChangeSickOrder(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getCheckNo(),
					this.getStrDeliverObj(),
					this.getStrWorkerNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	
	}
	
	//封箱
	public void tscArrangeCutbox(){
		try{	
			MsgRes msg=odata_CheckPackOnlineImpl.tscArrangeCutbox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrDlabel(),
					getStrPrinterGroupNo(),
					getStrWorkerNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
//	
//	
//	////将复核一半的配送对象/单信息显示出来,并校验
//	public void tscCheckUnfinished(){
//		try{	
//			MsgRes msg=odata_CheckPackOnlineImpl.tscCheckUnfinished(
//					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
//					super.getMdBdef_DefWorker().getWarehouseNo(),
//					this.getCheckNo());
//			super.writeObj(msg);
//		}catch (Exception e) 
//		{
//			e.printStackTrace();
//			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
//		}
//	}
	
	//获取未复核列表（弹出框）
	public void getUnCheckObjList(){
		try{	
			List<Stock_LabelMModel> list=odata_CheckPackOnlineImpl.getUnCheckObjList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getCheckNo(),
					this.getStrCheckType(),
					this.getStrScanNo());
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//根据快递单号或者配送对象取布控标志
	public void getECbukong(){
		try{	
			MsgRes msg=odata_CheckPackOnlineImpl.getECbukong(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getCheckNo(),
					this.getStrDeliverObj());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//包材扣减
	public void tscOutPackMete()
	{
		try{	
			System.out.println("str2:"+this.getStr2());
			System.out.println("getLabelNo():"+this.getLabelNo());
			System.out.println("getStrPackMeteQTY():"+getStrPackMeteQTY());
			System.out.println("getStrWorkerNo():"+getStrWorkerNo());
			MsgRes msg=odata_CheckPackOnlineImpl.tscOutPackMete(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					//getStrDlabel(),
					//getStrPackMeteNo(),
					getLabelNo(),
					getStr2(),
					getStrPackMeteQTY(),
					getStrWorkerNo());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	
	public IOdata_CheckPackOnlineService getOdata_CheckPackOnlineImpl() {
		return odata_CheckPackOnlineImpl;
	}
	public void setOdata_CheckPackOnlineImpl(
			IOdata_CheckPackOnlineService odata_CheckPackOnlineImpl) {
		this.odata_CheckPackOnlineImpl = odata_CheckPackOnlineImpl;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getStrDlabel() {
		return strDlabel;
	}
	public void setStrDlabel(String strDlabel) {
		this.strDlabel = strDlabel;
	}
	public String getStrPrinterGroupNo() {
		return strPrinterGroupNo;
	}
	public void setStrPrinterGroupNo(String strPrinterGroupNo) {
		this.strPrinterGroupNo = strPrinterGroupNo;
	}
	public String getStrWorkerNo() {
		return strWorkerNo;
	}
	public void setStrWorkerNo(String strWorkerNo) {
		this.strWorkerNo = strWorkerNo;
	}
	public String getSacnNum() {
		return sacnNum;
	}
	public void setSacnNum(String sacnNum) {
		this.sacnNum = sacnNum;
	}

	public String getStrBarcode() {
		return strBarcode;
	}

	public void setStrBarcode(String strBarcode) {
		this.strBarcode = strBarcode;
	}

	public String getStrDeliverObj() {
		return strDeliverObj;
	}

	public void setStrDeliverObj(String strDeliverObj) {
		this.strDeliverObj = strDeliverObj;
	}

	public String getStrAutoOutstock() {
		return strAutoOutstock;
	}

	public void setStrAutoOutstock(String strAutoOutstock) {
		this.strAutoOutstock = strAutoOutstock;
	}

	public String getStrPrintWayBill() {
		return strPrintWayBill;
	}

	public void setStrPrintWayBill(String strPrintWayBill) {
		this.strPrintWayBill = strPrintWayBill;
	}

	public String getStrPrintPackList() {
		return strPrintPackList;
	}

	public void setStrPrintPackList(String strPrintPackList) {
		this.strPrintPackList = strPrintPackList;
	}

	public String getStrPrintInVoice() {
		return strPrintInVoice;
	}

	public void setStrPrintInVoice(String strPrintInVoice) {
		this.strPrintInVoice = strPrintInVoice;
	}

	public String getStrCheckType() {
		return strCheckType;
	}

	public void setStrCheckType(String strCheckType) {
		this.strCheckType = strCheckType;
	}

	public String getStrPackMateOut() {
		return strPackMateOut;
	}

	public void setStrPackMateOut(String strPackMateOut) {
		this.strPackMateOut = strPackMateOut;
	}

	public String getStrPackMeteQTY() {
		return strPackMeteQTY;
	}

	public void setStrPackMeteQTY(String strPackMeteQTY) {
		this.strPackMeteQTY = strPackMeteQTY;
	}

	public String getStrPackMeteNo() {
		return strPackMeteNo;
	}

	public void setStrPackMeteNo(String strPackMeteNo) {
		this.strPackMeteNo = strPackMeteNo;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStrScanNo() {
		return strScanNo;
	}

	public void setStrScanNo(String strScanNo) {
		this.strScanNo = strScanNo;
	}
		
}
