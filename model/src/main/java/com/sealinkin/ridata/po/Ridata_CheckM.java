package com.sealinkin.ridata.po;
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
 * Ridata_CheckM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="RIDATA_CHECK_M")

public class Ridata_CheckM  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private Ridata_CheckMId id;
     private String SCheckNo;
     private String untreadType;
     private String untreadNo;
     private String dockNo;
     private String checkWorker;
     private String status;
     private Date checkStartDate;
     private Date checkEndDate;
     private String printerGroupNo;
     private String checkTools;
     private BigDecimal sendFlag;
     private BigDecimal printTimes;
     private String rgstName;
     private Date rgstDate;
     private String updtName;
     private Date updtDate; 


    // Constructors

    /** default constructor */
    public Ridata_CheckM() {
    }

	/** minimal constructor */
    public Ridata_CheckM(Ridata_CheckMId id, String SCheckNo, String untreadType, String untreadNo, String dockNo, String status, String printerGroupNo, BigDecimal sendFlag, BigDecimal printTimes, String rgstName, Date rgstDate) {
        this.id = id;
        this.SCheckNo = SCheckNo;
        this.untreadType = untreadType;
        this.untreadNo = untreadNo;
        this.dockNo = dockNo;
        this.status = status;
        this.printerGroupNo = printerGroupNo;
        this.sendFlag = sendFlag;
        this.printTimes = printTimes;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
    }
    
    /** full constructor */
    public Ridata_CheckM(Ridata_CheckMId id, String SCheckNo, String untreadType, String untreadNo, String dockNo, String checkWorker, String status, Date checkStartDate, Date checkEndDate, String printerGroupNo, String checkTools, BigDecimal sendFlag, BigDecimal printTimes, String rgstName, Date rgstDate, String updtName, Date updtDate) {
        this.id = id;
        this.SCheckNo = SCheckNo;
        this.untreadType = untreadType;
        this.untreadNo = untreadNo;
        this.dockNo = dockNo;
        this.checkWorker = checkWorker;
        this.status = status;
        this.checkStartDate = checkStartDate;
        this.checkEndDate = checkEndDate;
        this.printerGroupNo = printerGroupNo;
        this.checkTools = checkTools;
        this.sendFlag = sendFlag;
        this.printTimes = printTimes;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.updtName = updtName;
        this.updtDate = updtDate;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="checkNo", column=@Column(name="CHECK_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="ownerNo", column=@Column(name="OWNER_NO", nullable=false, length=3) ) } )

    public Ridata_CheckMId getId() {
        return this.id;
    }
    
    public void setId(Ridata_CheckMId id) {
        this.id = id;
    }
    
    @Column(name="S_CHECK_NO", nullable=false, length=20)

    public String getSCheckNo() {
        return this.SCheckNo;
    }
    
    public void setSCheckNo(String SCheckNo) {
        this.SCheckNo = SCheckNo;
    }
    
    @Column(name="UNTREAD_TYPE", nullable=false, length=2)

    public String getUntreadType() {
        return this.untreadType;
    }
    
    public void setUntreadType(String untreadType) {
        this.untreadType = untreadType;
    }
    
    @Column(name="UNTREAD_NO", nullable=false, length=20)

    public String getUntreadNo() {
        return this.untreadNo;
    }
    
    public void setUntreadNo(String untreadNo) {
        this.untreadNo = untreadNo;
    }
    
    @Column(name="DOCK_NO", nullable=false, length=3)

    public String getDockNo() {
        return this.dockNo;
    }
    
    public void setDockNo(String dockNo) {
        this.dockNo = dockNo;
    }
    
    @Column(name="CHECK_WORKER", length=20)

    public String getCheckWorker() {
        return this.checkWorker;
    }
    
    public void setCheckWorker(String checkWorker) {
        this.checkWorker = checkWorker;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    @Column(name="PRINTER_GROUP_NO", nullable=false, length=5)

    public String getPrinterGroupNo() {
        return this.printerGroupNo;
    }
    
    public void setPrinterGroupNo(String printerGroupNo) {
        this.printerGroupNo = printerGroupNo;
    }
    
    @Column(name="CHECK_TOOLS", length=10)

    public String getCheckTools() {
        return this.checkTools;
    }
    
    public void setCheckTools(String checkTools) {
        this.checkTools = checkTools;
    }
    
    @Column(name="SEND_FLAG", nullable=false, precision=22, scale=0)

    public BigDecimal getSendFlag() {
        return this.sendFlag;
    }
    
    public void setSendFlag(BigDecimal sendFlag) {
        this.sendFlag = sendFlag;
    }
    
    @Column(name="PRINT_TIMES", nullable=false, precision=22, scale=0)

    public BigDecimal getPrintTimes() {
        return this.printTimes;
    }
    
    public void setPrintTimes(BigDecimal printTimes) {
        this.printTimes = printTimes;
    }
    
    @Column(name="RGST_NAME", nullable=false, length=20)

    public String getRgstName() {
        return this.rgstName;
    }
    
    public void setRgstName(String rgstName) {
        this.rgstName = rgstName;
    }
    @Temporal(TemporalType.DATE)
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