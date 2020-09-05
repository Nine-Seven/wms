package com.sealinkin.odata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OdataPackageTmp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_PACKAGE_TMP")
public class Odata_PackageTmp implements java.io.Serializable {

	// Fields

	private Odata_PackageTmpId id;

	// Constructors

	/** default constructor */
	public Odata_PackageTmp() {
	}

	/** full constructor */
	public Odata_PackageTmp(Odata_PackageTmpId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "poNo", column = @Column(name = "PO_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "poType", column = @Column(name = "PO_TYPE", nullable = false, length = 5)),
			@AttributeOverride(name = "sourceexpNo", column = @Column(name = "SOURCEEXP_NO", nullable = false, length = 30)),
			@AttributeOverride(name = "expDate", column = @Column(name = "EXP_DATE", length = 7)),
			@AttributeOverride(name = "shipperNo", column = @Column(name = "SHIPPER_NO", length = 15)),
			@AttributeOverride(name = "shipperDeliverNo", column = @Column(name = "SHIPPER_DELIVER_NO", nullable = false, length = 30)),
			@AttributeOverride(name = "custAddress", column = @Column(name = "CUST_ADDRESS", length = 100)),
			@AttributeOverride(name = "custPhone", column = @Column(name = "CUST_PHONE", length = 50)),
			@AttributeOverride(name = "contactorName", column = @Column(name = "CONTACTOR_NAME", length = 50)),
			@AttributeOverride(name = "sendAddress", column = @Column(name = "SEND_ADDRESS", length = 100)),
			@AttributeOverride(name = "sendMobilePhone", column = @Column(name = "SEND_MOBILE_PHONE", length = 20)),
			@AttributeOverride(name = "sendName", column = @Column(name = "SEND_NAME", length = 50)) })
	public Odata_PackageTmpId getId() {
		return this.id;
	}

	public void setId(Odata_PackageTmpId id) {
		this.id = id;
	}

}