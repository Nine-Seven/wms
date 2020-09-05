package com.sealinkin.rodata.action;


import java.util.List;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.rodata.model.Rodata_OutstockDModel;
import com.sealinkin.rodata.service.IRodata_ScanQCService;
import com.sealinkin.util.ExceptionUtil;

public class Rodata_ScanQCAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRodata_ScanQCService rodata_ScanQCImpl;
	private String strQuery;
	private String strWorker;
	private String barcode;
	private String poNo;
	private String articleNo;
	private String recedeNo;
	private String ownerNo;
	private String sacnNum;
	private String pageSql;
	private String strWheresql;
	
	//判断头档是否存在
		public void getRodata_ScanM(){
			try {
				MsgRes msg =  rodata_ScanQCImpl.getRodata_ScanM(
						super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
						super.getMdBdef_DefWorker().getWarehouseNo(),
						super.getMdBdef_DefWorker().getWorkerOwner(), 
						this.getStrQuery());
				super.writeObj(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	//获取未扫描商品
	public void getRodata_ScanD(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=this.rodata_ScanQCImpl.getRodata_ScanD(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrQuery(),getStart(),getLimit());
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取已扫描商品信息
	public void ScanDNot(){
		try{
			ExtListDataBo<Rodata_OutstockDModel> list=this.rodata_ScanQCImpl.ScanDNot(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getStrQuery(),this.getStrWorker(),
					getStart(),getLimit());
			super.writeObj(list);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//检验条码，并带出商品信息
	public void checkArticleNo(){
		try 
		{
			MsgRes msgRes = this.rodata_ScanQCImpl.checkArticleNo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					this.getBarcode(),
					this.poNo,
					super.getMdBdef_DefWorker().getWarehouseNo());	
			super.writeObj(msgRes);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void tscScan(){
		try 
		{
			MsgRes msg = this.rodata_ScanQCImpl.tscScan(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo(),
					this.getRecedeNo(),
					this.getArticleNo(),
					this.getSacnNum(),
					strWorker				
				);	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//获取原单号
	public void getPoNoQC(){
		try {
			List<ComboxBo> list=this.rodata_ScanQCImpl.getPoNoQC(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStrQuery(),
					strWheresql);
			writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	//换箱
	public void recede(){
		try 
		{
			MsgRes msg = this.rodata_ScanQCImpl.recede(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo(),
					this.getRecedeNo(),
					this.getStrWorker()				
				);	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//重扫描
	public void recedeAgain(){
		try 
		{
			MsgRes msg = this.rodata_ScanQCImpl.recedeAgain(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo(),
					this.getRecedeNo(),
					this.getStrWorker()				
				);	
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false, ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	public void setRodata_ScanQCImpl(IRodata_ScanQCService rodata_ScanQCImpl) {
		this.rodata_ScanQCImpl = rodata_ScanQCImpl;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public String getStrWorker() {
		return strWorker;
	}
	public void setStrWorker(String strWorker) {
		this.strWorker = strWorker;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}


	public String getArticleNo() {
		return articleNo;
	}


	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}


	public String getRecedeNo() {
		return recedeNo;
	}


	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}


	public String getOwnerNo() {
		return ownerNo;
	}


	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}


	public String getSacnNum() {
		return sacnNum;
	}


	public void setSacnNum(String sacnNum) {
		this.sacnNum = sacnNum;
	}
	public String getPageSql() {
		return pageSql;
	}
	public void setPageSql(String pageSql) {
		this.pageSql = pageSql;
	}
	public String getStrWheresql() {
		return strWheresql;
	}
	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}
}
