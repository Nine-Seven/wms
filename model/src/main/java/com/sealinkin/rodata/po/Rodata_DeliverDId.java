package com.sealinkin.rodata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RodataDeliverDId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Rodata_DeliverDId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private String deliverNo;
	private String recedeNo;
	private Short poId;
	private String articleNo;
	private Long articleId;
	private String barcode;
	private Double packingQty;
	private String lotNo;
	private Date produceDate;
	private Date expireDate;
	private String quality;
	private String importBatchNo;
	private Double articleQty;
	private Double realQty;
	private String cellNo;
	private String recedeName;
	private Date recedeDate;
	private String itemType;
	private String batchSerialNo;
	private Long cellId;

	// Constructors

	/** default constructor */
	public Rodata_DeliverDId() {
	}

	/** minimal constructor */
	public Rodata_DeliverDId(String warehouseNo, String ownerNo,
			String deliverNo, String recedeNo, Short poId, String articleNo,
			Long articleId, String barcode, Double packingQty, String lotNo,
			Date produceDate, Date expireDate, String quality,
			String importBatchNo, Double articleQty, Double realQty,
			String cellNo, String itemType, String batchSerialNo, Long cellId) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.deliverNo = deliverNo;
		this.recedeNo = recedeNo;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.barcode = barcode;
		this.packingQty = packingQty;
		this.lotNo = lotNo;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.importBatchNo = importBatchNo;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.cellNo = cellNo;
		this.itemType = itemType;
		this.batchSerialNo = batchSerialNo;
		this.cellId = cellId;
	}

	/** full constructor */
	public Rodata_DeliverDId(String warehouseNo, String ownerNo,
			String deliverNo, String recedeNo, Short poId, String articleNo,
			Long articleId, String barcode, Double packingQty, String lotNo,
			Date produceDate, Date expireDate, String quality,
			String importBatchNo, Double articleQty, Double realQty,
			String cellNo, String recedeName, Date recedeDate, String itemType,
			String batchSerialNo, Long cellId) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.deliverNo = deliverNo;
		this.recedeNo = recedeNo;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.barcode = barcode;
		this.packingQty = packingQty;
		this.lotNo = lotNo;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.importBatchNo = importBatchNo;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.cellNo = cellNo;
		this.recedeName = recedeName;
		this.recedeDate = recedeDate;
		this.itemType = itemType;
		this.batchSerialNo = batchSerialNo;
		this.cellId = cellId;
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

	@Column(name = "DELIVER_NO", nullable = false, length = 20)
	public String getDeliverNo() {
		return this.deliverNo;
	}

	public void setDeliverNo(String deliverNo) {
		this.deliverNo = deliverNo;
	}

	@Column(name = "RECEDE_NO", nullable = false, length = 20)
	public String getRecedeNo() {
		return this.recedeNo;
	}

	public void setRecedeNo(String recedeNo) {
		this.recedeNo = recedeNo;
	}

	@Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)
	public Short getPoId() {
		return this.poId;
	}

	public void setPoId(Short poId) {
		this.poId = poId;
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

	@Column(name = "BARCODE", nullable = false, length = 25)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "LOT_NO", nullable = false, length = 20)
	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCE_DATE", nullable = false, length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_DATE", nullable = false, length = 7)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "QUALITY", nullable = false, length = 2)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "IMPORT_BATCH_NO", nullable = false, length = 20)
	public String getImportBatchNo() {
		return this.importBatchNo;
	}

	public void setImportBatchNo(String importBatchNo) {
		this.importBatchNo = importBatchNo;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 14, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "REAL_QTY", nullable = false, precision = 14, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "RECEDE_NAME", length = 20)
	public String getRecedeName() {
		return this.recedeName;
	}

	public void setRecedeName(String recedeName) {
		this.recedeName = recedeName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECEDE_DATE", length = 7)
	public Date getRecedeDate() {
		return this.recedeDate;
	}

	public void setRecedeDate(Date recedeDate) {
		this.recedeDate = recedeDate;
	}

	@Column(name = "ITEM_TYPE", nullable = false, length = 20)
	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Column(name = "BATCH_SERIAL_NO", nullable = false, length = 20)
	public String getBatchSerialNo() {
		return this.batchSerialNo;
	}

	public void setBatchSerialNo(String batchSerialNo) {
		this.batchSerialNo = batchSerialNo;
	}

	@Column(name = "CELL_ID", nullable = false, precision = 10, scale = 0)
	public Long getCellId() {
		return this.cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rodata_DeliverDId))
			return false;
		Rodata_DeliverDId castOther = (Rodata_DeliverDId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getDeliverNo() == castOther.getDeliverNo()) || (this
						.getDeliverNo() != null
						&& castOther.getDeliverNo() != null && this
						.getDeliverNo().equals(castOther.getDeliverNo())))
				&& ((this.getRecedeNo() == castOther.getRecedeNo()) || (this
						.getRecedeNo() != null
						&& castOther.getRecedeNo() != null && this
						.getRecedeNo().equals(castOther.getRecedeNo())))
				&& ((this.getPoId() == castOther.getPoId()) || (this.getPoId() != null
						&& castOther.getPoId() != null && this.getPoId()
						.equals(castOther.getPoId())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getArticleId() == castOther.getArticleId()) || (this
						.getArticleId() != null
						&& castOther.getArticleId() != null && this
						.getArticleId().equals(castOther.getArticleId())))
				&& ((this.getBarcode() == castOther.getBarcode()) || (this
						.getBarcode() != null && castOther.getBarcode() != null && this
						.getBarcode().equals(castOther.getBarcode())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getLotNo() == castOther.getLotNo()) || (this
						.getLotNo() != null && castOther.getLotNo() != null && this
						.getLotNo().equals(castOther.getLotNo())))
				&& ((this.getProduceDate() == castOther.getProduceDate()) || (this
						.getProduceDate() != null
						&& castOther.getProduceDate() != null && this
						.getProduceDate().equals(castOther.getProduceDate())))
				&& ((this.getExpireDate() == castOther.getExpireDate()) || (this
						.getExpireDate() != null
						&& castOther.getExpireDate() != null && this
						.getExpireDate().equals(castOther.getExpireDate())))
				&& ((this.getQuality() == castOther.getQuality()) || (this
						.getQuality() != null && castOther.getQuality() != null && this
						.getQuality().equals(castOther.getQuality())))
				&& ((this.getImportBatchNo() == castOther.getImportBatchNo()) || (this
						.getImportBatchNo() != null
						&& castOther.getImportBatchNo() != null && this
						.getImportBatchNo()
						.equals(castOther.getImportBatchNo())))
				&& ((this.getArticleQty() == castOther.getArticleQty()) || (this
						.getArticleQty() != null
						&& castOther.getArticleQty() != null && this
						.getArticleQty().equals(castOther.getArticleQty())))
				&& ((this.getRealQty() == castOther.getRealQty()) || (this
						.getRealQty() != null && castOther.getRealQty() != null && this
						.getRealQty().equals(castOther.getRealQty())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())))
				&& ((this.getRecedeName() == castOther.getRecedeName()) || (this
						.getRecedeName() != null
						&& castOther.getRecedeName() != null && this
						.getRecedeName().equals(castOther.getRecedeName())))
				&& ((this.getRecedeDate() == castOther.getRecedeDate()) || (this
						.getRecedeDate() != null
						&& castOther.getRecedeDate() != null && this
						.getRecedeDate().equals(castOther.getRecedeDate())))
				&& ((this.getItemType() == castOther.getItemType()) || (this
						.getItemType() != null
						&& castOther.getItemType() != null && this
						.getItemType().equals(castOther.getItemType())))
				&& ((this.getBatchSerialNo() == castOther.getBatchSerialNo()) || (this
						.getBatchSerialNo() != null
						&& castOther.getBatchSerialNo() != null && this
						.getBatchSerialNo()
						.equals(castOther.getBatchSerialNo())))
				&& ((this.getCellId() == castOther.getCellId()) || (this
						.getCellId() != null && castOther.getCellId() != null && this
						.getCellId().equals(castOther.getCellId())));
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
				+ (getDeliverNo() == null ? 0 : this.getDeliverNo().hashCode());
		result = 37 * result
				+ (getRecedeNo() == null ? 0 : this.getRecedeNo().hashCode());
		result = 37 * result
				+ (getPoId() == null ? 0 : this.getPoId().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37 * result
				+ (getArticleId() == null ? 0 : this.getArticleId().hashCode());
		result = 37 * result
				+ (getBarcode() == null ? 0 : this.getBarcode().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37 * result
				+ (getLotNo() == null ? 0 : this.getLotNo().hashCode());
		result = 37
				* result
				+ (getProduceDate() == null ? 0 : this.getProduceDate()
						.hashCode());
		result = 37
				* result
				+ (getExpireDate() == null ? 0 : this.getExpireDate()
						.hashCode());
		result = 37 * result
				+ (getQuality() == null ? 0 : this.getQuality().hashCode());
		result = 37
				* result
				+ (getImportBatchNo() == null ? 0 : this.getImportBatchNo()
						.hashCode());
		result = 37
				* result
				+ (getArticleQty() == null ? 0 : this.getArticleQty()
						.hashCode());
		result = 37 * result
				+ (getRealQty() == null ? 0 : this.getRealQty().hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		result = 37
				* result
				+ (getRecedeName() == null ? 0 : this.getRecedeName()
						.hashCode());
		result = 37
				* result
				+ (getRecedeDate() == null ? 0 : this.getRecedeDate()
						.hashCode());
		result = 37 * result
				+ (getItemType() == null ? 0 : this.getItemType().hashCode());
		result = 37
				* result
				+ (getBatchSerialNo() == null ? 0 : this.getBatchSerialNo()
						.hashCode());
		result = 37 * result
				+ (getCellId() == null ? 0 : this.getCellId().hashCode());
		return result;
	}

}