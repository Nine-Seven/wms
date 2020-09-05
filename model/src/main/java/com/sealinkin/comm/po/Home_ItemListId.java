package com.sealinkin.comm.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HomeItemListId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Home_ItemListId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldId;
	private String subItemId;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Home_ItemListId() {
	}

	/** full constructor */
	public Home_ItemListId(String fieldId, String subItemId, String enterpriseNo) {
		this.fieldId = fieldId;
		this.subItemId = subItemId;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "FIELD_ID", nullable = false, length = 20)
	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "SUB_ITEM_ID", nullable = false, length = 20)
	public String getSubItemId() {
		return this.subItemId;
	}

	public void setSubItemId(String subItemId) {
		this.subItemId = subItemId;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Home_ItemListId))
			return false;
		Home_ItemListId castOther = (Home_ItemListId) other;

		return ((this.getFieldId() == castOther.getFieldId()) || (this
				.getFieldId() != null && castOther.getFieldId() != null && this
				.getFieldId().equals(castOther.getFieldId())))
				&& ((this.getSubItemId() == castOther.getSubItemId()) || (this
						.getSubItemId() != null
						&& castOther.getSubItemId() != null && this
						.getSubItemId().equals(castOther.getSubItemId())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFieldId() == null ? 0 : this.getFieldId().hashCode());
		result = 37 * result
				+ (getSubItemId() == null ? 0 : this.getSubItemId().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}