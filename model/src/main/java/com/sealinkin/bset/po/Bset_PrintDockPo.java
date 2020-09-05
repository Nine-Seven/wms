package com.sealinkin.bset.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bset_PrintDockPo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PNTSET_PRINTER_DOCK")
public class Bset_PrintDockPo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Bset_PrintDockPoId id;
	private String printerGroupNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bset_PrintDockPo() {
	}

	/** minimal constructor */
	public Bset_PrintDockPo(Bset_PrintDockPoId id, String printerGroupNo,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.printerGroupNo = printerGroupNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bset_PrintDockPo(Bset_PrintDockPoId id, String printerGroupNo,
			String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.printerGroupNo = printerGroupNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "dockNo", column = @Column(name = "DOCK_NO", nullable = false, length = 3)) })
	public Bset_PrintDockPoId getId() {
		return this.id;
	}

	public void setId(Bset_PrintDockPoId id) {
		this.id = id;
	}

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 10)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

}