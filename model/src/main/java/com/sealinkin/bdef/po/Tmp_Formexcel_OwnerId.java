package com.sealinkin.bdef.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TmpFormexcelOwnerId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_Formexcel_OwnerId implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String ownerNo;
	private String ownerName;
	private String ownerAlias;
	private String ownerAddress;
	private String ownerPhone;
	private String ownerFax;
	private String ownerContact;
	private String authorityType;
	private Double rowId;
	private String enterpriseNo;
	private String status;
	private String cellManagerType;
	private String typeValue;
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
	public Tmp_Formexcel_OwnerId() {
	}

	/** minimal constructor */
	public Tmp_Formexcel_OwnerId(String ownerNo, String ownerName,
			String authorityType, Double rowId, String status) {
		this.ownerNo = ownerNo;
		this.ownerName = ownerName;
		this.authorityType = authorityType;
		this.rowId = rowId;
		this.status = status;
	}

	/** full constructor */
	public Tmp_Formexcel_OwnerId(String ownerNo, String ownerName,
			String ownerAlias, String ownerAddress, String ownerPhone,
			String ownerFax, String ownerContact, String authorityType,
			Double rowId, String enterpriseNo, String status,
			String cellManagerType, String typeValue, String rsvVarod1,
			String rsvVarod2, String rsvVarod3, String rsvVarod4,
			String rsvVarod5, String rsvVarod6, String rsvVarod7,
			String rsvVarod8, Double rsvNum1, Double rsvNum2,
			Double rsvNum3, Date rsvDate1, Date rsvDate2, Date rsvDate3) {
		this.ownerNo = ownerNo;
		this.ownerName = ownerName;
		this.ownerAlias = ownerAlias;
		this.ownerAddress = ownerAddress;
		this.ownerPhone = ownerPhone;
		this.ownerFax = ownerFax;
		this.ownerContact = ownerContact;
		this.authorityType = authorityType;
		this.rowId = rowId;
		this.enterpriseNo = enterpriseNo;
		this.status = status;
		this.cellManagerType = cellManagerType;
		this.typeValue = typeValue;
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

	@Column(name = "AUTHORITY_TYPE", nullable = false, length = 1)
	public String getAuthorityType() {
		return this.authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	@Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "ENTERPRISE_NO", length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		if (!(other instanceof Tmp_Formexcel_OwnerId))
			return false;
		Tmp_Formexcel_OwnerId castOther = (Tmp_Formexcel_OwnerId) other;

		return ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
				.getOwnerNo() != null && castOther.getOwnerNo() != null && this
				.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getOwnerName() == castOther.getOwnerName()) || (this
						.getOwnerName() != null
						&& castOther.getOwnerName() != null && this
						.getOwnerName().equals(castOther.getOwnerName())))
				&& ((this.getOwnerAlias() == castOther.getOwnerAlias()) || (this
						.getOwnerAlias() != null
						&& castOther.getOwnerAlias() != null && this
						.getOwnerAlias().equals(castOther.getOwnerAlias())))
				&& ((this.getOwnerAddress() == castOther.getOwnerAddress()) || (this
						.getOwnerAddress() != null
						&& castOther.getOwnerAddress() != null && this
						.getOwnerAddress().equals(castOther.getOwnerAddress())))
				&& ((this.getOwnerPhone() == castOther.getOwnerPhone()) || (this
						.getOwnerPhone() != null
						&& castOther.getOwnerPhone() != null && this
						.getOwnerPhone().equals(castOther.getOwnerPhone())))
				&& ((this.getOwnerFax() == castOther.getOwnerFax()) || (this
						.getOwnerFax() != null
						&& castOther.getOwnerFax() != null && this
						.getOwnerFax().equals(castOther.getOwnerFax())))
				&& ((this.getOwnerContact() == castOther.getOwnerContact()) || (this
						.getOwnerContact() != null
						&& castOther.getOwnerContact() != null && this
						.getOwnerContact().equals(castOther.getOwnerContact())))
				&& ((this.getAuthorityType() == castOther.getAuthorityType()) || (this
						.getAuthorityType() != null
						&& castOther.getAuthorityType() != null && this
						.getAuthorityType()
						.equals(castOther.getAuthorityType())))
				&& ((this.getRowId() == castOther.getRowId()) || (this
						.getRowId() != null && castOther.getRowId() != null && this
						.getRowId().equals(castOther.getRowId())))
				&& ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
						.getEnterpriseNo() != null
						&& castOther.getEnterpriseNo() != null && this
						.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getCellManagerType() == castOther
						.getCellManagerType()) || (this.getCellManagerType() != null
						&& castOther.getCellManagerType() != null && this
						.getCellManagerType().equals(
								castOther.getCellManagerType())))
				&& ((this.getTypeValue() == castOther.getTypeValue()) || (this
						.getTypeValue() != null
						&& castOther.getTypeValue() != null && this
						.getTypeValue().equals(castOther.getTypeValue())))
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
		result = 37 * result
				+ (getOwnerName() == null ? 0 : this.getOwnerName().hashCode());
		result = 37
				* result
				+ (getOwnerAlias() == null ? 0 : this.getOwnerAlias()
						.hashCode());
		result = 37
				* result
				+ (getOwnerAddress() == null ? 0 : this.getOwnerAddress()
						.hashCode());
		result = 37
				* result
				+ (getOwnerPhone() == null ? 0 : this.getOwnerPhone()
						.hashCode());
		result = 37 * result
				+ (getOwnerFax() == null ? 0 : this.getOwnerFax().hashCode());
		result = 37
				* result
				+ (getOwnerContact() == null ? 0 : this.getOwnerContact()
						.hashCode());
		result = 37
				* result
				+ (getAuthorityType() == null ? 0 : this.getAuthorityType()
						.hashCode());
		result = 37 * result
				+ (getRowId() == null ? 0 : this.getRowId().hashCode());
		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37
				* result
				+ (getCellManagerType() == null ? 0 : this.getCellManagerType()
						.hashCode());
		result = 37 * result
				+ (getTypeValue() == null ? 0 : this.getTypeValue().hashCode());
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