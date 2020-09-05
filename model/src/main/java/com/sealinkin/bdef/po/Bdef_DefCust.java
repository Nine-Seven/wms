package com.sealinkin.bdef.po;

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
 * BdefDefcust entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BDEF_DEFCUST")
public class Bdef_DefCust implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bdef_DefcustId id;
	private String ownerNo;
	private String ownerCustNo;
	private String custFlag;
	private Byte custType;
	private String shippingMethod;
	private String boxDeliver;
	private String custName;
	private String custAlias;
	private String custAddress;
	private String custPostcode;
	private String deliveryAddress;
	private String custPhone1;
	private String custPhone2;
	private String custFax1;
	private String custFax2;
	private String custEmail1;
	private String custEmail2;
	private String contactorName1;
	private String contactorName2;
	private String invoiceNo;
	private String invoiceAddr;
	private String invoiceHeader;
	private String remark;
	private String status;
	private Double maxCarTonnage;
	private String receivingHours;
	private String createFlag;
	private String custDept;
	private String custProvince;
	private String custCity;
	private String custZone;
	private String custNotecode;
	private String tradeFlag;
	private String onlyDateFlag;
	private String collectFlag;
	private String containerMaterial;
	private String warnFlag;
	private BigDecimal divideOrder;
	private String rgstName;
	private Date rgstDate;
	private String updtName;
	private Date updtDate;
	private Short prioLevel;
	private String prioType;
	private String controlType;
	private BigDecimal controlValue;

	// Constructors

	/** default constructor */
	public Bdef_DefCust() {
	}

	/** minimal constructor */
	public Bdef_DefCust(Bdef_DefcustId id, String ownerNo, String ownerCustNo,
			String custFlag, Byte custType, String shippingMethod,
			String boxDeliver, String custName, String status,
			String tradeFlag, String onlyDateFlag, String collectFlag,
			String containerMaterial, String warnFlag, String rgstName,
			Date rgstDate, Short prioLevel) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.ownerCustNo = ownerCustNo;
		this.custFlag = custFlag;
		this.custType = custType;
		this.shippingMethod = shippingMethod;
		this.boxDeliver = boxDeliver;
		this.custName = custName;
		this.status = status;
		this.tradeFlag = tradeFlag;
		this.onlyDateFlag = onlyDateFlag;
		this.collectFlag = collectFlag;
		this.containerMaterial = containerMaterial;
		this.warnFlag = warnFlag;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.prioLevel = prioLevel;
	}

	/** full constructor */
	public Bdef_DefCust(Bdef_DefcustId id, String ownerNo, String ownerCustNo,
			String custFlag, Byte custType, String shippingMethod,
			String boxDeliver, String custName, String custAlias,
			String custAddress, String custPostcode, String deliveryAddress,
			String custPhone1, String custPhone2, String custFax1,
			String custFax2, String custEmail1, String custEmail2,
			String contactorName1, String contactorName2, String invoiceNo,
			String invoiceAddr, String invoiceHeader, String remark,
			String status, Double maxCarTonnage, String receivingHours,
			String createFlag, String custDept, String custProvince,
			String custCity, String custZone, String custNotecode,
			String tradeFlag, String onlyDateFlag, String collectFlag,
			String containerMaterial, String warnFlag, BigDecimal divideOrder,
			String rgstName, Date rgstDate, String updtName, Date updtDate,
			Short prioLevel, String prioType, String controlType,
			BigDecimal controlValue) {
		this.id = id;
		this.ownerNo = ownerNo;
		this.ownerCustNo = ownerCustNo;
		this.custFlag = custFlag;
		this.custType = custType;
		this.shippingMethod = shippingMethod;
		this.boxDeliver = boxDeliver;
		this.custName = custName;
		this.custAlias = custAlias;
		this.custAddress = custAddress;
		this.custPostcode = custPostcode;
		this.deliveryAddress = deliveryAddress;
		this.custPhone1 = custPhone1;
		this.custPhone2 = custPhone2;
		this.custFax1 = custFax1;
		this.custFax2 = custFax2;
		this.custEmail1 = custEmail1;
		this.custEmail2 = custEmail2;
		this.contactorName1 = contactorName1;
		this.contactorName2 = contactorName2;
		this.invoiceNo = invoiceNo;
		this.invoiceAddr = invoiceAddr;
		this.invoiceHeader = invoiceHeader;
		this.remark = remark;
		this.status = status;
		this.maxCarTonnage = maxCarTonnage;
		this.receivingHours = receivingHours;
		this.createFlag = createFlag;
		this.custDept = custDept;
		this.custProvince = custProvince;
		this.custCity = custCity;
		this.custZone = custZone;
		this.custNotecode = custNotecode;
		this.tradeFlag = tradeFlag;
		this.onlyDateFlag = onlyDateFlag;
		this.collectFlag = collectFlag;
		this.containerMaterial = containerMaterial;
		this.warnFlag = warnFlag;
		this.divideOrder = divideOrder;
		this.rgstName = rgstName;
		this.rgstDate = rgstDate;
		this.updtName = updtName;
		this.updtDate = updtDate;
		this.prioLevel = prioLevel;
		this.prioType = prioType;
		this.controlType = controlType;
		this.controlValue = controlValue;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "enterpriseNo", column = @Column(name = "ENTERPRISE_NO", nullable = false, length = 15)),
			@AttributeOverride(name = "custNo", column = @Column(name = "CUST_NO", nullable = false, length = 20)) })
	public Bdef_DefcustId getId() {
		return this.id;
	}

	public void setId(Bdef_DefcustId id) {
		this.id = id;
	}

	@Column(name = "OWNER_NO", nullable = false, length = 3)
	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	@Column(name = "OWNER_CUST_NO", nullable = false, length = 15)
	public String getOwnerCustNo() {
		return this.ownerCustNo;
	}

	public void setOwnerCustNo(String ownerCustNo) {
		this.ownerCustNo = ownerCustNo;
	}

	@Column(name = "CUST_FLAG", nullable = false, length = 1)
	public String getCustFlag() {
		return this.custFlag;
	}

	public void setCustFlag(String custFlag) {
		this.custFlag = custFlag;
	}

	@Column(name = "CUST_TYPE", nullable = false, precision = 2, scale = 0)
	public Byte getCustType() {
		return this.custType;
	}

	public void setCustType(Byte custType) {
		this.custType = custType;
	}

	@Column(name = "SHIPPING_METHOD", nullable = false, length = 1)
	public String getShippingMethod() {
		return this.shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	@Column(name = "BOX_DELIVER", nullable = false, length = 1)
	public String getBoxDeliver() {
		return this.boxDeliver;
	}

	public void setBoxDeliver(String boxDeliver) {
		this.boxDeliver = boxDeliver;
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

	@Column(name = "CUST_POSTCODE", length = 10)
	public String getCustPostcode() {
		return this.custPostcode;
	}

	public void setCustPostcode(String custPostcode) {
		this.custPostcode = custPostcode;
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

	@Column(name = "CUST_PHONE2", length = 50)
	public String getCustPhone2() {
		return this.custPhone2;
	}

	public void setCustPhone2(String custPhone2) {
		this.custPhone2 = custPhone2;
	}

	@Column(name = "CUST_FAX1", length = 20)
	public String getCustFax1() {
		return this.custFax1;
	}

	public void setCustFax1(String custFax1) {
		this.custFax1 = custFax1;
	}

	@Column(name = "CUST_FAX2", length = 20)
	public String getCustFax2() {
		return this.custFax2;
	}

	public void setCustFax2(String custFax2) {
		this.custFax2 = custFax2;
	}

	@Column(name = "CUST_EMAIL1", length = 50)
	public String getCustEmail1() {
		return this.custEmail1;
	}

	public void setCustEmail1(String custEmail1) {
		this.custEmail1 = custEmail1;
	}

	@Column(name = "CUST_EMAIL2", length = 50)
	public String getCustEmail2() {
		return this.custEmail2;
	}

	public void setCustEmail2(String custEmail2) {
		this.custEmail2 = custEmail2;
	}

	@Column(name = "CONTACTOR_NAME1", length = 50)
	public String getContactorName1() {
		return this.contactorName1;
	}

	public void setContactorName1(String contactorName1) {
		this.contactorName1 = contactorName1;
	}

	@Column(name = "CONTACTOR_NAME2", length = 50)
	public String getContactorName2() {
		return this.contactorName2;
	}

	public void setContactorName2(String contactorName2) {
		this.contactorName2 = contactorName2;
	}

	@Column(name = "INVOICE_NO", length = 10)
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "INVOICE_ADDR", length = 20)
	public String getInvoiceAddr() {
		return this.invoiceAddr;
	}

	public void setInvoiceAddr(String invoiceAddr) {
		this.invoiceAddr = invoiceAddr;
	}

	@Column(name = "INVOICE_HEADER", length = 20)
	public String getInvoiceHeader() {
		return this.invoiceHeader;
	}

	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	@Column(name = "REMARK", length = 225)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "STATUS", nullable = false, precision = 1, scale = 0)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MAX_CAR_TONNAGE", precision = 8, scale = 5)
	public Double getMaxCarTonnage() {
		return this.maxCarTonnage;
	}

	public void setMaxCarTonnage(Double maxCarTonnage) {
		this.maxCarTonnage = maxCarTonnage;
	}

	@Column(name = "RECEIVING_HOURS", length = 20)
	public String getReceivingHours() {
		return this.receivingHours;
	}

	public void setReceivingHours(String receivingHours) {
		this.receivingHours = receivingHours;
	}

	@Column(name = "CREATE_FLAG", length = 1)
	public String getCreateFlag() {
		return this.createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	@Column(name = "CUST_DEPT", length = 200)
	public String getCustDept() {
		return this.custDept;
	}

	public void setCustDept(String custDept) {
		this.custDept = custDept;
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

	@Column(name = "CUST_NOTECODE", length = 20)
	public String getCustNotecode() {
		return this.custNotecode;
	}

	public void setCustNotecode(String custNotecode) {
		this.custNotecode = custNotecode;
	}

	@Column(name = "TRADE_FLAG", nullable = false, length = 1)
	public String getTradeFlag() {
		return this.tradeFlag;
	}

	public void setTradeFlag(String tradeFlag) {
		this.tradeFlag = tradeFlag;
	}

	@Column(name = "ONLY_DATE_FLAG", nullable = false, length = 1)
	public String getOnlyDateFlag() {
		return this.onlyDateFlag;
	}

	public void setOnlyDateFlag(String onlyDateFlag) {
		this.onlyDateFlag = onlyDateFlag;
	}

	@Column(name = "COLLECT_FLAG", nullable = false, length = 2)
	public String getCollectFlag() {
		return this.collectFlag;
	}

	public void setCollectFlag(String collectFlag) {
		this.collectFlag = collectFlag;
	}

	@Column(name = "CONTAINER_MATERIAL", nullable = false, length = 2)
	public String getContainerMaterial() {
		return this.containerMaterial;
	}

	public void setContainerMaterial(String containerMaterial) {
		this.containerMaterial = containerMaterial;
	}

	@Column(name = "WARN_FLAG", nullable = false, length = 2)
	public String getWarnFlag() {
		return this.warnFlag;
	}

	public void setWarnFlag(String warnFlag) {
		this.warnFlag = warnFlag;
	}

	@Column(name = "DIVIDE_ORDER", precision = 22, scale = 0)
	public BigDecimal getDivideOrder() {
		return this.divideOrder;
	}

	public void setDivideOrder(BigDecimal divideOrder) {
		this.divideOrder = divideOrder;
	}

	@Column(name = "RGST_NAME", nullable = false, length = 20)
	public String getRgstName() {
		return this.rgstName;
	}

	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE", length = 7)
	public Date getUpdtDate() {
		return this.updtDate;
	}

	public void setUpdtDate(Date updtDate) {
		this.updtDate = updtDate;
	}

	@Column(name = "PRIO_LEVEL", nullable = false, precision = 3, scale = 0)
	public Short getPrioLevel() {
		return this.prioLevel;
	}

	public void setPrioLevel(Short prioLevel) {
		this.prioLevel = prioLevel;
	}

	@Column(name = "PRIO_TYPE")
	public String getPrioType() {
		return this.prioType;
	}

	public void setPrioType(String prioType) {
		this.prioType = prioType;
	}

	@Column(name = "CONTROL_TYPE", length = 1)
	public String getControlType() {
		return this.controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	@Column(name = "CONTROL_VALUE", precision = 22, scale = 0)
	public BigDecimal getControlValue() {
		return this.controlValue;
	}

	public void setControlValue(BigDecimal controlValue) {
		this.controlValue = controlValue;
	}

}