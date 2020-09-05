package com.sealinkin.bdef.po;

// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Tmp_Fromexcelcsetcell entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TMP_FROMEXCELCSETCELL")
public class Tmp_Fromexcelcsetcell implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tmp_FromexcelcsetcellId id;

	// Constructors

	/** default constructor */
	public Tmp_Fromexcelcsetcell() {
	}

	/** full constructor */
	public Tmp_Fromexcelcsetcell(Tmp_FromexcelcsetcellId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "barcode", column = @Column(name = "BARCODE", nullable = false, length = 13)),
			@AttributeOverride(name = "articleName", column = @Column(name = "ARTICLE_NAME", nullable = false, length = 100)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "maxQty", column = @Column(name = "MAX_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "alertQty", column = @Column(name = "ALERT_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "keepCells", column = @Column(name = "KEEP_CELLS", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "lineId", column = @Column(name = "LINE_ID", nullable = false, precision = 2, scale = 0)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)),
           	@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)) })

	public Tmp_FromexcelcsetcellId getId() {
		return this.id;
	}

	public void setId(Tmp_FromexcelcsetcellId id) {
		this.id = id;
	}

}