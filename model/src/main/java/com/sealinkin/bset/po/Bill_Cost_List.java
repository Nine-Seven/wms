package com.sealinkin.bset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BillCostList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_COST_LIST")
public class Bill_Cost_List implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_Cost_ListId id;

	// Constructors

	/** default constructor */
	public Bill_Cost_List() {
	}

	/** full constructor */
	public Bill_Cost_List(Bill_Cost_ListId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "billingDate", column = @Column(name = "BILLING_DATE", length = 7)),
			@AttributeOverride(name = "amount", column = @Column(name = "AMOUNT", precision = 12, scale = 3)),
			@AttributeOverride(name = "flag", column = @Column(name = "FLAG", length = 1)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "appendCondition", column = @Column(name = "APPEND_CONDITION", length = 1)),
			@AttributeOverride(name = "appendValue1", column = @Column(name = "APPEND_VALUE1", precision = 12, scale = 3)),
			@AttributeOverride(name = "appendValue2", column = @Column(name = "APPEND_VALUE2", precision = 12, scale = 3)),
			@AttributeOverride(name = "billingUnit", column = @Column(name = "BILLING_UNIT", length = 3)),
			@AttributeOverride(name = "unitPrice", column = @Column(name = "UNIT_PRICE", precision = 12, scale = 3)) })
	public Bill_Cost_ListId getId() {
		return this.id;
	}

	public void setId(Bill_Cost_ListId id) {
		this.id = id;
	}

}