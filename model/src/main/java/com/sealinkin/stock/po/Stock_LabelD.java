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
 * Stock_LabelD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_LABEL_D")
public class Stock_LabelD implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Stock_LabelDId id;
	private String batchNo;
	private String ownerNo;
	private String sourceNo;
	private String containerType;
	private String articleNo;
	private long articleId;
	private double packingQty;
	private double qty;
	private String expNo;
	private String waveNo;
	private String custNo;
	private String subCustNo;
	private String lineNo;
	private String status;
	private Integer divideId;
	private String expType;
	private String dpsCellNo;
	private String deliverObj;
	private Date expDate;
	private String advanceCellNo;
	private String advanceStatus;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Stock_LabelD() {
	}

	/** minimal constructor */
	public Stock_LabelD(Stock_LabelDId id, String batchNo, String ownerNo,
			String sourceNo, String containerType, String articleNo,
			long articleId, double packingQty, double qty, String expNo,
			String waveNo, String custNo, String subCustNo, String lineNo,
			String status, Integer divideId, String expType, String deliverObj,
			Date expDate, String rgstName, Date rgstDate) {
		this.id = id;
		this.batchNo = batchNo;
		this.ownerNo = ownerNo;
		this.sourceNo = sourceNo;
		this.containerType = containerType;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.qty = qty;
		this.expNo = expNo;
		this.waveNo = waveNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.lineNo = lineNo;
		this.status = status;
		this.divideId = divideId;
		this.expType = expType;
		this.deliverObj = deliverObj;
		this.expDate = expDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Stock_LabelD(Stock_LabelDId id, String batchNo, String ownerNo,
			String sourceNo, String containerType, String articleNo,
			long articleId, double packingQty, double qty, String expNo,
			String waveNo, String custNo, String subCustNo, String lineNo,
			String status, Integer divideId, String expType, String dpsCellNo,
			String deliverObj, Date expDate, String advanceCellNo,
			String advanceStatus, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.batchNo = batchNo;
		this.ownerNo = ownerNo;
		this.sourceNo = sourceNo;
		this.containerType = containerType;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.qty = qty;
		this.expNo = expNo;
		this.waveNo = waveNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.lineNo = lineNo;
		this.status = status;
		this.divideId = divideId;
		this.expType = expType;
		this.dpsCellNo = dpsCellNo;
		this.deliverObj = deliverObj;
		this.expDate = expDate;
		this.advanceCellNo = advanceCellNo;
		this.advanceStatus = advanceStatus;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "containerNo", column = @Column(name = "CONTAINER_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "rowId", column = @Column(name = "ROW_ID", nullable = false, precision = 10, scale = 0)) })
	public Stock_LabelDId getId() {
		return this.id;
	}

	public void setId(Stock_LabelDId id) {
		this.id = id;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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

	@Column(name = "CONTAINER_TYPE", nullable = false, length = 1)
	public String getContainerType() {
		return this.containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
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

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
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

	@Column(name = "LINE_NO", nullable = false, length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DIVIDE_ID", nullable = false, precision = 8, scale = 0)
	public Integer getDivideId() {
		return this.divideId;
	}

	public void setDivideId(Integer divideId) {
		this.divideId = divideId;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 2)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@Column(name = "DPS_CELL_NO", length = 24)
	public String getDpsCellNo() {
		return this.dpsCellNo;
	}

	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}

	@Column(name = "DELIVER_OBJ", nullable = false, length = 24)
	public String getDeliverObj() {
		return this.deliverObj;
	}

	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}

	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "ADVANCE_CELL_NO", length = 24)
	public String getAdvanceCellNo() {
		return this.advanceCellNo;
	}

	public void setAdvanceCellNo(String advanceCellNo) {
		this.advanceCellNo = advanceCellNo;
	}

	@Column(name = "ADVANCE_STATUS", length = 2)
	public String getAdvanceStatus() {
		return this.advanceStatus;
	}

	public void setAdvanceStatus(String advanceStatus) {
		this.advanceStatus = advanceStatus;
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

}