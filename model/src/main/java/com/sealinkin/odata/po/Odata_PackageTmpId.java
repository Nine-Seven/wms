package com.sealinkin.odata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OdataPackageTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_PackageTmpId implements java.io.Serializable {

	// Fields

	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String poNo;
	private String poType;
	private String sourceexpNo;
	private Date expDate;
	private String shipperNo;
	private String shipperDeliverNo;
	private String custAddress;
	private String custPhone;
	private String contactorName;
	private String sendAddress;
	private String sendMobilePhone;
	private String sendName;

	// Constructors

	/** default constructor */
	public Odata_PackageTmpId() {
	}

	/** minimal constructor */
	public Odata_PackageTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String poNo, String poType, String sourceexpNo,
			String shipperDeliverNo) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poNo = poNo;
		this.poType = poType;
		this.sourceexpNo = sourceexpNo;
		this.shipperDeliverNo = shipperDeliverNo;
	}

	/** full constructor */
	public Odata_PackageTmpId(String enterpriseNo, String warehouseNo,
			String ownerNo, String poNo, String poType, String sourceexpNo,
			Date expDate, String shipperNo, String shipperDeliverNo,
			String custAddress, String custPhone, String contactorName,
			String sendAddress, String sendMobilePhone, String sendName) {
		this.enterpriseNo = enterpriseNo;
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.poNo = poNo;
		this.poType = poType;
		this.sourceexpNo = sourceexpNo;
		this.expDate = expDate;
		this.shipperNo = shipperNo;
		this.shipperDeliverNo = shipperDeliverNo;
		this.custAddress = custAddress;
		this.custPhone = custPhone;
		this.contactorName = contactorName;
		this.sendAddress = sendAddress;
		this.sendMobilePhone = sendMobilePhone;
		this.sendName = sendName;
	}

	// Property accessors

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
	public String getEnterpriseNo() {
		return this.enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	@Column(name = "WAREHOUSE_NO", nullable = false, length = 5)
	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 10)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "PO_NO", nullable = false, length = 20)
	public String getPoNo() {
		return this.poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	@Column(name = "PO_TYPE", nullable = false, length = 5)
	public String getPoType() {
		return this.poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	@Column(name = "SOURCEEXP_NO", nullable = false, length = 30)
	public String getSourceexpNo() {
		return this.sourceexpNo;
	}

	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXP_DATE", length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "SHIPPER_NO", length = 15)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	@Column(name = "SHIPPER_DELIVER_NO", nullable = false, length = 30)
	public String getShipperDeliverNo() {
		return this.shipperDeliverNo;
	}

	public void setShipperDeliverNo(String shipperDeliverNo) {
		this.shipperDeliverNo = shipperDeliverNo;
	}

	@Column(name = "CUST_ADDRESS", length = 100)
	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	@Column(name = "CUST_PHONE", length = 50)
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "CONTACTOR_NAME", length = 50)
	public String getContactorName() {
		return this.contactorName;
	}

	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	@Column(name = "SEND_ADDRESS", length = 100)
	public String getSendAddress() {
		return this.sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	@Column(name = "SEND_MOBILE_PHONE", length = 20)
	public String getSendMobilePhone() {
		return this.sendMobilePhone;
	}

	public void setSendMobilePhone(String sendMobilePhone) {
		this.sendMobilePhone = sendMobilePhone;
	}

	@Column(name = "SEND_NAME", length = 50)
	public String getSendName() {
		return this.sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_PackageTmpId))
			return false;
		Odata_PackageTmpId castOther = (Odata_PackageTmpId) other;

		return ((this.getEnterpriseNo() == castOther.getEnterpriseNo()) || (this
				.getEnterpriseNo() != null
				&& castOther.getEnterpriseNo() != null && this
				.getEnterpriseNo().equals(castOther.getEnterpriseNo())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getPoNo() == castOther.getPoNo()) || (this.getPoNo() != null
						&& castOther.getPoNo() != null && this.getPoNo()
						.equals(castOther.getPoNo())))
				&& ((this.getPoType() == castOther.getPoType()) || (this
						.getPoType() != null && castOther.getPoType() != null && this
						.getPoType().equals(castOther.getPoType())))
				&& ((this.getSourceexpNo() == castOther.getSourceexpNo()) || (this
						.getSourceexpNo() != null
						&& castOther.getSourceexpNo() != null && this
						.getSourceexpNo().equals(castOther.getSourceexpNo())))
				&& ((this.getExpDate() == castOther.getExpDate()) || (this
						.getExpDate() != null && castOther.getExpDate() != null && this
						.getExpDate().equals(castOther.getExpDate())))
				&& ((this.getShipperNo() == castOther.getShipperNo()) || (this
						.getShipperNo() != null
						&& castOther.getShipperNo() != null && this
						.getShipperNo().equals(castOther.getShipperNo())))
				&& ((this.getShipperDeliverNo() == castOther
						.getShipperDeliverNo()) || (this.getShipperDeliverNo() != null
						&& castOther.getShipperDeliverNo() != null && this
						.getShipperDeliverNo().equals(
								castOther.getShipperDeliverNo())))
				&& ((this.getCustAddress() == castOther.getCustAddress()) || (this
						.getCustAddress() != null
						&& castOther.getCustAddress() != null && this
						.getCustAddress().equals(castOther.getCustAddress())))
				&& ((this.getCustPhone() == castOther.getCustPhone()) || (this
						.getCustPhone() != null
						&& castOther.getCustPhone() != null && this
						.getCustPhone().equals(castOther.getCustPhone())))
				&& ((this.getContactorName() == castOther.getContactorName()) || (this
						.getContactorName() != null
						&& castOther.getContactorName() != null && this
						.getContactorName()
						.equals(castOther.getContactorName())))
				&& ((this.getSendAddress() == castOther.getSendAddress()) || (this
						.getSendAddress() != null
						&& castOther.getSendAddress() != null && this
						.getSendAddress().equals(castOther.getSendAddress())))
				&& ((this.getSendMobilePhone() == castOther
						.getSendMobilePhone()) || (this.getSendMobilePhone() != null
						&& castOther.getSendMobilePhone() != null && this
						.getSendMobilePhone().equals(
								castOther.getSendMobilePhone())))
				&& ((this.getSendName() == castOther.getSendName()) || (this
						.getSendName() != null
						&& castOther.getSendName() != null && this
						.getSendName().equals(castOther.getSendName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnterpriseNo() == null ? 0 : this.getEnterpriseNo()
						.hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getPoNo() == null ? 0 : this.getPoNo().hashCode());
		result = 37 * result
				+ (getPoType() == null ? 0 : this.getPoType().hashCode());
		result = 37
				* result
				+ (getSourceexpNo() == null ? 0 : this.getSourceexpNo()
						.hashCode());
		result = 37 * result
				+ (getExpDate() == null ? 0 : this.getExpDate().hashCode());
		result = 37 * result
				+ (getShipperNo() == null ? 0 : this.getShipperNo().hashCode());
		result = 37
				* result
				+ (getShipperDeliverNo() == null ? 0 : this
						.getShipperDeliverNo().hashCode());
		result = 37
				* result
				+ (getCustAddress() == null ? 0 : this.getCustAddress()
						.hashCode());
		result = 37 * result
				+ (getCustPhone() == null ? 0 : this.getCustPhone().hashCode());
		result = 37
				* result
				+ (getContactorName() == null ? 0 : this.getContactorName()
						.hashCode());
		result = 37
				* result
				+ (getSendAddress() == null ? 0 : this.getSendAddress()
						.hashCode());
		result = 37
				* result
				+ (getSendMobilePhone() == null ? 0 : this.getSendMobilePhone()
						.hashCode());
		result = 37 * result
				+ (getSendName() == null ? 0 : this.getSendName().hashCode());
		return result;
	}

}