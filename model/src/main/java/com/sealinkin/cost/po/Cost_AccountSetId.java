package com.sealinkin.cost.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostAccountSetId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_AccountSetId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String accountNo;

	// Constructors

	/** default constructor */
	public Cost_AccountSetId() {
	}

	/** full constructor */
	public Cost_AccountSetId(String enterpriseNo, String accountNo) {
		this.enterpriseNo = enterpriseNo;
		this.accountNo = accountNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "ACCOUNT_NO", nullable = false, length = 8)
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cost_AccountSetId))
			return false;
		Cost_AccountSetId castOther = (Cost_AccountSetId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getAccountNo() == castOther.getAccountNo()) || (this
						.getAccountNo() != null
						&& castOther.getAccountNo() != null && this
						.getAccountNo().equals(castOther.getAccountNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getAccountNo() == null ? 0 : this.getAccountNo().hashCode());
		return result;
	}

}