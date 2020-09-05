package com.sealinkin.rodata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RodataDeliverD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_DELIVER_D")
public class Rodata_DeliverD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_DeliverDId id;

	// Constructors

	/** default constructor */
	public Rodata_DeliverD() {
	}

	/** full constructor */
	public Rodata_DeliverD(Rodata_DeliverDId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "deliverNo", column = @Column(name = "DELIVER_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "recedeNo", column = @Column(name = "RECEDE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "poId", column = @Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "articleId", column = @Column(name = "ARTICLE_ID", nullable = false, precision = 12, scale = 0)),
			@AttributeOverride(name = "barcode", column = @Column(name = "BARCODE", nullable = false, length = 25)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "lotNo", column = @Column(name = "LOT_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "produceDate", column = @Column(name = "PRODUCE_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "expireDate", column = @Column(name = "EXPIRE_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "quality", column = @Column(name = "QUALITY", nullable = false, length = 2)),
			@AttributeOverride(name = "importBatchNo", column = @Column(name = "IMPORT_BATCH_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "articleQty", column = @Column(name = "ARTICLE_QTY", nullable = false, precision = 14, scale = 5)),
			@AttributeOverride(name = "realQty", column = @Column(name = "REAL_QTY", nullable = false, precision = 14, scale = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "recedeName", column = @Column(name = "RECEDE_NAME", length = 20)),
			@AttributeOverride(name = "recedeDate", column = @Column(name = "RECEDE_DATE", length = 7)),
			@AttributeOverride(name = "itemType", column = @Column(name = "ITEM_TYPE", nullable = false, length = 20)),
			@AttributeOverride(name = "batchSerialNo", column = @Column(name = "BATCH_SERIAL_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "cellId", column = @Column(name = "CELL_ID", nullable = false, precision = 10, scale = 0)) })
	public Rodata_DeliverDId getId() {
		return this.id;
	}

	public void setId(Rodata_DeliverDId id) {
		this.id = id;
	}

}