package com.sealinkin.bdef.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BdefDefsupplierLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFSUPPLIER_LOG")
public class Bdef_DefsupplierLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private BigDecimal serialid;
	private String ownerNo;
	private String supplierNo;
	private String realSupplierNo;
	private String realSupplierName;
	private String supplierName;
	private String supplierAlias;
	private String supplierAddress1;
	private String supplierPhone1;
	private String supplierFax1;
	private String supplier1;
	private String supplierAddress2;
	private String supplierPhone2;
	private String supplierFax2;
	private String supplier2;
	private String supplierRemark;
	private String invoiceNo;
	private String invoiceAddr;
	private String invoiceHeader;
	private String status;
	private Boolean unloadFlag;
	private String areaNo;
	private String createFlag;
	private String supplierNoteCode;
	private String deptNo;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String modiattr;
	private Date moditime;
	private String modiopid;
	private String enterpriseNo;
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
	public Bdef_DefsupplierLog() {
	}

	/** minimal constructor */
	public Bdef_DefsupplierLog(BigDecimal serialid, String ownerNo,
			String supplierNo, String supplierName, Boolean unloadFlag,
			String createFlag, String deptNo, String rgstName, Date rgstDate,
			String modiattr, Date moditime, String modiopid) {
		this.serialid = serialid;
		this.ownerNo = ownerNo;
		this.supplierNo = supplierNo;
		this.supplierName = supplierName;
		this.unloadFlag = unloadFlag;
		this.createFlag = createFlag;
		this.deptNo = deptNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.modiattr = modiattr;
		this.moditime = moditime;
		this.modiopid = modiopid;
	}

	/** full constructor */
	public Bdef_DefsupplierLog(BigDecimal serialid, String ownerNo,
			String supplierNo, String realSupplierNo, String realSupplierName,
			String supplierName, String supplierAlias, String supplierAddress1,
			String supplierPhone1, String supplierFax1, String supplier1,
			String supplierAddress2, String supplierPhone2,
			String supplierFax2, String supplier2, String supplierRemark,
			String invoiceNo, String invoiceAddr, String invoiceHeader,
			String status, Boolean unloadFlag, String areaNo,
			String createFlag, String supplierNoteCode, String deptNo,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			String modiattr, Date moditime, String modiopid,
			String enterpriseNo, String rsvVar1, String rsvVar2,
			String rsvVar3, String rsvVar4, String rsvVar5, String rsvVar6,
			String rsvVar7, String rsvVar8, Double rsvNum1,
			Double rsvNum2, Double rsvNum3, Date rsvDate1,
			Date rsvDate2, Date rsvDate3) {
		this.serialid = serialid;
		this.ownerNo = ownerNo;
		this.supplierNo = supplierNo;
		this.realSupplierNo = realSupplierNo;
		this.realSupplierName = realSupplierName;
		this.supplierName = supplierName;
		this.supplierAlias = supplierAlias;
		this.supplierAddress1 = supplierAddress1;
		this.supplierPhone1 = supplierPhone1;
		this.supplierFax1 = supplierFax1;
		this.supplier1 = supplier1;
		this.supplierAddress2 = supplierAddress2;
		this.supplierPhone2 = supplierPhone2;
		this.supplierFax2 = supplierFax2;
		this.supplier2 = supplier2;
		this.supplierRemark = supplierRemark;
		this.invoiceNo = invoiceNo;
		this.invoiceAddr = invoiceAddr;
		this.invoiceHeader = invoiceHeader;
		this.status = status;
		this.unloadFlag = unloadFlag;
		this.areaNo = areaNo;
		this.createFlag = createFlag;
		this.supplierNoteCode = supplierNoteCode;
		this.deptNo = deptNo;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.modiattr = modiattr;
		this.moditime = moditime;
		this.modiopid = modiopid;
		this.enterpriseNo = enterpriseNo;
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
	@Id
	@Column(name = "SERIALID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getSerialid() {
		return this.serialid;
	}

	public void setSerialid(BigDecimal serialid) {
		this.serialid = serialid;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "SUPPLIER_NO", nullable = false, length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "REAL_SUPPLIER_NO", length = 10)
	public String getRealSupplierNo() {
		return this.realSupplierNo;
	}

	public void setRealSupplierNo(String realSupplierNo) {
		this.realSupplierNo = realSupplierNo;
	}

	@Column(name = "REAL_SUPPLIER_NAME", length = 90)
	public String getRealSupplierName() {
		return this.realSupplierName;
	}

	public void setRealSupplierName(String realSupplierName) {
		this.realSupplierName = realSupplierName;
	}

	@Column(name = "SUPPLIER_NAME", nullable = false, length = 90)
	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "SUPPLIER_ALIAS", length = 90)
	public String getSupplierAlias() {
		return this.supplierAlias;
	}

	public void setSupplierAlias(String supplierAlias) {
		this.supplierAlias = supplierAlias;
	}

	@Column(name = "SUPPLIER_ADDRESS1", length = 180)
	public String getSupplierAddress1() {
		return this.supplierAddress1;
	}

	public void setSupplierAddress1(String supplierAddress1) {
		this.supplierAddress1 = supplierAddress1;
	}

	@Column(name = "SUPPLIER_PHONE1", length = 50)
	public String getSupplierPhone1() {
		return this.supplierPhone1;
	}

	public void setSupplierPhone1(String supplierPhone1) {
		this.supplierPhone1 = supplierPhone1;
	}

	@Column(name = "SUPPLIER_FAX1", length = 50)
	public String getSupplierFax1() {
		return this.supplierFax1;
	}

	public void setSupplierFax1(String supplierFax1) {
		this.supplierFax1 = supplierFax1;
	}

	@Column(name = "SUPPLIER1", length = 50)
	public String getSupplier1() {
		return this.supplier1;
	}

	public void setSupplier1(String supplier1) {
		this.supplier1 = supplier1;
	}

	@Column(name = "SUPPLIER_ADDRESS2", length = 180)
	public String getSupplierAddress2() {
		return this.supplierAddress2;
	}

	public void setSupplierAddress2(String supplierAddress2) {
		this.supplierAddress2 = supplierAddress2;
	}

	@Column(name = "SUPPLIER_PHONE2", length = 50)
	public String getSupplierPhone2() {
		return this.supplierPhone2;
	}

	public void setSupplierPhone2(String supplierPhone2) {
		this.supplierPhone2 = supplierPhone2;
	}

	@Column(name = "SUPPLIER_FAX2", length = 50)
	public String getSupplierFax2() {
		return this.supplierFax2;
	}

	public void setSupplierFax2(String supplierFax2) {
		this.supplierFax2 = supplierFax2;
	}

	@Column(name = "SUPPLIER2", length = 15)
	public String getSupplier2() {
		return this.supplier2;
	}

	public void setSupplier2(String supplier2) {
		this.supplier2 = supplier2;
	}

	@Column(name = "SUPPLIER_REMARK", length = 50)
	public String getSupplierRemark() {
		return this.supplierRemark;
	}

	public void setSupplierRemark(String supplierRemark) {
		this.supplierRemark = supplierRemark;
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

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "UNLOAD_FLAG", nullable = false, precision = 1, scale = 0)
	public Boolean getUnloadFlag() {
		return this.unloadFlag;
	}

	public void setUnloadFlag(Boolean unloadFlag) {
		this.unloadFlag = unloadFlag;
	}

	@Column(name = "AREA_NO", length = 2)
	public String getAreaNo() {
		return this.areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	@Column(name = "CREATE_FLAG", nullable = false, length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "SUPPLIER_NOTE_CODE", length = 50)
	public String getSupplierNoteCode() {
		return this.supplierNoteCode;
	}

	public void setSupplierNoteCode(String supplierNoteCode) {
		this.supplierNoteCode = supplierNoteCode;
	}

	@Column(name = "DEPT_NO", nullable = false, length = 10)
	public String getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
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

	@Column(name = "MODIATTR", nullable = false, length = 1)
	public String getModiattr() {
		return this.modiattr;
	}

	public void setModiattr(String modiattr) {
		this.modiattr = modiattr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODITIME", nullable = false, length = 7)
	public Date getModitime() {
		return this.moditime;
	}

	public void setModitime(Date moditime) {
		this.moditime = moditime;
	}

	@Column(name = "MODIOPID", nullable = false, length = 20)
	public String getModiopid() {
		return this.modiopid;
	}

	public void setModiopid(String modiopid) {
		this.modiopid = modiopid;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
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