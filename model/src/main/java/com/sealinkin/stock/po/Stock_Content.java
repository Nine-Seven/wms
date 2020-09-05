package com.sealinkin.stock.po;
// default package

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Stock_Content entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_CONTENT")
public class Stock_Content implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Stock_ContentId id;
	private String ownerNo;
	private String deptNo;
	private String articleNo;
	private long articleId;
	private double packingQty;
	private double qty;
	private double outstockQty;
	private double instockQty;
	private double unusualQty;
	private String status;
	private String flag;
	private String instockType;
	private String mvHandFlag;
	private String labelNo;
	private String stockType;
	private String stockValue;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String subLabelNo;

	// Constructors

	/** default constructor */
	public Stock_Content() {
	}

	/** minimal constructor */
	public Stock_Content(Stock_ContentId id, String ownerNo, String deptNo,
			String articleNo, long articleId, double packingQty, double qty,
			double outstockQty, double instockQty, double unusualQty,
			String status, String flag, String instockType, String mvHandFlag,
			String labelNo, String stockType, String stockValue,
			String rgstName, Date rgstDate, String subLabelNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.deptNo = deptNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.qty = qty;
		this.outstockQty = outstockQty;
		this.instockQty = instockQty;
		this.unusualQty = unusualQty;
		this.status = status;
		this.flag = flag;
		this.instockType = instockType;
		this.mvHandFlag = mvHandFlag;
		this.labelNo = labelNo;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.subLabelNo = subLabelNo;
	}

	/** full constructor */
	public Stock_Content(Stock_ContentId id, String ownerNo, String deptNo,
			String articleNo, long articleId, double packingQty, double qty,
			double outstockQty, double instockQty, double unusualQty,
			String status, String flag, String instockType, String mvHandFlag,
			String labelNo, String stockType, String stockValue,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String subLabelNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.deptNo = deptNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.qty = qty;
		this.outstockQty = outstockQty;
		this.instockQty = instockQty;
		this.unusualQty = unusualQty;
		this.status = status;
		this.flag = flag;
		this.instockType = instockType;
		this.mvHandFlag = mvHandFlag;
		this.labelNo = labelNo;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.subLabelNo = subLabelNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "cellNo", column = @Column(name = "CELL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "cellId", column = @Column(name = "CELL_ID", nullable = false, precision = 15, scale = 0)) })
	public Stock_ContentId getId() {
		return this.id;
	}

	public void setId(Stock_ContentId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "DEPT_NO", nullable = false, length = 10)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "ARTICLE_NO", nullable = false, length = 15)
	public String getArticleNo() {
		return this.articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	@Column(name = "ARTICLE_ID", nullable = false, precision = 12, scale = 0)
	public long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "QTY", nullable = false, precision = 18, scale = 5)
	public double getQty() {
		return this.qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	@Column(name = "OUTSTOCK_QTY", nullable = false, precision = 18, scale = 5)
	public double getOutstockQty() {
		return this.outstockQty;
	}

	public void setOutstockQty(double outstockQty) {
		this.outstockQty = outstockQty;
	}

	@Column(name = "INSTOCK_QTY", nullable = false, precision = 18, scale = 5)
	public double getInstockQty() {
		return this.instockQty;
	}

	public void setInstockQty(double instockQty) {
		this.instockQty = instockQty;
	}

	@Column(name = "UNUSUAL_QTY", nullable = false, precision = 18, scale = 5)
	public double getUnusualQty() {
		return this.unusualQty;
	}

	public void setUnusualQty(double unusualQty) {
		this.unusualQty = unusualQty;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "FLAG", nullable = false, length = 1)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "INSTOCK_TYPE", nullable = false, length = 1)
	public String getInstockType() {
		return this.instockType;
	}

	public void setInstockType(String instockType) {
		this.instockType = instockType;
	}

	@Column(name = "MV_HAND_FLAG", nullable = false, length = 1)
	public String getMvHandFlag() {
		return this.mvHandFlag;
	}

	public void setMvHandFlag(String mvHandFlag) {
		this.mvHandFlag = mvHandFlag;
	}

	@Column(name = "LABEL_NO", nullable = false, length = 24)
	public String getLabelNo() {
		return this.labelNo;
	}

	public void setLabelNo(String labelNo) {
		this.labelNo = labelNo;
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

	@Column(name = "SUB_LABEL_NO", nullable = false, length = 24)
	public String getSubLabelNo() {
		return this.subLabelNo;
	}

	public void setSubLabelNo(String subLabelNo) {
		this.subLabelNo = subLabelNo;
	}

}