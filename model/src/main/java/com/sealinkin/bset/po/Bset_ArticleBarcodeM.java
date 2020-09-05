package com.sealinkin.bset.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BsetArticleBarcodeM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BSET_ARTICLE_BARCODE_M")
public class Bset_ArticleBarcodeM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bset_ArticleBarcodeMId id;

	// Constructors

	/** default constructor */
	public Bset_ArticleBarcodeM() {
	}

	/** full constructor */
	public Bset_ArticleBarcodeM(Bset_ArticleBarcodeMId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "paperNo", column = @Column(name = "PAPER_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "status", column = @Column(name = "STATUS", nullable = false, length = 2)),
			@AttributeOverride(name = "rgstName", column = @Column(name = "RGST_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "rgstDate", column = @Column(name = "RGST_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "updtName", column = @Column(name = "UPDT_NAME", length = 20)),
			@AttributeOverride(name = "updtDate", column = @Column(name = "UPDT_DATE", length = 7)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", length = 15)) })
	public Bset_ArticleBarcodeMId getId() {
		return this.id;
	}

	public void setId(Bset_ArticleBarcodeMId id) {
		this.id = id;
	}

}