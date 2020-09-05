package com.sealinkin.cdef.po;
// default package

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Cdef_DefcellSuppcount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CDEF_DEFCELL_SUPPCOUNT")
public class Cdef_DefcellSuppcount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Cdef_DefcellSuppcountId id;
	private BigDecimal suppCount;

	// Constructors

	/** default constructor */
	public Cdef_DefcellSuppcount() {
	}

	/** minimal constructor */
	public Cdef_DefcellSuppcount(Cdef_DefcellSuppcountId id) {
		this.id = id;
	}

	/** full constructor */
	public Cdef_DefcellSuppcount(Cdef_DefcellSuppcountId id, BigDecimal suppCount) {
		this.id = id;
		this.suppCount = suppCount;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)) })
	public Cdef_DefcellSuppcountId getId() {
		return this.id;
	}

	public void setId(Cdef_DefcellSuppcountId id) {
		this.id = id;
	}

	@Column(name = "SUPP_COUNT", precision = 20, scale = 0)
	public BigDecimal getSuppCount() {
		return this.suppCount;
	}

	public void setSuppCount(BigDecimal suppCount) {
		this.suppCount = suppCount;
	}

}