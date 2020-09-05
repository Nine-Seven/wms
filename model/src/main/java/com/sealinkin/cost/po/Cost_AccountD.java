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
 * CostAccountD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_ACCOUNT_D")
public class Cost_AccountD implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_AccountDId id;
	private Short accountId;
	private String accountType;
	private Double otherCost1;
	private Double otherCost2;
	private Double otherCost3;
	private Double otherCost4;
	private Double otherCost5;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_AccountD() {
	}

	/** minimal constructor */
	public Cost_AccountD(Cost_AccountDId id, Short accountId, String accountType,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.accountId = accountId;
		this.accountType = accountType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_AccountD(Cost_AccountDId id, Short accountId, String accountType,
			Double otherCost1, Double otherCost2, Double otherCost3,
			Double otherCost4, Double otherCost5, String remark,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.accountId = accountId;
		this.accountType = accountType;
		this.otherCost1 = otherCost1;
		this.otherCost2 = otherCost2;
		this.otherCost3 = otherCost3;
		this.otherCost4 = otherCost4;
		this.otherCost5 = otherCost5;
		this.remark = remark;
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
			@AttributeOverride(name = "accountGroupNo", column = @Column(name = "ACCOUNT_GROUP_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "accountNo", column = @Column(name = "ACCOUNT_NO", nullable = false, length = 5)) })
	public Cost_AccountDId getId() {
		return this.id;
	}

	public void setId(Cost_AccountDId id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT_ID", nullable = false, precision = 4, scale = 0)
	public Short getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Short accountId) {
		this.accountId = accountId;
	}

	@Column(name = "ACCOUNT_TYPE", nullable = false, length = 3)
	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Column(name = "OTHER_COST1", precision = 12, scale = 3)
	public Double getOtherCost1() {
		return this.otherCost1;
	}

	public void setOtherCost1(Double otherCost1) {
		this.otherCost1 = otherCost1;
	}

	@Column(name = "OTHER_COST2", precision = 12, scale = 3)
	public Double getOtherCost2() {
		return this.otherCost2;
	}

	public void setOtherCost2(Double otherCost2) {
		this.otherCost2 = otherCost2;
	}

	@Column(name = "OTHER_COST3", precision = 12, scale = 3)
	public Double getOtherCost3() {
		return this.otherCost3;
	}

	public void setOtherCost3(Double otherCost3) {
		this.otherCost3 = otherCost3;
	}

	@Column(name = "OTHER_COST4", precision = 12, scale = 3)
	public Double getOtherCost4() {
		return this.otherCost4;
	}

	public void setOtherCost4(Double otherCost4) {
		this.otherCost4 = otherCost4;
	}

	@Column(name = "OTHER_COST5", precision = 12, scale = 3)
	public Double getOtherCost5() {
		return this.otherCost5;
	}

	public void setOtherCost5(Double otherCost5) {
		this.otherCost5 = otherCost5;
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

}