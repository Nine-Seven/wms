package com.sealinkin.bset.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BillFormulasetTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bill_FormulasetTmpId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String billingProject;
	private String projectName;
	private String billingType;
	private String billingCycle;
	private String billingFlag;
	private String billingUnit;
	private Double unitPrice;
	private Double fixedValue;
	private String valueFlag;
	private String appendCondition;
	private Double appendValue1;
	private Double appendValue2;
	private String remark;
	private String status;
	private String enterpriseNo;
	private Date endDate;
	private String balanceDay;
	private String familyNo;
	private Integer rowId;

	// Constructors

	/** default constructor */
	public Bill_FormulasetTmpId() {
	}

	/** minimal constructor */
	public Bill_FormulasetTmpId(String warehouseNo, String ownerNo,
			String billingProject, String projectName, String billingType,
			String billingCycle, String billingFlag, Double unitPrice,
			String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.projectName = projectName;
		this.billingType = billingType;
		this.billingCycle = billingCycle;
		this.billingFlag = billingFlag;
		this.unitPrice = unitPrice;
		this.enterpriseNo = enterpriseNo;
	}

	/** full constructor */
	public Bill_FormulasetTmpId(String warehouseNo, String ownerNo,
			String billingProject, String projectName, String billingType,
			String billingCycle, String billingFlag, String billingUnit,
			Double unitPrice, Double fixedValue, String valueFlag,
			String appendCondition, Double appendValue1, Double appendValue2,
			String remark, String status, String enterpriseNo, Date endDate,
			String balanceDay, String familyNo, Integer rowId) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.billingProject = billingProject;
		this.projectName = projectName;
		this.billingType = billingType;
		this.billingCycle = billingCycle;
		this.billingFlag = billingFlag;
		this.billingUnit = billingUnit;
		this.unitPrice = unitPrice;
		this.fixedValue = fixedValue;
		this.valueFlag = valueFlag;
		this.appendCondition = appendCondition;
		this.appendValue1 = appendValue1;
		this.appendValue2 = appendValue2;
		this.remark = remark;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.endDate = endDate;
		this.balanceDay = balanceDay;
		this.familyNo = familyNo;
		this.rowId = rowId;
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

	@Column(name = "BILLING_PROJECT", nullable = false, length = 8)
	public String getBillingProject() {
		return this.billingProject;
	}

	public void setBillingProject(String billingProject) {
		this.billingProject = billingProject;
	}

	@Column(name = "PROJECT_NAME", nullable = false, length = 64)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "BILLING_TYPE", nullable = false, length = 3)
	public String getBillingType() {
		return this.billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	@Column(name = "BILLING_CYCLE", nullable = false, length = 3)
	public String getBillingCycle() {
		return this.billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	@Column(name = "BILLING_FLAG", nullable = false, length = 3)
	public String getBillingFlag() {
		return this.billingFlag;
	}

	public void setBillingFlag(String billingFlag) {
		this.billingFlag = billingFlag;
	}

	@Column(name = "BILLING_UNIT", length = 3)
	public String getBillingUnit() {
		return this.billingUnit;
	}

	public void setBillingUnit(String billingUnit) {
		this.billingUnit = billingUnit;
	}

	@Column(name = "UNIT_PRICE", nullable = false, precision = 12, scale = 3)
	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "FIXED_VALUE", precision = 22, scale = 0)
	public Double getFixedValue() {
		return this.fixedValue;
	}

	public void setFixedValue(Double fixedValue) {
		this.fixedValue = fixedValue;
	}

	@Column(name = "VALUE_FLAG", length = 1)
	public String getValueFlag() {
		return this.valueFlag;
	}

	public void setValueFlag(String valueFlag) {
		this.valueFlag = valueFlag;
	}

	@Column(name = "APPEND_CONDITION", length = 1)
	public String getAppendCondition() {
		return this.appendCondition;
	}

	public void setAppendCondition(String appendCondition) {
		this.appendCondition = appendCondition;
	}

	@Column(name = "APPEND_VALUE1", precision = 12, scale = 3)
	public Double getAppendValue1() {
		return this.appendValue1;
	}

	public void setAppendValue1(Double appendValue1) {
		this.appendValue1 = appendValue1;
	}

	@Column(name = "APPEND_VALUE2", precision = 12, scale = 3)
	public Double getAppendValue2() {
		return this.appendValue2;
	}

	public void setAppendValue2(Double appendValue2) {
		this.appendValue2 = appendValue2;
	}

	@Column(name = "REMARK", length = 225)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "BALANCE_DAY", length = 20)
	public String getBalanceDay() {
		return this.balanceDay;
	}

	public void setBalanceDay(String balanceDay) {
		this.balanceDay = balanceDay;
	}

	@Column(name = "FAMILY_NO", length = 20)
	public String getFamilyNo() {
		return this.familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	@Column(name = "ROW_ID", precision = 22, scale = 0)
	public Integer getRowId() {
		return this.rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bill_FormulasetTmpId))
			return false;
		Bill_FormulasetTmpId castOther = (Bill_FormulasetTmpId) other;

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
				&& ((this.getProjectName() == castOther.getProjectName()) || (this
						.getProjectName() != null
						&& castOther.getProjectName() != null && this
						.getProjectName().equals(castOther.getProjectName())))
				&& ((this.getBillingType() == castOther.getBillingType()) || (this
						.getBillingType() != null
						&& castOther.getBillingType() != null && this
						.getBillingType().equals(castOther.getBillingType())))
				&& ((this.getBillingCycle() == castOther.getBillingCycle()) || (this
						.getBillingCycle() != null
						&& castOther.getBillingCycle() != null && this
						.getBillingCycle().equals(castOther.getBillingCycle())))
				&& ((this.getBillingFlag() == castOther.getBillingFlag()) || (this
						.getBillingFlag() != null
						&& castOther.getBillingFlag() != null && this
						.getBillingFlag().equals(castOther.getBillingFlag())))
				&& ((this.getBillingUnit() == castOther.getBillingUnit()) || (this
						.getBillingUnit() != null
						&& castOther.getBillingUnit() != null && this
						.getBillingUnit().equals(castOther.getBillingUnit())))
				&& ((this.getUnitPrice() == castOther.getUnitPrice()) || (this
						.getUnitPrice() != null
						&& castOther.getUnitPrice() != null && this
						.getUnitPrice().equals(castOther.getUnitPrice())))
				&& ((this.getFixedValue() == castOther.getFixedValue()) || (this
						.getFixedValue() != null
						&& castOther.getFixedValue() != null && this
						.getFixedValue().equals(castOther.getFixedValue())))
				&& ((this.getValueFlag() == castOther.getValueFlag()) || (this
						.getValueFlag() != null
						&& castOther.getValueFlag() != null && this
						.getValueFlag().equals(castOther.getValueFlag())))
				&& ((this.getAppendCondition() == castOther
						.getAppendCondition()) || (this.getAppendCondition() != null
						&& castOther.getAppendCondition() != null && this
						.getAppendCondition().equals(
								castOther.getAppendCondition())))
				&& ((this.getAppendValue1() == castOther.getAppendValue1()) || (this
						.getAppendValue1() != null
						&& castOther.getAppendValue1() != null && this
						.getAppendValue1().equals(castOther.getAppendValue1())))
				&& ((this.getAppendValue2() == castOther.getAppendValue2()) || (this
						.getAppendValue2() != null
						&& castOther.getAppendValue2() != null && this
						.getAppendValue2().equals(castOther.getAppendValue2())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getBalanceDay() == castOther.getBalanceDay()) || (this
						.getBalanceDay() != null
						&& castOther.getBalanceDay() != null && this
						.getBalanceDay().equals(castOther.getBalanceDay())))
				&& ((this.getFamilyNo() == castOther.getFamilyNo()) || (this
						.getFamilyNo() != null
						&& castOther.getFamilyNo() != null && this
						.getFamilyNo().equals(castOther.getFamilyNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())));
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
		result = 37
				* result
				+ (getProjectName() == null ? 0 : this.getProjectName()
						.hashCode());
		result = 37
				* result
				+ (getBillingType() == null ? 0 : this.getBillingType()
						.hashCode());
		result = 37
				* result
				+ (getBillingCycle() == null ? 0 : this.getBillingCycle()
						.hashCode());
		result = 37
				* result
				+ (getBillingFlag() == null ? 0 : this.getBillingFlag()
						.hashCode());
		result = 37
				* result
				+ (getBillingUnit() == null ? 0 : this.getBillingUnit()
						.hashCode());
		result = 37 * result
				+ (getUnitPrice() == null ? 0 : this.getUnitPrice().hashCode());
		result = 37
				* result
				+ (getFixedValue() == null ? 0 : this.getFixedValue()
						.hashCode());
		result = 37 * result
				+ (getValueFlag() == null ? 0 : this.getValueFlag().hashCode());
		result = 37
				* result
				+ (getAppendCondition() == null ? 0 : this.getAppendCondition()
						.hashCode());
		result = 37
				* result
				+ (getAppendValue1() == null ? 0 : this.getAppendValue1()
						.hashCode());
		result = 37
				* result
				+ (getAppendValue2() == null ? 0 : this.getAppendValue2()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37
				* result
				+ (getBalanceDay() == null ? 0 : this.getBalanceDay()
						.hashCode());
		result = 37 * result
				+ (getFamilyNo() == null ? 0 : this.getFamilyNo().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		return result;
	}

}