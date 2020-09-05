package com.sealinkin.mdata.po;

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
 * MdataPlanM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MDATA_PLAN_M" )
public class Mdata_PlanM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mdata_PlanMId id;
	private String ownerNo;
	private String sourceType;
	private String outstockType;
	private String status;
	private Date moveDate;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Mdata_PlanM() {
	}

	/** minimal constructor */
	public Mdata_PlanM(Mdata_PlanMId id, String ownerNo, String sourceType,
			String outstockType, String status, Date moveDate, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.sourceType = sourceType;
		this.outstockType = outstockType;
		this.status = status;
		this.moveDate = moveDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Mdata_PlanM(Mdata_PlanMId id, String ownerNo, String sourceType,
			String outstockType, String status, Date moveDate, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.sourceType = sourceType;
		this.outstockType = outstockType;
		this.status = status;
		this.moveDate = moveDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "planNo", column = @Column(name = "PLAN_NO", nullable = false, length = 20)) })
	public Mdata_PlanMId getId() {
		return this.id;
	}

	public void setId(Mdata_PlanMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "SOURCE_TYPE", nullable = false, length = 1)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)
	public String getOutstockType() {
		return this.outstockType;
	}

	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MOVE_DATE", nullable = false, length = 7)
	public Date getMoveDate() {
		return this.moveDate;
	}

	public void setMoveDate(Date moveDate) {
		this.moveDate = moveDate;
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