package com.sealinkin.stock.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * StockAdjM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_ADJ_M")
public class StockAdjM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StockAdjMId id;
	private String adjType;
	private String poNo;
	private Date adjDate;
	private String status;
	private String createFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private BigDecimal sendFlag;

	// Constructors

	/** default constructor */
	public StockAdjM() {
	}

	/** minimal constructor */
	public StockAdjM(StockAdjMId id, String adjType, String poNo,
			Date adjDate, String status, String createFlag,
			String rgstName, Date rgstDate, BigDecimal sendFlag) {
		this.id = id;
		this.adjType = adjType;
		this.poNo = poNo;
		this.adjDate = adjDate;
		this.status = status;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.sendFlag = sendFlag;
	}

	/** full constructor */
	public StockAdjM(StockAdjMId id, String adjType, String poNo,
			Date adjDate, String status, String createFlag,
			String rgstName, Date rgstDate, String updtName,
			Date updtDate, BigDecimal sendFlag) {
		this.id = id;
		this.adjType = adjType;
		this.poNo = poNo;
		this.adjDate = adjDate;
		this.status = status;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.sendFlag = sendFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "adjNo", column = @Column(name = "ADJ_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)) })
	public StockAdjMId getId() {
		return this.id;
	}

	public void setId(StockAdjMId id) {
		this.id = id;
	}

	@Column(name = "ADJ_TYPE", nullable = false, length = 1)
	public String getAdjType() {
		return this.adjType;
	}

	public void setAdjType(String adjType) {
		this.adjType = adjType;
	}

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Column(name = "ADJ_DATE", nullable = false, length = 7)
	public Date getAdjDate() {
		return this.adjDate;
	}

	public void setAdjDate(Date adjDate) {
		this.adjDate = adjDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
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

	@Column(name = "UPDT_NAME", length = 20)
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

	@Column(name = "SEND_FLAG", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

}