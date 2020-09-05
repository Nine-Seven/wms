package com.sealinkin.idata.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Idata_CheckPal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IDATA_CHECK_PAL")
public class Idata_CheckPal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Idata_CheckPalId id;
	private String articleNo;
	private double packingQty;
	private double checkQty;
	private String status;
	private String printerGroupNo;
	private String dockNo;
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
	private String deptNo;
	private String fixpalFlag;
	private String custNo;
	private String subCustNo;
	private String businessType;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private double price;

	// Constructors

	/** default constructor */
	public Idata_CheckPal() {
	}

	/** minimal constructor */
	public Idata_CheckPal(Idata_CheckPalId id, String articleNo,
			double packingQty, double checkQty, String status,
			String printerGroupNo, String dockNo, String barcode,
			Date produceDate, Date expireDate, String quality, String lotNo,
			String stockType, String stockValue, String deptNo,
			String fixpalFlag, String custNo, String subCustNo,
			String businessType, String rgstName, Date rgstDate) {
		this.id = id;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.checkQty = checkQty;
		this.status = status;
		this.printerGroupNo = printerGroupNo;
		this.dockNo = dockNo;
		this.barcode = barcode;
		this.produceDate = produceDate;
		this.expireDate = expireDate;
		this.quality = quality;
		this.lotNo = lotNo;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.deptNo = deptNo;
		this.fixpalFlag = fixpalFlag;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.businessType = businessType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Idata_CheckPal(Idata_CheckPalId id, String articleNo,
			double packingQty, double checkQty, String status,
			String printerGroupNo, String dockNo, String barcode,
			Date produceDate, Date expireDate, String quality, String lotNo,
			String rsvBatch1, String rsvBatch2, String rsvBatch3,
			String rsvBatch4, String rsvBatch5, String rsvBatch6,
			String rsvBatch7, String rsvBatch8, String stockType,
			String stockValue, String deptNo, String fixpalFlag, String custNo,
			String subCustNo, String businessType, String rgstName,
			Date rgstDate, String updtName, Date updtDate, double price) {
		this.id = id;
		this.articleNo = articleNo;
		this.packingQty = packingQty;
		this.checkQty = checkQty;
		this.status = status;
		this.printerGroupNo = printerGroupNo;
		this.dockNo = dockNo;
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
		this.deptNo = deptNo;
		this.fixpalFlag = fixpalFlag;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.businessType = businessType;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.price = price;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "SCheckNo", column = @Column(name = "S_CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "checkNo", column = @Column(name = "CHECK_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "checkRowId", column = @Column(name = "CHECK_ROW_ID", nullable = false, precision = 4, scale = 0)),
			@AttributeOverride(name = "containerNo", column = @Column(name = "CONTAINER_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "labelNo", column = @Column(name = "LABEL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "subLabelNo", column = @Column(name = "SUB_LABEL_NO", nullable = false, length = 24)) })
	public Idata_CheckPalId getId() {
		return this.id;
	}

	public void setId(Idata_CheckPalId id) {
		this.id = id;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "CHECK_QTY", nullable = false, precision = 18, scale = 5)
	public double getCheckQty() {
		return this.checkQty;
	}

	public void setCheckQty(double checkQty) {
		this.checkQty = checkQty;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PRINTER_GROUP_NO", nullable = false, length = 5)
	public String getPrinterGroupNo() {
		return this.printerGroupNo;
	}

	public void setPrinterGroupNo(String printerGroupNo) {
		this.printerGroupNo = printerGroupNo;
	}

	@Column(name = "DOCK_NO", nullable = false, length = 3)
	public String getDockNo() {
		return this.dockNo;
	}

	public void setDockNo(String dockNo) {
		this.dockNo = dockNo;
	}

	@Column(name = "BARCODE", nullable = false, length = 25)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "PRODUCE_DATE", nullable = false, length = 7)
	public Date getProduceDate() {
		return this.produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

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

	@Column(name = "DEPT_NO", nullable = false, length = 20)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "FIXPAL_FLAG", nullable = false, length = 1)
	public String getFixpalFlag() {
		return this.fixpalFlag;
	}

	public void setFixpalFlag(String fixpalFlag) {
		this.fixpalFlag = fixpalFlag;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "SUB_CUST_NO", nullable = false, length = 20)
	public String getSubCustNo() {
		return this.subCustNo;
	}

	public void setSubCustNo(String subCustNo) {
		this.subCustNo = subCustNo;
	}

	@Column(name = "BUSINESS_TYPE", nullable = false, length = 2)
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

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

	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "PRICE", precision = 126, scale = 0)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}