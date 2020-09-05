package com.sealinkin.protocolExchange.idata;

import java.io.Serializable;
/**
 * 验收流水号应答 对应StuIMSerialNoAnswer
 * @author lich
 *
 */
public class SerialNoAnswerModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String WarehouseNo;
	private String SImportNo;
    private String ownerNo;
    private String supplierNo;
    private String importType;
    private String batchSerialNo;
    private String dateNow;//系统时间
    private String qualityFlag;//品质标识 huangb 20160715
    
    private String poNo;//源单号
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getSImportNo() {
		return SImportNo;
	}
	public void setSImportNo(String sImportNo) {
		SImportNo = sImportNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getImportType() {
		return importType;
	}
	public void setImportType(String importType) {
		this.importType = importType;
	}
	public String getBatchSerialNo() {
		return batchSerialNo;
	}
	public void setBatchSerialNo(String batchSerialNo) {
		this.batchSerialNo = batchSerialNo;
	}
	public String getDateNow() {
		return dateNow;
	}
	public void setDateNow(String dateNow) {
		this.dateNow = dateNow;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getQualityFlag() {
		return qualityFlag;
	}
	public void setQualityFlag(String qualityFlag) {
		this.qualityFlag = qualityFlag;
	}

}
