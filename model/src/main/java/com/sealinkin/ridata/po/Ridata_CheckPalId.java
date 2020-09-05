package com.sealinkin.ridata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RidataCheckPalId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Ridata_CheckPalId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String labelNo;
	private String checkNo;
	private Short checkRowId;
	private String articleNo;
	private Double packingQty;
	private Double checkQty;
	private String status;
	private String printerGroupNo;
	private String dockNo;
	private String containerNo;
	private String fixpalFlag;
	private String seasonFlag;
	private String quality;
	private String scanLabelNo;
	private String businessType;
	private String cellNo;
	private String firstcheckLabelNo;

	// Constructors

	/** default constructor */
	public Ridata_CheckPalId() {
	}

	/** minimal constructor */
	public Ridata_CheckPalId(String warehouseNo, String ownerNo, String labelNo,
			String checkNo, Short checkRowId, String articleNo,
			Double packingQty, Double checkQty, String status,
			String printerGroupNo, String dockNo, String containerNo,
			String fixpalFlag, String quality, String scanLabelNo,
			String businessType, String cellNo, String firstcheckLabelNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.labelNo = labelNo;
		this.checkNo = checkNo;
		this.checkRowId = checkRowId;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.checkQty = checkQty;
		this.status = status;
		this.printerGroupNo = printerGroupNo;
		this.dockNo = dockNo;
		this.containerNo = containerNo;
		this.fixpalFlag = fixpalFlag;
		this.quality = quality;
		this.scanLabelNo = scanLabelNo;
		this.businessType = businessType;
		this.cellNo = cellNo;
		this.firstcheckLabelNo = firstcheckLabelNo;
	}

	/** full constructor */
	public Ridata_CheckPalId(String warehouseNo, String ownerNo, String labelNo,
			String checkNo, Short checkRowId, String articleNo,
			Double packingQty, Double checkQty, String status,
			String printerGroupNo, String dockNo, String containerNo,
			String fixpalFlag, String seasonFlag, String quality,
			String scanLabelNo, String businessType, String cellNo,
			String firstcheckLabelNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.labelNo = labelNo;
		this.checkNo = checkNo;
		this.checkRowId = checkRowId;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.checkQty = checkQty;
		this.status = status;
		this.printerGroupNo = printerGroupNo;
		this.dockNo = dockNo;
		this.containerNo = containerNo;
		this.fixpalFlag = fixpalFlag;
		this.seasonFlag = seasonFlag;
		this.quality = quality;
		this.scanLabelNo = scanLabelNo;
		this.businessType = businessType;
		this.cellNo = cellNo;
		this.firstcheckLabelNo = firstcheckLabelNo;
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

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "CHECK_NO", nullable = false, length = 20)
	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Column(name = "CHECK_ROW_ID", nullable = false, precision = 4, scale = 0)
	public Short getCheckRowId() {
		return this.checkRowId;
	}

	public void setCheckRowId(Short checkRowId) {
		this.checkRowId = checkRowId;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "CHECK_QTY", nullable = false, precision = 18, scale = 5)
	public Double getCheckQty() {
		return this.checkQty;
	}

	public void setCheckQty(Double checkQty) {
		this.checkQty = checkQty;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	@Column(name = "DOCK_NO", nullable = false, length = 3)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Column(name = "CONTAINER_NO", nullable = false, length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	@Column(name = "FIXPAL_FLAG", nullable = false, length = 1)
	public String getFixpalFlag() {
		return this.fixpalFlag;
	}

	public void setFixpalFlag(String fixpalFlag) {
		this.fixpalFlag = fixpalFlag;
	}

	@Column(name = "SEASON_FLAG", length = 1)
	public String getSeasonFlag() {
		return this.seasonFlag;
	}

	public void setSeasonFlag(String seasonFlag) {
		this.seasonFlag = seasonFlag;
	}

	@Column(name = "QUALITY", nullable = false, length = 1)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "SCAN_LABEL_NO", nullable = false, length = 24)
	public String getScanLabelNo() {
		return this.scanLabelNo;
	}

	public void setScanLabelNo(String scanLabelNo) {
		this.scanLabelNo = scanLabelNo;
	}

	@Column(name = "BUSINESS_TYPE", nullable = false, length = 2)
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "FIRSTCHECK_LABEL_NO", nullable = false, length = 24)
	public String getFirstcheckLabelNo() {
		return this.firstcheckLabelNo;
	}

	public void setFirstcheckLabelNo(String firstcheckLabelNo) {
		this.firstcheckLabelNo = firstcheckLabelNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Ridata_CheckPalId))
			return false;
		Ridata_CheckPalId castOther = (Ridata_CheckPalId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getLabelNo() == castOther.getLabelNo()) || (this
						.getLabelNo() != null && castOther.getLabelNo() != null && this
						.getLabelNo().equals(castOther.getLabelNo())))
				&& ((this.getCheckNo() == castOther.getCheckNo()) || (this
						.getCheckNo() != null && castOther.getCheckNo() != null && this
						.getCheckNo().equals(castOther.getCheckNo())))
				&& ((this.getCheckRowId() == castOther.getCheckRowId()) || (this
						.getCheckRowId() != null
						&& castOther.getCheckRowId() != null && this
						.getCheckRowId().equals(castOther.getCheckRowId())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getCheckQty() == castOther.getCheckQty()) || (this
						.getCheckQty() != null
						&& castOther.getCheckQty() != null && this
						.getCheckQty().equals(castOther.getCheckQty())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getPrinterGroupNo() == castOther.getPrinterGroupNo()) || (this
						.getPrinterGroupNo() != null
						&& castOther.getPrinterGroupNo() != null && this
						.getPrinterGroupNo().equals(
								castOther.getPrinterGroupNo())))
				&& ((this.getDockNo() == castOther.getDockNo()) || (this
						.getDockNo() != null && castOther.getDockNo() != null && this
						.getDockNo().equals(castOther.getDockNo())))
				&& ((this.getContainerNo() == castOther.getContainerNo()) || (this
						.getContainerNo() != null
						&& castOther.getContainerNo() != null && this
						.getContainerNo().equals(castOther.getContainerNo())))
				&& ((this.getFixpalFlag() == castOther.getFixpalFlag()) || (this
						.getFixpalFlag() != null
						&& castOther.getFixpalFlag() != null && this
						.getFixpalFlag().equals(castOther.getFixpalFlag())))
				&& ((this.getSeasonFlag() == castOther.getSeasonFlag()) || (this
						.getSeasonFlag() != null
						&& castOther.getSeasonFlag() != null && this
						.getSeasonFlag().equals(castOther.getSeasonFlag())))
				&& ((this.getQuality() == castOther.getQuality()) || (this
						.getQuality() != null && castOther.getQuality() != null && this
						.getQuality().equals(castOther.getQuality())))
				&& ((this.getScanLabelNo() == castOther.getScanLabelNo()) || (this
						.getScanLabelNo() != null
						&& castOther.getScanLabelNo() != null && this
						.getScanLabelNo().equals(castOther.getScanLabelNo())))
				&& ((this.getBusinessType() == castOther.getBusinessType()) || (this
						.getBusinessType() != null
						&& castOther.getBusinessType() != null && this
						.getBusinessType().equals(castOther.getBusinessType())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())))
				&& ((this.getFirstcheckLabelNo() == castOther
						.getFirstcheckLabelNo()) || (this
						.getFirstcheckLabelNo() != null
						&& castOther.getFirstcheckLabelNo() != null && this
						.getFirstcheckLabelNo().equals(
								castOther.getFirstcheckLabelNo())));
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
				+ (getLabelNo() == null ? 0 : this.getLabelNo().hashCode());
		result = 37 * result
				+ (getCheckNo() == null ? 0 : this.getCheckNo().hashCode());
		result = 37
				* result
				+ (getCheckRowId() == null ? 0 : this.getCheckRowId()
						.hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37 * result
				+ (getCheckQty() == null ? 0 : this.getCheckQty().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getPrinterGroupNo() == null ? 0 : this.getPrinterGroupNo()
						.hashCode());
		result = 37 * result
				+ (getDockNo() == null ? 0 : this.getDockNo().hashCode());
		result = 37
				* result
				+ (getContainerNo() == null ? 0 : this.getContainerNo()
						.hashCode());
		result = 37
				* result
				+ (getFixpalFlag() == null ? 0 : this.getFixpalFlag()
						.hashCode());
		result = 37
				* result
				+ (getSeasonFlag() == null ? 0 : this.getSeasonFlag()
						.hashCode());
		result = 37 * result
				+ (getQuality() == null ? 0 : this.getQuality().hashCode());
		result = 37
				* result
				+ (getScanLabelNo() == null ? 0 : this.getScanLabelNo()
						.hashCode());
		result = 37
				* result
				+ (getBusinessType() == null ? 0 : this.getBusinessType()
						.hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		result = 37
				* result
				+ (getFirstcheckLabelNo() == null ? 0 : this
						.getFirstcheckLabelNo().hashCode());
		return result;
	}

}