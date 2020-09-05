package com.sealinkin.idata.po;
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
 * Idata_ImportSd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="IDATA_IMPORT_SD")

public class Idata_ImportSd  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_ImportSdId id;
     private String articleNo;
     private Double packingQty;
     private Double poQty;
     private Double importQty;
     private Double qcQty;
     private String checkName;
     private String status;
     private Date checkDate;
     private String qcStatus;
     private String outStockFlag;
     private String outpaceArticleFlag;
     private String itemType;
     private Double planAcrossQty;
     private Double checkAcrossQty;
     private String qcFlag;


    // Constructors

    /** default constructor */
    public Idata_ImportSd() {
    }

	/** minimal constructor */
    public Idata_ImportSd(Idata_ImportSdId id, String articleNo, Double packingQty, Double poQty, Double importQty, Double qcQty, String status, String outStockFlag, String outpaceArticleFlag, String itemType, String qcFlag) {
        this.id = id;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.poQty = poQty;
        this.importQty = importQty;
        this.qcQty = qcQty;
        this.status = status;
        this.outStockFlag = outStockFlag;
        this.outpaceArticleFlag = outpaceArticleFlag;
        this.itemType = itemType;
        this.qcFlag = qcFlag;
    }
    
    /** full constructor */
    public Idata_ImportSd(Idata_ImportSdId id, String articleNo, Double packingQty, Double poQty, Double importQty, Double qcQty, String checkName, String status, Date checkDate, String qcStatus, String outStockFlag, String outpaceArticleFlag, String itemType, Double planAcrossQty, Double checkAcrossQty, String qcFlag) {
        this.id = id;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.poQty = poQty;
        this.importQty = importQty;
        this.qcQty = qcQty;
        this.checkName = checkName;
        this.status = status;
        this.checkDate = checkDate;
        this.qcStatus = qcStatus;
        this.outStockFlag = outStockFlag;
        this.outpaceArticleFlag = outpaceArticleFlag;
        this.itemType = itemType;
        this.planAcrossQty = planAcrossQty;
        this.checkAcrossQty = checkAcrossQty;
        this.qcFlag = qcFlag;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="SImportNo", column=@Column(name="S_IMPORT_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="rowId", column=@Column(name="ROW_ID", nullable=false, precision=4, scale=0) ) } )

    public Idata_ImportSdId getId() {
        return this.id;
    }
    
    public void setId(Idata_ImportSdId id) {
        this.id = id;
    }
    
    @Column(name="ARTICLE_NO", nullable=false, length=15)

    public String getArticleNo() {
        return this.articleNo;
    }
    
    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }
    
    @Column(name="PACKING_QTY", nullable=false, precision=18, scale=5)

    public Double getPackingQty() {
        return this.packingQty;
    }
    
    public void setPackingQty(Double packingQty) {
        this.packingQty = packingQty;
    }
    
    @Column(name="PO_QTY", nullable=false, precision=18, scale=5)

    public Double getPoQty() {
        return this.poQty;
    }
    
    public void setPoQty(Double poQty) {
        this.poQty = poQty;
    }
    
    @Column(name="IMPORT_QTY", nullable=false, precision=18, scale=5)

    public Double getImportQty() {
        return this.importQty;
    }
    
    public void setImportQty(Double importQty) {
        this.importQty = importQty;
    }
    
    @Column(name="QC_QTY", nullable=false, precision=18, scale=5)

    public Double getQcQty() {
        return this.qcQty;
    }
    
    public void setQcQty(Double qcQty) {
        this.qcQty = qcQty;
    }
    
    @Column(name="CHECK_NAME", length=20)

    public String getCheckName() {
        return this.checkName;
    }
    
    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CHECK_DATE", length=7)

    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    
    @Column(name="QC_STATUS", length=2)

    public String getQcStatus() {
        return this.qcStatus;
    }
    
    public void setQcStatus(String qcStatus) {
        this.qcStatus = qcStatus;
    }
    
    @Column(name="OUT_STOCK_FLAG", nullable=false, length=1)

    public String getOutStockFlag() {
        return this.outStockFlag;
    }
    
    public void setOutStockFlag(String outStockFlag) {
        this.outStockFlag = outStockFlag;
    }
    
    @Column(name="OUTPACE_ARTICLE_FLAG", nullable=false, length=1)

    public String getOutpaceArticleFlag() {
        return this.outpaceArticleFlag;
    }
    
    public void setOutpaceArticleFlag(String outpaceArticleFlag) {
        this.outpaceArticleFlag = outpaceArticleFlag;
    }
    
    @Column(name="ITEM_TYPE", nullable=false, length=20)

    public String getItemType() {
        return this.itemType;
    }
    
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    @Column(name="PLAN_ACROSS_QTY", precision=15)

    public Double getPlanAcrossQty() {
        return this.planAcrossQty;
    }
    
    public void setPlanAcrossQty(Double planAcrossQty) {
        this.planAcrossQty = planAcrossQty;
    }
    
    @Column(name="CHECK_ACROSS_QTY", precision=15)

    public Double getCheckAcrossQty() {
        return this.checkAcrossQty;
    }
    
    public void setCheckAcrossQty(Double checkAcrossQty) {
        this.checkAcrossQty = checkAcrossQty;
    }
    
    @Column(name="QC_FLAG", nullable=false, length=1)

    public String getQcFlag() {
        return this.qcFlag;
    }
    
    public void setQcFlag(String qcFlag) {
        this.qcFlag = qcFlag;
    }
   








}