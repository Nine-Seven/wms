package com.sealinkin.cset.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OsetBufferId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Oset_BufferId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String bufferName;
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String cellNo;
	
	// Constructors

	/** default constructor */
	public Oset_BufferId() {
	}

	/** full constructor */
	public Oset_BufferId(String enterpriseNo, String warehouseNo,
			String bufferName, String wareNo, String areaNo, String stockNo,
			String cellNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.bufferName = bufferName;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.cellNo = cellNo;
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

	@Column(name = "BUFFER_NAME", nullable = false, length = 30)
	public String getBufferName() {
		return this.bufferName;
	}

	public void setBufferName(String bufferName) {
		this.bufferName = bufferName;
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
		if (!(other instanceof Oset_BufferId))
			return false;
		Oset_BufferId castOther = (Oset_BufferId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getBufferName() == castOther.getBufferName()) || (this
						.getBufferName() != null
						&& castOther.getBufferName() != null && this
						.getBufferName().equals(castOther.getBufferName())))
				&& ((this.getWareNo() == castOther.getWareNo()) || (this
						.getWareNo() != null && castOther.getWareNo() != null && this
						.getWareNo().equals(castOther.getWareNo())))
				&& ((this.getAreaNo() == castOther.getAreaNo()) || (this
						.getAreaNo() != null && castOther.getAreaNo() != null && this
						.getAreaNo().equals(castOther.getAreaNo())))
				&& ((this.getStockNo() == castOther.getStockNo()) || (this
						.getStockNo() != null && castOther.getStockNo() != null && this
						.getStockNo().equals(castOther.getStockNo())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())));
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
		result = 37
				* result
				+ (getBufferName() == null ? 0 : this.getBufferName()
						.hashCode());
		result = 37 * result
				+ (getWareNo() == null ? 0 : this.getWareNo().hashCode());
		result = 37 * result
				+ (getAreaNo() == null ? 0 : this.getAreaNo().hashCode());
		result = 37 * result
				+ (getStockNo() == null ? 0 : this.getStockNo().hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		return result;
	}

}