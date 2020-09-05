package com.sealinkin.idata.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DockSheet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOCK_SHEET")
public class DockSheet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private DockSheetId id;
	private short TDock;
	private short UDock;
	private short FDock;
	private short carsCount;

	// Constructors

	/** default constructor */
	public DockSheet() {
	}

	/** minimal constructor */
	public DockSheet(DockSheetId id) {
		this.id = id;
	}

	/** full constructor */
	public DockSheet(DockSheetId id, short TDock, short UDock, short FDock,
			short carsCount) {
		this.id = id;
		this.TDock = TDock;
		this.UDock = UDock;
		this.FDock = FDock;
		this.carsCount = carsCount;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 10)),
			@AttributeOverride(name = "startTime", column = @Column(name = "START_TIME", nullable = false, length = 5)),
			@AttributeOverride(name = "endTime", column = @Column(name = "END_TIME", nullable = false, length = 5)) })
	public DockSheetId getId() {
		return this.id;
	}

	public void setId(DockSheetId id) {
		this.id = id;
	}

	@Column(name = "T_DOCK", precision = 3, scale = 0)
	public short getTDock() {
		return this.TDock;
	}

	public void setTDock(short TDock) {
		this.TDock = TDock;
	}

	@Column(name = "U_DOCK", precision = 3, scale = 0)
	public short getUDock() {
		return this.UDock;
	}

	public void setUDock(short UDock) {
		this.UDock = UDock;
	}

	@Column(name = "F_DOCK", precision = 3, scale = 0)
	public short getFDock() {
		return this.FDock;
	}

	public void setFDock(short FDock) {
		this.FDock = FDock;
	}

	@Column(name = "CARS_COUNT", precision = 3, scale = 0)
	public short getCarsCount() {
		return this.carsCount;
	}

	public void setCarsCount(short carsCount) {
		this.carsCount = carsCount;
	}

}