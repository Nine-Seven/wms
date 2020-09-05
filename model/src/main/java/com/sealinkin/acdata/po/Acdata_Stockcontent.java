package com.sealinkin.acdata.po;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AcdataStockcontent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACDATA_STOCKCONTENT")
public class Acdata_Stockcontent implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private Acdata_StockcontentId id;
	private String barcodeNo;
	private Long inQty;
	private Double inWt;
	private Double inVl;
	private Long qty;
	private Double wt;
	private Double vl;
	private Date stime;

	// Constructors

	/** default constructor */
	public Acdata_Stockcontent() {
	}

	/** minimal constructor */
	public Acdata_Stockcontent(Acdata_StockcontentId id, Long inQty, Double inWt,
			Double inVl, Long qty, Double wt, Double vl, Date stime) {
		this.id = id;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.qty = qty;
		this.wt = wt;
		this.vl = vl;
		this.stime = stime;
	}

	/** full constructor */
	public Acdata_Stockcontent(Acdata_StockcontentId id, String barcodeNo,
			Long inQty, Double inWt, Double inVl, Long qty, Double wt,
			Double vl, Date stime) {
		this.id = id;
		this.barcodeNo = barcodeNo;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.qty = qty;
		this.wt = wt;
		this.vl = vl;
		this.stime = stime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "orderNo", column = @Column(name = "ORDER_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "sourceNo", column = @Column(name = "SOURCE_NO", nullable = false, length = 50)),
			@AttributeOverride(name = "articleName", column = @Column(name = "ARTICLE_NAME", nullable = false, length = 128)) })
	public Acdata_StockcontentId getId() {
		return this.id;
	}

	public void setId(Acdata_StockcontentId id) {
		this.id = id;
	}

	@Column(name = "BARCODE_NO", length = 50)
	public String getBarcodeNo() {
		return this.barcodeNo;
	}

	public void setBarcodeNo(String barcodeNo) {
		this.barcodeNo = barcodeNo;
	}

	@Column(name = "IN_QTY", nullable = false, precision = 12, scale = 0)
	public Long getInQty() {
		return this.inQty;
	}

	public void setInQty(Long inQty) {
		this.inQty = inQty;
	}

	@Column(name = "IN_WT", nullable = false, precision = 12, scale = 4)
	public Double getInWt() {
		return this.inWt;
	}

	public void setInWt(Double inWt) {
		this.inWt = inWt;
	}

	@Column(name = "IN_VL", nullable = false, precision = 12, scale = 4)
	public Double getInVl() {
		return this.inVl;
	}

	public void setInVl(Double inVl) {
		this.inVl = inVl;
	}

	@Column(name = "QTY", nullable = false, precision = 18, scale = 0)
	public Long getQty() {
		return this.qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	@Column(name = "WT", nullable = false, precision = 12, scale = 4)
	public Double getWt() {
		return this.wt;
	}

	public void setWt(Double wt) {
		this.wt = wt;
	}

	@Column(name = "VL", nullable = false, precision = 12, scale = 4)
	public Double getVl() {
		return this.vl;
	}

	public void setVl(Double vl) {
		this.vl = vl;
	}

	@Column(name = "STIME", nullable = false, length = 7)
	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

}