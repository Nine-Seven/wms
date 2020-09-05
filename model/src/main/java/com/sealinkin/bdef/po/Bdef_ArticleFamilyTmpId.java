package com.sealinkin.bdef.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BdefArticleFamilyTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_ArticleFamilyTmpId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String familyNo;
	private String familyName;
	private String ownerArticleNo;
	private String useType;
	private String rgstName;
	private Date rgstDate;
	private String status;

	// Constructors

	/** default constructor */
	public Bdef_ArticleFamilyTmpId() {
	}

	/** minimal constructor */
	public Bdef_ArticleFamilyTmpId(String enterpriseNo, String ownerNo,
			String familyNo, String ownerArticleNo, String rgstName,
			Date rgstDate, String status) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.familyNo = familyNo;
		this.ownerArticleNo = ownerArticleNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.status = status;
	}

	/** full constructor */
	public Bdef_ArticleFamilyTmpId(String enterpriseNo, String ownerNo,
			String familyNo, String familyName, String ownerArticleNo,
			String useType, String rgstName, Date rgstDate, String status) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.familyNo = familyNo;
		this.familyName = familyName;
		this.ownerArticleNo = ownerArticleNo;
		this.useType = useType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.status = status;
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

	@Column(name = "FAMILY_NAME", length = 45)
	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "USE_TYPE", length = 1)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_ArticleFamilyTmpId))
			return false;
		Bdef_ArticleFamilyTmpId castOther = (Bdef_ArticleFamilyTmpId) other;

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
				&& ((this.getFamilyName() == castOther.getFamilyName()) || (this
						.getFamilyName() != null
						&& castOther.getFamilyName() != null && this
						.getFamilyName().equals(castOther.getFamilyName())))
				&& ((this.getOwnerArticleNo() == castOther.getOwnerArticleNo()) || (this
						.getOwnerArticleNo() != null
						&& castOther.getOwnerArticleNo() != null && this
						.getOwnerArticleNo().equals(
								castOther.getOwnerArticleNo())))
				&& ((this.getUseType() == castOther.getUseType()) || (this
						.getUseType() != null && castOther.getUseType() != null && this
						.getUseType().equals(castOther.getUseType())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
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
		result = 37
				* result
				+ (getFamilyName() == null ? 0 : this.getFamilyName()
						.hashCode());
		result = 37
				* result
				+ (getOwnerArticleNo() == null ? 0 : this.getOwnerArticleNo()
						.hashCode());
		result = 37 * result
				+ (getUseType() == null ? 0 : this.getUseType().hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}