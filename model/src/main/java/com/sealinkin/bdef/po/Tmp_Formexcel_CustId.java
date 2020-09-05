package com.sealinkin.bdef.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TmpFormexcelCustId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Tmp_Formexcel_CustId implements java.io.Serializable {

	// Fields

	private String ownerNo;
	private String ownerCustNo;
	private String custName;
	private String custAlias;
	private String custAddress;
	private String deliveryAddress;
	private String custPhone1;
	private String contactorName1;
	private String custProvince;
	private String custCity;
	private String custZone;
	private String status;
	private String enterpriseNo;
	private Double rowId;
	private String controlType;
	private Integer controlValue;
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
	public Tmp_Formexcel_CustId() {
	}

	/** minimal constructor */
	public Tmp_Formexcel_CustId(String ownerNo, String custName, String status,
			String enterpriseNo, Double rowId, String controlType,
			Integer controlValue) {
		this.ownerNo = ownerNo;
		this.custName = custName;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
		this.controlType = controlType;
		this.controlValue = controlValue;
	}

	/** full constructor */
	public Tmp_Formexcel_CustId(String ownerNo, String ownerCustNo,
			String custName, String custAlias, String custAddress,
			String deliveryAddress, String custPhone1, String contactorName1,
			String custProvince, String custCity, String custZone,
			String status, String enterpriseNo, Double rowId,
			String controlType, Integer controlValue, String rsvVarod1,
			String rsvVarod2, String rsvVarod3, String rsvVarod4,
			String rsvVarod5, String rsvVarod6, String rsvVarod7,
			String rsvVarod8, Double rsvNum1, Double rsvNum2,
			Double rsvNum3, Date rsvDate1, Date rsvDate2, Date rsvDate3) {
		this.ownerNo = ownerNo;
		this.ownerCustNo = ownerCustNo;
		this.custName = custName;
		this.custAlias = custAlias;
		this.custAddress = custAddress;
		this.deliveryAddress = deliveryAddress;
		this.custPhone1 = custPhone1;
		this.contactorName1 = contactorName1;
		this.custProvince = custProvince;
		this.custCity = custCity;
		this.custZone = custZone;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
		this.controlType = controlType;
		this.controlValue = controlValue;
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

	@Column(name = "OWNER_CUST_NO", length = 20)
	public String getOwnerCustNo() {
		return this.ownerCustNo;
	}

	public void setOwnerCustNo(String ownerCustNo) {
		this.ownerCustNo = ownerCustNo;
	}

	@Column(name = "CUST_NAME", nullable = false, length = 90)
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "CUST_ALIAS", length = 90)
	public String getCustAlias() {
		return this.custAlias;
	}

	public void setCustAlias(String custAlias) {
		this.custAlias = custAlias;
	}

	@Column(name = "CUST_ADDRESS", length = 100)
	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	@Column(name = "DELIVERY_ADDRESS", length = 180)
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Column(name = "CUST_PHONE1", length = 50)
	public String getCustPhone1() {
		return this.custPhone1;
	}

	public void setCustPhone1(String custPhone1) {
		this.custPhone1 = custPhone1;
	}

	@Column(name = "CONTACTOR_NAME1", length = 50)
	public String getContactorName1() {
		return this.contactorName1;
	}

	public void setContactorName1(String contactorName1) {
		this.contactorName1 = contactorName1;
	}

	@Column(name = "CUST_PROVINCE", length = 200)
	public String getCustProvince() {
		return this.custProvince;
	}

	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}

	@Column(name = "CUST_CITY", length = 200)
	public String getCustCity() {
		return this.custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	@Column(name = "CUST_ZONE", length = 200)
	public String getCustZone() {
		return this.custZone;
	}

	public void setCustZone(String custZone) {
		this.custZone = custZone;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
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

	@Column(name = "ROW_ID", nullable = false, precision = 22, scale = 0)
	public Double getRowId() {
		return this.rowId;
	}

	public void setRowId(Double rowId) {
		this.rowId = rowId;
	}

	@Column(name = "CONTROL_TYPE", nullable = false, length = 1)
	public String getControlType() {
		return this.controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	@Column(name = "CONTROL_VALUE", nullable = false, precision = 22, scale = 0)
	public Integer getControlValue() {
		return this.controlValue;
	}

	public void setControlValue(Integer controlValue) {
		this.controlValue = controlValue;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RSV_DATE1", length = 7)
	public Date getRsvDate1() {
		return this.rsvDate1;
	}

	public void setRsvDate1(Date rsvDate1) {
		this.rsvDate1 = rsvDate1;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RSV_DATE2", length = 7)
	public Date getRsvDate2() {
		return this.rsvDate2;
	}

	public void setRsvDate2(Date rsvDate2) {
		this.rsvDate2 = rsvDate2;
	}

	@Temporal(TemporalType.TIMESTAMP)
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
		if (!(other instanceof Tmp_Formexcel_CustId))
			return false;
		Tmp_Formexcel_CustId castOther = (Tmp_Formexcel_CustId) other;

		return ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
				.getOwnerNo() != null && castOther.getOwnerNo() != null && this
				.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getOwnerCustNo() == castOther.getOwnerCustNo()) || (this
						.getOwnerCustNo() != null
						&& castOther.getOwnerCustNo() != null && this
						.getOwnerCustNo().equals(castOther.getOwnerCustNo())))
				&& ((this.getCustName() == castOther.getCustName()) || (this
						.getCustName() != null
						&& castOther.getCustName() != null && this
						.getCustName().equals(castOther.getCustName())))
				&& ((this.getCustAlias() == castOther.getCustAlias()) || (this
						.getCustAlias() != null
						&& castOther.getCustAlias() != null && this
						.getCustAlias().equals(castOther.getCustAlias())))
				&& ((this.getCustAddress() == castOther.getCustAddress()) || (this
						.getCustAddress() != null
						&& castOther.getCustAddress() != null && this
						.getCustAddress().equals(castOther.getCustAddress())))
				&& ((this.getDeliveryAddress() == castOther
						.getDeliveryAddress()) || (this.getDeliveryAddress() != null
						&& castOther.getDeliveryAddress() != null && this
						.getDeliveryAddress().equals(
								castOther.getDeliveryAddress())))
				&& ((this.getCustPhone1() == castOther.getCustPhone1()) || (this
						.getCustPhone1() != null
						&& castOther.getCustPhone1() != null && this
						.getCustPhone1().equals(castOther.getCustPhone1())))
				&& ((this.getContactorName1() == castOther.getContactorName1()) || (this
						.getContactorName1() != null
						&& castOther.getContactorName1() != null && this
						.getContactorName1().equals(
								castOther.getContactorName1())))
				&& ((this.getCustProvince() == castOther.getCustProvince()) || (this
						.getCustProvince() != null
						&& castOther.getCustProvince() != null && this
						.getCustProvince().equals(castOther.getCustProvince())))
				&& ((this.getCustCity() == castOther.getCustCity()) || (this
						.getCustCity() != null
						&& castOther.getCustCity() != null && this
						.getCustCity().equals(castOther.getCustCity())))
				&& ((this.getCustZone() == castOther.getCustZone()) || (this
						.getCustZone() != null
						&& castOther.getCustZone() != null && this
						.getCustZone().equals(castOther.getCustZone())))
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
				&& ((this.getControlType() == castOther.getControlType()) || (this
						.getControlType() != null
						&& castOther.getControlType() != null && this
						.getControlType().equals(castOther.getControlType())))
				&& ((this.getControlValue() == castOther.getControlValue()) || (this
						.getControlValue() != null
						&& castOther.getControlValue() != null && this
						.getControlValue().equals(castOther.getControlValue())))
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
				+ (getOwnerCustNo() == null ? 0 : this.getOwnerCustNo()
						.hashCode());
		result = 37 * result
				+ (getCustName() == null ? 0 : this.getCustName().hashCode());
		result = 37 * result
				+ (getCustAlias() == null ? 0 : this.getCustAlias().hashCode());
		result = 37
				* result
				+ (getCustAddress() == null ? 0 : this.getCustAddress()
						.hashCode());
		result = 37
				* result
				+ (getDeliveryAddress() == null ? 0 : this.getDeliveryAddress()
						.hashCode());
		result = 37
				* result
				+ (getCustPhone1() == null ? 0 : this.getCustPhone1()
						.hashCode());
		result = 37
				* result
				+ (getContactorName1() == null ? 0 : this.getContactorName1()
						.hashCode());
		result = 37
				* result
				+ (getCustProvince() == null ? 0 : this.getCustProvince()
						.hashCode());
		result = 37 * result
				+ (getCustCity() == null ? 0 : this.getCustCity().hashCode());
		result = 37 * result
				+ (getCustZone() == null ? 0 : this.getCustZone().hashCode());
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
				+ (getControlType() == null ? 0 : this.getControlType()
						.hashCode());
		result = 37
				* result
				+ (getControlValue() == null ? 0 : this.getControlValue()
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