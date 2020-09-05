package com.sealinkin.oset.po;
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
 * OsetDefline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OSET_DEFLINE")
public class Oset_Defline implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Oset_DeflineId id;
	private String deliverType;
	private String transportType;
	private String lineName;
	private String lineFname;
	private String lineRemark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Oset_Defline() {
	}

	/** minimal constructor */
	public Oset_Defline(Oset_DeflineId id, String deliverType,
			String transportType, String rgstName, Date rgstDate) {
		this.id = id;
		this.deliverType = deliverType;
		this.transportType = transportType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Oset_Defline(Oset_DeflineId id, String deliverType,
			String transportType, String lineName, String lineFname,
			String lineRemark, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.deliverType = deliverType;
		this.transportType = transportType;
		this.lineName = lineName;
		this.lineFname = lineFname;
		this.lineRemark = lineRemark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "lineNo", column = @Column(name = "LINE_NO", nullable = false, length = 4)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Oset_DeflineId getId() {
		return this.id;
	}

	public void setId(Oset_DeflineId id) {
		this.id = id;
	}

	@Column(name = "DELIVER_TYPE", nullable = false, length = 1)
	public String getDeliverType() {
		return this.deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	@Column(name = "TRANSPORT_TYPE", nullable = false, length = 1)
	public String getTransportType() {
		return this.transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	@Column(name = "LINE_NAME", length = 50)
	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "LINE_FNAME", length = 100)
	public String getLineFname() {
		return this.lineFname;
	}

	public void setLineFname(String lineFname) {
		this.lineFname = lineFname;
	}

	@Column(name = "LINE_REMARK", length = 50)
	public String getLineRemark() {
		return this.lineRemark;
	}

	public void setLineRemark(String lineRemark) {
		this.lineRemark = lineRemark;
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