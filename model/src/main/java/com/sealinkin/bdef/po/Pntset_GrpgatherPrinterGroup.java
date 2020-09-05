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
 * PntsetGrpgatherPrinterGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTSET_GRPGATHER_PRINTER_GROUP")
public class Pntset_GrpgatherPrinterGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pntset_GrpgatherPrinterGroupId id;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Pntset_GrpgatherPrinterGroup() {
	}

	/** minimal constructor */
	public Pntset_GrpgatherPrinterGroup(Pntset_GrpgatherPrinterGroupId id,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Pntset_GrpgatherPrinterGroup(Pntset_GrpgatherPrinterGroupId id,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
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
			@AttributeOverride(name = "grpgatherNo", column = @Column(name = "GRPGATHER_NO", nullable = false, length = 2)),
			@AttributeOverride(name = "printerGroupNo", column = @Column(name = "PRINTER_GROUP_NO", nullable = false, length = 20)) })
	public Pntset_GrpgatherPrinterGroupId getId() {
		return this.id;
	}

	public void setId(Pntset_GrpgatherPrinterGroupId id) {
		this.id = id;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}