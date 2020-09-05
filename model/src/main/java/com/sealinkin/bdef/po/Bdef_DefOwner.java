package com.sealinkin.bdef.po;

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
 * BdefDefowner entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFOWNER")
public class Bdef_DefOwner implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Bdef_DefownerId id;
	private String ownerName;
	private String ownerAlias;
	private String ownerAddress;
	private String ownerPhone;
	private String ownerFax;
	private String ownerContact;
	private String ownerRemark;
	private String invoiceNo;
	private String invoiceAddr;
	private String invoiceHeader;
	private String status;
	private String turnOverRule;
	private String fixedcellFlag;
	private String authorityType;
	private String IStrategy;
	private String OStrategy;
	private String MStrategy;
	private String riStrategy;
	private String roStrategy;
	private String fcStrategy;
	private String rsvStrategy1;
	private String rsvStrategy2;
	private String rsvStrategy3;
	private String rsvStrategy4;
	private String rsvStrategy5;
	private String rsvStrategy6;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String scanFlag;
	private String cellManagerType;
	private String typeValue;
	private Long rowId;
	private String rsvVar1;
	private String rsvVar2;
	private String rsvVar3;
	private String rsvVar4;
	private String rsvVar5;
	private String rsvVar6;
	private String rsvVar7;
	private String rsvVar8;
	private Double rsvNum1;
	private Double rsvNum2;
	private Double rsvNum3;
	private Date rsvDate1;
	private Date rsvDate2;
	private Date rsvDate3;

	// Constructors

	/** default constructor */
	public Bdef_DefOwner() {
	}

	/** minimal constructor */
	public Bdef_DefOwner(Bdef_DefownerId id, String ownerName, String status,
			String turnOverRule, String fixedcellFlag, String authorityType,
			String IStrategy, String OStrategy, String MStrategy,
			String riStrategy, String roStrategy, String fcStrategy,
			String rgstName, Date rgstDate, String scanFlag) {
		this.id = id;
		this.ownerName = ownerName;
		this.status = status;
		this.turnOverRule = turnOverRule;
		this.fixedcellFlag = fixedcellFlag;
		this.authorityType = authorityType;
		this.IStrategy = IStrategy;
		this.OStrategy = OStrategy;
		this.MStrategy = MStrategy;
		this.riStrategy = riStrategy;
		this.roStrategy = roStrategy;
		this.fcStrategy = fcStrategy;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.scanFlag = scanFlag;
	}

	/** full constructor */
	public Bdef_DefOwner(Bdef_DefownerId id, String ownerName, String ownerAlias,
			String ownerAddress, String ownerPhone, String ownerFax,
			String ownerContact, String ownerRemark, String invoiceNo,
			String invoiceAddr, String invoiceHeader, String status,
			String turnOverRule, String fixedcellFlag, String authorityType,
			String IStrategy, String OStrategy, String MStrategy,
			String riStrategy, String roStrategy, String fcStrategy,
			String rsvStrategy1, String rsvStrategy2, String rsvStrategy3,
			String rsvStrategy4, String rsvStrategy5, String rsvStrategy6,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String scanFlag, String cellManagerType, String typeValue,
			Long rowId, String rsvVar1, String rsvVar2, String rsvVar3,
			String rsvVar4, String rsvVar5, String rsvVar6, String rsvVar7,
			String rsvVar8, Double rsvNum1, Double rsvNum2,
			Double rsvNum3, Date rsvDate1, Date rsvDate2, Date rsvDate3) {
		this.id = id;
		this.ownerName = ownerName;
		this.ownerAlias = ownerAlias;
		this.ownerAddress = ownerAddress;
		this.ownerPhone = ownerPhone;
		this.ownerFax = ownerFax;
		this.ownerContact = ownerContact;
		this.ownerRemark = ownerRemark;
		this.invoiceNo = invoiceNo;
		this.invoiceAddr = invoiceAddr;
		this.invoiceHeader = invoiceHeader;
		this.status = status;
		this.turnOverRule = turnOverRule;
		this.fixedcellFlag = fixedcellFlag;
		this.authorityType = authorityType;
		this.IStrategy = IStrategy;
		this.OStrategy = OStrategy;
		this.MStrategy = MStrategy;
		this.riStrategy = riStrategy;
		this.roStrategy = roStrategy;
		this.fcStrategy = fcStrategy;
		this.rsvStrategy1 = rsvStrategy1;
		this.rsvStrategy2 = rsvStrategy2;
		this.rsvStrategy3 = rsvStrategy3;
		this.rsvStrategy4 = rsvStrategy4;
		this.rsvStrategy5 = rsvStrategy5;
		this.rsvStrategy6 = rsvStrategy6;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.scanFlag = scanFlag;
		this.cellManagerType = cellManagerType;
		this.typeValue = typeValue;
		this.rowId = rowId;
		this.rsvVar1 = rsvVar1;
		this.rsvVar2 = rsvVar2;
		this.rsvVar3 = rsvVar3;
		this.rsvVar4 = rsvVar4;
		this.rsvVar5 = rsvVar5;
		this.rsvVar6 = rsvVar6;
		this.rsvVar7 = rsvVar7;
		this.rsvVar8 = rsvVar8;
		this.rsvNum1 = rsvNum1;
		this.rsvNum2 = rsvNum2;
		this.rsvNum3 = rsvNum3;
		this.rsvDate1 = rsvDate1;
		this.rsvDate2 = rsvDate2;
		this.rsvDate3 = rsvDate3;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "ownerNo", column = @Column(name = "OWNER_NO", nullable = false, length = 3)) })
	public Bdef_DefownerId getId() {
		return this.id;
	}

	public void setId(Bdef_DefownerId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NAME", nullable = false, length = 60)
	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "OWNER_ALIAS", length = 40)
	public String getOwnerAlias() {
		return this.ownerAlias;
	}

	public void setOwnerAlias(String ownerAlias) {
		this.ownerAlias = ownerAlias;
	}

	@Column(name = "OWNER_ADDRESS", length = 120)
	public String getOwnerAddress() {
		return this.ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	@Column(name = "OWNER_PHONE", length = 15)
	public String getOwnerPhone() {
		return this.ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	@Column(name = "OWNER_FAX", length = 15)
	public String getOwnerFax() {
		return this.ownerFax;
	}

	public void setOwnerFax(String ownerFax) {
		this.ownerFax = ownerFax;
	}

	@Column(name = "OWNER_CONTACT", length = 20)
	public String getOwnerContact() {
		return this.ownerContact;
	}

	public void setOwnerContact(String ownerContact) {
		this.ownerContact = ownerContact;
	}

	@Column(name = "OWNER_REMARK", length = 50)
	public String getOwnerRemark() {
		return this.ownerRemark;
	}

	public void setOwnerRemark(String ownerRemark) {
		this.ownerRemark = ownerRemark;
	}

	@Column(name = "INVOICE_NO", length = 20)
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "INVOICE_ADDR", length = 100)
	public String getInvoiceAddr() {
		return this.invoiceAddr;
	}

	public void setInvoiceAddr(String invoiceAddr) {
		this.invoiceAddr = invoiceAddr;
	}

	@Column(name = "INVOICE_HEADER", length = 50)
	public String getInvoiceHeader() {
		return this.invoiceHeader;
	}

	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TURN_OVER_RULE", nullable = false, length = 4)
	public String getTurnOverRule() {
		return this.turnOverRule;
	}

	public void setTurnOverRule(String turnOverRule) {
		this.turnOverRule = turnOverRule;
	}

	@Column(name = "FIXEDCELL_FLAG", nullable = false, length = 1)
	public String getFixedcellFlag() {
		return this.fixedcellFlag;
	}

	public void setFixedcellFlag(String fixedcellFlag) {
		this.fixedcellFlag = fixedcellFlag;
	}

	@Column(name = "AUTHORITY_TYPE", nullable = false, length = 1)
	public String getAuthorityType() {
		return this.authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name = "I_STRATEGY", nullable = false, length = 2)
	public String getIStrategy() {
		return this.IStrategy;
	}

	public void setIStrategy(String IStrategy) {
		this.IStrategy = IStrategy;
	}

	@Column(name = "O_STRATEGY", nullable = false, length = 2)
	public String getOStrategy() {
		return this.OStrategy;
	}

	public void setOStrategy(String OStrategy) {
		this.OStrategy = OStrategy;
	}

	@Column(name = "M_STRATEGY", nullable = false, length = 2)
	public String getMStrategy() {
		return this.MStrategy;
	}

	public void setMStrategy(String MStrategy) {
		this.MStrategy = MStrategy;
	}

	@Column(name = "RI_STRATEGY", nullable = false, length = 2)
	public String getRiStrategy() {
		return this.riStrategy;
	}

	public void setRiStrategy(String riStrategy) {
		this.riStrategy = riStrategy;
	}

	@Column(name = "RO_STRATEGY", nullable = false, length = 2)
	public String getRoStrategy() {
		return this.roStrategy;
	}

	public void setRoStrategy(String roStrategy) {
		this.roStrategy = roStrategy;
	}

	@Column(name = "FC_STRATEGY", nullable = false, length = 2)
	public String getFcStrategy() {
		return this.fcStrategy;
	}

	public void setFcStrategy(String fcStrategy) {
		this.fcStrategy = fcStrategy;
	}

	@Column(name = "RSV_STRATEGY1", length = 2)
	public String getRsvStrategy1() {
		return this.rsvStrategy1;
	}

	public void setRsvStrategy1(String rsvStrategy1) {
		this.rsvStrategy1 = rsvStrategy1;
	}

	@Column(name = "RSV_STRATEGY2", length = 2)
	public String getRsvStrategy2() {
		return this.rsvStrategy2;
	}

	public void setRsvStrategy2(String rsvStrategy2) {
		this.rsvStrategy2 = rsvStrategy2;
	}

	@Column(name = "RSV_STRATEGY3", length = 2)
	public String getRsvStrategy3() {
		return this.rsvStrategy3;
	}

	public void setRsvStrategy3(String rsvStrategy3) {
		this.rsvStrategy3 = rsvStrategy3;
	}

	@Column(name = "RSV_STRATEGY4", length = 2)
	public String getRsvStrategy4() {
		return this.rsvStrategy4;
	}

	public void setRsvStrategy4(String rsvStrategy4) {
		this.rsvStrategy4 = rsvStrategy4;
	}

	@Column(name = "RSV_STRATEGY5", length = 2)
	public String getRsvStrategy5() {
		return this.rsvStrategy5;
	}

	public void setRsvStrategy5(String rsvStrategy5) {
		this.rsvStrategy5 = rsvStrategy5;
	}

	@Column(name = "RSV_STRATEGY6", length = 2)
	public String getRsvStrategy6() {
		return this.rsvStrategy6;
	}

	public void setRsvStrategy6(String rsvStrategy6) {
		this.rsvStrategy6 = rsvStrategy6;
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

	@Column(name = "SCAN_FLAG", nullable = false, length = 1)
	public String getScanFlag() {
		return this.scanFlag;
	}

	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}

	@Column(name = "CELL_MANAGER_TYPE", length = 2)
	public String getCellManagerType() {
		return this.cellManagerType;
	}

	public void setCellManagerType(String cellManagerType) {
		this.cellManagerType = cellManagerType;
	}

	@Column(name = "TYPE_VALUE", length = 24)
	public String getTypeValue() {
		return this.typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	@Column(name = "ROW_ID", precision = 18, scale = 0)
	public Long getRowId() {
		return this.rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	@Column(name = "RSV_VAR1", length = 50)
	public String getRsvVar1() {
		return this.rsvVar1;
	}

	public void setRsvVar1(String rsvVar1) {
		this.rsvVar1 = rsvVar1;
	}

	@Column(name = "RSV_VAR2", length = 50)
	public String getRsvVar2() {
		return this.rsvVar2;
	}

	public void setRsvVar2(String rsvVar2) {
		this.rsvVar2 = rsvVar2;
	}

	@Column(name = "RSV_VAR3", length = 50)
	public String getRsvVar3() {
		return this.rsvVar3;
	}

	public void setRsvVar3(String rsvVar3) {
		this.rsvVar3 = rsvVar3;
	}

	@Column(name = "RSV_VAR4", length = 50)
	public String getRsvVar4() {
		return this.rsvVar4;
	}

	public void setRsvVar4(String rsvVar4) {
		this.rsvVar4 = rsvVar4;
	}

	@Column(name = "RSV_VAR5", length = 50)
	public String getRsvVar5() {
		return this.rsvVar5;
	}

	public void setRsvVar5(String rsvVar5) {
		this.rsvVar5 = rsvVar5;
	}

	@Column(name = "RSV_VAR6", length = 50)
	public String getRsvVar6() {
		return this.rsvVar6;
	}

	public void setRsvVar6(String rsvVar6) {
		this.rsvVar6 = rsvVar6;
	}

	@Column(name = "RSV_VAR7", length = 50)
	public String getRsvVar7() {
		return this.rsvVar7;
	}

	public void setRsvVar7(String rsvVar7) {
		this.rsvVar7 = rsvVar7;
	}

	@Column(name = "RSV_VAR8", length = 50)
	public String getRsvVar8() {
		return this.rsvVar8;
	}

	public void setRsvVar8(String rsvVar8) {
		this.rsvVar8 = rsvVar8;
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