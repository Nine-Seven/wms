package com.sealinkin.cost.po;


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostBillingTypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_BillingTypeId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String billingType;

	// Constructors

	/** default constructor */
	public Cost_BillingTypeId() {
	}

	/** full constructor */
	public Cost_BillingTypeId(String enterpriseNo, String billingType) {
		this.enterpriseNo = enterpriseNo;
		this.billingType = billingType;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_BillingTypeId))
			return false;
		Cost_BillingTypeId castOther = (Cost_BillingTypeId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getBillingType() == castOther.getBillingType()) || (this
						.getBillingType() != null
						&& castOther.getBillingType() != null && this
						.getBillingType().equals(castOther.getBillingType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getBillingType() == null ? 0 : this.getBillingType()
						.hashCode());
		return result;
	}

}
