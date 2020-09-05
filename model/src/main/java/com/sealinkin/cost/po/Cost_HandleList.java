package com.sealinkin.cost.po;

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
 * CostHandleList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_HANDLE_LIST")
public class Cost_HandleList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_HandleListId id;
	private String billingProject;
	private Date amountDate;
	private Double value;
	private String status;
	private String costFlag;
	private String sourceNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_HandleList() {
	}

	/** minimal constructor */
	public Cost_HandleList(Cost_HandleListId id, String billingProject,
			Date amountDate, Double value, String rgstName, Date rgstDate) {
		this.id = id;
		this.billingProject = billingProject;
		this.amountDate = amountDate;
		this.value = value;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_HandleList(Cost_HandleListId id, String billingProject,
			Date amountDate, Double value, String status, String costFlag,
			String sourceNo, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.billingProject = billingProject;
		this.amountDate = amountDate;
		this.value = value;
		this.status = status;
		this.costFlag = costFlag;
		this.sourceNo = sourceNo;
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
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, length = 20)) })
	public Cost_HandleListId getId() {
		return this.id;
	}

	public void setId(Cost_HandleListId id) {
		this.id = id;
	}

	@Column(name = "BILLING_PROJECT", nullable = false, length = 8)
	public String getBillingProject() {
		return this.billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AMOUNT_DATE", nullable = false, length = 7)
	public Date getAmountDate() {
		return this.amountDate;
	}

	public void setAmountDate(Date amountDate) {
		this.amountDate = amountDate;
	}

	@Column(name = "VALUE", nullable = false, precision = 12, scale = 5)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "COST_FLAG", length = 1)
	public String getCostFlag() {
		return this.costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}

	@Column(name = "SOURCE_NO", length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
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