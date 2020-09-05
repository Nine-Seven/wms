package com.sealinkin.fcdata.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Fcdata_RequestM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_REQUEST_M")
public class Fcdata_RequestM implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Fcdata_RequestMId id;
	private String ownerNo;
	private String planType;
	private String fcdataType;
	private String planNo;
	private Date requestDate;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Fcdata_RequestM() {
	}

	/** minimal constructor */
	public Fcdata_RequestM(Fcdata_RequestMId id, String ownerNo, String planType,
			String fcdataType, String planNo, Date requestDate, String status,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.planType = planType;
		this.fcdataType = fcdataType;
		this.planNo = planNo;
		this.requestDate = requestDate;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Fcdata_RequestM(Fcdata_RequestMId id, String ownerNo, String planType,
			String fcdataType, String planNo, Date requestDate, String status,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.planType = planType;
		this.fcdataType = fcdataType;
		this.planNo = planNo;
		this.requestDate = requestDate;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "requestNo", column = @Column(name = "REQUEST_NO", nullable = false, length = 20)) })
	public Fcdata_RequestMId getId() {
		return this.id;
	}

	public void setId(Fcdata_RequestMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PLAN_TYPE", nullable = false, length = 1)
	public String getPlanType() {
		return this.planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Column(name = "FCDATA_TYPE", nullable = false, length = 1)
	public String getFcdataType() {
		return this.fcdataType;
	}

	public void setFcdataType(String fcdataType) {
		this.fcdataType = fcdataType;
	}

	@Column(name = "PLAN_NO", nullable = false, length = 20)
	public String getPlanNo() {
		return this.planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

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

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}