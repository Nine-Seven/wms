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
 * BdefDefprinter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTDEF_DEFPRINTER")
public class Bdef_Defprinter implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_DefprinterId id;
	private String printerName;
	private String paperTypeNo;
	private String printerDriver;
	private String printerPort;
	private Boolean status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bdef_Defprinter() {
	}

	/** minimal constructor */
	public Bdef_Defprinter(Bdef_DefprinterId id, String paperTypeNo,
			Boolean status, String rgstName, Date rgstDate) {
		this.id = id;
		this.paperTypeNo = paperTypeNo;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_Defprinter(Bdef_DefprinterId id, String printerName,
			String paperTypeNo, String printerDriver, String printerPort,
			Boolean status, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.printerName = printerName;
		this.paperTypeNo = paperTypeNo;
		this.printerDriver = printerDriver;
		this.printerPort = printerPort;
		this.status = status;
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
			@AttributeOverride(name = "printerNo", column = @Column(name = "PRINTER_NO", nullable = false, length = 5)) })
	public Bdef_DefprinterId getId() {
		return this.id;
	}

	public void setId(Bdef_DefprinterId id) {
		this.id = id;
	}

	@Column(name = "PRINTER_NAME", length = 200)
	public String getPrinterName() {
		return this.printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	@Column(name = "PAPER_TYPE_NO", nullable = false, length = 1)
	public String getPaperTypeNo() {
		return this.paperTypeNo;
	}

	public void setPaperTypeNo(String paperTypeNo) {
		this.paperTypeNo = paperTypeNo;
	}

	@Column(name = "PRINTER_DRIVER", length = 10)
	public String getPrinterDriver() {
		return this.printerDriver;
	}

	public void setPrinterDriver(String printerDriver) {
		this.printerDriver = printerDriver;
	}

	@Column(name = "PRINTER_PORT", length = 10)
	public String getPrinterPort() {
		return this.printerPort;
	}

	public void setPrinterPort(String printerPort) {
		this.printerPort = printerPort;
	}

	@Column(name = "STATUS", nullable = false, precision = 1, scale = 0)
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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