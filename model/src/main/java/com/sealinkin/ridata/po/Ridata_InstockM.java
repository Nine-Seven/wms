package com.sealinkin.ridata.po;

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
 * RidataInstockM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RIDATA_INSTOCK_M" )
public class Ridata_InstockM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_InstockMId id;
	private String operateType;
	private String autoLocateFlag;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Ridata_InstockM() {
	}

	/** minimal constructor */
	public Ridata_InstockM(Ridata_InstockMId id, String operateType,
			String autoLocateFlag, String status, String rgstName, Date rgstDate) {
		this.id = id;
		this.operateType = operateType;
		this.autoLocateFlag = autoLocateFlag;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Ridata_InstockM(Ridata_InstockMId id, String operateType,
			String autoLocateFlag, String status, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.operateType = operateType;
		this.autoLocateFlag = autoLocateFlag;
		this.status = status;
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
	public Ridata_InstockMId getId() {
		return this.id;
	}

	public void setId(Ridata_InstockMId id) {
		this.id = id;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "AUTO_LOCATE_FLAG", nullable = false, length = 1)
	public String getAutoLocateFlag() {
		return this.autoLocateFlag;
	}

	public void setAutoLocateFlag(String autoLocateFlag) {
		this.autoLocateFlag = autoLocateFlag;
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