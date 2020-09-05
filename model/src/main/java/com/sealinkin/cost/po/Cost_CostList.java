package com.sealinkin.cost.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CostCostList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_COST_LIST")
public class Cost_CostList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_CostListId id;

	// Constructors

	/** default constructor */
	public Cost_CostList() {
	}

	/** full constructor */
	public Cost_CostList(Cost_CostListId id) {
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
			@AttributeOverride(name = "buildDate", column = @Column(name = "BUILD_DATE", length = 7)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "beginDate", column = @Column(name = "BEGIN_DATE", length = 7)),
			@AttributeOverride(name = "endDate", column = @Column(name = "END_DATE", length = 7)),
			@AttributeOverride(name = "qty", column = @Column(name = "QTY", precision = 12, scale = 3)),
			@AttributeOverride(name = "amount", column = @Column(name = "AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "favourableAmount", column = @Column(name = "FAVOURABLE_AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", length = 2)),
			@AttributeOverride(name = "flag", column = @Column(name = "FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "accountNo", column = @Column(name = "ACCOUNT_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "createFlag", column = @Column(name = "CREATE_FLAG", length = 1)),
			@AttributeOverride(name = "costFlag", column = @Column(name = "COST_FLAG", nullable = false, length = 1)) })
	public Cost_CostListId getId() {
		return this.id;
	}

	public void setId(Cost_CostListId id) {
		this.id = id;
	}

}