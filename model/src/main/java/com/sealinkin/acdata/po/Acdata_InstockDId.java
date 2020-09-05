package com.sealinkin.acdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AcdataInstockDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Acdata_InstockDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String instockNo;
	private String articleName;

	// Constructors

	/** default constructor */
	public Acdata_InstockDId() {
	}

	/** full constructor */
	public Acdata_InstockDId(String warehouseNo, String instockNo,
			String articleName) {
		this.warehouseNo = warehouseNo;
		this.instockNo = instockNo;
		this.articleName = articleName;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "INSTOCK_NO", nullable = false, length = 20)
	public String getInstockNo() {
		return this.instockNo;
	}

	public void setInstockNo(String instockNo) {
		this.instockNo = instockNo;
	}

	@Column(name = "ARTICLE_NAME", nullable = false, length = 128)
	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Acdata_InstockDId))
			return false;
		Acdata_InstockDId castOther = (Acdata_InstockDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getInstockNo() == castOther.getInstockNo()) || (this
						.getInstockNo() != null
						&& castOther.getInstockNo() != null && this
						.getInstockNo().equals(castOther.getInstockNo())))
				&& ((this.getArticleName() == castOther.getArticleName()) || (this
						.getArticleName() != null
						&& castOther.getArticleName() != null && this
						.getArticleName().equals(castOther.getArticleName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getInstockNo() == null ? 0 : this.getInstockNo().hashCode());
		result = 37
				* result
				+ (getArticleName() == null ? 0 : this.getArticleName()
						.hashCode());
		return result;
	}

}