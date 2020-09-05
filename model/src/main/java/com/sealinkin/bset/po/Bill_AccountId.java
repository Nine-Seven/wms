package com.sealinkin.bset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BillAccountId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bill_AccountId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String accountNo;
	private String accountLadder;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Bill_AccountId() {
	}

	/** full constructor */
	public Bill_AccountId(String warehouseNo, String ownerNo, String accountNo,
			String accountLadder, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.accountNo = accountNo;
		this.accountLadder = accountLadder;
		this.enterpriseNo = enterpriseNo;
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

	@Column(name = "ACCOUNT_NO", nullable = false, length = 5)
	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	@Column(name = "ACCOUNT_LADDER", nullable = false, length = 3)
	public String getAccountLadder() {
		return this.accountLadder;
	}

	public void setAccountLadder(String accountLadder) {
		this.accountLadder = accountLadder;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bill_AccountId))
			return false;
		Bill_AccountId castOther = (Bill_AccountId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getAccountNo() == castOther.getAccountNo()) || (this
						.getAccountNo() != null
						&& castOther.getAccountNo() != null && this
						.getAccountNo().equals(castOther.getAccountNo())))
				&& ((this.getAccountLadder() == castOther.getAccountLadder()) || (this
						.getAccountLadder() != null
						&& castOther.getAccountLadder() != null && this
						.getAccountLadder()
						.equals(castOther.getAccountLadder())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getAccountNo() == null ? 0 : this.getAccountNo().hashCode());
		result = 37
				* result
				+ (getAccountLadder() == null ? 0 : this.getAccountLadder()
						.hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}