package com.sealinkin.bset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BillFinancial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_FINANCIAL")
public class Bill_Financial implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_FinancialId id;

	// Constructors

	/** default constructor */
	public Bill_Financial() {
	}

	/** full constructor */
	public Bill_Financial(Bill_FinancialId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingMonth", column = @Column(name = "BILLING_MONTH", nullable = false, length = 10)),
			@AttributeOverride(name = "accountNo", column = @Column(name = "ACCOUNT_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "amount", column = @Column(name = "AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "discountAmount", column = @Column(name = "DISCOUNT_AMOUNT", precision = 12, scale = 3)) })
	public Bill_FinancialId getId() {
		return this.id;
	}

	public void setId(Bill_FinancialId id) {
		this.id = id;
	}

}