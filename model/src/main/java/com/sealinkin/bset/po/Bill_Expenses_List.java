package com.sealinkin.bset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BillExpensesList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BILL_EXPENSES_LIST")
public class Bill_Expenses_List implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bill_Expenses_ListId id;

	// Constructors

	/** default constructor */
	public Bill_Expenses_List() {
	}

	/** full constructor */
	public Bill_Expenses_List(Bill_Expenses_ListId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "billingProject", column = @Column(name = "BILLING_PROJECT", nullable = false, length = 8)),
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUP_NO", length = 10)),
			@AttributeOverride(name = "beginDate", column = @Column(name = "BEGIN_DATE", length = 7)),
			@AttributeOverride(name = "endDate", column = @Column(name = "END_DATE", length = 7)),
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
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "billingType", column = @Column(name = "BILLING_TYPE", nullable = false, length = 3)),
			@AttributeOverride(name = "flag", column = @Column(name = "FLAG", length = 1)) })
	public Bill_Expenses_ListId getId() {
		return this.id;
	}

	public void setId(Bill_Expenses_ListId id) {
		this.id = id;
	}

}