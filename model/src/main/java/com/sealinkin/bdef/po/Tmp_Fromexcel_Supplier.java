package com.sealinkin.bdef.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TmpFromexcelSupplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FROMEXCEL_SUPPLIER")
public class Tmp_Fromexcel_Supplier implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Tmp_Fromexcel_SupplierId id;

	// Constructors

	/** default constructor */
	public Tmp_Fromexcel_Supplier() {
	}

	/** full constructor */
	public Tmp_Fromexcel_Supplier(Tmp_Fromexcel_SupplierId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "supplierNo", column = @Column(name = "SUPPLIER_NO", length = 10)),
			@AttributeOverride(name = "supplierName", column = @Column(name = "SUPPLIER_NAME", nullable = false, length = 90)),
			@AttributeOverride(name = "supplierAddress1", column = @Column(name = "SUPPLIER_ADDRESS1", length = 180)),
			@AttributeOverride(name = "supplierPhone1", column = @Column(name = "SUPPLIER_PHONE1", length = 50)),
			@AttributeOverride(name = "supplier1", column = @Column(name = "SUPPLIER1", length = 50)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", length = 2)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", precision = 22, scale = 0)),
			@AttributeOverride(name = "unloadFlag", column = @Column(name = "UNLOAD_FLAG", nullable = false, precision = 1, scale = 0)),
			@AttributeOverride(name = "rsvVarod1", column = @Column(name = "RSV_VAROD1", length = 50)),
			@AttributeOverride(name = "rsvVarod2", column = @Column(name = "RSV_VAROD2", length = 50)),
			@AttributeOverride(name = "rsvVarod3", column = @Column(name = "RSV_VAROD3", length = 50)),
			@AttributeOverride(name = "rsvVarod4", column = @Column(name = "RSV_VAROD4", length = 50)),
			@AttributeOverride(name = "rsvVarod5", column = @Column(name = "RSV_VAROD5", length = 50)),
			@AttributeOverride(name = "rsvVarod6", column = @Column(name = "RSV_VAROD6", length = 50)),
			@AttributeOverride(name = "rsvVarod7", column = @Column(name = "RSV_VAROD7", length = 50)),
			@AttributeOverride(name = "rsvVarod8", column = @Column(name = "RSV_VAROD8", length = 50)),
			@AttributeOverride(name = "rsvNum1", column = @Column(name = "RSV_NUM1", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvNum2", column = @Column(name = "RSV_NUM2", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvNum3", column = @Column(name = "RSV_NUM3", precision = 22, scale = 0)),
			@AttributeOverride(name = "rsvDate1", column = @Column(name = "RSV_DATE1", length = 7)),
			@AttributeOverride(name = "rsvDate2", column = @Column(name = "RSV_DATE2", length = 7)),
			@AttributeOverride(name = "rsvDate3", column = @Column(name = "RSV_DATE3", length = 7)) })
	public Tmp_Fromexcel_SupplierId getId() {
		return this.id;
	}

	public void setId(Tmp_Fromexcel_SupplierId id) {
		this.id = id;
	}

}