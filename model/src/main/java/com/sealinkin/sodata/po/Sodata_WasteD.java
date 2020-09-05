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
 * SodataWasteD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SODATA_WASTE_D")
public class Sodata_WasteD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sodata_WasteDId id;
	private String ownerNo;
	private String articleNo;
	private Double packingQty;
	private String lotNo;
	private String quality;
	private Date produceDate;
	private Date expireDate;
	private Double wasteQty;
	private Double realQty;
	private Double locateQty;

	// Constructors

	/** default constructor */
	public Sodata_WasteD() {
	}

	/** minimal constructor */
	public Sodata_WasteD(Sodata_WasteDId id, String ownerNo, String articleNo,
			Double packingQty, String lotNo, Double wasteQty, Double realQty) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.lotNo = lotNo;
		this.wasteQty = wasteQty;
		this.realQty = realQty;
	}

	/** full constructor */
	public Sodata_WasteD(Sodata_WasteDId id, String ownerNo, String articleNo,
			Double packingQty, String lotNo, String quality, Date produceDate,
			Date expireDate, Double wasteQty, Double realQty, Double locateQty) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.lotNo = lotNo;
		this.quality = quality;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.wasteQty = wasteQty;
		this.realQty = realQty;
		this.locateQty = locateQty;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "wasteNo", column = @Column(name = "WASTE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "poId", column = @Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)) })
	public Sodata_WasteDId getId() {
		return this.id;
	}

	public void setId(Sodata_WasteDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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

	@Column(name = "WASTE_QTY", nullable = false, precision = 14, scale = 5)
	public Double getWasteQty() {
		return this.wasteQty;
	}

	public void setWasteQty(Double wasteQty) {
		this.wasteQty = wasteQty;
	}

	@Column(name = "REAL_QTY", nullable = false, precision = 14, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "LOCATE_QTY", precision = 14, scale = 5)
	public Double getLocateQty() {
		return this.locateQty;
	}

	public void setLocateQty(Double locateQty) {
		this.locateQty = locateQty;
	}

}