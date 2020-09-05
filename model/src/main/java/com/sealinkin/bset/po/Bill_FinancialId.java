package com.sealinkin.bset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BillFinancialId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bill_FinancialId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingMonth;
	private String accountNo;
	private Double amount;
	private Double discountAmount;

	// Constructors

	/** default constructor */
	public Bill_FinancialId() {
	}

	/** minimal constructor */
	public Bill_FinancialId(String warehouseNo, String ownerNo,
			String billingMonth, String accountNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingMonth = billingMonth;
		this.accountNo = accountNo;
	}

	/** full constructor */
	public Bill_FinancialId(String warehouseNo, String ownerNo,
			String billingMonth, String accountNo, Double amount,
			Double discountAmount) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingMonth = billingMonth;
		this.accountNo = accountNo;
		this.amount = amount;
		this.discountAmount = discountAmount;
	}

	// Property accessors

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

	@Column(name = "BILLING_MONTH", nullable = false, length = 10)
	public String getBillingMonth() {
		return this.billingMonth;
	}

	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}

	@Column(name = "ACCOUNT_NO", nullable = false, length = 5)
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bill_FinancialId))
			return false;
		Bill_FinancialId castOther = (Bill_FinancialId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getBillingMonth() == castOther.getBillingMonth()) || (this
						.getBillingMonth() != null
						&& castOther.getBillingMonth() != null && this
						.getBillingMonth().equals(castOther.getBillingMonth())))
				&& ((this.getAccountNo() == castOther.getAccountNo()) || (this
						.getAccountNo() != null
						&& castOther.getAccountNo() != null && this
						.getAccountNo().equals(castOther.getAccountNo())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null && castOther.getAmount() != null && this
						.getAmount().equals(castOther.getAmount())))
				&& ((this.getDiscountAmount() == castOther.getDiscountAmount()) || (this
						.getDiscountAmount() != null
						&& castOther.getDiscountAmount() != null && this
						.getDiscountAmount().equals(
								castOther.getDiscountAmount())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getBillingMonth() == null ? 0 : this.getBillingMonth()
						.hashCode());
		result = 37 * result
				+ (getAccountNo() == null ? 0 : this.getAccountNo().hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getDiscountAmount() == null ? 0 : this.getDiscountAmount()
						.hashCode());
		return result;
	}

}