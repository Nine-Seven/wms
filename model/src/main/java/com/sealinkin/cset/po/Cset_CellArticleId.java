package com.sealinkin.cset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CsetCellArticleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Cset_CellArticleId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String articleNo;
	private String ownerNo;
	private String pickType;

	// Constructors

	/** default constructor */
	public Cset_CellArticleId() {
	}

	/** full constructor */
	public Cset_CellArticleId(String enterpriseNo, String warehouseNo,
			String articleNo, String ownerNo, String pickType) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.articleNo = articleNo;
		this.ownerNo = ownerNo;
		this.pickType = pickType;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 13)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PICK_TYPE", nullable = false, length = 1)
	public String getPickType() {
		return this.pickType;
	}

	public void setPickType(String pickType) {
		this.pickType = pickType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cset_CellArticleId))
			return false;
		Cset_CellArticleId castOther = (Cset_CellArticleId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getPickType() == castOther.getPickType()) || (this
						.getPickType() != null
						&& castOther.getPickType() != null && this
						.getPickType().equals(castOther.getPickType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getPickType() == null ? 0 : this.getPickType().hashCode());
		return result;
	}

}