package com.sealinkin.sodata.po;

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
 * SodataOutstockD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SODATA_OUTSTOCK_D")
public class Sodata_OutstockD implements java.io.Serializable {

	// Fields

	private Sodata_OutstockDId id;
	
	private String sourceNo;
	private Short poId;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private Double articleQty;
	private Double realQty;
	private String SCellNo;
	private Long SCellId;
	private String DCellNo;
	private Long DCellId;
	private String status;
	private String assignName;
	private String outstockName;
	private Date outstockDate;

	// Constructors

	/** default constructor */
	public Sodata_OutstockD() {
	}

	/** minimal constructor */
	public Sodata_OutstockD(Sodata_OutstockDId id,
			String sourceNo, Short poId, String articleNo, Long articleId,
			Double packingQty, Double articleQty, Double realQty,
			String SCellNo, Long SCellId, String DCellNo, Long DCellId,
			String status, String assignName) {
		this.id = id;
		this.sourceNo = sourceNo;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.status = status;
		this.assignName = assignName;
	}

	/** full constructor */
	public Sodata_OutstockD(Sodata_OutstockDId id, 
			String sourceNo, Short poId, String articleNo, Long articleId,
			Double packingQty, Double articleQty, Double realQty,
			String SCellNo, Long SCellId, String DCellNo, Long DCellId,
			String status, String assignName, String outstockName,
			Date outstockDate) {
		this.id = id;
		this.sourceNo = sourceNo;
		this.poId = poId;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.realQty = realQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.status = status;
		this.assignName = assignName;
		this.outstockName = outstockName;
		this.outstockDate = outstockDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "outstockNo", column = @Column(name = "OUTSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "divideId", column = @Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)) })
	public Sodata_OutstockDId getId() {
		return this.id;
	}

	public void setId(Sodata_OutstockDId id) {
		this.id = id;
	}


	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
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

	@Column(name = "S_CELL_NO", nullable = false, length = 24)
	public String getSCellNo() {
		return this.SCellNo;
	}

	public void setSCellNo(String SCellNo) {
		this.SCellNo = SCellNo;
	}

	@Column(name = "S_CELL_ID", nullable = false, precision = 10, scale = 0)
	public Long getSCellId() {
		return this.SCellId;
	}

	public void setSCellId(Long SCellId) {
		this.SCellId = SCellId;
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

}