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
 * Idata_ImportD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="IDATA_IMPORT_D")

public class Idata_ImportD  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_ImportDId id;
     private String articleNo;
     private Double packingQty;
     private Double poQty;
     private Double importQty;
     private Double unitCost;
     private String checkName;
     private String status;
     private Date checkDate;
     private String outStockFlag;
     private String errorStatus;
     private String itemType;
     private Double planAcrossQty;
     private Double checkAcrossQty;
     private String qcStatus;
     private String qcFlag;


    // Constructors

    /** default constructor */
    public Idata_ImportD() {
    }

	/** minimal constructor */
    public Idata_ImportD(Idata_ImportDId id, String articleNo, Double packingQty, Double poQty, Double importQty, String status, String outStockFlag, String itemType, String qcStatus, String qcFlag) {
        this.id = id;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.poQty = poQty;
        this.importQty = importQty;
        this.status = status;
        this.outStockFlag = outStockFlag;
        this.itemType = itemType;
        this.qcStatus = qcStatus;
        this.qcFlag = qcFlag;
    }
    
    /** full constructor */
    public Idata_ImportD(Idata_ImportDId id, String articleNo, Double packingQty, Double poQty, Double importQty, Double unitCost, String checkName, String status, Date checkDate, String outStockFlag, String errorStatus, String itemType, Double planAcrossQty, Double checkAcrossQty, String qcStatus, String qcFlag) {
        this.id = id;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.poQty = poQty;
        this.importQty = importQty;
        this.unitCost = unitCost;
        this.checkName = checkName;
        this.status = status;
        this.checkDate = checkDate;
        this.outStockFlag = outStockFlag;
        this.errorStatus = errorStatus;
        this.itemType = itemType;
        this.planAcrossQty = planAcrossQty;
        this.checkAcrossQty = checkAcrossQty;
        this.qcStatus = qcStatus;
        this.qcFlag = qcFlag;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="importNo", column=@Column(name="IMPORT_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="poId", column=@Column(name="PO_ID", nullable=false, precision=4, scale=0) ) } )

    public Idata_ImportDId getId() {
        return this.id;
    }
    
    public void setId(Idata_ImportDId id) {
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
    
    @Column(name="UNIT_COST", precision=16, scale=5)

    public Double getUnitCost() {
        return this.unitCost;
    }
    
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
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
    
    @Column(name="OUT_STOCK_FLAG", nullable=false, length=1)

    public String getOutStockFlag() {
        return this.outStockFlag;
    }
    
    public void setOutStockFlag(String outStockFlag) {
        this.outStockFlag = outStockFlag;
    }
    
    @Column(name="ERROR_STATUS", length=200)

    public String getErrorStatus() {
        return this.errorStatus;
    }
    
    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
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
    
    @Column(name="QC_STATUS", nullable=false, length=2)

    public String getQcStatus() {
        return this.qcStatus;
    }
    
    public void setQcStatus(String qcStatus) {
        this.qcStatus = qcStatus;
    }
    
    @Column(name="QC_FLAG", nullable=false, length=1)

    public String getQcFlag() {
        return this.qcFlag;
    }
    
    public void setQcFlag(String qcFlag) {
        this.qcFlag = qcFlag;
    }
   








}