package com.sealinkin.acdata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AcdataOutstockDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Acdata_OutstockDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String outstockNo;
	private String articleName;

	// Constructors

	/** default constructor */
	public Acdata_OutstockDId() {
	}

	/** full constructor */
	public Acdata_OutstockDId(String warehouseNo, String outstockNo,
			String articleName) {
		this.warehouseNo = warehouseNo;
		this.outstockNo = outstockNo;
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

	@Column(name = "OUTSTOCK_NO", nullable = false, length = 20)
	public String getOutstockNo() {
		return this.outstockNo;
	}

	public void setOutstockNo(String outstockNo) {
		this.outstockNo = outstockNo;
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
		if (!(other instanceof Acdata_OutstockDId))
			return false;
		Acdata_OutstockDId castOther = (Acdata_OutstockDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOutstockNo() == castOther.getOutstockNo()) || (this
						.getOutstockNo() != null
						&& castOther.getOutstockNo() != null && this
						.getOutstockNo().equals(castOther.getOutstockNo())))
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
		result = 37
				* result
				+ (getOutstockNo() == null ? 0 : this.getOutstockNo()
						.hashCode());
		result = 37
				* result
				+ (getArticleName() == null ? 0 : this.getArticleName()
						.hashCode());
		return result;
	}

}