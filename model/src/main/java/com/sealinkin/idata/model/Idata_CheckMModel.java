package com.sealinkin.idata.model;

import com.sealinkin.idata.po.Idata_CheckM;

public class Idata_CheckMModel extends Idata_CheckM{
	
	private static final long serialVersionUID = 1L;
	
	private String enterpriseNo;
	private String checkNo;
	private String warehouseNo;
	private String ownerNo;
	
	private String statusText;
	private String ownerName;
	private String workerName;
	private String unloadWorker;
	private String poType;
	private String poNo;
	private String supplierName;
	private Double sumCheckQty;
	private Double sumPoQty;
	private Double sumImportQty;
	private String sendFlagText;//打印状态 huangb 20160719
	private String isDiffFlag;//是否有差异 huangb 20160815
	private String classType;
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getWarehouseNo() {
		return warehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getUnloadWorker() {
		return unloadWorker;
	}
	public void setUnloadWorker(String unloadWorker) {
		this.unloadWorker = unloadWorker;
	}
	public String getPoType() {
		return poType;
	}
	public void setPoType(String poType) {
		this.poType = poType;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Double getSumCheckQty() {
		return sumCheckQty;
	}
	public void setSumCheckQty(Double sumCheckQty) {
		this.sumCheckQty = sumCheckQty;
	}
	public Double getSumPoQty() {
		return sumPoQty;
	}
	public void setSumPoQty(Double sumPoQty) {
		this.sumPoQty = sumPoQty;
	}
	public Double getSumImportQty() {
		return sumImportQty;
	}
	public void setSumImportQty(Double sumImportQty) {
		this.sumImportQty = sumImportQty;
	}
	public String getSendFlagText() {
		return sendFlagText;
	}
	public void setSendFlagText(String sendFlagText) {
		this.sendFlagText = sendFlagText;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getIsDiffFlag() {
		return isDiffFlag;
	}
	public void setIsDiffFlag(String isDiffFlag) {
		this.isDiffFlag = isDiffFlag;
	}
	
	
}
