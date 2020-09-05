package com.sealinkin.bset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BillFormulasetTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_FORMULASET_TMP")
public class Bill_FormulasetTmp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_FormulasetTmpId id;

	// Constructors

	/** default constructor */
	public Bill_FormulasetTmp() {
	}

	/** full constructor */
	public Bill_FormulasetTmp(Bill_FormulasetTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "projectName", column = @Column(name = "PROJECT_NAME", nullable = false, length = 64)),
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)),
			@AttributeOverride(name = "billingCycle", column = @Column(name = "BILLING_CYCLE", nullable = false, length = 3)),
			@AttributeOverride(name = "billingFlag", column = @Column(name = "BILLING_FLAG", nullable = false, length = 3)),
			@AttributeOverride(name = "billingUnit", column = @Column(name = "BILLING_UNIT", length = 3)),
			@AttributeOverride(name = "unitPrice", column = @Column(name = "UNIT_PRICE", nullable = false, precision = 12, scale = 3)),
			@AttributeOverride(name = "fixedValue", column = @Column(name = "FIXED_VALUE", precision = 22, scale = 0)),
			@AttributeOverride(name = "valueFlag", column = @Column(name = "VALUE_FLAG", length = 1)),
			@AttributeOverride(name = "appendCondition", column = @Column(name = "APPEND_CONDITION", length = 1)),
			@AttributeOverride(name = "appendValue1", column = @Column(name = "APPEND_VALUE1", precision = 12, scale = 3)),
			@AttributeOverride(name = "appendValue2", column = @Column(name = "APPEND_VALUE2", precision = 12, scale = 3)),
			@AttributeOverride(name = "remark", column = @Column(name = "REMARK", length = 225)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", length = 1)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "endDate", column = @Column(name = "END_DATE", length = 7)),
			@AttributeOverride(name = "balanceDay", column = @Column(name = "BALANCE_DAY", length = 20)),
			@AttributeOverride(name = "familyNo", column = @Column(name = "FAMILY_NO", length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", precision = 22, scale = 0)) })
	public Bill_FormulasetTmpId getId() {
		return this.id;
	}

	public void setId(Bill_FormulasetTmpId id) {
		this.id = id;
	}

}