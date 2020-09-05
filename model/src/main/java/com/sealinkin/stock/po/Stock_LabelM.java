package com.sealinkin.stock.po;

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
 * StockLabelM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STOCK_LABEL_M" )
public class Stock_LabelM implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Stock_LabelMId id;
	private String batchNo;
	private String sourceNo;
	private String deliverArea;
	private String status;
	private String loadContainerNo;
	private String ownerContainerNo;
	private String ownerCellNo;
	private String custNo;
	private String trunckCellNo;
	private String ASorterChuteNo;
	private String checkChuteNo;
	private String deliverObj;
	private String useType;
	private String lineNo;
	private String currArea;
	private Short seqValue;
	private Double length;
	private Double width;
	private Double height;
	private String deviceNo;
	private String reportId;
	private String recheckNo;
	private String midLabelNo;
	private String bigExpNoFlag;
	private String checkChuteInstatus;
	private String stockType;
	private Date expDate;
	private String chuteLabelFlag;
	private String hmManualFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;

	// Constructors

	/** default constructor */
	public Stock_LabelM() {
	}

	/** minimal constructor */
	public Stock_LabelM(Stock_LabelMId id, String batchNo, String sourceNo,
			String status, String ownerContainerNo, String ownerCellNo,
			String custNo, String deliverObj, String useType, String lineNo,
			String reportId, String stockType, Date expDate,
			String hmManualFlag, String rgstName, Date rgstDate) {
		this.id = id;
		this.batchNo = batchNo;
		this.sourceNo = sourceNo;
		this.status = status;
		this.ownerContainerNo = ownerContainerNo;
		this.ownerCellNo = ownerCellNo;
		this.custNo = custNo;
		this.deliverObj = deliverObj;
		this.useType = useType;
		this.lineNo = lineNo;
		this.reportId = reportId;
		this.stockType = stockType;
		this.expDate = expDate;
		this.hmManualFlag = hmManualFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
	}

	/** full constructor */
	public Stock_LabelM(Stock_LabelMId id, String batchNo, String sourceNo,
			String deliverArea, String status, String loadContainerNo,
			String ownerContainerNo, String ownerCellNo, String custNo,
			String trunckCellNo, String ASorterChuteNo, String checkChuteNo,
			String deliverObj, String useType, String lineNo, String currArea,
			Short seqValue, Double length, Double width, Double height,
			String deviceNo, String reportId, String recheckNo,
			String midLabelNo, String bigExpNoFlag, String checkChuteInstatus,
			String stockType, Date expDate, String chuteLabelFlag,
			String hmManualFlag, String rgstName, Date rgstDate,
			String updtName, Date updtDate) {
		this.id = id;
		this.batchNo = batchNo;
		this.sourceNo = sourceNo;
		this.deliverArea = deliverArea;
		this.status = status;
		this.loadContainerNo = loadContainerNo;
		this.ownerContainerNo = ownerContainerNo;
		this.ownerCellNo = ownerCellNo;
		this.custNo = custNo;
		this.trunckCellNo = trunckCellNo;
		this.ASorterChuteNo = ASorterChuteNo;
		this.checkChuteNo = checkChuteNo;
		this.deliverObj = deliverObj;
		this.useType = useType;
		this.lineNo = lineNo;
		this.currArea = currArea;
		this.seqValue = seqValue;
		this.length = length;
		this.width = width;
		this.height = height;
		this.deviceNo = deviceNo;
		this.reportId = reportId;
		this.recheckNo = recheckNo;
		this.midLabelNo = midLabelNo;
		this.bigExpNoFlag = bigExpNoFlag;
		this.checkChuteInstatus = checkChuteInstatus;
		this.stockType = stockType;
		this.expDate = expDate;
		this.chuteLabelFlag = chuteLabelFlag;
		this.hmManualFlag = hmManualFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "labelNo", column = @Column(name = "LABEL_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "containerNo", column = @Column(name = "CONTAINER_NO", nullable = false, length = 24)),
			@AttributeOverride(name = "containerType", column = @Column(name = "CONTAINER_TYPE", nullable = false, length = 1)) })
	public Stock_LabelMId getId() {
		return this.id;
	}

	public void setId(Stock_LabelMId id) {
		this.id = id;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 2)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "SOURCE_NO", nullable = false, length = 20)
	public String getSourceNo() {
		return this.sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Column(name = "DELIVER_AREA", length = 24)
	public String getDeliverArea() {
		return this.deliverArea;
	}

	public void setDeliverArea(String deliverArea) {
		this.deliverArea = deliverArea;
	}

	@Column(name = "STATUS", nullable = false, length = 3)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LOAD_CONTAINER_NO", length = 24)
	public String getLoadContainerNo() {
		return this.loadContainerNo;
	}

	public void setLoadContainerNo(String loadContainerNo) {
		this.loadContainerNo = loadContainerNo;
	}

	@Column(name = "OWNER_CONTAINER_NO", nullable = false, length = 24)
	public String getOwnerContainerNo() {
		return this.ownerContainerNo;
	}

	public void setOwnerContainerNo(String ownerContainerNo) {
		this.ownerContainerNo = ownerContainerNo;
	}

	@Column(name = "OWNER_CELL_NO", nullable = false, length = 24)
	public String getOwnerCellNo() {
		return this.ownerCellNo;
	}

	public void setOwnerCellNo(String ownerCellNo) {
		this.ownerCellNo = ownerCellNo;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "TRUNCK_CELL_NO", length = 24)
	public String getTrunckCellNo() {
		return this.trunckCellNo;
	}

	public void setTrunckCellNo(String trunckCellNo) {
		this.trunckCellNo = trunckCellNo;
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

	@Column(name = "USE_TYPE", nullable = false, length = 2)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "LINE_NO", nullable = false, length = 4)
	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "CURR_AREA", length = 24)
	public String getCurrArea() {
		return this.currArea;
	}

	public void setCurrArea(String currArea) {
		this.currArea = currArea;
	}

	@Column(name = "SEQ_VALUE", precision = 3, scale = 0)
	public Short getSeqValue() {
		return this.seqValue;
	}

	public void setSeqValue(Short seqValue) {
		this.seqValue = seqValue;
	}

	@Column(name = "LENGTH", precision = 10, scale = 5)
	public Double getLength() {
		return this.length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@Column(name = "WIDTH", precision = 10, scale = 5)
	public Double getWidth() {
		return this.width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	@Column(name = "HEIGHT", precision = 10, scale = 5)
	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Column(name = "DEVICE_NO", length = 3)
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}


	@Column(name = "REPORT_ID", nullable = false, length = 20)
	public String getReportId() {
		return this.reportId;
	}

	
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	@Column(name = "RECHECK_NO", length = 20)
	public String getRecheckNo() {
		return this.recheckNo;
	}

	public void setRecheckNo(String recheckNo) {
		this.recheckNo = recheckNo;
	}

	@Column(name = "MID_LABEL_NO", length = 24)
	public String getMidLabelNo() {
		return this.midLabelNo;
	}

	public void setMidLabelNo(String midLabelNo) {
		this.midLabelNo = midLabelNo;
	}

	@Column(name = "BIG_EXP_NO_FLAG", length = 1)
	public String getBigExpNoFlag() {
		return this.bigExpNoFlag;
	}

	public void setBigExpNoFlag(String bigExpNoFlag) {
		this.bigExpNoFlag = bigExpNoFlag;
	}

	@Column(name = "CHECK_CHUTE_INSTATUS", length = 2)
	public String getCheckChuteInstatus() {
		return this.checkChuteInstatus;
	}

	public void setCheckChuteInstatus(String checkChuteInstatus) {
		this.checkChuteInstatus = checkChuteInstatus;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "CHUTE_LABEL_FLAG", length = 1)
	public String getChuteLabelFlag() {
		return this.chuteLabelFlag;
	}

	public void setChuteLabelFlag(String chuteLabelFlag) {
		this.chuteLabelFlag = chuteLabelFlag;
	}

	@Column(name = "HM_MANUAL_FLAG", nullable = false, length = 1)
	public String getHmManualFlag() {
		return this.hmManualFlag;
	}

	public void setHmManualFlag(String hmManualFlag) {
		this.hmManualFlag = hmManualFlag;
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