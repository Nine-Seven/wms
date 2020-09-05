package com.sealinkin.rodata.po;

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
import javax.persistence.UniqueConstraint;

/**
 * RodataRecedeM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_RECEDE_M",  uniqueConstraints = @UniqueConstraint(columnNames = {
		"WAREHOUSE_NO", "OWNER_NO", "PO_TYPE", "PO_NO" }))
public class Rodata_RecedeM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_RecedeMId id;
	private String recedeType;
	private String poType;
	private String poNo;
	private String supplierNo;
	private String classType;
	private String untreadNo;
	private Date recedeDate;
	private String status;
	private String createFlag;
	private String errorStatus;
	private BigDecimal sendFlag;
	private String stockType;
	private String stockValue;
	private String quality;
	private String recedeRemark;
	private Date requestDate;
	private String deptNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String waveNo;
	private String orgNo;
	private String takeType;
	// Constructors

	/** default constructor */
	public Rodata_RecedeM() {
	}

	/** minimal constructor */
	public Rodata_RecedeM(Rodata_RecedeMId id, String recedeType, String poType,
			String poNo, String supplierNo, String classType, String untreadNo,
			Date recedeDate, String status, String createFlag, String takeType,
			BigDecimal sendFlag, String stockType, String stockValue,
			String deptNo, String rgstName, Date rgstDate, String waveNo,String orgNo) {
		this.id = id;
		this.recedeType = recedeType;
		this.poType = poType;
		this.poNo = poNo;
		this.supplierNo = supplierNo;
		this.classType = classType;
		this.untreadNo = untreadNo;
		this.recedeDate = recedeDate;
		this.status = status;
		this.createFlag = createFlag;
		this.sendFlag = sendFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.deptNo = deptNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.waveNo = waveNo;
		this.orgNo = orgNo;
		this.takeType =  takeType;
		
	}

	/** full constructor */
	public Rodata_RecedeM(Rodata_RecedeMId id, String recedeType, String poType,
			String poNo, String supplierNo, String classType, String untreadNo,
			Date recedeDate, String status, String createFlag,
			String errorStatus, BigDecimal sendFlag, String stockType,
			String stockValue, String quality,  String takeType,
			String recedeRemark, Date requestDate, String deptNo,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String waveNo,String orgNo) {
		this.id = id;
		this.recedeType = recedeType;
		this.poType = poType;
		this.poNo = poNo;
		this.supplierNo = supplierNo;
		this.classType = classType;
		this.untreadNo = untreadNo;
		this.recedeDate = recedeDate;
		this.status = status;
		this.createFlag = createFlag;
		this.errorStatus = errorStatus;
		this.sendFlag = sendFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.quality = quality;
		this.recedeRemark = recedeRemark;
		this.requestDate = requestDate;
		this.deptNo = deptNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.waveNo = waveNo;
		this.orgNo = orgNo;
		this.takeType =takeType;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "recedeNo", column = @Column(name = "RECEDE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)) })
	public Rodata_RecedeMId getId() {
		return this.id;
	}

	public void setId(Rodata_RecedeMId id) {
		this.id = id;
	}

	@Column(name = "RECEDE_TYPE", nullable = false, length = 2)
	public String getRecedeType() {
		return this.recedeType;
	}

	public void setRecedeType(String recedeType) {
		this.recedeType = recedeType;
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

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "CLASS_TYPE", nullable = false, length = 1)
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Column(name = "TAKE_TYPE", nullable = false, length = 1)
	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	@Column(name = "UNTREAD_NO", nullable = false, length = 20)
	public String getUntreadNo() {
		return this.untreadNo;
	}


	public void setUntreadNo(String untreadNo) {
		this.untreadNo = untreadNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECEDE_DATE", nullable = false, length = 7)
	public Date getRecedeDate() {
		return this.recedeDate;
	}

	public void setRecedeDate(Date recedeDate) {
		this.recedeDate = recedeDate;
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

	@Column(name = "ERROR_STATUS", length = 100)
	public String getErrorStatus() {
		return this.errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	@Column(name = "SEND_FLAG", nullable = false, precision = 22, scale = 0)
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

	@Column(name = "QUALITY", length = 1)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}


	@Column(name = "RECEDE_REMARK")
	public String getRecedeRemark() {
		return this.recedeRemark;
	}

	public void setRecedeRemark(String recedeRemark) {
		this.recedeRemark = recedeRemark;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "DEPT_NO", nullable = false, length = 10)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}
	@Column(name = "ORG_NO", length = 20)
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

}