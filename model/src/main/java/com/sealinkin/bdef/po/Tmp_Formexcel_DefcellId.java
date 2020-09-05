package com.sealinkin.bdef.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TmpFormexcelDefcellId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_Formexcel_DefcellId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String wareNo;
	private String areaNo;
	private String stockNo;
	private String stockX;
	private String bayX;
	private String stockY;
	private String prefix;
	private String cellNo;
	private String cellStatus;
	private String mixSupplier;
	private Integer mixFlag;
	private Double maxQty;
	private Double maxCase;
	private Double maxWeight;
	private Double maxVolume;
	private Integer pickOrder;
	private String status;
	private Double rowId;

	// Constructors

	/** default constructor */
	public Tmp_Formexcel_DefcellId() {
	}

	/** full constructor */
	public Tmp_Formexcel_DefcellId(String enterpriseNo, String warehouseNo,
			String ownerNo, String wareNo, String areaNo, String stockNo,
			String stockX, String bayX, String stockY, String prefix,
			String cellNo, String cellStatus, String mixSupplier,
			Integer mixFlag, Double maxQty, Double maxCase, Double maxWeight,
			Double maxVolume, Integer pickOrder, String status, Double rowId) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.wareNo = wareNo;
		this.areaNo = areaNo;
		this.stockNo = stockNo;
		this.stockX = stockX;
		this.bayX = bayX;
		this.stockY = stockY;
		this.prefix = prefix;
		this.cellNo = cellNo;
		this.cellStatus = cellStatus;
		this.mixSupplier = mixSupplier;
		this.mixFlag = mixFlag;
		this.maxQty = maxQty;
		this.maxCase = maxCase;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.pickOrder = pickOrder;
		this.status = status;
		this.rowId = rowId;
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

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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

	@Column(name = "STOCK_X", nullable = false, length = 5)
	public String getStockX() {
		return this.stockX;
	}

	public void setStockX(String stockX) {
		this.stockX = stockX;
	}

	@Column(name = "BAY_X", nullable = false, length = 5)
	public String getBayX() {
		return this.bayX;
	}

	public void setBayX(String bayX) {
		this.bayX = bayX;
	}

	@Column(name = "STOCK_Y", nullable = false, length = 5)
	public String getStockY() {
		return this.stockY;
	}

	public void setStockY(String stockY) {
		this.stockY = stockY;
	}

	@Column(name = "PREFIX", nullable = false, length = 20)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "CELL_STATUS", nullable = false, length = 1)
	public String getCellStatus() {
		return this.cellStatus;
	}

	public void setCellStatus(String cellStatus) {
		this.cellStatus = cellStatus;
	}

	@Column(name = "MIX_SUPPLIER", nullable = false, length = 1)
	public String getMixSupplier() {
		return this.mixSupplier;
	}

	public void setMixSupplier(String mixSupplier) {
		this.mixSupplier = mixSupplier;
	}

	@Column(name = "MIX_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getMixFlag() {
		return this.mixFlag;
	}

	public void setMixFlag(Integer mixFlag) {
		this.mixFlag = mixFlag;
	}

	@Column(name = "MAX_QTY", nullable = false, precision = 3, scale = 0)
	public Double getMaxQty() {
		return this.maxQty;
	}

	public void setMaxQty(Double maxQty) {
		this.maxQty = maxQty;
	}

	@Column(name = "MAX_CASE", nullable = false, precision = 13, scale = 5)
	public Double getMaxCase() {
		return this.maxCase;
	}

	public void setMaxCase(Double maxCase) {
		this.maxCase = maxCase;
	}

	@Column(name = "MAX_WEIGHT", nullable = false, precision = 13, scale = 5)
	public Double getMaxWeight() {
		return this.maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Column(name = "MAX_VOLUME", nullable = false, precision = 13, scale = 5)
	public Double getMaxVolume() {
		return this.maxVolume;
	}

	public void setMaxVolume(Double maxVolume) {
		this.maxVolume = maxVolume;
	}

	@Column(name = "PICK_ORDER", nullable = false, precision = 5, scale = 0)
	public Integer getPickOrder() {
		return this.pickOrder;
	}

	public void setPickOrder(Integer pickOrder) {
		this.pickOrder = pickOrder;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)
	public Double  getRowId() {
		return this.rowId;
	}

	public void setRowId(Double  rowId) {
		this.rowId = rowId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Tmp_Formexcel_DefcellId))
			return false;
		Tmp_Formexcel_DefcellId castOther = (Tmp_Formexcel_DefcellId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getWareNo() == castOther.getWareNo()) || (this
						.getWareNo() != null && castOther.getWareNo() != null && this
						.getWareNo().equals(castOther.getWareNo())))
				&& ((this.getAreaNo() == castOther.getAreaNo()) || (this
						.getAreaNo() != null && castOther.getAreaNo() != null && this
						.getAreaNo().equals(castOther.getAreaNo())))
				&& ((this.getStockNo() == castOther.getStockNo()) || (this
						.getStockNo() != null && castOther.getStockNo() != null && this
						.getStockNo().equals(castOther.getStockNo())))
				&& ((this.getStockX() == castOther.getStockX()) || (this
						.getStockX() != null && castOther.getStockX() != null && this
						.getStockX().equals(castOther.getStockX())))
				&& ((this.getBayX() == castOther.getBayX()) || (this.getBayX() != null
						&& castOther.getBayX() != null && this.getBayX()
						.equals(castOther.getBayX())))
				&& ((this.getStockY() == castOther.getStockY()) || (this
						.getStockY() != null && castOther.getStockY() != null && this
						.getStockY().equals(castOther.getStockY())))
				&& ((this.getPrefix() == castOther.getPrefix()) || (this
						.getPrefix() != null && castOther.getPrefix() != null && this
						.getPrefix().equals(castOther.getPrefix())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())))
				&& ((this.getCellStatus() == castOther.getCellStatus()) || (this
						.getCellStatus() != null
						&& castOther.getCellStatus() != null && this
						.getCellStatus().equals(castOther.getCellStatus())))
				&& ((this.getMixSupplier() == castOther.getMixSupplier()) || (this
						.getMixSupplier() != null
						&& castOther.getMixSupplier() != null && this
						.getMixSupplier().equals(castOther.getMixSupplier())))
				&& ((this.getMixFlag() == castOther.getMixFlag()) || (this
						.getMixFlag() != null && castOther.getMixFlag() != null && this
						.getMixFlag().equals(castOther.getMixFlag())))
				&& ((this.getMaxQty() == castOther.getMaxQty()) || (this
						.getMaxQty() != null && castOther.getMaxQty() != null && this
						.getMaxQty().equals(castOther.getMaxQty())))
				&& ((this.getMaxCase() == castOther.getMaxCase()) || (this
						.getMaxCase() != null && castOther.getMaxCase() != null && this
						.getMaxCase().equals(castOther.getMaxCase())))
				&& ((this.getMaxWeight() == castOther.getMaxWeight()) || (this
						.getMaxWeight() != null
						&& castOther.getMaxWeight() != null && this
						.getMaxWeight().equals(castOther.getMaxWeight())))
				&& ((this.getMaxVolume() == castOther.getMaxVolume()) || (this
						.getMaxVolume() != null
						&& castOther.getMaxVolume() != null && this
						.getMaxVolume().equals(castOther.getMaxVolume())))
				&& ((this.getPickOrder() == castOther.getPickOrder()) || (this
						.getPickOrder() != null
						&& castOther.getPickOrder() != null && this
						.getPickOrder().equals(castOther.getPickOrder())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())));
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
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getWareNo() == null ? 0 : this.getWareNo().hashCode());
		result = 37 * result
				+ (getAreaNo() == null ? 0 : this.getAreaNo().hashCode());
		result = 37 * result
				+ (getStockNo() == null ? 0 : this.getStockNo().hashCode());
		result = 37 * result
				+ (getStockX() == null ? 0 : this.getStockX().hashCode());
		result = 37 * result
				+ (getBayX() == null ? 0 : this.getBayX().hashCode());
		result = 37 * result
				+ (getStockY() == null ? 0 : this.getStockY().hashCode());
		result = 37 * result
				+ (getPrefix() == null ? 0 : this.getPrefix().hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		result = 37
				* result
				+ (getCellStatus() == null ? 0 : this.getCellStatus()
						.hashCode());
		result = 37
				* result
				+ (getMixSupplier() == null ? 0 : this.getMixSupplier()
						.hashCode());
		result = 37 * result
				+ (getMixFlag() == null ? 0 : this.getMixFlag().hashCode());
		result = 37 * result
				+ (getMaxQty() == null ? 0 : this.getMaxQty().hashCode());
		result = 37 * result
				+ (getMaxCase() == null ? 0 : this.getMaxCase().hashCode());
		result = 37 * result
				+ (getMaxWeight() == null ? 0 : this.getMaxWeight().hashCode());
		result = 37 * result
				+ (getMaxVolume() == null ? 0 : this.getMaxVolume().hashCode());
		result = 37 * result
				+ (getPickOrder() == null ? 0 : this.getPickOrder().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		return result;
	}

}