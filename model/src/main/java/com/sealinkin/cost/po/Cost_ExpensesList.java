package com.sealinkin.cost.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CostExpensesList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_EXPENSES_LIST")
public class Cost_ExpensesList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_ExpensesListId id;

	// Constructors

	/** default constructor */
	public Cost_ExpensesList() {
	}

	/** full constructor */
	public Cost_ExpensesList(Cost_ExpensesListId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)),
			@AttributeOverride(name = "familyNo", column = @Column(name = "FAMILY_NO", length = 10)),
			@AttributeOverride(name = "sourceNo", column = @Column(name = "SOURCE_NO", length = 20)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "buildDate", column = @Column(name = "BUILD_DATE", length = 7)),
			@AttributeOverride(name = "area", column = @Column(name = "AREA", precision = 12, scale = 3)),
			@AttributeOverride(name = "tray", column = @Column(name = "TRAY", precision = 12, scale = 3)),
			@AttributeOverride(name = "qty", column = @Column(name = "QTY", precision = 12, scale = 3)),
			@AttributeOverride(name = "volume", column = @Column(name = "VOLUME", precision = 12, scale = 3)),
			@AttributeOverride(name = "weight", column = @Column(name = "WEIGHT", precision = 12, scale = 3)),
			@AttributeOverride(name = "reserved1", column = @Column(name = "RESERVED1", precision = 12, scale = 3)),
			@AttributeOverride(name = "reserved2", column = @Column(name = "RESERVED2", precision = 12, scale = 3)),
			@AttributeOverride(name = "reserved3", column = @Column(name = "RESERVED3", precision = 12, scale = 3)),
			@AttributeOverride(name = "reserved4", column = @Column(name = "RESERVED4", precision = 12, scale = 3)),
			@AttributeOverride(name = "reserved5", column = @Column(name = "RESERVED5", precision = 12, scale = 3)),
			@AttributeOverride(name = "reserved6", column = @Column(name = "RESERVED6", precision = 12, scale = 3)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", length = 2)),
			@AttributeOverride(name = "flag", column = @Column(name = "FLAG", length = 1)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)) })
	public Cost_ExpensesListId getId() {
		return this.id;
	}

	public void setId(Cost_ExpensesListId id) {
		this.id = id;
	}

}