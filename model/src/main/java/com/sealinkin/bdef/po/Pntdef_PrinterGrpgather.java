package com.sealinkin.bdef.po;

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
 * PntdefPrinterGrpgather entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTDEF_PRINTER_GRPGATHER")
public class Pntdef_PrinterGrpgather implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pntdef_PrinterGrpgatherId id;
	private String grpgatherName;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Pntdef_PrinterGrpgather() {
	}

	/** minimal constructor */
	public Pntdef_PrinterGrpgather(Pntdef_PrinterGrpgatherId id,
			String grpgatherName) {
		this.id = id;
		this.grpgatherName = grpgatherName;
	}

	/** full constructor */
	public Pntdef_PrinterGrpgather(Pntdef_PrinterGrpgatherId id,
			String grpgatherName, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.grpgatherName = grpgatherName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "grpgatherNo", column = @Column(name = "GRPGATHER_NO", nullable = false, length = 2)) })
	public Pntdef_PrinterGrpgatherId getId() {
		return this.id;
	}

	public void setId(Pntdef_PrinterGrpgatherId id) {
		this.id = id;
	}

	@Column(name = "GRPGATHER_NAME", nullable = false, length = 40)
	public String getGrpgatherName() {
		return this.grpgatherName;
	}

	public void setGrpgatherName(String grpgatherName) {
		this.grpgatherName = grpgatherName;
	}

	@Column(name = "RGST_NAME", length = 20)
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