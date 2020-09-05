package com.sealinkin.odata.po;
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
 * Odata_ExpM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ODATA_EXP_M")

public class Odata_ExpM  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -2580090237123998041L;
	private Odata_ExpMId id;
     private String ownerNo;
     private String ownerCustNo;
     private String custNo;
     private String subCustNo;
     private String sourceexpType;
     private String sourceexpNo;
     private Date expDate;
     private String fastFlag;
     private String status;
     private Short priority;
     private String addExpNo;
     private String importNo;
     private String deliverType;
     private String transportType;
     private String batchNo;
     private String lineNo;
     private String fullLineName;
     private String custAddress;
     private String custAddressCode;
     private String contactorName;
     private String custPhone;
     private String custMail;
     private String errorStatus;
     private String createFlag;
     private String returnFlag;
     private String expRemark;
     private String belongFlag;
     private String sendFlag;
     private String bufferLineNo;
     private String specialArticleGroup;
     private String expStatus;
     private String stockType;
     private Byte orderPeriod;
     private String financeType;
     private String kickFlag;
     private String realCustNo;
     private String realCustName;
     private String deptName;
     private String agentNo;
     private String paymentTerm;
     private String deptNo;
     private String custExpNo;
     private Date erpoperateDate;
     private Date custsendDate;
     private String printFlag;
     private String erpNo;
     private String diffReason;
     private String rgstName;
     private Date rgstDate;
     private String updtName;
     private Date updtDate;
     private String waveNo;
     private String shipperNo;
     private String shipperDeliverNo;
     private String deliverAddress;
     private String printBillFlag;
     private String orderSource;
     private String orgNo;
     private String rsvVarod1;
     private String rsvVarod2;
     private String rsvVarod3;
     private String rsvVarod4;
     private String rsvVarod5;
     private String rsvVarod6;
     private String rsvVarod7;
     private String rsvVarod8;
     private BigDecimal rsvNum1;
     private BigDecimal rsvNum2;
     private BigDecimal rsvNum3;
     private Date rsvDate1;
     private Date rsvDate2;
     private Date rsvDate3;
     private String takeType;
     private Date lastCustsendDate;
     private Long reportUpSerial;
     private String shopNo;
     private BigDecimal skucount;
     private String sendAddress;
     private String sendAddressCode;
     private String sendName;
     private String sendCompanyName;
     private String sendPostcode;
     private String sendMobilePhone;
     private String sendTelephone;
     private String sendJpn;
     private String sendProvince;
     private String sendCity;
     private String sendZone;
     private String sendCountry;
     private String receiveCompanyName;
     private String receiveTelephone;
     private String receiveJpn;
     private String receiveProvince;
     private String receiveCity;
     private String receiveZone;
     private String receiveCountry;
     private String takeAddress;
     private String takeAddressCode;
     private String takeName;
     private String takeCompanyName;
     private String takePostcode;
     private String takeMobilePhone;
     private String takeTelephone;
     private String takeJpn;
     private String takeProvince;
     private String takeCity;
     private String takeZone;
     private String takeCountry;


    // Constructors

    /** default constructor */
    public Odata_ExpM() {
    }

	/** minimal constructor */
    public Odata_ExpM(Odata_ExpMId id, String ownerNo, String ownerCustNo, String custNo, String subCustNo, String sourceexpType, String sourceexpNo, Date expDate, String fastFlag, String status, Short priority, String addExpNo, String deliverType, String transportType, String createFlag, String returnFlag, String belongFlag, String sendFlag, String specialArticleGroup, String rgstName, Date rgstDate, String orgNo, String takeType) {
        this.id = id;
        this.ownerNo = ownerNo;
        this.ownerCustNo = ownerCustNo;
        this.custNo = custNo;
        this.subCustNo = subCustNo;
        this.sourceexpType = sourceexpType;
        this.sourceexpNo = sourceexpNo;
        this.expDate = expDate;
        this.fastFlag = fastFlag;
        this.status = status;
        this.priority = priority;
        this.addExpNo = addExpNo;
        this.deliverType = deliverType;
        this.transportType = transportType;
        this.createFlag = createFlag;
        this.returnFlag = returnFlag;
        this.belongFlag = belongFlag;
        this.sendFlag = sendFlag;
        this.specialArticleGroup = specialArticleGroup;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.orgNo = orgNo;
        this.takeType = takeType;
    }
    
    /** full constructor */
    public Odata_ExpM(Odata_ExpMId id, String ownerNo, String ownerCustNo, String custNo, String subCustNo, String sourceexpType, String sourceexpNo, Date expDate, String fastFlag, String status, Short priority, String addExpNo, String importNo, String deliverType, String transportType, String batchNo, String lineNo, String fullLineName, String custAddress, String custAddressCode, String contactorName, String custPhone, String custMail, String errorStatus, String createFlag, String returnFlag, String expRemark, String belongFlag, String sendFlag, String bufferLineNo, String specialArticleGroup, String expStatus, String stockType, Byte orderPeriod, String financeType, String kickFlag, String realCustNo, String realCustName, String deptName, String agentNo, String paymentTerm, String deptNo, String custExpNo, Date erpoperateDate, Date custsendDate, String printFlag, String erpNo, String diffReason, String rgstName, Date rgstDate, String updtName, Date updtDate, String waveNo, String shipperNo, String shipperDeliverNo, String deliverAddress, String printBillFlag, String orderSource, String orgNo, String rsvVarod1, String rsvVarod2, String rsvVarod3, String rsvVarod4, String rsvVarod5, String rsvVarod6, String rsvVarod7, String rsvVarod8, BigDecimal rsvNum1, BigDecimal rsvNum2, BigDecimal rsvNum3, Date rsvDate1, Date rsvDate2, Date rsvDate3, String takeType, Date lastCustsendDate, Long reportUpSerial, String shopNo, BigDecimal skucount, String sendAddress, String sendAddressCode, String sendName, String sendCompanyName, String sendPostcode, String sendMobilePhone, String sendTelephone, String sendJpn, String sendProvince, String sendCity, String sendZone, String sendCountry, String receiveCompanyName, String receiveTelephone, String receiveJpn, String receiveProvince, String receiveCity, String receiveZone, String receiveCountry, String takeAddress, String takeAddressCode, String takeName, String takeCompanyName, String takePostcode, String takeMobilePhone, String takeTelephone, String takeJpn, String takeProvince, String takeCity, String takeZone, String takeCountry) {
        this.id = id;
        this.ownerNo = ownerNo;
        this.ownerCustNo = ownerCustNo;
        this.custNo = custNo;
        this.subCustNo = subCustNo;
        this.sourceexpType = sourceexpType;
        this.sourceexpNo = sourceexpNo;
        this.expDate = expDate;
        this.fastFlag = fastFlag;
        this.status = status;
        this.priority = priority;
        this.addExpNo = addExpNo;
        this.importNo = importNo;
        this.deliverType = deliverType;
        this.transportType = transportType;
        this.batchNo = batchNo;
        this.lineNo = lineNo;
        this.fullLineName = fullLineName;
        this.custAddress = custAddress;
        this.custAddressCode = custAddressCode;
        this.contactorName = contactorName;
        this.custPhone = custPhone;
        this.custMail = custMail;
        this.errorStatus = errorStatus;
        this.createFlag = createFlag;
        this.returnFlag = returnFlag;
        this.expRemark = expRemark;
        this.belongFlag = belongFlag;
        this.sendFlag = sendFlag;
        this.bufferLineNo = bufferLineNo;
        this.specialArticleGroup = specialArticleGroup;
        this.expStatus = expStatus;
        this.stockType = stockType;
        this.orderPeriod = orderPeriod;
        this.financeType = financeType;
        this.kickFlag = kickFlag;
        this.realCustNo = realCustNo;
        this.realCustName = realCustName;
        this.deptName = deptName;
        this.agentNo = agentNo;
        this.paymentTerm = paymentTerm;
        this.deptNo = deptNo;
        this.custExpNo = custExpNo;
        this.erpoperateDate = erpoperateDate;
        this.custsendDate = custsendDate;
        this.printFlag = printFlag;
        this.erpNo = erpNo;
        this.diffReason = diffReason;
        this.rgstName = rgstName;
        this.rgstDate = rgstDate;
        this.updtName = updtName;
        this.updtDate = updtDate;
        this.waveNo = waveNo;
        this.shipperNo = shipperNo;
        this.shipperDeliverNo = shipperDeliverNo;
        this.deliverAddress = deliverAddress;
        this.printBillFlag = printBillFlag;
        this.orderSource = orderSource;
        this.orgNo = orgNo;
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
        this.takeType = takeType;
        this.lastCustsendDate = lastCustsendDate;
        this.reportUpSerial = reportUpSerial;
        this.shopNo = shopNo;
        this.skucount = skucount;
        this.sendAddress = sendAddress;
        this.sendAddressCode = sendAddressCode;
        this.sendName = sendName;
        this.sendCompanyName = sendCompanyName;
        this.sendPostcode = sendPostcode;
        this.sendMobilePhone = sendMobilePhone;
        this.sendTelephone = sendTelephone;
        this.sendJpn = sendJpn;
        this.sendProvince = sendProvince;
        this.sendCity = sendCity;
        this.sendZone = sendZone;
        this.sendCountry = sendCountry;
        this.receiveCompanyName = receiveCompanyName;
        this.receiveTelephone = receiveTelephone;
        this.receiveJpn = receiveJpn;
        this.receiveProvince = receiveProvince;
        this.receiveCity = receiveCity;
        this.receiveZone = receiveZone;
        this.receiveCountry = receiveCountry;
        this.takeAddress = takeAddress;
        this.takeAddressCode = takeAddressCode;
        this.takeName = takeName;
        this.takeCompanyName = takeCompanyName;
        this.takePostcode = takePostcode;
        this.takeMobilePhone = takeMobilePhone;
        this.takeTelephone = takeTelephone;
        this.takeJpn = takeJpn;
        this.takeProvince = takeProvince;
        this.takeCity = takeCity;
        this.takeZone = takeZone;
        this.takeCountry = takeCountry;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="enterpriseNo", column=@Column(name="ENTERPRISE_NO", nullable=false, length=15) ), 
        @AttributeOverride(name="warehouseNo", column=@Column(name="WAREHOUSE_NO", nullable=false, length=5) ), 
        @AttributeOverride(name="expNo", column=@Column(name="EXP_NO", nullable=false, length=20) ), 
        @AttributeOverride(name="expType", column=@Column(name="EXP_TYPE", nullable=false, length=5) ) } )

    public Odata_ExpMId getId() {
        return this.id;
    }
    
    public void setId(Odata_ExpMId id) {
        this.id = id;
    }
    
    @Column(name="OWNER_NO", nullable=false, length=3)

    public String getOwnerNo() {
        return this.ownerNo;
    }
    
    public void setOwnerNo(String ownerNo) {
        this.ownerNo = ownerNo;
    }
    
    @Column(name="OWNER_CUST_NO", nullable=false, length=20)

    public String getOwnerCustNo() {
        return this.ownerCustNo;
    }
    
    public void setOwnerCustNo(String ownerCustNo) {
        this.ownerCustNo = ownerCustNo;
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
    
    @Column(name="SOURCEEXP_TYPE", nullable=false, length=5)

    public String getSourceexpType() {
        return this.sourceexpType;
    }
    
    public void setSourceexpType(String sourceexpType) {
        this.sourceexpType = sourceexpType;
    }
    
    @Column(name="SOURCEEXP_NO", nullable=false, length=30)

    public String getSourceexpNo() {
        return this.sourceexpNo;
    }
    
    public void setSourceexpNo(String sourceexpNo) {
        this.sourceexpNo = sourceexpNo;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EXP_DATE", nullable=false, length=7)

    public Date getExpDate() {
        return this.expDate;
    }
    
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    
    @Column(name="FAST_FLAG", nullable=false, length=1)

    public String getFastFlag() {
        return this.fastFlag;
    }
    
    public void setFastFlag(String fastFlag) {
        this.fastFlag = fastFlag;
    }
    
    @Column(name="STATUS", nullable=false, length=2)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="PRIORITY", nullable=false, precision=3, scale=0)

    public Short getPriority() {
        return this.priority;
    }
    
    public void setPriority(Short priority) {
        this.priority = priority;
    }
    
    @Column(name="ADD_EXP_NO", nullable=false, length=20)

    public String getAddExpNo() {
        return this.addExpNo;
    }
    
    public void setAddExpNo(String addExpNo) {
        this.addExpNo = addExpNo;
    }
    
    @Column(name="IMPORT_NO", length=20)

    public String getImportNo() {
        return this.importNo;
    }
    
    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }
    
    @Column(name="DELIVER_TYPE", nullable=false, length=1)

    public String getDeliverType() {
        return this.deliverType;
    }
    
    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }
    
    @Column(name="TRANSPORT_TYPE", nullable=false, length=1)

    public String getTransportType() {
        return this.transportType;
    }
    
    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }
    
    @Column(name="BATCH_NO", length=2)

    public String getBatchNo() {
        return this.batchNo;
    }
    
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    
    @Column(name="LINE_NO", length=4)

    public String getLineNo() {
        return this.lineNo;
    }
    
    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }
    
    @Column(name="FULL_LINE_NAME", length=50)

    public String getFullLineName() {
        return this.fullLineName;
    }
    
    public void setFullLineName(String fullLineName) {
        this.fullLineName = fullLineName;
    }
    
    @Column(name="CUST_ADDRESS", length=100)

    public String getCustAddress() {
        return this.custAddress;
    }
    
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
    
    @Column(name="CUST_ADDRESS_CODE", length=20)

    public String getCustAddressCode() {
        return this.custAddressCode;
    }
    
    public void setCustAddressCode(String custAddressCode) {
        this.custAddressCode = custAddressCode;
    }
    
    @Column(name="CONTACTOR_NAME", length=50)

    public String getContactorName() {
        return this.contactorName;
    }
    
    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }
    
    @Column(name="CUST_PHONE", length=50)

    public String getCustPhone() {
        return this.custPhone;
    }
    
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
    
    @Column(name="CUST_MAIL", length=50)

    public String getCustMail() {
        return this.custMail;
    }
    
    public void setCustMail(String custMail) {
        this.custMail = custMail;
    }
    
    @Column(name="ERROR_STATUS", length=3)

    public String getErrorStatus() {
        return this.errorStatus;
    }
    
    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }
    
    @Column(name="CREATE_FLAG", nullable=false, length=1)

    public String getCreateFlag() {
        return this.createFlag;
    }
    
    public void setCreateFlag(String createFlag) {
        this.createFlag = createFlag;
    }
    
    @Column(name="RETURN_FLAG", nullable=false, length=1)

    public String getReturnFlag() {
        return this.returnFlag;
    }
    
    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }
    
    @Column(name="EXP_REMARK", length=100)

    public String getExpRemark() {
        return this.expRemark;
    }
    
    public void setExpRemark(String expRemark) {
        this.expRemark = expRemark;
    }
    
    @Column(name="BELONG_FLAG", nullable=false, length=1)

    public String getBelongFlag() {
        return this.belongFlag;
    }
    
    public void setBelongFlag(String belongFlag) {
        this.belongFlag = belongFlag;
    }
    
    @Column(name="SEND_FLAG", nullable=false, length=2)

    public String getSendFlag() {
        return this.sendFlag;
    }
    
    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }
    
    @Column(name="BUFFER_LINE_NO", length=4)

    public String getBufferLineNo() {
        return this.bufferLineNo;
    }
    
    public void setBufferLineNo(String bufferLineNo) {
        this.bufferLineNo = bufferLineNo;
    }
    
    @Column(name="SPECIAL_ARTICLE_GROUP", nullable=false, length=1)

    public String getSpecialArticleGroup() {
        return this.specialArticleGroup;
    }
    
    public void setSpecialArticleGroup(String specialArticleGroup) {
        this.specialArticleGroup = specialArticleGroup;
    }
    
    @Column(name="EXP_STATUS", length=2)

    public String getExpStatus() {
        return this.expStatus;
    }
    
    public void setExpStatus(String expStatus) {
        this.expStatus = expStatus;
    }
    
    @Column(name="STOCK_TYPE", length=1)

    public String getStockType() {
        return this.stockType;
    }
    
    public void setStockType(String stockType) {
        this.stockType = stockType;
    }
    
    @Column(name="ORDER_PERIOD", precision=2, scale=0)

    public Byte getOrderPeriod() {
        return this.orderPeriod;
    }
    
    public void setOrderPeriod(Byte orderPeriod) {
        this.orderPeriod = orderPeriod;
    }
    
    @Column(name="FINANCE_TYPE", length=100)

    public String getFinanceType() {
        return this.financeType;
    }
    
    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }
    
    @Column(name="KICK_FLAG", length=1)

    public String getKickFlag() {
        return this.kickFlag;
    }
    
    public void setKickFlag(String kickFlag) {
        this.kickFlag = kickFlag;
    }
    
    @Column(name="REAL_CUST_NO", length=20)

    public String getRealCustNo() {
        return this.realCustNo;
    }
    
    public void setRealCustNo(String realCustNo) {
        this.realCustNo = realCustNo;
    }
    
    @Column(name="REAL_CUST_NAME", length=20)

    public String getRealCustName() {
        return this.realCustName;
    }
    
    public void setRealCustName(String realCustName) {
        this.realCustName = realCustName;
    }
    
    @Column(name="DEPT_NAME", length=30)

    public String getDeptName() {
        return this.deptName;
    }
    
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    @Column(name="AGENT_NO", length=20)

    public String getAgentNo() {
        return this.agentNo;
    }
    
    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }
    
    @Column(name="PAYMENT_TERM", length=30)

    public String getPaymentTerm() {
        return this.paymentTerm;
    }
    
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
    
    @Column(name="DEPT_NO", length=10)

    public String getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
    
    @Column(name="CUST_EXP_NO", length=20)

    public String getCustExpNo() {
        return this.custExpNo;
    }
    
    public void setCustExpNo(String custExpNo) {
        this.custExpNo = custExpNo;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ERPOPERATE_DATE", length=7)

    public Date getErpoperateDate() {
        return this.erpoperateDate;
    }
    
    public void setErpoperateDate(Date erpoperateDate) {
        this.erpoperateDate = erpoperateDate;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CUSTSEND_DATE", length=7)

    public Date getCustsendDate() {
        return this.custsendDate;
    }
    
    public void setCustsendDate(Date custsendDate) {
        this.custsendDate = custsendDate;
    }
    
    @Column(name="PRINT_FLAG", length=1)

    public String getPrintFlag() {
        return this.printFlag;
    }
    
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }
    
    @Column(name="ERP_NO", length=20)

    public String getErpNo() {
        return this.erpNo;
    }
    
    public void setErpNo(String erpNo) {
        this.erpNo = erpNo;
    }
    
    @Column(name="DIFF_REASON", length=10)

    public String getDiffReason() {
        return this.diffReason;
    }
    
    public void setDiffReason(String diffReason) {
        this.diffReason = diffReason;
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
    
    @Column(name="WAVE_NO", length=20)

    public String getWaveNo() {
        return this.waveNo;
    }
    
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }
    
    @Column(name="SHIPPER_NO", length=15)

    public String getShipperNo() {
        return this.shipperNo;
    }
    
    public void setShipperNo(String shipperNo) {
        this.shipperNo = shipperNo;
    }
    
    @Column(name="SHIPPER_DELIVER_NO", length=30)

    public String getShipperDeliverNo() {
        return this.shipperDeliverNo;
    }
    
    public void setShipperDeliverNo(String shipperDeliverNo) {
        this.shipperDeliverNo = shipperDeliverNo;
    }
    
    @Column(name="DELIVER_ADDRESS", length=50)

    public String getDeliverAddress() {
        return this.deliverAddress;
    }
    
    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }
    
    @Column(name="PRINT_BILL_FLAG", length=1)

    public String getPrintBillFlag() {
        return this.printBillFlag;
    }
    
    public void setPrintBillFlag(String printBillFlag) {
        this.printBillFlag = printBillFlag;
    }
    
    @Column(name="ORDER_SOURCE", length=2)

    public String getOrderSource() {
        return this.orderSource;
    }
    
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }
    
    @Column(name="ORG_NO", nullable=false, length=20)

    public String getOrgNo() {
        return this.orgNo;
    }
    
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    
    @Column(name="RSV_VAROD1", length=50)

    public String getRsvVarod1() {
        return this.rsvVarod1;
    }
    
    public void setRsvVarod1(String rsvVarod1) {
        this.rsvVarod1 = rsvVarod1;
    }
    
    @Column(name="RSV_VAROD2", length=50)

    public String getRsvVarod2() {
        return this.rsvVarod2;
    }
    
    public void setRsvVarod2(String rsvVarod2) {
        this.rsvVarod2 = rsvVarod2;
    }
    
    @Column(name="RSV_VAROD3", length=50)

    public String getRsvVarod3() {
        return this.rsvVarod3;
    }
    
    public void setRsvVarod3(String rsvVarod3) {
        this.rsvVarod3 = rsvVarod3;
    }
    
    @Column(name="RSV_VAROD4", length=50)

    public String getRsvVarod4() {
        return this.rsvVarod4;
    }
    
    public void setRsvVarod4(String rsvVarod4) {
        this.rsvVarod4 = rsvVarod4;
    }
    
    @Column(name="RSV_VAROD5", length=50)

    public String getRsvVarod5() {
        return this.rsvVarod5;
    }
    
    public void setRsvVarod5(String rsvVarod5) {
        this.rsvVarod5 = rsvVarod5;
    }
    
    @Column(name="RSV_VAROD6", length=50)

    public String getRsvVarod6() {
        return this.rsvVarod6;
    }
    
    public void setRsvVarod6(String rsvVarod6) {
        this.rsvVarod6 = rsvVarod6;
    }
    
    @Column(name="RSV_VAROD7", length=50)

    public String getRsvVarod7() {
        return this.rsvVarod7;
    }
    
    public void setRsvVarod7(String rsvVarod7) {
        this.rsvVarod7 = rsvVarod7;
    }
    
    @Column(name="RSV_VAROD8", length=50)

    public String getRsvVarod8() {
        return this.rsvVarod8;
    }
    
    public void setRsvVarod8(String rsvVarod8) {
        this.rsvVarod8 = rsvVarod8;
    }
    
    @Column(name="RSV_NUM1", precision=22, scale=0)

    public BigDecimal getRsvNum1() {
        return this.rsvNum1;
    }
    
    public void setRsvNum1(BigDecimal rsvNum1) {
        this.rsvNum1 = rsvNum1;
    }
    
    @Column(name="RSV_NUM2", precision=22, scale=0)

    public BigDecimal getRsvNum2() {
        return this.rsvNum2;
    }
    
    public void setRsvNum2(BigDecimal rsvNum2) {
        this.rsvNum2 = rsvNum2;
    }
    
    @Column(name="RSV_NUM3", precision=22, scale=0)

    public BigDecimal getRsvNum3() {
        return this.rsvNum3;
    }
    
    public void setRsvNum3(BigDecimal rsvNum3) {
        this.rsvNum3 = rsvNum3;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RSV_DATE1", length=7)

    public Date getRsvDate1() {
        return this.rsvDate1;
    }
    
    public void setRsvDate1(Date rsvDate1) {
        this.rsvDate1 = rsvDate1;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RSV_DATE2", length=7)

    public Date getRsvDate2() {
        return this.rsvDate2;
    }
    
    public void setRsvDate2(Date rsvDate2) {
        this.rsvDate2 = rsvDate2;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="RSV_DATE3", length=7)

    public Date getRsvDate3() {
        return this.rsvDate3;
    }
    
    public void setRsvDate3(Date rsvDate3) {
        this.rsvDate3 = rsvDate3;
    }
    
    @Column(name="TAKE_TYPE", nullable=false, length=1)

    public String getTakeType() {
        return this.takeType;
    }
    
    public void setTakeType(String takeType) {
        this.takeType = takeType;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_CUSTSEND_DATE", length=7)

    public Date getLastCustsendDate() {
        return this.lastCustsendDate;
    }
    
    public void setLastCustsendDate(Date lastCustsendDate) {
        this.lastCustsendDate = lastCustsendDate;
    }
    
    @Column(name="REPORT_UP_SERIAL", precision=18, scale=0)

    public Long getReportUpSerial() {
        return this.reportUpSerial;
    }
    
    public void setReportUpSerial(Long reportUpSerial) {
        this.reportUpSerial = reportUpSerial;
    }
    
    @Column(name="SHOP_NO", length=20)

    public String getShopNo() {
        return this.shopNo;
    }
    
    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }
    
    @Column(name="SKUCOUNT", precision=22, scale=0)

    public BigDecimal getSkucount() {
        return this.skucount;
    }
    
    public void setSkucount(BigDecimal skucount) {
        this.skucount = skucount;
    }
    
    @Column(name="SEND_ADDRESS", length=100)

    public String getSendAddress() {
        return this.sendAddress;
    }
    
    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }
    
    @Column(name="SEND_ADDRESS_CODE", length=20)

    public String getSendAddressCode() {
        return this.sendAddressCode;
    }
    
    public void setSendAddressCode(String sendAddressCode) {
        this.sendAddressCode = sendAddressCode;
    }
    
    @Column(name="SEND_NAME", length=50)

    public String getSendName() {
        return this.sendName;
    }
    
    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
    
    @Column(name="SEND_COMPANY_NAME", length=100)

    public String getSendCompanyName() {
        return this.sendCompanyName;
    }
    
    public void setSendCompanyName(String sendCompanyName) {
        this.sendCompanyName = sendCompanyName;
    }
    
    @Column(name="SEND_POSTCODE", length=20)

    public String getSendPostcode() {
        return this.sendPostcode;
    }
    
    public void setSendPostcode(String sendPostcode) {
        this.sendPostcode = sendPostcode;
    }
    
    @Column(name="SEND_MOBILE_PHONE", length=20)

    public String getSendMobilePhone() {
        return this.sendMobilePhone;
    }
    
    public void setSendMobilePhone(String sendMobilePhone) {
        this.sendMobilePhone = sendMobilePhone;
    }
    
    @Column(name="SEND_TELEPHONE", length=20)

    public String getSendTelephone() {
        return this.sendTelephone;
    }
    
    public void setSendTelephone(String sendTelephone) {
        this.sendTelephone = sendTelephone;
    }
    
    @Column(name="SEND_JPN", length=10)

    public String getSendJpn() {
        return this.sendJpn;
    }
    
    public void setSendJpn(String sendJpn) {
        this.sendJpn = sendJpn;
    }
    
    @Column(name="SEND_PROVINCE", length=50)

    public String getSendProvince() {
        return this.sendProvince;
    }
    
    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }
    
    @Column(name="SEND_CITY", length=50)

    public String getSendCity() {
        return this.sendCity;
    }
    
    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }
    
    @Column(name="SEND_ZONE", length=50)

    public String getSendZone() {
        return this.sendZone;
    }
    
    public void setSendZone(String sendZone) {
        this.sendZone = sendZone;
    }
    
    @Column(name="SEND_COUNTRY", length=50)

    public String getSendCountry() {
        return this.sendCountry;
    }
    
    public void setSendCountry(String sendCountry) {
        this.sendCountry = sendCountry;
    }
    
    @Column(name="RECEIVE_COMPANY_NAME", length=100)

    public String getReceiveCompanyName() {
        return this.receiveCompanyName;
    }
    
    public void setReceiveCompanyName(String receiveCompanyName) {
        this.receiveCompanyName = receiveCompanyName;
    }
    
    @Column(name="RECEIVE_TELEPHONE", length=20)

    public String getReceiveTelephone() {
        return this.receiveTelephone;
    }
    
    public void setReceiveTelephone(String receiveTelephone) {
        this.receiveTelephone = receiveTelephone;
    }
    
    @Column(name="RECEIVE_JPN", length=10)

    public String getReceiveJpn() {
        return this.receiveJpn;
    }
    
    public void setReceiveJpn(String receiveJpn) {
        this.receiveJpn = receiveJpn;
    }
    
    @Column(name="RECEIVE_PROVINCE", length=50)

    public String getReceiveProvince() {
        return this.receiveProvince;
    }
    
    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince;
    }
    
    @Column(name="RECEIVE_CITY", length=50)

    public String getReceiveCity() {
        return this.receiveCity;
    }
    
    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity;
    }
    
    @Column(name="RECEIVE_ZONE", length=50)

    public String getReceiveZone() {
        return this.receiveZone;
    }
    
    public void setReceiveZone(String receiveZone) {
        this.receiveZone = receiveZone;
    }
    
    @Column(name="RECEIVE_COUNTRY", length=50)

    public String getReceiveCountry() {
        return this.receiveCountry;
    }
    
    public void setReceiveCountry(String receiveCountry) {
        this.receiveCountry = receiveCountry;
    }
    
    @Column(name="TAKE_ADDRESS", length=100)

    public String getTakeAddress() {
        return this.takeAddress;
    }
    
    public void setTakeAddress(String takeAddress) {
        this.takeAddress = takeAddress;
    }
    
    @Column(name="TAKE_ADDRESS_CODE", length=20)

    public String getTakeAddressCode() {
        return this.takeAddressCode;
    }
    
    public void setTakeAddressCode(String takeAddressCode) {
        this.takeAddressCode = takeAddressCode;
    }
    
    @Column(name="TAKE_NAME", length=50)

    public String getTakeName() {
        return this.takeName;
    }
    
    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }
    
    @Column(name="TAKE_COMPANY_NAME", length=100)

    public String getTakeCompanyName() {
        return this.takeCompanyName;
    }
    
    public void setTakeCompanyName(String takeCompanyName) {
        this.takeCompanyName = takeCompanyName;
    }
    
    @Column(name="TAKE_POSTCODE", length=20)

    public String getTakePostcode() {
        return this.takePostcode;
    }
    
    public void setTakePostcode(String takePostcode) {
        this.takePostcode = takePostcode;
    }
    
    @Column(name="TAKE_MOBILE_PHONE", length=20)

    public String getTakeMobilePhone() {
        return this.takeMobilePhone;
    }
    
    public void setTakeMobilePhone(String takeMobilePhone) {
        this.takeMobilePhone = takeMobilePhone;
    }
    
    @Column(name="TAKE_TELEPHONE", length=20)

    public String getTakeTelephone() {
        return this.takeTelephone;
    }
    
    public void setTakeTelephone(String takeTelephone) {
        this.takeTelephone = takeTelephone;
    }
    
    @Column(name="TAKE_JPN", length=10)

    public String getTakeJpn() {
        return this.takeJpn;
    }
    
    public void setTakeJpn(String takeJpn) {
        this.takeJpn = takeJpn;
    }
    
    @Column(name="TAKE_PROVINCE", length=50)

    public String getTakeProvince() {
        return this.takeProvince;
    }
    
    public void setTakeProvince(String takeProvince) {
        this.takeProvince = takeProvince;
    }
    
    @Column(name="TAKE_CITY", length=50)

    public String getTakeCity() {
        return this.takeCity;
    }
    
    public void setTakeCity(String takeCity) {
        this.takeCity = takeCity;
    }
    
    @Column(name="TAKE_ZONE", length=50)

    public String getTakeZone() {
        return this.takeZone;
    }
    
    public void setTakeZone(String takeZone) {
        this.takeZone = takeZone;
    }
    
    @Column(name="TAKE_COUNTRY", length=50)

    public String getTakeCountry() {
        return this.takeCountry;
    }
    
    public void setTakeCountry(String takeCountry) {
        this.takeCountry = takeCountry;
    }
   








}