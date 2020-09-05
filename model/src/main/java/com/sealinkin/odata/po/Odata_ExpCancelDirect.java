package com.sealinkin.odata.po;


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
 * OdataExpCancelDirect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_EXP_CANCEL_DIRECT")
public class Odata_ExpCancelDirect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_ExpCancelDirectId id;
	private String ownerNo;
	private Date expDate;
	private String custNo;
	private String subCustNo;
	private Date operateDate;
	private String status;
	private String sourceType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_ExpCancelDirect() {
	}

	/** minimal constructor */
	public Odata_ExpCancelDirect(Odata_ExpCancelDirectId id, String ownerNo,
			Date expDate, String custNo, String subCustNo,
			Date operateDate, String status, String sourceType,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expDate = expDate;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.operateDate = operateDate;
		this.status = status;
		this.sourceType = sourceType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_ExpCancelDirect(Odata_ExpCancelDirectId id, String ownerNo,
			Date expDate, String custNo, String subCustNo,
			Date operateDate, String status, String sourceType,
			String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expDate = expDate;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.operateDate = operateDate;
		this.status = status;
		this.sourceType = sourceType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "expType", column = @Column(name = "EXP_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "expNo", column = @Column(name = "EXP_NO", nullable = false, length = 20)) })
	public Odata_ExpCancelDirectId getId() {
		return this.id;
	}

	public void setId(Odata_ExpCancelDirectId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "SUB_CUST_NO", nullable = false, length = 20)
	public String getSubCustNo() {
		return this.subCustNo;
	}

	public void setSubCustNo(String subCustNo) {
		this.subCustNo = subCustNo;
	}

	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SOURCE_TYPE", nullable = false, length = 1)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}