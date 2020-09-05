/**
 * 模块名称：拆零容器整理打包（小嘴，按客户复核）
 * 模块代码：3928
 * @author hkl
 */
package com.sealinkin.odata.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.odata.model.Odata_CheckLabelDModel;
import com.sealinkin.odata.service.IOdata_BArrangeService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.ExceptionUtil;

@SuppressWarnings({"serial","unused"})
public class Odata_BArrangePackAction extends CommAction{
	
	private static final Logger logger = Logger.getLogger(Odata_BArrangePackAction.class);
	private IOdata_BArrangeService odata_BArrangeImpl;
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
	private double strWeight;
	
	//获取未封箱明细
		public void getCheckLabelDList()
		{
			try{	
				List<Odata_CheckLabelDModel> list=odata_BArrangeImpl.getCheckLabelDList(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo());
				writeArray(list);
			}catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		//出货整理打包》源标签号检验
		public void CheckSLabelNo()
		{
			try{	
				MsgRes msg=odata_BArrangeImpl.CheckSLabelNo(super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
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
	/*
	 * 获取未复合商品
	 */
	public void getUnCheckLabelD(){
		try{	
			ExtListDataBo<Odata_CheckLabelDModel> list=odata_BArrangeImpl.getUnCheckLabelD(
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
			List<Odata_CheckLabelDModel> list=odata_BArrangeImpl.getStockLabelD(
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
	 * 	拆零出货整理打包》输入数量或者条码回车
	 */
	public void tscCheckBarcodeAndSave()
	{
		try{	
			MsgRes msg=odata_BArrangeImpl.tscCheckBarcodeAndSave(
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
	 * 	拆零出货整理打包》取标签的复核量
	 */
	public void tscBoxQty()
	{
		try{	
			MsgRes msg=odata_BArrangeImpl.tscBoxQty(
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
			MsgRes msg=odata_BArrangeImpl.updateCheckM(
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
	 * 拆零出货整理打包》封箱
	 */
	public void tscArrangeCutbox(){
		try{	
			MsgRes msg=odata_BArrangeImpl.tscArrangeCutbox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrDlabel(),
					getStrPrinterGroupNo(),
					getStrWorkerNo(),strWeight);
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
			MsgRes msg=odata_BArrangeImpl.tscReceipt(
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
			MsgRes msg=odata_BArrangeImpl.tscAnewScan(
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
	
	

	public IOdata_BArrangeService getOdata_BArrangeImpl() {
		return odata_BArrangeImpl;
	}
	public void setOdata_BArrangeImpl(IOdata_BArrangeService odata_BArrangeImpl) {
		this.odata_BArrangeImpl = odata_BArrangeImpl;
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
	public double getStrWeight() {
		return strWeight;
	}
	public void setStrWeight(double strWeight) {
		this.strWeight = strWeight;
	}
	
	
}
