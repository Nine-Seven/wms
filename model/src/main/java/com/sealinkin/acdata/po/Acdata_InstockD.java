package com.sealinkin.acdata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AcdataInstockD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACDATA_INSTOCK_D")
public class Acdata_InstockD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acdata_InstockDId id;
	private String barcodeNo;
	private Long inQty;
	private Double inWt;
	private Double inVl;
	private Long istockQty;
	private Double istockWt;
	private Double istockVl;
	private String remark;

	// Constructors

	/** default constructor */
	public Acdata_InstockD() {
	}

	/** minimal constructor */
	public Acdata_InstockD(Acdata_InstockDId id, Long inQty, Double inWt,
			Double inVl, Long istockQty, Double istockWt, Double istockVl) {
		this.id = id;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.istockQty = istockQty;
		this.istockWt = istockWt;
		this.istockVl = istockVl;
	}

	/** full constructor */
	public Acdata_InstockD(Acdata_InstockDId id, String barcodeNo, Long inQty,
			Double inWt, Double inVl, Long istockQty, Double istockWt,
			Double istockVl, String remark) {
		this.id = id;
		this.barcodeNo = barcodeNo;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.istockQty = istockQty;
		this.istockWt = istockWt;
		this.istockVl = istockVl;
		this.remark = remark;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "instockNo", column = @Column(name = "INSTOCK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "articleName", column = @Column(name = "ARTICLE_NAME", nullable = false, length = 128)) })
	public Acdata_InstockDId getId() {
		return this.id;
	}

	public void setId(Acdata_InstockDId id) {
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

	@Column(name = "ISTOCK_QTY", nullable = false, precision = 12, scale = 0)
	public Long getIstockQty() {
		return this.istockQty;
	}

	public void setIstockQty(Long istockQty) {
		this.istockQty = istockQty;
	}

	@Column(name = "ISTOCK_WT", nullable = false, precision = 12, scale = 4)
	public Double getIstockWt() {
		return this.istockWt;
	}

	public void setIstockWt(Double istockWt) {
		this.istockWt = istockWt;
	}

	@Column(name = "ISTOCK_VL", nullable = false, precision = 12, scale = 4)
	public Double getIstockVl() {
		return this.istockVl;
	}

	public void setIstockVl(Double istockVl) {
		this.istockVl = istockVl;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}