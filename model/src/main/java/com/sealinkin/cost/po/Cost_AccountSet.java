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
 * CostAccountSet entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_ACCOUNT_SET")
public class Cost_AccountSet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_AccountSetId id;
	private String accountName;
	private String createFlag;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_AccountSet() {
	}

	/** minimal constructor */
	public Cost_AccountSet(Cost_AccountSetId id, String accountName,
			String createFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.accountName = accountName;
		this.createFlag = createFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_AccountSet(Cost_AccountSetId id, String accountName,
			String createFlag, String remark, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.accountName = accountName;
		this.createFlag = createFlag;
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
			@AttributeOverride(name = "accountNo", column = @Column(name = "ACCOUNT_NO", nullable = false, length = 8)) })
	public Cost_AccountSetId getId() {
		return this.id;
	}

	public void setId(Cost_AccountSetId id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT_NAME", nullable = false, length = 64)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
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