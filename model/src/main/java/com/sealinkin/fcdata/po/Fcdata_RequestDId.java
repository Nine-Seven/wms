package com.sealinkin.fcdata.po;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Fcdata_RequestDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Fcdata_RequestDId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String warehouseNo;
	private String ownerNo;
	private String requestNo;
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String articleNo;
	private String cellNo;

	// Constructors

	/** default constructor */
	public Fcdata_RequestDId() {
	}

	/** full constructor */
	public Fcdata_RequestDId(String warehouseNo, String ownerNo,
			String requestNo, String wareNo, String areaNo, String stockNo,
			String articleNo, String cellNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.requestNo = requestNo;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.articleNo = articleNo;
		this.cellNo = cellNo;
	}

	// Property accessors

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "REQUEST_NO", nullable = false, length = 20)
	public String getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	@Column(name = "WARE_NO", nullable = false, length = 5)
	public String getWareNo() {
		return this.wareNo;
	}

	public void setWareNo(String wareNo) {
		this.wareNo = wareNo;
	}

	@Column(name = "AREA_NO", nullable = false, length = 5)
	public String getAreaNo() {
		return this.areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	@Column(name = "STOCK_NO", nullable = false, length = 5)
	public String getStockNo() {
		return this.stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Fcdata_RequestDId))
			return false;
		Fcdata_RequestDId castOther = (Fcdata_RequestDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getRequestNo() == castOther.getRequestNo()) || (this
						.getRequestNo() != null
						&& castOther.getRequestNo() != null && this
						.getRequestNo().equals(castOther.getRequestNo())))
				&& ((this.getWareNo() == castOther.getWareNo()) || (this
						.getWareNo() != null && castOther.getWareNo() != null && this
						.getWareNo().equals(castOther.getWareNo())))
				&& ((this.getAreaNo() == castOther.getAreaNo()) || (this
						.getAreaNo() != null && castOther.getAreaNo() != null && this
						.getAreaNo().equals(castOther.getAreaNo())))
				&& ((this.getStockNo() == castOther.getStockNo()) || (this
						.getStockNo() != null && castOther.getStockNo() != null && this
						.getStockNo().equals(castOther.getStockNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getRequestNo() == null ? 0 : this.getRequestNo().hashCode());
		result = 37 * result
				+ (getWareNo() == null ? 0 : this.getWareNo().hashCode());
		result = 37 * result
				+ (getAreaNo() == null ? 0 : this.getAreaNo().hashCode());
		result = 37 * result
				+ (getStockNo() == null ? 0 : this.getStockNo().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		return result;
	}

}