package com.sealinkin.odata.po;

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
 * OdataPackageD entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ODATA_PACKAGE_D")
public class Odata_PackageD implements java.io.Serializable {

	// Fields

	private Odata_PackageDId id;
	private String ownerNo;
	private String ownerCustNo;
	private String custNo;
	private String subCustNo;
	private Date expDate;
	private String status;
	private String custAddress;
	private String custAddressCode;
	private String contactorName;
	private String custPhone;
	private String custMail;
	private String remark;
	private String printFlag;
	private String cellNo;
	private String containerNo;
	private String shipperNo;
	private String deliverAddress;
	private String orgNo;
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
	private String deliverdateType;
	private String envoicePrintStatus;
	private String waybillPrintStatus;
	private String packlistPrintStatus;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private String instockName;
	private Date instockDate;
	private String outstockName;
	private Date outstockDate;

	// Constructors

	/** default constructor */
	public Odata_PackageD() {
	}

	/** minimal constructor */
	public Odata_PackageD(Odata_PackageDId id, String ownerNo) {
		this.id = id;
		this.ownerNo = ownerNo;
	}

	/** full constructor */
	public Odata_PackageD(Odata_PackageDId id, String ownerNo,
			String ownerCustNo, String custNo, String subCustNo, Date expDate,
			String status, String custAddress, String custAddressCode,
			String contactorName, String custPhone, String custMail,
			String remark, String printFlag, String cellNo, String containerNo,
			String shipperNo, String deliverAddress, String orgNo,
			String sendAddress, String sendAddressCode, String sendName,
			String sendCompanyName, String sendPostcode,
			String sendMobilePhone, String sendTelephone, String sendJpn,
			String sendProvince, String sendCity, String sendZone,
			String sendCountry, String receiveCompanyName,
			String receiveTelephone, String receiveJpn, String receiveProvince,
			String receiveCity, String receiveZone, String receiveCountry,
			String takeAddress, String takeAddressCode, String takeName,
			String takeCompanyName, String takePostcode,
			String takeMobilePhone, String takeTelephone, String takeJpn,
			String takeProvince, String takeCity, String takeZone,
			String takeCountry, String deliverdateType,
			String envoicePrintStatus, String waybillPrintStatus,
			String packlistPrintStatus, String rgstName, Date rgstDate,
			String updtName, Date updtDate, String instockName,
			Date instockDate, String outstockName, Date outstockDate) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.ownerCustNo = ownerCustNo;
		this.custNo = custNo;
		this.subCustNo = subCustNo;
		this.expDate = expDate;
		this.status = status;
		this.custAddress = custAddress;
		this.custAddressCode = custAddressCode;
		this.contactorName = contactorName;
		this.custPhone = custPhone;
		this.custMail = custMail;
		this.remark = remark;
		this.printFlag = printFlag;
		this.cellNo = cellNo;
		this.containerNo = containerNo;
		this.shipperNo = shipperNo;
		this.deliverAddress = deliverAddress;
		this.orgNo = orgNo;
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
		this.deliverdateType = deliverdateType;
		this.envoicePrintStatus = envoicePrintStatus;
		this.waybillPrintStatus = waybillPrintStatus;
		this.packlistPrintStatus = packlistPrintStatus;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.instockName = instockName;
		this.instockDate = instockDate;
		this.outstockName = outstockName;
		this.outstockDate = outstockDate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "warehouseNo", column = @Column(name = "WAREHOUSE_NO", nullable = false, length = 5)),
			@AttributeOverride(name = "poNo", column = @Column(name = "PO_NO", nullable = false, length = 20)),
			@AttributeOverride(name = "sourceexpNo", column = @Column(name = "SOURCEEXP_NO", nullable = false, length = 30)),
			@AttributeOverride(name = "shipperDeliverNo", column = @Column(name = "SHIPPER_DELIVER_NO", nullable = false, length = 30)) })
	public Odata_PackageDId getId() {
		return this.id;
	}

	public void setId(Odata_PackageDId id) {
		this.id = id;
	}

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

	@Column(name = "CUST_NO", length = 20)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "SUB_CUST_NO", length = 20)
	public String getSubCustNo() {
		return this.subCustNo;
	}

	public void setSubCustNo(String subCustNo) {
		this.subCustNo = subCustNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXP_DATE", length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CUST_ADDRESS", length = 100)
	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	@Column(name = "CUST_ADDRESS_CODE", length = 20)
	public String getCustAddressCode() {
		return this.custAddressCode;
	}

	public void setCustAddressCode(String custAddressCode) {
		this.custAddressCode = custAddressCode;
	}

	@Column(name = "CONTACTOR_NAME", length = 50)
	public String getContactorName() {
		return this.contactorName;
	}

	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	@Column(name = "CUST_PHONE", length = 50)
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	@Column(name = "CUST_MAIL", length = 50)
	public String getCustMail() {
		return this.custMail;
	}

	public void setCustMail(String custMail) {
		this.custMail = custMail;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "PRINT_FLAG", length = 1)
	public String getPrintFlag() {
		return this.printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	@Column(name = "CELL_NO", length = 24)
	public String getCellNo() {
		return this.cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	@Column(name = "CONTAINER_NO", length = 24)
	public String getContainerNo() {
		return this.containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	@Column(name = "SHIPPER_NO", length = 15)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	@Column(name = "DELIVER_ADDRESS", length = 50)
	public String getDeliverAddress() {
		return this.deliverAddress;
	}

	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}

	@Column(name = "ORG_NO", length = 20)
	public String getOrgNo() {
		return this.orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	@Column(name = "SEND_ADDRESS", length = 100)
	public String getSendAddress() {
		return this.sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	@Column(name = "SEND_ADDRESS_CODE", length = 20)
	public String getSendAddressCode() {
		return this.sendAddressCode;
	}

	public void setSendAddressCode(String sendAddressCode) {
		this.sendAddressCode = sendAddressCode;
	}

	@Column(name = "SEND_NAME", length = 50)
	public String getSendName() {
		return this.sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	@Column(name = "SEND_COMPANY_NAME", length = 100)
	public String getSendCompanyName() {
		return this.sendCompanyName;
	}

	public void setSendCompanyName(String sendCompanyName) {
		this.sendCompanyName = sendCompanyName;
	}

	@Column(name = "SEND_POSTCODE", length = 20)
	public String getSendPostcode() {
		return this.sendPostcode;
	}

	public void setSendPostcode(String sendPostcode) {
		this.sendPostcode = sendPostcode;
	}

	@Column(name = "SEND_MOBILE_PHONE", length = 20)
	public String getSendMobilePhone() {
		return this.sendMobilePhone;
	}

	public void setSendMobilePhone(String sendMobilePhone) {
		this.sendMobilePhone = sendMobilePhone;
	}

	@Column(name = "SEND_TELEPHONE", length = 20)
	public String getSendTelephone() {
		return this.sendTelephone;
	}

	public void setSendTelephone(String sendTelephone) {
		this.sendTelephone = sendTelephone;
	}

	@Column(name = "SEND_JPN", length = 10)
	public String getSendJpn() {
		return this.sendJpn;
	}

	public void setSendJpn(String sendJpn) {
		this.sendJpn = sendJpn;
	}

	@Column(name = "SEND_PROVINCE", length = 50)
	public String getSendProvince() {
		return this.sendProvince;
	}

	public void setSendProvince(String sendProvince) {
		this.sendProvince = sendProvince;
	}

	@Column(name = "SEND_CITY", length = 50)
	public String getSendCity() {
		return this.sendCity;
	}

	public void setSendCity(String sendCity) {
		this.sendCity = sendCity;
	}

	@Column(name = "SEND_ZONE", length = 50)
	public String getSendZone() {
		return this.sendZone;
	}

	public void setSendZone(String sendZone) {
		this.sendZone = sendZone;
	}

	@Column(name = "SEND_COUNTRY", length = 50)
	public String getSendCountry() {
		return this.sendCountry;
	}

	public void setSendCountry(String sendCountry) {
		this.sendCountry = sendCountry;
	}

	@Column(name = "RECEIVE_COMPANY_NAME", length = 100)
	public String getReceiveCompanyName() {
		return this.receiveCompanyName;
	}

	public void setReceiveCompanyName(String receiveCompanyName) {
		this.receiveCompanyName = receiveCompanyName;
	}

	@Column(name = "RECEIVE_TELEPHONE", length = 20)
	public String getReceiveTelephone() {
		return this.receiveTelephone;
	}

	public void setReceiveTelephone(String receiveTelephone) {
		this.receiveTelephone = receiveTelephone;
	}

	@Column(name = "RECEIVE_JPN", length = 10)
	public String getReceiveJpn() {
		return this.receiveJpn;
	}

	public void setReceiveJpn(String receiveJpn) {
		this.receiveJpn = receiveJpn;
	}

	@Column(name = "RECEIVE_PROVINCE", length = 50)
	public String getReceiveProvince() {
		return this.receiveProvince;
	}

	public void setReceiveProvince(String receiveProvince) {
		this.receiveProvince = receiveProvince;
	}

	@Column(name = "RECEIVE_CITY", length = 50)
	public String getReceiveCity() {
		return this.receiveCity;
	}

	public void setReceiveCity(String receiveCity) {
		this.receiveCity = receiveCity;
	}

	@Column(name = "RECEIVE_ZONE", length = 50)
	public String getReceiveZone() {
		return this.receiveZone;
	}

	public void setReceiveZone(String receiveZone) {
		this.receiveZone = receiveZone;
	}

	@Column(name = "RECEIVE_COUNTRY", length = 50)
	public String getReceiveCountry() {
		return this.receiveCountry;
	}

	public void setReceiveCountry(String receiveCountry) {
		this.receiveCountry = receiveCountry;
	}

	@Column(name = "TAKE_ADDRESS", length = 100)
	public String getTakeAddress() {
		return this.takeAddress;
	}

	public void setTakeAddress(String takeAddress) {
		this.takeAddress = takeAddress;
	}

	@Column(name = "TAKE_ADDRESS_CODE", length = 20)
	public String getTakeAddressCode() {
		return this.takeAddressCode;
	}

	public void setTakeAddressCode(String takeAddressCode) {
		this.takeAddressCode = takeAddressCode;
	}

	@Column(name = "TAKE_NAME", length = 50)
	public String getTakeName() {
		return this.takeName;
	}

	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}

	@Column(name = "TAKE_COMPANY_NAME", length = 100)
	public String getTakeCompanyName() {
		return this.takeCompanyName;
	}

	public void setTakeCompanyName(String takeCompanyName) {
		this.takeCompanyName = takeCompanyName;
	}

	@Column(name = "TAKE_POSTCODE", length = 20)
	public String getTakePostcode() {
		return this.takePostcode;
	}

	public void setTakePostcode(String takePostcode) {
		this.takePostcode = takePostcode;
	}

	@Column(name = "TAKE_MOBILE_PHONE", length = 20)
	public String getTakeMobilePhone() {
		return this.takeMobilePhone;
	}

	public void setTakeMobilePhone(String takeMobilePhone) {
		this.takeMobilePhone = takeMobilePhone;
	}

	@Column(name = "TAKE_TELEPHONE", length = 20)
	public String getTakeTelephone() {
		return this.takeTelephone;
	}

	public void setTakeTelephone(String takeTelephone) {
		this.takeTelephone = takeTelephone;
	}

	@Column(name = "TAKE_JPN", length = 10)
	public String getTakeJpn() {
		return this.takeJpn;
	}

	public void setTakeJpn(String takeJpn) {
		this.takeJpn = takeJpn;
	}

	@Column(name = "TAKE_PROVINCE", length = 50)
	public String getTakeProvince() {
		return this.takeProvince;
	}

	public void setTakeProvince(String takeProvince) {
		this.takeProvince = takeProvince;
	}

	@Column(name = "TAKE_CITY", length = 50)
	public String getTakeCity() {
		return this.takeCity;
	}

	public void setTakeCity(String takeCity) {
		this.takeCity = takeCity;
	}

	@Column(name = "TAKE_ZONE", length = 50)
	public String getTakeZone() {
		return this.takeZone;
	}

	public void setTakeZone(String takeZone) {
		this.takeZone = takeZone;
	}

	@Column(name = "TAKE_COUNTRY", length = 50)
	public String getTakeCountry() {
		return this.takeCountry;
	}

	public void setTakeCountry(String takeCountry) {
		this.takeCountry = takeCountry;
	}

	@Column(name = "DELIVERDATE_TYPE", length = 20)
	public String getDeliverdateType() {
		return this.deliverdateType;
	}

	public void setDeliverdateType(String deliverdateType) {
		this.deliverdateType = deliverdateType;
	}

	@Column(name = "ENVOICE_PRINT_STATUS", length = 1)
	public String getEnvoicePrintStatus() {
		return this.envoicePrintStatus;
	}

	public void setEnvoicePrintStatus(String envoicePrintStatus) {
		this.envoicePrintStatus = envoicePrintStatus;
	}

	@Column(name = "WAYBILL_PRINT_STATUS", length = 1)
	public String getWaybillPrintStatus() {
		return this.waybillPrintStatus;
	}

	public void setWaybillPrintStatus(String waybillPrintStatus) {
		this.waybillPrintStatus = waybillPrintStatus;
	}

	@Column(name = "PACKLIST_PRINT_STATUS", length = 1)
	public String getPacklistPrintStatus() {
		return this.packlistPrintStatus;
	}

	public void setPacklistPrintStatus(String packlistPrintStatus) {
		this.packlistPrintStatus = packlistPrintStatus;
	}

	@Column(name = "RGST_NAME", length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RGST_DATE", length = 7)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "INSTOCK_NAME", length = 20)
	public String getInstockName() {
		return this.instockName;
	}

	public void setInstockName(String instockName) {
		this.instockName = instockName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSTOCK_DATE", length = 7)
	public Date getInstockDate() {
		return this.instockDate;
	}

	public void setInstockDate(Date instockDate) {
		this.instockDate = instockDate;
	}

	@Column(name = "OUTSTOCK_NAME", length = 20)
	public String getOutstockName() {
		return this.outstockName;
	}

	public void setOutstockName(String outstockName) {
		this.outstockName = outstockName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OUTSTOCK_DATE", length = 7)
	public Date getOutstockDate() {
		return this.outstockDate;
	}

	public void setOutstockDate(Date outstockDate) {
		this.outstockDate = outstockDate;
	}

}
