package com.sealinkin.comm.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HomeItemDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Home_ItemDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemId;
	private String subItemId;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Home_ItemDId() {
	}

	/** full constructor */
	public Home_ItemDId(String itemId, String subItemId, String enterpriseNo) {
		this.itemId = itemId;
		this.subItemId = subItemId;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors

	@Column(name = "ITEM_ID", nullable = false, length = 20)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
		if (!(other instanceof Home_ItemDId))
			return false;
		Home_ItemDId castOther = (Home_ItemDId) other;

		return ((this.getItemId() == castOther.getItemId()) || (this
				.getItemId() != null && castOther.getItemId() != null && this
				.getItemId().equals(castOther.getItemId())))
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
				+ (getItemId() == null ? 0 : this.getItemId().hashCode());
		result = 37 * result
				+ (getSubItemId() == null ? 0 : this.getSubItemId().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}