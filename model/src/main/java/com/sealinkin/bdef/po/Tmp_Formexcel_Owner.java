package com.sealinkin.bdef.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TmpFormexcelOwner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FORMEXCEL_OWNER")
public class Tmp_Formexcel_Owner implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Tmp_Formexcel_OwnerId id;

	// Constructors

	/** default constructor */
	public Tmp_Formexcel_Owner() {
	}

	/** full constructor */
	public Tmp_Formexcel_Owner(Tmp_Formexcel_OwnerId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "ownerName", column = @Column(name = "OWNER_NAME", nullable = false, length = 60)),
			@AttributeOverride(name = "ownerAlias", column = @Column(name = "OWNER_ALIAS", length = 40)),
			@AttributeOverride(name = "ownerAddress", column = @Column(name = "OWNER_ADDRESS", length = 120)),
			@AttributeOverride(name = "ownerPhone", column = @Column(name = "OWNER_PHONE", length = 15)),
			@AttributeOverride(name = "ownerFax", column = @Column(name = "OWNER_FAX", length = 15)),
			@AttributeOverride(name = "ownerContact", column = @Column(name = "OWNER_CONTACT", length = 20)),
			@AttributeOverride(name = "authorityType", column = @Column(name = "AUTHORITY_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "cellManagerType", column = @Column(name = "CELL_MANAGER_TYPE", length = 2)),
			@AttributeOverride(name = "typeValue", column = @Column(name = "TYPE_VALUE", length = 24)),
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
	public Tmp_Formexcel_OwnerId getId() {
		return this.id;
	}

	public void setId(Tmp_Formexcel_OwnerId id) {
		this.id = id;
	}

}