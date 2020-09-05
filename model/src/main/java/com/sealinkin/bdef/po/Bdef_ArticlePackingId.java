package com.sealinkin.bdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefArticlePackingId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_ArticlePackingId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String articleNo;
	private Double packingQty;

	// Constructors

	/** default constructor */
	public Bdef_ArticlePackingId() {
	}

	/** full constructor */
	public Bdef_ArticlePackingId(String enterpriseNo, String articleNo,
			Double packingQty) {
		this.enterpriseNo = enterpriseNo;
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
		if (!(other instanceof Bdef_ArticlePackingId))
			return false;
		Bdef_ArticlePackingId castOther = (Bdef_ArticlePackingId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
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
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		return result;
	}

}