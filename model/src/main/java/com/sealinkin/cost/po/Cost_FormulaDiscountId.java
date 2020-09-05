package com.sealinkin.cost.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostFormulaDiscountId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_FormulaDiscountId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String ladder;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Cost_FormulaDiscountId() {
	}

	/** full constructor */
	public Cost_FormulaDiscountId(String warehouseNo, String ownerNo,
			String billingProject, String ladder, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.ladder = ladder;
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

	@Column(name = "BILLING_PROJECT", nullable = false, length = 5)
	public String getBillingProject() {
		return this.billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}

	@Column(name = "LADDER", nullable = false, length = 3)
	public String getLadder() {
		return this.ladder;
	}

	public void setLadder(String ladder) {
		this.ladder = ladder;
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
		if (!(other instanceof Cost_FormulaDiscountId))
			return false;
		Cost_FormulaDiscountId castOther = (Cost_FormulaDiscountId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getBillingProject() == castOther.getBillingProject()) || (this
						.getBillingProject() != null
						&& castOther.getBillingProject() != null && this
						.getBillingProject().equals(
								castOther.getBillingProject())))
				&& ((this.getLadder() == castOther.getLadder()) || (this
						.getLadder() != null && castOther.getLadder() != null && this
						.getLadder().equals(castOther.getLadder())))
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
		result = 37
				* result
				+ (getBillingProject() == null ? 0 : this.getBillingProject()
						.hashCode());
		result = 37 * result
				+ (getLadder() == null ? 0 : this.getLadder().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}