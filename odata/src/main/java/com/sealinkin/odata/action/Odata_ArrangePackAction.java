/*
 * 出货整理打包
 * lich
 */

package com.sealinkin.odata.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.odata.service.IOdata_ArrangeService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial","unused"})
public class Odata_ArrangePackAction extends CommAction{
	
	private static final Logger logger = Logger.getLogger(Odata_ArrangePackAction.class);
	private IOdata_ArrangeService odata_ArrangeImpl;
	private MsgRes msgRes;
	private String str;
	private String strQuery;
	private String strFlag;
	private String strWheresql;
	private String strBarcode;
	private String strSlabel;
	private String strDlabel;
	private String strPrinterGroupNo;
	private String strWorkerNo;
	private Integer sacnNum;
	
	//出货整理打包》源标签号检验
	public void CheckSLabelNo()
	{
		try{	
			MsgRes msg=odata_ArrangeImpl.CheckSLabelNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(),
					this.getStrWorkerNo(),strQuery);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//出货整理打包》扫描台检验(复核公用)
	public void checkDock()
	{
		try{	
			MsgRes msg=odata_ArrangeImpl.checkDock(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/*
	 * 获得输入标签对应客户的所有标签
	 * lich
	 */
	public void getOdata_CusLabel()
	{
		try{	
			List<Stock_LabelMModel> list=odata_ArrangeImpl.getOdata_CusLabel(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),
					getStrQuery(),strPrinterGroupNo);
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/*
	 * 获取未复合商品
	 */
	public void getUnCheckLabelD(){
		try{	
			ExtListDataBo<Odata_CheckLabelDModel> list=odata_ArrangeImpl.getUnCheckLabelD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeStr(covtObjectToJson(list));
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/*
	 * 获得标签明细数据
	 * lich
	 */
	public void getStockLabelD()
	{
		try{	
			List<Stock_LabelDModel> list=odata_ArrangeImpl.getStockLabelD(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr(),
					getStrFlag());
			writeArray(list);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	/**
	 * 	出货整理打包》扫描商品条码
	 */
	public void tscCheckBarcode()
	{
		try{	
			MsgRes msg=odata_ArrangeImpl.tscCheckBarcode(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr(),
					getStrSlabel(),
					getStrDlabel(),
					this.getSacnNum(),
					this.getStrWorkerNo(),strFlag,strPrinterGroupNo);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	/**
	 * 	出货整理打包》取标签的复核量
	 */
	public void tscBoxQty()
	{
		try{	
			MsgRes msg=odata_ArrangeImpl.tscBoxQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}

	//修改复合单头档状态为10
	public void updateCheckM()
	{
		try{	
			MsgRes msg=odata_ArrangeImpl.updateCheckM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStr());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	/**
	 * 出货整理打包》封箱
	 */
	public void tscArrangeCutbox(){
		try{	
			MsgRes msg=odata_ArrangeImpl.tscArrangeCutbox(
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
	
	//回单
	public void receipt(){
		try{	
			MsgRes msg=odata_ArrangeImpl.tscReceipt(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrSlabel());
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	//重扫
	public void tscAnewScan(){
		try{	
			MsgRes msg=odata_ArrangeImpl.tscAnewScan(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					strDlabel,strWorkerNo);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	public IOdata_ArrangeService getOdata_ArrangeImpl() {
		return odata_ArrangeImpl;
	}

	public void setOdata_ArrangeImpl(IOdata_ArrangeService odata_ArrangeImpl) {
		this.odata_ArrangeImpl = odata_ArrangeImpl;
	}

	public MsgRes getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrFlag() {
		return strFlag;
	}

	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
	public String getStrBarcode() {
		return strBarcode;
	}
	public void setStrBarcode(String strBarcode) {
		this.strBarcode = strBarcode;
	}
	public String getStrSlabel() {
		return strSlabel;
	}
	public void setStrSlabel(String strSlabel) {
		this.strSlabel = strSlabel;
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
	public Integer getSacnNum() {
		return sacnNum;
	}
	public void setSacnNum(Integer sacnNum) {
		this.sacnNum = sacnNum;
	}
	
	
}
