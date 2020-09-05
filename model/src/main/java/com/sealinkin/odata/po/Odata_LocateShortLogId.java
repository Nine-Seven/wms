package com.sealinkin.odata.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OdataLocateShortLogId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_LocateShortLogId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enterpriseNo;
	private String warehouseNo;
	private String waveNo;
	private Long logSerial;
	private String ownerNo;
	private String expNo;
	private String custNo;
	private String subCustNo;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private String cellNo;
	private Double stockQty;
	private Double locateQty;
	private String operateType;
	private String shortReason;
	private Long transGroupNo;

	// Constructors

	/** default constructor */
	public Odata_LocateShortLogId() {
	}

	/** minimal constructor */
	public Odata_LocateShortLogId(String enterpriseNo, String warehouseNo,
			String waveNo, Long logSerial, String ownerNo, String expNo,
			String custNo, String subCustNo, String articleNo, Long articleId,
			Double packingQty, String cellNo, Double stockQty,
			Double locateQty, String operateType, Long transGroupNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.waveNo = waveNo;
		this.logSerial = logSerial;
		this.ownerNo = ownerNo;
		this.expNo = expNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.cellNo = cellNo;
		this.stockQty = stockQty;
		this.locateQty = locateQty;
		this.operateType = operateType;
		this.transGroupNo = transGroupNo;
	}

	/** full constructor */
	public Odata_LocateShortLogId(String enterpriseNo, String warehouseNo,
			String waveNo, Long logSerial, String ownerNo, String expNo,
			String custNo, String subCustNo, String articleNo, Long articleId,
			Double packingQty, String cellNo, Double stockQty,
			Double locateQty, String operateType, String shortReason,
			Long transGroupNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.waveNo = waveNo;
		this.logSerial = logSerial;
		this.ownerNo = ownerNo;
		this.expNo = expNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.cellNo = cellNo;
		this.stockQty = stockQty;
		this.locateQty = locateQty;
		this.operateType = operateType;
		this.shortReason = shortReason;
		this.transGroupNo = transGroupNo;
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

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}

	@Column(name = "LOG_SERIAL", nullable = false, precision = 10, scale = 0)
	public Long getLogSerial() {
		return this.logSerial;
	}

	public void setLogSerial(Long logSerial) {
		this.logSerial = logSerial;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 5)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "SUB_CUST_NO", nullable = false, length = 20)
	public String getSubCustNo() {
		return this.subCustNo;
	}

	public void setSubCustNo(String subCustNo) {
		this.subCustNo = subCustNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "ARTICLE_ID", nullable = false, precision = 12, scale = 0)
	public Long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "STOCK_QTY", nullable = false, precision = 18, scale = 5)
	public Double getStockQty() {
		return this.stockQty;
	}

	public void setStockQty(Double stockQty) {
		this.stockQty = stockQty;
	}

	@Column(name = "LOCATE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getLocateQty() {
		return this.locateQty;
	}

	public void setLocateQty(Double locateQty) {
		this.locateQty = locateQty;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "SHORT_REASON", length = 100)
	public String getShortReason() {
		return this.shortReason;
	}

	public void setShortReason(String shortReason) {
		this.shortReason = shortReason;
	}

	@Column(name = "TRANS_GROUP_NO", nullable = false, precision = 10, scale = 0)
	public Long getTransGroupNo() {
		return this.transGroupNo;
	}

	public void setTransGroupNo(Long transGroupNo) {
		this.transGroupNo = transGroupNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_LocateShortLogId))
			return false;
		Odata_LocateShortLogId castOther = (Odata_LocateShortLogId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getWaveNo() == castOther.getWaveNo()) || (this
						.getWaveNo() != null && castOther.getWaveNo() != null && this
						.getWaveNo().equals(castOther.getWaveNo())))
				&& ((this.getLogSerial() == castOther.getLogSerial()) || (this
						.getLogSerial() != null
						&& castOther.getLogSerial() != null && this
						.getLogSerial().equals(castOther.getLogSerial())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getExpNo() == castOther.getExpNo()) || (this
						.getExpNo() != null && castOther.getExpNo() != null && this
						.getExpNo().equals(castOther.getExpNo())))
				&& ((this.getCustNo() == castOther.getCustNo()) || (this
						.getCustNo() != null && castOther.getCustNo() != null && this
						.getCustNo().equals(castOther.getCustNo())))
				&& ((this.getSubCustNo() == castOther.getSubCustNo()) || (this
						.getSubCustNo() != null
						&& castOther.getSubCustNo() != null && this
						.getSubCustNo().equals(castOther.getSubCustNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getArticleId() == castOther.getArticleId()) || (this
						.getArticleId() != null
						&& castOther.getArticleId() != null && this
						.getArticleId().equals(castOther.getArticleId())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())))
				&& ((this.getStockQty() == castOther.getStockQty()) || (this
						.getStockQty() != null
						&& castOther.getStockQty() != null && this
						.getStockQty().equals(castOther.getStockQty())))
				&& ((this.getLocateQty() == castOther.getLocateQty()) || (this
						.getLocateQty() != null
						&& castOther.getLocateQty() != null && this
						.getLocateQty().equals(castOther.getLocateQty())))
				&& ((this.getOperateType() == castOther.getOperateType()) || (this
						.getOperateType() != null
						&& castOther.getOperateType() != null && this
						.getOperateType().equals(castOther.getOperateType())))
				&& ((this.getShortReason() == castOther.getShortReason()) || (this
						.getShortReason() != null
						&& castOther.getShortReason() != null && this
						.getShortReason().equals(castOther.getShortReason())))
				&& ((this.getTransGroupNo() == castOther.getTransGroupNo()) || (this
						.getTransGroupNo() != null
						&& castOther.getTransGroupNo() != null && this
						.getTransGroupNo().equals(castOther.getTransGroupNo())));
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
				+ (getWaveNo() == null ? 0 : this.getWaveNo().hashCode());
		result = 37 * result
				+ (getLogSerial() == null ? 0 : this.getLogSerial().hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getExpNo() == null ? 0 : this.getExpNo().hashCode());
		result = 37 * result
				+ (getCustNo() == null ? 0 : this.getCustNo().hashCode());
		result = 37 * result
				+ (getSubCustNo() == null ? 0 : this.getSubCustNo().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37 * result
				+ (getArticleId() == null ? 0 : this.getArticleId().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		result = 37 * result
				+ (getStockQty() == null ? 0 : this.getStockQty().hashCode());
		result = 37 * result
				+ (getLocateQty() == null ? 0 : this.getLocateQty().hashCode());
		result = 37
				* result
				+ (getOperateType() == null ? 0 : this.getOperateType()
						.hashCode());
		result = 37
				* result
				+ (getShortReason() == null ? 0 : this.getShortReason()
						.hashCode());
		result = 37
				* result
				+ (getTransGroupNo() == null ? 0 : this.getTransGroupNo()
						.hashCode());
		return result;
	}

}