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
 * StockPlanM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_PLAN_M")
public class Stock_PlanM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stock_PlanMId id;
	private String planType;
	private String poNo;
	private Date planDate;
	private String status;
	private String createFlag;
	private BigDecimal sendFlag;
	private String orgNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String remark;
	// Constructors

	/** default constructor */
	public Stock_PlanM() {
	}

	/** minimal constructor */
	public Stock_PlanM(Stock_PlanMId id, String orgNo) {
		this.id = id;
		this.orgNo = orgNo;
	}

	/** full constructor */
	public Stock_PlanM(Stock_PlanMId id, String planType, String poNo,
			Date planDate, String status, String createFlag,
			BigDecimal sendFlag, String orgNo, String rgstName, Date rgstDate,
			String updtName, Date updtDate,String remark) {
		this.id = id;
		this.planType = planType;
		this.poNo = poNo;
		this.planDate = planDate;
		this.status = status;
		this.createFlag = createFlag;
		this.sendFlag = sendFlag;
		this.orgNo = orgNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.remark = remark;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "planNo", column = @Column(name = "PLAN_NO", nullable = false, length = 30)) })
	public Stock_PlanMId getId() {
		return this.id;
	}

	public void setId(Stock_PlanMId id) {
		this.id = id;
	}

	@Column(name = "PLAN_TYPE", length = 2)
	public String getPlanType() {
		return this.planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Column(name = "PO_NO", length = 30)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_DATE", length = 7)
	public Date getPlanDate() {
		return this.planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	@Column(name = "STATUS", length = 3)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_FLAG", length = 2)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "SEND_FLAG", precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Column(name = "ORG_NO", nullable = false, length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	@Column(name = "RGST_NAME", length = 30)
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

	@Column(name = "REMARK", length = 255)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}