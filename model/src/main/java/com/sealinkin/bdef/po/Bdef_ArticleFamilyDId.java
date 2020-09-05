package com.sealinkin.bdef.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Bdef_ArticleFamilyDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_ArticleFamilyDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String familyNo;
	private String articleNo;

	// Constructors

	/** default constructor */
	public Bdef_ArticleFamilyDId() {
	}

	/** full constructor */
	public Bdef_ArticleFamilyDId(String enterpriseNo, String ownerNo,
			String familyNo, String articleNo) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.familyNo = familyNo;
		this.articleNo = articleNo;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "FAMILY_NO", nullable = false, length = 20)
	public String getFamilyNo() {
		return this.familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 13)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_ArticleFamilyDId))
			return false;
		Bdef_ArticleFamilyDId castOther = (Bdef_ArticleFamilyDId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getFamilyNo() == castOther.getFamilyNo()) || (this
						.getFamilyNo() != null
						&& castOther.getFamilyNo() != null && this
						.getFamilyNo().equals(castOther.getFamilyNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getFamilyNo() == null ? 0 : this.getFamilyNo().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		return result;
	}

}