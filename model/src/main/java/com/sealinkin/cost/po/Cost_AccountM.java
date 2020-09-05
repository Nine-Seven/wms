package com.sealinkin.cost.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CostAccountM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_ACCOUNT_M")
public class Cost_AccountM implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String accountGroupNo;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountCycle;
	private String balanceDay;
	private String status;
	private String remark;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Cost_AccountM() {
	}

	/** minimal constructor */
	public Cost_AccountM(String accountGroupNo, String enterpriseNo,
			String warehouseNo, String ownerNo, String accountCycle,
			String rgstName, Date rgstDate) {
		this.accountGroupNo = accountGroupNo;
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.accountCycle = accountCycle;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_AccountM(String accountGroupNo, String enterpriseNo,
			String warehouseNo, String ownerNo, String accountCycle,
			String balanceDay, String status, String remark, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.accountGroupNo = accountGroupNo;
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.accountCycle = accountCycle;
		this.balanceDay = balanceDay;
		this.status = status;
		this.remark = remark;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@Id
	@Column(name = "ACCOUNT_GROUP_NO", unique = true, nullable = false, length = 20)
	public String getAccountGroupNo() {
		return this.accountGroupNo;
	}

	public void setAccountGroupNo(String accountGroupNo) {
		this.accountGroupNo = accountGroupNo;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "ACCOUNT_CYCLE", nullable = false, length = 3)
	public String getAccountCycle() {
		return this.accountCycle;
	}

	public void setAccountCycle(String accountCycle) {
		this.accountCycle = accountCycle;
	}

	@Column(name = "BALANCE_DAY", length = 20)
	public String getBalanceDay() {
		return this.balanceDay;
	}

	public void setBalanceDay(String balanceDay) {
		this.balanceDay = balanceDay;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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