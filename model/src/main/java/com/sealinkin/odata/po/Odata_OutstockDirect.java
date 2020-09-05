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
 * OdataOutstockDirect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_OUTSTOCK_DIRECT" )
public class Odata_OutstockDirect implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_OutstockDirectId id;
	private String ownerNo;
	private String outstockType;
	private String sourceType;
	private String operateType;
	private String pickType;
	private String batchNo;
	private String waveNo;
	private String expType;
	private String expNo;
	private String custNo;
	private String subCustNo;
	private String articleNo;
	private Long articleId;
	private Double packingQty;
	private String SCellNo;
	private Long SCellId;
	private String SContainerNo;
	private String DCellNo;
	private Long DCellId;
	private String pickContainerNo;
	private String custContainerNo;
	private Double locateQty;
	private String status;
	private String deliverArea;
	private String lineNo;
	private Short priority;
	private String ASorterChuteNo;
	private String checkChuteNo;
	private String deliverObj;
	private Long suppCount;
	private String deviceNo;
	private String dpsCellNo;
	private String tempStatus;
	private Date expDate;
	private String stockType;
	private String labelNo;
	private String subLabelNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Odata_OutstockDirect() {
	}

	/** minimal constructor */
	public Odata_OutstockDirect(Odata_OutstockDirectId id, String ownerNo,
			String outstockType, String sourceType, String operateType,
			String pickType, String batchNo, String waveNo, String expType,
			String expNo, String custNo, String subCustNo, String articleNo,
			Long articleId, Double packingQty, String SCellNo, Long SCellId,
			String SContainerNo, String DCellNo, Long DCellId,
			String pickContainerNo, String custContainerNo, Double locateQty,
			String status, String lineNo, Short priority, String deliverObj,
			Date expDate, String stockType, String labelNo, String subLabelNo,
			String rgstName, Date rgstDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.outstockType = outstockType;
		this.sourceType = sourceType;
		this.operateType = operateType;
		this.pickType = pickType;
		this.batchNo = batchNo;
		this.waveNo = waveNo;
		this.expType = expType;
		this.expNo = expNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.SContainerNo = SContainerNo;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.pickContainerNo = pickContainerNo;
		this.custContainerNo = custContainerNo;
		this.locateQty = locateQty;
		this.status = status;
		this.lineNo = lineNo;
		this.priority = priority;
		this.deliverObj = deliverObj;
		this.expDate = expDate;
		this.stockType = stockType;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Odata_OutstockDirect(Odata_OutstockDirectId id, String ownerNo,
			String outstockType, String sourceType, String operateType,
			String pickType, String batchNo, String waveNo, String expType,
			String expNo, String custNo, String subCustNo, String articleNo,
			Long articleId, Double packingQty, String SCellNo, Long SCellId,
			String SContainerNo, String DCellNo, Long DCellId,
			String pickContainerNo, String custContainerNo, Double locateQty,
			String status, String deliverArea, String lineNo, Short priority,
			String ASorterChuteNo, String checkChuteNo, String deliverObj,
			Long suppCount, String deviceNo, String dpsCellNo,
			String tempStatus, Date expDate, String stockType, String labelNo,
			String subLabelNo, String rgstName, Date rgstDate, String updtName,
			Date updtDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.outstockType = outstockType;
		this.sourceType = sourceType;
		this.operateType = operateType;
		this.pickType = pickType;
		this.batchNo = batchNo;
		this.waveNo = waveNo;
		this.expType = expType;
		this.expNo = expNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.articleNo = articleNo;
		this.articleId = articleId;
		this.packingQty = packingQty;
		this.SCellNo = SCellNo;
		this.SCellId = SCellId;
		this.SContainerNo = SContainerNo;
		this.DCellNo = DCellNo;
		this.DCellId = DCellId;
		this.pickContainerNo = pickContainerNo;
		this.custContainerNo = custContainerNo;
		this.locateQty = locateQty;
		this.status = status;
		this.deliverArea = deliverArea;
		this.lineNo = lineNo;
		this.priority = priority;
		this.ASorterChuteNo = ASorterChuteNo;
		this.checkChuteNo = checkChuteNo;
		this.deliverObj = deliverObj;
		this.suppCount = suppCount;
		this.deviceNo = deviceNo;
		this.dpsCellNo = dpsCellNo;
		this.tempStatus = tempStatus;
		this.expDate = expDate;
		this.stockType = stockType;
		this.labelNo = labelNo;
		this.subLabelNo = subLabelNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "directSerial", column = @Column(name = "DIRECT_SERIAL", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "operateDate", column = @Column(name = "OPERATE_DATE", nullable = false, length = 7)) })
	public Odata_OutstockDirectId getId() {
		return this.id;
	}

	public void setId(Odata_OutstockDirectId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 5)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "OUTSTOCK_TYPE", nullable = false, length = 1)
	public String getOutstockType() {
		return this.outstockType;
	}

	public void setOutstockType(String outstockType) {
		this.outstockType = outstockType;
	}

	@Column(name = "SOURCE_TYPE", nullable = false, length = 2)
	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "OPERATE_TYPE", nullable = false, length = 1)
	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "PICK_TYPE", nullable = false, length = 1)
	public String getPickType() {
		return this.pickType;
	}

	public void setPickType(String pickType) {
		this.pickType = pickType;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "WAVE_NO", nullable = false, length = 20)
	public String getWaveNo() {
		return this.waveNo;
	}

	public void setWaveNo(String waveNo) {
		this.waveNo = waveNo;
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

	@Column(name = "PICK_CONTAINER_NO", nullable = false, length = 24)
	public String getPickContainerNo() {
		return this.pickContainerNo;
	}

	public void setPickContainerNo(String pickContainerNo) {
		this.pickContainerNo = pickContainerNo;
	}

	@Column(name = "CUST_CONTAINER_NO", nullable = false, length = 24)
	public String getCustContainerNo() {
		return this.custContainerNo;
	}

	public void setCustContainerNo(String custContainerNo) {
		this.custContainerNo = custContainerNo;
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

	@Column(name = "DELIVER_AREA", length = 24)
	public String getDeliverArea() {
		return this.deliverArea;
	}

	public void setDeliverArea(String deliverArea) {
		this.deliverArea = deliverArea;
	}

	@Column(name = "LINE_NO", nullable = false, length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "PRIORITY", nullable = false, precision = 3, scale = 0)
	public Short getPriority() {
		return this.priority;
	}

	public void setPriority(Short priority) {
		this.priority = priority;
	}

	@Column(name = "A_SORTER_CHUTE_NO", length = 4)
	public String getASorterChuteNo() {
		return this.ASorterChuteNo;
	}

	public void setASorterChuteNo(String ASorterChuteNo) {
		this.ASorterChuteNo = ASorterChuteNo;
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

	@Column(name = "SUPP_COUNT", precision = 10, scale = 0)
	public Long getSuppCount() {
		return this.suppCount;
	}

	public void setSuppCount(Long suppCount) {
		this.suppCount = suppCount;
	}

	@Column(name = "DEVICE_NO", length = 3)
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}


	@Column(name = "DPS_CELL_NO", length = 24)
	public String getDpsCellNo() {
		return this.dpsCellNo;
	}


	public void setDpsCellNo(String dpsCellNo) {
		this.dpsCellNo = dpsCellNo;
	}

	@Column(name = "TEMP_STATUS", length = 2)
	public String getTempStatus() {
		return this.tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
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