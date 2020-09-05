package com.sealinkin.idata.po;

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
 * IdataInstockDhty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_INSTOCK_DHTY")
public class Idata_InstockDhty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_InstockDhtyId id;
	private Long directSerial;
	private String instockType;
	private String cellNo;
	private Long cellId;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private String destCellNo;
	private Long destCellId;
	private Double articleQty;
	private String realCellNo;
	private Double realQty;
	private String sourceNo;
	private String status;
	private String authorizedWorker;
	private String subLabelNo;
	private String labelNo;
	private String businessType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String stockType;
	private String stockValue;
	private String autoLocateFlag;

	// Constructors

	/** default constructor */
	public Idata_InstockDhty() {
	}

	/** minimal constructor */
	public Idata_InstockDhty(Idata_InstockDhtyId id, Long directSerial,
			String instockType, String cellNo, Long cellId, String articleNo,
			Long articleId, Double packingQty, String destCellNo,
			Long destCellId, Double articleQty, String sourceNo, String status,
			String subLabelNo, String labelNo, String businessType,
			String rgstName, Date rgstDate, String stockType, String stockValue) {
		this.id = id;
		this.directSerial = directSerial;
		this.instockType = instockType;
		this.cellNo = cellNo;
		this.cellId = cellId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.destCellNo = destCellNo;
		this.destCellId = destCellId;
		this.articleQty = articleQty;
		this.sourceNo = sourceNo;
		this.status = status;
		this.subLabelNo = subLabelNo;
		this.labelNo = labelNo;
		this.businessType = businessType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.stockType = stockType;
		this.stockValue = stockValue;
	}

	/** full constructor */
	public Idata_InstockDhty(Idata_InstockDhtyId id, Long directSerial,
			String instockType, String cellNo, Long cellId, String articleNo,
			Long articleId, Double packingQty, String destCellNo,
			Long destCellId, Double articleQty, String realCellNo,
			Double realQty, String sourceNo, String status,
			String authorizedWorker, String subLabelNo, String labelNo,
			String businessType, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String stockType,
			String stockValue, String autoLocateFlag) {
		this.id = id;
		this.directSerial = directSerial;
		this.instockType = instockType;
		this.cellNo = cellNo;
		this.cellId = cellId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.destCellNo = destCellNo;
		this.destCellId = destCellId;
		this.articleQty = articleQty;
		this.realCellNo = realCellNo;
		this.realQty = realQty;
		this.sourceNo = sourceNo;
		this.status = status;
		this.authorizedWorker = authorizedWorker;
		this.subLabelNo = subLabelNo;
		this.labelNo = labelNo;
		this.businessType = businessType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.autoLocateFlag = autoLocateFlag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "instockNo", column = @Column(name = "INSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "instockId", column = @Column(name = "INSTOCK_ID", nullable = false, length = 20)) })
	public Idata_InstockDhtyId getId() {
		return this.id;
	}

	public void setId(Idata_InstockDhtyId id) {
		this.id = id;
	}

	@Column(name = "DIRECT_SERIAL", nullable = false, precision = 10, scale = 0)
	public Long getDirectSerial() {
		return this.directSerial;
	}

	public void setDirectSerial(Long directSerial) {
		this.directSerial = directSerial;
	}

	@Column(name = "INSTOCK_TYPE", nullable = false, length = 1)
	public String getInstockType() {
		return this.instockType;
	}

	public void setInstockType(String instockType) {
		this.instockType = instockType;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "CELL_ID", nullable = false, precision = 10, scale = 0)
	public Long getCellId() {
		return this.cellId;
	}

	public void setCellId(Long cellId) {
		this.cellId = cellId;
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

	@Column(name = "DEST_CELL_NO", nullable = false, length = 24)
	public String getDestCellNo() {
		return this.destCellNo;
	}

	public void setDestCellNo(String destCellNo) {
		this.destCellNo = destCellNo;
	}

	@Column(name = "DEST_CELL_ID", nullable = false, precision = 10, scale = 0)
	public Long getDestCellId() {
		return this.destCellId;
	}

	public void setDestCellId(Long destCellId) {
		this.destCellId = destCellId;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "REAL_CELL_NO", length = 24)
	public String getRealCellNo() {
		return this.realCellNo;
	}

	public void setRealCellNo(String realCellNo) {
		this.realCellNo = realCellNo;
	}

	@Column(name = "REAL_QTY", precision = 18, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "AUTHORIZED_WORKER", length = 20)
	public String getAuthorizedWorker() {
		return this.authorizedWorker;
	}

	public void setAuthorizedWorker(String authorizedWorker) {
		this.authorizedWorker = authorizedWorker;
	}

	@Column(name = "SUB_LABEL_NO", nullable = false, length = 24)
	public String getSubLabelNo() {
		return this.subLabelNo;
	}

	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "BUSINESS_TYPE", nullable = false, length = 2)
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
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

	@Column(name = "AUTO_LOCATE_FLAG", length = 1)
	public String getAutoLocateFlag() {
		return this.autoLocateFlag;
	}

	public void setAutoLocateFlag(String autoLocateFlag) {
		this.autoLocateFlag = autoLocateFlag;
	}

}