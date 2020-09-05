package com.sealinkin.bdef.po;

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
 * BdefDefworker entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFWORKER")
public class Bdef_DefWorker implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_DefWorkerId id;
	private String workerName;
	private BigDecimal status;
	private String pwd;
	private String ownerNo;
	private String warehouseNo;
	private BigDecimal sex;
	private String tel;
	private String address;
	private String title;
	private String authorityType;
	private String workerRfid;
	private String deptName;
	private String operateStatus;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bdef_DefWorker() {
	}

	/** minimal constructor */
	public Bdef_DefWorker(Bdef_DefWorkerId id, String workerName,
			BigDecimal status, String pwd, String authorityType,
			String workerRfid) {
		this.id = id;
		this.workerName = workerName;
		this.status = status;
		this.pwd = pwd;
		this.authorityType = authorityType;
		this.workerRfid = workerRfid;
	}

	/** full constructor */
	public Bdef_DefWorker(Bdef_DefWorkerId id, String workerName,
			BigDecimal status, String pwd, String ownerNo, String warehouseNo,
			BigDecimal sex, String tel, String address, String title,
			String authorityType, String workerRfid, String deptName,
			String operateStatus, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.workerName = workerName;
		this.status = status;
		this.pwd = pwd;
		this.ownerNo = ownerNo;
		this.warehouseNo = warehouseNo;
		this.sex = sex;
		this.tel = tel;
		this.address = address;
		this.title = title;
		this.authorityType = authorityType;
		this.workerRfid = workerRfid;
		this.deptName = deptName;
		this.operateStatus = operateStatus;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "workerNo", column = @Column(name = "WORKER_NO", nullable = false, length = 10)) })
	public Bdef_DefWorkerId getId() {
		return this.id;
	}

	public void setId(Bdef_DefWorkerId id) {
		this.id = id;
	}

	@Column(name = "WORKER_NAME", nullable = false, length = 50)
	public String getWorkerName() {
		return this.workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	@Column(name = "STATUS", nullable = false, precision = 22, scale = 0)
	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Column(name = "PWD", nullable = false, length = 64)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "OWNER_NO", length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "WAREHOUSE_NO", length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "SEX", precision = 22, scale = 0)
	public BigDecimal getSex() {
		return this.sex;
	}

	public void setSex(BigDecimal sex) {
		this.sex = sex;
	}

	@Column(name = "TEL", length = 20)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "ADDRESS", length = 90)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TITLE", length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "AUTHORITY_TYPE", nullable = false, length = 1)
	public String getAuthorityType() {
		return this.authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name = "WORKER_RFID", nullable = false, length = 20)
	public String getWorkerRfid() {
		return this.workerRfid;
	}

	public void setWorkerRfid(String workerRfid) {
		this.workerRfid = workerRfid;
	}

	@Column(name = "DEPT_NAME", length = 200)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "OPERATE_STATUS", length = 1)
	public String getOperateStatus() {
		return this.operateStatus;
	}

	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}