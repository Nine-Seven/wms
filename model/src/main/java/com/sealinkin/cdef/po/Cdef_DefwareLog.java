package com.sealinkin.cdef.po;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CdefDefwareLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CDEF_DEFWARE_LOG")
public class Cdef_DefwareLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cdef_DefwareLogId id;

	// Constructors

	/** default constructor */
	public Cdef_DefwareLog() {
	}

	/** full constructor */
	public Cdef_DefwareLog(Cdef_DefwareLogId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "wareName", column = @Column(name = "WARE_NAME", nullable = false, length = 30)),
			@AttributeOverride(name = "wareRemark", column = @Column(name = "WARE_REMARK", length = 50)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)) })
	public Cdef_DefwareLogId getId() {
		return this.id;
	}

	public void setId(Cdef_DefwareLogId id) {
		this.id = id;
	}

}