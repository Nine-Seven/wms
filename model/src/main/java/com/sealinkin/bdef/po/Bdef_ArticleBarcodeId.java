package com.sealinkin.bdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefArticleBarcodeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_ArticleBarcodeId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String barcode;
	private String ownerNo;
	private String articleNo;
	private Double packingQty;

	// Constructors

	/** default constructor */
	public Bdef_ArticleBarcodeId() {
	}

	/** full constructor */
	public Bdef_ArticleBarcodeId(String enterpriseNo, String barcode,
			String ownerNo, String articleNo, Double packingQty) {
		this.enterpriseNo = enterpriseNo;
		this.barcode = barcode;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "BARCODE", nullable = false, length = 25)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_ArticleBarcodeId))
			return false;
		Bdef_ArticleBarcodeId castOther = (Bdef_ArticleBarcodeId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getBarcode() == castOther.getBarcode()) || (this
						.getBarcode() != null && castOther.getBarcode() != null && this
						.getBarcode().equals(castOther.getBarcode())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getBarcode() == null ? 0 : this.getBarcode().hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		return result;
	}

}