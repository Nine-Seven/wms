package com.sealinkin.rodata.action;

import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.service.IRodata_ScanTTHService;
import com.sealinkin.util.ExceptionUtil;

public class Rodata_ScanTTHAction extends CommAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRodata_ScanTTHService rodata_ScanTTHImpl;
	private String strQuery;
	private String outstockNo;
	private String barcode;
	private String articleNo;
	private String ownerNo;
	private String scanNo;
    private String labelNo;

	//获取头档（下架单号）
	public void getRodata_ScanM(){
		try {
			List<ComboxBo> list = rodata_ScanTTHImpl.getRodata_ScanM(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					this.getStrQuery(), getStart(), getLimit());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取未复核商品信息
	public void getRodata_ScanD(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=this.rodata_ScanTTHImpl.getRodata_ScanD(this.getStrQuery(),getStart(),getLimit());
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取已复核商品信息
	public void getRodata_ScanDNot(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=this.rodata_ScanTTHImpl.
					getRodata_ScanDNot(this.getStrQuery(),getStart(),getLimit());
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//检验商品条码
	public void checkArticleNo(){
		try 
		{
			MsgRes msgRes = this.rodata_ScanTTHImpl.checkArticleNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getBarcode(),
					this.getOutstockNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());	
			super.writeObj(msgRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//检验该下架单是否扫描完成
		public void checkIsFinish(){
			try 
			{
				MsgRes msgRes = this.rodata_ScanTTHImpl.checkIsFinish(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						this.getOutstockNo(),
						super.getMdBdef_DefWorker().getWarehouseNo());	
				super.writeObj(msgRes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	//扫描商品回单
	public void saveOutstock(){
		try 
		{
			MsgRes msg = this.rodata_ScanTTHImpl.saveOutstock(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getArticleNo(),this.getOutstockNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getLabelNo());	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//保存
	public void save(){
		try 
		{
			MsgRes msg = this.rodata_ScanTTHImpl.save(
					 super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					 this.getOutstockNo(),
					 super.getMdBdef_DefWorker().getWorkerNo(),
					 super.getMdBdef_DefWorker().getWorkSpaceNo(),
					 super.getMdBdef_DefWorker().getWarehouseNo(),
					 this.getOwnerNo(),
					 this.getScanNo());	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取标签号
	public void getLoadBox(){
		try 
		{
			MsgRes msg = this.rodata_ScanTTHImpl.getLoadBox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					 this.getScanNo());	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//封箱
	public void closeBox(){
		try 
		{
			MsgRes msg = this.rodata_ScanTTHImpl.closeBox(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWorkSpaceNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo(),this.getScanNo(),this.getLabelNo());	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取标签列表
	public void getScanPackLabel(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=
					this.rodata_ScanTTHImpl.getScanPackLabel(
							super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
							super.getMdBdef_DefWorker().getWarehouseNo(),
							this.getStrQuery(),getStart(),getLimit());
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	取标签的扫描量
	 */
	public void tscRodataBoxQty()
	{
		try{	
			MsgRes msg=rodata_ScanTTHImpl.tscRodataBoxQty(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getLabelNo(),outstockNo);
			super.writeObj(msg);
		}catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	public IRodata_ScanTTHService getRodata_ScanTTHImpl() {
		return rodata_ScanTTHImpl;
	}
	public void setRodata_ScanTTHImpl(IRodata_ScanTTHService rodata_ScanTTHImpl) {
		this.rodata_ScanTTHImpl = rodata_ScanTTHImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getOutstockNo() {
		return outstockNo;
	}
	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getScanNo() {
		return scanNo;
	}
	public void setScanNo(String scanNo) {
		this.scanNo = scanNo;
	}
	public String getLabelNo() {
		return labelNo;
	}
	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}
}
