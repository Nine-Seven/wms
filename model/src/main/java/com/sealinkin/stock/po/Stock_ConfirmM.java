package com.sealinkin.stock.po;

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
 * StockConfirmM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_CONFIRM_M")
public class Stock_ConfirmM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stock_ConfirmMId id;
	private String planNo;
	private Date confirmDate;
	private String status;
	private BigDecimal sendFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Stock_ConfirmM() {
	}

	/** minimal constructor */
	public Stock_ConfirmM(Stock_ConfirmMId id, String planNo) {
		this.id = id;
		this.planNo = planNo;
	}

	/** full constructor */
	public Stock_ConfirmM(Stock_ConfirmMId id, String planNo, Date confirmDate,
			String status, BigDecimal sendFlag, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.planNo = planNo;
		this.confirmDate = confirmDate;
		this.status = status;
		this.sendFlag = sendFlag;
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
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "confirmNo", column = @Column(name = "CONFIRM_NO", nullable = false, length = 30)) })
	public Stock_ConfirmMId getId() {
		return this.id;
	}

	public void setId(Stock_ConfirmMId id) {
		this.id = id;
	}

	@Column(name = "PLAN_NO", nullable = false, length = 30)
	public String getPlanNo() {
		return this.planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CONFIRM_DATE", length = 7)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "STATUS", length = 3)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SEND_FLAG", precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Column(name = "RGST_NAME", length = 30)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 30)
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