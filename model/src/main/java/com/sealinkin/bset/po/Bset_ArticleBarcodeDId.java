package com.sealinkin.bset.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BsetArticleBarcodeDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Bset_ArticleBarcodeDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private Integer serialNo;
	private String articleNo;
	private Double packingQty;
	private String barcode;
	private String status;
	private String barcodeType;
	private String updtName;
	private Date updtDate;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Bset_ArticleBarcodeDId() {
	}

	/** minimal constructor */
	public Bset_ArticleBarcodeDId(String warehouseNo, String ownerNo,
			Integer serialNo, String articleNo, Double packingQty,
			String status, String barcodeType) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.serialNo = serialNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.status = status;
		this.barcodeType = barcodeType;
	}

	/** full constructor */
	public Bset_ArticleBarcodeDId(String warehouseNo, String ownerNo,
			Integer serialNo, String articleNo, Double packingQty,
			String barcode, String status, String barcodeType, String updtName,
			Date updtDate, String enterpriseNo) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.serialNo = serialNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.barcode = barcode;
		this.status = status;
		this.barcodeType = barcodeType;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.enterpriseNo = enterpriseNo;
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

	@Column(name = "SERIAL_NO", nullable = false, precision = 6, scale = 0)
	public Integer getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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

	@Column(name = "BARCODE", length = 20)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BARCODE_TYPE", nullable = false, length = 1)
	public String getBarcodeType() {
		return this.barcodeType;
	}

	public void setBarcodeType(String barcodeType) {
		this.barcodeType = barcodeType;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
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
		if (!(other instanceof Bset_ArticleBarcodeDId))
			return false;
		Bset_ArticleBarcodeDId castOther = (Bset_ArticleBarcodeDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getSerialNo() == castOther.getSerialNo()) || (this
						.getSerialNo() != null
						&& castOther.getSerialNo() != null && this
						.getSerialNo().equals(castOther.getSerialNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getBarcode() == castOther.getBarcode()) || (this
						.getBarcode() != null && castOther.getBarcode() != null && this
						.getBarcode().equals(castOther.getBarcode())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getBarcodeType() == castOther.getBarcodeType()) || (this
						.getBarcodeType() != null
						&& castOther.getBarcodeType() != null && this
						.getBarcodeType().equals(castOther.getBarcodeType())))
				&& ((this.getUpdtName() == castOther.getUpdtName()) || (this
						.getUpdtName() != null
						&& castOther.getUpdtName() != null && this
						.getUpdtName().equals(castOther.getUpdtName())))
				&& ((this.getUpdtDate() == castOther.getUpdtDate()) || (this
						.getUpdtDate() != null
						&& castOther.getUpdtDate() != null && this
						.getUpdtDate().equals(castOther.getUpdtDate())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())));
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
				+ (getSerialNo() == null ? 0 : this.getSerialNo().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37 * result
				+ (getBarcode() == null ? 0 : this.getBarcode().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getBarcodeType() == null ? 0 : this.getBarcodeType()
						.hashCode());
		result = 37 * result
				+ (getUpdtName() == null ? 0 : this.getUpdtName().hashCode());
		result = 37 * result
				+ (getUpdtDate() == null ? 0 : this.getUpdtDate().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		return result;
	}

}