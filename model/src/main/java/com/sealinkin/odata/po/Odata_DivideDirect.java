package com.sealinkin.odata.po;

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
 * OdataDivideDirect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_DIVIDE_DIRECT" )
public class Odata_DivideDirect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_DivideDirectId id;
	private String batchNo;
	private Date operateDate;
	private String custNo;
	private String subCustNo;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private Double articleQty;
	private String SCellNo;
	private Long SCellId;
	private String SContainerNo;
	private String custContainerNo;
	private String expType;
	private String expNo;
	private String waveNo;
	private String deliverArea;
	private String status;
	private String lineNo;
	private String trunckCellNo;
	private String checkChuteNo;
	private String deliverObj;
	private String advanceCellNo;
	private Date outstockDate;
	private String dpsCellNo;
	private String ASorterChuteNo;
	private Date expDate;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_DivideDirect() {
	}

	/** minimal constructor */
	public Odata_DivideDirect(Odata_DivideDirectId id, String batchNo,
			Date operateDate, String custNo, String subCustNo,
			String articleNo, Long articleId, Double packingQty,
			Double articleQty, String SCellNo, Long SCellId,
			String SContainerNo, String expType, String expNo, String waveNo,
			String status, String lineNo, String deliverObj, Date outstockDate,
			Date expDate, String rgstName, Date rgstDate) {
		this.id = id;
		this.batchNo = batchNo;
		this.operateDate = operateDate;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.SContainerNo = SContainerNo;
		this.expType = expType;
		this.expNo = expNo;
		this.waveNo = waveNo;
		this.status = status;
		this.lineNo = lineNo;
		this.deliverObj = deliverObj;
		this.outstockDate = outstockDate;
		this.expDate = expDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_DivideDirect(Odata_DivideDirectId id, String batchNo,
			Date operateDate, String custNo, String subCustNo,
			String articleNo, Long articleId, Double packingQty,
			Double articleQty, String SCellNo, Long SCellId,
			String SContainerNo, String custContainerNo, String expType,
			String expNo, String waveNo, String deliverArea, String status,
			String lineNo, String trunckCellNo, String checkChuteNo,
			String deliverObj, String advanceCellNo, Date outstockDate,
			String dpsCellNo, String ASorterChuteNo, Date expDate,
			String rgstName, Date rgstDate, String updtName, Date updtDate) {
		this.id = id;
		this.batchNo = batchNo;
		this.operateDate = operateDate;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.SContainerNo = SContainerNo;
		this.custContainerNo = custContainerNo;
		this.expType = expType;
		this.expNo = expNo;
		this.waveNo = waveNo;
		this.deliverArea = deliverArea;
		this.status = status;
		this.lineNo = lineNo;
		this.trunckCellNo = trunckCellNo;
		this.checkChuteNo = checkChuteNo;
		this.deliverObj = deliverObj;
		this.advanceCellNo = advanceCellNo;
		this.outstockDate = outstockDate;
		this.dpsCellNo = dpsCellNo;
		this.ASorterChuteNo = ASorterChuteNo;
		this.expDate = expDate;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "sourceNo", column = @Column(name = "SOURCE_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)),
			@AttributeOverride(name = "divideId", column = @Column(name = "DIVIDE_ID", nullable = false, precision = 10, scale = 0)) })
	public Odata_DivideDirectId getId() {
		return this.id;
	}

	public void setId(Odata_DivideDirectId id) {
		this.id = id;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OPERATE_DATE", nullable = false, length = 7)
	public Date getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
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

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 10, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
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

	@Column(name = "CUST_CONTAINER_NO", length = 24)
	public String getCustContainerNo() {
		return this.custContainerNo;
	}

	public void setCustContainerNo(String custContainerNo) {
		this.custContainerNo = custContainerNo;
	}

	@Column(name = "EXP_TYPE", nullable = false, length = 2)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
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

	@Column(name = "DELIVER_AREA", length = 24)
	public String getDeliverArea() {
		return this.deliverArea;
	}

	public void setDeliverArea(String deliverArea) {
		this.deliverArea = deliverArea;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LINE_NO", nullable = false, length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "TRUNCK_CELL_NO", length = 24)
	public String getTrunckCellNo() {
		return this.trunckCellNo;
	}

	public void setTrunckCellNo(String trunckCellNo) {
		this.trunckCellNo = trunckCellNo;
	}

	@Column(name = "CHECK_CHUTE_NO", length = 3)
	public String getCheckChuteNo() {
		return this.checkChuteNo;
	}

	public void setCheckChuteNo(String checkChuteNo) {
		this.checkChuteNo = checkChuteNo;
	}

	@Column(name = "DELIVER_OBJ", nullable = false, length = 24)
	public String getDeliverObj() {
		return this.deliverObj;
	}

	public void setDeliverObj(String deliverObj) {
		this.deliverObj = deliverObj;
	}

	@Column(name = "ADVANCE_CELL_NO", length = 24)
	public String getAdvanceCellNo() {
		return this.advanceCellNo;
	}

	public void setAdvanceCellNo(String advanceCellNo) {
		this.advanceCellNo = advanceCellNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OUTSTOCK_DATE", nullable = false, length = 7)
	public Date getOutstockDate() {
		return this.outstockDate;
	}

	public void setOutstockDate(Date outstockDate) {
		this.outstockDate = outstockDate;
	}

	@Column(name = "DPS_CELL_NO", length = 24)
	public String getDpsCellNo() {
		return this.dpsCellNo;
	}

	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}

	@Column(name = "A_SORTER_CHUTE_NO", length = 4)
	public String getASorterChuteNo() {
		return this.ASorterChuteNo;
	}

	public void setASorterChuteNo(String ASorterChuteNo) {
		this.ASorterChuteNo = ASorterChuteNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
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