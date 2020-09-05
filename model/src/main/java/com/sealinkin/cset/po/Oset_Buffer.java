package com.sealinkin.cset.po;

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
 * OsetBuffer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OSET_BUFFER")
public class Oset_Buffer implements java.io.Serializable {

	// Fields

	private Oset_BufferId id;
	private String status;
	private Double useVolumn;
	private Double useWeight;
	private Double useBoxnum;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Oset_Buffer() {
	}

	/** minimal constructor */
	public Oset_Buffer(Oset_BufferId id, String status, String rgstName,
			Date rgstDate) {
		this.id = id;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Oset_Buffer(Oset_BufferId id, String status, Double useVolumn,
			Double useWeight, Double useBoxnum, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.status = status;
		this.useVolumn = useVolumn;
		this.useWeight = useWeight;
		this.useBoxnum = useBoxnum;
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
			@AttributeOverride(name = "bufferName", column = @Column(name = "BUFFER_NAME", nullable = false, length = 30)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaNo", column = @Column(name = "AREA_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "stockNo", column = @Column(name = "STOCK_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)) })
	public Oset_BufferId getId() {
		return this.id;
	}

	public void setId(Oset_BufferId id) {
		this.id = id;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "USE_VOLUMN", precision = 18, scale = 5)
	public Double getUseVolumn() {
		return this.useVolumn;
	}

	public void setUseVolumn(Double useVolumn) {
		this.useVolumn = useVolumn;
	}

	@Column(name = "USE_WEIGHT", precision = 18, scale = 5)
	public Double getUseWeight() {
		return this.useWeight;
	}

	public void setUseWeight(Double useWeight) {
		this.useWeight = useWeight;
	}

	@Column(name = "USE_BOXNUM", precision = 18, scale = 5)
	public Double getUseBoxnum() {
		return this.useBoxnum;
	}

	public void setUseBoxnum(Double useBoxnum) {
		this.useBoxnum = useBoxnum;
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