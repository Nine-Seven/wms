package com.sealinkin.bdef.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TmpFromexcelSupplierId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_Fromexcel_SupplierId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String ownerNo;
	private String supplierNo;
	private String supplierName;
	private String supplierAddress1;
	private String supplierPhone1;
	private String supplier1;
	private String status;
	private String enterpriseNo;
	private Double rowId;
	private Integer unloadFlag;
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
	public Tmp_Fromexcel_SupplierId() {
	}

	/** minimal constructor */
	public Tmp_Fromexcel_SupplierId(String ownerNo, String supplierName,
			String enterpriseNo, Integer unloadFlag) {
		this.ownerNo = ownerNo;
		this.supplierName = supplierName;
		this.enterpriseNo = enterpriseNo;
		this.unloadFlag = unloadFlag;
	}

	/** full constructor */
	public Tmp_Fromexcel_SupplierId(String ownerNo, String supplierNo,
			String supplierName, String supplierAddress1,
			String supplierPhone1, String supplier1, String status,
			String enterpriseNo, Double rowId, Integer unloadFlag,
			String rsvVarod1, String rsvVarod2, String rsvVarod3,
			String rsvVarod4, String rsvVarod5, String rsvVarod6,
			String rsvVarod7, String rsvVarod8, Double rsvNum1,
			Double rsvNum2, Double rsvNum3, Date rsvDate1,
			Date rsvDate2, Date rsvDate3) {
		this.ownerNo = ownerNo;
		this.supplierNo = supplierNo;
		this.supplierName = supplierName;
		this.supplierAddress1 = supplierAddress1;
		this.supplierPhone1 = supplierPhone1;
		this.supplier1 = supplier1;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
		this.unloadFlag = unloadFlag;
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

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "SUPPLIER_NO", length = 10)
	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Column(name = "SUPPLIER_NAME", nullable = false, length = 90)
	public String getSupplierName() {
		return this.supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	@Column(name = "SUPPLIER1", length = 50)
	public String getSupplier1() {
		return this.supplier1;
	}

	public void setSupplier1(String supplier1) {
		this.supplier1 = supplier1;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 20)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "ROW_ID", precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "UNLOAD_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getUnloadFlag() {
		return this.unloadFlag;
	}

	public void setUnloadFlag(Integer unloadFlag) {
		this.unloadFlag = unloadFlag;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Tmp_Fromexcel_SupplierId))
			return false;
		Tmp_Fromexcel_SupplierId castOther = (Tmp_Fromexcel_SupplierId) other;

		return ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
				.getOwnerNo() != null && castOther.getOwnerNo() != null && this
				.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getSupplierNo() == castOther.getSupplierNo()) || (this
						.getSupplierNo() != null
						&& castOther.getSupplierNo() != null && this
						.getSupplierNo().equals(castOther.getSupplierNo())))
				&& ((this.getSupplierName() == castOther.getSupplierName()) || (this
						.getSupplierName() != null
						&& castOther.getSupplierName() != null && this
						.getSupplierName().equals(castOther.getSupplierName())))
				&& ((this.getSupplierAddress1() == castOther
						.getSupplierAddress1()) || (this.getSupplierAddress1() != null
						&& castOther.getSupplierAddress1() != null && this
						.getSupplierAddress1().equals(
								castOther.getSupplierAddress1())))
				&& ((this.getSupplierPhone1() == castOther.getSupplierPhone1()) || (this
						.getSupplierPhone1() != null
						&& castOther.getSupplierPhone1() != null && this
						.getSupplierPhone1().equals(
								castOther.getSupplierPhone1())))
				&& ((this.getSupplier1() == castOther.getSupplier1()) || (this
						.getSupplier1() != null
						&& castOther.getSupplier1() != null && this
						.getSupplier1().equals(castOther.getSupplier1())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())))
				&& ((this.getUnloadFlag() == castOther.getUnloadFlag()) || (this
						.getUnloadFlag() != null
						&& castOther.getUnloadFlag() != null && this
						.getUnloadFlag().equals(castOther.getUnloadFlag())))
				&& ((this.getRsvVarod1() == castOther.getRsvVarod1()) || (this
						.getRsvVarod1() != null
						&& castOther.getRsvVarod1() != null && this
						.getRsvVarod1().equals(castOther.getRsvVarod1())))
				&& ((this.getRsvVarod2() == castOther.getRsvVarod2()) || (this
						.getRsvVarod2() != null
						&& castOther.getRsvVarod2() != null && this
						.getRsvVarod2().equals(castOther.getRsvVarod2())))
				&& ((this.getRsvVarod3() == castOther.getRsvVarod3()) || (this
						.getRsvVarod3() != null
						&& castOther.getRsvVarod3() != null && this
						.getRsvVarod3().equals(castOther.getRsvVarod3())))
				&& ((this.getRsvVarod4() == castOther.getRsvVarod4()) || (this
						.getRsvVarod4() != null
						&& castOther.getRsvVarod4() != null && this
						.getRsvVarod4().equals(castOther.getRsvVarod4())))
				&& ((this.getRsvVarod5() == castOther.getRsvVarod5()) || (this
						.getRsvVarod5() != null
						&& castOther.getRsvVarod5() != null && this
						.getRsvVarod5().equals(castOther.getRsvVarod5())))
				&& ((this.getRsvVarod6() == castOther.getRsvVarod6()) || (this
						.getRsvVarod6() != null
						&& castOther.getRsvVarod6() != null && this
						.getRsvVarod6().equals(castOther.getRsvVarod6())))
				&& ((this.getRsvVarod7() == castOther.getRsvVarod7()) || (this
						.getRsvVarod7() != null
						&& castOther.getRsvVarod7() != null && this
						.getRsvVarod7().equals(castOther.getRsvVarod7())))
				&& ((this.getRsvVarod8() == castOther.getRsvVarod8()) || (this
						.getRsvVarod8() != null
						&& castOther.getRsvVarod8() != null && this
						.getRsvVarod8().equals(castOther.getRsvVarod8())))
				&& ((this.getRsvNum1() == castOther.getRsvNum1()) || (this
						.getRsvNum1() != null && castOther.getRsvNum1() != null && this
						.getRsvNum1().equals(castOther.getRsvNum1())))
				&& ((this.getRsvNum2() == castOther.getRsvNum2()) || (this
						.getRsvNum2() != null && castOther.getRsvNum2() != null && this
						.getRsvNum2().equals(castOther.getRsvNum2())))
				&& ((this.getRsvNum3() == castOther.getRsvNum3()) || (this
						.getRsvNum3() != null && castOther.getRsvNum3() != null && this
						.getRsvNum3().equals(castOther.getRsvNum3())))
				&& ((this.getRsvDate1() == castOther.getRsvDate1()) || (this
						.getRsvDate1() != null
						&& castOther.getRsvDate1() != null && this
						.getRsvDate1().equals(castOther.getRsvDate1())))
				&& ((this.getRsvDate2() == castOther.getRsvDate2()) || (this
						.getRsvDate2() != null
						&& castOther.getRsvDate2() != null && this
						.getRsvDate2().equals(castOther.getRsvDate2())))
				&& ((this.getRsvDate3() == castOther.getRsvDate3()) || (this
						.getRsvDate3() != null
						&& castOther.getRsvDate3() != null && this
						.getRsvDate3().equals(castOther.getRsvDate3())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37
				* result
				+ (getSupplierNo() == null ? 0 : this.getSupplierNo()
						.hashCode());
		result = 37
				* result
				+ (getSupplierName() == null ? 0 : this.getSupplierName()
						.hashCode());
		result = 37
				* result
				+ (getSupplierAddress1() == null ? 0 : this
						.getSupplierAddress1().hashCode());
		result = 37
				* result
				+ (getSupplierPhone1() == null ? 0 : this.getSupplierPhone1()
						.hashCode());
		result = 37 * result
				+ (getSupplier1() == null ? 0 : this.getSupplier1().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		result = 37
				* result
				+ (getUnloadFlag() == null ? 0 : this.getUnloadFlag()
						.hashCode());
		result = 37 * result
				+ (getRsvVarod1() == null ? 0 : this.getRsvVarod1().hashCode());
		result = 37 * result
				+ (getRsvVarod2() == null ? 0 : this.getRsvVarod2().hashCode());
		result = 37 * result
				+ (getRsvVarod3() == null ? 0 : this.getRsvVarod3().hashCode());
		result = 37 * result
				+ (getRsvVarod4() == null ? 0 : this.getRsvVarod4().hashCode());
		result = 37 * result
				+ (getRsvVarod5() == null ? 0 : this.getRsvVarod5().hashCode());
		result = 37 * result
				+ (getRsvVarod6() == null ? 0 : this.getRsvVarod6().hashCode());
		result = 37 * result
				+ (getRsvVarod7() == null ? 0 : this.getRsvVarod7().hashCode());
		result = 37 * result
				+ (getRsvVarod8() == null ? 0 : this.getRsvVarod8().hashCode());
		result = 37 * result
				+ (getRsvNum1() == null ? 0 : this.getRsvNum1().hashCode());
		result = 37 * result
				+ (getRsvNum2() == null ? 0 : this.getRsvNum2().hashCode());
		result = 37 * result
				+ (getRsvNum3() == null ? 0 : this.getRsvNum3().hashCode());
		result = 37 * result
				+ (getRsvDate1() == null ? 0 : this.getRsvDate1().hashCode());
		result = 37 * result
				+ (getRsvDate2() == null ? 0 : this.getRsvDate2().hashCode());
		result = 37 * result
				+ (getRsvDate3() == null ? 0 : this.getRsvDate3().hashCode());
		return result;
	}

}