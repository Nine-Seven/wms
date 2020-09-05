package com.sealinkin.odata.po;
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
 * Odata_OutstockD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ODATA_OUTSTOCK_D")

public class Odata_OutstockD  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_OutstockDId id;
     private Date operateDate;
     private String batchNo;
     private String expType;
     private String expNo;
     private String waveNo;
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
     private Double articleQty;
     private Double realQty;
     private String deliverArea;
     private String status;
     private String lineNo;
     private String trunckCellNo;
     private String ASorterChuteNo;
     private String checkChuteNo;
     private String deliverObj;
     private String advanceCellNo;
     private String assignName;
     private String outstockName;
     private String instockName;
     private Date outstockDate;
     private Date instockDate;
     private String dpsCellNo;
     private String deviceNo;
     private Short priority;
     private Date expDate;
     private String pickDevice;
     private String advancePickName;
     private Date advancePickDate;
     private String labelNo;
     private String stockType;
     private String emptyFlag;
     private String unboxFlag;
     private String subLabelNo;


    // Constructors

    /** default constructor */
    public Odata_OutstockD() {
    }

	/** minimal constructor */
    public Odata_OutstockD(Odata_OutstockDId id, Date operateDate, String batchNo, String expType, String expNo, String waveNo, String custNo, String subCustNo, String articleNo, Long articleId, Double packingQty, String SCellNo, Long SCellId, String SContainerNo, String DCellNo, Long DCellId, String pickContainerNo, String custContainerNo, Double articleQty, Double realQty, String status, String lineNo, String deliverObj, String assignName, Short priority, Date expDate, String labelNo, String stockType, String unboxFlag) {
        this.id = id;
        this.operateDate = operateDate;
        this.batchNo = batchNo;
        this.expType = expType;
        this.expNo = expNo;
        this.waveNo = waveNo;
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
        this.articleQty = articleQty;
        this.realQty = realQty;
        this.status = status;
        this.lineNo = lineNo;
        this.deliverObj = deliverObj;
        this.assignName = assignName;
        this.priority = priority;
        this.expDate = expDate;
        this.labelNo = labelNo;
        this.stockType = stockType;
        this.unboxFlag = unboxFlag;
    }
    
    /** full constructor */
    public Odata_OutstockD(Odata_OutstockDId id, Date operateDate, String batchNo, String expType, String expNo, String waveNo, String custNo, String subCustNo, String articleNo, Long articleId, Double packingQty, String SCellNo, Long SCellId, String SContainerNo, String DCellNo, Long DCellId, String pickContainerNo, String custContainerNo, Double articleQty, Double realQty, String deliverArea, String status, String lineNo, String trunckCellNo, String ASorterChuteNo, String checkChuteNo, String deliverObj, String advanceCellNo, String assignName, String outstockName, String instockName, Date outstockDate, Date instockDate, String dpsCellNo, String deviceNo, Short priority, Date expDate, String pickDevice, String advancePickName, Date advancePickDate, String labelNo, String stockType, String emptyFlag, String unboxFlag, String subLabelNo) {
        this.id = id;
        this.operateDate = operateDate;
        this.batchNo = batchNo;
        this.expType = expType;
        this.expNo = expNo;
        this.waveNo = waveNo;
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
        this.articleQty = articleQty;
        this.realQty = realQty;
        this.deliverArea = deliverArea;
        this.status = status;
        this.lineNo = lineNo;
        this.trunckCellNo = trunckCellNo;
        this.ASorterChuteNo = ASorterChuteNo;
        this.checkChuteNo = checkChuteNo;
        this.deliverObj = deliverObj;
        this.advanceCellNo = advanceCellNo;
        this.assignName = assignName;
        this.outstockName = outstockName;
        this.instockName = instockName;
        this.outstockDate = outstockDate;
        this.instockDate = instockDate;
        this.dpsCellNo = dpsCellNo;
        this.deviceNo = deviceNo;
        this.priority = priority;
        this.expDate = expDate;
        this.pickDevice = pickDevice;
        this.advancePickName = advancePickName;
        this.advancePickDate = advancePickDate;
        this.labelNo = labelNo;
        this.stockType = stockType;
        this.emptyFlag = emptyFlag;
        this.unboxFlag = unboxFlag;
        this.subLabelNo = subLabelNo;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="outstockNo", column=@Column(name="OUTSTOCK_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="divideId", column=@Column(name="DIVIDE_ID", nullable=false, precision=8, scale=0) ) } )

    public Odata_OutstockDId getId() {
        return this.id;
    }
    
    public void setId(Odata_OutstockDId id) {
        this.id = id;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="OPERATE_DATE", nullable=false, length=7)

    public Date getOperateDate() {
        return this.operateDate;
    }
    
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
    
    @Column(name="BATCH_NO", nullable=false, length=2)

    public String getBatchNo() {
        return this.batchNo;
    }
    
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    
    @Column(name="EXP_TYPE", nullable=false, length=2)

    public String getExpType() {
        return this.expType;
    }
    
    public void setExpType(String expType) {
        this.expType = expType;
    }
    
    @Column(name="EXP_NO", nullable=false, length=20)

    public String getExpNo() {
        return this.expNo;
    }
    
    public void setExpNo(String expNo) {
        this.expNo = expNo;
    }
    
    @Column(name="WAVE_NO", nullable=false, length=20)

    public String getWaveNo() {
        return this.waveNo;
    }
    
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }
    
    @Column(name="CUST_NO", nullable=false, length=20)

    public String getCustNo() {
        return this.custNo;
    }
    
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
    
    @Column(name="SUB_CUST_NO", nullable=false, length=20)

    public String getSubCustNo() {
        return this.subCustNo;
    }
    
    public void setSubCustNo(String subCustNo) {
        this.subCustNo = subCustNo;
    }
    
    @Column(name="ARTICLE_NO", nullable=false, length=15)

    public String getArticleNo() {
        return this.articleNo;
    }
    
    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }
    
    @Column(name="ARTICLE_ID", nullable=false, precision=12, scale=0)

    public Long getArticleId() {
        return this.articleId;
    }
    
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    
    @Column(name="PACKING_QTY", nullable=false, precision=18, scale=5)

    public Double getPackingQty() {
        return this.packingQty;
    }
    
    public void setPackingQty(Double packingQty) {
        this.packingQty = packingQty;
    }
    
    @Column(name="S_CELL_NO", nullable=false, length=24)

    public String getSCellNo() {
        return this.SCellNo;
    }
    
    public void setSCellNo(String SCellNo) {
        this.SCellNo = SCellNo;
    }
    
    @Column(name="S_CELL_ID", nullable=false, precision=10, scale=0)

    public Long getSCellId() {
        return this.SCellId;
    }
    
    public void setSCellId(Long SCellId) {
        this.SCellId = SCellId;
    }
    
    @Column(name="S_CONTAINER_NO", nullable=false, length=24)

    public String getSContainerNo() {
        return this.SContainerNo;
    }
    
    public void setSContainerNo(String SContainerNo) {
        this.SContainerNo = SContainerNo;
    }
    
    @Column(name="D_CELL_NO", nullable=false, length=24)

    public String getDCellNo() {
        return this.DCellNo;
    }
    
    public void setDCellNo(String DCellNo) {
        this.DCellNo = DCellNo;
    }
    
    @Column(name="D_CELL_ID", nullable=false, precision=10, scale=0)

    public Long getDCellId() {
        return this.DCellId;
    }
    
    public void setDCellId(Long DCellId) {
        this.DCellId = DCellId;
    }
    
    @Column(name="PICK_CONTAINER_NO", nullable=false, length=24)

    public String getPickContainerNo() {
        return this.pickContainerNo;
    }
    
    public void setPickContainerNo(String pickContainerNo) {
        this.pickContainerNo = pickContainerNo;
    }
    
    @Column(name="CUST_CONTAINER_NO", nullable=false, length=24)

    public String getCustContainerNo() {
        return this.custContainerNo;
    }
    
    public void setCustContainerNo(String custContainerNo) {
        this.custContainerNo = custContainerNo;
    }
    
    @Column(name="ARTICLE_QTY", nullable=false, precision=15, scale=5)

    public Double getArticleQty() {
        return this.articleQty;
    }
    
    public void setArticleQty(Double articleQty) {
        this.articleQty = articleQty;
    }
    
    @Column(name="REAL_QTY", nullable=false, precision=15, scale=5)

    public Double getRealQty() {
        return this.realQty;
    }
    
    public void setRealQty(Double realQty) {
        this.realQty = realQty;
    }
    
    @Column(name="DELIVER_AREA", length=24)

    public String getDeliverArea() {
        return this.deliverArea;
    }
    
    public void setDeliverArea(String deliverArea) {
        this.deliverArea = deliverArea;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="LINE_NO", nullable=false, length=4)

    public String getLineNo() {
        return this.lineNo;
    }
    
    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }
    
    @Column(name="TRUNCK_CELL_NO", length=24)

    public String getTrunckCellNo() {
        return this.trunckCellNo;
    }
    
    public void setTrunckCellNo(String trunckCellNo) {
        this.trunckCellNo = trunckCellNo;
    }
    
    @Column(name="A_SORTER_CHUTE_NO", length=4)

    public String getASorterChuteNo() {
        return this.ASorterChuteNo;
    }
    
    public void setASorterChuteNo(String ASorterChuteNo) {
        this.ASorterChuteNo = ASorterChuteNo;
    }
    
    @Column(name="CHECK_CHUTE_NO", length=3)

    public String getCheckChuteNo() {
        return this.checkChuteNo;
    }
    
    public void setCheckChuteNo(String checkChuteNo) {
        this.checkChuteNo = checkChuteNo;
    }
    
    @Column(name="DELIVER_OBJ", nullable=false, length=24)

    public String getDeliverObj() {
        return this.deliverObj;
    }
    
    public void setDeliverObj(String deliverObj) {
        this.deliverObj = deliverObj;
    }
    
    @Column(name="ADVANCE_CELL_NO", length=24)

    public String getAdvanceCellNo() {
        return this.advanceCellNo;
    }
    
    public void setAdvanceCellNo(String advanceCellNo) {
        this.advanceCellNo = advanceCellNo;
    }
    
    @Column(name="ASSIGN_NAME", nullable=false, length=20)

    public String getAssignName() {
        return this.assignName;
    }
    
    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
    
    @Column(name="OUTSTOCK_NAME", length=20)

    public String getOutstockName() {
        return this.outstockName;
    }
    
    public void setOutstockName(String outstockName) {
        this.outstockName = outstockName;
    }
    
    @Column(name="INSTOCK_NAME", length=20)

    public String getInstockName() {
        return this.instockName;
    }
    
    public void setInstockName(String instockName) {
        this.instockName = instockName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="OUTSTOCK_DATE", length=7)

    public Date getOutstockDate() {
        return this.outstockDate;
    }
    
    public void setOutstockDate(Date outstockDate) {
        this.outstockDate = outstockDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="INSTOCK_DATE", length=7)

    public Date getInstockDate() {
        return this.instockDate;
    }
    
    public void setInstockDate(Date instockDate) {
        this.instockDate = instockDate;
    }
    
    @Column(name="DPS_CELL_NO", length=24)

    public String getDpsCellNo() {
        return this.dpsCellNo;
    }
    
    public void setDpsCellNo(String dpsCellNo) {
        this.dpsCellNo = dpsCellNo;
    }
    
    @Column(name="DEVICE_NO", length=3)

    public String getDeviceNo() {
 		return deviceNo;
 	}

 	public void setDeviceNo(String deviceNo) {
 		this.deviceNo = deviceNo;
 	}

    
    @Column(name="PRIORITY", nullable=false, precision=3, scale=0)

    public Short getPriority() {
        return this.priority;
    }
    
 
	public void setPriority(Short priority) {
        this.priority = priority;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXP_DATE", nullable=false, length=7)

    public Date getExpDate() {
        return this.expDate;
    }
    
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    
    @Column(name="PICK_DEVICE", length=10)

    public String getPickDevice() {
        return this.pickDevice;
    }
    
    public void setPickDevice(String pickDevice) {
        this.pickDevice = pickDevice;
    }
    
    @Column(name="ADVANCE_PICK_NAME", length=20)

    public String getAdvancePickName() {
        return this.advancePickName;
    }
    
    public void setAdvancePickName(String advancePickName) {
        this.advancePickName = advancePickName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ADVANCE_PICK_DATE", length=7)

    public Date getAdvancePickDate() {
        return this.advancePickDate;
    }
    
    public void setAdvancePickDate(Date advancePickDate) {
        this.advancePickDate = advancePickDate;
    }
    
    @Column(name="LABEL_NO", nullable=false, length=24)

    public String getLabelNo() {
        return this.labelNo;
    }
    
    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }
    
    @Column(name="STOCK_TYPE", nullable=false, length=1)

    public String getStockType() {
        return this.stockType;
    }
    
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }
    
    @Column(name="EMPTY_FLAG", length=1)

    public String getEmptyFlag() {
        return this.emptyFlag;
    }
    
    public void setEmptyFlag(String emptyFlag) {
        this.emptyFlag = emptyFlag;
    }
    
    @Column(name="UNBOX_FLAG", nullable=false, length=1)

    public String getUnboxFlag() {
        return this.unboxFlag;
    }
    
    public void setUnboxFlag(String unboxFlag) {
        this.unboxFlag = unboxFlag;
    }
    
    @Column(name="SUB_LABEL_NO", length=24)

    public String getSubLabelNo() {
        return this.subLabelNo;
    }
    
    public void setSubLabelNo(String subLabelNo) {
        this.subLabelNo = subLabelNo;
    }
   








}