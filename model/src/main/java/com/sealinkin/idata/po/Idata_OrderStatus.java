package com.sealinkin.idata.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Idata_OrderStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_ORDER_STATUS")
public class Idata_OrderStatus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Idata_OrderStatusId id;
	private Date requestDate;
	private String endTime;
	private String sheetType;
	private String dockNo;
	private String lastBatch;
	private String ownerNo;
	private String supplierNo;
	private byte carsCount;
	private String SImportNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String status;

	// Constructors

	/** default constructor */
	public Idata_OrderStatus() {
	}

	/** minimal constructor */
	public Idata_OrderStatus(Idata_OrderStatusId id, String endTime,
			String ownerNo, byte carsCount, String SImportNo) {
		this.id = id;
		this.endTime = endTime;
		this.ownerNo = ownerNo;
		this.carsCount = carsCount;
		this.SImportNo = SImportNo;
	}

	/** full constructor */
	public Idata_OrderStatus(Idata_OrderStatusId id, Date requestDate,
			String endTime, String sheetType, String dockNo, String lastBatch,
			String ownerNo, String supplierNo, byte carsCount,
			String SImportNo, String rgstName, Date rgstDate, String updtName,
			Date updtDate, String status) {
		this.id = id;
		this.requestDate = requestDate;
		this.endTime = endTime;
		this.sheetType = sheetType;
		this.dockNo = dockNo;
		this.lastBatch = lastBatch;
		this.ownerNo = ownerNo;
		this.supplierNo = supplierNo;
		this.carsCount = carsCount;
		this.SImportNo = SImportNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.status = status;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "orderSerial", column = @Column(name = "ORDER_SERIAL", nullable = false, length = 20)),
			@AttributeOverride(name = "startTime", column = @Column(name = "START_TIME", nullable = false, length = 5)) })
	public Idata_OrderStatusId getId() {
		return this.id;
	}

	public void setId(Idata_OrderStatusId id) {
		this.id = id;
	}

	@Column(name = "REQUEST_DATE", length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "END_TIME", nullable = false, length = 5)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "SHEET_TYPE", length = 1)
	public String getSheetType() {
		return this.sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	@Column(name = "DOCK_NO", length = 20)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Column(name = "LAST_BATCH", length = 2)
	public String getLastBatch() {
		return this.lastBatch;
	}

	public void setLastBatch(String lastBatch) {
		this.lastBatch = lastBatch;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "SUPPLIER_NO", length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "CARS_COUNT", nullable = false, precision = 2, scale = 0)
	public byte getCarsCount() {
		return this.carsCount;
	}

	public void setCarsCount(byte carsCount) {
		this.carsCount = carsCount;
	}

	@Column(name = "S_IMPORT_NO", nullable = false, length = 20)
	public String getSImportNo() {
		return this.SImportNo;
	}

	public void setSImportNo(String SImportNo) {
		this.SImportNo = SImportNo;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}