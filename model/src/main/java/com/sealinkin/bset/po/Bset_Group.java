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
 * BsetGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_GROUP")
public class Bset_Group implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bset_GroupId id;
	private String printerGroupName;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_Group() {
	}

	/** minimal constructor */
	public Bset_Group(Bset_GroupId id, String printerGroupName, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.printerGroupName = printerGroupName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bset_Group(Bset_GroupId id, String printerGroupName, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.printerGroupName = printerGroupName;
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
			@AttributeOverride(name = "printerGroupNo", column = @Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)) })
	public Bset_GroupId getId() {
		return this.id;
	}

	public void setId(Bset_GroupId id) {
		this.id = id;
	}

	@Column(name = "PRINTER_GROUP_NAME", nullable = false, length = 200)
	public String getPrinterGroupName() {
		return this.printerGroupName;
	}

	public void setPrinterGroupName(String printerGroupName) {
		this.printerGroupName = printerGroupName;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 10)
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