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
 * Odata_DivideD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ODATA_DIVIDE_D")

public class Odata_DivideD  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Odata_DivideDId id;
     private String batchNo;
     private String sourceNo;
     private Date operateDate;
     private String custNo;
     private String subCustNo;
     private String expType;
     private String expNo;
     private String waveNo;
     private String articleNo;
     private Long articleId;
     private Double packingQty;
     private Double articleQty;
     private Double realQty;
     private String SCellNo;
     private Long SCellId;
     private String SContainerNo;
     private String DCellNo;
     private Long DCellId;
     private String deliverArea;
     private String lineNo;
     private String trunckCellNo;
     private String checkChuteNo;
     private String deliverObj;
     private Date outstockDate;
     private String assignName;
     private String divideName;
     private Date divideDate;
     private String dpsCellNo;
     private String ASorterChuteNo;
     private Date expDate;


    // Constructors

    /** default constructor */
    public Odata_DivideD() {
    }

	/** minimal constructor */
    public Odata_DivideD(Odata_DivideDId id, String batchNo, String sourceNo, Date operateDate, String custNo, String subCustNo, String expType, String expNo, String waveNo, String articleNo, Long articleId, Double packingQty, Double articleQty, String SCellNo, Long SCellId, String SContainerNo, String lineNo, String deliverObj, Date outstockDate, String assignName, Date expDate) {
        this.id = id;
        this.batchNo = batchNo;
        this.sourceNo = sourceNo;
        this.operateDate = operateDate;
        this.custNo = custNo;
        this.subCustNo = subCustNo;
        this.expType = expType;
        this.expNo = expNo;
        this.waveNo = waveNo;
        this.articleNo = articleNo;
        this.articleId = articleId;
        this.packingQty = packingQty;
        this.articleQty = articleQty;
        this.SCellNo = SCellNo;
        this.SCellId = SCellId;
        this.SContainerNo = SContainerNo;
        this.lineNo = lineNo;
        this.deliverObj = deliverObj;
        this.outstockDate = outstockDate;
        this.assignName = assignName;
        this.expDate = expDate;
    }
    
    /** full constructor */
    public Odata_DivideD(Odata_DivideDId id, String batchNo, String sourceNo, Date operateDate, String custNo, String subCustNo, String expType, String expNo, String waveNo, String articleNo, Long articleId, Double packingQty, Double articleQty, Double realQty, String SCellNo, Long SCellId, String SContainerNo, String DCellNo, Long DCellId, String deliverArea, String lineNo, String trunckCellNo, String checkChuteNo, String deliverObj, Date outstockDate, String assignName, String divideName, Date divideDate, String dpsCellNo, String ASorterChuteNo, Date expDate) {
        this.id = id;
        this.batchNo = batchNo;
        this.sourceNo = sourceNo;
        this.operateDate = operateDate;
        this.custNo = custNo;
        this.subCustNo = subCustNo;
        this.expType = expType;
        this.expNo = expNo;
        this.waveNo = waveNo;
        this.articleNo = articleNo;
        this.articleId = articleId;
        this.packingQty = packingQty;
        this.articleQty = articleQty;
        this.realQty = realQty;
        this.SCellNo = SCellNo;
        this.SCellId = SCellId;
        this.SContainerNo = SContainerNo;
        this.DCellNo = DCellNo;
        this.DCellId = DCellId;
        this.deliverArea = deliverArea;
        this.lineNo = lineNo;
        this.trunckCellNo = trunckCellNo;
        this.checkChuteNo = checkChuteNo;
        this.deliverObj = deliverObj;
        this.outstockDate = outstockDate;
        this.assignName = assignName;
        this.divideName = divideName;
        this.divideDate = divideDate;
        this.dpsCellNo = dpsCellNo;
        this.ASorterChuteNo = ASorterChuteNo;
        this.expDate = expDate;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="divideNo", column=@Column(name="DIVIDE_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="divideId", column=@Column(name="DIVIDE_ID", nullable=false, precision=10, scale=0) ), 
        @AttributeOverride(name="custContainerNo", column=@Column(name="CUST_CONTAINER_NO", nullable=false, length=24) ), 
        @AttributeOverride(name="status", column=@Column(name="STATUS", nullable=false, length=2) ) } )

    public Odata_DivideDId getId() {
        return this.id;
    }
    
    public void setId(Odata_DivideDId id) {
        this.id = id;
    }
    
    @Column(name="BATCH_NO", nullable=false, length=2)

    public String getBatchNo() {
        return this.batchNo;
    }
    
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    
    @Column(name="SOURCE_NO", nullable=false, length=20)

    public String getSourceNo() {
        return this.sourceNo;
    }
    
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="OPERATE_DATE", nullable=false, length=7)

    public Date getOperateDate() {
        return this.operateDate;
    }
    
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
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
    
    @Column(name="ARTICLE_QTY", nullable=false, precision=14, scale=5)

    public Double getArticleQty() {
        return this.articleQty;
    }
    
    public void setArticleQty(Double articleQty) {
        this.articleQty = articleQty;
    }
    
    @Column(name="REAL_QTY", precision=14, scale=5)

    public Double getRealQty() {
        return this.realQty;
    }
    
    public void setRealQty(Double realQty) {
        this.realQty = realQty;
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
    
    @Column(name="D_CELL_NO", length=24)

    public String getDCellNo() {
        return this.DCellNo;
    }
    
    public void setDCellNo(String DCellNo) {
        this.DCellNo = DCellNo;
    }
    
    @Column(name="D_CELL_ID", precision=10, scale=0)

    public Long getDCellId() {
        return this.DCellId;
    }
    
    public void setDCellId(Long DCellId) {
        this.DCellId = DCellId;
    }
    
    @Column(name="DELIVER_AREA", length=24)

    public String getDeliverArea() {
        return this.deliverArea;
    }
    
    public void setDeliverArea(String deliverArea) {
        this.deliverArea = deliverArea;
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
    @Temporal(TemporalType.DATE)
    @Column(name="OUTSTOCK_DATE", nullable=false, length=7)

    public Date getOutstockDate() {
        return this.outstockDate;
    }
    
    public void setOutstockDate(Date outstockDate) {
        this.outstockDate = outstockDate;
    }
    
    @Column(name="ASSIGN_NAME", nullable=false, length=20)

    public String getAssignName() {
        return this.assignName;
    }
    
    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
    
    @Column(name="DIVIDE_NAME", length=20)

    public String getDivideName() {
        return this.divideName;
    }
    
    public void setDivideName(String divideName) {
        this.divideName = divideName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DIVIDE_DATE", length=7)

    public Date getDivideDate() {
        return this.divideDate;
    }
    
    public void setDivideDate(Date divideDate) {
        this.divideDate = divideDate;
    }
    
    @Column(name="DPS_CELL_NO", length=24)

    public String getDpsCellNo() {
        return this.dpsCellNo;
    }
    
    public void setDpsCellNo(String dpsCellNo) {
        this.dpsCellNo = dpsCellNo;
    }
    
    @Column(name="A_SORTER_CHUTE_NO", length=4)

    public String getASorterChuteNo() {
        return this.ASorterChuteNo;
    }
    
    public void setASorterChuteNo(String ASorterChuteNo) {
        this.ASorterChuteNo = ASorterChuteNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXP_DATE", nullable=false, length=7)

    public Date getExpDate() {
        return this.expDate;
    }
    
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
   








}