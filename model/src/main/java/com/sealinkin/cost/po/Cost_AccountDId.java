package com.sealinkin.cost.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostAccountDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_AccountDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String accountGroupNo;
	private String accountNo;

	// Constructors

	/** default constructor */
	public Cost_AccountDId() {
	}

	/** full constructor */
	public Cost_AccountDId(String enterpriseNo, String warehouseNo,
			String ownerNo, String accountGroupNo, String accountNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.accountGroupNo = accountGroupNo;
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

	@Column(name = "ACCOUNT_GROUP_NO", nullable = false, length = 20)
	public String getAccountGroupNo() {
		return this.accountGroupNo;
	}

	public void setAccountGroupNo(String accountGroupNo) {
		this.accountGroupNo = accountGroupNo;
	}

	@Column(name = "ACCOUNT_NO", nullable = false, length = 5)
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
		if (!(other instanceof Cost_AccountDId))
			return false;
		Cost_AccountDId castOther = (Cost_AccountDId) other;

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
				&& ((this.getAccountGroupNo() == castOther.getAccountGroupNo()) || (this
						.getAccountGroupNo() != null
						&& castOther.getAccountGroupNo() != null && this
						.getAccountGroupNo().equals(
								castOther.getAccountGroupNo())))
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
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getAccountGroupNo() == null ? 0 : this.getAccountGroupNo()
						.hashCode());
		result = 37 * result
				+ (getAccountNo() == null ? 0 : this.getAccountNo().hashCode());
		return result;
	}

}