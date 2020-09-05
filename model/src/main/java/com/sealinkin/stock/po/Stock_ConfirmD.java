package com.sealinkin.stock.po;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StockConfirmD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_CONFIRM_D")
public class Stock_ConfirmD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stock_ConfirmDId id;
	private String sourceNo;
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
	private Double articleQty;
	private Double realQty;
	private String stockType;
	private String stockValue;
	private String labelNo;
	private String status;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Stock_ConfirmD() {
	}

	/** minimal constructor */
	public Stock_ConfirmD(Stock_ConfirmDId id, String articleNo, Long articleId,
			Double packingQty, Date produceDate, Date expireDate,
			String quality, String lotNo, String importBatchNo,
			String rsvBatch1, String rsvBatch2, String rsvBatch3,
			String rsvBatch4, String rsvBatch5, String rsvBatch6,
			String rsvBatch7, String rsvBatch8, String cellNo,
			Double articleQty, String stockType, String stockValue,
			String rgstName, Date rgstDate) {
		this.id = id;
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
		this.articleQty = articleQty;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Stock_ConfirmD(Stock_ConfirmDId id, String sourceNo, String articleNo,
			Long articleId, Double packingQty, Date produceDate,
			Date expireDate, String quality, String lotNo,
			String importBatchNo, String rsvBatch1, String rsvBatch2,
			String rsvBatch3, String rsvBatch4, String rsvBatch5,
			String rsvBatch6, String rsvBatch7, String rsvBatch8,
			String cellNo, Double articleQty, Double realQty, String stockType,
			String stockValue, String labelNo, String status, String rgstName,
			Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.sourceNo = sourceNo;
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
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.labelNo = labelNo;
		this.status = status;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "confirmNo", column = @Column(name = "CONFIRM_NO", nullable = false, length = 30)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)) })
	public Stock_ConfirmDId getId() {
		return this.id;
	}

	public void setId(Stock_ConfirmDId id) {
		this.id = id;
	}

	@Column(name = "SOURCE_NO", length = 30)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 20)
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

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 10, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "REAL_QTY", precision = 10, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
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

	@Column(name = "RGST_NAME", nullable = false, length = 30)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 30)
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

}