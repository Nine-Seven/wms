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
import javax.persistence.UniqueConstraint;


/**
 * Idata_ImportM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="IDATA_IMPORT_M"
, uniqueConstraints = @UniqueConstraint(columnNames={"WAREHOUSE_NO", "OWNER_NO", "PO_TYPE", "PO_NO"})
)

public class Idata_ImportM  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_ImportMId id;
     private String importType;
     private String poType;
     private String poNo;
     private String supplierNo;
     private Date orderDate;
     private Date requestDate;
     private String status;
     private String createFlag;
     private String importRemark;
     private Short endDate;
     private BigDecimal receiveType;
     private String payTypeName;
     private String orderTypeName;
     private String layType;
     private String classType;
     private String errorStatus;
     private Date orderEndDate;
     private String withCodeFlag;
     private String stockType;
     private String stockValue;
     private String quality;
     private Short sampleRate;
     private String deptNo;
     private String sendFlag;
     private String rgstName;
     private Date rgstDate;
     private String updtName;
     private Date updtDate;
     private String orgNo;
     private String takeType;
     private String rsvVarod1;
 	 private String rsvVarod2;
 	 private String rsvVarod3;
 	 private String rsvVarod4;
 	 private String rsvVarod5;
 	 private String rsvVarod6;
 	 private String rsvVarod7;
 	 private String rsvVarod8;
 	 private Double rsvNum1;
 	 private Double rsvNum2;
 	 private Double rsvNum3;
 	 private Date rsvDate1;
 	 private Date rsvDate2;
 	 private Date rsvDate3;

    // Constructors

    /** default constructor */
    public Idata_ImportM() {
    }

	/** minimal constructor */
    public Idata_ImportM(Idata_ImportMId id, String importType, String poType, String poNo, String supplierNo, Date orderDate, Date requestDate, String status, String createFlag, Short endDate, BigDecimal receiveType, String layType, String classType, Date orderEndDate, String withCodeFlag, String stockType, String stockValue, String deptNo, String sendFlag, String rgstName, Date rgstDate,String orgNo, String takeType) {
        this.id = id;
        this.importType = importType;
        this.poType = poType;
        this.poNo = poNo;
        this.supplierNo = supplierNo;
        this.orderDate = orderDate;
        this.requestDate = requestDate;
        this.status = status;
        this.createFlag = createFlag;
        this.endDate = endDate;
        this.receiveType = receiveType;
        this.layType = layType;
        this.classType = classType;
        this.orderEndDate = orderEndDate;
        this.withCodeFlag = withCodeFlag;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.deptNo = deptNo;
        this.sendFlag = sendFlag;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.orgNo = orgNo;
        this.takeType = takeType;
    }
    
    /** full constructor */
    public Idata_ImportM(Idata_ImportMId id, String importType, String poType, String poNo, String supplierNo, Date orderDate, Date requestDate, String status, String createFlag, String importRemark, Short endDate, BigDecimal receiveType, String payTypeName, String orderTypeName, String layType, String classType, String errorStatus, Date orderEndDate, String withCodeFlag, String stockType, String stockValue, String quality, Short sampleRate, String deptNo, String sendFlag, String rgstName, Date rgstDate, String updtName, Date updtDate,String orgNo,String takeType, String rsvVarod1, String rsvVarod2,
			String rsvVarod3, String rsvVarod4, String rsvVarod5,
			String rsvVarod6, String rsvVarod7, String rsvVarod8,
			Double rsvNum1, Double rsvNum2, Double rsvNum3,
			Date rsvDate1, Date rsvDate2, Date rsvDate3) {
        this.id = id;
        this.importType = importType;
        this.poType = poType;
        this.poNo = poNo;
        this.supplierNo = supplierNo;
        this.orderDate = orderDate;
        this.requestDate = requestDate;
        this.status = status;
        this.createFlag = createFlag;
        this.importRemark = importRemark;
        this.endDate = endDate;
        this.receiveType = receiveType;
        this.payTypeName = payTypeName;
        this.orderTypeName = orderTypeName;
        this.layType = layType;
        this.classType = classType;
        this.errorStatus = errorStatus;
        this.orderEndDate = orderEndDate;
        this.withCodeFlag = withCodeFlag;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.quality = quality;
        this.sampleRate = sampleRate;
        this.deptNo = deptNo;
        this.sendFlag = sendFlag;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.updtName = updtName;
        this.updtDate = updtDate;
        this.orgNo = orgNo;
        this.takeType = takeType;
        this.rsvVarod1 = rsvVarod1;
		this.rsvVarod2 = rsvVarod2;
		this.rsvVarod3 = rsvVarod3;
		this.rsvVarod4 = rsvVarod4;
		this.rsvVarod5 = rsvVarod5;
		this.rsvVarod6 = rsvVarod6;
		this.rsvVarod7 = rsvVarod7;
		this.rsvVarod8 = rsvVarod8;
		this.rsvNum1 = rsvNum1;
		this.rsvNum2 = rsvNum2;
		this.rsvNum3 = rsvNum3;
		this.rsvDate1 = rsvDate1;
		this.rsvDate2 = rsvDate2;
		this.rsvDate3 = rsvDate3;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="importNo", column=@Column(name="IMPORT_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ) } )

    public Idata_ImportMId getId() {
        return this.id;
    }
    
    public void setId(Idata_ImportMId id) {
        this.id = id;
    }
    
    @Column(name="IMPORT_TYPE", nullable=false, length=2)

    public String getImportType() {
        return this.importType;
    }
    
    public void setImportType(String importType) {
        this.importType = importType;
    }
    
    @Column(name="PO_TYPE", nullable=false, length=5)

    public String getPoType() {
        return this.poType;
    }
    
    public void setPoType(String poType) {
        this.poType = poType;
    }
    
    @Column(name="PO_NO", nullable=false, length=20)

    public String getPoNo() {
        return this.poNo;
    }
    
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }
    
    @Column(name="SUPPLIER_NO", nullable=false, length=10)

    public String getSupplierNo() {
        return this.supplierNo;
    }
    
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ORDER_DATE", nullable=false, length=7)

    public Date getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="REQUEST_DATE", nullable=false, length=7)

    public Date getRequestDate() {
        return this.requestDate;
    }
    
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="CREATE_FLAG", nullable=false, length=1)

    public String getCreateFlag() {
        return this.createFlag;
    }
    
    public void setCreateFlag(String createFlag) {
        this.createFlag = createFlag;
    }
    
    @Column(name="IMPORT_REMARK")

    public String getImportRemark() {
        return this.importRemark;
    }
    
    public void setImportRemark(String importRemark) {
        this.importRemark = importRemark;
    }
    
    @Column(name="END_DATE", nullable=false, precision=3, scale=0)

    public Short getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Short endDate) {
        this.endDate = endDate;
    }
    
    @Column(name="RECEIVE_TYPE", nullable=false, precision=22, scale=0)

    public BigDecimal getReceiveType() {
        return this.receiveType;
    }
    
    public void setReceiveType(BigDecimal receiveType) {
        this.receiveType = receiveType;
    }
    
    @Column(name="PAY_TYPE_NAME", length=30)

    public String getPayTypeName() {
        return this.payTypeName;
    }
    
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
    
    @Column(name="ORDER_TYPE_NAME", length=48)

    public String getOrderTypeName() {
        return this.orderTypeName;
    }
    
    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }
    
    @Column(name="LAY_TYPE", nullable=false, length=1)

    public String getLayType() {
        return this.layType;
    }
    
    public void setLayType(String layType) {
        this.layType = layType;
    }
    
    @Column(name="CLASS_TYPE", nullable=false, length=1)
    public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

  
    @Column(name="ERROR_STATUS", length=200)

    public String getErrorStatus() {
        return this.errorStatus;
    }
    
   
	public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ORDER_END_DATE", nullable=false, length=7)

    public Date getOrderEndDate() {
        return this.orderEndDate;
    }
    
    public void setOrderEndDate(Date orderEndDate) {
        this.orderEndDate = orderEndDate;
    }
    
    @Column(name="WITH_CODE_FLAG", nullable=false, length=1)

    public String getWithCodeFlag() {
        return this.withCodeFlag;
    }
    
    public void setWithCodeFlag(String withCodeFlag) {
        this.withCodeFlag = withCodeFlag;
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
    
    @Column(name="QUALITY", length=1)

    public String getQuality() {
        return this.quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
    }
    
    @Column(name="SAMPLE_RATE", precision=3, scale=0)

    public Short getSampleRate() {
        return this.sampleRate;
    }
    
    public void setSampleRate(Short sampleRate) {
        this.sampleRate = sampleRate;
    }
    
    @Column(name="DEPT_NO", nullable=false, length=10)

    public String getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    
    @Column(name="SEND_FLAG", nullable=false, length=2)

    public String getSendFlag() {
        return this.sendFlag;
    }
    
    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }
    
    @Column(name="RGST_NAME", nullable=false, length=20)

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
    
    @Column(name="UPDT_NAME", length=20)

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

    @Column(name="ORG_NO", length=20)
	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	@Column(name = "TAKE_TYPE", nullable = false, length = 1)
	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}
	@Column(name = "RSV_VAROD1", length = 50)
	public String getRsvVarod1() {
		return this.rsvVarod1;
	}

	public void setRsvVarod1(String rsvVarod1) {
		this.rsvVarod1 = rsvVarod1;
	}

	@Column(name = "RSV_VAROD2", length = 50)
	public String getRsvVarod2() {
		return this.rsvVarod2;
	}

	public void setRsvVarod2(String rsvVarod2) {
		this.rsvVarod2 = rsvVarod2;
	}

	@Column(name = "RSV_VAROD3", length = 50)
	public String getRsvVarod3() {
		return this.rsvVarod3;
	}

	public void setRsvVarod3(String rsvVarod3) {
		this.rsvVarod3 = rsvVarod3;
	}

	@Column(name = "RSV_VAROD4", length = 50)
	public String getRsvVarod4() {
		return this.rsvVarod4;
	}

	public void setRsvVarod4(String rsvVarod4) {
		this.rsvVarod4 = rsvVarod4;
	}

	@Column(name = "RSV_VAROD5", length = 50)
	public String getRsvVarod5() {
		return this.rsvVarod5;
	}

	public void setRsvVarod5(String rsvVarod5) {
		this.rsvVarod5 = rsvVarod5;
	}

	@Column(name = "RSV_VAROD6", length = 50)
	public String getRsvVarod6() {
		return this.rsvVarod6;
	}

	public void setRsvVarod6(String rsvVarod6) {
		this.rsvVarod6 = rsvVarod6;
	}

	@Column(name = "RSV_VAROD7", length = 50)
	public String getRsvVarod7() {
		return this.rsvVarod7;
	}

	public void setRsvVarod7(String rsvVarod7) {
		this.rsvVarod7 = rsvVarod7;
	}

	@Column(name = "RSV_VAROD8", length = 50)
	public String getRsvVarod8() {
		return this.rsvVarod8;
	}

	public void setRsvVarod8(String rsvVarod8) {
		this.rsvVarod8 = rsvVarod8;
	}

	@Column(name = "RSV_NUM1", precision = 22, scale = 0)
	public Double getRsvNum1() {
		return this.rsvNum1;
	}

	public void setRsvNum1(Double rsvNum1) {
		this.rsvNum1 = rsvNum1;
	}

	@Column(name = "RSV_NUM2", precision = 22, scale = 0)
	public Double getRsvNum2() {
		return this.rsvNum2;
	}

	public void setRsvNum2(Double rsvNum2) {
		this.rsvNum2 = rsvNum2;
	}

	@Column(name = "RSV_NUM3", precision = 22, scale = 0)
	public Double getRsvNum3() {
		return this.rsvNum3;
	}

	public void setRsvNum3(Double rsvNum3) {
		this.rsvNum3 = rsvNum3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RSV_DATE1", length = 7)
	public Date getRsvDate1() {
		return this.rsvDate1;
	}

	public void setRsvDate1(Date rsvDate1) {
		this.rsvDate1 = rsvDate1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RSV_DATE2", length = 7)
	public Date getRsvDate2() {
		return this.rsvDate2;
	}

	public void setRsvDate2(Date rsvDate2) {
		this.rsvDate2 = rsvDate2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RSV_DATE3", length = 7)
	public Date getRsvDate3() {
		return this.rsvDate3;
	}

	public void setRsvDate3(Date rsvDate3) {
		this.rsvDate3 = rsvDate3;
	}

}