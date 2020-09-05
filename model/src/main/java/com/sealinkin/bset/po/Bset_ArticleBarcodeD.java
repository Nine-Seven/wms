package com.sealinkin.bset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BsetArticleBarcodeD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_ARTICLE_BARCODE_D")
public class Bset_ArticleBarcodeD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bset_ArticleBarcodeDId id;

	// Constructors

	/** default constructor */
	public Bset_ArticleBarcodeD() {
	}

	/** full constructor */
	public Bset_ArticleBarcodeD(Bset_ArticleBarcodeDId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "barcode", column = @Column(name = "BARCODE", length = 20)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "barcodeType", column = @Column(name = "BARCODE_TYPE", nullable = false, length = 1)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)) })
	public Bset_ArticleBarcodeDId getId() {
		return this.id;
	}

	public void setId(Bset_ArticleBarcodeDId id) {
		this.id = id;
	}

}