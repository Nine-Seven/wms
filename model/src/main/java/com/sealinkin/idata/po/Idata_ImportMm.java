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
 * Idata_ImportMm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="IDATA_IMPORT_MM")

public class Idata_ImportMm  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Idata_ImportMmId id;
     private String importType;
     private String deptNo;
     private String poType;
     private String supplierNo;
     private String status;
     private String serialNo;
     private String stockType;
     private String stockValue;
     private String printerName;
     private Date printerDate;
     private String rgstName;
     private Date rgstDate;
     private String updtName;
     private Date updtDate;


    // Constructors

    /** default constructor */
    public Idata_ImportMm() {
    }

	/** minimal constructor */
    public Idata_ImportMm(Idata_ImportMmId id, String importType, String deptNo, String poType, String supplierNo, String status, String serialNo, String stockType, String stockValue, String rgstName, Date rgstDate) {
        this.id = id;
        this.importType = importType;
        this.deptNo = deptNo;
        this.poType = poType;
        this.supplierNo = supplierNo;
        this.status = status;
        this.serialNo = serialNo;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
    }
    
    /** full constructor */
    public Idata_ImportMm(Idata_ImportMmId id, String importType, String deptNo, String poType, String supplierNo, String status, String serialNo, String stockType, String stockValue, String printerName, Date printerDate, String rgstName, Date rgstDate, String updtName, Date updtDate) {
        this.id = id;
        this.importType = importType;
        this.deptNo = deptNo;
        this.poType = poType;
        this.supplierNo = supplierNo;
        this.status = status;
        this.serialNo = serialNo;
        this.stockType = stockType;
        this.stockValue = stockValue;
        this.printerName = printerName;
        this.printerDate = printerDate;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.updtName = updtName;
        this.updtDate = updtDate;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="SImportNo", column=@Column(name="S_IMPORT_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ) } )

    public Idata_ImportMmId getId() {
        return this.id;
    }
    
    public void setId(Idata_ImportMmId id) {
        this.id = id;
    }
    
    @Column(name="IMPORT_TYPE", nullable=false, length=2)

    public String getImportType() {
        return this.importType;
    }
    
    public void setImportType(String importType) {
        this.importType = importType;
    }
    
    @Column(name="DEPT_NO", nullable=false, length=10)

    public String getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    
    @Column(name="PO_TYPE", nullable=false, length=5)

    public String getPoType() {
        return this.poType;
    }
    
    public void setPoType(String poType) {
        this.poType = poType;
    }
    
    @Column(name="SUPPLIER_NO", nullable=false, length=10)

    public String getSupplierNo() {
        return this.supplierNo;
    }
    
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="SERIAL_NO", nullable=false, length=6)

    public String getSerialNo() {
        return this.serialNo;
    }
    
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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
    
    @Column(name="PRINTER_NAME", length=20)

    public String getPrinterName() {
        return this.printerName;
    }
    
    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="PRINTER_DATE", length=7)

    public Date getPrinterDate() {
        return this.printerDate;
    }
    
    public void setPrinterDate(Date printerDate) {
        this.printerDate = printerDate;
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
    @Temporal(TemporalType.DATE)
    @Column(name="UPDT_DATE", length=7)

    public Date getUpdtDate() {
        return this.updtDate;
    }
    
    public void setUpdtDate(Date updtDate) {
        this.updtDate = updtDate;
    }
   








}