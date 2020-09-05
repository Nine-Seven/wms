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
 * RodataRecedeD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_RECEDE_D")
public class Rodata_RecedeD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_RecedeDId id;
	private String ownerArticleNo;
	private String articleNo;
	private Double packingQty;
	private String lotNo;
	private String quality;
	private Date produceDate;
	private Date expireDate;
	private Double recedeQty;
	private Double outstockQty;
	private Double realQty;
	private Double unitCost;
	private String recedeDes;
	private String errorStatus;
	private Double budgetQty;
	private String cellNo;
	private Double locateQty;
	private String status;
	// Constructors

	/** default constructor */
	public Rodata_RecedeD() {
	}

	/** minimal constructor */
	public Rodata_RecedeD(Rodata_RecedeDId id, String ownerArticleNo,
			String articleNo, Double packingQty, String lotNo,
			Double recedeQty, Double realQty) {
		this.id = id;
		this.ownerArticleNo = ownerArticleNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.lotNo = lotNo;
		this.recedeQty = recedeQty;
		this.realQty = realQty;
	}

	/** full constructor */
	public Rodata_RecedeD(Rodata_RecedeDId id, String ownerArticleNo,
			String articleNo, Double packingQty, String lotNo, String quality,
			Date produceDate, Date expireDate, Double recedeQty,
			Double outstockQty, Double realQty, Double unitCost,
			String recedeDes, String errorStatus, Double budgetQty,
			String cellNo, Double locateQty,String status) {
		this.id = id;
		this.ownerArticleNo = ownerArticleNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.lotNo = lotNo;
		this.quality = quality;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.recedeQty = recedeQty;
		this.outstockQty = outstockQty;
		this.realQty = realQty;
		this.unitCost = unitCost;
		this.recedeDes = recedeDes;
		this.errorStatus = errorStatus;
		this.budgetQty = budgetQty;
		this.cellNo = cellNo;
		this.locateQty = locateQty;
		this.status = status;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "recedeNo", column = @Column(name = "RECEDE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "poId", column = @Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)) })
	public Rodata_RecedeDId getId() {
		return this.id;
	}

	public void setId(Rodata_RecedeDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 25)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
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

	@Column(name = "LOT_NO", nullable = false, length = 32)
	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	@Column(name = "QUALITY", length = 1)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCE_DATE", length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_DATE", length = 7)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "RECEDE_QTY", nullable = false, precision = 14, scale = 5)
	public Double getRecedeQty() {
		return this.recedeQty;
	}

	public void setRecedeQty(Double recedeQty) {
		this.recedeQty = recedeQty;
	}

	@Column(name = "OUTSTOCK_QTY", precision = 14, scale = 5)
	public Double getOutstockQty() {
		return this.outstockQty;
	}

	public void setOutstockQty(Double outstockQty) {
		this.outstockQty = outstockQty;
	}

	@Column(name = "REAL_QTY", nullable = false, precision = 14, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "UNIT_COST", precision = 16, scale = 5)
	public Double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	@Column(name = "RECEDE_DES", length = 60)
	public String getRecedeDes() {
		return this.recedeDes;
	}

	public void setRecedeDes(String recedeDes) {
		this.recedeDes = recedeDes;
	}

	@Column(name = "ERROR_STATUS", length = 100)
	public String getErrorStatus() {
		return this.errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	@Column(name = "BUDGET_QTY", precision = 14, scale = 5)
	public Double getBudgetQty() {
		return this.budgetQty;
	}

	public void setBudgetQty(Double budgetQty) {
		this.budgetQty = budgetQty;
	}

	@Column(name = "CELL_NO", length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "LOCATE_QTY", precision = 14, scale = 5)
	public Double getLocateQty() {
		return this.locateQty;
	}

	public void setLocateQty(Double locateQty) {
		this.locateQty = locateQty;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}