package com.sealinkin.bdef.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TmpFormexcelDefcell entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FORMEXCEL_DEFCELL")
public class Tmp_Formexcel_Defcell implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Tmp_Formexcel_DefcellId id;

	// Constructors

	/** default constructor */
	public Tmp_Formexcel_Defcell() {
	}

	/** full constructor */
	public Tmp_Formexcel_Defcell(Tmp_Formexcel_DefcellId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "wareNo", column = @Column(name = "WARE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "areaNo", column = @Column(name = "AREA_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "stockNo", column = @Column(name = "STOCK_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "stockX", column = @Column(name = "STOCK_X", nullable = false, length = 5)),
			@AttributeOverride(name = "bayX", column = @Column(name = "BAY_X", nullable = false, length = 5)),
			@AttributeOverride(name = "stockY", column = @Column(name = "STOCK_Y", nullable = false, length = 5)),
			@AttributeOverride(name = "prefix", column = @Column(name = "PREFIX", nullable = false, length = 20)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "cellStatus", column = @Column(name = "CELL_STATUS", nullable = false, length = 1)),
			@AttributeOverride(name = "mixSupplier", column = @Column(name = "MIX_SUPPLIER", nullable = false, length = 1)),
			@AttributeOverride(name = "mixFlag", column = @Column(name = "MIX_FLAG", nullable = false, precision = 1, scale = 0)),
			@AttributeOverride(name = "maxQty", column = @Column(name = "MAX_QTY", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "maxCase", column = @Column(name = "MAX_CASE", nullable = false, precision = 13, scale = 5)),
			@AttributeOverride(name = "maxWeight", column = @Column(name = "MAX_WEIGHT", nullable = false, precision = 13, scale = 5)),
			@AttributeOverride(name = "maxVolume", column = @Column(name = "MAX_VOLUME", nullable = false, precision = 13, scale = 5)),
			@AttributeOverride(name = "pickOrder", column = @Column(name = "PICK_ORDER", nullable = false, precision = 5, scale = 0)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)) })
	public Tmp_Formexcel_DefcellId getId() {
		return this.id;
	}

	public void setId(Tmp_Formexcel_DefcellId id) {
		this.id = id;
	}

}