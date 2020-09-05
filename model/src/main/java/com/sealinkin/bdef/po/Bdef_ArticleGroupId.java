package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BdefArticleGroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bdef_ArticleGroupId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String ownerNo;
	private String groupNo;
	private String groupLevel;

	// Constructors

	/** default constructor */
	public Bdef_ArticleGroupId() {
	}

	/** full constructor */
	public Bdef_ArticleGroupId(String enterpriseNo, String ownerNo,
			String groupNo, String groupLevel) {
		this.enterpriseNo = enterpriseNo;
		this.ownerNo = ownerNo;
		this.groupNo = groupNo;
		this.groupLevel = groupLevel;
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

	@Column(name = "GROUP_NO", nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "GROUP_LEVEL", nullable = false, length = 1)
	public String getGroupLevel() {
		return this.groupLevel;
	}

	public void setGroupLevel(String groupLevel) {
		this.groupLevel = groupLevel;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Bdef_ArticleGroupId))
			return false;
		Bdef_ArticleGroupId castOther = (Bdef_ArticleGroupId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getGroupNo() == castOther.getGroupNo()) || (this
						.getGroupNo() != null && castOther.getGroupNo() != null && this
						.getGroupNo().equals(castOther.getGroupNo())))
				&& ((this.getGroupLevel() == castOther.getGroupLevel()) || (this
						.getGroupLevel() != null
						&& castOther.getGroupLevel() != null && this
						.getGroupLevel().equals(castOther.getGroupLevel())));
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
				+ (getGroupNo() == null ? 0 : this.getGroupNo().hashCode());
		result = 37
				* result
				+ (getGroupLevel() == null ? 0 : this.getGroupLevel()
						.hashCode());
		return result;
	}

}