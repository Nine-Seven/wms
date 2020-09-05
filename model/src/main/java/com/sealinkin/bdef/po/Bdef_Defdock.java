package com.sealinkin.bdef.po;
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
 * BdefDefdock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFDOCK")
public class Bdef_Defdock implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_DefdockId id;
	private String dockName;
	private String dockType;
	private String adjustBoard;
	private String locateType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bdef_Defdock() {
	}

	/** minimal constructor */
	public Bdef_Defdock(Bdef_DefdockId id, String dockType, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.dockType = dockType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_Defdock(Bdef_DefdockId id, String dockName, String dockType,
			String adjustBoard, String locateType, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.dockName = dockName;
		this.dockType = dockType;
		this.adjustBoard = adjustBoard;
		this.locateType = locateType;
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
			@AttributeOverride(name = "dockNo", column = @Column(name = "DOCK_NO", nullable = false, length = 3)) })
	public Bdef_DefdockId getId() {
		return this.id;
	}

	public void setId(Bdef_DefdockId id) {
		this.id = id;
	}

	@Column(name = "DOCK_NAME", length = 50)
	public String getDockName() {
		return this.dockName;
	}

	public void setDockName(String dockName) {
		this.dockName = dockName;
	}

	@Column(name = "DOCK_TYPE", nullable = false, length = 2)
	public String getDockType() {
		return this.dockType;
	}

	public void setDockType(String dockType) {
		this.dockType = dockType;
	}

	@Column(name = "ADJUST_BOARD", length = 1)
	public String getAdjustBoard() {
		return this.adjustBoard;
	}

	public void setAdjustBoard(String adjustBoard) {
		this.adjustBoard = adjustBoard;
	}

	@Column(name = "LOCATE_TYPE", length = 1)
	public String getLocateType() {
		return this.locateType;
	}

	public void setLocateType(String locateType) {
		this.locateType = locateType;
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

}