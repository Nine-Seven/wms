package com.sealinkin.sodata.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SodataWasteM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SODATA_WASTE_M")
public class Sodata_WasteM implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private Sodata_WasteMId id;
	private String ownerNo;
	private String wasteType;
	private String poType;
	private String poNo;
	private Date wasteDate;
	private Date requestDate;
	private String status;
	private String createFlag;
	private BigDecimal sendFlag;
	private String stockType;
	private String stockValue;
	private String orgNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Sodata_WasteM() {
	}

	/** minimal constructor */
	public Sodata_WasteM(Sodata_WasteMId id, String ownerNo, String wasteType,
			String poType, String poNo, Date wasteDate, Date requestDate,
			String status, String createFlag, String stockType,
			String stockValue, String orgNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.wasteType = wasteType;
		this.poType = poType;
		this.poNo = poNo;
		this.wasteDate = wasteDate;
		this.requestDate = requestDate;
		this.status = status;
		this.createFlag = createFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.orgNo = orgNo;
	}

	/** full constructor */
	public Sodata_WasteM(Sodata_WasteMId id, String ownerNo, String wasteType,
			String poType, String poNo, Date wasteDate, Date requestDate,
			String status, String createFlag, BigDecimal sendFlag,
			String stockType, String stockValue, String orgNo, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.wasteType = wasteType;
		this.poType = poType;
		this.poNo = poNo;
		this.wasteDate = wasteDate;
		this.requestDate = requestDate;
		this.status = status;
		this.createFlag = createFlag;
		this.sendFlag = sendFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.orgNo = orgNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "wasteNo", column = @Column(name = "WASTE_NO", nullable = false, length = 20)) })
	public Sodata_WasteMId getId() {
		return this.id;
	}

	public void setId(Sodata_WasteMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "WASTE_TYPE", nullable = false, length = 2)
	public String getWasteType() {
		return this.wasteType;
	}

	public void setWasteType(String wasteType) {
		this.wasteType = wasteType;
	}

	@Column(name = "PO_TYPE", nullable = false, length = 5)
	public String getPoType() {
		return this.poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WASTE_DATE", nullable = false, length = 7)
	public Date getWasteDate() {
		return this.wasteDate;
	}

	public void setWasteDate(Date wasteDate) {
		this.wasteDate = wasteDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false, length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "SEND_FLAG", precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Column(name = "STOCK_VALUE", nullable = false, length = 20)
	public String getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

	@Column(name = "ORG_NO", nullable = false, length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
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

}