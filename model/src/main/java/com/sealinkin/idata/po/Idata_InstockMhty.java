package com.sealinkin.idata.po;

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
 * IdataInstockMhty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_INSTOCK_MHTY")
public class Idata_InstockMhty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_InstockMhtyId id;
	private String status;
	private String dispatchWorker;
	private Date dispatchDate;
	private String instockWorker;
	private Date instockDate;
	private String operateType;
	private String locateType;
	private String containerLocateFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Idata_InstockMhty() {
	}

	/** minimal constructor */
	public Idata_InstockMhty(Idata_InstockMhtyId id, String status,
			String dispatchWorker, Date dispatchDate, String operateType,
			String locateType, String containerLocateFlag, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.status = status;
		this.dispatchWorker = dispatchWorker;
		this.dispatchDate = dispatchDate;
		this.operateType = operateType;
		this.locateType = locateType;
		this.containerLocateFlag = containerLocateFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Idata_InstockMhty(Idata_InstockMhtyId id, String status,
			String dispatchWorker, Date dispatchDate, String instockWorker,
			Date instockDate, String operateType, String locateType,
			String containerLocateFlag, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.status = status;
		this.dispatchWorker = dispatchWorker;
		this.dispatchDate = dispatchDate;
		this.instockWorker = instockWorker;
		this.instockDate = instockDate;
		this.operateType = operateType;
		this.locateType = locateType;
		this.containerLocateFlag = containerLocateFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "instockNo", column = @Column(name = "INSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)) })
	public Idata_InstockMhtyId getId() {
		return this.id;
	}

	public void setId(Idata_InstockMhtyId id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DISPATCH_WORKER", nullable = false, length = 20)
	public String getDispatchWorker() {
		return this.dispatchWorker;
	}

	public void setDispatchWorker(String dispatchWorker) {
		this.dispatchWorker = dispatchWorker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DISPATCH_DATE", nullable = false, length = 7)
	public Date getDispatchDate() {
		return this.dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	@Column(name = "INSTOCK_WORKER", length = 20)
	public String getInstockWorker() {
		return this.instockWorker;
	}

	public void setInstockWorker(String instockWorker) {
		this.instockWorker = instockWorker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSTOCK_DATE", length = 7)
	public Date getInstockDate() {
		return this.instockDate;
	}

	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "LOCATE_TYPE", nullable = false, length = 1)
	public String getLocateType() {
		return this.locateType;
	}

	public void setLocateType(String locateType) {
		this.locateType = locateType;
	}

	@Column(name = "CONTAINER_LOCATE_FLAG", nullable = false, length = 1)
	public String getContainerLocateFlag() {
		return this.containerLocateFlag;
	}

	public void setContainerLocateFlag(String containerLocateFlag) {
		this.containerLocateFlag = containerLocateFlag;
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