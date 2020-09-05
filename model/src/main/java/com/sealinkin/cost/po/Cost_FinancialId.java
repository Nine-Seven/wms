package com.sealinkin.cost.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CostFinancialId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_FinancialId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String checkNo;
	private Double amount;
	private Double discountAmount;
	private Double realAmount;
	private Date beginDate;
	private Date endDate;
	private String flag;
	private Date buildDate;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String createFlag;
	private String costFlag;
	private String accountGroupNo;

	// Constructors

	/** default constructor */
	public Cost_FinancialId() {
	}

	/** minimal constructor */
	public Cost_FinancialId(String warehouseNo, String ownerNo,
			String accountNo, String checkNo, Date beginDate, Date endDate,
			String flag, Date buildDate, String status, String rgstName,
			Date rgstDate) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.accountNo = accountNo;
		this.checkNo = checkNo;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.flag = flag;
		this.buildDate = buildDate;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Cost_FinancialId(String enterpriseNo, String warehouseNo,
			String ownerNo, String accountNo, String checkNo, Double amount,
			Double discountAmount, Double realAmount, Date beginDate,
			Date endDate, String flag, Date buildDate, String status,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String createFlag, String costFlag, String accountGroupNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.accountNo = accountNo;
		this.checkNo = checkNo;
		this.amount = amount;
		this.discountAmount = discountAmount;
		this.realAmount = realAmount;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.flag = flag;
		this.buildDate = buildDate;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.createFlag = createFlag;
		this.costFlag = costFlag;
		this.accountGroupNo = accountGroupNo;
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

	@Column(name = "ACCOUNT_NO", nullable = false, length = 5)
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

	@Column(name = "AMOUNT", precision = 12, scale = 3)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "DISCOUNT_AMOUNT", precision = 12, scale = 3)
	public Double getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "REAL_AMOUNT", precision = 12, scale = 3)
	public Double getRealAmount() {
		return this.realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGIN_DATE", nullable = false, length = 7)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false, length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "FLAG", nullable = false, length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BUILD_DATE", nullable = false, length = 7)
	public Date getBuildDate() {
		return this.buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	@Column(name = "STATUS", nullable = false, length = 3)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "COST_FLAG", length = 1)
	public String getCostFlag() {
		return this.costFlag;
	}

	public void setCostFlag(String costFlag) {
		this.costFlag = costFlag;
	}

	@Column(name = "ACCOUNT_GROUP_NO", length = 5)
	public String getAccountGroupNo() {
		return this.accountGroupNo;
	}

	public void setAccountGroupNo(String accountGroupNo) {
		this.accountGroupNo = accountGroupNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_FinancialId))
			return false;
		Cost_FinancialId castOther = (Cost_FinancialId) other;

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
				&& ((this.getAccountNo() == castOther.getAccountNo()) || (this
						.getAccountNo() != null
						&& castOther.getAccountNo() != null && this
						.getAccountNo().equals(castOther.getAccountNo())))
				&& ((this.getCheckNo() == castOther.getCheckNo()) || (this
						.getCheckNo() != null && castOther.getCheckNo() != null && this
						.getCheckNo().equals(castOther.getCheckNo())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getDiscountAmount() == castOther.getDiscountAmount()) || (this
						.getDiscountAmount() != null
						&& castOther.getDiscountAmount() != null && this
						.getDiscountAmount().equals(
								castOther.getDiscountAmount())))
				&& ((this.getRealAmount() == castOther.getRealAmount()) || (this
						.getRealAmount() != null
						&& castOther.getRealAmount() != null && this
						.getRealAmount().equals(castOther.getRealAmount())))
				&& ((this.getBeginDate() == castOther.getBeginDate()) || (this
						.getBeginDate() != null
						&& castOther.getBeginDate() != null && this
						.getBeginDate().equals(castOther.getBeginDate())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getBuildDate() == castOther.getBuildDate()) || (this
						.getBuildDate() != null
						&& castOther.getBuildDate() != null && this
						.getBuildDate().equals(castOther.getBuildDate())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
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
						.getCostFlag().equals(castOther.getCostFlag())))
				&& ((this.getAccountGroupNo() == castOther.getAccountGroupNo()) || (this
						.getAccountGroupNo() != null
						&& castOther.getAccountGroupNo() != null && this
						.getAccountGroupNo().equals(
								castOther.getAccountGroupNo())));
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
		result = 37 * result
				+ (getAccountNo() == null ? 0 : this.getAccountNo().hashCode());
		result = 37 * result
				+ (getCheckNo() == null ? 0 : this.getCheckNo().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getDiscountAmount() == null ? 0 : this.getDiscountAmount()
						.hashCode());
		result = 37
				* result
				+ (getRealAmount() == null ? 0 : this.getRealAmount()
						.hashCode());
		result = 37 * result
				+ (getBeginDate() == null ? 0 : this.getBeginDate().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getBuildDate() == null ? 0 : this.getBuildDate().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
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
		result = 37
				* result
				+ (getAccountGroupNo() == null ? 0 : this.getAccountGroupNo()
						.hashCode());
		return result;
	}

}