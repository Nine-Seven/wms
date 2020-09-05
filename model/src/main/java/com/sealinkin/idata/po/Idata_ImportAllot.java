package com.sealinkin.idata.po;
// default package

import java.math.BigDecimal;
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
 * Idata_ImportAllot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="IDATA_IMPORT_ALLOT")

public class Idata_ImportAllot  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_ImportAllotId id;
     private Double poQty;
     private Double allotQty;
     private String status;
     private String rgstName;
     private Date rgstDate;
     private String updtName;
     private Date updtDate;
     private BigDecimal sendFlag;
     private String takeType;

    // Constructors

    /** default constructor */
    public Idata_ImportAllot() {
    }

	/** minimal constructor */
    public Idata_ImportAllot(Idata_ImportAllotId id, Double poQty, Double allotQty, String status, String rgstName, Date rgstDate,String takeType) {
        this.id = id;
        this.poQty = poQty;
        this.allotQty = allotQty;
        this.status = status;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.takeType = takeType;
    }
    
    /** full constructor */
    public Idata_ImportAllot(Idata_ImportAllotId id, Double poQty, Double allotQty, String status, String rgstName, Date rgstDate, String updtName, Date updtDate, BigDecimal sendFlag,String takeType) {
        this.id = id;
        this.poQty = poQty;
        this.allotQty = allotQty;
        this.status = status;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.updtName = updtName;
        this.updtDate = updtDate;
        this.sendFlag = sendFlag;
        this.takeType = takeType;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="importNo", column=@Column(name="IMPORT_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ), 
        @AttributeOverride(name="articleNo", column=@Column(name="ARTICLE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="packingQty", column=@Column(name="PACKING_QTY", nullable=false, precision=18, scale=5) ), 
        @AttributeOverride(name="subCustNo", column=@Column(name="SUB_CUST_NO", nullable=false, length=10) ), 
        @AttributeOverride(name="custNo", column=@Column(name="CUST_NO", nullable=false, length=10) ), 
        @AttributeOverride(name="poNo", column=@Column(name="PO_NO", nullable=false, length=50) ) } )

    public Idata_ImportAllotId getId() {
        return this.id;
    }
    
    public void setId(Idata_ImportAllotId id) {
        this.id = id;
    }
    
    @Column(name="PO_QTY", nullable=false, precision=18, scale=5)

    public Double getPoQty() {
        return this.poQty;
    }
    
    public void setPoQty(Double poQty) {
        this.poQty = poQty;
    }
    
    @Column(name="ALLOT_QTY", nullable=false, precision=18, scale=5)

    public Double getAllotQty() {
        return this.allotQty;
    }
    
    public void setAllotQty(Double allotQty) {
        this.allotQty = allotQty;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="RGST_NAME", nullable=false, length=10)

    public String getRgstName() {
        return this.rgstName;
    }
    
    public void setRgstName(String rgstName) {
        this.rgstName = rgstName;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RGST_DATE", nullable=false, length=7)

    public Date getRgstDate() {
        return this.rgstDate;
    }
    
    public void setRgstDate(Date rgstDate) {
        this.rgstDate = rgstDate;
    }
    
    @Column(name="UPDT_NAME", length=10)

    public String getUpdtName() {
        return this.updtName;
    }
    
    public void setUpdtName(String updtName) {
        this.updtName = updtName;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDT_DATE", length=7)

    public Date getUpdtDate() {
        return this.updtDate;
    }
    
    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }
    
    @Column(name="SEND_FLAG", precision=22, scale=0)

    public BigDecimal getSendFlag() {
        return this.sendFlag;
    }
    
    public void setSendFlag(BigDecimal sendFlag) {
        this.sendFlag = sendFlag;
    }

    @Column(name="TAKE_TYPE", nullable=false, length=1)
	public String getTakeType() {
		return takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}
   








}