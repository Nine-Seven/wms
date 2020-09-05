package com.sealinkin.oset.po;
// default package

import java.math.BigDecimal;
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
 * OsetLineCust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OSET_LINE_CUST")
public class Oset_LineCust implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Oset_LineCustId id;
	private Integer  lineSeqNo;
	private Double distance;
	private Double charge;
	private String tollNoArray;
	private Double speedLimit;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Oset_LineCust() {
	}

	/** minimal constructor */
	public Oset_LineCust(Oset_LineCustId id, String rgstName, Date rgstDate) {
		this.id = id;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Oset_LineCust(Oset_LineCustId id, Integer lineSeqNo,
			Double distance, Double charge, String tollNoArray,
			Double speedLimit, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.lineSeqNo = lineSeqNo;
		this.distance = distance;
		this.charge = charge;
		this.tollNoArray = tollNoArray;
		this.speedLimit = speedLimit;
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
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "lineNo", column = @Column(name = "LINE_NO", nullable = false, length = 4)) })
	public Oset_LineCustId getId() {
		return this.id;
	}

	public void setId(Oset_LineCustId id) {
		this.id = id;
	}

	@Column(name = "LINE_SEQ_NO", precision = 22, scale = 0)
	public Integer getLineSeqNo() {
		return this.lineSeqNo;
	}

	public void setLineSeqNo(Integer lineSeqNo) {
		this.lineSeqNo = lineSeqNo;
	}

	@Column(name = "DISTANCE", precision = 22, scale = 0)
	public Double getDistance() {
		return this.distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Column(name = "CHARGE", precision = 13, scale = 5)
	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Column(name = "TOLL_NO_ARRAY", length = 200)
	public String getTollNoArray() {
		return this.tollNoArray;
	}

	public void setTollNoArray(String tollNoArray) {
		this.tollNoArray = tollNoArray;
	}

	@Column(name = "SPEED_LIMIT", precision = 22, scale = 0)
	public Double getSpeedLimit() {
		return this.speedLimit;
	}

	public void setSpeedLimit(Double speedLimit) {
		this.speedLimit = speedLimit;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
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