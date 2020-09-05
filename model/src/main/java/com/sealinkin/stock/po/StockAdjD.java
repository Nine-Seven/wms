package com.sealinkin.stock.po;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * StockAdjD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_ADJ_D")
public class StockAdjD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StockAdjDId id;
	private String ownerNo;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private Date produceDate;
	private Date expireDate;
	private String quality;
	private String lotNo;
	private String importBatchNo;
	private String rsvBatch1;
	private String rsvBatch2;
	private String rsvBatch3;
	private String rsvBatch4;
	private String rsvBatch5;
	private String rsvBatch6;
	private String rsvBatch7;
	private String rsvBatch8;
	private String cellNo;
	private Double planQty;
	private Double realQty;
	private String adjDes;
	private String barcode;
	private String stockType;
	private String stockValue;
	private String labelNo;
	private String status;

	// Constructors

	/** default constructor */
	public StockAdjD() {
	}

	/** minimal constructor */
	public StockAdjD(StockAdjDId id, String ownerNo, String articleNo,
			Long articleId, Double packingQty, Date produceDate,
			Date expireDate, String quality, String lotNo,
			String importBatchNo, String rsvBatch1, String rsvBatch2,
			String rsvBatch3, String rsvBatch4, String rsvBatch5,
			String rsvBatch6, String rsvBatch7, String rsvBatch8,
			String cellNo, Double planQty, String stockType, String stockValue) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.lotNo = lotNo;
		this.importBatchNo = importBatchNo;
		this.rsvBatch1 = rsvBatch1;
		this.rsvBatch2 = rsvBatch2;
		this.rsvBatch3 = rsvBatch3;
		this.rsvBatch4 = rsvBatch4;
		this.rsvBatch5 = rsvBatch5;
		this.rsvBatch6 = rsvBatch6;
		this.rsvBatch7 = rsvBatch7;
		this.rsvBatch8 = rsvBatch8;
		this.cellNo = cellNo;
		this.planQty = planQty;
		this.stockType = stockType;
		this.stockValue = stockValue;
	}

	/** full constructor */
	public StockAdjD(StockAdjDId id, String ownerNo, String articleNo,
			Long articleId, Double packingQty, Date produceDate,
			Date expireDate, String quality, String lotNo,
			String importBatchNo, String rsvBatch1, String rsvBatch2,
			String rsvBatch3, String rsvBatch4, String rsvBatch5,
			String rsvBatch6, String rsvBatch7, String rsvBatch8,
			String cellNo, Double planQty, Double realQty, String adjDes,
			String barcode, String stockType, String stockValue,
			String labelNo, String status) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.lotNo = lotNo;
		this.importBatchNo = importBatchNo;
		this.rsvBatch1 = rsvBatch1;
		this.rsvBatch2 = rsvBatch2;
		this.rsvBatch3 = rsvBatch3;
		this.rsvBatch4 = rsvBatch4;
		this.rsvBatch5 = rsvBatch5;
		this.rsvBatch6 = rsvBatch6;
		this.rsvBatch7 = rsvBatch7;
		this.rsvBatch8 = rsvBatch8;
		this.cellNo = cellNo;
		this.planQty = planQty;
		this.realQty = realQty;
		this.adjDes = adjDes;
		this.barcode = barcode;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.labelNo = labelNo;
		this.status = status;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "adjNo", column = @Column(name = "ADJ_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 3, scale = 0)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)) })
	public StockAdjDId getId() {
		return this.id;
	}

	public void setId(StockAdjDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 13)
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

	@Column(name = "PRODUCE_DATE", nullable = false, length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

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

	@Column(name = "LOT_NO", nullable = false, length = 20)
	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	@Column(name = "IMPORT_BATCH_NO", nullable = false, length = 20)
	public String getImportBatchNo() {
		return this.importBatchNo;
	}

	public void setImportBatchNo(String importBatchNo) {
		this.importBatchNo = importBatchNo;
	}

	@Column(name = "RSV_BATCH1", nullable = false, length = 20)
	public String getRsvBatch1() {
		return this.rsvBatch1;
	}

	public void setRsvBatch1(String rsvBatch1) {
		this.rsvBatch1 = rsvBatch1;
	}

	@Column(name = "RSV_BATCH2", nullable = false, length = 20)
	public String getRsvBatch2() {
		return this.rsvBatch2;
	}

	public void setRsvBatch2(String rsvBatch2) {
		this.rsvBatch2 = rsvBatch2;
	}

	@Column(name = "RSV_BATCH3", nullable = false, length = 20)
	public String getRsvBatch3() {
		return this.rsvBatch3;
	}

	public void setRsvBatch3(String rsvBatch3) {
		this.rsvBatch3 = rsvBatch3;
	}

	@Column(name = "RSV_BATCH4", nullable = false, length = 20)
	public String getRsvBatch4() {
		return this.rsvBatch4;
	}

	public void setRsvBatch4(String rsvBatch4) {
		this.rsvBatch4 = rsvBatch4;
	}

	@Column(name = "RSV_BATCH5", nullable = false, length = 20)
	public String getRsvBatch5() {
		return this.rsvBatch5;
	}

	public void setRsvBatch5(String rsvBatch5) {
		this.rsvBatch5 = rsvBatch5;
	}

	@Column(name = "RSV_BATCH6", nullable = false, length = 20)
	public String getRsvBatch6() {
		return this.rsvBatch6;
	}

	public void setRsvBatch6(String rsvBatch6) {
		this.rsvBatch6 = rsvBatch6;
	}

	@Column(name = "RSV_BATCH7", nullable = false, length = 20)
	public String getRsvBatch7() {
		return this.rsvBatch7;
	}

	public void setRsvBatch7(String rsvBatch7) {
		this.rsvBatch7 = rsvBatch7;
	}

	@Column(name = "RSV_BATCH8", nullable = false, length = 20)
	public String getRsvBatch8() {
		return this.rsvBatch8;
	}

	public void setRsvBatch8(String rsvBatch8) {
		this.rsvBatch8 = rsvBatch8;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "PLAN_QTY", nullable = false, precision = 10, scale = 5)
	public Double getPlanQty() {
		return this.planQty;
	}

	public void setPlanQty(Double planQty) {
		this.planQty = planQty;
	}

	@Column(name = "REAL_QTY", precision = 10, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "ADJ_DES", length = 30)
	public String getAdjDes() {
		return this.adjDes;
	}

	public void setAdjDes(String adjDes) {
		this.adjDes = adjDes;
	}

	@Column(name = "BARCODE", length = 25)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Column(name = "STOCK_VALUE", nullable = false, length = 20)
	public String getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

	@Column(name = "LABEL_NO", length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}