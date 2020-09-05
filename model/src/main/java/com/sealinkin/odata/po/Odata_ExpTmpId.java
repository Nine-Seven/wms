package com.sealinkin.odata.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OdataExpTmpId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class Odata_ExpTmpId implements java.io.Serializable {

	// Fields

	private String warehouseNo;
	private String ownerNo;
	private String expType;
	private String sourceexpNo;
	private String custNo;
	private String fastFlag;
	private String ownerArticleNo;
	private Double packingQty;
	private Double articleQty;
	private String lotnoCondition;
	private String lotnoValue1;
	private String lotnoValue2;
	private String expRemark;
	private String status;
	private String enterpriseNo;
	private Double rowId;
	private String specifyField;
	private String specifyCondition;
	private String specifyValue1;
	private String specifyValue2;
	private Double unitCost;
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
	private Date expDate;
	private String takeType;
	private String produceCondition;
	private Date produceValue1;
	private Date produceValue2;
	private String contactorName;
	private String shipperNo;
	private String shipperDeliverNo;
	private String orderSource;
	private String sendName;
	private String sendMobilePhone;
    private String sendTelephone;
    private String sendAddress;
    private String receiveProvince;
    private String receiveCity;
    private String receiveZone;
    private String custAddress;
    private String custPhone;

	// Constructors

	/** default constructor */
	public Odata_ExpTmpId() {
	}

	/** minimal constructor */
	public Odata_ExpTmpId(String warehouseNo, String ownerNo, String expType,
			String sourceexpNo, String custNo, String fastFlag,
			String ownerArticleNo, Double packingQty, Double articleQty,
			String status, String enterpriseNo, Double rowId, Date expDate,
			String takeType) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.sourceexpNo = sourceexpNo;
		this.custNo = custNo;
		this.fastFlag = fastFlag;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
		this.expDate = expDate;
		this.takeType = takeType;
	}

	/** full constructor */
	public Odata_ExpTmpId(String warehouseNo, String ownerNo, String expType,
			String sourceexpNo, String custNo, String fastFlag,
			String ownerArticleNo, Double packingQty, Double articleQty,
			String lotnoCondition, String lotnoValue1, String lotnoValue2,
			String expRemark, String status, String enterpriseNo,
			Double rowId, String specifyField, String specifyCondition,
			String specifyValue1, String specifyValue2, Double unitCost,
			String rsvVarod1, String rsvVarod2, String rsvVarod3,
			String rsvVarod4, String rsvVarod5, String rsvVarod6,
			String rsvVarod7, String rsvVarod8, Double rsvNum1,
			Double rsvNum2, Double rsvNum3, Date rsvDate1,
			Date rsvDate2, Date rsvDate3, Date expDate, String takeType,
			String produceCondition, Date produceValue1, Date produceValue2,
			String contactorName, String shipperNo, String shipperDeliverNo,
			String orderSource,String sendName,String sendMobilePhone,
			String sendTelephone,String sendAddress,String custAddress,
			String custPhone,String receiveProvince,String receiveCity,String receiveZone) {
		this.warehouseNo = warehouseNo;
		this.ownerNo = ownerNo;
		this.expType = expType;
		this.sourceexpNo = sourceexpNo;
		this.custNo = custNo;
		this.fastFlag = fastFlag;
		this.ownerArticleNo = ownerArticleNo;
		this.packingQty = packingQty;
		this.articleQty = articleQty;
		this.lotnoCondition = lotnoCondition;
		this.lotnoValue1 = lotnoValue1;
		this.lotnoValue2 = lotnoValue2;
		this.expRemark = expRemark;
		this.status = status;
		this.enterpriseNo = enterpriseNo;
		this.rowId = rowId;
		this.specifyField = specifyField;
		this.specifyCondition = specifyCondition;
		this.specifyValue1 = specifyValue1;
		this.specifyValue2 = specifyValue2;
		this.unitCost = unitCost;
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
		this.expDate = expDate;
		this.takeType = takeType;
		this.produceCondition = produceCondition;
		this.produceValue1 = produceValue1;
		this.produceValue2 = produceValue2;
		this.contactorName = contactorName;
		this.shipperNo = shipperNo;
		this.shipperDeliverNo = shipperDeliverNo;
		this.orderSource = orderSource;
		this.sendName = sendName;
		this.sendMobilePhone = sendMobilePhone;
		this.sendTelephone = sendTelephone;
		this.sendAddress = sendAddress;
		this.custAddress = custAddress;
		this.custPhone = custPhone;
		this.receiveProvince = receiveProvince;
		this.receiveCity = receiveCity;
		this.receiveZone = receiveZone;
	}

	// Property accessors

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

	@Column(name = "EXP_TYPE", nullable = false, length = 5)
	public String getExpType() {
		return this.expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	@Column(name = "SOURCEEXP_NO", nullable = false, length = 30)
	public String getSourceexpNo() {
		return this.sourceexpNo;
	}

	public void setSourceexpNo(String sourceexpNo) {
		this.sourceexpNo = sourceexpNo;
	}

	@Column(name = "CUST_NO", nullable = false, length = 10)
	public String getCustNo() {
		return this.custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	@Column(name = "FAST_FLAG", nullable = false, length = 1)
	public String getFastFlag() {
		return this.fastFlag;
	}

	public void setFastFlag(String fastFlag) {
		this.fastFlag = fastFlag;
	}

	@Column(name = "OWNER_ARTICLE_NO", nullable = false, length = 15)
	public String getOwnerArticleNo() {
		return this.ownerArticleNo;
	}

	public void setOwnerArticleNo(String ownerArticleNo) {
		this.ownerArticleNo = ownerArticleNo;
	}

	@Column(name = "PACKING_QTY", nullable = false, precision = 18, scale = 5)
	public Double getPackingQty() {
		return this.packingQty;
	}

	public void setPackingQty(Double packingQty) {
		this.packingQty = packingQty;
	}

	@Column(name = "ARTICLE_QTY", nullable = false, precision = 18, scale = 5)
	public Double getArticleQty() {
		return this.articleQty;
	}

	public void setArticleQty(Double articleQty) {
		this.articleQty = articleQty;
	}

	@Column(name = "LOTNO_CONDITION", length = 100)
	public String getLotnoCondition() {
		return this.lotnoCondition;
	}

	public void setLotnoCondition(String lotnoCondition) {
		this.lotnoCondition = lotnoCondition;
	}

	@Column(name = "LOTNO_VALUE1", length = 50)
	public String getLotnoValue1() {
		return this.lotnoValue1;
	}

	public void setLotnoValue1(String lotnoValue1) {
		this.lotnoValue1 = lotnoValue1;
	}

	@Column(name = "LOTNO_VALUE2", length = 50)
	public String getLotnoValue2() {
		return this.lotnoValue2;
	}

	public void setLotnoValue2(String lotnoValue2) {
		this.lotnoValue2 = lotnoValue2;
	}

	@Column(name = "EXP_REMARK")
	public String getExpRemark() {
		return this.expRemark;
	}

	public void setExpRemark(String expRemark) {
		this.expRemark = expRemark;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ENTERPRISE_NO", nullable = false, length = 15)
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

	@Column(name = "SPECIFY_FIELD", length = 50)
	public String getSpecifyField() {
		return this.specifyField;
	}

	public void setSpecifyField(String specifyField) {
		this.specifyField = specifyField;
	}

	@Column(name = "SPECIFY_CONDITION", length = 100)
	public String getSpecifyCondition() {
		return this.specifyCondition;
	}

	public void setSpecifyCondition(String specifyCondition) {
		this.specifyCondition = specifyCondition;
	}

	@Column(name = "SPECIFY_VALUE1", length = 50)
	public String getSpecifyValue1() {
		return this.specifyValue1;
	}

	public void setSpecifyValue1(String specifyValue1) {
		this.specifyValue1 = specifyValue1;
	}

	@Column(name = "SPECIFY_VALUE2", length = 50)
	public String getSpecifyValue2() {
		return this.specifyValue2;
	}

	public void setSpecifyValue2(String specifyValue2) {
		this.specifyValue2 = specifyValue2;
	}

	@Column(name = "UNIT_COST", precision = 11, scale = 5)
	public Double getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXP_DATE", nullable = false, length = 7)
	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	@Column(name = "TAKE_TYPE", nullable = false, length = 1)
	public String getTakeType() {
		return this.takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	@Column(name = "PRODUCE_CONDITION", length = 100)
	public String getProduceCondition() {
		return this.produceCondition;
	}

	public void setProduceCondition(String produceCondition) {
		this.produceCondition = produceCondition;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRODUCE_VALUE1", length = 7)
	public Date getProduceValue1() {
		return this.produceValue1;
	}

	public void setProduceValue1(Date produceValue1) {
		this.produceValue1 = produceValue1;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRODUCE_VALUE2", length = 7)
	public Date getProduceValue2() {
		return this.produceValue2;
	}

	public void setProduceValue2(Date produceValue2) {
		this.produceValue2 = produceValue2;
	}

	@Column(name = "CONTACTOR_NAME", length = 50)
	public String getContactorName() {
		return this.contactorName;
	}

	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	@Column(name = "SHIPPER_NO", length = 15)
	public String getShipperNo() {
		return this.shipperNo;
	}

	public void setShipperNo(String shipperNo) {
		this.shipperNo = shipperNo;
	}

	@Column(name = "SHIPPER_DELIVER_NO", length = 30)
	public String getShipperDeliverNo() {
		return this.shipperDeliverNo;
	}

	public void setShipperDeliverNo(String shipperDeliverNo) {
		this.shipperDeliverNo = shipperDeliverNo;
	}

	@Column(name = "ORDER_SOURCE", length = 2)
	public String getOrderSource() {
		return this.orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	
	@Column(name="SEND_NAME", length=50)
    public String getSendName() {
        return this.sendName;
    }
    
    public void setSendName(String sendName) {
        this.sendName = sendName;
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
    
    @Column(name="SEND_ADDRESS", length=100)
    public String getSendAddress() {
        return this.sendAddress;
    }
    
    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }
    
    @Column(name="CUST_ADDRESS", length=100)
    public String getCustAddress() {
        return this.custAddress;
    }
    
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
    
    @Column(name="CUST_PHONE", length=50)
    public String getCustPhone() {
        return this.custPhone;
    }
    
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
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
    
    
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Odata_ExpTmpId))
			return false;
		Odata_ExpTmpId castOther = (Odata_ExpTmpId) other;

		return ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
				.getWarehouseNo() != null && castOther.getWarehouseNo() != null && this
				.getWarehouseNo().equals(castOther.getWarehouseNo())))
				&& ((this.getOwnerNo() == castOther.getOwnerNo()) || (this
						.getOwnerNo() != null && castOther.getOwnerNo() != null && this
						.getOwnerNo().equals(castOther.getOwnerNo())))
				&& ((this.getExpType() == castOther.getExpType()) || (this
						.getExpType() != null && castOther.getExpType() != null && this
						.getExpType().equals(castOther.getExpType())))
				&& ((this.getSourceexpNo() == castOther.getSourceexpNo()) || (this
						.getSourceexpNo() != null
						&& castOther.getSourceexpNo() != null && this
						.getSourceexpNo().equals(castOther.getSourceexpNo())))
				&& ((this.getCustNo() == castOther.getCustNo()) || (this
						.getCustNo() != null && castOther.getCustNo() != null && this
						.getCustNo().equals(castOther.getCustNo())))
				&& ((this.getFastFlag() == castOther.getFastFlag()) || (this
						.getFastFlag() != null
						&& castOther.getFastFlag() != null && this
						.getFastFlag().equals(castOther.getFastFlag())))
				&& ((this.getOwnerArticleNo() == castOther.getOwnerArticleNo()) || (this
						.getOwnerArticleNo() != null
						&& castOther.getOwnerArticleNo() != null && this
						.getOwnerArticleNo().equals(
								castOther.getOwnerArticleNo())))
				&& ((this.getPackingQty() == castOther.getPackingQty()) || (this
						.getPackingQty() != null
						&& castOther.getPackingQty() != null && this
						.getPackingQty().equals(castOther.getPackingQty())))
				&& ((this.getArticleQty() == castOther.getArticleQty()) || (this
						.getArticleQty() != null
						&& castOther.getArticleQty() != null && this
						.getArticleQty().equals(castOther.getArticleQty())))
				&& ((this.getLotnoCondition() == castOther.getLotnoCondition()) || (this
						.getLotnoCondition() != null
						&& castOther.getLotnoCondition() != null && this
						.getLotnoCondition().equals(
								castOther.getLotnoCondition())))
				&& ((this.getLotnoValue1() == castOther.getLotnoValue1()) || (this
						.getLotnoValue1() != null
						&& castOther.getLotnoValue1() != null && this
						.getLotnoValue1().equals(castOther.getLotnoValue1())))
				&& ((this.getLotnoValue2() == castOther.getLotnoValue2()) || (this
						.getLotnoValue2() != null
						&& castOther.getLotnoValue2() != null && this
						.getLotnoValue2().equals(castOther.getLotnoValue2())))
				&& ((this.getExpRemark() == castOther.getExpRemark()) || (this
						.getExpRemark() != null
						&& castOther.getExpRemark() != null && this
						.getExpRemark().equals(castOther.getExpRemark())))
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
				&& ((this.getSpecifyField() == castOther.getSpecifyField()) || (this
						.getSpecifyField() != null
						&& castOther.getSpecifyField() != null && this
						.getSpecifyField().equals(castOther.getSpecifyField())))
				&& ((this.getSpecifyCondition() == castOther
						.getSpecifyCondition()) || (this.getSpecifyCondition() != null
						&& castOther.getSpecifyCondition() != null && this
						.getSpecifyCondition().equals(
								castOther.getSpecifyCondition())))
				&& ((this.getSpecifyValue1() == castOther.getSpecifyValue1()) || (this
						.getSpecifyValue1() != null
						&& castOther.getSpecifyValue1() != null && this
						.getSpecifyValue1()
						.equals(castOther.getSpecifyValue1())))
				&& ((this.getSpecifyValue2() == castOther.getSpecifyValue2()) || (this
						.getSpecifyValue2() != null
						&& castOther.getSpecifyValue2() != null && this
						.getSpecifyValue2()
						.equals(castOther.getSpecifyValue2())))
				&& ((this.getUnitCost() == castOther.getUnitCost()) || (this
						.getUnitCost() != null
						&& castOther.getUnitCost() != null && this
						.getUnitCost().equals(castOther.getUnitCost())))
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
						.getRsvDate3().equals(castOther.getRsvDate3())))
				&& ((this.getExpDate() == castOther.getExpDate()) || (this
						.getExpDate() != null && castOther.getExpDate() != null && this
						.getExpDate().equals(castOther.getExpDate())))
				&& ((this.getTakeType() == castOther.getTakeType()) || (this
						.getTakeType() != null
						&& castOther.getTakeType() != null && this
						.getTakeType().equals(castOther.getTakeType())))
				&& ((this.getProduceCondition() == castOther
						.getProduceCondition()) || (this.getProduceCondition() != null
						&& castOther.getProduceCondition() != null && this
						.getProduceCondition().equals(
								castOther.getProduceCondition())))
				&& ((this.getProduceValue1() == castOther.getProduceValue1()) || (this
						.getProduceValue1() != null
						&& castOther.getProduceValue1() != null && this
						.getProduceValue1()
						.equals(castOther.getProduceValue1())))
				&& ((this.getProduceValue2() == castOther.getProduceValue2()) || (this
						.getProduceValue2() != null
						&& castOther.getProduceValue2() != null && this
						.getProduceValue2()
						.equals(castOther.getProduceValue2())))
				&& ((this.getContactorName() == castOther.getContactorName()) || (this
						.getContactorName() != null
						&& castOther.getContactorName() != null && this
						.getContactorName()
						.equals(castOther.getContactorName())))
				&& ((this.getShipperNo() == castOther.getShipperNo()) || (this
						.getShipperNo() != null
						&& castOther.getShipperNo() != null && this
						.getShipperNo().equals(castOther.getShipperNo())))
				&& ((this.getShipperDeliverNo() == castOther
						.getShipperDeliverNo()) || (this.getShipperDeliverNo() != null
						&& castOther.getShipperDeliverNo() != null && this
						.getShipperDeliverNo().equals(
								castOther.getShipperDeliverNo())))
				&& ((this.getOrderSource() == castOther.getOrderSource()) || (this
						.getOrderSource() != null
						&& castOther.getOrderSource() != null && this
						.getOrderSource().equals(castOther.getOrderSource())))
				&& ((this.getSendName() == castOther.getSendName()) || (this
						.getSendName() != null
						&& castOther.getSendName() != null && this
						.getSendName().equals(castOther.getSendName())))
				&& ((this.getSendMobilePhone() == castOther.getSendMobilePhone()) || (this
						.getSendMobilePhone() != null
						&& castOther.getSendMobilePhone() != null && this
						.getSendMobilePhone().equals(castOther.getSendMobilePhone())))
				&& ((this.getSendTelephone() == castOther.getSendTelephone()) || (this
						.getSendTelephone() != null
						&& castOther.getSendTelephone() != null && this
						.getSendTelephone().equals(castOther.getSendTelephone())))
				&& ((this.getSendAddress() == castOther.getSendAddress()) || (this
						.getSendAddress() != null
						&& castOther.getSendAddress() != null && this
						.getSendAddress().equals(castOther.getSendAddress())))
				&& ((this.getCustAddress() == castOther.getCustAddress()) || (this
						.getCustAddress() != null
						&& castOther.getCustAddress() != null && this
						.getCustAddress().equals(castOther.getCustAddress())))
				&& ((this.getCustPhone() == castOther.getCustPhone()) || (this
						.getCustPhone() != null
						&& castOther.getCustPhone() != null && this
						.getCustPhone().equals(castOther.getCustPhone())))
				&& ((this.getReceiveProvince() == castOther.getReceiveProvince()) || (this
						.getReceiveProvince() != null
						&& castOther.getReceiveProvince() != null && this
						.getReceiveProvince().equals(castOther.getReceiveProvince())))
				&& ((this.getReceiveCity() == castOther.getReceiveCity()) || (this
						.getReceiveCity() != null
						&& castOther.getReceiveCity() != null && this
						.getReceiveCity().equals(castOther.getReceiveCity())))
				&& ((this.getReceiveZone() == castOther.getReceiveZone()) || (this
						.getReceiveZone() != null
						&& castOther.getReceiveZone() != null && this
						.getReceiveZone().equals(castOther.getReceiveZone())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		result = 37 * result
				+ (getOwnerNo() == null ? 0 : this.getOwnerNo().hashCode());
		result = 37 * result
				+ (getExpType() == null ? 0 : this.getExpType().hashCode());
		result = 37
				* result
				+ (getSourceexpNo() == null ? 0 : this.getSourceexpNo()
						.hashCode());
		result = 37 * result
				+ (getCustNo() == null ? 0 : this.getCustNo().hashCode());
		result = 37 * result
				+ (getFastFlag() == null ? 0 : this.getFastFlag().hashCode());
		result = 37
				* result
				+ (getOwnerArticleNo() == null ? 0 : this.getOwnerArticleNo()
						.hashCode());
		result = 37
				* result
				+ (getPackingQty() == null ? 0 : this.getPackingQty()
						.hashCode());
		result = 37
				* result
				+ (getArticleQty() == null ? 0 : this.getArticleQty()
						.hashCode());
		result = 37
				* result
				+ (getLotnoCondition() == null ? 0 : this.getLotnoCondition()
						.hashCode());
		result = 37
				* result
				+ (getLotnoValue1() == null ? 0 : this.getLotnoValue1()
						.hashCode());
		result = 37
				* result
				+ (getLotnoValue2() == null ? 0 : this.getLotnoValue2()
						.hashCode());
		result = 37 * result
				+ (getExpRemark() == null ? 0 : this.getExpRemark().hashCode());
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
				+ (getSpecifyField() == null ? 0 : this.getSpecifyField()
						.hashCode());
		result = 37
				* result
				+ (getSpecifyCondition() == null ? 0 : this
						.getSpecifyCondition().hashCode());
		result = 37
				* result
				+ (getSpecifyValue1() == null ? 0 : this.getSpecifyValue1()
						.hashCode());
		result = 37
				* result
				+ (getSpecifyValue2() == null ? 0 : this.getSpecifyValue2()
						.hashCode());
		result = 37 * result
				+ (getUnitCost() == null ? 0 : this.getUnitCost().hashCode());
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
		result = 37 * result
				+ (getExpDate() == null ? 0 : this.getExpDate().hashCode());
		result = 37 * result
				+ (getTakeType() == null ? 0 : this.getTakeType().hashCode());
		result = 37
				* result
				+ (getProduceCondition() == null ? 0 : this
						.getProduceCondition().hashCode());
		result = 37
				* result
				+ (getProduceValue1() == null ? 0 : this.getProduceValue1()
						.hashCode());
		result = 37
				* result
				+ (getProduceValue2() == null ? 0 : this.getProduceValue2()
						.hashCode());
		result = 37
				* result
				+ (getContactorName() == null ? 0 : this.getContactorName()
						.hashCode());
		result = 37 * result
				+ (getShipperNo() == null ? 0 : this.getShipperNo().hashCode());
		result = 37
				* result
				+ (getShipperDeliverNo() == null ? 0 : this
						.getShipperDeliverNo().hashCode());
		result = 37
				* result
				+ (getOrderSource() == null ? 0 : this.getOrderSource()
						.hashCode());
		result = 37
				* result
				+ (getSendName() == null ? 0 : this.getSendName()
						.hashCode());
		result = 37
				* result
				+ (getSendMobilePhone() == null ? 0 : this.getSendMobilePhone()
						.hashCode());
		result = 37
				* result
				+ (getSendTelephone() == null ? 0 : this.getSendTelephone()
						.hashCode());
		result = 37
				* result
				+ (getSendAddress() == null ? 0 : this.getSendAddress()
						.hashCode());
		result = 37
				* result
				+ (getCustAddress() == null ? 0 : this.getCustAddress()
						.hashCode());
		result = 37
				* result
				+ (getCustPhone() == null ? 0 : this.getCustPhone()
						.hashCode());
		result = 37
				* result
				+ (getReceiveProvince() == null ? 0 : this.getReceiveProvince()
						.hashCode());
		result = 37
				* result
				+ (getReceiveCity() == null ? 0 : this.getReceiveCity()
						.hashCode());
		result = 37
				* result
				+ (getReceiveZone() == null ? 0 : this.getReceiveZone()
						.hashCode());
		return result;
	}

}