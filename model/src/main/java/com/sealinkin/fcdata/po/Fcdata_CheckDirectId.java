package com.sealinkin.fcdata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FcdataCheckDirectId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Fcdata_CheckDirectId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String warehouseNo;
	private String ownerNo;
	private Long directSerial;
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
	public Fcdata_CheckDirectId() {
	}

	/** minimal constructor */
	public Fcdata_CheckDirectId(String warehouseNo, String ownerNo,
			Long directSerial, String planNo, String requestNo, String cellNo,
			String articleNo, Double packingQty, Double articleQty,
			String status, Date requestDate, String fcdataType, String labelNo,
			String subLabelNo, String deptNo, String barcode, Date produceDate,
			Date expireDate, String quality, String lotNo, String stockType,
			String stockValue, String rgstName, Date rgstDate) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.directSerial = directSerial;
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
	public Fcdata_CheckDirectId(String warehouseNo, String ownerNo,
			Long directSerial, String planNo, String requestNo, String cellNo,
			String articleNo, Double packingQty, Double articleQty,
			String status, Date requestDate, String fcdataType, String labelNo,
			String subLabelNo, String deptNo, String barcode, Date produceDate,
			Date expireDate, String quality, String lotNo, String rsvBatch1,
			String rsvBatch2, String rsvBatch3, String rsvBatch4,
			String rsvBatch5, String rsvBatch6, String rsvBatch7,
			String rsvBatch8, String stockType, String stockValue,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.directSerial = directSerial;
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

	@Column(name = "DIRECT_SERIAL", nullable = false, precision = 10, scale = 0)
	public Long getDirectSerial() {
		return this.directSerial;
	}

	public void setDirectSerial(Long directSerial) {
		this.directSerial = directSerial;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Fcdata_CheckDirectId))
			return false;
		Fcdata_CheckDirectId castOther = (Fcdata_CheckDirectId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getDirectSerial() == castOther.getDirectSerial()) || (this
						.getDirectSerial() != null
						&& castOther.getDirectSerial() != null && this
						.getDirectSerial().equals(castOther.getDirectSerial())))
				&& ((this.getPlanNo() == castOther.getPlanNo()) || (this
						.getPlanNo() != null && castOther.getPlanNo() != null && this
						.getPlanNo().equals(castOther.getPlanNo())))
				&& ((this.getRequestNo() == castOther.getRequestNo()) || (this
						.getRequestNo() != null
						&& castOther.getRequestNo() != null && this
						.getRequestNo().equals(castOther.getRequestNo())))
				&& ((this.getCellNo() == castOther.getCellNo()) || (this
						.getCellNo() != null && castOther.getCellNo() != null && this
						.getCellNo().equals(castOther.getCellNo())))
				&& ((this.getArticleNo() == castOther.getArticleNo()) || (this
						.getArticleNo() != null
						&& castOther.getArticleNo() != null && this
						.getArticleNo().equals(castOther.getArticleNo())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getArticleQty() == castOther.getArticleQty()) || (this
						.getArticleQty() != null
						&& castOther.getArticleQty() != null && this
						.getArticleQty().equals(castOther.getArticleQty())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getRequestDate() == castOther.getRequestDate()) || (this
						.getRequestDate() != null
						&& castOther.getRequestDate() != null && this
						.getRequestDate().equals(castOther.getRequestDate())))
				&& ((this.getFcdataType() == castOther.getFcdataType()) || (this
						.getFcdataType() != null
						&& castOther.getFcdataType() != null && this
						.getFcdataType().equals(castOther.getFcdataType())))
				&& ((this.getLabelNo() == castOther.getLabelNo()) || (this
						.getLabelNo() != null && castOther.getLabelNo() != null && this
						.getLabelNo().equals(castOther.getLabelNo())))
				&& ((this.getSubLabelNo() == castOther.getSubLabelNo()) || (this
						.getSubLabelNo() != null
						&& castOther.getSubLabelNo() != null && this
						.getSubLabelNo().equals(castOther.getSubLabelNo())))
				&& ((this.getDeptNo() == castOther.getDeptNo()) || (this
						.getDeptNo() != null && castOther.getDeptNo() != null && this
						.getDeptNo().equals(castOther.getDeptNo())))
				&& ((this.getBarcode() == castOther.getBarcode()) || (this
						.getBarcode() != null && castOther.getBarcode() != null && this
						.getBarcode().equals(castOther.getBarcode())))
				&& ((this.getProduceDate() == castOther.getProduceDate()) || (this
						.getProduceDate() != null
						&& castOther.getProduceDate() != null && this
						.getProduceDate().equals(castOther.getProduceDate())))
				&& ((this.getExpireDate() == castOther.getExpireDate()) || (this
						.getExpireDate() != null
						&& castOther.getExpireDate() != null && this
						.getExpireDate().equals(castOther.getExpireDate())))
				&& ((this.getQuality() == castOther.getQuality()) || (this
						.getQuality() != null && castOther.getQuality() != null && this
						.getQuality().equals(castOther.getQuality())))
				&& ((this.getLotNo() == castOther.getLotNo()) || (this
						.getLotNo() != null && castOther.getLotNo() != null && this
						.getLotNo().equals(castOther.getLotNo())))
				&& ((this.getRsvBatch1() == castOther.getRsvBatch1()) || (this
						.getRsvBatch1() != null
						&& castOther.getRsvBatch1() != null && this
						.getRsvBatch1().equals(castOther.getRsvBatch1())))
				&& ((this.getRsvBatch2() == castOther.getRsvBatch2()) || (this
						.getRsvBatch2() != null
						&& castOther.getRsvBatch2() != null && this
						.getRsvBatch2().equals(castOther.getRsvBatch2())))
				&& ((this.getRsvBatch3() == castOther.getRsvBatch3()) || (this
						.getRsvBatch3() != null
						&& castOther.getRsvBatch3() != null && this
						.getRsvBatch3().equals(castOther.getRsvBatch3())))
				&& ((this.getRsvBatch4() == castOther.getRsvBatch4()) || (this
						.getRsvBatch4() != null
						&& castOther.getRsvBatch4() != null && this
						.getRsvBatch4().equals(castOther.getRsvBatch4())))
				&& ((this.getRsvBatch5() == castOther.getRsvBatch5()) || (this
						.getRsvBatch5() != null
						&& castOther.getRsvBatch5() != null && this
						.getRsvBatch5().equals(castOther.getRsvBatch5())))
				&& ((this.getRsvBatch6() == castOther.getRsvBatch6()) || (this
						.getRsvBatch6() != null
						&& castOther.getRsvBatch6() != null && this
						.getRsvBatch6().equals(castOther.getRsvBatch6())))
				&& ((this.getRsvBatch7() == castOther.getRsvBatch7()) || (this
						.getRsvBatch7() != null
						&& castOther.getRsvBatch7() != null && this
						.getRsvBatch7().equals(castOther.getRsvBatch7())))
				&& ((this.getRsvBatch8() == castOther.getRsvBatch8()) || (this
						.getRsvBatch8() != null
						&& castOther.getRsvBatch8() != null && this
						.getRsvBatch8().equals(castOther.getRsvBatch8())))
				&& ((this.getStockType() == castOther.getStockType()) || (this
						.getStockType() != null
						&& castOther.getStockType() != null && this
						.getStockType().equals(castOther.getStockType())))
				&& ((this.getStockValue() == castOther.getStockValue()) || (this
						.getStockValue() != null
						&& castOther.getStockValue() != null && this
						.getStockValue().equals(castOther.getStockValue())))
				&& ((this.getRgstName() == castOther.getRgstName()) || (this
						.getRgstName() != null
						&& castOther.getRgstName() != null && this
						.getRgstName().equals(castOther.getRgstName())))
				&& ((this.getRgstDate() == castOther.getRgstDate()) || (this
						.getRgstDate() != null
						&& castOther.getRgstDate() != null && this
						.getRgstDate().equals(castOther.getRgstDate())))
				&& ((this.getUpdtName() == castOther.getUpdtName()) || (this
						.getUpdtName() != null
						&& castOther.getUpdtName() != null && this
						.getUpdtName().equals(castOther.getUpdtName())))
				&& ((this.getUpdtDate() == castOther.getUpdtDate()) || (this
						.getUpdtDate() != null
						&& castOther.getUpdtDate() != null && this
						.getUpdtDate().equals(castOther.getUpdtDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getDirectSerial() == null ? 0 : this.getDirectSerial()
						.hashCode());
		result = 37 * result
				+ (getPlanNo() == null ? 0 : this.getPlanNo().hashCode());
		result = 37 * result
				+ (getRequestNo() == null ? 0 : this.getRequestNo().hashCode());
		result = 37 * result
				+ (getCellNo() == null ? 0 : this.getCellNo().hashCode());
		result = 37 * result
				+ (getArticleNo() == null ? 0 : this.getArticleNo().hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37
				* result
				+ (getArticleQty() == null ? 0 : this.getArticleQty()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getRequestDate() == null ? 0 : this.getRequestDate()
						.hashCode());
		result = 37
				* result
				+ (getFcdataType() == null ? 0 : this.getFcdataType()
						.hashCode());
		result = 37 * result
				+ (getLabelNo() == null ? 0 : this.getLabelNo().hashCode());
		result = 37
				* result
				+ (getSubLabelNo() == null ? 0 : this.getSubLabelNo()
						.hashCode());
		result = 37 * result
				+ (getDeptNo() == null ? 0 : this.getDeptNo().hashCode());
		result = 37 * result
				+ (getBarcode() == null ? 0 : this.getBarcode().hashCode());
		result = 37
				* result
				+ (getProduceDate() == null ? 0 : this.getProduceDate()
						.hashCode());
		result = 37
				* result
				+ (getExpireDate() == null ? 0 : this.getExpireDate()
						.hashCode());
		result = 37 * result
				+ (getQuality() == null ? 0 : this.getQuality().hashCode());
		result = 37 * result
				+ (getLotNo() == null ? 0 : this.getLotNo().hashCode());
		result = 37 * result
				+ (getRsvBatch1() == null ? 0 : this.getRsvBatch1().hashCode());
		result = 37 * result
				+ (getRsvBatch2() == null ? 0 : this.getRsvBatch2().hashCode());
		result = 37 * result
				+ (getRsvBatch3() == null ? 0 : this.getRsvBatch3().hashCode());
		result = 37 * result
				+ (getRsvBatch4() == null ? 0 : this.getRsvBatch4().hashCode());
		result = 37 * result
				+ (getRsvBatch5() == null ? 0 : this.getRsvBatch5().hashCode());
		result = 37 * result
				+ (getRsvBatch6() == null ? 0 : this.getRsvBatch6().hashCode());
		result = 37 * result
				+ (getRsvBatch7() == null ? 0 : this.getRsvBatch7().hashCode());
		result = 37 * result
				+ (getRsvBatch8() == null ? 0 : this.getRsvBatch8().hashCode());
		result = 37 * result
				+ (getStockType() == null ? 0 : this.getStockType().hashCode());
		result = 37
				* result
				+ (getStockValue() == null ? 0 : this.getStockValue()
						.hashCode());
		result = 37 * result
				+ (getRgstName() == null ? 0 : this.getRgstName().hashCode());
		result = 37 * result
				+ (getRgstDate() == null ? 0 : this.getRgstDate().hashCode());
		result = 37 * result
				+ (getUpdtName() == null ? 0 : this.getUpdtName().hashCode());
		result = 37 * result
				+ (getUpdtDate() == null ? 0 : this.getUpdtDate().hashCode());
		return result;
	}

}