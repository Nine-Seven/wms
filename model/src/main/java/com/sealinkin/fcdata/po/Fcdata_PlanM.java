package com.sealinkin.fcdata.po;

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
 * FcdataPlanM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_PLAN_M")
public class Fcdata_PlanM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fcdata_PlanMId id;
	private String ownerNo;
	private String planType;
	private String fcdataType;
	private String sourcePlanNo;
	private Date planDate;
	private Date beginDate;
	private Date endDate;
	private String status;
	private String createFlag;
	private String planRemark;
	private BigDecimal sendFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String orgNo;
	 
	// Constructors

	/** default constructor */
	public Fcdata_PlanM() {
	}

	/** minimal constructor */
	public Fcdata_PlanM(Fcdata_PlanMId id, String ownerNo, String planType,
			String fcdataType, String sourcePlanNo, Date planDate,
			String status, String createFlag, BigDecimal sendFlag,
			String rgstName, Date rgstDate,String orgNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.planType = planType;
		this.fcdataType = fcdataType;
		this.sourcePlanNo = sourcePlanNo;
		this.planDate = planDate;
		this.status = status;
		this.createFlag = createFlag;
		this.sendFlag = sendFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.orgNo = orgNo;
	}

	/** full constructor */
	public Fcdata_PlanM(Fcdata_PlanMId id, String ownerNo, String planType,
			String fcdataType, String sourcePlanNo, Date planDate,
			Date beginDate, Date endDate, String status, String createFlag,
			String planRemark, BigDecimal sendFlag, String rgstName,
			Date rgstDate, String updtName, Date updtDate,String orgNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.planType = planType;
		this.fcdataType = fcdataType;
		this.sourcePlanNo = sourcePlanNo;
		this.planDate = planDate;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.status = status;
		this.createFlag = createFlag;
		this.planRemark = planRemark;
		this.sendFlag = sendFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.orgNo = orgNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "planNo", column = @Column(name = "PLAN_NO", nullable = false, length = 20)) })
	public Fcdata_PlanMId getId() {
		return this.id;
	}

	public void setId(Fcdata_PlanMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PLAN_TYPE", nullable = false, length = 1)
	public String getPlanType() {
		return this.planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Column(name = "FCDATA_TYPE", nullable = false, length = 1)
	public String getFcdataType() {
		return this.fcdataType;
	}

	public void setFcdataType(String fcdataType) {
		this.fcdataType = fcdataType;
	}

	@Column(name = "SOURCE_PLAN_NO", nullable = false, length = 20)
	public String getSourcePlanNo() {
		return this.sourcePlanNo;
	}

	public void setSourcePlanNo(String sourcePlanNo) {
		this.sourcePlanNo = sourcePlanNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_DATE", nullable = false, length = 7)
	public Date getPlanDate() {
		return this.planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_DATE", length = 7)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	@Column(name = "PLAN_REMARK")
	public String getPlanRemark() {
		return this.planRemark;
	}

	public void setPlanRemark(String planRemark) {
		this.planRemark = planRemark;
	}

	@Column(name = "SEND_FLAG", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
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

	@Column(name = "ORG_NO", length = 20)
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	
}