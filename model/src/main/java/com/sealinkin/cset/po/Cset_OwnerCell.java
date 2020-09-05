package com.sealinkin.cset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CsetOwnerCell entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CSET_OWNER_CELL")
public class Cset_OwnerCell implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cset_OwnerCellId id;

	// Constructors

	/** default constructor */
	public Cset_OwnerCell() {
	}

	/** full constructor */
	public Cset_OwnerCell(Cset_OwnerCellId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)) })
	public Cset_OwnerCellId getId() {
		return this.id;
	}

	public void setId(Cset_OwnerCellId id) {
		this.id = id;
	}

}