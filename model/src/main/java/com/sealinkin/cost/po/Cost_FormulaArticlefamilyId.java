package com.sealinkin.cost.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CostFormulaArticlefamilyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cost_FormulaArticlefamilyId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String familyNo;
	private String billingProject;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Cost_FormulaArticlefamilyId() {
	}

	/** full constructor */
	public Cost_FormulaArticlefamilyId(String warehouseNo, String ownerNo,
			String familyNo, String billingProject, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.familyNo = familyNo;
		this.billingProject = billingProject;
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

	@Column(name = "FAMILY_NO", nullable = false, length = 10)
	public String getFamilyNo() {
		return this.familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	@Column(name = "BILLING_PROJECT", nullable = false, length = 8)
	public String getBillingProject() {
		return this.billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
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
		if (!(other instanceof Cost_FormulaArticlefamilyId))
			return false;
		Cost_FormulaArticlefamilyId castOther = (Cost_FormulaArticlefamilyId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getFamilyNo() == castOther.getFamilyNo()) || (this
						.getFamilyNo() != null
						&& castOther.getFamilyNo() != null && this
						.getFamilyNo().equals(castOther.getFamilyNo())))
				&& ((this.getBillingProject() == castOther.getBillingProject()) || (this
						.getBillingProject() != null
						&& castOther.getBillingProject() != null && this
						.getBillingProject().equals(
								castOther.getBillingProject())))
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
				+ (getFamilyNo() == null ? 0 : this.getFamilyNo().hashCode());
		result = 37
				* result
				+ (getBillingProject() == null ? 0 : this.getBillingProject()
						.hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}