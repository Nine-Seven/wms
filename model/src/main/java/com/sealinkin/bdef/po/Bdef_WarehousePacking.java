package com.sealinkin.bdef.po;

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
 * BdefWarehousePacking entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_WAREHOUSE_PACKING")
public class Bdef_WarehousePacking implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_WarehousePackingId id;
	private String warehouseNo;
	private String packingUnit;
	private Double palBaseQbox;
	private Double palHeightQbox;
	private Double qpalette;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Bdef_WarehousePacking() {
	}

	/** minimal constructor */
	public Bdef_WarehousePacking(Bdef_WarehousePackingId id, String warehouseNo,
			String packingUnit, Double qpalette, String rgstName, Date rgstDate) {
		this.id = id;
		this.warehouseNo = warehouseNo;
		this.packingUnit = packingUnit;
		this.qpalette = qpalette;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Bdef_WarehousePacking(Bdef_WarehousePackingId id, String warehouseNo,
			String packingUnit, Double palBaseQbox, Double palHeightQbox,
			Double qpalette, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.warehouseNo = warehouseNo;
		this.packingUnit = packingUnit;
		this.palBaseQbox = palBaseQbox;
		this.palHeightQbox = palHeightQbox;
		this.qpalette = qpalette;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "articleNo", column = @Column(name = "ARTICLE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "packingQty", column = @Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)),
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)) })
	public Bdef_WarehousePackingId getId() {
		return this.id;
	}

	public void setId(Bdef_WarehousePackingId id) {
		this.id = id;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "PACKING_UNIT", nullable = false, length = 20)
	public String getPackingUnit() {
		return this.packingUnit;
	}

	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}

	@Column(name = "PAL_BASE_QBOX", precision = 18, scale = 5)
	public Double getPalBaseQbox() {
		return this.palBaseQbox;
	}

	public void setPalBaseQbox(Double palBaseQbox) {
		this.palBaseQbox = palBaseQbox;
	}

	@Column(name = "PAL_HEIGHT_QBOX", precision = 18, scale = 5)
	public Double getPalHeightQbox() {
		return this.palHeightQbox;
	}

	public void setPalHeightQbox(Double palHeightQbox) {
		this.palHeightQbox = palHeightQbox;
	}

	@Column(name = "QPALETTE", nullable = false, precision = 18, scale = 5)
	public Double getQpalette() {
		return this.qpalette;
	}

	public void setQpalette(Double qpalette) {
		this.qpalette = qpalette;
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

}