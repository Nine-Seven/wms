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
 * Ridata_UntreadD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="RIDATA_UNTREAD_D"
)

public class Ridata_UntreadD  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_UntreadDId id;
     private String supplierNo;
     private String articleNo;
     private Double packingQty;
     private Double untreadQty;
     private Double checkQty;
     private Double unitCost;
     private String checkName;
     private Date checkDate;
     private String status;
     private String lotNo;
     private String quality;
     private Date produceDate;
     private Date expireDate;


    // Constructors

    /** default constructor */
    public Ridata_UntreadD() {
    }

	/** minimal constructor */
    public Ridata_UntreadD(Ridata_UntreadDId id, String supplierNo, String articleNo, Double packingQty, Double untreadQty, String status) {
        this.id = id;
        this.supplierNo = supplierNo;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.untreadQty = untreadQty;
        this.status = status;
    }
    
    /** full constructor */
    public Ridata_UntreadD(Ridata_UntreadDId id, String supplierNo, String articleNo, Double packingQty, Double untreadQty, Double checkQty, Double unitCost, String checkName, Date checkDate, String status, String lotNo, String quality, Date produceDate, Date expireDate) {
        this.id = id;
        this.supplierNo = supplierNo;
        this.articleNo = articleNo;
        this.packingQty = packingQty;
        this.untreadQty = untreadQty;
        this.checkQty = checkQty;
        this.unitCost = unitCost;
        this.checkName = checkName;
        this.checkDate = checkDate;
        this.status = status;
        this.lotNo = lotNo;
        this.quality = quality;
        this.produceDate = produceDate;
        this.expireDate = expireDate;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="untreadNo", column=@Column(name="UNTREAD_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="poId", column=@Column(name="PO_ID", nullable=false, precision=4, scale=0) ) } )

    public Ridata_UntreadDId getId() {
        return this.id;
    }
    
    public void setId(Ridata_UntreadDId id) {
        this.id = id;
    }
    
    @Column(name="SUPPLIER_NO", nullable=false, length=10)

    public String getSupplierNo() {
        return this.supplierNo;
    }
    
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }
    
    @Column(name="ARTICLE_NO", nullable=false, length=13)

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
    
    @Column(name="UNTREAD_QTY", nullable=false, precision=18, scale=5)

    public Double getUntreadQty() {
        return this.untreadQty;
    }
    
    public void setUntreadQty(Double untreadQty) {
        this.untreadQty = untreadQty;
    }
    
    @Column(name="CHECK_QTY", precision=18, scale=5)

    public Double getCheckQty() {
        return this.checkQty;
    }
    
    public void setCheckQty(Double checkQty) {
        this.checkQty = checkQty;
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
    @Temporal(TemporalType.DATE)
    @Column(name="CHECK_DATE", length=7)

    public Date getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="LOT_NO", length=32)

    public String getLotNo() {
        return this.lotNo;
    }
    
    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }
    
    @Column(name="QUALITY", length=1)

    public String getQuality() {
        return this.quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="PRODUCE_DATE", length=7)

    public Date getProduceDate() {
        return this.produceDate;
    }
    
    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXPIRE_DATE", length=7)

    public Date getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
   








}