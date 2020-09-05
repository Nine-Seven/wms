package com.sealinkin.acdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AcdataStockcontentId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Acdata_StockcontentId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String orderNo;
	private String sourceNo;
	private String articleName;

	// Constructors

	/** default constructor */
	public Acdata_StockcontentId() {
	}

	/** full constructor */
	public Acdata_StockcontentId(String warehouseNo, String orderNo,
			String sourceNo, String articleName) {
		this.warehouseNo = warehouseNo;
		this.orderNo = orderNo;
		this.sourceNo = sourceNo;
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

	@Column(name = "ORDER_NO", nullable = false, length = 20)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 50)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
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
		if (!(other instanceof Acdata_StockcontentId))
			return false;
		Acdata_StockcontentId castOther = (Acdata_StockcontentId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this
						.getOrderNo() != null && castOther.getOrderNo() != null && this
						.getOrderNo().equals(castOther.getOrderNo())))
				&& ((this.getSourceNo() == castOther.getSourceNo()) || (this
						.getSourceNo() != null
						&& castOther.getSourceNo() != null && this
						.getSourceNo().equals(castOther.getSourceNo())))
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
				+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result
				+ (getSourceNo() == null ? 0 : this.getSourceNo().hashCode());
		result = 37
				* result
				+ (getArticleName() == null ? 0 : this.getArticleName()
						.hashCode());
		return result;
	}

}