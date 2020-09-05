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
 * CostOtherSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_OTHER_SET")
public class Cost_OtherSet implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cost_OtherSetId id;
	private String costName;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String createFlag;

	// Constructors

	/** default constructor */
	public Cost_OtherSet() {
	}

	/** minimal constructor */
	public Cost_OtherSet(Cost_OtherSetId id, String costName, String rgstName,
			Date rgstDate, String createFlag) {
		this.id = id;
		this.costName = costName;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.createFlag = createFlag;
	}

	/** full constructor */
	public Cost_OtherSet(Cost_OtherSetId id, String costName, String remark,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String createFlag) {
		this.id = id;
		this.costName = costName;
		this.remark = remark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.createFlag = createFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "costNo", column = @Column(name = "COST_NO", nullable = false, length = 8)) })
	public Cost_OtherSetId getId() {
		return this.id;
	}

	public void setId(Cost_OtherSetId id) {
		this.id = id;
	}

	@Column(name = "COST_NAME", nullable = false, length = 64)
	public String getCostName() {
		return this.costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	@Column(name = "REMARK", length = 225)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
}