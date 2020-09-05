package com.sealinkin.ridata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * RidataCheckPal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RIDATA_CHECK_PAL", uniqueConstraints = @UniqueConstraint(columnNames = {
		"LABEL_NO", "WAREHOUSE_NO", "OWNER_NO", "CHECK_NO", "CHECK_ROW_ID",
		"CONTAINER_NO", "CELL_NO", "FIRSTCHECK_LABEL_NO" }))
public class Ridata_CheckPal implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_CheckPalId id;

	// Constructors

	/** default constructor */
	public Ridata_CheckPal() {
	}

	/** full constructor */
	public Ridata_CheckPal(Ridata_CheckPalId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "labelNo", column = @Column(name = "LABEL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "checkRowId", column = @Column(name = "CHECK_ROW_ID", nullable = false, precision = 4, scale = 0)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "checkQty", column = @Column(name = "CHECK_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "printerGroupNo", column = @Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "dockNo", column = @Column(name = "DOCK_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "containerNo", column = @Column(name = "CONTAINER_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "fixpalFlag", column = @Column(name = "FIXPAL_FLAG", nullable = false, length = 1)),
			@AttributeOverride(name = "seasonFlag", column = @Column(name = "SEASON_FLAG", length = 1)),
			@AttributeOverride(name = "quality", column = @Column(name = "QUALITY", nullable = false, length = 1)),
			@AttributeOverride(name = "scanLabelNo", column = @Column(name = "SCAN_LABEL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "businessType", column = @Column(name = "BUSINESS_TYPE", nullable = false, length = 2)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "firstcheckLabelNo", column = @Column(name = "FIRSTCHECK_LABEL_NO", nullable = false, length = 24)) })
	public Ridata_CheckPalId getId() {
		return this.id;
	}

	public void setId(Ridata_CheckPalId id) {
		this.id = id;
	}

}