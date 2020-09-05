package com.sealinkin.fcdata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FcdataCheckDirect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_CHECK_DIRECT")
public class Fcdata_CheckDirect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long directSerial;
	private String warehouseNo;
	private String ownerNo;
	private String planNo;
	private String requestNo;
	private String cellNo;
	private String articleNo;
	private Double packingQty;
	private Double articleQty;
	private String status;
	private Date requestDate;
	private String fcdataType;
	private String labelNo;
	private String subLabelNo;
	private String deptNo;
	private String barcode;
	private Date produceDate;
	private Date expireDate;
	private String quality;
	private String lotNo;
	private String rsvBatch1;
	private String rsvBatch2;
	private String rsvBatch3;
	private String rsvBatch4;
	private String rsvBatch5;
	private String rsvBatch6;
	private String rsvBatch7;
	private String rsvBatch8;
	private String stockType;
	private String stockValue;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Fcdata_CheckDirect() {
	}

	/** minimal constructor */
	public Fcdata_CheckDirect(Long directSerial, String warehouseNo,
			String ownerNo, String planNo, String requestNo, String cellNo,
			String articleNo, Double packingQty, Double articleQty,
			String status, Date requestDate, String fcdataType, String labelNo,
			String subLabelNo, String deptNo, String barcode, Date produceDate,
			Date expireDate, String quality, String lotNo, String stockType,
			String stockValue, String rgstName, Date rgstDate) {
		this.directSerial = directSerial;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.planNo = planNo;
		this.requestNo = requestNo;
		this.cellNo = cellNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.status = status;
		this.requestDate = requestDate;
		this.fcdataType = fcdataType;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.deptNo = deptNo;
		this.barcode = barcode;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.lotNo = lotNo;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Fcdata_CheckDirect(Long directSerial, String warehouseNo,
			String ownerNo, String planNo, String requestNo, String cellNo,
			String articleNo, Double packingQty, Double articleQty,
			String status, Date requestDate, String fcdataType, String labelNo,
			String subLabelNo, String deptNo, String barcode, Date produceDate,
			Date expireDate, String quality, String lotNo, String rsvBatch1,
			String rsvBatch2, String rsvBatch3, String rsvBatch4,
			String rsvBatch5, String rsvBatch6, String rsvBatch7,
			String rsvBatch8, String stockType, String stockValue,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.directSerial = directSerial;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.planNo = planNo;
		this.requestNo = requestNo;
		this.cellNo = cellNo;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.status = status;
		this.requestDate = requestDate;
		this.fcdataType = fcdataType;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.deptNo = deptNo;
		this.barcode = barcode;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.lotNo = lotNo;
		this.rsvBatch1 = rsvBatch1;
		this.rsvBatch2 = rsvBatch2;
		this.rsvBatch3 = rsvBatch3;
		this.rsvBatch4 = rsvBatch4;
		this.rsvBatch5 = rsvBatch5;
		this.rsvBatch6 = rsvBatch6;
		this.rsvBatch7 = rsvBatch7;
		this.rsvBatch8 = rsvBatch8;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@Id
	@Column(name = "DIRECT_SERIAL", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDirectSerial() {
		return this.directSerial;
	}

	public void setDirectSerial(Long directSerial) {
		this.directSerial = directSerial;
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

	@Column(name = "PLAN_NO", nullable = false, length = 20)
	public String getPlanNo() {
		return this.planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	@Column(name = "REQUEST_NO", nullable = false, length = 20)
	public String getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	@Column(name = "CELL_NO", nullable = false, length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
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

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false, length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "FCDATA_TYPE", nullable = false, length = 1)
	public String getFcdataType() {
		return this.fcdataType;
	}

	public void setFcdataType(String fcdataType) {
		this.fcdataType = fcdataType;
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

	@Column(name = "DEPT_NO", nullable = false, length = 20)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "BARCODE", nullable = false, length = 25)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRODUCE_DATE", nullable = false, length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_DATE", nullable = false, length = 7)
	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "QUALITY", nullable = false, length = 2)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "LOT_NO", nullable = false, length = 20)
	public String getLotNo() {
		return this.lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	@Column(name = "RSV_BATCH1", length = 20)
	public String getRsvBatch1() {
		return this.rsvBatch1;
	}

	public void setRsvBatch1(String rsvBatch1) {
		this.rsvBatch1 = rsvBatch1;
	}

	@Column(name = "RSV_BATCH2", length = 20)
	public String getRsvBatch2() {
		return this.rsvBatch2;
	}

	public void setRsvBatch2(String rsvBatch2) {
		this.rsvBatch2 = rsvBatch2;
	}

	@Column(name = "RSV_BATCH3", length = 20)
	public String getRsvBatch3() {
		return this.rsvBatch3;
	}

	public void setRsvBatch3(String rsvBatch3) {
		this.rsvBatch3 = rsvBatch3;
	}

	@Column(name = "RSV_BATCH4", length = 20)
	public String getRsvBatch4() {
		return this.rsvBatch4;
	}

	public void setRsvBatch4(String rsvBatch4) {
		this.rsvBatch4 = rsvBatch4;
	}

	@Column(name = "RSV_BATCH5", length = 20)
	public String getRsvBatch5() {
		return this.rsvBatch5;
	}

	public void setRsvBatch5(String rsvBatch5) {
		this.rsvBatch5 = rsvBatch5;
	}

	@Column(name = "RSV_BATCH6", length = 20)
	public String getRsvBatch6() {
		return this.rsvBatch6;
	}

	public void setRsvBatch6(String rsvBatch6) {
		this.rsvBatch6 = rsvBatch6;
	}

	@Column(name = "RSV_BATCH7", length = 20)
	public String getRsvBatch7() {
		return this.rsvBatch7;
	}

	public void setRsvBatch7(String rsvBatch7) {
		this.rsvBatch7 = rsvBatch7;
	}

	@Column(name = "RSV_BATCH8", length = 20)
	public String getRsvBatch8() {
		return this.rsvBatch8;
	}

	public void setRsvBatch8(String rsvBatch8) {
		this.rsvBatch8 = rsvBatch8;
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