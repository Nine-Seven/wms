package com.sealinkin.cost.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CostFinancial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_FINANCIAL")
public class Cost_Financial implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Cost_FinancialId id;

	// Constructors

	/** default constructor */
	public Cost_Financial() {
	}

	/** full constructor */
	public Cost_Financial(Cost_FinancialId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "accountNo", column = @Column(name = "ACCOUNT_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "amount", column = @Column(name = "AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "discountAmount", column = @Column(name = "DISCOUNT_AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "realAmount", column = @Column(name = "REAL_AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "beginDate", column = @Column(name = "BEGIN_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "endDate", column = @Column(name = "END_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "flag", column = @Column(name = "FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "buildDate", column = @Column(name = "BUILD_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 3)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "createFlag", column = @Column(name = "CREATE_FLAG", length = 1)),
			@AttributeOverride(name = "costFlag", column = @Column(name = "COST_FLAG", length = 1)),
			@AttributeOverride(name = "accountGroupNo", column = @Column(name = "ACCOUNT_GROUP_NO", length = 5)) })
	public Cost_FinancialId getId() {
		return this.id;
	}

	public void setId(Cost_FinancialId id) {
		this.id = id;
	}

}