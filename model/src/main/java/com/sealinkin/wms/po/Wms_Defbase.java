package com.sealinkin.wms.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Wms_Defbase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WMS_DEFBASE")
public class Wms_Defbase implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Wms_DefbaseId id;

	// Constructors

	/** default constructor */
	public Wms_Defbase() {
	}

	/** full constructor */
	public Wms_Defbase(Wms_DefbaseId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "colname", column = @Column(name = "COLNAME", nullable = false, length = 50)),
			@AttributeOverride(name = "groupNo", column = @Column(name = "GROUP_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "subGroupNo", column = @Column(name = "SUB_GROUP_NO", nullable = false, length = 10)),
			@AttributeOverride(name = "useLevel", column = @Column(name = "USE_LEVEL", nullable = false, length = 4)),
			@AttributeOverride(name = "sdefine", column = @Column(name = "SDEFINE", length = 500)),
			@AttributeOverride(name = "ndefine", column = @Column(name = "NDEFINE", precision = 12, scale = 5)),
			@AttributeOverride(name = "memo", column = @Column(name = "MEMO", length = 500)) })
	public Wms_DefbaseId getId() {
		return this.id;
	}

	public void setId(Wms_DefbaseId id) {
		this.id = id;
	}

}