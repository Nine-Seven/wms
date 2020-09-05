package com.sealinkin.ridata.po;
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
 * Ridata_CheckD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="RIDATA_CHECK_D")

public class Ridata_CheckD  implements java.io.Serializable {


    /**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private Ridata_CheckDId id;
     private String articleNo;
     private String barcode;
     private Double packingQty;
     private String lotNo;
     private Date produceDate;
     private Date expireDate;
     private String quality;
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
     private Double checkQty;
     private String checkWorker;
     private Date checkStartDate;
     private Date checkEndDate;
     private String checkType;


    // Constructors

    /** default constructor */
    public Ridata_CheckD() {
    }

	/** minimal constructor */
    public Ridata_CheckD(Ridata_CheckDId id, String articleNo, String barcode, Double packingQty, String lotNo, Date produceDate, Date expireDate, String quality, String stockType, String stockValue, String deptNo, Double checkQty, String checkType) {
        this.id = id;
        this.articleNo = articleNo;
        this.barcode = barcode;
        this.packingQty = packingQty;
        this.lotNo = lotNo;
        this.produceDate = produceDate;
        this.expireDate = expireDate;
        this.quality = quality;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.deptNo = deptNo;
        this.checkQty = checkQty;
        this.checkType = checkType;
    }
    
    /** full constructor */
    public Ridata_CheckD(Ridata_CheckDId id, String articleNo, String barcode, Double packingQty, String lotNo, Date produceDate, Date expireDate, String quality, String rsvBatch1, String rsvBatch2, String rsvBatch3, String rsvBatch4, String rsvBatch5, String rsvBatch6, String rsvBatch7, String rsvBatch8, String stockType, String stockValue, String deptNo, Double checkQty, String checkWorker, Date checkStartDate, Date checkEndDate, String checkType) {
        this.id = id;
        this.articleNo = articleNo;
        this.barcode = barcode;
        this.packingQty = packingQty;
        this.lotNo = lotNo;
        this.produceDate = produceDate;
        this.expireDate = expireDate;
        this.quality = quality;
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
        this.checkQty = checkQty;
        this.checkWorker = checkWorker;
        this.checkStartDate = checkStartDate;
        this.checkEndDate = checkEndDate;
        this.checkType = checkType;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="checkNo", column=@Column(name="CHECK_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="rowId", column=@Column(name="ROW_ID", nullable=false, precision=4, scale=0) ), 
        @AttributeOverride(name="supplierNo", column=@Column(name="SUPPLIER_NO", nullable=false, length=10) ) } )

    public Ridata_CheckDId getId() {
        return this.id;
    }
    
    public void setId(Ridata_CheckDId id) {
        this.id = id;
    }
    
    @Column(name="ARTICLE_NO", nullable=false, length=15)

    public String getArticleNo() {
        return this.articleNo;
    }
    
    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }
    
    @Column(name="BARCODE", nullable=false, length=20)

    public String getBarcode() {
        return this.barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    @Column(name="PACKING_QTY", nullable=false, precision=18, scale=5)

    public Double getPackingQty() {
        return this.packingQty;
    }
    
    public void setPackingQty(Double packingQty) {
        this.packingQty = packingQty;
    }
    
    @Column(name="LOT_NO", nullable=false, length=32)

    public String getLotNo() {
        return this.lotNo;
    }
    
    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="PRODUCE_DATE", nullable=false, length=7)

    public Date getProduceDate() {
        return this.produceDate;
    }
    
    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXPIRE_DATE", nullable=false, length=7)

    public Date getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
    @Column(name="QUALITY", nullable=false, length=1)

    public String getQuality() {
        return this.quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
    }
    
    @Column(name="RSV_BATCH1", length=20)

    public String getRsvBatch1() {
        return this.rsvBatch1;
    }
    
    public void setRsvBatch1(String rsvBatch1) {
        this.rsvBatch1 = rsvBatch1;
    }
    
    @Column(name="RSV_BATCH2", length=20)

    public String getRsvBatch2() {
        return this.rsvBatch2;
    }
    
    public void setRsvBatch2(String rsvBatch2) {
        this.rsvBatch2 = rsvBatch2;
    }
    
    @Column(name="RSV_BATCH3", length=20)

    public String getRsvBatch3() {
        return this.rsvBatch3;
    }
    
    public void setRsvBatch3(String rsvBatch3) {
        this.rsvBatch3 = rsvBatch3;
    }
    
    @Column(name="RSV_BATCH4", length=20)

    public String getRsvBatch4() {
        return this.rsvBatch4;
    }
    
    public void setRsvBatch4(String rsvBatch4) {
        this.rsvBatch4 = rsvBatch4;
    }
    
    @Column(name="RSV_BATCH5", length=20)

    public String getRsvBatch5() {
        return this.rsvBatch5;
    }
    
    public void setRsvBatch5(String rsvBatch5) {
        this.rsvBatch5 = rsvBatch5;
    }
    
    @Column(name="RSV_BATCH6", length=20)

    public String getRsvBatch6() {
        return this.rsvBatch6;
    }
    
    public void setRsvBatch6(String rsvBatch6) {
        this.rsvBatch6 = rsvBatch6;
    }
    
    @Column(name="RSV_BATCH7", length=20)

    public String getRsvBatch7() {
        return this.rsvBatch7;
    }
    
    public void setRsvBatch7(String rsvBatch7) {
        this.rsvBatch7 = rsvBatch7;
    }
    
    @Column(name="RSV_BATCH8", length=20)

    public String getRsvBatch8() {
        return this.rsvBatch8;
    }
    
    public void setRsvBatch8(String rsvBatch8) {
        this.rsvBatch8 = rsvBatch8;
    }
    
    @Column(name="STOCK_TYPE", nullable=false, length=1)

    public String getStockType() {
        return this.stockType;
    }
    
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }
    
    @Column(name="STOCK_VALUE", nullable=false, length=20)

    public String getStockValue() {
        return this.stockValue;
    }
    
    public void setStockValue(String stockValue) {
        this.stockValue = stockValue;
    }
    
    @Column(name="DEPT_NO", nullable=false, length=20)

    public String getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    
    @Column(name="CHECK_QTY", nullable=false, precision=18, scale=5)

    public Double getCheckQty() {
        return this.checkQty;
    }
    
    public void setCheckQty(Double checkQty) {
        this.checkQty = checkQty;
    }
    
    @Column(name="CHECK_WORKER", length=20)

    public String getCheckWorker() {
        return this.checkWorker;
    }
    
    public void setCheckWorker(String checkWorker) {
        this.checkWorker = checkWorker;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CHECK_START_DATE", length=7)

    public Date getCheckStartDate() {
        return this.checkStartDate;
    }
    
    public void setCheckStartDate(Date checkStartDate) {
        this.checkStartDate = checkStartDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CHECK_END_DATE", length=7)

    public Date getCheckEndDate() {
        return this.checkEndDate;
    }
    
    public void setCheckEndDate(Date checkEndDate) {
        this.checkEndDate = checkEndDate;
    }
    
    @Column(name="CHECK_TYPE", nullable=false, length=6)

    public String getCheckType() {
        return this.checkType;
    }
    
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }
   








}