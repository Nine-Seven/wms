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
 * OdataLocateM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_LOCATE_M")
public class Odata_LocateM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_LocateMId id;
	private String ownerNo;
	private String expType;
	private String status;
	private String locateName;
	private Date locateDate;
	private String divideFlag;
	private String specifyCell;
	private Date expDate;
	private Long taskBatch;
	private String sourceType;
	private String divideDeviceType;
	private String divideCompute;
	private String createFlag;
	private String orgNo;

	// Constructors

	/** default constructor */
	public Odata_LocateM() {
	}

	/** minimal constructor */
	public Odata_LocateM(Odata_LocateMId id, String ownerNo, String expType,
			String status, String locateName, Date locateDate,
			String divideFlag, String specifyCell, Date expDate,
			Long taskBatch, String sourceType, String divideDeviceType,
			String divideCompute, String createFlag) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.status = status;
		this.locateName = locateName;
		this.locateDate = locateDate;
		this.divideFlag = divideFlag;
		this.specifyCell = specifyCell;
		this.expDate = expDate;
		this.taskBatch = taskBatch;
		this.sourceType = sourceType;
		this.divideDeviceType = divideDeviceType;
		this.divideCompute = divideCompute;
		this.createFlag = createFlag;
	}

	/** full constructor */
	public Odata_LocateM(Odata_LocateMId id, String ownerNo, String expType,
			String status, String locateName, Date locateDate,
			String divideFlag, String specifyCell, Date expDate,
			Long taskBatch, String sourceType, String divideDeviceType,
			String divideCompute, String createFlag, String orgNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.status = status;
		this.locateName = locateName;
		this.locateDate = locateDate;
		this.divideFlag = divideFlag;
		this.specifyCell = specifyCell;
		this.expDate = expDate;
		this.taskBatch = taskBatch;
		this.sourceType = sourceType;
		this.divideDeviceType = divideDeviceType;
		this.divideCompute = divideCompute;
		this.createFlag = createFlag;
		this.orgNo = orgNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "waveNo", column = @Column(name = "WAVE_NO", nullable = false, length = 20)) })
	public Odata_LocateMId getId() {
		return this.id;
	}

	public void setId(Odata_LocateMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 5)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LOCATE_NAME", nullable = false, length = 20)
	public String getLocateName() {
		return this.locateName;
	}

	public void setLocateName(String locateName) {
		this.locateName = locateName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOCATE_DATE", nullable = false, length = 7)
	public Date getLocateDate() {
		return this.locateDate;
	}

	public void setLocateDate(Date locateDate) {
		this.locateDate = locateDate;
	}

	@Column(name = "DIVIDE_FLAG", nullable = false, length = 1)
	public String getDivideFlag() {
		return this.divideFlag;
	}

	public void setDivideFlag(String divideFlag) {
		this.divideFlag = divideFlag;
	}

	@Column(name = "SPECIFY_CELL", nullable = false, length = 24)
	public String getSpecifyCell() {
		return this.specifyCell;
	}

	public void setSpecifyCell(String specifyCell) {
		this.specifyCell = specifyCell;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "TASK_BATCH", nullable = false, precision = 10, scale = 0)
	public Long getTaskBatch() {
		return this.taskBatch;
	}

	public void setTaskBatch(Long taskBatch) {
		this.taskBatch = taskBatch;
	}

	@Column(name = "SOURCE_TYPE", nullable = false, length = 1)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "DIVIDE_DEVICE_TYPE", nullable = false, length = 1)
	public String getDivideDeviceType() {
		return this.divideDeviceType;
	}

	public void setDivideDeviceType(String divideDeviceType) {
		this.divideDeviceType = divideDeviceType;
	}

	@Column(name = "DIVIDE_COMPUTE", nullable = false, length = 1)
	public String getDivideCompute() {
		return this.divideCompute;
	}

	public void setDivideCompute(String divideCompute) {
		this.divideCompute = divideCompute;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "ORG_NO", length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

}