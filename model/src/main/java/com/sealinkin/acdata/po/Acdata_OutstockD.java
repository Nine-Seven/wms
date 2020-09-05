package com.sealinkin.acdata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AcdataOutstockD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACDATA_OUTSTOCK_D")
public class Acdata_OutstockD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acdata_OutstockDId id;
	private String barcodeNo;
	private Long inQty;
	private Double inWt;
	private Double inVl;
	private Long alreadyQty;
	private Double alreadyWt;
	private Double alreadyVl;
	private Long ostockQty;
	private Double ostockWt;
	private Double ostockVl;
	private String remark;

	// Constructors

	/** default constructor */
	public Acdata_OutstockD() {
	}

	/** minimal constructor */
	public Acdata_OutstockD(Acdata_OutstockDId id, Long inQty, Double inWt,
			Double inVl, Long alreadyQty, Double alreadyWt, Double alreadyVl,
			Long ostockQty, Double ostockWt, Double ostockVl) {
		this.id = id;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.alreadyQty = alreadyQty;
		this.alreadyWt = alreadyWt;
		this.alreadyVl = alreadyVl;
		this.ostockQty = ostockQty;
		this.ostockWt = ostockWt;
		this.ostockVl = ostockVl;
	}

	/** full constructor */
	public Acdata_OutstockD(Acdata_OutstockDId id, String barcodeNo, Long inQty,
			Double inWt, Double inVl, Long alreadyQty, Double alreadyWt,
			Double alreadyVl, Long ostockQty, Double ostockWt, Double ostockVl,
			String remark) {
		this.id = id;
		this.barcodeNo = barcodeNo;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.alreadyQty = alreadyQty;
		this.alreadyWt = alreadyWt;
		this.alreadyVl = alreadyVl;
		this.ostockQty = ostockQty;
		this.ostockWt = ostockWt;
		this.ostockVl = ostockVl;
		this.remark = remark;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "outstockNo", column = @Column(name = "OUTSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "articleName", column = @Column(name = "ARTICLE_NAME", nullable = false, length = 128)) })
	public Acdata_OutstockDId getId() {
		return this.id;
	}

	public void setId(Acdata_OutstockDId id) {
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

	@Column(name = "ALREADY_QTY", nullable = false, precision = 12, scale = 0)
	public Long getAlreadyQty() {
		return this.alreadyQty;
	}

	public void setAlreadyQty(Long alreadyQty) {
		this.alreadyQty = alreadyQty;
	}

	@Column(name = "ALREADY_WT", nullable = false, precision = 12, scale = 4)
	public Double getAlreadyWt() {
		return this.alreadyWt;
	}

	public void setAlreadyWt(Double alreadyWt) {
		this.alreadyWt = alreadyWt;
	}

	@Column(name = "ALREADY_VL", nullable = false, precision = 12, scale = 4)
	public Double getAlreadyVl() {
		return this.alreadyVl;
	}

	public void setAlreadyVl(Double alreadyVl) {
		this.alreadyVl = alreadyVl;
	}

	@Column(name = "OSTOCK_QTY", nullable = false, precision = 12, scale = 0)
	public Long getOstockQty() {
		return this.ostockQty;
	}

	public void setOstockQty(Long ostockQty) {
		this.ostockQty = ostockQty;
	}

	@Column(name = "OSTOCK_WT", nullable = false, precision = 12, scale = 4)
	public Double getOstockWt() {
		return this.ostockWt;
	}

	public void setOstockWt(Double ostockWt) {
		this.ostockWt = ostockWt;
	}

	@Column(name = "OSTOCK_VL", nullable = false, precision = 12, scale = 4)
	public Double getOstockVl() {
		return this.ostockVl;
	}

	public void setOstockVl(Double ostockVl) {
		this.ostockVl = ostockVl;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}