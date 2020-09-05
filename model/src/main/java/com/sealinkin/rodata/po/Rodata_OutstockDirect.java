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
 * RodataOutstockDirect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RODATA_OUTSTOCK_DIRECT")
public class Rodata_OutstockDirect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rodata_OutstockDirectId id;
	private String warehouseNo;
	private String ownerNo;
	private String sourceNo;
	private String operateType;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private String SCellNo;
	private Long SCellId;
	private String SContainerNo;
	private String DCellNo;
	private Long DCellId;
	private Double locateQty;
	private String status;
	private String supplierNo;
	private String classType;
	private Short poId;
	private String stockType;
	private String stockValue;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String labelNo;
	private String subLabelNo;
	private String waveNo;
	private String enterpriseNo;

	// Constructors

	/** default constructor */
	public Rodata_OutstockDirect() {
	}

	/** minimal constructor */
	public Rodata_OutstockDirect(Rodata_OutstockDirectId id, String warehouseNo,
			String ownerNo, String sourceNo, String operateType,
			String articleNo, Long articleId, Double packingQty,
			String SCellNo, Long SCellId, String SContainerNo, String DCellNo,
			Long DCellId, Double locateQty, String status, String supplierNo,
			String classType, Short poId, String stockType, String stockValue,
			String rgstName, Date rgstDate, String labelNo, String subLabelNo,
			String waveNo) {
		this.id = id;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.sourceNo = sourceNo;
		this.operateType = operateType;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.SContainerNo = SContainerNo;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.locateQty = locateQty;
		this.status = status;
		this.supplierNo = supplierNo;
		this.classType = classType;
		this.poId = poId;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.waveNo = waveNo;
	}

	/** full constructor */
	public Rodata_OutstockDirect(Rodata_OutstockDirectId id, String warehouseNo,
			String ownerNo, String sourceNo, String operateType,
			String articleNo, Long articleId, Double packingQty,
			String SCellNo, Long SCellId, String SContainerNo, String DCellNo,
			Long DCellId, Double locateQty, String status, String supplierNo,
			String classType, Short poId, String stockType, String stockValue,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String labelNo, String subLabelNo, String waveNo,
			String enterpriseNo) {
		this.id = id;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.sourceNo = sourceNo;
		this.operateType = operateType;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.SContainerNo = SContainerNo;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.locateQty = locateQty;
		this.status = status;
		this.supplierNo = supplierNo;
		this.classType = classType;
		this.poId = poId;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.waveNo = waveNo;
		this.enterpriseNo = enterpriseNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)),
			@AttributeOverride(name = "directSerial", column = @Column(name = "DIRECT_SERIAL", nullable = false, precision = 8, scale = 0)) })
	public Rodata_OutstockDirectId getId() {
		return this.id;
	}

	public void setId(Rodata_OutstockDirectId id) {
		this.id = id;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
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

	@Column(name = "S_CONTAINER_NO", nullable = false, length = 24)
	public String getSContainerNo() {
		return this.SContainerNo;
	}

	public void setSContainerNo(String SContainerNo) {
		this.SContainerNo = SContainerNo;
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

	@Column(name = "LOCATE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getLocateQty() {
		return this.locateQty;
	}

	public void setLocateQty(Double locateQty) {
		this.locateQty = locateQty;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "CLASS_TYPE", nullable = false, length = 1)
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}


	@Column(name = "PO_ID", nullable = false, precision = 4, scale = 0)
	public Short getPoId() {
		return this.poId;
	}
	public void setPoId(Short poId) {
		this.poId = poId;
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

	@Column(name = "RGST_NAME", nullable = false, length = 10)
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

	@Column(name = "UPDT_NAME", length = 10)
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

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

}