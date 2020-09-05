package com.sealinkin.rodata.po;
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
 * Rodata_BoxM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_BOX_M")
public class Rodata_BoxM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_BoxMId id;
	private String status;
	private String recedeNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String ownerCellNo;

	// Constructors

	/** default constructor */
	public Rodata_BoxM() {
	}

	/** minimal constructor */
	public Rodata_BoxM(Rodata_BoxMId id, String status, String recedeNo,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.status = status;
		this.recedeNo = recedeNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Rodata_BoxM(Rodata_BoxMId id, String status, String recedeNo,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String ownerCellNo) {
		this.id = id;
		this.status = status;
		this.recedeNo = recedeNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.ownerCellNo = ownerCellNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "labelNo", column = @Column(name = "LABEL_NO", nullable = false, length = 24)) })
	public Rodata_BoxMId getId() {
		return this.id;
	}

	public void setId(Rodata_BoxMId id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RECEDE_NO", nullable = false, length = 20)
	public String getRecedeNo() {
		return this.recedeNo;
	}

	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
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

	@Column(name = "OWNER_CELL_NO", length = 24)
	public String getOwnerCellNo() {
		return this.ownerCellNo;
	}

	public void setOwnerCellNo(String ownerCellNo) {
		this.ownerCellNo = ownerCellNo;
	}

}