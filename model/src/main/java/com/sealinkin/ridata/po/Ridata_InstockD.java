package com.sealinkin.ridata.po;

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
 * RidataInstockD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RIDATA_INSTOCK_D" )
public class Ridata_InstockD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_InstockDId id;
	private String cellNo;
	private Long cellId;
	private String containerNo;
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
	private String instockName;
	private Date instockDate;
	private String assignName;
	private String labelNo;
	private String scanLabelNo;
	private String businessType;
	private Long directSerial;
	private String realContainerNo;
	private String stockType;
	private String stockValue;

	// Constructors

	/** default constructor */
	public Ridata_InstockD() {
	}

	/** minimal constructor */
	public Ridata_InstockD(Ridata_InstockDId id, String cellNo, Long cellId,
			String containerNo, String articleNo, Long articleId,
			Double packingQty, String destCellNo, Long destCellId,
			Double articleQty, Double realQty, String sourceNo, String status,
			String assignName, String labelNo, String scanLabelNo,
			String businessType, Long directSerial, String stockType,
			String stockValue) {
		this.id = id;
		this.cellNo = cellNo;
		this.cellId = cellId;
		this.containerNo = containerNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.destCellNo = destCellNo;
		this.destCellId = destCellId;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.sourceNo = sourceNo;
		this.status = status;
		this.assignName = assignName;
		this.labelNo = labelNo;
		this.scanLabelNo = scanLabelNo;
		this.businessType = businessType;
		this.directSerial = directSerial;
		this.stockType = stockType;
		this.stockValue = stockValue;
	}

	/** full constructor */
	public Ridata_InstockD(Ridata_InstockDId id, String cellNo, Long cellId,
			String containerNo, String articleNo, Long articleId,
			Double packingQty, String destCellNo, Long destCellId,
			Double articleQty, String realCellNo, Double realQty,
			String sourceNo, String status, String instockName,
			Date instockDate, String assignName, String labelNo,
			String scanLabelNo, String businessType, Long directSerial,
			String realContainerNo, String stockType, String stockValue) {
		this.id = id;
		this.cellNo = cellNo;
		this.cellId = cellId;
		this.containerNo = containerNo;
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
		this.instockName = instockName;
		this.instockDate = instockDate;
		this.assignName = assignName;
		this.labelNo = labelNo;
		this.scanLabelNo = scanLabelNo;
		this.businessType = businessType;
		this.directSerial = directSerial;
		this.realContainerNo = realContainerNo;
		this.stockType = stockType;
		this.stockValue = stockValue;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "instockNo", column = @Column(name = "INSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "instockId", column = @Column(name = "INSTOCK_ID", nullable = false, precision = 10, scale = 0)) })
	public Ridata_InstockDId getId() {
		return this.id;
	}

	public void setId(Ridata_InstockDId id) {
		this.id = id;
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

	@Column(name = "CONTAINER_NO", nullable = false, length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
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

	@Column(name = "REAL_QTY", nullable = false, precision = 18, scale = 5)
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

	@Column(name = "INSTOCK_NAME", length = 20)
	public String getInstockName() {
		return this.instockName;
	}

	public void setInstockName(String instockName) {
		this.instockName = instockName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INSTOCK_DATE", length = 7)
	public Date getInstockDate() {
		return this.instockDate;
	}

	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}

	@Column(name = "ASSIGN_NAME", nullable = false, length = 20)
	public String getAssignName() {
		return this.assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
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

	@Column(name = "DIRECT_SERIAL", nullable = false, precision = 10, scale = 0)
	public Long getDirectSerial() {
		return this.directSerial;
	}

	public void setDirectSerial(Long directSerial) {
		this.directSerial = directSerial;
	}

	@Column(name = "REAL_CONTAINER_NO", length = 24)
	public String getRealContainerNo() {
		return this.realContainerNo;
	}

	public void setRealContainerNo(String realContainerNo) {
		this.realContainerNo = realContainerNo;
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

}