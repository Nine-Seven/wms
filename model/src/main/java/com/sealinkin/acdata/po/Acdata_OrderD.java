package com.sealinkin.acdata.po;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AcdataOrderD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACDATA_ORDER_D")
public class Acdata_OrderD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acdata_OrderDId id;
	private Long orderQty;
	private Double orderWt;
	private Double orderVl;
	private Long inQty;
	private Double inWt;
	private Double inVl;
	private Long signQty;
	private Double signWt;
	private Double signVl;
	private Long lostQty;
	private Double lostWt;
	private Double lostVl;
	private String remark;

	// Constructors

	/** default constructor */
	public Acdata_OrderD() {
	}

	/** minimal constructor */
	public Acdata_OrderD(Acdata_OrderDId id, Long orderQty, Double orderWt,
			Double orderVl, Long inQty, Double inWt, Double inVl, Long signQty,
			Double signWt, Double signVl, Long lostQty, Double lostWt,
			Double lostVl) {
		this.id = id;
		this.orderQty = orderQty;
		this.orderWt = orderWt;
		this.orderVl = orderVl;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.signQty = signQty;
		this.signWt = signWt;
		this.signVl = signVl;
		this.lostQty = lostQty;
		this.lostWt = lostWt;
		this.lostVl = lostVl;
	}

	/** full constructor */
	public Acdata_OrderD(Acdata_OrderDId id, Long orderQty, Double orderWt,
			Double orderVl, Long inQty, Double inWt, Double inVl, Long signQty,
			Double signWt, Double signVl, Long lostQty, Double lostWt,
			Double lostVl, String remark) {
		this.id = id;
		this.orderQty = orderQty;
		this.orderWt = orderWt;
		this.orderVl = orderVl;
		this.inQty = inQty;
		this.inWt = inWt;
		this.inVl = inVl;
		this.signQty = signQty;
		this.signWt = signWt;
		this.signVl = signVl;
		this.lostQty = lostQty;
		this.lostWt = lostWt;
		this.lostVl = lostVl;
		this.remark = remark;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "orderNo", column = @Column(name = "ORDER_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "barcodeNo", column = @Column(name = "BARCODE_NO", nullable = false, length = 50)),
			@AttributeOverride(name = "articleName", column = @Column(name = "ARTICLE_NAME", nullable = false, length = 128)) })
	public Acdata_OrderDId getId() {
		return this.id;
	}

	public void setId(Acdata_OrderDId id) {
		this.id = id;
	}

	@Column(name = "ORDER_QTY", nullable = false, precision = 12, scale = 0)
	public Long getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(Long orderQty) {
		this.orderQty = orderQty;
	}

	@Column(name = "ORDER_WT", nullable = false, precision = 12, scale = 4)
	public Double getOrderWt() {
		return this.orderWt;
	}

	public void setOrderWt(Double orderWt) {
		this.orderWt = orderWt;
	}

	@Column(name = "ORDER_VL", nullable = false, precision = 12, scale = 4)
	public Double getOrderVl() {
		return this.orderVl;
	}

	public void setOrderVl(Double orderVl) {
		this.orderVl = orderVl;
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

	@Column(name = "SIGN_QTY", nullable = false, precision = 12, scale = 0)
	public Long getSignQty() {
		return this.signQty;
	}

	public void setSignQty(Long signQty) {
		this.signQty = signQty;
	}

	@Column(name = "SIGN_WT", nullable = false, precision = 12, scale = 4)
	public Double getSignWt() {
		return this.signWt;
	}

	public void setSignWt(Double signWt) {
		this.signWt = signWt;
	}

	@Column(name = "SIGN_VL", nullable = false, precision = 12, scale = 4)
	public Double getSignVl() {
		return this.signVl;
	}

	public void setSignVl(Double signVl) {
		this.signVl = signVl;
	}

	@Column(name = "LOST_QTY", nullable = false, precision = 12, scale = 0)
	public Long getLostQty() {
		return this.lostQty;
	}

	public void setLostQty(Long lostQty) {
		this.lostQty = lostQty;
	}

	@Column(name = "LOST_WT", nullable = false, precision = 12, scale = 4)
	public Double getLostWt() {
		return this.lostWt;
	}

	public void setLostWt(Double lostWt) {
		this.lostWt = lostWt;
	}

	@Column(name = "LOST_VL", nullable = false, precision = 12, scale = 4)
	public Double getLostVl() {
		return this.lostVl;
	}

	public void setLostVl(Double lostVl) {
		this.lostVl = lostVl;
	}

	@Column(name = "REMARK", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}