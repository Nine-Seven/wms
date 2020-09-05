package com.sealinkin.rodata.po;

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
 * RodataOutstockD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_OUTSTOCK_D")
public class Rodata_OutstockD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_OutstockDId id;
	private Short poId;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private Double articleQty;
	private Double realQty;
	private String SLabelNo;
	private String DCellNo;
	private Long DCellId;
	private Long outstockCellId;
	private String outstockContainerNo;
	private String status;
	private String assignName;
	private String outstockName;
	private Date outstockDate;
	private String stockType;
	private String stockValue;
	private String scanName;
	private Double outstockQty;
	private Date scanDate;
	private String labelNo;
	private String subLabelNo;
	private String waveNo;

	// Constructors

	/** default constructor */
	public Rodata_OutstockD() {
	}

	/** minimal constructor */
	public Rodata_OutstockD(Rodata_OutstockDId id, Short poId, String articleNo,
			Long articleId, Double packingQty, Double articleQty,
			Double realQty, String SLabelNo, String DCellNo, Long DCellId,
			String status, String assignName, String stockType,
			String stockValue, Double outstockQty, String labelNo,
			String subLabelNo, String waveNo) {
		this.id = id;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.SLabelNo = SLabelNo;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.status = status;
		this.assignName = assignName;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.outstockQty = outstockQty;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.waveNo = waveNo;
	}

	/** full constructor */
	public Rodata_OutstockD(Rodata_OutstockDId id, Short poId, String articleNo,
			Long articleId, Double packingQty, Double articleQty,
			Double realQty, String SLabelNo, String DCellNo, Long DCellId,
			Long outstockCellId, String outstockContainerNo, String status,
			String assignName, String outstockName, Date outstockDate,
			String stockType, String stockValue, String scanName,
			Double outstockQty, Date scanDate, String labelNo,
			String subLabelNo, String waveNo) {
		this.id = id;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.SLabelNo = SLabelNo;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.outstockCellId = outstockCellId;
		this.outstockContainerNo = outstockContainerNo;
		this.status = status;
		this.assignName = assignName;
		this.outstockName = outstockName;
		this.outstockDate = outstockDate;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.scanName = scanName;
		this.outstockQty = outstockQty;
		this.scanDate = scanDate;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.waveNo = waveNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "outstockNo", column = @Column(name = "OUTSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "sourceNo", column = @Column(name = "SOURCE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "divideId", column = @Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "SCellNo", column = @Column(name = "S_CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "SCellId", column = @Column(name = "S_CELL_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "outstockCellNo", column = @Column(name = "OUTSTOCK_CELL_NO", nullable = false, length = 24)) })
	public Rodata_OutstockDId getId() {
		return this.id;
	}

	public void setId(Rodata_OutstockDId id) {
		this.id = id;
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

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
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

	@Column(name = "S_LABEL_NO", nullable = false, length = 24)
	public String getSLabelNo() {
		return SLabelNo;
	}

	public void setSLabelNo(String sLabelNo) {
		SLabelNo = sLabelNo;
	}


	@Column(name = "D_CELL_NO", nullable = false, length = 24)
	public String getDCellNo() {
		return this.DCellNo;
	}


	public void setDCellNo(String DCellNo) {
		this.DCellNo = DCellNo;
	}

	@Column(name = "D_CELL_ID", nullable = false, precision = 10, scale = 0)
	public Long getDCellId() {
		return this.DCellId;
	}

	public void setDCellId(Long DCellId) {
		this.DCellId = DCellId;
	}

	@Column(name = "OUTSTOCK_CELL_ID", precision = 10, scale = 0)
	public Long getOutstockCellId() {
		return this.outstockCellId;
	}

	public void setOutstockCellId(Long outstockCellId) {
		this.outstockCellId = outstockCellId;
	}

	@Column(name = "OUTSTOCK_CONTAINER_NO", length = 24)
	public String getOutstockContainerNo() {
		return this.outstockContainerNo;
	}

	public void setOutstockContainerNo(String outstockContainerNo) {
		this.outstockContainerNo = outstockContainerNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ASSIGN_NAME", nullable = false, length = 20)
	public String getAssignName() {
		return this.assignName;
	}

	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	@Column(name = "OUTSTOCK_NAME", length = 20)
	public String getOutstockName() {
		return this.outstockName;
	}

	public void setOutstockName(String outstockName) {
		this.outstockName = outstockName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OUTSTOCK_DATE", length = 7)
	public Date getOutstockDate() {
		return this.outstockDate;
	}

	public void setOutstockDate(Date outstockDate) {
		this.outstockDate = outstockDate;
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

	@Column(name = "SCAN_NAME", length = 20)
	public String getScanName() {
		return this.scanName;
	}

	public void setScanName(String scanName) {
		this.scanName = scanName;
	}

	@Column(name = "OUTSTOCK_QTY", nullable = false, precision = 14, scale = 5)
	public Double getOutstockQty() {
		return this.outstockQty;
	}

	public void setOutstockQty(Double outstockQty) {
		this.outstockQty = outstockQty;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCAN_DATE", length = 7)
	public Date getScanDate() {
		return this.scanDate;
	}

	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
	}

	@Column(name = "SUB_LABEL_NO", nullable = false, length = 24)
	public String getSubLabelNo() {
		return this.subLabelNo;
	}

	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
	}

}