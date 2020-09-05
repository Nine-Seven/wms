package com.sealinkin.bdef.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TmpFormexcelCust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FORMEXCEL_CUST")
public class Tmp_Formexcel_Cust implements java.io.Serializable {

	// Fields

	private Tmp_Formexcel_CustId id;

	// Constructors

	/** default constructor */
	public Tmp_Formexcel_Cust() {
	}

	/** full constructor */
	public Tmp_Formexcel_Cust(Tmp_Formexcel_CustId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "ownerCustNo", column = @Column(name = "OWNER_CUST_NO", length = 20)),
			@AttributeOverride(name = "custName", column = @Column(name = "CUST_NAME", nullable = false, length = 90)),
			@AttributeOverride(name = "custAlias", column = @Column(name = "CUST_ALIAS", length = 90)),
			@AttributeOverride(name = "custAddress", column = @Column(name = "CUST_ADDRESS", length = 100)),
			@AttributeOverride(name = "deliveryAddress", column = @Column(name = "DELIVERY_ADDRESS", length = 180)),
			@AttributeOverride(name = "custPhone1", column = @Column(name = "CUST_PHONE1", length = 50)),
			@AttributeOverride(name = "contactorName1", column = @Column(name = "CONTACTOR_NAME1", length = 50)),
			@AttributeOverride(name = "custProvince", column = @Column(name = "CUST_PROVINCE", length = 200)),
			@AttributeOverride(name = "custCity", column = @Column(name = "CUST_CITY", length = 200)),
			@AttributeOverride(name = "custZone", column = @Column(name = "CUST_ZONE", length = 200)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "controlType", column = @Column(name = "CONTROL_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "controlValue", column = @Column(name = "CONTROL_VALUE", nullable = false, precision = 22, scale = 0)),
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
	public Tmp_Formexcel_CustId getId() {
		return this.id;
	}

	public void setId(Tmp_Formexcel_CustId id) {
		this.id = id;
	}

}