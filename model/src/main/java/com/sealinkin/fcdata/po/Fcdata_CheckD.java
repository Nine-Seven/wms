package com.sealinkin.fcdata.po;
// default package

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
 * FcdataCheckD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FCDATA_CHECK_D")
public class Fcdata_CheckD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fcdata_CheckDId id;
	private String ownerNo;
	private String cellNo;
	private String articleNo;
	private Integer orderId;
	private Integer subOrderId;
	private Double packingQty;
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
	private Double articleQty;
	private Double checkQty;
	private Double recheckQty;
	private Double realQty;
	private String addFlag;
	private String status;
	private String checkType;
	private Double thirdQty;
	private String checkFlag;
	private String checkWorker;
	private Date checkDate;
	private String differentFlag;
	private String recheckWorker;
	private Date recheckDate;
	private String thirdWorker;
	private Date thirdDate;

	// Constructors

	/** default constructor */
	public Fcdata_CheckD() {
	}

	/** minimal constructor */
	public Fcdata_CheckD(Fcdata_CheckDId id, String ownerNo, String cellNo,
			String articleNo, Integer orderId, Integer subOrderId,
			Double packingQty, String labelNo, String subLabelNo,
			String deptNo, String barcode, Date produceDate, Date expireDate,
			String quality, String lotNo, String stockType, String stockValue,
			String addFlag, String status, String checkType, String checkFlag,
			String differentFlag) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.cellNo = cellNo;
		this.articleNo = articleNo;
		this.orderId = orderId;
		this.subOrderId = subOrderId;
		this.packingQty = packingQty;
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
		this.addFlag = addFlag;
		this.status = status;
		this.checkType = checkType;
		this.checkFlag = checkFlag;
		this.differentFlag = differentFlag;
	}

	/** full constructor */
	public Fcdata_CheckD(Fcdata_CheckDId id, String ownerNo, String cellNo,
			String articleNo, Integer orderId, Integer subOrderId,
			Double packingQty, String labelNo, String subLabelNo,
			String deptNo, String barcode, Date produceDate, Date expireDate,
			String quality, String lotNo, String rsvBatch1, String rsvBatch2,
			String rsvBatch3, String rsvBatch4, String rsvBatch5,
			String rsvBatch6, String rsvBatch7, String rsvBatch8,
			String stockType, String stockValue, Double articleQty,
			Double checkQty, Double recheckQty, Double realQty, String addFlag,
			String status, String checkType, Double thirdQty, String checkFlag,
			String checkWorker, Date checkDate, String differentFlag,
			String recheckWorker, Date recheckDate, String thirdWorker,
			Date thirdDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.cellNo = cellNo;
		this.articleNo = articleNo;
		this.orderId = orderId;
		this.subOrderId = subOrderId;
		this.packingQty = packingQty;
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
		this.articleQty = articleQty;
		this.checkQty = checkQty;
		this.recheckQty = recheckQty;
		this.realQty = realQty;
		this.addFlag = addFlag;
		this.status = status;
		this.checkType = checkType;
		this.thirdQty = thirdQty;
		this.checkFlag = checkFlag;
		this.checkWorker = checkWorker;
		this.checkDate = checkDate;
		this.differentFlag = differentFlag;
		this.recheckWorker = recheckWorker;
		this.recheckDate = recheckDate;
		this.thirdWorker = thirdWorker;
		this.thirdDate = thirdDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 6, scale = 0)) })
	public Fcdata_CheckDId getId() {
		return this.id;
	}

	public void setId(Fcdata_CheckDId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
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

	@Column(name = "ORDER_ID", nullable = false, precision = 6, scale = 0)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "SUB_ORDER_ID", nullable = false, precision = 6, scale = 0)
	public Integer getSubOrderId() {
		return this.subOrderId;
	}

	public void setSubOrderId(Integer subOrderId) {
		this.subOrderId = subOrderId;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
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

	@Column(name = "LOT_NO", nullable = false, length = 32)
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

	@Column(name = "ARTICLE_QTY", precision = 13, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "CHECK_QTY", precision = 13, scale = 5)
	public Double getCheckQty() {
		return this.checkQty;
	}

	public void setCheckQty(Double checkQty) {
		this.checkQty = checkQty;
	}

	@Column(name = "RECHECK_QTY", precision = 13, scale = 5)
	public Double getRecheckQty() {
		return this.recheckQty;
	}

	public void setRecheckQty(Double recheckQty) {
		this.recheckQty = recheckQty;
	}

	@Column(name = "REAL_QTY", precision = 13, scale = 5)
	public Double getRealQty() {
		return this.realQty;
	}

	public void setRealQty(Double realQty) {
		this.realQty = realQty;
	}

	@Column(name = "ADD_FLAG", nullable = false, length = 1)
	public String getAddFlag() {
		return this.addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CHECK_TYPE", nullable = false, length = 1)
	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	@Column(name = "THIRD_QTY", precision = 13, scale = 5)
	public Double getThirdQty() {
		return this.thirdQty;
	}

	public void setThirdQty(Double thirdQty) {
		this.thirdQty = thirdQty;
	}

	@Column(name = "CHECK_FLAG", nullable = false, length = 1)
	public String getCheckFlag() {
		return this.checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	@Column(name = "CHECK_WORKER", length = 20)
	public String getCheckWorker() {
		return this.checkWorker;
	}

	public void setCheckWorker(String checkWorker) {
		this.checkWorker = checkWorker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE", length = 7)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "DIFFERENT_FLAG", nullable = false, length = 1)
	public String getDifferentFlag() {
		return this.differentFlag;
	}

	public void setDifferentFlag(String differentFlag) {
		this.differentFlag = differentFlag;
	}

	@Column(name = "RECHECK_WORKER", length = 20)
	public String getRecheckWorker() {
		return this.recheckWorker;
	}

	public void setRecheckWorker(String recheckWorker) {
		this.recheckWorker = recheckWorker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECHECK_DATE", length = 7)
	public Date getRecheckDate() {
		return this.recheckDate;
	}

	public void setRecheckDate(Date recheckDate) {
		this.recheckDate = recheckDate;
	}

	@Column(name = "THIRD_WORKER", length = 20)
	public String getThirdWorker() {
		return this.thirdWorker;
	}

	public void setThirdWorker(String thirdWorker) {
		this.thirdWorker = thirdWorker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "THIRD_DATE", length = 7)
	public Date getThirdDate() {
		return this.thirdDate;
	}

	public void setThirdDate(Date thirdDate) {
		this.thirdDate = thirdDate;
	}

}