package com.sealinkin.ridata.po;

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
 * RidataUntreadM entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RIDATA_UNTREAD_M", uniqueConstraints = @UniqueConstraint(columnNames = {
		"WAREHOUSE_NO", "OWNER_NO", "PO_TYPE", "PO_NO", "ENTERPRISE_NO" }))
public class Ridata_UntreadM implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ridata_UntreadMId id;
	private String ownerNo;
	private String untreadType;
	private String poType;
	private String poNo;
	private String classType;
	private String custNo;
	private Date untreadDate;
	private Date requestDate;
	private String status;
	private String createFlag;
	private String untreadRemark;
	private String untreadFlag;
	private String custAddressCode;
	private String stockType;
	private String stockValue;
	private String deptNo;
	private BigDecimal sendFlag;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String expNo;
	private String quality;
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
	private BigDecimal rsvNum1;
	private BigDecimal rsvNum2;
	private BigDecimal rsvNum3;
	private Date rsvDate1;
	private Date rsvDate2;
	private Date rsvDate3;
	private String carPlanNo;

	// Constructors

	/** default constructor */
	public Ridata_UntreadM() {
	}

	/** minimal constructor */
	public Ridata_UntreadM(Ridata_UntreadMId id, String ownerNo,
			String untreadType, String poType, String poNo, String classType,
			String custNo, Date untreadDate, Date requestDate, String status,
			String createFlag, String untreadFlag, String stockType,
			String stockValue, String deptNo, BigDecimal sendFlag,
			String rgstName, Date rgstDate, String expNo, String quality,
			String orgNo, String takeType) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.untreadType = untreadType;
		this.poType = poType;
		this.poNo = poNo;
		this.classType = classType;
		this.custNo = custNo;
		this.untreadDate = untreadDate;
		this.requestDate = requestDate;
		this.status = status;
		this.createFlag = createFlag;
		this.untreadFlag = untreadFlag;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.deptNo = deptNo;
		this.sendFlag = sendFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.expNo = expNo;
		this.quality = quality;
		this.orgNo = orgNo;
		this.takeType = takeType;
	}

	/** full constructor */
	public Ridata_UntreadM(Ridata_UntreadMId id, String ownerNo,
			String untreadType, String poType, String poNo, String classType,
			String custNo, Date untreadDate, Date requestDate, String status,
			String createFlag, String untreadRemark, String untreadFlag,
			String custAddressCode, String stockType, String stockValue,
			String deptNo, BigDecimal sendFlag, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String expNo, String quality,
			String orgNo, String takeType, String rsvVarod1, String rsvVarod2,
			String rsvVarod3, String rsvVarod4, String rsvVarod5,
			String rsvVarod6, String rsvVarod7, String rsvVarod8,
			BigDecimal rsvNum1, BigDecimal rsvNum2, BigDecimal rsvNum3,
			Date rsvDate1, Date rsvDate2, Date rsvDate3, String carPlanNo) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.untreadType = untreadType;
		this.poType = poType;
		this.poNo = poNo;
		this.classType = classType;
		this.custNo = custNo;
		this.untreadDate = untreadDate;
		this.requestDate = requestDate;
		this.status = status;
		this.createFlag = createFlag;
		this.untreadRemark = untreadRemark;
		this.untreadFlag = untreadFlag;
		this.custAddressCode = custAddressCode;
		this.stockType = stockType;
		this.stockValue = stockValue;
		this.deptNo = deptNo;
		this.sendFlag = sendFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.expNo = expNo;
		this.quality = quality;
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
		this.carPlanNo = carPlanNo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "untreadNo", column = @Column(name = "UNTREAD_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)) })
	public Ridata_UntreadMId getId() {
		return this.id;
	}

	public void setId(Ridata_UntreadMId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "UNTREAD_TYPE", nullable = false, length = 5)
	public String getUntreadType() {
		return this.untreadType;
	}

	public void setUntreadType(String untreadType) {
		this.untreadType = untreadType;
	}

	@Column(name = "PO_TYPE", nullable = false, length = 5)
	public String getPoType() {
		return this.poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Column(name = "CLASS_TYPE", nullable = false, length = 1)
	public String getClassType() {
		return this.classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	@Column(name = "CUST_NO", nullable = false, length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UNTREAD_DATE", nullable = false, length = 7)
	public Date getUntreadDate() {
		return this.untreadDate;
	}

	public void setUntreadDate(Date untreadDate) {
		this.untreadDate = untreadDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false, length = 7)
	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "UNTREAD_REMARK")
	public String getUntreadRemark() {
		return this.untreadRemark;
	}

	public void setUntreadRemark(String untreadRemark) {
		this.untreadRemark = untreadRemark;
	}

	@Column(name = "UNTREAD_FLAG", nullable = false, length = 1)
	public String getUntreadFlag() {
		return this.untreadFlag;
	}

	public void setUntreadFlag(String untreadFlag) {
		this.untreadFlag = untreadFlag;
	}

	@Column(name = "CUST_ADDRESS_CODE", length = 20)
	public String getCustAddressCode() {
		return this.custAddressCode;
	}

	public void setCustAddressCode(String custAddressCode) {
		this.custAddressCode = custAddressCode;
	}

	@Column(name = "STOCK_TYPE", nullable = false, length = 1)
	public String getStockType() {
		return this.stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	@Column(name = "STOCK_VALUE", nullable = false, length = 20)
	public String getStockValue() {
		return this.stockValue;
	}

	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}

	@Column(name = "DEPT_NO", nullable = false, length = 10)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "SEND_FLAG", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RGST_DATE", nullable = false, length = 7)
	public Date getRgstDate() {
		return this.rgstDate;
	}

	public void setRgstDate(Date rgstDate) {
		this.rgstDate = rgstDate;
	}

	@Column(name = "UPDT_NAME", length = 20)
	public String getUpdtName() {
		return this.updtName;
	}

	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "EXP_NO", nullable = false, length = 20)
	public String getExpNo() {
		return this.expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	@Column(name = "QUALITY", nullable = false, length = 2)
	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "ORG_NO", nullable = false, length = 20)
	public String getOrgNo() {
		return this.orgNo;
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
	public BigDecimal getRsvNum1() {
		return this.rsvNum1;
	}

	public void setRsvNum1(BigDecimal rsvNum1) {
		this.rsvNum1 = rsvNum1;
	}

	@Column(name = "RSV_NUM2", precision = 22, scale = 0)
	public BigDecimal getRsvNum2() {
		return this.rsvNum2;
	}

	public void setRsvNum2(BigDecimal rsvNum2) {
		this.rsvNum2 = rsvNum2;
	}

	@Column(name = "RSV_NUM3", precision = 22, scale = 0)
	public BigDecimal getRsvNum3() {
		return this.rsvNum3;
	}

	public void setRsvNum3(BigDecimal rsvNum3) {
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

	@Column(name = "CAR_PLAN_NO", length = 20)
	public String getCarPlanNo() {
		return this.carPlanNo;
	}

	public void setCarPlanNo(String carPlanNo) {
		this.carPlanNo = carPlanNo;
	}

}