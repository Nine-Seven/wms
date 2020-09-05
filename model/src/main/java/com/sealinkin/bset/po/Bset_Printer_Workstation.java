package com.sealinkin.bset.po;
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
 * BsetPrinterWorkstation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTSET_PRINTER_WORKSTATION")
public class Bset_Printer_Workstation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bset_Printer_WorkstationId id;
	private String workstationName;
	private String printerGroupNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_Printer_Workstation() {
	}

	/** minimal constructor */
	public Bset_Printer_Workstation(Bset_Printer_WorkstationId id,
			String workstationName, String printerGroupNo) {
		this.id = id;
		this.workstationName = workstationName;
		this.printerGroupNo = printerGroupNo;
	}

	/** full constructor */
	public Bset_Printer_Workstation(Bset_Printer_WorkstationId id,
			String workstationName, String printerGroupNo, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.workstationName = workstationName;
		this.printerGroupNo = printerGroupNo;
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
			@AttributeOverride(name = "workstationNo", column = @Column(name = "WORKSTATION_NO", nullable = false, length = 3)) })
	public Bset_Printer_WorkstationId getId() {
		return this.id;
	}

	public void setId(Bset_Printer_WorkstationId id) {
		this.id = id;
	}

	@Column(name = "WORKSTATION_NAME", nullable = false, length = 50)
	public String getWorkstationName() {
		return this.workstationName;
	}

	public void setWorkstationName(String workstationName) {
		this.workstationName = workstationName;
	}

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	@Column(name = "RGST_NAME", length = 10)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 10)
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