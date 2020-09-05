package com.sealinkin.cost.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CostCostListId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_CostListId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String billingType;
	private Date buildDate;
	private Integer serialNo;
	private Date beginDate;
	private Date endDate;
	private Double qty;
	private Double amount;
	private Double favourableAmount;
	private String status;
	private String flag;
	private String accountNo;
	private String checkNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String createFlag;
	private String costFlag;
	// Constructors

	/** default constructor */
	public Cost_CostListId() {
	}

	/** minimal constructor */
	public Cost_CostListId(String warehouseNo, String ownerNo,
			String billingProject, String billingType, Integer serialNo,
			String flag, String accountNo, String checkNo, String rgstName,
			Date rgstDate, String costFlag) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.billingType = billingType;
		this.serialNo = serialNo;
		this.flag = flag;
		this.accountNo = accountNo;
		this.checkNo = checkNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.costFlag = costFlag;
	}

	/** full constructor */
	public Cost_CostListId(String enterpriseNo, String warehouseNo,
			String ownerNo, String billingProject, String billingType,
			Date buildDate, Integer serialNo, Date beginDate, Date endDate,
			Double qty, Double amount, Double favourableAmount, String status,
			String flag, String accountNo, String checkNo, String rgstName,
			Date rgstDate, String updtName, Date updtDate, String createFlag,
			String costFlag) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.billingType = billingType;
		this.buildDate = buildDate;
		this.serialNo = serialNo;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.qty = qty;
		this.amount = amount;
		this.favourableAmount = favourableAmount;
		this.status = status;
		this.flag = flag;
		this.accountNo = accountNo;
		this.checkNo = checkNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.createFlag = createFlag;
		this.costFlag = costFlag;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", length = 15)
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

	@Column(name = "BILLING_PROJECT", nullable = false, length = 8)
	public String getBillingProject() {
		return this.billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BUILD_DATE", length = 7)
	public Date getBuildDate() {
		return this.buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	@Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)
	public Integer getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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

	@Column(name = "QTY", precision = 12, scale = 3)
	public Double getQty() {
		return this.qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	@Column(name = "AMOUNT", precision = 12, scale = 3)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "FAVOURABLE_AMOUNT", precision = 12, scale = 3)
	public Double getFavourableAmount() {
		return this.favourableAmount;
	}

	public void setFavourableAmount(Double favourableAmount) {
		this.favourableAmount = favourableAmount;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "FLAG", nullable = false, length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "ACCOUNT_NO", nullable = false, length = 20)
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "CHECK_NO", nullable = false, length = 20)
	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
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
	
	@Column(name = "CREATE_FLAG", length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	
	@Column(name = "COST_FLAG", nullable = false, length = 1)
	public String getCostFlag() {
		return this.costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_CostListId))
			return false;
		Cost_CostListId castOther = (Cost_CostListId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getBillingProject() == castOther.getBillingProject()) || (this
						.getBillingProject() != null
						&& castOther.getBillingProject() != null && this
						.getBillingProject().equals(
								castOther.getBillingProject())))
				&& ((this.getBillingType() == castOther.getBillingType()) || (this
						.getBillingType() != null
						&& castOther.getBillingType() != null && this
						.getBillingType().equals(castOther.getBillingType())))
				&& ((this.getBuildDate() == castOther.getBuildDate()) || (this
						.getBuildDate() != null
						&& castOther.getBuildDate() != null && this
						.getBuildDate().equals(castOther.getBuildDate())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getBeginDate() == castOther.getBeginDate()) || (this
						.getBeginDate() != null
						&& castOther.getBeginDate() != null && this
						.getBeginDate().equals(castOther.getBeginDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getQty() == castOther.getQty()) || (this.getQty() != null
						&& castOther.getQty() != null && this.getQty().equals(
						castOther.getQty())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getFavourableAmount() == castOther
						.getFavourableAmount()) || (this.getFavourableAmount() != null
						&& castOther.getFavourableAmount() != null && this
						.getFavourableAmount().equals(
								castOther.getFavourableAmount())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getAccountNo() == castOther.getAccountNo()) || (this
						.getAccountNo() != null
						&& castOther.getAccountNo() != null && this
						.getAccountNo().equals(castOther.getAccountNo())))
				&& ((this.getCheckNo() == castOther.getCheckNo()) || (this
						.getCheckNo() != null && castOther.getCheckNo() != null && this
						.getCheckNo().equals(castOther.getCheckNo())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getUpdtName() == castOther.getUpdtName()) || (this
						.getUpdtName() != null
						&& castOther.getUpdtName() != null && this
						.getUpdtName().equals(castOther.getUpdtName())))
				&& ((this.getUpdtDate() == castOther.getUpdtDate()) || (this
						.getUpdtDate() != null
						&& castOther.getUpdtDate() != null && this
						.getUpdtDate().equals(castOther.getUpdtDate())))
				&& ((this.getCreateFlag() == castOther.getCreateFlag()) || (this
						.getCreateFlag() != null
						&& castOther.getCreateFlag() != null && this
						.getCreateFlag().equals(castOther.getCreateFlag())))
			    && ((this.getCostFlag() == castOther.getCostFlag()) || (this
						.getCostFlag() != null
						&& castOther.getCostFlag() != null && this
						.getCostFlag().equals(castOther.getCostFlag())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getBillingProject() == null ? 0 : this.getBillingProject()
						.hashCode());
		result = 37
				* result
				+ (getBillingType() == null ? 0 : this.getBillingType()
						.hashCode());
		result = 37 * result
				+ (getBuildDate() == null ? 0 : this.getBuildDate().hashCode());
		result = 37 * result
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37 * result
				+ (getBeginDate() == null ? 0 : this.getBeginDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37 * result
				+ (getQty() == null ? 0 : this.getQty().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getFavourableAmount() == null ? 0 : this
						.getFavourableAmount().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getAccountNo() == null ? 0 : this.getAccountNo().hashCode());
		result = 37 * result
				+ (getCheckNo() == null ? 0 : this.getCheckNo().hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getUpdtName() == null ? 0 : this.getUpdtName().hashCode());
		result = 37 * result
				+ (getUpdtDate() == null ? 0 : this.getUpdtDate().hashCode());
		result = 37
				* result
				+ (getCreateFlag() == null ? 0 : this.getCreateFlag()
						.hashCode());
		result = 37 * result
				+ (getCostFlag() == null ? 0 : this.getCostFlag().hashCode());
		return result;
	}

}