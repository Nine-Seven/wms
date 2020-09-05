package com.sealinkin.fcdata.po;
// default package

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
 * FcdataCheckM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_CHECK_M")
public class Fcdata_CheckM implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Fcdata_CheckMId id;
	private String ownerNo;
	private String planNo;
	private String requestNo;
	private Date checkDate;
	private Date requestDate;
	private String assignNo;
	private String realNo;
	private String status;
	private String checkRemark;
	private String serialNo;
	private String fcdataType;
	private String checkType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Fcdata_CheckM() {
	}

	/** minimal constructor */
	public Fcdata_CheckM(Fcdata_CheckMId id, String ownerNo, String planNo,
			String requestNo, Date checkDate, Date requestDate,
			String assignNo, String status, String serialNo, String fcdataType,
			String checkType, String rgstName, Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.planNo = planNo;
		this.requestNo = requestNo;
		this.checkDate = checkDate;
		this.requestDate = requestDate;
		this.assignNo = assignNo;
		this.status = status;
		this.serialNo = serialNo;
		this.fcdataType = fcdataType;
		this.checkType = checkType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Fcdata_CheckM(Fcdata_CheckMId id, String ownerNo, String planNo,
			String requestNo, Date checkDate, Date requestDate,
			String assignNo, String realNo, String status, String checkRemark,
			String serialNo, String fcdataType, String checkType,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.planNo = planNo;
		this.requestNo = requestNo;
		this.checkDate = checkDate;
		this.requestDate = requestDate;
		this.assignNo = assignNo;
		this.realNo = realNo;
		this.status = status;
		this.checkRemark = checkRemark;
		this.serialNo = serialNo;
		this.fcdataType = fcdataType;
		this.checkType = checkType;
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
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)) })
	public Fcdata_CheckMId getId() {
		return this.id;
	}

	public void setId(Fcdata_CheckMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PLAN_NO", nullable = false, length = 20)
	public String getPlanNo() {
		return this.planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	@Column(name = "REQUEST_NO", nullable = false, length = 20)
	public String getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE", nullable = false, length = 7)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false, length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "ASSIGN_NO", nullable = false, length = 20)
	public String getAssignNo() {
		return this.assignNo;
	}

	public void setAssignNo(String assignNo) {
		this.assignNo = assignNo;
	}

	@Column(name = "REAL_NO", length = 20)
	public String getRealNo() {
		return this.realNo;
	}

	public void setRealNo(String realNo) {
		this.realNo = realNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CHECK_REMARK")
	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	@Column(name = "SERIAL_NO", nullable = false, length = 6)
	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "FCDATA_TYPE", nullable = false, length = 1)
	public String getFcdataType() {
		return this.fcdataType;
	}

	public void setFcdataType(String fcdataType) {
		this.fcdataType = fcdataType;
	}

	@Column(name = "CHECK_TYPE", nullable = false, length = 1)
	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
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